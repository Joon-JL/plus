package com.sds.secframework.singleIF.service;

import java.util.ArrayList;
import java.util.List;

import samsung.esb.mail.vo.RecipientESBVO;

import com.sds.secframework.singleIF.dto.MailVO;

/**
 * <P>Single ESB 메일 Interface</P>
 * 
 * [지원기능]<BR>
 * <BR>
 * - 메일전송<BR>
 * - 메일발송취소<BR>
 * - 메일수신상태조회<BR> 
 *  
 * @version com.sds.secframework.singleIF V1.0
 * 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */
public interface EsbMailService {
	
	/**
	 * <P>메일발송</P>
	 * 메일관련 테이블에 전송 데이터를 입력한 후, ESB메일전송을 수행.
	 * 
	 * @param String argModuleId, String argMisId
	 * @return 메일전송 결과 (성공:true, 실패:false)
	 */
	public boolean sendMail(String argModuleId, String argMisId);
	
	/**
	 * <P>보안메일발송</P>
	 * 메일관련 테이블에 전송 데이터를 입력한 후, ESB메일전송을 수행.
	 * 
	 * @param String argModuleId, String argMisId
	 * @return 메일전송 결과 (성공:true, 실패:false)
	 */
	public boolean sendMailSecu(String argModuleId, String argMisId);

	/**
	 * <P>메일발송 취소</P>
	 * 메일발송 취소를 수행 -> 수신인 테이블의 상태변경
	 * 테이블 : TB_COM_RECIPIENT_ETY_CSVO
	 * 
	 * @param argModuleId : 모듈아이디, argMisId:MIS ID, argPassword:비밀번호
	 * @return 메일발송 취소결과 (성공:true, 실패:false)
	 */
	public boolean cancelMail(String argModuleId, String argMisId, String argPassword);
	
	/**
	 * <P>메일상태조회</P>
	 * 메일수신상태 조회. 수신상태 정보 업데이트 수행
	 * 
	 * @param argModuleId : 모듈아이디, argMisId:MIS ID, argPassword:비밀번호
	 * @return 메일상태 조회결과
	 */
	public RecipientESBVO[] getRecipientStatus(String argModuleId, String argMisId);

	/**
	 * <P>메일등록</P>
	 * @param vo 싱글메일 Value Object
	 * @throws Exception 
	 */
	public void insertMail(MailVO vo) throws Exception;
	
	/**
	 * <P>Admin 메일등록</P>
	 * @param vo 싱글메일 Value Object
	 * @throws Exception 
	 */
	public void insertAdminMail(MailVO vo) throws Exception;
	
	/**
	 * <P>Admin 메일등록</P>
	 * @param vo 싱글메일 Value Object
	 * @throws Exception 
	 */
	public void insertAdminMailSub(MailVO vo) throws Exception;
	
	/**
	 * <P>메일전송목록</P>
	 * @param vo 싱글메일 Value Object
	 * @throws Exception 
	 */
	public List listMail(MailVO vo) throws Exception;
	
	/**
	 * <P>[배치작업]메일발송</P>
	 * 일정주기별로 전송대기중인 건을 읽어와서 메일전송 후 결과를 메일발신 Header테이블에 반영한다.
	 * 테이블 : TB_COM_HEADER_HELPER_CSVO
	 *  
	 */
	public void sendMailBatch() throws Exception;
}
