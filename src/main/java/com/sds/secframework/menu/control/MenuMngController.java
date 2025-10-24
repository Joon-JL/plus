package com.sds.secframework.menu.control;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.dto.CommonForm;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.menu.dto.MenuMngVO;
import com.sds.secframework.menu.service.MenuMngService;

public class MenuMngController extends CommonController {
	
	private MenuMngService menuService;
		
	public void setMenuService(MenuMngService menuService) {
		this.menuService = menuService;
	}
	
	/**
	 * 페이지 포워드.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		try {
			/*********************************************************
			 * Forwarding URL
			**********************************************************/
	        // 등록페이지로 Forwading
	        String forwardURL = "/WEB-INF/jsp/secfw/menu/MenuMngList.jsp";
	        
	        //-----------BIND:바인드설정 -------------------- 
	        CommonForm form = new CommonForm();
	        bind(request, form);

			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
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
	 * 메뉴목록조회 Tree
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void listMenuTree(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드
			**********************************************************/
	        String sysCd  = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
	
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        MenuMngVO vo = new MenuMngVO();
			bind(request, vo);
						
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			vo.setSys_cd(sysCd);
			vo.setMenu_id(propertyService.getProperty("secfw.menu.topMenuId"));
			
    		/*********************************************************
			 * 목록 조회
			**********************************************************/
    		JSONArray jsonArray = menuService.listMenuTree(vo);		

    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jsonArray);
    		out.flush();
    		out.close();
    		
		} catch (Exception e) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} 
    }
	
	/**
	 * 메뉴목록조회 Table
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void listMenuTable(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드
			**********************************************************/
			String sysCd  = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
	
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        MenuMngVO vo = new MenuMngVO();
			bind(request, vo);
						
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			vo.setSys_cd(sysCd);
			vo.setMenu_id(vo.getSelect_menu_id());
			
    		/*********************************************************
			 * 목록 조회
			**********************************************************/
    		JSONArray jsonArray = menuService.listMenuTable(vo);		
    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jsonArray);
    		out.flush();
    		out.close();
    		
		} catch (Exception e) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} 
    }
	
	/**
	 * 메뉴 정보를 등록한다
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void insertMenu(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드
			**********************************************************/
			String sysCd  = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");

	        
	        String userId = (String)session.getAttribute("secfw.session.user_id");

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        MenuMngVO vo = new MenuMngVO();
			bind(request, vo);
						
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
    		vo.setSys_cd(sysCd);
    		vo.setReg_id(userId);
    		vo.setMod_id(userId);
			
    		/*********************************************************
			 * 메뉴 등록
			**********************************************************/
    		JSONArray jsonArray =menuService.insertMenu(vo);		

    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jsonArray);
    		out.flush();
    		out.close();
    		
		} catch (Exception e) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		}  
    }

	/**
	 * 메뉴삭제
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void deleteMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드
			**********************************************************/
			String sysCd  = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
	
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        MenuMngVO vo = new MenuMngVO();
			bind(request, vo);
						
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
    		vo.setSys_cd(sysCd);
			
    		/*********************************************************
			 * 메뉴삭제
			**********************************************************/
    		int result = menuService.deleteMenu(vo);		

    		HashMap returnMap = new HashMap();
    		returnMap.put("returnMessage", messageSource.getMessage("secfw.msg.succ.delete",  null, new RequestContext(request).getLocale()));
    		
    		JSONObject jsonObject = JSONObject.fromObject(returnMap);
    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jsonObject);
    		out.flush();
    		out.close();
    		
		} catch (Exception e) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		}  
    }
}
