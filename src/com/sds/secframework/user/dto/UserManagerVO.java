package com.sds.secframework.user.dto;

import com.sds.secframework.common.dto.CommonVO;

/**
 * 사용자정보 Value Object
 *  
 * @version V1.0
 * 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */

public class UserManagerVO extends CommonVO {
	
	private String area_nm = null;    //사업장명
	private String dept_nm_eng = null;    //부서영문명
	private String duty_apbtman_id = null;    //업무 승인자 ID
	private String gthr_cnst_dt = null;    //수집동의일시
	
	public String getArea_nm() {
		return area_nm;
	}
	public void setArea_nm(String area_nm) {
		this.area_nm = area_nm;
	}
	public String getDept_nm_eng() {
		return dept_nm_eng;
	}
	public void setDept_nm_eng(String dept_nm_eng) {
		this.dept_nm_eng = dept_nm_eng;
	}
	public String getDuty_apbtman_id() {
		return duty_apbtman_id;
	}
	public void setDuty_apbtman_id(String duty_apbtman_id) {
		this.duty_apbtman_id = duty_apbtman_id;
	}
	public String getGthr_cnst_dt() {
		return gthr_cnst_dt;
	}
	public void setGthr_cnst_dt(String gthr_cnst_dt) {
		this.gthr_cnst_dt = gthr_cnst_dt;
	}


	/**********************************************************************
	 * 김정곤 조회 속성 
	 **********************************************************************/
	/**조회콤보. */
	String srch_cntnt_type="";
	
	public String getSrch_cntnt_type() {
		return srch_cntnt_type;
	}
	public void setSrch_cntnt_type(String srch_cntnt_type) {
		this.srch_cntnt_type = srch_cntnt_type;
	}


	/**조회TEXT. */
	String srch_cntnt="";	

	public String getSrch_cntnt() {
		return srch_cntnt;
	}
	public void setSrch_cntnt(String srch_cntnt) {
		this.srch_cntnt = srch_cntnt;
	}

	/**조회역할권한. */
	String srch_role_cd="";
	
	public String getSrch_role_cd() {
		return srch_role_cd;
	}
	public void setSrch_role_cd(String srch_role_cd) {
		this.srch_role_cd = srch_role_cd;
	}	
	
	/**조회권한지정여부. */
	String srch_auth_apnt_yn = "";
	
	public String getSrch_auth_apnt_yn() {
		return srch_auth_apnt_yn;
	}
	public void setSrch_auth_apnt_yn(String srch_auth_apnt_yn) {
		this.srch_auth_apnt_yn = srch_auth_apnt_yn;
	}
	
	String srch_access_yn = "";
	
	
	/**승인여부. */
	public String getSrch_access_yn() {
		return srch_access_yn;
	}
	public void setSrch_access_yn(String srch_access_yn) {
		this.srch_access_yn = srch_access_yn;
	}


	/**권한지정여부*/
	String auth_apnt_yn = "";
	/**권한지정부서*/
	String auth_apnt_dept = "";
	/**권한지정부서이름*/
	String auth_apnt_dept_nm = "";
	public String getAuth_apnt_yn() {
		return auth_apnt_yn;
	}
	public void setAuth_apnt_yn(String auth_apnt_yn) {
		this.auth_apnt_yn = auth_apnt_yn;
	}
	public String getAuth_apnt_dept() {
		return auth_apnt_dept;
	}
	public void setAuth_apnt_dept(String auth_apnt_dept) {
		this.auth_apnt_dept = auth_apnt_dept;
	}
	public String getAuth_apnt_dept_nm() {
		return auth_apnt_dept_nm;
	}
	public void setAuth_apnt_dept_nm(String auth_apnt_dept_nm) {
		this.auth_apnt_dept_nm = auth_apnt_dept_nm;
	}
	
	/**승인여부*/
	String access_yn = "";
	String loc_gbn = "";		
	public String getAccess_yn() {
		return access_yn;
	}
	public void setAccess_yn(String access_yn) {
		this.access_yn = access_yn;
	}
	public String getLoc_gbn() {
		return loc_gbn;
	}
	public void setLoc_gbn(String loc_gbn) {
		this.loc_gbn = loc_gbn;
	}


	/*사용자 권한*/
	String role_cd="";
	
	public String getRole_cd() {
		return role_cd;
	}
	public void setRole_cd(String role_cd) {
		this.role_cd = role_cd;
	}	

	String sys_cd;
	
	public String getSys_cd() {
		return sys_cd;
	}
	public void setSys_cd(String sys_cd) {
		this.sys_cd = sys_cd;
	}

	/*로그인사용자*/
	
	String login_id;

	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	/*메일수신여부*/
	String email_rcv_yn;

	public String getEmail_rcv_yn() {
		return email_rcv_yn;
	}
	public void setEmail_rcv_yn(String email_rcv_yn) {
		this.email_rcv_yn = email_rcv_yn;
	}
	
	/*사용자 아이디 POPUP*/

	String popup_user_nm;
	
	public String getPopup_user_nm() {
		return popup_user_nm;
	}
	public void setPopup_user_nm(String popup_user_nm) {
		this.popup_user_nm = popup_user_nm;
	}


	/**********************************************************************
	 * 임직원 조회 속성
	 **********************************************************************/
	/**주민번호 */
	String res_no;				
	/**사번 */
	String emp_no;				
	/**성명 */
	String user_nm;				
	/**한자명 */
	String chinese_nm;			
	/**회사코드 */
	String comp_cd;				
	/**회사명 */
	String comp_nm;				
	/**부문코드 */
	String business_cd;			
	/**부문명 */
	String business_nm;			
	/**사업부코드 */
	String division_cd;			
	/**사업부명 */
	String division_nm;			
	/**사업장코드 */
	String area_cd;				
	/**부서코드 */
	String dept_cd;				
	/**내부부서코드 */
	String in_dept_cd;			
	/**부서명 */
	String dept_nm;				
	/**부서변경일자 */
	String change_dept_ymd;		
	/**원소속부서코드 */
	String origin_dept_cd;		
	/**원소속부서명 */
	String origin_dept_nm;		
	/**입사발령코드 */
	String appoint_cd;			
	/**그룹입사일 */
	String grp_join_ymd;		
	/**입사차수 */
	String join_term;			
	/**입사기수 */
	String join_level;			
	/**입사일 */
	String join_ymd;			
	/**승격일 */
	String raise_ymd;			
	/**승급일 */
	String promotion_ymd;		
	/**차기승격년월 */
	String next_raise_ym;		
	/**차기승급년월 */
	String next_promotion_ym;	
	/**퇴직일 */
	String retirement_ymd;		
	/**휴직일 */
	String suspension_ymd;		
	/**복직일 */
	String reinstatment_ymd;	
	/**직무코드 */
	String jikmu_cd;			
	/**직무명 */
	String jikmu_nm;			
	/**직급코드 */
	String jikgup_cd;			
	/**직급명 */
	String jikgup_nm;			
	/**그룹직급코드 */
	String grp_jikgup_cd;		
	/**그룹직급명 */
	String grp_jikgup_nm;		
	/**직책코드 */
	String jikchek_cd;			
	/**학력코드 */
	String schooling_cd;		
	/**대학원코드 */
	String graduate_school_cd;	
	/**대학원명 */
	String graduate_school_nm;	
	/**석사전공코드 */
	String master_major_cd;		
	/**석사전공명 */
	String master_major_nm;		
	/**대학코드 */
	String univ_cd;				
	/**학사전공코드 */
	String bachelor_major_cd;	
	/**학사전공명 */
	String bachelor_major_nm;	
	/**병역 */
	String military;			
	/**성별코드 */
	String sex_cd;				
	/**ABO혈액형 */
	String abo_blood;			
	/**RH혈액형 */
	String rh_blood;			
	/**취미 */
	String hobby;				
	/**생년월일 */
	String birth_ymd;			
	/**양력여부 */
	String solar_yn;			
	/**키 */
	String height;				
	/**몸무게 */
	String weight;				
	/**결혼기념일 */
	String marriage_ymd;		
	/**지역코드 */
	String region_cd;			
	/**지역명 */
	String region_nm;			
	/**지역영문명 */
	String region_nm_eng;			
	/**이메일 */
	String email;				
	/**집주소 */
	String home_address;			
	/**회사우편번호코드 */
	String comp_zip_cd;			
	/**회사주소 */
	String comp_address;		
	/**회사전화번호 */
	String office_tel_no;		
	/**집전화번호 */
	String home_tel_no;			
	/**핸드폰번호 */
	String mobile_no;			
	/**재직상태 */
	String status;				
	/**언어 */
	String last_locale;			
	/**인터페이스시스템구분 */
	String sys_gbn;		
	/**사용자타입 */
	String employee_type;			   
	/**보안레벨 */
	String user_level;			   	    
	/**등록일자 */
	String reg_dt;	     
	/**소속조직코드*/
	String blngt_orgnz;
	/**담당부서코드*/
	String resp_operdiv;
	
	/**********************************************************************
	 * 검색조건
	 **********************************************************************/
	/**사용자검색타입 */
	String srch_user_cntnt_type;         
	/**사용자검색내용 */
	String srch_user_cntnt;              	
	/**사용자부서구분 */
	String user_type;					 	
	/**검색조건 */
	String search_combo;				 
	/**검색명 */
	String search_name;					 	
	/**구분 */
	String flag;					  	 
	/**하위부서 포함여부 */
	String includeLower;					
	/**성명 */
	String empnm;	
	
	public String getRes_no() {
		return res_no;
	}
	public void setRes_no(String res_no) {
		this.res_no = res_no;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public String getUser_nm() {
		return user_nm;
	}
	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}
	public String getChinese_nm() {
		return chinese_nm;
	}
	public void setChinese_nm(String chinese_nm) {
		this.chinese_nm = chinese_nm;
	}
	public String getComp_cd() {
		return comp_cd;
	}
	public void setComp_cd(String comp_cd) {
		this.comp_cd = comp_cd;
	}
	public String getComp_nm() {
		return comp_nm;
	}
	public void setComp_nm(String comp_nm) {
		this.comp_nm = comp_nm;
	}
	public String getBusiness_cd() {
		return business_cd;
	}
	public void setBusiness_cd(String business_cd) {
		this.business_cd = business_cd;
	}
	public String getBusiness_nm() {
		return business_nm;
	}
	public void setBusiness_nm(String business_nm) {
		this.business_nm = business_nm;
	}
	public String getDivision_cd() {
		return division_cd;
	}
	public void setDivision_cd(String division_cd) {
		this.division_cd = division_cd;
	}
	public String getDivision_nm() {
		return division_nm;
	}
	public void setDivision_nm(String division_nm) {
		this.division_nm = division_nm;
	}
	public String getArea_cd() {
		return area_cd;
	}
	public void setArea_cd(String area_cd) {
		this.area_cd = area_cd;
	}
	public String getDept_cd() {
		return dept_cd;
	}
	public void setDept_cd(String dept_cd) {
		this.dept_cd = dept_cd;
	}
	public String getIn_dept_cd() {
		return in_dept_cd;
	}
	public void setIn_dept_cd(String in_dept_cd) {
		this.in_dept_cd = in_dept_cd;
	}
	public String getDept_nm() {
		return dept_nm;
	}
	public void setDept_nm(String dept_nm) {
		this.dept_nm = dept_nm;
	}
	public String getChange_dept_ymd() {
		return change_dept_ymd;
	}
	public void setChange_dept_ymd(String change_dept_ymd) {
		this.change_dept_ymd = change_dept_ymd;
	}
	public String getOrigin_dept_cd() {
		return origin_dept_cd;
	}
	public void setOrigin_dept_cd(String origin_dept_cd) {
		this.origin_dept_cd = origin_dept_cd;
	}
	public String getOrigin_dept_nm() {
		return origin_dept_nm;
	}
	public void setOrigin_dept_nm(String origin_dept_nm) {
		this.origin_dept_nm = origin_dept_nm;
	}
	public String getAppoint_cd() {
		return appoint_cd;
	}
	public void setAppoint_cd(String appoint_cd) {
		this.appoint_cd = appoint_cd;
	}
	public String getGrp_join_ymd() {
		return grp_join_ymd;
	}
	public void setGrp_join_ymd(String grp_join_ymd) {
		this.grp_join_ymd = grp_join_ymd;
	}
	public String getJoin_term() {
		return join_term;
	}
	public void setJoin_term(String join_term) {
		this.join_term = join_term;
	}
	public String getJoin_level() {
		return join_level;
	}
	public void setJoin_level(String join_level) {
		this.join_level = join_level;
	}
	public String getJoin_ymd() {
		return join_ymd;
	}
	public void setJoin_ymd(String join_ymd) {
		this.join_ymd = join_ymd;
	}
	public String getRaise_ymd() {
		return raise_ymd;
	}
	public void setRaise_ymd(String raise_ymd) {
		this.raise_ymd = raise_ymd;
	}
	public String getPromotion_ymd() {
		return promotion_ymd;
	}
	public void setPromotion_ymd(String promotion_ymd) {
		this.promotion_ymd = promotion_ymd;
	}
	public String getNext_raise_ym() {
		return next_raise_ym;
	}
	public void setNext_raise_ym(String next_raise_ym) {
		this.next_raise_ym = next_raise_ym;
	}
	public String getNext_promotion_ym() {
		return next_promotion_ym;
	}
	public void setNext_promotion_ym(String next_promotion_ym) {
		this.next_promotion_ym = next_promotion_ym;
	}
	public String getRetirement_ymd() {
		return retirement_ymd;
	}
	public void setRetirement_ymd(String retirement_ymd) {
		this.retirement_ymd = retirement_ymd;
	}
	public String getSuspension_ymd() {
		return suspension_ymd;
	}
	public void setSuspension_ymd(String suspension_ymd) {
		this.suspension_ymd = suspension_ymd;
	}
	public String getReinstatment_ymd() {
		return reinstatment_ymd;
	}
	public void setReinstatment_ymd(String reinstatment_ymd) {
		this.reinstatment_ymd = reinstatment_ymd;
	}
	public String getJikmu_cd() {
		return jikmu_cd;
	}
	public void setJikmu_cd(String jikmu_cd) {
		this.jikmu_cd = jikmu_cd;
	}
	public String getJikmu_nm() {
		return jikmu_nm;
	}
	public void setJikmu_nm(String jikmu_nm) {
		this.jikmu_nm = jikmu_nm;
	}
	public String getJikgup_cd() {
		return jikgup_cd;
	}
	public void setJikgup_cd(String jikgup_cd) {
		this.jikgup_cd = jikgup_cd;
	}
	public String getJikgup_nm() {
		return jikgup_nm;
	}
	public void setJikgup_nm(String jikgup_nm) {
		this.jikgup_nm = jikgup_nm;
	}
	public String getGrp_jikgup_cd() {
		return grp_jikgup_cd;
	}
	public void setGrp_jikgup_cd(String grp_jikgup_cd) {
		this.grp_jikgup_cd = grp_jikgup_cd;
	}
	public String getGrp_jikgup_nm() {
		return grp_jikgup_nm;
	}
	public void setGrp_jikgup_nm(String grp_jikgup_nm) {
		this.grp_jikgup_nm = grp_jikgup_nm;
	}
	public String getJikchek_cd() {
		return jikchek_cd;
	}
	public void setJikchek_cd(String jikchek_cd) {
		this.jikchek_cd = jikchek_cd;
	}
	public String getSchooling_cd() {
		return schooling_cd;
	}
	public void setSchooling_cd(String schooling_cd) {
		this.schooling_cd = schooling_cd;
	}
	public String getGraduate_school_cd() {
		return graduate_school_cd;
	}
	public void setGraduate_school_cd(String graduate_school_cd) {
		this.graduate_school_cd = graduate_school_cd;
	}
	public String getGraduate_school_nm() {
		return graduate_school_nm;
	}
	public void setGraduate_school_nm(String graduate_school_nm) {
		this.graduate_school_nm = graduate_school_nm;
	}
	public String getMaster_major_cd() {
		return master_major_cd;
	}
	public void setMaster_major_cd(String master_major_cd) {
		this.master_major_cd = master_major_cd;
	}
	public String getMaster_major_nm() {
		return master_major_nm;
	}
	public void setMaster_major_nm(String master_major_nm) {
		this.master_major_nm = master_major_nm;
	}
	public String getUniv_cd() {
		return univ_cd;
	}
	public void setUniv_cd(String univ_cd) {
		this.univ_cd = univ_cd;
	}
	public String getBachelor_major_cd() {
		return bachelor_major_cd;
	}
	public void setBachelor_major_cd(String bachelor_major_cd) {
		this.bachelor_major_cd = bachelor_major_cd;
	}
	public String getBachelor_major_nm() {
		return bachelor_major_nm;
	}
	public void setBachelor_major_nm(String bachelor_major_nm) {
		this.bachelor_major_nm = bachelor_major_nm;
	}
	public String getMilitary() {
		return military;
	}
	public void setMilitary(String military) {
		this.military = military;
	}
	public String getSex_cd() {
		return sex_cd;
	}
	public void setSex_cd(String sex_cd) {
		this.sex_cd = sex_cd;
	}
	public String getAbo_blood() {
		return abo_blood;
	}
	public void setAbo_blood(String abo_blood) {
		this.abo_blood = abo_blood;
	}
	public String getRh_blood() {
		return rh_blood;
	}
	public void setRh_blood(String rh_blood) {
		this.rh_blood = rh_blood;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getBirth_ymd() {
		return birth_ymd;
	}
	public void setBirth_ymd(String birth_ymd) {
		this.birth_ymd = birth_ymd;
	}
	public String getSolar_yn() {
		return solar_yn;
	}
	public void setSolar_yn(String solar_yn) {
		this.solar_yn = solar_yn;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getMarriage_ymd() {
		return marriage_ymd;
	}
	public void setMarriage_ymd(String marriage_ymd) {
		this.marriage_ymd = marriage_ymd;
	}
	public String getRegion_cd() {
		return region_cd;
	}
	public void setRegion_cd(String region_cd) {
		this.region_cd = region_cd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHome_address() {
		return home_address;
	}
	public void setHome_address(String home_address) {
		this.home_address = home_address;
	}
	public String getComp_zip_cd() {
		return comp_zip_cd;
	}
	public void setComp_zip_cd(String comp_zip_cd) {
		this.comp_zip_cd = comp_zip_cd;
	}
	public String getComp_address() {
		return comp_address;
	}
	public void setComp_address(String comp_address) {
		this.comp_address = comp_address;
	}
	public String getOffice_tel_no() {
		return office_tel_no;
	}
	public void setOffice_tel_no(String office_tel_no) {
		this.office_tel_no = office_tel_no;
	}
	public String getHome_tel_no() {
		return home_tel_no;
	}
	public void setHome_tel_no(String home_tel_no) {
		this.home_tel_no = home_tel_no;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLast_locale() {
		return last_locale;
	}
	public void setLast_locale(String last_locale) {
		this.last_locale = last_locale;
	}
	public String getSys_gbn() {
		return sys_gbn;
	}
	public void setSys_gbn(String sys_gbn) {
		this.sys_gbn = sys_gbn;
	}
	public String getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public String getSrch_user_cntnt_type() {
		return srch_user_cntnt_type;
	}
	public void setSrch_user_cntnt_type(String srch_user_cntnt_type) {
		this.srch_user_cntnt_type = srch_user_cntnt_type;
	}
	public String getSrch_user_cntnt() {
		return srch_user_cntnt;
	}
	public void setSrch_user_cntnt(String srch_user_cntnt) {
		this.srch_user_cntnt = srch_user_cntnt;
	}	
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}	
	public String getSearch_combo() {
		return search_combo;
	}
	public void setSearch_combo(String search_combo) {
		this.search_combo = search_combo;
	}
	public String getSearch_name() {
		return search_name;
	}
	public void setSearch_name(String search_name) {
		this.search_name = search_name;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getIncludeLower() {
		return includeLower;
	}
	public void setIncludeLower(String includeLower) {
		this.includeLower = includeLower;
	}
	public String getEmpnm() {
		return empnm;
	}
	public void setEmpnm(String empnm) {
		this.empnm = empnm;
	}
	public String getRegion_nm() {
		return region_nm;
	}
	public void setRegion_nm(String region_nm) {
		this.region_nm = region_nm;
	}
	public String getRegion_nm_eng() {
		return region_nm_eng;
	}
	public void setRegion_nm_eng(String region_nm_eng) {
		this.region_nm_eng = region_nm_eng;
	}
	public String getEmployee_type() {
		return employee_type;
	}
	public void setEmployee_type(String employee_type) {
		this.employee_type = employee_type;
	}
	public String getUser_level() {
		return user_level;
	}
	public void setUser_level(String user_level) {
		this.user_level = user_level;
	}	
	
	public String getBlngt_orgnz() {
		return blngt_orgnz;
	}
	public void setBlngt_orgnz(String blngt_orgnz) {
		this.blngt_orgnz = blngt_orgnz;
	}
	
	public String getResp_operdiv() {
		return resp_operdiv;
	}
	public void setResp_operdiv(String resp_operdiv) {
		this.resp_operdiv = resp_operdiv;
	}


	/**********************************************************************
	 * TB_USER_ID 관련 
	 **********************************************************************/

	String		user_id;   
	//String		res_no;       
	//String		emp_no;    
	//String		user_nm;   
	String		user_nm_eng;       
	//String		chinese_nm;   
	String		single_id;          
	String		single_epid;          
	//String		comp_cd;   
	//String		comp_nm;       
	String		comp_nm_eng;       
	//String		business_cd;      
	//String		business_nm;           
	String		business_nm_eng;     
	//String		division_cd;          
	//String		division_nm;    
	String		division_nm_eng;        
	//String		area_cd;         
	//String		dept_cd;         
	//String		in_dept_cd;      
	//String		dept_nm;       
	//String		change_dept_ymd;       
	//String		origin_dept_cd;       
	//String		origin_dept_nm;       
	//String		appoint_cd;           
	//String		grp_join_ymd;        
	//String		join_term;        
	//String		join_level;           
	//String		join_ymd;         
	//String		raise_ymd;       
	//String		promotion_ymd;          
	//String		next_raise_ym;        
	//String		next_promotion_ym;    
	//String		retirement_ymd;       
	//String		suspension_ymd;         
	//String		reinstatment_ymd;         
	String		jikgun_cd;          
	String		jikgun_nm;         
	String		jikgun_nm_eng;      
	//String		jikgup_cd;           
	//String		jikgup_nm;        
	String		jikgup_nm_eng;          
	//String		grp_jikgup_cd;          
	//String		grp_jikgup_nm;          
	String		grp_jikgup_nm_eng;         
	//String		jikmu_cd;         
	//String		jikmu_nm;          
	String		jikmu_nm_eng;        
	//String		jikchek_cd;       
	//String		schooling_cd;          
	//String		graduate_school_cd;         
	//String		graduate_school_nm;        
	String		graduate_school_nm_eng;         
	//String		master_major_cd;         
	//String		master_major_nm;          
	String		master_major_nm_eng;          
	//String		univ_cd;           
	String		univ_nm;         
	String		univ_nm_eng;         
	//String		bachelor_major_cd;          
	//String		bachelor_major_nm;           
	String		bachelor_major_nm_eng;       
	//String		military;         
	//String		sex_cd;         
	//String		abo_blood;          
	//String		rh_blood;     
	//String		hobby;        
	//String		birth_ymd;         
	//String		solar_yn;           
	//String		height;            
	//String		weight;          
	//String		marriage_ymd;        
	//String		region_cd;       
	//String		email;           
	//String		home_address;          
	//String		comp_zip_cd;      
	//String		comp_address;        
	//String		office_tel_no;           
	//String		home_tel_no;     
	//String		mobile_no;        
	//String		status;          
	//String		last_locale;        
	//String		sys_gbn;        
	//String		reg_dt;  
	String		mna_mng_yn;           
	//String		area_nm;    
	//String		user_level;          
	//String		region_nm;        
	//String		region_nm_eng;          
	//String		employee_type;     
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_nm_eng() {
		return user_nm_eng;
	}
	public void setUser_nm_eng(String user_nm_eng) {
		this.user_nm_eng = user_nm_eng;
	}
	public String getSingle_id() {
		return single_id;
	}
	public void setSingle_id(String single_id) {
		this.single_id = single_id;
	}
	public String getSingle_epid() {
		return single_epid;
	}
	public void setSingle_epid(String single_epid) {
		this.single_epid = single_epid;
	}
	public String getComp_nm_eng() {
		return comp_nm_eng;
	}
	public void setComp_nm_eng(String comp_nm_eng) {
		this.comp_nm_eng = comp_nm_eng;
	}
	public String getBusiness_nm_eng() {
		return business_nm_eng;
	}
	public void setBusiness_nm_eng(String business_nm_eng) {
		this.business_nm_eng = business_nm_eng;
	}
	public String getDivision_nm_eng() {
		return division_nm_eng;
	}
	public void setDivision_nm_eng(String division_nm_eng) {
		this.division_nm_eng = division_nm_eng;
	}
	public String getJikgun_cd() {
		return jikgun_cd;
	}
	public void setJikgun_cd(String jikgun_cd) {
		this.jikgun_cd = jikgun_cd;
	}
	public String getJikgun_nm() {
		return jikgun_nm;
	}
	public void setJikgun_nm(String jikgun_nm) {
		this.jikgun_nm = jikgun_nm;
	}
	public String getJikgun_nm_eng() {
		return jikgun_nm_eng;
	}
	public void setJikgun_nm_eng(String jikgun_nm_eng) {
		this.jikgun_nm_eng = jikgun_nm_eng;
	}
	public String getJikgup_nm_eng() {
		return jikgup_nm_eng;
	}
	public void setJikgup_nm_eng(String jikgup_nm_eng) {
		this.jikgup_nm_eng = jikgup_nm_eng;
	}
	public String getGrp_jikgup_nm_eng() {
		return grp_jikgup_nm_eng;
	}
	public void setGrp_jikgup_nm_eng(String grp_jikgup_nm_eng) {
		this.grp_jikgup_nm_eng = grp_jikgup_nm_eng;
	}
	public String getJikmu_nm_eng() {
		return jikmu_nm_eng;
	}
	public void setJikmu_nm_eng(String jikmu_nm_eng) {
		this.jikmu_nm_eng = jikmu_nm_eng;
	}
	public String getGraduate_school_nm_eng() {
		return graduate_school_nm_eng;
	}
	public void setGraduate_school_nm_eng(String graduate_school_nm_eng) {
		this.graduate_school_nm_eng = graduate_school_nm_eng;
	}
	public String getMaster_major_nm_eng() {
		return master_major_nm_eng;
	}
	public void setMaster_major_nm_eng(String master_major_nm_eng) {
		this.master_major_nm_eng = master_major_nm_eng;
	}
	public String getUniv_nm() {
		return univ_nm;
	}
	public void setUniv_nm(String univ_nm) {
		this.univ_nm = univ_nm;
	}
	public String getUniv_nm_eng() {
		return univ_nm_eng;
	}
	public void setUniv_nm_eng(String univ_nm_eng) {
		this.univ_nm_eng = univ_nm_eng;
	}
	public String getBachelor_major_nm_eng() {
		return bachelor_major_nm_eng;
	}
	public void setBachelor_major_nm_eng(String bachelor_major_nm_eng) {
		this.bachelor_major_nm_eng = bachelor_major_nm_eng;
	}
	public String getMna_mng_yn() {
		return mna_mng_yn;
	}
	public void setMna_mng_yn(String mna_mng_yn) {
		this.mna_mng_yn = mna_mng_yn;
	}
    	
	/*
	 * 조회조건 디테일 
	 * */	
		
	String	serch_detail_div;
	String  serch_detail_vel;	
	
	public String getSerch_detail_div() {
		return serch_detail_div;
	}
	public void setSerch_detail_div(String serch_detail_div) {
		this.serch_detail_div = serch_detail_div;
	}
	public String getSerch_detail_vel() {
		return serch_detail_vel;
	}
	public void setSerch_detail_vel(String serch_detail_vel) {
		this.serch_detail_vel = serch_detail_vel;
	}

	/*
	 * ESB POPUP 조회조건 
	 * */	
	String pop_user_id;

	public String getPop_user_id() {
		return pop_user_id;
	}
	public void setPop_user_id(String pop_user_id) {
		this.pop_user_id = pop_user_id;
	}
	
	/*사용자업데이트 여부 */
	String auto_rnew_yn;
	
	public String getAuto_rnew_yn() {
		return auto_rnew_yn;
	}
	public void setAuto_rnew_yn(String auto_rnew_yn) {
		this.auto_rnew_yn = auto_rnew_yn;
	}
	
	String sec_reviewer_type;

	public String getSec_reviewer_type() {
		return sec_reviewer_type;
	}
	public void setSec_reviewer_type(String sec_reviewer_type) {
		this.sec_reviewer_type = sec_reviewer_type;
	}
	
	String user_srt;
	String gthr_cnst_yn;

	public String getUser_srt() {
		return user_srt;
	}
	public void setUser_srt(String user_srt) {
		this.user_srt = user_srt;
	}
	public String getGthr_cnst_yn() {
		return gthr_cnst_yn;
	}
	public void setGthr_cnst_yn(String gthr_cnst_yn) {
		this.gthr_cnst_yn = gthr_cnst_yn;
	}
	
	String user_real_nm;
	String user_real_nm_eng;

	public String getUser_real_nm() {
		return user_real_nm;
	}
	public void setUser_real_nm(String user_real_nm) {
		this.user_real_nm = user_real_nm;
	}
	public String getUser_real_nm_eng() {
		return user_real_nm_eng;
	}
	public void setUser_real_nm_eng(String user_real_nm_eng) {
		this.user_real_nm_eng = user_real_nm_eng;
	}
	
	/*결재자 여부 */
	String approval_yn;
	
	public String getApproval_yn() {
		return approval_yn;
	}
	public void setApproval_yn(String approval_yn) {
		this.approval_yn = approval_yn;
	}
}
