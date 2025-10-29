<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파  일  명 : LawfirmRemitCert_l.jsp
 * 프로그램명 : 송금증 목록
 * 설      명 : 
 * 작  성  자 : 박병주
 * 작  성  일 : 2011.10
 */
--%>

<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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

	/**
	* 초기화면 로딩 
	*/
	$(document).ready(function(){
		
		var frm = document.frm;
		
		// calenda
		initCal("srch_start_dt");   //첫번재 날짜창 설정 : 변수는 해당 태그의 id값
		initCal("srch_end_dt");     //두번재 날짜창 설정 : 변수는 해당 태그의 id값
		
		// 로펌명
		frm.srch_lawfirm_nm.value = "<c:out value='${command.srch_lawfirm_nm}'/>";		
	
	});
	
	/**
	* 국가선택 POPUP function
	*/
	function NationPop(mode)
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
	* 페이징 function
	*/
	function goPage(pgNum){
		var frm = document.frm;

		viewHiddenProgress(true) ;
		
		frm.target = "_self";
		frm.action = "<c:url value='/las/lawservice/lawfirmRemitCert.do' />";
		frm.method.value = "listLawfirmRemitCert";
		frm.curPage.value = pgNum;
		
		frm.submit();
	}
  
	/**
	* 버튼 동작 부분
	*/ 
	function pageAction(flag){
		
		var frm = document.frm;		

		if(flag == "search"){
		    //유효성 체크
		    if(validateForm(frm)){
				viewHiddenProgress(true) ;
				frm.target = "_self";
				frm.action = "<c:url value='/las/lawservice/lawfirmRemitCert.do' />";
			    frm.method.value = "listLawfirmRemitCert";
				frm.curPage.value = "1";		
				frm.submit();
		    }
		}else if(flag == "new"){
			viewHiddenProgress(true) ;
			frm.target = "_self";
			frm.action = "<c:url value='/las/lawservice/lawfirmRemitCert.do' />";
		    frm.method.value = "forwardLawfirmRemitCertInsert";

			frm.submit();
		}
	}
	
	/**
	* Form check
	*/
	function checkForm(){
		var frm = document.frm;
		var check_tag = true;
		
/* 		if(frm.fst_cntrtday.value != ''){
			frm.fst_cntrtday.value = replace(frm.fst_cntrtday.value,"-","");
		}  */
		
		return check_tag;

	}
	
	/**
	* 송금증 리스트 가기
	*/
	function listRemitCertView(lawfirm_id){
		var frm = document.frm;
		
		viewHiddenProgress(true) ;
		frm.lawfirm_id.value = lawfirm_id;

		frm.target = "_self";
		frm.action = "<c:url value='/las/lawservice/lawfirmRemitCert.do' />";
		frm.method.value = "listRemitCert";
		
		frm.submit();		
	}
	
	/**
	* 메시지 처리
	*/
	function showMessage(msg) {
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
		}
	}

</script>
</head>
<body onLoad='showMessage("<c:out value='${returnMessage}'/>");'>
<!-- Calendar  -->
<div id='popCal' style='position: absolute; visibility: hidden; ridge; width: 10; z-index:1'>
<iframe name="popFrame" src="<c:url value='/secfw/popCalendar.do?method=goPopCalendar' />" frameborder="0" scrolling="no" width="196" height="188"></iframe></div>
<script event=onclick() for=document> 
    if(popCal.style.visibility == "visible"){
      popCal.style.visibility="hidden";
    }
</script>

<form:form name="frm" id='frm' method="post" autocomplete="off"> 
<input type="hidden" name="method"  value="" />
<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>" />
<input type="hidden" id="forward_url" name="forward_url"  value="" />
<input type="hidden" id="screen_type" name="screen_type"  value="list" />

<!-- key Form -->
<input type="hidden" id="nation" name="nation"  value="" />
<input type="hidden" id="srch_nation" name="srch_nation"  value="<c:out value='${command.srch_nation}'/>" />
<input type="hidden" id="lawfirm_id" name="lawfirm_id"  value="" />

<div id="wrap">
<!-- container -->
<div id="container">
<!-- Location -->
<div class="location"><IMG SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
<!-- //Location -->	
	<!-- title -->
	<div class="title">
		<h3><spring:message code="las.page.field.lawservice.remitcert"/></h3>
	</div>
	<!-- //title -->
	<!-- content -->
	<div id="content">
	
				<!--search-->        
    <div class="search_box">
				<div class="search_box_inner">
					<table class="search_tb">
						<colgroup>
							<col/>
							<col width="75px"/>
							</colgroup>
							<tr>
							<td>
							<table class="search_form">
								<colgroup>
									<col width="15%"/>
									<col width="30%"/>
									<col width="15%"/>
									<col width="40%"/>
								</colgroup>
								<tr>
									<th><spring:message code="las.page.field.lawservice.lawfirmnm" /></th>
									<td>
										<input id="srch_lawfirm_nm" name="srch_lawfirm_nm" value="<c:out value='${command.srch_reg_nm}'/>" type="text" style="width:150px;" alt="<spring:message code="las.page.field.lawservice.lawfirmnm" />" maxlength="100"/>
									</td>
									<th><spring:message code="las.page.field.lawservice.contractday" /></th>
									<td>
										<input type="text" name="srch_start_dt" id="srch_start_dt" value="<c:out value='${command.srch_start_dt}'/>"  class="text_calendar02" readonly />
										 ~
										  <input type="text" name="srch_end_dt" id="srch_end_dt" value="<c:out value='${command.srch_end_dt}'/>" class="text_calendar02" readonly />			
									</td>
							  </tr>
							   <tr>
									<th><spring:message code="las.page.field.lawservice.expertarea" /></th>
									<td>
										<input id="srch_mainftre_estmt" name="srch_mainftre_estmt" value="<c:out value='${command.srch_mainftre_estmt}'/>" type="text" style="width:150px;" alt="<spring:message code="las.page.field.lawservice.expertarea" />" maxlength="2000" />
									</td>
									<th><spring:message code="las.page.field.lawservice.nation" /></th>
									<td>
										<input id="srch_nation_nm"  name="srch_nation_nm" value="<c:out value='${command.srch_nation_nm}'/>" type="text" style="width:150px;" onclick="javascript:NationPop()" readoly />
										&nbsp;<a href="javascript:NationPop('srch');"><spring:message code="las.page.field.lawservice.select" /></a>
									</td>
							  </tr>
							</table>
						</td>
						<td class="vb tC"><a href="javascript:pageAction('search');"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="las.page.button.lawservice.read" />"/></a></td>
						</tr>
					</table>
				</div>
	  </div>
			<!--//search-->


		<!-- list -->
		<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
		  <colgroup>
				<col width="7%" />
				<col width="63%" />
				<col width="15%" />
				<col width="15%" />
			</colgroup>
		  <thead>
			<tr>
			  <th><spring:message code="las.page.field.lawservice.order" /></th>
			  <th><spring:message code="las.page.field.lawservice.lawfirm" /></th>
			  <th><spring:message code="las.page.field.lawservice.nation" /></th>
			  <th><spring:message code="las.page.field.lawservice.contractday" /></th>
			</tr>
		  </thead>
		  <tbody>
			<c:choose>
						<c:when test="${pageUtil.totalRow > 0}">
							<c:forEach var="list" items="${resultList}">
							<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
							    <td><c:out value='${list.rn}'/></td>
								<td class="tL">
									<div style='width:280px;overflow:hidden;text-overflow:ellipsis'>
						            	<nobr><a href='javascript:listRemitCertView("<c:out value='${list.lawfirm_id}'/>");'>
						          		<c:out value='${list.lawfirm_nm}'/></a></nobr>
						          	</div>
					            </td>
					            <td class="tC"><c:out value='${list.nation_nm}'/></td>
					            <td class="tC">
					            	<fmt:parseDate value="${list.fst_cntrtday}" var="dateFmt" pattern="yyyymmdd"/>
					            	<fmt:formatDate value="${dateFmt}" pattern="yyyy-mm-dd"/>
					            </td>
							</tr>
							</c:forEach>
					    </c:when>
					    <c:otherwise>
							<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
								<td colspan="4" align="center"><spring:message code="las.msg.succ.noResult" /></td>
							</tr>
					    </c:otherwise>
				</c:choose>
		  </tbody>
		  </table>
		<!-- //list -->
		<!-- pagination -->
		<div class="pagination">
			<%=pageUtil.getPageNavi(pageUtil, "") %>
		</div>
		<!-- //pagination -->
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
