/**
 * Project Name : 법무시스템 이식
 * File name	: MainLawInfoService.java
 * Description	: 주요입법동향 Service interface
 * Author		: 
 * Date			: 2011. 08
 * Copyright	:
 */
package com.sec.las.lawinformation.service;

import java.util.List;

import com.sec.las.lawinformation.dto.MainLawInfoVO;

/**
 * @Description : 주요입법동향/법무사례 Service
 * @Author 		: 
 * @Date		: 2011. 08
 */
public interface MainLawInfoService {
	
	
	/**
	 * 주요입법동향/법무사례 목록을 조회한다.
	 * @param  vo MainLawInfoVo
	 * @return List
	 * @throws Exception
	 */
	List listMainLawInfo(MainLawInfoVO vo) throws Exception;
	
	
	/**
	 * 주요입법동향/법무사례를 등록한다.
	 * @param  vo MainLawInfoVo
	 * @return int
	 * @throws Exception
	 */
	int insertMainLawInfo(MainLawInfoVO vo) throws Exception;
	
	/**
	 * 주요입법동향/법무사례를 수정한다.
	 * @param  vo MainLawInfoVo
	 * @return int
	 * @throws Exception
	 */
	int modifyMainLawInfo(MainLawInfoVO vo) throws Exception;

	/**
	 * 주요입법동향/법무사례를 삭제한다.
	 * @param  vo MainLawInfoVo
	 * @return int
	 * @throws Exception
	 */
	int deleteMainLawInfo(MainLawInfoVO vo) throws Exception;

	/**
	 * 주요입법동향/법무사례를 상세조회한다.
	 * @param  vo MainLawInfoVO
	 * @return List
	 * @throws Exception
	 */
	List detailMainLawInfo(MainLawInfoVO vo) throws Exception;
	
	/**
	 * 주요입법동향/법무사례 조회수 증가
	 * @param  vo MainLawInfoVO
	 * @return List
	 * @throws Exception
	 */
	void increseRdcnt(MainLawInfoVO vo) throws Exception;

	/**
	 * 표준 조항 권한조회
	 * @param  vo MainLawInfoVO, mode String
	 * @return boolean
	 * @throws Exception
	 */
	boolean authMainLawInfo(String mode, MainLawInfoVO vo) throws Exception;

	/**
	 * 사용자 ROLE에 따른 권한 제어
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	String checkAuthByRole(MainLawInfoVO vo) throws Exception;
	
	/**
	 * 주요입법동향 목록을 조회한다.(법무메인)
	 * @param  vo MainLawInfoVo
	 * @return List
	 * @throws Exception
	 */
	List listMainLawInfoByMain(MainLawInfoVO vo) throws Exception;
}