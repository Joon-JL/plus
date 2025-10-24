/**
 * Project Name : 계약관리시스템
 * File name	: ContractNegoPointController.java
 * Description	: 계약지식공유 > 주요거래선 포인트 
 * Author		: 유영섭
 * Date			: 2011. 09
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

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;

import com.sec.clm.share.dto.ContractNegoPointForm;
import com.sec.clm.share.dto.ContractNegoPointVO;
import com.sec.clm.share.dto.LawTermsForm;
import com.sec.clm.share.dto.LawTermsVO;

import com.sec.clm.share.service.ContractNegoPointService;
import com.sec.common.util.ClmsDataUtil;
import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.util.service.ComUtilService;

public class ContractNegoPointController extends CommonController {
	/**
	 * ContractNegoPointController서비스
	 */
	private ContractNegoPointService contractNegoPointService;
	
	public void setContractNegoPointService(ContractNegoPointService contractNegoPointService) {
		this.contractNegoPointService = contractNegoPointService;
	}
	
	/**
	 * ComUtil 서비스
	 * 
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
	 * 계약 지식공유 > 주요 거래선 유형별 협상 포인트 > 협상포인트 목록
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	

	public ModelAndView listContractNegoPoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL = "";
			String returnMessage = "";

			ContractNegoPointForm form = new ContractNegoPointForm();
			ContractNegoPointVO vo = new ContractNegoPointVO();
			PageUtil 	pageUtil   = null;
			List   		resultList = null;
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			
			forwardURL = "/WEB-INF/jsp/clm/share/ContractNegoPoint_l.jsp";	
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new ContractNegoPointForm();
			vo = new ContractNegoPointVO();
		    
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//초기 화면검색요청 시 검색 파라미터 초기화 : NULL 값 방지
			if(form.getSrch_keyword() == null) {
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
			resultList = contractNegoPointService.listContractNegoPoint(vo);
            
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
			boolean insertAuth = contractNegoPointService.authContractNegoPint(INSERT, vo);
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
			throw new Exception("ContractNegoPointController.listContractNegoPoint() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ContractNegoPointController.listContractNegoPoint() Throwable !!");
		}
	}
	
	/**
	 * 계약 지식공유 > 주요 거래선 유형별 협상 포인트 > 협상포인트 등록 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	

	public ModelAndView forwardContractNegoPointInsert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			/*********************************************************
			 * Forwarding URL
			**********************************************************/			
			                                       
		    String forwardURL = "/WEB-INF/jsp/clm/share/ContractNegoPoint_i.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ContractNegoPointForm form = new ContractNegoPointForm();
			ContractNegoPointVO vo = new ContractNegoPointVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * JSP 반환변수
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			//등록권한 체크
			boolean insertAuth = contractNegoPointService.authContractNegoPint(INSERT, vo);
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
			throw new Exception("Error");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	 * 계약 지식공유 > 주요 거래선 유형별 협상 포인트 > 협상포인트 등록 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */

	public ModelAndView insertContractNegoPoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			ContractNegoPointForm form = null;
			ContractNegoPointVO vo = null;
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL =  "/clm/share/ContractNegoPoint.do?method=detailContractNegoPoint";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/			
			form = new ContractNegoPointForm();
			vo = new ContractNegoPointVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			ModelAndView mav = new ModelAndView();
			
			boolean insertAuth = contractNegoPointService.authContractNegoPint(INSERT, vo);
			if(insertAuth){
				//Cross-site Scripting 방지
				vo.setCntrt_oppnt_nm(StringUtil.convertHtmlTochars(vo.getCntrt_oppnt_nm()));
				vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
				vo.setCustomer_cd(StringUtil.convertHtmlTochars(vo.getCustomer_cd()));                       
							
				vo.setReg_id(form.getSession_user_id());
				vo.setReg_nm(form.getSession_user_nm());
				
				/*********************************************************
				 * Service
				**********************************************************/
				
				int nego_no = contractNegoPointService.insertContractNegoPoint(vo);
				form.setNego_no(nego_no);
				vo.setNego_no(nego_no);
				
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
			throw new Exception("ContractNegoPointController.insertContractNegoPoint() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ContractNegoPointController.insertContractNegoPoint() Throwable !!");
		}
	}
	
	/**
	 * 계약 지식공유 > 주요 거래선 유형별 협상 포인트 > 협상포인트 상세
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	

	public ModelAndView detailContractNegoPoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			ContractNegoPointForm cmd = (ContractNegoPointForm)request.getAttribute("command");
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			 
			ContractNegoPointForm form = null; 
			ContractNegoPointVO vo = null;
			List   		resultList = null;

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			
			forwardURL =  "/WEB-INF/jsp/clm/share/ContractNegoPoint_d.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			
			form = new ContractNegoPointForm();
			vo = new ContractNegoPointVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			if(cmd != null){
				form = cmd;				
				vo.setNego_no(form.getNego_no());				
			}
			
			/*********************************************************
             * 권한에 따른 처리 처리
            **********************************************************/  
			ModelAndView mav = new ModelAndView();
			
			boolean searchAuth = contractNegoPointService.authContractNegoPint(SEARCH, vo);
			if(searchAuth){
	            boolean modifyAuth = contractNegoPointService.authContractNegoPint(MODIFY, vo);
	            boolean deleteAuth = contractNegoPointService.authContractNegoPint(DELETE, vo);
				form.setAuth_modify(modifyAuth);
				form.setAuth_delete(deleteAuth);				
				
				/*********************************************************
				 * Service
				**********************************************************/
			
				resultList = contractNegoPointService.detailContractNegoPoint(vo);
				if(resultList != null && resultList.size()>0){
					ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
					/*********************************************************
					 * 조회 처리
					**********************************************************/			
					// 본인 글이 아닐 경우 조회수 증가			
					if(!vo.getSession_user_id().equals(lom.get("reg_id"))) {
						contractNegoPointService.increseRdcnt(vo);				
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
			throw new Exception("ContractNegoPointController.detailContractNegoPoint() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ContractNegoPointController.detailContractNegoPoint() Exception !!");
		}
	}
	
	/**
	 * 계약 지식공유 > 주요 거래선 유형별 협상 포인트 > 협상포인트 수정 forward
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */

	public ModelAndView forwardContractNegoPointModify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			 
			ContractNegoPointForm form = null; 
			ContractNegoPointVO vo = null;
			List   		resultList = null;
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/share/ContractNegoPoint_u.jsp";
		
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			form = new ContractNegoPointForm();
			vo   = new ContractNegoPointVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			boolean modifyAuth = contractNegoPointService.authContractNegoPint(MODIFY, vo);
			if(modifyAuth){
				/*********************************************************
				 * Service
				 **********************************************************/
				resultList = contractNegoPointService.detailContractNegoPoint(vo);

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
			throw new Exception("ContractNegoPointController.forwardLawTermsModify() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ContractNegoPointController.forwardLawTermsModify() Throwable !!");
		}
	}

	
	/**
	 * 계약 지식공유 > 주요 거래선 유형별 협상 포인트 > 협상포인트 수정
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */

	public ModelAndView modifyContractNegoPoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			 
			ContractNegoPointForm form = null; 
			ContractNegoPointVO vo = null;

			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL =  "/clm/share/ContractNegoPoint.do?method=listContractNegoPoint";
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			form = new ContractNegoPointForm();
			vo   = new ContractNegoPointVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			boolean modifyAuth = contractNegoPointService.authContractNegoPint(MODIFY, vo);
			
			if(modifyAuth){

				vo.setMod_id(vo.getSession_user_id());
				vo.setMod_nm(vo.getSession_user_nm());
				
				form.setMod_id(vo.getSession_user_id());
				form.setMod_nm(vo.getSession_user_nm());
				
				vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
				vo.setBody_mime(StringUtil.convertHtmlTochars(vo.getBody_mime()));
				
			    /*********************************************************
				 * Service
				**********************************************************/
				int resultList = contractNegoPointService.modifyContractNegoPoint(vo);
				
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
			throw new Exception("ContractNegoPointController.modifyContractNegoPoint() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ContractNegoPointController.modifyContractNegoPoint() Throwable !!");
		}
	}
	
	/**
	 * 계약 지식공유 > 주요 거래선 유형별 협상 포인트 > 협상포인트 삭제
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */

	public ModelAndView deleteContractNegoPoint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			
			String forwardURL    = "";
			String returnMessage = "";
			 
			ContractNegoPointForm form = null; 
			ContractNegoPointVO vo = null;
			PageUtil 	pageUtil   = null;
			List   		resultList = null;
			
			/*********************************************************
			 * Parameter
			**********************************************************/
			forwardURL = "/clm/share/ContractNegoPoint.do?method=listContractNegoPoint";
						
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			form = new ContractNegoPointForm();
			vo   = new ContractNegoPointVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			// 권한체크 
			boolean deleteAuth = contractNegoPointService.authContractNegoPint(DELETE, vo);
			
			if(deleteAuth){
		
				vo.setDel_id(vo.getSession_user_id());
				vo.setDel_nm(vo.getSession_user_nm());
				
				form.setDel_id(vo.getSession_user_id());
				form.setDel_nm(vo.getSession_user_nm());

				/*********************************************************
				 * Service
				**********************************************************/
				int result= contractNegoPointService.deleteContractNegoPoint(vo);
				
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
			throw new Exception("ContractNegoPointController.deleteContractNegoPoint() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ContractNegoPointController.deleteContractNegoPoint() Throwable !!");
		}
	}

	

}