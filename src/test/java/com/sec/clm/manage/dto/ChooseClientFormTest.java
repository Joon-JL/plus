package com.sec.clm.manage.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import java.util.List;
import static org.mockito.Mockito.mock;

public class ChooseClientFormTest {

    @Test(timeout = 20000)
    public void testGetCnsdreq_idShouldNotThrowException() {
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
    public void testGetCntrt_idShouldNotThrowException() {
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
        ChooseClientForm clazz = new ChooseClientForm();
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
    public void testGetChose_clientShouldNotThrowException() {
        ChooseClientForm clazz = new ChooseClientForm();
        boolean isNotException = true;
        try {
            clazz.getChose_client();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetChose_clientWithStringChoseClientShouldNotThrowException() {
        ChooseClientForm clazz = new ChooseClientForm();
        String chose_client = "chose_client";
        boolean isNotException = true;
        try {
            clazz.setChose_client(chose_client);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
