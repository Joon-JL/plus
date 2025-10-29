package com.sec.las.lawservice.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class DeptListFormTest {

    @Test(timeout = 20000)
    public void testGetDept_cdShouldNotThrowException() {
        DeptListForm clazz = new DeptListForm();
        boolean isNotException = true;
        try {
            clazz.getDept_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDept_cdWithStringDeptCdShouldNotThrowException() {
        DeptListForm clazz = new DeptListForm();
        String dept_cd = "dept_cd";
        boolean isNotException = true;
        try {
            clazz.setDept_cd(dept_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDept_nmShouldNotThrowException() {
        DeptListForm clazz = new DeptListForm();
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
        DeptListForm clazz = new DeptListForm();
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
    public void testGetDept_nm_engShouldNotThrowException() {
        DeptListForm clazz = new DeptListForm();
        boolean isNotException = true;
        try {
            clazz.getDept_nm_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDept_nm_engWithStringDeptNmEngShouldNotThrowException() {
        DeptListForm clazz = new DeptListForm();
        String dept_nm_eng = "dept_nm_eng";
        boolean isNotException = true;
        try {
            clazz.setDept_nm_eng(dept_nm_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetIn_dept_cdShouldNotThrowException() {
        DeptListForm clazz = new DeptListForm();
        boolean isNotException = true;
        try {
            clazz.getIn_dept_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetIn_dept_cdWithStringInDeptCdShouldNotThrowException() {
        DeptListForm clazz = new DeptListForm();
        String in_dept_cd = "in_dept_cd";
        boolean isNotException = true;
        try {
            clazz.setIn_dept_cd(in_dept_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDept_levelShouldNotThrowException() {
        DeptListForm clazz = new DeptListForm();
        boolean isNotException = true;
        try {
            clazz.getDept_level();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDept_levelWithStringDeptLevelShouldNotThrowException() {
        DeptListForm clazz = new DeptListForm();
        String dept_level = "dept_level";
        boolean isNotException = true;
        try {
            clazz.setDept_level(dept_level);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDept_orderShouldNotThrowException() {
        DeptListForm clazz = new DeptListForm();
        boolean isNotException = true;
        try {
            clazz.getDept_order();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDept_orderWithStringDeptOrderShouldNotThrowException() {
        DeptListForm clazz = new DeptListForm();
        String dept_order = "dept_order";
        boolean isNotException = true;
        try {
            clazz.setDept_order(dept_order);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDown_dept_ynShouldNotThrowException() {
        DeptListForm clazz = new DeptListForm();
        boolean isNotException = true;
        try {
            clazz.getDown_dept_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDown_dept_ynWithStringDownDeptYnShouldNotThrowException() {
        DeptListForm clazz = new DeptListForm();
        String down_dept_yn = "down_dept_yn";
        boolean isNotException = true;
        try {
            clazz.setDown_dept_yn(down_dept_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUp_dept_cdShouldNotThrowException() {
        DeptListForm clazz = new DeptListForm();
        boolean isNotException = true;
        try {
            clazz.getUp_dept_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUp_dept_cdWithStringUpDeptCdShouldNotThrowException() {
        DeptListForm clazz = new DeptListForm();
        String up_dept_cd = "up_dept_cd";
        boolean isNotException = true;
        try {
            clazz.setUp_dept_cd(up_dept_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
