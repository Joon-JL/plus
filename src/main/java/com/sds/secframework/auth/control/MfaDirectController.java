package com.sds.secframework.auth.control;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sds.secframework.auth.service.MfaAuthService;
import com.sds.secframework.common.control.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


public class MfaDirectController extends CommonController {

    private MfaAuthService mfaAuthService;

    public void setMfaAuthService(MfaAuthService mfaAuthService) {
        this.mfaAuthService = mfaAuthService;
    }

    // 약속된 토큰 - 이메일 매핑
    private static final Map TOKEN_USER_MAP = new HashMap();
    static {
        TOKEN_USER_MAP.put("a8f9c2d7e1", "joonlee.uk@partner.samsung.com");
        TOKEN_USER_MAP.put("s9x2m4p8q1", "sung1min.kim@samsung.com");//M230214151447C6E8232
        TOKEN_USER_MAP.put("y2z2g5q3u4", "younghak.lim@partner.samsung.com");//M260122093148C6E7397
        TOKEN_USER_MAP.put("u3k3f6c4r5", "isa.irp.security3@samsung.com");//M240409005328C101848
        TOKEN_USER_MAP.put("m3s3k6o4k9", "sm21.jung@samsung.com");//D060721180203C601516
        TOKEN_USER_MAP.put("i7u4w4h2q8", "yhyuk.kim@samsung.com");//D090227082111C600107
        TOKEN_USER_MAP.put("n1p9k7i8k9", "soohwan1.kim@samsung.com");//D070104084212C604128
        TOKEN_USER_MAP.put("a9z3k6t4w2", "jungah.jee@samsung.com");//D060123133950C600120
        TOKEN_USER_MAP.put("g8f8q9g1p9", "hs73.chang@samsung.com");//M040331020937C604977

    }

    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String method = request.getParameter("method");

        if ("verifyDirectCode".equals(method)) {
            verifyDirectCode(request, response);
            return null;
        } else if ("resendCode".equals(method)) {
            resendCode(request, response);
            return null;
        } else {
            return directLogin(request, response);
        }
    }

    public ModelAndView directLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String token = request.getParameter("token");

        if (token == null || !TOKEN_USER_MAP.containsKey(token)) {
            return new ModelAndView("redirect:/auth/unauthorized.do");
        }

        String targetEmail = (String) TOKEN_USER_MAP.get(token);

        // 6자리 인증코드 생성 및 콘솔 출력
        String mfaCode = mfaAuthService.sendMfaCodeEmail(targetEmail);

        // 세션에 이메일 및 인증코드 저장
        HttpSession session = request.getSession(true);
        session.setAttribute("PENDING_MFA_EMAIL", targetEmail);
        session.setAttribute("PENDING_MFA_CODE", mfaCode);

        return new ModelAndView("/WEB-INF/jsp/secfw/auth/mfaDirect.jsp"); // /WEB-INF/jsp/auth/mfaDirect.jsp
    }

    /**
     * 2단계: 코드 일치 여부 단순 검증 (AJAX POST)
     */
    public void verifyDirectCode(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("==== verifyDirectCode=======");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String inputCode = request.getParameter("inputCode");
        System.out.println("==== inputCode : " +  inputCode);
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("PENDING_MFA_EMAIL") == null) {
            out.print("{\"status\":\"EXPIRED\", \"message\":\"세션이 없습니다. 약속된 URL로 다시 접근해주세요.\"}");
            out.flush();
            return;
        }

        String savedCode = (String) session.getAttribute("PENDING_MFA_CODE");
        String email = (String) session.getAttribute("PENDING_MFA_EMAIL");

        // 코드 검증
        if (inputCode != null && savedCode != null && savedCode.equals(inputCode.trim())) {
            session.removeAttribute("PENDING_MFA_CODE");
            session.removeAttribute("PENDING_MFA_EMAIL");

            session.setAttribute("USER_EMAIL", email);
            session.setAttribute("IS_AUTHENTICATED", Boolean.TRUE);

            String user_id = "";

            switch(email) {
                case "joonlee.uk@partner.samsung.com":
                    user_id = "M250926071750C6E0253";
                    break;
                case "sung1min.kim@samsung.com":
                    user_id = "M230214151447C6E8232";
                    break;
                case "younghak.lim@partner.samsung.com":
                    user_id = "M260122093148C6E7397";
                    break;
                case "isa.irp.security3@samsung.com":
                    user_id = "M240409005328C101848";
                    break;
                case "sm21.jung@samsung.com":
                    user_id = "D060721180203C601516";
                    break;
                case "yhyuk.kim@samsung.com":
                    user_id = "D090227082111C600107";
                    break;
                case "soohwan1.kim@samsung.com":
                    user_id = "D070104084212C604128";
                case "jungah.jee@samsung.com":
                    user_id = "D060123133950C600120";
                    break;
                case "hs73.chang@samsung.com":
                    user_id = "M040331020937C604977";
                    break;
            }


//                TOKEN_USER_MAP.put("a8f9c2d7e1", "joonlee.uk@partner.samsung.com");
//                TOKEN_USER_MAP.put("s9x2m4p8q1", "sung1min.kim@samsung.com");//
//                TOKEN_USER_MAP.put("y2z2g5q3u4",

            String redirectUrl = request.getContextPath() + "/secfw/ssoCheck.do?method=clmsSelLoginPrcs&user_id=" + user_id;
            out.print("{\"status\":\"SUCCESS\", \"redirectUrl\":\"" + redirectUrl + "\"}");

            System.out.println("redirectUrl : " + redirectUrl);


        } else {
            out.print("{\"status\":\"FAIL\", \"message\":\"인증코드가 일치하지 않습니다.\"}");
        }
        out.flush();
    }

    /**
     * 코드 재발생 (AJAX POST) - 콘솔에 새로운 코드 출력
     */
    public void resendCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("==== resendCode=======");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("PENDING_MFA_EMAIL") == null) {
            out.print("{\"status\":\"EXPIRED\", \"message\":\"세션이 없습니다.\"}");
            out.flush();
            return;
        }

        String email = (String) session.getAttribute("PENDING_MFA_EMAIL");
        String newMfaCode = mfaAuthService.sendMfaCodeEmail(email);

        session.setAttribute("PENDING_MFA_CODE", newMfaCode);

        out.print("{\"status\":\"SUCCESS\", \"message\":\"A new MFA code has been sent to your email. \"}");
        out.flush();
    }
}