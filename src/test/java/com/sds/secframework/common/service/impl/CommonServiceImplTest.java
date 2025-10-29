package com.sds.secframework.common.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.apache.commons.logging.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.context.MessageSource;

import com.sds.secframework.common.dao.CommonDAO;
import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.file.service.AttachFileService;
import com.sds.secframework.util.service.ComUtilService;
import com.sec.common.clmsfile.service.ClmsFileService;
import org.springframework.transaction.PlatformTransactionManager;

@RunWith(MockitoJUnitRunner.class)
public class CommonServiceImplTest {

    @Mock
    private CommonDAO commonDAO;

    @Mock
    private PropertyService propertyService;

    @Mock
    private MessageSource messageSource;

    @Mock
    private AttachFileService attachFileService;

    @Mock
    private ClmsFileService clmsFileService;

    @Mock
    private ComUtilService comUtilService;

    @Mock
    private PlatformTransactionManager transactionManager;

    private CommonServiceImpl service;

    @Before
    public void setUp() {
        service = new CommonServiceImpl();
        service.setCommonDAO(commonDAO);
        service.setPropertyService(propertyService);
        service.setMessageSource(messageSource);
        service.setAttachFileService(attachFileService);
        service.setClmsFileService(clmsFileService);
        service.setComUtilService(comUtilService);
        service.setTransactionManager(transactionManager);
    }

    @Test
    public void testSetters() throws NoSuchFieldException, IllegalAccessException {
        assertNotNull(service);
        assertEquals(commonDAO, service.getClass().getDeclaredField("commonDAO").get(service));
        assertEquals(propertyService, service.getClass().getDeclaredField("propertyService").get(service));
        assertEquals(messageSource, service.getClass().getDeclaredField("messageSource").get(service));
        assertEquals(attachFileService, service.getClass().getDeclaredField("attachFileService").get(service));
        assertEquals(clmsFileService, service.getClass().getDeclaredField("clmsFileService").get(service));
        assertEquals(comUtilService, service.getClass().getDeclaredField("comUtilService").get(service));
        assertEquals(transactionManager, service.getClass().getDeclaredField("transactionManager").get(service));
    }

    @Test
    public void testGetLogger() {
        Log logger = service.getLogger();
        assertNotNull(logger);
    }

    @Test
    public void testGetMessageSource() {
        MessageSource source = service.getMessageSource();
        assertEquals(messageSource, source);
    }
}
