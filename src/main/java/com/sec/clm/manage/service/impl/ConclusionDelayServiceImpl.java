package com.sec.clm.manage.service.impl;

import java.util.HashMap;
import java.util.List;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.StringUtil;

import com.sec.clm.manage.dto.ConclusionDelayVO;
import com.sec.clm.manage.service.ConclusionDelayService;
/**
 * Description	: 미체결사유 Service Impl
 * Author 		: 심주완
 * Date			: 2011. 09. 05
 */
public class ConclusionDelayServiceImpl extends CommonServiceImpl implements ConclusionDelayService {

	/**
	 * 미체결사유 전체목록을 조회한다.			히스토리관리시 사용함(현재사용안함)
	 * @param  vo ConclusionDelayVO
	 * @return List
	 * @throws Exception
	 */
	public List listConclusionDelay(ConclusionDelayVO vo) throws Exception {
		return commonDAO.list("clm.manage.listConclusionDelay", vo);
	}

	/**
	 * 미체결사유 등록 및 수정한다.			히스토리관리시 사용함(현재사용안함)
	 * @param  vo ConclusionDelayVO
	 * @return int
	 * @throws Exception
	 */
	public int insertConclusionDelay(ConclusionDelayVO vo) throws Exception {
		
		int result = 0;
		for(int i=0; i < vo.getCntrt_id_arr().length; i++) {
			vo.setCntrt_id(vo.getCntrt_id_arr()[i]);
			vo.setDlay_seqno(vo.getDlay_seqno_arr()[i]);
			//XXS처리
			vo.setChgebfr_cnclsn_plndday(StringUtil.convertHtmlTochars(vo.getChgebfr_cnclsn_plndday_arr()[i].replace("-", "")));	//변경전체결예정일
			vo.setChgeaft_cnclsn_plndday(StringUtil.convertHtmlTochars(vo.getChgeaft_cnclsn_plndday_arr()[i].replace("-", "")));	//변경전체결예정일
			vo.setDlay_cause(StringUtil.convertHtmlTochars(vo.getDlay_cause_arr()[i]));
			
			vo.setReg_id(vo.getSession_user_id());
			vo.setReg_nm(vo.getSession_user_nm());
			vo.setDel_yn(vo.getDel_yn_arr()[i]);
			vo.setDel_id(vo.getSession_user_id());
			vo.setDel_nm(vo.getSession_user_nm());
			vo.setWork_flag(vo.getWork_flag_arr()[i]);
			
			result = commonDAO.insert("clm.manage.insertConclusionDelay", vo);
		}
		return result;
	}
	
	
	/**
	 * 미체결사유 전체목록을 조회한다.	히스토리관리안함	2011.11.16 심주완추가
	 * @param  vo ConclusionDelayVO
	 * @return List
	 * @throws Exception
	 */
	public List listConclusionDelayForMain(ConclusionDelayVO vo) throws Exception {
		return commonDAO.list("clm.manage.listConclusionDelayForMain", vo);
	}

	/**
	 * 미체결사유 등록 및 수정한다.  	히스토리관리안함	2011.11.16 심주완추가
	 * @param  vo ConclusionDelayVO
	 * @return int
	 * @throws Exception
	 */
	public int insertConclusionDelayForMain(ConclusionDelayVO vo) throws Exception {
		
		int result = 0;
			//XXS처리
		vo.setChgebfr_cnclsn_plndday(StringUtil.convertHtmlTochars(vo.getChgebfr_cnclsn_plndday().replace("-", "")));	//변경전체결예정일
		vo.setChgeaft_cnclsn_plndday(StringUtil.convertHtmlTochars(vo.getChgeaft_cnclsn_plndday()).replace("-", ""));	//변경전체결예정일
		vo.setDlay_cause(StringUtil.convertHtmlTochars(vo.getDlay_cause()));
			
		vo.setReg_id(vo.getSession_user_id());
		vo.setReg_nm(vo.getSession_user_nm());
		vo.setDel_id(vo.getSession_user_id());
		vo.setDel_nm(vo.getSession_user_nm());
		vo.setWork_flag(vo.getWork_flag());
			
		result = commonDAO.insert("clm.manage.insertConclusionDelayForMain", vo);
		
		return result;
	}

}