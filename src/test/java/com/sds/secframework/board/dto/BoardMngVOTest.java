package com.sds.secframework.board.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class BoardMngVOTest {

    @Test(timeout = 20000)
    public void testGetDefaultLocaleShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        boolean isNotException = true;
        try {
            clazz.getDefaultLocale();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDefaultLocaleWithStringDefaultLocaleShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        String defaultLocale = "defaultLocale";
        boolean isNotException = true;
        try {
            clazz.setDefaultLocale(defaultLocale);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_use_ynShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
        String srch_use_yn = "srch_use_yn";
        boolean isNotException = true;
        try {
            clazz.setSrch_use_yn(srch_use_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_board_nmShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_board_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_board_nmWithStringSrchBoardNmShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        String srch_board_nm = "srch_board_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_board_nm(srch_board_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCommentsShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
    public void testGetBoard_typeShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        boolean isNotException = true;
        try {
            clazz.getBoard_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBoard_typeWithStringBoardTypeShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        String board_type = "board_type";
        boolean isNotException = true;
        try {
            clazz.setBoard_type(board_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDefault_row_cntShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        boolean isNotException = true;
        try {
            clazz.getDefault_row_cnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDefault_row_cntWithIntDefaultRowCntShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        int default_row_cnt = 0;
        boolean isNotException = true;
        try {
            clazz.setDefault_row_cnt(default_row_cnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRow_cnt_ynShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        boolean isNotException = true;
        try {
            clazz.getRow_cnt_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRow_cnt_ynWithStringRowCntYnShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        String row_cnt_yn = "row_cnt_yn";
        boolean isNotException = true;
        try {
            clazz.setRow_cnt_yn(row_cnt_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRow_cntShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        boolean isNotException = true;
        try {
            clazz.getRow_cnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRow_cntWithStringRowCntShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        String row_cnt = "row_cnt";
        boolean isNotException = true;
        try {
            clazz.setRow_cnt(row_cnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNew_hold_dayShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        boolean isNotException = true;
        try {
            clazz.getNew_hold_day();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNew_hold_dayWithIntNewHoldDayShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        int new_hold_day = 0;
        boolean isNotException = true;
        try {
            clazz.setNew_hold_day(new_hold_day);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDefault_hold_dayShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        boolean isNotException = true;
        try {
            clazz.getDefault_hold_day();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDefault_hold_dayWithIntDefaultHoldDayShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        int default_hold_day = 0;
        boolean isNotException = true;
        try {
            clazz.setDefault_hold_day(default_hold_day);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHold_day_ynShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
    public void testGetImg_ynShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        boolean isNotException = true;
        try {
            clazz.getImg_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetImg_ynWithStringImgYnShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        String img_yn = "img_yn";
        boolean isNotException = true;
        try {
            clazz.setImg_yn(img_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_ynShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
    public void testGetMax_file_cntShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        boolean isNotException = true;
        try {
            clazz.getMax_file_cnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMax_file_cntWithIntMaxFileCntShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        int max_file_cnt = 0;
        boolean isNotException = true;
        try {
            clazz.setMax_file_cnt(max_file_cnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMax_file_sizeShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        boolean isNotException = true;
        try {
            clazz.getMax_file_size();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMax_file_sizeWithIntMaxFileSizeShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        int max_file_size = 0;
        boolean isNotException = true;
        try {
            clazz.setMax_file_size(max_file_size);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDiv_ynShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        boolean isNotException = true;
        try {
            clazz.getDiv_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDiv_ynWithStringDivYnShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        String div_yn = "div_yn";
        boolean isNotException = true;
        try {
            clazz.setDiv_yn(div_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDiv_cdShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
    public void testGetRecommend_ynShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
    public void testGetAppend_recommend_ynShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        boolean isNotException = true;
        try {
            clazz.getAppend_recommend_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAppend_recommend_ynWithStringAppendRecommendYnShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        String append_recommend_yn = "append_recommend_yn";
        boolean isNotException = true;
        try {
            clazz.setAppend_recommend_yn(append_recommend_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReply_ynShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
    public void testGetOpen_ynShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
    public void testGetAnonymity_ynShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
    public void testGetUse_ynShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
    public void testGetSend_mail_ynShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        boolean isNotException = true;
        try {
            clazz.getSend_mail_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSend_mail_ynWithStringSendMailYnShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        String send_mail_yn = "send_mail_yn";
        boolean isNotException = true;
        try {
            clazz.setSend_mail_yn(send_mail_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSend_mail_addrShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        boolean isNotException = true;
        try {
            clazz.getSend_mail_addr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSend_mail_addrWithStringSendMailAddrShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        String send_mail_addr = "send_mail_addr";
        boolean isNotException = true;
        try {
            clazz.setSend_mail_addr(send_mail_addr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTemp_save_ynShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        boolean isNotException = true;
        try {
            clazz.getTemp_save_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTemp_save_ynWithStringTempSaveYnShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        String temp_save_yn = "temp_save_yn";
        boolean isNotException = true;
        try {
            clazz.setTemp_save_yn(temp_save_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUrgency_ynShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
    public void testGetUrgency_dayShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        boolean isNotException = true;
        try {
            clazz.getUrgency_day();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUrgency_dayWithIntUrgencyDayShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        int urgency_day = 0;
        boolean isNotException = true;
        try {
            clazz.setUrgency_day(urgency_day);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRead_user_ynShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        boolean isNotException = true;
        try {
            clazz.getRead_user_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRead_user_ynWithStringReadUserYnShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        String read_user_yn = "read_user_yn";
        boolean isNotException = true;
        try {
            clazz.setRead_user_yn(read_user_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_ynShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
    public void testGetBoard_nm_arrayShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        boolean isNotException = true;
        try {
            clazz.getBoard_nm_array();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBoard_nm_arrayWithStringArrayBoardNmArrayShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        String[] board_nm_array = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setBoard_nm_array(board_nm_array);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBoard_nmShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        boolean isNotException = true;
        try {
            clazz.getBoard_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBoard_nmWithStringBoardNmShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        String board_nm = "board_nm";
        boolean isNotException = true;
        try {
            clazz.setBoard_nm(board_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetLanguage_type_arrayShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        boolean isNotException = true;
        try {
            clazz.getLanguage_type_array();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLanguage_type_arrayWithStringArrayLanguageTypeArrayShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        String[] language_type_array = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setLanguage_type_array(language_type_array);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetLanguage_typeShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        boolean isNotException = true;
        try {
            clazz.getLanguage_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLanguage_typeWithStringLanguageTypeShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
        String language_type = "language_type";
        boolean isNotException = true;
        try {
            clazz.setLanguage_type(language_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSys_cdShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
    public void testGetReg_idShouldNotThrowException() {
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
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
        BoardMngVO clazz = new BoardMngVO();
        String mod_dt = "mod_dt";
        boolean isNotException = true;
        try {
            clazz.setMod_dt(mod_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
