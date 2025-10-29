package com.sec.clm.manage.dto;

import java.lang.String;
import org.junit.Test;
import java.math.BigDecimal;
import com.sec.clm.manage.dto.ConsiderationForm;
import static org.mockito.Mockito.mock;

public class ConsultationFormTest {

    @Test(timeout = 20000)
    public void testGetArr_cntrt_idShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getArr_cntrt_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetArr_cntrt_idWithStringArrayArrCntrtIdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String[] arr_cntrt_id = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setArr_cntrt_id(arr_cntrt_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEn_cntrt_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getEn_cntrt_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEn_cntrt_nmWithStringEnCntrtNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String en_cntrt_nm = "en_cntrt_nm";
        boolean isNotException = true;
        try {
            clazz.setEn_cntrt_nm(en_cntrt_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRel_up_cntrt_idShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getRel_up_cntrt_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRel_up_cntrt_idWithStringRelUpCntrtIdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String rel_up_cntrt_id = "rel_up_cntrt_id";
        boolean isNotException = true;
        try {
            clazz.setRel_up_cntrt_id(rel_up_cntrt_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRel_ynShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getRel_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRel_ynWithStringRelYnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String rel_yn = "rel_yn";
        boolean isNotException = true;
        try {
            clazz.setRel_yn(rel_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExec_chk_arrShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getExec_chk_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAbbr_comp_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getAbbr_comp_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAbbr_comp_nmWithStringAbbrCompNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String abbr_comp_nm = "abbr_comp_nm";
        boolean isNotException = true;
        try {
            clazz.setAbbr_comp_nm(abbr_comp_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExec_chk_arrWithIntArrayExecChkArrShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        int[] exec_chk_arr = mock(int[].class);
        boolean isNotException = true;
        try {
            clazz.setExec_chk_arr(exec_chk_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExec_gbn_arrShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getExec_gbn_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExec_gbn_arrWithStringArrayExecGbnArrShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String[] exec_gbn_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setExec_gbn_arr(exec_gbn_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExec_itm_arrShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getExec_itm_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExec_itm_arrWithStringArrayExecItmArrShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String[] exec_itm_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setExec_itm_arr(exec_itm_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExec_amt_arrShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getExec_amt_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExec_amt_arrWithStringArrayExecAmtArrShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String[] exec_amt_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setExec_amt_arr(exec_amt_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExec_cmpltday_arrShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getExec_cmpltday_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExec_cmpltday_arrWithStringArrayExecCmpltdayArrShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String[] exec_cmpltday_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setExec_cmpltday_arr(exec_cmpltday_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNote_arrShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getNote_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNote_arrWithStringArrayNoteArrShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String[] note_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setNote_arr(note_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExec_gbnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getExec_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExec_gbnWithStringExecGbnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String exec_gbn = "exec_gbn";
        boolean isNotException = true;
        try {
            clazz.setExec_gbn(exec_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExec_itmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getExec_itm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExec_itmWithStringExecItmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String exec_itm = "exec_itm";
        boolean isNotException = true;
        try {
            clazz.setExec_itm(exec_itm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExec_amtShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getExec_amt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExec_amtWithBigDecimalExecAmtShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        BigDecimal exec_amt = mock(BigDecimal.class);
        boolean isNotException = true;
        try {
            clazz.setExec_amt(exec_amt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExec_cmpltdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getExec_cmpltday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExec_cmpltdayWithStringExecCmpltdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String exec_cmpltday = "exec_cmpltday";
        boolean isNotException = true;
        try {
            clazz.setExec_cmpltday(exec_cmpltday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNoteShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getNote();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNoteWithStringNoteShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String note = "note";
        boolean isNotException = true;
        try {
            clazz.setNote(note);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExec_seqnoShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getExec_seqno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExec_seqnoWithIntExecSeqnoShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        int exec_seqno = 0;
        boolean isNotException = true;
        try {
            clazz.setExec_seqno(exec_seqno);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExec_contShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getExec_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExec_contWithStringExecContShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String exec_cont = "exec_cont";
        boolean isNotException = true;
        try {
            clazz.setExec_cont(exec_cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExec_plnddayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getExec_plndday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExec_plnddayWithStringExecPlnddayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String exec_plndday = "exec_plndday";
        boolean isNotException = true;
        try {
            clazz.setExec_plndday(exec_plndday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExec_statusShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getExec_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExec_statusWithStringExecStatusShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String exec_status = "exec_status";
        boolean isNotException = true;
        try {
            clazz.setExec_status(exec_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExec_seqno_arrShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getExec_seqno_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExec_seqno_arrWithIntArrayExecSeqnoArrShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        int[] exec_seqno_arr = mock(int[].class);
        boolean isNotException = true;
        try {
            clazz.setExec_seqno_arr(exec_seqno_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExec_cont_arrShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getExec_cont_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExec_cont_arrWithStringArrayExecContArrShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String[] exec_cont_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setExec_cont_arr(exec_cont_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExec_plndday_arrShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getExec_plndday_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExec_plndday_arrWithStringArrayExecPlnddayArrShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String[] exec_plndday_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setExec_plndday_arr(exec_plndday_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExec_status_arrShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getExec_status_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExec_status_arrWithStringArrayExecStatusArrShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String[] exec_status_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setExec_status_arr(exec_status_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_yn_arrShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getDel_yn_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_yn_arrWithStringArrayDelYnArrShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
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
    public void testGetSeal_ffmt_dept_cdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSeal_ffmt_dept_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_ffmt_dept_cdWithStringSealFfmtDeptCdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String seal_ffmt_dept_cd = "seal_ffmt_dept_cd";
        boolean isNotException = true;
        try {
            clazz.setSeal_ffmt_dept_cd(seal_ffmt_dept_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRegNotiday_beforeShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getRegNotiday_before();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRegNotiday_beforeWithStringRegNotidayBeforeShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String regNotiday_before = "regNotiday_before";
        boolean isNotException = true;
        try {
            clazz.setRegNotiday_before(regNotiday_before);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRegExprt_notidayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getRegExprt_notiday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRegExprt_notidayWithStringRegExprtNotidayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String regExprt_notiday = "regExprt_notiday";
        boolean isNotException = true;
        try {
            clazz.setRegExprt_notiday(regExprt_notiday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRegSeal_ffmtman_idShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getRegSeal_ffmtman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRegSeal_ffmtman_idWithStringRegSealFfmtmanIdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String regSeal_ffmtman_id = "regSeal_ffmtman_id";
        boolean isNotException = true;
        try {
            clazz.setRegSeal_ffmtman_id(regSeal_ffmtman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRegSeal_ffmtman_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getRegSeal_ffmtman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRegSeal_ffmtman_nmWithStringRegSealFfmtmanNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String regSeal_ffmtman_nm = "regSeal_ffmtman_nm";
        boolean isNotException = true;
        try {
            clazz.setRegSeal_ffmtman_nm(regSeal_ffmtman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRegSeal_ffmt_dept_cdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getRegSeal_ffmt_dept_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRegSeal_ffmt_dept_cdWithStringRegSealFfmtDeptCdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String regSeal_ffmt_dept_cd = "regSeal_ffmt_dept_cd";
        boolean isNotException = true;
        try {
            clazz.setRegSeal_ffmt_dept_cd(regSeal_ffmt_dept_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRegSeal_ffmt_dept_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getRegSeal_ffmt_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRegSeal_ffmt_dept_nmWithStringRegSealFfmtDeptNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String regSeal_ffmt_dept_nm = "regSeal_ffmt_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setRegSeal_ffmt_dept_nm(regSeal_ffmt_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRegSeal_ffmtman_jikgup_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getRegSeal_ffmtman_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRegSeal_ffmtman_jikgup_nmWithStringRegSealFfmtmanJikgupNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String regSeal_ffmtman_jikgup_nm = "regSeal_ffmtman_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setRegSeal_ffmtman_jikgup_nm(regSeal_ffmtman_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPageGbnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getPageGbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPageGbnWithStringPageGbnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String pageGbn = "pageGbn";
        boolean isNotException = true;
        try {
            clazz.setPageGbn(pageGbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_validateShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getFile_validate();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFile_validateWithStringFileValidateShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String file_validate = "file_validate";
        boolean isNotException = true;
        try {
            clazz.setFile_validate(file_validate);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReq_status_titleShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getReq_status_title();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReq_status_titleWithStringReqStatusTitleShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String req_status_title = "req_status_title";
        boolean isNotException = true;
        try {
            clazz.setReq_status_title(req_status_title);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBiz_clsfcn_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getBiz_clsfcn_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBiz_clsfcn_nmWithStringBizClsfcnNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String biz_clsfcn_nm = "biz_clsfcn_nm";
        boolean isNotException = true;
        try {
            clazz.setBiz_clsfcn_nm(biz_clsfcn_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDepth_clsfcn_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getDepth_clsfcn_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDepth_clsfcn_nmWithStringDepthClsfcnNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String depth_clsfcn_nm = "depth_clsfcn_nm";
        boolean isNotException = true;
        try {
            clazz.setDepth_clsfcn_nm(depth_clsfcn_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnclsnpurps_bigclsfcn_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCnclsnpurps_bigclsfcn_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnclsnpurps_bigclsfcn_nmWithStringCnclsnpurpsBigclsfcnNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cnclsnpurps_bigclsfcn_nm = "cnclsnpurps_bigclsfcn_nm";
        boolean isNotException = true;
        try {
            clazz.setCnclsnpurps_bigclsfcn_nm(cnclsnpurps_bigclsfcn_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnclsnpurps_midclsfcn_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCnclsnpurps_midclsfcn_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnclsnpurps_midclsfcn_nmWithStringCnclsnpurpsMidclsfcnNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cnclsnpurps_midclsfcn_nm = "cnclsnpurps_midclsfcn_nm";
        boolean isNotException = true;
        try {
            clazz.setCnclsnpurps_midclsfcn_nm(cnclsnpurps_midclsfcn_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRel_type1ShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getRel_type1();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRel_type1WithStringRelType1ShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String rel_type1 = "rel_type1";
        boolean isNotException = true;
        try {
            clazz.setRel_type1(rel_type1);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTmp_cntrt_oppnt_cdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getTmp_cntrt_oppnt_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTmp_cntrt_oppnt_cdWithStringTmpCntrtOppntCdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String tmp_cntrt_oppnt_cd = "tmp_cntrt_oppnt_cd";
        boolean isNotException = true;
        try {
            clazz.setTmp_cntrt_oppnt_cd(tmp_cntrt_oppnt_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTmp_region_gbnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getTmp_region_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTmp_region_gbnWithStringTmpRegionGbnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String tmp_region_gbn = "tmp_region_gbn";
        boolean isNotException = true;
        try {
            clazz.setTmp_region_gbn(tmp_region_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTmp_cntrt_oppnt_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getTmp_cntrt_oppnt_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTmp_cntrt_oppnt_nmWithStringTmpCntrtOppntNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String tmp_cntrt_oppnt_nm = "tmp_cntrt_oppnt_nm";
        boolean isNotException = true;
        try {
            clazz.setTmp_cntrt_oppnt_nm(tmp_cntrt_oppnt_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTmp_cntrt_oppnt_rprsntmanShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getTmp_cntrt_oppnt_rprsntman();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTmp_cntrt_oppnt_rprsntmanWithStringTmpCntrtOppntRprsntmanShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String tmp_cntrt_oppnt_rprsntman = "tmp_cntrt_oppnt_rprsntman";
        boolean isNotException = true;
        try {
            clazz.setTmp_cntrt_oppnt_rprsntman(tmp_cntrt_oppnt_rprsntman);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTmp_cntrt_oppnt_typeShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getTmp_cntrt_oppnt_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTmp_cntrt_oppnt_typeWithStringTmpCntrtOppntTypeShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String tmp_cntrt_oppnt_type = "tmp_cntrt_oppnt_type";
        boolean isNotException = true;
        try {
            clazz.setTmp_cntrt_oppnt_type(tmp_cntrt_oppnt_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTmp_cntrt_oppnt_respmanShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getTmp_cntrt_oppnt_respman();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTmp_cntrt_oppnt_respmanWithStringTmpCntrtOppntRespmanShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String tmp_cntrt_oppnt_respman = "tmp_cntrt_oppnt_respman";
        boolean isNotException = true;
        try {
            clazz.setTmp_cntrt_oppnt_respman(tmp_cntrt_oppnt_respman);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTmp_cntrt_oppnt_telnoShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getTmp_cntrt_oppnt_telno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTmp_cntrt_oppnt_telnoWithStringTmpCntrtOppntTelnoShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String tmp_cntrt_oppnt_telno = "tmp_cntrt_oppnt_telno";
        boolean isNotException = true;
        try {
            clazz.setTmp_cntrt_oppnt_telno(tmp_cntrt_oppnt_telno);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTmp_cntrt_oppnt_emailShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getTmp_cntrt_oppnt_email();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTmp_cntrt_oppnt_emailWithStringTmpCntrtOppntEmailShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String tmp_cntrt_oppnt_email = "tmp_cntrt_oppnt_email";
        boolean isNotException = true;
        try {
            clazz.setTmp_cntrt_oppnt_email(tmp_cntrt_oppnt_email);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTab_cntWithStringTabCntShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String tab_cnt = "tab_cnt";
        boolean isNotException = true;
        try {
            clazz.setTab_cnt(tab_cnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTab_cntShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getTab_cnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnsdman_idWithStringCnsdmanIdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cnsdman_id = "cnsdman_id";
        boolean isNotException = true;
        try {
            clazz.setCnsdman_id(cnsdman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnsdman_idShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCnsdman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnsdman_nmWithStringCnsdmanNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cnsdman_nm = "cnsdman_nm";
        boolean isNotException = true;
        try {
            clazz.setCnsdman_nm(cnsdman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnsdman_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCnsdman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnsDayWithStringCnsDayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cnsDay = "cnsDay";
        boolean isNotException = true;
        try {
            clazz.setCnsDay(cnsDay);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnsDayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCnsDay();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnsd_opnnWithStringCnsdOpnnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cnsd_opnn = "cnsd_opnn";
        boolean isNotException = true;
        try {
            clazz.setCnsd_opnn(cnsd_opnn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnsd_opnnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCnsd_opnn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetApbtman_idWithStringApbtmanIdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String apbtman_id = "apbtman_id";
        boolean isNotException = true;
        try {
            clazz.setApbtman_id(apbtman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetApbtman_idShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getApbtman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetApbtman_nmWithStringApbtmanNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String apbtman_nm = "apbtman_nm";
        boolean isNotException = true;
        try {
            clazz.setApbtman_nm(apbtman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetApbtman_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getApbtman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetApbtDayWithStringApbtDayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String apbtDay = "apbtDay";
        boolean isNotException = true;
        try {
            clazz.setApbtDay(apbtDay);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetApbtDayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getApbtDay();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetApbt_opnnWithStringApbtOpnnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String apbt_opnn = "apbt_opnn";
        boolean isNotException = true;
        try {
            clazz.setApbt_opnn(apbt_opnn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetApbt_opnnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getApbt_opnn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnsd_statusWithStringCnsdStatusShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cnsd_status = "cnsd_status";
        boolean isNotException = true;
        try {
            clazz.setCnsd_status(cnsd_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnsd_statusShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCnsd_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRiskWithStringRiskShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String risk = "risk";
        boolean isNotException = true;
        try {
            clazz.setRisk(risk);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRiskShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getRisk();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetArr_parent_cntrt_idWithStringArrayArrParentCntrtIdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String[] arr_parent_cntrt_id = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setArr_parent_cntrt_id(arr_parent_cntrt_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetArr_parent_cntrt_idShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getArr_parent_cntrt_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetParent_cntrt_idWithStringParentCntrtIdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String parent_cntrt_id = "parent_cntrt_id";
        boolean isNotException = true;
        try {
            clazz.setParent_cntrt_id(parent_cntrt_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetParent_cntrt_idShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getParent_cntrt_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetArr_rel_typeWithStringArrayArrRelTypeShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String[] arr_rel_type = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setArr_rel_type(arr_rel_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetArr_rel_typeShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getArr_rel_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRel_typeWithStringRelTypeShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String rel_type = "rel_type";
        boolean isNotException = true;
        try {
            clazz.setRel_type(rel_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRel_typeShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getRel_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetArr_explWithStringArrayArrExplShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String[] arr_expl = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setArr_expl(arr_expl);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetArr_explShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getArr_expl();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExplWithStringExplShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String expl = "expl";
        boolean isNotException = true;
        try {
            clazz.setExpl(expl);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExplShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getExpl();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetArr_rel_defnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getArr_rel_defn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetArr_rel_defnWithStringArrayArrRelDefnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String[] arr_rel_defn = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setArr_rel_defn(arr_rel_defn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRel_defnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getRel_defn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRel_defnWithStringRelDefnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String rel_defn = "rel_defn";
        boolean isNotException = true;
        try {
            clazz.setRel_defn(rel_defn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAttr_frmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getAttr_frm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_oppnt_nationWithStringCntrtOppntNationShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_oppnt_nation = "cntrt_oppnt_nation";
        boolean isNotException = true;
        try {
            clazz.setCntrt_oppnt_nation(cntrt_oppnt_nation);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAttr_seqnoWithStringAttrSeqnoShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String attr_seqno = "attr_seqno";
        boolean isNotException = true;
        try {
            clazz.setAttr_seqno(attr_seqno);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAttr_seqnoShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getAttr_seqno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAttr_nmWithStringAttrNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String attr_nm = "attr_nm";
        boolean isNotException = true;
        try {
            clazz.setAttr_nm(attr_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAttr_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getAttr_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAttr_nm_engWithStringAttrNmEngShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String attr_nm_eng = "attr_nm_eng";
        boolean isNotException = true;
        try {
            clazz.setAttr_nm_eng(attr_nm_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAttr_nm_engShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getAttr_nm_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAttr_explWithStringAttrExplShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String attr_expl = "attr_expl";
        boolean isNotException = true;
        try {
            clazz.setAttr_expl(attr_expl);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAttr_explShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getAttr_expl();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAttr_frmWithStringAttrFrmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String attr_frm = "attr_frm";
        boolean isNotException = true;
        try {
            clazz.setAttr_frm(attr_frm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAtr_frmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getAtr_frm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAttr_sz_wdthWithStringAttrSzWdthShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String attr_sz_wdth = "attr_sz_wdth";
        boolean isNotException = true;
        try {
            clazz.setAttr_sz_wdth(attr_sz_wdth);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAttr_sz_wdthShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getAttr_sz_wdth();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAttr_sz_hghtWithStringAttrSzHghtShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String attr_sz_hght = "attr_sz_hght";
        boolean isNotException = true;
        try {
            clazz.setAttr_sz_hght(attr_sz_hght);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAttr_sz_hghtShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getAttr_sz_hght();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAttr_max_szWithStringAttrMaxSzShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String attr_max_sz = "attr_max_sz";
        boolean isNotException = true;
        try {
            clazz.setAttr_max_sz(attr_max_sz);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAttr_max_szShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getAttr_max_sz();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMndtry_ynWithStringMndtryYnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String mndtry_yn = "mndtry_yn";
        boolean isNotException = true;
        try {
            clazz.setMndtry_yn(mndtry_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMndtry_ynShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getMndtry_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCd_ynWithStringCdYnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cd_yn = "cd_yn";
        boolean isNotException = true;
        try {
            clazz.setCd_yn(cd_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCd_ynShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCd_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCd_sysWithStringCdSysShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cd_sys = "cd_sys";
        boolean isNotException = true;
        try {
            clazz.setCd_sys(cd_sys);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCd_sysShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCd_sys();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCd_grpWithStringCdGrpShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cd_grp = "cd_grp";
        boolean isNotException = true;
        try {
            clazz.setCd_grp(cd_grp);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCd_grpShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCd_grp();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPrnt_srtWithStringPrntSrtShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String prnt_srt = "prnt_srt";
        boolean isNotException = true;
        try {
            clazz.setPrnt_srt(prnt_srt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPrnt_srtShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getPrnt_srt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAttr_cdWithStringAttrCdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String attr_cd = "attr_cd";
        boolean isNotException = true;
        try {
            clazz.setAttr_cd(attr_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAttr_cdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getAttr_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAttr_contWithStringAttrContShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String attr_cont = "attr_cont";
        boolean isNotException = true;
        try {
            clazz.setAttr_cont(attr_cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAttr_contShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getAttr_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDemnd_seqnoWithStringDemndSeqnoShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String demnd_seqno = "demnd_seqno";
        boolean isNotException = true;
        try {
            clazz.setDemnd_seqno(demnd_seqno);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDemndman_idWithStringDemndmanIdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String demndman_id = "demndman_id";
        boolean isNotException = true;
        try {
            clazz.setDemndman_id(demndman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDemndman_nmWithStringDemndmanNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String demndman_nm = "demndman_nm";
        boolean isNotException = true;
        try {
            clazz.setDemndman_nm(demndman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDemndman_dept_nmWithStringDemndmanDeptNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String demndman_dept_nm = "demndman_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setDemndman_dept_nm(demndman_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTrgtman_idWithStringTrgtmanIdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String trgtman_id = "trgtman_id";
        boolean isNotException = true;
        try {
            clazz.setTrgtman_id(trgtman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetArr_trgtman_idWithStringArrayArrTrgtmanIdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String[] arr_trgtman_id = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setArr_trgtman_id(arr_trgtman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTrgtman_nmWithStringTrgtmanNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String trgtman_nm = "trgtman_nm";
        boolean isNotException = true;
        try {
            clazz.setTrgtman_nm(trgtman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetArr_trgtman_nmWithStringArrayArrTrgtmanNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String[] arr_trgtman_nm = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setArr_trgtman_nm(arr_trgtman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTrgtman_dept_nmWithStringTrgtmanDeptNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String trgtman_dept_nm = "trgtman_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setTrgtman_dept_nm(trgtman_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetArr_trgtman_dept_nmWithStringArrayArrTrgtmanDeptNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String[] arr_trgtman_dept_nm = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setArr_trgtman_dept_nm(arr_trgtman_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRd_authWithStringRdAuthShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
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
    public void testSetAuth_startdayWithStringAuthStartdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String auth_startday = "auth_startday";
        boolean isNotException = true;
        try {
            clazz.setAuth_startday(auth_startday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_enddayWithStringAuthEnddayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String auth_endday = "auth_endday";
        boolean isNotException = true;
        try {
            clazz.setAuth_endday(auth_endday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDemnd_statusWithStringDemndStatusShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String demnd_status = "demnd_status";
        boolean isNotException = true;
        try {
            clazz.setDemnd_status(demnd_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDemnd_dtWithStringDemndDtShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String demnd_dt = "demnd_dt";
        boolean isNotException = true;
        try {
            clazz.setDemnd_dt(demnd_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHndl_dtWithStringHndlDtShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String hndl_dt = "hndl_dt";
        boolean isNotException = true;
        try {
            clazz.setHndl_dt(hndl_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHndlman_idWithStringHndlmanIdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String hndlman_id = "hndlman_id";
        boolean isNotException = true;
        try {
            clazz.setHndlman_id(hndlman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHndlman_nmWithStringHndlmanNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String hndlman_nm = "hndlman_nm";
        boolean isNotException = true;
        try {
            clazz.setHndlman_nm(hndlman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHndl_contWithStringHndlContShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String hndl_cont = "hndl_cont";
        boolean isNotException = true;
        try {
            clazz.setHndl_cont(hndl_cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDemnd_seqnoShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getDemnd_seqno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDemndman_idShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getDemndman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDemndman_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getDemndman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDemndman_dept_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getDemndman_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTrgtman_idShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getTrgtman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetArr_trgtman_idShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getArr_trgtman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTrgtman_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getTrgtman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetArr_trgtman_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getArr_trgtman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTrgtman_dept_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getTrgtman_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetArr_trgtman_dept_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getArr_trgtman_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRd_authShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getRd_auth();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAuth_startdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getAuth_startday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAuth_enddayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getAuth_endday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDemnd_statusShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getDemnd_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDemnd_dtShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getDemnd_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHndl_dtShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getHndl_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHndlman_idShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getHndlman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHndlman_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getHndlman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHndl_contShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getHndl_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_idWithStringCntrtIdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
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
    public void testSetCntrt_nmWithStringCntrtNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_nm = "cntrt_nm";
        boolean isNotException = true;
        try {
            clazz.setCntrt_nm(cntrt_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRegion_gbnWithStringRegionGbnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String region_gbn = "region_gbn";
        boolean isNotException = true;
        try {
            clazz.setRegion_gbn(region_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBiz_clsfcnWithStringBizClsfcnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String biz_clsfcn = "biz_clsfcn";
        boolean isNotException = true;
        try {
            clazz.setBiz_clsfcn(biz_clsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDepth_clsfcnWithStringDepthClsfcnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String depth_clsfcn = "depth_clsfcn";
        boolean isNotException = true;
        try {
            clazz.setDepth_clsfcn(depth_clsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnclsnpurps_bigclsfcnWithStringCnclsnpurpsBigclsfcnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cnclsnpurps_bigclsfcn = "cnclsnpurps_bigclsfcn";
        boolean isNotException = true;
        try {
            clazz.setCnclsnpurps_bigclsfcn(cnclsnpurps_bigclsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnclsnpurps_midclsfcnWithStringCnclsnpurpsMidclsfcnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cnclsnpurps_midclsfcn = "cnclsnpurps_midclsfcn";
        boolean isNotException = true;
        try {
            clazz.setCnclsnpurps_midclsfcn(cnclsnpurps_midclsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnclsnpurps_midclsfcn_etcWithStringCnclsnpurpsMidclsfcnEtcShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cnclsnpurps_midclsfcn_etc = "cnclsnpurps_midclsfcn_etc";
        boolean isNotException = true;
        try {
            clazz.setCnclsnpurps_midclsfcn_etc(cnclsnpurps_midclsfcn_etc);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_trgtWithStringCntrtTrgtShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_trgt = "cntrt_trgt";
        boolean isNotException = true;
        try {
            clazz.setCntrt_trgt(cntrt_trgt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_trgt_detWithStringCntrtTrgtDetShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_trgt_det = "cntrt_trgt_det";
        boolean isNotException = true;
        try {
            clazz.setCntrt_trgt_det(cntrt_trgt_det);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPshdbkgrnd_purpsWithStringPshdbkgrndPurpsShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String pshdbkgrnd_purps = "pshdbkgrnd_purps";
        boolean isNotException = true;
        try {
            clazz.setPshdbkgrnd_purps(pshdbkgrnd_purps);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPayment_gbnWithStringPaymentGbnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String payment_gbn = "payment_gbn";
        boolean isNotException = true;
        try {
            clazz.setPayment_gbn(payment_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAntcptnefctWithStringAntcptnefctShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String antcptnefct = "antcptnefct";
        boolean isNotException = true;
        try {
            clazz.setAntcptnefct(antcptnefct);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrtperiod_startdayWithStringCntrtperiodStartdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrtperiod_startday = "cntrtperiod_startday";
        boolean isNotException = true;
        try {
            clazz.setCntrtperiod_startday(cntrtperiod_startday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrtperiod_enddayWithStringCntrtperiodEnddayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrtperiod_endday = "cntrtperiod_endday";
        boolean isNotException = true;
        try {
            clazz.setCntrtperiod_endday(cntrtperiod_endday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSecret_keepperiodWithStringSecretKeepperiodShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String secret_keepperiod = "secret_keepperiod";
        boolean isNotException = true;
        try {
            clazz.setSecret_keepperiod(secret_keepperiod);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_untprcWithStringCntrtUntprcShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_untprc = "cntrt_untprc";
        boolean isNotException = true;
        try {
            clazz.setCntrt_untprc(cntrt_untprc);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_amtWithStringCntrtAmtShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_amt = "cntrt_amt";
        boolean isNotException = true;
        try {
            clazz.setCntrt_amt(cntrt_amt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCrrncy_unitWithStringCrrncyUnitShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String crrncy_unit = "crrncy_unit";
        boolean isNotException = true;
        try {
            clazz.setCrrncy_unit(crrncy_unit);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPayment_condWithStringPaymentCondShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String payment_cond = "payment_cond";
        boolean isNotException = true;
        try {
            clazz.setPayment_cond(payment_cond);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuto_rnew_ynWithStringAutoRnewYnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String auto_rnew_yn = "auto_rnew_yn";
        boolean isNotException = true;
        try {
            clazz.setAuto_rnew_yn(auto_rnew_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExprt_notidayWithStringExprtNotidayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String exprt_notiday = "exprt_notiday";
        boolean isNotException = true;
        try {
            clazz.setExprt_notiday(exprt_notiday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOblgt_lmtWithStringOblgtLmtShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String oblgt_lmt = "oblgt_lmt";
        boolean isNotException = true;
        try {
            clazz.setOblgt_lmt(oblgt_lmt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSpcl_condWithStringSpclCondShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String spcl_cond = "spcl_cond";
        boolean isNotException = true;
        try {
            clazz.setSpcl_cond(spcl_cond);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnclsn_plnddayWithStringCnclsnPlnddayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cnclsn_plndday = "cnclsn_plndday";
        boolean isNotException = true;
        try {
            clazz.setCnclsn_plndday(cnclsn_plndday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_respman_idWithStringCntrtRespmanIdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_respman_id = "cntrt_respman_id";
        boolean isNotException = true;
        try {
            clazz.setCntrt_respman_id(cntrt_respman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_respman_nmWithStringCntrtRespmanNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_respman_nm = "cntrt_respman_nm";
        boolean isNotException = true;
        try {
            clazz.setCntrt_respman_nm(cntrt_respman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_oppnt_cdWithStringCntrtOppntCdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_oppnt_cd = "cntrt_oppnt_cd";
        boolean isNotException = true;
        try {
            clazz.setCntrt_oppnt_cd(cntrt_oppnt_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_oppnt_nationWithStringCntrtOppntNationShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_oppnt_nation = "cntrt_oppnt_nation";
        boolean isNotException = true;
        try {
            clazz.getCntrt_oppnt_nation(cntrt_oppnt_nation);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSign_plndman_idWithStringSignPlndmanIdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String sign_plndman_id = "sign_plndman_id";
        boolean isNotException = true;
        try {
            clazz.setSign_plndman_id(sign_plndman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSign_plndman_nmWithStringSignPlndmanNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String sign_plndman_nm = "sign_plndman_nm";
        boolean isNotException = true;
        try {
            clazz.setSign_plndman_nm(sign_plndman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_mthdWithStringSealMthdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String seal_mthd = "seal_mthd";
        boolean isNotException = true;
        try {
            clazz.setSeal_mthd(seal_mthd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_cnclsn_ynWithStringCntrtCnclsnYnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_cnclsn_yn = "cntrt_cnclsn_yn";
        boolean isNotException = true;
        try {
            clazz.setCntrt_cnclsn_yn(cntrt_cnclsn_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSignman_idWithStringSignmanIdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String signman_id = "signman_id";
        boolean isNotException = true;
        try {
            clazz.setSignman_id(signman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSignman_nmWithStringSignmanNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String signman_nm = "signman_nm";
        boolean isNotException = true;
        try {
            clazz.setSignman_nm(signman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOppnt_signman_nmWithStringOppntSignmanNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String oppnt_signman_nm = "oppnt_signman_nm";
        boolean isNotException = true;
        try {
            clazz.setOppnt_signman_nm(oppnt_signman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOppnt_signman_jikgupWithStringOppntSignmanJikgupShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String oppnt_signman_jikgup = "oppnt_signman_jikgup";
        boolean isNotException = true;
        try {
            clazz.setOppnt_signman_jikgup(oppnt_signman_jikgup);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOppnt_signman_deptWithStringOppntSignmanDeptShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String oppnt_signman_dept = "oppnt_signman_dept";
        boolean isNotException = true;
        try {
            clazz.setOppnt_signman_dept(oppnt_signman_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOppnt_signdayWithStringOppntSigndayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String oppnt_signday = "oppnt_signday";
        boolean isNotException = true;
        try {
            clazz.setOppnt_signday(oppnt_signday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_cnclsndayWithStringCntrtCnclsndayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_cnclsnday = "cntrt_cnclsnday";
        boolean isNotException = true;
        try {
            clazz.setCntrt_cnclsnday(cntrt_cnclsnday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCpy_regdayWithStringCpyRegdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cpy_regday = "cpy_regday";
        boolean isNotException = true;
        try {
            clazz.setCpy_regday(cpy_regday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_noWithStringCntrtNoShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_no = "cntrt_no";
        boolean isNotException = true;
        try {
            clazz.setCntrt_no(cntrt_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLoacWithStringLoacShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String loac = "loac";
        boolean isNotException = true;
        try {
            clazz.setLoac(loac);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDspt_resolt_mthdWithStringDsptResoltMthdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String dspt_resolt_mthd = "dspt_resolt_mthd";
        boolean isNotException = true;
        try {
            clazz.setDspt_resolt_mthd(dspt_resolt_mthd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDspt_resolt_mthd_detWithStringDsptResoltMthdDetShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String dspt_resolt_mthd_det = "dspt_resolt_mthd_det";
        boolean isNotException = true;
        try {
            clazz.setDspt_resolt_mthd_det(dspt_resolt_mthd_det);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOrg_acpt_dlay_causeWithStringOrgAcptDlayCauseShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String org_acpt_dlay_cause = "org_acpt_dlay_cause";
        boolean isNotException = true;
        try {
            clazz.setOrg_acpt_dlay_cause(org_acpt_dlay_cause);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOrg_acptdayWithStringOrgAcptdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String org_acptday = "org_acptday";
        boolean isNotException = true;
        try {
            clazz.setOrg_acptday(org_acptday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOrg_tkovman_idWithStringOrgTkovmanIdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String org_tkovman_id = "org_tkovman_id";
        boolean isNotException = true;
        try {
            clazz.setOrg_tkovman_id(org_tkovman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOrg_tkovman_nmWithStringOrgTkovmanNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String org_tkovman_nm = "org_tkovman_nm";
        boolean isNotException = true;
        try {
            clazz.setOrg_tkovman_nm(org_tkovman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOrg_hdovman_idWithStringOrgHdovmanIdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String org_hdovman_id = "org_hdovman_id";
        boolean isNotException = true;
        try {
            clazz.setOrg_hdovman_id(org_hdovman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOrg_hdovman_nmWithStringOrgHdovmanNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String org_hdovman_nm = "org_hdovman_nm";
        boolean isNotException = true;
        try {
            clazz.setOrg_hdovman_nm(org_hdovman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOrg_strg_posWithStringOrgStrgPosShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String org_strg_pos = "org_strg_pos";
        boolean isNotException = true;
        try {
            clazz.setOrg_strg_pos(org_strg_pos);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPrcs_depthWithStringPrcsDepthShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String prcs_depth = "prcs_depth";
        boolean isNotException = true;
        try {
            clazz.setPrcs_depth(prcs_depth);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDepth_statusWithStringDepthStatusShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String depth_status = "depth_status";
        boolean isNotException = true;
        try {
            clazz.setDepth_status(depth_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_statusWithStringCntrtStatusShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_status = "cntrt_status";
        boolean isNotException = true;
        try {
            clazz.setCntrt_status(cntrt_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_dtWithStringRegDtShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
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
        ConsultationForm clazz = new ConsultationForm();
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
        ConsultationForm clazz = new ConsultationForm();
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
        ConsultationForm clazz = new ConsultationForm();
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
        ConsultationForm clazz = new ConsultationForm();
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
        ConsultationForm clazz = new ConsultationForm();
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
        ConsultationForm clazz = new ConsultationForm();
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
        ConsultationForm clazz = new ConsultationForm();
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
        ConsultationForm clazz = new ConsultationForm();
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
        ConsultationForm clazz = new ConsultationForm();
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
    public void testSetLoac_etcWithStringLoacEtcShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String loac_etc = "loac_etc";
        boolean isNotException = true;
        try {
            clazz.setLoac_etc(loac_etc);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_chge_demnddayWithStringCntrtChgeDemnddayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_chge_demndday = "cntrt_chge_demndday";
        boolean isNotException = true;
        try {
            clazz.setCntrt_chge_demndday(cntrt_chge_demndday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_chge_demndman_idWithStringCntrtChgeDemndmanIdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_chge_demndman_id = "cntrt_chge_demndman_id";
        boolean isNotException = true;
        try {
            clazz.setCntrt_chge_demndman_id(cntrt_chge_demndman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_chge_demndman_nmWithStringCntrtChgeDemndmanNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_chge_demndman_nm = "cntrt_chge_demndman_nm";
        boolean isNotException = true;
        try {
            clazz.setCntrt_chge_demndman_nm(cntrt_chge_demndman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_chge_demnd_causeWithStringCntrtChgeDemndCauseShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_chge_demnd_cause = "cntrt_chge_demnd_cause";
        boolean isNotException = true;
        try {
            clazz.setCntrt_chge_demnd_cause(cntrt_chge_demnd_cause);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_chge_plnddayWithStringCntrtChgePlnddayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_chge_plndday = "cntrt_chge_plndday";
        boolean isNotException = true;
        try {
            clazz.setCntrt_chge_plndday(cntrt_chge_plndday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_chge_conf_ynWithStringCntrtChgeConfYnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_chge_conf_yn = "cntrt_chge_conf_yn";
        boolean isNotException = true;
        try {
            clazz.setCntrt_chge_conf_yn(cntrt_chge_conf_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_chge_confdayWithStringCntrtChgeConfdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_chge_confday = "cntrt_chge_confday";
        boolean isNotException = true;
        try {
            clazz.setCntrt_chge_confday(cntrt_chge_confday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_chge_confman_idWithStringCntrtChgeConfmanIdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_chge_confman_id = "cntrt_chge_confman_id";
        boolean isNotException = true;
        try {
            clazz.setCntrt_chge_confman_id(cntrt_chge_confman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_chge_confman_nmWithStringCntrtChgeConfmanNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_chge_confman_nm = "cntrt_chge_confman_nm";
        boolean isNotException = true;
        try {
            clazz.setCntrt_chge_confman_nm(cntrt_chge_confman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBfhdcstn_mtnman_idWithStringBfhdcstnMtnmanIdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String bfhdcstn_mtnman_id = "bfhdcstn_mtnman_id";
        boolean isNotException = true;
        try {
            clazz.setBfhdcstn_mtnman_id(bfhdcstn_mtnman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBfhdcstn_mtnman_nmWithStringBfhdcstnMtnmanNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String bfhdcstn_mtnman_nm = "bfhdcstn_mtnman_nm";
        boolean isNotException = true;
        try {
            clazz.setBfhdcstn_mtnman_nm(bfhdcstn_mtnman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBfhdcstn_mtnman_jikgup_nmWithStringBfhdcstnMtnmanJikgupNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String bfhdcstn_mtnman_jikgup_nm = "bfhdcstn_mtnman_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setBfhdcstn_mtnman_jikgup_nm(bfhdcstn_mtnman_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBfhdcstn_mtn_dept_nmWithStringBfhdcstnMtnDeptNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String bfhdcstn_mtn_dept_nm = "bfhdcstn_mtn_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setBfhdcstn_mtn_dept_nm(bfhdcstn_mtn_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBfhdcstn_mtnman_emailWithStringBfhdcstnMtnmanEmailShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String bfhdcstn_mtnman_email = "bfhdcstn_mtnman_email";
        boolean isNotException = true;
        try {
            clazz.setBfhdcstn_mtnman_email(bfhdcstn_mtnman_email);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBfhdcstn_apbt_mthdWithStringBfhdcstnApbtMthdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String bfhdcstn_apbt_mthd = "bfhdcstn_apbt_mthd";
        boolean isNotException = true;
        try {
            clazz.setBfhdcstn_apbt_mthd(bfhdcstn_apbt_mthd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBfhdcstn_apbtman_idWithStringBfhdcstnApbtmanIdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String bfhdcstn_apbtman_id = "bfhdcstn_apbtman_id";
        boolean isNotException = true;
        try {
            clazz.setBfhdcstn_apbtman_id(bfhdcstn_apbtman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBfhdcstn_apbtman_nmWithStringBfhdcstnApbtmanNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String bfhdcstn_apbtman_nm = "bfhdcstn_apbtman_nm";
        boolean isNotException = true;
        try {
            clazz.setBfhdcstn_apbtman_nm(bfhdcstn_apbtman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBfhdcstn_apbtman_jikgup_nmWithStringBfhdcstnApbtmanJikgupNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String bfhdcstn_apbtman_jikgup_nm = "bfhdcstn_apbtman_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setBfhdcstn_apbtman_jikgup_nm(bfhdcstn_apbtman_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBfhdcstn_apbt_dept_nmWithStringBfhdcstnApbtDeptNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String bfhdcstn_apbt_dept_nm = "bfhdcstn_apbt_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setBfhdcstn_apbt_dept_nm(bfhdcstn_apbt_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBfhdcstn_apbtdayWithStringBfhdcstnApbtdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String bfhdcstn_apbtday = "bfhdcstn_apbtday";
        boolean isNotException = true;
        try {
            clazz.setBfhdcstn_apbtday(bfhdcstn_apbtday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_rqstdayWithStringSealRqstdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String seal_rqstday = "seal_rqstday";
        boolean isNotException = true;
        try {
            clazz.setSeal_rqstday(seal_rqstday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_rqstman_idWithStringSealRqstmanIdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String seal_rqstman_id = "seal_rqstman_id";
        boolean isNotException = true;
        try {
            clazz.setSeal_rqstman_id(seal_rqstman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_rqstman_nmWithStringSealRqstmanNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String seal_rqstman_nm = "seal_rqstman_nm";
        boolean isNotException = true;
        try {
            clazz.setSeal_rqstman_nm(seal_rqstman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_ffmtman_idWithStringSealFfmtmanIdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String seal_ffmtman_id = "seal_ffmtman_id";
        boolean isNotException = true;
        try {
            clazz.setSeal_ffmtman_id(seal_ffmtman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_ffmtman_nmWithStringSealFfmtmanNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String seal_ffmtman_nm = "seal_ffmtman_nm";
        boolean isNotException = true;
        try {
            clazz.setSeal_ffmtman_nm(seal_ffmtman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_ffmtdayWithStringSealFfmtdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String seal_ffmtday = "seal_ffmtday";
        boolean isNotException = true;
        try {
            clazz.setSeal_ffmtday(seal_ffmtday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_ffmt_dept_nmWithStringSealFfmtDeptNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String seal_ffmt_dept_nm = "seal_ffmt_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setSeal_ffmt_dept_nm(seal_ffmt_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_ffmtman_jikgup_nmWithStringSealFfmtmanJikgupNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String seal_ffmtman_jikgup_nm = "seal_ffmtman_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setSeal_ffmtman_jikgup_nm(seal_ffmtman_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_operdivWithStringRegOperdivShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String reg_operdiv = "reg_operdiv";
        boolean isNotException = true;
        try {
            clazz.setReg_operdiv(reg_operdiv);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetStart_indexWithStringStartIndexShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
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
        ConsultationForm clazz = new ConsultationForm();
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
        ConsultationForm clazz = new ConsultationForm();
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
    public void testGetCntrt_idShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRegion_gbnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getRegion_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBiz_clsfcnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getBiz_clsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDepth_clsfcnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getDepth_clsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnclsnpurps_bigclsfcnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCnclsnpurps_bigclsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnclsnpurps_midclsfcnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCnclsnpurps_midclsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnclsnpurps_midclsfcn_etcShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCnclsnpurps_midclsfcn_etc();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_trgtShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_trgt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_trgt_detShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_trgt_det();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPshdbkgrnd_purpsShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getPshdbkgrnd_purps();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPayment_gbnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getPayment_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAntcptnefctShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getAntcptnefct();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrtperiod_startdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrtperiod_startday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrtperiod_enddayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrtperiod_endday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSecret_keepperiodShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSecret_keepperiod();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_untprcShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_untprc();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_amtShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_amt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCrrncy_unitShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCrrncy_unit();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPayment_condShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getPayment_cond();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAuto_rnew_ynShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getAuto_rnew_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExprt_notidayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getExprt_notiday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOblgt_lmtShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getOblgt_lmt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSpcl_condShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSpcl_cond();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnclsn_plnddayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCnclsn_plndday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_respman_idShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_respman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_respman_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_respman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_oppnt_cdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_oppnt_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_oppnt_nationShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_oppnt_nation();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSign_plndman_idShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSign_plndman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSign_plndman_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSign_plndman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_mthdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSeal_mthd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_cnclsn_ynShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_cnclsn_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSignman_idShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSignman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSignman_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSignman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOppnt_signman_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getOppnt_signman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOppnt_signman_jikgupShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getOppnt_signman_jikgup();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOppnt_signman_deptShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getOppnt_signman_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOppnt_signdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getOppnt_signday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_cnclsndayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_cnclsnday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCpy_regdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCpy_regday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_noShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetLoacShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getLoac();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDspt_resolt_mthdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getDspt_resolt_mthd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDspt_resolt_mthd_detShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getDspt_resolt_mthd_det();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOrg_acpt_dlay_causeShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getOrg_acpt_dlay_cause();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOrg_acptdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getOrg_acptday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOrg_tkovman_idShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getOrg_tkovman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOrg_tkovman_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getOrg_tkovman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOrg_hdovman_idShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getOrg_hdovman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOrg_hdovman_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getOrg_hdovman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOrg_strg_posShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getOrg_strg_pos();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPrcs_depthShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getPrcs_depth();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDepth_statusShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getDepth_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_statusShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dtShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
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
        ConsultationForm clazz = new ConsultationForm();
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
        ConsultationForm clazz = new ConsultationForm();
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
        ConsultationForm clazz = new ConsultationForm();
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
        ConsultationForm clazz = new ConsultationForm();
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
        ConsultationForm clazz = new ConsultationForm();
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
        ConsultationForm clazz = new ConsultationForm();
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
        ConsultationForm clazz = new ConsultationForm();
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
        ConsultationForm clazz = new ConsultationForm();
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
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getDel_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetLoac_etcShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getLoac_etc();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_chge_demnddayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_chge_demndday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_chge_demndman_idShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_chge_demndman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_chge_demndman_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_chge_demndman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_chge_demnd_causeShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_chge_demnd_cause();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_chge_plnddayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_chge_plndday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_chge_conf_ynShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_chge_conf_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_chge_confdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_chge_confday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_chge_confman_idShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_chge_confman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_chge_confman_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_chge_confman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBfhdcstn_mtnman_idShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getBfhdcstn_mtnman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBfhdcstn_mtnman_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getBfhdcstn_mtnman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBfhdcstn_mtnman_jikgup_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getBfhdcstn_mtnman_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBfhdcstn_mtn_dept_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getBfhdcstn_mtn_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBfhdcstn_mtnman_emailShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getBfhdcstn_mtnman_email();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBfhdcstn_apbt_mthdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getBfhdcstn_apbt_mthd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBfhdcstn_apbtman_idShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getBfhdcstn_apbtman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBfhdcstn_apbtman_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getBfhdcstn_apbtman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBfhdcstn_apbtman_jikgup_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getBfhdcstn_apbtman_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBfhdcstn_apbt_dept_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getBfhdcstn_apbt_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBfhdcstn_apbtdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getBfhdcstn_apbtday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_rqstdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSeal_rqstday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_rqstman_idShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSeal_rqstman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_rqstman_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSeal_rqstman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_ffmtman_idShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSeal_ffmtman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_ffmtman_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSeal_ffmtman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_ffmtdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSeal_ffmtday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_ffmt_dept_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSeal_ffmt_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_ffmtman_jikgup_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSeal_ffmtman_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_operdivShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getReg_operdiv();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStart_indexShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
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
        ConsultationForm clazz = new ConsultationForm();
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
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCurPage();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUser_roleWithStringUserRoleShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String user_role = "user_role";
        boolean isNotException = true;
        try {
            clazz.setUser_role(user_role);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUser_roleShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getUser_role();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUser_orgnzWithStringUserOrgnzShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String user_orgnz = "user_orgnz";
        boolean isNotException = true;
        try {
            clazz.setUser_orgnz(user_orgnz);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUser_orgnzShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getUser_orgnz();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAgree_seqnoShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getAgree_seqno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAgree_seqnoWithIntAgreeSeqnoShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        int agree_seqno = 0;
        boolean isNotException = true;
        try {
            clazz.setAgree_seqno(agree_seqno);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAgreeman_idShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getAgreeman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAgreeman_idWithStringAgreemanIdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String agreeman_id = "agreeman_id";
        boolean isNotException = true;
        try {
            clazz.setAgreeman_id(agreeman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAgreeman_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getAgreeman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAgreeman_nmWithStringAgreemanNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String agreeman_nm = "agreeman_nm";
        boolean isNotException = true;
        try {
            clazz.setAgreeman_nm(agreeman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAgreeman_jikgup_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getAgreeman_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAgreeman_jikgup_nmWithStringAgreemanJikgupNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String agreeman_jikgup_nm = "agreeman_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setAgreeman_jikgup_nm(agreeman_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAgreeman_dept_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getAgreeman_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAgreeman_dept_nmWithStringAgreemanDeptNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String agreeman_dept_nm = "agreeman_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setAgreeman_dept_nm(agreeman_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAgreedayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getAgreeday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAgreedayWithStringAgreedayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String agreeday = "agreeday";
        boolean isNotException = true;
        try {
            clazz.setAgreeday(agreeday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAgree_ynShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getAgree_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAgree_ynWithStringAgreeYnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String agree_yn = "agree_yn";
        boolean isNotException = true;
        try {
            clazz.setAgree_yn(agree_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAgree_causeShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getAgree_cause();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAgree_causeWithStringAgreeCauseShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String agree_cause = "agree_cause";
        boolean isNotException = true;
        try {
            clazz.setAgree_cause(agree_cause);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetApproval_cntrt_infoShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getApproval_cntrt_info();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetApproval_cntrt_infoWithStringApprovalCntrtInfoShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String approval_cntrt_info = "approval_cntrt_info";
        boolean isNotException = true;
        try {
            clazz.setApproval_cntrt_info(approval_cntrt_info);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFileInfos1ShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getFileInfos1();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFileInfos1WithStringFileInfos1ShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String fileInfos1 = "fileInfos1";
        boolean isNotException = true;
        try {
            clazz.setFileInfos1(fileInfos1);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFileInfos2ShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getFileInfos2();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFileInfos2WithStringFileInfos2ShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String fileInfos2 = "fileInfos2";
        boolean isNotException = true;
        try {
            clazz.setFileInfos2(fileInfos2);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFileInfos3ShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getFileInfos3();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFileInfos3WithStringFileInfos3ShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String fileInfos3 = "fileInfos3";
        boolean isNotException = true;
        try {
            clazz.setFileInfos3(fileInfos3);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFileInfos4ShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getFileInfos4();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFileInfos4WithStringFileInfos4ShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String fileInfos4 = "fileInfos4";
        boolean isNotException = true;
        try {
            clazz.setFileInfos4(fileInfos4);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFileInfos5ShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getFileInfos5();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFileInfos5WithStringFileInfos5ShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String fileInfos5 = "fileInfos5";
        boolean isNotException = true;
        try {
            clazz.setFileInfos5(fileInfos5);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFileInfos6ShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getFileInfos6();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFileInfos6WithStringFileInfos6ShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String fileInfos6 = "fileInfos6";
        boolean isNotException = true;
        try {
            clazz.setFileInfos6(fileInfos6);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSubmit_statusShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSubmit_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSubmit_statusWithStringSubmitStatusShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String submit_status = "submit_status";
        boolean isNotException = true;
        try {
            clazz.setSubmit_status(submit_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetApprovalman_idShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getApprovalman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetApprovalman_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getApprovalman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetApprovalman_nmWithStringApprovalmanNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String approvalman_nm = "approvalman_nm";
        boolean isNotException = true;
        try {
            clazz.setApprovalman_nm(approvalman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetApprovalman_idWithStringApprovalmanIdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String approvalman_id = "approvalman_id";
        boolean isNotException = true;
        try {
            clazz.setApprovalman_id(approvalman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetApprovalman_dept_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getApprovalman_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetApprovalman_dept_nmWithStringApprovalmanDeptNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String approvalman_dept_nm = "approvalman_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setApprovalman_dept_nm(approvalman_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetApprovalman_jikgup_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getApprovalman_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetApprovalman_jikgup_nmWithStringApprovalmanJikgupNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String approvalman_jikgup_nm = "approvalman_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setApprovalman_jikgup_nm(approvalman_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetApproval_opinionShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getApproval_opinion();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetApproval_opinionWithStringApprovalOpinionShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String approval_opinion = "approval_opinion";
        boolean isNotException = true;
        try {
            clazz.setApproval_opinion(approval_opinion);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetIsmycontractShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getIsmycontract();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetIsmycontractWithStringIsmycontractShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String ismycontract = "ismycontract";
        boolean isNotException = true;
        try {
            clazz.setIsmycontract(ismycontract);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetList_modeShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getList_mode();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetList_modeWithStringListModeShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String list_mode = "list_mode";
        boolean isNotException = true;
        try {
            clazz.setList_mode(list_mode);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_resp_deptShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_resp_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_resp_deptWithStringSrchRespDeptShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_resp_dept = "srch_resp_dept";
        boolean isNotException = true;
        try {
            clazz.setSrch_resp_dept(srch_resp_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_oppnt_cdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_oppnt_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_oppnt_cdWithStringSrchOppntCdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_oppnt_cd = "srch_oppnt_cd";
        boolean isNotException = true;
        try {
            clazz.setSrch_oppnt_cd(srch_oppnt_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_review_titleShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_review_title();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_review_titleWithStringSrchReviewTitleShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_review_title = "srch_review_title";
        boolean isNotException = true;
        try {
            clazz.setSrch_review_title(srch_review_title);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_cntrt_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_cntrt_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_cntrt_nmWithStringSrchCntrtNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_cntrt_nm = "srch_cntrt_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_cntrt_nm(srch_cntrt_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_cntrt_noShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_cntrt_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_cntrt_noWithStringSrchCntrtNoShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_cntrt_no = "srch_cntrt_no";
        boolean isNotException = true;
        try {
            clazz.setSrch_cntrt_no(srch_cntrt_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_str_org_acptdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_str_org_acptday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_str_org_acptdayWithStringSrchStrOrgAcptdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_str_org_acptday = "srch_str_org_acptday";
        boolean isNotException = true;
        try {
            clazz.setSrch_str_org_acptday(srch_str_org_acptday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_end_org_acptdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_end_org_acptday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_end_org_acptdayWithStringSrchEndOrgAcptdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_end_org_acptday = "srch_end_org_acptday";
        boolean isNotException = true;
        try {
            clazz.setSrch_end_org_acptday(srch_end_org_acptday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_reqman_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_reqman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_reqman_nmWithStringSrchReqmanNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_reqman_nm = "srch_reqman_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_reqman_nm(srch_reqman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_start_reqdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_start_reqday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_start_reqdayWithStringSrchStartReqdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_start_reqday = "srch_start_reqday";
        boolean isNotException = true;
        try {
            clazz.setSrch_start_reqday(srch_start_reqday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_end_reqdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_end_reqday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_end_reqdayWithStringSrchEndReqdayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_end_reqday = "srch_end_reqday";
        boolean isNotException = true;
        try {
            clazz.setSrch_end_reqday(srch_end_reqday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_start_cnlsndayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_start_cnlsnday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_start_cnlsndayWithStringSrchStartCnlsndayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_start_cnlsnday = "srch_start_cnlsnday";
        boolean isNotException = true;
        try {
            clazz.setSrch_start_cnlsnday(srch_start_cnlsnday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_end_cnlsndayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_end_cnlsnday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_end_cnlsndayWithStringSrchEndCnlsndayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_end_cnlsnday = "srch_end_cnlsnday";
        boolean isNotException = true;
        try {
            clazz.setSrch_end_cnlsnday(srch_end_cnlsnday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_resp_dept_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_resp_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_resp_dept_nmWithStringSrchRespDeptNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_resp_dept_nm = "srch_resp_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_resp_dept_nm(srch_resp_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_respman_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_respman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_respman_nmWithStringSrchRespmanNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_respman_nm = "srch_respman_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_respman_nm(srch_respman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_oppnt_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_oppnt_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_oppnt_nmWithStringSrchOppntNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_oppnt_nm = "srch_oppnt_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_oppnt_nm(srch_oppnt_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_cnsdman_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_cnsdman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_cnsdman_nmWithStringSrchCnsdmanNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_cnsdman_nm = "srch_cnsdman_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_cnsdman_nm(srch_cnsdman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_stateShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_state();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_stateWithStringSrchStateShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_state = "srch_state";
        boolean isNotException = true;
        try {
            clazz.setSrch_state(srch_state);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_if_sys_cdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_if_sys_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_if_sys_cdWithStringSrchIfSysCdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_if_sys_cd = "srch_if_sys_cd";
        boolean isNotException = true;
        try {
            clazz.setSrch_if_sys_cd(srch_if_sys_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_biz_clsfcnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_biz_clsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_biz_clsfcnWithStringSrchBizClsfcnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_biz_clsfcn = "srch_biz_clsfcn";
        boolean isNotException = true;
        try {
            clazz.setSrch_biz_clsfcn(srch_biz_clsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_cnclsnpurps_bigclsfcnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_cnclsnpurps_bigclsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_cnclsnpurps_bigclsfcnWithStringSrchCnclsnpurpsBigclsfcnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_cnclsnpurps_bigclsfcn = "srch_cnclsnpurps_bigclsfcn";
        boolean isNotException = true;
        try {
            clazz.setSrch_cnclsnpurps_bigclsfcn(srch_cnclsnpurps_bigclsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_contract_type_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_contract_type_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_contract_type_nmWithStringSrchContractTypeNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_contract_type_nm = "srch_contract_type_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_contract_type_nm(srch_contract_type_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_contract_tagetShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_contract_taget();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_contract_tagetWithStringSrchContractTagetShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_contract_taget = "srch_contract_taget";
        boolean isNotException = true;
        try {
            clazz.setSrch_contract_taget(srch_contract_taget);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_stepShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_step();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_stepWithStringSrchStepShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_step = "srch_step";
        boolean isNotException = true;
        try {
            clazz.setSrch_step(srch_step);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetWork_flagShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getWork_flag();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetWork_flagWithStringWorkFlagShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
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
    public void testGetWorktypeShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getWorktype();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetWorktypeWithStringWorktypeShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String worktype = "worktype";
        boolean isNotException = true;
        try {
            clazz.setWorktype(worktype);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPage_gbnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getPage_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPage_gbnWithStringPageGbnShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String page_gbn = "page_gbn";
        boolean isNotException = true;
        try {
            clazz.setPage_gbn(page_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReport_urlShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getReport_url();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReport_urlWithStringReportUrlShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String report_url = "report_url";
        boolean isNotException = true;
        try {
            clazz.setReport_url(report_url);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAuthreq_clientShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getAuthreq_client();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuthreq_clientWithStringAuthreqClientShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String authreq_client = "authreq_client";
        boolean isNotException = true;
        try {
            clazz.setAuthreq_client(authreq_client);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOrg_hdov_dept_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getOrg_hdov_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOrg_hdov_dept_nmWithStringOrgHdovDeptNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String org_hdov_dept_nm = "org_hdov_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setOrg_hdov_dept_nm(org_hdov_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOrg_hdovman_jikgup_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getOrg_hdovman_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOrg_hdovman_jikgup_nmWithStringOrgHdovmanJikgupNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String org_hdovman_jikgup_nm = "org_hdovman_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setOrg_hdovman_jikgup_nm(org_hdovman_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTempsave_flagShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getTempsave_flag();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTempsave_flagWithStringTempsaveFlagShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String tempsave_flag = "tempsave_flag";
        boolean isNotException = true;
        try {
            clazz.setTempsave_flag(tempsave_flag);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSignman_jikgup_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSignman_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSignman_jikgup_nmWithStringSignmanJikgupNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String signman_jikgup_nm = "signman_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setSignman_jikgup_nm(signman_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSign_dept_nmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSign_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSign_dept_nmWithStringSignDeptNmShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String sign_dept_nm = "sign_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setSign_dept_nm(sign_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSigndayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSignday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSigndayWithStringSigndayShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String signday = "signday";
        boolean isNotException = true;
        try {
            clazz.setSignday(signday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_top30_cusShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_top30_cus();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_top30_cusWithStringCntrtTop30CusShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_top30_cus = "cntrt_top30_cus";
        boolean isNotException = true;
        try {
            clazz.setCntrt_top30_cus(cntrt_top30_cus);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_src_cont_drftShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_src_cont_drft();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_src_cont_drftWithStringCntrtSrcContDrftShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_src_cont_drft = "cntrt_src_cont_drft";
        boolean isNotException = true;
        try {
            clazz.setCntrt_src_cont_drft(cntrt_src_cont_drft);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_rel_prdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_rel_prd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_rel_prdWithStringCntrtRelPrdShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String cntrt_rel_prd = "cntrt_rel_prd";
        boolean isNotException = true;
        try {
            clazz.setCntrt_rel_prd(cntrt_rel_prd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_divisionShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_division();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_divisionWithStringSrchDivisionShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_division = "srch_division";
        boolean isNotException = true;
        try {
            clazz.setSrch_division(srch_division);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_vendor_typeShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_vendor_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_vendor_typeWithStringSrchVendorTypeShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_vendor_type = "srch_vendor_type";
        boolean isNotException = true;
        try {
            clazz.setSrch_vendor_type(srch_vendor_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_vendor_type_detailShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_vendor_type_detail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_vendor_type_detailWithStringSrchVendorTypeDetailShouldNotThrowException() {
        ConsultationForm clazz = new ConsultationForm();
        String srch_vendor_type_detail = "srch_vendor_type_detail";
        boolean isNotException = true;
        try {
            clazz.setSrch_vendor_type_detail(srch_vendor_type_detail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
