package com.sec.clm.admin.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class FaqVOTest {

    @Test(timeout = 20000)
    public void testGetSeqnoShouldNotThrowException() {
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
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
        FaqVO clazz = new FaqVO();
        String mode = "mode";
        boolean isNotException = true;
        try {
            clazz.setMode(mode);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCont_enShouldNotThrowException() {
        FaqVO clazz = new FaqVO();
        boolean isNotException = true;
        try {
            clazz.getCont_en();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCont_enWithStringContEnShouldNotThrowException() {
        FaqVO clazz = new FaqVO();
        String cont_en = "cont_en";
        boolean isNotException = true;
        try {
            clazz.setCont_en(cont_en);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTitle_enShouldNotThrowException() {
        FaqVO clazz = new FaqVO();
        boolean isNotException = true;
        try {
            clazz.getTitle_en();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTitle_enWithStringTitleEnShouldNotThrowException() {
        FaqVO clazz = new FaqVO();
        String title_en = "title_en";
        boolean isNotException = true;
        try {
            clazz.setTitle_en(title_en);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
