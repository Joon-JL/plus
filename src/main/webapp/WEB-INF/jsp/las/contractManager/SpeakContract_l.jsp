<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@page import="com.sds.secframework.common.util.PageUtil"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<%
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil") ;
	
	//메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet"> 


<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/CommonProgress.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script language="javascript">

	$(document).ready(function(){
	
	    initCal("srch_start_ymd");   //첫번재 날짜창 설정 : 변수는 해당 태그의 id값
	    initCal("srch_end_ymd");     //두번재 날짜창 설정 : 변수는 해당 태그의 id값
	});
	
	function search(){
		goPage(1) ;
	}

	function goPage(page) {
		viewHiddenProgress(true) ;
		var f = document.frm ;

		var startDt = f.srch_start_ymd.value ;
		var endDt = f.srch_end_ymd.value ;

		if(startDt>endDt){
			alert("<spring:message code='las.page.field.contractManager.setFromDateEarly'/>") ;
			f.srch_start_ymd.focus() ;
			return ;
		}
		
		f.seqno.value = -1;
		f.curPage.value = page ;
		f.method.value = "listSpeakContract" ;
		f.action = "<c:url value='/las/contractmanager/speakContract.do' />" ;
		f.target = "_self" ;
		f.submit() ;
	}

	function goInsertForm() {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		
		f.seqno.value = -1;
		f.method.value = "forwardInsertSpeakContract" ;
		f.action = "<c:url value='/las/contractmanager/speakContract.do' />" ;
		f.target = "_self" ;
		f.submit() ; 
	}

	function goDetail(seq_no) {
		viewHiddenProgress(true) ;
		var f = document.frm ;
		f.seqno.value = seq_no;
		f.method.value = "forwardDetailSpeakContract" ;
		f.action = "<c:url value='/las/contractmanager/speakContract.do' />" ;
		f.target = "_self" ;
		f.submit() ; 
	}
	
	/**
     * ESB 임직원 조회
	 */
	 function searchEmployee() {
		    var frm = document.frm;
		    frm.srch_user_cntnt.value = frm.srch_respman_nm.value;
		    var srchValue = comTrim(frm.srch_user_cntnt.value); //입력받은 값

		    if (srchValue == "" || getByteLength(srchValue) < 4) { //최소 2자리 이상 입력받게 한다.
		        alert('<spring:message code='secfw.msg.error.nameMinByte' />');
		        frm.srch_respman_nm.focus();
		        return;
		    }

		    PopUpWindowOpen('', 800, 450, true);
		    frm.target = "PopUpWindow";

		    frm.srch_user_cntnt_type.value = "userName"; //이름으로 검색  - 보통 이름으로 검색함
		    //frm.srch_user_cntnt_type.value = "userId"; //마이싱글아이디로 검색(tb_com_user의 user_id 아님, tb_com_user의 single_id 임)
		    //frm.srch_user_cntnt_type.value = "epid"; //EPID로 검색(tb_com_user의 user_id임)

		    frm.action = "<c:url value='/secfw/esbOrg.do' />";
		    frm.method.value = "listEsbEmployee";
		    frm.submit();

		    frm.target = "";
		}
	
	function setUserInfos(obj) {
		var f = document.frm;
	    var userInfo = obj;
	    
	    f.srch_respman_nm.value = userInfo.cn;
	    f.srch_respman_id.value = userInfo.epid;
	}
	
</script>

</head>
<body onLoad="alertMessage('<c:out value='${command.return_message}'/>')">


<form:form name="frm" id="frm" method="post" autocomplete="off">
<input type="hidden" name="method" value=""/>
<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>"/>
<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>"/>
<input type="hidden" name="isForeign" value="<c:out value='${command.isForeign}'/>"/>
<input type="hidden" name="seqno"/>
<input type="hidden" name="srch_respman_id" value=""/>
<input type="hidden" name="srch_user_cntnt" value=""/>
<input type="hidden" name="srch_user_cntnt_type" value=""/>

<div id="wrap">	
	<div id="container">
		<!-- Location -->
        <div class="location">
            <img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/>
        </div>
        <!-- //Location -->
		<!-- content -->
		<div id="content">
			<!-- title -->
			<div class="title">
			<h3><spring:message code="las.page.field.speakcontract.main_title" /></h3>
		</div>
		<!-- //title -->
			
				<!-- search-->
		<div  class="search_box">
			<div class="search_box_inner">
					<table class="search_tb">
						<colgroup>
							<col/> 
							<col width="75px"/>
							</colgroup>
							<tr>
							<td>
							<table  class="search_form">
								<colgroup>
									<col width="7%"/>
									<col width="43%"/>
									<col width="7%"/>
									<col width="43%"/>
								</colgroup>
								<tr>
									<th><spring:message code="las.page.field.speakcontract.reg_dt" /></th>
									<td>
										 <input type="text" name="srch_start_ymd" id="srch_start_ymd" value="<c:out value='${command.srch_start_ymd}'/>"  class="text_calendar02"/>~
    									 <input type="text" name="srch_end_ymd" id="srch_end_ymd" value="<c:out value='${command.srch_end_ymd}'/>" class="text_calendar02"/></td>
									<th><spring:message code="las.page.field.speakcontract.respman_nm" /></th>
									<td>
										<input type="text" id="srch_respman_nm" name="srch_respman_nm" class="text_full" style="width:100px;" value="<c:out value='${command.srch_respman_nm}'/>" onKeyDown="if(event.keyCode==13){search()}" style="width:218px;" />
										
										<span class="btn_all_b">
										<span class="save"></span>
										<a href="javascript:searchEmployee()"><spring:message code="las.page.field.contractManager.select"/></a>
										</span>
									</td>
								</tr>
								<tr>
									<th><spring:message code="las.page.field.speakcontract.title" /></th>
									<td>
										<input type="text" id="srch_title" name="srch_title" class="text_full" value="<c:out value='${command.srch_title}'/>" onKeyDown="if(event.keyCode==13){search()}" style="width:400px;" />
									</td>
								</tr>
							</table>
						</td>
						
						<td class="vb tC"><a href="javascript:search()"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="las.page.field.contractManager.search"/>"/></a></td>
						</tr>
					</table>
				</div>		
			</div>

			<div class="t_titBtn">
					<!-- button -->
					<div class="btn_wrap_t fR">
						<c:if test="${command.auth_insert}">
						<span class="btn_all_w">
							<span class="save"></span>
							<a href="javascript:goInsertForm()"><spring:message code="las.page.button.insert" /></a>
						</span>
						</c:if>
					</div>
					<!-- //button --> 
			</div>
			
		
			<!-- list -->
			<table class="list_basic">
			 <colgroup>
			  <col width="65%"/>
				<col width="10%"/>
				<col width="15%"/>
				<col width="10%"/>
		     </colgroup>
			  <thead>
			    <tr>
			      <th><spring:message code="las.page.field.speakcontract.title" /></th>
				  <th><spring:message code="las.page.field.speakcontract.reqman_nm" /></th>
				  <th><spring:message code="las.page.field.speakcontract.respman_nm" /></th>
				  <th><spring:message code="las.page.field.speakcontract.reg_dt" /></th>
				</tr>
		      </thead>
			  <tbody>
				<c:choose>
					<c:when test="${pageUtil.totalRow > 0}">				
						<c:forEach var="list" items="${command.speakcontract_list}">
						<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
						    <td class="tL"><a href="javascript:goDetail('<c:out value="${list.seqno}"/>')"><c:out value='${list.title}'/></a></td>
							<td><c:out value='${list.reqman_nm}'/></td>
							<td><c:out value='${list.respman_nm}'/></td>
							<td><c:out value='${list.reg_dt_date}'/></td>
						</tr>
						</c:forEach>
			   		 </c:when>
			   		 <c:otherwise>
			     	     <tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
			      	      <td colspan="4"><spring:message code="las.page.msg.noResult" /></td>
			      	    </tr>
			   		 </c:otherwise>
				</c:choose>
		      </tbody>
		  </table>
			<!-- //list -->
			
			<!-- pagination -->
			<div class="t_titBtn">
				<div class="total_num">
					Total: <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%><spring:message code="las.page.field.contractManager.case"/>
				</div>
				<div class="pagination">
					<%=pageUtil.getPageNavi(pageUtil, "") %>	
				</div>
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
