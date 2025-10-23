package com.sds.secframework.board.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import java.util.List;
import static org.mockito.Mockito.mock;
import java.util.ArrayList;

public class BoardMngFormTest {

    @Test(timeout = 20000)
    public void testGetSrch_use_ynShouldNotThrowException() {
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
    public void testGetResultListShouldNotThrowException() {
        BoardMngForm clazz = new BoardMngForm();
        boolean isNotException = true;
        try {
            clazz.getResultList();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetResultListWithListResultListShouldNotThrowException() {
        BoardMngForm clazz = new BoardMngForm();
        List resultList = mock(List.class);
        boolean isNotException = true;
        try {
            clazz.setResultList(resultList);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSys_cdShouldNotThrowException() {
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
    public void testGetBoard_nm_koShouldNotThrowException() {
        BoardMngForm clazz = new BoardMngForm();
        boolean isNotException = true;
        try {
            clazz.getBoard_nm_ko();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBoard_nm_koWithStringBoardNmKoShouldNotThrowException() {
        BoardMngForm clazz = new BoardMngForm();
        String board_nm_ko = "board_nm_ko";
        boolean isNotException = true;
        try {
            clazz.setBoard_nm_ko(board_nm_ko);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBoard_nm_enShouldNotThrowException() {
        BoardMngForm clazz = new BoardMngForm();
        boolean isNotException = true;
        try {
            clazz.getBoard_nm_en();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBoard_nm_enWithStringBoardNmEnShouldNotThrowException() {
        BoardMngForm clazz = new BoardMngForm();
        String board_nm_en = "board_nm_en";
        boolean isNotException = true;
        try {
            clazz.setBoard_nm_en(board_nm_en);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBoard_nmShouldNotThrowException() {
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
    public void testGetBoard_nm_listShouldNotThrowException() {
        BoardMngForm clazz = new BoardMngForm();
        boolean isNotException = true;
        try {
            clazz.getBoard_nm_list();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBoard_nm_listWithArrayListBoardNmListShouldNotThrowException() {
        BoardMngForm clazz = new BoardMngForm();
        ArrayList board_nm_list = mock(ArrayList.class);
        boolean isNotException = true;
        try {
            clazz.setBoard_nm_list(board_nm_list);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCommentsShouldNotThrowException() {
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
    public void testGetBoard_type_nmShouldNotThrowException() {
        BoardMngForm clazz = new BoardMngForm();
        boolean isNotException = true;
        try {
            clazz.getBoard_type_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBoard_type_nmWithStringBoardTypeNmShouldNotThrowException() {
        BoardMngForm clazz = new BoardMngForm();
        String board_type_nm = "board_type_nm";
        boolean isNotException = true;
        try {
            clazz.setBoard_type_nm(board_type_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDefault_row_cntShouldNotThrowException() {
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
    public void testGetRow_cnt_comboShouldNotThrowException() {
        BoardMngForm clazz = new BoardMngForm();
        boolean isNotException = true;
        try {
            clazz.getRow_cnt_combo();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRow_cnt_comboWithStringRowCntComboShouldNotThrowException() {
        BoardMngForm clazz = new BoardMngForm();
        String row_cnt_combo = "row_cnt_combo";
        boolean isNotException = true;
        try {
            clazz.setRow_cnt_combo(row_cnt_combo);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRow_cnt_ynShouldNotThrowException() {
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
        boolean isNotException = true;
        try {
            clazz.getDiv_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDiv_cd_comboShouldNotThrowException() {
        BoardMngForm clazz = new BoardMngForm();
        boolean isNotException = true;
        try {
            clazz.getDiv_cd_combo();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDiv_cd_comboWithStringDivCdComboShouldNotThrowException() {
        BoardMngForm clazz = new BoardMngForm();
        String div_cd_combo = "div_cd_combo";
        boolean isNotException = true;
        try {
            clazz.setDiv_cd_combo(div_cd_combo);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDiv_cdWithStringDivCdShouldNotThrowException() {
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
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
        BoardMngForm clazz = new BoardMngForm();
        String del_yn = "del_yn";
        boolean isNotException = true;
        try {
            clazz.setDel_yn(del_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
