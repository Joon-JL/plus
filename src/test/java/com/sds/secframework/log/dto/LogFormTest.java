package com.sds.secframework.log.dto;

import com.sds.secframework.common.dto.CommonForm;
import com.sds.secframework.common.util.DateUtil;
import java.lang.String;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.mockito.Mockito.mock;

public class LogFormTest {

    @Test(timeout = 20000)
    public void testGetUser_nmShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getUser_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUser_nmWithStringUserNmShouldNotThrowException() {
        LogForm clazz = new LogForm();
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
    public void testGetDept_nmShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getDept_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDept_nmWithStringDeptNmShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String dept_nm = "dept_nm";
        boolean isNotException = true;
        try {
            clazz.setDept_nm(dept_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetComp_nmShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getComp_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetComp_nmWithStringCompNmShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String comp_nm = "comp_nm";
        boolean isNotException = true;
        try {
            clazz.setComp_nm(comp_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetYearShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getYear();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetYearWithStringYearShouldNotThrowException() {
        LogForm clazz = new LogForm();
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
    public void testGetMonthShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getMonth();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMonthWithStringMonthShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String month = "month";
        boolean isNotException = true;
        try {
            clazz.setMonth(month);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDayShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getDay();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDayWithStringDayShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String day = "day";
        boolean isNotException = true;
        try {
            clazz.setDay(day);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetHourShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getHour();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetHourWithStringHourShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String hour = "hour";
        boolean isNotException = true;
        try {
            clazz.setHour(hour);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetLogin_dtShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getLogin_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLogin_dtWithStringLoginDtShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String login_dt = "login_dt";
        boolean isNotException = true;
        try {
            clazz.setLogin_dt(login_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetLogout_dtShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getLogout_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLogout_dtWithStringLogoutDtShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String logout_dt = "logout_dt";
        boolean isNotException = true;
        try {
            clazz.setLogout_dt(logout_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetIp_addressShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getIp_address();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetIp_addressWithStringIpAddressShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String ip_address = "ip_address";
        boolean isNotException = true;
        try {
            clazz.setIp_address(ip_address);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBrowser_typeShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getBrowser_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBrowser_typeWithStringBrowserTypeShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String browser_type = "browser_type";
        boolean isNotException = true;
        try {
            clazz.setBrowser_type(browser_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMenu_nmShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getMenu_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMenu_nmWithStringMenuNmShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String menu_nm = "menu_nm";
        boolean isNotException = true;
        try {
            clazz.setMenu_nm(menu_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetContact_dtShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getContact_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetContact_dtWithStringContactDtShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String contact_dt = "contact_dt";
        boolean isNotException = true;
        try {
            clazz.setContact_dt(contact_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_info_strShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getFile_info_str();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFile_info_strWithStringFileInfoStrShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String file_info_str = "file_info_str";
        boolean isNotException = true;
        try {
            clazz.setFile_info_str(file_info_str);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_pathShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getFile_path();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFile_pathWithStringFilePathShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String file_path = "file_path";
        boolean isNotException = true;
        try {
            clazz.setFile_path(file_path);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_nmShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getFile_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFile_nmWithStringFileNmShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String file_nm = "file_nm";
        boolean isNotException = true;
        try {
            clazz.setFile_nm(file_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetUse_gubunShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getUse_gubun();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUse_gubunWithStringUseGubunShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String use_gubun = "use_gubun";
        boolean isNotException = true;
        try {
            clazz.setUse_gubun(use_gubun);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStart_dateShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getStart_date();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetStart_dateWithStringStartDateShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String start_date = "start_date";
        boolean isNotException = true;
        try {
            clazz.setStart_date(start_date);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEnd_dateShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getEnd_date();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEnd_dateWithStringEndDateShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String end_date = "end_date";
        boolean isNotException = true;
        try {
            clazz.setEnd_date(end_date);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSearch_nameShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getSearch_name();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSearch_nameWithStringSearchNameShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String search_name = "search_name";
        boolean isNotException = true;
        try {
            clazz.setSearch_name(search_name);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSearch_comboShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getSearch_combo();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSearch_comboWithStringSearchComboShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String search_combo = "search_combo";
        boolean isNotException = true;
        try {
            clazz.setSearch_combo(search_combo);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRange_dateShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getRange_date();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRange_dateWithIntRangeDateShouldNotThrowException() {
        LogForm clazz = new LogForm();
        int range_date = 0;
        boolean isNotException = true;
        try {
            clazz.setRange_date(range_date);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSdfShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getSdf();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSdfWithSimpleDateFormatSdfShouldNotThrowException() {
        LogForm clazz = new LogForm();
        SimpleDateFormat sdf = mock(SimpleDateFormat.class);
        boolean isNotException = true;
        try {
            clazz.setSdf(sdf);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCurDateShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getCurDate();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCurDateWithDateCurDateShouldNotThrowException() {
        LogForm clazz = new LogForm();
        Date curDate = mock(Date.class);
        boolean isNotException = true;
        try {
            clazz.setCurDate(curDate);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMenu_idShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getMenu_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMenu_idWithStringMenuIdShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String menu_id = "menu_id";
        boolean isNotException = true;
        try {
            clazz.setMenu_id(menu_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSession_idShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getSession_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSession_idWithStringSessionIdShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String session_id = "session_id";
        boolean isNotException = true;
        try {
            clazz.setSession_id(session_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetExcel_gbnShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getExcel_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetExcel_gbnWithStringExcelGbnShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String excel_gbn = "excel_gbn";
        boolean isNotException = true;
        try {
            clazz.setExcel_gbn(excel_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStart_timeShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getStart_time();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetStart_timeWithStringStartTimeShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String start_time = "start_time";
        boolean isNotException = true;
        try {
            clazz.setStart_time(start_time);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEnd_timeShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getEnd_time();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEnd_timeWithStringEndTimeShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String end_time = "end_time";
        boolean isNotException = true;
        try {
            clazz.setEnd_time(end_time);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStart_datetimeShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getStart_datetime();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetStart_datetimeWithStringStartDatetimeShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String start_datetime = "start_datetime";
        boolean isNotException = true;
        try {
            clazz.setStart_datetime(start_datetime);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEnd_datetimeShouldNotThrowException() {
        LogForm clazz = new LogForm();
        boolean isNotException = true;
        try {
            clazz.getEnd_datetime();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEnd_datetimeWithStringEndDatetimeShouldNotThrowException() {
        LogForm clazz = new LogForm();
        String end_datetime = "end_datetime";
        boolean isNotException = true;
        try {
            clazz.setEnd_datetime(end_datetime);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_start_dtShouldNotThrowException() {
        LogForm clazz = new LogForm();
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
        LogForm clazz = new LogForm();
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
        LogForm clazz = new LogForm();
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
        LogForm clazz = new LogForm();
        String srch_end_dt = "srch_end_dt";
        boolean isNotException = true;
        try {
            clazz.setSrch_end_dt(srch_end_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
