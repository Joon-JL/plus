package com.sds.secframework.user.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class UserManagerVOTest {

    @Test(timeout = 20000)
    public void testGetArea_nmShouldNotThrowException() {
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
    public void testGetDept_nm_engShouldNotThrowException() {
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
    public void testGetDuty_apbtman_idShouldNotThrowException() {
        UserManagerVO clazz = new UserManagerVO();
        boolean isNotException = true;
        try {
            clazz.getDuty_apbtman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDuty_apbtman_idWithStringDutyApbtmanIdShouldNotThrowException() {
        UserManagerVO clazz = new UserManagerVO();
        String duty_apbtman_id = "duty_apbtman_id";
        boolean isNotException = true;
        try {
            clazz.setDuty_apbtman_id(duty_apbtman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGthr_cnst_dtShouldNotThrowException() {
        UserManagerVO clazz = new UserManagerVO();
        boolean isNotException = true;
        try {
            clazz.getGthr_cnst_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGthr_cnst_dtWithStringGthrCnstDtShouldNotThrowException() {
        UserManagerVO clazz = new UserManagerVO();
        String gthr_cnst_dt = "gthr_cnst_dt";
        boolean isNotException = true;
        try {
            clazz.setGthr_cnst_dt(gthr_cnst_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_cntnt_typeShouldNotThrowException() {
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
        boolean isNotException = true;
        try {
            clazz.getBusiness_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBusiness_nmWithStringBusinessNmShouldNotThrowException() {
        UserManagerVO clazz = new UserManagerVO();
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
    public void testGetDivision_cdShouldNotThrowException() {
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
    public void testGetArea_cdShouldNotThrowException() {
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
    public void testGetBlngt_orgnzShouldNotThrowException() {
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
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
        UserManagerVO clazz = new UserManagerVO();
        String auto_rnew_yn = "auto_rnew_yn";
        boolean isNotException = true;
        try {
            clazz.setAuto_rnew_yn(auto_rnew_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSec_reviewer_typeShouldNotThrowException() {
        UserManagerVO clazz = new UserManagerVO();
        boolean isNotException = true;
        try {
            clazz.getSec_reviewer_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSec_reviewer_typeWithStringSecReviewerTypeShouldNotThrowException() {
        UserManagerVO clazz = new UserManagerVO();
        String sec_reviewer_type = "sec_reviewer_type";
        boolean isNotException = true;
        try {
            clazz.setSec_reviewer_type(sec_reviewer_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUser_srtShouldNotThrowException() {
        UserManagerVO clazz = new UserManagerVO();
        boolean isNotException = true;
        try {
            clazz.getUser_srt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUser_srtWithStringUserSrtShouldNotThrowException() {
        UserManagerVO clazz = new UserManagerVO();
        String user_srt = "user_srt";
        boolean isNotException = true;
        try {
            clazz.setUser_srt(user_srt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGthr_cnst_ynShouldNotThrowException() {
        UserManagerVO clazz = new UserManagerVO();
        boolean isNotException = true;
        try {
            clazz.getGthr_cnst_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGthr_cnst_ynWithStringGthrCnstYnShouldNotThrowException() {
        UserManagerVO clazz = new UserManagerVO();
        String gthr_cnst_yn = "gthr_cnst_yn";
        boolean isNotException = true;
        try {
            clazz.setGthr_cnst_yn(gthr_cnst_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUser_real_nmShouldNotThrowException() {
        UserManagerVO clazz = new UserManagerVO();
        boolean isNotException = true;
        try {
            clazz.getUser_real_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUser_real_nmWithStringUserRealNmShouldNotThrowException() {
        UserManagerVO clazz = new UserManagerVO();
        String user_real_nm = "user_real_nm";
        boolean isNotException = true;
        try {
            clazz.setUser_real_nm(user_real_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUser_real_nm_engShouldNotThrowException() {
        UserManagerVO clazz = new UserManagerVO();
        boolean isNotException = true;
        try {
            clazz.getUser_real_nm_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUser_real_nm_engWithStringUserRealNmEngShouldNotThrowException() {
        UserManagerVO clazz = new UserManagerVO();
        String user_real_nm_eng = "user_real_nm_eng";
        boolean isNotException = true;
        try {
            clazz.setUser_real_nm_eng(user_real_nm_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetApproval_ynShouldNotThrowException() {
        UserManagerVO clazz = new UserManagerVO();
        boolean isNotException = true;
        try {
            clazz.getApproval_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetApproval_ynWithStringApprovalYnShouldNotThrowException() {
        UserManagerVO clazz = new UserManagerVO();
        String approval_yn = "approval_yn";
        boolean isNotException = true;
        try {
            clazz.setApproval_yn(approval_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
