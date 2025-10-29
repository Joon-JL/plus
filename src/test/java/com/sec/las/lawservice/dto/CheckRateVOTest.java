package com.sec.las.lawservice.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import java.math.BigDecimal;
import static org.mockito.Mockito.mock;

public class CheckRateVOTest {

    @Test(timeout = 20000)
    public void testGetC_dtShouldNotThrowException() {
        CheckRateVO clazz = new CheckRateVO();
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
        CheckRateVO clazz = new CheckRateVO();
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
        CheckRateVO clazz = new CheckRateVO();
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
        CheckRateVO clazz = new CheckRateVO();
        String crrncy_unit = "crrncy_unit";
        boolean isNotException = true;
        try {
            clazz.setCrrncy_unit(crrncy_unit);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
