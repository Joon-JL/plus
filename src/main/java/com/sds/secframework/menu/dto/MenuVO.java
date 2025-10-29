package com.sds.secframework.menu.dto;

import com.sds.secframework.common.dto.CommonVO;

public class MenuVO extends CommonVO {
	/** 시스템 코드 **/
	private String sys_cd;

	/**
	 * 메뉴 ID
	 */
	private String menu_id;

	/**
	 * 상위메뉴 ID
	 */
	private String up_menu_id;

	/**
	 * 메뉴명
	 */
	private String menu_nm;

	/**
	 * 메뉴 LEVEL
	 */
	private int menu_level;

	/**
	 * 메뉴 순서
	 */
	private int menu_order;

	/**
	 * 메뉴 URL
	 */
	private String menu_url;

	/**
	 * 메뉴 이미지(스타일시트 번호)
	 */
	private String menu_img;

	/**
	 * 사용자 ID
	 */
	private String user_id;
	  
	/**회사코드 */
	private String comp_cd;

	public String getSys_cd() {
		return sys_cd;
	}

	public void setSys_cd(String sys_cd) {
		this.sys_cd = sys_cd;
	}

	public String getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}

	public String getUp_menu_id() {
		return up_menu_id;
	}

	public void setUp_menu_id(String up_menu_id) {
		this.up_menu_id = up_menu_id;
	}

	public String getMenu_nm() {
		return menu_nm;
	}

	public void setMenu_nm(String menu_nm) {
		this.menu_nm = menu_nm;
	}

	public int getMenu_level() {
		return menu_level;
	}

	public void setMenu_level(int menu_level) {
		this.menu_level = menu_level;
	}

	public int getMenu_order() {
		return menu_order;
	}

	public void setMenu_order(int menu_order) {
		this.menu_order = menu_order;
	}

	public String getMenu_url() {
		return menu_url;
	}

	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}

	public String getMenu_img() {
		return menu_img;
	}

	public void setMenu_img(String menu_img) {
		this.menu_img = menu_img;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getComp_cd() {
		return comp_cd;
	}

	public void setComp_cd(String comp_cd) {
		this.comp_cd = comp_cd;
	}
}
