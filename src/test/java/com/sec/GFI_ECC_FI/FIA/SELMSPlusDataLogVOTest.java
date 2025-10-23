package com.sec.GFI_ECC_FI.FIA;

import java.lang.String;
import org.joda.time.DateTime;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class SELMSPlusDataLogVOTest {

    @Test(timeout = 20000)
    public void testGetExecutionDateShouldNotThrowException() {
        SELMSPlusDataLogVO clazz = new SELMSPlusDataLogVO();
        boolean isNotException = true;
        try {
            clazz.getExecutionDate();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSELMSPlusDataCntShouldNotThrowException() {
        SELMSPlusDataLogVO clazz = new SELMSPlusDataLogVO();
        boolean isNotException = true;
        try {
            clazz.getSELMSPlusDataCnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTransferredDataCntShouldNotThrowException() {
        SELMSPlusDataLogVO clazz = new SELMSPlusDataLogVO();
        boolean isNotException = true;
        try {
            clazz.getTransferredDataCnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFaildDataShouldNotThrowException() {
        SELMSPlusDataLogVO clazz = new SELMSPlusDataLogVO();
        boolean isNotException = true;
        try {
            clazz.getFaildData();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExecutionDateWithDateTimeExecutionDateShouldNotThrowException() {
        SELMSPlusDataLogVO clazz = new SELMSPlusDataLogVO();
        DateTime executionDate = mock(DateTime.class);
        boolean isNotException = true;
        try {
            clazz.setExecutionDate(executionDate);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSELMSPlusDataCntWithIntSelmsPlusDataCntShouldNotThrowException() {
        SELMSPlusDataLogVO clazz = new SELMSPlusDataLogVO();
        int selmsPlusDataCnt = 0;
        boolean isNotException = true;
        try {
            clazz.setSELMSPlusDataCnt(selmsPlusDataCnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTransferredDataCntWithIntTransferredDataCntShouldNotThrowException() {
        SELMSPlusDataLogVO clazz = new SELMSPlusDataLogVO();
        int transferredDataCnt = 0;
        boolean isNotException = true;
        try {
            clazz.setTransferredDataCnt(transferredDataCnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFaildDataWithStringFaildDataShouldNotThrowException() {
        SELMSPlusDataLogVO clazz = new SELMSPlusDataLogVO();
        String faildData = "faildData";
        boolean isNotException = true;
        try {
            clazz.setFaildData(faildData);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
