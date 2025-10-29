package com.sds.secframework.singleIF;

import com.sds.secframework.common.util.EsbUtil;
import com.sds.secframework.singleIF.dto.MailVO;
import com.sds.secframework.singleIF.service.EsbMailService;
import com.sds.secframework.singleIF.service.impl.EsbMailServiceImpl;

/**
 * <P>Single ESB 테스트 Class</P>
 * 
 * 싱글 ESB연계 테스트 Class<BR>
 * <br>
 * [기능 : 실행방법]<br>
 * - 결재상신 : java com.sds.secframework.singleIF approval submit<br>
 * - 결재상신취소 : java com.sds.secframework.singleIF approval cancelApproval<br>
 * - 결재상태조회 : java com.sds.secframework.singleIF approval getStatusWithPath<br>
 * <BR>
 * - 메일전송 : java com.sds.secframework.singleIF mail sendMail<br>
 * - 메일전송취소 : java com.sds.secframework.singleIF mail cancelMail<br>
 * - 메일수신상태조회 : java com.sds.secframework.singleIF mail getRecipientStatus<br>
 *
 * @version com.sds.secframework.singleIF V1.0
 * 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */
public class ESBTestMain {
	
	String mailSenderAddr = "";
	String approvalSender = "";
	
	public static void main(String args[]) throws Exception {

			
			EsbMailService mail = new EsbMailServiceImpl();
			
			MailVO mailVO = new MailVO();
			
			/***********************************************************
			 * 	메일전송
			 ***********************************************************/
				
				String moduleId = "KUM_MODULE";
				String misId    = EsbUtil.generateMisId("MAIL");
				String htmlYn   = "true";                                       //메일메시지본문(true: HTML, false: TEXT) 
				String msgType  = "personal";                                   //메시지종류(personal:개인, official:공문)
				String timeZone = "GMT+0";                                      //타임존
				String isDst    = "false";                                      //서머타임적용여부
				
				/** 메일헤더 **/
				mailVO.setModule_id(moduleId);
				mailVO.setMis_id(misId);
				mailVO.setSubject("[제목]싱글 메일 테스트 입니다.");                //제목
				mailVO.setBody("<P>[본문]메일의 본문입니다. 전송이 잘 될까요?</P>"); //본문
				mailVO.setMsg_type(msgType);                                     //메시지종류(personal:개인, official:공문)
				mailVO.setBhtml_content_check(htmlYn);                           //메일메시지본문(true: HTML, false: TEXT)
				mailVO.setTime_zone(timeZone);                                   //타임존
				mailVO.setIs_dst(isDst);                                         //서머타임적용여부
				mailVO.setSender_mail_addr("ace4u_khs@stage.samsung.com");    //발신자메일주소
				mailVO.setStatus("0");                                           //전송상태 0:메일발송대기
				
				/** 메일송신자 **/
				mailVO.setLocale("en_US");
				mailVO.setEncoding("utf-8");
				
				/** 메일수신자 **/
				String[] mailRecipientEtyVOs1 = new String[1];
				String[] mailRecipientEtyVOs2 = new String[1];
				String[] mailRecipientEtyVOs3 = new String[1];
				
				mailRecipientEtyVOs1[0] =  "1";
				mailRecipientEtyVOs2[0] =  "t";                           //t:수신, c:참조, b:비밀참조                          
				mailRecipientEtyVOs3[0] =  "ace4u_khs@stage.samsung.com";

				mailVO.setIseq_ids(mailRecipientEtyVOs1);
				mailVO.setRec_types(mailRecipientEtyVOs2);
				mailVO.setRec_addrs(mailRecipientEtyVOs3);
				
				/** 첨부파일정보 **/
				// 테스트하고자 하는 첨부파일명, 경로, 사이즈로 변경하여 테스트 한다.
                
				String[] mailAttachEtyVOs1 = new String[1];
				String[] mailAttachEtyVOs2 = new String[1];
				String[] mailAttachEtyVOs3 = new String[1];
								
				//메일전송 호출
				mail.insertMail(mailVO);

	}
	
}
