package com.sds.secframework.auth.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class AuthFormTest {

    @Test(timeout = 20000)
    public void testGetRole_cdShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
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
        AuthForm clazz = new AuthForm();
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
    public void testGetRole_nmShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getRole_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRole_nmWithStringRoleNmShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String role_nm = "role_nm";
        boolean isNotException = true;
        try {
            clazz.setRole_nm(role_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCommentsShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getComments();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCommentsWithStringCommentsShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String comments = "comments";
        boolean isNotException = true;
        try {
            clazz.setComments(comments);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUse_ynShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getUse_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUse_ynWithStringUseYnShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String use_yn = "use_yn";
        boolean isNotException = true;
        try {
            clazz.setUse_yn(use_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_idShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
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
        AuthForm clazz = new AuthForm();
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
    public void testGetReg_dtShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
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
        AuthForm clazz = new AuthForm();
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
        AuthForm clazz = new AuthForm();
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
        AuthForm clazz = new AuthForm();
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
    public void testGetMod_dtShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
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
        AuthForm clazz = new AuthForm();
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
    public void testGetSrch_cntnt_typeShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_cntnt_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_cntnt_typeWithStringSrchCntntTypeShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String srch_cntnt_type = "srch_cntnt_type";
        boolean isNotException = true;
        try {
            clazz.setSrch_cntnt_type(srch_cntnt_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_cntntShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_cntnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_cntntWithStringSrchCntntShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String srch_cntnt = "srch_cntnt";
        boolean isNotException = true;
        try {
            clazz.setSrch_cntnt(srch_cntnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUser_nmShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getUser_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUser_nmWithStringUserNmShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String user_nm = "user_nm";
        boolean isNotException = true;
        try {
            clazz.setUser_nm(user_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDept_cdShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
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
        AuthForm clazz = new AuthForm();
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
        AuthForm clazz = new AuthForm();
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
        AuthForm clazz = new AuthForm();
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
    public void testGetJikgup_nmShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getJikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetJikgup_nmWithStringJikgupNmShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String jikgup_nm = "jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setJikgup_nm(jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMapping_user_idShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getMapping_user_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMapping_user_idWithStringArrayMappingUserIdShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String[] mapping_user_id = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setMapping_user_id(mapping_user_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMapping_auth_cdShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getMapping_auth_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMapping_auth_cdWithStringArrayMappingAuthCdShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String[] mapping_auth_cd = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setMapping_auth_cd(mapping_auth_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMapping_menu_idShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getMapping_menu_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMapping_menu_idWithStringArrayMappingMenuIdShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String[] mapping_menu_id = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setMapping_menu_id(mapping_menu_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMapping_method_infosShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getMapping_method_infos();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMapping_method_infosWithStringArrayMappingMethodInfosShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String[] mapping_method_infos = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setMapping_method_infos(mapping_method_infos);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMapping_page_idShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getMapping_page_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMapping_page_idWithStringArrayMappingPageIdShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String[] mapping_page_id = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setMapping_page_id(mapping_page_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUser_typeShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getUser_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUser_typeWithStringUserTypeShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String user_type = "user_type";
        boolean isNotException = true;
        try {
            clazz.setUser_type(user_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMenu_idShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getMenu_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMenu_idWithStringMenuIdShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String menu_id = "menu_id";
        boolean isNotException = true;
        try {
            clazz.setMenu_id(menu_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAccess_controlShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getAccess_control();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAccess_controlWithStringAccessControlShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String access_control = "access_control";
        boolean isNotException = true;
        try {
            clazz.setAccess_control(access_control);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAuth_cdShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
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
        AuthForm clazz = new AuthForm();
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
        AuthForm clazz = new AuthForm();
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
        AuthForm clazz = new AuthForm();
        String auth_nm = "auth_nm";
        boolean isNotException = true;
        try {
            clazz.setAuth_nm(auth_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_auth_cdShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_auth_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_auth_cdWithStringSrchAuthCdShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String srch_auth_cd = "srch_auth_cd";
        boolean isNotException = true;
        try {
            clazz.setSrch_auth_cd(srch_auth_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_use_ynShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_use_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_use_ynWithStringSrchUseYnShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String srch_use_yn = "srch_use_yn";
        boolean isNotException = true;
        try {
            clazz.setSrch_use_yn(srch_use_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetChkValuesShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getChkValues();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetChkValuesWithStringArrayChkValuesShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String[] chkValues = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setChkValues(chkValues);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_role_cntntShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_role_cntnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_role_cntntWithStringSrchRoleCntntShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String srch_role_cntnt = "srch_role_cntnt";
        boolean isNotException = true;
        try {
            clazz.setSrch_role_cntnt(srch_role_cntnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_auth_cntntShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_auth_cntnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_auth_cntntWithStringSrchAuthCntntShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String srch_auth_cntnt = "srch_auth_cntnt";
        boolean isNotException = true;
        try {
            clazz.setSrch_auth_cntnt(srch_auth_cntnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_user_cntnt_typeShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_user_cntnt_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_user_cntnt_typeWithStringSrchUserCntntTypeShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String srch_user_cntnt_type = "srch_user_cntnt_type";
        boolean isNotException = true;
        try {
            clazz.setSrch_user_cntnt_type(srch_user_cntnt_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_user_cntntShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_user_cntnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_user_cntntWithStringSrchUserCntntShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String srch_user_cntnt = "srch_user_cntnt";
        boolean isNotException = true;
        try {
            clazz.setSrch_user_cntnt(srch_user_cntnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_page_cntntShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_page_cntnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_page_cntntWithStringSrchPageCntntShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String srch_page_cntnt = "srch_page_cntnt";
        boolean isNotException = true;
        try {
            clazz.setSrch_page_cntnt(srch_page_cntnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetChk_idShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getChk_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetChk_idWithStringArrayChkIdShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String[] chk_id = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setChk_id(chk_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSys_cdsShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getSys_cds();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSys_cdsWithStringArraySysCdsShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String[] sys_cds = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setSys_cds(sys_cds);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRole_cdsShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getRole_cds();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRole_cdsWithStringArrayRoleCdsShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String[] role_cds = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setRole_cds(role_cds);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRole_nmsShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getRole_nms();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRole_nmsWithStringArrayRoleNmsShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String[] role_nms = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setRole_nms(role_nms);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAuth_cdsShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getAuth_cds();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_cdsWithStringArrayAuthCdsShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String[] auth_cds = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setAuth_cds(auth_cds);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAuth_nmsShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getAuth_nms();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_nmsWithStringArrayAuthNmsShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String[] auth_nms = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setAuth_nms(auth_nms);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCommentssShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getCommentss();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCommentssWithStringArrayCommentssShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String[] commentss = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setCommentss(commentss);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUse_ynsShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getUse_yns();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUse_ynsWithStringArrayUseYnsShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String[] use_yns = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setUse_yns(use_yns);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPackage_nmShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getPackage_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPackage_nmWithStringPackageNmShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String package_nm = "package_nm";
        boolean isNotException = true;
        try {
            clazz.setPackage_nm(package_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetClass_nmShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
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
        AuthForm clazz = new AuthForm();
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
        AuthForm clazz = new AuthForm();
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
        AuthForm clazz = new AuthForm();
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
    public void testGetComp_cdShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getComp_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetComp_cdWithStringCompCdShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String comp_cd = "comp_cd";
        boolean isNotException = true;
        try {
            clazz.setComp_cd(comp_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_comp_cdShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_comp_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_comp_cdWithStringSrchCompCdShouldNotThrowException() {
        AuthForm clazz = new AuthForm();
        String srch_comp_cd = "srch_comp_cd";
        boolean isNotException = true;
        try {
            clazz.setSrch_comp_cd(srch_comp_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
