package com.sds.secframework.file.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sds.secframework.common.dto.CommonVO;
import com.sds.secframework.file.dto.AttachFileVO;

/**
 * 파일 업로드를 위한 Interface
 *
 */
public interface AttachFileService {
		
	/**
	 * 파일을 업로드하기 위한 method
	 * HttpServletRequest 객체로 부터 파일에 대한 정보를 가져와서
	 * context-properties.xml 파일에 정의된 UPLOAD_DIR 위치에
	 * 해당 파일을 저장한다.
	 * @param request 첨부파일에 대한 정보가 포함된 HttpServletRequest 객체
	 * @return 파일에 대한 메타 정보를 담고 있는 List 객체
	 * @throws Exception
	 */
	List fileUpload(HttpServletRequest request) throws Exception;
	
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
	List fileUpload(HttpServletRequest request, String destDir) throws Exception;

	// 교육통합

	/**
	 * 파일 목록 조회
	 * @param sys_cd:시스템 코드, file_ref_no:첨부파일 일련번호
	 * @return 파일목록 리스트
	 * @throws Exception
	 */
	List listFileInfo(HashMap hm) throws Exception;
	
	/**
	 * 파일상세조회
	 * @param sys_cd:시스템 코드, file_ref_no:첨부파일 일련번호, seq_no:일련번호
	 * @return 1개 파일정보
	 * @throws Exception
	 */
	List getFileInfo(HashMap hm) throws Exception;
	
	/**
	 * 파일정보 등록
	 * @param AttachFileVO vo 파일정보 VO Object
	 * @return 수행결과
	 * @throws Exception
	 */
	int insertFile(AttachFileVO vo) throws Exception;
	
	/**
	 * 파일정보 삭제
	 * @param AttachFileVO vo 파일정보 VO Object
	 * @return 수행결과
	 * @throws Exception
	 */
	int deleteFile(AttachFileVO vo) throws Exception;

	
	
	
	  /**
	  * DB에서 넘어온 File 정보를 다시 File Attatch 문자열로 만든다.
	  *
	  * @param    collist DB에서 넘어온 File List
	  * @return   File Attatch 문자열(seq_no*로컬파일명*파일정보*서버파일명(경로+실제저장파일명)*크기|....)
	  */
	 String getFileInfosToString(List collist) throws Exception;
	 
	  /**
	  * String 으로 넘어온 File Info를 FileVO 타입으로 변환하여 넘겨준다.
	  *
	  * @param    화면에서 넘어온 FileInfo Stringseq_no*로컬파일명*파일정보*서버파일명(경로+실제저장파일명)*크기|....)
	  * @return   FileVO
	  */
	 ArrayList getFileVOFromFileInfos(String fileInfos) throws Exception;
	 
	 ArrayList getMailAttachEtyVO(String fileInfos) throws Exception;
	 ArrayList getAttachmentWSVO(String fileInfos) throws Exception;
	 
	/**
	 * 첨부파일 일련번호 채번<P>
	 *
	 * @param 
	 * @return 첨부파일 일련번호
	 * @throws Exception
	*/
	String getFileRefNo() throws Exception;
	
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
	String getFileRefNo(String gubun) throws Exception;

	/**
	 * 첨부파일 유무. FileInfos정보로 파일이 있는지 없는지 판별<P>
	 *
	 * @param 
	 * @return boolean
	 * @throws Exception
	*/
	boolean isExistsFileInfos(String fileInfos) throws Exception;
	
	/** 김정곤 2011,03,18
	 * 파일을 insert 한다. DataBase 에 정보저장
	 * 작업후 file_ref_no 를 리턴해준다.
	 * @param 
	 * @return String
	 * @throws Exception
	 */
	public String FileInsert(String FileInfos) throws Exception;

	/** 김정곤 2011,03,18
	 * 파일을 update 한다. DataBase 에 정보저장
	 * 작업후 file_ref_no 를 리턴해준다.
	 * @param 
	 * @return String
	 * @throws Exception
	 */	
	public String FileUpdate(String FileInfos,String File_ref_no) throws Exception;
	
	/** 김정곤 2011,03,18	
	 * 파일을 delete 한다. DataBase 에 정보저장
	 * 작업후 "" 를 리턴해준다.
	 * @param 
	 * @return String
	 * @throws Exception
	 */		
	public String FileDelete(String FileInfos,String File_ref_no) throws Exception;		
		
		
	/** 김정곤 2011,03,18
	 * 파일을 리스트를 조회 한다.
	 * 작업후 fileInfos 를 리턴해준다.
	 * 리턴한 값을 다음과 같이 사용할 수 있다.
	 * result.put("fileInfos",attachFileService.FileList(vo,vo.getFile_ref_no()));
	 * @param 
	 * @return String
	 * @throws Exception
	 */
	public String FileList(String File_ref_no) throws Exception;
	/**
	 * 파일을 엑션별로 다룬다. DataBase 정보 조회
	 * 작업후 fileInfos 를 리턴해준다.
	 * 리턴한 값을 다음과 같이 사용할 수 있다.
	 * result.put("fileInfos",attachFileService.FileListCom(vo,vo.getFile_ref_no()));
	 * @param 
	 * @return String
	 * @throws Exception
	 */		
	public String FileSave(String FileInfos,String File_ref_no,String div) throws Exception;	
	
}
