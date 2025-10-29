package com.sec.las.lawconsulting.dto;

import java.lang.Exception;
import java.sql.Timestamp;
import java.lang.String;
import org.junit.Test;
import java.util.List;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class LawConsultFormTest {

    @Test(timeout = 20000)
    public void testConstructorNotNull() throws Exception {
        LawConsultForm obj = new LawConsultForm();
        assert obj != null;
    }

    @Test(timeout = 20000)
    public void testGetsSel_grdShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getsSel_grd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetsSel_grdWithStringSSelGrdShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String sSel_grd = "sSel_grd";
        boolean isNotException = true;
        try {
            clazz.setsSel_grd(sSel_grd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnslt_noWithStringCnsltNoShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String cnslt_no = "cnslt_no";
        boolean isNotException = true;
        try {
            clazz.setCnslt_no(cnslt_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHstry_noWithIntHstryNoShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        int hstry_no = 0;
        boolean isNotException = true;
        try {
            clazz.setHstry_no(hstry_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnslt_posWithIntCnsltPosShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        int cnslt_pos = 0;
        boolean isNotException = true;
        try {
            clazz.setCnslt_pos(cnslt_pos);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnslt_srtWithIntCnsltSrtShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        int cnslt_srt = 0;
        boolean isNotException = true;
        try {
            clazz.setCnslt_srt(cnslt_srt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTitleWithStringTitleShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
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
    public void testSetContWithStringContShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String cont = "cont";
        boolean isNotException = true;
        try {
            clazz.setCont(cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPrgrs_statusWithStringPrgrsStatusShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String prgrs_status = "prgrs_status";
        boolean isNotException = true;
        try {
            clazz.setPrgrs_status(prgrs_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMain_matr_contWithStringMainMatrContShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String main_matr_cont = "main_matr_cont";
        boolean isNotException = true;
        try {
            clazz.setMain_matr_cont(main_matr_cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRejct_causeWithStringRejctCauseShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String rejct_cause = "rejct_cause";
        boolean isNotException = true;
        try {
            clazz.setRejct_cause(rejct_cause);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHold_causeWithStringHoldCauseShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String hold_cause = "hold_cause";
        boolean isNotException = true;
        try {
            clazz.setHold_cause(hold_cause);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExtnl_cnsltynWithStringExtnlCnsltynShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String extnl_cnsltyn = "extnl_cnsltyn";
        boolean isNotException = true;
        try {
            clazz.setExtnl_cnsltyn(extnl_cnsltyn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnslt_amtWithStringCnsltAmtShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String cnslt_amt = "cnslt_amt";
        boolean isNotException = true;
        try {
            clazz.setCnslt_amt(cnslt_amt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetWeekdutyynWithStringWeekdutyynShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
    public void testSetReg_idWithStringRegIdShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
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
    public void testSetReg_nmWithStringRegNmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
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
    public void testSetReg_deptWithStringRegDeptShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String reg_dept = "reg_dept";
        boolean isNotException = true;
        try {
            clazz.setReg_dept(reg_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_telnoWithStringRegTelnoShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String reg_telno = "reg_telno";
        boolean isNotException = true;
        try {
            clazz.setReg_telno(reg_telno);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_dept_nmWithStringRegDeptNmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String reg_dept_nm = "reg_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setReg_dept_nm(reg_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_dtWithStringModDtShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
    public void testSetDmstfrgn_gbnWithStringDmstfrgnGbnShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
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
    public void testSetApprvldayWithStringApprvldayShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String apprvlday = "apprvlday";
        boolean isNotException = true;
        try {
            clazz.setApprvlday(apprvlday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetStart_indexWithStringStartIndexShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String start_index = "start_index";
        boolean isNotException = true;
        try {
            clazz.setStart_index(start_index);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEnd_indexWithStringEndIndexShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String end_index = "end_index";
        boolean isNotException = true;
        try {
            clazz.setEnd_index(end_index);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCurPageWithStringCurPageShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String curPage = "curPage";
        boolean isNotException = true;
        try {
            clazz.setCurPage(curPage);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnslt_noShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getCnslt_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHstry_noShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getHstry_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnslt_posShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getCnslt_pos();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnslt_srtShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getCnslt_srt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTitleShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getTitle();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetContShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getCont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPrgrs_statusShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getPrgrs_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMain_matr_contShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getMain_matr_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRejct_causeShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getRejct_cause();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHold_causeShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getHold_cause();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExtnl_cnsltynShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getExtnl_cnsltyn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnslt_amtShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getCnslt_amt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetWeekdutyynShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getGrpmgr_redept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_idShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getReg_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_nmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getReg_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_deptShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getReg_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_telnoShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getReg_telno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dept_nmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getReg_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMod_dtShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getDel_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDmstfrgn_gbnShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getDmstfrgn_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetApprvldayShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getApprvlday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStart_indexShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getStart_index();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEnd_indexShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getEnd_index();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCurPageShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getCurPage();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_respman_nmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
    public void testGetSrch_contShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_contWithStringSrchContShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String srch_cont = "srch_cont";
        boolean isNotException = true;
        try {
            clazz.setSrch_cont(srch_cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_reg_nmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_reg_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_reg_nmWithStringSrchRegNmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String srch_reg_nm = "srch_reg_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_reg_nm(srch_reg_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_prgrs_statusShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_prgrs_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_prgrs_statusWithStringSrchPrgrsStatusShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String srch_prgrs_status = "srch_prgrs_status";
        boolean isNotException = true;
        try {
            clazz.setSrch_prgrs_status(srch_prgrs_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_reg_deptShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_reg_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_reg_deptWithStringSrchRegDeptShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String srch_reg_dept = "srch_reg_dept";
        boolean isNotException = true;
        try {
            clazz.setSrch_reg_dept(srch_reg_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_start_ymdShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
    public void testGetIsForeignShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
    public void testGetLawconsult_listShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getLawconsult_list();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLawconsult_listWithListLawconsultListShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        List lawconsult_list = mock(List.class);
        boolean isNotException = true;
        try {
            clazz.setLawconsult_list(lawconsult_list);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReturn_titleShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getReturn_title();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReturn_titleWithStringReturnTitleShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String return_title = "return_title";
        boolean isNotException = true;
        try {
            clazz.setReturn_title(return_title);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReturn_messageShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getReturn_message();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReturn_messageWithStringReturnMessageShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String return_message = "return_message";
        boolean isNotException = true;
        try {
            clazz.setReturn_message(return_message);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRow_cntShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getRow_cnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRow_cntWithIntRowCntShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        int row_cnt = 0;
        boolean isNotException = true;
        try {
            clazz.setRow_cnt(row_cnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetConsult_typeShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getConsult_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetConsult_typeWithStringConsultTypeShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String consult_type = "consult_type";
        boolean isNotException = true;
        try {
            clazz.setConsult_type(consult_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetConsult_type_nameShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getConsult_type_name();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetConsult_type_nameWithStringConsultTypeNameShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String consult_type_name = "consult_type_name";
        boolean isNotException = true;
        try {
            clazz.setConsult_type_name(consult_type_name);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBody_mimeShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
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
        LawConsultForm clazz = new LawConsultForm();
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
    public void testGetLawconsult_type_listShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getLawconsult_type_list();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLawconsult_type_listWithListLawconsultTypeListShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        List lawconsult_type_list = mock(List.class);
        boolean isNotException = true;
        try {
            clazz.setLawconsult_type_list(lawconsult_type_list);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPrgrs_status_nameShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getPrgrs_status_name();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPrgrs_status_nameWithStringPrgrsStatusNameShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String prgrs_status_name = "prgrs_status_name";
        boolean isNotException = true;
        try {
            clazz.setPrgrs_status_name(prgrs_status_name);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCheck_prgrs_statusShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getCheck_prgrs_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCheck_prgrs_statusWithStringCheckPrgrsStatusShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String check_prgrs_status = "check_prgrs_status";
        boolean isNotException = true;
        try {
            clazz.setCheck_prgrs_status(check_prgrs_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_consult_typeShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_consult_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_consult_typeWithStringSrchConsultTypeShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String srch_consult_type = "srch_consult_type";
        boolean isNotException = true;
        try {
            clazz.setSrch_consult_type(srch_consult_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_consult_type_nameShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_consult_type_name();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_consult_type_nameWithStringSrchConsultTypeNameShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String srch_consult_type_name = "srch_consult_type_name";
        boolean isNotException = true;
        try {
            clazz.setSrch_consult_type_name(srch_consult_type_name);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPub_ynShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getPub_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPub_ynWithStringPubYnShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String pub_yn = "pub_yn";
        boolean isNotException = true;
        try {
            clazz.setPub_yn(pub_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGrpmgr_re_ynShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getGrpmgr_re_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGrpmgr_re_ynWithStringGrpmgrReYnShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String grpmgr_re_yn = "grpmgr_re_yn";
        boolean isNotException = true;
        try {
            clazz.setGrpmgr_re_yn(grpmgr_re_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGrpmgr_idShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getGrpmgr_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGrpmgr_idWithStringGrpmgrIdShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String grpmgr_id = "grpmgr_id";
        boolean isNotException = true;
        try {
            clazz.setGrpmgr_id(grpmgr_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetGrpmgr_nmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getGrpmgr_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetGrpmgr_nmWithStringGrpmgrNmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String grpmgr_nm = "grpmgr_nm";
        boolean isNotException = true;
        try {
            clazz.setGrpmgr_nm(grpmgr_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOrdr_contShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getOrdr_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOrdr_contWithStringOrdrContShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String ordr_cont = "ordr_cont";
        boolean isNotException = true;
        try {
            clazz.setOrdr_cont(ordr_cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnsdman_idShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getCnsdman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnsdman_idWithStringCnsdmanIdShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String cnsdman_id = "cnsdman_id";
        boolean isNotException = true;
        try {
            clazz.setCnsdman_id(cnsdman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnsdman_nmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getCnsdman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnsdman_nmWithStringCnsdmanNmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String cnsdman_nm = "cnsdman_nm";
        boolean isNotException = true;
        try {
            clazz.setCnsdman_nm(cnsdman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnsd_dtShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getCnsd_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnsd_dtWithStringCnsdDtShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String cnsd_dt = "cnsd_dt";
        boolean isNotException = true;
        try {
            clazz.setCnsd_dt(cnsd_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnsd_opnnShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getCnsd_opnn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnsd_opnnWithStringCnsdOpnnShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String cnsd_opnn = "cnsd_opnn";
        boolean isNotException = true;
        try {
            clazz.setCnsd_opnn(cnsd_opnn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetApbtman_idShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getApbtman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetApbtman_idWithStringApbtmanIdShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String apbtman_id = "apbtman_id";
        boolean isNotException = true;
        try {
            clazz.setApbtman_id(apbtman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetApbtman_nmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getApbtman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetApbtman_nmWithStringApbtmanNmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String apbtman_nm = "apbtman_nm";
        boolean isNotException = true;
        try {
            clazz.setApbtman_nm(apbtman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetApbt_opnnShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getApbt_opnn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetApbt_opnnWithStringApbtOpnnShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String apbt_opnn = "apbt_opnn";
        boolean isNotException = true;
        try {
            clazz.setApbt_opnn(apbt_opnn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRe_dtShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getRe_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRe_dtWithStringReDtShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String re_dt = "re_dt";
        boolean isNotException = true;
        try {
            clazz.setRe_dt(re_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRespman_idShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getRespman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRespman_idWithStringRespmanIdShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
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
    public void testGetExtnl_prgrs_statusShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getExtnl_prgrs_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExtnl_prgrs_statusWithStringExtnlPrgrsStatusShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String extnl_prgrs_status = "extnl_prgrs_status";
        boolean isNotException = true;
        try {
            clazz.setExtnl_prgrs_status(extnl_prgrs_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetIntnl_prgrs_statusShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getIntnl_prgrs_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetIntnl_prgrs_statusWithStringIntnlPrgrsStatusShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String intnl_prgrs_status = "intnl_prgrs_status";
        boolean isNotException = true;
        try {
            clazz.setIntnl_prgrs_status(intnl_prgrs_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFileInfos2ShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getFileInfos2();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFileInfos2WithStringFileInfos2ShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String fileInfos2 = "fileInfos2";
        boolean isNotException = true;
        try {
            clazz.setFileInfos2(fileInfos2);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFileInfos3ShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getFileInfos3();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFileInfos3WithStringFileInfos3ShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String fileInfos3 = "fileInfos3";
        boolean isNotException = true;
        try {
            clazz.setFileInfos3(fileInfos3);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExtnl_listShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getExtnl_list();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExtnl_listWithStringArrayExtnlListShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String[] extnl_list = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setExtnl_list(extnl_list);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExtnlListShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getExtnlList();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExtnlListWithListExtnlListShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        List extnlList = mock(List.class);
        boolean isNotException = true;
        try {
            clazz.setExtnlList(extnlList);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExtnl_comp_nmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getExtnl_comp_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExtnl_comp_nmWithStringExtnlCompNmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String extnl_comp_nm = "extnl_comp_nm";
        boolean isNotException = true;
        try {
            clazz.setExtnl_comp_nm(extnl_comp_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExtnl_cnslt_amtShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getExtnl_cnslt_amt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExtnl_cnslt_amtWithLongExtnlCnsltAmtShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        long extnl_cnslt_amt = 0L;
        boolean isNotException = true;
        try {
            clazz.setExtnl_cnslt_amt(extnl_cnslt_amt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExtnl_crrncy_unitShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getExtnl_crrncy_unit();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExtnl_crrncy_unitWithStringExtnlCrrncyUnitShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String extnl_crrncy_unit = "extnl_crrncy_unit";
        boolean isNotException = true;
        try {
            clazz.setExtnl_crrncy_unit(extnl_crrncy_unit);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExtnl_krw_amtShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getExtnl_krw_amt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExtnl_krw_amtWithLongExtnlKrwAmtShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        long extnl_krw_amt = 0L;
        boolean isNotException = true;
        try {
            clazz.setExtnl_krw_amt(extnl_krw_amt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExtnl_seqShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getExtnl_seq();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExtnl_seqWithIntExtnlSeqShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        int extnl_seq = 0;
        boolean isNotException = true;
        try {
            clazz.setExtnl_seq(extnl_seq);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_midclsfcn2ShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getFile_midclsfcn2();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFile_midclsfcn2WithStringFileMidclsfcn2ShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String file_midclsfcn2 = "file_midclsfcn2";
        boolean isNotException = true;
        try {
            clazz.setFile_midclsfcn2(file_midclsfcn2);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetIsGrpmgrShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getIsGrpmgr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetIsGrpmgrWithStringIsGrpmgrShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String isGrpmgr = "isGrpmgr";
        boolean isNotException = true;
        try {
            clazz.setIsGrpmgr(isGrpmgr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUser_listShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getUser_list();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUser_listWithStringArrayUserListShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String[] user_list = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setUser_list(user_list);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRespman_listShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getRespman_list();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRespman_listWithStringArrayRespmanListShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String[] respman_list = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setRespman_list(respman_list);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRespman_nmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getRespman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRespman_nmWithStringRespmanNmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
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
    public void testGetRejct_opnnShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getRejct_opnn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRejct_opnnWithStringRejctOpnnShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String rejct_opnn = "rejct_opnn";
        boolean isNotException = true;
        try {
            clazz.setRejct_opnn(rejct_opnn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_reg_idShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_reg_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_reg_idWithStringSrchRegIdShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String srch_reg_id = "srch_reg_id";
        boolean isNotException = true;
        try {
            clazz.setSrch_reg_id(srch_reg_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_receptionShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_reception();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_receptionWithStringSrchReceptionShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String srch_reception = "srch_reception";
        boolean isNotException = true;
        try {
            clazz.setSrch_reception(srch_reception);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRegman_jikgup_nmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getRegman_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRegman_jikgup_nmWithStringRegmanJikgupNmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String regman_jikgup_nm = "regman_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setRegman_jikgup_nm(regman_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetIsReviewShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getIsReview();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetIsReviewWithStringIsReviewShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String isReview = "isReview";
        boolean isNotException = true;
        try {
            clazz.setIsReview(isReview);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dt_tsShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getReg_dt_ts();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_dt_tsWithTimestampRegDtTsShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        Timestamp reg_dt_ts = mock(Timestamp.class);
        boolean isNotException = true;
        try {
            clazz.setReg_dt_ts(reg_dt_ts);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dtShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getReg_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_dtWithStringRegDtShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
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
    public void testGetReg_intnl_deptShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getReg_intnl_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_intnl_deptWithStringRegIntnlDeptShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String reg_intnl_dept = "reg_intnl_dept";
        boolean isNotException = true;
        try {
            clazz.setReg_intnl_dept(reg_intnl_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_operdivShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getReg_operdiv();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_operdivWithStringRegOperdivShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String reg_operdiv = "reg_operdiv";
        boolean isNotException = true;
        try {
            clazz.setReg_operdiv(reg_operdiv);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTo_transferShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getTo_transfer();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTo_transferWithStringToTransferShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String to_transfer = "to_transfer";
        boolean isNotException = true;
        try {
            clazz.setTo_transfer(to_transfer);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnslt_typeShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getCnslt_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnslt_typeWithStringCnsltTypeShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String cnslt_type = "cnslt_type";
        boolean isNotException = true;
        try {
            clazz.setCnslt_type(cnslt_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetComp_cdShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getComp_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetComp_cdWithStringCompCdShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
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
    public void testGetSrch_elcompShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_elcomp();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_elcompWithStringSrchElcompShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String srch_elcomp = "srch_elcomp";
        boolean isNotException = true;
        try {
            clazz.setSrch_elcomp(srch_elcomp);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSolo_ynShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getSolo_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSolo_ynWithStringSoloYnShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String solo_yn = "solo_yn";
        boolean isNotException = true;
        try {
            clazz.setSolo_yn(solo_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_solo_ynShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_solo_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_solo_ynWithStringSrchSoloYnShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String srch_solo_yn = "srch_solo_yn";
        boolean isNotException = true;
        try {
            clazz.setSrch_solo_yn(srch_solo_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetContentsShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getContents();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetContentsWithStringContentsShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String contents = "contents";
        boolean isNotException = true;
        try {
            clazz.setContents(contents);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetIsStdContShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getIsStdCont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetIsStdContWithStringIsStdContShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String isStdCont = "isStdCont";
        boolean isNotException = true;
        try {
            clazz.setIsStdCont(isStdCont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFwd_gbnShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getFwd_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFwd_gbnWithStringFwdGbnShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String fwd_gbn = "fwd_gbn";
        boolean isNotException = true;
        try {
            clazz.setFwd_gbn(fwd_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_complete_ynShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_complete_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_complete_ynWithStringSrchCompleteYnShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String srch_complete_yn = "srch_complete_yn";
        boolean isNotException = true;
        try {
            clazz.setSrch_complete_yn(srch_complete_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetComplete_ynShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getComplete_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetComplete_ynWithStringCompleteYnShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String complete_yn = "complete_yn";
        boolean isNotException = true;
        try {
            clazz.setComplete_yn(complete_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReq_reply_dtShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getReq_reply_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReq_reply_dtWithStringReqReplyDtShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String req_reply_dt = "req_reply_dt";
        boolean isNotException = true;
        try {
            clazz.setReq_reply_dt(req_reply_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetArr_trgtman_idShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getArr_trgtman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetArr_trgtman_idWithStringArrayArrTrgtmanIdShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String[] arr_trgtman_id = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setArr_trgtman_id(arr_trgtman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTrgtman_idShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getTrgtman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTrgtman_idWithStringTrgtmanIdShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String trgtman_id = "trgtman_id";
        boolean isNotException = true;
        try {
            clazz.setTrgtman_id(trgtman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetArr_trgtman_nmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getArr_trgtman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetArr_trgtman_nmWithStringArrayArrTrgtmanNmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String[] arr_trgtman_nm = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setArr_trgtman_nm(arr_trgtman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTrgtman_nmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getTrgtman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTrgtman_nmWithStringTrgtmanNmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String trgtman_nm = "trgtman_nm";
        boolean isNotException = true;
        try {
            clazz.setTrgtman_nm(trgtman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetArr_trgtman_dept_nmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getArr_trgtman_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetArr_trgtman_dept_nmWithStringArrayArrTrgtmanDeptNmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String[] arr_trgtman_dept_nm = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setArr_trgtman_dept_nm(arr_trgtman_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTrgtman_dept_nmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getTrgtman_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTrgtman_dept_nmWithStringTrgtmanDeptNmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String trgtman_dept_nm = "trgtman_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setTrgtman_dept_nm(trgtman_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetArr_trgtman_jikgup_nmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getArr_trgtman_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetArr_trgtman_jikgup_nmWithStringArrayArrTrgtmanJikgupNmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String[] arr_trgtman_jikgup_nm = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setArr_trgtman_jikgup_nm(arr_trgtman_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTrgtman_jikgup_nmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getTrgtman_jikgup_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTrgtman_jikgup_nmWithStringTrgtmanJikgupNmShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String trgtman_jikgup_nm = "trgtman_jikgup_nm";
        boolean isNotException = true;
        try {
            clazz.setTrgtman_jikgup_nm(trgtman_jikgup_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMark_numShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getMark_num();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMark_numWithStringMarkNumShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String mark_num = "mark_num";
        boolean isNotException = true;
        try {
            clazz.setMark_num(mark_num);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_comp_cdShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_comp_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_comp_cdWithStringSrchCompCdShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String srch_comp_cd = "srch_comp_cd";
        boolean isNotException = true;
        try {
            clazz.setSrch_comp_cd(srch_comp_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_prgrs_status_cdShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_prgrs_status_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_prgrs_status_cdWithStringSrchPrgrsStatusCdShouldNotThrowException() throws Exception {
        LawConsultForm clazz = new LawConsultForm();
        String srch_prgrs_status_cd = "srch_prgrs_status_cd";
        boolean isNotException = true;
        try {
            clazz.setSrch_prgrs_status_cd(srch_prgrs_status_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
