package com.sds.secframework.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.String;
import org.junit.Test;
import javax.mail.MessagingException;
import javax.mail.internet.MimeUtility;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class ConverterTest {

    @Test(timeout = 20000)
    public void testReplaceWithStringOriginalAndStringOldstrAndStringNewstrShouldNotThrowException() {
        Converter clazz = new Converter();
        String original = "original";
        String oldstr = "oldstr";
        String newstr = "newstr";
        boolean isNotException = true;
        try {
            clazz.replace(original, oldstr, newstr);
        } catch (Exception __e) {
            isNotException = false;
        }
        assert isNotException;
    }

}
