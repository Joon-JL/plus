package com.sec.clm.admin.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class FaqFormTest {

    @Test(timeout = 20000)
    public void testGetSeqnoShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        boolean isNotException = true;
        try {
            clazz.getSeqno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOrgnz_cdShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        boolean isNotException = true;
        try {
            clazz.getOrgnz_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOrgnz_cdWithStringOrgnzCdShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
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
    public void testGetOrgnz_cdsShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
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
        FaqForm clazz = new FaqForm();
        String[] orgnz_cds = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setOrgnz_cds(orgnz_cds);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSec_ynShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        boolean isNotException = true;
        try {
            clazz.getSec_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSec_ynWithStringSecYnShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        String sec_yn = "sec_yn";
        boolean isNotException = true;
        try {
            clazz.setSec_yn(sec_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeqnoWithIntSeqnoShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        int seqno = 0;
        boolean isNotException = true;
        try {
            clazz.setSeqno(seqno);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAnnce_gbnShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        boolean isNotException = true;
        try {
            clazz.getAnnce_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAnnce_gbnWithStringAnnceGbnShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        String annce_gbn = "annce_gbn";
        boolean isNotException = true;
        try {
            clazz.setAnnce_gbn(annce_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTitleShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        boolean isNotException = true;
        try {
            clazz.getTitle();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTitleWithStringTitleShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        String title = "title";
        boolean isNotException = true;
        try {
            clazz.setTitle(title);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCont_typeShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        boolean isNotException = true;
        try {
            clazz.getCont_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCont_typeWithStringContTypeShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        String cont_type = "cont_type";
        boolean isNotException = true;
        try {
            clazz.setCont_type(cont_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetContShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        boolean isNotException = true;
        try {
            clazz.getCont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetContWithStringContShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        String cont = "cont";
        boolean isNotException = true;
        try {
            clazz.setCont(cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRdcntShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        boolean isNotException = true;
        try {
            clazz.getRdcnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRdcntWithIntRdcntShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        int rdcnt = 0;
        boolean isNotException = true;
        try {
            clazz.setRdcnt(rdcnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRd_trgt1ShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        boolean isNotException = true;
        try {
            clazz.getRd_trgt1();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRd_trgt1WithStringRdTrgt1ShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        String rd_trgt1 = "rd_trgt1";
        boolean isNotException = true;
        try {
            clazz.setRd_trgt1(rd_trgt1);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRd_trgt2ShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        boolean isNotException = true;
        try {
            clazz.getRd_trgt2();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRd_trgt2WithStringRdTrgt2ShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        String rd_trgt2 = "rd_trgt2";
        boolean isNotException = true;
        try {
            clazz.setRd_trgt2(rd_trgt2);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAnnce_startdayShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        boolean isNotException = true;
        try {
            clazz.getAnnce_startday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAnnce_startdayWithStringAnnceStartdayShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        String annce_startday = "annce_startday";
        boolean isNotException = true;
        try {
            clazz.setAnnce_startday(annce_startday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAnnce_enddayShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        boolean isNotException = true;
        try {
            clazz.getAnnce_endday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAnnce_enddayWithStringAnnceEnddayShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        String annce_endday = "annce_endday";
        boolean isNotException = true;
        try {
            clazz.setAnnce_endday(annce_endday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dtShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
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
        FaqForm clazz = new FaqForm();
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
        FaqForm clazz = new FaqForm();
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
        FaqForm clazz = new FaqForm();
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
        FaqForm clazz = new FaqForm();
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
        FaqForm clazz = new FaqForm();
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
    public void testGetRegman_jikgup_nmShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        boolean isNotException = true;
        try {
            clazz.getRegman_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRegman_jikgup_nmWithStringRegmanJikgupNmShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        String regman_jikgup_nm = "regman_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setRegman_jikgup_nm(regman_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dept_nmShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
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
        FaqForm clazz = new FaqForm();
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
        FaqForm clazz = new FaqForm();
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
        FaqForm clazz = new FaqForm();
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
        FaqForm clazz = new FaqForm();
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
        FaqForm clazz = new FaqForm();
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
        FaqForm clazz = new FaqForm();
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
        FaqForm clazz = new FaqForm();
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
    public void testGetDel_ynShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        boolean isNotException = true;
        try {
            clazz.getDel_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_ynWithStringDelYnShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        String del_yn = "del_yn";
        boolean isNotException = true;
        try {
            clazz.setDel_yn(del_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_dtShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        boolean isNotException = true;
        try {
            clazz.getDel_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_dtWithStringDelDtShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        String del_dt = "del_dt";
        boolean isNotException = true;
        try {
            clazz.setDel_dt(del_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_idShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        boolean isNotException = true;
        try {
            clazz.getDel_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_idWithStringDelIdShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        String del_id = "del_id";
        boolean isNotException = true;
        try {
            clazz.setDel_id(del_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_nmShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        boolean isNotException = true;
        try {
            clazz.getDel_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_nmWithStringDelNmShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        String del_nm = "del_nm";
        boolean isNotException = true;
        try {
            clazz.setDel_nm(del_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_keywordShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_keyword();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_keywordWithStringSrchKeywordShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        String srch_keyword = "srch_keyword";
        boolean isNotException = true;
        try {
            clazz.setSrch_keyword(srch_keyword);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_keyword_contentShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_keyword_content();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_keyword_contentWithStringSrchKeywordContentShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        String srch_keyword_content = "srch_keyword_content";
        boolean isNotException = true;
        try {
            clazz.setSrch_keyword_content(srch_keyword_content);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_cont_typeShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_cont_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_cont_typeWithStringSrchContTypeShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        String srch_cont_type = "srch_cont_type";
        boolean isNotException = true;
        try {
            clazz.setSrch_cont_type(srch_cont_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetModeShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        boolean isNotException = true;
        try {
            clazz.getMode();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetModeWithStringModeShouldNotThrowException() {
        FaqForm clazz = new FaqForm();
        String mode = "mode";
        boolean isNotException = true;
        try {
            clazz.setMode(mode);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
