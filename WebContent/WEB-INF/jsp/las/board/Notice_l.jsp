<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.las.board.dto.NoticeForm" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파  일  명 : Notice_l.jsp
 * 프로그램명 : 법무공지, 법원송달문서공지, 시스템FAQ - 리스트
 * 설      명 : 
 * 작  성  자 : 한지훈
 * 작  성  일 : 2011.08.12
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
	NoticeForm command = (NoticeForm)request.getAttribute("command");
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	List resultList = (List)request.getAttribute("resultList");
%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script language="javascript">

	/**
	* 달력 setting
	*/
	$(document).ready(function(){
	    initCal("srch_start_dt");   
	    initCal("srch_end_dt","20110804083441819_0000000160");  
	}); 

	function search(){
		goPage(1);
	}

	function goPage(page) {
		
		var frm = document.frm;

		var startDt = frm.srch_start_dt.value;
		var endDt = frm.srch_end_dt.value;

		if(startDt>endDt){
			alert("<spring:message code='clm.msg.alert.board.srchAnnceDt'/>");
			frm.srch_start_dt.focus();
			return;
		}

		frm.curPage.value = page;
		<c:choose>
			<c:when test="${command.annce_knd=='MEMO'}">
				frm.method.value = "listLawNotice";
			</c:when>
			<c:when test="${command.annce_knd=='ANNO'}">
				frm.method.value = "listLawNotice";
			</c:when>
			<c:when test="${command.annce_knd=='DEPT'}">
				frm.method.value = "listDispatchNotice";
			</c:when>
			<c:when test="${command.annce_knd=='FAQ'}">
				frm.method.value = "listSysFaq";
			</c:when>
		</c:choose>

		viewHiddenProgress(true) ;
		
		frm.action = "<c:url value='/las/board/notice.do' />";
		frm.target = "_self";
		frm.submit();
	}
  
	function goInsertForm() {
		
		var frm = document.frm;
		
		<c:choose>
			<c:when test="${command.annce_knd=='MEMO'}">
				frm.method.value = "forwardInsertLawNotice";
			</c:when>
			<c:when test="${command.annce_knd=='ANNO'}">
				frm.method.value = "forwardInsertLawNotice";
			</c:when>
			<c:when test="${command.annce_knd=='DEPT'}">
				frm.method.value = "forwardInsertDispatchNotice";
			</c:when>
			<c:when test="${command.annce_knd=='FAQ'}">
				frm.method.value = "forwardInsertSysFaq";
			</c:when>
		</c:choose>
		
		frm.action = "<c:url value='/las/board/notice.do' />";
		frm.target = "_self";

		viewHiddenProgress(true) ;
		
		frm.submit(); 
	}
	
	function goDetail(seqno) {
		
		var frm = document.frm;

		viewHiddenProgress(true) ;
		
	    frm.seqno.value = seqno;
		
		<c:choose>
			<c:when test="${command.annce_knd=='MEMO'}">
				frm.method.value = "detailLawNotice";
			</c:when>
			<c:when test="${command.annce_knd=='ANNO'}">
				frm.method.value = "detailLawNotice";
			</c:when>
			<c:when test="${command.annce_knd=='DEPT'}">
				frm.method.value = "detailDispatchNotice";
			</c:when>
			<c:when test="${command.annce_knd=='FAQ'}">
				frm.method.value = "detailSysFaq";
			</c:when>
		</c:choose>
		
		frm.action = "<c:url value='/las/board/notice.do' />";
		frm.target = "_self";
		
		frm.submit();
	}

	/**
	* 메시지 처리
	*/
	function showMessage(msg) {
		if(msg != "" && msg != null && msg.length > 1) {
			alert(msg);
		}
	}
</script>
</head>
<body onLoad='showMessage("<c:out value='${returnMessage}'/>");'>

<div id="wrap">	
	<div id="container">
		<!-- location -->
		<div class="location"><IMG SRC="<%=IMAGE %>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->
		
		<!-- title -->
		<div class="title">
		<c:choose>
			<c:when test="${command.annce_knd=='MEMO'}">
			<h3><spring:message code="las.page.title.board.Notice"/></h3>
			</c:when>
			<c:when test="${command.annce_knd=='ANNO'}">
			<h3><spring:message code="las.page.title.board.LawNotice" /></h3>
			</c:when>
			<c:when test="${command.annce_knd=='DEPT'}">
			<h3><spring:message code="las.page.title.board.DispatchNotice" /></h3>
			</c:when>
			<c:when test="${command.annce_knd=='FAQ'}">
			<h3><spring:message code="las.page.title.board.SysFaq" /></h3>
			</c:when>
		</c:choose>
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
		<div id="content_in">
			<form:form name="frm" id="frm" method="post" autocomplete="off">
			<input type="hidden" name="method" 		value="" />
			<input type="hidden" name="menu_id" 	value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage" 	value="<c:out value='${command.curPage}'/>" />
			
			<input type="hidden" name="annce_knd" id="annce_knd" value="<c:out value='${command.annce_knd}'/>" />
			<input type="hidden" name="seqno"     id="seqno"     value="<c:out value='${command.seqno}'/>" />

			<!-- Search -->
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
										<col width="10%"/>
										<col width="40%"/>
										<col width="10%"/>
										<col width="40%"/>
									</colgroup>
									<tr>
										<th><spring:message code="las.page.field.bbs.condition"/></th>
										<td>
											<select name="srch_combo" id="select">
											<option value="title" <c:if test="${command.srch_combo=='title'}">selected</c:if>><spring:message code="las.page.field.board.notice.title" /></option>
											<c:choose>
												<c:when test="${command.annce_knd!='FAQ'}">
												<option value="reg_nm" <c:if test="${command.srch_combo=='reg_nm'}">selected</c:if>><spring:message code="las.page.field.board.notice.reg_nm" /></option>
												</c:when>
											</c:choose>
											<option value="cont" <c:if test="${command.srch_combo=='cont'}">selected</c:if>><spring:message code="clm.page.msg.common.content" /></option>
											</select>
											<input type="text" name="srch_word" class="text" style="width:70%" value="<c:out value='${command.srch_word}'/>" maxLength="100" OnKeyDown="if(event.keyCode==13) { search(); }"/>
										</td>
										<th><spring:message code="las.page.field.board.notice.reg_dt" /></th>
										<td>
											<input type="text" name="srch_start_dt" id="srch_start_dt" value="<c:out value='${command.srch_start_dt}'/>" class="text_calendar02" readonly="readonly" / > ~
											<input type="text" name="srch_end_dt" id="srch_end_dt" value="<c:out value='${command.srch_end_dt}'/>" class="text_calendar02" readonly="readonly" />
										</td>
										
									</tr>
								</table>
							</td>
							<td class="vb tC">
								<a href="javascript:search();"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="las.page.button.search"/>"/></a>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!-- //Search -->	
			<!-- button -->
			<div class="t_titBtn">
				<div class="btn_wrap_t">
					<%if(command.isAuth_insert()){ %> 	
					<span class="btn_all_w"><span class="edit"></span><a href="javascript:goInsertForm('new');"><spring:message code='las.page.button.new' /></a></span>
					<%} %>
				</div>
			</div>		
			<!-- //button -->
			<!-- List -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
				<colgroup>
					<!-- !@# 공지사항(MEMO)의 경우 헤더 조절 -->
					<%if("MEMO".equals(command.getAnnce_knd())){ %>
						<col width="5%" />
						<col width="*" />
						<col width="15%" />
						<col width="15%" />
						<col width="10%" />
<%-- 						<col width="*"/> --%>
<%-- 						<col width="7%" /> --%>
					<%}else{ %>
						<col width="5%" />
						<col width="*" />
						<col width="15%" />
						<col width="10%" />
<%-- 						<col width="*"/> --%>
<%-- 						<col width="12%" /> --%>
					<%} %>
				</colgroup>
		    	<thead>
		          <tr>
		            <th><spring:message code="las.page.field.board.notice.no" /></th>		<!-- 번호 -->
		            <th><spring:message code="las.page.field.board.notice.title" /></th>	<!-- 제목 -->
		            <c:choose>
		            <c:when test="${command.annce_knd=='MEMO'}"> <!--!@# 공지(MEMO) : 공지기간 헤더 삽입 -->
		            	<th><spring:message code="clm.page.field.board.annceDt" /></th>	<!-- 공지기간 -->
		            </c:when>
		            </c:choose>
		            <th><spring:message code="las.page.field.board.notice.reg_nm" /></th>	<!-- 작성자 -->
		            <th><spring:message code="las.page.field.board.notice.reg_dt" /></th>	<!-- 작성일 -->
<%-- 		            <th><spring:message code="las.page.field.board.notice.rdcnt" /></th>	<!-- 조회수 --> --%>
		          </tr>
		        </thead>
		        <tbody>
<%
				if(pageUtil.getTotalRow() > 0) {
					for(int idx=0; idx<resultList.size(); idx++) {
						ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
						pageContext.setAttribute("lom", lom);
%>		
<%
						// title 값이 null 이면 skip	
// 						if(("ko".equals(command.getSession_user_locale())&&!"".equals(lom.get("title"))) 
// 							||	("en".equals(command.getSession_user_locale())&&!"".equals(lom.get("title_en")))
// 						){	
						%>
						<tr  <%if(idx == resultList.size()-1){%>class="end"<%}%> onMouseOut="this.className='<%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onMouseOver="this.className='on <%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'">
					    	<td class="tC"><%=lom.get("rn") %></td>
							<td class="tL overflow" title='<%=lom.get("title_en")%>'>
							<%								
								// 14일 이내의 글일 경우 new 이모티콘 표시
								if ("Y".equals(lom.get("new_yn"))) {
%>
									<span class="iconBox icon020"></span>
<%
					         	}
%>
						   		<a href='javascript:goDetail("<%=lom.get("seqno") %>");'>
<%								
								// 중요공지의 경우 타이틀을 굵게 표시
								if("Y".equals(lom.get("imp_flg"))){
									out.print("<b>");
									
								}
								
								// 회사코드가 SYS인 경우ㅡ(전사공지)ㅡ 타이틀 앞에 '[전사공지]' 추가
								if("SYS".equals(lom.get("comp_cd"))){ 
%>
									[<spring:message code='las.page.field.board.notice.allCompNotice'/>]
<%
								}
								
								// 타이틀 
			        			if("ko".equals(command.getSession_user_locale())){
			        				out.print(lom.get("title"));
			        			}else{
			        				out.print(lom.get("title_en"));
			        			}
%>
			        			</a>
					        </td>
					        <%if(lom.get("annce_knd").equals("MEMO")){%>
								<td class="tC">
									<%if(!"".equals((String)lom.get("annce_st_ymd"))){
										out.print(lom.get("annce_st_ymd")+" ~ ");
										out.print(lom.get("annce_ed_ymd"));
									}else{ %>
										<spring:message code="clm.msg.field.board.always"/>
									<%} %>
								</td>								
					        <%}%>
						    <td class="tL overflow" title='<%=lom.get("reg_nm") %>'><%=lom.get("reg_nm") %></td> 
						    <td class="tC"><%=lom.get("reg_dt") %></td>   
<%-- 							<td class="tR"><fmt:formatNumber value="${lom.rdcnt}" type="number"/></td> --%>
							
						</tr>
			      	<%
// 			      	}
					}
				}else{		  
 			  %>
					<tr class="end">
						<!-- !@# 공지사항(MEMO)의 경우 span 조절 -->
						<%if("MEMO".equals(command.getAnnce_knd())){ %>
							<td colspan="5" align="center" class="tC"><spring:message code="las.msg.succ.noResult" /></td>
						<%}else{ %>
							<td colspan="4" align="center" class="tC"><spring:message code="las.msg.succ.noResult" /></td>						
						<%} %>
						
					</tr>
			  <%}
			  %>
				</tbody>
		   	</table>
		    <!-- //list -->
			<div class="t_titBtn">
				<div class="total_num">Total : <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%></div>
			    <!-- pagination  -->
				<div class="pagination"><%=pageUtil.getPageNavi(pageUtil, "") %></div>
				<!-- //pagination -->
			</div>
			</form:form>
		</div>
		</div>
		<!-- //content -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
	<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->
</body>
</html>