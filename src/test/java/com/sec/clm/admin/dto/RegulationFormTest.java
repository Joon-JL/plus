package com.sec.clm.admin.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class RegulationFormTest {

    @Test(timeout = 20000)
    public void testGetSrch_one_rule_depthShouldNotThrowException() {
        RegulationForm clazz = new RegulationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_one_rule_depth();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_one_rule_depthWithStringSrchOneRuleDepthShouldNotThrowException() {
        RegulationForm clazz = new RegulationForm();
        String srch_one_rule_depth = "srch_one_rule_depth";
        boolean isNotException = true;
        try {
            clazz.setSrch_one_rule_depth(srch_one_rule_depth);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_two_rule_depthShouldNotThrowException() {
        RegulationForm clazz = new RegulationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_two_rule_depth();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_two_rule_depthWithStringSrchTwoRuleDepthShouldNotThrowException() {
        RegulationForm clazz = new RegulationForm();
        String srch_two_rule_depth = "srch_two_rule_depth";
        boolean isNotException = true;
        try {
            clazz.setSrch_two_rule_depth(srch_two_rule_depth);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_contShouldNotThrowException() {
        RegulationForm clazz = new RegulationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_contWithStringSrchContShouldNotThrowException() {
        RegulationForm clazz = new RegulationForm();
        String srch_cont = "srch_cont";
        boolean isNotException = true;
        try {
            clazz.setSrch_cont(srch_cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_rule_choiceShouldNotThrowException() {
        RegulationForm clazz = new RegulationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_rule_choice();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_rule_choiceWithStringSrchRuleChoiceShouldNotThrowException() {
        RegulationForm clazz = new RegulationForm();
        String srch_rule_choice = "srch_rule_choice";
        boolean isNotException = true;
        try {
            clazz.setSrch_rule_choice(srch_rule_choice);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOne_rule_title_inputShouldNotThrowException() {
        RegulationForm clazz = new RegulationForm();
        boolean isNotException = true;
        try {
            clazz.getOne_rule_title_input();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOne_rule_title_inputWithStringOneRuleTitleInputShouldNotThrowException() {
        RegulationForm clazz = new RegulationForm();
        String one_rule_title_input = "one_rule_title_input";
        boolean isNotException = true;
        try {
            clazz.setOne_rule_title_input(one_rule_title_input);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRule_noWithIntRuleNoShouldNotThrowException() {
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
        boolean isNotException = true;
        try {
            clazz.getRule_title();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRule_contShouldNotThrowException() {
        RegulationForm clazz = new RegulationForm();
        boolean isNotException = true;
        try {
            clazz.getRule_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dtShouldNotThrowException() {
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
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
        RegulationForm clazz = new RegulationForm();
        boolean isNotException = true;
        try {
            clazz.getDel_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOne_titleShouldNotThrowException() {
        RegulationForm clazz = new RegulationForm();
        boolean isNotException = true;
        try {
            clazz.getOne_title();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOne_titleWithStringOneTitleShouldNotThrowException() {
        RegulationForm clazz = new RegulationForm();
        String one_title = "one_title";
        boolean isNotException = true;
        try {
            clazz.setOne_title(one_title);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOne_contShouldNotThrowException() {
        RegulationForm clazz = new RegulationForm();
        boolean isNotException = true;
        try {
            clazz.getOne_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOne_contWithStringOneContShouldNotThrowException() {
        RegulationForm clazz = new RegulationForm();
        String one_cont = "one_cont";
        boolean isNotException = true;
        try {
            clazz.setOne_cont(one_cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTwo_titleShouldNotThrowException() {
        RegulationForm clazz = new RegulationForm();
        boolean isNotException = true;
        try {
            clazz.getTwo_title();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTwo_titleWithStringTwoTitleShouldNotThrowException() {
        RegulationForm clazz = new RegulationForm();
        String two_title = "two_title";
        boolean isNotException = true;
        try {
            clazz.setTwo_title(two_title);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTwo_contShouldNotThrowException() {
        RegulationForm clazz = new RegulationForm();
        boolean isNotException = true;
        try {
            clazz.getTwo_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTwo_contWithStringTwoContShouldNotThrowException() {
        RegulationForm clazz = new RegulationForm();
        String two_cont = "two_cont";
        boolean isNotException = true;
        try {
            clazz.setTwo_cont(two_cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRule_title_enShouldNotThrowException() {
        RegulationForm clazz = new RegulationForm();
        boolean isNotException = true;
        try {
            clazz.getRule_title_en();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRule_cont_enShouldNotThrowException() {
        RegulationForm clazz = new RegulationForm();
        boolean isNotException = true;
        try {
            clazz.getRule_cont_en();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
