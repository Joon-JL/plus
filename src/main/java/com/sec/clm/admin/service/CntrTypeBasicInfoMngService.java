/**
 * Project Name : 계약관리체계 강화 프로젝트
 * File name	: CntrTypeBasicInfoMngService.java
 * Description	: 계약유형별 속성관리 Service interface
 * Author		: 김형준
 * Date			: 2011. 09. 01
 * Copyright	:
 */
package com.sec.clm.admin.service;

import java.util.HashMap;
import java.util.List;

import com.sec.clm.admin.dto.CntrTypeBasicInfoMngVO;

/**
 * Description	: Service interface
 * Author		: 김형준
 * Date			: 2011. 09. 01
 */
public interface CntrTypeBasicInfoMngService {
	
	/**
	 * 조회
	 * @param vo CntrTypeBasicInfoMngVO
	 * @return
	 * @throws Exception
	 */
	List listCntrTypeBasicInfoMng(CntrTypeBasicInfoMngVO vo) throws Exception;
	
	/**
	 * 조회(iframe)
	 * @param vo CntrTypeBasicInfoMngVO
	 * @return
	 * @throws Exception
	 */
	HashMap listCntrTypeBasicInfoMngIF(CntrTypeBasicInfoMngVO vo) throws Exception;
	
	/**
	 * 상세조회
	 * @param vo CntrTypeBasicInfoMngVO
	 * @return
	 * @throws Exception
	 */
	HashMap detailCntrTypeBasicInfoMng(CntrTypeBasicInfoMngVO vo) throws Exception;
	
	/**
	 * 수정
	 * @param vo CntrTypeBasicInfoMngVO
	 * @return
	 * @throws Exception
	 */	
	int modifyCntrTypeBasicInfoMng(CntrTypeBasicInfoMngVO vo) throws Exception;

}
