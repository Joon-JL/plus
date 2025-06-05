package com.sds.secframework.code.control;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.code.dto.ActscodeForm;
import com.sds.secframework.code.dto.ActscodeVO;
import com.sds.secframework.code.service.ActscodeService;
import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.util.service.ComUtilService;

public class ActscodeController extends CommonController {

	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */	
	private ActscodeService actscodeService;
	public void setActscodeService(ActscodeService actscodeService) {
		this.actscodeService = actscodeService;
	}
	
	/**
	 * ComUtilService 선언 및 세팅
	 */
	private ComUtilService comUtilService;
	public void setComUtilService(ComUtilService comUtilService) {
		this.comUtilService = comUtilService;
	}
	
	/**
	 * 코드 관리 메인
	 * @param  
	 * @return Log
	 * @throws 
	*/	
	public ModelAndView mngActscode(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
	        
	        /*********************************************************
			 * Forwarding URL
			**********************************************************/
			String forwardURL = "/WEB-INF/jsp/secfw/code/ActscodeManage.jsp";
			
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ActscodeForm form = new ActscodeForm();
			ActscodeVO   vo   = new ActscodeVO();
	        bind(request, form);
			bind(request, vo);
	        COMUtil.getUserAuditInfo(request, vo);
    		COMUtil.getUserAuditInfo(request, form);
    		
	        /*********************************************************
			 * 파라미터세팅
			**********************************************************/
    		if(vo.getSession_user_locale() == null || vo.getSession_user_locale().equals("")) {
    			vo.setSession_user_locale("en");
    		}
            // 메시지처리
    		String returnMessage = "";
            if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
            	returnMessage = (String)request.getAttribute("returnMessage");
            }
            if(returnMessage.equals("")) {
	    		if(vo.getGrp_cd() == null || vo.getGrp_cd().equals("")) {
	    			vo.setGrp_cd("TOP");
	    			form.setGrp_cd("TOP");
	    		}
            } else {
    			vo.setGrp_cd("TOP");
    			form.setGrp_cd("TOP");
    			vo.setSrch_sys_cd("");
    			form.setSrch_sys_cd("");
            }

    		/*********************************************************
			 * 정보 조회
			**********************************************************/
			ModelAndView mav = new ModelAndView();
            List listCode = null;
            listCode = actscodeService.listCode(vo);
    		
			mav.setViewName(forwardURL);			
			mav.addObject("listCode", listCode);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
			return mav;
        
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 공통코드 목록 (Ajax처리)
	 * @param  
	 * @return Log
	 * @throws 
	*/	
	public void listCodeAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        ActscodeVO   vo   = new ActscodeVO();
	        COMUtil.getUserAuditInfo(request, vo);
			bind(request, vo);

	    	/*********************************************************
			 * 파라미터세팅
			**********************************************************/
    		if(vo.getSession_user_locale() == null || vo.getSession_user_locale().equals("")) {
    			vo.setSession_user_locale("en");
    		}
    		if(vo.getGrp_cd() == null || vo.getGrp_cd().equals("")) {
    			vo.setGrp_cd("TOP");
    		}
    		
    		/*********************************************************
			 * 목록 조회
			**********************************************************/
	        JSONArray jsonArray = null;
	        jsonArray = actscodeService.listCodeAjax(vo);
	        
	        response.setContentType("application/json; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.print(jsonArray);
	        out.flush();
	        out.close();
        
		} catch (Exception e) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		jo.put("returnCnt", 0);

    		response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} catch (Throwable t) {
			response.setContentType("application/json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
			out.flush();
			out.close();
		}
	}
	
	/**
	 * 공통코드 저장 처리
	 */
	public ModelAndView saveCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * 사용자아이디
			**********************************************************/
	        HttpSession session = request.getSession(false);
	        String userId = (String)session.getAttribute("secfw.session.user_id");

			/*********************************************************
			 * Forwarding URL
			**********************************************************/
	        String forwardURL = "/acts/cmm/actscode.do?method=mngActscode";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        ActscodeForm form = new ActscodeForm();
	        ActscodeVO   vo   = new ActscodeVO();
			bind(request, form);
			bind(request, vo);
	        COMUtil.getUserAuditInfo(request, vo);
	        COMUtil.getUserAuditInfo(request, form);

		    /*********************************************************
			 * 파라미터세팅
			**********************************************************/
			if(vo.getSession_user_id() == null || vo.getSession_user_id().equals("")) {
				vo.setSession_user_id(userId);
			}
			if(vo.getSession_user_locale() == null || vo.getSession_user_locale().equals("")) {
				vo.setSession_user_locale("en");
			}

	    	ModelAndView mav = new ModelAndView(); //상세페이지 이동

			boolean retFlag = false;
	        String returnMessage = "";
	        retFlag = actscodeService.saveCode(vo);

		    if(retFlag) {
		    	returnMessage = "저장을 성공하였습니다.";
		    } else {
		    	returnMessage = "저장을 실패하였습니다.";
		    }
		    mav.addObject("returnMessage", returnMessage);
	        
		    /*********************************************************
			 * 상세조회 컨트롤러 호출
			**********************************************************/
			mav.setViewName(forwardURL);			
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
	
}