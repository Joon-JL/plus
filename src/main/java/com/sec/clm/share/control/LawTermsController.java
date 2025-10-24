/**
 * Project Name : 계약관리시스템
 * File name	: LawTermsController.java
 * Description	: LawTermsController
 * Author		: 유영섭
 * Date			: 2011. 08. 04
 * Copyright	:
 */

package com.sec.clm.share.control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;  

import anyframe.core.idgen.IIdGenerationService;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;

import com.sec.clm.share.dto.LawTermsForm;
import com.sec.clm.share.dto.LawTermsVO;
import com.sec.clm.share.service.LawTermsService;
import com.sec.common.util.ClmsDataUtil;
import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.util.service.ComUtilService;

/**
 * 계약지식공유 > 계약 법률용어 > 계약용어집 목록 
 * @author 유영섭
* Date	: 2011. 08. 04 
 */

public class LawTermsController extends CommonController {
	
	private LawTermsService lawTermsService;
	/**
	 * 
	 * @param LawTermsServic 서비스 세팅
	 */
	
	public void setLawTermsService(LawTermsService lawTermsService) {
		this.lawTermsService = lawTermsService;
	}
	
	/**
	 * ComUtil 서비스
	 */
	private ComUtilService comUtilService;	
	/**
	 * ComUtil 서비스 세팅
	 * @param comUtilService
	 * @return void
	 * @throws
	 */
	public void setComUtilService(ComUtilService comUtilService) {
		this.comUtilService = comUtilService;
	}
     

	/**
	 * 계약 지식공유 > 계약 법률용어 > 계약용어집 목록
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	
	public ModelAndView listLawTerms(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
						
			
		
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL = "";
			String returnMessage = "";
			
			LawTermsForm form = new LawTermsForm();
			LawTermsVO vo = new LawTermsVO();
			PageUtil 	pageUtil   = null;
			List   		resultList = null;
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/share/LawTerms_l.jsp";	
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			
			form = new LawTermsForm();
			vo = new LawTermsVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
            
			//  초기 화면검색요청 시 검색 파라미터 초기화 : NULL 값 방지
			if(form.getSrch_keyword_content() == null) {
				form.setSrch_keyword_content("");
				vo.setSrch_keyword_content("");
			}
			
			if(form.getSrch_keyword_content() == null) {
				form.setSrch_keyword("");
				vo.setSrch_keyword("");
			}
			
		
			/*********************************************************
			 * Page  setting
			**********************************************************/
			pageUtil = new PageUtil();
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(), "1")));
			
			//pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());  //현재 페이지의 첫번째 게시물번호  set
			vo.setEnd_index(pageUtil.getEndIndex());   //현재 페이지의 마지막 게시물번호  set
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = lawTermsService.listLawTerms(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap) resultList.get(0);
			

				pageUtil.setTotalRow(((Integer) lom.get("total_cnt")) .intValue());
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
			
			//등록권한 설정
			boolean insertAuth = lawTermsService.authLawTerms(INSERT, vo);
			form.setAuth_insert(insertAuth);
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("LawTermsController.listLawTerms() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("LawTermsController.listLawTerms() Throwable !!");
		}
	}
	
	/**
	  * 계약 지식공유 > 계약 법률용어 > 계약용어집 목록 등록 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */

	public ModelAndView forwardLawTermsInsert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
		
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL = "";
			
			LawTermsForm form = null;
			LawTermsVO vo = null;
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			 forwardURL = "/WEB-INF/jsp/clm/share/LawTerms_i.jsp";
			 
			/*********************************************************
			* Form 및 VO Binding
			**********************************************************/			
			form = new LawTermsForm();
			vo = new LawTermsVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			//등록권한 체크
			boolean insertAuth = lawTermsService.authLawTerms(INSERT, vo);
			if(insertAuth){
				form.setAuth_insert(insertAuth);
				mav.setViewName(forwardURL);
				mav.addObject("command", form);
				
			}else{
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				// 메시지처리 - 등록권한이 없습니다. 
		    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noInsertAuth", null, new RequestContext(request).getLocale());
		    	// 메시지처리 - 시스템 관리자에게 문의하십시오.
		    	String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
				mav.addObject("secfw.alert.title", alertTitle);
				mav.addObject("secfw.alert.message", alertMessage);  					
			}

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("LawTermsController.forwardLawTermsInsert() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("LawTermsController.forwardLawTermsInsert() Throwable !!");
		}
	}

	/**
	  * 계약 지식공유 > 계약 법률용어 > 계약용어집 등록
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	
	public ModelAndView insertLawTerms(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			LawTermsForm form  = null;
			LawTermsVO vo = null;
						
			/*********************************************************
			 * Parameter setting
			**********************************************************/
							
			forwardURL =  "/clm/share/LawTerms.do?method=detailLawTerms";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new LawTermsForm();
			vo = new LawTermsVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			ModelAndView mav = new ModelAndView();
			
			boolean insertAuth = lawTermsService.authLawTerms(INSERT, vo);
			if(insertAuth){
				//Cross-site Scripting 방지
				vo.setTerms_eng_nm(StringUtil.convertHtmlTochars(vo.getTerms_eng_nm()));
				vo.setTerms_eng_abbr_nm(StringUtil.convertHtmlTochars(vo.getTerms_eng_abbr_nm()));
				
				//cont에 들어있는 내용은 service단에서 나모파일 저장 & convert 후 저장
				vo.setReg_id(vo.getSession_user_id());
				vo.setReg_nm(vo.getSession_user_nm());
				
				/*********************************************************
				 * Service
				**********************************************************/
				int seqno = lawTermsService.insertLawTerms(vo);
				
				form.setSeqno(seqno);
				vo.setSeqno(seqno);
				/*********************************************************
				 * Massage
				**********************************************************/
				// 메시지처리 - 등록되었습니다.
				returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());

				mav.setViewName(forwardURL);
				mav.addObject("command", form);
				mav.addObject("returnMessage", returnMessage);
			}else{
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				// 메시지처리 - 등록권한이 없습니다. 
		    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noInsertAuth", null, new RequestContext(request).getLocale());
		    	// 메시지처리 - 시스템 관리자에게 문의하십시오.
		    	String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
				mav.addObject("secfw.alert.title", alertTitle);
				mav.addObject("secfw.alert.message", alertMessage);  					
			}	
			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("LawTermsController.insertLawTerms");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("LawTermsController.insertLawTerms");
		}
	}

	/**
	  * 계약 지식공유 > 계약 법률용어 > 계약용어집 등록 > 상세
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	
	public ModelAndView detailLawTerms(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			
			LawTermsForm cmd = (LawTermsForm)request.getAttribute("command");
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			  
			LawTermsForm form       = null;
			LawTermsVO   vo         = null;
			List   		resultList = null;

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL =  "/WEB-INF/jsp/clm/share/LawTerms_d.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new LawTermsForm();
			vo   = new LawTermsVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);


			if(cmd != null){
				form = cmd;				
				vo.setSeqno(form.getSeqno());				
			}

			/*********************************************************
             * 권한 처리
            **********************************************************/         
			ModelAndView mav = new ModelAndView();
			
			boolean searchAuth = lawTermsService.authLawTerms(SEARCH, vo);
			if(searchAuth){
	            boolean modifyAuth = lawTermsService.authLawTerms(MODIFY, vo);
	            boolean deleteAuth = lawTermsService.authLawTerms(DELETE, vo);
				form.setAuth_modify(modifyAuth);
				form.setAuth_delete(deleteAuth);				
				
				/*********************************************************
				 * Service
				**********************************************************/
				resultList = lawTermsService.detailLawTerms(vo);
				if(resultList != null && resultList.size()>0){
					ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
					/*********************************************************
					 * 조회 처리
					**********************************************************/			
					// 본인 글이 아닐 경우 조회수 증가			
					if(!vo.getSession_user_id().equals(lom.get("reg_id"))) {
						getLogger().debug("###### Increase Rdcnt #####");
						lawTermsService.increseRdcnt(vo) ;				
					}
					
					/*********************************************************
					 * Massage
					**********************************************************/
					returnMessage = StringUtil.bvl((String)request.getAttribute("returnMessage"), "");

					/*********************************************************
					 * ModelAndView
					**********************************************************/
					mav.setViewName(forwardURL);
					
					mav.addObject("resultList", resultList);
					mav.addObject("lom", lom);
					mav.addObject("command", form);
					mav.addObject("returnMessage", returnMessage);		
				}else{
					mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
	
					// 메시지처리 - 데이터가 존재하지 않습니다. 
			    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noDataInfo", null, new RequestContext(request).getLocale());
			    	// 메시지처리 - 시스템 관리자에게 문의하십시오.
			    	String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
					
					mav.addObject("secfw.alert.title", alertTitle);
					mav.addObject("secfw.alert.message", alertMessage);
				}				

			}else{
				mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
				// 메시지처리 - 수정권한이 없습니다. 
		    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noSearchAuth", null, new RequestContext(request).getLocale());
		    	// 메시지처리 - 시스템 관리자에게 문의하십시오.
		    	String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
				mav.addObject("secfw.alert.title", alertTitle);
				mav.addObject("secfw.alert.message", alertMessage);  					
			}	
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("LawTermsController.detailLawTerms() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("LawTermsController.detailLawTerms() Throwable !!");
		}
	}
	
	/**
	  * 계약 지식공유 > 계약 법률용어 > 계약용어집 수정 포워드 수정페이지
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	

	public ModelAndView forwardLawTermsModify(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			LawTermsForm form       = null;
			LawTermsVO   vo         = null;
			List   		resultList = null;
			

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/share/LawTerms_u.jsp";
		
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new LawTermsForm();
			vo   = new LawTermsVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			boolean modifyAuth = lawTermsService.authLawTerms(MODIFY, vo);
			if(modifyAuth){
				/*********************************************************
				 * Service
				 **********************************************************/
				resultList = lawTermsService.detailLawTerms(vo);

				if(resultList != null && resultList.size()>0){
					ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
					
					form.setAuth_modify(modifyAuth);
					mav.setViewName(forwardURL);
					mav.addObject("resultList", resultList);
					mav.addObject("lom", lom); 
					mav.addObject("command", form);
				}else{
					mav.setViewName(propertyService.getProperty("secfw.url.alertPage"));
	
					// 메시지처리 - 데이터가 존재하지 않습니다. 
			    	String alertTitle = messageSource.getMessage("secfw.page.field.alert.noDataInfo", null, new RequestContext(request).getLocale());
			    	// 메시지처리 - 시스템 관리자에게 문의하십시오.
			    	String alertMessage = messageSource.getMessage("secfw.page.field.alert.adminMessage", null, new RequestContext(request).getLocale());
					
					mav.addObject("secfw.alert.title", alertTitle);
					mav.addObject("secfw.alert.message", alertMessage);
				}				
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
			throw new Exception("LawTermsController.forwardLawTermsModify() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("LawTermsController.forwardLawTermsModify() Throwable !!");
		}
	}
	
	
	
	
	/**
	  * 계약 지식공유 > 계약 법률용어 > 계약용어집 수정
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */

	public ModelAndView modifyLawTerms(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			LawTermsForm form       = null;
			LawTermsVO   vo         = null;

			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL =  "/clm/share/LawTerms.do?method=listLawTerms";
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			form = new LawTermsForm();
			vo   = new LawTermsVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			boolean modifyAuth = lawTermsService.authLawTerms(MODIFY, vo);
			
			if(modifyAuth){
	
				vo.setMod_id(vo.getSession_user_id());
				vo.setMod_nm(vo.getSession_user_nm());
				
				form.setMod_id(vo.getSession_user_id());
				form.setMod_nm(vo.getSession_user_nm());
				
				vo.setTerms_abbr_nm(StringUtil.convertHtmlTochars(vo.getTerms_abbr_nm()));
				vo.setTerms_eng_abbr_nm(StringUtil.convertHtmlTochars(vo.getTerms_eng_abbr_nm()));
				vo.setTerms_eng_nm(StringUtil.convertHtmlTochars(vo.getTerms_eng_nm()));
				vo.setTerms_expl(StringUtil.convertHtmlTochars(vo.getTerms_expl()));
				
			    /*********************************************************
				 * Service
				**********************************************************/
				int resultList = lawTermsService.modifyLawTerms(vo);
				
				/*********************************************************
				 * Massage
				**********************************************************/
				// 메시지처리 - 수정되었습니다.
				returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());
				
				/*********************************************************
				 * ModelAndView
				**********************************************************/
			
	
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
			throw new Exception("LawTermsController.modifyLawTerms() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("LawTermsController.modifyLawTerms() Throwable !!");
		}
	}

	/**
	  * 계약 지식공유 > 계약 법률용어 > 계약용어집 삭제
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */


	public ModelAndView deleteLawTerms(HttpServletRequest request, HttpServletResponse response) throws Exception {
		     
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			LawTermsForm form       = null;
			LawTermsVO   vo         = null;
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
			forwardURL = "/clm/share/LawTerms.do?method=listLawTerms";
						
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			form = new LawTermsForm();
			vo   = new LawTermsVO();
	
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			// 권한체크 
			
			boolean deleteAuth = lawTermsService.authLawTerms(DELETE, vo);
			if(deleteAuth){
				
				vo.setDel_id(vo.getSession_user_id());
				vo.setDel_nm(vo.getSession_user_nm());
				
				form.setDel_id(vo.getSession_user_id());
				form.setDel_nm(vo.getSession_user_nm());
	
				/*********************************************************
				 * Service
				**********************************************************/
				int result= lawTermsService.deleteLawTerms(vo);
				
				/*********************************************************
				 * Massage
				**********************************************************/
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
				throw new Exception("LawTermsController.deleteLawTerms() Exception !!");
		}catch (Throwable t) {
				t.printStackTrace();
				throw new Exception("LawTermsController.deleteLawTerms() Throwable !!");
		}
}

}