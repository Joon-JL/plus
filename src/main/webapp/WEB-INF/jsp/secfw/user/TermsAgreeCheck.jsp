<!DOCTYPE>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%

	String CntrtGbn = StringUtil.bvl((String)request.getParameter("cntrt_gbn"), "");
	String SysNm = StringUtil.bvl((String)request.getParameter("sys_nm"), "");
	String KeyId = StringUtil.bvl((String)request.getParameter("key_id"), "");

	String methodName = "";
	if("".equals(CntrtGbn)){
		methodName = "goIntroAfterTermsAgreeCheck";
	}else{
		methodName = "goLinkAfterTermsAgreeCheck";
	}
%>
 
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="secfw.page.field.user.samsungClms"/></title>
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet"/>  
<link href="<%=CSS%>/popup.css"   type="text/css" rel="stylesheet"/>  
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="/script/clms/common.js" type="text/javascript"></script>
<script language="javascript" src="/script/clms/jquery-1.6.1.min.js" type="text/javascript"></script>
<script language="javascript" src="/script/clms/new_style.js" type="text/javascript"></script>
<script>
function goIntro(){
	var frm = document.frm;
	
	if(frm.chk1.checked == false){
		alert("<spring:message code='secfw.page.field.user.chkAgrBox'/>");
		return;
	}
	
	if(frm.chk2.checked == false){
		alert("<spring:message code='secfw.page.field.user.PlzPrivacyPolicy'/>");
		return;
	}
	
	frm.action = "<c:url value='/secfw/ssoCheck.do' />";
	frm.method.value = "<%=methodName%>";
	frm.submit();

}
function cencel(){
	alert("<spring:message code='secfw.page.field.user.mustDoAgrClms'/>");
}
</script>
<style>
	body {background-color:#FBFBFA;overflow:hidden}
	.agreeWrap {position:absolute; top:50%; left:50%; width:780px; height:px; margin-top:-350px; margin-left:-390px; }
	.agreeTitle {font-family:Malgun Gothic; letter-spacing:-1px; font-weight:bold; width:780px;  text-align:center;font-size:14px; color:#333; padding-bottom: 10px;}
	.agree {width:780px;height:520px; background:#fff url(../../images/las/ko/mail/agree_bg.gif) no-repeat }
	.agreein {margin-left:30px; width:720px;overflow:hidden; padding-top:10px}
	*:first-child+html .agreein {margin-top:10px;}
	.agreein p{font-family:Malgun Gothic; letter-spacing:-1px; }
	.footer {height:20px;text-indent:90px; font-family:Tahoma; font-size:10px; margin-top:10px; line-height:200%; background:url(../../../images/las/ko/common/logo_samsung.gif) no-repeat 30px 0; }
	.footer .contactus {background:url(../../../images/las/ko/icon/icon_mail.gif) no-repeat 0 2px; color:#5E4B3F; padding-left:25px;  margin-left:7px; line-height:10px!important;}
	.footer .contactus span {color:#B6602C}
	textarea {border:1px solid #AEAEAE;width:98%; color:#555; height:140px}
</style>
</head>
<body>
<form name="frm" id="frm" method="post" autocomplete="off">
	<!-- method -->
	<input type="hidden" name="method">
	<input type="hidden" name="cntrt_gbn" value="<%=CntrtGbn%>">
	<input type="hidden" name="sys_nm" value="<%=SysNm%>">
	<input type="hidden" name="key_id" value="<%=KeyId%>">	
	
<div class="agreeWrap">
	<div class="agreeTitle"><img src="<%=IMAGE%>/icon/ico_lock.png" align="absmiddle" style='margin-left:-24px'/><spring:message code="secfw.page.field.user.chkClmsBox"/></div>
    <div class='agree'>
    	<div class='agreein'>
    	
			
			
			 <!-- title -->
	        <div class="title_basic">
	          <h4> <spring:message code="secfw.page.field.user.slmsUserAgreement"/></h4>
	        </div>
            <!-- // title -->
            
            <div>
               
<textarea name="textarea2" id="textarea2" rows="12">
<spring:message code="secfw.page.msg.agreeChk.article01"/>
<spring:message code="secfw.page.msg.agreeChk.article02"/>
<spring:message code="secfw.page.msg.agreeChk.article03"/>
<spring:message code="secfw.page.msg.agreeChk.article04"/>
<spring:message code="secfw.page.msg.agreeChk.article05"/>
<spring:message code="secfw.page.msg.agreeChk.article06"/>
<spring:message code="secfw.page.msg.agreeChk.article07"/>
<spring:message code="secfw.page.msg.agreeChk.article08"/>
<spring:message code="secfw.page.msg.agreeChk.article09"/>
<spring:message code="secfw.page.msg.agreeChk.article10"/>
<spring:message code="secfw.page.msg.agreeChk.article99"/>
</textarea>
              
	              <p class="mt10 tR">
	                <input type="checkbox" name="chk1" id="checkbox" />
	                <spring:message code="secfw.page.field.user.Iagree"/>
	              </p>
              </div>
              
              <!-- title -->
              <div class="title_basic" >
                <h4><spring:message code="secfw.page.field.user.privacyPolicy"/></h4>
              </div>
              <!-- // title -->
              
              <div>
<textarea name="textarea" id="textarea" rows="12">
<spring:message code="secfw.page.msg.agreeChk.announce01"/>
<spring:message code="secfw.page.msg.agreeChk.announce02"/>
<spring:message code="secfw.page.msg.agreeChk.announce99"/>
</textarea>
             
	              <p class="mt10 tR">
	                 <input type="checkbox" name="chk2" id="checkbox" />
	                 <spring:message code="secfw.page.field.user.AgreePrvPolcy"/>
	              </p>
              </div>	       

			
			
			<!-- button -->
			<div class="tC" style="margin-bottom: 15px;margin-top:15px"> 
				<span class="btn_all_ss"><span class="confirm"></span><a href="javascript:goIntro();"><spring:message code="secfw.page.field.user.confirm"/></a></span> 
				<span class="btn_all_ss"><span class="cancel"></span><a href="javascript:cencel();"><spring:message code="secfw.page.field.user.cancel"/></a></span>
			</div>
			<!-- //button -->



		</div>
	</div>
	
	<div class='footer'>ⓒ SAMSUNG  <span class='contactus'><span>Contact Us :</span> selmsplus@samsung.com</span></div>
</div>
</form>
</body>
</html>