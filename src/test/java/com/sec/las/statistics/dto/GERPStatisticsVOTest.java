package com.sec.las.statistics.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class GERPStatisticsVOTest {

    @Test(timeout = 20000)
    public void testSetTypeWithStringTypeShouldNotThrowException() {
        GERPStatisticsVO clazz = new GERPStatisticsVO();
        String type = "type";
        boolean isNotException = true;
        try {
            clazz.setType(type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTypeShouldNotThrowException() {
        GERPStatisticsVO clazz = new GERPStatisticsVO();
        boolean isNotException = true;
        try {
            clazz.getType();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTargetYearWithStringTargetYearShouldNotThrowException() {
        GERPStatisticsVO clazz = new GERPStatisticsVO();
        String targetYear = "targetYear";
        boolean isNotException = true;
        try {
            clazz.setTargetYear(targetYear);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTargetYearShouldNotThrowException() {
        GERPStatisticsVO clazz = new GERPStatisticsVO();
        boolean isNotException = true;
        try {
            clazz.getTargetYear();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCompCdWithStringCompCdShouldNotThrowException() {
        GERPStatisticsVO clazz = new GERPStatisticsVO();
        String compCd = "compCd";
        boolean isNotException = true;
        try {
            clazz.setCompCd(compCd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCompCdShouldNotThrowException() {
        GERPStatisticsVO clazz = new GERPStatisticsVO();
        boolean isNotException = true;
        try {
            clazz.getCompCd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetVendorTypeWithStringVenderTypeShouldNotThrowException() {
        GERPStatisticsVO clazz = new GERPStatisticsVO();
        String venderType = "venderType";
        boolean isNotException = true;
        try {
            clazz.setVendorType(venderType);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetVendorTypeShouldNotThrowException() {
        GERPStatisticsVO clazz = new GERPStatisticsVO();
        boolean isNotException = true;
        try {
            clazz.getVendorType();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
