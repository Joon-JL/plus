package com.sds.secframework.menu.control;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.util.Log;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.menu.dto.SiteMapForm;
import com.sds.secframework.menu.dto.SiteMapVO;
import com.sds.secframework.menu.service.SiteMapService;

public class SiteMapController extends CommonController {
	
	/**
	 * PropertyService 선언 및 세팅
	 */
	private PropertyService propertyService;
	public void setPropertyService(PropertyService propertyService) {
		this.propertyService = propertyService;
	}		
	
	private SiteMapService siteMapService;	
	public void setSiteMapService(SiteMapService siteMapService) {
		this.siteMapService = siteMapService;
	}
	
	/**
	 * 사이트 맵 화면 로드
	 * <p>
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView LoadPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession(false);
		String forwardURL 	= "/WEB-INF/jsp/secfw/menu/SiteMap.jsp";
		SiteMapForm form = new SiteMapForm();
		SiteMapVO   vo   = new SiteMapVO();
		
		try {
	        String sysCd   = propertyService.getProperty("secfw.sysCd");			        
            String MenuId  = propertyService.getProperty("secfw.menu.topMenuId");
            String userId  = (String)session.getAttribute("secfw.session.user_id");
            String comp_cd = (String)session.getAttribute("secfw.session.comp_cd");
            
			vo.setSys_cd(sysCd);
			vo.setMenu_id(MenuId);
			vo.setUser_id(userId);
			vo.setComp_cd(comp_cd);
			
	        HashMap Srh = siteMapService.SerchAuth(vo);
	        List  resultList = (List)Srh.get("resultList");	
	        
	       // 메뉴 정보  
			mav.addObject("resultList", resultList);
			
	       // 메뉴셋트를 동적으로 구성해준다. 
			for (int i=1; i<=resultList.size(); i++) {
				mav.addObject("Menu"+i, (List)Srh.get("Menu"+i));	        
			}
			
			mav.addObject("command", form);
			mav.addObject("returnMessage", "");
			mav.setViewName(forwardURL);
			
		} catch (Exception e) {
			
		}
		
		return mav;
	}
}
