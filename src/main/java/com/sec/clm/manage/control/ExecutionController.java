/**
 * Project Name : 이행정보
 * File name	: ExecutionController.java
 * Description	: 이행정보  Controller
 * Author		: 신승완
 * Date			: 2011. 09. 05
 * Copyright	:
 */
package com.sec.clm.manage.control;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.util.service.ComUtilService;
import com.sec.clm.manage.dto.ConsultationForm;
import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.manage.dto.ExecutionForm;
import com.sec.clm.manage.dto.ExecutionVO;
import com.sec.clm.manage.service.ConsiderationService;
import com.sec.clm.manage.service.ExecutionService;
import com.sec.common.clmscom.service.CLMSCommonService;

/**
 * Description	: 이행정보  Controller
 * Author 		: 신승완
 * Date			: 2011. 09. 05
 */
public class ExecutionController extends CommonController {
	
	private ManageController manageControl;
	
	public void setManageControl(ManageController manageControl) {
		this.manageControl = manageControl;
	}
	
	/**
	 * ExecutionService 서비스
	 */
	private ExecutionService executionService;
	
	/**
	 * ComUtil 서비스
	 */
	private ComUtilService comUtilService;
	
	
	/**
	 * ExecutionService 서비스 세팅
	 * @param executionService
	 * @return void
	 * @throws
	 */
	public void setExecutionService(ExecutionService executionService) {
		this.executionService = executionService;
	}
	
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
	
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private ConsiderationService considerationService;
	public void setConsiderationService(ConsiderationService considerationService) { 
		this.considerationService = considerationService;
	}
	
	/**
	* 계약이행 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listManageExecution(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		request.setAttribute("status_mode", "C02504");
		mav = manageControl.listManage(request, response);
		
		return mav; 
	}

	/**
	 * 계약정보 전체조회
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView listContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			ExecutionForm form       = null;
			ExecutionVO   vo         = null;
			List   		resultList = new ArrayList();
			List 		tempList = null;
			
			HttpSession session = null;
			
			ListOrderedMap initLom = null;
			ListOrderedMap tempLom = null;
			ArrayList lom = null;
			/*********************************************************
			 * Session Check
			**********************************************************/	
			session = request.getSession(false);
						
			/*********************************************************
			 * Forward setting
			**********************************************************/
			
			String pageGbn = (String)request.getParameter("pageGbn");
			
			if("registration".equals(pageGbn)){
				forwardURL = "/WEB-INF/jsp/clm/manage/RegExecution_l.jsp";
			}
			else{
				forwardURL = "/WEB-INF/jsp/clm/manage/Execution_l.jsp";
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
			
			String cntrt_no = null;
			String prcs_depth = null;
			
			/*********************************************************
			 * Service
			**********************************************************/
			tempList = executionService.listContract(vo);

			/*********************************************************
			 * Service
			 **********************************************************/

			if (tempList != null && tempList.size() > 0) {
				for (int i = 0; i < tempList.size(); i++) {
					tempLom = (ListOrderedMap) tempList.get(i);
					//체결후등록 -_-;;
					if("registration".equals(pageGbn)){
						resultList.add(tempList.get(i));
					}
					//이행
					else {
						if (tempLom.get("depth_status").equals("C02662")) {
							resultList.add(tempList.get(i));
						}
					}
				}
				
				if(resultList != null && resultList.size() > 0){
					initLom = (ListOrderedMap) resultList.get(0);
					form.setCnsdreq_id((String)initLom.get("cnsdreq_id"));
					form.setCntrt_id((String)initLom.get("cntrt_id"));
					form.setCnsd_dept((String)initLom.get("cnsd_dept"));
					form.setReqman_id((String)initLom.get("reqman_id"));
					cntrt_no = (String)initLom.get("cntrt_no");
					prcs_depth = (String)initLom.get("prcs_depth");				
				}
				lom = (ArrayList)resultList;
			}
			
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

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("contractLom", lom);
			mav.addObject("contractReqLom", initLom);
			mav.addObject("contractCommand", form);
			mav.addObject("command", form);

			mav.addObject("cntrt_no", cntrt_no);
			mav.addObject("prcs_depth", prcs_depth);			
			mav.addObject("ssems_oppnt_cd",StringUtil.bvl(request.getParameter("ssems_oppnt_cd"),""));//ssems인터페이스시 사용
		
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("listContract() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("listContract() Throwable !!");
		}
	}
	
	/**
	 * 계약정보상세조회
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView detailContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			ExecutionForm form       = null;
			ExecutionVO   vo         = null;
			List   		resultList = null;
			
			int td_cnt = 0;
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
						
			/*********************************************************
			 * Forward setting
			**********************************************************/
			
			String pageGbn =  (String)request.getParameter("pageGbn");
			
			if("registration".equals(pageGbn)){
				forwardURL = "/WEB-INF/jsp/clm/manage/RegExecution_mst.jsp";
			} else {
				forwardURL = "/WEB-INF/jsp/clm/manage/Execution_mst.jsp";
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
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = executionService.detailContract(vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
						
			ListOrderedMap lom = null;
			ListOrderedMap contractReqLom = null;
			ArrayList contractMstLom = null;
			ArrayList contractExeLom = null;
			ArrayList contractSpclLom = null;
			ArrayList contractCnclsndlayLom = null;
			ArrayList contractReqList = null;
			//연관계약정보
			ArrayList contractRelationLom = null;
			
			//특화정보
			ArrayList special1List = new ArrayList();
			ArrayList special2List = new ArrayList();		
			
			int relationListSize = 0;
			 
			if (resultList != null && resultList.size() > 0) {
				contractMstLom  = (ArrayList)resultList.get(0);
				contractExeLom  = (ArrayList)resultList.get(1);
				contractSpclLom	= (ArrayList)resultList.get(2);
				contractCnclsndlayLom = (ArrayList)resultList.get(3);
				contractRelationLom   = (ArrayList)resultList.get(4);
				contractReqList = (ArrayList)resultList.get(5);
				
				if(contractReqList!=null && contractReqList.size() > 0){
					contractReqLom = (ListOrderedMap)contractReqList.get(0);
				}
				
				if(contractRelationLom != null)
					relationListSize = contractRelationLom.size();
				 
				if(contractMstLom!=null && contractMstLom.size() >0 ){
					//master 정보
					lom = (ListOrderedMap)contractMstLom.get(0);
					lom.put("reqman_id", form.getReqman_id());
					//마스터 정보 에 개행 문자 <BR> 변화 
					//기대효과  <BR>antcptnefctANTCPTNEFCT
					lom.put("antcptnefct", StringUtil.convertEnterToBR((String)lom.get("antcptnefct")));
					//지불/수분조건 <BR>PAYMENT_COND
					lom.put("payment_cond", StringUtil.convertEnterToBR((String)lom.get("payment_cond")));
					//기타주요사항 <BR>ETC_MAIN_CONT
					lom.put("etc_main_cont", StringUtil.convertEnterToBR((String)lom.get("etc_main_cont")));			
					//CNTRT_CHGE_DEMND_CAUSE
					lom.put("cntrt_chge_demnd_cause", StringUtil.convertEnterToBR((String)lom.get("cntrt_chge_demnd_cause")));
					//단가내역 요약cntrt_untprc_expl  CNTRT_UNTPRC_EXPL
					lom.put("cntrt_untprc_expl", StringUtil.convertEnterToBR(StringUtil.bvl((String)lom.get("cntrt_untprc_expl"),"")));			
					//책임한도   oblgt_lmt    OBLGT_LMT
					lom.put("oblgt_lmt", StringUtil.convertEnterToBR(StringUtil.bvl((String)lom.get("oblgt_lmt"),"")));				
					//Specila Condition  spcl_cond   SPCL_COND
					lom.put("spcl_cond", StringUtil.convertEnterToBR(StringUtil.bvl((String)lom.get("spcl_cond"),"")));			
					//loac_etc 준거법 상세  LOAC_ETC
					lom.put("loac_etc", StringUtil.convertEnterToBR(StringUtil.bvl((String)lom.get("loac_etc"),"")));
					//dspt_resolt_mthd_det 분쟁해결방법상세    DSPT_RESOLT_DET
					lom.put("dspt_resolt_mthd_det", StringUtil.convertEnterToBR(StringUtil.bvl((String)lom.get("dspt_resolt_mthd_det"),"")));
				}else{
					lom = new ListOrderedMap();
				}
				
				if(contractSpclLom!=null && contractSpclLom.size() >0 ){
					//특화정보 UI를 위한 정보
					for (int i = 0; i < contractSpclLom.size(); i++) {
						ListOrderedMap tempLom = (ListOrderedMap) contractSpclLom.get(i);
						if ("C04002".equals(tempLom.get("crtn_depth"))) {
							special1List.add(contractSpclLom.get(i));
						} else if ("C04005".equals(tempLom.get("crtn_depth"))) {
							special2List.add(contractSpclLom.get(i));
						}						
					}
				}else{
					//td_cnt = 0;
				}
			}
			List authReqList 			= null;//권한요청자 -관련자
			ConsultationVO	consultationVo = new ConsultationVO();
			consultationVo.setCntrt_id(vo.getCntrt_id());
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
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			//특화정보 UI를 위한 정보
			mav.addObject("td_cnt", td_cnt);
			
			mav.addObject("contractMstLom", lom);
			mav.addObject("contractExeLom", contractExeLom);
			mav.addObject("contractSpclLom", contractSpclLom);
			mav.addObject("contractCnclsndlayLom", contractCnclsndlayLom);
			mav.addObject("contractRelationLom", contractRelationLom);
			mav.addObject("contractReqLom", contractReqLom);
			mav.addObject("contractExecCommand", form);
			mav.addObject("command", form);
			
			
			//특화정보
			mav.addObject("considerationSpecialList", special1List);
			mav.addObject("consultationSpecialList" , special2List);
			mav.addObject("considerationSpecialSize", special1List.size());
			mav.addObject("consultationSpecialSize" , special2List.size());
			
			mav.addObject("relationListSize" , relationListSize);
			
			if(reqAuthInfo != null && !"".equals(reqAuthInfo.toString())) {
				mav.addObject("reqAuthInfo", reqAuthInfo.toString());
				mav.addObject("reqAuthFormInfo", reqAuthFormInfo.toString());
				mav.addObject("reqAuthSvcInfo", reqAuthSvcInfo.toString());
			} else {
				mav.addObject("reqAuthInfo", "");
				mav.addObject("reqAuthFormInfo", "");
				mav.addObject("reqAuthSvcInfo", "");
			}

			mav.addObject("ssems_oppnt_cd",StringUtil.bvl(request.getParameter("ssems_oppnt_cd"),""));//ssems인터페이스시 사용
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("detailContract() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("detailContract() Throwable !!");
		}
	}
	/**
	 * 이행정보 전체조회
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView listExecution(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			ExecutionForm form       = null;
			ExecutionVO   vo         = null;
			PageUtil 	pageUtil   = null;
			List   		resultList = null;
			
			String payment_gbn = "";
			
			ArrayList lom = null;
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new ExecutionForm();
			vo = new ExecutionVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Forward setting
			**********************************************************/
			
			String pageGbn = "";
			
			pageGbn = form.getPgGbn();
			
			if("registration".equals(pageGbn)){
				
				forwardURL = "/WEB-INF/jsp/clm/manage/RegExecution_exe.jsp";
				 
			} else {
				
				forwardURL = "/WEB-INF/jsp/clm/manage/Execution_exe.jsp";
				
			}
			
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
			
			/*********************************************************
			 * Service
			**********************************************************/

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap tmp = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(((Integer)tmp.get("total_cnt")).intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();
				
				//지불_구분 C02001:지불/수금, C02002:지불, C02003:수금, C02004:해당없음
				payment_gbn = (String)tmp.get("payment_gbn");
				
				lom = (ArrayList)resultList;
			}			

			String max_exec_num = executionService.getMaxExecNum(vo);
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("executionList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("executionLom", lom);
			mav.addObject("max_exec_num", max_exec_num);
			mav.addObject("payment_gbn", payment_gbn);
			mav.addObject("executionCommand", form);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

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
	 * 이행정보 상세조회
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView detailExecution(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			ExecutionForm form       = null;
			ExecutionVO   vo         = null;
			PageUtil 	pageUtil   = null;
			List   		resultList = null;
			
			String payment_gbn = "";
			ArrayList lom = null;
			
			/*********************************************************
			 * Forward setting
			**********************************************************/
			
			String pageGbn =  (String)request.getParameter("pageGbn");
			
			if("registration".equals(pageGbn)){
				forwardURL = "/WEB-INF/jsp/clm/manage/RegExecution_exe_d.jsp";
			} else {
				forwardURL = "/WEB-INF/jsp/clm/manage/Execution_exe_d.jsp";
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
			
			/*********************************************************
			 * Service
			**********************************************************/
			
			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap tmp = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(((Integer)tmp.get("total_cnt")).intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();
				
				//지불_구분 C02001:지불/수금, C02002:지불, C02003:수금, C02004:해당없음
				payment_gbn = (String)tmp.get("payment_gbn");
			}
			
			lom = (ArrayList)resultList;

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("executionList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("executionLom", lom);
			mav.addObject("payment_gbn", payment_gbn);
			mav.addObject("executionCommand", form);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

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
	 * 이행정보 신규저장
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView insertExecution(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*********************************************************
		 * Parameter 
		**********************************************************/
		int result = 0;
		String forwardURL    = "";
		String returnMessage = "";
		
		ExecutionForm form       = null;
		ExecutionVO   vo         = null;
		
		ModelAndView mav = null;		
				
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new ExecutionForm();
			vo   = new ExecutionVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Parameter
			**********************************************************/
            String pageGbn = "";
			pageGbn = form.getPgGbn();
			
			if("registration".equals(pageGbn)){
				vo.setPageGbn(pageGbn);
				forwardURL =  "/clm/manage/execution.do?method=listExecution&pageGbn=registration";
			} else {
				forwardURL =  "/clm/manage/execution.do?method=listExecution";
			}
			/*********************************************************
			 * Service
			**********************************************************/
			returnMessage = messageSource.getMessage("secfw.msg.succ.save", null, new RequestContext(request).getLocale());
			result = executionService.insertExecution(vo);
						
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("executionCommand", form);
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
	 * 이행정보 상태값 수정
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView modifyExecution(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			ExecutionForm form       = null;
			ExecutionVO   vo         = null;

			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL =  "/clm/manage/execution.do?method=listExecution";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new ExecutionForm();
			vo   = new ExecutionVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());
			executionService.modifyExecution(vo);
			
			String max_exec_num = executionService.getMaxExecNum(vo);
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("executionCommand", form);
			mav.addObject("max_exec_num", max_exec_num);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
			return mav;//

		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 이행정보   삭제
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView deleteExecution(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			ExecutionForm form       = null;
			ExecutionVO   vo         = null;
			
			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL = "/clm/manage/execution.do?method=listExecution";
	
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/			
			form = new ExecutionForm();
			vo   = new ExecutionVO();

			bind(request, form);
			bind(request, vo);
		
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());
			executionService.deleteExecution(vo);

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("executionCommand", form);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("deleteExecution() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("deleteExecution() Throwable !!");
		}
	}
	
	/**
	 * 이력정보 전체조회
	 * @param request, response
	 * @return ModelAndView
	 * @throws
	 */
	public ModelAndView listContractHis(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			ExecutionForm form       = null;
			ExecutionVO   vo         = null;
			List   		resultList = null;
			
			ArrayList review = null;
			ArrayList approve = null;
			ArrayList sign = null;
			ArrayList info = null;
			ArrayList con = null;
			ArrayList contractMstList = null;
			ArrayList contractCnclsndlayList = null;
			
			ListOrderedMap contractMstLom = null;
			ListOrderedMap contractCnclsndlayLom = null;
			
			List authReqList = null;
			List resultAttachList = null;
			StringBuffer reqAuthInfo		= new StringBuffer();
				
			/*********************************************************
			 * Forward setting
			**********************************************************/
			
			String pageGbn =  (String)request.getParameter("pageGbn");
			forwardURL = "/WEB-INF/jsp/clm/manage/Execution_his.jsp";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new ExecutionForm();
			vo = new ExecutionVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = executionService.listHisExecution(vo);

			//첨부파일 Sungwoo added 2014-09-01
			resultAttachList = considerationService.listCompletionAttachInfo(vo);
			
			if (resultList != null && resultList.size() > 0) {
				review = (ArrayList)resultList.get(0);
				approve = (ArrayList)resultList.get(1);
				sign = (ArrayList)resultList.get(2);
				info = (ArrayList)resultList.get(3);
				contractMstList = (ArrayList)resultList.get(4);
				contractCnclsndlayList = (ArrayList)resultList.get(5);
				
				con = (ArrayList)resultList.get(6);
				
				if(contractMstList!=null && contractMstList.size() >0 ){
					//master 정보
					contractMstLom = (ListOrderedMap)contractMstList.get(0);
					contractMstLom.put("org_acpt_dlay_cause", StringUtil.convertEnterToBR(StringUtil.bvl((String)contractMstLom.get("org_acpt_dlay_cause"),"")));
					contractMstLom.put("reqman_id", form.getReqman_id());
				}else{
					contractMstLom = new ListOrderedMap();
				}
				
				if(contractCnclsndlayList!=null && contractCnclsndlayList.size() >0 ){
					contractCnclsndlayLom = (ListOrderedMap)contractCnclsndlayList.get(0);
				}else{
					contractCnclsndlayLom = null;
				}	
				
				//11.관련자 정보
				if(resultList.get(7) != null && ((List)resultList.get(7)).size() > 0) {
					authReqList = (ArrayList)resultList.get(7);
					
					for(int i=0; i < authReqList.size(); i++) {
						ListOrderedMap authLom	= (ListOrderedMap)authReqList.get(i);
						if(i > 0){
							reqAuthInfo.append(", " + StringUtil.bvl((String)authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_dept_nm"), "")); //display용						
						} else {
							reqAuthInfo.append(StringUtil.bvl((String)authLom.get("trgtman_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_jikgup_nm"), "") + "/" + StringUtil.bvl((String)authLom.get("trgtman_dept_nm"), ""));						
						}
					}					
				}					
			}
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			//최종 건을 구하기 위한 maxrm 변수 세팅
			int maxrm = 0;
			if(review != null){
				ListOrderedMap _lom = new ListOrderedMap();
				for(int i = 0 ; i < review.size() ; i++){
					_lom = (ListOrderedMap) review.get(i);
					
					if( maxrm < Integer.parseInt(String.valueOf(_lom.get("rm")))){
						maxrm = Integer.parseInt(String.valueOf(_lom.get("rm")));
					}
				}
			}
			
			mav.addObject("maxrm", maxrm);
			mav.addObject("review", review);
			mav.addObject("approve", approve);
			mav.addObject("sign", sign);
			mav.addObject("info", info);
			mav.addObject("con",con);
			mav.addObject("hisCommand", form);
			mav.addObject("reqAuthInfo"			, reqAuthInfo);
			mav.addObject("command", form);
			mav.addObject("authReqList"			, authReqList);
			
			mav.addObject("contractMstLom", contractMstLom);
			mav.addObject("contractCnclsndlayLom", contractCnclsndlayLom);
			mav.addObject("resultAttachList", resultAttachList);			//Sungwoo added 2014-09-03

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("listContractHis() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("listContractHis() Throwable !!");
		}
	}
	
	/**
	 * 계약의뢰 상세화면 인쇄
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardPrintPop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "/WEB-INF/jsp/clm/report/ExecutionPrint.jsp";
			ConsultationForm form = new ConsultationForm();
			ConsultationVO 	 vo   = new ConsultationVO();
			
			bind(request, form);
			bind(request, vo);
			
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
	 * status 수정 (종료관리 버튼 클릭 시 상태를 이행중->종료대상으로 변경)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView modifyContractStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			String forwardURL = "/WEB-INF/jsp/clm/manage/Execution_l.jsp";
			
			ExecutionVO vo = new ExecutionVO();
			ExecutionForm form = new ExecutionForm();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			ModelAndView mav = new ModelAndView();
			String returnMessage = "";
			int result = -1;

			result = executionService.modifyContractStatus(vo);
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
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