package com.sds.secframework.auth.dto;

import java.lang.String;
import org.junit.Test;
import java.util.List;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class ClassMethodAuthFormTest {

    @Test(timeout = 20000)
    public void testConstructorNotNull() {
        ClassMethodAuthForm obj = new ClassMethodAuthForm();
        assert obj != null;
    }

    @Test(timeout = 20000)
    public void testGetSys_cdShouldNotThrowException() {
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
    public void testGetRole_cdShouldNotThrowException() {
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
    public void testGetIsModifyShouldNotThrowException() {
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
        boolean isNotException = true;
        try {
            clazz.getIsModify();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetIsModifyWithStringIsModifyShouldNotThrowException() {
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
        String isModify = "isModify";
        boolean isNotException = true;
        try {
            clazz.setIsModify(isModify);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAvailable_auth_listShouldNotThrowException() {
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
        boolean isNotException = true;
        try {
            clazz.getAvailable_auth_list();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAvailable_auth_listWithListAvailableAuthListShouldNotThrowException() {
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
        List available_auth_list = mock(List.class);
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
        boolean isNotException = true;
        try {
            clazz.getAssigned_auth_list();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAssigned_auth_listWithListAssignedAuthListShouldNotThrowException() {
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
        List assigned_auth_list = mock(List.class);
        boolean isNotException = true;
        try {
            clazz.setAssigned_auth_list(assigned_auth_list);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBoard_nmShouldNotThrowException() {
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
        boolean isNotException = true;
        try {
            clazz.getBoard_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBoard_nmWithStringBoardNmShouldNotThrowException() {
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
        String board_nm = "board_nm";
        boolean isNotException = true;
        try {
            clazz.setBoard_nm(board_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRow_cntShouldNotThrowException() {
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
        boolean isNotException = true;
        try {
            clazz.getRow_cnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRow_cntWithIntRowCntShouldNotThrowException() {
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
        int row_cnt = 0;
        boolean isNotException = true;
        try {
            clazz.setRow_cnt(row_cnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetClass_method_auth_listShouldNotThrowException() {
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
        boolean isNotException = true;
        try {
            clazz.getClass_method_auth_list();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetClass_method_auth_listWithListClassMethodAuthListShouldNotThrowException() {
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
        List class_method_auth_list = mock(List.class);
        boolean isNotException = true;
        try {
            clazz.setClass_method_auth_list(class_method_auth_list);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReturn_messageShouldNotThrowException() {
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
        boolean isNotException = true;
        try {
            clazz.getReturn_message();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReturn_messageWithStringReturnMessageShouldNotThrowException() {
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
        String return_message = "return_message";
        boolean isNotException = true;
        try {
            clazz.setReturn_message(return_message);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAuth_cdShouldNotThrowException() {
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
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
    public void testGetAuth_nmShouldNotThrowException() {
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
        boolean isNotException = true;
        try {
            clazz.getAuth_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_nmWithStringAuthNmShouldNotThrowException() {
        ClassMethodAuthForm clazz = new ClassMethodAuthForm();
        String auth_nm = "auth_nm";
        boolean isNotException = true;
        try {
            clazz.setAuth_nm(auth_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
