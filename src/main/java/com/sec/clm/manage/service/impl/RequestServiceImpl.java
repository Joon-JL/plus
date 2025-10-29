/**
* Project Name : 계약관리시스템
* File Name : RequestServiceImpl.java
* Description : 계약권한요청 ServiceImpl
* Author : 신남원
* Date : 2010.09.27
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.manage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.map.ListOrderedMap;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sec.clm.manage.dto.RequestVO;
import com.sec.clm.manage.service.MailSendService;
import com.sec.clm.manage.service.RequestService;

public class RequestServiceImpl extends CommonServiceImpl implements RequestService {

	private MailSendService mailSendService ;

	public void setMailSendService(MailSendService mailSendService) {
		this.mailSendService = mailSendService;
	}
	/**
	* 의뢰정보 조회
	* 신규신청일 경우 계약검토단계는 의뢰담당자정보 조회
	* @param RequestVO
	* @return ListOrderedMap
	* @throws Exception
	*/
	public ListOrderedMap detailRequest(RequestVO vo) throws Exception {
		ListOrderedMap result = null;
		
		List resultList = commonDAO.list("clm.manage.detailRequest", vo);
		if(resultList != null && resultList.size()>0){
			result = (ListOrderedMap)resultList.get(0);
		}
		
		return result;
	}

	/**
	* 계약정보 조회
	* 신규신청일 경우 계약체결단계부터는 계약담당자정보 조회
	* @param RequestVO
	* @return ListOrderedMap
	* @throws Exception
	*/
	public ListOrderedMap detailCntrt(RequestVO vo) throws Exception {
		ListOrderedMap result = null;
		
		List resultList = commonDAO.list("clm.manage.detailCntrt", vo);
		if(resultList != null && resultList.size()>0){
			result = (ListOrderedMap)resultList.get(0);
		}
		
		return result;
	}

	/**
	* 권한요청정보
	* 신청한 건이나 승인할 건은 권한요청 정보 조회
	* @param RequestVO
	* @param sessionUserId
	* @return List
	* @throws Exception
	*/
	public ListOrderedMap detailAuthReqInfo(RequestVO vo) throws Exception{
		ListOrderedMap result = null;
		
		List resultList = null;
		//의뢰Id로 요청자 조회
		if("C02501".equals(vo.getP_prcs_depth()) || "C02502".equals(vo.getP_prcs_depth())){
			resultList = commonDAO.list("clm.manage.detailAuthReqInfo1", vo);
		//계약ID로 대상자 조회
		}else{
			resultList = commonDAO.list("clm.manage.detailAuthReqInfo2", vo);
		}
		
		if(resultList != null && resultList.size()>0){
			result = (ListOrderedMap)resultList.get(0);
		}
		return result;
	}

	/**
	* 계약권한요청 저장 
	* 
	* @param RequestVO
	* @return int
	* @throws Exception
	*/
	public int insertRequest(RequestVO vo) throws Exception {
		int result = 0;
		
		if("C02501".equals(vo.getP_prcs_depth()) || "C02502".equals(vo.getP_prcs_depth())){//검토, 체결 단계
			//관련 계약 목록 조회
			List resultList = commonDAO.list("clm.manage.listRequestCntrt", vo);
			
			//목록에 의한 권한 정보 저장
			if(resultList != null && resultList.size() > 0){
				for(int i = 0 ; i < resultList.size() ; i++){
					ListOrderedMap lom = (ListOrderedMap)resultList.get(i);
					vo.setCntrt_id((String)lom.get("cntrt_id"));
					vo.setDemnd_seqno(maxDemndSeqno(vo));
					commonDAO.insert("clm.manage.insertRequestAuthreq", vo);
				}
				result = 1;
			}
		}else{//체결단계 이후
			vo.setDemnd_seqno(maxDemndSeqno(vo));
			result = commonDAO.insert("clm.manage.insertRequestAuthreq", vo);
		}
		
		return result;		
	}

	/**
	* 계약권한요청 일련번호 
	* 
	* @param RequestVO
	* @return int
	* @throws Exception
	*/
	private int maxDemndSeqno(RequestVO vo)throws Exception{
		int seqNo = 0;
		
		List resultList = commonDAO.list("clm.manage.maxRequestSeqno", vo);
		if(resultList != null && resultList.size()>0){
			ListOrderedMap result = (ListOrderedMap)resultList.get(0);
			seqNo = ((Integer)result.get("max_demnd_seqno")).intValue();
		}
		
		return seqNo;
	}	
	
	/**
	* 계약권한요청 삭제 - 취소
	* 
	* @param RequestVO
	* @return int
	* @throws Exception
	*/
	public int deleteRequest(RequestVO vo) throws Exception {
		int result = 0;

		if("C02501".equals(vo.getP_prcs_depth()) || "C02502".equals(vo.getP_prcs_depth())){//검토,체결 단계
			//관련 계약 목록 조회
			List resultList = commonDAO.list("clm.manage.listRequestCntrt", vo);
			
			//목록에 의한 권한 정보 저장
			if(resultList != null && resultList.size() > 0){
				for(int i = 0 ; i < resultList.size() ; i++){
					ListOrderedMap lom = (ListOrderedMap)resultList.get(i);
					vo.setCntrt_id((String)lom.get("cntrt_id"));
					commonDAO.delete("clm.manage.deleteRequestAuthreq", vo);
				}
				result = 1;
			}
			
		}else{//체결단계 이후
			result = commonDAO.delete("clm.manage.deleteRequestAuthreq", vo);
		}

		return result;
	}
	
	/**
	* 계약권한요청 수정  - 승인
	* 
	* @param RequestVO
	* @return int
	* @throws Exception
	*/
	public int modifyRequest(RequestVO vo) throws Exception {
		int result = 0;

		if("C02501".equals(vo.getP_prcs_depth()) || "C02502".equals(vo.getP_prcs_depth())){//검토,체결 단계
			//관련 계약 목록 조회
			List resultList = commonDAO.list("clm.manage.listRequestCntrt", vo);
			
			//목록에 의한 권한 정보 저장
			if(resultList != null && resultList.size() > 0){
				for(int i = 0 ; i < resultList.size() ; i++){
					ListOrderedMap lom = (ListOrderedMap)resultList.get(i);
					vo.setCntrt_id((String)lom.get("cntrt_id"));
					//권한요청 승인처리
					commonDAO.modify("clm.manage.modifyRequestAuthreq", vo);
					
					if(vo.getDemnd_status().equals("C03702")) {		// 승인일때만 계약담당자 변경
						//계약마스터 담당자 변경
						commonDAO.modify("clm.manage.modifyContractMasterStatus", vo);
					}
					
				}
				result = 1;
			}
			if(vo.getDemnd_status().equals("C03702")) {		// 승인일때만 의뢰자변경
				//검토의뢰자 변경
				commonDAO.modify("clm.manage.modifyContractCnsdReqStatus", vo);
			}
		}else{//체결단계 이후
			//권한요청 승인처리
			commonDAO.modify("clm.manage.modifyRequestAuthreq", vo);
			if(vo.getDemnd_status().equals("C03702")) {		// 승인일때만 계약담당자 변경
				//의뢰 담당자에게 조회 권한 부여
				insertAuthReq(vo);
				//계약마스터 담당자 변경
				result = commonDAO.modify("clm.manage.modifyContractMasterStatus", vo);
			}
		}	

		return result;
	}
	
	/*
	 * 계약권한요청 등록
	 * 주의 : 요청자와 대상자가 뒤바뀜(쿼리 순서 바꾸어 놓음 )
	 */
	private int insertAuthReq(RequestVO vo) throws Exception {
		int result = 0;
		
		vo.setDemnd_seqno(maxDemndSeqno(vo)); //일련번호
		vo.setDemnd_gbn("C04601");	//요청구분
		vo.setRd_auth("C03601");	//모든권한조회
		vo.setAuth_startday(DateUtil.today());	//권한시작일
		vo.setDemnd_status("C03702");	//요청상태:승인
		String demndCont = messageSource.getMessage("clm.page.alert.manageRequest.demndCont", null, new Locale(vo.getSession_user_locale()));
		String hndlCont = messageSource.getMessage("clm.page.alert.manageRequest.hndlCont", null, new Locale(vo.getSession_user_locale()));
		vo.setDemnd_cont(demndCont);
		vo.setHndl_cont(hndlCont);
		
		commonDAO.insert("clm.manage.insertSearchAuthreq", vo);
		
		
		return result;
	}
	
	/**
	* 계약 담당자 변경 권한 체크
	* @param RequestVO
	* @return boolean
	* @throws Exception
	*/
	public boolean checkChangeAuth(RequestVO vo) throws Exception {
		boolean result = false;
		
		List resultList = commonDAO.list("clm.manage.checkChangeAuth", vo);
		if(resultList != null && resultList.size()>0){
			result = true;
		}
		
		return result;
	}

	/**
	* 계약 담당자 변경을 위한 기본 조회
	* @param RequestVO
	* @return ListOrderedMap
	* @throws Exception
	*/
	public ListOrderedMap detailChangeInfo(RequestVO vo) throws Exception{
		ListOrderedMap result = null;
		
		List resultList = null;
		
		resultList = commonDAO.list("clm.manage.detailChangeInfo", vo);
		
		if(resultList != null && resultList.size()>0){
			result = (ListOrderedMap)resultList.get(0);
		}
		return result;
	}
	
	/**
	* 계약서 담당자변경처리
	* 
	* @param RequestVO
	* @return int
	* @throws Exception
	*/
	public int modifyChange(RequestVO vo) throws Exception {
		int result = 0;
		
		vo.setTrgtman_nm(StringUtil.convertHtmlTochars(vo.getTrgtman_nm()));
		vo.setTrgtman_jikgup_nm(StringUtil.convertHtmlTochars(vo.getTrgtman_jikgup_nm()));
		vo.setTrgtman_dept_nm(StringUtil.convertHtmlTochars(vo.getTrgtman_dept_nm()));
		vo.setDemnd_cont(StringUtil.convertHtmlTochars(vo.getDemnd_cont()));

		if("C02501".equals(vo.getP_prcs_depth()) || "C02502".equals(vo.getP_prcs_depth())){ //검토단계, 체결단계
			//검토의뢰자 변경
			result = commonDAO.modify("clm.manage.modifyContractCnsdReqStatus", vo);
		}

		//담당자변경 이력 기록
		result = commonDAO.insert("clm.manage.insertChangeAuthreq", vo);

		//계약마스터 담당자 변경
		result = commonDAO.modify("clm.manage.changeContractMasterStatus", vo);
		
		Locale locale1 = new Locale((String)vo.getSession_user_locale() );
		
		//메시지 처리 - 상기 계약의 의뢰자(담당자)가 위와 같이 변경되었습니다.<br>향후 본 건에 대한 검토의뢰, 체결품의, 이행관리, 종료관리 등의 업무는<br>변경 후 의뢰자(담당자)만 진행 하실 수 있습니다.
		String mailContents = (String)messageSource.getMessage("clm.page.field.request.modifyChange02", null, locale1);

		HashMap<String, String> hm = new HashMap<String, String>();		
		hm.put("cnsdreq_id"		, vo.getCnsdreq_id());
		hm.put("cntrt_id"		, vo.getCntrt_id());
		hm.put("main_title"		, (String)messageSource.getMessage("clm.page.field.mailsend.requiredTitle", null, locale1));
		hm.put("mail_content"	, mailContents);
		hm.put("respman_info"	, vo.getCntrt_respman_nm());//변경전 담당자
		hm.put("respman_info2"	, vo.getTrgtman_nm()+"/"+vo.getTrgtman_jikgup_nm()+"/"+vo.getTrgtman_dept_nm());//변경후담당자
		hm.put("req_title"		, vo.getReq_title());
		hm.put("cntrt_nm"		, vo.getCntrt_nm());
		hm.put("locale"			, locale1.toString());
		hm.put("mail_type"		, "24");	
		
		//1. 기존담당자에게 메일전송
		hm.put("receiver_id"	, vo.getCntrt_respman_id());
		mailSendService.sendMailAfterApproval(hm);
		
		//2. 변경된 담당자에게 메일전송
		hm.put("receiver_id"	, vo.getTrgtman_id());
		mailSendService.sendMailAfterApproval(hm);
		
		return result;	// 변경에 대한 성공여부는 계약 마스터에 의해 판단하면 됨.
	}
	

}