package com.sec.clm.admin.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class SubjectFormTest {

    @Test(timeout = 20000)
    public void testGetAuth_insertShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        boolean isNotException = true;
        try {
            clazz.getAuth_insert();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_insertWithBooleanAuthInsertShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        boolean auth_insert = false;
        boolean isNotException = true;
        try {
            clazz.setAuth_insert(auth_insert);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAuth_modifyShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        boolean isNotException = true;
        try {
            clazz.getAuth_modify();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_modifyWithBooleanAuthModifyShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        boolean auth_modify = false;
        boolean isNotException = true;
        try {
            clazz.setAuth_modify(auth_modify);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testIsAuth_deleteShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        boolean isNotException = true;
        try {
            clazz.isAuth_delete();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_deleteWithBooleanAuthDeleteShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        boolean auth_delete = false;
        boolean isNotException = true;
        try {
            clazz.setAuth_delete(auth_delete);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_dimensionShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_dimension();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_dimensionWithStringSrchDimensionShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        String srch_dimension = "srch_dimension";
        boolean isNotException = true;
        try {
            clazz.setSrch_dimension(srch_dimension);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStart_indexShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        boolean isNotException = true;
        try {
            clazz.getStart_index();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetStart_indexWithStringStartIndexShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        String start_index = "start_index";
        boolean isNotException = true;
        try {
            clazz.setStart_index(start_index);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEnd_indexShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        boolean isNotException = true;
        try {
            clazz.getEnd_index();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEnd_indexWithStringEndIndexShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        String end_index = "end_index";
        boolean isNotException = true;
        try {
            clazz.setEnd_index(end_index);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCurPageShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        boolean isNotException = true;
        try {
            clazz.getCurPage();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCurPageWithStringCurPageShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        String curPage = "curPage";
        boolean isNotException = true;
        try {
            clazz.setCurPage(curPage);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRow_cntShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        boolean isNotException = true;
        try {
            clazz.getRow_cnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRow_cntWithIntRowCntShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        int row_cnt = 0;
        boolean isNotException = true;
        try {
            clazz.setRow_cnt(row_cnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDimension_cdShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        boolean isNotException = true;
        try {
            clazz.getDimension_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDimension_cdWithStringDimensionCdShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        String dimension_cd = "dimension_cd";
        boolean isNotException = true;
        try {
            clazz.setDimension_cd(dimension_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetType_cdShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
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
        SubjectForm clazz = new SubjectForm();
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
    public void testGetDimension_nmShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        boolean isNotException = true;
        try {
            clazz.getDimension_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDimension_nmWithStringDimensionNmShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        String dimension_nm = "dimension_nm";
        boolean isNotException = true;
        try {
            clazz.setDimension_nm(dimension_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCd_nmShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
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
        SubjectForm clazz = new SubjectForm();
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
        SubjectForm clazz = new SubjectForm();
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
        SubjectForm clazz = new SubjectForm();
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
    public void testGetCd_nm_engShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
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
        SubjectForm clazz = new SubjectForm();
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
    public void testGetCd_nm_abbr_engShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        boolean isNotException = true;
        try {
            clazz.getCd_nm_abbr_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCd_nm_abbr_engWithStringCdNmAbbrEngShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        String cd_nm_abbr_eng = "cd_nm_abbr_eng";
        boolean isNotException = true;
        try {
            clazz.setCd_nm_abbr_eng(cd_nm_abbr_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPrnt_srtShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        boolean isNotException = true;
        try {
            clazz.getPrnt_srt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPrnt_srtWithStringPrntSrtShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        String prnt_srt = "prnt_srt";
        boolean isNotException = true;
        try {
            clazz.setPrnt_srt(prnt_srt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCd_explShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        boolean isNotException = true;
        try {
            clazz.getCd_expl();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCd_explWithStringCdExplShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        String cd_expl = "cd_expl";
        boolean isNotException = true;
        try {
            clazz.setCd_expl(cd_expl);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUse_ynShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
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
        SubjectForm clazz = new SubjectForm();
        String use_yn = "use_yn";
        boolean isNotException = true;
        try {
            clazz.setUse_yn(use_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dtShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        boolean isNotException = true;
        try {
            clazz.getReg_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_dtWithStringRegDtShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        String reg_dt = "reg_dt";
        boolean isNotException = true;
        try {
            clazz.setReg_dt(reg_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_idShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        boolean isNotException = true;
        try {
            clazz.getReg_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_idWithStringRegIdShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        String reg_id = "reg_id";
        boolean isNotException = true;
        try {
            clazz.setReg_id(reg_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_nmShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        boolean isNotException = true;
        try {
            clazz.getReg_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_nmWithStringRegNmShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        String reg_nm = "reg_nm";
        boolean isNotException = true;
        try {
            clazz.setReg_nm(reg_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dept_nmShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        boolean isNotException = true;
        try {
            clazz.getReg_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_dept_nmWithStringRegDeptNmShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        String reg_dept_nm = "reg_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setReg_dept_nm(reg_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMod_dtShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        boolean isNotException = true;
        try {
            clazz.getMod_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_dtWithStringModDtShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        String mod_dt = "mod_dt";
        boolean isNotException = true;
        try {
            clazz.setMod_dt(mod_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMod_idShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        boolean isNotException = true;
        try {
            clazz.getMod_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_idWithStringModIdShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        String mod_id = "mod_id";
        boolean isNotException = true;
        try {
            clazz.setMod_id(mod_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMod_nmShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        boolean isNotException = true;
        try {
            clazz.getMod_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_nmWithStringModNmShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        String mod_nm = "mod_nm";
        boolean isNotException = true;
        try {
            clazz.setMod_nm(mod_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMod_dept_nmShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        boolean isNotException = true;
        try {
            clazz.getMod_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_dept_nmWithStringModDeptNmShouldNotThrowException() {
        SubjectForm clazz = new SubjectForm();
        String mod_dept_nm = "mod_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setMod_dept_nm(mod_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
