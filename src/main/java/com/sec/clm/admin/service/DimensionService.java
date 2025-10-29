/**
 * Project Name : 계약관리시스템
 * File name	: DimensionService.java
 * Description	: 계약분류관리 Service interface
 * Author		: 한지훈
 * Date			: 2011. 08. 24
 * Copyright	:
 */
package com.sec.clm.admin.service;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import net.sf.json.JSONArray;

import com.sec.clm.admin.dto.DimensionVO;
import com.sec.clm.admin.dto.RegulationVO;

/**
 * Description	: Service interface
 * Author		: 한지훈
 * Date			: 2011. 08. 24
 */
public interface DimensionService {
	
	/**
	 * 조회
	 * @param vo DimensionVO
	 * @return
	 * @throws Exception
	 */
	List listDimension(DimensionVO vo) throws Exception;
	
	/**
	 * 등록
	 * @param vo DimensionVO
	 * @return String
	 * @throws Exception
	 */
	String insertDimension(DimensionVO vo) throws Exception;
	
	/**
	 * 수정
	 * @param vo DimensionVO
	 * @return int
	 * @throws Exception
	 */
	int modifyDimension(DimensionVO vo) throws Exception;
	 
	/**
	 * 삭제(실제 삭제하는 것이 아니고 DEL_YN을 "Y"로 업데이트) 
	 * @param vo DimensionVO
	 * @return int
	 * @throws Exception
	 */
	int deleteDimension(DimensionVO vo) throws Exception;
	
	/**
	 * 상세조회
	 * @param  vo ContDivMngVO
	 * @return List
	 * @throws Exception
	 */
	List detailDimension(DimensionVO vo) throws Exception;
	
	/**
	 * 계약유형 목록 Tree 형식
	 * @param 
	 * @return 메뉴리스트 JSON
	 * @throws Exception 
	 */
	JSONArray listDimensionTree(DimensionVO vo) throws Exception;
	
	/**
	 * 계약유형리스트
	 * @param  vo ContDivMngVO
	 * @return List
	 * @throws Exception
	 */
	List listDimensionChart(DimensionVO vo) throws Exception;
	
	
	/**
	 * 계약 용어집
	 * @param  vo ContDivMngVO
	 * @return List
	 * @throws Exception
	 */
	List listDimensionWord(DimensionVO vo) throws Exception;
	
	/**
	* 계약 용어집 1단계 조회
	* 
	* @param 
	* @return List
	* @throws Exception
	*/
	List listODepthDimesionWord(DimensionVO vo) throws Exception ;
	
	/**
	* 계약 용어집 목록조회
	* 
	* @param RegulationVO
	* @return List
	* @throws Exception
	*/
	List listDimesionWordAdmin(DimensionVO vo) throws Exception;
	
	/**
	* 등록 화면전체depth(selectbox)조회.
	* 
	* @param 
	* @return List
	* @throws Exception
	*/
	List listUpRule(DimensionVO vo) throws Exception ;
	
	/**
	* 계약 용어집 등록
	* 
	* @param RegulationVO
	* @return List
	* @throws Exception
	*/
	int insertDimensionWordAdmin(DimensionVO vo) throws Exception;
	
	/**
	* 계약 용어집 상세조회
	* 
	* @param RegulationVO
	* @return List
	* @throws Exception
	*/
	ListOrderedMap detailDimensionWordAdmin(DimensionVO vo) throws Exception;
	
	/**
	* 계약용어집 수정
	* 
	* @param RegulationVO
	* @return List
	* @throws Exception
	*/
	int modifyDimensionWordAdmin(DimensionVO vo) throws Exception;
	
	/**
	* 계약용어집 삭제
	* 
	* @param RegulationVO
	* @return List
	* @throws Exception
	*/
	int deleteDimensionWordAmdin(DimensionVO vo) throws Exception;	
	
}
