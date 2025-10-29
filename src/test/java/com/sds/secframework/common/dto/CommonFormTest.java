package com.sds.secframework.common.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.util.ObjectCopyUtil;
import static org.mockito.Mockito.mock;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class CommonFormTest {

    @Test(timeout = 20000)
    public void testGetSession_infsysnmShouldNotThrowException() {
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
    public void testGetSession_jikgup_cdShouldNotThrowException() {
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
        boolean isNotException = true;
        try {
            clazz.getUser_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_jikgup_nmWithStringSessionJikgupNmShouldNotThrowException() {
        CommonForm clazz = new CommonForm();
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
    public void testGetSession_user_role_cdsShouldNotThrowException() {
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
    public void testGetSession_jikgup_nmShouldNotThrowException() {
        CommonForm clazz = new CommonForm();
        boolean isNotException = true;
        try {
            clazz.getSession_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetForward_urlShouldNotThrowException() {
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
    public void testGetStart_indexShouldNotThrowException() {
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
    public void testGetReturn_messageShouldNotThrowException() {
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
    public void testGetReturn_titleShouldNotThrowException() {
        CommonForm clazz = new CommonForm();
        boolean isNotException = true;
        try {
            clazz.getReturn_title();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReturn_titleWithStringReturnTitleShouldNotThrowException() {
        CommonForm clazz = new CommonForm();
        String return_title = "return_title";
        boolean isNotException = true;
        try {
            clazz.setReturn_title(return_title);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_comp_telShouldNotThrowException() {
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
        CommonForm clazz = new CommonForm();
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
