package com.sec.las.lawinformation.service;

import java.util.List;

import com.sec.las.lawinformation.dto.GuideneduVO;

public interface GuideneduService {
	
	/**
	 * 임직원법무교육자료 목록을 조회한다.
	 * @param  vo GuideneduVO
	 * @return List
	 * @throws Exception
	 */
	List listGuidenedu(GuideneduVO vo) throws Exception;

	/**
	 * 임직원법무교육자료를 등록한다.
	 * @param  vo GuideneduVO
	 * @return int
	 * @throws Exception
	 */
	int insertGuidenedu(GuideneduVO vo)throws Exception;

	/**
	 * 계약서 GuideLine 자료를 등록한다.
	 * @param  vo GuideneduVO
	 * @return int
	 * @throws Exception 
	 */
	int insertGuideline(GuideneduVO vo)throws Exception;

	/**
	 * 임직원법무교육자료를 수정한다.
	 * @param  vo GuideneduVO
	 * @return int
	 * @throws Exception
	 */
	int modifyGuidenedu(GuideneduVO vo) throws Exception;

	/**
	 * 계약서 GuideLine 수정한다.
	 * @param  vo GuideneduVO
	 * @return int
	 * @throws Exception
	 */
	int modifyGuideline(GuideneduVO vo) throws Exception;

	/**
	 * 임직원법무교육자료를 삭제한다.
	 * @param  vo GuideneduVO
	 * @return int
	 * @throws Exception
	 */
	int deleteGuidenedu(GuideneduVO vo) throws Exception;

	/**
	 * 계약서 GuideLine을 삭제한다.
	 * @param  vo GuideneduVO
	 * @return int
	 * @throws Exception
	 */
	int deleteGuideline(GuideneduVO vo) throws Exception;

	/**
	 * 임직원법무교육자료를 상세조회한다.
	 * @param  vo GuideneduVO
	 * @return List
	 * @throws Exception
	 */
	List getGuidenedu(GuideneduVO vo) throws Exception;

	/**
	 * 임직원법무교육자료 조회수 증가
	 * @param  vo GuideneduVO
	 * @return List
	 * @throws Exception
	 */
	void increseRdcnt(GuideneduVO vo) throws Exception;

	/**
	* 표준 조항 권한조회
	* 
	* @param vo GuideneduVO
	* @return boolean
	* @throws Exception
	*/
	boolean authGuidenedu(String mode, GuideneduVO vo) throws Exception;

	/**
	 * 사용자 ROLE에 따른 권한 제어
	 * @param vo GuideneduVO
	 * @return String
	 * @throws Exception
	 */
	String checkAuthByRole(GuideneduVO vo) throws Exception;
}