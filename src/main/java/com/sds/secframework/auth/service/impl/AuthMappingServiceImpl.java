package com.sds.secframework.auth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.auth.dto.AuthVO;
import com.sds.secframework.auth.service.AuthMappingService;
import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.FormatUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.user.dto.UserVO;

public class AuthMappingServiceImpl extends CommonServiceImpl implements AuthMappingService{

	/**
	 * 역할목록조회
	 * @param AuthVO 권한관리 Value Object
	 * @return 역할 목록
	 * @throws Exception
	 */
	public JSONArray listRole(HashMap hm) throws Exception {
		return JSONArray.fromObject(commonDAO.list("secfw.auth.roleMng.listRole", hm));
	}

	/**
	 * 사용자목록조회
	 * @param AuthVO 권한관리 Value Object
	 * @return 역할 목록
	 * @throws Exception
	 */
	public JSONArray listUser(HashMap hm) throws Exception {
		UserVO vo = new UserVO();
		
		vo.setSrch_user_cntnt_type((String)hm.get("srch_cntnt_type"));
		vo.setSrch_user_cntnt((String)hm.get("srch_cntnt"));
		
		return JSONArray.fromObject(commonDAO.list("secfw.user.listUser", vo));
	}
	
	/**
	 * 역할-사용자 목록조회
	 * @param AuthVO 권한관리 Value Object
	 * @return 역할-사용자 목록
	 * @throws Exception
	 */
	public JSONArray listRoleUser(AuthVO vo) throws Exception {
		return JSONArray.fromObject(commonDAO.list("secfw.auth.authMapping.listRoleUser", vo));
	}

	/**
	 * 역할-사용자 등록
	 * @param AuthVO 권한관리 Value Object
	 * @return 등록결과
	 * @throws Exception
	 */
	public void insertRoleUser(AuthVO vo) throws Exception {
		
		String[] mappingInfo = vo.getMapping_user_id();
		
		//역할별 사용자 삭제
		deleteRoleUser(vo);
		
		if(mappingInfo!=null && mappingInfo.length>0) {
			for(int i=0; i<mappingInfo.length; i++) {
				vo.setUser_id(mappingInfo[i]);
				commonDAO.insert("secfw.auth.authMapping.insertRoleUser", vo);
			}
		}
	}

	/**
	 * 역할별 사용자 삭제
	 * @param AuthVO 권한관리 Value Object
	 * @return 삭제결과
	 * @throws Exception
	 */
	public void deleteRoleUser(AuthVO vo) throws Exception {
		commonDAO.delete("secfw.auth.authMapping.deleteRoleUser", vo);
	}

	/**
	 * 권한목록조회
	 * @param hm 권한관리 Value Object
	 * @return 역할 목록
	 * @throws Exception
	 */
	public JSONArray listAuth(HashMap hm) throws Exception {
		return JSONArray.fromObject(commonDAO.list("secfw.auth.authMng.listAuth", hm));
	}
	
	/**
	 * 역할-권한 목록조회
	 * @param AuthVO 권한관리 Value Object
	 * @return 역할-사용자 목록
	 * @throws Exception
	 */
	public JSONArray listRoleAuth(AuthVO vo) throws Exception {
		return JSONArray.fromObject(commonDAO.list("secfw.auth.authMapping.listRoleAuth", vo));
	}

	/**
	 * 역할-권한 등록
	 * @param AuthVO 권한관리 Value Object
	 * @return 등록결과
	 * @throws Exception
	 */
	public void insertRoleAuth(AuthVO vo) throws Exception {
		
		String[] mappingInfo = vo.getMapping_auth_cd();
		
		//역할별 권한 삭제
		deleteRoleAuth(vo);
		
		if(mappingInfo!=null && mappingInfo.length>0) {
			for(int i=0; i<mappingInfo.length; i++) {
				vo.setAuth_cd(mappingInfo[i]);
				commonDAO.insert("secfw.auth.authMapping.insertRoleAuth", vo);
			}
		}
	}

	/**
	 * 역할별 권한 삭제
	 * @param AuthVO 권한관리 Value Object
	 * @return 삭제결과
	 * @throws Exception
	 */
	public void deleteRoleAuth(AuthVO vo) throws Exception {
		commonDAO.delete("secfw.auth.authMapping.deleteRoleAuthByRoleCd", vo);
	}
	
	/**
	 * 메뉴목록조회
	 * @param hm 권한관리
	 * @return 역할-사용자 목록
	 * @throws Exception
	 */
	public JSONArray listMenuAuth(HashMap hm) throws Exception {
		
		ArrayList listMenuAl = (ArrayList)commonDAO.list("secfw.auth.authMapping.listMenuAuth", hm);

		JSONArray jsonArray = new JSONArray();
		ArrayList[] boxAl = null;

		if(listMenuAl!=null && listMenuAl.size()>0) {
			
			ListOrderedMap levelLom = (ListOrderedMap)listMenuAl.get(0);
			
			int maxLevel = FormatUtil.formatInt(levelLom.get("tree_max_level"));
			boxAl = new ArrayList[maxLevel];
			
			for(int i=0; i<boxAl.length; i++) {
				boxAl[i] = new ArrayList();
			}
			
			//메뉴정보는 array에 담는다.
			for(int i=0; i<listMenuAl.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)listMenuAl.get(i);
				
				String treeKey    = (String)lom.get("tree_key");
				String treeUpKey  = (String)lom.get("tree_up_key");
				String treeTitle  = (String)lom.get("tree_title");
				int    treeLevel  = FormatUtil.formatInt(lom.get("tree_level"));
				String treeSelect = StringUtil.bvl((String)lom.get("tree_select"),"");
				String treeIsLeaf = (String)lom.get("tree_isleaf");
				
				JSONObject jo = new JSONObject();
				JSONArray  ja = null;
				
				jo.put("title"    , treeTitle);
				jo.put("key"      , treeKey);
				jo.put("treeUpKey", treeUpKey);
				
				if("Y".equals(treeSelect)) jo.put("select", new Boolean(true));
				if("N".equals(treeIsLeaf)) {
					jo.put("isFolder", new Boolean(true));
					jo.put("expand", new Boolean(true));
					ja = new JSONArray();
					jo.put("children", ja);
				}

				ArrayList levelAl = boxAl[treeLevel-1];
				levelAl.add(jo);
			}

			for(int i=maxLevel-1; i-1>=0; i--) {
				
				ArrayList childAl  = boxAl[i];
				ArrayList parentAl = boxAl[i-1];
				
				for(int j=0; j<parentAl.size();j++) {
					
					JSONObject parentJsonObject = (JSONObject)parentAl.get(j);
					String parentTreeKey = (String)parentJsonObject.get("key");
					JSONArray parentJsonArray = (JSONArray)parentJsonObject.get("children");
					
					for(int k=0; k<childAl.size();k++) {
						
						JSONObject childJsonObject = (JSONObject)childAl.get(k);
						String childTreeUpKey = (String)childJsonObject.get("treeUpKey");

						if(parentTreeKey.equals(childTreeUpKey)) {
							parentJsonArray.add(childJsonObject);
					
						}
						
					}
				}
			}
		}

		if(boxAl != null && boxAl.length>0){
			
			ArrayList al = boxAl[0];
			
			for(int i=0; i<al.size();i++){
				JSONObject jsonObject = (JSONObject)al.get(i);
				jsonArray.add(jsonObject);
			}
		}

		return jsonArray;
	}

	/**
	 * 메뉴-권한 등록
	 * @param AuthVO 권한관리 Value Object
	 * @return 등록결과
	 * @throws Exception
	 */
	public void insertMenuAuth(AuthVO vo) throws Exception {
		
		String[] mappingInfo = vo.getMapping_menu_id();
		
		//메뉴별 권한 삭제
		deleteMenuAuth(vo);
		
		if(mappingInfo!=null && mappingInfo.length>0) {
			for(int i=0; i<mappingInfo.length; i++) {
				vo.setMenu_id(mappingInfo[i]);
				commonDAO.insert("secfw.auth.authMapping.insertMenuAuth", vo);
			}
		}
		
		//Access Control 적용
		insertAccessControl(vo);
	}

	/**
	 * 메뉴 Access Control 조회
	 * @param AuthVO 권한관리 Value Object
	 * @return 등록결과
	 * @throws Exception
	 */
	public String getAccessControl(AuthVO vo) throws Exception {

		String result = "";
		List temp = commonDAO.list("secfw.auth.authMapping.getAccessControl", vo);
		if(temp != null && temp.size()>0){
			ListOrderedMap lom = (ListOrderedMap)temp.get(0);
			result = (String)lom.get("access_control");
		}
		return result;		
	}
	
	/**
	 * 메뉴 Access Control 등록
	 * @param AuthVO 권한관리 Value Object
	 * @return 등록결과
	 * @throws Exception
	 */
	public void insertAccessControl(AuthVO vo) throws Exception {

		//기존 데이타 존재여부 체크
		List temp = commonDAO.list("secfw.auth.authMapping.getAccessControl", vo);

		if(temp != null && temp.size()>0){
			commonDAO.insert("secfw.auth.authMapping.modifyAccessControl", vo);
		} else {
			commonDAO.insert("secfw.auth.authMapping.insertMenuAuth", vo);
		}
		
	}
	
	/**
	 * 메뉴별 권한 삭제
	 * @param AuthVO 권한관리 Value Object
	 * @return 삭제결과
	 * @throws Exception
	 */
	public void deleteMenuAuth(AuthVO vo) throws Exception {
		commonDAO.delete("secfw.auth.authMapping.deleteMenuAuth", vo);
	}

	/**
	 * 메소드목록조회(권한별)
	 * @param hm 권한
	 * @return 역할-사용자 목록
	 * @throws Exception
	 */
	public JSONArray listMethodAuth(HashMap hm) throws Exception {
		
		ArrayList listMethodAl = (ArrayList)commonDAO.list("secfw.auth.authMapping.listMethodAuth", hm);

		JSONArray jsonArray = new JSONArray();
		ArrayList[] boxAl = null;

		if(listMethodAl!=null && listMethodAl.size()>0) {
			
			ListOrderedMap levelLom = (ListOrderedMap)listMethodAl.get(0);
			
			int maxLevel = FormatUtil.formatInt(levelLom.get("tree_max_level"));
			boxAl = new ArrayList[maxLevel];
			
			for(int i=0; i<boxAl.length; i++) {
				boxAl[i] = new ArrayList();
			}
			
			//메뉴정보는 array에 담는다.
			for(int i=0; i<listMethodAl.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)listMethodAl.get(i);
				
				String treeKey    = (String)lom.get("tree_key");
				String treeUpKey  = (String)lom.get("tree_up_key");
				String treeTitle  = (String)lom.get("tree_title");
				int    treeLevel  = FormatUtil.formatInt(lom.get("tree_level"));
				String treeSelect = StringUtil.bvl((String)lom.get("tree_select"),"");
				String treeIsLeaf = (String)lom.get("tree_isleaf");
				
				JSONObject jo = new JSONObject();
				JSONArray  ja = null;
				
				jo.put("title"    , treeTitle);
				jo.put("key"      , treeKey);
				jo.put("treeUpKey", treeUpKey);
				
				if("Y".equals(treeSelect)) jo.put("select", new Boolean(true));
				if("N".equals(treeIsLeaf)) {
					jo.put("isFolder", new Boolean(true));
					jo.put("expand", new Boolean(true));
					ja = new JSONArray();
					jo.put("children", ja);
				}

				ArrayList levelAl = boxAl[treeLevel-1];
				levelAl.add(jo);
			}

			for(int i=maxLevel-1; i-1>=0; i--) {
				
				ArrayList childAl  = boxAl[i];
				ArrayList parentAl = boxAl[i-1];
				
				for(int j=0; j<parentAl.size();j++) {
					
					JSONObject parentJsonObject = (JSONObject)parentAl.get(j);
					String parentTreeKey = (String)parentJsonObject.get("key");
					JSONArray parentJsonArray = (JSONArray)parentJsonObject.get("children");
					
					for(int k=0; k<childAl.size();k++) {
						
						JSONObject childJsonObject = (JSONObject)childAl.get(k);
						String childTreeUpKey = (String)childJsonObject.get("treeUpKey");

						if(parentTreeKey.equals(childTreeUpKey)) {
							parentJsonArray.add(childJsonObject);
					
						}
						
					}
				}
			}
		}

		if(boxAl != null && boxAl.length>0){
			
			ArrayList al = boxAl[0];
			
			for(int i=0; i<al.size();i++){
				JSONObject jsonObject = (JSONObject)al.get(i);
				jsonArray.add(jsonObject);
			}
		}

		return jsonArray;
	}

	/**
	 * 메소드-권한 등록
	 * @param AuthVO 권한관리 Value Object
	 * @return 등록결과
	 * @throws Exception
	 */
	public void insertMethodAuth(AuthVO vo) throws Exception {
		
		String[] mappingMethodInfos = vo.getMapping_method_infos();
		
		//메소드별 권한 삭제
		deleteMethodAuth(vo);
		
		if(mappingMethodInfos!=null && mappingMethodInfos.length>0) {
			for(int i=0; i<mappingMethodInfos.length; i++) {
				
				String[] mappingMethodInfo = StringUtil.split(mappingMethodInfos[i], ":");
				
				vo.setPackage_nm(mappingMethodInfo[0]);
				vo.setClass_nm(mappingMethodInfo[1]);
				vo.setMethod_nm(mappingMethodInfo[2]);
				
				commonDAO.insert("secfw.auth.authMapping.insertMethodAuth", vo);
			}
		}
		
	}
	
	/**
	 * 메소드별 권한 삭제
	 * @param AuthVO 권한관리 Value Object
	 * @return 삭제결과
	 * @throws Exception
	 */
	public void deleteMethodAuth(AuthVO vo) throws Exception {
		commonDAO.delete("secfw.auth.authMapping.deleteMethodAuth", vo);
	}

	/**
	 * 페이지목록조회
	 * @param AuthVO 권한관리 Value Object
	 * @return 역할-사용자 목록
	 * @throws Exception
	 */
	public JSONArray listPage(AuthVO vo) throws Exception {
		
		List list = commonDAO.list("secfw.auth.authMapping.listPage", vo);

		JSONArray jsonArray = new JSONArray();

		if(list!=null && list.size()>0) {
			
			for(int i=0; i<list.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)list.get(i);
				
				JSONObject jsonObject = new JSONObject();

				jsonObject.put("sys_cd"         ,(String)lom.get("sys_cd"));
				jsonObject.put("page_id"        ,(String)lom.get("page_id"));
				jsonObject.put("page_nm"        ,(String)lom.get("page_nm"));
				jsonObject.put("page_url"       ,(String)lom.get("page_url"));
				jsonObject.put("use_yn"         ,(String)lom.get("use_yn"));
				jsonObject.put("authcheck_yn"   ,(String)lom.get("authcheck_yn"));
				jsonObject.put("developer_nm"   ,(String)lom.get("developer_nm"));
				jsonObject.put("comments"       ,(String)lom.get("comments"));
				jsonObject.put("total_cnt"      ,FormatUtil.formatNumToString(lom.get("total_cnt")));

				jsonArray.add(jsonObject);
			}
		}

		return jsonArray;
	}
	
	/**
	 * 권한-페이지목록조회
	 * @param AuthVO 권한관리 Value Object
	 * @return 역할-사용자 목록
	 * @throws Exception
	 */
	public JSONArray listPageAuth(AuthVO vo) throws Exception {
		
		List list = commonDAO.list("secfw.auth.authMapping.listPageAuth", vo);

		JSONArray jsonArray = new JSONArray();

		if(list!=null && list.size()>0) {
			
			for(int i=0; i<list.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)list.get(i);
				
				JSONObject jsonObject = new JSONObject();

				jsonObject.put("sys_cd"         ,(String)lom.get("sys_cd"));
				jsonObject.put("page_id"        ,(String)lom.get("page_id"));
				jsonObject.put("page_nm"        ,(String)lom.get("page_nm"));
				jsonObject.put("page_url"       ,(String)lom.get("page_url"));
				jsonObject.put("use_yn"         ,(String)lom.get("use_yn"));
				jsonObject.put("authcheck_yn"   ,(String)lom.get("authcheck_yn"));
				jsonObject.put("developer_nm"   ,(String)lom.get("developer_nm"));
				jsonObject.put("comments"       ,(String)lom.get("comments"));
				jsonObject.put("access_control" ,(String)lom.get("access_control"));
				jsonObject.put("total_cnt"      ,FormatUtil.formatNumToString(lom.get("total_cnt")));

				jsonArray.add(jsonObject);
			}
		}

		return jsonArray;
	}

	/**
	 * 페이지-권한 등록
	 * @param AuthVO 권한관리 Value Object
	 * @return 등록결과
	 * @throws Exception
	 */
	public void insertPageAuth(AuthVO vo) throws Exception {
		
		String[] mappingInfo = vo.getMapping_page_id();
		
		//페이지별 권한 삭제
		deletePageAuth(vo);
		
		if(mappingInfo!=null && mappingInfo.length>0) {
			for(int i=0; i<mappingInfo.length; i++) {
				//vo.setPage_id(mappingInfo[i]);
				commonDAO.insert("secfw.auth.authMapping.insertPageAuth", vo);
			}
		}
	}

	/**
	 * 페이지별 권한 삭제
	 * @param AuthVO 권한관리 Value Object
	 * @return 삭제결과
	 * @throws Exception
	 */
	public void deletePageAuth(AuthVO vo) throws Exception {
		commonDAO.delete("secfw.auth.authMapping.deletePageAuth", vo);
	}
	
	
	/**
	 * 사별 메뉴목록조회
	 * @param hm 권한관리
	 * @return 역할-사용자 목록
	 * @throws Exception
	 */
	public JSONArray listCompMenuAuth(HashMap hm) throws Exception {
		
		ArrayList listMenuAl = (ArrayList)commonDAO.list("secfw.auth.authMapping.listCompMenuAuth", hm);

		JSONArray jsonArray = new JSONArray();
		ArrayList[] boxAl = null;

		if(listMenuAl!=null && listMenuAl.size()>0) {
			
			ListOrderedMap levelLom = (ListOrderedMap)listMenuAl.get(0);
			
			int maxLevel = FormatUtil.formatInt(levelLom.get("tree_max_level"));
			boxAl = new ArrayList[maxLevel];
			
			for(int i=0; i<boxAl.length; i++) {
				boxAl[i] = new ArrayList();
			}
			
			//메뉴정보는 array에 담는다.
			for(int i=0; i<listMenuAl.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)listMenuAl.get(i);
				
				String treeKey    = (String)lom.get("tree_key");
				String treeUpKey  = (String)lom.get("tree_up_key");
				String treeTitle  = (String)lom.get("tree_title");
				int    treeLevel  = FormatUtil.formatInt(lom.get("tree_level"));
				String treeSelect = StringUtil.bvl((String)lom.get("tree_select"),"");
				String treeIsLeaf = (String)lom.get("tree_isleaf");
				
				JSONObject jo = new JSONObject();
				JSONArray  ja = null;
				
				jo.put("title"    , treeTitle);
				jo.put("key"      , treeKey);
				jo.put("treeUpKey", treeUpKey);
				
				if("Y".equals(treeSelect)) jo.put("select", new Boolean(true));
				if("N".equals(treeIsLeaf)) {
					jo.put("isFolder", new Boolean(true));
					jo.put("expand", new Boolean(true));
					ja = new JSONArray();
					jo.put("children", ja);
				}

				ArrayList levelAl = boxAl[treeLevel-1];
				levelAl.add(jo);
			}

			for(int i=maxLevel-1; i-1>=0; i--) {
				
				ArrayList childAl  = boxAl[i];
				ArrayList parentAl = boxAl[i-1];
				
				for(int j=0; j<parentAl.size();j++) {
					
					JSONObject parentJsonObject = (JSONObject)parentAl.get(j);
					String parentTreeKey = (String)parentJsonObject.get("key");
					JSONArray parentJsonArray = (JSONArray)parentJsonObject.get("children");
					
					for(int k=0; k<childAl.size();k++) {
						
						JSONObject childJsonObject = (JSONObject)childAl.get(k);
						String childTreeUpKey = (String)childJsonObject.get("treeUpKey");

						if(parentTreeKey.equals(childTreeUpKey)) {
							parentJsonArray.add(childJsonObject);
					
						}
						
					}
				}
			}
		}

		if(boxAl != null && boxAl.length>0){
			
			ArrayList al = boxAl[0];
			
			for(int i=0; i<al.size();i++){
				JSONObject jsonObject = (JSONObject)al.get(i);
				jsonArray.add(jsonObject);
			}
		}

		return jsonArray;
	}

	/**
	 * 사별 메뉴-권한 등록
	 * @param AuthVO 권한관리 Value Object
	 * @return 등록결과
	 * @throws Exception
	 */
	public void insertCompMenuAuth(AuthVO vo) throws Exception {
		
		String[] mappingInfo = vo.getMapping_menu_id();
		
		//메뉴별 권한 삭제
		deleteCompMenuAuth(vo);
		
		if(mappingInfo!=null && mappingInfo.length>0) {
			for(int i=0; i<mappingInfo.length; i++) {
				vo.setMenu_id(mappingInfo[i]);
				commonDAO.insert("secfw.auth.authMapping.insertCompMenuAuth", vo);
			}
		}
		
		//Access Control 적용
		insertAccessControl2(vo);
	}
	
	/**
	 * 사별 메뉴 권한 삭제
	 * @param AuthVO 권한관리 Value Object
	 * @return 삭제결과
	 * @throws Exception
	 */
	public void deleteCompMenuAuth(AuthVO vo) throws Exception {
		commonDAO.delete("secfw.auth.authMapping.deleteCompMenuAuth", vo);
	}
	
	/**
	 * 사별 메뉴 Access Control 등록
	 * @param AuthVO 권한관리 Value Object
	 * @return 등록결과
	 * @throws Exception
	 */
	public void insertAccessControl2(AuthVO vo) throws Exception {

		//기존 데이타 존재여부 체크
		List temp = commonDAO.list("secfw.auth.authMapping.getAccessControl2", vo);

		if(temp != null && temp.size()>0){
			commonDAO.insert("secfw.auth.authMapping.modifyAccessControl2", vo);
		} else {
			commonDAO.insert("secfw.auth.authMapping.insertCompMenuAuth", vo);
		}
		
	}
	
	/**
	 * 사용자목록조회
	 * @param AuthVO 권한관리 Value Object
	 * @return 역할 목록
	 * @throws Exception
	 */
	public JSONArray listUserByRole(HashMap hm) throws Exception {
		UserVO vo = new UserVO();
		
		vo.setSrch_user_cntnt_type((String)hm.get("srch_cntnt_type"));
		vo.setSrch_user_cntnt((String)hm.get("srch_cntnt"));
		vo.setRole_cd((String)hm.get("role_cd"));
		
		return JSONArray.fromObject(commonDAO.list("secfw.user.listUserByRole", vo));
	}
	
	/**
	 * 전자검토자 등록
	 * @param AuthVO 권한관리 Value Object
	 * @return 등록결과
	 * @throws Exception
	 */
	public void insertMngComp(AuthVO vo) throws Exception {
		
		String[] mappingInfo = vo.getMapping_comp_cd();
		
		//역할별 사용자 삭제
		deleteMngComp(vo);
		
		if(mappingInfo!=null && mappingInfo.length>0) {
			for(int i=0; i<mappingInfo.length; i++) {
				vo.setMng_comp_cd(mappingInfo[i]);
				commonDAO.insert("secfw.auth.authMapping.insertMngComp", vo);
			}
		}
	}
	
	/**
	 * 전자검토자 삭제
	 * @param AuthVO 권한관리 Value Object
	 * @return 삭제결과
	 * @throws Exception
	 */
	public void deleteMngComp(AuthVO vo) throws Exception {
		commonDAO.delete("secfw.auth.authMapping.deleteMngComp", vo);
	}
	
	/**
	 * 관리회사 목록조회
	 * @param AuthVO 권한관리 Value Object
	 * @return 역할-사용자 목록
	 * @throws Exception
	 */
	public JSONArray listMngComp(AuthVO vo) throws Exception {
		return JSONArray.fromObject(commonDAO.list("secfw.auth.authMapping.listMngComp", vo));
	}
}
