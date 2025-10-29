package com.sec.clm.manage.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import java.util.List;
import static org.mockito.Mockito.mock;

public class ManageFormTest {

    @Test(timeout = 20000)
    public void testGetArgShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getArg();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetsSel_grdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getsSel_grd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetsSel_grdWithStringSSelGrdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String sSel_grd = "sSel_grd";
        boolean isNotException = true;
        try {
            clazz.setsSel_grd(sSel_grd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetsElCompShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getsElComp();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetsElCompWithStringSElCompShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String sElComp = "sElComp";
        boolean isNotException = true;
        try {
            clazz.setsElComp(sElComp);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetArgWithStringArgShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String arg = "arg";
        boolean isNotException = true;
        try {
            clazz.setArg(arg);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetsReturnUrlShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getsReturnUrl();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetsReturnUrlWithStringSReturnUrlShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String sReturnUrl = "sReturnUrl";
        boolean isNotException = true;
        try {
            clazz.setsReturnUrl(sReturnUrl);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSubmit_statusShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSubmit_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSubmit_statusWithStringSubmitStatusShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String submit_status = "submit_status";
        boolean isNotException = true;
        try {
            clazz.setSubmit_status(submit_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_lSrch_Type_cdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_lSrch_Type_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_lSrch_Type_cdWithListSrchLSrchTypeCdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        List srch_lSrch_Type_cd = mock(List.class);
        boolean isNotException = true;
        try {
            clazz.setSrch_lSrch_Type_cd(srch_lSrch_Type_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_lsrch_Ltype_cdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_lsrch_Ltype_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_lsrch_Ltype_cdWithListSrchLsrchLtypeCdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        List srch_lsrch_Ltype_cd = mock(List.class);
        boolean isNotException = true;
        try {
            clazz.setSrch_lsrch_Ltype_cd(srch_lsrch_Ltype_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_lsrch_Mtype_cdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_lsrch_Mtype_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_lsrch_Mtype_cdWithListSrchLsrchMtypeCdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        List srch_lsrch_Mtype_cd = mock(List.class);
        boolean isNotException = true;
        try {
            clazz.setSrch_lsrch_Mtype_cd(srch_lsrch_Mtype_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_lsrch_Stype_cdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_lsrch_Stype_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_lsrch_Stype_cdWithListSrchLsrchStypeCdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        List srch_lsrch_Stype_cd = mock(List.class);
        boolean isNotException = true;
        try {
            clazz.setSrch_lsrch_Stype_cd(srch_lsrch_Stype_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_sSrch_Ltype_cdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_sSrch_Ltype_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_sSrch_Ltype_cdWithStringSrchSSrchLtypeCdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_sSrch_Ltype_cd = "srch_sSrch_Ltype_cd";
        boolean isNotException = true;
        try {
            clazz.setSrch_sSrch_Ltype_cd(srch_sSrch_Ltype_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_sSrch_Mtype_cdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_sSrch_Mtype_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_sSrch_Mtype_cdWithStringSrchSSrchMtypeCdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_sSrch_Mtype_cd = "srch_sSrch_Mtype_cd";
        boolean isNotException = true;
        try {
            clazz.setSrch_sSrch_Mtype_cd(srch_sSrch_Mtype_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_sSrch_Stype_cdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_sSrch_Stype_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_sSrch_Stype_cdWithStringSrchSSrchStypeCdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_sSrch_Stype_cd = "srch_sSrch_Stype_cd";
        boolean isNotException = true;
        try {
            clazz.setSrch_sSrch_Stype_cd(srch_sSrch_Stype_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_cntrt_trgt_det2ShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_cntrt_trgt_det2();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_cntrt_trgt_det2WithStringSrchCntrtTrgtDet2ShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_cntrt_trgt_det2 = "srch_cntrt_trgt_det2";
        boolean isNotException = true;
        try {
            clazz.setSrch_cntrt_trgt_det2(srch_cntrt_trgt_det2);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_lPayment_gbnShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_lPayment_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_lPayment_gbnWithListSrchLPaymentGbnShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        List srch_lPayment_gbn = mock(List.class);
        boolean isNotException = true;
        try {
            clazz.setSrch_lPayment_gbn(srch_lPayment_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_sPayment_gbnShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_sPayment_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_sPayment_gbnWithStringSrchSPaymentGbnShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_sPayment_gbn = "srch_sPayment_gbn";
        boolean isNotException = true;
        try {
            clazz.setSrch_sPayment_gbn(srch_sPayment_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_bfhdcstn_apbtman_nmShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_bfhdcstn_apbtman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_bfhdcstn_apbtman_nmWithStringSrchBfhdcstnApbtmanNmShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_bfhdcstn_apbtman_nm = "srch_bfhdcstn_apbtman_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_bfhdcstn_apbtman_nm(srch_bfhdcstn_apbtman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_lMn_cnsd_deptShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_lMn_cnsd_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_lMn_cnsd_deptWithListSrchLMnCnsdDeptShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        List srch_lMn_cnsd_dept = mock(List.class);
        boolean isNotException = true;
        try {
            clazz.setSrch_lMn_cnsd_dept(srch_lMn_cnsd_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_sMn_cnsd_deptShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_sMn_cnsd_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_sMn_cnsd_deptWithStringSrchSMnCnsdDeptShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_sMn_cnsd_dept = "srch_sMn_cnsd_dept";
        boolean isNotException = true;
        try {
            clazz.setSrch_sMn_cnsd_dept(srch_sMn_cnsd_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_mn_cnsd_deptShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_mn_cnsd_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_mn_cnsd_deptWithListSrchMnCnsdDeptShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        List srch_mn_cnsd_dept = mock(List.class);
        boolean isNotException = true;
        try {
            clazz.setSrch_mn_cnsd_dept(srch_mn_cnsd_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_str_org_acptdayShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_str_org_acptday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_str_org_acptdayWithStringSrchStrOrgAcptdayShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_str_org_acptday = "srch_str_org_acptday";
        boolean isNotException = true;
        try {
            clazz.setSrch_str_org_acptday(srch_str_org_acptday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_end_org_acptdayShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_end_org_acptday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_end_org_acptdayWithStringSrchEndOrgAcptdayShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_end_org_acptday = "srch_end_org_acptday";
        boolean isNotException = true;
        try {
            clazz.setSrch_end_org_acptday(srch_end_org_acptday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_auto_rnew_ynShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_auto_rnew_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_auto_rnew_ynWithStringSrchAutoRnewYnShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_auto_rnew_yn = "srch_auto_rnew_yn";
        boolean isNotException = true;
        try {
            clazz.setSrch_auto_rnew_yn(srch_auto_rnew_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_lAuto_rnew_ynShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_lAuto_rnew_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_lAuto_rnew_ynWithListSrchLAutoRnewYnShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        List srch_lAuto_rnew_yn = mock(List.class);
        boolean isNotException = true;
        try {
            clazz.setSrch_lAuto_rnew_yn(srch_lAuto_rnew_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_lSeal_mthdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_lSeal_mthd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_lSeal_mthdWithListSrchLSealMthdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        List srch_lSeal_mthd = mock(List.class);
        boolean isNotException = true;
        try {
            clazz.setSrch_lSeal_mthd(srch_lSeal_mthd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_sSeal_mthdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_sSeal_mthd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_sSeal_mthdWithStringSrchSSealMthdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_sSeal_mthd = "srch_sSeal_mthd";
        boolean isNotException = true;
        try {
            clazz.setSrch_sSeal_mthd(srch_sSeal_mthd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_signman_nmShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_signman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_signman_nmWithStringSrchSignmanNmShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_signman_nm = "srch_signman_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_signman_nm(srch_signman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_lLoacShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_lLoac();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_lLoacWithListSrchLLoacShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        List srch_lLoac = mock(List.class);
        boolean isNotException = true;
        try {
            clazz.setSrch_lLoac(srch_lLoac);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_sLoacShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_sLoac();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_sLoacWithStringSrchSLoacShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_sLoac = "srch_sLoac";
        boolean isNotException = true;
        try {
            clazz.setSrch_sLoac(srch_sLoac);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_lDesp_resolt_mthdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_lDesp_resolt_mthd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_lDesp_resolt_mthdWithListSrchLDespResoltMthdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        List srch_lDesp_resolt_mthd = mock(List.class);
        boolean isNotException = true;
        try {
            clazz.setSrch_lDesp_resolt_mthd(srch_lDesp_resolt_mthd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_sDesp_resolt_mthdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_sDesp_resolt_mthd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_sDesp_resolt_mthdWithStringSrchSDespResoltMthdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_sDesp_resolt_mthd = "srch_sDesp_resolt_mthd";
        boolean isNotException = true;
        try {
            clazz.setSrch_sDesp_resolt_mthd(srch_sDesp_resolt_mthd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStatus_modeShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getStatus_mode();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_start_cnlsndayShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_start_cnlsnday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_start_cnlsndayWithStringSrchStartCnlsndayShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_start_cnlsnday = "srch_start_cnlsnday";
        boolean isNotException = true;
        try {
            clazz.setSrch_start_cnlsnday(srch_start_cnlsnday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_end_cnlsndayShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_end_cnlsnday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_end_cnlsndayWithStringSrchEndCnlsndayShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_end_cnlsnday = "srch_end_cnlsnday";
        boolean isNotException = true;
        try {
            clazz.setSrch_end_cnlsnday(srch_end_cnlsnday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetStatus_modeWithStringStatusModeShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String status_mode = "status_mode";
        boolean isNotException = true;
        try {
            clazz.setStatus_mode(status_mode);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetList_modeShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getList_mode();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetList_modeWithStringListModeShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String list_mode = "list_mode";
        boolean isNotException = true;
        try {
            clazz.setList_mode(list_mode);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_review_titleShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_review_title();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_review_titleWithStringSrchReviewTitleShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_review_title = "srch_review_title";
        boolean isNotException = true;
        try {
            clazz.setSrch_review_title(srch_review_title);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_reqman_nmShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_reqman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_reqman_nmWithStringSrchReqmanNmShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_reqman_nm = "srch_reqman_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_reqman_nm(srch_reqman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_start_reqdayShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_start_reqday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_start_reqdayWithStringSrchStartReqdayShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_start_reqday = "srch_start_reqday";
        boolean isNotException = true;
        try {
            clazz.setSrch_start_reqday(srch_start_reqday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_end_reqdayShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_end_reqday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_end_reqdayWithStringSrchEndReqdayShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_end_reqday = "srch_end_reqday";
        boolean isNotException = true;
        try {
            clazz.setSrch_end_reqday(srch_end_reqday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_respman_nmShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_respman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_respman_nmWithStringSrchRespmanNmShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_respman_nm = "srch_respman_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_respman_nm(srch_respman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_resp_deptShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_resp_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_resp_deptWithStringSrchRespDeptShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_resp_dept = "srch_resp_dept";
        boolean isNotException = true;
        try {
            clazz.setSrch_resp_dept(srch_resp_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_resp_dept_nmShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_resp_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_resp_dept_nmWithStringSrchRespDeptNmShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_resp_dept_nm = "srch_resp_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_resp_dept_nm(srch_resp_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_oppnt_cdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_oppnt_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_oppnt_cdWithStringSrchOppntCdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_oppnt_cd = "srch_oppnt_cd";
        boolean isNotException = true;
        try {
            clazz.setSrch_oppnt_cd(srch_oppnt_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_oppnt_nmShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_oppnt_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_oppnt_nmWithStringSrchOppntNmShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_oppnt_nm = "srch_oppnt_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_oppnt_nm(srch_oppnt_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_cnsdman_nmShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_cnsdman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_cnsdman_nmWithStringSrchCnsdmanNmShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_cnsdman_nm = "srch_cnsdman_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_cnsdman_nm(srch_cnsdman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_biz_clsfcnShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_biz_clsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_biz_clsfcnWithStringSrchBizClsfcnShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_biz_clsfcn = "srch_biz_clsfcn";
        boolean isNotException = true;
        try {
            clazz.setSrch_biz_clsfcn(srch_biz_clsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_cnclsnpurps_bigclsfcnShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_cnclsnpurps_bigclsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_cnclsnpurps_bigclsfcnWithStringSrchCnclsnpurpsBigclsfcnShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_cnclsnpurps_bigclsfcn = "srch_cnclsnpurps_bigclsfcn";
        boolean isNotException = true;
        try {
            clazz.setSrch_cnclsnpurps_bigclsfcn(srch_cnclsnpurps_bigclsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_stepShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_step();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_stepWithStringSrchStepShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_step = "srch_step";
        boolean isNotException = true;
        try {
            clazz.setSrch_step(srch_step);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_stateShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_state();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_stateWithStringSrchStateShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_state = "srch_state";
        boolean isNotException = true;
        try {
            clazz.setSrch_state(srch_state);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_demand_gbnShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_demand_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_demand_gbnWithStringSrchDemandGbnShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_demand_gbn = "srch_demand_gbn";
        boolean isNotException = true;
        try {
            clazz.setSrch_demand_gbn(srch_demand_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_demand_statusShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_demand_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_demand_statusWithStringSrchDemandStatusShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_demand_status = "srch_demand_status";
        boolean isNotException = true;
        try {
            clazz.setSrch_demand_status(srch_demand_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetLinkViewFlagShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getLinkViewFlag();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLinkViewFlagWithStringLinkViewFlagShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String linkViewFlag = "linkViewFlag";
        boolean isNotException = true;
        try {
            clazz.setLinkViewFlag(linkViewFlag);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_if_sys_cdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_if_sys_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_if_sys_cdWithStringSrchIfSysCdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_if_sys_cd = "srch_if_sys_cd";
        boolean isNotException = true;
        try {
            clazz.setSrch_if_sys_cd(srch_if_sys_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_cntrt_nmShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_cntrt_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_cntrt_nmWithStringSrchCntrtNmShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_cntrt_nm = "srch_cntrt_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_cntrt_nm(srch_cntrt_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_cntrt_noShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_cntrt_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_cntrt_noWithStringSrchCntrtNoShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_cntrt_no = "srch_cntrt_no";
        boolean isNotException = true;
        try {
            clazz.setSrch_cntrt_no(srch_cntrt_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetIsOrgMgrShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getIsOrgMgr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetIsOrgMgrWithStringIsOrgMgrShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String isOrgMgr = "isOrgMgr";
        boolean isNotException = true;
        try {
            clazz.setIsOrgMgr(isOrgMgr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_mn_cnsdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_mn_cnsd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_mn_cnsdWithStringSrchMnCnsdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_mn_cnsd = "srch_mn_cnsd";
        boolean isNotException = true;
        try {
            clazz.setSrch_mn_cnsd(srch_mn_cnsd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExcept_cntrt_idShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getExcept_cntrt_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExcept_cntrt_idWithStringExceptCntrtIdShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String except_cntrt_id = "except_cntrt_id";
        boolean isNotException = true;
        try {
            clazz.setExcept_cntrt_id(except_cntrt_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetClosed_ynShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getClosed_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetClosed_ynWithStringClosedYnShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String closed_yn = "closed_yn";
        boolean isNotException = true;
        try {
            clazz.setClosed_yn(closed_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_divisionShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_division();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_divisionWithStringSrchDivisionShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_division = "srch_division";
        boolean isNotException = true;
        try {
            clazz.setSrch_division(srch_division);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_vendor_typeShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_vendor_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_vendor_typeWithStringSrchVendorTypeShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_vendor_type = "srch_vendor_type";
        boolean isNotException = true;
        try {
            clazz.setSrch_vendor_type(srch_vendor_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_vendor_type_detailShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_vendor_type_detail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_vendor_type_detailWithStringSrchVendorTypeDetailShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_vendor_type_detail = "srch_vendor_type_detail";
        boolean isNotException = true;
        try {
            clazz.setSrch_vendor_type_detail(srch_vendor_type_detail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHq_cnsd_statusShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getHq_cnsd_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHq_cnsd_statusWithStringHqCnsdStatusShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String hq_cnsd_status = "hq_cnsd_status";
        boolean isNotException = true;
        try {
            clazz.setHq_cnsd_status(hq_cnsd_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_tnc_noShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_tnc_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_tnc_noWithStringSrchTncNoShouldNotThrowException() {
        ManageForm clazz = new ManageForm();
        String srch_tnc_no = "srch_tnc_no";
        boolean isNotException = true;
        try {
            clazz.setSrch_tnc_no(srch_tnc_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
