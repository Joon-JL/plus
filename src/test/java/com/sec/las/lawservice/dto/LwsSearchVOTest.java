package com.sec.las.lawservice.dto;

import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class LwsSearchVOTest {

    @Test(timeout = 20000)
    public void testGetSrch_typeShouldNotThrowException() {
        LwsSearchVO clazz = new LwsSearchVO();
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
        LwsSearchVO clazz = new LwsSearchVO();
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
        LwsSearchVO clazz = new LwsSearchVO();
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
        LwsSearchVO clazz = new LwsSearchVO();
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
        LwsSearchVO clazz = new LwsSearchVO();
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
        LwsSearchVO clazz = new LwsSearchVO();
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
        LwsSearchVO clazz = new LwsSearchVO();
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
        LwsSearchVO clazz = new LwsSearchVO();
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
