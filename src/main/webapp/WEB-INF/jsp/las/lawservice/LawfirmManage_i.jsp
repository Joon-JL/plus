<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>
<%@ page import="com.sds.secframework.common.util.Token"%>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파  일  명 : LawfirmManage_i.jsp
 * 프로그램명 : 로펌관리 등록화면
 * 설      명 :  로펌을 등록
 * 작  성  자 :  박병주
 * 작  성  일 : 2011.08
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript">
<!--
	/**
	* 초기화면 로딩
	*/
	$(document).ready(function(){
		
		var frm = document.frm;
		
		//첨부파일창 load
		initPage();
		
		initCal("fst_cntrtday");
		
		var $inputTxt=$('#title');
		if($inputTxt.val()==''){
			$inputTxt.focus();
		}
		//$('#search_name').focus();
	
	});
	
	/**
	* 첨부파일창 load
	*/
	function initPage(){

	    frm.target          = "fileList";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.submit();   
	}
	
	/**
	* 국가선택 팝업 
	*/
	function NationPop()
	{		
		var frm = document.frm;
	
		PopUpWindowOpen('', "1000", "860", true);
		frm.action = "/secfw/main.do";
		frm.method.value="forwardPage";
		frm.target = "PopUpWindow";
		frm.forward_url.value="/WEB-INF/jsp/las/lawservice/NationList_p.jsp";
		frm.submit();

	}	

	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;

		//저장
		if(flag == "insert"){
		    
		    //나모웹에디터 관련
		    //frm.body_mime.value = frm.wec.MIMEValue;
			
		    //유효성 체크
		    if(validateForm(frm)){
		    	
		    	var confirmMessage = frm.lawfirm_id.value==("") ? "<spring:message code='secfw.msg.ask.insert' />" : "<spring:message code='secfw.msg.ask.update' />" ;
		    	
		    	fileList.UploadFile(function(uploadCount){

                    //첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우

                    if(uploadCount == -1){

                        initPage(); //첨부파일창 Reload
                        alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
                        return;

                    }                    

                    if(checkForm() && confirm(confirmMessage)){
    		    		
    		    		viewHiddenProgress(true);
    		    		frm.method.value = frm.lawfirm_id.value==("") ? "insertLawfirmManage" : "modifyLawfirmManage";
    					frm.target = "_self";
    					frm.action = "<c:url value='/las/lawservice/lawfirmManage.do' />";
    			    	frm.submit();	

                    } else {
    		    		return;	
    		    	}
                });
		    }    
		//취소
		} else if(flag == "cancel"){
			
			viewHiddenProgress(true);
			frm.method.value = frm.lawfirm_id.value==("") ? "listLawfirmManage" : "detailLawfirmManage";
			frm.target = "_self";
			frm.action = "<c:url value='/las/lawservice/lawfirmManage.do' />";
			frm.submit();
			
		} 
	}
	
	/**
	* Form check
	*/
	function checkForm(){
		var frm = document.frm;
		var check_tag = true;
		
		if(frm.fst_cntrtday.value != ''){
			frm.fst_cntrtday.value = replace(frm.fst_cntrtday.value,"-","");
		} 
		
		return check_tag;

	}

//-->
</script>

</head>
<body>
<!-- 
**************************** Calendar 관련 추가 영역 **************************** 
-->
<div id='popCal' style='position: absolute; visibility: hidden; ridge; width: 10; z-index:3'>
<iframe name="popFrame" src="<c:url value='/secfw/popCalendar.do?method=goPopCalendar' />" frameborder="0" scrolling="no" width="196" height="188" ></iframe></div>
<script event=onclick() for=document> 
	if(popCal.style.visibility == "visible"){
	  popCal.style.visibility="hidden";
	}
</script>
<form:form name="frm" id="frm" method="post" autocomplete="off">
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
<input type="hidden" name="screen_type"  value="insert" />

<!-- search form -->
<input type="hidden" name="srch_event_no"   value="<c:out value='${command.srch_event_no}'/>" />
<input type="hidden" name="srch_event_nm"   value="<c:out value='${command.srch_event_nm}'/>" />
<input type="hidden" name="srch_lawfirm_nm"   value="<c:out value='${command.srch_lawfirm_nm}'/>" />  
<input type="hidden" name="srch_lawfirm_id"   value="<c:out value='${command.srch_lawfirm_id}'/>" />
<input type="hidden" name="srch_mainftre_estmt"   value="<c:out value='${command.srch_mainftre_estmt}'/>" />
<input type="hidden" name="srch_kbn1"   value="<c:out value='${command.srch_kbn1}'/>" />
<input type="hidden" name="srch_kbn2"   value="<c:out value='${command.srch_kbn2}'/>" />
<input type="hidden" name="srch_nation"   value="<c:out value='${command.srch_nation}'/>" />
<input type="hidden" name="srch_nation_nm"   value="<c:out value='${command.srch_nation_nm}'/>" />

<!-- key form-->
<input type="hidden" name="forward_url"   value="" />
<input type="hidden" name="lawfirm_id"   value="<c:out value='${command.lawfirm_id}'/>" />
<input type="hidden" name="nation"   value="<c:out value='${command.nation}'/>" />

<!-- 첨부파일정보 시작 -->
<input type="hidden" name="fileInfos"   value="" />
<input type="hidden" name="file_bigclsfcn"  value="F005" />
<input type="hidden" name="file_midclsfcn" 	value="F00505" />
<input type="hidden" name="file_smlclsfcn"  value="" />
<input type="hidden" name="ref_key"     value="<c:out value='${command.lawfirm_id}'/>" />
<c:choose>
<c:when test="${command.lawfirm_id==''}">
	<input type="hidden" name="view_gbn"    value="upload" />
	</c:when>
	<c:otherwise>
	<input type="hidden" name="view_gbn" 	value="modify" />
	</c:otherwise>
</c:choose>

<!-- 나모 웹 에디터 관련 -->
<input type="hidden" name="body_mime" id="body_mime" value="" />
<input type="hidden" name="cont" id="cont" value="" />

<div id="wrap">		
	<!-- container -->
<div id="container">
	<!-- Location -->
	<div class="location"><IMG SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
	<!-- //Location -->	
	<!-- title -->
	<div class="title">
		<c:choose>
			<c:when test="${command.lawfirm_id == ''}">
				<h3><spring:message code="las.page.field.lawservice.lawfirmmng"/>&nbsp;<spring:message code="las.page.field.lawservice.register"/></h3>
			</c:when>
		<c:otherwise>
			<h3><spring:message code="las.page.field.lawservice.lawfirmmng"/>&nbsp;<spring:message code="las.page.field.lawservice.modify"/></h3>
				</c:otherwise>
		</c:choose>		
	</div>
	<!-- //title -->
	<!-- content -->
	<div id="content">
		<!-- view -->
		<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
			<colgroup>
				<col width="100" />
				<col width="310" />
				<col width="100" />
				<col width="310" />
			</colgroup>
			<tbody>
				<tr>
					<th><spring:message code="las.page.field.lawservice.lawfirmen"/></th>
					<td colspan="3">
						<input id="lawfirm_nm" name="lawfirm_nm" value="<c:out value='${command.lawfirm_nm}'/>" maxlength="33" type="text" class="text_full" style="width:200px" required alt="<spring:message code="las.page.field.lawservice.lawfirmnm"/>" />
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.eventnm"/></th>
					<td colspan="3">
						<select id='fst_event_no' name='fst_event_no' style='width:250px;'>
							<option value=''>-- <spring:message code="las.page.field.lawservice.select"/> --</option>	
							<c:forEach var="list" items="${eventList}">
								<option value="<c:out value='${list.event_no}'/>" <c:if test='${list.event_no == command.fst_event_no}'> selected </c:if>>
									<c:out value='${list.event_nm}'/>
								</option>			
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.fstcont"/></th>
					<td>
					<input alt="<spring:message code="las.page.field.lawservice.fstcont"/>" type="text" name="fst_cntrtday" id="fst_cntrtday" value="<c:out value='${command.fst_cntrtday}'/>"  class="text_calendar02" readonly />
					</td>
					<th><spring:message code="las.page.field.lawservice.email"/></th>
					<td><input alt="<spring:message code="las.page.field.lawservice.email"/>" id="rprsnt_email" name="rprsnt_email" value="<c:out value='${command.rprsnt_email}'/>" maxlength="32" type="text" class="text_full" style="width:200px" email /></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.contact"/></th>
					<td><input alt="<spring:message code="las.page.field.lawservice.contact"/>" id="rprsnt_tel"  name="rprsnt_tel" value="<c:out value='${command.rprsnt_tel}'/>" maxlength="24" type="text" class="text_full" style="width:200px" phone /></td>
					<th><spring:message code="las.page.field.lawservice.fax"/></th>
					<td><input alt="<spring:message code="las.page.field.lawservice.fax"/>" id="rprsnt_fax" name="rprsnt_fax"  value="<c:out value='${command.rprsnt_fax}'/>" maxlength="24" type="text" class="text_full" style="width:200px" phone /></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.addr"/></th>
					<td colspan="3"><input alt="<spring:message code="las.page.field.lawservice.addr"/>" id="addr" name="addr" value="<c:out value='${command.addr}'/>" maxlength="35" type="text" class="text_full" /></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.state"/></th>
					<td><input id="state_gbn" alt="<spring:message code="las.page.field.lawservice.state"/>" name="state_gbn"  value="<c:out value='${command.state_gbn}'/>" maxlength="8" type="text" class="text_full" style="width:200px" /></td>
					<th><spring:message code="las.page.field.lawservice.nation"/></th>
					<td><input id="nation_nm" alt="<spring:message code="las.page.field.lawservice.nation"/>" name="nation_nm" value="<c:out value='${command.nation_nm}'/>" type="text" class="text_full" style="width:200px" readonly <c:if test="${command.lawfirm_id==''}">onclick="javascript:NationPop();"</c:if> required />&nbsp;<c:if test="${command.lawfirm_id==''}"><a href="javascript:NationPop();"><spring:message code="las.page.field.lawservice.selectnation"/></a></c:if></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.home"/></th>
					<td colspan="3">http:// <input alt="<spring:message code="las.page.field.lawservice.home"/>" id="homepage" name="homepage" value="<c:out value='${command.homepage}'/>" maxlength="48"  type="text" class="text_full" style="width:200px" url /></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.maniftree"/></th>
					<td colspan="3"><textarea alt="<spring:message code="las.page.field.lawservice.maniftree"/>" id="mainftre_estmt" maxlength="1332" name="mainftre_estmt"  cols="as" rows="7" class="text_area_full"><c:out value='${command.mainftre_estmt}'/></textarea></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.banknm"/></th>
					<td><input alt="<spring:message code="las.page.field.lawservice.banknm"/>" id="bank_nm" name="bank_nm" maxlength="48" value="<c:out value='${command.bank_nm}'/>" type="text" class="text_full" style="width:200px" /></td>
					<th><spring:message code="las.page.field.lawservice.account"/></th>
					<td><input alt="<spring:message code="las.page.field.lawservice.account"/>" id="accnt_no" name="accnt_no" type="text" value="<c:out value='${command.accnt_no}'/>" maxlength="24" class="text_full" style="width:200px" /></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.bankaddr"/></th>
					<td colspan="3"><input alt="<spring:message code="las.page.field.lawservice.bankaddr"/>" id="bank_addr" name="bank_addr" value="<c:out value='${command.bank_addr}'/>"  maxlength="65" type="text" class="text_full" style="width:200px" /></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.banketc"/></th>
					<td colspan="3"><textarea alt="<spring:message code="las.page.field.lawservice.banketc"/>" id="bank_note" name="bank_note"  maxlength="65" cols="as" rows="3" class="text_area_full"><c:out value='${command.bank_note}'/></textarea></td>
				</tr>
				<tr class="end">
					<th><spring:message code="las.page.field.lawservice.attach"/></th>
					<td colspan="3">
						<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="80px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- //view -->		
		<div class="t_titBtn">
			<div class="btn_wrap">
				<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('insert')"><spring:message code="las.page.button.lawservice.save"/></a></span>
				<span class="btn_all_w"><span class="cancel"></span><a href="javascript:pageAction('cancel')"><spring:message code="las.page.button.lawservice.cancel"/></a></span>
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
<!-- //wrap -->
</form:form>
</body>
</html>