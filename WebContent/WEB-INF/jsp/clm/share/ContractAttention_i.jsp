<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.sec.common.util.ClmsDataUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@page import="com.sds.secframework.common.util.Token"%>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>



<%@ page import="com.sec.clm.share.dto.ContractAttentionForm" %>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>

<%@ include file= "/WEB-INF/jsp/common/common.jsp" %>

<%--
/**			
 * 파  일  명 : ContractAttention_i.jsp
 * 프로그램명 : 계약체결시 유의사항 등록
 * 설      명 : 
 * 작  성  자 : 유영섭  
 * 작  성  일 : 2011.09. 20
 */
 --%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	ContractAttentionForm command = (ContractAttentionForm)request.getAttribute("command");
	 
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
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>

<script language="javascript">

$(document).ready(function(){
	
    getCodeSelectByUpCd("frm", "type_gbn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=TOP&combo_abbr=F&combo_type=S&combo_del_yn=N&combo_selected=<%=command.getType_gbn()%>");
    frm.type_bigclsfcn.style.display = 'none';
    frm.type_midclsfcn.style.display = 'none'; 
   
});

function changeSubCd(selObjId, gbn, upCd) {

	if(upCd == ''){
	 	frm.type_bigclsfcn.style.display = 'none';
	    frm.type_midclsfcn.style.display = 'none';
	    frm.type_bigclsfcn.value = "";
	    frm.type_midclsfcn.value = ""; 
	}
	 if(upCd == "T01" || upCd == "T02"){
		frm.type_bigclsfcn.style.display = '';
	 	frm.type_midclsfcn.style.display = 'none';
		frm.type_midclsfcn.value = ""; 
    }else if(upCd == "T03"){	
    	frm.type_bigclsfcn.style.display = '';
        frm.type_midclsfcn.style.display = ''; 
}
	
	if(upCd == "") {
        upCd = "XXX";
    }

    getCodeSelectByUpCd('frm', selObjId, '/common/clmsCom.do?method=getComboHTML&combo_gbn=' + gbn + '&combo_up_cd=' + upCd + '&combo_abbr=F&combo_type=S&combo_del_yn=N');
}


 function changeSubCd2(selObjId, gbn, upCd) {

	 	 if(upCd == ''){
	 		 frm.type_midclsfcn.style.display = 'none'
		 	 frm.type_midclsfcn.value = ""; 
	    }
	 	 
        getCodeSelectByUpCd('frm', selObjId, '/common/clmsCom.do?method=getComboHTML&combo_gbn=' + gbn + '&combo_up_cd=' + upCd + '&combo_abbr=F&combo_type=S&combo_del_yn=N');
}


	
/**
* 버튼 동작 부분
*/
function pageAction(flag){
	var frm = document.frm;

	//저장
	if(flag == "insert"){
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/share/ContractAttention.do' />"; 
	    frm.method.value = "insertContractAttention";
		    
	    //나모웹에디터 관련
	    frm.body_mime.value = frm.wec.MIMEValue;
		
		
	    //Validation Check1 
	    if(frm.type_gbn.value == ""){
	    	alert("<spring:message code='clm.page.field.contractattention.exam1' />");
	    	return;
	    }
	    
	    if(frm.type_bigclsfcn.value == ""){
	    	alert("<spring:message code='clm.page.field.contractattention.exam2' />");
	    	return;
	    }
	    
	    if(frm.title.value == ""){
	    	alert("<spring:message code='clm.page.field.contractattention.title_check' />");
	    	return;
	    }

	    var i = document.getElementById("type_midclsfcn");
	  		if(frm.type_midclsfcn.value == "" && i.length > 1){
	    		alert("<spring:message code='clm.page.field.contractattention.exam3' />");
	    		return;
	  		 }
	    
	    
	    
	    viewHiddenProgress(true) ;
	    frm.submit();	
	    
	    
	    
	}else if(flag == "cancel"){	
		if(confirm("<spring:message code='secfw.msg.ask.cancel' />")){
			viewHiddenProgress(true) ;
			frm.method.value = "listContractAttention";
			frm.submit();		
		}
	}  
}  



</script>

</head>
<body>
<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">

<!-- search form -->
<input type="hidden" name="method"   value="">
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>">

<!-- key form-->
<input type="hidden" name="mtbat_no"	value="<c:out value='${command.mtbat_no}'/>" />
<input type="hidden" name="body_mime" id="body_mime" value="" />
<!-- Insert Page Param -->



  

<!-- // **************************** Form Setting **************************** -->

<div id="wrap"> 
	

	<!-- container -->
		<div id="container">	
		<!-- Location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.contractattention.insertTitle" /></h3>
		</div>
		<!-- //title -->
		<!-- content -->
		<div id="content">
			<!-- view -->
			<table cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col class="tal_w04" />
					<col width="200" />
					<col width="200" />
					<col  />
				</colgroup>
				<tbody>
						<tr>
						<th><spring:message code="clm.page.field.contractattention.exam" /><span class="astro">*</span></th>
						<td>            
							<select name="type_gbn" id="type_gbn" style="width:195px;" value="" alt="<spring:message code="clm.page.field.contractattention.exam1" />"  required onChange='javascript:changeSubCd("type_bigclsfcn", "CONTRACTTYPE", this.value); '>
							
							</select>
						</td>
						
						<td>
							 <select name="type_bigclsfcn" id="type_bigclsfcn" value="" alt="<spring:message code="clm.page.field.contractattention.exam2" />"  required style="width:195px;" onChange='javascript:changeSubCd2("type_midclsfcn", "CONTRACTTYPE", this.value); '>
							           	
							 </select>
						</td>
						<td>
							<select name="type_midclsfcn" id="type_midclsfcn"  value="" alt="<spring:message code="clm.page.field.contractattention.exam3" />"  required style="width:195px;">
										
							</select>	
						</td>
					
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.contractattention.title" /><span class="astro">*</span></th>
						<td colspan="3">           
							<input type="text" name="title" id="title" alt="<spring:message code="clm.page.field.lawterms.terms_ab" />" maxLength="128" class="text_full" style="width:300px" required />
						</td>
					
						
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.contractattention.cont" /><span class="astro">*</span></th>
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