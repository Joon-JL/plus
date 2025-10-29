package com.sec.common.clmsfile.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class ComFileVOTest {

    @Test(timeout = 20000)
    public void testGetFileInfoNameShouldNotThrowException() {
        ComFileVO clazz = new ComFileVO();
        boolean isNotException = true;
        try {
            clazz.getFileInfoName();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFileInfoNameWithStringFileInfoNameShouldNotThrowException() {
        ComFileVO clazz = new ComFileVO();
        String fileInfoName = "fileInfoName";
        boolean isNotException = true;
        try {
            clazz.setFileInfoName(fileInfoName);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFileFrameNameShouldNotThrowException() {
        ComFileVO clazz = new ComFileVO();
        boolean isNotException = true;
        try {
            clazz.getFileFrameName();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFileFrameNameWithStringFileFrameNameShouldNotThrowException() {
        ComFileVO clazz = new ComFileVO();
        String fileFrameName = "fileFrameName";
        boolean isNotException = true;
        try {
            clazz.setFileFrameName(fileFrameName);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetModule_idShouldNotThrowException() {
        ComFileVO clazz = new ComFileVO();
        boolean isNotException = true;
        try {
            clazz.getModule_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_gbnShouldNotThrowException() {
        ComFileVO clazz = new ComFileVO();
        boolean isNotException = true;
        try {
            clazz.getFile_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFile_gbnWithStringFileGbnShouldNotThrowException() {
        ComFileVO clazz = new ComFileVO();
        String file_gbn = "file_gbn";
        boolean isNotException = true;
        try {
            clazz.setFile_gbn(file_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_del_modeShouldNotThrowException() {
        ComFileVO clazz = new ComFileVO();
        boolean isNotException = true;
        try {
            clazz.getFile_del_mode();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFile_del_modeWithStringFileDelModeShouldNotThrowException() {
        ComFileVO clazz = new ComFileVO();
        String file_del_mode = "file_del_mode";
        boolean isNotException = true;
        try {
            clazz.setFile_del_mode(file_del_mode);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSys_gbnShouldNotThrowException() {
        ComFileVO clazz = new ComFileVO();
        boolean isNotException = true;
        try {
            clazz.getSys_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSys_gbnWithStringSysGbnShouldNotThrowException() {
        ComFileVO clazz = new ComFileVO();
        String sys_gbn = "sys_gbn";
        boolean isNotException = true;
        try {
            clazz.setSys_gbn(sys_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetModule_idWithStringModuleIdShouldNotThrowException() {
        ComFileVO clazz = new ComFileVO();
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
        ComFileVO clazz = new ComFileVO();
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
        ComFileVO clazz = new ComFileVO();
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
    public void testGetSequenceShouldNotThrowException() {
        ComFileVO clazz = new ComFileVO();
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
        ComFileVO clazz = new ComFileVO();
        String sequence = "sequence";
        boolean isNotException = true;
        try {
            clazz.setSequence(sequence);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_nameShouldNotThrowException() {
        ComFileVO clazz = new ComFileVO();
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
        ComFileVO clazz = new ComFileVO();
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
    public void testGetFile_pathShouldNotThrowException() {
        ComFileVO clazz = new ComFileVO();
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
        ComFileVO clazz = new ComFileVO();
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
    public void testGetFile_sizeShouldNotThrowException() {
        ComFileVO clazz = new ComFileVO();
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
        ComFileVO clazz = new ComFileVO();
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
    public void testGetView_gbnShouldNotThrowException() {
        ComFileVO clazz = new ComFileVO();
        boolean isNotException = true;
        try {
            clazz.getView_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetView_gbnWithStringViewGbnShouldNotThrowException() {
        ComFileVO clazz = new ComFileVO();
        String view_gbn = "view_gbn";
        boolean isNotException = true;
        try {
            clazz.setView_gbn(view_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
