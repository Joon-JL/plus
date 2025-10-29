package com.sec.las.lawservice.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class LawyerEstimateVOTest {

    @Test(timeout = 20000)
    public void testGetLwr_noShouldNotThrowException() {
        LawyerEstimateVO clazz = new LawyerEstimateVO();
        boolean isNotException = true;
        try {
            clazz.getLwr_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLwr_noWithStringLwrNoShouldNotThrowException() {
        LawyerEstimateVO clazz = new LawyerEstimateVO();
        String lwr_no = "lwr_no";
        boolean isNotException = true;
        try {
            clazz.setLwr_no(lwr_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEstmt_titleShouldNotThrowException() {
        LawyerEstimateVO clazz = new LawyerEstimateVO();
        boolean isNotException = true;
        try {
            clazz.getEstmt_title();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEstmt_titleWithStringEstmtTitleShouldNotThrowException() {
        LawyerEstimateVO clazz = new LawyerEstimateVO();
        String estmt_title = "estmt_title";
        boolean isNotException = true;
        try {
            clazz.setEstmt_title(estmt_title);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEstmt_contShouldNotThrowException() {
        LawyerEstimateVO clazz = new LawyerEstimateVO();
        boolean isNotException = true;
        try {
            clazz.getEstmt_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEstmt_contWithStringEstmtContShouldNotThrowException() {
        LawyerEstimateVO clazz = new LawyerEstimateVO();
        String estmt_cont = "estmt_cont";
        boolean isNotException = true;
        try {
            clazz.setEstmt_cont(estmt_cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
