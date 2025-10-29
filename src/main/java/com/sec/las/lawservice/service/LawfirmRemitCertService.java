package com.sec.las.lawservice.service;

/**
 * Project Name : 법무시스템
 * File Name : LawfirmRemitCertService.java
 * Description : 송금증 Service
 * Author : 박병주
 * Date : 2011.09
 * Copyright : 2011 by SAMSUNG. All rights reserved.
 */
import java.util.List;

import com.sec.las.lawservice.dto.LawfirmManageVO;
import com.sec.las.lawservice.dto.LawfirmRemitCertVO;

public interface LawfirmRemitCertService {
	/**
	 * 송금증 목록 조회
	 * @param LawfirmManageVO
	 * @return List
	 * @throws Exception
	 */
	List listLawfirmRemitCert(LawfirmManageVO vo) throws Exception;
	/**
	 * 송금증 등록
	 * @param LawfirmRemitCertVO
	 * @return int
	 * @throws Exception
	 */
	int insertLawfirmRemitCert(LawfirmRemitCertVO vo) throws Exception;
	/**
	 * 송금증 수정
	 * @param LawfirmRemitCertVO
	 * @return int
	 * @throws Exception
	 */
	int modifyLawfirmRemitCert(LawfirmRemitCertVO vo) throws Exception;
	/**
	 * 송금증 삭제
	 * @param LawfirmRemitCertVO
	 * @return int
	 * @throws Exception
	 */
	int deleteLawfirmRemitCert(LawfirmRemitCertVO vo) throws Exception;
	/**
	 * 송금증 상세
	 * @param LawfirmRemitCertVO
	 * @return List
	 * @throws Exception
	 */
	List detailLawfirmRemitCert(LawfirmRemitCertVO vo) throws Exception;

}