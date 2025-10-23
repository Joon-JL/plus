package com.sec.clm.admin.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class CntrBasicAttrMngFormTest {

    @Test(timeout = 20000)
    public void testGetSrch_info_itmShouldNotThrowException() {
        CntrBasicAttrMngForm clazz = new CntrBasicAttrMngForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_info_itm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_info_itmWithStringSrchInfoItmShouldNotThrowException() {
        CntrBasicAttrMngForm clazz = new CntrBasicAttrMngForm();
        String srch_info_itm = "srch_info_itm";
        boolean isNotException = true;
        try {
            clazz.setSrch_info_itm(srch_info_itm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAttr_seqnoShouldNotThrowException() {
        CntrBasicAttrMngForm clazz = new CntrBasicAttrMngForm();
        boolean isNotException = true;
        try {
            clazz.getAttr_seqno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAttr_seqnoWithIntAttrSeqnoShouldNotThrowException() {
        CntrBasicAttrMngForm clazz = new CntrBasicAttrMngForm();
        int attr_seqno = 0;
        boolean isNotException = true;
        try {
            clazz.setAttr_seqno(attr_seqno);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
