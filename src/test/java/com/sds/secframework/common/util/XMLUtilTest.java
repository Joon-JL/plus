package com.sds.secframework.common.util;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.jdom.*;
import org.jdom.output.XMLOutputter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class XMLUtilTest {

    @Mock
    private Document document;

    @Mock
    private Element element;

    @Mock
    private Attribute attribute;

    private XMLUtil xmlUtil;

    @Before
    public void setUp() {
        xmlUtil = new XMLUtil();
    }

    @Test
    public void testConstructor_Default() {
        XMLUtil util = new XMLUtil();
        assertNotNull(util);
    }

    @Test
    public void testConstructor_WithRootName() {
        XMLUtil util = new XMLUtil("root");
        assertNotNull(util);
    }

    @Test
    public void testAddInnerElement() {
        Element parent = new Element("parent");
        Element result = xmlUtil.addInnerElement(parent, "child", "value");
        assertNotNull(result);
        assertEquals("child", result.getName());
        assertEquals("value", result.getText());
    }

    @Test
    public void testAddInnerAttribute() {
        Element element = new Element("test");
        xmlUtil.addInnerAttribute(element, "attr", "value");
        // Verify that method executes without exception
        assertTrue(true);
    }
}
