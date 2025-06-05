package com.sec.clm.manage.control;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.singleIF.service.EsbApprovalService;
import com.sds.secframework.singleIF.service.EsbOrgService;
import com.sds.secframework.util.service.ComUtilService;
import com.sec.clm.manage.dto.ChooseContractForm;
import com.sec.clm.manage.dto.ChooseContractVO;
import com.sec.clm.manage.dto.ConsultationExecForm;
import com.sec.clm.manage.dto.ConsultationExecVO;
import com.sec.clm.manage.dto.ConsultationForm;
import com.sec.clm.manage.dto.ConsultationSpecialForm;
import com.sec.clm.manage.dto.ConsultationSpecialVO;
import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.manage.dto.ExecutionForm;
import com.sec.clm.manage.dto.ExecutionVO;
import com.sec.clm.manage.service.ConsultationService;
import com.sec.clm.manage.service.ConsiderationService;
import com.sec.clm.manage.service.ExecutionService;
import com.sec.common.util.ClmsDataUtil;
import com.sec.common.clmscom.service.CLMSCommonService;
import com.sec.common.clmsfile.service.ClmsFileService;

/**
 * 계약관리>My Contract>체결품의 
 * @author HP
 *
 */
public class ConsultationController extends CommonController {
	private ManageController manageControl;
	
	public void setManageControl(ManageController manageControl) {
		this.manageControl = manageControl;
	}
	
	//계약의뢰서비스
	private ConsiderationService considerationService;
	
	public void setConsiderationService(ConsiderationService considerationService) {
		this.considerationService = considerationService;
	}
	
	private ConsultationService consultationService;
	
	public void setConsultationService(ConsultationService consultationService) {
		this.consultationService = consultationService;
	}
	
	private ExecutionService executionService;
	
	public void setExecutionService(ExecutionService executionService) {
		this.executionService = executionService;
	}
	
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private EsbApprovalService esbApprovalService;
	public void setEsbApprovalService(EsbApprovalService esbApprovalService) {
		this.esbApprovalService = esbApprovalService;
	}
	
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private EsbOrgService esbOrgService;
	public void setEsbOrgService(EsbOrgService esbOrgService) {
		this.esbOrgService = esbOrgService;
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
	
	private CLMSCommonService clmsComService;
	public void setClmsComService(CLMSCommonService clmsComService)
	{
		this.clmsComService = clmsComService;
	}
	
	/**
	 * 첨부파일 
	 */
	private ClmsFileService clmsFileService;
	public void setClmsFileService(ClmsFileService clmsFileService) {
		this.clmsFileService = clmsFileService;
	}
	
	/**
	* 계약체결 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listManageConsultation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		request.setAttribute("status_mode", "C02502");
		mav = manageControl.listManage(request, response);
		
		return mav; 
	}
	
	/**
	 * 체결품의계약정보 조회
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView listConsultationContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			List resultList 			= null;
			ListOrderedMap masterLom 	= null;
			String forwardURL 			= "/WEB-INF/jsp/clm/manage/Consultation_d.jsp";

			ConsultationForm form 		= new ConsultationForm();
			ConsultationVO vo 			= new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			ModelAndView mav = new ModelAndView();
				
			String returnMessage = "";
			resultList = consultationService.listConsultation(vo);
		
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
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
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
	 * 체결품의에서 회신상태로 프로세스 복원
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView cancelConsultation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "/clm/manage/consultation.do?method=detailConsultation";
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			ModelAndView mav = new ModelAndView();
			int result = consultationService.cancelConsultation(vo);
			
			forwardURL = "/clm/manage/consideration.do?method=listManageConsideration";
			
			String returnMessage = messageSource.getMessage("secfw.msg.succ.complete", null, new RequestContext(request).getLocale());
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
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
	 * 체결품의정보 등록
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView modifyConsultation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "/clm/manage/consultation.do?method=detailConsultation";

			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			
			ConsultationExecForm execForm = new ConsultationExecForm();
			ConsultationExecVO execVo = new ConsultationExecVO();
			
			bind(request, form);
			bind(request, vo);
			
			bind(request, execForm);
			bind(request, execVo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			COMUtil.getUserAuditInfo(request, execForm);
			COMUtil.getUserAuditInfo(request, execVo);
			
			ModelAndView mav = new ModelAndView();
			
			int result = consultationService.modifyConsultation(vo, execVo);
			
			if("APPROVAL".equals(form.getWork_flag())){
				forwardURL = "/clm/manage/chooseContract.do?method=forwardChooseContractPopup";
			}else if("LIST".equals(form.getWork_flag())){
				forwardURL = "/clm/manage/consultation.do?method=listManageConsultation";
			}else{
				forwardURL = "/clm/manage/consultation.do?method=detailConsultation";
			}
			
			String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("command", form);
			mav.addObject("execCommand", execForm);
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
	 * 체결품의정보 등록(탭간이동 시 임시저장용도)
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public void modifyConsultationByTab(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			
			ConsultationExecForm execForm = new ConsultationExecForm();
			ConsultationExecVO execVo = new ConsultationExecVO();
			
			bind(request, form);
			bind(request, vo);
			
			bind(request, execForm);
			bind(request, execVo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			COMUtil.getUserAuditInfo(request, execForm);
			COMUtil.getUserAuditInfo(request, execVo);
			
			int result = consultationService.modifyConsultation(vo, execVo);
			
			JSONObject jo = new JSONObject();
			jo.put("tabSaveYN", "Y");    		
			
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
			
		}catch (Exception e) {
			JSONObject jo 		= new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out 	= response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		}catch (Throwable t) {
			JSONObject jo 		= new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out 	= response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		}
	}	
	
	/**
	 * 계약마스터정보조회
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView detailConsultationContractMaster(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "/WEB-INF/jsp/clm/manage/Consultation_d_master.jsp";
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ConsultationForm contractForm = new ConsultationForm();
			ConsultationVO contractVo = new ConsultationVO();	
			
			bind(request, contractForm);
			bind(request, contractVo);
			
			COMUtil.getUserAuditInfo(request, contractForm);
			COMUtil.getUserAuditInfo(request, contractVo);

			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			List contractList = consultationService.detailConsultationContractMaster(contractVo);
			ArrayList contractArrayList = (ArrayList)contractList;			
			ListOrderedMap contractLom	= (ListOrderedMap)contractList.get(0);
			
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("command", contractForm);
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
	
	public ModelAndView detailConsultation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL 			= "/WEB-INF/jsp/clm/manage/Consultation_d_view.jsp";
			List authReqList 			= null;//권한요청자 -관련자
			String user_role			= "";
			String[] authInfo			= this.getUserAuth(request);
			user_role				    = authInfo[0];
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
 	
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			if("modify".equals(form.getPage_gbn())) {
				forwardURL 					= "/WEB-INF/jsp/clm/manage/Consultation_d.jsp";
			}
			
			ModelAndView mav = new ModelAndView();
					
			form.setUser_role(user_role);
			form.setUser_orgnz(vo.getSession_blngt_orgnz());
			
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			ClmsDataUtil.debug("detailConsideration   vo.getCnsdreq_id() >>>  " + vo.getCnsdreq_id());			
			int cntrt_cnt = 0;
			List dcList = consultationService.detailOnlyConsultation(vo); //계약체결상태의 계약건만 조회되도록 수정
		
			cntrt_cnt = dcList.size();
			ListOrderedMap lomReq= (ListOrderedMap)dcList.get(0);			
			//master_cnt, master_id
			vo.setCntrt_id((String)lomReq.get("cntrt_id"));
			authReqList = considerationService.listContractAuthreq(vo);//권한요청자 -관련자
			
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
			
			ArrayList lomMaster = (ArrayList)dcList;			
			
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("lomReq", lomReq);
			mav.addObject("lomMaster", lomMaster);
			mav.addObject("contractCount", cntrt_cnt);
			mav.addObject("dcList", dcList);
			if(reqAuthInfo != null && !"".equals(reqAuthInfo.toString())) {
				mav.addObject("reqAuthInfo", reqAuthInfo.toString());
				mav.addObject("reqAuthFormInfo", reqAuthFormInfo.toString());
				mav.addObject("reqAuthSvcInfo", reqAuthSvcInfo.toString());
			} else {
				mav.addObject("reqAuthInfo", "");
				mav.addObject("reqAuthFormInfo", "");
				mav.addObject("reqAuthSvcInfo", "");
			}
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
	 * 연관계약정보
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView listConsultationRelation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    				= "";
			String returnMessage 				= "";
			ConsultationForm 		form       		= null;
			ConsultationVO   		vo         		= null;
			List   					resultList 		= null;
			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/manage/Consultation_d_relation.jsp";
		
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/			
			form = new ConsultationForm();
			vo   = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
		
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			ModelAndView mav = new ModelAndView();
			ArrayList relationList = new ArrayList();
			
			/*********************************************************
			 * Service
			**********************************************************/
			 resultList = consultationService.listConsultationRealtion(vo);
			
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
			 
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("command", form);
			mav.addObject("relationList", relationList);
			mav.addObject("relationListSize", relationListSize);
			mav.addObject("returnMessage", returnMessage);
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("listConsultationRelation() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("listConsultationRelation() Throwable !!");
		}
	}
	
	
	/**
	 * 특화정보조회
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */	
	public ModelAndView listConsultationSpecial(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    				= "";
			String returnMessage 				= "";
			ConsultationForm form       		= null;
			ConsultationVO   vo         		= null;
			ConsultationSpecialForm specialForm = null;
			ConsultationSpecialVO   specialVO   = null;
			List   		resultList 				= null;
			
			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/manage/Consultation_d_special.jsp";
	
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/			
			form = new ConsultationForm();
			vo   = new ConsultationVO();
			
			specialForm = new ConsultationSpecialForm();
			specialVO   = new ConsultationSpecialVO();

			bind(request, form);
			bind(request, vo);
			bind(request, specialForm);
			bind(request, specialVO);
		
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			COMUtil.getUserAuditInfo(request, specialForm);
			COMUtil.getUserAuditInfo(request, specialVO);
			
			ModelAndView mav = new ModelAndView();
			
			/*********************************************************
			 * Service
			**********************************************************/
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

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("command", form);
			mav.addObject("specialCommand", specialForm);
			mav.addObject("considerationSpecialList", special1List);
			mav.addObject("consultationSpecialList" , special2List);
			mav.addObject("considerationSpecialSize", special1List.size());
			mav.addObject("consultationSpecialSize" , special2List.size());
			mav.addObject("returnMessage", returnMessage);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("listConsultationSpecial() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("listConsultationSpecial() Throwable !!");
		}
	}
	
	/**
	 * 이행정보 전체조회
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView listConsultationExe(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			String payment_gbn	 = "";
			ConsultationForm consultationForm	= null;
			ExecutionForm form       = null;
			ExecutionVO   vo         = null;
			
			PageUtil 	pageUtil   = null;
			List   		resultList = null;
						
			/*********************************************************
			 * Forward setting
			**********************************************************/
			
			String pageGbn= StringUtil.bvl(request.getParameter("pageGbn"),"");
			
			
			if("registration".equals(pageGbn)){
				forwardURL = "/WEB-INF/jsp/clm/manage/RegConsultation_d_exe.jsp";
			} else {
				forwardURL = "/WEB-INF/jsp/clm/manage/Consultation_d_exe.jsp";
			}
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new ExecutionForm();
			vo = new ExecutionVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			consultationForm 	= new ConsultationForm();
			bind(request, consultationForm);
			COMUtil.getUserAuditInfo(request, consultationForm);
			
			ModelAndView mav = new ModelAndView();
			/*********************************************************
			 * Page  setting
			**********************************************************/
			pageUtil = new PageUtil();
			
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());	//현재 페이지의 첫번째 게시물번호  set
			vo.setEnd_index(pageUtil.getEndIndex());		//현재 페이지의 마지막 게시물번호  set
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = executionService.listExecution(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap tmp = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(((Integer)tmp.get("total_cnt")).intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();
				//지불_구분 C02001:지불/수금, C02002:지불, C02003:수금, C02004:해당없음
				payment_gbn = (String)tmp.get("payment_gbn");
			}
			
			ArrayList lom = (ArrayList)resultList;

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("executionList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("executionLom", lom);
			mav.addObject("executionCommand", form);
			mav.addObject("returnMessage", returnMessage);
			mav.addObject("command", consultationForm);
			mav.addObject("payment_gbn", payment_gbn);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("listExecution() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("listExecution() Throwable !!");
		}
	}
	
	//*결재관련시작
	//결재상신문서 내용을 생성한다.
	public ModelAndView makeApprovalHtml(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "";
			String strUrl = (request.getRequestURL()).toString();
			String strAttachUrl = strUrl.substring(0, strUrl.indexOf("/", 7) + 1) + "clms/common/clmsfile.do?method=doClmsDownload&file_id=";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			ConsultationForm form = new ConsultationForm();
			ConsultationVO   vo   = new ConsultationVO();
		
			ChooseContractForm contractForm = new ChooseContractForm();		
			ChooseContractVO contractVo 	= new ChooseContractVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			bind(request, contractForm);
			bind(request, contractVo);
			
			COMUtil.getUserAuditInfo(request, contractForm);
			COMUtil.getUserAuditInfo(request, contractVo);
			
			forwardURL = "/secfw/esbApproval.do?method=forwardApproval";
			
			List reqList				= null;		//의뢰정보
			ListOrderedMap reqLom		= null;
			StringBuffer sbContent		= null;
			
			/******************************************************
			 * 1. 버튼에 따라서 이행정보의 완료일,비고, 확인여부를 보여주고 숨겨야 된다.
			 * 2. 아래 값(buGubn)을 이용하여 버튼에 따라서 값을 던져줘야 된다. 
			 **************************************************/
			
			String buGubn = "";   // 인쇄 버튼의 구분
			
			buGubn = StringUtil.nvl((String)request.getParameter("buGubn"),"");
			
			vo.setBuGubn(buGubn);
			
			String agreeUserId 		= "";
			String approvalTitle 	= "";
			String last_locale	 	= "";
			reqList = consultationService.detailConsultationApprovalRequest(vo);
			sbContent = new StringBuffer();
			if(reqList != null && reqList.size() > 0) {
				reqLom	= new ListOrderedMap();
				reqLom	= (ListOrderedMap)reqList.get(0);
				agreeUserId = (String)reqLom.get("agree_user_id");
				approvalTitle	= (String)reqLom.get("req_title");
				last_locale	= (String)reqLom.get("last_locale");
				
				makeApprovalHeader(sbContent, strUrl.substring(0, strUrl.indexOf("/", 7)),last_locale);
				makeApprovalReqInfo(sbContent, reqLom, strAttachUrl);

				if(contractVo.getApproval_yn_arr() != null && contractVo.getApproval_yn_arr().length > 0) {
					makeApprovalContractInfo(sbContent, contractVo, vo, strAttachUrl, reqLom);
				}	
				makeApprovalFooter(sbContent);
			}
			
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("form", form);
			mav.addObject("approval_content", sbContent.toString());
			
			Locale locale1 = new Locale(last_locale);
			//[체결품의]
			mav.addObject("approval_title", (String)messageSource.getMessage("clm.page.field.approval.makeApprovalHtml", null, locale1)+approvalTitle );
			mav.addObject("approval_option", "B");	
			mav.addObject("apprvl_clsfcn","C03002");
			mav.addObject("approval_post_process", "postAppContStatus");
			mav.addObject("ref_key",vo.getCnsdreq_id());
			mav.addObject("approval_agree_id",agreeUserId);
			
			return mav; 
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	public ModelAndView forwardPreviewPop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL 		= "/WEB-INF/jsp/clm/manage/Consultation_preview_p.jsp";
			String strUrl = (request.getRequestURL()).toString();
			String strAttachUrl = strUrl.substring(0, strUrl.indexOf("/", 7) + 1) + "clms/common/clmsfile.do?method=doClmsDownload&file_id=";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			ConsultationForm form = new ConsultationForm();
			ConsultationVO   vo   = new ConsultationVO();
		
			ChooseContractForm contractForm = new ChooseContractForm();		
			ChooseContractVO contractVo 	= new ChooseContractVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			bind(request, contractForm);
			bind(request, contractVo);
			
			COMUtil.getUserAuditInfo(request, contractForm);
			COMUtil.getUserAuditInfo(request, contractVo);
			
			List reqList				= null;		//의뢰정보
			ListOrderedMap reqLom		= null;
			StringBuffer sbContent		= null;
			List contractList			= null;
			ListOrderedMap contLom		= null;
			
			/******************************************************
			 * 1. 버튼에 따라서 이행정보의 완료일,비고, 확인여부를 보여주고 숨겨야 된다.
			 * 2. 아래 값(buGubn)을 이용하여 버튼에 따라서 값을 던져줘야 된다. 
			 **************************************************/
			
			String buGubn = "";   // 인쇄 버튼의 구분
			
			buGubn = StringUtil.nvl((String)request.getParameter("buGubn"),"");
			
			vo.setBuGubn(buGubn);
			
			String last_locale		= "";
			
			reqList = consultationService.detailConsultationApprovalRequest(vo);
			
			sbContent = new StringBuffer();
			if(reqList != null && reqList.size() > 0) {
				reqLom	= new ListOrderedMap();
				reqLom	= (ListOrderedMap)reqList.get(0);
				last_locale	= (String)reqLom.get("last_locale");
				
				makeApprovalHeader(sbContent, strUrl.substring(0, strUrl.indexOf("/", 7)), last_locale);
				makeApprovalReqInfo(sbContent, reqLom, strAttachUrl);
				contractList = consultationService.listConsultationApprovalContract(vo);
				if(contractList != null && contractList.size() > 0) {
					String[] approvalYn = new String[contractList.size()];
					String[] cntrtArr   = new String[contractList.size()];
					for(int i=0; i < contractList.size();i++) {
						contLom = (ListOrderedMap)contractList.get(i);
						approvalYn[i] = "Y";
						cntrtArr[i] = (String)contLom.get("cntrt_id");
					}
					contractVo.setApproval_yn_arr(approvalYn);
					contractVo.setCntrt_id_arr(cntrtArr);
				}
				if(contractVo.getApproval_yn_arr() != null && contractVo.getApproval_yn_arr().length > 0) {
					makeApprovalContractInfo(sbContent, contractVo, vo, strAttachUrl, reqLom);
				}	
				makeApprovalFooter(sbContent);
			}
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("command", form);
			mav.addObject("content", sbContent.toString());
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	//결재문서 스타일정보설정
	public void makeApprovalHeader(StringBuffer sb, String url, String last_locale) throws Exception {
		try {
			sb.append("<!DOCTYPE html>\n")
			  .append("<html>\n")
			  .append("<head>\n");
			//  .append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=8; IE=9\" />\n");
			Locale locale1 = new Locale(last_locale);
			sb.append("<title>"+(String)messageSource.getMessage("clm.main.title", null, locale1)+"</title>\n")
			  .append("<link href=\""+url+"/style/las/"+locale1+"/las.css\" type=\"text/css\" rel=\"stylesheet\"></link>\n")
			  .append("<link href=\""+url+"/style/las/"+locale1+"/mail.css\"   type=\"text/css\" rel=\"stylesheet\">")
			  .append("</head>\n")
			  .append("<body>");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	//결재문서 스타일정보설정
	public void makeApprovalFooter(StringBuffer sb) throws Exception {
		try {
			 sb.append("</div>")
			  .append("</div>")
			  .append("</div>")
		      .append("</body>\n")
		     .append("</html>\n");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	//검토의뢰정보셋팅
	public void makeApprovalReqInfo(StringBuffer sb, ListOrderedMap lom, String attachinfo) throws Exception {
		try {
			String last_locale	= (String)lom.get("last_locale");
			Locale locale1 = new Locale(last_locale);
			
			sb.append("<div id=\"m_wrap\">\n")
			  .append("    <div class=\"m_header menu2\">\n")
			  .append("    <h1></h1>\n")
			  .append("    <h2><span class=\"/images/clm/" + last_locale + "/mail/pncacc.gif\"></SPAN></h2>\n")
			  .append("    </div>\n")
			  .append("    <div id=\"m_container\">\n")
			  .append("        <div class=\"contents\">\n");
			 
			//계약품의작성결재를 상신하오니 재가하여 주시기 바랍니다.
			sb.append("        <h3><p>"+(String)messageSource.getMessage("clm.page.field.approval.makeApprovalReqInfo", null, locale1)+"</p></h3>\n");
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	//계약상세정보셋팅
	public void makeApprovalContractInfo(StringBuffer sb, ChooseContractVO contractVo, ConsultationVO vo, String attachinfo, ListOrderedMap rLom) throws Exception {
		try {
			ListOrderedMap lom				= null;
			ListOrderedMap tempLom			= null;
			ConsultationSpecialVO specialVo	= new ConsultationSpecialVO();	 
			ConsultationExecVO	  execVo	= new ConsultationExecVO();
			String last_locale	= (String)rLom.get("last_locale");
			Locale locale1 = new Locale(last_locale);
			
			specialVo.setSession_user_locale(last_locale);
			execVo.setSession_user_locale(last_locale);
			
			int iSize = contractVo.getApproval_yn_arr().length;
			for(int i=0; i < contractVo.getApproval_yn_arr().length; i++) {
				if("Y".equals(contractVo.getApproval_yn_arr()[i])) {
					vo.setCntrt_id(contractVo.getCntrt_id_arr()[i]);
					List detailContract = consultationService.detailConsultationContractMasterApproval(vo);
					specialVo.setCntrt_id(vo.getCntrt_id());
					List listSpecial 	= consultationService.listConsultationSpecial(specialVo);
					ArrayList special1List = new ArrayList();
					ArrayList special2List = new ArrayList();
					if(listSpecial.size() >0 && listSpecial != null) {
						 for(int j=0; j < listSpecial.size(); j++) {
							 ListOrderedMap specialLom = (ListOrderedMap)listSpecial.get(j);
							 if("C04002".equals(specialLom.get("crtn_depth"))) {
								 special1List.add(listSpecial.get(j));
							 } else if("C04005".equals(specialLom.get("crtn_depth"))) {
								 special2List.add(listSpecial.get(j));
							 }
						 }
					 }
					
					/******************************************************
					 * 1. 버튼에 따라서 이행정보의 완료일,비고, 확인여부를 보여주고 숨겨야 된다.
					 * 2. 아래 값(buGubn)을 이용하여 버튼에 따라서 값을 던져줘야 된다. 
					 **************************************************/
					
					String buGubn = "";   // 인쇄 버튼의 구분
					
					buGubn = (String)vo.getBuGubn(); // 버튼의 구분 여부를 체크 합니다.
					
					//이행정보조회
					execVo.setCntrt_id(vo.getCntrt_id());
					List listExec		= consultationService.listConsultationExec(execVo);
					
					ArrayList listContractAttach = new ArrayList();		//의뢰계약서
					ArrayList listEtcAttach		 = new ArrayList();		//의뢰기타
					ArrayList listPreAttach		 = new ArrayList();		//사전검토
					ArrayList listPriceAttach	 = new ArrayList();		//단가내역
					ArrayList listEtc2Attach	 = new ArrayList();		//의뢰별첨
					
					List listAttachInfo = consultationService.listConsultationApprovalAttachInfo(vo);
					if(listAttachInfo != null && listAttachInfo.size() > 0) {
						for(int j=0; j < listAttachInfo.size(); j++) {
							tempLom = (ListOrderedMap)listAttachInfo.get(j);
							if("1".equals((String)tempLom.get("filetype"))) {
								listContractAttach.add(listAttachInfo.get(j));
							} else if("2".equals((String)tempLom.get("filetype"))) {
								listEtcAttach.add(listAttachInfo.get(j));
							} else if("3".equals((String)tempLom.get("filetype"))) {
								listPreAttach.add(listAttachInfo.get(j));
							} else if("4".equals((String)tempLom.get("filetype"))) {
								listPriceAttach.add(listAttachInfo.get(j));
							} else if("5".equals((String)tempLom.get("filetype"))) {
								listEtc2Attach.add(listAttachInfo.get(j));
							}
						}
					}
					
					String titleIndex = "";
					if(iSize > 1) titleIndex = (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo01", null, locale1) +" "+ (i+1)+" "+ " - "; //계약
					ListOrderedMap lomDetail = (ListOrderedMap)detailContract.get(0);
					sb.append("	<div class=\"mt5\"></div>\n")
				      .append("	<div width=\"100%\" class=\"title_basic\">\n")
				      .append("	<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n")
				      .append("	<colgroup>\n")
					  .append("		<col width=\"50%\" />\n")
					  .append("		<col width=\"50%\" />\n")
					  .append("	</colgroup>\n") 
				      .append("	    <tr>\n")
				      //계약기본정보
				      .append("	        <td><h4>" +" "+ titleIndex +" " + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo02", null, locale1) + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h4></td>\n");
if("" != lomDetail.get("cntrt_no") && null != lomDetail.get("cntrt_no")){				      
				    sb.append("		    <td align=\"right\"> ID : "+ lomDetail.get("cntrt_no") +"</td>\n");
}
				    sb.append("	    </tr>\n")
				      .append("	</table>\n")
					  .append("</div>\n")
					  .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
					  .append("	<colgroup>\n")
					  .append("		<col width=\"17%\" />\n")
					  .append("		<col width=\"11%\" />\n")
					  .append("		<col width=\"17%\" />\n")
					  .append("		<col width=\"14%\" />\n")
					  .append("		<col width=\"12%\" />\n")
					  .append("		<col width=\"12%\" />\n")
					  .append("		<col width=\"17%\" />\n")
					  .append("	</colgroup>\n") 
			          .append("    	<tr>\n")
			          //계약명
					  .append("        <th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo03", null, locale1) + "</th>\n")
				      .append("        <td colspan=\"6\"><span class=\"fL\">" + lomDetail.get("cntrt_nm") +"</span></td>\n")
				      .append("    	</tr>\n")
				      .append("    	<tr>\n")
				      //계약상대방
				      .append("        <th>" + (String)messageSource.getMessage("clm.page.msg.common.otherParty", null, locale1) + "</th>\n")
				      .append("        <td colspan=\"2\">" + lomDetail.get("cntrt_oppnt_nm") + "</td>\n")
				      //대표자명
				      .append("        <th>" + (String)messageSource.getMessage("clm.page.field.customer.registerNo", null, locale1) + "</th>\n")
				      .append("        <td>" + lomDetail.get("cntrt_oppnt_rprsntman") + "</td>\n")
				      //업체Type
				      .append("        <th>" + (String)messageSource.getMessage("clm.page.field.customer.contractRequired", null, locale1) + "</th>\n")
				      .append("        <td>" + StringUtil.bvl(lomDetail.get("cntrt_oppnt_type_nm"), "") + "</td>\n")
				      .append("	   	</tr>\n");
				    if(!"".equals(StringUtil.bvl(lomDetail.get("cntrt_oppnt_respman"), ""))){				    
				    sb.append("    	<tr>\n")
				      //상대방담당자	
				      .append("        <th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo07", null, locale1) + "</th>\n")
				      .append("        <td colspan=\"2\">" + StringUtil.bvl(lomDetail.get("cntrt_oppnt_respman"), "") + "</td>\n")
				      //전화번호
				      .append("        <th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo08", null, locale1) + "</th>\n")
				      .append("        <td>" + StringUtil.bvl(lomDetail.get("cntrt_oppnt_telno"), "") + "</td>\n")
				      .append("        <th>E-mail</th>\n")
				      .append("        <td>" + StringUtil.bvl(lomDetail.get("cntrt_oppnt_email"), "") + "</td>\n")
				      .append("	   	</tr>\n");
				    }				      
				    sb.append("	   	<tr class=\"slide-target02 slide-area\">\n")
				    	//계약유형
				      .append("	       <th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo09", null, locale1) + "</th>\n")
				      .append("    	   <td colspan=\"6\">" + lomDetail.get("biz_clsfcn_nm") + " / " + lomDetail.get("depth_clsfcn_nm") + " / " + lomDetail.get("cnclsnpurps_bigclsfcn_nm") + " / " + lomDetail.get("cnclsnpurps_midclsfcn_nm") + "</td>\n")
				      .append("	   	</tr>\n")
				      .append("    	<tr>\n")
				      //계약대상
				      .append("        <th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo10", null, locale1) + "</th>\n")
				      .append("        <td colspan=\"2\">" + lomDetail.get("cntrt_trgt_nm") + "</td>\n")
				      //계약대상상세
				      .append("        <th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo11", null, locale1) + "</th>\n")
				      .append("        <td colspan=\"3\">" + lomDetail.get("cntrt_trgt_det")+ "</td>\n")
					  .append("		</tr>\n");
				    if(!"".equals(StringUtil.bvl(lomDetail.get("pshdbkgrnd_purps"), ""))){
					sb.append("		<tr>\n")
					  //추진목적 및 배경
					  .append("			<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo12", null, locale1) + "</th>\n")
					  .append("			<td colspan=\"6\">" + StringUtil.bvl((String)lomDetail.get("pshdbkgrnd_purps"), "") + "</td>\n")
					  .append("		</tr>\n");
				    }
				    if(!"".equals(StringUtil.bvl(lomDetail.get("antcptnefct"), ""))){
					sb.append("		<tr>\n")
					  //기대효과	
					  .append("			<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo13", null, locale1) + "</th>\n")
					  .append("			<td colspan=\"6\">" + StringUtil.convertEnterToBR(StringUtil.bvl((String)lomDetail.get("antcptnefct"),"")) + "</td>\n")
					  .append("  	</tr>\n");
				    }
					sb.append("		<tr>\n")
					  //계약기간	
					  .append(" 	    <th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo14", null, locale1) + "</th>\n")
					  .append("	        <td colspan=\"6\">" + lomDetail.get("cntrtperiod_startday") + " ~ " + lomDetail.get("cntrtperiod_endday") +"</td>\n")
					  .append("		</tr>\n")
					  .append("     <tr>\n")
					  //지불/수금 구분
					  .append("			<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo15", null, locale1) + "</th>\n");
					if("C02004".equals((String)lomDetail.get("payment_gbn"))) {
						  sb.append("   <td colspan=\"6\">" + lomDetail.get("payment_gbn_nm") + "</td>\n");
					} else {	  
						  sb.append("   <td colspan=\"2\">" + lomDetail.get("payment_gbn_nm") + "</td>\n")
						    //계약금액
						  	.append("   <th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo16", null, locale1) + "</th>\n")
					        .append("  	<td align=\"right\" class=\"tR\">" + StringUtil.bvl(lomDetail.get("cntrt_amt"), "0")+ "</td>\n")
					        //통화(단위)
					        .append("	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo17", null, locale1) + "</th>\n")
					        .append("	<td>" + lomDetail.get("crrncy_unit") + "</td>\n");
					}      
					sb.append("		</tr>\n");
					if(!"".equals(StringUtil.bvl(lomDetail.get("cntrt_untprc_expl"), ""))){
					sb.append("     <tr>\n")
					  //단가내역	
					  .append("         <th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo18", null, locale1) + "</th>\n")
					  .append("         <td colspan=\"6\">" + StringUtil.bvl(lomDetail.get("cntrt_untprc_expl"), "") + "</td>\n")
					  .append("     </tr>\n");
					} 
					if(!"".equals(StringUtil.bvl(lomDetail.get("payment_cond"), ""))){
					sb.append("     <tr>\n")
					  //지불/수금 조건
					  .append("	    	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo19", null, locale1) + "</th>\n")
					  .append("	        <td colspan=\"6\">" + StringUtil.convertEnterToBR(StringUtil.bvl(lomDetail.get("payment_cond"), "")) + "</td>\n")
					  .append("     </tr>\n");
					}
					
					if(!"".equals(StringUtil.bvl(lomDetail.get("etc_main_cont"), "")) || !"".equals(StringUtil.bvl(lomDetail.get("if_sys_cd"), ""))) {
					  sb.append("     <tr>\n")
					    //기타 주요사항
					    .append("	    	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo20", null, locale1) + "</th>\n");
						if(!"".equals((String)lomDetail.get("if_sys_cd"))) {
							sb.append("           <td colspan=\"6\" valign=\"top\">" + StringUtil.convertEnterToBR(StringUtil.bvl(lomDetail.get("etc_main_cont"), "")) + " [" + lomDetail.get("if_sys_cd") + "]</td>\n");
						}else{
							sb.append("           <td colspan=\"6\" valign=\"top\">" + StringUtil.convertEnterToBR(StringUtil.bvl(lomDetail.get("etc_main_cont"), "")) + "</td>\n");
						}
					  sb.append("     </tr>\n");						
					}
					
					sb.append("	</table>\n")
					  .append("	<div class=\"mt5\"></div>\n")
					  //계약상세정보
				      .append("	<div class=\"title_basic\"><h4>"+" "+ titleIndex +" " + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo21", null, locale1) + "</h4></div>\n")
				      .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
				      .append("	<colgroup>\n")
				      .append("		<col width=\"20%\" />\n")
				      .append("		<col width=\"80%\" />\n")
				      .append("	</colgroup>\n")
					//비밀유지기간
				      .append("     <tr>\n")
					  .append("         <th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo22", null, locale1) + "</th>\n")
					  .append("	        <td> " + StringUtil.bvl(lomDetail.get("secret_keepperiod"), "") + "</td>\n")
					  .append("     </tr>\n");
					//특화조건시작
					if(special1List != null && special1List.size() > 0) {
						if(special1List.size() > 0 ) {
							for(int k=0; k < special1List.size(); k++) {
								ListOrderedMap s1Lom = (ListOrderedMap)special1List.get(k);
								if(!"".equals(StringUtil.bvl(s1Lom.get("attr_value"), ""))){
									sb.append("	<tr>\n")	
									  .append("		<th>" +s1Lom.get("attr_nm") + "</th>\n")
									  .append("		<td>" +  s1Lom.get("attr_value") + "</td>\n")
									  .append("	</tr>\n");
								}
							}	
						}
					}
					
					if(special2List != null && special2List.size() > 0) {
						if(special2List.size() > 0 ) {
							
							for(int k=0; k < special2List.size(); k++) {
								ListOrderedMap s2Lom = (ListOrderedMap)special2List.get(k);
								if(!"".equals(StringUtil.bvl(s2Lom.get("attr_value"), ""))){
								sb.append("<tr>")
								  .append("		<th>" + s2Lom.get("attr_nm") + "</th>\n")
								  .append("		<td>" +  StringUtil.bvl(s2Lom.get("attr_value"), "") + "</td>\n")
								  .append("</tr>\n");
								}
							}
							
						}
					}
					//특화정보끝
					if(!"".equals(StringUtil.bvl(lomDetail.get("auto_rnew_yn"), ""))){
					sb.append("<tr>\n")
					  //자동연장여부
					  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo23", null, locale1) + "</th>\n");
						if("Y".equals((String)lomDetail.get("auto_rnew_yn"))) {
						sb.append("	<td>YES</td>\n");
						}else{
						sb.append("	<td>NO</td>\n");
						}
					sb.append("</tr>\n");
					}
					if(!"".equals(StringUtil.bvl(lomDetail.get("oblgt_lmt"), ""))){
					sb.append("<tr>\n")
					  //배상책임한도
					  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo24", null, locale1) + "</th>\n")
					  .append("		<td>" + StringUtil.bvl(lomDetail.get("oblgt_lmt"), "") + "</td>\n")
					  .append("	</tr>\n");
					}
					if(!"".equals(StringUtil.bvl(lomDetail.get("spcl_cond"), ""))){				  
					sb.append("	<tr>\n")
					  //기타 특약사항
					  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo25", null, locale1) + "</th>\n")
					  .append("		<td>" + StringUtil.bvl(lomDetail.get("spcl_cond"), "") + "</td>\n")
					  .append("	</tr>\n");
					}					
					if(!"".equals(StringUtil.bvl(lomDetail.get("loac"), ""))){
					sb.append("	<tr>\n")
					  //준거법
					  .append("	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo26", null, locale1) + "</th>\n")
					  .append("		<td>" + lomDetail.get("loac") + "</td>\n")
					  .append("	</tr>\n");
					}
					if(!"".equals(StringUtil.bvl(lomDetail.get("loac_etc"), ""))){
					sb.append("	<tr>\n")
					  //준거법상세
					  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo27", null, locale1) + "</th>\n")
					  .append("		<td>" + StringUtil.convertEnterToBR((String)StringUtil.bvl(lomDetail.get("loac_etc"), "")) + "</td>\n")
					  .append("	</tr>\n");
					}
					if(!"".equals(StringUtil.bvl(lomDetail.get("dspt_resolt_mthd"), ""))){
					sb.append("	<tr>\n")
					  //분쟁해결방법
					  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo28", null, locale1) + "</th>\n")
					  .append(" 	<td>" + lomDetail.get("dspt_resolt_mthd") + "</td>\n")
					  .append("	</tr>\n");
					}
					if(!"".equals(StringUtil.bvl(lomDetail.get("dspt_resolt_mthd_det"), ""))){					  
					sb.append("	<tr>\n")
					  //분쟁해결방법상세	
					  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo29", null, locale1) + "</th>\n")
					  .append("		<td>" + StringUtil.convertEnterToBR((String)StringUtil.bvl(lomDetail.get("dspt_resolt_mthd_det"), "")) + "</td>\n")
					  .append("	</tr>\n");
					} 
					sb.append(" <tr>\n")
					  //법무팀검토담당자
					  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo30", null, locale1) + "</th>\n")
					  .append("		<td>" + StringUtil.bvl(lomDetail.get("cnsdman_nm"), "") + 
							  			"/" + StringUtil.bvl(lomDetail.get("cnsd_jikgup_nm"), "")+ 
							  			"/" + StringUtil.bvl(lomDetail.get("cnsd_dept_nm"), "")+ 
					  					"</td>\n")
					  .append("	</tr>\n")
					  .append(" <tr>\n")
					  //최종법무검토의견
					  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo31", null, locale1) + "</th>\n")
					  .append("		<td>" + StringUtil.convertCharsToHtml(StringUtil.bvl((String)lomDetail.get("cnsd_opnn"), "")) + "</td>\n")
					  .append("	</tr>\n")
					  .append("</table>\n");
						int iC05501Size = 0;	//지불계획
						int iC05502Size = 0;	//수금계획
						int iC05503Size = 0;	//기타이행계획
						for(int j=0; j < listExec.size();j++) {
							ListOrderedMap execLom = (ListOrderedMap)listExec.get(j);
							if("C05501".equals(execLom.get("exec_gbn"))) {
								iC05501Size++;
							} else if("C05502".equals(execLom.get("exec_gbn"))) {
								iC05502Size++;
							} else {
								iC05503Size++;
							}
						}
						
					if(iC05501Size != 0 || iC05502Size != 0 || iC05503Size != 0){
						//이행사항시작					
						sb.append("	<div class=\"mt5\"></div>\n")
					      .append("	<div class=\"title_basic\">\n")
					      //주요이행사항
					      .append("	<h4>" + titleIndex + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo32", null, locale1) + "</h4>\n")
					      .append(" </div>");
						if("C02001".equals((String)lomDetail.get("payment_gbn")) || "C02002".equals((String)lomDetail.get("payment_gbn")) || iC05501Size > 0) {	//지불
							sb.append("	<div class=\"mt5\"></div>\n")
						      .append("	<div class=\"title_basic\">\n")
						      //지불계획
						      .append("	<h5 class=\"ntitle05\">" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo33", null, locale1) + " </h5>\n")
						      .append(" </div>")
						      .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style02\">\n");
if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우
							sb.append("	<colgroup>\n")
						      .append(" 	<col width=\"7%\"/>\n")
							  .append("		<col width=\"20%\"/>\n")
							  .append("		<col width=\"12%\"/>\n")
							  .append("		<col width=\"13%\"/>\n")
							  .append("		<col width=\"17%\"/>\n")
							  .append("	</colgroup>\n");
} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
							sb.append("	<colgroup>\n")
						      .append(" 	<col width=\"7%\"/>\n")
							  .append("		<col width=\"13%\"/>\n")
							  .append("		<col width=\"12%\"/>\n")
							  .append("		<col width=\"13%\"/>\n")
							  .append("		<col width=\"17%\"/>\n")
							  .append("		<col width=\"12%\"/>\n")
							  .append("		<col width=\"16%\"/>\n")
							  .append("		<col width=\"10%\"/>\n")
							  .append("	</colgroup>\n");
}
							sb.append("	<thead>\n")
							  .append("	<tr>\n");
if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우(							  
							sb.append("		<th>No</th>\n")
							  //이행항목
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo34", null, locale1) + "</th>\n")
							  //완료예정일
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo35", null, locale1) + "</th>\n")
							  //금액
							  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo36", null, locale1) + "</th>\n")
							  //지불조건
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo37", null, locale1) + "</th>\n");
} else {                 // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
							sb.append("		<th>No</th>\n")
							  //이행항목
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo38", null, locale1) + "</th>\n")
							  //완료예정일
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo39", null, locale1) + "</th>\n")
							  //금액
							  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo36", null, locale1) + "</th>\n")
							  //지불조건
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo37", null, locale1) + "</th>\n")
							  //완료일
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo41", null, locale1) + "</th>\n")
							  //비고
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo42", null, locale1) + "</th>\n")
							  //확인여부
							  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo43", null, locale1) + "</th>\n");
}
							sb.append("	</tr>\n")
							  .append("	</thead>\n")
							  .append("	<tbody>\n");
						    if(iC05501Size > 0) {
						    	int k = 1;
						    	for(int j = 0; j < listExec.size(); j++) {
									ListOrderedMap execLom = (ListOrderedMap)listExec.get(j);
									if("C05501".equals(execLom.get("exec_gbn"))) {
										sb.append("	<tr>\n");
if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우(												
								    	sb.append("		<td align=\"center\">" + k + "</td>\n")
								    	  .append("		<td>" + execLom.get("exec_itm") + "</td>\n")
								    	  .append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n")
										  .append("		<td align=\"right\">" + execLom.get("exec_amt") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cont"), "") + "</td>\n")
								    	  .append("	</tr>\n");
} else {
										sb.append("		<td align=\"center\">" + k + "</td>\n")
										  .append("		<td>" + execLom.get("exec_itm") + "</td>\n")
										  .append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n")
										  .append("		<td align=\"right\">" + execLom.get("exec_amt") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cont"), "") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cmpltday"), "") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("note"), "") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_statusnm"), "") + "</td>\n")
										  .append("	</tr>\n");
}
								 		k++;
									}
								}
						    }
							sb.append("</tbody>\n")
							  .append("</table>\n");
						}
						
						if("C02001".equals((String)lomDetail.get("payment_gbn")) || "C02003".equals((String)lomDetail.get("payment_gbn")) || iC05502Size > 0) {	//수금
							sb.append("	<div class=\"mt5\"></div>\n")
						      .append("	<div class=\"title_basic\">\n")
						      //수금계획
						      .append("	<h5 class=\"ntitle05\">" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo44", null, locale1) + "</h5>\n")
						      .append(" </div>")
							  .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style02\">\n")
							  .append("	<colgroup>\n");
if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우			  
						    sb.append(" 	<col width=\"7%\"/>\n")
							  .append("		<col width=\"20%\"/>\n")
							  .append("		<col width=\"12%\"/>\n")
							  .append("		<col width=\"13%\"/>\n")
							  .append("		<col width=\"17%\"/>\n");
} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
							sb.append(" 	<col width=\"7%\"/>\n")
							  .append("		<col width=\"13%\"/>\n")
							  .append("		<col width=\"12%\"/>\n")
							  .append("		<col width=\"13%\"/>\n")
							  .append("		<col width=\"17%\"/>\n")
							  .append("		<col width=\"12%\"/>\n")
							  .append("		<col width=\"16%\"/>\n")
							  .append("		<col width=\"10%\"/>\n");
}
							sb.append("	</colgroup>\n")
							  .append("	<thead>\n")
							  .append("	<tr>\n");
if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우							  
							sb.append("		<th>No</th>\n")
							  //이행항목
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo38", null, locale1) + "</th>\n")
							  //완료예정일
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo39", null, locale1) + "</th>\n")
							  //금액
							  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo36", null, locale1) + "</th>\n")
							  //수금조건
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo45", null, locale1) + "</th>\n");
} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
							sb.append("		<th>No</th>\n")
							  //이행항목
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo38", null, locale1) + "</th>\n")
							  //완료예정일
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo39", null, locale1) + "</th>\n")
							  //금액
							  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo36", null, locale1) + "</th>\n")
							  //수금조건
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo45", null, locale1) + "</th>\n")
							  //완료일
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo41", null, locale1) + "</th>\n")
							  //비고
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo42", null, locale1) + "</th>\n")
							  //확인여부
							  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo43", null, locale1) + "</th>\n");
}
							sb.append("	</tr>\n")
							  .append("	</thead>\n")
							  .append("	<tbody>\n");
							if(iC05502Size > 0) {
								int k = 1;
						    	for(int j = 0; j < listExec.size(); j++) {
									ListOrderedMap execLom = (ListOrderedMap)listExec.get(j);
									if("C05502".equals(execLom.get("exec_gbn"))) {
										sb.append("	<tr>\n");
if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우
								    	sb.append("		<td align=\"center\">" + k + "</td>\n")
								    	  .append("		<td>" + execLom.get("exec_itm") + "</td>\n")
								    	  .append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n")
										  .append("		<td align=\"right\">" + execLom.get("exec_amt") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cont"), "") + "</td>\n");
} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
										sb.append("		<td align=\"center\">" + k + "</td>\n")
										  .append("		<td>" + execLom.get("exec_itm") + "</td>\n")
										  .append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n")
										  .append("		<td align=\"right\">" + execLom.get("exec_amt") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cont"), "") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cmpltday"), "") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("note"), "") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_statusnm"), "") + "</td>\n");
}
								    	sb.append("	</tr>\n");
								 		k++;
									}
								}
						    }
							sb.append("</tbody>\n")
							  .append("</table>\n");
						} 
						if(iC05503Size > 0) {
						sb.append("	<div class=\"mt5\"></div>\n")
					      .append("	<div class=\"title_basic\">\n")
					      //기타이행계획
					      .append("	<h5 class=\"ntitle05\">" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo46", null, locale1) + "</h5>\n")
					      .append(" </div>")
						  .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style02\">\n")
						  .append("	<colgroup>\n");
if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우						  
					    sb.append(" 	<col width=\"7%\"/>\n")
						  .append("		<col width=\"20%\"/>\n")
						  .append("		<col width=\"12%\"/>\n")
						  .append("		<col width=\"30%\"/>\n");
} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
						sb.append(" 	<col width=\"7%\"/>\n")
						  .append("		<col width=\"13%\"/>\n")
						  .append("		<col width=\"12%\"/>\n")
						  .append("		<col width=\"30%\"/>\n")
						  .append("		<col width=\"12%\"/>\n")
						  .append("		<col width=\"16%\"/>\n")
						  .append("		<col width=\"10%\"/>\n");
}
						sb.append("	</colgroup>\n")
						  .append("	<thead>\n")
						  .append("	<tr>\n");
if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우						  
						sb.append("		<th>No</th>\n")
						  //이행항목
						  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo38", null, locale1) + "</th>\n")
						  //완료예정일
						  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo39", null, locale1) + "</th>\n")
						  .append(" 	<th>Description</th>\n");
} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
						sb.append("		<th>No</th>\n")
						  //이행항목	
						  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo38", null, locale1) + "</th>\n")
						  //완료예정일
						  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo39", null, locale1) + "</th>\n")
						  .append(" 	<th>Description</th>\n")
						  //완료일
						  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo41", null, locale1) + "</th>\n")
						  //비고
						  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo42", null, locale1) + "</th>\n")
						  //확인여부
						  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo43", null, locale1) + "</th>\n");
}
						sb.append("	</tr>\n")
						  .append("	</thead>\n")
						  .append("	<tbody>\n");
							int k = 1;
					    	for(int j = 0; j < listExec.size(); j++) {
								ListOrderedMap execLom = (ListOrderedMap)listExec.get(j);
								if("C05503".equals(execLom.get("exec_gbn"))) {
									sb.append("	<tr>\n");
if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우	
							    	sb.append("		<td align=\"center\">" + k + "</td>\n")
							    	  .append("		<td>" + execLom.get("exec_itm") + "</td>\n")
							    	  .append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n")
									  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cont"), "") + "</td>\n");
} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
									sb.append("		<td align=\"center\">" + k + "</td>\n")
									  .append("		<td>" + execLom.get("exec_itm") + "</td>\n")
									  .append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n")
									  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cont"), "") + "</td>\n")
									  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cmpltday"), "") + "</td>\n")
									  .append("		<td>" + StringUtil.bvl(execLom.get("note"), "") + "</td>\n")
									  .append("		<td>" + StringUtil.bvl(execLom.get("exec_statusnm"), "") + "</td>\n");
}
								    sb.append("	</tr>\n");							 		
									k++;
								}
							}
					    }
					
					}
					
					sb.append("</tbody>\n")
					  .append("</table>\n")
					  .append("</div>\n");
// 이행사항 종료
				//	}
					sb.append("	<div class=\"mt5\"></div>\n")
				      .append("	<div class=\"title_basic\">\n")
				      //체결정보
				      .append("	<h4>" + titleIndex + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo47", null, locale1) + "</h4>\n")
				      .append(" </div>")
				      .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
				      .append("	<colgroup>\n")
				      .append("		<col width=\"16%\" />\n")
				      .append("		<col width=\"16%\" />\n")
				      .append("		<col width=\"16%\" />\n")
				      .append("		<col width=\"16%\" />\n")
				      .append("		<col width=\"16%\" />\n")
				      .append("		<col width=\"20%\" />\n")
				      .append("	</colgroup>\n")
				      .append("		<tr>\n")
				      //직인서명구분
				      .append("			<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo48", null, locale1) + "</th>\n");
				    if("C02101".equals((String)lomDetail.get("seal_mthd"))) {
				    	//사용인감날인
				    	sb.append("	<td colspan=\"2\">" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo49", null, locale1) + "</td>\n");
				    }else if("C02103".equals((String)lomDetail.get("seal_mthd"))){
				    	//법인인감날인
				    	sb.append("	<td colspan=\"2\">" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo50", null, locale1) + "</td>\n");
				    } else {
				    	//서명
				    	sb.append("	<td colspan=\"2\">" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo51", null, locale1) + "</td>\n");
				    }
				      sb.append("		</tr>\n");
				      sb.append("   	<tr>\n");
				      
				    if("C02101".equals((String)lomDetail.get("seal_mthd"))) {
				    	//날인담당자
				    	sb.append("<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo53", null, locale1) + "</th>")
				          .append("<td colspan=\"2\">" + StringUtil.bvl((String)lomDetail.get("seal_ffmtman_nm"), "") + "/" + StringUtil.bvl((String)lomDetail.get("seal_ffmtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)lomDetail.get("seal_ffmt_dept_nm"), "") + "</td>\n");
				    } else {
				    	//서명예정자
				    	sb.append("<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo54", null, locale1) + "</th>")
				    	  .append("<td colspan=\"2\">" + StringUtil.bvl((String)lomDetail.get("sign_plndman_nm"), "") + "/" + StringUtil.bvl((String)lomDetail.get("sign_plndman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)lomDetail.get("sign_plnd_dept_nm"), "") + "</td>\n");
				    }
				    //체결예정일
				    sb.append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo55", null, locale1) + "</th>\n")
				      .append("		<td colspan=\"2\">" + lomDetail.get("cnclsn_plndday") + "</td>\n")
				  	  .append("</tr>\n")
				  	  .append("	<tr>\n")
				  	  //계약담당자
				  	  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo56", null, locale1) + "</th>\n")
				  	  .append("		<td colspan=\"5\">" + StringUtil.bvl((String)lomDetail.get("cntrt_respman_nm"), "") + "/" + StringUtil.bvl((String)lomDetail.get("cntrt_respman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)lomDetail.get("cntrt_resp_dept_nm"), "") + "</td>\n")
				  	  .append("	</tr>\n")
				  	  .append("</table>\n");
				    
				    sb.append("	<div class=\"mt5\"></div>\n")
				      .append("	<div class=\"title_basic\">\n")
				      //첨부파일
				      .append("	<h4>" + titleIndex + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo57", null, locale1) + "</h4>\n")
				      .append(" </div>")
				      .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
				      .append("	<colgroup>\n")
				      .append("		<col width=\"14%\" />\n")
				      .append("		<col />\n")
				      .append("	</colgroup>\n")
				      .append(" <tr>\n")
				      //계약서
				      .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo58", null, locale1) + "</th>\n");
				      if(listContractAttach != null && listContractAttach.size() > 0) {
				    	  tempLom = (ListOrderedMap)listContractAttach.get(0);
				    	  sb.append("<td><a href=\"" + attachinfo + (String)tempLom.get("file_id")+ "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				      } else {
						sb.append("");
				      }
				    sb.append("</tr>\n");
				    
				    if(listEtc2Attach != null && listEtc2Attach.size() > 0) {
				    	for(int k=0; k < listEtc2Attach.size(); k++) {
				    		tempLom = (ListOrderedMap)listEtc2Attach.get(k);
				    		sb.append("<tr>\n");
				    		if(k==0) {
				    			if(listEtc2Attach.size()==1) {
				    				//첨부/별첨
				    				sb.append("<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo59", null, locale1) + "</td>\n");
				    			} else {
				    				//첨부/별첨
				    				sb.append("<th rowspan=\"" + listEtc2Attach.size()+ "\">" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo59", null, locale1) + "</td>\n");
				    			}
				    			sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				    		} else {
				    			sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				    		}
				    		sb.append("</tr>\n");
				    	}
				    }
				    
				    if(listPreAttach != null && listPreAttach.size() > 0) {
				    	for(int k=0; k < listPreAttach.size(); k++) {
				    		tempLom = (ListOrderedMap)listPreAttach.get(k);
				    		sb.append("<tr>\n");
				    		if(k==0) {
				    			if(listPreAttach.size()==1) {
				    				//사전승인
				    				sb.append("<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo60", null, locale1) + "</td>\n");
				    			} else {
				    				//사전승인
				    				sb.append("<th rowspan=\"" + listPreAttach.size() + "\">" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo60", null, locale1) + "</td>\n");
				    			}
					    		sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				    		} else {
				    			sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				    		}
				    		sb.append("</tr>\n");
				    	}
				    }
				    
				    if(listEtcAttach != null && listEtcAttach.size() > 0) {
				    	for(int k=0; k < listEtcAttach.size(); k++) {
				    		tempLom = (ListOrderedMap)listEtcAttach.get(k);
				    		sb.append("<tr>\n");
				    		if(k==0) {
				    			if(listEtcAttach.size()==1) {
				    				//기타
				    				sb.append("<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo61", null, locale1) + "</th>\n");
				    			} else {
				    				//기타
				    				sb.append("<th rowspan=\"" + listEtcAttach.size()+ "\">" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo61", null, locale1) + "</th>\n");
				    			}
				    			sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				    		} else {
				    			sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				    		}
				    		sb.append("</tr>\n");
				    	}
				    }
				    
				    if(listPriceAttach != null && listPriceAttach.size() > 0) {
				    	for(int k=0; k < listPriceAttach.size(); k++) {
				    		tempLom = (ListOrderedMap)listPriceAttach.get(k);
				    		sb.append("<tr>\n");
				    		if(k==0) {
				    			if(listPriceAttach.size()==1) {
				    				//단가정보
				    				sb.append("<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo62", null, locale1) + "</th>\n");
				    			} else {
				    				//단가정보
				    				sb.append("<th rowspan=\"" + listPriceAttach.size()+ "\">" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfo62", null, locale1) + "</th>\n");
				    			}
				    			sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				    		} else {
				    			sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				    		}
				    		sb.append("</tr>\n");
				    	}
				    }
				    sb.append("</table>\n");
				}	
			}	
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}	
	}
	
	/**
	 * 품의작성상신 후 상태변경
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView modifyConsultationStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String errLine  = "";
		try {
			String forwardURL = "/clm/manage/consultation.do?method=detailConsultation";
		
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			ModelAndView mav = new ModelAndView();
			
			int result = consultationService.modifyConsultationStatus(vo);
			
			String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
			
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
		
			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error errLine:" + errLine);
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error2 errLine:" + errLine);
		}
	}
	
	/**
	 * 결재관련자 ESB ValidationCheck
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void listConsultationApprovalEsbValidation(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		try {
			//Form 및 VO Binding
			ConsultationForm form 	= new ConsultationForm();
	        ConsultationVO vo 		= new ConsultationVO();
			
	        bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			JSONObject jo = new JSONObject();
    		
			 //상신자 정보 조회
	        Locale lc = new Locale((String)vo.getSession_user_locale() );
	        
	        if(vo.getSession_user_id()!= null && !"".equals(vo.getSession_user_id())) {	//기안자세션이 있음	
	        	//2012-07-23 삼성전자 외 타사임직원(삼성그룹내) 결재 사용 허용여부 확인
	        	HashMap hm = new HashMap();
	        	hm.put("user_id", vo.getSession_user_id());
	        	hm.put("new_flag", "N");
	        	List exceptWsvoList = esbApprovalService.listExceptWsvoList(hm);
	        	if(exceptWsvoList != null && exceptWsvoList.size() > 0){ //결재 ESB조회 예외자가 존재(ESB 조회안함)
	        		jo.put("result", "Y");
	        		jo.put("message", "");
	        	}else{
	        		
		        	Vector drafterUserVector = null;
		        	
	        		// !@# ESB 인터페이스 변경(authCompCd 추가 다시 삭제) 2013.04.25
		        	drafterUserVector = esbOrgService.userSearchByEpid(vo.getSession_user_id(), lc);
		        	if(drafterUserVector == null || drafterUserVector.size()==0) {	//기안자가 ESB에 없음
		        		jo.put("result", "N");
		        		jo.put("message",(String)messageSource.getMessage("clm.page.field.approval.listConsultationApprovalEsbValidation01", null, lc)); //기안자 정보가 ESB에 존재하지 않습니다.
		        	} else {//기안자가 ESB에 있음
		        		jo.put("result", "Y");
		        		jo.put("message", "");
		            	 
		        		
		        	}
	        	}
	        } else {
	        	jo.put("result", "N");
        		jo.put("message", (String)messageSource.getMessage("clm.page.field.approval.listConsultationApprovalEsbValidation02", null, lc));//기안자의 세션값이 존재하지 않습니다.
	        }
	        
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jo = new JSONObject();
    		jo.put("errorMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
    		jo.put("errorMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} 
	}
	
	public String[] getUserAuth(HttpServletRequest request) {
		String[] authString = new String[2];
		HttpSession session = request.getSession(false);
		List tmpSessionRoleList = (List)session.getAttribute("secfw.session.role") ;
		
		authString[0] = "";
		authString[1] = "";
		for (int i = 0; i < tmpSessionRoleList.size(); i++) {
			Map tmpSessionRoleMap = (Map) tmpSessionRoleList.get(i);
			if(tmpSessionRoleMap.containsValue("RC01")) {
				authString[0] = "RC01";
				authString[1] = "CRUD";
				break;
			} else if(tmpSessionRoleMap.containsValue("RD01")) {
				authString[0] = "RD01";
				authString[1] = "CRUD";
				break;
			} else if(tmpSessionRoleMap.containsValue("RD02")) {
				authString[0] = "RD02";
				authString[1] = "CRUD";
				break;
			} else if(tmpSessionRoleMap.containsValue("RA01")) {
				authString[0] = "RA01";
				authString[1] = "R";
				break;
			} else if(tmpSessionRoleMap.containsValue("RA02")) {
				authString[0] = "RA02";
				authString[1] = "R";
				break;
				
			} else if(tmpSessionRoleMap.containsValue("RA03")){
				authString[0] = "RA03";
				authString[1] = "R";
				break;
			} 
		}
		
		return authString;
	}
	
	/**
	 * 품의서인쇄
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardConsultationApprovalPrint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "/WEB-INF/jsp/clm/report/ConsultationApprovalPrint_p.jsp";
			ConsultationForm form = new ConsultationForm();
			ConsultationVO 	 vo   = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			form.setReport_url(propertyService.getProperty("secfw.url.domain"));
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("command", form);
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			

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
	 * 상태선택팝업
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardChooseStatusPopup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			String forwardURL 		= "/WEB-INF/jsp/clm/manage/ChooseStatus_p.jsp";
			
			ConsultationForm form 	= new ConsultationForm();		
			
			bind(request, form);
			
			COMUtil.getUserAuditInfo(request, form);
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
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
	 * 반려/취소시 상태이동
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifyReworkStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "/clm/manage/consultation.do?method=detailConsultation";

			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			ModelAndView mav = new ModelAndView();
						
			List resultList = consultationService.listConsultationContract(vo);
			int result = 0;
			
			if(resultList != null && resultList.size() > 0) {	
				for(int i=0; i < resultList.size(); i++) {
					ListOrderedMap lom = (ListOrderedMap)resultList.get(i);
					if("request".equals(form.getSubmit_status())) {		//재검토의뢰
						vo.setCntrt_status("C02401");
						vo.setPrcs_depth("C02501");
						vo.setDepth_status("C02608");
						vo.setPlndbn_req_yn("N");  // 최종본 회신여부 N 으로 수정 재검토 할수있게 심우규 수정							
						vo.setCnsd_status("C04305");
						result = consultationService.modifyReworkCnsdStatus(vo);
					} else {
						vo.setCntrt_status("C02401");
						vo.setPrcs_depth("C02502");
						vo.setDepth_status("C02621");
					}
					result = consultationService.modifyReworkAgreeInfo(vo);
					result = consultationService.modifyReworkContractStatus(vo);
				}
				
				if("request".equals(form.getSubmit_status())) {		//재검토의뢰
					vo.setPrgrs_status("C04207");
					//포워드한다...의뢰로...메뉴까지 바꾸는걸로
					forwardURL = "/secfw/main.do?method=forwardMainFrame&targetMenuId=20110802182454521_0000000036&selected_menu_id=20110802182454521_0000000036&selected_menu_detail_id=20110803091536879_0000000048&set_init_url=/clm/manage/consideration.do?method=listManageConsideration";
				} else {
					vo.setPrgrs_status("C04211");
				}	
				result = consultationService.modifyReworkRequestStatus(vo);
				mav.setViewName(forwardURL);
				this.getLogger().debug("forwardURL: " +mav.getViewName());
				mav.addObject("command", form);
			}	
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
	 * 품의작성보류
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView modifyConsultationStatusDefer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "/clm/manage/consultation.do?method=listManageConsultation";
		
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			ModelAndView mav = new ModelAndView();
			
			int result = consultationService.modifyConsultationStatusDefer(vo);

			String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
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
	
	/*
	 * History 팝업
	 * */
	public ModelAndView forwardHistoryPop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL 		= "/WEB-INF/jsp/clm/manage/Consultation_preview_p.jsp";
			String strUrl = (request.getRequestURL()).toString();
			String strAttachUrl = strUrl.substring(0, strUrl.indexOf("/", 7) + 1) + "clms/common/clmsfile.do?method=doClmsDownload&file_id=";
			
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			ConsultationForm form = new ConsultationForm();
			ConsultationVO   vo   = new ConsultationVO();
		
			ChooseContractForm contractForm = new ChooseContractForm();		
			ChooseContractVO contractVo 	= new ChooseContractVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			bind(request, contractForm);
			bind(request, contractVo);
			
			COMUtil.getUserAuditInfo(request, contractForm);
			COMUtil.getUserAuditInfo(request, contractVo);
			
			
			
			List reqList				= null;		//의뢰정보
			ListOrderedMap reqLom		= null;
			StringBuffer sbContent		= null;
			List contractList			= null;
			ListOrderedMap contLom		= null;
			
			String agreeUserId 		= "";
			String approvalTitle 	= "";
			String last_locale		= "";
			reqList = consultationService.detailConsultationApprovalRequest(vo);
			
			String sPrgrs_status = ""; // 진행 상태 입니다.
			
			/******************************************************
			 * 1. 버튼에 따라서 이행정보의 완료일,비고, 확인여부를 보여주고 숨겨야 된다.
			 * 2. 아래 값(buGubn)을 이용하여 버튼에 따라서 값을 던져줘야 된다. 
			 **************************************************/
			
			String buGubn = "";   // 인쇄 버튼의 구분
			String sMid_id = "";  // 결재관련 코드입니다. 사용이유는 각 계약건마다 결재자가 변경이 될 경우 변경된 값을 가지고 오기 위해서 입니다.
			
			sMid_id = request.getParameter("mis_id");
			
			buGubn = StringUtil.nvl((String)request.getParameter("buGubn"),"");
			
			vo.setBuGubn(buGubn);
			
			sbContent = new StringBuffer();
			if(reqList != null && reqList.size() > 0) {
				reqLom	= new ListOrderedMap();
				reqLom	= (ListOrderedMap)reqList.get(0);
				agreeUserId = (String)reqLom.get("agree_user_id");
				approvalTitle	= (String)reqLom.get("req_title");
				sPrgrs_status = (String)reqLom.get("prgrs_status");
				last_locale = (String)reqLom.get("last_locale");
				makeApprovalHeader(sbContent, strUrl.substring(0, strUrl.indexOf("/", 7)), last_locale);
				
				if(!"C04211".equals(sPrgrs_status) && !"C04201".equals(sPrgrs_status)){   // 작성중일 경우에는 위의 결제 내역을 보여주면 아니 아니 아니되오~~ 작성중 : C04211, C042101
				
					vo.setMis_id(sMid_id);
					
					makeApprovalHistory(sbContent, vo, last_locale);
				    
				}
				makeApprovalReqInfo(sbContent, reqLom, strAttachUrl);
			    contractList = consultationService.listConsultationApprovalContract(vo);
				if(contractList != null && contractList.size() > 0) {
					String[] approvalYn = new String[contractList.size()];
					String[] cntrtArr   = new String[contractList.size()];
					for(int i=0; i < contractList.size();i++) {
						contLom = (ListOrderedMap)contractList.get(i);
						approvalYn[i] = "Y";
						cntrtArr[i] = (String)contLom.get("cntrt_id");
					}
					contractVo.setApproval_yn_arr(approvalYn);
					contractVo.setCntrt_id_arr(cntrtArr);
				}
				if(contractVo.getApproval_yn_arr() != null && contractVo.getApproval_yn_arr().length > 0) {
					makeApprovalContractInfo(sbContent, contractVo, vo, strAttachUrl, reqLom);
				}	
				makeApprovalFooter(sbContent);
			}
			
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("command", form);
			mav.addObject("content", sbContent.toString());
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	//결재문서 스타일정보설정
	public void makeApprovalHistory(StringBuffer sb, ConsultationVO vo, String last_locale) throws Exception {
		try {
			List approvalHisList = consultationService.listApprovalHis(vo);
			
			Locale locale1 = new Locale(last_locale);
			
			if(approvalHisList != null && approvalHisList.size() > 0){
			
				ListOrderedMap lomDetail = (ListOrderedMap)approvalHisList.get(0);
				
				sb.append("<table cellspacing=0 cellpadding=0 width=\"100%\" border=\"0\" nowrap>\n")
				  .append("<tbody>\n")
				  .append("	<tr>\n")
				  .append("		<td>\n")
				  .append("			<table cellspacing=0 cellpadding=0 width=\"100%\" border=0>\n")
				  .append("			<tbody>\n")
				  .append("				<tr>\n")
				  .append("					<td style=\"background-color: #ffffff\" height=10></td>\n")
				  .append("				</tr>\n")
				  .append("			</tbody>\n")
				  .append("			</table>\n")
				  .append("		</td>\n")
				  .append("	</tr>\n")
				  .append("	<tr align=middle>\n")
				  .append("		<td style=\"padding-right: 0px; padding-left: 0px; padding-bottom: 0px; padding-top: 0px; background-color: #d8e7ef\">\n")
				  .append("			<table cellspacing=0 cellpadding=0 width=\"100%\" border=0>\n")
				  .append("			<tbody>\n")
				  .append("				<tr>\n")
				  .append("					<td style=\"background-color: #b4b4b4\" height=1></td>\n")
				  .append("				</tr>\n")
				  .append("			</tbody>\n")
				  .append("			</table>\n")
				  .append("			<table cellspacing=0 cellpadding=0 width=\"100%\" bgcolor=#ffffff border=0>\n")
				  .append("			<tbody>\n")
				  .append("				<tr>\n")
				  //제목
				  .append("					<td style=\"padding-right: 5px; padding-left: 10px; font-weight: bold; font-size: 9pt; width: 10%; color: #7a5e4b; height: 28px; background-color: #f4f1e7\" nowrap>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalHistory01", null, locale1) + "</td>\n")
				  .append("					<td style=\"padding-left: 5px; font-weight: bold; font-size: 9pt; color: #666666; padding-top: 5px; background-color: #f4f1e7;text-align: left !important\" width=\"100%\">\n")
				  .append("						<table cellspacing=0 cellpadding=0 border=0>\n")
				  .append("						<tbody>\n")
				  .append("							<tr>\n")
				  .append("								<td style=\"padding-left: 10px; font-size: 9pt; color: #666666; padding-top: 2px; height: 28px; background-color: #f4f1e7\">"+lomDetail.get("title")+"</td>\n")
				  .append("							</tr>\n")
				  .append("						</tbody>\n")
				  .append("						</table>\n")
				  .append("					</td>\n")
				  .append("				</tr>\n")
				  .append("				<tr>\n")
				  .append("					<td style=\"background-color: #dbdbdb\" colspan=2 height=1></td>\n")
				  .append("				</tr>\n")
				  .append("				<tr>\n")
				  //작성자
				  .append("					<td style=\"padding-right: 5px; padding-left: 10px; font-weight: bold; font-size: 9pt; width: 10%; color: #7a5e4b; padding-top: 1px; height: 28px; background-color: #fcfbf8\">" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalHistory02", null, locale1) + "</td>\n")
				  .append("					<td style=\"padding-left: 5px; font-weight: bold; font-size: 9pt; color: #666666; padding-top: 5px; background-color: #fcfbf8;text-align: left !important\" width=\"100%\">\n")
				  .append("						<table cellspacing=0 cellpadding=0 border=0>\n")
				  .append("						<tbody>\n")
				  .append("							<tr>\n")
				  .append("								<td style=\"padding-left: 10px; font-size: 9pt; color: #666666; padding-top: 2px; height: 28px; background-color: #fcfbf8\">"+lomDetail.get("g_user")+"</td>\n")
				  .append("							</tr>\n")
				  .append("						</tbody>\n")
				  .append("						</table>\n")
				  .append("					</td>\n")
				  .append("				</tr>\n")
				  .append("				<tr>\n")
				  .append("					<td style=\"background-color: #dbdbdb\" colspan=2 height=1></td>\n")
				  .append("				</tr>\n")
				  .append("				<tr>\n")
				  //표준시간
				  .append("					<td style=\"padding-right: 5px; padding-left: 10px; font-weight: bold; font-size: 9pt; width: 10%; color: #7a5e4b; padding-top: 1px; height: 28px; background-color: #fcfbf8\">" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalHistory03", null, locale1) + "</td>\n")
				  .append("					<td style=\"padding-left: 5px; font-weight: bold; font-size: 9pt; color: #666666; padding-top: 5px; background-color: #fcfbf8;text-align: left !important\" width=\"100%\">\n")
				  .append("						<table cellspacing=0 cellpadding=0 border=0>\n")
				  .append("						<tbody>\n")
				  .append("							<tr>\n")
				  .append("								<td style=\"padding-left: 10px; font-size: 9pt; color: #666666; padding-top: 2px; height: 28px; background-color: #fcfbf8\">"+lomDetail.get("time_zone")+"</td>\n")
				  .append("							</tr>\n")
				  .append("						</tbody>\n")
				  .append("						</table>\n")
				  .append("					</td>\n")
				  .append("				</tr>\n")
				  .append("			</tbody>\n")
				  .append("			</table>\n")
				  .append("			<table cellspacing=0 cellpadding=0 width=\"100%\" border=0>\n")
				  .append("			<tbody>\n")
				  .append("				<tr>\n")
				  .append("					<td style=\"background-color: #b4b4b4\" height=1></td>\n")
				  .append("				</tr>\n")
				  .append("			</tbody>\n")
				  .append("			</table>\n")
				  .append("			<table style=\"table-layout: fixed\" cellspacing=0 cellpadding=0 width=\"100%\" border=0>\n")
				  .append("			<tbody>\n")
				  .append("				<tr align=middle>\n")
				  //순번
				  .append("					<td style=\"font-weight: bold; font-size: 9pt; color: #7a5e4b; height: 28px; background-color: #e4e2d6; text-align: center\" nowrap width=\"10%\" >" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalHistory04", null, locale1) + "</td>\n")
				  //구분
				  .append("					<td style=\"font-weight: normal; font-size: 9pt; color: #333333; height: 28px; background-color: #e4e2d6; text-align: center\" nowrap width=\"10%\" >" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalHistory05", null, locale1) + "</td>\n")
				  //성명
				  .append("					<td style=\"font-weight: normal; font-size: 9pt; color: #333333; height: 28px; background-color: #e4e2d6; text-align: center\" nowrap width=\"25%\" >" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalHistory06", null, locale1) + "</td>\n")
				  //결재일시
				  .append("					<td style=\"font-weight: normal; font-size: 9pt; color: #333333; height: 28px; background-color: #e4e2d6; text-align: center\" nowrap width=\"25%\" >" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalHistory07", null, locale1) + "</td>\n")
				  //부서명
				  .append("					<td style=\"font-weight: normal; font-size: 9pt; color: #333333; height: 28px; background-color: #e4e2d6; text-align: center\" nowrap width=\"25%\" >" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalHistory08", null, locale1) + "</td>\n")
				  .append("				</tr>\n")
				  .append("				<tr>\n")
				  .append("					<td bgcolor=#d9d9d9 colspan=5 height=1 ></td>\n")
				  .append("				</tr>\n")
				  .append("			</tbody>\n")
				  .append("			</table>\n")
				  .append("			<table cellspacing=0 cellpadding=0 width=\"100%\" border=0>\n")
				  .append("			<tbody>\n");
					
				for(int i=0; i < approvalHisList.size(); i++) {  
					ListOrderedMap s1Lom = (ListOrderedMap)approvalHisList.get(i);
					sb.append("				<tr>\n");
	if(!((String)messageSource.getMessage("clm.page.field.consultation.makeApprovalHistory01", null, locale1)).equals(s1Lom.get("activity"))){			// !"통보"
					sb.append("					<td style=\"padding-right: 5px; padding-left: 10px; font-weight: bold; font-size: 9pt; color: #7a5e4b; padding-top: 1px; border-bottom: #cccccc 1px solid; height: 28px; background-color: #fcfbf8\" align=middle width=\"10%\" rowspan=2 >"+s1Lom.get("sequence")+"</td>\n");
	} else {
		sb.append("					<td style=\"padding-right: 5px; padding-left: 10px; font-weight: bold; font-size: 9pt; color: #7a5e4b; padding-top: 1px; border-bottom: #cccccc 1px solid; height: 28px; background-color: #fcfbf8\" align=middle width=\"10%\" rowspan=1 >"+s1Lom.get("sequence")+"</td>\n");
	}
					sb.append("					<td style=\"padding-left: 10px; font-size: 9pt; color: #333333; padding-top: 2px; border-bottom: #cccccc 1px solid; height: 28px; background-color: #fcfbf8;text-align: left !important\" width=\"15%\" >"+s1Lom.get("activity")+"</td>\n")
					  .append("					<td style=\"padding-left: 10px; font-size: 9pt; color: #333333; padding-top: 2px; border-bottom: #cccccc 1px solid; height: 28px; background-color: #fcfbf8;text-align: left !important\" width=\"25%\" >"+s1Lom.get("user_nm")+"</a></td>\n")
					  .append("					<td style=\"padding-left: 10px; font-size: 9pt; color: #755232; padding-top: 2px; border-bottom: #cccccc 1px solid; height: 28px; background-color: #fcfbf8;text-align: left !important\" width=\"25%\" >"+s1Lom.get("approved")+"</td>\n")
					  .append("					<td style=\"padding-left: 10px; font-size: 9pt; color: #333333; padding-top: 2px; border-bottom: #cccccc 1px solid; height: 28px; background-color: #fcfbf8;text-align: left !important\" width=\"25%\" >"+s1Lom.get("dept_name")+"</td>\n")
					  .append("				</tr>\n");
	if(!((String)messageSource.getMessage("clm.page.field.consultation.makeApprovalHistory01", null, locale1)).equals(s1Lom.get("activity"))){			// !"통보"
					sb.append("				<tr>\n")
					  .append("					<td style=\"padding-left: 10px; font-size: 9pt; color: #666666; word-break: break-all; padding-top: 2px; border-bottom: #cccccc 1px solid; height: 28px; background-color: #ffffff; word-wrap: break-word;text-align: left !important\" colspan=7 >"+s1Lom.get("opinion")+"</td>\n")
					  .append("				</tr>\n");
	} else {
	//				sb.append("				<tr>\n")
	//				  .append("					<td style=\"padding-left: 10px; font-size: 9pt; color: #666666; word-break: break-all; padding-top: 2px; border-bottom: #cccccc 1px solid; height: 28px; background-color: #ffffff; word-wrap: break-word;text-align: left !important\" colspan=7 >내 용 없 음 </td>\n")
	//				  .append("				</tr>\n");
	}
				}
				  
				sb.append("			</tbody>\n")
				  .append("			</table>\n")
				  .append("		</td>\n")
				  .append("	</tr>\n")
				  .append("</tbody>\n")
				  .append("</table>\n");			  
			}else{
				
			}
			  
			  
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	
	public ModelAndView detailConsultationNew(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			HttpSession session 		= request.getSession(false);
			String forwardURL 			= "/WEB-INF/jsp/clm/manage/Consultation_d_view_new.jsp";
			List authReqList 			= null;//권한요청자 -관련자
			List reviewerList			= null;	//Reviewer List
			String user_role			= "";
			String[] authInfo			= this.getUserAuth(request);
			user_role				    = authInfo[0];
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			ModelAndView mav = new ModelAndView();
			
			form.setUser_role(user_role);
			form.setUser_orgnz(vo.getSession_blngt_orgnz());
			
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			int cntrt_cnt = 0;
			List dcList = consultationService.detailOnlyConsultation(vo); //계약체결상태의 계약건만 조회되도록 수정
			cntrt_cnt = dcList.size();
			ListOrderedMap lomReq= (ListOrderedMap)dcList.get(0);			
			//master_cnt, master_id
			vo.setCntrt_id((String)lomReq.get("cntrt_id"));
			
			authReqList = considerationService.listContractAuthreq(vo);//권한요청자 -관련자
			reviewerList = considerationService.listContractReviewer(vo);	// Sungwoo Reviewer List added 2014-12-09
			
			StringBuffer reqAuthInfo		= new StringBuffer();
			StringBuffer reqAuthFormInfo	= new StringBuffer();
			StringBuffer reqAuthSvcInfo		= new StringBuffer();
			
			StringBuffer reqAuthSeqno		= new StringBuffer();
			StringBuffer reqAuthTrgrtman_id	= new StringBuffer();
			StringBuffer reqAuthTrgrtman_nm	= new StringBuffer();
			StringBuffer reqAuthTrgrtman_jikgup	= new StringBuffer();
			StringBuffer reqAuthTrgrtman_dept	= new StringBuffer();
			
			StringBuffer reqReviewerList	= new StringBuffer();
			
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
			
			//Sungwoo added 2014-12-09 for reviewer list added
			if(reviewerList != null && reviewerList.size() > 0){
				for (int i = 0; i < reviewerList.size(); i++) {
					reqReviewerList.append(i > 0 ? "<br>" : ""); 
					ListOrderedMap reviewerLom	= (ListOrderedMap)reviewerList.get(i);
					
					reqReviewerList.append("[");
					reqReviewerList.append(reviewerLom.get("MAIN_CNSD_YN") != null && reviewerLom.get("MAIN_CNSD_YN").equals("Y") ? "O" : "S");
					reqReviewerList.append("]");
					reqReviewerList.append(
											StringUtil.bvl((String)reviewerLom.get("USER_NM_ENG"), "") 
											+ "/" + StringUtil.bvl((String)reviewerLom.get("JIKGUP_NM_ENG"), "") 
											+ "/" + StringUtil.bvl((String)reviewerLom.get("DEPT_NM_ENG"), "")); //display용
				}
			}
			
			ArrayList lomMaster = (ArrayList)dcList;
			
			/*********** 1. 계약정보(detailConsultationContractMaster() 메소드 로직)  ****************/
			List contractList = consultationService.detailConsultationContractMaster(vo);
							
			ArrayList contractArrayList = (ArrayList)contractList;			
			ListOrderedMap contractLom	= (ListOrderedMap)contractList.get(0);
			
			//비밀유지기간
			contractLom.put("secret_keepperiod", StringUtil.convertEnterToBR(StringUtil.bvl((String)contractLom.get("secret_keepperiod"),"")));
			//책임한도   oblgt_lmt
			contractLom.put("oblgt_lmt", StringUtil.convertEnterToBR(StringUtil.bvl((String)contractLom.get("oblgt_lmt"),"")));				
			//Special Condition  spcl_cond
			contractLom.put("spcl_cond", StringUtil.convertEnterToBR(StringUtil.bvl((String)contractLom.get("spcl_cond"),"")));			
			//loac_etc 준거법 상세
			contractLom.put("loac_etc", StringUtil.convertEnterToBR(StringUtil.bvl((String)contractLom.get("loac_etc"),"")));
			//dspt_resolt_mthd_det 분쟁해결방법상세 
			contractLom.put("dspt_resolt_mthd_det", StringUtil.convertEnterToBR(StringUtil.bvl((String)contractLom.get("dspt_resolt_mthd_det"),"")));
			//pshdbkgrnd_purps 추진 목적 및 배경 
			contractLom.put("pshdbkgrnd_purps", StringUtil.convertBRToEnter(StringUtil.bvl((String)contractLom.get("pshdbkgrnd_purps"),"")));
			
			List resultList = null;

			/*********** 3. 연관계약정보(listConsultationRelation() 메소드 로직)  ****************/
			ArrayList relationList = new ArrayList();
			resultList = consultationService.listConsultationRealtion(vo);
			
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
		
			/*********** 4. 이행정보(listConsultationExe() 메소드 로직)  ****************/
			
			ExecutionVO executionVo = new ExecutionVO() ;
			executionVo.setCntrt_id(vo.getCntrt_id()) ;
			executionVo.setSys_cd(vo.getSys_cd()) ;
			executionVo.setSession_user_locale(vo.getSession_user_locale());
			resultList = executionService.listExecution(executionVo);
			
			String payment_gbn = "" ;
			int executionListSize = 0 ;
			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap tmp = (ListOrderedMap)resultList.get(0);

				//지불_구분 C02001:지불/수금, C02002:지불, C02003:수금, C02004:해당없음
				payment_gbn = (String)tmp.get("payment_gbn");
				
			}
			
			ArrayList executionLom = (ArrayList)resultList;

			/*
			 * TODO : Sungwoo added 2014-09-24 마지막 review 내역 가져오기 위해 임시추가
			 * 추후 Detail 페이지 공통 처리 후 삭제 예정
			*/
			resultList = consultationService.listReqContractReview(vo);

			ListOrderedMap lomRe = null ;
			if(resultList != null){
				lomRe = (ListOrderedMap)resultList.get(resultList.size()-1);
			}
			mav.addObject("lomRe", lomRe);
			
			/*********** 수정가능여부 세팅(기존 jsp에서 아래 분기를 <tr>,<td>마다 있던것을 간소화 하기 위해 ****************/
			String pageMode = "D" ;
			
			if("modify".equals(form.getPage_gbn())) { // 수정단계이고
				forwardURL = "/WEB-INF/jsp/clm/manage/Consultation_d_new.jsp";
				
				// 진행단계가 회신,작성중,보류이며 본인 의뢰인 경우에만 수정 가능
				if("C04211".equals(lomReq.get("prgrs_status")) || "C04212".equals(lomReq.get("prgrs_status")) || "C04207".equals(lomReq.get("prgrs_status"))) { 
					if(form.getSession_user_id().equals(form.getReqman_id())){
						pageMode = "M" ;
					}
				}
			}
			lomReq.put("page_mode", pageMode) ;
			
			/**
			 * 구주 법무시스템의 경우 로그인하 사람의 권한이 Legal Admin(RA01)일 경우 
			 * 계약의 모든 권한을 가지게 된다.
			 */
			
			// session에서 ROLE에 대한 값을 가지고 온다.
			ArrayList userRoleList = (ArrayList) session.getAttribute("secfw.session.roleList");  
			
			if(userRoleList.contains("RA01") || userRoleList.contains("RD01") || userRoleList.contains("RM00")) {
				form.setTop_role("RA01");
			} else if(userRoleList.contains("RA02") || userRoleList.contains("RA03")) {
				form.setTop_role("RA02");
			} else {
				form.setTop_role("ETC");
			}
			
			/*********** 1. 계약정보 세팅  ****************/
			mav.addObject("contractArrayList", contractArrayList);
			mav.addObject("contractLom", contractLom);
			
			/*********** 3. 연관계약정보 세팅 ****************/
			mav.addObject("relationList", relationList);
			mav.addObject("relationListSize", relationListSize);
			
			/*********** 4. 이행정보 세팅 ****************/
			mav.addObject("executionList", resultList);
			mav.addObject("executionLom", executionLom);
			mav.addObject("payment_gbn", payment_gbn);
			mav.addObject("executionListSize", executionListSize);

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("lomReq", lomReq);
			mav.addObject("lomMaster", lomMaster);
			mav.addObject("contractCount", cntrt_cnt);
			mav.addObject("dcList", dcList);
			
			if(reqAuthInfo != null && !"".equals(reqAuthInfo.toString())) {
				mav.addObject("reqAuthInfo", reqAuthInfo.toString());
				mav.addObject("reqAuthFormInfo", reqAuthFormInfo.toString());
				mav.addObject("reqAuthSvcInfo", reqAuthSvcInfo.toString());
			} else {
				mav.addObject("reqAuthInfo", "");
				mav.addObject("reqAuthFormInfo", "");
				mav.addObject("reqAuthSvcInfo", "");
			}
			
			mav.addObject("reqReviewerList", reqReviewerList);
			mav.addObject("command", form);
			
			/*********** 콤보 **************/
			Locale lc = (Locale)localeResolver.resolveLocale(request);
			String locale = StringUtil.bvl(lc.getLanguage(), "en");
			
			// 공통코드 콤보 세팅
			HashMap comboMap = new HashMap() ;
			comboMap.put("SYS_CD", "LAS");
			comboMap.put("SELECT", "#");
			comboMap.put("LOCALE", locale);
			comboMap.put("DEL_YN", "N");
			comboMap.put("USEMAN_MNG_ITM1", "");
			comboMap.put("USEMAN_MNG_ITM2", "");
			comboMap.put("USEMAN_MNG_ITM3", "");
			comboMap.put("USEMAN_MNG_ITM4", "");
			comboMap.put("USEMAN_MNG_ITM5", "");
			
			// 지불 구분
			comboMap.put("GRP_CD", "C020");
			comboMap.put("SELECTED", StringUtil.bvl(contractLom.get("payment_gbn"), ""));
			comboMap.put("ABBR", "F");
			comboMap.put("TYPE", "S");
			String paymentGbnCombo =clmsComService.listComCdByGrpCd(comboMap) ;
			
			// 화폐 단위
			comboMap.put("GRP_CD", "C5");
			comboMap.put("SELECTED", (String)contractLom.get("crrncy_unit"));
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			String crrncyUnitCombo =clmsComService.listComCdByGrpCd(comboMap) ;
			
			// 사전승인 승인방식
			comboMap.put("GRP_CD", "C028");
			comboMap.put("SELECTED", contractLom.get("bfhdcstn_apbt_mthd"));
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			String bfhdcstnApbtMthdCombo = clmsComService.listComCdByGrpCd(comboMap) ;
			
			// 연관계약 관계유형
			comboMap.put("GRP_CD", "C032");
			comboMap.put("SELECTED", "");
			comboMap.put("ABBR", "A");
			comboMap.put("TYPE", "S");
			String relYypeCombo = clmsComService.listComCdByGrpCd(comboMap) ;
			
			HashMap combo = new HashMap() ;
			combo.put("paymentGbn", paymentGbnCombo) ; // 지불구분 콤보
			combo.put("crrncyUnit", crrncyUnitCombo) ; // 화폐단위 콤보
			combo.put("bfhdcstnApbtMthd", bfhdcstnApbtMthdCombo) ; // 사전승인 승인방식 콤보
			combo.put("relType", relYypeCombo) ; // 연관계약 관계유형 콤보
			
			mav.addObject("combo", combo) ; // 콤보
			
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
	 * 체결품의정보 등록
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView modifyConsultationNew(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);

			String forwardURL = "/clm/manage/consultation.do?method=detailConsultationNew";

			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			
			ConsultationExecForm execForm = new ConsultationExecForm();
			ConsultationExecVO execVo = new ConsultationExecVO();
			
			bind(request, form);
			bind(request, vo);
		
			bind(request, execForm);
			bind(request, execVo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			COMUtil.getUserAuditInfo(request, execForm);
			COMUtil.getUserAuditInfo(request, execVo);
			
			ModelAndView mav = new ModelAndView();
			int result = consultationService.modifyConsultation(vo, execVo);
			
			if("APPROVAL".equals(form.getWork_flag())){
				//2013-02-14 체결품의 프로세스 간소화(modify by hjkim)
				//결재상신 버튼을 눌렀을 때 여기서 유효성 정보를 조회한다.
				/*********************************************************
				 * 계약유효성정보조회
				**********************************************************/
				boolean goApprovalPage = true;
				String retErrType = "";
				List resultList = consultationService.listApprovalValidation(vo);
				if(resultList != null && resultList.size() > 0) {
					//현재 단일계약이므로 1개의 row만 select된다.
					ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
					
					if((Integer)lom.get("null_cnt") > 0 || (Integer)lom.get("exec_cnt") == 0) {
						//필수항목이 입력되지 않았습니다. 품의작성를 진행하실수 없습니다.
						goApprovalPage = false;
						retErrType = "NOT_INSERT";
					} 
					
					if(Integer.parseInt((String)lom.get("wsvo_cnt")) > 0){
						//이미 체결품의중입니다.
						goApprovalPage = false;
						retErrType = "ALREADY";
					}
				}
				
				//정상건들일 경우 결재 팝업 호출
				if(goApprovalPage){ 
					forwardURL = "/clm/manage/consultation.do?method=makeApprovalHtmlNew";
				}else{ //정상적이지 않은 건들일 경우 에러표시 페이지로 이동
					forwardURL = "/WEB-INF/jsp/secfw/singleIF/ApprovalCheckResult.jsp";
					mav.addObject("retErrType", retErrType);
				}
				
			}else if("LIST".equals(form.getWork_flag())){
				forwardURL = "/clm/manage/consultation.do?method=listManageConsultation";
			}else{
				forwardURL = "/clm/manage/consultation.do?method=detailConsultationNew";
			}
			
			String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("command", form);
			mav.addObject("execCommand", execForm);
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
	 * 체결품의정보 등록
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public void modifyConsultationNew_temp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			HttpSession session = request.getSession(false);
			
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);


			ConsultationExecForm execForm = new ConsultationExecForm();
			ConsultationExecVO execVo = new ConsultationExecVO();

			bind(request, execForm);
			bind(request, execVo);

			COMUtil.getUserAuditInfo(request, execForm);
			COMUtil.getUserAuditInfo(request, execVo);

			JSONObject jo = new JSONObject();
			
			int result = consultationService.modifyConsultation(vo, execVo);
			/*********************************************************
			 * 계약유효성정보조회
			**********************************************************/
			boolean goApprovalPage = true;
			String retErrMsg = "";
			List resultList = consultationService.listApprovalValidation(vo);
			if(resultList != null && resultList.size() > 0) {
				//현재 단일계약이므로 1개의 row만 select된다.
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

				if( Integer.parseInt(String.valueOf(lom.get("null_cnt"))) > 0) {
					//필수항목이 입력되지 않았습니다. 품의작성를 진행하실수 없습니다.
					goApprovalPage = false;
					
					retErrMsg = (String)messageSource.getMessage("clm.msg.alert.choosecontract.require01", null, new RequestContext(request).getLocale());
				} 

				if(Integer.parseInt(String.valueOf(lom.get("wsvo_cnt"))) > 0){
					//이미 체결품의중입니다.
					goApprovalPage = false;
					retErrMsg = (String)messageSource.getMessage("clm.page.msg.manage.announce131", null, new RequestContext(request).getLocale());
				}
			}

			if(goApprovalPage){					
				jo.put("returnCd", "Y");
				jo.put("returnMsg", "");	
			}else{
				jo.put("returnCd", "N");
				jo.put("returnMsg", retErrMsg);	
			}

			
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out 	= response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();

		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jo 		= new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out 	= response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
			JSONObject jo 		= new JSONObject();
			t.printStackTrace();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out 	= response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} 	
	}
	
	//*결재관련시작
	//결재상신문서 내용을 생성한다.
	public ModelAndView makeApprovalHtmlNew(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			HttpSession session = request.getSession(false);
			String forwardURL = "";
			String strUrl = (request.getRequestURL()).toString();
			String strAttachUrl = strUrl.substring(0, strUrl.indexOf("/", 7) + 1) + "clms/common/clmsfile.do?method=doClmsDownload&file_id=";
			
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			ConsultationForm form = new ConsultationForm();
			ConsultationVO   vo   = new ConsultationVO();
		
			ChooseContractForm contractForm = new ChooseContractForm();		
			ChooseContractVO contractVo 	= new ChooseContractVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			bind(request, contractForm);
			bind(request, contractVo);
			
			COMUtil.getUserAuditInfo(request, contractForm);
			COMUtil.getUserAuditInfo(request, contractVo);
			
			//forwardURL = "/secfw/esbApproval.do?method=forwardApproval&approval_option=B&ref_key="+vo.getCnsdreq_id()+ "&method=postAppContStatus&apprvl_clsfcn=C03002";
			forwardURL = "/secfw/esbApproval.do?method=forwardApproval";
			
			List reqList				= null;		//의뢰정보
			ListOrderedMap reqLom		= null;
			StringBuffer sbContent		= null;
			
			/******************************************************
			 * 1. 버튼에 따라서 이행정보의 완료일,비고, 확인여부를 보여주고 숨겨야 된다.
			 * 2. 아래 값(buGubn)을 이용하여 버튼에 따라서 값을 던져줘야 된다. 
			 **************************************************/
			
			
			String buGubn = "";   // 인쇄 버튼의 구분
			
			buGubn = StringUtil.nvl((String)request.getParameter("buGubn"),"");
			
			vo.setBuGubn(buGubn);
			
			String agreeUserId 		= "";
			String approvalTitle 	= "";
			String last_locale	 	= (String)vo.getSession_user_locale();
			reqList = consultationService.detailConsultationApprovalRequest(vo);
			//contractList = consultationService.listConsultationApprovalContract(vo);
			sbContent = new StringBuffer();
			if(reqList != null && reqList.size() > 0) {
				reqLom	= new ListOrderedMap();
				reqLom	= (ListOrderedMap)reqList.get(0);
				agreeUserId = (String)reqLom.get("agree_user_id");
				approvalTitle	= (String)reqLom.get("req_title");
				reqLom.put("last_locale",last_locale);
				
				makeApprovalHeader(sbContent, strUrl.substring(0, strUrl.indexOf("/", 7)), last_locale);
				makeApprovalReqInfo(sbContent, reqLom, strAttachUrl);
				//makeApprovalContractInfo(sbContent, contractList);
				if(contractVo.getApproval_yn_arr() != null && contractVo.getApproval_yn_arr().length > 0) {
					makeApprovalContractInfoNew(sbContent, contractVo, vo, strAttachUrl, reqLom);
				}	
				makeApprovalFooter(sbContent);
			}
			
			ModelAndView mav = new ModelAndView();
							
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("form", form);
			mav.addObject("approval_content", sbContent.toString());
			
			Locale locale1 = new Locale(last_locale);
			//[체결품의]
			mav.addObject("approval_title", (String)messageSource.getMessage("clm.page.field.approval.makeApprovalHtml", null, locale1)+approvalTitle );
			mav.addObject("approval_option", "B");	
			mav.addObject("apprvl_clsfcn","C03002");
			mav.addObject("approval_post_process", "postAppContStatus");
			mav.addObject("ref_key",vo.getCnsdreq_id());
			mav.addObject("approval_agree_id",agreeUserId);
			
			/*mav.addObject("approval_module_id", "");
	        String approvalMisID       = StringUtil.bvl((String)request.getParameter("approval_mis_id"), "");
	        String approvalDrafterID   = StringUtil.bvl((String)request.getParameter("approval_draft_id"), userId);
	        String approvalTitle       = StringUtil.bvl((String)request.getParameter("approval_title"), "");
	        String approvalContent     = StringUtil.bvl((String)request.getParameter("approval_content"), "");
	        String approvalHtmlGbn     = StringUtil.bvl((String)request.getParameter("approval_html_gbn"), "");
	        String approvalFileInfos   = StringUtil.bvl((String)request.getParameter("approval_fileInfos"), "");
	        String approvalPostProcess = StringUtil.bvl((String)request.getParameter("approval_post_process"), "");*/
			//mav.addObject("command", form);

			return mav; 
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	//계약상세정보셋팅
	public void makeApprovalContractInfoNew(StringBuffer sb, ChooseContractVO contractVo, ConsultationVO vo, String attachinfo, ListOrderedMap rLom) throws Exception {
		try {
			ListOrderedMap lom				= null;
			ListOrderedMap tempLom			= null;
			ConsultationSpecialVO specialVo	= new ConsultationSpecialVO();	 
			ConsultationExecVO	  execVo	= new ConsultationExecVO();
			String last_locale	= (String)rLom.get("last_locale");
			Locale locale1 = new Locale(last_locale);
			
			specialVo.setSession_user_locale(last_locale);
			execVo.setSession_user_locale(last_locale);
			
			int iSize = contractVo.getApproval_yn_arr().length;
			for(int i=0; i < contractVo.getApproval_yn_arr().length; i++) {
				if("Y".equals(contractVo.getApproval_yn_arr()[i])) {
					vo.setCntrt_id(contractVo.getCntrt_id_arr()[i]);
					List detailContract = consultationService.detailConsultationContractMasterApproval(vo);
					
					ListOrderedMap a = (ListOrderedMap)detailContract.get(0);
					
					specialVo.setCntrt_id(vo.getCntrt_id());
					List listSpecial 	= consultationService.listConsultationSpecial(specialVo);
					ArrayList special1List = new ArrayList();
					ArrayList special2List = new ArrayList();
					if(listSpecial.size() >0 && listSpecial != null) {
						 for(int j=0; j < listSpecial.size(); j++) {
							 ListOrderedMap specialLom = (ListOrderedMap)listSpecial.get(j);
							 if("C04002".equals(specialLom.get("crtn_depth"))) {
								 special1List.add(listSpecial.get(j));
							 } else if("C04005".equals(specialLom.get("crtn_depth"))) {
								 special2List.add(listSpecial.get(j));
							 }
						 }
					 }
					
					StringBuffer reqReviewerList	= new StringBuffer();
					List reviewerList = considerationService.listContractReviewer(vo);	// Sungwoo Reviewer List added 2014-12-09
					//Sungwoo added 2014-12-09 for reviewer list added
					if(reviewerList != null && reviewerList.size() > 0){
						for (int j = 0; j < reviewerList.size(); j++) {
							reqReviewerList.append(j > 0 ? "<br>" : ""); 
							ListOrderedMap reviewerLom	= (ListOrderedMap)reviewerList.get(j);
							
							reqReviewerList.append("[");
							reqReviewerList.append(reviewerLom.get("MAIN_CNSD_YN") != null && reviewerLom.get("MAIN_CNSD_YN").equals("Y") ? "O" : "S");
							reqReviewerList.append("]");
							reqReviewerList.append(
													StringUtil.bvl((String)reviewerLom.get("USER_NM_ENG"), "") 
													+ "/" + StringUtil.bvl((String)reviewerLom.get("JIKGUP_NM_ENG"), "") 
													+ "/" + StringUtil.bvl((String)reviewerLom.get("DEPT_NM_ENG"), "")); //display용
						}
					}
					
					/******************************************************
					 * 1. 버튼에 따라서 이행정보의 완료일,비고, 확인여부를 보여주고 숨겨야 된다.
					 * 2. 아래 값(buGubn)을 이용하여 버튼에 따라서 값을 던져줘야 된다. 
					 **************************************************/
					
					String buGubn = "";   // 인쇄 버튼의 구분
					
					buGubn = (String)vo.getBuGubn(); // 버튼의 구분 여부를 체크 합니다.
					
					//이행정보조회
					execVo.setCntrt_id(vo.getCntrt_id());
					List listExec		= consultationService.listConsultationExec(execVo);
					
					ArrayList listContractAttach = new ArrayList();		//의뢰계약서
					ArrayList listEtcAttach		 = new ArrayList();		//의뢰기타
					ArrayList listPreAttach		 = new ArrayList();		//사전검토
					ArrayList listPriceAttach	 = new ArrayList();		//단가내역
					ArrayList listEtc2Attach	 = new ArrayList();		//의뢰별첨
					
					List listAttachInfo = consultationService.listConsultationApprovalAttachInfo(vo);
					if(listAttachInfo != null && listAttachInfo.size() > 0) {
						for(int j=0; j < listAttachInfo.size(); j++) {
							tempLom = (ListOrderedMap)listAttachInfo.get(j);
							if("1".equals((String)tempLom.get("filetype"))) {
								listContractAttach.add(listAttachInfo.get(j));
							} else if("2".equals((String)tempLom.get("filetype"))) {
								listEtcAttach.add(listAttachInfo.get(j));
							} else if("3".equals((String)tempLom.get("filetype"))) {
								listPreAttach.add(listAttachInfo.get(j));
							} else if("4".equals((String)tempLom.get("filetype"))) {
								listPriceAttach.add(listAttachInfo.get(j));
							} else if("5".equals((String)tempLom.get("filetype"))) {
								listEtc2Attach.add(listAttachInfo.get(j));
							}
						}
					}
					
					String titleIndex = "";
					if(iSize > 1) titleIndex = (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew01", null, locale1) +" "+ (i+1)+" "+ " - ";//계약
					ListOrderedMap lomDetail = (ListOrderedMap)detailContract.get(0);
					sb.append("	<div class=\"mt5\"></div>\n")
				      .append("	<div width=\"100%\" class=\"title_basic\">\n")
				      .append("	<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n")
				      .append("	<colgroup>\n")
					  .append("		<col width=\"50%\" />\n")
					  .append("		<col width=\"50%\" />\n")
					  .append("	</colgroup>\n") 
				      .append("	    <tr>\n")
				      //계약기본정보
				      .append("	        <td><h4>" +" "+ titleIndex +" " + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew02", null, locale1) +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h4></td>\n");

					
	if("" != lomDetail.get("cntrt_no") && null != lomDetail.get("cntrt_no")){				      
				    sb.append("		    <td align=\"right\"> ID : "+ StringUtil.bvl(lomDetail.get("cntrt_no"),"") +"</td>\n");
	}
					    sb.append("	    </tr>\n")
				      .append("	</table>\n")
					  .append("</div>\n")
					  .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
					  .append("	<colgroup>\n")
                      .append("	  <col width='13%' />\n")
	                  .append("   <col width='21%' />\n")
	                  .append("   <col width='14%' />\n")
	                  .append("   <col width='19%' />\n")
	                  .append("   <col width='12%' />\n")
	                  .append("   <col width='21%' />\n")
					  .append("	</colgroup>\n")
					  .append("    	<tr>\n")
					  //의뢰명
					  .append("        <th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew03", null, locale1) +"</th>\n")
				      .append("        <td colspan=\"5\"><span class=\"fL\">" + StringUtil.bvl(StringUtil.convertNamoCharsToHtml((String)lomDetail.get("req_title")),"") +"</span></td>\n")
				      .append("    	</tr>\n")
			          .append("    	<tr>\n")
			          //계약명
					  .append("        <th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew05", null, locale1) +"</th>\n")
				      .append("        <td colspan=\"5\"><span class=\"fL\">" + StringUtil.bvl(lomDetail.get("cntrt_nm"),"") +"</span></td>\n")
				      .append("    	</tr>\n")
				      .append("    	<tr>\n")
			          //Requester
					  .append("        <th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew04", null, locale1) +"</th>\n")
				      .append("        <td colspan=\"5\"><span class=\"fL\">" + StringUtil.bvl(lomDetail.get("reqman_nm"),"") +" / " + StringUtil.bvl(lomDetail.get("reqman_jikgup_nm"),"") + "/ " + StringUtil.bvl(lomDetail.get("req_dept_nm"),"") +"</span></td>\n")
				      .append("    	</tr>\n")
				      .append("    	<tr>\n")
			          //Reviewer List Sungwoo added 2014-12-09
					  .append("        <th>" + (String)messageSource.getMessage("clm.page.msg.manage.reviewer", null, locale1) +"</th>\n")
				      .append("        <td colspan=\"5\"><span class=\"fL\">" + reqReviewerList +"</span></td>\n")
				      .append("    	</tr>\n")
				      .append("    	<tr>\n")
				      //계약상대방
				      .append("        <th>" + (String)messageSource.getMessage("clm.page.msg.common.otherParty", null, locale1) +"</th>\n")
				      .append("        <td>" + StringUtil.bvl(lomDetail.get("cntrt_oppnt_nm"),"") + "</td>\n")
				      //대표자명
				      .append("        <th>" + (String)messageSource.getMessage("clm.page.field.customer.registerNo", null, locale1) +"</th>\n")
				      .append("        <td>" + StringUtil.convertNamoCharsToHtml((String)lomDetail.get("cntrt_oppnt_rprsntman")) + "</td>\n")
				      //상대방유형
				      .append("        <th>" + (String)messageSource.getMessage("clm.page.field.customer.contractRequired", null, locale1) +"</th>\n")
				      .append("        <td>" + StringUtil.bvl(lomDetail.get("cntrt_oppnt_type_nm"), "") + "</td>\n")
				      .append("	   	</tr>\n")
					  .append("	   	<tr class=\"slide-target02 slide-area\">\n")
					  //계약유형
				      .append("	       <th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew09", null, locale1) +"</th>\n")
				      .append("    	   <td colspan=\"3\">" + StringUtil.bvl(lomDetail.get("biz_clsfcn_nm"),"") + " / " + StringUtil.bvl(lomDetail.get("depth_clsfcn_nm"),"") + " / " + StringUtil.bvl(lomDetail.get("cnclsnpurps_bigclsfcn_nm"),"") + " / " + StringUtil.bvl(lomDetail.get("cnclsnpurps_midclsfcn_nm"),"") + "</td>\n")
				      //자동연장여부
				      .append("	       <th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew10", null, locale1) +"</th>\n")
				      .append("    	   <td colspan=\"3\">" + ("Y".equals((String)lomDetail.get("auto_rnew_yn")) ? "Yes" : "No") + "</td>\n")
				      .append("	   	</tr>\n")
				      .append("    	<tr>\n")
				      
				      .append("    	<tr>\n")
				      //top30Cus
				      .append("        <th>" + (String)messageSource.getMessage("clm.page.msg.manage.top30Cus", null, locale1) +"</th>\n")
				      .append("        <td>" + StringUtil.bvl(lomDetail.get("top_30_cus"),"") + "</td>\n")
				      //related_products
				      .append("        <th>" + (String)messageSource.getMessage("clm.page.msg.manage.relatedPrd", null, locale1) +"</th>\n")
				      .append("        <td>" + StringUtil.convertNamoCharsToHtml((String)lomDetail.get("related_products")) + "</td>\n")
				      //cont_draft
				      .append("        <th>" + (String)messageSource.getMessage("clm.page.msg.manage.srcContDraft", null, locale1) +"</th>\n")
				      .append("        <td>" + StringUtil.bvl(lomDetail.get("cont_draft"), "") + "</td>\n")
				      .append("	   	</tr>\n")

				      //계약대상
				      .append("        <th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew11", null, locale1) +"</th>\n")
				      .append("        <td>" + StringUtil.bvl(lomDetail.get("cntrt_trgt_nm"),"") + "</td>\n")
				      //계약대상상세
				      .append("        <th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew12", null, locale1) +"</th>\n")
				      .append("        <td  colspan=\"3\">" + StringUtil.convertEnterToBR(StringUtil.convertNamoCharsToHtml((String)lomDetail.get("cntrt_trgt_det")))+ "</td>\n")
					  .append("		</tr>\n")
					  .append("		<tr>\n")
					  //계약기간
					  .append(" 	    <th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew14", null, locale1) +"</th>\n")
					  .append("	        <td colspan=\"3\">" + lomDetail.get("cntrtperiod_startday") + " ~ " + lomDetail.get("cntrtperiod_endday") +"</td>\n")
					  //지불/수금 구분
					  .append("			<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew15", null, locale1) +"</th>\n")
					  .append("         <td colspan=\"6\">" + lomDetail.get("payment_gbn_nm") + "</td>\n")
					  .append("		</tr>\n") ;
					if(!"C02004".equals((String)lomDetail.get("payment_gbn"))) {
					sb.append("		<tr>\n")
					  //계약금액
					  .append("         <th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew16", null, locale1) +"</th>\n")
					  //(단가로 체결)
					  .append("  	    <td colspan='3' align=\"right\" class=\"tR\">" + StringUtil.bvl(lomDetail.get("cntrt_amt"), "0")+ (!"".equals(StringUtil.bvl(lomDetail.get("cntrt_untprc_expl"), "")) ? " " + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew17", null, locale1) : "") +  "</td>\n")
			          //통화(단위)
					  .append("         <th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew18", null, locale1) +"</th>\n")
			          .append("         <td>" + lomDetail.get("crrncy_unit") + "</td>\n")
					  .append("		</tr>\n") ;
					}
					if(!"".equals(StringUtil.bvl(lomDetail.get("cntrt_untprc_expl"), ""))){
						sb.append("     <tr>\n")
						  //단가내역
						  .append("         <th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew19", null, locale1) +"</th>\n")
						  .append("         <td colspan=\"5\">" + StringUtil.bvl(StringUtil.convertEnterToBR(StringUtil.convertNamoCharsToHtml((String)lomDetail.get("cntrt_untprc_expl"))), "") + "</td>\n")
						  .append("     </tr>\n");
						}
				    if(!"".equals(StringUtil.bvl(lomDetail.get("pshdbkgrnd_purps"), ""))){
					sb.append("		<tr>\n")
					  //추진목적 및 배경
					  .append("			<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew20", null, locale1) +"</th>\n")
					  .append("			<td colspan=\"5\">" + StringUtil.bvl((String)lomDetail.get("pshdbkgrnd_purps"), "") + "</td>\n")
					  .append("		</tr>\n");
				    }
					if(!"".equals(StringUtil.bvl(lomDetail.get("etc_main_cont"), "")) || !"".equals(StringUtil.bvl(lomDetail.get("if_sys_cd"), ""))) {
					  sb.append("     <tr>\n")
					    //기타 주요사항
					    .append("	    	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew21", null, locale1) +"</th>\n");
						if(!"".equals(StringUtil.bvl(lomDetail.get("if_sys_cd"), ""))) {
							sb.append("           <td colspan=\"5\" valign=\"top\">" + StringUtil.convertEnterToBR(StringUtil.bvl(StringUtil.convertEnterToBR(StringUtil.convertNamoCharsToHtml((String)lomDetail.get("etc_main_cont"))), "")) + " [" + StringUtil.bvl(lomDetail.get("if_sys_cd"), "") + "]</td>\n");
						}else{
							sb.append("           <td colspan=\"5\" valign=\"top\">" + StringUtil.convertEnterToBR(StringUtil.bvl(StringUtil.convertEnterToBR(StringUtil.convertNamoCharsToHtml((String)lomDetail.get("etc_main_cont"))), "")) + "</td>\n");
						}
					  sb.append("     </tr>\n");						
					}
					
					//특화조건시작
					if(special1List != null && special1List.size() > 0) {
						if(special1List.size() > 0 ) {
							for(int k=0; k < special1List.size(); k++) {
								ListOrderedMap s1Lom = (ListOrderedMap)special1List.get(k);
								if(!"".equals(StringUtil.bvl(s1Lom.get("attr_value"), ""))){
									sb.append("	<tr>\n")	
									  .append("		<th>" +s1Lom.get("attr_nm") + "</th>\n")
									  .append("		<td colspan='5'>" +  StringUtil.convertEnterToBR(StringUtil.convertNamoCharsToHtml((String)s1Lom.get("attr_value"))) + "</td>\n")
									  .append("	</tr>\n");
								}
							}	
						}
					}
					
					if(special2List != null && special2List.size() > 0) {
						if(special2List.size() > 0 ) {
							
							for(int k=0; k < special2List.size(); k++) {
								ListOrderedMap s2Lom = (ListOrderedMap)special2List.get(k);
								if(!"".equals(StringUtil.bvl(s2Lom.get("attr_value"), ""))){
								sb.append("<tr>")
								  .append("		<th>" + s2Lom.get("attr_nm") + "</th>\n")
								  .append("		<td colspan='5'>" +  StringUtil.bvl(StringUtil.convertEnterToBR(StringUtil.convertNamoCharsToHtml((String)s2Lom.get("attr_value"))), "") + "</td>\n")
								  .append("</tr>\n");
								}
							}
							
						}
					}
					//특화정보끝
					
					if(!"".equals(StringUtil.bvl(lomDetail.get("oblgt_lmt"), ""))){
						sb.append("<tr>\n")
						  //배상책임한도	
						  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew22", null, locale1) +"</th>\n")
						  .append("		<td colspan='5'>" + StringUtil.convertEnterToBR(StringUtil.convertNamoCharsToHtml((String)StringUtil.bvl(lomDetail.get("oblgt_lmt"), ""))) + "</td>\n")
						  .append("	</tr>\n");
					}
					if(!"".equals(StringUtil.bvl(lomDetail.get("spcl_cond"), ""))){				  
						sb.append("	<tr>\n")
						  //기타 특약사항
						  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew23", null, locale1) +"</th>\n")
						  .append("		<td colspan='5'>" + StringUtil.convertEnterToBR(StringUtil.convertNamoCharsToHtml((String)StringUtil.bvl(lomDetail.get("spcl_cond"), ""))) + "</td>\n")
						  .append("	</tr>\n");
					}
					
					sb.append("	<tr>\n")
					  //준거법
					  .append("	    <th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew24", null, locale1) +"</th>\n")
					  .append("		<td>" +  StringUtil.bvl(lomDetail.get("loac"), "") + "</td>\n")
					  //준거법상세
					  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew25", null, locale1) +"</th>\n")
					  .append("		<td colspan='3'>" + StringUtil.convertEnterToBR((String)StringUtil.bvl(lomDetail.get("loac_etc"), "")) + "</td>\n")
					  .append("	</tr>\n")
					  .append("	<tr>\n")
					  //분쟁해결방법
					  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew26", null, locale1) +"</th>\n")
					  .append(" 	<td>" + StringUtil.bvl(lomDetail.get("dspt_resolt_mthd"), "") + "</td>\n")
					  //분쟁해결방법상세
					  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew27", null, locale1) +"</th>\n")
					  .append("		<td colspan='3'>" + StringUtil.convertEnterToBR((String)StringUtil.bvl(lomDetail.get("dspt_resolt_mthd_det"), "")) + "</td>\n")
					  .append("	</tr>\n")
					  .append("	</table>\n");
					
					//최종본 검토의견
					sb.append("	<div class=\"mt5\"></div>\n")
				      .append("	<div class=\"title_basic\">\n")
				      .append("	<h4>" + titleIndex + (String)messageSource.getMessage("clm.page.field.regist.makeApprovalContractInfo230", null, locale1) +"</h4>\n")
				      .append(" </div>")
				      .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
				      .append("	<colgroup>\n")
				      .append("		<col width=\"14%\" />\n")
				      .append("		<col />\n")
				      .append("	</colgroup>\n")
				      .append(" <tr>\n")
				      .append("	<th>" + (String)messageSource.getMessage("clm.page.msg.manage.finalOpin", null, locale1) +"</th>\n")
				      .append(" <td>" + StringUtil.bvl(lomDetail.get("cont"), "") + "</td>\n")
				      .append("</tr>\n");
				    sb.append("</table>\n");
				  //최종본 검토의견끝
					
					// TODO 2011-12-05 김형준 : 고객요청으로 체결 품의상신시 주요이행사항, 지불계획, 기타이행계획 안보이게 한다.
					if("C04211".equals((String)rLom.get("prgrs_status")) || "C04212".equals((String)rLom.get("prgrs_status")) || 
						"C04213".equals((String)rLom.get("prgrs_status")) || "C04214".equals((String)rLom.get("prgrs_status")) || 
						"C04215".equals((String)rLom.get("prgrs_status")) || "C04216".equals((String)rLom.get("prgrs_status")) ||
						"C04211".equals((String)rLom.get("prgrs_status"))) 
					{
						
					}else{
						
						int iC05501Size = 0;	//지불계획
						int iC05502Size = 0;	//수금계획
						int iC05503Size = 0;	//기타이행계획
						for(int j=0; j < listExec.size();j++) {
							ListOrderedMap execLom = (ListOrderedMap)listExec.get(j);
							if("C05501".equals(execLom.get("exec_gbn"))) {
								iC05501Size++;
							} else if("C05502".equals(execLom.get("exec_gbn"))) {
								iC05502Size++;
							} else {
								iC05503Size++;
							}
						}
						
					if(iC05501Size != 0 || iC05502Size != 0 || iC05503Size != 0){
						
						
						//이행사항시작					
						sb.append("	<div class=\"mt5\"></div>\n")
					      .append("	<div class=\"title_basic\">\n")
					      //주요이행사항
					      .append("	<h4>" + titleIndex + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew28", null, locale1) +"</h4>\n")
					      .append(" </div>");
						if("C02001".equals((String)lomDetail.get("payment_gbn")) || "C02002".equals((String)lomDetail.get("payment_gbn")) || iC05501Size > 0) {	//지불
							sb.append("	<div class=\"mt5\"></div>\n")
						      .append("	<div class=\"title_basic3\">\n")
						      //지불계획
						      .append((String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew29", null, locale1)+"\n")
						      .append(" </div>")
						      .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"list_basic\">\n");
	if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우
							sb.append("	<colgroup>\n")
						      .append(" 	<col width=\"7%\"/>\n")
							  .append("		<col width=\"20%\"/>\n")
							  .append("		<col width=\"12%\"/>\n")
							  .append("		<col width=\"13%\"/>\n")
							  .append("		<col width=\"17%\"/>\n")
							  .append("	</colgroup>\n");
	} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
							sb.append("	<colgroup>\n")
						      .append(" 	<col width=\"7%\"/>\n")
							  .append("		<col width=\"13%\"/>\n")
							  .append("		<col width=\"12%\"/>\n")
							  .append("		<col width=\"13%\"/>\n")
							  .append("		<col width=\"17%\"/>\n")
							  .append("		<col width=\"12%\"/>\n")
							  .append("		<col width=\"16%\"/>\n")
							  .append("		<col width=\"10%\"/>\n")
							  .append("	</colgroup>\n");
	}
								sb.append("	<thead>\n")
							  .append("	<tr>\n");
	if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우(							  
							sb.append("		<th>No</th>\n")
							  //이행항목
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew30", null, locale1) +"</th>\n")
							  //완료예정일
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew31", null, locale1) +"</th>\n")
							  //금액
							  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew32", null, locale1) +"</th>\n")
							  //지불조건
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew33", null, locale1) +"</th>\n");
	} else {                 // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
							sb.append("		<th>No</th>\n")
							  //이행항목
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew30", null, locale1) +"</th>\n")
							  //완료예정일
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew31", null, locale1) +"</th>\n")
							  //금액
							  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew32", null, locale1) +"</th>\n")
							  //지불조건
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew33", null, locale1) +"</th>\n")
							  //완료일
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew34", null, locale1) +"</th>\n")
							  //비고
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew35", null, locale1) +"</th>\n")
							  //확인여부
							  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew36", null, locale1) +"</th>\n");
	}
		
								sb.append("	</tr>\n")
							  .append("	</thead>\n")
							  .append("	<tbody>\n");
						    if(iC05501Size > 0) {
						    	int k = 1;
						    	for(int j = 0; j < listExec.size(); j++) {
									ListOrderedMap execLom = (ListOrderedMap)listExec.get(j);
									if("C05501".equals(execLom.get("exec_gbn"))) {
										sb.append("	<tr>\n");
	if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우(												
								    	sb.append("		<td align=\"center\">" + k + "</td>\n")
								    	  .append("		<td>" + StringUtil.convertEnterToBR(StringUtil.convertNamoCharsToHtml((String)execLom.get("exec_itm"))) + "</td>\n")
								    	  .append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n")
										  .append("		<td align=\"right\">" + execLom.get("exec_amt") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(StringUtil.convertEnterToBR(StringUtil.convertNamoCharsToHtml((String)execLom.get("exec_cont"))), "") + "</td>\n")
								    	  .append("	</tr>\n");
	} else {
											sb.append("		<td align=\"center\">" + k + "</td>\n")
										  .append("		<td>" + StringUtil.convertEnterToBR(StringUtil.convertNamoCharsToHtml((String)execLom.get("exec_itm"))) + "</td>\n")
										  .append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n")
										  .append("		<td align=\"right\">" + execLom.get("exec_amt") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(StringUtil.convertEnterToBR(StringUtil.convertNamoCharsToHtml((String)execLom.get("exec_cont"))), "") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cmpltday"), "") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(StringUtil.convertEnterToBR(StringUtil.convertNamoCharsToHtml((String)execLom.get("note"))), "") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_statusnm"), "") + "</td>\n")
										  .append("	</tr>\n");
	}
									 		k++;
										}
									}
							    }
							sb.append("</tbody>\n")
							  .append("</table>\n");
						}
						
						if("C02001".equals((String)lomDetail.get("payment_gbn")) || "C02003".equals((String)lomDetail.get("payment_gbn")) || iC05502Size > 0) {	//수금
							sb.append("	<div class=\"mt5\"></div>\n")
						      .append("	<div class=\"title_basic3\">\n")
						      //수금계획
						      .append((String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew37", null, locale1)+"\n")
						      .append(" </div>")
							  .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"list_basic\">\n")
							  .append("	<colgroup>\n");
	if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우			  
						    sb.append(" 	<col width=\"7%\"/>\n")
							  .append("		<col width=\"20%\"/>\n")
							  .append("		<col width=\"12%\"/>\n")
							  .append("		<col width=\"13%\"/>\n")
							  .append("		<col width=\"17%\"/>\n");
	} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
							sb.append(" 	<col width=\"7%\"/>\n")
							  .append("		<col width=\"13%\"/>\n")
							  .append("		<col width=\"12%\"/>\n")
							  .append("		<col width=\"13%\"/>\n")
							  .append("		<col width=\"17%\"/>\n")
							  .append("		<col width=\"12%\"/>\n")
							  .append("		<col width=\"16%\"/>\n")
							  .append("		<col width=\"10%\"/>\n");
	}
								sb.append("	</colgroup>\n")
							  .append("	<thead>\n")
							  .append("	<tr>\n");
	if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우							  
							sb.append("		<th>No</th>\n")
							  //이행항목
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew30", null, locale1) +"</th>\n")
							  //완료예정일
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew31", null, locale1) +"</th>\n")
							  //금액
							  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew32", null, locale1) +"</th>\n")
							  //수금조건
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew38", null, locale1) +"</th>\n");
	} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
							sb.append("		<th>No</th>\n")
							  //이행항목
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew30", null, locale1) +"</th>\n")
							  //완료예정일
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew31", null, locale1) +"</th>\n")
							  //금액
							  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew32", null, locale1) +"</th>\n")
							  //수금조건
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew38", null, locale1) +"</th>\n")
							  //완료일
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew34", null, locale1) +"</th>\n")
							  //비고
							  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew35", null, locale1) +"</th>\n")
							  //확인여부
							  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew36", null, locale1) +"</th>\n");
	}
								sb.append("	</tr>\n")
							  .append("	</thead>\n")
							  .append("	<tbody>\n");
							if(iC05502Size > 0) {
								int k = 1;
						    	for(int j = 0; j < listExec.size(); j++) {
									ListOrderedMap execLom = (ListOrderedMap)listExec.get(j);
									if("C05502".equals(execLom.get("exec_gbn"))) {
										sb.append("	<tr>\n");
	if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우
								    	sb.append("		<td align=\"center\">" + k + "</td>\n")
								    	  .append("		<td>" + StringUtil.convertEnterToBR(StringUtil.convertNamoCharsToHtml((String)execLom.get("exec_itm"))) + "</td>\n")
								    	  .append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n")
										  .append("		<td align=\"right\">" + execLom.get("exec_amt") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(StringUtil.convertEnterToBR(StringUtil.convertNamoCharsToHtml((String)execLom.get("exec_cont"))), "") + "</td>\n");
	} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
										sb.append("		<td align=\"center\">" + k + "</td>\n")
										  .append("		<td>" + StringUtil.convertEnterToBR(StringUtil.convertNamoCharsToHtml((String)execLom.get("exec_itm"))) + "</td>\n")
										  .append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n")
										  .append("		<td align=\"right\">" + execLom.get("exec_amt") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(StringUtil.convertEnterToBR(StringUtil.convertNamoCharsToHtml((String)execLom.get("exec_cont"))), "") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cmpltday"), "") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(StringUtil.convertEnterToBR(StringUtil.convertNamoCharsToHtml((String)execLom.get("note"))), "") + "</td>\n")
										  .append("		<td>" + StringUtil.bvl(execLom.get("exec_statusnm"), "") + "</td>\n");
	}
									    	sb.append("	</tr>\n");
	
	
	
								 		k++;
									}
								}
						    }
							sb.append("</tbody>\n")
							  .append("</table>\n");
						} 
						if(iC05503Size > 0) {
						sb.append("	<div class=\"mt5\"></div>\n")
					      .append("	<div class=\"title_basic3\">\n")
					      //기타이행계획
					      .append((String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew39", null, locale1) +"\n")
					      .append(" </div>")
						  .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"list_basic\">\n")
						  .append("	<colgroup>\n");
	if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우						  
					    sb.append(" 	<col width=\"7%\"/>\n")
						  .append("		<col width=\"20%\"/>\n")
						  .append("		<col width=\"12%\"/>\n")
						  .append("		<col width=\"30%\"/>\n");
	} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
						sb.append(" 	<col width=\"7%\"/>\n")
						  .append("		<col width=\"13%\"/>\n")
						  .append("		<col width=\"12%\"/>\n")
						  .append("		<col width=\"30%\"/>\n")
						  .append("		<col width=\"12%\"/>\n")
						  .append("		<col width=\"16%\"/>\n")
						  .append("		<col width=\"10%\"/>\n");
	}
							sb.append("	</colgroup>\n")
						  .append("	<thead>\n")
						  .append("	<tr>\n");
	if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우						  
						sb.append("		<th>No</th>\n")
						  //이행항목
						  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew30", null, locale1) +"</th>\n")
						  //완료예정일
						  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew31", null, locale1) +"</th>\n")
						  .append(" 	<th>Description</th>\n");
	} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
						sb.append("		<th>No</th>\n")
						  //이행항목
						  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew30", null, locale1) +"</th>\n")
						  //완료예정일
						  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew31", null, locale1) +"</th>\n")
						  .append(" 	<th>Description</th>\n")
						  //완료일
						  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew34", null, locale1) +"</th>\n")
						  //비고
						  .append(" 	<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew35", null, locale1) +"</th>\n")
						  //확인여부
						  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew36", null, locale1) +"</th>\n");
	}
							sb.append("	</tr>\n")
						  .append("	</thead>\n")
						  .append("	<tbody>\n");
							int k = 1;
					    	for(int j = 0; j < listExec.size(); j++) {
								ListOrderedMap execLom = (ListOrderedMap)listExec.get(j);
								if("C05503".equals(execLom.get("exec_gbn"))) {
									sb.append("	<tr>\n");
	if("".equals(buGubn)){   // 하단의 각 단계 별로 눌렀을 경우	
							    	sb.append("		<td align=\"center\">" + k + "</td>\n")
							    	  .append("		<td>" + StringUtil.convertEnterToBR(StringUtil.convertNamoCharsToHtml((String)execLom.get("exec_itm"))) + "</td>\n")
							    	  .append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n")
									  .append("		<td>" + StringUtil.bvl(StringUtil.convertEnterToBR(StringUtil.convertNamoCharsToHtml((String)execLom.get("exec_cont"))), "") + "</td>\n");
	} else {          // 상단의 인쇄 버튼 클릭 시 (품의서 출력 버튼)
									sb.append("		<td align=\"center\">" + k + "</td>\n")
									  .append("		<td>" + StringUtil.convertEnterToBR(StringUtil.convertNamoCharsToHtml((String)execLom.get("exec_itm"))) + "</td>\n")
									  .append("		<td align=\"center\">" + execLom.get("exec_plndday") + "</td>\n")
									  .append("		<td>" + StringUtil.bvl(StringUtil.convertEnterToBR(StringUtil.convertNamoCharsToHtml((String)execLom.get("exec_cont"))), "") + "</td>\n")
									  .append("		<td>" + StringUtil.bvl(execLom.get("exec_cmpltday"), "") + "</td>\n")
									  .append("		<td>" + StringUtil.bvl(StringUtil.convertEnterToBR(StringUtil.convertNamoCharsToHtml((String)execLom.get("note"))), "") + "</td>\n")
									  .append("		<td>" + StringUtil.bvl(execLom.get("exec_statusnm"), "") + "</td>\n");
	}
									    sb.append("	</tr>\n");							 		
									k++;
								}
							}
					    }
					
					}
					
					sb.append("</tbody>\n")
					  .append("</table>\n") ;
	// 이행사항 종료
					}
					sb.append("	<div class=\"mt5\"></div>\n")
				      .append("	<div class=\"title_basic\">\n")
				      //체결정보
				      .append("	<h4>" + titleIndex + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew40", null, locale1) +"</h4>\n")
				      .append(" </div>")
				      .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
				      .append("	<colgroup>\n")
				      .append("		<col width=\"12%\" />\n")
				      .append("		<col width=\"23%\" />\n")
				      .append("		<col width=\"23%\" />\n")
				      .append("		<col width=\"12%\" />\n")
				      .append("		<col width=\"15%\" />\n")
				      .append("		<col width=\"15%\" />\n")
				      .append("	</colgroup>\n")
				      .append("		<tr>\n")
				      //직인서명구분
				      .append("			<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew41", null, locale1) +"</th>\n");
				    if("C02101".equals((String)lomDetail.get("seal_mthd"))) {
				    	//사용인감날인
				    	sb.append("	<td colspan=\"4\">" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew42", null, locale1) +"</td>\n");
				    }else if("C02103".equals((String)lomDetail.get("seal_mthd"))){
				    	//법인인감날인
				    	sb.append("	<td colspan=\"4\">" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew43", null, locale1) +"</td>\n");
				    } else {
				    	//서명
				    	sb.append("	<td colspan=\"4\">" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew44", null, locale1) +"</td>\n");
				    }
				      sb.append("		</tr>\n");
				      sb.append("   	<tr>\n");
				      
				    if("C02101".equals((String)lomDetail.get("seal_mthd")) || "C02103".equals((String)lomDetail.get("seal_mthd"))) {
				    	//날인담당자
				    	sb.append("<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew46", null, locale1) +"</th>")
				          .append("<td colspan=\"2\">" + StringUtil.bvl((String)lomDetail.get("seal_ffmtman_nm"), "") + "/" + StringUtil.bvl((String)lomDetail.get("seal_ffmtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)lomDetail.get("seal_ffmt_dept_nm"), "") + "</td>\n");
				    } else {
				    	//서명예정자
				    	sb.append("<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew47", null, locale1) +"</th>")
				    	  .append("<td colspan=\"2\">" + StringUtil.bvl((String)lomDetail.get("sign_plndman_nm"), "") + "/" + StringUtil.bvl((String)lomDetail.get("sign_plndman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)lomDetail.get("sign_plnd_dept_nm"), "") + "</td>\n");
				    }
				    //체결예정일
				    sb.append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew48", null, locale1) +"</th>\n")
				      .append("		<td colspan=\"2\">" + lomDetail.get("cnclsn_plndday") + "</td>\n")
				  	  .append("</tr>\n")
				  	  .append("	<tr>\n")
				  	  //계약담당자
				  	  .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew49", null, locale1) +"</th>\n")
				  	  .append("		<td colspan=\"5\">" + StringUtil.bvl((String)lomDetail.get("cntrt_respman_nm"), "") + "/" + StringUtil.bvl((String)lomDetail.get("cntrt_respman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)lomDetail.get("cntrt_resp_dept_nm"), "") + "</td>\n")
				  	  .append("	</tr>\n")
				  	  .append("</table>\n");
				    
				    
				    // 필수 항목 체크 결과 값 15개 항목 시작
				   // if("T030705".equals(StringUtil.bvl(lomDetail.get("cnclsnpurps_midclsfcn_nm"),"")) || "T030706".equals(StringUtil.bvl(lomDetail.get("cnclsnpurps_midclsfcn_nm"),"")) ){
				   
				    // 값이 없으면 없는데로 표시 하면 된다.
				    sb.append("	<div class=\"mt5\"></div>\n")
				      .append("	<div class=\"title_basic\">\n")
				      .append("	<h4>" + (String)messageSource.getMessage("las.page.field.hqrequest.page05", null, locale1) +"</h4>\n")
				      .append(" </div>")
				      .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
				      .append("	<colgroup>\n")
				      .append("		<col width=\"5%\" />\n")
				      .append("		<col width=\"20%\" />\n")
				      .append("		<col width=\"*%\" />\n")
				      .append("	</colgroup>\n")
				      .append(" <tr>\n")
					  .append("	<th  class='tC'> No.</th>\n")
					  .append("	<th  class='tC'>" + (String)messageSource.getMessage("las.page.field.hqrequest.page12", null, locale1) +"</th>\n")
					  .append("	<th  class='tC'>" + (String)messageSource.getMessage("las.page.field.hqrequest.page13", null, locale1) +"</th>\n")
					  .append("</tr>\n");
				    
				    int x = 1;
				    
    				// 필수 항목 체크 내용 - 15개				
					List chekItemList = consultationService.listCheckListAppl(vo);
				    
				    if(chekItemList.size() > 0){
						ListOrderedMap chk_lom = new ListOrderedMap();
						for(int j = 0 ; j < chekItemList.size() ; j++){
							chk_lom = (ListOrderedMap) chekItemList.get(j);	
							
							sb.append(" <tr>\n")
							  .append("<td class='tC'>" + x++  +"</td>")
							  .append("<td>"+StringUtil.convertEnterToBR((String)chk_lom.get("cd_nm"))+"</td>")
							  .append("<td>"+StringUtil.convertEnterToBR((String)chk_lom.get("REMARK"))+"</td>")
							  .append("</tr>\n");
							
						}
					} else {
						sb.append(" <tr>\n")
						  .append("<td colspan='3' align='center' class='tC'>"+(String)messageSource.getMessage("las.msg.succ.noResult", null, locale1)+"</td>")
						  .append("</tr>\n");
					}
   
				    sb.append("</table>\n");
				  // }
				    // 필수 항목 체크 결과 값 15개 항목 종료
				    
				    
				    sb.append("	<div class=\"mt5\"></div>\n")
				      .append("	<div class=\"title_basic\">\n")
				      //첨부파일
				      .append("	<h4>" + titleIndex + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew50", null, locale1) +"</h4>\n")
				      .append(" </div>")
				      .append("	<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table-style01\">\n")
				      .append("	<colgroup>\n")
				      .append("		<col width=\"14%\" />\n")
				      .append("		<col />\n")
				      .append("	</colgroup>\n")
				      .append(" <tr>\n")
				      //계약서
				      .append("		<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew51", null, locale1) +"</th>\n");
				      if(listContractAttach != null && listContractAttach.size() > 0) {
				    	  tempLom = (ListOrderedMap)listContractAttach.get(0);
				    	  sb.append("<td><a href=\"" + attachinfo + (String)tempLom.get("file_id")+ "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				      } else {
						sb.append("");
				      }
				    sb.append("</tr>\n");
				    
				    if(listEtc2Attach != null && listEtc2Attach.size() > 0) {
				    	for(int k=0; k < listEtc2Attach.size(); k++) {
				    		tempLom = (ListOrderedMap)listEtc2Attach.get(k);
				    		sb.append("<tr>\n");
				    		if(k==0) {
				    			if(listEtc2Attach.size()==1) {
				    				//첨부/별첨
				    				sb.append("<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew52", null, locale1) +"</td>\n");
				    			} else {
				    				//첨부/별첨
				    				sb.append("<th rowspan=\"" + listEtc2Attach.size()+ "\">" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew52", null, locale1) +"</td>\n");
				    			}
				    			sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				    		} else {
				    			sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				    		}
				    		sb.append("</tr>\n");
				    	}
				    }
				    
				    if(listPreAttach != null && listPreAttach.size() > 0) {
				    	for(int k=0; k < listPreAttach.size(); k++) {
				    		tempLom = (ListOrderedMap)listPreAttach.get(k);
				    		sb.append("<tr>\n");
				    		if(k==0) {
				    			if(listPreAttach.size()==1) {
				    				//사전승인
				    				sb.append("<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew53", null, locale1) +"</td>\n");
				    			} else {
				    				//사전승인
				    				sb.append("<th rowspan=\"" + listPreAttach.size() + "\">" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew53", null, locale1) +"</td>\n");
				    			}
					    		sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				    		} else {
				    			sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				    		}
				    		sb.append("</tr>\n");
				    	}
				    }
				    
				    if(listEtcAttach != null && listEtcAttach.size() > 0) {
				    	for(int k=0; k < listEtcAttach.size(); k++) {
				    		tempLom = (ListOrderedMap)listEtcAttach.get(k);
				    		sb.append("<tr>\n");
				    		if(k==0) {
				    			if(listEtcAttach.size()==1) {
				    				//기타
				    				sb.append("<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew54", null, locale1) +"</th>\n");
				    			} else {
				    				//기타
				    				sb.append("<th rowspan=\"" + listEtcAttach.size()+ "\">" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew54", null, locale1) +"</th>\n");
				    			}
				    			sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				    		} else {
				    			sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				    		}
				    		sb.append("</tr>\n");
				    	}
				    } 
				    
				    if(listPriceAttach != null && listPriceAttach.size() > 0) {
				    	for(int k=0; k < listPriceAttach.size(); k++) {
				    		tempLom = (ListOrderedMap)listPriceAttach.get(k);
				    		sb.append("<tr>\n");
				    		if(k==0) {
				    			if(listPriceAttach.size()==1) {
				    				//단가정보
				    				sb.append("<th>" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew53", null, locale1) +"</th>\n");
				    			} else {
				    				//단가정보
				    				sb.append("<th rowspan=\"" + listPriceAttach.size()+ "\">" + (String)messageSource.getMessage("clm.page.field.approval.makeApprovalContractInfoNew53", null, locale1) +"</th>\n");
				    			}
				    			sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				    		} else {
				    			sb.append("<td class=\"tal_lineL\"><a href=\"" + attachinfo + (String)tempLom.get("file_id") + "\">" + (String)tempLom.get("org_file_nm") + "</a></td>\n");
				    		}
				    		sb.append("</tr>\n");
				    	}
				    }
				    sb.append("</table>\n");
				}	
			}	
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}	
	}
	
	/**
	 * 품의작성상신 후 상태변경
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView modifyConsultationStatusNew(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String errLine  = "";
		try {
			HttpSession session = request.getSession(false);
			
			String forwardURL = "/clm/manage/consultation.do?method=detailConsultationNew";
		
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			ModelAndView mav = new ModelAndView();
			
			int result = consultationService.modifyConsultationStatus(vo);
			
			String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
			
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error errLine:" + errLine);
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error2 errLine:" + errLine);
		}
	}
	
	/**
	 * 계약담당자 타 사업부로 지정되지 못하게 막는 기능 구현내용 수정
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public void getOrgnzCd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String errLine  = "";
		try {
			HttpSession session = request.getSession(false);
			String forwardURL = "/WEB-INF/jsp/clm/manage/Consultation_d_new.jsp";
		
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
					
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			JSONObject jo = new JSONObject();
			String orgnzCd = esbOrgService.getOrgnzCd(vo.getCntrt_resp_dept());		
		
			jo.put("orgnzCd", orgnzCd);		
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		}catch (Exception e) {
		
			e.printStackTrace();
			JSONObject jo = new JSONObject();
    		jo.put("errorMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
		
			JSONObject jo = new JSONObject();
    		jo.put("errorMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} 
	}
}