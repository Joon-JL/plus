package com.sec.clm.manage.service;

import java.util.HashMap;
import java.util.List;

import com.sec.clm.manage.dto.ConclusionDelayVO;
/**
 * Description	: 미체결사유 Service interface
 * Author 		: 심주완
 * Date			: 2011. 09. 05
 */
public interface ConclusionDelayService {
	/**
	 * 미체결사유  전체 목록을 조회한다.	팝업에서 사용-현재사용안함 추후 위해 남겨둠
	 * @param  vo ConclusionDelayVO
	 * @return List
	 * @throws Exception
	 */
	List listConclusionDelay(ConclusionDelayVO vo) throws Exception;
	
	/**
	 * 미체결사유 등록 및 수정한다.	팝업에서 사용-현재사용안함 추후 위해 남겨둠
	 * @param  vo ExecutionVO
	 * @return int
	 * @throws Exception
	 */
	int insertConclusionDelay(ConclusionDelayVO vo) throws Exception;
	
	
	/**
	 * 미체결사유  목록을 조회한다.	히스토리관리안함--체결본등록 메인화면에서 조회
	 * @param  vo ConclusionDelayVO
	 * @return List
	 * @throws Exception
	 */
	List listConclusionDelayForMain(ConclusionDelayVO vo) throws Exception;
	
	/**
	 * 미체결사유 등록 및 수정한다.	체결본등록메인화면에서 입력및 수정
	 * @param  vo ConclusionDelayVO
	 * @return int
	 * @throws Exception
	 */
	int insertConclusionDelayForMain(ConclusionDelayVO vo) throws Exception;

}