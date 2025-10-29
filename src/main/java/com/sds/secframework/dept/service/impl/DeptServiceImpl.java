package com.sds.secframework.dept.service.impl;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.FormatUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.dept.dto.DeptVO;
import com.sds.secframework.dept.service.DeptService;

public class DeptServiceImpl extends CommonServiceImpl implements DeptService {
	
	/**
	 * 부서 목록 조회(Tree형식)
	 * @param dept_nm 부서명
	 * @return 부서리스트 (Tree형식)
	 * @throws Exception
	 */
	public JSONArray listDeptTree(DeptVO vo) throws Exception {
		
		ArrayList listDeptAl = (ArrayList)commonDAO.list("secfw.dept.listDeptTree", vo);

		JSONArray jsonArray = new JSONArray();
		ArrayList[] boxAl = null;

		if(listDeptAl!=null && listDeptAl.size()>0) {
			
			ListOrderedMap levelLom = (ListOrderedMap)listDeptAl.get(0);
			
			int maxLevel = (FormatUtil.formatInt(levelLom.get("tree_max_level")));
			boxAl = new ArrayList[maxLevel];
			
			for(int i=0; i<boxAl.length; i++) {
				boxAl[i] = new ArrayList();
			}
			
			//정보는 array에 담는다.
			for(int i=0; i<listDeptAl.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)listDeptAl.get(i);
				
				String treeKey    = (String)lom.get("tree_key");
				String treeUpKey  = (String)lom.get("tree_up_key");
				String treeTitle  = (String)lom.get("tree_title");
				int    treeLevel  = (FormatUtil.formatInt(lom.get("tree_level")));
				int    arrayLoc   = (FormatUtil.formatInt(lom.get("array_loc")));
				String treeSelect = StringUtil.bvl((String)lom.get("tree_select"),"");
				String treeIsLeaf = (String)lom.get("tree_isleaf");
				
				JSONObject jo = new JSONObject();
				JSONArray  ja = null;
				
				jo.put("title"    , treeTitle);
				jo.put("key"      , treeKey);
				jo.put("treeUpKey", treeUpKey);
				
				String selectMenuId = StringUtil.bvl(vo.getSelect_dept_cd(), "");
				if(selectMenuId.equals(treeKey)) jo.put("activate", new Boolean(true));
				
				if("Y".equals(treeSelect)) jo.put("select", new Boolean(true));
				if("N".equals(treeIsLeaf)) {
					jo.put("isFolder", new Boolean(true));
					jo.put("expand", new Boolean(true));
					ja = new JSONArray();
					jo.put("children", ja);
				}

				ArrayList levelAl = boxAl[arrayLoc];
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
	 * 하위 부서 조회
	 * @param dept_cd 상위부서코드
	 * @return 부서리스트
	 * @throws Exception
	 */
	public JSONArray listChildDept(DeptVO vo) throws Exception {

		ArrayList listDeptAl = (ArrayList)commonDAO.list("secfw.dept.listDeptTree", vo);

		JSONArray jsonArray = new JSONArray();
		ArrayList[] boxAl = null;

		if(listDeptAl!=null && listDeptAl.size()>0) {
			
			ListOrderedMap levelLom = (ListOrderedMap)listDeptAl.get(0);
			
			int maxLevel = (FormatUtil.formatInt(levelLom.get("tree_max_level")));
			boxAl = new ArrayList[maxLevel];
			
			for(int i=0; i<boxAl.length; i++) {
				boxAl[i] = new ArrayList();
			}
			
			//정보는 array에 담는다.
			for(int i=0; i<listDeptAl.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)listDeptAl.get(i);
				
				String treeKey    = (String)lom.get("tree_key");
				String treeUpKey  = (String)lom.get("tree_up_key");
				String treeTitle  = (String)lom.get("tree_title");
				int    treeLevel  = (FormatUtil.formatInt(lom.get("tree_level")));
				int    arrayLoc   = (FormatUtil.formatInt(lom.get("array_loc")));
				String treeSelect = StringUtil.bvl((String)lom.get("tree_select"),"");
				String treeIsLeaf = (String)lom.get("tree_isleaf");
				
				JSONObject jo = new JSONObject();
				JSONArray  ja = null;
				
				jo.put("data"    , treeTitle);
				jo.put("id"      , treeKey);
				jo.put("treeUpKey", treeUpKey);
				
				String selectMenuId = StringUtil.bvl(vo.getSelect_dept_cd(), "");
				
				if("N".equals(treeIsLeaf)) {
					jo.put("state", "close");
					ja = new JSONArray();
					jo.put("children", ja);
				}

				ArrayList levelAl = boxAl[arrayLoc];
				levelAl.add(jo);
			}

			for(int i=maxLevel-1; i-1>=0; i--) {
				
				ArrayList childAl  = boxAl[i];
				ArrayList parentAl = boxAl[i-1];
				
				for(int j=0; j<parentAl.size();j++) {
					
					JSONObject parentJsonObject = (JSONObject)parentAl.get(j);
					String parentTreeKey = (String)parentJsonObject.get("id");
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
		
//		ArrayList listDeptAl = (ArrayList)commonDAO.list("secfw.dept.listChildDept", vo);
//
//		JSONArray jsonArray = new JSONArray();
//
//		if(listDeptAl!=null && listDeptAl.size()>0) {
//			
//			ListOrderedMap levelLom = (ListOrderedMap)listDeptAl.get(0);
//			
//			//정보는 array에 담는다.
//			for(int i=0; i<listDeptAl.size(); i++) {
//				ListOrderedMap lom = (ListOrderedMap)listDeptAl.get(i);
//				
//				String treeKey    = (String)lom.get("tree_key");
//				String treeUpKey  = (String)lom.get("tree_up_key");
//				String treeTitle  = (String)lom.get("tree_title");
//				String treeIsLeaf = (String)lom.get("tree_isleaf");
//				
//				JSONObject jo = new JSONObject();
//				
//				jo.put("data"    , treeTitle);
//				jo.put("attribute", new JSONObject().put("id", treeKey));
//				
//				if("N".equals(treeIsLeaf)) {
//					jo.put("state", "close");
//				} 
//
//				jsonArray.add(jo);
//			}
//		}
//				
//		return jsonArray;	
	}

}
