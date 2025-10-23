package com.sds.secframework.file.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class AttachFileFormTest {

    @Test(timeout = 20000)
    public void testGetFile_ref_noShouldNotThrowException() {
        AttachFileForm clazz = new AttachFileForm();
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
        AttachFileForm clazz = new AttachFileForm();
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
        AttachFileForm clazz = new AttachFileForm();
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
        AttachFileForm clazz = new AttachFileForm();
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
        AttachFileForm clazz = new AttachFileForm();
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
        AttachFileForm clazz = new AttachFileForm();
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
        AttachFileForm clazz = new AttachFileForm();
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
        AttachFileForm clazz = new AttachFileForm();
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
        AttachFileForm clazz = new AttachFileForm();
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
        AttachFileForm clazz = new AttachFileForm();
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
        AttachFileForm clazz = new AttachFileForm();
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
        AttachFileForm clazz = new AttachFileForm();
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
        AttachFileForm clazz = new AttachFileForm();
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
        AttachFileForm clazz = new AttachFileForm();
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
