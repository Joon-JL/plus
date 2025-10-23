package com.sec.las.lawinformation.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class LibraryVOTest {

    @Test(timeout = 20000)
    public void testGetInfo_gbnShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        boolean isNotException = true;
        try {
            clazz.getInfo_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBody_mimeShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        boolean isNotException = true;
        try {
            clazz.getBody_mime();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBody_mimeWithStringBodyMimeShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        String body_mime = "body_mime";
        boolean isNotException = true;
        try {
            clazz.setBody_mime(body_mime);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_start_dtShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
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
        LibraryVO clazz = new LibraryVO();
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
        LibraryVO clazz = new LibraryVO();
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
        LibraryVO clazz = new LibraryVO();
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
    public void testSetInfo_gbnWithStringInfoGbnShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        String info_gbn = "info_gbn";
        boolean isNotException = true;
        try {
            clazz.setInfo_gbn(info_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeqnoShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        boolean isNotException = true;
        try {
            clazz.getSeqno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeqnoWithIntSeqnoShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
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
    public void testGetLib_gbnShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        boolean isNotException = true;
        try {
            clazz.getLib_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLib_gbnWithStringLibGbnShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        String lib_gbn = "lib_gbn";
        boolean isNotException = true;
        try {
            clazz.setLib_gbn(lib_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTitleShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
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
        LibraryVO clazz = new LibraryVO();
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
    public void testGetContShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        boolean isNotException = true;
        try {
            clazz.getCont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetContWithStringContShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        String cont = "cont";
        boolean isNotException = true;
        try {
            clazz.setCont(cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUsageShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        boolean isNotException = true;
        try {
            clazz.getUsage();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUsageWithStringUsageShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        String usage = "usage";
        boolean isNotException = true;
        try {
            clazz.setUsage(usage);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRdcntShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        boolean isNotException = true;
        try {
            clazz.getRdcnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRdcntWithIntRdcntShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        int rdcnt = 0;
        boolean isNotException = true;
        try {
            clazz.setRdcnt(rdcnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRd_authShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        boolean isNotException = true;
        try {
            clazz.getRd_auth();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRd_authWithStringRdAuthShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        String rd_auth = "rd_auth";
        boolean isNotException = true;
        try {
            clazz.setRd_auth(rd_auth);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dtShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
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
        LibraryVO clazz = new LibraryVO();
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
    public void testGetReg_idShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
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
        LibraryVO clazz = new LibraryVO();
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
        LibraryVO clazz = new LibraryVO();
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
        LibraryVO clazz = new LibraryVO();
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
    public void testGetReg_deptShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        boolean isNotException = true;
        try {
            clazz.getReg_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_deptWithStringRegDeptShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        String reg_dept = "reg_dept";
        boolean isNotException = true;
        try {
            clazz.setReg_dept(reg_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dept_nmShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
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
        LibraryVO clazz = new LibraryVO();
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
    public void testGetMod_dtShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
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
        LibraryVO clazz = new LibraryVO();
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
    public void testGetMod_idShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
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
        LibraryVO clazz = new LibraryVO();
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
        LibraryVO clazz = new LibraryVO();
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
        LibraryVO clazz = new LibraryVO();
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
    public void testGetDel_ynShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
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
        LibraryVO clazz = new LibraryVO();
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
    public void testGetDel_dtShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        boolean isNotException = true;
        try {
            clazz.getDel_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_dtWithStringDelDtShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
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
    public void testGetDel_idShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        boolean isNotException = true;
        try {
            clazz.getDel_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_idWithStringDelIdShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
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
    public void testGetDel_nmShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        boolean isNotException = true;
        try {
            clazz.getDel_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_nmWithStringDelNmShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
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
    public void testGetSrch_nameShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_name();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_nameWithStringSrchNameShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        String srch_name = "srch_name";
        boolean isNotException = true;
        try {
            clazz.setSrch_name(srch_name);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_comboShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_combo();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_comboWithStringSrchComboShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        String srch_combo = "srch_combo";
        boolean isNotException = true;
        try {
            clazz.setSrch_combo(srch_combo);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_lib_gbnShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_lib_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_lib_gbnWithStringSrchLibGbnShouldNotThrowException() {
        LibraryVO clazz = new LibraryVO();
        String srch_lib_gbn = "srch_lib_gbn";
        boolean isNotException = true;
        try {
            clazz.setSrch_lib_gbn(srch_lib_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
