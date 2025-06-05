package com.sds.secframework.bbs.dto;

import com.sds.secframework.common.dto.CommonVO;

/**
 * 게시판 Value Object
 *  
 * @version V1.0
 * 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */

public class BBSVO extends CommonVO {

	/**********************************************************************
	 * 게시판 상세내역
	 **********************************************************************/
	/**게시판아이디 */
	private String bbs_id;                        
	/**게시글아이디 */
	private String notice_id;                     
	/**게시글타이틀 */
	private String notice_title;                  
	/**게시글내용 */
	private String notice_cntnt;                  
	/**상위게시글아이디 */
	private String up_notice_id;                  
	/**게시글그룹아이디 */
	private String notice_grp_id;                 
	/**게시글깊이 */
	private int    notice_depth;                  
	/** 게시글순서*/
	private int    notice_order;                  
	/**게시글표시순서 */
	private String notice_disp;                   
	/**게시글상태 */
	private String status;                        
	/**긴급여부 */
	private String urgency_yn;                    
	/**조회수 */
	private int    ref_cnt;                       
	/**추천횟수 */
	private int    recommend_cnt;                 
	/**게시시작일자 */
	private String notice_start_ymd;              
	/**게시종료일자 */
	private String notice_end_ymd;                
	/**게시등록일 */
	private String notice_ymd;                    
	/**첨부파일참조번호 */
	private String file_ref_no;                   
	/**등록자아이디 */
	private String reg_id;                        
	/**등록일시 */
	private String reg_dt;                        
	/**수정자아이디 */
	private String mod_id;                        
	/**수정일시 */
	private String mod_dt;                        

	/**********************************************************************
	 * 게시판 추천
	 **********************************************************************/
	/**사용자아이디 */
	private String recommend_user_id;             
	/**추천여부 */
	private String recommend_yn;                  
	/**추천일시 */
	private String recommend_dt;                  

	/**********************************************************************
	 * 게시판 한줄답변
	 **********************************************************************/
	/**덧글일련번호 */
	private String append_seq;                    
	/**덧글내용 */
	private String append_cntnt;                  

	/**********************************************************************
	 * DB 이외 추가
	 **********************************************************************/
	/**첨부파일 스트링 */
	private String fileInfos;                     
	/**원글(댓글등록시 원글아이디) */
	private String orign_notice_id;               
	/**조회카운트 적용여부 */
	private String ref_cnt_apply_yn;              
	
	/**********************************************************************
	 * 검색조건
	 **********************************************************************/
	/**게시검색시작일자 */
	private String srch_start_notice_ymd;         
	/**게시검색종료일자 */
	private String srch_end_notice_ymd;           
	/**게시검색타입 */
	private String srch_cntnt_type;               
	/**게시검색내용 */
	private String srch_cntnt;                    
	
	public String getBbs_id() {
		return bbs_id;
	}
	public void setBbs_id(String bbs_id) {
		this.bbs_id = bbs_id;
	}
	public String getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(String notice_id) {
		this.notice_id = notice_id;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_cntnt() {
		return notice_cntnt;
	}
	public void setNotice_cntnt(String notice_cntnt) {
		this.notice_cntnt = notice_cntnt;
	}
	public String getUp_notice_id() {
		return up_notice_id;
	}
	public void setUp_notice_id(String up_notice_id) {
		this.up_notice_id = up_notice_id;
	}
	public String getNotice_grp_id() {
		return notice_grp_id;
	}
	public void setNotice_grp_id(String notice_grp_id) {
		this.notice_grp_id = notice_grp_id;
	}
	public int getNotice_depth() {
		return notice_depth;
	}
	public void setNotice_depth(int notice_depth) {
		this.notice_depth = notice_depth;
	}
	public int getNotice_order() {
		return notice_order;
	}
	public void setNotice_order(int notice_order) {
		this.notice_order = notice_order;
	}
	public String getNotice_disp() {
		return notice_disp;
	}
	public void setNotice_disp(String notice_disp) {
		this.notice_disp = notice_disp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUrgency_yn() {
		return urgency_yn;
	}
	public void setUrgency_yn(String urgency_yn) {
		this.urgency_yn = urgency_yn;
	}
	public int getRef_cnt() {
		return ref_cnt;
	}
	public void setRef_cnt(int ref_cnt) {
		this.ref_cnt = ref_cnt;
	}
	public int getRecommend_cnt() {
		return recommend_cnt;
	}
	public void setRecommend_cnt(int recommend_cnt) {
		this.recommend_cnt = recommend_cnt;
	}
	public String getNotice_start_ymd() {
		return notice_start_ymd;
	}
	public void setNotice_start_ymd(String notice_start_ymd) {
		this.notice_start_ymd = notice_start_ymd;
	}
	public String getNotice_end_ymd() {
		return notice_end_ymd;
	}
	public void setNotice_end_ymd(String notice_end_ymd) {
		this.notice_end_ymd = notice_end_ymd;
	}
	public String getNotice_ymd() {
		return notice_ymd;
	}
	public void setNotice_ymd(String notice_ymd) {
		this.notice_ymd = notice_ymd;
	}
	public String getFile_ref_no() {
		return file_ref_no;
	}
	public void setFile_ref_no(String file_ref_no) {
		this.file_ref_no = file_ref_no;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public String getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public String getMod_id() {
		return mod_id;
	}
	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}
	public String getMod_dt() {
		return mod_dt;
	}
	public void setMod_dt(String mod_dt) {
		this.mod_dt = mod_dt;
	}
	public String getRecommend_user_id() {
		return recommend_user_id;
	}
	public void setRecommend_user_id(String recommend_user_id) {
		this.recommend_user_id = recommend_user_id;
	}
	public String getRecommend_yn() {
		return recommend_yn;
	}
	public void setRecommend_yn(String recommend_yn) {
		this.recommend_yn = recommend_yn;
	}
	public String getRecommend_dt() {
		return recommend_dt;
	}
	public void setRecommend_dt(String recommend_dt) {
		this.recommend_dt = recommend_dt;
	}
	public String getAppend_seq() {
		return append_seq;
	}
	public void setAppend_seq(String append_seq) {
		this.append_seq = append_seq;
	}
	public String getAppend_cntnt() {
		return append_cntnt;
	}
	public void setAppend_cntnt(String append_cntnt) {
		this.append_cntnt = append_cntnt;
	}
	public String getFileInfos() {
		return fileInfos;
	}
	public void setFileInfos(String fileInfos) {
		this.fileInfos = fileInfos;
	}
	public String getOrign_notice_id() {
		return orign_notice_id;
	}
	public void setOrign_notice_id(String orign_notice_id) {
		this.orign_notice_id = orign_notice_id;
	}
	
	public String getRef_cnt_apply_yn() {
		return ref_cnt_apply_yn;
	}
	public void setRef_cnt_apply_yn(String ref_cnt_apply_yn) {
		this.ref_cnt_apply_yn = ref_cnt_apply_yn;
	}
	public String getSrch_start_notice_ymd() {
		return srch_start_notice_ymd;
	}
	public void setSrch_start_notice_ymd(String srch_start_notice_ymd) {
		this.srch_start_notice_ymd = srch_start_notice_ymd;
	}
	public String getSrch_end_notice_ymd() {
		return srch_end_notice_ymd;
	}
	public void setSrch_end_notice_ymd(String srch_end_notice_ymd) {
		this.srch_end_notice_ymd = srch_end_notice_ymd;
	}
	public String getSrch_cntnt_type() {
		return srch_cntnt_type;
	}
	public void setSrch_cntnt_type(String srch_cntnt_type) {
		this.srch_cntnt_type = srch_cntnt_type;
	}
	public String getSrch_cntnt() {
		return srch_cntnt;
	}
	public void setSrch_cntnt(String srch_cntnt) {
		this.srch_cntnt = srch_cntnt;
	}	
}
