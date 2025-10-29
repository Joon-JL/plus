package com.sec.clm.review.dto;

import com.sds.secframework.common.dto.CommonForm;
import com.sds.secframework.common.util.DateUtil;

public class ConsiderationForm extends CommonForm {
	
	/**********************************************
	 * 검색 조건
	 **********************************************/
	/** 분류코드 */
	private String dimension_cd;
	/** 소속조직 */
	private String blngt_orgnz;
	
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
	// 전자 변호사일 경우 회사를 선택할 수 있는 검색 조건 
	private String sElComp;
	// 전자 담당자일 경우 모든 회사를 선택할 수 있는 검색 조건 
	private String sSel_grd;
	
	/** 계약상대방 */
	private String cntrt_oppnt_nm;
	/** 예정본 의뢰 여부 */
	private String plndbn_req_yn;
	
	/** 검토의뢰_ID */
	private String cnsdreq_id;
	/** 계약_ID */
	private String cntrt_id;
	/** 상대회사관련 계약건 검색 여부 */
	private String cntrt_srch_yn;
	/** 페이지 구분 */
	private String page_flag;
	
	/** 진행상태 **/
	private String stat_flag;
	
	/** DMSTFRGN **/
	private String dmstfrgn;
	
	/** 검토방식(단독여부) */
	private String srch_solo_yn;
	 
	/**********************************************
	 * 페이징
	 **********************************************/
	/** 페이지(공통) */
	protected String start_index;                      
	/** 페이지(공통) */
	protected String end_index;                        
	/** 페이지(공통) */
	protected String curPage;  
	/** 페이지당 row 수 */
	int row_cnt = 10;

	
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
	private String srch_resp_dept_nm;
	private String srch_oppnt_nm;
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
	
	private String sMultComp;
	
	private String sMultYn;
	
	// gerp_code : 2014-09-01 김미정 GERP Code
	private String srch_gerp_code;
	
	private String srch_cntrt_no;
			
	/**********************************************
	 * 입력값
	 **********************************************/
	
	
	public String getMark_num() {
		return mark_num;
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
	public void setMark_num(String mark_num) {
		this.mark_num = mark_num;
	}
	/**********************************************
	 * getter, setter 메소드
	 **********************************************/
	
	public String getDimension_cd() {
		return dimension_cd;
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
	public String getsMultComp() {
		return sMultComp;
	}
	public void setsMultComp(String sMultComp) {
		this.sMultComp = sMultComp;
	}
	public String getsMultYn() {
		return sMultYn;
	}
	public void setsMultYn(String sMultYn) {
		this.sMultYn = sMultYn;
	}
	public String getSrch_gerp_code() {
		return srch_gerp_code;
	}
	public void setSrch_gerp_code(String srch_gerp_code) {
		this.srch_gerp_code = srch_gerp_code;
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
	public String getSrch_cnclsnpurps() {
		return srch_cnclsnpurps;
	}
	public void setSrch_cnclsnpurps(String srch_cnclsnpurps) {
		this.srch_cnclsnpurps = srch_cnclsnpurps;
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
	public String getStart_index() {
		return start_index;
	}
	public void setStart_index(String start_index) {
		this.start_index = start_index;
	}
	public String getEnd_index() {
		return end_index;
	}
	public void setEnd_index(String end_index) {
		this.end_index = end_index;
	}
	public String getCurPage() {
		return curPage;
	}
	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}
	public int getRow_cnt() {
		return row_cnt;
	}
	public void setRow_cnt(int row_cnt) {
		this.row_cnt = row_cnt;
	}
	public String getCnsdreq_id() {
		return cnsdreq_id;
	}
	public void setCnsdreq_id(String cnsdreq_id) {
		this.cnsdreq_id = cnsdreq_id;
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
	public String getStat_flag(){
		return stat_flag;
	}
	public void setStat_flag(String stat_flag){
		this.stat_flag = stat_flag;
	}
	public String getDmstfrgn(){
		return dmstfrgn;
	}
	public void setDmstfrgn(String dmstfrgn){
		this.dmstfrgn = dmstfrgn;
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
	public String getSrch_resp_dept_nm() {
		return srch_resp_dept_nm;
	}
	public void setSrch_resp_dept_nm(String srch_resp_dept_nm) {
		this.srch_resp_dept_nm = srch_resp_dept_nm;
	}
	public String getSrch_oppnt_nm() {
		return srch_oppnt_nm;
	}
	public void setSrch_oppnt_nm(String srch_oppnt_nm) {
		this.srch_oppnt_nm = srch_oppnt_nm;
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
	public String getsElComp() {
		return sElComp;
	}
	public void setsElComp(String sElComp) {
		this.sElComp = sElComp;
	}
	
	public String getsSel_grd() {
		return sSel_grd;
	}

	public void setsSel_grd(String sSel_grd) {
		this.sSel_grd = sSel_grd;
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
	
}