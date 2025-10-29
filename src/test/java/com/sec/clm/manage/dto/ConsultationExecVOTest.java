package com.sec.clm.manage.dto;

import java.lang.String;
import org.junit.Test;
import java.math.BigDecimal;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class ConsultationExecVOTest {

    @Test(timeout = 20000)
    public void testGetCntrt_idShouldNotThrowException() {
        ConsultationExecVO clazz = new ConsultationExecVO();
        boolean isNotException = true;
        try {
            clazz.getCntrt_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_idWithStringCntrtIdShouldNotThrowException() {
        ConsultationExecVO clazz = new ConsultationExecVO();
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
    public void testGetExec_seqnoShouldNotThrowException() {
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
    public void testGetDel_ynShouldNotThrowException() {
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
    public void testGetCntrt_id_arrShouldNotThrowException() {
        ConsultationExecVO clazz = new ConsultationExecVO();
        boolean isNotException = true;
        try {
            clazz.getCntrt_id_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_id_arrWithStringArrayCntrtIdArrShouldNotThrowException() {
        ConsultationExecVO clazz = new ConsultationExecVO();
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
    public void testGetExec_seqno_arrShouldNotThrowException() {
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
        boolean isNotException = true;
        try {
            clazz.getExec_status_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExec_statusWithStringArrayExecStatusArrShouldNotThrowException() {
        ConsultationExecVO clazz = new ConsultationExecVO();
        String[] exec_status_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setExec_status(exec_status_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_yn_arrShouldNotThrowException() {
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
    public void testGetExec_gbnShouldNotThrowException() {
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
    public void testGetExec_chk_arrShouldNotThrowException() {
        ConsultationExecVO clazz = new ConsultationExecVO();
        boolean isNotException = true;
        try {
            clazz.getExec_chk_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExec_chk_arrWithIntArrayExecChkArrShouldNotThrowException() {
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
        ConsultationExecVO clazz = new ConsultationExecVO();
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
    public void testSetExec_status_arrWithStringArrayExecStatusArrShouldNotThrowException() {
        ConsultationExecVO clazz = new ConsultationExecVO();
        String[] exec_status_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setExec_status_arr(exec_status_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
