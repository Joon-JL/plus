<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.bbs.dto.BBSForm" %>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파     일     명 : BBSList.jsp
 * 프로그램명 : 게시내역  목록
 * 설               명 : 
 * 작     성     자 : 금현서
 * 작     성     일 : 2009.11
 */
--%>
<%
	ArrayList resultList = (ArrayList)request.getAttribute("resultList");
	//BBSForm command = (BBSForm)request.getAttribute("command");
	//처리결과 메시지
    String returnMessage = (String)request.getAttribute("returnMessage");
	//메뉴네비게이션 스트링
	String    menuNavi          = (String)request.getAttribute("secfw.menuNavi");
	
	//PageUtil 객체를 읽어들임
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");

	//게시판 옵션
	ListOrderedMap bbsOptionMap = (ListOrderedMap)request.getAttribute("bbsOption");
	
	int newHoldTerm   = 1;
	
	// 2011.03.31 안홍순 임시로 주석처리
	//if(bbsOptionMap != null) {
	//	newHoldTerm       = ((BigDecimal)bbsOptionMap.get("new_hold_term")).intValue();
	//}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="secfw.page.title.bbs.BBSList" /></title>

<LINK href="<c:url value='/style/secfw/common.css' />" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript">
<!--

/**
	* 페이징 function
	*/
	function goPage(pgNum){
		var frm = document.frm;

		frm.target = "_self";
		frm.action = "<c:url value='/secfw/bbs.do' />";
		frm.method.value = "listBBSDetailPage";
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
			frm.action = "<c:url value='/secfw/bbs.do' />";
		    frm.method.value = "listBBSDetailPage";
		    
			frm.doSearch.value = "Y";
			frm.curPage.value = "1";

			frm.submit();

		} else if(flag == "new"){	
			frm.target = "_self";
			frm.action = "<c:url value='/secfw/bbs.do' />";
		    frm.method.value = "goInsertBBSDetail";

			frm.submit();
		}
	}

	/**
	* 상세내역 조회
	*/
	function detailView(bbs_id, notice_id){
		var frm = document.frm;

	    frm.bbs_id.value    = bbs_id;
	    frm.notice_id.value = notice_id;

	    frm.doSearch.value = "Y";
		
		frm.target = "_self";
		frm.action = "<c:url value='/secfw/bbs.do' />";
		frm.method.value = "detailBBSDetail";

		frm.submit();		
	}

	/**
	* 메시지 처리
	*/
	function showMessage(msg){

		if(msg != null && msg.length > 1) {
			alert(msg);
		}
	}

	/**
	* - Progress Image 
	*/	
	$(function(){
		var pageUrl = "<c:url value='/WEB-INF/jsp/secfw/common/ProgressLayer.jsp' />";
		$('#insec').load(pageUrl);
	});	
//-->

</script>

</head>
<body onLoad="showMessage('<%=returnMessage %>')">
<!-- progress -->
<div id="insec" style="position:absolute; z-index:100; left:expression((body.clientWidth-390)/2); top:expression((body.clientHeight-114)/2);"></div>
<!-- //progress -->
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
<!-- ****************************** Form Setting ************************************ -->
<form:form name="frm" method="post" autocomplete="off">
	<!-- 페이지 공통(리스트성조회) -->
	<input type="hidden" name="method"   value="">
	<input type="hidden" name="menu_id"  value="${command.menu_id}">
	
	<input type="hidden" name="doSearch" value="${command.doSearch}">
	<input type="hidden" name="curPage" value="${command.curPage}">
	
	<!-- 게시판 관련 -->
	<input type="hidden" name="bbs_id"    value="${command.bbs_id}">
	<input type="hidden" name="notice_id" value="${command.notice_id}">
<!-- //**************************** Form Setting **************************** -->
		<!-- Location -->
		<div class="location"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- Title -->
		<h3><spring:message code="secfw.page.title.bbs.BBSMngList" /></h3>
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
					<col/>
					</colgroup>
					<tr>
		              <th><spring:message code="secfw.page.field.bbs.period"/> :</th>
		              <td>
		              	<input type="text" name="srch_start_notice_ymd" id="srch_start_notice_ymd"  class="input_calendar" value="<c:choose><c:when test='${!empty command.srch_start_notice_ymd}'>${command.srch_start_notice_ymd}</c:when><c:otherwise><%=DateUtil.formatDate(DateUtil.addDays(DateUtil.today(),-30),"-") %></c:otherwise></c:choose>" /><img src="<c:url value='/images/secfw/ico/ico_input_calendar.gif' />" onClick="popFrame.fPopCalendar(srch_start_notice_ymd,srch_start_notice_ymd,popCal)" class="cp"> 
			            &nbsp;~&nbsp;
		                <input type="text" name="srch_end_notice_ymd" id="srch_end_notice_ymd"  class="input_calendar" value="<c:choose><c:when test='${!empty command.srch_end_notice_ymd}'>${command.srch_end_notice_ymd}</c:when><c:otherwise><%=DateUtil.formatDate(DateUtil.today(),"-") %></c:otherwise></c:choose>" /><img src="<c:url value='/images/secfw/ico/ico_input_calendar.gif' />" onClick="popFrame.fPopCalendar(srch_end_notice_ymd,srch_end_notice_ymd,popCal)" class="cp">
		              </td>
		                <th><spring:message code="secfw.page.field.bbs.classification"/></th>
		                <td>
		                	<select name="srch_cntnt_type" id="srch_cntnt_type" class="select">
		                  		<option value='title' <c:if test="${command.srch_cntnt_type eq 'title'}">selected</c:if>><spring:message code="secfw.page.field.bbs.title"/></option>
		                  		<option value='user'  <c:if test="${command.srch_cntnt_type eq 'user'}">selected</c:if>><spring:message code="secfw.page.field.bbs.writer"/></option>
		                	</select>
		                </td>
		                <td>
		              		<input type="text" name="srch_cntnt" id="srch_cntnt"  class="text"  value="${command.srch_cntnt}" OnKeyDown="if(event.keyCode==13) { pageAction('search'); }"/>
		              	</td>
		              	<td>
		              		<a href="javascript:pageAction('search');" class="bt_search"><span><spring:message code="secfw.page.field.bbs.search2"/></span></a>
		              	</td>
					</tr>
				</table>
			</div>
		</div>
		<br class="space_15"/>
		<!-- //Search -->

		<!-- Total -->
		<div class="total"><p>Total : <strong><%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%></strong></p></div>
		<!-- Button -->
		<div class="bt_content_top"> 
			<a href="javascript:pageAction('new')" class="bt_fn"><span><spring:message code="secfw.page.button.new" /><img src="<c:url value='/images/secfw/ico/ico_tri.gif' />"></span></a>
		</div>
		<!-- //Button -->

		<!-- List -->
		<table class="basic_list">
	          <colgroup>
	          <col align="center" width="30px"/>
	          <col align="left" />
	          <col align="center" width="180px"/>
	          <col align="center" width="70px"/>
	          <col align="center" width="40px"/>
	          </colgroup>
	          <tr>
	            <th><spring:message code="secfw.page.field.bbs.sequence"/></th>
	            <th><spring:message code="secfw.page.field.bbs.title"/></th>
	            <th><spring:message code="secfw.page.field.bbs.writer"/></th>
	            <th><spring:message code="secfw.page.field.bbs.postDate"/></th>
	            <th><spring:message code="secfw.page.field.bbs.search2"/></th>
	          </tr>
	<%
		if(pageUtil.getTotalRow() > 0){
			int idx = (pageUtil.getThisPage() -1)*pageUtil.getRowPerPage()+1;
			    for(idx=0;idx < resultList.size();idx++){
			    
			    	ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
			    	
			    	int noticeDepth  = ((BigDecimal)lom.get("notice_depth")).intValue();
			    	String urgencyYn = (String)lom.get("urgency_yn");
			    	String regYMD    = StringUtil.removeChar((String)lom.get("reg_dt"),"-");
	%>

	          <tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
	            <td><%=lom.get("rn") %></td>
	            <td><div style='width:280px;overflow:hidden;text-overflow:ellipsis'>
	            		<nobr><a href="javascript:detailView('<%=lom.get("bbs_id") %>','<%=lom.get("notice_id") %>');">
<%
	// 댓글표시
	if(noticeDepth > 1) {
		String nbsp ="";
		for(int i=2; i<noticeDepth; i++) {
			nbsp = nbsp + "&nbsp;&nbsp;&nbsp;&nbsp;";
		}
%>      
		<%=nbsp %><img src="<c:url value='/images/secfw/ico/ico_list_reply.gif' />">
<%
	}
%>
<%	if(Integer.parseInt(regYMD) >= Integer.parseInt(DateUtil.addDays(DateUtil.getTime("yyyyMMdd"),-newHoldTerm))) { //새글이미지 (등록일, 등록다음날까지 새글)%> 
	<img src="<c:url value='/images/secfw/ico/ico_new.gif' />">
<% } %>
<%	if("Y".equals(urgencyYn)) { //긴급이미지 %> 
	<img src="<c:url value='/images/secfw/ico/ico_emergency.gif' />"> 
<% } %>
	        		    	<%=lom.get("notice_title") %>
	            		</a></nobr>
	            	</div>
	            </td>
	            <td><div style='width:180px;overflow:hidden;text-overflow:ellipsis'><nobr><%=lom.get("user_nm") + "/" + lom.get("dept_nm") + "/" + lom.get("comp_nm") %></nobr></div></td>
	            <td><%=DateUtil.formatDate((String)lom.get("notice_ymd"), "-") %></td>
	            <td><%=lom.get("ref_cnt") %></td>
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
<script language="javascript">
$(function(){
	$('#insec').css({display:"none"});
});	
</script>
</html>
