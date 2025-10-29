package com.sds.secframework.dept.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class DeptVOTest {

    @Test(timeout = 20000)
    public void testGetDept_cdShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
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
        DeptVO clazz = new DeptVO();
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
        DeptVO clazz = new DeptVO();
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
        DeptVO clazz = new DeptVO();
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
        DeptVO clazz = new DeptVO();
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
        DeptVO clazz = new DeptVO();
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
    public void testGetDept_levelShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        boolean isNotException = true;
        try {
            clazz.getDept_level();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDept_levelWithIntDeptLevelShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        int dept_level = 0;
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
        DeptVO clazz = new DeptVO();
        boolean isNotException = true;
        try {
            clazz.getDept_order();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDept_orderWithIntDeptOrderShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        int dept_order = 0;
        boolean isNotException = true;
        try {
            clazz.setDept_order(dept_order);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetIn_dept_cdShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
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
        DeptVO clazz = new DeptVO();
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
    public void testGetUp_dept_cdShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
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
        DeptVO clazz = new DeptVO();
        String up_dept_cd = "up_dept_cd";
        boolean isNotException = true;
        try {
            clazz.setUp_dept_cd(up_dept_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUp_dept_nmShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        boolean isNotException = true;
        try {
            clazz.getUp_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUp_dept_nmWithStringUpDeptNmShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        String up_dept_nm = "up_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setUp_dept_nm(up_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetComp_cdShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
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
        DeptVO clazz = new DeptVO();
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
        DeptVO clazz = new DeptVO();
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
        DeptVO clazz = new DeptVO();
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
        DeptVO clazz = new DeptVO();
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
        DeptVO clazz = new DeptVO();
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
        DeptVO clazz = new DeptVO();
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
        DeptVO clazz = new DeptVO();
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
    public void testGetDown_dept_ynShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
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
        DeptVO clazz = new DeptVO();
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
    public void testGetDept_mgr_emp_noShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        boolean isNotException = true;
        try {
            clazz.getDept_mgr_emp_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDept_mgr_emp_noWithStringDeptMgrEmpNoShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        String dept_mgr_emp_no = "dept_mgr_emp_no";
        boolean isNotException = true;
        try {
            clazz.setDept_mgr_emp_no(dept_mgr_emp_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDept_mgr_nmShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        boolean isNotException = true;
        try {
            clazz.getDept_mgr_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDept_mgr_nmWithStringDeptMgrNmShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        String dept_mgr_nm = "dept_mgr_nm";
        boolean isNotException = true;
        try {
            clazz.setDept_mgr_nm(dept_mgr_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDept_mgr_jikgup_nmShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        boolean isNotException = true;
        try {
            clazz.getDept_mgr_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDept_mgr_jikgup_nmWithStringDeptMgrJikgupNmShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        String dept_mgr_jikgup_nm = "dept_mgr_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setDept_mgr_jikgup_nm(dept_mgr_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDept_mgr_jikgup_nm_engShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        boolean isNotException = true;
        try {
            clazz.getDept_mgr_jikgup_nm_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDept_mgr_jikgup_nm_engWithStringDeptMgrJikgupNmEngShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        String dept_mgr_jikgup_nm_eng = "dept_mgr_jikgup_nm_eng";
        boolean isNotException = true;
        try {
            clazz.setDept_mgr_jikgup_nm_eng(dept_mgr_jikgup_nm_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_idShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
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
        DeptVO clazz = new DeptVO();
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
        DeptVO clazz = new DeptVO();
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
        DeptVO clazz = new DeptVO();
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
        DeptVO clazz = new DeptVO();
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
        DeptVO clazz = new DeptVO();
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
        DeptVO clazz = new DeptVO();
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
        DeptVO clazz = new DeptVO();
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
    public void testGetSrch_dept_nmShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_dept_nmWithStringSrchDeptNmShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        String srch_dept_nm = "srch_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_dept_nm(srch_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTree_dept_cdShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        boolean isNotException = true;
        try {
            clazz.getTree_dept_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTree_dept_cdWithStringTreeDeptCdShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        String tree_dept_cd = "tree_dept_cd";
        boolean isNotException = true;
        try {
            clazz.setTree_dept_cd(tree_dept_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSelect_dept_cdShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        boolean isNotException = true;
        try {
            clazz.getSelect_dept_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSelect_dept_cdWithStringSelectDeptCdShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        String select_dept_cd = "select_dept_cd";
        boolean isNotException = true;
        try {
            clazz.setSelect_dept_cd(select_dept_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSearch_nameShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
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
        DeptVO clazz = new DeptVO();
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
    public void testGetSub_org_cdShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        boolean isNotException = true;
        try {
            clazz.getSub_org_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSub_org_cdWithStringSubOrgCdShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        String sub_org_cd = "sub_org_cd";
        boolean isNotException = true;
        try {
            clazz.setSub_org_cd(sub_org_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSub_org_nmShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        boolean isNotException = true;
        try {
            clazz.getSub_org_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSub_org_nmWithStringSubOrgNmShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        String sub_org_nm = "sub_org_nm";
        boolean isNotException = true;
        try {
            clazz.setSub_org_nm(sub_org_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSub_org_nm_engShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        boolean isNotException = true;
        try {
            clazz.getSub_org_nm_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSub_org_nm_engWithStringSubOrgNmEngShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        String sub_org_nm_eng = "sub_org_nm_eng";
        boolean isNotException = true;
        try {
            clazz.setSub_org_nm_eng(sub_org_nm_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUp_dept_nm_engShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        boolean isNotException = true;
        try {
            clazz.getUp_dept_nm_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUp_dept_nm_engWithStringUpDeptNmEngShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
        String up_dept_nm_eng = "up_dept_nm_eng";
        boolean isNotException = true;
        try {
            clazz.setUp_dept_nm_eng(up_dept_nm_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetComp_nm_engShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
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
        DeptVO clazz = new DeptVO();
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
    public void testGetMenu_idShouldNotThrowException() {
        DeptVO clazz = new DeptVO();
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
        DeptVO clazz = new DeptVO();
        String menu_id = "menu_id";
        boolean isNotException = true;
        try {
            clazz.setMenu_id(menu_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
