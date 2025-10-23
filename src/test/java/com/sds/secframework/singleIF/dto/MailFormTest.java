package com.sds.secframework.singleIF.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class MailFormTest {

    @Test(timeout = 20000)
    public void testGetSender_single_idShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getSender_single_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSender_single_idWithStringSenderSingleIdShouldNotThrowException() {
        MailForm clazz = new MailForm();
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
    public void testGetModule_idShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getModule_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetModule_idWithStringModuleIdShouldNotThrowException() {
        MailForm clazz = new MailForm();
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
    public void testGetMis_idShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getMis_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMis_idWithStringMisIdShouldNotThrowException() {
        MailForm clazz = new MailForm();
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
    public void testGetMsg_keyShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getMsg_key();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMsg_keyWithStringMsgKeyShouldNotThrowException() {
        MailForm clazz = new MailForm();
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
    public void testGetSubjectShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getSubject();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSubjectWithStringSubjectShouldNotThrowException() {
        MailForm clazz = new MailForm();
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
    public void testGetBodyShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getBody();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBodyWithStringBodyShouldNotThrowException() {
        MailForm clazz = new MailForm();
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
    public void testGetMsg_typeShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getMsg_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMsg_typeWithStringMsgTypeShouldNotThrowException() {
        MailForm clazz = new MailForm();
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
    public void testGetBhtml_content_checkShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getBhtml_content_check();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBhtml_content_checkWithStringBhtmlContentCheckShouldNotThrowException() {
        MailForm clazz = new MailForm();
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
    public void testGetIfile_countShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getIfile_count();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetIfile_countWithStringIfileCountShouldNotThrowException() {
        MailForm clazz = new MailForm();
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
    public void testGetTime_zoneShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getTime_zone();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetTime_zoneWithStringTimeZoneShouldNotThrowException() {
        MailForm clazz = new MailForm();
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
    public void testGetIs_dstShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getIs_dst();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetIs_dstWithStringIsDstShouldNotThrowException() {
        MailForm clazz = new MailForm();
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
    public void testGetSender_mail_addrShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getSender_mail_addr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSender_mail_addrWithStringSenderMailAddrShouldNotThrowException() {
        MailForm clazz = new MailForm();
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
    public void testGetStatusShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getStatus();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetStatusWithStringStatusShouldNotThrowException() {
        MailForm clazz = new MailForm();
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
    public void testGetCreate_dateShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getCreate_date();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetCreate_dateWithStringCreateDateShouldNotThrowException() {
        MailForm clazz = new MailForm();
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
    public void testGetLocaleShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getLocale();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLocaleWithStringLocaleShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String locale = "locale";
        boolean isNotException = true;
        try {
            clazz.setLocale(locale);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetEncodingShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getEncoding();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetEncodingWithStringEncodingShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String encoding = "encoding";
        boolean isNotException = true;
        try {
            clazz.setEncoding(encoding);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPasswordShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getPassword();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPasswordWithStringPasswordShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String password = "password";
        boolean isNotException = true;
        try {
            clazz.setPassword(password);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetIseq_idsShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getIseq_ids();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetIseq_idsWithStringArrayIseqIdsShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String[] iseq_ids = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setIseq_ids(iseq_ids);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRec_typesShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getRec_types();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRec_typesWithStringArrayRecTypesShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String[] rec_types = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setRec_types(rec_types);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRec_addrsShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getRec_addrs();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRec_addrsWithStringArrayRecAddrsShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String[] rec_addrs = mock(String[].class);
        boolean isNotException = true;
        try {
            clazz.setRec_addrs(rec_addrs);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFileInfosShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getFileInfos();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFileInfosWithStringFileInfosShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String fileInfos = "fileInfos";
        boolean isNotException = true;
        try {
            clazz.setFileInfos(fileInfos);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetIseq_idShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getIseq_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetIseq_idWithStringIseqIdShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String iseq_id = "iseq_id";
        boolean isNotException = true;
        try {
            clazz.setIseq_id(iseq_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRec_typeShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getRec_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRec_typeWithStringRecTypeShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String rec_type = "rec_type";
        boolean isNotException = true;
        try {
            clazz.setRec_type(rec_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRec_addrShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getRec_addr();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRec_addrWithStringRecAddrShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String rec_addr = "rec_addr";
        boolean isNotException = true;
        try {
            clazz.setRec_addr(rec_addr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetIfseq_idShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getIfseq_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetIfseq_idWithStringIfseqIdShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String ifseq_id = "ifseq_id";
        boolean isNotException = true;
        try {
            clazz.setIfseq_id(ifseq_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_nameShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getFile_name();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFile_nameWithStringFileNameShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String file_name = "file_name";
        boolean isNotException = true;
        try {
            clazz.setFile_name(file_name);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetLocal_pathShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getLocal_path();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetLocal_pathWithStringLocalPathShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String local_path = "local_path";
        boolean isNotException = true;
        try {
            clazz.setLocal_path(local_path);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBody_mimeShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getBody_mime();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBody_mimeWithStringBodyMimeShouldNotThrowException() {
        MailForm clazz = new MailForm();
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
    public void testGetDrm_typeShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getDrm_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDrm_typeWithStringDrmTypeShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String drm_type = "drm_type";
        boolean isNotException = true;
        try {
            clazz.setDrm_type(drm_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDrm_can_printShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getDrm_can_print();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDrm_can_printWithStringDrmCanPrintShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String drm_can_print = "drm_can_print";
        boolean isNotException = true;
        try {
            clazz.setDrm_can_print(drm_can_print);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDrm_can_saveShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getDrm_can_save();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDrm_can_saveWithStringDrmCanSaveShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String drm_can_save = "drm_can_save";
        boolean isNotException = true;
        try {
            clazz.setDrm_can_save(drm_can_save);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDrm_use_countShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getDrm_use_count();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDrm_use_countWithStringDrmUseCountShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String drm_use_count = "drm_use_count";
        boolean isNotException = true;
        try {
            clazz.setDrm_use_count(drm_use_count);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDrm_pc_countShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getDrm_pc_count();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDrm_pc_countWithStringDrmPcCountShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String drm_pc_count = "drm_pc_count";
        boolean isNotException = true;
        try {
            clazz.setDrm_pc_count(drm_pc_count);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDrm_valid_daysShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getDrm_valid_days();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDrm_valid_daysWithStringDrmValidDaysShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String drm_valid_days = "drm_valid_days";
        boolean isNotException = true;
        try {
            clazz.setDrm_valid_days(drm_valid_days);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDrm_confirm_mail4intShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getDrm_confirm_mail4int();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDrm_confirm_mail4intWithStringDrmConfirmMail4intShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String drm_confirm_mail4int = "drm_confirm_mail4int";
        boolean isNotException = true;
        try {
            clazz.setDrm_confirm_mail4int(drm_confirm_mail4int);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDrm_confirm_mail4extShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getDrm_confirm_mail4ext();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDrm_confirm_mail4extWithStringDrmConfirmMail4extShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String drm_confirm_mail4ext = "drm_confirm_mail4ext";
        boolean isNotException = true;
        try {
            clazz.setDrm_confirm_mail4ext(drm_confirm_mail4ext);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDrm_can_view_rcptShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getDrm_can_view_rcpt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDrm_can_view_rcptWithStringDrmCanViewRcptShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String drm_can_view_rcpt = "drm_can_view_rcpt";
        boolean isNotException = true;
        try {
            clazz.setDrm_can_view_rcpt(drm_can_view_rcpt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetBody_typeShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getBody_type();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetBody_typeWithStringBodyTypeShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String body_type = "body_type";
        boolean isNotException = true;
        try {
            clazz.setBody_type(body_type);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFault_actor1ShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getFault_actor1();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFault_actor1WithStringFaultActor1ShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String fault_actor1 = "fault_actor1";
        boolean isNotException = true;
        try {
            clazz.setFault_actor1(fault_actor1);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFault_code1ShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getFault_code1();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFault_code1WithStringFaultCode1ShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String fault_code1 = "fault_code1";
        boolean isNotException = true;
        try {
            clazz.setFault_code1(fault_code1);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFault_string1ShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getFault_string1();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFault_string1WithStringFaultString1ShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String fault_string1 = "fault_string1";
        boolean isNotException = true;
        try {
            clazz.setFault_string1(fault_string1);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFault_messageShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getFault_message();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFault_messageWithStringFaultMessageShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String fault_message = "fault_message";
        boolean isNotException = true;
        try {
            clazz.setFault_message(fault_message);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSequenceShouldNotThrowException() {
        MailForm clazz = new MailForm();
        boolean isNotException = true;
        try {
            clazz.getSequence();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSequenceWithStringSequenceShouldNotThrowException() {
        MailForm clazz = new MailForm();
        String sequence = "sequence";
        boolean isNotException = true;
        try {
            clazz.setSequence(sequence);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
