package com.sds.secframework.common.util;

import java.lang.String;
import org.junit.Test;
import java.util.*;

public class WebUtilTest {

    @Test(timeout = 20000)
    public void testNl2brWithStringSShouldNotThrowException() {
        WebUtil clazz = new WebUtil();
        String s = "s";
        boolean isNotException = true;
        try {
            clazz.nl2br(s);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testBr2nlWithStringStrShouldNotThrowException() {
        WebUtil clazz = new WebUtil();
        String str = "str";
        boolean isNotException = true;
        try {
            clazz.br2nl(str);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testBr2spaceWithStringStrShouldNotThrowException() {
        WebUtil clazz = new WebUtil();
        String str = "str";
        boolean isNotException = true;
        try {
            clazz.br2space(str);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testChkRadioTypeWithStringCode01AndStringCode02ShouldNotThrowException() {
        WebUtil clazz = new WebUtil();
        String code01 = "code01";
        String code02 = "code02";
        boolean isNotException = true;
        try {
            clazz.chkRadioType(code01, code02);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testChkSelectTypeWithStringCode01AndStringCode02ShouldNotThrowException() {
        WebUtil clazz = new WebUtil();
        String code01 = "code01";
        String code02 = "code02";
        boolean isNotException = true;
        try {
            clazz.chkSelectType(code01, code02);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testHtmlToTextWithStringPStrShouldNotThrowException() {
        WebUtil clazz = new WebUtil();
        String p_str = "p_str";
        boolean isNotException = true;
        try {
            clazz.htmlToText(p_str);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

    @Test(timeout = 20000)
    public void testTextToHtmlWithStringSzDetailShouldNotThrowException() {
        WebUtil clazz = new WebUtil();
        String szDetail = "szDetail";
        boolean isNotException = true;
        try {
            clazz.textToHtml(szDetail);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
