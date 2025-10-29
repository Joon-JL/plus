package com.sds.secframework.file.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class AttachFileVOTest {

    @Test(timeout = 20000)
    public void testGetSys_cdShouldNotThrowException() {
        AttachFileVO clazz = new AttachFileVO();
        boolean isNotException = true;
        try {
            clazz.getSys_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSys_cdWithStringSysCdShouldNotThrowException() {
        AttachFileVO clazz = new AttachFileVO();
        String sys_cd = "sys_cd";
        boolean isNotException = true;
        try {
            clazz.setSys_cd(sys_cd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_ref_noShouldNotThrowException() {
        AttachFileVO clazz = new AttachFileVO();
        boolean isNotException = true;
        try {
            clazz.getFile_ref_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFile_ref_noWithStringFileRefNoShouldNotThrowException() {
        AttachFileVO clazz = new AttachFileVO();
        String file_ref_no = "file_ref_no";
        boolean isNotException = true;
        try {
            clazz.setFile_ref_no(file_ref_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSeq_noShouldNotThrowException() {
        AttachFileVO clazz = new AttachFileVO();
        boolean isNotException = true;
        try {
            clazz.getSeq_no();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSeq_noWithStringSeqNoShouldNotThrowException() {
        AttachFileVO clazz = new AttachFileVO();
        String seq_no = "seq_no";
        boolean isNotException = true;
        try {
            clazz.setSeq_no(seq_no);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_pathShouldNotThrowException() {
        AttachFileVO clazz = new AttachFileVO();
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
        AttachFileVO clazz = new AttachFileVO();
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
        AttachFileVO clazz = new AttachFileVO();
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
        AttachFileVO clazz = new AttachFileVO();
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
    public void testGetFile_sizeShouldNotThrowException() {
        AttachFileVO clazz = new AttachFileVO();
        boolean isNotException = true;
        try {
            clazz.getFile_size();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFile_sizeWithStringFileSizeShouldNotThrowException() {
        AttachFileVO clazz = new AttachFileVO();
        String file_size = "file_size";
        boolean isNotException = true;
        try {
            clazz.setFile_size(file_size);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_infoShouldNotThrowException() {
        AttachFileVO clazz = new AttachFileVO();
        boolean isNotException = true;
        try {
            clazz.getFile_info();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFile_infoWithStringFileInfoShouldNotThrowException() {
        AttachFileVO clazz = new AttachFileVO();
        String file_info = "file_info";
        boolean isNotException = true;
        try {
            clazz.setFile_info(file_info);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMenu_idShouldNotThrowException() {
        AttachFileVO clazz = new AttachFileVO();
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
        AttachFileVO clazz = new AttachFileVO();
        String menu_id = "menu_id";
        boolean isNotException = true;
        try {
            clazz.setMenu_id(menu_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
