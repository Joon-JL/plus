package com.sds.secframework.common.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.board.dto.BoardMngForm;
import com.sds.secframework.board.dto.BoardMngVO;
import com.sds.secframework.board.service.BoardMngService;
import com.sds.secframework.board.service.impl.BoardMngServiceImpl;
import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.ObjectCopyUtil;

public class BoardAuthCheckInterceptor extends HandlerInterceptorAdapter {

	/**
	 * CommonDAO에 대한 DAO선언 및 세팅
	 * @param commonDAO
	 */
	private CommonDAO commonDAO;
	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}

	/**
	 * MessageSource 선언 및 세팅
	 */
	private MessageSource messageSource;
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	/**
	 * PropertyService 선언 및 세팅
	 */
	private PropertyService propertyService;
	public void setPropertyService(PropertyService propertyService) {
		this.propertyService = propertyService;
	}
	
	/**
	 * Locale 선언 및 세팅
	 */
	private LocaleResolver localeResolver;
	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}
	
	/**
	 * 
	 */
	public boolean preHandle(HttpServletRequest request,	HttpServletResponse response, Object handler) throws Exception {
	
		boolean result = true ;
		String method = request.getParameter("method") ;
		String errorPage = "/secfw/boardErrorDeleted.do" ;
		String errorTitle = null ;
		String errorMessage = null ;
		
		BoardMngVO mngVo = new BoardMngVO() ;
		BoardMngForm mngForm = new BoardMngForm() ;
		
		// 1. BoardMngService 생성
		BoardMngServiceImpl boardMngServiceImpl = new BoardMngServiceImpl() ;
		boardMngServiceImpl.setCommonDAO(commonDAO) ;
		
		BoardMngService boardMngService = (BoardMngService)boardMngServiceImpl ;
		
		// 2. 파라메터 값과 기본 설정 값을 vo에 세팅
		ObjectCopyUtil.copyRequestParameterToVo(request, mngVo) ;
		
		// 시스템 설정값
		mngVo.setSys_cd(propertyService.getProperty("secfw.sysCd")) ;
		mngVo.setDefaultLocale(propertyService.getProperty("secfw.defaultLocale")) ;
				
		// 3. 게시판 마스터 / 권한 정보 가져오기 
		Map map = boardMngService.detailBoardMng(mngVo) ; // 게시판 마스터
		ObjectCopyUtil.copyMapToVo(map, mngForm) ;
		
		List boardAuthList = boardMngService.listBoardAuth(mngVo) ; // 게시판 권한
		
		// 4. 삭제 여부 확인
		if(result && mngForm.getDel_yn().equals("Y")) {
			result = false ;
			errorTitle = messageSource.getMessage("secfw.msg.error.deletedBoard.title", null, new RequestContext(request).getLocale()) ; ;
			errorMessage = messageSource.getMessage("secfw.msg.error.deletedBoard.message", null, new RequestContext(request).getLocale()) ; ;
		}
		
		// 5. 사용여부 확인
		if (result && mngForm.getUse_yn().equals("N")) {
			result = false ;
			errorTitle = messageSource.getMessage("secfw.msg.error.noUseBoard.title", null, new RequestContext(request).getLocale()) ;
			errorMessage = messageSource.getMessage("secfw.msg.error.noUseBoard.message", null, new RequestContext(request).getLocale()) ;
		}
		// 6. 등록(수정) 페이지 이동이나 등록(수정,삭제)시 등록/수정/삭제 권한 체크
		if(result && (method.equals("forwardInsertBoardForm") || // 등록페이지 이동
				          method.equals("insertBoard") ||  // 등록
				          method.equals("forwardModifyBoardForm") || // 수정페이지 이동 
				          method.equals("modifyBoard") ||  // 수정
				          method.equals("deleteBoard"))) {// 삭제
			result = checkBoardAuth(request, mngForm, boardAuthList, "C") ;
			if(!result) {
				errorTitle = messageSource.getMessage("secfw.msg.error.noAuthBoard.title", null, new RequestContext(request).getLocale()) ;
				errorMessage = messageSource.getMessage("secfw.msg.error.noAuthBoard.message", null, new RequestContext(request).getLocale()) ;
			}
		}
		// 7. 댓글 권한 체크
		else if(result && (method.equals("insertBoardAppend") || // 댓글 입력
				                  method.equals("modifyBoardAppend") || // 댓글 수정
				                  method.equals("deleteBoardAppend"))) { // 댓글 삭제
			result = checkBoardAuth(request, mngForm, boardAuthList, "M") ;
			if(!result){
				errorTitle = messageSource.getMessage("secfw.msg.error.noAuthBoard.title", null, new RequestContext(request).getLocale()) ;
				errorMessage = messageSource.getMessage("secfw.msg.error.noAuthBoard.message", null, new RequestContext(request).getLocale()) ;
			}
		}
		// 8. 답글 권한 체크
	
		
		
		
		if(!result){
			mngForm.setReturn_title(errorTitle) ;
			mngForm.setReturn_message(errorMessage) ;
			
			ModelAndView mav = new ModelAndView() ;
			mav.setViewName(errorPage) ;
			mav.addObject("command", mngForm) ;
			throw new ModelAndViewDefiningException(mav) ;
		}
	
		return super.preHandle(request, response, handler);
	}
	
	private boolean checkBoardAuth(HttpServletRequest request, BoardMngForm form, List boardAuthList, String authType) throws Exception{
		
		boolean result = false ;
		HttpSession session = request.getSession() ;

		// 사용자 권한
		List userRoleList = (List)session.getAttribute("secfw.session.role") ;
	
		// 게시판 권한 체크
		for(int i=0; i<userRoleList.size(); i++) {
			Map userRoleMap = (Map)userRoleList.get(i) ;
			String userRoleCd = (String)userRoleMap.get("role_cd") ;
			result = checkBoardAuth(boardAuthList, userRoleCd, authType) ;
			if(result) {
				break ;
			}
		}
		
		if(!result) {
			
			form.setReturn_title(messageSource.getMessage("secfw.msg.error.noAuthBoard.title", null, new RequestContext(request).getLocale())) ;
			form.setReturn_message(messageSource.getMessage("secfw.msg.error.noAuthBoard.message", null, new RequestContext(request).getLocale())) ;
		}
		
		return result ;
		
	}
	
	
	/**
	 * 게시판 권한 체크
	 * 사용자 Role(userRoleCd)이 체크하고자 하는 권한(authCd)으로 게시판 권한 리스트에(boardAuthList) 있는지 확인.
	 * 있을 경우 true 리턴
	 * 
	 * @param boardAuthList 게시판 권한 리스트
	 * @param userRoleCd 사용자의 Role
	 * @param authCd 체크 하고자 하는 권한 (C:등록권한, A:답변권한, M:댓글권한)
	 * @return
	 * @throws Exception
	 */
	private boolean checkBoardAuth(List boardAuthList, String userRoleCd, String authCd) throws Exception {
		boolean result = false ;
		
		for(int i=0; i<boardAuthList.size(); i++) {
			Map boardAuthMap = (Map)boardAuthList.get(i) ;
			String boardRoleCd = (String)boardAuthMap.get("role_cd") ;
			String boardAuthCd = (String)boardAuthMap.get("auth_cd") ;
			if(boardAuthCd.equals(authCd) && boardRoleCd.equals(userRoleCd)) {
				result = true ;
				break ;
			}
		}
		
		return result ;
	}
	
	
}
