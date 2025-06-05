package com.sds.secframework.dept.control;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.dept.dto.DeptVO;
import com.sds.secframework.dept.service.DeptService;

public class DeptController extends CommonController {

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private DeptService deptService;
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
		
	/**
	 * 부서 목록 조회(Tree형식)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void listDeptTree(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        DeptVO vo = new DeptVO();
			bind(request, vo);
						
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			vo.setTree_dept_cd(propertyService.getProperty("secfw.org.topDeptCd"));
			
    		/*********************************************************
			 * 목록 조회
			**********************************************************/
    		JSONArray jsonArray = deptService.listDeptTree(vo);		

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
	 * 하위 부서 조회
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void listChildDept(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			
			String treeDeptCd = StringUtil.bvl((String)request.getParameter("tree_dept_cd"), propertyService.getProperty("secfw.org.topDeptCd"));
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        DeptVO vo = new DeptVO();
			bind(request, vo);
						
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			vo.setTree_dept_cd(treeDeptCd);
			
    		/*********************************************************
			 * 목록 조회
			**********************************************************/
    		JSONArray jsonArray = deptService.listChildDept(vo);		

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
}
