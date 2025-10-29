package com.sec.common.clmsfile.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class ClmsFileFormTest {

    @Test(timeout = 20000)
    public void testSetFile_idWithStringFileIdShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        String file_id = "file_id";
        boolean isNotException = true;
        try {
            clazz.setFile_id(file_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFileVersionShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        boolean isNotException = true;
        try {
            clazz.getFileVersion();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFileVersionWithStringFileVersionShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        String fileVersion = "fileVersion";
        boolean isNotException = true;
        try {
            clazz.setFileVersion(fileVersion);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetNewFileNmShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        boolean isNotException = true;
        try {
            clazz.getNewFileNm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetNewFileNmWithStringNewFileNmShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        String newFileNm = "newFileNm";
        boolean isNotException = true;
        try {
            clazz.setNewFileNm(newFileNm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetMultiYnShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        boolean isNotException = true;
        try {
            clazz.getMultiYn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetMultiYnWithStringMultiYnShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        String multiYn = "multiYn";
        boolean isNotException = true;
        try {
            clazz.setMultiYn(multiYn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFileFrameNameShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
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
        ClmsFileForm clazz = new ClmsFileForm();
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
    public void testGetFileInfoNameShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
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
        ClmsFileForm clazz = new ClmsFileForm();
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
    public void testGetFile_del_modeShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
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
        ClmsFileForm clazz = new ClmsFileForm();
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
    public void testGetFile_gbnShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
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
        ClmsFileForm clazz = new ClmsFileForm();
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
    public void testGetView_gbnShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
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
        ClmsFileForm clazz = new ClmsFileForm();
        String view_gbn = "view_gbn";
        boolean isNotException = true;
        try {
            clazz.setView_gbn(view_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFile_pathWithStringFilePathShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
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
    public void testSetOrg_file_nmWithStringOrgFileNmShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        String org_file_nm = "org_file_nm";
        boolean isNotException = true;
        try {
            clazz.setOrg_file_nm(org_file_nm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFile_srtWithIntFileSrtShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        int file_srt = 0;
        boolean isNotException = true;
        try {
            clazz.setFile_srt(file_srt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFile_szWithIntFileSzShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        int file_sz = 0;
        boolean isNotException = true;
        try {
            clazz.setFile_sz(file_sz);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSys_cdWithStringSysCdShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
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
    public void testSetFile_bigclsfcnWithStringFileBigclsfcnShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        String file_bigclsfcn = "file_bigclsfcn";
        boolean isNotException = true;
        try {
            clazz.setFile_bigclsfcn(file_bigclsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFile_midclsfcnWithStringFileMidclsfcnShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        String file_midclsfcn = "file_midclsfcn";
        boolean isNotException = true;
        try {
            clazz.setFile_midclsfcn(file_midclsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFile_smlclsfcnWithStringFileSmlclsfcnShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        String file_smlclsfcn = "file_smlclsfcn";
        boolean isNotException = true;
        try {
            clazz.setFile_smlclsfcn(file_smlclsfcn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetRef_keyWithStringRefKeyShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        String ref_key = "ref_key";
        boolean isNotException = true;
        try {
            clazz.setRef_key(ref_key);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFile_infoWithStringFileInfoShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
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
    public void testSetReg_dtWithStringRegDtShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
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
        ClmsFileForm clazz = new ClmsFileForm();
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
    public void testSetDel_ynWithStringDelYnShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        String del_yn = "del_yn";
        boolean isNotException = true;
        try {
            clazz.setDel_yn(del_yn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_dtWithStringDelDtShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        String del_dt = "del_dt";
        boolean isNotException = true;
        try {
            clazz.setDel_dt(del_dt);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetDel_idWithStringDelIdShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        String del_id = "del_id";
        boolean isNotException = true;
        try {
            clazz.setDel_id(del_id);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_idShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        boolean isNotException = true;
        try {
            clazz.getFile_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_pathShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        boolean isNotException = true;
        try {
            clazz.getFile_path();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOrg_file_nmShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        boolean isNotException = true;
        try {
            clazz.getOrg_file_nm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_srtShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        boolean isNotException = true;
        try {
            clazz.getFile_srt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_szShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        boolean isNotException = true;
        try {
            clazz.getFile_sz();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSys_cdShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        boolean isNotException = true;
        try {
            clazz.getSys_cd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_bigclsfcnShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        boolean isNotException = true;
        try {
            clazz.getFile_bigclsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_midclsfcnShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        boolean isNotException = true;
        try {
            clazz.getFile_midclsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_smlclsfcnShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        boolean isNotException = true;
        try {
            clazz.getFile_smlclsfcn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetRef_keyShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        boolean isNotException = true;
        try {
            clazz.getRef_key();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFile_infoShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        boolean isNotException = true;
        try {
            clazz.getFile_info();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetReg_dtShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
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
        ClmsFileForm clazz = new ClmsFileForm();
        boolean isNotException = true;
        try {
            clazz.getReg_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_ynShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        boolean isNotException = true;
        try {
            clazz.getDel_yn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_dtShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        boolean isNotException = true;
        try {
            clazz.getDel_dt();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetDel_idShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        boolean isNotException = true;
        try {
            clazz.getDel_id();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetPreAllowFileListShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        boolean isNotException = true;
        try {
            clazz.getPreAllowFileList();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetPreAllowFileListWithStringPreAllowFileListShouldNotThrowException() {
        ClmsFileForm clazz = new ClmsFileForm();
        String preAllowFileList = "preAllowFileList";
        boolean isNotException = true;
        try {
            clazz.setPreAllowFileList(preAllowFileList);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
