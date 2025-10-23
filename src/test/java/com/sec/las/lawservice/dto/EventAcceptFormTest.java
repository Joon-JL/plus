package com.sec.las.lawservice.dto;

import java.lang.String;
import org.junit.Test;
import java.math.BigDecimal;
import static org.mockito.Mockito.mock;

public class EventAcceptFormTest {

    @Test(timeout = 20000)
    public void testGetEvent_nmShouldNotThrowException() {
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
    public void testGetIntnl_dept_cdShouldNotThrowException() {
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
        boolean isNotException = true;
        try {
            clazz.getTot_remit_amt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTot_remit_amtWithBigDecimalArrayTotRemitAmtShouldNotThrowException() {
        EventAcceptForm clazz = new EventAcceptForm();
        BigDecimal[] tot_remit_amt = mock(BigDecimal[].class);
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
        EventAcceptForm clazz = new EventAcceptForm();
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
