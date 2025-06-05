package com.sds.secframework.board.service;

import com.sds.secframework.board.dto.BoardMngVO;
import com.sds.secframework.board.dto.BoardVO;

public interface BoardService {
	
	/**
	 * 게시글을 등록한다.
	 * @param mngVo BoardMngVO
	 * @param vo BoardMngVO
	 * @return
	 * @throws Exception
	 */
	public int insertBoard(BoardMngVO mngVo, BoardVO vo) throws Exception ;
}
