package com.sec.common.clmsfile.dto;

import com.sds.secframework.common.dto.CommonForm;
import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class FileFormTest {

    @Test(timeout = 20000)
    public void testGetFileNmShouldNotThrowException() {
        FileForm clazz = new FileForm();
        boolean isNotException = true;
        try {
            clazz.getFileNm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFileTypeShouldNotThrowException() {
        FileForm clazz = new FileForm();
        boolean isNotException = true;
        try {
            clazz.getFileType();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFileTypeWithStringFileTypeShouldNotThrowException() {
        FileForm clazz = new FileForm();
        String fileType = "fileType";
        boolean isNotException = true;
        try {
            clazz.setFileType(fileType);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFileNmWithStringFileNmShouldNotThrowException() {
        FileForm clazz = new FileForm();
        String fileNm = "fileNm";
        boolean isNotException = true;
        try {
            clazz.setFileNm(fileNm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testGetFolderNmShouldNotThrowException() {
        FileForm clazz = new FileForm();
        boolean isNotException = true;
        try {
            clazz.getFolderNm();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetFolderNmWithStringFolderNmShouldNotThrowException() {
        FileForm clazz = new FileForm();
        String folderNm = "folderNm";
        boolean isNotException = true;
        try {
            clazz.setFolderNm(folderNm);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
