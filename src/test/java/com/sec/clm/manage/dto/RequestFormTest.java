package com.sec.clm.manage.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class RequestFormTest {

    @Test(timeout = 20000)
    public void testGetCntrt_idShouldNotThrowException() {
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
    public void testGetSrch_respman_nmShouldNotThrowException() {
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
    public void testGetTrgtman_deptShouldNotThrowException() {
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
        RequestForm clazz = new RequestForm();
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
