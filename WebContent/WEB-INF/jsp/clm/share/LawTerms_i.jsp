<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="java.util.Date" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sec.clm.share.dto.LawTermsForm" %>

<%@ include file= "/WEB-INF/jsp/common/common.jsp" %>

<%--
/**			
 * 파  일  명 : LawTerms_i.jsp
 * 프로그램명 : 계약용어집 등록
 * 설      명 : 
 * 작  성  자 : 유영섭  
 * 작  성  일 : 2011.08
 */
 --%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	LawTermsForm command = (LawTermsForm)request.getAttribute("command");
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<LINK href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet">

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>


<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script language="javascript">
	
	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
		
		
		//저장
		if(flag == "insert"){
			frm.target = "_self";
			frm.action = "<c:url value='/clm/share/LawTerms.do' />"; 
			frm.method.value = "insertLawTerms";
			
			  //나모웹에디터 관련
		    frm.body_mime.value = frm.wec.MIMEValue;
			
		    //유효성 체크
		    if(validateForm(frm)){
		    	
				if(confirm("<spring:message code='secfw.msg.ask.insert' />")){
			   
				frm.submit();	
			  	
			  	}
			  
			}
		}else if(flag == "cancel"){	
			if(confirm("<spring:message code='secfw.msg.ask.cancel' />")){
				viewHiddenProgress(true) ;
			   	frm.method.value = "listLawTerms";
			   	frm.submit();		
		}
	}  
  
}


</script>

</head>
<body>
<!-- **************************** Calendar 관련 추가 영역 **************************** -->
<div id='popCal' style='position: absolute; visibility: hidden; ridge; width: 10; z-index:1;'>
<iframe name="popFrame" src="<c:url value='/secfw/popCalendar.do?method=goPopCalendar' />" frameborder="0" scrolling="no" width=196 height=188></iframe></div>
<script event=onclick() for=document> 
if(popCal.style.visibility == "visible"){
  popCal.style.visibility="hidden";
}
</script>

<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">

<!-- search form -->
<input type="hidden" name="method"   value="">
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>">

<!-- key form-->
<input type="hidden" name="seqno"	value="<c:out value='${command.seqno}'/>" />
<input type="hidden" name="body_mime" id="body_mime" value="" />



  

<!-- // **************************** Form Setting **************************** -->

<div id="wrap"> 
	<!-- container -->
	<div id="container">
		<!-- Location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.lawterms.listTitle" /></h3>
		</div>
		<!-- //title -->
		<!-- content -->
		<div id="content">
			<!-- view -->
			<table  cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col class="tal_w04" />
					<col />
					<col class="tal_w04" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th><spring:message code="clm.page.field.lawterms.terms" /><span class="astro">*</span></th>
						<td colspan="3">            
							<input type="text" name="terms_nm" id="terms_nm"   value=""  alt="<spring:message code="clm.page.field.lawterms.terms" />" maxLength="64" class="text_full" style="width:300px"  required />
						</td>
					
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.lawterms.terms_ab" /><span class="astro">*</span></th>
						<td colspan="3">           
							<input type="text" name="terms_abbr_nm" id="terms_abbr_nm" value=""  alt="<spring:message code="clm.page.field.lawterms.terms_ab" />" maxLength="64" class="text_full" style="width:300px" required />
						</td>
					
						
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.lawterms.expl" /><span class="astro">*</span></th>
						<td  colspan="3">
							<%@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"%>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- //view -->
	
			<div class="btn_wrap">
		<%if(command.isAuth_insert()){ %>
				<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('insert');"><spring:message code="secfw.page.button.insert" /></a></span>
		<%} %>
				<span class="btn_all_w"><span class="cancel"></span><a href="javascript:pageAction('cancel');"><spring:message code="secfw.page.button.cancel" /></a></span>
			</div>
		</div>
		<!-- //content -->
        <!-- footer -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- //footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
</form:form>
</body>
<!-- 나모 웹에디터 관련 추가 -->
<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
	var wecObj = document.wec;
	wecObj.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	wecObj.SetDefaultFontSize("9");
	wecObj.EditMode = 1;	
</SCRIPT>
</html>