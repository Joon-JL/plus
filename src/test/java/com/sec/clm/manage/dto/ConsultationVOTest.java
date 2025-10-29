package com.sec.clm.manage.dto;

import java.lang.String;
import com.sec.clm.manage.dto.ConsiderationVO;
import org.junit.Test;
import java.math.BigDecimal;
import static org.mockito.Mockito.mock;

public class ConsultationVOTest {

    @Test(timeout = 20000)
    public void testGetHq_req_ynShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getHq_req_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHq_req_ynWithStringHqReqYnShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String hq_req_yn = "hq_req_yn";
        boolean isNotException = true;
        try {
            clazz.setHq_req_yn(hq_req_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetComp_cdShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getComp_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEn_cntrt_nmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testSetComp_cdWithStringCompCdShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String comp_cd = "comp_cd";
        boolean isNotException = true;
        try {
            clazz.setComp_cd(comp_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRel_up_cntrt_idShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testGetMis_idShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getMis_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMis_idWithStringMisIdShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String mis_id = "mis_id";
        boolean isNotException = true;
        try {
            clazz.setMis_id(mis_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExec_chk_arrShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testGetBuGubnShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getBuGubn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBuGubnWithStringBuGubnShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String buGubn = "buGubn";
        boolean isNotException = true;
        try {
            clazz.setBuGubn(buGubn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBlngt_orgnz_idShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getBlngt_orgnz_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBlngt_orgnz_idWithStringBlngtOrgnzIdShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String blngt_orgnz_id = "blngt_orgnz_id";
        boolean isNotException = true;
        try {
            clazz.setBlngt_orgnz_id(blngt_orgnz_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRel_type1ShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testGetBorgnzShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getBorgnz();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBorgnzWithStringBorgnzShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String borgnz = "borgnz";
        boolean isNotException = true;
        try {
            clazz.setBorgnz(borgnz);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetManager_ynShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getManager_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetManager_ynWithStringManagerYnShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String manager_yn = "manager_yn";
        boolean isNotException = true;
        try {
            clazz.setManager_yn(manager_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDeptShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getDept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDeptWithStringDeptShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String dept = "dept";
        boolean isNotException = true;
        try {
            clazz.setDept(dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUser_idShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getUser_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUser_idWithStringUserIdShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String user_id = "user_id";
        boolean isNotException = true;
        try {
            clazz.setUser_id(user_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPartShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getPart();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPartWithStringPartShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String part = "part";
        boolean isNotException = true;
        try {
            clazz.setPart(part);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRole_cdShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getRole_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRole_cdWithStringRoleCdShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String role_cd = "role_cd";
        boolean isNotException = true;
        try {
            clazz.setRole_cd(role_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMaster_cntrt_idShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getMaster_cntrt_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMaster_cntrt_idWithStringMasterCntrtIdShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String master_cntrt_id = "master_cntrt_id";
        boolean isNotException = true;
        try {
            clazz.setMaster_cntrt_id(master_cntrt_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDemnd_gbnShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getDemnd_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDemnd_gbnWithStringDemndGbnShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String demnd_gbn = "demnd_gbn";
        boolean isNotException = true;
        try {
            clazz.setDemnd_gbn(demnd_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRegNotiday_beforeShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testGetSeal_ffmt_dept_cdShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testGetCntrt_oppnt_nmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getCntrt_oppnt_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_oppnt_nmWithStringCntrtOppntNmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String cntrt_oppnt_nm = "cntrt_oppnt_nm";
        boolean isNotException = true;
        try {
            clazz.setCntrt_oppnt_nm(cntrt_oppnt_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_oppnt_telnoShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getCntrt_oppnt_telno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_oppnt_telnoWithStringCntrtOppntTelnoShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String cntrt_oppnt_telno = "cntrt_oppnt_telno";
        boolean isNotException = true;
        try {
            clazz.setCntrt_oppnt_telno(cntrt_oppnt_telno);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_oppnt_emailShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getCntrt_oppnt_email();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_oppnt_emailWithStringCntrtOppntEmailShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String cntrt_oppnt_email = "cntrt_oppnt_email";
        boolean isNotException = true;
        try {
            clazz.setCntrt_oppnt_email(cntrt_oppnt_email);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_oppnt_rprsntmanShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getCntrt_oppnt_rprsntman();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_oppnt_rprsntmanWithStringCntrtOppntRprsntmanShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String cntrt_oppnt_rprsntman = "cntrt_oppnt_rprsntman";
        boolean isNotException = true;
        try {
            clazz.setCntrt_oppnt_rprsntman(cntrt_oppnt_rprsntman);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_oppnt_typeShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getCntrt_oppnt_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_oppnt_typeWithStringCntrtOppntTypeShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String cntrt_oppnt_type = "cntrt_oppnt_type";
        boolean isNotException = true;
        try {
            clazz.setCntrt_oppnt_type(cntrt_oppnt_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_oppnt_respmanShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getCntrt_oppnt_respman();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_oppnt_respmanWithStringCntrtOppntRespmanShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String cntrt_oppnt_respman = "cntrt_oppnt_respman";
        boolean isNotException = true;
        try {
            clazz.setCntrt_oppnt_respman(cntrt_oppnt_respman);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_statusWithStringCntrtStatusShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
    public void testGetCntrt_statusShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getCntrt_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_chge_demnd_dept_nmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getCntrt_chge_demnd_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_chge_demnd_dept_nmWithStringCntrtChgeDemndDeptNmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String cntrt_chge_demnd_dept_nm = "cntrt_chge_demnd_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setCntrt_chge_demnd_dept_nm(cntrt_chge_demnd_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_chge_demndman_jikgup_nmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getCntrt_chge_demndman_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_chge_demndman_jikgup_nmWithStringCntrtChgeDemndmanJikgupNmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String cntrt_chge_demndman_jikgup_nm = "cntrt_chge_demndman_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setCntrt_chge_demndman_jikgup_nm(cntrt_chge_demndman_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetChage_causeShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getChage_cause();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetChage_causeWithStringChageCauseShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String chage_cause = "chage_cause";
        boolean isNotException = true;
        try {
            clazz.setChage_cause(chage_cause);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFileInfosEtcShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getFileInfosEtc();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFileInfosEtcWithStringFileInfosEtcShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String fileInfosEtc = "fileInfosEtc";
        boolean isNotException = true;
        try {
            clazz.setFileInfosEtc(fileInfosEtc);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFileInfos1ShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testSetTab_cntWithStringTabCntShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getArr_expl();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetArr_rel_defnShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testSetRel_defnWithStringRelDefnShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
    public void testGetRel_defnShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getRel_defn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExplWithStringExplShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getExpl();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAttr_seqnoWithStringAttrSeqnoShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getPrnt_srt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetArr_attr_seqnoWithStringArrayArrAttrSeqnoShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String[] arr_attr_seqno = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setArr_attr_seqno(arr_attr_seqno);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetArr_attr_seqnoShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getArr_attr_seqno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetArr_attr_cdWithStringArrayArrAttrCdShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String[] arr_attr_cd = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setArr_attr_cd(arr_attr_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetArr_attr_cdShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getArr_attr_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAttr_cdWithStringAttrCdShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getAttr_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetArr_attr_contWithStringArrayArrAttrContShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String[] arr_attr_cont = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setArr_attr_cont(arr_attr_cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetArr_attr_contShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getArr_attr_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAttr_contWithStringAttrContShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getAttr_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTrgtman_jikgup_nmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getTrgtman_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTrgtman_jikgup_nmWithStringTrgtmanJikgupNmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String trgtman_jikgup_nm = "trgtman_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setTrgtman_jikgup_nm(trgtman_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetArr_trgtman_jikgup_nmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getArr_trgtman_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetArr_trgtman_jikgup_nmWithStringArrayArrTrgtmanJikgupNmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String[] arr_trgtman_jikgup_nm = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setArr_trgtman_jikgup_nm(arr_trgtman_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDemnd_seqnoWithStringDemndSeqnoShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getHndl_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetArr_cntrt_idWithStringArrayArrCntrtIdShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
    public void testSetCntrt_idWithStringCntrtIdShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testSetCntrt_untprc_explWithStringCntrtUntprcExplShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String cntrt_untprc_expl = "cntrt_untprc_expl";
        boolean isNotException = true;
        try {
            clazz.setCntrt_untprc_expl(cntrt_untprc_expl);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_amtWithStringCntrtAmtShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testSetCnclsn_plndday1WithStringCnclsnPlndday1ShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String cnclsn_plndday1 = "cnclsn_plndday1";
        boolean isNotException = true;
        try {
            clazz.setCnclsn_plndday1(cnclsn_plndday1);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_respman_idWithStringCntrtRespmanIdShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testSetCntrt_oppnt_nationWithStringCntrtOppntNationShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
    public void testSetSign_plndman_idWithStringSignPlndmanIdShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testSetSign_plnd_dept_nmWithStringSignPlndDeptNmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String sign_plnd_dept_nm = "sign_plnd_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setSign_plnd_dept_nm(sign_plnd_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSign_plndman_jikgup_nmWithStringSignPlndmanJikgupNmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String sign_plndman_jikgup_nm = "sign_plndman_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setSign_plndman_jikgup_nm(sign_plndman_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeal_mthdWithStringSealMthdShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testSetReg_dtWithStringRegDtShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testSetBody_mime1WithStringBodyMime1ShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String body_mime1 = "body_mime1";
        boolean isNotException = true;
        try {
            clazz.setBody_mime1(body_mime1);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetArr_cntrt_idShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getArr_cntrt_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_idShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getSecret_keepperiod();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_untprc_explShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getCntrt_untprc_expl();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_amtShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getCnclsn_plndday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnclsn_plndday1ShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getCnclsn_plndday1();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_respman_idShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getSign_plndman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSign_plnd_dept_nmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getSign_plnd_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSign_plndman_jikgup_nmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getSign_plndman_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeal_mthdShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getDepth_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dtShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getCurPage();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBody_mime1ShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getBody_mime1();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUser_roleWithStringUserRoleShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getUser_orgnz();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetStatusWithStringStatusShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String status = "status";
        boolean isNotException = true;
        try {
            clazz.setStatus(status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStatusShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getStatus();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_resp_deptWithStringCntrtRespDeptShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String cntrt_resp_dept = "cntrt_resp_dept";
        boolean isNotException = true;
        try {
            clazz.setCntrt_resp_dept(cntrt_resp_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_resp_dept_nmWithStringCntrtRespDeptNmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String cntrt_resp_dept_nm = "cntrt_resp_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setCntrt_resp_dept_nm(cntrt_resp_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_resp_up_deptWithStringCntrtRespUpDeptShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String cntrt_resp_up_dept = "cntrt_resp_up_dept";
        boolean isNotException = true;
        try {
            clazz.setCntrt_resp_up_dept(cntrt_resp_up_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_respman_jikgup_nmWithStringCntrtRespmanJikgupNmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String cntrt_respman_jikgup_nm = "cntrt_respman_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setCntrt_respman_jikgup_nm(cntrt_respman_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFst_cntrt_resp_deptShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getFst_cntrt_resp_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFst_cntrt_resp_deptWithStringFstCntrtRespDeptShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String fst_cntrt_resp_dept = "fst_cntrt_resp_dept";
        boolean isNotException = true;
        try {
            clazz.setFst_cntrt_resp_dept(fst_cntrt_resp_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFst_cntrt_resp_up_deptShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getFst_cntrt_resp_up_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFst_cntrt_resp_up_deptWithStringFstCntrtRespUpDeptShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String fst_cntrt_resp_up_dept = "fst_cntrt_resp_up_dept";
        boolean isNotException = true;
        try {
            clazz.setFst_cntrt_resp_up_dept(fst_cntrt_resp_up_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEtc_main_contWithStringEtcMainContShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String etc_main_cont = "etc_main_cont";
        boolean isNotException = true;
        try {
            clazz.setEtc_main_cont(etc_main_cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEtc_main_contShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getEtc_main_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_resp_deptShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getCntrt_resp_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_resp_dept_nmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getCntrt_resp_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_resp_up_deptShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getCntrt_resp_up_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_respman_jikgup_nmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getCntrt_respman_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAgree_seqnoShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testGetPlndbn_chge_contShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getPlndbn_chge_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPlndbn_chge_contWithStringPlndbnChgeContShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String plndbn_chge_cont = "plndbn_chge_cont";
        boolean isNotException = true;
        try {
            clazz.setPlndbn_chge_cont(plndbn_chge_cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetChg_prgrs_statusShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getChg_prgrs_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetChg_prgrs_statusWithStringChgPrgrsStatusShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String chg_prgrs_status = "chg_prgrs_status";
        boolean isNotException = true;
        try {
            clazz.setChg_prgrs_status(chg_prgrs_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetList_modeShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testGetSrch_start_reqdayShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testGetSrch_reqman_nmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testGetSrch_contract_type_nmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testGetSrch_stateShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testGetHold_seqnoShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getHold_seqno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHold_seqnoWithIntHoldSeqnoShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        int hold_seqno = 0;
        boolean isNotException = true;
        try {
            clazz.setHold_seqno(hold_seqno);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHold_causeShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getHold_cause();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHold_causeWithStringHoldCauseShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String hold_cause = "hold_cause";
        boolean isNotException = true;
        try {
            clazz.setHold_cause(hold_cause);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_review_titleShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testGetSrch_start_cnlsndayShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testGetSrch_if_sys_cdShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testGetSrch_stepShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testGetAuthreq_clientShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testGetAttr_frmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getAttr_frm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTempsave_flagShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testGetStd_cnslt_noShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getStd_cnslt_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetStd_cnslt_noWithStringStdCnsltNoShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String std_cnslt_no = "std_cnslt_no";
        boolean isNotException = true;
        try {
            clazz.setStd_cnslt_no(std_cnslt_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRejct_opnnShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getRejct_opnn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRejct_opnnWithStringRejctOpnnShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String rejct_opnn = "rejct_opnn";
        boolean isNotException = true;
        try {
            clazz.setRejct_opnn(rejct_opnn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetParam_seqnoShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getParam_seqno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetParam_seqnoWithStringParamSeqnoShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String param_seqno = "param_seqno";
        boolean isNotException = true;
        try {
            clazz.setParam_seqno(param_seqno);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetActive_cntrt_idShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getActive_cntrt_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetActive_cntrt_idWithStringActiveCntrtIdShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String active_cntrt_id = "active_cntrt_id";
        boolean isNotException = true;
        try {
            clazz.setActive_cntrt_id(active_cntrt_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDeptcnsd_cnsd_deptShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getDeptcnsd_cnsd_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDeptcnsd_cnsd_deptWithStringDeptcnsdCnsdDeptShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String deptcnsd_cnsd_dept = "deptcnsd_cnsd_dept";
        boolean isNotException = true;
        try {
            clazz.setDeptcnsd_cnsd_dept(deptcnsd_cnsd_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDeptcnsd_cnsdman_idShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getDeptcnsd_cnsdman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDeptcnsd_cnsdman_idWithStringDeptcnsdCnsdmanIdShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String deptcnsd_cnsdman_id = "deptcnsd_cnsdman_id";
        boolean isNotException = true;
        try {
            clazz.setDeptcnsd_cnsdman_id(deptcnsd_cnsdman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDeptcnsd_cnsdman_nmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getDeptcnsd_cnsdman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDeptcnsd_cnsdman_nmWithStringDeptcnsdCnsdmanNmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String deptcnsd_cnsdman_nm = "deptcnsd_cnsdman_nm";
        boolean isNotException = true;
        try {
            clazz.setDeptcnsd_cnsdman_nm(deptcnsd_cnsdman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetChg_depth_statusShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getChg_depth_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetChg_depth_statusWithStringChgDepthStatusShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String chg_depth_status = "chg_depth_status";
        boolean isNotException = true;
        try {
            clazz.setChg_depth_status(chg_depth_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDeptcnsd_apbtman_idShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getDeptcnsd_apbtman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDeptcnsd_apbtman_idWithStringDeptcnsdApbtmanIdShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String deptcnsd_apbtman_id = "deptcnsd_apbtman_id";
        boolean isNotException = true;
        try {
            clazz.setDeptcnsd_apbtman_id(deptcnsd_apbtman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDeptcnsd_apbtman_nmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getDeptcnsd_apbtman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDeptcnsd_apbtman_nmWithStringDeptcnsdApbtmanNmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String deptcnsd_apbtman_nm = "deptcnsd_apbtman_nm";
        boolean isNotException = true;
        try {
            clazz.setDeptcnsd_apbtman_nm(deptcnsd_apbtman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDeptcnsd_apbt_opnnShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getDeptcnsd_apbt_opnn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDeptcnsd_apbt_opnnWithStringDeptcnsdApbtOpnnShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String deptcnsd_apbt_opnn = "deptcnsd_apbt_opnn";
        boolean isNotException = true;
        try {
            clazz.setDeptcnsd_apbt_opnn(deptcnsd_apbt_opnn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDeptcnsd_cnsd_statusShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getDeptcnsd_cnsd_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDeptcnsd_cnsd_statusWithStringDeptcnsdCnsdStatusShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String deptcnsd_cnsd_status = "deptcnsd_cnsd_status";
        boolean isNotException = true;
        try {
            clazz.setDeptcnsd_cnsd_status(deptcnsd_cnsd_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMain_matr_contShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getMain_matr_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMain_matr_contWithStringMainMatrContShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String main_matr_cont = "main_matr_cont";
        boolean isNotException = true;
        try {
            clazz.setMain_matr_cont(main_matr_cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetChg_cntrt_statusShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getChg_cntrt_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetChg_cntrt_statusWithStringChgCntrtStatusShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String chg_cntrt_status = "chg_cntrt_status";
        boolean isNotException = true;
        try {
            clazz.setChg_cntrt_status(chg_cntrt_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetChg_prcs_depthShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getChg_prcs_depth();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetChg_prcs_depthWithStringChgPrcsDepthShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String chg_prcs_depth = "chg_prcs_depth";
        boolean isNotException = true;
        try {
            clazz.setChg_prcs_depth(chg_prcs_depth);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnsd_memoShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getCnsd_memo();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnsd_memoWithStringCnsdMemoShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String cnsd_memo = "cnsd_memo";
        boolean isNotException = true;
        try {
            clazz.setCnsd_memo(cnsd_memo);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReqdept_nmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getReqdept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReqdept_nmWithStringReqdeptNmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String reqdept_nm = "reqdept_nm";
        boolean isNotException = true;
        try {
            clazz.setReqdept_nm(reqdept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSignman_jikgup_nmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testSetGERPCostTypeWithStringGerpCostTypeShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String gerpCostType = "gerpCostType";
        boolean isNotException = true;
        try {
            clazz.setGERPCostType(gerpCostType);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGERPCostTypeShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getGERPCostType();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGERPContractTypeWithStringGerpContractTypeShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String gerpContractType = "gerpContractType";
        boolean isNotException = true;
        try {
            clazz.setGERPContractType(gerpContractType);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGERPContractTypeShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getGERPContractType();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGERPDivCodeWithStringGerpDivCodeShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String gerpDivCode = "gerpDivCode";
        boolean isNotException = true;
        try {
            clazz.setGERPDivCode(gerpDivCode);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGERPDivCodeShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getGERPDivCode();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_top30_cusShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
        ConsultationVO clazz = new ConsultationVO();
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
    public void testGetGerpCostTypeShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getGerpCostType();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGerpCostTypeWithStringGerpCostTypeShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String gerpCostType = "gerpCostType";
        boolean isNotException = true;
        try {
            clazz.setGerpCostType(gerpCostType);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGerpContractTypeShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getGerpContractType();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGerpContractTypeWithStringGerpContractTypeShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String gerpContractType = "gerpContractType";
        boolean isNotException = true;
        try {
            clazz.setGerpContractType(gerpContractType);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGerpDivCodeShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getGerpDivCode();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGerpDivCodeWithStringGerpDivCodeShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String gerpDivCode = "gerpDivCode";
        boolean isNotException = true;
        try {
            clazz.setGerpDivCode(gerpDivCode);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTnc_app_linkShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getTnc_app_link();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTnc_app_linkWithStringTncAppLinkShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String tnc_app_link = "tnc_app_link";
        boolean isNotException = true;
        try {
            clazz.setTnc_app_link(tnc_app_link);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCtc_linkShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getCtc_link();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCtc_linkWithStringCtcLinkShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String ctc_link = "ctc_link";
        boolean isNotException = true;
        try {
            clazz.setCtc_link(ctc_link);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOtc_linkShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getOtc_link();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOtc_linkWithStringOtcLinkShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String otc_link = "otc_link";
        boolean isNotException = true;
        try {
            clazz.setOtc_link(otc_link);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTnc_summary_linkShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getTnc_summary_link();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTnc_summary_linkWithStringTncSummaryLinkShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String tnc_summary_link = "tnc_summary_link";
        boolean isNotException = true;
        try {
            clazz.setTnc_summary_link(tnc_summary_link);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSys_nmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getSys_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSys_nmWithStringSysNmShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String sys_nm = "sys_nm";
        boolean isNotException = true;
        try {
            clazz.setSys_nm(sys_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAsgn_dtShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        boolean isNotException = true;
        try {
            clazz.getAsgn_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAsgn_dtWithStringAsgnDtShouldNotThrowException() {
        ConsultationVO clazz = new ConsultationVO();
        String asgn_dt = "asgn_dt";
        boolean isNotException = true;
        try {
            clazz.setAsgn_dt(asgn_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
