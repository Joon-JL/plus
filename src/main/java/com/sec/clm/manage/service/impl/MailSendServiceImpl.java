/**
* Project Name : 계약관리시스템
* File Name :MailSendServiceImpl.java
* Description : 인터페이스테이블정보관리 Service Impl
* Author : 심주완
* Date : 2011.10.13
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.EsbUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.singleIF.dto.MailVO;
import com.sds.secframework.singleIF.service.EsbMailService;
import com.sds.secframework.singleIF.service.EsbOrgService;
import com.sec.clm.manage.dto.CompletionVO;
import com.sec.clm.manage.dto.ConclusionVO;
import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.manage.service.MailSendService;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class MailSendServiceImpl extends CommonServiceImpl implements MailSendService {

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private EsbMailService esbMailService;

	public void setEsbMailService(EsbMailService esbMailService) {
		this.esbMailService = esbMailService;
	}

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private EsbOrgService esbOrgService;

	public void setEsbOrgService(EsbOrgService esbOrgService) {
		this.esbOrgService = esbOrgService;
	}

	public List<?> listAgreeRequestSendMailInfo(ConsultationVO vo) throws Exception {
		return commonDAO.list("clm.manage.listAgreeRequestSendMailInfo", vo);
	}

	public List<?> listCompletionNotifyMailInfo(CompletionVO vo) throws Exception {
		return commonDAO.list("clm.manage.listCompletionNotifyMailInfo", vo);
	}

	public void sendAgreeRequestNotifyMailSend(ConsultationVO vo) throws Exception {
		String errLine = " sendAgreeRequestNotifyMailSend pass 001";
		try {
			List<?> listAgreeRequestSendMailInfo = null;
			ListOrderedMap agreeLom = null;
			String agreeId = ""; // 합의자아이디
			String agreeTitle = ""; // 메일타이틀
			String agreeMailAddr = ""; // 합의자메일주소
			HashMap<String, String> hm = new HashMap<String, String>();

			// 합의자정보조회 및 메일내용
			listAgreeRequestSendMailInfo = this.listAgreeRequestSendMailInfo(vo);
			errLine = " sendAgreeRequestNotifyMailSend pass 002 listAgreeRequestSendMailInfo:" + listAgreeRequestSendMailInfo;
			if (listAgreeRequestSendMailInfo != null && listAgreeRequestSendMailInfo.size() > 0) {
				agreeLom = (ListOrderedMap) listAgreeRequestSendMailInfo.get(0);
				agreeId = (String) agreeLom.get("agreeman_id");
				agreeTitle = (String) agreeLom.get("req_title");

			}

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			MailVO mailVo = new MailVO();
			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			// 모듈아이디 및 misId 세팅 -> (각 시스템/업무별로 개별 Key값을 입력)
			String moduleId = vo.getSys_cd();
			String misId = EsbUtil.generateMisId("MAIL");

			Locale locale1 = new Locale((String) vo.getSession_user_locale());

			mailVo.setModule_id(moduleId);
			mailVo.setMis_id(misId);
			mailVo.setMsg_key("11");

			mailVo.setSubject((String) messageSource.getMessage("clm.page.field.mailsend.subjectTitle", null, locale1));
			// 보내는 사람
			mailVo.setSender_single_id(propertyService.getProperty("clms.admin.id"));
			mailVo.setSender_mail_addr(propertyService.getProperty("clms.admin.email"));

			// 로케일, 인코딩 설정, Time Zone
			mailVo.setLocale(vo.getSession_user_locale());
			mailVo.setEncoding("utf-8");
			mailVo.setTime_zone("GMT+0");
			mailVo.setIs_dst("false");

			errLine = " sendAgreeRequestNotifyMailSend pass 003";

			// 상신자 정보 조회
			if (agreeId != null && !"".equals(agreeId)) {
				agreeMailAddr = esbOrgService.getUserEpMailAddr(agreeId);
			}

			// STATUS 값 설정 - Default "0"
			mailVo.setStatus("1");
			errLine = " sendAgreeRequestNotifyMailSend pass 004 agreeMailAddr:" + agreeMailAddr;
			// 받는 사람
			String[] iseq_ids = null;
			String[] rec_types = null;
			String[] rec_addrs = null;

			iseq_ids = new String[1];
			rec_types = new String[1];
			rec_addrs = new String[1];
			iseq_ids[0] = "1";
			rec_types[0] = "t";
			rec_addrs[0] = agreeMailAddr;
			hm.put("mail_type", "24");

			hm.put("main_title", (String) messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, locale1));
			hm.put("main_link", propertyService.getProperty("secfw.url.domain") + "/login.do");
			hm.put("param1", agreeTitle);
			// 메시지 처리 - 품의작성에 대한 확인 요청이 접수되었습니다. 계약관리 시스템을 통해 품의 내용을 확인하시어 결재 진행
			// 바랍니다.
			hm.put("param2", (String) messageSource.getMessage("clm.page.field.mailsend.sendAgreeRequestNotifyMailSend03", null, locale1));
			String contentHtml = this.getMailContent(hm);
			errLine = " sendAgreeRequestNotifyMailSend pass 005 agreeMailAddr:" + agreeMailAddr;
			mailVo.setBhtml_content_check("true");
			mailVo.setIseq_ids(iseq_ids);
			mailVo.setRec_types(rec_types);
			mailVo.setRec_addrs(rec_addrs);
			mailVo.setBody(contentHtml);
			errLine = " sendAgreeRequestNotifyMailSend pass 006 agreeMailAddr:" + agreeMailAddr;
			/*********************************************************
			 * 메일 내역 등록
			 **********************************************************/
			esbMailService.insertMail(mailVo);
			errLine = " sendAgreeRequestNotifyMailSend pass 007 agreeMailAddr:" + agreeMailAddr;

			/*********************************************************
			 * 메일전송
			 **********************************************************/
			try {
				esbMailService.sendMail(moduleId, misId);
			} catch (Exception e) {
				misId = EsbUtil.generateMisId("MAIL");
				mailVo.setMis_id(misId);
				esbMailService.insertAdminMail(mailVo);

				esbMailService.sendMail(moduleId, misId);

				misId = EsbUtil.generateMisId("MAIL");
				mailVo.setMis_id(misId);
				esbMailService.insertAdminMailSub(mailVo);

				esbMailService.sendMail(moduleId, misId);
			}

			errLine = " sendAgreeRequestNotifyMailSend pass 008 agreeMailAddr:" + agreeMailAddr;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error sendAgreeRequestNotifyMailSend errLine:" + errLine);
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error sendAgreeRequestNotifyMailSend errLine2:" + errLine);
		}
	}

	public List<?> listContractConculusionSendMailInfo(ConclusionVO vo) throws Exception {
		return commonDAO.list("clm.manage.listContractConculusionSendMailInfo", vo);
	}

	// 체결본사본등록--현재사용안함
	public void sendContractConculusionRegisterNotifyMailSend(ConclusionVO vo) throws Exception {
		try {
			List<?> listContractConculusionSendMailInfo = null;
			ListOrderedMap respLom = null;
			String cntrtRespId = "";
			String cntrtTitle = "";
			String cntrtRespMailAddr = "";
			// 합의자정보조회 및 메일내용
			listContractConculusionSendMailInfo = this.listContractConculusionSendMailInfo(vo);

			if (listContractConculusionSendMailInfo != null && listContractConculusionSendMailInfo.size() > 0) {
				respLom = (ListOrderedMap) listContractConculusionSendMailInfo.get(0);
				cntrtRespId = (String) respLom.get("cntrt_respman_id");
				cntrtTitle = (String) respLom.get("cntrt_nm");
			}

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			MailVO mailVo = new MailVO();
			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			// 모듈아이디 및 misId 세팅 -> (각 시스템/업무별로 개별 Key값을 입력)
			String moduleId = vo.getSys_cd();
			String misId = EsbUtil.generateMisId("MAIL");

			Locale locale1 = new Locale((String) vo.getSession_user_locale());

			mailVo.setModule_id(moduleId);
			mailVo.setMis_id(misId);
			mailVo.setMsg_key("11");
			mailVo.setSubject(messageSource.getMessage("clm.page.field.mailsend.subjectTitle", null, locale1));
			// 보내는 사람

			mailVo.setSender_single_id(propertyService.getProperty("clms.admin.id"));
			mailVo.setSender_mail_addr(propertyService.getProperty("clms.admin.email"));

			// 로케일, 인코딩 설정, Time Zone
			mailVo.setLocale(vo.getSession_user_locale());
			mailVo.setEncoding("utf-8");
			mailVo.setTime_zone("GMT+0");
			mailVo.setIs_dst("false");

			// 상신자 정보 조회
			if (cntrtRespId != null && !"".equals(cntrtRespId)) {
				cntrtRespMailAddr = esbOrgService.getUserEpMailAddr(cntrtRespId);
			}

			// STATUS 값 설정 - Default "0"
			mailVo.setStatus("1");

			// 받는 사람
			String[] iseq_ids = null;
			String[] rec_types = null;
			String[] rec_addrs = null;

			iseq_ids = new String[1];
			rec_types = new String[1];
			rec_addrs = new String[1];
			iseq_ids[0] = "1";
			rec_types[0] = "t";
			rec_addrs[0] = cntrtRespMailAddr;
			StringBuffer contentHtml = new StringBuffer();
			String[] amsg2 = { vo.getReq_title(), cntrtTitle };
			contentHtml.append("<html xmlns=\"http://www.w3.org/1999/xhtml\"><HEAD><TITLE></TITLE>").append("<META content=\"text/html; charset=utf-8\" http-equiv=Content-Type>").append("<META name=GENERATOR content=ActiveSquare></HEAD>")
					.append("<BODY style=\"FONT-FAMILY: 굴림; FONT-SIZE: 10pt\">").append("<P style=\"LINE-HEIGHT: 100%; MARGIN-TOP: 0px; MARGIN-BOTTOM: 0px\">");
			contentHtml.append((String) messageSource.getMessage("clm.page.field.mailsend.sendContractConculusionRegisterNotifyMailSend02", amsg2, locale1));
			mailVo.setBhtml_content_check("true");
			mailVo.setIseq_ids(iseq_ids);
			mailVo.setRec_types(rec_types);
			mailVo.setRec_addrs(rec_addrs);
			mailVo.setBody(contentHtml.toString());

			if (cntrtRespMailAddr != null && !"".equals(cntrtRespMailAddr)) {
				/*********************************************************
				 * 메일 내역 등록
				 **********************************************************/
				esbMailService.insertMail(mailVo);

				/*********************************************************
				 * 메일전송
				 **********************************************************/
				try {
					esbMailService.sendMail(moduleId, misId);
				} catch (Exception e) {
					misId = EsbUtil.generateMisId("MAIL");
					mailVo.setMis_id(misId);
					esbMailService.insertAdminMail(mailVo);

					esbMailService.sendMail(moduleId, misId);

					misId = EsbUtil.generateMisId("MAIL");
					mailVo.setMis_id(misId);
					esbMailService.insertAdminMailSub(mailVo);

					esbMailService.sendMail(moduleId, misId);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 법무 검토의뢰 검토의견 작성 완료 발신 - 2012.01.04 서유강대리 요청[유관부서 관련 메일 없음]
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	public void sendConsiderationDeliveryMailSend(com.sec.clm.review.dto.ConsultationVO vo) throws Exception {
		try {
			List<?> listConsiderationDeliveryMailInfo = null;
			ListOrderedMap respLom = null;
			String cntrtRespId = "";
			String cntrtTitle = vo.getReq_title();
			String cntrtRespMailAddr = "";
			HashMap<String, String> hm = new HashMap<String, String>();

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			MailVO mailVo = new MailVO();
			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			// 모듈아이디 및 misId 세팅 -> (각 시스템/업무별로 개별 Key값을 입력)
			String moduleId = vo.getSys_cd();
			String misId = EsbUtil.generateMisId("MAIL");

			Locale locale1 = new Locale((String) vo.getSession_user_locale());
			String[] amsg = { cntrtTitle };

			mailVo.setModule_id(moduleId);
			mailVo.setMis_id(misId);
			mailVo.setMsg_key("11");
			mailVo.setSubject(messageSource.getMessage("clm.page.field.mailsend.subjectTitle", null, locale1));
			// 보내는 사람
			mailVo.setSender_single_id(propertyService.getProperty("clms.admin.id"));
			mailVo.setSender_mail_addr(propertyService.getProperty("clms.admin.email"));

			// 로케일, 인코딩 설정, Time Zone
			mailVo.setLocale(vo.getSession_user_locale());
			mailVo.setEncoding("utf-8");
			mailVo.setTime_zone("GMT+0");
			mailVo.setIs_dst("false");

			// 메일내용 조회
			listConsiderationDeliveryMailInfo = commonDAO.list("clm.manage.listConsiderationDeliveryMailInfo", vo);

			if (listConsiderationDeliveryMailInfo != null && listConsiderationDeliveryMailInfo.size() > 0) {

				int mail_cnt = listConsiderationDeliveryMailInfo.size();

				// 받는 사람
				String[] iseq_ids = null;
				String[] rec_types = null;
				String[] rec_addrs = null;

				iseq_ids = new String[mail_cnt];
				rec_types = new String[mail_cnt];
				rec_addrs = new String[mail_cnt];

				for (int i = 0; i < listConsiderationDeliveryMailInfo.size(); i++) {
					respLom = (ListOrderedMap) listConsiderationDeliveryMailInfo.get(i);
					cntrtRespId = (String) respLom.get("respman_id");

					// 상신자 정보 조회
					if (cntrtRespId != null && !"".equals(cntrtRespId)) {
						cntrtRespMailAddr = esbOrgService.getUserEpMailAddr(cntrtRespId);
					}

					// STATUS 값 설정 - Default "0"
					mailVo.setStatus("1");

					iseq_ids[i] = "1";
					rec_types[i] = "t";
					rec_addrs[i] = cntrtRespMailAddr;
					hm.put("mail_type", "24");
					hm.put("main_title", (String) messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, locale1));
					hm.put("main_link", propertyService.getProperty("secfw.url.domain") + "/login.do");
					hm.put("param1", cntrtTitle);
					// 메시지 처리 - [cntrtTitle] 건에 대한 검토의견을 보내드립니다.
					hm.put("param2", (String) messageSource.getMessage("clm.page.field.mailsend.sendConsiderationDeliveryMailSend01", amsg, locale1));
					String contentHtml = this.getMailContent(hm);
					mailVo.setBhtml_content_check("true");
					mailVo.setIseq_ids(iseq_ids);
					mailVo.setRec_types(rec_types);
					mailVo.setRec_addrs(rec_addrs);
					mailVo.setBody(contentHtml.toString());
				}

				if (cntrtRespMailAddr != null && !"".equals(cntrtRespMailAddr)) {
					/*********************************************************
					 * 메일 내역 등록
					 **********************************************************/
					esbMailService.insertMail(mailVo);

					/********************************************************
					 * 메일전송
					 **********************************************************/
					try {
						esbMailService.sendMail(moduleId, misId);
					} catch (Exception e) {
						misId = EsbUtil.generateMisId("MAIL");
						mailVo.setMis_id(misId);
						esbMailService.insertAdminMail(mailVo);

						esbMailService.sendMail(moduleId, misId);

						misId = EsbUtil.generateMisId("MAIL");
						mailVo.setMis_id(misId);
						esbMailService.insertAdminMailSub(mailVo);

						esbMailService.sendMail(moduleId, misId);
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 법무 검토요청 발신 - 2012.01.04 서유강대리 요청[유관부서 관련 메일 없음]
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	public void sendConsiderationApplyMailSend(com.sec.clm.review.dto.ConsultationVO vo) throws Exception {
		try {
			List<?> listConsiderationApplyMailInfo = null;
			ListOrderedMap respLom = null;
			String cntrtRespId = "";
			String cntrtTitle = vo.getReq_title();
			String cntrtRespMailAddr = "";
			HashMap<String, String> hm = new HashMap<String, String>();

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			MailVO mailVo = new MailVO();
			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			// 모듈아이디 및 misId 세팅 -> (각 시스템/업무별로 개별 Key값을 입력)
			String moduleId = vo.getSys_cd();
			String misId = EsbUtil.generateMisId("MAIL");

			Locale locale1 = new Locale((String) vo.getSession_user_locale());

			String[] amsg = { cntrtTitle };

			mailVo.setModule_id(moduleId);
			mailVo.setMis_id(misId);
			mailVo.setMsg_key("11");
			mailVo.setSubject(messageSource.getMessage("clm.page.field.mailsend.subjectTitle", null, locale1));
			// 보내는 사람
			mailVo.setSender_single_id(propertyService.getProperty("clms.admin.id"));
			mailVo.setSender_mail_addr(propertyService.getProperty("clms.admin.email"));

			// 로케일, 인코딩 설정, Time Zone
			mailVo.setLocale(vo.getSession_user_locale());
			mailVo.setEncoding("utf-8");
			mailVo.setTime_zone("GMT+0");
			mailVo.setIs_dst("false");

			// 메일내용 조회
			listConsiderationApplyMailInfo = commonDAO.list("clm.manage.listConsiderationApplyMailInfo", vo);

			if (listConsiderationApplyMailInfo != null && listConsiderationApplyMailInfo.size() > 0) {

				int mail_cnt = listConsiderationApplyMailInfo.size();

				// 받는 사람
				String[] iseq_ids = null;
				String[] rec_types = null;
				String[] rec_addrs = null;

				iseq_ids = new String[mail_cnt];
				rec_types = new String[mail_cnt];
				rec_addrs = new String[mail_cnt];

				for (int i = 0; i < listConsiderationApplyMailInfo.size(); i++) {
					respLom = (ListOrderedMap) listConsiderationApplyMailInfo.get(i);
					cntrtRespId = (String) respLom.get("respman_id");

					// 상신자 정보 조회
					if (cntrtRespId != null && !"".equals(cntrtRespId)) {
						cntrtRespMailAddr = esbOrgService.getUserEpMailAddr(cntrtRespId);
					}

					// STATUS 값 설정 - Default "0"
					mailVo.setStatus("1");

					iseq_ids[i] = "1";
					rec_types[i] = "t";
					rec_addrs[i] = cntrtRespMailAddr;
					hm.put("mail_type", "24");
					hm.put("main_title", (String) messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, locale1));
					hm.put("main_link", "http://clm.samsung.net/login.do");
					hm.put("main_link", propertyService.getProperty("secfw.url.domain") + "/login.do");
					hm.put("param1", cntrtTitle);
					// 메시지 처리 - [cntrtTitle] 건에 대한 검토의견이 필요합니다. 검토를 부탁드립니다.
					hm.put("param2", (String) messageSource.getMessage("clm.page.field.mailsend.sendConsiderationApplyMailSend01", amsg, locale1));
					String contentHtml = this.getMailContent(hm);
					mailVo.setBhtml_content_check("true");
					mailVo.setIseq_ids(iseq_ids);
					mailVo.setRec_types(rec_types);
					mailVo.setRec_addrs(rec_addrs);
					mailVo.setBody(contentHtml.toString());
				}

				if (cntrtRespMailAddr != null && !"".equals(cntrtRespMailAddr)) {
					/*********************************************************
					 * 메일 내역 등록
					 **********************************************************/
					esbMailService.insertMail(mailVo);

					/*********************************************************
					 * 메일전송
					 **********************************************************/
					try {
						esbMailService.sendMail(moduleId, misId);
					} catch (Exception e) {
						misId = EsbUtil.generateMisId("MAIL");
						mailVo.setMis_id(misId);
						esbMailService.insertAdminMail(mailVo);

						esbMailService.sendMail(moduleId, misId);

						misId = EsbUtil.generateMisId("MAIL");
						mailVo.setMis_id(misId);
						esbMailService.insertAdminMailSub(mailVo);

						esbMailService.sendMail(moduleId, misId);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 법무 검토 후 회신 메일 방송[일반/최종본]
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return List
	 * @throws Exception
	 * @Deprecated
	 */
	@Deprecated
	public void sendConsiderationReplyMailSend(com.sec.clm.review.dto.ConsultationVO vo) throws Exception {
		try {
			Locale locale1 = new Locale((String) vo.getSession_user_locale());
			// Set default mail subject
			String subject = (String) messageSource.getMessage("clm.page.field.mailsend.subjectTitle", null, locale1);

			List<?> listConsiderationRequesterMailInfo = null;
			List<?> listConsiderationReplyMailInfo = null;

			ListOrderedMap mailLom = null;
			ListOrderedMap respLom = null;
			String cntrtTitle = vo.getReq_title();
			String cntrtRespMailAddr = "";
			String cntrt_no = "";
			HashMap<String, String> hm = new HashMap<String, String>();

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			MailVO mailVo = new MailVO();
			MailVO reqMailVo = new MailVO(); // Requester 메시지 분기용 mailVo 처리
			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			// 모듈아이디 및 misId 세팅 -> (각 시스템/업무별로 개별 Key값을 입력)
			String moduleId = vo.getSys_cd();
			String misId = EsbUtil.generateMisId("MAIL");
			String reqMisId = EsbUtil.generateMisId("MAIL2");
			mailVo.setModule_id(moduleId);
			mailVo.setMis_id(misId);
			mailVo.setMsg_key("11");

			// 보내는 사람
			mailVo.setSender_single_id(propertyService.getProperty("clms.admin.id"));
			mailVo.setSender_mail_addr(propertyService.getProperty("clms.admin.email"));

			// 로케일, 인코딩 설정, Time Zone
			mailVo.setLocale(vo.getSession_user_locale());
			mailVo.setEncoding("utf-8");
			mailVo.setTime_zone("GMT+0");
			mailVo.setIs_dst("false");

			// 메일정보 조회
			List<?> listCnsdreqMailInfo = commonDAO.list("clm.manage.listCnsdreqMailInfo", vo);
			mailLom = (ListOrderedMap) listCnsdreqMailInfo.get(0);
			cntrt_no = (String) mailLom.get("cntrt_no");
			vo.setComp_cd((String) mailLom.get("comp_cd"));

			mailVo.setSubject(subject);

			if (vo.getPlndbn_req_yn().equals("Y")) { // 최종본 의뢰
				// 메일내용 조회
				listConsiderationRequesterMailInfo = commonDAO.list("clm.manage.reqUserList", vo); // Requester
																									// 추가
																									// 조회.
																									// 메시지
																									// 분기처리
																									// 위해
				listConsiderationReplyMailInfo = commonDAO.list("clm.manage.listConsiderationReplyMailInfoByFinalReviewer", vo); // Legal
																																	// Admin
																																	// 조회.
			} else {
				// 메일내용 조회
				listConsiderationReplyMailInfo = commonDAO.list("clm.manage.reqUserList", vo); // 법무
																								// 검토회신
																								// 발신(to
																								// requester,
																								// only)
			}

			if (listConsiderationReplyMailInfo != null && listConsiderationReplyMailInfo.size() > 0) {

				int mail_cnt = listConsiderationReplyMailInfo.size();

				// 받는 사람
				String[] iseq_ids = null;
				String[] rec_types = null;
				String[] rec_addrs = null;

				iseq_ids = new String[mail_cnt];
				rec_types = new String[mail_cnt];
				rec_addrs = new String[mail_cnt];

				getLogger().debug("############### list size = " + listConsiderationReplyMailInfo.size());

				for (int i = 0; i < listConsiderationReplyMailInfo.size(); i++) {

					respLom = (ListOrderedMap) listConsiderationReplyMailInfo.get(i);

					// 상신자 정보 조회
					cntrtRespMailAddr = (String) respLom.get("email");

					// 2014-02-13 Kevin. 메일 발송을 배치를 통해 처리하기 위해 설정 값 변경.
					mailVo.setStatus("0");

					iseq_ids[i] = "1";
					rec_types[i] = "t";
					rec_addrs[i] = cntrtRespMailAddr;
					hm.put("mail_type", "24");
					hm.put("cntrt_no", cntrt_no);
					hm.put("param1", cntrtTitle);
					hm.put("main_title", (String) messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, locale1));

					if (vo.getPlndbn_req_yn().equals("Y")) { // 최종본 의뢰
						// Legal Admin 발송을 위해 email 서식변경
						hm.put("mail_type", "Legal");
						hm.put("param2", vo.getSession_user_nm() + "/" + vo.getSession_jikgup_nm() + "/" + vo.getSession_dept_nm());
						hm.put("param3", (String) messageSource.getMessage("clm.page.field.mailsend.sendConsiderationReplyMailSend07", null, locale1));
					} else {
						hm.put("param2", (String) messageSource.getMessage("clm.page.field.mailsend.sendConsiderationReplyMailSend02", null, locale1));
					}

					hm.put("main_link", propertyService.getProperty("secfw.url.domain") + "/login.do");

					String contentHtml = this.getMailContent(hm);
					mailVo.setBhtml_content_check("true");
					mailVo.setIseq_ids(iseq_ids);
					mailVo.setRec_types(rec_types);
					mailVo.setRec_addrs(rec_addrs);
					mailVo.setBody(contentHtml.toString());

				} // end for

				if (cntrtRespMailAddr != null && !"".equals(cntrtRespMailAddr)) {
					/*********************************************************
					 * 메일 내역 등록
					 **********************************************************/
					esbMailService.insertMail(mailVo);
				}
			}

			// ReqMail 분기 처리 위해 vo value bind 및 mis id 추가 생성 2014-07-17 신성우
			reqMailVo = mailVo;
			reqMailVo.setMis_id(reqMisId);

			// Requester 용 메일 발송
			if (listConsiderationRequesterMailInfo != null && listConsiderationRequesterMailInfo.size() > 0) {

				int mail_cnt = listConsiderationRequesterMailInfo.size();

				// 받는 사람
				String[] iseq_ids = null;
				String[] rec_types = null;
				String[] rec_addrs = null;

				iseq_ids = new String[mail_cnt];
				rec_types = new String[mail_cnt];
				rec_addrs = new String[mail_cnt];

				getLogger().info("############### list size = " + listConsiderationRequesterMailInfo.size());

				for (int i = 0; i < listConsiderationRequesterMailInfo.size(); i++) {

					respLom = (ListOrderedMap) listConsiderationRequesterMailInfo.get(i);
					// 상신자 정보 조회
					cntrtRespMailAddr = (String) respLom.get("email");

					// 2014-02-13 Kevin. 메일 발송을 배치를 통해 처리하기 위해 설정 값 변경.
					reqMailVo.setStatus("0");

					iseq_ids[i] = "1";
					rec_types[i] = "t";
					rec_addrs[i] = cntrtRespMailAddr;
					hm.put("mail_type", "24");
					hm.put("cntrt_no", cntrt_no);
					hm.put("param1", cntrtTitle);
					hm.put("main_title", (String) messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, locale1));

					// 최종본 의뢰
					hm.put("main_link", propertyService.getProperty("secfw.url.domain") + "/login.do");
					hm.put("param2", (String) messageSource.getMessage("clm.page.field.mailsend.sendConsiderationReplyMailSend01", null, locale1));

					String contentHtml = this.getMailContent(hm);
					reqMailVo.setBhtml_content_check("true");
					reqMailVo.setIseq_ids(iseq_ids);
					reqMailVo.setRec_types(rec_types);
					reqMailVo.setRec_addrs(rec_addrs);
					reqMailVo.setBody(contentHtml.toString());

				} // end for

				if (cntrtRespMailAddr != null && !"".equals(cntrtRespMailAddr)) {
					/*********************************************************
					 * 메일 내역 등록
					 **********************************************************/
					esbMailService.insertMail(reqMailVo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			getLogger().info("############################################## Exception :  " + e.toString());
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			getLogger().info("############################################## Throwable :  " + t.toString());
			throw new Exception("Error");
		}
	}
	/**
	 * 
	 * review request가 replied 됬을떄 notification email 발송
	 * 
	 */
	@SuppressWarnings({ "unchecked" })
	public void sendEmailReviewReplied(com.sec.clm.review.dto.ConsultationVO vo) throws Exception {
		try {

			Locale locale1 = new Locale((String) vo.getSession_user_locale());
			// Set default mail subject
			String subject = (String) messageSource.getMessage("clm.page.field.mailsend.subjectTitle", null, locale1);
			String main_title = "";
			String contents = "";

			List<ListOrderedMap> requesterList = null;
			List<ListOrderedMap> legalAdminList = null;

			ListOrderedMap mailLom = null;
			ListOrderedMap respLom = null;

			String cntrtTitle = vo.getReq_title();
			String cntrtRespMailAddr = "";
			String cntrt_no = "";
			HashMap<String, String> hm = new HashMap<String, String>();

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			MailVO mailVo = new MailVO();

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			// 모듈아이디 및 misId 세팅 -> (각 시스템/업무별로 개별 Key값을 입력)
			String moduleId = vo.getSys_cd();
			String misId = EsbUtil.generateMisId("MAIL");

			setDefaultValueForMailVO(vo.getSession_user_locale(), mailVo, moduleId, misId);

			// 메일정보 조회
			List<ListOrderedMap> listCnsdreqMailInfo = (List<ListOrderedMap>) commonDAO.list("clm.manage.listCnsdreqMailInfo", vo);
			mailLom = (ListOrderedMap) listCnsdreqMailInfo.get(0);
			cntrt_no = (String) mailLom.get("cntrt_no");
			vo.setComp_cd((String) mailLom.get("comp_cd"));

			// Final reply 구분자에 의한 메일 subject와 main title 및 contents 구성.
			if (vo.getPlndbn_req_yn().equals("Y")) {
				subject = (String) messageSource.getMessage("selms.email.contract.review.finalReply.subject", null, locale1);
				main_title = (String) messageSource.getMessage("selms.email.contract.review.finalReply.contents.title", null, locale1);
				contents = (String) messageSource.getMessage("selms.email.contract.review.finalReply.contents.body.requester", null, locale1);
			} else {
				subject = (String) messageSource.getMessage("selms.email.contract.review.reply.subject", null, locale1);
				main_title = (String) messageSource.getMessage("selms.email.contract.review.reply.contents.title", null, locale1);
				contents = (String) messageSource.getMessage("selms.email.contract.review.reply.contents.body", null, locale1);
			}

			// send email to requester start
			mailVo.setSubject(subject);

			requesterList = (List<ListOrderedMap>) commonDAO.list("clm.manage.reqUserList", vo);

			int mail_cnt = requesterList.size();

			// 받는 사람
			String[] iseq_ids = null;
			String[] rec_types = null;
			String[] rec_addrs = null;

			iseq_ids = new String[mail_cnt];
			rec_types = new String[mail_cnt];
			rec_addrs = new String[mail_cnt];

			for (int i = 0; i < requesterList.size(); i++) {

				respLom = (ListOrderedMap) requesterList.get(i);
				// 상신자 정보 조회
				cntrtRespMailAddr = (String) respLom.get("email");

				iseq_ids[i] = "1";
				rec_types[i] = "t";
				rec_addrs[i] = cntrtRespMailAddr;

			} // end for

			if (rec_addrs != null && rec_addrs.length > 0) {
				/*********************************************************
				 * 메일 내역 등록
				 **********************************************************/
				hm.put("mail_type", "24");
				hm.put("cntrt_no", cntrt_no);
				hm.put("param1", cntrtTitle);
				hm.put("main_title", main_title);

				hm.put("main_link", propertyService.getProperty("secfw.url.domain") + "/login.do");
				hm.put("param2", contents);

				String contentHtml = this.getMailContent(hm);
				mailVo.setBhtml_content_check("true");
				mailVo.setIseq_ids(iseq_ids);
				mailVo.setRec_types(rec_types);
				mailVo.setRec_addrs(rec_addrs);
				mailVo.setBody(contentHtml.toString());

				esbMailService.insertMail(mailVo);
			}

			// Final reply, send email to Legal Admin additionally
			if (vo.getPlndbn_req_yn().equals("Y")) {

				contents = (String) messageSource.getMessage("selms.email.contract.review.finalReply.contents.body.legalAdmin", null, locale1);

				mailVo = new MailVO();

				moduleId = vo.getSys_cd();
				misId = EsbUtil.generateMisId("MAIL");

				setDefaultValueForMailVO(vo.getSession_user_locale(), mailVo, moduleId, misId);

				mailVo.setSubject(subject);
				legalAdminList = commonDAO.list("clm.manage.listConsiderationReplyMailInfoByFinalReviewer", vo);
				mail_cnt = legalAdminList.size();

				// 받는 사람
				iseq_ids = null;
				rec_types = null;
				rec_addrs = null;

				iseq_ids = new String[mail_cnt];
				rec_types = new String[mail_cnt];
				rec_addrs = new String[mail_cnt];

				for (int i = 0; i < legalAdminList.size(); i++) {

					respLom = (ListOrderedMap) legalAdminList.get(i);

					// 상신자 정보 조회
					cntrtRespMailAddr = (String) respLom.get("email");

					// 2014-02-13 Kevin. 메일 발송을 배치를 통해 처리하기 위해 설정 값 변경.

					iseq_ids[i] = "1";
					rec_types[i] = "t";
					rec_addrs[i] = cntrtRespMailAddr;

				} // end for

				if (rec_addrs != null && rec_addrs.length > 0) {
					hm.put("cntrt_no", cntrt_no);
					hm.put("param1", cntrtTitle);
					hm.put("main_title", main_title);
					hm.put("mail_type", "Legal");
					hm.put("param2", vo.getSession_user_nm() + "/" + vo.getSession_jikgup_nm() + "/" + vo.getSession_dept_nm());
					hm.put("param3", contents);
					hm.put("main_link", propertyService.getProperty("secfw.url.domain") + "/login.do");

					String contentHtml = this.getMailContent(hm);
					mailVo.setBhtml_content_check("true");
					mailVo.setIseq_ids(iseq_ids);
					mailVo.setRec_types(rec_types);
					mailVo.setRec_addrs(rec_addrs);
					mailVo.setBody(contentHtml.toString());

					/*********************************************************
					 * 메일 내역 등록
					 **********************************************************/
					esbMailService.insertMail(mailVo);
				}

				// 메일내용 조회
				requesterList = commonDAO.list("clm.manage.reqUserList", vo);
				legalAdminList = commonDAO.list("clm.manage.listConsiderationReplyMailInfoByFinalReviewer", vo); // Legal
																													// Admin
																													// 조회.
			}

		} catch (Exception e) {
			e.printStackTrace();
			getLogger().info("############################################## Exception :  " + e.toString());
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			getLogger().info("############################################## Throwable :  " + t.toString());
			throw new Exception("Error");
		}
	}

	private void setDefaultValueForMailVO(String locale, MailVO mailVo, String moduleId, String misId) {
		mailVo.setModule_id(moduleId);
		mailVo.setMis_id(misId);
		mailVo.setMsg_key("11");
		mailVo.setStatus("0");

		// 보내는 사람
		mailVo.setSender_single_id(propertyService.getProperty("clms.admin.id"));
		mailVo.setSender_mail_addr(propertyService.getProperty("clms.admin.email"));

		// 로케일, 인코딩 설정, Time Zone
		mailVo.setLocale(locale);
		mailVo.setEncoding("utf-8");
		mailVo.setTime_zone("GMT+0");
		mailVo.setIs_dst("false");
	}

	/**
	 * Legal Admin이 법무담당자를 지정했을떄 발신
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	public void sendConsiderationRespmanMailSend(com.sec.clm.review.dto.ConsultationVO vo) throws Exception {
		try {
			List<?> listConsiderationReplyMailInfo = null;
			ListOrderedMap mailLom = null;
			ListOrderedMap respLom = null;
			String cntrtReqId = "";
			String cntrtTitle = vo.getReq_title();
			String cntrtRespMailAddr = "";
			String cntrt_no = "";
			String subject = "";
			String contents = "";
			String title = "";

			HashMap<String, String> hm = new HashMap<String, String>();

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			MailVO mailVo = new MailVO();
			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			// 모듈아이디 및 misId 세팅 -> (각 시스템/업무별로 개별 Key값을 입력)
			String moduleId = vo.getSys_cd();
			String misId = EsbUtil.generateMisId("MAIL");

			Locale locale1 = new Locale((String) vo.getSession_user_locale());

			if ("Y".equals(vo.getMn_respman_apnt_yn()) || "Y".equals(vo.getVc_respman_apnt_yn())) {
				// 메시지 처리 - 검토 의뢰하신 계약에 대한 검토자가 변경되었습니다.<br>효율적인 계약검토를 위하여 계약
				// 검토자가 변경되었으니,<br>향후에는 변경된 검토자와 협의하여 주시기 바랍니다.
				// contents = (String)
				// messageSource.getMessage("clm.page.field.mailsend.sendConsiderationRespmanMailSend02",
				// null, locale1);
				subject = (String) messageSource.getMessage("selms.email.contract.review.reassign.subject", null, locale1);
				title = (String) messageSource.getMessage("selms.email.contract.review.reassign.contents.title", null, locale1);
				contents = (String) messageSource.getMessage("selms.email.contract.review.reassign.contents.body", null, locale1);
			} else {
				// 메시지 처리 - 검토 의뢰하신 계약에 대한 검토자가 지정되었습니다.<br>검토 회신 일정 등 문의사항이 있는
				// 경우 지정된 검토자와 직접 협의하시기 바랍니다.<br>검토 회신 완료된 경우 싱글 메일로 자동 통보됩니다.
				// contents = (String)
				// messageSource.getMessage("clm.page.field.mailsend.sendConsiderationRespmanMailSend03",
				// null, locale1);
				subject = (String) messageSource.getMessage("selms.email.contract.review.assign.subject", null, locale1);
				title = (String) messageSource.getMessage("selms.email.contract.review.assign.contents.title", null, locale1);
				contents = (String) messageSource.getMessage("selms.email.contract.review.assign.contents.body", null, locale1);
			}

			mailVo.setModule_id(moduleId);
			mailVo.setMis_id(misId);
			mailVo.setMsg_key("11");
			// Sungwoo 2014-07-15 replacement subject
			mailVo.setSubject(subject);
			// 보내는 사람
			mailVo.setSender_single_id(propertyService.getProperty("clms.admin.id"));
			mailVo.setSender_mail_addr(propertyService.getProperty("clms.admin.email"));

			// 로케일, 인코딩 설정, Time Zone
			mailVo.setLocale(vo.getSession_user_locale());
			mailVo.setEncoding("utf-8");
			mailVo.setTime_zone("GMT+0");
			mailVo.setIs_dst("false");

			// 메일정보 조회
			List<?> listCnsdreqMailInfo = commonDAO.list("clm.manage.listCnsdreqMailInfo", vo);
			mailLom = (ListOrderedMap) listCnsdreqMailInfo.get(0);
			cntrt_no = (String) mailLom.get("cntrt_no");
			vo.setComp_cd((String) mailLom.get("comp_cd"));

			// 메일내용 조회
			listConsiderationReplyMailInfo = commonDAO.list("clm.manage.listConsiderationRespManMailInfo", vo);

			if (listConsiderationReplyMailInfo != null && !listConsiderationReplyMailInfo.isEmpty()) {

				int mail_cnt = listConsiderationReplyMailInfo.size();

				// 받는 사람
				String[] iseq_ids = null;
				String[] rec_types = null;
				String[] rec_addrs = null;

				iseq_ids = new String[mail_cnt];
				rec_types = new String[mail_cnt];
				rec_addrs = new String[mail_cnt];

				for (int i = 0; i < mail_cnt; i++) {

					respLom = (ListOrderedMap) listConsiderationReplyMailInfo.get(i);
					cntrtReqId = (String) respLom.get("USER_ID");

					// 상신자 정보 조회
					if (StringUtils.isNotEmpty(cntrtReqId)) {
						cntrtRespMailAddr = esbOrgService.getUserEpMailAddr(cntrtReqId);
					}

					iseq_ids[i] = "1";
					rec_types[i] = "t";
					rec_addrs[i] = cntrtRespMailAddr;

				} // end for

				if (rec_addrs != null && rec_addrs.length > 0) {
					// STATUS 값 설정 - Default "0"

					mailVo.setStatus("1");

					hm.put("mail_type", "24");
					// hm.put("main_title",
					// messageSource.getMessage("clm.page.field.mailsend.requiredTitle",
					// null, locale1).toString());
					hm.put("main_title", title);
					hm.put("main_link", propertyService.getProperty("secfw.url.domain") + "/login.do");
					hm.put("cntrt_no", cntrt_no);
					hm.put("param1", cntrtTitle); // 의뢰명
					hm.put("param2", contents);

					String contentHtml = this.getMailContent(hm);
					mailVo.setBody(contentHtml.toString());
					mailVo.setBhtml_content_check("true");
					mailVo.setIseq_ids(iseq_ids);
					mailVo.setRec_types(rec_types);
					mailVo.setRec_addrs(rec_addrs);

					/*********************************************************
					 * 메일 내역 등록
					 **********************************************************/
					esbMailService.insertMail(mailVo);

					/*********************************************************
					 * 메일전송
					 **********************************************************/

					try {
						esbMailService.sendMail(moduleId, misId);
					} catch (Exception e) {
						misId = EsbUtil.generateMisId("MAIL");
						mailVo.setMis_id(misId);
						esbMailService.insertAdminMail(mailVo);

						esbMailService.sendMail(moduleId, misId);

						misId = EsbUtil.generateMisId("MAIL");
						mailVo.setMis_id(misId);
						esbMailService.insertAdminMailSub(mailVo);

						esbMailService.sendMail(moduleId, misId);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	// 종료확인
	public void sendCompletionNotifyMailSend(CompletionVO vo) throws Exception {
		try {
			List<?> listCompletionNotifyMailInfo = null;
			ListOrderedMap completionLom = null;
			String reqmanId = "";
			String completionTitle = "";
			String reqMailAddr = "";
			// 합의자정보조회 및 메일내용
			listCompletionNotifyMailInfo = this.listCompletionNotifyMailInfo(vo);

			if (listCompletionNotifyMailInfo != null && listCompletionNotifyMailInfo.size() > 0) {
				completionLom = (ListOrderedMap) listCompletionNotifyMailInfo.get(0);
				reqmanId = (String) completionLom.get("reqman_id");
				completionTitle = (String) completionLom.get("cntrt_nm");
			}

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			MailVO mailVo = new MailVO();
			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			// 모듈아이디 및 misId 세팅 -> (각 시스템/업무별로 개별 Key값을 입력)
			String moduleId = vo.getSys_cd();
			String misId = EsbUtil.generateMisId("MAIL");

			Locale locale1 = new Locale((String) vo.getSession_user_locale());

			mailVo.setModule_id(moduleId);
			mailVo.setMis_id(misId);
			mailVo.setMsg_key("11");

			mailVo.setSubject(messageSource.getMessage("clm.page.field.mailsend.subjectTitle", null, locale1));
			// 보내는 사람
			mailVo.setSender_single_id(propertyService.getProperty("clms.admin.id"));
			mailVo.setSender_mail_addr(propertyService.getProperty("clms.admin.email"));

			// 로케일, 인코딩 설정, Time Zone
			mailVo.setLocale(vo.getSession_user_locale());
			mailVo.setEncoding("utf-8");
			mailVo.setTime_zone("GMT+0");
			mailVo.setIs_dst("false");

			// 상신자 정보 조회
			if (reqmanId != null && !"".equals(reqmanId)) {
				reqMailAddr = esbOrgService.getUserEpMailAddr(reqmanId);
			}

			// STATUS 값 설정 - Default "0"
			mailVo.setStatus("1");

			// 받는 사람
			String[] iseq_ids = null;
			String[] rec_types = null;
			String[] rec_addrs = null;

			iseq_ids = new String[1];
			rec_types = new String[1];
			rec_addrs = new String[1];
			iseq_ids[0] = "1";
			rec_types[0] = "t";
			rec_addrs[0] = reqMailAddr;
			StringBuffer contentHtml = new StringBuffer();
			String[] amsg = { completionTitle };
			contentHtml.append("<html xmlns=\"http://www.w3.org/1999/xhtml\"><HEAD><TITLE></TITLE>").append("<META content=\"text/html; charset=utf-8\" http-equiv=Content-Type>").append("<META name=GENERATOR content=ActiveSquare></HEAD>")
					.append("<BODY style=\"FONT-FAMILY: 굴림; FONT-SIZE: 10pt\">").append("<P style=\"LINE-HEIGHT: 100%; MARGIN-TOP: 0px; MARGIN-BOTTOM: 0px\">");
			contentHtml.append((String) messageSource.getMessage("clm.page.field.mailsend.sendCompletionNotifyMailSend02", amsg, locale1) + "</P></BODY></html>");
			mailVo.setBhtml_content_check("true");
			mailVo.setIseq_ids(iseq_ids);
			mailVo.setRec_types(rec_types);
			mailVo.setRec_addrs(rec_addrs);
			mailVo.setBody(contentHtml.toString());

			if (reqMailAddr != null && !"".equals(reqMailAddr)) {
				/*********************************************************
				 * 메일 내역 등록
				 **********************************************************/
				esbMailService.insertMail(mailVo);

				/*********************************************************
				 * 메일전송
				 **********************************************************/
				try {
					esbMailService.sendMail(moduleId, misId);
				} catch (Exception e) {
					misId = EsbUtil.generateMisId("MAIL");
					mailVo.setMis_id(misId);
					esbMailService.insertAdminMail(mailVo);

					esbMailService.sendMail(moduleId, misId);

					misId = EsbUtil.generateMisId("MAIL");
					mailVo.setMis_id(misId);
					esbMailService.insertAdminMailSub(mailVo);

					esbMailService.sendMail(moduleId, misId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 승인자 승인시 준법지원자에게 승인처리 알림 발신
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	public void sendCompleteStatusInformMail(ConsultationVO vo) throws Exception {
		try {
			ListOrderedMap mailLom = null;
			ListOrderedMap receiverListLom = null;
			Locale locale1 = new Locale((String) vo.getSession_user_locale());

			// 메시지 처리 - [계약종료][변경상신] (종료확인요청)
			String cntrt_nm = "";
			String blngt_orgnz_id = "";
			String cntrtRespId = "";
			String cntrtRespMailAddr = "";

			HashMap<String, String> hm = new HashMap<String, String>();
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			MailVO mailVo = new MailVO();

			// 메일정보 조회
			List<?> listCompleteMailInfo = commonDAO.list("clm.manage.detailCompleteStatusInformMail", vo);
			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			String moduleId = "LAS";
			String misId = EsbUtil.generateMisId("MAIL");

			mailVo.setModule_id(moduleId);
			mailVo.setMis_id(misId);
			mailVo.setMsg_key("11");
			// 메시지 처리 - 계약 변경확인
			mailVo.setSubject((String) messageSource.getMessage("clm.page.field.mailsend.subjectTitle", null, locale1));

			// 보내는 사람
			mailVo.setSender_single_id(propertyService.getProperty("clms.admin.id"));
			mailVo.setSender_mail_addr(propertyService.getProperty("clms.admin.email"));

			// 로케일, 인코딩 설정, Time Zone
			// mailVo.setLocale(vo.getSession_user_locale());
			mailVo.setLocale("en");
			mailVo.setEncoding("utf-8");
			mailVo.setTime_zone("GMT+0");
			mailVo.setIs_dst("false");

			mailLom = (ListOrderedMap) listCompleteMailInfo.get(0);
			cntrt_nm = (String) mailLom.get("cntrt_nm");
			blngt_orgnz_id = (String) mailLom.get("blngt_orgnz_id");

			// 준법지원자 사업부코드 설정
			vo.setBlngt_orgnz_id(blngt_orgnz_id);

			// 수신자리스트 조회
			List<?> receiverList = commonDAO.list("clm.manage.RequesterNReviewerList", vo);

			if (receiverList != null && receiverList.size() > 0) {
				int receiver_cnt = receiverList.size();

				// 받는 사람
				String[] iseq_ids = null;
				String[] rec_types = null;
				String[] rec_addrs = null;

				iseq_ids = new String[receiver_cnt];
				rec_types = new String[receiver_cnt];
				rec_addrs = new String[receiver_cnt];

				for (int i = 0; i < receiverList.size(); i++) {
					receiverListLom = (ListOrderedMap) receiverList.get(i);
					cntrtRespId = (String) receiverListLom.get("USER_ID");

					// 상신자 정보 조회
					if (cntrtRespId != null && !"".equals(cntrtRespId)) {
						cntrtRespMailAddr = esbOrgService.getUserEpMailAddr(cntrtRespId);
					}

					// STATUS 값 설정 - Default "0"
					mailVo.setStatus("1");

					// iseq_ids.push("12");
					iseq_ids[i] = "1";
					rec_types[i] = "t";
					rec_addrs[i] = cntrtRespMailAddr;
					hm.put("mail_type", "24");
					hm.put("main_title", messageSource.getMessage("clm.page.field.mailsend.sendCompleteStatusInformMail01", null, locale1).toString());
					hm.put("main_link", propertyService.getProperty("secfw.url.domain") + "/login.do");
					hm.put("param1", cntrt_nm);
					hm.put("param2", messageSource.getMessage("clm.page.field.mailsend.sendCompleteStatusInformMail04", null, locale1).toString());

					String contentHtml = this.getMailContent(hm);

					mailVo.setBhtml_content_check("true");
					mailVo.setIseq_ids(iseq_ids);
					mailVo.setRec_types(rec_types);
					mailVo.setRec_addrs(rec_addrs);
					mailVo.setBody(contentHtml.toString());
				}

				if (cntrtRespMailAddr != null && !"".equals(cntrtRespMailAddr)) {
					/*********************************************************
					 * 메일 내역 등록
					 **********************************************************/
					esbMailService.insertMail(mailVo);

					/********************************************************
					 * 메일전송
					 **********************************************************/
					try {
						esbMailService.sendMail(moduleId, misId);
					} catch (Exception e) {
						misId = EsbUtil.generateMisId("MAIL");
						mailVo.setMis_id(misId);
						esbMailService.insertAdminMail(mailVo);

						esbMailService.sendMail(moduleId, misId);

						misId = EsbUtil.generateMisId("MAIL");
						mailVo.setMis_id(misId);
						esbMailService.insertAdminMailSub(mailVo);
						esbMailService.sendMail(moduleId, misId);
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 메일 컨텐츠 생성
	 * 
	 * @param HashMap
	 * @return String
	 * @throws Exception
	 */
	public String getMailContent(HashMap hm) throws Exception {
		String content = "";

		// 부분별 내용
		StringBuffer topHtml = new StringBuffer();
		String contHtml = "";
		StringBuffer bottomHtml = new StringBuffer();

		// 파라미터 선언
		String mailType = (String) hm.get("mail_type");
		String mailTitle = (String) hm.get("main_title");
		String tdParam1 = (String) hm.get("param1");
		String tdParam2 = (String) hm.get("param2");
		String tdParam3 = StringUtil.bvl((String) hm.get("param3"), "");

		String last_locale = StringUtil.bvl((String) hm.get("last_locale"), "en");
		if (last_locale.length() > 2)
			last_locale = last_locale.substring(0, 2);

		/**
		 * 각 유형별 생성
		 * 
		 * 유형 : Send Notification Default 24 Send Notification to Legal Admin
		 * Legal Send Notification to System Admin etc
		 **/
		contHtml += "<table class='list_basic mt20'>";
		if (!"etc".equals(mailType)) {
			contHtml += "<colgroup>";
			contHtml += "<col width='14%' />";
			contHtml += "<col width='36%'/>";
			contHtml += "<col width='14%' />";
			contHtml += "<col width='36%'/>";
			contHtml += "</colgroup>";
		}
		if ("24".equals(mailType)) {
			// Sungwoo Replacement 2014-07-14
			contHtml += "<tr><th>Request Title</th><td colspan='3'>" + tdParam1 + "</td></tr>";
			contHtml += "<tr class='end'><th><b>ACTION</b></th><td colspan='3'>" + tdParam2 + "</td></tr>";
		} else if ("Legal".equals(mailType)) {
			// Sungwoo Replacement 2014-07-14
			contHtml += "<tr><th>Request Title</th><td colspan='3'>" + tdParam1 + "</td></tr>";
			contHtml += "<tr><th>Requester</th><td colspan='3' >" + tdParam2 + "</td></tr>";
			contHtml += "<tr class='end'><th><b>ACTION</b></th><td colspan='3'>" + tdParam3 + "</td></tr>";
		} else if ("etc".equals(mailType)) {
			contHtml += "<tr><td>" + tdParam1 + "</td></tr>";
		}
		contHtml += "</table>";
		String strUrl = "http://" + propertyService.getProperty("secfw.url.lasdomain");

		/** 기본구성 Start **/
		// 상단 구성
		topHtml.append("<!DOCTYPE html>");
		topHtml.append("<html>");
		topHtml.append("<head>");
		topHtml.append("<meta charset='utf-8' />");
	//	topHtml.append("<meta http-equiv='X-UA-Compatible' content='IE=8; IE=9' />");
		Locale locale1 = new Locale(last_locale);

		topHtml.append("<title>" + (String) messageSource.getMessage("clm.main.title", null, locale1) + "</title>");

		topHtml.append("<link href='" + strUrl + "/style/las/" + locale1 + "/mail.css' type=\"text/css\" rel=\"stylesheet\">");
		topHtml.append("<link href='" + strUrl + "/style/las/" + locale1 + "/las.css' type=\"text/css\" rel=\"stylesheet\">");
		topHtml.append("<script language=\"javascript\" src=\"" + strUrl + "/script/clms/common.js\" type=\"text/javascript\"></script>");
		topHtml.append("</head>");

		topHtml.append("<body>");
		topHtml.append("<div class=\"mailWrap\">");
		topHtml.append("<div class=\"mail_top\"></div>");
		topHtml.append("<div class=\"mail_mid\">");

		// 제목
		topHtml.append("<DIV class=page_list>");
		topHtml.append("<DIV class=in><span>" + mailTitle + "</span></DIV>");
		topHtml.append("</DIV>");
		String slasDomain = (String) propertyService.getProperty("secfw.url.domain");
		String pageLink = (String) messageSource.getMessage("las.mail.field.lawconsultImpl.sysLink", null, locale1);

		bottomHtml.append("<div class='tC mt20'>");
		bottomHtml.append("<span class=\"btn_mail_gosys\"><a href=\"" + slasDomain + "\" target=_blank>" + pageLink + "</a></span>");
		bottomHtml.append("</div>");

		bottomHtml.append("</div>");
		bottomHtml.append("<div class=\"mail_btm\"></div>");
		bottomHtml.append("</div>");
		bottomHtml.append("</body>");
		bottomHtml.append("</html>");

		/** 기본구성 End **/

		content = topHtml.toString() + contHtml + bottomHtml.toString();

		return content;
	}

	/**
	 * 준법지원자에게 종료 알림 발신 Termination
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	public void sendCompleteInformMail(ConsultationVO vo) throws Exception {
		try {
			ListOrderedMap mailLom = null;
			ListOrderedMap receiverListLom = null;
			Locale locale1 = new Locale((String) vo.getLoac());

			// 메시지 처리 - [CLMS] 계약이 종료되었습니다.
			String req_title = "";
			String cntrtRespId = "";
			String cntrtRespMailAddr = "";
			String compCd = "";
			String cntrt_status = ""; // added Sungwoo 2014-06-30

			HashMap<String, String> hm = new HashMap<String, String>();
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			MailVO mailVo = new MailVO();

			// 메일정보 조회
			List<?> listCompleteMailInfo = commonDAO.list("clm.manage.detailCompleteStatusInformMail", vo);
			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			String moduleId = "LAS";
			String misId = EsbUtil.generateMisId("MAIL");

			
			setDefaultValueForMailVO( vo.getLoac(), mailVo, moduleId, misId);
			

			mailLom = (ListOrderedMap) listCompleteMailInfo.get(0);
			req_title = (String) mailLom.get("req_title");
			compCd = (String) mailLom.get("comp_cd");
			cntrt_status = (String) mailLom.get("cntrt_status");

			// 준법지원자 사업부코드 설정
			vo.setBlngt_orgnz_id("A00000001");
			vo.setComp_cd(compCd);

			// 수신자리스트 조회 분기처리
			List<?> receiverList = null;
			List<?> reviewerList = null;

			// C02402 일 경우 receiver, Reviewer의 메시지가 분기처리됨.
			if ("C02402".equals(cntrt_status)) {
				receiverList = commonDAO.list("clm.manage.reqUserList", vo);
				reviewerList = commonDAO.list("clm.manage.listConsiderationReReqMailInfo", vo);

			} else {
				receiverList = commonDAO.list("clm.manage.RequesterNReviewerList", vo);
			}
			
			
			mailVo.setSubject((String) messageSource.getMessage("clm.page.field.mailsend.subjectTitle", null, locale1));
			
			
			if (receiverList != null && receiverList.size() > 0) {
				int receiver_cnt = receiverList.size();

				// 받는 사람
				String[] iseq_ids = null;
				String[] rec_types = null;
				String[] rec_addrs = null;

				iseq_ids = new String[receiver_cnt];
				rec_types = new String[receiver_cnt];
				rec_addrs = new String[receiver_cnt];
				for (int i = 0; i < receiverList.size(); i++) {
					receiverListLom = (ListOrderedMap) receiverList.get(i);
					cntrtRespId = (String) receiverListLom.get("USER_ID");

					// 상신자 정보 조회
					if (cntrtRespId != null && !"".equals(cntrtRespId)) {
						cntrtRespMailAddr = esbOrgService.getUserEpMailAddr(cntrtRespId);
					}
				 
					iseq_ids[i] = "1";
					rec_types[i] = "t";
					rec_addrs[i] = cntrtRespMailAddr;
					
				}
				
			 
			
			
				
				if (rec_addrs != null && rec_addrs.length>0) {
					
					
					hm.put("mail_type", "24");
					//hm.put("main_title", (String) messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, locale1));
					hm.put("main_title", (String) messageSource.getMessage("selms.email.contract.termination.terminated.contents.title", null, locale1));
					
					hm.put("main_link", propertyService.getProperty("secfw.url.domain") + "/login.do");
					hm.put("param1", req_title);
					//hm.put("param2", messageSource.getMessage("clm.page.field.mailsend.sendCompleteInformMail03", null, locale1));
					hm.put("param2", messageSource.getMessage("selms.email.contract.termination.terminated.contents.body", null, locale1));

					// 계약연장일 경우 해당사업부 관리자에게 메일 전송
					if ("C02402".equals(cntrt_status)) {
						// SUNGWOO 2014-07-16 ROLE_CD 에 따른 분기처리.
						mailVo.setSubject((String) messageSource.getMessage("selms.email.contract.termination.extended.subject", null, locale1));
						hm.put("main_title", (String) messageSource.getMessage("selms.email.contract.termination.extended.contents.title", null, locale1));
						hm.put("param2", (String) messageSource.getMessage("selms.email.contract.termination.extended.contents.body.requester", null, locale1));
						
					} else if ("C02404".equals(cntrt_status)) {  
					//	hm.put("param2", (String) messageSource.getMessage("clm.page.field.consultation.getTerminationComment", null, locale1));
						mailVo.setSubject((String) messageSource.getMessage("selms.email.contract.termination.expired.subject", null, locale1));
						hm.put("main_title", (String) messageSource.getMessage("selms.email.contract.termination.expired.contents.title", null, locale1));
						hm.put("param2", (String) messageSource.getMessage("selms.email.contract.termination.expired.contents.body", null, locale1));
					} else if ("C02405".equals(cntrt_status)) { // Sungwoo added // Termination - 	// Cancelled
						//hm.put("param2", (String) messageSource.getMessage("clm.page.field.completion.insertCompletion06", null, locale1));
						mailVo.setSubject((String) messageSource.getMessage("selms.email.contract.termination.terminated.subject", null, locale1));
						hm.put("main_title", (String) messageSource.getMessage("selms.email.contract.termination.terminated.contents.title", null, locale1));
						hm.put("param2", (String) messageSource.getMessage("selms.email.contract.termination.terminated.contents.body", null, locale1));
					}

					String contentHtml = this.getMailContent(hm);

					mailVo.setBhtml_content_check("true");
					mailVo.setIseq_ids(iseq_ids);
					mailVo.setRec_types(rec_types);
					mailVo.setRec_addrs(rec_addrs);
					mailVo.setBody(contentHtml.toString());
					
					
					
					
					// 메일 내역 등록
					esbMailService.insertMail(mailVo);

					// 메일전송
//					try {
//						esbMailService.sendMail(moduleId, misId);
//					} catch (Exception e) {
//						misId = EsbUtil.generateMisId("MAIL");
//						mailVo.setMis_id(misId);
//						esbMailService.insertAdminMail(mailVo);
//
//						esbMailService.sendMail(moduleId, misId);
//
//						misId = EsbUtil.generateMisId("MAIL");
//						mailVo.setMis_id(misId);
//						esbMailService.insertAdminMailSub(mailVo);
//						esbMailService.sendMail(moduleId, misId);
//					}
				}
			}

			// Reviewer 발송 로직 추가
			mailVo = new MailVO();
			 
			
			setDefaultValueForMailVO( vo.getLoac(), mailVo, moduleId, EsbUtil.generateMisId("MAIL2") );
			
			
			if (reviewerList != null && reviewerList.size() > 0) {
				int receiver_cnt = reviewerList.size();

				// 받는 사람
				String[] iseq_ids = null;
				String[] rec_types = null;
				String[] rec_addrs = null;

				iseq_ids = new String[receiver_cnt];
				rec_types = new String[receiver_cnt];
				rec_addrs = new String[receiver_cnt];
				for (int i = 0; i < reviewerList.size(); i++) {
					receiverListLom = (ListOrderedMap) reviewerList.get(i);
					cntrtRespId = (String) receiverListLom.get("USER_ID");

					// 상신자 정보 조회
					if (cntrtRespId != null && !"".equals(cntrtRespId)) {
						cntrtRespMailAddr = esbOrgService.getUserEpMailAddr(cntrtRespId);
					}
					// STATUS 값 설정 - Default "0"
				//	mailVo.setStatus("1");

					iseq_ids[i] = "1";
					rec_types[i] = "t";
					rec_addrs[i] = cntrtRespMailAddr;
				
				}
				if (rec_addrs != null && rec_addrs.length>0) {
					
					mailVo.setSubject((String) messageSource.getMessage("selms.email.contract.termination.extended.subject", null, locale1));
					
					
					
					hm.put("mail_type", "24");
					//hm.put("main_title", (String) messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, locale1));
					hm.put("main_link", propertyService.getProperty("secfw.url.domain") + "/login.do");
					hm.put("param1", req_title);
					//hm.put("param2", (String) messageSource.getMessage("clm.page.field.completion.insertCompletion02", null, locale1));
					
					hm.put("main_title", (String) messageSource.getMessage("selms.email.contract.termination.extended.contents.title", null, locale1));
					hm.put("param2", (String) messageSource.getMessage("selms.email.contract.termination.extended.contents.body.reviewer", null, locale1));
					
					String contentHtml = this.getMailContent(hm);

					mailVo.setBhtml_content_check("true");
					mailVo.setIseq_ids(iseq_ids);
					mailVo.setRec_types(rec_types);
					mailVo.setRec_addrs(rec_addrs);
					mailVo.setBody(contentHtml.toString());
				//	mailVo.setSubject((String) messageSource.getMessage("clm.page.field.mailsend.subjectTitle", null, locale1));
					
					
					// 메일 내역 등록
					esbMailService.insertMail(mailVo);

					// 메일전송
//					try {
//						esbMailService.sendMail(moduleId, misId);
//					} catch (Exception e) {
//						misId = EsbUtil.generateMisId("MAIL");
//						mailVo.setMis_id(misId);
//						esbMailService.insertAdminMail(mailVo);
//
//						esbMailService.sendMail(moduleId, misId);
//
//						misId = EsbUtil.generateMisId("MAIL");
//						mailVo.setMis_id(misId);
//						esbMailService.insertAdminMailSub(mailVo);
//						esbMailService.sendMail(moduleId, misId);
//					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 승인,반려 후 메일 보내기
	 * 
	 * @param HashMap
	 *            map
	 * @return List
	 * @throws Exception
	 */
	public void sendMailAfterApproval(HashMap map) throws Exception {
		try {
			ListOrderedMap mailLom = null;
			String cnsdreq_id = StringUtil.bvl((String) map.get("cnsdreq_id"), "");
			String cntrt_id = StringUtil.bvl((String) map.get("cntrt_id"), "");
			String cntrt_no = "";
			String mail_content = "";
			String cntrtRespId = "";
			String cntrtRespMailAddr = "";
			String req_title = "";
			String locale = StringUtil.bvl(map.get("locale").toString(), "en");

			HashMap<String, String> hm = new HashMap<String, String>();
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			MailVO mailVo = new MailVO();
			CompletionVO vo = new CompletionVO();

			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			String moduleId = "LAS";
			String misId = EsbUtil.generateMisId("MAIL");

			mailVo.setModule_id(moduleId);
			mailVo.setMis_id(misId);
			mailVo.setMsg_key("11");

			// 보내는 사람
			mailVo.setSender_single_id(propertyService.getProperty("clms.admin.id"));
			mailVo.setSender_mail_addr(propertyService.getProperty("clms.admin.email"));

			// 로케일, 인코딩 설정, Time Zone
			// mailVo.setLocale(vo.getSession_user_locale());
			mailVo.setLocale("en");
			mailVo.setEncoding("utf-8");
			mailVo.setTime_zone("GMT+0");
			mailVo.setIs_dst("false");

			// 수신자 정보
			if (null != map) {
				cntrtRespId = StringUtil.bvl((String) map.get("receiver_id"), "");
				mail_content = StringUtil.bvl((String) map.get("mail_content"), "");
				req_title = StringUtil.bvl((String) map.get("req_title"), "");
			}
			// 메일정보 조회
			vo.setCnsdreq_id(cnsdreq_id);
			vo.setCntrt_id(cntrt_id);
			List<?> listCompleteMailInfo = commonDAO.list("clm.manage.detailCompleteStatusInformMail", vo);

			if (null != listCompleteMailInfo && listCompleteMailInfo.size() > 0) {
				mailLom = (ListOrderedMap) listCompleteMailInfo.get(0);
				cntrt_no = (String) mailLom.get("cntrt_no");
				req_title = (String) mailLom.get("req_title");
				hm.put("mail_type", "24");
				hm.put("main_title", (String) messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, new Locale(locale)));
				hm.put("main_link", propertyService.getProperty("secfw.url.domain") + "/login.do");
				hm.put("cntrt_no", cntrt_no);
				hm.put("param1", req_title);
				hm.put("param2", mail_content);

				String contentHtml = this.getMailContent(hm);

				// 담당자 메일주소 조회
				if (cntrtRespId != null && !"".equals(cntrtRespId)) {
					cntrtRespMailAddr = esbOrgService.getUserEpMailAddr(cntrtRespId);
				}
				mailVo.setSubject((String) messageSource.getMessage("clm.page.field.mailsend.subjectTitle", null, new Locale(locale)));
				mailVo.setStatus("1");

				String[] iseq_ids = new String[1];
				String[] rec_types = new String[1];
				String[] rec_addrs = new String[1];
				iseq_ids[0] = "1";
				rec_types[0] = "t";
				rec_addrs[0] = cntrtRespMailAddr;

				mailVo.setBhtml_content_check("true");
				mailVo.setIseq_ids(iseq_ids);
				mailVo.setRec_types(rec_types);
				mailVo.setRec_addrs(rec_addrs);
				mailVo.setBody(contentHtml.toString());

				if (cntrtRespMailAddr != null && !"".equals(cntrtRespMailAddr)) {
					/*********************************************************
					 * 메일 내역 등록
					 **********************************************************/
					esbMailService.insertMail(mailVo);

					/********************************************************
					 * 메일전송
					 **********************************************************/
					try {
						esbMailService.sendMail(moduleId, misId);
					} catch (Exception e) {
						misId = EsbUtil.generateMisId("MAIL");
						mailVo.setMis_id(misId);
						esbMailService.insertAdminMail(mailVo);

						esbMailService.sendMail(moduleId, misId);

						misId = EsbUtil.generateMisId("MAIL");
						mailVo.setMis_id(misId);
						esbMailService.insertAdminMailSub(mailVo);
						esbMailService.sendMail(moduleId, misId);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 접수 알림 메일 전송 (해당사업부관리자에게 메일전송) signing, execution
	 * 
	 * @param CompletionVO
	 *            vo
	 * @return List
	 * @throws Exception
	 */
	public void sendAcceptInfoMail(HashMap map) throws Exception {
		try {
			ListOrderedMap mailLom = null;
			ListOrderedMap receiverListLom = null;

			String cnsdreq_id = StringUtil.bvl(map.get("cnsdreq_id").toString(), "");
			String cntrt_id = StringUtil.bvl(map.get("cntrt_id").toString(), "");
			String mail_content = StringUtil.bvl(map.get("mail_content").toString(), "");
			String req_title = "";
			String cntrt_no = "";
			String blngt_orgnz_id = "";
			String cntrtRespId = "";
			String cntrtRespMailAddr = "";
			String contentHtml = "";
			String compCd = "";
			String locale = StringUtil.bvl(map.get("locale").toString(), "en");
			String reciver_list = map.get("reciver_list") != null ? StringUtil.bvl(map.get("reciver_list").toString(), "") : ""; // added
																																	// Sungwoo
																																	// 2014-07-01

			Locale locale1 = new Locale(locale);

			HashMap<String, String> hm = new HashMap<String, String>();
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			MailVO mailVo = new MailVO();
			CompletionVO vo = new CompletionVO();
			// 메일정보 조회
			vo.setCnsdreq_id(cnsdreq_id);
			vo.setCntrt_id(cntrt_id);
			List<?> listCompleteMailInfo = commonDAO.list("clm.manage.detailCompleteStatusInformMail", vo);
			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			String moduleId = "LAS";
			String misId = EsbUtil.generateMisId("MAIL");
			
			setDefaultValueForMailVO(locale, mailVo, moduleId, misId);

//			mailVo.setModule_id(moduleId);
//			mailVo.setMis_id(misId);
//			mailVo.setMsg_key("11");

			// 보내는 사람
//			mailVo.setSender_single_id(propertyService.getProperty("clms.admin.id"));
//			mailVo.setSender_mail_addr(propertyService.getProperty("clms.admin.email"));

			// 로케일, 인코딩 설정, Time Zone
			// mailVo.setLocale((String)map.get("locale"));
//			mailVo.setLocale(locale);
//			mailVo.setEncoding("utf-8");
//			mailVo.setTime_zone("GMT+0");
//			mailVo.setIs_dst("false");

			mailLom = (ListOrderedMap) listCompleteMailInfo.get(0);
			cntrt_no = (String) mailLom.get("cntrt_no");
			req_title = (String) mailLom.get("req_title");
			blngt_orgnz_id = (String) mailLom.get("blngt_orgnz_id");
			compCd = (String) mailLom.get("comp_cd");

			// 준법지원자 사업부코드 설정
			vo.setBlngt_orgnz_id(blngt_orgnz_id);
			vo.setComp_cd(compCd);

			// 수신자리스트 조회
			List<?> receiverList = null;

			if (reciver_list.equals("review")) {
				receiverList = commonDAO.list("clm.manage.RequesterNReviewerList", vo);
			} else {
				receiverList = commonDAO.list("clm.manage.reqUserList", vo); // Sungwoo
																				// 2014-07-01
																				// 수신자
																				// 리스트
																				// 변경
																				// Requester
																				// 만
																				// 수신
			}
			if (receiverList != null && receiverList.size() > 0) {
				
				String subject=(String) messageSource.getMessage("clm.page.field.mailsend.subjectTitle", null, locale1);
				
				if(map.get("email.subject")!=null && map.get("email.subject").toString()!=""){
					mailVo.setSubject(map.get("email.subject").toString());
				}else{
					mailVo.setSubject(subject);
				}
				
				int receiver_cnt = receiverList.size();

				// 받는 사람
				String[] iseq_ids = null;
				String[] rec_types = null;
				String[] rec_addrs = null;

				iseq_ids = new String[receiver_cnt];
				rec_types = new String[receiver_cnt];
				rec_addrs = new String[receiver_cnt];

				for (int i = 0; i < receiverList.size(); i++) {
					receiverListLom = (ListOrderedMap) receiverList.get(i);
					cntrtRespId = (String) receiverListLom.get("USER_ID");

					// 상신자 정보 조회
					if (cntrtRespId != null && !"".equals(cntrtRespId)) {
						cntrtRespMailAddr = esbOrgService.getUserEpMailAddr(cntrtRespId);
					}

				
					// STATUS 값 설정 - Default "0"
					//mailVo.setStatus("1");

					iseq_ids[i] = "1";
					rec_types[i] = "t";
					rec_addrs[i] = cntrtRespMailAddr;
				
				}

				if (rec_addrs != null && rec_addrs.length>0) {
					
					hm.put("mail_type", "24");
					hm.put("main_title", (map.get("main_title")!=null && map.get("main_title").toString()!="") ? map.get("main_title").toString() : (String) messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, locale1));
					hm.put("main_link", propertyService.getProperty("secfw.url.domain") + "/login.do");
					hm.put("cntrt_no", cntrt_no);
					hm.put("param1", req_title);
					hm.put("param2", mail_content);

					contentHtml = this.getMailContent(hm);

					mailVo.setBhtml_content_check("true");
					mailVo.setIseq_ids(iseq_ids);
					mailVo.setRec_types(rec_types);
					mailVo.setRec_addrs(rec_addrs);
					mailVo.setBody(contentHtml.toString());
					
					
					/*********************************************************
					 * 메일 내역 등록
					 **********************************************************/
					esbMailService.insertMail(mailVo);

					/********************************************************
					 * 메일전송
					 **********************************************************/
				/*	try {
						esbMailService.sendMail(moduleId, misId);
					} catch (Exception e) {
						misId = EsbUtil.generateMisId("MAIL");
						mailVo.setMis_id(misId);
						esbMailService.insertAdminMail(mailVo);

						esbMailService.sendMail(moduleId, misId);

						misId = EsbUtil.generateMisId("MAIL");
						mailVo.setMis_id(misId);
						esbMailService.insertAdminMailSub(mailVo);
						esbMailService.sendMail(moduleId, misId);
					}*/
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 전자검토자 임시 담당자 지정 메일 전송
	 * 
	 * @param HashMap
	 *            map
	 * @return List
	 * @throws Exception
	 */
	public void sendElecRespmanMailSend(HashMap map) throws Exception {
		try {

			String org_resp_id = StringUtil.bvl((String) map.get("org_resp_id"), "");
			String org_resp_nm = StringUtil.bvl((String) map.get("org_resp_nm"), "");
			String tmp_resp_id = StringUtil.bvl((String) map.get("tmp_resp_id"), "");
			String sendType = StringUtil.bvl((String) map.get("sendType"), "");
			String locale = StringUtil.bvl((String) map.get("locale"), "en");
			String org_resp_mail = "";
			String tmp_resp_mail = "";
			String contentHtml = "";
			String mail_content = "";
			Locale locale1 = new Locale(locale);

			HashMap<String, String> hm = new HashMap<String, String>();
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			MailVO mailVo = new MailVO();
			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			String moduleId = "LAS";
			String misId = EsbUtil.generateMisId("MAIL");

			mailVo.setModule_id(moduleId);
			mailVo.setMis_id(misId);
			mailVo.setMsg_key("11");

			mailVo.setLocale(locale);
			mailVo.setEncoding("utf-8");
			mailVo.setTime_zone("GMT+0");
			mailVo.setIs_dst("false");

			// 보내는 사람
			mailVo.setSender_single_id(propertyService.getProperty("clms.admin.id"));
			mailVo.setSender_mail_addr(propertyService.getProperty("clms.admin.email"));

			// 받는 사람
			String[] iseq_ids = new String[2];
			String[] rec_types = new String[2];
			String[] rec_addrs = new String[2];

			if (org_resp_id != null && !"".equals(org_resp_id)) {
				org_resp_mail = esbOrgService.getUserEpMailAddr(org_resp_id);
			}

			if (tmp_resp_id != null && !"".equals(tmp_resp_id)) {
				tmp_resp_mail = esbOrgService.getUserEpMailAddr(tmp_resp_id);
			}

			if ("A".equals(sendType)) {// 지정
				mail_content = (String) messageSource.getMessage("las.page.field.changRespman015", null, locale1);
				;// 전자검토자 임시 담당자가 지정되었습니다.
			} else {// 원복
				mail_content = (String) messageSource.getMessage("las.page.field.changRespman016", null, locale1);
				;// 전자검토자 임시 담당자가 원복되었습니다.
			}
			mailVo.setSubject((String) messageSource.getMessage("clm.page.field.mailsend.subjectTitle", null, locale1));
			mailVo.setStatus("1");

			iseq_ids[0] = "1";
			rec_types[0] = "t";
			rec_addrs[0] = org_resp_mail;

			iseq_ids[1] = "1";
			rec_types[1] = "t";
			rec_addrs[1] = tmp_resp_mail;

			hm.put("mail_type", "24");
			hm.put("main_title", (String) messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, locale1));
			hm.put("main_link", propertyService.getProperty("secfw.url.domain") + "/login.do");
			hm.put("param1", org_resp_nm);// 원담당자
			hm.put("param2", mail_content);// 내용
			hm.put("last_locale", locale);
			contentHtml = this.getMailContent(hm);

			mailVo.setBhtml_content_check("true");
			mailVo.setIseq_ids(iseq_ids);
			mailVo.setRec_types(rec_types);
			mailVo.setRec_addrs(rec_addrs);
			mailVo.setBody(contentHtml.toString());

			if (!"".equals(org_resp_mail) && !"".equals(tmp_resp_mail)) {
				/*********************************************************
				 * 메일 내역 등록
				 **********************************************************/
				esbMailService.insertMail(mailVo);

				/********************************************************
				 * 메일전송
				 **********************************************************/
				try {
					esbMailService.sendMail(moduleId, misId);
				} catch (Exception e) {
					misId = EsbUtil.generateMisId("MAIL");
					mailVo.setMis_id(misId);
					esbMailService.insertAdminMail(mailVo);

					esbMailService.sendMail(moduleId, misId);

					misId = EsbUtil.generateMisId("MAIL");
					mailVo.setMis_id(misId);
					esbMailService.insertAdminMailSub(mailVo);
					esbMailService.sendMail(moduleId, misId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 접수 알림 메일 전송 (담당회사 법무그룹장에게 메일전송)
	 * 
	 * @param HashMap
	 *            map(mail contents)
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public void sendReqInfoMail(HashMap arg) throws Exception {
		try {
			ListOrderedMap mailLom = null;
			ListOrderedMap receiverListLom = null;

			String locale = StringUtil.bvl((String) arg.get("locale"), "en");
			Locale locale1 = new Locale(StringUtil.bvl((String) arg.get("locale"), "en"));

			String cnsdreq_id = StringUtil.bvl((String) arg.get("cnsdreq_id"), "");
			String cntrt_id = StringUtil.bvl((String) arg.get("cntrt_id"), "");
			String subject = StringUtil.bvl((String) arg.get("email.subject"), "");
			String mainTitle = StringUtil.bvl((String) arg.get("main_title"), (String) messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, locale1));
			String mail_content = StringUtil.bvl((String) arg.get("mail_content"), "");
			String reqman_info = StringUtil.bvl((String) arg.get("reqman_info"), "");
			String req_title = "";
			String cntrt_no = "";
			String cntrtRespId = "";
			String cntrtRespMailAddr = "";
			String compCd = "";

			HashMap<String, String> hm = new HashMap<String, String>();
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			MailVO mailVo = new MailVO();
			CompletionVO vo = new CompletionVO();
			// 메일정보 조회
			vo.setCnsdreq_id(cnsdreq_id);
			vo.setCntrt_id(cntrt_id);
			vo.setSession_user_locale(locale);
			List<?> listCompleteMailInfo = commonDAO.list("clm.manage.detailCompleteStatusInformMail", vo);
			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			String moduleId = "LAS";
			String misId = EsbUtil.generateMisId("MAIL");

			
			setDefaultValueForMailVO(locale, mailVo, moduleId, misId);
			
//			mailVo.setModule_id(moduleId);
//			mailVo.setMis_id(misId);
//			mailVo.setMsg_key("11");
//
//			// 보내는 사람
//			mailVo.setSender_single_id(propertyService.getProperty("clms.admin.id"));
//			mailVo.setSender_mail_addr(propertyService.getProperty("clms.admin.email"));
//
//			// 로케일, 인코딩 설정, Time Zone
//			// mailVo.setLocale((String)map.get("locale"));
//			mailVo.setLocale(locale);
//			mailVo.setEncoding("utf-8");
//			mailVo.setTime_zone("GMT+0");
//			mailVo.setIs_dst("false");

			mailLom = (ListOrderedMap) listCompleteMailInfo.get(0);
			cntrt_no = (String) mailLom.get("cntrt_no");
			req_title = (String) mailLom.get("req_title");
			compCd = (String) mailLom.get("comp_cd");

			// 인자로 넘어온 email subject가 있을경우 해당 subject 사용
			if (StringUtils.isNotEmpty(subject)) {
				mailVo.setSubject(subject);

			} else {
				mailVo.setSubject((String) messageSource.getMessage("clm.page.field.mailsend.subjectTitle", null, locale1));

			}

			vo.setComp_cd(compCd);

			// default email type
			hm.put("mail_type", "24");

			
			//submit_status
			
			// 수신자리스트 조회
			List<?> receiverList = null;
			if (arg.get("request").equals("Legal")) { // Legal Admin List
				hm.put("mail_type", "Legal");
				receiverList = commonDAO.list("clm.manage.LegalAdminList", vo);
			} else if (arg.get("request").equals("re_request") || arg.get("request").equals("review")) { // Reviewer
																											// List
				receiverList = commonDAO.list("clm.manage.listConsiderationReReqMailInfo", vo);
			} else { // Requester List
				receiverList = commonDAO.list("clm.manage.reqUserList", vo);
			}

			if (receiverList != null && !receiverList.isEmpty()) {

				int receiver_cnt = receiverList.size();

				// 받는 사람
				String[] iseq_ids = null;
				String[] rec_types = null;
				String[] rec_addrs = null;

				iseq_ids = new String[receiver_cnt];
				rec_types = new String[receiver_cnt];
				rec_addrs = new String[receiver_cnt];

				for (int i = 0; i < receiverList.size(); i++) {
					receiverListLom = (ListOrderedMap) receiverList.get(i);
					cntrtRespId = (String) receiverListLom.get("USER_ID");

					// 상신자 정보 조회
					if (StringUtils.isNotEmpty(cntrtRespId)) {
						cntrtRespMailAddr = (String) receiverListLom.get("email");
					}

					iseq_ids[i] = "1";
					rec_types[i] = "t";
					rec_addrs[i] = cntrtRespMailAddr;

				}
				hm.put("main_title", mainTitle);
				hm.put("main_link", propertyService.getProperty("secfw.url.domain") + "/login.do");
				hm.put("cntrt_no", cntrt_no);

				if (arg.get("request").equals("Legal")) {
					hm.put("param1", req_title);
					hm.put("param2", reqman_info);
					hm.put("param3", mail_content);
				} else {
					hm.put("param1", req_title);
					hm.put("param2", mail_content);
				}

				// STATUS 값 설정 - Default "0"
				mailVo.setStatus("0");

				mailVo.setBhtml_content_check("true");
				mailVo.setIseq_ids(iseq_ids);
				mailVo.setRec_types(rec_types);
				mailVo.setRec_addrs(rec_addrs);
				mailVo.setBody(this.getMailContent(hm));

				if (rec_addrs != null && rec_addrs.length > 0) {
					/*********************************************************
					 * 메일 내역 등록
					 **********************************************************/
					esbMailService.insertMail(mailVo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 검토발신 시 전자검토자에게 알림메일 전송
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	public void sendConsiderationReviewerMailSend(com.sec.clm.review.dto.ConsultationVO vo) throws Exception {
		try {
			List<?> listConsiderationReplyMailInfo = null;
			ListOrderedMap mailLom = null;
			ListOrderedMap respLom = null;
			String req_title = "";
			String contents = "";
			String comp_cd = "";
			String reviewer_id = "";
			String reviewer_mail = "";
			HashMap<String, String> hm = new HashMap<String, String>();

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			MailVO mailVo = new MailVO();
			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			// 모듈아이디 및 misId 세팅 -> (각 시스템/업무별로 개별 Key값을 입력)
			String moduleId = vo.getSys_cd();
			String misId = EsbUtil.generateMisId("MAIL");

			Locale locale1 = new Locale((String) vo.getSession_user_locale());

			// 메시지 처리 - 검토자로부터 위 건에 대하여 검토되었으나 미결 건이 있습니다.<br>법무시스템을 통하여 확인하시기
			// 바랍니다.
			contents = (String) messageSource.getMessage("las.msg.field.sendmail002", null, locale1);

			mailVo.setModule_id(moduleId);
			mailVo.setMis_id(misId);
			mailVo.setMsg_key("11");
			mailVo.setSubject((String) messageSource.getMessage("clm.page.field.mailsend.subjectTitle", null, locale1));
			// 보내는 사람
			mailVo.setSender_single_id(propertyService.getProperty("clms.admin.id"));
			mailVo.setSender_mail_addr(propertyService.getProperty("clms.admin.email"));

			// 로케일, 인코딩 설정, Time Zone
			mailVo.setLocale(vo.getSession_user_locale());
			mailVo.setEncoding("utf-8");
			mailVo.setTime_zone("GMT+0");
			mailVo.setIs_dst("false");

			// 메일정보 조회
			List<?> listCnsdreqMailInfo = commonDAO.list("clm.manage.listCnsdreqMailInfo", vo);
			mailLom = (ListOrderedMap) listCnsdreqMailInfo.get(0);
			req_title = (String) mailLom.get("req_title");
			comp_cd = (String) mailLom.get("comp_cd");

			vo.setComp_cd(comp_cd);

			// 메일내용 조회
			listConsiderationReplyMailInfo = commonDAO.list("clm.manage.listConsiderationReviewerInfo", vo);

			if (listConsiderationReplyMailInfo != null && listConsiderationReplyMailInfo.size() > 0) {

				int mail_cnt = listConsiderationReplyMailInfo.size();

				// 받는 사람
				String[] iseq_ids = null;
				String[] rec_types = null;
				String[] rec_addrs = null;

				iseq_ids = new String[mail_cnt];
				rec_types = new String[mail_cnt];
				rec_addrs = new String[mail_cnt];

				for (int i = 0; i < listConsiderationReplyMailInfo.size(); i++) {
					respLom = (ListOrderedMap) listConsiderationReplyMailInfo.get(i);
					reviewer_id = (String) respLom.get("user_id");

					// 상신자 정보 조회{EpId가 없는 경우는 관리자에게 메일 전송)
					if (reviewer_id != null && !"".equals(reviewer_id)) {
						reviewer_mail = esbOrgService.getUserEpMailAddr(reviewer_id);
					}

					// STATUS 값 설정 - Default "0"
					mailVo.setStatus("1");
					iseq_ids[i] = "1";
					rec_types[i] = "t";
					rec_addrs[i] = reviewer_mail;
					hm.put("mail_type", "24");
					hm.put("main_title", (String) messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, locale1));
					hm.put("main_link", propertyService.getProperty("secfw.url.domain") + "/login.do");
					hm.put("param1", req_title); // 의뢰명
					hm.put("param2", contents); // 내용

					String contentHtml = this.getMailContent(hm);

					mailVo.setBhtml_content_check("true");
					mailVo.setIseq_ids(iseq_ids);
					mailVo.setRec_types(rec_types);
					mailVo.setRec_addrs(rec_addrs);
					mailVo.setBody(contentHtml.toString());
				} // end for

				if (reviewer_mail != null && !"".equals(reviewer_mail)) {
					/*********************************************************
					 * 메일 내역 등록
					 **********************************************************/
					esbMailService.insertMail(mailVo);

					/*********************************************************
					 * 메일전송
					 **********************************************************/
					try {
						esbMailService.sendMail(moduleId, misId);
					} catch (Exception e) {
						misId = EsbUtil.generateMisId("MAIL");
						mailVo.setMis_id(misId);
						esbMailService.insertAdminMail(mailVo);

						esbMailService.sendMail(moduleId, misId);

						misId = EsbUtil.generateMisId("MAIL");
						mailVo.setMis_id(misId);
						esbMailService.insertAdminMailSub(mailVo);

						esbMailService.sendMail(moduleId, misId);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 법률자문/표준계약서 메일 컨텐츠 생성
	 * 
	 * @param HashMap
	 * @return String
	 * @throws Exception
	 */
	public String getLawConsultMailContent(HashMap hm) throws Exception {
		String content = "";

		// 부분별 내용
		StringBuffer topHtml = new StringBuffer();
		String contHtml = "";
		StringBuffer bottomHtml = new StringBuffer();

		// 파라미터 선언
		String mailType = (String) hm.get("mail_type");
		String mailTitle = (String) hm.get("main_title");
		String tdParam1 = (String) hm.get("param1");
		String tdParam2 = (String) hm.get("param2");
		String tdParam3 = StringUtil.bvl((String) hm.get("param3"), "");
		String tdParam4 = StringUtil.bvl((String) hm.get("param4"), "");
		String last_locale = StringUtil.bvl((String) hm.get("last_locale"), "en");
		Locale _locale = new Locale(last_locale);
		if (last_locale.length() > 2)
			last_locale = last_locale.substring(0, 2);

		/**
		 * 유형별 [법률자문/표준계약서] 담당자 지정 (의뢰인, 검토자) 01 [법률자문/표준계약서] 검토결과 회신 (의뢰인) 02
		 * [법률자문/표준계약서] 반려 (의뢰인) 03 [법률자문/표준계약서] 요청 접수 (그룹장) 04 [법률자문/표준계약서] 미결
		 * 건 알림 (전자검토자) 05
		 */

		contHtml += "<table class='list_basic mt20'>";
		contHtml += "<colgroup>";
		contHtml += "<col width='14%' />";
		contHtml += "<col width='36%'/>";
		contHtml += "<col width='14%' />";
		contHtml += "<col width='36%'/>";
		contHtml += "</colgroup>";

		// 의뢰명, 검토자, 의뢰일, 내용
		if ("01".equals(mailType)) {
			contHtml += "<tr><th>" + messageSource.getMessage("las.page.field.lawconsultImpl.mailcolumn01", null, _locale) + "</th><td colspan='3'>" + tdParam1 + "</td></tr>";
			contHtml += "<tr><th>" + messageSource.getMessage("las.page.field.lawconsultImpl.mailcolumn02", null, _locale) + "</th><td>" + tdParam2 + "</td><th>"
					+ messageSource.getMessage("las.page.field.lawconsultImpl.mailcolumn03", null, _locale) + "</th><td>" + tdParam3 + "</td></tr>";
			contHtml += "<tr class='end'><th>" + messageSource.getMessage("las.page.field.lawconsultImpl.mailcolumn05", null, _locale) + "</th><td colspan='3'>"
					+ messageSource.getMessage("las.page.field.lawconsultImpl.mailContent01", null, _locale) + "</td></tr>";
		}
		// 의뢰명, 검토자, 의뢰일, 회신일, 내용
		else if ("02".equals(mailType)) {
			contHtml += "<tr><th>" + messageSource.getMessage("las.page.field.lawconsultImpl.mailcolumn01", null, _locale) + "</th><td colspan='3'>" + tdParam1 + "</td></tr>";
			contHtml += "<tr><th>" + messageSource.getMessage("las.page.field.lawconsultImpl.mailcolumn02", null, _locale) + "</th><td colspan='3'>" + tdParam2 + "</td></tr>";
			contHtml += "<tr><th>" + messageSource.getMessage("las.page.field.lawconsultImpl.mailcolumn03", null, _locale) + "</th><td>" + tdParam3 + "</td><th>"
					+ messageSource.getMessage("las.page.field.lawconsultImpl.mailcolumn04", null, _locale) + "</th><td>" + tdParam4 + "</td></tr>";
			contHtml += "<tr class='end'><th>" + messageSource.getMessage("las.page.field.lawconsultImpl.mailcolumn05", null, _locale) + "</th><td colspan='3'>"
					+ messageSource.getMessage("las.page.field.lawconsultImpl.mailContent02", null, _locale) + "</td></tr>";
		}
		// 의뢰명, 의뢰자, 의뢰일, 반려사유, 내용
		else if ("03".equals(mailType)) {
			contHtml += "<tr><th>" + messageSource.getMessage("las.page.field.lawconsultImpl.mailcolumn01", null, _locale) + "</th><td colspan='3'>" + tdParam1 + "</td></tr>";
			contHtml += "<tr><th>" + messageSource.getMessage("las.page.field.lawconsultImpl.mailcolumn07", null, _locale) + "</th><td>" + tdParam2 + "</td><th>"
					+ messageSource.getMessage("las.page.field.lawconsultImpl.mailcolumn03", null, _locale) + "</th><td>" + tdParam3 + "</td></tr>";
			contHtml += "<tr><th>" + messageSource.getMessage("las.page.field.lawconsultImpl.mailcolumn06", null, _locale) + "</th><td colspan='3'>" + tdParam4 + "</td></tr>";
			contHtml += "<tr class='end'><th>" + messageSource.getMessage("las.page.field.lawconsultImpl.mailcolumn05", null, _locale) + "</th><td colspan='3'>"
					+ messageSource.getMessage("las.page.field.lawconsultImpl.mailContent03", null, _locale) + "</td></tr>";
		}
		// 의뢰명, 의뢰자, 의뢰일, 내용
		else if ("04".equals(mailType)) {
			contHtml += "<tr><th>" + messageSource.getMessage("las.page.field.lawconsultImpl.mailcolumn01", null, _locale) + "</th><td colspan='3'>" + tdParam1 + "</td></tr>";
			contHtml += "<tr><th>" + messageSource.getMessage("las.page.field.lawconsultImpl.mailcolumn07", null, _locale) + "</th><td>" + tdParam2 + "</td><th>"
					+ messageSource.getMessage("las.page.field.lawconsultImpl.mailcolumn03", null, _locale) + "</th><td>" + tdParam3 + "</td></tr>";
			contHtml += "<tr class='end'><th>" + messageSource.getMessage("las.page.field.lawconsultImpl.mailcolumn05", null, _locale) + "</th><td colspan='3'>"
					+ messageSource.getMessage("las.page.field.lawconsultImpl.mailContent04", null, _locale) + "</td></tr>";
		}
		// 의뢰명, 의뢰자, 의뢰일, 내용
		else if ("05".equals(mailType)) {
			contHtml += "<tr><th>" + messageSource.getMessage("las.page.field.lawconsultImpl.mailcolumn01", null, _locale) + "</th><td colspan='3'>" + tdParam1 + "</td></tr>";
			contHtml += "<tr><th>" + messageSource.getMessage("las.page.field.lawconsultImpl.mailcolumn07", null, _locale) + "</th><td>" + tdParam2 + "</td><th>"
					+ messageSource.getMessage("las.page.field.lawconsultImpl.mailcolumn03", null, _locale) + "</th><td>" + tdParam3 + "</td></tr>";
			contHtml += "<tr class='end'><th>" + messageSource.getMessage("las.page.field.lawconsultImpl.mailcolumn05", null, _locale) + "</th><td colspan='3'>"
					+ messageSource.getMessage("las.page.field.lawconsultImpl.mailContent05", null, _locale) + "</td></tr>";
		}

		contHtml += "</table>";

		String strUrl = "http://" + propertyService.getProperty("secfw.url.lasdomain");

		/** 기본구성 Start **/
		// 상단 구성
		topHtml.append("<!DOCTYPE html>");
		topHtml.append("<html>");
		topHtml.append("<head>");
		topHtml.append("<meta charset='utf-8' />");
	//	topHtml.append("<meta http-equiv='X-UA-Compatible' content='IE=8; IE=9' />");
		topHtml.append("<title>" + (String) messageSource.getMessage("clm.main.title", null, _locale) + "</title>");

		topHtml.append("<link href='" + strUrl + "/style/las/" + _locale + "/mail.css' type=\"text/css\" rel=\"stylesheet\">");
		topHtml.append("<link href='" + strUrl + "/style/las/" + _locale + "/las.css' type=\"text/css\" rel=\"stylesheet\">");
		topHtml.append("<script language=\"javascript\" src=\"" + strUrl + "/script/clms/common.js\" type=\"text/javascript\"></script>");
		// topHtml.append("<!--[if IE]> <script
		// src=\"http://html5shiv.googlecode.com/svn/trunk/html5.js\"></script>
		// <![endif]-->");
		topHtml.append("</head>");

		topHtml.append("<body>");
		topHtml.append("<div class=\"mailWrap\">");
		topHtml.append("<div class=\"mail_top\"></div>");
		topHtml.append("<div class=\"mail_mid\">");

		// 제목

		String pageLink = (String) messageSource.getMessage("las.mail.field.lawconsultImpl.sysLink", null, _locale);

		topHtml.append("<DIV class=page_list>");
		topHtml.append("<DIV class=in><span>" + mailTitle + "</span></DIV>");
		topHtml.append("</DIV>");

		String slasDomain = (String) propertyService.getProperty("secfw.url.domain");

		bottomHtml.append("<div class='tC mt20'>");
		bottomHtml.append("<span class=\"btn_mail_gosys\"><a href=\"" + slasDomain + "\" target=_blank>" + pageLink + "</a></span>");
		bottomHtml.append("</div>");

		bottomHtml.append("</div>");
		bottomHtml.append("<div class=\"mail_btm\"></div>");
		bottomHtml.append("</div>");
		bottomHtml.append("</body>");
		bottomHtml.append("</html>");

		/** 기본구성 End **/

		content = topHtml.toString() + contHtml + bottomHtml.toString();

		return content;
	}

	/**
	 * 결재상신 시 수신자리스트에 알림메일 전송
	 * 
	 * @param vo
	 *            MailVO
	 * @return
	 * @throws Exception
	 */
	public void sendApprovalMail(MailVO mailVo) throws Exception {

		try {
			com.sec.clm.review.dto.ConsultationVO searchVo = new com.sec.clm.review.dto.ConsultationVO();
			String cntrtRespMailAddr = "";
			ListOrderedMap respLom = null;

			String search_email_list = "''";

			// 보내는 사람
			mailVo.setSender_single_id(propertyService.getProperty("clms.admin.id"));
			mailVo.setSender_mail_addr(propertyService.getProperty("clms.admin.email"));

			// 로케일, 인코딩 설정, Time Zone
			mailVo.setEncoding("utf-8");
			mailVo.setTime_zone("GMT+0");
			mailVo.setIs_dst("false");

			List<?> listConsiderationApprovalMailInfo = null;

			for (int i = 0; i < mailVo.getRec_addrs().length; i++) {
				search_email_list += mailVo.getRec_addrs()[i];

				if (i < mailVo.getRec_addrs().length - 1) {
					search_email_list += "','";
				} else {
					search_email_list += "''";
				}
			}
			searchVo.setComp_cd(mailVo.getSession_auth_comp_cd().replace("'", ""));
			searchVo.setCntrt_oppnt_email(search_email_list);

			// 메일내용 조회(Legal admin 리스트 조회)
			listConsiderationApprovalMailInfo = commonDAO.list("clm.manage.listConsiderationRespLegalAdminMailInfo", searchVo);

			// 받는 사람
			String[] iseq_ids = null;
			String[] rec_types = null;
			String[] rec_addrs = null;
			if (listConsiderationApprovalMailInfo != null && listConsiderationApprovalMailInfo.size() > 0) {

				int mail_cnt = listConsiderationApprovalMailInfo.size();

				iseq_ids = new String[mail_cnt];
				rec_types = new String[mail_cnt];
				rec_addrs = new String[mail_cnt];

				// 2014-02-13 Kevin. 메일 발송을 배치를 통해 처리하기 위해 설정 값 변경.
				mailVo.setStatus("0");

				for (int i = 0; i < listConsiderationApprovalMailInfo.size(); i++) {
					respLom = (ListOrderedMap) listConsiderationApprovalMailInfo.get(i);
					cntrtRespMailAddr = (String) respLom.get("EMAIL");

					iseq_ids[i] = "1";
					rec_types[i] = "t";
					rec_addrs[i] = cntrtRespMailAddr;
				}
			}

			mailVo.setIseq_ids(iseq_ids);
			mailVo.setRec_types(rec_types);
			mailVo.setRec_addrs(rec_addrs);

			/*********************************************************
			 * 메일 내역 등록
			 **********************************************************/
			esbMailService.insertMail(mailVo);

			/*********************************************************
			 * 메일전송 2014-02-13 Kevin. 의뢰 회신 시 모든 내용 저장 후, 메일 발송은 배치를 통해 진행한다.
			 * 성능상의 이유로.
			 **********************************************************/
			/* esbMailService.sendMail(moduleId, misId); */
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 법무 검토회신 발신[일반/최종본]
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	public void sendMailSendModiLegalOpinion(com.sec.clm.review.dto.ConsultationVO vo) throws Exception {
		try {
			List<?> listConsiderationReplyMailInfo = null;
			ListOrderedMap mailLom = null;
			ListOrderedMap respLom = null;
			String cntrtReqId = "";
			String cntrtTitle = vo.getReq_title();
			String cntrtRespMailAddr = "";
			String cntrt_no = "";
			HashMap<String, String> hm = new HashMap<String, String>();

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			MailVO mailVo = new MailVO();
			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			// 모듈아이디 및 misId 세팅 -> (각 시스템/업무별로 개별 Key값을 입력)
			String moduleId = vo.getSys_cd();
			String misId = EsbUtil.generateMisId("MAIL");

			mailVo.setModule_id(moduleId);
			mailVo.setMis_id(misId);
			mailVo.setMsg_key("11");

			Locale locale1 = new Locale((String) vo.getSession_user_locale());

			// [SELMS+]계약검토 결과가 추가(변경)되었습니다.
			/*
			 * 내용 Reply to Contract Review has been added or modified. Check
			 * SELMS+ to see the reply. For any inquiry to the review opinion,
			 * select "Request Again." To conclude the contract, request
			 * Reviewer to "Final Review".
			 */
			mailVo.setSubject((String) messageSource.getMessage("clm.page.field.mailsend.subjectTitle", null, locale1));

			// 보내는 사람
			mailVo.setSender_single_id(propertyService.getProperty("clms.admin.id"));
			mailVo.setSender_mail_addr(propertyService.getProperty("clms.admin.email"));

			// 로케일, 인코딩 설정, Time Zone
			mailVo.setLocale(vo.getSession_user_locale());
			mailVo.setEncoding("utf-8");
			mailVo.setTime_zone("GMT+0");
			mailVo.setIs_dst("false");

			// 메일정보 조회
			List<?> listCnsdreqMailInfo = commonDAO.list("clm.manage.listCnsdreqMailInfo", vo);
			mailLom = (ListOrderedMap) listCnsdreqMailInfo.get(0);
			cntrt_no = (String) mailLom.get("cntrt_no");
			vo.setComp_cd((String) mailLom.get("comp_cd"));

			// 메일내용 조회
			listConsiderationReplyMailInfo = commonDAO.list("clm.manage.reqUserList", vo);

			if (listConsiderationReplyMailInfo != null && listConsiderationReplyMailInfo.size() > 0) {

				int mail_cnt = listConsiderationReplyMailInfo.size();

				// 받는 사람
				String[] iseq_ids = null;
				String[] rec_types = null;
				String[] rec_addrs = null;

				iseq_ids = new String[mail_cnt];
				rec_types = new String[mail_cnt];
				rec_addrs = new String[mail_cnt];

				for (int i = 0; i < listConsiderationReplyMailInfo.size(); i++) {

					respLom = (ListOrderedMap) listConsiderationReplyMailInfo.get(i);
					cntrtReqId = (String) respLom.get("USER_ID");

					// 상신자 정보 조회
					if (cntrtReqId != null && !"".equals(cntrtReqId)) {
						cntrtRespMailAddr = esbOrgService.getUserEpMailAddr(cntrtReqId);
					}

					// 2014-02-13 Kevin. 메일 발송을 배치를 통해 처리하기 위해 설정 값 변경.
					mailVo.setStatus("0");

					iseq_ids[i] = "1";
					rec_types[i] = "t";
					rec_addrs[i] = cntrtRespMailAddr;
					hm.put("mail_type", "24");
					hm.put("cntrt_no", cntrt_no);
					hm.put("main_title", (String) messageSource.getMessage("clm.page.field.mailsend.sendLegalOpinion", null, locale1));
					hm.put("main_link", propertyService.getProperty("secfw.url.domain") + "/login.do");
					hm.put("param1", cntrtTitle);
					hm.put("param2", (String) messageSource.getMessage("clm.page.field.mailsend.sendLegalOpinion01", null, locale1));

					String contentHtml = this.getMailContent(hm);
					mailVo.setBhtml_content_check("true");
					mailVo.setIseq_ids(iseq_ids);
					mailVo.setRec_types(rec_types);
					mailVo.setRec_addrs(rec_addrs);
					mailVo.setBody(contentHtml.toString());

				} // end for

				if (cntrtRespMailAddr != null && !"".equals(cntrtRespMailAddr)) {
					/*********************************************************
					 * 메일 내역 등록
					 **********************************************************/
					esbMailService.insertMail(mailVo);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * Send mail for email notification Sungwoo added 2014-06-17 email 공통 발송 기능
	 * 작성 Recipient : Requestor(req), Reviewer(rev), Legal Admin(leg) title 및
	 * description을 전달받아 Recipient 대상 내역에게 발송처리
	 * 
	 * @param vo
	 *            ConsultationVO
	 * @return
	 * @throws Exception
	 */
	public void sendHardCopyMail(HashMap map) throws Exception {
		try {
			ListOrderedMap receiverListLom = null;

			String mail_content = StringUtil.bvl((String) map.get("mail_content"), "");
			String req_title = "";
			String cntrt_no = "";
			String cntrtRespId = "";
			String cntrtRespMailAddr = "";
			String contentHtml = "";
			String compCd = "";
			String locale = StringUtil.bvl((String) map.get("locale"), "en");
			Locale locale1 = new Locale(locale);
			HashMap<String, String> hm = new HashMap<String, String>();
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			MailVO mailVo = new MailVO();
			CompletionVO vo = new CompletionVO();
			/*********************************************************
			 * 파라미터세팅
			 **********************************************************/
			String moduleId = "LAS";
			String misId = EsbUtil.generateMisId("MAIL");

			mailVo.setModule_id(moduleId);
			mailVo.setMis_id(misId);
			mailVo.setMsg_key("11");

			// 보내는 사람
			mailVo.setSender_single_id(propertyService.getProperty("clms.admin.id"));
			mailVo.setSender_mail_addr(propertyService.getProperty("clms.admin.email"));

			// 로케일, 인코딩 설정, Time Zone
			mailVo.setLocale(locale);
			mailVo.setEncoding("utf-8");
			mailVo.setTime_zone("GMT+0");
			mailVo.setIs_dst("false");

			cntrt_no = (String) map.get("cntrt_no");
			req_title = (String) map.get("req_title");
			compCd = (String) map.get("comp_cd");

			mailVo.setSubject((String) messageSource.getMessage("clm.page.field.mailsend.subjectTitle", null, locale1));

			vo.setComp_cd(compCd);

			// 수신자리스트 조회
			// 2014-02-12 Kevin. 수신자 리스트 조회 시 메일 주소까지 같이 조회함. 아래 루프에서는 메일주소
			// 조회때문에 쿼리를 반복할 필요가 없음.
			List<?> receiverList = null;
			receiverList = commonDAO.list("clm.manage.listConsiderationReReqMailInfo", vo);
			if (receiverList != null && receiverList.size() > 0) {
				int receiver_cnt = receiverList.size();

				// 받는 사람
				String[] iseq_ids = null;
				String[] rec_types = null;
				String[] rec_addrs = null;

				iseq_ids = new String[receiver_cnt];
				rec_types = new String[receiver_cnt];
				rec_addrs = new String[receiver_cnt];

				for (int i = 0; i < receiverList.size(); i++) {
					receiverListLom = (ListOrderedMap) receiverList.get(i);
					cntrtRespId = (String) receiverListLom.get("USER_ID");

					// 상신자 정보 조회
					if (cntrtRespId != null && !"".equals(cntrtRespId)) {
						cntrtRespMailAddr = (String) receiverListLom.get("email");
					}

					mailVo.setSubject((String) messageSource.getMessage("clm.page.field.mailsend.subjectTitle", null, locale1));
					// 2014-02-12 Kevin. 메일 발송을 배치로 돌리기 위해 상태값 변경.
					mailVo.setStatus("0");

					iseq_ids[i] = "1";
					rec_types[i] = "t";
					rec_addrs[i] = cntrtRespMailAddr;

					hm.put("mail_type", "24");
					hm.put("main_title", (String) messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, locale1));
					hm.put("main_link", propertyService.getProperty("secfw.url.domain") + "/login.do");
					hm.put("cntrt_no", cntrt_no);
					hm.put("param1", req_title);
					hm.put("param2", mail_content);

					contentHtml = this.getMailContent(hm);

					mailVo.setBhtml_content_check("true");
					mailVo.setIseq_ids(iseq_ids);
					mailVo.setRec_types(rec_types);
					mailVo.setRec_addrs(rec_addrs);
					mailVo.setBody(contentHtml.toString());
				}

				if (cntrtRespMailAddr != null && !"".equals(cntrtRespMailAddr)) {
					/*********************************************************
					 * 메일 내역 등록
					 **********************************************************/
					esbMailService.insertMail(mailVo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	public Log getLogger() {
		return LogFactory.getLog(this.getClass());
	}
	
	/* FERNANDO (MFA project) Jan/2024 - start */
	public void sendSimpleEmail_Esb(Map params) throws Exception
	{
		//String email_destination = params.getOrDefault("destination", propertyService.getProperty("clms.admin.email")).toString();
		//String email_subject 	 = params.getOrDefault("subject", "SELMS+ notification").toString();
		//String email_body        = params.getOrDefault("body", "").toString();
		
		String email_destination = "";
		if (params.get("destination") != null) {
			email_destination = params.get("destination").toString();
		} else {
			if (propertyService.getProperty("clms.admin.email") != null) {
				email_destination = propertyService.getProperty("clms.admin.email").toString();
			} else {
				email_destination = "fernando.c@partner.samsung.com";
			}
		}
		
		String email_subject = "";
		if (params.get("subject") != null) {
			email_subject = params.get("subject").toString();
		} else {
			email_subject = "SELMS+ notification";
		}
		
		String email_body = "";
		if (params.get("body") != null) {
			email_body = params.get("body").toString();
		} else {
			email_body = "SELMS+ notification";
		}
		
		String locale = "en";
		String moduleId = "LAS";
		String misId = EsbUtil.generateMisId("MAIL");

		MailVO mailVo = new MailVO();

		mailVo.setModule_id(moduleId);
		mailVo.setMis_id(misId);
		mailVo.setMsg_key("11");

		mailVo.setLocale(locale);
		mailVo.setEncoding("utf-8");
		mailVo.setTime_zone("GMT+0");
		mailVo.setIs_dst("false");
		/*String summerTimeYn = (String) session.getAttribute("EP_SUMMERTIMEYN");
		if ("Y".equals(summerTimeYn)) {
			mailVo.setIs_dst("true");
		} else {
			mailVo.setIs_dst("false");
		}*/

		// 보내는 사람
		mailVo.setSender_single_id(propertyService.getProperty("clms.admin.id"));
		mailVo.setSender_mail_addr(propertyService.getProperty("clms.admin.email"));

		// 받는 사람
		String[] iseq_ids = new String[1];
		String[] rec_types = new String[1];
		String[] rec_addrs = new String[1];

		mailVo.setSubject(email_subject);
		mailVo.setStatus("0");

		iseq_ids[0] = "1";
		rec_types[0] = "t";
		rec_addrs[0] = email_destination;
		
		mailVo.setBhtml_content_check("true");
		mailVo.setIseq_ids(iseq_ids);
		mailVo.setRec_types(rec_types);
		mailVo.setRec_addrs(rec_addrs);
		mailVo.setBody(email_body);

		mailVo.setFileInfos("otp_check");
		
		esbMailService.insertMail(mailVo);

		try {
			esbMailService.sendMail(moduleId, misId);
		} catch (Exception e) {
			e.printStackTrace();
			//throw e;
		}
	}
	
	public void sendSimpleEmail(Map params) throws Exception
	{
		String  host = "smtp.w1.samsung.net";
		Integer port = 25;
		String  mail_user = "fernando.c@partner.samsung.com"; // "C10ML0766";
		String  mail_pwd_1 = "bandit#600"; // "C10ML0766289635";
		String  mail_pwd_2 = "bandit#1200"; // "C10ML0766289635";
		
		String from = "selmsplus@samsung.com"; // propertyService.getProperty("clms.admin.email")
		
		//String email_destination = params.getOrDefault("destination", propertyService.getProperty("clms.admin.email")).toString();
		//String email_subject = params.getOrDefault("subject", "SELMS+ notification").toString();
		//String email_body = params.getOrDefault("body", "").toString();
		
		String email_destination = "";
		if (params.get("destination") != null) {
			email_destination = params.get("destination").toString();
		} else {
			if (propertyService.getProperty("clms.admin.email") != null) {
				email_destination = propertyService.getProperty("clms.admin.email").toString();
			} else {
				email_destination = "fernando.c@partner.samsung.com";
			}
		}
		
		String email_subject = "";
		if (params.get("subject") != null) {
			email_subject = params.get("subject").toString();
		} else {
			email_subject = "SELMS+ notification";
		}
		
		String email_body = "";
		if (params.get("body") != null) {
			email_body = params.get("body").toString();
		} else {
			email_body = "SELMS+ notification";
		}
		
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", port.toString());
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", true);
		//Authenticator auth = new SMTPAuthenticator();
		Session session = Session.getDefaultInstance(properties);
	    
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(email_destination));
		message.setSubject(email_subject);
		message.setText(email_body);
		
        Transport transport = session.getTransport("smtp");
        
		try {
			//Transport.send(message);
	        transport.connect(host, port.intValue(), mail_user, mail_pwd_1);
			//transport.send(message);
	        transport.sendMessage(message, message.getAllRecipients());
		} catch (Exception e1) {
			try {
				//Transport.send(message);
		        transport.connect(host, port.intValue(), mail_user, mail_pwd_2);
				//transport.send(message);
		        transport.sendMessage(message, message.getAllRecipients());
			} catch (Exception e2) {
				e2.printStackTrace();
				//throw e;
			}		
		}		
        
        transport.close();
	}
	/* FERNANDO (MFA project) Jan/2024 - end */

}