/**
 * Project Name : 법무시스템
 * File Name : LawfirmManageController.java
 * Description : 로펌관리 Controller
 * Author : 박병주
 * Date : 2011.08.29
 * Copyright : 2011 by SAMSUNG. All rights reserved.
 */

package com.sec.las.lawservice.control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;
import com.sds.secframework.auth.service.ClassMethodAuthService;
import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.util.service.ComUtilService;
import com.sec.las.lawservice.dto.EventManageVO;
import com.sec.las.lawservice.dto.LawfirmEstimateForm;
import com.sec.las.lawservice.dto.LawfirmEstimateVO;
import com.sec.las.lawservice.dto.LawfirmManageForm;
import com.sec.las.lawservice.dto.LawfirmManageVO;
import com.sec.las.lawservice.dto.LawyerManageVO;
import com.sec.las.lawservice.dto.LwsCommonVO;
import com.sec.las.lawservice.service.LawfirmManageService;
import com.sec.las.lawservice.service.LawyerManageService;
import com.sec.las.lawservice.service.LwsCommonService;

public class LawfirmManageController extends CommonController {

	private LawfirmManageService LawfirmManageService;
	public void setLawfirmManageService(LawfirmManageService LawfirmManageService) {
		this.LawfirmManageService = LawfirmManageService;
	}
	
	private LwsCommonService lwsCommonService;
	public void setLwsCommonService(LwsCommonService lwsCommonService) {
		this.lwsCommonService = lwsCommonService;
	}
	
	private LawyerManageService lawyerManageService;
	public void setLawyerManageService(LawyerManageService lawyerManageService) {
		this.lawyerManageService = lawyerManageService;
	}

	private ComUtilService comUtilService;
	public void setComUtilService(ComUtilService comUtilService) {
		this.comUtilService = comUtilService;
	}
	
	private ClassMethodAuthService classMethodAuthService = null ;
	public void setClassMethodAuthService(ClassMethodAuthService classMethodAuthService) {
		this.classMethodAuthService = classMethodAuthService;
	}
	/**
	 * 로펌관리 목록
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listLawfirmManage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			String forwardURL = "/WEB-INF/jsp/las/lawservice/LawfirmManage_l.jsp";
			String returnMessage = "";
			
			LawfirmManageForm form = null;
			LawfirmManageVO vo =	null;		

			form = new LawfirmManageForm();
			vo = new LawfirmManageVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//XSS
			form.setSrch_event_no(StringUtil.bvl(form.getSrch_event_no(),""));
			form.setSrch_event_nm(StringUtil.bvl(form.getSrch_event_nm(),""));
			form.setSrch_lawfirm_id(StringUtil.bvl(form.getSrch_lawfirm_id(),""));
			form.setSrch_lawfirm_nm(StringUtil.bvl(form.getSrch_lawfirm_nm(),""));
			form.setSrch_kbn1(StringUtil.bvl(form.getSrch_kbn1(),""));
			form.setSrch_kbn2(StringUtil.bvl(form.getSrch_kbn2(),""));
			form.setSrch_mainftre_estmt(StringUtil.bvl(form.getSrch_mainftre_estmt(),""));
			form.setSrch_nation(StringUtil.bvl(form.getSrch_nation(),""));
			form.setSrch_start_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_start_dt(),"")));
			form.setSrch_end_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_end_dt(),"")));

			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());

			List resultList = LawfirmManageService.listLawfirmManage(vo);
			
			// 로펌 comboListBox
			LawfirmManageVO lvo = new LawfirmManageVO();
			List lawfirmList = lawyerManageService.getListLawfirm(lvo);
			
			// 사건 comboListBox
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
	 * 로펌관리 등록화면 호출
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardLawfirmManageInsert(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			String forwardURL = "/WEB-INF/jsp/las/lawservice/LawfirmManage_i.jsp";

			LawfirmManageForm form = new LawfirmManageForm();
			LawfirmManageVO vo = new LawfirmManageVO();

			bind(request, form);
			bind(request, vo);

			// 사건 comboListBox
			EventManageVO evo = new EventManageVO();
			List eventList = lawyerManageService.listLawyerManageGetEventList(evo);

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("eventList", eventList);
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
	 * 로펌관리 등록
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertLawfirmManage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			String forwardURL    = "";
			String returnMessage = "";

			LawfirmManageForm form = null;
			LawfirmManageVO vo = null;

			forwardURL = "/las/lawservice/lawfirmManage.do?method=listLawfirmManage";

			form = new LawfirmManageForm();
			vo = new LawfirmManageVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//XSS
			form.setLawfirm_id(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getLawfirm_id(),"")));
			form.setNation(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getNation(),"")));
			form.setNation_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getNation_nm(),"")));
			form.setLawfirm_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getLawfirm_nm(),"")));
			form.setFst_event_no(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getFst_event_no(),"")));
			form.setFst_cntrtday(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getFst_cntrtday(),"")));
			form.setRprsnt_tel(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getRprsnt_tel(),"")));
			form.setRprsnt_fax(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getRprsnt_fax(),"")));
			form.setRprsnt_email(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getRprsnt_email(),"")));
			form.setHomepage(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getHomepage(),"")));
			form.setState_gbn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getState_gbn(),"")));
			form.setAddr(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getAddr(),"")));
			form.setAccnt_no(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getAccnt_no(),"")));
			form.setMainftre_estmt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getMainftre_estmt(),"")));
			form.setBank_addr(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getBank_addr(),"")));
			form.setBank_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getBank_nm(),"")));
			form.setBank_note(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getBank_note(),"")));
			form.setLog_ymd(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getLog_ymd(),"")));

			vo.setReg_id(vo.getSession_user_id());
			vo.setReg_nm(vo.getSession_user_nm());			

			vo.setLawfirm_id(LawfirmManageService.getMaxLawfirmID(vo));

			LawfirmManageService.insertLawfirmManage(vo);

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
	 * 로펌평가 등록화면 호출
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardLawfirmEstimateInsert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {			

			String forwardURL = "/WEB-INF/jsp/las/lawservice/AddLawfirmEstimate_p.jsp";		

			LawfirmManageForm form = new LawfirmManageForm();
			LawfirmManageVO vo = new LawfirmManageVO();

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
	 * 로펌평가등록
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertLawfirmEstimate(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {

			String forwardURL    = "";
			String returnMessage = "";

			LawfirmEstimateForm form = null;
			LawfirmEstimateVO vo = null;

			form = new LawfirmEstimateForm();
			vo = new LawfirmEstimateVO();

			bind(request, form);
			bind(request, vo);			

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//XSS
			form.setLawfirm_id(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getLawfirm_id(),"")));
			form.setEstmt_name(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getEstmt_name(),"")));
			form.setEstmt_cont(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getEstmt_cont(),"")));

			vo.setReg_id(vo.getSession_user_id());
			vo.setReg_nm(vo.getSession_user_nm());

			LawfirmManageService.insertLawfirmEstimate(vo);

			returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());

			ModelAndView mav = new ModelAndView();

			forwardURL = "/las/lawservice/lawfirmManage.do?method=detailLawfirmManage";

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
	 * 로펌관리 상세
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailLawfirmManage(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {

			// LawfirmEstimateForm cmd = (LawfirmEstimateForm)request.getAttribute("command");

			String forwardURL    = "";
			String returnMessage = "";
			String strUrl = (request.getRequestURL()).toString();
			String strAttachUrl = strUrl.substring(0, strUrl.indexOf("/", 7) + 1) + "clms/common/clmsfile.do?method=doClmsDownload&file_id=";

			LawfirmManageForm form       = null;
			LawfirmManageVO   vo         = null;
			
			List   		resultList = null;
			List   		eventList = null;
			List   		estimateList = null;

			ArrayList al = null;
			ArrayList al_estimate = null;

			forwardURL =  "/WEB-INF/jsp/las/lawservice/LawfirmManage_d.jsp";

			form = new LawfirmManageForm();
			vo = new LawfirmManageVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

/*			if(cmd != null){
				form.setLawfirm_id(cmd.getLawfirm_id());
				vo.setLawfirm_id(cmd.getLawfirm_id());
			}*/

			// 상세정보취득
			resultList = LawfirmManageService.detailLawfirmManage(vo);
			ListOrderedMap lom = null;

			if(resultList!=null){				
				lom = (ListOrderedMap)resultList.get(0);
			} 

			// 로펌 평가 
			LawfirmEstimateVO esvo = new LawfirmEstimateVO();
			esvo.setLawfirm_id(vo.getLawfirm_id());
			estimateList = LawfirmManageService.listLawfirmEstimate(esvo);
			
		    for(int i=0; i<estimateList.size(); i++){
		    	String file_path = "";
				lom = (ListOrderedMap)estimateList.get(i);
				file_path = strAttachUrl + (String)lom.get("file_id");
				lom.put("file_path_temp", file_path);
		    }
			
			al_estimate = (ArrayList)estimateList;
			
			int estimate_list_cnt = 0;

			// 로펌 평가 seq
			form.setEstmt_no(LawfirmManageService.getNextEstmtNo(vo));		
			form.setEstimate_list_cnt(estimate_list_cnt);
			
			// 소속 변호사 취득
			LawyerManageVO lvo = new LawyerManageVO();
			lvo.setLawfirm_id(vo.getLawfirm_id());
			ArrayList lwr_list = (ArrayList)LawfirmManageService.getListLawfirmLawywer(lvo);

			// 하단 사건 리스트 
			EventManageVO evo = new EventManageVO();
			evo.setLawfirm_id(vo.getLawfirm_id());
			eventList = LawfirmManageService.listLawfrimManageGetEventList(evo);
			al = (ArrayList)eventList;		

			int event_list_cnt = 0;
			if(estimateList!=null)
				event_list_cnt = eventList.size();

			form.setEvent_list_cnt(event_list_cnt);

			returnMessage = StringUtil.bvl((String)request.getAttribute("returnMessage"), "");
			
			ModelAndView mav = new ModelAndView();	
			
			//권한 처리
			LwsCommonVO lcvo = new LwsCommonVO();
			lcvo.setAuth_str_id(vo.getLawfirm_id());
			lcvo.setSession_user_role_cds(vo.getSession_user_role_cds());
			lcvo.setSession_user_id(vo.getSession_user_id());
			boolean lws_auth_modify = lwsCommonService.authLws(MODIFY, lcvo, "LAWFIRM");
			boolean lws_auth_delete = lwsCommonService.authLws(DELETE, lcvo, "LAWFIRM");			
			form.setLws_auth_modify(lws_auth_modify);
			form.setLws_auth_delete(lws_auth_delete);

			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
			mav.addObject("lom", lom);
			mav.addObject("al", al);
			mav.addObject("el", al_estimate);
			mav.addObject("lwr_list", lwr_list);
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
	 * 로펌관리 수정화면 호출
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardLawfirmManageModify(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {			

			List resultList = null;
			String  forwardURL = "/WEB-INF/jsp/las/lawservice/LawfirmManage_i.jsp";
			String returnMessage = "";

			LawfirmManageForm form = new LawfirmManageForm();
			LawfirmManageVO vo = new LawfirmManageVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			// 상세정보취득
			resultList = LawfirmManageService.detailLawfirmManage(vo);
			ListOrderedMap lom = null;

			// 상세정보 세팅
			if(resultList!=null){				

				lom = (ListOrderedMap)resultList.get(0);

				form.setLawfirm_id((String)lom.get("lawfirm_id"));
				form.setNation((String)lom.get("nation"));
				form.setNation_nm((String)lom.get("nation_nm"));
				form.setLawfirm_nm((String)lom.get("lawfirm_nm"));
				form.setFst_event_no((String)lom.get("fst_event_no"));
				form.setFst_cntrtday((String)lom.get("fst_cntrtday"));
				form.setRprsnt_tel((String)lom.get("rprsnt_tel"));
				form.setRprsnt_fax((String)lom.get("rprsnt_fax"));
				form.setRprsnt_email((String)lom.get("rprsnt_email"));
				form.setHomepage((String)lom.get("homepage"));
				form.setState_gbn((String)lom.get("state_gbn"));
				form.setAddr((String)lom.get("addr"));
				form.setAccnt_no((String)lom.get("accnt_no"));
				form.setMainftre_estmt((String)lom.get("mainftre_estmt"));
				form.setBank_addr((String)lom.get("bank_addr"));
				form.setBank_nm((String)lom.get("bank_nm"));
				form.setBank_note((String)lom.get("bank_note"));
				form.setLog_ymd((String)lom.get("log_ymd"));

			} else {
				 returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale()) ;
			}

			// 사건 comboListBox
			EventManageVO evo = new EventManageVO();
			List eventList = lawyerManageService.listLawyerManageGetEventList(evo);

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
			mav.addObject("eventList", eventList);
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
	 * 로펌관리 수정
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifyLawfirmManage(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			
			String forwardURL = "/las/lawservice/lawfirmManage.do?method=detailLawfirmManage";

			LawfirmManageForm form = new LawfirmManageForm();
			LawfirmManageVO vo = new LawfirmManageVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			form.setMod_id(vo.getSession_user_id());
			form.setMod_nm(vo.getSession_user_nm());
			
			//XSS
			form.setLawfirm_id(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getLawfirm_id(),"")));
			form.setNation(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getNation(),"")));
			form.setNation_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getNation_nm(),"")));
			form.setLawfirm_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getLawfirm_nm(),"")));
			form.setFst_event_no(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getFst_event_no(),"")));
			form.setFst_cntrtday(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getFst_cntrtday(),"")));
			form.setRprsnt_tel(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getRprsnt_tel(),"")));
			form.setRprsnt_fax(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getRprsnt_fax(),"")));
			form.setRprsnt_email(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getRprsnt_email(),"")));
			form.setHomepage(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getHomepage(),"")));
			form.setState_gbn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getState_gbn(),"")));
			form.setAddr(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getAddr(),"")));
			form.setAccnt_no(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getAccnt_no(),"")));
			form.setMainftre_estmt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getMainftre_estmt(),"")));
			form.setBank_addr(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getBank_addr(),"")));
			form.setBank_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getBank_nm(),"")));
			form.setBank_note(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getBank_note(),"")));
			form.setLog_ymd(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getLog_ymd(),"")));
			
			//권한 처리
			LwsCommonVO lcvo = new LwsCommonVO();
			lcvo.setAuth_str_id(vo.getLawfirm_id());
			lcvo.setSession_user_role_cds(vo.getSession_user_role_cds());
			lcvo.setSession_user_id(vo.getSession_user_id());
			boolean lws_auth_modify = lwsCommonService.authLws(MODIFY, lcvo, "LAWFIRM");			
			if(!lws_auth_modify){
				ModelAndView mav = new ModelAndView() ; 
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				mav.addObject("secfw.alert.title", messageSource.getMessage("secfw.page.field.alert.noModifyAuth", null, new RequestContext(request).getLocale()));
				mav.addObject("secfw.alert.message", messageSource.getMessage("secfw.page.field.alert.noLinkMessage", null, new RequestContext(request).getLocale()));
				return mav;
			}				

			LawfirmManageService.modifyLawfirmManage(vo);

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
	 * 로펌관리 삭제
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView deleteLawfirmManage(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {		

			String forwardURL = "/las/lawservice/lawfirmManage.do?method=listLawfirmManage";

			LawfirmManageForm form = new LawfirmManageForm();
			LawfirmManageVO vo = new LawfirmManageVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			form.setDel_id((vo.getSession_user_id()));
			form.setDel_nm(vo.getSession_user_nm());
			
			//권한 처리
			LwsCommonVO lcvo = new LwsCommonVO();
			lcvo.setAuth_str_id(vo.getLawfirm_id());
			lcvo.setSession_user_role_cds(vo.getSession_user_role_cds());
			lcvo.setSession_user_id(vo.getSession_user_id());
			boolean lws_auth_del = lwsCommonService.authLws(DELETE, lcvo, "LAWFIRM");
			if(!lws_auth_del){
				ModelAndView mav = new ModelAndView() ; 
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				mav.addObject("secfw.alert.title", messageSource.getMessage("secfw.page.field.alert.noDeleteAuth", null, new RequestContext(request).getLocale()));
				mav.addObject("secfw.alert.message", messageSource.getMessage("secfw.page.field.alert.noLinkMessage", null, new RequestContext(request).getLocale()));
				return mav;
			}	

			LawfirmManageService.deleteLawfirmManage(vo);

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
	 * 로펌평가 삭제
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView deleteLawfirmEstimate(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {

			String forwardURL = "/las/lawservice/lawfirmManage.do?method=detailLawfirmManage";

			LawfirmEstimateForm form = null;
			LawfirmEstimateVO vo = null;

			form = new LawfirmEstimateForm();
			vo = new LawfirmEstimateVO();

			bind(request, form);
			bind(request, vo);			

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			vo.setEstmt_no(form.getEstmt_no());
			vo.setLawfirm_id(form.getLawfirm_id());

			LawfirmManageService.deleteLawfirmEstimate(vo);

			String returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());

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

}