package com.sec.clm.share.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class ContractAttentionVOTest {

    @Test(timeout = 20000)
    public void testSetMtbat_noWithIntMtbatNoShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        int mtbat_no = 0;
        boolean isNotException = true;
        try {
            clazz.setMtbat_no(mtbat_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetType_gbnWithStringTypeGbnShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        String type_gbn = "type_gbn";
        boolean isNotException = true;
        try {
            clazz.setType_gbn(type_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetType_bigclsfcnWithStringTypeBigclsfcnShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        String type_bigclsfcn = "type_bigclsfcn";
        boolean isNotException = true;
        try {
            clazz.setType_bigclsfcn(type_bigclsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetType_midclsfcnWithStringTypeMidclsfcnShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        String type_midclsfcn = "type_midclsfcn";
        boolean isNotException = true;
        try {
            clazz.setType_midclsfcn(type_midclsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTitleWithStringTitleShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
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
    public void testSetContWithStringContShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
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
    public void testSetRdcntWithIntRdcntShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
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
    public void testSetReg_dtWithStringRegDtShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
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
        ContractAttentionVO clazz = new ContractAttentionVO();
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
        ContractAttentionVO clazz = new ContractAttentionVO();
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
        ContractAttentionVO clazz = new ContractAttentionVO();
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
        ContractAttentionVO clazz = new ContractAttentionVO();
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
        ContractAttentionVO clazz = new ContractAttentionVO();
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
        ContractAttentionVO clazz = new ContractAttentionVO();
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
        ContractAttentionVO clazz = new ContractAttentionVO();
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
        ContractAttentionVO clazz = new ContractAttentionVO();
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
        ContractAttentionVO clazz = new ContractAttentionVO();
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
    public void testSetStart_indexWithStringStartIndexShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        String start_index = "start_index";
        boolean isNotException = true;
        try {
            clazz.setStart_index(start_index);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEnd_indexWithStringEndIndexShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        String end_index = "end_index";
        boolean isNotException = true;
        try {
            clazz.setEnd_index(end_index);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCurPageWithStringCurPageShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        String curPage = "curPage";
        boolean isNotException = true;
        try {
            clazz.setCurPage(curPage);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMtbat_noShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        boolean isNotException = true;
        try {
            clazz.getMtbat_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetType_gbnShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        boolean isNotException = true;
        try {
            clazz.getType_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetType_bigclsfcnShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        boolean isNotException = true;
        try {
            clazz.getType_bigclsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetType_midclsfcnShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        boolean isNotException = true;
        try {
            clazz.getType_midclsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTitleShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        boolean isNotException = true;
        try {
            clazz.getTitle();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetContShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        boolean isNotException = true;
        try {
            clazz.getCont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRdcntShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        boolean isNotException = true;
        try {
            clazz.getRdcnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dtShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
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
        ContractAttentionVO clazz = new ContractAttentionVO();
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
        ContractAttentionVO clazz = new ContractAttentionVO();
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
        ContractAttentionVO clazz = new ContractAttentionVO();
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
        ContractAttentionVO clazz = new ContractAttentionVO();
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
        ContractAttentionVO clazz = new ContractAttentionVO();
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
        ContractAttentionVO clazz = new ContractAttentionVO();
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
        ContractAttentionVO clazz = new ContractAttentionVO();
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
        ContractAttentionVO clazz = new ContractAttentionVO();
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
        ContractAttentionVO clazz = new ContractAttentionVO();
        boolean isNotException = true;
        try {
            clazz.getDel_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStart_indexShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        boolean isNotException = true;
        try {
            clazz.getStart_index();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEnd_indexShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        boolean isNotException = true;
        try {
            clazz.getEnd_index();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCurPageShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        boolean isNotException = true;
        try {
            clazz.getCurPage();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_bigclsfcnShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_bigclsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_bigclsfcnWithStringSrchBigclsfcnShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        String srch_bigclsfcn = "srch_bigclsfcn";
        boolean isNotException = true;
        try {
            clazz.setSrch_bigclsfcn(srch_bigclsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_midclsfcnShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_midclsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_midclsfcnWithStringSrchMidclsfcnShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        String srch_midclsfcn = "srch_midclsfcn";
        boolean isNotException = true;
        try {
            clazz.setSrch_midclsfcn(srch_midclsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_smlclsfcnShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_smlclsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_smlclsfcnWithStringSrchSmlclsfcnShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        String srch_smlclsfcn = "srch_smlclsfcn";
        boolean isNotException = true;
        try {
            clazz.setSrch_smlclsfcn(srch_smlclsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBody_mimeShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
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
        ContractAttentionVO clazz = new ContractAttentionVO();
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
    public void testIsAuth_insertShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        boolean isNotException = true;
        try {
            clazz.isAuth_insert();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_insertWithBooleanAuthInsertShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
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
    public void testIsAuth_modifyShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        boolean isNotException = true;
        try {
            clazz.isAuth_modify();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_modifyWithBooleanAuthModifyShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
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
    public void testIsAuth_deleteShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        boolean isNotException = true;
        try {
            clazz.isAuth_delete();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_deleteWithBooleanAuthDeleteShouldNotThrowException() {
        ContractAttentionVO clazz = new ContractAttentionVO();
        boolean auth_delete = false;
        boolean isNotException = true;
        try {
            clazz.setAuth_delete(auth_delete);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
