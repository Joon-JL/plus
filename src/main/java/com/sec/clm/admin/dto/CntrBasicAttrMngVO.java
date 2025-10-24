/**
 * Project Name : 계약관리시스템
 * File name	: CntrBasicAttrMngVO.java
 * Description	: 계약속성관리 Data Form(Model), 화면-서비스 로직 연결
 * Author		: 김형준
 * Date			: 2011. 08. 30
 * Copyright	:   
 */
package com.sec.clm.admin.dto;

import com.sds.secframework.common.dto.CommonVO;


/**
 * Description	: Data Form(Model), 화면-서비스 로직 연결
 * Author		: 김형준
 * Date			: 2011. 08. 30
 */
public class CntrBasicAttrMngVO extends CommonVO {
	
	/**********************************************
	 * 검색 조건
	 **********************************************/
	/** 정보 항목 */
	private String srch_info_itm;
	/** 속성 일련번호 */
	private int attr_seqno;
	
	/**********************************************
	 * getter, setter 메소드
	 **********************************************/
	public String getSrch_info_itm() {
		return srch_info_itm;
	}

	public void setSrch_info_itm(String srch_info_itm) {
		this.srch_info_itm = srch_info_itm;
	}
	
	public int getAttr_seqno() {
		return attr_seqno;
	}

	public void setAttr_seqno(int attr_seqno) {
		this.attr_seqno = attr_seqno;
	}

}
