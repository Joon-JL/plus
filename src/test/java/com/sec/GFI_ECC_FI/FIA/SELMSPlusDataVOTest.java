package com.sec.GFI_ECC_FI.FIA;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;
import java.util.HashMap;

public class SELMSPlusDataVOTest {

    @Test(timeout = 20000)
    public void testSetTargetDateWithStringTargetDateShouldNotThrowException() {
        SELMSPlusDataVO clazz = new SELMSPlusDataVO();
        String targetDate = "targetDate";
        boolean isNotException = true;
        try {
            clazz.setTargetDate(targetDate);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTargetDateShouldNotThrowException() {
        SELMSPlusDataVO clazz = new SELMSPlusDataVO();
        boolean isNotException = true;
        try {
            clazz.getTargetDate();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTargetHourWithIntTargetHourShouldNotThrowException() {
        SELMSPlusDataVO clazz = new SELMSPlusDataVO();
        int targetHour = 0;
        boolean isNotException = true;
        try {
            clazz.setTargetHour(targetHour);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTargetHourShouldNotThrowException() {
        SELMSPlusDataVO clazz = new SELMSPlusDataVO();
        boolean isNotException = true;
        try {
            clazz.getTargetHour();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTargetIntervalWithIntTargetIntervalShouldNotThrowException() {
        SELMSPlusDataVO clazz = new SELMSPlusDataVO();
        int targetInterval = 0;
        boolean isNotException = true;
        try {
            clazz.setTargetInterval(targetInterval);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTargetIntervalShouldNotThrowException() {
        SELMSPlusDataVO clazz = new SELMSPlusDataVO();
        boolean isNotException = true;
        try {
            clazz.getTargetInterval();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCNSDReqIDWithStringCnsdReqIDShouldNotThrowException() {
        SELMSPlusDataVO clazz = new SELMSPlusDataVO();
        String cnsdReqID = "cnsdReqID";
        boolean isNotException = true;
        try {
            clazz.setCNSDReqID(cnsdReqID);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCNSDReqIDShouldNotThrowException() {
        SELMSPlusDataVO clazz = new SELMSPlusDataVO();
        boolean isNotException = true;
        try {
            clazz.getCNSDReqID();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTargetCompCdListWithHashMapStringStringTargetCompCdListShouldNotThrowException() {
        SELMSPlusDataVO clazz = new SELMSPlusDataVO();
        HashMap<String, String> targetCompCdList = mock(HashMap.class);
        boolean isNotException = true;
        try {
            clazz.setTargetCompCdList(targetCompCdList);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTargetCompCdListShouldNotThrowException() {
        SELMSPlusDataVO clazz = new SELMSPlusDataVO();
        boolean isNotException = true;
        try {
            clazz.getTargetCompCdList();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTargetCompCdWithStringTargetCompCdShouldNotThrowException() {
        SELMSPlusDataVO clazz = new SELMSPlusDataVO();
        String targetCompCd = "targetCompCd";
        boolean isNotException = true;
        try {
            clazz.setTargetCompCd(targetCompCd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTargetCompCdShouldNotThrowException() {
        SELMSPlusDataVO clazz = new SELMSPlusDataVO();
        boolean isNotException = true;
        try {
            clazz.getTargetCompCd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
