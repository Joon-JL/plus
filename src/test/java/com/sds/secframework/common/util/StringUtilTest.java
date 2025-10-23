package com.sds.secframework.common.util;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class StringUtilTest {

    @Before
    public void setUp() {
        // Nothing to set up
    }

    @Test
    public void testPaddingZero_ShorterString() {
        String result = StringUtil.paddingZero("123", 5);
        assertEquals("00123", result);
    }

    @Test
    public void testPaddingZero_EqualLength() {
        String result = StringUtil.paddingZero("123", 3);
        assertEquals("123", result);
    }

    @Test
    public void testPaddingZero_LongerString() {
        String result = StringUtil.paddingZero("12345", 3);
        assertEquals("12345", result);
    }

    @Test
    public void testInitCap_LowercaseFirst() {
        String result = StringUtil.initCap("hello");
        assertEquals("Hello", result);
    }

    @Test
    public void testInitCap_UppercaseFirst() {
        String result = StringUtil.initCap("Hello");
        assertEquals("Hello", result);
    }

    @Test
    public void testRemoveChar_Char() {
        String result = StringUtil.removeChar("hello", 'l');
        assertEquals("heo", result);
    }

    @Test
    public void testRemoveChar_String() {
        String result = StringUtil.removeChar("hello", "ll");
        assertEquals("heo", result);
    }

    @Test
    public void testStr2int_Null() {
        int result = StringUtil.str2int(null);
        assertEquals(0, result);
    }

    @Test
    public void testStr2int_Valid() {
        int result = StringUtil.str2int("123");
        assertEquals(123, result);
    }

    @Test
    public void testInt2Str() {
        String result = StringUtil.int2Str(123);
        assertEquals("123", result);
    }

    @Test
    public void testNull2space_Null() {
        String result = StringUtil.null2space(null);
        assertEquals("", result);
    }

    @Test
    public void testNull2space_Empty() {
        String result = StringUtil.null2space("");
        assertEquals("", result);
    }

    @Test
    public void testNull2space_Whitespace() {
        String result = StringUtil.null2space("  hello  ");
        assertEquals("hello", result);
    }

    @Test
    public void testNull2str_Null() {
        String result = StringUtil.null2str(null, "default");
        assertEquals("default", result);
    }

    @Test
    public void testNull2str_Empty() {
        String result = StringUtil.null2str("", "default");
        assertEquals("default", result);
    }

    @Test
    public void testNull2str_Valid() {
        String result = StringUtil.null2str("hello", "default");
        assertEquals("hello", result);
    }

    @Test
    public void testIsNull_Null() {
        boolean result = StringUtil.isNull(null);
        assertTrue(result);
    }

    @Test
    public void testIsNull_Empty() {
        boolean result = StringUtil.isNull("");
        assertTrue(result);
    }

    @Test
    public void testIsNull_Whitespace() {
        boolean result = StringUtil.isNull("  ");
        assertTrue(result);
    }

    @Test
    public void testIsNull_Valid() {
        boolean result = StringUtil.isNull("hello");
        assertFalse(result);
    }

    @Test
    public void testSplit() {
        String[] result = StringUtil.split("a,b,c", ",");
        assertEquals(3, result.length);
        assertEquals("a", result[0]);
        assertEquals("b", result[1]);
        assertEquals("c", result[2]);
    }

    @Test
    public void testJoin() {
        String[] array = {"a", "b", "c"};
        String result = StringUtil.join(array, ",");
        assertEquals("a,b,c", result);
    }

    @Test
    public void testReplace() {
        String result = StringUtil.replace("hello world", "world", "test");
        assertEquals("hello test", result);
    }

    @Test
    public void testLeftPad() {
        String result = StringUtil.leftPad("123", '0', 5);
        assertEquals("00123", result);
    }

    @Test
    public void testRightPad() {
        String result = StringUtil.rightPad("123", '0', 5);
        assertEquals("12300", result);
    }

    @Test
    public void testNvl_Object_Null() {
        String result = StringUtil.nvl(null, "default");
        assertEquals("default", result);
    }

    @Test
    public void testNvl_Object_Valid() {
        String result = StringUtil.nvl("hello", "default");
        assertEquals("hello", result);
    }

    @Test
    public void testNvl_String_Null() {
        String result = StringUtil.nvl(null, "default");
        assertEquals("default", result);
    }

    @Test
    public void testNvl_String_Valid() {
        String result = StringUtil.nvl("hello", "default");
        assertEquals("hello", result);
    }

    @Test
    public void testBvl_Object_Null() {
        String result = StringUtil.bvl(null, "default");
        assertEquals("default", result);
    }

    @Test
    public void testBvl_Object_Empty() {
        String result = StringUtil.bvl("", "default");
        assertEquals("default", result);
    }

    @Test
    public void testBvl_Object_Valid() {
        String result = StringUtil.bvl("hello", "default");
        assertEquals("hello", result);
    }

    @Test
    public void testBvl_String_Null() {
        String result = StringUtil.bvl(null, "default");
        assertEquals("default", result);
    }

    @Test
    public void testBvl_String_Empty() {
        String result = StringUtil.bvl("", "default");
        assertEquals("default", result);
    }

    @Test
    public void testBvl_String_Valid() {
        String result = StringUtil.bvl("hello", "default");
        assertEquals("hello", result);
    }

    @Test
    public void testLtrim_Null() {
        String result = StringUtil.ltrim(null);
        assertEquals("", result);
    }
}
