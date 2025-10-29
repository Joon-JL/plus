package com.sec.clm.counsel.dto;

import java.lang.Exception;
import com.sds.secframework.common.util.DateUtil;
import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class QnaVOTest {

    @Test(timeout = 20000)
    public void testConstructorNotNull() throws Exception {
        QnaVO obj = new QnaVO();
        assert obj != null;
    }

    @Test(timeout = 20000)
    public void testGetSrch_start_dtShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_start_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_start_dtWithStringSrchStartDtShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        String srch_start_dt = "srch_start_dt";
        boolean isNotException = true;
        try {
            clazz.setSrch_start_dt(srch_start_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_end_dtShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_end_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_end_dtWithStringSrchEndDtShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        String srch_end_dt = "srch_end_dt";
        boolean isNotException = true;
        try {
            clazz.setSrch_end_dt(srch_end_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_prgrs_statusShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
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
        QnaVO clazz = new QnaVO();
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
    public void testGetSrch_titleShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
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
        QnaVO clazz = new QnaVO();
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
    public void testGetSrch_select_titleShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getSrch_select_title();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_select_titleWithStringSrchSelectTitleShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        String srch_select_title = "srch_select_title";
        boolean isNotException = true;
        try {
            clazz.setSrch_select_title(srch_select_title);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReplyGuShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getReplyGu();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReplyGuWithStringReplyGuShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        String replyGu = "replyGu";
        boolean isNotException = true;
        try {
            clazz.setReplyGu(replyGu);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeqnoShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getSeqno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeqnoWithIntSeqnoShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
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
    public void testGetUp_seqnoShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getUp_seqno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUp_seqnoWithIntUpSeqnoShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        int up_seqno = 0;
        boolean isNotException = true;
        try {
            clazz.setUp_seqno(up_seqno);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPostup_srtShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getPostup_srt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPostup_srtWithIntPostupSrtShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        int postup_srt = 0;
        boolean isNotException = true;
        try {
            clazz.setPostup_srt(postup_srt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPostup_depthShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getPostup_depth();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPostup_depthWithIntPostupDepthShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        int postup_depth = 0;
        boolean isNotException = true;
        try {
            clazz.setPostup_depth(postup_depth);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBbs_kndShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getBbs_knd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBbs_kndWithStringBbsKndShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        String bbs_knd = "bbs_knd";
        boolean isNotException = true;
        try {
            clazz.setBbs_knd(bbs_knd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCont_typeShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getCont_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCont_typeWithStringContTypeShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
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
    public void testGetTitleShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getTitle();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTitleWithStringTitleShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
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
    public void testGetContShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getCont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetContWithStringContShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
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
    public void testGetRdcntShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getRdcnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRdcntWithIntRdcntShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        int rdcnt = 0;
        boolean isNotException = true;
        try {
            clazz.setRdcnt(rdcnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPrgrs_statusShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getPrgrs_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPrgrs_statusWithStringPrgrsStatusShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
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
    public void testGetTrans_dtShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getTrans_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTrans_dtWithStringTransDtShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        String trans_dt = "trans_dt";
        boolean isNotException = true;
        try {
            clazz.setTrans_dt(trans_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTrans_demnd_contShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getTrans_demnd_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTrans_demnd_contWithStringTransDemndContShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        String trans_demnd_cont = "trans_demnd_cont";
        boolean isNotException = true;
        try {
            clazz.setTrans_demnd_cont(trans_demnd_cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPub_ynShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
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
        QnaVO clazz = new QnaVO();
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
    public void testGetReg_operdivShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
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
        QnaVO clazz = new QnaVO();
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
    public void testGetReg_dtShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
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
        QnaVO clazz = new QnaVO();
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
    public void testGetReg_idShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getReg_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_idWithStringRegIdShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
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
    public void testGetReg_nmShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getReg_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_nmWithStringRegNmShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
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
    public void testGetReg_deptShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getReg_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_deptWithStringRegDeptShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
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
    public void testGetReg_dept_nmShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getReg_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_dept_nmWithStringRegDeptNmShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
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
    public void testGetMod_dtShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getMod_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_dtWithStringModDtShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
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
    public void testGetMod_idShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getMod_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_idWithStringModIdShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
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
    public void testGetMod_nmShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getMod_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMod_nmWithStringModNmShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
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
    public void testGetDel_ynShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getDel_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_ynWithStringDelYnShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
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
    public void testGetDel_dtShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getDel_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_dtWithStringDelDtShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
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
    public void testGetDel_idShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getDel_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_idWithStringDelIdShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
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
    public void testGetDel_nmShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getDel_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_nmWithStringDelNmShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
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
    public void testGetTop_roleShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getTop_role();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTop_roleWithStringTopRoleShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        String top_role = "top_role";
        boolean isNotException = true;
        try {
            clazz.setTop_role(top_role);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBbs_knd_nmShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getBbs_knd_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBbs_knd_nmWithStringBbsKndNmShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        String bbs_knd_nm = "bbs_knd_nm";
        boolean isNotException = true;
        try {
            clazz.setBbs_knd_nm(bbs_knd_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPrgrs_status_nmShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getPrgrs_status_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPrgrs_status_nmWithStringPrgrsStatusNmShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        String prgrs_status_nm = "prgrs_status_nm";
        boolean isNotException = true;
        try {
            clazz.setPrgrs_status_nm(prgrs_status_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRegman_jikgup_nmShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
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
        QnaVO clazz = new QnaVO();
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
    public void testGetReturnMessageShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        boolean isNotException = true;
        try {
            clazz.getReturnMessage();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReturnMessageWithStringReturnMessageShouldNotThrowException() throws Exception {
        QnaVO clazz = new QnaVO();
        String returnMessage = "returnMessage";
        boolean isNotException = true;
        try {
            clazz.setReturnMessage(returnMessage);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
