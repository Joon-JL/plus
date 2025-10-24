package com.sds.secframework.auth.control;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.auth.dto.AuthForm;
import com.sds.secframework.auth.dto.AuthVO;
import com.sds.secframework.auth.service.AuthMappingService;
import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.user.dto.UserManagerVO;
import com.sds.secframework.user.service.UserManagerService;
import com.sec.common.clmscom.service.CLMSCommonService;

/**
 * <P>
 * 권한설정 Class
 * </P>
 * 
 * [지원기능]<BR>
 * <BR>
 * - 역활리스트 정보<BR>
 * - 사용자목록 조회<BR>
 * - 역활-사용자 목록조회<BR>
 * - 역활-사용자등록<BR>
 * - 권한목록조회<BR>
 * - 역활-권한 목록조회<BR>
 * - 역활-권한 등록<BR>
 * - 메뉴-권한 목록조회<BR>
 * - 메뉴-권한 등록<BR>
 * - 사용자목록조회<BR>
 * - 페이지-권한 목록조회<BR>
 * - 페이지 권한 등록<BR>
 * <br>
 * 
 * @version V1.0 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */

public class AuthMappingController extends CommonController {

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private AuthMappingService authMappingService;

	public void setAuthMappingService(AuthMappingService authMappingService) {
		this.authMappingService = authMappingService;
	}

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅 
	 * 공통코드 콤보셋팅 !@# 2013-04-16
	 */
	private CLMSCommonService clmsComService;
	public void setClmsComService(CLMSCommonService clmsComService)
	{
		this.clmsComService = clmsComService;
	}
	
	/**
	 *  userManagerService 선언 및 세팅
	 * 
	 */
	private UserManagerService userManagerService;
	public void setUserManagerService(UserManagerService userManagerService) {
		this.userManagerService = userManagerService;
	}

	/**
	 * 페이지 포워드.
	 * 
	 * @param request
	 * @param response
	 * @return 포워딩정보 및 데이타를 담은 ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			/*********************************************************
			 * Forwarding URL
			 **********************************************************/
			// 등록페이지로 Forwading
			String forwardURL = "";
			Locale lc = new RequestContext(request).getLocale();
			String localeCd = StringUtil.bvl(lc.getLanguage(), "en");
			String authGbn = StringUtil.bvl((String) request.getParameter("authGbn"), "");
			String compCombo = null;

			if (authGbn.equals("roleUser")) {
				forwardURL = "/WEB-INF/jsp/secfw/auth/MappingRoleUser.jsp";
			} else if (authGbn.equals("roleAuth")) {
				forwardURL = "/WEB-INF/jsp/secfw/auth/MappingRoleAuth.jsp";
			} else if (authGbn.equals("authMenu")) {
				forwardURL = "/WEB-INF/jsp/secfw/auth/MappingAuthMenu.jsp";
			} else if (authGbn.equals("authMethod")) {
				forwardURL = "/WEB-INF/jsp/secfw/auth/MappingAuthMethod.jsp";
			} else if (authGbn.equals("authPage")) {
				forwardURL = "/WEB-INF/jsp/secfw/auth/MappingAuthPage.jsp";
			} else if (authGbn.equals("compAuthMenu")) {
				forwardURL = "/WEB-INF/jsp/secfw/auth/MappingCompAuthMenu.jsp";
				// 공통코드 콤보셋팅 !@# 2013-04-16
				HashMap comboMap = new HashMap();
				comboMap.put("SYS_CD", "LAS");
				comboMap.put("GRP_CD", "COMP2");
				comboMap.put("LOCALE", localeCd);
				comboMap.put("ABBR", "A");
				comboMap.put("DEL_YN", "N");
				comboMap.put("TYPE", "S");
				compCombo = clmsComService.listComCdByGrpCd(comboMap);
			} else if (authGbn.equals("compReviewer")) {
				forwardURL = "/WEB-INF/jsp/secfw/auth/MappingCompReviewer.jsp";
			}
			
			//-----------BIND:바인드설정 -------------------- 
			AuthForm form = new AuthForm();
			bind(request, form);

			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			
			HashMap combo = new HashMap() ;
			combo.put("compGbn", compCombo) ; // 지불구분 콤보
			mav.addObject("combo", combo);
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
	 * 역활 리스트 정보
	 * 
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void listRole(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템 코드
			 **********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			AuthForm form = new AuthForm();
			bind(request, form);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			HashMap hm = new HashMap();

			hm.put("sys_cd", sysCd);
			hm.put("srch_cntnt", form.getSrch_role_cntnt());
			hm.put("srch_use_yn", "Y");

			/*********************************************************
			 * 목록 조회
			 **********************************************************/
			JSONArray jsonArray = authMappingService.listRole(hm);

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();

		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}

	/**
	 * 사용자목록조회
	 * 
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void listUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템 코드
			 **********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			AuthForm form = new AuthForm();
			bind(request, form);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			HashMap hm = new HashMap();

			hm.put("sys_cd", sysCd);
			hm.put("srch_cntnt_type", form.getSrch_user_cntnt_type());
			hm.put("srch_cntnt", form.getSrch_user_cntnt());

			/*********************************************************
			 * 목록 조회
			 **********************************************************/
			JSONArray jsonArray = authMappingService.listUser(hm);

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();

		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}

	/**
	 * 역할-사용자 목록조회
	 * 
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void listRoleUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템 코드
			 **********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			AuthVO vo = new AuthVO();
			bind(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			vo.setSys_cd(sysCd);

			/*********************************************************
			 * 목록 조회
			 **********************************************************/
			JSONArray jsonArray = authMappingService.listRoleUser(vo);

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();

		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}

	/**
	 * 역할-사용자 등록
	 * 
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void insertRoleUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템 코드
			 **********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");

			String userId = (String) session
					.getAttribute("secfw.session.user_id");

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			AuthVO vo = new AuthVO();
			bind(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			vo.setSys_cd(sysCd);
			vo.setReg_id(userId);
			vo.setMod_id(userId);

			/*********************************************************
			 * 역할-사용자 등록
			 **********************************************************/
			authMappingService.insertRoleUser(vo);

			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.succ.insert", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();

		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}

	/**
	 * 권한목록조회
	 * 
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void listAuth(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템 코드
			 **********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			AuthForm form = new AuthForm();
			bind(request, form);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			HashMap hm = new HashMap();

			hm.put("sys_cd", sysCd);
			hm.put("srch_cntnt", form.getSrch_auth_cntnt());
			hm.put("srch_use_yn", "Y");
			hm.put("srch_comp_cd", form.getSrch_comp_cd());
			hm.put("menu_id", form.getMenu_id());
			/*********************************************************
			 * 목록 조회
			 **********************************************************/
			JSONArray jsonArray = authMappingService.listAuth(hm);

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();

		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}

	/**
	 * 역할-권한 목록조회
	 * 
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void listRoleAuth(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템 코드
			 **********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			AuthVO vo = new AuthVO();
			bind(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			vo.setSys_cd(sysCd);

			/*********************************************************
			 * 목록 조회
			 **********************************************************/
			JSONArray jsonArray = authMappingService.listRoleAuth(vo);

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();

		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}

	/**
	 * 역할-권한 등록
	 * 
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void insertRoleAuth(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템 코드
			 **********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
			
			String userId = (String) session
					.getAttribute("secfw.session.user_id");

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			AuthVO vo = new AuthVO();
			bind(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			vo.setSys_cd(sysCd);
			vo.setReg_id(userId);
			vo.setMod_id(userId);

			/*********************************************************
			 * 역할-권한 등록
			 **********************************************************/
			authMappingService.insertRoleAuth(vo);

			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.succ.insert", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();

		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}

	/**
	 * 메뉴-권한 목록조회
	 * 
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void listMenuAuth(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템 코드
			 **********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			AuthForm form = new AuthForm();
			bind(request, form);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			HashMap<String, String> hm = new HashMap<String, String>();

			hm.put("sys_cd", sysCd);
			hm.put("auth_cd", form.getAuth_cd());
			hm.put("comp_cd", form.getComp_cd()); // 콤보박스에서 선택한 회사(메뉴권한설정에서는 hidden값)
			hm.put("top_menu_id", propertyService
					.getProperty("secfw.menu.topMenuId"));
			hm.put("use_yn", "Y");

			/*********************************************************
			 * 목록 조회
			 **********************************************************/
			JSONArray jsonArray = authMappingService.listMenuAuth(hm);

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();

		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}

	/**
	 * 메뉴-권한 등록
	 * 
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void insertMenuAuth(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템 코드
			 **********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
			String userId = (String)session.getAttribute("secfw.session.user_id");   // USER_ID

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			AuthVO vo = new AuthVO();
			bind(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			vo.setSys_cd(sysCd);
			vo.setReg_id(userId);
			vo.setMod_id(userId);
			//vo.setComp_cd(form.getComp_cd()); // 콤보박스에서 선택한 회사(메뉴권한설정에서는 hidden 값)

			/*********************************************************
			 * 메뉴-권한 등록
			 **********************************************************/
			authMappingService.insertMenuAuth(vo);

			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.succ.insert", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();

		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}

	/**
	 * 사별 메뉴-권한 목록조회
	 * 
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void listCompMenuAuth(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템 코드
			 **********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			AuthForm form = new AuthForm();
			bind(request, form);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			HashMap<String, String> hm = new HashMap<String, String>();

			hm.put("sys_cd", sysCd);
			hm.put("auth_cd", form.getAuth_cd());
			hm.put("comp_cd", form.getComp_cd());
			hm.put("top_menu_id", propertyService
					.getProperty("secfw.menu.topMenuId"));
			hm.put("use_yn", "Y");

			/*********************************************************
			 * 목록 조회
			 **********************************************************/
			JSONArray jsonArray = authMappingService.listCompMenuAuth(hm);

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();

		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}

	/**
	 * 사별 메뉴-권한 등록
	 * 
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void insertCompMenuAuth(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템 코드
			 **********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
			String userId = (String)session.getAttribute("secfw.session.user_id");   // USER_ID

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			AuthVO vo = new AuthVO();
			bind(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			vo.setSys_cd(sysCd);
			vo.setReg_id(userId);
			vo.setMod_id(userId);
			//vo.setComp_cd(vo.getComp_cd());

			/*********************************************************
			 * 메뉴-권한 등록
			 **********************************************************/
			authMappingService.insertCompMenuAuth(vo);

			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.succ.insert", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();

		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}

	/**
	 * 메뉴 Access Control 조회
	 * 
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void getAccessControl(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템 코드
			 **********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			AuthVO vo = new AuthVO();
			bind(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			vo.setSys_cd(sysCd);

			/*********************************************************
			 * AccessControl 조회
			 **********************************************************/
			String result = authMappingService.getAccessControl(vo);
			JSONObject jo = new JSONObject();
			jo.put("accessControl", result);

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();

		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}

	/**
	 * 메뉴 Access Control 등록
	 * 
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void insertAccessControl(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템 코드
			 **********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
			
			String userId = (String) session
					.getAttribute("secfw.session.user_id");

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			AuthVO vo = new AuthVO();
			bind(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			vo.setSys_cd(sysCd);
			vo.setReg_id(userId);

			/*********************************************************
			 * 메뉴 Access Control 등록
			 **********************************************************/
			authMappingService.insertAccessControl(vo);

			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.succ.insert", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();

		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}

	/**
	 * 메소드-권한 목록조회
	 * 
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void listMethodAuth(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템 코드
			 **********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
			
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			AuthForm form = new AuthForm();
			bind(request, form);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			HashMap hm = new HashMap();

			hm.put("sys_cd", sysCd);
			hm.put("auth_cd", form.getAuth_cd());

			/*********************************************************
			 * 목록 조회
			 **********************************************************/
			JSONArray jsonArray = authMappingService.listMethodAuth(hm);

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();

		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}

	/**
	 * 메뉴-권한 등록
	 * 
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void insertMethodAuth(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템 코드
			 **********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
			
			String userId = (String) session
					.getAttribute("secfw.session.user_id");

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			AuthVO vo = new AuthVO();
			bind(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			vo.setSys_cd(sysCd);
			vo.setReg_id(userId);
			vo.setMod_id(userId);

			/*********************************************************
			 * 메뉴-권한 등록
			 **********************************************************/
			authMappingService.insertMethodAuth(vo);

			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.succ.insert", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();

		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}

	/**
	 * 사용자목록조회
	 * 
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void listPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템 코드
			 **********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			AuthVO vo = new AuthVO();
			bind(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			vo.setSys_cd(sysCd);
			vo.setSrch_use_yn("Y");

			/*********************************************************
			 * 목록 조회
			 **********************************************************/
			JSONArray jsonArray = authMappingService.listPage(vo);

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();

		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}

	/**
	 * 페이지-권한 목록조회
	 * 
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void listPageAuth(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템 코드
			 **********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			AuthVO vo = new AuthVO();
			bind(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			vo.setSys_cd(sysCd);

			/*********************************************************
			 * 목록 조회
			 **********************************************************/
			JSONArray jsonArray = authMappingService.listPageAuth(vo);

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();

		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}

	/**
	 * 페이지-권한 등록
	 * 
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void insertPageAuth(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템 코드
			 **********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
			
			String userId = (String) session
					.getAttribute("secfw.session.user_id");

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			AuthVO vo = new AuthVO();
			bind(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			vo.setSys_cd(sysCd);
			vo.setReg_id(userId);
			vo.setMod_id(userId);

			/*********************************************************
			 * 메뉴-권한 등록
			 **********************************************************/
			authMappingService.insertPageAuth(vo);

			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.succ.insert", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();

		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}
	
	
	/**
	 * 사용자목록조회
	 * 
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void listUserByRole(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템 코드
			 **********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			AuthForm form = new AuthForm();
			bind(request, form);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			HashMap hm = new HashMap();

			hm.put("sys_cd", sysCd);
			hm.put("srch_cntnt_type", form.getSrch_user_cntnt_type());
			hm.put("srch_cntnt", form.getSrch_user_cntnt());
			hm.put("role_cd", form.getRole_cd());
			/*********************************************************
			 * 목록 조회
			 **********************************************************/
			JSONArray jsonArray = authMappingService.listUserByRole(hm);

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();

		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}
	
	/**
	 * 전자검토자 등록
	 * 
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void insertMngComp(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템 코드
			 **********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
			String userId = (String) session.getAttribute("secfw.session.user_id");
			
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			AuthVO vo = new AuthVO();
			bind(request, vo);

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			vo.setSys_cd(sysCd);
			vo.setReg_id(userId);
			vo.setMod_id(userId);
			
			UserManagerVO userManagerVO = userManagerService.findUserByPk(vo.getMng_user_id());
			userManagerVO.setSec_reviewer_type(StringUtil.bvl((String) request.getParameter("sec_reviewer_type"), ""));
			if("O".equals(userManagerVO.getSec_reviewer_type())) {
				//userManagerVO.setUser_nm("검토자(해외)");
				//userManagerVO.setUser_nm_eng("Reviewer(Overseas)");
			} else if("D".equals(userManagerVO.getSec_reviewer_type())) {
				//userManagerVO.setUser_nm("검토자(국내)");
				//userManagerVO.setUser_nm_eng("Reviewer(Domestic)");
			}
			userManagerService.updateUser(userManagerVO);
			
			/*********************************************************
			 * 역할-사용자 등록
			 **********************************************************/
			authMappingService.insertMngComp(vo);

			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.succ.insert", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();

		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}
	
	/**
	 * 관리회사 조회
	 * 
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void listMngComp(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			AuthVO vo = new AuthVO();
			bind(request, vo);

			/*********************************************************
			 * 관리회사 목록조회
			 **********************************************************/
			JSONArray jsonArray = authMappingService.listMngComp(vo);

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jsonArray);
			out.flush();
			out.close();
		} catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage(
					"secfw.msg.error.error", null, new RequestContext(request)
							.getLocale()));

			response.setContentType(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}
	}

}
