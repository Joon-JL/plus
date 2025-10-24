<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@page import="com.sds.secframework.common.util.PageUtil"%>
<%@page import="com.sds.secframework.common.util.DateUtil"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@page import="com.sds.secframework.common.util.Token"%>


<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet">

<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/CommonProgress.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>

<script language="javascript">
	function init(){
		alertMessage('<c:out value='${command.return_message}'/>') ;
	}

	function goList() {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.method.value = "listSpeakContract" ;
		f.action = "<c:url value='/las/contractmanager/speakContract.do' />" ;
		f.target = "_self" ;
		f.submit() ;
	}
	
	function refresh(){
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.reset() ;

		f.method.value = f.seqno.value == -1 ? "forwardInsertSpeakContract" : "forwardModifySpeakContract";
		f.action = "<c:url value='/las/contractmanager/speakContract.do' />" ;
		f.target = "_self" ;
		f.submit() ;
	}
	
	function save() {
		var f = document.frm ;
		
		if(f.reqman_id.value == ""){
			alert("<spring:message code='las.page.field.contractManager.chooseReq'/>");
			return;
		}
		if(validateForm(document.frm)) {		
			var confirmMessage = f.seqno.value == -1 ? "<spring:message code='secfw.msg.ask.insert' />" : "<spring:message code='secfw.msg.ask.update' />" ;

			 fileList.UploadFile(function(uploadCount){
                //첨부파일 업로드 결과 잘못된 형식의 파일이 올라간 경우
                if(uploadCount == -1){
                    initPage(); //첨부파일창 Reload
                    alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
                    return;
                }
                
                if(confirm(confirmMessage)){
                	f.method.value = f.seqno.value == -1 ? "insertSpeakContract" : "modifySpeakContract";
                	f.action = "<c:url value='/las/contractmanager/speakContract.do' />" ;
					f.target = "_self" ;
					
					//나모웹에디터 관련
					 f.body_mime.value = f.wec.MIMEValue;
	                 
					viewHiddenProgress(true) ;
	            	frm.submit();   
                }
            });
		}
	}
	
	function initPage(){
	    frm.target			= "fileList";
	    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value  	= "forwardClmsFilePage";
		
		frm.submit();	
	}	

	/**
     * ESB 임직원 조회
	 */
	function searchEmployee() {
	    var frm = document.frm;
	    frm.srch_user_cntnt.value = frm.reqman_nm.value;
	    var srchValue = comTrim(frm.srch_user_cntnt.value); //입력받은 값

	    if (srchValue == "" || getByteLength(srchValue) < 4) { //최소 2자리 이상 입력받게 한다.
	        alert('<spring:message code='secfw.msg.error.nameMinByte' />'); 
	        frm.reqman_nm.focus();
	        return;
	    }

	    PopUpWindowOpen('', 800, 450, true);
	    frm.target = "PopUpWindow";

	    frm.srch_user_cntnt_type.value = "userName"; //이름으로 검색  - 보통 이름으로 검색함

	    frm.action = "<c:url value='/secfw/esbOrg.do' />";
	    frm.method.value = "listEsbEmployee";
	    frm.submit();

	    frm.target = "";
	}
	
	function setUserInfos(obj) {
		var f = document.frm;
	    var userInfo = obj;
	    
	    f.reqman_nm.value = userInfo.cn;	//의뢰자 이름
	    f.reqman_id.value = userInfo.epid; //의뢰자 id
	    f.reqman_dept_nm.value = userInfo.department; //의뢰자 부서
	    f.reqman_dept.value = userInfo.departmentnumber; //의뢰자 부서
	    f.reqman_mail.value = userInfo.mail;
	}
</script>

</head>
<body onLoad="alertMessage('<c:out value='${command.return_message}'/>');initPage();">
<form:form name="frm" id="frm" autocomplete="off">
<input type="hidden" name="method" value=""/>
<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>"/>
<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>"/>
<input type="hidden" name="srch_title" value="<c:out value='${command.srch_title}'/>">
<input type="hidden" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}'/>">
<input type="hidden" name="srch_respman_id" value="<c:out value='${command.srch_respman_id}'/>">
<input type="hidden" name="srch_start_ymd" value="<c:out value='${command.srch_start_ymd}'/>">
<input type="hidden" name="srch_end_ymd" value="<c:out value='${command.srch_end_ymd}'/>">
<input type="hidden" name="row_cnt" value="<c:out value='${command.row_cnt}'/>">
<input type="hidden" name="seqno" value="<c:out value='${command.seqno}'/>"/>
<input type="hidden" name="srch_user_cntnt" value=""/>
<input type="hidden" name="srch_user_cntnt_type" value=""/>
<input type="hidden" name="status" value=""/>
<input type="hidden" name="reqman_id" value="<c:out value='${command.reqman_id}'/>"/>
<input type="hidden" name="reqman_dept" value="<c:out value='${command.reqman_dept}'/>"/>
<input type="hidden" name="reqman_mail" value=""/>
<input type="hidden" name="isForeign" value="<c:out value='${command.isForeign}'/>"/>

<!-- 첨부파일정보 -->
<input type="hidden" name="fileInfos" 	value="" />
<input type="hidden" name="file_bigclsfcn" 	value="F002" />
<input type="hidden" name="file_midclsfcn" 	value="F00201" />
<input type="hidden" name="file_smlclsfcn" 	value="" />
<input type="hidden" name="ref_key" 	value="<c:out value='${command.seqno}'/>" />

<c:choose>
	<c:when test="${command.seqno == -1 }">
		<input type="hidden" name="view_gbn" 	value="upload" />
		<!-- 나모 웹 에디터 관련 -->
		<input type="hidden" name="body_mime" id="body_mime" value="" />
	</c:when>
	<c:otherwise>
		<input type="hidden" name="view_gbn" 	value="modify" />
		<!-- 나모 웹 에디터 관련 -->
		<input type="hidden" name="body_mime" id="body_mime" value="<c:out value='${command.re_cont}'/>" />
	</c:otherwise>		
</c:choose>

<!-- 이중등록 방지 -->
<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>">

<div id ="wrap">
<!-- container -->
<div id="container">
	<!-- Location -->
        <div class="location">
            <img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/>
        </div>
    <!-- //Location -->
    
	<!-- content -->
	<div id="content">
	    <div class="title">
				<h3><spring:message code="las.page.field.speakcontract.title" /></h3>
		</div> 	
 
		<!-- view -->
		<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
			<colgroup>
				<col width="13%"/>
				<col width="37%"/>
				<col width="13%"/>
				<col width="37%"/>
			</colgroup>
			<tbody>
				<tr>
					<th><spring:message code="las.page.field.speakcontract.reg_dt" /></th>
					<td><%=DateUtil.formatDate(DateUtil.today(), "-")%></td>
					<th><spring:message code="las.page.field.speakcontract.respman_nm" /></th>
					<td>
						<c:out value='${command.respman_nm}'/> 
					</td>
				</tr>
				<tr>
				 	<th><spring:message code="las.page.field.speakcontract.reqman_nm" /></th>
					<td>
						<input name="reqman_nm" id="reqman_nm" type="text" value="<c:out value='${command.reqman_nm}' escapeXml='false'/>"  alt="<spring:message code="las.page.field.contractManager.requClient"/>" required="*" style="width: 200px;border:0px">
						<span class="btn_all_b">
							<span class="save"></span> 
						<a href="javascript:searchEmployee()"><spring:message code="las.page.field.contractManager.select"/></a> </span>
					</td>
					<th><spring:message code="las.page.field.speakcontract.reqman_dept_nm" /></th>
					<td><input name="reqman_dept_nm" id="reqman_dept_nm" type="text" value="<c:out value='${command.reqman_dept_nm}' escapeXml='false'/>"  readonly="readonly" style="width: 200px;"></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.speakcontract.send_mailyn" /></th>
					<td colspan="3">
					<spring:message code="las.page.field.lawconsult.yes" /><input type="radio" name="send_mailyn" value="Y" <c:if test="${command.send_mailyn=='Y'}"> checked="checked"</c:if>/>
					<spring:message code="las.page.field.lawconsult.no2" /><input type="radio" name="send_mailyn" value="N" <c:if test="${command.send_mailyn=='N'}"> checked="checked"</c:if>/>
					</td>
				</tr>
				
				<tr>
					<th><spring:message code="las.page.field.speakcontract.title" /></th>
					<td colspan="3"><input name="title" id="title" type="text" value="<c:out value='${command.title}' escapeXml='false'/>" class="text_full" alt="<spring:message code="las.page.field.contractManager.title"/>" required="*" maxLength="128"/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.speakcontract.re_cont" /></th>
					<td colspan="3">
					<%@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"%>
					</td>
				</tr>
				<tr class="end">
	            	<th><spring:message code="las.page.field.speakcontract.file" /></th>
	            	<td colspan="3">
	            		<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="70px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
	            	</td>
	          	</tr>
			</tbody>
		</table>
		<!-- //view -->
		<!-- button -->
		<div class="btn_wrap tC">
			<span class="btn_all_b">
				<span class="save"></span>
					<a href="javascript:save()"><spring:message code="las.page.button.save" /></a>
			</span>
			&nbsp;&nbsp;
			<span class="btn_all_w">
				<span class="cancel"></span>
				<a href="javascript:goList()"><spring:message code="las.page.button.cancel" /></a>
			</span>
		</div>
		<!-- //button -->
	
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

<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
	var wecObj = document.wec;
	wecObj.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	wecObj.SetDefaultFontSize("9");
	wecObj.EditMode = 1;
	wecObj.Value = document.frm.body_mime.value; //namo 에 값 셋팅
</SCRIPT>
</html>
