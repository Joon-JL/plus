<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MFA 테스트 인증</title>
    <script src="<c:url value='/js/jquery-1.12.4.min.js'/>"></script>
    <style>
        body { font-family: 'Malgun Gothic', sans-serif; background-color: #f4f6f9; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .mfa-card { background: #fff; padding: 40px; border-radius: 8px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); width: 380px; text-align: center; }
        .mfa-card h2 { margin-top: 0; color: #333; }
        .email-badge { background: #eef2f7; color: #0056b3; padding: 8px 12px; border-radius: 4px; font-weight: bold; margin-bottom: 15px; display: inline-block; font-size: 14px; }
        .notice-box { background: #fff3cd; color: #856404; padding: 10px; border-radius: 4px; font-size: 12px; margin-bottom: 20px; text-align: left; line-height: 1.4; }
        .input-code { width: 100%; height: 45px; font-size: 22px; text-align: center; letter-spacing: 8px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; margin-bottom: 15px; }
        .btn-submit { width: 100%; height: 45px; background-color: #0056b3; color: white; border: none; border-radius: 4px; font-size: 16px; font-weight: bold; cursor: pointer; }
        .btn-submit:hover { background-color: #004085; }
        .btn-resend { background: none; border: none; color: #6c757d; text-decoration: underline; margin-top: 15px; cursor: pointer; font-size: 13px; }
    </style>
</head>
<body>

<div class="mfa-card">
    <h2>2차 인증 (테스트)</h2>
    <div class="email-badge">${sessionScope.PENDING_MFA_EMAIL}</div>

    <div class="notice-box">
        💡 <b>개발 테스트 모드:</b><br/>
        이클립스 콘솔 또는 웹로직 stdout.log에 출력된 <b>6자리 AUTH CODE</b>를 입력하세요.
    </div>

    <form id="mfaForm" onsubmit="return false;">
        <input type="text" id="inputCode" class="input-code" maxlength="6" placeholder="000000" autocomplete="off" />
        <button type="button" class="btn-submit" onclick="fn_verifyCode();">인증 및 로그인</button>
    </form>

    <button type="button" class="btn-resend" onclick="fn_resendCode();">코드 재생성 (콘솔 출력)</button>
</div>

<script type="text/javascript">
    $(document).ready(function() {
        $("#inputCode").focus();

        $("#inputCode").keyup(function(e) {
            if(e.keyCode == 13) fn_verifyCode();
        });
    });

    function fn_verifyCode() {
        var code = $("#inputCode").val().trim();
        if (code.length !== 6) {
            alert("6자리 코드를 입력해주세요.");
            $("#inputCode").focus();
            return;
        }

        $.ajax({
            url: "${pageContext.request.contextPath}/auth/verifyDirectCode.do",
            type: "POST",
            data: { "inputCode": code },
            dataType: "json",
            success: function(res) {
                if (res.status === "SUCCESS") {
                    location.href = res.redirectUrl;
                } else {
                    alert(res.message);
                }
            },
            error: function() {
                alert("통신 중 오류가 발생했습니다.");
            }
        });
    }

    function fn_resendCode() {
        $.ajax({
            url: "${pageContext.request.contextPath}/auth/resendCode.do",
            type: "POST",
            dataType: "json",
            success: function(res) {
                alert(res.message);
                if (res.status === "SUCCESS") {
                    $("#inputCode").val("").focus();
                }
            }
        });
    }
</script>

</body>
</html>