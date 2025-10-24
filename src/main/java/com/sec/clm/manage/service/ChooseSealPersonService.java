/**
* Project Name : 계약관리시스템
* File Name :ChooseSealPersonService.java
* Description : 날인담당자선택팝업 Service interface
* Author : 심주완
* Date : 2011.09.29
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.service;

import java.util.HashMap;
import java.util.List;

import com.sec.clm.manage.dto.ChooseSealPersonVO;

public interface ChooseSealPersonService {
	
	// 날인담당자 팝업용
	List listChooseSealPerson(ChooseSealPersonVO vo) throws Exception;
	
	// 증명서류 발급자 팝업용
	List listChooseCertificatIssuer(ChooseSealPersonVO vo) throws Exception;	

	String getSealDeptComboHTML(HashMap hm) throws Exception;
}