<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap"%>
<%@ page import="com.sds.secframework.common.util.Token" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sec.common.clmscom.dto.OrgnzForm" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파     일     명 	: OrgnzSearchList.jsp
 * 프로그램명 	: 조직단위 선택 (팝업)
 * 설               명 	: 조직명으로 검색할 수 있으며, 체크박스에 체크된 데이터를 리스트로 반환한다.
 * 작     성     자 	: 신남원
 * 작     성     일 	: 2011.11.23
 */
--%>
<%
	List resultList = (List)request.getAttribute("resultList");
	OrgnzForm command = ((OrgnzForm)request.getAttribute("command") == null ? new OrgnzForm() : (OrgnzForm)request.getAttribute("command"));
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet"/>
<link href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet"/>

<script language="javascript" src="/script/secfw/jquery/jquery.js"></script>
<script language="javascript" src="/script/secfw/jquery/ui.core.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.form.js"></script>
<script language="javascript" src="/script/secfw/common/common.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language='javascript' src='/script/clms/event.js'></script>
<script language="javascript" src="/script/clms/clms_common.js"></script>
<script language="javascript">

	function pageAction() {
		var frm = document.frm;
		var orgnzList = new Array();
		
		var checkCnt = 0;
		
		//데이터가 존재할경우
		if(frm.orgnz_cd != null){
			//데이터가 1개일 경우
			if(frm.orgnz_cd.length == undefined){
				if(frm.orgnz_cd.checked == true){
					checkCnt = checkCnt + 1;
					var orgnzInfo = new Object();
					orgnzInfo.orgnz_cd  = frm.orgnz_cd[i].value;
					orgnzInfo.orgnz_nm  = frm.orgnz_nm[i].value;
					
					orgnzList.push(orgnzInfo);
				    opener.setReturnOrgnz(orgnzList);
					window.close();
				}
				if(checkCnt == 0){
					alert("<spring:message code='secfw.msg.ask.noSelect' />");
					return;
				}
			//데이터가 1개 이상일 경우
			}else{
				for(var i = 0 ; i < frm.orgnz_cd.length ; i++){
					if(frm.orgnz_cd[i].checked == true){
						checkCnt = checkCnt + 1;
						var orgnzInfo = new Object();
						orgnzInfo.orgnz_cd  = frm.orgnz_cd[i].value;
						orgnzInfo.orgnz_nm  = frm.orgnz_nm[i].value;
						
						orgnzList.push(orgnzInfo);
					}
				}
				if(checkCnt == 0){
					alert("<spring:message code='secfw.msg.ask.noSelect' />");
					return;
				}else{
					opener.setReturnOrgnz(orgnzList);
					window.close();
				}
			}		
		}else{
			alert("<spring:message code='secfw.page.field.alert.noDataInfo' />");
			window.close();
		}
	}

</script>
</head>
<body class="pop">
<form:form name="frm" id="frm" autocomplete="off">
	<!-- header -->
	<h1><spring:message code="clm.page.title.orgnz.popTitle" /></h1>
	<!-- //header -->
	<div class="pop_area">
		<div class="h_300">
		  <div class="pop_content">
			<!-- bigbox -->
				<div class="pop_Bbox">
					<div class="pop_Bbox_sub">
						<ul>
						<%
						if(resultList != null && resultList.size() > 0) {
							for(int idx = 0; idx < resultList.size(); idx++) {
								ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
						%>
							<li>
								<input type="checkbox" id="orgnz_cd" name="orgnz_cd" value="<%=lom.get("orgnz_cd")%>" <%if("Y".equals((String)lom.get("orgnz_chk"))){%>checked<%}%> /> 
								<input type="hidden" id="orgnz_nm" name="orgnz_nm" value="<%=lom.get("orgnz_nm")%>" /> 
								<%=lom.get("orgnz_nm")%>					
							</li>
						<%
							}
						}
						%>							
						</ul>
					</div>
				</div>
			<!--// bigbox -->
		  </div>
		</div>
	</div>
	<!-- //body -->
	<!--footer -->
	<div class="pop_footer">
		<div class="btn_wrap fR">
		 <span class="btn_all_w"><span class="save"></span><a href="javascript:pageAction()"><spring:message code='clm.page.button.confirm' /></a></span>
		 <span class="btn_all_w"><span class="cancel"></span><a href="#" onclick="javascript:self.close();"><spring:message code="common.page.field.OrgnzSearchList.close"/></a></span>
		</div>
	</div>
	<!-- //footer -->
</form:form>
</body>

</html>