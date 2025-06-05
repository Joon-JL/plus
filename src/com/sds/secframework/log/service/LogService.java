package com.sds.secframework.log.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.sds.secframework.log.dto.LogVO;

public interface LogService {

	/**
	 * 로그인 현황 로그등록
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	int insertLoginLog(LogVO vo) throws Exception;

	/**
	 * 로그인 아웃 로그등록
	 * @param 
	 * @return 
	 * @throws Exception
	 */	
	int updateLogoutLog(LogVO vo) throws Exception;
	
	/**
	 * 메뉴사용 현황 로그등록
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	int insertMenuLog(LogVO vo) throws Exception;

	/**
	 * 파일다운로드 로그등록
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	void insertFileDownLog(LogVO vo) throws Exception;
	
	/**
	 * 로그인 현황 조회
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	List listLoginLog(LogVO vo) throws Exception;
	
	/**
	 * 로그인 현황 조회(엑셀다운)
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	List excelDownLoginLog(LogVO vo) throws Exception;
	
	/**
	 * 화면 접속 현황 조회
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	List listMenuUseLog(LogVO vo) throws Exception;
	
	/**
	 * 파일다운로드 접속 현황 조회
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	List excelDownMenuUseLog(LogVO vo) throws Exception;
	
	/**
	 * 파일다운로드 현황 조회
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	List listFileLog(LogVO vo) throws Exception;

	/**
	 * 파일다운로드 현황 조회(엑셀다운)
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	List excelDownFileDownLoadLog(LogVO vo) throws Exception;

	/**
	 * CHART 로그인 현황 조회
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	JSONObject listLoginLogDayChart(LogVO vo) throws Exception;
	
	/**
	 * 부서별 로그인 현황 조회(엑셀다운)
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	List excelDownLoginDeptLog(LogVO vo) throws Exception;
	
	/**
	 * 사용자 최근 접속 정보
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public List listRecentUserInfo(String userId, String sysCd, String compCd) throws Exception;
	
	/**
	 * 컬럼 정보
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listColumnInfo(LogVO vo) throws Exception ;
	
	/**
	 * 로그인 현황 페이징 처리 안하는 리스트
	 * @param vo LogVO
	 * @return
	 * @throws Exception
	 */
	public List listLoginLogNoPage(LogVO vo) throws Exception ;
	
	/**
	 * 회사별 로그인 현황
	 * @param vo LogVO
	 * @return
	 * @throws Exception
	 */
	public List listLoginLogComp(LogVO vo) throws Exception ;
	
	/**
	 * 부서별 로그인 현황
	 * @param vo LogVO
	 * @return
	 * @throws Exception
	 */
	public List listLoginLogDept(LogVO vo) throws Exception ;
	
	/**
	 * 메뉴사용 현황 페이징 처리 안하는 리스트
	 * @param vo LogVO
	 * @return
	 * @throws Exception
	 */
	public List listMenuLogNoPage(LogVO vo) throws Exception ;
	
	/**
	 * 메뉴사용 현황(접속자수/접속횟수)
	 * @param vo LogVO
	 * @return
	 * @throws Exception
	 */
	public List listMenuLogContact(LogVO vo) throws Exception;
	
	/**
	 * 파일다운로드 현황 페이징 처리 안하는 리스트
	 * @param vo LogVO
	 * @return
	 * @throws Exception
	 */
	public List listFileLogNoPage(LogVO vo) throws Exception ;
}
