package com.sec.las.board.service;


import java.util.List;
import com.sec.las.board.dto.BbsVO;

public interface BbsService {

	List listbbs(BbsVO vo) throws Exception;
	
	List detailbbs(BbsVO vo) throws Exception;
	
	void increseRdcnt(BbsVO vo) throws Exception;
		
	List detailListbbs(BbsVO vo) throws Exception;

	int insertbbs(BbsVO vo,String insert_kbn) throws Exception;
	
	int updatePos(BbsVO vo) throws Exception;
	
	int updatePosDel(BbsVO vo) throws Exception;

	int modifybbs(BbsVO vo) throws Exception;

	int deletebbs(BbsVO vo) throws Exception;

	/**
	* 표준 조항 권한조회
	* 
	* @param ItemLibraryVO
	* @return boolean
	* @throws Exception
	*/
	boolean	authBbs(String mode, BbsVO vo) throws Exception;

	/**
	 * 사용자 ROLE에 따른 권한 제어
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	String checkAuthByRole(BbsVO vo) throws Exception;

	List delCheck(BbsVO vo) throws Exception;
}