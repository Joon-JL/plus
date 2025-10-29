package com.sec.clm.manage.control;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.util.service.ComUtilService;
import com.sec.clm.manage.dto.ConclusionDelayForm;
import com.sec.clm.manage.dto.ConclusionDelayVO;
import com.sec.clm.manage.dto.ConclusionForm;
import com.sec.clm.manage.dto.ConclusionVO;
import com.sec.clm.manage.dto.ConsultationForm;
import com.sec.clm.manage.dto.ConsultationSpecialForm;
import com.sec.clm.manage.dto.ConsultationSpecialVO;
import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.manage.dto.ExecutionVO;
import com.sec.clm.manage.service.ConclusionDelayService;
import com.sec.clm.manage.service.ConclusionService;
import com.sec.clm.manage.service.ConsiderationService;
import com.sec.clm.manage.service.ConsultationService;
import com.sec.clm.manage.service.ExecutionService;
import com.sec.clm.manage.service.MailSendService;
import com.sec.common.clmscom.service.CLMSCommonService;

import net.sf.json.JSONObject;

/**
 * 계약관리>체결본등록 
 * @author HP
 *
 */
public class ConclusionController extends CommonController {

	private ManageController manageControl;
	
	public void setManageControl(ManageController manageControl) {
		this.manageControl = manageControl;
	}	
	
	/**
	 * ConclusionService 서비스
	 */
	private ConclusionService conclusionService;
	/**
	 * ConclusionService 서비스 세팅
	 * @param conclusionService
	 * @return void
	 * @throws
	 */
	public void setConclusionService(ConclusionService conclusionService) {
		this.conclusionService = conclusionService;
	}
	
	/**
	 * ConsultationService 서비스
	 */
	private ConsultationService consultationService;
	/**
	 * ConsultationService 서비스 세팅
	 * @param consultationService
	 * @return void
	 * @throws
	 */
	public void setConsultationService(ConsultationService consultationService) {
		this.consultationService = consultationService;
	}
	
	/**
	 * ConclusionDelayService 서비스
	 */
	private ConclusionDelayService conclusionDelayService;
	/**
	 * ConclusionDelayService 서비스 세팅
	 * @param conclusionDelayService
	 * @return void
	 * @throws
	 */
	public void setConclusionDelayService(ConclusionDelayService conclusionDelayService) {
		this.conclusionDelayService = conclusionDelayService;
	}
	
	/**
	 * ComUtil 서비스
	 */
	private ComUtilService comUtilService;
	
	/**
	 * ComUtil 서비스 세팅
	 * @param comUtilService
	 * @return void
	 * @throws
	 */
	public void setComUtilService(ComUtilService comUtilService) {
		this.comUtilService = comUtilService;
	}
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private CLMSCommonService clmsComService;
	public void setClmsComService(CLMSCommonService clmsComService)
	{
		this.clmsComService = clmsComService;
	}
	
	private ExecutionService executionService;
	
	public void setExecutionService(ExecutionService executionService) {
		this.executionService = executionService;
	}
	
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private ConsiderationService considerationService;
	public void setConsiderationService(ConsiderationService considerationService) { 
		this.considerationService = considerationService;
	}
	
	/**
	* 계약등록 목록
	* 작성자 : 신남원 : 2011.09.29
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listManageConclusion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		request.setAttribute("status_mode", "C02503");
		mav = manageControl.listManage(request, response);
		
		return mav; 
	}

	/**
	 * MailSend Service added Sungwoo 2014-06-23
	 */
	private MailSendService mailSendService ;
	public void setMailSendService(MailSendService mailSendService) {
		this.mailSendService = mailSendService;
	}
	
	
	/**
	 * 체결본등록계약정보 조회
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView listConclusionContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {	
			List resultList = null;
			ListOrderedMap masterLom = null;
			String forwardURL = "/WEB-INF/jsp/clm/manage/Conclusion_d.jsp";

			ConclusionForm form = new ConclusionForm();
			ConclusionVO vo = new ConclusionVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			ModelAndView mav = new ModelAndView();
			
			String returnMessage = "";

			resultList = conclusionService.listConclusion(vo);
			
			if (resultList != null && resultList.size() > 0) {
				masterLom = (ListOrderedMap)resultList.get(0);

				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}
			}else {
				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}
			}
		
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +forwardURL);
			
			mav.addObject("resultList", resultList);
			mav.addObject("masterLom", masterLom);
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
	 * 체결본 등록정보 등록
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView modifyConclusion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);

			String forwardURL = "/clm/manage/conclusion.do?method=detailConclusion";

			ConclusionForm form = new ConclusionForm();
			ConclusionVO vo = new ConclusionVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//팝업으로 지연사유 관리 안함으로 인한 추가2011.11.16
			ConclusionDelayForm delayForm	= new ConclusionDelayForm();
			ConclusionDelayVO	delayVo		= new ConclusionDelayVO();
			
			bind(request, delayForm);
			bind(request, delayVo);
			
			COMUtil.getUserAuditInfo(request, delayForm);
			COMUtil.getUserAuditInfo(request, delayVo);
			
			ModelAndView mav = new ModelAndView();
			
			vo.setIp_address((String)session.getAttribute("secfw.session.loginIP"));
			
			int result = conclusionService.modifyConclusion(vo);
			this.getLogger().debug("No of the Modified conclusionService : " + result);
			
			String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
		
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +forwardURL);
			
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
	
	public ModelAndView detailConclusionContractMaster(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL	= "/WEB-INF/jsp/clm/manage/Conclusion_d_master.jsp";
			List authReqList	= null;//권한요청자 -관련자
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ConclusionForm contractForm = new ConclusionForm();
			ConclusionVO contractVo = new ConclusionVO();	
			
			bind(request, contractForm);
			bind(request, contractVo);
			
			COMUtil.getUserAuditInfo(request, contractForm);
			COMUtil.getUserAuditInfo(request, contractVo);
			
			ConsultationVO	consultationVo = new ConsultationVO();
			
			ModelAndView mav = new ModelAndView();
		
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			List contractList = conclusionService.detailConclusionContractMaster(contractVo);
			consultationVo.setCntrt_id(contractVo.getCntrt_id());
			authReqList = considerationService.listContractAuthreq(consultationVo);//권한요청자 -관련자
			
			StringBuffer reqAuthInfo		= new StringBuffer();
			StringBuffer reqAuthFormInfo	= new StringBuffer();
			StringBuffer reqAuthSvcInfo		= new StringBuffer();
			
			StringBuffer reqAuthSeqno		= new StringBuffer();
			StringBuffer reqAuthTrgrtman_id	= new StringBuffer();
			StringBuffer reqAuthTrgrtman_nm	= new StringBuffer();
			StringBuffer reqAuthTrgrtman_jikgup	= new StringBuffer();
			StringBuffer reqAuthTrgrtman_dept	= new StringBuffer();
			
			if(authReqList != null && authReqList.size() > 0) {
				for(int i=0; i < authReqList.size(); i++) {
					ListOrderedMap authLom	= (ListOrderedMap)authReqList.get(i);
					if(i > 0){
						reqAuthInfo.append(", " + StringUtil.bvl((String)authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_dept_nm"), "")); //display용
						reqAuthSvcInfo.append("!@#$" + authLom.get("demnd_seqno") + "|" + authLom.get("trgtman_id") + "|" + authLom.get("trgtman_nm") + "|" + authLom.get("trgtman_jikgup_nm") + "|" + authLom.get("trgtman_dept_nm")); //팝업과통신용
						reqAuthSeqno.append("," + authLom.get("demnd_seqno"));
						reqAuthTrgrtman_id.append("," + authLom.get("trgtman_id"));
						reqAuthTrgrtman_nm.append("," + authLom.get("trgtman_nm"));
						reqAuthTrgrtman_jikgup.append("," + authLom.get("trgtman_jikgup_nm"));
						reqAuthTrgrtman_dept.append("," + authLom.get("trgtman_dept_nm"));
					} else {
						reqAuthInfo.append(StringUtil.bvl((String)authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_dept_nm"), ""));
						reqAuthSvcInfo.append(authLom.get("demnd_seqno") + "|" + authLom.get("trgtman_id") + "|" + authLom.get("trgtman_nm") + "|" + authLom.get("trgtman_jikgup_nm") + "|" + authLom.get("trgtman_dept_nm")); //팝업과통신용
						reqAuthSeqno.append(authLom.get("demnd_seqno"));
						reqAuthTrgrtman_id.append(authLom.get("trgtman_id"));
						reqAuthTrgrtman_nm.append(authLom.get("trgtman_nm"));
						reqAuthTrgrtman_jikgup.append(authLom.get("trgtman_jikgup_nm"));
						reqAuthTrgrtman_dept.append(authLom.get("trgtman_dept_nm"));
					}
				}
				reqAuthFormInfo.append(reqAuthSeqno.toString() + "!@#$" + reqAuthTrgrtman_id.toString() + "!@#$" + reqAuthTrgrtman_nm.toString() +"!@#$" + reqAuthTrgrtman_jikgup.toString() + "!@#$" + reqAuthTrgrtman_dept.toString());
			}
			
			ArrayList contractArrayList = (ArrayList)contractList;			
			ListOrderedMap contractLom	= (ListOrderedMap)contractList.get(0);
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +forwardURL);
			
			if(reqAuthInfo != null && !"".equals(reqAuthInfo.toString())) {
				mav.addObject("reqAuthInfo", reqAuthInfo.toString());
				mav.addObject("reqAuthFormInfo", reqAuthFormInfo.toString());
				mav.addObject("reqAuthSvcInfo", reqAuthSvcInfo.toString());
			} else {
				mav.addObject("reqAuthInfo", "");
				mav.addObject("reqAuthFormInfo", "");
				mav.addObject("reqAuthSvcInfo", "");
			}
			mav.addObject("contractArrayList", contractArrayList);
			mav.addObject("contractLom", contractLom);
			mav.addObject("contractCommand", contractForm);
			
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/*
	 * 목록화면에서 클릭하여 상세화면으로 들어온 경우
	 * */
	public ModelAndView detailConclusion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);
			String forwardURL = "/WEB-INF/jsp/clm/manage/Conclusion_d_view.jsp";
			String user_role	= "";
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ConclusionForm form = new ConclusionForm();
			ConclusionVO vo = new ConclusionVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			if("modify".equals(form.getPage_gbn())) {
				forwardURL = "/WEB-INF/jsp/clm/manage/Conclusion_d.jsp";
			}
			
			if(vo.getCntrt_id() == null){
			}else if("".equals(vo.getCntrt_id())){
				vo.setCntrt_id(null);
			}
			
			ModelAndView mav = new ModelAndView();
				
			List tmpSessionRoleList = (List)session.getAttribute("secfw.session.role") ;
			
			for (int i = 0; i < tmpSessionRoleList.size(); i++) {
				Map tmpSessionRoleMap = (Map) tmpSessionRoleList.get(i);
				if (tmpSessionRoleMap.containsValue("RD01")) {
					user_role = "RD01";
				}
			}
			form.setUser_role(user_role);
			form.setUser_orgnz(vo.getSession_blngt_orgnz());
			
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			List dcList = conclusionService.detailOnlyConclusion(vo); //계약등록상태의 계약건만 조회되도록 수정 2011.12.20
			ListOrderedMap lomReq = (ListOrderedMap)dcList.get(0);
			ArrayList lomMaster = (ArrayList)dcList;			
		
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +forwardURL);
			
			mav.addObject("lomReq", lomReq);
			mav.addObject("lomMaster", lomMaster);
			mav.addObject("dcList", dcList);
			mav.addObject("command", form);
			mav.addObject("ssems_oppnt_cd",StringUtil.bvl(request.getParameter("ssems_oppnt_cd"),""));//ssems인터페이스시 사용
			mav.addObject("cntrt_no",StringUtil.bvl(request.getParameter("cntrt_no"),""));//ssems인터페이스시 사용
			
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	//체결본등록 - 특화정보
	public ModelAndView listConclusionSpecial(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL					= "";
			String returnMessage				= "";
			
			ConclusionForm	form			= null;
			ConclusionVO	vo			= null;
			ConsultationSpecialForm	specialForm	= null;
			ConsultationSpecialVO	specialVo	= null;
			List	resultList	= null;
			ListOrderedMap	lom			= null;
			int td_cnt	= 0;
			
			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/manage/Conclusion_d_special.jsp";
	
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/			
			form = new ConclusionForm();
			vo	= new ConclusionVO();
			
			specialForm = new ConsultationSpecialForm();
			specialVo	= new ConsultationSpecialVO();
			
			bind(request, form);
			bind(request, vo);
			bind(request, specialForm);
			bind(request, specialVo);
		
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			COMUtil.getUserAuditInfo(request, specialForm);
			COMUtil.getUserAuditInfo(request, specialVo);
			
			ModelAndView mav = new ModelAndView();
		
			resultList = consultationService.listConsultationSpecial(specialVo);
			if(resultList.size() >0 && resultList != null) {
				 lom	= (ListOrderedMap)resultList.get(0);
				 td_cnt = (Integer)lom.get("td_cnt");
			 }	
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +forwardURL);
			
			mav.addObject("command", form);
			mav.addObject("td_cnt", td_cnt);
			mav.addObject("specialCommand", specialForm);
			mav.addObject("specialList", resultList);
			mav.addObject("returnMessage", returnMessage);
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("listConclusionSpecial() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("listConclusionSpecial() Throwable !!");
		}
	}
	
	//지연정보
	public ModelAndView listConclusionDelay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL				= "";
			String returnMessage			= "";
			
			ConclusionForm form				= null;
			ConclusionVO	vo				= null;
			
			ConclusionDelayForm delayForm	= null;
			ConclusionDelayVO	delayVO		= null;
			List resultList				= null;
			
			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/manage/Conclusion_d_delay.jsp";
	
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/			
			form = new ConclusionForm();
			vo	= new ConclusionVO();
			
			delayForm = new ConclusionDelayForm();
			delayVO	= new ConclusionDelayVO();
			
			
			bind(request, form);
			bind(request, vo);
			
			bind(request, delayForm);
			bind(request, delayVO);
		
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			COMUtil.getUserAuditInfo(request, delayForm);
			COMUtil.getUserAuditInfo(request, delayVO);
			
			ModelAndView mav = new ModelAndView();
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = conclusionDelayService.listConclusionDelayForMain(delayVO);	//2011.11.16 심주완수정(히스토리관리안함)
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +forwardURL);
			
			mav.addObject("command", form);
			mav.addObject("delayCommand", delayForm);
			mav.addObject("delayList", resultList);
			mav.addObject("returnMessage", returnMessage);
			mav.addObject("delaySize", resultList.size());

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("listConclusionSpecial() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("listConclusionSpecial() Throwable !!");
		}
	}
	
	/**
	 * drop 팝업 -> 보류 팝업 12/06/29
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView opnnConclusion(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		try {

			String forwardURL = "/WEB-INF/jsp/clm/manage/Conclusion_opnn_p.jsp";
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ConclusionForm form = new ConclusionForm();
			ConclusionVO vo = new ConclusionVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);	
			this.getLogger().debug("forwardURL: " +forwardURL);
			
			mav.addObject("command", form);		

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
	 * 계약건 drop
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void dropContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ListOrderedMap returnMap = new ListOrderedMap();
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			
			JSONObject jo = new JSONObject();
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			vo.setDel_id(vo.getSession_user_id());
			vo.setDel_nm(vo.getSession_user_nm());
			vo.setDel_yn("Y");
						
			/*********************************************************
			 * 내역 등록
			**********************************************************/
			returnMap = conclusionService.dropDefer(vo);
			
			//Drop 계약건 메일발송 
			ListOrderedMap paramTargetMap = new ListOrderedMap();
			paramTargetMap.put("cnsdreq_id",vo.getCnsdreq_id());
			paramTargetMap.put("cntrt_id",vo.getCntrt_id());
			//Contract Drop인 경우 Y임
			//paramTargetMap.put("postproc_yn","NR");	//후처리Proc 여부
			paramTargetMap.put("postproc_yn","Y");	//후처리Proc 여부
			paramTargetMap.put("rc_flag","C");		//의뢰번호(R), 계약번호(C)
			
			// TODO : 실제 운영시 주석해제 해줄것
			//consultationService.sendDropReq(paramTargetMap);
			
			jo.put("returnCd", returnMap.get("cd"));			
			jo.put("returnMsg", returnMap.get("msg"));			
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
			
		} catch (Exception e) {
			JSONObject jo		= new JSONObject();
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo		= new JSONObject();
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
			
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}
	
	/**
	 * 계약건 보류해제
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ModelAndView deferNoneContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/	
			String forwardURL = "/WEB-INF/jsp/clm/manage/Conclusion_d_view.jsp";
			ModelAndView mav = new ModelAndView();
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ListOrderedMap returnMap = new ListOrderedMap();
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * 내역 등록
			 **********************************************************/
			returnMap = conclusionService.dropDefer(vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +forwardURL);
			
			mav.addObject("command", form);
			
			return detailConclusion(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 체결미등록에서 최종본 회신상태로 프로세스 복원 2012-07-03
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView cancelConclusion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "/clm/manage/conclusion.do?method=detailConclusion";

			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			ModelAndView mav = new ModelAndView();
			int result = conclusionService.cancelConclusion(vo);
			
			forwardURL = "/clm/manage/conclusion.do?method=listManageConclusion";
			
			String returnMessage = messageSource.getMessage("secfw.msg.succ.complete", null, new RequestContext(request).getLocale());
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +forwardURL);
			
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
	
	public Log getLogger() {
		return LogFactory.getLog(this.getClass());
	}
	
	/*
	 * 목록화면에서 클릭하여 상세화면으로 들어온 경우
	 * */
	public ModelAndView detailConclusionNew(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session = request.getSession(false);
			String forwardURL = "/WEB-INF/jsp/clm/manage/Conclusion_d_view_new.jsp";
			List resultList = null;
			String user_role	= "";
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ConclusionForm form = new ConclusionForm();
			ConclusionVO vo = new ConclusionVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			if("modify".equals(form.getPage_gbn())) {
				forwardURL = "/WEB-INF/jsp/clm/manage/Conclusion_d_new.jsp";
			}
			
			if("".equals(vo.getCntrt_id())){
				vo.setCntrt_id(null);
			}
			
			ModelAndView mav = new ModelAndView();
			
			List tmpSessionRoleList = (List)session.getAttribute("secfw.session.role") ;
			
			for (int i = 0; i < tmpSessionRoleList.size(); i++) {
				Map tmpSessionRoleMap = (Map) tmpSessionRoleList.get(i);
				if (tmpSessionRoleMap.containsValue("RD01")) {
					user_role = "RD01";
				}
			}
			form.setUser_role(user_role);
			form.setUser_orgnz(vo.getSession_blngt_orgnz());
			
			/***************1. 기본정보 *******************/
			List dcList = conclusionService.detailOnlyConclusion(vo); //계약등록상태의 계약건만 조회되도록 수정 2011.12.20
			ListOrderedMap lomReq = (ListOrderedMap)dcList.get(0);
			ArrayList lomMaster = (ArrayList)dcList;			
		
			/***************2. 계약마스터 정보(detailConclusionContractMaster() 에 있던 로직) ***************/
			vo.setCntrt_id((String)lomReq.get("cntrt_id")) ;
			List contractList = conclusionService.detailConclusionContractMasterNew(vo);
			
			ConsultationVO consultationVo = new ConsultationVO() ;
			consultationVo.setCnsdreq_id(vo.getCnsdreq_id()) ;
			consultationVo.setCntrt_id(vo.getCntrt_id());
			consultationVo.setSession_user_locale((String)vo.getSession_user_locale());
			List authReqList = considerationService.listContractAuthreq(consultationVo);//권한요청자 -관련자
			
			StringBuffer reqAuthInfo		= new StringBuffer();
			StringBuffer reqAuthFormInfo	= new StringBuffer();
			StringBuffer reqAuthSvcInfo		= new StringBuffer();
			
			StringBuffer reqAuthSeqno		= new StringBuffer();
			StringBuffer reqAuthTrgrtman_id	= new StringBuffer();
			StringBuffer reqAuthTrgrtman_nm	= new StringBuffer();
			StringBuffer reqAuthTrgrtman_jikgup	= new StringBuffer();
			StringBuffer reqAuthTrgrtman_dept	= new StringBuffer();
			
			if(authReqList != null && authReqList.size() > 0) {
				for(int i=0; i < authReqList.size(); i++) {
					ListOrderedMap authLom	= (ListOrderedMap)authReqList.get(i);
					if(i > 0){
						reqAuthInfo.append(", " + StringUtil.bvl((String)authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_dept_nm"), "")); //display용
						reqAuthSvcInfo.append("!@#$" + authLom.get("demnd_seqno") + "|" + authLom.get("trgtman_id") + "|" + authLom.get("trgtman_nm") + "|" + authLom.get("trgtman_jikgup_nm") + "|" + authLom.get("trgtman_dept_nm")); //팝업과통신용
						reqAuthSeqno.append("," + authLom.get("demnd_seqno"));
						reqAuthTrgrtman_id.append("," + authLom.get("trgtman_id"));
						reqAuthTrgrtman_nm.append("," + authLom.get("trgtman_nm"));
						reqAuthTrgrtman_jikgup.append("," + authLom.get("trgtman_jikgup_nm"));
						reqAuthTrgrtman_dept.append("," + authLom.get("trgtman_dept_nm"));
					} else {
						reqAuthInfo.append(StringUtil.bvl((String)authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_dept_nm"), ""));
						reqAuthSvcInfo.append(authLom.get("demnd_seqno") + "|" + authLom.get("trgtman_id") + "|" + authLom.get("trgtman_nm") + "|" + authLom.get("trgtman_jikgup_nm") + "|" + authLom.get("trgtman_dept_nm")); //팝업과통신용
						reqAuthSeqno.append(authLom.get("demnd_seqno"));
						reqAuthTrgrtman_id.append(authLom.get("trgtman_id"));
						reqAuthTrgrtman_nm.append(authLom.get("trgtman_nm"));
						reqAuthTrgrtman_jikgup.append(authLom.get("trgtman_jikgup_nm"));
						reqAuthTrgrtman_dept.append(authLom.get("trgtman_dept_nm"));
					}
				}
				reqAuthFormInfo.append(reqAuthSeqno.toString() + "!@#$" + reqAuthTrgrtman_id.toString() + "!@#$" + reqAuthTrgrtman_nm.toString() +"!@#$" + reqAuthTrgrtman_jikgup.toString() + "!@#$" + reqAuthTrgrtman_dept.toString());
			}
			
			ArrayList contractArrayList = (ArrayList)contractList;			
			ListOrderedMap contractLom	= (ListOrderedMap)contractList.get(0);
			
			contractLom.put("oblgt_lmt", StringUtil.convertEnterToBR(StringUtil.bvl((String)contractLom.get("oblgt_lmt"),"")));				
			//Special Condition  spcl_cond
			contractLom.put("spcl_cond", StringUtil.convertEnterToBR(StringUtil.bvl((String)contractLom.get("spcl_cond"),"")));			
			//loac_etc 준거법 상세
			contractLom.put("loac_etc", StringUtil.convertEnterToBR(StringUtil.bvl((String)contractLom.get("loac_etc"),"")));
			//dspt_resolt_mthd_det 분쟁해결방법상세 
			contractLom.put("dspt_resolt_mthd_det", StringUtil.convertEnterToBR(StringUtil.bvl((String)contractLom.get("dspt_resolt_mthd_det"),"")));
			
			/***************3. 특화 정보 *************/
			ConsultationSpecialVO specialVO	= new ConsultationSpecialVO();
			specialVO.setCntrt_id(vo.getCntrt_id()) ;
			specialVO.setSession_user_locale((String)vo.getSession_user_locale());
			
			resultList = consultationService.listConsultationSpecial(specialVO); 
			ArrayList special1List = new ArrayList();
			ArrayList special2List = new ArrayList();
			 
			if(resultList.size() >0 && resultList != null) {
				for(int i=0; i < resultList.size(); i++) {
					ListOrderedMap lom = (ListOrderedMap)resultList.get(i);
					if("C04002".equals(lom.get("crtn_depth"))) {
						special1List.add(resultList.get(i));
					} else if("C04005".equals(lom.get("crtn_depth"))) {
						special2List.add(resultList.get(i));
					}
				}
			}
			
			/*********** 4. 연관계약정보(listConsultationRelation() 메소드 로직)  ****************/
			ArrayList relationList = new ArrayList();
			resultList = consultationService.listConsultationRealtion(consultationVo);
			
			int relationListSize = 0;
			if(resultList.size() >0 && resultList != null) {
				for(int i=0; i < resultList.size(); i++) {
					ListOrderedMap lom = (ListOrderedMap)resultList.get(i);
					relationList.add(resultList.get(i));
					 
					if( relationListSize < Integer.parseInt(String.valueOf(lom.get("relation_cnt")))){
						relationListSize = Integer.parseInt(String.valueOf(lom.get("relation_cnt")));
					}
				}
			}
			
			/*********** 5. 이행정보(listConsultationExe() 메소드 로직)  ****************/
			
			ExecutionVO executionVo = new ExecutionVO() ;
			executionVo.setCntrt_id(vo.getCntrt_id()) ;
			executionVo.setSys_cd(vo.getSys_cd()) ;
			executionVo.setSession_user_locale((String)vo.getSession_user_locale());
			resultList = executionService.listExecution(executionVo);
			
			String payment_gbn = "" ;
			int executionListSize = 0 ;
			int execCnt1 = 0 ;
			int execCnt2 = 0 ;
			int execCnt3 = 0 ;
			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap tmp = (ListOrderedMap)resultList.get(0);

				//지불_구분 C02001:지불/수금, C02002:지불, C02003:수금, C02004:해당없음
				payment_gbn = (String)tmp.get("payment_gbn");
				
				executionListSize = ((Integer)tmp.get("total_cnt"));
				
				for(int i=0; i<resultList.size(); i++) {
					tmp = (ListOrderedMap)resultList.get(i);
					
					// 지불계획
					if("C05501".equals(tmp.get("exec_gbn"))){
						execCnt1 ++ ;
					}
					// 수금계획
					else if("C05502".equals(tmp.get("exec_gbn"))){
						execCnt2 ++ ;
					}
					// 기타이행계획
					else if("C05503".equals(tmp.get("exec_gbn"))){
						execCnt3 ++ ;
					}
				}
			}
			
			ArrayList executionLom = (ArrayList)resultList;
			
			/**
			 * 구주 법무시스템의 경우 로그인하 사람의 권한이 Legal Admin(RA01)일 경우 
			 * 계약의 모든 권한을 가지게 된다.
			 */
			
			ArrayList userRoleList = (ArrayList) session.getAttribute("secfw.session.roleList");  // session에서 ROLE에 대한 값을 가지고 온다.
			
			if(userRoleList.contains("RA01") || userRoleList.contains("RD01") || userRoleList.contains("RM00")) {
				form.setTop_role("RA01");
			} else if(userRoleList.contains("RA02") || userRoleList.contains("RA03")) {
				form.setTop_role("RA02");
			} else {
				form.setTop_role("ETC");
			}				
			
			/***************1. 기본정보 세팅 **************/
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +forwardURL);
			
			mav.addObject("lomReq", lomReq);
			mav.addObject("lomMaster", lomMaster);
			mav.addObject("dcList", dcList);
			mav.addObject("command", form);
			mav.addObject("ssems_oppnt_cd",StringUtil.bvl(request.getParameter("ssems_oppnt_cd"),""));//ssems인터페이스시 사용
			mav.addObject("cntrt_no",StringUtil.bvl(request.getParameter("cntrt_no"),""));//ssems인터페이스시 사용
			
			/***************2. 계약마스터 정보 세팅 **************/
			if(reqAuthInfo != null && !"".equals(reqAuthInfo.toString())) {
				mav.addObject("reqAuthInfo", reqAuthInfo.toString());
				mav.addObject("reqAuthFormInfo", reqAuthFormInfo.toString());
				mav.addObject("reqAuthSvcInfo", reqAuthSvcInfo.toString());
			} else {
				mav.addObject("reqAuthInfo", "");
				mav.addObject("reqAuthFormInfo", "");
				mav.addObject("reqAuthSvcInfo", "");
			}
			mav.addObject("authReqList", authReqList);
			mav.addObject("contractArrayList", contractArrayList);
			mav.addObject("contractLom", contractLom);
			
			/***************3. 특화정보 세팅 **************/
			mav.addObject("considerationSpecialList", special1List);
			mav.addObject("consultationSpecialList" , special2List);
			mav.addObject("considerationSpecialSize", special1List.size());
			mav.addObject("consultationSpecialSize" , special2List.size());
			
			/*********** 4. 연관계약정보 세팅 ****************/
			mav.addObject("relationList", relationList);
			mav.addObject("relationListSize", relationListSize);
			
			/*********** 5. 이행정보 세팅 ****************/
			mav.addObject("executionList", resultList);
			mav.addObject("executionLom", executionLom);
			mav.addObject("payment_gbn", payment_gbn);
			mav.addObject("executionListSize", executionListSize);
			mav.addObject("execCnt1", execCnt1);
			mav.addObject("execCnt2", execCnt2);
			mav.addObject("execCnt3", execCnt3);
			
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
	 * 2014-09-09 Kevin created.
	 * Select contract master data to be binded on Basic & Contract Information module showing on contract detail page of Registration step above.
	 * @param req
	 * @param res
	 */
	public void getBasicAndContractInfo(HttpServletRequest req, HttpServletResponse res){
		JSONObject json = new JSONObject();
		PrintWriter writer = null;
		
		try
		{
			res.setContentType("application/json; charset=utf-8");
			writer = res.getWriter();
			
			String cnsdreq_id = req.getParameter("cnsdreq_id");
			String cntrt_id = req.getParameter("cntrt_id");
			
			ConclusionVO vo1 = new ConclusionVO();
			vo1.setCntrt_id(cntrt_id);
			vo1.setCnsdreq_id(cnsdreq_id);
			ConsultationVO vo2 = new ConsultationVO();
			vo2.setCntrt_id(cntrt_id);
			
			// get contact information
			List list1 = conclusionService.detailConclusionContractMasterNew(vo1);
			// get related contract list
			List list2 = consultationService.listConsultationRealtion2(vo2);
			json.put("result", true);
			json.put("contractInfo", list1);
			json.put("relatedContracts", list2);
		}catch(Exception ex){
			json.put("result", false);
		}finally{
			writer.print(json);
			writer.flush();
			writer.close();
		}
	}
	
	
	/**
	 * 체결본 등록정보 등록
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView modifyConclusionNew(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {			
			HttpSession session = request.getSession(false);

			String forwardURL = "/clm/manage/conclusion.do?method=detailConclusionNew";

			ConclusionForm form = new ConclusionForm();
			ConclusionVO vo = new ConclusionVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//팝업으로 지연사유 관리 안함으로 인한 추가2011.11.16
			ConclusionDelayForm delayForm	= new ConclusionDelayForm();
			ConclusionDelayVO	delayVo		= new ConclusionDelayVO();
			
			bind(request, delayForm);
			bind(request, delayVo);
			
			COMUtil.getUserAuditInfo(request, delayForm);
			COMUtil.getUserAuditInfo(request, delayVo);
			
			HashMap hm = new HashMap();
			hm.put("sys_cd", "LAS");
			hm.put("user_id", form.getSession_user_id());
			hm.put("cnsdreq_id", vo.getCnsdreq_id());
			hm.put("cntrt_id", vo.getCntrt_id());
			hm.put("rc_flag", "C");
			hm.put("event_but", "");
			hm.put("menu_id", form.getMenu_id());
			hm.put("reqman_info", vo.getSession_user_nm()+"/"+vo.getSession_jikgup_nm()+"/"+vo.getSession_dept_nm()); //Sungwoo Added 2014-06-24

			ModelAndView mav = new ModelAndView();
			
			vo.setIp_address((String)session.getAttribute("secfw.session.loginIP"));
			
			//Sungwoo 추가 2014-04-07 파일 업로드 여부 체크후 결과 리턴
			int result = conclusionService.modifyConclusion(vo);
			String returnMessage = "";
			
			this.getLogger().debug("=====vo.getWork_type()=== >>> : " + vo.getWork_type() );
			//Sungwoo 변경 2014-05-13 temp일경우 처리
			if(! "temp".equals(vo.getWork_type())){
				//Sungwoo 변경 2014-05-13 result 첨부파일이 0일경우에만 동작
				if (result==0){ 
					returnMessage = messageSource.getMessage("secfw.msg.fail.CopyofConcludedContract", null, new RequestContext(request).getLocale());
					
					//파일 없는경우
					PrintWriter out = response.getWriter();
					response.setContentType("text/html;charset=UTF-8");
					out.println("<script type='text/javascript'>");
					out.println("alert('" + returnMessage + "');");
					out.println("history.back();");
					out.println("</script>");
					out.flush();
					response.flushBuffer();
					out.close();
				}
				
				// Sungwoo 2014-06-17 added email 발송 로직 추가
//				String localeStr = StringUtil.bvl((session.getValue("secfw.session.locale").toString()).trim(),propertyService.getProperty("secfw.defaultLocale"));
				Locale locale = new RequestContext(request).getLocale();
				
//				hm.put("main_title"		, (String)messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, locale));
//				hm.put("mail_content"	, (String)messageSource.getMessage("clm.page.field.conclusion.getHardCopyComment", null, locale));
				hm.put("email.subject"	, (String)messageSource.getMessage("selms.email.contract.hardCopy.subject", null, locale));
				hm.put("main_title"		, (String)messageSource.getMessage("selms.email.contract.hardCopy.contents.title", null, locale));
				hm.put("mail_content"	, (String)messageSource.getMessage("selms.email.contract.hardCopy.contents.body", null, locale));
				
				 
				hm.put("locale"	, locale);
				mailSendService.sendAcceptInfoMail(hm);
				returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
								
				mav.addObject("command", form);
				mav.addObject("returnMessage", returnMessage);
			}	
			mav.setViewName(forwardURL);
			this.getLogger().debug("Info forwardURL: " +forwardURL);
			
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
	 * 체결본사본 수정
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView modifyCoclusionCopy(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "/clm/manage/conclusion.do?method=detailConclusionNew";

			ConclusionForm form = new ConclusionForm();
			ConclusionVO vo = new ConclusionVO();
			ModelAndView mav = new ModelAndView();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			conclusionService.modifyConclusionCopy(vo);

			String returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());
			
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +forwardURL);
			
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