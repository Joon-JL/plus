package com.sds.secframework.singleIF.dto;

/**
 * Single결재 ESB 연동 결재경로 Value Object
 *  
 * @version com.sds.secframework.singleIF V1.0
 * 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */
public class ApprovalRouteVO {

	/**********************************************************************
	* 결재경로
	**********************************************************************/
	
	 /**모듈 식별자 ID*/
	private String module_id;                   
	/**Mis ID*/
	private String mis_id;                      
	/**메일주소*/
	private String mail_address;                
	/**처리구분*/
	private String action_type;                 
	/**설정구분*/
	private String activity;                    
	/**SMS 전송 여부*/
	private String alarm_type;                  
	/**결재문서 승인일*/
	private String approved;                    
	/**전결권한*/
	private String arbitrary;                   
	/**결재문서도착시간*/
	private String arrived;                     
	/**본문수정 권한*/
	private String body_modify;                 
	/**회사코드*/
	private String comp_code;                   
	/**회사명*/
	private String comp_name;                   
	/**부서코드  */
	private String dept_code;                   
	/**부서명   */
	private String dept_name;                   
	/**기한제 적용 시간*/
	private String duration;                    
	/**직책명*/
	private String duty;                        
	/**직책코드*/
	private String duty_code;                   
	/**총괄코드*/
	private String group_code;                  
	/**총괄명*/
	private String group_name;                  
	/**상신/결재의견*/
	private String opinion;                     
	/**예약 상신 시간*/
	private String reserved;                    
	/**경로 변경 권한*/
	private String route_modify;                
	/**결재순번   */
	private String sequence;                    
	/**주민등록번호  */
	private String social_id;                   
	/**EP ID*/
	private String user_id;                     
	/**사용자명*/
	private String user_name;                   
	/**대리결재자 여부   */
	private String delegated;                   
	
	public String getModule_id() {
		return module_id;
	}
	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}
	public String getMis_id() {
		return mis_id;
	}
	public void setMis_id(String mis_id) {
		this.mis_id = mis_id;
	}
	public String getMail_address() {
		return mail_address;
	}
	public void setMail_address(String mail_address) {
		this.mail_address = mail_address;
	}
	public String getAction_type() {
		return action_type;
	}
	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getAlarm_type() {
		return alarm_type;
	}
	public void setAlarm_type(String alarm_type) {
		this.alarm_type = alarm_type;
	}
	public String getApproved() {
		return approved;
	}
	public void setApproved(String approved) {
		this.approved = approved;
	}
	public String getArbitrary() {
		return arbitrary;
	}
	public void setArbitrary(String arbitrary) {
		this.arbitrary = arbitrary;
	}
	public String getArrived() {
		return arrived;
	}
	public void setArrived(String arrived) {
		this.arrived = arrived;
	}
	public String getBody_modify() {
		return body_modify;
	}
	public void setBody_modify(String body_modify) {
		this.body_modify = body_modify;
	}
	public String getComp_code() {
		return comp_code;
	}
	public void setComp_code(String comp_code) {
		this.comp_code = comp_code;
	}
	public String getComp_name() {
		return comp_name;
	}
	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}
	public String getDept_code() {
		return dept_code;
	}
	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getDuty_code() {
		return duty_code;
	}
	public void setDuty_code(String duty_code) {
		this.duty_code = duty_code;
	}
	public String getGroup_code() {
		return group_code;
	}
	public void setGroup_code(String group_code) {
		this.group_code = group_code;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}
	public String getRoute_modify() {
		return route_modify;
	}
	public void setRoute_modify(String route_modify) {
		this.route_modify = route_modify;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getSocial_id() {
		return social_id;
	}
	public void setSocial_id(String social_id) {
		this.social_id = social_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getDelegated() {
		return delegated;
	}
	public void setDelegated(String delegated) {
		this.delegated = delegated;
	}

	
}
