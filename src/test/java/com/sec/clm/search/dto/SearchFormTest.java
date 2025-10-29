package com.sec.clm.search.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class SearchFormTest {

    @Test(timeout = 20000)
    public void testGetCntrt_statusShouldNotThrowException() {
        SearchForm clazz = new SearchForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_statusWithStringCntrtStatusShouldNotThrowException() {
        SearchForm clazz = new SearchForm();
        String cntrt_status = "cntrt_status";
        boolean isNotException = true;
        try {
            clazz.setCntrt_status(cntrt_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetQueryTextShouldNotThrowException() {
        SearchForm clazz = new SearchForm();
        boolean isNotException = true;
        try {
            clazz.getQueryText();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetQueryTextWithStringQueryTextShouldNotThrowException() {
        SearchForm clazz = new SearchForm();
        String queryText = "queryText";
        boolean isNotException = true;
        try {
            clazz.setQueryText(queryText);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_start_dtShouldNotThrowException() {
        SearchForm clazz = new SearchForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_start_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_start_dtWithStringSrchStartDtShouldNotThrowException() {
        SearchForm clazz = new SearchForm();
        String srch_start_dt = "srch_start_dt";
        boolean isNotException = true;
        try {
            clazz.setSrch_start_dt(srch_start_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_end_dtShouldNotThrowException() {
        SearchForm clazz = new SearchForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_end_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_end_dtWithStringSrchEndDtShouldNotThrowException() {
        SearchForm clazz = new SearchForm();
        String srch_end_dt = "srch_end_dt";
        boolean isNotException = true;
        try {
            clazz.setSrch_end_dt(srch_end_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_index_dbWithStringSrchIndexDbShouldNotThrowException() {
        SearchForm clazz = new SearchForm();
        String srch_index_db = "srch_index_db";
        boolean isNotException = true;
        try {
            clazz.setSrch_index_db(srch_index_db);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_index_dbShouldNotThrowException() {
        SearchForm clazz = new SearchForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_index_db();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetQueryFieldWithStringQueryFieldShouldNotThrowException() {
        SearchForm clazz = new SearchForm();
        String queryField = "queryField";
        boolean isNotException = true;
        try {
            clazz.setQueryField(queryField);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetQueryFieldShouldNotThrowException() {
        SearchForm clazz = new SearchForm();
        boolean isNotException = true;
        try {
            clazz.getQueryField();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFieldTextWithStringFieldTextShouldNotThrowException() {
        SearchForm clazz = new SearchForm();
        String fieldText = "fieldText";
        boolean isNotException = true;
        try {
            clazz.setFieldText(fieldText);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFieldTextShouldNotThrowException() {
        SearchForm clazz = new SearchForm();
        boolean isNotException = true;
        try {
            clazz.getFieldText();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrchMinDateWithStringSrchMinDateShouldNotThrowException() {
        SearchForm clazz = new SearchForm();
        String srchMinDate = "srchMinDate";
        boolean isNotException = true;
        try {
            clazz.setSrchMinDate(srchMinDate);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrchMinDateShouldNotThrowException() {
        SearchForm clazz = new SearchForm();
        boolean isNotException = true;
        try {
            clazz.getSrchMinDate();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrchMaxDateWithStringSrchMaxDateShouldNotThrowException() {
        SearchForm clazz = new SearchForm();
        String srchMaxDate = "srchMaxDate";
        boolean isNotException = true;
        try {
            clazz.setSrchMaxDate(srchMaxDate);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrchMaxDateShouldNotThrowException() {
        SearchForm clazz = new SearchForm();
        boolean isNotException = true;
        try {
            clazz.getSrchMaxDate();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
