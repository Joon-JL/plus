package com.sec.clm.manage.control;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;
import com.sds.secframework.auth.dto.AuthForm;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.singleIF.dto.ApprovalVO;
import com.sds.secframework.singleIF.service.EsbApprovalService;
import com.sds.secframework.util.service.ComUtilService;
import com.sec.clm.manage.dto.ConsultationForm;
import com.sec.clm.manage.dto.ConsultationSpecialForm;
import com.sec.clm.manage.dto.ConsultationSpecialVO;
import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.manage.dto.ExecutionForm;
import com.sec.clm.manage.dto.ExecutionVO;
import com.sec.clm.manage.dto.MyApprovalForm;
import com.sec.clm.manage.dto.MyApprovalVO;
import com.sec.clm.manage.service.ConsiderationService;
import com.sec.clm.manage.service.ConsultationService;
import com.sec.clm.manage.service.ExecutionService;
import com.sec.clm.manage.service.MyApprovalService;
import com.sec.common.clmscom.service.CLMSCommonService;
import com.sec.common.util.ClmsDataUtil;
/**
 * 계약관리>My Approval>체결품의 
 * @author HP
 *
 */
public class MyApprovalController extends CommonController {
	
	private ManageController manageControl;
	
	public void setManageControl(ManageController manageControl) {
		this.manageControl = manageControl;
	}
	
	private MyApprovalService myApprovalService;
	
	public void setMyApprovalService(MyApprovalService myApprovalService) {
		this.myApprovalService = myApprovalService;
	}
	
	//계약의뢰서비스
	private ConsiderationService considerationService;
	
	public void setConsiderationService(ConsiderationService considerationService) {
		this.considerationService = considerationService;
	}
	
	//계약체결서비스
	private ConsultationService consultationService;
	
	public void setConsultationService(ConsultationService consultationService) {
		this.consultationService = consultationService;
	}
	
	//결재공통서비스
	private EsbApprovalService esbApprovalService;
	
	public void setEsbApprovalService(EsbApprovalService esbApprovalService) {
		this.esbApprovalService = esbApprovalService;
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
	public void setClmsComService(CLMSCommonService clmsComService){
		this.clmsComService = clmsComService;
	}
	
	private ExecutionService executionService;
	public void setExecutionService(ExecutionService executionService){
		this.executionService = executionService;
	}
	/**
	 * MyApproval 목록 조회
	 * 공통목록 : 신남원 : 2011.10.04
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView listMyApproval(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ModelAndView mav = new ModelAndView();
			request.setAttribute("status_mode", "myApproval");
			mav = manageControl.listManage(request, response);

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
	public ModelAndView modifyMyApproval(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "/clm/manage/myApproval.do?method=detailMyApproval&list_mode=cnsdreq";
			ConsultationForm form	= new ConsultationForm();
			ConsultationVO	 vo		= new ConsultationVO();
			
			MyApprovalForm myForm 	= new MyApprovalForm();
			MyApprovalVO   myVo 	= new MyApprovalVO();
			
			ApprovalVO	approvalVo	= new ApprovalVO();
			
			bind(request, form);
			bind(request, vo);
			bind(request, myForm);
			bind(request, myVo);
			bind(request, approvalVo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			COMUtil.getUserAuditInfo(request, myForm);
			COMUtil.getUserAuditInfo(request, myVo);
			
			COMUtil.getUserAuditInfo(request, approvalVo);
			
			ModelAndView mav = new ModelAndView();
			String returnMessage = "";
			
			int result = -1;
			
			//C02636 - 체결후등록
			//C02642 - 원본미등록
			if("C02642".equals(myVo.getDepth_status()) || "C02636".equals(myVo.getDepth_status())){
				result = myApprovalService.modifyMyApproval(myVo);
			}
			
			//C02662 계약 이행 JOON 02/Apr/2014
			if ("C02642".equals(myVo.getDepth_status()) && result > 0 ) {
				forwardURL = "/clm/manage/execution.do?method=listContract";					
			}
		
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
							
			mav.addObject("command", form);
			mav.addObject("myCommand", myForm);
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
	
	public ModelAndView detailMyApproval(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			HttpSession session 		= request.getSession(false);
			String forwardURL 			= "/WEB-INF/jsp/clm/manage/MyApproval_d_view.jsp";
			List resultList	 			= null;
			List authReqList 			= null;//권한요청자 -관련자
			String user_role			= "";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			MyApprovalForm form = new MyApprovalForm();
			MyApprovalVO vo 	= new MyApprovalVO();	
			
			ConsultationVO	consultationVo	= new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			bind(request, consultationVo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			COMUtil.getUserAuditInfo(request, consultationVo);
			
			//계약변경이면
			if("Y".equals(form.getIsOrgMgr())){
				forwardURL 			= "/WEB-INF/jsp/clm/manage/OrgMgr_d_view.jsp";
			}
			else{
				if("modify".equals(form.getPage_gbn())) {
					forwardURL 			= "/WEB-INF/jsp/clm/manage/MyApproval_d.jsp";
				}
			}
			
			ModelAndView mav = new ModelAndView();
			List tmpSessionRoleList = (List)session.getAttribute("secfw.session.role") ;

			for (int i = 0; i < tmpSessionRoleList.size(); i++) {
				Map tmpSessionRoleMap = (Map) tmpSessionRoleList.get(i);
				if (tmpSessionRoleMap.containsValue("RD01")) {
					user_role = "RD01";
					break;
				}
			}
			form.setUser_role(user_role);
			form.setUser_orgnz(vo.getSession_blngt_orgnz());
			
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			resultList = myApprovalService.detailMyApproval(vo);
			List dcList = consultationService.detailConsultation(consultationVo);
			List result_dcList = new ArrayList();
			ListOrderedMap cntrtLom = null;
			
			if(dcList != null){
				for(int i = 0 ; i < dcList.size() ; i++){
					cntrtLom = (ListOrderedMap) dcList.get(i);
					result_dcList.add(dcList.get(i));
				}
				if(result_dcList != null && result_dcList.size() > 0){
					cntrtLom = (ListOrderedMap) result_dcList.get(0);
				}
			}
			
			form.setCntrt_id((String)cntrtLom.get("cntrt_id"));
			
			ListOrderedMap lomReq 	= new ListOrderedMap();
			String submitInfo 		= "";
			String approvalInfo 	= "";
			String agreeInfo		= "";
			StringBuffer etcInfo	= new StringBuffer();
			int k=0;
			for(int i=0; i < resultList.size();i++) {
				ListOrderedMap tempReq = (ListOrderedMap)resultList.get(i);
				if(i==0) {
					lomReq.put("approval_title",tempReq.get("title"));
					lomReq.put("reqman_nm",		tempReq.get("reqman_nm"));
					lomReq.put("req_dt", 		tempReq.get("req_dt"));
					lomReq.put("agreeman_id", 	tempReq.get("agreeman_id"));
					lomReq.put("agreeman_nm",	tempReq.get("agreeman_nm"));
					lomReq.put("agree_cause", 	tempReq.get("agree_cause"));
					lomReq.put("agree_yn", 		tempReq.get("agree_yn"));
					lomReq.put("agreeday", 		tempReq.get("agreeday"));
					lomReq.put("module_id", 	tempReq.get("module_id"));
					lomReq.put("mis_id", 		tempReq.get("mis_id"));
					lomReq.put("cnsdreq_id", 	tempReq.get("cnsdreq_id"));
					lomReq.put("create_date", 	tempReq.get("create_date"));
					lomReq.put("prgrs_status",  tempReq.get("prgrs_status"));
				}
				
				if(tempReq.get("activity")!=null && ((String)tempReq.get("activity")).equals("0")) {
					submitInfo = (String)tempReq.get("user_info");
				} else if(tempReq.get("activity")!=null && ((String)tempReq.get("activity")).equals("1")) {
					approvalInfo = (String)tempReq.get("user_info");
				} else if(tempReq.get("activity")!=null && ((String)tempReq.get("activity")).equals("2")) {
					agreeInfo	= (String)tempReq.get("user_info");
				} else {
					if(k==0) {
						etcInfo.append((String)tempReq.get("user_info"));
					} else {
						etcInfo.append(", ");
						etcInfo.append((String)tempReq.get("userinfo"));
					}
					k++;
				}
			}
			
			lomReq.put("submitinfo", submitInfo);
			lomReq.put("approvalinfo", approvalInfo);
			lomReq.put("agreeinfo",agreeInfo);
			lomReq.put("etcinfo", etcInfo.toString());
			
			consultationVo.setCntrt_id((String)cntrtLom.get("cntrt_id"));
			
			authReqList = considerationService.listContractAuthreq(consultationVo);//권한요청자 -관련자
			StringBuffer reqAuthInfo	= new StringBuffer();
			
			if(authReqList != null && authReqList.size() > 0) {
				for(int i=0; i < authReqList.size(); i++) {
					ListOrderedMap authLom	= (ListOrderedMap)authReqList.get(i);
					if(i > 0){
						reqAuthInfo.append(", " + StringUtil.bvl((String)authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_dept_nm"), "")); 
					} else {
						reqAuthInfo.append(StringUtil.bvl((String)authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_dept_nm"), ""));
					}
				}
			}
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("cntrtLom", cntrtLom);
			mav.addObject("lomReq", lomReq);
			mav.addObject("lomMaster", result_dcList);
			if(reqAuthInfo != null && !"".equals(reqAuthInfo.toString())) {
				mav.addObject("reqAuthInfo", reqAuthInfo.toString());
			} else {
				mav.addObject("reqAuthInfo", "");
			}
			mav.addObject("resulList", resultList);
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
	 * 계약마스터정보조회
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView detailMyApprovalContractMaster(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "/WEB-INF/jsp/clm/manage/MyApproval_d_master.jsp";
			List resultList = null;
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			MyApprovalForm contractForm = new MyApprovalForm();
			MyApprovalVO contractVo 	= new MyApprovalVO();	
			
			bind(request, contractForm);
			bind(request, contractVo);
			
			//계약변경 메뉴인 경우 forwardURL 세팅 2012-07-19
			if("Y".equals(contractForm.getIsOrgMgr())){
				forwardURL = "/WEB-INF/jsp/clm/manage/OrgMgr_d_master.jsp";
				
				//가장 최근 이력을 가져오기 위해 MAX SEQ_NO을 SET
				if(contractVo.getSeqno() < 1){
					contractVo.setSeqno(myApprovalService.getOrgMngHisMaxSeqNo(contractVo));
				}
			}
			
			COMUtil.getUserAuditInfo(request, contractForm);
			COMUtil.getUserAuditInfo(request, contractVo);
			
			ModelAndView mav = new ModelAndView();
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			resultList = myApprovalService.detailMyApprovalContractMaster(contractVo);
			ArrayList contractList = null;
			ArrayList contractCnclsndlayList = null;
			ArrayList contractReqList	= null;
			ArrayList orgMngList = null;
			ArrayList specialList = null;
			ArrayList relationList = null;
			ArrayList special1List = new ArrayList();
			ArrayList special2List = new ArrayList();
			ListOrderedMap contractLom	= null;
			ListOrderedMap contractReqLom	= null;
			ListOrderedMap contractCnclsndlayLom = null;
			ListOrderedMap orgMngLom	= null;
			ListOrderedMap relationLom = null;
			  
			if(resultList != null && resultList.size() > 0 ) {
				contractList = (ArrayList)resultList.get(0);
				contractCnclsndlayList = (ArrayList)resultList.get(1);
				contractReqList = (ArrayList)resultList.get(2);
									
				orgMngList = (ArrayList)resultList.get(3);
				specialList = (ArrayList)resultList.get(4);
				relationList = (ArrayList)resultList.get(5);
			}
			
			if(contractList != null && contractList.size() > 0){
				contractLom = (ListOrderedMap)contractList.get(0);
			}
			
			if(contractCnclsndlayList != null && contractCnclsndlayList.size() > 0){
				contractCnclsndlayLom = (ListOrderedMap)contractCnclsndlayList.get(0);
			}

			//2012-07-17 의뢰정보
			if(contractReqList != null && contractReqList.size() > 0){
				contractReqLom = (ListOrderedMap)contractReqList.get(0);
			}
			
			// 2012-07-20 관리이력
			if (orgMngList != null && orgMngList.size() > 0) {
				orgMngLom = (ListOrderedMap) orgMngList.get(0);
			}
			
			if(contractLom!=null && contractLom.size() >0 && "modify".equals(contractForm.getPage_gbn())){
				contractLom = (ListOrderedMap)contractList.get(0);
				contractLom.put("org_acpt_dlay_cause", StringUtil.replace((String)contractLom.get("org_acpt_dlay_cause"), "<br>", "\r\n"));
			}
			

			//특화정보
			if (specialList.size() > 0 && specialList != null) {
				for (int i = 0; i < specialList.size(); i++) {
					ListOrderedMap lom = (ListOrderedMap) specialList.get(i);
					if ("C04002".equals(lom.get("crtn_depth"))) {
						special1List.add(specialList.get(i));
					} else if ("C04005".equals(lom.get("crtn_depth"))) {
						special2List.add(specialList.get(i));
					}
				}
			}

			//연관계약정보
			if (relationList.size() > 0 && relationList != null) {
				relationLom = (ListOrderedMap) relationList.get(0);
			}

			List listCa = null;
			listCa = considerationService.listContractAuthreq(contractVo);//권한요청자 -관련자 
			
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("contractCnclsndlayLom", contractCnclsndlayLom);
			mav.addObject("contractLom", contractLom);
			mav.addObject("contractReqLom", contractReqLom);
			mav.addObject("orgMngLom", orgMngLom);
			mav.addObject("contractCommand", contractForm);
			mav.addObject("considerationSpecialList", special1List);
			mav.addObject("consultationSpecialList" , special2List);
			mav.addObject("contractRelationList", relationList);
			mav.addObject("contractRelationLom", relationLom);
			mav.addObject("listCa", listCa); //관련자

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
	 * 특화정보조회
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */	
	public ModelAndView listMyApprovalSpecial(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL = "/WEB-INF/jsp/clm/manage/MyApproval_d_special.jsp";
			String returnMessage 				= "";
			
			MyApprovalForm form       			= new MyApprovalForm();
			MyApprovalVO   vo         			= new MyApprovalVO();
			ConsultationSpecialForm specialForm = new ConsultationSpecialForm();
			ConsultationSpecialVO   specialVO   = new ConsultationSpecialVO();
			List   		resultList 				= null;
	
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/			
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
	 * 이행정보조회
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView listMyApprovalExe(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    			= "";
			String returnMessage 			= "";
			String payment_gbn	 			= "";
			MyApprovalForm 	myApprovalForm	= null;
			ExecutionForm form       		= null;
			ExecutionVO   vo         		= null;
			PageUtil 	pageUtil   			= null;
			List   		resultList 			= null;
			
			/*********************************************************
			 * Forward setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/manage/MyApproval_d_exe.jsp";	
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new ExecutionForm();
			vo = new ExecutionVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			myApprovalForm 	= new MyApprovalForm();
			bind(request, myApprovalForm);
			COMUtil.getUserAuditInfo(request, myApprovalForm);
			
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

				pageUtil.setTotalRow(tmp.get("total_cnt"));
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
			mav.addObject("command", myApprovalForm);
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
	
	/**
	 * 연관계약
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */	
	public ModelAndView listMyApprovalRelation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL = "/WEB-INF/jsp/clm/manage/MyApproval_d_relation.jsp";
			String returnMessage 				= "";
			MyApprovalForm form       			= new MyApprovalForm();
			MyApprovalVO   vo         			= new MyApprovalVO();
			ConsultationForm specialForm = new ConsultationForm();
			ConsultationVO   specialVO   = new ConsultationVO();
			List   		resultList 				= null;
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/			
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
			resultList = consultationService.listConsultationRealtion(specialVO);
			ListOrderedMap lom = null;
			if(resultList.size() > 0 && resultList != null) {
				lom = (ListOrderedMap)resultList.get(0);
			}
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("command", form);
			mav.addObject("relationList", resultList);
			mav.addObject("relationListSize", resultList.size());
			mav.addObject("relationLom", lom);
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
	 * 검토이력조회
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView listMyApprovalHis(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL = "/WEB-INF/jsp/clm/manage/OrgMgr_d_his.jsp";
			MyApprovalForm form       = new MyApprovalForm();
			MyApprovalVO   vo         = new MyApprovalVO();
			List	resultList = null;
			ArrayList contractList = null;
			ArrayList contractCnclsndlayList = null;
			ArrayList orgMngHisList = null;
			ArrayList orgMngList = null;
			
			ListOrderedMap contractLom = null;
			ListOrderedMap contractCnclsndlayLom = null;
			ListOrderedMap orgMngLom = null;
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			
			bind(request, form);
			bind(request, vo);
			
			//가장 최근 이력을 가져오기 위해 MAX SEQ_NO을 SET				
			if(vo.getSeqno() < 1){
				vo.setSeqno(myApprovalService.getOrgMngHisMaxSeqNo(vo));
			}
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			ModelAndView mav = new ModelAndView();
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = myApprovalService.listMyApprovalHis(vo);
			
			if (resultList != null && resultList.size() > 0) {
				contractList = (ArrayList)resultList.get(0);
				contractCnclsndlayList = (ArrayList)resultList.get(1);
				orgMngHisList = (ArrayList)resultList.get(2);
				orgMngList = (ArrayList)resultList.get(3);
			}
			
			if(contractList != null & contractList.size() > 0){
				contractLom = (ListOrderedMap) contractList.get(0);
			}
			
			if(contractCnclsndlayList != null & contractCnclsndlayList.size() > 0){
				contractCnclsndlayLom = (ListOrderedMap) contractCnclsndlayList.get(0);
			}
			
			if(orgMngList != null & orgMngList.size() > 0){
				orgMngLom = (ListOrderedMap) orgMngList.get(0);
			}
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("contractLom", contractLom);
			mav.addObject("contractCnclsndlayLom",contractCnclsndlayLom);
			mav.addObject("orgMngHisList", orgMngHisList);
			mav.addObject("orgMngLom", orgMngLom);
			mav.addObject("contractCommand", form);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("listMyApprovalHis() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("listMyApprovalHis() Throwable !!");
		}
	}
	
	
	/**
	 * 표준계약서 승인 목록 조회
	 * 공통목록 : 이민우 : 2012.07.16
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView listStdContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ModelAndView mav = new ModelAndView();
			request.setAttribute("status_mode", "stdContract");
		
			mav = manageControl.listManage(request, response);

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
	 * 표준계약서 상세 조회
	 * 공통목록 : 이민우 : 2012.07.16
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView detailStdContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "";
			List resultList = null;
			ListOrderedMap lomCh = new ListOrderedMap();
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ConsultationForm form = new ConsultationForm();
			ConsultationVO vo = new ConsultationVO();	
			ModelAndView mav = new ModelAndView();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			form.setPageGbn(StringUtil.nvl((String)form.getPageGbn(),""));
			
			forwardURL = "/WEB-INF/jsp/clm/manage/ManageStdContract_d.jsp";	
			
			/*********************************************************
			 * Service++
			**********************************************************/
			List listDc = null;
			
			if("registration".equals(form.getPageGbn())){
				listDc = considerationService.detailConsiderationReg(vo);
			} else {
				listDc = considerationService.detailConsideration(vo);	
			}	
			
			ListOrderedMap lomRq= (ListOrderedMap)listDc.get(0);
			
			//나모 잔여 태그 제거			
			lomRq.put("cnsd_demnd_cont", StringUtil.convertNamoReplaceAll(StringUtil.bvl((String)lomRq.get("cnsd_demnd_cont"), "")));			
			lomRq.put("total_cnt", Integer.toString(listDc.size()));
			
			//의뢰테이블에 조인된 마스터 테이블에 계약1번 데이타 
			vo.setCntrt_id((String)lomRq.get("cntrt_id"));
			vo.setCnclsnpurps_bigclsfcn((String)lomRq.get("cnclsnpurps_bigclsfcn"));
			vo.setCnclsnpurps_midclsfcn((String)lomRq.get("cnclsnpurps_midclsfcn"));
			
			ArrayList deferList = new ArrayList();
			
			String arr_cntrt_id[] = new String[listDc.size()];
			for(int j=0; j<listDc.size(); j++){
				int k =j+1;
				
				ListOrderedMap lomRq_cntrt_id= (ListOrderedMap)listDc.get(j);
				ClmsDataUtil.debug("(String)lomRq_cntrt_id.get()  : "+(String)lomRq_cntrt_id.get("cntrt_id"));
				arr_cntrt_id[j] = (String)lomRq_cntrt_id.get("cntrt_id");
				vo.setArr_cntrt_id(arr_cntrt_id);
				vo.setStatus(Integer.toString(k));
				
				//보류 / 보류 해제 버튼 활성화을 위한  
				deferList.add(lomRq_cntrt_id.get("depth_status"));				
			}
			
			if(deferList.contains("C02607")){ 	//보류상태
				lomRq.put("defer_btn", "cancel");
			}else{
				lomRq.put("defer_btn","defer");
			}
			
			ClmsDataUtil.debug("arr_cntrt_idarr_cntrt_idarr_cntrt_id: "+arr_cntrt_id);
			
			vo.setStatus("consider");
			List listCa = considerationService.listContractAuthreq(vo);//권한요청자 -관련자 
			List listCh = considerationService.listCnsdreqHold(vo); //보류사유
			if(!listCh.isEmpty()){
				lomCh= (ListOrderedMap)listCh.get(0);
				lomCh.put("hold_cause", StringUtil.convertEnterToBR((String)lomCh.get("hold_cause")));
			}
			
			//변경사항 
			lomRq.put("plndbn_chge_cont", StringUtil.convertEnterToBR(StringUtil.nvl((String)lomRq.get("plndbn_chge_cont"),"")));		
			
			//최최 의뢰,재의뢰 , 최종본 의뢰 상태 설정
			// 2012.03.07 타이틀을 목록의 상태명으로 나오도록 수정 added by hanjihoon
			form.setReq_status_title(considerationService.getReqStatus((String)lomRq.get("prev_cnsdreq_id"),(String)lomRq.get("plndbn_req_yn"), vo));
			
			/************************************************** 
			 * 계약정보 상세
			 * 기존 detailContractMaster() 메소드에 있는 정보
			 **************************************************/
			
			/******* 1. 첫번째 계약ID의 계약 상세 조회한다. *******/
			List listDc2 = considerationService.detailContractMaster(vo);
			
			ListOrderedMap lomRq2= (ListOrderedMap)listDc2.get(0);
			
			//나모 잔여 태그 제거			
			lomRq2.put("cnsd_demnd_cont", StringUtil.convertNamoReplaceAll(StringUtil.bvl((String)lomRq2.get("cnsd_demnd_cont"),"")));
			
			//기대효과  <BR>antcptnefctANTCPTNEFCT
			lomRq2.put("antcptnefct", StringUtil.convertEnterToBR((String)lomRq2.get("antcptnefct")));
			//지불/수분조건 <BR>PAYMENT_COND
			lomRq2.put("payment_cond", StringUtil.convertEnterToBR((String)lomRq2.get("payment_cond")));
			//기타주요사항 <BR>ETC_MAIN_CONT
			lomRq2.put("etc_main_cont", StringUtil.convertEnterToBR((String)lomRq2.get("etc_main_cont")));			
			//
			lomRq2.put("cntrt_chge_demnd_cause", StringUtil.convertEnterToBR((String)lomRq2.get("cntrt_chge_demnd_cause")));
			//단가내역 요약cntrt_untprc_expl
			lomRq2.put("cntrt_untprc_expl", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq2.get("cntrt_untprc_expl"),"")));			
			//책임한도   oblgt_lmt
			lomRq2.put("oblgt_lmt", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq2.get("oblgt_lmt"),"")));				
			//Special Condition  spcl_cond
			lomRq2.put("spcl_cond", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq2.get("spcl_cond"),"")));			
			//loac_etc 준거법 상세
			lomRq2.put("loac_etc", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq2.get("loac_etc"),"")));
			//dspt_resolt_mthd_det 분쟁해결방법상세 
			lomRq2.put("dspt_resolt_mthd_det", StringUtil.convertEnterToBR(StringUtil.bvl((String)lomRq2.get("dspt_resolt_mthd_det"),"")));
			//추진목적 
			lomRq2.put("pshdbkgrnd_purps", StringUtil.convertNamoReplaceAll(StringUtil.bvl((String)lomRq2.get("pshdbkgrnd_purps"),"")));
			
			//종합검토의견
			if(lomRq2.get("cnsd_opnn") != null){
			lomRq2.put("cnsd_opnn", StringUtil.convertNamoReplaceAll(StringUtil.bvl((String)lomRq2.get("cnsd_opnn"),"")));
			}
			
			
			/******* 2. 특화정보를 조회한다. *******/
			vo.setCnclsnpurps_bigclsfcn((String)lomRq2.get("cnclsnpurps_bigclsfcn"));
			vo.setCnclsnpurps_midclsfcn((String)lomRq2.get("cnclsnpurps_midclsfcn"));
			
			vo.setPlndbn_req_yn((String)lomRq2.get("plndbn_req_yn"));
			vo.setCnsd_status((String)lomRq2.get("cnsd_status"));
			
			ClmsDataUtil.debug(">>>>>>>>>>>>>>"+vo.getPlndbn_req_yn());
			ClmsDataUtil.debug(">>>>>>>>>>>>>>"+vo.getCnsd_status());
			
			List listTs = considerationService.listTypeSpclinfo(vo);// TN_CLM_TYPE_SPCLINFO 특화정보
			
			//최초 의뢰,재의뢰 , 최종본 의뢰 상태 설정
			form.setReq_status_title(considerationService.getReqStatus((String)lomRq2.get("prev_cnsdreq_id"),(String)lomRq2.get("plndbn_req_yn"), vo));		
			form.setPlndbn_req_yn(vo.getPlndbn_req_yn());
			
			ListOrderedMap returnLom= considerationService.getFilevalidate(vo);
			form.setFile_validate((String)returnLom.get("fileValidate"));
			
			/************************************************** 
			 * 연관 계약 정보
			 * 기존 listRelationContract() 메소드에 있는 정보
			 **************************************************/
			//계약 id 전달
			vo.setCntrt_id((String)lomRq.get("cntrt_id")) ;
			vo.setParent_cntrt_id(StringUtil.nvl(vo.getParent_cntrt_id(), "")) ;
			vo.setSubmit_status("") ;
			HashMap contRcMap = considerationService.listRelationContract(vo);
			
			/************************************************** 
			 * 이력정보
			 * 기존 listContractHis() 메소드에 있는 정보
			 **************************************************/
			
			ArrayList review = null;
			ArrayList approve = null;
			ArrayList sign = null;
			ArrayList info = null;
			ArrayList defer = null;
			
			ExecutionVO execVo = new ExecutionVO() ;
			execVo.setCntrt_id((String)lomRq.get("cntrt_id")) ;
			execVo.setCnsdreq_id((String)lomRq.get("cnsdreq_id")) ;
			execVo.setSession_user_locale((String)vo.getSession_user_locale());
			resultList = considerationService.listHisExecution(execVo);
			
			if (resultList != null && resultList.size() > 0) {
				review = (ArrayList)resultList.get(0);
				approve = (ArrayList)resultList.get(1);
				sign = (ArrayList)resultList.get(2);
				info = (ArrayList)resultList.get(3);
				defer = (ArrayList)resultList.get(4);
				
				ClmsDataUtil.debug("#############################");
				ClmsDataUtil.debug("review : " + review.size());
				ClmsDataUtil.debug("approve : " +approve.size());
				ClmsDataUtil.debug("sign : " +sign.size());
				ClmsDataUtil.debug("info : " +info.size());
				ClmsDataUtil.debug("#############################");
			}
			//최종 건을 구하기 위한 maxrm 변수 세팅
			//회신 내용 구하기(가장 마지막 회신내용)
			int maxrm = 0;
			ListOrderedMap lomRe = null ;
			if(review != null){
				ListOrderedMap _lom = new ListOrderedMap();
				for(int i = 0 ; i < review.size() ; i++){
					_lom = (ListOrderedMap) review.get(i);
					
					if( maxrm < Integer.parseInt(String.valueOf(_lom.get("rm")))){
						maxrm = Integer.parseInt(String.valueOf(_lom.get("rm")));
					}
				}
				
				// 마지막 회신내용
				for(int i=review.size()-1; i>=0; i--) {
					lomRe = (ListOrderedMap) review.get(i) ;
					if("REPLY__".equals(lomRe.get("cr_flag"))){
						
						break ;
					}
				}
			}
			
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("lomRq", lomRq); // 의뢰 정보
			mav.addObject("lomRq2", lomRq2); // 계약 정보
			mav.addObject("lomRe", lomRe) ; // 회신 정보
			mav.addObject("contRc", contRcMap.get("contRc"));	//연관계약
			mav.addObject("cntRc", contRcMap.get("cntRc"));	//연관계약
			mav.addObject("listDc", listDc); // 의뢰 정보 LIST
			mav.addObject("listDc2", listDc2); // 계약 정보 LIST
			mav.addObject("listTs", listTs); // 특화 정보
			mav.addObject("listCa", listCa); // 관련자 정보
			mav.addObject("lomCh", lomCh);		//보류사유
			mav.addObject("maxrm", maxrm);
			mav.addObject("review", review);
			mav.addObject("approve", approve);
			mav.addObject("sign", sign);
			mav.addObject("info", info);
			mav.addObject("defer", defer);			
			
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
	 * 반려 페이지 포워드.
	 * 
	 * @param request
	 * @param response
	 * @return 포워딩정보 및 데이타를 담은 ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardPage(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			/*********************************************************
			 * Forwarding URL
			 **********************************************************/
			// 등록페이지로 Forwading
			String forwardURL = "";
		
			forwardURL = "/WEB-INF/jsp/clm/manage/ManageStdContract_p.jsp";
			
			//-----------BIND:바인드설정 -------------------- 
			AuthForm form = new AuthForm();
			bind(request, form);

			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("command", form);
			
			return mav;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception(t);
		}

	}
	
	/**
	 * 표준계약서 반려처리
	 * @param request
	 * @param response
	 * @return void
	 * @throws Exception
	 */
	public void rejctOpnn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			ConsultationForm form  	= null;	
			ConsultationVO   vo    	= null;
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new ConsultationForm();
			vo   = new ConsultationVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			
			JSONObject jo 		= new JSONObject();
			
			considerationService.rejctOpnn(vo);
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.succ.processEnd",  null, new RequestContext(request).getLocale()));
			
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Exception e) {
			JSONObject jo 		= new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out 	= response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
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
	 * 표준계약서 승인하기
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void ApprovalContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
			returnMap = considerationService.ApprovalStdContr(vo);
			
			ClmsDataUtil.debug("returnVal >>>" + returnMap.get("cd"));
    		jo.put("returnCd", returnMap.get("cd"));    		
    		jo.put("returnMsg", returnMap.get("msg"));    		
    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
    		
		} catch (Exception e) {
			JSONObject jo 		= new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out 	= response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
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
	 * 계약정보수정/원본출납관리 - 계약정보수정 버튼 눌렀을 시 호출
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView insertOrgMngHis(HttpServletRequest request, HttpServletResponse response) throws Exception {

		MyApprovalForm form = new MyApprovalForm();
		MyApprovalVO vo = new MyApprovalVO();
		ModelAndView mav = new ModelAndView();
		int result = 0;
		ListOrderedMap lom = new ListOrderedMap();

		bind(request, form);
		bind(request, vo);
		

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);
		
		//COMP_CD 추가 2013.04.17 박정훈!@#
		HttpSession session = request.getSession();		
		vo.setSession_auth_comp_cd(StringUtil.bvl((String)session.getAttribute("secfw.session.auth_comp_cd"), ""));
		 
		try {
			result = myApprovalService.insertOrgMngHis(vo);
			
			if (result > 0) {
			
			}
			else {
				throw new Exception();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("insertOrgMngHis() Throwable !!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("insertOrgMngHis() Throwable !!");
		}
		mav.addObject("return_message", form.getReturn_message());
		mav.addObject("command", form);
		return detailMyApproval(request, response);
	}
	
	/**
	 * 계약정보수정/원본출납관리 - 원본출납관리 버튼 눌렀을 시 호출
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView insertOrgMngHis2(HttpServletRequest request, HttpServletResponse response) throws Exception {

		MyApprovalForm form = new MyApprovalForm();
		MyApprovalVO vo = new MyApprovalVO();
		ModelAndView mav = new ModelAndView();
		int result = 0;
		
		bind(request, form);
		bind(request, vo);

		// 사용자 정보 세팅
		COMUtil.getUserAuditInfo(request, vo);
		COMUtil.getUserAuditInfo(request, form);

		try {
			result = myApprovalService.insertOrgMngHis2(vo);
			
			if (result > 0) {
			
			}
			else {
				throw new Exception();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("insertOrgMngHis() Throwable !!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("insertOrgMngHis() Throwable !!");
		}
		mav.addObject("return_message", form.getReturn_message());
		mav.addObject("command", form);
		return detailMyApproval(request, response);
	}
	
	/**
	 * 표지인쇄
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardPrintPop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "/WEB-INF/jsp/clm/manage/MyApproval_p.jsp";
			MyApprovalForm form = new MyApprovalForm();
			MyApprovalVO 	 vo   = new MyApprovalVO();
			ListOrderedMap lom = null;
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, vo);
			
			lom = myApprovalService.printMyApproval(vo);
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("command", form);
			mav.addObject("lom",lom);
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
}