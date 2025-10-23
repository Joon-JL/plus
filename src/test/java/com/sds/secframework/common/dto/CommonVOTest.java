package com.sds.secframework.common.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.util.ObjectCopyUtil;
import static org.mockito.Mockito.mock;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class CommonVOTest {

    @Test(timeout = 20000)
    public void testGetSession_infsysnmShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_infsysnm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_infsysnmWithStringSessionInfsysnmShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_infsysnm = "session_infsysnm";
        boolean isNotException = true;
        try {
            clazz.setSession_infsysnm(session_infsysnm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_related_productsShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_related_products();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_related_productsWithStringSessionRelatedProductsShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_related_products = "session_related_products";
        boolean isNotException = true;
        try {
            clazz.setSession_related_products(session_related_products);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSys_cdShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSys_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testIsAuth_insertShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.isAuth_insert();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_insertWithBooleanAuthInsertShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean auth_insert = false;
        boolean isNotException = true;
        try {
            clazz.setAuth_insert(auth_insert);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_emailShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_email();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_emailWithStringSessionEmailShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_email = "session_email";
        boolean isNotException = true;
        try {
            clazz.setSession_email(session_email);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_single_idShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_single_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_single_idWithStringSessionSingleIdShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_single_id = "session_single_id";
        boolean isNotException = true;
        try {
            clazz.setSession_single_id(session_single_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTop_roleShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
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
        CommonVO clazz = new CommonVO();
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
    public void testGetSession_division_cdShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_division_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_division_cdWithStringSessionDivisionCdShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_division_cd = "session_division_cd";
        boolean isNotException = true;
        try {
            clazz.setSession_division_cd(session_division_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testIsAuth_modifyShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.isAuth_modify();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_modifyWithBooleanAuthModifyShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean auth_modify = false;
        boolean isNotException = true;
        try {
            clazz.setAuth_modify(auth_modify);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testIsAuth_deleteShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.isAuth_delete();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_deleteWithBooleanAuthDeleteShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean auth_delete = false;
        boolean isNotException = true;
        try {
            clazz.setAuth_delete(auth_delete);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testIsAuth_searchShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.isAuth_search();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_searchWithBooleanAuthSearchShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean auth_search = false;
        boolean isNotException = true;
        try {
            clazz.setAuth_search(auth_search);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRef_keyShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getRef_key();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRef_keyWithStringRefKeyShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String ref_key = "ref_key";
        boolean isNotException = true;
        try {
            clazz.setRef_key(ref_key);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_bigclsfcnShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getFile_bigclsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFile_bigclsfcnWithStringFileBigclsfcnShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String file_bigclsfcn = "file_bigclsfcn";
        boolean isNotException = true;
        try {
            clazz.setFile_bigclsfcn(file_bigclsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_midclsfcnShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getFile_midclsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFile_midclsfcnWithStringFileMidclsfcnShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String file_midclsfcn = "file_midclsfcn";
        boolean isNotException = true;
        try {
            clazz.setFile_midclsfcn(file_midclsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_smlclsfcnShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getFile_smlclsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFile_smlclsfcnWithStringFileSmlclsfcnShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String file_smlclsfcn = "file_smlclsfcn";
        boolean isNotException = true;
        try {
            clazz.setFile_smlclsfcn(file_smlclsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetgSortStatShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getgSortStat();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetgSortStatWithStringGSortStatShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String gSortStat = "gSortStat";
        boolean isNotException = true;
        try {
            clazz.setgSortStat(gSortStat);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFileInfosShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getFileInfos();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFileInfosWithStringFileInfosShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String fileInfos = "fileInfos";
        boolean isNotException = true;
        try {
            clazz.setFileInfos(fileInfos);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSys_cdWithStringSysCdShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
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
    public void testGetUser_idShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
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
        CommonVO clazz = new CommonVO();
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
    public void testGetSession_user_idShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_user_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_user_idWithStringSessionUserIdShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_user_id = "session_user_id";
        boolean isNotException = true;
        try {
            clazz.setSession_user_id(session_user_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_user_nmShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_user_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_user_nmWithStringSessionUserNmShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_user_nm = "session_user_nm";
        boolean isNotException = true;
        try {
            clazz.setSession_user_nm(session_user_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_dept_cdShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_dept_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_dept_cdWithStringSessionDeptCdShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_dept_cd = "session_dept_cd";
        boolean isNotException = true;
        try {
            clazz.setSession_dept_cd(session_dept_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_dept_nmShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_dept_nmWithStringSessionDeptNmShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_dept_nm = "session_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setSession_dept_nm(session_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_clms_user_orgnzShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_clms_user_orgnz();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_clms_user_orgnzWithStringSessionClmsUserOrgnzShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_clms_user_orgnz = "session_clms_user_orgnz";
        boolean isNotException = true;
        try {
            clazz.setSession_clms_user_orgnz(session_clms_user_orgnz);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_resp_operdivShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_resp_operdiv();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_resp_operdivWithStringSessionRespOperdivShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_resp_operdiv = "session_resp_operdiv";
        boolean isNotException = true;
        try {
            clazz.setSession_resp_operdiv(session_resp_operdiv);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_comp_cdShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_comp_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_comp_cdWithStringSessionCompCdShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_comp_cd = "session_comp_cd";
        boolean isNotException = true;
        try {
            clazz.setSession_comp_cd(session_comp_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_comp_nmShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_comp_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_comp_nmWithStringSessionCompNmShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_comp_nm = "session_comp_nm";
        boolean isNotException = true;
        try {
            clazz.setSession_comp_nm(session_comp_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_user_localeShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_user_locale();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_user_localeWithStringSessionUserLocaleShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_user_locale = "session_user_locale";
        boolean isNotException = true;
        try {
            clazz.setSession_user_locale(session_user_locale);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_user_role_cdsShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_user_role_cds();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_user_role_cdsWithArrayListSessionUserRoleCdsShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        ArrayList session_user_role_cds = mock(ArrayList.class);
        boolean isNotException = true;
        try {
            clazz.setSession_user_role_cds(session_user_role_cds);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_jikgup_cdShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_jikgup_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_jikgup_cdWithStringSessionJikgupCdShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_jikgup_cd = "session_jikgup_cd";
        boolean isNotException = true;
        try {
            clazz.setSession_jikgup_cd(session_jikgup_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUser_jikgup_nmShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getUser_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUser_jikgup_nmWithStringSessionJikgupNmShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_jikgup_nm = "session_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setUser_jikgup_nm(session_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_jikgup_nmShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_jikgup_nmWithStringSessionJikgupNmShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_jikgup_nm = "session_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setSession_jikgup_nm(session_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStart_indexShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getStart_index();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetStart_indexWithStringStartIndexShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String start_index = "start_index";
        boolean isNotException = true;
        try {
            clazz.setStart_index(start_index);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEnd_indexShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getEnd_index();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEnd_indexWithStringEndIndexShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String end_index = "end_index";
        boolean isNotException = true;
        try {
            clazz.setEnd_index(end_index);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDoSearchShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getDoSearch();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDoSearchWithStringDoSearchShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String doSearch = "doSearch";
        boolean isNotException = true;
        try {
            clazz.setDoSearch(doSearch);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGSortStatShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getGSortStat();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGSortStatWithStringSortStatShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String sortStat = "sortStat";
        boolean isNotException = true;
        try {
            clazz.setGSortStat(sortStat);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCurPageShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getCurPage();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCurPageWithStringCurPageShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String curPage = "curPage";
        boolean isNotException = true;
        try {
            clazz.setCurPage(curPage);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMenu_idShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
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
        CommonVO clazz = new CommonVO();
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
    public void testGetDbTypeShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getDbType();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDbTypeWithStringDbTypeShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String dbType = "dbType";
        boolean isNotException = true;
        try {
            clazz.setDbType(dbType);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_comp_telShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_comp_tel();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_comp_telWithStringSessionCompTelShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_comp_tel = "session_comp_tel";
        boolean isNotException = true;
        try {
            clazz.setSession_comp_tel(session_comp_tel);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_mobileShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_mobile();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_mobileWithStringSessionMobileShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_mobile = "session_mobile";
        boolean isNotException = true;
        try {
            clazz.setSession_mobile(session_mobile);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_up_level_dept_cdShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_up_level_dept_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_up_level_dept_cdWithStringSessionUpLevelDeptCdShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_up_level_dept_cd = "session_up_level_dept_cd";
        boolean isNotException = true;
        try {
            clazz.setSession_up_level_dept_cd(session_up_level_dept_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_blngt_orgnzShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_blngt_orgnz();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_blngt_orgnzWithStringSessionBlngtOrgnzShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_blngt_orgnz = "session_blngt_orgnz";
        boolean isNotException = true;
        try {
            clazz.setSession_blngt_orgnz(session_blngt_orgnz);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_manager_ynShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_manager_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_manager_ynWithStringSessionManagerYnShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_manager_yn = "session_manager_yn";
        boolean isNotException = true;
        try {
            clazz.setSession_manager_yn(session_manager_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_support_ynShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_support_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_support_ynWithStringSessionSupportYnShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_support_yn = "session_support_yn";
        boolean isNotException = true;
        try {
            clazz.setSession_support_yn(session_support_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_in_dept_cdShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_in_dept_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_in_dept_cdWithStringSessionInDeptCdShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_in_dept_cd = "session_in_dept_cd";
        boolean isNotException = true;
        try {
            clazz.setSession_in_dept_cd(session_in_dept_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_blngt_orgnz_nmShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_blngt_orgnz_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_blngt_orgnz_nmWithStringSessionBlngtOrgnzNmShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_blngt_orgnz_nm = "session_blngt_orgnz_nm";
        boolean isNotException = true;
        try {
            clazz.setSession_blngt_orgnz_nm(session_blngt_orgnz_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_blngt_orgnz_nm_abbrShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_blngt_orgnz_nm_abbr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_blngt_orgnz_nm_abbrWithStringSessionBlngtOrgnzNmAbbrShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_blngt_orgnz_nm_abbr = "session_blngt_orgnz_nm_abbr";
        boolean isNotException = true;
        try {
            clazz.setSession_blngt_orgnz_nm_abbr(session_blngt_orgnz_nm_abbr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_auth_apnt_ynShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_auth_apnt_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_auth_apnt_ynWithStringSessionAuthApntYnShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_auth_apnt_yn = "session_auth_apnt_yn";
        boolean isNotException = true;
        try {
            clazz.setSession_auth_apnt_yn(session_auth_apnt_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_auth_apnt_deptShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_auth_apnt_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_auth_apnt_deptWithStringSessionAuthApntDeptShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_auth_apnt_dept = "session_auth_apnt_dept";
        boolean isNotException = true;
        try {
            clazz.setSession_auth_apnt_dept(session_auth_apnt_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_page_countShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_page_count();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_page_countWithStringSrchPageCountShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String srch_page_count = "srch_page_count";
        boolean isNotException = true;
        try {
            clazz.setSrch_page_count(srch_page_count);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_auth_comp_cdShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        boolean isNotException = true;
        try {
            clazz.getSession_auth_comp_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_auth_comp_cdWithStringSessionAuthCompCdShouldNotThrowException() {
        CommonVO clazz = new CommonVO();
        String session_auth_comp_cd = "session_auth_comp_cd";
        boolean isNotException = true;
        try {
            clazz.setSession_auth_comp_cd(session_auth_comp_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
