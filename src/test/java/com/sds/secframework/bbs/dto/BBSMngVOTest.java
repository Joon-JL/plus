package com.sds.secframework.bbs.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class BBSMngVOTest {

    @Test(timeout = 20000)
    public void testGetSys_cdShouldNotThrowException() {
        BBSMngVO clazz = new BBSMngVO();
        boolean isNotException = true;
        try {
            clazz.getSys_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSys_cdWithStringSysCdShouldNotThrowException() {
        BBSMngVO clazz = new BBSMngVO();
        String sys_cd = "sys_cd";
        boolean isNotException = true;
        try {
            clazz.setSys_cd(sys_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBbs_idShouldNotThrowException() {
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
        BBSMngVO clazz = new BBSMngVO();
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
