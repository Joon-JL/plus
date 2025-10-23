package com.sec.clm.admin.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class DecisionFormTest {

    @Test(timeout = 20000)
    public void testSetSeqnoWithIntSeqnoShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
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
    public void testSetOperdiv_cdWithStringOperdivCdShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        String operdiv_cd = "operdiv_cd";
        boolean isNotException = true;
        try {
            clazz.setOperdiv_cd(operdiv_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBiz_clsfcnWithStringBizClsfcnShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        String biz_clsfcn = "biz_clsfcn";
        boolean isNotException = true;
        try {
            clazz.setBiz_clsfcn(biz_clsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDepth_clsfcnWithStringDepthClsfcnShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        String depth_clsfcn = "depth_clsfcn";
        boolean isNotException = true;
        try {
            clazz.setDepth_clsfcn(depth_clsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnclsnpurps_bigclsfcnWithStringCnclsnpurpsBigclsfcnShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        String cnclsnpurps_bigclsfcn = "cnclsnpurps_bigclsfcn";
        boolean isNotException = true;
        try {
            clazz.setCnclsnpurps_bigclsfcn(cnclsnpurps_bigclsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnclsnpurps_midclsfcnWithStringCnclsnpurpsMidclsfcnShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        String cnclsnpurps_midclsfcn = "cnclsnpurps_midclsfcn";
        boolean isNotException = true;
        try {
            clazz.setCnclsnpurps_midclsfcn(cnclsnpurps_midclsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetApprvl_clsfcnWithStringApprvlClsfcnShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        String apprvl_clsfcn = "apprvl_clsfcn";
        boolean isNotException = true;
        try {
            clazz.setApprvl_clsfcn(apprvl_clsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDcd_crtrnWithStringDcdCrtrnShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        String dcd_crtrn = "dcd_crtrn";
        boolean isNotException = true;
        try {
            clazz.setDcd_crtrn(dcd_crtrn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDcd_authmanWithStringDcdAuthmanShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        String dcd_authman = "dcd_authman";
        boolean isNotException = true;
        try {
            clazz.setDcd_authman(dcd_authman);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAgree_funcstaffWithStringAgreeFuncstaffShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        String agree_funcstaff = "agree_funcstaff";
        boolean isNotException = true;
        try {
            clazz.setAgree_funcstaff(agree_funcstaff);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAgree_funcstaff_nmWithStringAgreeFuncstaffNmShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        String agree_funcstaff_nm = "agree_funcstaff_nm";
        boolean isNotException = true;
        try {
            clazz.setAgree_funcstaff_nm(agree_funcstaff_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSurtteam_agree_trgtWithStringSurtteamAgreeTrgtShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        String surtteam_agree_trgt = "surtteam_agree_trgt";
        boolean isNotException = true;
        try {
            clazz.setSurtteam_agree_trgt(surtteam_agree_trgt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMngtsurtoffice_agree_ynWithStringMngtsurtofficeAgreeYnShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        String mngtsurtoffice_agree_yn = "mngtsurtoffice_agree_yn";
        boolean isNotException = true;
        try {
            clazz.setMngtsurtoffice_agree_yn(mngtsurtoffice_agree_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNoteWithStringNoteShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        String note = "note";
        boolean isNotException = true;
        try {
            clazz.setNote(note);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRule_contWithStringRuleContShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
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
    public void testSetReg_dtWithStringRegDtShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
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
        DecisionForm clazz = new DecisionForm();
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
        DecisionForm clazz = new DecisionForm();
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
        DecisionForm clazz = new DecisionForm();
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
        DecisionForm clazz = new DecisionForm();
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
        DecisionForm clazz = new DecisionForm();
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
        DecisionForm clazz = new DecisionForm();
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
        DecisionForm clazz = new DecisionForm();
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
        DecisionForm clazz = new DecisionForm();
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
        DecisionForm clazz = new DecisionForm();
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
    public void testGetSeqnoShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        boolean isNotException = true;
        try {
            clazz.getSeqno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOperdiv_cdShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        boolean isNotException = true;
        try {
            clazz.getOperdiv_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBiz_clsfcnShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        boolean isNotException = true;
        try {
            clazz.getBiz_clsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDepth_clsfcnShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        boolean isNotException = true;
        try {
            clazz.getDepth_clsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnclsnpurps_bigclsfcnShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        boolean isNotException = true;
        try {
            clazz.getCnclsnpurps_bigclsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnclsnpurps_midclsfcnShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        boolean isNotException = true;
        try {
            clazz.getCnclsnpurps_midclsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetApprvl_clsfcnShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        boolean isNotException = true;
        try {
            clazz.getApprvl_clsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDcd_crtrnShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        boolean isNotException = true;
        try {
            clazz.getDcd_crtrn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDcd_authmanShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        boolean isNotException = true;
        try {
            clazz.getDcd_authman();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAgree_funcstaffShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        boolean isNotException = true;
        try {
            clazz.getAgree_funcstaff();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAgree_funcstaff_nmShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        boolean isNotException = true;
        try {
            clazz.getAgree_funcstaff_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSurtteam_agree_trgtShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        boolean isNotException = true;
        try {
            clazz.getSurtteam_agree_trgt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMngtsurtoffice_agree_ynShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        boolean isNotException = true;
        try {
            clazz.getMngtsurtoffice_agree_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNoteShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
        boolean isNotException = true;
        try {
            clazz.getNote();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRule_contShouldNotThrowException() {
        DecisionForm clazz = new DecisionForm();
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
        DecisionForm clazz = new DecisionForm();
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
        DecisionForm clazz = new DecisionForm();
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
        DecisionForm clazz = new DecisionForm();
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
        DecisionForm clazz = new DecisionForm();
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
        DecisionForm clazz = new DecisionForm();
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
        DecisionForm clazz = new DecisionForm();
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
        DecisionForm clazz = new DecisionForm();
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
        DecisionForm clazz = new DecisionForm();
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
        DecisionForm clazz = new DecisionForm();
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
        DecisionForm clazz = new DecisionForm();
        boolean isNotException = true;
        try {
            clazz.getDel_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
