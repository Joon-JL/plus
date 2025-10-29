<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
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
 * 파  일  명 : LawServiceSearch_p.jsp
 * 프로그램명 : 법무시스템 : 로펌서비스 검색 품의양식 팝업
 * 설      명 : 로펌서비스 검색 품의양식
 * 작  성  자 : 박병주
 * 작  성  일 : 2011.10
 */
 --%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/las/new_style.js" type="text/javascript' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript">
<!--
/**
* 메시지 처리
*/
function showMessage(msg) {
	if( msg != "" && msg != null && msg.length > 1 ) {
		alert(msg);
	}
}

//-->
</script>
</head>
<body onLoad='showMessage("<c:out value='${returnMessage}'/>");'>
<form:form name="frm" id="frm" autocomplete="off">
<input type="hidden" id="method" name="method" value="" />
<input type="hidden" id="menu_id" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" id="curPage" name="curPage" value="<c:out value='${command.curPage}'/>" />

  <div class="contents">
   <span class="fR red_b" style="padding-bottom:7px">* <spring:message code="las.page.field.lawservice.classified"/></span>
    <table class="list_basic_s tr_nobg" border="1" >
<colgroup>
            <col width="110px" />
            <col width="115px" />
            <col width="100px" />
            <col width="40px" />
            <col width="100px" />
            <col width="85px" />
            <col width="75px" />
            <col width="65px" />           
      </colgroup>
          <thead>
            <tr>
              <th><spring:message code="las.page.field.lawservice.total"/></th>
              <th><spring:message code="las.page.field.lawservice.eventnm" /></th>
              <th><spring:message code="las.page.field.lawservice.lawfirm" /></th>
              <th>&nbsp;</th>
              <th><spring:message code="las.page.field.lawservice.claimamt"/></th>
              <th>INVOICE</th>
              <th><spring:message code="las.page.field.lawservice.srvfromto"/></th>
              <th><spring:message code="las.page.field.lawservice.plndremitday"/></th>
            </tr>
          </thead>
          <tbody>
			<c:choose>
						<c:when test="${!empty resultList}">
							<c:forEach var="list" items="${resultList}">
							<tr>
								<td class="tC"><c:out value='${list.dept_nm}'/></td>
					            <td class="tC"><c:out value='${list.event_nm}'/></td>
					            <td class="tC"><c:out value='${list.lawfirm_nm}'/></td>
					            <td class="tC"><c:out value='${list.crrncy_unit}'/></td>
					            <td class="tR"><fmt:formatNumber value='${list.claim_amt}' pattern="#,#00.00#"/></td>
					            <td class="tC"><c:out value='${list.invoice_no}'/></td>
					            <td class="tC"><c:out value='${list.srvcday}'/></td>
					            <td class="tC"><c:out value='${list.remitplndday}'/></td>
							</tr>
							</c:forEach>
					    </c:when>
					    <c:otherwise>
							<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
								<td colspan="8" align="center"><spring:message code="las.msg.succ.noResult" /></td>
							</tr>
					    </c:otherwise>
				</c:choose>
		    </tbody>
    </table>
  </div>
  </form:form>
</body>
</html>