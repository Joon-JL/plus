package com.sec.clm.manage.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import java.util.List;
import static org.mockito.Mockito.mock;

public class ManageVOTest {

    @Test(timeout = 20000)
    public void testGetComp_cdShouldNotThrowException() {
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
    public void testGetRequest_YnShouldNotThrowException() {
        ManageVO clazz = new ManageVO();
        boolean isNotException = true;
        try {
            clazz.getRequest_Yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRequest_YnWithStringRequestYnShouldNotThrowException() {
        ManageVO clazz = new ManageVO();
        String request_Yn = "request_Yn";
        boolean isNotException = true;
        try {
            clazz.setRequest_Yn(request_Yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetArgShouldNotThrowException() {
        ManageVO clazz = new ManageVO();
        boolean isNotException = true;
        try {
            clazz.getArg();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetElUserlYnShouldNotThrowException() {
        ManageVO clazz = new ManageVO();
        boolean isNotException = true;
        try {
            clazz.getElUserlYn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetElUserlYnWithStringElUserlYnShouldNotThrowException() {
        ManageVO clazz = new ManageVO();
        String elUserlYn = "elUserlYn";
        boolean isNotException = true;
        try {
            clazz.setElUserlYn(elUserlYn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSElCompShouldNotThrowException() {
        ManageVO clazz = new ManageVO();
        boolean isNotException = true;
        try {
            clazz.getSElComp();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSElCompWithStringSElCompShouldNotThrowException() {
        ManageVO clazz = new ManageVO();
        String sElComp = "sElComp";
        boolean isNotException = true;
        try {
            clazz.setSElComp(sElComp);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetArgWithStringArgShouldNotThrowException() {
        ManageVO clazz = new ManageVO();
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
    public void testGetStatus_modeShouldNotThrowException() {
        ManageVO clazz = new ManageVO();
        boolean isNotException = true;
        try {
            clazz.getStatus_mode();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_lSrch_Type_cdShouldNotThrowException() {
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
    public void testGetSrch_start_cnlsndayShouldNotThrowException() {
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
    public void testGetIsExcelFlagShouldNotThrowException() {
        ManageVO clazz = new ManageVO();
        boolean isNotException = true;
        try {
            clazz.getIsExcelFlag();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetIsExcelFlagWithStringIsExcelFlagShouldNotThrowException() {
        ManageVO clazz = new ManageVO();
        String isExcelFlag = "isExcelFlag";
        boolean isNotException = true;
        try {
            clazz.setIsExcelFlag(isExcelFlag);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_if_sys_cdShouldNotThrowException() {
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
    public void testGetDept_gbnShouldNotThrowException() {
        ManageVO clazz = new ManageVO();
        boolean isNotException = true;
        try {
            clazz.getDept_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDept_gbnWithStringDeptGbnShouldNotThrowException() {
        ManageVO clazz = new ManageVO();
        String dept_gbn = "dept_gbn";
        boolean isNotException = true;
        try {
            clazz.setDept_gbn(dept_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExcept_cntrt_idShouldNotThrowException() {
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
    public void testGetsElCompShouldNotThrowException() {
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
    public void testGetSrch_divisionShouldNotThrowException() {
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
        ManageVO clazz = new ManageVO();
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
