package com.sds.secframework.user.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class UserFormTest {

    @Test(timeout = 20000)
    public void testGetUser_idShouldNotThrowException() {
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
    public void testGetDept_nm_engShouldNotThrowException() {
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
    public void testGetAccess_ynShouldNotThrowException() {
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
    public void testGetRes_noShouldNotThrowException() {
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
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
        UserForm clazz = new UserForm();
        String area_nm = "area_nm";
        boolean isNotException = true;
        try {
            clazz.setArea_nm(area_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
