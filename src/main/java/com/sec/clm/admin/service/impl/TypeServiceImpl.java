/**
 * Project Name : 계약관리시스템
 * File name	: TypeServiceImpl.java
 * Description	: 계약유형관리 Service impl(concrete class)
 * Author		: 한지훈
 * Date			: 2011. 08. 24
 * Copyright	:
 */
package com.sec.clm.admin.service.impl;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sec.clm.admin.dto.TypeVO;
import com.sec.clm.admin.service.TypeService;

/**
 * Description	: Service impl(concrete class)
 * Author		: 한지훈
 * Date			: 2011. 08. 24
 */
public class TypeServiceImpl extends CommonServiceImpl implements TypeService {

	/**
	 * 조회
	 * @param vo TypeVO
	 * @return List
	 * @throws Exception
	 */
	public List listType(TypeVO vo) throws Exception {
		//Cross-site Scripting 방지
		vo.setSrch_dimension(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_dimension(),"")));
		vo.setSrch_word(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSrch_word(),"")));
		
		return commonDAO.list("clm.admin.listType", vo);
	}
	
	/**
	 * Item 조회
	 * @param vo TypeVO
	 * @return List
	 * @throws Exception
	 */
	public List listTypeItem(TypeVO vo) throws Exception {
		return commonDAO.list("clm.admin.listTypeItem", vo);
	}
	
	/**
	 * Item 적용 목록 조회
	 * @param vo TypeVO
	 * @return List
	 * @throws Exception
	 */
	public List listApplyItem(TypeVO vo) throws Exception {
		//Cross-site Scripting 방지
		vo.setType_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getType_cd(),"")));
		
		return commonDAO.list("clm.admin.listApplyItem", vo);
	}
	
	/**
	 * 등록
	 * @param  vo TypeVO
	 * @return String
	 * @throws Exception  sqlCUDExecutor(IQueryInfo queryInfo, Object[] values)
	 */
	public String insertType(TypeVO vo) throws Exception {
		String type_cd = "T";
		List resultList = null;
		
		//Cross-site Scripting 방지
		vo.setDimension_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDimension_cd(),"")));
		vo.setDetail_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDetail_cd(),"")));
		
		resultList = commonDAO.list("clm.admin.makeTypeCd", vo);
		
		if (resultList != null && resultList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)resultList.get(0);

			vo.setType_cd(lom.get("nextTypeCd").toString());
			
			type_cd = vo.getType_cd();
		} else {
			vo.setType_cd(type_cd);
		}

		//Cross-site Scripting 방지
		vo.setDimension_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDimension_nm(),"")));
		vo.setDetail_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDetail_nm(),"")));
		vo.setCd_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_nm(),"")));
		vo.setCd_nm_abbr(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_nm_abbr(),"")));
		vo.setCd_nm_eng(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_nm_eng(),"")));
		vo.setCd_nm_abbr_eng(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_nm_abbr_eng(),"")));
		vo.setPrnt_srt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getPrnt_srt(),"")));
		vo.setCd_expl(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_expl(),"")));
		
		vo.setReg_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_user_id(),"")));
		vo.setReg_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_user_nm(),"")));
		vo.setReg_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_dept_nm(),"")));
		commonDAO.insert("clm.admin.insertType", vo);
		
		//계약적용대상 등록
		String item_cd[] = vo.getItem_cds();
		String item_cd_nm[] = vo.getItem_cd_nms();
		String item_cd_nm_abbr[] = vo.getItem_cd_nm_abbrs();
		String item_cd_nm_eng[] = vo.getItem_cd_nm_engs();
		String item_cd_nm_abbr_eng[] = vo.getItem_cd_nm_abbr_engs();
		
		for(int i=0; i<item_cd.length; i++) {
			if(!item_cd[i].equals("")){
				vo.setItem_cd(StringUtil.convertHtmlTochars(vo.getType_cd() + StringUtil.bvl(item_cd[i],"")));
				vo.setItem_cd_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(item_cd_nm[i],"")));
				vo.setItem_cd_nm_abbr(StringUtil.convertHtmlTochars(StringUtil.bvl(item_cd_nm_abbr[i],"")));
				vo.setItem_cd_nm_eng(StringUtil.convertHtmlTochars(StringUtil.bvl(item_cd_nm_eng[i],"")));
				vo.setItem_cd_nm_abbr_eng(StringUtil.convertHtmlTochars(StringUtil.bvl(item_cd_nm_abbr_eng[i],"")));
				commonDAO.insert("clm.admin.insertTypeItem", vo);
			}
		}
		return type_cd;
	}
	
	/**
	 * 수정
	 * @param  vo TypeVO
	 * @return int
	 * @throws Exception
	 */
	public int modifyType(TypeVO vo) throws Exception {
		//Cross-site Scripting 방지
		vo.setDimension_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDimension_cd(),"")));
		vo.setDetail_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDetail_cd(),"")));
		vo.setType_cd(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getType_cd(),"")));
		vo.setDimension_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDimension_nm(),"")));
		vo.setDetail_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getDetail_nm(),"")));
		vo.setCd_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_nm(),"")));
		vo.setCd_nm_abbr(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_nm_abbr(),"")));
		vo.setCd_nm_eng(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_nm_eng(),"")));
		vo.setCd_nm_abbr_eng(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_nm_abbr_eng(),"")));
		vo.setPrnt_srt(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getPrnt_srt(),"")));
		vo.setCd_expl(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getCd_expl(),"")));
		
		vo.setMod_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_user_id(),"")));
		vo.setMod_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_user_nm(),"")));
		vo.setMod_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_dept_nm(),"")));
		
		//계약적용대상 삭제
		commonDAO.delete("clm.admin.deleteTypeItem", vo);
		
		//계약적용대상 등록
		String item_cd[] = vo.getItem_cds();
		String item_cd_nm[] = vo.getItem_cd_nms();
		String item_cd_nm_abbr[] = vo.getItem_cd_nm_abbrs();
		String item_cd_nm_eng[] = vo.getItem_cd_nm_engs();
		String item_cd_nm_abbr_eng[] = vo.getItem_cd_nm_abbr_engs();
		
		for(int i=0; i<item_cd.length; i++) {
			if(!item_cd[i].equals("")){
				vo.setItem_cd(StringUtil.convertHtmlTochars(vo.getType_cd() + StringUtil.bvl(item_cd[i],"")));
				vo.setItem_cd_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(item_cd_nm[i],"")));
				vo.setItem_cd_nm_abbr(StringUtil.convertHtmlTochars(StringUtil.bvl(item_cd_nm_abbr[i],"")));
				vo.setItem_cd_nm_eng(StringUtil.convertHtmlTochars(StringUtil.bvl(item_cd_nm_eng[i],"")));
				vo.setItem_cd_nm_abbr_eng(StringUtil.convertHtmlTochars(StringUtil.bvl(item_cd_nm_abbr_eng[i],"")));
				vo.setReg_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_user_id(),"")));
				vo.setReg_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_user_nm(),"")));
				vo.setReg_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_dept_nm(),"")));
				commonDAO.insert("clm.admin.insertTypeItem", vo);
			}
		}
		
		return commonDAO.modify("clm.admin.modifyType", vo);
	}
	
	/**
	 * 삭제
	 * @param  vo TypeVO
	 * @return int
	 * @throws Exception
	 */
	public int deleteType(TypeVO vo) throws Exception {
		//Cross-site Scripting 방지
		vo.setType_cd(StringUtil.convertHtmlTochars(vo.getType_cd()));
		
		vo.setMod_id(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_user_id(),"")));
		vo.setMod_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_user_nm(),"")));
		vo.setMod_dept_nm(StringUtil.convertHtmlTochars(StringUtil.bvl(vo.getSession_dept_nm(),"")));
		
		return commonDAO.delete("clm.admin.deleteType", vo);
	}
	
	/**
	 * 상세조회
	 * @param  vo TypeVO
	 * @return List
	 * @throws Exception
	 */
	public List detailType(TypeVO vo) throws Exception {
		return commonDAO.list("clm.admin.detailType", vo);
	}
}
