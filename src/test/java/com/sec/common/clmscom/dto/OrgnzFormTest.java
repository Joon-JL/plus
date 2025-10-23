package com.sec.common.clmscom.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class OrgnzFormTest {

    @Test(timeout = 20000)
    public void testGetOrgnz_cdShouldNotThrowException() {
        OrgnzForm clazz = new OrgnzForm();
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
        OrgnzForm clazz = new OrgnzForm();
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
        OrgnzForm clazz = new OrgnzForm();
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
        OrgnzForm clazz = new OrgnzForm();
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
        OrgnzForm clazz = new OrgnzForm();
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
        OrgnzForm clazz = new OrgnzForm();
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
        OrgnzForm clazz = new OrgnzForm();
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
        OrgnzForm clazz = new OrgnzForm();
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
