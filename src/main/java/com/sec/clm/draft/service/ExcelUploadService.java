package com.sec.clm.draft.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sec.clm.draft.dto.CustomerNewVO;
import com.sec.clm.draft.dto.ExcelVO;

public interface ExcelUploadService {
	
	/**
	 * 저장
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	int saveExcelContent(List calList) throws Exception;
	
	
	/**
	 * 유효성 체크
	 * @param calList
	 * @return
	 * @throws Exception
	 */
//	int validateContent(List calList) throws Exception;


	/**
	 * 엑셀파일 서버 업로드
	 * @param HttpServletRequest request
	 * @return
	 * @throws Exception
	 */
	void saveExcelFile(HttpServletRequest request) throws Exception;

	/**
	 * 엑셀 파일 Temp Table bulk insert
	 * @param ExcelVO vo
	 * @return List
	 * @throws Exception
	 */
	List saveTempTable(ExcelVO vo) throws Exception;

	/**
	 * 엑셀 파일 insert
	 * @param ExcelVO vo
	 * @return
	 * @throws Exception
	 */
	List insertExcelToTable(ExcelVO vo)throws Exception;

	/**
	 * 에러 리스트 조회
	 * @param ExcelVO vo
	 * @return List
	 * @throws Exception
	 */
	List listErrorReport(ExcelVO vo) throws Exception;

	/**
	 * 에러 세부사항 조회
	 * @param ExcelVO vo
	 * @return List
	 * @throws Exception
	 */
	List listErrorDetail(ExcelVO vo) throws Exception;

	/**
	 * 엑셀 업로드 결과 조회
	 * @param ExcelVO vo
	 * @return List
	 * @throws Exception
	 */
	List excelInsertResult(ExcelVO vo) throws Exception;



}
