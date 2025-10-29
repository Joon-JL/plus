<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.Token" %>
<%@ page import="com.sec.las.statistics.dto.StatisticsMonthForm" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="java.util.Date" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>   
<%--
/**
 * 파     일     명 : StatisticsMonth_d.jsp
 * 프로그램명 : 월간업무 기본 조회
 * 설               명 : 
 * 작     성     자 : 서백진
 * 작     성     일 : 2011.09
 */
--%>
<%
	//메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	ListOrderedMap lom = (ListOrderedMap)request.getAttribute("lom");
	//처리결과 메시지
	Date cal= new Date();
	String returnMessage = (String)request.getAttribute("returnMessage");	
	StatisticsMonthForm statisticsMonthForm = (StatisticsMonthForm)request.getAttribute("command") ;
	String locale = statisticsMonthForm.getDmstfrgn_gbn();	
	String sYear ="";
	String sMonth ="";
	String sFrToday = "";
	//String sWeekno = statisticsMonthForm.getSrch_weekno();
%>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<title><spring:message code="las.page.title.statistics.StatisticsMonth" /></title>
<LINK href="<%=CSS%>/las.css"       type="text/css" rel="stylesheet"> 

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value="/script/clms/jquery-1.6.2.min.js"/> type="text/javascript"></script>
<script language="javascript" src="<c:url value="/script/clms/new_style.js"/>ss type="text/javascript"></script>
<script language="javascript" src="<c:url value='/util/fckeditor/fckeditor.js' />"></script>
<script language="javascript">
<!--

	$(function() {

		$(document).keydown(function(event){
	
			if(event.keyCode == "13") {
				var target = event.target;
				if(target.id == "srch_cntnt") {
					//listLawSuitBasic("Y","1");
				} else {
					return false;
				}
			}
		});
	});
	/**
	* 수정
	*/
	function enableView(){
		document.all("namo1").style.display = "";
		//document.all("namo2").style.display = "";
		document.all("btn1").style.display = "";
		document.all("btn2").style.display = "";		
		document.all("id_crntmonth_rslt").style.display = "none";
		//document.all("id_nextmonth_plan").style.display = "none";			
	}
	/**
	* 수정
	*/
	function modifyView(){
		var frm = document.frm;
	    //frm.seqno.value = seqno;
	    frm.body_mime.value = frm.wec[0].MIMEValue;
	    frm.body_mime1.value = frm.wec[1].MIMEValue;

	    frm.transfer.value = "etc";
		frm.target = "_self";
		frm.action = "<c:url value='/las/statistics/statisticsMonth.do' />";
		frm.method.value = "modifyStatisticsMonth";
		frm.page_gbn.value = "M";
		if(confirm("<spring:message code='secfw.msg.ask.update' />")){
			frm.submit();
		}
	
	}	
	/**
	* 등록
	*/
	function insertView(){
		var frm = document.frm;
	    //나모웹에디터 관련
	    frm.body_mime.value = frm.wec[0].MIMEValue;
	    frm.body_mime1.value = frm.wec[1].MIMEValue;
 
	    frm.transfer.value = "etc";
		frm.target = "_self";
		frm.action = "<c:url value='/las/statistics/statisticsMonth.do' />";
		frm.method.value = "insertStatisticsMonth";
		frm.page_gbn.value = "I";
		if(confirm("<spring:message code='secfw.msg.ask.insert' />")){
			frm.submit();
		}
	}	
	/**
	* Tab 이동
	*/

	function moveTab(flag){
		var frm = document.frm;
		if(validateForm(document.frm) == false) {  
			return false;
		}
		frm.target = "_self";
		frm.action = "<c:url value='/las/statistics/statisticsMonth.do' />";
		frm.method.value = "forwardStatistics";
		
		if(flag == "1"){
			frm.transfer.value = "contract";
		}else if(flag == "2"){
			frm.transfer.value = "consult";
		}else if(flag == "4"){
			frm.transfer.value = "etc";
			frm.page_gbn.value = "";
		}
		frm.doSearch.value= "Y";
		frm.submit();
	}
	function getLastDay(YY,MM){ return new Date(YY,MM ,0); } 
	function getInfo(oDate){ 
		
		var y = oDate.getFullYear(); 
		var m = oDate.getMonth() + 1; 
		var d = oDate.getDate(); 
		return d;
		//alert( y + '년 ' + m + '월 ' + d +'일입니다' ); 
	} 	
			
    function initPage() {
    	var frm = document.frm;
    	frm.page_gbn.value = "M";
    }
    
	function showMessage(msg){
		if(msg != null && msg.length > 1) {
			alert(msg);
		}
	}		
//-->
</script>

</head>
<body onLoad="showMessage('<%=returnMessage %>');initPage();">  
<div id="wrap">
<div id="container">

	<div class="location">
		<img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/>		
		<c:out value='${menuNavi}' escapeXml="false"/>
	</div>
		<!-- Title -->
	<div class="title">
	<h3><spring:message code="las.page.title.statistics.StatisticsMonth" /></h3>
	</div> 		
	<div id="content">
	<div id="content_in">
		<!--**************************** Form Setting ****************************	-->
		<form:form id="frm" name="frm" method="post" autocomplete="off">
		<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
		<!-- 페이지 공통 -->	
		<input type="hidden" name="method"   value="">
		<input type="hidden" name="doSearch" value="<c:out value='${command.doSearch}'/>">
		<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>">	
		<input type="hidden" name="seqno" value="<%=lom.get("seqno") %>">
		<input type="hidden" name="seqnos" value="<%=lom.get("seqno") %>">
		<input type="hidden" name="page_gbn" value="<c:out value='${command.page_gbn}'/>">
		<input type="hidden" name="transfer" value="<c:out value='${command.transfer}'/>">
		<input type="hidden" name="srch_weekno" value="srch_weekno">
		<input type="hidden" name="srch_weekfr" value="<c:out value='${command.srch_weekfr}'/>">
		<input type="hidden" name="srch_weekto" value="<c:out value='${command.srch_weekto}'/>">
		<input type="hidden" name="dmstfrgn_gbn" value="<c:out value='${command.dmstfrgn_gbn}'/>">
		<input type="hidden" name="tabs" value="TN_LAS_ETC_MONTHDUTY">
		
		<!-- 이중등록 방지 -->
		<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>">
	
		<!-- 나모 웹 에디터 관련 -->
		<input type="hidden" name="body_mime" id="body_mime" value="<c:out value='${lom.crntmonth_rslt}' />" />
		<input type="hidden" name="body_mime1" id="body_mime1" value="<c:out value='${lom.nextmonth_plan}' />" />
		<input type="hidden" name="crntmonth_rslt" id="crntmonth_rslt" value="<c:out value='${lom.crntmonth_rslt}' />" />
		<input type="hidden" name="nextmonth_plan" id="nextmonth_plan" value="<c:out value='${lom.nextmonth_plan}' />" />	
		<!-- //	**************************** Form Setting ****************************	-->
		<!-- tab01 -->
		<ul class="tab_basic">
			<li><a href="javascript:moveTab('1');"><spring:message code="las.page.field.statistics.contract" /></a></li>
			<li><a href="javascript:moveTab('2');"><spring:message code="las.page.field.statistics.consult" /></a></li>
			<!-- 
			<li><a href="#"><spring:message code="las.page.field.statistics.lawsuit" /></a></li>
			 -->
			<li class="on"><a href="#"><spring:message code="las.page.field.statistics.etc" /></a></li>				
		</ul>
		<!-- 두줄라인 추가 --><div class="tal_topLine"></div><!-- //두줄라인 추가 -->
		<BR>
<%
	int iYear = cal.getYear()+2000-100;
	int iMonth = cal.getMonth();
	
	if((statisticsMonthForm.getSrch_regdt2() != null && iMonth+1  == Integer.parseInt(statisticsMonthForm.getSrch_regdt2()) 
			&& iYear == Integer.parseInt(statisticsMonthForm.getSrch_regdt1()))
				|| statisticsMonthForm.getSrch_regdt2() == null){	
%>	

		<div class="t_titBtn">
			<div class="btn_wrap_t fR">
				<span class="btn_all_w"><span class="edit"></span><a href="javascript:enableView();"><spring:message code="las.page.button.modify" /></a></span>
			</div>	
    	</div>		
<%
	}else{
%>
		<BR></BR>
<%
	}
%>	
		<!-- //Button -->
		<!--90% 안에서 작업하기 search-->
		<!-- //Button -->
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
								<col width="*%"/>
							</colgroup>
								
							<tr>                                                                                                                                                                                                                   
	    	        		<th><spring:message code="las.page.field.contract.library.reg_dt2" /></th> 
                                                                                                                                                                                         
	    	        		<td>
			    			<select name="srch_regdt1" id="srch_regdt1" class="select" onchange="javascript:moveTab('4')">
			    			<%
			    			
			    			for(int i=0;i < 6 ; i++){	
			    				int iYear1 = iYear-i;
			    			%>
							<c:choose>
								<c:when test="${command.srch_regdt1==''}">
								<%
									if(iYear1  == iYear){
										sYear = iYear1+"";
										out.println("<option value='"+iYear1+"' selected>"+iYear1+"</option>");
									}else{
										out.println("<option value='"+iYear1+"'>"+iYear1+"</option>");
									}
								 %>
								</c:when>
								<c:otherwise>	
								<%							
									if(statisticsMonthForm.getSrch_regdt1() != null && statisticsMonthForm.getSrch_regdt1().equals(Integer.toString(iYear1))){										
										sYear = iYear1+"";
										out.println("<option value='"+iYear1+"' selected>"+iYear1+"</option>");
									}else if(statisticsMonthForm.getSrch_regdt1() == null){
										if(iYear1  == iYear){
											sYear = iYear1+"";
											out.println("<option value='"+iYear1+"' selected>"+iYear1+"</option>");
										}else{
											out.println("<option value='"+iYear1+"'>"+iYear1+"</option>");
										}
									}else{
										out.println("<option value='"+iYear1+"'>"+iYear1+"</option>");
									}
						
								 %>
								</c:otherwise>
							</c:choose>
			    			
			    			<%} %>    			
			    			</select> <spring:message code="las.page.field.statistics.year"/>&nbsp;  	   
			    			 
			    			<select name="srch_regdt2" id="srch_regdt2" class="select" onchange="javascript:moveTab('4')">		
   			
			    			<%
			    			
			    			for(int i=1;i < 13 ; i++){			    				
			    			%>
							<c:choose>
								<c:when test="${command.srch_regdt2==''}">
								<%
									if(iMonth+1  == i){
										sMonth = i>9?i+"":"0"+i;
										out.println("<option value='"+i+"' selected>"+i+"</option>");
									}else{
										out.println("<option value='"+i+"'>"+i+"</option>");
									}
								 %>
								</c:when>
								<c:otherwise>
								<%
								if(statisticsMonthForm.getSrch_regdt2() != null && statisticsMonthForm.getSrch_regdt2().equals(Integer.toString(i))){
									sMonth = i>9?i+"":"0"+i;
									out.println("<option value='"+i+"' selected>"+i+"</option>");
								}else if(statisticsMonthForm.getSrch_regdt2() == null){
									if(iMonth+1  == i){
										sMonth = i>9?i+"":"0"+i;
										out.println("<option value='"+i+"' selected>"+i+"</option>");
									}else{
										out.println("<option value='"+i+"'>"+i+"</option>");
									}
								}else{
										out.println("<option value='"+i+"'>"+i+"</option>");
								}
								 %>
								</c:otherwise>
							</c:choose>			    			
			    			
			    			<%} %>     			
			    			</select> <spring:message code="las.page.field.statistics.mm"/>&nbsp;
			    			<span id="ddTable"> 
	    	        		<script>
    	        			var dd = getInfo(getLastDay('<%=sYear %>','<%=sMonth %>'));
    	        			var term = "";
    	        			function fdd(d){
	    	        			if(d == '28'){
									term = "<%= "("+sYear+"-"+ sMonth +"-01"+" ~ "+sYear+"-"+ sMonth +"-28)" %>";
	    	        			}else if(d == '29'){
	    	        				term = "<%= "("+sYear+"-"+ sMonth +"-01"+" ~ "+sYear+"-"+ sMonth +"-29)" %>";
	    	        			}else if(d == '30'){
	    	        				term = "<%= "("+sYear+"-"+ sMonth +"-01"+" ~ "+sYear+"-"+ sMonth +"-30)" %>";
	    	        			}else if(d == '31'){
	    	        				term = "<%= "("+sYear+"-"+ sMonth +"-01"+" ~ "+sYear+"-"+ sMonth +"-31)" %>";
	    	        			}
	    	        			fdd1(term);	    	        			
    	        			}
    	        			function fdd1(term1){
								$('#ddTable').append(term1);	    	        			
    	        			}    
    	        			fdd(dd);	        					
    	        			</script>
    	        			</span>   	    			 	        		
	    	        		</td>
	    	        		
                           </tr>
						</table>
					</td>
					</tr>
				</table>
			</div>
  		</div>

		<!--//90% 안에서 작업하기 search-->

		<!-- //Search -->
		<!-- //Clear -->
		<br />
		
		<!-- List -->
		<table id="id_crntmonth_rslt" border="0" cellspacing="0" cellpadding="0" class="list_basic">
			<colgroup>
			<col width="50%" align="center"/>
			<col width="50%" align="center"/>
			</colgroup>
			<thead>
			<tr>
				<th><spring:message code="las.page.field.statistics.monthperform" /></th>
				<th><spring:message code="las.page.field.statistics.monthplan" /></th>
			</tr>
			</thead>
			  <tbody>
				<tr>
					<td class="tL">
						<div class="tal_txt_style">
						<%=(lom != null && lom.size() > 0)?lom.get("crntmonth_rslt"):"&nbsp;" %>	
						</div>
					</td>
					<td class="tL">
						<div class="tal_txt_style">
						<%=(lom != null && lom.size() > 0)?lom.get("nextmonth_plan"):"&nbsp;" %>	
						</div>
					</td>					
				</tr>
		  </tbody>
		 </table>
		 
		<table id="namo1" style="display:none;" border="0" cellspacing="0" cellpadding="0" class="list_basic">
			<colgroup>
			<col width="50%" align="center"/>
			<col width="50%" align="center"/>
			</colgroup>
			<thead>
			<tr>
				<th><spring:message code="las.page.field.statistics.monthperform" /></th>
				<th><spring:message code="las.page.field.statistics.monthplan" /></th>
			</tr>
			</thead>
			  <tbody>
				<tr>
					<td class="tL">
					<%@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"%>
					</td>
					<td class="tL">
					<%@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"%>
					</td>					
				</tr> 	
		  </tbody>
		 </table>		 

			<!-- //view  -->
			<!--  Function Button  -->
<% if(lom != null && lom.size() > 0){ %>			
			<div class="btn_wrap" id="btn1" style="display:none;">
				<span class="btn_all_b"><span class="save"></span><a href="javascript:modifyView()"><spring:message code="las.page.button.save"/></a></span>
			</div>
			<div class="btn_wrap" id="btn2" style="display:none;">
			
			</div>			
<% }else{ %>
			<div class="btn_wrap" id="btn1" style="display:none;">
			
			</div>
			<div class="btn_wrap" id="btn2" style="display:none;">
				<span class="btn_all_b"><span class="save"></span><a href="javascript:insertView()"><spring:message code="las.page.button.save"/></a></span>
			</div>
<% }%>
			<!-- //  Function Button  -->				
	</form:form>
	</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</div>
	
</div>
	<!-- footer -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>	 
	<!-- // footer -->
</body>
<!-- 나모 웹에디터 관련 추가 -->
<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
	var wecObj0 = document.wec[0];
	wecObj0.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	wecObj0.SetDefaultFontSize("9");
	wecObj0.EditMode = 1;
	wecObj0.Value = document.frm.crntmonth_rslt.value; //namo 에 값 셋팅
	
	var wecObj1 = document.wec[1];
	wecObj1.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	wecObj1.SetDefaultFontSize("9");
	wecObj1.EditMode = 1;
	wecObj1.Value = document.frm.nextmonth_plan.value; //namo 에 값 셋팅
</SCRIPT>
</html>