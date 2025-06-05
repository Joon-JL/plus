package com.sds.secframework.file.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.context.MessageSource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import anyframe.common.exception.BaseException;
import anyframe.core.idgen.IIdGenerationService;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.file.dto.AttachFileVO;
import com.sds.secframework.file.service.AttachFileService;
import com.sds.secframework.singleIF.dto.ApprovalVO;
import com.sds.secframework.singleIF.dto.MailVO;

/**
 * 파일 업로드를 위한 Class
 *
 */
public class AttachFileServiceImpl extends CommonServiceImpl implements AttachFileService {
		
	/**
	 * ID 값을 생성하기 위한 idGeneration 선언 및 세팅
	 */
	private IIdGenerationService idGenerationService = null;
	public void setIdGenerationService(IIdGenerationService idGenerationService) {
		this.idGenerationService = idGenerationService;
	}

	/**
	 * 에러 발생시 처리한 messageSource 선언
	 */
	private MessageSource messageSource;
	
	/**
	 * 업로드를 제한 하는 파일의 형식을 지정한다.
	 */
	private String restrictFileList;
			
	/**
	 * MessageSource 에 대한 setter method
	 * @param messageSource
	 */
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * 업로드 제한 파일 목록을 가져온다.
	 * @return
	 */
	public String getRestrictFileList() {
		return restrictFileList;
	}

	/**
	 * 업로드 제한 파일 목록을 세팅한다.
	 * @param restrictFileList 업로드 제한 파일
	 */
	public void setRestrictFileList(String restrictFileList) {
		this.restrictFileList = restrictFileList;
	}
	
	/**
	 * 업로드 제한된 파일형식이 있는지 확인 한다.
	 * @param fileExt 업로드 시도한 파일의 확장자
	 * @return 0 : 제한된 파일형식이 없음, 1 : 제한된 형식의 파일이 있음
	 * @throws Exception
	 */
	private int extList( String fileExt ) throws Exception {
		StringTokenizer	token = new StringTokenizer(restrictFileList,",");
		
		int restrictFileCnt = 0; 
		
		while (token.hasMoreTokens()) {
			if(fileExt.equals( token.nextToken().trim().toLowerCase())) {
				restrictFileCnt++;
			}
		}
		
		return restrictFileCnt;		
	}

	/**
	 * 파일을 업로드하기 위한 method
	 * HttpServletRequest 객체로 부터 파일에 대한 정보를 가져와서
	 * context-properties.xml 파일에 정의된 UPLOAD_DIR 위치에
	 * 해당 파일을 저장한다.
	 * @param request 첨부파일에 대한 정보가 포함된 HttpServletRequest 객체
	 * @return 파일에 대한 메타 정보를 담고 있는 List 객체
	 * @throws Exception
	 */
	public List fileUpload(HttpServletRequest request) throws Exception {
		/**
		 * HttpServletRequest 객체를 파일을 처리 할수 있는 MultipartHttpServletRequest 객체로 Casting
		 */
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;

		/**
		 * MultipartHttpServletRequest 객체에 담겨진 파일의 정보를 map 형식으로 추출한다. 
		 */
		Map fileMap = multipartRequest.getFileMap();
				
		/**
		 * map 에서 key 값을 가져온다. 
		 */
		Set fileSet = fileMap.keySet();
		
		/**
		 * key 값을 object array로 변환한다.
		 */
		Object[] fileObject = fileSet.toArray();
		
		/**
		 * 원래 파일명
		 */
		String originalFileName = "";
		
		/**
		 * 서버에 저장될 파일명
		 */
		String serverFileName = "";
		
		/**
		 * 파일의 확장자
		 */
		String fileExt = "";
				
		/**
		 * 파일의 메타정보(파일사이즈,원래 파일명, 서버에 저장될 파일명, 저장경로)를 저장할 HashMap 선언
		 */
		HashMap fileInfo = null;
		
		/**
		 * 파일의 메타정보를 리턴하기 위한 List
		 */
		List fileInfoList = new ArrayList();
		
		/**
		 * request 객체로 넘어온 첨부파일의 개수 만큼 loop를 수행
		 */
		for (int i = 0 ; i < fileObject.length ; i++) {

			/**
			 * MultipartHttpServletRequest 객체에 담겨진 내용중에서 첨부 파일의 정보를 가져온다. 
			 */
			MultipartFile file = multipartRequest.getFile((String)fileObject[i]);
			
			/**
			 * 파일에 대한 정보가 null이 아니거나 원래 파일명이 empty String이 아닐 경우에만
			 * 파일에 대한 정보를 추출한다.
			 */
			if (file != null && !file.getOriginalFilename().equals("")) {
				
				/**
				 * 사용자가 첨부한 파일의 원래 이름을 가져온다.
				 */
				originalFileName = file.getOriginalFilename();
						
				/**
				 * 파일의 확장자가 있을 경우 확인
				 */
				if (originalFileName.lastIndexOf(".") != -1) {
					
					/**
					 * 파일의 확장자 정보를 소문자로 변환해서 가져온다.
					 */
					fileExt = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();
				}
				
				/**
				 * 제한된 형식의 파일이 있는지 확인 한다.
				 */
				if (extList(fileExt) > 0 ) {
					throw new BaseException(messageSource.getMessage(
						"error.attachserviceimpl.fileupload", new String[] {fileExt}, Locale.getDefault()));
				}

				/**
				 * 서버에 저장할 파일명을 idGenerationService를 이용해서 생성한다.
				 */
				serverFileName = idGenerationService.getNextStringId() + "." + fileExt;
			
				/**
				 * 파일을 저장할 서버의 경로를 context-properties.xml 파일에서 가져온다.
				 * default 경로
				 */
				String destDir = propertyService.getProperty("UPLOAD_DIR");
			
				/**
				 * 파일에 대한 객체를 생성한다.
				 */
				File dirPath = new File(destDir);
				
				/**
				 * 서버의 저장경로가 없을경우 해당 디렉토리를 생성한다.
				 */
				if (!dirPath.exists()) {
					dirPath.mkdirs();
				}
			
				/**
				 * 임시로 파일을 저장한다.
				 */
				File destination = File.createTempFile("file", serverFileName, dirPath);
			
				/**
				 * 파일을 서버의 저장경로로 복사한다.
				 */
				FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(destination));
				
				/**
				 * 파일의 메타 정보를 담기 위한 map 객체를 생성한다.
				 */
				fileInfo = new HashMap();
			
				/**
				 * 파일 사이즈
				 */
				fileInfo.put("FILE_SIZE", new Long(file.getSize()));
				
				/**
				 * 원래 파일명
				 */
				fileInfo.put("FILE_NAME", originalFileName);
				
				/**
				 * 파일이 저장될 서버 경로
				 */
				fileInfo.put("FILE_PATH", destDir);
				
				/**
				 * 서버에 저장될 파일명
				 */
				fileInfo.put("FILE_SERVER_NAME", destination.getName());
				
				/**
				 * 파일의 메타 정보를 List에 추가
				 */
				fileInfoList.add(fileInfo);
			}
		}
		
		/**
		 * 파일의 메타 정보를 list 객체로 리턴한다.
		 */
		return fileInfoList;
	}
	
	/**
	 * 파일을 업로드하기 위한 method
	 * HttpServletRequest 객체로 부터 파일에 대한 정보를 가져와서
	 * context-properties.xml 사용자가 정의한 위치에
	 * 해당 파일을 저장한다.
	 * @param request 첨부파일에 대한 정보가 포함된 HttpServletRequest 객체
	 * @param destDir 파일을 저장할 위치 정보
	 * @return 파일에 대한 메타 정보를 담고 있는 List 객체
	 * @throws Exception
	 */
	public List fileUpload(HttpServletRequest request, String uploadPath) throws Exception {
		
		/**
		 * HttpServletRequest 객체를 파일을 처리 할수 있는 MultipartHttpServletRequest 객체로 Casting
		 */
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;

		/**
		 * MultipartHttpServletRequest 객체에 담겨진 파일의 정보를 map 형식으로 추출한다. 
		 */
		Map fileMap = multipartRequest.getFileMap();
				
		/**
		 * map 에서 key 값을 가져온다. 
		 */
		Set fileSet = fileMap.keySet();
		
		/**
		 * key 값을 object array로 변환한다.
		 */
		Object[] fileObject = fileSet.toArray();
		
		/**
		 * 원래 파일명
		 */
		String originalFileName = "";
		
		/**
		 * 서버에 저장될 파일명
		 */
		String serverFileName = "";
		
		/**
		 * 파일의 확장자
		 */
		String fileExt = "";
		
		/**
		 * 파일의 메타정보(파일사이즈,원래 파일명, 서버에 저장될 파일명, 저장경로)를 저장할 HashMap 선언
		 */
		HashMap fileInfo = null;
		
		/**
		 * 파일의 메타정보를 리턴하기 위한 List
		 */
		List fileInfoList = new ArrayList();

		/**
		 * request 객체로 넘어온 첨부파일의 개수 만큼 loop를 수행
		 */
		for (int i = 0 ; i < fileObject.length ; i++) {
			
			/**
			 * MultipartHttpServletRequest 객체에 담겨진 내용중에서 첨부 파일의 정보를 가져온다. 
			 */
			MultipartFile file = multipartRequest.getFile((String)fileObject[i]);
			
			/**
			 * 파일에 대한 정보가 null이 아니거나 원래 파일명이 empty String이 아닐 경우에만
			 * 파일에 대한 정보를 추출한다.
			 */
			if (file != null && !file.getOriginalFilename().equals("")) {
				
				/**
				 * 사용자가 첨부한 파일의 원래 이름을 가져온다.
				 */
				originalFileName = file.getOriginalFilename();
			
				/**
				 * 파일의 확장자가 있을 경우 확인
				 */
				if (originalFileName.lastIndexOf(".") != -1) {
					
					/**
					 * 파일의 확장자 정보를 소문자로 변환해서 가져온다.
					 */
					fileExt = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();
				}
				
				/**
				 * 제한된 형식의 파일이 있는지 확인 한다.
				 */
				if (extList(fileExt) > 0 ) {
					throw new BaseException(messageSource.getMessage(
						"error.attachserviceimpl.fileupload", new String[] {fileExt}, Locale.getDefault()));
				}
				
				/**
				 * 서버에 저장할 파일명을 idGenerationService를 이용해서 생성한다.
				 */
				serverFileName = idGenerationService.getNextStringId() + "." + fileExt;
				
				/**
				 * 파일을 저장할 서버의 경로를 context-properties.xml 파일에서 가져온다.
				 * default 경로가 아닌 사용자가 지정한 경로를 가져온다.
				 */
				String destDir = propertyService.getProperty(uploadPath);
			
				/**
				 * 파일에 대한 객체를 생성한다.
				 */
				File dirPath = new File(destDir);
				
				/**
				 * 서버의 저장경로가 없을경우 해당 디렉토리를 생성한다.
				 */
				if (!dirPath.exists()) {
					dirPath.mkdirs();
				}
			
				/**
				 * 임시로 파일을 저장한다.
				 */
				File destination = File.createTempFile("file", serverFileName, dirPath);
			
				/**
				 * 파일을 서버의 저장경로로 복사한다.
				 */
				FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(destination));
				
				/**
				 * 파일의 메타 정보를 담기 위한 map 객체를 생성한다.
				 */
				fileInfo = new HashMap();
			
				/**
				 * 파일 사이즈
				 */
				fileInfo.put("FILE_SIZE", new Long(file.getSize()));
				
				/**
				 * 원래 파일명
				 */
				fileInfo.put("FILE_NAME", originalFileName);
				
				/**
				 * 서버에 저장될 파일명
				 */
				fileInfo.put("FILE_PATH", destDir);
				
				/**
				 * 서버에 저장될 파일명
				 */
				fileInfo.put("FILE_SERVER_NAME", destination.getName());
				
				/**
				 * 파일의 메타 정보를 List에 추가
				 */
				fileInfoList.add(fileInfo);
			}
		}
		
		/**
		 * 파일의 메타 정보를 list 객체로 리턴한다.
		 */
		return fileInfoList;
	}
	
	// 교육통합 
	
	/**
	 * 파일 목록 조회
	 * @param sys_cd:시스템 코드, file_ref_no:첨부파일 일련번호
	 * @return 파일목록 리스트
	 * @throws Exception
	 */
	public List listFileInfo(HashMap hm) throws Exception {
		hm.put("sys_cd", propertyService.getProperty("secfw.sysCd"));
		return commonDAO.list("secfw.file.listAttachFileInfo", hm);
	}
	
	/**
	 * 파일상세조회
	 * @param sys_cd:시스템 코드, file_ref_no:첨부파일 일련번호, seq_no:일련번호
	 * @return 1개 파일정보
	 * @throws Exception
	 */
	public List getFileInfo(HashMap hm) throws Exception {
		hm.put("sys_cd", propertyService.getProperty("secfw.sysCd"));
		return commonDAO.list("secfw.file.getAttachFileInfo", hm);
	}
	
	/**
	 * 파일정보 등록
	 * @param AttachFileVO vo 파일정보 VO Object
	 * @return 수행결과
	 * @throws Exception
	 */
	public int insertFile(AttachFileVO vo) throws Exception {
		vo.setSys_cd(propertyService.getProperty("secfw.sysCd"));
		return commonDAO.insert("secfw.file.insertAttachFile", vo);		
	}
	
	/**
	 * 파일정보 삭제
	 * @param AttachFileVO vo 파일정보 VO Object
	 * @return 수행결과
	 * @throws Exception
	 */
	public int deleteFile(AttachFileVO vo) throws Exception {
		vo.setSys_cd(propertyService.getProperty("secfw.sysCd"));
		return commonDAO.delete("secfw.file.deleteAttachFile", vo);
	}

	  /**
	  * DB에서 넘어온 File 정보를 다시 File Attatch 문자열로 만든다.
	  *
	  * @param    collist DB에서 넘어온 File List
	  * @return   File Attatch 문자열(seq_no*로컬파일명*파일정보*서버파일명(경로+실제저장파일명)*크기|....)
	  */
	public String getFileInfosToString(List collist) throws Exception {
		
	  	String fileInfos = "";

	    if( collist.size() > 0){
	        Iterator it = collist.iterator();
	   		while(it.hasNext())
	   		{
	   			ListOrderedMap hList = (ListOrderedMap)it.next();

				fileInfos  	= fileInfos 
				            + (String)hList.get("seq_no")+"*" 
				            + (String)hList.get("file_nm")+"*"
				            + (String)hList.get("file_info")+"*"
				            + (String)hList.get("file_path")+"*"
				            + (String)hList.get("file_size")+"|";
	   		}
		}

		return fileInfos;
	  }
	
	  /**
	  * String 으로 넘어온 File Info를 FileVO 타입으로 변환하여 넘겨준다.
	  *
	  * @param    화면에서 넘어온 FileInfo Stringseq_no*로컬파일명*파일정보*서버파일명(경로+실제저장파일명)*크기|....)
	  * @return   FileVO
	  */
	 public ArrayList getFileVOFromFileInfos(String fileInfos) throws Exception {

		 ArrayList result = new ArrayList();
		 AttachFileVO vo = null;
		 	
		// File 단위로 자른다.
		String[] arrFileInfo = StringUtil.token(fileInfos, "|");
		
		for(int i = 0; i < arrFileInfo.length; i++){
				
			String[] arrFileDetail = StringUtil.token(arrFileInfo[i], "*");
			vo = new AttachFileVO();
			
			if (checkFileExt(arrFileDetail)) {
				vo.setSeq_no(arrFileDetail[0]);
				vo.setFile_nm(arrFileDetail[1]);
				vo.setFile_info(arrFileDetail[2]);
				vo.setFile_path(arrFileDetail[3]);
				vo.setFile_size(arrFileDetail[4]);
	
				result.add(vo);	
			}
		}
			
		return result;
	 }

	  /**
	  * String 으로 넘어온 File Info를 ESB 첨부파일VO 타입으로 변환하여 넘겨준다.
	  *
	  * @param    화면에서 넘어온 FileInfo Stringseq_no*로컬파일명*파일정보*서버파일명(경로+실제저장파일명)*크기|....)
	  * @return   FileVO
	  */
	 public ArrayList getMailAttachEtyVO(String fileInfos) throws Exception {

		 ArrayList result = new ArrayList();
		 MailVO vo = null;
		 	
		// File 단위로 자른다.
		String[] arrFileInfo = StringUtil.token(fileInfos, "|");
		
		for(int i = 0; i < arrFileInfo.length; i++){
				
			String[] arrFileDetail = StringUtil.token(arrFileInfo[i], "*");
			vo = new MailVO();
			
			vo.setIfseq_id(String.valueOf(i));
			vo.setFile_name(arrFileDetail[1]);
			vo.setLocal_path(arrFileDetail[3]);
			
			result.add(vo);			
		}
			
		return result;
	 }
	 
	  /**
	  * String 으로 넘어온 File Info를 FileVO 타입으로 변환하여 넘겨준다.
	  *
	  * @param    화면에서 넘어온 FileInfo Stringseq_no*로컬파일명*파일정보*서버파일명(경로+실제저장파일명)*크기|....)
	  * @return   FileVO
	  */
	 public ArrayList getAttachmentWSVO(String fileInfos) throws Exception {

		 ArrayList result = new ArrayList();
		 ApprovalVO vo = null;
		 	
		// File 단위로 자른다.
		String[] arrFileInfo = StringUtil.token(fileInfos, "|");
		
		for(int i = 0; i < arrFileInfo.length; i++){
				
			String[] arrFileDetail = StringUtil.token(arrFileInfo[i], "*");
			vo = new ApprovalVO();
			
			vo.setSequence(String.valueOf(i));
			vo.setFile_name(arrFileDetail[1]);
			vo.setStore_location(arrFileDetail[3]);
			vo.setFile_size(arrFileDetail[4]);

			result.add(vo);			
		}
			
		return result;
	 }
	 
	  /**
	  * String 으로 넘어온 File Info를 HashMap 타입으로 변환하여 넘겨준다.
	  *
	  * @param    화면에서 넘어온 FileInfo Stringseq_no*로컬파일명*파일정보*서버파일명(경로+실제저장파일명)*크기|....)
	  * @return   FileVO
	  */
	 public static ArrayList getFileInfosList(String fileInfos) throws Exception {

		 ArrayList result = new ArrayList();
		 HashMap hm = null;
		 	
		// File 단위로 자른다.
		String[] arrFileInfo = StringUtil.token(fileInfos, "|");
		
		for(int i = 0; i < arrFileInfo.length; i++){
				
			String[] arrFileDetail = StringUtil.token(arrFileInfo[i], "*");
			hm = new HashMap();
			
			hm.put("seq_no"   ,arrFileDetail[0]);
			hm.put("file_nm"  ,arrFileDetail[1]);
			hm.put("file_info",arrFileDetail[2]);
			hm.put("file_path",arrFileDetail[3]);
			hm.put("file_size",arrFileDetail[4]);

			result.add(hm);			
		}
			
		return result;
	 } 
	 	 	 
	/**
	 * 첨부파일 일련번호 채번<P>
	 *
	 * @param 
	 * @return 첨부파일 일련번호
	 * @throws Exception
	*/
	public String getFileRefNo() throws Exception {
		return DateUtil.getTime("yyyyMMddHHmmssSSS") + idGenerationService.getNextStringId();
	}

	/**
	 * 첨부파일 일련번호 채번<P>
	 *
	 * @param 업무구분
	 *  매뉴얼       : MN
	 *  FAQ          : FA
	 * 공지사항      : NT
	 * Case Studies : CS
	 * 제보         : JA
	 * 이슈         : IS
	 * 기타         : OT
	 * @return 첨부파일 일련번호
	 * @throws Exception
	*/
	public String getFileRefNo(String gubun) throws Exception {
		return StringUtil.bvl(gubun,"") + DateUtil.getTime("yyyyMMddHHmmssSSS") + idGenerationService.getNextStringId();
	}
	
	/**
	 * 첨부파일 유무. FileInfos정보로 파일이 있는지 없는지 판별<P>
	 *
	 * @param 
	 * @return boolean
	 * @throws Exception
	*/
	public boolean isExistsFileInfos(String fileInfos) throws Exception {
		
		boolean result = false;
		
		if(fileInfos != null && !fileInfos.trim().equals("") && fileInfos.length() > 10) {
			result = true;
		}
		
		return result;
	}
	
	/**
	 * 파일 확장자 체크
	 * 업로드 제외 대상 파일의 경우 해당파일 삭제처리.
	 *
	 * @param 
	 * @return boolean
	 * @throws Exception
	 */
	private boolean checkFileExt(String[] fileInfoAry) {
		boolean ret = true;
		String fileNm = StringUtil.bvl(fileInfoAry[1], "");
		String fileExt = fileNm.substring(fileNm.lastIndexOf("."), fileNm.length());

		String checkFileTypeInProperty = propertyService.getProperty("secfw.epftp.notAllowFileType");
		StringTokenizer st = new StringTokenizer(checkFileTypeInProperty, ",");
		
		while(st.hasMoreTokens()) {
			if (st.nextToken().equals(fileExt)) {
				ret = false;
				
				try {
					File file = new File(fileInfoAry[3]);
					if (file.exists()) {
						file.delete();
					}
					
				}catch (Exception e) {
				}
				
				break;
			}
		}
		
		return ret;
	}
	
	/***********************************************************************************
	 파일처리 로직을 위해 필수로  부분 시작  김정곤 2011/03/18
	************************************************************************************/
	/**
	 * 파일을 delete 한다. DataBase 에 정보저장
	 * 작업후 "" 를 리턴해준다.
	 * @param 
	 * @return String
	 * @throws Exception
	 */		
	public String FileDelete(String FileInfos,String File_ref_no) throws Exception{
		/*
		 * vo 정보와 ,구분자를 보내준다. divState 경우 delete 일경우 만 넣어주고 디폴트는 널이다. 
		 * */
		
		String fileInfos = new String(StringUtil.bvl(FileInfos,""));
		String fileRefNo = new String(StringUtil.bvl(File_ref_no,""));
		
		
		/***************************************************
		 * 0. 첨부삭제          	       
		 * *************************************************/ 
		if	(
				//attachFileService.isExistsFileInfos(fileInfos)
				//&&
				fileRefNo!=null&&!fileRefNo.equals("null")&&!fileRefNo.equals("")
			) 
		{
			/*
			List fileList = attachFileService.getFileVOFromFileInfos(fileInfos);
			AttachFileVO fileVO = new AttachFileVO();
			
			for(int i = 0; i < fileList.size(); i++) {
	
				fileVO = (AttachFileVO)fileList.get(i);	
				
				//시스템코드, 첨부파일일련번호  추가
				fileVO.setSys_cd(propertyService.getProperty("secfw.sysCd"));
				fileVO.setFile_ref_no(File_ref_no);
				commonDAO.delete("secfw.file.deleteAttachFileAll", fileVO);
			}	*/
			
			AttachFileVO fileVO = new AttachFileVO();
			fileVO.setSys_cd(propertyService.getProperty("secfw.sysCd"));
			fileVO.setFile_ref_no(File_ref_no);
			commonDAO.delete("secfw.file.deleteAttachFileAll", fileVO);

		}
		return "";
	}	
	/**
	 * 파일을 update 한다. DataBase 에 정보저장
	 * 작업후 file_ref_no 를 리턴해준다.
	 * @param 
	 * @return String
	 * @throws Exception
	 */	
	public String FileUpdate(String FileInfos,String File_ref_no) throws Exception{
		String fileInfos = new String(StringUtil.bvl(FileInfos,""));
		String fileRefNo = new String(StringUtil.bvl(File_ref_no,""));
		
		
		/***************************************************
		 * 1. 첨부추가          	       
		 * *************************************************/ 
		if	(	attachFileService.isExistsFileInfos(fileInfos)
				&&fileRefNo!=null&&!fileRefNo.equals("null")&&!fileRefNo.equals("")
			)
		{
			List fileList = attachFileService.getFileVOFromFileInfos(fileInfos);
			AttachFileVO fileVO = null;
			
			for(int i = 0; i < fileList.size(); i++) {
	
				fileVO = (AttachFileVO)fileList.get(i);	
				
				//시스템코드, 첨부파일일련번호  추가
				fileVO.setSys_cd(propertyService.getProperty("secfw.sysCd"));
				fileVO.setFile_ref_no(fileRefNo);
				
				commonDAO.insert("secfw.file.insertAttachFile", fileVO);
			}	

		}			
		return fileRefNo;
	}
	
	/**
	 * 파일을 insert 한다. DataBase 에 정보저장
	 * 작업후 file_ref_no 를 리턴해준다.
	 * @param 
	 * @return String
	 * @throws Exception
	 */
		public String FileInsert(String FileInfos) throws Exception{
			String fileInfos = new String(StringUtil.bvl(FileInfos,""));
			String fileRefNo =null;// new String(StringUtil.bvl(File_ref_no,""));
			
				if(
						attachFileService.isExistsFileInfos(fileInfos)
						&&
						(fileRefNo==null||fileRefNo.equals("null")||fileRefNo.equals(""))
					){
				List fileList = attachFileService.getFileVOFromFileInfos(fileInfos);
				AttachFileVO fileVO = null;
				fileRefNo = attachFileService.getFileRefNo();
				
				for(int i = 0; i < fileList.size(); i++) {
		
					fileVO = (AttachFileVO)fileList.get(i);	
					
					//시스템코드, 첨부파일일련번호  추가
					fileVO.setSys_cd(propertyService.getProperty("secfw.sysCd"));
					fileVO.setFile_ref_no(fileRefNo);
					
					commonDAO.insert("secfw.file.insertAttachFile", fileVO);			
				}  		

			}
			
			return fileRefNo;
		}
		/**
		 * 파일을 리스트를 조회 한다. DataBase 정보 조회
		 * 작업후 fileInfos 를 리턴해준다.
		 * 리턴한 값을 다음과 같이 사용할 수 있다.
		 * result.put("fileInfos",attachFileService.FileListCom(vo,vo.getFile_ref_no()));
		 * @param 
		 * @return String
		 * @throws Exception
		 */
		public String FileList(String File_ref_no) throws Exception{	
			// 2. 첨부조회 (sys_cd, file_ref_no)
			HashMap fileHm = new HashMap();
			fileHm.put("sys_cd", propertyService.getProperty("secfw.sysCd"));
			fileHm.put("file_ref_no",StringUtil.bvl(File_ref_no,""));
			
			// 파일정보 조회 -> 파일스트링 변환
			List fileList = commonDAO.list("secfw.file.listAttachFileInfo", fileHm);
			String listBBSFile = attachFileService.getFileInfosToString(fileList);	
			return listBBSFile;
		}
		
		/**
		 * 파일을 엑션별로 다룬다. DataBase 정보 조회
		 * 작업후 fileInfos 를 리턴해준다.
		 * 리턴한 값을 다음과 같이 사용할 수 있다.
		 * result.put("fileInfos",attachFileService.FileListCom(vo,vo.getFile_ref_no()));
		 * @param 
		 * @return String
		 * @throws Exception
		 */		
		public String FileSave(String FileInfos,String File_ref_no,String div){
			String fileRefNo = new String(StringUtil.bvl(File_ref_no,""));
			String Div         = new String(StringUtil.bvl(div,""));
		 	String fileInfos   = new String(StringUtil.bvl(FileInfos,""));
		 		 	
			try{
				if(fileInfos.equals("")&&!fileRefNo.equals("")){
					FileDelete(FileInfos,File_ref_no);
				}else{
					if(fileRefNo==null||fileRefNo.equals("")||fileRefNo.equals("null")){
						fileRefNo=FileInsert(FileInfos);
					}else{
						FileDelete(FileInfos,File_ref_no);
						FileUpdate(FileInfos,File_ref_no);			
					}
				}
			}catch(Exception e){
					e.printStackTrace();
			}
			return fileRefNo;
		}	
		
	/***********************************************************************************
	 파일처리 로직을 위해 필수로  부분 끝
	************************************************************************************/	
	
	
}
