package com.sec.las.board.service;

import java.util.List;

import com.sec.las.board.dto.WeekScheduleVO;

public interface WeekScheduleService {

	public List listWeekSchedule(WeekScheduleVO vo) throws Exception;
	
	public List excelDown(WeekScheduleVO vo) throws Exception;

	public int insertWeekSchedule(WeekScheduleVO vo) throws Exception;

	public int modifyWeekSchedule(WeekScheduleVO vo) throws Exception;

	public List detailWeekSchedule(WeekScheduleVO vo) throws Exception;

	public List getWeekSchedule(WeekScheduleVO vo) throws Exception;

	String checkAuthByRole(WeekScheduleVO vo) throws Exception;
	/**
	 * 주 단위로 각 담당의 스케줄을 갱신
	 * @return void
	 * @throws Exception
	 */
	public void weeklyBatch() throws Exception;
	/**
	 * 년 단위로 각 담당의 스케줄을 갱신
	 * @return void
	 * @throws Exception
	 */
	public void annuallyBatch() throws Exception;
	
}