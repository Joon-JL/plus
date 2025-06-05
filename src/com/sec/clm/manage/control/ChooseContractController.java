/**
* Project Name : 계약관리시스템
* File Name : ChooseContractController.java
* Description : 계약선택팝업Controller
* Author : 심주완
* Date : 2011.09.25
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.clm.manage.control;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.StringUtil;

import com.sec.clm.manage.dto.ChooseContractForm;
import com.sec.clm.manage.dto.ChooseContractVO;
import com.sec.clm.manage.dto.ConsultationVO;

import com.sec.clm.manage.service.ChooseContractService;
import com.sds.secframework.common.control.CommonController;

public class ChooseContractController extends CommonController {
	private ChooseContractService chooseContractService;
	public void setChooseContractService(ChooseContractService chooseContractService) {
		this.chooseContractService = chooseContractService;
	}
	
	/**
	* 계약팝업
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	//================심주완 작업영역 시작=======================================
	//===================2011.08.31 심주완추가  계약선택 팝업페이지호출=========================
	public ModelAndView forwardChooseContractPopup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			String forwardURL 		= "/WEB-INF/jsp/clm/manage/ChooseContract_p.jsp";
			String returnMessage 	= "";
			List resultList			= null;
			ChooseContractForm form 	= new ChooseContractForm();		
			ChooseContractVO vo 		= new ChooseContractVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			resultList = chooseContractService.listChooseContract(vo);
			
			if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
				returnMessage = (String)request.getAttribute("returnMessage");
			}
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +forwardURL);
			mav.addObject("resultList", resultList);
			mav.addObject("command", form);
			
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
		}
	}
	
	public ModelAndView modifyChooseContract(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			String forwardURL 		= "/clm/manage/consultation.do?method=makeApprovalHtml";
			int result				= 0;
			ChooseContractForm form = new ChooseContractForm();		
			ChooseContractVO vo 	= new ChooseContractVO();	
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			if(vo.getCntrt_id_arr() != null && vo.getCntrt_id_arr().length > 0) {
				result = chooseContractService.modifyChooseContract(vo);
			}
			String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +forwardURL);
			mav.addObject("command", form);
			
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
	 * 체결품의상신 ValidationCheck
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void listApprovalValidation(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		try {
			
	        HttpSession session = request.getSession(false);
	        
	        /*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			ChooseContractVO vo = new ChooseContractVO();
			bind(request, vo);
			
			/*********************************************************
			 * 계약유효성정보조회
			**********************************************************/
			
			List resultList = chooseContractService.listApprovalValidation(vo);
			JSONObject jo = new JSONObject();
    		StringBuffer sbCntrtNm = new StringBuffer();
    		StringBuffer sbCntrtId = new StringBuffer();
    		String wsvo_cnt = "";
    		
    		int k = 0;
			for(int i=0; i < resultList.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)resultList.get(i);
				if((Integer)lom.get("null_cnt") > 0 || (Integer)lom.get("exec_cnt") == 0) {
					for(int j=0; j < vo.getCntrt_id_arr().length;j++) {
						if("Y".equals(vo.getApproval_yn_arr()[j]) && vo.getCntrt_id_arr()[j].equals((String)lom.get("cntrt_id"))) {
							if(k==0) {
								sbCntrtNm.append((String)lom.get("cntrt_title"));
								sbCntrtId.append((String)lom.get("cntrt_id"));
							} else {
								sbCntrtNm.append(", ");
								sbCntrtNm.append((String)lom.get("cntrt_title"));
								sbCntrtId.append(", ");
								sbCntrtId.append((String)lom.get("cntrt_id"));
							}
							k++;
						}	
					}
				}
				wsvo_cnt = (String)lom.get("wsvo_cnt");
			}
			
			if(sbCntrtNm == null || sbCntrtNm.length()== 0) {
				jo.put("cntrtNm", "");
				jo.put("cntrtId", "");
				jo.put("wsvoCnt", wsvo_cnt);
			} else {
				jo.put("cntrtNm", sbCntrtNm.toString());
				jo.put("cntrtId", sbCntrtId.toString());
				jo.put("wsvoCnt", wsvo_cnt);
			}
			
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jo = new JSONObject();
    		jo.put("errorMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
    		jo.put("errorMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} 
	}	
}	