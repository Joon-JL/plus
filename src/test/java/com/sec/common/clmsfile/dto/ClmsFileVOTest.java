package com.sec.common.clmsfile.dto;

import java.lang.String;
import org.junit.Test;
import com.sds.secframework.common.dto.CommonVO;
import static org.mockito.Mockito.mock;

public class ClmsFileVOTest {

    @Test(timeout = 20000)
    public void testGetOcr_work_targetShouldNotThrowException() {
        ClmsFileVO clazz = new ClmsFileVO();
        boolean isNotException = true;
        try {
            clazz.getOcr_work_target();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOcr_work_targetWithStringOcrWorkTargetShouldNotThrowException() {
        ClmsFileVO clazz = new ClmsFileVO();
        String ocr_work_target = "ocr_work_target";
        boolean isNotException = true;
        try {
            clazz.setOcr_work_target(ocr_work_target);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetOcr_work_langShouldNotThrowException() {
        ClmsFileVO clazz = new ClmsFileVO();
        boolean isNotException = true;
        try {
            clazz.getOcr_work_lang();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetOcr_work_langWithStringOcrWorkLangShouldNotThrowException() {
        ClmsFileVO clazz = new ClmsFileVO();
        String ocr_work_lang = "ocr_work_lang";
        boolean isNotException = true;
        try {
            clazz.setOcr_work_lang(ocr_work_lang);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFile_idWithStringFileIdShouldNotThrowException() {
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
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
        ClmsFileVO clazz = new ClmsFileVO();
        String preAllowFileList = "preAllowFileList";
        boolean isNotException = true;
        try {
            clazz.setPreAllowFileList(preAllowFileList);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFinalVersionShouldNotThrowException() {
        ClmsFileVO clazz = new ClmsFileVO();
        boolean isNotException = true;
        try {
            clazz.getFinalVersion();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFinalVersionWithStringFinalVersionShouldNotThrowException() {
        ClmsFileVO clazz = new ClmsFileVO();
        String finalVersion = "finalVersion";
        boolean isNotException = true;
        try {
            clazz.setFinalVersion(finalVersion);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFinalAddShouldNotThrowException() {
        ClmsFileVO clazz = new ClmsFileVO();
        boolean isNotException = true;
        try {
            clazz.getFinalAdd();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFinalAddWithStringFinalAddShouldNotThrowException() {
        ClmsFileVO clazz = new ClmsFileVO();
        String finalAdd = "finalAdd";
        boolean isNotException = true;
        try {
            clazz.setFinalAdd(finalAdd);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetSize_gbnShouldNotThrowException() {
        ClmsFileVO clazz = new ClmsFileVO();
        boolean isNotException = true;
        try {
            clazz.getSize_gbn();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetSize_gbnWithStringSizeGbnShouldNotThrowException() {
        ClmsFileVO clazz = new ClmsFileVO();
        String size_gbn = "size_gbn";
        boolean isNotException = true;
        try {
            clazz.setSize_gbn(size_gbn);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
