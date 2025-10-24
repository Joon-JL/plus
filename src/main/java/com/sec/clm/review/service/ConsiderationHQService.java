package com.sec.clm.review.service;

/**
 * Project Name : 법무시스템
 * File Name : SignService.java
 * Description : 날인 Service
 * Author : 박병주
 * Date : 2013.05
 * Copyright : 2013 by SAMSUNG. All rights reserved.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sec.clm.manage.dto.ConsiderationVO;
import com.sec.clm.review.dto.ConsiderationHQVO;
import com.sec.clm.review.dto.ConsultationVO;

public interface ConsiderationHQService {
	
	/**
	 * HQ검토 목록 조회
	 * @param ConsiderationHQVO
	 * @return List
	 * @throws Exception
	 */
	List listConsiderationHQ(ConsiderationHQVO vo) throws Exception;
	
	/**
	 * HQ검토 상세 조회
	 * @param ConsiderationHQVO
	 * @return List
	 * @throws Exception
	 */
	List detailConsiderationHQ(ConsiderationHQVO vo) throws Exception;
		
	/**
	 * HQ 담당자 리스트
	 * @param vo ConsiderationHQVO
	 * @return
	 * @throws Exception
	 */
	List listRespmanHQ(ConsiderationHQVO vo) throws Exception;
	
	/**
	 * 담당자 리스트[팝업]
	 * @param vo ConsiderationHQVO
	 * @return List
	 * @throws Exception
	 */
	List popRespmanHQ(ConsiderationHQVO vo) throws Exception;
	
	/**
	 * 법무담당자 지정
	 * @param  vo ConsiderationHQVO
	 * @return int
	 * @throws Exception
	 */
	int confirmRespmanHQ(ConsiderationHQVO vo) throws Exception;
	
	/**
	 * rtnReturn  처리
	 * @param vo ConsiderationHQVO
	 * @return List
	 * @throws Exception
	 */
	int rtnReturn(ConsiderationHQVO vo) throws Exception;
	
	/**
	 * 임시저장 
	 * @param vo ConsiderationHQVO
	 * @return List
	 * @throws Exception
	 */
	 int tempSave(ConsiderationHQVO vo) throws Exception;
			
	/**
	 * HQ HEAD 회신 
	 * @param vo ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	int hq01RESP(ConsiderationHQVO vo) throws Exception;
	
	
	/**
	 * HQ REVIEWER 회신 
	 * @param vo ConsiderationVO
	 * @return List
	 * @throws Exception
	 */
	int hq02RESP(ConsiderationHQVO vo) throws Exception;
	
	/**
	 * HQ 이력정보 목록을 조회한다. (원시 이력 리스트를 받아서 그 이력 정보를 참조하여 HQ 이력조회 정보와 합친다 ) 
	 * @param  vo ConsiderationHQVO
	 * @return List
	 * @throws Exception
	 */
	List getHqHistory(ArrayList orginHisroyList , ConsiderationHQVO vo) throws Exception;
	
	/**
	 * 메일 발송
	 * @param vo ConsultationVO
	 * @return List
	 * @throws Exception
	 */
	int sendHQMail(ConsiderationHQVO vo, List user_list, String mode ) throws Exception;
		

}