<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>::: 계약관리시스템오픈 :::</title>
<style type="text/css">
body, div, h1, h2, h3, h4, h5, h6, ul, ol, li, dl, dt, dd, p, form, fieldset, input, table, tr, th, td, a 
{margin:0;padding:0; font-family:'Malgun Gothic','맑은고딕','Dotum','돋움',Tahoma,sans-serif,arial,verdana; font-size:11px; color:#1a1a1a; line-height:18px;}

a {color:#326CC3;  text-decoration: none;}
a:hover { color:#DE4376; text-decoration: underline;} 

.bg {position: absolute;height:600px; width: 500px; background:url(<%=IMAGE%>/notice_bg3.jpg) no-repeat right top;}
.contents {position: relative; width:276px; height:300px; padding-top: 214px; padding-left: 240px; }
.btn {position: relative; width: 480px; padding-bottom: 20px; padding-right: 20px; text-align: right;}

li {list-style:none;}
</style>
<script language="javascript" src="<c:url value='/script/clms/common.js' />"></script>
<script language="javascript">
/**
* 하루동안 창을 열지 않기
*/
function closeWin(){
	var frm = document.frm ;	
	if (frm.closeEvent.checked ) {
		setCookie("openPopup", "no" , 1); // 1일 간 쿠키적용 	
	}
	self.close();	
}
</script>
</head>
<body>
<form name="frm" id="frm" method="post" autocomplete="off">
<!-- 팝업창 사이즈 500 * 600 -->
<div class="bg">
	<div class="contents">
		clm.mobile@samsung.com
	</div>
	<div class="btn"><a href="http://legal2.sec.samsung.net/login.do?f=c" target="_blank"><img src="<%=IMAGE%>/btn_go3.png" border="0"/></a></div>
</div>

</form>
</body>
</html>
 --%>

<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title><spring:message code="common.page.field.OpenPopMobile.noticeOutofOrder"/></title>
<style type="text/css">
body, div, h1, h2, h3, h4, h5, h6, ul, ol, li, dl, dt, dd, p, form, fieldset, input, table, tr, th, td, a 
{margin:0;padding:0; font-family:'Malgun Gothic','맑은고딕','Dotum','돋움',Tahoma,sans-serif,arial,verdana; font-size:12px; color:#1a1a1a; line-height:18px;}
.bg {height:100%; width: 350px; background:url(<%=IMAGE%>/notice_bg_.jpg) repeat-y}
.notice_top {position: relative; width:350px; height:255px; top: 0px; left: 0px; background:url(<%=IMAGE%>/notice_top.jpg) no-repeat}
.notice_mid {margin-left:30px; color:#C7DFFF; font-size:12px; width:300px;height:*; font-weight:bold  }
.notice_mid a {color:#FFEB7E;  text-decoration: underline;}
.notice_mid a:hover { color:#FFD903; text-decoration: underline;} 
.notice_bottom {position:absolute; bottom:0; height:20px; width:350px; background:#031E46; text-align:right;}
.notice_bottom .btn_close {position: relative; top: 5px; right:7px;}
.green {color:#ACE350}
.org {color:#FA9E53}
.yellow {color:#EED73F}
.font-11 {font-size:11px;letter-spacing:-1px; line-height:120%;}

.today {text-align: right; position: relative; width:340px; height:24px; padding-left: 10px; padding-top: 3px; font-family:'Malgun Gothic','맑은고딕','Dotum','돋움',Tahoma,sans-serif,arial,verdana; font-size:12px; color:#333;}
.today input {vertical-align:middle;}
</style>
<script language="javascript" src="<c:url value='/script/clms/common.js' />"></script>
<script language="javascript">
/**
* 하루동안 창을 열지 않기
*/
function closeWin(){
	var frm = document.frm ;	
	if (frm.closeEvent.checked ) {
		setCookie("noticePopupLas", "no" , 1); // 1일 간 쿠키적용 	
	}
	self.close();	
}
</script>
</head>
<body>
<form name="frm" id="frm" method="post" autocomplete="off">
<!-- 팝업창 사이즈 너비 350px * 390px  -->
	<div class="bg">
		
		<div class="notice_top"></div>
		<div class='notice_mid'>
		    <img src="<%=IMAGE%>/notice_ball.png" border="0" align="absmiddle"/><spring:message code="secfw.page.field.bbs.period"/> : 3월  28일 (목) 21:00~22:00<br/><br/>
			<img src="<%=IMAGE%>/notice_ball.png" border="0" align="absmiddle"/><spring:message code="common.page.field.OpenPopMobile.inq2"/> 02-2255-4967/4968<br/><br/>
			 
			<span class='green font-12'><spring:message code="common.page.field.OpenPopMobile.byRenewal"/> 
                    <spring:message code="common.page.field.OpenPopMobile.contactUsInError"/>
           </span>  <br/> <br/>
			
		
		</div>
	</div>

	<div class="today"><spring:message code="common.page.field.OpenPopMobile.closeToday"/> 
    	<input type="checkbox" name="closeEvent" id="closeEvent" onClick="javascript:closeWin();"/>
    </div>
         	
<!-- //팝업창 사이즈 너비 350px * 390px  -->	
</form>
</body>
</html>