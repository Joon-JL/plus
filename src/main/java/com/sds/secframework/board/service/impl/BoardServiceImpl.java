package com.sds.secframework.board.service.impl;

import anyframe.core.idgen.IIdGenerationService;

import com.sds.secframework.board.dto.BoardMngVO;
import com.sds.secframework.board.dto.BoardVO;
import com.sds.secframework.board.service.BoardService;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.StringUtil;

public class BoardServiceImpl extends CommonServiceImpl implements BoardService {

	IIdGenerationService idGenerationService ;
	public void setIdGenerationService(IIdGenerationService idGenerationService) {
		this.idGenerationService = idGenerationService;
	}
	
	/**
	 * 게시글을 등록한다.
	 * @param vo BoardMngVO
	 * @return
	 * @throws Exception
	 */
	public int insertBoard(BoardMngVO mngVo, BoardVO vo) throws Exception {
		int result = 0 ;
		
		
		
		try {
			// 1. 첨부파일 등록
			String fileRefNo = insertFile(vo, null) ;
			vo.setFile_ref_no(fileRefNo) ;
			
			// 2. 게시글 등록
			// 2-1. 게시글 ID 세팅
			vo.setNotice_id(DateUtil.getTime("yyyyMMddHHmmssSSS") + idGenerationService.getNextStringId());
			
			// 2-2. 유효일자에서 "-" 삭제
			if(mngVo.getHold_day_yn().equals("Y")) {
				vo.setHold_day_yn(mngVo.getHold_day_yn()) ;
				vo.setHold_start_ymd(StringUtil.removeChar(vo.getHold_start_ymd(), "-")) ;
				vo.setHold_end_ymd(StringUtil.removeChar(vo.getHold_end_ymd(), "-")) ;
			}
			
			
			result = commonDAO.insert("secfw.board.insertBoard", vo) ;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 첨부파일 처리 
	 * @param vo BoardMngVO
	 * @param div 구분(삭제일때만 "delete"로 넘겨주고 그외에는 null을 세팅한다.
	 * @return
	 * @throws Exception
	 */
	public String insertFile(BoardVO vo,String div) throws Exception{
		String fileRefNo = null ;;
		
		// 첨부파일이 있는 경우
		if(vo.getFileInfos()!=null && !vo.getFileInfos().equals("")) {
			
			// 삭제인 경우
			if(div!=null && div.equals("delete")){
				//attachFileService.FileDelete(vo);
			}else{
				fileRefNo = StringUtil.bvl(vo.getFile_ref_no(),"") ;
				
				// 등록인 경우
				if(fileRefNo==null||fileRefNo.equals("")||fileRefNo.equals("null")){
					//fileRefNo=attachFileService.FileInsert(vo);
				}
				// 수정인 경우
				else{
					//attachFileService.FileDelete(vo);
					//attachFileService.FileUpdate(vo);			
				}
			}
		}

		return fileRefNo;
	}

}
