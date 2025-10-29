package com.sds.secframework.board.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;

import anyframe.core.idgen.IIdGenerationService;

import com.sds.secframework.board.dto.BoardMngVO;
import com.sds.secframework.board.service.BoardMngService;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.DateUtil;

public class BoardMngServiceImpl extends CommonServiceImpl implements BoardMngService {
	
	IIdGenerationService idGenerationService ;
	public void setIdGenerationService(IIdGenerationService idGenerationService) {
		this.idGenerationService = idGenerationService;
	}


	/**
	 * 게시판 관리 리스트 조회 
	 * @param vo BoardMngVO
	 * @return
	 * @throws Exception
	 */
	public List listBoardMng(BoardMngVO vo) throws Exception {

		return commonDAO.list("secfw.board.listBoardMng", vo) ;
	}
	
	
	/**
	 * 게시판 마스터 등록
	 * <p>
	 * 1. 마스터 등록
	 * 2. 게시판명 등록
	 * </P>
	 * @param vo BoardMngVO
	 * @return
	 * @throws Exception
	 */
	public int insertBoardMng(BoardMngVO vo) throws Exception {
		int result = 0 ;
		
		String boardId = DateUtil.getTime("yyyyMMddHHmmssSSS") + idGenerationService.getNextStringId();
		vo.setBoard_id(boardId);
				
		// 1. 마스터 등록
		result = commonDAO.insert("secfw.board.insertBoardMng", vo) ;
		
		// 2. 게시판명 등록
		for(int i=0; i<vo.getBoard_nm_array().length; i++) {
			vo.setBoard_nm(vo.getBoard_nm_array()[i]) ;
			vo.setLanguage_type(vo.getLanguage_type_array()[i]) ;
			
			result += insertBoardMngTitle(vo) ;
		}
		
		return result ;
	}
	
	/**
	 * 게시판명 등록
	 * @param vo BoardMngVO
	 * @return
	 * @throws Exception
	 */
	public int insertBoardMngTitle(BoardMngVO vo) throws Exception {
		
		return commonDAO.insert("secfw.board.insertBoardTitle", vo) ;
	}

	/**
	 * 게시판 마스터 상세 조회
	 * <P>
	 * 1. 마스터 조회
	 * 2. 게시판명 조회 및 세팅
	 * </P>
	 * @param vo BoardMngVO
	 * @return
	 * @throws Exception
	 */
	public Map detailBoardMng(BoardMngVO vo) throws Exception {
		// 1. 마스터 조회
		List list = commonDAO.list("secfw.board.detailBoardMng", vo) ;
		Map result = null ;
		
		// 2. 게시판명 리스트 조회 및 세팅
		if(list!=null && list.size()>0){
			result = (Map)list.get(0) ;
			list = listBoardTitle(vo) ;
			
			result.put("board_nm_list", list) ;
		} else {
			result = new ListOrderedMap() ;
		}
		
		return result;
	}

	/**
	 * 게시판명 리스트 조회
	 * @param vo BoardMngVO
	 * @return
	 * @throws Exception
	 */
	public List listBoardTitle(BoardMngVO vo) throws Exception {
		return commonDAO.list("secfw.board.listBoardTitle", vo) ;
	}
	
	/**
	 * 게시판 마스터 수정
	 * <P>
	 * 1. 마스터 수정
	 * 2. 게시판명 수정
	 * </P>
	 * @param vo BoardMngVO
	 * @return
	 * @throws Exception
	 */
	public int modifyeBoardMng(BoardMngVO vo) throws Exception {
		int result = 0 ;
				
		// 1. 마스터 수정
		result = commonDAO.modify("secfw.board.modifyBoardMng", vo) ;
		
		// 2. 게시판명 수정
		for(int i=0; i<vo.getBoard_nm_array().length; i++) {
			vo.setBoard_nm(vo.getBoard_nm_array()[i]) ;
			vo.setLanguage_type(vo.getLanguage_type_array()[i]) ;
			
			result += modifyBoardMngTitle(vo) ;
		}
		
		return result ;
	}
	
	/**
	 * 게시판명 수정
	 * 
	 * @param vo BoardMngVO
	 * @return
	 * @throws Exception
	 */
	public int modifyBoardMngTitle(BoardMngVO vo) throws Exception {
		return commonDAO.modify("secfw.board.modifyBoardTitle", vo) ;
	}

	/**
	 * 게시판관리 삭제
	 * 실제는 DEL_YN 을 "Y"로 업데이트
	 * @param vo BoardMngVO
	 * @return
	 * @throws Exception
	 */
	public int deleteBoardMng(BoardMngVO vo) throws Exception {
		vo.setDel_yn("Y") ;
		return commonDAO.delete("secfw.board.deleteBoardMng", vo) ;
	}

	/**
	 * 게시판 권한 리스트
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List listBoardAuth(BoardMngVO vo) throws Exception {

		return commonDAO.list("secfw.board.listBoardAuth", vo) ;
	}
}
