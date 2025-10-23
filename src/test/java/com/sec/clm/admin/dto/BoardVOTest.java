package com.sec.clm.admin.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class BoardVOTest {

    @Test(timeout = 20000)
    public void testGetSeqnoShouldNotThrowException() {
        BoardVO clazz = new BoardVO();
        boolean isNotException = true;
        try {
            clazz.getSeqno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetManager_ynShouldNotThrowException() {
        BoardVO clazz = new BoardVO();
        boolean isNotException = true;
        try {
            clazz.getManager_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetManager_ynWithStringManagerYnShouldNotThrowException() {
        BoardVO clazz = new BoardVO();
        String manager_yn = "manager_yn";
        boolean isNotException = true;
        try {
            clazz.setManager_yn(manager_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOrgnz_cdShouldNotThrowException() {
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
    public void testGetSrch_start_dtShouldNotThrowException() {
        BoardVO clazz = new BoardVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_start_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_start_dtWithStringSrchStartDtShouldNotThrowException() {
        BoardVO clazz = new BoardVO();
        String srch_start_dt = "srch_start_dt";
        boolean isNotException = true;
        try {
            clazz.setSrch_start_dt(srch_start_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_end_dtShouldNotThrowException() {
        BoardVO clazz = new BoardVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_end_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_end_dtWithStringSrchEndDtShouldNotThrowException() {
        BoardVO clazz = new BoardVO();
        String srch_end_dt = "srch_end_dt";
        boolean isNotException = true;
        try {
            clazz.setSrch_end_dt(srch_end_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_reg_operdivShouldNotThrowException() {
        BoardVO clazz = new BoardVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_reg_operdiv();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_reg_operdivWithStringSrchRegOperdivShouldNotThrowException() {
        BoardVO clazz = new BoardVO();
        String srch_reg_operdiv = "srch_reg_operdiv";
        boolean isNotException = true;
        try {
            clazz.setSrch_reg_operdiv(srch_reg_operdiv);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_keywordShouldNotThrowException() {
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
    public void testGetModeShouldNotThrowException() {
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
    public void testGetSel_langShouldNotThrowException() {
        BoardVO clazz = new BoardVO();
        boolean isNotException = true;
        try {
            clazz.getSel_lang();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSel_langWithStringSelLangShouldNotThrowException() {
        BoardVO clazz = new BoardVO();
        String sel_lang = "sel_lang";
        boolean isNotException = true;
        try {
            clazz.setSel_lang(sel_lang);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCont_enShouldNotThrowException() {
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
        BoardVO clazz = new BoardVO();
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
