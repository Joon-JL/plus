/**
 * Project Name : 법무시스템 구주총괄
 * File name	: CalendarService.java
 * Description	: 법무시스템 캘린더 서비스
 * Author		: 박병주
 * Date			: 2013. 10
 * Copyright 	: 2013 by SAMSUNG. All rights reserved.
 */
package com.sec.las.calendar.service;

import java.util.List;
import net.sf.json.JSONArray;
import com.sec.las.calendar.dto.CalVO;

public interface CalendarService {
	/**
	 *  법무 켈린더 ( 초기화면 / 이전 월 / 다음 월 )
	 *  
	 * @param CalVO
	 * @return List
	 * @throws Exception
	 */
	List lasCalendar(CalVO vo) throws Exception;
	
	/**
	 *  GET WEEKDAYS  
	 * @param CalVO
	 * @return List
	 * @throws Exception
	 */
	List getWeekDays(CalVO vo) throws Exception;
	
	/**
	 *  캘린더 ( 해당 법무이벤트 취득 AJAX) 
	 *  
	 * @param  vo CalVO
	 * @return JSONArray
	 * @throws Exception  
	 */
	JSONArray getCalendarDayInfoAjax(CalVO vo) throws Exception;	
	
	/**
	 * 개인 일정 등록  
	 * @param  vo CalVO
	 * @return JSONArray
	 * @throws Exception  
	 */
	int saveCalSKDL(CalVO vo) throws Exception;	
	
	/**
	 * 개인 일정 조회
	 * @param  vo CalVO
	 * @return JSONArray
	 * @throws Exception  
	 */
	List getCalSKDL(CalVO vo) throws Exception;	
	
	/**
	 * 개인 일정 삭제
	 * @param  vo CalVO
	 * @return JSONArray
	 * @throws Exception  
	 */
	int delCalSKDL(CalVO vo) throws Exception;	
}
