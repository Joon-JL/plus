package com.sds.secframework.singleIF.service;

import java.util.HashMap;
import java.util.List;

import samsung.esb.approval.vo.AttachmentWSVO;
import samsung.esb.approval.vo.RouteWSVO;
import samsung.esb.approval.vo.StartProcessWSVO;
import samsung.esb.common.vo.ESBFaultVO;

import com.sds.secframework.singleIF.dto.ApprovalVO;

/**
 * <P>Single ESB 결재 Interface</P>
 * 
 * [지원기능]<BR>
 * <BR>
 * - 결재상신<BR>
 * - 결재상태조회<BR>
 * - 결재상신취소<BR> 
 *  
 * @version com.sds.secframework.singleIF V1.0
 * 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */
public interface EsbApprovalService {

	/**
	 * <P>결재상신</P>
	 * 결재관련 테이블에 해당 데이터를 입력한 후, ESB결재연동을 수행.
	 * 
	 * @param ApprovalVO : 결재상신 Value Object
	 * @return 결재상신 결과 (성공:true, 실패:false)
	 */
	public boolean submit(ApprovalVO vo);
	
	/**
	 * <P>결재상신</P>
	 * 결재관련 테이블에 해당 데이터를 입력
	 * 
	 * @param ApprovalVO : 결재상신 Value Object
	 * @return 결재상신 결과 (성공:true, 실패:false)
	 */
	public boolean preSubmit(ApprovalVO vo);
	
	/**
	 * <P>결재상신</P>
	 * 기 등록건에 대해 ESB결재연동을 수행.
	 * @param ApprovalVO : 결재상신 Value Object
	 * @return 결재상신 결과 (성공:true, 실패:false)
	 */
	public boolean afterSubmit(ApprovalVO vo);

	/**
	 * <P>결재상신취소</P>
	 * 현재 결재진행중인 건에 한하여 결재상신 취소를 수행.
	 * 
	 * @param argModuleId:모듈ID,  argMisId:MIS ID(ESB연동KEY), argOpinion:상신취소의견
	 * @return 결재상신 취소결과 (성공:1, 결재진행인 건이 아닐경우:2 예외:E)
	 */
	public String cancelApproval(String argModuleId,String argMisId, String argOpinion);

	/**
	 * <P>결재상태조회</P>
	 * 결재상태 조회. 결재경로 정보를 받아와서 최신상태로 반영.
	 * 
	 * @param argModuleId:모듈ID,  argMisId:MIS ID(ESB연동KEY)
	 * @return HashMap 결재상태 조회결과
	 *        KEY : "status",      VALUE : 결재상태값 (1:결재진행중, 2:완결, 3:반려, 4:상신취소, 5:전결, 6:후완결)
	 *        KEY : "approvalPath" VALUE : 결재경로 정보 RouteWSVO[] 타입
	 */
	public HashMap getStatusWithPath(String argModuleId,String argMisId);
	
	/**
	 * <P>결재기본정보 조회</P>
	 * 결재기본 정보를 읽어온다.
	 * 테이블 : TB_COM_START_PROCESS_WSVO
	 * 
	 * @param argModuleId:모듈ID,  argMisId:MIS ID(ESB연동KEY)
	 * @return StartProcessWSVO 결재기본정보 VO
	 */
	public StartProcessWSVO getStartProcessWSVO(String argModuleId,String argMisId) throws Exception;
	
	/**
	 * <P>결재경로조회</P>
	 * 결재경로 정보를 읽어온다.
	 * 테이블 : TB_COM_ROUTE_WSVO
	 * 
	 * @param argModuleId:모듈ID,  argMisId:MIS ID(ESB연동KEY)
	 * @return RouteWSVO[] 결재경로 VO
	 */
	public RouteWSVO[] getRouteWSVO(String argModuleId,String argMisId) throws Exception;
	
	/**
	 * <P>첨부파일조회</P>
	 * 결재첨부파일 정보를 읽어온다.
	 * 테이블 : TB_COM_ATTACHMENT_WSVO
	 * 
	 * @param argModuleId:모듈ID,  argMisId:MIS ID(ESB연동KEY)
	 * @return AttachmentWSVO[] 첨부파일 VO
	 */
	public AttachmentWSVO[] getAttachmentWSVO(String argModuleId,String argMisId) throws Exception;
	
	/**
	 * <P>결재상신 정보등록</P>
	 * 
	 * @param vo 싱글결재 Value Object
	 * @throws Exception 
	 */
	public void insertApproval(ApprovalVO vo) throws Exception;
	
	/**
	 * <P>에러로그</P>
	 * ESB연계시 발생한 에러로그를 남긴다.
	 * 테이블 : TB_COM_ESB_FAULT_VO 
	 * 
	 * @param argModuleId:모듈ID,  argMisId:MIS ID(ESB연동KEY), fault:ESBFaultVO 오류Value Object
	 */
	public void insertError(String argModuleId,String argMisId, ESBFaultVO fault) throws Exception;
	
	/**
	 * 기안자 ESB조회 예외 여부 조회
	 * @param hm
	 * @return
	 * @throws Exception
	 */
	public List listExceptWsvoList(HashMap hm) throws Exception;
	
	/**
	 * approval path 에 등록된 조건과 일치하는 결재자들 조회
	 * @param hm
	 * @return
	 * @throws Exception
	 */
	public List listExceptWsvoListPath(HashMap hm) throws Exception;
	
}
