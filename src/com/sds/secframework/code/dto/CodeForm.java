package com.sds.secframework.code.dto;

import com.sds.secframework.common.dto.CommonForm;

/**
 * 코드관리 Value Object
 *  
 * @version V1.0
 * 작성일 : 2009/12/18
 * @author 금현서, ace4u_khs@samsung.com
 */

public class CodeForm extends CommonForm {

	/**그룹코드 */
	private String  grp_cd;
	/**그룹코드명 */
	private String  grp_cd_nm;
	/**그룹코드명약어 */
	private String  grp_cd_abbr_nm;
	/**그룹코드영문명 */
	private String  grp_cd_nm_eng;
	/**그룹코드영문약어명 */
	private String  grp_cd_abbr_nm_eng;	
	/**공통코드 */
	private String  cd;
	/**공통코드명 */
	private String  cd_nm;
	/**공통코드약어명 */
	private String  cd_abbr_nm;
	/**공통코드영문명 */
	private String  cd_nm_eng;
	/**공통코드영문약어명 */
	private String  cd_abbr_nm_eng;
	/**순서 */
	private int     cd_order;
	/**사용자정의컬럼 */
	private String  useman_mng_itm1;
	/**사용자정의컬럼 */
	private String  useman_mng_itm2;
	/**사용자정의컬럼 */
	private String  useman_mng_itm3;
	/**사용자정의컬럼 */
	private String  useman_mng_itm4;
	/**사용자정의컬럼 */
	private String  useman_mng_itm5;
	/**사용여부 */
	private String  use_yn;
	/**설명 */
	private String  comments;
	/**등록자아이디 */
	private String  reg_id;
	/**등록일시 */
	private String  reg_dt;
	/**수정자아이디 */
	private String  mod_id;
	/**수정일시 */
	private String  mod_dt;

	
	/**********************************************************************
	 * 배열
	 **********************************************************************/
	/**그룹코드 */
	private String[] grp_cds;
	/**그룹코드명 */
	private String[] grp_cd_nms;
	/**그룹코드약어명 */
	private String[] grp_cd_abbr_nms;
	/**그룹코드영문명 */
	private String[] grp_cd_nm_engs;
	/**그룹코드영문약어명 */
	private String[] grp_cd_abbr_nm_engs;	
	/**공통코드 */
	private String[] cds;
	/**공통코드명 */
	private String[] cd_nms;
	/**공통코드약어명 */
	private String[] cd_abbr_nms;
	/**공통코드영문명 */
	private String[] cd_nm_engs;
	/**공통코드영문약어명 */
	private String[] cd_abbr_nm_engs;
	/**순서 */
	private int[]    cd_orders;
	/**사용자정의컬럼 */
	private String[] useman_mng_itm1s;
	/**사용자정의컬럼 */
	private String[] useman_mng_itm2s;
	/**사용자정의컬럼 */
	private String[] useman_mng_itm3s;
	/**사용자정의컬럼 */
	private String[] useman_mng_itm4s;
	/**사용자정의컬럼 */
	private String[] useman_mng_itm5s;
	/**사용여부 */
	private String[] use_yns;
	/**설명 */
	private String[] commentss;
	/**그룹코드(신) */
	private String[] new_grp_cds;
	/**그룹코드명(신) */
	private String[] new_cds;
	
	/**********************************************************************
	 * 검색조건
	 **********************************************************************/
	/**그룹코드검색명 */
	private String  srch_grp_cd_cntnt;
	/**코드명 */
	private String  srch_cd_cntnt;
	/**사용여부 */
	private String  srch_use_yn;
	/**선택된그룹코드 */
	private String  select_grp_cd;

	/**********************************************************************
	 * 체크박스
	 **********************************************************************/
	/**그룹코드(체크) */
	private String[]  chk_grp_cds;
	/**코드(체크) */
	private String[]  chk_cds;
	
	public String getGrp_cd() {
		return grp_cd;
	}
	public void setGrp_cd(String grp_cd) {
		this.grp_cd = grp_cd;
	}
	public String getGrp_cd_nm() {
		return grp_cd_nm;
	}
	public void setGrp_cd_nm(String grp_cd_nm) {
		this.grp_cd_nm = grp_cd_nm;
	}
	public String getGrp_cd_abbr_nm() {
		return grp_cd_abbr_nm;
	}
	public void setGrp_cd_abbr_nm(String grp_cd_abbr_nm) {
		this.grp_cd_abbr_nm = grp_cd_abbr_nm;
	}
	public String getGrp_cd_nm_eng() {
		return grp_cd_nm_eng;
	}
	public void setGrp_cd_nm_eng(String grp_cd_nm_eng) {
		this.grp_cd_nm_eng = grp_cd_nm_eng;
	}
	public String getGrp_cd_abbr_nm_eng() {
		return grp_cd_abbr_nm_eng;
	}
	public void setGrp_cd_abbr_nm_eng(String grp_cd_abbr_nm_eng) {
		this.grp_cd_abbr_nm_eng = grp_cd_abbr_nm_eng;
	}
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public String getCd_nm() {
		return cd_nm;
	}
	public void setCd_nm(String cd_nm) {
		this.cd_nm = cd_nm;
	}
	public String getCd_abbr_nm() {
		return cd_abbr_nm;
	}
	public void setCd_abbr_nm(String cd_abbr_nm) {
		this.cd_abbr_nm = cd_abbr_nm;
	}
	public String getCd_nm_eng() {
		return cd_nm_eng;
	}
	public void setCd_nm_eng(String cd_nm_eng) {
		this.cd_nm_eng = cd_nm_eng;
	}
	public String getCd_abbr_nm_eng() {
		return cd_abbr_nm_eng;
	}
	public void setCd_abbr_nm_eng(String cd_abbr_nm_eng) {
		this.cd_abbr_nm_eng = cd_abbr_nm_eng;
	}
	public int getCd_order() {
		return cd_order;
	}
	public void setCd_order(int cd_order) {
		this.cd_order = cd_order;
	}
	public String getUseman_mng_itm1() {
		return useman_mng_itm1;
	}
	public void setUseman_mng_itm1(String useman_mng_itm1) {
		this.useman_mng_itm1 = useman_mng_itm1;
	}
	public String getUseman_mng_itm2() {
		return useman_mng_itm2;
	}
	public void setUseman_mng_itm2(String useman_mng_itm2) {
		this.useman_mng_itm2 = useman_mng_itm2;
	}
	public String getUseman_mng_itm3() {
		return useman_mng_itm3;
	}
	public void setUseman_mng_itm3(String useman_mng_itm3) {
		this.useman_mng_itm3 = useman_mng_itm3;
	}
	public String getUseman_mng_itm4() {
		return useman_mng_itm4;
	}
	public void setUseman_mng_itm4(String useman_mng_itm4) {
		this.useman_mng_itm4 = useman_mng_itm4;
	}
	public String getUseman_mng_itm5() {
		return useman_mng_itm5;
	}
	public void setUseman_mng_itm5(String useman_mng_itm5) {
		this.useman_mng_itm5 = useman_mng_itm5;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
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
	public String[] getGrp_cds() {
		return grp_cds;
	}
	public void setGrp_cds(String[] grp_cds) {
		this.grp_cds = grp_cds;
	}
	public String[] getGrp_cd_nms() {
		return grp_cd_nms;
	}
	public void setGrp_cd_nms(String[] grp_cd_nms) {
		this.grp_cd_nms = grp_cd_nms;
	}
	public String[] getGrp_cd_abbr_nms() {
		return grp_cd_abbr_nms;
	}
	public void setGrp_cd_abbr_nms(String[] grp_cd_abbr_nms) {
		this.grp_cd_abbr_nms = grp_cd_abbr_nms;
	}
	public String[] getGrp_cd_nm_engs() {
		return grp_cd_nm_engs;
	}
	public void setGrp_cd_nm_engs(String[] grp_cd_nm_engs) {
		this.grp_cd_nm_engs = grp_cd_nm_engs;
	}
	public String[] getGrp_cd_abbr_nm_engs() {
		return grp_cd_abbr_nm_engs;
	}
	public void setGrp_cd_abbr_nm_engs(String[] grp_cd_abbr_nm_engs) {
		this.grp_cd_abbr_nm_engs = grp_cd_abbr_nm_engs;
	}
	public String[] getCds() {
		return cds;
	}
	public void setCds(String[] cds) {
		this.cds = cds;
	}
	public String[] getCd_nms() {
		return cd_nms;
	}
	public void setCd_nms(String[] cd_nms) {
		this.cd_nms = cd_nms;
	}
	public String[] getCd_abbr_nms() {
		return cd_abbr_nms;
	}
	public void setCd_abbr_nms(String[] cd_abbr_nms) {
		this.cd_abbr_nms = cd_abbr_nms;
	}
	public String[] getCd_nm_engs() {
		return cd_nm_engs;
	}
	public void setCd_nm_engs(String[] cd_nm_engs) {
		this.cd_nm_engs = cd_nm_engs;
	}
	public String[] getCd_abbr_nm_engs() {
		return cd_abbr_nm_engs;
	}
	public void setCd_abbr_nm_engs(String[] cd_abbr_nm_engs) {
		this.cd_abbr_nm_engs = cd_abbr_nm_engs;
	}
	public int[] getCd_orders() {
		return cd_orders;
	}
	public void setCd_orders(int[] cd_orders) {
		this.cd_orders = cd_orders;
	}
	public String[] getUseman_mng_itm1s() {
		return useman_mng_itm1s;
	}
	public void setUseman_mng_itm1s(String[] useman_mng_itm1s) {
		this.useman_mng_itm1s = useman_mng_itm1s;
	}
	public String[] getUseman_mng_itm2s() {
		return useman_mng_itm2s;
	}
	public void setUseman_mng_itm2s(String[] useman_mng_itm2s) {
		this.useman_mng_itm2s = useman_mng_itm2s;
	}
	public String[] getUseman_mng_itm3s() {
		return useman_mng_itm3s;
	}
	public void setUseman_mng_itm3s(String[] useman_mng_itm3s) {
		this.useman_mng_itm3s = useman_mng_itm3s;
	}
	public String[] getUseman_mng_itm4s() {
		return useman_mng_itm4s;
	}
	public void setUseman_mng_itm4s(String[] useman_mng_itm4s) {
		this.useman_mng_itm4s = useman_mng_itm4s;
	}
	public String[] getUseman_mng_itm5s() {
		return useman_mng_itm5s;
	}
	public void setUseman_mng_itm5s(String[] useman_mng_itm5s) {
		this.useman_mng_itm5s = useman_mng_itm5s;
	}
	public String[] getUse_yns() {
		return use_yns;
	}
	public void setUse_yns(String[] use_yns) {
		this.use_yns = use_yns;
	}
	public String[] getCommentss() {
		return commentss;
	}
	public void setCommentss(String[] commentss) {
		this.commentss = commentss;
	}
	public String[] getNew_grp_cds() {
		return new_grp_cds;
	}
	public void setNew_grp_cds(String[] new_grp_cds) {
		this.new_grp_cds = new_grp_cds;
	}
	public String[] getNew_cds() {
		return new_cds;
	}
	public void setNew_cds(String[] new_cds) {
		this.new_cds = new_cds;
	}
	public String getSrch_grp_cd_cntnt() {
		return srch_grp_cd_cntnt;
	}
	public void setSrch_grp_cd_cntnt(String srch_grp_cd_cntnt) {
		this.srch_grp_cd_cntnt = srch_grp_cd_cntnt;
	}
	public String getSrch_cd_cntnt() {
		return srch_cd_cntnt;
	}
	public void setSrch_cd_cntnt(String srch_cd_cntnt) {
		this.srch_cd_cntnt = srch_cd_cntnt;
	}
	public String getSrch_use_yn() {
		return srch_use_yn;
	}
	public void setSrch_use_yn(String srch_use_yn) {
		this.srch_use_yn = srch_use_yn;
	}
	public String getSelect_grp_cd() {
		return select_grp_cd;
	}
	public void setSelect_grp_cd(String select_grp_cd) {
		this.select_grp_cd = select_grp_cd;
	}
	public String[] getChk_grp_cds() {
		return chk_grp_cds;
	}
	public void setChk_grp_cds(String[] chk_grp_cds) {
		this.chk_grp_cds = chk_grp_cds;
	}
	public String[] getChk_cds() {
		return chk_cds;
	}
	public void setChk_cds(String[] chk_cds) {
		this.chk_cds = chk_cds;
	}
	
}
