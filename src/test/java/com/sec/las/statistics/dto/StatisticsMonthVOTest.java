package com.sec.las.statistics.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class StatisticsMonthVOTest {

    @Test(timeout = 20000)
    public void testSetSeqnosWithStringArraySeqnosShouldNotThrowException() {
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
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
        StatisticsMonthVO clazz = new StatisticsMonthVO();
        boolean isNotException = true;
        try {
            clazz.getCurPage();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
