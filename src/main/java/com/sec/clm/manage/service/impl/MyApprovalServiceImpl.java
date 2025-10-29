/**
* Project Name : 계약관리시스템
* File Name :ConsultationServiceImpl.java
* Description : 체결품의ServiceImpl
* Author : 심주완
* Date : 2011.09.16
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;

import java.util.Locale;

import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.manage.dto.MyApprovalVO;
import com.sec.clm.manage.service.MailSendService;
import com.sec.clm.manage.service.MyApprovalService;
import com.sec.common.clmsfile.dto.ClmsFileVO;
import java.util.Date;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyApprovalServiceImpl extends CommonServiceImpl implements MyApprovalService {

	private MailSendService mailSendService ;

	public void setMailSendService(MailSendService mailSendService) {
		this.mailSendService = mailSendService;
	}
	/**
	 * MyApproval목록조회 
	 * @param  vo MyApprovalVO
	 * @return list
	 * @throws Exception
	 */
	public List listMyApproval(MyApprovalVO vo) throws Exception {
		return commonDAO.list("clm.manage.listMyApproval", vo);
	}
	
	/**
	 * 이력정보조회 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List listMyApprovalHis(MyApprovalVO vo) throws Exception {
		List resultList = null;
		resultList = new ArrayList();
		
		//체결이력
		resultList.add(commonDAO.list("clm.manage.detailMyApprovalContractMaster", vo));
		resultList.add(commonDAO.list("clm.manage.listConclusionDelay", vo));
		
		//보류이력
		resultList.add(commonDAO.list("clm.manage.orgmng.listOrgMngHis", vo)); //2012-07-20 추가. history 가져옴
		resultList.add(commonDAO.list("clm.manage.orgmng.detailOrgMngHis", vo)); //2012-07-20 추가. history 가져옴

		return resultList;
	}
	
	/**
	 * MyApproval정보등록 
	 * @param  vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyMyApproval(MyApprovalVO vo) throws Exception {
		
		//원본관리 메모 XSS 처리
		vo.setOrg_acpt_dlay_cause(StringUtil.replace(vo.getOrg_acpt_dlay_cause(), "\r\n", "<br>"));
		vo.setOrg_acpt_dlay_cause(StringUtil.convertHtmlTochars(vo.getOrg_acpt_dlay_cause()));
		vo.setOrg_acpt_dlay_cause(StringUtil.replace(vo.getOrg_acpt_dlay_cause(),"&lt;br&gt;", "<br>"));
		
		int result = 0;		

		if("C02642".equals(vo.getDepth_status())) {
			
			//계약마스터테이블 수정
			vo.setOrg_acptday(vo.getOrg_acptday().replace("-", ""));
			vo.setCntrt_cnclsnday(StringUtil.convertHtmlTochars(vo.getCntrt_cnclsnday().replace("-",""))); 	//계약체결일
			
			List cnclsndayList = null;
			ListOrderedMap cnclsndayLom = null;
			String str = "";
			
			cnclsndayList = commonDAO.list("clm.manage.getCntrtCnclsnday", vo);
			cnclsndayLom = (ListOrderedMap)cnclsndayList.get(0);
			str = (String)cnclsndayLom.get("cntrt_cnclsnday");
			
			//계약체결일이 변경되지 않았을 시
			if(str.equals(vo.getCntrt_cnclsnday())){
				result = commonDAO.modify("clm.manage.modifyMyApprovalCntrtStatus", vo);
			}
			//계약체결일이 변경되었을 시
			else{
				result = commonDAO.modify("clm.manage.modifyMyApprovalCntrtStatus2", vo);
			}
			
			//의뢰테이블 수정
			vo.setPrgrs_status("C04218");
			result = commonDAO.modify("clm.manage.modifyConclusionReqStatus", vo);
			
			//계약등록 소요시간(LeadTime)입력 : (체결본 등록 ~ 원본접수 완료 시점)
			commonDAO.modify("clm.manage.modifyDeptCnsdLeadTimeReg", vo);
			
			ListOrderedMap lom 	= null;
			String fileAdd = ""; 
			
			HashMap<String, Serializable> hm = new HashMap<String, Serializable>();
			hm.put("cntrt_id", vo.getCntrt_id());
			hm.put("cnsdreq_id", vo.getCnsdreq_id());		//Sungwoo added 2014-06-25
			hm.put("reqman_info", vo.getSession_user_nm()+"/"+vo.getSession_jikgup_nm()+"/"+vo.getSession_dept_nm()); //Sungwoo Added 2014-06-24
			List list = commonDAO.list("clm.manage.selectCntrtNo", hm);
			if (list != null && list.size() > 0) {
				lom = (ListOrderedMap)list.get(0);
				
				//연도 4자리중 2자리만 넣도록 수정
				if(vo.getCntrt_cnclsnday() !=null && vo.getCntrt_cnclsnday().length()>=8) fileAdd = vo.getCntrt_cnclsnday().substring(2);
				fileAdd = fileAdd + "_" + StringUtil.bvl((String)lom.get("auto_cntrd_nm"), "");
			}
			
			/*************************************************
			 * 첨부파일 저장
			 *************************************************/
			ClmsFileVO fvo = new ClmsFileVO();

			fvo.setSys_cd(vo.getSys_cd());
			fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
			fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
			fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
			fvo.setRef_key(vo.getRef_key());
			fvo.setFileInfos(vo.getFileInfos6());
			fvo.setFinalAdd(fileAdd); //날짜4자리_일련번호3자리
			fvo.setReg_id(vo.getSession_user_id());			
			clmsFileService.mngClmsAttachFile(fvo, vo);
			
			Locale locale1 = new Locale((String)vo.getSession_user_locale() );
			
			//담당자에게 승인 메일보내기 Execution - In Progress
			//hm.put("main_title"		, (String)messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, locale1));
			//hm.put("mail_content"	, (String)messageSource.getMessage("clm.page.field.myApproval.modifyMyApproval01", null, locale1));
			
			hm.put("email.subject"		, (String)messageSource.getMessage("selms.email.contract.execution.subject", null, locale1));
			hm.put("main_title"		, (String)messageSource.getMessage("selms.email.contract.execution.contents.title", null, locale1));
			hm.put("mail_content"	, (String)messageSource.getMessage("selms.email.contract.execution.contents.body", null, locale1));
			
			
			
			hm.put("locale"			, locale1);
			hm.put("reciver_list", "review");	//added sungwoo 2014-07-01 receiver 분기 처리
			
			
			mailSendService.sendAcceptInfoMail(hm);
		}
		return result;
	}
	
	/**
	 * 체결목록계약상세정보조회 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List detailMyApprovalContractMaster(MyApprovalVO vo) throws Exception {
		List resultList = null;
		resultList = new ArrayList();
		
		resultList.add(commonDAO.list("clm.manage.detailMyApprovalContractMaster", vo));
		resultList.add(commonDAO.list("clm.manage.listConclusionDelay", vo));
		resultList.add(commonDAO.list("clm.manage.listContract", vo)); //2012-07-17 추가. 의뢰정보를 가져오기 위해...
		
		resultList.add(commonDAO.list("clm.manage.orgmng.detailOrgMngHis", vo)); //2012-07-20 추가. history 가져옴
		resultList.add(commonDAO.list("clm.manage.listConsultationSpecial", vo)); // 12-08-03 특화정보
		resultList.add(commonDAO.list("clm.manage.listConsultationRelation", vo)); // 12-08-03 연관계약
		
		return resultList;
	}
	
	
	/**
	 * 체결목록계약상세정보조회 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List detailMyApproval(MyApprovalVO vo) throws Exception {
		return commonDAO.list("clm.manage.detailMyApproval", vo);
	}
	
	/**
	 * 게약정보수정
	 */
	public int insertOrgMngHis(MyApprovalVO vo) throws Exception {
		
		//원본관리 메모 XSS 처리
		vo.setOrg_acpt_dlay_cause(StringUtil.replace(vo.getOrg_acpt_dlay_cause(), "\r\n", "<br>"));
		vo.setOrg_acpt_dlay_cause(StringUtil.convertHtmlTochars(vo.getOrg_acpt_dlay_cause()));
		vo.setOrg_acpt_dlay_cause(StringUtil.replace(vo.getOrg_acpt_dlay_cause(),"&lt;br&gt;", "<br>"));
		
		int result = 0;
		
		
		//계약금액 콤마제거
		vo.setCntrt_amt(StringUtil.bvl(vo.getCntrt_amt(), "").replaceAll(",", ""));
		
		if(vo.getCntrtperiod_startday() != null){
			vo.setCntrtperiod_startday(StringUtil.removeChar(vo.getCntrtperiod_startday(), '-'));
		}
		if(vo.getCntrtperiod_endday() != null){
			vo.setCntrtperiod_endday(StringUtil.removeChar(vo.getCntrtperiod_endday(),'-'));
		}
		if(vo.getCnclsn_plndday() != null){
			vo.setCnclsn_plndday(StringUtil.removeChar(vo.getCnclsn_plndday(),'-'));
		}
		if(vo.getExprt_notiday() != null){
			vo.setExprt_notiday(StringUtil.removeChar(vo.getExprt_notiday(),'-'));
		}
		if(vo.getOrg_acptday() != null){
			vo.setOrg_acptday(StringUtil.removeChar(vo.getOrg_acptday(),'-'));
		}
		if(vo.getOrg_mng_dt() != null){
			vo.setOrg_mng_dt(StringUtil.removeChar(vo.getOrg_mng_dt(),'-'));
		}
		if(vo.getCntrt_cnclsnday() != null){
			vo.setCntrt_cnclsnday(StringUtil.removeChar(vo.getCntrt_cnclsnday(), '-'));
		}
		if(vo.getSignday() != null){
			vo.setSignday(StringUtil.removeChar(vo.getSignday(), '-'));
		}
		if(vo.getOppnt_signday() != null){
			vo.setOppnt_signday(StringUtil.removeChar(vo.getOppnt_signday(), '-'));
		}
		
		// 2014-05-20 Kevin commented. Following data has not been used since SELMS+ built. It is garbage data.
		/*int max_seqno = getOrgMngHisMaxSeqNo(vo);
		
		//첫번째 이력이라면 현재 계약정보 이력에 저장
		if(max_seqno == 0){
			result = commonDAO.insert("clm.manage.orgmng.insertOrgMngHisFirst", vo);
		}
		result = commonDAO.insert("clm.manage.orgmng.insertOrgMngHis", vo);*/
	    
		List cnclsndayList = null;
		ListOrderedMap cnclsndayLom = null;
		cnclsndayList = commonDAO.list("clm.manage.getCntrtCnclsnday", vo);
		cnclsndayLom = (ListOrderedMap)cnclsndayList.get(0);
		// 2014-05-20 Kevin added [SP_14MAY01]
		String prcs_depth = (String)cnclsndayLom.get("PRCS_DEPTH");
		vo.setPrcs_depth(prcs_depth);		//Sungwoo added 2014-07-09
		
		// Termination step
		if(prcs_depth.equals("C02505")){
			if(vo.getCntrtperiod_startday() != null && vo.getCntrtperiod_startday() != "" && vo.getCntrtperiod_endday() != null && vo.getCntrtperiod_endday() != ""){
				
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				Calendar toDay = Calendar.getInstance();
				toDay.setTime(dateFormat.parse(dateFormat.format(new Date())));
				
				Calendar startDate = Calendar.getInstance();
				startDate.setTime(dateFormat.parse(vo.getCntrtperiod_startday()));
				
				Calendar endDate = Calendar.getInstance();
				endDate.setTime(dateFormat.parse(vo.getCntrtperiod_endday()));
				
				if((startDate.equals(toDay) || startDate.before(toDay)) && (endDate.equals(toDay) || endDate.after(toDay))){
					ClmsFileVO fileVO = new ClmsFileVO();
					fileVO.setRef_key(vo.getCntrt_id());	// REF_KEY
					// Following codes mean Origin copy of the contact.
					fileVO.setFile_bigclsfcn("F012");
					fileVO.setFile_midclsfcn("F01203");
					fileVO.setFile_smlclsfcn("");
					List originCopyList = commonDAO.list("clms.common.getCntrtFileInfo", fileVO);
					if(originCopyList != null && originCopyList.size() > 0){
						vo.setPrcs_depth("C02504");
						vo.setDepth_status("C02662");
					} else {
						vo.setPrcs_depth("C02503");
						vo.setDepth_status("C02642");
					}
				}
			}
		}
		
		result = commonDAO.modify("clm.manage.orgmng.updateOrgMng", vo);
		
		ListOrderedMap lom 	= null;
		String fileAdd = ""; 
		
		HashMap hm = new HashMap();
		hm.put("cntrt_id", vo.getCntrt_id());
		List list = commonDAO.list("clm.manage.selectCntrtNo", hm);
		if (list != null && list.size() > 0) {
			lom = (ListOrderedMap)list.get(0);
			
			//연도 4자리중 2자리만 넣도록 수정
			if(vo.getCntrt_cnclsnday() !=null && vo.getCntrt_cnclsnday().length()>=8) fileAdd = vo.getCntrt_cnclsnday().substring(2);
			fileAdd = fileAdd + "_" + StringUtil.bvl((String)lom.get("auto_cntrd_nm"), "");
		}
		
		/*************************************************
		 * 첨부파일 저장
		 *************************************************/
		ClmsFileVO fvo = new ClmsFileVO();

		fvo.setSys_cd(vo.getSys_cd());
		fvo.setFile_bigclsfcn(vo.getFile_bigclsfcn());
		fvo.setFile_midclsfcn(vo.getFile_midclsfcn());
		fvo.setFile_smlclsfcn(vo.getFile_smlclsfcn());
		fvo.setRef_key(vo.getRef_key());
		fvo.setFileInfos(vo.getFileInfos6());
		fvo.setFinalAdd(fileAdd); //날짜4자리_일련번호3자리
		fvo.setReg_id(vo.getSession_user_id());			
		clmsFileService.mngClmsAttachFile(fvo, vo);
		
		
		//권한요청 삭제 - 관련자
		commonDAO.delete("clm.manage.deleteContractAuthreq", vo);
		//관련자 등록
			if(vo.getArr_trgtman_id() != null){
			
			for(int j=0; j<vo.getArr_trgtman_id().length; j++){
				String[] arrTmpId 		= vo.getArr_trgtman_id();
				String[] arrTmpNm 		= vo.getArr_trgtman_nm();
				String[] arrTmpDeptNm 	= vo.getArr_trgtman_dept_nm();
				String[] arrTmpJikgupNm = vo.getArr_trgtman_jikgup_nm();
				
				vo.setMaster_cntrt_id(vo.getCntrt_id());	
				vo.setDemnd_seqno(Integer.toString(j+1));
				vo.setDemnd_gbn("C04601");
				vo.setDemndman_id(vo.getSession_user_id());	//요청자 아이디
				vo.setDemndman_nm(vo.getSession_user_nm());
				vo.setDemndman_dept_nm(vo.getSession_dept_nm());			
				vo.setTrgtman_id(arrTmpId[j]);  			//
				vo.setTrgtman_nm(arrTmpNm[j]);				
				vo.setTrgtman_dept_nm(arrTmpDeptNm[j]);
				vo.setTrgtman_jikgup_nm(arrTmpJikgupNm[j]);
				vo.setDemnd_status("C03702");
				
				commonDAO.insert("clm.manage.insertContractAuthreq",vo);
			}
		}
		
		Locale locale1 = new Locale((String)vo.getSession_user_locale() );
			
		//담당자에게 승인 메일보내기
		//메시지 처리 - 계약 정보가 수정되었습니다.
		String mainTitle 	= (String)messageSource.getMessage("clm.page.field.myApproval.insertOrgMngHis01", null, locale1);
		//메시지 처리 - 계약 정보가 수정되었습니다. <br>수정된 사항을 확인하시기 바랍니다.
		String mailContents = (String)messageSource.getMessage("clm.page.field.myApproval.insertOrgMngHis02", null, locale1);
	
		HashMap map = new HashMap();		
		map.put("receiver_id"	, vo.getReqman_id());
		map.put("main_title"	, mainTitle);

		String[] amsg = {vo.getReq_title(), mailContents};
		//map.put("mail_content"	, "의뢰명["+vo.getReq_title()+"]의 "+mailContents);
		map.put("mail_content"	, (String)messageSource.getMessage("clm.page.field.myApproval.insertOrgMngHis03", amsg, locale1));
		map.put("mail_type"		, "etc");	
		map.put("locale", locale1);
		mailSendService.sendMailAfterApproval(map);
		
		return result;
		
	}
	
	/**
	 * 원본출납관리
	 */
	public int insertOrgMngHis2(MyApprovalVO vo) throws Exception{
		
		//원본관리 메모 XSS 처리
		vo.setOrg_acpt_dlay_cause(StringUtil.replace(vo.getOrg_acpt_dlay_cause(), "\r\n", "<br>"));
		vo.setOrg_acpt_dlay_cause(StringUtil.convertHtmlTochars(vo.getOrg_acpt_dlay_cause()));
		vo.setOrg_acpt_dlay_cause(StringUtil.replace(vo.getOrg_acpt_dlay_cause(),"&lt;br&gt;", "<br>"));
		
		int result = 0;
		int max_seqno = getOrgMngHisMaxSeqNo(vo);
		
		//계약금액 콤마제거
		vo.setCntrt_amt(StringUtil.bvl(vo.getCntrt_amt(), "").replaceAll(",", ""));
		
		if(vo.getCntrtperiod_startday() != null){
			vo.setCntrtperiod_startday(StringUtil.removeChar(vo.getCntrtperiod_startday(), '-'));
		}
		if(vo.getCntrtperiod_endday() != null){
			vo.setCntrtperiod_endday(StringUtil.removeChar(vo.getCntrtperiod_endday(),'-'));
		}
		if(vo.getCnclsn_plndday() != null){
			vo.setCnclsn_plndday(StringUtil.removeChar(vo.getCnclsn_plndday(),'-'));
		}
		if(vo.getExprt_notiday() != null){
			vo.setExprt_notiday(StringUtil.removeChar(vo.getExprt_notiday(),'-'));
		}
		if(vo.getOrg_acptday() != null){
			vo.setOrg_acptday(StringUtil.removeChar(vo.getOrg_acptday(),'-'));
		}
		if(vo.getOrg_mng_dt() != null){
			vo.setOrg_mng_dt(StringUtil.removeChar(vo.getOrg_mng_dt(),'-'));
		}

		
		//첫번째 이력이라면 현재 계약정보 이력에 저장
		if(max_seqno == 0){
			result = commonDAO.insert("clm.manage.orgmng.insertOrgMngHisFirst", vo);
		}

		result = commonDAO.insert("clm.manage.orgmng.insertOrgMngHis", vo);
		
		return result;
	}
	
	public int getOrgMngHisMaxSeqNo(MyApprovalVO vo) throws Exception{
		List result = null;
		ListOrderedMap lom = null;
		int max_seqno = 0;
		
		result = commonDAO.list("clm.manage.orgmng.getOrgMngHisSeqno", vo);
		lom = (ListOrderedMap)result.get(0);
		max_seqno = Integer.parseInt(String.valueOf(lom.get("seqno")));
		
		return max_seqno;
	}
	
	public String getCntrtCnclsnday(MyApprovalVO vo) throws Exception {
		List result = null;
		ListOrderedMap lom = null;
		String str = "";
		
		result = commonDAO.list("clm.manage.getCntrtCnclsnday", vo);
		lom = (ListOrderedMap)result.get(0);
		str = StringUtil.bvl(String.valueOf(lom.get("cntrt_cnclsnday")),"");
		
		return str;
	}
	
	public ListOrderedMap printMyApproval(MyApprovalVO vo) throws Exception {
		
		List resultList = null;
		ListOrderedMap lom = null;
		
		resultList = commonDAO.list("clm.manage.printMyApproval",vo);
		lom = (ListOrderedMap) resultList.get(0);
		
		return lom;
	}
	
	/**
	 * 권한요청자 - 관련자 select
	 * @return
	 * @throws Exception
	 */
	public List listContractAuthreq(ConsultationVO vo) throws Exception{
		return commonDAO.list("clm.manage.listContractAuthreq", vo);
	}	
}