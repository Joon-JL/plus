package com.sds.secframework.singleIF.control;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.singleIF.dto.EmployeeVO;
import com.sds.secframework.singleIF.service.EsbOrgService;

import flex.messaging.io.ArrayList;

public class EsbOrgController extends CommonController {

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private EsbOrgService esbOrgService;	
	public void setEsbOrgService(EsbOrgService esbOrgService) {
		this.esbOrgService = esbOrgService;
	}

	/**
	 * 임직원조회(no-Paging)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listEsbEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/
			HttpSession session = request.getSession(false);
			String sysCd  = (String)session.getAttribute("secfw.session.sys_cd");
			String authCompCd = (String)session.getAttribute("secfw.session.auth_comp_cd");
			String compCd = (String)session.getAttribute("secfw.session.comp_cd");
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			EmployeeVO form = new EmployeeVO();
			bind(request, form);
			
			/*********************************************************
			 * Forwarding URL
			**********************************************************/
			// 멀티로사용자를  체크할 수 있는 페이지로 갈지 결정
			String forwardURL = "";
			String multiYn = StringUtil.bvl((String)request.getParameter("multiChk"), "N");
			
			String input_multiYn = StringUtil.bvl(form.getSrch_user_cntnt(),"");
			int int_multiYn = 0;
			
			boolean boo_multiYn = false;
			
			String setInputWord[] = null;
			
			String srchCntntType = "";
			
			Locale lc = (Locale)localeResolver.resolveLocale(request);
			
			if(!"".equals(input_multiYn)){
				int_multiYn = input_multiYn.indexOf(";");
				
				if(int_multiYn > 0){
					boo_multiYn = true;
				}
			}
			
			// 넘어오는 인자에서 ';' 값이 있으면 멀티로 분기를 시켜 버린다.
			// 넘어오는 값엣 ';' 값이 없는 경우 즉 int_multYn이 0보다 큰 경우는 기존의 로직을 따르게 된다.
			if(int_multiYn > 0){
				// 목록페이지로 Forwading
				
				forwardURL = "/WEB-INF/jsp/secfw/search/EsbEmployeeListMultiCheckPopup.jsp";	
		        
			} else {
				
				forwardURL = "/WEB-INF/jsp/secfw/search/EsbEmployeeListPopup.jsp";
				
				
			}
			
			if(form.getSrch_user_cntnt() == null) {
				form.setSrch_user_cntnt("");
			} else {
				form.setSrch_user_cntnt(StringUtil.convertHtmlTochars(form.getSrch_user_cntnt()));
			}
			
			Vector   result = new Vector();
			
			ArrayList   sumResult = new ArrayList();
			
			String returnMessage = "";			
	        /*********************************************************
			 * 검색처리
			**********************************************************/
			if ("N".equals(form.getDoSearch())) {

			} else {
			    /*********************************************************
				 * 파라미터세팅
				**********************************************************/
	    		srchCntntType = form.getSrch_user_cntnt_type();
	    		String srchCntnt     = form.getSrch_user_cntnt();
	    		
	    		// 문자 열에 '.', '@' 있는 경우
				// '.'이 있는 경우 아이디 검색으로 이동
				// '@'이 있는 경우 이메일 검색으로 이동
				// 아무 것도 없는 경우 이름 검색으로 이동
				
				if(boo_multiYn){
					
					setInputWord = input_multiYn.split(";");
					
					for(String splitWord : setInputWord){
						
						if(splitWord.indexOf("@") > 0){
							
							result = esbOrgService.userSearchByMail(splitWord, lc);
							
							form.setCheck_yn("e_u");
			    			
			    			if(result != null){
			    				sumResult.add(result);
				    		}
							
						} else if(splitWord.indexOf(".") > 0) {
							
							result = esbOrgService.userSearchByUid(splitWord, lc);
							
							form.setCheck_yn("e_u");
					    	
					    	if(result != null){
					    		sumResult.add(result);
				    		}
							
						} else {
							
							result = esbOrgService.userSearchByName(splitWord, lc);
							
				    		if(result != null){
				    			sumResult.add(result);
				    		}
							
							if ("".equals(StringUtil.bvl(srchCntnt, ""))) {
				    			srchCntnt = form.getAdd_mail_user();
				    		}
						}
					}
					
				} else {
					form.setSrch_user_cntnt("");					
						
					srchCntntType = "userNm";
					
						if(input_multiYn.indexOf("@") > 0){
							
							srchCntntType = "email";
							
						}else if(input_multiYn.indexOf(".") > 0) {
							
							srchCntntType = "userId";
							
						} else {
							
							if ("".equals(StringUtil.bvl(srchCntnt, ""))) {
				    			srchCntnt = form.getAdd_mail_user();
				    		}
						}
				}
	    		
      		    /*********************************************************
				 * 임직원조회
				**********************************************************/
	    		
	    		
	    		//마이싱글아이디
	    		if("userId".equals(srchCntntType)) {
	        		// !@# ESB인터페이스 변경 (auth_comp_cd추가) 2013.04.25
		    		//result = esbOrgService.userSearchByCompUid(compCd, srchCntnt, lc);
		    		result = esbOrgService.userSearchByUid(srchCntnt, lc);
		    		
		    		if(result != null){
		    			sumResult.add(result);
		    		}
		    		
	    		//EPID
	    		} else if("epid".equals(srchCntntType)) {
	    			// !@# ESB인터페이스 변경 (auth_comp_cd추가) 2013.04.25
		    		result = esbOrgService.userSearchByEpid(srchCntnt, lc);
		    		
		    		if(result != null){
		    			sumResult.add(result);
		    		}
		    		
	    		//email
	    		} else if("email".equals(srchCntntType)) {
	    			
	    			result = esbOrgService.userSearchByMultMail(setInputWord, lc);
	    			
	    			if(result != null){
	    				sumResult.add(result);
		    		}
	    		
	    		// 이름
			    } else if("miltUserId".equals(srchCntntType)){
			    	
			    	result = esbOrgService.userSearchByMultId(setInputWord, lc);
			    	
			    	if(result != null){
			    		sumResult.add(result);
		    		}
	    		
			    } else if("userNm".equals(srchCntntType)) {
	        		// !@# ESB인터페이스 변경 (auth_comp_cd추가) 2013.04.25
		    		result = esbOrgService.userSearchByName(srchCntnt, lc);
		    		
		    		//Sungwoo added 2014-07-15		    		
		    		if(result.size() == 0){
		    			result = esbOrgService.userSearchByUid(srchCntnt, lc);
		    		}
		    		
		    		if(result != null){
		    			sumResult.add(result);
		    		}
	    		}
	
	    		if(result != null && result.size() > 0) {
	    			
	                // 메시지처리 - 정상적으로 조회되었습니다.
	                if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
	                	returnMessage = (String)request.getAttribute("returnMessage");
	                }
	           
	            	 
	               
	            } else {
	            	// 메시지처리 - 조회된 결과가 없습니다.
	                if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
	                	returnMessage = (String)request.getAttribute("returnMessage");
	                } else {       
		            	returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale());	                
		            }
	            }
           
			}
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			
			if(boo_multiYn){
				mav.addObject("resultList", sumResult);
			} else {
				mav.addObject("resultList", result);
			}

			
			mav.addObject("returnMessage", returnMessage);
			mav.addObject("command", form);
				
			return mav;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("error!!!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("error!!!");
		}
    }
	/**
	 * 전자 임직원조회(no-Paging)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listEsbElecEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			/*********************************************************
			 * 시스템  코드 및 사용자아이디
			**********************************************************/
			HttpSession session = request.getSession(false);
			String sysCd  = (String)session.getAttribute("secfw.session.sys_cd");
			String compCd = (String)session.getAttribute("secfw.session.comp_cd");
			String authCompCd = "'C10'";
			/*********************************************************
			 * Forwarding URL
			**********************************************************/
	        // 목록페이지로 Forwading
	        String forwardURL = "/WEB-INF/jsp/secfw/search/EsbEmployeeListPopup.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			EmployeeVO form = new EmployeeVO();
			bind(request, form);
			
	
			if(form.getSrch_user_cntnt() == null) {
				form.setSrch_user_cntnt("");
			} else {
				form.setSrch_user_cntnt(StringUtil.convertHtmlTochars(form.getSrch_user_cntnt()));
			}
			
			Vector   result = null;
			String returnMessage = "";			
	        /*********************************************************
			 * 검색처리
			**********************************************************/
			if ("N".equals(form.getDoSearch())) {
			
			} else {
			        /*********************************************************
				 * 파라미터세팅
				**********************************************************/
	    		String srchCntntType = form.getSrch_user_cntnt_type();
	    		String srchCntnt     = form.getSrch_user_cntnt();
	    		
	    		if ("".equals(StringUtil.bvl(srchCntnt, ""))) {
	    			srchCntnt = form.getAdd_mail_user();
	    		}
	
	    		 /*********************************************************
				 * 임직원조회
				**********************************************************/
	    		Locale lc = (Locale)localeResolver.resolveLocale(request);
	    		
	    		//마이싱글아이디
	    		if("userId".equals(srchCntntType)) {
	        		// !@# ESB인터페이스 변경 (auth_comp_cd추가) 2013.04.25
		    		result = esbOrgService.userSearchByCompUid(compCd, srchCntnt, lc);
//		    		result = esbOrgService.userSearchByUid(srchCntnt, lc);
	    		//EPID
	    		} else if("epid".equals(srchCntntType)) {
	    			// !@# ESB인터페이스 변경 (auth_comp_cd추가) 2013.04.25
		    		result = esbOrgService.userSearchByEpid(srchCntnt, lc);		
	    		//이름(userName)
	    		} else {
	        		// !@# ESB인터페이스 변경 (auth_comp_cd추가) 2013.04.25
		    		result = esbOrgService.userSearchByName(srchCntnt, lc);
//	    			result = esbOrgService.userSearchByName(srchCntnt, lc);
	    		}
	
	    		if(result != null && result.size() > 0) {
	    			
	                // 메시지처리 - 정상적으로 조회되었습니다.
	                if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
	                	returnMessage = (String)request.getAttribute("returnMessage");
	                }
	           
	            	 
	               
	            } else {
	            	// 메시지처리 - 조회된 결과가 없습니다.
	                if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
	                	returnMessage = (String)request.getAttribute("returnMessage");
	                } else {       
		            	returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale());	                
		            }
	            }
           
			}

			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
		
			mav.addObject("elecYn", "Y");
			mav.addObject("resultList", result);
			mav.addObject("returnMessage", returnMessage);
			mav.addObject("command", form);
				
			return mav;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("error!!!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("error!!!");
		}
    }
}
