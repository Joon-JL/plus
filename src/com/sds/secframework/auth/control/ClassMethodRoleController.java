package com.sds.secframework.auth.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.auth.dto.ClassMethodRoleForm;
import com.sds.secframework.auth.dto.ClassMethodRoleVO;
import com.sds.secframework.auth.service.ClassMethodRoleService;
import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.BoardPageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.common.util.Token;


public class ClassMethodRoleController extends CommonController{

	private ClassMethodRoleService ClassMethodRoleService = null;

	public void setClassMethodRoleService(
			ClassMethodRoleService ClassMethodRoleService) {
		this.ClassMethodRoleService = ClassMethodRoleService;
	}

	/**
	 * 클래스 메소드 리스트 출력 함수
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ClassMethodRoleForm form = new ClassMethodRoleForm();
		ClassMethodRoleVO vo = new ClassMethodRoleVO();

		bind(request, form);
		bind(request, vo);

		//기본정보 세팅
		setCommonInfo(vo, request);

		return listPage(request, form, vo);

	}

	/**
	 * 클래스 메소드 리스트 출력 함수 
	 * @param request
	 * @param form
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listPage(HttpServletRequest request, ClassMethodRoleForm form, ClassMethodRoleVO vo) throws Exception{
		String forwardURL = "/WEB-INF/jsp/secfw/auth/ClassMethodRoleList.jsp";
		BoardPageUtil pageUtil = new BoardPageUtil() ;
		ModelAndView mav = new ModelAndView();
		List ClassMethodRoleList = null;

		//1. 리스트 정보 설정
		pageUtil.setThisPage(StringUtil.nvl(form.getCurPage(),"1")) ;
		pageUtil.setRowPerPage(form.getRow_cnt()) ;
		vo.setStart_index(pageUtil.getStartIndex()) ;
		vo.setEnd_index(pageUtil.getEndIndex()) ;

		//서비스 수행
		ClassMethodRoleList = ClassMethodRoleService.listPage(vo);
		form.setClass_method_role_list(ClassMethodRoleList);

		//페이징 처리
		pageUtil.setTotalRow(pageUtil.getTotalRow(ClassMethodRoleList, "TOTAL_CNT"));
		pageUtil.setGroup() ;

		mav.setViewName(forwardURL);
		mav.addObject("command", form);
		mav.addObject("pageUtil", pageUtil) ;
		return mav;	
	}

	/**
	 * 메소드를 등록하는 페이지로 이동한다.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardInsertForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav = new ModelAndView();
		ClassMethodRoleForm form = new ClassMethodRoleForm() ;
		ClassMethodRoleVO vo = new ClassMethodRoleVO();
		String forwardURL = "/WEB-INF/jsp/secfw/auth/ClassMethodRoleInsert.jsp" ;
		List availableRoleList = null;

		//bind
		bind(request, vo);
		bind(request, form) ;

		//사용자정보 세팅
		setCommonInfo(vo, request);

		//설정권한리스트 초기화
		//설정가능권한에 모든 권한, 설정된 권한 null로 초기화
		availableRoleList = ClassMethodRoleService.listNotRole(vo);
		form.setAvailable_role_list(availableRoleList);
		form.setAssigned_role_list(null);

		mav.setViewName(forwardURL);
		mav.addObject("command", form);
		return mav;
	}

	/**
	 * 클래스 메소드 상세조회페이지(수정페이지)로 이동한다.
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardModifyForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ClassMethodRoleForm form = new ClassMethodRoleForm() ;
		ClassMethodRoleVO vo = new ClassMethodRoleVO();
		
		//bind
		bind(request, vo);
		bind(request, form) ;

		//사용자정보 세팅
		setCommonInfo(vo, request);
		return forwardModifyForm(request, form, vo);
	}

	public ModelAndView forwardModifyForm(HttpServletRequest request, ClassMethodRoleForm form, ClassMethodRoleVO vo) throws Exception{
		ModelAndView mav = new ModelAndView();
		Map map = null;
		String returnMessage = null;
		List availableRoleList = null;
		List assignedRoleList = null;
		String forwardURL = "/WEB-INF/jsp/secfw/auth/ClassMethodRoleInsert.jsp" ;

		map = ClassMethodRoleService.detail(vo);

		//조회 결과가 없을 경우 에러 메시지 세팅. 조회된 결과가 없으므로 List로 복귀한다.
		if(map.size() == 0){
			returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale()) ;
			form.setReturn_message(returnMessage);
			mav = listPage(request, form, vo);
			mav.addObject("command", form);
			return mav;
		}

		//설정권한리스트 초기화
		availableRoleList = ClassMethodRoleService.listNotRole(vo);
		assignedRoleList = ClassMethodRoleService.listRole(vo);
		form.setAvailable_role_list(availableRoleList);
		form.setAssigned_role_list(assignedRoleList);

		mav.setViewName(forwardURL);
		mav.addObject("command", form);
		return mav;
	}

	/**
	 * 클래스 메소드 권한 삽입
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ClassMethodRoleForm form = new ClassMethodRoleForm();
		ClassMethodRoleVO vo = new ClassMethodRoleVO();
		String[] assigned_role_list = request.getParameterValues("sel_assigned_role_list");
		String[] available_role_list = request.getParameterValues("sel_available_role_list");
		String forwardURL = "/WEB-INF/jsp/secfw/auth/ClassMethodRoleInsert.jsp";
		ModelAndView mav = new ModelAndView();
		String returnMessage = null ;
		int result = 0;

		//객체 바인딩
		bind(request, form);
		bind(request, vo);

		//사용자 정보 설정
		setCommonInfo(vo, request);

		vo.setAssigned_role_list(assigned_role_list);
		vo.setAvailable_role_list(available_role_list);

		try{
			// 중복 수정 체크
			if(Token.isValid(request)){
				//중복 등록이 아닐 시 : 등록 서비스 호출
				result = ClassMethodRoleService.insert(vo);
			}
			//중복 등록 시
			else {
				result = 100;
			}

			if(result > 0){
				setInitFormVO(request, form, vo);
				mav = listPage(request, form, vo);

				//데이터 삽입 성공 시 - 검색조건 초기화 및 list 1페이지로 이동
				if(result == 1){
					returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
					Token.resetToken(request, "TOKEN") ;
				}
			}
			else{
				//중복 데이터 실패 에러 시 - 다시 insertForm을 띄운다. 사용자가 입력했던 정보는 유지
				if(result == -100){
					returnMessage = messageSource.getMessage("secfw.msg.succ.insertDup", null, new RequestContext(request).getLocale());
					setInitRoleList(form, available_role_list, assigned_role_list);
					mav.setViewName(forwardURL);
				}
				//삽입 실패 시
				else{
					throw new Exception();
				}
			}

		} catch (Exception e){
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale());
			setInitRoleList(form, available_role_list, assigned_role_list);
			mav.setViewName(forwardURL);
			e.printStackTrace();
		}

		form.setReturn_message(returnMessage);
		mav.addObject("command",form);
		return mav;
	}

	/**
	 * 클래스 메소드 권한 수정
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView modify(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ClassMethodRoleForm form = new ClassMethodRoleForm();
		ClassMethodRoleVO vo = new ClassMethodRoleVO();
		String[] assigned_role_list = request.getParameterValues("sel_assigned_role_list");
		String[] available_role_list = request.getParameterValues("sel_available_role_list");
		String forwardURL = "/WEB-INF/jsp/secfw/auth/ClassMethodRoleInsert.jsp";
		ModelAndView mav = new ModelAndView();
		String returnMessage = null ;
		int result = 0;
		Map map = null;

		//객체 바인딩
		bind(request, form);
		bind(request, vo);

		//사용자 정보 설정
		setCommonInfo(vo, request);

		vo.setAssigned_role_list(assigned_role_list);
		vo.setAvailable_role_list(available_role_list);

		try{
			if(Token.isValid(request)){

				map = ClassMethodRoleService.detail(vo);

				//수정할 데이터가 존재하지 않으면
				if(map.size() == 0){
					returnMessage = messageSource.getMessage("secfw.msg.error.modify", null, new RequestContext(request).getLocale());
					mav = listPage(request, form, vo);
				}

				//수정할 데이터가 존재하면
				else{
					result = ClassMethodRoleService.modify(vo);
					
					//데이터 수정 성공 시
					if(result == 1){
						returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());
						mav = forwardModifyForm(request, form, vo);
						Token.resetToken(request, "TOKEN");
					}
					//수정 실패 시
					else{
						throw new Exception();
					}
				}
			}
			//isvalid = false
			else{
				mav = forwardModifyForm(request, form, vo);
			}
			
		} catch (Exception e){
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale());
			setInitRoleList(form, available_role_list, assigned_role_list);
			mav.setViewName(forwardURL);
			e.printStackTrace();
		}

		form.setReturn_message(returnMessage);
		mav.addObject("command",form);
		return mav;
	}

	/**
	 * 수정화면에서 단건 삭제시
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ClassMethodRoleForm form = new ClassMethodRoleForm();
		ClassMethodRoleVO vo = new ClassMethodRoleVO();
		ModelAndView mav = new ModelAndView();
		String forwardURL = "/WEB-INF/jsp/secfw/auth/ClassMethodRoleInsert.jsp";
		String returnMessage = null ;
		int result = 0;
		Map map = null;
		
		//객체 바인딩
		bind(request, form);
		bind(request, vo);

		try{
			if(Token.isValid(request)){
				map = ClassMethodRoleService.detail(vo);
	
				//삭제할 데이터가 존재하지 않으면
				if(map.size() == 0){
					returnMessage = messageSource.getMessage("secfw.msg.error.delete", null, new RequestContext(request).getLocale());
					mav = listPage(request, form, vo);
				}
				//삭제할 데이터 존재 시
				else{
					result = ClassMethodRoleService.delete(vo);

					//삭제 성공시 검색 조건 초기화 및 리스트 1페이지로 이동
					if(result == 1){
						setInitFormVO(request, form, vo);
						mav = listPage(request, form, vo);
						returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());
						Token.resetToken(request, "TOKEN");
					}
					//삭제 실패시
					else{
						throw new Exception();
					}
				}
			} 
			else {
				setInitFormVO(request, form, vo);
				mav = listPage(request, form, vo);
			}
				
		} catch (Exception e){
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale());
			String[] available_role_list = request.getParameterValues("sel_available_role_list");
			String[] assigned_role_list = request.getParameterValues("sel_assigned_role_list");
			setInitRoleList(form, available_role_list, assigned_role_list);
			mav.setViewName(forwardURL);
			e.printStackTrace();
		}
		form.setReturn_message(returnMessage);
		mav.addObject("command",form);
		return mav;
	}

	/**
	 * 리스트페이스에서 체크박스 선택으로 삭제시(다중삭제가능)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView deleteMulti(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ClassMethodRoleForm form = new ClassMethodRoleForm();
		ClassMethodRoleVO vo = new ClassMethodRoleVO();
		ModelAndView mav = new ModelAndView();
		String checkbox[] = request.getParameterValues("checkbox");
		String returnMessage = null ;
		int result = 0;
		Map map = null;
		StringTokenizer token = null;
		
		//객체 바인딩
		bind(request, form);
		bind(request, vo);

		//vo에 checkbox 세팅
		vo.setCheckbox(checkbox);

		try{
			if(Token.isValid(request)){

				//sys_cd : class_nm : method_nm 형식의 값으로 넘겨진 checkbox 값을
				//':'를 구분자로 하는 tokenizer를 이용해 vo에 각각의 값을 세팅한다. 
				for(int i = 0 ; i < checkbox.length ; i++){
					token = new StringTokenizer(checkbox[i],":");
					for(int j = 0 ; token.hasMoreTokens() ; j++){
						switch(j){
						case 0:
							vo.setSys_cd(token.nextToken());
						case 1:
							vo.setClass_nm(token.nextToken());
						case 2:
							vo.setMethod_nm(token.nextToken());
						}
					}

					//데이터 조회
					map = ClassMethodRoleService.detail(vo);

					//삭제할 데이터가 존재하지 않으면
					if(map.size() == 0){
						returnMessage = messageSource.getMessage("secfw.msg.error.delete", null, new RequestContext(request).getLocale());
						mav = listPage(request, form, vo);
					}
					//삭제할 데이터 존재 시
					else{
						result = ClassMethodRoleService.deleteMulti(vo);

						//삭제 성공시 검색 조건 초기화 및 리스트 1페이지로 이동
						if(result == 1){
							returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());
						}
						//삭제 실패시
						else{
							throw new Exception();
						}
					}
				}
			}
			//중복 작업이면 listPage로 이동한다.
			else{
				setInitFormVO(request, form, vo);
				mav = listPage(request, form, vo);
			}
			
			if(result == 1){
				setInitFormVO(request, form, vo);
				Token.resetToken(request, "TOKEN") ;
			}
		
		} catch (Exception e){
			returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale());
			e.printStackTrace();
		}
		mav = listPage(request, form, vo);
		form.setReturn_message(returnMessage);
		mav.addObject("command",form);
		return mav;
	}


	/**
	 * Form과 VO 검색조건 및 현재페이지 변수 초기화
	 * @param request
	 * @param form
	 * @param vo
	 * @throws Exception
	 */
	private void setInitFormVO(HttpServletRequest request, ClassMethodRoleForm form, ClassMethodRoleVO vo) throws Exception {
		form.setSrch_type("ALL") ;
		form.setClass_srch_word(null);
		form.setMethod_srch_word(null);
		form.setCurPage("1") ;

		vo.setSrch_type("ALL") ;
		vo.setClass_srch_word(null);
		vo.setMethod_srch_word(null);
		vo.setCurPage("1") ;
	}

	/**
	 * 권한 설정 list 조작
	 * @param form
	 * @param available_role_list
	 * @param assigned_role_list
	 */
	private void setInitRoleList(ClassMethodRoleForm form, String[] available_role_list, String[] assigned_role_list){
		List availableRoleList = new ArrayList() ;
		List assignedRoleList = new ArrayList();
		Map map = null ;

		available_role_list = available_role_list==null ? new String[0] : available_role_list ;
		assigned_role_list = assigned_role_list==null ? new String[0] : assigned_role_list ;

		for(int i=0; i<available_role_list.length; i++) {
			map = new HashMap() ;
			map.put("role_cd", available_role_list[i]) ;
			availableRoleList.add(map) ;
		}

		for(int i=0; i<assigned_role_list.length; i++) {
			map = new HashMap() ;
			map.put("role_cd", assigned_role_list[i]) ;
			assignedRoleList.add(map);
		}
		form.setAvailable_role_list(availableRoleList) ;
		form.setAssigned_role_list(assignedRoleList) ;
	}
}
