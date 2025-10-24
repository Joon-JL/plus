/**
 * Project Name : 계약관리시스템
 * File name	: ContractAttentionController.java
 * Description	:  계약지식공유 > 계약체결유의사항 Controller
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

import com.sec.clm.share.dto.ContractAttentionForm;
import com.sec.clm.share.dto.ContractAttentionVO;
import com.sec.clm.share.dto.LawTermsForm;
import com.sec.clm.share.dto.LawTermsVO;
import com.sec.clm.share.service.ContractAttentionService;
import com.sec.common.util.ClmsDataUtil;
import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.util.service.ComUtilService;

/**
 * 계약지식공유 > 계약 체결시 유의사항 > 유의사항 목록
 * 
 * @author 유영섭 Date : 2011. 08. 04
 */

public class ContractAttentionController extends CommonController {

	/**
	 * ContractAttention 서비스
	 */

	private ContractAttentionService contractAttentionService;

	/**
	 * 
	 * @param ContractAttentionService
	 */

	public void setContractAttentionService(
			ContractAttentionService contractAttentionService) {
		this.contractAttentionService = contractAttentionService;
	}

	/**
	 * ComUtil 서비스
	 * 
	 */
	private ComUtilService comUtilService;

	/**
	 * ComUtil 서비스 세팅
	 * 
	 * @param comUtilService
	 * @return void
	 * @throws
	 */

	public void setComUtilService(ComUtilService comUtilService) {
		this.comUtilService = comUtilService;
	}

	/**
	 * 계약 지식공유 > 계약 체결시 유의사항 > 유의사항목록
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */

	public ModelAndView listContractAttention(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			/*********************************************************
			 * Parameter
			 **********************************************************/
			String forwardURL = "";
			String returnMessage = "";

			ContractAttentionForm form = new ContractAttentionForm();
			ContractAttentionVO vo = new ContractAttentionVO();
			PageUtil pageUtil = null;
			List resultList = null;

			/*********************************************************
			 * Parameter setting
			 **********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/share/ContractAttention_l.jsp";

			/*********************************************************
			 * Form & VO Binding
			 **********************************************************/
			form = new ContractAttentionForm();
			vo = new ContractAttentionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			// 초기 화면검색요청 시 검색 파라미터 초기화 : NULL 값 방지
			// 초기 화면검색요청 시 검색 파라미터 초기화 : NULL 값 방지
			if (form.getSrch_bigclsfcn() == null) {
				form.setSrch_bigclsfcn("");
				vo.setSrch_bigclsfcn("");
			}

			if (form.getSrch_midclsfcn() == null) {
				form.setSrch_midclsfcn("");
				vo.setSrch_midclsfcn("");
			}

			if (form.getSrch_smlclsfcn() == null) {
				form.setSrch_smlclsfcn("");
				vo.setSrch_smlclsfcn("");
			}

			/*********************************************************
			 * Page setting
			 **********************************************************/

			pageUtil = new PageUtil();
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(
					form.getCurPage(), "1")));

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(
					form.getCurPage(), "1")));

			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());

			/*********************************************************
			 * Service
			 **********************************************************/
			resultList = contractAttentionService.listContractAttention(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap) resultList.get(0);

				pageUtil.setTotalRow(((Integer) lom.get("total_cnt"))
						.intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();

				// 메시지처리 - 정상적으로 조회되었습니다.
				if ((String) request.getAttribute("returnMessage") != null
						&& ((String) request.getAttribute("returnMessage"))
								.length() > 1) {
					returnMessage = (String) request
							.getAttribute("returnMessage");
				} else {
					// 메뉴 최초 진입 시 메세지 여부
					if ("Y".equals(vo.getDoSearch()))
						returnMessage = messageSource.getMessage(
								"secfw.msg.succ.search", null,
								new RequestContext(request).getLocale());
				}
			} else {
				// 메시지처리 - 조회된 결과가 없습니다.
				if ((String) request.getAttribute("returnMessage") != null
						&& ((String) request.getAttribute("returnMessage"))
								.length() > 1) {
					returnMessage = (String) request
							.getAttribute("returnMessage");
				} else {
					// 메뉴 최초 진입 시 메세지 여부
					if ("Y".equals(vo.getDoSearch()))
						returnMessage = messageSource.getMessage(
								"secfw.msg.succ.noResult", null,
								new RequestContext(request).getLocale());
				}
			}

			//등록권한 설정
			boolean insertAuth = contractAttentionService.authContractAttention(INSERT, vo);
			form.setAuth_insert(insertAuth);
			
			ArrayList lom = (ArrayList) resultList;

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);

			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
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
	 * 계약 지식공유 > 계약 지식공유 > 계약체결시 유의사항 등록 forward
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */

	public ModelAndView forwardContractAttentionInsert(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {

			/*********************************************************
			 * Parameter
			 **********************************************************/
			String forwardURL = "";

			ContractAttentionForm form = null;
			ContractAttentionVO vo = null;

			/*********************************************************
			 * Parameter setting
			 **********************************************************/

			forwardURL = "/WEB-INF/jsp/clm/share/ContractAttention_i.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			form = new ContractAttentionForm();
			vo = new ContractAttentionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * ModelAndView
			 **********************************************************/
			ModelAndView mav = new ModelAndView();

			//등록권한 체크
			boolean insertAuth = contractAttentionService.authContractAttention(INSERT, vo);
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

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 계약 지식공유 > 계약 체결시유의 사항 > 계약 체결시 유의사항 등록
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */

	public ModelAndView insertContractAttention(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Parameter
			 **********************************************************/
			String forwardURL = "";
			String returnMessage = "";

			ContractAttentionForm form = null;
			ContractAttentionVO vo = null;

			/*********************************************************
			 * Parameter setting
			 **********************************************************/
			forwardURL = "/clm/share/ContractAttention.do?method=detailContractAttention";

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			form = new ContractAttentionForm();
			vo = new ContractAttentionVO();


			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			ModelAndView mav = new ModelAndView();
			
			boolean insertAuth = contractAttentionService.authContractAttention(INSERT, vo);
			if(insertAuth){
				// Cross-site Scripting 방지
				vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
				vo.setCont(StringUtil.convertHtmlTochars(vo.getCont()));

				// cont에 들어있는 내용은 service단에서 나모파일 저장 & convert 후 저장


				vo.setReg_id(vo.getSession_user_id());
				vo.setReg_nm(vo.getSession_user_nm());

				/*********************************************************
				 * Service
				 **********************************************************/

				int mtbat_no = contractAttentionService.insertContractAttention(vo);

				form.setMtbat_no(mtbat_no);
				vo.setMtbat_no(mtbat_no);

				/*********************************************************
				 * Massage
				 **********************************************************/
				// 메시지처리 - 등록되었습니다.
				returnMessage = messageSource.getMessage("secfw.msg.succ.insert",
						null, new RequestContext(request).getLocale());

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
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

	/**
	 * 계약 지식공유 > 계약지식공유 > 계약체결시유의사항 > 상세
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */

	public ModelAndView detailContractAttention(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			
			ContractAttentionForm cmd = (ContractAttentionForm)request.getAttribute("command");
			

			/*********************************************************
			 * Parameter
			 **********************************************************/
			String forwardURL = "";
			String returnMessage = "";

			ContractAttentionForm form = new ContractAttentionForm();
			ContractAttentionVO vo = new ContractAttentionVO();
			List resultList = null;

			/*********************************************************
			 * Parameter setting
			 **********************************************************/

			forwardURL = "/WEB-INF/jsp/clm/share/ContractAttention_d.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			form = new ContractAttentionForm();
			vo = new ContractAttentionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			if(cmd != null){
				form = cmd;				
				vo.setMtbat_no(form.getMtbat_no());				
			}
			
			/*********************************************************
             * 권한에 따른 처리 처리
            **********************************************************/   
			ModelAndView mav = new ModelAndView();
			
			boolean searchAuth = contractAttentionService.authContractAttention(SEARCH, vo);
			if(searchAuth){
	            boolean modifyAuth = contractAttentionService.authContractAttention(MODIFY, vo);
	            boolean deleteAuth = contractAttentionService.authContractAttention(DELETE, vo);
				form.setAuth_modify(modifyAuth);
				form.setAuth_delete(deleteAuth);				
				
				/*********************************************************
				 * 조회 처리
				**********************************************************/		
				resultList = contractAttentionService.detailContractAttention(vo);
				if(resultList != null && resultList.size()>0){
					ListOrderedMap lom = (ListOrderedMap) resultList.get(0);

					// 본인 글이 아닐 경우 조회수 증가
					if (!vo.getSession_user_id().equals(lom.get("reg_id"))) {
						getLogger().debug("###### Increase Rdcnt #####");
						contractAttentionService.increseRdcnt(vo);
					}
					/*********************************************************
					 * Massage
					 **********************************************************/
					returnMessage = StringUtil.bvl((String) request.getAttribute("returnMessage"), "");

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

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(
					"ContractAttentionController.detailContractAttention() Exception !!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception(
					"ContractAttentionController.detailContractAttention() Throwable !!");
		}
	}

	/**
	 * 계약 지식공유 > 계약 체결시 유의사항 > 계약체결시 유의사항 수정 포워드 수정페이지
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */

	public ModelAndView forwardContractAttentionModify(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {

			/*********************************************************
			 * Parameter
			 **********************************************************/
			String forwardURL = "";
			String returnMessage = "";

			ContractAttentionForm form = null;
			ContractAttentionVO vo = null;
			List resultList = null;

			/*********************************************************
			 * Parameter setting
			 **********************************************************/
			forwardURL = "/WEB-INF/jsp/clm/share/ContractAttention_u.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			form = new ContractAttentionForm();
			vo = new ContractAttentionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * ModelAndView
			 **********************************************************/
			ModelAndView mav = new ModelAndView();
			
			boolean modifyAuth = contractAttentionService.authContractAttention(MODIFY, vo);
			if(modifyAuth){
				/*********************************************************
				 * Service
				 **********************************************************/
				resultList = contractAttentionService.detailContractAttention(vo);

				if(resultList != null && resultList.size()>0){
					ListOrderedMap lom = (ListOrderedMap) resultList.get(0);
					
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

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(
					"ContractAttentionController.forwardContractAttentionModify() Exception !!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception(
					"ContractAttentionController.forwardContractAttentionModify() Throwable !!");
		}
	}

	/**
	 * 계약 지식공유 > 계약 체결시 유의사항 > 계약체결시 유의사항 수정
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */

	public ModelAndView modifyContractAttention(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			/*********************************************************
			 * Parameter
			 **********************************************************/
			String forwardURL = "";
			String returnMessage = "";

			ContractAttentionForm form = null;
			ContractAttentionVO vo = null;

			/*********************************************************
			 * Parameter
			 **********************************************************/
			forwardURL = "/clm/share/ContractAttention.do?method=detailContractAttention";

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ModelAndView mav = new ModelAndView();
			form = new ContractAttentionForm();
			vo = new ContractAttentionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			boolean modifyAuth = contractAttentionService.authContractAttention(MODIFY, vo);
				
			if(modifyAuth){
					
				vo.setMod_id(vo.getSession_user_id());
				vo.setMod_nm(vo.getSession_user_nm());
	
				form.setMod_id(vo.getSession_user_id());
				form.setMod_nm(vo.getSession_user_nm());
	
				vo.setTitle(StringUtil.convertHtmlTochars(vo.getTitle()));
	
				/*********************************************************
				 * Service
				 **********************************************************/
				int resultList = contractAttentionService.modifyContractAttention(vo);
	
				/*********************************************************
				 * Massage
				 **********************************************************/
				// 메시지처리 - 수정되었습니다.
				returnMessage = messageSource.getMessage("secfw.msg.succ.modify",null, new RequestContext(request).getLocale());
	
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

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(
					"LawTermsController.modifyLawTerms() Exception !!");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception(
					"LawTermsController.modifyLawTerms() Throwable !!");
		}
	}

	/**
	 * 계약 지식공유 > 계약 체결시 유의사항 > 게약체결시 유의사항 삭제
	 * 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */

	public ModelAndView deleteContractAttention(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {

			/*********************************************************
			 * Parameter
			 **********************************************************/
			String forwardURL = "";
			String returnMessage = "";

			ContractAttentionForm form = null;
			ContractAttentionVO vo = null;
			PageUtil pageUtil = null;
			List resultList = null;

			/*********************************************************
			 * Parameter
			 **********************************************************/
			forwardURL = "/clm/share/ContractAttention.do?method=listContractAttention";

			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			ModelAndView mav = new ModelAndView();
			form = new ContractAttentionForm();
			vo = new ContractAttentionVO();

			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			// 권한체크
			boolean deleteAuth = contractAttentionService.authContractAttention(DELETE, vo);
			
			if(deleteAuth){
				vo.setDel_id(vo.getSession_user_id());
				vo.setDel_nm(vo.getSession_user_nm());
	
				form.setDel_id(vo.getSession_user_id());
				form.setDel_nm(vo.getSession_user_nm());
	
				/*********************************************************
				 * Service
				 **********************************************************/
	
				int result = contractAttentionService.deleteContractAttention(vo);
	
				/*********************************************************
				 * Massage
				 **********************************************************/
				// 메시지처리 - 삭제되었습니다.
				returnMessage = messageSource.getMessage("secfw.msg.succ.delete",
						null, new RequestContext(request).getLocale());
	
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

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}

}