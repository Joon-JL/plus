package com.sds.secframework.board.service;

import java.util.List;
import java.util.Map;

import com.sds.secframework.board.dto.BoardMngVO;

public interface BoardMngService {
	
	/**
	 * 게시판 마스터 리스트 
	 * @param vo TODO
	 * @return
	 * @throws Exception
	 */
	public List listBoardMng(BoardMngVO vo) throws Exception ;
	
	/**
	 * 게시판 마스터 등록 
	 * @param vo
	 * @return
	 */
	public int insertBoardMng(BoardMngVO vo) throws Exception ;
	
	/**
	 * 게시판명 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int insertBoardMngTitle(BoardMngVO vo) throws Exception ;
	
	
	/**
	 * 게시판 마스터 상세 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Map detailBoardMng(BoardMngVO vo) throws Exception ;
	
	/**
	 * 게시판 타이틀 리스트 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listBoardTitle(BoardMngVO vo) throws Exception ;
	
	/**
	 * 게시판 마스터 수정 
	 * @param vo
	 * @return
	 */
	public int modifyeBoardMng(BoardMngVO vo) throws Exception ;
	
	/**
	 * 게시판명 수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int modifyBoardMngTitle(BoardMngVO vo) throws Exception ;
	
	/**
	 * 게시판명 삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int deleteBoardMng(BoardMngVO vo) throws Exception ;
	
	/**
	 * 게시판 권한 리스트
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listBoardAuth(BoardMngVO vo) throws Exception ;
}
