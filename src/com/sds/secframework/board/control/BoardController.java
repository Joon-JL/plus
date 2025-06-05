package com.sds.secframework.board.control;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.board.dto.BoardForm;
import com.sds.secframework.board.dto.BoardMngForm;
import com.sds.secframework.board.dto.BoardMngVO;
import com.sds.secframework.board.dto.BoardVO;
import com.sds.secframework.board.service.BoardMngService;
import com.sds.secframework.board.service.BoardService;
import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.BoardUtil;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.FileUtil;
import com.sds.secframework.common.util.ObjectCopyUtil;
import com.sds.secframework.util.service.ComboService;

/**
 */
public class BoardController extends CommonController {
	/**
	 * 게시판 관리 서비스
	 */
	private BoardMngService boardMngService ;
	
	/**
	 * 게시판 관리 서비스 세팅
	 * @param boardMngService
	 */
	public void setBoardMngService(BoardMngService boardMngService) {
		this.boardMngService = boardMngService;
	}
	
	/**
	 * 게시글 서비스
	 */
	private BoardService boardService ;
	
	/**
	 * 게시글 서비스 세팅
	 * @param boardService BoardService
	 */
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	/**
	 * 콤보 서비스
	 */
	ComboService comboService = null ;
	
	/**
	 * 콤보 서비스 세팅
	 * @param comboService
	 */
	public void setComboService(ComboService comboService) {
		this.comboService = comboService ;
	}


	/**
	 * 게시글  리스트 조회
	 * 
	 * @param reuqest HttpServletRequest
	 * @param response HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardMngForm mngForm = new BoardMngForm() ;
		BoardMngVO mngVo = new BoardMngVO() ;
		BoardForm form = new BoardForm() ;
		BoardVO vo = new BoardVO() ;
		
		Locale locale = new RequestContext(request).getLocale() ;
	
		// 기본 설정값 세팅
		setCommonInfo(mngVo, request) ;
		
		//bind
		bind(request, mngForm) ;
		bind(request, mngVo) ;
		
		bind(request, form) ;
		
		
		return listBoard(mngForm, mngVo, form, vo, locale) ;
	}
	
	/**
	 * 게시글 리스트 조회
	 * @param mngForm BoardMngForm
	 * @param mngVo BoardMngVO
	 * @param form BoardForm
	 * @param vo BoardVO
	 * @param locale Locale
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listBoard(BoardMngForm mngForm, BoardMngVO mngVo, BoardForm form,  BoardVO vo, Locale locale) throws Exception {
		ModelAndView mav = new ModelAndView() ;
		
		// 1. 게시판 마스터 가져오기 
		Map boardMngMap = boardMngService.detailBoardMng(mngVo) ;
		ObjectCopyUtil.copyMapToVo(boardMngMap, mngVo) ;
		ObjectCopyUtil.copyMapToVo(boardMngMap, mngForm) ;
		
		// 2. 해당 언어 타입에 맞는 게시판명 세팅
		BoardUtil.setBoardNm(mngForm, (List)boardMngMap.get("board_nm_list"), mngVo.getDefaultLocale()) ;
		
		// 3. 페이지수 콤보 만들기
		if(mngVo.getRow_cnt_yn().equals("Y")) {
			String rowUnitMsg = messageSource.getMessage("secfw.page.field.bbs.row_cnt_unit", null, locale) ;
			mngForm.setRow_cnt_combo(BoardUtil.getRowCntCombo(mngVo.getRow_cnt(), form.getUser_row_cnt(), rowUnitMsg)) ;
		}
		
		// 4. 구분이 있을 경우 구분 콤보 가져오기
		mngForm.setDiv_cd_combo(getDivCdComob(mngVo, form.getSrch_div_cd(), "", messageSource.getMessage("secfw.page.button.select", null, locale), "F")) ;
		
		String forwardURL = "/WEB-INF/jsp/secfw/board/" + mngForm.getBoard_type() + "/BoardList.jsp" ;
	
		mav.setViewName(forwardURL) ;
		mav.addObject("command", form) ;
		mav.addObject("mngCommand", mngForm) ;
		
		return mav ;
	}
		
	/**
	 * 게시글  등록 화면으로 이동
	 * 
	 * @param reuqest HttpServletRequest
	 * @param response HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardInsertBoardForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardMngForm mngForm = new BoardMngForm() ;
		BoardMngVO mngVo = new BoardMngVO() ;
		
		BoardForm form = new BoardForm() ;
		
		ModelAndView mav = new ModelAndView() ;
		
		// 기본 설정값 세팅
		setCommonInfo(mngVo, request) ;
		
		//bind
		bind(request, mngForm) ;
		bind(request, mngVo) ;
		
		bind(request, form) ;
		
		// 1. 게시판 마스터 가져오기 
		Map boardMngMap = boardMngService.detailBoardMng(mngVo) ;
		ObjectCopyUtil.copyMapToVo(boardMngMap, mngVo) ;
		ObjectCopyUtil.copyMapToVo(boardMngMap, mngForm) ;
		
		// 2. 해당 언어 타입에 맞는 게시판명 세팅
		BoardUtil.setBoardNm(mngForm, (List)boardMngMap.get("board_nm_list"), mngVo.getDefaultLocale()) ;
		
		// 3. 구분이 있을 경우 구분 콤보 가져오기
		mngForm.setDiv_cd_combo(getDivCdComob(mngVo, form.getSrch_div_cd(), null, null, null)) ;
		
		// 4. 유효일자설정여부가 Y인 경우 유효일자에 기본값 설정
		//     from : 오늘일자, to : 오늘일자 + 기본유효일수
		if(mngForm.getHold_day_yn().equals("Y")) {
			form.setHold_start_ymd(DateUtil.formatDate(DateUtil.today(), "-")) ;
			form.setHold_end_ymd(DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), mngForm.getDefault_hold_day()), "-")) ;
		}
		
		String forwardURL = "/WEB-INF/jsp/secfw/board/" + mngForm.getBoard_type() + "/BoardInsert.jsp" ;
		
		setCommonInfo(mngVo, request) ;
		mav.setViewName(forwardURL) ;
		mav.addObject("mngCommand", mngForm) ;
		mav.addObject("command", form) ;
		return mav ;
	}
	
	/**
	 * 게시글  등록
	 * 
	 * @param reuqest HttpServletRequest
	 * @param response HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	public ModelAndView insertBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardMngForm mngForm = new BoardMngForm() ;
		BoardMngVO mngVo = new BoardMngVO() ;

		BoardForm form = new BoardForm() ;
		BoardVO vo = new BoardVO() ;
		
		ModelAndView mav = new ModelAndView() ;
		
		// 기본 설정값 세팅
		setCommonInfo(mngVo, request) ;
		setCommonInfo(vo, request) ;
		
		//bind
		bind(request, mngForm) ;
		bind(request, mngVo) ;
		
		bind(request, form) ;
		bind(request, vo) ;
		
		try {
			// 1. 게시판 마스터 가져오기 
			Map boardMngMap = boardMngService.detailBoardMng(mngVo) ;
			ObjectCopyUtil.copyMapToVo(boardMngMap, mngVo) ;
			ObjectCopyUtil.copyMapToVo(boardMngMap, mngForm) ;
			
			
			/*
			MultipartFile imgFile = form.getImg_file() ;
			String saveDir = request.getRealPath("") + "/upload/board/"+form.getBoard_id() + "/" ;
			String fileName =  imgFile.getOriginalFilename();
			String contentType = imgFile.getContentType() ;			
			*/
			
			
			// 2. 등록
			int result = boardService.insertBoard(mngVo, vo) ;
			
			if(result==1) {
				Locale locale = new RequestContext(request).getLocale() ;
				
				// 등록화면의 검색 조건을 초기화한다.(등록후 리스트로 돌아갈 때에는 리스트에서 가져온 검색조건을 초기화)
				form.setSrch_div_cd(null) ;
				form.setSrch_type(null) ;
				form.setSrch_word(null) ;
				form.setUser_row_cnt(0) ;
				form.setCurPage("1") ;
				
				vo.setSrch_div_cd(null) ;
				vo.setSrch_type(null) ;
				vo.setSrch_word(null) ;
				vo.setUser_row_cnt(0) ;
				form.setCurPage("1") ;
				
				mav = listBoard(mngForm, mngVo, form, vo, locale) ;
			} else {
			
				
			}
		} catch (Exception e) {
			String forwardURL = "/WEB-INF/jsp/secfw/board/" + mngForm.getBoard_type() + "/BoardList.jsp" ;
			//returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale()) ;
						
			mav.setViewName(forwardURL) ;
			mav.addObject("mngCommand", mngForm) ;
			mav.addObject("command", form) ;
		}
		
		return mav ;
	}
	
	
	
	/**
	 * 구분코드가 있는 경우 구분코드를 콤보 option으로 리턴한다.
	 * @param vo BoardMngVO
	 * @param selectedValue 기본 선택할 value
	 * @param addValue 추가할 option의 value
	 * @param addText 추가할 option의 text
	 * @param addPosition 추가할 위치 (F:맨 처음, L:맨 마지막)
	 * @return
	 * @throws Exception
	 */
	private String getDivCdComob(BoardMngVO vo, String selectedValue, String addValue, String addText, String addPosition) throws Exception{
		String combo = "" ;
		
		if(vo.getDiv_yn().equals("Y")) {
			HashMap hm = new HashMap() ;
			hm.put("sys_cd", vo.getSys_cd()) ;
			hm.put("grp_cd", vo.getDiv_cd()) ;
			hm.put("use_yn", "Y") ;
			hm.put("selected", selectedValue) ;
			hm.put("language", vo.getDefaultLocale()) ;
			hm.put("abbr", "") ;

			combo = comboService.getCommonCodeCombo(hm) ;
						
			// 추가되는 option 있는 경우 option 을 해당 위치에 추가
			if(addValue!=null) {
				String newCombo = "<option value=\"" + addValue + "\">" + addText + "</option>" ;
				
				// 맨 처음에 추가
				if(addPosition.equals("F")) {
					combo = newCombo + combo ; 
				}
				// 맨 마지막에 추가
				else if (addPosition.equals("L")) {
					combo = combo + newCombo ; 
				}
			}
		}
		
		
		return combo ;
	
	}
	
}
