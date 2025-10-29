<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>
<%@ page import="com.sds.secframework.common.util.Token"%>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파  일  명 : LawfirmRemitCert_i.jsp
 * 프로그램명 : 송금증 입력 화면
 * 설      명 :  송금증을 입력한다.
 * 작  성  자 :  박병주
 * 작  성  일 : 2011.10
 */
--%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script language="javascript">
<!--
	/**
	* 초기화면 로딩
	*/
	$(document).ready(function(){
		
		var frm = document.frm;
		// 첨부파일창 load
		initPage();
		
		// 날짜 textbox
		initCal("remitday");
		
	}); 	//초기화면 로딩 끝
	
	/**
	* 첨부파일창 load
	*/
	function initPage(){
		
		var frm = document.frm;
	
	    frm.target          = "fileList";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.submit();   
	}
	
	/**
	* form 전송시
	*/	
	function checkForm(){
		
		var frm = document.frm;
		var check_flag = true;
		
		check_date();
		
		return check_flag;
	}
	
	/**
	* 저장시 날짜 form 에서 "-" 를 삭제 
	*/
	function check_date(){
		$('*').attr('class',function(index){
			if($(this).attr("class") == 'text_calendar02'){
				$(this).val($(this).val().replace(/-/gi,''));					
			}
		}); 
	}

	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		
		var frm = document.frm;

		//저장
		if(flag == "insert"){
		    
		    var confirmMessage = frm.remitcert_no.value==("") ? "<spring:message code='secfw.msg.ask.insert' />" : "<spring:message code='secfw.msg.ask.update' />" ;
		    
		    //유효성 체크
		    if(validateForm(frm)){
		    	
		    	var confirmMessage = frm.remitcert_no.value==("") ? "<spring:message code='secfw.msg.ask.insert' />" : "<spring:message code='secfw.msg.ask.update' />" ;
		    	
		    	fileList.UploadFile(function(uploadCount){

					//지정된 형식 이외에 파일을 추가한 경우
					if (uploadCount < 0) {
						return;
					}                  

                    if(checkForm() && confirm(confirmMessage)){
    		    		
                    	viewHiddenProgress(true) ;
    		    		frm.method.value = frm.remitcert_no.value==("") ? "insertLawfirmRemitCert" : "modifyLawfirmRemitCert";
    					frm.target = "_self";
    					frm.action = "<c:url value='/las/lawservice/lawfirmRemitCert.do' />";
    			    	frm.submit();	

                    } else {
    		    		return;	
    		    	}
                });
		    } 		    
			
		//취소
		} else if(flag == "cancel"){
			
			viewHiddenProgress(true);
			frm.method.value = frm.remitcert_no.value==("") ? "listRemitCert" : "detailLawfirmRemitCert";
			frm.action = "<c:url value='/las/lawservice/lawfirmRemitCert.do' />";
			frm.target = "_self";
			frm.submit();			
		} 
	}
	
//-->
</script>

</head>
<body>
<!-- Calendar  -->
<div id='popCal' style='position: absolute; visibility: hidden; ridge; width: 10; z-index:1'>
<iframe name="popFrame" src="<c:url value='/secfw/popCalendar.do?method=goPopCalendar' />" frameborder="0" scrolling="no" width="196" height="188"></iframe></div>
<script event=onclick() for=document> 
    if(popCal.style.visibility == "visible"){
      popCal.style.visibility="hidden";
    }
</script>

<form:form name="frm" id="frm" method="post" autocomplete="off">
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
<input type="hidden" name="forward_from"  value="<c:out value='${command.forward_from}'/>" />
<input type="hidden" name="forward_url"   value="" />

<!-- search form -->
<input type="hidden" name="srch_lawfirm_nm"   value="<c:out value='${command.srch_lawfirm_nm}'/>" />  
<input type="hidden" name="srch_start_dt"   value="<c:out value='${command.srch_start_dt}'/>" />
<input type="hidden" name="srch_end_dt"   value="<c:out value='${command.srch_end_dt}'/>" />
<input type="hidden" name="srch_order_type"   value="<c:out value='${command.srch_mainftre_estmt}'/>" />

<!-- key form-->

<input type="hidden" name="lawfirm_id" id="lawfirm_id" value="<c:out value='${command.lawfirm_id}'/>"  />
<input type="hidden" name="remitcert_no" id="remitcert_no" value="<c:out value='${command.remitcert_no}'/>"  />

<!-- 첨부파일정보 시작 -->
<input type="hidden" name="fileInfos"   value="" />
<input type="hidden" name="file_bigclsfcn"  value="F005" />
<input type="hidden" name="file_midclsfcn" 	value="F00507" />
<input type="hidden" name="file_smlclsfcn"  value="" />
<input type="hidden" name="ref_key"     value="<c:out value='${command.remitcert_no}'/>" />
<c:choose>
<c:when test="${command.remitcert_no == ''}">
	<input type="hidden" name="view_gbn"    value="upload" />
	</c:when>
	<c:otherwise>
	<input type="hidden" name="view_gbn" 	value="modify" />
	</c:otherwise>
</c:choose>

<div id="wrap">	
	<!-- container -->
<div id="container">
	<!-- Location -->
	<div class="location"><IMG SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
	<!-- //Location -->	
	<!-- title -->
	<div class="title">
		<c:choose>
			<c:when test="${command.remitcert_no == ''}">
				<h3><spring:message code="las.page.field.lawservice.remitcert"/> <spring:message code="las.page.field.lawservice.register" /></h3>
			</c:when>
		<c:otherwise>
			<h3><spring:message code="las.page.field.lawservice.remitcert"/> <spring:message code="las.page.field.lawservice.modify" /></h3>
				</c:otherwise>
		</c:choose>		
	</div>
	<!-- //title -->
	<!-- content -->
	<div id="content">
		<!-- view -->
		<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
			<colgroup>
				<col width="15%" />
				<col width="35%" />
				<col width="15%" />
				<col width="35%" />
			</colgroup>
			<tbody>
				<tr>
					<th><spring:message code="las.page.field.lawservice.subtitle"/></th>
					<td colspan="3">					
						<input alt="<spring:message code="las.page.field.lawservice.subtitle"/>" required type="text" name="remitcert_nm" id="remitcert_nm" value="<c:out value='${lom.remitcert_nm}'/>" class="text_full" maxLength="100" />
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.lawfirm" /></th>
					<td colspan="3">
						<c:choose>
							<c:when test="${command.remitcert_no == ''}">
								<c:out value='${command.lawfirm_nm}'/>
							</c:when>
							<c:otherwise>
								<c:out value='${lom.lawfirm_nm}'/>
							</c:otherwise>
						</c:choose>	
					</td>
				</tr>
				<tr>	
					<th><spring:message code="las.page.field.lawservice.remitday"/></th>
					<td>
						<input alt="<spring:message code="las.page.field.lawservice.remitday"/>" required type="text" name="remitday" id="remitday" value="<c:out value='${lom.remitday}'/>"  class="text_calendar02" readonly />
					</td>
					<th><spring:message code="las.page.field.lawservice.regdt"/></th>
					<td>
						<c:out value='${command.reg_dt}'/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.incharge" /></th>
					<td colspan="3">
						<c:out value='${command.reg_nm}'/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.remitcont"/></th>
					<td colspan="3">
						<textarea alt="<spring:message code="las.page.field.lawservice.remitcont"/>" id="cont" name="cont"  maxlength="500" cols="as" rows="3" class="text_area_full"  maxLength="2000" ><c:out value='${lom.cont}'/></textarea>
					</td>
				</tr>
				<tr class="end">
					<th><spring:message code="las.page.field.lawservice.attach" /></th>
					<td colspan="3">
						<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="80px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- //view -->
		
		<div class="t_titBtn">
			<div class="btn_wrap">
				<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('insert')"><spring:message code="las.page.button.lawservice.save" /></a></span>
				<span class="btn_all_w"><span class="cancel"></span><a href="javascript:pageAction('cancel')"><spring:message code="las.page.button.lawservice.cancel" /></a></span>
			</div>
		</div>

	</div>
	<!-- //content -->
			<!-- footer  -->
			<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
			<!-- // footer -->
			<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</div>
<!-- //container -->
</div>
</form:form>
</body>
</html>