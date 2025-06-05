package com.sds.secframework.bbs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sds.secframework.bbs.dto.BBSMngVO;

/**
 * 게시판 관리 기능 제공
 * @author Hyunseo Kum
 */
public interface BBSMngService {

	/**
	 * 게시판 목록 조회
	 * @param sys_cd 시스템 코드
	 * @return 관리자용 메뉴리스트
	 * @throws Exception
	 */
	List listBBSMasterPage(HashMap hm) throws Exception;

	/**
	 * 게시판 상세내역 조회
	 * @param HashMap 시스템 코드, 게시판ID
	 * @return 게시판 상세내역
	 * @throws Exception
	 */
	List detailBBSMaster(HashMap hm) throws Exception;

	/**
	 * 게시판 상세내역 조회
	 * @param HashMap 시스템 코드, 게시판ID
	 * @return 게시판 상세내역
	 * @throws Exception
	 */
	Map getBBSMaster(HashMap hm) throws Exception;
	
	/**
	 * 게시판 내역 등록
	 * @param vo 게시판관리 Value Object
	 * @return 
	 * @throws Exception
	 */
	int insertBBSMaster(BBSMngVO vo) throws Exception;

	/**
	 * 게시판 내역 수정
	 * @param vo 게시판관리 Value Object
	 * @return 
	 * @throws Exception
	 */
	int modifyBBSMaster(BBSMngVO vo) throws Exception;

	/**
	 * 게시판 내역 삭제
	 * @param vo 게시판관리 Value Object
	 * @return 
	 * @throws Exception
	 */
	int deleteBBSMaster(HashMap hm) throws Exception;
}
