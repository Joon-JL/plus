package com.sds.secframework.log.dto;

import com.sds.secframework.common.util.DateUtil;
import java.lang.String;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class LogVOTest {

    @Test(timeout = 20000)
    public void testGetUser_nmShouldNotThrowException() {
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
    public void testGetYearShouldNotThrowException() {
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
    public void testGetMenu_idShouldNotThrowException() {
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
    public void testGetMenu_nmShouldNotThrowException() {
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
    public void testGetUser_idShouldNotThrowException() {
        LogVO clazz = new LogVO();
        boolean isNotException = true;
        try {
            clazz.getUser_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetUser_idWithStringUserIdShouldNotThrowException() {
        LogVO clazz = new LogVO();
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
    public void testGetSession_idShouldNotThrowException() {
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
    public void testGetTable_nmShouldNotThrowException() {
        LogVO clazz = new LogVO();
        boolean isNotException = true;
        try {
            clazz.getTable_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTable_nmWithStringTableNmShouldNotThrowException() {
        LogVO clazz = new LogVO();
        String table_nm = "table_nm";
        boolean isNotException = true;
        try {
            clazz.setTable_nm(table_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStart_timeShouldNotThrowException() {
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
    public void testGetAuth_comp_cdShouldNotThrowException() {
        LogVO clazz = new LogVO();
        boolean isNotException = true;
        try {
            clazz.getAuth_comp_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetAuth_comp_cdWithStringAuthCompCdShouldNotThrowException() {
        LogVO clazz = new LogVO();
        String auth_comp_cd = "auth_comp_cd";
        boolean isNotException = true;
        try {
            clazz.setAuth_comp_cd(auth_comp_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_start_dtShouldNotThrowException() {
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
        LogVO clazz = new LogVO();
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
