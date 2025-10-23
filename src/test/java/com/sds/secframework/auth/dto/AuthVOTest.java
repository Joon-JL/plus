package com.sds.secframework.auth.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class AuthVOTest {

    @Test(timeout = 20000)
    public void testGetRole_cdShouldNotThrowException() {
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
        AuthVO clazz = new AuthVO();
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
    public void testGetMapping_comp_cdShouldNotThrowException() {
        AuthVO clazz = new AuthVO();
        boolean isNotException = true;
        try {
            clazz.getMapping_comp_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMapping_comp_cdWithStringArrayMappingCompCdShouldNotThrowException() {
        AuthVO clazz = new AuthVO();
        String[] mapping_comp_cd = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setMapping_comp_cd(mapping_comp_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMng_comp_cdShouldNotThrowException() {
        AuthVO clazz = new AuthVO();
        boolean isNotException = true;
        try {
            clazz.getMng_comp_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMng_comp_cdWithStringMngCompCdShouldNotThrowException() {
        AuthVO clazz = new AuthVO();
        String mng_comp_cd = "mng_comp_cd";
        boolean isNotException = true;
        try {
            clazz.setMng_comp_cd(mng_comp_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMng_user_idShouldNotThrowException() {
        AuthVO clazz = new AuthVO();
        boolean isNotException = true;
        try {
            clazz.getMng_user_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMng_user_idWithStringMngUserIdShouldNotThrowException() {
        AuthVO clazz = new AuthVO();
        String mng_user_id = "mng_user_id";
        boolean isNotException = true;
        try {
            clazz.setMng_user_id(mng_user_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
