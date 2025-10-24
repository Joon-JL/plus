package com.sds.secframework.menu.control;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.ObjectCopyUtil;
import com.sds.secframework.menu.dto.MenuForm;
import com.sds.secframework.menu.dto.MenuVO;
import com.sds.secframework.menu.service.MenuService;

public class MenuController extends CommonController {
	
	/**
	 * 메뉴 서비스
	 */
	MenuService menuService = null ;
	
	/**
	 * 메뉴 서비스 세팅
	 * @param menuService MenuService
	 */
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService ;
	}
	
	/**
	 * Top 메뉴로 이동
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardTopMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 변수 선언
		ModelAndView mav = new ModelAndView() ;
		MenuVO vo = new MenuVO() ;
		MenuForm form = new MenuForm() ;
		MenuForm selectForm = null ;
		
		String topMenuId = null ;
		String subMenuId = null ;
		String forwardURL = "/WEB-INF/jsp/secfw/menu/TopMenu.jsp" ;
		
		HttpSession session = request.getSession(false) ;
		String comp_cd = (String)session.getAttribute("secfw.session.comp_cd");
		
		// bind
		bind(request, vo) ;
		bind(request, form) ;
		
		// 기본정보세팅
		setCommonInfo(vo, request) ;
		
		// 시작할 메뉴 ID 세팅(계층형 메뉴에서 시작점)
		vo.setUp_menu_id("root") ;
		vo.setComp_cd(comp_cd);
		
		// 사용자 권한별 메뉴 리스트 가져오기
		List list = menuService.listTreeMenuUserAuth(vo) ;
		form.setResult_list(list) ;
		
		// 선택된 메뉴의 정보
		selectForm = getMenuDetail(list, form.getSelect_menu_id(), false) ;
		
		// 선택된 메뉴가 top 메뉴일 때 sub 메뉴는 첫번째 메뉴
		if(selectForm.getUp_menu_id().equals("root")) {
			topMenuId = selectForm.getMenu_id() ;
			subMenuId = getMenuDetail(list, topMenuId, true).getMenu_id() ;
		}
		// 선택된 메뉴가 sub 메뉴일 때 top 메뉴는 sub 메뉴의 up_menu_id
		else {
			topMenuId = selectForm.getUp_menu_id() ;
			subMenuId = selectForm.getMenu_id() ;
		}
		
		mav.setViewName(forwardURL) ;
		mav.addObject("command", form) ;
		mav.addObject("top_menu_id", topMenuId) ;
		mav.addObject("sub_menu_id",subMenuId) ;
		return mav ;
	}
	
	/**
	 * Top 메뉴로 이동
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardLeftMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 변수 선언
		ModelAndView mav = new ModelAndView() ;
		MenuVO vo = new MenuVO() ;
		MenuForm form = new MenuForm() ;
		
		boolean isTopMenu = false ;
		String forwardURL = "/WEB-INF/jsp/secfw/menu/LeftMenu.jsp" ;
		
		HttpSession session = request.getSession(false) ;
		String comp_cd = (String)session.getAttribute("secfw.session.comp_cd");
		
		// bind
		bind(request, vo) ;
		bind(request, form) ;
		
		// 기본정보세팅
		setCommonInfo(vo, request) ;
		
		// 선택한 메뉴의 정보 가져오기
		vo.setMenu_id(form.getSelect_menu_id()) ;
		Map map = menuService.getMenuDetail(vo) ;
		
		// 선택한 메뉴가 TOP메뉴인지 여부
		isTopMenu = map.get("UP_MENU_ID").equals("root") ? true : false ;
		
		// 시작할 메뉴 ID 세팅(계층형 메뉴에서 시작점)
		// 선택한 메뉴가 TOP 메뉴이면 UP_MENU_ID에 MENU_ID 세팅
		if(isTopMenu) {
			vo.setUp_menu_id(vo.getMenu_id()) ;
		} else {
			vo.setUp_menu_id((String)map.get("UP_MENU_ID")) ;
		}
		
		vo.setComp_cd(comp_cd);
		
		// 사용자 권한별 메뉴 리스트 가져오기(sub 메뉴)
		List list = menuService.listTreeMenuUserAuth(vo) ;
		form.setResult_list(list) ;
		
		// 선택한 메뉴가 TOP 메뉴이면 하위 첫번째 메뉴가 select_menu_id 가 된다.
		if(isTopMenu) {
			form.setSelect_menu_id(getFirstSubMenuId(list)) ;
		}
		
		// 선택한 메뉴가 하위 메뉴가 있는지 여부 세팅
		form.setHas_sub_menu(hasSubMenu(list, form.getSelect_menu_id())) ;
				
		mav.setViewName(forwardURL) ;
		mav.addObject("command", form) ;

		return mav ;
	}
	
	/**
	 * 해당메뉴의 메뉴 상세 정보를 리스트에서 가져온다.
	 * @param list 메뉴 트리 리스트
	 * @param menuId 확인하고자 하는 메뉴
	 * @param checkUpMenuId  상위 메뉴로 확인할지 여부(true : 상위 메뉴의 서브메뉴 중 첫번째 메뉴, false : 현재 메뉴)
	 * @return
	 * @throws Exception
	 */
	private MenuForm getMenuDetail(List list, String menuId, boolean checkUpMenuId) throws Exception {
		MenuForm form = new MenuForm() ;
		
		for(int i=0; i<list.size(); i++) {
			Map map = (Map)list.get(i) ;
			if(menuId.equals(map.get(checkUpMenuId ? "up_menu_id" : "menu_id"))) {
				ObjectCopyUtil.copyMapToVo(map, form) ;
				break ;
			}
		}
		
		return form ;
	}
	
	/**
	 * 서브메뉴의 첫번째 메뉴 ID 가져오기
	 * @param list
	 * @return
	 * @throws Exception
	 */
	private String getFirstSubMenuId(List list) throws Exception {
		String firstMenuId = null ;
		if(list!=null && list.size()>0) {
			Map map = (Map)list.get(0) ;
			firstMenuId = (String)map.get("menu_id") ;
		}
		return firstMenuId ;
	}
	
	/**
	 * 하위 메뉴를 가졌는지 여부
	 * @param list
	 * @param menuId
	 * @return
	 */
	private boolean hasSubMenu(List list, String menuId) {
		boolean result = false ;
		for(int i=0; i<list.size(); i++) {
			Map map = (Map)list.get(i) ;
			if(menuId.equals(map.get("up_menu_id"))) {
				result = true ;
				break ;
			}
		}
		return result ;
	}
}
