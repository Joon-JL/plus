/**
 * Project Name : 법무시스템 이식
 * File name	: LibraryService.java
 * Description	: 국내/해외계약서 Service interface
 * Author		: 신승완
 * Date			: 2011. 08. 04
 * Copyright	:
 */
package com.sec.las.lawinformation.service;

import java.util.List;

import com.sec.las.lawinformation.dto.LibraryVO;

/**
 * Description	: 국내/해외계약서 Service interface
 * Author 		: 신승완
 * Date			: 2011. 08. 04
 */
public interface LibraryService {

	/**
	 * 국내/해외계약서 목록을 조회한다.
	 * @param  vo LibraryVO
	 * @return List
	 * @throws Exception
	 */
	List listLibrary(LibraryVO vo) throws Exception;

	/**
	 * 국내/해외계약서를 등록한다.
	 * @param  vo LibraryVO
	 * @return int
	 * @throws Exception
	 */
	int insertLibrary(LibraryVO vo) throws Exception;

	/**
	 * 국내/해외계약서를 수정한다.
	 * @param  vo LibraryVO
	 * @return int
	 * @throws Exception
	 */
	int modifyLibrary(LibraryVO vo) throws Exception;

	/**
	 * 국내/해외계약서를 삭제한다.
	 * @param  vo LibraryVO
	 * @return int
	 * @throws Exception
	 */
	int deleteLibrary(LibraryVO vo) throws Exception;

	/**
	 * 국내/해외계약서를 상세조회한다.
	 * @param  vo LibraryVO
	 * @return List
	 * @throws Exception
	 */
	List getLibrary(LibraryVO vo) throws Exception;
	
	/**
	 * 국내/해외계약서 조회수 증가
	 * @param  vo LibraryVO
	 * @return List
	 * @throws Exception
	 */
	void increseRdcnt(LibraryVO vo) throws Exception;

	/**
	* 표준 조항 권한조회
	* @param vo LibraryVO
	* @return boolean
	* @throws Exception
	*/
	boolean authLibrary(String mode, LibraryVO vo) throws Exception;

	/**
	 * 사용자 ROLE에 따른 권한 제어
	* @param vo LibraryVO
	 * @return String
	 * @throws Exception
	 */
	String checkAuthByRole(LibraryVO vo) throws Exception;

}