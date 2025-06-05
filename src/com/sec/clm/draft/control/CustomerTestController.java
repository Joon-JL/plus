/**
* Project Name : 계약관리시스템
* File Name : CustomerController.java
* Description : 계약상대방 검색 팝업 Controller
* Author : dawn
* Date : 2011.09.29
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.draft.control;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.draft.dto.CustomerNewForm;
import com.sec.clm.draft.dto.CustomerNewVO;
import com.sec.clm.draft.dto.CustomerTestForm;
import com.sec.clm.draft.dto.CustomerTestVO;
import com.sec.clm.draft.service.CustomerTestService;

public class CustomerTestController extends CommonController {
	private CustomerTestService customerTestService;
	
	public void setCustomerTestService(CustomerTestService customerTestService) {
		this.customerTestService = customerTestService;
	}
	
	/**
	* 계약상대방 검색 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception              
	*/
	public ModelAndView listCustomerTest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String forwardURL    = "";
			String srch_custmor  = "";
			String srch_nation   = "";        // 국가 코드
			String srch_taxNo    = "";        // 사업자번호
			String srch_gerp    = "";        // gerp코드
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			CustomerTestForm form = new CustomerTestForm();
			CustomerTestVO vo = new CustomerTestVO();
			
			bind(request, form);
			bind(request, vo);
	
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/* 거래선 수정 기능 추가로 페이지 분기. isModify를 Y로 세팅하면 거래선 수정 화면이 호출 2012-05-15 */			
			if("Y".equals(form.getIsModify())){
				forwardURL = "/WEB-INF/jsp/clm/draft/Customer_u.jsp";
			}
			else{
				forwardURL = "/WEB-INF/jsp/clm/draft/Customer1_p.jsp";
			}
			
			srch_gerp = StringUtil.bvl(form.getSrch_gerp(), ""); 	 // gerp코드
			srch_custmor = StringUtil.bvl(form.getSrch_customer(), "");  // 업체명
			srch_nation  = StringUtil.bvl(form.getNation2(), "");        // 국가 코드
			srch_taxNo   = StringUtil.bvl(form.getSrch_taxNo(), "");     // 사업자번호
			
			vo.setSrch_gerp(srch_gerp);
			vo.setSrch_customer(srch_custmor.toUpperCase());
			vo.setSrch_nation(srch_nation);
			vo.setSrch_taxNo(srch_taxNo);
			
			form.setSelectCnt(StringUtil.bvl(form.getSelectCnt(), "0"));
			form.setSelectRow(StringUtil.bvl(form.getSelectRow(), "0"));
			
			/*********************************************************
			 * 페이지 객체
			**********************************************************/
			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			
			List customerList = null;
			
			vo.setSrch_nation(srch_nation);
			// 2014-09-23 Kevin added.
			vo.setSrch_vendor_type("");
			if(request.getParameter("srch_vendor_type") != null && request.getParameter("srch_vendor_type") != ""){
				vo.setSrch_vendor_type(request.getParameter("srch_vendor_type"));
			}
			
			customerList = customerTestService.listCustomerTest(vo);
			
			if (customerList != null && customerList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)customerList.get(0);

				pageUtil.setTotalRow(((Integer)lom.get("total_cnt")));
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();
			}
			
			ModelAndView mav = new ModelAndView();
			if(!"search".equals(form.getPage_gbn())){
				if(srch_nation.equals("")){
					List nation = customerTestService.getNationList(vo);
					
					if (nation != null && nation.size() > 0) {
						ListOrderedMap lom = (ListOrderedMap)nation.get(0);
					
						form.setNation2(StringUtil.bvl(lom.get("cd"),""));
						form.setSrch_nation(StringUtil.bvl(lom.get("cd_nm"),""));
					}
				}
			}
			mav.setViewName(forwardURL);
			mav.addObject("customerList", customerList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("command", form);
			// 2014-09-24 Kevin added.
			mav.addObject("srch_vendor_type", vo.getSrch_vendor_type());
			
			return mav;
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Error");
		}catch(Throwable t){
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	* 계약상대방 검색테스트 화면 호출
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listCustomerTest1(HttpServletRequest request, HttpServletResponse response)throws Exception{
		try{
			String forwardURL = "/WEB-INF/jsp/clm/draft/CustomerTest_p.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			CustomerTestForm form = new CustomerTestForm();
			CustomerTestVO vo = new CustomerTestVO();
			
			bind(request, form);
			bind(request, vo);
	
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			form.setSrch_customer(StringUtil.bvl(form.getSrch_customer(), ""));//업체명
			vo.setSrch_customer(StringUtil.bvl(form.getSrch_customer(), ""));
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			return mav;
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Error");
		}catch(Throwable t){
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	* 계약상대방 검색 목록 - 계약지식 공유 화면
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listCustomerShare(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String forwardURL = "/WEB-INF/jsp/clm/share/CustomerView_l.jsp";
			String srch_customer = "";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			CustomerTestForm form = new CustomerTestForm();
			CustomerTestVO vo = new CustomerTestVO();
			
			bind(request, form);
			bind(request, vo);
	
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setSrch_customer(StringUtil.bvl(form.getSrch_customer(), ""));
			form.setSelectCnt(StringUtil.bvl(form.getSelectCnt(), "0"));
			form.setSelectRow(StringUtil.bvl(form.getSelectRow(), "0"));
			
			/*********************************************************
			 * 페이지 객체
			**********************************************************/
			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			
			srch_customer = form.getSrch_customer();
			
			vo.setSrch_customer(StringUtil.bvl(form.getSrch_customer(), "").toUpperCase());
			
			List customerList = null;
			
			if( srch_customer != null && srch_customer != ""){//null이 아닐 경우만.
				customerList = customerTestService.listCustomerTest(vo);
			} else {
				
			}
			
			if (customerList != null && customerList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)customerList.get(0);

				pageUtil.setTotalRow(((Integer)lom.get("total_cnt")).intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();
			}
			
			
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("customerList", customerList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("command", form);
			
			return mav;
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Error");
		}catch(Throwable t){
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	* 계약상대방 검색 목록 - 계약지식 공유 화면
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView detailCustomerShare(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String forwardURL   = "/WEB-INF/jsp/clm/share/CustomerView_d.jsp";
			String sCustomer_cd = "";  // 검색엔진에서 받기 위한 값입니다.
			String sDodun       = "";  // 검색 엔진에서 받기 위한 값입니다.
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			CustomerTestForm form = new CustomerTestForm();
			CustomerTestVO vo = new CustomerTestVO();
			
			bind(request, form);
			bind(request, vo);
	
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			form.setSrch_customer(StringUtil.bvl(form.getSrch_customer(), ""));//업체명
			vo.setSrch_customer(StringUtil.bvl(form.getSrch_customer(), ""));
			form.setSelectCnt(StringUtil.bvl(form.getSelectCnt(), "0"));
			form.setSelectRow(StringUtil.bvl(form.getSelectRow(), "0"));
			
			sCustomer_cd = StringUtil.bvl((String)request.getParameter("customer_cd"),"");
			sDodun       = StringUtil.bvl((String)request.getParameter("dodun"),"");
			
			vo.setCustomer_cd(sCustomer_cd);
			vo.setDodun(sDodun);
			
			List customerList = null;
			if(!"".equals(form.getSrch_customer())){//null이 아닐 경우만.
				customerList = customerTestService.detailCustomerShare(vo);
			} else if(!"".equals(sCustomer_cd) && !"".equals(sDodun)){
				customerList = customerTestService.detailCustomerShare(vo);
			} 
			
			ListOrderedMap lom = null;
			if (customerList != null && customerList.size() > 0) {
				lom = (ListOrderedMap)customerList.get(0);
			}
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("lom", lom);

			return mav;
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Error");
		}catch(Throwable t){
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	public ModelAndView modifyCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			
			int result = 0;
			String forwardURL = "/clm/draft/customerTest.do?method=listCustomerTest";
			String returnMessage     = "";	 // 리턴메시지
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			CustomerTestForm form = new CustomerTestForm();
			CustomerTestVO vo = new CustomerTestVO();
			
			bind(request, form);
			bind(request, vo);
	
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			
			
			result = customerTestService.modifyCustomer(vo);
			
			if(result < 0){
				throw new Exception();
			}
			else{
				//메시지 처리 - 수정되었습니다.
				returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());
//				form.setReturn_message((String)messageSource.getMessage("clm.page.field.customerTest.modifyCustomer01", null, new RequestContext(request).getLocale()));
			}
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			return mav;
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Error");
		}catch(Throwable t){
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	* RegistrationNo 수정
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public void modifyRegistrationNo(HttpServletRequest request, HttpServletResponse response)throws Exception{
		try{
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			CustomerTestForm form = new CustomerTestForm();
			CustomerTestVO vo = new CustomerTestVO();
			
			bind(request, form);
			bind(request, vo);
							
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			
			
			List result = customerTestService.checkRegistrationNo(vo);
			String message ="";
			String modResult ="";
			int update = 0;
			
			// Registration No 중복 체크제거
			update = customerTestService.updateRegistrationNo(vo);
			if(update>0){
				modResult = "Y";
			}
			
			// Registration No 중복 체크
//			if (result != null && result.size() > 0){
//				modResult = "N";
//				message = messageSource.getMessage("las.page.field.contractManager.alreadyRegistration", null, new RequestContext(request).getLocale());
//			} else {				
//				update = customerTestService.updateRegistrationNo(vo);
//				if(update>0){
//					modResult = "Y";
//				}
//			}
			
			JSONObject jo = new JSONObject();
    		jo.put("message", message);			
    		jo.put("result", modResult);
//			jo.put("message",requiredLom.get("message"));
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
    		
		} catch (Exception e) {
			JSONObject jo 		= new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out 	= response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
			JSONObject jo 		= new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out 	= response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		}
	}

	/**
	* 계약상대방 검색 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception              
	*/
	public ModelAndView listTop30Customer(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String forwardURL    = "";
			String srch_custmor  = "";
			String srch_nation   = "";        // 국가 코드
			String srch_taxNo    = "";        // 사업자번호
			String srch_gerp    = "";        // gerp코드
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			CustomerTestForm form = new CustomerTestForm();
			CustomerTestVO vo = new CustomerTestVO();
			
			bind(request, form);
			bind(request, vo);
	
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			forwardURL = "/WEB-INF/jsp/clm/draft/Top30Customer_p.jsp";
			
			
			
			/*********************************************************
			 * 페이지 객체
			**********************************************************/
			PageUtil pageUtil = new PageUtil();
			
			List customerList = null;
			
			
			customerList = customerTestService.listTop30Customer(vo);
			
			if (customerList != null && customerList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)customerList.get(0);

				pageUtil.setTotalRow(((Integer)lom.get("total_cnt")));
			}
						
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("customerList", customerList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("command", form);
			return mav;
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Error");
		}catch(Throwable t){
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	/**
	* overTop30Cus 체크
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public void overTop30Cus(HttpServletRequest request, HttpServletResponse response)throws Exception{
		try{
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			CustomerTestForm form = new CustomerTestForm();
			CustomerTestVO vo = new CustomerTestVO();
			
			bind(request, form);
			bind(request, vo);
							
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			
			
			List result = customerTestService.overTop30Cus(vo);
			String message ="";
			String modResult ="";

			if(result!=null){
				ListOrderedMap lom = (ListOrderedMap)result.get(0);
				
				if((Integer)lom.get("CNT")>29){
					modResult="Y";
					message = messageSource.getMessage("las.page.field.contractManager.more30customer", null, new RequestContext(request).getLocale());
				}else{
					modResult="N";
				}
			}


			
			JSONObject jo = new JSONObject();
    		jo.put("message", message);			
    		jo.put("result", modResult);
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
    		
		} catch (Exception e) {
			JSONObject jo 		= new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out 	= response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
			JSONObject jo 		= new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out 	= response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		}
	}
	
	/**
	* checkGerpCd 체크
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public void checkGerpCd(HttpServletRequest request, HttpServletResponse response)throws Exception{
		try{
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			CustomerTestForm form = new CustomerTestForm();
			CustomerTestVO vo = new CustomerTestVO();
			
			bind(request, form);
			bind(request, vo);
							
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			List result = customerTestService.checkGerpCd(vo);
			String message ="";
			String modResult ="";

			if(result!=null){
				ListOrderedMap lom = (ListOrderedMap)result.get(0);
				
				if((Integer)lom.get("CNT")>0){
					modResult="Y";
				}else{
					modResult="N";
				}
			}

			
			JSONObject jo = new JSONObject();
    		jo.put("message", message);			
    		jo.put("result", modResult);
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
    		
		} catch (Exception e) {
			JSONObject jo 		= new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out 	= response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
			JSONObject jo 		= new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out 	= response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		}
	}
	
	
	/**
	* checkGerpCus 체크
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public void checkGerpCus(HttpServletRequest request, HttpServletResponse response)throws Exception{
		try{
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			CustomerTestForm form = new CustomerTestForm();
			CustomerTestVO vo = new CustomerTestVO();
			
			bind(request, form);
			bind(request, vo);
							
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			List result = customerTestService.checkGerpCus(vo);
			String message ="";
			String modResult ="";
			String customer_cd="";

			if(result!=null){
				ListOrderedMap lom = (ListOrderedMap)result.get(0);
				
				if((Integer)lom.get("CNT")>0){
					modResult="Y";
					customer_cd=(String)lom.get("customer_cd");
				}else{
					modResult="N";
				}
			}

			
			JSONObject jo = new JSONObject();
    		jo.put("message", message);			
    		jo.put("result", modResult);
    		jo.put("customer_cd", customer_cd);
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
    		
		} catch (Exception e) {
			JSONObject jo 		= new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out 	= response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
			JSONObject jo 		= new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out 	= response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		}
	}
	
}
