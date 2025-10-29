<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.admin.dto.FaqForm" %>

<%@page import="com.sds.secframework.common.util.Token"%>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%--
/**
 * 파  일  명 : Faq_u.jsp
 * 프로그램명 : FAQ 수정
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
 --%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	FaqForm command = (FaqForm)request.getAttribute("command");
	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("lom"); 
	List orgnzList = (List)request.getAttribute("orgnzList");	
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
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script language="javascript">
<%if(orgnzList != null && orgnzList.size()>0){%>
var index = <%=orgnzList.size()%>;
<%}else{%>
var index = 0;
<%}%>
	

	$(document).ready(function(){
		//공지대상1 활성화하기
		<%if("C01602".equals((String)lom.get("rd_trgt1"))){%>
			$("#searchBtn").attr('style', 'padding-left:5px;padding-right:5px;display:');
			$("#returnOrgnzCds").attr('style', 'display:');
		<%}%>
		getCodeSelectByUpCd("frm", "cont_type", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=&combo_grp_cd=C015&combo_type=S&combo_del_yn=N&combo_selected=<%=lom.get("cont_type")%>');
		getCodeSelectByUpCd("frm", "rd_trgt1", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=&combo_grp_cd=C016&combo_type=S&combo_del_yn=N&combo_selected=<%=lom.get("rd_trgt1")%>');
	});
	
	$(function() {
		$("#rd_trgt1").change(function() {
			var val1 = $("#rd_trgt1").val();
			
			if(val1 == "C01602"){
				$("#searchBtn").attr('style', 'padding-left:5px;padding-right:5px;display:');
				$("#returnOrgnzCds").attr('style', 'display:');
			}else{
				$('.orgnz_span').remove("");
				$("#searchBtn").attr('style', 'padding-left:5px;padding-right:5px;display:none');
				$("#returnOrgnzCds").attr('style', 'display:none');
			}

		});
	
	});
	
	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
		
		frm.target = "_self";
		frm.action = "<c:url value='/clm/admin/faq.do' />";
		//저장
		if(flag == "insert"){
		    frm.method.value = "modifyFaq";
		    
		    //나모웹에디터 관련
		    frm.cont.value = frm.wec1.MIMEValue;
		    frm.cont_en.value = frm.wec2.MIMEValue;
			//공지대상1 체크
		    if(frm.rd_trgt1.value == "C01602"){
		    	if(frm.orgnz_cds == null){
		    		alert("<spring:message code='clm.page.alert.orgnz.noSelect' />");
		    		return;
		    	}		    	
		    }
		    //Validation Check2
		    if(validateForm(frm)){
				if(confirm("<spring:message code='secfw.msg.ask.update' />")){
					viewHiddenProgress(true) ;
			    	frm.submit();
			    }
		    }
		//초기화
		} else if(flag == "cancel"){	
			if(confirm("<spring:message code='secfw.msg.ask.cancel' />")){
				viewHiddenProgress(true) ;
			    frm.method.value = "detailFaq";
				frm.submit();			
			}
		}
	}

	//단위조직 팝업 호출
	function customerClick(){
		var frm = document.frm;
		
		//viewHiddenProgress(true);

		PopUpWindowOpen('', 640, 300, true);
		frm.target = "PopUpWindow";
		frm.action = "<c:url value='/common/clmsCom.do' />";
		frm.method.value = "forwardOrgnzPop";
		//frm.srch_up_orgnz_cd.value = "B00000004";
		frm.submit();
	}
	
	//공지대상 삭제
	function orgnzCdDel(id){
		$("#"+id).remove();
	}
    
	//단위조직 팝업 리턴 값.
	function setReturnOrgnz(orgnzList){
		var frm = document.frm;
		
		viewHiddenProgress(false);

		if(orgnzList.length > 0){
			//전체 조직단위 삭제
			$('.orgnz_span').remove("");
			//선택된 데이터 출력
			for(var idx=0; idx<orgnzList.length; idx++) {
				var obj = orgnzList[idx];
				
				var returnVal = "";
				returnVal += "<span class=\"orgnz_span\" style=\"padding-right:5px;\" id=\"orgnz_"+index+"\">"+obj.orgnz_nm;
		    	returnVal += "<a href=\"javascript:orgnzCdDel('orgnz_"+index+"');\" ><img SRC=\"<%=IMAGE%>/icon/cancel_new.gif\"/></a>";
		    	returnVal += "<input type=\"hidden\" id=\"orgnz_cds"+index+"\" name=\"orgnz_cds\" value=\"" + obj.orgnz_cd + "\" />";
		    	returnVal += "</span>";
			    
			    $('#returnOrgnzCds').append(returnVal);
			    index = index + 1;
			}
		}
	}
</script>

</head>
<body>
<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">

<!-- search form -->
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
<input type="hidden" name="srch_cont_type" value="<c:out value='${command.srch_cont_type}'/>" />
<input type="hidden" name="srch_keyword"    value="<c:out value='${command.srch_keyword}'/>" />
<input type="hidden" name="srch_keyword_content"    value="<c:out value='${command.srch_keyword_content}'/>" />

<!-- key form-->
<input type="hidden" name="seqno"	value="<c:out value='${command.seqno}'/>" />
<input type="hidden" name="cont"	value="<c:out value='${lom.cont}' />" />
<input type="hidden" name="cont_en"	value="<c:out value='${lom.cont_en}' />" />

<!-- 단위조직 팝업 form-->
<input type="hidden" name="srch_up_orgnz_cd"	value="" /><!-- 상위조직코드 -->
<!-- // **************************** Form Setting **************************** -->
<div id="wrap">
	<!-- container -->
	<div id="container">	

		<!-- Location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->

		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.faq.modifyTitle" /></h3>
		</div>
		<!-- //title -->

		<!-- content -->
		<div id="content">
			<!-- view -->
			<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
				<colgroup>
					<col width="100" />
					<col width="720" />
				</colgroup>
				<tbody>
					<tr>
						<th><spring:message code="clm.page.field.faq.contType" /><span class="astro">*</span></th>
						<td>
							<select name="cont_type" id="cont_type" required>
							</select>
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.faq.rdTrgt1" /><span class="astro">*</span></th>
						<td>
							<select name="rd_trgt1" id="rd_trgt1" required alt="<spring:message code="clm.page.field.faq.rdTrgt1" />">
							</select>
							<span id="searchBtn" style="padding-left:5px;padding-right:5px;display:none"><a href="javascript:customerClick();" ><img src="<%=IMAGE%>/btn/btn_wrap_fr_reference.gif" alt="<spring:message code="clm.page.msg.common.search" htmlEscape="true"/>" /></a></span>
							<span id="returnOrgnzCds" style="display:none">
						<%if("C01602".equals((String)lom.get("rd_trgt1"))){
							if(orgnzList != null && orgnzList.size()>0){ 
								for(int i=0; i<orgnzList.size();i++){
									ListOrderedMap orgnz = (ListOrderedMap)orgnzList.get(i);
						%>
							<span class="orgnz_span" style="padding-right:5px;" id="orgnz_<%=i%>"><%=(String)orgnz.get("orgnz_nm")%>
								<a href='javascript:orgnzCdDel("orgnz_<%=i%>");' ><img SRC='<%=IMAGE%>/icon/cancel_new.gif'/></a>
								<input type="hidden" id="orgnz_cds<%=i%>" name="orgnz_cds" value="<%=(String)orgnz.get("orgnz_cd")%>" />
							</span>						
						<%
								}
							}
						}
						%>
							</span>
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.faq.titleKr" /><span class="astro">*</span></th>
						<td>
							<input type="text" name="title" id="title" required alt="<spring:message code="clm.page.field.faq.titleKr" />" maxLength="300" class="text_full" style="width:90%" value="<%=lom.get("title")%>" />
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.faq.titleEn" /><span class="astro">*</span></th>
						<td>
							<input type="text" name="title_en" id="title_en" required alt="<spring:message code="clm.page.field.faq.titleEn" />" maxLength="300" class="text_full" style="width:90%" value="<%=lom.get("title_en")%>" />
						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.page.field.faq.contKr" /><span class="astro">*</span></th>
						<td>
							<%
		  					request.setAttribute("wec_set_id","wec1"); 
		  					request.setAttribute("wec_mode","EDIT");	
		  					request.setAttribute("wec_com_script_yn","Y");
		  					%>
							<%@ include file="/WEB-INF/jsp/secfw/common/NamoAutoInclude.jspf"%>
						</td>
					</tr>
					<tr class="end">
						<th><spring:message code="clm.page.field.faq.contEn" /><span class="astro">*</span></th>
						<td>
							<%
		  					request.setAttribute("wec_set_id","wec2"); 
		  					request.setAttribute("wec_mode","EDIT");	
		  					request.setAttribute("wec_com_script_yn","Y");
		  					%>
							<%@ include file="/WEB-INF/jsp/secfw/common/NamoAutoInclude.jspf"%>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- //view -->
	
			<div class="btn_wrap">
		<%if(command.isAuth_modify()){ %>
				<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('insert');"><spring:message code="secfw.page.button.insert" /></a></span>
		<%} %>
				<span class="btn_all_w"><span class="cancel"></span><a href="javascript:pageAction('cancel');"><spring:message code="secfw.page.button.cancel" /></a></span>
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
<!-- 나모 웹에디터 관련 추가 -->
<SCRIPT language="javascript" FOR="wec1" EVENT="OnInitCompleted()">
	var wecObj1 = document.wec1;
	wecObj1.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	wecObj1.SetDefaultFontSize("9");
	wecObj1.EditMode = 1;
	wecObj1.Value = document.frm.cont.value;
</SCRIPT>
<SCRIPT language="javascript" FOR="wec2" EVENT="OnInitCompleted()">
	var wecObj2 = document.wec2;
	wecObj2.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	wecObj2.SetDefaultFontSize("9");
	wecObj2.EditMode = 1;
	wecObj2.Value = document.frm.cont_en.value;
</SCRIPT>
</html>
