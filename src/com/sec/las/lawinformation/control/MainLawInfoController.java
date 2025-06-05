/**
 * Project Name : 법무시스템 이식
 * File name	: MainLawInfoController.java
 * Description	: 주요입법동향/법무사례  Controller
 * Author		: 
 * Date			: 2011. 08
 * Copyright	: 2011 by SAMSUNG. All rights reserved.
 */
package com.sec.las.lawinformation.control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.las.lawinformation.dto.MainLawInfoForm;
import com.sec.las.lawinformation.dto.MainLawInfoVO;
import com.sec.las.lawinformation.service.MainLawInfoService;

public class MainLawInfoController extends CommonController {
	/**   
	 * 입법동향서비스     
	 * 
	 */
	private MainLawInfoService mainLawInfoService;
	
	/**
	 * 주요입법동향 서비스 세팅
	 * 
	 * @param libraryService
	 * @return void
	 * @throws
	 */
	public void setMainLawInfoService(MainLawInfoService mainLawInfoService) {
		this.mainLawInfoService = mainLawInfoService;
	}

	/**
	 * 페이징 처리가 되는 리스트 조회
	 * @param request 
	 * @param response 
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listMainLawInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL = "";
			String returnMessage = "";
			
			PageUtil 	pageUtil   = null;
			List   		resultList = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/las/lawinformation/MainLawInfo_l.jsp";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			MainLawInfoForm form = new MainLawInfoForm();
			MainLawInfoVO   vo   = new MainLawInfoVO();
			
			bind(request, form);
			bind(request, vo);
	
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			if (form.getSrch_dmstfrgn_gbn() != null && !"".equals(form.getSrch_dmstfrgn_gbn()) && !"F".equals(form.getSrch_dmstfrgn_gbn())){
				form.setSrch_nation("");
				vo.setSrch_nation("");
			}
			//  초기 화면검색요청 시 검색 파라미터 초기화 : NULL 값 방지
			form.setInfo_gbn(StringUtil.bvl(form.getInfo_gbn(),"error"));
			if (form.getSrch_nation() == null){
				form.setSrch_nation("");
				vo.setSrch_nation("");
			}
			if (form.getSrch_mainlawexam() == null){
				form.setSrch_mainlawexam("");
				vo.setSrch_mainlawexam("");
			}
			if (form.getSrch_dmstfrgn_gbn() == null){
				form.setSrch_dmstfrgn_gbn("");
				vo.setSrch_dmstfrgn_gbn("");
			}
	
			//Cross-site Scripting 방지
			form.setSrch_start_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_start_dt(),"")));
			form.setSrch_end_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_end_dt(),"")));
			form.setSrch_name(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_name(),"")));
			form.setTrgt_nation(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getTrgt_nation(),"")));
					
		
		    vo.setInfo_gbn(StringUtil.bvl(vo.getInfo_gbn(),"error"));
		
		    if("error".equals(vo.getInfo_gbn()))
		    	throw new Exception("##### info_gbn is null #####");
			
			vo.setSrch_start_dt(StringUtil.bvl(vo.getSrch_start_dt(),""));
			vo.setSrch_end_dt(StringUtil.bvl(vo.getSrch_end_dt(),""));
			vo.setSrch_name(StringUtil.bvl(vo.getSrch_name(),""));
			vo.setTrgt_nation(StringUtil.bvl(vo.getTrgt_nation(),""));
			
			/*********************************************************
			 * Page  setting
			**********************************************************/
			pageUtil = new PageUtil();
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));
			
			vo.setStart_index(pageUtil.getStartIndex());	//현재 페이지의 첫번째 게시물번호  set
			vo.setEnd_index(pageUtil.getEndIndex());		//현재 페이지의 마지막 게시물번호  set
		
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = mainLawInfoService.listMainLawInfo(vo);
			
			if (resultList != null && resultList.size() > 0) { 
				ListOrderedMap tmp = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(((BigDecimal)tmp.get("total_cnt")).intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();

				// 메시지처리 - 정상적으로 조회되었습니다.
				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}else {
					//메뉴 최초 진입 시 메세지 여부
					if("Y".equals(vo.getDoSearch()))
						returnMessage = messageSource.getMessage("secfw.msg.succ.search", null, new RequestContext(request).getLocale());
				}
			}else {
				// 메시지처리 - 조회된 결과가 없습니다.
                if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
                	returnMessage = (String)request.getAttribute("returnMessage");
                } else {
                	//메뉴 최초 진입 시 메세지 여부
                	if("Y".equals(vo.getDoSearch()))
                		returnMessage = messageSource.getMessage("secfw.msg.succ.noResult", null, new RequestContext(request).getLocale());
                }
			}
			
    		ArrayList lom = (ArrayList)resultList;

			/*********************************************************
             * 권한 처리
            **********************************************************/ 
			String accessLevel = mainLawInfoService.checkAuthByRole(vo);
			if ("A".equals(accessLevel)){
				form.setAuth_insert(true);
			}
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
    		ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("lom", lom);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;  
			
		}catch (Exception e) {  
			e.printStackTrace();
			throw new Exception("MainLawInfoController.listMainLawInfo() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("MainLawInfoController.listMainLawInfo() Throwable !!");
		}
	}

	/**
	 * 주요/입법동향   등록페이지 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardInsertMainLawInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			
			MainLawInfoForm form = null;
			MainLawInfoVO vo = null; 

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/las/lawinformation/MainLawInfo_i.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/			
			form = new MainLawInfoForm();
			vo = new MainLawInfoVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("command", form);

			return mav; 
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("LibraryController.forwardInsertMainLawInfo() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("LibraryController.forwardInsertMainLawInfo() Throwable !!");
		}
	}

	
	
	
			
	/**
	 * 주요법무사례 등록
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception  
	 */

	public ModelAndView insertMainLawInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			MainLawInfoForm form = null;
			MainLawInfoVO vo = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL =  "/las/lawinformation/mainLawInfo.do?method=detailMainLawInfo";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new MainLawInfoForm();
			vo = new MainLawInfoVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//Cross-site Scripting 방지
			vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
//			vo.setCont(StringUtil.convertHtmlTochars(vo.getCont()));

			//cont에 들어있는 내용은 service단에서 나모파일 저장 & convert 후 저장
			if (form.getDmstfrgn_gbn()!=null && !"F".equals(form.getDmstfrgn_gbn())){
				form.setTrgt_nation("");
				vo.setTrgt_nation("");
			}
			
			vo.setReg_id(vo.getSession_user_id());
			vo.setReg_nm(vo.getSession_user_nm());
			vo.setReg_dept(vo.getSession_dept_cd());
			vo.setReg_dept_nm(vo.getSession_dept_nm());
			
			/*********************************************************
			 * Service
			**********************************************************/
			
			String accessLevel = mainLawInfoService.checkAuthByRole(vo);
		    int seqno =0;
		    
		    if ("A".equals(accessLevel)){
		    	seqno = mainLawInfoService.insertMainLawInfo(vo);
		    }else{
		    	throw new Exception(messageSource.getMessage("secfw.page.field.alert.noAuth", null, new RequestContext(request).getLocale()));
		    }
			  
			form.setSeqno(seqno);
			vo.setSeqno(seqno);
			
			/*********************************************************
			 * Massage
			**********************************************************/
			// 메시지처리 - 등록되었습니다.
			returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("MainLawInfoController.insertMainLawInfo() Exception !! ");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("MainLawInfoController.insertMainLawInfo() Throwable !!");
		}
	}

	
	/**
	 * 주요/입법동향   상세정보
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	
	public ModelAndView detailMainLawInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			MainLawInfoForm cmd = (MainLawInfoForm)request.getAttribute("command");
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			MainLawInfoForm form = null;
			MainLawInfoVO vo = null;
			List   		resultList = null;
		
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/las/lawinformation/MainLawInfo_d.jsp";
				
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			
			form = new MainLawInfoForm();
			vo = new MainLawInfoVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			if(cmd != null){
				form = cmd;				
				vo.setInfo_gbn(form.getInfo_gbn());
				vo.setSeqno(form.getSeqno());				
			}
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = mainLawInfoService.detailMainLawInfo(vo);
			
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

			if(resultList==null)
				throw new Exception("##### queryService is null ##### ");
			
			/*********************************************************
             * 권한 처리
            **********************************************************/         
            boolean modifyAuth = mainLawInfoService.authMainLawInfo(MODIFY, vo);
            boolean deleteAuth = mainLawInfoService.authMainLawInfo(DELETE, vo);
			String accessLevel = mainLawInfoService.checkAuthByRole(vo);
			
			if ("A".equals(accessLevel)){
				form.setAuth_modify(modifyAuth);
				form.setAuth_delete(deleteAuth);
			}
			
			// 본인 글이 아닐 경우 조회수 증가			
			if(!vo.getSession_user_id().equals(lom.get("reg_id"))) {
				mainLawInfoService.increseRdcnt(vo) ;  	  			
			}    
			/*********************************************************
			 * Massage
			**********************************************************/			 
			returnMessage = StringUtil.bvl((String)request.getAttribute("returnMessage"), "");

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			
			mav.addObject("resultList", resultList);
			mav.addObject("lom", lom);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
			return mav;
			 
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("MainLawInfoController.detailMainLawInfo() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("MainLawInfoController.detailMainLawInfo() Throwable !!");
		}
	}


	/**
	 * 주요입법동향  수정 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */

	public ModelAndView forwardModifyMainLawInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
              
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			
			MainLawInfoForm form = new MainLawInfoForm();
			MainLawInfoVO vo = new MainLawInfoVO();

			bind(request, form);
			bind(request, vo);
			List   		resultList = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/las/lawinformation/MainLawInfo_u.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
		    form = new MainLawInfoForm();
			vo   = new MainLawInfoVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			
			resultList = mainLawInfoService.detailMainLawInfo(vo);
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			if(lom != null){
				boolean auth = mainLawInfoService.authMainLawInfo(MODIFY, vo);
				String accessLevel = mainLawInfoService.checkAuthByRole(vo);
				
				if(auth && "A".equals(accessLevel)){
					mav.setViewName(forwardURL);
					mav.addObject("resultList", resultList);
					mav.addObject("lom", lom); 
					mav.addObject("command", form);
				}else{
    				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
    				// 메시지처리 - 수정권한이 없습니다. 
    		    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noModifyAuth", null, new RequestContext(request).getLocale());
    		    	// 메시지처리 - 시스템 관리자에게 문의하십시오.
    		    	String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
    				mav.addObject("secfw.alert.title", alertTitle);
    				mav.addObject("secfw.alert.message", alertMessage);  					
				}
			}else{
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));

				// 메시지처리 - 사용자 정보가 없습니다. 
		    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noDataInfo", null, new RequestContext(request).getLocale());
		    	// 메시지처리 - 시스템 관리자에게 문의하십시오.
		    	String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
				
				mav.addObject("secfw.alert.title", alertTitle);
				mav.addObject("secfw.alert.message", alertMessage);
			}

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("MainLawInfoController.forwardmodifyMainLawInfo() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("MainLawInfoController.forwardmodifyMainLawInfo() Throwable !!");
		}
	}
	
	
	/**
	 * 주요 입법동향수정 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifyMainLawInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			MainLawInfoForm form = new MainLawInfoForm();
			MainLawInfoVO vo = new MainLawInfoVO();

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL =  "/las/lawinformation/mainLawInfo.do?method=detailMainLawInfo";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			form = new MainLawInfoForm();
			vo   = new MainLawInfoVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			boolean auth = mainLawInfoService.authMainLawInfo(MODIFY, vo);
			String accessLevel = mainLawInfoService.checkAuthByRole(vo);
			
			if(auth && "A".equals(accessLevel)){

				vo.setMod_id(vo.getSession_user_id());
				vo.setMod_nm(vo.getSession_user_nm());
				
				form.setMod_id(vo.getSession_user_id());
				form.setMod_nm(vo.getSession_user_nm());
				
				// Cross-site scripting방지
				vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
				vo.setLaw_gbn(StringUtil.convertHtmlTochars(vo.getLaw_gbn()));
				vo.setTrgt_nation(StringUtil.convertHtmlTochars(vo.getTrgt_nation()));

				if (form.getDmstfrgn_gbn()!=null && !"F".equals(form.getDmstfrgn_gbn())){
					form.setTrgt_nation("");
					vo.setTrgt_nation("");
				}
				
				int resultList = mainLawInfoService.modifyMainLawInfo(vo);
			
				// 메시지처리 - 수정되었습니다.
				returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());
			
				mav.setViewName(forwardURL);
				mav.addObject("command", form);
				mav.addObject("returnMessage", returnMessage);
				
			}else{
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				// 메시지처리 - 수정권한이 없습니다. 
		    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noModifyAuth", null, new RequestContext(request).getLocale());
		    	// 메시지처리 - 시스템 관리자에게 문의하십시오.
		    	String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
				mav.addObject("secfw.alert.title", alertTitle);
				mav.addObject("secfw.alert.message", alertMessage);  					
			}
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("LibraryController.modifyMainLawInfo() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("LibraryController.modifyMainLawInfo() Throwable !!");
		}
	}

		/**
		 * 주요 입법동향   삭제
		 * @param request
		 * @param response
		 * @return ModelAndView
		 * @throws Exception
		 */
	
	
	public ModelAndView deleteMainLawInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			MainLawInfoForm form = new MainLawInfoForm();
			MainLawInfoVO vo = new MainLawInfoVO();
			PageUtil 	pageUtil   = null;
			List   		resultList = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL = "/las/lawinformation/mainLawInfo.do?method=listMainLawInfo";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ModelAndView mav = new ModelAndView();
	        form = new MainLawInfoForm();
	        vo   = new MainLawInfoVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			// 권한체크 
			boolean auth = mainLawInfoService.authMainLawInfo(DELETE, vo);
			String accessLevel = mainLawInfoService.checkAuthByRole(vo);
			
			if(auth && "A".equals(accessLevel)){
				vo.setDel_id(vo.getSession_user_id());
				vo.setDel_nm(vo.getSession_user_nm());
				form.setDel_id(vo.getSession_user_id());
				form.setDel_nm(vo.getSession_user_nm());
				form.setDel_id(vo.getSession_user_id());
				form.setDel_nm(vo.getSession_user_nm());
				
				int result = mainLawInfoService.deleteMainLawInfo(vo);
				
				// 메시지처리 - 삭제되었습니다.
				returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());
				
				mav.setViewName(forwardURL);
				mav.addObject("command", form);
				mav.addObject("returnMessage", returnMessage);

	       	}else {

	        	mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
	            // 메시지처리 - 삭제권한이 없습니다. 
	        	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noDeleteAuth", null, new RequestContext(request).getLocale());
	            // 메시지처리 - 시스템 관리자에게 문의하십시오.
	        	String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());

	            mav.addObject("secfw.alert.title", alertTitle);
	            mav.addObject("secfw.alert.message", alertMessage);                     
	        }
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("MainLawInfoController.deleteMainLawInfo() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("MainLawInfoController.deleteMainLawInfo() Throwable !!");
		}
	}
	
	/**
	 * 주요 입법동향  상세(법무메인)
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailMainLawInfoByMain(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/las/lawinformation/MainLawInfo_p.jsp";
				
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			MainLawInfoForm form = new MainLawInfoForm();
			MainLawInfoVO vo =  new MainLawInfoVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setInfo_gbn("C00405");
			
			List resultList = null;
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = mainLawInfoService.detailMainLawInfo(vo);
			
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

			if(resultList==null)
				throw new Exception("##### queryService is null ##### ");
			
			// 본인 글이 아닐 경우 조회수 증가			
			if(!vo.getSession_user_id().equals(lom.get("reg_id"))) {
				mainLawInfoService.increseRdcnt(vo) ;  	  			
			}    
			
			/*********************************************************
			 * Massage
			**********************************************************/			 
			returnMessage = StringUtil.bvl((String)request.getAttribute("returnMessage"), "");

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			mav.setViewName(forwardURL);
			
			mav.addObject("resultList", resultList);
			mav.addObject("lom", lom);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
			return mav;
			 
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("MainLawInfoController.detailMainLawInfoByMain() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("MainLawInfoController.detailMainLawInfoByMain() Throwable !!");
		}
	}	

}