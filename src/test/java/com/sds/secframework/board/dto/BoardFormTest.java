package com.sds.secframework.board.dto;

import com.sds.secframework.common.dto.CommonForm;
import com.sds.secframework.common.util.DateUtil;
import java.lang.String;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;
import static org.mockito.Mockito.mock;

public class BoardFormTest {

    @Test(timeout = 20000)
    public void testGetSrch_div_cdShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_div_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_div_cdWithStringSrchDivCdShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        String srch_div_cd = "srch_div_cd";
        boolean isNotException = true;
        try {
            clazz.setSrch_div_cd(srch_div_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_typeShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_typeWithStringSrchTypeShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        String srch_type = "srch_type";
        boolean isNotException = true;
        try {
            clazz.setSrch_type(srch_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_wordShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_word();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_wordWithStringSrchWordShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        String srch_word = "srch_word";
        boolean isNotException = true;
        try {
            clazz.setSrch_word(srch_word);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_start_dtShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
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
        BoardForm clazz = new BoardForm();
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
        BoardForm clazz = new BoardForm();
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
        BoardForm clazz = new BoardForm();
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
    public void testGetUser_row_cntShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getUser_row_cnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUser_row_cntWithIntUserRowCntShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        int user_row_cnt = 0;
        boolean isNotException = true;
        try {
            clazz.setUser_row_cnt(user_row_cnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSys_cdShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
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
        BoardForm clazz = new BoardForm();
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
    public void testGetBoard_idShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getBoard_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBoard_idWithStringBoardIdShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        String board_id = "board_id";
        boolean isNotException = true;
        try {
            clazz.setBoard_id(board_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNotice_idShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
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
        BoardForm clazz = new BoardForm();
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
    public void testGetUp_notice_idShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
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
        BoardForm clazz = new BoardForm();
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
    public void testGetNotice_levelShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getNotice_level();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNotice_levelWithStringNoticeLevelShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        String notice_level = "notice_level";
        boolean isNotException = true;
        try {
            clazz.setNotice_level(notice_level);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNotice_level_orderShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getNotice_level_order();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNotice_level_orderWithIntNoticeLevelOrderShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        int notice_level_order = 0;
        boolean isNotException = true;
        try {
            clazz.setNotice_level_order(notice_level_order);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNotice_status_cdShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getNotice_status_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNotice_status_cdWithStringNoticeStatusCdShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        String notice_status_cd = "notice_status_cd";
        boolean isNotException = true;
        try {
            clazz.setNotice_status_cd(notice_status_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDiv_cdShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getDiv_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDiv_cdWithStringDivCdShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        String div_cd = "div_cd";
        boolean isNotException = true;
        try {
            clazz.setDiv_cd(div_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTitleShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
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
        BoardForm clazz = new BoardForm();
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
    public void testGetContentsShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getContents();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetContentsWithStringContentsShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        String contents = "contents";
        boolean isNotException = true;
        try {
            clazz.setContents(contents);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetContents_typeShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getContents_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetContents_typeWithStringContentsTypeShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        String contents_type = "contents_type";
        boolean isNotException = true;
        try {
            clazz.setContents_type(contents_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_ref_noShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
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
        BoardForm clazz = new BoardForm();
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
    public void testGetImg_file_ref_noShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getImg_file_ref_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetImg_file_ref_noWithStringImgFileRefNoShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        String img_file_ref_no = "img_file_ref_no";
        boolean isNotException = true;
        try {
            clazz.setImg_file_ref_no(img_file_ref_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRef_cntShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
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
        BoardForm clazz = new BoardForm();
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
        BoardForm clazz = new BoardForm();
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
        BoardForm clazz = new BoardForm();
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
    public void testGetOpen_ynShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getOpen_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOpen_ynWithStringOpenYnShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        String open_yn = "open_yn";
        boolean isNotException = true;
        try {
            clazz.setOpen_yn(open_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSecurity_ynShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getSecurity_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSecurity_ynWithStringSecurityYnShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        String security_yn = "security_yn";
        boolean isNotException = true;
        try {
            clazz.setSecurity_yn(security_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPasswordShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getPassword();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPasswordWithStringPasswordShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        String password = "password";
        boolean isNotException = true;
        try {
            clazz.setPassword(password);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAnonymity_ynShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getAnonymity_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAnonymity_ynWithStringAnonymityYnShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        String anonymity_yn = "anonymity_yn";
        boolean isNotException = true;
        try {
            clazz.setAnonymity_yn(anonymity_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAnonymity_nmShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getAnonymity_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAnonymity_nmWithStringAnonymityNmShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        String anonymity_nm = "anonymity_nm";
        boolean isNotException = true;
        try {
            clazz.setAnonymity_nm(anonymity_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHold_day_ynShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getHold_day_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHold_day_ynWithStringHoldDayYnShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        String hold_day_yn = "hold_day_yn";
        boolean isNotException = true;
        try {
            clazz.setHold_day_yn(hold_day_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHold_start_ymdShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getHold_start_ymd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHold_start_ymdWithStringHoldStartYmdShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        String hold_start_ymd = "hold_start_ymd";
        boolean isNotException = true;
        try {
            clazz.setHold_start_ymd(hold_start_ymd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHold_end_ymdShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getHold_end_ymd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHold_end_ymdWithStringHoldEndYmdShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        String hold_end_ymd = "hold_end_ymd";
        boolean isNotException = true;
        try {
            clazz.setHold_end_ymd(hold_end_ymd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUrgency_ynShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
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
        BoardForm clazz = new BoardForm();
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
    public void testGetUrgency_start_ymdShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getUrgency_start_ymd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUrgency_start_ymdWithStringUrgencyStartYmdShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        String urgency_start_ymd = "urgency_start_ymd";
        boolean isNotException = true;
        try {
            clazz.setUrgency_start_ymd(urgency_start_ymd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUrgency_end_ymdShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getUrgency_end_ymd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUrgency_end_ymdWithStringUrgencyEndYmdShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        String urgency_end_ymd = "urgency_end_ymd";
        boolean isNotException = true;
        try {
            clazz.setUrgency_end_ymd(urgency_end_ymd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_ynShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
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
        BoardForm clazz = new BoardForm();
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
    public void testGetReg_idShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
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
        BoardForm clazz = new BoardForm();
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
        BoardForm clazz = new BoardForm();
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
        BoardForm clazz = new BoardForm();
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
    public void testGetReg_jikgup_nmShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getReg_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_jikgup_nmWithStringRegJikgupNmShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        String reg_jikgup_nm = "reg_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setReg_jikgup_nm(reg_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dept_nmShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
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
        BoardForm clazz = new BoardForm();
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
    public void testGetReg_dtShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
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
        BoardForm clazz = new BoardForm();
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
        BoardForm clazz = new BoardForm();
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
        BoardForm clazz = new BoardForm();
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
        BoardForm clazz = new BoardForm();
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
        BoardForm clazz = new BoardForm();
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
    public void testGetMod_jikgup_nmShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getMod_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_jikgup_nmWithStringModJikgupNmShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        String mod_jikgup_nm = "mod_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setMod_jikgup_nm(mod_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMod_dept_nmShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
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
        BoardForm clazz = new BoardForm();
        String mod_dept_nm = "mod_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setMod_dept_nm(mod_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMod_dtShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
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
        BoardForm clazz = new BoardForm();
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
    public void testGetImg_fileShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getImg_file();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetImg_fileWithMultipartFileImgFileShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        MultipartFile img_file = mock(MultipartFile.class);
        boolean isNotException = true;
        try {
            clazz.setImg_file(img_file);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetView_gbnShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        boolean isNotException = true;
        try {
            clazz.getView_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetView_gbnWithStringViewGbnShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
        String view_gbn = "view_gbn";
        boolean isNotException = true;
        try {
            clazz.setView_gbn(view_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFileInfosShouldNotThrowException() {
        BoardForm clazz = new BoardForm();
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
        BoardForm clazz = new BoardForm();
        String fileInfos = "fileInfos";
        boolean isNotException = true;
        try {
            clazz.setFileInfos(fileInfos);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
