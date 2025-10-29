/**
 * Project Name : 계약관리시스템
 * File name	: ApprovalPathController.java
 * Description	: 결재라인PATH관리 Controller
 * Author		: 박정훈
 * Date			: 2014. 05. 19
 * Copyright	:
 */
package com.sec.clm.admin.control;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.ExcelBuilder;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.admin.dto.ApprovalPathForm;
import com.sec.clm.admin.dto.ApprovalPathVO;
import com.sec.clm.admin.service.ApprovalPathService;
import com.sec.clm.manage.dto.ManageForm;
import com.sec.clm.manage.dto.ManageVO;



/**
 * Description	: Controller
 * Author		: 박정훈
 * Date			: 2014. 05. 19
 */
public class ApprovalPathController extends CommonController {
	
	/**
	 * Role 서비스
	 */
	public ApprovalPathService approvalPathService;
	
	/**
	 * Role 서비스 세팅
	 * 
	 * @param roleService
	 * @return void
	 * @throws
	 */
	public void setApprovalPathService(ApprovalPathService approvalPathService) {
		this.approvalPathService = approvalPathService;
	}
	

	/**
	 * 목록조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listApprovalPath(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			ApprovalPathForm form       = null;
			ApprovalPathVO   vo         = null;
			PageUtil pageUtil   = null;
			List   	 resultList = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/admin/ApprovalPath_l.jsp";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new ApprovalPathForm();
			vo   = new ApprovalPathVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//Cross-site Scripting 방0지
			form.setSrch_path_name(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_path_name(),"")));
			form.setSrch_use_yn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_use_yn(),"")));
			form.setSrch_loc_gbn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_loc_gbn(),"")));
			form.setCurPage(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getCurPage(),"")));
			
			/*********************************************************
			 * Page  setting
			**********************************************************/
			pageUtil = new PageUtil();
			
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());	//현재 페이지의 첫번째 게시물번호  set
			vo.setEnd_index(pageUtil.getEndIndex());		//현재 페이지의 마지막 게시물번호  set

			if(form.getCurPage() == null) {
				setInitFormVO(request, form, vo);
			}
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = approvalPathService.listApprovalPath(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap tmp = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(((BigDecimal)tmp.get("total_cnt")).intValue());
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

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			form.setReturn_message(returnMessage);
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);

			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("lom", lom);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ApprovalPathController.listApprovalPath() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ApprovalPathController.listApprovalPath() Throwable !!");
		}
	}
	
	
	/**
	 * 등록 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardInsertApprovalPath(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			
			ApprovalPathForm form       = null;
			ApprovalPathVO   vo         = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);

			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/admin/ApprovalPath_i.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/			
			form = new ApprovalPathForm();
			vo   = new ApprovalPathVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			
			vo.setUp_type_cd("T01"); 
			List Combotype_1 = approvalPathService.listCombolist(vo) ;
			
			vo.setUp_type_cd("T02"); 
			List Combotype_2 = approvalPathService.listCombolist(vo) ;
			
			vo.setUp_type_cd("T03"); 
			List Combotype_3 = approvalPathService.listCombolist(vo) ;
									
						
			HashMap combo = new HashMap() ;
			combo.put("combotype_1", Combotype_1) ; // contract Type1 콤보
			combo.put("combotype_2", Combotype_2) ; // contract Type2 콤보
			combo.put("combotype_3", Combotype_3) ; // contract Type3 콤보

			

			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("combo", combo);

			return mav; 
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ApprovalPathController.forwardInsertApprovalPath() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ApprovalPathController.forwardInsertApprovalPath() Throwable !!");
		}
	}
	
	/**
	 * 등록
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertApprovalPath(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			ApprovalPathForm form       = null;
			ApprovalPathVO   vo         = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/clm/admin/approvalPath.do?method=listApprovalPath";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new ApprovalPathForm();
			vo   = new ApprovalPathVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			int resultVal = approvalPathService.insertApprovalPath(vo);
			
			//  유저등록/업데이트
			approvalPathService.UsersInfo(vo);	
			/*********************************************************
			 * Massage
			**********************************************************/
			// 메시지처리 - 등록되었습니다.
			returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);

			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ApprovalPathController.insertApprovalPath() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ApprovalPathController.insertApprovalPath() Throwable !!");
		}
	}

	/**
	 * 상세정보
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailApprovalPath(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			ApprovalPathForm form       = null;
			ApprovalPathVO   vo         = null;
			List   	 resultList = null;
			List   	 resultListUser = null;
			List   	 resultListDETAIL = null;
			
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/admin/ApprovalPath_d.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new ApprovalPathForm();
			vo   = new ApprovalPathVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);



			/*
			// 수정권한 세팅
			form.setAuth_modify(ClmsBoardUtil.getDeleteModifyAuth(request, form.getReg_id())); //reg_id : 등록자 EPID
			// 삭제권한 세팅
			form.setAuth_delete(ClmsBoardUtil.getDeleteModifyAuth(request, form.getReg_id())); //reg_id : 등록자 EPID
			 */
			/*********************************************************
			 * Service
			**********************************************************/
			//TN_CLM_APPROVAL_CONT LIST
			resultList = approvalPathService.detailApprovalPath(vo);
			//TN_CLM_APPROVAL_CONT_DETAIL LIST
			resultListDETAIL = approvalPathService.detailApprovalPathDetail(vo);
			//TN_CLM_APPROVAL_PATH LIST
			resultListUser = approvalPathService.detailApprovalUsers(vo);
			
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			
			ListOrderedMap lom2 = null;
			
			int resultdetailsize = 0;
			if(resultListDETAIL.size() > 0){
				resultdetailsize = resultListDETAIL.size();
				lom2 = (ListOrderedMap)resultListDETAIL.get(0);
			}
			
			if(resultList==null)
				throw new Exception("##### queryService is null ##### ");
			
			/*********************************************************
			 * Massage
			**********************************************************/
			returnMessage = StringUtil.bvl((String)request.getAttribute("returnMessage"), "");

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			
			mav.addObject("resultList", resultList);
			mav.addObject("resultListUser", resultListUser);
			mav.addObject("resultListDETAIL", resultListDETAIL);
			mav.addObject("resultdetailsize",resultdetailsize);
			mav.addObject("lom", lom);
			mav.addObject("lom2", lom2);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ApprovalPathController.detailApprovalPath() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ApprovalPathController.detailApprovalPath() Throwable !!");
		}
	}

	/**
	 * 수정 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardModifyApprovalPath(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			ApprovalPathForm form       = null;
			ApprovalPathVO   vo         = null;
			List   	   resultList = null;
			List   	 resultListUser = null;
			List   	 resultListDETAIL = null;
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/admin/ApprovalPath_u.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new ApprovalPathForm();
			vo   = new ApprovalPathVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			
			vo.setUp_type_cd("T01"); 
			List Combotype_1 = approvalPathService.listCombolist(vo) ;
			
			vo.setUp_type_cd("T02"); 
			List Combotype_2 = approvalPathService.listCombolist(vo) ;
			
			vo.setUp_type_cd("T03"); 
			List Combotype_3 = approvalPathService.listCombolist(vo) ;
			
			vo.setUp_type_cd(vo.getType_3()); 
			List Combotype_4 = approvalPathService.listCombolist(vo) ;
						
			HashMap combo = new HashMap() ;
			combo.put("combotype_1", Combotype_1) ; // contract Type1 콤보
			combo.put("combotype_2", Combotype_2) ; // contract Type2 콤보
			combo.put("combotype_3", Combotype_3) ; // contract Type3 콤보
			combo.put("combotype_4", Combotype_4) ; // contract Type4 콤보
			
			
			
			
			
			
			//TN_CLM_APPROVAL_CONT LIST
			resultList = approvalPathService.detailApprovalPath(vo);
			//TN_CLM_APPROVAL_CONT_DETAIL LIST
			resultListDETAIL = approvalPathService.detailApprovalPathDetail(vo);
			//TN_CLM_APPROVAL_PATH LIST
			resultListUser = approvalPathService.detailApprovalUsers(vo);
			
			if(resultList==null)
				throw new Exception("##### queryService is null ##### ");
			
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			
			vo.setComp_cd((String)lom.get("comp_cd"));
			
			List approverRole = approvalPathService.roleCombolist(vo) ;
			combo.put("approverRole", approverRole) ; // approverRole 콤보
			
			ListOrderedMap lom2 = null;
			
			int resultdetailsize = 0;
			if(resultListDETAIL.size() > 0){
				resultdetailsize = resultListDETAIL.size();
				lom2 = (ListOrderedMap)resultListDETAIL.get(0);
			}
			
			
			int Usersize = 0;
			if(resultListUser.size() > 0){
				Usersize = resultListUser.size();
			}

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			
			mav.addObject("resultList", resultList);
			mav.addObject("resultListUser", resultListUser);
			mav.addObject("resultListDETAIL", resultListDETAIL);
			mav.addObject("Usersize",Usersize);
			mav.addObject("resultdetailsize",resultdetailsize);
			mav.addObject("lom", lom);
			mav.addObject("lom2", lom2);
			mav.addObject("command", form);
			mav.addObject("combo", combo);
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ApprovalPathController.forwardModifyApprovalPath() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ApprovalPathController.forwardModifyApprovalPath() Throwable !!");
		}
	}

	/**
	 * 수정 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifyApprovalPath(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			ApprovalPathForm form        = null;
			ApprovalPathVO   vo          = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL = "/clm/admin/approvalPath.do?method=detailApprovalPath";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new ApprovalPathForm();
			vo   = new ApprovalPathVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			form.setMod_id(vo.getSession_user_id());

			
			/*********************************************************
			 * Service
			**********************************************************/
			int result = approvalPathService.modifyApprovalPath(vo);
			//  유저등록/업데이트
			approvalPathService.UsersInfo(vo);	
			/*********************************************************
			 * Massage
			**********************************************************/
			// 메시지처리 - 수정되었습니다.
			returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ApprovalPathController.modifyType() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ApprovalPathController.modifyType() Throwable !!");
		}
	}

	/**
	 * 삭제
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView deleteApprovalPath(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			ApprovalPathForm form       = null;
			ApprovalPathVO   vo         = null;
			PageUtil 	pageUtil   = null;
			List   		resultList = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL = "/clm/admin/approvalPath.do?method=listApprovalPath";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new ApprovalPathForm();
			vo   = new ApprovalPathVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			form.setDel_id(vo.getSession_user_id());
			form.setComp_cd(vo.getLoc_gbn());

			/*********************************************************
			 * Service
			**********************************************************/
			int result = approvalPathService.deleteApprovalPath(vo);
			
			/*********************************************************
			 * Massage
			**********************************************************/
			// 메시지처리 - 삭제되었습니다.
			returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ApprovalPathController.deleteApprovalPath() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ApprovalPathController.deleteApprovalPath() Throwable !!");
		}
	}

	/**
	 *계약유형 조회 
	 * @param sb
	 * @param map
	 * @param form
	 * @param reqAuthInfo
	 * @throws Exception
	 */
	public void checkContract(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ApprovalPathForm form       = null;
			ApprovalPathVO   vo         = null;
			
			
			form = new ApprovalPathForm();
			vo   = new ApprovalPathVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			List resultList = null;
    		
    		
    		
    		vo.setUp_type_cd(vo.getType_3()); 
			resultList = approvalPathService.listCombolist(vo) ;
			
			ListOrderedMap lom = null;
			
			JSONObject jo = new JSONObject();
			
			

			String opt = "";
			opt = "<option value=''>--select--</option>";
			for(int i=0;resultList.size()>i;i++){
				lom = (ListOrderedMap)resultList.get(i);
				opt += "<option value="+(String)lom.get("type_cd")+">"+(String)lom.get("cd_nm")+"</option>";
				
			}
			
			jo.put("olist", opt);

//			jo.put("message",requiredLom.get("message"));
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
	 *role 조회 
	 * @param sb
	 * @param map
	 * @param form
	 * @param reqAuthInfo
	 * @throws Exception
	 */
	public void checkRolelist(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			
			String comp_cd = request.getParameter("comp_cd");
			
			ApprovalPathForm form       = null;
			ApprovalPathVO   vo         = null;
			
			
			form = new ApprovalPathForm();
			vo   = new ApprovalPathVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			List resultList = null;

			vo.setComp_cd(comp_cd);

			resultList = approvalPathService.roleCombolist(vo) ;
			
						
			ListOrderedMap lom = null;
			
			JSONObject jo = new JSONObject();
			
			

			String opt = "";

			for(int i=0;resultList.size()>i;i++){
				lom = (ListOrderedMap)resultList.get(i);
				opt += "<option value="+(String)lom.get("role_cd")+">"+(String)lom.get("role_nm")+"</option>";
				
			}
			
			jo.put("olist", opt);

//			jo.put("message",requiredLom.get("message"));
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
	* 엑셀 다운로드
	* 
	* @param request
	* @return void
	* @throws Exception
	*/
	public void listApprovalPathExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			ApprovalPathForm form       = null;
			ApprovalPathVO   vo         = null;
			
			
			form = new ApprovalPathForm();
			vo   = new ApprovalPathVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			List resultList = null;
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = approvalPathService.listApprovalPathExcel(vo);
			
			/*********************************************************
			 * Excel DownLoad
			**********************************************************/
			String		fileNm			= "";
			String[]	sheetNmAry		= new String[1];
			String[]	titleInfo		= new String[1];
			
			String[]	subTitleInfo	= null;
			String[]	columnInfo		= null;
			short[]		columnAlign		= null;
			
			//메시지 처리 - No
			String message_no = messageSource.getMessage("secfw.page.field.approvalPath.No", null, new RequestContext(request).getLocale());
			//메시지 처리 - path name
			String message_PathName = messageSource.getMessage("secfw.page.field.approvalPath.PathName", null, new RequestContext(request).getLocale());
			//메시지 처리 - Priority
			String message_Priority = messageSource.getMessage("secfw.page.field.approvalPath.Priority", null, new RequestContext(request).getLocale());
			//메시지 처리 - Active
			String message_Active = messageSource.getMessage("secfw.page.field.approvalPath.Active", null, new RequestContext(request).getLocale());
			//메시지 처리 - Mandatory
			String message_Mandatory = messageSource.getMessage("secfw.page.field.approvalPath.Mandatory", null, new RequestContext(request).getLocale());
			//메시지 처리 - Contract Type(Business Stage)
			String message_ContractType1 = messageSource.getMessage("secfw.page.field.approvalPath.ContractType1", null, new RequestContext(request).getLocale());
			//메시지 처리 - Contract Type(Contract Stage)
			String message_ContractType2 = messageSource.getMessage("secfw.page.field.approvalPath.ContractType2", null, new RequestContext(request).getLocale());
			//메시지 처리 - Contract Type(Purpose of Coantract)
			String message_ContractType3 = messageSource.getMessage("secfw.page.field.approvalPath.ContractType3", null, new RequestContext(request).getLocale());
			//메시지 처리 - Contract Type(Detail for Purpose of Contract)
			String message_ContractType4 = messageSource.getMessage("secfw.page.field.approvalPath.ContractType4", null, new RequestContext(request).getLocale());
			//메시지 처리 - ConditionDetail
			String message_ConditionDetail = messageSource.getMessage("secfw.page.field.approvalPath.ConditionDetail", null, new RequestContext(request).getLocale());
			//메시지 처리 - Approver
			String message_Approver = messageSource.getMessage("secfw.page.field.approvalPath.Approver", null, new RequestContext(request).getLocale());
			
			
			fileNm			= (String)messageSource.getMessage("secfw.page.field.approvalPath.ApprovalPathList", null, new RequestContext(request).getLocale())+ "_" + DateUtil.today() + ".xls";
			sheetNmAry[0]	= (String)messageSource.getMessage("secfw.page.field.approvalPath.ApprovalPathList", null, new RequestContext(request).getLocale())+ "_" + DateUtil.today();
			
			//메시지 처리 - 의뢰별 목록
			titleInfo[0]	= (String)messageSource.getMessage("secfw.page.field.approvalPath.ApprovalPathList", null, new RequestContext(request).getLocale());
			subTitleInfo	= new String[]{message_no, message_PathName, message_Priority, message_Active, message_Mandatory, message_ContractType1, message_ContractType2, message_ContractType3, message_ContractType4, message_ConditionDetail,message_Approver};
			columnInfo		= new String[]{"sort_no", "path_nm", "priority", "use_yn", "mandatory", "type_1", "type_2", "type_3", "type_4", "OPERATION", "approver"};
			columnAlign		= new short[]{ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_CENTER};
			

	        
	        ExcelBuilder excel = new ExcelBuilder(sheetNmAry);

			int sheetsCount = excel.getSheetsCount();                                // Sheet의 갯수			

			for(int i=0;i<sheetsCount;i++) {                                         // Sheet의 수 만큼 Loop				
				
				excel.setBold(true);
				excel.setFontColor(ExcelBuilder.COLOR_BLACK);
				excel.setFontName("맑은 고딕");
				excel.setFontSize((short)11);
				excel.setBgColor(ExcelBuilder.COLOR_YELLOW);
				excel.setBorder(true);

				excel.addTitleRow(i, subTitleInfo);
				excel.setDefaultStyle();
				
				excel.setAlign(columnAlign);
				excel.setVAlign(ExcelBuilder.VALIGN_CENTER);
				excel.setBorder(new boolean[]{true});
				
				excel.addRows(i, columnInfo, resultList);
				
				excel.setDefaultStyle();
			
			}			
			
			excel.download(fileNm, response);
			
		}catch (Exception e) {
			e.printStackTrace();
		}catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	
	/**
	 * Form과 VO 검색조건 및 현재페이지 변수 초기화
	 * @param request
	 * @param form
	 * @param vo
	 * @throws Exception
	 */
	public void setInitFormVO(HttpServletRequest request, ApprovalPathForm form, ApprovalPathVO vo) throws Exception {
		form.setCurPage("1");
		vo.setCurPage("1");
	}
}