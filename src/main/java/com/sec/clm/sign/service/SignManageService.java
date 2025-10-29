package com.sec.clm.sign.service;

/**
 * Project Name : 법무시스템
 * File Name : SignService.java
 * Description : 날인 Service
 * Author : 박병주
 * Date : 2013.05
 * Copyright : 2013 by SAMSUNG. All rights reserved.
 */
import java.util.HashMap;
import java.util.List;

import com.sec.clm.sign.dto.SignManageVO;
import com.sec.clm.sign.dto.SignManageVO;
import com.sec.las.lawservice.dto.LwsCommonVO;

public interface SignManageService {
	/**
	 * 날인 목록 조회
	 * @param SignManageVO
	 * @return List
	 * @throws Exception
	 */
	List listSign(SignManageVO vo) throws Exception;
	/**
	 * 날인 등록
	 * @param SignManageVO
	 * @return int
	 * @throws Exception
	 */
	String insertSign(SignManageVO vo) throws Exception;
	/**
	 * 날인 수정
	 * @param SignManageVO
	 * @return int
	 * @throws Exception
	 */
	int modifySign(SignManageVO vo) throws Exception;
	/**
	 * 날인 담당자 수정
	 * @param SignManageVO
	 * @return int
	 * @throws Exception
	 */
	int modifySignMan(SignManageVO vo) throws Exception;
	/**
	 * 날인 / 증명서류 처리
	 * @param SignManageVO
	 * @return int
	 * @throws Exception
	 */
	int signAction(String modeSQL,SignManageVO vo) throws Exception;
	/**
	 * 날인 삭제
	 * @param SignManageVO
	 * @return int
	 * @throws Exception
	 */
	int deleteSign(SignManageVO vo) throws Exception;
	/**
	 * 날인 상세
	 * @param SignManageVO
	 * @return List
	 * @throws Exception
	 */
	List detailSign(SignManageVO vo) throws Exception;
	/**
	 * 날인 상세_첨부파일 취득
	 * @param SignManageVO
	 * @return List
	 * @throws Exception
	 */
	public List getAttachList(SignManageVO vo) throws Exception;
	/**
	 * 승인이력
	 * @param SignManageVO
	 * @return List
	 * @throws Exception
	 */
	public List listSignAppr(SignManageVO vo) throws Exception;	
	/**
	 * 날인 상태 수정(품의상신 성공시)
	 * @param SignManageVO
	 * @return int
	 * @throws Exception
	 */
	int modifySignStatus(SignManageVO vo) throws Exception;	
	/**
	* 의뢰자/배정자 권한 체크 (수정/삭제 버튼제어)
	* 
	* @param SignManageVOx
	* @return boolean
	* @throws Exception
	*/
	boolean authModify(String mode, SignManageVO lcvo) throws Exception;
	
	/**
	* 의뢰자/배정자 권한 체크 (날인처리/반납처리/증명서류처리 버튼제어)
	* 
	* @param SignManageVO
	* @return boolean
	* @throws Exception
	*/
	boolean authProcess(String mode, SignManageVO lcvo) throws Exception;
	/**
	 * 사용자 ROLE에 따른 권한 제어
	 * 
	 * @param LwsCommonVO
	 * @return String
	 * @throws Exception
	 */
	String checkAuthByRole(SignManageVO lcvo) throws Exception;
	
	/**
	 * 사용자 조직에 따른 권한 제어:해외 법무팀 , IP 만 접속허용
	 * 
	 * @param LwsCommonVO
	 * @return void
	 * @throws Exception
	 */
	void checkBasicAccessAuth(SignManageVO lcvo,String returnMessage) throws Exception;
	
	/**
	 * 관리자 권한을 가지는 가의 체크
	 * 
	 * @param LwsCommonVO
	 * @return void
	 * @throws Exception
	 */
	boolean isAccessAuthAdmin(SignManageVO lcvo) throws Exception;
	
	/**
	 * 후처리 날인 등록
	 * 
	 * @param 
	 * @return HashMap
	 * @throws Exception
	 */
	public HashMap insertPrtbInfo(String cnsdreq_id, String cntrt_id, String create_date) throws Exception;

}