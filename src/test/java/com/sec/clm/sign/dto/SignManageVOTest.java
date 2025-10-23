package com.sec.clm.sign.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class SignManageVOTest {

    @Test(timeout = 20000)
    public void testGetDoc_scrtxtShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getDoc_scrtxt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetsRbShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getsRb();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetsRbWithStringSRbShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String sRb = "sRb";
        boolean isNotException = true;
        try {
            clazz.setsRb(sRb);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDoc_scrtxtWithStringDocScrtxtShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String doc_scrtxt = "doc_scrtxt";
        boolean isNotException = true;
        try {
            clazz.setDoc_scrtxt(doc_scrtxt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRtn_seal_noShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getRtn_seal_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRtn_seal_noWithStringRtnSealNoShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String rtn_seal_no = "rtn_seal_no";
        boolean isNotException = true;
        try {
            clazz.setRtn_seal_no(rtn_seal_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_seal_rqstman_idShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_seal_rqstman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_seal_rqstman_idWithStringSrchSealRqstmanIdShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_seal_rqstman_id = "srch_seal_rqstman_id";
        boolean isNotException = true;
        try {
            clazz.setSrch_seal_rqstman_id(srch_seal_rqstman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExcel_methodShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getExcel_method();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExcel_methodWithStringExcelMethodShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String excel_method = "excel_method";
        boolean isNotException = true;
        try {
            clazz.setExcel_method(excel_method);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGoExcelShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getGoExcel();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGoExcelWithStringGoExcelShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String goExcel = "goExcel";
        boolean isNotException = true;
        try {
            clazz.setGoExcel(goExcel);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_rtnman_idShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSeal_rtnman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_rtnman_idWithStringSealRtnmanIdShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String seal_rtnman_id = "seal_rtnman_id";
        boolean isNotException = true;
        try {
            clazz.setSeal_rtnman_id(seal_rtnman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_rtnman_nmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSeal_rtnman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_rtnman_nmWithStringSealRtnmanNmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String seal_rtnman_nm = "seal_rtnman_nm";
        boolean isNotException = true;
        try {
            clazz.setSeal_rtnman_nm(seal_rtnman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_rtnman_dept_nmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSeal_rtnman_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_rtnman_dept_nmWithStringSealRtnmanDeptNmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String seal_rtnman_dept_nm = "seal_rtnman_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setSeal_rtnman_dept_nm(seal_rtnman_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_rtnman_jikgup_nmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSeal_rtnman_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_rtnman_jikgup_nmWithStringSealRtnmanJikgupNmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String seal_rtnman_jikgup_nm = "seal_rtnman_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setSeal_rtnman_jikgup_nm(seal_rtnman_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_apl_out_ynShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_apl_out_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_apl_out_ynWithStringSrchAplOutYnShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_apl_out_yn = "srch_apl_out_yn";
        boolean isNotException = true;
        try {
            clazz.setSrch_apl_out_yn(srch_apl_out_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_prc_seal_noShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_prc_seal_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_prc_seal_noWithStringSrchPrcSealNoShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_prc_seal_no = "srch_prc_seal_no";
        boolean isNotException = true;
        try {
            clazz.setSrch_prc_seal_no(srch_prc_seal_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_rtn_ynShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_rtn_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_rtn_ynWithStringSrchRtnYnShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_rtn_yn = "srch_rtn_yn";
        boolean isNotException = true;
        try {
            clazz.setSrch_rtn_yn(srch_rtn_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_prc_ymd_startShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_prc_ymd_start();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_prc_ymd_startWithStringSrchPrcYmdStartShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_prc_ymd_start = "srch_prc_ymd_start";
        boolean isNotException = true;
        try {
            clazz.setSrch_prc_ymd_start(srch_prc_ymd_start);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_prc_ymd_endShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_prc_ymd_end();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_prc_ymd_endWithStringSrchPrcYmdEndShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_prc_ymd_end = "srch_prc_ymd_end";
        boolean isNotException = true;
        try {
            clazz.setSrch_prc_ymd_end(srch_prc_ymd_end);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_rtn_ymd_startShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_rtn_ymd_start();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_rtn_ymd_startWithStringSrchRtnYmdStartShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_rtn_ymd_start = "srch_rtn_ymd_start";
        boolean isNotException = true;
        try {
            clazz.setSrch_rtn_ymd_start(srch_rtn_ymd_start);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_rtn_ymd_endShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_rtn_ymd_end();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_rtn_ymd_endWithStringSrchRtnYmdEndShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_rtn_ymd_end = "srch_rtn_ymd_end";
        boolean isNotException = true;
        try {
            clazz.setSrch_rtn_ymd_end(srch_rtn_ymd_end);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetForwardFromShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getForwardFrom();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetForwardFromWithStringForwardFromShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String forwardFrom = "forwardFrom";
        boolean isNotException = true;
        try {
            clazz.setForwardFrom(forwardFrom);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_seal_ffmtman_idShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_seal_ffmtman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_seal_ffmtman_idWithStringSrchSealFfmtmanIdShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_seal_ffmtman_id = "srch_seal_ffmtman_id";
        boolean isNotException = true;
        try {
            clazz.setSrch_seal_ffmtman_id(srch_seal_ffmtman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_doc_issuer_idShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_doc_issuer_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_doc_issuer_idWithStringSrchDocIssuerIdShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_doc_issuer_id = "srch_doc_issuer_id";
        boolean isNotException = true;
        try {
            clazz.setSrch_doc_issuer_id(srch_doc_issuer_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAuth_str_idShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getAuth_str_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_str_idWithStringAuthStrIdShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String auth_str_id = "auth_str_id";
        boolean isNotException = true;
        try {
            clazz.setAuth_str_id(auth_str_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dtShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getReg_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_dtWithStringRegDtShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String reg_dt = "reg_dt";
        boolean isNotException = true;
        try {
            clazz.setReg_dt(reg_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_nmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getReg_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_nmWithStringRegNmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String reg_nm = "reg_nm";
        boolean isNotException = true;
        try {
            clazz.setReg_nm(reg_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSelect_auth_apnt_ynShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSelect_auth_apnt_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSelect_auth_apnt_ynWithStringSelectAuthApntYnShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String select_auth_apnt_yn = "select_auth_apnt_yn";
        boolean isNotException = true;
        try {
            clazz.setSelect_auth_apnt_yn(select_auth_apnt_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGubn_cdShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getGubn_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGubn_cdWithStringGubnCdShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String gubn_cd = "gubn_cd";
        boolean isNotException = true;
        try {
            clazz.setGubn_cd(gubn_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAra_nmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getAra_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAra_nmWithStringAraNmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String ara_nm = "ara_nm";
        boolean isNotException = true;
        try {
            clazz.setAra_nm(ara_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCommentsShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getComments();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCommentsWithStringCommentsShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String comments = "comments";
        boolean isNotException = true;
        try {
            clazz.setComments(comments);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_apl_clsShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_apl_cls();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_apl_clsWithStringSrchAplClsShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_apl_cls = "srch_apl_cls";
        boolean isNotException = true;
        try {
            clazz.setSrch_apl_cls(srch_apl_cls);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_apl_yShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_apl_y();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_apl_yWithStringSrchAplYShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_apl_y = "srch_apl_y";
        boolean isNotException = true;
        try {
            clazz.setSrch_apl_y(srch_apl_y);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_apl_mShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_apl_m();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_apl_mWithStringSrchAplMShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_apl_m = "srch_apl_m";
        boolean isNotException = true;
        try {
            clazz.setSrch_apl_m(srch_apl_m);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_apl_prev30dShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_apl_prev30d();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_apl_prev30dWithStringSrchAplPrev30dShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_apl_prev30d = "srch_apl_prev30d";
        boolean isNotException = true;
        try {
            clazz.setSrch_apl_prev30d(srch_apl_prev30d);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_seal_rqst_statusShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_seal_rqst_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_seal_rqst_statusWithStringSrchSealRqstStatusShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_seal_rqst_status = "srch_seal_rqst_status";
        boolean isNotException = true;
        try {
            clazz.setSrch_seal_rqst_status(srch_seal_rqst_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_seal_knd_cdShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_seal_knd_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_seal_knd_cdWithStringSrchSealKndCdShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_seal_knd_cd = "srch_seal_knd_cd";
        boolean isNotException = true;
        try {
            clazz.setSrch_seal_knd_cd(srch_seal_knd_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_seal_ffmt_statusShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_seal_ffmt_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_seal_ffmt_statusWithStringSrchSealFfmtStatusShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_seal_ffmt_status = "srch_seal_ffmt_status";
        boolean isNotException = true;
        try {
            clazz.setSrch_seal_ffmt_status(srch_seal_ffmt_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_titleShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_title();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_titleWithStringSrchTitleShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_title = "srch_title";
        boolean isNotException = true;
        try {
            clazz.setSrch_title(srch_title);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_doc_noShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_doc_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_doc_noWithStringSrchDocNoShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_doc_no = "srch_doc_no";
        boolean isNotException = true;
        try {
            clazz.setSrch_doc_no(srch_doc_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_doc_issue_statusShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_doc_issue_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_doc_issue_statusWithStringSrchDocIssueStatusShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_doc_issue_status = "srch_doc_issue_status";
        boolean isNotException = true;
        try {
            clazz.setSrch_doc_issue_status(srch_doc_issue_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_sbmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_sbm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_sbmWithStringSrchSbmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_sbm = "srch_sbm";
        boolean isNotException = true;
        try {
            clazz.setSrch_sbm(srch_sbm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_seal_rqstman_nmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_seal_rqstman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_seal_rqstman_nmWithStringSrchSealRqstmanNmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_seal_rqstman_nm = "srch_seal_rqstman_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_seal_rqstman_nm(srch_seal_rqstman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_seal_ffmtman_nmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_seal_ffmtman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_seal_ffmtman_nmWithStringSrchSealFfmtmanNmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_seal_ffmtman_nm = "srch_seal_ffmtman_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_seal_ffmtman_nm(srch_seal_ffmtman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_seal_ffmtday_startShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_seal_ffmtday_start();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_seal_ffmtday_startWithStringSrchSealFfmtdayStartShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_seal_ffmtday_start = "srch_seal_ffmtday_start";
        boolean isNotException = true;
        try {
            clazz.setSrch_seal_ffmtday_start(srch_seal_ffmtday_start);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_seal_ffmtday_endShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_seal_ffmtday_end();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_seal_ffmtday_endWithStringSrchSealFfmtdayEndShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_seal_ffmtday_end = "srch_seal_ffmtday_end";
        boolean isNotException = true;
        try {
            clazz.setSrch_seal_ffmtday_end(srch_seal_ffmtday_end);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_apl_seal_noShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_apl_seal_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_apl_seal_noWithStringSrchAplSealNoShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String srch_apl_seal_no = "srch_apl_seal_no";
        boolean isNotException = true;
        try {
            clazz.setSrch_apl_seal_no(srch_apl_seal_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetComp_cdShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getComp_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetComp_cdWithStringCompCdShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String comp_cd = "comp_cd";
        boolean isNotException = true;
        try {
            clazz.setComp_cd(comp_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetComp_nmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getComp_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetComp_nmWithStringCompNmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String comp_nm = "comp_nm";
        boolean isNotException = true;
        try {
            clazz.setComp_nm(comp_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetApl_ymShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getApl_ym();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetApl_ymWithStringAplYmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String apl_ym = "apl_ym";
        boolean isNotException = true;
        try {
            clazz.setApl_ym(apl_ym);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetApl_sqnShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getApl_sqn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetApl_sqnWithStringAplSqnShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String apl_sqn = "apl_sqn";
        boolean isNotException = true;
        try {
            clazz.setApl_sqn(apl_sqn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetApl_clsShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getApl_cls();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetApl_clsWithStringAplClsShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String apl_cls = "apl_cls";
        boolean isNotException = true;
        try {
            clazz.setApl_cls(apl_cls);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTitleShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getTitle();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTitleWithStringTitleShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String title = "title";
        boolean isNotException = true;
        try {
            clazz.setTitle(title);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSbmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSbm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSbmWithStringSbmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String sbm = "sbm";
        boolean isNotException = true;
        try {
            clazz.setSbm(sbm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUse_summShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getUse_summ();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUse_summWithStringUseSummShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String use_summ = "use_summ";
        boolean isNotException = true;
        try {
            clazz.setUse_summ(use_summ);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTxtShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getTxt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTxtWithStringTxtShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String txt = "txt";
        boolean isNotException = true;
        try {
            clazz.setTxt(txt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_knd_cdShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSeal_knd_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_knd_cdWithStringSealKndCdShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String seal_knd_cd = "seal_knd_cd";
        boolean isNotException = true;
        try {
            clazz.setSeal_knd_cd(seal_knd_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_ynShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSeal_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_ynWithStringSealYnShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String seal_yn = "seal_yn";
        boolean isNotException = true;
        try {
            clazz.setSeal_yn(seal_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetApl_out_ynShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getApl_out_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetApl_out_ynWithStringAplOutYnShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String apl_out_yn = "apl_out_yn";
        boolean isNotException = true;
        try {
            clazz.setApl_out_yn(apl_out_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetApl_seal_noShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getApl_seal_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetApl_seal_noWithStringAplSealNoShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String apl_seal_no = "apl_seal_no";
        boolean isNotException = true;
        try {
            clazz.setApl_seal_no(apl_seal_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPrc_seal_noShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getPrc_seal_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPrc_seal_noWithStringPrcSealNoShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String prc_seal_no = "prc_seal_no";
        boolean isNotException = true;
        try {
            clazz.setPrc_seal_no(prc_seal_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetApl_ymd_fromShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getApl_ymd_from();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetApl_ymd_fromWithStringAplYmdFromShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String apl_ymd_from = "apl_ymd_from";
        boolean isNotException = true;
        try {
            clazz.setApl_ymd_from(apl_ymd_from);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetApl_ymd_toShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getApl_ymd_to();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetApl_ymd_toWithStringAplYmdToShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String apl_ymd_to = "apl_ymd_to";
        boolean isNotException = true;
        try {
            clazz.setApl_ymd_to(apl_ymd_to);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPrc_ymd_fromShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getPrc_ymd_from();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPrc_ymd_fromWithStringPrcYmdFromShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String prc_ymd_from = "prc_ymd_from";
        boolean isNotException = true;
        try {
            clazz.setPrc_ymd_from(prc_ymd_from);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPrc_ymd_toShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getPrc_ymd_to();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPrc_ymd_toWithStringPrcYmdToShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String prc_ymd_to = "prc_ymd_to";
        boolean isNotException = true;
        try {
            clazz.setPrc_ymd_to(prc_ymd_to);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRtn_ynShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getRtn_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRtn_ynWithStringRtnYnShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String rtn_yn = "rtn_yn";
        boolean isNotException = true;
        try {
            clazz.setRtn_yn(rtn_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRtn_ymdShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getRtn_ymd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRtn_ymdWithStringRtnYmdShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String rtn_ymd = "rtn_ymd";
        boolean isNotException = true;
        try {
            clazz.setRtn_ymd(rtn_ymd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDoc_ynShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getDoc_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDoc_ynWithStringDocYnShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String doc_yn = "doc_yn";
        boolean isNotException = true;
        try {
            clazz.setDoc_yn(doc_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDoc1ShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getDoc1();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDoc1WithStringDoc1ShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String doc1 = "doc1";
        boolean isNotException = true;
        try {
            clazz.setDoc1(doc1);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDoc2ShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getDoc2();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDoc2WithStringDoc2ShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String doc2 = "doc2";
        boolean isNotException = true;
        try {
            clazz.setDoc2(doc2);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDoc3ShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getDoc3();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDoc3WithStringDoc3ShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String doc3 = "doc3";
        boolean isNotException = true;
        try {
            clazz.setDoc3(doc3);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDoc4ShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getDoc4();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDoc4WithStringDoc4ShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String doc4 = "doc4";
        boolean isNotException = true;
        try {
            clazz.setDoc4(doc4);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDoc5ShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getDoc5();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDoc5WithStringDoc5ShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String doc5 = "doc5";
        boolean isNotException = true;
        try {
            clazz.setDoc5(doc5);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDoc6ShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getDoc6();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDoc6WithStringDoc6ShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String doc6 = "doc6";
        boolean isNotException = true;
        try {
            clazz.setDoc6(doc6);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDoc7ShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getDoc7();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDoc7WithStringDoc7ShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String doc7 = "doc7";
        boolean isNotException = true;
        try {
            clazz.setDoc7(doc7);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDoc8ShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getDoc8();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDoc8WithStringDoc8ShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String doc8 = "doc8";
        boolean isNotException = true;
        try {
            clazz.setDoc8(doc8);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDoc9ShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getDoc9();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDoc9WithStringDoc9ShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String doc9 = "doc9";
        boolean isNotException = true;
        try {
            clazz.setDoc9(doc9);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDoc10ShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getDoc10();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDoc10WithStringDoc10ShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String doc10 = "doc10";
        boolean isNotException = true;
        try {
            clazz.setDoc10(doc10);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_rqstman_idShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSeal_rqstman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_rqstman_idWithStringSealRqstmanIdShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String seal_rqstman_id = "seal_rqstman_id";
        boolean isNotException = true;
        try {
            clazz.setSeal_rqstman_id(seal_rqstman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_rqstman_nmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSeal_rqstman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_rqstman_nmWithStringSealRqstmanNmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String seal_rqstman_nm = "seal_rqstman_nm";
        boolean isNotException = true;
        try {
            clazz.setSeal_rqstman_nm(seal_rqstman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_rqstman_jikgup_nmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSeal_rqstman_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_rqstman_jikgup_nmWithStringSealRqstmanJikgupNmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String seal_rqstman_jikgup_nm = "seal_rqstman_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setSeal_rqstman_jikgup_nm(seal_rqstman_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_rqst_dept_nmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSeal_rqst_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_rqst_dept_nmWithStringSealRqstDeptNmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String seal_rqst_dept_nm = "seal_rqst_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setSeal_rqst_dept_nm(seal_rqst_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_rqst_statusShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSeal_rqst_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_rqst_statusWithStringSealRqstStatusShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String seal_rqst_status = "seal_rqst_status";
        boolean isNotException = true;
        try {
            clazz.setSeal_rqst_status(seal_rqst_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_rqstdayShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSeal_rqstday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_rqstdayWithStringSealRqstdayShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String seal_rqstday = "seal_rqstday";
        boolean isNotException = true;
        try {
            clazz.setSeal_rqstday(seal_rqstday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_ffmtman_idShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSeal_ffmtman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_ffmtman_idWithStringSealFfmtmanIdShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String seal_ffmtman_id = "seal_ffmtman_id";
        boolean isNotException = true;
        try {
            clazz.setSeal_ffmtman_id(seal_ffmtman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_ffmtman_nmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSeal_ffmtman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_ffmtman_nmWithStringSealFfmtmanNmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String seal_ffmtman_nm = "seal_ffmtman_nm";
        boolean isNotException = true;
        try {
            clazz.setSeal_ffmtman_nm(seal_ffmtman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_ffmtman_jikgup_nmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSeal_ffmtman_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_ffmtman_jikgup_nmWithStringSealFfmtmanJikgupNmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String seal_ffmtman_jikgup_nm = "seal_ffmtman_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setSeal_ffmtman_jikgup_nm(seal_ffmtman_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_ffmt_dept_nmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSeal_ffmt_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_ffmt_dept_nmWithStringSealFfmtDeptNmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String seal_ffmt_dept_nm = "seal_ffmt_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setSeal_ffmt_dept_nm(seal_ffmt_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_ffmt_statusShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSeal_ffmt_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_ffmt_statusWithStringSealFfmtStatusShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String seal_ffmt_status = "seal_ffmt_status";
        boolean isNotException = true;
        try {
            clazz.setSeal_ffmt_status(seal_ffmt_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_ffmtdayShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getSeal_ffmtday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_ffmtdayWithStringSealFfmtdayShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String seal_ffmtday = "seal_ffmtday";
        boolean isNotException = true;
        try {
            clazz.setSeal_ffmtday(seal_ffmtday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDoc_issuer_idShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getDoc_issuer_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDoc_issuer_idWithStringDocIssuerIdShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String doc_issuer_id = "doc_issuer_id";
        boolean isNotException = true;
        try {
            clazz.setDoc_issuer_id(doc_issuer_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDoc_issuer_nmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getDoc_issuer_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDoc_issuer_nmWithStringDocIssuerNmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String doc_issuer_nm = "doc_issuer_nm";
        boolean isNotException = true;
        try {
            clazz.setDoc_issuer_nm(doc_issuer_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDoc_issuer_jikgup_nmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getDoc_issuer_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDoc_issuer_jikgup_nmWithStringDocIssuerJikgupNmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String doc_issuer_jikgup_nm = "doc_issuer_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setDoc_issuer_jikgup_nm(doc_issuer_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDoc_issuer_dept_nmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getDoc_issuer_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDoc_issuer_dept_nmWithStringDocIssuerDeptNmShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String doc_issuer_dept_nm = "doc_issuer_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setDoc_issuer_dept_nm(doc_issuer_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDoc_issue_statusShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getDoc_issue_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDoc_issue_statusWithStringDocIssueStatusShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String doc_issue_status = "doc_issue_status";
        boolean isNotException = true;
        try {
            clazz.setDoc_issue_status(doc_issue_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDoc_issuedayShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getDoc_issueday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDoc_issuedayWithStringDocIssuedayShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String doc_issueday = "doc_issueday";
        boolean isNotException = true;
        try {
            clazz.setDoc_issueday(doc_issueday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRef_keyShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getRef_key();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRef_keyWithStringRefKeyShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String ref_key = "ref_key";
        boolean isNotException = true;
        try {
            clazz.setRef_key(ref_key);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnsdreq_idShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getCnsdreq_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnsdreq_idWithStringCnsdreqIdShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String cnsdreq_id = "cnsdreq_id";
        boolean isNotException = true;
        try {
            clazz.setCnsdreq_id(cnsdreq_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_idShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        boolean isNotException = true;
        try {
            clazz.getCntrt_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_idWithStringCntrtIdShouldNotThrowException() {
        SignManageVO clazz = new SignManageVO();
        String cntrt_id = "cntrt_id";
        boolean isNotException = true;
        try {
            clazz.setCntrt_id(cntrt_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
