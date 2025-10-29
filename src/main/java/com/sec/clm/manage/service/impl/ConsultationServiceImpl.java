/**
* Project Name : 계약관리시스템
* File Name :ConsultationServiceImpl.java
* Description : 체결품의ServiceImpl
* Author : 심주완
* Date : 2011.09.16
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Locale;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.EsbUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.singleIF.dto.MailVO;
import com.sds.secframework.singleIF.service.EsbMailService;
import com.sds.secframework.singleIF.service.EsbOrgService;


import com.sec.clm.manage.dto.CompletionVO;
import com.sec.clm.manage.dto.ConsultationVO;
import com.sec.clm.manage.dto.ConsultationSpecialVO;
import com.sec.clm.manage.dto.ConsultationExecVO;
import com.sec.clm.manage.service.ConsultationService;
import com.sec.clm.manage.service.MailSendService;
import com.sec.common.clmsfile.dto.ClmsFileVO;

public class ConsultationServiceImpl extends CommonServiceImpl implements ConsultationService {
	
	private MailSendService mailSendService ;

	public void setMailSendService(MailSendService mailSendService) {
		this.mailSendService = mailSendService;
	}
	
	private EsbMailService esbMailService ;

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
	 * 체결목록조회 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List listConsultation(ConsultationVO vo) throws Exception {
		return commonDAO.list("clm.manage.listConsultation", vo);
	}
	
	/**
	 * 체결품의에서 회신상태로 프로세스 복원
	 * @param  vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	public int cancelConsultation(ConsultationVO vo) throws Exception {
		vo.setCnsdreq_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCnsdreq_id(),"")));
		return commonDAO.modify("clm.manage.cancelConsultation", vo);
	}
	
	
	// 2015-01-09 Kevin added.
	// 개발인지, 운영인지 구분하기 위해.
	private static PropertyService propertyService = null;
	public void setPropertyService(PropertyService propertyService){
		this.propertyService = propertyService;
	}
	
	/**
	 * 체결정보등록 
	 * @param  vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	//public int modifyConsultation(ConsultationVO vo, ConsultationExecVO execVo, ConsultationSpecialVO specialVo) throws Exception {
	public int modifyConsultation(ConsultationVO vo, ConsultationExecVO execVo) throws Exception {
		 
		int result = 0;
		//체결정보입력(TN_CLM_CONTRACT_MASTER)-Update
		// 2014-01-31 Kevin. 계약 만료 알림 값이 설정 됐을 경우에만 할당하고, 아니면 null.
		if(vo.getExprt_notiday() != null && vo.getExprt_notiday() != ""){
			vo.setExprt_notiday(StringUtil.convertHtmlTochars(vo.getExprt_notiday().replace("-", "")));
		}
		else{
			vo.setExprt_notiday(null);
		}
		vo.setCnclsn_plndday(StringUtil.convertHtmlTochars(vo.getCnclsn_plndday().replace("-", "")));
		vo.setCntrtperiod_startday(StringUtil.convertHtmlTochars(vo.getCntrtperiod_startday().replace("-", ""))); //계약기간 - 시작일
		vo.setCntrtperiod_endday(StringUtil.convertHtmlTochars(vo.getCntrtperiod_endday().replace("-", ""))); //계약기간 - 종료일
		
		vo.setCntrt_trgt_det(StringUtil.convertHtmlTochars(vo.getCntrt_trgt_det())) ; // 계약대상 상세
		vo.setCntrt_untprc_expl(StringUtil.convertHtmlTochars(vo.getCntrt_untprc_expl())) ; // 단가내역 요약
		vo.setEtc_main_cont(StringUtil.convertHtmlTochars(vo.getEtc_main_cont())) ; // 기타주요사항 
		
		if(!"".equals(StringUtil.bvl(vo.getCntrt_amt(), ""))){
			vo.setCntrt_amt(vo.getCntrt_amt().replaceAll(",", "")); //계약기간 콤마 제거
		}else{
			vo.setCntrt_amt("0");
		}
		
		vo.setPshdbkgrnd_purps(StringUtil.convertEnterToBR((StringUtil.convertNamoCharsToHtml(vo.getBody_mime())))); //Cross-site Scripting 방지 처리
		
		vo.setBfhdcstn_apbtday(StringUtil.removeChar(vo.getBfhdcstn_apbtday(), '-'));	//사전품의_승인일
		
		/*************************************************
		 * 첨부파일 저장
		 *************************************************/
		ClmsFileVO fvo = new ClmsFileVO();
		
		fvo.setSys_cd(vo.getSys_cd());
		fvo.setReg_id(vo.getSession_user_id());
		
		//#2 첨부/별첨 첨부파일
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01202");
		//fvo.setFile_smlclsfcn("F0120209"); 원래 썼었으나 법무시스템에서 최종본회신때 따로 파일첨부하는 기능이 사라졌으므로 의뢰때의 파일을 씀
		fvo.setFile_smlclsfcn("F0120208");
		fvo.setRef_key(vo.getCntrt_id()+"@" + vo.getCnsdreq_id());
		fvo.setFileInfos(vo.getFileInfos5());
		clmsFileService.mngClmsAttachFile(fvo, vo);
		
		//#3 기타_체결본 첨부파일
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01202");
		fvo.setFile_smlclsfcn("F0120212");
		fvo.setRef_key(vo.getCntrt_id()+"@" + vo.getCnsdreq_id());
		fvo.setFileInfos(vo.getFileInfos2());
		clmsFileService.mngClmsAttachFile(fvo, vo);
		
		//#4 단가 첨부파일
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01202");
		fvo.setFile_smlclsfcn("F0120211");
		fvo.setRef_key(vo.getCntrt_id());
		fvo.setFileInfos(vo.getFileInfos4());
		clmsFileService.mngClmsAttachFile(fvo, vo);
		
		//#5 사전검토정보 첨부파일
		fvo.setFile_bigclsfcn("F012");
		fvo.setFile_midclsfcn("F01201");
		fvo.setFile_smlclsfcn("F0120101");
		fvo.setRef_key(vo.getCntrt_id());
		fvo.setFileInfos(vo.getFileInfos3());
		clmsFileService.mngClmsAttachFile(fvo, vo);
		
		//임시저장 버튼을 눌렀을 시에만 의뢰건 기준으로 상태를 변경한다.
		if("button".equals(vo.getTempsave_flag())){
			result = commonDAO.modify("clm.manage.modifyConsultation", vo); //계약 마스터 수정
		 
		}else{ //tab 클릭시에는 상태를 변경하지 않는다.
			result = commonDAO.modify("clm.manage.modifyConsultationByTab", vo); //계약 마스터 수정
		}
		
		result = commonDAO.delete("clm.manage.deleteConsultationExec", execVo);  //이행정보 삭제
		
		//이행정보등록(TN_CLM_CONT_EXECINFO)-delete
		String[] exec_cont_arr = execVo.getExec_cont_arr();
		String[] exec_plndday_arr = execVo.getExec_plndday_arr();
		String[] exec_gbn_arr	= execVo.getExec_gbn_arr();
		String[] exec_item_arr	= execVo.getExec_itm_arr();
		String[] exec_amt_arr	= execVo.getExec_amt_arr();
		
		if(exec_cont_arr != null) {
			for(int i=0; i < exec_cont_arr.length; i++){
			
				execVo.setCntrt_id(execVo.getCntrt_id());
				execVo.setExec_status("C03102");
				execVo.setExec_cont(StringUtil.convertHtmlTochars(exec_cont_arr[i])) ; // 조건
				execVo.setExec_plndday(exec_plndday_arr[i].replace("-", ""));
				execVo.setExec_gbn(exec_gbn_arr[i]);
				execVo.setExec_itm(StringUtil.convertHtmlTochars(exec_item_arr[i])); // 이행항목
				execVo.setExec_amt(new BigDecimal(StringUtil.ltrim(exec_amt_arr[i].replaceAll(",", ""))));
				execVo.setExec_cmpltday("");
				execVo.setNote("");
			
				result = commonDAO.insert("clm.manage.insertConsultationExec", execVo); //이행정보 등록		
			}
		}

		result = this.modifyAuthReqClient(vo.getAuthreq_client(), vo.getCntrt_id(), vo.getSession_user_id(), vo.getSession_user_nm(), vo.getSession_dept_nm());
		
		return result;
	}
	
	/**
	 * 체결목록상세조회 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List detailConsultation(ConsultationVO vo) throws Exception {
		return commonDAO.list("clm.manage.detailConsultation", vo);
	}
	
	/**
	 * 체결목록상세조회 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List detailOnlyConsultation(ConsultationVO vo) throws Exception {
		return commonDAO.list("clm.manage.detailOnlyConsultation", vo);
	}
	
	/**
	 * 체결목록계약정보조회 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List listConsultationContract(ConsultationVO vo) throws Exception {
		return commonDAO.list("clm.manage.listConsultationContract", vo);
	}
	
	
	/**
	 * 체결목록계약상세정보조회 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List detailConsultationContractMaster(ConsultationVO vo) throws Exception {
		return commonDAO.list("clm.manage.detailConsultationContractMaster", vo);
	}
	
	
	/**
	 * 2014-09-09 Kevin declared @Deprecated.
	 * 체결목록연관계약정보조회 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	@Deprecated
	public List listConsultationRealtion(ConsultationVO vo) throws Exception {
		return commonDAO.list("clm.manage.listConsultationRelation", vo);
	}
	
	/**
	 * 2014-09-09 Kevin created.
	 * This is return data set of related contact.
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listConsultationRealtion2(ConsultationVO vo) throws Exception{
		return commonDAO.list("clm.manage.listConsultationRelation2", vo);
	}
	
	
	/**
	 * 체결목록특화정보조회 
	 * @param  vo ConsultationSpecialVO
	 * @return list
	 * @throws Exception
	 */
	public List listConsultationSpecial(ConsultationSpecialVO specialVo) throws Exception {
		return commonDAO.list("clm.manage.listConsultationSpecial", specialVo);
	}
	
	/**
	 * 체결목록이행정보조회 
	 * @param  vo ConsultationExecVO
	 * @return list
	 * @throws Exception
	 */
	public List listConsultationExec(ConsultationExecVO execVo) throws Exception {
		return commonDAO.list("clm.manage.listConsultationExec", execVo);
	}
	
	/**
	 * 이행정보등록 
	 * @param  vo ConsultationExecVO
	 * @return int
	 * @throws Exception
	 */
	public int insertConsultationExec(ConsultationExecVO vo) throws Exception {
		int result = 0;

		result = commonDAO.delete("clm.manage.deleteConsultationExec", vo);

		String[] exec_cont_arr = vo.getExec_cont_arr();
		String[] exec_plndday_arr = vo.getExec_plndday_arr();
		
		if(exec_cont_arr != null) {
			
			for(int i=0; i < exec_cont_arr.length; i++){
			
				vo.setCntrt_id(vo.getCntrt_id());
				vo.setExec_status("C03101");
				vo.setExec_cont(exec_cont_arr[i]);
				vo.setExec_plndday(exec_plndday_arr[i].replace("-", ""));
				
				result = commonDAO.insert("clm.manage.insertConsultationExec", vo);
						
			}
		}		
		return result;
	}
	
	/*===========================================
	 * 결재관련서비스시작
	 ============================================*/
	/**
	 * 의뢰정보조회 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List detailConsultationApprovalRequest(ConsultationVO vo) throws Exception {
		return commonDAO.list("clm.manage.detailConsultationApprovalRequest", vo);
	}
	
	/**
	 * 계약마스터정보 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List listConsultationApprovalContract(ConsultationVO vo) throws Exception {
		return commonDAO.list("clm.manage.listConsultationApprovalContract", vo);
	}
	
	/**
	 * 상신성공후 상태정보 Update 
	 * @param  vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyConsultationStatus(ConsultationVO vo) throws Exception {
		//체결정보입력(TN_CLM_CONTRACT_MASTER)-Update
		int result = 0;
		
		// 2015-01-09 Kevin added
		// 개발 서버이면, 상태를 결재 중으로 업데이트 치지 않는다. 요청에 의해, 개발서버에서 결재 상신시 자동 승인 처리 되기 때문.
		String serverDivision = this.propertyService.getProperty("secfw.server.division");
		boolean isDevServer = (serverDivision != null && serverDivision.equals("DEV"));
		if(isDevServer){
			return 1;
		}
		
		//체결품의 상신후 체결품의 확인중으로 상태 변경
		vo.setPrgrs_status("C04214"); //C04213 -> C04214
		result = commonDAO.modify("clm.manage.modifyConsultationReqStatus", vo);
		if(vo.getApproval_cntrt_info()!= null && !"".equals(vo.getApproval_cntrt_info())) {
			String[] cntrtArray = vo.getApproval_cntrt_info().split("#", 0);
			
			for(int i = 0; i < cntrtArray.length; i++) {
				vo.setCntrt_id(cntrtArray[i]);
				vo.setCntrt_status("C02401");
				vo.setPrcs_depth("C02502");
				vo.setDepth_status("C02622"); //C02626 -> C02622
				result = commonDAO.modify("clm.manage.modifyConsultationCntrtStatus", vo);
			}	
		} 	
		
		/* 상신후 소요시간 산정(계약체결 구간)
		 * 해당 계약건의 계약체결(최종본회신일 ~ 체결본등록)에 소요된 시간 (Lead time) 저장 
		 * 통계메뉴 위한 산정
		 * */
		commonDAO.modify("clm.manage.modifyDeptCnsdLeadTimeCnt",vo);
		
		return result;
	}
	
	/**
	 * 체결품의계약상세정보조회 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List detailConsultationContractMasterApproval(ConsultationVO vo) throws Exception {
		return commonDAO.list("clm.manage.detailConsultationContractMasterApproval", vo);
	}
	
	/**
	 * 체결품의 첨부파일목록 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List listConsultationApprovalAttachInfo(ConsultationVO vo) throws Exception {
		return commonDAO.list("clm.manage.listConsultationApprovalAttachInfo", vo);
	}
	
	/**
	 * 체결품의 합의자목록 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List listConsultationAgreeInfo(ConsultationVO vo) throws Exception {
		return commonDAO.list("clm.manage.listConsultationAgreeInfo",vo);
	}
	
	/**
	 * 체결품의 필수 15개 항목 리스트
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List listCheckListAppl(ConsultationVO vo) throws Exception {
        
		vo.setCntrt_id(StringUtil.bvl(vo.getCntrt_id(),""));
		
		if("HQ".equals((String)vo.getSession_comp_cd())){
			vo.setComp_cd("");
		} else {
			vo.setComp_cd(vo.getSession_comp_cd());
		}
		
		
		return commonDAO.list("las.contractmanager.itemCheckResonDBYn", vo);
	}
	
	/**
	 * 재상신/재의뢰상태수정(계약) 
	 * @param  vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyReworkContractStatus(ConsultationVO vo) throws Exception {
		int result = commonDAO.modify("clm.manage.modifyReworkContractStatus", vo);
		return result;
	}
	
	/**
	 * 재상신/재의뢰상태수정(의뢰) 
	 * @param  vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyReworkRequestStatus(ConsultationVO vo) throws Exception {
		int result = commonDAO.modify("clm.manage.modifyReworkRequestStatus", vo);
		return result;
	}
	
	/**
	 * 재의뢰시 상태수정(검토) 
	 * @param  vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyReworkCnsdStatus(ConsultationVO vo) throws Exception {
		int result = commonDAO.modify("clm.manage.modifyReworkCnsdStatus", vo);
		return result;
	}
	
	/**
	 * 재상신시 합의정보삭제 
	 * @param  vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyReworkAgreeInfo(ConsultationVO vo) throws Exception {
		int result = commonDAO.modify("clm.manage.modifyReworkAgreeInfo", vo);
		return result;
	}
	
	/**
	 * 관련자정보 Update
	 * @param  vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyAuthReqClient(String authReqClient, String cntrt_id, String demndman_id, String demndman_nm, String demndman_dept_nm) throws Exception {
		int result = 0;
		String[] arrAuthReqClient = StringUtil.split(authReqClient, "!@#$");
		HashMap<String, String> hmCntrt = new HashMap<String, String>();
		hmCntrt.put("cntrt_id", cntrt_id);
		result = commonDAO.delete("clm.manage.deleteConsultationContractAuthreq", hmCntrt);
		
		/*
		 * 최준영 추가 (2011.12.06)
		 * 
		 * 계약체결에서는 TN_CLM_CONTRACT_AUTHREQ 테이블을 모두 삭제하고 
		 * 다시 재등록할 경우 담당자 변경요청정보까지 삭제되므로 수정함.
		 * 
		 * 쿼리도 같이 수정함
		 * clm.manage.deleteConsultationContractAuthreq
		 * clm.manage.insertConsultationContractAuthreq
		 */
		ConsultationVO csltVo = new ConsultationVO();
		csltVo.setCntrt_id(cntrt_id);

		int maxSeq = 0;

		List<?> resultList = commonDAO.list("clm.manage.maxRequestSeqno", csltVo);
		if(resultList != null && resultList.size()>0){
			ListOrderedMap resultMap = (ListOrderedMap)resultList.get(0);
			//maxSeq = Integer.parseInt(String.valueOf(resultMap.get("max_demnd_seqno")));
			maxSeq = Integer.parseInt(String.valueOf(resultMap.get("max_demnd_seqno")));
		}
		
		for(int i=0; i < arrAuthReqClient.length; i++) {
			String[] arrClientInfo = StringUtil.split(arrAuthReqClient[i],"|");
			HashMap<String, Comparable> hm = new HashMap<String, Comparable>();
			hm.put("cntrt_id", 			cntrt_id);
			//hm.put("demnd_seqno", 		arrClientInfo[0]);
			
			hm.put("demnd_seqno", (i+maxSeq));
			
			hm.put("demndman_id", 		demndman_id);
			hm.put("demndman_nm", 		demndman_nm);
			hm.put("demndman_dept_nm", 	demndman_dept_nm);					
			hm.put("trgtman_id", 		arrClientInfo[1]);
			hm.put("trgtman_nm", 		arrClientInfo[2]);
			hm.put("trgtman_jikgup_nm", arrClientInfo[3]);
			hm.put("trgtman_dept_nm", 	arrClientInfo[4]);
			hm.put("rd_auth", "C03601");
			hm.put("demnd_status", "C03702");
			result = commonDAO.insert("clm.manage.insertConsultationContractAuthreq", hm);
		}
		return result;
	}
	
	/**
	 * 체결보류 
	 * @param  vo ConsultationVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyConsultationStatusDefer(ConsultationVO vo) throws Exception {
		//체결정보입력(TN_CLM_CONTRACT_MASTER)-Update
		int result = 0;
		List<?> resultList = null;
		vo.setPrgrs_status("C04212");
		result = commonDAO.modify("clm.manage.modifyConsultationReqStatus", vo);
		resultList = commonDAO.list("clm.manage.listConsultationContract", vo);
		
		if(resultList != null && resultList.size() > 0) {
			for(int i = 0; i < resultList.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)resultList.get(i);
				vo.setCntrt_id((String)lom.get("cntrt_id"));
				vo.setCntrt_status("C02401");
				vo.setPrcs_depth("C02502");
				vo.setDepth_status("C02627");
				result = commonDAO.modify("clm.manage.modifyConsultationCntrtStatus", vo);
			}
		
		} 	
		return result;
	}
	
	
	/**
	 * 해당의뢰건이 결재 완료 되었을 경우 승인자에게 메일을 발송처리한다.
	 * 의뢰건일경우 첫번째계약건으로 해서 체결예정일,체결여부를 표시한다.
	 * 
	 * 현재 이함수를 호출하는 경우 : 1. 계약체결시 결제완료되어 Registration - Signing 변경시
	 * 
	 * @param  cnsdreq_id(필수값) 		: 의뢰번호       
	 * @param  cntrt_id(Optional 값) 	: 계약번호  <==rc_flag가 C일경우 필수값
	 * @param  rc_flag(필수값) 			: 의뢰번호(R), 계약번호(C)   <==의뢰단위로 DROP하는 경우는 R, 계약단위로 DROP하는 경우는 C로 설정한다.
	 * 					       		참고 : 그러나, 결재후처리에서 호출되는 경우는 의뢰번호만 넘어오지만 계약별로 DROP되도록 처리하기위해
	 * 										쿼리에서 	postproc_yn값으로 계약별로 DROP되도록 처리함									
	 * @param  postproc_yn(필수값) 		: Y (Termination - auto) , NR (Registration Signing)
	 * @return void
	 * @throws Exception
	 */
	public void sendDropReq(ListOrderedMap resultTargetMap) throws Exception {

		try {

			String cntrt_id			= StringUtil.bvl((String)resultTargetMap.get("cntrt_id"),"");	
			String cnsdreq_id		= StringUtil.bvl((String)resultTargetMap.get("cnsdreq_id"),"");	//Sungwoo added 2014-06-17
			String rc_flag 			= ((String)resultTargetMap.get("rc_flag")).trim();
			String sPostproc_yn 	= ((String)resultTargetMap.get("postproc_yn")) == null ? "N" : ((String)resultTargetMap.get("postproc_yn")).trim() ;
			
			HashMap<String, String> targetMap = new HashMap<String, String>();
			targetMap.put("cnsdreq_id", ((String)resultTargetMap.get("cnsdreq_id")).trim());
			if(resultTargetMap.get("cntrt_id")==null) resultTargetMap.put("cntrt_id","");
			targetMap.put("cntrt_id", cntrt_id);
			targetMap.put("rc_flag", ((String)resultTargetMap.get("rc_flag")).trim());
			targetMap.put("postproc_yn", sPostproc_yn);
			//의뢰번호만 파라미터로 받았을경우 
			if(rc_flag.equals("R")){
				//최초 계약건(계약1번) 을 구하기
				ArrayList<?> targetList = (ArrayList<?>)commonDAO.list("common.jobs.getDropMinCntrtid", targetMap);
				ListOrderedMap tempTargetMap = (ListOrderedMap)targetList.get(0);
				targetMap.put("cntrt_id", ((String)tempTargetMap.get("cntrt_id")).trim());
				
			}else if(rc_flag.equals("C") && (cntrt_id==null || (cntrt_id!=null && cntrt_id.length()<5))){
				//rc_flag가 C이면서 cntrt_id값을 입력 안한 경우
				throw new Exception("Error sendDropReq : rc_flag(C), cntrt_id = " + cntrt_id);
			}

			String cntrtRespId						= "";
			String cntrtRespMailAddr				= "";
			 
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			MailVO mailVo = new MailVO();
			CompletionVO vo = new CompletionVO();	//Sungwoo added 2014-06-17

			ListOrderedMap receiverListLom			= null;

			/*********************************************************
			 * 파라미터세팅 : DB에서 해당메일건을 가져오는 로직추가필요
			**********************************************************/
			//모듈아이디 및 misId 세팅 -> (각 시스템/업무별로 개별 Key값을 입력)
			String moduleId = "CLM";
			mailVo.setModule_id(moduleId);
			
			//보내는 사람
			mailVo.setSender_mail_addr(propertyService.getProperty("clms.admin.email") );
			String[] sTempsingle_id = ((String)propertyService.getProperty("clms.admin.email")).split("@");
			 
			mailVo.setSender_single_id(sTempsingle_id[0]);
	
			//로케일, 인코딩 설정, Time Zone
			String defaultLocale = propertyService.getProperty("secfw.defaultLocale");
			mailVo.setLocale(defaultLocale);
			mailVo.setEncoding(StringUtil.bvl(mailVo.getEncoding(),"utf-8"));
			String time_zone = propertyService.getProperty("secfw.mail.default.time_zone");
			mailVo.setTime_zone(time_zone);
			String summerTimeYn = "";
			if("Y".equals(summerTimeYn)) {
				mailVo.setIs_dst("true");
			} else {
				mailVo.setIs_dst("false");
			}

			//STATUS 값 설정 - Default "0"
			mailVo.setStatus("0");
		  
			String[] iseq_ids  = null;
			String[] rec_types = null;
			String[] rec_addrs = null;
			
			String sflag = "FLAG6"; //Drop되는 의뢰건 혹은 계약건에 대해 통보메일
			String misId = "";

			targetMap.put("session_user_locale", defaultLocale);	//added Sungwoo 2014-06-25
			
			ArrayList<?> targetList = (ArrayList<?>)commonDAO.list("common.jobs.getDropReq", targetMap);
			
			if(targetList != null && targetList.size() > 0) {
				for(int i=0; i<targetList.size(); i++){
					ListOrderedMap tempTargetMap = (ListOrderedMap)targetList.get(i);

					resultTargetMap.put("email", ((String)tempTargetMap.get("email")).trim()) ;									//1.email (In Charger)	
					resultTargetMap.put("cntrt_nm", ((String)tempTargetMap.get("cntrt_nm")).trim());							//3.계약명
					resultTargetMap.put("CNTRTPERIOD_STARTDAY", ((String)tempTargetMap.get("CNTRTPERIOD_STARTDAY")).trim());	//4.Contract Start day	Sungwoo Replacement 2014-06-16
					resultTargetMap.put("CNTRTPERIOD_ENDDAY", ((String)tempTargetMap.get("CNTRTPERIOD_ENDDAY")).trim());		//5.Contract End day	Sungwoo Replacement 2014-06-16
					resultTargetMap.put("exec_cont", ((String)tempTargetMap.get("CNTRT_CHGE_DEMND_CAUSE")).trim());				//6.이행내용
					resultTargetMap.put("cntrt_respman_nm", ((String)tempTargetMap.get("cntrt_respman_nm")).trim());			//7.담당자(이름/직급/부서)
					resultTargetMap.put("cntrt_num_nm", ((String)tempTargetMap.get("cntrt_num_nm")).trim());					//8.계약1, 계약2 표시
					resultTargetMap.put("title", ((String)tempTargetMap.get("title")).trim());									//9.품의명

					resultTargetMap.put("prcs_depth_nm", ((String)tempTargetMap.get("prcs_depth_nm")).trim());
					resultTargetMap.put("cntrt_cnclsn_yn", ((String)tempTargetMap.get("cntrt_cnclsn_yn")).trim());
					resultTargetMap.put("cpy_reg_yn", ((String)tempTargetMap.get("cpy_reg_yn")).trim());
					resultTargetMap.put("cntrt_chge_demnd_cause", ((String)tempTargetMap.get("cntrt_chge_demnd_cause")).trim());//사유
					resultTargetMap.put("postproc_yn", sPostproc_yn) ;												//후처리여부 등의  플래그역할
					
					//개별 메일건마다 제목과 내용이 모두 틀리므로 MISID를 건별로 새로 생성한다.  
					misId = EsbUtil.generateMisId("MAIL",i);
					mailVo.setMis_id(misId);

					vo.setCnsdreq_id(cnsdreq_id);
					vo.setCntrt_id(cntrt_id);
					
					//Sungwoo added 2014-06-17 email user list 조회내역대로 발송 변경처리 
					List<?> receiverList = commonDAO.list("clm.manage.reqUserList", vo);

					if(receiverList != null && receiverList.size() > 0 ) {		
						
						int receiver_cnt = receiverList.size();
						
						iseq_ids  = new String[receiver_cnt];
						rec_types = new String[receiver_cnt];
						rec_addrs = new String[receiver_cnt];

						for (int j = 0; j < receiverList.size(); j++) {
							receiverListLom 	= (ListOrderedMap)receiverList.get(j);
							cntrtRespId 		= (String)receiverListLom.get("USER_ID");
							
							//상신자 정보 조회
					        if(cntrtRespId != null && !"".equals(cntrtRespId)) {
					        	cntrtRespMailAddr = esbOrgService.getUserEpMailAddr(cntrtRespId);
					        }
					        
							iseq_ids[j]  = "1";
							rec_types[j] = "t";
							rec_addrs[j] = cntrtRespMailAddr;
							
							mailVo.setIseq_ids(iseq_ids);
							mailVo.setRec_types(rec_types);
							mailVo.setRec_addrs(rec_addrs);
							//BODY TYPE 세팅
							mailVo.setBhtml_content_check("true");
							mailVo.setBody(getMailContent(sflag,resultTargetMap));
//							mailVo.setSubject(messageSource.getMessage("clm.page.field.mailsend.subjectTitle", null, new Locale(resultTargetMap.get("gian_locale").toString())));		//Sungwoo Replacement mail Title 2014-07-16 
							mailVo.setSubject(messageSource.getMessage("selms.email.contract.signing.subject", null, new Locale(resultTargetMap.get("gian_locale").toString())));		//Sungwoo Replacement mail Title 2014-07-16 
		
						}
						if(cntrtRespMailAddr != null && !"".equals(cntrtRespMailAddr)) {
						    /*********************************************************
							 * 메일 내역 등록
							**********************************************************/
							esbMailService.insertMail(mailVo);
						}
					}
				}
			}
			 
		} catch (Exception e) {
        	//getLogger().error( " sendApproval  Exception : " + e);
        	e.printStackTrace();
        } finally{ 

        }
	}
	
	/**
	 * 결재 완료 확인 된 계약건 메일발송처리 
	 * @param  sflag1 String
	 * @param  resultTargetMap ListOrderedMap
	 * @return String
	 * @throws Exception
	 */
	private String getMailContent(String sflag1, ListOrderedMap resultTargetMap){
		String scontent = "";
		String lasdomain = propertyService.getProperty("secfw.url.lasdomain");
		
		Locale locale1 = new Locale((String)resultTargetMap.get("gian_locale"));
		
		//메시지 처리 - [계약체결]
		String main_title = messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, locale1).toString().trim();
		String comment = "";
		String postproc_yn = ((String)resultTargetMap.get("postproc_yn")).trim();
		
		comment = "<b>";
		if (postproc_yn.equals("NR")) {
			//메시지 처리 - [계약등록], 체결품의 완료 후 Registration - Signing
//			comment		+= "&#8729; " + messageSource.getMessage("clm.page.field.consultation.getRegSigningComment1", null, locale1).toString() + "<br/>";
//			comment		+= "&#8729; " + messageSource.getMessage("clm.page.field.consultation.getRegSigningComment2", null, locale1).toString() + "<br/>";
//			comment		+= "&#8729; " + messageSource.getMessage("clm.page.field.consultation.getRegSigningComment3", null, locale1).toString() + "<br/>";
			comment+= messageSource.getMessage("selms.email.contract.signing.contents.body", null, locale1).toString()+ "<br/>";
			main_title=messageSource.getMessage("selms.email.contract.signing.contents.title", null, locale1).toString();
			
		}else{
			//메시지 처리 - 체결품의시 Termination - Terminated
			comment		= messageSource.getMessage("clm.page.field.consultation.getTerminationComment", null, locale1).toString();
		} 
		comment += "</b>";
		HashMap<String, String> hm = new HashMap<String, String>();
		  
		hm.put("mail_type", "24"); 
		//메시지 처리
		hm.put("main_title", main_title);
		//My Contract 목록 화면으로 이동
		hm.put("main_link", "http://"+lasdomain+"/clm/manage/myContract.do?method=listMyContract&menu_id=20110803091536533_0000000045");
		
		hm.put("param1", ((String)resultTargetMap.get("title")).trim());
		hm.put("param2", comment);
	 	 
		try {
			scontent = mailSendService.getMailContent(hm);
		}catch(Exception e){
			
		}
		//공통사용하기위해 아래 내용 사용안함. 
		 
		return scontent;
	}
	
	/**
	 * 결재내역 조회
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List<?> listApprovalHis(ConsultationVO vo) throws Exception {
		return commonDAO.list("clm.manage.listApprovalHis", vo);
	}
	
	/**
	 * 계약유효성조회 
	 * @param  vo ConsultationVO
	 * @return list
	 * @throws Exception
	 */
	public List<?> listApprovalValidation(ConsultationVO vo) throws Exception {
		return commonDAO.list("clm.manage.listApprovalValidation", vo);
	}
	
	/*
	 * TODO : Sungwoo added 2014-09-24 마지막 review 내역 가져오기 위해 임시추가
	 * 추후 Detail 페이지 공통 처리 후 삭제 예정
	*/
	public List<?>listReqContractReview(ConsultationVO vo) throws Exception{
		return commonDAO.list("clm.manage.listReqContractReview", vo);
	}
	
}