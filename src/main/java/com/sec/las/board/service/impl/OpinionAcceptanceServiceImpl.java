/**
 * File name	: OpinionAcceptanceServiceImpl.java
 * Description	: 타법인 의견 수렴 서비스 구현체
 * Author		:
 * Date			: 2013. 11
 * Copyright	: SAMSUNG.
 */
package com.sec.las.board.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.commons.collections.map.ListOrderedMap;

import anyframe.core.idgen.IIdGenerationService;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.EsbUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.singleIF.dto.MailVO;
import com.sds.secframework.singleIF.service.EsbMailService;
import com.sds.secframework.singleIF.service.EsbOrgService;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import com.sec.las.board.dto.OpinionAcceptanceForm;
import com.sec.las.board.dto.OpinionAcceptanceVO;
import com.sec.las.board.service.OpinionAcceptanceService;

public class OpinionAcceptanceServiceImpl extends CommonServiceImpl implements OpinionAcceptanceService {
	
	/**
	 * ID 생성 서비스
	 */
	IIdGenerationService idGenerationService;
	
	/**
	 * ID 생성 서비스 세팅
	 * @param idGenerationService
	 */
	public void setIdGenerationService(IIdGenerationService idGenerationService) {
		this.idGenerationService = idGenerationService;
	}
	
	/**
	 * 싱글 메일 서비스
	 */
	private EsbMailService esbMailService = null;
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

	/**
	 * 조회
	 * @param vo OpinionAcceptanceVO
	 * @return List
	 * @throws Exception
	 */
	public List listOpinionAcceptance(OpinionAcceptanceVO vo) throws Exception {
		return commonDAO.list("las.board.listOpinionAcceptance", vo);
	}
	
	/**
	 * 과제 등록
	 * @param  vo OpinionAcceptanceVO
	 * @return int
	 * @throws Exception  sqlCUDExecutor(IQueryInfo queryInfo, Object[] values)
	 */
	public int insertOpinionAcceptance(OpinionAcceptanceForm form, OpinionAcceptanceVO vo) throws Exception {
		
		try {
			
			int sussess_cnt = 0;
			int part_member_cnt = 0;
			String seqno = "";

			// SEQ 따기
			List resultList = commonDAO.list("las.board.bbs.getNextAccno", vo);
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			seqno = String.valueOf(lom.get("NEXT_ACC_NO"));			
			vo.setSeqno(seqno);
			form.setSeqno(seqno);

			// 첨부 파일 저장
			ClmsFileVO fvo = new ClmsFileVO();
			fvo.setSys_cd(vo.getSys_cd());
			fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
			fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
			fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
			fvo.setRef_key(seqno);
			fvo.setFileInfos(vo.getFileInfos());
			fvo.setReg_id(vo.getSession_user_id());		
			clmsFileService.mngClmsAttachFile(fvo);	
				
			// 참여자 등록
			part_member_cnt = saveOpinionAcceptanceUser(form, vo);		
			vo.setPart_member_cnt(String.valueOf(part_member_cnt));			
			sussess_cnt = 	commonDAO.insert("las.board.insertOpinionAcceptance", vo);	
					
			return sussess_cnt;
			
		} catch (Exception e) {
			e.printStackTrace();			
			throw e;
		}		
	}
	
	/**
	 * 과제 수정
	 * @param  vo OpinionAcceptanceVO
	 * @return int
	 * @throws Exception  sqlCUDExecutor(IQueryInfo queryInfo, Object[] values)
	 */
	public int modifyOpinionAcceptance(OpinionAcceptanceForm form, OpinionAcceptanceVO vo) throws Exception {
		
		try {
			
			int sussess_cnt = 0;
			int part_member_cnt = 0;
			int part_memver_delete_cnt = 0;

			// 첨부 파일 저장
			ClmsFileVO fvo = new ClmsFileVO();
			fvo.setSys_cd(vo.getSys_cd());
			fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
			fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
			fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
			fvo.setRef_key(vo.getSeqno());
			fvo.setFileInfos(vo.getFileInfos());
			fvo.setReg_id(vo.getSession_user_id());		
			clmsFileService.mngClmsAttachFile(fvo);	
			
			// 기 등록 참여자 전체 삭제
			part_memver_delete_cnt = commonDAO.delete("las.board.deleteAllOpinionAcceptance", vo);		
				
			if(part_memver_delete_cnt!=-1){
				// 참여자 등록
				part_member_cnt = saveOpinionAcceptanceUser(form, vo);		
				vo.setPart_member_cnt(String.valueOf(part_member_cnt));	
					
				sussess_cnt = 	commonDAO.insert("las.board.modifyOpinionAcceptance", vo);				
			}	

			return sussess_cnt;
			
		} catch (Exception e) {
			e.printStackTrace();			
			throw e;
		}		
	}
	
	/**
	 * 답변 등록
	 * @param vo OpinionAcceptanceVO
	 * @return int
	 * @throws Exception
	 */
	public int saveOpinionAcceptanceReply(OpinionAcceptanceVO vo, String insert_kbn) throws Exception{

		int sussess_cnt = 0;		
		String re_seqno = "";
		
		if("REPLY".equals(insert_kbn)){ //  답변 등록
			// 답변 SEQ 따기
			List resultList = commonDAO.list("las.board.bbs.getNextRefSeqno", vo);
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			
			re_seqno = String.valueOf(lom.get("NEXT_RE_SEQNO"));		
				
		} else if("REPLY_MOD".equals(insert_kbn)){ // 답변 수정
		
			re_seqno = vo.getRe_seqno();
		
		}
		
		vo.setRe_seqno(re_seqno);
		
		// 첨부 파일 저장
		ClmsFileVO fvo = new ClmsFileVO();
		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(re_seqno);
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());
		
		clmsFileService.mngClmsAttachFile(fvo);
		
		if("REPLY".equals(insert_kbn)){ //  답변 등록
			sussess_cnt = 	commonDAO.insert("las.board.insertOpinionAcceptanceReply", vo);
			
			if(sussess_cnt > 0)  { //  답변 등록 성공 시
				vo.setReply_yn("Y");
				commonDAO.modify("las.board.insertOpinionAcceptanceReplyYN", vo);
			}
			
			
		} else if("REPLY_MOD".equals(insert_kbn)){ // 답변 수정
			sussess_cnt = 	commonDAO.insert("las.board.modifyOpinionAcceptanceReply", vo);
		}				
		return sussess_cnt;
	}
	
	/**
	 * 과제 수정
	 * @param  vo OpinionAcceptanceVO
	 * @return int
	 * @throws Exception  sqlCUDExecutor(IQueryInfo queryInfo, Object[] values)
	 */
	public int modifyReplyOpinionAcceptance(OpinionAcceptanceVO vo) throws Exception {
		
		try {
			
			int del_cnt = 0;
			
			// 첨부 파일 저장
			ClmsFileVO fvo = new ClmsFileVO();
			fvo.setSys_cd(vo.getSys_cd());
			fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
			fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
			fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
			fvo.setRef_key(vo.getRe_seqno());
			fvo.setFileInfos(vo.getFileInfos());
			fvo.setReg_id(vo.getSession_user_id());		
			
			clmsFileService.mngClmsAttachFile(fvo);	
			
			del_cnt = 	commonDAO.insert("las.board.modifyReplyOpinionAcceptance", vo);	

			return del_cnt;
			
		} catch (Exception e) {
			e.printStackTrace();			
			throw e;
		}		
	}
	
	/**
	 * 과제 답변 대상자 메일 발송 /  과제 스테이터스 변경
	 * @param vo OpinionAcceptanceVO
	 * @return int
	 * @throws Exception
	 */
	public int sendRequestReplyOpinion(OpinionAcceptanceVO vo) throws Exception{
		
		try {
			
			int mod_cnt = 0;			
			int sent_cnt = 0;
			
			mod_cnt = 	commonDAO.modify("las.board.sendRequestReplyOpinion", vo);	

			// 메일발송
			if(mod_cnt > 0)
				sent_cnt =  sendOpinionRequestInformMail(vo);
			
			return sent_cnt;
			
		} catch (Exception e) {
			e.printStackTrace();			
			throw e;
		}		
	}
	
	/**
	 * 참여자 등록
	 * @param vo
	 * @return int
	 * @throws Exception
	 */
	public int insertOpinionAcceptanceUser(OpinionAcceptanceVO vo) throws Exception{
		int userSeqno = idGenerationService.getNextIntegerId();  //참여자 테이블 Key
		vo.setUserSeqno(userSeqno);
		
		return commonDAO.insert("las.board.insertOpinionAcceptanceUser", vo);
	}
	
	/**
	 * 삭제
	 * @param  vo OpinionAcceptanceVO
	 * @return int
	 * @throws Exception
	 */
	public int deleteOpinionAcceptance(OpinionAcceptanceVO vo) throws Exception {

		int result=0;
		result = commonDAO.delete("las.board.deleteOpinionAcceptance", vo);
		
		//  첨부파일 삭제
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(vo.getSeqno());
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());

        clmsFileService.delClmsAttachFile(fvo);  
        
		// 기 등록 참여자 전체 삭제
		// commonDAO.delete("las.board.deleteAllOpinionAcceptance", vo);		

		return result;
	}
	
	/**
	 * 답변 삭제
	 * @param  vo OpinionAcceptanceVO
	 * @return int
	 * @throws Exception
	 */
	public int deleteOpinionAcceptanceReply(OpinionAcceptanceVO vo) throws Exception {

		int result=0;
		int reply_cnt = 0;
		result = commonDAO.delete("las.board.deleteOpinionAcceptanceReply", vo);
		
		if(result > 0)  { //  답변 삭제 성공 시
			
			List cnt_list = commonDAO.list("las.board.insertOpinionAcceptanceReplyCnt",vo);
			ListOrderedMap re_lom = (ListOrderedMap)cnt_list.get(0);
			
			reply_cnt  = (Integer)re_lom.get("ANS_CNT");
			
			if(reply_cnt == 0){
				vo.setReply_yn("N");
				commonDAO.modify("las.board.insertOpinionAcceptanceReplyYN", vo);
			}
		
		}
		
		//  첨부파일 삭제
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(vo.getRe_seqno());
		fvo.setFileInfos(vo.getFileInfos());
		fvo.setReg_id(vo.getSession_user_id());

        clmsFileService.delClmsAttachFile(fvo);  

		return result;
	}
	
	/**
	 * 상세조회
	 * @param  vo OpinionAcceptanceVO
	 * @return List
	 * @throws Exception
	 */
	public List detailOpinionAcceptance(OpinionAcceptanceVO vo) throws Exception {		
		return commonDAO.list("las.board.detailOpinionAcceptance", vo);
	}	
	
	/**
	 * 하단 답변 목록 조회
	 * @param  vo OpinionAcceptanceVO
	 * @return List
	 * @throws Exception
	 */
	public List detailListOpinionAcceptance(OpinionAcceptanceVO vo) throws Exception {		
		return commonDAO.list("las.board.detailListOpinionAcceptance", vo);
	}
	
	/**
	 * 답변 상세조회
	 * @param  vo OpinionAcceptanceVO
	 * @return List
	 * @throws Exception
	 */
	public List detailOpinionAcceptanceReply(OpinionAcceptanceVO vo) throws Exception{		
		return commonDAO.list("las.board.detailOpinionAcceptanceReply", vo);
	}
	
	/**
	 * 초기 참여자 리스트 조회 ( 법인 전체 LEGAL ADMIN / 검토자(RA02)
	 * @param  vo OpinionAcceptanceVO
	 * @return List
	 * @throws Exception
	 */
	public List getOpinionAcceptanceUser(OpinionAcceptanceVO vo) throws Exception{
		return commonDAO.list("las.board.getOpinionAcceptanceUser", vo);
	}
	
	/**
	 * 참여자 리스트 상세조회
	 * @param  vo OpinionAcceptanceVO
	 * @return List
	 * @throws Exception
	 */
	public List listOpinionAcceptanceUser(OpinionAcceptanceVO vo) throws Exception {		
		return commonDAO.list("las.board.listOpinionAcceptanceUser", vo);
	}
	
	/**
	 * 과제 참여자 등록
	 * @param  vo OpinionAcceptanceVO
	 * @return int
	 * @throws Exception
	 */
	private int saveOpinionAcceptanceUser(OpinionAcceptanceForm form, OpinionAcceptanceVO vo) throws Exception {
		
		try {
			
			int part_member_cnt = 0;		

			String userEpid = StringUtil.nvl(form.getUserEpid(), "");
			String userSingleId = StringUtil.nvl(form.getUserSingleId(), "");
			String userEmail = StringUtil.nvl(form.getUserEmail(), "");
			String userName = StringUtil.nvl(form.getUserName(), "");
			String userDeptNm = StringUtil.nvl(form.getUserDeptNm(), "");
			String userJikupNm = StringUtil.nvl(form.getUserJikupNm(), "");
			String userCompNm = StringUtil.nvl(form.getUserCompNm(), "");
			
			ArrayList<String> array = new ArrayList<String>();  //참여자 userEpid를 저장할 배열
			ArrayList<String> array2 = new ArrayList<String>(); //참여자 userSingleId를 저장할 배열
			ArrayList<String> array3 = new ArrayList<String>(); //참여자 userEmail를 저장할 배열
			ArrayList<String> array4 = new ArrayList<String>(); //참여자 userName를 저장할 배열
			ArrayList<String> array5 = new ArrayList<String>(); //참여자 userDeptNm를 저장할 배열
			ArrayList<String> array6 = new ArrayList<String>(); //참여자 userJikupNm를 저장할 배열
			ArrayList<String> array7 = new ArrayList<String>(); //참여자 userCompNm를 저장할 배열
			
			if(!"".equals(userEpid)){
				StringTokenizer sUserEpid = new StringTokenizer(userEpid, ",");			//참여자 userEpid 단어 추출
				StringTokenizer sUserSingleId = new StringTokenizer(userSingleId, ",");	//참여자 userSingleId 단어 추출
				StringTokenizer sUserEmail = new StringTokenizer(userEmail, ",");		//참여자 userEmail 단어 추출
				StringTokenizer sUserName = new StringTokenizer(userName, ",");			//참여자 userName 단어 추출
				StringTokenizer sUserDeptNm = new StringTokenizer(userDeptNm,",");		//참여자 userDeptNm 단어 추출
				StringTokenizer sUserJikupNm = new StringTokenizer(userJikupNm, ",");	//참여자 userJikupNm 단어 추출
				StringTokenizer sUserCompNm = new StringTokenizer(userCompNm, ",");		//참여자 userCompNm 단어 추출
				
				while(sUserEpid.hasMoreElements()){
					array.add(StringUtil.bvl(sUserEpid.nextToken(),""));    	//추출된 관련인 Type을 배열에 넣기
					array2.add(StringUtil.bvl(sUserSingleId.nextToken(),"")); 	
					array3.add(StringUtil.bvl(sUserEmail.nextToken(),"")); 	
					array4.add(StringUtil.bvl(sUserName.nextToken(),"")); 		
					array5.add(StringUtil.bvl(sUserDeptNm.nextToken(),"")); 
					array6.add(StringUtil.bvl(sUserJikupNm.nextToken(),"")); 
					array7.add(StringUtil.bvl(sUserCompNm.nextToken(),"")); 	
				}
				
				for(int j=0; j<array.size(); j++)
				{
					vo.setUserEpid(array.get(j));
					vo.setUserSingleId(array2.get(j));
					vo.setUserEmail(array3.get(j));
					vo.setUserName(array4.get(j));
					vo.setUserDeptNm(array5.get(j));
					vo.setUserJikupNm(array6.get(j));
					vo.setUserCompNm(array7.get(j));
					
					part_member_cnt += insertOpinionAcceptanceUser(vo);
				}
			}
			
			return part_member_cnt;
		} catch (Exception e) {
			e.printStackTrace();			
			throw e;
		}	
	}
	
	/**
	 * 권한 조회 (자기 작성 글인지 확인)
	 * @param  vo OpinionAcceptanceVO
	 * @return List
	 * @throws Exception
	 */
	public Boolean authOpinionAcceptance(OpinionAcceptanceVO vo, String mode) throws Exception{
		
		Boolean authYN = false;
		List list = null;
		
		if("REPLY".equals(mode)){
			list = commonDAO.list("las.board.authOpinionAcceptanceReply", vo);
		} else {
			list = commonDAO.list("las.board.authOpinionAcceptance", vo);
		}		
		
		if(list!=null && list.size() > 0)
			authYN = true;
		
		
		return authYN;
	}
	
	/**
	 * 타법인 참여 대상에게 메일 발송
	 * @param vo ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	public int sendOpinionRequestInformMail(OpinionAcceptanceVO vo) throws Exception {
		try {
			
			int sent_cnt = 0;

			ListOrderedMap lom					= null;

			String mainTitle						= "";
			String contents							= "";
			String user_mail = "";
			String today = DateUtil.dateIn(DateUtil.today());
			
			HashMap<String,String> hm = new HashMap<String,String>();

			MailVO mailVo = new MailVO();
			
			//모듈아이디 및 misId 세팅 -> (각 시스템/업무별로 개별 Key값을 입력)
		
			String moduleId = vo.getSys_cd();
			String misId = EsbUtil.generateMisId("MAIL");
			
			Locale locale1 = new Locale((String)vo.getSession_user_locale() );
		
			//메시지 처리
			mainTitle	= (String)messageSource.getMessage("las.msg.field.opnion.sendmail001", null, locale1); // [SELMS+]A Request for Comments has been submitted.
			contents	= (String)messageSource.getMessage("las.msg.field.opnion.sendmail004", null, locale1)	 // A request for Comments has been submitted by the Requester as above. 
					+ "<BR>" + (String)messageSource.getMessage("las.msg.field.opnion.sendmail005", null, locale1);  // Check and answer the request in SELMS+. 
			
			mailVo.setModule_id(moduleId);
			mailVo.setMis_id(misId);
			mailVo.setMsg_key("11");
			mailVo.setSubject(mainTitle);
			//보내는 사람
			//mailVo.setSender_mail_addr(vo.getSession_email());
			//mailVo.setSender_single_id(vo.getSession_single_id());
			mailVo.setSender_single_id(propertyService.getProperty("clms.admin.id"));
			mailVo.setSender_mail_addr(propertyService.getProperty("clms.admin.email"));
			
			//로케일, 인코딩 설정, Time Zone
			mailVo.setLocale(vo.getSession_user_locale());
			mailVo.setEncoding("utf-8");
			mailVo.setTime_zone("GMT+0");
			mailVo.setIs_dst("false");
			
			//메일 대상자 조회
			List user_list = listOpinionAcceptanceUser(vo);
			
			if(user_list != null && user_list.size() > 0 ) {
				
				int mail_cnt = user_list.size();
				
				//받는 사람
				String[] iseq_ids  = null;
				String[] rec_types = null;
				String[] rec_addrs = null;
				
				iseq_ids  = new String[mail_cnt];
				rec_types = new String[mail_cnt];
				rec_addrs = new String[mail_cnt];
	
				for (int i = 0; i < user_list.size(); i++) {
					lom 		= (ListOrderedMap)user_list.get(i);
					user_mail 	= (String)lom.get("EMAIL");
					
					//STATUS 값 설정 - Default "0"
					mailVo.setStatus("1");
					
					//iseq_ids.push("12");
					iseq_ids[i]  = "1";
					rec_types[i] = "t";
					rec_addrs[i] = user_mail;				
			        hm.put("main_title",mainTitle);
			        hm.put("main_link", propertyService.getProperty("secfw.url.domain")+"/login.do" ); 

			        hm.put("mainTitle", mainTitle);	// 메일 타이틀
			        hm.put("subject", (String)vo.getTitle());	// 의견 수렴 과제 명
			        hm.put("requester", (String)vo.getSession_user_nm());	// 요청자
			        hm.put("date", today);		//발송날짜
			        hm.put("contents", contents);		//내용

					String contentHtml = this.getMailContent(hm);
				   
					mailVo.setBhtml_content_check("true");
					mailVo.setIseq_ids(iseq_ids);
					mailVo.setRec_types(rec_types);
				    mailVo.setRec_addrs(rec_addrs);
				    mailVo.setBody(contentHtml.toString());
				} //end for
				
			    if(user_mail != null && !"".equals(user_mail)) {
				    /*********************************************************
					 * 메일 내역 등록
					**********************************************************/
					esbMailService.insertMail(mailVo);
		
					/*********************************************************
					 * 메일전송
					**********************************************************/
					try {
				
						if(esbMailService.sendMail(moduleId, misId))
							sent_cnt = user_list.size();
					
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
			
			return sent_cnt;
			
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
	 * @param HashMap
	 * @return String
	 * @throws Exception
	 */
	private String getMailContent(HashMap<String,String> hm) throws Exception{
		
		String content= "";
		StringBuffer topHtml =  new StringBuffer();	
		StringBuffer bottomHtml = new StringBuffer();
		String contHtml = "";
		
		String last_locale = StringUtil.bvl((String)hm.get("last_locale"), "en");
		
		Locale locale1 = new Locale(last_locale);
		
		String strUrl = "http://"+propertyService.getProperty("secfw.url.lasdomain");		

		String main_title =  (String)hm.get("main_title");
		String subject =  (String)hm.get("subject");
		String requester =  (String)hm.get("requester");		
		String request_date =  (String)hm.get("date");	
		String contents =  (String)hm.get("contents");
		
		contHtml += "<table class='list_basic mt20'>";
		contHtml += "<colgroup>";
		contHtml += "<col width='14%' />";
		contHtml += "<col width='36%'/>";
		contHtml += "<col width='14%' />";
		contHtml += "<col width='36%'/>";
		contHtml += "</colgroup>";
		contHtml += "<tr><th>"+(String)messageSource.getMessage("las.msg.field.opnion.sendmail002", null, locale1)+"</th><td colspan='3'>" + subject + "</td></tr>";
		contHtml += "<tr><th>"+(String)messageSource.getMessage("clm.page.field.contract.request.user", null, locale1)+"</th><td colspan='3'>" + requester + "</td></tr>";
		contHtml += "<tr><th>"+(String)messageSource.getMessage("las.msg.field.opnion.sendmail003", null, locale1)+"</th><td colspan='3'>" + request_date+ "</td></tr>";
		contHtml += "<tr class='end'><th>"+(String)messageSource.getMessage("clm.page.field.admin.subject.detail", null, locale1)+"</th><td colspan='3'>" + contents + "</td></tr>";
		contHtml += "</table>";
		
		//상단 구성
		topHtml.append("<!DOCTYPE html>");
		topHtml.append("<html>");
		topHtml.append("<head>");
		topHtml.append("<meta charset='utf-8' />");
	//	topHtml.append("<meta http-equiv='X-UA-Compatible' content='IE=8; IE=9' />");

		topHtml.append("<title>"+(String)messageSource.getMessage("clm.main.title", null, locale1)+"</title>");
	
		topHtml.append("<link href='"+strUrl+"/style/las/"+locale1+"/mail.css' type=\"text/css\" rel=\"stylesheet\">");
		topHtml.append("<link href='"+strUrl+"/style/las/"+locale1+"/las.css' type=\"text/css\" rel=\"stylesheet\">");
		topHtml.append("<script language=\"javascript\" src=\""+strUrl+"/script/clms/common.js\" type=\"text/javascript\"></script>");
		//topHtml.append("<!--[if IE]> <script src=\"http://html5shiv.googlecode.com/svn/trunk/html5.js\"></script> <![endif]-->");
		topHtml.append("</head>");
		
		topHtml.append("<body>");
		topHtml.append("<div class=\"mailWrap\">");
		topHtml.append("<div class=\"mail_top\"></div>");
		topHtml.append("<div class=\"mail_mid\">");	
		
		//제목
		topHtml.append("<DIV class=page_list>");
		topHtml.append("<DIV class=in><span>" + main_title + "</span></DIV>");
		topHtml.append("</DIV>");		
		
		// 하단 SELMS+ 바로가기
		
		String slasDomain = (String)propertyService.getProperty("secfw.url.domain");
		String pageLink	= (String)messageSource.getMessage("las.mail.field.lawconsultImpl.sysLink", null, locale1);  //  SELMS+ 바로가기
		
		bottomHtml.append("<div class='tC mt20'>");
		bottomHtml.append("<span class=\"btn_mail_gosys\"><a href=\"" + slasDomain +  "\" target=_blank>"+pageLink+"</a></span>");
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

}
