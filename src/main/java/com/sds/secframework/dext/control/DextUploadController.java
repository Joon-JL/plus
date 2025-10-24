package com.sds.secframework.dext.control;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.dext.service.DextUploadService;

import devpia.dextupload.FileUpload;

public class DextUploadController extends CommonController {
	
	/**
	 * 
	 */
	private DextUploadService dextUploadService;
	
	/**
	 * @param clmsFileService
	 */
	public void setDextUploadService(DextUploadService clmsFileService) {
		this.dextUploadService = clmsFileService;
	}
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void uploadFiles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FileUpload fileUpload = new FileUpload(request, response, "UTF-8");

		try {
			/*********************************************************
			 * 업로드 처리
			**********************************************************/
			
			String fileInfos = dextUploadService.dextFileUpload(fileUpload);
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(fileInfos);
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
