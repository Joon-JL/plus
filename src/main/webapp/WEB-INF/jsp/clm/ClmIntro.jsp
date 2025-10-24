<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="org.springframework.web.servlet.support.RequestContext"%>
<%@page import="com.sds.secframework.common.util.StringUtil" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
	boolean spotViewFlag  = spotViewFlag(session);
	String personalViewFlag = personalViewFlag(session);
%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="clm.main.title" /></title>

<LINK href="<%=CSS%>/layout.css"   type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="/script/clms/common.js" type="text/javascript"></script>

<script language="javascript">
$(document).ready(function(){
	$('#spot').bind('click', function(){
		$('#sessionConfig').attr("style","display:block");		
	});
	$('#psl').bind('click', function(){
		$('#personalConfig').attr("style","display:block");		
	});
});

/**
 * 메인 페이지로 이동
 */
 function doAction() {
	var frm = document.frm;
	
	var goFlag = "main";
	
	//세션 재 설정
	var $roleChk = "";
<%
if(spotViewFlag){ //보안관련하여  spotViewFlag 가 true인 사람만 표시한다(소스보기에서 안보이기 용도)
%>
	$roleChk = $('#sessionConfig :checked').val();
<%
}else if(!"".equals(StringUtil.bvl(personalViewFlag, ""))){
%>
	$roleChk = $('#personalConfig :checked').val();
<%	
}else{
%>	
	$roleChk = undefined;
<%	
}
%>
	if( typeof $roleChk != "undefined"){
		goFlag = "backdoor";
<%
if(spotViewFlag){ //보안관련하여  spotViewFlag 가 true인 사람만 표시한다(소스보기에서 안보이기 용도)
%>
		if($roleChk=="1"){
			$('input[name=user_id]').val('M101221062328C605436');
		}else if($roleChk=="2"){
			$('input[name=user_id]').val('D060303170039C100646');
		}else if($roleChk=="3"){
			$('input[name=user_id]').val('D091230221722C100767');
		}else if($roleChk=="4"){
			$('input[name=user_id]').val('R020218102340C104121');
		}else if($roleChk=="5"){
			$('input[name=user_id]').val('R020218102336C109809');
		}else if($roleChk=="6"){
			$('input[name=user_id]').val('R020218102336C109805');
		}else if($roleChk=="7"){
			$('input[name=user_id]').val('D080228100555C602146');
		}else if($roleChk=="8"){
			$('input[name=user_id]').val('R020218102330C109410');
		}else if($roleChk=="9"){   // 임현승
			$('input[name=user_id]').val('R020218102326C100366');
		}else if($roleChk=="10"){   // 이기원
			$('input[name=user_id]').val('M030702144020C108208');
		}else if($roleChk=="11"){   // 심우규
			$('input[name=user_id]').val('R020218102320C101152');
		}else if($roleChk=="12"){   // 전수창
			$('input[name=user_id]').val('D060303172944C101393');
		}else if($roleChk=="13"){   // 배민우
			$('input[name=user_id]').val('M101213141935C108968');
		}else if($roleChk=="14"){   // 손상한
			$('input[name=user_id]').val('P040109205544C100135');
		}else if($roleChk=="15"){   // 김종성
			$('input[name=user_id]').val('D100705153430C101083');
		}else{
			$('input[name=user_id]').val();
		}		
<%
}
%>

<%
if(!"".equals(StringUtil.bvl(personalViewFlag, ""))){
%>
		if($roleChk=="1"){
			$('input[name=user_id]').val('M120329023226C106063'); //메모리사업부 계약관리시스템
		}else if($roleChk=="2"){
			$('input[name=user_id]').val('M120329023445C104075'); //DS총괄 계약관리시스템
		}else if($roleChk=="3"){
			$('input[name=user_id]').val('M120329023640C107791'); //TP센터 계약관리시스템
		}else if($roleChk=="4"){
			$('input[name=user_id]').val('M120330050507C105945'); //반도체연구소 계약관리시스템
		}else if($roleChk=="5"){
			$('input[name=user_id]').val('M120330050819C104328'); //기흥/화성단지총괄 계약관리시스템
		}else if($roleChk=="6"){
			$('input[name=user_id]').val('M120328030219C108515'); //DMC부문 계약관리시스템
		}else if($roleChk=="7"){
			$('input[name=user_id]').val('M120329025856C107681'); //기타조직 계약관리시스템
		}else if($roleChk=="8"){
			$('input[name=user_id]').val('M110627234243C105628'); //국내법무 계약관리시스템
		}else if($roleChk=="9"){
			$('input[name=user_id]').val('M121023000222C109592'); //정보보호센터 계약관리시스템
		}else{
			$('input[name=user_id]').val();
		}
		
<%
}
%>


	}
	
	if(goFlag == "main"){
		frm.action = "<c:url value='/secfw/main.do' />";
		frm.method.value = "forwardMainPage";
		frm.flag.value = "C"; //menuInterceptor 에서 sys_cd를 셋팅하기 위해
	}else{
		frm.action = "<c:url value='/secfw/ssoCheck.do' />";
		frm.method.value = "clmsSelLoginPrcs";
		frm.f.value = "c";
	}
	
	frm.submit();
}

//페이지 자동 forward
function goMain(){
<%
	if(!spotViewFlag){ //spot 이 보이는 사람들은 자동으로 메인으로 넘어가는 기능을 안쓴다.
		if("".equals(StringUtil.bvl(personalViewFlag,""))){ //이동훈,곽현영은 안쓴다.
%>	 
	 setTimeout(function(){doAction();}, 1000);
<%
		}
	}
%>		 
}
</script>
</head>
<body onLoad="javascript:goMain();">
<form:form id="frm" name="frm" autocomplete="off">
	<input type="hidden" id="method" name="method"/>
	<input type="hidden" id="flag" name="flag"/>

<!-- login box-->
<div id="login_box">
 <%
 if(langCd.equals("ko")){
	 request.setAttribute("main_user_nm",(String)session.getAttribute("secfw.session.user_nm"));	 
 }else{
	 request.setAttribute("main_user_nm",(String)session.getAttribute("secfw.session.user_nm_en"));
 }
 %>             
    <!-- login -->
    <div class="login">
     <span class="logo"><img src="<%=IMAGE%>/common/system_logo.gif" alt="logo"  /></span>
        <dl>
        <dt><%=StringUtil.bvl((String)session.getAttribute("secfw.session.dept_nm"), "")%></dt><dd><spring:message code="clm.main.user_nm" arguments="${main_user_nm}"  /></dd>
        <dt>Current Connection IP</dt><dd><%=StringUtil.bvl((String)session.getAttribute("secfw.session.loginIP"), "")%></dd>
        <dt>Last Connection IP</dt><dd><%=StringUtil.bvl((String)request.getAttribute("lastConnectIP"), "")%></dd>
		<dt>Last Login Time</dt><dd><%=StringUtil.bvl((String)request.getAttribute("lastConnectTime"), "")%></dd>
        </dl>
 
		<p class="detail">This System is strictly restricted to authorized users only.<br />Any illegal access shall be punished with a related-law.</p>
		<p class="detail_ko">
			삼성 전자 임직원을 위한 시스템으로서 인가된 분만 사용 가능하며 불법적으로 사용 시 <br />
			법적 제재를 받을 수 있습니다. 본 시스템 상 개인정보 처리에 대하여는 HR-Partner에  <br />
			게시되어 있는 개인정보처리방침을 참고하시기 바랍니다. <br />
		</p>
		
		<a href="<%=StringUtil.mainBvl((String)request.getAttribute("strAttachUrl"), "")%>direct_clm" class="btn_main_go"><spring:message code="clm.main.shortcuticon" /><!-- 바로가기 아이콘 생성 --><img src="<%=IMAGE%>/icon/ico_arrow_sky.gif" /></a>
		<a href="javascript:doAction();" class="btn_main">Confirm <img src="<%=IMAGE%>/icon/ico_arrow_sky.gif" /></a>
    </div>
    <!-- //login -->  
 <%
 	if(spotViewFlag){
 %>
    <!-- login -->
    <input type="hidden" name="user_id" value=""/>
    <input type="hidden" name="f" value=""/>
    <span id="spot">.</span>
    <div id="sessionConfig" style="display:none">
    	<div>무선의뢰인 - 이경섭<input type="radio" name="userid" id="userid" value="1" /></div>
    	<div>무선의뢰인 - 심우규<input type="radio" name="userid" id="userid" value="11" /></div>
    	<div>무선의뢰인 - 임현승<input type="radio" name="userid" id="userid" value="9" /></div>
    	<div>메모리 의뢰인 - 전수창<input type="radio" name="userid" id="userid" value="12" /></div>
    	<div>MSC 의뢰인 - 손상한<input type="radio" name="userid" id="userid" value="14" /></div>
    	<div><br/></div>
    	<div>무선준법지원 - 김홍<input type="radio" name="userid" id="userid" value="2" /></div>
    	<div>영상 디스플레이 준법 지원 - 이기원<input type="radio" name="userid" id="userid" value="10" /></div>
    	<div>메모리 준법 지원 - 배민우<input type="radio" name="userid" id="userid" value="13" /></div>
    	<div>MSC 준법 지원 - 김종성<input type="radio" name="userid" id="userid" value="15" /></div>
    	<div><br/></div>
    	<div>국내법무담당자 - 박태준<input type="radio" name="userid" id="userid" value="3" /></div>
    	<div>국내법무그룹장 - 최윤식<input type="radio" name="userid" id="userid" value="4" /></div>
    	<div><br/></div>
    	<div>해외법무담당자 - 김일환<input type="radio" name="userid" id="userid" value="5" /></div>
    	<div>해외법무그룹장 - 최종원<input type="radio" name="userid" id="userid" value="6" /></div>
    	<div><br/></div>
    	<div>IP센터담당자 - 한지연<input type="radio" name="userid" id="userid" value="7" /></div>
    	<div>IP센터그룹장 - 김유석<input type="radio" name="userid" id="userid" value="8" /></div>    	
    </div>    
    <!-- //login -->  
 <%
 	}
 %>
 
 <%
 	if(!"".equals(StringUtil.bvl(personalViewFlag, ""))){
 %>
	<input type="hidden" name="user_id" value=""/>
    <input type="hidden" name="f" value=""/>
    <span id="psl">사업부선택 Click!</span>
	<div id="personalConfig" style="display:none">

<%
		if("LEE".equals(StringUtil.bvl(personalViewFlag, ""))){
%>	
		<div>메모리사업부 계약관리시스템<input type="radio" name="userid" id="userid" value="1" /></div>
    	<div>DS부문 계약관리시스템<input type="radio" name="userid" id="userid" value="2" /></div>
		<div>TP센터 계약관리시스템<input type="radio" name="userid" id="userid" value="3" /></div>
    	<div>반도체연구소 계약관리시스템<input type="radio" name="userid" id="userid" value="4" /></div>
		<div>기흥/화성단지총괄 계약관리시스템<input type="radio" name="userid" id="userid" value="5" /></div>
<%
		}else{
%>    	
		<!-- <div>DMC부문 계약관리시스템<input type="radio" name="userid" id="userid" value="6" /></div>  dmc 폐지-->
		<div>정보보호센터 계약관리시스템<input type="radio" name="userid" id="userid" value="9" /></div>
		<div>국내법무 계약관리 시스템<input type="radio" name="userid" id="userid" value="8" /></div>
    	<div>기타 계약관리시스템<input type="radio" name="userid" id="userid" value="7" /></div>
<%
		}
%>     
    </div>      
 <%
 	}
 %> 
 
</div>
<!-- //login box-->
</form:form>
</body>
</html>
<%!
//백도어 view 여부
public boolean spotViewFlag(HttpSession session) throws Exception{
	boolean retV = false;
	ArrayList roleList = (ArrayList)session.getAttribute("secfw.session.role");
	String userId = (String)session.getAttribute("secfw.session.user_id"); //UserId

	ArrayList userRoleList = new ArrayList();
    if(roleList != null && roleList.size()>0){
    	for(int i=0; i < roleList.size() ;i++){
        	HashMap hm = (HashMap)roleList.get(i);
        	String roleCd = (String)hm.get("role_cd");
        	userRoleList.add(roleCd);
        }
	}
    String result = "";
    if(userRoleList != null && userRoleList.size()>0) {
    	if(userRoleList.contains("RA00")){
			result = "A";
		}
	}
    
  //시스템 관리자이거나 아래 사람들만 spot 이 보임
    if("A".equals(result) || (
    		"R020218102320C101152".equals(userId) || //심우규
    		"D080228100555C602146".equals(userId) || //한지연
    		"D050120225841C101032".equals(userId) || //서유강
    		"R020218102326C100366".equals(userId) || //임현승
    		"M030702144020C108208".equals(userId) || //이기원
    		"M050707002047C104463".equals(userId) || //박수진
    		"D091230221722C100767".equals(userId) || //박태준
    		"D060705193616C103463".equals(userId) || //이효은
    		"R020218102340C104121".equals(userId) || //최윤식
    		"D110401102907C600064".equals(userId) || //김종태
    		"D110808090247C100398".equals(userId) || //강주현
			"D110808090241C100389".equals(userId) || //이지은
    		"R020218102336C109809".equals(userId) //김일환
    )){
    	retV = true;
    }
    
    return retV;
}

//이동훈,곽현영 전용
public String personalViewFlag(HttpSession session) throws Exception{
	String retV = "";
	String userId = (String)session.getAttribute("secfw.session.user_id"); //UserId

	if("M090521003925PCK8125".equals(userId)){
		retV = "LEE";
	}else if("S020305135853C101859".equals(userId)){
		retV = "KWOK";
	}else{
		retV = "";
	}
	
    return retV;
}
 %>