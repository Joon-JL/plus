<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.Token" %>
<%@ page import="com.sec.las.statistics.dto.StatisticsMonthForm" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %> 

  
<%--
/**
 * 파     일     명 : StatisticsMonthContract_d.jsp
 * 프로그램명 : 월간업무(계약) 기본 조회
 * 설               명 : 
 * 작     성     자 : 서백진
 * 작     성     일 : 2011.09
 */
--%>
<%
	//메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	ArrayList resultList = (ArrayList)request.getAttribute("resultList");
	Date cal= new Date();
	//ListOrderedMap lom = (ListOrderedMap)resultList.get(0);
	StatisticsMonthForm statisticsMonthForm = (StatisticsMonthForm)request.getAttribute("command") ;
	String locale = statisticsMonthForm.getDmstfrgn_gbn();	
	//처리결과 메시지
	String returnMessage = (String)request.getAttribute("returnMessage");	
	String sYear ="";
	String sMonth ="";
%>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<title><spring:message code="las.page.title.statistics.StatisticsMonth" /></title>

<LINK href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet"></LINK>
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
					//return false;
				}
			}
		});
	});
	/**
	* 수정
	*/
	function enableView1(){
		var i = 0;
		result_yn = document.all("result_yn").value;
		obj = document.getElementsByName("seqnos1");
		obj1 = document.getElementsByName("monthdutyyns");
		//while(obj[i] && result_yn=="P"){
		while(obj[i]){
			obj[i].style.display = "";
			if(obj1[i].value == 'Y'){
				obj[i].checked = true;
			}
			i++;
		}
		//if(result_yn=="P"){
			document.all("btn1").style.display = "";
		//}		
	}
	function enableView(){
		var frm = document.frm;
		frm.target = "_self";
		frm.action = "<c:url value='/las/statistics/statisticsMonth.do' />";
		frm.method.value = "forwardStatistics";
		frm.transfer.value = "contract";
		//frm.page_gbn.value = "C";
		frm.display_yn.value = "Y";
		frm.submit();	
	}		
	function chkSeqnos(idx){
		obj = document.getElementsByName("monthdutyyns");
		obj1 = document.getElementsByName("seqnos1");
		if(obj1[idx].checked){
			obj[idx].value = "Y";
		}else{
			obj[idx].value = "N";
		}
	}	
	/**
	* 수정
	*/
	function modifyView(){
		var frm = document.frm;
		frm.target = "_self";
		frm.action = "<c:url value='/las/statistics/statisticsMonth.do' />";
		frm.method.value = "modifyStatisticsMonth";
		frm.page_gbn.value = "C";
		if(confirm("<spring:message code='secfw.msg.ask.update' />")){
			frm.submit();
		}
	}	

	/**
	* Tab 이동
	*/

	function moveTab(flag){
		var frm = document.frm;
		if(validateForm(document.frm) == false) {  
			return;
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
    	frm.page_gbn.value = "C";
    	if("<c:out value='${command.display_yn}'/>" == "Y"){
    		enableView1();
    	}      	
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
	<form:form id="frm" name="frm" method="post" autocomplete="off">
<!-- 
**************************** Form Setting **************************** 
-->

	<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
	<!-- 페이지 공통 -->	
	<input type="hidden" name="method"   value="">
	<input type="hidden" name="doSearch" value="<c:out value='${command.doSearch}'/>"/>
	<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>"/>	
	<input type="hidden" name="page_gbn" value="<c:out value='${command.page_gbn}'/>"/>
	<input type="hidden" name="transfer" value="<c:out value='${command.transfer}'/>"/>
	<input type="hidden" name="display_yn" value=""/>
	<input type="hidden" name="dmstfrgn_gbn" value="<c:out value='${command.dmstfrgn_gbn}'/>"/>
	<!-- 이중등록 방지 -->
	<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>"/>

<!-- //**************************** Form Setting ****************************-->	
		<div class="newstyle-area">
		<!-- tab01 -->
		<ul class="tab_basic">
			<li class="on"><a href="#"><spring:message code="las.page.field.statistics.contract" /></a></li>
			<li><a href="javascript:moveTab('2');"><spring:message code="las.page.field.statistics.consult" /></a></li>
			<!-- 
			<li><a href="#"><spring:message code="las.page.field.statistics.lawsuit" /></a></li>
			 -->
			<li><a href="javascript:moveTab('4');"><spring:message code="las.page.field.statistics.etc" /></a></li>				
		</ul>
		<!-- 두줄라인 추가 --><div class="tal_topLine"></div><!-- //두줄라인 추가 -->
		<br />
		<div class="t_titBtn">
			<div class="Sel_aw fL">
	  			<select name="result_yn" id="result_yn" onchange="javascript:moveTab('1')" style="width:100px;">
	  			<option value='R' <c:if test="${command.result_yn=='R'}">selected</c:if>><spring:message code="las.page.field.statistics.record"/></option>
	  			<option value='P' <c:if test="${command.result_yn=='P'}">selected</c:if>><spring:message code="las.page.field.statistics.plan"/></option>	    			
	  			</select>			
			</div>
<%
	int iYear = cal.getYear()+2000-100;
	int iMonth = cal.getMonth();
	
	if((statisticsMonthForm.getSrch_regdt2() != null && iMonth+1  == Integer.parseInt(statisticsMonthForm.getSrch_regdt2()) 
			&& iYear == Integer.parseInt(statisticsMonthForm.getSrch_regdt1()))
				|| statisticsMonthForm.getSrch_regdt2() == null){		
%>									
<!--  			* 수정 로직의 목적이 불분명 - 임시적으로 불필요로 판단				 -->
<!-- 			<div class="btn_wrap_t fR"> -->
<%-- 				<span class="btn_all_w"><span class="edit"></span><a href="javascript:enableView();"><spring:message code="las.page.button.modify" /></a></span> --%>
<!-- 			</div> -->
<%
	}	
%>					
    	</div>
		<!-- //Button -->
		<!--90% 안에서 작업하기 search-->
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
								<col width="40%"/>
							</colgroup>
								
							<tr>                                                                                                                                                                                                              
	    	        		<th><spring:message code="las.page.field.contract.library.reg_dt2" /></th>                                                                                                                                                                                            
	    	        		<td>
			    			<select name="srch_regdt1" id="srch_regdt1" class="select" onchange="javascript:moveTab('1')">
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
			    			
			    			<select name="srch_regdt2" id="srch_regdt2" class="select" onchange="javascript:moveTab('1')">		
   			
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
			    			</select> <spring:message code="las.page.field.statistics.mm"/>	&nbsp;  	
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
		<br/>
	<%
		if(resultList!=null)
			for(int idx=0;idx < resultList.size();idx++){
			    
			   ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);			    	
	%>		
		<!-- List -->
		<table border="0" cellspacing="0" cellpadding="0" class="table-style01 mt20">
			<colgroup>
				<col width="100" />
				<col width="310" />
				<col width="100" />
				<col width="310" />
			</colgroup>
			<tbody>
			<tr>
				<th><spring:message code="las.page.field.common.req_title" /></th>
				<td colspan="3"><%=(lom != null && lom.size() > 0)?StringUtil.convertHtmlTochars((String)lom.get("req_title")):"" %></td>
			</tr>
			<tr>
				<th><spring:message code="las.page.field.common.title" /></th>
				<td colspan="2"><%=(lom != null && lom.size() > 0)?lom.get("title"):"" %></td>
				<td style="border-left:0"><input type="checkbox" style="display:none;" id='seqnos1' name='seqnos1' value='<%=lom.get("seqno") %>' onClick="javascript:chkSeqnos(<%=idx %>)"></td>
				<input type="hidden" id='seqnos' name='seqnos' value='<%=lom.get("seqno") %>'>
				<input type="hidden" id='tabs' name='tabs' value='<%=lom.get("tab") %>'>
				<input type="hidden" id='monthdutyyns' name='monthdutyyns' value='<%=lom.get("monthdutyyn") %>'>
			</tr>
			<tr>
				<th><spring:message code="las.page.field.mainproject.oppnt_comp" /></th>
				<td><%=(lom != null && lom.size() > 0)?lom.get("company"):"" %></td>
				<th><spring:message code="las.page.field.statistics.req_dept" /></th>
				<td><%=(lom != null && lom.size() > 0)?lom.get("dept_nm"):"" %></td>				
			</tr>
			<tr>
				<td align="left" colspan="4">
				 
    			<div class="text_area" style="height:170px; overflow:auto;">
    			<!-- <textarea name="conts" readonly rows="14" cols="170" class="text_area_full" ><%=(lom != null && lom.size() > 0)?lom.get("cont"):"" %></textarea> -->
       			<%=(lom != null && lom.size() > 0)?lom.get("cont"):"" %>
       			</div><br /> 	
       			</td>	
			       							
			</tr> 			
			</tbody>			
		</table>

	<%
			}
	%>		
			<!-- //view  -->
			<!--  Function Button  -->
			<!-- 
<% if(resultList != null && resultList.size() > 0){ %>			
			<div class="btn_wrap" id="btn1" style="display:none;"><spring:message code="las.page.field.statistics.checksave"/>
				<span class="btn_all_b"><span class="save"></span><a href="javascript:modifyView()"><spring:message code="las.page.button.save"/></a></span>
			</div>
			<div class="btn_wrap" id="btn2" style="display:none;">
			
			</div>			
<% }else{ %>
		<table border="0" cellspacing="0" cellpadding="0" class="table-style01 mt20">
			<colgroup>
				<col width="100" />
				<col width="310" />
				<col width="100" />
				<col width="310" />
			</colgroup>
			<tbody>
			<tr>
				<th><spring:message code="las.page.field.common.title" /></th>
				<td colspan="2">&nbsp;</td>
				<td style="border-left:0" >
					<div id="seqnos" style="display:none;"></div>				
				</td>
				<input type="hidden" id='tabs' name='tabs' value=''>
				<input type="hidden" id='monthdutyyns' name='monthdutyyns' value='Y'>
			</tr>
			<tr>
				<th><spring:message code="las.page.field.mainproject.oppnt_comp" /></th>
				<td>&nbsp;</td>
				<th><spring:message code="las.page.field.statistics.req_dept" /></th>
				<td>&nbsp;</td>				
			</tr>
			<tr>
				<td align="left" colspan="4">
				 
    			<div class="text_area" style="height:170px;">
    			<textarea name="conts" readonly rows="14" cols="170" class="text_area_full" ></textarea>
       			
       			</div><br /> 	
       			</td>	
			       							
			</tr> 			
			</tbody>			
		</table>
<% }%> -->
			<!-- //  Function Button  -->
			
			</div>
			</form:form>
	</div>
</div>

<% if(resultList != null && resultList.size() == 0){ %>	
<div class="t_titBtn" align="center">
	<span class="tC" ><spring:message code="las.msg.succ.noResult" /></span>
</div>
				
<div class="btn_wrap" id="btn1" style="display:none;"></div>
<div class="btn_wrap" id="btn2" style="display:none;"></div>	

<% }%>	
	
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>

</div>
<!-- //container -->

</div>
<!-- footer --> 
<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- // footer -->

</body>
</html>