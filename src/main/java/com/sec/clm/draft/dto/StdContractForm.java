/**
 * Project Name : 법무시스템 이식
 * File Name : LawConsultForm.java
 * Description : 법률자문 Form
 * Author : 김현구
 * Date : 2011. 08. 30
 * Copyright : 
 */


package com.sec.clm.draft.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import com.sds.secframework.common.dto.CommonVO;
import com.sds.secframework.common.util.DateUtil;


/**
* Description : 법률자문 Form
* Author : 김현구
* Date : 2011.08.30
*/
public class StdContractForm extends CommonVO {
	
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
	
	
	/**진행_상태*/
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
	/**담당자 */
	private String respman_id;
	/**담당자 */
	private String respman_nm;

	/**********************************************
	 * PAGE 관련
	 **********************************************/
	
	/**페이지 시작*/
	private String start_index;
	/**페이지 끝*/
	private String end_index;
	/**현재 페이지*/
	private String curPage;

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
	/**검색어 - 총괄(등록부서) */
	private String srch_reg_dept;
	/**검색어 - 총괄(등록부서)명 */
	private String srch_reg_dept_nm;
	
	/**검색어 - 자문유형 */
	private String srch_consult_type;
	private String srch_consult_type_name;

	/** 조회시작일자(의뢰일)*/
	private String srch_start_ymd;
	/**조회종료일자(의뢰일)*/
	private String srch_end_ymd;
	
	private String srch_reg_id;
	private String srch_reception;

	/*********************************************/
	
	/**국내/해외 여부*/
	private String isForeign;
	
	/**한 페이지 행 수*/
	private int row_cnt = 10;
	
	/**********************************************
	 * 결과 값
	 **********************************************/
	/**조회한 리스트*/
	private List lawconsult_list;
	private List lawconsult_type_list;

	/**결과 타이틀*/
	private String return_title;

	/**결과 메시지*/
	private String return_message;
	
	private String consult_type;
	private String consult_type_name;
	private String check_prgrs_status;
	
	/**첨부파일 추가 부분 */
	private String fileInfos2;
	private String fileInfos3;
	private String file_midclsfcn2;
	
	/**외부기관 관련 변수 */
	private String[] extnl_list;
	private List extnlList;
	private String extnl_comp_nm;
	private long extnl_cnslt_amt;
	//private BigDecimal extnl_cnslt_amt;
	private String extnl_crrncy_unit;
	private long extnl_krw_amt;
	private int extnl_seq;
	
	private String isGrpmgr;
	private String[] user_list;
	private String[] respman_list;
	
	private String rejct_opnn;
	
	/**Review화면에서 넘어온 작업인지 판별 */
	private String isReview;
	
	/**생성자*/
	public StdContractForm() throws Exception {
		//srch_start_ymd = DateUtil.formatDate(DateUtil.addDays(DateUtil.today(), -365), "-");
		//srch_end_ymd = DateUtil.formatDate(DateUtil.today(), "-");
		del_yn = "N";
		isForeign = "false";
		isReview = "N";
		isGrpmgr = "N";
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

	public List getLawconsult_list() {
		return lawconsult_list;
	}

	public void setLawconsult_list(List lawconsult_list) {
		this.lawconsult_list = lawconsult_list;
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

	public int getRow_cnt() {
		return row_cnt;
	}

	public void setRow_cnt(int row_cnt) {
		this.row_cnt = row_cnt;
	}

	public String getConsult_type() {
		return consult_type;
	}

	public void setConsult_type(String consult_type) {
		this.consult_type = consult_type;
	}

	public String getConsult_type_name() {
		return consult_type_name;
	}

	public void setConsult_type_name(String consult_type_name) {
		this.consult_type_name = consult_type_name;
	}

	public String getBody_mime() {
		return body_mime;
	}

	public void setBody_mime(String body_mime) {
		this.body_mime = body_mime;
	}

	public List getLawconsult_type_list() {
		return lawconsult_type_list;
	}

	public void setLawconsult_type_list(List lawconsult_type_list) {
		this.lawconsult_type_list = lawconsult_type_list;
	}

	public String getPrgrs_status_name() {
		return prgrs_status_name;
	}

	public void setPrgrs_status_name(String prgrs_status_name) {
		this.prgrs_status_name = prgrs_status_name;
	}

	public String getCheck_prgrs_status() {
		return check_prgrs_status;
	}

	public void setCheck_prgrs_status(String check_prgrs_status) {
		this.check_prgrs_status = check_prgrs_status;
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

	public String getSrch_reg_dept_nm() {
		return srch_reg_dept_nm;
	}

	public void setSrch_reg_dept_nm(String srch_reg_dept_nm) {
		this.srch_reg_dept_nm = srch_reg_dept_nm;
	}
}