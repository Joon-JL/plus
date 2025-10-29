package com.sec.clm.manage.dto;

import java.lang.String;
import org.junit.Test;
import java.util.List;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class ChooseContractTypeVOTest {

    @Test(timeout = 20000)
    public void testGetType_cdShouldNotThrowException() {
        ChooseContractTypeVO clazz = new ChooseContractTypeVO();
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
        ChooseContractTypeVO clazz = new ChooseContractTypeVO();
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
        ChooseContractTypeVO clazz = new ChooseContractTypeVO();
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
        ChooseContractTypeVO clazz = new ChooseContractTypeVO();
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
        ChooseContractTypeVO clazz = new ChooseContractTypeVO();
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
        ChooseContractTypeVO clazz = new ChooseContractTypeVO();
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
        ChooseContractTypeVO clazz = new ChooseContractTypeVO();
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
        ChooseContractTypeVO clazz = new ChooseContractTypeVO();
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
        ChooseContractTypeVO clazz = new ChooseContractTypeVO();
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
        ChooseContractTypeVO clazz = new ChooseContractTypeVO();
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
