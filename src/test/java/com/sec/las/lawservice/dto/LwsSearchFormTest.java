package com.sec.las.lawservice.dto;

import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class LwsSearchFormTest {

    @Test(timeout = 20000)
    public void testGetSrch_typeShouldNotThrowException() {
        LwsSearchForm clazz = new LwsSearchForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_typeWithStringSrchTypeShouldNotThrowException() {
        LwsSearchForm clazz = new LwsSearchForm();
        String srch_type = "srch_type";
        boolean isNotException = true;
        try {
            clazz.setSrch_type(srch_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_yearShouldNotThrowException() {
        LwsSearchForm clazz = new LwsSearchForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_year();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_yearWithStringSrchYearShouldNotThrowException() {
        LwsSearchForm clazz = new LwsSearchForm();
        String srch_year = "srch_year";
        boolean isNotException = true;
        try {
            clazz.setSrch_year(srch_year);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_year_typeShouldNotThrowException() {
        LwsSearchForm clazz = new LwsSearchForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_year_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_year_typeWithStringSrchYearTypeShouldNotThrowException() {
        LwsSearchForm clazz = new LwsSearchForm();
        String srch_year_type = "srch_year_type";
        boolean isNotException = true;
        try {
            clazz.setSrch_year_type(srch_year_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_money_typeShouldNotThrowException() {
        LwsSearchForm clazz = new LwsSearchForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_money_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_money_typeWithStringSrchMoneyTypeShouldNotThrowException() {
        LwsSearchForm clazz = new LwsSearchForm();
        String srch_money_type = "srch_money_type";
        boolean isNotException = true;
        try {
            clazz.setSrch_money_type(srch_money_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
