/**
 * Project Name : 법무시스템
 * File Name : LawyerManageController.java
 * Description : 변호사 관리 Controller
 * Author : 박병주
 * Date : 2011.09
 * Copyright : 2011 by SAMSUNG. All rights reserved.
 */

package com.sec.las.lawservice.control;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.util.service.ComUtilService;
import com.sec.common.clmscom.dto.CLMSCommonVO;
import com.sec.common.clmscom.service.CLMSCommonService;
import com.sec.las.lawservice.dto.EventManageVO;
import com.sec.las.lawservice.dto.LawfirmManageVO;
import com.sec.las.lawservice.dto.LawyerEstimateVO;
import com.sec.las.lawservice.dto.LawyerManageForm;
import com.sec.las.lawservice.dto.LawyerManageVO;
import com.sec.las.lawservice.dto.LwsCommonVO;
import com.sec.las.lawservice.service.LawyerManageService;
import com.sec.las.lawservice.service.LwsCommonService;

public class LawyerManageController extends CommonController {	
	
	private LwsCommonService lwsCommonService;
	public void setLwsCommonService(LwsCommonService lwsCommonService) {
		this.lwsCommonService = lwsCommonService;
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

	private LawyerManageService lawyerManageService;
	
	public void setLawyerManageService(LawyerManageService lawyerManageService) {
		this.lawyerManageService = lawyerManageService;
	}
	
	/**
	 * 변호사관리 목록조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listLawyerManage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			String forwardURL = null;
			LawyerManageForm form = null;
			LawyerManageVO vo =	null;		

			forwardURL = "/WEB-INF/jsp/las/lawservice/LawyerManage_l.jsp";		

			form = new LawyerManageForm();
			vo = new LawyerManageVO();
		
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
			form.setSrch_expert_area(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_expert_area(),"")));
			form.setSrch_lwr_estimate_lvl(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_lwr_estimate_lvl(),"")));
			form.setSrch_lwr_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_lwr_nm(),"")));
			form.setCurPage(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getCurPage(),"")));
			
			vo.setSrch_event_no(StringUtil.bvl(vo.getSrch_event_no(),""));
			vo.setSrch_event_nm(StringUtil.bvl(vo.getSrch_event_nm(),""));
			vo.setSrch_lawfirm_id(StringUtil.bvl(vo.getSrch_lawfirm_id(),""));
			vo.setSrch_lawfirm_nm(StringUtil.bvl(vo.getSrch_lawfirm_nm(),""));
			vo.setSrch_kbn1(StringUtil.bvl(vo.getSrch_kbn1(),""));
			vo.setSrch_kbn2(StringUtil.bvl(vo.getSrch_kbn2(),""));
			vo.setSrch_expert_area(StringUtil.bvl(vo.getSrch_expert_area(),""));
			vo.setSrch_lwr_estimate_lvl(StringUtil.bvl(vo.getSrch_lwr_estimate_lvl(),""));			
			vo.setSrch_lwr_nm(StringUtil.bvl(vo.getSrch_lwr_nm(),""));

			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			
			if(form.getCurPage() == null) {
				form.setCurPage("1");
				vo.setCurPage("1");
			}

			String returnMessage = "";

			List resultList = lawyerManageService.listLawyerManage(vo);			
			// 사건 comboListBox
			EventManageVO evo = new EventManageVO();
			List eventList = lawyerManageService.listLawyerManageGetEventList(evo);
			
			// 로펌 comboListBox
			LawfirmManageVO lvo = new LawfirmManageVO();
			List lawfirmList = lawyerManageService.getListLawfirm(lvo);

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
			mav.addObject("eventList", eventList);
			mav.addObject("lawfirmList", lawfirmList);
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
	 * 변호사관리 등록화면 호출
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardLawyerManageInsert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			String forwardURL = "/WEB-INF/jsp/las/lawservice/LawyerManage_i.jsp";

			LawyerManageForm form = new LawyerManageForm();
			LawyerManageVO vo = new LawyerManageVO();
			bind(request, form);
			bind(request, vo);
			
			//검색옵션 사건리스트
			EventManageVO evo = new EventManageVO();
			List eventList = lawyerManageService.listLawyerManageGetEventList(evo);
			
			//로펌 리스트
			LawfirmManageVO lvo = new LawfirmManageVO();
			List lawfirmList = lawyerManageService.getListLawfirm(lvo);
			
			//전문분야리스트
			CLMSCommonVO cvo = new CLMSCommonVO();
			
			//전문분야 코드 세팅
			cvo.setGrp_cd("110");
			cvo.setSys_cd("LAS");
			List expertAreaList = clmsComService.listComCdByGrpCd2(cvo);

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("eventList", eventList);
			mav.addObject("lawfirmList", lawfirmList);
			mav.addObject("expertAreaList", expertAreaList);
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
	 * 변호사관리 등록처리
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertLawyerManage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			
			String forwardURL    = "";
			String returnMessage = "";

			LawyerManageForm form = null;
			LawyerManageVO vo = null;

			forwardURL = "/las/lawservice/lawyerManage.do?method=listLawyerManage";

			form = new LawyerManageForm();
			vo = new LawyerManageVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);		
			
			vo.setReg_id(vo.getSession_user_id());
			vo.setReg_nm(vo.getSession_user_nm());
			
			//Cross-site Scripting 방지
			form.setLwr_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getLwr_nm(),"")));
			form.setFirst_name(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getFirst_name(),"")));
			form.setLast_name(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getLast_name(),"")));
			form.setLwr_jikgup(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getLwr_jikgup(),"")));
			form.setTelno(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getTelno(),"")));
			form.setPay_lvl(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getPay_lvl(),"")));
			form.setEmail(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getEmail(),"")));
			form.setExpert_area(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getExpert_area(),"")));
			form.setFrom_school(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getFrom_school(),"")));
			form.setFrom_school_grdtyear(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getFrom_school_grdtyear(),"")));
			// form.setCareer_cont(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getCareer_cont(),"")));
			form.setLawschool(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getLawschool(),"")));
			form.setLawschool_grdtyear(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getLawschool_grdtyear(),"")));
			form.setNation(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getNation(),"")));
			
			// 변호사 시퀀스 세팅
			vo.setLwr_no(lawyerManageService.getMaxLawyerNo(vo));			
			
			lawyerManageService.insertLawyerManage(vo);

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
	 * 변호사관리 상세조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailLawyerManage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			// LawyerManageForm cmd = (LawyerManageForm)request.getAttribute("command");
			String forwardURL    = "";
			String returnMessage = "";
			String profile_image_yn;
			
			List resultList = null;
			//하단리스트
			List   		eventList = null;


			ArrayList al = null;
			ArrayList al_estimate = null;
			
			super.setCacheSeconds(0);
			forwardURL = "/WEB-INF/jsp/las/lawservice/LawyerManage_d.jsp?fakeid=" + System.currentTimeMillis();

			LawyerManageForm form = new LawyerManageForm();
			LawyerManageVO   vo   = new LawyerManageVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			// 상세정보취득
			resultList = lawyerManageService.detailLawyerManage(vo);
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			
			// 프로필 이미지 존재 여부 확인
			profile_image_yn = getLawyerProfileImg(vo.getLwr_no());
			
			// 나모 에디터 데이타 처리 
			lom.put("career_cont", StringUtil.convertCharsToHtml((String)lom.get("career_cont")));
			
			// 변호사 평가정보 취득 
			LawyerEstimateVO levo = new LawyerEstimateVO();
			levo.setLwr_no(vo.getLwr_no());
			List estimate_list = lawyerManageService.getListLawyerEstimate(levo);			
			
			//전문분야리스트 취득
			CLMSCommonVO cvo = new CLMSCommonVO();

			cvo.setGrp_cd("110");
			cvo.setSys_cd("LAS");
			List expertAreaList = clmsComService.listComCdByGrpCd2(cvo);
			
			// 하단 사건 리스트 
			List event_list = lawyerManageService.getLawyerEventList(vo);

			returnMessage = StringUtil.bvl((String)request.getAttribute("returnMessage"), "");
			
			//권한 처리
			LwsCommonVO lcvo = new LwsCommonVO();
			lcvo.setAuth_str_id(vo.getLwr_no());
			lcvo.setSession_user_role_cds(vo.getSession_user_role_cds());
			lcvo.setSession_user_id(vo.getSession_user_id());
			boolean lws_auth_modify = lwsCommonService.authLws(MODIFY, lcvo, "LAWYER");
			boolean lws_auth_delete = lwsCommonService.authLws(DELETE, lcvo, "LAWYER");			
			form.setLws_auth_modify(lws_auth_modify);
			form.setLws_auth_delete(lws_auth_delete);

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);			
			mav.addObject("resultList", resultList);
			mav.addObject("expertAreaList", expertAreaList);
			mav.addObject("lom", lom);
			mav.addObject("event_list", event_list);
			mav.addObject("estimate_list", estimate_list);
			mav.addObject("profile_image_yn", profile_image_yn);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.detailNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.detailNotice() Throwable !!");
		}
	}
	
	/**
	 * 변호사관리 변호사 프로파일 사진 경로 확인
	 * @param String
	 * @return String
	 * @throws Exception
	 */
	public String getLawyerProfileImg (String lwr_no) throws Exception {
		String img_path = "N";
		String uploadDir = propertyService.getProperty("clms.file.lawyer.dir");
		
		File f = new File(uploadDir+ "/" + lwr_no);
		
		if(f.exists()){
			img_path = "Y";
		}
		
		return img_path;
	}
	
	/**
	 * 변호사관리 수정화면 호출
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardLawyerManageModify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
		
			List resultList = null;
			String forwardURL = "";
			// String returnMessage = "";
			
			forwardURL = "/WEB-INF/jsp/las/lawservice/LawyerManage_i.jsp";
	
			LawyerManageForm form = new LawyerManageForm();
			LawyerManageVO   vo   = new LawyerManageVO();
	
			bind(request, form);
			bind(request, vo);
	
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
	
			//검색옵션 사건리스트
			EventManageVO evo = new EventManageVO();
			List eventList = lawyerManageService.listLawyerManageGetEventList(evo);
			
			//검색옵션 사건리스트
			EventManageVO evo2 = new EventManageVO();
			evo2.setLwr_no(vo.getLwr_no());
			List eventList2 = lawyerManageService.getLawyerEventList2(evo2);
			
			int event2_list_cnt = 0;

			if(eventList2!=null)
					event2_list_cnt = eventList2.size();			 
			
			//로펌 리스트
			LawfirmManageVO lvo = new LawfirmManageVO();
			List lawfirmList = lawyerManageService.getListLawfirm(lvo);
			
			// 상세정보취득
			resultList = lawyerManageService.detailLawyerManage(vo);
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			
			// 변호사 평가정보 취득 
			LawyerEstimateVO levo = new LawyerEstimateVO();
			levo.setLwr_no(vo.getLwr_no());
			List estimateList = lawyerManageService.getListLawyerEstimate(levo);
			
			int estimate_list_cnt = 0;
			
			if(estimateList!=null)
				estimate_list_cnt = estimateList.size();
			
			ArrayList al_estimate = (ArrayList)estimateList;
			form.setEstimate_list_cnt(estimate_list_cnt);		
			
			//전문분야리스트 취득
			CLMSCommonVO cvo = new CLMSCommonVO();
			cvo.setGrp_cd("110");
			cvo.setSys_cd("LAS");
			List expertAreaList = clmsComService.listComCdByGrpCd2(cvo);
	
			ModelAndView mav = new ModelAndView();
	
			mav.setViewName(forwardURL);
			mav.addObject("eventList", eventList);
			mav.addObject("eventList2", eventList2);
			mav.addObject("event2_list_cnt", new Integer(event2_list_cnt));
			mav.addObject("lawfirmList", lawfirmList);
			mav.addObject("expertAreaList", expertAreaList);
			mav.addObject("estimateList", estimateList);
			mav.addObject("al_estimate", al_estimate);
			mav.addObject("lom", lom);
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
	 * 변호사관리 수정처리
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifyLawyerManage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {			

			String forwardURL = "/las/lawservice/lawyerManage.do?method=detailLawyerManage";

			LawyerManageForm form = new LawyerManageForm();
			LawyerManageVO vo = new LawyerManageVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//Cross-site Scripting 방지
			form.setLwr_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getLwr_nm(),"")));
			form.setFirst_name(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getFirst_name(),"")));
			form.setLast_name(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getLast_name(),"")));
			form.setLwr_jikgup(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getLwr_jikgup(),"")));
			form.setTelno(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getTelno(),"")));
			form.setPay_lvl(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getPay_lvl(),"")));
			form.setEmail(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getEmail(),"")));
			form.setExpert_area(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getExpert_area(),"")));
			form.setFrom_school(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getFrom_school(),"")));
			form.setFrom_school_grdtyear(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getFrom_school_grdtyear(),"")));
			// form.setCareer_cont(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getCareer_cont(),"")));
			form.setLawschool(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getLawschool(),"")));
			form.setLawschool_grdtyear(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getLawschool_grdtyear(),"")));
			form.setNation(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getNation(),"")));
			
			vo.setReg_id(vo.getSession_user_id());
			vo.setReg_nm(vo.getSession_user_nm());	
			
			//권한 처리
			LwsCommonVO lcvo = new LwsCommonVO();
			lcvo.setAuth_str_id(vo.getLwr_no());
			lcvo.setSession_user_role_cds(vo.getSession_user_role_cds());
			lcvo.setSession_user_id(vo.getSession_user_id());
			boolean lws_auth_modify = lwsCommonService.authLws(MODIFY, lcvo, "LAWYER");
			if(!lws_auth_modify){
				ModelAndView mav = new ModelAndView() ; 
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				mav.addObject("secfw.alert.title", messageSource.getMessage("secfw.page.field.alert.noModifyAuth", null, new RequestContext(request).getLocale()));
				mav.addObject("secfw.alert.message", messageSource.getMessage("secfw.page.field.alert.noLinkMessage", null, new RequestContext(request).getLocale()));
				return mav;
			}	

			lawyerManageService.modifyLawyerManage(vo);

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
	 * 변호사관리 삭제처리
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView deleteLawyerManage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {

			String forwardURL = "/las/lawservice/lawyerManage.do?method=listLawyerManage";

			LawyerManageForm form = new LawyerManageForm();
			LawyerManageVO vo = new LawyerManageVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//권한 처리
			LwsCommonVO lcvo = new LwsCommonVO();
			lcvo.setAuth_str_id(vo.getLwr_no());
			lcvo.setSession_user_role_cds(vo.getSession_user_role_cds());
			lcvo.setSession_user_id(vo.getSession_user_id());
			boolean lws_auth_del = lwsCommonService.authLws(DELETE, lcvo, "LAWYER");
			if(!lws_auth_del){
				ModelAndView mav = new ModelAndView() ; 
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				mav.addObject("secfw.alert.title", messageSource.getMessage("secfw.page.field.alert.noDeleteAuth", null, new RequestContext(request).getLocale()));
				mav.addObject("secfw.alert.message", messageSource.getMessage("secfw.page.field.alert.noLinkMessage", null, new RequestContext(request).getLocale()));
				return mav;
			}	

			int result = lawyerManageService.deleteLawyerManage(vo);

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
	 * 변호사관리 프로파일 사진 업로드 팝업호출
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardUploadLawyerProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {			

			String forwardURL    = "";

			forwardURL = "/WEB-INF/jsp/las/lawservice/LawyerProfileUpload_p.jsp";

			LawyerManageForm form = new LawyerManageForm();
			LawyerManageVO vo = new LawyerManageVO();

			bind(request, form);
			bind(request, vo);

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
	 * 변호사관리 프로파일 사진 업로드 부모창 상세 화면 호출
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardDetailLawywerFromPopUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {			
			String forwardURL = "/las/lawservice/lawyerManage.do?method=detailLawyerManage";

			LawyerManageForm form = new LawyerManageForm();
			LawyerManageVO vo = new LawyerManageVO();

			bind(request, form);
			bind(request, vo);

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
		
}