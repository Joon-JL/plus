package com.sec.las.lawservice.service;

import java.util.List;

import net.sf.json.JSONArray;

import com.sec.las.lawservice.dto.CheckRateVO;
import com.sec.las.lawservice.dto.EventAcceptSrvCostVO;
import com.sec.las.lawservice.dto.EventAcceptVO;
import com.sec.las.lawservice.dto.EventManageVO;
import com.sec.las.lawservice.dto.LawyerManageVO;

public interface EventAcceptSrvCostService {
	
	/**
	 * 용역비 검색용 사건 목록
	 * 
	 * @param EventManageVO
	 * @return List
	 * @throws Exception
	 */
	List listEventSub(EventManageVO vo) throws Exception;
	
	/**
	 * 용역비 등록
	 * 
	 * @param EventAcceptSrvCostVO
	 * @return List
	 * @throws Exception
	 */
	int insertEventAcceptSrvCost(EventAcceptVO vo) throws Exception;
	/**
	 * 용역비 수정
	 * 
	 * @param EventAcceptSrvCostVO
	 * @return List
	 * @throws Exception
	 */
	int modifyEventAcceptSrvCost(EventAcceptVO vo) throws Exception;
	/**
	 * 용역비 삭제
	 * 
	 * @param EventAcceptSrvCostVO
	 * @return List
	 * @throws Exception
	 */
	int deleteEventAcceptSrvCost(EventAcceptVO vo) throws Exception;
	/**
	 * 사건접수 상세
	 * 
	 * @param EventAcceptSrvCostVO
	 * @return List
	 * @throws Exception
	 */
	List detailEventAccept(EventAcceptVO vo) throws Exception;
	/**
	 * 용역비 상세
	 * 
	 * @param EventAcceptVO
	 * @return List
	 * @throws Exception
	 */
	List detailEventAcceptSrvCost(EventAcceptVO vo) throws Exception;
	/**
	 * 용역비 SEQ
	 * 
	 * @param EventAcceptSrvCostVO
	 * @return String
	 * @throws Exception
	 */
	String getMaxAcptNo(EventAcceptVO vo) throws Exception;
	/**
	 * 변호사 comboListBox 
	 * 
	 * @param LawyerManageVO
	 * @return List
	 * @throws Exception
	 */
	List getLawyerList(LawyerManageVO vo) throws Exception;
	

	
	/**
	 * 사건 담당 변호사 목록 
	 * 
	 * @param EventAcceptVO
	 * @return List
	 * @throws Exception
	 */
	List getListEventAcceptLawyer(EventAcceptVO vo) throws Exception;
	/**
	 * INVOICE NO 중복 조회
	 * 
	 * @param EventAcceptVO
	 * @return JSONArray
	 * @throws Exception
	 */
	JSONArray checkInvoiceNoAjax(EventAcceptVO vo) throws Exception;
	/**
	 * INVOICE 검색
	 * 
	 * @param EventAcceptVO
	 * @return JSONArray
	 * @throws Exception
	 */
	JSONArray srchInvoiceNo(EventAcceptVO vo) throws Exception;
	/**
	 * 환율적용정보 조회
	 * 
	 * @param EventAcceptVO
	 * @return JSONArray
	 * @throws Exception
	 */
	JSONArray checkRateAjax(EventAcceptVO vo) throws Exception;	
	/**
	 * 송금일자 갱신
	 * 
	 * @param EventAcceptVO
	 * @return List
	 * @throws Exception
	 */
	int updateRemitday(EventAcceptVO vo) throws Exception;
	/**
	 * REVIEW 갱신
	 * 
	 * @param EventAcceptVO
	 * @return List
	 * @throws Exception
	 */
	int updateReviewYn(EventAcceptVO vo) throws Exception;	
	/**
	 * 송금정보 초기화
	 * 
	 * @param EventAcceptVO
	 * @return List
	 * @throws Exception
	 */
	int updateRemitInit(EventAcceptVO vo) throws Exception;	
	
	/**
	 * 미지급일자를 갱신.　
	 * 
	 * @param EventAcceptVO
	 * @return List
	 * @throws Exception
	 */
	int updateUnpayday(EventAcceptVO vo) throws Exception;	
	
	

}