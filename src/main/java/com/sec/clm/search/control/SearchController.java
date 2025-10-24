package com.sec.clm.search.control;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.search.common.util.DateUtil;
import com.sec.clm.search.dto.SearchForm;
import com.sec.clm.search.dto.SearchVO;
import com.sec.clm.search.service.SearchService;

public class SearchController extends CommonController {
	private SearchService searchService;

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	// 통합검색 패스 (프레임셋을 이용한 Top 출력을 위함)
	public ModelAndView passSearch(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView();

		// 파라메터를 그대로 전달하되 method를 listSearch로 수정한다.
		Map<String, String[]> parameters = request.getParameterMap();
		for (String parameter : parameters.keySet()) {
			String[] values = null;
			if (parameter.equals("method")) {
				values = new String[] { "listSearch" };
			} else {
				values = parameters.get(parameter);
			}
			mav.addObject(parameter, values);
		}

		String forwardURL = "/WEB-INF/jsp/clm/search/SearchFrameMain.jsp";
		mav.setViewName(forwardURL);

		return mav;
	}

	// 통합검색
	public ModelAndView listSearch(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			//HttpSession session = request.getSession(false);

			SearchForm form = new SearchForm();
			SearchVO vo = new SearchVO();

			bind(request, form);
			bind(request, vo);

			// 계약서 권한검색을 하기위해 사용자 정보를 세팅한다.
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			// 처음부터 3건의 검색결과만 가져오도록 세팅한다.
			vo.setStart_index("1");
			vo.setEnd_index("3");
			// 시스템 코드를 가져온다.(CLM : 계약관리, LAS : 법무)
			// vo.setSys_cd((String)
			// session.getAttribute("secfw.session.sys_cd"));
			vo.setSys_cd("CLM");

			// 검색결과 건수
			int totalSearchCount = 0;
			int resultListG_Count = 0;
			int resultListG2_Count = 0;
			int resultListG3_Count = 0;
			int resultList0_Count = 0;
			int resultList1_Count = 0;
			int resultList2_Count = 0;
			int resultList3_Count = 0;
			int resultList4_Count = 0;
			int resultList5_Count = 0;
			int resultList6_Count = 0;
			int resultList7_Count = 0;
			int resultList8_Count = 0;
			int resultList9_Count = 0;
			int resultList10_Count = 0;
			int resultList11_Count = 0;
			int resultList12_Count = 0;
			int resultList13_Count = 0;
			String returnMessage = "";

			// 계약서에서 날짜 상세검색시 YYYY-MM-DD 형식을 IDOL날짜 검색형식에 맞게 DD/MM/YYYY로 변경해 준다.
			vo.setSrchMinDate(DateUtil.getChangeDateFormat(StringUtil.bvl(
					form.getSrchMinDate(), "")));
			vo.setSrchMaxDate(DateUtil.getChangeDateFormat(StringUtil.bvl(
					form.getSrchMaxDate(), "")));

			// 이 경우는 없지만 에러를 방지하기 위해 값이 없을경우 전체 색인DB를 setting한다.
			if (form.getSrch_index_db() == null
					|| "".equals(form.getSrch_index_db())) {
//				if ("CLM".equals(vo.getSys_cd())) {
//					// 2011.12.21 clm_share_customer 제거
//					// form.setSrch_index_db("clm_master_consideration,clm_master_consultation,clm_master_conclusion,clm_master_execution,clm_master_completion,clm_about,clm_library,clm_rule_regulation,clm_rule_decision,clm_share_contracttype,clm_share_rule,clm_share_terms,clm_share_customer,clm_counsel_notice,clm_counsel_faq,clm_counsel_qna");
//					form.setSrch_index_db("clm_master_consideration,clm_master_consultation,clm_master_conclusion,clm_master_execution,clm_master_completion,clm_master_orglastransfer,clm_master_divisiontransfer,clm_about,clm_library,clm_rule_regulation,clm_rule_decision,clm_share_contracttype,clm_share_rule,clm_share_terms,clm_counsel_notice,clm_counsel_faq,clm_counsel_qna");
//				} else {
//					form.setSrch_index_db("las_master_consideration,las_master_consultation,las_master_conclusion,las_master_execution,las_master_completion,las_master_orglastransfer,las_master_divisiontransfer,clm_about,clm_library,clm_rule_regulation,clm_rule_decision,clm_share_contracttype,clm_share_rule,clm_share_terms,clm_counsel_notice,clm_counsel_faq,clm_counsel_qna");
//				}
				form.setSrch_index_db("clm_master_consideration,clm_master_consultation,clm_master_conclusion,clm_master_execution,clm_master_completion,clm_about,clm_rule_regulation,clm_share_terms,clm_counsel_notice,clm_counsel_faq,clm_counsel_qna");
			}

			// 계약관리 > 계약검토(계약관리용색인DB : clm_master_consideration, 법무용색인DB :
			// las_master_consideration)
			List resultList0 = null;
//			if ("CLM".equals(vo.getSys_cd())) {
			if (form.getSrch_index_db().contains("clm_master_consideration")) {
				vo.setSrch_index_db("clm_master_consideration");
				resultList0 = searchService.listSearch(vo);
			}
//			} else {
//				if (form.getSrch_index_db()
//						.contains("las_master_consideration")) {
//					vo.setSrch_index_db("las_master_consideration");
//					resultList0 = searchService.listSearch(vo);
//				}
//			}

			// 검색결과가 있으면 검색건수를 저장한다.
			if (resultList0 != null && resultList0.size() > 0) {
				SearchVO resultvo = (SearchVO) resultList0.get(0);
				resultList0_Count = resultvo.getTotal_cnt();
				resultListG_Count += resultvo.getTotal_cnt();
				totalSearchCount += resultvo.getTotal_cnt();
			}

			// 계약관리 > 계약체결(계약관리용색인DB : clm_master_consultation, 법무용색인DB :
			// las_master_consultation)
			List resultList1 = null;
//			if ("CLM".equals(vo.getSys_cd())) {
			if (form.getSrch_index_db().contains("clm_master_consultation")) {
				vo.setSrch_index_db("clm_master_consultation");
				resultList1 = searchService.listSearch(vo);
			}
//			} else {
//				if (form.getSrch_index_db().contains("las_master_consultation")) {
//					vo.setSrch_index_db("las_master_consultation");
//					resultList1 = searchService.listSearch(vo);
//				}
//			}

			if (resultList1 != null && resultList1.size() > 0) {
				SearchVO resultvo = (SearchVO) resultList1.get(0);
				resultList1_Count = resultvo.getTotal_cnt();
				resultListG_Count += resultvo.getTotal_cnt();
				totalSearchCount += resultvo.getTotal_cnt();
			}

			// 계약관리 > 계약등록(계약관리용색인DB : clm_master_conclusion, 법무용색인DB :
			// las_master_conclusion)
			List resultList2 = null;
//			if ("CLM".equals(vo.getSys_cd())) {
			if (form.getSrch_index_db().contains("clm_master_conclusion")) {
				vo.setSrch_index_db("clm_master_conclusion");
				resultList2 = searchService.listSearch(vo);
			}
//			} else {
//				if (form.getSrch_index_db().contains("las_master_conclusion")) {
//					vo.setSrch_index_db("las_master_conclusion");
//					resultList2 = searchService.listSearch(vo);
//				}
//			}

			if (resultList2 != null && resultList2.size() > 0) {
				SearchVO resultvo = (SearchVO) resultList2.get(0);
				resultList2_Count = resultvo.getTotal_cnt();
				resultListG_Count += resultvo.getTotal_cnt();
				totalSearchCount += resultvo.getTotal_cnt();
			}

			// 계약관리 > 계약이행(계약관리용색인DB : clm_master_execution, 법무용색인DB :
			// las_master_execution)
			List resultList3 = null;
//			if ("CLM".equals(vo.getSys_cd())) {
			if (form.getSrch_index_db().contains("clm_master_execution")) {
				vo.setSrch_index_db("clm_master_execution");
				resultList3 = searchService.listSearch(vo);
			}
//			} else {
//				if (form.getSrch_index_db().contains("las_master_execution")) {
//					vo.setSrch_index_db("las_master_execution");
//					resultList3 = searchService.listSearch(vo);
//				}
//			}

			if (resultList3 != null && resultList3.size() > 0) {
				SearchVO resultvo = (SearchVO) resultList3.get(0);
				resultList3_Count = resultvo.getTotal_cnt();
				resultListG_Count += resultvo.getTotal_cnt();
				totalSearchCount += resultvo.getTotal_cnt();
			}

			// 계약관리 > 계약종료(계약관리용색인DB : clm_master_completion, 법무용색인DB :
			// las_master_completion)
			List resultList4 = null;
//			if ("CLM".equals(vo.getSys_cd())) {
			if (form.getSrch_index_db().contains("clm_master_completion")) {
				vo.setSrch_index_db("clm_master_completion");
				resultList4 = searchService.listSearch(vo);
			}
//			} else {
//				if (form.getSrch_index_db().contains("las_master_completion")) {
//					vo.setSrch_index_db("las_master_completion");
//					resultList4 = searchService.listSearch(vo);
//				}
//			}

			if (resultList4 != null && resultList4.size() > 0) {
				SearchVO resultvo = (SearchVO) resultList4.get(0);
				resultList4_Count = resultvo.getTotal_cnt();
				resultListG_Count += resultvo.getTotal_cnt();
				totalSearchCount += resultvo.getTotal_cnt();
			}

			// 계약관리 > 구법무이관(계약관리용색인DB : clm_master_orglastransfer, 법무용색인DB :
			// las_master_divisiontransfer)
//			List resultList5 = null;
//			if ("CLM".equals(vo.getSys_cd())) {
//				if (form.getSrch_index_db().contains(
//						"clm_master_orglastransfer")) {
//					vo.setSrch_index_db("clm_master_orglastransfer");
//					resultList5 = searchService.listSearch(vo);
//				}
//			} else {
//				if (form.getSrch_index_db().contains(
//						"las_master_orglastransfer")) {
//					vo.setSrch_index_db("las_master_orglastransfer");
//					resultList5 = searchService.listSearch(vo);
//				}
//			}

//			if (resultList5 != null && resultList5.size() > 0) {
//				SearchVO resultvo = (SearchVO) resultList5.get(0);
//				resultList5_Count = resultvo.getTotal_cnt();
//				resultListG_Count += resultvo.getTotal_cnt();
//				totalSearchCount += resultvo.getTotal_cnt();
//			}

			// 계약관리 > 사업부이관(계약관리용색인DB : clm_master_divisiontransfer, 법무용색인DB :
			// las_master_divisiontransfer)
//			List resultList6 = null;
//			if ("CLM".equals(vo.getSys_cd())) {
//				if (form.getSrch_index_db().contains(
//						"clm_master_divisiontransfer")) {
//					vo.setSrch_index_db("las_master_divisiontransfer");
//					resultList6 = searchService.listSearch(vo);
//				}
//			} else {
//				if (form.getSrch_index_db().contains(
//						"clm_master_divisiontransfer")) {
//					vo.setSrch_index_db("las_master_divisiontransfer");
//					resultList6 = searchService.listSearch(vo);
//				}
//			}

//			if (resultList6 != null && resultList6.size() > 0) {
//				SearchVO resultvo = (SearchVO) resultList6.get(0);
//				resultList6_Count = resultvo.getTotal_cnt();
//				resultListG_Count += resultvo.getTotal_cnt();
//				totalSearchCount += resultvo.getTotal_cnt();
//			}

//			/*********************************************************
//			 * 상단메뉴관련 (MainController 로직복사)
//			 **********************************************************/
//			String sysCd = (String) session
//					.getAttribute("secfw.session.sys_cd");
//			String userId = (String) session
//					.getAttribute("secfw.session.user_id");

//			HashMap paramMap = new HashMap();
//			paramMap.put("menuGbn", "TOP");
//			paramMap.put("targetMenuId", "");
//			paramMap.put("sys_cd", sysCd);
//			paramMap.put("user_id", userId);

//			HashMap hm = searchService.listMenu(paramMap);
//			List menuList = (List) hm.get("menuList");

//			List resultList7 = null;
//			List resultList8 = null;
//			List resultList9 = null;
//			List resultList10 = null;
//			List resultList11 = null;
//			List resultList12 = null;
//			List resultList13 = null;

//			boolean menuAuth = false;

//			if ("CLM".equals(vo.getSys_cd())) {
//				menuAuth = true;
//			} else {
//				if (menuList != null && menuList.size() > 0) {
//					String compMenuId = "";
//
//					for (int i = 0; i < menuList.size(); i++) {
//						ListOrderedMap lom = (ListOrderedMap) menuList.get(i);
//						compMenuId = (String) lom.get("menu_id");
//						if ("20111021141939360_0000000214".equals(compMenuId)) {
//							menuAuth = true;
//							break;
//						}
//					}
//				}
//			}
			
			// Legal Advice > Legal Advice Review
			List resultList5 = null;
			if (form.getSrch_index_db().contains("clm_advice_review")) {
				vo.setSrch_index_db("clm_advice_review");
				resultList5 = searchService.listSearch(vo);
				
				if (resultList5 != null && resultList5.size() > 0) {
					SearchVO resultvo = (SearchVO) resultList5.get(0);
					resultList5_Count = resultvo.getTotal_cnt();
					resultListG2_Count += resultvo.getTotal_cnt();
					totalSearchCount += resultvo.getTotal_cnt();
				}
			}
			
			// Legal Advice > Legal Advice Request
			List resultList6 = null;
			if (form.getSrch_index_db().contains("clm_advice_request")) {
				vo.setSrch_index_db("clm_advice_request");
				resultList6 = searchService.listSearch(vo);
				
				if (resultList6 != null && resultList6.size() > 0) {
					SearchVO resultvo = (SearchVO) resultList6.get(0);
					resultList6_Count = resultvo.getTotal_cnt();
					resultListG2_Count += resultvo.getTotal_cnt();
					totalSearchCount += resultvo.getTotal_cnt();
				}
			}
						
			// Legal Advice > Legal Advice Review
			List resultList7 = null;
			if (form.getSrch_index_db().contains("clm_advice_ntts")) {
				vo.setSrch_index_db("clm_advice_ntts");
				resultList7 = searchService.listSearch(vo);
				
				if (resultList7 != null && resultList7.size() > 0) {
					SearchVO resultvo = (SearchVO) resultList7.get(0);
					resultList7_Count = resultvo.getTotal_cnt();
					resultListG2_Count += resultvo.getTotal_cnt();
					totalSearchCount += resultvo.getTotal_cnt();
				}
			}

//			if (menuAuth) {
			// About CLM(clm_about)
				// List resultList5 = null;
			List resultList8 = null;
			if (form.getSrch_index_db().contains("clm_about")) {
				vo.setSrch_index_db("clm_about");
				resultList8 = searchService.listSearch(vo);
			}

			if (resultList8 != null && resultList8.size() > 0) {
				SearchVO resultvo = (SearchVO) resultList8.get(0);
				resultList8_Count = resultvo.getTotal_cnt();
				totalSearchCount += resultvo.getTotal_cnt();
			}

			// 계약라이브러리(clm_library)
			// List resultList6 = null;
//			if (form.getSrch_index_db().contains("clm_library")) {
//				vo.setSrch_index_db("clm_library");
//				resultList8 = searchService.listSearch(vo);
//			}

//			if (resultList8 != null && resultList8.size() > 0) {
//				SearchVO resultvo = (SearchVO) resultList8.get(0);
//				resultList8_Count = resultvo.getTotal_cnt();
//				totalSearchCount += resultvo.getTotal_cnt();
//			}

			// 계약관리규정(전사계약업무규정 : clm_rule_regulation, 체결품의전결규정 :
			// clm_rule_decision)
			// List resultList7 = null;
			List resultList9 = null;
//			if (form.getSrch_index_db().contains("clm_rule_regulation,clm_rule_decision")) {
//				vo.setSrch_index_db("clm_rule_regulation,clm_rule_decision");
			if (form.getSrch_index_db().contains("clm_rule_regulation")) {
				vo.setSrch_index_db("clm_rule_regulation");
				resultList9 = searchService.listSearch(vo);
			}

			if (resultList9 != null && resultList9.size() > 0) {
				SearchVO resultvo = (SearchVO) resultList9.get(0);
				resultList9_Count = resultvo.getTotal_cnt();
				totalSearchCount += resultvo.getTotal_cnt();
			}

			// 계약지식공유(계약유형정의 : clm_share_contracttype, 계약유형별이해 :
			// clm_share_rule, 계약용어집 : clm_share_terms, 계약상대방 :
			// clm_share_customer)
			// List resultList8 = null;
			List resultList10 = null;
//			if ("CLM".equals(vo.getSys_cd())) {
				// if(form.getSrch_index_db().contains("clm_share_contracttype,clm_share_rule,clm_share_terms,clm_share_customer"))
				// {
				// vo.setSrch_index_db("clm_share_contracttype,clm_share_rule,clm_share_terms,clm_share_customer");
//			if (form.getSrch_index_db().contains("clm_share_contracttype,clm_share_rule,clm_share_terms")) {
//				vo.setSrch_index_db("clm_share_contracttype,clm_share_rule,clm_share_terms");
			if (form.getSrch_index_db().contains("clm_share_terms")) {
				vo.setSrch_index_db("clm_share_terms");
				resultList10 = searchService.listSearch(vo);
			}
//			} else {
//				// 법무쪽은 계약상대방 카테고리를 검색하지 않음
//				if (form.getSrch_index_db()
//						.contains(
//								"clm_share_contracttype,clm_share_rule,clm_share_terms")) {
//					vo.setSrch_index_db("clm_share_contracttype,clm_share_rule,clm_share_terms");
//					resultList10 = searchService.listSearch(vo);
//				}
//			}

			if (resultList10 != null && resultList10.size() > 0) {
				SearchVO resultvo = (SearchVO) resultList10.get(0);
				resultList10_Count = resultvo.getTotal_cnt();
				totalSearchCount += resultvo.getTotal_cnt();
			}

			// 공지 및 Q&A > 전사공지사항 (clm_counsel_notice)
			// List resultList9 = null;
			List resultList11 = null;
			if (form.getSrch_index_db().contains("clm_counsel_notice")) {
				vo.setSrch_index_db("clm_counsel_notice");
				resultList11 = searchService.listSearch(vo);
			}

			if (resultList11 != null && resultList11.size() > 0) {
				SearchVO resultvo = (SearchVO) resultList11.get(0);
//				int filterNum = 0;
//				// 필터링된 건수를 파악하여 총건수에서 뺀다.
//				if (resultvo.getTotal_cnt() > 3) {
//					filterNum = 3 - resultList9.size();
//				} else {
//					filterNum = resultvo.getTotal_cnt() - resultList11.size();
//				}
//				resultList11_Count = resultvo.getTotal_cnt() - filterNum;
//				resultListG3_Count += resultvo.getTotal_cnt() - filterNum;
//				totalSearchCount += resultvo.getTotal_cnt() - filterNum;
				resultList11_Count = resultvo.getTotal_cnt();
				resultListG3_Count += resultvo.getTotal_cnt();
				totalSearchCount += resultvo.getTotal_cnt();
			}

			// 공지 및 Q&A > FAQ (clm_counsel_faq)
			// List resultList10 = null;
			List resultList12 = null;
			if (form.getSrch_index_db().contains("clm_counsel_faq")) {
				vo.setSrch_index_db("clm_counsel_faq");
				resultList12 = searchService.listSearch(vo);
			}

			if (resultList12 != null && resultList12.size() > 0) {
				SearchVO resultvo = (SearchVO) resultList12.get(0);
				resultList12_Count = resultvo.getTotal_cnt();
				resultListG3_Count += resultvo.getTotal_cnt();
				totalSearchCount += resultvo.getTotal_cnt();
			}

			// 공지 및 Q&A > Q&A (clm_counsel_qna)
			// List resultList11 = null;
			List resultList13 = null;
			if (form.getSrch_index_db().contains("clm_counsel_qna")) {
				vo.setSrch_index_db("clm_counsel_qna");
				resultList13 = searchService.listSearch(vo);
			}

			if (resultList13 != null && resultList13.size() > 0) {
				SearchVO resultvo = (SearchVO) resultList13.get(0);
				resultList13_Count = resultvo.getTotal_cnt();
				resultListG3_Count += resultvo.getTotal_cnt();
				totalSearchCount += resultvo.getTotal_cnt();
			}
//			}

			/*
			 * //About CLM(clm_about) List resultList5 = null;
			 * if(form.getSrch_index_db().contains("clm_about")) {
			 * vo.setSrch_index_db("clm_about"); resultList5 =
			 * searchService.listSearch(vo); }
			 * 
			 * if (resultList5 != null && resultList5.size() > 0) { SearchVO
			 * resultvo = (SearchVO)resultList5.get(0); resultList5_Count =
			 * resultvo.getTotal_cnt(); totalSearchCount +=
			 * resultvo.getTotal_cnt(); }
			 * 
			 * //계약라이브러리(clm_library) List resultList6 = null;
			 * if(form.getSrch_index_db().contains("clm_library")) {
			 * vo.setSrch_index_db("clm_library"); resultList6 =
			 * searchService.listSearch(vo); }
			 * 
			 * if (resultList6 != null && resultList6.size() > 0) { SearchVO
			 * resultvo = (SearchVO)resultList6.get(0); resultList6_Count =
			 * resultvo.getTotal_cnt(); totalSearchCount +=
			 * resultvo.getTotal_cnt(); }
			 * 
			 * //계약관리규정(전사계약업무규정 : clm_rule_regulation, 체결품의전결규정 :
			 * clm_rule_decision) List resultList7 = null;
			 * if(form.getSrch_index_db
			 * ().contains("clm_rule_regulation,clm_rule_decision")) {
			 * vo.setSrch_index_db("clm_rule_regulation,clm_rule_decision");
			 * resultList7 = searchService.listSearch(vo); }
			 * 
			 * if (resultList7 != null && resultList7.size() > 0) { SearchVO
			 * resultvo = (SearchVO)resultList7.get(0); resultList7_Count =
			 * resultvo.getTotal_cnt(); totalSearchCount +=
			 * resultvo.getTotal_cnt(); }
			 * 
			 * //계약지식공유(계약유형정의 : clm_share_contracttype, 계약유형별이해 :
			 * clm_share_rule, 계약용어집 : clm_share_terms, 계약상대방 :
			 * clm_share_customer) List resultList8 = null;
			 * if("CLM".equals(vo.getSys_cd())) {
			 * if(form.getSrch_index_db().contains(
			 * "clm_share_contracttype,clm_share_rule,clm_share_terms,clm_share_customer"
			 * )) { vo.setSrch_index_db(
			 * "clm_share_contracttype,clm_share_rule,clm_share_terms,clm_share_customer"
			 * ); if(form.getSrch_index_db().contains(
			 * "clm_share_contracttype,clm_share_rule,clm_share_terms")) {
			 * vo.setSrch_index_db
			 * ("clm_share_contracttype,clm_share_rule,clm_share_terms");
			 * resultList8 = searchService.listSearch(vo); } } else {
			 * if(form.getSrch_index_db
			 * ().contains("clm_share_contracttype,clm_share_rule,clm_share_terms"
			 * )) { //법무쪽은 계약상대방 카테고리를 검색하지 않음 vo.setSrch_index_db(
			 * "clm_share_contracttype,clm_share_rule,clm_share_terms");
			 * resultList8 = searchService.listSearch(vo); } }
			 * 
			 * if (resultList8 != null && resultList8.size() > 0) { SearchVO
			 * resultvo = (SearchVO)resultList8.get(0); resultList8_Count =
			 * resultvo.getTotal_cnt(); totalSearchCount +=
			 * resultvo.getTotal_cnt(); }
			 * 
			 * //공지 및 Q&A > 전사공지사항 (clm_counsel_notice) List resultList9 = null;
			 * if(form.getSrch_index_db().contains("clm_counsel_notice")) {
			 * vo.setSrch_index_db("clm_counsel_notice"); resultList9 =
			 * searchService.listSearch(vo); }
			 * 
			 * if (resultList9 != null && resultList9.size() > 0) { SearchVO
			 * resultvo = (SearchVO)resultList9.get(0); int filterNum = 0;
			 * //필터링된 건수를 파악하여 총건수에서 뺀다. if(resultvo.getTotal_cnt() > 3) {
			 * filterNum = 3 - resultList9.size(); } else { filterNum =
			 * resultvo.getTotal_cnt() - resultList9.size(); } resultList9_Count
			 * = resultvo.getTotal_cnt() - filterNum; resultListG2_Count +=
			 * resultvo.getTotal_cnt() - filterNum; totalSearchCount +=
			 * resultvo.getTotal_cnt() - filterNum; }
			 * 
			 * //공지 및 Q&A > FAQ (clm_counsel_faq) List resultList10 = null;
			 * if(form.getSrch_index_db().contains("clm_counsel_faq")) {
			 * vo.setSrch_index_db("clm_counsel_faq"); resultList10 =
			 * searchService.listSearch(vo); }
			 * 
			 * if (resultList10 != null && resultList10.size() > 0) { SearchVO
			 * resultvo = (SearchVO)resultList10.get(0); resultList10_Count =
			 * resultvo.getTotal_cnt(); resultListG2_Count +=
			 * resultvo.getTotal_cnt(); totalSearchCount +=
			 * resultvo.getTotal_cnt(); }
			 * 
			 * //공지 및 Q&A > Q&A (clm_counsel_qna) List resultList11 = null;
			 * if(form.getSrch_index_db().contains("clm_counsel_qna")) {
			 * vo.setSrch_index_db("clm_counsel_qna"); resultList11 =
			 * searchService.listSearch(vo); }
			 * 
			 * if (resultList11 != null && resultList11.size() > 0) { SearchVO
			 * resultvo = (SearchVO)resultList11.get(0); resultList11_Count =
			 * resultvo.getTotal_cnt(); resultListG2_Count +=
			 * resultvo.getTotal_cnt(); totalSearchCount +=
			 * resultvo.getTotal_cnt(); }
			 */

			if ((String) request.getAttribute("returnMessage") != null	&& ((String) request.getAttribute("returnMessage")).length() > 1) {
				returnMessage = (String) request.getAttribute("returnMessage");
			}

			String forwardURL = "/WEB-INF/jsp/clm/search/Search_l.jsp";

			// 세션을 체크해서 계약또는법무검색페이지로 설정한다.
//			if ("CLM".equals(vo.getSys_cd())) {
//				forwardURL = "/WEB-INF/jsp/clm/search/Search_l.jsp"; // 계약 통합검색
//			} else {
//				forwardURL = "/WEB-INF/jsp/las/search/Search_l.jsp"; // 법무 통합검색
//			}

			ModelAndView mav = new ModelAndView();

			/*********************************************************
			 * 상단메뉴관련 (MainController 로직복사)
			 **********************************************************/
			/*
			 * String sysCd =
			 * (String)session.getAttribute("secfw.session.sys_cd"); String
			 * userId = (String)session.getAttribute("secfw.session.user_id");
			 * 
			 * HashMap paramMap = new HashMap(); paramMap.put("menuGbn" ,
			 * "TOP"); paramMap.put("targetMenuId" , ""); paramMap.put("sys_cd"
			 * , sysCd); paramMap.put("user_id" , userId);
			 * 
			 * HashMap hm = searchService.listMenu(paramMap); List menuList =
			 * (List)hm.get("menuList");
			 */
//			Map map = new HashMap();
//			int Level_One = 0;
//			if (menuList != null && menuList.size() > 0) {
//				String compMenuId = "";
//
//				for (int i = 0; i < menuList.size(); i++) {
//					ListOrderedMap lom = (ListOrderedMap) menuList.get(i);
//
//					if ("1".equals(StringUtil.bvl(lom.get("menu_level"), ""))) {
//						compMenuId = (String) lom.get("menu_id");
//						map.put(compMenuId, new Integer(0));
//						Level_One++;
//					} /*
//					 * else { if (map.get(lom.get("up_menu_id")) != null) { int
//					 * cnt = ((Integer) map.get(lom.get("up_menu_id")))
//					 * .intValue() + 1; map.put((String) lom.get("up_menu_id"),
//					 * new Integer(cnt)); } }
//					 */
//				}
//			}

			// 1레벨의 싸이즈
//			mav.addObject("menuSize", Integer.toString(Level_One));
//			mav.addObject("menuList", menuList);
//			mav.addObject("itvocUrl",
//					propertyService.getProperty("secfw.url.itvoc"));
//			mav.addObject("clmLoginUrl",
//					propertyService.getProperty("secfw.url.clmLogin"));
//			mav.addObject("slmsUrl",
//					propertyService.getProperty("secfw.url.slms"));

			/*********************************************************
			 * 상단메뉴관련 끝
			 **********************************************************/

			mav.setViewName(forwardURL);
			mav.addObject("resultList0", resultList0);
			mav.addObject("resultList1", resultList1);
			mav.addObject("resultList2", resultList2);
			mav.addObject("resultList3", resultList3);
			mav.addObject("resultList4", resultList4);
			mav.addObject("resultList5", resultList5);
			mav.addObject("resultList6", resultList6);
			mav.addObject("resultList7", resultList7);
			mav.addObject("resultList8", resultList8);
			mav.addObject("resultList9", resultList9);
			mav.addObject("resultList10", resultList10);
			mav.addObject("resultList11", resultList11);
			mav.addObject("resultList12", resultList12);
			mav.addObject("resultList13", resultList13);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			mav.addObject("totalSearchCount", totalSearchCount);
			mav.addObject("resultListG_Count", resultListG_Count);
			mav.addObject("resultListG2_Count", resultListG2_Count);
			mav.addObject("resultListG3_Count", resultListG3_Count);
			mav.addObject("resultList0_Count", resultList0_Count);
			mav.addObject("resultList1_Count", resultList1_Count);
			mav.addObject("resultList2_Count", resultList2_Count);
			mav.addObject("resultList3_Count", resultList3_Count);
			mav.addObject("resultList4_Count", resultList4_Count);
			mav.addObject("resultList5_Count", resultList5_Count);
			mav.addObject("resultList6_Count", resultList6_Count);
			mav.addObject("resultList7_Count", resultList7_Count);
			mav.addObject("resultList8_Count", resultList8_Count);
			mav.addObject("resultList9_Count", resultList9_Count);
			mav.addObject("resultList10_Count", resultList10_Count);
			mav.addObject("resultList11_Count", resultList11_Count);
			mav.addObject("resultList12_Count", resultList12_Count);
			mav.addObject("resultList13_Count", resultList13_Count);

			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	// 개별검색
	public ModelAndView listDetailSearch(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
//			HttpSession session = request.getSession(false);

			SearchForm form = new SearchForm();
			SearchVO vo = new SearchVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			PageUtil pageUtil = new PageUtil();

			// 페이지 유틸을 사용하여 페이지번호에 알맞는 시작점부터 20건의 검색결과를 가져오게 세팅한다.
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(
					form.getCurPage(), "1")));
			pageUtil.setRowPerPage(20);

			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			//vo.setSys_cd((String) session.getAttribute("secfw.session.sys_cd"));
			vo.setSys_cd("CLM");

			// 총 검색결과 건수
			int totalSearchCount = 0;
			String returnMessage = "";
			String indexSysName = "";
			String writerText = null;

			// 계약서에서 날짜 상세검색시 YYYY-MM-DD 형식을 IDOL날짜 검색형식에 맞게 DD/MM/YYYY로 변경해 준다.
			vo.setSrchMinDate(DateUtil.getChangeDateFormat(StringUtil.bvl(
					form.getSrchMinDate(), "")));
			vo.setSrchMaxDate(DateUtil.getChangeDateFormat(StringUtil.bvl(
					form.getSrchMaxDate(), "")));

			vo.setSrch_index_db(StringUtil.bvl(form.getSrch_index_db(), ""));
			List resultList = null;

			/*********************************************************
			 * 상단메뉴관련 (MainController 로직복사)
			 **********************************************************/

//			String sysCd = (String) session
//					.getAttribute("secfw.session.sys_cd");
//			String userId = (String) session
//					.getAttribute("secfw.session.user_id");
//
//			HashMap paramMap = new HashMap();
//			paramMap.put("menuGbn", "TOP");
//			paramMap.put("targetMenuId", "");
//			paramMap.put("sys_cd", sysCd);
//			paramMap.put("user_id", userId);
//
//			HashMap hm = searchService.listMenu(paramMap);
//			List menuList = (List) hm.get("menuList");
//
//			boolean menuAuth = false;
//
//			if ("CLM".equals(vo.getSys_cd())) {
//				menuAuth = true;
//			} else {
//				if (menuList != null && menuList.size() > 0) {
//					String compMenuId = "";
//
//					for (int i = 0; i < menuList.size(); i++) {
//						ListOrderedMap lom = (ListOrderedMap) menuList.get(i);
//						compMenuId = (String) lom.get("menu_id");
//						if ("20111021141939360_0000000214".equals(compMenuId)) {
//							menuAuth = true;
//							break;
//						}
//					}
//				}
//			}

			//if (!"".equals(vo.getSrch_index_db()) && menuAuth) {
			if (!"".equals(vo.getSrch_index_db())) {
				resultList = searchService.listSearch(vo);
			}

			// if(!"".equals(vo.getSrch_index_db())) {
			// resultList = searchService.listSearch(vo);
			// }

			if (resultList != null && resultList.size() > 0) {
				SearchVO resultvo = (SearchVO) resultList.get(0);
				int filterNum = 0;
				if ("clm_counsel_notice".equals(vo.getSrch_index_db())) {
					// 공지사항검색인 경우 필터링된 건수를 파악하여 총건수에서 뺀다.
					if (resultvo.getTotal_cnt() > 20) {
						filterNum = 20 - resultList.size();
					} else {
						filterNum = resultvo.getTotal_cnt() - resultList.size();
					}
				}
				totalSearchCount += resultvo.getTotal_cnt() - filterNum;
				// 페이지 처리
				pageUtil.setTotalRow(totalSearchCount);
				pageUtil.setGroup();
			}

			// 검색대상 색인DB를 체크하여 검색하는 카테고리명을 출력할수 있도록 세팅한다.(메시지 프로퍼티에 명시된 ID)
			if ("clm_master_consideration".equals(vo.getSrch_index_db())
					|| "clm_master_consultation".equals(vo.getSrch_index_db())
					|| "clm_master_conclusion".equals(vo.getSrch_index_db())
					|| "clm_master_execution".equals(vo.getSrch_index_db())
					|| "clm_master_completion".equals(vo.getSrch_index_db())) {
//					|| "clm_master_orglastransfer".equals(vo.getSrch_index_db())
//					|| "clm_master_divisiontransfer".equals(vo.getSrch_index_db())
//					|| "las_master_consideration".equals(vo.getSrch_index_db())
//					|| "las_master_consultation".equals(vo.getSrch_index_db())
//					|| "las_master_conclusion".equals(vo.getSrch_index_db())
//					|| "las_master_execution".equals(vo.getSrch_index_db())
//					|| "las_master_completion".equals(vo.getSrch_index_db())
//					|| "las_master_orglastransfer".equals(vo.getSrch_index_db())
//					|| "las_master_divisiontransfer".equals(vo.getSrch_index_db())) {
				indexSysName = "clm.page.field.srch.contract"; // 계약서
				writerText = "clm.page.field.srch.srchReqmanNm";
			} else if ("clm_advice_review".equals(vo.getSrch_index_db())
						|| "clm_advice_request".equals(vo.getSrch_index_db())
						|| "clm_advice_ntts".equals(vo.getSrch_index_db())) {
				indexSysName = "clm.page.field.srch.advice"; // 자문
				writerText = "clm.page.field.srch.srchReqmanNm";
			} else if ("clm_about".equals(vo.getSrch_index_db())) {
				indexSysName = "clm.page.field.srch.about"; // About CLM
//			} else if ("clm_library".equals(vo.getSrch_index_db())) {
//				indexSysName = "clm.page.field.srch.library"; // 계약라이브러리
//			} else if ("clm_rule_regulation,clm_rule_decision".equals(vo.getSrch_index_db())) {
			} else if ("clm_rule_regulation".equals(vo.getSrch_index_db())) {
				indexSysName = "clm.page.field.srch.rule"; // 계약관리규정
				// } else
				// if("clm_share_contracttype,clm_share_rule,clm_share_terms,clm_share_customer".equals(vo.getSrch_index_db())
				// ||
				// "clm_share_contracttype,clm_share_rule,clm_share_terms".equals(vo.getSrch_index_db()))
				// {
//			} else if ("clm_share_contracttype,clm_share_rule,clm_share_terms".equals(vo.getSrch_index_db())
//					|| "clm_share_contracttype,clm_share_rule,clm_share_terms".equals(vo.getSrch_index_db())) {
			} else if ("clm_share_terms".equals(vo.getSrch_index_db())) {
				indexSysName = "clm.page.field.srch.share"; // 계약지식공유
			} else if ("clm_counsel_notice".equals(vo.getSrch_index_db())
					|| "clm_counsel_faq".equals(vo.getSrch_index_db())
					|| "clm_counsel_qna".equals(vo.getSrch_index_db())) {
				indexSysName = "clm.page.field.srch.counsel"; // 공지 및 Q&A
				writerText = "clm.page.field.srch.writer";
			}

			if ((String) request.getAttribute("returnMessage") != null
					&& ((String) request.getAttribute("returnMessage"))
							.length() > 1) {
				returnMessage = (String) request.getAttribute("returnMessage");
			}

			String forwardURL = "/WEB-INF/jsp/clm/search/SearchDetail_l.jsp";

			// 세션을 체크해서 계약또는법무검색페이지로 설정한다.
//			if ("CLM".equals(vo.getSys_cd())) {
//				// 계약 개별검색화면
//				forwardURL = "/WEB-INF/jsp/clm/search/SearchDetail_l.jsp";
//			} else {
//				// 법무 개별검색화면
//				forwardURL = "/WEB-INF/jsp/las/search/SearchDetail_l.jsp";
//			}

			ModelAndView mav = new ModelAndView();

			/*********************************************************
			 * 상단메뉴관련 (MainController 로직복사)
			 **********************************************************/
			/*
			 * String sysCd =
			 * (String)session.getAttribute("secfw.session.sys_cd"); String
			 * userId = (String)session.getAttribute("secfw.session.user_id");
			 * 
			 * HashMap paramMap = new HashMap(); paramMap.put("menuGbn" ,
			 * "TOP"); paramMap.put("targetMenuId" , ""); paramMap.put("sys_cd"
			 * , sysCd); paramMap.put("user_id" , userId);
			 * 
			 * HashMap hm = searchService.listMenu(paramMap); List menuList =
			 * (List)hm.get("menuList");
			 */
//			Map map = new HashMap();
//			int Level_One = 0;
//			if (menuList != null && menuList.size() > 0) {
//				String compMenuId = "";
//
//				for (int i = 0; i < menuList.size(); i++) {
//					ListOrderedMap lom = (ListOrderedMap) menuList.get(i);
//
//					if ("1".equals(StringUtil.bvl(lom.get("menu_level"), ""))) {
//						compMenuId = (String) lom.get("menu_id");
//						map.put(compMenuId, new Integer(0));
//						Level_One++;
//					} /*
//					 * else { if (map.get(lom.get("up_menu_id")) != null) { int
//					 * cnt = ((Integer) map.get(lom.get("up_menu_id")))
//					 * .intValue() + 1; map.put((String) lom.get("up_menu_id"),
//					 * new Integer(cnt)); } }
//					 */
//				}
//			}
//
//			// 1레벨의 싸이즈
//			mav.addObject("menuSize", Integer.toString(Level_One));
//			mav.addObject("menuList", menuList);
//			mav.addObject("itvocUrl",
//					propertyService.getProperty("secfw.url.itvoc"));
//			mav.addObject("clmLoginUrl",
//					propertyService.getProperty("secfw.url.clmLogin"));
//			mav.addObject("slmsUrl",
//					propertyService.getProperty("secfw.url.slms"));

			/*********************************************************
			 * 상단메뉴관련 끝
			 **********************************************************/

			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			mav.addObject("totalSearchCount", totalSearchCount);
			mav.addObject("indexSysName", indexSysName);
			mav.addObject("writerText", writerText);

			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 상세검색 콤보관련
	 * 
	 * @param request
	 * @param response
	 * @return void
	 * @throws Exception
	 */
	public void getSrchDetailCombo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {

//			HttpSession session = request.getSession(false);

			SearchForm form = new SearchForm();
			SearchVO vo = new SearchVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			// 얻어올 코드플래그 관련
			String comboflag = StringUtil.bvl(
					(String) request.getParameter("comboflag"), "");
			String upCd = StringUtil.bvl((String) request.getParameter("upCd"),
					"");
			// 계약유형 코드를 가져온다.
			String result = searchService.getMasterCombo(comboflag, upCd, vo);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();

		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null,
					new RequestContext(request).getLocale()));

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null,
					new RequestContext(request).getLocale()));

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}

}
