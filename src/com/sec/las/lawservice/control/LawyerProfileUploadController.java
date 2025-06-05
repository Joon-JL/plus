/**
 * Project Name : 법무시스템
 * File Name : LawyerProfileUploadController.java
 * Description : 변호사 관리 사진 업로드 Controller
 * Author : 박병주
 * Date : 2011.09
 * Copyright : 2011 by SAMSUNG. All rights reserved.
 */
package com.sec.las.lawservice.control;

import java.io.File;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.FileUtil;
import com.sec.las.lawservice.dto.LawyerManageForm;
import com.sec.las.lawservice.dto.LawyerManageVO;
import com.sec.las.lawservice.service.LawyerManageService;

public class LawyerProfileUploadController extends LawyerManageController {
	
	private LawyerManageService lawyerManageService;
	
	public void setLawyerManageService(LawyerManageService lawyerManageService) {
		this.lawyerManageService = lawyerManageService;
	}
		
	/**
	 * 변호사관리 프로파일 사진 업로드 처리
	 * @param request
	 * @param response
	 * @return void
	 * @throws Exception
	 */
	public ModelAndView uploadLawyerProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			
			getLogger().debug(">>>>>>>>>>>>>>>>>>> uploadLawyerProfile=");
			// getLogger().debug(">>>>>>>>>>>>>>>>>>> menu_id="+(String)request.getAttribute("menu_id"));
			
			ModelAndView mav = null;
			
			String uploadDir = propertyService.getProperty("clms.file.lawyer.dir");
			
			LawyerManageForm form = new LawyerManageForm();
			LawyerManageVO   vo   = new LawyerManageVO();
		
			bind(request, form);
			bind(request, vo);

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
			MultipartFile file = multipartRequest.getFile("lawyer_photo");

			getLogger().debug(">>>>>>>>>>>>>>>>>>> file="+file);
			getLogger().debug(">>>>>>>>>>>>>>>>>>> menu_id="+(String)request.getAttribute("menu_id"));
			   
			if (!new File(uploadDir).exists()) {
				new File(uploadDir).mkdirs();
			}
			
			String fileName = "";
			
			if (file != null) {
				
				String fileInfo = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
			
				if (file.getOriginalFilename().lastIndexOf(".") > 0) {
					// fileName = uploadDir + File.separator + file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".")) + "_" + util.ActsDateUtil.getDate(new Date(), "yyyyMMddHHmmss") + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
					fileName = 
						uploadDir 
						+ File.separator 
						+ vo.getLwr_no()
						+ "_" 
						+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
				
					if(FileUtil.saveFile(fileName, file.getBytes()) == -1){
						//파일을 저장하는 중에 에러가 발생하였습니다.
						throw new Exception(messageSource.getMessage("las.page.field.lawyerprofile.uploadLawyerProfile01", null, new RequestContext(request).getLocale()));
					} else if(checkFileInfo(fileInfo) == false){
						//허용되지 않는 확장자를 가진 파일 입니다.
						throw new Exception(messageSource.getMessage("las.page.field.lawyerprofile.uploadLawyerProfile02", null, new RequestContext(request).getLocale()));
					} else {
						// 변호사 사진 파일 정보를 첨부 파일 테이블에 갱신
						lawyerManageService.insertLawyerProfilePhoto(vo);
						// 사진 업로드 처리 후  상세 화면 method 리턴 						
						
					}
				}

			}
			
			mav = detailLawyerManage(request, response);	
			
			return mav;
		
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * checkFileInfo
	 * 업로드 가능한 파일 확장자 인지 체크 한다.
	 * @param 업로드 시도한 파일의 확장자
	 * @return true : 허용된 파일형식이 있음, false : 허용된 파일 형식이 없음
	 * @throws Exception
	 */
	private boolean checkFileInfo( String fileInfo ) throws Exception {
		boolean result = false;
		
		String allowList = propertyService.getProperty("PDF,JPG,BMP,GIF");
		StringTokenizer	token = new StringTokenizer(allowList,",");
		
		while (token.hasMoreTokens()) {
			if(fileInfo.equals( token.nextToken().trim().toLowerCase())) {
				result = true;
				break;
			}
		}
		
		return result;		
	}
}
