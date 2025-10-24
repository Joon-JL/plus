package com.sec.clm.review.dto;

import com.sds.secframework.common.dto.CommonVO;

public class ConsiderationVO extends CommonVO {

	/**********************************************
	 * 검색 조건
	 **********************************************/
	/** 분류코드 */
	private String dimension_cd;
	/** 소속조직 */
	private String blngt_orgnz;
	/** 권한직위 */
	private String top_role;
	
	/** Type */
	private String srch_type_cd;
	/** 의뢰명 */
	private String srch_req_title;
	/** 조직 */
	private String srch_orgnz;
	/** 의뢰시작일 */
	private String srch_start_dt;
	/** 의뢰종료일*/
	private String srch_end_dt;
	/** 의뢰자 ID */
	private String srch_reqman_id;
	/** 의뢰자 명 */
	private String srch_reqman_nm;
	/** 의뢰상태 */
	private String srch_prgrs_status;
	/** 주관부서 */
	private String srch_owner_dept;
	/** 법무상태 */
	private String srch_law_status;
	/** IP상태 */
	private String srch_ip_status;
	/** 담당자ID */
	private String srch_respman_id;
	/** 담당자  명 */
	private String srch_respman_nm;
	/** 비즈니스단계 */
    private String srch_biz_depth;
	/** 체결 목적 */
	private String srch_cnclsnpurps;
	/** 계약상대방 */
	private String srch_cntrt_oppnt_nm;
	private String srch_cnsd_cont;			// 내용 검색 (검토요청내용 & 검토의견)
	private String srch_if_sys_cd;			// 연계시스템 검색
	private String sElComp;

	/** 계약상대방 */
	private String cntrt_oppnt_nm;
	/** 예정본 의뢰 여부 */
	private String plndbn_req_yn;
	
	/** 검토의뢰_ID */
	private String cnsdreq_id;
	/** 검토의뢰_부서 */
	private String req_dept;
	
	private String prev_cnsdreq_id;
	
	/** 검토방식(단독여부) */
	private String srch_solo_yn;
	
	/** 계약_ID */
	private String cntrt_id;
	/** 상대회사관련 계약건 검색 여부 */
	private String cntrt_srch_yn;
	/** 페이지 구분 */
	private String page_flag;
	/** 변경전_검토_부서 */
	private String cngebfr_cnsd_dept;

	private String stat_flag;
	
	// 법무시스템 전사계약List 추가에 따른 기존 계약관리 계약목록 VO 복사
	private String list_mode;
	private String srch_review_title;
	private String srch_start_reqday;
	private String srch_end_reqday;
	private String srch_cnsdman_nm;
	private String srch_resp_dept;
	private String srch_biz_clsfcn;
	private String srch_cnclsnpurps_bigclsfcn;
	private String srch_step;
	private String srch_state;
	private String srch_oppnt_cd;
	private String srch_start_cnlsnday;
	private String srch_end_cnlsnday;
	
	private String mark_cntrt_id;
	private String mark_num;
	private String rtn_cont;
	private String srch_closed_yn;
	
	private String close_cont;
	
	//HQ 검토의뢰 시 필요한내용
	private String hq_cnsdreq_id;
	private String prev_hq_cnsdreq_id;
	private String hq_req_title;
	private String hq_cnsd_demnd_cont;
	private String hq_cnsd_status;
	
	// 필수 항목 체크 관련
	private String item_cd;
	private String dis_yn;
	private String remark;
	private String sum_text;
	private String cnsd_level;
	
	private String db_cnsdreq_id;
	private String resp_key;
	
	/** HQ Reviewer 명 */
	private String srch_hq_reviewer_nm;
	
	private String sMultComp;
	
	private String hq_req_yn;
	
	// gerp_code : 2014-09-01 김미정 GERP Code
	private String srch_gerp_code;
	
	
	private String srch_cntrt_no;
	
	public String getPrev_cnsdreq_id() {
		return prev_cnsdreq_id;
	}
	public String getHq_cnsdreq_id() {
		return hq_cnsdreq_id;
	}
	public void setHq_cnsdreq_id(String hq_cnsdreq_id) {
		this.hq_cnsdreq_id = hq_cnsdreq_id;
	}
	public String getPrev_hq_cnsdreq_id() {
		return prev_hq_cnsdreq_id;
	}
	public void setPrev_hq_cnsdreq_id(String prev_hq_cnsdreq_id) {
		this.prev_hq_cnsdreq_id = prev_hq_cnsdreq_id;
	}
	public String getHq_req_title() {
		return hq_req_title;
	}
	public void setHq_req_title(String hq_req_title) {
		this.hq_req_title = hq_req_title;
	}
	public String getHq_cnsd_demnd_cont() {
		return hq_cnsd_demnd_cont;
	}
	public void setHq_cnsd_demnd_cont(String hq_cnsd_demnd_cont) {
		this.hq_cnsd_demnd_cont = hq_cnsd_demnd_cont;
	}
	public String getHq_cnsd_status() {
		return hq_cnsd_status;
	}
	public void setHq_cnsd_status(String hq_cnsd_status) {
		this.hq_cnsd_status = hq_cnsd_status;
	}
	public String getItem_cd() {
		return item_cd;
	}
	public void setItem_cd(String item_cd) {
		this.item_cd = item_cd;
	}
	public String getDis_yn() {
		return dis_yn;
	}
	public void setDis_yn(String dis_yn) {
		this.dis_yn = dis_yn;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSum_text() {
		return sum_text;
	}
	public void setSum_text(String sum_text) {
		this.sum_text = sum_text;
	}
	public String getCnsd_level() {
		return cnsd_level;
	}
	public void setCnsd_level(String cnsd_level) {
		this.cnsd_level = cnsd_level;
	}
	public String getDb_cnsdreq_id() {
		return db_cnsdreq_id;
	}
	public void setDb_cnsdreq_id(String db_cnsdreq_id) {
		this.db_cnsdreq_id = db_cnsdreq_id;
	}
	public String getResp_key() {
		return resp_key;
	}
	public void setResp_key(String resp_key) {
		this.resp_key = resp_key;
	}
	public String getSrch_hq_reviewer_nm() {
		return srch_hq_reviewer_nm;
	}
	public void setSrch_hq_reviewer_nm(String srch_hq_reviewer_nm) {
		this.srch_hq_reviewer_nm = srch_hq_reviewer_nm;
	}
	public String getsMultComp() {
		return sMultComp;
	}
	public void setsMultComp(String sMultComp) {
		this.sMultComp = sMultComp;
	}
	public String getHq_req_yn() {
		return hq_req_yn;
	}
	public void setHq_req_yn(String hq_req_yn) {
		this.hq_req_yn = hq_req_yn;
	}
	public String getSrch_gerp_code() {
		return srch_gerp_code;
	}
	public void setSrch_gerp_code(String srch_gerp_code) {
		this.srch_gerp_code = srch_gerp_code;
	}
	public void setPrev_cnsdreq_id(String prev_cnsdreq_id) {
		this.prev_cnsdreq_id = prev_cnsdreq_id;
	}
	private String dmstfrgn_gbn;
	
	public String getDmstfrgn_gbn() {
		return dmstfrgn_gbn;
	}
	public void setDmstfrgn_gbn(String dmstfrgn_gbn) {
		this.dmstfrgn_gbn = dmstfrgn_gbn;
	}
	private String lastbn_chge_yn;
	
	public String getLastbn_chge_yn() {
		return lastbn_chge_yn;
	}
	public void setLastbn_chge_yn(String lastbn_chge_yn) {
		this.lastbn_chge_yn = lastbn_chge_yn;
	}

	private String plndbn_chge_cont;
	
	public String getPlndbn_chge_cont() {
		return plndbn_chge_cont;
	}
	public void setPlndbn_chge_cont(String plndbn_chge_cont) {
		this.plndbn_chge_cont = plndbn_chge_cont;
	}
	
	private String cnsd_demnd_cont;
	

	public String getCnsd_demnd_cont() {
		return cnsd_demnd_cont;
	}
	public void setCnsd_demnd_cont(String cnsd_demnd_cont) {
		this.cnsd_demnd_cont = cnsd_demnd_cont;
	}			
	public String getClose_cont() {
		return close_cont;
	}
	public void setClose_cont(String close_cont) {
		this.close_cont = close_cont;
	}
	public String getSrch_closed_yn() {
		return srch_closed_yn;
	}
	public void setSrch_closed_yn(String srch_closed_yn) {
		this.srch_closed_yn = srch_closed_yn;
	}
	public String getsElComp() {
		return sElComp;
	}
	public void setsElComp(String sElComp) {
		this.sElComp = sElComp;
	}
	public String getRtn_cont() {
		return rtn_cont;
	}
	public void setRtn_cont(String rtn_cont) {
		this.rtn_cont = rtn_cont;
	}
	public String getMark_cntrt_id() {
		return mark_cntrt_id;
	}
	public void setMark_cntrt_id(String mark_cntrt_id) {
		this.mark_cntrt_id = mark_cntrt_id;
	}
	public String getMark_num() {
		return mark_num;
	}
	public void setMark_num(String mark_num) {
		this.mark_num = mark_num;
	}
	public String getStat_flag(){
		return stat_flag;
	}
	public void setStat_flag(String stat_flag){
		this.stat_flag = stat_flag;
	}
	
	//DMSTFRGN_GBN
	private String dmstfrgn;
	
	public String getDmstfrgn(){
		return dmstfrgn;
	}
	public void setDmstfrgn(String dmstfrgn){
		this.dmstfrgn = dmstfrgn;
	}
	
	//TN_CLM_CONT_CNSDREQ	진행_상태	PRGRS_STATUS
	private String prgrs_status;
	
	public String getPrgrs_status(){
		return prgrs_status;
	}
	public void setPrgrs_status(String prgrs_status){
		this.prgrs_status = prgrs_status;
	}
	/**********************************************
	 * 입력값
	 **********************************************/
	
	/**********************************************
	 * getter, setter 메소드
	 **********************************************/
	public String getDimension_cd() {
		return dimension_cd;
	}
	public void setDimension_cd(String dimension_cd) {
		this.dimension_cd = dimension_cd;
	}
	public String getBlngt_orgnz() {
		return blngt_orgnz;
	}
	public void setBlngt_orgnz(String blngt_orgnz) {
		this.blngt_orgnz = blngt_orgnz;
	}
	public String getTop_role() {
		return top_role;
	}
	public void setTop_role(String top_role) {
		this.top_role = top_role;
	}
	public String getSrch_respman_id() {
		return srch_respman_id;
	}
	public void setSrch_respman_id(String srch_respman_id) {
		this.srch_respman_id = srch_respman_id;
	}
	public String getSrch_reqman_id() {
		return srch_reqman_id;
	}
	public void setSrch_reqman_id(String srch_reqman_id) {
		this.srch_reqman_id = srch_reqman_id;
	}
	public String getSrch_prgrs_status() {
		return srch_prgrs_status;
	}
	public void setSrch_prgrs_status(String srch_prgrs_status) {
		this.srch_prgrs_status = srch_prgrs_status;
	}
	public String getSrch_type_cd() {
		return srch_type_cd;
	}
	public void setSrch_type_cd(String srch_type_cd) {
		this.srch_type_cd = srch_type_cd;
	}
	public String getSrch_orgnz() {
		return srch_orgnz;
	}
	public void setSrch_orgnz(String srch_orgnz) {
		this.srch_orgnz = srch_orgnz;
	}
	public String getSrch_owner_dept() {
		return srch_owner_dept;
	}
	public void setSrch_owner_dept(String srch_owner_dept) {
		this.srch_owner_dept = srch_owner_dept;
	}
	public String getSrch_law_status() {
		return srch_law_status;
	}
	public void setSrch_law_status(String srch_law_status) {
		this.srch_law_status = srch_law_status;
	}
	public String getSrch_ip_status() {
		return srch_ip_status;
	}
	public void setSrch_ip_status(String srch_ip_status) {
		this.srch_ip_status = srch_ip_status;
	}
	public String getSrch_biz_depth() {
		return srch_biz_depth;
	}
	public void setSrch_biz_depth(String srch_biz_depth) {
		this.srch_biz_depth = srch_biz_depth;
	}
	public String getSrch_cntrt_oppnt_nm() {
		return srch_cntrt_oppnt_nm;
	}
	public void setSrch_cntrt_oppnt_nm(String srch_cntrt_oppnt_nm) {
		this.srch_cntrt_oppnt_nm = srch_cntrt_oppnt_nm;
	}
	public String getCntrt_oppnt_nm() {
		return cntrt_oppnt_nm;
	}
	public void setCntrt_oppnt_nm(String cntrt_oppnt_nm) {
		this.cntrt_oppnt_nm = cntrt_oppnt_nm;
	}
	public String getPlndbn_req_yn() {
		return plndbn_req_yn;
	}
	public void setPlndbn_req_yn(String plndbn_req_yn) {
		this.plndbn_req_yn = plndbn_req_yn;
	}
	public String getSrch_req_title() {
		return srch_req_title;
	}
	public void setSrch_req_title(String srch_req_title) {
		this.srch_req_title = srch_req_title;
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
	public String getSrch_reqman_nm() {
		return srch_reqman_nm;
	}
	public void setSrch_reqman_nm(String srch_reqman_nm) {
		this.srch_reqman_nm = srch_reqman_nm;
	}
	public String getSrch_respman_nm() {
		return srch_respman_nm;
	}
	public void setSrch_respman_nm(String srch_respman_nm) {
		this.srch_respman_nm = srch_respman_nm;
	}
	public String getSrch_cnclsnpurps() {
		return srch_cnclsnpurps;
	}
	public void setSrch_cnclsnpurps(String srch_cnclsnpurps) {
		this.srch_cnclsnpurps = srch_cnclsnpurps;
	}
	public String getStart_index() {
		return start_index;
	}
	public void setStart_index(String start_index) {
		this.start_index = start_index;
	}
	public String getCnsdreq_id() {
		return cnsdreq_id;
	}
	public void setCnsdreq_id(String cnsdreq_id) {
		this.cnsdreq_id = cnsdreq_id;
	}
	public String getReq_dept() {
		return req_dept;
	}
	public void setReq_dept(String req_dept) {
		this.req_dept = req_dept;
	}
	public String getCntrt_id() {
		return cntrt_id;
	}
	public void setCntrt_id(String cntrt_id) {
		this.cntrt_id = cntrt_id;
	}
	public String getCntrt_srch_yn() {
		return cntrt_srch_yn;
	}
	public void setCntrt_srch_yn(String cntrt_srch_yn) {
		this.cntrt_srch_yn = cntrt_srch_yn;
	}
	public String getPage_flag(){
		return page_flag;
	}
	public void setPage_flag(String page_flag){
		this.page_flag = page_flag;
	}
	public String getCngebfr_cnsd_dept() {
		return cngebfr_cnsd_dept;
	}
	public void setCngebfr_cnsd_dept(String cngebfr_cnsd_dept) {
		this.cngebfr_cnsd_dept = cngebfr_cnsd_dept;
	}
	public String getList_mode() {
		return list_mode;
	}
	public void setList_mode(String list_mode) {
		this.list_mode = list_mode;
	}
	public String getSrch_review_title() {
		return srch_review_title;
	}
	public void setSrch_review_title(String srch_review_title) {
		this.srch_review_title = srch_review_title;
	}
	public String getSrch_start_reqday() {
		return srch_start_reqday;
	}
	public void setSrch_start_reqday(String srch_start_reqday) {
		this.srch_start_reqday = srch_start_reqday;
	}
	public String getSrch_end_reqday() {
		return srch_end_reqday;
	}
	public void setSrch_end_reqday(String srch_end_reqday) {
		this.srch_end_reqday = srch_end_reqday;
	}
	public String getSrch_cnsdman_nm() {
		return srch_cnsdman_nm;
	}
	public void setSrch_cnsdman_nm(String srch_cnsdman_nm) {
		this.srch_cnsdman_nm = srch_cnsdman_nm;
	}
	public String getSrch_resp_dept() {
		return srch_resp_dept;
	}
	public void setSrch_resp_dept(String srch_resp_dept) {
		this.srch_resp_dept = srch_resp_dept;
	}
	public String getSrch_biz_clsfcn() {
		return srch_biz_clsfcn;
	}
	public void setSrch_biz_clsfcn(String srch_biz_clsfcn) {
		this.srch_biz_clsfcn = srch_biz_clsfcn;
	}
	public String getSrch_cnclsnpurps_bigclsfcn() {
		return srch_cnclsnpurps_bigclsfcn;
	}
	public void setSrch_cnclsnpurps_bigclsfcn(String srch_cnclsnpurps_bigclsfcn) {
		this.srch_cnclsnpurps_bigclsfcn = srch_cnclsnpurps_bigclsfcn;
	}
	public String getSrch_step() {
		return srch_step;
	}
	public void setSrch_step(String srch_step) {
		this.srch_step = srch_step;
	}
	public String getSrch_state() {
		return srch_state;
	}
	public void setSrch_state(String srch_state) {
		this.srch_state = srch_state;
	}
	public String getSrch_oppnt_cd() {
		return srch_oppnt_cd;
	}
	public void setSrch_oppnt_cd(String srch_oppnt_cd) {
		this.srch_oppnt_cd = srch_oppnt_cd;
	}
	public String getSrch_start_cnlsnday() {
		return srch_start_cnlsnday;
	}
	public void setSrch_start_cnlsnday(String srch_start_cnlsnday) {
		this.srch_start_cnlsnday = srch_start_cnlsnday;
	}
	public String getSrch_end_cnlsnday() {
		return srch_end_cnlsnday;
	}
	public void setSrch_end_cnlsnday(String srch_end_cnlsnday) {
		this.srch_end_cnlsnday = srch_end_cnlsnday;
	}
	public String getSrch_cnsd_cont() {
		return srch_cnsd_cont;
	}
	public void setSrch_cnsd_cont(String srch_cnsd_cont) {
		this.srch_cnsd_cont = srch_cnsd_cont;
	}
	public String getSrch_if_sys_cd() {
		return srch_if_sys_cd;
	}
	public void setSrch_if_sys_cd(String srch_if_sys_cd) {
		this.srch_if_sys_cd = srch_if_sys_cd;
	}
	public String getSElComp() {
		return sElComp;
	}
	public void setSElComp(String sElComp) {
		this.sElComp = sElComp;
	}
	public String getSrch_solo_yn() {
		return srch_solo_yn;
	}
	public void setSrch_solo_yn(String srch_solo_yn) {
		this.srch_solo_yn = srch_solo_yn;
	}
	
	
	
	public String getSrch_cntrt_no() {
		return srch_cntrt_no;
	}
	public void setSrch_cntrt_no(String srch_cntrt_no) {
		this.srch_cntrt_no = srch_cntrt_no;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blngt_orgnz == null) ? 0 : blngt_orgnz.hashCode());
		result = prime * result + ((close_cont == null) ? 0 : close_cont.hashCode());
		result = prime * result + ((cngebfr_cnsd_dept == null) ? 0 : cngebfr_cnsd_dept.hashCode());
		result = prime * result + ((cnsd_demnd_cont == null) ? 0 : cnsd_demnd_cont.hashCode());
		result = prime * result + ((cnsd_level == null) ? 0 : cnsd_level.hashCode());
		result = prime * result + ((cnsdreq_id == null) ? 0 : cnsdreq_id.hashCode());
		result = prime * result + ((cntrt_id == null) ? 0 : cntrt_id.hashCode());
		result = prime * result + ((cntrt_oppnt_nm == null) ? 0 : cntrt_oppnt_nm.hashCode());
		result = prime * result + ((cntrt_srch_yn == null) ? 0 : cntrt_srch_yn.hashCode());
		result = prime * result + ((db_cnsdreq_id == null) ? 0 : db_cnsdreq_id.hashCode());
		result = prime * result + ((dimension_cd == null) ? 0 : dimension_cd.hashCode());
		result = prime * result + ((dis_yn == null) ? 0 : dis_yn.hashCode());
		result = prime * result + ((dmstfrgn == null) ? 0 : dmstfrgn.hashCode());
		result = prime * result + ((dmstfrgn_gbn == null) ? 0 : dmstfrgn_gbn.hashCode());
		result = prime * result + ((hq_cnsd_demnd_cont == null) ? 0 : hq_cnsd_demnd_cont.hashCode());
		result = prime * result + ((hq_cnsd_status == null) ? 0 : hq_cnsd_status.hashCode());
		result = prime * result + ((hq_cnsdreq_id == null) ? 0 : hq_cnsdreq_id.hashCode());
		result = prime * result + ((hq_req_title == null) ? 0 : hq_req_title.hashCode());
		result = prime * result + ((hq_req_yn == null) ? 0 : hq_req_yn.hashCode());
		result = prime * result + ((item_cd == null) ? 0 : item_cd.hashCode());
		result = prime * result + ((lastbn_chge_yn == null) ? 0 : lastbn_chge_yn.hashCode());
		result = prime * result + ((list_mode == null) ? 0 : list_mode.hashCode());
		result = prime * result + ((mark_cntrt_id == null) ? 0 : mark_cntrt_id.hashCode());
		result = prime * result + ((mark_num == null) ? 0 : mark_num.hashCode());
		result = prime * result + ((page_flag == null) ? 0 : page_flag.hashCode());
		result = prime * result + ((plndbn_chge_cont == null) ? 0 : plndbn_chge_cont.hashCode());
		result = prime * result + ((plndbn_req_yn == null) ? 0 : plndbn_req_yn.hashCode());
		result = prime * result + ((prev_cnsdreq_id == null) ? 0 : prev_cnsdreq_id.hashCode());
		result = prime * result + ((prev_hq_cnsdreq_id == null) ? 0 : prev_hq_cnsdreq_id.hashCode());
		result = prime * result + ((prgrs_status == null) ? 0 : prgrs_status.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((req_dept == null) ? 0 : req_dept.hashCode());
		result = prime * result + ((resp_key == null) ? 0 : resp_key.hashCode());
		result = prime * result + ((rtn_cont == null) ? 0 : rtn_cont.hashCode());
		result = prime * result + ((sElComp == null) ? 0 : sElComp.hashCode());
		result = prime * result + ((sMultComp == null) ? 0 : sMultComp.hashCode());
		result = prime * result + ((srch_biz_clsfcn == null) ? 0 : srch_biz_clsfcn.hashCode());
		result = prime * result + ((srch_biz_depth == null) ? 0 : srch_biz_depth.hashCode());
		result = prime * result + ((srch_closed_yn == null) ? 0 : srch_closed_yn.hashCode());
		result = prime * result + ((srch_cnclsnpurps == null) ? 0 : srch_cnclsnpurps.hashCode());
		result = prime * result + ((srch_cnclsnpurps_bigclsfcn == null) ? 0 : srch_cnclsnpurps_bigclsfcn.hashCode());
		result = prime * result + ((srch_cnsd_cont == null) ? 0 : srch_cnsd_cont.hashCode());
		result = prime * result + ((srch_cnsdman_nm == null) ? 0 : srch_cnsdman_nm.hashCode());
		result = prime * result + ((srch_cntrt_oppnt_nm == null) ? 0 : srch_cntrt_oppnt_nm.hashCode());
		result = prime * result + ((srch_end_cnlsnday == null) ? 0 : srch_end_cnlsnday.hashCode());
		result = prime * result + ((srch_end_dt == null) ? 0 : srch_end_dt.hashCode());
		result = prime * result + ((srch_end_reqday == null) ? 0 : srch_end_reqday.hashCode());
		result = prime * result + ((srch_gerp_code == null) ? 0 : srch_gerp_code.hashCode());
		result = prime * result + ((srch_hq_reviewer_nm == null) ? 0 : srch_hq_reviewer_nm.hashCode());
		result = prime * result + ((srch_if_sys_cd == null) ? 0 : srch_if_sys_cd.hashCode());
		result = prime * result + ((srch_ip_status == null) ? 0 : srch_ip_status.hashCode());
		result = prime * result + ((srch_law_status == null) ? 0 : srch_law_status.hashCode());
		result = prime * result + ((srch_oppnt_cd == null) ? 0 : srch_oppnt_cd.hashCode());
		result = prime * result + ((srch_orgnz == null) ? 0 : srch_orgnz.hashCode());
		result = prime * result + ((srch_owner_dept == null) ? 0 : srch_owner_dept.hashCode());
		result = prime * result + ((srch_prgrs_status == null) ? 0 : srch_prgrs_status.hashCode());
		result = prime * result + ((srch_req_title == null) ? 0 : srch_req_title.hashCode());
		result = prime * result + ((srch_reqman_id == null) ? 0 : srch_reqman_id.hashCode());
		result = prime * result + ((srch_reqman_nm == null) ? 0 : srch_reqman_nm.hashCode());
		result = prime * result + ((srch_resp_dept == null) ? 0 : srch_resp_dept.hashCode());
		result = prime * result + ((srch_respman_id == null) ? 0 : srch_respman_id.hashCode());
		result = prime * result + ((srch_respman_nm == null) ? 0 : srch_respman_nm.hashCode());
		result = prime * result + ((srch_review_title == null) ? 0 : srch_review_title.hashCode());
		result = prime * result + ((srch_solo_yn == null) ? 0 : srch_solo_yn.hashCode());
		result = prime * result + ((srch_start_cnlsnday == null) ? 0 : srch_start_cnlsnday.hashCode());
		result = prime * result + ((srch_start_dt == null) ? 0 : srch_start_dt.hashCode());
		result = prime * result + ((srch_start_reqday == null) ? 0 : srch_start_reqday.hashCode());
		result = prime * result + ((srch_state == null) ? 0 : srch_state.hashCode());
		result = prime * result + ((srch_step == null) ? 0 : srch_step.hashCode());
		result = prime * result + ((srch_type_cd == null) ? 0 : srch_type_cd.hashCode());
		result = prime * result + ((stat_flag == null) ? 0 : stat_flag.hashCode());
		result = prime * result + ((sum_text == null) ? 0 : sum_text.hashCode());
		result = prime * result + ((top_role == null) ? 0 : top_role.hashCode());
		return result;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsiderationVO other = (ConsiderationVO) obj;
		if (blngt_orgnz == null) {
			if (other.blngt_orgnz != null)
				return false;
		} else if (!blngt_orgnz.equals(other.blngt_orgnz))
			return false;
		if (close_cont == null) {
			if (other.close_cont != null)
				return false;
		} else if (!close_cont.equals(other.close_cont))
			return false;
		if (cngebfr_cnsd_dept == null) {
			if (other.cngebfr_cnsd_dept != null)
				return false;
		} else if (!cngebfr_cnsd_dept.equals(other.cngebfr_cnsd_dept))
			return false;
		if (cnsd_demnd_cont == null) {
			if (other.cnsd_demnd_cont != null)
				return false;
		} else if (!cnsd_demnd_cont.equals(other.cnsd_demnd_cont))
			return false;
		if (cnsd_level == null) {
			if (other.cnsd_level != null)
				return false;
		} else if (!cnsd_level.equals(other.cnsd_level))
			return false;
		if (cnsdreq_id == null) {
			if (other.cnsdreq_id != null)
				return false;
		} else if (!cnsdreq_id.equals(other.cnsdreq_id))
			return false;
		if (cntrt_id == null) {
			if (other.cntrt_id != null)
				return false;
		} else if (!cntrt_id.equals(other.cntrt_id))
			return false;
		if (cntrt_oppnt_nm == null) {
			if (other.cntrt_oppnt_nm != null)
				return false;
		} else if (!cntrt_oppnt_nm.equals(other.cntrt_oppnt_nm))
			return false;
		if (cntrt_srch_yn == null) {
			if (other.cntrt_srch_yn != null)
				return false;
		} else if (!cntrt_srch_yn.equals(other.cntrt_srch_yn))
			return false;
		if (db_cnsdreq_id == null) {
			if (other.db_cnsdreq_id != null)
				return false;
		} else if (!db_cnsdreq_id.equals(other.db_cnsdreq_id))
			return false;
		if (dimension_cd == null) {
			if (other.dimension_cd != null)
				return false;
		} else if (!dimension_cd.equals(other.dimension_cd))
			return false;
		if (dis_yn == null) {
			if (other.dis_yn != null)
				return false;
		} else if (!dis_yn.equals(other.dis_yn))
			return false;
		if (dmstfrgn == null) {
			if (other.dmstfrgn != null)
				return false;
		} else if (!dmstfrgn.equals(other.dmstfrgn))
			return false;
		if (dmstfrgn_gbn == null) {
			if (other.dmstfrgn_gbn != null)
				return false;
		} else if (!dmstfrgn_gbn.equals(other.dmstfrgn_gbn))
			return false;
		if (hq_cnsd_demnd_cont == null) {
			if (other.hq_cnsd_demnd_cont != null)
				return false;
		} else if (!hq_cnsd_demnd_cont.equals(other.hq_cnsd_demnd_cont))
			return false;
		if (hq_cnsd_status == null) {
			if (other.hq_cnsd_status != null)
				return false;
		} else if (!hq_cnsd_status.equals(other.hq_cnsd_status))
			return false;
		if (hq_cnsdreq_id == null) {
			if (other.hq_cnsdreq_id != null)
				return false;
		} else if (!hq_cnsdreq_id.equals(other.hq_cnsdreq_id))
			return false;
		if (hq_req_title == null) {
			if (other.hq_req_title != null)
				return false;
		} else if (!hq_req_title.equals(other.hq_req_title))
			return false;
		if (hq_req_yn == null) {
			if (other.hq_req_yn != null)
				return false;
		} else if (!hq_req_yn.equals(other.hq_req_yn))
			return false;
		if (item_cd == null) {
			if (other.item_cd != null)
				return false;
		} else if (!item_cd.equals(other.item_cd))
			return false;
		if (lastbn_chge_yn == null) {
			if (other.lastbn_chge_yn != null)
				return false;
		} else if (!lastbn_chge_yn.equals(other.lastbn_chge_yn))
			return false;
		if (list_mode == null) {
			if (other.list_mode != null)
				return false;
		} else if (!list_mode.equals(other.list_mode))
			return false;
		if (mark_cntrt_id == null) {
			if (other.mark_cntrt_id != null)
				return false;
		} else if (!mark_cntrt_id.equals(other.mark_cntrt_id))
			return false;
		if (mark_num == null) {
			if (other.mark_num != null)
				return false;
		} else if (!mark_num.equals(other.mark_num))
			return false;
		if (page_flag == null) {
			if (other.page_flag != null)
				return false;
		} else if (!page_flag.equals(other.page_flag))
			return false;
		if (plndbn_chge_cont == null) {
			if (other.plndbn_chge_cont != null)
				return false;
		} else if (!plndbn_chge_cont.equals(other.plndbn_chge_cont))
			return false;
		if (plndbn_req_yn == null) {
			if (other.plndbn_req_yn != null)
				return false;
		} else if (!plndbn_req_yn.equals(other.plndbn_req_yn))
			return false;
		if (prev_cnsdreq_id == null) {
			if (other.prev_cnsdreq_id != null)
				return false;
		} else if (!prev_cnsdreq_id.equals(other.prev_cnsdreq_id))
			return false;
		if (prev_hq_cnsdreq_id == null) {
			if (other.prev_hq_cnsdreq_id != null)
				return false;
		} else if (!prev_hq_cnsdreq_id.equals(other.prev_hq_cnsdreq_id))
			return false;
		if (prgrs_status == null) {
			if (other.prgrs_status != null)
				return false;
		} else if (!prgrs_status.equals(other.prgrs_status))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (req_dept == null) {
			if (other.req_dept != null)
				return false;
		} else if (!req_dept.equals(other.req_dept))
			return false;
		if (resp_key == null) {
			if (other.resp_key != null)
				return false;
		} else if (!resp_key.equals(other.resp_key))
			return false;
		if (rtn_cont == null) {
			if (other.rtn_cont != null)
				return false;
		} else if (!rtn_cont.equals(other.rtn_cont))
			return false;
		if (sElComp == null) {
			if (other.sElComp != null)
				return false;
		} else if (!sElComp.equals(other.sElComp))
			return false;
		if (sMultComp == null) {
			if (other.sMultComp != null)
				return false;
		} else if (!sMultComp.equals(other.sMultComp))
			return false;
		if (srch_biz_clsfcn == null) {
			if (other.srch_biz_clsfcn != null)
				return false;
		} else if (!srch_biz_clsfcn.equals(other.srch_biz_clsfcn))
			return false;
		if (srch_biz_depth == null) {
			if (other.srch_biz_depth != null)
				return false;
		} else if (!srch_biz_depth.equals(other.srch_biz_depth))
			return false;
		if (srch_closed_yn == null) {
			if (other.srch_closed_yn != null)
				return false;
		} else if (!srch_closed_yn.equals(other.srch_closed_yn))
			return false;
		if (srch_cnclsnpurps == null) {
			if (other.srch_cnclsnpurps != null)
				return false;
		} else if (!srch_cnclsnpurps.equals(other.srch_cnclsnpurps))
			return false;
		if (srch_cnclsnpurps_bigclsfcn == null) {
			if (other.srch_cnclsnpurps_bigclsfcn != null)
				return false;
		} else if (!srch_cnclsnpurps_bigclsfcn.equals(other.srch_cnclsnpurps_bigclsfcn))
			return false;
		if (srch_cnsd_cont == null) {
			if (other.srch_cnsd_cont != null)
				return false;
		} else if (!srch_cnsd_cont.equals(other.srch_cnsd_cont))
			return false;
		if (srch_cnsdman_nm == null) {
			if (other.srch_cnsdman_nm != null)
				return false;
		} else if (!srch_cnsdman_nm.equals(other.srch_cnsdman_nm))
			return false;
		if (srch_cntrt_oppnt_nm == null) {
			if (other.srch_cntrt_oppnt_nm != null)
				return false;
		} else if (!srch_cntrt_oppnt_nm.equals(other.srch_cntrt_oppnt_nm))
			return false;
		if (srch_end_cnlsnday == null) {
			if (other.srch_end_cnlsnday != null)
				return false;
		} else if (!srch_end_cnlsnday.equals(other.srch_end_cnlsnday))
			return false;
		if (srch_end_dt == null) {
			if (other.srch_end_dt != null)
				return false;
		} else if (!srch_end_dt.equals(other.srch_end_dt))
			return false;
		if (srch_end_reqday == null) {
			if (other.srch_end_reqday != null)
				return false;
		} else if (!srch_end_reqday.equals(other.srch_end_reqday))
			return false;
		if (srch_gerp_code == null) {
			if (other.srch_gerp_code != null)
				return false;
		} else if (!srch_gerp_code.equals(other.srch_gerp_code))
			return false;
		if (srch_hq_reviewer_nm == null) {
			if (other.srch_hq_reviewer_nm != null)
				return false;
		} else if (!srch_hq_reviewer_nm.equals(other.srch_hq_reviewer_nm))
			return false;
		if (srch_if_sys_cd == null) {
			if (other.srch_if_sys_cd != null)
				return false;
		} else if (!srch_if_sys_cd.equals(other.srch_if_sys_cd))
			return false;
		if (srch_ip_status == null) {
			if (other.srch_ip_status != null)
				return false;
		} else if (!srch_ip_status.equals(other.srch_ip_status))
			return false;
		if (srch_law_status == null) {
			if (other.srch_law_status != null)
				return false;
		} else if (!srch_law_status.equals(other.srch_law_status))
			return false;
		if (srch_oppnt_cd == null) {
			if (other.srch_oppnt_cd != null)
				return false;
		} else if (!srch_oppnt_cd.equals(other.srch_oppnt_cd))
			return false;
		if (srch_orgnz == null) {
			if (other.srch_orgnz != null)
				return false;
		} else if (!srch_orgnz.equals(other.srch_orgnz))
			return false;
		if (srch_owner_dept == null) {
			if (other.srch_owner_dept != null)
				return false;
		} else if (!srch_owner_dept.equals(other.srch_owner_dept))
			return false;
		if (srch_prgrs_status == null) {
			if (other.srch_prgrs_status != null)
				return false;
		} else if (!srch_prgrs_status.equals(other.srch_prgrs_status))
			return false;
		if (srch_req_title == null) {
			if (other.srch_req_title != null)
				return false;
		} else if (!srch_req_title.equals(other.srch_req_title))
			return false;
		if (srch_reqman_id == null) {
			if (other.srch_reqman_id != null)
				return false;
		} else if (!srch_reqman_id.equals(other.srch_reqman_id))
			return false;
		if (srch_reqman_nm == null) {
			if (other.srch_reqman_nm != null)
				return false;
		} else if (!srch_reqman_nm.equals(other.srch_reqman_nm))
			return false;
		if (srch_resp_dept == null) {
			if (other.srch_resp_dept != null)
				return false;
		} else if (!srch_resp_dept.equals(other.srch_resp_dept))
			return false;
		if (srch_respman_id == null) {
			if (other.srch_respman_id != null)
				return false;
		} else if (!srch_respman_id.equals(other.srch_respman_id))
			return false;
		if (srch_respman_nm == null) {
			if (other.srch_respman_nm != null)
				return false;
		} else if (!srch_respman_nm.equals(other.srch_respman_nm))
			return false;
		if (srch_review_title == null) {
			if (other.srch_review_title != null)
				return false;
		} else if (!srch_review_title.equals(other.srch_review_title))
			return false;
		if (srch_solo_yn == null) {
			if (other.srch_solo_yn != null)
				return false;
		} else if (!srch_solo_yn.equals(other.srch_solo_yn))
			return false;
		if (srch_start_cnlsnday == null) {
			if (other.srch_start_cnlsnday != null)
				return false;
		} else if (!srch_start_cnlsnday.equals(other.srch_start_cnlsnday))
			return false;
		if (srch_start_dt == null) {
			if (other.srch_start_dt != null)
				return false;
		} else if (!srch_start_dt.equals(other.srch_start_dt))
			return false;
		if (srch_start_reqday == null) {
			if (other.srch_start_reqday != null)
				return false;
		} else if (!srch_start_reqday.equals(other.srch_start_reqday))
			return false;
		if (srch_state == null) {
			if (other.srch_state != null)
				return false;
		} else if (!srch_state.equals(other.srch_state))
			return false;
		if (srch_step == null) {
			if (other.srch_step != null)
				return false;
		} else if (!srch_step.equals(other.srch_step))
			return false;
		if (srch_type_cd == null) {
			if (other.srch_type_cd != null)
				return false;
		} else if (!srch_type_cd.equals(other.srch_type_cd))
			return false;
		if (stat_flag == null) {
			if (other.stat_flag != null)
				return false;
		} else if (!stat_flag.equals(other.stat_flag))
			return false;
		if (sum_text == null) {
			if (other.sum_text != null)
				return false;
		} else if (!sum_text.equals(other.sum_text))
			return false;
		if (top_role == null) {
			if (other.top_role != null)
				return false;
		} else if (!top_role.equals(other.top_role))
			return false;
		return true;
	}
	
	
	
}