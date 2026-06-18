package com.sds.secframework.user.control;



import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.outldap.samsung.net.Employee;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.auth.dto.AuthVO;
import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.singleIF.service.EsbOrgService;
import com.sds.secframework.user.dto.UserManagerForm;
import com.sds.secframework.user.dto.UserManagerVO;
import com.sds.secframework.user.service.UserManagerService;
import com.sds.secframework.user.service.UserService;
import com.sec.clm.sign.dto.SignManageForm;
import com.sec.clm.sign.dto.SignManageVO;

public class UserManagerController extends CommonController {
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
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	/**
	 *  userManagerService 선언 및 세팅
	 * 
	 */
	private UserManagerService userManagerService;
	public void setUserManagerService(UserManagerService userManagerService) {
		this.userManagerService = userManagerService;
	}		
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private EsbOrgService esbOrgService;
	public void setEsbOrgService(EsbOrgService esbOrgService) {
		this.esbOrgService = esbOrgService;
	}	
	
	/*공유할 결과 메시지 */
	String returnMessage;
	
	
	/**
	 * 사용자 정보 조회  김정곤 2011/03/21
	 * <P>
	 * 1. 리스트 정보 설정(현재페이지, 페이지당 ROW 수, 시작index, 종료index)
	 * 2. 리스트 조회 서비스 호출
	 * 3. 결과 List 를 form에 세팅
	 * 4. 페이징 처리
	 * </P>
	 * @param form UserManageForm
	 * @param vo   UserManageVO
	 * @return
	 * @throws Exception
	 */	
	@SuppressWarnings("rawtypes")
	public ModelAndView listUserMng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//----------- RETURN URL:리턴경로 --------------
		ModelAndView mav  = new ModelAndView();
		String forwardURL = "/WEB-INF/jsp/secfw/user/UserMngList.jsp";
		
		//----------- BIND:바인드설정 --------------------
		UserManagerForm form = new UserManagerForm();
		UserManagerVO vo     = new UserManagerVO();
		
		bind(request, form);
		bind(request, vo);
		
		//----------- SET VEL:필수값 설정 -----------------
        HttpSession session = request.getSession(false);
        String sysCd        = (String)session.getAttribute("secfw.session.sys_cd");
        String userId       = (String)session.getAttribute("secfw.session.user_id");
        String auth_comp_cd = (String)session.getAttribute("secfw.session.auth_comp_cd");
        String compCd       = (String)session.getAttribute("secfw.session.comp_cd");
        vo.setSys_cd(sysCd);
        vo.setLogin_id(userId);
        vo.setSession_auth_comp_cd(auth_comp_cd);
        vo.setSession_comp_cd(compCd);
        
		//----------- SERCH:조회 정보 ---------------
        HashMap Srh = userManagerService.listUserMng(vo);
        
        mav.addObject("RollList", (List)Srh.get("RollList"));
        mav.addObject("listUser", (List)Srh.get("listUser"));
        mav.addObject("orgnzList", (List)Srh.get("orgnzList"));
		mav.setViewName(forwardURL);
        
		form.setReturn_message(StringUtil.bvl( this.returnMessage,""));
		mav.addObject("command", form);
		this.returnMessage = null;
		
		return mav;
	}

	/**
	 * 사용자 정보 저장 2013/10/30
	 * <P>
	 * 1. 저장 서비스 호출
	 * 2. 페이징 처리
	 * </P>
	 * @param form UserManageForm
	 * @param vo   UserManageVO
	 * @return
	 * @throws Exception
	 */	
	public void SaveUserMng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			//-----------RETURN URL:리턴경로 -------------- 
			ModelAndView mav 		= new ModelAndView();
			String forwardURL 		= "/WEB-INF/jsp/secfw/user/UserMngList.jsp";
			//-----------BIND:바인드설정 -------------------- 		
			UserManagerForm form 	= new UserManagerForm();
			UserManagerVO   vo   	= new UserManagerVO();	
			 
			bind(request, form);
			bind(request, vo);
			//-----------SET VEL:필수값 설정 ----------------- 		
	        HttpSession session 	= request.getSession(false);
	        String sysCd			= (String)session.getAttribute("secfw.session.sys_cd");
	        String userId 			= (String)session.getAttribute("secfw.session.user_id");
	        String authCompCd		= (String)session.getAttribute("secfw.session.auth_comp_cd");
	        String compCd = (String)session.getAttribute("secfw.session.comp_cd");
	        vo.setSys_cd(sysCd);
	        vo.setLogin_id(userId);
	        vo.setSession_auth_comp_cd(authCompCd);
	        vo.setSession_comp_cd(compCd);	        
        
	        Locale locale = new RequestContext(request).getLocale();
	        
	        
			//-----------SAVE:정보 저장 ---------------        
	        String result_msg 		=  userManagerService.SaveUserMng(vo, locale);     
//	        this.returnMessage = result_msg;
	        
	        response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(result_msg);
			out.flush();
			out.close();
			
			
		}catch (Exception e) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		}catch (Throwable t) {
			JSONObject jo = new JSONObject();
			jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jo);
			out.flush();
			out.close();
		} 
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void UserDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		try {
			HttpSession session = request.getSession(false);
			
			/*********************************************************
			 * 시스템  코드
			**********************************************************/
			String sysCd        = (String)session.getAttribute("secfw.session.sys_cd");
			String auth_comp_cd = (String)session.getAttribute("secfw.session.auth_comp_cd");
			String comp_cd = (String)session.getAttribute("secfw.session.comp_cd");
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        UserManagerVO vo = new UserManagerVO();
			bind(request, vo);
			
			/*********************************************************
			 * 파라미터세팅
			**********************************************************/
			vo.setSys_cd(sysCd);
			vo.setSession_auth_comp_cd(auth_comp_cd);
			vo.setComp_cd(comp_cd);
			
    		/*********************************************************
			 * 목록 조회
			**********************************************************/
    		JSONArray jsonArray = userManagerService.SerchUserEsb(vo);
    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jsonArray);
    		out.flush();
    		out.close();
		} catch (Exception e) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		}
	}
	

	/**
	 * 사용자 정보 삭제 김정곤 2011/03/21
	 * <P>
	 * 1. 저장 서비스 호출
	 * 2. 페이징 처리
	 * </P>
	 * @param form UserManageForm
	 * @param vo   UserManageVO
	 * @return
	 * @throws Exception
	 */	
	public ModelAndView DeleteUserMng(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//-----------RETURN URL:리턴경로 -------------- 
		ModelAndView mav = new ModelAndView();
		String forwardURL 		= "/WEB-INF/jsp/secfw/user/UserMngList.jsp";
		//-----------BIND:바인드설정 -------------------- 		
		UserManagerForm form 	= new UserManagerForm();
		UserManagerVO   vo   	= new UserManagerVO();	
		 
		bind(request, form);
		bind(request, vo);
		//-----------SET VEL:필수값 설정 ----------------- 		
        HttpSession session 	= request.getSession(false);
        String sysCd			= (String)session.getAttribute("secfw.session.sys_cd");
        vo.setSys_cd(sysCd);
        
        
        Locale locale = new RequestContext(request).getLocale();
        
        String result_msg 		= userManagerService.DeleteUser(vo, locale);
        this.returnMessage = result_msg;
		return listUserMng(request,response);
	}	
	
	/**
	 * ESB 정보 팝업 김정곤 2011/04/07
	 * <P>
	 * 1. 저장 서비스 호출
	 * 2. 페이징 처리
	 * </P>
	 * @param form UserManageForm
	 * @param vo   UserManageVO
	 * @return
	 * @throws Exception
	*/
	public ModelAndView EsbUserPopUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//-----------RETURN URL:리턴경로 -------------- 
		ModelAndView mav = new ModelAndView();
		String forwardURL 		= "/WEB-INF/jsp/secfw/user/EsbUserPop.jsp";
		//-----------BIND:바인드설정 -------------------- 		
		UserManagerForm form 	= new UserManagerForm();
		UserManagerVO   vo   	= new UserManagerVO();	
		 
		bind(request, form);
		bind(request, vo);
		//-----------SET VEL:필수값 설정 ----------------- 		
        HttpSession session 	= request.getSession(false);
        String sysCd			= (String)session.getAttribute("secfw.session.sys_cd");
        String authCompCd		= (String)session.getAttribute("secfw.session.auth_comp_cd");
        vo.setSys_cd(sysCd);
        vo.setSession_auth_comp_cd(authCompCd);
		mav.setViewName(forwardURL);
      
		HashMap Srh =  userManagerService.SerchUserEsbInfo(vo);        
        mav.addObject("UserInfo", Srh);
		
		form.setReturn_message(StringUtil.bvl( this.returnMessage,""));
		mav.addObject("command", form);
		this.returnMessage = null;
		return mav;
	}
	
	/**
	 * 계약지정인 리스트 조회  김형준 2012/1/4
	 * <P>
	 * 1. 리스트 정보 설정(현재페이지, 페이지당 ROW 수, 시작index, 종료index)
	 * 2. 리스트 조회 서비스 호출
	 * 3. 결과 List 를 form에 세팅
	 * 4. 페이징 처리
	 * </P>
	 * @param form UserManageForm
	 * @param vo   UserManageVO
	 * @return
	 * @throws Exception
	 */	
	public ModelAndView listApntUserMng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//-----------RETURN URL:리턴경로 -------------- 
		ModelAndView mav 		= new ModelAndView();
		String forwardURL 		= "/WEB-INF/jsp/secfw/user/UserApntMngList.jsp";
		//-----------BIND:바인드설정 -------------------- 		
		UserManagerForm form 	= new UserManagerForm();
		UserManagerVO   vo   	= new UserManagerVO();	
		 
		bind(request, form);
		bind(request, vo);
		
		//-----------SET VEL:필수값 설정 ----------------- 		
        HttpSession session 	= request.getSession(false);
        String blngt_orgnz			= (String)session.getAttribute("secfw.session.blngt_orgnz"); //소속조직
        vo.setBlngt_orgnz(blngt_orgnz);
        String comp_cd			= (String)session.getAttribute("secfw.session.comp_cd"); //소속회사
        vo.setSession_comp_cd(comp_cd);
        
		//-----------SERCH:조회 정보 ---------------        
        HashMap Srh =  userManagerService.listApntUserMng(vo);        
        mav.addObject("listUser", (List)Srh.get("listUser"));
        
		//-----------RETURN INFO:리턴 정보 --------------- 		
		mav.setViewName(forwardURL);
        		
		form.setReturn_message(StringUtil.bvl( this.returnMessage,""));
		mav.addObject("command", form);
		this.returnMessage = null;
		return mav;
	}	
	
	/**
	 * 계약지정인 정보 저장 - 김형준 2012/01/10
	 * <P>
	 * 1. 저장 서비스 호출
	 * 2. 페이징 처리
	 * </P>
	 * @param form UserManageForm
	 * @param vo   UserManageVO
	 * @return
	 * @throws Exception
	 */	
	public ModelAndView SaveApntUserMng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//-----------RETURN URL:리턴경로 -------------- 
		ModelAndView mav 		= new ModelAndView();
		String forwardURL 		= "/WEB-INF/jsp/secfw/user/UserApntMngList.jsp";
		
		//-----------BIND:바인드설정 -------------------- 		
		UserManagerForm form 	= new UserManagerForm();
		UserManagerVO   vo   	= new UserManagerVO();	
		 
		bind(request, form);
		bind(request, vo);
		
		//-----------SET VEL:필수값 설정 ----------------- 		
		 HttpSession session 	= request.getSession(false);
        String comp_cd			= (String)session.getAttribute("secfw.session.comp_cd"); //소속회사
        vo.setSession_comp_cd(comp_cd);
		
		//-----------SAVE:정보 저장 ---------------        
        String result_msg 		=  userManagerService.SaveApntUserMng(vo);     
        this.returnMessage = result_msg;
		return  listApntUserMng(request,response);
	}
	
	/**
	 * 날인담당자 리스트 조회  
	 * <P>
	 * 1. 리스트 정보 설정(현재페이지, 페이지당 ROW 수, 시작index, 종료index)
	 * 2. 리스트 조회 서비스 호출
	 * 3. 결과 List 를 form에 세팅
	 * 4. 페이징 처리
	 * </P>
	 * @param form UserManageForm
	 * @param vo   UserManageVO
	 * @return
	 * @throws Exception
	 */	
	public ModelAndView listSignUserMng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//-----------RETURN URL:리턴경로 -------------- 
		ModelAndView mav 		= new ModelAndView();
		String forwardURL 		= "/WEB-INF/jsp/secfw/user/UserSignMngList.jsp";
		//-----------BIND:바인드설정 -------------------- 		
		UserManagerForm form 	= new UserManagerForm();
		UserManagerVO   vo   	= new UserManagerVO();	
		 
		bind(request, form);
		bind(request, vo);
		
		//-----------SET VEL:필수값 설정 ----------------- 		
        HttpSession session 	= request.getSession(false);
        String blngt_orgnz			= (String)session.getAttribute("secfw.session.blngt_orgnz"); //소속조직
        vo.setBlngt_orgnz(blngt_orgnz);
        String comp_cd			= (String)session.getAttribute("secfw.session.comp_cd"); //소속회사
        vo.setSession_comp_cd(comp_cd);	
		
		//-----------SERCH:조회 정보 ---------------        
        HashMap Srh =  userManagerService.listSignUserMng(vo);        
        mav.addObject("listUser", (List)Srh.get("listUser"));
        
		//-----------RETURN INFO:리턴 정보 --------------- 		
		mav.setViewName(forwardURL);
        		
		form.setReturn_message(StringUtil.bvl( this.returnMessage,""));
		mav.addObject("command", form);
		this.returnMessage = null;
		return mav;
	}	
	
	/**
	 * 증명서류 발급자 리스트 조회  
	 * <P>
	 * 1. 리스트 정보 설정(현재페이지, 페이지당 ROW 수, 시작index, 종료index)
	 * 2. 리스트 조회 서비스 호출
	 * 3. 결과 List 를 form에 세팅
	 * 4. 페이징 처리
	 * </P>
	 * @param form UserManageForm
	 * @param vo   UserManageVO
	 * @return
	 * @throws Exception
	 */	
	public ModelAndView listSignDocUserMng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//-----------RETURN URL:리턴경로 -------------- 
		ModelAndView mav 		= new ModelAndView();
		String forwardURL 		= "/WEB-INF/jsp/secfw/user/UserSignDocMngList.jsp";
		//-----------BIND:바인드설정 -------------------- 		
		UserManagerForm form 	= new UserManagerForm();
		UserManagerVO   vo   	= new UserManagerVO();	
		 
		bind(request, form);
		bind(request, vo);
		
		//-----------SET VEL:필수값 설정 ----------------- 		
        HttpSession session 	= request.getSession(false);
        String blngt_orgnz			= (String)session.getAttribute("secfw.session.blngt_orgnz"); //소속조직
        vo.setBlngt_orgnz(blngt_orgnz);
        String comp_cd			= (String)session.getAttribute("secfw.session.comp_cd"); //소속회사
        vo.setSession_comp_cd(comp_cd);	
		
		//-----------SERCH:조회 정보 ---------------        
        HashMap Srh =  userManagerService.listSignDocUserMng(vo);        
        mav.addObject("listUser", (List)Srh.get("listUser"));
        
		//-----------RETURN INFO:리턴 정보 --------------- 		
		mav.setViewName(forwardURL);
        		
		form.setReturn_message(StringUtil.bvl( this.returnMessage,""));
		mav.addObject("command", form);
		this.returnMessage = null;
		return mav;
	}
	
	/**
	 * 날인담당자 정보 // 증명서류발급자 정보 저장 
	 * <P>
	 * 1. 저장 서비스 호출
	 * 2. 페이징 처리
	 * </P>
	 * @param form UserManageForm
	 * @param vo   UserManageVO
	 * @return
	 * @throws Exception
	 */	
	public ModelAndView SaveSignUserMng(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//-----------RETURN URL:리턴경로 -------------- 
		ModelAndView mav 		= new ModelAndView();
		String forwardURL 		= "/WEB-INF/jsp/secfw/user/UserSignMngList.jsp";
		
		//-----------BIND:바인드설정 -------------------- 		
		UserManagerForm form 	= new UserManagerForm();
		UserManagerVO   vo   	= new UserManagerVO();	
		
		SignManageForm sform= new SignManageForm();
		SignManageVO svo = new SignManageVO();
		 
		bind(request, form);
		bind(request, vo);
		
		bind(request, sform);
		bind(request, svo);
		
		//-----------SET VEL:필수값 설정 ----------------- 		
		HttpSession session 	= request.getSession(false);
        String comp_cd			= (String)session.getAttribute("secfw.session.comp_cd"); //소속회사		
        String session_user_id			= (String)session.getAttribute("secfw.session.user_id"); 
        vo.setSession_comp_cd(comp_cd);	
        vo.setSession_user_id(session_user_id);
        
		//-----------SAVE:정보 저장 ---------------        
        int result_int 		=  userManagerService.SaveSignUserMng(vo,svo);   

        // 메세지:저장 되었습니다.
        String returnMessage = messageSource.getMessage("secfw.msg.succ.save",  null, new RequestContext(request).getLocale()); 
        
		if("SC0101".equals(svo.getGubn_cd())){
			mav = listSignUserMng(request,response);
			mav.addObject("command", form);
			mav.addObject("commands", sform);
			mav.addObject("returnMessage", returnMessage);
		} else {
			mav = listSignDocUserMng(request,response);
			mav.addObject("command", form);
			mav.addObject("commands", sform);
			mav.addObject("returnMessage", returnMessage);
		}
		return  mav;
		
	}
	
	/**
	 * 사용승인대상 리스트 조회  
	 * <P>
	 * 1. 리스트 정보 설정(현재페이지, 페이지당 ROW 수, 시작index, 종료index)
	 * 2. 리스트 조회 서비스 호출
	 * 3. 결과 List 를 form에 세팅
	 * 4. 페이징 처리
	 * </P>
	 * @param form UserManageForm
	 * @param vo   UserManageVO
	 * @return
	 * @throws Exception
	 */	
	public ModelAndView listUserAccess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//-----------RETURN URL:리턴경로 -------------- 
		ModelAndView mav 		= new ModelAndView();
		String forwardURL 		= "/WEB-INF/jsp/secfw/user/UserAccessList.jsp";
		//-----------BIND:바인드설정 -------------------- 		
		UserManagerForm form 	= new UserManagerForm();
		UserManagerVO   vo   	= new UserManagerVO();	
		 
		bind(request, form);
		bind(request, vo);
		
		//-----------SET VEL:필수값 설정 ----------------- 		
        HttpSession session 	= request.getSession(false);
        String blngt_orgnz			= (String)session.getAttribute("secfw.session.blngt_orgnz"); //소속조직
        vo.setBlngt_orgnz(blngt_orgnz);
//        String comp_cd			= (String)session.getAttribute("secfw.session.comp_cd"); //소속회사
//        vo.setSession_comp_cd(comp_cd);	
        
        String dept_cd = (String)session.getAttribute("secfw.session.dept_cd");
  
        // 반영시 주석 해제
//        vo.setSession_dept_cd(dept_cd);
        
        // 테스트용 dept_cd
        vo.setSession_dept_cd("C1PARTNERS");

        
		//-----------SERCH:조회 정보 ---------------        
        HashMap Srh =  userManagerService.listUserAccess(vo);        
        mav.addObject("listUser", (List)Srh.get("listUser"));
        
		//-----------RETURN INFO:리턴 정보 --------------- 		
		mav.setViewName(forwardURL);
        		
		form.setReturn_message(StringUtil.bvl( this.returnMessage,""));
		mav.addObject("command", form);
		this.returnMessage = null;
		return mav;
	}	
	
	/**
	 * 날인담당자 정보 // 증명서류발급자 정보 저장 
	 * <P>
	 * 1. 저장 서비스 호출
	 * 2. 페이징 처리
	 * </P>
	 * @param form UserManageForm
	 * @param vo   UserManageVO
	 * @return
	 * @throws Exception
	 */	
	public ModelAndView SaveUserAccess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//-----------RETURN URL:리턴경로 -------------- 
		ModelAndView mav 		= new ModelAndView();
		String forwardURL 		= "/WEB-INF/jsp/secfw/user/UserAccessList.jsp";
		
		//-----------BIND:바인드설정 -------------------- 		
		UserManagerForm form 	= new UserManagerForm();
		UserManagerVO   vo   	= new UserManagerVO();	
		
		 
		bind(request, form);
		bind(request, vo);
		
		
		//-----------SET VEL:필수값 설정 ----------------- 		
		HttpSession session 	= request.getSession(false);
        String comp_cd			= (String)session.getAttribute("secfw.session.comp_cd"); //소속회사		
        String session_user_id			= (String)session.getAttribute("secfw.session.user_id"); 
        vo.setSession_comp_cd(comp_cd);	
        vo.setSession_user_id(session_user_id);
        
		//-----------SAVE:정보 저장 ---------------        
        int result_int 		=  userManagerService.SaveUserAccess(vo);   

        // 메세지:저장 되었습니다.
        String returnMessage = messageSource.getMessage("secfw.msg.succ.save",  null, new RequestContext(request).getLocale()); 
        
        mav = listUserAccess(request,response);
        mav.setViewName(forwardURL);
		mav.addObject("returnMessage", returnMessage);
        
		
		return  mav;
		
	}
	
	public String[] SpliteArry(String Parm,String div){
		String[] Result;		
		String 	 str_arry =StringUtil.bvl(Parm,"");
		if( !Parm.equals("")){
			Result = str_arry.split(div);
		}else{
			Result =null;
		}
		return Result;
	}
}
