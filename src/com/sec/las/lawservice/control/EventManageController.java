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
import com.sds.secframework.util.service.ComUtilService;
import com.sec.common.clmscom.service.CLMSCommonService;
import com.sec.las.lawservice.dto.DeptListForm;
import com.sec.las.lawservice.dto.DeptListVO;
import com.sec.las.lawservice.dto.EventAcceptLawyerVO;
import com.sec.las.lawservice.dto.EventAcceptVO;
import com.sec.las.lawservice.dto.EventManageForm;
import com.sec.las.lawservice.dto.EventManageVO;
import com.sec.las.lawservice.dto.EventRefDeptVO;
import com.sec.las.lawservice.dto.LawfirmManageVO;
import com.sec.las.lawservice.dto.LwsCommonVO;
import com.sec.las.lawservice.service.EventManageService;
import com.sec.las.lawservice.service.LawyerManageService;
import com.sec.las.lawservice.service.LwsCommonService;

public class EventManageController extends CommonController {
	
	private static String FORWARD_FROM_EVENT_LIST = "0";	
	private static String FORWARD_FROM_SRVCOST_LIST = "1";
	
	private EventManageService eventManageService;
	public void setEventManageService(EventManageService eventManageService) {
		this.eventManageService = eventManageService;
	}
	
	private LwsCommonService lwsCommonService;
	public void setLwsCommonService(LwsCommonService lwsCommonService) {
		this.lwsCommonService = lwsCommonService;
	}
	
	private CLMSCommonService clmsComService;
	public void setClmsComService(CLMSCommonService clmsComService)
	{
		this.clmsComService = clmsComService;
	}
	
	private LawyerManageService lawyerManageService;

	public void setLawyerManageService(LawyerManageService lawyerManageService) {
		this.lawyerManageService = lawyerManageService;
	}
	
	// ComUtil 서비스
	private ComUtilService comUtilService;

	public void setComUtilService(ComUtilService comUtilService) {
		this.comUtilService = comUtilService;
	}
	/**
	 * 사건 관리 목록
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listEventManage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			String forwardURL = "";
			String returnMessage = "";
			
			EventManageForm form = null;
			EventManageVO vo = null;
			
			List resultList = null;
			
			HttpSession session = request.getSession(false);
			
			forwardURL = "/WEB-INF/jsp/las/lawservice/EventManage_l.jsp";

			form = new EventManageForm();
			vo = new EventManageVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//Cross-site Scripting 방지
			form.setSrch_event_no(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_event_no(),"")));
			form.setSrch_event_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_event_nm(),"")));
			form.setSrch_lawfirm_id(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_lawfirm_id(),"")));
			form.setSrch_lawfirm_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_lawfirm_nm(),"")));
			form.setSrch_kbn1(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_kbn1(),"")));
			form.setSrch_kbn2(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_kbn2(),"")));
			form.setSrch_lwr_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_lwr_nm(),"")));
			form.setSrch_reg_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_reg_nm(),"")));
			form.setSrch_start_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_start_dt(),"")));
			form.setSrch_end_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_end_dt(),"")));
			
			vo.setSrch_event_no(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_event_no(),"")));
			vo.setSrch_event_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_event_nm(),"")));
			vo.setSrch_lawfirm_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_lawfirm_id(),"")));
			vo.setSrch_lawfirm_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_lawfirm_nm(),"")));
			vo.setSrch_kbn1(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_kbn1(),"")));
			vo.setSrch_kbn2(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_kbn2(),"")));
			vo.setSrch_lwr_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_lwr_nm(),"")));
			vo.setSrch_reg_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_reg_nm(),"")));
			vo.setSrch_start_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_start_dt(),"")));
			vo.setSrch_end_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_end_dt(),"")));
			
			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			
			if(form.getCurPage() == null) {
				form.setCurPage("1");
				vo.setCurPage("1");
			}

			resultList = eventManageService.listEventManage(vo);
			
			//검색옵션 로펌리스트
			LawfirmManageVO lvo = new LawfirmManageVO();
			List lawfirmList = lawyerManageService.getListLawfirm(lvo);
			
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
			mav.addObject("lawfirmList", lawfirmList);
			mav.addObject("eventList", eventList);
			mav.addObject("lom", lom);
			mav.addObject("pageUtil", pageUtil);
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
	 * 사건 관리 등록화면 호출
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardEventManageInsert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			String forwardURL = "";
			String returnMessage = "";
			
			EventManageForm form = null;
			EventManageVO vo = null;
			
			List resultList = null;
			
			HttpSession session = request.getSession(false);
			
			forwardURL = "/WEB-INF/jsp/las/lawservice/EventManage_i.jsp";

			form = new EventManageForm();
			vo = new EventManageVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
		
			form.setReg_nm(vo.getSession_user_nm());
			form.setReg_dt(DateUtil.getCurrentDay());
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
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
	 * 사건 관리 등록
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertEventManage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			String forwardURL = "";
			String returnMessage = "";
			
			EventManageForm form = null;
			EventManageVO vo = null;
			
			HttpSession session = request.getSession(false);			

			form = new EventManageForm();
			vo = new EventManageVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//Cross-site Scripting 방지
			form.setEvent_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getEvent_nm(),"")));
			form.setEvent_gbn1(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getEvent_gbn1(),"")));
			form.setEvent_gbn2(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getEvent_gbn2(),"")));
			form.setLawsuit_trgt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getLawsuit_trgt(),"")));
			
			vo.setEvent_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getEvent_nm(),"")));
			vo.setEvent_gbn1(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getEvent_gbn1(),"")));
			vo.setEvent_gbn2(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getEvent_gbn2(),"")));
			vo.setLawsuit_trgt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLawsuit_trgt(),"")));
			
			if(vo.getForward_from().equals(FORWARD_FROM_SRVCOST_LIST)){
				forwardURL = "/las/lawservice/eventAcceptSrvCost.do?method=listEventSub";				
			} else {
				forwardURL = "/las/lawservice/eventManage.do?method=listEventManage";
			}			
			
			vo.setEvent_no(eventManageService.getMaxEventNo(vo));
			vo.setReg_id(vo.getSession_user_id());
			vo.setReg_nm(vo.getSession_user_nm());
			
			eventManageService.insertEventManage(vo);

			returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());

			ModelAndView mav = new ModelAndView();

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
	 * 사건 관리 상세
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailEventManage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			String forwardURL = "/WEB-INF/jsp/las/lawservice/EventManage_d.jsp";
			
			EventManageForm form = null;
			EventManageVO vo = null;
			
			List resultList = null;
			
			HttpSession session = request.getSession(false);

			form = new EventManageForm();
			vo = new EventManageVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);			
			
			// 상세정보취득
			resultList = eventManageService.detailEventManage(vo);
			ListOrderedMap lom = null;

			if(resultList!=null){				
				lom = (ListOrderedMap)resultList.get(0);
			} 
			
			// 나모 에디터 데이타 처리 
			lom.put("evnet_cont", StringUtil.convertCharsToHtml((String)lom.get("evnet_cont")));
			
			//  비용귀속 부서 취득
			EventRefDeptVO edvo = new EventRefDeptVO();
			edvo.setEvent_no(vo.getEvent_no());
			ArrayList dept_list = (ArrayList)eventManageService.getListRefDept(edvo);
			
			//  사건 담당 변호사 취득
			EventAcceptLawyerVO eavo = new EventAcceptLawyerVO();
			eavo.setEvent_no(vo.getEvent_no());
			ArrayList lwr_list = (ArrayList)eventManageService.getListLawywerAcceptEvent(eavo);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			
			// 하단리스트 정보
			PageUtil pageUtil = new PageUtil();
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			
			if(form.getCurPage() == null) {
				form.setCurPage("1");
				vo.setCurPage("1");
			}			
			
			if(vo.getForward_from().equals(FORWARD_FROM_SRVCOST_LIST)){
				//  INVOICE 목록 취득
				int invoice_list_cnt = 0;
				EventAcceptVO acvo = new EventAcceptVO();
				acvo.setEvent_no(vo.getEvent_no());
				List re_invoice_list = eventManageService.getListEventAcceptInvoice(acvo);
				
				if(re_invoice_list!=null)
					invoice_list_cnt = re_invoice_list.size();
				
				ArrayList invoice_list = (ArrayList)re_invoice_list;
				mav.addObject("invoice_list", invoice_list);
				mav.addObject("invoice_list_cnt", new Integer(invoice_list_cnt));		
			} else {
				//  등록 사건 목록 취득
				int event_accept_list_cnt = 0;
				EventAcceptVO acvo = new EventAcceptVO();
				acvo.setEvent_no(vo.getEvent_no());
				List event_accept_resultList = eventManageService.getListEventAccept(acvo);
				
				if(event_accept_resultList!=null)
					event_accept_list_cnt = event_accept_resultList.size();
				
				ArrayList event_accept_list = (ArrayList)event_accept_resultList;
				mav.addObject("event_accept_list", event_accept_list);
				mav.addObject("event_accept_list_cnt", new Integer(event_accept_list_cnt));
			}
			
			//권한 처리
			LwsCommonVO lcvo = new LwsCommonVO();
			lcvo.setAuth_str_id(vo.getEvent_no());
			lcvo.setSession_user_role_cds(vo.getSession_user_role_cds());
			lcvo.setSession_user_id(vo.getSession_user_id());
			boolean lws_auth_modify = lwsCommonService.authLws(MODIFY, lcvo, "EVENT");
			boolean lws_auth_delete = lwsCommonService.authLws(DELETE, lcvo, "EVENT");			
			form.setLws_auth_modify(lws_auth_modify);
			form.setLws_auth_delete(lws_auth_delete);
			
			mav.addObject("resultList", resultList);
			mav.addObject("command", form);
			mav.addObject("lom", lom);
			mav.addObject("dept_list", dept_list);
			mav.addObject("lwr_list", lwr_list);

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
	 * 사건 관리 수정화면 호출
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardEventManageModify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			String forwardURL = "";
			String returnMessage = "";
			
			EventManageForm form = null;
			EventManageVO vo = null;
			
			List resultList = null;
			
			HttpSession session = request.getSession(false);

			forwardURL = "/WEB-INF/jsp/las/lawservice/EventManage_i.jsp";

			form = new EventManageForm();
			vo = new EventManageVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			form.setReg_nm(vo.getSession_user_nm());
			form.setReg_dt(DateUtil.getCurrentDay());

			resultList = eventManageService.detailEventManage(vo);
			ListOrderedMap lom = null;

			if(resultList!=null){				
				lom = (ListOrderedMap)resultList.get(0);
			} 
			
			//  비용귀속 부처 취득
			EventRefDeptVO edvo = new EventRefDeptVO();
			edvo.setEvent_no(vo.getEvent_no());
			ArrayList dept_list = (ArrayList)eventManageService.getListRefDept(edvo);

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
			mav.addObject("command", form);
			mav.addObject("lom", lom);
			mav.addObject("dept_list", dept_list);

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
	 * 사건 관리 수정
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifyEventManage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			String forwardURL = "";
			String returnMessage = "";
			
			EventManageForm form = null;
			EventManageVO vo = null;
			
			List resultList = null;
			
			HttpSession session = request.getSession(false);

			form = new EventManageForm();
			vo = new EventManageVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//Cross-site Scripting 방지
			form.setSrch_event_no(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_event_no(),"")));
			form.setSrch_event_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_event_nm(),"")));
			form.setSrch_lawfirm_id(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_lawfirm_id(),"")));
			form.setSrch_lawfirm_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_lawfirm_nm(),"")));
			form.setSrch_kbn1(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_kbn1(),"")));
			form.setSrch_kbn2(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_kbn2(),"")));
			form.setSrch_lwr_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_lwr_nm(),"")));
			form.setSrch_reg_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_reg_nm(),"")));
			form.setSrch_start_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_start_dt(),"")));
			form.setSrch_end_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_end_dt(),"")));
			
			vo.setSrch_event_no(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_event_no(),"")));
			vo.setSrch_event_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_event_nm(),"")));
			vo.setSrch_lawfirm_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_lawfirm_id(),"")));
			vo.setSrch_lawfirm_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_lawfirm_nm(),"")));
			vo.setSrch_kbn1(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_kbn1(),"")));
			vo.setSrch_kbn2(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_kbn2(),"")));
			vo.setSrch_lwr_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_lwr_nm(),"")));
			vo.setSrch_reg_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_reg_nm(),"")));
			vo.setSrch_start_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_start_dt(),"")));
			vo.setSrch_end_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_end_dt(),"")));
		
			vo.setMod_id(vo.getSession_user_id());
			vo.setMod_nm(vo.getSession_user_nm());
					
			forwardURL = "/las/lawservice/eventManage.do?method=detailEventManage";
			
			//권한 처리
			LwsCommonVO lcvo = new LwsCommonVO();
			lcvo.setAuth_str_id(vo.getEvent_no());
			lcvo.setSession_user_role_cds(vo.getSession_user_role_cds());
			lcvo.setSession_user_id(vo.getSession_user_id());
			boolean lws_auth_modify = lwsCommonService.authLws(MODIFY, lcvo, "EVENT");
			if(!lws_auth_modify){
				ModelAndView mav = new ModelAndView() ; 
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				mav.addObject("secfw.alert.title", messageSource.getMessage("secfw.page.field.alert.noModifyAuth", null, new RequestContext(request).getLocale()));
				mav.addObject("secfw.alert.message", messageSource.getMessage("secfw.page.field.alert.noLinkMessage", null, new RequestContext(request).getLocale()));
				return mav;
			}	
			
			int result = eventManageService.modifyEventManage(vo);

			returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());

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
	 * 사건 관리 삭제
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView deleteEventManage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			String forwardURL = "";
			String returnMessage = "";
			
			EventManageForm form = null;
			EventManageVO vo = null;
			
			HttpSession session = request.getSession(false);			

			form = new EventManageForm();
			vo = new EventManageVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);			
			
			if(vo.getForward_from().equals(FORWARD_FROM_SRVCOST_LIST)){
				forwardURL = "/las/lawservice/eventAcceptSrvCost.do?method=listEventSub";				
			} else {
				forwardURL = "/las/lawservice/eventManage.do?method=listEventManage";
			}		
			
			vo.setDel_id(vo.getSession_user_id());
			vo.setDel_nm(vo.getSession_user_nm());
			
			//권한 처리
			LwsCommonVO lcvo = new LwsCommonVO();
			lcvo.setAuth_str_id(vo.getEvent_no());
			lcvo.setSession_user_role_cds(vo.getSession_user_role_cds());
			lcvo.setSession_user_id(vo.getSession_user_id());
			boolean lws_auth_del = lwsCommonService.authLws(DELETE, lcvo, "EVENT");
			if(!lws_auth_del){
				ModelAndView mav = new ModelAndView() ; 
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				mav.addObject("secfw.alert.title", messageSource.getMessage("secfw.page.field.alert.noDeleteAuth", null, new RequestContext(request).getLocale()));
				mav.addObject("secfw.alert.message", messageSource.getMessage("secfw.page.field.alert.noLinkMessage", null, new RequestContext(request).getLocale()));
				return mav;
			}	

			eventManageService.deleteEventManage(vo);

			returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());

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
	 * 부서 리스트를 반환한다.(트리구조)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listDeptTree(HttpServletRequest request, HttpServletResponse response) throws Exception{

		HttpSession session = request.getSession(false);
                                                                                   
		String forwardURL = "/WEB-INF/jsp/las/lawservice/EventManage_p.jsp";
		
		ArrayList dept_list = (ArrayList)eventManageService.getListDept();
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName(forwardURL);
		
		mav.addObject("resultList", dept_list);
	
		return mav;
		
	}
	
	/**
	 * 부서 리스트를 반환한다.(트리구조)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
/*	public ModelAndView listDeptTree_tmp(HttpServletRequest request, HttpServletResponse response) throws Exception{

		HttpSession session = request.getSession(false);
                                                                                   
		String forwardURL = "/WEB-INF/jsp/las/lawservice/DeptList_p.jsp";

		DeptListForm form  = new DeptListForm();
		DeptListVO vo = new DeptListVO();
		
		bind(request, form);
		bind(request, vo);
		
		if(form.getDept_nm() == null) {
			form.setDept_nm("");
		} else {
			form.setDept_nm(StringUtil.convertHtmlTochars(form.getDept_nm()));
		}
		
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);
		
		String returnMessage = "";		
                                                                      
		// 파라미터 세팅                                                                                                                                 
		if(vo.getUp_dept_cd() == null || vo.getUp_dept_cd().equals("")) {
			vo.setUp_dept_cd("TOP");
			form.setUp_dept_cd(vo.getUp_dept_cd());
		}
 
		ArrayList dept_list = null;
		
		dept_list = (ArrayList)eventManageService.listDeptTree(vo);
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName(forwardURL);
		
		mav.addObject("resultList", dept_list);
		mav.addObject("command", form);
		mav.addObject("returnMessage", returnMessage);
		
		return mav;
		
	}*/
	
	/**
	 * 부서 리스트를 반환한다.(해당부서의 하위부서만)
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void listDeptTreeAjax(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{

			// Form 및 VO Binding
			DeptListForm form  = new DeptListForm();
			DeptListVO vo = new DeptListVO();
			bind(request, vo);
			COMUtil.getUserAuditInfo(request, vo);			

			//  하위부서 목록조회
			JSONArray jsonArray = eventManageService.listDeptTreeAjax(vo);
			
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

}