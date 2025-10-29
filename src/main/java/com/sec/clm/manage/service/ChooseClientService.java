/**
* Project Name : 계약관리시스템
* File Name :ChooseClientService.java
* Description : 의뢰인선택팝업 Service interface
* Author : 심주완
* Date : 2011.09.25
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.service;

import java.util.List;

import com.sec.clm.manage.dto.ChooseClientVO;

public interface ChooseClientService {
	List listChooseClient(ChooseClientVO vo) throws Exception;
}