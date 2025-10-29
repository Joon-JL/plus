package com.sds.secframework.common.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceTest {

    @Mock
    private MessageSource messageSource;

    private MessageService messageService;

    @Before
    public void setUp() {
        messageService = new MessageService();
        messageService.setMessageSource(messageSource);
    }

    @Test
    public void testGetMessage_CodeOnly() {
        when(messageSource.getMessage(anyString(), any(), any(Locale.class))).thenReturn("Test Message");

        String result = messageService.getMessage("test.code");

        assertEquals("Test Message", result);
        verify(messageSource).getMessage(eq("test.code"), isNull(), any(Locale.class));
    }

    @Test
    public void testGetMessage_CodeAndArgs() {
        when(messageSource.getMessage(anyString(), any(), any(Locale.class))).thenReturn("Test Message");

        String result = messageService.getMessage("test.code", new Object[]{"arg1"});

        assertEquals("Test Message", result);
        verify(messageSource).getMessage(eq("test.code"), any(), any(Locale.class));
    }

    @Test
    public void testGetMessage_AllParameters() {
        when(messageSource.getMessage(anyString(), any(), anyString(), any(Locale.class))).thenReturn("Test Message");

        String result = messageService.getMessage("test.code", new Object[]{"arg1"}, "Default", Locale.KOREA);

        assertEquals("Test Message", result);
        verify(messageSource).getMessage(eq("test.code"), any(), eq("Default"), any(Locale.class));
    }

    @Test
    public void testGetMessage_CodeArgsLocale() {
        when(messageSource.getMessage(anyString(), any(), any(Locale.class))).thenReturn("Test Message");

        String result = messageService.getMessage("test.code", new Object[]{"arg1"}, Locale.KOREA);

        assertEquals("Test Message", result);
        verify(messageSource).getMessage(eq("test.code"), any(), any(Locale.class));
    }
}
