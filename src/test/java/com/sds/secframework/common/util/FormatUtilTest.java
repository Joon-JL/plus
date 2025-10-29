package com.sds.secframework.common.util;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FormatUtilTest {

    @Before
    public void setUp() {
        // Nothing to set up
    }

    @Test
    public void testFormat_IntWithStringFormat() {
        String result = FormatUtil.format(1234, "###,###,##0.00");
        assertEquals("1,234.00", result);
    }

    @Test
    public void testFormat_LongWithStringFormat() {
        String result = FormatUtil.format(1234567L, "###,###,##0.00");
        assertEquals("1,234,567.00", result);
    }

    @Test
    public void testFormat_DoubleWithStringFormat() {
        String result = FormatUtil.format(1234.56, "###,###,##0.00");
        assertEquals("1,234.56", result);
    }

    @Test
    public void testFormat_DoubleDefault() {
        String result = FormatUtil.format(1234.56);
        assertEquals("1,234.56", result);
    }

    @Test
    public void testFormat_LongDefault() {
        String result = FormatUtil.format(1234567L);
        assertEquals("1,234,567", result);
    }

    @Test
    public void testFormat_IntDefault() {
        String result = FormatUtil.format(1234567);
        assertEquals("1,234,567", result);
    }

    @Test
    public void testFormatInt_BigDecimal() {
        BigDecimal bd = new BigDecimal("123");
        int result = FormatUtil.formatInt(bd);
        assertEquals(123, result);
    }

    @Test
    public void testFormatInt_Integer() {
        Integer i = Integer.valueOf(456);
        int result = FormatUtil.formatInt(i);
        assertEquals(456, result);
    }

    @Test
    public void testFormatInt_Null() {
        int result = FormatUtil.formatInt(null);
        assertEquals(0, result);
    }

    @Test
    public void testFormatNumToString_BigDecimal() {
        BigDecimal bd = new BigDecimal("789");
        String result = FormatUtil.formatNumToString(bd);
        assertEquals("789", result);
    }

    @Test
    public void testFormatNumToString_Integer() {
        Integer i = Integer.valueOf(101);
        String result = FormatUtil.formatNumToString(i);
        assertEquals("101", result);
    }

    @Test
    public void testFormatNumToString_Null() {
        String result = FormatUtil.formatNumToString(null);
        assertEquals("", result);
    }
}
