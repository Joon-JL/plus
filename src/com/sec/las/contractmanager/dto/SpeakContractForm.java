/**
* Project Name : 법무시스템 이식
* File Name : SpeakContractController.java
* Description : 구두계약 FORM
* Author : 김현구
* Date : 2011. 08. 05
* Copyright : 
*/


package com.sec.las.contractmanager.dto;

import java.util.List;

import com.sds.secframework.common.dto.CommonForm;
import com.sds.secframework.common.util.DateUtil;

public class SpeakContractForm extends CommonForm{

	/**
	 * 일련번호
	 */
	private int seqno;  			
	
	/**
	 * 제목
	 */
	private String title; 			
	
	/**
	 * 회신내용
	 */
	private String re_cont;			
	
	/**
	 * Namo웹에디터 관련 변수
	 */
	private String body_mime;
	
	/**
	 *국내해외구분 
	 */
	private String dmstfrgn_gbn;	
	
	/**
	 * 회신내용참조
	 */
	private String re_cont_ref;		
	
	/**
	 * 의뢰자ID
	 */
	private String reqman_id;		
	
	/**
	 * 의뢰자명
	 */
	private String reqman_nm;		
	
	/**
	 * 의뢰자부서
	 */
	private String reqman_dept;		
	
	/**
	 * 의뢰자부서명
	 */
	private String reqman_dept_nm;	
	
	/**
	 * 의뢰자 메일
	 */
	private String reqman_mail;
	
	/**
	 * 등록일시
	 */
	private String reg_dt;			
	
	/**
	 * 담당자ID
	 */
	private String respman_id;		
	
	/**
	 * 담당자명
	 */
	private String respman_nm;		
	
	/**
	 * 담당자부서
	 */
	private String respman_dept;	
	
	/**
	 * 담당자부서명
	 */
	private String respman_dept_nm;	
	
	/**
	 * 주간업무여부
	 */
	private String weekdutyyn;		
	
	/**
	 * 월간업무여부
	 */
	private String monthdutyyn;		
	
	/**
	 * 확인_주간업무여부 
	 */
	private String conf_weekdutyyn;	
	
	/**
	 * 확인_월간업무여부
	 */
	private String conf_monthdutyyn;
	
	/**
	 * 그룹장_회신부서
	 */
	private String grpmgr_redept;	
	
	/**
	 * 수정일시
	 */
	private String mod_dt;			
	
	/**
	 * 수정자ID
	 */
	private String mod_id;			
	
	/**
	 * 수정자명
	 */
	private String mod_nm;			
	
	/**
	 * 삭제여부
	 */
	private String del_yn;			
	
	/**
	 * 삭제일시
	 */
	private String del_dt;			
	
	/**
	 * 삭제자ID
	 */
	private String del_id;			
	
	/**
	 * 삭제자명
	 */
	private String del_nm;
	
	/**
	 * 페이지 시작
	 */
	private String start_index;
	
	/**
	 * 페이지 끝
	 */
	private String end_index;
	
	/**
	 * 현재페이지
	 */
	private String curPage;
	
	/**
	 * 한 페이지 행 수
	 */
	private int row_cnt = 10;

	/**********************************************
	 * 게시판설정(게시판명, 권한)
	 **********************************************/
	/**
	 * 게시판명
	 */
	private String board_nm;

	/**
	 * 게시판 등록 가능 여부
	 */
	private boolean auth_insert;

	/**
	 * 게시판 수정 가능 여부
	 */
	private boolean auth_modify;

	/**
	 * 게시판 삭제 가능 여부
	 */
	private boolean auth_delete;
	
	/**
	 * 메일 발송 여부
	 */
	private String send_mailyn;

	/**********************************************
	 * 검색 조건
	 **********************************************/

	/**
	 * 검색어 - 담당자
	 */
	private String srch_respman_nm;

	/**
	 * 검색어 - 담당자 id
	 */
	private String srch_respman_id;
	
	/**
	 * 검색어 - 제목
	 */
	private String srch_title;
	
	/**
	 * 조회시작일자
	 */
	private String srch_start_ymd;

	/**
	 * 조회종료일자
	 */
	private String srch_end_ymd;
	
	/**********************************************
	 * 결과 값
	 **********************************************/
	/**
	 * 조회한 리스트
	 */
	private List speakcontract_list;

	/**
	 * 결과 타이틀
	 */
	private String return_title;

	/**
	 * 결과 메시지
	 */
	private String return_message;
	
	private String isForeign;
	
	
	/**
	 * 생성자
	 */
	public SpeakContractForm() throws Exception {
		//srch_start_ymd = DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), -365), "-");
		//srch_end_ymd = DateUtil.formatDate(DateUtil.today(), "-");
		del_yn = "N";
		send_mailyn = "N";
		isForeign = "false";
	}
	
	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setRe_cont(String re_cont) {
		this.re_cont = re_cont;
	}
	public void setDmstfrgn_gbn(String dmstfrgn_gbn) {
		this.dmstfrgn_gbn = dmstfrgn_gbn;
	}
	public void setRe_cont_ref(String re_cont_ref) {
		this.re_cont_ref = re_cont_ref;
	}
	public void setReqman_id(String reqman_id) {
		this.reqman_id = reqman_id;
	}
	public void setReqman_nm(String reqman_nm) {
		this.reqman_nm = reqman_nm;
	}
	public void setReqman_dept(String reqman_dept) {
		this.reqman_dept = reqman_dept;
	}
	public void setReqman_dept_nm(String reqman_dept_nm) {
		this.reqman_dept_nm = reqman_dept_nm;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public void setRespman_id(String respman_id) {
		this.respman_id = respman_id;
	}
	public void setRespman_nm(String respman_nm) {
		this.respman_nm = respman_nm;
	}
	public void setRespman_dept(String respman_dept) {
		this.respman_dept = respman_dept;
	}
	public void setRespman_dept_nm(String respman_dept_nm) {
		this.respman_dept_nm = respman_dept_nm;
	}
	public void setWeekdutyyn(String weekdutyyn) {
		this.weekdutyyn = weekdutyyn;
	}
	public void setMonthdutyyn(String monthdutyyn) {
		this.monthdutyyn = monthdutyyn;
	}
	public void setConf_weekdutyyn(String conf_weekdutyyn) {
		this.conf_weekdutyyn = conf_weekdutyyn;
	}
	public void setConf_monthdutyyn(String conf_monthdutyyn) {
		this.conf_monthdutyyn = conf_monthdutyyn;
	}
	public void setGrpmgr_redept(String grpmgr_redept) {
		this.grpmgr_redept = grpmgr_redept;
	}
	public void setMod_dt(String mod_dt) {
		this.mod_dt = mod_dt;
	}
	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}
	public void setMod_nm(String mod_nm) {
		this.mod_nm = mod_nm;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public void setDel_dt(String del_dt) {
		this.del_dt = del_dt;
	}
	public void setDel_id(String del_id) {
		this.del_id = del_id;
	}
	public void setDel_nm(String del_nm) {
		this.del_nm = del_nm;
	}
	public void setStart_index(String start_index) {
		this.start_index = start_index;
	}
	public void setEnd_index(String end_index) {
		this.end_index = end_index;
	}
	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}
	public int getSeqno() {
		return this.seqno;
	}
	public String getTitle() {
		return this.title;
	}
	public String getRe_cont() {
		return this.re_cont;
	}
	public String getDmstfrgn_gbn() {
		return this.dmstfrgn_gbn;
	}
	public String getRe_cont_ref() {
		return this.re_cont_ref;
	}
	public String getReqman_id() {
		return this.reqman_id;
	}
	public String getReqman_nm() {
		return this.reqman_nm;
	}
	public String getReqman_dept() {
		return this.reqman_dept;
	}
	public String getReqman_dept_nm() {
		return this.reqman_dept_nm;
	}
	public String getReg_dt() {
		return this.reg_dt;
	}
	public String getRespman_id() {
		return this.respman_id;
	}
	public String getRespman_nm() {
		return this.respman_nm;
	}
	public String getRespman_dept() {
		return this.respman_dept;
	}
	public String getRespman_dept_nm() {
		return this.respman_dept_nm;
	}
	public String getWeekdutyyn() {
		return this.weekdutyyn;
	}
	public String getMonthdutyyn() {
		return this.monthdutyyn;
	}
	public String getConf_weekdutyyn() {
		return this.conf_weekdutyyn;
	}
	public String getConf_monthdutyyn() {
		return this.conf_monthdutyyn;
	}
	public String getGrpmgr_redept() {
		return this.grpmgr_redept;
	}
	public String getMod_dt() {
		return this.mod_dt;
	}
	public String getMod_id() {
		return this.mod_id;
	}
	public String getMod_nm() {
		return this.mod_nm;
	}
	public String getDel_yn() {
		return this.del_yn;
	}
	public String getDel_dt() {
		return this.del_dt;
	}
	public String getDel_id() {
		return this.del_id;
	}
	public String getDel_nm() {
		return this.del_nm;
	}
	public String getStart_index() {
		return this.start_index;
	}
	public String getEnd_index() {
		return this.end_index;
	}
	public String getCurPage() {
		return this.curPage;
	}

	
	///////////////////////////////////////////////////////////////////
	
	
	public int getRow_cnt() {
		return row_cnt;
	}

	public void setRow_cnt(int row_cnt) {
		this.row_cnt = row_cnt;
	}

	public String getBoard_nm() {
		return board_nm;
	}

	public void setBoard_nm(String board_nm) {
		this.board_nm = board_nm;
	}

	public boolean isAuth_insert() {
		return auth_insert;
	}

	public void setAuth_insert(boolean auth_insert) {
		this.auth_insert = auth_insert;
	}

	public boolean isAuth_modify() {
		return auth_modify;
	}

	public void setAuth_modify(boolean auth_modify) {
		this.auth_modify = auth_modify;
	}

	public boolean isAuth_delete() {
		return auth_delete;
	}

	public void setAuth_delete(boolean auth_delete) {
		this.auth_delete = auth_delete;
	}


	public String getSrch_respman_nm() {
		return srch_respman_nm;
	}

	public void setSrch_respman_nm(String srch_respman_nm) {
		this.srch_respman_nm = srch_respman_nm;
	}

	public String getSrch_title() {
		return srch_title;
	}

	public void setSrch_title(String srch_title) {
		this.srch_title = srch_title;
	}

	public String getSrch_start_ymd() {
		return srch_start_ymd;
	}

	public void setSrch_start_ymd(String srch_start_ymd) {
		this.srch_start_ymd = srch_start_ymd;
	}

	public String getSrch_end_ymd() {
		return srch_end_ymd;
	}

	public void setSrch_end_ymd(String srch_end_ymd) {
		this.srch_end_ymd = srch_end_ymd;
	}

	public String getReturn_title() {
		return return_title;
	}

	public void setReturn_title(String return_title) {
		this.return_title = return_title;
	}

	public String getReturn_message() {
		return return_message;
	}

	public void setReturn_message(String return_message) {
		this.return_message = return_message;
	}

	public List getSpeakcontract_list() {
		return speakcontract_list;
	}

	public void setSpeakcontract_list(List speakcontract_list) {
		this.speakcontract_list = speakcontract_list;
	}

	public String getIsForeign() {
		return isForeign;
	}

	public void setIsForeign(String isForeign) {
		this.isForeign = isForeign;
	}

	public String getSrch_respman_id() {
		return srch_respman_id;
	}

	public void setSrch_respman_id(String srch_respman_id) {
		this.srch_respman_id = srch_respman_id;
	}

	public String getBody_mime() {
		return body_mime;
	}

	public void setBody_mime(String body_mime) {
		this.body_mime = body_mime;
	}

	public String getReqman_mail() {
		return reqman_mail;
	}

	public void setReqman_mail(String reqman_mail) {
		this.reqman_mail = reqman_mail;
	}

	public String getSend_mailyn() {
		return send_mailyn;
	}

	public void setSend_mailyn(String send_mailyn) {
		this.send_mailyn = send_mailyn;
	}
	
}
