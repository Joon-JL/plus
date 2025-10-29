/**
 * Project Name : 법무시스템 이식
 * File name	: DeptListForm.java
 * Description	: 귀속부서 선택 팝업  Data Form(Model)
 * Author		: 박병주
 * Date			: 2011. 09
 * Copyright	:   
 */
package com.sec.las.lawservice.dto;

import com.sds.secframework.common.dto.CommonForm;

public class DeptListForm  extends CommonForm {
	// 부서코드
	private String dept_cd;
	// 부서명
	private String dept_nm;
	// 부서명 영문
	private String dept_nm_eng;
	// 적용 부서 코드
	private String in_dept_cd;
	// 트리레벨
	private String dept_level;
	// 부서순서
	private String dept_order;
	// 하위 부서 유무
	private String down_dept_yn;
	// 상위 부서 코드
	private String up_dept_cd;
	
	public String getDept_cd() {
		return dept_cd;
	}
	public void setDept_cd(String dept_cd) {
		this.dept_cd = dept_cd;
	}
	public String getDept_nm() {
		return dept_nm;
	}
	public void setDept_nm(String dept_nm) {
		this.dept_nm = dept_nm;
	}
	public String getDept_nm_eng() {
		return dept_nm_eng;
	}
	public void setDept_nm_eng(String dept_nm_eng) {
		this.dept_nm_eng = dept_nm_eng;
	}
	public String getIn_dept_cd() {
		return in_dept_cd;
	}
	public void setIn_dept_cd(String in_dept_cd) {
		this.in_dept_cd = in_dept_cd;
	}
	public String getDept_level() {
		return dept_level;
	}
	public void setDept_level(String dept_level) {
		this.dept_level = dept_level;
	}
	public String getDept_order() {
		return dept_order;
	}
	public void setDept_order(String dept_order) {
		this.dept_order = dept_order;
	}
	public String getDown_dept_yn() {
		return down_dept_yn;
	}
	public void setDown_dept_yn(String down_dept_yn) {
		this.down_dept_yn = down_dept_yn;
	}
	public String getUp_dept_cd() {
		return up_dept_cd;
	}
	public void setUp_dept_cd(String up_dept_cd) {
		this.up_dept_cd = up_dept_cd;
	}
}
