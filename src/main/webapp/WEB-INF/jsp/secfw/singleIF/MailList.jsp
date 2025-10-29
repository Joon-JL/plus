<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파     일     명 : MailList.jsp
 * 프로그램명 : 메일 목록
 * 설               명 : 
 * 작     성     자 : 금현서
 * 작     성     일 : 2009.11
 */
--%>
<%

	//메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");

	//목록데이타
	ArrayList resultList = (ArrayList)request.getAttribute("resultList");

	//처리결과 메시지
    String returnMessage = (String)request.getAttribute("returnMessage");
	
	//PageUtil 객체
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
%>
<html>
<head>
<meta charset="utf-8" />

<title><spring:message code="secfw.page.title.singleIF.MailList" /></title>

<link href="<c:url value='/style/secfw/common.css' />" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript">
<!--

	/**
	* 페이징 function
	*/
	function goPage(pgNum){
		var frm = document.frm;

		frm.target = "_self";
		frm.action = "<c:url value='/secfw/mail.do' />";
		frm.method.value = "listMail";
		frm.doSearch.value= "Y";
		frm.curPage.value = pgNum;
		frm.submit();
	}

	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
	
		if(flag == "search"){
			
			frm.target = "_self";
			frm.action = "<c:url value='/secfw/mail.do' />";
		    frm.method.value = "listMail";
		    
			frm.doSearch.value = "Y";
			frm.curPage.value = "1";

			frm.submit();

		} else if(flag == "new"){	
		    PopUpWindowOpen('', 850, 750, false);
			frm.target = "PopUpWindow";

			frm.action = "<c:url value='/secfw/mail.do' />";
			frm.method.value = "goInsertMail";

			frm.submit();
		}
	}

	/**
	* 상세내역 조회
	*/
	function detailView(module_id, mis_id){
		var frm = document.frm;

	    frm.module_id.value = module_id;
	    frm.mis_id.value    = mis_id;

	    PopUpWindowOpen('', 850, 750, false);
		frm.target = "PopUpWindow";

		frm.action = "<c:url value='/secfw/mail.do' />";
		frm.method.value = "detailMail";

		frm.submit();
	}

	/**
	* 메시지 처리
	*/
	function showMessage(msg){

		if(msg != null && msg.length > 1) {
			alert(msg);
		}5
	}

	function reload() {
		pageAction('search');
	}
//window.name='secfw.BBSMng';	
//-->
</script>

</head>
<body onLoad="showMessage('<%=returnMessage %>')">
<div class="contentWrap">
	<div class="content_area">
<!-- **************************** Calendar 관련 추가 영역 **************************** -->
<div id='popCal' style='position: absolute; visibility: hidden; ridge; width: 10'>
<iframe name="popFrame" src="<c:url value='/secfw/popCalendar.do?method=goPopCalendar' />" frameborder="0" scrolling="no" width=196 height=188></iframe></div>
<script event=onclick() for=document> 
if(popCal.style.visibility == "visible"){
  popCal.style.visibility="hidden";
}
</script>
<!-- //**************************** Calendar 관련 추가 영역 **************************** -->
	
<!--**************************** Form Setting ****************************-->
<form:form name="frm" method="post" autocomplete="off"> 
	<!-- 페이지 공통 -->
	<input type="hidden" name="method"   value="">
	
	<input type="hidden" name="doSearch" value="N">
	<input type="hidden" name="curPage" value="${command.curPage}">
	
	<!-- 페이지별  -->
	<input type="hidden" name="module_id" value="">
	<input type="hidden" name="mis_id" value="">
<!-- //**************************** Form Setting ****************************-->
		<!-- Location -->
		<div class="location"><%=menuNavi %></div>
		<!-- Title -->
		<h3><spring:message code="secfw.page.title.singleIF.MailList" /></h3>
		<!-- Search -->
		<div class="search_box">
			<div class="search_box_in">
				<table class="search_in">
					<colgroup>
					<col/>
					<col/>
					<col/>
					<col/>
					<col/>
					</colgroup>
					<tr>
			              <!-- 게시판명 -->
			              <th><spring:message code="secfw.page.field.singleIF.period"/> :</th>
			          	  <td>
		              		<input type="text" name="srch_start_ymd" id="srch_start_ymd"  class="input_calendar" value="<c:choose><c:when test='${!empty command.srch_start_ymd}'>${command.srch_start_ymd}</c:when><c:otherwise><%=DateUtil.formatDate(DateUtil.addDays(DateUtil.today(),-30),"-") %></c:otherwise></c:choose>" /><img src="<c:url value='/images/secfw/ico/ico_input_calendar.gif' />" onClick="popFrame.fPopCalendar(srch_start_ymd,srch_start_ymd,popCal)" class="cp"> 
			            	&nbsp;~&nbsp;
		                	<input type="text" name="srch_end_ymd" id="srch_end_ymd"  class="input_calendar" value="<c:choose><c:when test='${!empty command.srch_end_ymd}'>${command.srch_end_ymd}</c:when><c:otherwise><%=DateUtil.formatDate(DateUtil.today(),"-") %></c:otherwise></c:choose>" /><img src="<c:url value='/images/secfw/ico/ico_input_calendar.gif' />" onClick="popFrame.fPopCalendar(srch_end_ymd,srch_end_ymd,popCal)" class="cp">
		              	</td>
						<!-- 상태 -->
			              <th><spring:message code="secfw.page.field.singleIF.status"/> :</th>
			              <td>
			                <select name="srch_status" id="srch_status" class="select">
			                  <option value=""  <c:if test="${command.srch_status eq ''}">selected</c:if>><spring:message code="secfw.page.field.common.total" /></option>
			                  <option value="T" <c:if test="${command.srch_status eq 'T'}">selected</c:if>><spring:message code="secfw.page.field.singleIF.tmp"/></option>
			                  <option value="0" <c:if test="${command.srch_status eq '0'}">selected</c:if>><spring:message code="secfw.page.field.singleIF.pending"/></option>
			                  <option value="1" <c:if test="${command.srch_status eq '1'}">selected</c:if>><spring:message code="secfw.page.field.singleIF.deliveryCompl"/></option>
			                  <option value="E" <c:if test="${command.srch_status eq 'E'}">selected</c:if>><spring:message code="secfw.page.field.singleIF.deliveryFailed"/></option>
			                </select>
			              </td>
			              <td>
			                <!-- 조회 -->
			              	<a href="javascript:pageAction('search');" class="bt_search"><span><spring:message code="secfw.page.button.search" /></span></a>
			              </td>
					</tr>
				</table>
			</div>
		</div>
		<!-- //Search -->
		
		<!-- Total -->
		<div class="total"><p>Total : <strong><%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%></strong></p></div>
		<!-- Button -->
		<!-- div class="bt_content_top"><a href="javascript:pageAction('new')" class="bt_fn"><span><spring:message code="secfw.page.button.new" /><img src="<c:url value='/images/secfw/ico/ico_tri.gif' />"></span></a></div -->
		<!-- //Button -->

		<!-- //Clear -->
		<br>
		
		<!-- List -->
		<div class="dscrollx" style="height:293px;">
			<table class="basic_list mz fix" style="width:800px;" >
	          <colgroup>
	          <col align="center" width="40px"/>
	          <col align="left" />
	          <col align="left"   width="220px"/>
	          <col align="center" width="100px"/>
	          <col align="center" width="120px"/>
	          </colgroup>
	          <tr>
	            <th><spring:message code="secfw.page.field.common.no" /></th><!-- 순번 -->
	            <th><spring:message code="secfw.page.field.singleIF.title"/></th><!-- 제목 -->
	            <th><spring:message code="secfw.page.field.singleIF.senderEmailAddr"/></th><!-- 발신자메일주소 -->
	            <th><spring:message code="secfw.page.field.singleIF.statusVal"/></th><!-- 상태값 -->
	            <th><spring:message code="secfw.page.field.singleIF.createDate"/></th><!-- 생성일자 -->
	          </tr>
	<%
		if(pageUtil.getTotalRow() > 0){
			int idx = (pageUtil.getThisPage() -1)*pageUtil.getRowPerPage()+1;
			    for(idx=0;idx < resultList.size();idx++){
			    
			    	ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
			    	
	%>
	          <tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
	            <td><%=lom.get("rn") %></td>
	            <td class="overflow"><%=lom.get("subject") %></td>
	            <td><%=lom.get("sender_mail_addr") %></td>
	            <td><%=lom.get("status") %></td>
	            <td><%=lom.get("create_date") %></td>
	          </tr>

	<%
			    }
		} else { // 조회된 데이타가 없을경우
	%>
	          <tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
	            <td colspan="5"><spring:message code="secfw.msg.succ.noResult" /></td>
	          </tr>
	<%
		}
	%>
	        </table>
        </div>
		<!-- //List -->
		<!-- Paging  -->
      <div class="paging">
		<%=pageUtil.getPageNavi(pageUtil, "") %>
      </div>
		<!-- // Paging -->
	</form:form>
	<!-- Footer -->
	<address>
		<spring:message code="secfw.page.copyright.content.bottom" />
	</address>
	<!-- //Footer -->
	</div>
</div>
</body>
</html>