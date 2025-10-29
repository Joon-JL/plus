package com.sec.clm.manage.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class GERPInfoVOTest {

    @Test(timeout = 20000)
    public void testSetCntrtIDWithStringCntrtIDShouldNotThrowException() {
        GERPInfoVO clazz = new GERPInfoVO();
        String cntrtID = "cntrtID";
        boolean isNotException = true;
        try {
            clazz.setCntrtID(cntrtID);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrtIDShouldNotThrowException() {
        GERPInfoVO clazz = new GERPInfoVO();
        boolean isNotException = true;
        try {
            clazz.getCntrtID();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGERPCodeTypeWithStringCodeTypeShouldNotThrowException() {
        GERPInfoVO clazz = new GERPInfoVO();
        String codeType = "codeType";
        boolean isNotException = true;
        try {
            clazz.setGERPCodeType(codeType);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGERPCodeTypeShouldNotThrowException() {
        GERPInfoVO clazz = new GERPInfoVO();
        boolean isNotException = true;
        try {
            clazz.getGERPCodeType();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCompCdWithStringCompCdShouldNotThrowException() {
        GERPInfoVO clazz = new GERPInfoVO();
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
        GERPInfoVO clazz = new GERPInfoVO();
        boolean isNotException = true;
        try {
            clazz.getCompCd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
