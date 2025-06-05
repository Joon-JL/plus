<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%

    //메뉴네비게이션 스트링
	String[] ExcelNams  = (String[])request.getAttribute("ExcelNams");
	String[] ExcelVels = (String[])request.getAttribute("ExcelVels");

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="secfw.page.field.log.selectExcel"/></title>

<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript">
/**
 * 초기 설정
 *
 * @param  
 * @return 
 * @throws 
 */	
function init() {
	//초기로드 
            
}	

/**
* 체크된 값을 반환한다.
*
* @param  
* @return 
* @throws 
*/	
function excelChack(){
	//액셀선택 확인 
	 var ExcelNms ="";
	 var ExcelVel ="";
<%
 for(int i=0;i<ExcelNams.length;i++){
%>

	if(document.frm.<%=ExcelVels[i]%>.checked){
  	  	if(ExcelNms==""){
	  		ExcelNms +="<%=ExcelNams[i]%>";
	  		ExcelVel +="<%=ExcelVels[i]%>";
  	  	}else{
	  		ExcelNms +=",<%=ExcelNams[i]%>";
	  		ExcelVel +=",<%=ExcelVels[i]%>";
  	  	}
  	 }
<%}%>
//alert(ExcelNms);
var exeleInfo = new Object();
	exeleInfo.excel_nm=ExcelNms;
	exeleInfo.excel_vel=ExcelVel;
	opener.excelDownLoad(exeleInfo) ;
	window.close();
}
/**
* 선택값을 동일 하게 설정한다.
*
* @param  
* @return 
* @throws 
*/
function CheckAll(Chked){
<%
	for(int i=0;i<ExcelNams.length;i++){
%>  	
			document.frm.<%=ExcelVels[i]%>.checked=Chked;

		<%}%>
}
	
</script> 
</head>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common.js' />"></script>
<body onLoad="init();">
<LINK href="<c:url value='/style/secfw/common.css' />" type="text/css" rel="stylesheet">
<form:form id="frm" name="frm" method="post" autocomplete="off">
	
		
		<div class="search_box">
			<div class="search_box_in">	
			<span><span class="astro"><spring:message code="secfw.page.field.log.downloadList"/><span/></span>
			<table class='search_in' cellpadding='0' cellspacing='0'>
					<colgroup>
					<col/>
					<col/>
					<col/>
					<col/>					
					</colgroup>
					<tr>
					<td>
		            	<input type="checkbox" name="ChackAll" id="ChackAll" value="Y" class="radio" checked onclick="CheckAll(this.checked)">
					</td>
					<th colspan='3'><spring:message code="secfw.page.field.log.all"/><span class="astro">*</span></th>
					</tr>
				<% 
					for(int i=1 ;i<=ExcelNams.length;i++){
				    if(i%2==1){ 
				%>
					<tr>
					<%}%>	
			            <td  style='width:20'>
			            	<input type="checkbox" name="<%=ExcelVels[i-1]%>" id="<%=ExcelVels[i-1]%>" value="Y" class="radio" checked>
			            </td>
			            <th  style='width:100' ><%=ExcelNams[i-1]%><span class="astro">*</span></th>
					<%if(i%2==0){%>
					</tr>				  
					<%}%>
				<%
				    }
				%>
			</table>
			<div class="bt_content_top">
				<a href="javascript:excelChack()" class="bt_fn ico_excel_down"><span><spring:message code="secfw.page.field.log.selectCompl"/></span></a>
		    </div>			
			</div>
		</div>
</form:form>
</body>
</html>

