package com.sds.secframework.singleIF.dto;

import java.lang.String;
import model.outldap.samsung.net.Organization;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class OrganizationVOTest {

    @Test(timeout = 20000)
    public void testGetMenu_idShouldNotThrowException() {
        OrganizationVO clazz = new OrganizationVO();
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
        OrganizationVO clazz = new OrganizationVO();
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
    public void testGetPage_idShouldNotThrowException() {
        OrganizationVO clazz = new OrganizationVO();
        boolean isNotException = true;
        try {
            clazz.getPage_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPage_idWithStringPageIdShouldNotThrowException() {
        OrganizationVO clazz = new OrganizationVO();
        String page_id = "page_id";
        boolean isNotException = true;
        try {
            clazz.setPage_id(page_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUser_idShouldNotThrowException() {
        OrganizationVO clazz = new OrganizationVO();
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
        OrganizationVO clazz = new OrganizationVO();
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
    public void testGetDoSearchShouldNotThrowException() {
        OrganizationVO clazz = new OrganizationVO();
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
        OrganizationVO clazz = new OrganizationVO();
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
    public void testGetSrch_dept_cntnt_typeShouldNotThrowException() {
        OrganizationVO clazz = new OrganizationVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_dept_cntnt_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_dept_cntnt_typeWithStringSrchDeptCntntTypeShouldNotThrowException() {
        OrganizationVO clazz = new OrganizationVO();
        String srch_dept_cntnt_type = "srch_dept_cntnt_type";
        boolean isNotException = true;
        try {
            clazz.setSrch_dept_cntnt_type(srch_dept_cntnt_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_dept_cntntShouldNotThrowException() {
        OrganizationVO clazz = new OrganizationVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_dept_cntnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_dept_cntntWithStringSrchDeptCntntShouldNotThrowException() {
        OrganizationVO clazz = new OrganizationVO();
        String srch_dept_cntnt = "srch_dept_cntnt";
        boolean isNotException = true;
        try {
            clazz.setSrch_dept_cntnt(srch_dept_cntnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
