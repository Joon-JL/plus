import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;

/*
 * 
 * selms 기존 구주 테이블  ==> selms plus 신규 테이블
 * 
 * ==========================
 * selms 기존 구주 테이블
 * =============================
 	TCM_FCONTRACT
	TCM_CNTRTYPE
	TCM_REQHIST
	TCM_UPFILE
	
	TCM_APP
	TCM_APP_LINE

 * ==========================
 * selms plus 신규 테이블
 * ==========================
	TN_CLM_CUSTOMER
	TN_CLM_CONTRACT_MASTER
	TN_CLM_CONT_CNSDREQ
	TN_CLM_CONTRACT_CNSD
	TN_CLM_CONT_CNSDREQ_RESPMAN
	TN_COM_ATTACH
	TN_CLM_CONTRACT_DEPTCNSD
	TN_CLM_CONT_CNSDREQ_HOLD
	
	TB_COM_START_PROCESS_WSVO
	TB_COM_ROUTE_WSVO
	TB_COM_ATTACHMENT_WSVO
	

 * ===============================================
 * 이관완료후 EHQ 해당하는 건이 누락되었을 경우 업데이트 쿼리
 * ===============================================
	UPDATE TN_CLM_CONT_CNSDREQ SET COMP_CD=A.COMP_CD
	FROM TB_COM_USER A, TN_CLM_CONT_CNSDREQ B WHERE A.COMP_CD ='EHQ' AND A.USER_ID = B.REQMAN_ID
	
	UPDATE [TN_CLM_CONTRACT_CNSD] SET COMP_CD=A.COMP_CD FROM TB_COM_USER A, [TN_CLM_CONTRACT_CNSD] B,
	(SELECT USER_ID,A.COMP_CD, cnsdreq_id, REQMAN_ID FROM TB_COM_USER A, TN_CLM_CONT_CNSDREQ B WHERE A.COMP_CD ='EHQ' AND A.USER_ID = B.REQMAN_ID) C
	 WHERE A.COMP_CD ='EHQ' AND A.USER_ID = C.REQMAN_ID AND B.cnsdreq_id=C.cnsdreq_id
	
	UPDATE tn_clm_contract_master SET COMP_CD=A.COMP_CD  FROM TB_COM_USER A, [TN_CLM_CONTRACT_CNSD] B,
	(SELECT USER_ID,A.COMP_CD, cnsdreq_id, REQMAN_ID FROM TB_COM_USER A, TN_CLM_CONT_CNSDREQ B WHERE A.COMP_CD ='EHQ' AND A.USER_ID = B.REQMAN_ID) C,
	tn_clm_contract_master D
	WHERE A.COMP_CD ='EHQ' AND A.USER_ID = C.REQMAN_ID AND B.cnsdreq_id=C.cnsdreq_id AND B.CNTRT_ID=D.CNTRT_ID
	 
	UPDATE TN_CLM_CONTRACT_DEPTCNSD SET COMP_CD=A.COMP_CD
	FROM TN_CLM_CONTRACT_DEPTCNSD A, tn_clm_contract_master B
	WHERE A.CNTRT_ID=B.CNTRT_ID AND B.COMP_CD ='EHQ'
	
	UPDATE TN_CLM_CUSTOMER SET COMP_CD=B.COMP_CD
	FROM TN_CLM_CUSTOMER A, tn_clm_contract_master B
	WHERE A.CUSTOMER_CD=B.CNTRT_OPPNT_CD AND B.COMP_CD ='EHQ'  
	
 * ===============================================
 * 이관전 삭제 쿼리 : 예('SEUK','EHQ'일 경우)
 * ===============================================	
	다른 지법인을 이관시는 아래의 쿼리를 수정해서 사용할 것.
		
			PRCS_DEPTH='C02506' 상태값으로 들어간 데이타없는지 확인후 없다면 아래의 삭제쿼리를 수행한다.
			 SELECT TA.* FROM TN_CLM_CONTRACT_MASTER TA
				 WHERE PRCS_DEPTH <> 'C02506'
				 AND EXISTS (
				  SELECT 'E'  FROM TN_CLM_CUSTOMER A, TN_CLM_CONTRACT_MASTER B
				 WHERE A.CUSTOMER_CD LIKE 'MG%'
				 AND B.CNTRT_OPPNT_CD= A.CUSTOMER_CD 
				 AND B.COMP_CD IN ('SEUK','EHQ')
				 AND B.PRCS_DEPTH='C02506'
				 AND TA.CNTRT_OPPNT_CD= A.CUSTOMER_CD 
			 )
			
			--앞서 넣어준 계약상대방을 삭제처리
			DELETE TN_CLM_CUSTOMER WHERE  CUSTOMER_CD IN (	 
			 SELECT DISTINCT A.CUSTOMER_CD  FROM TN_CLM_CUSTOMER A, TN_CLM_CONTRACT_MASTER B
				 WHERE A.CUSTOMER_CD LIKE 'MG%'
				 AND B.CNTRT_OPPNT_CD= A.CUSTOMER_CD 
				 AND B.COMP_CD IN ('SEUK','EHQ')
				 AND B.PRCS_DEPTH='C02506'
			)
			
			DELETE TN_CLM_CONT_CNSDREQ WHERE  CNSDREQ_ID IN (	 
			SELECT B.CNSDREQ_ID 
				FROM  TN_CLM_CONTRACT_CNSD B,
					(SELECT  * FROM  
					TN_CLM_CONTRACT_MASTER A
					WHERE A.COMP_CD IN ('SEUK','EHQ')
					AND PRCS_DEPTH ='C02506') C
				WHERE  
				B.CNTRT_ID = C.CNTRT_ID
			)
			
			DELETE TN_CLM_CONT_CNSDREQ_RESPMAN WHERE  CNSDREQ_ID IN (	 
			SELECT B.CNSDREQ_ID 
				FROM  TN_CLM_CONTRACT_CNSD B,
					(SELECT  * FROM  
					TN_CLM_CONTRACT_MASTER A
					WHERE A.COMP_CD IN ('SEUK','EHQ')
					AND PRCS_DEPTH ='C02506') C
				WHERE  
				B.CNTRT_ID = C.CNTRT_ID
			)
			
			DELETE TN_CLM_CONTRACT_DEPTCNSD WHERE  CNSDREQ_ID IN (	 
			SELECT B.CNSDREQ_ID 
				FROM  TN_CLM_CONTRACT_CNSD B,
					(SELECT  * FROM  
					TN_CLM_CONTRACT_MASTER A
					WHERE A.COMP_CD IN ('SEUK','EHQ')
					AND PRCS_DEPTH ='C02506') C
				WHERE  
				B.CNTRT_ID = C.CNTRT_ID
			)
			
			DELETE TN_CLM_CONT_CNSDREQ_HOLD WHERE  CNSDREQ_ID IN (	 
			SELECT B.CNSDREQ_ID 
				FROM  TN_CLM_CONTRACT_CNSD B,
					(SELECT  * FROM  
					TN_CLM_CONTRACT_MASTER A
					WHERE A.COMP_CD IN ('SEUK','EHQ')
					AND PRCS_DEPTH ='C02506') C
				WHERE  
				B.CNTRT_ID = C.CNTRT_ID
			)
			
			DELETE TB_COM_ATTACHMENT_WSVO WHERE MIS_ID IN (    
				SELECT MIS_ID FROM TB_COM_START_PROCESS_WSVO WHERE  REF_KEY IN (	 
				SELECT B.CNSDREQ_ID 
					FROM  TN_CLM_CONTRACT_CNSD B,
						(SELECT  * FROM  
						TN_CLM_CONTRACT_MASTER A
						WHERE A.COMP_CD IN ('SEUK','EHQ')
						AND PRCS_DEPTH ='C02506') C
					WHERE  
					B.CNTRT_ID = C.CNTRT_ID
				)
			)
			    
			DELETE TB_COM_ROUTE_WSVO WHERE MIS_ID IN (    
				SELECT MIS_ID FROM TB_COM_START_PROCESS_WSVO WHERE  REF_KEY IN (	 
				SELECT B.CNSDREQ_ID 
					FROM  TN_CLM_CONTRACT_CNSD B,
						(SELECT  * FROM  
						TN_CLM_CONTRACT_MASTER A
						WHERE A.COMP_CD IN ('SEUK','EHQ')
						AND PRCS_DEPTH ='C02506') C
					WHERE  
					B.CNTRT_ID = C.CNTRT_ID
				)
			)
			
			DELETE TB_COM_START_PROCESS_WSVO WHERE MIS_ID IN (    
				SELECT MIS_ID FROM TB_COM_START_PROCESS_WSVO WHERE  REF_KEY IN (	 
				SELECT B.CNSDREQ_ID 
					FROM  TN_CLM_CONTRACT_CNSD B,
						(SELECT  * FROM  
						TN_CLM_CONTRACT_MASTER A
						WHERE A.COMP_CD IN ('SEUK','EHQ')
						AND PRCS_DEPTH ='C02506') C
					WHERE  
					B.CNTRT_ID = C.CNTRT_ID
				)
			)
			
			DELETE TN_COM_ATTACH WHERE FILE_ID IN (  
			 SELECT FILE_ID FROM TN_COM_ATTACH TA,
			   (SELECT B.CNSDREQ_ID, B.CNTRT_ID 
					FROM  TN_CLM_CONTRACT_CNSD B,
						(SELECT  * FROM  
						TN_CLM_CONTRACT_MASTER A
						WHERE PRCS_DEPTH ='C02506'  AND A.COMP_CD IN ('SEUK','EHQ')) C
					WHERE  
					B.CNTRT_ID = C.CNTRT_ID) TB
			 WHERE TA.REF_KEY LIKE  TB.CNSDREQ_ID + '%'
			   OR TA.REF_KEY LIKE  TB.CNTRT_ID + '%'
			   )
			
			DELETE TN_CLM_CONTRACT_CNSD WHERE  CNSDREQ_ID IN (	 
			SELECT B.CNSDREQ_ID 
				FROM  TN_CLM_CONTRACT_CNSD B,
					(SELECT  * FROM  
					TN_CLM_CONTRACT_MASTER A
					WHERE  PRCS_DEPTH ='C02506'  AND A.COMP_CD IN ('SEUK','EHQ') ) C
				WHERE  
				B.CNTRT_ID = C.CNTRT_ID
			)
			
			DELETE
			TN_CLM_CONTRACT_MASTER  
			WHERE  PRCS_DEPTH ='C02506'
			AND COMP_CD IN ('SEUK','EHQ')
			
			
			
			
 * 
 * 
 * 설명 :
 * 		1. arg 값이  0 일 경우 : 구시스템 데이타를 신시스템으로 전체 이관
 * 				   1 일 경우 : 구시스템 데이타중 체결(S06)건만을 신시스템으로 DELETE INSERT 이관
 *  	
 * 	
 * 
 * 
 * 
 * 	1. 의뢰인이 의뢰
	2. 의뢰인이 재의뢰할 경우
	
		--> 우리쪽으로 의뢰 1개만 기록
	
	1. 의뢰인이 의뢰
	2. 법무팀 회신
	3. 의뢰인이 재의뢰하고
	4. 의뢰인이 재의뢰하고
	
		--> 우리쪽으로 의뢰 3개 생성
		   회신은 1개는 정상회신, 1개는 보류
	
	
	1. 의뢰인이 의뢰
	2. 법무팀 회신
	3. 의뢰인 재의뢰
	4. 법무팀 재회신
	5. 법무팀 재회신
	
		--> 의뢰 3개 생성 (1, 3, 5)
		    회신 3개 생성 (2, 4, 5)	
		    
		    
    이관 이력 
  2013. 12. 17
     - EHQ, SEUK 전체 데이타 이관 완료
     - 주의 : 이관시 계약ID,의뢰ID를 만들때 CUSTOMER_CD를 사용했는데, 길이때문에 로직을 변경함. 
     		따라서 EHQ, SEUK를 제외한 이후에 이관하는 지법인에 대해서는 먼저 삭제후 이관프로그램을 수행할것.
     		   
 * */
public class migSeLms {
	
	public migSeLms(String migType) throws Exception {
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sourceUrl = "jdbc:sqlserver://106.10.1.131:1433;databasename=selmsplus_old;user=selmsplus_dev;password=elmway40!";
		//String targetUrl = "jdbc:sqlserver://106.10.1.131:1433;databasename=selmsplus;user=selmsplus_dev;password=elmway40!"; //구주개발
		String targetUrl = "jdbc:sqlserver://182.198.117.31:1433;databasename=selmsplus_20241121;user=selmsplus_usr;password=qjqan13#"; //구주운영
		
		// Source DB
		Connection srConn = null;
		
		PreparedStatement contStmt = null;			// TCM_FCONTRACT
		PreparedStatement contTypeStmt = null;		// TCM_CNTRTYPE
		PreparedStatement reqHistStmt = null;		// TCM_REQHIST
		PreparedStatement contFileStmt = null;		// TCM_UPFILE
		PreparedStatement appStmt = null;			// TCM_APP
		PreparedStatement appLineStmt = null;		// TCM_APP_LINE
		
		ResultSet contRs = null;
		ResultSet contTypeRs = null;
		ResultSet reqHistRs = null;
		ResultSet contFileRs = null;
		ResultSet appRs = null;
		ResultSet appLineRs = null;
		
		// Target DB
		Connection tgConn = null;
		
		PreparedStatement chkCompcdStmt = null;				// COMP_CD 체크
		PreparedStatement existCompStmt = null;				// TN_CLM_CUSTOMER
		PreparedStatement getCustomerCdStmt = null;			// TN_CLM_CUSTOMER
		PreparedStatement insCustomerStmt = null;			// TN_CLM_CUSTOMER
		PreparedStatement uptCustomerStmt = null;			// TN_CLM_CUSTOMER
		PreparedStatement insContractMasterStmt = null;		// TN_CLM_CONTRACT_MASTER
		PreparedStatement delContractMasterStmt = null;		// TN_CLM_CONTRACT_MASTER
		PreparedStatement getCntrtNoStmt = null;			// TN_CLM_CONTRACT_MASTER
		PreparedStatement insContCnsdReqStmt = null;		// TN_CLM_CONT_CNSDREQ
		PreparedStatement uptContCnsdReqStmt = null;		// TN_CLM_CONT_CNSDREQ
		PreparedStatement delContCnsdReqStmt = null;		// TN_CLM_CONT_CNSDREQ
		PreparedStatement insContractCnsdStmt = null;		// TN_CLM_CONTRACT_CNSD
		PreparedStatement uptContractCnsdStmt = null;		// TN_CLM_CONTRACT_CNSD
		PreparedStatement delContractCnsdStmt = null;		// TN_CLM_CONTRACT_CNSD
		PreparedStatement insCnsdReqRespManStmt = null;		// TN_CLM_CONT_CNSDREQ_RESPMAN
		PreparedStatement delCnsdReqRespManStmt = null;		// TN_CLM_CONT_CNSDREQ_RESPMAN
		PreparedStatement insComAttach = null;				// TN_COM_ATTACH
		PreparedStatement delComAttach = null;				// TN_COM_ATTACH
		PreparedStatement insContractDeptCnsdStmt = null;	// TN_CLM_CONTRACT_DEPTCNSD
		PreparedStatement uptContractDeptCnsdStmt = null;	// TN_CLM_CONTRACT_DEPTCNSD
		PreparedStatement delContractDeptCnsdStmt = null;	// TN_CLM_CONTRACT_DEPTCNSD
		PreparedStatement extContractDeptCnsdStmt = null;	// TN_CLM_CONTRACT_DEPTCNSD
		PreparedStatement insContCnsdReqHoldStmt = null;	// TN_CLM_CONT_CNSDREQ_HOLD
		PreparedStatement delContCnsdReqHoldStmt = null;	// TN_CLM_CONT_CNSDREQ_HOLD
		PreparedStatement extContCnsdReqHoldStmt = null;	// TN_CLM_CONT_CNSDREQ_HOLD
		PreparedStatement insStartProcessWsvoStmt = null;	// TB_COM_START_PROCESS_WSVO
		PreparedStatement delStartProcessWsvoStmt = null;	// TB_COM_START_PROCESS_WSVO
		PreparedStatement insRouteWsvoStmt = null;			// TB_COM_ROUTE_WSVO
		PreparedStatement delRouteWsvoStmt = null;			// TB_COM_ROUTE_WSVO
		PreparedStatement insAttachmentWsvoStmt = null;		// TB_COM_ATTACHMENT_WSVO
		PreparedStatement delAttachmentWsvoStmt = null;		// TB_COM_ATTACHMENT_WSVO
		
		ResultSet chkCompRs = null;
		ResultSet existCompRs = null;
		ResultSet getCustomerCdRs = null;
		ResultSet getCntrtNoRs = null;
		ResultSet extContractDeptCnsdRs = null;
		ResultSet extContCnsdReqHoldRs = null;
		
		String sql = "";
		
		int rsRow = 0;
		int bi = 0;
		int sqlRet = 0;
		
		String customerCd = "";
		int maxCustomerCnt = 0;
		
		String cntrNo = "";
		String cntrtId = "";
		String untPrc = "0";
		String paymentGbn = "";
		String bizClsfcn = "";
		String depthClsfcn = "";
		String cnclsnpurpsBigclsfcn = "";
		String cnclsnpurpsMidclsfcn = "";
		String cntrtCnclsnYn = "";
		String cntrtCnclsnday = "";
		String cntrtNo = "";
		String cntrtStatus = "";
		String depthStatus = "";
		String prgrsStatus = "";
		String cntrtNoYMD = "";
		String reqDt = "";
		String reqManJuno = "";
		String reqManNm = "";
		String reqDept = "";
		String reqDeptNm = "";
		String divisionCd = "";
		String respHeadJuno = "";
		String respHead = "";
		String respMan1Userid = "";
		String respMan1 = "";
		String respMan2Userid = "";
		String respMan2 = "";
		String respMan3Userid = "";
		String respMan3 = "";
		String orderResman = "";
		String mState = "";
		String cntrTitle = "";
		String reqCont = "";
		String holdCause = "";
		String backCause = "";
		String updtDt = "";
		
		String cnsdReqId = "";
		String prevCnsdReqId = "";
		String cnsdStatus = "";
		String fstCnsdReqId = "";
		String mnRespManApntYn = "";
		
		String fileId = "";
		String fileBigclsfcn = "";
		String fileMidclsfcn = "";
		String fileSmlclsfcn = "";
		String refKey = "";
		String upTp = "";
		
		String apbtDt = "";
		String cnsdOpnn = "";
		String cnsdDt = "";
		String cnsdManId = "";
		String cnsdManNm = "";
		String apbtManId = "";
		String apbtManNm = "";
		String reDt = "";
		String cnsdHoldCause = "";
		
		String sState = "";
		String apbtOpnn = "";
		String mainMatrCont = "";
		String sPrgrsStatus = "";
		
		String misId = "";
		String chg_divisioncd = "";
		String ehq_exist = "N";
		boolean addFlag = true;
		
		int cnsdLevel = 0;
		int histNo = 0;
		int fileNo = 0;
		
		try {
			System.out.println("================start1");
			srConn = DriverManager.getConnection(sourceUrl);
			System.out.println("================start1 pass");
			tgConn = DriverManager.getConnection(targetUrl);
			System.out.println("================start2 pass");
			tgConn.setAutoCommit(false);
			
			sql   = "SELECT A.CNTRNO"
			      + "      ,CONVERT(NVARCHAR(30), A.REQDT, 113) AS REQDT"
			      + "      ,A.REQMANNM"
			      + "      ,A.REQDEPT"
			      + "      ,A.REQMANJUNO"
			      + "      ,A.REQDEPTNM"
			      + "      ,A.DIVISIONCD"
			      + "      ,A.CNTRTITLE"
			      + "      ,A.REQCONT"
			      + "      ,A.CNTRDT"
			      + "      ,A.PRODUCT"
			      + "      ,A.RESPDT"
			      + "      ,ISNULL(CAST(A.CNTRAMT AS NVARCHAR(16)), '0') AS CNTRAMT"
			      + "      ,A.CURR"
			      + "      ,ISNULL(A.PREAMT, '0') AS PREAMT"
			      + "      ,A.PRECURR"
			      + "      ,ISNULL(CONVERT(NVARCHAR(8), FRDT, 112), '00000000') AS FRDT"
			      + "      ,ISNULL(CONVERT(NVARCHAR(8), TODT, 112), '99999999') AS TODT"
			      + "      ,RTRIM(A.STATE) AS STATE"
			      + "      ,ISNULL(A.RESPHEADJUNO, '') AS RESPHEADJUNO"
			      + "      ,ISNULL(A.RESPHEAD, '') AS RESPHEAD"
			      + "      ,ISNULL(A.RESPMAN1USERID, '') AS RESPMAN1USERID"
			      + "      ,ISNULL(A.RESPMAN1, '') AS RESPMAN1"
			      + "      ,ISNULL(A.RESPMAN2USERID, '') AS RESPMAN2USERID"
			      + "      ,ISNULL(A.RESPMAN2, '') AS RESPMAN2"
			      + "      ,ISNULL(A.RESPMAN3USERID, '') AS RESPMAN3USERID"
			      + "      ,ISNULL(A.RESPMAN3, '') AS RESPMAN3"
			      + "      ,ISNULL(A.ORDERRESMAN, '') AS ORDERRESMAN"
			      + "      ,ISNULL(A.HOLDCAUSE, '') AS HOLDCAUSE"
			      + "      ,ISNULL(A.BACKCAUSE, '') AS BACKCAUSE"
			      + "      ,CONVERT(NVARCHAR(30), A.UPDTDT, 113) AS UPDTDT"
			      + "      ,B.CMPYNM"
			      + "      ,B.CMPYADDR"
			      + "  FROM TCM_FCONTRACT A"
			      + "       LEFT OUTER JOIN TCM_DEALCOMP B ON A.CNTRNO = B.CNTRNO";
			if("1".equals(migType)) {
				//sql += " WHERE A.STATE IN ('S06')";
				//sql += " and A.divisioncd = 'SEUK' "; //임시로 SEUK 만 이관하도록 수정  : 2013. 11. 11적용
				//sql += " and A.divisioncd IN ('SESA','SEBN','SEGR','SEP','SENA') "; //2013. 11. 23적용
				//sql += " WHERE A.divisioncd IN ('SEUK','EHQ') "; //2013. 12. 17적용
				//sql += " WHERE A.STATE <> 'S01' AND A.divisioncd IN ('SEUK','EHQ','SESA','SEBN','SEGR','SEP','SENA') "; //2013. 12. 30적용
				sql += " WHERE A.STATE <> 'S01'  "; //2014. 01. 10 적용
			}
			contStmt = srConn.prepareStatement(sql);
			contRs = contStmt.executeQuery();
			
			sql   = "SELECT RTRIM(CNTRTP) AS CNTRTP"
			      + "  FROM TCM_CNTRTYPE"
			      + " WHERE CNTRNO = ?";
			contTypeStmt = srConn.prepareStatement(sql);
			
			sql   = "SELECT A.HISTNO"
			      + "      ,RTRIM(A.STATE) AS STATE"
			      + "      ,CONVERT(NVARCHAR(30), A.REGDT, 113) AS REGDT"
			      + "      ,A.USERID"
			      + "      ,A.REGMANNM"
			      + "      ,A.REGDEPT"
			      + "      ,A.REGDEPTNM"
			      + "      ,(CASE WHEN LEN(CAST(A.REGCONT AS NVARCHAR))<2 THEN C.CNTRTITLE ELSE ISNULL(A.REGCONT, C.CNTRTITLE) END )REGCONT"
			      + "      ,A.BACKCAUSE"
			      + "      ,A.RESCONTENT"
			      + "      ,CASE WHEN A.CNTRTITLE IS NULL THEN C.CNTRTITLE "
			      + "      		 WHEN LEN(A.CNTRTITLE) = 0 THEN C.CNTRTITLE "
			      + "            ELSE A.CNTRTITLE END CNTRTITLE "
			      + "      ,A.DEPTCONT"
			      + "  FROM TCM_REQHIST A"
			      + "  LEFT OUTER JOIN"
			      + "  TCM_FCONTRACT C ON A.CNTRNO = C.CNTRNO"
			      + " WHERE A.CNTRNO = ?"
			      + " ORDER BY A.HISTNO ASC";
			
			
			
			
			reqHistStmt = srConn.prepareStatement(sql);

			sql   = "SELECT UPTP"
			      + "      ,SEQNO"
			      + "      ,PATH"
			      + "      ,FILENM"
			      + "      ,FILESIZE"
			      + "	   ,( CASE WHEN SUBSTRING(FILENM,LEN(FILENM)-3,1) = '.' THEN SUBSTRING(FILENM,LEN(FILENM)-2,3) "
			      + "			   WHEN SUBSTRING(FILENM,LEN(FILENM)-4,1) = '.' THEN SUBSTRING(FILENM,LEN(FILENM)-3,4) "
			      + "		  END ) FILE_EXT "
			      + "  FROM TCM_UPFILE"
			      + " WHERE REFNO = ?"
			      + " ORDER BY SEQNO ASC";
			contFileStmt = srConn.prepareStatement(sql);
			
			sql   = "SELECT APPTITLE"
			      + "      ,APPCONTENT"
			      + "      ,STATUS"
			      + "  FROM TCM_APP"
			      + " WHERE CNTRNO = ?";
			appStmt = srConn.prepareStatement(sql);
			
			sql   = "SELECT MISID"
			      + "      ,APPORDER"
			      + "      ,APPTYPE"
			      + "      ,USERID"
			      + "      ,GRADE"
			      + "      ,NAME"
			      + "      ,EMAIL"
			      + "      ,DEPTNAME"
			      + "      ,OPINION"
			      + "      ,SUBMITDATE"
			      + "      ,APPROVEDATE"
			      + "  FROM TCM_APP_LINE"
			      + " WHERE CNTRNO = ?"
			      + " ORDER BY CAST(APPORDER AS INT) ASC";
			appLineStmt = srConn.prepareStatement(sql);
			
			System.out.println("=================pass xx sql="+sql);

			sql = "SELECT ( CASE WHEN ? = 'SEUK' AND ( "
				  + " SUBSTRING(?, 1, 5) IN ('C10CZ','C10N6','C10A7') "
				  + " OR SUBSTRING(?, 1, 7) = 'C10CD01' "
				  + " OR SUBSTRING(?, 1, 8) IN ('C10N8017','C10D0490','C10N0759','C10N0766','C10S9915','C10A7923') "
				  + " ) THEN 'EHQ' ELSE 'NOCHANGE' END) CHG_DIVISION , "
				  + "ISNULL((SELECT TOP 1 'Y' FROM TN_CLM_CUSTOMER WHERE COMP_CD = 'EHQ' AND CUSTOMER_NM1 = ? AND STREET = ? AND CUSTOMER_CD LIKE 'MG%'),'N') EHQ_EXIST";
			
			chkCompcdStmt = tgConn.prepareStatement(sql);
			
			sql   = "SELECT CUSTOMER_CD "
				  + "  FROM TN_CLM_CUSTOMER "
			      + " WHERE COMP_CD = ?"
			      + "   AND CUSTOMER_NM1 = ?"
			      + "   AND STREET = ?";
			existCompStmt = tgConn.prepareStatement(sql);
			
			sql   = "SELECT ISNULL(MAX(CAST(SUBSTRING(CUSTOMER_CD, 3, 10) AS INT)), 0) + 1"
			      + "  FROM TN_CLM_CUSTOMER"
			      + " WHERE CUSTOMER_CD LIKE 'MG%'";
			getCustomerCdStmt = tgConn.prepareStatement(sql);

			sql   = "INSERT INTO TN_CLM_CUSTOMER("
			      + "   CUSTOMER_CD"
			      + "  ,COMP_CD"
			      + "  ,CUSTOMER_NM1"
			      + "  ,STREET"
			      + "  ,REG_DT"
			      
			      + "  ,DODUN"
			      + "  ,GUDUN"
			      + "  ,HQDUN"
			      
			      + "  ,IV_NM1"
			      + "  ,GUNAM"
			      + "  ,DONAM"
			      + "  ,HQNAM"
			      + "  ,CUSTOMER_NM2"
			      + "  ,SCUSTOMER_NM1"
			      + "  ,SCUSTOMER_SORT"
			      + "  ,SGUNAM"
			      + "  ,SDONAM"
			      + "  ,SIV_NM1)"
			      + " VALUES("
			      + "   ?"
			      + "  ,?"
			      + "  ,?"
			      + "  ,?"
			      + "  ,getDate()"
			      
			      + "  ,?"
			      + "  ,?"
			      + "  ,?"
			      
			      + "  ,?"
			      + "  ,?"
			      + "  ,?"
			      + "  ,?"
			      + "  ,?"
			      + "  ,?"
			      + "  ,?"
			      + "  ,?"
			      + "  ,?"
			      + "  ,?"
			      
			      + " )";
			insCustomerStmt = tgConn.prepareStatement(sql);

			sql   = "UPDATE TN_CLM_CUSTOMER "
			      + "   SET COMP_CD = 'EHQ' "
			      + "  WHERE CUSTOMER_CD = ?";
			uptCustomerStmt = tgConn.prepareStatement(sql);
		
			
			sql   = "INSERT INTO TN_CLM_CONTRACT_MASTER("
				  + "    CNTRT_ID"
				  + "   ,COMP_CD"
				  + "   ,COMP_NM"
				  + "   ,CNTRT_NM"
				  + "   ,CNTRT_NM_EN"
				  + "   ,REGION_GBN"
				  + "   ,CNTRT_OPPNT_CD"
				  + "   ,CNTRT_OPPNT_NM"
				  + "   ,CNTRT_OPPNT_TYPE"
				  + "   ,BIZ_CLSFCN"
				  + "   ,DEPTH_CLSFCN"
				  + "   ,CNCLSNPURPS_BIGCLSFCN"
				  + "   ,CNCLSNPURPS_MIDCLSFCN"
				  + "   ,CNTRT_TRGT"
				  + "   ,PSHDBKGRND_PURPS"
				  + "   ,PAYMENT_GBN"
				  + "   ,CNTRTPERIOD_STARTDAY"
				  + "   ,CNTRTPERIOD_ENDDAY"
				  + "   ,CNTRT_UNTPRC"
				  + "   ,CNTRT_UNTPRC_EXPL"
				  + "   ,CNTRT_AMT"
				  + "   ,CRRNCY_UNIT"
				  + "   ,ETC_MAIN_CONT"
				  + "   ,AUTO_RNEW_YN"
				  + "   ,CNCLSN_PLNDDAY"
				  + "   ,CNTRT_RESPMAN_ID"
				  + "   ,CNTRT_RESPMAN_NM"
				  + "   ,CNTRT_RESP_DEPT"
				  + "   ,CNTRT_RESP_DEPT_NM"
				  + "   ,FST_CNTRT_RESP_DEPT"
				  + "   ,SEAL_MTHD"
				  + "   ,CNTRT_CNCLSN_YN"
				  + "   ,CNTRT_CNCLSNDAY"
				  + "   ,CNTRT_NO"
				  + "   ,CNTRT_STATUS"
				  + "   ,PRCS_DEPTH"
				  + "   ,DEPTH_STATUS"
				  + "   ,REG_OPERDIV"
				  + "   ,REG_DT"
				  + "   ,REG_ID"
				  + "   ,REG_NM"
				  + "   ,CNTRT_INFO_GBN"
				  + "   ,SELMS_STATE)"
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
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,CONVERT(DECIMAL(15,3), ?)"
				  + "   ,?"
				  + "   ,CONVERT(DECIMAL(15,3), ?)"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,ISNULL((SELECT TOP 1 USER_ID FROM TB_COM_USER WHERE ? IN (EMP_NO, SINGLE_ID)),?)"
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
				  + "   ,CONVERT(DATETIME, ?, 113)"
				  + "   ,ISNULL((SELECT TOP 1 USER_ID FROM TB_COM_USER WHERE ? IN (EMP_NO, SINGLE_ID)),?)"
				  + "   ,?"
				  + "   ,?,?)";
			insContractMasterStmt = tgConn.prepareStatement(sql);

			sql   = "DELETE FROM TN_CLM_CONTRACT_MASTER WHERE CNTRT_ID = ?";
			delContractMasterStmt = tgConn.prepareStatement(sql);

			sql   = "SELECT ISNULL(MAX(CAST(SUBSTRING(CNTRT_NO, CHARINDEX('_', CNTRT_NO)+1, 10) AS INT)), 0) + 1"
			      + "  FROM TN_CLM_CONTRACT_MASTER"
			      + " WHERE CNTRT_NO LIKE ? + ? + '%'";
			getCntrtNoStmt = tgConn.prepareStatement(sql);

			sql   = "INSERT INTO TN_CLM_CONT_CNSDREQ("
				  + "    CNSDREQ_ID"
				  + "   ,COMP_CD"
				  + "   ,COMP_NM"
				  + "   ,MN_CNSD_DEPT"
				  + "   ,MN_RESPMAN_APNT_YN"
				  + "   ,DMSTFRGN_GBN"
				  + "   ,PREV_CNSDREQ_ID"
				  + "   ,REQ_TITLE"
				  + "   ,REQMAN_ID"
				  + "   ,REQMAN_NM"
				  + "   ,REQ_OPERDIV"
				  + "   ,REQ_DEPT"
				  + "   ,REQ_DEPT_NM"
				  + "   ,REQ_DT"
				  + "   ,FST_REQ_DEPT"
				  + "   ,ACPT_DT"
				  + "   ,CNSD_DEMND_CONT"
				  + "   ,PRGRS_STATUS"
				  + "   ,CNSD_HOLD_CAUSE"
				  + "   ,RE_DT"
				  + "   ,REG_DT"
				  + "   ,REG_ID"
				  + "   ,REG_NM"
				  + "   ,CNSD_LEVEL"
				  + "   ,FST_CNSDREQ_ID)"
			      + " VALUES("
				  + "    ?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,ISNULL((SELECT TOP 1 USER_ID FROM TB_COM_USER WHERE ? IN (EMP_NO, SINGLE_ID)),?)"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,CONVERT(DATETIME, ?, 113)"
				  + "   ,?"
				  + "   ,CONVERT(DATETIME, ?, 113)"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,CASE WHEN ? = '' THEN NULL ELSE CONVERT(DATETIME, ?, 113) END"
				  + "   ,CONVERT(DATETIME, ?, 113)"
				  + "   ,ISNULL((SELECT TOP 1 USER_ID FROM TB_COM_USER WHERE ? IN (EMP_NO, SINGLE_ID)),?)"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?)";
			insContCnsdReqStmt = tgConn.prepareStatement(sql);

			sql   = "UPDATE TN_CLM_CONT_CNSDREQ"
				  + "   SET PRGRS_STATUS = ?"
				  + "      ,RE_DT = CASE WHEN ? = '' THEN RE_DT ELSE CONVERT(DATETIME, ?, 113) END"
				  + "      ,CNSD_HOLD_CAUSE = CASE WHEN ? = '' THEN CNSD_HOLD_CAUSE ELSE ? END"
				  + " WHERE CNSDREQ_ID = ?";
			uptContCnsdReqStmt = tgConn.prepareStatement(sql);

			sql   = "DELETE FROM TN_CLM_CONT_CNSDREQ WHERE CNSDREQ_ID IN (SELECT CNSDREQ_ID FROM TN_CLM_CONTRACT_CNSD WHERE CNTRT_ID = ?)";
			delContCnsdReqStmt = tgConn.prepareStatement(sql);

			sql   = "INSERT INTO TN_CLM_CONTRACT_CNSD("
				  + "    CNSDREQ_ID"
				  + "   ,CNTRT_ID"
				  + "   ,COMP_CD"
				  + "   ,COMP_NM"
				  + "   ,CNSDMAN_ID"
				  + "   ,CNSDMAN_NM"
				  + "   ,CNSD_DT"
				  + "   ,CNSD_MEMO"
				  + "   ,CNSD_OPNN"
				  + "   ,APBTMAN_ID"
				  + "   ,APBTMAN_NM"
				  + "   ,APBT_DT"
				  + "   ,APBT_OPNN"
				  + "   ,CNSD_STATUS"
				  + "   ,REG_DT"
				  + "   ,REG_ID"
				  + "   ,REG_NM)"
			      + " VALUES("
				  + "    ?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,ISNULL((SELECT TOP 1 USER_ID FROM TB_COM_USER WHERE ? IN (EMP_NO, SINGLE_ID)),?)"
				  + "   ,?"
				  + "   ,CASE WHEN ? = '' THEN NULL ELSE CONVERT(DATETIME, ?, 113) END"
				  + "   ,?"
				  + "   ,?"
				  + "   ,ISNULL((SELECT TOP 1 USER_ID FROM TB_COM_USER WHERE ? IN (EMP_NO, SINGLE_ID)),?)"
				  + "   ,?"
				  + "   ,CASE WHEN ? = '' THEN NULL ELSE CONVERT(DATETIME, ?, 113) END"
				  + "   ,?"
				  + "   ,?"
				  + "   ,CONVERT(DATETIME, ?, 113)"
				  + "   ,ISNULL((SELECT TOP 1 USER_ID FROM TB_COM_USER WHERE ? IN (EMP_NO, SINGLE_ID)),?)"
				  + "   ,?)";
			insContractCnsdStmt = tgConn.prepareStatement(sql);

			sql   = "UPDATE TN_CLM_CONTRACT_CNSD"
				  + "   SET CNSDMAN_ID = ISNULL((SELECT TOP 1 USER_ID FROM TB_COM_USER WHERE ? IN (EMP_NO, SINGLE_ID)),?)"
				  + "      ,CNSDMAN_NM = ?"
				  + "      ,CNSD_DT = CONVERT(DATETIME, ?, 113)"
				  + "      ,CNSD_MEMO = ?"
				  + "      ,CNSD_OPNN = ?"
				  + "      ,APBTMAN_ID = ISNULL((SELECT TOP 1 USER_ID FROM TB_COM_USER WHERE ? IN (EMP_NO, SINGLE_ID)),?)"
				  + "      ,APBTMAN_NM = ?"
				  + "      ,APBT_DT = CONVERT(DATETIME, ?, 113)"
				  + "      ,APBT_OPNN = ?"
				  + "      ,CNSD_STATUS = ?"
				  + " WHERE CNSDREQ_ID = ?"
				  + "   AND CNTRT_ID = ?";
			uptContractCnsdStmt = tgConn.prepareStatement(sql);

			sql   = "DELETE FROM TN_CLM_CONTRACT_CNSD WHERE CNTRT_ID = ?";
			delContractCnsdStmt = tgConn.prepareStatement(sql);

			sql   = "INSERT INTO TN_CLM_CONT_CNSDREQ_RESPMAN("
				  + "    CNSDREQ_ID"
				  + "   ,RESPMAN_ID"
				  + "   ,RESPMAN_NM"
				  + "   ,RESP_DEPT"
				  + "   ,ASGN_DT"
				  + "   ,ASGNMAN_ID"
				  + "   ,ASGNMAN_NM"
				  + "   ,MAIN_CNSD_YN"
				  + "   ,AUTO_APBT_YN"
				  + "   ,APBT_MEMO)"
			      + " VALUES("
				  + "    ?"
				  + "   ,ISNULL((SELECT TOP 1 USER_ID FROM TB_COM_USER WHERE ? IN (EMP_NO, SINGLE_ID)),?)"
				  + "   ,?"
				  + "   ,?"
				  + "   ,CONVERT(DATETIME, ?, 113)"
				  + "   ,ISNULL((SELECT TOP 1 USER_ID FROM TB_COM_USER WHERE ? IN (EMP_NO, SINGLE_ID)),?)"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?)";
			insCnsdReqRespManStmt = tgConn.prepareStatement(sql);

			sql   = "DELETE FROM TN_CLM_CONT_CNSDREQ_RESPMAN WHERE CNSDREQ_ID IN (SELECT CNSDREQ_ID FROM TN_CLM_CONTRACT_CNSD WHERE CNTRT_ID = ?)";
			delCnsdReqRespManStmt = tgConn.prepareStatement(sql);

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
				  + "   ,REG_ID, FILE_INFO )"
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
				  + "   ,ISNULL((SELECT TOP 1 USER_ID FROM TB_COM_USER WHERE ? IN (EMP_NO, SINGLE_ID)),?) "
				  + "	,?)";
			insComAttach = tgConn.prepareStatement(sql);

			sql   = "DELETE FROM TN_COM_ATTACH WHERE REF_KEY LIKE ? + '%'";
			delComAttach = tgConn.prepareStatement(sql);

			sql   = "INSERT INTO TN_CLM_CONTRACT_DEPTCNSD("
				  + "    CNSDREQ_ID"
				  + "   ,CNTRT_ID"
				  + "   ,COMP_CD"
				  + "   ,COMP_NM"
				  + "   ,CNSD_DEPT"
				  + "   ,CNSDMAN_ID"
				  + "   ,CNSDMAN_NM"
				  + "   ,CNSD_DT"
				  + "   ,CNSD_OPNN"
				  + "   ,MAIN_MATR_CONT"
				  + "   ,APBTMAN_ID"
				  + "   ,APBTMAN_NM"
				  + "   ,APBT_DT"
				  + "   ,APBT_OPNN"
				  + "   ,CNSD_STATUS)"
			      + " VALUES("
				  + "    ?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,ISNULL((SELECT TOP 1 USER_ID FROM TB_COM_USER WHERE ? IN (EMP_NO, SINGLE_ID)),?)"
				  + "   ,?"
				  + "   ,CASE WHEN ? = '' THEN NULL ELSE CONVERT(DATETIME, ?, 113) END"
				  + "   ,?"
				  + "   ,?"
				  + "   ,ISNULL((SELECT TOP 1 USER_ID FROM TB_COM_USER WHERE ? IN (EMP_NO, SINGLE_ID)),?)"
				  + "   ,?"
				  + "   ,CASE WHEN ? = '' THEN NULL ELSE CONVERT(DATETIME, ?, 113) END"
				  + "   ,?"
				  + "   ,?)";
			insContractDeptCnsdStmt = tgConn.prepareStatement(sql);

			sql   = "UPDATE TN_CLM_CONTRACT_DEPTCNSD"
				  + "   SET CNSDMAN_ID = ISNULL((SELECT TOP 1 USER_ID FROM TB_COM_USER WHERE ? IN (EMP_NO, SINGLE_ID)),?)"
				  + "      ,CNSDMAN_NM = ?"
				  + "      ,CNSD_DT = CONVERT(DATETIME, ?, 113)"
				  + "      ,CNSD_OPNN = ?"
				  + "      ,MAIN_MATR_CONT = ?"
				  + "      ,APBTMAN_ID = ISNULL((SELECT TOP 1 USER_ID FROM TB_COM_USER WHERE ? IN (EMP_NO, SINGLE_ID)),?)"
				  + "      ,APBTMAN_NM = ?"
				  + "      ,APBT_DT = CONVERT(DATETIME, ?, 113)"
				  + "      ,APBT_OPNN = ?"
				  + "      ,CNSD_STATUS = ?"
				  + " WHERE CNSDREQ_ID = ?"
				  + "   AND CNTRT_ID = ?"
				  + "   AND CNSD_DEPT = ?";
			uptContractDeptCnsdStmt = tgConn.prepareStatement(sql);

			sql   = "DELETE FROM TN_CLM_CONTRACT_DEPTCNSD WHERE CNTRT_ID = ?";
			delContractDeptCnsdStmt = tgConn.prepareStatement(sql);

			sql   = "SELECT CNSD_STATUS"
			      + "  FROM TN_CLM_CONTRACT_DEPTCNSD"
				  + " WHERE CNSDREQ_ID = ?"
				  + "   AND CNTRT_ID = ?"
				  + "   AND CNSD_DEPT = ?";
			extContractDeptCnsdStmt = tgConn.prepareStatement(sql);

			sql   = "INSERT INTO TN_CLM_CONT_CNSDREQ_HOLD("
				  + "    CNSDREQ_ID"
				  + "   ,HOLD_SEQNO"
				  + "   ,HOLD_STARTDAY"
				  + "   ,HOLD_ENDDAY"
				  + "   ,HOLD_CAUSE"
				  + "   ,REG_DT"
				  + "   ,REG_ID"
				  + "   ,REG_NM)"
			      + " VALUES("
				  + "    ?"
				  + "   ,?"
				  + "   ,CONVERT(DATETIME, ?, 113)"
				  + "   ,CASE WHEN ? = '' THEN NULL ELSE CONVERT(DATETIME, ?, 113) END"
				  + "   ,?"
				  + "   ,CONVERT(DATETIME, ?, 113)"
				  + "   ,ISNULL((SELECT TOP 1 USER_ID FROM TB_COM_USER WHERE ? IN (EMP_NO, SINGLE_ID)),?)"
				  + "   ,?)";
			insContCnsdReqHoldStmt = tgConn.prepareStatement(sql);

			sql   = "DELETE FROM TN_CLM_CONT_CNSDREQ_HOLD WHERE CNSDREQ_ID IN (SELECT CNSDREQ_ID FROM TN_CLM_CONTRACT_CNSD WHERE CNTRT_ID = ?)";
			delContCnsdReqHoldStmt = tgConn.prepareStatement(sql);

			sql   = "SELECT HOLD_SEQNO"
			      + "  FROM TN_CLM_CONT_CNSDREQ_HOLD"
				  + " WHERE CNSDREQ_ID = ?";
			extContCnsdReqHoldStmt = tgConn.prepareStatement(sql);

			sql   = "INSERT INTO TB_COM_START_PROCESS_WSVO("
				  + "    MODULE_ID"
				  + "   ,MIS_ID"
				  + "   ,STATUS"
				  + "   ,BODY"
				  + "   ,CREATE_DATE"
				  + "   ,LOCALE_INFO"
				  + "   ,TIME_ZONE"
				  + "   ,TITLE"
				  + "   ,APPRVL_CLSFCN"
				  + "   ,REF_KEY)"
			      + " VALUES("
				  + "    'LAS'"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,'en_US.UTF-8'"		
				  + "   ,'GMT+0'"			
				  + "   ,?"
				  + "   ,'C03002'"
				  + "   ,?)";
			insStartProcessWsvoStmt = tgConn.prepareStatement(sql);

			sql   = "DELETE FROM TB_COM_START_PROCESS_WSVO WHERE REF_KEY IN (SELECT CNSDREQ_ID FROM TN_CLM_CONTRACT_CNSD WHERE CNTRT_ID = ?)";
			delStartProcessWsvoStmt = tgConn.prepareStatement(sql);

			sql   = "INSERT INTO TB_COM_ROUTE_WSVO("
				  + "    MODULE_ID"
				  + "   ,MIS_ID"
				  + "   ,MAIL_ADDRESS"
				  + "   ,ACTION_TYPE"
				  + "   ,ACTIVITY"
				  + "   ,APPROVED"
				  + "   ,ARRIVED"
				  + "   ,COMP_CODE"
				  + "   ,COMP_NAME"
				  + "   ,DEPT_CODE"
				  + "   ,DEPT_NAME"
				  + "   ,OPINION"
				  + "   ,SEQUENCE"
				  + "   ,USER_ID"
				  + "   ,USER_NAME)"
			      + " VALUES("
				  + "    'LAS'"
				  + "   ,?"
				  + "   ,?"
				  + "   ,'0'"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,?"
				  + "   ,ISNULL((SELECT TOP 1 USER_ID FROM TB_COM_USER WHERE ? IN (EMP_NO, SINGLE_ID)),?)"
				  + "   ,?)";
			insRouteWsvoStmt = tgConn.prepareStatement(sql);

			sql   = "DELETE FROM TB_COM_ROUTE_WSVO WHERE MIS_ID IN (SELECT MIS_ID FROM TB_COM_START_PROCESS_WSVO WHERE REF_KEY IN (SELECT CNSDREQ_ID FROM TN_CLM_CONTRACT_CNSD WHERE CNTRT_ID = ?))";
			delRouteWsvoStmt = tgConn.prepareStatement(sql);

			sql   = "INSERT INTO TB_COM_ATTACHMENT_WSVO("
				  + "    MODULE_ID"
				  + "   ,MIS_ID"
				  + "   ,SEQUENCE"
				  + "   ,FILE_NAME"
				  + "   ,FILE_SIZE"
				  + "   ,STORE_LOCATION)"
			      + " VALUES("
				  + "    'LAS'"
				  + "   ,?"
				  + "   ,CAST(? AS NVARCHAR)"
				  + "   ,?"
				  + "   ,CAST(? AS NVARCHAR)"
				  + "   ,?)";
			insAttachmentWsvoStmt = tgConn.prepareStatement(sql);

			sql   = "DELETE FROM TB_COM_ATTACHMENT_WSVO WHERE MIS_ID IN (SELECT MIS_ID FROM TB_COM_START_PROCESS_WSVO WHERE REF_KEY IN (SELECT CNSDREQ_ID FROM TN_CLM_CONTRACT_CNSD WHERE CNTRT_ID = ?))";
			delAttachmentWsvoStmt = tgConn.prepareStatement(sql);



			// 계약정보 이관
			while(contRs.next()) {
				cntrNo	= strNull(contRs.getString("CNTRNO"), "");
				mState	= strNull(contRs.getString("STATE"), "");
				
				cntrTitle	= strNull(contRs.getString("CNTRTITLE"), "");
				reqCont		= strNull(contRs.getString("REQCONT"), "");
				
				reqDt		= strNull(contRs.getString("REQDT"), "");
				reqManJuno	= strNull(contRs.getString("REQMANJUNO"), "");
				reqManNm	= strNull(contRs.getString("REQMANNM"), "");
				reqDept		= strNull(contRs.getString("REQDEPT"), "");
				reqDeptNm	= strNull(contRs.getString("REQDEPTNM"), "");
				divisionCd	= strNull(contRs.getString("DIVISIONCD"), "");
				
				respHeadJuno	= strNull(contRs.getString("RESPHEADJUNO"), "");
				respHead		= strNull(contRs.getString("RESPHEAD"), "");
				respMan1Userid	= strNull(contRs.getString("RESPMAN1USERID"), "");
				respMan1		= strNull(contRs.getString("RESPMAN1"), "");
				respMan2Userid	= strNull(contRs.getString("RESPMAN2USERID"), "");
				respMan2		= strNull(contRs.getString("RESPMAN2"), "");
				respMan3Userid	= strNull(contRs.getString("RESPMAN3USERID"), "");
				respMan3		= strNull(contRs.getString("RESPMAN3"), "");
				orderResman		= strNull(contRs.getString("ORDERRESMAN"), "");
				
				holdCause	= strNull(contRs.getString("HOLDCAUSE"), "");
				backCause	= strNull(contRs.getString("BACKCAUSE"), "");
				updtDt		= strNull(contRs.getString("UPDTDT"), "");
				

				bi = 0;
				chkCompcdStmt.setString(++bi, divisionCd);
				chkCompcdStmt.setString(++bi, reqDept);
				chkCompcdStmt.setString(++bi, reqDept);
				chkCompcdStmt.setString(++bi, reqDept);
				chkCompcdStmt.setString(++bi, contRs.getString("CMPYNM"));
				chkCompcdStmt.setString(++bi, contRs.getString("CMPYADDR"));
			System.out.println("divisionCd,reqDept,CMPYNM,CMPYADDR:"+divisionCd+"|"+reqDept+"|"+contRs.getString("CMPYNM")+"|"+ contRs.getString("CMPYADDR"));	
				chg_divisioncd = ""; ehq_exist = "N";
				// 계약상대방 체크 : SHUK 중에 부서코드로 체크해서 EHQ로 변경
				chkCompRs = chkCompcdStmt.executeQuery();
				if(chkCompRs.next()) {
					chg_divisioncd = chkCompRs.getString(1);
					ehq_exist = chkCompRs.getString(2);
				}
				
				customerCd = "";
				++rsRow;

				bi = 0;
						  
				existCompStmt.setString(++bi, divisionCd);
				existCompStmt.setString(++bi, contRs.getString("CMPYNM"));
				existCompStmt.setString(++bi, contRs.getString("CMPYADDR"));
				
				if(chg_divisioncd.equals("EHQ")){
					//위에서는 기존 divisionCd 로 TN_CLM_CUSTOMER를 조회해서 EHQ에 해당되면 여기서 변경처리
					divisionCd = chg_divisioncd;
				}
				
				// 계약상대방 체크
				existCompRs = existCompStmt.executeQuery();
				if(existCompRs.next()) {
					customerCd = existCompRs.getString(1);

					if(chg_divisioncd.equals("EHQ")){
						//EHQ로 업데이트 처리
						uptCustomerStmt.setString(1, customerCd);
						sqlRet = uptCustomerStmt.executeUpdate();

						if(sqlRet != 1) {
							System.out.print(rsRow + "\t");
							System.out.print(customerCd);
							System.out.print("\t" + cntrtId);
							System.out.print("\t" + cnsdReqId);
		
							System.out.print("\t==> TN_CLM_CUSTOMER 수정 실패");
							System.out.println("");
						}
					}
					
				} else {
					if(!"".equals(strNull(contRs.getString("CMPYNM"), ""))) {
						// 거래상대방 등록
						getCustomerCdRs = getCustomerCdStmt.executeQuery();
						
						maxCustomerCnt = 0;
						
						if(getCustomerCdRs.next()) {
							maxCustomerCnt = getCustomerCdRs.getInt(1);
						} else {
							maxCustomerCnt = 1;
						}
						
						customerCd = "MG" + String.format("%05d", maxCustomerCnt);
						
						bi = 0;
						insCustomerStmt.setString(++bi, customerCd);
						insCustomerStmt.setString(++bi, divisionCd);
						insCustomerStmt.setString(++bi, contRs.getString("CMPYNM"));
						insCustomerStmt.setString(++bi, strNull(contRs.getString("CMPYADDR"), ""));
						
						insCustomerStmt.setString(++bi, customerCd);
						insCustomerStmt.setString(++bi, customerCd);
						insCustomerStmt.setString(++bi, customerCd);
						
						insCustomerStmt.setString(++bi, contRs.getString("CMPYNM"));
						insCustomerStmt.setString(++bi, contRs.getString("CMPYNM"));
						insCustomerStmt.setString(++bi, contRs.getString("CMPYNM"));
						insCustomerStmt.setString(++bi, contRs.getString("CMPYNM"));
						insCustomerStmt.setString(++bi, contRs.getString("CMPYNM"));
						insCustomerStmt.setString(++bi, contRs.getString("CMPYNM"));
						insCustomerStmt.setString(++bi, contRs.getString("CMPYNM"));
						insCustomerStmt.setString(++bi, contRs.getString("CMPYNM"));
						insCustomerStmt.setString(++bi, contRs.getString("CMPYNM"));
						insCustomerStmt.setString(++bi, contRs.getString("CMPYNM"));
						
						if(chg_divisioncd.equals("EHQ")){
							if(ehq_exist.equals("N")){
								insCustomerStmt.executeUpdate();
							}
						}else{
							insCustomerStmt.executeUpdate();
						}
						
					} else {
						customerCd = "NULL";
					}
				}

				
				if("NULL".equals(customerCd)) {
					cntrtId = cntrNo + "_" + customerCd;
				} else {
					if(customerCd.length()<=3){
						cntrtId = cntrNo + "_000";
					}else if(customerCd.length()<=5){	
						cntrtId = cntrNo + "_" + customerCd.substring(3,customerCd.length());
					}else{
						cntrtId = cntrNo + "_" + customerCd.substring(3,6);
					}
				}
				
				System.out.println("cntrNo_customerCd = " + cntrtId);
				if("".equals(strNull(contRs.getString("PREAMT"), ""))) {
					untPrc = "0";
				} else {
					untPrc = contRs.getString("PREAMT");
				}
				if("0".equals(contRs.getString("CNTRAMT"))) {
					paymentGbn = "C02004";		// 금액이 없으면 지불/수금구분은 '해당없음'
				} else {
					paymentGbn = "C02001";		// 금액이 있으면 지불/수금구분은 '지불/수금'
				}
				
				bi = 0;
				contTypeStmt.setString(++bi, cntrNo);
				contTypeRs = contTypeStmt.executeQuery();
				if(contTypeRs.next()) {
					if("A011".equals(strNull(contTypeRs.getString(1), ""))) {			// 투자금융 - NDA
						bizClsfcn = "T0101";					// NDA
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0311";			// Alliance/Investment/Finance
						cnclsnpurpsMidclsfcn = "T031102";		// 인수/투자계약
					} else if("A021".equals(strNull(contTypeRs.getString(1), ""))) {	// 투자금융 - MOU(LOI)
						bizClsfcn = "T0102";					// MOU/LOI/MOA/LOA
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0311";			// Alliance/Investment/Finance
						cnclsnpurpsMidclsfcn = "T031102";		// 인수/투자계약
					} else if("A071".equals(strNull(contTypeRs.getString(1), ""))) {	// 투자금융 - Others
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0311";			// Alliance/Investment/Finance
						cnclsnpurpsMidclsfcn = "T031102";		// 인수/투자계약
					} else if("B011".equals(strNull(contTypeRs.getString(1), ""))) {	// 생산/기술/품질 - NDA
						bizClsfcn = "T0101";					// NDA
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0305";			// Manufacturing
						cnclsnpurpsMidclsfcn = "T030599";		// 기타
					} else if("B021".equals(strNull(contTypeRs.getString(1), ""))) {	// 생산/기술/품질 - MOU(LOI)
						bizClsfcn = "T0102";					// MOU/LOI/MOA/LOA
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0305";			// Manufacturing
						cnclsnpurpsMidclsfcn = "T030599";		// 기타
					} else if("B071".equals(strNull(contTypeRs.getString(1), ""))) {	// 생산/기술/품질 - Others
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0305";			// Manufacturing
						cnclsnpurpsMidclsfcn = "T030599";		// 기타
					} else if("D011".equals(strNull(contTypeRs.getString(1), ""))) {	// 고용/홍보/자문/부동산/기타- NDA
						bizClsfcn = "T0101";					// NDA
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0309";			// HR/General Administration
						cnclsnpurpsMidclsfcn = "T030902";		// 총무계약
					} else if("D021".equals(strNull(contTypeRs.getString(1), ""))) {	// 고용/홍보/자문/부동산/기타 - MOU(LOI)
						bizClsfcn = "T0102";					// MOU/LOI/MOA/LOA
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0309";			// HR/General Administration
						cnclsnpurpsMidclsfcn = "T030902";		// 총무계약
					} else if("D071".equals(strNull(contTypeRs.getString(1), ""))) {	// 고용/홍보/자문/부동산/기타 - Others
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0309";			// HR/General Administration
						cnclsnpurpsMidclsfcn = "T030902";		// 총무계약
					} else if("C011".equals(strNull(contTypeRs.getString(1), ""))) {	// 판매/구매 - NDA
						bizClsfcn = "T0101";					// NDA
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0307";			// Marketing/Sales
						cnclsnpurpsMidclsfcn = "T030799";		// 기타
					} else if("C021".equals(strNull(contTypeRs.getString(1), ""))) {	// 판매/구매 - MOU(LOI)
						bizClsfcn = "T0102";					// MOU/LOI/MOA/LOA
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0307";			// Marketing/Sales
						cnclsnpurpsMidclsfcn = "T030799";		// 기타
					} else if("C081".equals(strNull(contTypeRs.getString(1), ""))) {	// 판매/구매 - Others
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0307";			// Marketing/Sales
						cnclsnpurpsMidclsfcn = "T030799";		// 기타
					} else if("A031".equals(strNull(contTypeRs.getString(1), ""))) {	// Joint Venture
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0311";			// Alliance/Investment/Finance
						cnclsnpurpsMidclsfcn = "T031101";		// 제휴/협력계약
					} else if("A041".equals(strNull(contTypeRs.getString(1), ""))) {	// Stock Purchase Agreement
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0311";			// Alliance/Investment/Finance
						cnclsnpurpsMidclsfcn = "T031101";		// 제휴/협력계약
					} else if("A042".equals(strNull(contTypeRs.getString(1), ""))) {	// Shareholder''s Agreement
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0311";			// Alliance/Investment/Finance
						cnclsnpurpsMidclsfcn = "T031101";		// 제휴/협력계약
					} else if("A043".equals(strNull(contTypeRs.getString(1), ""))) {	// Article of Incorporation
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0309";			// HR/General Administration
						cnclsnpurpsMidclsfcn = "T030902";		// 총무계약
					} else if("A046".equals(strNull(contTypeRs.getString(1), ""))) {	// Other M&A
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0311";			// Alliance/Investment/Finance
						cnclsnpurpsMidclsfcn = "T031102";		// 인수/투자계약
					} else if("A051".equals(strNull(contTypeRs.getString(1), ""))) {	// Consortium/Partnership
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0311";			// Alliance/Investment/Finance
						cnclsnpurpsMidclsfcn = "T031101";		// 제휴/협력계약
					} else if("A061".equals(strNull(contTypeRs.getString(1), ""))) {	// Financing
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0311";			// Alliance/Investment/Finance
						cnclsnpurpsMidclsfcn = "T031103";		// 금융/보험계약
					} else if("B031".equals(strNull(contTypeRs.getString(1), ""))) {	// Technology License
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0303";			// 라이선스
						cnclsnpurpsMidclsfcn = "T030399";		// 기타
					} else if("B032".equals(strNull(contTypeRs.getString(1), ""))) {	// Patent & IPR License
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0303";			// 라이선스
						cnclsnpurpsMidclsfcn = "T030399";		// 기타
					} else if("B033".equals(strNull(contTypeRs.getString(1), ""))) {	// S/W License
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0303";			// 라이선스
						cnclsnpurpsMidclsfcn = "T030399";		// 기타
					} else if("B034".equals(strNull(contTypeRs.getString(1), ""))) {	// License & Distributorship
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0303";			// 라이선스
						cnclsnpurpsMidclsfcn = "T030399";		// 기타
					} else if("B035".equals(strNull(contTypeRs.getString(1), ""))) {	// Other License
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0303";			// 라이선스
						cnclsnpurpsMidclsfcn = "T030399";		// 기타
					} else if("B041".equals(strNull(contTypeRs.getString(1), ""))) {	// Development
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0301";			// Development
						cnclsnpurpsMidclsfcn = "T030199";		// 기타
					} else if("B042".equals(strNull(contTypeRs.getString(1), ""))) {	// Joint Development
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0301";			// Development
						cnclsnpurpsMidclsfcn = "T030102";		// 공동개발계약
					} else if("B043".equals(strNull(contTypeRs.getString(1), ""))) {	// Development & Distributorship
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0301";			// Development
						cnclsnpurpsMidclsfcn = "T030199";		// 기타
					} else if("B044".equals(strNull(contTypeRs.getString(1), ""))) {	// Other Development
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0301";			// Development
						cnclsnpurpsMidclsfcn = "T030199";		// 기타
					} else if("B051".equals(strNull(contTypeRs.getString(1), ""))) {	// Maintenance/Support/Training
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0309";			// HR/General Administration
						cnclsnpurpsMidclsfcn = "T030902";		// 총무계약
					} else if("B061".equals(strNull(contTypeRs.getString(1), ""))) {	// Evaluation/Test
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0309";			// HR/General Administration
						cnclsnpurpsMidclsfcn = "T030902";		// 총무계약
					} else if("C031".equals(strNull(contTypeRs.getString(1), ""))) {	// Supply/Sales
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0307";			// Marketing/Sales
						cnclsnpurpsMidclsfcn = "T030705";		// 공급계약
					} else if("C041".equals(strNull(contTypeRs.getString(1), ""))) {	// Purchase
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0304";			// Purchase
						cnclsnpurpsMidclsfcn = "T030499";		// 기타
					} else if("C051".equals(strNull(contTypeRs.getString(1), ""))) {	// Distributorship/Agency
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0307";			// Marketing/Sales
						cnclsnpurpsMidclsfcn = "T030799";		// 기타
					} else if("C061".equals(strNull(contTypeRs.getString(1), ""))) {	// OEM/Foundry
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0305";			// Manufacturing
						cnclsnpurpsMidclsfcn = "T030502";		// OEM(외주임가공)계약
					} else if("C071".equals(strNull(contTypeRs.getString(1), ""))) {	// Quality
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0308";			// Customer Satisfaction/Service
						cnclsnpurpsMidclsfcn = "T030802";		// 품질계약
					} else if("D031".equals(strNull(contTypeRs.getString(1), ""))) {	// Consulting
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0312";			// Consulting/Research
						cnclsnpurpsMidclsfcn = "T031201";		// 컨설팅계약
					} else if("D041".equals(strNull(contTypeRs.getString(1), ""))) {	// Promotion/Marketing Alliance
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0311";			// Alliance/Investment/Finance
						cnclsnpurpsMidclsfcn = "T031101";		// 제휴/협력계약
					} else if("D051".equals(strNull(contTypeRs.getString(1), ""))) {	// Employment
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0309";			// HR/General Administration
						cnclsnpurpsMidclsfcn = "T030901";		// 인사계약
					} else if("D061".equals(strNull(contTypeRs.getString(1), ""))) {	// Lease
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0309";			// HR/General Administration
						cnclsnpurpsMidclsfcn = "T030902";		// 총무계약
					} else if("E121".equals(strNull(contTypeRs.getString(1), ""))) {	// Transportation (road)
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0306";			// 물류
						cnclsnpurpsMidclsfcn = "T030601";		// 운송
					} else if("E131".equals(strNull(contTypeRs.getString(1), ""))) {	// Transportation (combined modalities)
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0306";			// 물류
						cnclsnpurpsMidclsfcn = "T030601";		// 운송
					} else if("E141".equals(strNull(contTypeRs.getString(1), ""))) {	// Warehousing (not Warehouse Lease)
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0306";			// 물류
						cnclsnpurpsMidclsfcn = "T030602";		// 창고
					} else if("A044".equals(strNull(contTypeRs.getString(1), ""))) {	// Business Transfer Agreement
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0306";			// 물류
						cnclsnpurpsMidclsfcn = "T030603";		// Agency
					} else if("A045".equals(strNull(contTypeRs.getString(1), ""))) {	// Investor's Right Agreement
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0311";			// Alliance/Investment/Finance
						cnclsnpurpsMidclsfcn = "T031102";		// 인수/투자계약
					} else if("E011".equals(strNull(contTypeRs.getString(1), ""))) {	// LOGISTICS NDA
						bizClsfcn = "T0101";					// NDA
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0306";			// 물류
						cnclsnpurpsMidclsfcn = "T030699";		// 기타
					} else if("E051".equals(strNull(contTypeRs.getString(1), ""))) {	// LOGISTICS Others
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0306";			// 물류
						cnclsnpurpsMidclsfcn = "T030699";		// 기타
					} else if("E061".equals(strNull(contTypeRs.getString(1), ""))) {	// LOGISTICS Customs
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0306";			// 물류
						cnclsnpurpsMidclsfcn = "T030699";		// 기타
					} else if("E062".equals(strNull(contTypeRs.getString(1), ""))) {	// LOGISTICS Transportation
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0306";			// 물류
						cnclsnpurpsMidclsfcn = "T030601";		// 운송
					} else if("E063".equals(strNull(contTypeRs.getString(1), ""))) {	// LOGISTICS Other
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0306";			// 물류
						cnclsnpurpsMidclsfcn = "T030699";		// 기타
					} else if("E071".equals(strNull(contTypeRs.getString(1), ""))) {	// LOGISTICS Warehousing (not Lease)
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0306";			// 물류
						cnclsnpurpsMidclsfcn = "T030602";		// 창고
					} else if("E072".equals(strNull(contTypeRs.getString(1), ""))) {	// LOGISTICS Warehouse Lease (Samsung rents)
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0306";			// 물류
						cnclsnpurpsMidclsfcn = "T030602";		// 창고
					} else if("E081".equals(strNull(contTypeRs.getString(1), ""))) {	// LOGISTICS Transportation(air)
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0306";			// 물류
						cnclsnpurpsMidclsfcn = "T030601";		// 운송
					} else if("E085".equals(strNull(contTypeRs.getString(1), ""))) {	// LOGISTICS Transportation(road)
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0306";			// 물류
						cnclsnpurpsMidclsfcn = "T030601";		// 운송
					} else {
						if(!"A001".equals(strNull(contTypeRs.getString(1), ""))) {
							System.out.println("\tcheck type [" + contTypeRs.getString(1) + "]");
						}
						bizClsfcn = "T0103";					// 본계약
						depthClsfcn = "T0201";					// 신규계약
						cnclsnpurpsBigclsfcn = "T0399";			// 미정
						cnclsnpurpsMidclsfcn = "T039999";		// 미정
					}
				} else {
					bizClsfcn = "T0103";				// 본계약
					depthClsfcn = "T0201";				// 신규계약
					cnclsnpurpsBigclsfcn = "T0399";		// 미정
					cnclsnpurpsMidclsfcn = "T039999";	// 미정
				}

				System.out.println("\t[" + mState + "]");

				if("S06".equals(mState)) {		// 체결
					cntrtCnclsnYn = "Y";
					cntrtCnclsnday = contRs.getString("FRDT");
					
					// 계약종료일이 현재일 이전이면 상태를 만료상태로 설정
					//System.out.print("\t[" + Integer.parseInt(getShortDate(new Date())) + "]");
					//System.out.print("\t[" + Integer.parseInt(strNull(contRs.getString("TODT"), "0")) + "]");
					
					if(Integer.parseInt(getShortDate(new Date())) > Integer.parseInt(strNull(contRs.getString("TODT"), "0"))) {
						cntrtStatus = "C02402";
						depthStatus = "C02662";
						prgrsStatus = "C04219";
					} else {
						cntrtStatus = "C02404";
						depthStatus = "C02686";
						prgrsStatus = "C04223";
					}
					
					// 계약번호 채번
					cntrtNoYMD = contRs.getString("FRDT").substring(2, 8);
					
					bi = 0;
					getCntrtNoStmt.setString(++bi, divisionCd);
					getCntrtNoStmt.setString(++bi, cntrtNoYMD);
					
					getCntrtNoRs = getCntrtNoStmt.executeQuery();
					if(getCntrtNoRs.next()) {
						cntrtNo = divisionCd + cntrtNoYMD + "_" + String.format("%04d", getCntrtNoRs.getInt(1));
					} else {
						cntrtNo = divisionCd + cntrtNoYMD + "_0001";
					}

				} else {
					cntrtCnclsnYn = "N";
					cntrtCnclsnday = "";
					cntrtNo = "";
					
					cntrtStatus = "C02401";
					
					if("S01".equals(mState)) {				// 의뢰 임시저장 (의뢰인)
						depthStatus = "C02601";		// 작성중
						prgrsStatus = "C04201";		// 작성중
					} else if("S02".equals(mState)) {		// 의뢰
						depthStatus = "C02606";		// 검토중
						prgrsStatus = "C04203";		// 의뢰
					} else if("S03".equals(mState)) {		// 회신
						depthStatus = "C02608";		// 회신완료
						prgrsStatus = "C04207";		// 회신
					} else if("S04".equals(mState)) {		// 재의뢰
						depthStatus = "C02606";		// 검토중
						prgrsStatus = "C04203";		// 의뢰
					} else if("S05".equals(mState)) {		// 보류
						depthStatus = "C02607";		// 보류
						prgrsStatus = "C04210";		// 보류
					} else if("S07".equals(mState)) {		// 반려
						depthStatus = "C02608";		// 회신완료
						prgrsStatus = "C04207";		// 회신
					} else if("S08".equals(mState)) {		// 결재상신 (법무팀)
						depthStatus = "C02606";		// 검토중
						prgrsStatus = "C04205";		// 미결
					} else if("S09".equals(mState)) {		// 회신 임시저장 (법무팀)
						depthStatus = "C02606";		// 검토중
						prgrsStatus = "C04204";		// 검토
					} else if("S10".equals(mState)) {		// 재의뢰 임시저장 (의뢰인)
						depthStatus = "C02601";		// 작성중
						prgrsStatus = "C04201";		// 작성중
					} else if("S11".equals(mState)) {		// 결재반려 (법무팀)
						depthStatus = "C02606";		// 검토중
						prgrsStatus = "C04204";		// 검토
					} else if("S12".equals(mState)) {		// 이관
						depthStatus = "C02606";		// 검토중
						prgrsStatus = "C04203";		// 의뢰
					} else if("S13".equals(mState)) {		// 내부품의 (의뢰인)
						depthStatus = "C02621";		// 작성중
						prgrsStatus = "C04211";		// 작성중
					} else if("S14".equals(mState)) {		// 결재중 (의뢰인 ?)
						depthStatus = "C02622";		// 결재중
						prgrsStatus = "C04214";		// 결재중
					} else if("S15".equals(mState)) {		// 내부품의 완료 (?)
						depthStatus = "C02641";		// 체결미확인		
						prgrsStatus = "C04217";		// 체결미확인   	
					} else if("S16".equals(mState)) {		// 내부품의 반려 (?)
						depthStatus = "C02625";		// 반려
						prgrsStatus = "C04216";		// 반려
					} else if("S17".equals(mState)) {		// 내부품의 상신취소 (?)
						depthStatus = "C02624";		// 상신취소
						prgrsStatus = "C04215";		// 상신취소
					} else if("S19".equals(mState)) {		// Do Not Proceed
						depthStatus = "C02627";		// 보류
						prgrsStatus = "C04212";		// 보류
					} else if("S20".equals(mState)) {		// Left the Business
						depthStatus = "C02607";		// 보류
						prgrsStatus = "C04210";		// 보류
					} else {
						System.out.println("\t[" + mState + "]");
						depthStatus = "";
						prgrsStatus = "";
						return;
					}
				}
		System.out.println("=================pass xx 001=");		
				// TB_COM_ATTACHMENT_WSVO 삭제
				bi = 0;
				delAttachmentWsvoStmt.setString	(++bi, cntrtId);
				delAttachmentWsvoStmt.executeUpdate();
				
				// TB_COM_ROUTE_WSVO 삭제
				bi = 0;
				delRouteWsvoStmt.setString	(++bi, cntrtId);
				delRouteWsvoStmt.executeUpdate();
				
				// TB_COM_START_PROCESS_WSVO 삭제
				bi = 0;
				delStartProcessWsvoStmt.setString	(++bi, cntrtId);
				delStartProcessWsvoStmt.executeUpdate();
				
				// TN_CLM_CONT_CNSDREQ_HOLD 삭제
				bi = 0;
				delContCnsdReqHoldStmt.setString	(++bi, cntrtId);
				delContCnsdReqHoldStmt.executeUpdate();
				
				// TN_CLM_CONTRACT_DEPTCNSD 삭제
				bi = 0;
				delContractDeptCnsdStmt.setString	(++bi, cntrtId);
				delContractDeptCnsdStmt.executeUpdate();
				
				// TN_COM_ATTACH 삭제
				bi = 0;
				delComAttach.setString	(++bi, cntrtId);
				delComAttach.executeUpdate();
				
				// TN_CLM_CONT_CNSDREQ_RESPMAN 삭제
				bi = 0;
				delCnsdReqRespManStmt.setString	(++bi, cntrtId);
				delCnsdReqRespManStmt.executeUpdate();
				
				// TN_CLM_CONT_CNSDREQ 삭제
				bi = 0;
				delContCnsdReqStmt.setString	(++bi, cntrtId);
				delContCnsdReqStmt.executeUpdate();
				
				// TN_CLM_CONTRACT_CNSD 삭제
				bi = 0;
				delContractCnsdStmt.setString	(++bi, cntrtId);
				delContractCnsdStmt.executeUpdate();
				
				// TN_CLM_CONTRACT_MASTER 삭제
				bi = 0;
				delContractMasterStmt.setString	(++bi, cntrtId);
				delContractMasterStmt.executeUpdate();
				
				// TN_CLM_CONTRACT_MASTER 등록
				bi = 0;
				insContractMasterStmt.setString	(++bi, cntrtId);							//CNTRT_ID
				insContractMasterStmt.setString	(++bi, divisionCd);							//COMP_CD
				insContractMasterStmt.setString	(++bi, divisionCd);									//COMP_NM
				insContractMasterStmt.setString	(++bi, cntrTitle);							//CNTRT_NM
				insContractMasterStmt.setString	(++bi, cntrTitle);							//CNTRT_NM_EN
				insContractMasterStmt.setString	(++bi, "C01802");							//REGION_GBN	: 해외
				insContractMasterStmt.setString	(++bi, customerCd);							//CNTRT_OPPNT_CD
				insContractMasterStmt.setString	(++bi, contRs.getString("CMPYNM"));			//CNTRT_OPPNT_NM
				insContractMasterStmt.setString	(++bi, "C05699");							//CNTRT_OPPNT_TYPE	: 기타
				insContractMasterStmt.setString	(++bi, bizClsfcn);							//BIZ_CLSFCN
				insContractMasterStmt.setString	(++bi, depthClsfcn);						//DEPTH_CLSFCN
				insContractMasterStmt.setString	(++bi, cnclsnpurpsBigclsfcn);				//CNCLSNPURPS_BIGCLSFCN
				insContractMasterStmt.setString	(++bi, cnclsnpurpsMidclsfcn);				//CNCLSNPURPS_MIDCLSFCN
				insContractMasterStmt.setString	(++bi, cnclsnpurpsMidclsfcn + "999");		//CNTRT_TRGT
				insContractMasterStmt.setString	(++bi, reqCont);							//PSHDBKGRND_PURPS
				insContractMasterStmt.setString	(++bi, paymentGbn);							//PAYMENT_GBN
				insContractMasterStmt.setString	(++bi, contRs.getString("FRDT"));			//CNTRTPERIOD_STARTDAY
				insContractMasterStmt.setString	(++bi, contRs.getString("TODT"));			//CNTRTPERIOD_ENDDAY
				insContractMasterStmt.setString	(++bi, untPrc);								//CNTRT_UNTPRC
				insContractMasterStmt.setString	(++bi, contRs.getString("PRECURR"));		//CNTRT_UNTPRC_EXPL
				insContractMasterStmt.setString	(++bi, contRs.getString("CNTRAMT"));		//CNTRT_AMT
				insContractMasterStmt.setString	(++bi, contRs.getString("CURR"));			//CRRNCY_UNIT
				insContractMasterStmt.setString	(++bi, contRs.getString("PRODUCT"));		//ETC_MAIN_CONT
				insContractMasterStmt.setString	(++bi, "N");								//AUTO_RNEW_YN
				insContractMasterStmt.setString	(++bi, cntrtCnclsnday);						//CNCLSN_PLNDDAY
				insContractMasterStmt.setString	(++bi, reqManJuno);							//CNTRT_RESPMAN_ID
				insContractMasterStmt.setString	(++bi, reqManJuno);							//CNTRT_RESPMAN_ID
				insContractMasterStmt.setString	(++bi, reqManNm);							//CNTRT_RESPMAN_NM
				insContractMasterStmt.setString	(++bi, reqDept);							//CNTRT_RESP_DEPT
				insContractMasterStmt.setString	(++bi, reqDeptNm);							//CNTRT_RESP_DEPT_NM
				insContractMasterStmt.setString	(++bi, reqDept);							//FST_CNTRT_RESP_DEPT
				insContractMasterStmt.setString	(++bi, "C02102");							//SEAL_MTHD	: 서명
				insContractMasterStmt.setString	(++bi, cntrtCnclsnYn);						//CNTRT_CNCLSN_YN
				insContractMasterStmt.setString	(++bi, cntrtCnclsnday);						//CNTRT_CNCLSNDAY
				insContractMasterStmt.setString	(++bi, cntrtNo);							//CNTRT_NO
				insContractMasterStmt.setString	(++bi, cntrtStatus);						//CNTRT_STATUS
				insContractMasterStmt.setString	(++bi, "C02506");							//PRCS_DEPTH	: 구법무이관
				insContractMasterStmt.setString	(++bi, depthStatus);						//DEPTH_STATUS
				insContractMasterStmt.setString	(++bi, divisionCd);							//REG_OPERDIV
				insContractMasterStmt.setString	(++bi, reqDt);								//REG_DT
				insContractMasterStmt.setString	(++bi, reqManJuno);							//REG_ID
				insContractMasterStmt.setString	(++bi, reqManJuno);							//REG_ID
				insContractMasterStmt.setString	(++bi, reqManNm);							//REG_NM
				insContractMasterStmt.setString	(++bi, "C05402");							//CNTRT_INFO_GBN	: 구 법무시스템 데이터
				insContractMasterStmt.setString	(++bi, mState);								//SELMS_STATE
				System.out.println("=================pass xx 002=");
				System.out.println("VALUES==="+cntrtId+"','"+ divisionCd+"','"+divisionCd+"','"+cntrTitle+"','"+cntrTitle
				+"','"+ "C01802"+"','"+ customerCd+"','"+ contRs.getString("CMPYNM")+"','"+ "C05699"+"','"+ bizClsfcn
				+"','"+ depthClsfcn+"','"+cnclsnpurpsBigclsfcn+"','"+ cnclsnpurpsMidclsfcn+"','"+ cnclsnpurpsMidclsfcn + "999"
				+"','"+reqCont+"','"+paymentGbn+"','"+contRs.getString("FRDT")+"','"+ contRs.getString("TODT")
				+"','"+ untPrc+"','"+ contRs.getString("PRECURR")+"','"+ contRs.getString("CNTRAMT")
				+"','"+ contRs.getString("CURR")+"','"+ contRs.getString("PRODUCT")+"','"+ "N"+"','"+cntrtCnclsnday
				+"','"+ reqManJuno+"','"+ reqManJuno+"','"+ reqManNm+"','"+ reqDept+"','"+ reqDeptNm+"','"+ reqDept
				+"','"+ "C02102"+"','"+ cntrtCnclsnYn+"','"+ cntrtCnclsnday+"','"+ cntrtNo+"','"+ cntrtStatus
				+"','"+"C02506"+"','"+ depthStatus+"','"+ divisionCd+"','"+ reqDt+"','"+ reqManJuno+"','"+ reqManJuno
				+"','"+ reqManNm+"','"+ "C05402'");
				sqlRet = insContractMasterStmt.executeUpdate();
				System.out.println("=================pass xx 003=");			
				if(sqlRet == 1) {
					bi = 0;
					reqHistStmt.setString(++bi, cntrNo);
					System.out.println("=================pass xx 003-1=");		
					reqHistRs = reqHistStmt.executeQuery();
					System.out.println("=================pass xx 003-2=");
					if(reqHistRs.next()) {
						//이력 기준으로 의뢰 데이터 생성
						addFlag = true;
						cnsdReqId = "";
						prevCnsdReqId = "";
						fstCnsdReqId = "";
						cnsdLevel = 0;
						if(!"".equals(respMan1Userid)) {
							mnRespManApntYn = "Y";
						} else {
							mnRespManApntYn = "N";
						}
						
						do {
							sState = strNull(reqHistRs.getString("STATE"), "");
							histNo = reqHistRs.getInt("HISTNO");
							if("S03".equals(sState) || "S08".equals(sState) || "S09".equals(sState)) {		// 회신(S03), 결재상신(S08), 임시저장(S09)
								if(addFlag) {
									// 최초의뢰에 대한 회신, 회신 후 추가회신, 의뢰인이 보류하였는데도 회신한 케이스
									if("NULL".equals(customerCd)) {
										cnsdReqId = cntrNo + customerCd + "_" + String.format("%02d", histNo);
									} else {
										if(customerCd.length()<=3){
											cnsdReqId = cntrNo + "_000";
										}else if(customerCd.length()<=5){	
											cnsdReqId = cntrNo + customerCd.substring(3,customerCd.length()) + "_" + String.format("%02d", histNo);
										}else{
											cnsdReqId = cntrNo + customerCd.substring(3,6) + "_" + String.format("%02d", histNo);
										}
									}
									if("".equals(fstCnsdReqId)) {
										fstCnsdReqId = cnsdReqId;
									}
									System.out.println("=================pass xx 003-3 cnsdReqId="+cnsdReqId);		
									cnsdManId = strNull(reqHistRs.getString("USERID"), "");
									cnsdManNm = strNull(reqHistRs.getString("REGMANNM"), "");
									cnsdDt = strNull(reqHistRs.getString("REGDT"), "");
									mainMatrCont = strNull(reqHistRs.getString("RESCONTENT"), "");
									cnsdOpnn = strNull(reqHistRs.getString("REGCONT"), "");
									apbtManId = respHeadJuno;
									apbtManNm = respHead;
									apbtDt = strNull(reqHistRs.getString("REGDT"), "");
									apbtOpnn = strNull(reqHistRs.getString("DEPTCONT"), "");
									
									if("S03".equals(sState)) {
										sPrgrsStatus = "C04207";
										cnsdStatus = "C04305";
									} else if("S08".equals(sState)) {
										sPrgrsStatus = "C04205";
										cnsdStatus = "C04303";
									} else {
										sPrgrsStatus = "C04204";
										cnsdStatus = "C04302";
									}
									
									//System.out.println("cntrtId = " + cntrtId + "\tcnsdReqId = " + cnsdReqId);

									// TN_CLM_CONTRACT_CNSD 등록
									bi = 0;
									insContractCnsdStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
									insContractCnsdStmt.setString (++bi, cntrtId);			// CNTRT_ID
									insContractCnsdStmt.setString (++bi, divisionCd);		// COMP_CD
									insContractCnsdStmt.setString (++bi, divisionCd);		// COMP_NM
									insContractCnsdStmt.setString (++bi, cnsdManId);		// CNSDMAN_ID
									insContractCnsdStmt.setString (++bi, cnsdManId);		// CNSDMAN_ID
									insContractCnsdStmt.setString (++bi, cnsdManNm);		// CNSDMAN_NM
									insContractCnsdStmt.setString (++bi, cnsdDt);			// CNSD_DT
									insContractCnsdStmt.setString (++bi, cnsdDt);			// CNSD_DT
									insContractCnsdStmt.setString (++bi, mainMatrCont);		// CNSD_MEMO
									insContractCnsdStmt.setString (++bi, cnsdOpnn);			// CNSD_OPNN
									insContractCnsdStmt.setString (++bi, apbtManId);		// APBTMAN_ID
									insContractCnsdStmt.setString (++bi, apbtManId);		// APBTMAN_ID
									insContractCnsdStmt.setString (++bi, apbtManNm);		// APBTMAN_NM
									insContractCnsdStmt.setString (++bi, apbtDt);			// APBT_DT
									insContractCnsdStmt.setString (++bi, apbtDt);			// APBT_DT
									insContractCnsdStmt.setString (++bi, apbtOpnn);			// APBT_OPNN
									insContractCnsdStmt.setString (++bi, cnsdStatus);		// CNSD_STATUS
									insContractCnsdStmt.setString (++bi, reqDt);			// REG_DT
									insContractCnsdStmt.setString (++bi, reqManJuno);		// REG_ID
									insContractCnsdStmt.setString (++bi, reqManJuno);		// REG_ID
									insContractCnsdStmt.setString (++bi, reqManNm);			// REG_NM
									System.out.println("=================pass xx 004=");		
									sqlRet = insContractCnsdStmt.executeUpdate();
									System.out.println("=================pass xx 005=");	
									if(sqlRet == 1) {
										// TN_CLM_CONT_CNSDREQ 등록
										bi = 0;
										insContCnsdReqStmt.setString (++bi, cnsdReqId);											// CNSDREQ_ID
										insContCnsdReqStmt.setString (++bi, divisionCd);										// COMP_CD
										insContCnsdReqStmt.setString (++bi, divisionCd);										// COMP_NM
										insContCnsdReqStmt.setString (++bi, "A00000001");										// MN_CNSD_DEPT
										insContCnsdReqStmt.setString (++bi, mnRespManApntYn);									// MN_RESPMAN_APNT_YN
										insContCnsdReqStmt.setString (++bi, "F");												// DMSTFRGN_GBN
										insContCnsdReqStmt.setString (++bi, prevCnsdReqId);										// PREV_CNSDREQ_ID
										insContCnsdReqStmt.setString (++bi, strNull(reqHistRs.getString("CNTRTITLE"), ""));		// REQ_TITLE
										insContCnsdReqStmt.setString (++bi, reqManJuno);										// REQMAN_ID
										insContCnsdReqStmt.setString (++bi, reqManJuno);										// REQMAN_ID
										insContCnsdReqStmt.setString (++bi, reqManNm);											// REQMAN_NM
										insContCnsdReqStmt.setString (++bi, divisionCd);										// REQ_OPERDIV
										insContCnsdReqStmt.setString (++bi, reqDept);											// REQ_DEPT
										insContCnsdReqStmt.setString (++bi, reqDeptNm);											// REQ_DEPT_NM
										insContCnsdReqStmt.setString (++bi, reqDt);												// REQ_DT
										insContCnsdReqStmt.setString (++bi, reqDept);											// FST_REQ_DEPT
										insContCnsdReqStmt.setString (++bi, reqDt);												// ACPT_DT
										insContCnsdReqStmt.setString (++bi, reqCont);											// CNSD_DEMND_CONT
										insContCnsdReqStmt.setString (++bi, sPrgrsStatus);										// PRGRS_STATUS
										insContCnsdReqStmt.setString (++bi, cnsdHoldCause);										// CNSD_HOLD_CAUSE
										insContCnsdReqStmt.setString (++bi, cnsdDt);											// RE_DT
										insContCnsdReqStmt.setString (++bi, cnsdDt);											// RE_DT
										insContCnsdReqStmt.setString (++bi, cnsdDt);											// REG_DT
										insContCnsdReqStmt.setString (++bi, reqManJuno);										// REG_ID
										insContCnsdReqStmt.setString (++bi, reqManJuno);										// REG_ID
										insContCnsdReqStmt.setString (++bi, reqManNm);											// REG_NM
										insContCnsdReqStmt.setInt	 (++bi, ++cnsdLevel);										// CNSD_LEVEL
										insContCnsdReqStmt.setString (++bi, fstCnsdReqId);										// FST_CNSDREQ_ID
										System.out.println("=================pass xx 006=");	
										sqlRet = insContCnsdReqStmt.executeUpdate();
										System.out.println("=================pass xx 007=");	
										if(sqlRet != 1) {
											System.out.print(rsRow + "\t");
											System.out.print(cntrNo);
											System.out.print("\t" + cntrtId);
											System.out.print("\t" + cnsdReqId);
						
											System.out.print("\t==> TN_CLM_CONT_CNSDREQ 등록 실패");
											System.out.println("");
										}
										
										// TN_CLM_CONTRACT_DEPTCNSD 등록
										bi = 0;
										insContractDeptCnsdStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
										insContractDeptCnsdStmt.setString (++bi, cntrtId);			// CNTRT_ID
										insContractDeptCnsdStmt.setString (++bi, divisionCd);		// COMP_CD
										insContractDeptCnsdStmt.setString (++bi, divisionCd);				// COMP_NM
										insContractDeptCnsdStmt.setString (++bi, "A00000001");		// CNSD_DEPT
										insContractDeptCnsdStmt.setString (++bi, cnsdManId);		// CNSDMAN_ID
										insContractDeptCnsdStmt.setString (++bi, cnsdManId);		// CNSDMAN_ID
										insContractDeptCnsdStmt.setString (++bi, cnsdManNm);		// CNSDMAN_NM
										insContractDeptCnsdStmt.setString (++bi, cnsdDt);			// CNSD_DT
										insContractDeptCnsdStmt.setString (++bi, cnsdDt);			// CNSD_DT
										insContractDeptCnsdStmt.setString (++bi, cnsdOpnn);			// CNSD_OPNN
										insContractDeptCnsdStmt.setString (++bi, mainMatrCont);		// MAIN_MATR_CONT
										insContractDeptCnsdStmt.setString (++bi, apbtManId);		// APBTMAN_ID
										insContractDeptCnsdStmt.setString (++bi, apbtManId);		// APBTMAN_ID
										insContractDeptCnsdStmt.setString (++bi, apbtManNm);		// APBTMAN_NM
										insContractDeptCnsdStmt.setString (++bi, apbtDt);			// APBT_DT
										insContractDeptCnsdStmt.setString (++bi, apbtDt);			// APBT_DT
										insContractDeptCnsdStmt.setString (++bi, apbtOpnn);			// APBT_OPNN
										insContractDeptCnsdStmt.setString (++bi, cnsdStatus);		// CNSD_STATUS
										System.out.println("=================pass xx 008=");	
										sqlRet = insContractDeptCnsdStmt.executeUpdate();
										System.out.println("=================pass xx 009=");	
										if(sqlRet != 1) {
											System.out.print(rsRow + "\t");
											System.out.print(cntrNo);
											System.out.print("\t" + cntrtId);
											System.out.print("\t" + cnsdReqId);
						
											System.out.print("\t==> TN_CLM_CONTRACT_DEPTCNSD 등록 실패");
											System.out.println("");
										}
										
										if(!"".equals(respMan1Userid)) {
											// TN_CLM_CONT_CNSDREQ_RESPMAN 등록
											bi = 0;
											insCnsdReqRespManStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
											insCnsdReqRespManStmt.setString (++bi, respMan1Userid);	// RESPMAN_ID
											insCnsdReqRespManStmt.setString (++bi, respMan1Userid);	// RESPMAN_ID
											insCnsdReqRespManStmt.setString (++bi, respMan1);		// RESPMAN_NM
											insCnsdReqRespManStmt.setString (++bi, "A00000001");	// RESP_DEPT
											insCnsdReqRespManStmt.setString (++bi, reqDt);			// ASGN_DT
											insCnsdReqRespManStmt.setString (++bi, respHeadJuno);	// ASGNMAN_ID
											insCnsdReqRespManStmt.setString (++bi, respHeadJuno);	// ASGNMAN_ID
											insCnsdReqRespManStmt.setString (++bi, respHead);		// ASGNMAN_NM
											insCnsdReqRespManStmt.setString (++bi, "Y");			// MAIN_CNSD_YN
											insCnsdReqRespManStmt.setString (++bi, "N");			// AUTO_APBT_YN
											insCnsdReqRespManStmt.setString (++bi, orderResman);	// APBT_MEMO
											System.out.println("=================pass xx 010=");
											sqlRet = insCnsdReqRespManStmt.executeUpdate();
											System.out.println("=================pass xx 011=");
											if(sqlRet != 1) {
												System.out.print(rsRow + "\t");
												System.out.print(cntrNo);
												System.out.print("\t" + cntrtId);
												System.out.print("\t" + cnsdReqId);
							
												System.out.print("\t==> TN_CLM_CONT_CNSDREQ_RESPMAN 1 등록 실패");
												System.out.println("");
											}
										}
										System.out.println("=================pass xx 011= 001");
										if(!"".equals(respMan2Userid) && !respMan1Userid.equals(respMan2Userid)) {
											// TN_CLM_CONT_CNSDREQ_RESPMAN 등록
											bi = 0;
											insCnsdReqRespManStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
											insCnsdReqRespManStmt.setString (++bi, respMan2Userid);	// RESPMAN_ID
											insCnsdReqRespManStmt.setString (++bi, respMan2Userid);	// RESPMAN_ID
											insCnsdReqRespManStmt.setString (++bi, respMan2);		// RESPMAN_NM
											insCnsdReqRespManStmt.setString (++bi, "A00000001");	// RESP_DEPT
											insCnsdReqRespManStmt.setString (++bi, reqDt);			// ASGN_DT
											insCnsdReqRespManStmt.setString (++bi, respHeadJuno);	// ASGNMAN_ID
											insCnsdReqRespManStmt.setString (++bi, respHeadJuno);	// ASGNMAN_ID
											insCnsdReqRespManStmt.setString (++bi, respHead);		// ASGNMAN_NM
											insCnsdReqRespManStmt.setString (++bi, "N");			// MAIN_CNSD_YN
											insCnsdReqRespManStmt.setString (++bi, "N");			// AUTO_APBT_YN
											insCnsdReqRespManStmt.setString (++bi, orderResman);	// APBT_MEMO
											System.out.println("=================pass xx 012=");
											sqlRet = insCnsdReqRespManStmt.executeUpdate();
											System.out.println("=================pass xx 013=");
											if(sqlRet != 1) {
												System.out.print(rsRow + "\t");
												System.out.print(cntrNo);
												System.out.print("\t" + cntrtId);
												System.out.print("\t" + cnsdReqId);
							
												System.out.print("\t==> TN_CLM_CONT_CNSDREQ_RESPMAN 2 등록 실패");
												System.out.println("");
											}
										}
										System.out.println("=================pass xx 011= 002");
										if(!"".equals(respMan3Userid) && !respMan1Userid.equals(respMan3Userid) && !respMan2Userid.equals(respMan3Userid)) {
											// TN_CLM_CONT_CNSDREQ_RESPMAN 등록
											bi = 0;
											insCnsdReqRespManStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
											insCnsdReqRespManStmt.setString (++bi, respMan3Userid);	// RESPMAN_ID
											insCnsdReqRespManStmt.setString (++bi, respMan3Userid);	// RESPMAN_ID
											insCnsdReqRespManStmt.setString (++bi, respMan3);		// RESPMAN_NM
											insCnsdReqRespManStmt.setString (++bi, "A00000001");	// RESP_DEPT
											insCnsdReqRespManStmt.setString (++bi, reqDt);			// ASGN_DT
											insCnsdReqRespManStmt.setString (++bi, respHeadJuno);	// ASGNMAN_ID
											insCnsdReqRespManStmt.setString (++bi, respHeadJuno);	// ASGNMAN_ID
											insCnsdReqRespManStmt.setString (++bi, respHead);		// ASGNMAN_NM
											insCnsdReqRespManStmt.setString (++bi, "N");			// MAIN_CNSD_YN
											insCnsdReqRespManStmt.setString (++bi, "N");			// AUTO_APBT_YN
											insCnsdReqRespManStmt.setString (++bi, orderResman);	// APBT_MEMO
											System.out.println("=================pass xx 014=");	
											sqlRet = insCnsdReqRespManStmt.executeUpdate();
											System.out.println("=================pass xx 015=");
											if(sqlRet != 1) {
												System.out.print(rsRow + "\t");
												System.out.print(cntrNo);
												System.out.print("\t" + cntrtId);
												System.out.print("\t" + cnsdReqId);
							
												System.out.print("\t==> TN_CLM_CONT_CNSDREQ_RESPMAN 3 등록 실패");
												System.out.println("");
											}
										}
										System.out.println("=================pass xx 011= 003");
									} else {
										System.out.print(rsRow + "\t");
										System.out.print(cntrNo);
										System.out.print("\t" + cntrtId);
										System.out.print("\t" + cnsdReqId);
					
										System.out.print("\t==> TN_CLM_CONTRACT_CNSD 등록 실패");
										System.out.println("");
									}
								} else {
									// 재의뢰에 대한 회신 등 완료가 안된 회신이 존재하는데 회신의 경우
									// 기존에 등록된 의뢰에 대해 회신정보만 업데이트
									
									cnsdManId = strNull(reqHistRs.getString("USERID"), "");
									cnsdManNm = strNull(reqHistRs.getString("REGMANNM"), "");
									cnsdDt = strNull(reqHistRs.getString("REGDT"), "");
									mainMatrCont = strNull(reqHistRs.getString("RESCONTENT"), "");
									cnsdOpnn = strNull(reqHistRs.getString("REGCONT"), "");
									apbtManId = respHeadJuno;
									apbtManNm = respHead;
									apbtDt = strNull(reqHistRs.getString("REGDT"), "");
									apbtOpnn = strNull(reqHistRs.getString("DEPTCONT"), "");
									
									cnsdStatus = "C04305";
									
									System.out.println("cntrtId = " + cntrtId + "\tcnsdReqId = " + cnsdReqId);

									// TN_CLM_CONTRACT_CNSD 수정
									bi = 0;
									uptContractCnsdStmt.setString (++bi, cnsdManId);		// CNSDMAN_ID
									uptContractCnsdStmt.setString (++bi, cnsdManId);		// CNSDMAN_ID
									uptContractCnsdStmt.setString (++bi, cnsdManNm);		// CNSDMAN_NM
									uptContractCnsdStmt.setString (++bi, cnsdDt);			// CNSD_DT
									uptContractCnsdStmt.setString (++bi, mainMatrCont);		// CNSD_MEMO
									uptContractCnsdStmt.setString (++bi, cnsdOpnn);			// CNSD_OPNN
									uptContractCnsdStmt.setString (++bi, apbtManId);		// APBTMAN_ID
									uptContractCnsdStmt.setString (++bi, apbtManId);		// APBTMAN_ID
									uptContractCnsdStmt.setString (++bi, apbtManNm);		// APBTMAN_NM
									uptContractCnsdStmt.setString (++bi, apbtDt);			// APBT_DT
									uptContractCnsdStmt.setString (++bi, apbtOpnn);			// APBT_OPNN
									uptContractCnsdStmt.setString (++bi, cnsdStatus);		// CNSD_STATUS
									uptContractCnsdStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
									uptContractCnsdStmt.setString (++bi, cntrtId);			// CNTRT_ID
									
									sqlRet = uptContractCnsdStmt.executeUpdate();

									if(sqlRet != 1) {
										System.out.print(rsRow + "\t");
										System.out.print(cntrNo);
										System.out.print("\t" + cntrtId);
										System.out.print("\t" + cnsdReqId);
					
										System.out.print("\t==> TN_CLM_CONTRACT_CNSD 수정 실패");
										System.out.println("");
									}

									// TN_CLM_CONT_CNSDREQ 수정
									bi = 0;
									uptContCnsdReqStmt.setString (++bi, "C04207");		// PRGRS_STATUS
									uptContCnsdReqStmt.setString (++bi, cnsdDt);		// RE_DT
									uptContCnsdReqStmt.setString (++bi, cnsdDt);		// RE_DT
									uptContCnsdReqStmt.setString (++bi, "");			// CNSD_HOLD_CAUSE
									uptContCnsdReqStmt.setString (++bi, "");			// CNSD_HOLD_CAUSE
									uptContCnsdReqStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
									
									sqlRet = uptContCnsdReqStmt.executeUpdate();

									if(sqlRet != 1) {
										System.out.print(rsRow + "\t");
										System.out.print(cntrNo);
										System.out.print("\t" + cntrtId);
										System.out.print("\t" + cnsdReqId);
					
										System.out.print("\t==> TN_CLM_CONT_CNSDREQ 수정 실패");
										System.out.println("");
									}
									
									// TN_CLM_CONTRACT_DEPTCNSD 존재 확인
									bi = 0;
									extContractDeptCnsdStmt.setString (++bi, cnsdReqId);	// CNSDREQ_ID
									extContractDeptCnsdStmt.setString (++bi, cntrtId);		// CNTRT_ID
									extContractDeptCnsdStmt.setString (++bi, "A00000001");	// CNSD_DEPT
									
									extContractDeptCnsdRs = extContractDeptCnsdStmt.executeQuery();
									
									if(extContractDeptCnsdRs.next()) {
										// TN_CLM_CONTRACT_DEPTCNSD 수정
										bi = 0;

										uptContractDeptCnsdStmt.setString (++bi, cnsdManId);		// CNSDMAN_ID
										uptContractDeptCnsdStmt.setString (++bi, cnsdManId);		// CNSDMAN_ID
										uptContractDeptCnsdStmt.setString (++bi, cnsdManNm);		// CNSDMAN_NM
										uptContractDeptCnsdStmt.setString (++bi, cnsdDt);			// CNSD_DT
										uptContractDeptCnsdStmt.setString (++bi, cnsdOpnn);			// CNSD_OPNN
										uptContractDeptCnsdStmt.setString (++bi, mainMatrCont);		// MAIN_MATR_CONT
										uptContractDeptCnsdStmt.setString (++bi, apbtManId);		// APBTMAN_ID
										uptContractDeptCnsdStmt.setString (++bi, apbtManId);		// APBTMAN_ID
										uptContractDeptCnsdStmt.setString (++bi, apbtManNm);		// APBTMAN_NM
										uptContractDeptCnsdStmt.setString (++bi, apbtDt);			// APBT_DT
										uptContractDeptCnsdStmt.setString (++bi, apbtOpnn);			// APBT_OPNN
										uptContractDeptCnsdStmt.setString (++bi, cnsdStatus);		// CNSD_STATUS
										uptContractDeptCnsdStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
										uptContractDeptCnsdStmt.setString (++bi, cntrtId);			// CNTRT_ID
										uptContractDeptCnsdStmt.setString (++bi, "A00000001");		// CNSD_DEPT
										
										sqlRet = uptContractDeptCnsdStmt.executeUpdate();
		
										if(sqlRet != 1) {
											System.out.print(rsRow + "\t");
											System.out.print(cntrNo);
											System.out.print("\t" + cntrtId);
											System.out.print("\t" + cnsdReqId);
						
											System.out.print("\t==> TN_CLM_CONTRACT_DEPTCNSD 수정 실패");
											System.out.println("");
										}
									} else {
										// TN_CLM_CONTRACT_DEPTCNSD 등록
										bi = 0;
										insContractDeptCnsdStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
										insContractDeptCnsdStmt.setString (++bi, cntrtId);			// CNTRT_ID
										insContractDeptCnsdStmt.setString (++bi, divisionCd);		// COMP_CD
										insContractDeptCnsdStmt.setString (++bi, divisionCd);				// COMP_NM
										insContractDeptCnsdStmt.setString (++bi, "A00000001");		// CNSD_DEPT
										insContractDeptCnsdStmt.setString (++bi, cnsdManId);		// CNSDMAN_ID
										insContractDeptCnsdStmt.setString (++bi, cnsdManId);		// CNSDMAN_ID
										insContractDeptCnsdStmt.setString (++bi, cnsdManNm);		// CNSDMAN_NM
										insContractDeptCnsdStmt.setString (++bi, cnsdDt);			// CNSD_DT
										insContractDeptCnsdStmt.setString (++bi, cnsdDt);			// CNSD_DT
										insContractDeptCnsdStmt.setString (++bi, cnsdOpnn);			// CNSD_OPNN
										insContractDeptCnsdStmt.setString (++bi, mainMatrCont);		// MAIN_MATR_CONT
										insContractDeptCnsdStmt.setString (++bi, apbtManId);		// APBTMAN_ID
										insContractDeptCnsdStmt.setString (++bi, apbtManId);		// APBTMAN_ID
										insContractDeptCnsdStmt.setString (++bi, apbtManNm);		// APBTMAN_NM
										insContractDeptCnsdStmt.setString (++bi, apbtDt);			// APBT_DT
										insContractDeptCnsdStmt.setString (++bi, apbtDt);			// APBT_DT
										insContractDeptCnsdStmt.setString (++bi, apbtOpnn);			// APBT_OPNN
										insContractDeptCnsdStmt.setString (++bi, cnsdStatus);		// CNSD_STATUS
										System.out.println("=================pass xx 020=");	
										sqlRet = insContractDeptCnsdStmt.executeUpdate();
										System.out.println("=================pass xx 021=");	
										if(sqlRet != 1) {
											System.out.print(rsRow + "\t");
											System.out.print(cntrNo);
											System.out.print("\t" + cntrtId);
											System.out.print("\t" + cnsdReqId);
						
											System.out.print("\t==> TN_CLM_CONTRACT_DEPTCNSD 등록 실패");
											System.out.println("");
										}
									}
								}
								System.out.println("=================pass xx 011= 004");
								addFlag = true;
								prevCnsdReqId = cnsdReqId;
							} else if("S04".equals(sState) || "S10".equals(sState)) {		// 재의뢰(S04), 재의뢰 임시저장(S10)
								//System.out.println("prevCnsdReqId = [" + prevCnsdReqId + "],\tfstCnsdReqId = [" + fstCnsdReqId + "]");

								if(!addFlag) {
									// 재의뢰인데 앞의 의뢰가 완료(회신, 보류)가 안된 케이스
									// 강제로 앞의 의뢰는 의뢰자 보류처리로 변경 후 재의뢰 등록
									cnsdHoldCause = "Auto Holding";

									// TN_CLM_CONT_CNSDREQ 수정
									bi = 0;
									uptContCnsdReqStmt.setString (++bi, "C04210");		// PRGRS_STATUS
									uptContCnsdReqStmt.setString (++bi, "");			// RE_DT
									uptContCnsdReqStmt.setString (++bi, "");			// RE_DT
									uptContCnsdReqStmt.setString (++bi, cnsdHoldCause);	// CNSD_HOLD_CAUSE
									uptContCnsdReqStmt.setString (++bi, cnsdHoldCause);	// CNSD_HOLD_CAUSE
									uptContCnsdReqStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
									System.out.println("=================pass xx 022=");		
									sqlRet = uptContCnsdReqStmt.executeUpdate();
									System.out.println("=================pass xx 023=");	
									if(sqlRet != 1) {
										System.out.print(rsRow + "\t");
										System.out.print(cntrNo);
										System.out.print("\t" + cntrtId);
										System.out.print("\t" + cnsdReqId);
					
										System.out.print("\t==> TN_CLM_CONT_CNSDREQ 수정 실패");
										System.out.println("");
									}
									
									// 데이터이관 시에는 동일한 의뢰ID에 대해 2개 이상 보류사유가 들어가는 경우가 없으므로 중복 체크 후 등록
									bi = 0;
									extContCnsdReqHoldStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
									System.out.println("=================pass xx 024=");		
									extContCnsdReqHoldRs = extContCnsdReqHoldStmt.executeQuery();
									System.out.println("=================pass xx 025=");	
									if(!extContCnsdReqHoldRs.next()) {
										// 보류 사유 기록
										reqDt = strNull(reqHistRs.getString("REGDT"), "");
										reqManJuno = strNull(reqHistRs.getString("USERID"), "");
										reqManNm = strNull(reqHistRs.getString("REGMANNM"), "");

										// TN_CLM_CONT_CNSDREQ_HOLD 등록
										bi = 0;
										insContCnsdReqHoldStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
										insContCnsdReqHoldStmt.setInt	 (++bi, 1);				// HOLD_SEQNO
										insContCnsdReqHoldStmt.setString (++bi, reqDt);			// HOLD_STARTDAY
										insContCnsdReqHoldStmt.setString (++bi, "");			// HOLD_ENDDAY
										insContCnsdReqHoldStmt.setString (++bi, "");			// HOLD_ENDDAY
										insContCnsdReqHoldStmt.setString (++bi, cnsdHoldCause);	// HOLD_CAUSE
										insContCnsdReqHoldStmt.setString (++bi, reqDt);			// REG_DT
										insContCnsdReqHoldStmt.setString (++bi, reqManJuno);	// REG_ID
										insContCnsdReqHoldStmt.setString (++bi, reqManJuno);	// REG_ID
										insContCnsdReqHoldStmt.setString (++bi, reqManNm);		// REG_NM
										System.out.println("=================pass xx 026=");	
										sqlRet = insContCnsdReqHoldStmt.executeUpdate();
										System.out.println("=================pass xx 027=");	
										if(sqlRet != 1) {
											System.out.print(rsRow + "\t");
											System.out.print(cntrNo);
											System.out.print("\t" + cntrtId);
											System.out.print("\t" + cnsdReqId);
						
											System.out.print("\t==> TN_CLM_CONT_CNSDREQ_HOLD 등록 실패");
											System.out.println("");
										}
									}

									prevCnsdReqId = cnsdReqId;
								}

								if("NULL".equals(customerCd)) {
									cnsdReqId = cntrNo + customerCd + "_" + String.format("%02d", histNo);
								} else {
									if(customerCd.length()<=3){
										cnsdReqId = cntrNo + "_000";
									}else if(customerCd.length()<=5){	
										cnsdReqId = cntrNo + customerCd.substring(3,customerCd.length()) + "_" + String.format("%02d", histNo);
									}else{
										cnsdReqId = cntrNo + customerCd.substring(3,6) + "_" + String.format("%02d", histNo);
									}
								}
								if("".equals(fstCnsdReqId)) {
									fstCnsdReqId = cnsdReqId;
								}
								
								cnsdManId = "";
								cnsdManNm = "";
								cnsdDt = "";
								mainMatrCont = "";
								cnsdOpnn = "";
								apbtManId = "";
								apbtManNm = "";
								apbtDt = "";
								apbtOpnn = "";
								reqDt = strNull(reqHistRs.getString("REGDT"), "");
								reqManJuno = strNull(reqHistRs.getString("USERID"), "");
								reqManNm = strNull(reqHistRs.getString("REGMANNM"), "");
								
								if("S04".equals(sState)) {
									sPrgrsStatus = "C04203";
								} else {
									sPrgrsStatus = "C04201";
								}
								cnsdStatus = "C04301";
								
								System.out.println("cntrtId = " + cntrtId + "\tcnsdReqId = " + cnsdReqId);

								// TN_CLM_CONTRACT_CNSD 등록
								bi = 0;
								insContractCnsdStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
								insContractCnsdStmt.setString (++bi, cntrtId);			// CNTRT_ID
								insContractCnsdStmt.setString (++bi, divisionCd);		// COMP_CD
								insContractCnsdStmt.setString (++bi, divisionCd);		// COMP_NM
								insContractCnsdStmt.setString (++bi, cnsdManId);		// CNSDMAN_ID
								insContractCnsdStmt.setString (++bi, cnsdManId);		// CNSDMAN_ID
								insContractCnsdStmt.setString (++bi, cnsdManNm);		// CNSDMAN_NM
								insContractCnsdStmt.setString (++bi, cnsdDt);			// CNSD_DT
								insContractCnsdStmt.setString (++bi, cnsdDt);			// CNSD_DT
								insContractCnsdStmt.setString (++bi, mainMatrCont);		// CNSD_MEMO
								insContractCnsdStmt.setString (++bi, cnsdOpnn);			// CNSD_OPNN
								insContractCnsdStmt.setString (++bi, apbtManId);		// APBTMAN_ID
								insContractCnsdStmt.setString (++bi, apbtManId);		// APBTMAN_ID
								insContractCnsdStmt.setString (++bi, apbtManNm);		// APBTMAN_NM
								insContractCnsdStmt.setString (++bi, apbtDt);			// APBT_DT
								insContractCnsdStmt.setString (++bi, apbtDt);			// APBT_DT
								insContractCnsdStmt.setString (++bi, apbtOpnn);			// APBT_OPNN
								insContractCnsdStmt.setString (++bi, cnsdStatus);		// CNSD_STATUS
								insContractCnsdStmt.setString (++bi, reqDt);			// REG_DT
								insContractCnsdStmt.setString (++bi, reqManJuno);		// REG_ID
								insContractCnsdStmt.setString (++bi, reqManJuno);		// REG_ID
								insContractCnsdStmt.setString (++bi, reqManNm);			// REG_NM
								System.out.println("=================pass xx 028=");	
								sqlRet = insContractCnsdStmt.executeUpdate();
								System.out.println("=================pass xx 029=");	
								if(sqlRet == 1) {
									reqDept = strNull(reqHistRs.getString("REGDEPT"), "");
									reqDeptNm = strNull(reqHistRs.getString("REGDEPTNM"), "");
									reqCont = strNull(reqHistRs.getString("REGCONT"), "");
									cnsdHoldCause = "";
									
									// TN_CLM_CONT_CNSDREQ 등록
									bi = 0;
									insContCnsdReqStmt.setString (++bi, cnsdReqId);											// CNSDREQ_ID
									insContCnsdReqStmt.setString (++bi, divisionCd);										// COMP_CD
									insContCnsdReqStmt.setString (++bi, divisionCd);										// COMP_NM
									insContCnsdReqStmt.setString (++bi, "A00000001");										// MN_CNSD_DEPT
									insContCnsdReqStmt.setString (++bi, mnRespManApntYn);									// MN_RESPMAN_APNT_YN
									insContCnsdReqStmt.setString (++bi, "F");												// DMSTFRGN_GBN
									insContCnsdReqStmt.setString (++bi, prevCnsdReqId);										// PREV_CNSDREQ_ID
									insContCnsdReqStmt.setString (++bi, strNull(reqHistRs.getString("CNTRTITLE"), ""));		// REQ_TITLE
									insContCnsdReqStmt.setString (++bi, reqManJuno);										// REQMAN_ID
									insContCnsdReqStmt.setString (++bi, reqManJuno);										// REQMAN_ID
									insContCnsdReqStmt.setString (++bi, reqManNm);											// REQMAN_NM
									insContCnsdReqStmt.setString (++bi, divisionCd);										// REQ_OPERDIV
									insContCnsdReqStmt.setString (++bi, reqDept);											// REQ_DEPT
									insContCnsdReqStmt.setString (++bi, reqDeptNm);											// REQ_DEPT_NM
									insContCnsdReqStmt.setString (++bi, reqDt);												// REQ_DT
									insContCnsdReqStmt.setString (++bi, reqDept);											// FST_REQ_DEPT
									insContCnsdReqStmt.setString (++bi, reqDt);												// ACPT_DT
									insContCnsdReqStmt.setString (++bi, reqCont);											// CNSD_DEMND_CONT
									insContCnsdReqStmt.setString (++bi, sPrgrsStatus);										// PRGRS_STATUS
									insContCnsdReqStmt.setString (++bi, cnsdHoldCause);										// CNSD_HOLD_CAUSE
									insContCnsdReqStmt.setString (++bi, cnsdDt);											// RE_DT
									insContCnsdReqStmt.setString (++bi, cnsdDt);											// RE_DT
									insContCnsdReqStmt.setString (++bi, cnsdDt);											// REG_DT
									insContCnsdReqStmt.setString (++bi, reqManJuno);										// REG_ID
									insContCnsdReqStmt.setString (++bi, reqManJuno);										// REG_ID
									insContCnsdReqStmt.setString (++bi, reqManNm);											// REG_NM
									insContCnsdReqStmt.setInt	 (++bi, ++cnsdLevel);										// CNSD_LEVEL
									insContCnsdReqStmt.setString (++bi, fstCnsdReqId);										// FST_CNSDREQ_ID
									System.out.println("=================pass xx 030=");	
									sqlRet = insContCnsdReqStmt.executeUpdate();
									System.out.println("=================pass xx 031=");
									if(sqlRet != 1) {
										System.out.print(rsRow + "\t");
										System.out.print(cntrNo);
										System.out.print("\t" + cntrtId);
										System.out.print("\t" + cnsdReqId);
					
										System.out.print("\t==> TN_CLM_CONT_CNSDREQ 등록 실패");
										System.out.println("");
									}
									
									if(!"".equals(respMan1Userid)) {
										// TN_CLM_CONT_CNSDREQ_RESPMAN 등록
										bi = 0;
										insCnsdReqRespManStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
										insCnsdReqRespManStmt.setString (++bi, respMan1Userid);	// RESPMAN_ID
										insCnsdReqRespManStmt.setString (++bi, respMan1Userid);	// RESPMAN_ID
										insCnsdReqRespManStmt.setString (++bi, respMan1);		// RESPMAN_NM
										insCnsdReqRespManStmt.setString (++bi, "A00000001");	// RESP_DEPT
										insCnsdReqRespManStmt.setString (++bi, reqDt);			// ASGN_DT
										insCnsdReqRespManStmt.setString (++bi, respHeadJuno);	// ASGNMAN_ID
										insCnsdReqRespManStmt.setString (++bi, respHeadJuno);	// ASGNMAN_ID
										insCnsdReqRespManStmt.setString (++bi, respHead);		// ASGNMAN_NM
										insCnsdReqRespManStmt.setString (++bi, "Y");			// MAIN_CNSD_YN
										insCnsdReqRespManStmt.setString (++bi, "N");			// AUTO_APBT_YN
										insCnsdReqRespManStmt.setString (++bi, orderResman);	// APBT_MEMO
										System.out.println("=================pass xx 032=");
										sqlRet = insCnsdReqRespManStmt.executeUpdate();
										System.out.println("=================pass xx 033=");
										if(sqlRet != 1) {
											System.out.print(rsRow + "\t");
											System.out.print(cntrNo);
											System.out.print("\t" + cntrtId);
											System.out.print("\t" + cnsdReqId);
						
											System.out.print("\t==> TN_CLM_CONT_CNSDREQ_RESPMAN 1 등록 실패");
											System.out.println("");
										}
									}
		
									if(!"".equals(respMan2Userid) && !respMan1Userid.equals(respMan2Userid)) {
										// TN_CLM_CONT_CNSDREQ_RESPMAN 등록
										bi = 0;
										insCnsdReqRespManStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
										insCnsdReqRespManStmt.setString (++bi, respMan2Userid);	// RESPMAN_ID
										insCnsdReqRespManStmt.setString (++bi, respMan2Userid);	// RESPMAN_ID
										insCnsdReqRespManStmt.setString (++bi, respMan2);		// RESPMAN_NM
										insCnsdReqRespManStmt.setString (++bi, "A00000001");	// RESP_DEPT
										insCnsdReqRespManStmt.setString (++bi, reqDt);			// ASGN_DT
										insCnsdReqRespManStmt.setString (++bi, respHeadJuno);	// ASGNMAN_ID
										insCnsdReqRespManStmt.setString (++bi, respHeadJuno);	// ASGNMAN_ID
										insCnsdReqRespManStmt.setString (++bi, respHead);		// ASGNMAN_NM
										insCnsdReqRespManStmt.setString (++bi, "N");			// MAIN_CNSD_YN
										insCnsdReqRespManStmt.setString (++bi, "N");			// AUTO_APBT_YN
										insCnsdReqRespManStmt.setString (++bi, orderResman);	// APBT_MEMO
										System.out.println("=================pass xx 034=");
										sqlRet = insCnsdReqRespManStmt.executeUpdate();
										System.out.println("=================pass xx 035=");
										if(sqlRet != 1) {
											System.out.print(rsRow + "\t");
											System.out.print(cntrNo);
											System.out.print("\t" + cntrtId);
											System.out.print("\t" + cnsdReqId);
						
											System.out.print("\t==> TN_CLM_CONT_CNSDREQ_RESPMAN 2 등록 실패");
											System.out.println("");
										}
									}
		
									if(!"".equals(respMan3Userid) && !respMan1Userid.equals(respMan3Userid) && !respMan2Userid.equals(respMan3Userid)) {
										// TN_CLM_CONT_CNSDREQ_RESPMAN 등록
										bi = 0;
										insCnsdReqRespManStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
										insCnsdReqRespManStmt.setString (++bi, respMan3Userid);	// RESPMAN_ID
										insCnsdReqRespManStmt.setString (++bi, respMan3Userid);	// RESPMAN_ID
										insCnsdReqRespManStmt.setString (++bi, respMan3);		// RESPMAN_NM
										insCnsdReqRespManStmt.setString (++bi, "A00000001");	// RESP_DEPT
										insCnsdReqRespManStmt.setString (++bi, reqDt);			// ASGN_DT
										insCnsdReqRespManStmt.setString (++bi, respHeadJuno);	// ASGNMAN_ID
										insCnsdReqRespManStmt.setString (++bi, respHeadJuno);	// ASGNMAN_ID
										insCnsdReqRespManStmt.setString (++bi, respHead);		// ASGNMAN_NM
										insCnsdReqRespManStmt.setString (++bi, "N");			// MAIN_CNSD_YN
										insCnsdReqRespManStmt.setString (++bi, "N");			// AUTO_APBT_YN
										insCnsdReqRespManStmt.setString (++bi, orderResman);	// APBT_MEMO
										System.out.println("=================pass xx 036=");
										sqlRet = insCnsdReqRespManStmt.executeUpdate();
										System.out.println("=================pass xx 037=");
										if(sqlRet != 1) {
											System.out.print(rsRow + "\t");
											System.out.print(cntrNo);
											System.out.print("\t" + cntrtId);
											System.out.print("\t" + cnsdReqId);
						
											System.out.print("\t==> TN_CLM_CONT_CNSDREQ_RESPMAN 3 등록 실패");
											System.out.println("");
										}
									}
								} else {
									System.out.print(rsRow + "\t");
									System.out.print(cntrNo);
									System.out.print("\t" + cntrtId);
									System.out.print("\t" + cnsdReqId);
				
									System.out.print("\t==> TN_CLM_CONTRACT_CNSD 등록 실패");
									System.out.println("");
								}

								addFlag = false;
							} else if("S05".equals(sState)) {		// 보류
								if("".equals(cnsdReqId)) {
									// 의뢰 정보가 없는 상태에서 보류처리 (즉, 최초의뢰에 대해 법무팀 검토없이 의뢰자가 보류하는 경우)
									cnsdStatus = "C04301";
									cnsdDt = "";
									apbtDt = "";
									cnsdOpnn = "";
									cnsdManId = "";
									cnsdManNm = "";
									apbtManId = "";
									apbtManNm = "";
									reDt = "";
									cnsdHoldCause = strNull(reqHistRs.getString("BACKCAUSE"), "");

									if("NULL".equals(customerCd)) {
										cnsdReqId = cntrNo + customerCd + "_" + String.format("%02d", histNo);
									} else {
										if(customerCd.length()<=3){
											cnsdReqId = cntrNo + "_000";
										}else if(customerCd.length()<=5){	
											cnsdReqId = cntrNo + customerCd.substring(3,customerCd.length()) + "_" + String.format("%02d", histNo);
										}else{
											cnsdReqId = cntrNo + customerCd.substring(3,6) + "_" + String.format("%02d", histNo);
										}
									}
									if("".equals(fstCnsdReqId)) {
										fstCnsdReqId = cnsdReqId;
									}
									
									// TN_CLM_CONTRACT_CNSD 등록
									bi = 0;
									insContractCnsdStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
									insContractCnsdStmt.setString (++bi, cntrtId);			// CNTRT_ID
									insContractCnsdStmt.setString (++bi, divisionCd);		// COMP_CD
									insContractCnsdStmt.setString (++bi, divisionCd);				// COMP_NM
									insContractCnsdStmt.setString (++bi, cnsdManId);		// CNSDMAN_ID
									insContractCnsdStmt.setString (++bi, cnsdManId);		// CNSDMAN_ID
									insContractCnsdStmt.setString (++bi, cnsdManNm);		// CNSDMAN_NM
									insContractCnsdStmt.setString (++bi, cnsdDt);			// CNSD_DT
									insContractCnsdStmt.setString (++bi, cnsdDt);			// CNSD_DT
									insContractCnsdStmt.setString (++bi, "");				// CNSD_MEMO
									insContractCnsdStmt.setString (++bi, cnsdOpnn);			// CNSD_OPNN
									insContractCnsdStmt.setString (++bi, apbtManId);		// APBTMAN_ID
									insContractCnsdStmt.setString (++bi, apbtManId);		// APBTMAN_ID
									insContractCnsdStmt.setString (++bi, apbtManNm);		// APBTMAN_NM
									insContractCnsdStmt.setString (++bi, apbtDt);			// APBT_DT
									insContractCnsdStmt.setString (++bi, apbtDt);			// APBT_DT
									insContractCnsdStmt.setString (++bi, "");				// APBT_OPNN
									insContractCnsdStmt.setString (++bi, cnsdStatus);		// CNSD_STATUS
									insContractCnsdStmt.setString (++bi, reqDt);			// REG_DT
									insContractCnsdStmt.setString (++bi, reqManJuno);		// REG_ID
									insContractCnsdStmt.setString (++bi, reqManJuno);		// REG_ID
									insContractCnsdStmt.setString (++bi, reqManNm);			// REG_NM
									System.out.println("=================pass xx 038=");
									sqlRet = insContractCnsdStmt.executeUpdate();
									System.out.println("=================pass xx 039=");
									if(sqlRet == 1) {
										cntrTitle = strNull(reqHistRs.getString("CNTRTITLE"), "");
										reqManJuno = strNull(reqHistRs.getString("USERID"), "");
										reqManNm = strNull(reqHistRs.getString("REGMANNM"), "");
										reqDept = strNull(reqHistRs.getString("REGDEPT"), "");
										reqDeptNm = strNull(reqHistRs.getString("REGDEPTNM"), "");
										reqDt = strNull(reqHistRs.getString("REGDT"), "");
										reqCont = strNull(reqHistRs.getString("REGCONT"), "");
										
										// TN_CLM_CONT_CNSDREQ 등록
										bi = 0;
										insContCnsdReqStmt.setString (++bi, cnsdReqId);			// CNSDREQ_ID
										insContCnsdReqStmt.setString (++bi, divisionCd);		// COMP_CD
										insContCnsdReqStmt.setString (++bi, divisionCd);				// COMP_NM
										insContCnsdReqStmt.setString (++bi, "A00000001");		// MN_CNSD_DEPT
										insContCnsdReqStmt.setString (++bi, mnRespManApntYn);	// MN_RESPMAN_APNT_YN
										insContCnsdReqStmt.setString (++bi, "F");				// DMSTFRGN_GBN
										insContCnsdReqStmt.setString (++bi, prevCnsdReqId);		// PREV_CNSDREQ_ID
										insContCnsdReqStmt.setString (++bi, cntrTitle);			// REQ_TITLE
										insContCnsdReqStmt.setString (++bi, reqManJuno);		// REQMAN_ID
										insContCnsdReqStmt.setString (++bi, reqManJuno);		// REQMAN_ID
										insContCnsdReqStmt.setString (++bi, reqManNm);			// REQMAN_NM
										insContCnsdReqStmt.setString (++bi, divisionCd);		// REQ_OPERDIV
										insContCnsdReqStmt.setString (++bi, reqDept);			// REQ_DEPT
										insContCnsdReqStmt.setString (++bi, reqDeptNm);			// REQ_DEPT_NM
										insContCnsdReqStmt.setString (++bi, reqDt);				// REQ_DT
										insContCnsdReqStmt.setString (++bi, reqDept);			// FST_REQ_DEPT
										insContCnsdReqStmt.setString (++bi, reqDt);				// ACPT_DT
										insContCnsdReqStmt.setString (++bi, reqCont);			// CNSD_DEMND_CONT
										insContCnsdReqStmt.setString (++bi, "C04210");			// PRGRS_STATUS
										insContCnsdReqStmt.setString (++bi, cnsdHoldCause);		// CNSD_HOLD_CAUSE
										insContCnsdReqStmt.setString (++bi, reDt);				// RE_DT
										insContCnsdReqStmt.setString (++bi, reDt);				// RE_DT
										insContCnsdReqStmt.setString (++bi, reqDt);				// REG_DT
										insContCnsdReqStmt.setString (++bi, reqManJuno);		// REG_ID
										insContCnsdReqStmt.setString (++bi, reqManJuno);		// REG_ID
										insContCnsdReqStmt.setString (++bi, reqManNm);			// REG_NM
										insContCnsdReqStmt.setInt	 (++bi, ++cnsdLevel);		// CNSD_LEVEL
										insContCnsdReqStmt.setString (++bi, fstCnsdReqId);		// FST_CNSDREQ_ID
										System.out.println("=================pass xx 040=");
										sqlRet = insContCnsdReqStmt.executeUpdate();
										System.out.println("=================pass xx 041=");
										if(sqlRet != 1) {
											System.out.print(rsRow + "\t");
											System.out.print(cntrNo);
											System.out.print("\t" + cntrtId);
											System.out.print("\t" + cnsdReqId);
						
											System.out.print("\t==> TN_CLM_CONT_CNSDREQ 등록 실패");
											System.out.println("");
										}
										
										// 데이터이관 시에는 동일한 의뢰ID에 대해 2개 이상 보류사유가 들어가는 경우가 없으므로 중복 체크 후 등록
										bi = 0;
										extContCnsdReqHoldStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
										
										extContCnsdReqHoldRs = extContCnsdReqHoldStmt.executeQuery();
										
										if(!extContCnsdReqHoldRs.next()) {
											// TN_CLM_CONT_CNSDREQ_HOLD 등록
											bi = 0;
											insContCnsdReqHoldStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
											insContCnsdReqHoldStmt.setInt	 (++bi, 1);				// HOLD_SEQNO
											insContCnsdReqHoldStmt.setString (++bi, reqDt);			// HOLD_STARTDAY
											insContCnsdReqHoldStmt.setString (++bi, "");			// HOLD_ENDDAY
											insContCnsdReqHoldStmt.setString (++bi, "");			// HOLD_ENDDAY
											insContCnsdReqHoldStmt.setString (++bi, cnsdHoldCause);	// HOLD_CAUSE
											insContCnsdReqHoldStmt.setString (++bi, reqDt);			// REG_DT
											insContCnsdReqHoldStmt.setString (++bi, reqManJuno);	// REG_ID
											insContCnsdReqHoldStmt.setString (++bi, reqManJuno);	// REG_ID
											insContCnsdReqHoldStmt.setString (++bi, reqManNm);		// REG_NM
											System.out.println("=================pass xx 042=");
											sqlRet = insContCnsdReqHoldStmt.executeUpdate();
											System.out.println("=================pass xx 043=");
											if(sqlRet != 1) {
												System.out.print(rsRow + "\t");
												System.out.print(cntrNo);
												System.out.print("\t" + cntrtId);
												System.out.print("\t" + cnsdReqId);
							
												System.out.print("\t==> TN_CLM_CONT_CNSDREQ_HOLD 등록 실패");
												System.out.println("");
											}
										}
			
										if(!"".equals(respMan1Userid)) {
											// TN_CLM_CONT_CNSDREQ_RESPMAN 등록
											bi = 0;
											insCnsdReqRespManStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
											insCnsdReqRespManStmt.setString (++bi, respMan1Userid);	// RESPMAN_ID
											insCnsdReqRespManStmt.setString (++bi, respMan1Userid);	// RESPMAN_ID
											insCnsdReqRespManStmt.setString (++bi, respMan1);		// RESPMAN_NM
											insCnsdReqRespManStmt.setString (++bi, "A00000001");	// RESP_DEPT
											insCnsdReqRespManStmt.setString (++bi, reqDt);			// ASGN_DT
											insCnsdReqRespManStmt.setString (++bi, respHeadJuno);	// ASGNMAN_ID
											insCnsdReqRespManStmt.setString (++bi, respHeadJuno);	// ASGNMAN_ID
											insCnsdReqRespManStmt.setString (++bi, respHead);		// ASGNMAN_NM
											insCnsdReqRespManStmt.setString (++bi, "Y");			// MAIN_CNSD_YN
											insCnsdReqRespManStmt.setString (++bi, "N");			// AUTO_APBT_YN
											insCnsdReqRespManStmt.setString (++bi, orderResman);	// APBT_MEMO
											System.out.println("=================pass xx 044=");
											sqlRet = insCnsdReqRespManStmt.executeUpdate();
											System.out.println("=================pass xx 045=");
											if(sqlRet != 1) {
												System.out.print(rsRow + "\t");
												System.out.print(cntrNo);
												System.out.print("\t" + cntrtId);
												System.out.print("\t" + cnsdReqId);
							
												System.out.print("\t==> TN_CLM_CONT_CNSDREQ_RESPMAN 1 등록 실패");
												System.out.println("");
											}
										}
			
										if(!"".equals(respMan2Userid) && !respMan1Userid.equals(respMan2Userid)) {
											// TN_CLM_CONT_CNSDREQ_RESPMAN 등록
											bi = 0;
											insCnsdReqRespManStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
											insCnsdReqRespManStmt.setString (++bi, respMan2Userid);	// RESPMAN_ID
											insCnsdReqRespManStmt.setString (++bi, respMan2Userid);	// RESPMAN_ID
											insCnsdReqRespManStmt.setString (++bi, respMan2);		// RESPMAN_NM
											insCnsdReqRespManStmt.setString (++bi, "A00000001");	// RESP_DEPT
											insCnsdReqRespManStmt.setString (++bi, reqDt);			// ASGN_DT
											insCnsdReqRespManStmt.setString (++bi, respHeadJuno);	// ASGNMAN_ID
											insCnsdReqRespManStmt.setString (++bi, respHeadJuno);	// ASGNMAN_ID
											insCnsdReqRespManStmt.setString (++bi, respHead);		// ASGNMAN_NM
											insCnsdReqRespManStmt.setString (++bi, "N");			// MAIN_CNSD_YN
											insCnsdReqRespManStmt.setString (++bi, "N");			// AUTO_APBT_YN
											insCnsdReqRespManStmt.setString (++bi, orderResman);	// APBT_MEMO
											System.out.println("=================pass xx 046=");
											sqlRet = insCnsdReqRespManStmt.executeUpdate();
											System.out.println("=================pass xx 047=");
											if(sqlRet != 1) {
												System.out.print(rsRow + "\t");
												System.out.print(cntrNo);
												System.out.print("\t" + cntrtId);
												System.out.print("\t" + cnsdReqId);
							
												System.out.print("\t==> TN_CLM_CONT_CNSDREQ_RESPMAN 2 등록 실패");
												System.out.println("");
											}
										}
			
										if(!"".equals(respMan3Userid) && !respMan1Userid.equals(respMan3Userid) && !respMan2Userid.equals(respMan3Userid)) {
											// TN_CLM_CONT_CNSDREQ_RESPMAN 등록
											bi = 0;
											insCnsdReqRespManStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
											insCnsdReqRespManStmt.setString (++bi, respMan3Userid);	// RESPMAN_ID
											insCnsdReqRespManStmt.setString (++bi, respMan3Userid);	// RESPMAN_ID
											insCnsdReqRespManStmt.setString (++bi, respMan3);		// RESPMAN_NM
											insCnsdReqRespManStmt.setString (++bi, "A00000001");	// RESP_DEPT
											insCnsdReqRespManStmt.setString (++bi, reqDt);			// ASGN_DT
											insCnsdReqRespManStmt.setString (++bi, respHeadJuno);	// ASGNMAN_ID
											insCnsdReqRespManStmt.setString (++bi, respHeadJuno);	// ASGNMAN_ID
											insCnsdReqRespManStmt.setString (++bi, respHead);		// ASGNMAN_NM
											insCnsdReqRespManStmt.setString (++bi, "N");			// MAIN_CNSD_YN
											insCnsdReqRespManStmt.setString (++bi, "N");			// AUTO_APBT_YN
											insCnsdReqRespManStmt.setString (++bi, orderResman);	// APBT_MEMO
											System.out.println("=================pass xx 048=");
											sqlRet = insCnsdReqRespManStmt.executeUpdate();
											System.out.println("=================pass xx 049=");
											if(sqlRet != 1) {
												System.out.print(rsRow + "\t");
												System.out.print(cntrNo);
												System.out.print("\t" + cntrtId);
												System.out.print("\t" + cnsdReqId);
							
												System.out.print("\t==> TN_CLM_CONT_CNSDREQ_RESPMAN 3 등록 실패");
												System.out.println("");
											}
										}
									} else {
										System.out.print(rsRow + "\t");
										System.out.print(cntrNo);
										System.out.print("\t" + cntrtId);
										System.out.print("\t" + cnsdReqId);
					
										System.out.print("\t==> TN_CLM_CONTRACT_CNSD 등록 실패");
										System.out.println("");
									}
									
									addFlag = true;
									prevCnsdReqId = cnsdReqId;
								} else if(addFlag) {
									// 회신이 완료된 상태의 보류는 체결 보류로 처리
									
									// TN_CLM_CONT_CNSDREQ 수정
									bi = 0;
									uptContCnsdReqStmt.setString (++bi, "C04212");		// PRGRS_STATUS
									uptContCnsdReqStmt.setString (++bi, "");			// RE_DT
									uptContCnsdReqStmt.setString (++bi, "");			// RE_DT
									uptContCnsdReqStmt.setString (++bi, "");			// CNSD_HOLD_CAUSE (검토보류이므로 내용 작성 안함)
									uptContCnsdReqStmt.setString (++bi, "");			// CNSD_HOLD_CAUSE (검토보류이므로 내용 작성 안함)
									uptContCnsdReqStmt.setString (++bi, prevCnsdReqId);	// CNSDREQ_ID
									
									sqlRet = uptContCnsdReqStmt.executeUpdate();

									if(sqlRet != 1) {
										System.out.print(rsRow + "\t");
										System.out.print(cntrNo);
										System.out.print("\t" + cntrtId);
										System.out.print("\t" + cnsdReqId);
					
										System.out.print("\t==> TN_CLM_CONT_CNSDREQ 수정 실패");
										System.out.println("");
									}
									
									// 데이터이관 시에는 동일한 의뢰ID에 대해 2개 이상 보류사유가 들어가는 경우가 없으므로 중복 체크 후 등록
									bi = 0;
									extContCnsdReqHoldStmt.setString (++bi, prevCnsdReqId);		// CNSDREQ_ID
									
									extContCnsdReqHoldRs = extContCnsdReqHoldStmt.executeQuery();
									
									if(!extContCnsdReqHoldRs.next()) {
										// 보류 사유 기록
										reqDt = strNull(reqHistRs.getString("REGDT"), "");
										reqManJuno = strNull(reqHistRs.getString("USERID"), "");
										reqManNm = strNull(reqHistRs.getString("REGMANNM"), "");
										cnsdHoldCause = strNull(reqHistRs.getString("BACKCAUSE"), "");

										// TN_CLM_CONT_CNSDREQ_HOLD 등록
										bi = 0;
										insContCnsdReqHoldStmt.setString (++bi, prevCnsdReqId);	// CNSDREQ_ID
										insContCnsdReqHoldStmt.setInt	 (++bi, 1);				// HOLD_SEQNO
										insContCnsdReqHoldStmt.setString (++bi, reqDt);			// HOLD_STARTDAY
										insContCnsdReqHoldStmt.setString (++bi, "");			// HOLD_ENDDAY
										insContCnsdReqHoldStmt.setString (++bi, "");			// HOLD_ENDDAY
										insContCnsdReqHoldStmt.setString (++bi, cnsdHoldCause);	// HOLD_CAUSE
										insContCnsdReqHoldStmt.setString (++bi, reqDt);			// REG_DT
										insContCnsdReqHoldStmt.setString (++bi, reqManJuno);	// REG_ID
										insContCnsdReqHoldStmt.setString (++bi, reqManJuno);	// REG_ID
										insContCnsdReqHoldStmt.setString (++bi, reqManNm);		// REG_NM
		
										sqlRet = insContCnsdReqHoldStmt.executeUpdate();
		
										if(sqlRet != 1) {
											System.out.print(rsRow + "\t");
											System.out.print(cntrNo);
											System.out.print("\t" + cntrtId);
											System.out.print("\t" + cnsdReqId);
						
											System.out.print("\t==> TN_CLM_CONT_CNSDREQ_HOLD 등록 실패");
											System.out.println("");
										}
									}
								} else {
									// 회신이 완료 안된 상태의 보류는 검토의뢰 보류로 처리
									cnsdHoldCause = strNull(reqHistRs.getString("BACKCAUSE"), "");

									// TN_CLM_CONT_CNSDREQ 수정
									bi = 0;
									uptContCnsdReqStmt.setString (++bi, "C04210");		// PRGRS_STATUS
									uptContCnsdReqStmt.setString (++bi, "");			// RE_DT
									uptContCnsdReqStmt.setString (++bi, "");			// RE_DT
									uptContCnsdReqStmt.setString (++bi, cnsdHoldCause);	// CNSD_HOLD_CAUSE
									uptContCnsdReqStmt.setString (++bi, cnsdHoldCause);	// CNSD_HOLD_CAUSE
									uptContCnsdReqStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
									
									sqlRet = uptContCnsdReqStmt.executeUpdate();

									if(sqlRet != 1) {
										System.out.print(rsRow + "\t");
										System.out.print(cntrNo);
										System.out.print("\t" + cntrtId);
										System.out.print("\t" + cnsdReqId);
					
										System.out.print("\t==> TN_CLM_CONT_CNSDREQ 수정 실패");
										System.out.println("");
									}
									
									// 데이터이관 시에는 동일한 의뢰ID에 대해 2개 이상 보류사유가 들어가는 경우가 없으므로 중복 체크 후 등록
									bi = 0;
									extContCnsdReqHoldStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
									
									extContCnsdReqHoldRs = extContCnsdReqHoldStmt.executeQuery();
									
									if(!extContCnsdReqHoldRs.next()) {
										// 보류 사유 기록
										reqDt = strNull(reqHistRs.getString("REGDT"), "");
										reqManJuno = strNull(reqHistRs.getString("USERID"), "");
										reqManNm = strNull(reqHistRs.getString("REGMANNM"), "");
	
										// TN_CLM_CONT_CNSDREQ_HOLD 등록
										bi = 0;
										insContCnsdReqHoldStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
										insContCnsdReqHoldStmt.setInt	 (++bi, 1);				// HOLD_SEQNO
										insContCnsdReqHoldStmt.setString (++bi, reqDt);			// HOLD_STARTDAY
										insContCnsdReqHoldStmt.setString (++bi, "");			// HOLD_ENDDAY
										insContCnsdReqHoldStmt.setString (++bi, "");			// HOLD_ENDDAY
										insContCnsdReqHoldStmt.setString (++bi, cnsdHoldCause);	// HOLD_CAUSE
										insContCnsdReqHoldStmt.setString (++bi, reqDt);			// REG_DT
										insContCnsdReqHoldStmt.setString (++bi, reqManJuno);	// REG_ID
										insContCnsdReqHoldStmt.setString (++bi, reqManJuno);	// REG_ID
										insContCnsdReqHoldStmt.setString (++bi, reqManNm);		// REG_NM
		
										sqlRet = insContCnsdReqHoldStmt.executeUpdate();
		
										if(sqlRet != 1) {
											System.out.print(rsRow + "\t");
											System.out.print(cntrNo);
											System.out.print("\t" + cntrtId);
											System.out.print("\t" + cnsdReqId);
						
											System.out.print("\t==> TN_CLM_CONT_CNSDREQ_HOLD 등록 실패");
											System.out.println("");
										}
									}
									
									prevCnsdReqId = cnsdReqId;
									addFlag = true;
								}
							} else {
								System.out.println("new Type " + sState);
								tgConn.rollback();
								return;
							}
							System.out.println("=================pass xx 011= 005");	
							// histNo 별로 첨부파일 등록
							bi = 0;
							contFileStmt.setString(++bi, cntrNo);
							
							contFileRs = contFileStmt.executeQuery();
							
							while(contFileRs.next()) {
								upTp = strNull(contFileRs.getString("UPTP"), "");
								
								fileId = cntrtId + "_" + upTp + "_" + contFileRs.getInt("SEQNO");
								fileBigclsfcn = "";
								fileMidclsfcn = "";
								fileSmlclsfcn = "";
								refKey = cntrtId + "@" + cnsdReqId;
								
								if("05".equals(upTp) && ("S03".equals(sState) || "S08".equals(sState) || "S09".equals(sState))) {
									fileBigclsfcn = "F012";
									fileMidclsfcn = "F01202";
									fileSmlclsfcn = "F0120202";
								} else if("05".equals(upTp) && ("S04".equals(sState) || "S05".equals(sState) || "S10".equals(sState))) {
									fileBigclsfcn = "F012";
									fileMidclsfcn = "F01202";
									fileSmlclsfcn = "F0120201";
								} else if("06".equals(upTp) && ("S03".equals(sState) || "S08".equals(sState) || "S09".equals(sState))) {
									fileBigclsfcn = "F012";
									fileMidclsfcn = "F01202";
									fileSmlclsfcn = "F0120206";
								} else {
									continue;
								}
								
								// TN_COM_ATTACH 등록
								bi = 0;
								insComAttach.setString (++bi, fileId);											// FILE_ID
								insComAttach.setString (++bi, strNull(contFileRs.getString("PATH"), "")+strNull(contFileRs.getString("FILENM"), ""));		// FILE_PATH
								insComAttach.setString (++bi, strNull(contFileRs.getString("FILENM"), ""));		// ORG_FILE_NM
								insComAttach.setInt    (++bi, contFileRs.getInt("SEQNO"));						// FILE_SRT
								insComAttach.setInt    (++bi, contFileRs.getInt("FILESIZE"));					// FILE_SZ
								insComAttach.setString (++bi, "LAS");											// SYS_CD
								insComAttach.setString (++bi, fileBigclsfcn);									// FILE_BIGCLSFCN
								insComAttach.setString (++bi, fileMidclsfcn);									// FILE_MIDCLSFCN
								insComAttach.setString (++bi, fileSmlclsfcn);									// FILE_SMLCLSFCN
								insComAttach.setString (++bi, refKey);											// REF_KEY
								insComAttach.setString (++bi, reqDt);											// REG_DT
								insComAttach.setString (++bi, reqManJuno);										// REG_ID
								insComAttach.setString (++bi, reqManJuno);										// REG_ID
								insComAttach.setString (++bi, contFileRs.getString("FILE_EXT"));				// FILE_INFO
								System.out.println("=================pass xx 011= 006");
								sqlRet = insComAttach.executeUpdate();
								System.out.println("=================pass xx 011= 007");
								if(sqlRet != 1) {
									System.out.print(rsRow + "\t");
									System.out.print(cntrNo);
									System.out.print("\t" + cntrtId);
									System.out.print("\t" + cnsdReqId);
				
									System.out.print("\t==> TN_COM_ATTACH 등록 실패");
									System.out.println("");
								}
							}							
							
						}while(reqHistRs.next());
						
						// TN_CLM_CONT_CNSDREQ 상태 수정
						bi = 0;
						uptContCnsdReqStmt.setString (++bi, prgrsStatus);	// PRGRS_STATUS
						uptContCnsdReqStmt.setString (++bi, "");			// RE_DT
						uptContCnsdReqStmt.setString (++bi, "");			// RE_DT
						uptContCnsdReqStmt.setString (++bi, "");			// CNSD_HOLD_CAUSE
						uptContCnsdReqStmt.setString (++bi, "");			// CNSD_HOLD_CAUSE
						uptContCnsdReqStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
						System.out.println("=================pass xx 011= 008");
						sqlRet = uptContCnsdReqStmt.executeUpdate();
						System.out.println("=================pass xx 011= 009");
						if(sqlRet != 1) {
							System.out.print(rsRow + "\t");
							System.out.print(cntrNo);
							System.out.print("\t" + cntrtId);
							System.out.print("\t" + cnsdReqId);
		
							System.out.print("\t==> TN_CLM_CONT_CNSDREQ 상태 수정 실패");
							System.out.println("");
						}
						
						
					} else {
						// 이력이 없으므로 TCM_FCONTRACT 기준으로 데이터 생성
						// S01[임시저장], S02[의뢰]
						// S06[체결], S03[회신]
						// S07[반려]
						// S20[보류] - 검토중에 보류
						// S19[보류] - 체결전에 보류
						if("S01".equals(mState) || "S02".equals(mState)) {
							cnsdStatus = "C04301";
							cnsdDt = "";
							apbtDt = "";
							cnsdOpnn = "";
							cnsdManId = "";
							cnsdManNm = "";
							apbtManId = "";
							apbtManNm = "";
							reDt = "";
							cnsdHoldCause = "";
						} else if("S06".equals(mState) || "S03".equals(mState)) {
							cnsdStatus = "C04305";
							cnsdDt = reqDt;
							apbtDt = reqDt;
							cnsdOpnn = "auto reply";
							cnsdManId = respMan1Userid;
							cnsdManNm = respMan1;
							apbtManId = respHeadJuno;
							apbtManNm = respHead;
							reDt = apbtDt;
							cnsdHoldCause = "";
						} else if("S07".equals(mState)) {
							cnsdStatus = "C04305";
							cnsdDt = ("".equals(updtDt)?reqDt:updtDt);
							apbtDt = ("".equals(updtDt)?reqDt:updtDt);
							cnsdOpnn = backCause;
							cnsdManId = respHeadJuno;
							cnsdManNm = respHead;
							apbtManId = respHeadJuno;
							apbtManNm = respHead;
							reDt = apbtDt;
							cnsdHoldCause = "";
						} else if("S20".equals(mState)) {
							cnsdStatus = "C04301";
							cnsdDt = "";
							apbtDt = "";
							cnsdOpnn = "";
							cnsdManId = "";
							cnsdManNm = "";
							apbtManId = "";
							apbtManNm = "";
							reDt = "";
							cnsdHoldCause = ("".equals(holdCause)?"Left the Business":holdCause);
						} else if("S19".equals(mState)) {
							cnsdStatus = "C04301";
							cnsdDt = "";
							apbtDt = "";
							cnsdOpnn = "";
							cnsdManId = "";
							cnsdManNm = "";
							apbtManId = "";
							apbtManNm = "";
							reDt = "";
							cnsdHoldCause = ("".equals(holdCause)?"Do Not Proceed":holdCause);
						} else {
							System.out.println(mState);
							continue;
						}
						if("NULL".equals(customerCd)) {
							cnsdReqId = cntrNo + customerCd + "_00";
						} else {
							if(customerCd.length()<=3){
								cnsdReqId = cntrNo + "_00";
							}else if(customerCd.length()<=5){	
								cnsdReqId = cntrNo + customerCd.substring(3,customerCd.length()) + "_00";
							}else{
								cnsdReqId = cntrNo + customerCd.substring(3,6) + "_00";
							}
						}
						prevCnsdReqId = "";
						fstCnsdReqId = cnsdReqId;
						cnsdLevel = 0;
						if(!"".equals(respMan1Userid)) {
							mnRespManApntYn = "Y";
						} else {
							mnRespManApntYn = "N";
						}
						
						// TN_CLM_CONTRACT_CNSD 등록
						bi = 0;
						insContractCnsdStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
						insContractCnsdStmt.setString (++bi, cntrtId);			// CNTRT_ID
						insContractCnsdStmt.setString (++bi, divisionCd);		// COMP_CD
						insContractCnsdStmt.setString (++bi, divisionCd);				// COMP_NM
						insContractCnsdStmt.setString (++bi, cnsdManId);		// CNSDMAN_ID
						insContractCnsdStmt.setString (++bi, cnsdManId);		// CNSDMAN_ID
						insContractCnsdStmt.setString (++bi, cnsdManNm);		// CNSDMAN_NM
						insContractCnsdStmt.setString (++bi, cnsdDt);			// CNSD_DT
						insContractCnsdStmt.setString (++bi, cnsdDt);			// CNSD_DT
						insContractCnsdStmt.setString (++bi, "");				// CNSD_MEMO
						insContractCnsdStmt.setString (++bi, cnsdOpnn);			// CNSD_OPNN
						insContractCnsdStmt.setString (++bi, apbtManId);		// APBTMAN_ID
						insContractCnsdStmt.setString (++bi, apbtManId);		// APBTMAN_ID
						insContractCnsdStmt.setString (++bi, apbtManNm);		// APBTMAN_NM
						insContractCnsdStmt.setString (++bi, apbtDt);			// APBT_DT
						insContractCnsdStmt.setString (++bi, apbtDt);			// APBT_DT
						insContractCnsdStmt.setString (++bi, "");				// APBT_OPNN
						insContractCnsdStmt.setString (++bi, cnsdStatus);		// CNSD_STATUS
						insContractCnsdStmt.setString (++bi, reqDt);			// REG_DT
						insContractCnsdStmt.setString (++bi, reqManJuno);		// REG_ID
						insContractCnsdStmt.setString (++bi, reqManJuno);		// REG_ID
						insContractCnsdStmt.setString (++bi, reqManNm);			// REG_NM
						System.out.println("=================pass xx 080=cnsdDt,apbtDt,reqDt:"+cnsdDt+"|"+apbtDt+"|"+reqDt);
						                                       	                                       	
						sqlRet = insContractCnsdStmt.executeUpdate();
						System.out.println("=================pass xx 081=");	
						if(sqlRet == 1) {
							// TN_CLM_CONT_CNSDREQ 등록
							bi = 0;
							insContCnsdReqStmt.setString (++bi, cnsdReqId);			// CNSDREQ_ID
							insContCnsdReqStmt.setString (++bi, divisionCd);		// COMP_CD
							insContCnsdReqStmt.setString (++bi, divisionCd);				// COMP_NM
							insContCnsdReqStmt.setString (++bi, "A00000001");		// MN_CNSD_DEPT
							insContCnsdReqStmt.setString (++bi, mnRespManApntYn);	// MN_RESPMAN_APNT_YN
							insContCnsdReqStmt.setString (++bi, "F");				// DMSTFRGN_GBN
							insContCnsdReqStmt.setString (++bi, prevCnsdReqId);		// PREV_CNSDREQ_ID
							insContCnsdReqStmt.setString (++bi, cntrTitle);			// REQ_TITLE
							insContCnsdReqStmt.setString (++bi, reqManJuno);		// REQMAN_ID
							insContCnsdReqStmt.setString (++bi, reqManJuno);		// REQMAN_ID
							insContCnsdReqStmt.setString (++bi, reqManNm);			// REQMAN_NM
							insContCnsdReqStmt.setString (++bi, divisionCd);		// REQ_OPERDIV
							insContCnsdReqStmt.setString (++bi, reqDept);			// REQ_DEPT
							insContCnsdReqStmt.setString (++bi, reqDeptNm);			// REQ_DEPT_NM
							insContCnsdReqStmt.setString (++bi, reqDt);				// REQ_DT
							insContCnsdReqStmt.setString (++bi, reqDept);			// FST_REQ_DEPT
							insContCnsdReqStmt.setString (++bi, reqDt);				// ACPT_DT
							insContCnsdReqStmt.setString (++bi, reqCont);			// CNSD_DEMND_CONT
							insContCnsdReqStmt.setString (++bi, prgrsStatus);		// PRGRS_STATUS
							insContCnsdReqStmt.setString (++bi, cnsdHoldCause);		// CNSD_HOLD_CAUSE
							insContCnsdReqStmt.setString (++bi, reDt);				// RE_DT
							insContCnsdReqStmt.setString (++bi, reDt);				// RE_DT
							insContCnsdReqStmt.setString (++bi, reqDt);				// REG_DT
							insContCnsdReqStmt.setString (++bi, reqManJuno);		// REG_ID
							insContCnsdReqStmt.setString (++bi, reqManJuno);		// REG_ID
							insContCnsdReqStmt.setString (++bi, reqManNm);			// REG_NM
							insContCnsdReqStmt.setInt	 (++bi, ++cnsdLevel);		// CNSD_LEVEL
							insContCnsdReqStmt.setString (++bi, fstCnsdReqId);		// FST_CNSDREQ_ID
							System.out.println("=================pass xx 082=");	
							sqlRet = insContCnsdReqStmt.executeUpdate();
							System.out.println("=================pass xx 083=");	
							if(sqlRet != 1) {
								System.out.print(rsRow + "\t");
								System.out.print(cntrNo);
								System.out.print("\t" + cntrtId);
								System.out.print("\t" + cnsdReqId);
			
								System.out.print("\t==> TN_CLM_CONT_CNSDREQ 등록 실패");
								System.out.println("");
							}
							
							if("C04302".equals(cnsdStatus) || "C04303".equals(cnsdStatus) || "C04305".equals(cnsdStatus)) {
								// TN_CLM_CONTRACT_DEPTCNSD 등록
								bi = 0;
								insContractDeptCnsdStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
								insContractDeptCnsdStmt.setString (++bi, cntrtId);			// CNTRT_ID
								insContractDeptCnsdStmt.setString (++bi, divisionCd);		// COMP_CD
								insContractDeptCnsdStmt.setString (++bi, divisionCd);		// COMP_NM
								insContractDeptCnsdStmt.setString (++bi, "A00000001");		// CNSD_DEPT
								insContractDeptCnsdStmt.setString (++bi, cnsdManId);		// CNSDMAN_ID
								insContractDeptCnsdStmt.setString (++bi, cnsdManId);		// CNSDMAN_ID
								insContractDeptCnsdStmt.setString (++bi, cnsdManNm);		// CNSDMAN_NM
								insContractDeptCnsdStmt.setString (++bi, cnsdDt);			// CNSD_DT
								insContractDeptCnsdStmt.setString (++bi, cnsdDt);			// CNSD_DT
								insContractDeptCnsdStmt.setString (++bi, cnsdOpnn);			// CNSD_OPNN
								insContractDeptCnsdStmt.setString (++bi, "");				// MAIN_MATR_CONT
								insContractDeptCnsdStmt.setString (++bi, apbtManId);		// APBTMAN_ID
								insContractDeptCnsdStmt.setString (++bi, apbtManId);		// APBTMAN_ID
								insContractDeptCnsdStmt.setString (++bi, apbtManNm);		// APBTMAN_NM
								insContractDeptCnsdStmt.setString (++bi, apbtDt);			// APBT_DT
								insContractDeptCnsdStmt.setString (++bi, apbtDt);			// APBT_DT
								insContractDeptCnsdStmt.setString (++bi, "");				// APBT_OPNN
								insContractDeptCnsdStmt.setString (++bi, cnsdStatus);		// CNSD_STATUS
								System.out.println("=================pass xx 084=");	
								sqlRet = insContractDeptCnsdStmt.executeUpdate();
								System.out.println("=================pass xx 085=");	
								if(sqlRet != 1) {
									System.out.print(rsRow + "\t");
									System.out.print(cntrNo);
									System.out.print("\t" + cntrtId);
									System.out.print("\t" + cnsdReqId);
				
									System.out.print("\t==> TN_CLM_CONTRACT_DEPTCNSD 등록 실패");
									System.out.println("");
								}
							}
							
							if("C04210".equals(prgrsStatus) || "C04212".equals(prgrsStatus)) {
								// 데이터이관 시에는 동일한 의뢰ID에 대해 2개 이상 보류사유가 들어가는 경우가 없으므로 중복 체크 후 등록
								bi = 0;
								extContCnsdReqHoldStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
								
								extContCnsdReqHoldRs = extContCnsdReqHoldStmt.executeQuery();
								
								if(!extContCnsdReqHoldRs.next()) {
									// TN_CLM_CONT_CNSDREQ_HOLD 등록
									bi = 0;
									insContCnsdReqHoldStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
									insContCnsdReqHoldStmt.setInt	 (++bi, 1);				// HOLD_SEQNO
									insContCnsdReqHoldStmt.setString (++bi, reqDt);			// HOLD_STARTDAY
									insContCnsdReqHoldStmt.setString (++bi, "");			// HOLD_ENDDAY
									insContCnsdReqHoldStmt.setString (++bi, "");			// HOLD_ENDDAY
									insContCnsdReqHoldStmt.setString (++bi, cnsdHoldCause);	// HOLD_CAUSE
									insContCnsdReqHoldStmt.setString (++bi, reqDt);			// REG_DT
									insContCnsdReqHoldStmt.setString (++bi, reqManJuno);	// REG_ID
									insContCnsdReqHoldStmt.setString (++bi, reqManJuno);	// REG_ID
									insContCnsdReqHoldStmt.setString (++bi, reqManNm);		// REG_NM
	
									sqlRet = insContCnsdReqHoldStmt.executeUpdate();
	
									if(sqlRet != 1) {
										System.out.print(rsRow + "\t");
										System.out.print(cntrNo);
										System.out.print("\t" + cntrtId);
										System.out.print("\t" + cnsdReqId);
					
										System.out.print("\t==> TN_CLM_CONT_CNSDREQ_HOLD 등록 실패");
										System.out.println("");
									}
								}
							}

							if(!"".equals(respMan1Userid)) {
								// TN_CLM_CONT_CNSDREQ_RESPMAN 등록
								bi = 0;
								insCnsdReqRespManStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
								insCnsdReqRespManStmt.setString (++bi, respMan1Userid);	// RESPMAN_ID
								insCnsdReqRespManStmt.setString (++bi, respMan1Userid);	// RESPMAN_ID
								insCnsdReqRespManStmt.setString (++bi, respMan1);		// RESPMAN_NM
								insCnsdReqRespManStmt.setString (++bi, "A00000001");	// RESP_DEPT
								insCnsdReqRespManStmt.setString (++bi, reqDt);			// ASGN_DT
								insCnsdReqRespManStmt.setString (++bi, respHeadJuno);	// ASGNMAN_ID
								insCnsdReqRespManStmt.setString (++bi, respHeadJuno);	// ASGNMAN_ID
								insCnsdReqRespManStmt.setString (++bi, respHead);		// ASGNMAN_NM
								insCnsdReqRespManStmt.setString (++bi, "Y");			// MAIN_CNSD_YN
								insCnsdReqRespManStmt.setString (++bi, "N");			// AUTO_APBT_YN
								insCnsdReqRespManStmt.setString (++bi, orderResman);	// APBT_MEMO
								
								sqlRet = insCnsdReqRespManStmt.executeUpdate();
	
								if(sqlRet != 1) {
									System.out.print(rsRow + "\t");
									System.out.print(cntrNo);
									System.out.print("\t" + cntrtId);
									System.out.print("\t" + cnsdReqId);
				
									System.out.print("\t==> TN_CLM_CONT_CNSDREQ_RESPMAN 1 등록 실패");
									System.out.println("");
								}
							}

							if(!"".equals(respMan2Userid) && !respMan1Userid.equals(respMan2Userid)) {
								// TN_CLM_CONT_CNSDREQ_RESPMAN 등록
								bi = 0;
								insCnsdReqRespManStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
								insCnsdReqRespManStmt.setString (++bi, respMan2Userid);	// RESPMAN_ID
								insCnsdReqRespManStmt.setString (++bi, respMan2Userid);	// RESPMAN_ID
								insCnsdReqRespManStmt.setString (++bi, respMan2);		// RESPMAN_NM
								insCnsdReqRespManStmt.setString (++bi, "A00000001");	// RESP_DEPT
								insCnsdReqRespManStmt.setString (++bi, reqDt);			// ASGN_DT
								insCnsdReqRespManStmt.setString (++bi, respHeadJuno);	// ASGNMAN_ID
								insCnsdReqRespManStmt.setString (++bi, respHeadJuno);	// ASGNMAN_ID
								insCnsdReqRespManStmt.setString (++bi, respHead);		// ASGNMAN_NM
								insCnsdReqRespManStmt.setString (++bi, "N");			// MAIN_CNSD_YN
								insCnsdReqRespManStmt.setString (++bi, "N");			// AUTO_APBT_YN
								insCnsdReqRespManStmt.setString (++bi, orderResman);	// APBT_MEMO
								
								sqlRet = insCnsdReqRespManStmt.executeUpdate();
	
								if(sqlRet != 1) {
									System.out.print(rsRow + "\t");
									System.out.print(cntrNo);
									System.out.print("\t" + cntrtId);
									System.out.print("\t" + cnsdReqId);
				
									System.out.print("\t==> TN_CLM_CONT_CNSDREQ_RESPMAN 2 등록 실패");
									System.out.println("");
								}
							}

							if(!"".equals(respMan3Userid) && !respMan1Userid.equals(respMan3Userid) && !respMan2Userid.equals(respMan3Userid)) {
								// TN_CLM_CONT_CNSDREQ_RESPMAN 등록
								bi = 0;
								insCnsdReqRespManStmt.setString (++bi, cnsdReqId);		// CNSDREQ_ID
								insCnsdReqRespManStmt.setString (++bi, respMan3Userid);	// RESPMAN_ID
								insCnsdReqRespManStmt.setString (++bi, respMan3Userid);	// RESPMAN_ID
								insCnsdReqRespManStmt.setString (++bi, respMan3);		// RESPMAN_NM
								insCnsdReqRespManStmt.setString (++bi, "A00000001");	// RESP_DEPT
								insCnsdReqRespManStmt.setString (++bi, reqDt);			// ASGN_DT
								insCnsdReqRespManStmt.setString (++bi, respHeadJuno);	// ASGNMAN_ID
								insCnsdReqRespManStmt.setString (++bi, respHeadJuno);	// ASGNMAN_ID
								insCnsdReqRespManStmt.setString (++bi, respHead);		// ASGNMAN_NM
								insCnsdReqRespManStmt.setString (++bi, "N");			// MAIN_CNSD_YN
								insCnsdReqRespManStmt.setString (++bi, "N");			// AUTO_APBT_YN
								insCnsdReqRespManStmt.setString (++bi, orderResman);	// APBT_MEMO
								
								sqlRet = insCnsdReqRespManStmt.executeUpdate();
	
								if(sqlRet != 1) {
									System.out.print(rsRow + "\t");
									System.out.print(cntrNo);
									System.out.print("\t" + cntrtId);
									System.out.print("\t" + cnsdReqId);
				
									System.out.print("\t==> TN_CLM_CONT_CNSDREQ_RESPMAN 3 등록 실패");
									System.out.println("");
								}
							}
							
						} else {
							System.out.print(rsRow + "\t");
							System.out.print(cntrNo);
							System.out.print("\t" + cntrtId);
							System.out.print("\t" + cnsdReqId);
		
							System.out.print("\t==> TN_CLM_CONTRACT_CNSD 등록 실패");
							System.out.println("");
						}
						
					}
					System.out.println("=================pass xx 011= 010");
					bi = 0;
					contFileStmt.setString(++bi, cntrNo);
					
					contFileRs = contFileStmt.executeQuery();
					
					while(contFileRs.next()) {
						upTp = strNull(contFileRs.getString("UPTP"), "");
						
						fileId = cntrtId + "_" + upTp + "_" + contFileRs.getInt("SEQNO");
						fileBigclsfcn = "";
						fileMidclsfcn = "";
						fileSmlclsfcn = "";
						refKey = cntrtId;
						
						if("01".equals(upTp) || "02".equals(upTp)) {
							fileBigclsfcn = "F012";
							fileMidclsfcn = "F01202";
							fileSmlclsfcn = "F0120201";
							refKey = cntrtId + "@" + fstCnsdReqId;
						} else if("03".equals(upTp)) {
							fileBigclsfcn = "F012";
							fileMidclsfcn = "F01203";
							fileSmlclsfcn = "";
						} else {
							continue;
						}
						
						// TN_COM_ATTACH 등록
						bi = 0;
						insComAttach.setString (++bi, fileId);											// FILE_ID
						insComAttach.setString (++bi, strNull(contFileRs.getString("PATH"), "")+strNull(contFileRs.getString("FILENM"), ""));		// FILE_PATH
						insComAttach.setString (++bi, strNull(contFileRs.getString("FILENM"), ""));		// ORG_FILE_NM
						insComAttach.setInt    (++bi, contFileRs.getInt("SEQNO"));						// FILE_SRT
						insComAttach.setInt    (++bi, contFileRs.getInt("FILESIZE"));					// FILE_SZ
						insComAttach.setString (++bi, "LAS");											// SYS_CD
						insComAttach.setString (++bi, fileBigclsfcn);									// FILE_BIGCLSFCN
						insComAttach.setString (++bi, fileMidclsfcn);									// FILE_MIDCLSFCN
						insComAttach.setString (++bi, fileSmlclsfcn);									// FILE_SMLCLSFCN
						insComAttach.setString (++bi, refKey);											// REF_KEY
						insComAttach.setString (++bi, reqDt);											// REG_DT
						insComAttach.setString (++bi, reqManJuno);										// REG_ID
						insComAttach.setString (++bi, reqManJuno);										// REG_ID
						insComAttach.setString (++bi, contFileRs.getString("FILE_EXT"));
						System.out.println("=================pass xx 011= 011");	
						sqlRet = insComAttach.executeUpdate();
						System.out.println("=================pass xx 011= 012");
						if(sqlRet != 1) {
							System.out.print(rsRow + "\t");
							System.out.print(cntrNo);
							System.out.print("\t" + cntrtId);
							System.out.print("\t" + cnsdReqId);
		
							System.out.print("\t==> TN_COM_ATTACH 등록 실패");
							System.out.println("");
						}
					}
					
					if("S13".equals(mState) || "S14".equals(mState) || "S15".equals(mState) || "S16".equals(mState) || "S17".equals(mState) || "S06".equals(mState)) {
						// 체결품의 등록
						bi = 0;
						appStmt.setString(++bi, cntrNo);
						System.out.println("=================pass xx 011= 012-1");
						appRs = appStmt.executeQuery();
						System.out.println("=================pass xx 011= 012-2 cntrNo:"+cntrNo);
						if(appRs.next()) {
							System.out.println("=================pass xx 011= 012-3");
							bi = 0;
							appLineStmt.setString(++bi, cntrNo);
							System.out.println("=================pass xx 011= 012-4");
							appLineRs = appLineStmt.executeQuery();
							System.out.println("=================pass xx 011= 012-5");
							if(appLineRs.next()) {
								// 결재정보와 결재경로가 모두 있을 경우 등록
								misId = appLineRs.getString("MISID");

								// TB_COM_START_PROCESS_WSVO 등록
								bi = 0;
								insStartProcessWsvoStmt.setString (++bi, misId);								// MIS_ID
								insStartProcessWsvoStmt.setString (++bi, "2");									// STATUS : 이관데이타는 모두 결재된 걸로 처리함.
								insStartProcessWsvoStmt.setString (++bi, appRs.getString("APPCONTENT"));		// BODY
								insStartProcessWsvoStmt.setString (++bi, appLineRs.getString("SUBMITDATE"));	// CREATE_DATE
								insStartProcessWsvoStmt.setString (++bi, appRs.getString("APPTITLE"));			// TITLE
								insStartProcessWsvoStmt.setString (++bi, cntrtId);								// REF_KEY
								System.out.println("=================pass xx 011= 013");	
								sqlRet = insStartProcessWsvoStmt.executeUpdate();
								System.out.println("=================pass xx 011= 014");
								if(sqlRet == 1) {
									do {
										// TB_COM_ROUTE_WSVO 등록
										bi = 0;
										insRouteWsvoStmt.setString (++bi, misId);								// MIS_ID
										insRouteWsvoStmt.setString (++bi, appLineRs.getString("EMAIL"));		// MAIL_ADDRESS
										insRouteWsvoStmt.setString (++bi, appLineRs.getString("APPTYPE"));		// ACTIVITY
										insRouteWsvoStmt.setString (++bi, appLineRs.getString("APPROVEDATE"));	// APPROVED
										insRouteWsvoStmt.setString (++bi, appLineRs.getString("SUBMITDATE"));	// ARRIVED
										insRouteWsvoStmt.setString (++bi, divisionCd);							// COMP_CODE
										insRouteWsvoStmt.setString (++bi, "");									// COMP_NAME
										insRouteWsvoStmt.setString (++bi, "");									// DEPT_CODE
										insRouteWsvoStmt.setString (++bi, appLineRs.getString("DEPTNAME"));		// DEPT_NAME
										insRouteWsvoStmt.setString (++bi, appLineRs.getString("OPINION"));		// OPINION
										insRouteWsvoStmt.setString (++bi, appLineRs.getString("APPORDER"));		// SEQUENCE
										insRouteWsvoStmt.setString (++bi, appLineRs.getString("USERID"));		// USER_ID
										insRouteWsvoStmt.setString (++bi, appLineRs.getString("USERID"));		// USER_ID
										insRouteWsvoStmt.setString (++bi, appLineRs.getString("NAME"));			// USER_NAME
										System.out.println("=================pass xx 011= 015 cntrNo:"+misId+","+appLineRs.getString("EMAIL")+",");
										System.out.println("=================pass xx 011= 015 cntrNo:"+appLineRs.getString("APPTYPE")+","+appLineRs.getString("APPROVEDATE")+",");
										System.out.println("=================pass xx 011= 015 cntrNo:"+appLineRs.getString("SUBMITDATE")+","+divisionCd+",");
										System.out.println("=================pass xx 011= 015 cntrNo:"+appLineRs.getString("DEPTNAME")+","+appLineRs.getString("OPINION")+",");
										System.out.println("=================pass xx 011= 015 cntrNo:"+appLineRs.getString("USERID")+","+appLineRs.getString("NAME")+",");
										
										sqlRet = insRouteWsvoStmt.executeUpdate();
										System.out.println("=================pass xx 011= 016");
										if(sqlRet != 1) {
											System.out.print(rsRow + "\t");
											System.out.print(cntrNo);
											System.out.print("\t" + cntrtId);
											System.out.print("\t" + cnsdReqId);
						
											System.out.print("\t==> TB_COM_ROUTE_WSVO 등록 실패");
											System.out.println("");
										}
									}while(appLineRs.next());
									
									// TB_COM_ATTACHMENT_WSVO 등록
									bi = 0;
									contFileStmt.setString(++bi, cntrNo);
									
									contFileRs = contFileStmt.executeQuery();
									fileNo = 0;
									
									while(contFileRs.next()) {
										upTp = strNull(contFileRs.getString("UPTP"), "");
										
										if("08".equals(upTp)) {
											// TB_COM_ATTACHMENT_WSVO 등록
											bi = 0;
											insAttachmentWsvoStmt.setString	(++bi, misId);											// MIS_ID
											insAttachmentWsvoStmt.setInt	(++bi, ++fileNo);										// SEQUENCE
											insAttachmentWsvoStmt.setString	(++bi, strNull(contFileRs.getString("FILENM"), ""));	// FILE_NAME
											insAttachmentWsvoStmt.setInt	(++bi, contFileRs.getInt("FILESIZE"));					// FILE_SIZE
											insAttachmentWsvoStmt.setString	(++bi, strNull(contFileRs.getString("PATH"), ""));		// STORE_LOCATION
											System.out.println("=================pass xx 011= 017");
											sqlRet = insAttachmentWsvoStmt.executeUpdate();
											System.out.println("=================pass xx 011= 018");
											if(sqlRet != 1) {
												System.out.print(rsRow + "\t");
												System.out.print(cntrNo);
												System.out.print("\t" + cntrtId);
												System.out.print("\t" + cnsdReqId);
							
												System.out.print("\t==> TB_COM_ATTACHMENT_WSVO 등록 실패");
												System.out.println("");
											}

										}
									}
								} else {
									System.out.print(rsRow + "\t");
									System.out.print(cntrNo);
									System.out.print("\t" + cntrtId);
									System.out.print("\t" + cnsdReqId);
				
									System.out.print("\t==> TB_COM_START_PROCESS_WSVO 등록 실패");
									System.out.println("");
								}
								
							}
						}
						System.out.println("=================pass xx 011= 012-7");
					}
					
					System.out.print(rsRow + "\t");
					System.out.print(cntrNo);
					System.out.print("\t" + cntrtId);

					System.out.print("\t==> 등록 성공");
					System.out.println("");
					
				} else {
					System.out.print(rsRow + "\t");
					System.out.print(cntrNo);
					System.out.print("\t" + cntrtId);

					System.out.print("\t==> TN_CLM_CONTRACT_MASTER 등록 실패");
					System.out.println("");
				}
				
				
			}
			
		} catch (SQLException se) {
			System.out.println(se.toString());
			tgConn.rollback();
		} finally {
			if(contRs != null) contRs.close();
			
			if(contStmt != null) contStmt.close();
			
			if(srConn != null) srConn.close();
			if(tgConn != null) {
				tgConn.setAutoCommit(true);
				tgConn.close();
			}
		}
		
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
		
		if(args.length != 1 || (!"0".equals(args[0]) && !"1".equals(args[0]))) {
			System.out.println("Usage : java migSeLms option");
			System.out.println("\toption = 0 [All Contract]");
			System.out.println("\toption = 1 [Conclusion Contract]");
			return;
		}
		
		migSeLms migBatch = new migSeLms("1");
	}
}