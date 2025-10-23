package com.sec.clm.rule.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class RegulationVOTest {

    @Test(timeout = 20000)
    public void testSetRule_noWithIntRuleNoShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        int rule_no = 0;
        boolean isNotException = true;
        try {
            clazz.setRule_no(rule_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUp_rule_noWithIntUpRuleNoShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        int up_rule_no = 0;
        boolean isNotException = true;
        try {
            clazz.setUp_rule_no(up_rule_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRule_depthWithIntRuleDepthShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        int rule_depth = 0;
        boolean isNotException = true;
        try {
            clazz.setRule_depth(rule_depth);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRule_srtWithIntRuleSrtShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        int rule_srt = 0;
        boolean isNotException = true;
        try {
            clazz.setRule_srt(rule_srt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDn_rule_exist_ynWithStringDnRuleExistYnShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        String dn_rule_exist_yn = "dn_rule_exist_yn";
        boolean isNotException = true;
        try {
            clazz.setDn_rule_exist_yn(dn_rule_exist_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRule_titleWithStringRuleTitleShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        String rule_title = "rule_title";
        boolean isNotException = true;
        try {
            clazz.setRule_title(rule_title);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRule_title_enWithStringRuleTitleEnShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        String rule_title_en = "rule_title_en";
        boolean isNotException = true;
        try {
            clazz.setRule_title_en(rule_title_en);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRule_contWithStringRuleContShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        String rule_cont = "rule_cont";
        boolean isNotException = true;
        try {
            clazz.setRule_cont(rule_cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRule_cont_enWithStringRuleContEnShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        String rule_cont_en = "rule_cont_en";
        boolean isNotException = true;
        try {
            clazz.setRule_cont_en(rule_cont_en);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_dtWithStringRegDtShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
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
    public void testSetReg_idWithStringRegIdShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
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
    public void testSetReg_nmWithStringRegNmShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
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
    public void testSetMod_dtWithStringModDtShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
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
    public void testSetMod_idWithStringModIdShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
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
    public void testSetMod_nmWithStringModNmShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
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
    public void testSetDel_ynWithStringDelYnShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
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
    public void testSetDel_dtWithStringDelDtShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
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
    public void testSetDel_idWithStringDelIdShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
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
    public void testSetDel_nmWithStringDelNmShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
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
    public void testGetRule_noShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        boolean isNotException = true;
        try {
            clazz.getRule_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUp_rule_noShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        boolean isNotException = true;
        try {
            clazz.getUp_rule_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRule_depthShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        boolean isNotException = true;
        try {
            clazz.getRule_depth();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRule_srtShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        boolean isNotException = true;
        try {
            clazz.getRule_srt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDn_rule_exist_ynShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        boolean isNotException = true;
        try {
            clazz.getDn_rule_exist_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRule_titleShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        boolean isNotException = true;
        try {
            clazz.getRule_title();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRule_title_enShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        boolean isNotException = true;
        try {
            clazz.getRule_title_en();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRule_contShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        boolean isNotException = true;
        try {
            clazz.getRule_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRule_cont_enShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        boolean isNotException = true;
        try {
            clazz.getRule_cont_en();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dtShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        boolean isNotException = true;
        try {
            clazz.getReg_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_idShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        boolean isNotException = true;
        try {
            clazz.getReg_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_nmShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        boolean isNotException = true;
        try {
            clazz.getReg_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMod_dtShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        boolean isNotException = true;
        try {
            clazz.getMod_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMod_idShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        boolean isNotException = true;
        try {
            clazz.getMod_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMod_nmShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        boolean isNotException = true;
        try {
            clazz.getMod_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_ynShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        boolean isNotException = true;
        try {
            clazz.getDel_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_dtShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        boolean isNotException = true;
        try {
            clazz.getDel_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_idShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        boolean isNotException = true;
        try {
            clazz.getDel_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_nmShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        boolean isNotException = true;
        try {
            clazz.getDel_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        boolean isNotException = true;
        try {
            clazz.getCnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntWithIntCntShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        int cnt = 0;
        boolean isNotException = true;
        try {
            clazz.setCnt(cnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUp_rule_no_rnShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        boolean isNotException = true;
        try {
            clazz.getUp_rule_no_rn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUp_rule_no_rnWithIntUpRuleNoRnShouldNotThrowException() {
        RegulationVO clazz = new RegulationVO();
        int up_rule_no_rn = 0;
        boolean isNotException = true;
        try {
            clazz.setUp_rule_no_rn(up_rule_no_rn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
