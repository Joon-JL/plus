package com.sec.clm.manage.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import java.util.List;
import static org.mockito.Mockito.mock;

public class ConclusionDelayFormTest {

    @Test(timeout = 20000)
    public void testSetCntrt_idWithStringCntrtIdShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        String cntrt_id = "cntrt_id";
        boolean isNotException = true;
        try {
            clazz.setCntrt_id(cntrt_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDlay_seqnoWithIntDlaySeqnoShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        int dlay_seqno = 0;
        boolean isNotException = true;
        try {
            clazz.setDlay_seqno(dlay_seqno);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetChgebfr_cnclsn_plnddayWithStringChgebfrCnclsnPlnddayShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        String chgebfr_cnclsn_plndday = "chgebfr_cnclsn_plndday";
        boolean isNotException = true;
        try {
            clazz.setChgebfr_cnclsn_plndday(chgebfr_cnclsn_plndday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetChgeaft_cnclsn_plnddayWithStringChgeaftCnclsnPlnddayShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        String chgeaft_cnclsn_plndday = "chgeaft_cnclsn_plndday";
        boolean isNotException = true;
        try {
            clazz.setChgeaft_cnclsn_plndday(chgeaft_cnclsn_plndday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDlay_causeWithStringDlayCauseShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        String dlay_cause = "dlay_cause";
        boolean isNotException = true;
        try {
            clazz.setDlay_cause(dlay_cause);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_idWithStringRegIdShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
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
        ConclusionDelayForm clazz = new ConclusionDelayForm();
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
    public void testSetDel_ynWithStringDelYnShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
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
    public void testSetDel_idWithStringDelIdShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
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
        ConclusionDelayForm clazz = new ConclusionDelayForm();
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
    public void testSetChk_valWithStringChkValShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        String chk_val = "chk_val";
        boolean isNotException = true;
        try {
            clazz.setChk_val(chk_val);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetWork_flagWithStringWorkFlagShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        String work_flag = "work_flag";
        boolean isNotException = true;
        try {
            clazz.setWork_flag(work_flag);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_id_arrWithStringArrayCntrtIdArrShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        String[] cntrt_id_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setCntrt_id_arr(cntrt_id_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDlay_seqno_arrWithIntArrayDlaySeqnoArrShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        int[] dlay_seqno_arr = mock(int[].class);
        boolean isNotException = true;
        try {
            clazz.setDlay_seqno_arr(dlay_seqno_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetChgebfr_cnclsn_plndday_arrWithStringArrayChgebfrCnclsnPlnddayArrShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        String[] chgebfr_cnclsn_plndday_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setChgebfr_cnclsn_plndday_arr(chgebfr_cnclsn_plndday_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetChgeaft_cnclsn_plndday_arrWithStringArrayChgeaftCnclsnPlnddayArrShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        String[] chgeaft_cnclsn_plndday_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setChgeaft_cnclsn_plndday_arr(chgeaft_cnclsn_plndday_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDlay_cause_arrWithStringArrayDlayCauseArrShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        String[] dlay_cause_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setDlay_cause_arr(dlay_cause_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_id_arrWithStringArrayRegIdArrShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        String[] reg_id_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setReg_id_arr(reg_id_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_nm_arrWithStringArrayRegNmArrShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        String[] reg_nm_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setReg_nm_arr(reg_nm_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_yn_arrWithStringArrayDelYnArrShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        String[] del_yn_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setDel_yn_arr(del_yn_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_id_arrWithStringArrayDelIdArrShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        String[] del_id_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setDel_id_arr(del_id_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_nm_arrWithStringArrayDelNmArrShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        String[] del_nm_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setDel_nm_arr(del_nm_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetChk_val_arrWithStringArrayChkValArrShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        String[] chk_val_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setChk_val_arr(chk_val_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetWork_flag_arrWithStringArrayWorkFlagArrShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        String[] work_flag_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setWork_flag_arr(work_flag_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_idShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDlay_seqnoShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        boolean isNotException = true;
        try {
            clazz.getDlay_seqno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetChgebfr_cnclsn_plnddayShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        boolean isNotException = true;
        try {
            clazz.getChgebfr_cnclsn_plndday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetChgeaft_cnclsn_plnddayShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        boolean isNotException = true;
        try {
            clazz.getChgeaft_cnclsn_plndday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDlay_causeShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        boolean isNotException = true;
        try {
            clazz.getDlay_cause();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_idShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
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
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        boolean isNotException = true;
        try {
            clazz.getReg_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_ynShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        boolean isNotException = true;
        try {
            clazz.getDel_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_idShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
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
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        boolean isNotException = true;
        try {
            clazz.getDel_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetChk_valShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        boolean isNotException = true;
        try {
            clazz.getChk_val();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetWork_flagShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        boolean isNotException = true;
        try {
            clazz.getWork_flag();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_id_arrShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_id_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDlay_seqno_arrShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        boolean isNotException = true;
        try {
            clazz.getDlay_seqno_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetChgebfr_cnclsn_plndday_arrShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        boolean isNotException = true;
        try {
            clazz.getChgebfr_cnclsn_plndday_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetChgeaft_cnclsn_plndday_arrShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        boolean isNotException = true;
        try {
            clazz.getChgeaft_cnclsn_plndday_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDlay_cause_arrShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        boolean isNotException = true;
        try {
            clazz.getDlay_cause_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_id_arrShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        boolean isNotException = true;
        try {
            clazz.getReg_id_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_nm_arrShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        boolean isNotException = true;
        try {
            clazz.getReg_nm_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_yn_arrShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        boolean isNotException = true;
        try {
            clazz.getDel_yn_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_id_arrShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        boolean isNotException = true;
        try {
            clazz.getDel_id_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_nm_arrShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        boolean isNotException = true;
        try {
            clazz.getDel_nm_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetChk_val_arrShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        boolean isNotException = true;
        try {
            clazz.getChk_val_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetWork_flag_arrShouldNotThrowException() {
        ConclusionDelayForm clazz = new ConclusionDelayForm();
        boolean isNotException = true;
        try {
            clazz.getWork_flag_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
