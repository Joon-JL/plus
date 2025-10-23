package com.sec.las.lawconsulting.dto;

import java.lang.Exception;
import com.sds.secframework.common.dto.CommonForm;
import com.sds.secframework.common.util.DateUtil;
import java.lang.String;
import org.junit.Test;
import java.util.List;
import static org.mockito.Mockito.mock;

public class SpeakConsultFormTest {

    @Test(timeout = 20000)
    public void testConstructorNotNull() throws Exception {
        SpeakConsultForm obj = new SpeakConsultForm();
        assert obj != null;
    }

    @Test(timeout = 20000)
    public void testSetSeqnoWithIntSeqnoShouldNotThrowException() throws Exception {
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
    public void testSetStart_indexWithStringStartIndexShouldNotThrowException() throws Exception {
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
    public void testGetSeqnoShouldNotThrowException() throws Exception {
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
        boolean isNotException = true;
        try {
            clazz.getDel_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStart_indexShouldNotThrowException() throws Exception {
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
        boolean isNotException = true;
        try {
            clazz.getCurPage();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRow_cntShouldNotThrowException() throws Exception {
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
    public void testGetBoard_nmShouldNotThrowException() throws Exception {
        SpeakConsultForm clazz = new SpeakConsultForm();
        boolean isNotException = true;
        try {
            clazz.getBoard_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBoard_nmWithStringBoardNmShouldNotThrowException() throws Exception {
        SpeakConsultForm clazz = new SpeakConsultForm();
        String board_nm = "board_nm";
        boolean isNotException = true;
        try {
            clazz.setBoard_nm(board_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testIsAuth_insertShouldNotThrowException() throws Exception {
        SpeakConsultForm clazz = new SpeakConsultForm();
        boolean isNotException = true;
        try {
            clazz.isAuth_insert();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_insertWithBooleanAuthInsertShouldNotThrowException() throws Exception {
        SpeakConsultForm clazz = new SpeakConsultForm();
        boolean auth_insert = false;
        boolean isNotException = true;
        try {
            clazz.setAuth_insert(auth_insert);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testIsAuth_modifyShouldNotThrowException() throws Exception {
        SpeakConsultForm clazz = new SpeakConsultForm();
        boolean isNotException = true;
        try {
            clazz.isAuth_modify();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_modifyWithBooleanAuthModifyShouldNotThrowException() throws Exception {
        SpeakConsultForm clazz = new SpeakConsultForm();
        boolean auth_modify = false;
        boolean isNotException = true;
        try {
            clazz.setAuth_modify(auth_modify);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testIsAuth_deleteShouldNotThrowException() throws Exception {
        SpeakConsultForm clazz = new SpeakConsultForm();
        boolean isNotException = true;
        try {
            clazz.isAuth_delete();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_deleteWithBooleanAuthDeleteShouldNotThrowException() throws Exception {
        SpeakConsultForm clazz = new SpeakConsultForm();
        boolean auth_delete = false;
        boolean isNotException = true;
        try {
            clazz.setAuth_delete(auth_delete);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_respman_nmShouldNotThrowException() throws Exception {
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
    public void testGetReturn_titleShouldNotThrowException() throws Exception {
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
    public void testGetSpeakcontract_listShouldNotThrowException() throws Exception {
        SpeakConsultForm clazz = new SpeakConsultForm();
        boolean isNotException = true;
        try {
            clazz.getSpeakcontract_list();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSpeakcontract_listWithListSpeakcontractListShouldNotThrowException() throws Exception {
        SpeakConsultForm clazz = new SpeakConsultForm();
        List speakcontract_list = mock(List.class);
        boolean isNotException = true;
        try {
            clazz.setSpeakcontract_list(speakcontract_list);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetIsForeignShouldNotThrowException() throws Exception {
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
        SpeakConsultForm clazz = new SpeakConsultForm();
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
