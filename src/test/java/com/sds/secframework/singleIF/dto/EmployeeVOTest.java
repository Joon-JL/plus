package com.sds.secframework.singleIF.dto;

import java.lang.String;
import org.junit.Test;
import model.outldap.samsung.net.Employee;
import static org.mockito.Mockito.mock;

public class EmployeeVOTest {

    @Test(timeout = 20000)
    public void testGetCheck_ynShouldNotThrowException() {
        EmployeeVO clazz = new EmployeeVO();
        boolean isNotException = true;
        try {
            clazz.getCheck_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCheck_ynWithStringCheckYnShouldNotThrowException() {
        EmployeeVO clazz = new EmployeeVO();
        String check_yn = "check_yn";
        boolean isNotException = true;
        try {
            clazz.setCheck_yn(check_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMenu_idShouldNotThrowException() {
        EmployeeVO clazz = new EmployeeVO();
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
        EmployeeVO clazz = new EmployeeVO();
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
        EmployeeVO clazz = new EmployeeVO();
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
        EmployeeVO clazz = new EmployeeVO();
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
        EmployeeVO clazz = new EmployeeVO();
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
        EmployeeVO clazz = new EmployeeVO();
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
        EmployeeVO clazz = new EmployeeVO();
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
        EmployeeVO clazz = new EmployeeVO();
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
    public void testGetSrch_user_cntnt_typeShouldNotThrowException() {
        EmployeeVO clazz = new EmployeeVO();
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
        EmployeeVO clazz = new EmployeeVO();
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
        EmployeeVO clazz = new EmployeeVO();
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
        EmployeeVO clazz = new EmployeeVO();
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
    public void testGetAdd_mail_userShouldNotThrowException() {
        EmployeeVO clazz = new EmployeeVO();
        boolean isNotException = true;
        try {
            clazz.getAdd_mail_user();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAdd_mail_userWithStringAddMailUserShouldNotThrowException() {
        EmployeeVO clazz = new EmployeeVO();
        String add_mail_user = "add_mail_user";
        boolean isNotException = true;
        try {
            clazz.setAdd_mail_user(add_mail_user);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
