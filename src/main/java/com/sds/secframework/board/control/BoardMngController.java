package com.sds.secframework.board.control;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.board.dto.BoardMngForm;
import com.sds.secframework.board.dto.BoardMngVO;
import com.sds.secframework.board.service.BoardMngService;
import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.BoardUtil;
import com.sds.secframework.common.util.ObjectCopyUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;

/**
 */
public class BoardMngController extends CommonController {
	private BoardMngService boardMngService ;
	
	/**
	 * 게시판 관리 서비스 세팅
	 * @param boardMngService
	 */
	public void setBoardMngService(BoardMngService boardMngService) {
		this.boardMngService = boardMngService;
	}

	/**
	 * 게시판 관리 리스트 조회
	 * 
	 * @param reuqest HttpServletRequest
	 * @param response HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listBoardMng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardMngForm form = new BoardMngForm() ;
		BoardMngVO vo = new BoardMngVO() ;
		
		//bind
		bind(request, form) ;
		bind(request, vo) ;
		
		setCommonInfo(vo, request) ;
		
		return listBoardMng(form, vo) ;
	}
	
	/**
	 * 게시판 관리 리스트를 조회
	 * <P>
	 * 1. 리스트 정보 설정(현재페이지, 페이지당 ROW 수, 시작index, 종료index)
	 * 2. 리스트 조회 서비스 호출
	 * 3. 결과 List 를 form에 세팅
	 * 4. 페이징 처리
	 * </P>
	 * @param form BoardMngForm
	 * @param vo BoardMngVO
	 * @return
	 * @throws Exception
	 */
	private ModelAndView listBoardMng(BoardMngForm form, BoardMngVO vo) throws Exception {
		
		PageUtil pageUtil = new PageUtil() ;
		ModelAndView mav = new ModelAndView() ;
		
		String forwardURL = "/WEB-INF/jsp/secfw/board/BoardMngList.jsp" ;
		
		//1. 리스트 정보 설정
		pageUtil.setThisPage(StringUtil.nvl(form.getCurPage(),"1")) ;
		pageUtil.setRowPerPage(10) ;
		vo.setStart_index(pageUtil.getStartIndex()) ;
		vo.setEnd_index(pageUtil.getEndIndex()) ;
		
		//2. 리스트 조회 서비스 호출
		List resultList = boardMngService.listBoardMng(vo) ;
		
		//3. 결과 세팅
		form.setResultList(resultList) ;
		form.setReturn_message("") ;
		
		//4. 페이징 처리
		pageUtil.setTotalRow(pageUtil.getTotalRow(resultList, "total_cnt")) ;
		pageUtil.setGroup() ;

		mav.setViewName(forwardURL) ;
		mav.addObject("command", form) ;
		mav.addObject("pageUtil", pageUtil) ;
		return mav ;
	}
	
	/**
	 * 게시판 관리 등록화면으로 이동
	 * @param reuqest HttpServletRequest
	 * @param response HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardInsertBoardMngForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardMngForm form = new BoardMngForm() ;
		ModelAndView mav = new ModelAndView() ;
		
		String forwardURL = "/WEB-INF/jsp/secfw/board/BoardMngInsert.jsp" ;
		
		//bind
		bind(request, form) ;
		
		mav.setViewName(forwardURL) ;
		mav.addObject("command", form) ;
		return mav ;
	}
	
	/**
	 * 게시판 관리 등록
	 * <P>
	 * 1. VO에 기봇값 세팅
	 * 2. 게시판 title 세팅
	 * 3. 등록 서비스 호출
	 * 4. 성공하면 리스트 화면으로 이동
	 *    - 검색조건 초기화
	 *    - 1페이지로 이동
	 * 5. 실패하면 등록 화면으로 이동
	 *    - 에러 메시지 세팅
	 * </P>
	 * @param reuqest HttpServletRequest
	 * @param response HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	public ModelAndView insertBoardMng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardMngForm form = new BoardMngForm() ;
		BoardMngVO vo = new BoardMngVO() ;
		ModelAndView mav = new ModelAndView() ;
		
		String forwardURL = "/WEB-INF/jsp/secfw/board/BoardMngList.jsp" ;
		String returnMessage = null ;
		
		//bind
		bind(request, form) ;
		bind(request, vo) ;
		
		// 1. VO에 기본값 세팅
		setCommonInfo(vo, request) ;
        
        // 2. 게시판 title 세팅
        vo.setBoard_nm_array(new String[]{form.getBoard_nm_ko(), form.getBoard_nm_en()}) ;
        vo.setLanguage_type_array(new String[]{"ko","en"}) ;
        
		try {
			// 3. 등록 서비스 호출
			int result = boardMngService.insertBoardMng(vo) ;
			
			// 4. 등록에 성공하면 리스트로 이동한다.
			//    모든 검색 조건은 초기화 되며 1페이지로 이동한다.
			if(result>0) {
				
				returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale()) ;
				
				// 등록화면의 검색 조건을 초기화한다.(등록후 리스트로 돌아갈 때에는 리스트에서 가져온 검색조건을 초기화)
				form.setSrch_board_nm(null) ;
				form.setSrch_use_yn(null) ;
				form.setCurPage("1") ;
				
				vo.setSrch_board_nm(null) ;
				vo.setSrch_use_yn(null) ;
				vo.setCurPage("1") ;
				
				mav = listBoardMng(form, vo) ;
			} else {
				throw new Exception() ;
			}
		} catch (Exception e) {
			// 5. 실패하면 에러메시지 세팅 후 등록화면으로 이동
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale()) ;
			forwardURL = "/WEB-INF/jsp/secfw/board/BoardMngInsert.jsp" ;
			e.printStackTrace();
		}
		
		// 리턴 메시지 
		form.setReturn_message(returnMessage) ;
		
		mav.setViewName(forwardURL) ;
		mav.addObject("command", form) ;
		return mav ;
	}
	
	/**
	 * 게시판 관리 상세로 이동
	 * @param reuqest HttpServletRequest
	 * @param response HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardModifyBoardMngForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardMngForm form = new BoardMngForm() ;
		BoardMngVO vo = new BoardMngVO() ;
		
		//bind
		bind(request, form) ;
		bind(request, vo) ;
		
		setCommonInfo(vo, request) ;
		
		return detailBoardMng(form, vo) ;
	}
	
	/**
	 * 게시판 관리 상세
	 * @param form BoardMngForm
	 * @param vo BoardMngVO
	 * @return
	 * @throws Exception
	 */
	public ModelAndView detailBoardMng(BoardMngForm form, BoardMngVO vo) throws Exception {
		ModelAndView mav = new ModelAndView() ;
		
		String forwardURL = "/WEB-INF/jsp/secfw/board/BoardMngInsert.jsp" ;
		
		// 상세조회
		Map result = boardMngService.detailBoardMng(vo) ;
		ObjectCopyUtil.copyMapToVo(result, form) ;
		
		// 게시판명 해당 언어 필드에 세팅
		BoardUtil.setBoardNm(form, form.getBoard_nm_list()) ;
		
		// 리턴 메시지 
		form.setReturn_message("") ;
		
		mav.setViewName(forwardURL) ;
		mav.addObject("command", form) ;
		return mav ;
	}
	
	/**
	 * 게시판 관리 수정
	 * <P>
	 * 1. VO에 기봇값 세팅
	 * 2. 게시판 title 세팅
	 * 3. 수정 서비스 호출
	 * 4. 성공하면 상세 화면으로 이동
	 * 5. 실패하면 수정 화면으로 이동
	 *    - 에러 메시지 세팅
	 * </P>
	 * @param reuqest HttpServletRequest
	 * @param response HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	public ModelAndView modifyBoardMng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardMngForm form = new BoardMngForm() ;
		BoardMngVO vo = new BoardMngVO() ;
		ModelAndView mav = new ModelAndView() ;
		
		String forwardURL = "/WEB-INF/jsp/secfw/board/BoardMngList.jsp" ;
		String returnMessage = null ;
		
		//bind
		bind(request, form) ;
		bind(request, vo) ;
		
		// 1. VO에 기본값 세팅
		setCommonInfo(vo, request) ;
        
        // 2. 게시판 title 세팅
        vo.setBoard_nm_array(new String[]{form.getBoard_nm_ko(), form.getBoard_nm_en()}) ;
        vo.setLanguage_type_array(new String[]{"ko","en"}) ;
        
		try {
			// 3. 게시판 관리 수정 서비스 호출
			int result = boardMngService.modifyeBoardMng(vo) ;
			
			// 4. 수정에 성공하면 상세화면으로 이동한다.
			if(result>0) {
				returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale()) ;
				mav = detailBoardMng(form, vo) ;
			} else {
				throw new Exception() ;
			}
		} catch (Exception e) {
			// 5. 실패하면 에러메시지 세팅 후 수정화면으로 이동 
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale()) ;
			forwardURL = "/WEB-INF/jsp/secfw/board/BoardMngInsert.jsp" ;
			mav.setViewName(forwardURL) ;
			e.printStackTrace();
		}
		
		// 리턴 메시지 
		form.setReturn_message(returnMessage) ;
		
		mav.addObject("command", form) ;
		return mav ;
	}
	
	/**
	 * 게시판 관리 삭제
	 * <P>
	 * 1. VO에 기봇값 세팅
	 * 2. 삭제 서비스 호출
	 * 3. 성공하면 리스트 화면으로 이동
	 *    - 검색조건 초기화
	 *    - 1페이지로 이동
	 * 4. 실패하면 수정 화면으로 이동
	 *    - 에러 메시지 세팅
	 * </P>
	 * @param reuqest HttpServletRequest
	 * @param response HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteBoardMng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardMngForm form = new BoardMngForm() ;
		BoardMngVO vo = new BoardMngVO() ;
		ModelAndView mav = new ModelAndView() ;
		
		String forwardURL = "/WEB-INF/jsp/secfw/board/BoardMngList.jsp" ;
		String returnMessage = null ;
		
		//bind
		bind(request, form) ;
		bind(request, vo) ;
		
		// 1. VO에 기본값 세팅
		setCommonInfo(vo, request) ;
		
		try {
			//2. 삭제 서비스 호출
			int result = boardMngService.deleteBoardMng(vo) ;
			
			if(result>0) {
				
				// 3. 삭제에 성공하면 리스트로 이동한다.
				//    모든 검색 조건은 초기화 되며 1페이지로 이동한다.
				returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale()) ;
				
				// 상세화면의 검색 조건을 초기화한다.(삭제 후 리스트로 돌아갈 때에는 리스트에서 가져온 검색조건을 초기화)
				form.setSrch_board_nm(null) ;
				form.setSrch_use_yn(null) ;
				form.setCurPage("1") ;
				
				vo.setSrch_board_nm(null) ;
				vo.setSrch_use_yn(null) ;
				vo.setCurPage("1") ;
				
				mav = listBoardMng(form, vo) ;
			} else {
				throw new Exception() ;
			}
		} catch (Exception e) {
			// 4. 실패하면 에러메시지 세팅 후 상세화면으로 이동 
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale()) ;
			forwardURL = "/WEB-INF/jsp/secfw/board/BoardMngInsert.jsp" ;
			e.printStackTrace();
		}
		
		// 리턴 메시지 
		form.setReturn_message(returnMessage) ;
		
		mav.setViewName(forwardURL) ;
		mav.addObject("command", form) ;
		return mav ;
	}
	
	
}
