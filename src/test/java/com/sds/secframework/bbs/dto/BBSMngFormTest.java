package com.sds.secframework.bbs.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class BBSMngFormTest {

    @Test(timeout = 20000)
    public void testGetBbs_idShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        boolean isNotException = true;
        try {
            clazz.getBbs_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBbs_idWithStringBbsIdShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        String bbs_id = "bbs_id";
        boolean isNotException = true;
        try {
            clazz.setBbs_id(bbs_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBbs_nmShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        boolean isNotException = true;
        try {
            clazz.getBbs_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBbs_nmWithStringBbsNmShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        String bbs_nm = "bbs_nm";
        boolean isNotException = true;
        try {
            clazz.setBbs_nm(bbs_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBbs_nm_engShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        boolean isNotException = true;
        try {
            clazz.getBbs_nm_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBbs_nm_engWithStringBbsNmEngShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        String bbs_nm_eng = "bbs_nm_eng";
        boolean isNotException = true;
        try {
            clazz.setBbs_nm_eng(bbs_nm_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCommentsShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        boolean isNotException = true;
        try {
            clazz.getComments();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCommentsWithStringCommentsShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        String comments = "comments";
        boolean isNotException = true;
        try {
            clazz.setComments(comments);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNew_hold_termShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        boolean isNotException = true;
        try {
            clazz.getNew_hold_term();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNew_hold_termWithIntNewHoldTermShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        int new_hold_term = 0;
        boolean isNotException = true;
        try {
            clazz.setNew_hold_term(new_hold_term);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReply_ynShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        boolean isNotException = true;
        try {
            clazz.getReply_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReply_ynWithStringReplyYnShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        String reply_yn = "reply_yn";
        boolean isNotException = true;
        try {
            clazz.setReply_yn(reply_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAppend_ynShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        boolean isNotException = true;
        try {
            clazz.getAppend_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAppend_ynWithStringAppendYnShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        String append_yn = "append_yn";
        boolean isNotException = true;
        try {
            clazz.setAppend_yn(append_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_ynShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        boolean isNotException = true;
        try {
            clazz.getFile_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFile_ynWithStringFileYnShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        String file_yn = "file_yn";
        boolean isNotException = true;
        try {
            clazz.setFile_yn(file_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUrgency_ynShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        boolean isNotException = true;
        try {
            clazz.getUrgency_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUrgency_ynWithStringUrgencyYnShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        String urgency_yn = "urgency_yn";
        boolean isNotException = true;
        try {
            clazz.setUrgency_yn(urgency_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRecommend_ynShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        boolean isNotException = true;
        try {
            clazz.getRecommend_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRecommend_ynWithStringRecommendYnShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        String recommend_yn = "recommend_yn";
        boolean isNotException = true;
        try {
            clazz.setRecommend_yn(recommend_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHold_term_ynShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        boolean isNotException = true;
        try {
            clazz.getHold_term_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHold_term_ynWithStringHoldTermYnShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        String hold_term_yn = "hold_term_yn";
        boolean isNotException = true;
        try {
            clazz.setHold_term_yn(hold_term_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUse_ynShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
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
        BBSMngForm clazz = new BBSMngForm();
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
    public void testGetReg_idShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
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
        BBSMngForm clazz = new BBSMngForm();
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
    public void testGetReg_dtShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
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
        BBSMngForm clazz = new BBSMngForm();
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
    public void testGetMod_idShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
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
        BBSMngForm clazz = new BBSMngForm();
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
    public void testGetMod_dtShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
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
        BBSMngForm clazz = new BBSMngForm();
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
    public void testGetSrch_bbs_nmShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_bbs_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_bbs_nmWithStringSrchBbsNmShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        String srch_bbs_nm = "srch_bbs_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_bbs_nm(srch_bbs_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_use_ynShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_use_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_use_ynWithStringSrchUseYnShouldNotThrowException() {
        BBSMngForm clazz = new BBSMngForm();
        String srch_use_yn = "srch_use_yn";
        boolean isNotException = true;
        try {
            clazz.setSrch_use_yn(srch_use_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
