import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;

/*
 * 자문데이타 이관 이력 
 * 	2013. 12.18 : SEUK ==> 부서코드체크하여 일부는 EHQ로 하여 이관함. 기존데이타(TLC_FLAWCONSULT)에서 DIVISIONCD(COMP_CD에 해상) 가 NULL인 건이(1529건) 많음. 
 * 							아래의 쿼리로 업데이트 후 내일 재작업 필요
 * 					BEGIN TRAN
						 UPDATE TLC_FLAWCONSULT SET DIVISIONCD = TA.DIVISIONCD
						 FROM (
							SELECT A.*, B.LCNO LCNO_CHK, DIVISIONCD FROM (
								SELECT LCNO, HISTNO
								FROM TLC_FLAWCONSULT WHERE DIVISIONCD IS NULL
							) A 
							LEFT OUTER JOIN
							(SELECT LCNO, MIN(HISTNO) HISTNO, DIVISIONCD
							FROM TLC_FLAWCONSULT WHERE DIVISIONCD IS NOT NULL GROUP BY LCNO,DIVISIONCD) B ON A.LCNO = B.LCNO
						) TA , TLC_FLAWCONSULT TB
						 WHERE TA.LCNO = TB.LCNO AND TB.DIVISIONCD IS NULL
								AND TA.HISTNO=TB.HISTNO
						
					ROLLBACK
 * 							
 * 	2013. 12. 31 : 기존 데이타에 동일한 LCNO에 대해 DIVISIONCD가 틀린 건이 있음. 수작업으로 수정후 작업진행함.
 * 				
 * 	중복 체크쿠리	 : SELECT DIVISIONCD,* from TLC_FLAWCONSULT where LCNO
					  IN (
					 SELECT   LCNO FROM (
						SELECT LCNO, DIVISIONCD FROM TLC_FLAWCONSULT GROUP BY LCNO, DIVISIONCD 
					 )TA 
					 GROUP BY LCNO HAVING COUNT(LCNO)>1
					 )
 * 				
 * */

public class migConsult {

	public migConsult() throws Exception {

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sourceUrl = "jdbc:sqlserver://106.10.1.131:1433;databasename=selmsplus_old;user=selmsplus_dev;password=elmway40!";
		//String targetUrl = "jdbc:sqlserver://106.10.1.131:1433;databasename=selmsplus;user=selmsplus_dev;password=elmway40!"; //구주개발
		String targetUrl = "jdbc:sqlserver://182.198.117.31:1433;databasename=selmsplus_20241121;user=selmsplus_usr;password=qjqan13#"; //구주운영
		
		
		// Source DB
		Connection srConn = null;

		PreparedStatement receptStmt = null;			// TLC_RECEPT
		PreparedStatement fLawConsultStmt = null;		// TLC_FLAWCONSULT
		PreparedStatement outConsultStmt = null;		// TLC_OUTCONSULT
		PreparedStatement lcTypeStmt = null;			// TLC_LCTYPE
		PreparedStatement upFileStmt = null;			// TLC_UPFILE
		PreparedStatement chkCompcdStmt = null;			// COMP_CD 체크
		

		ResultSet receptRs = null;
		ResultSet fLawConsultRs = null;
		ResultSet outConsultRs = null;
		ResultSet lcTypeRs = null;
		ResultSet upFileRs = null;
		ResultSet chkCompRs = null;

		// Target DB
		Connection tgConn = null;

		PreparedStatement insConsultStmt = null;			// TN_LAS_CONSULT
		PreparedStatement delConsultStmt = null;			// TN_LAS_CONSULT
		PreparedStatement insConsultExtnlcompStmt = null;	// TN_LAS_CONSULT_EXTNLCOMP
		PreparedStatement delConsultExtnlcompStmt = null;	// TN_LAS_CONSULT_EXTNLCOMP
		PreparedStatement insConsultTypeStmt = null;		// TN_LAS_CONSULT_TYPE
		PreparedStatement delConsultTypeStmt = null;		// TN_LAS_CONSULT_TYPE
		PreparedStatement insConsultRespmanStmt = null;		// TN_LAS_CONSULT_RESPMAN
		PreparedStatement delConsultRespmanStmt = null;		// TN_LAS_CONSULT_RESPMAN
		PreparedStatement insComAttach = null;				// TN_COM_ATTACH
		PreparedStatement delComAttach = null;				// TN_COM_ATTACH
		

		String sql = "";

		int rsRow = 0;
		int bi = 0;
		int sqlRet = 0;

		String lcNo = "";
		String respHeadJuno = "";
		String respHead = "";
		String respMan1Userid = "";
		String respMan1 = "";
		String respMan2Userid = "";
		String respMan2 = "";
		String respMan3Userid = "";
		String respMan3 = "";
		String orderResman = "";
		String respChk = "";
		
		String state = "";
		String extnlPrgrsStatus = "";
		String intnlPrgrsStatus = "";
		String extnlCnsltyn = "";
		String divisionCd = "";
		String regDeptNm = "";
		String regDept = "";
		String regTelNo = "";
		String regNm = "";
		String regId = "";
		String regDt = "";
		String reDt = "";
		String apbtOpnn = "";
		String apbtmanNm = "";
		String apbtmanId = "";
		String cnsdOpnn = "";
		String cnsdDt = "";
		String cnsdmanNm = "";
		String cnsdmanId = "";
		String appDt = "";
		String holdCause = "";
		String rejctCause = "";
		String mainMatrCont = "";
		String cont = "";
		
		String reqManJuno = "";
		String reqDt = "";
		String refKey = "";
		String fileId = "";
		String fileBigclsfcn = "";
		String fileMidclsfcn = "";
		String fileSmlclsfcn = "";
		String chg_divisioncd = "";
		
		int histNo = 0;

		try {
			srConn = DriverManager.getConnection(sourceUrl);
			tgConn = DriverManager.getConnection(targetUrl);

			tgConn.setAutoCommit(false);

			sql   = "SELECT LCNO"
			      + "      ,RESPHEADJUNO"
			      + "      ,RESPHEAD"
			      + "      ,RESPMAN1USERID"
			      + "      ,RESPMAN1"
			      + "      ,RESPMAN2USERID"
			      + "      ,RESPMAN2"
			      + "      ,RESPMAN3USERID"
			      + "      ,RESPMAN3"
			      + "      ,ORDERRESMAN"
			      + "      ,RESPCHK"
			      + "      ,CONVERT(NVARCHAR(8), APPDT, 112) AS APPDT"
			      + "  FROM TLC_RECEPT A"
				  + "  WHERE EXISTS (SELECT 'E' FROM TLC_FLAWCONSULT B WHERE B.LCNO = A.LCNO "
				  //+ "  AND B.DIVISIONCD IN ('SEUK','SESA','SEBN','SEGR','SEP','SENA') ) ";
				  
				  //2014. 1. 10 적용
				  + "  AND B.DIVISIONCD IN ('SEAD','SEAG','SEB','SECZ','SEF','SEG','SEH-P','SEH-S' "
				  + "                      ,'SEI','SELS','SELSK','SEPM','SEPOL','SEROM','SESG','SESK','SRPOL','SSEL') ) ";	
			receptStmt = srConn.prepareStatement(sql);
			receptRs = receptStmt.executeQuery();

			sql   = "SELECT HISTNO"
			      + "      ,WRPOS"
			      + "      ,WRDEPTH"
			      + "      ,CONVERT(NVARCHAR(30), REGDT, 113) AS REGDT"
			      + "      ,REGMANJUNO"
			      + "      ,REGMANNM"
			      + "      ,REGDEPT"
			      + "      ,REGDEPTNM"
			      + "      ,DIVISIONCD"
			      + "      ,REGTELNO"
			      + "      ,LCTITLE"
			      + "      ,CONTENT"
			      + "      ,BACKCAUSE"
			      + "      ,RESCONTENT"
			      + "      ,HOLDCAUSE"
			      + "      ,DEPTCONT"
			      + "      ,RTRIM(STATE) AS STATE"
			      + "  FROM TLC_FLAWCONSULT"
			      + " WHERE LCNO = ?"
			      //+ " AND DIVISIONCD IN ('SEUK','SESA','SEBN','SEGR','SEP','SENA') " //2013. 12. 30적용
			
			      //2014. 1. 10 적용
				  + "  AND DIVISIONCD IN ('SEAD','SEAG','SEB','SECZ','SEF','SEG','SEH-P','SEH-S' "
				  + "                     ,'SEI','SELS','SELSK','SEPM','SEPOL','SEROM','SESG','SESK','SRPOL','SSEL') "
			
			      + " AND STATE <> 'S01' "
			      + " ORDER BY HISTNO ASC";
			fLawConsultStmt = srConn.prepareStatement(sql);

			sql   = "SELECT SEQNO"
			      + "      ,CMPYNM"
			      + "      ,CONSPAY"
			      + "      ,CURR"
			      + "      ,WONAMT"
			      + "      ,CONVERT(NVARCHAR(30), PAYDT, 113) AS PAYDT"
			      + "  FROM TLC_OUTCONSULT"
			      + " WHERE LCNO = ?"
			      + "   AND HISTNO = ?"
			      + " ORDER BY SEQNO ASC";
			outConsultStmt = srConn.prepareStatement(sql);

			sql   = "SELECT RTRIM(LCTYPE) AS LCTYPE"
			      + "      ,LCTPNM"
			      + "  FROM TLC_LCTYPE"
			      + " WHERE LCNO = ?"
			      + "   AND HISTNO = ?";
			lcTypeStmt = srConn.prepareStatement(sql);

			sql   = "SELECT REFNO"
			      + "      ,UPTP"
			      + "      ,SEQNO"
			      + "      ,PATH"
			      + "      ,FILENM"
			      + "      ,FILESIZE"
			      + "  FROM TLC_UPFILE"
			      + " WHERE REFNO = ?";
			upFileStmt = srConn.prepareStatement(sql);
			
			sql = "SELECT ( CASE WHEN ? = 'SEUK' AND ( "
				  + " SUBSTRING(?, 1, 5) IN ('C10CZ','C10N6','C10A7') "
				  + " OR SUBSTRING(?, 1, 7) = 'C10CD01' "
				  + " OR SUBSTRING(?, 1, 8) IN ('C10N8017','C10D0490','C10N0759','C10N0766','C10S9915','C10A7923') "	
				  + " ) THEN 'EHQ' ELSE ? END) CHG_DIVISION ";
			
			chkCompcdStmt = tgConn.prepareStatement(sql);
			
			
			sql   = " BEGIN"
				  + " DECLARE @USER_ID1 NVARCHAR(150), @USER_ID2 NVARCHAR(150), @USER_ID3 NVARCHAR(150), @USER_ID4 NVARCHAR(150), @COMP_CD NVARCHAR(10)"
				  +	" SELECT TOP 1 @USER_ID1 = USER_ID  FROM TB_COM_USER WHERE LEN(?) > 0 AND ? IN (USER_ID, EMP_NO, SINGLE_ID)"
				  +	" SELECT TOP 1 @USER_ID2 = USER_ID  FROM TB_COM_USER WHERE LEN(?) > 0 AND ? IN (USER_ID, EMP_NO, SINGLE_ID)"
				  +	" SELECT TOP 1 @USER_ID3 = USER_ID  FROM TB_COM_USER WHERE LEN(?) > 0 AND ? IN (USER_ID, EMP_NO, SINGLE_ID)"
				  +	" SELECT TOP 1 @COMP_CD = COMP_CD, @USER_ID4 = USER_ID  FROM TB_COM_USER WHERE LEN(?) > 0 AND ? IN (USER_ID, EMP_NO, SINGLE_ID)" 
				  + " INSERT INTO TN_LAS_CONSULT("
				  + "    CNSLT_NO"
				  + "   ,HSTRY_NO"
				  + "   ,CNSLT_POS"
				  + "   ,CNSLT_SRT"
				  + "   ,DMSTFRGN_GBN"
				  + "   ,TITLE"
				  + "   ,CONT"
				  + "   ,EXTNL_PRGRS_STATUS"
				  + "   ,INTNL_PRGRS_STATUS"
				  + "   ,MAIN_MATR_CONT"
				  + "   ,REJCT_CAUSE"
				  + "   ,HOLD_CAUSE"
				  + "   ,EXTNL_CNSLTYN"
				  + "   ,GRPMGR_ID"
				  + "   ,GRPMGR_NM"
				  + "   ,ORDR_CONT"
				  + "   ,GRPMGR_RE_YN"
				  + "   ,APPRVLDAY"
				  + "   ,CNSDMAN_ID"
				  + "   ,CNSDMAN_NM"
				  + "   ,CNSD_DT"
				  + "   ,CNSD_OPNN"
				  + "   ,APBTMAN_ID"
				  + "   ,APBTMAN_NM"
				  + "   ,APBT_OPNN"
				  + "   ,RE_DT"
				  + "   ,REG_OPERDIV"
				  + "   ,COMP_CD"
				  + "   ,REG_DT"
				  + "   ,REG_ID"
				  + "   ,REG_NM"
				  + "   ,REG_TELNO"
				  + "   ,REG_DEPT"
				  + "   ,REG_DEPT_NM)"
			      + " VALUES("
				  + "    ?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,ISNULL(@USER_ID1, ?)"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,ISNULL(@USER_ID2, ?)"
				  + "   ,?"
				  + "   ,CASE WHEN ? = '' THEN NULL ELSE CONVERT(DATETIME, ?, 113) END"
				  + "   ,?"
				  + "   ,ISNULL(@USER_ID3, ?)"
				  + "   ,?"
				  + "   ,?"
				  + "   ,CASE WHEN ? = '' THEN NULL ELSE CONVERT(DATETIME, ?, 113) END"
				  + "   ,?"
				  + "   ,ISNULL(@COMP_CD, ?)"
				  + "   ,CONVERT(DATETIME, ?, 113)"
				  + "   ,ISNULL(@USER_ID4, ?)"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?)"
				  + " END" ;
			insConsultStmt = tgConn.prepareStatement(sql);

			sql   = "DELETE FROM TN_LAS_CONSULT WHERE CNSLT_NO = ?";
			delConsultStmt = tgConn.prepareStatement(sql);

			sql   = "INSERT INTO TN_LAS_CONSULT_EXTNLCOMP("
				  + "    CNSLT_NO"
				  + "   ,HSTRY_NO"
				  + "   ,SEQNO"
				  + "   ,COMP_NM"
				  + "   ,CNSLT_AMT"
				  + "   ,CRRNCY_UNIT"
				  + "   ,KRW_AMT"
				  + "   ,PAYDAY)"
			      + " VALUES("
				  + "    ?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?)";
			insConsultExtnlcompStmt = tgConn.prepareStatement(sql);

			sql   = "DELETE FROM TN_LAS_CONSULT_EXTNLCOMP WHERE CNSLT_NO = ?";
			delConsultExtnlcompStmt = tgConn.prepareStatement(sql);

			sql   = "INSERT INTO TN_LAS_CONSULT_TYPE("
				  + "    CNSLT_NO"
				  + "   ,HSTRY_NO"
				  + "   ,CNSLT_TYPE"
				  + "   ,CNSLT_TYPENM)"
			      + " VALUES("
				  + "    ?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?)";
			insConsultTypeStmt = tgConn.prepareStatement(sql);

			sql   = "DELETE FROM TN_LAS_CONSULT_TYPE WHERE CNSLT_NO = ?";
			delConsultTypeStmt = tgConn.prepareStatement(sql);

			sql   = "INSERT INTO TN_LAS_CONSULT_RESPMAN("
				  + "    CNSLT_NO"
				  + "   ,COMP_CD"
				  + "   ,RESPMAN_ID"
				  + "   ,RESPMAN_NM"
				  + "   ,REG_DT"
				  + "   ,REG_ID"
				  + "   ,REG_NM)"
			      + " VALUES("
				  + "    ?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,CONVERT(DATETIME, ?, 112)"
				  + "   ,ISNULL((SELECT TOP 1 USER_ID FROM TB_COM_USER WHERE ? IN (EMP_NO, SINGLE_ID)),?)"
				  + "   ,?)";
			insConsultRespmanStmt = tgConn.prepareStatement(sql);

			sql   = "DELETE FROM TN_LAS_CONSULT_RESPMAN WHERE CNSLT_NO = ?";
			delConsultRespmanStmt = tgConn.prepareStatement(sql);

			sql   = "INSERT INTO TN_COM_ATTACH("
				  + "    FILE_ID"
				  + "   ,FILE_PATH"
				  + "   ,ORG_FILE_NM"
				  + "   ,FILE_SRT"
				  + "   ,FILE_SZ"
				  + "   ,SYS_CD"
				  + "   ,FILE_BIGCLSFCN"
				  + "   ,FILE_MIDCLSFCN"
				  + "   ,FILE_SMLCLSFCN"
				  + "   ,REF_KEY"
				  + "   ,REG_DT"
				  + "   ,REG_ID)"
			      + " VALUES("
				  + "    ?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,CONVERT(DATETIME, ?, 113)"
				  + "   ,ISNULL((SELECT TOP 1 USER_ID FROM TB_COM_USER WHERE ? IN (EMP_NO, SINGLE_ID)),?) )";
			insComAttach = tgConn.prepareStatement(sql);

			sql   = "DELETE FROM TN_COM_ATTACH WHERE REF_KEY LIKE ? + '%' AND FILE_BIGCLSFCN = 'F003'";
			delComAttach = tgConn.prepareStatement(sql);


			// 자문 이관
			while(receptRs.next()) {
				++rsRow;

				lcNo			= strNull(receptRs.getString("LCNO"), "");
				respHeadJuno	= strNull(receptRs.getString("RESPHEADJUNO"), "");
				respHead		= strNull(receptRs.getString("RESPHEAD"), "");
				respMan1Userid	= strNull(receptRs.getString("RESPMAN1USERID"), "");
				respMan1		= strNull(receptRs.getString("RESPMAN1"), "");
				respMan2Userid	= strNull(receptRs.getString("RESPMAN2USERID"), "");
				respMan2		= strNull(receptRs.getString("RESPMAN2"), "");
				respMan3Userid	= strNull(receptRs.getString("RESPMAN3USERID"), "");
				respMan3		= strNull(receptRs.getString("RESPMAN3"), "");
				orderResman		= strNull(receptRs.getString("ORDERRESMAN"), "");
				respChk			= strNull(receptRs.getString("RESPCHK"), "");
				appDt			= strNull(receptRs.getString("APPDT"), "");
				


				// TN_COM_ATTACH 삭제
				bi = 0;
				delComAttach.setString	(++bi, lcNo);
				delComAttach.executeUpdate();

				// TN_LAS_CONSULT_RESPMAN 삭제
				bi = 0;
				delConsultRespmanStmt.setString	(++bi, lcNo);
				delConsultRespmanStmt.executeUpdate();

				// TN_LAS_CONSULT_EXTNLCOMP 삭제
				bi = 0;
				delConsultExtnlcompStmt.setString	(++bi, lcNo);
				delConsultExtnlcompStmt.executeUpdate();

				// TN_LAS_CONSULT_TYPE 삭제
				bi = 0;
				delConsultTypeStmt.setString	(++bi, lcNo);
				delConsultTypeStmt.executeUpdate();

				// TN_LAS_CONSULT 삭제
				bi = 0;
				delConsultStmt.setString	(++bi, lcNo);
				delConsultStmt.executeUpdate();


				bi = 0;
				fLawConsultStmt.setString(++bi, lcNo);

				fLawConsultRs = fLawConsultStmt.executeQuery();

				cont = "";
				regDt = "";
				regId = "";
				regNm = "";
				regTelNo = "";
				regDept = "";
				regDeptNm = "";
				divisionCd = "";

				while(fLawConsultRs.next()) {
					histNo = fLawConsultRs.getInt("HISTNO");
					state = fLawConsultRs.getString("STATE");
					
					mainMatrCont = fLawConsultRs.getString("RESCONTENT");
					rejctCause = fLawConsultRs.getString("BACKCAUSE");
					holdCause = fLawConsultRs.getString("HOLDCAUSE");
					
					cnsdmanId = "";
					cnsdmanNm = "";
					cnsdDt = "";
					cnsdOpnn = "";
					apbtmanId = "";
					apbtmanNm = "";
					apbtOpnn = "";
					reDt = "";
					
					if("S02".equals(state) || "S04".equals(state) || "S05".equals(state)) {		// 의뢰(S02), 재의뢰(S04), 보류(S05)
						extnlPrgrsStatus = state;
						intnlPrgrsStatus = state;

						cont = fLawConsultRs.getString("CONTENT");
						regDt = fLawConsultRs.getString("REGDT");
						regId = fLawConsultRs.getString("REGMANJUNO");
						regNm = fLawConsultRs.getString("REGMANNM");
						regTelNo = fLawConsultRs.getString("REGTELNO");
						regDept = fLawConsultRs.getString("REGDEPT");
						regDeptNm = fLawConsultRs.getString("REGDEPTNM");
						divisionCd = fLawConsultRs.getString("DIVISIONCD");
						
						reqDt = regDt;
						reqManJuno = regId;
					} else if("S01".equals(state) || "S10".equals(state)) {		// 의뢰 임시저장
						extnlPrgrsStatus = state;
						intnlPrgrsStatus = "S02";

						cont = fLawConsultRs.getString("CONTENT");
						regDt = fLawConsultRs.getString("REGDT");
						regId = fLawConsultRs.getString("REGMANJUNO");
						regNm = fLawConsultRs.getString("REGMANNM");
						regTelNo = fLawConsultRs.getString("REGTELNO");
						regDept = fLawConsultRs.getString("REGDEPT");
						regDeptNm = fLawConsultRs.getString("REGDEPTNM");
						divisionCd = fLawConsultRs.getString("DIVISIONCD");
						
						reqDt = regDt;
						reqManJuno = regId;
					} else if("S07".equals(state)) {			// 반려
						extnlPrgrsStatus = state;
						intnlPrgrsStatus = state;

						cnsdOpnn = fLawConsultRs.getString("CONTENT");
						regDt = fLawConsultRs.getString("REGDT");
						cnsdmanId = fLawConsultRs.getString("REGMANJUNO");
						cnsdmanNm = fLawConsultRs.getString("REGMANNM");
						cnsdDt = fLawConsultRs.getString("REGDT");
						
						apbtmanId = respHeadJuno;
						apbtmanNm = respHead;
						apbtOpnn = fLawConsultRs.getString("DEPTCONT");
						reDt = fLawConsultRs.getString("REGDT");
						
						reqDt = cnsdDt;
						reqManJuno = cnsdmanId;
					} else if("S11".equals(state)) {			// 법무그룹장 회신반려
						extnlPrgrsStatus = state;
						intnlPrgrsStatus = state;

						cnsdOpnn = fLawConsultRs.getString("CONTENT");
						regDt = fLawConsultRs.getString("REGDT");
						cnsdmanId = fLawConsultRs.getString("REGMANJUNO");
						cnsdmanNm = fLawConsultRs.getString("REGMANNM");
						cnsdDt = fLawConsultRs.getString("REGDT");
						
						apbtmanId = respHeadJuno;
						apbtmanNm = respHead;
						apbtOpnn = fLawConsultRs.getString("DEPTCONT");
						reDt = "";
						
						reqDt = cnsdDt;
						reqManJuno = cnsdmanId;
					} else if("S09".equals(state) || "S08".equals(state)) {			// 검토의견 임시저장(S09), 검토의견 미결(S08)
						extnlPrgrsStatus = "S02";
						intnlPrgrsStatus = state;

						cnsdOpnn = fLawConsultRs.getString("CONTENT");
						regDt = fLawConsultRs.getString("REGDT");
						cnsdmanId = fLawConsultRs.getString("REGMANJUNO");
						cnsdmanNm = fLawConsultRs.getString("REGMANNM");
						cnsdDt = fLawConsultRs.getString("REGDT");
						
						apbtmanId = "";
						apbtmanNm = "";
						apbtOpnn = "";
						reDt = "";
						
						reqDt = cnsdDt;
						reqManJuno = cnsdmanId;
					} else if("S03".equals(state)) {			// 회신
						extnlPrgrsStatus = state;
						intnlPrgrsStatus = state;

						cnsdOpnn = fLawConsultRs.getString("CONTENT");
						regDt = fLawConsultRs.getString("REGDT");
						cnsdmanId = fLawConsultRs.getString("REGMANJUNO");
						cnsdmanNm = fLawConsultRs.getString("REGMANNM");
						cnsdDt = fLawConsultRs.getString("REGDT");
						
						apbtmanId = respHeadJuno;
						apbtmanNm = respHead;
						apbtOpnn = fLawConsultRs.getString("DEPTCONT");
						reDt = fLawConsultRs.getString("REGDT");
						
						reqDt = cnsdDt;
						reqManJuno = cnsdmanId;
					} else {
						System.out.println("new Type [" + state + "]");
						tgConn.rollback();
						return;
					}
					
					if(regId!=null && regId.equals("")){
						regId = fLawConsultRs.getString("REGMANJUNO");
						regNm = fLawConsultRs.getString("REGMANNM");
						divisionCd = fLawConsultRs.getString("DIVISIONCD");
					}
					
					bi = 0;
					outConsultStmt.setString(++bi, lcNo);
					outConsultStmt.setInt	(++bi, histNo);
	
					outConsultRs = outConsultStmt.executeQuery();
					
					if(outConsultRs.next()) {
						extnlCnsltyn = "Y";
					} else {
						extnlCnsltyn = "N";
					}
					
					
					bi = 0;
					chkCompcdStmt.setString(++bi, divisionCd);
					chkCompcdStmt.setString(++bi, regDept);
					chkCompcdStmt.setString(++bi, regDept);
					chkCompcdStmt.setString(++bi, regDept);
					chkCompcdStmt.setString(++bi, divisionCd);
					chkCompRs = chkCompcdStmt.executeQuery();
					if(chkCompRs.next()) {
						chg_divisioncd = chkCompRs.getString(1);
						divisionCd = chg_divisioncd;
					}
					
					
					// TN_LAS_CONSULT 등록
					bi = 0;
					insConsultStmt.setString	(++bi, respHeadJuno);							// GRPMGR_ID
					insConsultStmt.setString	(++bi, respHeadJuno);							// GRPMGR_ID
					insConsultStmt.setString	(++bi, cnsdmanId);								// CNSDMAN_ID
					insConsultStmt.setString	(++bi, cnsdmanId);								// CNSDMAN_ID
					insConsultStmt.setString	(++bi, apbtmanId);								// APBTMAN_ID
					insConsultStmt.setString	(++bi, apbtmanId);								// APBTMAN_ID
					insConsultStmt.setString	(++bi, regId);									// REG_ID
					insConsultStmt.setString	(++bi, regId);									// REG_ID
					
					insConsultStmt.setString	(++bi, lcNo);									// CNSLT_NO
					insConsultStmt.setInt		(++bi, histNo);									// HSTRY_NO
					insConsultStmt.setInt		(++bi, fLawConsultRs.getInt("WRPOS"));			// CNSLT_POS
					insConsultStmt.setInt		(++bi, fLawConsultRs.getInt("WRDEPTH"));		// CNSLT_SRT
					insConsultStmt.setString	(++bi, "H");									// DMSTFRGN_GBN
					insConsultStmt.setString	(++bi, fLawConsultRs.getString("LCTITLE"));		// TITLE
					insConsultStmt.setString	(++bi, cont);									// CONT
					insConsultStmt.setString	(++bi, extnlPrgrsStatus);						// EXTNL_PRGRS_STATUS
					insConsultStmt.setString	(++bi, intnlPrgrsStatus);						// INTNL_PRGRS_STATUS
					insConsultStmt.setString	(++bi, mainMatrCont);							// MAIN_MATR_CONT
					insConsultStmt.setString	(++bi, rejctCause);								// REJCT_CAUSE
					insConsultStmt.setString	(++bi, holdCause);								// HOLD_CAUSE
					insConsultStmt.setString	(++bi, extnlCnsltyn);							// EXTNL_CNSLTYN
					insConsultStmt.setString	(++bi, respHeadJuno);							// GRPMGR_ID
					insConsultStmt.setString	(++bi, respHead);								// GRPMGR_NM
					insConsultStmt.setString	(++bi, orderResman);							// ORDR_CONT
					insConsultStmt.setString	(++bi, respChk);								// GRPMGR_RE_YN
					insConsultStmt.setString	(++bi, appDt);									// APPRVLDAY
					insConsultStmt.setString	(++bi, cnsdmanId);								// CNSDMAN_ID
					insConsultStmt.setString	(++bi, cnsdmanNm);								// CNSDMAN_NM
					insConsultStmt.setString	(++bi, cnsdDt);									// CNSD_DT
					insConsultStmt.setString	(++bi, cnsdDt);									// CNSD_DT
					insConsultStmt.setString	(++bi, cnsdOpnn);								// CNSD_OPNN
					insConsultStmt.setString	(++bi, apbtmanId);								// APBTMAN_ID
					insConsultStmt.setString	(++bi, apbtmanNm);								// APBTMAN_NM
					insConsultStmt.setString	(++bi, apbtOpnn);								// APBT_OPNN
					insConsultStmt.setString	(++bi, reDt);									// RE_DT
					insConsultStmt.setString	(++bi, reDt);									// RE_DT
					insConsultStmt.setString	(++bi, divisionCd);								// REG_OPERDIV
					insConsultStmt.setString	(++bi, divisionCd);								// COMP_CD
					insConsultStmt.setString	(++bi, regDt);									// REG_DT
					insConsultStmt.setString	(++bi, regId);									// REG_ID
					insConsultStmt.setString	(++bi, regNm);									// REG_NM
					insConsultStmt.setString	(++bi, regTelNo);								// REG_TELNO
					insConsultStmt.setString	(++bi, regDept);								// REG_DEPT
					insConsultStmt.setString	(++bi, regDeptNm);								// REG_DEPT_NM
	
					sqlRet = insConsultStmt.executeUpdate();
					
					if(sqlRet == 1) {
	
						// TN_LAS_CONSULT_TYPE 등록
						bi = 0;
						lcTypeStmt.setString(++bi, lcNo);
						lcTypeStmt.setInt	(++bi, histNo);
		
						lcTypeRs = lcTypeStmt.executeQuery();
						
						while(lcTypeRs.next()) {
							bi = 0;
							insConsultTypeStmt.setString	(++bi, lcNo);								// CNSLT_NO
							insConsultTypeStmt.setInt		(++bi, histNo);								// HSTRY_NO
							insConsultTypeStmt.setString	(++bi, lcTypeRs.getString("LCTYPE"));		// COMP_NM
							insConsultTypeStmt.setString	(++bi, lcTypeRs.getString("LCTPNM"));		// PAYDAY
			
							sqlRet = insConsultTypeStmt.executeUpdate();
							
							if(sqlRet != 1) {
								System.out.print(rsRow);
								System.out.print("\t" + lcNo);
								System.out.print("\t" + histNo);
			
								System.out.print("\t==> TN_LAS_CONSULT_TYPE 등록 실패");
								System.out.println("");
							}
						}
	
	
						// TN_LAS_CONSULT_EXTNLCOMP 등록
						if("Y".equals(extnlCnsltyn)) {
							do {
								bi = 0;
								insConsultExtnlcompStmt.setString	(++bi, lcNo);									// CNSLT_NO
								insConsultExtnlcompStmt.setInt		(++bi, histNo);									// HSTRY_NO
								insConsultExtnlcompStmt.setInt		(++bi, outConsultRs.getInt("SEQNO"));			// SEQNO
								insConsultExtnlcompStmt.setString	(++bi, outConsultRs.getString("CMPYNM"));		// COMP_NM
								insConsultExtnlcompStmt.setInt		(++bi, outConsultRs.getInt("CONSPAY"));			// CNSLT_AMT
								insConsultExtnlcompStmt.setString	(++bi, outConsultRs.getString("CURR"));			// CRRNCY_UNIT
								insConsultExtnlcompStmt.setInt		(++bi, outConsultRs.getInt("WONAMT"));			// KRW_AMT
								insConsultExtnlcompStmt.setString	(++bi, outConsultRs.getString("PAYDT"));		// PAYDAY
				
								sqlRet = insConsultExtnlcompStmt.executeUpdate();
								
								if(sqlRet != 1) {
									System.out.print(rsRow);
									System.out.print("\t" + lcNo);
									System.out.print("\t" + histNo);
				
									System.out.print("\t==> TN_LAS_CONSULT_EXTNLCOMP 등록 실패");
									System.out.println("");
								}
							}while(outConsultRs.next());
						}
	
	
						// TN_COM_ATTACH 등록
						bi = 0;
						upFileStmt.setString(++bi, lcNo + "@" + histNo);
		
						upFileRs = upFileStmt.executeQuery();
						
						while(upFileRs.next()) {
							fileId = strNull(upFileRs.getString("REFNO"), "") + "_" + strNull(upFileRs.getString("UPTP"), "") + "_" + upFileRs.getInt("SEQNO");
							fileBigclsfcn = "F003";
							fileMidclsfcn = "F00301";
							fileSmlclsfcn = "";
							refKey = lcNo + "@" + histNo;
							
							// TN_COM_ATTACH 등록
							bi = 0;
							insComAttach.setString (++bi, fileId);										// FILE_ID
							insComAttach.setString (++bi, strNull(upFileRs.getString("PATH"), ""));		// FILE_PATH
							insComAttach.setString (++bi, strNull(upFileRs.getString("FILENM"), ""));	// ORG_FILE_NM
							insComAttach.setInt    (++bi, upFileRs.getInt("SEQNO"));					// FILE_SRT
							insComAttach.setInt    (++bi, upFileRs.getInt("FILESIZE"));					// FILE_SZ
							insComAttach.setString (++bi, "LAS");										// SYS_CD
							insComAttach.setString (++bi, fileBigclsfcn);								// FILE_BIGCLSFCN
							insComAttach.setString (++bi, fileMidclsfcn);								// FILE_MIDCLSFCN
							insComAttach.setString (++bi, fileSmlclsfcn);								// FILE_SMLCLSFCN
							insComAttach.setString (++bi, refKey);										// REF_KEY
							
							insComAttach.setString (++bi, reqDt);										// REG_DT
							insComAttach.setString (++bi, reqManJuno);									// REG_ID
							insComAttach.setString (++bi, reqManJuno);									// REG_ID
							
							sqlRet = insComAttach.executeUpdate();
	
							if(sqlRet != 1) {
								System.out.print(rsRow);
								System.out.print("\t" + lcNo);
								System.out.print("\t" + histNo);
			
								System.out.print("\t==> TN_COM_ATTACH 등록 실패");
								System.out.println("");
							}
						}
	
	
						System.out.print(rsRow);
						System.out.print("\t" + lcNo);
						System.out.print("\t" + histNo);
	
						System.out.print("\t==> 등록 성공");
						System.out.println("");
						
					} else {
						System.out.print(rsRow);
						System.out.print("\t" + lcNo);
						System.out.print("\t" + histNo);
	
						System.out.print("\t==> TN_LAS_CONSULT 등록 실패");
						System.out.println("");
					}
				}
				
				if(!"".equals(respMan1Userid)) {
					// TN_LAS_CONSULT_RESPMAN 등록
					bi = 0;
					insConsultRespmanStmt.setString (++bi, lcNo);			// CNSLT_NO
					insConsultRespmanStmt.setString (++bi, divisionCd);		// COMP_CD
					insConsultRespmanStmt.setString (++bi, respMan1Userid);	// RESPMAN_ID
					insConsultRespmanStmt.setString (++bi, respMan1);		// RESPMAN_NM
					insConsultRespmanStmt.setString (++bi, appDt);			// REG_DT
					insConsultRespmanStmt.setString (++bi, respHeadJuno);	// REG_ID
					insConsultRespmanStmt.setString (++bi, respHeadJuno);	// REG_ID
					insConsultRespmanStmt.setString (++bi, respHead);		// REG_NM
					
					sqlRet = insConsultRespmanStmt.executeUpdate();

					if(sqlRet != 1) {
						System.out.print(rsRow);
						System.out.print("\t" + lcNo);
	
						System.out.print("\t==> TN_LAS_CONSULT_RESPMAN 1 등록 실패");
						System.out.println("");
					}
				}

				if(!"".equals(respMan2Userid) && !respMan1Userid.equals(respMan2Userid)) {
					// TN_LAS_CONSULT_RESPMAN 등록
					bi = 0;
					insConsultRespmanStmt.setString (++bi, lcNo);			// CNSLT_NO
					insConsultRespmanStmt.setString (++bi, divisionCd);		// COMP_CD
					insConsultRespmanStmt.setString (++bi, respMan2Userid);	// RESPMAN_ID
					insConsultRespmanStmt.setString (++bi, respMan2);		// RESPMAN_NM
					insConsultRespmanStmt.setString (++bi, appDt);			// REG_DT
					insConsultRespmanStmt.setString (++bi, respHeadJuno);	// REG_ID
					insConsultRespmanStmt.setString (++bi, respHeadJuno);	// REG_ID
					insConsultRespmanStmt.setString (++bi, respHead);		// REG_NM
					
					sqlRet = insConsultRespmanStmt.executeUpdate();

					if(sqlRet != 1) {
						System.out.print(rsRow);
						System.out.print("\t" + lcNo);
	
						System.out.print("\t==> TN_LAS_CONSULT_RESPMAN 2 등록 실패");
						System.out.println("");
					}
				}

				if(!"".equals(respMan3Userid) && !respMan1Userid.equals(respMan3Userid) && !respMan2Userid.equals(respMan3Userid)) {
					// TN_LAS_CONSULT_RESPMAN 등록
					bi = 0;
					insConsultRespmanStmt.setString (++bi, lcNo);			// CNSLT_NO
					insConsultRespmanStmt.setString (++bi, divisionCd);		// COMP_CD
					insConsultRespmanStmt.setString (++bi, respMan3Userid);	// RESPMAN_ID
					insConsultRespmanStmt.setString (++bi, respMan3);		// RESPMAN_NM
					insConsultRespmanStmt.setString (++bi, appDt);			// REG_DT
					insConsultRespmanStmt.setString (++bi, respHeadJuno);	// REG_ID
					insConsultRespmanStmt.setString (++bi, respHeadJuno);	// REG_ID
					insConsultRespmanStmt.setString (++bi, respHead);		// REG_NM
					
					sqlRet = insConsultRespmanStmt.executeUpdate();

					if(sqlRet != 1) {
						System.out.print(rsRow);
						System.out.print("\t" + lcNo);
	
						System.out.print("\t==> TN_LAS_CONSULT_RESPMAN 3 등록 실패");
						System.out.println("");
					}
				}
			}
		
		} catch (SQLException se) {
			System.out.println(se.toString());
			tgConn.rollback();
		} finally {
			if(receptRs != null) receptRs.close();

			if(receptStmt != null) receptStmt.close();

			if(srConn != null) srConn.close();
			if(tgConn != null) {
				tgConn.setAutoCommit(true);
				tgConn.close();
			}
		}
		System.out.println("==end==");
	}

	public String strNull(String argStr, String defaultValue) throws Exception {
		if(argStr == null) {
			return defaultValue;
		} else {
			return argStr;
		}
	}

	public String getShortDate(Date dt) throws Exception {
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
		return simpledateformat.format(dt);
	}

	public static void main(String args[]) throws Exception {

		migConsult migBatch = new migConsult();
	}
}