package com.sds.secframework.main.dto;

import com.sds.secframework.common.dto.CommonForm;

public class MainForm extends CommonForm {
	/** 대상 메뉴 아이디 */
	private String target_menu_id;
	/** 선택된 메뉴 아이디 */
	private String selected_menu_id;
	/** 선택된 메뉴 명 */
	private String selected_menu_nm;
	/** 메뉴구분(top, left) */
	private String menu_gbn;
	/** RELOAD 구분 */
	private String reload_yn;

	public String getTarget_menu_id() {
		return target_menu_id;
	}

	public void setTarget_menu_id(String target_menu_id) {
		this.target_menu_id = target_menu_id;
	}

	public String getMenu_gbn() {
		return menu_gbn;
	}

	public void setMenu_gbn(String menu_gbn) {
		this.menu_gbn = menu_gbn;
	}

	public String getSelected_menu_id() {
		return selected_menu_id;
	}

	public void setSelected_menu_id(String selected_menu_id) {
		this.selected_menu_id = selected_menu_id;
	}

	public String getSelected_menu_nm() {
		return selected_menu_nm;
	}

	public void setSelected_menu_nm(String selected_menu_nm) {
		this.selected_menu_nm = selected_menu_nm;
	}
	
	public String getReload_yn() {
		return reload_yn;
	}

	public void setReload_yn(String reload_yn) {
		this.reload_yn = reload_yn;
	}
}
