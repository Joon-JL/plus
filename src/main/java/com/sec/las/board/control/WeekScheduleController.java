/**
* Project Name : 법무시스템 이식
* File Name : WeekScheduleController.java
* Description : 일정관리 Controller
* Author : 
* Date : 2010.09
* Copyright : 2011 by SAMSUNG. All rights reserved.
*/
package com.sec.las.board.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.sds.secframework.common.control.CommonController;
import com.sds.secframework.common.util.COMUtil;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.ExcelBuilder;
import com.sds.secframework.common.util.PageUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sec.las.board.dto.WeekScheduleForm;
import com.sec.las.board.dto.WeekScheduleVO;
import com.sec.las.board.service.WeekScheduleService;
 
/**
 * Description	: 일정관리 Controller
 * Author		: 
 * Date			: 2011. 09
 */
public class WeekScheduleController extends CommonController {
	
	/**
	 * WeekSchedule 서비스
	 */
	private WeekScheduleService weekScheduleService = null;
	
	/**
	 * WeekSchedule 서비스 세팅
	 * @param weekScheduleService
	 * @return void
	 */
	public void setWeekScheduleService(WeekScheduleService weekScheduleService) {
		this.weekScheduleService = weekScheduleService;
	}

	/**
	* WeekSchedule 목록 조회
	* 
	* @param request, response
	* @return ModelAndView
	* @throws Exception
	 */
	public ModelAndView listWeekSchedule(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{

			/*********************************************************
			 * Parameter
			**********************************************************/	
			String forwardURL = "";
			String returnMessage = "";
			
			List resultList = null;
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);		
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter Setting
			**********************************************************/	
			forwardURL = "/WEB-INF/jsp/las/board/WeekSchedule_l.jsp";

			/*********************************************************
			 * Form & VO Binding
			**********************************************************/
			WeekScheduleForm form = new WeekScheduleForm();
			WeekScheduleVO vo = new WeekScheduleVO();
			
			bind(request, form);
			bind(request, vo);		

			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);	
			
			if(form.getYear() == null){				
				form.setYear(DateUtil.year());				
			}
			if(form.getWeekseq() == null){			
				form.setWeekseq(DateUtil.getWeekOfYear(new Date()));
			}
			
			if("1".equals(form.getWeekseq())){
				form.setWeekFirstDay(form.getYear()+"-01-01");
			} else{
				form.setWeekFirstDay(DateUtil.getFirstDayOfWeek(form.getYear(), form.getWeekseq()));			
			}
			if("53".equals(form.getWeekseq())){
				form.setWeekLastDay(form.getYear()+"-12-31");
			} else{
				form.setWeekLastDay(DateUtil.getLastDayOfWeek(form.getYear(), form.getWeekseq()));
			}
			
			vo.setYear(StringUtil.bvl(form.getYear(),"error"));
			vo.setWeekseq(StringUtil.bvl(form.getWeekseq(),"error"));			

			
			/*********************************************************
			 * Page setting
			**********************************************************/	
			PageUtil pageUtil = new PageUtil();
			pageUtil.setThisPage(Integer.parseInt(StringUtil.bvl(form.getCurPage(),"1")));
			vo.setStart_index(pageUtil.getStartIndex());
			vo.setEnd_index(pageUtil.getEndIndex());	
			
			/*********************************************************
			 * Service
			**********************************************************/	
			resultList = weekScheduleService.listWeekSchedule(vo);	
			
			if (resultList != null && resultList.size() > 0) {
				ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

				pageUtil.setTotalRow(Integer.parseInt(String.valueOf(lom.get("total_cnt"))));
				pageUtil.setRowPerPage(50);
				pageUtil.setGroup();

				// 메시지처리 - 정상적으로 조회되었습니다.
				if((String)request.getAttribute("returnMessage") != null && ((String)request.getAttribute("returnMessage")).length() > 1 ) {
					returnMessage = (String)request.getAttribute("returnMessage");
				} else {
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

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			/*********************************************************
             * 권한 처리
            **********************************************************/ 
			String accessLevel = weekScheduleService.checkAuthByRole(vo);
			
			if ("A".equals(accessLevel)){
				form.setAuth_modify(true);
			}
			mav.setViewName(forwardURL);
			
			mav.addObject("lom", lom);
			mav.addObject("resultList", resultList);
			mav.addObject("pageUtil", pageUtil);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("WeekScheduleController.listWeekSchedule() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("WeekScheduleController.listWeekSchedule() Throwable !!");
		}
	}
	
	
	/**
	 * 주간업무 작성 및 등록/수정 폼 표시 
	 * @param request, response
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardInsertWeekSchedule(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL = "";
			List resultList = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);

			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/las/board/WeekSchedule_i.jsp";   //주간 업무 작성 및 수정 

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/	
			WeekScheduleForm form = new WeekScheduleForm();
			WeekScheduleVO vo = new WeekScheduleVO();
			
			bind(request, form);
			bind(request, vo);
			
			// 기본정보 세팅(시스템정보, 사용자정보)
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			form.setPath_gbn("Menu");
			vo.setUser_id(form.getUser_id());

			/*********************************************************
			 * Service
			**********************************************************/
			resultList = weekScheduleService.detailWeekSchedule(vo);

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();	
			
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			form.setCrntweek_rslt(StringUtil.replace((String)lom.get("crntweek_rslt"),"<BR>","\r\n"));
			form.setNextweek_plan(StringUtil.replace((String)lom.get("nextweek_plan"),"<BR>","\r\n"));


			/*********************************************************
             * 권한 처리
            **********************************************************/ 
			String accessLevel = weekScheduleService.checkAuthByRole(vo);
			
			mav.setViewName(forwardURL);
			mav.addObject("lom", lom);
			mav.addObject("resultList", resultList);	
			mav.addObject("command", form);

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
	 * 주간업무 작성 및 등록/수정 폼 표시 - 메인화면 호출
	 * @param request, response
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public ModelAndView forwardInsertWeekScheduleByMain(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL = "";
			List resultList = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);

			if(session==null)
				throw new Exception("##### Session is null #####");
			
			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/las/board/WeekSchedule_i.jsp";   //주간 업무 작성 및 수정 

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/	
			WeekScheduleForm form = new WeekScheduleForm();
			WeekScheduleVO vo = new WeekScheduleVO();
			
			bind(request, form);
			bind(request, vo);
			
			// 기본정보 세팅(시스템정보, 사용자정보)
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			form.setPath_gbn("Main");
			vo.setUser_id(vo.getSession_user_id());
			
			if(form.getUser_id()==null){
				// 메인화면에서 초기 로딩시 기본값 Setting	
				form.setYear(DateUtil.year());
				form.setWeekseq(DateUtil.getWeekOfYear(new Date()));
				form.setUser_id(vo.getSession_user_id());
				form.setWeekFirstDay(DateUtil.getFirstDayOfWeek(form.getYear(), form.getWeekseq()));			
				form.setWeekLastDay(DateUtil.getLastDayOfWeek(form.getYear(), form.getWeekseq()));
				
				vo.setYear(DateUtil.year());		
				vo.setWeekseq(DateUtil.getWeekOfYear(new Date()));		
			}
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = weekScheduleService.detailWeekSchedule(vo);

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();	
			
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
			form.setCrntweek_rslt(StringUtil.replace((String)lom.get("crntweek_rslt"),"<BR>","\r\n"));
			form.setNextweek_plan(StringUtil.replace((String)lom.get("nextweek_plan"),"<BR>","\r\n"));


			/*********************************************************
             * 권한 처리
            **********************************************************/ 
			String accessLevel = weekScheduleService.checkAuthByRole(vo);
			
			mav.setViewName(forwardURL);
			mav.addObject("lom", lom);
			mav.addObject("resultList", resultList);	
			mav.addObject("command", form);

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
	 * 주간 업무 작성 (등록 /수정)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView insertWeekSchedule(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {

			WeekScheduleForm cmd = (WeekScheduleForm)request.getAttribute("command");
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			List resultList = null;
			int result = 0 ;		
			
			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);
			
			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			WeekScheduleForm form = new WeekScheduleForm();
			WeekScheduleVO vo = new WeekScheduleVO();
			
			bind(request, form) ;
			bind(request, vo) ;		

			if(cmd != null){
				form = cmd;
				vo.setUser_id(form.getUser_id());
				vo.setUser_nm(form.getUser_nm());
			}
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			if ("Main".equals(form.getPath_gbn())){
				forwardURL = "/las/board/weekSchedule.do?method=forwardInsertWeekScheduleByMain";
			} else{
				forwardURL = "/las/board/weekSchedule.do?method=forwardInsertWeekSchedule";
			}
				
			/*********************************************************
			 * Service
			**********************************************************/
			resultList = weekScheduleService.getWeekSchedule(vo);					
		    
			if(resultList.size() > 0 ){
				result = weekScheduleService.modifyWeekSchedule(vo);				
			}else{
				result = weekScheduleService.insertWeekSchedule(vo);
			}				

			if(result==1){
				returnMessage = messageSource.getMessage("secfw.msg.succ.save", null, new RequestContext(request).getLocale()) ;					
			}

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();

			/*********************************************************
             * 권한 처리
            **********************************************************/ 
			String accessLevel = weekScheduleService.checkAuthByRole(vo);
			
			mav.setViewName(forwardURL);
			mav.addObject("command", form);
			mav.addObject("returnMessage", returnMessage);

			return mav;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.insertNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.insertNotice() Throwable !!");
		}

	}	
	/**
	 * 주간 업무 상세 보기  
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView detailWeekSchedule(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
			/*********************************************************
			 * Parameter 
			**********************************************************/
			String forwardURL    = "";
			String returnMessage = "";
			
			List resultList = null;
			ListOrderedMap lom = null;

			/*********************************************************
			 * Session Check
			**********************************************************/	
			HttpSession session = request.getSession(false);

			if(session==null)
				throw new Exception("##### Session is null #####");

			/*********************************************************
			 * Parameter setting
			**********************************************************/
			forwardURL = "/WEB-INF/jsp/las/board/WeekSchedule_i.jsp";

			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
			WeekScheduleForm form = new WeekScheduleForm();
			WeekScheduleVO vo = new WeekScheduleVO();

			bind(request, form);
			bind(request, vo);
			
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);		
			
			if(form.getYear() == null){				
				form.setYear(DateUtil.year());				
			}
			if(form.getWeekseq() == null){			
				form.setWeekseq(DateUtil.getWeekOfYear(new Date()));
			}
			
			form.setWeekFirstDay(DateUtil.getFirstDayOfWeek(form.getYear(), form.getWeekseq()));			
			form.setWeekLastDay(DateUtil.getLastDayOfWeek(form.getYear(), form.getWeekseq()));
			
			vo.setYear(StringUtil.bvl(form.getYear(),"error"));
			vo.setWeekseq(StringUtil.bvl(form.getWeekseq(),"error"));
			vo.setWeekFirstDay(StringUtil.bvl(form.getWeekFirstDay(),"error"));
			vo.setWeekLastDay(StringUtil.bvl(form.getWeekLastDay(),"error"));

			/*********************************************************
			 * Service
			**********************************************************/
			resultList = weekScheduleService.detailWeekSchedule(vo);

			if(resultList.size() > 0){
				lom = (ListOrderedMap)resultList.get(0);
				form.setCrntweek_rslt(StringUtil.replace((String)lom.get("crntweek_rslt"),"<BR>","\r\n"));
				form.setNextweek_plan(StringUtil.replace((String)lom.get("nextweek_plan"),"<BR>","\r\n"));
			}

			/*********************************************************
			 * Massage
			**********************************************************/
			returnMessage = StringUtil.bvl((String)request.getAttribute("returnMessage"), "");

			/*********************************************************
			 * ModelAndView
			**********************************************************/
			ModelAndView mav = new ModelAndView();	

			/*********************************************************
             * 권한 처리
            **********************************************************/ 
			String accessLevel = weekScheduleService.checkAuthByRole(vo);
			
			mav.setViewName(forwardURL);
			mav.addObject("resultList", resultList);
			mav.addObject("lom", lom);
			mav.addObject("command", form);
			
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
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void excelDownLoad(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			ListOrderedMap lom = null;
			
			/*********************************************************
			 * Form 및 VO Binding
			**********************************************************/
	        WeekScheduleForm form = new WeekScheduleForm();
	        WeekScheduleVO   vo   = new WeekScheduleVO();
			bind(request, form);
			bind(request, vo);
			COMUtil.getUserAuditInfo(request, form);
			COMUtil.getUserAuditInfo(request, vo);
			
			/*********************************************************
			 * 목록조회
			**********************************************************/
	        List resultList = weekScheduleService.excelDown(vo); 
	        
	        // 엑셀출력시 <BR>제거
	        for(int i=0; i<resultList.size(); i++){
				lom = (ListOrderedMap)resultList.get(i);
		        lom.put("crntweek_rslt", StringUtil.replace((String)lom.get("crntweek_rslt"),"<BR>","\r\n"));
		        lom.put("nextweek_plan", StringUtil.replace((String)lom.get("nextweek_plan"),"<BR>","\r\n"));
	        }
			
			/*********************************************************
			 * Excel DownLoad
			**********************************************************/
	        String ExelNm  = request.getParameter("exel_nm");
	        String ExelVel = request.getParameter("exel_vel");
	        String fileNm  = "schedule_" + DateUtil.today() + ".xls";
			String strTitleInfo = vo.getYear()+"년 "+vo.getWeekseq()+"주차 ( "+form.getWeekFirstDay()+" ~ "+form.getWeekLastDay()+") ";
			String[] sheetNmAry = new String[] {"schedule"+DateUtil.today()};			
			
			String[] titleInfo   = new String[] {strTitleInfo};		//ExelNm.split(",");
			String[] subTitleInfo  = new String[]{"이름","금주일정","차주일정"};		//ExelNm.split(","); 
			String[] columnInfo  = new String[] {"user_nm","crntweek_rslt","nextweek_plan"};//ExelVel.split(",");
			short[]  columnAlign = new short[] {ExcelBuilder.ALIGN_CENTER,ExcelBuilder.ALIGN_LEFT,ExcelBuilder.ALIGN_LEFT};
	        			
	        ExcelBuilder excel = new ExcelBuilder(sheetNmAry);
            // 두개의 Sheet를 생성한다.
			int sheetsCount = excel.getSheetsCount();                                // Sheet의 갯수			

			for(int i=0;i<sheetsCount;i++) {                                         		// Sheet의 수 만큼 Loop				
				
				/*excel.setFontName	("맑은 고딕");                                         		// Row의 모든 Cell의 Font명을 굴림체로 설정한다.
				excel.setFontSize	((short)11); 
				if (i == 0) {
					excel.addTitleRow(i, titleInfo);                                 		// 타이틀을 설정한다.
				}	
				excel.setAlign		(ExcelBuilder.ALIGN_CENTER);                           	// Row의 모든 Cell을 Center 정렬한다.
				excel.setBold		(true);                                                 // Row의 모든 Cell을 Bold로 설정한다.
				excel.setFontColor	(ExcelBuilder.COLOR_BLACK);                        		// Row의 모든 Cell의 글자색을 White로 설정한다.				
				excel.setBgColor	(ExcelBuilder.COLOR_YELLOW);                     	// Row의 모든 Cell의 배경색을 부드러운 Blue로 설정한다.
				excel.setBorder		(true); 
				// Row의 모든 Cell의 테두리를 설정한다.
				excel.addRow(i, subTitleInfo);	
				excel.setBold		(false);  
				excel.setBgColor	(ExcelBuilder.COLOR_WHITE);                     	// Row의 모든 Cell의 배경색을 부드러운 Blue로 설정한다.                                            		
				if (i == 0) {
					excel.setAlign(columnAlign);                                     		// 셀 정렬
				}
				excel.setVAlign(ExcelBuilder.VALIGN_CENTER);                         		// Row의 모든 Cell을 Vertical Center 정렬한다.
				excel.setBorder(new boolean[] {true});                               		// Row의 모든 Cell의 테두리를 설정한다. (cause. 배열의 크기가 1)				
				if (i == 0) {
					excel.addRows(i, columnInfo, resultList);                        		// 데이타 엑셀에 박기										
				}
				excel.setDefaultStyle();                                             		// Row의 모든 Style을 기본값으로 복원한다.
				*/
				
				excel.setBold(true);
				excel.setFontColor(ExcelBuilder.COLOR_BLACK);
				excel.setFontName("맑은 고딕");
				excel.setFontSize((short)11);
				excel.setBgColor(ExcelBuilder.COLOR_YELLOW);
				excel.setBorder(true);
				
				excel.addTitleRow(i, subTitleInfo);
				excel.setDefaultStyle();
				
				excel.setAlign(columnAlign);
				excel.setVAlign(ExcelBuilder.VALIGN_CENTER);
				excel.setBorder(new boolean[]{true});
				
				excel.addRows(i, columnInfo, resultList);
				
				excel.setDefaultStyle();
				
			}			
			excel.download(fileNm, response);                           
    		
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable t) {
			t.printStackTrace();
		} 
    }

}