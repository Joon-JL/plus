package com.sec.las.statistics.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class StatisticsWeekFormTest {

    @Test(timeout = 20000)
    public void testSetSeqnosWithStringArraySeqnosShouldNotThrowException() {
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
    public void testSetWeekdutyynsWithStringArrayWeekdutyynsShouldNotThrowException() {
        StatisticsWeekForm clazz = new StatisticsWeekForm();
        String[] weekdutyyns = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setWeekdutyyns(weekdutyyns);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTabsWithStringArrayTabsShouldNotThrowException() {
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
    public void testSetCrntweek_rsltWithStringCrntweekRsltShouldNotThrowException() {
        StatisticsWeekForm clazz = new StatisticsWeekForm();
        String crntweek_rslt = "crntweek_rslt";
        boolean isNotException = true;
        try {
            clazz.setCrntweek_rslt(crntweek_rslt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNextweek_planWithStringNextweekPlanShouldNotThrowException() {
        StatisticsWeekForm clazz = new StatisticsWeekForm();
        String nextweek_plan = "nextweek_plan";
        boolean isNotException = true;
        try {
            clazz.setNextweek_plan(nextweek_plan);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeqnoWithStringSeqnoShouldNotThrowException() {
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
    public void testSetWeekdutyynWithStringWeekdutyynShouldNotThrowException() {
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
    public void testSetStart_indexWithStringStartIndexShouldNotThrowException() {
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
    public void testSetWeekseqWithStringWeekseqShouldNotThrowException() {
        StatisticsWeekForm clazz = new StatisticsWeekForm();
        String weekseq = "weekseq";
        boolean isNotException = true;
        try {
            clazz.setWeekseq(weekseq);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetYearWithStringYearShouldNotThrowException() {
        StatisticsWeekForm clazz = new StatisticsWeekForm();
        String year = "year";
        boolean isNotException = true;
        try {
            clazz.setYear(year);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetWeekFirstDayWithStringWeekFirstDayShouldNotThrowException() {
        StatisticsWeekForm clazz = new StatisticsWeekForm();
        String weekFirstDay = "weekFirstDay";
        boolean isNotException = true;
        try {
            clazz.setWeekFirstDay(weekFirstDay);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetWeekLastDayWithStringWeekLastDayShouldNotThrowException() {
        StatisticsWeekForm clazz = new StatisticsWeekForm();
        String weekLastDay = "weekLastDay";
        boolean isNotException = true;
        try {
            clazz.setWeekLastDay(weekLastDay);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetWeekseqShouldNotThrowException() {
        StatisticsWeekForm clazz = new StatisticsWeekForm();
        boolean isNotException = true;
        try {
            clazz.getWeekseq();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetYearShouldNotThrowException() {
        StatisticsWeekForm clazz = new StatisticsWeekForm();
        boolean isNotException = true;
        try {
            clazz.getYear();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetWeekFirstDayShouldNotThrowException() {
        StatisticsWeekForm clazz = new StatisticsWeekForm();
        boolean isNotException = true;
        try {
            clazz.getWeekFirstDay();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetWeekLastDayShouldNotThrowException() {
        StatisticsWeekForm clazz = new StatisticsWeekForm();
        boolean isNotException = true;
        try {
            clazz.getWeekLastDay();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDisplay_conf_ynShouldNotThrowException() {
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
        boolean isNotException = true;
        try {
            clazz.getSeqno();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCrntweek_rsltShouldNotThrowException() {
        StatisticsWeekForm clazz = new StatisticsWeekForm();
        boolean isNotException = true;
        try {
            clazz.getCrntweek_rslt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNextweek_planShouldNotThrowException() {
        StatisticsWeekForm clazz = new StatisticsWeekForm();
        boolean isNotException = true;
        try {
            clazz.getNextweek_plan();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDmstfrgn_gbnShouldNotThrowException() {
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
        boolean isNotException = true;
        try {
            clazz.getCnsd_depts();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetWeekdutyynShouldNotThrowException() {
        StatisticsWeekForm clazz = new StatisticsWeekForm();
        boolean isNotException = true;
        try {
            clazz.getWeekdutyyn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetWeekdutyynsShouldNotThrowException() {
        StatisticsWeekForm clazz = new StatisticsWeekForm();
        boolean isNotException = true;
        try {
            clazz.getWeekdutyyns();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPage_gbnShouldNotThrowException() {
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
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
        StatisticsWeekForm clazz = new StatisticsWeekForm();
        boolean isNotException = true;
        try {
            clazz.getCurPage();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
