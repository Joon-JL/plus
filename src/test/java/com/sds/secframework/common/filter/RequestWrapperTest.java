package com.sds.secframework.common.filter;

import java.util.regex.Pattern;
import java.lang.String;
import com.sds.secframework.common.util.StringUtil;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import static org.junit.Assert.assertNotNull;

public class RequestWrapperTest {

    @Test(timeout = 20000)
    public void testConstructorWithHttpServletRequestServletRequestNotNull() {
        // 서블릿 요청 및 응답 객체 생성
        HttpServletRequest request = new MockHttpServletRequest();

        // RequestWrapper 생성자에 실제 객체 전달
        RequestWrapper obj = new RequestWrapper(request);

        // 객체가 정상적으로 생성되었는지 확인
        assertNotNull(obj);
    }


    @Test(timeout = 20000)
    public void testGetParameterValuesWithStringParameterShouldNotThrowException() {
        // 서블릿 요청 객체 생성 및 파라미터 초기화
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("parameter", new String[]{"value1", "value2"});

        // RequestWrapper 생성자에 실제 객체 전달
        RequestWrapper clazz = new RequestWrapper(request);

        // 파라미터 값 조회 시도
        boolean isNotException = true;
        try {
            clazz.getParameterValues("parameter");
        } catch (Exception __e) {
            isNotException = false;
        }

        // 예외가 발생하지 않았는지 확인
        assert isNotException;
    }



    @Test(timeout = 20000)
    public void testGetParameterWithStringParameterShouldNotThrowException() {
        // 서블릿 요청 객체 생성 및 파라미터 초기화
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("parameter", "value");

        // RequestWrapper 생성자에 실제 객체 전달
        RequestWrapper clazz = new RequestWrapper(request);

        // 파라미터 값 조회 시도
        boolean isNotException = true;
        try {
            clazz.getParameter("parameter");
        } catch (Exception __e) {
            isNotException = false;
        }

        // 예외가 발생하지 않았는지 확인
        assert isNotException;
    }


    @Test(timeout = 20000)
    public void testGetHeaderWithStringNameShouldNotThrowException() {
        // 서블릿 요청 객체 생성 및 헤더 초기화
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("name", "value");

        // RequestWrapper 생성자에 실제 객체 전달
        RequestWrapper clazz = new RequestWrapper(request);

        // 헤더 값 조회 시도
        boolean isNotException = true;
        try {
            clazz.getHeader("name");
        } catch (Exception __e) {
            isNotException = false;
        }

        // 예외가 발생하지 않았는지 확인
        assert isNotException;
    }


}
