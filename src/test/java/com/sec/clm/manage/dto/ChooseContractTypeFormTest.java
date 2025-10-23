package com.sec.clm.manage.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import java.util.List;
import static org.mockito.Mockito.mock;

public class ChooseContractTypeFormTest {

    @Test(timeout = 20000)
    public void testGetForword_fromShouldNotThrowException() {
        ChooseContractTypeForm clazz = new ChooseContractTypeForm();
        boolean isNotException = true;
        try {
            clazz.getForword_from();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetForword_fromWithStringForwordFromShouldNotThrowException() {
        ChooseContractTypeForm clazz = new ChooseContractTypeForm();
        String forword_from = "forword_from";
        boolean isNotException = true;
        try {
            clazz.setForword_from(forword_from);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetType_cdShouldNotThrowException() {
        ChooseContractTypeForm clazz = new ChooseContractTypeForm();
        boolean isNotException = true;
        try {
            clazz.getType_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetType_cdWithStringTypeCdShouldNotThrowException() {
        ChooseContractTypeForm clazz = new ChooseContractTypeForm();
        String type_cd = "type_cd";
        boolean isNotException = true;
        try {
            clazz.setType_cd(type_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUp_type_cdShouldNotThrowException() {
        ChooseContractTypeForm clazz = new ChooseContractTypeForm();
        boolean isNotException = true;
        try {
            clazz.getUp_type_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUp_type_cdWithStringUpTypeCdShouldNotThrowException() {
        ChooseContractTypeForm clazz = new ChooseContractTypeForm();
        String up_type_cd = "up_type_cd";
        boolean isNotException = true;
        try {
            clazz.setUp_type_cd(up_type_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCd_nmShouldNotThrowException() {
        ChooseContractTypeForm clazz = new ChooseContractTypeForm();
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
        ChooseContractTypeForm clazz = new ChooseContractTypeForm();
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
    public void testGetCd_nm_abbrShouldNotThrowException() {
        ChooseContractTypeForm clazz = new ChooseContractTypeForm();
        boolean isNotException = true;
        try {
            clazz.getCd_nm_abbr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCd_nm_abbrWithStringCdNmAbbrShouldNotThrowException() {
        ChooseContractTypeForm clazz = new ChooseContractTypeForm();
        String cd_nm_abbr = "cd_nm_abbr";
        boolean isNotException = true;
        try {
            clazz.setCd_nm_abbr(cd_nm_abbr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUse_ynShouldNotThrowException() {
        ChooseContractTypeForm clazz = new ChooseContractTypeForm();
        boolean isNotException = true;
        try {
            clazz.getUse_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUse_ynWithStringUseYnShouldNotThrowException() {
        ChooseContractTypeForm clazz = new ChooseContractTypeForm();
        String use_yn = "use_yn";
        boolean isNotException = true;
        try {
            clazz.setUse_yn(use_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
