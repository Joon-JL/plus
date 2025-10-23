package com.sec.common.clmscom.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class CLMSCommonVOTest {

    @Test(timeout = 20000)
    public void testGetSys_cdShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getSys_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAll_sumShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getAll_sum();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAll_sumWithStringAllSumShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String all_sum = "all_sum";
        boolean isNotException = true;
        try {
            clazz.setAll_sum(all_sum);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDis_ynShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getDis_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDis_ynWithStringDisYnShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String dis_yn = "dis_yn";
        boolean isNotException = true;
        try {
            clazz.setDis_yn(dis_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetItem_cdShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getItem_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetItem_cdWithStringItemCdShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String item_cd = "item_cd";
        boolean isNotException = true;
        try {
            clazz.setItem_cd(item_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSys_cdWithStringSysCdShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
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
    public void testGetGrp_cdShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getGrp_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGrp_cdWithStringGrpCdShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String grp_cd = "grp_cd";
        boolean isNotException = true;
        try {
            clazz.setGrp_cd(grp_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCdShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getCd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCdWithStringCdShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String cd = "cd";
        boolean isNotException = true;
        try {
            clazz.setCd(cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCd_nmShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getCd_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCd_nmWithStringCdNmShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String cd_nm = "cd_nm";
        boolean isNotException = true;
        try {
            clazz.setCd_nm(cd_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCd_abbr_nmShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getCd_abbr_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCd_abbr_nmWithStringCdAbbrNmShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String cd_abbr_nm = "cd_abbr_nm";
        boolean isNotException = true;
        try {
            clazz.setCd_abbr_nm(cd_abbr_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCd_nm_engShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getCd_nm_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCd_nm_engWithStringCdNmEngShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String cd_nm_eng = "cd_nm_eng";
        boolean isNotException = true;
        try {
            clazz.setCd_nm_eng(cd_nm_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCd_abbr_nm_engShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getCd_abbr_nm_eng();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCd_abbr_nm_engWithStringCdAbbrNmEngShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String cd_abbr_nm_eng = "cd_abbr_nm_eng";
        boolean isNotException = true;
        try {
            clazz.setCd_abbr_nm_eng(cd_abbr_nm_eng);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSelectCdShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getSelectCd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSelectCdWithStringSelectCdShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String selectCd = "selectCd";
        boolean isNotException = true;
        try {
            clazz.setSelectCd(selectCd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMenu_gbnShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getMenu_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMenu_gbnWithStringMenuGbnShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String menu_gbn = "menu_gbn";
        boolean isNotException = true;
        try {
            clazz.setMenu_gbn(menu_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMenu_idShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
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
        CLMSCommonVO clazz = new CLMSCommonVO();
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
    public void testGetUse_ynShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
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
        CLMSCommonVO clazz = new CLMSCommonVO();
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
    public void testGetUp_menu_idShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getUp_menu_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUp_menu_idWithStringUpMenuIdShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String up_menu_id = "up_menu_id";
        boolean isNotException = true;
        try {
            clazz.setUp_menu_id(up_menu_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMenu_nmShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getMenu_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMenu_nmWithStringMenuNmShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String menu_nm = "menu_nm";
        boolean isNotException = true;
        try {
            clazz.setMenu_nm(menu_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMenu_urlShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getMenu_url();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMenu_urlWithStringMenuUrlShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String menu_url = "menu_url";
        boolean isNotException = true;
        try {
            clazz.setMenu_url(menu_url);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetForward_urlShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getForward_url();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetForward_urlWithStringForwardUrlShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String forward_url = "forward_url";
        boolean isNotException = true;
        try {
            clazz.setForward_url(forward_url);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUp_orgnz_cdShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getUp_orgnz_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUp_orgnz_cdWithStringUpOrgnzCdShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String up_orgnz_cd = "up_orgnz_cd";
        boolean isNotException = true;
        try {
            clazz.setUp_orgnz_cd(up_orgnz_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDept_cdShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
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
        CLMSCommonVO clazz = new CLMSCommonVO();
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
        CLMSCommonVO clazz = new CLMSCommonVO();
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
        CLMSCommonVO clazz = new CLMSCommonVO();
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
    public void testGetDept_levelShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getDept_level();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDept_levelWithIntegerDeptLevelShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        Integer dept_level = mock(Integer.class);
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
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getDept_order();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDept_orderWithIntegerDeptOrderShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        Integer dept_order = mock(Integer.class);
        boolean isNotException = true;
        try {
            clazz.setDept_order(dept_order);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDown_dept_ynShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
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
        CLMSCommonVO clazz = new CLMSCommonVO();
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
    public void testGetUp_dept_cdShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
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
        CLMSCommonVO clazz = new CLMSCommonVO();
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
    public void testGetTarget_tableShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getTarget_table();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTarget_tableWithStringTargetTableShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String target_table = "target_table";
        boolean isNotException = true;
        try {
            clazz.setTarget_table(target_table);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTarget_keyShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getTarget_key();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTarget_keyWithStringTargetKeyShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String target_key = "target_key";
        boolean isNotException = true;
        try {
            clazz.setTarget_key(target_key);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTarget_clobShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getTarget_clob();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTarget_clobWithStringTargetClobShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String target_clob = "target_clob";
        boolean isNotException = true;
        try {
            clazz.setTarget_clob(target_clob);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPage_gbnShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getPage_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPage_gbnWithStringPageGbnShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String page_gbn = "page_gbn";
        boolean isNotException = true;
        try {
            clazz.setPage_gbn(page_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTop_roleShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getTop_role();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTop_roleWithStringTopRoleShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String top_role = "top_role";
        boolean isNotException = true;
        try {
            clazz.setTop_role(top_role);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUser_idShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
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
        CLMSCommonVO clazz = new CLMSCommonVO();
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
    public void testGetUser_nmShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
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
        CLMSCommonVO clazz = new CLMSCommonVO();
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
    public void testGetLocaleShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getLocale();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLocaleWithStringLocaleShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String locale = "locale";
        boolean isNotException = true;
        try {
            clazz.setLocale(locale);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRole_cdShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
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
        CLMSCommonVO clazz = new CLMSCommonVO();
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
        CLMSCommonVO clazz = new CLMSCommonVO();
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
        CLMSCommonVO clazz = new CLMSCommonVO();
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
    public void testGetRegion_cdShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
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
        CLMSCommonVO clazz = new CLMSCommonVO();
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
    public void testGetRegion_nmShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
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
        CLMSCommonVO clazz = new CLMSCommonVO();
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
    public void testGetRegion_midcateShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getRegion_midcate();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRegion_midcateWithStringRegionMidcateShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String region_midcate = "region_midcate";
        boolean isNotException = true;
        try {
            clazz.setRegion_midcate(region_midcate);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRegion_midcate_nmShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getRegion_midcate_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRegion_midcate_nmWithStringRegionMidcateNmShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String region_midcate_nm = "region_midcate_nm";
        boolean isNotException = true;
        try {
            clazz.setRegion_midcate_nm(region_midcate_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEmailShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
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
        CLMSCommonVO clazz = new CLMSCommonVO();
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
    public void testGetDel_ynShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getDel_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_ynWithStringDelYnShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String del_yn = "del_yn";
        boolean isNotException = true;
        try {
            clazz.setDel_yn(del_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDiv_nmShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getDiv_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDiv_nmWithStringDivNmShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String div_nm = "div_nm";
        boolean isNotException = true;
        try {
            clazz.setDiv_nm(div_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUseman_mng_itm1ShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        boolean isNotException = true;
        try {
            clazz.getUseman_mng_itm1();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUseman_mng_itm1WithStringUsemanMngItm1ShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
        String useman_mng_itm1 = "useman_mng_itm1";
        boolean isNotException = true;
        try {
            clazz.setUseman_mng_itm1(useman_mng_itm1);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetComp_cdShouldNotThrowException() {
        CLMSCommonVO clazz = new CLMSCommonVO();
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
        CLMSCommonVO clazz = new CLMSCommonVO();
        String comp_cd = "comp_cd";
        boolean isNotException = true;
        try {
            clazz.setComp_cd(comp_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
