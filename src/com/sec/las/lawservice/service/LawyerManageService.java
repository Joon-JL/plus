/**
 * Project Name : 법무시스템
 * File Name : LawyerManageService.java
 * Description : 변호사 관리 Service
 * Author : 박병주
 * Date : 2011.09
 * Copyright : 2011 by SAMSUNG. All rights reserved.
 */
package com.sec.las.lawservice.service;

import java.util.List;

import com.sec.las.lawservice.dto.EventManageVO;
import com.sec.las.lawservice.dto.LawfirmManageVO;
import com.sec.las.lawservice.dto.LawyerEstimateVO;
import com.sec.las.lawservice.dto.LawyerManageVO;

public interface LawyerManageService {

	/**
	 * 변호사관리 목록 조회
	 * 
	 * @param LawyerManageVO
	 * @return List
	 * @throws Exception
	 */
	List listLawyerManage(LawyerManageVO vo) throws Exception;
	
	/**
	 * 변호사관리 등록
	 * 
	 * @param LawyerManageVO
	 * @return List
	 * @throws Exception
	 */
	int insertLawyerManage(LawyerManageVO vo) throws Exception;
	
	/**
	 * 변호사관리 수정
	 * 
	 * @param LawyerManageVO
	 * @return int
	 * @throws Exception
	 */
	int modifyLawyerManage(LawyerManageVO vo) throws Exception;
	
	/**
	 * 변호사관리 삭제
	 * 
	 * @param LawyerManageVO
	 * @return int
	 * @throws Exception
	 */
	int deleteLawyerManage(LawyerManageVO vo) throws Exception;
	
	/**
	 * 변호사관리 상세
	 * 
	 * @param LawyerManageVO
	 * @return List
	 * @throws Exception
	 */
	List detailLawyerManage(LawyerManageVO vo) throws Exception;
	
	/**
	 * 변호사관리 상세 하단 사건리스트
	 * 
	 * @param EventManageVO
	 * @return List
	 * @throws Exception
	 */
	List getLawyerEventList(LawyerManageVO vo) throws Exception;
	
	/**
	 * 변호사관리 검색용사건리스트
	 * 
	 * @param EventManageVO
	 * @return List
	 * @throws Exception
	 */
	List listLawyerManageGetEventList(EventManageVO vo) throws Exception;
	
	/**
	 * 변호사관리 수정화면 검색용사건리스트
	 * 
	 * @param EventManageVO
	 * @return List
	 * @throws Exception
	 */
	List getLawyerEventList2(EventManageVO vo) throws Exception;
	
	/**
	 * 변호사관리 변호사ID 시퀀스
	 * 
	 * @param LawyerManageVO
	 * @return String
	 * @throws Exception
	 */
	String getMaxLawyerNo(LawyerManageVO vo) throws Exception;
	
	/**
	 * 변호사관리 변호사평가  ID 시퀀스
	 * 
	 * @param LawyerManageVO
	 * @return String
	 * @throws Exception
	 */
	String getMaxLawyerEstmtNo(LawyerManageVO vo) throws Exception;
	
	/**
	 * 변호사관리 로펌리스트
	 * 
	 * @param LawfirmManageVO
	 * @return List
	 * @throws Exception
	 */
	List getListLawfirm(LawfirmManageVO vo) throws Exception;
	//List getListLawfirm() throws Exception;
	
	/**
	 * 변호사관리 평가리스트
	 * 
	 * @param LawyerManageVO
	 * @return List
	 * @throws Exception
	 */
	List getListLawyerEstimate(LawyerEstimateVO vo) throws Exception;
	/**
	 * 변호사관리 변호사 사진 등록
	 * 
	 * @param LawyerManageVO
	 * @return List
	 * @throws Exception
	 */
	int insertLawyerProfilePhoto(LawyerManageVO vo) throws Exception;

}