package com.sec.las.statistics.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class StatisticsMonthFormTest {

    @Test(timeout = 20000)
    public void testSetSeqnosWithStringArraySeqnosShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        String[] seqnos = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setSeqnos(seqnos);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMonthdutyynsWithStringArrayMonthdutyynsShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        String[] monthdutyyns = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setMonthdutyyns(monthdutyyns);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTabsWithStringArrayTabsShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        String[] tabs = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setTabs(tabs);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTabWithStringTabShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        String tab = "tab";
        boolean isNotException = true;
        try {
            clazz.setTab(tab);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCrntmonth_rsltWithStringCrntmonthRsltShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        String crntmonth_rslt = "crntmonth_rslt";
        boolean isNotException = true;
        try {
            clazz.setCrntmonth_rslt(crntmonth_rslt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNextmonth_planWithStringNextmonthPlanShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        String nextmonth_plan = "nextmonth_plan";
        boolean isNotException = true;
        try {
            clazz.setNextmonth_plan(nextmonth_plan);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeqnoWithStringSeqnoShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        String seqno = "seqno";
        boolean isNotException = true;
        try {
            clazz.setSeqno(seqno);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDmstfrgn_gbnWithStringDmstfrgnGbnShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
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
    public void testSetSrch_dmstfrgn_gbnWithStringSrchDmstfrgnGbnShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        String srch_dmstfrgn_gbn = "srch_dmstfrgn_gbn";
        boolean isNotException = true;
        try {
            clazz.setSrch_dmstfrgn_gbn(srch_dmstfrgn_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPage_gbnWithStringPageGbnShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        String page_gbn = "page_gbn";
        boolean isNotException = true;
        try {
            clazz.setPage_gbn(page_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetContsWithStringArrayContsShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        String[] conts = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setConts(conts);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetContWithStringContShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
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
    public void testSetCnsdreq_idWithStringCnsdreqIdShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
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
    public void testSetCntrt_idWithStringCntrtIdShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
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
    public void testSetCnsd_deptWithStringCnsdDeptShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        String cnsd_dept = "cnsd_dept";
        boolean isNotException = true;
        try {
            clazz.setCnsd_dept(cnsd_dept);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCnsd_deptsWithStringArrayCnsdDeptsShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        String[] cnsd_depts = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setCnsd_depts(cnsd_depts);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMonthdutyynWithStringMonthdutyynShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
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
    public void testSetStart_indexWithStringStartIndexShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
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
    public void testSetEnd_indexWithStringEndIndexShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
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
    public void testSetCurPageWithStringCurPageShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
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
    public void testSetTransferWithStringTransferShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        String transfer = "transfer";
        boolean isNotException = true;
        try {
            clazz.setTransfer(transfer);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTotcntWithStringTotcntShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        String totcnt = "totcnt";
        boolean isNotException = true;
        try {
            clazz.setTotcnt(totcnt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBody_mimeWithStringBodyMimeShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
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
    public void testSetBody_mime1WithStringBodyMime1ShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        String body_mime1 = "body_mime1";
        boolean isNotException = true;
        try {
            clazz.setBody_mime1(body_mime1);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_regdt1WithStringSrchRegdt1ShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        String srch_regdt1 = "srch_regdt1";
        boolean isNotException = true;
        try {
            clazz.setSrch_regdt1(srch_regdt1);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_regdt2WithStringSrchRegdt2ShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        String srch_regdt2 = "srch_regdt2";
        boolean isNotException = true;
        try {
            clazz.setSrch_regdt2(srch_regdt2);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetResult_ynWithStringResultYnShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        String result_yn = "result_yn";
        boolean isNotException = true;
        try {
            clazz.setResult_yn(result_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_weeknoWithStringSrchWeeknoShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        String srch_weekno = "srch_weekno";
        boolean isNotException = true;
        try {
            clazz.setSrch_weekno(srch_weekno);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_weekfrWithStringSrchWeekfrShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        String srch_weekfr = "srch_weekfr";
        boolean isNotException = true;
        try {
            clazz.setSrch_weekfr(srch_weekfr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_weektoWithStringSrchWeektoShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        String srch_weekto = "srch_weekto";
        boolean isNotException = true;
        try {
            clazz.setSrch_weekto(srch_weekto);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetReg_idWithStringRegIdShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
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
    public void testSetDisplay_ynWithStringDisplayYnShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        String display_yn = "display_yn";
        boolean isNotException = true;
        try {
            clazz.setDisplay_yn(display_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDisplay_conf_ynWithStringDisplayConfYnShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        String display_conf_yn = "display_conf_yn";
        boolean isNotException = true;
        try {
            clazz.setDisplay_conf_yn(display_conf_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDisplay_conf_ynShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getDisplay_conf_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDisplay_ynShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getDisplay_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_idShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getReg_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_weektoShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_weekto();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_weekfrShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_weekfr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_weeknoShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_weekno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetResult_ynShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getResult_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_regdt1ShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_regdt1();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_regdt2ShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_regdt2();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBody_mimeShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getBody_mime();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBody_mime1ShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getBody_mime1();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTotcntShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getTotcnt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTransferShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getTransfer();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeqnosShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getSeqnos();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeqnoShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getSeqno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCrntmonth_rsltShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getCrntmonth_rslt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNextmonth_planShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getNextmonth_plan();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDmstfrgn_gbnShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getDmstfrgn_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_dmstfrgn_gbnShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_dmstfrgn_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetContsShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getConts();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetContShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getCont();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTabsShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getTabs();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTabShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getTab();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnsdreq_idShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getCnsdreq_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCntrt_idShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getCntrt_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnsd_deptShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getCnsd_dept();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCnsd_deptsShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getCnsd_depts();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMonthdutyynShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getMonthdutyyn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMonthdutyynsShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getMonthdutyyns();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPage_gbnShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getPage_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStart_indexShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getStart_index();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEnd_indexShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getEnd_index();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCurPageShouldNotThrowException() {
        StatisticsMonthForm clazz = new StatisticsMonthForm();
        boolean isNotException = true;
        try {
            clazz.getCurPage();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
