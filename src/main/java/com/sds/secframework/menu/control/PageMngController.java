package com.sds.secframework.menu.control;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.dto.CommonForm;
import com.sds.secframework.common.util.ExcelLoader;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.menu.dto.PageMngForm;
import com.sds.secframework.menu.dto.PageMngVO;
import com.sds.secframework.menu.service.PageMngService;

public class PageMngController extends CommonController {
	
	private PageMngService pageMngService;
		
	public void setPageMngService(PageMngService pageMngService) {
		this.pageMngService = pageMngService;
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
			
			String forwardPage = StringUtil.bvl((String)request.getParameter("forwardURL"), "");
			/*********************************************************
			 * Forwarding URL
			**********************************************************/
	        // 등록페이지로 Forwading
	        String forwardURL = "";
	        
	        if("excelUpload".equals(forwardPage)) {
	        	forwardURL = "/WEB-INF/jsp/secfw/menu/PageMngListExcel.jsp";
	        } else {
	        	forwardURL = "/WEB-INF/jsp/secfw/menu/PageMngList.jsp";
	        }
	        
	        PageMngForm form = new PageMngForm();
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
	 * 페이지목록조회 Table
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void listPageTable(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드
			**********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
	
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        PageMngVO vo = new PageMngVO();
			bind(request, vo);
						
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			vo.setSys_cd(sysCd);
			
    		/*********************************************************
			 * 목록 조회
			**********************************************************/
    		JSONArray jsonArray = pageMngService.listPageTable(vo);		
    		
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
	 * 페이지 정보를 등록한다
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void insertPage(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드
			**********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");

	        
	        String userId = (String)session.getAttribute("secfw.session.user_id");

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        PageMngVO vo = new PageMngVO();
			bind(request, vo);
						
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
    		vo.setSys_cd(sysCd);
    		vo.setReg_id(userId);
    		vo.setMod_id(userId);
			
    		/*********************************************************
			 * 페이지 등록
			**********************************************************/
    		pageMngService.insertPage(vo);
    		
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
	 * 페이지삭제
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void deletePage(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드
			**********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
	
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        PageMngVO vo = new PageMngVO();
			bind(request, vo);
						
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
    		vo.setSys_cd(sysCd);
			
    		/*********************************************************
			 * 페이지삭제
			**********************************************************/
    		int result = pageMngService.deletePage(vo);		

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
	 * 페이지목록조회 Table
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void listPageTableUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드
			**********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
	
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        PageMngVO vo = new PageMngVO();
			bind(request, vo);
						
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			vo.setSys_cd(sysCd);
			
    		/*********************************************************
			 * 목록 조회
			**********************************************************/
    		JSONArray jsonArray = pageMngService.listPageTableUpload(vo);		
    		
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
	 * 페이지 정보를 등록한다
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void uploadExcel(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		try {
			 HttpSession session = request.getSession(false);
			 
			/*********************************************************
			 * 엑셀파일 스트림 획득
			**********************************************************/
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	        MultipartFile excelFile = multipartRequest.getFile("excelFile");

	        InputStream is = excelFile.getInputStream();
	        ExcelLoader excel = new ExcelLoader(is); 
	        
			/*********************************************************
			 * 시스템  코드
			**********************************************************/
	        String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");

	       
	        String userId = (String)session.getAttribute("secfw.session.user_id");

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        PageMngVO vo = new PageMngVO();
			bind(request, vo);
						
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
    		vo.setSys_cd(sysCd);
    		vo.setReg_id(userId);
    		vo.setMod_id(userId);
			
    		/*********************************************************
			 * 페이지 등록
			**********************************************************/
    		String result = pageMngService.uploadExcel(vo, excel);		
    		
    		//JSONObject jo = new JSONObject();
    		//jo.put("returnMessage", messageSource.getMessage("secfw.msg.succ.insert",  null, new RequestContext(request).getLocale()));
    		
    		response.setContentType("text/html; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print("<html><script language='javascript'>parent.listPageTable();alert('" + messageSource.getMessage("secfw.msg.succ.insert",  null, new RequestContext(request).getLocale()) + "');</script></html>");
    		out.flush();
    		out.close();
    		
		} catch (Exception e) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
    		response.setContentType("text/html; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print("<html><script language='javascript'>alert('" + messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()) + "');</script></html>");
    		out.flush();
    		out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
    		response.setContentType("text/html; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print("<html><script language='javascript'>alert('" + messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()) + "');</script></html>");
    		out.flush();
    		out.close();
		}  
    }

	/**
	 * 페이지 정보를 등록한다
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void insertPageUpload(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		try {
			 HttpSession session = request.getSession(false);
			 
			/*********************************************************
			 * 시스템  코드
			**********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");

	       
	        String userId = (String)session.getAttribute("secfw.session.user_id");

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        PageMngVO vo = new PageMngVO();
			bind(request, vo);
						
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
    		vo.setSys_cd(sysCd);
    		vo.setReg_id(userId);
    		vo.setMod_id(userId);
			
    		/*********************************************************
			 * 페이지 등록
			**********************************************************/
    		pageMngService.insertPageUpload(vo);
    		
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
	 * 페이지삭제
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void deletePageUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드
			**********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
	
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        PageMngVO vo = new PageMngVO();
			bind(request, vo);
						
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
    		vo.setSys_cd(sysCd);
			
    		/*********************************************************
			 * 페이지삭제
			**********************************************************/
    		int result = pageMngService.deletePageUpload(vo);		

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
	 * 페이지 엑셀 업로드 데이타를 운영시스템으로 전송
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void transferExcelUpload(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		
		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드
			**********************************************************/
			String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");

	        
	        String userId = (String)session.getAttribute("secfw.session.user_id");

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        PageMngVO vo = new PageMngVO();
			bind(request, vo);
						
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
    		vo.setSys_cd(sysCd);
    		vo.setReg_id(userId);
    		vo.setMod_id(userId);
			
    		/*********************************************************
			 * 페이지 등록
			**********************************************************/
    		pageMngService.transferExcelUpload(vo);
    		
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
}
