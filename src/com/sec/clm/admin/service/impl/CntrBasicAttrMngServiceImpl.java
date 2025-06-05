/**
 * Project Name : 계약관리체계 강화 프로젝트
 * File name	: CntrBasicAttrServiceImpl.java
 * Description	: 계약속성관리 Service impl(concrete class)
 * Author		: 김형준
 * Date			: 2011. 08. 30
 * Copyright	:
 */
package com.sec.clm.admin.service.impl;

import java.util.List;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sec.clm.admin.dto.CntrBasicAttrMngVO;
import com.sec.clm.admin.service.CntrBasicAttrMngService;


/**
 * Description	: Service impl(concrete class)
 * Author		: 김형준
 * Date			: 2011. 08. 30
 */
public class CntrBasicAttrMngServiceImpl extends CommonServiceImpl implements CntrBasicAttrMngService{
	
	/**
	 * 조회
	 * @param vo CntrGrpDetailMngVO
	 * @return List
	 * @throws Exception
	 */
	public List listCntrBasicAttrMng(CntrBasicAttrMngVO vo) throws Exception{
		return commonDAO.list("clm.admin.listCntrBasicAttrMng", vo);
	}
	
	/**
	 * 상세조회
	 * @param vo CntrBasicAttrMngVO
	 * @return
	 * @throws Exception
	 */
	public List detailCntrBasicAttrMng(CntrBasicAttrMngVO vo) throws Exception{
		return commonDAO.list("clm.admin.detailCntrBasicAttrMng", vo);
	}
	
}
