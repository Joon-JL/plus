package com.sec.clm.manage.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class RequestVOTest {

    @Test(timeout = 20000)
    public void testGetCntrt_idShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getCntrt_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetP_demnd_gbnShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getP_demnd_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetP_demnd_gbnWithStringPDemndGbnShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        String p_demnd_gbn = "p_demnd_gbn";
        boolean isNotException = true;
        try {
            clazz.setP_demnd_gbn(p_demnd_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetP_prcs_depthShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getP_prcs_depth();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetP_prcs_depthWithStringPPrcsDepthShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        String p_prcs_depth = "p_prcs_depth";
        boolean isNotException = true;
        try {
            clazz.setP_prcs_depth(p_prcs_depth);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_idWithStringCntrtIdShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
    public void testGetDemnd_seqnoShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getDemnd_seqno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDemnd_seqnoWithIntDemndSeqnoShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        int demnd_seqno = 0;
        boolean isNotException = true;
        try {
            clazz.setDemnd_seqno(demnd_seqno);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDemndman_idShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getDemndman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDemndman_idWithStringDemndmanIdShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
    public void testGetDemndman_nmShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getDemndman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDemndman_nmWithStringDemndmanNmShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
    public void testGetDemndman_dept_nmShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getDemndman_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDemndman_dept_nmWithStringDemndmanDeptNmShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
    public void testGetTrgtman_idShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getTrgtman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTrgtman_idWithStringTrgtmanIdShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
    public void testGetTrgtman_nmShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getTrgtman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTrgtman_nmWithStringTrgtmanNmShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
    public void testGetTrgtman_dept_nmShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getTrgtman_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTrgtman_dept_nmWithStringTrgtmanDeptNmShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
    public void testGetRd_authShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
    public void testGetAuth_startdayShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getAuth_startday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_startdayWithStringAuthStartdayShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
    public void testGetAuth_enddayShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getAuth_endday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_enddayWithStringAuthEnddayShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
    public void testGetDemnd_statusShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getDemnd_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDemnd_statusWithStringDemndStatusShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
    public void testGetDemnd_dtShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getDemnd_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDemnd_dtWithStringDemndDtShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
    public void testGetHndl_dtShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getHndl_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHndl_dtWithStringHndlDtShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
    public void testGetHndl_contShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getHndl_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHndl_contWithStringHndlContShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
    public void testGetHndlman_nmShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getHndlman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHndlman_nmWithStringHndlmanNmShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
    public void testGetHndlman_idShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getHndlman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHndlman_idWithStringHndlmanIdShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
    public void testGetDel_ynShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
    public void testGetDemnd_gbnShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
    public void testGetDemndman_jikgup_nmShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getDemndman_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDemndman_jikgup_nmWithStringDemndmanJikgupNmShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        String demndman_jikgup_nm = "demndman_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setDemndman_jikgup_nm(demndman_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTrgtman_jikgup_nmShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
    public void testGetDemnd_contShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getDemnd_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDemnd_contWithStringDemndContShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        String demnd_cont = "demnd_cont";
        boolean isNotException = true;
        try {
            clazz.setDemnd_cont(demnd_cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnsdreq_idShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
    public void testGetStatus_modeShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getStatus_mode();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetStatus_modeWithStringStatusModeShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        String status_mode = "status_mode";
        boolean isNotException = true;
        try {
            clazz.setStatus_mode(status_mode);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetList_modeShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
    public void testGetSrch_review_titleShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
    public void testGetSrch_reqman_nmShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
    public void testGetSrch_respman_nmShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
    public void testGetSrch_resp_deptShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
    public void testGetSrch_resp_dept_nmShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
    public void testGetSrch_oppnt_cdShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
    public void testGetSrch_oppnt_nmShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
    public void testGetSrch_biz_clsfcnShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
    public void testGetSrch_stateShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
    public void testGetSrch_demand_gbnShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_demand_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_demand_gbnWithStringSrchDemandGbnShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        String srch_demand_gbn = "srch_demand_gbn";
        boolean isNotException = true;
        try {
            clazz.setSrch_demand_gbn(srch_demand_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_demand_statusShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_demand_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_demand_statusWithStringSrchDemandStatusShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        String srch_demand_status = "srch_demand_status";
        boolean isNotException = true;
        try {
            clazz.setSrch_demand_status(srch_demand_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReq_titleShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getReq_title();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReq_titleWithStringReqTitleShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        String req_title = "req_title";
        boolean isNotException = true;
        try {
            clazz.setReq_title(req_title);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_respman_idShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
        RequestVO clazz = new RequestVO();
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
    public void testGetCntrt_respman_nmShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getCntrt_respman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_respman_nmWithStringCntrtRespmanNmShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
    public void testGetCntrt_nmShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getCntrt_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_nmWithStringCntrtNmShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
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
    public void testGetTrgtman_deptShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getTrgtman_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTrgtman_deptWithStringTrgtmanDeptShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        String trgtman_dept = "trgtman_dept";
        boolean isNotException = true;
        try {
            clazz.setTrgtman_dept(trgtman_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTrgtman_in_deptShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        boolean isNotException = true;
        try {
            clazz.getTrgtman_in_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTrgtman_in_deptWithStringTrgtmanInDeptShouldNotThrowException() {
        RequestVO clazz = new RequestVO();
        String trgtman_in_dept = "trgtman_in_dept";
        boolean isNotException = true;
        try {
            clazz.setTrgtman_in_dept(trgtman_in_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
