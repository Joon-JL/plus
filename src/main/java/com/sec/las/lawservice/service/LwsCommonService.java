/**
 * Project Name : 법무시스템
 * File Name : LwsCommonService.java
 * Description : 로펌서비스 공통  Service
 * Author : 박병주
 * Date : 2011.10
 * Copyright : 2011 by SAMSUNG. All rights reserved.
 */
package com.sec.las.lawservice.service;

import com.sec.las.lawservice.dto.LwsCommonVO;

public interface LwsCommonService {
	
	/**
	* 표준 조항 권한조회
	* 
	* @param LwsCommonVO
	* @return boolean
	* @throws Exception
	*/
	boolean authLws(String mode, LwsCommonVO lcvo, String sqlmode) throws Exception;
	/**
	 * 사용자 ROLE에 따른 권한 제어
	 * 
	 * @param LwsCommonVO
	 * @return String
	 * @throws Exception
	 */
	String checkAuthByRole(LwsCommonVO lcvo) throws Exception;
	
	/**
	 * 사용자 조직에 따른 권한 제어:해외 법무팀 , IP 만 접속허용
	 * 
	 * @param LwsCommonVO
	 * @return void
	 * @throws Exception
	 */
	void checkBasicAccessAuth(LwsCommonVO lcvo,String returnMessage) throws Exception;
	
	/**
	 * 관리자 권한을 가지는 가의 체크
	 * 
	 * @param LwsCommonVO
	 * @return void
	 * @throws Exception
	 */
	boolean isAccessAuthAdmin(LwsCommonVO lcvo) throws Exception;
}
