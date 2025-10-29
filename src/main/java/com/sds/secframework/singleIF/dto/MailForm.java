package com.sds.secframework.singleIF.dto;

import com.sds.secframework.common.dto.CommonVO;

public class MailForm extends CommonVO {

	/**
	 * Single메일 ESB 연동 메일내역 Value Object
	 *  
	 * @version com.sds.secframework.singleIF V1.0
	 * 작성일 : 2009/12/18
	 * @author 금현서, ace4u_khs@samsung.com
	 */

	  /**********************************************************************
	  * 메일내역
	   **********************************************************************/
	
		/**모듈 식별자 ID*/
		private String module_id;                 
		/**Mis Id*/
		private String mis_id;                    
		/**메시지 키값*/
		private String msg_key;                   
		/**제목*/
		private String subject;                   
		/**본문내용*/
		private String body;                   
		/**본문내용*/
		private String body_mime;                      
		/**메세지종류 (personal:개인,official:공문)*/
		private String msg_type;                  
		/**메일메세지본문 (true:html,false:text)*/
		private String bhtml_content_check;       
		/**첨부파일갯수*/
		private String ifile_count;               
		/**Time Zone 정보*/
		private String time_zone;                 
		/**Summer Time 적용여부*/
		private String is_dst;                    
		/**발신자 메일주소*/
		private String sender_mail_addr;    
		/**발신자SingleId**/
		private String sender_single_id;		
		/**0:대기중,1:전송완료,E:전송실패*/
		private String status;                    
		/**생성일자*/
		private String create_date;               

		/**********************************************************************
		* 보안내역
	    **********************************************************************/
		/**보안메일종류*/
		private String drm_type;               
		/**인쇄기능*/
		private String drm_can_print;               
		/**일반문서로 저장 허용*/
		private String drm_can_save;               
		/**개봉횟수*/
		private String drm_use_count;               
		/**개봉가능 PC 대수*/
		private String drm_pc_count;               
		/**사용기간지정*/
		private String drm_valid_days;               
		/**내부사용자 확인메일 수신옵션*/
		private String drm_confirm_mail4int;               
		/**외부사용자 확인메일 수신옵션*/
		private String drm_confirm_mail4ext;               
		/**수신인목록 보이기 옵션*/
		private String drm_can_view_rcpt;               

		/**********************************************************************
		* 메일발신자
		**********************************************************************/
		/**발신로케일*/
		private String locale;
		/**발신인코딩*/
		private String encoding;
		/**발신자싱글패스워드*/
		private String password;
		
		/**********************************************************************
		* 메일수신자 
		**********************************************************************/
		/**수신자일련번호*/
		private String iseq_id;
		/**수신형태*/
		private String rec_type;
		/**수신인이메일주소*/
		private String rec_addr;
		
		/**수신자일련번호*/
		private String[] iseq_ids;
		/**수신형태*/
		private String[] rec_types;
		/**수신인이메일주소*/
		private String[] rec_addrs;
		
		/**********************************************************************
		* 메일첨부
		**********************************************************************/
		/**파일일련번호*/
		private String ifseq_id;
		/**수신자일련번호*/
		private String file_name;
		/**수신자일련번호*/
		private String local_path;
		
		/**첨부파일 스트링 */
		private String fileInfos = "";
		
		/**Body Type*/
		private String body_type;
		
		/**********************************************************************
		* 오류내역
		**********************************************************************/
		private String fault_actor1;
		private String fault_code1;
		private String fault_string1;
		private String fault_message;
		private String sequence;

		public String getSender_single_id() {
			return sender_single_id;
		}

		public void setSender_single_id(String sender_single_id) {
			this.sender_single_id = sender_single_id;
		}

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

		public String getMsg_key() {
			return msg_key;
		}

		public void setMsg_key(String msg_key) {
			this.msg_key = msg_key;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}

		public String getMsg_type() {
			return msg_type;
		}

		public void setMsg_type(String msg_type) {
			this.msg_type = msg_type;
		}

		public String getBhtml_content_check() {
			return bhtml_content_check;
		}

		public void setBhtml_content_check(String bhtml_content_check) {
			this.bhtml_content_check = bhtml_content_check;
		}

		public String getIfile_count() {
			return ifile_count;
		}

		public void setIfile_count(String ifile_count) {
			this.ifile_count = ifile_count;
		}

		public String getTime_zone() {
			return time_zone;
		}

		public void setTime_zone(String time_zone) {
			this.time_zone = time_zone;
		}

		public String getIs_dst() {
			return is_dst;
		}

		public void setIs_dst(String is_dst) {
			this.is_dst = is_dst;
		}

		public String getSender_mail_addr() {
			return sender_mail_addr;
		}

		public void setSender_mail_addr(String sender_mail_addr) {
			this.sender_mail_addr = sender_mail_addr;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getCreate_date() {
			return create_date;
		}

		public void setCreate_date(String create_date) {
			this.create_date = create_date;
		}

		public String getLocale() {
			return locale;
		}

		public void setLocale(String locale) {
			this.locale = locale;
		}

		public String getEncoding() {
			return encoding;
		}

		public void setEncoding(String encoding) {
			this.encoding = encoding;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String[] getIseq_ids() {
			return iseq_ids;
		}

		public void setIseq_ids(String[] iseq_ids) {
			this.iseq_ids = iseq_ids;
		}

		public String[] getRec_types() {
			return rec_types;
		}

		public void setRec_types(String[] rec_types) {
			this.rec_types = rec_types;
		}

		public String[] getRec_addrs() {
			return rec_addrs;
		}

		public void setRec_addrs(String[] rec_addrs) {
			this.rec_addrs = rec_addrs;
		}

		public String getFileInfos() {
			return fileInfos;
		}

		public void setFileInfos(String fileInfos) {
			this.fileInfos = fileInfos;
		}

		public String getIseq_id() {
			return iseq_id;
		}

		public void setIseq_id(String iseq_id) {
			this.iseq_id = iseq_id;
		}

		public String getRec_type() {
			return rec_type;
		}

		public void setRec_type(String rec_type) {
			this.rec_type = rec_type;
		}

		public String getRec_addr() {
			return rec_addr;
		}

		public void setRec_addr(String rec_addr) {
			this.rec_addr = rec_addr;
		}

		public String getIfseq_id() {
			return ifseq_id;
		}

		public void setIfseq_id(String ifseq_id) {
			this.ifseq_id = ifseq_id;
		}

		public String getFile_name() {
			return file_name;
		}

		public void setFile_name(String file_name) {
			this.file_name = file_name;
		}

		public String getLocal_path() {
			return local_path;
		}

		public void setLocal_path(String local_path) {
			this.local_path = local_path;
		}

		public String getBody_mime() {
			return body_mime;
		}

		public void setBody_mime(String body_mime) {
			this.body_mime = body_mime;
		}

		public String getDrm_type() {
			return drm_type;
		}

		public void setDrm_type(String drm_type) {
			this.drm_type = drm_type;
		}

		public String getDrm_can_print() {
			return drm_can_print;
		}

		public void setDrm_can_print(String drm_can_print) {
			this.drm_can_print = drm_can_print;
		}

		public String getDrm_can_save() {
			return drm_can_save;
		}

		public void setDrm_can_save(String drm_can_save) {
			this.drm_can_save = drm_can_save;
		}

		public String getDrm_use_count() {
			return drm_use_count;
		}

		public void setDrm_use_count(String drm_use_count) {
			this.drm_use_count = drm_use_count;
		}

		public String getDrm_pc_count() {
			return drm_pc_count;
		}

		public void setDrm_pc_count(String drm_pc_count) {
			this.drm_pc_count = drm_pc_count;
		}

		public String getDrm_valid_days() {
			return drm_valid_days;
		}

		public void setDrm_valid_days(String drm_valid_days) {
			this.drm_valid_days = drm_valid_days;
		}

		public String getDrm_confirm_mail4int() {
			return drm_confirm_mail4int;
		}

		public void setDrm_confirm_mail4int(String drm_confirm_mail4int) {
			this.drm_confirm_mail4int = drm_confirm_mail4int;
		}

		public String getDrm_confirm_mail4ext() {
			return drm_confirm_mail4ext;
		}

		public void setDrm_confirm_mail4ext(String drm_confirm_mail4ext) {
			this.drm_confirm_mail4ext = drm_confirm_mail4ext;
		}

		public String getDrm_can_view_rcpt() {
			return drm_can_view_rcpt;
		}

		public void setDrm_can_view_rcpt(String drm_can_view_rcpt) {
			this.drm_can_view_rcpt = drm_can_view_rcpt;
		}

		public String getBody_type() {
			return body_type;
		}

		public void setBody_type(String body_type) {
			this.body_type = body_type;
		}

		public String getFault_actor1() {
			return fault_actor1;
		}

		public void setFault_actor1(String fault_actor1) {
			this.fault_actor1 = fault_actor1;
		}

		public String getFault_code1() {
			return fault_code1;
		}

		public void setFault_code1(String fault_code1) {
			this.fault_code1 = fault_code1;
		}

		public String getFault_string1() {
			return fault_string1;
		}

		public void setFault_string1(String fault_string1) {
			this.fault_string1 = fault_string1;
		}

		public String getFault_message() {
			return fault_message;
		}

		public void setFault_message(String fault_message) {
			this.fault_message = fault_message;
		}

		public String getSequence() {
			return sequence;
		}

		public void setSequence(String sequence) {
			this.sequence = sequence;
		}
		
}
