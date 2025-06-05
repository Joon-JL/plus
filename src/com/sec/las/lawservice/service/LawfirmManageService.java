/**
 * Project Name : 법무시스템
 * File Name : LawfirmManageService.java
 * Description : 로펌 관리 Service
 * Author : 박병주
 * Date : 2010.09
 * Copyright : 2011 by SAMSUNG. All rights reserved.
 */
package com.sec.las.lawservice.service;

import java.util.List;

import com.sec.las.lawservice.dto.EventManageVO;
import com.sec.las.lawservice.dto.LawfirmManageVO;
import com.sec.las.lawservice.dto.LawfirmEstimateVO;
import com.sec.las.lawservice.dto.LawyerManageVO;

public interface LawfirmManageService {

	/**
	 * 로펌관리 목록 조회
	 * 
	 * @param LawfirmManageVO
	 * @return List
	 * @throws Exception
	 */
	List listLawfirmManage(LawfirmManageVO vo) throws Exception;
	
	/**
	 * 로펌관리 등록
	 * 
	 * @param LawfirmManageVO
	 * @return int
	 * @throws Exception
	 */
	int insertLawfirmManage(LawfirmManageVO vo) throws Exception;
	
	/**
	 * 로펌관리 수정
	 * 
	 * @param LawfirmManageVO
	 * @return int
	 * @throws Exception
	 */
	int modifyLawfirmManage(LawfirmManageVO vo) throws Exception;
	
	/**
	 * 로펌관리 삭제
	 * 
	 * @param LawfirmManageVO
	 * @return int
	 * @throws Exception
	 */
	int deleteLawfirmManage(LawfirmManageVO vo) throws Exception;
	
	/**
	 * 로펌관리 상세조회
	 * 
	 * @param LawfirmManageVO
	 * @return List
	 * @throws Exception
	 */
	List detailLawfirmManage(LawfirmManageVO vo) throws Exception;

	/**
	 * 로펌관리 하단 사건리스트
	 * 
	 * @param EventManageVO
	 * @return List
	 * @throws Exception
	 */
	List listLawfrimManageGetEventList(EventManageVO evo) throws Exception;
	
	/**
	 * 로펌평가 등록
	 * 
	 * @param LawfirmEstimateVO
	 * @return int
	 * @throws Exception
	 */
	int insertLawfirmEstimate(LawfirmEstimateVO vo) throws Exception;
	
	/**
	 * 로펌평가 목록
	 * 
	 * @param LawfirmEstimateVO
	 * @return List
	 * @throws Exception
	 */
	List listLawfirmEstimate(LawfirmEstimateVO vo) throws Exception;
	
	/**
	 * 로펌평가 SEQID 조회
	 * 
	 * @param LawfirmManageVO
	 * @return String
	 * @throws Exception
	 */
	String getNextEstmtNo(LawfirmManageVO vo) throws Exception;
	
	/**
	 * 로펌관리 SEQID 조회
	 * 
	 * @param LawfirmManageVO
	 * @return String
	 * @throws Exception
	 */
	String getMaxLawfirmID(LawfirmManageVO vo) throws Exception;
	/**
	 * 로펌평가 삭제
	 * 
	 * @param LawfirmManageVO
	 * @return int
	 * @throws Exception
	 */
	int deleteLawfirmEstimate(LawfirmEstimateVO vo) throws Exception ;
	/**
	 * 로펌소속변호사 목록
	 * 
	 * @param LawyerManageVO
	 * @return List
	 * @throws Exception
	 */
	List getListLawfirmLawywer(LawyerManageVO vo) throws Exception;

}