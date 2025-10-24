package com.sds.secframework.menu.dto;

import java.util.List;

import com.sds.secframework.common.dto.CommonForm;

public class MenuForm extends CommonForm {
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
	 * 선택 메뉴
	 */
	private String select_menu_id;

	/**
	 * 하위 메뉴 존재 여부
	 */
	private boolean has_sub_menu;

	/**
	 * 결과 리스트
	 */
	private List result_list;

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

	public String getSelect_menu_id() {
		return select_menu_id;
	}

	public void setSelect_menu_id(String select_menu_id) {
		this.select_menu_id = select_menu_id;
	}

	public List getResult_list() {
		return result_list;
	}

	public void setResult_list(List result_list) {
		this.result_list = result_list;
	}

	public boolean isHas_sub_menu() {
		return has_sub_menu;
	}

	public void setHas_sub_menu(boolean has_sub_menu) {
		this.has_sub_menu = has_sub_menu;
	}

}
