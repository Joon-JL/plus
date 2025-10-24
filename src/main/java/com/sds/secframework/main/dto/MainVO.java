package com.sds.secframework.main.dto;

import com.sds.secframework.common.dto.CommonVO;

public class MainVO extends CommonVO {
	/** 대상 메뉴 아이디 */
	private String target_menu_id;
	/** 선택된 메뉴 아이디 */
	private String selected_menu_id;
	/** 선택된 메뉴 명 */
	private String selected_menu_nm;
	/** 메뉴 구분(top, left) */
	private String menu_gbn;

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
	public void setSelected_menu_nm(String selected_menu_nm) {
		this.selected_menu_nm = selected_menu_nm;
	}
	
}
