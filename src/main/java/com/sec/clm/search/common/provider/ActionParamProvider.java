package com.sec.clm.search.common.provider;

import java.util.ArrayList;

import com.autonomy.aci.ActionParameter;
import com.sds.secframework.common.dao.CommonDAO;
import com.sec.clm.search.common.util.IDOLQueryParseUtil;
import com.sec.clm.search.common.util.StringUtils;
import com.sec.clm.search.dto.SearchVO;

public class ActionParamProvider{
	
	private static final ActionParamProvider instance = new ActionParamProvider();

    private ActionParamProvider(){}
	
	public static ActionParamProvider getInstance(){
		return instance;
	}
	
	public ArrayList<ActionParameter> provide(SearchVO vo){
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

        //StringBuilder 위해 접속자 Role정보를 가져온다.
        StringBuilder roleCdsBuilder = new StringBuilder();

        for(int i=0;i < vo.getSession_user_role_cds().size(); i++) {
            roleCdsBuilder.append(" ").append(vo.getSession_user_role_cds().get(i));
        }
        //검색엔진 권한쿼리 조합
        String session_user_role_cds =  roleCdsBuilder.toString();

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

        paramList.add(new ActionParameter("Text", queryText));

        if(!"".equals(fieldText)) {
            paramList.add(new ActionParameter("FieldText", fieldText));
        }

        return paramList;
	}
	
}
