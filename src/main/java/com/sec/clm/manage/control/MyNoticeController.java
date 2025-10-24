/**
 * Project Name : 계약관리시스템
 * File name	: MyNoticeController.java
 * Description	: 계약관리 > MyNotice
 * Author		: 유영섭
 * Date			: 2011. 10-14
 * Copyright	:
 */


package com.sec.clm.manage.control;

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

import com.sec.clm.manage.dto.MyNoticeForm;
import com.sec.clm.manage.dto.MyNoticeVO;
import com.sec.clm.manage.service.MyNoticeService;
import com.sec.common.util.ClmsDataUtil;
import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.util.service.ComUtilService;

public class MyNoticeController extends CommonController {
	/**
	 * MyNoticeController 서비스
	 */
	private MyNoticeService myNoticeService;
	
	public void setMyNoticeService(MyNoticeService myNoticeService) {
		this.myNoticeService = myNoticeService;
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
	 * 계약 관리 > MyNotice
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	
	public ModelAndView listMyNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ClmsDataUtil.debug("==============================================MyNotice===");
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL = "";
			String returnMessage = "";
			
			MyNoticeForm form = new MyNoticeForm();
			MyNoticeVO vo = new MyNoticeVO();
			PageUtil 	pageUtil   = null;
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
			
			forwardURL = "/WEB-INF/jsp/clm/manage/MyNotice_l.jsp";	
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			form = new MyNoticeForm();
			vo =  new MyNoticeVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//데이터 초기화 해주기 
		    form.setSrch_start_reg_dt(StringUtil.bvl(form.getSrch_start_reg_dt(), ""));
		    vo.setSrch_start_reg_dt(StringUtil.bvl(vo.getSrch_start_reg_dt(), ""));
		    form.setSrch_end_reg_dt(StringUtil.bvl(form.getSrch_end_reg_dt(), ""));
		    vo.setSrch_end_reg_dt(StringUtil.bvl(vo.getSrch_end_reg_dt(), ""));
		    form.setSrch_name(StringUtil.bvl(form.getSrch_name(), ""));
		    vo.setSrch_name(form.getSrch_name());
		    form.setSrch_stat(StringUtil.bvl(form.getSrch_stat(), ""));
		    vo.setSrch_stat(form.getSrch_stat());
		    form.setSrch_keyword_mailtitle(StringUtil.bvl(form.getSrch_keyword_mailtitle(),""));
		    vo.setSrch_keyword_mailtitle(form.getSrch_keyword_mailtitle());
			/*********************************************************
			 * Page  setting
			**********************************************************/
		
			pageUtil = new PageUtil();
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(), "1")));

			vo.setStart_index(pageUtil.getStartIndex());  //현재 페이지의 첫번째 게시물번호  set
			vo.setEnd_index(pageUtil.getEndIndex());   //현재 페이지의 마지막 게시물번호  set
			

			/*********************************************************
			 * Service
			**********************************************************/
			resultList = myNoticeService.listMyNotice(vo);  
			  

			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap) resultList.get(0);
			
				pageUtil.setTotalRow((Integer.parseInt(lom.get("total_cnt").toString())));
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

			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
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

	public ModelAndView detailMyNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			

			/*********************************************************
			 * Parameter
		    **********************************************************/
			String forwardURL = "";
			String returnMessage = "";

			MyNoticeForm form = new MyNoticeForm();
			MyNoticeVO vo = new MyNoticeVO();
			List resultList = null;
	
			ListOrderedMap lom = new ListOrderedMap();
			ListOrderedMap lomRe = new ListOrderedMap();       
			
			/*********************************************************
			 * Session Check
			 **********************************************************/
			HttpSession session = request.getSession(false);
			
			if (session == null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			 **********************************************************/
			
			forwardURL = "/WEB-INF/jsp/clm/manage/MyNotice_d.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			
			form = new MyNoticeForm();
			vo = new MyNoticeVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			// forward request 속성 값 가져오기...
			if (request.getAttribute("command") != null) {
				MyNoticeForm command = (MyNoticeForm)request.getAttribute("command");
				
				form.setMis_id(command.getMis_id());
				vo.setMis_id(command.getMis_id());
				
				form.setModule_id(command.getModule_id());
				vo.setModule_id(command.getModule_id());
				
			}
			/*********************************************************
			 * Service
			 **********************************************************/
			resultList = myNoticeService.detailMyNotice(vo);
			
			List listRe = myNoticeService.listRecman(vo);    //수신인리스트
			if (resultList != null && resultList.size() > 0) {
				lom = (ListOrderedMap) resultList.get(0);
			}
			
			/*수신인의 이름들을 붙여서 표시하도록 변경함*/
			StringBuffer sbRe = new StringBuffer();
			sbRe.append("");
			if (listRe != null && listRe.size() > 0) {
				for(int i=0; i < listRe.size(); i++) {
					ListOrderedMap lomReTemp	= (ListOrderedMap)listRe.get(i);
					
					//i 의 값이 3,5,7 ... 일때 이름앞에 <br>태그를 붙임
					if(i==0)
						sbRe.append(lomReTemp.get("user_nm"));
					else if( i>0 && (i%2 == 0))
						sbRe.append("<br>").append(lomReTemp.get("user_nm"));
					else
						sbRe.append(",  ").append(lomReTemp.get("user_nm"));
				}
			}
			/*********************************************************
			 * Massage
			 **********************************************************/
			returnMessage = StringUtil.bvl((String) request.getAttribute("returnMessage"), "");
			/*********************************************************
			 * ModelAndView
			 **********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("resultList", resultList);
		    mav.addObject("sRe", sbRe.toString()); //수신인리스트
			mav.addObject("lom", lom);  
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("MyNoticeController.detailMyNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("MyNoticeController.detailMyNotice() Throwable !!");
		}
	}
	/**
	 * 수신자 리스트 
	 * @param vo
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList listRecman(MyNoticeVO vo) throws Exception {
		try {
			/*********************************************************
			 * Parameter 
			**********************************************************/
			List   	   resultList = null;
			
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = myNoticeService.listRecman(vo);
			
			ArrayList lom = (ArrayList)resultList;
			
			if(resultList==null)
				throw new Exception("##### queryService is null ##### ");
			
			return lom;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ConsiderationController.listRelationman() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("ConsiderationController.listRelationman() Throwable !!");
		}
	}
	
	/**
	 * 계약관리 메인 - my notice 상세 팝업 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView detailMyNoticeByMain(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			

			/*********************************************************
			 * Parameter
		    **********************************************************/
			String forwardURL = "";
			String returnMessage = "";

			MyNoticeForm form = new MyNoticeForm();
			MyNoticeVO vo = new MyNoticeVO();
			List resultList = null;
	
			ListOrderedMap lom = new ListOrderedMap();
			ListOrderedMap lomRe = new ListOrderedMap();       
			
			/*********************************************************
			 * Session Check
			 **********************************************************/
			HttpSession session = request.getSession(false);
			
			if (session == null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			 **********************************************************/
			
			forwardURL = "/WEB-INF/jsp/clm/manage/MyNotice_p.jsp";
			
			/*********************************************************
			 * Form 및 VO Binding
			 **********************************************************/
			
			form = new MyNoticeForm();
			vo = new MyNoticeVO();
			
			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			// forward request 속성 값 가져오기...
			if (request.getAttribute("command") != null) {
				MyNoticeForm command = (MyNoticeForm)request.getAttribute("command");
				
				form.setMis_id(command.getMis_id());
				vo.setMis_id(command.getMis_id());
				
				form.setModule_id(command.getModule_id());
				vo.setModule_id(command.getModule_id());
			}
			/*********************************************************
			 * Service
			 **********************************************************/
			resultList = myNoticeService.detailMyNotice(vo);
			
			List listRe = myNoticeService.listRecman(vo);    //수신인리스트
			if (resultList != null && resultList.size() > 0) {
				lom = (ListOrderedMap) resultList.get(0);
			}
			 
			/*수신인의 이름들을 붙여서 표시하도록 변경함*/
			StringBuffer sbRe = new StringBuffer();
			sbRe.append("");
			if (listRe != null && listRe.size() > 0) {
				for(int i=0; i < listRe.size(); i++) {
					ListOrderedMap lomReTemp	= (ListOrderedMap)listRe.get(i);
					
					//i 의 값이 3,5,7 ... 일때 이름앞에 <br>태그를 붙임
					if(i==0)
						sbRe.append(lomReTemp.get("user_nm"));
					else if( i>0 && (i%2 == 0))
						sbRe.append("<br>").append(lomReTemp.get("user_nm"));
					else
						sbRe.append(",  ").append(lomReTemp.get("user_nm"));
				}
			}
			
			/*********************************************************
			 * Massage
			 **********************************************************/
			returnMessage = StringUtil.bvl((String) request.getAttribute("returnMessage"), "");
			/*********************************************************
			 * ModelAndView
			 **********************************************************/
			ModelAndView mav = new ModelAndView();

			mav.setViewName(forwardURL);
			this.getLogger().debug("forwardURL: " +mav.getViewName());
			
			mav.addObject("resultList", resultList);
			mav.addObject("sRe", sbRe.toString()); //수신인리스트
			mav.addObject("lom", lom);  
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
			return mav;
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("MyNoticeController.detailMyNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("MyNoticeController.detailMyNotice() Throwable !!");
		}
	}	

}