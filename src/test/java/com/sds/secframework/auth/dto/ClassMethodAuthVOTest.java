package com.sds.secframework.auth.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class ClassMethodAuthVOTest {

    @Test(timeout = 20000)
    public void testConstructorNotNull() {
        ClassMethodAuthVO obj = new ClassMethodAuthVO();
        assert obj != null;
    }

    @Test(timeout = 20000)
    public void testGetSys_cdShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        boolean isNotException = true;
        try {
            clazz.getSys_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSys_cdWithStringSysCdShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        String sys_cd = "sys_cd";
        boolean isNotException = true;
        try {
            clazz.setSys_cd(sys_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetClass_nmShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        boolean isNotException = true;
        try {
            clazz.getClass_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetClass_nmWithStringClassNmShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        String class_nm = "class_nm";
        boolean isNotException = true;
        try {
            clazz.setClass_nm(class_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMethod_nmShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        boolean isNotException = true;
        try {
            clazz.getMethod_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMethod_nmWithStringMethodNmShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        String method_nm = "method_nm";
        boolean isNotException = true;
        try {
            clazz.setMethod_nm(method_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAuth_cdShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        boolean isNotException = true;
        try {
            clazz.getAuth_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_cdWithStringAuthCdShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        String auth_cd = "auth_cd";
        boolean isNotException = true;
        try {
            clazz.setAuth_cd(auth_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_idShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
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
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
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
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
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
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
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
    public void testGetReg_jikgup_nmShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        boolean isNotException = true;
        try {
            clazz.getReg_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_jikgup_nmWithStringRegJikgupNmShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        String reg_jikgup_nm = "reg_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setReg_jikgup_nm(reg_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dept_nmShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        boolean isNotException = true;
        try {
            clazz.getReg_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_dept_nmWithStringRegDeptNmShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        String reg_dept_nm = "reg_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setReg_dept_nm(reg_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dtShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
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
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
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
    public void testGetMod_idShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
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
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
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
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
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
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
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
    public void testGetMod_jikgup_nmShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        boolean isNotException = true;
        try {
            clazz.getMod_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_jikgup_nmWithStringModJikgupNmShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        String mod_jikgup_nm = "mod_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setMod_jikgup_nm(mod_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMod_dept_nmShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        boolean isNotException = true;
        try {
            clazz.getMod_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_dept_nmWithStringModDeptNmShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        String mod_dept_nm = "mod_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setMod_dept_nm(mod_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMod_dtShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
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
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
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
    public void testGetCheck_ynShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        boolean isNotException = true;
        try {
            clazz.getCheck_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCheck_ynWithStringCheckYnShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        String check_yn = "check_yn";
        boolean isNotException = true;
        try {
            clazz.setCheck_yn(check_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_typeShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_typeWithStringSrchTypeShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        String srch_type = "srch_type";
        boolean isNotException = true;
        try {
            clazz.setSrch_type(srch_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetClass_srch_wordShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        boolean isNotException = true;
        try {
            clazz.getClass_srch_word();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetClass_srch_wordWithStringClassSrchWordShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        String class_srch_word = "class_srch_word";
        boolean isNotException = true;
        try {
            clazz.setClass_srch_word(class_srch_word);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMethod_srch_wordShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        boolean isNotException = true;
        try {
            clazz.getMethod_srch_word();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMethod_srch_wordWithStringMethodSrchWordShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        String method_srch_word = "method_srch_word";
        boolean isNotException = true;
        try {
            clazz.setMethod_srch_word(method_srch_word);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTable_nameShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        boolean isNotException = true;
        try {
            clazz.getTable_name();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTable_nameWithStringTableNameShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        String table_name = "table_name";
        boolean isNotException = true;
        try {
            clazz.setTable_name(table_name);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAvailable_auth_listShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        boolean isNotException = true;
        try {
            clazz.getAvailable_auth_list();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAvailable_auth_listWithStringArrayAvailableAuthListShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        String[] available_auth_list = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setAvailable_auth_list(available_auth_list);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAssigned_auth_listShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        boolean isNotException = true;
        try {
            clazz.getAssigned_auth_list();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAssigned_auth_listWithStringArrayAssignedAuthListShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        String[] assigned_auth_list = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setAssigned_auth_list(assigned_auth_list);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCheckboxShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        boolean isNotException = true;
        try {
            clazz.getCheckbox();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCheckboxWithStringArrayCheckboxShouldNotThrowException() {
        ClassMethodAuthVO clazz = new ClassMethodAuthVO();
        String[] checkbox = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setCheckbox(checkbox);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
