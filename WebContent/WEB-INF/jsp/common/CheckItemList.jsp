<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%-- 
/**
 * 파  일  명 : CheckItemList.jsp
 * 프로그램명 : 법인별 필수 항목 체크 리스트 표기 여부 설정 화면
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2014.04.24
 */
--%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="IE=edge" >
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<link href="/style/las/ko/las.css"   type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript">

	
	/**
	* 메시지 처리
	*/
	function showMessage(msg) {
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
		}
	}
	
	// 체크 클릭 시 display를 선택할 수 있게 열어 준다.
	function changeDisplay(){
		
		var frm = document.frm;
		var ch_Click = document.getElementsByName("chClick");
		
		for(var i=0;i<ch_Click.length;i++){
			
			if(ch_Click[i].checked==true){
				document.getElementById("disYes_"+i).disabled=false;
				document.getElementById("disNo_"+i).disabled=false;
			} else {
				document.getElementById("disYes_"+i).disabled=true;
				document.getElementById("disNo_"+i).disabled=true;
			}
		}
	}
	
	// 체크한 내용을 저장 한다.
	function checkSave(){
		
		var frm = document.frm;
		
		var ch_Click = document.getElementsByName("chClick");
		var check_value_yn;
		var check_value;
		var sum;
		
		for(var i=0;i<ch_Click.length;i++){
			
			if(ch_Click[i].checked==true){
				
				frm.check_value.value += ch_Click[i].value+",";
				
				frm.check_value_sum.value = frm.check_value.value;
				
				check_value = document.getElementsByName("dis_"+i);
				
				for(var z=0;z < check_value.length;z++){
					if(check_value[z].checked==true){
						
						frm.r_check_value.value += check_value[z].value+","; 
						
						frm.check_value_sum.value = frm.r_check_value.value;
						
					}
				}
				
				sum = frm.check_value.value + ":" +frm.r_check_value.value;
				
			} 
			
		}
		
		
		frm.check_value.value = "";
		frm.r_check_value.value = "";
		
		frm.all_sum.value = sum;
		
		frm.action = "<c:url value='/common/clmsCom.do' />";
	    frm.method.value = "modifyCheckItem";
	    frm.submit();   
		
	}
	
</script>
</head>
<body>
<div id="wrap">
	<!-- container -->
	<div id="container">
    <!-- location -->
	    <div class="location">
	    	<img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0"/><c:out value='${menuNavi}' escapeXml="false"/>
	    </div>
	    <!-- title -->
		<div class="title">
			<h3><spring:message code="las.page.field.hqrequest.page27"/></h3>
		</div>
	    <!-- //location -->
		<!-- content -->
		<div id="content">
			<div id="content_in">
			<!-- **************************** Form Setting **************************** -->
			<form:form name="frm" id='frm' method="post" autocomplete="off"> 
				<!-- search form -->
				<input type="hidden" name="method"   value="" />
				<input type="hidden" name="user_id" value="" />
				<input type="hidden" name="check_value" />
				<input type="hidden" name="r_check_value" />
				<input type="hidden" name="check_value_sum" />
				<input type="hidden" name="check_value_yn_sum" />
				<input type="hidden" name="all_sum"/>
				
			<!-- // **************************** Form Setting **************************** -->
			<!-- list -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic mt20">
				<colgroup>
				    <col width="10"/>
					<col width="10"/>
					<col width="15" />
					<col width="15"/>
			  	</colgroup>
			  	<thead>
					<tr>
						<th><spring:message code="clm.page.field.lawterms.rdcnt"/></th>
						<th><spring:message code="clm.common.checkPageListTitle"/></th>
						<th><spring:message code="clm.page.msg.manage.confirmYn"/></th>
						<th><spring:message code="las.page.field.menu.display_yn"/></th>
					</tr>
				</thead>
			 	<tbody>
			 	<c:choose>
				 	<c:when test="${checkListSize > 0 }">
				 	    <c:forEach var="chlist" items="${checkList}" varStatus="i">
					        <tr class="end">
					          <td class='tC'><c:out value='${chlist.no }'/> </td>
					          <td><c:out value='${chlist.cd_nm }'/></td>
					          <td class='tC'><input type="checkbox" name="chClick" value="<c:out value='${chlist.item_cd }' />" onclick="javascript:changeDisplay();" /></td>
					          <td class='tC'>
					          <c:choose>
					              <c:when test="${'Y' eq chlist.dis_yn }">
					                  Yes : <input type="radio" name="dis_<c:out value='${chlist.CHK_NO }'/>" id="disYes_<c:out value='${chlist.CHK_NO }'/>" checked="checked" disabled="disabled"  value="Y"/> No : <input type="radio" name="dis_<c:out value='${chlist.CHK_NO }'/>" id="disNo_<c:out value='${chlist.CHK_NO }'/>"  disabled="disabled" value="N"/></td>
					              </c:when>
					              <c:otherwise>
					                  Yes : <input type="radio" name="dis_<c:out value='${chlist.CHK_NO }'/>" id="disYes_<c:out value='${chlist.CHK_NO }'/>"  disabled="disabled"  value="Y"/> No : <input type="radio" name="dis_<c:out value='${chlist.CHK_NO }'/>" id="disNo_<c:out value='${chlist.CHK_NO }'/>"  disabled="disabled" value="N" checked="checked"/></td>
					              </c:otherwise>
					          </c:choose>
					        </tr>
				        </c:forEach>
				    </c:when>
				    <c:otherwise>
				        <tr>
						  <td colspan="4" class="td_ac" align="center"><spring:message code="secfw.msg.succ.noResult" /></td>
						</tr>
				    </c:otherwise>
			    </c:choose>
			    
					
							
			  </tbody>
			</table>
				<div class="btn_wrap" >
                    <span class="btn_all_w"><span class="tsave"></span><a href="javascript:checkSave();"><spring:message code='clm.page.button.contract.imsisave' /></a></span>			
				</div>
			
			<!-- //list -->
		</form:form>
		</div>
		</div>
		<!-- //content -->	
		</div>
		<!-- //container -->
	</div>
		<!-- footer -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- //footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
</html>

