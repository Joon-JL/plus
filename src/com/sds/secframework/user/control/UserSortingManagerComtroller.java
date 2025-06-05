/**
* Project Name : 계약관리체계 강화 프로젝트
* File Name : UserSortingManagerComtroller
* Description : 변호사 Sorting 관리 Controller
* Author : 김형준
* Date : 2011. 11. 10
* Copyright : 
*/
package com.sds.secframework.user.control;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.singleIF.service.EsbOrgService;
import com.sds.secframework.user.dto.UserSortingManagerForm;
import com.sds.secframework.user.dto.UserSortingManagerVO;
import com.sds.secframework.user.service.UserManagerService;
import com.sds.secframework.user.service.UserSortingManagerService;

/**
 * Description	: Controller
 * Author		: 김형준
 * Date			: 2011. 11. 10
 */
public class UserSortingManagerComtroller extends CommonController {
	
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
	 * userSortingManagerService 선언
	 */
	private UserSortingManagerService userSortingManagerService;
	public void setUserSortingManagerService(UserSortingManagerService userSortingManagerService){
		this.userSortingManagerService = userSortingManagerService;
	}
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private EsbOrgService esbOrgService;
	public void setEsbOrgService(EsbOrgService esbOrgService) {
		this.esbOrgService = esbOrgService;
	}	
	
	
	/**
	 * 사용자 Sorting 정보를 조회
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listUserSortingMng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String forwardURL = "/WEB-INF/jsp/secfw/user/UserSortingMngList.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			UserSortingManagerForm form = new UserSortingManagerForm();
			UserSortingManagerVO vo = new UserSortingManagerVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, vo);
    		COMUtil.getUserAuditInfo(request, form);
    		
    		/*********************************************************
			 * 파라미터세팅
			**********************************************************/
    		if(form.getSrch_blngt_orgnz() == null){
    			form.setSrch_blngt_orgnz("A00000001"); //기본 국내 법무팀으로 셋팅
    			vo.setSrch_blngt_orgnz("A00000001"); //기본 국내법무팀으로 셋팅
    		}
    		
    		
    		List listSortingUser = null;
    		listSortingUser = userSortingManagerService.listSortingUser(vo);
    		
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);		
			mav.addObject("listSortingUser", listSortingUser);
			mav.addObject("command", form);
			
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}		
	}
	
	public ModelAndView saveUserSortingMng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String forwardURL = "/secfw/userSortingMng.do?method=listUserSortingMng";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			UserSortingManagerForm form = new UserSortingManagerForm();
			UserSortingManagerVO vo = new UserSortingManagerVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, vo);
    		COMUtil.getUserAuditInfo(request, form);
    		
    		/*********************************************************
			 * 파라미터세팅
			**********************************************************/
    		if(form.getSrch_blngt_orgnz() == null){
    			form.setSrch_blngt_orgnz("A00000001"); 	//기본 국내 법무팀으로 셋팅
    			vo.setSrch_blngt_orgnz("A00000001"); 	//기본 국내 법무팀으로 셋팅
    		}
    		ModelAndView mav = new ModelAndView();
    		
    		boolean retFlag = false;
	        String returnMessage = "";
    		retFlag = userSortingManagerService.saveSortingUser(vo);
    		
    		Locale locale = new RequestContext(request).getLocale();    		
    		
    		
    		if(retFlag) {
//				returnMessage = "저장을 성공하였습니다.";
    			messageSource.getMessage("secfw.msg.succ.save", null, locale);
    			
    			returnMessage = messageSource.getMessage("secfw.msg.succ.save", null, locale);
	        } else {
//				returnMessage = "저장을 실패하였습니다.";
	        	returnMessage = messageSource.getMessage("secfw.msg.error.error", null, locale);
	        }
	        mav.addObject("returnMessage", returnMessage);
    		
    		mav.setViewName(forwardURL);
    		mav.addObject("command", form);
    		
    		return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}				
	}	
}
