/**
* Project Name : 계약관리시스템
* File Name : CustomerNewService.java
* Description : 계약상대방 신규등록 팝업 Service
* Author : dawn
* Date : 2011.01.21
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.draft.service;

import java.util.List;

import com.sec.clm.draft.dto.NationVO;

public interface NationService {

	/**
	* 국가검색
	* 
	* @param NationVO
	* @return List
	* @throws Exception
	*/
	List listNation(NationVO vo) throws Exception ;
}
