package com.sds.secframework.auth.service.impl;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;

import anyframe.core.idgen.IIdGenerationService;

import com.sds.secframework.auth.dto.AuthVO;
import com.sds.secframework.auth.service.AuthMngService;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.FormatUtil;
import com.sds.secframework.common.util.StringUtil;

public class AuthMngServiceImpl extends CommonServiceImpl implements AuthMngService{

	/**
	 * ID 값을 생성하기 위한 idGeneration 선언 및 세팅
	 */
	private IIdGenerationService idGenerationService = null;
	public void setIdGenerationService(IIdGenerationService idGenerationService) {
		this.idGenerationService = idGenerationService;
	}
	
	/**
	 * 권한 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public JSONArray listAuth(HashMap hm) throws Exception {
		
		List list = commonDAO.list("secfw.auth.authMng.listAuth", hm);

		JSONArray jsonArray = new JSONArray();

		if(list!=null && list.size()>0) {
			
			for(int i=0; i<list.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)list.get(i);

				JSONObject jsonObject = new JSONObject();

				jsonObject.put("sys_cd"       ,(String)lom.get("sys_cd"));
				jsonObject.put("auth_cd"      ,(String)lom.get("auth_cd"));
				jsonObject.put("auth_nm"      ,(String)lom.get("auth_nm"));
				jsonObject.put("comments"     ,(String)lom.get("comments"));
				jsonObject.put("use_yn"       ,(String)lom.get("use_yn"));
				jsonObject.put("total_cnt"    ,FormatUtil.formatNumToString(lom.get("total_cnt")));

				jsonArray.add(jsonObject);
			}
		}
		
		return jsonArray;
	}

	/**
	 * 권한 내역 등록
	 * @param vo 권한관리 Value Object
	 * @return 
	 * @throws Exception
	 */
	public int insertAuth(AuthVO vo) throws Exception {
		
		int result = 0;

		  String[] auth_cds       = vo.getAuth_cds();         // 페이지ID
		  String[] auth_nms       = vo.getAuth_nms();         // 페이지명
		  String[] commentss      = vo.getCommentss();        // 설명
		  String[] use_yns        = vo.getUse_yns();          // 사용여부
		  
		if(auth_cds!=null && auth_cds.length>0) {
			for(int i=0; i<auth_cds.length; i++) {

				String authCd = StringUtil.bvl(auth_cds[i],"");
				
				vo.setAuth_cd(auth_cds[i]);              
				vo.setAuth_nm(StringUtil.convertHtmlTochars(auth_nms[i]));              
				vo.setComments(StringUtil.convertHtmlTochars(commentss[i]));            
				vo.setUse_yn(use_yns[i]);                
				
				if("".equals(authCd)) {

					//권한코드 생성
					vo.setAuth_cd(DateUtil.getTime("yyyyMMddHHmmssSSS") + idGenerationService.getNextStringId());
										
					result += commonDAO.insert("secfw.auth.authMng.insertAuth", vo);
				} else {
					result += modifyAuth(vo);
				}
			}
		}
	
		return result;	
	
	}

	/**
	 * 권한 내역 수정
	 * @param vo 권한관리 Value Object
	 * @return 
	 * @throws Exception
	 */
	public int modifyAuth(AuthVO vo) throws Exception {
		vo.setAuth_nm(StringUtil.convertHtmlTochars(vo.getAuth_nm()));              
		vo.setComments(StringUtil.convertHtmlTochars(vo.getComments()));            
		return commonDAO.modify("secfw.auth.authMng.modifyAuth", vo);		
	}

	/**
	 * 권한 내역 삭제
	 * @param vo 권한관리 Value Object
	 * @return 
	 * @throws Exception
	 */
	public void deleteAuth(AuthVO vo) throws Exception {

		String[] chkValues = vo.getChkValues();
		
		for(int i=0; i<chkValues.length; i++) {
			String auth_cd = chkValues[i];
			vo.setAuth_cd(auth_cd);
			
			//권한삭제
			commonDAO.delete("secfw.auth.authMng.deleteAuth", vo);
			//메뉴-권한삭제
			commonDAO.delete("secfw.auth.authMapping.deleteMenuAuth", vo);		
			//페이지-권한삭제
			commonDAO.delete("secfw.auth.authMapping.deletePageAuth", vo);		
			//역활-권한삭제
			commonDAO.delete("secfw.auth.authMapping.deleteRoleAuthByAuthCd", vo);		
		}
	}
}