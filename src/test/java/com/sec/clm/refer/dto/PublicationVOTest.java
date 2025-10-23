package com.sec.clm.refer.dto;

import java.lang.Exception;
import com.sds.secframework.common.util.DateUtil;
import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class PublicationVOTest {

    @Test(timeout = 20000)
    public void testConstructorNotNull() throws Exception {
        PublicationVO obj = new PublicationVO();
        assert obj != null;
    }

    @Test(timeout = 20000)
    public void testGetSrch_typeShouldNotThrowException() throws Exception {
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_end_ymd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_ref_noShouldNotThrowException() throws Exception {
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
    public void testSetSrch_end_ymdWithStringSrchEndYmdShouldNotThrowException() throws Exception {
        PublicationVO clazz = new PublicationVO();
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
    public void testGetSys_cdShouldNotThrowException() throws Exception {
        PublicationVO clazz = new PublicationVO();
        boolean isNotException = true;
        try {
            clazz.getSys_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSys_cdWithStringSysCdShouldNotThrowException() throws Exception {
        PublicationVO clazz = new PublicationVO();
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
    public void testGetNotice_idShouldNotThrowException() throws Exception {
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
    public void testGetNew_hold_dayShouldNotThrowException() throws Exception {
        PublicationVO clazz = new PublicationVO();
        boolean isNotException = true;
        try {
            clazz.getNew_hold_day();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNew_hold_dayWithIntNewHoldDayShouldNotThrowException() throws Exception {
        PublicationVO clazz = new PublicationVO();
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
    public void testGetDel_ynShouldNotThrowException() throws Exception {
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
        PublicationVO clazz = new PublicationVO();
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
