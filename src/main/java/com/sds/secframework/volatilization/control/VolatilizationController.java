package com.sds.secframework.volatilization.control;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.auth.dto.AuthVO;
import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.user.service.UserService;
import com.sds.secframework.volatilization.service.VolatilizationService;

public class VolatilizationController extends CommonController {

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */	
	private VolatilizationService volatilizationService;
	public void setVolatilizationService(VolatilizationService volatilizationService) {
		this.volatilizationService = volatilizationService;
	}

	/**
	 * 리스트
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void listTestDupConn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * 목록 조회
			**********************************************************/
			String result = volatilizationService.listTestDupConn();
					
		} catch (Exception e) {

		} 

	}

	/**
	 * 등록
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void insertTestDupConn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {

			/*********************************************************
			 * 등록
			**********************************************************/
			int result = volatilizationService.insertTestDupConn();
    		
		} catch (Exception e) {
			

		} 
		
	}
	
	/**
	 * 사용자 로그인
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		try {
		HttpSession session = request.getSession(true);

    	/**
    	 * Forwarding하는 페이지 정보를 가져온다. 
    	 */
		String userId = StringUtil.bvl(((String)request.getParameter("user_id")).trim(),"");
		String localeStr = StringUtil.bvl(((String)request.getParameter("locale")).trim(),propertyService.getProperty("secfw.defaultLocale"));

			/************************************************************************************************
			 * 세션정보 세팅
			 *   아래 항목은 필수적으로 세팅 해주시기 바랍니다
			 *   각종 사용현황 및 사용자정보 세팅에 사용됩니다
			 *    
			 *   - 사용자아이디 : secfw.session.user_id    (시스템 사용자아이디)
			 *   - 회사명       : secfw.session.comp_nm   (사용자 표시시 필요) 
			 *   - 부서명       : secfw.session.dept_nm   (로그인/메뉴사용현황등에 표시) 
			 *   - 직급명       : secfw.session.grade_nm  (로그인/메뉴사용현황등에 표시) 
			 *   - 사용자명     : secfw.session.user_nm    (사용자명)
			 *   - 싱글이메일   : secfw.session.email
			 *   - 사용자레벨   : secfw.session.user_level(사용자레벨 A:Admin) : 사용자 레벨값으로 확장사용 가능
			 *************************************************************************************************/
			
    		//DB 사용자정보 조회 : 시스템에서 별도로 관리하는 사용자의 정보를 세션정보에 저장
 
			HashMap hm = new HashMap();
			hm.put("user_id", userId);
			hm.put("sys_cd", propertyService.getProperty("secfw.sysCd"));
			
    		List userInfo = userService.getUserInfo(hm);  

    		if(userInfo != null && userInfo.size() > 0) {
	    	
				ListOrderedMap lom = (ListOrderedMap)userInfo.get(0);
				
				/********************************************************************************
				 * 시스템별 세션정보 세팅(시작)
				 *******************************************************************************/
				session.setAttribute("secfw.session.user_id", userId);
				// 2012.05.29 담당사업부코드 세션 추가 added by hanjihoon
				session.setAttribute("secfw.session_clms_user_orgnz", StringUtil.bvl((String)lom.get("clms_user_orgnz"),""));
				session.setAttribute("secfw.session.resp_operdiv",   StringUtil.bvl((String)lom.get("resp_operdiv"),""));
				session.setAttribute("secfw.session.comp_nm", StringUtil.bvl((String)lom.get("comp_nm"),""));
				session.setAttribute("secfw.session.dept_nm", StringUtil.bvl((String)lom.get("dept_nm"),""));
				session.setAttribute("secfw.session.grade_nm", StringUtil.bvl((String)lom.get("grade_nm"),""));
				session.setAttribute("secfw.session.user_nm", StringUtil.bvl((String)lom.get("user_nm"),""));
				session.setAttribute("secfw.session.email", StringUtil.bvl((String)lom.get("email"),""));
				
				/********************************************************************************
				 * 시스템별 세션정보 세팅(종료)
				 *******************************************************************************/

				/***********************************************************************************
				 * 사용자 로케일정보 세팅
				 ***********************************************************************************/
				Locale locale = new Locale(localeStr);
				//localeResolver에 locale 셋팅
				localeResolver.setLocale(request, response, locale);
				
				session.setAttribute("secfw.session.language_flag", localeStr);
				
				/***********************************************************************************
				 * 기본권한 세팅 (사용자 테이블에 있으나, 기본권한이 없을경우 기본권한에 사용자 정보 입력)
				 ***********************************************************************************/
				try {
					AuthVO authVo = new AuthVO();
					
					authVo.setSys_cd(propertyService.getProperty("secfw.sysCd"));
					authVo.setRole_cd(propertyService.getProperty("secfw.auth.basicRole"));
					authVo.setUser_id((String)session.getAttribute("secfw.session.user_id"));
					
					List existsRoleUser = userService.existsRoleUser(authVo);
					
					if(existsRoleUser!=null && existsRoleUser.size()>0) {
						ListOrderedMap existsRoleMap = (ListOrderedMap)existsRoleUser.get(0);
						
						String existsRoleUserYn = (String)existsRoleMap.get("exists_yn");
						
						if("N".equals(existsRoleUserYn)) {
							userService.insertRoleUser(authVo);				
						}
					}
				}catch(Exception e) {
					e.printStackTrace();
				}

				/***********************************************************************************
				 * Property 에 설정된 관리자와 동일인이면 세션 사용자레벨에 관리자 권한으로 세팅
				 ***********************************************************************************/
				String adminEPID = propertyService.getProperty("secfw.admin.epid");
				StringTokenizer adminStrToken = new StringTokenizer(adminEPID, ",");
				
				while(adminStrToken.hasMoreTokens()) {
					
					String adminTokenStr = adminStrToken.nextToken();
					
					if(userId.equals(adminTokenStr)) { 
						session.setAttribute("secfw.session.user_level", "A");
						break;
					}
				}
				
				if(userId.equals(adminEPID)) {
					session.setAttribute("secfw.session.user_level", "A");
				}
				
				mav.setViewName(propertyService.getProperty("secfw.url.introPage"));
				
			}
    		else {// 사용자 정보가 없을 경우
				mav = new ModelAndView();
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));

				// 메시지처리 - 사용자 정보가 없습니다. 
		    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noUserInfo", null, new RequestContext(request).getLocale());
		    	// 메시지처리 - 시스템 관리자에게 문의하십시오.
		    	String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
				
				mav.addObject("secfw.alert.title", alertTitle);
				mav.addObject("secfw.alert.message", alertMessage);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mav;	
	}
}
