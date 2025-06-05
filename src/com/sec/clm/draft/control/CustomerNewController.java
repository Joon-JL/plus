/**
* Project Name : 계약관리시스템
* File Name : CustomerNewController.java
* Description : 계약상대방 신규등록 팝업 Controller
* Author : dawn
* Date : 2011.10.21
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.draft.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import net.sf.json.JSONObject;
import java.io.PrintWriter;
import org.springframework.web.servlet.support.RequestContext;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.db2.jcc.t4.v;
import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.draft.dto.CustomerNewForm;
import com.sec.clm.draft.dto.CustomerNewVO;
import com.sec.clm.draft.service.CustomerNewService;

public class CustomerNewController extends CommonController {
	private CustomerNewService customerNewService;
	
	public void setCustomerNewService(CustomerNewService customerNewService) {
		this.customerNewService = customerNewService;
	}
	
	
	/**
	* 계약상대방 신규등록 호출
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listCustomerNew(HttpServletRequest request, HttpServletResponse response)throws Exception {
		try{
			String forwardURL = "/WEB-INF/jsp/clm/draft/CustomerNew_p.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			CustomerNewForm form = new CustomerNewForm();
			CustomerNewVO vo = new CustomerNewVO();
			
			bind(request, form);
			bind(request, vo);
	
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
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
	* 계약상대방 신규등록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView insertCustomer(HttpServletRequest request, HttpServletResponse response)throws Exception{
		try{
			String forwardURL = "/WEB-INF/jsp/clm/draft/CustomerNew_p.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			CustomerNewForm form = new CustomerNewForm();
			CustomerNewVO vo = new CustomerNewVO();
			
			bind(request, form);
			bind(request, vo);
	
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			if("".equals(StringUtil.bvl(form.getCustomer_nm1(),""))){
				form.setSrch_customer(form.getCustomer_nm1_H());
				vo.setCustomer_nm1(form.getCustomer_nm1_H());
				vo.setLinbu(form.getLinbu_H());
			} else {
				form.setSrch_customer(form.getCustomer_nm1());
			}
			
			
			form.setSrch_customer(StringUtil.bvl(form.getSrch_customer(), ""));//업체명
			vo.setSrch_customer(StringUtil.bvl(form.getSrch_customer(), ""));
			
			vo.setReg_id(form.getSession_user_id());
			vo.setReg_nm(form.getSession_user_nm());
			vo.setNationRd(form.getNationRd());//국내
			
			
			
			int result = customerNewService.insertCustomer(vo);
			
			if(result > 0){
				form.setReturn_message("1");
			} else {
				form.setReturn_message("2");
			}
			
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
	* 계약상대방 신규등록 시 중복 체크
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView checkTexNo(HttpServletRequest request, HttpServletResponse response)throws Exception{
		try{
			String forwardURL = "/WEB-INF/jsp/clm/draft/CustomerNew_p.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			CustomerNewForm form = new CustomerNewForm();
			CustomerNewVO vo = new CustomerNewVO();
			
			bind(request, form);
			bind(request, vo);
	
			if("Y".equals(form.getIsModify())){
				forwardURL = "/clm/draft/customerTest.do?method=listCustomerTest";
			}
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			form.setSrch_customer(form.getCustomer_nm1());
			
			form.setSrch_customer(StringUtil.bvl(form.getSrch_customer(), ""));//업체명
			vo.setSrch_customer(StringUtil.bvl(form.getSrch_customer(), ""));
			
			vo.setReg_id(form.getSession_user_id());
			vo.setReg_nm(form.getSession_user_nm());
			vo.setNationRd(form.getNationRd());//국내
			
			List result = customerNewService.checkTexNo(vo);
			
			if (result != null && result.size() > 0){
				
				ListOrderedMap lom = (ListOrderedMap)result.get(0);
			
				form.setCheck_texNm((String)lom.get("CUSTOMER_NM1"));
				form.setCheck_texNo((String)lom.get("TAX_NO"));
				form.setCheck_YN("P");
					
			} else {
				
			    form.setCheck_texNm("");
		 	    form.setCheck_texNo("");
				form.setCheck_YN("P");
			}
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("check", form);
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
	* 계약상대방 신규등록 시 중복 체크
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public void jocheckTexNo(HttpServletRequest request, HttpServletResponse response)throws Exception{
		try{
			String forwardURL = "/WEB-INF/jsp/clm/draft/CustomerNew_p.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			CustomerNewForm form = new CustomerNewForm();
			CustomerNewVO vo = new CustomerNewVO();
			
			bind(request, form);
			bind(request, vo);
	
			if("Y".equals(form.getIsModify())){
				forwardURL = "/clm/draft/customerTest.do?method=listCustomerTest";
			}
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			form.setSrch_customer(form.getCustomer_nm1());
			
			form.setSrch_customer(StringUtil.bvl(form.getSrch_customer(), ""));//업체명
			vo.setSrch_customer(StringUtil.bvl(form.getSrch_customer(), ""));
			
			vo.setReg_id(form.getSession_user_id());
			vo.setReg_nm(form.getSession_user_nm());
			vo.setNationRd(form.getNationRd());//국내
			
			String check_yn = "P";
			String check_texNm = "";
			String check_texNo = "";
			
			List result = customerNewService.checkTexNo(vo);
			
			if (result != null && result.size() > 0){
				
				ListOrderedMap lom = (ListOrderedMap)result.get(0);
			
				check_texNm = (String)lom.get("CUSTOMER_NM1");
				check_texNo = (String)lom.get("TAX_NO");
					
			} else {
				
				check_texNm = "";
				check_texNo = "";
			}
			
			JSONObject jo = new JSONObject();
    		jo.put("check_YN", check_yn);
    		jo.put("check_texNm", check_texNm);
    		jo.put("check_texNo", check_texNo);
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
			
		}catch(Exception e){
			
			JSONObject jo 		= new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out 	= response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		}catch(Throwable t){
			
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
