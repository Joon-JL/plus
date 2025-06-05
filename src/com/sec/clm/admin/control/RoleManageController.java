/**
 * Project Name : 계약관리시스템
 * File name	: RoleManageController.java
 * Description	: 결재라인Role관리 Controller
 * Author		: 박정훈
 * Date			: 2014. 05. 19
 * Copyright	:
 */
package com.sec.clm.admin.control;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.admin.dto.ApprovalPathForm;
import com.sec.clm.admin.dto.ApprovalPathVO;
import com.sec.clm.admin.dto.RoleForm;
import com.sec.clm.admin.dto.RoleVO;
import com.sec.clm.admin.service.RoleService;
import com.sec.common.util.ClmsBoardUtil;

/**
 * Description	: Controller
 * Author		: 박정훈
 * Date			: 2014. 05. 19
 */
public class RoleManageController extends CommonController {
	
	/**
	 * Role 서비스
	 */
	public RoleService roleService;
	
	/**
	 * Role 서비스 세팅
	 * 
	 * @param roleService
	 * @return void
	 * @throws
	 */
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	/**
	 * 목록조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			RoleForm form       = null;
			RoleVO   vo         = null;
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
			forwardURL = "/WEB-INF/jsp/clm/admin/Role_l.jsp";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new RoleForm();
			vo   = new RoleVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//Cross-site Scripting 방0지
			form.setSrch_role_name(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_role_name(),"")));
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
			resultList = roleService.listRole(vo);

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
			throw new Exception("TypeController.listType() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("TypeController.listType() Throwable !!");
		}
	}
	
	
	/**
	 * 등록 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardInsertRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			
			RoleForm form       = null;
			RoleVO   vo         = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);

			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/admin/Role_i.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/			
			form = new RoleForm();
			vo   = new RoleVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("command", form);

			return mav; 
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("TypeController.forwardInsertType() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("TypeController.forwardInsertType() Throwable !!");
		}
	}
	
	/**
	 * 등록
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			RoleForm form       = null;
			RoleVO   vo         = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/clm/admin/role.do?method=listRole";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new RoleForm();
			vo   = new RoleVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			int resultVal = roleService.insertRole(vo);
			
			//  유저등록/업데이트
				roleService.UsersInfo(vo);		
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
			throw new Exception("RoleManageController.insertRole() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("RoleManageController.insertRole() Throwable !!");
		}
	}

	/**
	 * 상세정보
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			RoleForm form       = null;
			RoleVO   vo         = null;
			List   	 resultList = null;
			List   	 resultListUser = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/admin/Role_d.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new RoleForm();
			vo   = new RoleVO();

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
			resultList = roleService.detailRole(vo);

			resultListUser = roleService.detailAssignedUsers(vo);
			
			ListOrderedMap lom = null;
			
			if(resultList.size() > 0){
				 lom = (ListOrderedMap)resultList.get(0);
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
			mav.addObject("lom", lom);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("TypeController.detailType() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("TypeController.detailType() Throwable !!");
		}
	}

	/**
	 * 수정 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardModifyRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			RoleForm form       = null;
			RoleVO   vo         = null;
			List   	   resultList = null;
			List   	 resultListUser = null;
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/admin/Role_u.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new RoleForm();
			vo   = new RoleVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = roleService.detailRole(vo);
			resultListUser = roleService.detailAssignedUsers(vo);
			
			if(resultList==null)
				throw new Exception("##### queryService is null ##### ");
			
			ListOrderedMap lom = null;
			int Usersize = 0;
			
			if(resultList.size() > 0){
				 lom = (ListOrderedMap)resultList.get(0);
				 Usersize = resultListUser.size();
			}
			
			
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			
			mav.addObject("resultList", resultList);
			mav.addObject("resultListUser", resultListUser);
			mav.addObject("usersize", Usersize);
			mav.addObject("lom", lom);
			mav.addObject("command", form);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("TypeController.forwardModifyType() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("TypeController.forwardModifyType() Throwable !!");
		}
	}

	/**
	 * 수정 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifyRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			RoleForm form        = null;
			RoleVO   vo          = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL = "/clm/admin/role.do?method=detailRole";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new RoleForm();
			vo   = new RoleVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			form.setMod_id(vo.getSession_user_id());

			
			/*********************************************************
			 * Service
			**********************************************************/
			//유저정보등록
			int result = roleService.modifyRole(vo);
			
			//  유저등록/업데이트
			roleService.UsersInfo(vo);			
			
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
			throw new Exception("TypeController.modifyType() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("TypeController.modifyType() Throwable !!");
		}
	}

	/**
	 * 삭제
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView deleteRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			RoleForm form       = null;
			RoleVO   vo         = null;
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
			forwardURL = "/clm/admin/role.do?method=listRole";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new RoleForm();
			vo   = new RoleVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			form.setDel_id(vo.getSession_user_id());
			form.setComp_cd(vo.getSession_comp_cd());

			/*********************************************************
			 * Service
			**********************************************************/
			int result = roleService.deleteRole(vo);
			
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
			throw new Exception("TypeController.deleteType() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("TypeController.deleteType() Throwable !!");
		}
	}
	
	/**
	 *reviewer 조회 
	 * @param sb
	 * @param map
	 * @param form
	 * @param reqAuthInfo
	 * @throws Exception
	 */
	public void reviewAuto(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			RoleForm form       = null;
			RoleVO   vo         = null;
			
			
			form = new RoleForm();
			vo   = new RoleVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			List resultList = null;

			resultList = roleService.reviewAutolist(vo) ;
						
			ListOrderedMap lom = null;
			
			JSONObject jo = new JSONObject();
		
			
			ListOrderedMap resultListMap = new ListOrderedMap();
			
			String user_id = "";
			String user_nm = "";
			String dept_nm = "";
			String jikgun_nm_eng = "";
			
			String ser ="";
			for(int i=0; i < resultList.size(); i++) {
				if(resultList.size()-1 != i){
					ser = ",";
				}else{
					ser ="";
				}
				resultListMap = (ListOrderedMap)resultList.get(i);
				user_id += (String)resultListMap.get("USER_ID")+ser;
				user_nm += StringUtil.bvl((String)resultListMap.get("USER_NM"),"")+ser;
				dept_nm += StringUtil.bvl((String)resultListMap.get("DEPT_NM"),"")+ser;
				jikgun_nm_eng += StringUtil.bvl((String)resultListMap.get("JIKGUN_NM_ENG"),"")+ser;
			}
			
			jo.put("user_id", user_id);
			jo.put("user_nm", user_nm);
			jo.put("dept_nm", dept_nm);
			jo.put("jikgun_nm_eng", jikgun_nm_eng);
			
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
	 * Approval path에 사용여부 조회 
	 * @param sb
	 * @param map
	 * @param form
	 * @param reqAuthInfo
	 * @throws Exception
	 */
	public void checkApprovalData(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			RoleForm form       = null;
			RoleVO   vo         = null;
			
			
			form = new RoleForm();
			vo   = new RoleVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			int resultList = 0;

			resultList = roleService.checkApprovalYn(vo) ;
						
			ListOrderedMap lom = null;
			
			JSONObject jo = new JSONObject();
						
			
			jo.put("val", resultList);

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
	 * Form과 VO 검색조건 및 현재페이지 변수 초기화
	 * @param request
	 * @param form
	 * @param vo
	 * @throws Exception
	 */
	public void setInitFormVO(HttpServletRequest request, RoleForm form, RoleVO vo) throws Exception {
		form.setCurPage("1");
		vo.setCurPage("1");
	}
}