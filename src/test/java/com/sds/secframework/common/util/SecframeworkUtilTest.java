package com.sds.secframework.common.util;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SecframeworkUtilTest {

    @Before
    public void setUp() {
        // Nothing to set up
    }

    @Test
    public void testGetQueryString_NullParams() {
        String sql = "SELECT * FROM table WHERE col1 = ? AND col2 = ?";
        String result = SecframeworkUtil.getQueryString(sql, null);
        assertEquals(sql, result);
    }

    @Test
    public void testGetQueryString_EmptyParams() {
        String sql = "SELECT * FROM table WHERE col1 = ? AND col2 = ?";
        List params = new ArrayList();
        String result = SecframeworkUtil.getQueryString(sql, params);
        assertEquals(sql, result);
    }

    @Test
    public void testGetQueryString_StringParams() {
        String sql = "SELECT * FROM table WHERE col1 = ? AND col2 = ?";
        List params = new ArrayList();
        params.add("value1");
        params.add("value2");
        String result = SecframeworkUtil.getQueryString(sql, params);
        assertNotNull(result);
    }

    @Test
    public void testGetQueryString_DateParams() {
        String sql = "SELECT * FROM table WHERE col1 = ? AND col2 = ?";
        List params = new ArrayList();
        params.add(new Date(System.currentTimeMillis()));
        params.add("value2");
        String result = SecframeworkUtil.getQueryString(sql, params);
        assertNotNull(result);
    }

    @Test
    public void testGetQueryString_NullParam() {
        String sql = "SELECT * FROM table WHERE col1 = ? AND col2 = ?";
        List params = new ArrayList();
        params.add(null);
        params.add("value2");
        String result = SecframeworkUtil.getQueryString(sql, params);
        assertNotNull(result);
    }

    @Test
    public void testGetQueryString_WithColon() {
        String sql = "SELECT * FROM table WHERE col1 = :param1 AND col2 = :param2";
        List params = new ArrayList();
        params.add("value1");
        params.add("value2");
        String result = SecframeworkUtil.getQueryString(sql, params);
        assertNotNull(result);
    }

    @Test
    public void testGetQueryString_WithSingleQuote() {
        String sql = "SELECT * FROM table WHERE col1 = 'test' AND col2 = ?";
        List params = new ArrayList();
        params.add("value2");
        String result = SecframeworkUtil.getQueryString(sql, params);
        assertNotNull(result);
    }

    @Test
    public void testGetQueryString_NoPlaceholders() {
        String sql = "SELECT * FROM table WHERE col1 = 'test'";
        List params = new ArrayList();
        params.add("value1");
        String result = SecframeworkUtil.getQueryString(sql, params);
        assertNotNull(result);
    }
}
