/**
 * Project Name : 법무시스템
 * File Name : EventManageController.java
 * Description : 사건 관리 Controller
 * Author : 박병주
 * Date : 2011.09
 * Copyright : 2011 by SAMSUNG. All rights reserved.
 */
package com.sec.las.lawservice.control;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import anyframe.common.util.DateUtil;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.las.lawservice.dto.EventAcceptForm;
import com.sec.las.lawservice.dto.EventAcceptLawyerVO;
import com.sec.las.lawservice.dto.EventAcceptVO;
import com.sec.las.lawservice.dto.EventManageForm;
import com.sec.las.lawservice.dto.EventManageVO;
import com.sec.las.lawservice.dto.EventRefDeptVO;
import com.sec.las.lawservice.dto.LawfirmManageVO;
import com.sec.las.lawservice.dto.LawyerManageVO;
import com.sec.las.lawservice.dto.LwsCommonVO;
import com.sec.las.lawservice.service.EventAcceptSrvCostService;
import com.sec.las.lawservice.service.EventManageService;
import com.sec.las.lawservice.service.LawyerManageService;
import com.sec.las.lawservice.service.LwsCommonService;
import com.sec.common.getXIRateData.service.GetXIRateDataService;

public class EventAcceptSrvCostController extends CommonController {
	private EventAcceptSrvCostService eventAcceptSrvCostService;
	public void setEventAcceptSrvCostService(EventAcceptSrvCostService eventAcceptSrvCostService) {
		this.eventAcceptSrvCostService = eventAcceptSrvCostService;
	}
	
	private LwsCommonService lwsCommonService;
	public void setLwsCommonService(LwsCommonService lwsCommonService) {
		this.lwsCommonService = lwsCommonService;
	}
	
	private EventManageService eventManageService;
	public void setEventManageService(EventManageService eventManageService) {
		this.eventManageService = eventManageService;
	}
	
	private LawyerManageService lawyerManageService;

	public void setLawyerManageService(LawyerManageService lawyerManageService) {
		this.lawyerManageService = lawyerManageService;
	}
	
	private GetXIRateDataService getXIRateDataService;
	public void setGetXIRateDataService(GetXIRateDataService getXIRateDataService) {
		this.getXIRateDataService = getXIRateDataService;
	}

	/**
	 * 사건 목록 (용역화면용）　
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listEventSub(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);

			String forwardURL = "/WEB-INF/jsp/las/lawservice/EventAcceptSrvCost_l.jsp";

			EventManageForm form = new EventManageForm();
			EventManageVO vo = new EventManageVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);			
			
			//Cross-site Scripting 방지
			form.setSrch_event_no(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_event_no(),"")));
			form.setSrch_event_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_event_nm(),"")));
			form.setSrch_kbn1(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_kbn1(),"")));
			form.setSrch_kbn2(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_kbn2(),"")));
			form.setSrch_reg_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_reg_nm(),"")));
			form.setSrch_start_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_start_dt(),"")));
			form.setSrch_end_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_end_dt(),"")));
			form.setSrch_order_type(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_order_type(),"")));
			
			vo.setSrch_event_no(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_event_no(),"")));
			vo.setSrch_event_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_event_nm(),"")));
			vo.setSrch_kbn1(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_kbn1(),"")));
			vo.setSrch_kbn2(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_kbn2(),"")));
			vo.setSrch_reg_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_reg_nm(),"")));
			vo.setSrch_start_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_start_dt(),"")));
			vo.setSrch_end_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_end_dt(),"")));
			vo.setSrch_order_type(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_order_type(),"")));			

			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			
			if(form.getCurPage() == null) {
				form.setCurPage("1");
				vo.setCurPage("1");
			}
			
			if(form.getSrch_order_type().equals("")) {
				vo.setSrch_order_type("S_EVENT_NM");
			}

			String returnMessage = "";

			List resultList = eventAcceptSrvCostService.listEventSub(vo);
			
			//검색옵션 사건리스트
			EventManageVO evo = new EventManageVO();
			List eventList = lawyerManageService.listLawyerManageGetEventList(evo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap tmp = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(((Integer)tmp.get("total_cnt")).intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();

				// 메시지처리 - 정상적으로 조회되었습니다.
				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}else {
					//메뉴 최초 진입 시 메세지 여부
					if("Y".equals(vo.getDoSearch()))
						returnMessage = messageSource.getMessage("secfw.msg.succ.search", null, new RequestContext(request).getLocale());
				}
			}else {
				// 메시지처리 - 조회된 결과가 없습니다.
				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				} else {
					//메뉴 최초 진입 시 메세지 여부
					if("Y".equals(vo.getDoSearch()))
						returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale());
				}
			}
			
			ArrayList lom = (ArrayList)resultList;
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("command", form);
			mav.addObject("lom", lom);
			mav.addObject("eventList", eventList);
			mav.addObject("returnMessage", returnMessage);

			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 용역비 등록화면 호출
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardEventAcceptSrvCostInsert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);

			String forwardURL = "/WEB-INF/jsp/las/lawservice/EventAcceptSrvCost_i.jsp";
			
			EventManageForm event_form = new EventManageForm();
			EventManageVO event_vo = new EventManageVO();
			
			bind(request, event_form);
			bind(request, event_vo);
			
			EventAcceptForm form = new EventAcceptForm();
			EventAcceptVO vo = new EventAcceptVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//form.setAcpt_no(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getAcpt_no(),"")));			
			//vo.setAcpt_no(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getAcpt_no(),"")));
			form.setAcpt_no("");
			vo.setAcpt_no("");
			form.setReg_id(vo.getSession_user_id());
			form.setReg_nm(vo.getSession_user_nm());
			form.setAcptday(DateUtil.getCurrentDay());
			
			// 사건정보
			ListOrderedMap event_lom = new ListOrderedMap();
			event_lom.put("event_no",event_vo.getEvent_no());
			event_lom.put("event_nm",event_vo.getEvent_nm());
			event_lom.put("event_gbn1",event_vo.getEvent_gbn1());
			event_lom.put("event_gbn2",event_vo.getEvent_gbn2());
			event_lom.put("lawsuit_trgt_nm",event_vo.getLawsuit_trgt_nm());			
			
			// 로펌 comboListBox
			LawfirmManageVO lvo = new LawfirmManageVO();
			ArrayList lawfirm_list = (ArrayList)lawyerManageService.getListLawfirm(lvo);
			
			//  변호사 comboListBox
			LawyerManageVO ecavo = new LawyerManageVO();
			ArrayList lwr_list_all = (ArrayList)eventAcceptSrvCostService.getLawyerList(ecavo);
			
			//  사건 담당 변호사 취득
			EventAcceptLawyerVO eavo = new EventAcceptLawyerVO();
			eavo.setEvent_no(vo.getEvent_no());
			ArrayList lwr_list_event = (ArrayList)eventManageService.getListLawywerAcceptEvent(eavo);
						
			// 비용귀속 부서 
			int dept_list_cnt = 0;
			EventRefDeptVO edvo = new EventRefDeptVO();
			edvo.setEvent_no(vo.getEvent_no());
			ArrayList dept_list = (ArrayList)eventManageService.getListRefDept(edvo);
			
			//오늘보다 하루 이전 날짜
			//String str_cday  = DateUtil.getYesterday();
			// 오늘 날짜로 수정
			String str_cday  = DateUtil.getCurrentDay();
			
			if(dept_list!=null)
				dept_list_cnt = dept_list.size();

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("lawfirm_list", lawfirm_list);
			mav.addObject("lwr_list_all", lwr_list_all);
			mav.addObject("lwr_list_event", lwr_list_event);
			mav.addObject("dept_list", dept_list);
			mav.addObject("dept_list_cnt", dept_list_cnt);
			mav.addObject("str_cday", str_cday);
			mav.addObject("event_lom", event_lom);

			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 용역비 등록
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertEventAcceptSrvCost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			//String forwardURL = "/las/lawservice/eventManage.do?method=detailEventManage";
			String forwardURL = "";
			
			EventAcceptForm form = null;
			EventAcceptVO vo = null;
			
			HttpSession session = request.getSession(false);			

			form = new EventAcceptForm();
			vo = new EventAcceptVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setReg_id(vo.getSession_user_id());
			vo.setReg_nm(vo.getSession_user_nm());
			
			vo.setAcpt_no(eventAcceptSrvCostService.getMaxAcptNo(vo));	
			
			eventAcceptSrvCostService.insertEventAcceptSrvCost(vo);

			String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());

			ModelAndView mav = new ModelAndView();
			
			//검색에서 넘어왔을 경우 검색으로 다시 보낸다.
			if("2".equals(StringUtil.bvl(form.getForward_from(),""))){
				forwardURL = "/las/lawservice/lawServiceSearch.do?method=listLawServiceSearch";
			}else{
				forwardURL = "/las/lawservice/eventManage.do?method=detailEventManage";
			}
			
			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;

		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 용역비 상세　
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailEventAcceptSrvCost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);
			
			String forwardURL = "/WEB-INF/jsp/las/lawservice/EventAcceptSrvCost_d.jsp";

			EventAcceptForm form = new EventAcceptForm();
			EventAcceptVO vo = new EventAcceptVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			// 상세정보			
			List resultList = eventAcceptSrvCostService.detailEventAccept(vo);
			ListOrderedMap lom = null;

			if(resultList!=null){				
				lom = (ListOrderedMap)resultList.get(0);
			} 
			
			//  사건 담당 변호사
			ArrayList lwr_list_event = (ArrayList)eventAcceptSrvCostService.getListEventAcceptLawyer(vo);
			
			//  용역비 목록
			ArrayList srv_cost_list = (ArrayList)eventAcceptSrvCostService.detailEventAcceptSrvCost(vo);
			
			//  INVOICE 목록 
			int invoice_list_cnt = 0;
			List re_invoice_list = eventManageService.getListEventAcceptInvoice(vo);
			
			if(re_invoice_list!=null)
				invoice_list_cnt = re_invoice_list.size();
			
			ArrayList invoice_list = (ArrayList)re_invoice_list;
			
			//권한 처리
			LwsCommonVO lcvo = new LwsCommonVO();
			lcvo.setAuth_str_id(vo.getAcpt_no());
			lcvo.setSession_user_role_cds(vo.getSession_user_role_cds());
			lcvo.setSession_user_id(vo.getSession_user_id());
			boolean lws_auth_modify = lwsCommonService.authLws(MODIFY, lcvo, "ACPT");
			boolean lws_auth_delete = lwsCommonService.authLws(DELETE, lcvo, "ACPT");	
			boolean lws_auth_admin_access = lwsCommonService.isAccessAuthAdmin(lcvo);	
			form.setLws_auth_modify(lws_auth_modify);
			form.setLws_auth_delete(lws_auth_delete);
			form.setLws_auth_admin_access(lws_auth_admin_access);
			
			//오늘 날짜
			String str_cday  = DateUtil.getCurrentDay();

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("lom", lom);
			mav.addObject("lwr_list_event", lwr_list_event);
			mav.addObject("srv_cost_list", srv_cost_list);
			mav.addObject("invoice_list", invoice_list);
			mav.addObject("str_cday", str_cday);
			mav.addObject("invoice_list_cnt", new Integer(invoice_list_cnt));	
			mav.addObject("command", form);

			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 용역비 수정화면 호출　
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardEventAcceptSrvCostModify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);

			String forwardURL = "/WEB-INF/jsp/las/lawservice/EventAcceptSrvCost_i.jsp";

			EventAcceptForm form = new EventAcceptForm();
			EventAcceptVO vo = new EventAcceptVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			form.setAcpt_no(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getAcpt_no(),"")));			
			vo.setAcpt_no(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getAcpt_no(),"")));
			form.setReg_id(vo.getSession_user_id());
			form.setReg_nm(vo.getSession_user_nm());
			form.setAcptday(DateUtil.getCurrentDay());
			
			// 사건정보
			EventManageVO evo = new EventManageVO();
			evo.setEvent_no(vo.getEvent_no());
			List eventList = eventManageService.detailEventManage(evo);			
			ListOrderedMap event_lom = new ListOrderedMap();			
			if(eventList!=null){				
				event_lom = (ListOrderedMap)eventList.get(0);
			} 
			
			// 상세정보			
			List resultList = eventAcceptSrvCostService.detailEventAccept(vo);
			ListOrderedMap lom = null;

			if(resultList!=null){				
				lom = (ListOrderedMap)resultList.get(0);
			} 			
			
			// 로펌 comboListBox
			LawfirmManageVO lvo = new LawfirmManageVO();
			ArrayList lawfirm_list = (ArrayList)lawyerManageService.getListLawfirm(lvo);
			
			//  변호사 comboListBox
			LawyerManageVO ecavo = new LawyerManageVO();
			ArrayList lwr_list_all = (ArrayList)eventAcceptSrvCostService.getLawyerList(ecavo);
			
			//  사건 담당 변호사 취득
			EventAcceptLawyerVO eavo = new EventAcceptLawyerVO();
			eavo.setEvent_no(vo.getEvent_no());
			ArrayList lwr_list_event = (ArrayList)eventManageService.getListLawywerAcceptEvent(eavo);
						
			// 비용귀속 부서 
			int dept_list_cnt = 0;
			EventRefDeptVO edvo = new EventRefDeptVO();
			edvo.setEvent_no(vo.getEvent_no());
			// ArrayList dept_list = (ArrayList)eventManageService.getListRefDept(edvo);
			ArrayList dept_list = (ArrayList)eventAcceptSrvCostService.detailEventAcceptSrvCost(vo);
			
			//오늘보다 하루 이전 날짜
			String str_cday  = DateUtil.getYesterday();
			
			if(dept_list!=null)
				dept_list_cnt = dept_list.size();


			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("event_lom", event_lom);
			mav.addObject("lom", lom);
			mav.addObject("str_cday", str_cday);
			mav.addObject("lawfirm_list", lawfirm_list);
			mav.addObject("lwr_list_all", lwr_list_all);
			mav.addObject("lwr_list_event", lwr_list_event);
			mav.addObject("dept_list", dept_list);
			mav.addObject("dept_list_cnt", dept_list_cnt);
			

			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 용역비 수정　
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifyEventAcceptSrvCost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);

			String forwardURL = "/las/lawservice/eventAcceptSrvCost.do?method=detailEventAcceptSrvCost";

			EventAcceptForm form = new EventAcceptForm();
			EventAcceptVO vo = new EventAcceptVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setMod_id(vo.getSession_user_id());
			vo.setMod_nm(vo.getSession_user_nm());
			vo.setAcpt_no(form.getAcpt_no());
			
			//권한 처리
			LwsCommonVO lcvo = new LwsCommonVO();
			lcvo.setAuth_str_id(vo.getAcpt_no());
			lcvo.setSession_user_role_cds(vo.getSession_user_role_cds());
			lcvo.setSession_user_id(vo.getSession_user_id());
			boolean lws_auth_modify = lwsCommonService.authLws(MODIFY, lcvo, "ACPT");			
			if(!lws_auth_modify){
				ModelAndView mav = new ModelAndView() ; 
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				mav.addObject("secfw.alert.title", messageSource.getMessage("secfw.page.field.alert.noModifyAuth", null, new RequestContext(request).getLocale()));
				mav.addObject("secfw.alert.message", messageSource.getMessage("secfw.page.field.alert.noLinkMessage", null, new RequestContext(request).getLocale()));
				return mav;
			}	

			int result = eventAcceptSrvCostService.modifyEventAcceptSrvCost(vo);

			String returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("returnMessage", returnMessage);
			mav.addObject("command", form);
			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 용역비 삭제　
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView deleteEventAcceptSrvCost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);

			String forwardURL = "/las/lawservice/eventManage.do?method=detailEventManage";

			EventAcceptForm form = new EventAcceptForm();
			EventAcceptVO vo = new EventAcceptVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//권한 처리
			LwsCommonVO lcvo = new LwsCommonVO();
			lcvo.setAuth_str_id(vo.getAcpt_no());
			lcvo.setSession_user_role_cds(vo.getSession_user_role_cds());
			lcvo.setSession_user_id(vo.getSession_user_id());
			boolean lws_auth_del = lwsCommonService.authLws(DELETE, lcvo, "ACPT");			
			if(!lws_auth_del){
				ModelAndView mav = new ModelAndView() ; 
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				mav.addObject("secfw.alert.title", messageSource.getMessage("secfw.page.field.alert.noDeleteAuth", null, new RequestContext(request).getLocale()));
				mav.addObject("secfw.alert.message", messageSource.getMessage("secfw.page.field.alert.noLinkMessage", null, new RequestContext(request).getLocale()));
				return mav;
			}	

			int result = eventAcceptSrvCostService.deleteEventAcceptSrvCost(vo);

			String returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("returnMessage", returnMessage);
			mav.addObject("command", form);
			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * INVOICE NO 을 반환한다.
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void checkInvoiceNoAjax(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{

			// Form 및 VO Binding
			EventAcceptForm form  = new EventAcceptForm();
			EventAcceptVO vo = new EventAcceptVO();
			bind(request, vo);
			COMUtil.getUserAuditInfo(request, vo);			

			//  INVOICE 조회
			JSONArray jsonArray = eventAcceptSrvCostService.checkInvoiceNoAjax(vo);
			
			response.setContentType("application/json; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.print(jsonArray);
	        out.flush();
	        out.close();
		} catch (Exception e) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		jo.put("returnCnt", 0);

    		response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
			out.flush();
			out.close();
		}
	}
	
	/**
	 * INVOICE NO 을 검색하고 검색결과가 존재하면 해당 상세화면으로 이동한다.
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void srchInvoiceNo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{

			// Form 및 VO Binding
			EventAcceptForm form  = new EventAcceptForm();
			EventAcceptVO vo = new EventAcceptVO();
			bind(request, vo);
			COMUtil.getUserAuditInfo(request, vo);			

			//  INVOICE 조회
			JSONArray jsonArray = eventAcceptSrvCostService.srchInvoiceNo(vo);
			
			response.setContentType("application/json; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.print(jsonArray);
	        out.flush();
	        out.close();
		} catch (Exception e) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		jo.put("returnCnt", 0);

    		response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
			out.flush();
			out.close();
		}
	}
	
	/**
	 * 환율 반영 정보를  반환한다.
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void checkRateAjax(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{

			// Form 및 VO Binding
			EventAcceptForm form  = new EventAcceptForm();
			EventAcceptVO vo = new EventAcceptVO();
			bind(request, vo);
			COMUtil.getUserAuditInfo(request, vo);			

			JSONArray jsonArray = eventAcceptSrvCostService.checkRateAjax(vo);
			
			response.setContentType("application/json; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.print(jsonArray);
	        out.flush();
	        out.close();
		} catch (Exception e) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		jo.put("returnCnt", 0);

    		response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
			out.flush();
			out.close();
		}
	}
	
	/**
	 *  송금일자를 갱신한다.　
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView updateRemitday(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);

			String forwardURL = "/las/lawservice/eventAcceptSrvCost.do?method=detailEventAcceptSrvCost";

			EventAcceptForm form = new EventAcceptForm();
			EventAcceptVO vo = new EventAcceptVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setMod_id(vo.getSession_user_id());
			vo.setMod_nm(vo.getSession_user_nm());

			int result = eventAcceptSrvCostService.updateRemitday(vo);

			String returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("returnMessage", returnMessage);
			mav.addObject("command", form);
			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 *  REVIEW를 갱신한다.　
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView updateReviewYn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);

			String forwardURL = "/las/lawservice/eventAcceptSrvCost.do?method=detailEventAcceptSrvCost";

			EventAcceptForm form = new EventAcceptForm();
			EventAcceptVO vo = new EventAcceptVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setMod_id(vo.getSession_user_id());
			vo.setMod_nm(vo.getSession_user_nm());

			int result = eventAcceptSrvCostService.updateReviewYn(vo);

			String returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("returnMessage", returnMessage);
			mav.addObject("command", form);
			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 *  송금정보를 초기화 한다.　
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView updateRemitInit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);

			String forwardURL = "/las/lawservice/eventAcceptSrvCost.do?method=detailEventAcceptSrvCost";

			EventAcceptForm form = new EventAcceptForm();
			EventAcceptVO vo = new EventAcceptVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setMod_id(vo.getSession_user_id());
			vo.setMod_nm(vo.getSession_user_nm());

			int result = eventAcceptSrvCostService.updateRemitInit(vo);

			String returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("returnMessage", returnMessage);
			mav.addObject("command", form);
			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 *  미지급일자를 갱신 한다.　
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView updateUnpayday(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);

			String forwardURL = "/las/lawservice/eventAcceptSrvCost.do?method=detailEventAcceptSrvCost";

			EventAcceptForm form = new EventAcceptForm();
			EventAcceptVO vo = new EventAcceptVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setMod_id(vo.getSession_user_id());
			vo.setMod_nm(vo.getSession_user_nm());

			int result = eventAcceptSrvCostService.updateUnpayday(vo);

			String returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("returnMessage", returnMessage);
			mav.addObject("command", form);
			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 *  환율정보를 강제 갱신 한다.　
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView updateCurrencyInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);

			String forwardURL = "/las/lawservice/eventAcceptSrvCost.do?method=detailEventAcceptSrvCost";

			EventAcceptForm form = new EventAcceptForm();
			EventAcceptVO vo = new EventAcceptVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			getXIRateDataService.insertRateData_test();

			String returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("returnMessage", returnMessage);
			mav.addObject("command", form);
			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
}