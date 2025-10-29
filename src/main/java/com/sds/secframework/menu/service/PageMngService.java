package com.sds.secframework.menu.service;

import net.sf.json.JSONArray;

import com.sds.secframework.common.util.ExcelLoader;
import com.sds.secframework.menu.dto.PageMngVO;

/**
 * 페이지관리 Interface
 *
 */
public interface PageMngService {

	/**
	 * 페이지 목록 Table 형식
	 * @param 
	 * @return 페이지리스트 JSON
	 * @throws Exception 
	 */
	JSONArray listPageTable(PageMngVO vo) throws Exception;

	/**
	 * 페이지를 등록한다.
	 * @param menuVO 페이지VO
	 * @return
	 * @throws Exception
	 */
	void insertPage(PageMngVO vo) throws Exception;

	/**
	 * 페이지정보를 수정한다.
	 * @param menuVO 페이지VO
	 * @return
	 * @throws Exception
	 */
	int modifyPage(PageMngVO vo) throws Exception;

	/**
	 * 페이지를 삭제.
	 * @param menuVO 페이지VO
	 * @return
	 * @throws Exception
	 */
	int deletePage(PageMngVO vo) throws Exception;	

	/**
	 * 페이지 목록 Table 형식
	 * @param 
	 * @return 페이지리스트 JSON
	 * @throws Exception 
	 */
	JSONArray listPageTableUpload(PageMngVO vo) throws Exception;

	/**
	 * 페이지를 등록한다.
	 * @param menuVO 페이지VO
	 * @return
	 * @throws Exception
	 */
	String uploadExcel(PageMngVO vo, ExcelLoader excel) throws Exception;

	/**
	 * 페이지를 등록한다.
	 * @param menuVO 페이지VO
	 * @return
	 * @throws Exception
	 */
	void insertPageUpload(PageMngVO vo) throws Exception;

	/**
	 * 페이지를 삭제.
	 * @param menuVO 페이지VO
	 * @return
	 * @throws Exception
	 */
	int deletePageUpload(PageMngVO vo) throws Exception;
	
	/**
	 * 페이지 엑셀 업로드 데이타를 운영시스템으로 전송
	 * @param menuVO 페이지VO
	 * @return
	 * @throws Exception
	 */
	public int transferExcelUpload(PageMngVO vo) throws Exception;


}
