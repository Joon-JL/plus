package com.sec.clm.manage.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class ConsultationSpecialFormTest {

    @Test(timeout = 20000)
    public void testSetCntrt_idWithStringCntrtIdShouldNotThrowException() {
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
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
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
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
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
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
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
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
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
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
    public void testSetTd_cntWithIntTdCntShouldNotThrowException() {
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
        int td_cnt = 0;
        boolean isNotException = true;
        try {
            clazz.setTd_cnt(td_cnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetColspan_stringWithStringColspanStringShouldNotThrowException() {
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
        String colspan_string = "colspan_string";
        boolean isNotException = true;
        try {
            clazz.setColspan_string(colspan_string);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAttr_worktypeWithStringAttrWorktypeShouldNotThrowException() {
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
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
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
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
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
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
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
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
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
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
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
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
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
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
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
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
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
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
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
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
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
        boolean isNotException = true;
        try {
            clazz.getCrtn_depth();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTd_cntShouldNotThrowException() {
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
        boolean isNotException = true;
        try {
            clazz.getTd_cnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetColspan_stringShouldNotThrowException() {
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
        boolean isNotException = true;
        try {
            clazz.getColspan_string();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAttr_worktypeShouldNotThrowException() {
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
        boolean isNotException = true;
        try {
            clazz.getAttr_worktype();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_id_arrShouldNotThrowException() {
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_id_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetAttr_seqno_arrShouldNotThrowException() {
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
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
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
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
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
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
        ConsultationSpecialForm clazz = new ConsultationSpecialForm();
        boolean isNotException = true;
        try {
            clazz.getAttr_worktype_arr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
