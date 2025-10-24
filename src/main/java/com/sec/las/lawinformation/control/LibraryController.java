/**
 * Project Name : 법무시스템 이식
 * File name	: LibraryController.java
 * Description	: 국내/해외계약서  Controller
 * Author		: 신승완
 * Date			: 2011. 08. 04
 * Copyright	:
 */
package com.sec.las.lawinformation.control;

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
import com.sec.las.lawinformation.dto.LibraryForm;
import com.sec.las.lawinformation.dto.LibraryVO;
import com.sec.las.lawinformation.service.LibraryService;

/**
 * Description	: 국내/해외계약서  Controller
 * Author 		: 신승완
 * Date			: 2011. 08. 04
 */
public class LibraryController extends CommonController {
	
	/**
	 * Library 서비스
	 */
	private LibraryService libraryService;
	
	/**
	 * Library 서비스 세팅
	 * 
	 * @param libraryService
	 * @return void
	 * @throws
	 */
	public void setLibraryService(LibraryService libraryService) {
		this.libraryService = libraryService;
	}
	

	/**
	 * 국내/해외계약서  목록조회
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView listLibrary(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			String accessLevel	 = "";
			
			LibraryForm form       = null;
			LibraryVO   vo         = null;
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
			forwardURL = "/WEB-INF/jsp/las/lawinformation/Library_l.jsp";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new LibraryForm();
			vo   = new LibraryVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			//  초기 화면검색요청 시 검색 파라미터 초기화 : NULL 값 방지
			// form setting
			form.setInfo_gbn(StringUtil.bvl(form.getInfo_gbn(),"error"));

			if("error".equals(form.getInfo_gbn()))
				throw new Exception("##### info_gbn is null #####");
			
			//Cross-site Scripting 방지
			form.setSrch_start_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_start_dt(),"")));
			form.setSrch_end_dt(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_end_dt(),"")));
			form.setSrch_name(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_name(),"")));
			form.setSrch_lib_gbn(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_lib_gbn(),"")));
			
			// vo setting
			vo.setInfo_gbn(StringUtil.bvl(vo.getInfo_gbn(),"error"));

			if("error".equals(vo.getInfo_gbn()))
				throw new Exception("##### info_gbn is null #####");
			
			if("C00401".equals(form.getInfo_gbn())){
				form.setInfo_gbn("C00404");
				vo.setInfo_gbn("C00404");
			}
			
			vo.setSrch_start_dt(StringUtil.bvl(vo.getSrch_start_dt(),""));
			vo.setSrch_end_dt(StringUtil.bvl(vo.getSrch_end_dt(),""));
			vo.setSrch_name(StringUtil.bvl(vo.getSrch_name(),""));
			vo.setSrch_lib_gbn(StringUtil.bvl(vo.getSrch_lib_gbn(),""));

			/*********************************************************
             * 권한 처리
            **********************************************************/ 
			accessLevel = libraryService.checkAuthByRole(vo);
			// 조회권한-법무팀인 건: 관리자, 법무팀만 조회가능 
			vo.setTop_role(accessLevel);
			if ("A".equals(accessLevel)){
				form.setAuth_insert(true);
			}
			
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
			resultList = libraryService.listLibrary(vo);
			
			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap tmp = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(((Integer)tmp.get("total_cnt")).intValue());
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
			 * ModelAndView
			**********************************************************/
			//form.setReturn_message(returnMessage);
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
			throw new Exception("LibraryController.listLibrary() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("LibraryController.listLibrary() Throwable !!");
		}
	}

	/**
	 * 국내/해외계약서   등록페이지 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardInsertLibrary(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			
			LibraryForm form       = null;
			LibraryVO   vo         = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/las/lawinformation/Library_i.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/			
			form = new LibraryForm();
			vo   = new LibraryVO();
			
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
			throw new Exception("LibraryController.forwardInsertLibrary() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("LibraryController.forwardInsertLibrary() Throwable !!");
		}
	}

	/**
	 * 국내/해외계약서   등록
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertLibrary(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			LibraryForm form       = null;
			LibraryVO   vo         = null;

			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL =  "/las/lawinformation/library.do?method=listLibrary";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new LibraryForm();
			vo   = new LibraryVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//Cross-site Scripting 방지
			vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
			vo.setUsage(StringUtil.convertHtmlTochars(vo.getUsage()));
			
			//cont에 들어있는 내용은 service단에서 나모파일 저장 & convert 후 저장

			vo.setReg_id(vo.getSession_user_id());
			vo.setReg_nm(vo.getSession_user_nm());
			vo.setReg_dept(vo.getSession_dept_cd());
			vo.setReg_dept_nm(vo.getSession_dept_nm());
			
			/*********************************************************
			 * Service
			**********************************************************/
			int seqno = libraryService.insertLibrary(vo);
			
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
			throw new Exception("LibraryController.insertLibrary() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("LibraryController.insertLibrary() Throwable !!");
		}
	}
	
	/**
	 * 국내/해외계약서   상세정보
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailLibrary(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			LibraryForm cmd = (LibraryForm)request.getAttribute("command");
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			LibraryForm form       = null;
			LibraryVO   vo         = null;
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
			forwardURL =  "/WEB-INF/jsp/las/lawinformation/Library_d.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new LibraryForm();
			vo   = new LibraryVO();

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
			resultList = libraryService.getLibrary(vo);

			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			
			if(resultList==null)
				throw new Exception("##### queryService is null ##### ");
			
			/*********************************************************
             * 권한 처리
            **********************************************************/         
            boolean modifyAuth = libraryService.authLibrary(MODIFY, vo);
            boolean deleteAuth = libraryService.authLibrary(DELETE, vo);
			String accessLevel = libraryService.checkAuthByRole(vo);
			
			if ("A".equals(accessLevel)){
	            form.setAuth_modify(modifyAuth);
	            form.setAuth_delete(deleteAuth);
			} 

			// 본인 글이 아닐 경우 조회수 증가			
			if(!vo.getSession_user_id().equals(lom.get("reg_id"))) {
				libraryService.increseRdcnt(vo) ;				
			}
			
			/*********************************************************
			 * Massage
			**********************************************************/
			returnMessage = StringUtil.bvl((String)request.getAttribute("returnMessage"), "");

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			// 법무팀이 아닌데 조회한 데이터가 법무팀Only인 경우 에러
			if (!"A".equals(accessLevel) && "C00503".equals(vo.getRd_auth())){
	        	
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
	            // 메시지처리 - 페이지 권한이 없습니다. 
	        	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noAuthTitle", null, new RequestContext(request).getLocale());
	            // 메시지처리 - 시스템 관리자에게 문의하십시오.
	        	String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
	            mav.addObject("secfw.alert.title", alertTitle);
	            mav.addObject("secfw.alert.message", alertMessage);  
			}else {

				mav.setViewName(forwardURL);
				
				mav.addObject("resultList", resultList);
				mav.addObject("lom", lom);
				mav.addObject("command", form);
				mav.addObject("returnMessage", returnMessage);
			}
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("LibraryController.detailLibrary() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("LibraryController.detailLibrary() Throwable !!");
		}
	}

	/**
	 * 국내/해외계약서   수정 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardModifyLibrary(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			LibraryForm form       = null;
			LibraryVO   vo         = null;
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
			forwardURL = "/WEB-INF/jsp/las/lawinformation/Library_u.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new LibraryForm();
			vo   = new LibraryVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = libraryService.getLibrary(vo);
			
			if(resultList==null)
				throw new Exception("##### queryService is null ##### ");
			
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			if(lom != null){
				
				boolean auth = libraryService.authLibrary(MODIFY, vo);
				String accessLeve = libraryService.checkAuthByRole(vo);
				
				if(auth && "A".equals(accessLeve)){
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
			throw new Exception("LibraryController.forwardModifyLibrary() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("LibraryController.forwardModifyLibrary() Throwable !!");
		}
	}

	/**
	 * 국내/해외계약서   수정 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifyLibrary(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			LibraryForm form       = null;
			LibraryVO   vo         = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL =  "/las/lawinformation/library.do?method=listLibrary";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			form = new LibraryForm();
			vo   = new LibraryVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			boolean auth = libraryService.authLibrary(MODIFY, vo);
			String accessLeve = libraryService.checkAuthByRole(vo);
			
			if(auth && "A".equals(accessLeve)){

				vo.setMod_id(vo.getSession_user_id());
				vo.setMod_nm(vo.getSession_user_nm());
				
				form.setMod_id(vo.getSession_user_id());
				form.setMod_nm(vo.getSession_user_nm());
				
				vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
				vo.setUsage(StringUtil.convertHtmlTochars(vo.getUsage()));
				
				int result = libraryService.modifyLibrary(vo);
				
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
			throw new Exception("LibraryController.modifyLibrary() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("LibraryController.modifyLibrary() Throwable !!");
		}
	}

	/**
	 * 국내/해외계약서   삭제
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView deleteLibrary(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			LibraryForm form       = null;
			LibraryVO   vo         = null;
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
			forwardURL = "/las/lawinformation/library.do?method=listLibrary";
	
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			form = new LibraryForm();
			vo   = new LibraryVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			// 권한체크 
			boolean auth = libraryService.authLibrary(DELETE, vo);
			String accessLevel = libraryService.checkAuthByRole(vo);
			
			if(auth && "A".equals(accessLevel)){
			
				vo.setDel_id(vo.getSession_user_id());
				vo.setDel_nm(vo.getSession_user_nm());
				
				form.setDel_id(vo.getSession_user_id());
				form.setDel_nm(vo.getSession_user_nm());
	
				int result = libraryService.deleteLibrary(vo);
	
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
			throw new Exception("LibraryController.deleteLibrary() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("LibraryController.deleteLibrary() Throwable !!");
		}
	}

}