/**
 * Project Name : 계약관리시스템
 * File name	: SubjectServiceImpl.java
 * Description	: 계약분류상세관리 Service impl(concrete class)
 * Author		: 한지훈
 * Date			: 2011. 08. 24
 * Copyright	:
 */
package com.sec.clm.admin.service.impl;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.admin.dto.SubjectVO;
import com.sec.clm.admin.service.SubjectService;

/**
 * Description	: Service impl(concrete class)
 * Author		: 한지훈
 * Date			: 2011. 08. 24
 */
public class SubjectServiceImpl extends CommonServiceImpl implements SubjectService {

	/**
	 * 조회
	 * @param vo SubjectVO
	 * @return List
	 * @throws Exception
	 */
	public List listSubject(SubjectVO vo) throws Exception {
		//Cross-site Scripting 방지
		vo.setSrch_dimension(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_dimension(),"")));
		
		return commonDAO.list("clm.admin.listSubject", vo);
	}
	
	/**
	 * 등록
	 * @param  vo SubjectVO
	 * @return String
	 * @throws Exception  sqlCUDExecutor(IQueryInfo queryInfo, Object[] values)
	 */
	public String insertSubject(SubjectVO vo) throws Exception {
		String type_cd = "T";
		List resultList = null;
		
		resultList = commonDAO.list("clm.admin.makeSubjectCd", vo);
		
		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

			vo.setType_cd(lom.get("nextSubjectCd").toString());
			
			type_cd = vo.getType_cd();
		} else {
			vo.setType_cd(type_cd);
		}

		//Cross-site Scripting 방지
		vo.setCd_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_nm(),"")));
		vo.setCd_nm_abbr(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_nm_abbr(),"")));
		vo.setCd_nm_eng(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_nm_eng(),"")));
		vo.setCd_nm_abbr_eng(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_nm_abbr_eng(),"")));
		vo.setPrnt_srt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getPrnt_srt(),"")));
		vo.setCd_expl(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_expl(),"")));
		
		vo.setReg_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_user_id(),"")));
		vo.setReg_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_user_nm(),"")));
		vo.setReg_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_dept_nm(),"")));
		commonDAO.insert("clm.admin.insertSubject", vo);
		
		return type_cd;
	}
	
	/**
	 * 수정
	 * @param  vo DimensionVO
	 * @return int
	 * @throws Exception
	 */
	public int modifySubject(SubjectVO vo) throws Exception {
		//Cross-site Scripting 방지
		vo.setType_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getType_cd(),"")));
		vo.setCd_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_nm(),"")));
		vo.setCd_nm_abbr(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_nm_abbr(),"")));
		vo.setCd_nm_eng(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_nm_eng(),"")));
		vo.setCd_nm_abbr_eng(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_nm_abbr_eng(),"")));
		vo.setPrnt_srt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getPrnt_srt(),"")));
		vo.setCd_expl(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_expl(),"")));
		
		vo.setMod_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_user_id(),"")));
		vo.setMod_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_user_nm(),"")));
		vo.setMod_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_dept_nm(),"")));
		
		return commonDAO.modify("clm.admin.modifySubject", vo);
	}
	
	/**
	 * 삭제
	 * @param  vo SubjectVO
	 * @return int
	 * @throws Exception
	 */
	public int deleteSubject(SubjectVO vo) throws Exception {
		//Cross-site Scripting 방지
		vo.setType_cd(StringUtil.convertHtmlTochars(vo.getType_cd()));
		
		vo.setMod_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_user_id(),"")));
		vo.setMod_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_user_nm(),"")));
		vo.setMod_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_dept_nm(),"")));
		
		return commonDAO.delete("clm.admin.deleteSubject", vo);
	}
	
	/**
	 * 상세조회
	 * @param  vo SubjectVO
	 * @return List
	 * @throws Exception
	 */
	public List detailSubject(SubjectVO vo) throws Exception {
		return commonDAO.list("clm.admin.detailSubject", vo);
	}
}
