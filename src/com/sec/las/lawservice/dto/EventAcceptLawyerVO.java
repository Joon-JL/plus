/**
 * Project Name : 법무시스템 이식
 * File name	: EventAcceptLawyerVO.java
 * Description	: 사건관련 변호사리스트를 취득한다.
 * Author		: 박병주
 * Date			: 2011. 09
 * Copyright	:   
 */
package com.sec.las.lawservice.dto;


public class EventAcceptLawyerVO extends LwsCommonVO {
	private String lwr_no;
	private String lwr_nm;	
	private String seqno;	
	private String event_no;	
	private String acpt_no;
	
	public String getLwr_nm() {
		return lwr_nm;
	}
	public void setLwr_nm(String lwr_nm) {
		this.lwr_nm = lwr_nm;
	}
	public String getLwr_no() {
		return lwr_no;
	}
	public void setLwr_no(String lwr_no) {
		this.lwr_no = lwr_no;
	}
	public String getSeqno() {
		return seqno;
	}
	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	public String getEvent_no() {
		return event_no;
	}
	public void setEvent_no(String event_no) {
		this.event_no = event_no;
	}
	public String getAcpt_no() {
		return acpt_no;
	}
	public void setAcpt_no(String acpt_no) {
		this.acpt_no = acpt_no;
	}	
}
