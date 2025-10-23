package com.sec.las.lawconsulting.dto;

import java.lang.Exception;
import com.sds.secframework.common.util.DateUtil;
import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class SpeakConsultVOTest {

    @Test(timeout = 20000)
    public void testConstructorNotNull() throws Exception {
        SpeakConsultVO obj = new SpeakConsultVO();
        assert obj != null;
    }

    @Test(timeout = 20000)
    public void testSetSeqnoWithIntSeqnoShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
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
    public void testSetTitleWithStringTitleShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String title = "title";
        boolean isNotException = true;
        try {
            clazz.setTitle(title);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRe_contWithStringReContShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String re_cont = "re_cont";
        boolean isNotException = true;
        try {
            clazz.setRe_cont(re_cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDmstfrgn_gbnWithStringDmstfrgnGbnShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String dmstfrgn_gbn = "dmstfrgn_gbn";
        boolean isNotException = true;
        try {
            clazz.setDmstfrgn_gbn(dmstfrgn_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRe_cont_refWithStringReContRefShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String re_cont_ref = "re_cont_ref";
        boolean isNotException = true;
        try {
            clazz.setRe_cont_ref(re_cont_ref);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReqman_idWithStringReqmanIdShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String reqman_id = "reqman_id";
        boolean isNotException = true;
        try {
            clazz.setReqman_id(reqman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReqman_nmWithStringReqmanNmShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String reqman_nm = "reqman_nm";
        boolean isNotException = true;
        try {
            clazz.setReqman_nm(reqman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReqman_deptWithStringReqmanDeptShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String reqman_dept = "reqman_dept";
        boolean isNotException = true;
        try {
            clazz.setReqman_dept(reqman_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReqman_dept_nmWithStringReqmanDeptNmShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String reqman_dept_nm = "reqman_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setReqman_dept_nm(reqman_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_dtWithStringRegDtShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
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
    public void testSetRespman_idWithStringRespmanIdShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String respman_id = "respman_id";
        boolean isNotException = true;
        try {
            clazz.setRespman_id(respman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRespman_nmWithStringRespmanNmShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String respman_nm = "respman_nm";
        boolean isNotException = true;
        try {
            clazz.setRespman_nm(respman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRespman_deptWithStringRespmanDeptShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String respman_dept = "respman_dept";
        boolean isNotException = true;
        try {
            clazz.setRespman_dept(respman_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRespman_dept_nmWithStringRespmanDeptNmShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String respman_dept_nm = "respman_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setRespman_dept_nm(respman_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetWeekdutyynWithStringWeekdutyynShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String weekdutyyn = "weekdutyyn";
        boolean isNotException = true;
        try {
            clazz.setWeekdutyyn(weekdutyyn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMonthdutyynWithStringMonthdutyynShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String monthdutyyn = "monthdutyyn";
        boolean isNotException = true;
        try {
            clazz.setMonthdutyyn(monthdutyyn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetConf_weekdutyynWithStringConfWeekdutyynShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String conf_weekdutyyn = "conf_weekdutyyn";
        boolean isNotException = true;
        try {
            clazz.setConf_weekdutyyn(conf_weekdutyyn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetConf_monthdutyynWithStringConfMonthdutyynShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String conf_monthdutyyn = "conf_monthdutyyn";
        boolean isNotException = true;
        try {
            clazz.setConf_monthdutyyn(conf_monthdutyyn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGrpmgr_redeptWithStringGrpmgrRedeptShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String grpmgr_redept = "grpmgr_redept";
        boolean isNotException = true;
        try {
            clazz.setGrpmgr_redept(grpmgr_redept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_dtWithStringModDtShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String mod_dt = "mod_dt";
        boolean isNotException = true;
        try {
            clazz.setMod_dt(mod_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_idWithStringModIdShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String mod_id = "mod_id";
        boolean isNotException = true;
        try {
            clazz.setMod_id(mod_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_nmWithStringModNmShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String mod_nm = "mod_nm";
        boolean isNotException = true;
        try {
            clazz.setMod_nm(mod_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_ynWithStringDelYnShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
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
    public void testSetDel_dtWithStringDelDtShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
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
    public void testSetDel_idWithStringDelIdShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
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
    public void testSetDel_nmWithStringDelNmShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
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
    public void testGetSeqnoShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getSeqno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTitleShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getTitle();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRe_contShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getRe_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDmstfrgn_gbnShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getDmstfrgn_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRe_cont_refShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getRe_cont_ref();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReqman_idShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getReqman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReqman_nmShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getReqman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReqman_deptShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getReqman_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReqman_dept_nmShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getReqman_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dtShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getReg_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRespman_idShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getRespman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRespman_nmShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getRespman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRespman_deptShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getRespman_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRespman_dept_nmShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getRespman_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetWeekdutyynShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getWeekdutyyn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMonthdutyynShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getMonthdutyyn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetConf_weekdutyynShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getConf_weekdutyyn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetConf_monthdutyynShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getConf_monthdutyyn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGrpmgr_redeptShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getGrpmgr_redept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMod_dtShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getMod_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMod_idShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getMod_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMod_nmShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getMod_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_ynShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getDel_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_dtShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getDel_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_idShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getDel_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_nmShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getDel_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_respman_nmShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_respman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_respman_nmWithStringSrchRespmanNmShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String srch_respman_nm = "srch_respman_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_respman_nm(srch_respman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_titleShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_title();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_titleWithStringSrchTitleShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String srch_title = "srch_title";
        boolean isNotException = true;
        try {
            clazz.setSrch_title(srch_title);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_start_ymdShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_start_ymd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_start_ymdWithStringSrchStartYmdShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String srch_start_ymd = "srch_start_ymd";
        boolean isNotException = true;
        try {
            clazz.setSrch_start_ymd(srch_start_ymd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_end_ymdShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_end_ymd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_end_ymdWithStringSrchEndYmdShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String srch_end_ymd = "srch_end_ymd";
        boolean isNotException = true;
        try {
            clazz.setSrch_end_ymd(srch_end_ymd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSys_cdShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getSys_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSys_cdWithStringSysCdShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String sys_cd = "sys_cd";
        boolean isNotException = true;
        try {
            clazz.setSys_cd(sys_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetIsForeignShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getIsForeign();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetIsForeignWithStringIsForeignShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String isForeign = "isForeign";
        boolean isNotException = true;
        try {
            clazz.setIsForeign(isForeign);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_respman_idShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_respman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_respman_idWithStringSrchRespmanIdShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String srch_respman_id = "srch_respman_id";
        boolean isNotException = true;
        try {
            clazz.setSrch_respman_id(srch_respman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBody_mimeShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getBody_mime();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBody_mimeWithStringBodyMimeShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String body_mime = "body_mime";
        boolean isNotException = true;
        try {
            clazz.setBody_mime(body_mime);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReqman_mailShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getReqman_mail();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReqman_mailWithStringReqmanMailShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String reqman_mail = "reqman_mail";
        boolean isNotException = true;
        try {
            clazz.setReqman_mail(reqman_mail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSend_mailynShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        boolean isNotException = true;
        try {
            clazz.getSend_mailyn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSend_mailynWithStringSendMailynShouldNotThrowException() throws Exception {
        SpeakConsultVO clazz = new SpeakConsultVO();
        String send_mailyn = "send_mailyn";
        boolean isNotException = true;
        try {
            clazz.setSend_mailyn(send_mailyn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
