package com.sec.common.clmscom.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class OrgnzVOTest {

    @Test(timeout = 20000)
    public void testGetOrgnz_cdShouldNotThrowException() {
        OrgnzVO clazz = new OrgnzVO();
        boolean isNotException = true;
        try {
            clazz.getOrgnz_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_up_orgnz_cdShouldNotThrowException() {
        OrgnzVO clazz = new OrgnzVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_up_orgnz_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_up_orgnz_cdWithStringSrchUpOrgnzCdShouldNotThrowException() {
        OrgnzVO clazz = new OrgnzVO();
        String srch_up_orgnz_cd = "srch_up_orgnz_cd";
        boolean isNotException = true;
        try {
            clazz.setSrch_up_orgnz_cd(srch_up_orgnz_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOrgnz_cdWithStringOrgnzCdShouldNotThrowException() {
        OrgnzVO clazz = new OrgnzVO();
        String orgnz_cd = "orgnz_cd";
        boolean isNotException = true;
        try {
            clazz.setOrgnz_cd(orgnz_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOrgnz_nmShouldNotThrowException() {
        OrgnzVO clazz = new OrgnzVO();
        boolean isNotException = true;
        try {
            clazz.getOrgnz_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOrgnz_nmWithStringOrgnzNmShouldNotThrowException() {
        OrgnzVO clazz = new OrgnzVO();
        String orgnz_nm = "orgnz_nm";
        boolean isNotException = true;
        try {
            clazz.setOrgnz_nm(orgnz_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOrgnz_cdsShouldNotThrowException() {
        OrgnzVO clazz = new OrgnzVO();
        boolean isNotException = true;
        try {
            clazz.getOrgnz_cds();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOrgnz_cdsWithStringArrayOrgnzCdsShouldNotThrowException() {
        OrgnzVO clazz = new OrgnzVO();
        String[] orgnz_cds = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setOrgnz_cds(orgnz_cds);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
