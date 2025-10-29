package com.sec.clm.admin.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class CntrBasicAttrMngVOTest {

    @Test(timeout = 20000)
    public void testGetSrch_info_itmShouldNotThrowException() {
        CntrBasicAttrMngVO clazz = new CntrBasicAttrMngVO();
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
        CntrBasicAttrMngVO clazz = new CntrBasicAttrMngVO();
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
        CntrBasicAttrMngVO clazz = new CntrBasicAttrMngVO();
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
        CntrBasicAttrMngVO clazz = new CntrBasicAttrMngVO();
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
