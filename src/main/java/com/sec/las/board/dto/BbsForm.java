package com.sec.las.board.dto;

import com.sds.secframework.common.dto.CommonVO;


public class BbsForm extends CommonVO {
	
	/** 권한직위 */
	private String top_role;
	
	public String getTop_role() {
		return top_role;
	}
	public void setTop_role(String top_role) {
		this.top_role = top_role;
	}
	
	/**********************************************
	 * 검색 조건
	 **********************************************/
	// 검색 타입(제목, 작성자)
	private String srch_type;
	// 검색어
	private String srch_word;
	// 조회일자
	private String srch_start_dt;
	// 조회종료일자
	private String srch_end_dt;
	// 국내/해외/전체 
	private String srch_dmstfrgn_gbn;
	
	/**********************************************
	 * 구분 조건
	 **********************************************/

	// 등록/답변
	private String insert_kbn;

	public String getInsert_kbn() {
		return insert_kbn;
	}
	public void setInsert_kbn(String insert_kbn) {
		this.insert_kbn = insert_kbn;
	}

	/**********************************************
	 * 권한처리
	 **********************************************/
	// 게시판 등록 가능 여부
	private boolean auth_insert;
	// 게시판 답글등록 가능 여부
	private boolean auth_reply;
	// 게시판 수정 가능 여부
	private boolean auth_modify;
	// 게시판 삭제 가능 여부
	private boolean auth_delete;

	public boolean isAuth_insert() {
		return auth_insert;
	}
	public void setAuth_insert(boolean auth_insert) {
		this.auth_insert = auth_insert;
	}
	public boolean isAuth_reply() {
		return auth_reply;
	}
	public void setAuth_reply(boolean auth_reply) {
		this.auth_reply = auth_reply;
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
	
	/**********************************************
	 * 첨부파일
	 **********************************************/
	// 첨부파일ID
	private String file_ref_no;
	// 첨부파일 처리 구분값
	private String view_gbn;
	// 첨부파일정보
	private String fileInfos;
	
	public String getFile_ref_no() {
		return file_ref_no;
	}
	public void setFile_ref_no(String file_ref_no) {
		this.file_ref_no = file_ref_no;
	}
	public String getView_gbn() {
		return view_gbn;
	}
	public void setView_gbn(String view_gbn) {
		this.view_gbn = view_gbn;
	}
	public String getFileInfos() {
		return fileInfos;
	}
	public void setFileInfos(String fileInfos) {
		this.fileInfos = fileInfos;
	}
	
	/**********************************************
	 * 컨트롤
	 **********************************************/	
	// 검색된 리스트 갯수
	private int totalCnt;

	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}	
	
	/**********************************************
	 * 입력값
	 **********************************************/	
	// 그룹_번호
	private int grp_no;
	// 그룹_일련번호
	private int grp_seqno;
	// 게시_순서
	private int postup_srt;
	// 게시_단계
	private int postup_depth;
	// 게시판_종류
	private String bbs_knd;
	// 제목
	private String title;
	// 내용
	private String cont;
	// 조회수
	private int rdcnt;
	// 전체_일련번호
	private int total_seqno;
	// 등록_일시
	private String reg_dt;
	// 등록자_ID
	private String reg_id;
	// 등록자_명
	private String reg_nm;
	// 등록_부서
	private String reg_dept;
	// 등록_부서_명
	private String reg_dept_nm;
	// 수정_일시
	private String mod_dt;
	// 수정자_ID
	private String mod_id;
	// 수정자_명
	private String mod_nm;
	// 삭제_여부
	private String del_yn;
	// 삭제_일시
	private String del_dt;
	// 삭제자_ID
	private String del_id;
	// 삭제자_명
	private String del_nm;
	
	
	private String return_message;
	
	private String return_title;


	public String getReturn_message() {
		return return_message;
	}
	public void setReturn_message(String return_message) {
		this.return_message = return_message;
	}
	public String getReturn_title() {
		return return_title;
	}
	public void setReturn_title(String return_title) {
		this.return_title = return_title;
	}
	/**********************************************
	 * getter, setter 메소드
	 **********************************************/
	public void setGrp_no(int grp_no) {
		this.grp_no = grp_no;
	}
	public void setGrp_seqno(int grp_seqno) {
		this.grp_seqno = grp_seqno;
	}
	public void setPostup_srt(int postup_srt) {
		this.postup_srt = postup_srt;
	}
	public void setPostup_depth(int postup_depth) {
		this.postup_depth = postup_depth;
	}
	public void setBbs_knd(String bbs_knd) {
		this.bbs_knd = bbs_knd;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public void setRdcnt(int rdcnt) {
		this.rdcnt = rdcnt;
	}
	public void setTotal_seqno(int total_seqno) {
		this.total_seqno = total_seqno;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public void setReg_nm(String reg_nm) {
		this.reg_nm = reg_nm;
	}
	public void setReg_dept(String reg_dept) {
		this.reg_dept = reg_dept;
	}
	public void setReg_dept_nm(String reg_dept_nm) {
		this.reg_dept_nm = reg_dept_nm;
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
	public int getGrp_no() {
		return this.grp_no;
	}
	public int getGrp_seqno() {
		return this.grp_seqno;
	}
	public int getPostup_srt() {
		return this.postup_srt;
	}
	public int getPostup_depth() {
		return this.postup_depth;
	}
	public String getBbs_knd() {
		return this.bbs_knd;
	}
	public String getTitle() {
		return this.title;
	}
	public String getCont() {
		return this.cont;
	}
	public int getRdcnt() {
		return this.rdcnt;
	}
	public int getTotal_seqno() {
		return this.total_seqno;
	}
	public String getReg_dt() {
		return this.reg_dt;
	}
	public String getReg_id() {
		return this.reg_id;
	}
	public String getReg_nm() {
		return this.reg_nm;
	}
	public String getReg_dept() {
		return this.reg_dept;
	}
	public String getReg_dept_nm() {
		return this.reg_dept_nm;
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
	public String getSrch_type() {
		return srch_type;
	}
	public void setSrch_type(String srch_type) {
		this.srch_type = srch_type;
	}
	public String getSrch_word() {
		return srch_word;
	}
	public void setSrch_word(String srch_word) {
		this.srch_word = srch_word;
	}	
	public String getSrch_start_dt() {
		return srch_start_dt;
	}
	public void setSrch_start_dt(String srch_start_dt) {
		this.srch_start_dt = srch_start_dt;
	}
	public String getSrch_end_dt() {
		return srch_end_dt;
	}
	public void setSrch_end_dt(String srch_end_dt) {
		this.srch_end_dt = srch_end_dt;
	}
	
	
	public String getSrch_dmstfrgn_gbn() {
		return srch_dmstfrgn_gbn;
	}
	public void setSrch_dmstfrgn_gbn(String srch_dmstfrgn_gbn) {
		this.srch_dmstfrgn_gbn = srch_dmstfrgn_gbn;
	}

	//부서공유자료실 구분자
	private String isPds;

	public String getIsPds() {
		return isPds;
	}
	public void setIsPds(String isPds) {
		this.isPds = isPds;
	}
	
	private String dmstfrgn_gbn;
	private String reg_jikgup_nm;

	public String getDmstfrgn_gbn() {
		return dmstfrgn_gbn;
	}
	public void setDmstfrgn_gbn(String dmstfrgn_gbn) {
		this.dmstfrgn_gbn = dmstfrgn_gbn;
	}
	public String getReg_jikgup_nm() {
		return reg_jikgup_nm;
	}
	public void setReg_jikgup_nm(String reg_jikgup_nm) {
		this.reg_jikgup_nm = reg_jikgup_nm;
	}
	
}
