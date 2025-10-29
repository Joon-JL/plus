package com.sec.clm.search.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.autonomy.aci.AciResponse;
import com.autonomy.aci.ActionParameter;
import com.autonomy.aci.businessobjects.ResultList;
import com.autonomy.aci.businessobjects.XMLResultDocument;
import com.autonomy.aci.exceptions.IDOLException;
import com.autonomy.aci.services.ConceptRetrievalFunctionality;
import com.autonomy.aci.services.IDOLService;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.search.common.provider.AciConnectionDetailsProvider;
import com.sec.clm.search.common.provider.ActionParamProvider;
import com.sec.clm.search.common.util.SummaryUtils;
import com.sec.clm.search.dto.SearchVO;
import com.sec.clm.search.service.SearchService;

public class SearchServiceImpl extends CommonServiceImpl implements SearchService {

	public List listSearch(SearchVO vo) throws Exception {
		
		List result = new ArrayList<SearchVO>();
		ResultList resultCrfList = null;
		
		IDOLService service = new IDOLService(AciConnectionDetailsProvider.getInstance().provide(propertyService.getProperty("secfw.idol.common.ip"), 9000)); //검색엔진 접속정보를 설정한다.
		ConceptRetrievalFunctionality crf = service.useConceptRetrievalFunctionality();
		ArrayList<ActionParameter> paramList = ActionParamProvider.getInstance().provide(vo); //검색엔진 액션파라메터를 설정한다.
		
		try {
			resultCrfList = crf.doQuery(paramList); //파라메터를 던져 검색을 실행한다.
		} catch (IDOLException e) {
			// All terms were invalid, through being stopwords, too short, or incorrectly formatted
			vo.setQueryText("");
			paramList = ActionParamProvider.getInstance().provide(vo);
			resultCrfList = crf.doQuery(paramList);
		}
		
		ArrayList<HashMap<String,String>> resultList = new ArrayList<HashMap<String,String>>();
		int iTotalSise= 0;
		String link_url = "";
		if(resultCrfList.getDocumentCount()>0){
			ArrayList list = resultCrfList.getDocuments();
			
			iTotalSise = resultCrfList.getTotalHits();
			for(int i = 0 ; i<list.size(); i++){
				XMLResultDocument document= (XMLResultDocument)list.get(i);
				AciResponse res = document.getXMLContent();
				ArrayList linksList = document.getLinks();
//				//공지사항검색 권한에 따른 필터링관련
//				if("clm_counsel_notice".equals(vo.getSrch_index_db())) {
//					String session_user_role_cds = "";
//					for(int j=0;j < vo.getSession_user_role_cds().size(); j++) { //비교를 위해 접속자 Role정보를 가져온다.
//						session_user_role_cds = session_user_role_cds + " " + vo.getSession_user_role_cds().get(j);
//					}
//					//RD_TRGT2 이 계약관리조직(C05702)이고, 나의 역할이 RA00, RA01, RA02, RM00, RD01 인 데이터만 검색결과에 나온다.
//					if("C05702".equals(res.getTagValue("MF_RD_TRGT2")) && !session_user_role_cds.contains("RA00") && !session_user_role_cds.contains("RA01") && !session_user_role_cds.contains("RA02") && !session_user_role_cds.contains("RA03") && !session_user_role_cds.contains("RM00") && !session_user_role_cds.contains("RD01")) {
//						continue;
//					}
//				}
				
				String drecontent = res.getTagValue("DRECONTENT");
				String drefilecontent = res.getTagValue("DREFILECONTENT");
				HashMap<String,String> info = new HashMap<String,String>();
				info.put("TOTAL_HITS", String.valueOf(iTotalSise)); //검색된 건수
				info.put("WEIGHT", Float.toString(document.getWeight()));
				if(linksList != null && linksList.size() > 0) { //입력한 검색어 중에 분석된 term이 있을경우 하이라이트와 요약처리를 한다.
//					if("clm_share_contracttype".equals(document.getDatabase())) {
//						String dretitle = SummaryUtils.getSummary(res.getTagValue("DRETITLE"), linksList, 100);
//						if(!"".equals(res.getTagValue("LF_UP_CD_NM"))) dretitle = res.getTagValue("LF_UP_CD_NM") + ">" + dretitle;
//						if(!"".equals(res.getTagValue("LF_TOP_CD_NM"))) dretitle = res.getTagValue("LF_TOP_CD_NM") + ">" + dretitle;
//						info.put("DRETITLE", dretitle);
//					} else {
//						info.put("DRETITLE", SummaryUtils.getSummary(res.getTagValue("DRETITLE"), linksList, 100));
//					}
					if("clm_master_consideration".equals(document.getDatabase()) || "clm_master_consultation".equals(document.getDatabase()) || "clm_master_conclusion".equals(document.getDatabase()) || "clm_master_execution".equals(document.getDatabase()) || "clm_master_completion".equals(document.getDatabase())) {
						String dretitle = SummaryUtils.getSummary(res.getTagValue("DRETITLE"), linksList, 100);
						String reqtitle = SummaryUtils.getSummary(res.getTagValue("IF_REQ_TITLE"), linksList, 100);
						//info.put("DRETITLE", reqtitle + " (" + dretitle + ")");
						info.put("DRETITLE", reqtitle);
						info.put("DRETITLE2", dretitle);
					} else {
						info.put("DRETITLE", SummaryUtils.getSummary(res.getTagValue("DRETITLE"), linksList, 100));
					}
					//info.put("DRECONTENT", SummaryUtils.getSummary(drecontent, linksList, 300));
					String summary = SummaryUtils.getSummary(drecontent, linksList, 300);
					if(summary.length() > 0) {
						summary += "<br>";
					}
					summary += SummaryUtils.getSummary(drefilecontent, linksList, 300);
					info.put("DRECONTENT", summary);
				} else {
					int titleMaxLength = 100; //제목 최대길이
					int contentMaxLength = 300; //본문 요약정보 최대길이
					
					String dretitle = res.getTagValue("DRETITLE");
					if(dretitle.length() > titleMaxLength) { //제목이 최대길이보다 길경우 설정한 최대길이 만큼 요약한다.
						if("clm_master_consideration".equals(document.getDatabase()) || "clm_master_consultation".equals(document.getDatabase()) || "clm_master_conclusion".equals(document.getDatabase()) || "clm_master_execution".equals(document.getDatabase()) || "clm_master_completion".equals(document.getDatabase())) {
							dretitle = dretitle.substring(0, titleMaxLength) + "...";
							info.put("DRETITLE2", dretitle);
						} else {
							info.put("DRETITLE", dretitle.substring(0, titleMaxLength) + "...");
						}
					} else {
						if("clm_master_consideration".equals(document.getDatabase()) || "clm_master_consultation".equals(document.getDatabase()) || "clm_master_conclusion".equals(document.getDatabase()) || "clm_master_execution".equals(document.getDatabase()) || "clm_master_completion".equals(document.getDatabase())) {
							info.put("DRETITLE2", dretitle);
						} else {
							info.put("DRETITLE", dretitle);
						}
					}
					
					if("clm_master_consideration".equals(document.getDatabase()) || "clm_master_consultation".equals(document.getDatabase()) || "clm_master_conclusion".equals(document.getDatabase()) || "clm_master_execution".equals(document.getDatabase()) || "clm_master_completion".equals(document.getDatabase())) {
						String reqtitle = res.getTagValue("IF_REQ_TITLE");
						if(reqtitle.length() > titleMaxLength) { //제목이 최대길이보다 길경우 설정한 최대길이 만큼 요약한다.
							reqtitle = reqtitle.substring(0, titleMaxLength) + "...";
						}
						info.put("DRETITLE", reqtitle);
					}
					
					if(drecontent.length() > contentMaxLength) { //본문이 최대길이보다 길경우 설정한 최대길이 만큼 요약한다.
						drecontent = drecontent.substring(0, contentMaxLength);
					}
					if(drefilecontent.length() > contentMaxLength) { //첨부파일본문이 최대길이보다 길경우 설정한 최대길이 만큼 요약한다.
						drefilecontent = drefilecontent.substring(0, contentMaxLength);
					}
					if(drecontent.trim().length() > 0 && drefilecontent.trim().length() > 0)
						drecontent += "<br>";
					
					info.put("DRECONTENT", drecontent + drefilecontent);
				}
				info.put("DREDATE", res.getTagValue("DREDATE"));
//				info.put("LF_MENU_POS", res.getTagValue("LF_MENU_POS"));
				info.put("IF_WRITER", res.getTagValue("IF_WRITER"));
				
				//시스템에 따라 맞는 매뉴ID를 가져온다.
//				if("CLM".equals(vo.getSys_cd())) {
				info.put("LF_MENU_ID", res.getTagValue("LF_MENU_ID"));
				info.put("LF_UP_MENU_ID", res.getTagValue("LF_UP_MENU_ID"));
//				} else {
//					info.put("LF_MENU_ID", res.getTagValue("LF_LAS_MENU_ID"));
//					info.put("LF_UP_MENU_ID", res.getTagValue("LF_LAS_UP_MENU_ID"));
//				}
				
				//상세링크 Url조합
				if("clm_master_consideration".equals(vo.getSrch_index_db())) { //계약관리 > 계약검토
					//link_url = "/clm/manage/consideration.do?method=detailConsideration&cnsdreq_id=" + res.getTagValue("LF_CNSDREQ_ID");
					link_url = "/clm/manage/consideration.do?method=detailConsiderationNew&cnsdreq_id=" + res.getTagValue("LF_CNSDREQ_ID");
				} else if ("clm_master_consultation".equals(vo.getSrch_index_db())) { //계약관리 > 계약체결
					link_url = "/clm/manage/consultation.do?method=detailConsultationNew&cnsdreq_id=" + res.getTagValue("LF_CNSDREQ_ID");
				} else if ("clm_master_conclusion".equals(vo.getSrch_index_db())) { //계약관리 > 계약등록
					link_url = "/clm/manage/conclusion.do?method=detailConclusion&cnsdreq_id=" + res.getTagValue("LF_CNSDREQ_ID");
				} else if ("clm_master_execution".equals(vo.getSrch_index_db())) { //계약관리 > 계약이행
					link_url = "/clm/manage/execution.do?method=listContract&cnsdreq_id=" + res.getTagValue("LF_CNSDREQ_ID");
				} else if ("clm_master_completion".equals(vo.getSrch_index_db())) { //계약관리 > 계약종료
					link_url = "/clm/manage/completion.do?method=listContract&cnsdreq_id=" + res.getTagValue("LF_CNSDREQ_ID");
				} else if ("las_master_consideration".equals(vo.getSrch_index_db()) || "las_master_consultation".equals(vo.getSrch_index_db()) || "las_master_conclusion".equals(vo.getSrch_index_db()) || "las_master_execution".equals(vo.getSrch_index_db()) || "las_master_completion".equals(vo.getSrch_index_db()) || "las_master_orglastransfer".equals(vo.getSrch_index_db()) || "las_master_divisiontransfer".equals(vo.getSrch_index_db()) || "clm_master_orglastransfer".equals(vo.getSrch_index_db()) || "clm_master_divisiontransfer".equals(vo.getSrch_index_db())) { //계약관리(las), 계약관리 > 구법무이관 , 계약관리 > 사업부이관(clm,las) 
					link_url = "/las/contractmanager/consideration.do?method=detailConsideration&cnsdreq_id=" + res.getTagValue("LF_CNSDREQ_ID");
				} else if ("clm_advice_review".equals(vo.getSrch_index_db())) { //Legal Advice Review
					link_url = "/las/lawconsulting/lawConsult.do?method=forwardDetailLawConsult&cnslt_no=" + document.getDocReference();
				} else if ("clm_advice_request".equals(vo.getSrch_index_db())) { //Request Legal Advice
					link_url = "/las/lawconsulting/lawConsult.do?method=forwardDetailLawConsultReqman&isForeign=H&cnslt_no=" + document.getDocReference();
				} else if ("clm_advice_ntts".equals(vo.getSrch_index_db())) { //Legal Advice (not through the system)
					link_url = "/las/lawconsulting/speakConsult.do?method=forwardDetailSpeakConsult&seqno=" + document.getDocReference();
				} else if ("clm_about".equals(vo.getSrch_index_db())) { //About CLM
					link_url = "/clm/admin/aboutClmManage.do?method=listAboutClmManageHtml&seqno=" + document.getDocReference().split(",")[0];
//				} else if ("clm_library".equals(vo.getSrch_index_db())) { //계약라이브러리
//					if("C03501".equals(res.getTagValue("MF_MENU"))) { //Reference계약서
//						link_url = "/clm/draft/refContract.do?method=detailRefContract&lib_no=" + document.getDocReference();
//					} else if("C03502".equals(res.getTagValue("MF_MENU"))) { //표준 Template
//						link_url = "/clm/draft/template.do?method=detailTemplate&lib_no=" + document.getDocReference();
//					} else if("C03503".equals(res.getTagValue("MF_MENU"))) { //조항  라이브러리
//						link_url = "/clm/draft/itemLibrary.do?method=detailItemLibrary&lib_no=" + document.getDocReference();
//					}
				//} else if ("clm_rule_regulation,clm_rule_decision".equals(vo.getSrch_index_db())) { //계약관리규정
				} else if ("clm_rule_regulation".equals(vo.getSrch_index_db())) { // SELME+ GUIDELINES
//					if("clm_rule_decision".equals(document.getDatabase())) { //체결품의 전결규정
//						link_url = res.getTagValue("LF_URL");
//					} else { //全社 계약업무 규정
						//link_url = "/clm/rule/regulation.do?method=listRegulation&srch_rule_no=" + document.getDocReference();
						//link_url = "/clm/admin/regulation.do?method=detailRegulation&rule_no=" + document.getDocReference() + "&srch_rule_no=" + document.getDocReference();
						link_url = "/clm/rule/regulation.do?method=listRegulation";
//					}
//				} else if ("clm_share_contracttype,clm_share_rule,clm_share_terms,clm_share_customer".equals(vo.getSrch_index_db()) || "clm_share_contracttype,clm_share_rule,clm_share_terms".equals(vo.getSrch_index_db())) { //계약지식공유
				} else if ("clm_share_terms".equals(vo.getSrch_index_db())) { // Contract Guidance
//					if("clm_share_contracttype".equals(document.getDatabase())) { //계약유형정의
//						link_url = "/clm/admin/dimension.do?method=listDimensionInfolist"; //링크가 하나뿐임
//					} else if("clm_share_rule".equals(document.getDatabase())) { //계약유형별 이해
//						if("CLM".equals(vo.getSys_cd())) {
//						link_url = "/clm/admin/dimension.do?method=listDimensionWord&menu_id="+res.getTagValue("LF_MENU_ID")+"&rule_no=" + document.getDocReference();
//						} else {
//							link_url = "/clm/admin/dimension.do?method=listDimensionWord&menu_id="+res.getTagValue("LF_LAS_MENU_ID")+"&rule_no=" + document.getDocReference();
//						}
//					} else if("clm_share_terms".equals(document.getDatabase())) { //계약 용어집
//						if("CLM".equals(vo.getSys_cd())) {
						//link_url = "/clm/admin/dimension.do?method=listDimensionWord&menu_id="+res.getTagValue("LF_MENU_ID")+"&rule_no=" + document.getDocReference();
						link_url = "/clm/admin/dimension.do?method=listDimensionWord";
//						} else {
//							link_url = "/clm/admin/dimension.do?method=listDimensionWord&menu_id="+res.getTagValue("LF_LAS_MENU_ID")+"&rule_no=" + document.getDocReference();
//						}
//					} else if("clm_share_customer".equals(document.getDatabase())) { //계약상대방
//						link_url = "/clm/draft/customerTest.do?method=detailCustomerShare&customer_cd="+document.getDocReference()+"&dodun="+res.getTagValue("LF_DODUN");
//					}
				} else if("clm_counsel_faq".equals(vo.getSrch_index_db())) { //FAQ
					//link_url = "/clm/counsel/faq.do?method=detailFaq&seqno=" + document.getDocReference();
					//link_url = "/clm/counsel/faq.do?method=listFaq"; //링크가 하나뿐임
					//link_url = "/las/board/notice.do?method=listSysFaq&annce_knd=FAQ"; //링크가 하나뿐임
					link_url = "/las/board/notice.do?method=detailSysFaq&annce_knd=FAQ&seqno=" + document.getDocReference();
				} else if("clm_counsel_notice".equals(vo.getSrch_index_db())) { //전사공지사항
					//link_url = "/clm/counsel/board.do?method=detailBoard&mode=M&seqno=" + document.getDocReference();
					//link_url = "/clm/counsel/board.do?method=detailBoard&mode=M&seqno=" + document.getDocReference();
					link_url = "/las/board/notice.do?method=detailLawNotice&annce_knd=MEMO&seqno=" + document.getDocReference();
				} else if("clm_counsel_qna".equals(vo.getSrch_index_db())){
					//link_url = "/clm/counsel/qna.do?method=detailQna&up_seqno=" + res.getTagValue("LF_UP_SEQNO") + "&seqno=" + document.getDocReference();
					link_url = "/las/board/bbs.do?method=detailSysQnA&bbs_knd=C01201&grp_no=" + res.getTagValue("LF_GRP_NO") + "&grp_seqno=" + res.getTagValue("LF_GRP_SEQNO") + "&postup_srt=" + res.getTagValue("LF_POSTUP_SRT") + "&postup_depth=" + res.getTagValue("LF_POSTUP_DEPTH") + "&total_seqno=" + document.getDocReference();
				}
				info.put("link_url", link_url);
				
				resultList.add(info);
			}
		}
		
		if(resultList != null && resultList.size() > 0 ){
			HashMap<String,String> idolInfo = new HashMap<String,String>();
			for(int i = 0 ; i<resultList.size() ; i++){
				idolInfo = resultList.get(i);
				SearchVO getInfo = new SearchVO();
				getInfo.setTotal_cnt(Integer.parseInt(idolInfo.get("TOTAL_HITS"))); //검색건수								
				getInfo.setWeight(Float.valueOf(idolInfo.get("WEIGHT")).floatValue());
				getInfo.setDretitle(idolInfo.get("DRETITLE")); //제목
				getInfo.setDrecontent(idolInfo.get("DRECONTENT")); //본문요약정보
				getInfo.setDredate(idolInfo.get("DREDATE")); //날짜(생성일)
//				getInfo.setLf_menu_pos(idolInfo.get("LF_MENU_POS")); //매뉴위치
				getInfo.setIf_writer(idolInfo.get("IF_WRITER")); //작성자
				getInfo.setLf_menu_id(idolInfo.get("LF_MENU_ID")); //매뉴ID
				getInfo.setLf_up_menu_id(idolInfo.get("LF_UP_MENU_ID")); //상위매뉴ID
				getInfo.setLink_url(idolInfo.get("link_url")); //상세링크정보

				getInfo.setDretitle2(idolInfo.get("DRETITLE2")); //리퀘스트 타이틀 (My Contract 전용)
				
				result.add(getInfo);
			}
		}
		
		return result;
	}
	
	/**
	 * 매뉴 구분에 따라 Top 메뉴, Left 메뉴를 반환한다(로직복사 MainServiceImpl.java)
	 * 
	 * @param	menuGbn -> TOP  : Top Menu
	 * 				 -> LEFT : Left Menu
	 */
	public HashMap listMenu(HashMap hm) throws Exception {
		
		String menuNm = "";
		List menuList = null;
		List menuNmList = null;
		HashMap resultMap = new HashMap();
		
		String menuGbn      = StringUtil.bvl((String)hm.get("menuGbn"),"");
		String userLocale   = StringUtil.bvl((String)hm.get("userLocale"),propertyService.getProperty("secfw.defaultLocale"));
		String targetMenuId = StringUtil.bvl((String)hm.get("targetMenuId"),"");
		String selectMenuId = StringUtil.bvl((String)hm.get("selectedMenuId"),"");
		String sysCd 		= StringUtil.bvl((String)hm.get("sys_cd"),"");
		
		String upMenuId     = "";
		String topMenuLevel = StringUtil.bvl(propertyService.getProperty("secfw.menu.topMenuLevel"), "1");
		
		
		if ("TOP".equals(menuGbn)) {                
			upMenuId = propertyService.getProperty("secfw.menu.topMenuId");
			hm.put("up_menu_id", upMenuId);
			hm.put("topMenuLevel", topMenuLevel);
		} else {
			HashMap paramMap = new HashMap();
			paramMap.put("sys_cd", sysCd);
			//paramMap.put("sys_cd", "CPIS");
			paramMap.put("menu_id", selectMenuId);
			
			int menuLevel = -1;
			do {
				List findList = commonDAO.list("secfw.menu.findUpMenuId", paramMap);

				if (findList != null && findList.size() > 0) {
					ListOrderedMap lom = (ListOrderedMap)findList.get(0);
					menuLevel = Integer.parseInt(StringUtil.bvl(lom.get("menu_level"), "1"));
					
					if (menuLevel == 1) {
						upMenuId = StringUtil.bvl(lom.get("menu_id"), "");
					}else {
						paramMap.put("menu_id", (String)lom.get("up_menu_id"));
					}
				}else {
					menuLevel = 1;
					upMenuId = selectMenuId;
				}
			}while (menuLevel != 1);
			
			hm.put("up_menu_id", upMenuId);
		}
		
		//메뉴리스트
		menuList = commonDAO.list("secfw.menu.listMenuAuth", hm);
		resultMap.put("menuList", menuList);
		
		//최상위 메뉴명 
		menuNmList = commonDAO.list("secfw.menu.topMenuNm", hm);
		if(menuNmList != null && menuNmList.size()>0){
			ListOrderedMap mNm = (ListOrderedMap)menuNmList.get(0);
			menuNm = (String)mNm.get("menu_nm");
		}
		resultMap.put("menuNm", menuNm);
		
		return resultMap;
	}
	
	/**
	 * 계약유형 코드를 가져온다.(계약서 상세검색용)
	 */
	 public String getMasterCombo(String comboflag, String upCd, SearchVO vo) throws Exception {
		String result = "";
		List list = null;
		
		if("getBiz_clsfcn".equals(comboflag)) {
			list = commonDAO.list("clm.search.getBiz_clsfcn", vo); //계약유형 1코드를 가져온다.
		} else if("getDepth_clsfcn".equals(comboflag)) {
			list = commonDAO.list("clm.search.getDepth_clsfcn", vo); //계약유형 2코드를 가져온다.
		} else if("getCnclsnpurps_bigclsfcn".equals(comboflag)) {
			list = commonDAO.list("clm.search.getCnclsnpurps_bigclsfcn", vo); //계약유형 3코드를 가져온다.
		} else if("getCnclsnpurps_midclsfcn".equals(comboflag)) {
			HashMap iHm = new HashMap();
			iHm.put("up_cd", upCd);
			iHm.put("user_locale", vo.getSession_user_locale());
			list = commonDAO.list("clm.search.getCnclsnpurps_midclsfcn", iHm); //계약유형 4코드를 가져온다.
		}
		
		result = "<option value='' selected>-- ALL --</option>";

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)list.get(i);
				String type_cd		   = (String)lom.get("TYPE_CD"); //계약유형코드		
				String cd_nm	       = (String)lom.get("CD_NM"); //계약유형명		
				
				result = result+"<option value='"+type_cd+"'>"+cd_nm+"</option>";
			}
		}

		return result;
	 }
	 
}
