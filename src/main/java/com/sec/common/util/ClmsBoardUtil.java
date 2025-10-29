package com.sec.common.util;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.sds.secframework.auth.dto.ClassMethodAuthVO;
import com.sds.secframework.auth.service.ClassMethodAuthService;
import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.FileUtil;
import com.sds.secframework.common.util.FormatUtil;
import com.sds.secframework.common.util.ObjectCopyUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.util.service.ComUtilService;
import com.sds.secframework.util.service.ComboService;
import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiUtils;

public class ClmsBoardUtil {
	/**
	 * 구분코드가 있는 경우 구분코드를 콤보 option으로 리턴한다.
	 * @param vo BoardMngVO
	 * @param selectedValue 기본 선택할 value
	 * @param addValue 추가할 option의 value
	 * @param addText 추가할 option의 text
	 * @param addPosition 추가할 위치 (F:맨 처음, L:맨 마지막)
	 * @return
	 * @throws Exception
	 */
	static public String getDivCdComob(ComboService comboService, Object vo, String selectedValue, String addValue, String addText, String addPosition) throws Exception{
		String combo = "" ;
		
		HashMap hm = new HashMap() ;
		hm.put("sys_cd", ObjectCopyUtil.getFieldValue(vo, "sys_cd")) ;
		hm.put("grp_cd", ObjectCopyUtil.getFieldValue(vo, "div_grp_cd")) ;
		hm.put("use_yn", "Y") ;
		hm.put("selected", selectedValue) ;
		hm.put("language", "en") ;
		hm.put("abbr", "") ;

		combo = comboService.getCommonCodeCombo(hm) ;
					
		// 추가되는 option 있는 경우 option 을 해당 위치에 추가
		if(addValue!=null) {
			String newCombo = "<option value=\"" + addValue + "\">" + addText + "</option>" ;
			
			// 맨 처음에 추가
			if(addPosition.equals("F")) {
				combo = newCombo + combo ; 
			}
			// 맨 마지막에 추가
			else if (addPosition.equals("L")) {
				combo = combo + newCombo ; 
			}
		}
		
		
		return combo ;
	
	}
	
	/**
	 * 이미지 파일 저장
	 * @param propertyService PropertyService
	 * @param vo Value Object
	 * @param subDir 저장할 하위 경로(truth, notice.. 등 한단어로 세팅)
	 * @throws Exception
	 */
	static public void saveImageFile(PropertyService propertyService, Object vo, String subDir) throws Exception {
		MultipartFile imgFile = (MultipartFile) ObjectCopyUtil.getFieldValue(vo, "img_file_path") ;
		String imgDelYn = (String)ObjectCopyUtil.getFieldValue(vo, "img_del_yn") ;
		
		String realSavePath = propertyService.getProperty("clms.img.save.dir") + "/" + subDir + "/" + DateUtil.today().substring(0, 6) ; // 실제저장 경로 
		String webUrl = propertyService.getProperty("clms.img.web.dir") + "/" + subDir + "/" + DateUtil.today().substring(0, 6) ; // 웹경로
		String noticeId = (String)ObjectCopyUtil.getFieldValue(vo, "notice_id") ; // 글번호
		String orgiFilePath = (String)ObjectCopyUtil.getFieldValue(vo, "org_img_file") ; // 기존 파일(웹루트 이하 경로)
		String orgiThumbnailFilePath = (String)ObjectCopyUtil.getFieldValue(vo, "org_thumbnail_img_file") ; // 기존 섬네일 파일(웹루트 이하 경로)
		String newFileName =  imgFile.getOriginalFilename(); // 새 파일(파일명)
		String extention = newFileName.substring(newFileName.lastIndexOf(".") + 1) ; // 새파일 확장자
		String saveFileName = noticeId + "." + extention ;
		String thumbnailFileName = noticeId + "_thumbnail" + "." + extention ; 
				
		File file = null ;
		
		// 1. 파일이 있을 경우 기존파일 삭제 후 등록
		if(newFileName!=null && !newFileName.equals("")) {
			// 1-1. 기존파일이 있는 경우 삭제
			if(orgiFilePath!=null && !orgiFilePath.equals("")) {
				file = new File(propertyService.getProperty("clms.context.root") + orgiFilePath) ;
				if(file.exists()) {
					file.delete() ;
					ObjectCopyUtil.setFieldValue(vo, "img_file", "" , String.class) ;
				}

				file = new File(propertyService.getProperty("clms.context.root") + orgiThumbnailFilePath) ;
				if(file.exists()) {
					file.delete() ;
					ObjectCopyUtil.setFieldValue(vo, "thumbnail_img_file", "" , String.class) ;
				}
			}
			
			// 1-2. 경로 생성
			FileUtil.mkdir(realSavePath) ;
			
			// 1-3. 새파일 등록
			file = new File(realSavePath + "/" + saveFileName) ;
			imgFile.transferTo(file) ;
			
			// 1-4. VO에 새이미지파일 세팅
			ObjectCopyUtil.setFieldValue(vo, "img_file", webUrl + "/" + saveFileName, String.class) ;
			
			// 1-5. 썸네일 생성
			if(saveThumbnailImage(file.getPath(), realSavePath + "/" + thumbnailFileName, 120, 90)) {
				ObjectCopyUtil.setFieldValue(vo, "thumbnail_img_file", webUrl + "/" + thumbnailFileName, String.class) ;
			}
		}
		// 2. 기존파일 삭제인 경우 삭제
		else if(imgDelYn!=null && imgDelYn.equals("Y")) {
			if(orgiFilePath!=null && !orgiFilePath.equals("")) {
				file = new File(propertyService.getProperty("clms.context.root") + orgiFilePath) ;
				if(file.exists()) {
					file.delete() ;
					ObjectCopyUtil.setFieldValue(vo, "img_file", "" , String.class) ;
				}
				
				file = new File(propertyService.getProperty("clms.context.root") + orgiThumbnailFilePath) ;
				if(file.exists()) {
					file.delete() ;
					ObjectCopyUtil.setFieldValue(vo, "thumbnail_img_file", "" , String.class) ;
				}
			}
		}
		//3. 변경이 없는궁여 기존 파일 정보 세팅
		else {
			ObjectCopyUtil.setFieldValue(vo, "img_file", (String)ObjectCopyUtil.getFieldValue(vo, "org_img_file") , String.class) ;
			ObjectCopyUtil.setFieldValue(vo, "thumbnail_img_file", (String)ObjectCopyUtil.getFieldValue(vo, "org_thumbnail_img_file") , String.class) ;
		}
	}
	
	/**
	 * Thumbnail 이미지 생성
	 * @param oriFilePath 원본 이미지 파일 경로
	 * @param thumbFilePath thumbnail 이미지 파일 경로
	 * @param width 이미지 넓이
	 * @param height 이미지 높이
	 * @return
	 * @throws Exception
	 */
	static public  boolean saveThumbnailImage(String oriFilePath, String thumbFilePath, int width, int height) throws Exception{
		boolean result = false ;
		
		File file = new File(oriFilePath) ;
		if(file.exists()) {
			Image image = JimiUtils.getThumbnail(file.getPath(), width, height, Jimi.IN_MEMORY) ;
			Jimi.putImage(image, thumbFilePath) ;
			
			result = true ;
		}
		
		return result ;
	}
	
	/**
	 * 문자열의 < 와 >을 &lt;, &gt; 로 변경하고
	 * 줄바꿈을 <br>로 변경
	 * @param str
	 * @return
	 */
	static public String getNoTag(String str) {
		str = StringUtil.replace(str, "<", "&lt;") ;
		str = StringUtil.replace(str, ">", "&gt;") ;
		str = StringUtil.replace(str,"\r\n","<br>") ;
		
		return str ;
	}
	
	/**
	 * 로그인한 사용자가 관리자인지 확인
	 * 관리자면 Y, 아니면 N 리턴
	 * @param request
	 * @return
	 * @throws Exception
	 */
	static public String getUserAdminYN(HttpServletRequest request) throws Exception {
		
		// 로그인한 사람의 Role이 관리자이면 true
		String result = isAdmin(request) ? "Y" : "N" ;
	    		
		return result ;
	}
	
	
	/**
	 * 관리자인지 확인
	 * @param request HttpServletRequest
	 * @return
	 * @throws Exception
	 */
	static public boolean isAdmin(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession() ;
		List userAuthList = (List) session.getAttribute("secfw.session.role");
		
	    return isAdmin(userAuthList) ;
	}
	
	/**
	 * 관리자인지 확인
	 * @param userAuthList 사용자 권한 리스트
	 * @return
	 * @throws Exception
	 */
	static public boolean isAdmin(List userAuthList) throws Exception {
		boolean result = false ;
			
		 for (int i = 0; i < userAuthList.size(); i++) {
	        Map userRoleMap = (Map) userAuthList.get(i);
	        String userRoleCd = (String) userRoleMap.get("role_cd");
	        if(userRoleCd.equals("ADMIN")) {
	        	result = true ;
	        	break ;
	        }
		 }
			
		 return result ;
	}
	
	/**
	 * 답변형 게시판의 글레벨을 계산한다.
	 * 
	 * @param commonDAO CommonDAO
	 * @param vo 입력할 Value Object
	 * @param type 종류(append:댓글, question:알려주세요 ...)
	 * @return
	 * @throws Exception
	 */
	public static int getNoticeLevel(CommonDAO commonDAO, Object vo, String type) throws Exception {
		List list = commonDAO.list("shri." + type + ".level", vo) ;
		int level = 0 ;
		
		if(list!=null && list.size()!=0) {
			Map map = (Map)list.get(0) ;
			String fieldNm = type.equals("append") ? "append_level" : "notice_level" ;
			level = FormatUtil.formatInt(map.get(fieldNm)) ;
		}
				
		return level ;
	}
	
	/**
	 * 답변형 게시판의 순서를 계산한다.
	 * 1. 원본글의 레벨보다 작거나 같고 원본글의 순서보다 큰 순서의 최소값
	 * 2. 1번에 해당하는 값이 없을 경우 최대순서 + 1 
	 * 
	 * @param commonDAO CommonDAO
	 * @param vo 답변글의 Value Object
	 * @param upVo 원본글의 Value Object
	 * @param type 종류(append:댓글, question:알려주세요 ...)
	 * @return
	 * @throws Exception
	 */
	public static int getNoticeOrder(CommonDAO commonDAO, Object vo, Object upVo, String type) throws Exception {
		//1. 원본글의 레벨보다 작거나 같고 원본글의 순서보다 큰 순서의 최소값
		List list = commonDAO.list("shri." + type + ".order", upVo) ;
		int order = 0 ;
		
		if(list!=null && list.size()!=0) {
			Map map = (Map)list.get(0) ;
			
			String fieldNm = type.equals("append") ? "append_order" : "notice_order" ;
			
			//2. 1번에 해당하는 값이 없을 경우 최대순서 + 1
			if(map.get(fieldNm)!=null) {
				order = FormatUtil.formatInt(map.get(fieldNm)) ;
			} else {
				 list = commonDAO.list("shri." + type + ".maxorder", vo) ;
				 if(list!=null && list.size()!=0) {
					 map = (Map)list.get(0) ;
					 order = FormatUtil.formatInt(map.get(fieldNm)) + 1 ;
				 }
			}
		}
		
		return order ;
	}
	
	/**
	 * 답변형 게시판의  글순서를 업데이트 한다.
	 * @param commonDAO CommonDAO
	 * @param vo Value Object 
	 * @param type 종류(append:댓글, question:알려주세요 ...)
	 * @throws Exception
	 */
	static public void modifyAppendOrder(CommonDAO commonDAO, Object vo, String type) throws Exception {
		commonDAO.modify("shri." + type + ".modifyOrder", vo) ;
	}
	
	/**
	 * 나모에디터 사용한 글내용을 변환
	 * @param comUtilService ComUtilService
	 * @param decodeText 글내용
	 * @return
	 * @throws Exception
	 */
	static public String getNamoContentsHTML(ComUtilService comUtilService, String decodeText) throws Exception {

		// 나모 Text & 첨부파일 처리
		HashMap hm = comUtilService.getNamoContentAndFileInfo(decodeText);
        String contentHtml = (String)hm.get("CONTENT");
        
        //contentHtml.
        contentHtml = StringUtil.replace(contentHtml, "<html>", "<html xmlns='http://www.w3.org/1999/xhtml'>");
        contentHtml = StringUtil.replace(contentHtml, "<HTML>", "<html xmlns='http://www.w3.org/1999/xhtml'>");
        contentHtml = StringUtil.replace(contentHtml, "</HTML>", "</html>");
        
        return contentHtml ;
	}
		
	/**
	 * 클래스 메소드에 대한 
	 * @param serivce ClassMethodAuthService
	 * @param classNm 클래스명(패키지 포함)
	 * @param methodNm 메소드명
	 * @return
	 * @throws Exception
	 */
	static public List getClassMethodAuthList(ClassMethodAuthService serivce, String classNm, String methodNm, String sysCd) throws Exception{
		ClassMethodAuthVO vo = new ClassMethodAuthVO() ;
		vo.setClass_nm(classNm) ;
		vo.setMethod_nm(methodNm) ;
		vo.setSys_cd(sysCd);
		
		//return serivce.getClassMethodRoleList(vo) ;
		return serivce.getClassMethodAuthList(vo);
	}
	
	
	/**
	 * 클래스 메소드의 권한이 있는지 여부
	 * @param serivce ClassMethodAuthService
	 * @param userAuthList 사용자 권한(역할) 리스트
	 * @param classNm 클래스명
	 * @param methodNm 메소드명
	 * @return
	 * @throws Exception
	 */
	static public boolean getClassMethodAuth(HttpServletRequest request, ClassMethodAuthService serivce, String classNm, String methodNm) throws Exception{
		HttpSession session = request.getSession(false) ;
		String sysCd = StringUtil.bvl((String)session.getAttribute("secfw.session.sys_cd"), "");
		
		boolean result = false ;
		List methodAuthList = getClassMethodAuthList(serivce, classNm, methodNm, sysCd) ;
		
		
		//List userAuthList = (List)session.getAttribute("secfw.session.role") ;
		List userAuthList = (List)session.getAttribute("secfw.session.auth") ;
				
		// 해당 메소드의 권한이 있는지 체크
		for(int i=0; !result && i<userAuthList.size(); i++) {
			Map userAuth = (Map)userAuthList.get(i) ;
			for(int j=0; j<methodAuthList.size(); j++) {
				Map methodAuth = (Map)methodAuthList.get(j) ;
				/*
				if(userAuth.get("role_cd").equals(methodAuth.get("role_cd"))){
					result = true ;
					break ;
				}
				*/
				if(userAuth.get("auth_cd").equals(methodAuth.get("auth_cd"))){
					result = true ;
					break ;
				}
			}
		}
		
		return result ;
	}
	
	/**
	 * 수정/삭제 권한
	 * @param request HttpServletRequest
	 * @param noticeRegId 등록자ID
	 * @return
	 * @throws Exception
	 */
	static public boolean getDeleteModifyAuth(HttpServletRequest request, String noticeRegId) throws Exception {
		HttpSession session = request.getSession(false) ;
		String userId = (String)session.getAttribute("secfw.session.user_id") ;
		boolean isAdmin = isAdmin(request) ;
		boolean result = false ;
		
		// 관리자이거나 본인 글이면 수정/삭제 가능
		if(isAdmin || userId.equals(noticeRegId)) {
			result = true ;
		}
		
		return result ;
	}
	
	/**
	 * 연구제안 상세(Detail)화면에서의 수정/삭제 권한
	 * @param request
	 * @param reg_id 
	 * @param status_cd
	 * @return
	 * @throws Exception
	 */
	static public boolean getProposalDeleteModifyAuth(HttpServletRequest request, String reg_id, String status_cd) throws Exception {
		HttpSession session = request.getSession(false) ;
		String userId = (String)session.getAttribute("secfw.session.user_id") ;
		boolean isAdmin = isAdmin(request);
		boolean result = false;
		
		//관리자일때는 수정/삭제 모두 가능
		if(isAdmin){
			result = true;
		}
		//관리자가 아닐 때는 상태값이 10일때만 가능
		else if(userId.equals(reg_id)){
			if(status_cd.equals("10")){
				result = true;
			}
		}
		return result;
	}
	
	/**
	 * 연구제안 상세(Detail)화면에서의 접수 권한
	 * @param request
	 * @param status_cd
	 * @return
	 * @throws Exception
	 */
	static public boolean getProposalReviewAuth(HttpServletRequest request, String status_cd) throws Exception {
		boolean isAdmin = isAdmin(request);
		boolean result = false;
		
		//관리자이면서 상태값 10일 때 검토가능
		if(isAdmin && status_cd.equals("10")){
			result = true;
		}
		return result;
	}
	
	/**
	 * 연구제안 상세(Detail)화면에서 의견란 조회 권한
	 * @param request
	 * @param status_cd
	 * @return
	 * @throws Exception
	 */
	static public boolean getProposalOpinionAuth(HttpServletRequest request, String status_cd) throws Exception {
		boolean isAdmin = isAdmin(request);
		boolean result = false;
		
		//관리자면 의견 조회 가능
		if(isAdmin){
			result = true;
		}
		//관리자가 아니면 상태(status_cd)가 30(채택)이거나 40(불채택)일시만 조회 가능
		else if(status_cd.equals("30") || status_cd.equals("40")){
			result = true;
		}
		return result;
	}
	
	/**
	 * 삭제/수정 권한 체크 후 권한 없으면 에러페이지로 이동 
	 * @param request HttpServletRequest
	 * @param noticeRegId 등록자ID
	 * @param type 종류(modify:수정, delete:삭제)
	 * @throws Exception
	 */
	static public void checkDeleteModifyAuth(HttpServletRequest request, String noticeRegId, String type) throws Exception {
		if(!getDeleteModifyAuth(request, noticeRegId)) {
			ModelAndView mav = new ModelAndView() ; 
			mav.setViewName("/WEB-INF/jsp/secfw/error/SystemAlert.jsp");
			mav.addObject("secfw.alert.title", "권한이 없습니다.");
			mav.addObject("secfw.alert.message",  (type.equals("modify")?"수정":"삭제") + " 권한이 없습니다.");
			
			throw new ModelAndViewDefiningException(mav) ;
		}
	}
	
	/**
	 * 첨부파일의 파일 타입 확인
	 * @param file MultipartFile
	 * @param okFileArray 허용할 파일 타입 배열
	 * @param blankCheck 빈값 체크할지 여부
	 * @return
	 */
	static public boolean getFileType(MultipartFile file, String[] okFileArray, boolean blankCheck) {
		boolean result = false ;
		String contentType = file.getContentType() ;
		String fileName = file.getOriginalFilename() ;
		
		// 파일이 없을 경우 blankCheck 가 false 이면 true를 리턴
		if(fileName==null || fileName.equals("")) {
			if(!blankCheck) {
				result = true ;
			}
		} else {
			result = getFileType(contentType, okFileArray) ;
		}
		
		
		return result ;
	}
	
	/**
	 * 허용된 이미지 파일 타입인지 체크
	 * @param contentType 파일의 content type
	 * @param okFileArray 허용될 파일타입의 배열
	 * @return
	 */
	static public boolean getFileType(String contentType, String[] okFileArray ) {
		boolean result = false ;
				
		for(int i=0; !result && i<okFileArray.length; i++) {
			if(contentType.equals("image/" + okFileArray[i])) {
				result = true ;
				break ;
			}
		}
		
		return result ;
	}
	
	/**
	 * 허용된 첨부파일 체크 
	 * @param file MultipartFile
	 * @param okFileArray 허용할 파일 타입 배열(image/gif ... 형식)
	 * @param errorMessage 에러 메시지
	 * @param blankCheck 빈값 체크여부
	 * @throws Exception
	 */
	static public void checkFileType(MultipartFile file, String[] okFileArray, String errorMessage, boolean blankCheck) throws Exception {
		if(!getFileType(file, okFileArray, blankCheck)) {
			ModelAndView mav = new ModelAndView() ; 
			mav.setViewName("/WEB-INF/jsp/secfw/error/SystemAlert.jsp");
			mav.addObject("secfw.alert.title", "첨부하신 파일은 허용되지 않습니다.");
			mav.addObject("secfw.alert.message",  errorMessage);
			
			throw new ModelAndViewDefiningException(mav) ;
		}
	}
	
	
	/********************************************************
	 * 알려주세요 게시판 관련 
	 ********************************************************/
	
	/**
	 * 알려주세요 게시판 답변 권한 
	 * 1. 원본글이 일반 사용자 글인 경우 관리자만 답변 가능
	 * 2. 원본글이 관리자 글인 경우 임시저장 글이 아니거나 권한이 있는 사용자 답변 가능
	 * @param request HttpServletRequest
	 * @param serivce ClassMethodAuthService
	 * @param classNm 클래스명
	 * @param methodNm 메소드명
	 * @param noticeAdminYn 원본글이 관리자글인지 여부(Y:관리자글, N:일반사용자글)
	 * @param status 글상태
	 * @return
	 * @throws Exception
	 */
	static public boolean getQuestionReplyAuth(HttpServletRequest request, ClassMethodAuthService serivce, String classNm, String methodNm, String noticeAdminYn, String status) throws Exception {
		boolean result = false ;
		boolean isAdmin = isAdmin(request) ;
		
		// 원본글이 일반사용자의 글인 경우 - 관라지만 답변 가능 
		if(noticeAdminYn.equals("N") && isAdmin) {
			result = true ;
		}
		// 원본글이 관리자의 글인 경우 - 임시저장 글이 아니고 답변 권한이 있는 사용자 모두 답변 가능
		else if(noticeAdminYn.equals("Y")) {
			if(status.equals("10")){
				result = false ;
			}
			else {
				result = getClassMethodAuth(request, serivce, classNm, methodNm) ;
			}
		}
		
		return result ;
	}
	
	/**
	 * 알려주세요 게시판 답변 권한 체크
	 * @param request HttpServletRequest
	 * @param serivce ClassMethodAuthService
	 * @param classNm 클래스명
	 * @param methodNm 메소드명
	 * @param noticeAdminYn 원본글이 관리자글인지 여부(Y:관리자글, N:일반사용자글)
	 * @param status 글상태
	 * @throws Exception
	 */
	static public void checkQuestionReplyAuth(HttpServletRequest request, ClassMethodAuthService serivce, String classNm, String methodNm, String noticeAdminYn, String status) throws Exception {
		if(!getQuestionReplyAuth(request, serivce, classNm, methodNm, noticeAdminYn, status)) {
			ModelAndView mav = new ModelAndView() ; 
			mav.setViewName("/WEB-INF/jsp/secfw/error/SystemAlert.jsp");
			mav.addObject("secfw.alert.title", "권한이 없습니다.");
			mav.addObject("secfw.alert.message",  "답변 권한이 없습니다.");
			
			throw new ModelAndViewDefiningException(mav) ;
		}
	}
	
	/**
	 * 답변 확정 권한 
	 * 글상태가 확정(00)이 아니고 관리자 이거나 본인글인 경우.
	 * @param request HttpServletRequest
	 * @param status 글상태
	 * @param regId 등록자ID
	 * @throws Exception
	 */
	static public boolean getQuestionReplyConfirmAuth(HttpServletRequest request, String status, String regId) throws Exception {
		boolean result = false ;
		
		HttpSession session = request.getSession(false) ;
		String userId = (String)session.getAttribute("secfw.session.user_id") ;
		
		if(!status.equals("00") && (isAdmin(request) || userId.equals(regId))) {
			result = true ;
		}
		
		return result ;
	}
	
	/**
	 * 임시저장 권한
	 * 사용자가 관리자 이고, 글상태가 확정(00)이 아닌 경우
	 * @param request HttpServletRequest
	 * @param status 글상태
	 * @return
	 */
	static public boolean getQuestionTempSaveAuth(HttpServletRequest request, String status) throws Exception {
		boolean result = false ;
		boolean isAdmin = isAdmin(request) ;
		
		if(isAdmin && !status.equals("00")) {
			result = true ;
		}
		
		return result ;
	}
	
	/**
	 * 답변확정 권한.
	 * @param request
	 * @param status
	 * @param regId
	 * @throws Exception
	 */
	static public void checkQuestionReplyConfirmAuth(HttpServletRequest request, String status, String regId) throws Exception {
		if(!getQuestionReplyConfirmAuth(request, status, regId)){
			ModelAndView mav = new ModelAndView() ; 
			mav.setViewName("/WEB-INF/jsp/secfw/error/SystemAlert.jsp");
			mav.addObject("secfw.alert.title", "권한이 없습니다.");
			mav.addObject("secfw.alert.message",  "답변 확정 권한이 없습니다.");
			
			throw new ModelAndViewDefiningException(mav) ;
		}
	}
	
	/**
	 * 파라미터로 넘겨진 srchRole에 해당하는 사용자 역할 검색. 있으면 true. 없으면 false
	 * @param request
	 * @param srchRole
	 * @return
	 * @throws Exception
	 * @author 김현구(2011-08-30)
	 */
	static public boolean searchRole(HttpServletRequest request, String srchRole) throws Exception {
		HttpSession session = request.getSession(false);
		ArrayList userRoleList = (ArrayList) session.getAttribute("secfw.session.role");
		boolean result = false ;

		if(userRoleList != null){
			for (int i = 0; i < userRoleList.size(); i++) {
				Map userRoleMap = (Map) userRoleList.get(i);
				String userRoleCd = (String) userRoleMap.get("role_cd");
				if (userRoleCd.equals(srchRole)) {
					result = true;
					break;
				}
			}	
		}
		return result;
	}
}

