package com.sds.secframework.main.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sds.secframework.common.service.impl.CommonServiceImpl;
import com.sds.secframework.common.util.StringUtil;
import com.sds.secframework.main.dto.MainVO;
import com.sds.secframework.main.service.MainService;

public class MainServiceImpl extends CommonServiceImpl implements MainService {

	/**
	 * 매뉴 구분에 따라 Top 메뉴, Left 메뉴를 반환한다
	 * 
	 * @param	menuGbn -> TOP  : Top Menu
	 * 				 -> LEFT : Left Menu
	 */
	public HashMap listMenu(HashMap hm) throws Exception {
		
		String menuNm = "";
		String menuNmEng = "";
		String menuNmFra = "";
		String menuNmDeu = "";
		String menuNmIta = "";
		String menuNmEsp = "";
		String menuId = "";
		List menuList = null;
		List menuNmList = null;
		HashMap resultMap = new HashMap();
		
		String menuGbn      = StringUtil.bvl((String)hm.get("menuGbn"),"");
		String userLocale   = StringUtil.bvl((String)hm.get("userLocale"),propertyService.getProperty("secfw.defaultLocale"));
		String targetMenuId = StringUtil.bvl((String)hm.get("targetMenuId"),"");
		String selectMenuId = StringUtil.bvl((String)hm.get("selectedMenuId"),"");
		String sysCd 		= StringUtil.bvl((String)hm.get("sys_cd"),"");
		
		String upMenuId     = "";
		String topMenuLevel = StringUtil.bvl(propertyService.getProperty("secfw.menu.topMenuLevel"), "1");
		
		
		if ("TOP".equals(menuGbn)) {                
			upMenuId = propertyService.getProperty("secfw.menu.topMenuId");
			hm.put("up_menu_id", upMenuId);
			hm.put("topMenuLevel", topMenuLevel);
		} else {
			HashMap paramMap = new HashMap();
			paramMap.put("sys_cd", sysCd);
			//paramMap.put("sys_cd", "CPIS");
			paramMap.put("menu_id", selectMenuId);
			
			int menuLevel = -1;
			do {
				List findList = commonDAO.list("secfw.menu.findUpMenuId", paramMap);

				if (findList != null && findList.size() > 0) {
					ListOrderedMap lom = (ListOrderedMap)findList.get(0);
					menuLevel = Integer.parseInt(StringUtil.bvl(lom.get("menu_level"), "1"));
					
					if (menuLevel == 1) {
						upMenuId = StringUtil.bvl(lom.get("menu_id"), "");
					}else {
						paramMap.put("menu_id", (String)lom.get("up_menu_id"));
						selectMenuId = (String)lom.get("up_menu_id"); //2014.04 jnam 추가 
					}
				}else {
					menuLevel = 1;
					upMenuId = selectMenuId;
				}
			}while (menuLevel != 1);
			
			hm.put("up_menu_id", upMenuId);
		}
		
		//메뉴리스트
		menuList = commonDAO.list("secfw.menu.listMenuAuth", hm);
		resultMap.put("menuList", menuList);
		
		//최상위 메뉴명 
		menuNmList = commonDAO.list("secfw.menu.topMenuNm", hm);
		if(menuNmList != null && menuNmList.size()>0){
			ListOrderedMap mNm = (ListOrderedMap)menuNmList.get(0);
			menuNm = (String)mNm.get("menu_nm");
			menuNmEng = (String)mNm.get("menu_nm_eng");
			menuNmFra = (String)mNm.get("menu_nm_fra");
			menuNmDeu = (String)mNm.get("menu_nm_deu");
			menuNmIta = (String)mNm.get("menu_nm_ita");
			menuNmEsp = (String)mNm.get("menu_nm_esp");
			menuId = (String)mNm.get("menu_id");
		}
		resultMap.put("menuNm", menuNm);
		resultMap.put("menuNmEng", menuNmEng);
		resultMap.put("menuNmFra", menuNmFra);
		resultMap.put("menuNmDeu", menuNmDeu);
		resultMap.put("menuNmIta", menuNmIta);
		resultMap.put("menuNmEsp", menuNmEsp);
		resultMap.put("menuId", menuId);
		
		return resultMap;
	}
	
	/**
	 * sitemap용 메뉴체계 가져오기
	 * @param hm
	 * @return
	 * @throws Exception
	 */
	public List listSiteMapMenu(HashMap hm) throws Exception{
		
		List menuList = null;
		String upMenuId     = "";
		String topMenuLevel = "3";

		upMenuId = propertyService.getProperty("secfw.menu.topMenuId");
		hm.put("up_menu_id", upMenuId);
		hm.put("topMenuLevel", topMenuLevel);

		menuList = commonDAO.list("secfw.menu.listMenuAuth", hm);
	
		return menuList;
	}
	
	/**
	 *  계약관리 시스템 메인 - 각 계약별 건수 조회
	 * @param hm
	 * @return
	 * @throws Exception
	 */	
	public HashMap getClmStatuCount(HashMap hm) throws Exception {
		HashMap resultMap = new HashMap();
		
		List clmList = null;
		
		String[] prcs_depth_arr = {"C02501","C02502","C02503","C02504","C02505"}; //검토,체결,등록,이행,종료
		String[] prcs_depth_cnt_arr = {"0","0","0","0","0", "0"}; //검토,체결,등록,이행,종료, Closed(2014-01-31 Kevin 추가함.)
		
		clmList = commonDAO.list("secfw.menu.getClmStatusCount", hm);
		
		if(clmList != null && clmList.size() > 0) {
			for(int j=0; j<clmList.size(); j++) {
				ListOrderedMap lom = (ListOrderedMap)clmList.get(j);
				
				for(int i=0; i<prcs_depth_arr.length; i++) {
					if(prcs_depth_arr[i].equals((String)lom.get("prcs_depth"))) {
						prcs_depth_cnt_arr[i] = (lom.get("cnt")).toString();
						
						// 2014-01-31 Kevin.
						// Review 단계이면...
						if(prcs_depth_arr[i] == "C02501"){
							// Closed 된 계약 건수를 추출해서
							String strClosedCnt = lom.get("closed").toString();
							// 결과 값 배열 맨 마지막에 저장하고
							prcs_depth_cnt_arr[prcs_depth_cnt_arr.length-1] = strClosedCnt;
							// 조회 된 리뷰 계약 건수에서 - Closed 된 계약 건수를 뺀 후 = 다시 결과 값에 저장.
							prcs_depth_cnt_arr[i] = String.valueOf(Integer.valueOf(lom.get("cnt").toString()) - Integer.valueOf(strClosedCnt));
						}
						break;
					}
				}
			}
		}
		
		for(int i=0; i<prcs_depth_arr.length; i++){
			resultMap.put(prcs_depth_arr[i], prcs_depth_cnt_arr[i]);
		}
		// 2014-01-31 Kevin. 마지막에는 리턴 객체에 "Closed"라는 키로, Closed 된 계약 건수를 저장한다.
		resultMap.put("Closed", prcs_depth_cnt_arr[prcs_depth_cnt_arr.length-1]);
		return resultMap;
	}
	
	/**
	 *  메인이미지 업데이트
	 * @param multiRequest
	 * @return
	 * @throws Exception
	 */	
	public void updateMainImage(MultipartHttpServletRequest multiRequest) throws Exception {
		
		HttpSession session = multiRequest.getSession(false);
		
		String allowFormat = propertyService.getProperty("secfw.image.option.allowFileList");
		String existCompYn = StringUtil.bvl(session.getAttribute("secfw.session.exist_comp_yn"), "");
		String compCd = "Y".equals(existCompYn) ? "_" + StringUtil.bvl(session.getAttribute("secfw.session.comp_cd"), "") : "";
		
		final Map files = multiRequest.getFileMap();
		
		if(files != null){
			String wasDestPath = "";
			Iterator fileIterator = files.values().iterator();
			MultipartFile file;
			while(fileIterator.hasNext()) {
				
				file = (MultipartFile)fileIterator.next();
				String fieldName = file.getName();
				String locale = "mainImgFileEn".equals(fieldName) || "mainBgImgFileEn".equals(fieldName) ? "en" : "ko";
				String imageURL = propertyService.getProperty("secfw.image.url.lasCmnImg", new Object[] {(Object)locale});
				String orgFileNm = file.getOriginalFilename();
				String imgFormat = "";
				String imgNm = "mainImgFileEn".equals(fieldName) || "mainImgFileKo".equals(fieldName) ? "main_img" : "main_img_bg";
				wasDestPath = imageURL;
				// 파일의 확장자가 있을 경우 확인
				if (orgFileNm.lastIndexOf(".") != -1) {
					// 파일의 확장자 정보를 소문자로 변환해서 가져온다.
					 imgFormat = orgFileNm.substring(orgFileNm.lastIndexOf(".") + 1).toLowerCase();
				} else {
					continue;
				}
				
				if (!"".equals(orgFileNm) && checkFileInfo(allowFormat, imgFormat)) {		//허용된 확장자
					// 서버의 저장경로가 없을경우 해당 디렉토리를 생성
					if (!new File(wasDestPath).exists()) {
						new File(imageURL).mkdirs();
					}
					wasDestPath += imgNm + compCd + "." + imgFormat;
					
					file.transferTo(new File(wasDestPath));
				} else {	//허용되지 않은 확장자
					throw new Exception();
				}
			}
		}
	}
	
	/**
	 * ClmsFile : 업로드 가능한 파일 확장자 인지 체크 한다.
	 * @param 업로드 시도한 파일의 확장자
	 * @return true : 허용된 파일형식이 있음, false : 허용된 파일 형식이 없음
	 * @throws Exception
	 */
	private boolean checkFileInfo(String allowInfo,  String fileInfo ) throws Exception {
		boolean result = false;
		
		String allowList = allowInfo;
		StringTokenizer	token = new StringTokenizer(allowList,",");
		
		while (token.hasMoreTokens()) {
			if(fileInfo.equals( token.nextToken().trim().toLowerCase())) {
				result = true;
				break;
			}
		}
		
		return result;		
	}	

	/**
	 *  계약관리 시스템 메인 - 원본미등록(C02642) 건 조회
	 * @param hm
	 * @return
	 * @throws Exception
	 */	
	public String getNotServed(HashMap hm) throws Exception {
		String scnt = "0";
		List clmList = null;
		
		clmList = commonDAO.list("secfw.menu.getNotServed", hm);
		
		if(clmList != null && clmList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)clmList.get(0);
			scnt = (String)(lom.get("cnt")).toString();
			 
		}
		
		return scnt;
	}
	
	/**
	 *  계약관리 시스템 메인 - 자동연장 건 조회
	 * @param hm
	 * @return
	 * @throws Exception
	 */	
	public String countAutoExtension(HashMap hm) throws Exception {
		String scnt = "0";
		List clmList = null;
		
		clmList = commonDAO.list("secfw.menu.getAutoExtension", hm);
		
		if(clmList != null && clmList.size() > 0) {
			ListOrderedMap lom = (ListOrderedMap)clmList.get(0);
			scnt = (String)(lom.get("cnt")).toString();
			 
		}
		
		return scnt;
	}
}
