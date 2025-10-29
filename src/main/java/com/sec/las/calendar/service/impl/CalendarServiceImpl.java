/**
 * Project Name : 법무시스템 구주총괄
 * File name	: CalendarServiceImpl.java
 * Description	: 법무시스템 캘린더 서비스 구현체
 * Author		: 박병주
 * Date			: 2013. 10
 * Copyright 	: 2013 by SAMSUNG. All rights reserved.
 */
package com.sec.las.calendar.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.las.calendar.dto.CalVO;
import com.sec.las.calendar.service.CalendarService;

public class CalendarServiceImpl extends CommonServiceImpl implements CalendarService{
	
	/**
	 *  켈린더 ( 초기화면 / 이전 월 / 다음 월 )
	 *  
	 * @param CalVO
	 * @return List
	 * @throws Exception
	 */
	public List lasCalendar(CalVO vo) throws Exception{
		
		List resultList = null;
		
		if("tab_month".equals(vo.getTab_mode())){
			resultList=  commonDAO.list("las.calendar.init", vo);
		} else if("tab_day".equals(vo.getTab_mode())){
			resultList=  commonDAO.list("las.calendar.day", vo);
		} else if("tab_week".equals(vo.getTab_mode())){
			resultList=  commonDAO.list("las.calendar.week", vo);
		} else {
			throw new Exception("Error : no tap_mode paramater.");
		}
		
		return resultList;
	}
	
	/**
	 *  GET WEEKDAYS  
	 * @param CalVO
	 * @return List
	 * @throws Exception
	 */
	public List getWeekDays(CalVO vo) throws Exception{
		return  commonDAO.list("las.calendar.getweekdays", vo);
	}
	
	/**
	 *   해당 법무이벤트 취득 AJAX 
	 *  
	 * @param  vo CalVO
	 * @return JSONArray
	 * @throws Exception  
	 */
	public JSONArray getCalendarDayInfoAjax(CalVO vo) throws Exception{

		JSONArray jsonArray = new JSONArray();

		List al = commonDAO.list("las.calendar.dailyinfo", vo);

		if (al != null && al.size() > 0) {

			for (int i = 0; i < al.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap) al.get(i);

				JSONObject jsonObject = new JSONObject();
				
				jsonObject.put("vol_acvt_cd", (String) lom.get("vol_acvt_cd"));
				jsonObject.put("pgm_nm", (String) lom.get("pgm_nm"));
				jsonObject.put("vol_ctr_cd", (String) lom.get("vol_ctr_cd"));
				jsonObject.put("vol_ctr_nm", (String) lom.get("vol_ctr_nm"));
				//jsonObject.put("arsl", (String) lom.get("arsl"));				
				//jsonObject.put("recruit_yn", (String) lom.get("recruit_yn"));				
				
				jsonObject.put("list_size", al.size());

				jsonArray.add(jsonObject);
			}
		}

		return jsonArray;
	}
	
	/**
	 * 개인 일정 저장 (등록/수정)
	 * @param  vo CalVO
	 * @return JSONArray
	 * @throws Exception  
	 */
	public int saveCalSKDL(CalVO vo) throws Exception{
		
		int save_cnt = 0;
		
		// 개행처리
		vo.setCont(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCont(),"")));	
		
		if("INSERT".equals(vo.getWrite_mode())){
			save_cnt = commonDAO.modify("las.calendar.insert", vo);
		} else{
			save_cnt = commonDAO.modify("las.calendar.modify", vo);
		}		
		
		return save_cnt;
	}
	
	/**
	 * 개인 일정 조회
	 * @param  vo CalVO
	 * @return JSONArray
	 * @throws Exception  
	 */
	public List getCalSKDL(CalVO vo) throws Exception{
		return  commonDAO.list("las.calendar.detail", vo);
	}
	
	/**
	 * 개인 일정 삭제
	 * @param  vo CalVO
	 * @return JSONArray
	 * @throws Exception  
	 */
	public int delCalSKDL(CalVO vo) throws Exception{
		return  commonDAO.delete("las.calendar.delete", vo);
	}

}
