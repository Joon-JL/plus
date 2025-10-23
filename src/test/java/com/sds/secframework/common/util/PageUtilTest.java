package com.sds.secframework.common.util;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;
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
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PageUtilTest {

    private PageUtil pageUtil;

    @Before
    public void setUp() {
        pageUtil = new PageUtil();
    }

    @Test
    public void testGetNextGroup() {
        pageUtil.setTotalRow(100);
        pageUtil.setRowPerPage(10);
        pageUtil.setThisPage(5);
        pageUtil.setPageGroup(10);
        pageUtil.setGroup();

        assertEquals(10, pageUtil.getNextGroup()); // 현재 그룹의 마지막 페이지가 10이므로
    }

    @Test
    public void testGetPrevGroup() {
        pageUtil.setTotalRow(100);
        pageUtil.setRowPerPage(10);
        pageUtil.setThisPage(15);
        pageUtil.setPageGroup(10);
        pageUtil.setGroup();

        assertEquals(10, pageUtil.getPrevGroup()); // 현재 그룹의 마지막 페이지가 10이므로
    }
}
