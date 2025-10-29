/**
* Project Name : 계약관리시스템
* File Name : FaqController.java
* Description : FAQ Controller
* Author : 신남원
* Date : 2010.09.05
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.counsel.control;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.admin.dto.FaqForm;
import com.sec.clm.admin.dto.FaqVO;
import com.sec.clm.admin.service.FaqService;

public class FaqController extends CommonController {
	private FaqService faqService;
	public void setFaqService(FaqService faqService) {
		this.faqService = faqService;
	}

	/**
	* FAQ 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listFaq(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			FaqForm cmd = (FaqForm)request.getAttribute("command");
			
			/*********************************************************
			 * Forwarding URL
			**********************************************************/			
			String forwardURL = "/WEB-INF/jsp/clm/counsel/Faq_l.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			FaqForm form = new FaqForm();
			FaqVO vo = new FaqVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			if(cmd != null){
				form = cmd;
				form.setSeqno(0);
				vo.setSeqno(0);
			}
			
			//데이터 초기화 해주기
			form.setSrch_cont_type(StringUtil.bvl(form.getSrch_cont_type(),""));
			vo.setSrch_cont_type(form.getSrch_cont_type());
			form.setSrch_keyword(StringUtil.bvl(form.getSrch_keyword(), ""));
			vo.setSrch_keyword(form.getSrch_keyword());
			form.setSrch_keyword_content(StringUtil.bvl(form.getSrch_keyword_content(), ""));
			vo.setSrch_keyword_content(form.getSrch_keyword_content());
			
			vo.setAnnce_gbn("C01302");
			form.setMode("M");
			vo.setMode("M");
			/*********************************************************
			 * 페이지 객체
			**********************************************************/
			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());

	        /*********************************************************
			 * JSP 반환변수
			**********************************************************/
			String returnMessage = "";

			/*********************************************************
			 * 검색처리
			**********************************************************/
			//XSS 처리
			vo.setSrch_keyword_content(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_keyword_content(), "").toUpperCase()));
			
			List resultList = faqService.listCounselFaq(vo);

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(((Integer)lom.get("total_cnt")).intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();
			}
			
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

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
	 * FAQ 조회수 증가
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *  ,Object command, BindException errors
	 */
	public void incRdcnt(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			FaqForm form = new FaqForm();
			FaqVO vo = new FaqVO();
			
			bind(request, form);
			bind(request, vo);

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
//			getLogger().error(">>>>>>>>>>>>"+vo.getSeqno());
			int result = faqService.incRdcntFaq(vo);
			
			JSONObject jo = new JSONObject();
			
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
			
		} catch (Exception e) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
			response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} catch (Throwable t) {
			JSONObject jo = new JSONObject();
    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
    		
    		response.setContentType("application/json; charset=UTF-8");
    		PrintWriter out = response.getWriter();
    		out.print(jo);
    		out.flush();
    		out.close();
		} 
		
    }	
	
}