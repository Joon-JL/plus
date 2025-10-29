package com.sec.las.lawservice.dto;

import java.lang.String;
import org.junit.Test;
import java.math.BigDecimal;
import static org.mockito.Mockito.mock;

public class EventAcceptSrvCostFormTest {

    @Test(timeout = 20000)
    public void testGetAcpt_noShouldNotThrowException() {
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
    public void testGetIntnl_dept_cdShouldNotThrowException() {
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
    public void testGetRemitdayShouldNotThrowException() {
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
    public void testGetEvent_noShouldNotThrowException() {
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
    public void testGetC_dtShouldNotThrowException() {
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
    public void testGetCrrncy_unitShouldNotThrowException() {
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
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
    public void testGetUsrateShouldNotThrowException() {
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
        boolean isNotException = true;
        try {
            clazz.getUsrate();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUsrateWithBigDecimalUsrateShouldNotThrowException() {
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
        BigDecimal usrate = mock(BigDecimal.class);
        boolean isNotException = true;
        try {
            clazz.setUsrate(usrate);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExrateShouldNotThrowException() {
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
        boolean isNotException = true;
        try {
            clazz.getExrate();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExrateWithBigDecimalExrateShouldNotThrowException() {
        EventAcceptSrvCostForm clazz = new EventAcceptSrvCostForm();
        BigDecimal exrate = mock(BigDecimal.class);
        boolean isNotException = true;
        try {
            clazz.setExrate(exrate);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
