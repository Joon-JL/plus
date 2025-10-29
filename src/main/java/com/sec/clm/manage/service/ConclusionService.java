/**
* Project Name : 계약관리시스템
* File Name :ConclusionService.java
* Description : 체결본등록Service인터페이스
* Author : 심주완
* Date : 2011.09.21
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.service;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sec.clm.manage.dto.ConclusionVO;
import com.sec.clm.manage.dto.ConclusionDelayVO;
import com.sec.clm.manage.dto.ConsultationVO;

public interface ConclusionService {
	//체결본등록
	List listConclusion(ConclusionVO vo) throws Exception;
	//체결본정보등록
	int modifyConclusion(ConclusionVO vo) throws Exception;
	//체결본등록 상세
	List detailConclusion(ConclusionVO vo) throws Exception;
	
	//체결본등록 상세   : C02503 계약등록 만 조회
	List detailOnlyConclusion(ConclusionVO vo) throws Exception;
	
	//체결본등록-계약탭
	List listConclusionContract(ConclusionVO vo) throws Exception;
	//체결본등록-계약마스터
	List detailConclusionContractMaster(ConclusionVO vo) throws Exception;
	
	//체결품의 특화정보이력
	List listConclusionDelay(ConclusionDelayVO specialVo) throws Exception;
	
	//계약Drop
	ListOrderedMap dropDefer(ConsultationVO vo) throws Exception;
	
	// 체결미확인에서 회신상태로 프로세스 복원
	int cancelConclusion(ConsultationVO vo) throws Exception;

	//체결본등록-계약마스터
	List detailConclusionContractMasterNew(ConclusionVO vo) throws Exception;
	
	void modifyConclusionCopy(ConclusionVO vo) throws Exception;
}