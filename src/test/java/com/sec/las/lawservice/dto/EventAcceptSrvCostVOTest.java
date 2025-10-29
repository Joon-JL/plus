package com.sec.las.lawservice.dto;

import java.lang.String;
import org.junit.Test;
import java.math.BigDecimal;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class EventAcceptSrvCostVOTest {

    @Test(timeout = 20000)
    public void testGetTot_remit_amtShouldNotThrowException() {
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
        boolean isNotException = true;
        try {
            clazz.getTot_remit_amt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTot_remit_amtWithBigDecimalTotRemitAmtShouldNotThrowException() {
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
        BigDecimal tot_remit_amt = mock(BigDecimal.class);
        boolean isNotException = true;
        try {
            clazz.setTot_remit_amt(tot_remit_amt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAcpt_noShouldNotThrowException() {
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
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
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
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
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
        boolean isNotException = true;
        try {
            clazz.getIntnl_dept_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetIntnl_dept_cdWithStringIntnlDeptCdShouldNotThrowException() {
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
        String intnl_dept_cd = "intnl_dept_cd";
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
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
        boolean isNotException = true;
        try {
            clazz.getGrp_dept_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGrp_dept_cdWithStringGrpDeptCdShouldNotThrowException() {
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
        String grp_dept_cd = "grp_dept_cd";
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
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
        boolean isNotException = true;
        try {
            clazz.getDept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDept_nmWithStringDeptNmShouldNotThrowException() {
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
        String dept_nm = "dept_nm";
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
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
        boolean isNotException = true;
        try {
            clazz.getTotamt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTotamtWithBigDecimalTotamtShouldNotThrowException() {
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
        BigDecimal totamt = mock(BigDecimal.class);
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
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
        boolean isNotException = true;
        try {
            clazz.getSrvc_amt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrvc_amtWithBigDecimalSrvcAmtShouldNotThrowException() {
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
        BigDecimal srvc_amt = mock(BigDecimal.class);
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
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
        boolean isNotException = true;
        try {
            clazz.getAddtnl_amt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAddtnl_amtWithBigDecimalAddtnlAmtShouldNotThrowException() {
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
        BigDecimal addtnl_amt = mock(BigDecimal.class);
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
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
        boolean isNotException = true;
        try {
            clazz.getDc_rate();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDc_rateWithBigDecimalDcRateShouldNotThrowException() {
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
        BigDecimal dc_rate = mock(BigDecimal.class);
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
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
        boolean isNotException = true;
        try {
            clazz.getPlnd_remit_amt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPlnd_remit_amtWithBigDecimalPlndRemitAmtShouldNotThrowException() {
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
        BigDecimal plnd_remit_amt = mock(BigDecimal.class);
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
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
        boolean isNotException = true;
        try {
            clazz.getUsd_amt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUsd_amtWithBigDecimalUsdAmtShouldNotThrowException() {
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
        BigDecimal usd_amt = mock(BigDecimal.class);
        boolean isNotException = true;
        try {
            clazz.setUsd_amt(usd_amt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRemitdayShouldNotThrowException() {
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
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
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
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
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
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
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
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
    public void testGetReg_dtShouldNotThrowException() {
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
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
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
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
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
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
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
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
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
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
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
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
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
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
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
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
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
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
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
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
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
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
        EventAcceptSrvCostVO clazz = new EventAcceptSrvCostVO();
        String mod_nm = "mod_nm";
        boolean isNotException = true;
        try {
            clazz.setMod_nm(mod_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
