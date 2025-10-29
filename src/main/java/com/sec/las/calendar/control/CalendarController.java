/**
 * Project Name : 법무시스템 구주총괄
 * File name	: CalendarController.java
 * Description	: 법무시스템 캘린더 CONTROLLER
 * Author		: 박병주
 * Date			: 2013. 10
 * Copyright 	: 2013 by SAMSUNG. All rights reserved.
 */
package com.sec.las.calendar.control;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.ObjectCopyUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.las.calendar.dto.CalForm;
import com.sec.las.calendar.dto.CalVO;
//import anyframe.common.util.DateUtil;
import com.sec.las.calendar.service.CalendarService;

public class CalendarController extends CommonController {	
	
	/**
	 * 서비스 세팅
	 */
	private CalendarService calendarService;
	public void setCalendarService(CalendarService calendarService) {
		this.calendarService = calendarService;
	}
	
	/**
	* 법무 캘린더 ( 초기화면 / 이전 월 / 다음 월 )
	*
	* @param request
	* @param response
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView lasCalendar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {		
		
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
 			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			String forwardURL = "/WEB-INF/jsp/las/calendar/LasCalendar.jsp";			
			String returnMessage = "";
			
			ModelAndView mav = new ModelAndView() ;
			
			CalVO vo = new CalVO();		
			CalForm form = new CalForm();		
			
			bind(request, vo) ;
			bind(request, form) ;
			
			 //기본정보 세팅(시스템정보, 사용자정보)
			setCommonInfo(vo, request) ;
			setCommonInfo(form, request);			
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//XSS 처리
			setXSSFORM(form);
			setXSSVO(vo, session);		
			
			// 월별 탭 활성화 처리
			form.setTab_mode(StringUtil.bvl((String)form.getTab_mode(),"tab_month"));			
			vo.setTab_mode(StringUtil.bvl((String)form.getTab_mode(),"tab_month"));
			
			String today  = DateUtil.today();

			// 초기 검색 날짜 세팅
			if(form.getSrch_yyyymm() == null || form.getSrch_yyyymm().equals(""))
			{
				String temp = DateUtil.month();
				int i = 0;
				
				// jsp 화면의 javascript 에서 월 계산시 실제 월 -1로 계산 (1월 = 0)
				i = Integer.valueOf(temp) - 1;
				temp = Integer.toString(i);
				
				form.setSrch_year(DateUtil.year());
				vo.setSrch_year(DateUtil.year());
				
				form.setSrch_month(temp);
				vo.setSrch_month(temp);
				
				vo.setSrch_yyyymmdd(today.substring(0, 6) + "01");
			} else {
				vo.setSrch_yyyymmdd(form.getSrch_yyyymm() + "01");
			}
			
			/*********************************************************
			 * Service
			**********************************************************/
			List resultList = calendarService.lasCalendar(vo);	
		
			/*********************************************************
			 * MAV 세팅
			**********************************************************/			
			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
			mav.addObject("command", form) ;
			
			return mav;

		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception(t);
		}
	}
	
	/**
	* 법무 캘린더 ( 초기화면 / 이전 월 / 다음 월 ) 메인 화면 전용 AJAX
	*
	* @param request
	* @param response
	* @return ModelAndView
	* @throws Exception
	*/
	public void lasCalendarAJAX(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {		
			HttpSession session = request.getSession(false);
 			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			CalVO vo = new CalVO();		
			CalForm form = new CalForm();		
			
			bind(request, vo) ;
			bind(request, form) ;
			
			 //기본정보 세팅(시스템정보, 사용자정보)
			setCommonInfo(vo, request) ;
			setCommonInfo(form, request);			
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//XSS 처리
			setXSSFORM(form);
			setXSSVO(vo, session);		
			
			// 월별 탭 활성화 처리
			//form.setTab_mode(StringUtil.bvl((String)form.getTab_mode(),"tab_month"));			
			vo.setTab_mode(StringUtil.bvl((String)form.getTab_mode(),"tab_month"));
			
			String today  = DateUtil.today();

			// 초기 검색 날짜 세팅
			if(form.getSrch_yyyymm() == null || form.getSrch_yyyymm().equals(""))
			{
				String temp = DateUtil.month();
				int i = 0;
				
				// jsp 화면의 javascript 에서 월 계산시 실제 월 -1로 계산 (1월 = 0)
				i = Integer.valueOf(temp) - 1;
				temp = Integer.toString(i);
				
				form.setSrch_year(DateUtil.year());
				vo.setSrch_year(DateUtil.year());
				
				form.setSrch_month(temp);
				vo.setSrch_month(temp);
				
				vo.setSrch_yyyymmdd(today.substring(0, 6) + "01");
			} else {
				vo.setSrch_yyyymmdd(form.getSrch_yyyymm() + "01");
			}			

			/*********************************************************
			 * Service
			**********************************************************/
			List al = calendarService.lasCalendar(vo);	
		
			/*********************************************************
			 * AJAX 세팅
			**********************************************************/			
			JSONArray jsonArray = new JSONArray();
						
			if(al!=null && al.size()>0) {	
				
				for(int i=0; i<al.size(); i++) {
					ListOrderedMap lom = (ListOrderedMap)al.get(i);

					JSONObject jsonObject = new JSONObject();
					
					jsonObject.put("START_DT", (String)lom.get("START_DT"));
					jsonObject.put("PLN_SUM", (Integer)lom.get("PLN_SUM"));
					jsonObject.put("EXE_SUM", (Integer)lom.get("EXE_SUM"));
					jsonObject.put("CONC_SUM", (Integer)lom.get("CONC_SUM"));					

					jsonArray.add(jsonObject);
				}
			}
			
			response.setContentType("application/json; charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.print(jsonArray);
		    out.flush();
		    out.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error");
//			JSONObject jo = new JSONObject();
//    		jo.put("returnMessage", messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
//    		jo.put("returnCnt", 0);
//
//    		response.setContentType("application/json; charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.print(jo);
//			out.flush();
//			out.close();
		} catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("Error");
//			response.setContentType("application/json; charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.print(messageSource.getMessage("secfw.msg.error.error",  null, new RequestContext(request).getLocale()));
//			out.flush();
//			out.close();
		}
	}
	
	/**
	* 법무 캘린더 ( 일별 )
	*
	* @param request
	* @param response
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView lasCalendarDay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {		
		
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
 			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			String forwardURL = "/WEB-INF/jsp/las/calendar/LasCalendar.jsp";			
			String returnMessage = "";
			
			ModelAndView mav = new ModelAndView() ;
			
			CalVO vo = new CalVO();		
			CalForm form = new CalForm();		
			
			bind(request, vo) ;
			bind(request, form) ;
			
			 //기본정보 세팅(시스템정보, 사용자정보)
			setCommonInfo(vo, request) ;
			setCommonInfo(form, request);			
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			setXSSFORM(form);
			setXSSVO(vo, session);				
			
			// 월별 탭 활성화 처리
			form.setTab_mode(StringUtil.bvl((String)form.getTab_mode(),"tab_day"));
			vo.setTab_mode(StringUtil.bvl((String)vo.getTab_mode(),"tab_day"));
			
			String setDay = StringUtil.bvl((String)form.getSrch_yyyymmdd2(),DateUtil.today()); 
			
			form.setSrch_yyyymmdd2(setDay);
			vo.setSrch_yyyymmdd2(setDay);
			
			/*********************************************************
			 * Service
			**********************************************************/
			List dayList = calendarService.lasCalendar(vo);	
		
			/*********************************************************
			 * MAV 세팅
			**********************************************************/			
			mav.setViewName(forwardURL);
			mav.addObject("dayList", dayList);
			mav.addObject("command", form) ;	
			
			return mav;

		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception(t);
		}
	}
	
	/**
	* 법무 캘린더 ( 주별 )
	*
	* @param request
	* @param response
	* @return ModelAndView
	* @throws Exception
	*/
	public ModelAndView lasCalendarWeek(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {		
		
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
 			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			String forwardURL = "/WEB-INF/jsp/las/calendar/LasCalendar.jsp";			
			String returnMessage = "";
			
			ModelAndView mav = new ModelAndView() ;
			
			CalVO vo = new CalVO();		
			CalForm form = new CalForm();		
			
			bind(request, vo) ;
			bind(request, form) ;
			
			 //기본정보 세팅(시스템정보, 사용자정보)
			setCommonInfo(vo, request) ;
			setCommonInfo(form, request);			
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//XSS 처리
			setXSSFORM(form);
			setXSSVO(vo, session);		
			
			// 월별 탭 활성화 처리
			form.setTab_mode(StringUtil.bvl((String)form.getTab_mode(),"tab_week"));
			vo.setTab_mode(StringUtil.bvl((String)vo.getTab_mode(),"tab_week"));
			
			String setDay = StringUtil.bvl((String)form.getSrch_yyyymmdd(),DateUtil.today()); 
			
			form.setSrch_yyyymmdd(setDay);
			vo.setSrch_yyyymmdd(setDay);
			
			/*********************************************************
			 * Service
			**********************************************************/
			List weekList = calendarService.getWeekDays(vo);	
			List resultList = calendarService.lasCalendar(vo);		
			
			// 해당주차의 7일의 날짜를 루프 시키면서 해당 날짜의 스케줄 데이타가 존재 할 경우 MAP 에 삽입 후 , 이 MAP 을 다시 LIST 에 삽입한다.
			// 완성된 해당 날짜의 데이타 LIST 를 해당날짜의 LOM1 에 다시 삽입하는 것으로 마무리.
			for(int i = 0;i < weekList.size();i++){

				ListOrderedMap lom1 = (ListOrderedMap)weekList.get(i);
				
				ArrayList<HashMap<String, String>> skdlList = new ArrayList<HashMap<String, String>>();		
				
				for(int j = 0;j < resultList.size();j++){					

					ListOrderedMap lom2 = (ListOrderedMap)resultList.get(j);
					
					String lom1_adate = (String)lom1.get("ADATE");
					
					if(lom2.get("SEQNO")!=null){					
					
						String lom2_adate = (String)lom2.get("ADATE");					
						
						if(lom1_adate.equals(lom2_adate)){				
							
							HashMap<String, String> skdlMap = new HashMap<String, String>();
							
							skdlMap.put("ADATE", (String)lom2.get("ADATE"));								
							skdlMap.put("SEQNO", (String)lom2.get("SEQNO"));
							skdlMap.put("TITLE", (String)lom2.get("TITLE"));
							skdlMap.put("CONT", (String)lom2.get("CONT"));	
							skdlMap.put("LOCA", (String)lom2.get("LOCA"));
							skdlMap.put("START_DT", (String)lom2.get("START_DT"));
							skdlMap.put("END_DT", (String)lom2.get("END_DT"));
							skdlMap.put("S_TYPE", (String)lom2.get("S_TYPE"));	
							
							skdlList.add(skdlMap);
						}					
					}							
				}
				
				lom1.put("CONTLIST", skdlList);
			}
		
			/*********************************************************
			 * MAV 세팅
			**********************************************************/			
			mav.setViewName(forwardURL);
			mav.addObject("weekList", weekList);
			mav.addObject("resultList", resultList);
			mav.addObject("command", form) ;	
			
			return mav;

		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception(t);
		}
	}
	
	/**
	 * 개인 일정 등록 [팝업]
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView popInsertSKDL(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    	= "";
			String returnMessage 	= "";

			List   		resultList 	= null;
			ListOrderedMap lom	= new ListOrderedMap();
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/las/calendar/LasCalendar_i.jsp";			
			
			/*********************************************************
			 * Form & VO Binding
			**********************************************************/	
			CalVO vo = new CalVO();		
			CalForm form = new CalForm();		
			
			bind(request, vo) ;
			bind(request, form) ;
			
			 //기본정보 세팅(시스템정보, 사용자정보)
			setCommonInfo(vo, request) ;
			setCommonInfo(form, request);			
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//XSS 처리
			setXSSFORM(form);
			setXSSVO(vo, session);		
			
			String write_mode = form.getWrite_mode();
			
			/*********************************************************
			 * Service
			**********************************************************/
			ModelAndView mav = new ModelAndView();
			
			if("MODIFY".equals(form.getWrite_mode())){
				
				resultList = calendarService.getCalSKDL(vo);
				
				if(resultList != null && resultList.size() > 0){
					lom = (ListOrderedMap)resultList.get(0);
				}
			}
				
			mav.setViewName(forwardURL);

			//mav.addObject("resultList", resultList);
			mav.addObject("write_mode", write_mode);	
			mav.addObject("lom", lom);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);
			
			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("TypeController.listTypeItem() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("TypeController.listTypeItem() Throwable !!");
		}
	}
	
	/**
	 * 개인 일정 등록 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView saveCalSKDL(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {			
			HttpSession session = request.getSession(false);
			
			String forwardURL  = "/WEB-INF/jsp/las/calendar/LasCalendar_i.jsp";	
			
			CalForm form = new CalForm();
			CalVO vo =	new CalVO();	

			bind(request, form);
			bind(request, vo);
			
			setCommonInfo(vo,request);
			setCommonInfo(form,request);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//XSS 처리
			setXSSFORM(form);
			setXSSVO(vo, session);		
			
			ModelAndView mav = new ModelAndView();
			
			// 초기 등록 인지 수정인지 구분값 세팅
			vo.setWrite_mode(form.getWrite_mode());
			
			int save_cnt = 0;			
			save_cnt = calendarService.saveCalSKDL(vo);	

			String returnMessage  = "";

			if(save_cnt > 0){
				returnMessage = messageSource.getMessage("secfw.msg.succ.save", null, new RequestContext(request).getLocale());
			} else {
				returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale())
						+ messageSource.getMessage("secfw.msg.error.ask", null, new RequestContext(request).getLocale());
			}	
			
			form.setSrch_yyyymmdd2(vo.getStart_dt());
			
			mav.setViewName(forwardURL);
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
	 * 개인 일정 삭제
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 */
	public ModelAndView delCalSKDL(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {			
			HttpSession session = request.getSession(false);
			
			String forwardURL  = "/WEB-INF/jsp/las/calendar/LasCalendar_i.jsp";	
			
			CalForm form = new CalForm();
			CalVO vo =	new CalVO();	

			bind(request, form);
			bind(request, vo);
			
			setCommonInfo(vo,request);
			setCommonInfo(form,request);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			//XSS 처리
			setXSSFORM(form);
			setXSSVO(vo, session);		
			
			ModelAndView mav = new ModelAndView();
			
			// 초기 등록 인지 수정인지 구분값 세팅
			vo.setWrite_mode(form.getWrite_mode());
			
			int save_cnt = 0;			
			save_cnt = calendarService.delCalSKDL(vo);	

			String returnMessage  = "";

			if(save_cnt > 0){
				returnMessage = messageSource.getMessage("secfw.msg.succ.delete", null, new RequestContext(request).getLocale());
			} else {
				returnMessage = messageSource.getMessage("secfw.msg.error.error", null, new RequestContext(request).getLocale())
						+ messageSource.getMessage("secfw.msg.error.ask", null, new RequestContext(request).getLocale());
			}	
			
			form.setSrch_yyyymmdd2(vo.getStart_dt());
			
			mav.setViewName(forwardURL);
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
	 * CalForm XSS 처리
	 * @param CalForm
	 * @return CalForm
	 * @throws Exception
	 */
	private CalForm setXSSFORM(CalForm form) throws Exception{
		
		form.setSrch_year(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_year(),"")));
		form.setSrch_month(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_month(),"")));
		form.setSrch_yyyymm(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_yyyymm(),"")));
		form.setSrch_yyyymmdd(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_yyyymmdd(),"").replace("-", "")));
		form.setSrch_yyyymmdd2(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_yyyymmdd2(),"").replace("-", "")));
		form.setSrch_mode(StringUtil.convertHtmlTochars(StringUtil.bvl(form.getSrch_mode(),"")));	

		return form;
	}
	
	/**
	 * CalVO XSS 처리
	 * @param PMPJTVO
	 * @return PMPJTVO
	 * @throws Exception
	 */
	private CalVO setXSSVO(CalVO vo, HttpSession session) throws Exception{		
		
		String sysCd = propertyService.getProperty("secfw.sysCd");			
		
		ObjectCopyUtil.setFieldValue(vo, "sys_cd", sysCd, String.class) ;
		ObjectCopyUtil.setFieldValue(vo, "lang_select_cd", (String)session.getAttribute("secfw.session.lang_select_cd"), String.class) ;
		ObjectCopyUtil.setFieldValue(vo, "session_language_flag", (String)session.getAttribute("secfw.session.language_flag"), String.class) ;
		ObjectCopyUtil.setFieldValue(vo, "session_dept_cd", (String)session.getAttribute("secfw.session.dept_cd"), String.class) ;	
		
		vo.setSrch_year(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_year(),"")));
		vo.setSrch_month(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_month(),"")));
		vo.setSrch_yyyymm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_yyyymm(),"")));
		vo.setSrch_yyyymmdd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_yyyymmdd(),"").replace("-", "")));
		vo.setSrch_yyyymmdd2(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_yyyymmdd2(),"").replace("-", "")));
		vo.setSrch_mode(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_mode(),"").replace("-", "")));
		
		vo.setTitle(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getTitle(),"")));
		vo.setLoca(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getLoca(),"")));
		vo.setCont(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCont(),"")));
		
		return vo;
	}

}
