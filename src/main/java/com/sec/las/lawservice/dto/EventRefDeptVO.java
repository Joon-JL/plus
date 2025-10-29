/**
 * Project Name : 법무시스템 이식
 * File name	: EventRefDeptVO.java
 * Description	: 법무_사건_관련부서  Data VO(Model)
 * Author		: 박병주
 * Date			: 2011. 09
 * Copyright	:   
 */
package com.sec.las.lawservice.dto;

import com.sds.secframework.common.dto.CommonVO;

public class EventRefDeptVO extends CommonVO{
	// 사건_번호
	private String event_no;
	// 내부_부서_코드
	private String intnl_dept_cd;
	// 그룹_부서_코드
	private String grp_dept_cd;
	// 부서_명
	private String dept_nm;
	// 등록자_ID
	private String reg_id;
	// 등록자_명
	private String reg_nm;
	
	public String getEvent_no() {
		return event_no;
	}
	public void setEvent_no(String event_no) {
		this.event_no = event_no;
	}
	public String getIntnl_dept_cd() {
		return intnl_dept_cd;
	}
	public void setIntnl_dept_cd(String intnl_dept_cd) {
		this.intnl_dept_cd = intnl_dept_cd;
	}
	public String getGrp_dept_cd() {
		return grp_dept_cd;
	}
	public void setGrp_dept_cd(String grp_dept_cd) {
		this.grp_dept_cd = grp_dept_cd;
	}
	public String getDept_nm() {
		return dept_nm;
	}
	public void setDept_nm(String dept_nm) {
		this.dept_nm = dept_nm;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public String getReg_nm() {
		return reg_nm;
	}
	public void setReg_nm(String reg_nm) {
		this.reg_nm = reg_nm;
	}
}
