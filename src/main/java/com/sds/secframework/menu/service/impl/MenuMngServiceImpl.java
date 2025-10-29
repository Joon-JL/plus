package com.sds.secframework.menu.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;

import anyframe.core.idgen.IIdGenerationService;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.DateUtil;
import com.sds.secframework.common.util.FormatUtil;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.menu.dto.MenuMngVO;
import com.sds.secframework.menu.service.MenuMngService;

/**
 * 메뉴관리 Class
 *
 */
public class MenuMngServiceImpl extends CommonServiceImpl implements MenuMngService {
	
	/**
	 * ID 값을 생성하기 위한 idGeneration 선언 및 세팅
	 */
	private IIdGenerationService idGenerationService = null;
	public void setIdGenerationService(IIdGenerationService idGenerationService) {
		this.idGenerationService = idGenerationService;
	}

	/**
	 * 메뉴 목록 Tree타입
	 * @param 
	 * @return 메뉴리스트 JSON
	 * @throws Exception 
	 */
	public JSONArray listMenuTree(MenuMngVO vo) throws Exception {
		
		ArrayList listMenuAl = (ArrayList)commonDAO.list("secfw.menu.listMenuTree", vo);

		JSONArray jsonArray = new JSONArray();
		ArrayList[] boxAl = null;

		if(listMenuAl!=null && listMenuAl.size()>0) {
			
			ListOrderedMap levelLom = (ListOrderedMap)listMenuAl.get(0);
			
			int maxLevel = (FormatUtil.formatInt(levelLom.get("tree_max_level")));
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
				int    treeLevel  = (FormatUtil.formatInt(lom.get("tree_level")));
				int    arrayLoc   = (FormatUtil.formatInt(lom.get("array_loc")));
				String treeSelect = StringUtil.bvl((String)lom.get("tree_select"),"");
				String treeIsLeaf = (String)lom.get("tree_isleaf");
				
				JSONObject jo = new JSONObject();
				JSONArray  ja = null;
				
				jo.put("title"    , treeTitle);
				jo.put("key"      , treeKey);
				jo.put("treeUpKey", treeUpKey);
				
				String selectMenuId = StringUtil.bvl(vo.getSelect_menu_id(), "");
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
	 * 메뉴 목록 Table 타입
	 * @param 
	 * @return 메뉴리스트 JSON
	 * @throws Exception 
	 */
	public JSONArray listMenuTable(MenuMngVO vo) throws Exception {
		
		ArrayList listMenuAl = (ArrayList)commonDAO.list("secfw.menu.listMenuTable", vo);

		JSONArray jsonArray = new JSONArray();

		if(listMenuAl!=null && listMenuAl.size()>0) {
			
			for(int i=0; i<listMenuAl.size(); i++) {
				ListOrderedMap lom = (ListOrderedMap)listMenuAl.get(i);

				JSONObject jsonObject = new JSONObject();

				jsonObject.put("sys_cd"       ,(String)lom.get("sys_cd"));
				jsonObject.put("menu_id"      ,(String)lom.get("menu_id"));
				jsonObject.put("menu_nm"      ,(String)lom.get("menu_nm"));
				jsonObject.put("menu_nm_eng"  ,(String)lom.get("menu_nm_eng"));
				jsonObject.put("menu_nm_cha"  ,(String)lom.get("menu_nm_cha"));
				jsonObject.put("menu_nm_jpn"  ,(String)lom.get("menu_nm_jpn"));
				jsonObject.put("menu_nm_fra"  ,(String)lom.get("menu_nm_fra"));
				jsonObject.put("menu_nm_deu"  ,(String)lom.get("menu_nm_deu"));
				jsonObject.put("menu_nm_ita"  ,(String)lom.get("menu_nm_ita"));
				jsonObject.put("menu_nm_esp"  ,(String)lom.get("menu_nm_esp"));
				jsonObject.put("up_menu_id"   ,(String)lom.get("up_menu_id"));
				jsonObject.put("menu_level"   ,FormatUtil.formatNumToString(lom.get("menu_level")));
				jsonObject.put("menu_order"   ,FormatUtil.formatNumToString(lom.get("menu_order")));
				jsonObject.put("comments"     ,(String)lom.get("comments"));
				jsonObject.put("use_yn"       ,(String)lom.get("use_yn"));
				jsonObject.put("menu_url"     ,(String)lom.get("menu_url"));
				jsonObject.put("menu_type"    ,(String)lom.get("menu_type"));
				jsonObject.put("authcheck_yn" ,(String)lom.get("authcheck_yn"));
				jsonObject.put("menu_img"     ,(String)lom.get("menu_img"));
				jsonObject.put("menu_img_eng" ,(String)lom.get("menu_img_eng"));
				jsonObject.put("menu_img_cha" ,(String)lom.get("menu_img_cha"));
				jsonObject.put("menu_img_jpn" ,(String)lom.get("menu_img_jpn"));
				jsonObject.put("popup_nm"     ,(String)lom.get("popup_nm"));
				jsonObject.put("popup_width"  ,FormatUtil.formatNumToString(lom.get("popup_width")));
				jsonObject.put("popup_height" ,FormatUtil.formatNumToString(lom.get("popup_height")));
				jsonObject.put("display_yn"   ,(String)lom.get("display_yn"));
				jsonObject.put("total_cnt"    ,FormatUtil.formatNumToString(lom.get("total_cnt")));

				jsonArray.add(jsonObject);
			}
		}
		
		return jsonArray;			
	}

	/**
	 * 메뉴를 등록한다.
	 * @param menuVO 메뉴VO
	 * @return
	 * @throws Exception
	 */
	public JSONArray insertMenu(MenuMngVO vo) throws Exception {
		
		int result = 0;

		  String[] menu_ids       = vo.getMenu_ids();         // 메뉴ID
		  String[] menu_nms       = vo.getMenu_nms();         // 메뉴명
		  String[] menu_nm_engs   = vo.getMenu_nm_engs();     // 메뉴영문명
		  String[] menu_nm_chas   = vo.getMenu_nm_chas();     // 메뉴중문명
		  String[] menu_nm_jpns   = vo.getMenu_nm_jpns();     // 메뉴일문명
		  String[] menu_nm_fras   = vo.getMenu_nm_fras();     // 메뉴불문명
		  String[] menu_nm_deus   = vo.getMenu_nm_deus();     // 메뉴독문명
		  String[] menu_nm_itas   = vo.getMenu_nm_itas();     // 메뉴이문명
		  String[] menu_nm_esps   = vo.getMenu_nm_esps();     // 메뉴스페인어문명
		  String[] menu_orders    = vo.getMenu_orders();      // 메뉴순서
		  String[] commentss      = vo.getCommentss();        // 설명
		  String[] use_yns        = vo.getUse_yns();          // 사용여부
		  String[] menu_urls      = vo.getMenu_urls();        // 메뉴URL
		  String[] menu_types     = vo.getMenu_types();       // 메뉴타입(M:메뉴,P:팝업페이지)
		  String[] authcheck_yns  = vo.getAuthcheck_yns();    // 권한체크여부
		  String[] menu_imgs      = vo.getMenu_imgs();        // 메뉴이미지
		  String[] menu_img_engs  = vo.getMenu_img_engs();    // 메뉴영문이미지
		  String[] menu_img_chas  = vo.getMenu_img_chas();    // 메뉴중문이미지
		  String[] menu_img_jpns  = vo.getMenu_img_jpns();    // 메뉴일문이미지
		  String[] popup_nms      = vo.getPopup_nms();        // 팝업명
		  String[] popup_widths   = vo.getPopup_widths();     // 팝업가로사이즈
		  String[] popup_heights  = vo.getPopup_heights();    // 팝업세로사이즈
		  String[] display_yns    = vo.getDisplay_yns();      // 표시여부
		  
		if(menu_ids!=null && menu_ids.length>0) {
			for(int i=0; i<menu_ids.length; i++) {

				String menuId = StringUtil.bvl(menu_ids[i],"");
				
				vo.setMenu_id(menu_ids[i]);              // 메뉴ID
				vo.setMenu_nm(StringUtil.convertHtmlTochars(menu_nms[i]));              // 메뉴명
				vo.setMenu_nm_eng(StringUtil.convertHtmlTochars(menu_nm_engs[i]));      // 메뉴영문명
				vo.setMenu_nm_cha(StringUtil.convertHtmlTochars(menu_nm_chas[i]));      // 메뉴중문명
				vo.setMenu_nm_jpn(StringUtil.convertHtmlTochars(menu_nm_jpns[i]));      // 메뉴일문명
				vo.setMenu_nm_fra(StringUtil.convertHtmlTochars(menu_nm_fras[i]));      // 메뉴불문명
				vo.setMenu_nm_deu(StringUtil.convertHtmlTochars(menu_nm_deus[i]));      // 메뉴독문명
				vo.setMenu_nm_ita(StringUtil.convertHtmlTochars(menu_nm_itas[i]));      // 메뉴이문명
				vo.setMenu_nm_esp(StringUtil.convertHtmlTochars(menu_nm_esps[i]));      // 메뉴스페인어문명
				vo.setMenu_order(Integer.parseInt(StringUtil.bvl(menu_orders[i],"1")));        // 메뉴순서
				vo.setComments(StringUtil.convertHtmlTochars(commentss[i]));            // 설명
				vo.setUse_yn(use_yns[i]);                // 사용여부
				vo.setMenu_url(menu_urls[i]);            // 메뉴URL
				vo.setMenu_type(menu_types[i]);          // 메뉴타입(M:메뉴,P:페이지)
				vo.setMenu_img(StringUtil.convertHtmlTochars(menu_imgs[i]));            // 메뉴이미지
				vo.setMenu_img_eng(StringUtil.convertHtmlTochars(menu_img_engs[i]));    // 메뉴영문이미지
				vo.setMenu_img_cha(StringUtil.convertHtmlTochars(menu_img_chas[i]));    // 메뉴중문이미지
				vo.setMenu_img_jpn(StringUtil.convertHtmlTochars(menu_img_jpns[i]));    // 메뉴일문이미지
				vo.setPopup_nm(StringUtil.convertHtmlTochars(popup_nms[i]));            // 팝업명
				vo.setPopup_width(Integer.parseInt(StringUtil.bvl(popup_widths[i],"0")));      // 팝업가로사이즈
				vo.setPopup_height(Integer.parseInt(StringUtil.bvl(popup_heights[i],"0")));   // 팝업세로사이즈
				vo.setDisplay_yn(display_yns[i]);        // 표시여부
				
				if("".equals(menuId)) {

					//메뉴아이디 생성, 상위메뉴아이딧세팅
					String getMenuId = DateUtil.getTime("yyyyMMddHHmmssSSS") + idGenerationService.getNextStringId();
					vo.setMenu_id(getMenuId);
					vo.setUp_menu_id(vo.getSelect_menu_id());
					
					//메뉴레벨
					List upMenuList = commonDAO.list("secfw.menu.detailMenu", vo);
					int upMenuLevel = 0;
					if(upMenuList!=null && upMenuList.size()>0) {
						ListOrderedMap lom = (ListOrderedMap)upMenuList.get(0);
						upMenuLevel = (FormatUtil.formatInt(lom.get("menu_level")));
					} else {
						upMenuLevel = 0;
					}
					
					vo.setMenu_level(upMenuLevel + 1);
					
					result += commonDAO.insert("secfw.menu.insertMenu", vo);
				} else {
					result += modifyMenu(vo);
				}
			}
		}
		
		vo.setMenu_id(vo.getSelect_menu_id());
		return listMenuTree(vo);
	}

	/**
	 * 메뉴정보를 수정한다.
	 * 1. 해당메뉴 정보 수정
	 * 2. 하위메뉴 상태정보 수정
	 * @param menuVO 메뉴VO
	 * @return
	 * @throws Exception
	 */
	public int modifyMenu(MenuMngVO vo) throws Exception {
		
		int result = 0;

		vo.setMenu_nm(StringUtil.convertHtmlTochars(vo.getMenu_nm()));              // 메뉴명
		vo.setMenu_nm_eng(StringUtil.convertHtmlTochars(vo.getMenu_nm_eng()));      // 메뉴영문명
		vo.setMenu_nm_cha(StringUtil.convertHtmlTochars(vo.getMenu_nm_cha()));      // 메뉴중문명
		vo.setMenu_nm_jpn(StringUtil.convertHtmlTochars(vo.getMenu_nm_jpn()));      // 메뉴일문명
		vo.setMenu_nm_fra(StringUtil.convertHtmlTochars(vo.getMenu_nm_fra()));      // 메뉴일문명
		vo.setMenu_nm_deu(StringUtil.convertHtmlTochars(vo.getMenu_nm_deu()));      // 메뉴일문명
		vo.setMenu_nm_ita(StringUtil.convertHtmlTochars(vo.getMenu_nm_ita()));      // 메뉴일문명
		vo.setMenu_nm_esp(StringUtil.convertHtmlTochars(vo.getMenu_nm_esp()));      // 메뉴일문명
		vo.setComments(StringUtil.convertHtmlTochars(vo.getComments()));            // 설명
		vo.setMenu_img(StringUtil.convertHtmlTochars(vo.getMenu_img()));            // 메뉴이미지
		vo.setMenu_img_eng(StringUtil.convertHtmlTochars(vo.getMenu_img_eng()));    // 메뉴영문이미지
		vo.setMenu_img_cha(StringUtil.convertHtmlTochars(vo.getMenu_img_cha()));    // 메뉴중문이미지
		vo.setMenu_img_jpn(StringUtil.convertHtmlTochars(vo.getMenu_img_jpn()));    // 메뉴일문이미지
		vo.setPopup_nm(StringUtil.convertHtmlTochars(vo.getPopup_nm()));            // 팝업명

		//메뉴정보 수정
		result += commonDAO.modify("secfw.menu.modifyMenu", vo);
		
		//하위메뉴 사용여부 'N'으로 설정 
		if("N".equals(vo.getUse_yn())) 
			result += commonDAO.modify("secfw.menu.modifyChildMenuUse", vo);
		//하위메뉴 표시여부 'N'으로 설정 
		if("N".equals(vo.getDisplay_yn())) 
			result += commonDAO.modify("secfw.menu.modifyChildMenuDisplay", vo);
		
		return result;
	}

	/**
	 * 메뉴를 삭제.
	 * @param menuVO 메뉴VO
	 * @return
	 * @throws Exception
	 */
	public int deleteMenu(MenuMngVO vo) throws Exception {

		int result = 0;
		String[] mappingInfo = vo.getChk_id();

		if(mappingInfo!=null && mappingInfo.length>0) {
			for(int i=0; i<mappingInfo.length; i++) {
				vo.setMenu_id(mappingInfo[i]);
				result += commonDAO.delete("secfw.menu.deleteMenu", vo);	
			}
		}
		
		return result;			
	}
	
	
}
