/**
 * Project Name : 법무시스템
 * File Name : LawfirmRemitCertController.java
 * Description : 송금증 관리 Controller
 * Author : 박병주
 * Date : 2011.10
 * Copyright : 2011 by SAMSUNG. All rights reserved.
 */
package com.sec.las.lawservice.control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.sec.las.lawservice.dto.LawfirmManageForm;
import com.sec.las.lawservice.dto.LawfirmManageVO;
import com.sec.las.lawservice.dto.LawfirmRemitCertForm;
import com.sec.las.lawservice.dto.LawfirmRemitCertVO;
import com.sec.las.lawservice.dto.LwsCommonVO;
import com.sec.las.lawservice.service.LawfirmManageService;
import com.sec.las.lawservice.service.LawfirmRemitCertService;
import com.sec.las.lawservice.service.LwsCommonService;

public class LawfirmRemitCertController extends CommonController {

	private LawfirmRemitCertService lawfirmRemitCertService;
	public void setLawfirmRemitCertService(LawfirmRemitCertService lawfirmRemitCertService) {
		this.lawfirmRemitCertService = lawfirmRemitCertService;
	}
	
	private LwsCommonService lwsCommonService;
	public void setLwsCommonService(LwsCommonService lwsCommonService) {
		this.lwsCommonService = lwsCommonService;
	}
	
	private LawfirmManageService LawfirmManageService;
	public void setLawfirmManageService(LawfirmManageService LawfirmManageService) {
		this.LawfirmManageService = LawfirmManageService;
	}
	
	private CLMSCommonService clmsComService;
	public void setClmsComService(CLMSCommonService clmsComService)
	{
		this.clmsComService = clmsComService;
	}
	
	// ComUtil 서비스
	private ComUtilService comUtilService;

	public void setComUtilService(ComUtilService comUtilService) {
		this.comUtilService = comUtilService;
	}
	
	/**
	 * 송금증 관리 목록
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listLawfirmRemitCert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);

			String forwardURL = "/WEB-INF/jsp/las/lawservice/LawfirmRemitCert_l.jsp";

			LawfirmManageForm form = null;
			LawfirmManageVO vo =	null;		

			form = new LawfirmManageForm();
			vo = new LawfirmManageVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//XSS
			form.setSrch_lawfirm_nm(StringUtil.bvl(form.getSrch_lawfirm_nm(),""));
			form.setSrch_mainftre_estmt(StringUtil.bvl(form.getSrch_mainftre_estmt(),""));
			form.setSrch_nation(StringUtil.bvl(form.getSrch_nation(),""));
			form.setSrch_start_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_start_dt(),"")));
			form.setSrch_end_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_end_dt(),"")));

			vo.setSrch_lawfirm_nm(StringUtil.bvl(vo.getSrch_lawfirm_nm(),""));
			vo.setSrch_mainftre_estmt(StringUtil.bvl(vo.getSrch_mainftre_estmt(),""));
			vo.setSrch_nation(StringUtil.bvl(vo.getSrch_nation(),""));
			vo.setSrch_start_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_start_dt(),"")));
			vo.setSrch_end_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_end_dt(),"")));
			
			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());	

			String returnMessage = "";

			List resultList = LawfirmManageService.listLawfirmManage(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(((Integer)lom.get("total_cnt")).intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();

				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}
			}else {
				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}
			}

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
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
	 * 송금증 관리 등록화면 호출
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardLawfirmRemitCertInsert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);

			String forwardURL = "/WEB-INF/jsp/las/lawservice/LawfirmRemitCert_i.jsp";;

			LawfirmRemitCertForm form = null;
			form = new LawfirmRemitCertForm();
			LawfirmRemitCertVO vo = null;
			vo = new LawfirmRemitCertVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			form.setReg_dt(DateUtil.getCurrentDay());
			form.setReg_nm(vo.getSession_user_nm());

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
	 * 송금증 관리 등록
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertLawfirmRemitCert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {			
			HttpSession session = request.getSession(false);

			LawfirmRemitCertForm form = new LawfirmRemitCertForm();
			LawfirmRemitCertVO vo = new LawfirmRemitCertVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//Cross-site Scripting 방지
			form.setRemitcert_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getRemitcert_nm(),"")));
			form.setRemitday(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getRemitday(),"")));
			form.setCont(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getCont(),"")));
			
			vo.setRemitcert_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getRemitcert_nm(),"")));
			vo.setRemitday(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getRemitday(),"")));
			vo.setCont(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCont(),"")));
			
			// 송금증 ID
			Calendar cld = Calendar.getInstance();  
			String remitcert_no = "R" + DateUtil.getCurrentDay().replace("-","")
			+ DateUtil.getCurrentHour().replace(":","")
			+ cld.get(Calendar.SECOND);
			
			vo.setReg_nm(vo.getSession_user_nm());
			vo.setReg_id(vo.getSession_user_id());
			
			vo.setRemitcert_no(remitcert_no);
			
			String forwardURL = "/las/lawservice/lawfirmRemitCert.do?method=listRemitCert";

			int result = lawfirmRemitCertService.insertLawfirmRemitCert(vo);

			String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());

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
	 * 송금증 관리 로펌별 송금증 리스트
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listRemitCert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);
			
			String forwardURL =  "/WEB-INF/jsp/las/lawservice/LawfirmRemitCertSub_l.jsp";
			
			LawfirmManageForm form = null;
			LawfirmManageVO vo = null;
			
			form = new LawfirmManageForm();
			vo = new LawfirmManageVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			// 상세정보취득
			List resultList = LawfirmManageService.detailLawfirmManage(vo);
			ListOrderedMap lom = null;
			if(resultList!=null){				
				lom = (ListOrderedMap)resultList.get(0);
			} 
			
			// 하단리스트 정보
			ArrayList remit_cert_list = (ArrayList)lawfirmRemitCertService.listLawfirmRemitCert(vo);
			int remit_cert_list_cnt = 0;
			if(remit_cert_list!=null)
				remit_cert_list_cnt = remit_cert_list.size();			

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("remit_cert_list", remit_cert_list);
			mav.addObject("remit_cert_list_cnt", remit_cert_list_cnt);
			mav.addObject("command", form);
			mav.addObject("lom", lom);

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
	 * 송금증 수정화면 호출
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardLawfirmRemitCertModify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			String forwardURL = "/WEB-INF/jsp/las/lawservice/LawfirmRemitCert_i.jsp";;

			LawfirmRemitCertForm form = null;
			form = new LawfirmRemitCertForm();
			LawfirmRemitCertVO vo = null;
			vo = new LawfirmRemitCertVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			List resultList = lawfirmRemitCertService.detailLawfirmRemitCert(vo);
			ListOrderedMap lom = null;

			if(resultList!=null){				
				lom = (ListOrderedMap)resultList.get(0);
			} 

			form.setReg_dt(DateUtil.getCurrentDay());
			form.setReg_nm(vo.getSession_user_nm());

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("lom", lom);

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
	 * 송금증 수정
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifyLawfirmRemitCert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);

			String forwardURL = "/las/lawservice/lawfirmRemitCert.do?method=detailLawfirmRemitCert";	
			
			LawfirmRemitCertForm form = null;
			form = new LawfirmRemitCertForm();
			LawfirmRemitCertVO vo = null;
			vo = new LawfirmRemitCertVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//Cross-site Scripting 방지
			form.setRemitcert_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getRemitcert_nm(),"")));
			form.setRemitday(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getRemitday(),"")));
			form.setCont(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getCont(),"")));
			
			vo.setRemitcert_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getRemitcert_nm(),"")));
			vo.setRemitday(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getRemitday(),"")));
			vo.setCont(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCont(),"")));
			
			//권한 처리
			LwsCommonVO lcvo = new LwsCommonVO();
			lcvo.setAuth_str_id(vo.getRemitcert_no());
			lcvo.setSession_user_role_cds(vo.getSession_user_role_cds());
			lcvo.setSession_user_id(vo.getSession_user_id());
			boolean lws_auth_modify = lwsCommonService.authLws(MODIFY, lcvo, "REMITCERT");
			if(!lws_auth_modify){
				ModelAndView mav = new ModelAndView() ; 
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				mav.addObject("secfw.alert.title", messageSource.getMessage("secfw.page.field.alert.noModifyAuth", null, new RequestContext(request).getLocale()));
				mav.addObject("secfw.alert.message", messageSource.getMessage("secfw.page.field.alert.noLinkMessage", null, new RequestContext(request).getLocale()));
				return mav;
			}	

			int result = lawfirmRemitCertService.modifyLawfirmRemitCert(vo);

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
	 * 송금증 상세
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailLawfirmRemitCert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			String forwardURL = "/WEB-INF/jsp/las/lawservice/LawfirmRemitCert_d.jsp";
			LawfirmRemitCertForm form = null;
			LawfirmRemitCertVO vo = null;			
			List resultList = null;			

			form = new LawfirmRemitCertForm();
			vo = new LawfirmRemitCertVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			if(request.getAttribute("return_id") != null)
				vo.setRemitcert_no((String)request.getAttribute("return_id"));
			
			// 상세정보취득
			resultList = lawfirmRemitCertService.detailLawfirmRemitCert(vo);
			ListOrderedMap lom = null;

			if(resultList!=null){				
				lom = (ListOrderedMap)resultList.get(0);
			}
			
			// 하단리스트 정보
			LawfirmManageVO lfmvo = new LawfirmManageVO();
			lfmvo.setLawfirm_id(vo.getLawfirm_id());
			ArrayList remit_cert_list = (ArrayList)lawfirmRemitCertService.listLawfirmRemitCert(lfmvo);
			int remit_cert_list_cnt = 0;
			if(remit_cert_list!=null)
				remit_cert_list_cnt = remit_cert_list.size();
			
			//권한 처리
			LwsCommonVO lcvo = new LwsCommonVO();
			lcvo.setAuth_str_id(vo.getRemitcert_no());
			lcvo.setSession_user_role_cds(vo.getSession_user_role_cds());
			lcvo.setSession_user_id(vo.getSession_user_id());
			boolean lws_auth_modify = lwsCommonService.authLws(MODIFY, lcvo, "REMITCERT");
			boolean lws_auth_delete = lwsCommonService.authLws(DELETE, lcvo, "REMITCERT");			
			form.setLws_auth_modify(lws_auth_modify);
			form.setLws_auth_delete(lws_auth_delete);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("remit_cert_list_cnt", remit_cert_list_cnt);
			mav.addObject("remit_cert_list", remit_cert_list);
			mav.addObject("command", form);
			mav.addObject("lom", lom);

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
	 * 송금증 삭제
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView deleteLawfirmRemitCert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);

			String forwardURL = "/las/lawservice/lawfirmRemitCert.do?method=listRemitCert";	

			LawfirmRemitCertForm form = new LawfirmRemitCertForm();
			LawfirmRemitCertVO vo = new LawfirmRemitCertVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//권한 처리
			LwsCommonVO lcvo = new LwsCommonVO();
			lcvo.setAuth_str_id(vo.getRemitcert_no());
			lcvo.setSession_user_role_cds(vo.getSession_user_role_cds());
			lcvo.setSession_user_id(vo.getSession_user_id());
			boolean lws_auth_del = lwsCommonService.authLws(DELETE, lcvo, "REMITCERT");
			if(!lws_auth_del){
				ModelAndView mav = new ModelAndView() ; 
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				mav.addObject("secfw.alert.title", messageSource.getMessage("secfw.page.field.alert.noDeleteAuth", null, new RequestContext(request).getLocale()));
				mav.addObject("secfw.alert.message", messageSource.getMessage("secfw.page.field.alert.noLinkMessage", null, new RequestContext(request).getLocale()));
				return mav;
			}	

			int result = lawfirmRemitCertService.deleteLawfirmRemitCert(vo);

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

}