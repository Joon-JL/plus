package com.sec.clm.manage.dto;

import com.sds.secframework.common.dto.CommonVO;

public class RegistrationVO extends CommonVO {
	private String cnsdreq_id;
	private String cntrt_id;
	private String cntrt_nm;
	private String region_gbn;
	private String biz_clsfcn;
	private String depth_clsfcn;
	private String cnclsnpurps_bigclsfcn;
	private String cnclsnpurps_midclsfcn;
	private String cnclsnpurps_midclsfcn_etc;
	private String cntrt_trgt;
	private String cntrt_trgt_det;
	private String pshdbkgrnd_purps;
	private String payment_gbn;
	private String antcptnefct;
	private String cntrtperiod_startday;
	private String cntrtperiod_endday;
	private String secret_keepperiod;
	private String cntrt_untprc;
	private String cntrt_amt;
	private String crrncy_unit;
	private String payment_cond;
	private String auto_rnew_yn;
	private String exprt_notiday;
	private String oblgt_lmt;
	private String spcl_cond;
	private String cnclsn_plndday;
	private String cntrt_respman_id;
	private String cntrt_respman_nm;
	private String cntrt_oppnt_cd;
	private String cntrt_oppnt_nm;
	private String sign_plndman_id;
	private String sign_plndman_nm;
	private String seal_mthd;
	private String cntrt_cnclsn_yn;
	private String signman_id;
	private String signman_nm;
	private String signman_jikgup_nm;
	private String sign_dept_nm;
	private String signday;
	private String oppnt_signman_nm;
	private String oppnt_signman_jikgup;
	private String oppnt_signman_dept;
	private String oppnt_signday;
	private String cntrt_cnclsnday;
	private String cpy_regday;
	private String cntrt_no;
	private String loac;
	private String dspt_resolt_mthd;
	private String dspt_resolt_mthd_det;
	private String org_acpt_dlay_cause;
	private String org_acptday;
	private String org_tkovman_id;
	private String org_tkovman_nm;
	private String org_hdovman_id;
	private String org_hdovman_nm;
	private String org_hdov_dept_nm;
	private String org_hdovman_jikgup_nm;
	private String org_strg_pos;
	private String prcs_depth;
	private String depth_status;
	private String cntrt_status;
	private String reg_dt;
	private String reg_id;
	private String reg_nm;
	private String mod_dt;
	private String mod_id;
	private String mod_nm;
	private String del_yn;
	private String del_dt;
	private String del_id;
	private String del_nm;
	private String loac_etc;
	private String cntrt_chge_demndday;
	private String cntrt_chge_demndman_id;
	private String cntrt_chge_demndman_nm;
	private String cntrt_chge_demnd_cause;
	private String cntrt_chge_plndday;
	private String cntrt_chge_conf_yn;
	private String cntrt_chge_confday;
	private String cntrt_chge_confman_id;
	private String cntrt_chge_confman_nm;
	private String bfhdcstn_mtnman_id;
	private String bfhdcstn_mtnman_nm;
	private String bfhdcstn_mtnman_jikgup_nm;
	private String bfhdcstn_mtn_dept_nm;
	private String bfhdcstn_mtnman_email;
	private String bfhdcstn_apbt_mthd;
	private String bfhdcstn_apbtman_id;
	private String bfhdcstn_apbtman_nm;
	private String bfhdcstn_apbtman_jikgup_nm;
	private String bfhdcstn_apbt_dept_nm;
	private String bfhdcstn_apbtday;
	private String seal_rqstday;
	private String seal_rqstman_id;
	private String seal_rqstman_nm;
	private String seal_ffmtman_id;
	private String seal_ffmtman_nm;
	private String seal_ffmt_dept_cd;
	private String seal_ffmt_dept_nm;
	private String seal_ffmtman_jikgup_nm;
	private String seal_ffmtman_search_nm;
	private String seal_ffmtday;
	private String reg_operdiv;
	private String prgrs_status;
	
	private String agree_yn;
	private int agree_seqno;
	private String req_title;
	private String req_dt;
	
	private String cntrt_resp_info;
	private String reject_info;
	
	public String getCntrt_id() {
		return cntrt_id;
	}
	public void setCntrt_id(String cntrt_id) {
		this.cntrt_id = cntrt_id;
	}
	public String getCntrt_nm() {
		return cntrt_nm;
	}
	public void setCntrt_nm(String cntrt_nm) {
		this.cntrt_nm = cntrt_nm;
	}
	public String getRegion_gbn() {
		return region_gbn;
	}
	public void setRegion_gbn(String region_gbn) {
		this.region_gbn = region_gbn;
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
	public String getCnclsnpurps_midclsfcn_etc() {
		return cnclsnpurps_midclsfcn_etc;
	}
	public void setCnclsnpurps_midclsfcn_etc(String cnclsnpurps_midclsfcn_etc) {
		this.cnclsnpurps_midclsfcn_etc = cnclsnpurps_midclsfcn_etc;
	}
	public String getCntrt_trgt() {
		return cntrt_trgt;
	}
	public void setCntrt_trgt(String cntrt_trgt) {
		this.cntrt_trgt = cntrt_trgt;
	}
	public String getCntrt_trgt_det() {
		return cntrt_trgt_det;
	}
	public void setCntrt_trgt_det(String cntrt_trgt_det) {
		this.cntrt_trgt_det = cntrt_trgt_det;
	}
	public String getPshdbkgrnd_purps() {
		return pshdbkgrnd_purps;
	}
	public void setPshdbkgrnd_purps(String pshdbkgrnd_purps) {
		this.pshdbkgrnd_purps = pshdbkgrnd_purps;
	}
	public String getPayment_gbn() {
		return payment_gbn;
	}
	public void setPayment_gbn(String payment_gbn) {
		this.payment_gbn = payment_gbn;
	}
	public String getAntcptnefct() {
		return antcptnefct;
	}
	public void setAntcptnefct(String antcptnefct) {
		this.antcptnefct = antcptnefct;
	}
	public String getCntrtperiod_startday() {
		return cntrtperiod_startday;
	}
	public void setCntrtperiod_startday(String cntrtperiod_startday) {
		this.cntrtperiod_startday = cntrtperiod_startday;
	}
	public String getCntrtperiod_endday() {
		return cntrtperiod_endday;
	}
	public void setCntrtperiod_endday(String cntrtperiod_endday) {
		this.cntrtperiod_endday = cntrtperiod_endday;
	}
	public String getSecret_keepperiod() {
		return secret_keepperiod;
	}
	public void setSecret_keepperiod(String secret_keepperiod) {
		this.secret_keepperiod = secret_keepperiod;
	}
	public String getCntrt_untprc() {
		return cntrt_untprc;
	}
	public void setCntrt_untprc(String cntrt_untprc) {
		this.cntrt_untprc = cntrt_untprc;
	}
	public String getCntrt_amt() {
		return cntrt_amt;
	}
	public void setCntrt_amt(String cntrt_amt) {
		this.cntrt_amt = cntrt_amt;
	}
	public String getCrrncy_unit() {
		return crrncy_unit;
	}
	public void setCrrncy_unit(String crrncy_unit) {
		this.crrncy_unit = crrncy_unit;
	}
	public String getPayment_cond() {
		return payment_cond;
	}
	public void setPayment_cond(String payment_cond) {
		this.payment_cond = payment_cond;
	}
	public String getAuto_rnew_yn() {
		return auto_rnew_yn;
	}
	public void setAuto_rnew_yn(String auto_rnew_yn) {
		this.auto_rnew_yn = auto_rnew_yn;
	}
	public String getExprt_notiday() {
		return exprt_notiday;
	}
	public void setExprt_notiday(String exprt_notiday) {
		this.exprt_notiday = exprt_notiday;
	}
	public String getOblgt_lmt() {
		return oblgt_lmt;
	}
	public void setOblgt_lmt(String oblgt_lmt) {
		this.oblgt_lmt = oblgt_lmt;
	}
	public String getSpcl_cond() {
		return spcl_cond;
	}
	public void setSpcl_cond(String spcl_cond) {
		this.spcl_cond = spcl_cond;
	}
	public String getCnclsn_plndday() {
		return cnclsn_plndday;
	}
	public void setCnclsn_plndday(String cnclsn_plndday) {
		this.cnclsn_plndday = cnclsn_plndday;
	}
	public String getCntrt_respman_id() {
		return cntrt_respman_id;
	}
	public void setCntrt_respman_id(String cntrt_respman_id) {
		this.cntrt_respman_id = cntrt_respman_id;
	}
	public String getCntrt_respman_nm() {
		return cntrt_respman_nm;
	}
	public void setCntrt_respman_nm(String cntrt_respman_nm) {
		this.cntrt_respman_nm = cntrt_respman_nm;
	}
	public String getCntrt_oppnt_cd() {
		return cntrt_oppnt_cd;
	}
	public void setCntrt_oppnt_cd(String cntrt_oppnt_cd) {
		this.cntrt_oppnt_cd = cntrt_oppnt_cd;
	}
	public String getCntrt_oppnt_nm() {
		return cntrt_oppnt_nm;
	}
	public void setCntrt_oppnt_nm(String cntrt_oppnt_nm) {
		this.cntrt_oppnt_nm = cntrt_oppnt_nm;
	}
	public String getSign_plndman_id() {
		return sign_plndman_id;
	}
	public void setSign_plndman_id(String sign_plndman_id) {
		this.sign_plndman_id = sign_plndman_id;
	}
	public String getSign_plndman_nm() {
		return sign_plndman_nm;
	}
	public void setSign_plndman_nm(String sign_plndman_nm) {
		this.sign_plndman_nm = sign_plndman_nm;
	}
	public String getSeal_mthd() {
		return seal_mthd;
	}
	public void setSeal_mthd(String seal_mthd) {
		this.seal_mthd = seal_mthd;
	}
	public String getCntrt_cnclsn_yn() {
		return cntrt_cnclsn_yn;
	}
	public void setCntrt_cnclsn_yn(String cntrt_cnclsn_yn) {
		this.cntrt_cnclsn_yn = cntrt_cnclsn_yn;
	}
	public String getSignman_id() {
		return signman_id;
	}
	public void setSignman_id(String signman_id) {
		this.signman_id = signman_id;
	}
	public String getSignman_nm() {
		return signman_nm;
	}
	public void setSignman_nm(String signman_nm) {
		this.signman_nm = signman_nm;
	}
	public String getSignman_jikgup_nm() {
		return signman_jikgup_nm;
	}
	public void setSignman_jikgup_nm(String signman_jikgup_nm) {
		this.signman_jikgup_nm = signman_jikgup_nm;
	}
	public String getSign_dept_nm() {
		return sign_dept_nm;
	}
	public void setSign_dept_nm(String sign_dept_nm) {
		this.sign_dept_nm = sign_dept_nm;
	}
	public String getSignday() {
		return signday;
	}
	public void setSignday(String signday) {
		this.signday = signday;
	}
	public String getOppnt_signman_nm() {
		return oppnt_signman_nm;
	}
	public void setOppnt_signman_nm(String oppnt_signman_nm) {
		this.oppnt_signman_nm = oppnt_signman_nm;
	}
	public String getOppnt_signman_jikgup() {
		return oppnt_signman_jikgup;
	}
	public void setOppnt_signman_jikgup(String oppnt_signman_jikgup) {
		this.oppnt_signman_jikgup = oppnt_signman_jikgup;
	}
	public String getOppnt_signman_dept() {
		return oppnt_signman_dept;
	}
	public void setOppnt_signman_dept(String oppnt_signman_dept) {
		this.oppnt_signman_dept = oppnt_signman_dept;
	}
	public String getOppnt_signday() {
		return oppnt_signday;
	}
	public void setOppnt_signday(String oppnt_signday) {
		this.oppnt_signday = oppnt_signday;
	}
	public String getCntrt_cnclsnday() {
		return cntrt_cnclsnday;
	}
	public void setCntrt_cnclsnday(String cntrt_cnclsnday) {
		this.cntrt_cnclsnday = cntrt_cnclsnday;
	}
	public String getCpy_regday() {
		return cpy_regday;
	}
	public void setCpy_regday(String cpy_regday) {
		this.cpy_regday = cpy_regday;
	}
	public String getCntrt_no() {
		return cntrt_no;
	}
	public void setCntrt_no(String cntrt_no) {
		this.cntrt_no = cntrt_no;
	}
	public String getLoac() {
		return loac;
	}
	public void setLoac(String loac) {
		this.loac = loac;
	}
	public String getDspt_resolt_mthd() {
		return dspt_resolt_mthd;
	}
	public void setDspt_resolt_mthd(String dspt_resolt_mthd) {
		this.dspt_resolt_mthd = dspt_resolt_mthd;
	}
	public String getDspt_resolt_mthd_det() {
		return dspt_resolt_mthd_det;
	}
	public void setDspt_resolt_mthd_det(String dspt_resolt_mthd_det) {
		this.dspt_resolt_mthd_det = dspt_resolt_mthd_det;
	}
	public String getOrg_acpt_dlay_cause() {
		return org_acpt_dlay_cause;
	}
	public void setOrg_acpt_dlay_cause(String org_acpt_dlay_cause) {
		this.org_acpt_dlay_cause = org_acpt_dlay_cause;
	}
	public String getOrg_acptday() {
		return org_acptday;
	}
	public void setOrg_acptday(String org_acptday) {
		this.org_acptday = org_acptday;
	}
	public String getOrg_tkovman_id() {
		return org_tkovman_id;
	}
	public void setOrg_tkovman_id(String org_tkovman_id) {
		this.org_tkovman_id = org_tkovman_id;
	}
	public String getOrg_tkovman_nm() {
		return org_tkovman_nm;
	}
	public void setOrg_tkovman_nm(String org_tkovman_nm) {
		this.org_tkovman_nm = org_tkovman_nm;
	}
	public String getOrg_hdovman_id() {
		return org_hdovman_id;
	}
	public void setOrg_hdovman_id(String org_hdovman_id) {
		this.org_hdovman_id = org_hdovman_id;
	}
	public String getOrg_hdovman_nm() {
		return org_hdovman_nm;
	}
	public void setOrg_hdovman_nm(String org_hdovman_nm) {
		this.org_hdovman_nm = org_hdovman_nm;
	}
	public String getOrg_hdov_dept_nm() {
		return org_hdov_dept_nm;
	}
	public void setOrg_hdov_dept_nm(String org_hdov_dept_nm) {
		this.org_hdov_dept_nm = org_hdov_dept_nm;
	}
	public String getOrg_hdovman_jikgup_nm() {
		return org_hdovman_jikgup_nm;
	}
	public void setOrg_hdovman_jikgup_nm(String org_hdovman_jikgup_nm) {
		this.org_hdovman_jikgup_nm = org_hdovman_jikgup_nm;
	}
	public String getOrg_strg_pos() {
		return org_strg_pos;
	}
	public void setOrg_strg_pos(String org_strg_pos) {
		this.org_strg_pos = org_strg_pos;
	}
	public String getPrcs_depth() {
		return prcs_depth;
	}
	public void setPrcs_depth(String prcs_depth) {
		this.prcs_depth = prcs_depth;
	}
	public String getDepth_status() {
		return depth_status;
	}
	public void setDepth_status(String depth_status) {
		this.depth_status = depth_status;
	}
	public String getCntrt_status() {
		return cntrt_status;
	}
	public void setCntrt_status(String cntrt_status) {
		this.cntrt_status = cntrt_status;
	}
	public String getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public String getReg_nm() {
		return reg_nm;
	}
	public void setReg_nm(String reg_nm) {
		this.reg_nm = reg_nm;
	}
	public String getMod_dt() {
		return mod_dt;
	}
	public void setMod_dt(String mod_dt) {
		this.mod_dt = mod_dt;
	}
	public String getMod_id() {
		return mod_id;
	}
	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}
	public String getMod_nm() {
		return mod_nm;
	}
	public void setMod_nm(String mod_nm) {
		this.mod_nm = mod_nm;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public String getDel_dt() {
		return del_dt;
	}
	public void setDel_dt(String del_dt) {
		this.del_dt = del_dt;
	}
	public String getDel_id() {
		return del_id;
	}
	public void setDel_id(String del_id) {
		this.del_id = del_id;
	}
	public String getDel_nm() {
		return del_nm;
	}
	public void setDel_nm(String del_nm) {
		this.del_nm = del_nm;
	}
	public String getLoac_etc() {
		return loac_etc;
	}
	public void setLoac_etc(String loac_etc) {
		this.loac_etc = loac_etc;
	}
	public String getCntrt_chge_demndday() {
		return cntrt_chge_demndday;
	}
	public void setCntrt_chge_demndday(String cntrt_chge_demndday) {
		this.cntrt_chge_demndday = cntrt_chge_demndday;
	}
	public String getCntrt_chge_demndman_id() {
		return cntrt_chge_demndman_id;
	}
	public void setCntrt_chge_demndman_id(String cntrt_chge_demndman_id) {
		this.cntrt_chge_demndman_id = cntrt_chge_demndman_id;
	}
	public String getCntrt_chge_demndman_nm() {
		return cntrt_chge_demndman_nm;
	}
	public void setCntrt_chge_demndman_nm(String cntrt_chge_demndman_nm) {
		this.cntrt_chge_demndman_nm = cntrt_chge_demndman_nm;
	}
	public String getCntrt_chge_demnd_cause() {
		return cntrt_chge_demnd_cause;
	}
	public void setCntrt_chge_demnd_cause(String cntrt_chge_demnd_cause) {
		this.cntrt_chge_demnd_cause = cntrt_chge_demnd_cause;
	}
	public String getCntrt_chge_plndday() {
		return cntrt_chge_plndday;
	}
	public void setCntrt_chge_plndday(String cntrt_chge_plndday) {
		this.cntrt_chge_plndday = cntrt_chge_plndday;
	}
	public String getCntrt_chge_conf_yn() {
		return cntrt_chge_conf_yn;
	}
	public void setCntrt_chge_conf_yn(String cntrt_chge_conf_yn) {
		this.cntrt_chge_conf_yn = cntrt_chge_conf_yn;
	}
	public String getCntrt_chge_confday() {
		return cntrt_chge_confday;
	}
	public void setCntrt_chge_confday(String cntrt_chge_confday) {
		this.cntrt_chge_confday = cntrt_chge_confday;
	}
	public String getCntrt_chge_confman_id() {
		return cntrt_chge_confman_id;
	}
	public void setCntrt_chge_confman_id(String cntrt_chge_confman_id) {
		this.cntrt_chge_confman_id = cntrt_chge_confman_id;
	}
	public String getCntrt_chge_confman_nm() {
		return cntrt_chge_confman_nm;
	}
	public void setCntrt_chge_confman_nm(String cntrt_chge_confman_nm) {
		this.cntrt_chge_confman_nm = cntrt_chge_confman_nm;
	}
	public String getBfhdcstn_mtnman_id() {
		return bfhdcstn_mtnman_id;
	}
	public void setBfhdcstn_mtnman_id(String bfhdcstn_mtnman_id) {
		this.bfhdcstn_mtnman_id = bfhdcstn_mtnman_id;
	}
	public String getBfhdcstn_mtnman_nm() {
		return bfhdcstn_mtnman_nm;
	}
	public void setBfhdcstn_mtnman_nm(String bfhdcstn_mtnman_nm) {
		this.bfhdcstn_mtnman_nm = bfhdcstn_mtnman_nm;
	}
	public String getBfhdcstn_mtnman_jikgup_nm() {
		return bfhdcstn_mtnman_jikgup_nm;
	}
	public void setBfhdcstn_mtnman_jikgup_nm(String bfhdcstn_mtnman_jikgup_nm) {
		this.bfhdcstn_mtnman_jikgup_nm = bfhdcstn_mtnman_jikgup_nm;
	}
	public String getBfhdcstn_mtn_dept_nm() {
		return bfhdcstn_mtn_dept_nm;
	}
	public void setBfhdcstn_mtn_dept_nm(String bfhdcstn_mtn_dept_nm) {
		this.bfhdcstn_mtn_dept_nm = bfhdcstn_mtn_dept_nm;
	}
	public String getBfhdcstn_mtnman_email() {
		return bfhdcstn_mtnman_email;
	}
	public void setBfhdcstn_mtnman_email(String bfhdcstn_mtnman_email) {
		this.bfhdcstn_mtnman_email = bfhdcstn_mtnman_email;
	}
	public String getBfhdcstn_apbt_mthd() {
		return bfhdcstn_apbt_mthd;
	}
	public void setBfhdcstn_apbt_mthd(String bfhdcstn_apbt_mthd) {
		this.bfhdcstn_apbt_mthd = bfhdcstn_apbt_mthd;
	}
	public String getBfhdcstn_apbtman_id() {
		return bfhdcstn_apbtman_id;
	}
	public void setBfhdcstn_apbtman_id(String bfhdcstn_apbtman_id) {
		this.bfhdcstn_apbtman_id = bfhdcstn_apbtman_id;
	}
	public String getBfhdcstn_apbtman_nm() {
		return bfhdcstn_apbtman_nm;
	}
	public void setBfhdcstn_apbtman_nm(String bfhdcstn_apbtman_nm) {
		this.bfhdcstn_apbtman_nm = bfhdcstn_apbtman_nm;
	}
	public String getBfhdcstn_apbtman_jikgup_nm() {
		return bfhdcstn_apbtman_jikgup_nm;
	}
	public void setBfhdcstn_apbtman_jikgup_nm(String bfhdcstn_apbtman_jikgup_nm) {
		this.bfhdcstn_apbtman_jikgup_nm = bfhdcstn_apbtman_jikgup_nm;
	}
	public String getBfhdcstn_apbt_dept_nm() {
		return bfhdcstn_apbt_dept_nm;
	}
	public void setBfhdcstn_apbt_dept_nm(String bfhdcstn_apbt_dept_nm) {
		this.bfhdcstn_apbt_dept_nm = bfhdcstn_apbt_dept_nm;
	}
	public String getBfhdcstn_apbtday() {
		return bfhdcstn_apbtday;
	}
	public void setBfhdcstn_apbtday(String bfhdcstn_apbtday) {
		this.bfhdcstn_apbtday = bfhdcstn_apbtday;
	}
	public String getSeal_rqstday() {
		return seal_rqstday;
	}
	public void setSeal_rqstday(String seal_rqstday) {
		this.seal_rqstday = seal_rqstday;
	}
	public String getSeal_rqstman_id() {
		return seal_rqstman_id;
	}
	public void setSeal_rqstman_id(String seal_rqstman_id) {
		this.seal_rqstman_id = seal_rqstman_id;
	}
	public String getSeal_rqstman_nm() {
		return seal_rqstman_nm;
	}
	public void setSeal_rqstman_nm(String seal_rqstman_nm) {
		this.seal_rqstman_nm = seal_rqstman_nm;
	}
	public String getSeal_ffmtman_id() {
		return seal_ffmtman_id;
	}
	public void setSeal_ffmtman_id(String seal_ffmtman_id) {
		this.seal_ffmtman_id = seal_ffmtman_id;
	}
	public String getSeal_ffmtman_nm() {
		return seal_ffmtman_nm;
	}
	public void setSeal_ffmtman_nm(String seal_ffmtman_nm) {
		this.seal_ffmtman_nm = seal_ffmtman_nm;
	}
	public String getSeal_ffmt_dept_cd() {
		return seal_ffmt_dept_cd;
	}
	public void setSeal_ffmt_dept_cd(String seal_ffmt_dept_cd) {
		this.seal_ffmt_dept_cd = seal_ffmt_dept_cd;
	}
	public String getSeal_ffmt_dept_nm() {
		return seal_ffmt_dept_nm;
	}
	public void setSeal_ffmt_dept_nm(String seal_ffmt_dept_nm) {
		this.seal_ffmt_dept_nm = seal_ffmt_dept_nm;
	}
	public String getSeal_ffmtman_jikgup_nm() {
		return seal_ffmtman_jikgup_nm;
	}
	public void setSeal_ffmtman_jikgup_nm(String seal_ffmtman_jikgup_nm) {
		this.seal_ffmtman_jikgup_nm = seal_ffmtman_jikgup_nm;
	}
	public String getSeal_ffmtman_search_nm() {
		return seal_ffmtman_search_nm;
	}
	public void setSeal_ffmtman_search_nm(String seal_ffmtman_search_nm) {
		this.seal_ffmtman_search_nm = seal_ffmtman_search_nm;
	}
	public String getSeal_ffmtday() {
		return seal_ffmtday;
	}
	public void setSeal_ffmtday(String seal_ffmtday) {
		this.seal_ffmtday = seal_ffmtday;
	}
	public String getReg_operdiv() {
		return reg_operdiv;
	}
	public void setReg_operdiv(String reg_operdiv) {
		this.reg_operdiv = reg_operdiv;
	}
	public String getCnsdreq_id() {
		return cnsdreq_id;
	}
	public void setCnsdreq_id(String cnsdreq_id) {
		this.cnsdreq_id = cnsdreq_id;
	}
	public String getAgree_yn() {
		return agree_yn;
	}
	public void setAgree_yn(String agree_yn) {
		this.agree_yn = agree_yn;
	}
	public String getPrgrs_status() {
		return prgrs_status;
	}
	public void setPrgrs_status(String prgrs_status) {
		this.prgrs_status = prgrs_status;
	}
	public int getAgree_seqno() {
		return agree_seqno;
	}
	public void setAgree_seqno(int agree_seqno) {
		this.agree_seqno = agree_seqno;
	}
	public String getCntrt_resp_info() {
		return cntrt_resp_info;
	}
	public void setCntrt_resp_info(String cntrt_resp_info) {
		this.cntrt_resp_info = cntrt_resp_info;
	}
	public String getReq_title() {
		return req_title;
	}
	public void setReq_title(String req_title) {
		this.req_title = req_title;
	}
	public String getReq_dt() {
		return req_dt;
	}
	public void setReq_dt(String req_dt) {
		this.req_dt = req_dt;
	}
	public String getReject_info() {
		return reject_info;
	}
	public void setReject_info(String reject_info) {
		this.reject_info = reject_info;
	}
	
	
	
	
}