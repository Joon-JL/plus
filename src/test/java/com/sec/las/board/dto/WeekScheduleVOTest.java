package com.sec.las.board.dto;

import java.lang.String;
import org.junit.Test;
import java.util.Date;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class WeekScheduleVOTest {

    @Test(timeout = 20000)
    public void testSetUser_idWithStringUserIdShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
        String user_id = "user_id";
        boolean isNotException = true;
        try {
            clazz.setUser_id(user_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetYearWithStringYearShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
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
    public void testSetWeekseqWithStringWeekseqShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
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
    public void testSetWeekFirstDayWithStringWeekFirstDayShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
        String week_first_day = "week_first_day";
        boolean isNotException = true;
        try {
            clazz.setWeekFirstDay(week_first_day);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetWeekLastDayWithStringWeekLastDayShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
        String week_last_day = "week_last_day";
        boolean isNotException = true;
        try {
            clazz.setWeekLastDay(week_last_day);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUser_nmWithStringUserNmShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
        String user_nm = "user_nm";
        boolean isNotException = true;
        try {
            clazz.setUser_nm(user_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCrntweek_rsltWithStringCrntweekRsltShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
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
        WeekScheduleVO clazz = new WeekScheduleVO();
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
    public void testSetReg_dtWithStringRegDtShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
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
    public void testSetReg_idWithStringRegIdShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
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
    public void testSetReg_nmWithStringRegNmShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
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
    public void testSetMod_dtWithStringModDtShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
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
    public void testSetMod_idWithStringModIdShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
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
    public void testSetMod_nmWithStringModNmShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
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
    public void testSetStart_indexWithStringStartIndexShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
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
        WeekScheduleVO clazz = new WeekScheduleVO();
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
        WeekScheduleVO clazz = new WeekScheduleVO();
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
    public void testGetWeekFirstDayShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
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
        WeekScheduleVO clazz = new WeekScheduleVO();
        boolean isNotException = true;
        try {
            clazz.getWeekLastDay();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUser_idShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
        boolean isNotException = true;
        try {
            clazz.getUser_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetYearShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
        boolean isNotException = true;
        try {
            clazz.getYear();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetWeekseqShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
        boolean isNotException = true;
        try {
            clazz.getWeekseq();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUser_nmShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
        boolean isNotException = true;
        try {
            clazz.getUser_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCrntweek_rsltShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
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
        WeekScheduleVO clazz = new WeekScheduleVO();
        boolean isNotException = true;
        try {
            clazz.getNextweek_plan();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dtShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
        boolean isNotException = true;
        try {
            clazz.getReg_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_idShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
        boolean isNotException = true;
        try {
            clazz.getReg_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_nmShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
        boolean isNotException = true;
        try {
            clazz.getReg_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMod_dtShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
        boolean isNotException = true;
        try {
            clazz.getMod_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMod_idShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
        boolean isNotException = true;
        try {
            clazz.getMod_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMod_nmShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
        boolean isNotException = true;
        try {
            clazz.getMod_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStart_indexShouldNotThrowException() {
        WeekScheduleVO clazz = new WeekScheduleVO();
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
        WeekScheduleVO clazz = new WeekScheduleVO();
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
        WeekScheduleVO clazz = new WeekScheduleVO();
        boolean isNotException = true;
        try {
            clazz.getCurPage();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
