/**
 * Project Name : 법무시스템 이식
 * File Name : LawConsultController.java
 * Description : 법률자문 서비스 구현(Impl)
 * Author : 김현구
 * Date : 2011. 11. 1
 * Copyright : 
 */

package com.sec.las.lawconsulting.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.EsbUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.singleIF.dto.MailVO;
import com.sds.secframework.singleIF.service.EsbMailService;
import com.sds.secframework.singleIF.service.EsbOrgService;
import com.sec.clm.manage.service.MailSendService;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import com.sec.las.lawconsulting.dto.LawConsultVO;
import com.sec.las.lawconsulting.service.CounselHttpInvokerClientService;
import com.sec.las.lawconsulting.service.LawConsultService;

/**
 * Description : 법률자문 Service 구현체(impl) Author : 김현구 Date : 2011. 11. 1
 */
public class LawConsultServiceImpl extends CommonServiceImpl implements LawConsultService {

	/**
	 * 싱글 메일 서비스
	 */
	private EsbMailService esbMailService = null;

	/**
	 * 싱글 메일 서비스 세팅
	 * 
	 * @param esbMailService
	 */
	public void setEsbMailService(EsbMailService esbMailService) {
		this.esbMailService = esbMailService;
	}

	private MailSendService mailSendService = null;

	public void setMailSendService(MailSendService mailSendService) {
		this.mailSendService = mailSendService;
	}

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private EsbOrgService esbOrgService;
	public void setEsbOrgService(EsbOrgService esbOrgService) {
		this.esbOrgService = esbOrgService;
	}
	
	/**
	 * 법률자문 IF 서비스 호출을 위한 선언 및 세팅
	 */
	@SuppressWarnings("unused")
	private CounselHttpInvokerClientService counselHttpInvokerClientService;

	public void setCounselHttpInvokerClientService(CounselHttpInvokerClientService counselHttpInvokerClientService) {
		this.counselHttpInvokerClientService = counselHttpInvokerClientService;
	}

	/**
	 * List 출력
	 */
	@SuppressWarnings("rawtypes")
	public List listLawConsult(LawConsultVO vo) throws Exception {

		return commonDAO.list("las.lawconsult.list", vo);
	}

	/**
	 * 삽입 삽입하기 전 등록자 정보 세팅 및 XSS처리
	 */
	public int insertLawConsult(HttpServletRequest request, LawConsultVO vo) throws Exception {

		int result = 0;

		try {

			// 첫 의뢰 시 primary key(CNSLT_NO) 세팅
			if (vo.getCnslt_no() == null || "".equals(vo.getCnslt_no())) {
				String PK = getPrimaryKey(vo);
				vo.setCnslt_no(PK);
			}

			// 첫 의뢰가 아닐 시
			else {
				setCnslt(vo);
			}

			// XSS 처리
			vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
			vo.setCont(vo.getBody_mime());

			// 임시저장 or 의뢰 or 재의뢰 시
			if ("S01".equals(vo.getPrgrs_status()) || "S02".equals(vo.getPrgrs_status()) || "S04".equals(vo.getPrgrs_status())) {
				/*************************************************
				 * 첨부파일 저장
				 *************************************************/
				ClmsFileVO fvo = new ClmsFileVO();

				fvo.setSys_cd(vo.getSys_cd());
				fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
				fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
				fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
				fvo.setRef_key(vo.getCnslt_no() + "@" + Integer.toString(vo.getHstry_no()));
				fvo.setFileInfos(vo.getFileInfos());
				fvo.setReg_id(vo.getSession_user_id());
				clmsFileService.mngClmsAttachFile(fvo);
			}

			result = commonDAO.insert("las.lawconsult.insert", vo);

			modifyStatusLawConsult(request, vo);

			// 자문 참조자 insert
			if (vo.getArr_trgtman_id() != null) {

				commonDAO.insert("las.lawconsult.deleteContractAuthreq", vo);

				String[] arrTmpId = vo.getArr_trgtman_id();
				String[] arrTmpNm = vo.getArr_trgtman_nm();
				String[] arrTmpDeptNm = vo.getArr_trgtman_dept_nm();
				String[] arrTmpJikgupNm = vo.getArr_trgtman_jikgup_nm();

				for (int j = 0; j < vo.getArr_trgtman_id().length; j++) {

					vo.setTrgtman_id(arrTmpId[j]); //
					vo.setTrgtman_nm(arrTmpNm[j]);
					vo.setTrgtman_dept_nm(arrTmpDeptNm[j]);
					vo.setTrgtman_jikgup_nm(arrTmpJikgupNm[j]);

					commonDAO.insert("las.lawconsult.insertContractAuthreq", vo);
				}
			}

			// 자문유형 insert
			if (result == 1) {
				for (int i = 0; i < vo.getConsult_type_list().size(); i++) {
					vo.setConsult_type((String) vo.getConsult_type_list().get(i));
					commonDAO.insert("las.lawconsult.insertConsultType", vo);
				}
			}

			/********************************************************************************************
			 ** 메일 발송
			 * 
			 ** 최초 의뢰시 / 재의뢰시에만 발송 (반려 , 회신시 다른 로직에서 발송함) - 의뢰자에게 발송 -
			 ********************************************************************************************/

			// 의뢰 / 재의뢰시에 발송
			if (vo.getPrgrs_status().equals("S02") || vo.getPrgrs_status().equals("S04")) {

				String main_title = "";
				List<?> emailList = null;
				ListOrderedMap emailLom = new ListOrderedMap();
				ListOrderedMap lom = new ListOrderedMap();

				emailList = findUserEmail(vo);

				if (emailList.size() > 0) {
					emailLom = (ListOrderedMap) emailList.get(0);
					vo.setEmail((String) emailLom.get("email"));
				}				
				
				// 메일 발송에 필요한 정보
				lom = detailLawConsult(vo);
	
				// Set title
				if ("Y".equals(vo.getIsStdCont())) {
					main_title = messageSource.getMessage("las.mail.field.lawconsultImpl.mailTitle02", null, new RequestContext(request).getLocale());
				} else {
					main_title = messageSource.getMessage("las.mail.field.lawconsultImpl.mailTitle01", null, new RequestContext(request).getLocale());
				}

				// Getting Email Details. By JOON
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
				mailVo.setSubject(main_title);

				// 보내는 사람
				mailVo.setSender_single_id(propertyService.getProperty("clms.admin.id"));
				mailVo.setSender_mail_addr(propertyService.getProperty("clms.admin.email"));

				// 로케일, 인코딩 설정, Time Zone
				mailVo.setLocale(vo.getSession_user_locale());
				mailVo.setEncoding("utf-8");
				mailVo.setTime_zone("GMT+0");
				HttpSession session = request.getSession(false);
				String summerTimeYn = (String) session.getAttribute("EP_SUMMERTIMEYN");
				if ("Y".equals(summerTimeYn)) {
					mailVo.setIs_dst("true");
				} else {
					mailVo.setIs_dst("false");
				}

				// Email to Legal Admin. By JOON
				vo.setComp_cd((String) session.getAttribute("secfw.session.comp_cd"));
				List legalAdmins = getLegalAdminEmailAccounts(vo);
				
				int mail_cnt = legalAdmins.size();

				// 받는 사람
				String[] iseq_ids = null;
				String[] rec_types = null;
				String[] rec_addrs = null;

				iseq_ids = new String[mail_cnt];
				rec_types = new String[mail_cnt];
				rec_addrs = new String[mail_cnt];

				for (int i = 0; i < mail_cnt; i++) {

					mailVo.setStatus("0");
					iseq_ids[i] = "1";
					rec_types[i] = "t";
					String emailTo = (String) ((Map) legalAdmins.get(i)).get("email");
					rec_addrs[i] = (emailTo == null || emailTo == "") ? propertyService.getProperty("clms.admin.testEmail") : (String) ((Map) legalAdmins.get(i)).get("email");

					
				}
				
				if(mail_cnt>0){
					HashMap<String, String> hmap = new HashMap<String, String>();
					hmap.put("mail_type", "04");
					hmap.put("main_title", main_title);
					hmap.put("param1", (String) lom.get("title"));
					hmap.put("param2", (String) lom.get("reg_nm"));
					hmap.put("main_link", propertyService.getProperty("secfw.url.domain") + "/login.do");

					// timestamp를 string으로 바꾸기 위한 포맷
					SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
					hmap.put("param3", String.valueOf(lom.get("reg_dt")));

					String contentHtml = mailSendService.getLawConsultMailContent(hmap);

					mailVo.setBhtml_content_check("true");
					mailVo.setIseq_ids(iseq_ids);
					mailVo.setRec_types(rec_types);
					mailVo.setRec_addrs(rec_addrs);
					mailVo.setBody(contentHtml);

					/*********************************************************
					 * 메일 내역 등록
					 **********************************************************/
					esbMailService.insertMail(mailVo);
					/*********************************************************
					 * 메일전송
					 **********************************************************/
				//	esbMailService.sendMail(mailVo.getModule_id(), mailVo.getMis_id());
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 검토의뢰
	 */
	public int insertReviewLawConsult(HttpServletRequest request, LawConsultVO vo) throws Exception {
		int result = 0;
		// XSS 처리
		vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));

		// 2014-02-25 Kevin Added
		// 자문 CC insert
		if (vo.getArr_trgtman_id() != null && vo.getArr_trgtman_id().length > 0) {

			commonDAO.insert("las.lawconsult.deleteContractAuthreq", vo);

			String[] arrTmpId = vo.getArr_trgtman_id();
			String[] arrTmpNm = vo.getArr_trgtman_nm();
			String[] arrTmpDeptNm = vo.getArr_trgtman_dept_nm();
			String[] arrTmpJikgupNm = vo.getArr_trgtman_jikgup_nm();

			for (int j = 0; j < vo.getArr_trgtman_id().length; j++) {

				vo.setTrgtman_id(arrTmpId[j]);
				vo.setTrgtman_nm(arrTmpNm[j]);
				vo.setTrgtman_dept_nm(arrTmpDeptNm[j]);
				vo.setTrgtman_jikgup_nm(arrTmpJikgupNm[j]);

				commonDAO.insert("las.lawconsult.insertContractAuthreq", vo);
			}
		}

		// 1. 현재 건의 상태가 임시저장(S09) 이면 검토관련내용 update, 아니면 insert.
		// 2. 미결 & 담당자일시 update.
		if ("S09".equals(vo.getNow_intnl_prgrs_status()) || ("S08".equals(vo.getNow_intnl_prgrs_status()) && "N".equals(vo.getIsGrpmgr()))) {
			result = commonDAO.insert("las.lawconsult.insertReview2", vo);
		} else {
			// Primary Key 세팅
			setCnslt(vo);
			// review 관련내용 update
			result = commonDAO.insert("las.lawconsult.insertReview", vo);

			// 자문유형 insert
			if (result == 1) {
				for (int i = 0; i < vo.getConsult_type_list().size(); i++) {
					vo.setConsult_type((String) vo.getConsult_type_list().get(i));
					commonDAO.insert("las.lawconsult.insertConsultType", vo);
				}
			}
		}

		if (result == 1) {

			// 회신(S03), 임시저장(법무팀, s09) 경우
			if (vo.getPrgrs_status().equals("S09") || vo.getPrgrs_status().equals("S03")) {

				// 그룹장이 회신했을 시
				if (vo.getIsGrpmgr().equals("Y")) {

					// 그룹장이 담당자에 포함되어있는지 조회
					List list = listLawConsultRespman2(vo);

					// 담당자에 없으면 담당자에 포함
					if (list.size() == 0) {
						vo.setRespman_id(vo.getSession_user_id());
						insertRespmanLawConsult(vo);
					}
				}

				/*
				 * 상태가 회신일 시 ********************************************
				 * 1.ENTNL_STATUS 변경 (의뢰인 상태 회신으로 변경) 2. 회신일자 업데이트 3. 회신 메일 발송
				 * *************************************************************
				 */
				if ("S03".equals(vo.getPrgrs_status())) {
					// EXTNL_STATUS 변경
					modifyExtnlStatusLawConsult(vo);
					// 회신일자 반영
					commonDAO.modify("las.lawconsult.modifyReDt", vo);

					List<?> emailList = null;
					ListOrderedMap emailLom = new ListOrderedMap();
					ListOrderedMap lom = new ListOrderedMap();

					// 메일발송을 위해 의뢰인의 email정보를 빼온다
					String main_title = "";
					String requesterEmail = null;
					
					emailList = findUserEmail(vo);

					if (emailList.size() > 0) {
						emailLom = (ListOrderedMap) emailList.get(0);
						requesterEmail = (String) emailLom.get("email");
						//vo.setEmail((String) emailLom.get("email"));
					}

					// 메일 발송에 필요한 정보
					lom = detailLawConsult(vo);		

					// Set title
					if ("Y".equals(vo.getIsStdCont())) {
						main_title = messageSource.getMessage("las.mail.field.lawconsultImpl.mailTitle04", null, new RequestContext(request).getLocale());
					} else {
						main_title = messageSource.getMessage("las.mail.field.lawconsultImpl.mailTitle03", null, new RequestContext(request).getLocale());
					}

					HashMap<String, String> hmap = new HashMap<String, String>();
					hmap.put("mail_type", "02");
					hmap.put("main_title", main_title);
					hmap.put("param1", (String) lom.get("title"));
					hmap.put("param2", (String) lom.get("respman_nm"));

					// timestamp를 string으로 바꾸기 위한 포맷
					SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
					hmap.put("param3", String.valueOf(lom.get("reg_dt")));
					hmap.put("param4", date.format(lom.get("re_dt")));

					// Send Reviewers & Legal Admins. By JOON
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
					mailVo.setSubject(main_title);

					// 보내는 사람
					mailVo.setSender_single_id(propertyService.getProperty("clms.admin.id"));
					mailVo.setSender_mail_addr(propertyService.getProperty("clms.admin.email"));

					// 로케일, 인코딩 설정, Time Zone
					mailVo.setLocale(vo.getSession_user_locale());
					mailVo.setEncoding("utf-8");
					mailVo.setTime_zone("GMT+0");
					HttpSession session = request.getSession(false);
					String summerTimeYn = (String) session.getAttribute("EP_SUMMERTIMEYN");
					if ("Y".equals(summerTimeYn)) {
						mailVo.setIs_dst("true");
					} else {
						mailVo.setIs_dst("false");
					}
					// default setting end					
					
					// Email to Reviewers and Legal Admins
					vo.setComp_cd((String) session.getAttribute("secfw.session.comp_cd"));
					List emailTos = listReviewerAndLegalAdmin(vo);
					
					HashMap<String, String> addMap = new HashMap<String, String>();
					addMap.put("email", requesterEmail); 
					emailTos.add(addMap);// add Requester to the emailList
					
					int mail_cnt = emailTos.size();

					// 받는 사람
					String[] iseq_ids = null;
					String[] rec_types = null;
					String[] rec_addrs = null;

					iseq_ids = new String[mail_cnt];
					rec_types = new String[mail_cnt];
					rec_addrs = new String[mail_cnt];
					
					for (int i = 0; i < emailTos.size(); i++) {
						mailVo.setStatus("0");
						iseq_ids[i] = "1";
						rec_types[i] = "t";
						String emailTo = (String) ((Map) emailTos.get(i)).get("email");
						rec_addrs[i] = (emailTo == null || emailTo == "") ? propertyService.getProperty("clms.admin.testEmail") : (String) ((Map) emailTos.get(i)).get("email");
						
						
					}
					
					if(rec_addrs.length>0){
						String contentHtml = mailSendService.getLawConsultMailContent(hmap);

						mailVo.setBhtml_content_check("true");
						mailVo.setIseq_ids(iseq_ids);
						mailVo.setRec_types(rec_types);
						mailVo.setRec_addrs(rec_addrs);
						mailVo.setBody(contentHtml);
						/*********************************************************
						 * 메일 내역 등록
						 **********************************************************/
						esbMailService.insertMail(mailVo);
						/*********************************************************
						 * 메일전송
						 **********************************************************/
					//	esbMailService.sendMail(mailVo.getModule_id(), mailVo.getMis_id());
					}

				

				}

			}

		}

		/*************************************************
		 * 첨부파일 저장
		 *************************************************/
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(vo.getCnslt_no() + "@" + Integer.toString(vo.getHstry_no()));
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());

		clmsFileService.mngClmsAttachFile(fvo);

		return result;
	}
	/**
	 * 당당자 지정
	 */

	public int insertRespmanLawConsult(HttpServletRequest request, LawConsultVO vo) throws Exception {
		int result = 0;

		// 그룹장 상세화면에서 담당자 지정시
		if (vo.getPrgrs_status().equals("S02") || vo.getPrgrs_status().equals("S04")) {
			// 기존담당자 삭제
			deleteRespmanLawConsult(vo);

			ListOrderedMap respmanLom = new ListOrderedMap();
			ListOrderedMap regmanLom = new ListOrderedMap();
			List respmanList = null;
			List regmanList = null;
			String respman_nm = "";
			String main_title = "";
			String assignedAdviserEmail = null;

			// Set title
			if ("Y".equals(vo.getIsStdCont())) {
				main_title = messageSource.getMessage("las.mail.field.lawconsultImpl.mailTitle02", null, new RequestContext(request).getLocale());
			} else {
				main_title = messageSource.getMessage("las.mail.field.lawconsultImpl.mailTitle01_ASSIGN", null, new RequestContext(request).getLocale());
			}

			if (vo.getRespman_list() != null) {

				for (int i = 0; i < vo.getRespman_list().length; i++) {
					vo.setRespman_id(vo.getRespman_list()[i]);

					// 담당자 추가
					result = commonDAO.insert("las.lawconsult.insertRespman", vo);

					// 담당자에게 메일발송을 위해 해당 담당자의 email정보를 빼온다
					respmanList = listLawConsultRespmanOne(vo);

					respmanLom = (ListOrderedMap) respmanList.get(0);
					assignedAdviserEmail = (String) respmanLom.get("email"); // get assigned Adviser email account
					respman_nm = (String) respmanLom.get("respman_nm");
							
				}

				// 의뢰인에게 담당자 지정 메일을 발송 처리하는 부분
				String requesterEmail = null;//
				regmanList = findUserEmail(vo);

				if (regmanList.size() > 0) {
					regmanLom = (ListOrderedMap) regmanList.get(0);
					requesterEmail = (String) regmanLom.get("email"); //// get requester email account
				}				
								
				// Email Contents
				HashMap<String, String> hmap = new HashMap<String, String>();
				hmap.put("mail_type", "01");
				hmap.put("main_title", main_title);
				hmap.put("param1", vo.getTitle());
				hmap.put("param2", respman_nm);
				hmap.put("param3", vo.getReg_dt());

				// Send Legal Admins. By JOON
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
				mailVo.setSubject(main_title);

				// 보내는 사람
				mailVo.setSender_single_id(propertyService.getProperty("clms.admin.id"));
				mailVo.setSender_mail_addr(propertyService.getProperty("clms.admin.email"));

				// 로케일, 인코딩 설정, Time Zone
				mailVo.setLocale(vo.getSession_user_locale());
				mailVo.setEncoding("utf-8");
				mailVo.setTime_zone("GMT+0");
				HttpSession session = request.getSession(false);
				String summerTimeYn = (String) session.getAttribute("EP_SUMMERTIMEYN");
				if ("Y".equals(summerTimeYn)) {
					mailVo.setIs_dst("true");
				} else {
					mailVo.setIs_dst("false");
				}
				// default setting end		
								
				vo.setComp_cd((String) session.getAttribute("secfw.session.comp_cd"));
				int mail_cnt = 2;

				// 받는 사람
				String[] iseq_ids = null;
				String[] rec_types = null;
				String[] rec_addrs = null;

				iseq_ids = new String[mail_cnt];
				rec_types = new String[mail_cnt];
				rec_addrs = new String[mail_cnt];
				
				// add requesterEmail
				int j = 0;
				iseq_ids[j] = "1";
				rec_types[j] = "t";				
				rec_addrs[j] = (requesterEmail == null || requesterEmail == "") ? propertyService.getProperty("clms.admin.testEmail") : requesterEmail;					

				// add assignedAdviserEmail
				j=j+1;
				iseq_ids[j] = "1";
				rec_types[j] = "t";				
				rec_addrs[j] = (assignedAdviserEmail == null || assignedAdviserEmail == "") ? propertyService.getProperty("clms.admin.testEmail") : assignedAdviserEmail;					
				
				String contentHtml = mailSendService.getLawConsultMailContent(hmap);
				mailVo.setStatus("0");
				mailVo.setBhtml_content_check("true");
				mailVo.setIseq_ids(iseq_ids);
				mailVo.setRec_types(rec_types);
				mailVo.setRec_addrs(rec_addrs);
				mailVo.setBody(contentHtml);				

				/*********************************************************
				 * 메일 내역 등록
				 **********************************************************/
				esbMailService.insertMail(mailVo);
				/*********************************************************
				 * 메일전송
				 **********************************************************/
		//		esbMailService.sendMail(mailVo.getModule_id(), mailVo.getMis_id());				

			} else
				result = 1;

			// 메모 및 회신전결제확인 필드 내용 업데이트
			modifyGrpmgrDetailLawConsult(vo);
		}
		// 검토회신에서 그룹장 회신 시
		else {
			// 그룹장 회신이므로 그룹장을 담당자에 추가
			result = commonDAO.insert("las.lawconsult.insertRespman", vo);
		}
		return result;
	}

	/**
	 * 수정
	 */
	public int modifyLawConsult(LawConsultVO vo) throws Exception {
		int result = 0;

		// 수정자 정보 세션에서 받아와 vo에 세팅
		vo.setMod_nm(vo.getSession_user_nm());
		vo.setMod_id(vo.getSession_user_id());

		// XSS 처리
		vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));

		/*************************************************
		 * 첨부파일 저장
		 *************************************************/
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(vo.getCnslt_no() + "@" + Integer.toString(vo.getHstry_no()));
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());
		// 표준계약서일 경우에만 첨부파일이 하나이므로 clmsFileService.mngClmsAttachFile 에서 기존 첨부파일
		// 삭제로직 추가를 위해
		if ("A20".equals(vo.getConsult_type())) {
			fvo.setMultiYn("N");
		}
		clmsFileService.mngClmsAttachFile(fvo);

		// 내용
		vo.setCont(vo.getBody_mime());

		// 자문 참조자 insert
		if (vo.getArr_trgtman_id() != null) {

			commonDAO.insert("las.lawconsult.deleteContractAuthreq", vo);

			String[] arrTmpId = vo.getArr_trgtman_id();
			String[] arrTmpNm = vo.getArr_trgtman_nm();
			String[] arrTmpDeptNm = vo.getArr_trgtman_dept_nm();
			String[] arrTmpJikgupNm = vo.getArr_trgtman_jikgup_nm();

			for (int j = 0; j < vo.getArr_trgtman_id().length; j++) {

				vo.setTrgtman_id(arrTmpId[j]); //
				vo.setTrgtman_nm(arrTmpNm[j]);
				vo.setTrgtman_dept_nm(arrTmpDeptNm[j]);
				vo.setTrgtman_jikgup_nm(arrTmpJikgupNm[j]);

				commonDAO.insert("las.lawconsult.insertContractAuthreq", vo);
			}
		}

		result = commonDAO.modify("las.lawconsult.modify", vo);

		if (result == 1) {
			deleteLawConsultType(vo);

			for (int i = 0; i < vo.getConsult_type_list().size(); i++) {
				vo.setConsult_type((String) vo.getConsult_type_list().get(i));
				commonDAO.insert("las.lawconsult.insertConsultType", vo);
			}
		}
		return result;
	}

	/**
	 * 전체삭제
	 */
	public int deleteAllLawConsult(LawConsultVO vo) throws Exception {
		int result = 0;

		// 삭제자 정보 세션에서 받아와 vo에 세팅
		vo.setDel_nm(vo.getSession_user_nm());
		vo.setDel_id(vo.getSession_user_id());

		result = commonDAO.delete("las.lawconsult.deleteAll", vo); // MASTER
		commonDAO.delete("las.lawconsult.deleteAllConsultType", vo); // 자문유형 테이블
																		// 내용 삭제
		commonDAO.delete("las.lawconsult.deleteRespman", vo); // 담당자 삭제
		return result;
	}

	/**
	 * 삭제
	 */
	public int deleteLawConsult(LawConsultVO vo) throws Exception {
		int result = 0;

		// 삭제자 정보 세션에서 받아와 vo에 세팅
		vo.setDel_nm(vo.getSession_user_nm());
		vo.setDel_id(vo.getSession_user_id());

		result = commonDAO.delete("las.lawconsult.delete", vo);
		commonDAO.delete("las.lawconsult.deleteConsultType", vo);

		return result;
	}

	/**
	 * 상세보기
	 */
	public ListOrderedMap detailLawConsult(LawConsultVO vo) throws Exception {

		// 상세조회할 데이터를 불러온다
		List list = commonDAO.list("las.lawconsult.detail", vo);
		ListOrderedMap lom = new ListOrderedMap();

		if (list != null && list.size() != 0) {
			lom = (ListOrderedMap) list.get(0);
		}

		return lom;
	}

	public List listLawConsultReqman(LawConsultVO vo) throws Exception {
		// 검색관련변수 XXS 처리
		vo.setSrch_start_ymd(StringUtil.convertHtmlTochars(vo.getSrch_start_ymd()));
		vo.setSrch_end_ymd(StringUtil.convertHtmlTochars(vo.getSrch_end_ymd()));
		vo.setSrch_prgrs_status(StringUtil.convertHtmlTochars(vo.getSrch_prgrs_status()));

		return commonDAO.list("las.lawconsult.list_reqman", vo);
	}

	// 2014-03-03 Kevin. CC된 리스트를 조회한다.
	public List listLawConsultCCed(LawConsultVO vo) throws Exception {
		return commonDAO.list("las.lawconsult.ccedConsultationList", vo);
	}

	public List listLawStaConsultReqman(LawConsultVO vo) throws Exception {
		// 검색관련변수 XXS 처리
		vo.setSrch_start_ymd(StringUtil.convertHtmlTochars(vo.getSrch_start_ymd()));
		vo.setSrch_end_ymd(StringUtil.convertHtmlTochars(vo.getSrch_end_ymd()));
		vo.setSrch_prgrs_status(StringUtil.convertHtmlTochars(vo.getSrch_prgrs_status()));

		return commonDAO.list("las.stalawconsult.list_reqman", vo);
	}

	public List listLawConsultGrpmgr(LawConsultVO vo) throws Exception {

		// 검색관련변수 XXS 처리
		vo.setSrch_start_ymd(StringUtil.convertHtmlTochars(vo.getSrch_start_ymd()));
		vo.setSrch_end_ymd(StringUtil.convertHtmlTochars(vo.getSrch_end_ymd()));
		vo.setSrch_prgrs_status(StringUtil.convertHtmlTochars(vo.getSrch_prgrs_status()));
		vo.setSrch_consult_type(StringUtil.convertHtmlTochars(vo.getSrch_consult_type()));
		vo.setSrch_consult_type_name(StringUtil.convertHtmlTochars(vo.getSrch_consult_type_name()));
		vo.setSrch_title(StringUtil.convertHtmlTochars(vo.getSrch_title()));
		vo.setSrch_cont(StringUtil.convertHtmlTochars(vo.getSrch_cont()));
		vo.setSrch_reg_dept(StringUtil.convertHtmlTochars(vo.getSrch_reg_dept()));
		vo.setSrch_reg_nm(StringUtil.convertHtmlTochars(vo.getSrch_reg_nm()));
		vo.setUser_id(vo.getSession_user_id());

		return commonDAO.list("las.lawconsult.list_grpmgr", vo);
	}

	public List listLawConsultAdmin(LawConsultVO vo) throws Exception {
		// 검색관련변수 XXS 처리
		vo.setSrch_start_ymd(StringUtil.convertHtmlTochars(vo.getSrch_start_ymd()));
		vo.setSrch_end_ymd(StringUtil.convertHtmlTochars(vo.getSrch_end_ymd()));
		vo.setSrch_prgrs_status(StringUtil.convertHtmlTochars(vo.getSrch_prgrs_status()));
		vo.setSrch_consult_type(StringUtil.convertHtmlTochars(vo.getSrch_consult_type()));
		vo.setSrch_consult_type_name(StringUtil.convertHtmlTochars(vo.getSrch_consult_type_name()));
		vo.setSrch_title(StringUtil.convertHtmlTochars(vo.getSrch_title()));
		vo.setSrch_cont(StringUtil.convertHtmlTochars(vo.getSrch_cont()));
		vo.setSrch_reg_dept(StringUtil.convertHtmlTochars(vo.getSrch_reg_dept()));
		vo.setSrch_reg_nm(StringUtil.convertHtmlTochars(vo.getSrch_reg_nm()));
		vo.setUser_id(vo.getSession_user_id());

		return commonDAO.list("las.lawconsult.list_admin", vo);
	}

	public List listLawStaConsultAdmin(LawConsultVO vo) throws Exception {
		// 검색관련변수 XXS 처리
		vo.setSrch_start_ymd(StringUtil.convertHtmlTochars(vo.getSrch_start_ymd()));
		vo.setSrch_end_ymd(StringUtil.convertHtmlTochars(vo.getSrch_end_ymd()));
		vo.setSrch_prgrs_status(StringUtil.convertHtmlTochars(vo.getSrch_prgrs_status()));
		vo.setSrch_consult_type(StringUtil.convertHtmlTochars(vo.getSrch_consult_type()));
		vo.setSrch_consult_type_name(StringUtil.convertHtmlTochars(vo.getSrch_consult_type_name()));
		vo.setSrch_title(StringUtil.convertHtmlTochars(vo.getSrch_title()));
		vo.setSrch_cont(StringUtil.convertHtmlTochars(vo.getSrch_cont()));
		vo.setSrch_reg_dept(StringUtil.convertHtmlTochars(vo.getSrch_reg_dept()));
		vo.setSrch_reg_nm(StringUtil.convertHtmlTochars(vo.getSrch_reg_nm()));
		vo.setUser_id(vo.getSession_user_id());

		return commonDAO.list("las.lawStaconsult.list_admin", vo);
	}

	public List listLawStaConsultGrpmgr(LawConsultVO vo) throws Exception {
		// 검색관련변수 XXS 처리
		vo.setSrch_start_ymd(StringUtil.convertHtmlTochars(vo.getSrch_start_ymd()));
		vo.setSrch_end_ymd(StringUtil.convertHtmlTochars(vo.getSrch_end_ymd()));
		vo.setSrch_prgrs_status(StringUtil.convertHtmlTochars(vo.getSrch_prgrs_status()));
		vo.setSrch_consult_type(StringUtil.convertHtmlTochars(vo.getSrch_consult_type()));
		vo.setSrch_consult_type_name(StringUtil.convertHtmlTochars(vo.getSrch_consult_type_name()));
		vo.setSrch_title(StringUtil.convertHtmlTochars(vo.getSrch_title()));
		vo.setSrch_cont(StringUtil.convertHtmlTochars(vo.getSrch_cont()));
		vo.setSrch_reg_dept(StringUtil.convertHtmlTochars(vo.getSrch_reg_dept()));
		vo.setSrch_reg_nm(StringUtil.convertHtmlTochars(vo.getSrch_reg_nm()));
		vo.setUser_id(vo.getSession_user_id());

		return commonDAO.list("las.stalawconsult.list_grpmgr", vo);
	}

	/**
	 * Search Reviewers by joon 17/Mar/2014
	 */
	public List listReviewer(LawConsultVO vo) throws Exception {
		return commonDAO.list("las.lawconsult.listReviewer", vo);
	}

	public List listLawConsultRespman(LawConsultVO vo) throws Exception {
		return commonDAO.list("las.lawconsult.listRespman", vo);
	}

	public List listLawConsultRespmanOne(LawConsultVO vo) throws Exception {
		return commonDAO.list("las.lawconsult.listRespmanOne", vo);
	}

	public List listLawConsultHistory(LawConsultVO vo) throws Exception {

		return commonDAO.list("las.lawconsult.listHistory", vo);
	}

	public List listLawConsultHistoryReqman(LawConsultVO vo) throws Exception {
		return commonDAO.list("las.lawconsult.listHistory_reqman", vo);
	}

	public List listLawConsultRespman2(LawConsultVO vo) throws Exception {
		return commonDAO.list("las.lawconsult.listRespman2", vo);
	}

	public ListOrderedMap detailLawConsultReqman(LawConsultVO vo) throws Exception {
		// 상세조회할 데이터를 불러온다
		List<?> list = commonDAO.list("las.lawconsult.detail_reqman", vo);
		ListOrderedMap lom = new ListOrderedMap();
		String consult_type_name = null;
		if (list != null && list.size() != 0) {
			lom = (ListOrderedMap) list.get(0);
			consult_type_name = (String) lom.get("CONSULT_TYPE_NAME");
		}

		// consult_type_name의 값이 null인 경우 복수개의 명칭이기때문이며 따라서 아래의 쿼리 수행함.
		if (consult_type_name == null || (consult_type_name != null && consult_type_name.equals(""))) {
			consult_type_name = readMultiCousultTypeName(lom, vo);
			if (consult_type_name != null) {
				lom.put("CONSULT_TYPE_NAME", consult_type_name);
			}
		}

		return lom;
	}

	public ListOrderedMap detailLawConsultGrpmgr(LawConsultVO vo) throws Exception {
		// 상세조회할 데이터를 불러온다
		List<?> list = commonDAO.list("las.lawconsult.detail_grpmgr", vo);
		ListOrderedMap lom = new ListOrderedMap();
		String consult_type_name = null;
		if (list != null && list.size() != 0) {
			lom = (ListOrderedMap) list.get(0);
			consult_type_name = (String) lom.get("CONSULT_TYPE_NAME");
		}

		// consult_type_name의 값이 null인 경우 복수개의 명칭이기때문이며 따라서 아래의 쿼리 수행함.
		if (consult_type_name == null || (consult_type_name != null && consult_type_name.equals(""))) {
			consult_type_name = readMultiCousultTypeName(lom, vo);
			if (consult_type_name != null) {
				lom.put("CONSULT_TYPE_NAME", consult_type_name);
			}
		}

		return lom;
	}

	public String readMultiCousultTypeName(ListOrderedMap lom, LawConsultVO vo) throws Exception {
		String consult_type_name = "";
		vo.setDmstfrgn_gbn((String) lom.get("DMSTFRGN_GBN"));
		vo.setConsult_type((String) lom.get("CONSULT_TYPE"));
		List<?> list = commonDAO.list("las.lawconsult.multi_cousult_type_name", vo);
		ListOrderedMap lom2 = new ListOrderedMap();
		if (list != null && list.size() != 0) {
			lom2 = (ListOrderedMap) list.get(0);
			consult_type_name = (String) lom2.get("CONSULT_TYPE_NAME");
		}

		return consult_type_name;
	}

	public ListOrderedMap detailLawConsultRespman(LawConsultVO vo) throws Exception {
		// 상세조회할 데이터를 불러온다
		List<?> list = commonDAO.list("las.lawconsult.detail_respman", vo);
		ListOrderedMap lom = new ListOrderedMap();

		if (list != null && list.size() != 0) {
			lom = (ListOrderedMap) list.get(0);
		}

		return lom;
	}

	public int modifyExtnlStatusLawConsult(LawConsultVO vo) throws Exception {
		int result = 0;

		result = commonDAO.modify("las.lawconsult.modifyExtnlStatus", vo);
		return result;
	}

	public int modifyIntnlStatusLawConsult(LawConsultVO vo) throws Exception {
		int result = 0;

		result = commonDAO.modify("las.lawconsult.modifyIntnlStatus", vo);
		return result;
	}

	public int modifyStatusLawConsult(HttpServletRequest request, LawConsultVO vo) throws Exception {
		int result = 0;
		ListOrderedMap lom = new ListOrderedMap();
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("comp_cd", vo.getSession_comp_cd());
		hm.put("cnslt_no", vo.getCnslt_no());
		
		// textarea 줄바꿈 처리 및 XSS 처리
		// 보류
		if ("S05".equals(vo.getPrgrs_status())) {
			vo.setHold_cause(StringUtil.replace(vo.getHold_cause(), "\r\n", "<br>"));
			vo.setHold_cause(StringUtil.convertHtmlTochars(vo.getHold_cause()));
			vo.setHold_cause(StringUtil.replace(vo.getHold_cause(), "&lt;br&gt;", "<br>"));
		}
		// 반려
		else if ("S07".equals(vo.getPrgrs_status())) {

			String main_title = "";
			
			vo.setRejct_cause(StringUtil.replace(vo.getRejct_cause(), "\r\n", "<br>"));
			vo.setRejct_cause(StringUtil.convertHtmlTochars(vo.getRejct_cause()));
			vo.setRejct_cause(StringUtil.replace(vo.getRejct_cause(), "&lt;br&gt;", "<br>"));

			// 메일 발송에 필요한 정보
			lom = detailLawConsult(vo);
			// Set title
			if ("Y".equals(vo.getIsStdCont())) {
				main_title = messageSource.getMessage("las.mail.field.lawconsultImpl.mailTitle06", null, new RequestContext(request).getLocale());
			} else {
				main_title = messageSource.getMessage("las.mail.field.lawconsultImpl.mailTitle05", null, new RequestContext(request).getLocale());
			}

			hm.put("mail_type", "03");
			hm.put("main_title", main_title);
			hm.put("param1", (String) lom.get("title"));
			hm.put("param2", (String) lom.get("reg_nm"));
			hm.put("param3", String.valueOf(lom.get("reg_dt")));
			hm.put("param4", (String) lom.get("rejct_cause"));
			
			//Email to Requester Sungwoo Replaced 2014-07-29
			sendMailToAdmin(hm); 
			
			// Email to Reviewer. Sungwoo Replaced 2014-07-29
			hm.put("reciver_list", "Review");
			sendMailToAdmin(hm);
	
			// Email to Legal Admin. Sungwoo Replaced 2014-07-29
			hm.put("reciver_list", "Legal");
			sendMailToAdmin(hm);
		}
		// 의뢰
		else if ("S02".equals(vo.getPrgrs_status())) {
			vo.setOrdr_cont(StringUtil.replace(vo.getOrdr_cont(), "\r\n", "<br>"));
			vo.setOrdr_cont(StringUtil.convertHtmlTochars(vo.getOrdr_cont()));
			vo.setOrdr_cont(StringUtil.replace(vo.getOrdr_cont(), "&lt;br&gt;", "<br>"));
		}
		// 현재 상태가 미결이고 회신을 하려고 할 시에...
		else if ("S03".equals(vo.getPrgrs_status())) {

			// 회신일자 반영
			commonDAO.modify("las.lawconsult.modifyReDt", vo);

			// 메일 발송에 필요한 정보
			lom = detailLawConsult(vo);

			// 메일 정보가 있을 시 메일 발송한다.
			String mailTitle = messageSource.getMessage("las.page.field.lawconsultImpl.modifyStatusLawConsult01", null, new RequestContext(request).getLocale());
			String mailContents = (String) messageSource.getMessage("las.page.field.lawconsultImpl.modifyStatusLawConsult02", null, new RequestContext(request).getLocale());
			
			//Requester Send Sungwoo Replaced 2014-07-29
			sendMailToAdmin(hm);
			
			lom.put("mailTitle", mailTitle);
			lom.put("mailContents", mailContents);
			
			hm.put("mail_type", 	"01");
			hm.put("main_title", 	lom.get("mailTitle").toString());
			hm.put("param1", 		lom.get("title").toString());
			hm.put("param2", 		lom.get("respman_nm").toString());
			// timestamp를 string으로 바꾸기 위한 포맷
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			hm.put("param3", 		lom.get("reg_dt").toString());
			hm.put("param4", 		date.format(lom.get("re_dt")));
			hm.put("param5", 		lom.get("mailContents").toString());

			// Email to Reviewer. Sungwoo Replaced 2014-07-29
			hm.put("reciver_list", 	"Review");
			sendMailToAdmin(hm);
			
			// Email to Legal. Sungwoo Replaced 2014-07-29
			hm.put("reciver_list", 	"Legal");
			sendMailToAdmin(hm);
			
			// 미결 -> 회신 시 . 즉 그룹장이 미결에서 회신 시 사안개요, 법무팀 의견의 수정사항 및 법무그룹장 의견을 반영
			if ("S08".equals(vo.getNow_intnl_prgrs_status())) {

				vo.setApbt_opnn(StringUtil.replace(vo.getApbt_opnn(), "\r\n", "<br>"));
				vo.setApbt_opnn(StringUtil.convertHtmlTochars(vo.getApbt_opnn()));
				vo.setApbt_opnn(StringUtil.replace(vo.getApbt_opnn(), "&lt;br&gt;", "<br>"));

				// 회신일자 반영
				commonDAO.modify("las.lawconsult.modifyReDt", vo);
			}
		}
		// 법무장 반려
		else if ("S11".equals(vo.getPrgrs_status())) {
			vo.setRejct_opnn(StringUtil.replace(vo.getRejct_opnn(), "\r\n", "<br>"));
			vo.setRejct_opnn(StringUtil.convertHtmlTochars(vo.getRejct_opnn()));
			vo.setRejct_opnn(StringUtil.replace(vo.getRejct_opnn(), "&lt;br&gt;", "<br>"));
		}

		result = commonDAO.modify("las.lawconsult.modifyStatus", vo);
		return result;
	}

	/**
	 * Getting Legal Admin's Email accounts
	 */
	public List getLegalAdminEmailAccounts(LawConsultVO vo) throws Exception {
		return commonDAO.list("las.lawconsult.listLegalAdmin", vo);
	}
	
	/**
	 * Getting Legal Admin's Email accounts by JOON
	 * query에서 legal admin 제외
	 */
	public List listReviewerAndLegalAdmin(LawConsultVO vo) throws Exception {
		return commonDAO.list("las.lawconsult.listReviewerAndLegalAdmin", vo);
	}

	/**
	 * 그룹장 상세화면 수정(지정버튼-메모 및 회신전 결제확인 세팅)
	 */
	public int modifyGrpmgrDetailLawConsult(LawConsultVO vo) throws Exception {
		int result = 0;

		vo.setOrdr_cont(StringUtil.replace(vo.getOrdr_cont(), "\r\n", "<br>"));
		vo.setOrdr_cont(StringUtil.convertHtmlTochars(vo.getOrdr_cont()));
		vo.setOrdr_cont(StringUtil.replace(vo.getOrdr_cont(), "&lt;br&gt;", "<br>"));

		result = commonDAO.modify("las.lawconsult.modifyGrpmgrDetail", vo);
		return result;
	}

	/**
	 * 자문요청 완료상태 변경 (미완료<->완료)
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int updateCompleteStatusLawConsult(LawConsultVO vo) throws Exception {
		int result = 0;
		result = commonDAO.modify("las.lawconsult.updateCompleteStatusLawConsult", vo);
		return result;
	}

	public int modifyReDtLawConsult(LawConsultVO vo) throws Exception {
		int result = 0;

		result = commonDAO.modify("las.lawconsult.modifyReDt", vo);
		return result;
	}

	public int deleteLawConsultType(LawConsultVO vo) throws Exception {
		int result = 0;
		result = commonDAO.delete("las.lawconsult.deleteConsultType", vo);
		return result;
	}

	public int deleteRespmanLawConsult(LawConsultVO vo) throws Exception {
		int result = 0;
		result = commonDAO.delete("las.lawconsult.deleteRespman", vo);
		return result;
	}

	/**
	 * 해당 건 담당자인지 유무 판별
	 */
	public String findLawConsultRespman(LawConsultVO vo) throws Exception {
		List list = commonDAO.list("las.lawconsult.findRespman", vo);

		if (list == null || list.size() <= 0)
			return "N";
		else
			return "Y";
	}

	public List listLawConsultRecent(LawConsultVO vo) throws Exception {
		return commonDAO.list("las.lawconsult.list_recent", vo);
	}

	public List findUserEmail(LawConsultVO vo) throws Exception {
		return commonDAO.list("las.lawconsult.findUserEmail", vo);
	}

	/**
	 * 이관
	 */
	public int transferLawConsult(LawConsultVO vo) throws Exception {
		int result = 0;

		// 기존데이터 이관으로 상태 변경
		result = commonDAO.modify("las.lawconsult.modifyTransfer", vo);

		// 이관될 데이터의 primary key 생성
		String PK = getPrimaryKey(vo);
		vo.setCnslt_no(PK);

		// 데이터 이관
		result = commonDAO.insert("las.lawconsult.transfer", vo);

		// 자문유형 테이블에 기본 데이터 삽입
		vo.setConsult_type("A00");
		result = commonDAO.insert("las.lawconsult.insertConsultType", vo);

		/*************************************************
		 * 첨부파일 저장
		 *************************************************/
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(vo.getCnslt_no() + "@" + Integer.toString(vo.getHstry_no()));
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());
		clmsFileService.mngClmsAttachFile(fvo);

		return result;
	}

	/**
	 * 상위 메뉴 ID를 가져온다.
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public String getUpMenuId(LawConsultVO vo) throws Exception {
		String upMenuId = null;

		List<?> list = commonDAO.list("secfw.menu.detail", vo);
		if (list != null && list.size() == 1) {
			upMenuId = (String) (((Map) list.get(0)).get("up_menu_id"));
		}

		return upMenuId;
	}

	/**
	 * INSERT시 CNSLT_POS 와 CNSLT_SRT, HSTRY_NO를 세팅한다.
	 * 
	 * @param vo
	 * @throws Exception
	 */
	public void setCnslt(LawConsultVO vo) throws Exception {
		List<?> list = null;
		ListOrderedMap lom = new ListOrderedMap();

		// 들여쓰기 컬럼(cnslt_pos) +1 증가
		//vo.setCnslt_pos(vo.getCnslt_pos() + 1);	//Sungwoo commented 2014-07-02

		// 현재 들어가려는 데이터의 cnslt_srt(출력 순서)값 + 1보다 큰 값은 cnslt_srt값 1씩 각각 증가
		int temp_cnslt_srt = vo.getCnslt_srt() + 1;
		list = commonDAO.list("las.lawconsult.findMaxCnsltSrt", vo);
		lom = new ListOrderedMap();

		if (list != null) {
			lom = (ListOrderedMap) list.get(0);
			int maxCnsltSrt = Integer.parseInt(String.valueOf(lom.get("cnslt_srt")));
			
			//Sungwoo Replacement 2014-07-02 Reply는 항상 마지막에 등록되도록 변경 
			temp_cnslt_srt = maxCnsltSrt + 1;
			//Sungwoo commented. 2014-07-02
			/*
			// 삽입되려는 행의 SRT값보다 큰 행들의 CNSLT_SRT 값을 각각 1씩 증가시킨다.
			for (int i = vo.getCnslt_srt() + 1; i <= maxCnsltSrt; i++) {
				vo.setCnslt_srt(i + 1);
				vo.setHstry_no(vo.getHstry_no() + 1);
				commonDAO.modify("las.lawconsult.modifyCnsltSrt", vo);
			}			
			*/
		}

		// hstry_no 값 설정. 현재 건에서 가장 큰 hstry_no 값을 구해서 1을 더함
		list = commonDAO.list("las.lawconsult.findMaxHstryNo", vo);
		if (list != null) {
			lom = (ListOrderedMap) list.get(0);
			vo.setHstry_no(Integer.parseInt(String.valueOf(lom.get("hstry_no"))) + 1);
		}
		vo.setCnslt_srt(temp_cnslt_srt);
		vo.setCnslt_pos(temp_cnslt_srt);	//Sungwoo replacement 2014-07-02 들여쓰기 무조건 아래로 정렬
	}

	/**
	 * 메일 전송
	 * 
	 * @param request
	 * @param pVo
	 * @throws Exception
	 */
	private void sendMailToAdmin(HashMap map) throws Exception {
		try {
			MailVO mailVo = new MailVO();
			ListOrderedMap receiverListLom			= null;
			String cntrtRespId						= "";
			String cntrtRespMailAddr				= "";
			// 받는 사람 이메일 정보
			String reciver_list						= map.get("reciver_list") != null ? StringUtil.bvl(map.get("reciver_list").toString(),"") : "";	//added Sungwoo 2014-07-01
			String subject 							= null; // 제목
			String contentHtml 						= null; // 내용
			
			LawConsultVO vo							= new LawConsultVO();	//Sungwoo Replacement 2014-07-30
			vo.setComp_cd(map.get("comp_cd").toString());
			vo.setCnslt_no(map.get("cnslt_no").toString());
			
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			String moduleId = "LAS";
			String misId = EsbUtil.generateMisId("MAIL");
			
			mailVo.setModule_id(moduleId);
			mailVo.setMis_id(misId);
			mailVo.setMsg_key("11");
			/****************************************************************
			 * MailVO 변수 세팅
			 ****************************************************************/

			//보내는 사람
			mailVo.setSender_single_id(propertyService.getProperty("clms.admin.id"));
			mailVo.setSender_mail_addr(propertyService.getProperty("clms.admin.email"));
			
			//로케일, 인코딩 설정, Time Zone
			//mailVo.setLocale(vo.getSession_user_locale());
			mailVo.setLocale("en");
			mailVo.setEncoding("utf-8");
			mailVo.setTime_zone("GMT+0");
			mailVo.setIs_dst("false");

			// 제목
			subject = (String) map.get("main_title");
			map.put("main_link", propertyService.getProperty("secfw.url.domain") + "/login.do");
			contentHtml = mailSendService.getLawConsultMailContent(map);

			mailVo.setSubject(subject);
			mailVo.setBhtml_content_check("true");
			mailVo.setBody(contentHtml);

			//수신자리스트 조회
			List<?> receiverList = null;
			
			
			if(reciver_list.equals("Review")){
				receiverList = listReviewer(vo);
			}else if(reciver_list.equals("Legal")){
				receiverList = getLegalAdminEmailAccounts(vo);	
			}else{
				receiverList = findUserEmail(vo);	//default email Notification list
			}
			if(receiverList != null && receiverList.size() > 0 ) {				
				int receiver_cnt = receiverList.size();
				
				//받는 사람
				String[] iseq_ids  = null;
				String[] rec_types = null;
				String[] rec_addrs = null;
				
				iseq_ids  = new String[receiver_cnt];
				rec_types = new String[receiver_cnt];
				rec_addrs = new String[receiver_cnt];
				
				for (int i = 0; i < receiverList.size(); i++) {
					
					receiverListLom 	= (ListOrderedMap)receiverList.get(i);
					cntrtRespId 		= (String)receiverListLom.get("USER_ID");
					
					//상신자 정보 조회
			        if(cntrtRespId != null && !"".equals(cntrtRespId)) {
			        	cntrtRespMailAddr = esbOrgService.getUserEpMailAddr(cntrtRespId);
			        }
					
					//STATUS 값 설정 - Default "0"
					mailVo.setStatus("0");
					
					iseq_ids[i]  = "1";
					rec_types[i] = "t";
					rec_addrs[i] = cntrtRespMailAddr;


					mailVo.setBhtml_content_check("true");
					mailVo.setIseq_ids(iseq_ids);
					mailVo.setRec_types(rec_types);
				    mailVo.setRec_addrs(rec_addrs);
				    mailVo.setBody(contentHtml.toString());
				}				
				
				if(cntrtRespMailAddr != null && !"".equals(cntrtRespMailAddr)) {
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
		}
	}

	/**
	 * insert Review 시 담당자 insert 작업 수행(그룹장이 담당자 목록에 추가되는 경우)
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int insertRespmanLawConsult(LawConsultVO vo) throws Exception {
		int result = 0;

		// 그룹장 상세화면에서 담당자 지정시
		if (vo.getPrgrs_status().equals("S02")) {
			// 기존담당자 삭제
			deleteRespmanLawConsult(vo);

			if (vo.getRespman_list() != null) {
				for (int i = 0; i < vo.getRespman_list().length; i++) {
					vo.setRespman_id(vo.getRespman_list()[i]);

					result = commonDAO.insert("las.lawconsult.insertRespman", vo);
				}
			} else
				result = 1;

			// 메모 및 회신전결제확인 필드 내용 업데이트
			modifyGrpmgrDetailLawConsult(vo);
		}
		// 검토회신에서 그룹장 회신 시

		else {
			// 그룹장 회신이므로 그룹장을 담당자에 추가
			result = commonDAO.insert("las.lawconsult.insertRespman", vo);
		}
		return result;
	}

	public List listLawConsultRespmanPop(LawConsultVO vo) throws Exception {
		vo.setReg_operdiv(StringUtil.bvl(vo.getReg_operdiv(), "")); // 등록_사업부

		return commonDAO.list("las.lawconsult.getLasPerson", vo);
	}

	/**
	 * primary key 생성 및 리턴
	 */
	public String getPrimaryKey(LawConsultVO vo) throws Exception {

		// 상세조회할 데이터를 불러온다
		List list = commonDAO.list("las.lawconsult.createPrimaryKey", vo);
		ListOrderedMap lom = new ListOrderedMap();
		String PK = "";

		if (list != null && list.size() != 0) {
			lom = (ListOrderedMap) list.get(0);
		}

		PK = (String) lom.get("pk");
		return PK;
	}

	public List countLawConsult(LawConsultVO vo) throws Exception {
		return commonDAO.list("las.lawconsult.countLawConsult", vo);
	}

	public List countStandardContract(LawConsultVO vo) throws Exception {
		return commonDAO.list("las.lawconsult.countStandardContract", vo);
	}

	public String isStdCont(String isStdCont, HttpServletRequest request) throws Exception {
		if ("Y".equals(isStdCont)) {
			return messageSource.getMessage("las.page.field.lawconsultImpl.mailType02", null, new RequestContext(request).getLocale());
		} else {
			return messageSource.getMessage("las.page.field.lawconsultImpl.mailType01", null, new RequestContext(request).getLocale());
		}
	}

	/**
	 * 자문 참조자 조회
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<?> listContractAuthreq(LawConsultVO vo) throws Exception {
		return commonDAO.list("las.lawconsult.listContractAuthreq", vo);
	}

	/**
	 * 자문 중요도 마크업 업데이트 ( 설정 / 해제 )
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int setMarkUpAJAX(LawConsultVO vo) throws Exception {
		return commonDAO.modify("las.lawconsult.setMarkUpAJAX", vo);
	}

}