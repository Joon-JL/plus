package com.sec.clm.manage.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class MyNoticeFormTest {

    @Test(timeout = 20000)
    public void testSetModule_idWithStringModuleIdShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        String module_id = "module_id";
        boolean isNotException = true;
        try {
            clazz.setModule_id(module_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMis_idWithStringMisIdShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        String mis_id = "mis_id";
        boolean isNotException = true;
        try {
            clazz.setMis_id(mis_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMsg_keyWithStringMsgKeyShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        String msg_key = "msg_key";
        boolean isNotException = true;
        try {
            clazz.setMsg_key(msg_key);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSubjectWithStringSubjectShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        String subject = "subject";
        boolean isNotException = true;
        try {
            clazz.setSubject(subject);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBodyWithStringBodyShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        String body = "body";
        boolean isNotException = true;
        try {
            clazz.setBody(body);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMsg_typeWithStringMsgTypeShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        String msg_type = "msg_type";
        boolean isNotException = true;
        try {
            clazz.setMsg_type(msg_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBhtml_content_checkWithStringBhtmlContentCheckShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        String bhtml_content_check = "bhtml_content_check";
        boolean isNotException = true;
        try {
            clazz.setBhtml_content_check(bhtml_content_check);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetIfile_countWithStringIfileCountShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        String ifile_count = "ifile_count";
        boolean isNotException = true;
        try {
            clazz.setIfile_count(ifile_count);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTime_zoneWithStringTimeZoneShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        String time_zone = "time_zone";
        boolean isNotException = true;
        try {
            clazz.setTime_zone(time_zone);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetIs_dstWithStringIsDstShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        String is_dst = "is_dst";
        boolean isNotException = true;
        try {
            clazz.setIs_dst(is_dst);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSender_mail_addrWithStringSenderMailAddrShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        String sender_mail_addr = "sender_mail_addr";
        boolean isNotException = true;
        try {
            clazz.setSender_mail_addr(sender_mail_addr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSender_single_idWithStringSenderSingleIdShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        String sender_single_id = "sender_single_id";
        boolean isNotException = true;
        try {
            clazz.setSender_single_id(sender_single_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetStatusWithStringStatusShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        String status = "status";
        boolean isNotException = true;
        try {
            clazz.setStatus(status);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCreate_dateWithStringCreateDateShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        String create_date = "create_date";
        boolean isNotException = true;
        try {
            clazz.setCreate_date(create_date);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetStart_indexWithStringStartIndexShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
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
        MyNoticeForm clazz = new MyNoticeForm();
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
        MyNoticeForm clazz = new MyNoticeForm();
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
    public void testGetModule_idShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        boolean isNotException = true;
        try {
            clazz.getModule_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMis_idShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        boolean isNotException = true;
        try {
            clazz.getMis_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMsg_keyShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        boolean isNotException = true;
        try {
            clazz.getMsg_key();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSubjectShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        boolean isNotException = true;
        try {
            clazz.getSubject();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBodyShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        boolean isNotException = true;
        try {
            clazz.getBody();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMsg_typeShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        boolean isNotException = true;
        try {
            clazz.getMsg_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBhtml_content_checkShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        boolean isNotException = true;
        try {
            clazz.getBhtml_content_check();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetIfile_countShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        boolean isNotException = true;
        try {
            clazz.getIfile_count();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetTime_zoneShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        boolean isNotException = true;
        try {
            clazz.getTime_zone();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetIs_dstShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        boolean isNotException = true;
        try {
            clazz.getIs_dst();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSender_mail_addrShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        boolean isNotException = true;
        try {
            clazz.getSender_mail_addr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSender_single_idShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        boolean isNotException = true;
        try {
            clazz.getSender_single_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStatusShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        boolean isNotException = true;
        try {
            clazz.getStatus();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetCreate_dateShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        boolean isNotException = true;
        try {
            clazz.getCreate_date();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetStart_indexShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
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
        MyNoticeForm clazz = new MyNoticeForm();
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
        MyNoticeForm clazz = new MyNoticeForm();
        boolean isNotException = true;
        try {
            clazz.getCurPage();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_start_reg_dtShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_start_reg_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_start_reg_dtWithStringSrchStartRegDtShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        String srch_start_reg_dt = "srch_start_reg_dt";
        boolean isNotException = true;
        try {
            clazz.setSrch_start_reg_dt(srch_start_reg_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_end_reg_dtShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_end_reg_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_end_reg_dtWithStringSrchEndRegDtShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        String srch_end_reg_dt = "srch_end_reg_dt";
        boolean isNotException = true;
        try {
            clazz.setSrch_end_reg_dt(srch_end_reg_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_keyword_mailtitleShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_keyword_mailtitle();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_keyword_mailtitleWithStringSrchKeywordMailtitleShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        String srch_keyword_mailtitle = "srch_keyword_mailtitle";
        boolean isNotException = true;
        try {
            clazz.setSrch_keyword_mailtitle(srch_keyword_mailtitle);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_AddresseeShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_Addressee();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_AddresseeWithStringSrchAddresseeShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        String srch_Addressee = "srch_Addressee";
        boolean isNotException = true;
        try {
            clazz.setSrch_Addressee(srch_Addressee);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_statShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_stat();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_statWithStringSrchStatShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        String srch_stat = "srch_stat";
        boolean isNotException = true;
        try {
            clazz.setSrch_stat(srch_stat);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_stepShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
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
        MyNoticeForm clazz = new MyNoticeForm();
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
    public void testGetNameShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        boolean isNotException = true;
        try {
            clazz.getName();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNameWithStringNameShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        String name = "name";
        boolean isNotException = true;
        try {
            clazz.setName(name);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSrch_nameShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        boolean isNotException = true;
        try {
            clazz.getSrch_name();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSrch_nameWithStringSrchNameShouldNotThrowException() {
        MyNoticeForm clazz = new MyNoticeForm();
        String srch_name = "srch_name";
        boolean isNotException = true;
        try {
            clazz.setSrch_name(srch_name);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
