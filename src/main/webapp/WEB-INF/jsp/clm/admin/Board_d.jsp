<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.admin.dto.BoardForm" %>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%-- 
/**
 * 파  일  명 : Board_d.jsp
 * 프로그램명 : 공지사항 상세
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	BoardForm command = (BoardForm)request.getAttribute("command");
	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("lom"); 
	List orgnzList = (List)request.getAttribute("orgnzList");
	String userId = (String)session.getAttribute("secfw.session.user_id");
	Boolean isWriter = userId.equals(lom.get("reg_id"));
	Boolean isAdmin = StringUtil.nvl((String)session.getAttribute("secfw.session.user_level"), "").equals("A");
	String localeCode = (String)session.getAttribute("secfw.session.language_flag");
	String wec_set_id = ""; 
	String wec_mode = ""; 
	String wec_com_script_yn = ""; 
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript">
<!--
	function setResize(h) {
	  document.getElementById("wec").style.height = h + 40 + 'px';
	 }

	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/admin/board.do' />";
		if(flag == "modify"){
			viewHiddenProgress(true) ;
		    frm.method.value = "forwardModifyBoard";
			frm.submit();

		} else if(flag == "delete"){	
			if(confirm("<spring:message code='secfw.msg.ask.delete' />")){
				viewHiddenProgress(true) ;
			    frm.method.value = "deleteBoard";
			    frm.submit();
			}	
		} else if(flag == "list"){	
			viewHiddenProgress(true) ;
		    frm.method.value = "listBoard";
			frm.submit();
		}
	}
	
	/**
	* 메시지 처리
	*/
	function showMessage(msg){
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
		}
	}
	
	$(document).ready(function(){
		//첨부파일창 load
		initPage();
	});
	
	function initPage(){
		var f = document.frm;
		
	    f.target			= "fileList";
	    f.action = "<c:url value='/clms/common/clmsfile.do' />";
		f.method.value  	= "forwardClmsFilePage";
		getClmsFilePageCheck('frm');	
	}	
	
//-->

</script>

</head>
<body onload='showMessage("<c:out value='${returnMessage}'/>");'>

<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">

<!-- search form -->
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
<input type="hidden" name="mode"  value="<c:out value='${command.mode}'/>" />
<input type="hidden" name="srch_start_dt" value="<c:out value='${command.srch_start_dt}'/>" />
<input type="hidden" name="srch_end_dt"   value="<c:out value='${command.srch_end_dt}'/>" />
<input type="hidden" name="srch_reg_operdiv"      value="<c:out value='${command.srch_reg_operdiv}'/>" />
<input type="hidden" name="srch_keyword"    value="<c:out value='${command.srch_keyword}'/>" />
<input type="hidden" name="srch_keyword_content"    value="<c:out value='${command.srch_keyword_content}'/>" />

<!-- key form-->
<input type="hidden" name="seqno"	value="<c:out value='${command.seqno}'/>" />

<!-- 첨부파일정보 -->
<input type="hidden" name="fileInfos" 	value="" />
<input type="hidden" name="file_bigclsfcn" 	value="F008" />
<input type="hidden" name="file_midclsfcn" 	value="" />
<input type="hidden" name="file_smlclsfcn" 	value="" />
<input type="hidden" name="ref_key" 	value="<%=lom.get("seqno")%>" />
<input type="hidden" name="view_gbn" 	value="download" />

<input type="hidden" name="cont" id="cont" 	value="<c:out value='${lom.cont}'/>" />
<input type="hidden" name="cont_en" id="cont_en" 	value="<c:out value='${lom.cont_en}'/>" />
<!-- // **************************** Form Setting **************************** -->

<div id="wrap">
	<!-- container -->
	<div id="container">	

		<!-- Location -->
		<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->

		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.board.detailTitle" /></h3>
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
						<th><spring:message code="clm.page.field.board.titleKr" /></th>
						<td colspan="3"><c:out value='${lom.title}' /></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.board.titleEn" /></th>
						<td colspan="3"><c:out value='${lom.title_en}' /></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.board.rd_trgt1" /></th>
						<td colspan="3"><c:out value='${lom.rd_trgt1_nm}' />
						<%if("C01602".equals((String)lom.get("rd_trgt1"))){
							if(orgnzList != null && orgnzList.size()>0){ 
								for(int i=0; i<orgnzList.size();i++){
									ListOrderedMap orgnz = (ListOrderedMap)orgnzList.get(i);
									
									if(orgnzList.size() == 1){
							%>
									[<span style='padding-left:3px; padding-right:3px;'><%=(String)orgnz.get("orgnz_nm") %></span>]
							<%										
									}else{
										if(i == 0){
							%>
										[<span style='padding-left:3px;padding-right:3px;'><%=(String)orgnz.get("orgnz_nm") %></span>,
							<%
										}else if(i == orgnzList.size()-1){
							%>
										<span style='padding-left:3px;padding-right:3px;'><%=(String)orgnz.get("orgnz_nm") %></span>]
							<%
										}else{
							%>
										<span style='padding-left:3px;padding-right:3px;'><%=(String)orgnz.get("orgnz_nm") %></span>,
							<%
										}
									}
								}
							  }
							}
						%>
						
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.board.rd_trgt2" /></th>
						<td><c:out value='${lom.rd_trgt2_nm}' /></td>
						<th><spring:message code="clm.page.field.board.secYn" /></th>
						<td><%if("Y".equals((String)lom.get("sec_yn"))){%>
							<spring:message code="clm.page.field.board.secYn1" />
							<%}else{ %>
							<spring:message code="clm.page.field.board.secYn2" />
							<%} %>
						</td>
					</tr>					
					<tr>
						<th><spring:message code="clm.page.field.board.regNm" /></th>
						<td><c:out value='${lom.reg_nm}' /> / <c:out value='${lom.regman_jikgup_nm}'/> /<c:out value='${lom.reg_dept_nm}' /></td>
						<th><spring:message code="clm.page.field.board.regDt" /></th>
						<td><%=DateUtil.formatDate((String)lom.get("reg_dt"), "-")%></td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.board.annceDt" /></th>
						<td colspan="3">
				          	<%if(!"".equals((String)lom.get("annce_startday"))){%>
				          		<%=DateUtil.formatDate((String)lom.get("annce_startday"), "-")%>
				          		~
				          	<%}%>
				          	<%if(!"".equals((String)lom.get("annce_endday"))){%>
				          		<%=DateUtil.formatDate((String)lom.get("annce_endday"), "-")%>
				          	<%}%>
				          	<%if("".equals((String)lom.get("annce_startday")) && "".equals((String)lom.get("annce_endday"))){%>
				          		<spring:message code="clm.msg.field.board.always" />
				          	<%} %>
						</td>
					</tr>
					
					<tr <% if(!localeCode.equals("ko") && !isAdmin && !isWriter) {%> style="display:none" <%} %>>
						<th><spring:message code="clm.page.msg.common.content" /></th>
						<td colspan="3">
							<%if("Y".equals((String)lom.get("sec_yn"))){%>
								<div style="margin-bottom: 10px; margin-top:5px;"><img src="<%=IMAGE%>/common/notice_txt.jpg" width="560" height="19" /></div>					  					  
							<%}%>
							<!-- <c:out value='${lom.cont}' escapeXml="false" /> -->
							<%
  					
		  					request.setAttribute("wec_set_id","wec1"); 
		  					request.setAttribute("wec_mode","READ");	
		  					request.setAttribute("wec_com_script_yn","N");
		  					%>
							<%@ include file="/WEB-INF/jsp/secfw/common/NamoAutoIncludeView.jspf"%>
						</td>
					</tr>
					<tr <% if(!localeCode.equals("en") && !isAdmin && !isWriter){ %> style="display:none" <%} %>>
						<th><spring:message code="clm.page.msg.common.content" /></th>
						<td colspan="3">
							<%if("Y".equals((String)lom.get("sec_yn"))){%>
								<div style="margin-bottom: 10px; margin-top:5px;"><img src="<%=IMAGE%>/common/notice_txt.jpg" width="560" height="19" /></div>					  					  
							<%}%>
							<!-- <c:out value='${lom.cont}' escapeXml="false" /> -->
							<%
		  					request.setAttribute("wec_set_id","wec2"); 
		  					request.setAttribute("wec_mode","READ");	
		  					request.setAttribute("wec_com_script_yn","N");
		  					%>
							<%@ include file="/WEB-INF/jsp/secfw/common/NamoAutoIncludeView.jspf"%>
						</td>
					</tr>
					<tr class="end">
		            	<th><spring:message code="secfw.page.field.bbs.file" /></th>
		            	<td colspan="3">
		            	<iframe src="<c:url value='/clm/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="100px" leftmargin="0" topmargin="0" scrolling="auto"  ></iframe>
		            	</td>
		          	</tr>
				</tbody>
			</table>
			<!-- //view -->
	
			<div class="btn_wrap">
	<%if(command.isAuth_modify()){ %>		
				<span class="btn_all_w"><span class="edit"></span><a href="javascript:pageAction('modify');"><spring:message code="secfw.page.button.modify" /></a></span>
	<%}%>
	<%if(command.isAuth_delete()){ %>		
				<span class="btn_all_w"><span class="delete"></span><a href="javascript:pageAction('delete');"><spring:message code="secfw.page.button.delete" /></a></span>
	<%}%>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:pageAction('list');"><spring:message code="secfw.page.button.list" /></a></span>
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
<SCRIPT language="javascript" FOR="wec1" EVENT="OnInitCompleted()"> 
 document.wec1.Value = document.frm.cont.value;
 document.wec1.EditMode = 0;
 document.wec1.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
 document.wec1.SetDefaultFontSize("9"); 
</SCRIPT>
<SCRIPT language="javascript" FOR="wec2" EVENT="OnInitCompleted()"> 
 document.wec2.Value = document.frm.cont_en.value;
 document.wec2.EditMode = 0;
 document.wec2.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
 document.wec2.SetDefaultFontSize("9"); 
</script>
</html>