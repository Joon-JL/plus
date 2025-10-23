package com.sec.las.statistics.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class StatisticsWeekVOTest {

    @Test(timeout = 20000)
    public void testSetSeqnosWithStringArraySeqnosShouldNotThrowException() {
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
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
        StatisticsWeekVO clazz = new StatisticsWeekVO();
        boolean isNotException = true;
        try {
            clazz.getCurPage();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
