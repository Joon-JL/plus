/** 
 * Project Name : 법무시스템 이식
 * File Name : LawConsultVO.java
 * Description : 법률자문 Vo
 * Author : 김현구
 * Date : 2011. 08. 30
 * Copyright : 
 */

package com.sec.las.lawconsulting.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import com.sds.secframework.common.dto.CommonVO;
import com.sds.secframework.common.util.DateUtil;

/**
* Description : 법률자문 Vo
* Author : 김현구
* Date : 2011.08.30
*/
public class LawConsultVO extends CommonVO {
	
	/**********************************************
	 * DB COLUMN(TABLE : TN_LAS_CONSULT)
	 **********************************************/
	/**자문_번호*/
	private String cnslt_no;
	/**이력_번호*/
	private int hstry_no;
	/**자문_위치*/
	private int cnslt_pos;
	/**자문_순서*/
	private int cnslt_srt;
	/**국내해외_구분*/
	private String dmstfrgn_gbn;	
	/**제목*/
	private String title;
	/**내용*/
	private String cont;
	/** 나모웹에디터  - 내용 */
	private String body_mime;
	/**진행_상태*/
	private String prgrs_status;
	
	/**진행_상태(의뢰인)*/
	private String extnl_prgrs_status;
	/**진행_상태(그룹장,담당자)*/
	private String intnl_prgrs_status;
	
	/**진행_상태 이름*/
	private String prgrs_status_name;
	/**주요_사안_내용*/
	private String main_matr_cont;
	/**반려_사유*/
	private String rejct_cause;
	/**보류_사유*/
	private String hold_cause;
	/**외부_자문여부*/
	private String extnl_cnsltyn;
	/**자문_금액*/
	private String cnslt_amt;
	/**주간업무여부*/
	private String weekdutyyn;
	/**월간업무여부*/
	private String monthdutyyn;
	/**확인_주간업무여부*/
	private String conf_weekdutyyn;
	/**확인_월간업무여부*/
	private String conf_monthdutyyn;
	/**그룹장_회신부서*/
	private String grpmgr_redept;
	/**그룹장_의견*/
	//private String grpmgr_opnn;
	/**그룹장_반려_사유*/
	//private String grpmgr_rejct_cause;
	/**결재일*/
	private String apprvlday;
	/**등록_일시 timestamp*/
	private Timestamp reg_dt_ts;
	/**등록_일시*/
	private String reg_dt;
	/**등록자_ID*/
	private String reg_id;
	/**등록자_명*/
	private String reg_nm;
	/**등록_전화번호*/
	private String reg_dept;
	/**내부부서코드*/
	private String reg_intnl_dept;
	/**blngt_orgnz*/
	private String reg_operdiv;
	/**등록_부서*/
	private String reg_telno;
	/**등록_부서_명*/
	private String reg_dept_nm;
	/**등록자 직급*/
	private String regman_jikgup_nm;
	/**수정_일시*/
	private String mod_dt;
	/**수정자_ID*/
	private String mod_id;
	/**수정자_명*/
	private String mod_nm;
	/**삭제_여부*/
	private String del_yn;
	/**삭졔_일시*/
	private String del_dt;
	/**삭제자_ID*/
	private String del_id;
	/**삭제자_명*/
	private String del_nm;
	
	/**공개_여부*/
	private String pub_yn;
	/**그룹장_회신_여부*/
	private String grpmgr_re_yn;
	/**그룹장_id*/
	private String grpmgr_id;
	/**그룹장_명*/
	private String grpmgr_nm;
	/**그룹장_지시_내용(메모)*/
	private String ordr_cont;
	/**검토자_id*/
	private String cnsdman_id;
	/**검토자_명*/
	private String cnsdman_nm;
	/**검토_일시*/
	private String cnsd_dt;
	/**김토_의견*/
	private String cnsd_opnn;
	/**승인자_id*/
	private String apbtman_id;
	/**승인자_명*/
	private String apbtman_nm;
	/**승인_의견*/
	private String apbt_opnn;
	/**회신_일시 */
	private String re_dt;
	/**담당자 id */
	private String respman_id;
	/**담당자 이름 */
	private String respman_nm;
	/** 검토의뢰_부서 */
	private String req_dept;
	
	/** 완료 여부*/
	private String complete_yn;
	
	/** 회신 요청일*/
	private String req_reply_dt;
	
	
	private String comp_cd;
	
	/** 중요도표시 
	 * 1 : 디폴트 ( 선택없음 흰동그라미)
	 * 3 : 중요표시 (붉은동그라미)
	 **/
	private String mark_num;
	
	
	/**********************************************
	 * PAGE 관련
	 **********************************************/
	
	/**페이지 시작*/
	private String start_index;
	/**페이지 끝*/
	private String end_index;
	/**현재 페이지*/
	private String curPage;
		
	
	/**포워딩 페이지 구분*/
	private String fwd_gbn;
	
	
	// ---- 참조자(CC) 
	
	/** 참조자 ID*/
	private String[] arr_trgtman_id;
	private String trgtman_id;
	
	/** 참조자 이름*/
	private String[] arr_trgtman_nm;
	private String trgtman_nm;
	
	/** 참조자 부서명*/
	private String[] arr_trgtman_dept_nm;
	private String trgtman_dept_nm;
	
	/** 참조자 직급 */
	private String[] arr_trgtman_jikgup_nm;
	private String trgtman_jikgup_nm;

	
	/**********************************************
	 * 검색 조건
	 **********************************************/
	
	
	/**검색어 - 담당자*/
	private String srch_respman_nm;
	/**검색어 - 제목*/
	private String srch_title;
	/**검색어 - 내용*/
	private String srch_cont;
	/**검색어 - 의뢰인*/
	private String srch_reg_nm;
	/**검색어 - 진행상황*/
	private String srch_prgrs_status;
	/**통계화면 검색조건 - 진행상황 코드 */	
	private String srch_prgrs_status_cd;
	/**검색어 - 총괄(등록부서) */
	private String srch_reg_dept;
	
	/**검색어 - 자문유형 */
	private String srch_consult_type;
	private String srch_consult_type_name;

	/** 조회시작일자(의뢰일)*/
	private String srch_start_ymd;
	/**조회종료일자(의뢰일)*/
	private String srch_end_ymd;
	
	private String srch_reg_id;
	
	private String srch_reception;
	
	private String srch_elcomp;
	
	private String srch_solo_yn;
	
	/** 자문의뢰 완료 여부 */
	private String srch_complete_yn;

	/** 통계(Statistics)에서 회사별 상세현황 조회시 */
	private String srch_comp_cd;	
	
	/*********************************************/
	
	/**국내/해외 여부*/
	private String isForeign;
	
	private List consult_type_list;
	private List srch_consult_type_list;
	
	private String consult_type;
	private String consult_type_name;
	
	/**첨부파일 추가 부분 */
	private String fileInfos2;
	private String fileInfos3;
	private String file_midclsfcn2;

	/**외부기관 관련 변수 */
	private String[] extnl_list;
	private List extnlList;
	private String extnl_comp_nm;
	//private long extnl_cnslt_amt;
	//private BigDecimal extnl_cnslt_amt;
	private long extnl_cnslt_amt;
	private String extnl_crrncy_unit;
	private long extnl_krw_amt;
	private int extnl_seq;
	
	/**그룹장 여부 판별*/
	private String isGrpmgr;
	
	/**담당자 여부 판별*/
	private String isRespman;
	
	/**Review화면에서 넘어온 작업인지 판별 */
	private String isReview;

	/** 담당자 리스트*/
	private String[] user_list;
	private String[] respman_list;
	
	/**검토반려 이력 seq*/
	private int seqno;
	
	/**반려 의견*/
	private String rejct_opnn;
	
	/** 권한직위 */
	private String top_role;
	/** 소속조직 */
	private String blngt_orgnz;
	
	private String email;
	
	private String now_intnl_prgrs_status;
	
	/**그룹장_회신_여부*/
	private String grpmgr_re_yn_value;
	
	/**IF구분*/
	private String if_gbn;
	/**IF KEY_NO*/
	private String if_key_no;
	/**IF FLAG*/
	private String if_flag;
	/**IF 회신의견*/
	private String cnsd_opnn_body;
	private String check_prgrs_status;
	
	private String readLine;
	/**IF ERROR*/
	private String if_status;
	
    /**IF 회신자ID*/
    private String regpsn_id;
    /**IF 회신자명*/
    private String regpsn_nm;
    /**IF 회신자 부서코드*/
    private String regpsn_dept_cd;
    /**IF 회신자 부서명*/
    private String regpsn_dept_nm;
    
    private String to_transfer;
    
    
    private String cnslt_type;
    
    private String solo_yn;
    
    private String contents;
    
    //법률자문/표준계역서 구분
    private String isStdCont;
	
	/**생성자*/
	public LawConsultVO() throws Exception {
		//srch_start_ymd = DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), -365), "-");
		//srch_end_ymd = DateUtil.formatDate(DateUtil.today(), "-");
		
		cnslt_pos = 0;
		cnslt_srt = 0;
		extnl_cnsltyn = "N";
		cnslt_amt = "0";
		weekdutyyn = "Y";
		monthdutyyn = "Y";
		conf_weekdutyyn = "Y";
		conf_monthdutyyn = "Y";
		del_yn = "N";
		grpmgr_re_yn = "N";
		pub_yn = "Y";
		isRespman = "N";
		isReview = "N";
		isGrpmgr = "N";
		extnl_cnsltyn = "N";
	}
	
	public void setCnslt_no(String cnslt_no) {
		this.cnslt_no = cnslt_no;
	}
	public void setHstry_no(int hstry_no) {
		this.hstry_no = hstry_no;
	}
	public void setCnslt_pos(int cnslt_pos) {
		this.cnslt_pos = cnslt_pos;
	}
	public void setCnslt_srt(int cnslt_srt) {
		this.cnslt_srt = cnslt_srt;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	public void setPrgrs_status(String prgrs_status) {
		this.prgrs_status = prgrs_status;
	}
	public void setMain_matr_cont(String main_matr_cont) {
		this.main_matr_cont = main_matr_cont;
	}
	public void setRejct_cause(String rejct_cause) {
		this.rejct_cause = rejct_cause;
	}
	public void setHold_cause(String hold_cause) {
		this.hold_cause = hold_cause;
	}
	public void setExtnl_cnsltyn(String extnl_cnsltyn) {
		this.extnl_cnsltyn = extnl_cnsltyn;
	}
	public void setCnslt_amt(String cnslt_amt) {
		this.cnslt_amt = cnslt_amt;
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
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public void setReg_nm(String reg_nm) {
		this.reg_nm = reg_nm;
	}
	public void setReg_dept(String reg_dept) {
		this.reg_dept = reg_dept;
	}
	public void setReg_telno(String reg_telno) {
		this.reg_telno = reg_telno;
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
	public void setDmstfrgn_gbn(String dmstfrgn_gbn) {
		this.dmstfrgn_gbn = dmstfrgn_gbn;
	}
	public void setApprvlday(String apprvlday) {
		this.apprvlday = apprvlday;
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
	public String getCnslt_no() {
		return this.cnslt_no;
	}
	public int getHstry_no() {
		return this.hstry_no;
	}
	public int getCnslt_pos() {
		return this.cnslt_pos;
	}
	public int getCnslt_srt() {
		return this.cnslt_srt;
	}
	public String getTitle() {
		return this.title;
	}
	public String getCont() {
		return this.cont;
	}
	public String getPrgrs_status() {
		return this.prgrs_status;
	}
	public String getMain_matr_cont() {
		return this.main_matr_cont;
	}
	public String getRejct_cause() {
		return this.rejct_cause;
	}
	public String getHold_cause() {
		return this.hold_cause;
	}
	public String getExtnl_cnsltyn() {
		return this.extnl_cnsltyn;
	}
	public String getCnslt_amt() {
		return this.cnslt_amt;
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

	public String getReg_id() {
		return this.reg_id;
	}
	public String getReg_nm() {
		return this.reg_nm;
	}
	public String getReg_dept() {
		return this.reg_dept;
	}
	public String getReg_telno() {
		return this.reg_telno;
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
	public String getDmstfrgn_gbn() {
		return this.dmstfrgn_gbn;
	}
	public String getApprvlday() {
		return this.apprvlday;
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

	public String getSrch_respman_nm() {
		return srch_respman_nm;
	}

	public void setSrch_respman_nm(String srch_respman_nm) {
		this.srch_respman_nm = srch_respman_nm;
	}

	public String getReq_dept() {
		return req_dept;
	}
	
	public void setReq_dept(String req_dept) {
		this.req_dept = req_dept;
	}
	
	public String getSrch_title() {
		return srch_title;
	}

	public void setSrch_title(String srch_title) {
		this.srch_title = srch_title;
	}

	public String getSrch_cont() {
		return srch_cont;
	}

	public void setSrch_cont(String srch_cont) {
		this.srch_cont = srch_cont;
	}

	public String getSrch_reg_nm() {
		return srch_reg_nm;
	}

	public void setSrch_reg_nm(String srch_reg_nm) {
		this.srch_reg_nm = srch_reg_nm;
	}

	public String getSrch_prgrs_status() {
		return srch_prgrs_status;
	}

	public void setSrch_prgrs_status(String srch_prgrs_status) {
		this.srch_prgrs_status = srch_prgrs_status;
	}

	public String getSrch_reg_dept() {
		return srch_reg_dept;
	}

	public void setSrch_reg_dept(String srch_reg_dept) {
		this.srch_reg_dept = srch_reg_dept;
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

	public String getIsForeign() {
		return isForeign;
	}

	public void setIsForeign(String isForeign) {
		this.isForeign = isForeign;
	}

	public String getBody_mime() {
		return body_mime;
	}

	public List getConsult_type_list() {
		return consult_type_list;
	}

	public void setConsult_type_list(List consult_type_list) {
		this.consult_type_list = consult_type_list;
	}

	public void setBody_mime(String body_mime) {
		this.body_mime = body_mime;
	}

	public String getConsult_type() {
		return consult_type;
	}

	public void setConsult_type(String consult_type) {
		this.consult_type = consult_type;
	}

	public String getPrgrs_status_name() {
		return prgrs_status_name;
	}

	public void setPrgrs_status_name(String prgrs_status_name) {
		this.prgrs_status_name = prgrs_status_name;
	}

	public String getConsult_type_name() {
		return consult_type_name;
	}

	public void setConsult_type_name(String consult_type_name) {
		this.consult_type_name = consult_type_name;
	}

	public String getSrch_consult_type() {
		return srch_consult_type;
	}

	public void setSrch_consult_type(String srch_consult_type) {
		this.srch_consult_type = srch_consult_type;
	}

	public String getSrch_consult_type_name() {
		return srch_consult_type_name;
	}

	public void setSrch_consult_type_name(String srch_consult_type_name) {
		this.srch_consult_type_name = srch_consult_type_name;
	}

	public List getSrch_consult_type_list() {
		return srch_consult_type_list;
	}

	public void setSrch_consult_type_list(List srch_consult_type_list) {
		this.srch_consult_type_list = srch_consult_type_list;
	}

	public String getPub_yn() {
		return pub_yn;
	}

	public void setPub_yn(String pub_yn) {
		this.pub_yn = pub_yn;
	}

	public String getGrpmgr_re_yn() {
		return grpmgr_re_yn;
	}

	public void setGrpmgr_re_yn(String grpmgr_re_yn) {
		this.grpmgr_re_yn = grpmgr_re_yn;
	}

	public String getGrpmgr_id() {
		return grpmgr_id;
	}

	public void setGrpmgr_id(String grpmgr_id) {
		this.grpmgr_id = grpmgr_id;
	}

	public String getGrpmgr_nm() {
		return grpmgr_nm;
	}

	public void setGrpmgr_nm(String grpmgr_nm) {
		this.grpmgr_nm = grpmgr_nm;
	}

	public String getOrdr_cont() {
		return ordr_cont;
	}

	public void setOrdr_cont(String ordr_cont) {
		this.ordr_cont = ordr_cont;
	}

	public String getCnsdman_id() {
		return cnsdman_id;
	}

	public void setCnsdman_id(String cnsdman_id) {
		this.cnsdman_id = cnsdman_id;
	}

	public String getCnsdman_nm() {
		return cnsdman_nm;
	}

	public void setCnsdman_nm(String cnsdman_nm) {
		this.cnsdman_nm = cnsdman_nm;
	}

	public String getCnsd_dt() {
		return cnsd_dt;
	}

	public void setCnsd_dt(String cnsd_dt) {
		this.cnsd_dt = cnsd_dt;
	}

	public String getCnsd_opnn() {
		return cnsd_opnn;
	}

	public void setCnsd_opnn(String cnsd_opnn) {
		this.cnsd_opnn = cnsd_opnn;
	}

	public String getApbtman_id() {
		return apbtman_id;
	}

	public void setApbtman_id(String apbtman_id) {
		this.apbtman_id = apbtman_id;
	}

	public String getApbtman_nm() {
		return apbtman_nm;
	}

	public void setApbtman_nm(String apbtman_nm) {
		this.apbtman_nm = apbtman_nm;
	}

	public String getApbt_opnn() {
		return apbt_opnn;
	}

	public void setApbt_opnn(String apbt_opnn) {
		this.apbt_opnn = apbt_opnn;
	}

	public String getRe_dt() {
		return re_dt;
	}

	public void setRe_dt(String re_dt) {
		this.re_dt = re_dt;
	}

	public String getRespman_id() {
		return respman_id;
	}

	public void setRespman_id(String respman_id) {
		this.respman_id = respman_id;
	}
	public String getExtnl_prgrs_status() {
		return extnl_prgrs_status;
	}

	public void setExtnl_prgrs_status(String extnl_prgrs_status) {
		this.extnl_prgrs_status = extnl_prgrs_status;
	}

	public String getIntnl_prgrs_status() {
		return intnl_prgrs_status;
	}

	public void setIntnl_prgrs_status(String intnl_prgrs_status) {
		this.intnl_prgrs_status = intnl_prgrs_status;
	}

	public String getFileInfos2() {
		return fileInfos2;
	}

	public void setFileInfos2(String fileInfos2) {
		this.fileInfos2 = fileInfos2;
	}

	public String getFileInfos3() {
		return fileInfos3;
	}

	public void setFileInfos3(String fileInfos3) {
		this.fileInfos3 = fileInfos3;
	}

	public String[] getExtnl_list() {
		return extnl_list;
	}

	public void setExtnl_list(String[] extnl_list) {
		this.extnl_list = extnl_list;
	}

	public List getExtnlList() {
		return extnlList;
	}

	public void setExtnlList(List extnlList) {
		this.extnlList = extnlList;
	}

	public String getExtnl_comp_nm() {
		return extnl_comp_nm;
	}

	public void setExtnl_comp_nm(String extnl_comp_nm) {
		this.extnl_comp_nm = extnl_comp_nm;
	}

	public long getExtnl_cnslt_amt() {
		return extnl_cnslt_amt;
	}

	public void setExtnl_cnslt_amt(long extnl_cnslt_amt) {
		this.extnl_cnslt_amt = extnl_cnslt_amt;
	}
	
//	public BigDecimal getExtnl_cnslt_amt() {
//		return extnl_cnslt_amt;
//	}
//
//	public void setExtnl_cnslt_amt(BigDecimal extnl_cnslt_amt) {
//		this.extnl_cnslt_amt = extnl_cnslt_amt;
//	}

	public String getExtnl_crrncy_unit() {
		return extnl_crrncy_unit;
	}

	public void setExtnl_crrncy_unit(String extnl_crrncy_unit) {
		this.extnl_crrncy_unit = extnl_crrncy_unit;
	}

	public long getExtnl_krw_amt() {
		return extnl_krw_amt;
	}

	public void setExtnl_krw_amt(long extnl_krw_amt) {
		this.extnl_krw_amt = extnl_krw_amt;
	}

	public int getExtnl_seq() {
		return extnl_seq;
	}

	public void setExtnl_seq(int extnl_seq) {
		this.extnl_seq = extnl_seq;
	}

	public String getFile_midclsfcn2() {
		return file_midclsfcn2;
	}

	public void setFile_midclsfcn2(String file_midclsfcn2) {
		this.file_midclsfcn2 = file_midclsfcn2;
	}

	public String getIsGrpmgr() {
		return isGrpmgr;
	}

	public void setIsGrpmgr(String isGrpmgr) {
		this.isGrpmgr = isGrpmgr;
	}

	public String[] getUser_list() {
		return user_list;
	}

	public void setUser_list(String[] user_list) {
		this.user_list = user_list;
	}

	public String[] getRespman_list() {
		return respman_list;
	}

	public void setRespman_list(String[] respman_list) {
		this.respman_list = respman_list;
	}

	public String getRespman_nm() {
		return respman_nm;
	}

	public void setRespman_nm(String respman_nm) {
		this.respman_nm = respman_nm;
	}

	public int getSeqno() {
		return seqno;
	}

	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}

	public String getRejct_opnn() {
		return rejct_opnn;
	}

	public void setRejct_opnn(String rejct_opnn) {
		this.rejct_opnn = rejct_opnn;
	}

	public String getSrch_reg_id() {
		return srch_reg_id;
	}

	public void setSrch_reg_id(String srch_reg_id) {
		this.srch_reg_id = srch_reg_id;
	}

	public String getSrch_reception() {
		return srch_reception;
	}

	public void setSrch_reception(String srch_reception) {
		this.srch_reception = srch_reception;
	}

	public String getIsRespman() {
		return isRespman;
	}

	public void setIsRespman(String isRespman) {
		this.isRespman = isRespman;
	}

	public String getRegman_jikgup_nm() {
		return regman_jikgup_nm;
	}

	public void setRegman_jikgup_nm(String regman_jikgup_nm) {
		this.regman_jikgup_nm = regman_jikgup_nm;
	}

	public String getIsReview() {
		return isReview;
	}

	public void setIsReview(String isReview) {
		this.isReview = isReview;
	}

	public String getTop_role() {
		return top_role;
	}

	public void setTop_role(String top_role) {
		this.top_role = top_role;
	}

	public String getBlngt_orgnz() {
		return blngt_orgnz;
	}

	public void setBlngt_orgnz(String blngt_orgnz) {
		this.blngt_orgnz = blngt_orgnz;
	}

	public Timestamp getReg_dt_ts() {
		return reg_dt_ts;
	}

	public void setReg_dt_ts(Timestamp reg_dt_ts) {
		this.reg_dt_ts = reg_dt_ts;
	}

	public String getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNow_intnl_prgrs_status() {
		return now_intnl_prgrs_status;
	}

	public void setNow_intnl_prgrs_status(String now_intnl_prgrs_status) {
		this.now_intnl_prgrs_status = now_intnl_prgrs_status;
	}

	public String getGrpmgr_re_yn_value() {
		return grpmgr_re_yn_value;
	}

	public void setGrpmgr_re_yn_value(String grpmgr_re_yn_value) {
		this.grpmgr_re_yn_value = grpmgr_re_yn_value;
	}

	public String getReg_intnl_dept() {
		return reg_intnl_dept;
	}

	public void setReg_intnl_dept(String reg_intnl_dept) {
		this.reg_intnl_dept = reg_intnl_dept;
	}

	public String getReg_operdiv() {
		return reg_operdiv;
	}

	public void setReg_operdiv(String reg_operdiv) {
		this.reg_operdiv = reg_operdiv;
	}

	public String getIf_gbn() {
		return if_gbn;
	}

	public void setIf_gbn(String if_gbn) {
		this.if_gbn = if_gbn;
	}

	public String getIf_key_no() {
		return if_key_no;
	}

	public void setIf_key_no(String if_key_no) {
		this.if_key_no = if_key_no;
	}

	public String getIf_flag() {
		return if_flag;
	}

	public void setIf_flag(String if_flag) {
		this.if_flag = if_flag;
	}

	public String getCnsd_opnn_body() {
		return cnsd_opnn_body;
	}

	public void setCnsd_opnn_body(String cnsd_opnn_body) {
		this.cnsd_opnn_body = cnsd_opnn_body;
	}

	public String getCheck_prgrs_status() {
		return check_prgrs_status;
	}

	public void setCheck_prgrs_status(String check_prgrs_status) {
		this.check_prgrs_status = check_prgrs_status;
	}

	public String getReadLine() {
		return readLine;
	}

	public void setReadLine(String readLine) {
		this.readLine = readLine;
	}

	public String getIf_status() {
		return if_status;
	}

	public void setIf_status(String if_status) {
		this.if_status = if_status;
	}

	public String getRegpsn_id() {
		return regpsn_id;
	}

	public void setRegpsn_id(String regpsn_id) {
		this.regpsn_id = regpsn_id;
	}

	public String getRegpsn_nm() {
		return regpsn_nm;
	}

	public void setRegpsn_nm(String regpsn_nm) {
		this.regpsn_nm = regpsn_nm;
	}

	public String getRegpsn_dept_cd() {
		return regpsn_dept_cd;
	}

	public void setRegpsn_dept_cd(String regpsn_dept_cd) {
		this.regpsn_dept_cd = regpsn_dept_cd;
	}

	public String getRegpsn_dept_nm() {
		return regpsn_dept_nm;
	}

	public void setRegpsn_dept_nm(String regpsn_dept_nm) {
		this.regpsn_dept_nm = regpsn_dept_nm;
	}

	public String getTo_transfer() {
		return to_transfer;
	}

	public void setTo_transfer(String to_transfer) {
		this.to_transfer = to_transfer;
	}
	
	public String getCnslt_type() {
		return cnslt_type;
	}

	public void setCnslt_type(String cnslt_type) {
		this.cnslt_type = cnslt_type;
	}

	public String getComp_cd() {
		return comp_cd;
	}

	public void setComp_cd(String comp_cd) {
		this.comp_cd = comp_cd;
	}

	public String getSrch_elcomp() {
		return srch_elcomp;
	}

	public void setSrch_elcomp(String srch_elcomp) {
		this.srch_elcomp = srch_elcomp;
	}

	public String getSolo_yn() {
		return solo_yn;
	}

	public void setSolo_yn(String solo_yn) {
		this.solo_yn = solo_yn;
	}

	public String getSrch_solo_yn() {
		return srch_solo_yn;
	}

	public void setSrch_solo_yn(String srch_solo_yn) {
		this.srch_solo_yn = srch_solo_yn;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getIsStdCont() {
		return isStdCont;
	}

	public void setIsStdCont(String isStdCont) {
		this.isStdCont = isStdCont;
	}
	
	public String getFwd_gbn() {
		return fwd_gbn;
	}

	public void setFwd_gbn(String fwd_gbn) {
		this.fwd_gbn = fwd_gbn;
	}

	public String getSrch_complete_yn() {
		return srch_complete_yn;
	}

	public void setSrch_complete_yn(String srch_complete_yn) {
		this.srch_complete_yn = srch_complete_yn;
	}

	public String getComplete_yn() {
		return complete_yn;
	}

	public void setComplete_yn(String complete_yn) {
		this.complete_yn = complete_yn;
	}

	public String getReq_reply_dt() {
		return req_reply_dt;
	}

	public void setReq_reply_dt(String req_reply_dt) {
		this.req_reply_dt = req_reply_dt;
	}

	public String[] getArr_trgtman_id() {
		return arr_trgtman_id;
	}

	public void setArr_trgtman_id(String[] arr_trgtman_id) {
		this.arr_trgtman_id = arr_trgtman_id;
	}

	public String getTrgtman_id() {
		return trgtman_id;
	}

	public void setTrgtman_id(String trgtman_id) {
		this.trgtman_id = trgtman_id;
	}

	public String[] getArr_trgtman_nm() {
		return arr_trgtman_nm;
	}

	public void setArr_trgtman_nm(String[] arr_trgtman_nm) {
		this.arr_trgtman_nm = arr_trgtman_nm;
	}

	public String getTrgtman_nm() {
		return trgtman_nm;
	}

	public void setTrgtman_nm(String trgtman_nm) {
		this.trgtman_nm = trgtman_nm;
	}

	public String[] getArr_trgtman_dept_nm() {
		return arr_trgtman_dept_nm;
	}

	public void setArr_trgtman_dept_nm(String[] arr_trgtman_dept_nm) {
		this.arr_trgtman_dept_nm = arr_trgtman_dept_nm;
	}

	public String getTrgtman_dept_nm() {
		return trgtman_dept_nm;
	}

	public void setTrgtman_dept_nm(String trgtman_dept_nm) {
		this.trgtman_dept_nm = trgtman_dept_nm;
	}

	public String[] getArr_trgtman_jikgup_nm() {
		return arr_trgtman_jikgup_nm;
	}

	public void setArr_trgtman_jikgup_nm(String[] arr_trgtman_jikgup_nm) {
		this.arr_trgtman_jikgup_nm = arr_trgtman_jikgup_nm;
	}

	public String getTrgtman_jikgup_nm() {
		return trgtman_jikgup_nm;
	}

	public void setTrgtman_jikgup_nm(String trgtman_jikgup_nm) {
		this.trgtman_jikgup_nm = trgtman_jikgup_nm;
	}

	public String getMark_num() {
		return mark_num;
	}

	public void setMark_num(String mark_num) {
		this.mark_num = mark_num;
	}

	public String getSrch_comp_cd() {
		return srch_comp_cd;
	}

	public void setSrch_comp_cd(String srch_comp_cd) {
		this.srch_comp_cd = srch_comp_cd;
	}

	public String getSrch_prgrs_status_cd() {
		return srch_prgrs_status_cd;
	}

	public void setSrch_prgrs_status_cd(String srch_prgrs_status_cd) {
		this.srch_prgrs_status_cd = srch_prgrs_status_cd;
	}
	
}