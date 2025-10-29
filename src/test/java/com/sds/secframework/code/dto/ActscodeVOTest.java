package com.sds.secframework.code.dto;

import java.lang.String;
import org.junit.Test;
import java.math.BigDecimal;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class ActscodeVOTest {

    @Test(timeout = 20000)
    public void testGetGrp_cdShouldNotThrowException() {
        ActscodeVO clazz = new ActscodeVO();
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
        ActscodeVO clazz = new ActscodeVO();
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
        ActscodeVO clazz = new ActscodeVO();
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
        ActscodeVO clazz = new ActscodeVO();
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
        ActscodeVO clazz = new ActscodeVO();
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
        ActscodeVO clazz = new ActscodeVO();
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
        ActscodeVO clazz = new ActscodeVO();
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
        ActscodeVO clazz = new ActscodeVO();
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
        ActscodeVO clazz = new ActscodeVO();
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
        ActscodeVO clazz = new ActscodeVO();
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
        ActscodeVO clazz = new ActscodeVO();
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
        ActscodeVO clazz = new ActscodeVO();
        String[] cd_nm_eng_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setCd_nm_eng_arr(cd_nm_eng_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCd_nmShouldNotThrowException() {
        ActscodeVO clazz = new ActscodeVO();
        boolean isNotException = true;
        try {
            clazz.getCd_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCd_nmWithStringCdNmShouldNotThrowException() {
        ActscodeVO clazz = new ActscodeVO();
        String cd_nm = "cd_nm";
        boolean isNotException = true;
        try {
            clazz.setCd_nm(cd_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCd_nm_engShouldNotThrowException() {
        ActscodeVO clazz = new ActscodeVO();
        boolean isNotException = true;
        try {
            clazz.getCd_nm_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCd_nm_engWithStringCdNmEngShouldNotThrowException() {
        ActscodeVO clazz = new ActscodeVO();
        String cd_nm_eng = "cd_nm_eng";
        boolean isNotException = true;
        try {
            clazz.setCd_nm_eng(cd_nm_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCd_orderShouldNotThrowException() {
        ActscodeVO clazz = new ActscodeVO();
        boolean isNotException = true;
        try {
            clazz.getCd_order();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCd_orderWithIntCdOrderShouldNotThrowException() {
        ActscodeVO clazz = new ActscodeVO();
        int cd_order = 0;
        boolean isNotException = true;
        try {
            clazz.setCd_order(cd_order);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
