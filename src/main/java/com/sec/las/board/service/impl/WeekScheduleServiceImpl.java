package com.sec.las.board.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.las.board.dto.WeekScheduleVO;
import com.sec.las.board.service.WeekScheduleService;

public class WeekScheduleServiceImpl extends CommonServiceImpl implements WeekScheduleService {
	
	/**
	 * 
	 */
	public List listWeekSchedule(WeekScheduleVO vo) throws Exception {
		return commonDAO.list("las.weekSchedule.listPage", vo);
	}
	/**
	 * 
	 * @return
	 */
	public List excelDown(WeekScheduleVO vo) throws Exception {
		return commonDAO.list("las.weekSchedule.excelDown", vo);		
	}
	/**
	 * 
	 */
	public int insertWeekSchedule(WeekScheduleVO vo) throws Exception {

		convertHtmlToChars(vo);
	    int result = commonDAO.insert("las.weekSchedule.insert", vo);
	    
		return result;
	}
	/**
	 * 
	 */
	public int modifyWeekSchedule(WeekScheduleVO vo) throws Exception {

		convertHtmlToChars(vo);
		int result = commonDAO.modify("las.weekSchedule.modify", vo);
		
		return result;
	}
	/**
	 * 
	 */
	public List detailWeekSchedule(WeekScheduleVO vo) throws Exception {
		return commonDAO.list("las.weekSchedule.detail", vo);			
	}
	
	public List getWeekSchedule(WeekScheduleVO vo) throws Exception {
		
		return commonDAO.list("las.weekSchedule.existYn", vo);
	}	
	
	public void convertHtmlToChars(WeekScheduleVO vo) throws Exception {

		//textarea 줄바꿈 처리를 위해 \r\n을 <BR>로 치환
		vo.setCrntweek_rslt((StringUtil.replace(vo.getCrntweek_rslt(),"\r\n", "<BR>")));
		vo.setNextweek_plan((StringUtil.replace(vo.getNextweek_plan(),"\r\n", "<BR>")));
		
		vo.setCrntweek_rslt(vo.getCrntweek_rslt().replaceAll("\u0020","&nbsp"));
		vo.setNextweek_plan(vo.getNextweek_plan().replaceAll("\u0020","&nbsp"));




		//문자열 XSS 처리
		vo.setUser_nm(StringUtil.convertHtmlTochars(vo.getUser_nm()));
		vo.setReg_nm(StringUtil.convertHtmlTochars(vo.getReg_nm()));
		vo.setMod_nm(StringUtil.convertHtmlTochars(vo.getMod_nm()));
		vo.setCrntweek_rslt(StringUtil.convertHtmlTochars(vo.getCrntweek_rslt()));
		vo.setNextweek_plan(StringUtil.convertHtmlTochars(vo.getNextweek_plan()));
		
		//<BR>태그는 XSS처리 예외 적용
		vo.setCrntweek_rslt(StringUtil.replace(vo.getCrntweek_rslt(),"&lt;BR&gt;", "<BR>"));
		vo.setNextweek_plan(StringUtil.replace(vo.getNextweek_plan(),"&lt;BR&gt;", "<BR>"));
	}
	
	/**
	 * 사용자 ROLE에 따른 권한 제어
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	public String checkAuthByRole(WeekScheduleVO vo) throws Exception{
		try {

			/*
				시스템관리자        	RA00, AA00
				법무관리자          	RA01, AA01
				법무담당자          	RA02, AA02
				법무일반 사용자     	RA03, AA03
				cp관리자         RC01, AC01
				사업부계약관리자   RD01, AD01
				사업부계약담당자   RD02, AD02
				시스템운영자        	RM00, AM00
				일반임직원         	RZ00, AZ00 

			 	A: 전체 건에 대한 CRU가능
			 	B: 본인 것만 CRU가능
			*/

			String accessLevel = "";

	        ArrayList roleList = vo.getSession_user_role_cds();
			ArrayList userRoleList = new ArrayList();
			
			if(roleList != null && roleList.size() > 0){
			    for(int idx = 0; idx < roleList.size(); idx++){
			        HashMap roleListMap = (HashMap)roleList.get(idx);
			        userRoleList.add((String)roleListMap.get("role_cd"));
			    }
			}
			
			if(userRoleList != null && userRoleList.size() > 0){
				if(userRoleList.contains("RM00") || userRoleList.contains("RA00") || userRoleList.contains("RA01")){
					accessLevel = "A";
				} else if(userRoleList.contains("RA02") || userRoleList.contains("RA03")){
					accessLevel = "B";
				} else{
					accessLevel = "C";
				}
			}

			return accessLevel;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("NoticeController.detailNotice() Exception !!");
		}catch (Throwable t) {
			t.printStackTrace();
			throw new Exception("NoticeController.detailNotice() Throwable !!");
		}
	}
	
	/**
	 * 주 단위로 각 담당의 스케줄을 갱신
	 * @return void
	 * @throws Exception
	 */
	public void weeklyBatch() throws Exception{
		commonDAO.modify("las.weekSchedule.weeklyBatch");
	}
	/**
	 * 년 단위로 각 담당의 스케줄을 갱신
	 * @return void
	 * @throws Exception
	 */
	public void annuallyBatch() throws Exception{
		commonDAO.modify("las.weekSchedule.annuallyBatch");
	}
}