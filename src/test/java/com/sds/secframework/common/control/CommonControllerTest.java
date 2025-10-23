package com.sds.secframework.common.control;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sds.secframework.common.service.PropertyService;
import com.sds.secframework.common.util.ObjectCopyUtil;

@RunWith(MockitoJUnitRunner.class)
public class CommonControllerTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private PropertyService propertyService;

    private CommonController controller;

    @Before
    public void setUp() {
        controller = new CommonController();
        controller.setPropertyService(propertyService);
        when(request.getSession(false)).thenReturn(session);
        when(propertyService.getProperty("secfw.defaultLocale")).thenReturn("ko_KR");
    }

    @Test
    public void testSetCommonInfo_Success() throws Exception {
        // Given
        when(session.getAttribute("secfw.session.sys_cd")).thenReturn("SYS001");
        when(session.getAttribute("secfw.session.user_id")).thenReturn("testUser");
        when(session.getAttribute("secfw.session.user_nm")).thenReturn("Test User");
        when(session.getAttribute("secfw.session.dept_nm")).thenReturn("IT Dept");
        when(session.getAttribute("secfw.session.grade_nm")).thenReturn("Manager");

        Object testVO = new Object();

        // When
        controller.setCommonInfo(testVO, request);

        // Then
        verify(propertyService).getProperty("secfw.defaultLocale");
    }

    @Test
    public void testSetCommonInfo_NoSession() throws Exception {
        // Given
        when(request.getSession(false)).thenReturn(null);
        Object testVO = new Object();

        // When
        controller.setCommonInfo(testVO, request);

        // Then
        // Should not throw any exception
        assertTrue(true);
    }
}
