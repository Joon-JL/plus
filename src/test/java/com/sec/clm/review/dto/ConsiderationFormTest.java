package com.sec.clm.review.dto;

import com.sds.secframework.common.dto.CommonForm;
import com.sds.secframework.common.util.DateUtil;
import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class ConsiderationFormTest {

    @Test(timeout = 20000)
    public void testGetMark_numShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getMark_num();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetClose_contShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getClose_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetClose_contWithStringCloseContShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String close_cont = "close_cont";
        boolean isNotException = true;
        try {
            clazz.setClose_cont(close_cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_closed_ynShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_closed_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_closed_ynWithStringSrchClosedYnShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_closed_yn = "srch_closed_yn";
        boolean isNotException = true;
        try {
            clazz.setSrch_closed_yn(srch_closed_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRtn_contShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getRtn_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRtn_contWithStringRtnContShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String rtn_cont = "rtn_cont";
        boolean isNotException = true;
        try {
            clazz.setRtn_cont(rtn_cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMark_cntrt_idShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getMark_cntrt_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMark_cntrt_idWithStringMarkCntrtIdShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String mark_cntrt_id = "mark_cntrt_id";
        boolean isNotException = true;
        try {
            clazz.setMark_cntrt_id(mark_cntrt_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMark_numWithStringMarkNumShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
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
    public void testGetDimension_cdShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getDimension_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHq_cnsdreq_idShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getHq_cnsdreq_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHq_cnsdreq_idWithStringHqCnsdreqIdShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String hq_cnsdreq_id = "hq_cnsdreq_id";
        boolean isNotException = true;
        try {
            clazz.setHq_cnsdreq_id(hq_cnsdreq_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPrev_hq_cnsdreq_idShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getPrev_hq_cnsdreq_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPrev_hq_cnsdreq_idWithStringPrevHqCnsdreqIdShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String prev_hq_cnsdreq_id = "prev_hq_cnsdreq_id";
        boolean isNotException = true;
        try {
            clazz.setPrev_hq_cnsdreq_id(prev_hq_cnsdreq_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHq_req_titleShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getHq_req_title();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHq_req_titleWithStringHqReqTitleShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String hq_req_title = "hq_req_title";
        boolean isNotException = true;
        try {
            clazz.setHq_req_title(hq_req_title);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHq_cnsd_demnd_contShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getHq_cnsd_demnd_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHq_cnsd_demnd_contWithStringHqCnsdDemndContShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String hq_cnsd_demnd_cont = "hq_cnsd_demnd_cont";
        boolean isNotException = true;
        try {
            clazz.setHq_cnsd_demnd_cont(hq_cnsd_demnd_cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHq_cnsd_statusShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getHq_cnsd_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHq_cnsd_statusWithStringHqCnsdStatusShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String hq_cnsd_status = "hq_cnsd_status";
        boolean isNotException = true;
        try {
            clazz.setHq_cnsd_status(hq_cnsd_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetItem_cdShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getItem_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetItem_cdWithStringItemCdShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String item_cd = "item_cd";
        boolean isNotException = true;
        try {
            clazz.setItem_cd(item_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDis_ynShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getDis_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDis_ynWithStringDisYnShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String dis_yn = "dis_yn";
        boolean isNotException = true;
        try {
            clazz.setDis_yn(dis_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRemarkShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getRemark();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRemarkWithStringRemarkShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String remark = "remark";
        boolean isNotException = true;
        try {
            clazz.setRemark(remark);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSum_textShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSum_text();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSum_textWithStringSumTextShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String sum_text = "sum_text";
        boolean isNotException = true;
        try {
            clazz.setSum_text(sum_text);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnsd_levelShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getCnsd_level();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnsd_levelWithStringCnsdLevelShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String cnsd_level = "cnsd_level";
        boolean isNotException = true;
        try {
            clazz.setCnsd_level(cnsd_level);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetsMultCompShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getsMultComp();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetsMultCompWithStringSMultCompShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String sMultComp = "sMultComp";
        boolean isNotException = true;
        try {
            clazz.setsMultComp(sMultComp);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetsMultYnShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getsMultYn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetsMultYnWithStringSMultYnShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String sMultYn = "sMultYn";
        boolean isNotException = true;
        try {
            clazz.setsMultYn(sMultYn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_gerp_codeShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_gerp_code();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_gerp_codeWithStringSrchGerpCodeShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_gerp_code = "srch_gerp_code";
        boolean isNotException = true;
        try {
            clazz.setSrch_gerp_code(srch_gerp_code);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDimension_cdWithStringDimensionCdShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String dimension_cd = "dimension_cd";
        boolean isNotException = true;
        try {
            clazz.setDimension_cd(dimension_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBlngt_orgnzShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getBlngt_orgnz();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBlngt_orgnzWithStringBlngtOrgnzShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String blngt_orgnz = "blngt_orgnz";
        boolean isNotException = true;
        try {
            clazz.setBlngt_orgnz(blngt_orgnz);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_respman_idShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_respman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_respman_idWithStringSrchRespmanIdShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
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
    public void testGetSrch_reqman_idShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_reqman_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_reqman_idWithStringSrchReqmanIdShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_reqman_id = "srch_reqman_id";
        boolean isNotException = true;
        try {
            clazz.setSrch_reqman_id(srch_reqman_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_prgrs_statusShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_prgrs_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_prgrs_statusWithStringSrchPrgrsStatusShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
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
    public void testGetCntrt_oppnt_nmShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_oppnt_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_oppnt_nmWithStringCntrtOppntNmShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String cntrt_oppnt_nm = "cntrt_oppnt_nm";
        boolean isNotException = true;
        try {
            clazz.setCntrt_oppnt_nm(cntrt_oppnt_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPlndbn_req_ynShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getPlndbn_req_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPlndbn_req_ynWithStringPlndbnReqYnShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String plndbn_req_yn = "plndbn_req_yn";
        boolean isNotException = true;
        try {
            clazz.setPlndbn_req_yn(plndbn_req_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_req_titleShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_req_title();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_req_titleWithStringSrchReqTitleShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_req_title = "srch_req_title";
        boolean isNotException = true;
        try {
            clazz.setSrch_req_title(srch_req_title);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_cnclsnpurpsShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_cnclsnpurps();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_cnclsnpurpsWithStringSrchCnclsnpurpsShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_cnclsnpurps = "srch_cnclsnpurps";
        boolean isNotException = true;
        try {
            clazz.setSrch_cnclsnpurps(srch_cnclsnpurps);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_start_dtShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_start_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_start_dtWithStringSrchStartDtShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
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
    public void testGetSrch_end_dtShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_end_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_end_dtWithStringSrchEndDtShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
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
    public void testGetSrch_reqman_nmShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_reqman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_reqman_nmWithStringSrchReqmanNmShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_reqman_nm = "srch_reqman_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_reqman_nm(srch_reqman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_respman_nmShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_respman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_respman_nmWithStringSrchRespmanNmShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
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
    public void testGetSrch_type_cdShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_type_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_type_cdWithStringSrchTypeCdShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_type_cd = "srch_type_cd";
        boolean isNotException = true;
        try {
            clazz.setSrch_type_cd(srch_type_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_orgnzShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_orgnz();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_orgnzWithStringSrchOrgnzShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_orgnz = "srch_orgnz";
        boolean isNotException = true;
        try {
            clazz.setSrch_orgnz(srch_orgnz);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_owner_deptShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_owner_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_owner_deptWithStringSrchOwnerDeptShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_owner_dept = "srch_owner_dept";
        boolean isNotException = true;
        try {
            clazz.setSrch_owner_dept(srch_owner_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_law_statusShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_law_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_law_statusWithStringSrchLawStatusShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_law_status = "srch_law_status";
        boolean isNotException = true;
        try {
            clazz.setSrch_law_status(srch_law_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_ip_statusShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_ip_status();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_ip_statusWithStringSrchIpStatusShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_ip_status = "srch_ip_status";
        boolean isNotException = true;
        try {
            clazz.setSrch_ip_status(srch_ip_status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_biz_depthShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_biz_depth();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_biz_depthWithStringSrchBizDepthShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_biz_depth = "srch_biz_depth";
        boolean isNotException = true;
        try {
            clazz.setSrch_biz_depth(srch_biz_depth);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_cntrt_oppnt_nmShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_cntrt_oppnt_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_cntrt_oppnt_nmWithStringSrchCntrtOppntNmShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_cntrt_oppnt_nm = "srch_cntrt_oppnt_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_cntrt_oppnt_nm(srch_cntrt_oppnt_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStart_indexShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getStart_index();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetStart_indexWithStringStartIndexShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
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
    public void testGetEnd_indexShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getEnd_index();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEnd_indexWithStringEndIndexShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
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
    public void testGetCurPageShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getCurPage();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCurPageWithStringCurPageShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
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
    public void testGetRow_cntShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getRow_cnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRow_cntWithIntRowCntShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
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
    public void testGetCnsdreq_idShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getCnsdreq_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnsdreq_idWithStringCnsdreqIdShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String cnsdreq_id = "cnsdreq_id";
        boolean isNotException = true;
        try {
            clazz.setCnsdreq_id(cnsdreq_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_idShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_idWithStringCntrtIdShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
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
    public void testGetCntrt_srch_ynShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_srch_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCntrt_srch_ynWithStringCntrtSrchYnShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String cntrt_srch_yn = "cntrt_srch_yn";
        boolean isNotException = true;
        try {
            clazz.setCntrt_srch_yn(cntrt_srch_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPage_flagShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getPage_flag();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPage_flagWithStringPageFlagShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String page_flag = "page_flag";
        boolean isNotException = true;
        try {
            clazz.setPage_flag(page_flag);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStat_flagShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getStat_flag();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetStat_flagWithStringStatFlagShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String stat_flag = "stat_flag";
        boolean isNotException = true;
        try {
            clazz.setStat_flag(stat_flag);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDmstfrgnShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getDmstfrgn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDmstfrgnWithStringDmstfrgnShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String dmstfrgn = "dmstfrgn";
        boolean isNotException = true;
        try {
            clazz.setDmstfrgn(dmstfrgn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetList_modeShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getList_mode();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetList_modeWithStringListModeShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String list_mode = "list_mode";
        boolean isNotException = true;
        try {
            clazz.setList_mode(list_mode);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_review_titleShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_review_title();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_review_titleWithStringSrchReviewTitleShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_review_title = "srch_review_title";
        boolean isNotException = true;
        try {
            clazz.setSrch_review_title(srch_review_title);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_start_reqdayShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_start_reqday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_start_reqdayWithStringSrchStartReqdayShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_start_reqday = "srch_start_reqday";
        boolean isNotException = true;
        try {
            clazz.setSrch_start_reqday(srch_start_reqday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_end_reqdayShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_end_reqday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_end_reqdayWithStringSrchEndReqdayShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_end_reqday = "srch_end_reqday";
        boolean isNotException = true;
        try {
            clazz.setSrch_end_reqday(srch_end_reqday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_cnsdman_nmShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_cnsdman_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_cnsdman_nmWithStringSrchCnsdmanNmShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_cnsdman_nm = "srch_cnsdman_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_cnsdman_nm(srch_cnsdman_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_resp_deptShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_resp_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_resp_deptWithStringSrchRespDeptShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_resp_dept = "srch_resp_dept";
        boolean isNotException = true;
        try {
            clazz.setSrch_resp_dept(srch_resp_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_biz_clsfcnShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_biz_clsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_biz_clsfcnWithStringSrchBizClsfcnShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_biz_clsfcn = "srch_biz_clsfcn";
        boolean isNotException = true;
        try {
            clazz.setSrch_biz_clsfcn(srch_biz_clsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_cnclsnpurps_bigclsfcnShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_cnclsnpurps_bigclsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_cnclsnpurps_bigclsfcnWithStringSrchCnclsnpurpsBigclsfcnShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_cnclsnpurps_bigclsfcn = "srch_cnclsnpurps_bigclsfcn";
        boolean isNotException = true;
        try {
            clazz.setSrch_cnclsnpurps_bigclsfcn(srch_cnclsnpurps_bigclsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_stepShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_step();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_stepWithStringSrchStepShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_step = "srch_step";
        boolean isNotException = true;
        try {
            clazz.setSrch_step(srch_step);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_stateShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_state();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_stateWithStringSrchStateShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_state = "srch_state";
        boolean isNotException = true;
        try {
            clazz.setSrch_state(srch_state);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_oppnt_cdShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_oppnt_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_oppnt_cdWithStringSrchOppntCdShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_oppnt_cd = "srch_oppnt_cd";
        boolean isNotException = true;
        try {
            clazz.setSrch_oppnt_cd(srch_oppnt_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_resp_dept_nmShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_resp_dept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_resp_dept_nmWithStringSrchRespDeptNmShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_resp_dept_nm = "srch_resp_dept_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_resp_dept_nm(srch_resp_dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_oppnt_nmShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_oppnt_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_oppnt_nmWithStringSrchOppntNmShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_oppnt_nm = "srch_oppnt_nm";
        boolean isNotException = true;
        try {
            clazz.setSrch_oppnt_nm(srch_oppnt_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_start_cnlsndayShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_start_cnlsnday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_start_cnlsndayWithStringSrchStartCnlsndayShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_start_cnlsnday = "srch_start_cnlsnday";
        boolean isNotException = true;
        try {
            clazz.setSrch_start_cnlsnday(srch_start_cnlsnday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_end_cnlsndayShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_end_cnlsnday();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_end_cnlsndayWithStringSrchEndCnlsndayShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_end_cnlsnday = "srch_end_cnlsnday";
        boolean isNotException = true;
        try {
            clazz.setSrch_end_cnlsnday(srch_end_cnlsnday);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_cnsd_contShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_cnsd_cont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_cnsd_contWithStringSrchCnsdContShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_cnsd_cont = "srch_cnsd_cont";
        boolean isNotException = true;
        try {
            clazz.setSrch_cnsd_cont(srch_cnsd_cont);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_if_sys_cdShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_if_sys_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_if_sys_cdWithStringSrchIfSysCdShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_if_sys_cd = "srch_if_sys_cd";
        boolean isNotException = true;
        try {
            clazz.setSrch_if_sys_cd(srch_if_sys_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetsElCompShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getsElComp();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetsElCompWithStringSElCompShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String sElComp = "sElComp";
        boolean isNotException = true;
        try {
            clazz.setsElComp(sElComp);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetsSel_grdShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getsSel_grd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetsSel_grdWithStringSSelGrdShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
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
    public void testGetSrch_solo_ynShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_solo_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_solo_ynWithStringSrchSoloYnShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
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
    public void testGetSrch_cntrt_noShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_cntrt_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_cntrt_noWithStringSrchCntrtNoShouldNotThrowException() {
        ConsiderationForm clazz = new ConsiderationForm();
        String srch_cntrt_no = "srch_cntrt_no";
        boolean isNotException = true;
        try {
            clazz.setSrch_cntrt_no(srch_cntrt_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
