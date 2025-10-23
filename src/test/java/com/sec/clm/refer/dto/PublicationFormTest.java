package com.sec.clm.refer.dto;

import java.lang.Exception;
import com.sds.secframework.common.dto.CommonForm;
import com.sds.secframework.common.util.DateUtil;
import java.lang.String;
import org.junit.Test;
import java.util.List;
import static org.mockito.Mockito.mock;

public class PublicationFormTest {

    @Test(timeout = 20000)
    public void testConstructorNotNull() throws Exception {
        PublicationForm obj = new PublicationForm();
        assert obj != null;
    }

    @Test(timeout = 20000)
    public void testIsAuth_insertShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.isAuth_insert();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_insertWithBooleanAuthInsertShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean auth_insert = false;
        boolean isNotException = true;
        try {
            clazz.setAuth_insert(auth_insert);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testIsAuth_modifyShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.isAuth_modify();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_modifyWithBooleanAuthModifyShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean auth_modify = false;
        boolean isNotException = true;
        try {
            clazz.setAuth_modify(auth_modify);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testIsAuth_deleteShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.isAuth_delete();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_deleteWithBooleanAuthDeleteShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean auth_delete = false;
        boolean isNotException = true;
        try {
            clazz.setAuth_delete(auth_delete);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBoard_nmShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getBoard_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBoard_nmWithStringBoardNmShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
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
    public void testGetSrch_typeShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_typeWithStringSrchTypeShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
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
    public void testGetSrch_wordShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_word();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_wordWithStringSrchWordShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
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
    public void testGetSrch_start_ymdShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_start_ymd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_start_ymdWithStringSrchStartYmdShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        String srch_start_ymd = "srch_start_ymd";
        boolean isNotException = true;
        try {
            clazz.setSrch_start_ymd(srch_start_ymd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_end_ymdShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_end_ymd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_end_ymdWithStringSrchEndYmdShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        String srch_end_ymd = "srch_end_ymd";
        boolean isNotException = true;
        try {
            clazz.setSrch_end_ymd(srch_end_ymd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRow_cntShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getRow_cnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRow_cntWithIntRowCntShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        int row_cnt = 0;
        boolean isNotException = true;
        try {
            clazz.setRow_cnt(row_cnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_ref_noShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getFile_ref_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFile_ref_noWithStringFileRefNoShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
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
    public void testGetView_gbnShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getView_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetView_gbnWithStringViewGbnShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
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
    public void testGetFileInfosShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getFileInfos();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFileInfosWithStringFileInfosShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
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
    public void testGetNotice_idShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getNotice_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNotice_idWithStringNoticeIdShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
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
    public void testGetTitleShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getTitle();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTitleWithStringTitleShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
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
    public void testGetContentsShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getContents();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetContentsWithStringContentsShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
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
    public void testGetContents_typeShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getContents_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetContents_typeWithStringContentsTypeShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
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
    public void testGetRef_cntShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getRef_cnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRef_cntWithIntRefCntShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
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
    public void testGetDel_ynShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getDel_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_ynWithStringDelYnShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
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
    public void testGetReg_idShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getReg_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_idWithStringRegIdShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
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
    public void testGetReg_nmShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getReg_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_nmWithStringRegNmShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
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
    public void testGetReg_jikgup_nmShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getReg_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_jikgup_nmWithStringRegJikgupNmShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
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
    public void testGetReg_dept_nmShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getReg_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_dept_nmWithStringRegDeptNmShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
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
    public void testGetReg_dtShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getReg_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_dtWithStringRegDtShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
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
    public void testGetMod_idShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getMod_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_idWithStringModIdShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
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
    public void testGetMod_nmShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getMod_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_nmWithStringModNmShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
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
    public void testGetMod_jikgup_nmShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getMod_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_jikgup_nmWithStringModJikgupNmShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
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
    public void testGetMod_dept_nmShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getMod_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_dept_nmWithStringModDeptNmShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
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
    public void testGetMod_dtShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getMod_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_dtWithStringModDtShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
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
    public void testGetPublication_listShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getPublication_list();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPublication_listWithListPublicationListShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        List publication_list = mock(List.class);
        boolean isNotException = true;
        try {
            clazz.setPublication_list(publication_list);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReturn_titleShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getReturn_title();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReturn_titleWithStringReturnTitleShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        String return_title = "return_title";
        boolean isNotException = true;
        try {
            clazz.setReturn_title(return_title);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReturn_messageShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        boolean isNotException = true;
        try {
            clazz.getReturn_message();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReturn_messageWithStringReturnMessageShouldNotThrowException() throws Exception {
        PublicationForm clazz = new PublicationForm();
        String return_message = "return_message";
        boolean isNotException = true;
        try {
            clazz.setReturn_message(return_message);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
