package com.sds.secframework.auth.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class ClassMethodRoleVOTest {

    @Test(timeout = 20000)
    public void testConstructorNotNull() {
        ClassMethodRoleVO obj = new ClassMethodRoleVO();
        assert obj != null;
    }

    @Test(timeout = 20000)
    public void testGetSys_cdShouldNotThrowException() {
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
    public void testGetCheck_ynShouldNotThrowException() {
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
    public void testGetRole_cdShouldNotThrowException() {
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
    public void testGetReg_idShouldNotThrowException() {
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
    public void testGetSrch_typeShouldNotThrowException() {
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
    public void testGetAvailable_role_listShouldNotThrowException() {
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
        boolean isNotException = true;
        try {
            clazz.getAvailable_role_list();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAvailable_role_listWithStringArrayAvailableRoleListShouldNotThrowException() {
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
        String[] available_role_list = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setAvailable_role_list(available_role_list);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAssigned_role_listShouldNotThrowException() {
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
        boolean isNotException = true;
        try {
            clazz.getAssigned_role_list();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAssigned_role_listWithStringArrayAssignedRoleListShouldNotThrowException() {
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
        String[] assigned_role_list = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setAssigned_role_list(assigned_role_list);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCheckboxShouldNotThrowException() {
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
        ClassMethodRoleVO clazz = new ClassMethodRoleVO();
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
