/**
 * Project Name : 법무시스템
 * File Name : EventManageService.java
 * Description : 사건 관리 Service
 * Author : 박병주
 * Date : 2011.09
 * Copyright : 2011 by SAMSUNG. All rights reserved.
 */
package com.sec.las.lawservice.service;

import java.util.List;

import net.sf.json.JSONArray;

import com.sec.las.lawservice.dto.DeptListVO;
import com.sec.las.lawservice.dto.EventAcceptLawyerVO;
import com.sec.las.lawservice.dto.EventAcceptSrvCostVO;
import com.sec.las.lawservice.dto.EventManageVO;
import com.sec.las.lawservice.dto.EventRefDeptVO;
import com.sec.las.lawservice.dto.EventAcceptVO;

public interface EventManageService {
	/**
	 * 사건관리 리스트
	 * 
	 * @param EventManageVO
	 * @return List
	 * @throws Exception
	 */
	List listEventManage(EventManageVO vo) throws Exception;
	/**
	 * 사건관리 등록
	 * 
	 * @param EventManageVO
	 * @return int
	 * @throws Exception
	 */
	int insertEventManage(EventManageVO vo) throws Exception;
	/**
	 * 사건관리 수정
	 * 
	 * @param EventManageVO
	 * @return int
	 * @throws Exception
	 */
	int modifyEventManage(EventManageVO vo) throws Exception;
	/**
	 * 사건관리 삭제
	 * 
	 * @param EventManageVO
	 * @return int
	 * @throws Exception
	 */
	int deleteEventManage(EventManageVO vo) throws Exception;
	/**
	 * 사건관리 상세
	 * 
	 * @param EventManageVO
	 * @return List
	 * @throws Exception
	 */
	List detailEventManage(EventManageVO vo) throws Exception;
	/**
	 * 사건관리 사건시퀀스
	 * 
	 * @param EventManageVO
	 * @return String
	 * @throws Exception
	 */
	String getMaxEventNo(EventManageVO vo) throws Exception; 
	/**
	 * 등록 부서 목록 조회
	 * @param DeptListVO
	 * @return List
	 * @throws Exception
	 */
	List getListRefDept(EventRefDeptVO vo) throws Exception;
	/**
	 * 사건관련 변호사 목록 조회
	 * @param DeptListVO
	 * @return List
	 * @throws Exception
	 */
	List getListLawywerAcceptEvent(EventAcceptLawyerVO vo) throws Exception;
	/**
	 * 등록사건 목록 조회
	 * @param EventAcceptVO
	 * @return List
	 * @throws Exception
	 */
	List getListEventAccept(EventAcceptVO vo) throws Exception;
	/**
	 * 사건 접수 송장 목록 조회
	 * @param EventAcceptVO
	 * @return List
	 * @throws Exception
	 */
	List getListEventAcceptInvoice(EventAcceptVO vo) throws Exception;
	
	/**
	 * 부서 목록 조회
	 * @param DeptListVO
	 * @return List
	 * @throws Exception
	 */
	List getListDept() throws Exception;
	/**
	 * 부서 목록 조회
	 * @param DeptListVO
	 * @return List
	 * @throws Exception
	 */
	List listDeptTree(DeptListVO vo) throws Exception;
	
	/**
	 * 하위부서 목록 조회
	 * @param DeptListVO
	 * @return JSONArray
	 * @throws Exception
	 */
	JSONArray listDeptTreeAjax(DeptListVO vo) throws Exception; 

}