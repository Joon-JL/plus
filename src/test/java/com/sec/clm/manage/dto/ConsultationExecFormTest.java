package com.sec.clm.manage.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class ConsultationExecFormTest {

    @Test(timeout = 20000)
    public void testGetCntrt_idShouldNotThrowException() {
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
    public void testGetExec_chk_arrShouldNotThrowException() {
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
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
        ConsultationExecForm clazz = new ConsultationExecForm();
        String[] note_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setNote_arr(note_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
