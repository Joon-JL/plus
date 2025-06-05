/**
* Project Name : 계약관리시스템
* File Name : DecideArbitrarilyReController.java
* Description : 전결규정 Controller
* Author : dawn
* Date : 2011.09.26
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/

package com.sec.clm.admin.control;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.admin.dto.DecideArbitrarilyReForm;
import com.sec.clm.admin.dto.DecideArbitrarilyReVO;
import com.sec.clm.admin.service.DecideArbitrarilyReService;

public class DecideArbitrarilyReController extends CommonController{
	
	private DecideArbitrarilyReService decideArbitrarilyReService;
	public void setDecideArbitrarilyReService(DecideArbitrarilyReService decideArbitrarilyReService) {
		this.decideArbitrarilyReService = decideArbitrarilyReService;
	}
	
	/**
	* 전결규정 목록
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView listDecideArbitrarilyRe(HttpServletRequest request
			                                  , HttpServletResponse response)throws Exception{
		try{
			String forwardURL = "/WEB-INF/jsp/clm/admin/DecideArbitrarilyRe_l.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			DecideArbitrarilyReForm form = new DecideArbitrarilyReForm();
			DecideArbitrarilyReVO vo = new DecideArbitrarilyReVO();
	
			bind(request, form);
			bind(request, vo);
	
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			form.setSrch_operdiv_cd(StringUtil.bvl(form.getSrch_operdiv_cd(), ""));
			form.setSrch_biz_clsfcn(StringUtil.bvl(form.getSrch_biz_clsfcn(), ""));
			
			vo.setSrch_operdiv_cd(StringUtil.bvl(form.getSrch_operdiv_cd(), ""));
			vo.setSrch_biz_clsfcn(StringUtil.bvl(form.getSrch_biz_clsfcn(), ""));
			
			/*********************************************************
			 * 페이지 객체
			**********************************************************/
			PageUtil pageUtil = new PageUtil();

			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));

			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());
			
			String returnMessage = "";
			
			//전결규정 목록화면의 사업부 selectbox
			List operdivCdList = decideArbitrarilyReService.operdivCdList();
			//전결규정 목록화면의 비즈니스 단계 selectbox
			List bizClsfcnList = decideArbitrarilyReService.bizClsfcnList();
			//사용자 권한조회
			ListOrderedMap roleCd = decideArbitrarilyReService.roleCdDecideArbitrarilyRe(vo);
			//조회
			List resultList = decideArbitrarilyReService.listDecideArbitrarilyRe(vo);
			
			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(((BigDecimal)lom.get("total_cnt")).intValue());
				pageUtil.setRowPerPage(10);
				pageUtil.setGroup();

				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}
			}else {
				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				}
			}
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("operdivCdList", operdivCdList);
			mav.addObject("bizClsfcnList", bizClsfcnList);
			mav.addObject("roleCd", roleCd);
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
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
	* 전결규정 등록화면 호출
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardInsertDecideArbitrarilyRe(HttpServletRequest request
			                                           , HttpServletResponse response) throws Exception{
		try{
			String forwardURL = "/WEB-INF/jsp/clm/admin/DecideArbitrarilyRe_i.jsp";
	
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			DecideArbitrarilyReForm form = new DecideArbitrarilyReForm();
			DecideArbitrarilyReVO vo = new DecideArbitrarilyReVO();
	
			bind(request, form);
			bind(request, vo);
	
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//전결규정 등록화면의 사업부 selectbox
			List operdivCdList = decideArbitrarilyReService.operdivCdList();
			//전결규정 등록화면의 비즈니스 단계 selectbox
			List bizClsfcnList = decideArbitrarilyReService.bizClsfcnList();
			//전결규정 등록화면의 전결권자 selectbox
			List dcdAuthmanList = decideArbitrarilyReService.dcdAuthmanList();
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("operdivCdList", operdivCdList);
			mav.addObject("bizClsfcnList", bizClsfcnList);
			mav.addObject("dcdAuthmanList", dcdAuthmanList);
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
	* 전결규정 등록화면
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView insertDecideArbitrarilyRe(HttpServletRequest request
			                                    , HttpServletResponse response) throws Exception{
		try{
			String forwardURL= "/clm/admin/decideArbitrarilyRe.do?method=listDecideArbitrarilyRe";//목록페이지로 이동.
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			DecideArbitrarilyReForm form = new DecideArbitrarilyReForm();
			DecideArbitrarilyReVO vo = new DecideArbitrarilyReVO();
	
			bind(request, form);
			bind(request, vo);
	
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setReg_id(form.getSession_user_id());
			vo.setReg_nm(form.getSession_user_nm());
			
			int result = decideArbitrarilyReService.insertDecideArbitrarilyRe(vo);
			
			String returnMessage = messageSource.getMessage("secfw.msg.succ.insert", null, new RequestContext(request).getLocale());
			
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
	* 전결규정 상세화면
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView detailDecideArbitrarilyRe(HttpServletRequest request
			                                    , HttpServletResponse response) throws Exception{
		try{
			String forwardURL = "/WEB-INF/jsp/clm/admin/DecideArbitrarilyRe_d.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			DecideArbitrarilyReForm form = new DecideArbitrarilyReForm();
			DecideArbitrarilyReVO vo = new DecideArbitrarilyReVO();
	
			bind(request, form);
			bind(request, vo);
	
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//사용자 권한조회
			ListOrderedMap roleCd = decideArbitrarilyReService.roleCdDecideArbitrarilyRe(vo);
			//조회
			ListOrderedMap lom = decideArbitrarilyReService.detailDecideArbitrarilyRe(vo);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("roleCd", roleCd);
			mav.addObject("lom", lom);
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
	* 전결규정 수정화면 호출.
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView forwardModifyDecideArbitrarilyRe(HttpServletRequest request
			                                           , HttpServletResponse response) throws Exception{
		try{
			String forwardURL = "/WEB-INF/jsp/clm/admin/DecideArbitrarilyRe_u.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			DecideArbitrarilyReForm form = new DecideArbitrarilyReForm();
			DecideArbitrarilyReVO vo = new DecideArbitrarilyReVO();
	
			bind(request, form);
			bind(request, vo);
	
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//전결규정 등록화면의 사업부 selectbox
			List operdivCdList = decideArbitrarilyReService.operdivCdList();
			//전결규정 등록화면의 비즈니스 단계 selectbox
			List bizClsfcnList = decideArbitrarilyReService.bizClsfcnList();
			//전결규정 등록화면의 전결권자 selectbox
			List dcdAuthmanList = decideArbitrarilyReService.dcdAuthmanList();
			//조회
			ListOrderedMap lom = decideArbitrarilyReService.detailDecideArbitrarilyRe(vo);
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName(forwardURL);
			mav.addObject("lom", lom);
			mav.addObject("operdivCdList", operdivCdList);
			mav.addObject("bizClsfcnList", bizClsfcnList);
			mav.addObject("dcdAuthmanList", dcdAuthmanList);
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
	* 전결규정 수정화면
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView ModifyDecideArbitrarilyRe(HttpServletRequest request
                                                , HttpServletResponse response) throws Exception{
		try{
			String forwardURL= "/clm/admin/decideArbitrarilyRe.do?method=detailDecideArbitrarilyRe";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			DecideArbitrarilyReForm form = new DecideArbitrarilyReForm();
			DecideArbitrarilyReVO vo = new DecideArbitrarilyReVO();
	
			bind(request, form);
			bind(request, vo);
	
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setMod_id(form.getSession_user_id());
			vo.setMod_nm(form.getSession_user_nm());
			
			int result = decideArbitrarilyReService.modefyDecideArbitrarilyRe(vo);
			
			String returnMessage = messageSource.getMessage("secfw.msg.succ.modify", null, new RequestContext(request).getLocale());
			
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
	* 전결규정 삭제
	* 
	* @param request
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView deleteDecideArbitrarilyRe(HttpServletRequest request
                                                , HttpServletResponse response) throws Exception{
		try{
			String forwardURL= "/clm/admin/decideArbitrarilyRe.do?method=listDecideArbitrarilyRe";
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			DecideArbitrarilyReForm form = new DecideArbitrarilyReForm();
			DecideArbitrarilyReVO vo = new DecideArbitrarilyReVO();
	
			bind(request, form);
			bind(request, vo);
	
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			vo.setDel_id(form.getSession_user_id());
			vo.setDel_nm(form.getSession_user_nm());
			
			int result = decideArbitrarilyReService.deleteDecideArbitrarilyRe(vo);
			
			String returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());
			
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
	
}
