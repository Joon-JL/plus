/**
* Project Name : 계약관리시스템
* File Name : CustomerController.java
* Description : 거래상대방 검색 팝업 Controller
* Author : dawn
* Date : 2011.09.29
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.draft.control;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.draft.dto.CustomerForm;
import com.sec.clm.draft.dto.CustomerVO;
import com.sec.clm.draft.service.CustomerService;

public class CustomerController extends CommonController {
	private CustomerService customerService;
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	/**
	* 거래상대방 검색 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			String forwardURL = "/WEB-INF/jsp/clm/draft/Customer_p.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			CustomerForm form = new CustomerForm();
			CustomerVO vo = new CustomerVO();
			
			bind(request, form);
			bind(request, vo);
	
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			form.setSrch_customer(StringUtil.bvl(form.getSrch_customer(), ""));
			vo.setSrch_customer(StringUtil.bvl(form.getSrch_customer(), ""));
			
			/*********************************************************
			 * 페이지 객체
			**********************************************************/
			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			
			List customerList = customerService.listCustomer(vo);
			
			if (customerList != null && customerList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)customerList.get(0);

				pageUtil.setTotalRow(((Integer)lom.get("total_cnt")).intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();
			}
			
			List selectList = new ArrayList(); 
			
			if(form.getCustomer_cds() != null && form.getCustomer_cds().length > 0){
				for(int i=0; i<form.getCustomer_cds().length;i++){
					ListOrderedMap select = new ListOrderedMap();
					select.put("customer_nm1",  form.getCustomer_nm1s()[i]);
					select.put("rep_nm",        form.getRep_nms()[i]);
					select.put("industry_type", form.getIndustry_types()[i]);
					select.put("street",        form.getStreets()[i]);
					select.put("tax_nm1",       form.getTax_nm1s()[i]);
					
					select.put("customer_cd",   form.getCustomer_cds()[i]);
					select.put("gubn_cd",       form.getGubn_cds()[i]);
					select.put("account_cd",    form.getAccount_cds()[i]);
					select.put("customer_nm2",  form.getCustomer_nm2s()[i]);
					select.put("iv_nm1",        form.getIv_nm1s()[i]);
					select.put("iv_nm2",        form.getIv_nm2s()[i]);
					select.put("city",          form.getCitys()[i]);
					select.put("district",      form.getDistricts()[i]);
					select.put("iv_street",     form.getIv_streets()[i]);
					select.put("iv_city",       form.getIv_citys()[i]);
					select.put("iv_district",   form.getIv_districts()[i]);
					select.put("nation",        form.getNations()[i]);
					select.put("region",        form.getRegions()[i]);
					select.put("postalcode",    form.getPostalcodes()[i]);
					select.put("telephone1",    form.getTelephone1s()[i]);
					select.put("email",         form.getEmails()[i]);
					select.put("fax",           form.getFaxs()[i]);
					select.put("url",           form.getUrls()[i]);
					select.put("business_type", form.getBusiness_types()[i]);
					select.put("tax_nm2",       form.getTax_nm2s()[i]);
					select.put("tax_nm3",       form.getTax_nm3s()[i]);
					select.put("tax_nm4",       form.getTax_nm4s()[i]);
					
					selectList.add(select);
				}
			}
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("customerList", customerList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("command", form);
			mav.addObject("selectList", selectList);
			return mav;
			
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Error");
		}catch(Throwable t){
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
}
