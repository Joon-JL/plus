/**
 * Project Name : 계약관리체계 강화 프로젝트
 * File name	: CntrBasicAttrService.java
 * Description	: 계약속성관리 Service interface
 * Author		: 김형준
 * Date			: 2011. 08. 30
 * Copyright	:
 */
package com.sec.clm.admin.service;

import java.util.List;

import com.sec.clm.admin.dto.CntrBasicAttrMngVO;


/**
 * Description	: Service interface
 * Author		: 김형준
 * Date			: 2011. 08. 30
 */
public interface CntrBasicAttrMngService {
	
	
	/**
	 * 조회
	 * @param vo CntrBasicAttrMngVO
	 * @return
	 * @throws Exception
	 */
	List listCntrBasicAttrMng(CntrBasicAttrMngVO vo) throws Exception;
	
	/**
	 * 상세조회
	 * @param vo CntrBasicAttrMngVO
	 * @return
	 * @throws Exception
	 */
	List detailCntrBasicAttrMng(CntrBasicAttrMngVO vo) throws Exception;

}
