package com.sds.secframework.code.control;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.code.dto.CodeVO;
import com.sds.secframework.code.service.CodeService;
import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.StringUtil;

public class CodeController extends CommonController {

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */	
	private CodeService codeService;
	public void setCodeService(CodeService codeService) {
		this.codeService = codeService;
	}
	
	/**
	 * 그룹코드 리스트
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void listGrpCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드
			**********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
	
	        /*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			CodeVO vo = new CodeVO();
			bind(request, vo);
			
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			vo.setSys_cd(sysCd);

			/*********************************************************
			 * 목록 조회
			**********************************************************/
			JSONArray jsonArray = codeService.listGrpCode(vo);		
	
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
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
			out.flush();
			out.close();
		}
    }

	/**
	 * 공통코드 리스트
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void listCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드
			**********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			CodeVO vo = new CodeVO();
			
			bind(request, vo);

			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			vo.setSys_cd(sysCd);
			vo.setMenu_id((String)request.getParameter("menu_id"));

			/*********************************************************
			 * 목록 조회
			**********************************************************/
			JSONArray jsonArray = codeService.listCode(vo);
			
	
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
	 * 공통코드 리스트
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void listCodeCombo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드
			**********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			CodeVO vo = new CodeVO();
			bind(request, vo);
			
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			vo.setSys_cd(sysCd);
			
			/*********************************************************
			 * 목록 조회
			**********************************************************/
			JSONArray jsonArray = codeService.listCodeCombo(vo);		
	
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
	 * 그룹코드 등록
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void insertGrpCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String errCode ="";
		
		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			 **********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
			String userId = StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"), "");
			
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			CodeVO vo = new CodeVO();
			bind(request, vo);		
			
			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			vo.setSys_cd(sysCd);
			vo.setReg_id(userId);
			vo.setMod_id(userId);
			
    		/*********************************************************
			 * 그룹코드 등록
			**********************************************************/
			//중복체크
			String[] newGrpCds = vo.getNew_grp_cds();
			
			if(newGrpCds != null && newGrpCds.length>0) {
				for(int i=0; i<newGrpCds.length; i++) {
					HashMap hm = new HashMap();
					hm.put("sys_cd", sysCd);
					hm.put("grp_cd", newGrpCds[i]);
					String existsYn = codeService.existsGrpCode(hm);
					
					if("Y".equals(existsYn)) {
						errCode = newGrpCds[i];
						throw new Exception();
					}
					
				}
			} 
			
    		codeService.insertGrpCode(vo);		
			
    		HashMap returnMap = new HashMap();
    		returnMap.put("returnMessage", messageSource.getMessage("secfw.msg.succ.insert",  null, new RequestContext(request).getLocale()));
    		
    		JSONObject jsonObject = JSONObject.fromObject(returnMap);
    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jsonObject);
    		out.flush();
    		out.close();
    		
		} catch (Exception e) {
			
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()) + "(" + errCode + " Duplication!");

    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()) + "(" + errCode + " Duplication!");

    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} 
		
	}

	/**
	 * 공통코드 등록
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void insertCode(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String errCode ="";

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
	        String userId = StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"), "");
	        
	        /*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			CodeVO vo = new CodeVO();		
			bind(request, vo);	
			
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
	        vo.setSys_cd(sysCd);
	        vo.setReg_id(userId);
			vo.setMod_id(userId);

    		/*********************************************************
			 * 코드 등록
			**********************************************************/
			//중복체크
			String[] newCds = vo.getNew_cds();
			
			if(newCds != null && newCds.length>0) {
				for(int i=0; i<newCds.length; i++) {
					HashMap hm = new HashMap();
					hm.put("sys_cd", sysCd);
					hm.put("grp_cd", newCds[i]);
					String existsYn = codeService.existsCode(hm);
					
					if("Y".equals(existsYn)) {
						errCode = newCds[i];
						throw new Exception();

					}
				}
			}

			codeService.insertCode(vo);		

    		HashMap returnMap = new HashMap();
    		returnMap.put("returnMessage", messageSource.getMessage("secfw.msg.succ.insert",  null, new RequestContext(request).getLocale()));
    		
    		JSONObject jsonObject = JSONObject.fromObject(returnMap);
    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jsonObject);
    		out.flush();
    		out.close();
    		
		} catch (Exception e) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()) + "(" + errCode + " Duplication!");

    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()) + "(" + errCode + " Duplication!");

    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} 
		
    }

	/**
	 * 그룹코드 정보 삭제
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void deleteGrpCode(HttpServletRequest request,	HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드
			**********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
	
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			CodeVO vo = new CodeVO();		
			bind(request, vo);	

			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
    		vo.setSys_cd(sysCd);
			
    		/*********************************************************
			 * 그룹코드삭제
			**********************************************************/
    		codeService.deleteGrpCode(vo);		

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
	
	/**
	 * 공통코드 정보 삭제
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void deleteCode(HttpServletRequest request,	HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드
			**********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
	
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			CodeVO vo = new CodeVO();		
			bind(request, vo);	

			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
    		vo.setSys_cd(sysCd);

    		/*********************************************************
			 * 그룹코드삭제
			**********************************************************/
    		codeService.deleteCode(vo);		

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
