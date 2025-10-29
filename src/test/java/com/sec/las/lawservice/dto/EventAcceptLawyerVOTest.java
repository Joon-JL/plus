package com.sec.las.lawservice.dto;

import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class EventAcceptLawyerVOTest {

    @Test(timeout = 20000)
    public void testGetLwr_nmShouldNotThrowException() {
        EventAcceptLawyerVO clazz = new EventAcceptLawyerVO();
        boolean isNotException = true;
        try {
            clazz.getLwr_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLwr_nmWithStringLwrNmShouldNotThrowException() {
        EventAcceptLawyerVO clazz = new EventAcceptLawyerVO();
        String lwr_nm = "lwr_nm";
        boolean isNotException = true;
        try {
            clazz.setLwr_nm(lwr_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetLwr_noShouldNotThrowException() {
        EventAcceptLawyerVO clazz = new EventAcceptLawyerVO();
        boolean isNotException = true;
        try {
            clazz.getLwr_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLwr_noWithStringLwrNoShouldNotThrowException() {
        EventAcceptLawyerVO clazz = new EventAcceptLawyerVO();
        String lwr_no = "lwr_no";
        boolean isNotException = true;
        try {
            clazz.setLwr_no(lwr_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeqnoShouldNotThrowException() {
        EventAcceptLawyerVO clazz = new EventAcceptLawyerVO();
        boolean isNotException = true;
        try {
            clazz.getSeqno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeqnoWithStringSeqnoShouldNotThrowException() {
        EventAcceptLawyerVO clazz = new EventAcceptLawyerVO();
        String seqno = "seqno";
        boolean isNotException = true;
        try {
            clazz.setSeqno(seqno);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEvent_noShouldNotThrowException() {
        EventAcceptLawyerVO clazz = new EventAcceptLawyerVO();
        boolean isNotException = true;
        try {
            clazz.getEvent_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEvent_noWithStringEventNoShouldNotThrowException() {
        EventAcceptLawyerVO clazz = new EventAcceptLawyerVO();
        String event_no = "event_no";
        boolean isNotException = true;
        try {
            clazz.setEvent_no(event_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAcpt_noShouldNotThrowException() {
        EventAcceptLawyerVO clazz = new EventAcceptLawyerVO();
        boolean isNotException = true;
        try {
            clazz.getAcpt_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAcpt_noWithStringAcptNoShouldNotThrowException() {
        EventAcceptLawyerVO clazz = new EventAcceptLawyerVO();
        String acpt_no = "acpt_no";
        boolean isNotException = true;
        try {
            clazz.setAcpt_no(acpt_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
