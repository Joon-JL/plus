/** 
* Project Name : CLMS
* File Name : ClmsFileService.java
* Description : 공통 첨부파일 Service
* Author : 신남원
* Date : 2011. 08. 04
* Copyright : SamSung
*/
package com.sec.common.clmsfile.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.ListOrderedMap;

import net.sf.json.JSONArray;

import com.sds.secframework.common.dto.CommonVO;
import com.sds.secframework.log.dto.LogVO;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import com.sec.common.clmsfile.dto.ComFileVO;

/**
 * 첨부파일 처리를 위한 Interface
 *
 */
public interface ClmsFileService {

	/**
	* Clms : DB정보로부터 첨부파일정보 조회
	* @param  ClmsFileVO
	* @return String FileInfo
	* @throws Exception
	*/
	String getClmsDbFileInfos(ClmsFileVO vo) throws Exception;

	
	/**
	 * ClmsFile을 업로드하기 위한 method
	 * HttpServletRequest 객체로 부터 파일에 대한 정보를 가져와서
	 * 서버로 파일을 저장하고 저장된 파일 정보를 return 한다.
	 * @param request 첨부파일에 대한 정보가 포함된 HttpServletRequest 객체
	 * @return 파일정보를 담고 있는 fileInfos
	 * @throws Exception
	 */
	String clmsFileUpload(HttpServletRequest request) throws Exception;	
	
	/**
	* 첨부파일 관리 : 정보 저장 및 삭제
	* 업무 저장 또는 수정시 에 첨부되는 파일 정보를 수정 또는 삭제한다.
	* 각 서비스Impl에서 호출되는 부분
	* @param  ClmsFileVO vo
	* @return boolean 결과
	* @throws Exception
	*/
	boolean mngClmsAttachFile(ClmsFileVO vo) throws Exception; 

	boolean mngClmsAttachFile(ClmsFileVO vo, CommonVO cvo) throws Exception;
	
	/**
	* 첨부파일 관리 : 첨부파일 삭제
	* 각 업무에서 데이터 삭제시 해당 첨부파일을 전부 삭제 한다.
	* 각 서비스Impl에서 호출되는 부분
	* @param  ClmsFileVO vo
	* @return boolean 결과
	* @throws Exception
	*/
	boolean delClmsAttachFile(ClmsFileVO vo) throws Exception; 

	/**
	* ClmsFile Download
	* @param  ClmsFileVO vo
	* @return List
	* @throws Exception
	*/
	List clmsFileDownload(ClmsFileVO vo) throws Exception; 

	/**
	 * 첨부파일 DownloadLog
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	void fileDownLog(LogVO vo) throws Exception;
	
	/**
	 * 수정시 파일을 삭제하기 위한 method
	 * HttpServletRequest 객체로 부터 파일에 대한 정보를 가져와서
	 * 서버로 파일정보를  return 한다.
	 * @param request 첨부파일에 대한 정보가 포함된 HttpServletRequest 객체
	 * @return 파일정보를 담고 있는 fileInfos
	 * @throws Exception
	 */
	String clmsFileDelete(ClmsFileVO vo) throws Exception;		

	/**
	* 계약서정보 변경시 기존 파일들 이름 변경
	* @param  ClmsFileVO
	* @return void
	* @throws Exception
	*/
	void modifyBeforeCntrtFile(ClmsFileVO vo) throws Exception;
	
	/**
	* 라이브러리 파일 변경시 기존 파일들 이름 변경
	* @param  ClmsFileVO vo
	* @throws Exception
	*/
	void modifyBeforeLibFile(ClmsFileVO vo) throws Exception;
	
	/**********************************************************************
	* ComFile 처리 영역 (Mail_메일, Approval_결재)
	**********************************************************************/
	
	/**
	* ComFile : Com 첨부파일DB정보로부터 첨부파일정보 조회
	* @param  ComFileVO
	* @return String FileInfo
	* @throws Exception
	*/
	String getComDbFileInfos(ComFileVO vo) throws Exception;
	

	/**
	 * Com File을 업로드하기 위한 method
	 * HttpServletRequest 객체로 부터 파일에 대한 정보를 가져와서
	 * 서버로 파일을 저장하고 저장된 파일 정보를 return 한다.
	 * @param request 첨부파일에 대한 정보가 포함된 HttpServletRequest 객체
	 * @return 파일정보를 담고 있는 fileInfos
	 * @throws Exception
	 */
	String comFileUpload(HttpServletRequest request) throws Exception;	
	
	/**
	* ComFile Download
	* @param  ComFileVO vo
	* @return List
	* @throws Exception
	*/
	List comFileDownload(ComFileVO vo) throws Exception; 	

	/**
	 * 수정시 파일을 삭제하기 위한 method
	 * HttpServletRequest 객체로 부터 파일에 대한 정보를 가져와서
	 * 서버로 파일정보를  return 한다.
	 * @param request 첨부파일에 대한 정보가 포함된 HttpServletRequest 객체
	 * @return 파일정보를 담고 있는 fileInfos
	 * @throws Exception
	 */
	String comFileDelete(ComFileVO vo) throws Exception;	
	
	/**
	* 첨부파일 관리 : 정보 저장 및 삭제
	* 메일또는결재상신 시 저장 또는 수정시 에 첨부되는 파일 정보를 수정 또는 삭제한다.
	* 각 서비스Impl에서 호출되는 부분
	* @param  ComFileVO vo
	* @return boolean 결과
	* @throws Exception
	*/
	boolean mngComAttachFile(ComFileVO vo) throws Exception; 

	/**
	* ComFile : String 으로 넘어온 FileInfo를 FileVO 타입으로 변환하여  FileList로 반환 
	* 첨부파일을 DB 저장시 주로 사용
	* @param    화면에서 넘어온 FileInfo Stringseq_no*로컬파일명*파일정보*서버파일명(경로+실제저장파일명)*크기|....)
	* @return   List
	*/
	List getFileInfoToComFileList(String fileInfos) throws Exception;
		
	/**
	* 첨부파일 관리 : 첨부파일 삭제
	* 메일또는결재상신  데이터 삭제시 해당 첨부파일을 전부 삭제 한다.
	* 각 서비스Impl에서 호출되는 부분
	* @param  ComFileVO vo
	* @return boolean 결과
	* @throws Exception
	*/
	boolean delComAttachFile(ComFileVO vo) throws Exception; 	

	
	/**********************************************************************
	* fileUpload 
	**********************************************************************/
	/**
	 * 이미지 또는 엑셀파일등 단일 파일 업로드시 사용
	 * HttpServletRequest 객체로 부터 파일에 대한 정보를 가져와서
	 * 서버로 파일을 저장하고 저장된 파일 정보를 return 한다.
	 * @param request 첨부파일에 대한 정보가 포함된 HttpServletRequest 객체
	 * @return 파일정보를 담고 있는 fileInfos
	 * @throws Exception
	 */
	String fileUpload(HttpServletRequest request) throws Exception;	

	/**
	 * 파일 업로드 후 업로드된 파일을 지정 업무폴더로 이동하고 파일명을 변경한다.
	 * @param request
	 * @return
	 * @throws Exception
	 */
	String moveFile(HttpServletRequest request) throws Exception;
	/**********************************************************************
	* 공통
	**********************************************************************/
	
	/**
	 * 첨부파일 유무. FileInfos정보로 파일이 있는지 없는지 판별<P>
	 *
	 * @param 
	 * @return boolean
	 * @throws Exception
	*/
	boolean isExistsFileInfos(String fileInfos) throws Exception;
	
	/**
	 * 첨부파일 여부 확인
	 *
	 * @param    
	 * @return   
	 */
	JSONArray getClmsFilePageCheck(ClmsFileVO vo) throws Exception;
	
	/*
	 * 계약 체결단계에서 임시저장 및 체결품의 올릴떄 기존 파일에 FINAL을 붙인다.
	 * */
	boolean mngF012AttachFile(ClmsFileVO vo, CommonVO cvo) throws Exception;
	
	/**
	 * CpmsFile을 업로드하기 위한 method
	 * HttpServletRequest 객체로 부터 파일에 대한 정보를 가져와서
	 * 서버로 파일을 저장하고 저장된 파일 정보를 return 한다.
	 * @param request 첨부파일에 대한 정보가 포함된 HttpServletRequest 객체
	 * @return 파일정보를 담고 있는 fileInfos
	 * @throws Exception
	 */
	String cpmsFileUpload(HttpServletRequest request) throws Exception;	
	
	/**
	* CpmsFile : String 으로 넘어온 FileInfo를 FileVO 타입으로 변환하여  FileList로 반환 
	* 첨부파일을 DB 저장시 주로 사용
	* @param    화면에서 넘어온 FileInfo Stringseq_no*로컬파일명*파일정보*서버파일명(경로+실제저장파일명)*크기|....)
	* @return   List
	*/
	List getFileInfoToCpmsFileList(String fileInfos) throws Exception;
	
	/**
	* 표준계약서정보 승인시 기존 파일들 이름 변경
	* @param  ClmsFileVO
	* @return void
	* @throws Exception
	*/
	void modifyStdCntrtFile(ClmsFileVO vo) throws Exception;
	
	/**
	* 표지인쇄에서 파일정보 조회 
	* 생성자: 소유진 (2012.09.11 표지인쇄시 첨부파일사본정보가 누락되어 생성)
	* @param  ClmsFileVO
	* @return ListOrderedMap
	* @throws Exception*/	
	
	public ListOrderedMap getFileInfo(ClmsFileVO vo) throws Exception;


	List tncFileListSave(List fileListInfos1) throws Exception;


	String getClmsFileListToFileInfos(List savefileList1, String string) throws Exception;


	boolean checkFileInfo(String allowInfo, String fileInfo) throws Exception;
	
}