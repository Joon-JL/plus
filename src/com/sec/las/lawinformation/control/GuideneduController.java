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
import com.sec.las.lawinformation.dto.GuideneduForm;
import com.sec.las.lawinformation.dto.GuideneduVO;
import com.sec.las.lawinformation.service.GuideneduService;

public class GuideneduController extends CommonController {
	/**
	 * Business Logic 서비스 호출을 위한 선언 및 세팅
	 */
	private GuideneduService guideneduService;
	
	public void setGuideneduService(GuideneduService guideneduService) {
		this.guideneduService = guideneduService;
	}

	/**
	 * 가이드라인(교육) 리스트 정보
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public ModelAndView listGuidenedu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			GuideneduForm form       = null;
			GuideneduVO   vo         = null;
			PageUtil 	  pageUtil   = null;
			List   		  resultList = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/las/lawinformation/Guidenedu_l.jsp";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new GuideneduForm();
			vo   = new GuideneduVO();	
			
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
			form.setSrch_word(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_word(),"")));
			form.setSrch_type(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_type(),"")));
			
			// vo setting
			vo.setInfo_gbn(StringUtil.bvl(vo.getInfo_gbn(),"error"));

			if("error".equals(vo.getInfo_gbn()))
				throw new Exception("##### info_gbn is null #####");
			
			vo.setSrch_start_dt(StringUtil.bvl(vo.getSrch_start_dt(),""));
			vo.setSrch_end_dt(StringUtil.bvl(vo.getSrch_end_dt(),""));
			vo.setSrch_word(StringUtil.bvl(vo.getSrch_word(),""));
			vo.setSrch_type(StringUtil.bvl(vo.getSrch_type(),""));
			
			/*********************************************************
			 * Page  setting
			**********************************************************/
			pageUtil = new PageUtil();
			
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());	//현재 페이지의 첫번째 게시물번호  set
			vo.setEnd_index(pageUtil.getEndIndex());		//현재 페이지의 마지막 게시물번호  set

			if(form.getCurPage() == null) {
				setInitFormVO(request, form, vo);
			}
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = guideneduService.listGuidenedu(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap tmp = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(Integer.parseInt(tmp.get("total_cnt").toString()));
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
			String accessLevel = guideneduService.checkAuthByRole(vo);
			if ("A".equals(accessLevel)){
				form.setAuth_insert(true);
			}
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			form.setReturn_message(returnMessage);
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
			throw new Exception("GuideneduController.listGuidenedu() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("GuideneduController.listGuidenedu() Throwable !!");
		}
	}	

	/**
	 * 계약서 GuideLine 리스트 정보
	 * @param request
	 * @param response
	 * @return JSON Type
	 * @throws Exception
	 */
	public ModelAndView listGuideline(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			GuideneduForm form       = null;
			GuideneduVO   vo         = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/las/lawinformation/Guideline_l.jsp";
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new GuideneduForm();
			vo   = new GuideneduVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			// form, vo Info_gbn setting
			form.setInfo_gbn("C00401");
			vo.setInfo_gbn("C00401");

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			returnMessage = (String)request.getAttribute("returnMessage");
			
			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("GuideneduController.listGuideline() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("GuideneduController.listGuideline() Throwable !!");
		}
	}	

	/**
	 * 임직원교육법무자료 등록페이지 foward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardInsertGuidenedu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			
			GuideneduForm form       = null;
			GuideneduVO   vo         = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/las/lawinformation/Guidenedu_i.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/			
			form = new GuideneduForm();
			vo   = new GuideneduVO();
			
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
			throw new Exception("GuideneduController.forwardInsertGuidenedu() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("GuideneduController.forwardInsertGuidenedu() Throwable !!");
		}
	}

	/**
	 * 계약서 GuideLine 등록페이지 foward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardInsertGuideline(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			
			GuideneduForm form       = null;
			GuideneduVO   vo         = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/las/lawinformation/Guideline_i.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/			
			form = new GuideneduForm();
			vo   = new GuideneduVO();
			
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
			throw new Exception("GuideneduController.forwardInsertGuideline() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("GuideneduController.forwardInsertGuideline() Throwable !!");
		}
	}

	/**
	 * 임직원법무교육자료   등록
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertGuidenedu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			GuideneduForm form       = null;
			GuideneduVO   vo         = null;

			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL =  "/las/lawinformation/guidenedu.do?method=detailGuidenedu";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new GuideneduForm();
			vo   = new GuideneduVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//Cross-site Scripting 방지
			vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
			
			//cont에 들어있는 내용은 service단에서 나모파일 저장 & convert 후 저장

			vo.setReg_id(vo.getSession_user_id());
			vo.setReg_nm(vo.getSession_user_nm());
			vo.setReg_dept(vo.getSession_dept_cd());
			vo.setReg_dept_nm(vo.getSession_dept_nm());
			
			/*********************************************************
			 * Service
			**********************************************************/
			String accessLevel = guideneduService.checkAuthByRole(vo);
		    int seqno =0;
		    
		    if ("A".equals(accessLevel)){
		    	seqno = guideneduService.insertGuidenedu(vo);
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
			throw new Exception("GuideneduController.insertGuidenedu() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("GuideneduController.insertGuidenedu() Throwable !!");
		}
	}

	/**
	 * 계약서 GuideLine  등록
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView insertGuideline(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			GuideneduForm form       = null;
			GuideneduVO   vo         = null;

			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL =  "/las/lawinformation/guidenedu.do?method=listGuideline";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new GuideneduForm();
			vo   = new GuideneduVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//Cross-site Scripting 방지
			vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
			
			//cont에 들어있는 내용은 service단에서 나모파일 저장 & convert 후 저장

			vo.setReg_id(vo.getSession_user_id());
			vo.setReg_nm(vo.getSession_user_nm());
			vo.setReg_dept(vo.getSession_dept_cd());
			vo.setReg_dept_nm(vo.getSession_dept_nm());
			
			/*********************************************************
			 * Service
			**********************************************************/
			int seqno = guideneduService.insertGuideline(vo);
			
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
			throw new Exception("GuideneduController.insertGuideline() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("GuideneduController.insertGuideline() Throwable !!");
		}
	}
	
	/**
	 * 임직원법무교육자료   상세정보
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailGuidenedu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			GuideneduForm cmd = (GuideneduForm)request.getAttribute("command");
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			GuideneduForm  form       = null;
			GuideneduVO    vo         = null;
			List   		   resultList = null;
			ListOrderedMap lom 		  = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL =  "/WEB-INF/jsp/las/lawinformation/Guidenedu_d.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new GuideneduForm();
			vo   = new GuideneduVO();

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
			resultList = guideneduService.getGuidenedu(vo);
			lom = (ListOrderedMap)resultList.get(0);
			
			if(resultList==null)
				throw new Exception("##### queryService is null ##### ");
			
			/*********************************************************
             * 권한 처리
            **********************************************************/         
            boolean modifyAuth = guideneduService.authGuidenedu(MODIFY, vo);
            boolean deleteAuth = guideneduService.authGuidenedu(DELETE, vo);
            form.setAuth_modify(modifyAuth);
            form.setAuth_delete(deleteAuth);
				
			// 본인 글이 아닐 경우 조회수 증가			
			if(!vo.getSession_user_id().equals(lom.get("reg_id"))) {
				guideneduService.increseRdcnt(vo) ;				
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
			throw new Exception("GuideneduController.detailGuidenedu() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("GuideneduController.detailGuidenedu() Throwable !!");
		}
	}
	
	/**
	 * 계약서 GuideLine 상세정보
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView detailGuideline(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			GuideneduForm cmd = (GuideneduForm)request.getAttribute("command");
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			GuideneduForm  form       = null;
			GuideneduVO    vo         = null;
			List   		   resultList = null;
			ListOrderedMap lom 		  = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL =  "/WEB-INF/jsp/las/lawinformation/Guideline_d.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new GuideneduForm();
			vo   = new GuideneduVO();

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
			resultList = guideneduService.getGuidenedu(vo);
			
			if(resultList.size()>0) {
				lom = (ListOrderedMap)resultList.get(0);
				returnMessage = StringUtil.bvl((String)request.getAttribute("returnMessage"), "");
				
			} else {
				returnMessage = "noResult";
			}

			/*********************************************************
             * 권한 처리
            **********************************************************/     
            boolean modifyAuth = guideneduService.authGuidenedu(MODIFY, vo);
            boolean deleteAuth = guideneduService.authGuidenedu(DELETE, vo);
            form.setAuth_modify(modifyAuth);
            form.setAuth_delete(deleteAuth);
            
			String accessLevel = guideneduService.checkAuthByRole(vo);  
			if ("A".equals(accessLevel)){
				form.setAuth_insert(true);
			}

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
			throw new Exception("GuideneduController.detailGuideline() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("GuideneduController.detailGuideline() Throwable !!");
		}
	}

	/**
	 * 임직원법무교육자료   수정 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardModifyGuidenedu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			GuideneduForm form       = null;
			GuideneduVO   vo         = null;
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
			forwardURL = "/WEB-INF/jsp/las/lawinformation/Guidenedu_u.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new GuideneduForm();
			vo   = new GuideneduVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = guideneduService.getGuidenedu(vo);
			
			if(resultList==null)
				throw new Exception("##### queryService is null ##### ");
			
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			if(lom != null){
				
				boolean auth = guideneduService.authGuidenedu(MODIFY, vo);
				
				if(auth){
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
			throw new Exception("GuideneduController.forwardModifyGuidenedu() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("GuideneduController.forwardModifyGuidenedu() Throwable !!");
		}
	}

	/**
	 * 계약서 GuideLine 수정 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView forwardModifyGuideline(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			GuideneduForm form       = null;
			GuideneduVO   vo         = null;
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
			forwardURL = "/WEB-INF/jsp/las/lawinformation/Guideline_u.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new GuideneduForm();
			vo   = new GuideneduVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = guideneduService.getGuidenedu(vo);
			
			if(resultList==null)
				throw new Exception("##### queryService is null ##### ");
			
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			if(lom != null){
				
				boolean auth = guideneduService.authGuidenedu(MODIFY, vo);
				
				if(auth){
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
			throw new Exception("GuideneduController.forwardModifyGuideline() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("GuideneduController.forwardModifyGuideline() Throwable !!");
		}
	}

	/**
	 * 임직원법무교육자료   수정 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifyGuidenedu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			GuideneduForm form       = null;
			GuideneduVO   vo         = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL =  "/las/lawinformation/guidenedu.do?method=detailGuidenedu";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			form = new GuideneduForm();
			vo   = new GuideneduVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			boolean auth = guideneduService.authGuidenedu(MODIFY, vo);
			
			if(auth){

				vo.setMod_id(vo.getSession_user_id());
				vo.setMod_nm(vo.getSession_user_nm());
				
				form.setMod_id(vo.getSession_user_id());
				form.setMod_nm(vo.getSession_user_nm());
				
				vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
				
				int result = guideneduService.modifyGuidenedu(vo);
				
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
			throw new Exception("GuideneduController.modifyGuidenedu() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("GuideneduController.modifyGuidenedu() Throwable !!");
		}
	}

	/**
	 * 계약서 GuideLine 수정 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView modifyGuideline(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			GuideneduForm form       = null;
			GuideneduVO   vo         = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL =  "/las/lawinformation/guidenedu.do?method=listGuideline";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			form = new GuideneduForm();
			vo   = new GuideneduVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			boolean auth = guideneduService.authGuidenedu(MODIFY, vo);
			
			if(auth){

				vo.setMod_id(vo.getSession_user_id());
				vo.setMod_nm(vo.getSession_user_nm());
				
				form.setMod_id(vo.getSession_user_id());
				form.setMod_nm(vo.getSession_user_nm());
				
				vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
				
				guideneduService.modifyGuideline(vo);
				
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
			throw new Exception("GuideneduController.modifyGuideline() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("GuideneduController.modifyGuideline() Throwable !!");
		}
	}

	/**
	 * 임직원법무교육자료   삭제
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView deleteGuidenedu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			GuideneduForm form       = null;
			GuideneduVO   vo         = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL = "/las/lawinformation/guidenedu.do?method=listGuidenedu";
	
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			form = new GuideneduForm();
			vo   = new GuideneduVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			// 권한체크 
			boolean auth = guideneduService.authGuidenedu(DELETE, vo);
			
			if(auth){
			
				vo.setDel_id(vo.getSession_user_id());
				vo.setDel_nm(vo.getSession_user_nm());
				
				form.setDel_id(vo.getSession_user_id());
				form.setDel_nm(vo.getSession_user_nm());
	
				int result = guideneduService.deleteGuidenedu(vo);
	
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
			throw new Exception("GuideneduController.deleteGuidenedu() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("GuideneduController.deleteGuidenedu() Throwable !!");
		}
	}

	/**
	 * 계약서 GuideLine 삭제
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView deleteGuideline(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			GuideneduForm form       = null;
			GuideneduVO   vo         = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL = "/las/lawinformation/guidenedu.do?method=listGuideline";
	
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			form = new GuideneduForm();
			vo   = new GuideneduVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			// 권한체크 
			boolean auth = guideneduService.authGuidenedu(DELETE, vo);
			
			if(auth){
			
				int result = guideneduService.deleteGuideline(vo);
	
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
			throw new Exception("GuideneduController.deleteGuidenedu() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("GuideneduController.deleteGuidenedu() Throwable !!");
		}
	}

	/**
	 * Form과 VO 검색조건 및 현재페이지 변수 초기화
	 * @param request
	 * @param form
	 * @param vo
	 * @return void
	 * @throws Exception
	 */
	private void setInitFormVO(HttpServletRequest request, GuideneduForm form, GuideneduVO vo) throws Exception {
		form.setCurPage("1");
		vo.setCurPage("1");
	}
}