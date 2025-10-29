package com.sec.las.lawservice.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class EventRefDeptVOTest {

    @Test(timeout = 20000)
    public void testGetEvent_noShouldNotThrowException() {
        EventRefDeptVO clazz = new EventRefDeptVO();
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
        EventRefDeptVO clazz = new EventRefDeptVO();
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
    public void testGetIntnl_dept_cdShouldNotThrowException() {
        EventRefDeptVO clazz = new EventRefDeptVO();
        boolean isNotException = true;
        try {
            clazz.getIntnl_dept_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetIntnl_dept_cdWithStringIntnlDeptCdShouldNotThrowException() {
        EventRefDeptVO clazz = new EventRefDeptVO();
        String intnl_dept_cd = "intnl_dept_cd";
        boolean isNotException = true;
        try {
            clazz.setIntnl_dept_cd(intnl_dept_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGrp_dept_cdShouldNotThrowException() {
        EventRefDeptVO clazz = new EventRefDeptVO();
        boolean isNotException = true;
        try {
            clazz.getGrp_dept_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGrp_dept_cdWithStringGrpDeptCdShouldNotThrowException() {
        EventRefDeptVO clazz = new EventRefDeptVO();
        String grp_dept_cd = "grp_dept_cd";
        boolean isNotException = true;
        try {
            clazz.setGrp_dept_cd(grp_dept_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDept_nmShouldNotThrowException() {
        EventRefDeptVO clazz = new EventRefDeptVO();
        boolean isNotException = true;
        try {
            clazz.getDept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDept_nmWithStringDeptNmShouldNotThrowException() {
        EventRefDeptVO clazz = new EventRefDeptVO();
        String dept_nm = "dept_nm";
        boolean isNotException = true;
        try {
            clazz.setDept_nm(dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_idShouldNotThrowException() {
        EventRefDeptVO clazz = new EventRefDeptVO();
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
        EventRefDeptVO clazz = new EventRefDeptVO();
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
        EventRefDeptVO clazz = new EventRefDeptVO();
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
        EventRefDeptVO clazz = new EventRefDeptVO();
        String reg_nm = "reg_nm";
        boolean isNotException = true;
        try {
            clazz.setReg_nm(reg_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
