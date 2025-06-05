package com.sds.secframework.auth.control;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.auth.dto.AuthForm;
import com.sds.secframework.auth.dto.AuthVO;
import com.sds.secframework.auth.service.AuthMngService;
import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.dto.CommonForm;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.common.util.Token;

/**
 * <P>권한관리 Class</P>
 * 시스템에서 사용되어지는 권한을 관리하는 클래스
 * 
 * [지원기능]<BR>
 * <BR>
* - 권한목록 조회<BR>
* - 권한등록<BR>
* - 권한삭제<BR>
*  <br>
 *   
 * @version V1.0
 * 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */

public class AuthMngController extends CommonController {

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private AuthMngService authMngService;
	public void setAuthMngService(AuthMngService authMngService) {
		this.authMngService = authMngService;
	}

	/**
	 * 페이지 포워드.
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
	        String forwardURL = "/WEB-INF/jsp/secfw/auth/AuthMngList.jsp";
	        
	        //-----------BIND:바인드설정 -------------------- 
	        AuthVO vo = new AuthVO();
			bind(request, vo);

			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("command", vo);
			
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
	 * 권한 리스트 정보
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void listAuth(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			 HttpSession session = request.getSession(false);
			 
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
		       
	        String userId = (String)session.getAttribute("secfw.session.user_id");

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
    		hm.put("srch_cntnt",    form.getSrch_cntnt());
    		hm.put("srch_use_yn",    form.getSrch_use_yn());
	    		
    		 /*********************************************************
			 * 목록 조회
			**********************************************************/
    		JSONArray jsonArray = authMngService.listAuth(hm);		
    		
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
	 * 권한 정보를 등록한다
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void insertAuth(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
	
	        String userId = (String)session.getAttribute("secfw.session.user_id");

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			AuthVO vo = new AuthVO();
			bind(request, vo);
			
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			vo.setSys_cd(sysCd);   //시스템코드
			vo.setReg_id(userId);  //등록자아이디
    		vo.setMod_id(userId);
			
			/*********************************************************
			 * 내역 등록
			**********************************************************/
			if(Token.isValid(request)) {
				authMngService.insertAuth(vo);		
			}
			
    		JSONObject jo = new JSONObject();
    		jo.put("returnMessage", "SUCC");
    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
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
	 * 권한 정보를 삭제한다
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void deleteAuth(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		try {
			 HttpSession session = request.getSession(false);
			 
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
		       
	        String userId = (String)session.getAttribute("secfw.session.user_id");

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        AuthForm form = new AuthForm();
	        AuthVO vo = new AuthVO();
			bind(request, form);
			bind(request, vo);
			
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			vo.setSys_cd(sysCd);  //시스템코드
			
	    	/*********************************************************
			 * 권한관리 내역 삭제
			**********************************************************/
	    	authMngService.deleteAuth(vo);		
		    	  
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
