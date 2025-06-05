package com.sds.secframework.code.service.impl;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.code.dto.CodeVO;
import com.sds.secframework.code.service.CodeService;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.Converter;
import com.sds.secframework.common.util.FormatUtil;
import com.sds.secframework.common.util.StringUtil;

public class CodeServiceImpl extends CommonServiceImpl implements CodeService {

	/**********************************************************
	 * 공통코드
	 **********************************************************/

	// 그룹코드 목록조회
	public JSONArray listGrpCode(CodeVO vo) throws Exception {
		List al =  commonDAO.list("secfw.code.listGrpCode", vo);
				
		JSONArray jsonArray = new JSONArray();

		if(al!=null && al.size()>0) {
			
			for(int i=0; i<al.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)al.get(i);

				JSONObject jsonObject = new JSONObject();

				jsonObject.put("sys_cd", (String)lom.get("sys_cd"));
				jsonObject.put("grp_cd", (String)lom.get("grp_cd"));
				jsonObject.put("grp_cd_nm", (String)lom.get("grp_cd_nm"));
				jsonObject.put("grp_cd_abbr_nm", (String)lom.get("grp_cd_abbr_nm"));
				jsonObject.put("grp_cd_nm_eng", (String)lom.get("grp_cd_nm_eng"));
				jsonObject.put("grp_cd_abbr_nm_eng", (String)lom.get("grp_cd_abbr_nm_eng"));
				jsonObject.put("use_yn", (String)lom.get("use_yn"));
				jsonObject.put("comments", (String)lom.get("comments"));
				jsonObject.put("reg_id", (String)lom.get("reg_id"));
				jsonObject.put("reg_dt", (String)lom.get("reg_dt"));
				jsonObject.put("mod_id", (String)lom.get("mod_id"));
				jsonObject.put("mod_dt", (String)lom.get("mod_dt"));
				jsonObject.put("total_cnt", FormatUtil.formatNumToString(lom.get("total_cnt")));
				
				jsonArray.add(jsonObject);
				
			}
		}
		
		return jsonArray;	
	}
	
	// 공통코드 목록조회
	public JSONArray listCode(CodeVO vo) throws Exception {
		List al =  commonDAO.list("secfw.code.listCode", vo);
		
		JSONArray jsonArray = new JSONArray();

		if(al!=null && al.size()>0) {
			
			for(int i=0; i<al.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)al.get(i);

				JSONObject jsonObject = new JSONObject();

				jsonObject.put("sys_cd",  (String)lom.get("sys_cd"));
				jsonObject.put("grp_cd",  (String)lom.get("grp_cd"));
				jsonObject.put("grp_cd_nm",  (String)lom.get("grp_cd_nm"));
				jsonObject.put("cd",  (String)lom.get("cd"));
				jsonObject.put("cd_nm",  (String)lom.get("cd_nm"));
				jsonObject.put("cd_abbr_nm",  (String)lom.get("cd_abbr_nm"));
				jsonObject.put("cd_nm_eng",  (String)lom.get("cd_nm_eng"));
				jsonObject.put("cd_abbr_nm_eng",  (String)lom.get("cd_abbr_nm_eng"));
				jsonObject.put("cd_order",  FormatUtil.formatNumToString(lom.get("cd_order")));
				jsonObject.put("use_yn",  (String)lom.get("use_yn"));
				jsonObject.put("comments",  (String)lom.get("comments"));
				jsonObject.put("useman_mng_itm1",  (String)lom.get("useman_mng_itm1"));
				jsonObject.put("useman_mng_itm2",  (String)lom.get("useman_mng_itm2"));
				jsonObject.put("useman_mng_itm3",  (String)lom.get("useman_mng_itm3"));
				jsonObject.put("useman_mng_itm4",  (String)lom.get("useman_mng_itm4"));
				jsonObject.put("useman_mng_itm5",  (String)lom.get("useman_mng_itm5"));
				jsonObject.put("reg_id",  (String)lom.get("reg_id"));
				jsonObject.put("reg_dt",  (String)lom.get("reg_dt"));
				jsonObject.put("mod_id",  (String)lom.get("mod_id"));
				jsonObject.put("mod_dt",  (String)lom.get("mod_dt"));
				jsonObject.put("total_cnt",  FormatUtil.formatNumToString(lom.get("total_cnt")));
				
				jsonArray.add(jsonObject);
			}
		}
		
		return jsonArray;	
	}
	
	// 공통코드 목록조회
	public JSONArray listCodeCombo(CodeVO vo) throws Exception {
		List al =  commonDAO.list("secfw.code.listCode", vo);
		
		JSONArray jsonArray = new JSONArray();

		if(al!=null && al.size()>0) {
			
			for(int i=0; i<al.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)al.get(i);

				JSONObject jsonObject = new JSONObject();

				jsonObject.put("sys_cd",  (String)lom.get("sys_cd"));
				jsonObject.put("grp_cd",  (String)lom.get("grp_cd"));
				jsonObject.put("grp_cd_nm",  (String)lom.get("grp_cd_nm"));
				jsonObject.put("cd",  (String)lom.get("cd"));
				jsonObject.put("cd_nm",  (String)lom.get("cd_nm"));
				jsonObject.put("cd_abbr_nm",  (String)lom.get("cd_abbr_nm"));
				jsonObject.put("cd_nm_eng",  (String)lom.get("cd_nm_eng"));
				jsonObject.put("cd_abbr_nm_eng",  (String)lom.get("cd_abbr_nm_eng"));
				jsonObject.put("cd_order",  (String)lom.get("cd_order"));
				jsonObject.put("use_yn",  (String)lom.get("use_yn"));
				jsonObject.put("comments",  (String)lom.get("comments"));
				jsonObject.put("useman_mng_itm1",  (String)lom.get("useman_mng_itm1"));
				jsonObject.put("useman_mng_itm2",  (String)lom.get("useman_mng_itm2"));
				jsonObject.put("useman_mng_itm3",  (String)lom.get("useman_mng_itm3"));
				jsonObject.put("useman_mng_itm4",  (String)lom.get("useman_mng_itm4"));
				jsonObject.put("useman_mng_itm5",  (String)lom.get("useman_mng_itm5"));
				jsonObject.put("reg_id",  (String)lom.get("reg_id"));
				jsonObject.put("reg_dt",  (String)lom.get("reg_dt"));
				jsonObject.put("mod_id",  (String)lom.get("mod_id"));
				jsonObject.put("mod_dt",  (String)lom.get("mod_dt"));
				jsonObject.put("total_cnt",  (String)lom.get("total_cnt"));
				
				jsonArray.add(jsonObject);
			}
		}
		
		return jsonArray;	
	}

	// 그룹코드 신규등록
	public int insertGrpCode(CodeVO vo) throws Exception {

		int result = 0;

		String[] grp_cds             = vo.getGrp_cds();
		String[] grp_cd_nms          = vo.getGrp_cd_nms();
		String[] grp_cd_abbr_nms     = vo.getGrp_cd_abbr_nms();
		String[] grp_cd_nm_engs      = vo.getGrp_cd_nm_engs();
		String[] grp_cd_abbr_nm_engs = vo.getGrp_cd_abbr_nm_engs();
		String[] use_yns             = vo.getUse_yns();
		String[] commentss           = vo.getCommentss();
		  
		commonDAO.delete("secfw.code.deleteGrpCode4Insert", vo);
		
		if(grp_cds!=null && grp_cds.length>0) {
			for(int i=0; i<grp_cds.length; i++) {

				String grpCd = StringUtil.bvl(grp_cds[i],"");
				
				vo.setGrp_cd(grpCd);
				vo.setGrp_cd_nm(grp_cd_nms[i]);
				vo.setGrp_cd_abbr_nm(grp_cd_abbr_nms[i]);
				vo.setGrp_cd_nm_eng(grp_cd_nm_engs[i]);
				vo.setGrp_cd_abbr_nm_eng(grp_cd_abbr_nm_engs[i]);
				vo.setUse_yn(use_yns[i]);
				vo.setComments(commentss[i]);
				
				result += commonDAO.insert("secfw.code.insertGrpCode", vo);
			}
		}
		
		return result;
	
	}
	
	// 공통코드 신규등록
	public int insertCode(CodeVO vo) throws Exception {

		int result = 0;
		
		vo.setGrp_cd(vo.getSelect_grp_cd());

		String[] cds                 = vo.getCds();
		String[] cd_nms              = vo.getCd_nms();
		String[] cd_abbr_nms         = vo.getCd_abbr_nms();
		String[] cd_nm_engs          = vo.getCd_nm_engs();
		String[] cd_abbr_nm_engs     = vo.getCd_abbr_nm_engs();
		int[]    cd_orders           = vo.getCd_orders();
		String[] useman_mng_itm1s    = vo.getUseman_mng_itm1s();
		String[] useman_mng_itm2s    = vo.getUseman_mng_itm2s();
		String[] useman_mng_itm3s    = vo.getUseman_mng_itm3s();
		String[] useman_mng_itm4s    = vo.getUseman_mng_itm4s();
		String[] useman_mng_itm5s    = vo.getUseman_mng_itm5s();
		String[] use_yns             = vo.getUse_yns();
		String[] commentss           = vo.getCommentss();
		
		commonDAO.delete("secfw.code.deleteCodeByDeleteGrpCode", vo);
		  
		if(cds!=null && cds.length>0) {
			for(int i=0; i<cds.length; i++) {

				String cd = StringUtil.bvl(cds[i],"");
				
				vo.setCd(cd);
				vo.setCd_nm(cd_nms[i]);
				vo.setCd_abbr_nm(cd_abbr_nms[i]);
				vo.setCd_nm_eng(cd_nm_engs[i]);
				vo.setCd_abbr_nm_eng(cd_abbr_nm_engs[i]);
				vo.setCd_order(cd_orders[i]);
				vo.setUseman_mng_itm1(useman_mng_itm1s[i]);
				vo.setUseman_mng_itm2(useman_mng_itm2s[i]);
				vo.setUseman_mng_itm3(useman_mng_itm3s[i]);
				vo.setUseman_mng_itm4(useman_mng_itm4s[i]);
				vo.setUseman_mng_itm5(useman_mng_itm5s[i]);
				vo.setUse_yn(use_yns[i]);
				vo.setComments(commentss[i]);
				
				result += commonDAO.insert("secfw.code.insertCode", vo);
			}
		}
		
		return result;
	}
	
	// 그룹코드 수정
	public int modifyGrpCode(CodeVO vo) throws Exception {

		int result = 0;
		
		//그룹코드정보 수정
		result += commonDAO.modify("secfw.code.modifyGrpCode", vo);
		
		//공통코드 사용여부 'N'으로 설정 
		if("N".equals(vo.getUse_yn())) 
			result += commonDAO.modify("secfw.code.modifyChildCodeUse", vo);
		
		return result;

	}
	
	// 공통코드 수정
	public int modifyCode(CodeVO vo) throws Exception {
		return  commonDAO.modify("secfw.code.modifyCode", vo);
	}

	// 그룹코드 삭제
	public int deleteGrpCode(CodeVO vo) throws Exception {

		int result = 0;
		String[] mappingInfo = vo.getChk_grp_cds();

		if(mappingInfo!=null && mappingInfo.length>0) {
			for(int i=0; i<mappingInfo.length; i++) {
				vo.setGrp_cd(mappingInfo[i]);
				//그룹코드삭제
				result += commonDAO.delete("secfw.code.deleteGrpCode", vo);	
				//그룹코드삭제시 공통코드 삭제
				commonDAO.delete("secfw.code.deleteCodeByDeleteGrpCode", vo);	
			}
		}
		
		return result;			

	}
	// 공통코드 삭제
	public int deleteCode(CodeVO vo) throws Exception {
		
		int result = 0;
		String[] mappingInfo = vo.getChk_cds();

		if(mappingInfo!=null && mappingInfo.length>0) {
			for(int i=0; i<mappingInfo.length; i++) {
				vo.setCd(mappingInfo[i]);
				result += commonDAO.delete("secfw.code.deleteCode", vo);	
			}
		}
		
		return result;			

	}
	
	// 그룹코드 존재여부
	public String existsGrpCode(HashMap hm) throws Exception {
		
		String result = "";
		
		List list = commonDAO.list("secfw.code.existsGrpCode", hm);
		
		if(list != null && list.size()>0) {
			ListOrderedMap lom = (ListOrderedMap)list.get(0);
			result = (String)lom.get("exists_yn");
		}
			
		return result;
	}

	// 공통코드 존재여부
	public String existsCode(HashMap hm) throws Exception {
		String result = "";
		
		List list = commonDAO.list("secfw.code.existsCode", hm);
		
		if(list != null && list.size()>0) {
			ListOrderedMap lom = (ListOrderedMap)list.get(0);
			result = (String)lom.get("exists_yn");
		}
			
		return result;
	}
	
	/**
	 * 대분류에 의한 공통 코드 리스트를 반환한다
	 * @param GRP_CD 그룹코드
	 * @param SELECTED 디폴트 선택 코드
	 * @param LOCALE 한글영어 (ko : 한글, en : 영어)
	 * @param ABBR 약어/Full Name (A:약어, F:풀네임)
	 * @param DEL_YN 삭제여부(N : 삭제아닌 것들만, Y : 전부다)
	 * @param TYPE 최상위 표지구분 (S : 선택, T : 전체, "" : NULL)
	 */
	public String getCdComboByGrpCd(HashMap map) throws Exception {
		String result = "";
		String str = "";
		
		String sysCd = StringUtil.bvl((String)map.get("SYS_CD"), propertyService.getProperty("secfw.sysCd"));
		String grpCd = StringUtil.bvl((String)map.get("GRP_CD"), "");
		String selected = StringUtil.bvl((String)map.get("SELECTED"), "");
		String locale = StringUtil.bvl((String)map.get("LOCALE"), "");
		String abbr = StringUtil.bvl((String)map.get("ABBR"), "");
		String delYn = StringUtil.bvl((String)map.get("DEL_YN"), "");
		String type = StringUtil.bvl((String)map.get("TYPE"), "");
		
		HashMap iHm = new HashMap();
		//iHm.put("sys_cd", CpisConstants.CPIS_SYS_CD);
		iHm.put("sys_cd", sysCd);
		iHm.put("grp_cd", grpCd);
		iHm.put("del_yn", delYn);
		
		List list = commonDAO.list("secfw.code.getCdComboByGrpCd", iHm);
		
		String title = "";
		
		// 한영 표시문자 선택
		if ("ko".equals(locale)) {
			// 최상위 표시
			if ("S".equals(type)) {
				title = "-- 선택 --";
			}else if ("T".equals(type)) {
				title = "-- 전체 --";
			}else {
				title = "";
			}
		}else if ("zh".equals(locale)) {
			// 최상위 표시
			if ("S".equals(type)) {
				title = "-- Select --";
			}else if ("T".equals(type)) {
				title = "-- 全部 --";
			}else {
				title = "";
			}
		}else {
			// 최상위 표시
			if ("S".equals(type)) {
				title = "-- Select --";
			}else if ("T".equals(type)) {
				title = "-- All --";
			}else {
				title = "";
			}
		}
		
		if (!"".equals(title)) {
			result = "<option value=''>" + title + "</option>";
		}
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)list.get(i);
				
				String displayNm = "";
				
				String cd		   = (String)lom.get("cd");		
				String cdNm	       = (String)lom.get("cd_nm");	
				String cdAbbrNm	   = (String)lom.get("cd_abbr_nm");	
				String cdNmEng     = (String)lom.get("cd_nm_eng");	
				String cdAbbrNmEng = (String)lom.get("cd_abbr_nm_eng");	
				String cdNmChn     = (String)lom.get("cd_nm_chn");	
				String cdAbbrNmChn = (String)lom.get("cd_abbr_nm_chn");
				String cdNmFra     = (String)lom.get("cd_nm_fra");	
				String cdAbbrNmFra = (String)lom.get("cd_abbr_nm_fra");
				String cdNmDeu     = (String)lom.get("cd_nm_deu");	
				String cdAbbrNmDeu = (String)lom.get("cd_abbr_nm_deu");
						
				if( cd.equals(selected)){
					str = "selected";
				}else{
					str = "";
				}
				
				//한영 표시문자 선택
				if("ko".equals(locale)) {
					if("A".equals(abbr)) {//약어이면
						displayNm = cdAbbrNm;
					} else {
						displayNm = cdNm;
					}
				} else if("zh".equals(locale)) {
					if("A".equals(abbr)) {//약어이면
						displayNm = cdAbbrNmChn;
					} else {
						displayNm = cdNmChn;
					}
				} else if("fr".equals(locale)) {
					if("A".equals(abbr)) {//약어이면
						displayNm = cdAbbrNmFra;
					} else {
						displayNm = cdNmFra;
					}
				} else if("de".equals(locale)) {
					if("A".equals(abbr)) {//약어이면
						displayNm = cdAbbrNmDeu;
					} else {
						displayNm = cdNmDeu;
					}
				} else {
					if("A".equals(abbr)) {//약어이면
						displayNm = cdAbbrNmEng;
					} else {
						displayNm = cdNmEng;
					}
				}
				
				result = result+"<option value='"+cd+"' "+str+">"+displayNm+"</option>";
			}
		}
		
		return result;
	}

}
