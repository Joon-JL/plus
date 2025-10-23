package com.sec.clm.manage.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class ContractAgreeVOTest {

    @Test(timeout = 20000)
    public void testGetCntrt_idShouldNotThrowException() {
        ContractAgreeVO clazz = new ContractAgreeVO();
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
        ContractAgreeVO clazz = new ContractAgreeVO();
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
    public void testGetAgree_seqnoShouldNotThrowException() {
        ContractAgreeVO clazz = new ContractAgreeVO();
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
        ContractAgreeVO clazz = new ContractAgreeVO();
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
        ContractAgreeVO clazz = new ContractAgreeVO();
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
        ContractAgreeVO clazz = new ContractAgreeVO();
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
        ContractAgreeVO clazz = new ContractAgreeVO();
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
        ContractAgreeVO clazz = new ContractAgreeVO();
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
        ContractAgreeVO clazz = new ContractAgreeVO();
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
        ContractAgreeVO clazz = new ContractAgreeVO();
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
        ContractAgreeVO clazz = new ContractAgreeVO();
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
        ContractAgreeVO clazz = new ContractAgreeVO();
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
    public void testGetAgree_ynShouldNotThrowException() {
        ContractAgreeVO clazz = new ContractAgreeVO();
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
        ContractAgreeVO clazz = new ContractAgreeVO();
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
    public void testGetAgreedayShouldNotThrowException() {
        ContractAgreeVO clazz = new ContractAgreeVO();
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
        ContractAgreeVO clazz = new ContractAgreeVO();
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
    public void testGetAgree_causeShouldNotThrowException() {
        ContractAgreeVO clazz = new ContractAgreeVO();
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
        ContractAgreeVO clazz = new ContractAgreeVO();
        String agree_cause = "agree_cause";
        boolean isNotException = true;
        try {
            clazz.setAgree_cause(agree_cause);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
