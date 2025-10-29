package com.sec.clm.draft.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class NationVOTest {

    @Test(timeout = 20000)
    public void testGetEmailShouldNotThrowException() {
        NationVO clazz = new NationVO();
        boolean isNotException = true;
        try {
            clazz.getEmail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEmailWithStringEmailShouldNotThrowException() {
        NationVO clazz = new NationVO();
        String email = "email";
        boolean isNotException = true;
        try {
            clazz.setEmail(email);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGuShouldNotThrowException() {
        NationVO clazz = new NationVO();
        boolean isNotException = true;
        try {
            clazz.getGu();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGuWithStringGuShouldNotThrowException() {
        NationVO clazz = new NationVO();
        String gu = "gu";
        boolean isNotException = true;
        try {
            clazz.setGu(gu);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCdShouldNotThrowException() {
        NationVO clazz = new NationVO();
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
        NationVO clazz = new NationVO();
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
    public void testGetCd_nmShouldNotThrowException() {
        NationVO clazz = new NationVO();
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
        NationVO clazz = new NationVO();
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
    public void testGetCd_abbr_nmShouldNotThrowException() {
        NationVO clazz = new NationVO();
        boolean isNotException = true;
        try {
            clazz.getCd_abbr_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCd_abbr_nmWithStringCdAbbrNmShouldNotThrowException() {
        NationVO clazz = new NationVO();
        String cd_abbr_nm = "cd_abbr_nm";
        boolean isNotException = true;
        try {
            clazz.setCd_abbr_nm(cd_abbr_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCd_nm_engShouldNotThrowException() {
        NationVO clazz = new NationVO();
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
        NationVO clazz = new NationVO();
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
    public void testGetCd_abbr_nm_engShouldNotThrowException() {
        NationVO clazz = new NationVO();
        boolean isNotException = true;
        try {
            clazz.getCd_abbr_nm_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCd_abbr_nm_engWithStringCdAbbrNmEngShouldNotThrowException() {
        NationVO clazz = new NationVO();
        String cd_abbr_nm_eng = "cd_abbr_nm_eng";
        boolean isNotException = true;
        try {
            clazz.setCd_abbr_nm_eng(cd_abbr_nm_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
