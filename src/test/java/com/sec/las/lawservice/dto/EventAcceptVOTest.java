package com.sec.las.lawservice.dto;

import java.lang.String;
import org.junit.Test;
import java.math.BigDecimal;
import static org.mockito.Mockito.mock;

public class EventAcceptVOTest {

    @Test(timeout = 20000)
    public void testGetEvent_nmShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getEvent_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEvent_nmWithStringEventNmShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String event_nm = "event_nm";
        boolean isNotException = true;
        try {
            clazz.setEvent_nm(event_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEvent_gbn1ShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getEvent_gbn1();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEvent_gbn1WithStringEventGbn1ShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String event_gbn1 = "event_gbn1";
        boolean isNotException = true;
        try {
            clazz.setEvent_gbn1(event_gbn1);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEvent_gbn2ShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getEvent_gbn2();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEvent_gbn2WithStringEventGbn2ShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String event_gbn2 = "event_gbn2";
        boolean isNotException = true;
        try {
            clazz.setEvent_gbn2(event_gbn2);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetLwr_noShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getLwr_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLwr_noWithStringArrayLwrNoShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String[] lwr_no = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setLwr_no(lwr_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dtShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
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
        EventAcceptVO clazz = new EventAcceptVO();
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
    public void testGetReg_idShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getReg_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_idWithStringRegIdShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String reg_id = "reg_id";
        boolean isNotException = true;
        try {
            clazz.setReg_id(reg_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_nmShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
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
        EventAcceptVO clazz = new EventAcceptVO();
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
    public void testGetMod_dtShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getMod_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_dtWithStringModDtShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String mod_dt = "mod_dt";
        boolean isNotException = true;
        try {
            clazz.setMod_dt(mod_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMod_idShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getMod_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_idWithStringModIdShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String mod_id = "mod_id";
        boolean isNotException = true;
        try {
            clazz.setMod_id(mod_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMod_nmShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getMod_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_nmWithStringModNmShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String mod_nm = "mod_nm";
        boolean isNotException = true;
        try {
            clazz.setMod_nm(mod_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_ynShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getDel_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_ynWithStringDelYnShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String del_yn = "del_yn";
        boolean isNotException = true;
        try {
            clazz.setDel_yn(del_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_dtShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getDel_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_dtWithStringDelDtShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String del_dt = "del_dt";
        boolean isNotException = true;
        try {
            clazz.setDel_dt(del_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_idShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getDel_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_idWithStringDelIdShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String del_id = "del_id";
        boolean isNotException = true;
        try {
            clazz.setDel_id(del_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_nmShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getDel_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_nmWithStringDelNmShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String del_nm = "del_nm";
        boolean isNotException = true;
        try {
            clazz.setDel_nm(del_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetIntnl_dept_cdShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getIntnl_dept_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetIntnl_dept_cdWithStringArrayIntnlDeptCdShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String[] intnl_dept_cd = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setIntnl_dept_cd(intnl_dept_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGrp_dept_cdShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getGrp_dept_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGrp_dept_cdWithStringArrayGrpDeptCdShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String[] grp_dept_cd = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setGrp_dept_cd(grp_dept_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDept_nmShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getDept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDept_nmWithStringArrayDeptNmShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String[] dept_nm = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setDept_nm(dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTotamtShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getTotamt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTotamtWithBigDecimalArrayTotamtShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        BigDecimal[] totamt = mock(BigDecimal[].class);
        boolean isNotException = true;
        try {
            clazz.setTotamt(totamt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrvc_amtShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getSrvc_amt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrvc_amtWithBigDecimalArraySrvcAmtShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        BigDecimal[] srvc_amt = mock(BigDecimal[].class);
        boolean isNotException = true;
        try {
            clazz.setSrvc_amt(srvc_amt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAddtnl_amtShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getAddtnl_amt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAddtnl_amtWithBigDecimalArrayAddtnlAmtShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        BigDecimal[] addtnl_amt = mock(BigDecimal[].class);
        boolean isNotException = true;
        try {
            clazz.setAddtnl_amt(addtnl_amt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDc_rateShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getDc_rate();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDc_rateWithBigDecimalArrayDcRateShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        BigDecimal[] dc_rate = mock(BigDecimal[].class);
        boolean isNotException = true;
        try {
            clazz.setDc_rate(dc_rate);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPlnd_remit_amtShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getPlnd_remit_amt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPlnd_remit_amtWithBigDecimalArrayPlndRemitAmtShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        BigDecimal[] plnd_remit_amt = mock(BigDecimal[].class);
        boolean isNotException = true;
        try {
            clazz.setPlnd_remit_amt(plnd_remit_amt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUsd_amtShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getUsd_amt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUsd_amtWithBigDecimalArrayUsdAmtShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        BigDecimal[] usd_amt = mock(BigDecimal[].class);
        boolean isNotException = true;
        try {
            clazz.setUsd_amt(usd_amt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTot_remit_amtShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getTot_remit_amt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTot_remit_amtWithStringTotRemitAmtShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String tot_remit_amt = "tot_remit_amt";
        boolean isNotException = true;
        try {
            clazz.setTot_remit_amt(tot_remit_amt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetC_dtShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getC_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetC_dtWithStringCDtShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String c_dt = "c_dt";
        boolean isNotException = true;
        try {
            clazz.setC_dt(c_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAcpt_noShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getAcpt_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAcpt_noWithStringAcptNoShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String acpt_no = "acpt_no";
        boolean isNotException = true;
        try {
            clazz.setAcpt_no(acpt_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEvent_noShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getEvent_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEvent_noWithStringEventNoShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String event_no = "event_no";
        boolean isNotException = true;
        try {
            clazz.setEvent_no(event_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAcptdayShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getAcptday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAcptdayWithStringAcptdayShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String acptday = "acptday";
        boolean isNotException = true;
        try {
            clazz.setAcptday(acptday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetLawfirm_idShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getLawfirm_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLawfirm_idWithStringLawfirmIdShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String lawfirm_id = "lawfirm_id";
        boolean isNotException = true;
        try {
            clazz.setLawfirm_id(lawfirm_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetClaimdayShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getClaimday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetClaimdayWithStringClaimdayShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String claimday = "claimday";
        boolean isNotException = true;
        try {
            clazz.setClaimday(claimday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetClaim_amtShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getClaim_amt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetClaim_amtWithStringClaimAmtShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String claim_amt = "claim_amt";
        boolean isNotException = true;
        try {
            clazz.setClaim_amt(claim_amt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCrrncy_unitShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getCrrncy_unit();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCrrncy_unitWithStringCrrncyUnitShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String crrncy_unit = "crrncy_unit";
        boolean isNotException = true;
        try {
            clazz.setCrrncy_unit(crrncy_unit);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetClaim_contShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getClaim_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetClaim_contWithStringClaimContShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String claim_cont = "claim_cont";
        boolean isNotException = true;
        try {
            clazz.setClaim_cont(claim_cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetInvoice_noShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getInvoice_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetInvoice_noWithStringInvoiceNoShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String invoice_no = "invoice_no";
        boolean isNotException = true;
        try {
            clazz.setInvoice_no(invoice_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrvcstartdayShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getSrvcstartday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrvcstartdayWithStringSrvcstartdayShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String srvcstartday = "srvcstartday";
        boolean isNotException = true;
        try {
            clazz.setSrvcstartday(srvcstartday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrvcenddayShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getSrvcendday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrvcenddayWithStringSrvcenddayShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String srvcendday = "srvcendday";
        boolean isNotException = true;
        try {
            clazz.setSrvcendday(srvcendday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRemit_amtShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getRemit_amt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRemit_amtWithStringRemitAmtShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String remit_amt = "remit_amt";
        boolean isNotException = true;
        try {
            clazz.setRemit_amt(remit_amt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRemitplnddayShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getRemitplndday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRemitplnddayWithStringRemitplnddayShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String remitplndday = "remitplndday";
        boolean isNotException = true;
        try {
            clazz.setRemitplndday(remitplndday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRemitdayShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getRemitday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRemitdayWithStringRemitdayShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String remitday = "remitday";
        boolean isNotException = true;
        try {
            clazz.setRemitday(remitday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUnpaydayShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getUnpayday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUnpaydayWithStringUnpaydayShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String unpayday = "unpayday";
        boolean isNotException = true;
        try {
            clazz.setUnpayday(unpayday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReview_ynShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getReview_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReview_ynWithStringReviewYnShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String review_yn = "review_yn";
        boolean isNotException = true;
        try {
            clazz.setReview_yn(review_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReview_contShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getReview_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReview_contWithStringReviewContShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String review_cont = "review_cont";
        boolean isNotException = true;
        try {
            clazz.setReview_cont(review_cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetLawfirm_nmShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getLawfirm_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLawfirm_nmWithStringLawfirmNmShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String lawfirm_nm = "lawfirm_nm";
        boolean isNotException = true;
        try {
            clazz.setLawfirm_nm(lawfirm_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetLawsuit_trgtShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getLawsuit_trgt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLawsuit_trgtWithStringLawsuitTrgtShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String lawsuit_trgt = "lawsuit_trgt";
        boolean isNotException = true;
        try {
            clazz.setLawsuit_trgt(lawsuit_trgt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetLawsuit_trgt_nmShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getLawsuit_trgt_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLawsuit_trgt_nmWithStringLawsuitTrgtNmShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String lawsuit_trgt_nm = "lawsuit_trgt_nm";
        boolean isNotException = true;
        try {
            clazz.setLawsuit_trgt_nm(lawsuit_trgt_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUpfile_ynShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        boolean isNotException = true;
        try {
            clazz.getUpfile_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUpfile_ynWithStringUpfileYnShouldNotThrowException() {
        EventAcceptVO clazz = new EventAcceptVO();
        String upfile_yn = "upfile_yn";
        boolean isNotException = true;
        try {
            clazz.setUpfile_yn(upfile_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
