package com.sds.secframework.singleIF.dto;

import java.lang.String;
import org.junit.Test;
import static org.mockito.Mockito.mock;

public class ApprovalAttachmentVOTest {

    @Test(timeout = 20000)
    public void testGetModule_idShouldNotThrowException() {
        ApprovalAttachmentVO clazz = new ApprovalAttachmentVO();
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
        ApprovalAttachmentVO clazz = new ApprovalAttachmentVO();
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
        ApprovalAttachmentVO clazz = new ApprovalAttachmentVO();
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
        ApprovalAttachmentVO clazz = new ApprovalAttachmentVO();
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
        ApprovalAttachmentVO clazz = new ApprovalAttachmentVO();
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
        ApprovalAttachmentVO clazz = new ApprovalAttachmentVO();
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
        ApprovalAttachmentVO clazz = new ApprovalAttachmentVO();
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
        ApprovalAttachmentVO clazz = new ApprovalAttachmentVO();
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
    public void testGetFile_sizeShouldNotThrowException() {
        ApprovalAttachmentVO clazz = new ApprovalAttachmentVO();
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
        ApprovalAttachmentVO clazz = new ApprovalAttachmentVO();
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
    public void testGetStore_locationShouldNotThrowException() {
        ApprovalAttachmentVO clazz = new ApprovalAttachmentVO();
        boolean isNotException = true;
        try {
            clazz.getStore_location();
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testSetStore_locationWithStringStoreLocationShouldNotThrowException() {
        ApprovalAttachmentVO clazz = new ApprovalAttachmentVO();
        String store_location = "store_location";
        boolean isNotException = true;
        try {
            clazz.setStore_location(store_location);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
