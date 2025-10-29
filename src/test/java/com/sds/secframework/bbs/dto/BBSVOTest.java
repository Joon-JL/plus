package com.sds.secframework.bbs.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class BBSVOTest {

    @Test(timeout = 20000)
    public void testGetBbs_idShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
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
        BBSVO clazz = new BBSVO();
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
    public void testGetNotice_idShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getNotice_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNotice_idWithStringNoticeIdShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        String notice_id = "notice_id";
        boolean isNotException = true;
        try {
            clazz.setNotice_id(notice_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNotice_titleShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getNotice_title();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNotice_titleWithStringNoticeTitleShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        String notice_title = "notice_title";
        boolean isNotException = true;
        try {
            clazz.setNotice_title(notice_title);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNotice_cntntShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getNotice_cntnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNotice_cntntWithStringNoticeCntntShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        String notice_cntnt = "notice_cntnt";
        boolean isNotException = true;
        try {
            clazz.setNotice_cntnt(notice_cntnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUp_notice_idShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getUp_notice_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUp_notice_idWithStringUpNoticeIdShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        String up_notice_id = "up_notice_id";
        boolean isNotException = true;
        try {
            clazz.setUp_notice_id(up_notice_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNotice_grp_idShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getNotice_grp_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNotice_grp_idWithStringNoticeGrpIdShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        String notice_grp_id = "notice_grp_id";
        boolean isNotException = true;
        try {
            clazz.setNotice_grp_id(notice_grp_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNotice_depthShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getNotice_depth();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNotice_depthWithIntNoticeDepthShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        int notice_depth = 0;
        boolean isNotException = true;
        try {
            clazz.setNotice_depth(notice_depth);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNotice_orderShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getNotice_order();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNotice_orderWithIntNoticeOrderShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        int notice_order = 0;
        boolean isNotException = true;
        try {
            clazz.setNotice_order(notice_order);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNotice_dispShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getNotice_disp();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNotice_dispWithStringNoticeDispShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        String notice_disp = "notice_disp";
        boolean isNotException = true;
        try {
            clazz.setNotice_disp(notice_disp);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStatusShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getStatus();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetStatusWithStringStatusShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        String status = "status";
        boolean isNotException = true;
        try {
            clazz.setStatus(status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUrgency_ynShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
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
        BBSVO clazz = new BBSVO();
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
    public void testGetRef_cntShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getRef_cnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRef_cntWithIntRefCntShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        int ref_cnt = 0;
        boolean isNotException = true;
        try {
            clazz.setRef_cnt(ref_cnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRecommend_cntShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getRecommend_cnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRecommend_cntWithIntRecommendCntShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        int recommend_cnt = 0;
        boolean isNotException = true;
        try {
            clazz.setRecommend_cnt(recommend_cnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNotice_start_ymdShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getNotice_start_ymd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNotice_start_ymdWithStringNoticeStartYmdShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        String notice_start_ymd = "notice_start_ymd";
        boolean isNotException = true;
        try {
            clazz.setNotice_start_ymd(notice_start_ymd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNotice_end_ymdShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getNotice_end_ymd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNotice_end_ymdWithStringNoticeEndYmdShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        String notice_end_ymd = "notice_end_ymd";
        boolean isNotException = true;
        try {
            clazz.setNotice_end_ymd(notice_end_ymd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNotice_ymdShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getNotice_ymd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNotice_ymdWithStringNoticeYmdShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        String notice_ymd = "notice_ymd";
        boolean isNotException = true;
        try {
            clazz.setNotice_ymd(notice_ymd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_ref_noShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getFile_ref_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFile_ref_noWithStringFileRefNoShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        String file_ref_no = "file_ref_no";
        boolean isNotException = true;
        try {
            clazz.setFile_ref_no(file_ref_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_idShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
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
        BBSVO clazz = new BBSVO();
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
        BBSVO clazz = new BBSVO();
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
        BBSVO clazz = new BBSVO();
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
        BBSVO clazz = new BBSVO();
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
        BBSVO clazz = new BBSVO();
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
        BBSVO clazz = new BBSVO();
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
        BBSVO clazz = new BBSVO();
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
    public void testGetRecommend_user_idShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getRecommend_user_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRecommend_user_idWithStringRecommendUserIdShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        String recommend_user_id = "recommend_user_id";
        boolean isNotException = true;
        try {
            clazz.setRecommend_user_id(recommend_user_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRecommend_ynShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
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
        BBSVO clazz = new BBSVO();
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
    public void testGetRecommend_dtShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getRecommend_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRecommend_dtWithStringRecommendDtShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        String recommend_dt = "recommend_dt";
        boolean isNotException = true;
        try {
            clazz.setRecommend_dt(recommend_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAppend_seqShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getAppend_seq();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAppend_seqWithStringAppendSeqShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        String append_seq = "append_seq";
        boolean isNotException = true;
        try {
            clazz.setAppend_seq(append_seq);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAppend_cntntShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getAppend_cntnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAppend_cntntWithStringAppendCntntShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        String append_cntnt = "append_cntnt";
        boolean isNotException = true;
        try {
            clazz.setAppend_cntnt(append_cntnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFileInfosShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getFileInfos();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFileInfosWithStringFileInfosShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        String fileInfos = "fileInfos";
        boolean isNotException = true;
        try {
            clazz.setFileInfos(fileInfos);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOrign_notice_idShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getOrign_notice_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOrign_notice_idWithStringOrignNoticeIdShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        String orign_notice_id = "orign_notice_id";
        boolean isNotException = true;
        try {
            clazz.setOrign_notice_id(orign_notice_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRef_cnt_apply_ynShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getRef_cnt_apply_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRef_cnt_apply_ynWithStringRefCntApplyYnShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        String ref_cnt_apply_yn = "ref_cnt_apply_yn";
        boolean isNotException = true;
        try {
            clazz.setRef_cnt_apply_yn(ref_cnt_apply_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_start_notice_ymdShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_start_notice_ymd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_start_notice_ymdWithStringSrchStartNoticeYmdShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        String srch_start_notice_ymd = "srch_start_notice_ymd";
        boolean isNotException = true;
        try {
            clazz.setSrch_start_notice_ymd(srch_start_notice_ymd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_end_notice_ymdShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_end_notice_ymd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_end_notice_ymdWithStringSrchEndNoticeYmdShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        String srch_end_notice_ymd = "srch_end_notice_ymd";
        boolean isNotException = true;
        try {
            clazz.setSrch_end_notice_ymd(srch_end_notice_ymd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_cntnt_typeShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_cntnt_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_cntnt_typeWithStringSrchCntntTypeShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        String srch_cntnt_type = "srch_cntnt_type";
        boolean isNotException = true;
        try {
            clazz.setSrch_cntnt_type(srch_cntnt_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_cntntShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_cntnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_cntntWithStringSrchCntntShouldNotThrowException() {
        BBSVO clazz = new BBSVO();
        String srch_cntnt = "srch_cntnt";
        boolean isNotException = true;
        try {
            clazz.setSrch_cntnt(srch_cntnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
