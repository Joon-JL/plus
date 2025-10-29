package com.sds.secframework.user.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class UserManagerFormTest {

    @Test(timeout = 20000)
    public void testGetSrch_cntnt_typeShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
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
        UserManagerForm clazz = new UserManagerForm();
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
        UserManagerForm clazz = new UserManagerForm();
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
        UserManagerForm clazz = new UserManagerForm();
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
    public void testGetSrch_role_cdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_role_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_role_cdWithStringSrchRoleCdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String srch_role_cd = "srch_role_cd";
        boolean isNotException = true;
        try {
            clazz.setSrch_role_cd(srch_role_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_auth_apnt_ynShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_auth_apnt_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_auth_apnt_ynWithStringSrchAuthApntYnShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String srch_auth_apnt_yn = "srch_auth_apnt_yn";
        boolean isNotException = true;
        try {
            clazz.setSrch_auth_apnt_yn(srch_auth_apnt_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_access_ynShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_access_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_access_ynWithStringSrchAccessYnShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String srch_access_yn = "srch_access_yn";
        boolean isNotException = true;
        try {
            clazz.setSrch_access_yn(srch_access_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAuth_apnt_ynShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getAuth_apnt_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_apnt_ynWithStringAuthApntYnShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String auth_apnt_yn = "auth_apnt_yn";
        boolean isNotException = true;
        try {
            clazz.setAuth_apnt_yn(auth_apnt_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAuth_apnt_deptShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getAuth_apnt_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_apnt_deptWithStringAuthApntDeptShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String auth_apnt_dept = "auth_apnt_dept";
        boolean isNotException = true;
        try {
            clazz.setAuth_apnt_dept(auth_apnt_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAuth_apnt_dept_nmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getAuth_apnt_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_apnt_dept_nmWithStringAuthApntDeptNmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String auth_apnt_dept_nm = "auth_apnt_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setAuth_apnt_dept_nm(auth_apnt_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAccess_ynShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getAccess_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAccess_ynWithStringAccessYnShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String access_yn = "access_yn";
        boolean isNotException = true;
        try {
            clazz.setAccess_yn(access_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetLoc_gbnShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getLoc_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLoc_gbnWithStringLocGbnShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String loc_gbn = "loc_gbn";
        boolean isNotException = true;
        try {
            clazz.setLoc_gbn(loc_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRole_cdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
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
        UserManagerForm clazz = new UserManagerForm();
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
    public void testGetSys_cdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
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
        UserManagerForm clazz = new UserManagerForm();
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
    public void testGetLogin_idShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getLogin_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLogin_idWithStringLoginIdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String login_id = "login_id";
        boolean isNotException = true;
        try {
            clazz.setLogin_id(login_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEmail_rcv_ynShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getEmail_rcv_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEmail_rcv_ynWithStringEmailRcvYnShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String email_rcv_yn = "email_rcv_yn";
        boolean isNotException = true;
        try {
            clazz.setEmail_rcv_yn(email_rcv_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPopup_user_nmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getPopup_user_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPopup_user_nmWithStringPopupUserNmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String popup_user_nm = "popup_user_nm";
        boolean isNotException = true;
        try {
            clazz.setPopup_user_nm(popup_user_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRes_noShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getRes_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRes_noWithStringResNoShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String res_no = "res_no";
        boolean isNotException = true;
        try {
            clazz.setRes_no(res_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEmp_noShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getEmp_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEmp_noWithStringEmpNoShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String emp_no = "emp_no";
        boolean isNotException = true;
        try {
            clazz.setEmp_no(emp_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUser_nmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
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
        UserManagerForm clazz = new UserManagerForm();
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
    public void testGetChinese_nmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getChinese_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetChinese_nmWithStringChineseNmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String chinese_nm = "chinese_nm";
        boolean isNotException = true;
        try {
            clazz.setChinese_nm(chinese_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetComp_cdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
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
        UserManagerForm clazz = new UserManagerForm();
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
    public void testGetComp_nmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getComp_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetComp_nmWithStringCompNmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String comp_nm = "comp_nm";
        boolean isNotException = true;
        try {
            clazz.setComp_nm(comp_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBusiness_cdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getBusiness_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBusiness_cdWithStringBusinessCdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String business_cd = "business_cd";
        boolean isNotException = true;
        try {
            clazz.setBusiness_cd(business_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBusiness_nmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getBusiness_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDivision_cdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getDivision_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDivision_cdWithStringDivisionCdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String division_cd = "division_cd";
        boolean isNotException = true;
        try {
            clazz.setDivision_cd(division_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDivision_nmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getDivision_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDivision_nmWithStringDivisionNmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String division_nm = "division_nm";
        boolean isNotException = true;
        try {
            clazz.setDivision_nm(division_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBusiness_nmWithStringBusinessNmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String business_nm = "business_nm";
        boolean isNotException = true;
        try {
            clazz.setBusiness_nm(business_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetArea_cdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getArea_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetArea_cdWithStringAreaCdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String area_cd = "area_cd";
        boolean isNotException = true;
        try {
            clazz.setArea_cd(area_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDept_cdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
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
        UserManagerForm clazz = new UserManagerForm();
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
    public void testGetIn_dept_cdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
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
        UserManagerForm clazz = new UserManagerForm();
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
    public void testGetDept_nmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
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
        UserManagerForm clazz = new UserManagerForm();
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
    public void testGetChange_dept_ymdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getChange_dept_ymd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetChange_dept_ymdWithStringChangeDeptYmdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String change_dept_ymd = "change_dept_ymd";
        boolean isNotException = true;
        try {
            clazz.setChange_dept_ymd(change_dept_ymd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOrigin_dept_cdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getOrigin_dept_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOrigin_dept_cdWithStringOriginDeptCdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String origin_dept_cd = "origin_dept_cd";
        boolean isNotException = true;
        try {
            clazz.setOrigin_dept_cd(origin_dept_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOrigin_dept_nmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getOrigin_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOrigin_dept_nmWithStringOriginDeptNmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String origin_dept_nm = "origin_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setOrigin_dept_nm(origin_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAppoint_cdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getAppoint_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAppoint_cdWithStringAppointCdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String appoint_cd = "appoint_cd";
        boolean isNotException = true;
        try {
            clazz.setAppoint_cd(appoint_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGrp_join_ymdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getGrp_join_ymd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGrp_join_ymdWithStringGrpJoinYmdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String grp_join_ymd = "grp_join_ymd";
        boolean isNotException = true;
        try {
            clazz.setGrp_join_ymd(grp_join_ymd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetJoin_termShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getJoin_term();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetJoin_termWithStringJoinTermShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String join_term = "join_term";
        boolean isNotException = true;
        try {
            clazz.setJoin_term(join_term);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetJoin_levelShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getJoin_level();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetJoin_levelWithStringJoinLevelShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String join_level = "join_level";
        boolean isNotException = true;
        try {
            clazz.setJoin_level(join_level);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetJoin_ymdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getJoin_ymd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetJoin_ymdWithStringJoinYmdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String join_ymd = "join_ymd";
        boolean isNotException = true;
        try {
            clazz.setJoin_ymd(join_ymd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRaise_ymdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getRaise_ymd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRaise_ymdWithStringRaiseYmdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String raise_ymd = "raise_ymd";
        boolean isNotException = true;
        try {
            clazz.setRaise_ymd(raise_ymd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPromotion_ymdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getPromotion_ymd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPromotion_ymdWithStringPromotionYmdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String promotion_ymd = "promotion_ymd";
        boolean isNotException = true;
        try {
            clazz.setPromotion_ymd(promotion_ymd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNext_raise_ymShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getNext_raise_ym();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNext_raise_ymWithStringNextRaiseYmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String next_raise_ym = "next_raise_ym";
        boolean isNotException = true;
        try {
            clazz.setNext_raise_ym(next_raise_ym);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNext_promotion_ymShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getNext_promotion_ym();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNext_promotion_ymWithStringNextPromotionYmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String next_promotion_ym = "next_promotion_ym";
        boolean isNotException = true;
        try {
            clazz.setNext_promotion_ym(next_promotion_ym);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRetirement_ymdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getRetirement_ymd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRetirement_ymdWithStringRetirementYmdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String retirement_ymd = "retirement_ymd";
        boolean isNotException = true;
        try {
            clazz.setRetirement_ymd(retirement_ymd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSuspension_ymdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getSuspension_ymd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSuspension_ymdWithStringSuspensionYmdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String suspension_ymd = "suspension_ymd";
        boolean isNotException = true;
        try {
            clazz.setSuspension_ymd(suspension_ymd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReinstatment_ymdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getReinstatment_ymd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReinstatment_ymdWithStringReinstatmentYmdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String reinstatment_ymd = "reinstatment_ymd";
        boolean isNotException = true;
        try {
            clazz.setReinstatment_ymd(reinstatment_ymd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetJikmu_cdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getJikmu_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetJikmu_cdWithStringJikmuCdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String jikmu_cd = "jikmu_cd";
        boolean isNotException = true;
        try {
            clazz.setJikmu_cd(jikmu_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetJikmu_nmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getJikmu_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetJikmu_nmWithStringJikmuNmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String jikmu_nm = "jikmu_nm";
        boolean isNotException = true;
        try {
            clazz.setJikmu_nm(jikmu_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetJikgup_cdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getJikgup_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetJikgup_cdWithStringJikgupCdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String jikgup_cd = "jikgup_cd";
        boolean isNotException = true;
        try {
            clazz.setJikgup_cd(jikgup_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetJikgup_nmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
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
        UserManagerForm clazz = new UserManagerForm();
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
    public void testGetGrp_jikgup_cdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getGrp_jikgup_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGrp_jikgup_cdWithStringGrpJikgupCdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String grp_jikgup_cd = "grp_jikgup_cd";
        boolean isNotException = true;
        try {
            clazz.setGrp_jikgup_cd(grp_jikgup_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGrp_jikgup_nmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getGrp_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGrp_jikgup_nmWithStringGrpJikgupNmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String grp_jikgup_nm = "grp_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setGrp_jikgup_nm(grp_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetJikchek_cdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getJikchek_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetJikchek_cdWithStringJikchekCdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String jikchek_cd = "jikchek_cd";
        boolean isNotException = true;
        try {
            clazz.setJikchek_cd(jikchek_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSchooling_cdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getSchooling_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSchooling_cdWithStringSchoolingCdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String schooling_cd = "schooling_cd";
        boolean isNotException = true;
        try {
            clazz.setSchooling_cd(schooling_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGraduate_school_cdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getGraduate_school_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGraduate_school_cdWithStringGraduateSchoolCdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String graduate_school_cd = "graduate_school_cd";
        boolean isNotException = true;
        try {
            clazz.setGraduate_school_cd(graduate_school_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGraduate_school_nmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getGraduate_school_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGraduate_school_nmWithStringGraduateSchoolNmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String graduate_school_nm = "graduate_school_nm";
        boolean isNotException = true;
        try {
            clazz.setGraduate_school_nm(graduate_school_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMaster_major_cdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getMaster_major_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMaster_major_cdWithStringMasterMajorCdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String master_major_cd = "master_major_cd";
        boolean isNotException = true;
        try {
            clazz.setMaster_major_cd(master_major_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMaster_major_nmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getMaster_major_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMaster_major_nmWithStringMasterMajorNmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String master_major_nm = "master_major_nm";
        boolean isNotException = true;
        try {
            clazz.setMaster_major_nm(master_major_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUniv_cdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getUniv_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUniv_cdWithStringUnivCdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String univ_cd = "univ_cd";
        boolean isNotException = true;
        try {
            clazz.setUniv_cd(univ_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBachelor_major_cdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getBachelor_major_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBachelor_major_cdWithStringBachelorMajorCdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String bachelor_major_cd = "bachelor_major_cd";
        boolean isNotException = true;
        try {
            clazz.setBachelor_major_cd(bachelor_major_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBachelor_major_nmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getBachelor_major_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBachelor_major_nmWithStringBachelorMajorNmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String bachelor_major_nm = "bachelor_major_nm";
        boolean isNotException = true;
        try {
            clazz.setBachelor_major_nm(bachelor_major_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMilitaryShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getMilitary();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMilitaryWithStringMilitaryShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String military = "military";
        boolean isNotException = true;
        try {
            clazz.setMilitary(military);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSex_cdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getSex_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSex_cdWithStringSexCdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String sex_cd = "sex_cd";
        boolean isNotException = true;
        try {
            clazz.setSex_cd(sex_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAbo_bloodShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getAbo_blood();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAbo_bloodWithStringAboBloodShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String abo_blood = "abo_blood";
        boolean isNotException = true;
        try {
            clazz.setAbo_blood(abo_blood);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRh_bloodShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getRh_blood();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRh_bloodWithStringRhBloodShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String rh_blood = "rh_blood";
        boolean isNotException = true;
        try {
            clazz.setRh_blood(rh_blood);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHobbyShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getHobby();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHobbyWithStringHobbyShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String hobby = "hobby";
        boolean isNotException = true;
        try {
            clazz.setHobby(hobby);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBirth_ymdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getBirth_ymd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBirth_ymdWithStringBirthYmdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String birth_ymd = "birth_ymd";
        boolean isNotException = true;
        try {
            clazz.setBirth_ymd(birth_ymd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSolar_ynShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getSolar_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSolar_ynWithStringSolarYnShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String solar_yn = "solar_yn";
        boolean isNotException = true;
        try {
            clazz.setSolar_yn(solar_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHeightShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getHeight();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHeightWithStringHeightShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String height = "height";
        boolean isNotException = true;
        try {
            clazz.setHeight(height);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetWeightShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getWeight();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetWeightWithStringWeightShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String weight = "weight";
        boolean isNotException = true;
        try {
            clazz.setWeight(weight);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMarriage_ymdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getMarriage_ymd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMarriage_ymdWithStringMarriageYmdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String marriage_ymd = "marriage_ymd";
        boolean isNotException = true;
        try {
            clazz.setMarriage_ymd(marriage_ymd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRegion_cdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getRegion_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRegion_cdWithStringRegionCdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String region_cd = "region_cd";
        boolean isNotException = true;
        try {
            clazz.setRegion_cd(region_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEmailShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getEmail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEmailWithStringEmailShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String email = "email";
        boolean isNotException = true;
        try {
            clazz.setEmail(email);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHome_addressShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getHome_address();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHome_addressWithStringHomeAddressShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String home_address = "home_address";
        boolean isNotException = true;
        try {
            clazz.setHome_address(home_address);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetComp_zip_cdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getComp_zip_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetComp_zip_cdWithStringCompZipCdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String comp_zip_cd = "comp_zip_cd";
        boolean isNotException = true;
        try {
            clazz.setComp_zip_cd(comp_zip_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetComp_addressShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getComp_address();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetComp_addressWithStringCompAddressShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String comp_address = "comp_address";
        boolean isNotException = true;
        try {
            clazz.setComp_address(comp_address);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOffice_tel_noShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getOffice_tel_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOffice_tel_noWithStringOfficeTelNoShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String office_tel_no = "office_tel_no";
        boolean isNotException = true;
        try {
            clazz.setOffice_tel_no(office_tel_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHome_tel_noShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getHome_tel_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHome_tel_noWithStringHomeTelNoShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String home_tel_no = "home_tel_no";
        boolean isNotException = true;
        try {
            clazz.setHome_tel_no(home_tel_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMobile_noShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getMobile_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMobile_noWithStringMobileNoShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String mobile_no = "mobile_no";
        boolean isNotException = true;
        try {
            clazz.setMobile_no(mobile_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStatusShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getStatus();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetStatusWithStringStatusShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String status = "status";
        boolean isNotException = true;
        try {
            clazz.setStatus(status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetLast_localeShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getLast_locale();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLast_localeWithStringLastLocaleShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String last_locale = "last_locale";
        boolean isNotException = true;
        try {
            clazz.setLast_locale(last_locale);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSys_gbnShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getSys_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSys_gbnWithStringSysGbnShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String sys_gbn = "sys_gbn";
        boolean isNotException = true;
        try {
            clazz.setSys_gbn(sys_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dtShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
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
        UserManagerForm clazz = new UserManagerForm();
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
    public void testGetSrch_user_cntnt_typeShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
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
        UserManagerForm clazz = new UserManagerForm();
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
        UserManagerForm clazz = new UserManagerForm();
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
        UserManagerForm clazz = new UserManagerForm();
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
    public void testGetUser_typeShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
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
        UserManagerForm clazz = new UserManagerForm();
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
    public void testGetSearch_comboShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getSearch_combo();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSearch_comboWithStringSearchComboShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String search_combo = "search_combo";
        boolean isNotException = true;
        try {
            clazz.setSearch_combo(search_combo);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSearch_nameShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getSearch_name();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSearch_nameWithStringSearchNameShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String search_name = "search_name";
        boolean isNotException = true;
        try {
            clazz.setSearch_name(search_name);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFlagShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getFlag();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFlagWithStringFlagShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String flag = "flag";
        boolean isNotException = true;
        try {
            clazz.setFlag(flag);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetIncludeLowerShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getIncludeLower();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetIncludeLowerWithStringIncludeLowerShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String includeLower = "includeLower";
        boolean isNotException = true;
        try {
            clazz.setIncludeLower(includeLower);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEmpnmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getEmpnm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEmpnmWithStringEmpnmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String empnm = "empnm";
        boolean isNotException = true;
        try {
            clazz.setEmpnm(empnm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRegion_nmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getRegion_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRegion_nmWithStringRegionNmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String region_nm = "region_nm";
        boolean isNotException = true;
        try {
            clazz.setRegion_nm(region_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRegion_nm_engShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getRegion_nm_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRegion_nm_engWithStringRegionNmEngShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String region_nm_eng = "region_nm_eng";
        boolean isNotException = true;
        try {
            clazz.setRegion_nm_eng(region_nm_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEmployee_typeShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getEmployee_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEmployee_typeWithStringEmployeeTypeShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String employee_type = "employee_type";
        boolean isNotException = true;
        try {
            clazz.setEmployee_type(employee_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUser_levelShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getUser_level();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUser_levelWithStringUserLevelShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String user_level = "user_level";
        boolean isNotException = true;
        try {
            clazz.setUser_level(user_level);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetArea_nmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getArea_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetArea_nmWithStringAreaNmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String area_nm = "area_nm";
        boolean isNotException = true;
        try {
            clazz.setArea_nm(area_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBlngt_orgnzShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getBlngt_orgnz();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBlngt_orgnzWithStringBlngtOrgnzShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String blngt_orgnz = "blngt_orgnz";
        boolean isNotException = true;
        try {
            clazz.setBlngt_orgnz(blngt_orgnz);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetResp_operdivShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getResp_operdiv();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetResp_operdivWithStringRespOperdivShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String resp_operdiv = "resp_operdiv";
        boolean isNotException = true;
        try {
            clazz.setResp_operdiv(resp_operdiv);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUser_idShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getUser_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUser_idWithStringUserIdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String user_id = "user_id";
        boolean isNotException = true;
        try {
            clazz.setUser_id(user_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUser_nm_engShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getUser_nm_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUser_nm_engWithStringUserNmEngShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String user_nm_eng = "user_nm_eng";
        boolean isNotException = true;
        try {
            clazz.setUser_nm_eng(user_nm_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSingle_idShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getSingle_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSingle_idWithStringSingleIdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String single_id = "single_id";
        boolean isNotException = true;
        try {
            clazz.setSingle_id(single_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSingle_epidShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getSingle_epid();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSingle_epidWithStringSingleEpidShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String single_epid = "single_epid";
        boolean isNotException = true;
        try {
            clazz.setSingle_epid(single_epid);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetComp_nm_engShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getComp_nm_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetComp_nm_engWithStringCompNmEngShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String comp_nm_eng = "comp_nm_eng";
        boolean isNotException = true;
        try {
            clazz.setComp_nm_eng(comp_nm_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBusiness_nm_engShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getBusiness_nm_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBusiness_nm_engWithStringBusinessNmEngShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String business_nm_eng = "business_nm_eng";
        boolean isNotException = true;
        try {
            clazz.setBusiness_nm_eng(business_nm_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDivision_nm_engShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getDivision_nm_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDivision_nm_engWithStringDivisionNmEngShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String division_nm_eng = "division_nm_eng";
        boolean isNotException = true;
        try {
            clazz.setDivision_nm_eng(division_nm_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetJikgun_cdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getJikgun_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetJikgun_cdWithStringJikgunCdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String jikgun_cd = "jikgun_cd";
        boolean isNotException = true;
        try {
            clazz.setJikgun_cd(jikgun_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetJikgun_nmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getJikgun_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetJikgun_nmWithStringJikgunNmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String jikgun_nm = "jikgun_nm";
        boolean isNotException = true;
        try {
            clazz.setJikgun_nm(jikgun_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetJikgun_nm_engShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getJikgun_nm_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetJikgun_nm_engWithStringJikgunNmEngShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String jikgun_nm_eng = "jikgun_nm_eng";
        boolean isNotException = true;
        try {
            clazz.setJikgun_nm_eng(jikgun_nm_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetJikgup_nm_engShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getJikgup_nm_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetJikgup_nm_engWithStringJikgupNmEngShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String jikgup_nm_eng = "jikgup_nm_eng";
        boolean isNotException = true;
        try {
            clazz.setJikgup_nm_eng(jikgup_nm_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGrp_jikgup_nm_engShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getGrp_jikgup_nm_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGrp_jikgup_nm_engWithStringGrpJikgupNmEngShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String grp_jikgup_nm_eng = "grp_jikgup_nm_eng";
        boolean isNotException = true;
        try {
            clazz.setGrp_jikgup_nm_eng(grp_jikgup_nm_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetJikmu_nm_engShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getJikmu_nm_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetJikmu_nm_engWithStringJikmuNmEngShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String jikmu_nm_eng = "jikmu_nm_eng";
        boolean isNotException = true;
        try {
            clazz.setJikmu_nm_eng(jikmu_nm_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGraduate_school_nm_engShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getGraduate_school_nm_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGraduate_school_nm_engWithStringGraduateSchoolNmEngShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String graduate_school_nm_eng = "graduate_school_nm_eng";
        boolean isNotException = true;
        try {
            clazz.setGraduate_school_nm_eng(graduate_school_nm_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMaster_major_nm_engShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getMaster_major_nm_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMaster_major_nm_engWithStringMasterMajorNmEngShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String master_major_nm_eng = "master_major_nm_eng";
        boolean isNotException = true;
        try {
            clazz.setMaster_major_nm_eng(master_major_nm_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUniv_nmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getUniv_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUniv_nmWithStringUnivNmShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String univ_nm = "univ_nm";
        boolean isNotException = true;
        try {
            clazz.setUniv_nm(univ_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUniv_nm_engShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getUniv_nm_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUniv_nm_engWithStringUnivNmEngShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String univ_nm_eng = "univ_nm_eng";
        boolean isNotException = true;
        try {
            clazz.setUniv_nm_eng(univ_nm_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBachelor_major_nm_engShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getBachelor_major_nm_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBachelor_major_nm_engWithStringBachelorMajorNmEngShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String bachelor_major_nm_eng = "bachelor_major_nm_eng";
        boolean isNotException = true;
        try {
            clazz.setBachelor_major_nm_eng(bachelor_major_nm_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMna_mng_ynShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getMna_mng_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMna_mng_ynWithStringMnaMngYnShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String mna_mng_yn = "mna_mng_yn";
        boolean isNotException = true;
        try {
            clazz.setMna_mng_yn(mna_mng_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSerch_detail_divShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getSerch_detail_div();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSerch_detail_divWithStringSerchDetailDivShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String serch_detail_div = "serch_detail_div";
        boolean isNotException = true;
        try {
            clazz.setSerch_detail_div(serch_detail_div);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSerch_detail_velShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getSerch_detail_vel();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSerch_detail_velWithStringSerchDetailVelShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String serch_detail_vel = "serch_detail_vel";
        boolean isNotException = true;
        try {
            clazz.setSerch_detail_vel(serch_detail_vel);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPop_user_idShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getPop_user_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPop_user_idWithStringPopUserIdShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String pop_user_id = "pop_user_id";
        boolean isNotException = true;
        try {
            clazz.setPop_user_id(pop_user_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAuto_rnew_ynShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        boolean isNotException = true;
        try {
            clazz.getAuto_rnew_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuto_rnew_ynWithStringAutoRnewYnShouldNotThrowException() {
        UserManagerForm clazz = new UserManagerForm();
        String auto_rnew_yn = "auto_rnew_yn";
        boolean isNotException = true;
        try {
            clazz.setAuto_rnew_yn(auto_rnew_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
