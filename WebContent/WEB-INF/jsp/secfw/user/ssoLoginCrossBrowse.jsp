<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%
	String forwardURL = "";
	forwardURL = StringUtil.bvl((String)request.getParameter("secfw.forwardURL"),(String)request.getAttribute("secfw.forwardURL"));
	forwardURL = StringUtil.bvl(forwardURL, "");
	
%> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title> New Login check </title>
<script type='text/javascript' src="<c:url value='/script/secfw/mySingle/mySingleAdm.js'/>"></script>
<script type='text/javascript' src="<c:url value='/script/secfw/mySingle/checkSyncTray.js'/>"></script>

<script language="javaScript">
<!--

	function checkUseSyncEXE(){
	
	    var useSyncExe;
	    
	    alert("windows = "+navigator.userAgent.toLowerCase().indexOf("windows"));
	    alert("msie = "+navigator.userAgent.toLowerCase().indexOf("msie")==-1);
	    alert("msie = "+navigator.userAgent.toLowerCase());
		
	    if(navigator.userAgent.toLowerCase().indexOf("windows")!=-1 && navigator.userAgent.toLowerCase().indexOf("msie")==-1){
		    useSyncExe = true; 
		}else{
		   	useSyncExe = false;  	
		}
		    return useSyncExe;
		} 

      
	function goInit() {
		
	    mySingleAdm.initialize();
        mySingleAdm.useEpAdmJC(checkUseSyncEXE());
        mySingleAdm.islogin('isloginCallback');
        
    }       

    function isloginCallback(result) {
	  
        //if (result) {
       	  // Regist매소드를 사용하지 않는 시스템은 아래 항목을 무시하셔도 됩니다.
       	  //mySingleAdm.setRegist("StandardFramework", 0, "StandardFramework", "");
       	  
          mySingleAdm.sync('autosubmitCallback');
          
       //}
      
    }

    function autosubmitCallback() {
   		
	    var rrtn2 = mySingleAdm.getSecureBox();
   	    var form = document.form1;
   	    
        if(rrtn2 != "") {
   		   
   		    // 기존에 데이터 추출이후의 로직을 추가하시면 됩니다.
	        // rrtn2 값이 암호화된 EpTray데이터 입니다.
	        //alert(rrtn2);  
	        //document.getElementById('form1').totaldata.value = rrtn2; 
	        //document.getElementById('form1').submit();
	          
	        form.totaldata.value = rrtn2;
	        form.submit();
	        
	    } else {
		  
		    // EpTray에 데이터가 없을 경우의 로직을 넣으시면 됩니다.
	    	alert('MySingle Login is required. Please log-in to MySingle.');
			location.replace('http://www.samsung.net');
			return;
		  
	    }				
  }
                     

//-->
</script>

</head>
<body leftmargin="15" topmargin="0" marginwidth="0" marginheight="0" >
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<form name="form1" method="post" action="<c:url value='/secfw/ssoCheck.do' />" autocomplete="off" >
			                            
			
				<input type="hidden" name="totaldata" value="" />
				<input type="hidden" name="goURL" value="<c:url value='/secfw/ssoCheck.do' />"  autocomplete="off" />
				<input type="hidden" name="goParamName" value="totaldata" />
		        <input type="hidden" name="registService" value="SECFW" />
		        <input type="hidden" name="method" value="clmsLogin" />
		        <input type="hidden" name="secfw.forwardURL" value="<%=forwardURL %>" />
				<!-- 
					디코드 페이지로 보내는 Tray데이터 파라미터 이름입니다.
					goParamName 이름은 변경하지 마시고 value값만 기존에 사용하시던 파라미터명을 적어 주시면 됩니다.
					이 셈플 html에서는 totaldata라는 이름을 사용했으므로 totaldata 를 value로 적어주 시면 됩니다.
					예) <input type="hidden" name="goParamName" value="totaldata">
				-->
				<input type="hidden" name="goParamName" value="ssoData">
				<!--
					Regist Start Regist매소드를 사용하지 않는 시스템은 아래 항목을 무시하셔도 됩니다. 
				  Regist할 서비스명을 value에 넣습니다. 
				  예) <input type="hidden" name="registService" value="misServiceName">
				-->
			</form>
		</td>
	</tr>
</table>
<!-- Tag for SSO Start -->
<script language="javascript">

		if (navigator.appVersion.indexOf("Mac")!=-1){	
			
			form1.action = "http://www.samsung.net/portalWeb/getSecureBox.jsp";
			form1.submit();
			
		}else{
			
        	mySingleAdm.installComponent("goInit();", "http://www.samsung.net/portalWeb/cabs/");
        	
    	}    
</script>
<!-- Tag for SSO End -->
</body>
</html>