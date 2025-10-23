package com.sds.secframework.user.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class UserVOTest {

    @Test(timeout = 20000)
    public void testGetUser_idShouldNotThrowException() {
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
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
    public void testGetRole_cdShouldNotThrowException() {
        UserVO clazz = new UserVO();
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
        UserVO clazz = new UserVO();
        String role_cd = "role_cd";
        boolean isNotException = true;
        try {
            clazz.setRole_cd(role_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
