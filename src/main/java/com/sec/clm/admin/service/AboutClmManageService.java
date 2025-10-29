package com.sec.clm.admin.service;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.CommonService;
import com.sec.clm.admin.dto.AboutClmManageVO;

public interface AboutClmManageService extends CommonService {

	/**
	* About CLM 관리 목록조회
	* 
	* @param AboutClmManageVO
	* @return List
	* @throws Exception
	*/
	List listAboutClmManage(AboutClmManageVO vo) throws Exception ;

	/**
	* About CLM 관리 상세 조회
	* 
	* @param AboutClmManageVO
	* @return List
	* @throws Exception
	*/
	ListOrderedMap detailAboutClmManage(AboutClmManageVO vo) throws Exception ;

	/**
	* About CLM 관리 수정
	* 
	* @param AboutClmManageVO
	* @return int
	* @throws Exception
	*/
	int modifyAboutClmManage(AboutClmManageVO vo) throws Exception ;

	/**
	* about CLM Html 호출
	* 
	* @param AboutClmManageVO
	* @return List
	* @throws Exception
	*/
	ListOrderedMap listAboutClmManageHtml(AboutClmManageVO vo) throws Exception ;

}
