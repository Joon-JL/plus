/**
* Project Name : CLMS
* File Name : ClmsFileController.java
* Description : 공통 첨부파일 Controller
* Author : 신남원
* Date : 2011. 08. 04
* Copyright : SamSung
*/
package com.sec.common.clmsfile.control;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.log.dto.LogVO;
import com.sec.common.clmsfile.dto.ClmsFileForm;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import com.sec.common.clmsfile.dto.ComFileForm;
import com.sec.common.clmsfile.dto.ComFileVO;
import com.sec.common.clmsfile.dto.FileForm;
import com.sec.common.clmsfile.service.ClmsFileService;

public class ClmsFileController extends CommonController {
	private ClmsFileService clmsFileService;
	public void setClmsFileService(ClmsFileService clmsFileService) {
		this.clmsFileService = clmsFileService;
	}

	/**********************************************************************
	* ClmsFile 처리 영역
	**********************************************************************/
	
	/**
	 * Clms파일  페이지 포워드.
	 * 작성자 : 신남원 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardClmsFilePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/	        
			ClmsFileForm form = new ClmsFileForm();
			ClmsFileVO vo = new ClmsFileVO();

			bind(request, form);
			bind(request, vo);
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Property Setting
			**********************************************************/
			
			String file_bigclsfcn = "";  // 법무 F001, 계약  F008로 보내야 합니다.
			
			String maxUploadSize = "";
	        //최대 첨부 용량
			if("Y".equals(StringUtil.bvl(vo.getSize_gbn(), ""))){
				maxUploadSize = "31457280"; //CPMS IF 30MB 제한
			}else{
				maxUploadSize = propertyService.getProperty("clms.uploadify.maxFileSize"); //CLM 50MB 제한
			}
	        
			//허용할 파일 확장자 리스트
	        ArrayList allowFileList = new ArrayList();
			String allowFileListProperty = propertyService.getProperty("clms.uploadify.allowFileList");
			//계약체결본 등록시 pdf파일만 받기 위해 그페이지에서만 별도로 값을 넘겨서 처리한다.
			if(!"".equals(StringUtil.bvl(form.getPreAllowFileList(), ""))){
				allowFileListProperty = form.getPreAllowFileList();
			}
			
			StringTokenizer st = new StringTokenizer(allowFileListProperty, ",");
			
			while(st.hasMoreTokens()) {
				String extNm = st.nextToken();
				allowFileList.add(extNm);
			}	
	        
			//fileInfos
			String fileInfos = "";
			if("modify".equals(form.getView_gbn()) || "download".equals(form.getView_gbn()) || "reAttach".equals(form.getView_gbn())){
				fileInfos = clmsFileService.getClmsDbFileInfos(vo);
			}
			form.setFileInfos(fileInfos);

			/*********************************************************
			 * Forwarding URL
			**********************************************************/
	        //String forwardURL = "/WEB-INF/jsp/common/clmsfile/ClmsFileUpload.jsp";
			String forwardURL = "/WEB-INF/jsp/common/clmsfile/ClmsFileUploadByUploadfive.jsp";

			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
	        ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("maxUploadSize", maxUploadSize);
			mav.addObject("allowFileList", allowFileList);
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
	 * ClmsFile upload
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *  ,Object command, BindException errors
	 */
	public void doClmsUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * 업로드 처리 
			**********************************************************/
			String fileInfos = clmsFileService.clmsFileUpload(request);
			
			JSONObject jo = new JSONObject();
			jo.put("fileInfos" , fileInfos);
			
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
	 * ClmsFile upload
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *  ,Object command, BindException errors
	 */
	public void doOutLookUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * 업로드 처리 
			**********************************************************/
			String fileInfos = clmsFileService.clmsFileUpload(request);
			
			
			
			/*************************************************
			 * 첨부파일 저장
			 *************************************************/
			ClmsFileVO fvo = new ClmsFileVO();

			fvo.setSys_cd(request.getParameter("sys_cd"));
			fvo.setFile_bigclsfcn(request.getParameter("file_bigclsfcn"));
			fvo.setFile_midclsfcn(request.getParameter("file_midclsfcn"));
			fvo.setFile_smlclsfcn(request.getParameter("file_smlclsfcn"));
			fvo.setRef_key(request.getParameter("ref_key"));
			fvo.setReg_id(request.getParameter("reg_id"));
			fvo.setFileInfos(fileInfos);
			
			clmsFileService.mngClmsAttachFile(fvo);
			
			JSONObject jo = new JSONObject();
			jo.put("fileInfos" , fileInfos);
			
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
	 * 2014-08-15 Kevin Changed. 
	 * 결국 데이터 조회 결과는 파일 하나를 리턴 하지만, 코드 내에서는 while loop를 돈다. 이를 첫 번째 파일만 가져와서 처리하는 것으로 변경 함.
	 * 파일 다운로드 로그 저장 코드 삭제.
	 * ClmsFile download
	 * @param request : 
	 * @param response :null
	 * @throws Exception
	 */
	public ModelAndView doClmsDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean bsession_error = true; //세션이 없어져서 생긴 에러인 여부
		String file_id = "";
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/	        
			ClmsFileForm form = new ClmsFileForm();
			ClmsFileVO vo = new ClmsFileVO();
			
			bind(request, form);
			bind(request, vo);
			file_id = (String)vo.getFile_id(); //파일id
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			bsession_error = false; //바로 위의 세션체크하는 부분은 통과했으므로 세션문제는 아니므로 false
			
			List collist = clmsFileService.clmsFileDownload(vo);
			
		    if(collist != null && collist.size() > 0){
				ListOrderedMap hList = (ListOrderedMap)collist.get(0);
	   			String filename = (String)hList.get("org_file_nm");
	   			String filePath = (String)hList.get("file_path");
	   			String fileext  = filename.substring(filename.lastIndexOf(".")+1,filename.length());
	   			
	   			File file = new File(filePath);
	   			long length = file.length();
	   				
				ServletContext context = RequestContextUtils.getWebApplicationContext(request).getServletContext();
				String mimetype = context.getMimeType(file.getAbsolutePath());
				
		    	// header정보 
				response.setHeader("Connection", "close");
				response.setHeader("Content-Transfer-Encoding", "binary");
				response.setContentLength((int) length);
				response.setContentType(mimetype != null ? mimetype : "application/octet-stream");
				
				if (request.getHeader("User-Agent") == null) { // User-Agent가 없을 경우
					response.setCharacterEncoding("ISO-8859-1");
					filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
					response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
				} else if (request.getHeader("User-Agent").indexOf("MSIE") > 0 ) { // User-Agent == MSIE일 경우
					if ( fileext.equals("html") || fileext.equals("mht") || fileext.equals("htm") || fileext.equals("mhtml") || fileext.equals("eml")  ){
						response.setCharacterEncoding("euc-kr");
					}else{
						response.setCharacterEncoding("UTF-8");							
					}
					filename = URLEncoder.encode(filename, "UTF-8");
					filename = filename.replaceAll("\\+", " ");
					response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
				} else if (request.getHeader("User-Agent").indexOf("Mozilla") > 0 ) { // User-Agent == Mozilla일 경우
					response.setCharacterEncoding("ISO-8859-1");
					filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
					response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
				} else { // User-Agent == 그 외의 경우
					response.setCharacterEncoding("UTF-8");
					filename = URLEncoder.encode(filename, "UTF-8");
					filename = filename.replaceAll("\\+", " ");
					response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
				}
				
				DataInputStream in = new DataInputStream(new FileInputStream(file));
				ServletOutputStream sot = response.getOutputStream();
				
				boolean bstream_close = false;
				try{
					byte[] bbuf = new byte[1024000];
					while ((length = in.read(bbuf)) > 0){
						sot.write(bbuf, 0, (int) length);
					}
					
					in.close();
					sot.flush();
					sot.close();
					bstream_close = true;
				}catch(Exception e){
					// 사용자 download 취소
					e.printStackTrace();
				}finally{
					if(! bstream_close){
						in.close();
						sot.close();
					}
				}
				return null;
			}else{
				ModelAndView mav = new ModelAndView();
				mav.setViewName("/WEB-INF/jsp/secfw/error/PageNotFound.jsp");
				return mav;
			}
		} catch (Exception e) {
			e.printStackTrace();
			/*
			 * bsession_error 값이 true인 경우 세션에 의한 오류이므로 메시지문구를 변경처리해주기 위해
			 * 세션오류시의 문구 : 법무시스템에 사용자등록이 되어있거나, 한번이라도 접속하신 분은 다시 파일을 클릭하십시요. 
			 * 2013. 07. 24 수정
			 * */
			if(bsession_error){
				throw new Exception("CLMS_DOWNLOAD_SESSION_ERROR"+file_id);
			}else{
				throw new Exception(e);
			}
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception(t);
		}
	}

	/**
	 * ClmsFile Delete
	 * 실제 파일이 삭제되지 않고 삭제된 파일정보(fileInfos)만 반환
	 * 일반적으로 수정시 사용 됨
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void doClmsDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/	        
			ClmsFileForm form = new ClmsFileForm();
			ClmsFileVO vo = new ClmsFileVO();

			bind(request, form);
			bind(request, vo);
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);	
			
			String fileInfos = "";
//			getLogger().debug(">>>>>>>>>>fileId="+vo.getFile_id());

			if(!"".equals(vo.getFile_id())){
				fileInfos = clmsFileService.clmsFileDelete(vo);
			}    		
//			getLogger().debug(">>>>>>>>>>fileInfos="+fileInfos);
			
			response.setContentType("text/html; charset=UTF-8");
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
	
	/**********************************************************************
	* ComFile 처리 영역 (Mail_메일, Approval_결재)
	**********************************************************************/

	/**
	 * ComFile 페이지 포워드.
	 * 작성자 : 신남원 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardComFilePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/	        
			ComFileForm form = new ComFileForm();
			ComFileVO vo = new ComFileVO();

			bind(request, form);
			bind(request, vo);
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Property Setting
			**********************************************************/
	        //최대 첨부 용량
	        String maxUploadSize = propertyService.getProperty("clms.uploadify.maxFileSize");
			//허용할 파일 확장자 리스트
	        ArrayList allowFileList = new ArrayList();
			String allowFileListProperty = propertyService.getProperty("clms.uploadify.allowFileList");
			StringTokenizer st = new StringTokenizer(allowFileListProperty, ",");
			
			while(st.hasMoreTokens()) {
				String extNm = st.nextToken();
				allowFileList.add(extNm);
			}	
	        
			//fileInfos
			String fileInfos = "";
			if("modify".equals(form.getView_gbn()) || "download".equals(form.getView_gbn())){
				fileInfos = clmsFileService.getComDbFileInfos(vo);
			}
			form.setFileInfos(fileInfos);

			/*********************************************************
			 * Forwarding URL
			**********************************************************/
	        //String forwardURL = "/WEB-INF/jsp/common/clmsfile/ComFileUpload.jsp";
			String forwardURL = "/WEB-INF/jsp/common/clmsfile/ComFileUploadByUploadfive.jsp";

			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
	        ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("maxUploadSize", maxUploadSize);
			mav.addObject("allowFileList", allowFileList);
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
	 * ComFile upload
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *  ,Object command, BindException errors
	 */
	public void doComUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * 업로드 처리
			**********************************************************/
			
			String fileInfos = clmsFileService.comFileUpload(request);
			getLogger().debug(">>>>>>>ComFile Upload Info="+fileInfos);
			JSONObject jo = new JSONObject();
			jo.put("fileInfos"    , fileInfos);
			
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
	 * ComFile download
	 * @param request : 
	 * @param response :null
	 * @throws Exception
	 */
	public ModelAndView doComDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/	        
			ComFileForm form = new ComFileForm();
			ComFileVO vo = new ComFileVO();

			bind(request, form);
			bind(request, vo);
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);					

//			getLogger().debug(">>>>>>>"+vo.getFile_id()+","+vo.getRef_key()+","+vo.getFile_bigclsfcn()+","+vo.getFile_midclsfcn()+","+vo.getFile_smlclsfcn());

			List collist = null; 
			collist = clmsFileService.comFileDownload(vo);
			
//			getLogger().debug("collist size=" + collist.size());
			
		    if(collist != null && collist.size() > 0){
				
		        Iterator it = collist.iterator();
		   		while(it.hasNext())
		   		{
		   			ListOrderedMap hList = (ListOrderedMap)it.next();
		   			
		   			
		   			String filename = (String)hList.get("file_name");
		   			String filePath = (String)hList.get("file_path");
		   			File file = new File(filePath);
		   			
		   			long length = file.length();
		   			
					ServletContext context = RequestContextUtils.getWebApplicationContext(request).getServletContext();
					String mimetype = context.getMimeType(file.getAbsolutePath());
					
			    	// header정보 
					response.setHeader("Connection", "close");
					response.setHeader("Content-Transfer-Encoding", "binary");
					response.setContentLength((int) length);
					response.setContentType(mimetype != null ? mimetype : "application/octet-stream");
				
					if (request.getHeader("User-Agent") == null) { // User-Agent가 없을 경우
						response.setCharacterEncoding("ISO-8859-1");
						filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
						response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
					} else if (request.getHeader("User-Agent").indexOf("MSIE") > 0 ) { // User-Agent == MSIE일 경우
						response.setCharacterEncoding("UTF-8");
						filename = URLEncoder.encode(filename, "UTF-8");
						filename = filename.replaceAll("\\+", " ");
						response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
					} else if (request.getHeader("User-Agent").indexOf("Mozilla") > 0 ) { // User-Agent == Mozilla일 경우
						response.setCharacterEncoding("ISO-8859-1");
						filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
						response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
					} else { // User-Agent == 그 외의 경우
						response.setCharacterEncoding("UTF-8");
						filename = URLEncoder.encode(filename, "UTF-8");
						filename = filename.replaceAll("\\+", " ");
						response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
					}
					
					try{
						byte[] bbuf = new byte[1024000];
						DataInputStream in = new DataInputStream(new FileInputStream(file));
						ServletOutputStream sot = response.getOutputStream();
						while ((length = in.read(bbuf)) > 0) sot.write(bbuf, 0, (int) length);
						in.close();
						sot.flush();
						sot.close();
						
						
						/*********************************************************
						 * 파일 다운로드 로그 등록
						**********************************************************/
						LogVO   lvo   = new LogVO();
						bind(request, lvo);
						COMUtil.getUserAuditInfo(request, lvo);	
						/*********************************************************
						 * 파라미터세팅
						 **********************************************************/
						lvo.setUser_id(vo.getSession_user_id());
						lvo.setUser_nm(vo.getSession_user_nm());
						lvo.setDept_nm(vo.getSession_dept_nm());
						lvo.setFile_path((String)hList.get("file_path"));
						lvo.setFile_nm((String)hList.get("file_name"));
						clmsFileService.fileDownLog(lvo);
					}catch(Exception e){
						// 사용자 download 취소
						e.printStackTrace();
					}
					return null;
		   		}
			}else{
				ModelAndView mav = new ModelAndView();
				mav.setViewName("/WEB-INF/jsp/secfw/error/PageNotFound.jsp");
				return mav;
			}
		    return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception(t);
		}
	}	
	
	/**
	 * ComFile Delete
	 * 실제 파일이 삭제되지 않고 삭제된 파일정보(fileInfos)만 반환
	 * 일반적으로 수정시 사용 됨
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void doComDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/	        
			ComFileForm form = new ComFileForm();
			ComFileVO vo = new ComFileVO();

			bind(request, form);
			bind(request, vo);
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);	
			
			String fileInfos = "";
//			getLogger().debug(">>>>>>>>>>fileId="+vo.getFile_id());

			if(!"".equals(vo.getModule_id()) && !"".equals(vo.getMis_id()) && !"".equals(vo.getSequence())){
				fileInfos = clmsFileService.comFileDelete(vo);
			}    		
//			getLogger().debug(">>>>>>>>>>fileInfos="+fileInfos);
			
			response.setContentType("text/html; charset=UTF-8");
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
	
	/**********************************************************************
	* FileUpload 처리 영역
	**********************************************************************/	
	
	/**
	 * File 업로드 페이지 포워드(Popup)
	 * 사진 및 일반 단독 파일 업로드시 사용하는 화면
	 * 작성자 : 신남원 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardPopFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/	        
			FileForm form = new FileForm();

			bind(request, form);
			COMUtil.getUserAuditInfo(request, form);
			
			/*********************************************************
			 * Property Setting
			**********************************************************/
			//허용할 파일 확장자 리스트
	        ArrayList allowFileList = new ArrayList();
			String allowFileListProperty = "";
			//이미지 업로드 일 경우
			if("image".equals(form.getFileType())){
				allowFileListProperty = propertyService.getProperty("image.uploadify.allowFileList");
			}
			
			if(!"".equals(allowFileListProperty)){
				StringTokenizer st = new StringTokenizer(allowFileListProperty, ",");
				
				while(st.hasMoreTokens()) {
					String extNm = st.nextToken();
					allowFileList.add(extNm);
				}	
			}else{
				allowFileList = null;
			}
			/*********************************************************
			 * Forwarding URL
			**********************************************************/
	        String forwardURL = "/WEB-INF/jsp/common/clmsfile/FileUpload.jsp";

			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
	        ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("allowFileList", allowFileList);
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
	 * File upload
	 * 이미지 파일또는 엑셀파일등 업로드시 사용
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *  ,Object command, BindException errors
	 */
	public void doFileUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * 업로드 처리
			**********************************************************/
			String fileInfos = clmsFileService.fileUpload(request);
//			getLogger().error(">>>>>>>>>>>fileInfos="+fileInfos);
			JSONObject jo = new JSONObject();
			
			jo.put("fileInfos"    , fileInfos);
			
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
	 * 업로드된 파일 이동 및 ReName 처리
	 */
	public void doMoveFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * 업로드 처리
			**********************************************************/
			String fileInfos = clmsFileService.moveFile(request);
			JSONObject jo = new JSONObject();
			jo.put("fileInfos"    , fileInfos);
			
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
	/**********************************************************************
	* 미확인 영역
	**********************************************************************/	
	
	
	
	/**
	 * (메일 발송 대상자 엑셀파일 업로드).
	 *
	 * @param  
	 * @return Log
	 * @throws 
	*/	
	public void UploadExcel(HttpServletRequest request,	HttpServletResponse response)  throws Exception
	{
		//String strForward = "";
		//String strLangCode = "";
		//String empno = "";
		//String dofogubun = "";
		//String rolecode = "";
		String[] receiver;
		
		//===================================================================
	    // 세션체크
	    //===================================================================
		HttpSession session = request.getSession();
	     
	    //===================================================================
	    // 세션정보 획득
	    //===================================================================
		//strLangCode = (String) session.getAttribute("LANGUAGE_FLAG");
		//rolecode = (String) session.getAttribute("ROLE_CODE");
		 
	 	 
	 		 
	    //===================================================================
		//각 업무를 정의한다.
	    //=================================================================== 
		try {
			List list = null;
			//각 업무를 정의한다.
			//List list = clmsFileService.fileUpload(request);
		
			String upfilename = "";
			
		 	StringBuffer fileInfos = new StringBuffer();
		    if( list != null && list.size() > 0){
		        Iterator it = list.iterator();
		   		while(it.hasNext())
		   		{
		   			HashMap hm = (HashMap)it.next();

		   			upfilename = (String)hm.get("FILE_PATH");
					
		   		}
			}
		    
			Collection col = getSurveyTargetEmpExcelUploadList(upfilename); // 엑셀파일 조회
		 	 
//			getLogger().debug("col=====size====>>>"+col.size());
			
			int rowcnt = col.size(); // 엑셀 row 수
			receiver =  new String[rowcnt];
			
			Iterator itr_ins = col.iterator(); 
			int rsltCnt = 0;   
			 
			String sTemp = "";
			while(itr_ins.hasNext()){ 
				HashMap item = (HashMap) itr_ins.next();
				
				if((String)item.get("2") == null ) continue;
				  
				sTemp = ((String)item.get("2")).trim();
				if(sTemp.indexOf("@")==-1) continue;
					
				receiver[rsltCnt] =  "t|" + sTemp;

				rsltCnt++;
			}
	 		
			session.setAttribute("SYS_MAIL_COLLECTION", receiver);
			
			JSONObject jo = new JSONObject();
			//jo.put("recevierList"    , receiver.toString());
			jo.put("rsltCnt"    , Integer.toString(rsltCnt));
			
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
	    * @ejb.interface-method view-type="remote"
	    * 업로드된 엑셀파일로 부터 data 가져오기

	    * @ param  Hashmap param
	    * @ return   Collection : 응답내역
	    * @ exception Exception
	    */
		public Collection getSurveyTargetEmpExcelUploadList(String strFileFullPath) throws Exception {
			//EJB 모든 함수는 수행 전에  getLogger().debug("begin"); 를 표시한다.
//			getLogger().debug("begin");
			
			Collection rsltLst = null;
			try
			{

				rsltLst	= getExcelData(strFileFullPath);
				//getLogger().debug("excel size="+rsltLst);
						
						
			}
			catch (Exception e)
			{
				//EJB 모든 함수에서 예외처리는 다음과 같이 한다.
		   		e.printStackTrace();
		   		getLogger().error("::::::::::   getSurveyTargetEmpExcelUploadList \n"+e);
		       	//throw new BaseException("::::::::::   getSurveyTargetEmpExcelUploadList");
			}
			
			//EJB 모든 함수는 수행 후에  logger.debug("end"); 를 표시한다.
//			getLogger().debug("end");
			return rsltLst;
		}
		
		
		  /**
		  * Excel File을 읽어서 리턴한다.
		  *
		  * @param     file Excel File
		  * @return    Excel File Data (Collection안에 HashMap(0~, 값))
		  * @exception Exception
		  */
			public Collection getExcelData(File file) throws Exception{

				ArrayList arrRowData = new ArrayList();

				//파일로부터 Workbook 를 생성한다. Workbook은 하나의 xls파일을 말한다.
				Workbook w = Workbook.getWorkbook(file);

				//시트의 개수만큼 루프 돈다.(기본적으로 시트는 첫번째를 기준으로 한다)
				for(int sheet = 0; sheet < 1; sheet++){

					// i번째 시트를 얻는다.
					Sheet s = w.getSheet(sheet);

					//셀을 선언
					Cell[] row = null;
					//열의 개수만큼 루프를 돈다.

					for(int i = 1; i < s.getRows(); i++){

						//i 번째 열을 셀 배열로 받는다.
						row = s.getRow(i);

						//데이타가 있는 마지막 셀을 찾는다.
						int nonblank = 0;

						for(int j = row.length-1; j >= 0 ; j--){
							if(row[j].getType() != CellType.EMPTY ){
								nonblank = j;
								break;
							}
						}


						HashMap hCellData = new HashMap();
						for(int k = 0; k < nonblank+1; k++){  // 김명훈 수정 마지막 컬럼데이터를 안겨져와서 +1 수정 
							hCellData.put( String.valueOf(k), row[k].getContents());
						}
						arrRowData.add(i-1, hCellData);
					}
				}
				return arrRowData;
			}
		  /**
		  * Excel File을 읽어서 리턴한다.
		  *
		  * @param     strFileFullPath Excel File Full Path
		  * @return    Excel File Data (Collection>>HashMap)
		  * @exception Exception
		  */
			public Collection getExcelData(String strFileFullPath) throws Exception{
				return getExcelData(new File(strFileFullPath));
			}

	/**
	 * 첨부파일 여부 확인
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public void getClmsFilePageCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/	        
			ClmsFileForm form = new ClmsFileForm();
			ClmsFileVO vo = new ClmsFileVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Property Setting
			**********************************************************/
			JSONArray jsonArray = clmsFileService.getClmsFilePageCheck(vo);
    		
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
	 * CPMS IF ClmsFile upload
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *  ,Object command, BindException errors
	 */
	public ModelAndView doCpmsUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileInfos = "";
		try {
			/*********************************************************
			 * 업로드 처리
			**********************************************************/
			fileInfos = clmsFileService.cpmsFileUpload(request);
			
			/*********************************************************
			 * Forwarding URL
			**********************************************************/
	        String forwardURL = "/WEB-INF/jsp/common/clmsfile/CpmsFileUpload.jsp";
	
			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
	        ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("fileInfos", fileInfos);
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
	 * CPMS getFileInfo
	 * 파일 정보 가져오기 (표지인쇄 - 요청자:김홍,박보나 과장) 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *  ,Object command, BindException errors
	 */
	 public void getFileInfo (HttpServletRequest request, HttpServletResponse response) throws Exception {
		 try {/*********************************************************
				 * Form 및 VO Binding
				**********************************************************/	        
				ClmsFileForm form = new ClmsFileForm();
				ClmsFileVO vo = new ClmsFileVO();

				bind(request, form);
				bind(request, vo);
				COMUtil.getUserAuditInfo(request, form);
				COMUtil.getUserAuditInfo(request, vo);

				ListOrderedMap result  = clmsFileService.getFileInfo(vo);
				JSONObject jo = new JSONObject();
				
				if(result != null){
					String orgFileNm = (String)result.get("org_file_nm");
					double bytesize= ((BigDecimal)result.get("file_sz")).intValue();			
					double fileKb = myRound((Math.round(bytesize / 1024 * 100) * .01), 2);
					String suffix = "KB";
					if (fileKb > 1000) {
						fileKb = myRound(Math.round(fileKb *.001 * 100) * .01, 2);
						suffix = "MB";
					}
					
					jo.put("orgFileNm"   , orgFileNm);
					jo.put("fileSz"   , fileKb+suffix);
				}else{
					//첨부파일없음
					jo.put("orgFileNm", "");
					jo.put("fileSz"   , "");
				}
				response.setContentType("application/json; charset=UTF-8");
	    		PrintWriter out = response.getWriter();
	    		out.print(jo);
	    		out.flush();
	    		out.close();
				
		 }catch (Exception e) {
		 
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
	 
	 /* File Size 재계산 */
	 public double myRound(double num, int pos){
	
			double posV = Math.pow(10, 2);
			return Math.round(num*posV)/posV;
		}
 	 
}