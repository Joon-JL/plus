package com.sds.secframework.singleIF.dto;

import com.sds.secframework.common.dto.CommonVO;

/**
 * Single결재 ESB 연동 결재내역 Value Object
 *  
 * @version com.sds.secframework.singleIF V1.0
 * 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */
public class ApprovalVO extends CommonVO {

	  /**********************************************************************
	  * 결재내역
	   **********************************************************************/
	
		/**모듈 식별자 ID*/
		private String module_id;                  		
		/**Mis ID*/
		private String mis_id;
		/**결재진행상태 (0:대기중,E:오류,1:결재진행,2:완결,3:반려,4:상신취소,5:전결,6:후완결)*/
		private String status;                     		
		/**결재본문*/
		private String body;                      		
		/**본문 종류 (0:TEXT,1:HTML,2:MHTML)*/
		private String body_type;                 		
		/**문서 생성일*/
		private String create_date;              		
		/**보안문서옵션*/
		private String drm_option_info;          		
		/**로케일,인코딩*/
		private String locale_info;              		
		/**통보메일 전송옵션 (0:통보자에게,1:모두에게)*/
		private String noti_mail;                		
		/**긴급여부 (0:일반,1:긴급)*/
		private String priority;                 		
		/**보안문서 종류 (0:일반,1:대외비,2:극비)*/
		private String security;                 		
		/**타임존*/
		private String time_zone;                 		
		/**결재제목*/
		private String title;                      		
		/**결재후처리 프로세스 메소드*/
		private String method;                          
		/**결재후처리 프로세스 FLAG*/
		private String post_process_flag;       
		/**인코딩정보(화면)*/
		private String encoding;       
		
		private String ref_key;
		private String apprvl_clsfcn;
		
		private String path_Query;
		private String cntrt_id;
		private String path_id;
		
		private String comp_cd;
		private String biz_clsfcn;
		private String depth_clsfcn;
		private String cnclsnpurps_bigclsfcn;
		private String cnclsnpurps_midclsfcn;
		
		private String related_products;
		private String requ_item;
		private String req_title_sub;
		
		
	  /**********************************************************************
	  * 결재경로 Value Object
	   **********************************************************************/
		/**메일주소 */
		private String mail_address;
		/**처리구분 */
		private String action_type;
		/**설정구분 */
		private String activity;
		/**SMS 전송 여부 */
		private String alarm_type;
		/**결재문서 승인일 */
		private String approved;
		/**전결권한 */
		private String arbitrary;
		/**결재문서도착시간 */
		private String arrived;
		/**본문수정 권한 */
		private String body_modify;
		/**회사코드 */
		private String comp_code;
		/**회사명 */
		private String comp_name;
		/**부서코드 */
		private String dept_code;
		/**부서명 */
		private String dept_name;
		/**기한제 적용 시간 */
		private String duration;
		/**직책명 */
		private String duty;
		/**직책코드 */
		private String duty_code;
		/**총괄코드 */
		private String group_code;
		/**총괄명 */
		private String group_name;
		/**상신/결재의견 */
		private String opinion;
		/**예약 상신 시간 */
		private String reserved;
		/**경로 변경 권한 */
		private String route_modify;
		/**결재순번 */
		private String sequence;
		/**주민등록번호 */
		private String social_id;
		/**EP ID */
		private String user_id;
		/**사용자명 */
		private String user_name;
		/**대리결재자 여부 */
		private String delegated;

		/**메일주소 */
		private String[] mail_addresss;
		/**처리구분 */
		private String[] action_types;
		/**설정구분 */
		private String[] activitys;
		/**SMS 전송 여부 */
		private String[] alarm_types;
		/**결재문서 승인일 */
		private String[] approveds;
		/**전결권한 */
		private String[] arbitrarys;
		/**결재문서도착시간 */
		private String[] arriveds;
		/**본문수정 권한 */
		private String[] body_modifys;
		/**회사코드 */
		private String[] comp_codes;
		/**회사명 */
		private String[] comp_names;
		/**부서코드 */
		private String[] dept_codes;
		/**부서명 */
		private String[] dept_names;
		/**기한제 적용 시간 */
		private String[] durations;
		/**직책명 */
		private String[] dutys;
		/**직책코드 */
		private String[] duty_codes;
		/**총괄코드 */
		private String[] group_codes;
		/**총괄명 */
		private String[] group_names;
		/**상신/결재의견 */
		private String[] opinions;
		/**예약 상신 시간 */
		private String[] reserveds;
		/**경로 변경 권한 */
		private String[] route_modifys;
		/**결재순번 */
		private String[] sequences;
		/**주민등록번호 */
		private String[] social_ids;
		/**EP ID */
		private String[] user_ids;
		/**사용자명 */
		private String[] user_names;
		/**대리결재자 여부 */
		private String[] delegateds;
	  /**********************************************************************
	   * 결재첨부 Value Object
	   **********************************************************************/
		/**첨부파일 일련번호 */
		private String fsequence;
		/**첨부파일명 */
		private String file_name;
		/**첨부파일사이즈 */
		private String file_size;
		/**첨부파일경로 */
		private String store_location;

	  /**********************************************************************
	   * 어플리케이션 참조필드
	   **********************************************************************/
		/**첨부파일 스트링 */
		private String fileInfos;
		/**나모 MIME Value */
		private String body_mime;
		
		// cc등록을 위한 vo 세팅
		private String master_cntrt_id;
		private String demnd_seqno;
		private String demnd_gbn;
		private String demndman_id;
		private String demndman_nm;
		private String demndman_dept_nm;
		private String trgtman_id;
		private String trgtman_nm;
		private String trgtman_dept_nm;
		private String trgtman_jikgup_nm;
		private String demnd_status;
		private String demnd_cont;
		
		private String is_poa;

		public String getModule_id() {
			return module_id;
		}

		public String getMaster_cntrt_id() {
			return master_cntrt_id;
		}

		public void setMaster_cntrt_id(String master_cntrt_id) {
			this.master_cntrt_id = master_cntrt_id;
		}

		public String getDemnd_seqno() {
			return demnd_seqno;
		}

		public void setDemnd_seqno(String demnd_seqno) {
			this.demnd_seqno = demnd_seqno;
		}

		public String getDemnd_gbn() {
			return demnd_gbn;
		}

		public void setDemnd_gbn(String demnd_gbn) {
			this.demnd_gbn = demnd_gbn;
		}

		public String getDemndman_id() {
			return demndman_id;
		}

		public void setDemndman_id(String demndman_id) {
			this.demndman_id = demndman_id;
		}

		public String getDemndman_nm() {
			return demndman_nm;
		}

		public void setDemndman_nm(String demndman_nm) {
			this.demndman_nm = demndman_nm;
		}

		public String getDemndman_dept_nm() {
			return demndman_dept_nm;
		}

		public void setDemndman_dept_nm(String demndman_dept_nm) {
			this.demndman_dept_nm = demndman_dept_nm;
		}

		public String getTrgtman_id() {
			return trgtman_id;
		}

		public void setTrgtman_id(String trgtman_id) {
			this.trgtman_id = trgtman_id;
		}

		public String getTrgtman_nm() {
			return trgtman_nm;
		}

		public void setTrgtman_nm(String trgtman_nm) {
			this.trgtman_nm = trgtman_nm;
		}

		public String getTrgtman_dept_nm() {
			return trgtman_dept_nm;
		}

		public void setTrgtman_dept_nm(String trgtman_dept_nm) {
			this.trgtman_dept_nm = trgtman_dept_nm;
		}

		public String getTrgtman_jikgup_nm() {
			return trgtman_jikgup_nm;
		}

		public void setTrgtman_jikgup_nm(String trgtman_jikgup_nm) {
			this.trgtman_jikgup_nm = trgtman_jikgup_nm;
		}

		public String getDemnd_status() {
			return demnd_status;
		}

		public void setDemnd_status(String demnd_status) {
			this.demnd_status = demnd_status;
		}

		public String getDemnd_cont() {
			return demnd_cont;
		}

		public void setDemnd_cont(String demnd_cont) {
			this.demnd_cont = demnd_cont;
		}

		public String getIs_poa() {
			return is_poa;
		}

		public void setIs_poa(String is_poa) {
			this.is_poa = is_poa;
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

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}

		public String getBody_type() {
			return body_type;
		}

		public void setBody_type(String body_type) {
			this.body_type = body_type;
		}

		public String getCreate_date() {
			return create_date;
		}

		public void setCreate_date(String create_date) {
			this.create_date = create_date;
		}

		public String getDrm_option_info() {
			return drm_option_info;
		}

		public void setDrm_option_info(String drm_option_info) {
			this.drm_option_info = drm_option_info;
		}

		public String getLocale_info() {
			return locale_info;
		}

		public void setLocale_info(String locale_info) {
			this.locale_info = locale_info;
		}

		public String getNoti_mail() {
			return noti_mail;
		}

		public void setNoti_mail(String noti_mail) {
			this.noti_mail = noti_mail;
		}

		public String getPriority() {
			return priority;
		}

		public void setPriority(String priority) {
			this.priority = priority;
		}

		public String getSecurity() {
			return security;
		}

		public void setSecurity(String security) {
			this.security = security;
		}

		public String getTime_zone() {
			return time_zone;
		}

		public void setTime_zone(String time_zone) {
			this.time_zone = time_zone;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getMethod() {
			return method;
		}

		public void setMethod(String method) {
			this.method = method;
		}

		public String getPost_process_flag() {
			return post_process_flag;
		}

		public void setPost_process_flag(String post_process_flag) {
			this.post_process_flag = post_process_flag;
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

		public String[] getMail_addresss() {
			return mail_addresss;
		}

		public void setMail_addresss(String[] mail_addresss) {
			this.mail_addresss = mail_addresss;
		}

		public String[] getAction_types() {
			return action_types;
		}

		public void setAction_types(String[] action_types) {
			this.action_types = action_types;
		}

		public String[] getActivitys() {
			return activitys;
		}

		public void setActivitys(String[] activitys) {
			this.activitys = activitys;
		}

		public String[] getAlarm_types() {
			return alarm_types;
		}

		public void setAlarm_types(String[] alarm_types) {
			this.alarm_types = alarm_types;
		}

		public String[] getApproveds() {
			return approveds;
		}

		public void setApproveds(String[] approveds) {
			this.approveds = approveds;
		}

		public String[] getArbitrarys() {
			return arbitrarys;
		}

		public void setArbitrarys(String[] arbitrarys) {
			this.arbitrarys = arbitrarys;
		}

		public String[] getArriveds() {
			return arriveds;
		}

		public void setArriveds(String[] arriveds) {
			this.arriveds = arriveds;
		}

		public String[] getBody_modifys() {
			return body_modifys;
		}

		public void setBody_modifys(String[] body_modifys) {
			this.body_modifys = body_modifys;
		}

		public String[] getComp_codes() {
			return comp_codes;
		}

		public void setComp_codes(String[] comp_codes) {
			this.comp_codes = comp_codes;
		}

		public String[] getComp_names() {
			return comp_names;
		}

		public void setComp_names(String[] comp_names) {
			this.comp_names = comp_names;
		}

		public String[] getDept_codes() {
			return dept_codes;
		}

		public void setDept_codes(String[] dept_codes) {
			this.dept_codes = dept_codes;
		}

		public String[] getDept_names() {
			return dept_names;
		}

		public void setDept_names(String[] dept_names) {
			this.dept_names = dept_names;
		}

		public String[] getDurations() {
			return durations;
		}

		public void setDurations(String[] durations) {
			this.durations = durations;
		}

		public String[] getDutys() {
			return dutys;
		}

		public void setDutys(String[] dutys) {
			this.dutys = dutys;
		}

		public String[] getDuty_codes() {
			return duty_codes;
		}

		public void setDuty_codes(String[] duty_codes) {
			this.duty_codes = duty_codes;
		}

		public String[] getGroup_codes() {
			return group_codes;
		}

		public void setGroup_codes(String[] group_codes) {
			this.group_codes = group_codes;
		}

		public String[] getGroup_names() {
			return group_names;
		}

		public void setGroup_names(String[] group_names) {
			this.group_names = group_names;
		}

		public String[] getOpinions() {
			return opinions;
		}

		public void setOpinions(String[] opinions) {
			this.opinions = opinions;
		}

		public String[] getReserveds() {
			return reserveds;
		}

		public void setReserveds(String[] reserveds) {
			this.reserveds = reserveds;
		}

		public String[] getRoute_modifys() {
			return route_modifys;
		}

		public void setRoute_modifys(String[] route_modifys) {
			this.route_modifys = route_modifys;
		}

		public String[] getSequences() {
			return sequences;
		}

		public void setSequences(String[] sequences) {
			this.sequences = sequences;
		}

		public String[] getSocial_ids() {
			return social_ids;
		}

		public void setSocial_ids(String[] social_ids) {
			this.social_ids = social_ids;
		}

		public String[] getUser_ids() {
			return user_ids;
		}

		public void setUser_ids(String[] user_ids) {
			this.user_ids = user_ids;
		}

		public String[] getUser_names() {
			return user_names;
		}

		public void setUser_names(String[] user_names) {
			this.user_names = user_names;
		}

		public String[] getDelegateds() {
			return delegateds;
		}

		public void setDelegateds(String[] delegateds) {
			this.delegateds = delegateds;
		}

		public String getFsequence() {
			return fsequence;
		}

		public void setFsequence(String fsequence) {
			this.fsequence = fsequence;
		}

		public String getFile_name() {
			return file_name;
		}

		public void setFile_name(String file_name) {
			this.file_name = file_name;
		}

		public String getFile_size() {
			return file_size;
		}

		public void setFile_size(String file_size) {
			this.file_size = file_size;
		}

		public String getStore_location() {
			return store_location;
		}

		public void setStore_location(String store_location) {
			this.store_location = store_location;
		}

		public String getFileInfos() {
			return fileInfos;
		}

		public void setFileInfos(String fileInfos) {
			this.fileInfos = fileInfos;
		}

		public String getEncoding() {
			return encoding;
		}

		public void setEncoding(String encoding) {
			this.encoding = encoding;
		}

		public String getBody_mime() {
			return body_mime;
		}

		public void setBody_mime(String body_mime) {
			this.body_mime = body_mime;
		}

		public String getRef_key() {
			return ref_key;
		}

		public void setRef_key(String ref_key) {
			this.ref_key = ref_key;
		}

		public String getApprvl_clsfcn() {
			return apprvl_clsfcn;
		}

		public void setApprvl_clsfcn(String apprvl_clsfcn) {
			this.apprvl_clsfcn = apprvl_clsfcn;
		}

		public String getPath_Query() {
			return path_Query;
		}

		public void setPath_Query(String path_Query) {
			this.path_Query = path_Query;
		}

		public String getCntrt_id() {
			return cntrt_id;
		}

		public void setCntrt_id(String cntrt_id) {
			this.cntrt_id = cntrt_id;
		}

		public String getPath_id() {
			return path_id;
		}

		public void setPath_id(String path_id) {
			this.path_id = path_id;
		}

		public String getComp_cd() {
			return comp_cd;
		}

		public void setComp_cd(String comp_cd) {
			this.comp_cd = comp_cd;
		}

		public String getBiz_clsfcn() {
			return biz_clsfcn;
		}

		public void setBiz_clsfcn(String biz_clsfcn) {
			this.biz_clsfcn = biz_clsfcn;
		}

		public String getDepth_clsfcn() {
			return depth_clsfcn;
		}

		public void setDepth_clsfcn(String depth_clsfcn) {
			this.depth_clsfcn = depth_clsfcn;
		}

		public String getCnclsnpurps_bigclsfcn() {
			return cnclsnpurps_bigclsfcn;
		}

		public void setCnclsnpurps_bigclsfcn(String cnclsnpurps_bigclsfcn) {
			this.cnclsnpurps_bigclsfcn = cnclsnpurps_bigclsfcn;
		}

		public String getCnclsnpurps_midclsfcn() {
			return cnclsnpurps_midclsfcn;
		}

		public void setCnclsnpurps_midclsfcn(String cnclsnpurps_midclsfcn) {
			this.cnclsnpurps_midclsfcn = cnclsnpurps_midclsfcn;
		}

		public String getRelated_products() {
			return related_products;
		}

		public void setRelated_products(String related_products) {
			this.related_products = related_products;
		}

		public String getRequ_item() {
			return requ_item;
		}

		public void setRequ_item(String requ_item) {
			this.requ_item = requ_item;
		}	
		
		public String getReq_title_sub() {
			return req_title_sub;
		}

		public void setReq_title_sub(String req_title_sub) {
			this.req_title_sub = req_title_sub;
		}	
		
}
