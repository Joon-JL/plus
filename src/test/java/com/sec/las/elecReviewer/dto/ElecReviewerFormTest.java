package com.sec.las.elecReviewer.dto;

import com.sds.secframework.common.dto.CommonForm;
import com.sds.secframework.common.util.DateUtil;
import java.lang.String;
import org.junit.Test;
import java.util.List;
import static org.mockito.Mockito.mock;

public class ElecReviewerFormTest {

    @Test(timeout = 20000)
    public void testGetTmp_idShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getTmp_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTmp_idWithStringTmpIdShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        String tmp_id = "tmp_id";
        boolean isNotException = true;
        try {
            clazz.setTmp_id(tmp_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetComp_cdShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getComp_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetComp_cdWithStringCompCdShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        String comp_cd = "comp_cd";
        boolean isNotException = true;
        try {
            clazz.setComp_cd(comp_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetComp_nmShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getComp_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetComp_nmWithStringCompNmShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        String comp_nm = "comp_nm";
        boolean isNotException = true;
        try {
            clazz.setComp_nm(comp_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOrg_resp_idShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getOrg_resp_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOrg_resp_idWithStringOrgRespIdShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        String org_resp_id = "org_resp_id";
        boolean isNotException = true;
        try {
            clazz.setOrg_resp_id(org_resp_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOrg_resp_nmShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getOrg_resp_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOrg_resp_nmWithStringOrgRespNmShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        String org_resp_nm = "org_resp_nm";
        boolean isNotException = true;
        try {
            clazz.setOrg_resp_nm(org_resp_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOrg_resp_deptShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getOrg_resp_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOrg_resp_deptWithStringOrgRespDeptShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        String org_resp_dept = "org_resp_dept";
        boolean isNotException = true;
        try {
            clazz.setOrg_resp_dept(org_resp_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOrg_resp_dept_nmShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getOrg_resp_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOrg_resp_dept_nmWithStringOrgRespDeptNmShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        String org_resp_dept_nm = "org_resp_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setOrg_resp_dept_nm(org_resp_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOrg_resp_jikgup_nmShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getOrg_resp_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOrg_resp_jikgup_nmWithStringOrgRespJikgupNmShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        String org_resp_jikgup_nm = "org_resp_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setOrg_resp_jikgup_nm(org_resp_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTmp_resp_idShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getTmp_resp_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTmp_resp_idWithStringTmpRespIdShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        String tmp_resp_id = "tmp_resp_id";
        boolean isNotException = true;
        try {
            clazz.setTmp_resp_id(tmp_resp_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTmp_resp_nmShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getTmp_resp_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTmp_resp_nmWithStringTmpRespNmShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        String tmp_resp_nm = "tmp_resp_nm";
        boolean isNotException = true;
        try {
            clazz.setTmp_resp_nm(tmp_resp_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTmp_resp_deptShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getTmp_resp_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTmp_resp_deptWithStringTmpRespDeptShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        String tmp_resp_dept = "tmp_resp_dept";
        boolean isNotException = true;
        try {
            clazz.setTmp_resp_dept(tmp_resp_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTmp_resp_dept_nmShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getTmp_resp_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTmp_resp_dept_nmWithStringTmpRespDeptNmShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        String tmp_resp_dept_nm = "tmp_resp_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setTmp_resp_dept_nm(tmp_resp_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTmp_resp_jikgup_nmShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getTmp_resp_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTmp_resp_jikgup_nmWithStringTmpRespJikgupNmShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        String tmp_resp_jikgup_nm = "tmp_resp_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setTmp_resp_jikgup_nm(tmp_resp_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTmp_causeShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getTmp_cause();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTmp_causeWithStringTmpCauseShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        String tmp_cause = "tmp_cause";
        boolean isNotException = true;
        try {
            clazz.setTmp_cause(tmp_cause);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dtShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getReg_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_dtWithStringRegDtShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        String reg_dt = "reg_dt";
        boolean isNotException = true;
        try {
            clazz.setReg_dt(reg_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_idShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getReg_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_idWithStringRegIdShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        String reg_id = "reg_id";
        boolean isNotException = true;
        try {
            clazz.setReg_id(reg_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_nmShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getReg_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_nmWithStringRegNmShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        String reg_nm = "reg_nm";
        boolean isNotException = true;
        try {
            clazz.setReg_nm(reg_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_ynShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getDel_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_ynWithStringDelYnShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        String del_yn = "del_yn";
        boolean isNotException = true;
        try {
            clazz.setDel_yn(del_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_dtShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getDel_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_dtWithStringDelDtShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        String del_dt = "del_dt";
        boolean isNotException = true;
        try {
            clazz.setDel_dt(del_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_idShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getDel_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_idWithStringDelIdShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        String del_id = "del_id";
        boolean isNotException = true;
        try {
            clazz.setDel_id(del_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_nmShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getDel_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_nmWithStringDelNmShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        String del_nm = "del_nm";
        boolean isNotException = true;
        try {
            clazz.setDel_nm(del_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeqnoShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getSeqno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeqnoWithIntSeqnoShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        int seqno = 0;
        boolean isNotException = true;
        try {
            clazz.setSeqno(seqno);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCont_typeShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getCont_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCont_typeWithStringContTypeShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        String cont_type = "cont_type";
        boolean isNotException = true;
        try {
            clazz.setCont_type(cont_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCont_idShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getCont_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCont_idWithStringContIdShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        String cont_id = "cont_id";
        boolean isNotException = true;
        try {
            clazz.setCont_id(cont_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMng_comp_cdShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getMng_comp_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMng_comp_cdWithStringMngCompCdShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        String mng_comp_cd = "mng_comp_cd";
        boolean isNotException = true;
        try {
            clazz.setMng_comp_cd(mng_comp_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_user_nmShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_user_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_user_nmWithStringSrchUserNmShouldNotThrowException() {
        ElecReviewerForm clazz = new ElecReviewerForm();
        String srch_user_nm = "srch_user_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_user_nm(srch_user_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
