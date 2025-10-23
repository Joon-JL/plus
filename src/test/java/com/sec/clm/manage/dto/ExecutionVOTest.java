package com.sec.clm.manage.dto;

import java.lang.String;
import org.junit.Test;
import java.math.BigDecimal;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class ExecutionVOTest {

    @Test(timeout = 20000)
    public void testGetPageGbnShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
    public void testGetPayment_gbnShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        boolean isNotException = true;
        try {
            clazz.getPayment_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPayment_gbnWithStringPaymentGbnShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
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
    public void testGetMax_exec_numShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        boolean isNotException = true;
        try {
            clazz.getMax_exec_num();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMax_exec_numWithStringMaxExecNumShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        String max_exec_num = "max_exec_num";
        boolean isNotException = true;
        try {
            clazz.setMax_exec_num(max_exec_num);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExec_mod_flagShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        boolean isNotException = true;
        try {
            clazz.getExec_mod_flag();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExec_mod_flagWithStringExecModFlagShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        String exec_mod_flag = "exec_mod_flag";
        boolean isNotException = true;
        try {
            clazz.setExec_mod_flag(exec_mod_flag);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExec_up_noShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        boolean isNotException = true;
        try {
            clazz.getExec_up_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExec_up_noWithStringExecUpNoShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        String exec_up_no = "exec_up_no";
        boolean isNotException = true;
        try {
            clazz.setExec_up_no(exec_up_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExec_srtShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        boolean isNotException = true;
        try {
            clazz.getExec_srt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExec_srtWithStringExecSrtShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        String exec_srt = "exec_srt";
        boolean isNotException = true;
        try {
            clazz.setExec_srt(exec_srt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_idShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
    public void testGetReg_dtShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
    public void testGetMod_dtShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
    public void testGetCntrt_id_arrShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
    public void testGetExec_chk_arrShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
    public void testGetCnsdreq_idShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        boolean isNotException = true;
        try {
            clazz.getCnsdreq_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnsdreq_idWithStringCnsdreqIdShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        String cnsdreq_id = "cnsdreq_id";
        boolean isNotException = true;
        try {
            clazz.setCnsdreq_id(cnsdreq_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnsd_deptShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        boolean isNotException = true;
        try {
            clazz.getCnsd_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnsd_deptWithStringCnsdDeptShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        String cnsd_dept = "cnsd_dept";
        boolean isNotException = true;
        try {
            clazz.setCnsd_dept(cnsd_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_respman_idShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        boolean isNotException = true;
        try {
            clazz.getCntrt_respman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_respman_idWithStringCntrtRespmanIdShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
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
    public void testGetReqman_idShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        boolean isNotException = true;
        try {
            clazz.getReqman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReqman_idWithStringReqmanIdShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        String reqman_id = "reqman_id";
        boolean isNotException = true;
        try {
            clazz.setReqman_id(reqman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReqman_nmShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        boolean isNotException = true;
        try {
            clazz.getReqman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReqman_nmWithStringReqmanNmShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        String reqman_nm = "reqman_nm";
        boolean isNotException = true;
        try {
            clazz.setReqman_nm(reqman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExec_itm_arrShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
    public void testGetExec_gbn_arrShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
    public void testGetNote_arrShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
    public void testGetAuthreq_clientShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
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
        ExecutionVO clazz = new ExecutionVO();
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
    public void testGetExe_countShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        boolean isNotException = true;
        try {
            clazz.getExe_count();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExe_countWithStringExeCountShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        String exe_count = "exe_count";
        boolean isNotException = true;
        try {
            clazz.setExe_count(exe_count);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDepth_statusShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        boolean isNotException = true;
        try {
            clazz.getDepth_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDepth_statusWithStringDepthStatusShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
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
    public void testGetPrgrs_statusShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        boolean isNotException = true;
        try {
            clazz.getPrgrs_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPrgrs_statusWithStringPrgrsStatusShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        String prgrs_status = "prgrs_status";
        boolean isNotException = true;
        try {
            clazz.setPrgrs_status(prgrs_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBlngt_orgnzShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        boolean isNotException = true;
        try {
            clazz.getBlngt_orgnz();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBlngt_orgnzWithStringBlngtOrgnzShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        String blngt_orgnz = "blngt_orgnz";
        boolean isNotException = true;
        try {
            clazz.setBlngt_orgnz(blngt_orgnz);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDept_divShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        boolean isNotException = true;
        try {
            clazz.getDept_div();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDept_divWithStringDeptDivShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        String dept_div = "dept_div";
        boolean isNotException = true;
        try {
            clazz.setDept_div(dept_div);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_noShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        boolean isNotException = true;
        try {
            clazz.getCntrt_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_noWithStringCntrtNoShouldNotThrowException() {
        ExecutionVO clazz = new ExecutionVO();
        String cntrt_no = "cntrt_no";
        boolean isNotException = true;
        try {
            clazz.setCntrt_no(cntrt_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
