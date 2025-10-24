package com.sds.secframework.code.service.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.code.dto.ActscodeVO;
import com.sds.secframework.code.service.ActscodeService;
import com.sds.secframework.common.service.impl.CommonServiceImpl;


public class ActscodeServiceImpl extends CommonServiceImpl implements ActscodeService {
	
	/**********************************************************
	 * 감사커뮤니티 공통코드 처리
	 **********************************************************/

	/**
	 * 코드 목록 조회
	 */
	public List listCode(ActscodeVO vo) throws Exception {
		return commonDAO.list("acts.cmm.actscode.listCode", vo);
	}

	/**
	 * 공통코드 목록 (Ajax처리)
	 */
	public JSONArray listCodeAjax(ActscodeVO vo) throws Exception {
		List al = commonDAO.list("acts.cmm.actscode.listCode", vo);

		JSONArray jsonArray = new JSONArray();

		if(al!=null && al.size()>0) {
			
			for(int i=0; i<al.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)al.get(i);

				JSONObject jsonObject = new JSONObject();
				
				jsonObject.put("CD",		lom.get("CD"));
				jsonObject.put("CD_NM",		lom.get("CD_NM"));
				jsonObject.put("GRP_CD",	lom.get("GRP_CD"));
				jsonObject.put("SUB_CNT",	lom.get("SUB_CNT"));
				jsonObject.put("SYS_CD",	lom.get("SYS_CD"));
				jsonObject.put("CD_NM_ENG",	lom.get("CD_NM_ENG"));

				jsonArray.add(jsonObject);
			}
		}
		return jsonArray;
	}

	/**
	 * 공통코드 저장 (등록/수정/삭제 포함)
	 */
	public boolean saveCode(ActscodeVO vo) throws Exception {
		boolean retFlag = false;
		
		commonDAO.modify("acts.cmm.actscode.deleteSubCode", vo);
		
		if(vo.getCd_arr() != null && vo.getCd_arr().length > 0) {
			for(int idx=0; idx<vo.getCd_arr().length; idx++) {
				if(vo.getCd_arr()[idx].equals("genCode")) {
					List al = commonDAO.list("acts.cmm.actscode.genCode", vo);
					if(al!=null && al.size()>0) {
						ListOrderedMap lom = (ListOrderedMap)al.get(0);

						vo.setCd((String)lom.get("GEN_CD"));
						vo.setCd_nm(vo.getCd_nm_arr()[idx]);
						vo.setCd_nm_eng(vo.getCd_nm_eng_arr()[idx]);
						vo.setCd_order(idx+1);
						
						commonDAO.insert("acts.cmm.actscode.insertCode", vo);
					}
				} else {
					vo.setCd(vo.getCd_arr()[idx]);
					vo.setCd_nm(vo.getCd_nm_arr()[idx]);
					vo.setCd_nm_eng(vo.getCd_nm_eng_arr()[idx]);
					vo.setCd_order(idx+1);
					
					commonDAO.modify("acts.cmm.actscode.modifyCode", vo);
				}
			}
		}
		
		retFlag = true;
		
		return retFlag;
	}



}
