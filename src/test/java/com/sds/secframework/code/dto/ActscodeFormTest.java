package com.sds.secframework.code.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import java.math.BigDecimal;
import static org.mockito.Mockito.mock;

public class ActscodeFormTest {

    @Test(timeout = 20000)
    public void testGetGrp_cdShouldNotThrowException() {
        ActscodeForm clazz = new ActscodeForm();
        boolean isNotException = true;
        try {
            clazz.getGrp_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGrp_cdWithStringGrpCdShouldNotThrowException() {
        ActscodeForm clazz = new ActscodeForm();
        String grp_cd = "grp_cd";
        boolean isNotException = true;
        try {
            clazz.setGrp_cd(grp_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_sys_cdShouldNotThrowException() {
        ActscodeForm clazz = new ActscodeForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_sys_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_sys_cdWithStringSrchSysCdShouldNotThrowException() {
        ActscodeForm clazz = new ActscodeForm();
        String srch_sys_cd = "srch_sys_cd";
        boolean isNotException = true;
        try {
            clazz.setSrch_sys_cd(srch_sys_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCdShouldNotThrowException() {
        ActscodeForm clazz = new ActscodeForm();
        boolean isNotException = true;
        try {
            clazz.getCd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCdWithStringCdShouldNotThrowException() {
        ActscodeForm clazz = new ActscodeForm();
        String cd = "cd";
        boolean isNotException = true;
        try {
            clazz.setCd(cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCd_arrShouldNotThrowException() {
        ActscodeForm clazz = new ActscodeForm();
        boolean isNotException = true;
        try {
            clazz.getCd_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCd_arrWithStringArrayCdArrShouldNotThrowException() {
        ActscodeForm clazz = new ActscodeForm();
        String[] cd_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setCd_arr(cd_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCd_nm_arrShouldNotThrowException() {
        ActscodeForm clazz = new ActscodeForm();
        boolean isNotException = true;
        try {
            clazz.getCd_nm_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCd_nm_arrWithStringArrayCdNmArrShouldNotThrowException() {
        ActscodeForm clazz = new ActscodeForm();
        String[] cd_nm_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setCd_nm_arr(cd_nm_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCd_nm_eng_arrShouldNotThrowException() {
        ActscodeForm clazz = new ActscodeForm();
        boolean isNotException = true;
        try {
            clazz.getCd_nm_eng_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCd_nm_eng_arrWithStringArrayCdNmEngArrShouldNotThrowException() {
        ActscodeForm clazz = new ActscodeForm();
        String[] cd_nm_eng_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setCd_nm_eng_arr(cd_nm_eng_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
