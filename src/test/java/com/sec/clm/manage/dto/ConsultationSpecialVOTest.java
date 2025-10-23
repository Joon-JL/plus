package com.sec.clm.manage.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class ConsultationSpecialVOTest {

    @Test(timeout = 20000)
    public void testSetCntrt_idWithStringCntrtIdShouldNotThrowException() {
        ConsultationSpecialVO clazz = new ConsultationSpecialVO();
        String cntrt_id = "cntrt_id";
        boolean isNotException = true;
        try {
            clazz.setCntrt_id(cntrt_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAttr_seqnoWithIntAttrSeqnoShouldNotThrowException() {
        ConsultationSpecialVO clazz = new ConsultationSpecialVO();
        int attr_seqno = 0;
        boolean isNotException = true;
        try {
            clazz.setAttr_seqno(attr_seqno);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAttr_cdWithStringAttrCdShouldNotThrowException() {
        ConsultationSpecialVO clazz = new ConsultationSpecialVO();
        String attr_cd = "attr_cd";
        boolean isNotException = true;
        try {
            clazz.setAttr_cd(attr_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAttr_contWithStringAttrContShouldNotThrowException() {
        ConsultationSpecialVO clazz = new ConsultationSpecialVO();
        String attr_cont = "attr_cont";
        boolean isNotException = true;
        try {
            clazz.setAttr_cont(attr_cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCrtn_depthWithStringCrtnDepthShouldNotThrowException() {
        ConsultationSpecialVO clazz = new ConsultationSpecialVO();
        String crtn_depth = "crtn_depth";
        boolean isNotException = true;
        try {
            clazz.setCrtn_depth(crtn_depth);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAttr_worktypeWithStringAttrWorktypeShouldNotThrowException() {
        ConsultationSpecialVO clazz = new ConsultationSpecialVO();
        String attr_worktype = "attr_worktype";
        boolean isNotException = true;
        try {
            clazz.setAttr_worktype(attr_worktype);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_id_arrWithStringArrayCntrtIdArrShouldNotThrowException() {
        ConsultationSpecialVO clazz = new ConsultationSpecialVO();
        String[] cntrt_id_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setCntrt_id_arr(cntrt_id_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAttr_seqno_arrWithIntArrayAttrSeqnoArrShouldNotThrowException() {
        ConsultationSpecialVO clazz = new ConsultationSpecialVO();
        int[] attr_seqno_arr = mock(int[].class);
        boolean isNotException = true;
        try {
            clazz.setAttr_seqno_arr(attr_seqno_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAttr_cd_arrWithStringArrayAttrCdArrShouldNotThrowException() {
        ConsultationSpecialVO clazz = new ConsultationSpecialVO();
        String[] attr_cd_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setAttr_cd_arr(attr_cd_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAttr_cont_arrWithStringArrayAttrContArrShouldNotThrowException() {
        ConsultationSpecialVO clazz = new ConsultationSpecialVO();
        String[] attr_cont_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setAttr_cont_arr(attr_cont_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAttr_worktype_arrWithStringArrayAttrWorktypeArrShouldNotThrowException() {
        ConsultationSpecialVO clazz = new ConsultationSpecialVO();
        String[] attr_worktype_arr = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setAttr_worktype_arr(attr_worktype_arr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_idShouldNotThrowException() {
        ConsultationSpecialVO clazz = new ConsultationSpecialVO();
        boolean isNotException = true;
        try {
            clazz.getCntrt_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAttr_seqnoShouldNotThrowException() {
        ConsultationSpecialVO clazz = new ConsultationSpecialVO();
        boolean isNotException = true;
        try {
            clazz.getAttr_seqno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAttr_cdShouldNotThrowException() {
        ConsultationSpecialVO clazz = new ConsultationSpecialVO();
        boolean isNotException = true;
        try {
            clazz.getAttr_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAttr_contShouldNotThrowException() {
        ConsultationSpecialVO clazz = new ConsultationSpecialVO();
        boolean isNotException = true;
        try {
            clazz.getAttr_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCrtn_depthShouldNotThrowException() {
        ConsultationSpecialVO clazz = new ConsultationSpecialVO();
        boolean isNotException = true;
        try {
            clazz.getCrtn_depth();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_id_arrShouldNotThrowException() {
        ConsultationSpecialVO clazz = new ConsultationSpecialVO();
        boolean isNotException = true;
        try {
            clazz.getCntrt_id_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAttr_worktypeShouldNotThrowException() {
        ConsultationSpecialVO clazz = new ConsultationSpecialVO();
        boolean isNotException = true;
        try {
            clazz.getAttr_worktype();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAttr_seqno_arrShouldNotThrowException() {
        ConsultationSpecialVO clazz = new ConsultationSpecialVO();
        boolean isNotException = true;
        try {
            clazz.getAttr_seqno_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAttr_cd_arrShouldNotThrowException() {
        ConsultationSpecialVO clazz = new ConsultationSpecialVO();
        boolean isNotException = true;
        try {
            clazz.getAttr_cd_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAttr_cont_arrShouldNotThrowException() {
        ConsultationSpecialVO clazz = new ConsultationSpecialVO();
        boolean isNotException = true;
        try {
            clazz.getAttr_cont_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAttr_worktype_arrShouldNotThrowException() {
        ConsultationSpecialVO clazz = new ConsultationSpecialVO();
        boolean isNotException = true;
        try {
            clazz.getAttr_worktype_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
