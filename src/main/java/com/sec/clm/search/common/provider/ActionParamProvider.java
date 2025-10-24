package com.sec.clm.search.common.provider;

import java.util.ArrayList;

import com.autonomy.aci.ActionParameter;
import com.sec.clm.search.common.util.IDOLQueryParseUtil;
import com.sec.clm.search.common.util.StringUtils;
import com.sec.clm.search.dto.SearchVO;

public class ActionParamProvider{
	
	private static ActionParamProvider instance = new ActionParamProvider();
	
	
	public static ActionParamProvider getInstance(){
		return instance;
	}
	
	public ArrayList<ActionParameter> provide(SearchVO vo){
		synchronized(this) {			
			ArrayList<ActionParameter> paramList = new ArrayList<ActionParameter>();
			String queryText = StringUtils.stripSpecialChar(vo.getQueryText().toLowerCase()); //입력된 검색어에서 특수문자를 공백으로 치환한다.
			if("".equals(queryText.trim())) {
				//특수문자를 제거 하였을때 검색쿼리가 없다면 검색결과가 없는 쿼리로 대체하여 준다(공백일경우 오류남)
				queryText = "IDOLNOQUERYXXXXYYYYZZZZ";
			} else {
				if("clm_master_consideration".equals(vo.getSrch_index_db()) || "clm_master_consultation".equals(vo.getSrch_index_db()) || "clm_master_conclusion".equals(vo.getSrch_index_db()) || "clm_master_execution".equals(vo.getSrch_index_db()) || "clm_master_completion".equals(vo.getSrch_index_db()) || "clm_master_orglastransfer".equals(vo.getSrch_index_db()) || "clm_master_divisiontransfer".equals(vo.getSrch_index_db()) || "las_master_consideration".equals(vo.getSrch_index_db()) || "las_master_consultation".equals(vo.getSrch_index_db()) || "las_master_conclusion".equals(vo.getSrch_index_db()) || "las_master_execution".equals(vo.getSrch_index_db()) || "las_master_completion".equals(vo.getSrch_index_db()) || "las_master_orglastransfer".equals(vo.getSrch_index_db()) || "las_master_divisiontransfer".equals(vo.getSrch_index_db())) {
					if(!"".equals(vo.getQueryField())) {
						queryText = IDOLQueryParseUtil.parse(queryText, vo.getQueryField(), IDOLQueryParseUtil.OPER_AND);						
					}
				}
			}
			
			//검색엔진 권한쿼리 조합
			String session_user_role_cds = "";
			
			//비교를 위해 접속자 Role정보를 가져온다.
			for(int i=0;i < vo.getSession_user_role_cds().size(); i++) {
				session_user_role_cds = session_user_role_cds + " " + vo.getSession_user_role_cds().get(i);
			}
			
			String fieldText = ""; //검색엔진 필드 text
			//계약서 검색인경우 권한조건 쿼리조합
			if("clm_master_consideration".equals(vo.getSrch_index_db()) || "clm_master_consultation".equals(vo.getSrch_index_db()) || "clm_master_conclusion".equals(vo.getSrch_index_db()) || "clm_master_execution".equals(vo.getSrch_index_db()) || "clm_master_completion".equals(vo.getSrch_index_db())) {
				// 접속자가 관리자(전체 열람) 권한이 없을 경우 (의뢰자 : RZ00)
				if(!session_user_role_cds.contains("RA00") && !session_user_role_cds.contains("RA01") && !session_user_role_cds.contains("RD01") && !session_user_role_cds.contains("RM00") && !session_user_role_cds.contains("RA02")) {
					
					// 별도의 전체열람 권한도 없을 경우
					if(!vo.getSession_auth_apnt_yn().equals("Y")) {
						//계약당사자 또는 계약관련자인가?
						fieldText = "(MATCH{"+vo.getSession_user_id()+"}:MF_AUTH_CNTRT_RESPMAN_ID OR MATCH{"+vo.getSession_user_id()+"}:MF_AUTH_TRGTMAN_ID)";
					}
				}
			} else if("clm_advice_review".equals(vo.getSrch_index_db())) {
				// Legal Advice Review 의 경우 특정 권한이 있으면 전체를 표시한다.
				if(!session_user_role_cds.contains("RA00") && !session_user_role_cds.contains("RA01") && !session_user_role_cds.contains("RD01") && !session_user_role_cds.contains("RM00")) {
					 if(session_user_role_cds.contains("RA02")) {
						 // RA02 권한이 있으면 해당 유저가 담당한 내용만 표시한다.
						 if(!"".equals(fieldText)) {
								fieldText += " AND ";
							}
							fieldText += "MATCH{" + vo.getSession_user_id() + "}:MF_RESPMAN_ID*";	 
					 } else {
						 // 아무 권한이 없으면 비표시 대상이다.
						 queryText = "IDOLNOQUERYXXXXYYYYZZZZ";
					 }
				}
			} else if("clm_advice_request".equals(vo.getSrch_index_db())) {
				// Request Legal Advice 의 경우 자신이 등록한 내용만 표시한다.
				if(!"".equals(fieldText)) {
					fieldText += " AND ";
				}
				fieldText += "MATCH{" + vo.getSession_user_id() + "}:MF_REG_ID";
			} else if("clm_advice_ntts".equals(vo.getSrch_index_db())) {
				// Legal Advice (not through the system) 의 경우 특정 권한이 있으면 전체를 표시한다.
				if(!session_user_role_cds.contains("RA00") && !session_user_role_cds.contains("RA01") && !session_user_role_cds.contains("RD01") && !session_user_role_cds.contains("RM00") && !session_user_role_cds.contains("RA02")) {
					// 아무 권한이 없으면 비표시 대상이다.
					queryText = "IDOLNOQUERYXXXXYYYYZZZZ";
				}
			}
				
//				//시스템이 법무인경우 Role정보가 RA00, RA01, RA02, RA03, RC01이 아닌경우 계약서 검색결과가 나오면 안된다.(매뉴권한이 없기 때문에)
//				if("LAS".equals(vo.getSys_cd())) {
//					if(!session_user_role_cds.contains("RA00") && !session_user_role_cds.contains("RA01") && !session_user_role_cds.contains("RA02") && !session_user_role_cds.contains("RA03") && !session_user_role_cds.contains("RC01")) {
//						queryText = "IDOLNOQUERYXXXXYYYYZZZZ"; //검색결과가 없는 쿼리로 대체하여 준다.(결과가 안나오게)
//					}
//				}
				
//				if(!session_user_role_cds.contains("RA00") && !session_user_role_cds.contains("RC01")) { //접속자 Role이 관리자, CP관리자가 아닌경우 match쿼리를 조합한다.
//					if(session_user_role_cds.contains("RA01") || session_user_role_cds.contains("RA02") || session_user_role_cds.contains("RA03")) { //접속자 Role이 법무팀 사용자인 경우
//						if("A00000003".equals(vo.getSession_blngt_orgnz())) { //소속조직이 IP센터일경우
//							fieldText = "MATCH{"+vo.getSession_blngt_orgnz()+"}:MF_AUTH_MN_CNSD_DEPT:MF_AUTH_VC_CNSD_DEPT"; //정검토부서 또는 부검토부서가 소속조직과 일치하는가?
//						}					
//					} else {
//						fieldText = "MATCH{"+vo.getSession_blngt_orgnz()+"}:MF_AUTH_REG_OPERDIV"; //등록사업부가  소속조직과 일치하는가?
//						if(!session_user_role_cds.contains("RD01") && (!"Y".equals(vo.getSession_manager_yn()) || !"Y".equals(vo.getSession_support_yn()))) { //접속자 Role이 사업부 계약관리자가 아닐경우, 지원부서의 조직장이 아닐경우
//							if("Y".equals(vo.getSession_manager_yn())) { //조직장인경우
//								if(!"".equals(vo.getSession_blngt_orgnz()) && vo.getSession_blngt_orgnz() != null) {
//									fieldText += " AND MATCH{"+vo.getSession_blngt_orgnz()+"}:MF_AUTH_CNTRT_RESP_UP_DEPT0"; //계약담당상위부서가 소속조직과 일치하는가?
//								} else {
//									fieldText += " AND MATCH{IDOLNOMATCHQUERYXXXXYYYYZZZZ}:MF_AUTH_CNTRT_RESP_UP_DEPT0"; //만약 소속조직이 없을경우(null이나 공백) 검색결과가 없는쿼리로 대체하여준다.(결과가 안나오게)
//								}
//							} else {
//								//계약담당자ID와 접속자 EPID 또는 요청구분이 C04601과 일치하고 요청상태가 C03702와 일치하고 대상자ID가 접속자EPID와 일치하는가?
//								fieldText += " AND (MATCH{"+vo.getSession_user_id()+"}:MF_AUTH_CNTRT_RESPMAN_ID OR (MATCH{C04601}:MF_AUTH_DEMND_GBN AND MATCH{C03702}:MF_AUTH_DEMND_STATUS AND MATCH{"+vo.getSession_user_id()+"}:MF_AUTH_TRGTMAN_ID))";
//							}
//						}
//					}
//				}
//			} else if("clm_counsel_notice".equals(vo.getSrch_index_db()) || "clm_counsel_faq".equals(vo.getSrch_index_db())) { //공지사항과 FAQ 검색일경우 권한쿼리를 조합한다. 
//				if(!"".equals(vo.getSession_blngt_orgnz()) && vo.getSession_blngt_orgnz() != null) {
//					fieldText = "(MATCH{C01601}:MF_RD_TRGT1 OR (MATCH{C01602}:MF_RD_TRGT1 AND MATCH{" + vo.getSession_blngt_orgnz() +"}:MF_AUTH_ORGNZ_CD))";
//				} else {
//					fieldText = "(MATCH{C01601}:MF_RD_TRGT1 OR (MATCH{C01602}:MF_RD_TRGT1 AND MATCH{IDOLNOMATCHQUERYXXXXYYYYZZZZ}:MF_AUTH_ORGNZ_CD))";
//				}
//				if("clm_counsel_notice".equals(vo.getSrch_index_db()) && "Y".equals(vo.getSession_manager_yn())) { //공지사항 검색에서 조직장인경우
//					fieldText += " AND MATCH{C05701}:MF_RD_TRGT2";
//				}
//			}
			
			//조건쿼리 조합(화면에서 받아온것)
			//if("clm_master_consideration".equals(vo.getSrch_index_db()) || "clm_master_consultation".equals(vo.getSrch_index_db()) || "clm_master_conclusion".equals(vo.getSrch_index_db()) || "clm_master_execution".equals(vo.getSrch_index_db()) || "clm_master_completion".equals(vo.getSrch_index_db()) || "clm_master_orglastransfer".equals(vo.getSrch_index_db()) || "clm_master_divisiontransfer".equals(vo.getSrch_index_db()) || "las_master_consideration".equals(vo.getSrch_index_db()) || "las_master_consultation".equals(vo.getSrch_index_db()) || "las_master_conclusion".equals(vo.getSrch_index_db()) || "las_master_execution".equals(vo.getSrch_index_db()) || "las_master_completion".equals(vo.getSrch_index_db()) || "las_master_orglastransfer".equals(vo.getSrch_index_db()) || "las_master_divisiontransfer".equals(vo.getSrch_index_db())) {
			if("clm_master_consideration".equals(vo.getSrch_index_db()) || "clm_master_consultation".equals(vo.getSrch_index_db()) || "clm_master_conclusion".equals(vo.getSrch_index_db()) || "clm_master_execution".equals(vo.getSrch_index_db()) || "clm_master_completion".equals(vo.getSrch_index_db())) {
				if(!"".equals(fieldText)) {
					if(!"".equals(vo.getFieldText()) && vo.getFieldText() != null) {
						fieldText += " AND " + vo.getFieldText();
					}
				} else {
					fieldText = vo.getFieldText();
				}
			}
			
			// comp_cd 에 따른 제한 처리 추가 시작
			if(!"clm_share_terms".equals(vo.getSrch_index_db())) {
				if(!"".equals(fieldText)) {
					fieldText += " AND ";
				}
				fieldText += "MATCH{" + vo.getSession_comp_cd() + "}:MF_COMP_CD";
			}
			// comp_cd 에 따른 제한 처리 추가 끝
			
			paramList.add(new ActionParameter("TimeoutMS", 7000));
			//paramList.add(new ActionParameter("languageType", "englishUTF8"));
			paramList.add(new ActionParameter("Predict", false));
			paramList.add(new ActionParameter("TotalResults", true));
			paramList.add(new ActionParameter("Print", "fields"));
			//paramList.add(new ActionParameter("MatchAllTerms", "true"));			
			paramList.add(new ActionParameter("DatabaseMatch", vo.getSrch_index_db()));
			paramList.add(new ActionParameter("Start", vo.getStart_index()));
			paramList.add(new ActionParameter("MaxResults", vo.getEnd_index()));
			paramList.add(new ActionParameter("Sort", "Date"));
			//if("clm_master_consideration".equals(vo.getSrch_index_db()) || "clm_master_consultation".equals(vo.getSrch_index_db()) || "clm_master_conclusion".equals(vo.getSrch_index_db()) || "clm_master_execution".equals(vo.getSrch_index_db()) || "clm_master_completion".equals(vo.getSrch_index_db()) || "clm_master_orglastransfer".equals(vo.getSrch_index_db()) || "clm_master_divisiontransfer".equals(vo.getSrch_index_db()) || "las_master_consideration".equals(vo.getSrch_index_db()) || "las_master_consultation".equals(vo.getSrch_index_db()) || "las_master_conclusion".equals(vo.getSrch_index_db()) || "las_master_execution".equals(vo.getSrch_index_db()) || "las_master_completion".equals(vo.getSrch_index_db()) || "las_master_orglastransfer".equals(vo.getSrch_index_db()) || "las_master_divisiontransfer".equals(vo.getSrch_index_db())) {
			if("clm_master_consideration".equals(vo.getSrch_index_db()) || "clm_master_consultation".equals(vo.getSrch_index_db()) || "clm_master_conclusion".equals(vo.getSrch_index_db()) || "clm_master_execution".equals(vo.getSrch_index_db()) || "clm_master_completion".equals(vo.getSrch_index_db())) {
				if(!"".equals(vo.getSrchMinDate())) paramList.add(new ActionParameter("Mindate", vo.getSrchMinDate()));
				if(!"".equals(vo.getSrchMaxDate())) paramList.add(new ActionParameter("Maxdate", vo.getSrchMaxDate()));
			}
			
			
			//paramList.add(new ActionParameter("Text", "s:(" + queryText + ")<fnc.dummy>.......................</fnc.dummy>"));
			// locale update에 따른 쿼리 수정(wildcard 검색 관련, 2012/04/18) -start
//			String tmpEOQ = "";
//	    	String szPattern = "[\\*\\?]+";
//	    	
//	    	Pattern p = Pattern.compile (szPattern);
//	    	Matcher m = p.matcher (queryText);
//
//			if (m.find())  //와일드카드가 있는 경우
//				tmpEOQ = "EOQ";
//			else 
//				tmpEOQ = "EOQ";
//			paramList.add(new ActionParameter("Text", "S:(" + queryText + ")<"+tmpEOQ+">.......................</"+tmpEOQ+">"));
			// locale update에 따른 쿼리 수정(wildcard 검색 관련, 2012/04/18) -end
			
			paramList.add(new ActionParameter("Text", queryText));
			
			if(!"".equals(fieldText)) {
				paramList.add(new ActionParameter("FieldText", fieldText));
			}
			
			return paramList;
		}		
	}	
	
}
