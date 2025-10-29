package com.sds.secframework.main.service;

import java.util.HashMap;
import java.util.List;
import com.sds.secframework.main.dto.MainVO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface MainService {

	/**
	 * 매뉴 구분에 따라 Top 메뉴, Left 메뉴를 반환한다
	 * 
	 * @param	menuGbn  -> TOP  : Top Menu
	 * 					 -> LEFT : Left Menu
	 */
	HashMap listMenu(HashMap hm) throws Exception;
	
	
	/**
	 * sitemap용 메뉴체계 가져오기
	 * @param hm
	 * @return
	 * @throws Exception
	 */
	List listSiteMapMenu(HashMap hm) throws Exception;
	
	/**
	 *  계약관리 시스템 메인 - 각 계약별 건수 조회
	 * @param hm
	 * @return
	 * @throws Exception
	 */
	HashMap getClmStatuCount(HashMap hm) throws Exception;
	
	void updateMainImage(MultipartHttpServletRequest request) throws Exception;
	
	/**
	 *  계약관리 시스템 메인 - 원본미등록(C02642) 건 조회
	 * @param hm
	 * @return
	 * @throws Exception
	 */
	String getNotServed(HashMap hm) throws Exception;
	
	/**
	 *  계약관리 시스템 메인 - 자동연장 건 조회
	 * @param hm
	 * @return
	 * @throws Exception
	 */
	String countAutoExtension(HashMap hm) throws Exception;
}
