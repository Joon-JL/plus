package com.sds.secframework.auth.service.impl;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;

import anyframe.core.idgen.IIdGenerationService;

import com.sds.secframework.auth.dto.AuthVO;
import com.sds.secframework.auth.service.RoleMngService;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.FormatUtil;
import com.sds.secframework.common.util.StringUtil;

public class RoleMngServiceImpl extends CommonServiceImpl implements RoleMngService{

	/**
	 * ID 값을 생성하기 위한 idGeneration 선언 및 세팅
	 */
	private IIdGenerationService idGenerationService = null;
	public void setIdGenerationService(IIdGenerationService idGenerationService) {
		this.idGenerationService = idGenerationService;
	}
	
	/**
	 * 역활 목록 조회
	 * @param 
	 * @return 
	 * @throws Exception
	 */
	public JSONArray listRole(HashMap hm) throws Exception {
		
		List list = commonDAO.list("secfw.auth.roleMng.listRole", hm);

		JSONArray jsonArray = new JSONArray();

		if(list!=null && list.size()>0) {
			
			for(int i=0; i<list.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)list.get(i);

				JSONObject jsonObject = new JSONObject();

				jsonObject.put("sys_cd"       ,(String)lom.get("sys_cd"));
				jsonObject.put("role_cd"      ,(String)lom.get("role_cd"));
				jsonObject.put("role_nm"      ,(String)lom.get("role_nm"));
				jsonObject.put("comments"     ,(String)lom.get("comments"));
				jsonObject.put("use_yn"       ,(String)lom.get("use_yn"));
				jsonObject.put("total_cnt"    ,FormatUtil.formatNumToString(lom.get("total_cnt")));

				jsonArray.add(jsonObject);
			}
		}
		
		return jsonArray;	
	
	}

	/**
	 * 역활 내역 등록
	 * @param vo 역활관리 Value Object
	 * @return 
	 * @throws Exception
	 */
	public int insertRole(AuthVO vo) throws Exception {
	
		int result = 0;

		  String[] role_cds       = vo.getRole_cds();         // 페이지ID
		  String[] role_nms       = vo.getRole_nms();         // 페이지명
		  String[] commentss      = vo.getCommentss();        // 설명
		  String[] use_yns        = vo.getUse_yns();          // 사용여부
		  
		  //역활삭제
		  commonDAO.delete("secfw.auth.roleMng.deleteRole4Insert", vo);
		
		if(role_cds!=null && role_cds.length>0) {
			for(int i=0; i<role_cds.length; i++) {

				String roleCd = StringUtil.bvl(role_cds[i],"");
				
				vo.setRole_cd(StringUtil.convertHtmlTochars(roleCd));              
				vo.setRole_nm(StringUtil.convertHtmlTochars(role_nms[i]));              
				vo.setComments(StringUtil.convertHtmlTochars(commentss[i]));            
				vo.setUse_yn(use_yns[i]);                
				
				result += commonDAO.insert("secfw.auth.roleMng.insertRole", vo);
			}
		}	
		  
//		if(role_cds!=null && role_cds.length>0) {
//			for(int i=0; i<role_cds.length; i++) {
//
//				String roleCd = StringUtil.bvl(role_cds[i],"");
//				
//				vo.setRole_cd(role_cds[i]);              
//				vo.setRole_nm(role_nms[i]);              
//				vo.setComments(commentss[i]);            
//				vo.setUse_yn(use_yns[i]);                
//				
//				if("".equals(roleCd)) {
//
//					//역활코드 생성
//					vo.setRole_cd(DateUtil.getTime("yyyyMMddHHmmssSSS") + idGenerationService.getNextStringId());
//										
//					result += commonDAO.insert("secfw.auth.roleMng.insertRole", vo);
//				} else {
//					result += modifyRole(vo);
//				}
//			}
//		}
	
		return result;
	
	}

	/**
	 * 역활 내역 수정
	 * @param vo 역활관리 Value Object
	 * @return 
	 * @throws Exception
	 */
	public int modifyRole(AuthVO vo) throws Exception {
		vo.setRole_cd(StringUtil.convertHtmlTochars(vo.getRole_cd()));              
		vo.setRole_nm(StringUtil.convertHtmlTochars(vo.getRole_nm()));              
		vo.setComments(StringUtil.convertHtmlTochars(vo.getComments()));            

		return commonDAO.modify("secfw.auth.roleMng.modifyRole", vo);		
	}

	/**
	 * 역활 내역 삭제
	 * @param vo 역활관리 Value Object
	 * @return 
	 * @throws Exception
	 */
	public void deleteRole(AuthVO vo) throws Exception {
		
		String[] chkValues = vo.getChkValues();
		
		for(int i=0; i<chkValues.length; i++) {
		
			String role_cd = chkValues[i];
			vo.setRole_cd(role_cd);
			
			//역활삭제
			commonDAO.delete("secfw.auth.roleMng.deleteRole", vo);
			//사용자-역활삭제
			commonDAO.delete("secfw.auth.authMapping.deleteRoleUser", vo);		
			//역활-권한삭제
			commonDAO.delete("secfw.auth.authMapping.deleteRoleAuthByRoleCd", vo);	
		
		}

	}
}
