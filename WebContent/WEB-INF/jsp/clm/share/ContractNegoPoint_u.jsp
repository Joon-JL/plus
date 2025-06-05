<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.sec.common.util.ClmsDataUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@page import="com.sds.secframework.common.util.Token"%>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="com.sec.clm.share.dto.ContractNegoPointForm" %>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file= "/WEB-INF/jsp/common/common.jsp" %>

<%--
/**
 * 파  일  명 : ContractNegoPoint_u.jsp
 * 프로그램명 : 주요 거래선 유형별 협상포인트 수정 
 * 설      명 : 
 * 작  성  자 : 유영섭
 * 작  성  일 : 2011.10.07
 */
 --%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("lom"); 
	ContractNegoPointForm command = (ContractNegoPointForm)request.getAttribute("command");
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/clm.css" type="text/css" rel="stylesheet" />
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/util/fckeditor/fckeditor.js' />"></script>
<script language="javascript">



$(document).ready(function(){

	initPage();
    
});




/**
* 버튼 동작 부분
*/
function pageAction(flag){
	var frm = document.frm;
	
	frm.target = "_self";
	frm.action = "<c:url value='/clm/share/ContractNegoPoint.do' />"; 
	
	if(flag == "modify"){
	    frm.method.value = "modifyContractNegoPoint";
	    
	  	//나모웹에디터 관련
	    frm.body_mime.value = frm.wec.MIMEValue;
	      	
	  	//Validation Check2
	    if(validateForm(frm)){
			if(confirm("<spring:message code='secfw.msg.ask.update' />")){
				//첨부파일 업로드 실행
				fileList.UploadFile(function(uploadCount){
					if(uploadCount == -1){
						initPage();
						alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
						return;
					}

                    //첨부파일 필수일 경우
                    if (uploadCount == 0) {
                        alert("<spring:message code='secfw.msg.error.fileNon' />");
                        return;
                    }    
					viewHiddenProgress(true) ;
			    	frm.submit();	
				});
		    }
	    }
	} else if(flag == "cancel"){	
		if(confirm("<spring:message code='secfw.msg.ask.cancel' />")){
			viewHiddenProgress(true) ;
			
		    frm.method.value = "detailContractNegoPoint";
		    frm.nego_no.value = <%=lom.get("nego_no")%>;
			frm.submit();
		} 
	}
}
	
//거래상대방 팝업

function openNegoCustomer(){
    
	var cntrt_oppnt_nm = frm.cntrt_oppnt_nm.value;	
	PopUpWindowOpen('', 1163, 440, true);
	frm.target = "PopUpWindow";
	frm.action = "<c:url value='/clm/draft/customer.do' />";
	frm.method.value = "listCustomer";
	frm.customer_gb.value = "O";
	frm.srch_customer.value = cntrt_oppnt_nm;
	frm.submit();
	
	

}

function returnCustomer(obj){
	var frm = document.frm;
	frm.cntrt_oppnt_nm.value = obj.customer_nm1;
	frm.customer_cd.value = obj.customer_cd;
	
	

}

	/**
	* 메시지 처리
	*/
	function showMessage(msg){
		if(msg != null && msg.length > 1) {
			alert(msg);
		}
	}
	
	function initPage(){
	    frm.target			= "fileList";
	    frm.action 			= "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value  	= "forwardClmsFilePage";
		frm.submit();	
	}
	

//-->
</script>
  
</head>
<body>
<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">
<!-- srch form -->
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
<input type="hidden" name="mode"  value="<c:out value='${command.mode}'/>" />

<input type="hidden" name="nego_no"    id="nego_no"    value="<c:out value='${lom.nego_no}'/>" />

<!-- 나모 웹 에디터 관련 -->
<input type="hidden" name="body_mime" id="body_mime" value="<c:out value='${lom.cont}' />" />

<!-- key form-->
<input type="hidden" name="cont" id="cont" value="" />
<input type="hidden" name="srch_customer" id="srch_customer"	value="" />
<input type="hidden" name="customer_gb" id="customer_gb" value="" />
<input type="hidden" name="" id="customer_cd" value="" />

<!-- 첨부파일정보 -->
<input type="hidden" name="fileInfos" 	value="" />
<input type="hidden" name="file_bigclsfcn" 	value="F014" />
<input type="hidden" name="file_midclsfcn" 	value="" />
<input type="hidden" name="file_smlclsfcn" 	value="" />
<input type="hidden" name="ref_key" 	value="<c:out value='${command.nego_no}'/>" />
<input type="hidden" name="view_gbn" 	value="modify" />


<!-- // **************************** Form Setting **************************** -->

<div id="wrap">   

	<!-- container -->
	<div id="container">
			<!-- Location -->
			<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
			<!-- //Location -->	
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.negopoint.modifyTitle" /></h3>
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
						<th><spring:message code="clm.page.field.negopoint.nego_theother_person" /><span class="astro">*</span></th>
						<td colspan="3">            
						<input type="text" name="cntrt_oppnt_nm" id="cntrt_oppnt_nm"  style="width:165px" value="<c:out value='${lom.cntrt_oppnt_nm}'/>" class="text_search"/ alt="<spring:message code="clm.page.field.negopoint.nego_theother_person" />" required><a href="javascript:openNegoCustomer();"><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" /></a>
						<span id="customerReturnVal" >
						</span>
						</td>					
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.negopoint.title" /><span class="astro">*</span></th>
						<td colspan="3">           
							<input type="text" name="title" id="title" value="<c:out value='${lom.title}' />" alt="<spring:message code="clm.page.field.negopoint.title" />" maxLength="128" class="text_full" style="width:300px" required />
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.contractattention.cont" /><span class="astro">*</span></th>
						<td  colspan="3">
							<%@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"%>
						</td>
					</tr>
					<tr class="end">
		            	<th><spring:message code="secfw.page.field.bbs.file" /><span class="astro">*</span></th>
		            		<td colspan="3">
		            			<iframe src="<c:url value='/clm/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="150px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
		            		</td>
		          	</tr>
				</tbody>
			</table>
			<!-- //view -->
	
			<div class="btn_wrap">
		<%if(command.isAuth_modify()){ %>
				<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('modify');"><spring:message code="secfw.page.button.insert" /></a></span>
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
	wecObj.Value = document.frm.body_mime.value;
</SCRIPT>
</html>
