<!DOCTYPE html >
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.sds.secframework.common.util.Token" %>
<%@ page import="com.sec.las.statistics.dto.StatisticsMonthForm" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.HashMap" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>   
<%--
/**
 * 파     일     명 : StatisticsMonth_l.jsp
 * 프로그램명 : 월간업무 전체 조회
 * 설               명 : 
 * 작     성     자 : 서백진
 * 작     성     일 : 2011.09
 */
--%>
<%
	//메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	HttpSession hs = request.getSession(false);
	ArrayList respmanList = (ArrayList)request.getAttribute("respmanList");
	ArrayList resultList = (ArrayList)request.getAttribute("resultList");
	ArrayList resultList1 = (ArrayList)request.getAttribute("resultList1");
	ArrayList resultList2 = (ArrayList)request.getAttribute("resultList2");
	ArrayList resultList3 = (ArrayList)request.getAttribute("resultList3");
	ArrayList resultList4 = (ArrayList)request.getAttribute("resultList4");
	ArrayList resultList5 = (ArrayList)request.getAttribute("resultList5");
	//처리결과 메시지
	Date cal= new Date();
	String returnMessage = (String)request.getAttribute("returnMessage");	
	StatisticsMonthForm statisticsMonthForm = (StatisticsMonthForm)request.getAttribute("command") ;
	String locale = statisticsMonthForm.getDmstfrgn_gbn();	
	ArrayList roleList = (ArrayList)hs.getAttribute("secfw.session.role"); //session 사용할 경우
	ArrayList userRoleList = new ArrayList(); //role_cd만 꺼내기 위한 arrayList
	
	if(roleList != null && roleList.size() > 0){	
	    for(int idx = 0; idx < roleList.size(); idx++){	
	        HashMap roleListMap = (HashMap)roleList.get(idx);	 
	        userRoleList.add((String)roleListMap.get("role_cd"));
	    }
	}
	String accessLevel = "";
	
	//사용자 role 비교
	// RA00: 시스템관리자  RA01: 법무관리자  RA02: 법무담당자  RA03: 법무일반사용자
	// RC01: CP관리자
	// RD01: 사업부계약관리자  RD02: 사업부계약담당자
	// RM00: 시스템운영자
	
	if(userRoleList != null && userRoleList.size() > 0){
	    if(userRoleList.contains("RM00") || userRoleList.contains("RA00") || userRoleList.contains("RA01") ){
	        accessLevel = "A";
	    }else if(userRoleList.contains("RD01") || userRoleList.contains("RD02")){
	        accessLevel = "D";
	    }else if(userRoleList.contains("RC01")){
	        accessLevel = "B";
	    }else{
	        accessLevel = "C";
	    }
	}	
	/*
	String locale = (String)hs.getAttribute("secfw.session.blngt_orgnz");
	if(locale != null && locale.equals("A00000001")){ // 국내
		locale = "H";
	}else if(locale != null && locale.equals("A00000002")){ // 해외
		locale = "F";
	}else{
		locale = "";
	}
	*/	
	/*
	java.util.Enumeration e = session.getAttributeNames();
    while (e.hasMoreElements()) {
        String name = (String)e.nextElement();
        String value = session.getAttribute(name).toString();
        out.println(name + " = " + value);
        out.println("<br>");
    }
	*/
	String sYear ="";
	String sMonth ="";
	//out.println("locale"+locale);
	int idx1 = 0;
%>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<title><spring:message code="las.page.title.statistics.StatisticsMonthList" /></title>
<LINK href="<%=CSS%>/las.css"  type="text/css" rel="stylesheet"> 
  
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
	function getLastDay(YY,MM){ return new Date(YY,MM ,0); } 
	function getInfo(oDate){ 
		
		var y = oDate.getFullYear(); 
		var m = oDate.getMonth() + 1; 
		var d = oDate.getDate(); 
		return d;
		//alert( y + '년 ' + m + '월 ' + d +'일입니다' ); 
	} 

	/**
	* 수정
	*/
	function enableView1(){
		var i = 0;
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
		frm.method.value = "listStatisticsMonth";
		if(frm.reg_id.value == "")
			frm.reg_id.value = "ALL";
		
		frm.display_conf_yn.value = "Y";
		frm.submit();	
	}
		
	function chkSeqnos(idx){
		obj = document.getElementsByName("monthdutyyns");
		obj1 = document.getElementsByName("seqnos1");
		obj2 = document.getElementsByName("tabs");
		obj3 = document.getElementsByName("seqnos");

		if(obj1[idx].checked){
			obj[idx].value = "Y";
		}else{
			obj[idx].value = "N";
		}
		chkSeqnos1(obj2[idx].value, obj3[idx].value, obj[idx].value); // 담당자가 여러명일 경우 체크
	}
	function chkSeqnos1(tab, seqno, monthdutyyn){
		var i = 0;
		obj = document.getElementsByName("seqnos1");
		obj1 = document.getElementsByName("monthdutyyns");
		obj2 = document.getElementsByName("tabs");
		obj3 = document.getElementsByName("seqnos");
		while(obj[i]){

			if(obj3[i].value == seqno &&  obj2[i].value == tab){
				if(monthdutyyn == 'Y'){
					obj[i].checked = true;
				}else{
					obj[i].checked = false;
				}
				obj1[i].value = monthdutyyn;
			}
			i++;
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
		frm.display_conf_yn.value = "N";
		frm.page_gbn.value = "A";
		frm.transfer.value = "A";
		
		if(confirm("<spring:message code='secfw.msg.ask.update' />")){
			frm.submit();
		}
	}	
	/**
	* 상세
	*/
	function detailView(regid){
		var frm = document.frm;
	    //frm.seqno.value = seqno;
		frm.target = "_self";
		frm.action = "<c:url value='/las/statistics/statisticsMonth.do' />";
		frm.method.value = "listStatisticsMonth";
		frm.display_conf_yn.value = "";
		frm.reg_id.value = regid;
		frm.submit();
	}	
	function searchSta(){
		var frm = document.frm;
		if(validateForm(document.frm) == false) {  
			return;
		}
		
		frm.target = "_self";
		frm.action = "<c:url value='/las/statistics/statisticsMonth.do' />";
		frm.method.value = "listStatisticsMonth";
		if("<c:out value='${command.reg_id}'/>" == "")
			frm.reg_id.value = "ALL";
		
		frm.doSearch.value= "Y";
		frm.submit();
	}	
    function initPage() {
    	var frm = document.frm;

    	if("<c:out value='${command.display_conf_yn}'/>" == "Y" ){
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
	<h3><spring:message code="las.page.title.statistics.StatisticsMonthList" /></h3>
	</div> 		
	<div id="content">
	<div id="content_in">
		<!-- **************************** Form Setting ****************************-->
		<form:form id="frm" name="frm" method="post" autocomplete="off">
		<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
		<!-- 페이지 공통 -->	
		<input type="hidden" name="method"   value="">
		<input type="hidden" name="doSearch" value="<c:out value='${command.doSearch}'/>"/>
		<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>"/>
		
		<input type="hidden" name="transfer" value="<c:out value='${command.transfer}'/>"/>
		<input type="hidden" name="page_gbn" value="<c:out value='${command.page_gbn}'/>"/>
		<input type="hidden" name="display_yn" value="<c:out value='${command.display_yn}'/>"/>
		<input type="hidden" name="display_conf_yn" value="<c:out value='${command.display_conf_yn}'/>"/>
		<!-- 이중등록 방지 -->
		<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>"/>
		<input type="hidden" name="reg_id"   value="<c:out value='${command.reg_id}'/>"/>	

		<!-- //**************************** Form Setting ****************************-->
		
		<div class="btnwrap tR">
<!-- 			<div class="Sel_aw fL"> -->
<!-- 	  			<select name="dmstfrgn_gbn" id="dmstfrgn_gbn" onchange="javascript:searchSta()" style="width:100px;"> -->
<%-- 	  			<option value='F' <c:if test="${command.dmstfrgn_gbn=='F'}">selected</c:if>><spring:message code="las.page.tab.lawsuit.frgn" /></option> --%>
<%-- 	  			<option value='H' <c:if test="${command.dmstfrgn_gbn=='H'}">selected</c:if>><spring:message code="las.page.tab.lawsuit.dmst" /></option>	    			 --%>
<!-- 	  			</select>			 -->
<!-- 			</div> -->
<%
		int iYear = cal.getYear()+2000-100;
		int iMonth = cal.getMonth();
	
		if((statisticsMonthForm.getSrch_regdt2() != null && iMonth+1  == Integer.parseInt(statisticsMonthForm.getSrch_regdt2()) 
			&& iYear == Integer.parseInt(statisticsMonthForm.getSrch_regdt1()))
			|| statisticsMonthForm.getSrch_regdt2() == null){	
			if(accessLevel.equals("A")){
%>			   
				
					<span class="btn_all_w"><span class="edit"></span><a href="javascript:enableView();"><spring:message code="las.page.button.modify" /></a></span>
				
    			
<%			}
		}	
%>	    	
		</div>
		
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
			    			<select name="srch_regdt1" id="srch_regdt1" class="select" onchange="javascript:searchSta()">
			    			<%
			    			
			    			for(int i=0;i < 6 ; i++){	
			    				int iYear1 = iYear-i;
			    			%>
							<c:choose>
								<c:when test="${command.srch_regdt1==null}">
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
			    			
			    			<select name="srch_regdt2" id="srch_regdt2" class="select" onchange="javascript:searchSta()">		
   			
			    			<%
			    			
			    			for(int i=1;i < 13 ; i++){			    				
			    			%>
							<c:choose>
								<c:when test="${command.srch_regdt2==null}">
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
			    			
			    			<%} 	%>     			
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
    	        			function fdd1(term){
								$('#ddTable').append(term);	    	        			
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
		
	
<%

		if(respmanList.size() >0 && accessLevel.equals("A")){ 
%>	
<!-- 
		<div class="t_titBtn">
			<span class="gray_t_arw2"><spring:message code="las.page.field.speakconsult.respman_nm" /></span>
		</div>
 -->		
	
		<div class='title_basic'>
			<H4><spring:message code="las.page.field.speakconsult.respman_nm" /></H4>
		</div>
			 
		
		
		  		
		
		<table width="100%" align="center" >
		<tr><td>
		<table class="list_basic">
		  <colgroup>	
		
		    <col width="25%"/>
		    <col width="25%"/>
		    <col width="25%"/>
		    <col width="25%"/>	    	 		
	      </colgroup>
		  <thead>
		  <%
		  if(statisticsMonthForm.getReg_id() == null || statisticsMonthForm.getReg_id().equals("ALL")){
		  %>
			<tr>
				<th class="tal_bg_cor01" colspan="4"><nobr><b><a href="javascript:detailView('ALL');"><spring:message code="las.page.field.common.total" /></a></b></nobr></th>
			</tr>
		  <%
		  }else{%>	
			<tr>
				<th class="tal_bg_cor01" colspan="4"><nobr><a href="javascript:detailView('ALL');"><spring:message code="las.page.field.common.total" /></a></nobr></th>
			</tr>		  	
<%
		  }
			//String trStart 	= "<tr>";
			//String trEnd 	= "</tr>";
			String respman 	= "";			
			String html 	= "";
			String chk 		= "y";
			String chklast 	= "";
			String lastid 	= "";
			String lastnm 	= "";
			String sBold 	= "";
			String eBold 	= "";
			int chkidx 		= 0;
			for(int idx=0;idx < respmanList.size();idx++){

				ListOrderedMap lom = (ListOrderedMap)respmanList.get(idx);
				if(statisticsMonthForm.getReg_id() != null && statisticsMonthForm.getReg_id().equals(lom.get("respman_id"))){
					sBold = "<b>";
					eBold = "</b>";
				}else{
					sBold = "";
					eBold = "";					
				}
				respman = "<td  class='tC'>"
						+ sBold
						+ "<nobr><a href=javascript:detailView('" + lom.get("respman_id") + "');>"+ lom.get("respman_nm") + "</a>"
						+ eBold
						+ "</nobr></td>";
				chkidx++;
				if(chk == "y"){
					html 	= html + respman;
					chk 	= "n";
					chklast = "n";
					lastid 	= lom.get("respman_id")+"";
					lastnm 	= lom.get("respman_nm")+"";
				}else{
					chk 	= "y";
					html 	= html + respman;
					
					if(chkidx == 4){
						chklast = "y";
						html = "<tr>" + html + "</tr>";
						out.println(html);
						html = "";
					}
				}	
				if(chkidx == 4)
					chkidx = 0;
			}
			
			if(chklast == "n"){
				html = "<tr>" 
					  + html
					  + "<td colspan='"+(4-chkidx)+"' class='tC'></td>" 
					  + "</tr>";
		
				out.println(html);
			}
%>		
	      </thead>
		  <tbody>
		  </tbody>
		</table>
	  	</td>
	  	</tr>	
	  	</table>
	  	

		
	<%	}else if(accessLevel.equals("A")) {
			String html = "<tr id='resultNotFoundRow'><td colspan=2 align=center><spring:message code='secfw.msg.succ.noResult' /></td></tr>";
			out.println(html);
		}
%>		
		<!-- List--> 
		<table border="0" cellspacing="0" cellpadding="0" class="tal_las01 mt20">
				<colgroup>
				<c:choose>
					<c:when test="${langCd=='en'}">
						<col width="11%"/>	
						<col width="89%"/>
					</c:when>
					<c:when test="${langCd=='fr'}">
						<col width="14%"/>	
						<col width="86%"/>
					</c:when>
					<c:when test="${langCd=='de'}">
						<col width="11%"/>	
						<col width="89%"/>
					</c:when>
					<c:otherwise>
						<col width="11%"/>	
						<col width="89%"/>
					</c:otherwise>
				</c:choose>
				</colgroup>
				
				  <thead>
					<tr>
					 <th><spring:message code="las.page.field.statistics.schedule" /></th>
					  <th><spring:message code="las.page.field.statistics.perform" /></th>
					 
					</tr>
				  </thead>
				  <tbody>
	<%		if(resultList.size() >0){ %>				  
				  <tr>
					  <td colspan="2" style='padding:4px 10px;' class='tL'>
							<span class="gray_t_arw2"><spring:message code="las.page.field.statistics.contract" /></span>
					  </td>
				  </tr>					  
	<%		}
			// 계약 실적
			if(resultList!=null)
			for(int idx=0;idx < resultList.size();idx++){			    
			   ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);			    	
	%>									
 
					<tr>
						<td class="cor_th01 ">
						<%=((String)lom.get("reg_dt")).substring(0,4)+"-"+((String)lom.get("reg_dt")).substring(4,6)+"-"+((String)lom.get("reg_dt")).substring(6,8) %>
						<input type="checkbox" style="display:none;" id='seqnos1' name='seqnos1' value='<%=lom.get("seqno") %>' onClick="javascript:chkSeqnos(<%=idx1 %>)">
						<input type="hidden" id='seqnos' name='seqnos' value='<%=lom.get("seqno") %>'>
						<input type="hidden" id='tabs' name='tabs' value='<%=lom.get("tab") %>@R'>
						<input type="hidden" id='monthdutyyns' name='monthdutyyns' value='<%=lom.get("conf_monthdutyyn") %>'>								
						</td>
						<td class="tL ">
							<!-- 테이블안에 테이블01 -->
							<div class="">
							<table border="0" cellspacing="0" cellpadding="0" class="tal_las01_sub">
								<colgroup>
									<col width="15%" />
									<col width="*" />
									<col width="15%" />
									<col width="35%" />
								</colgroup>
								<tbody>
								<tr>
									<th><spring:message code="las.page.field.common.title" /></th>
									<td colspan="3"><%=(lom != null && lom.size() > 0)?lom.get("title"):"" %></td>								
								</tr>
								<tr>
									<th><spring:message code="las.page.field.mainproject.oppnt_comp" /></th>
									<td><%=(lom != null && lom.size() > 0)?lom.get("company"):"" %></td>
									<th><spring:message code="las.page.field.statistics.req_dept" /></th>
									<td><%=(lom != null && lom.size() > 0)?lom.get("dept_nm"):"" %></td>				
								</tr>
								<tr>
									<th><spring:message code="las.page.field.speakconsult.respman_nm" /></th>
									<td colspan="3"><%=(lom != null && lom.size() > 0)?lom.get("respman_nm"):"" %></td>
								</tr>								
								<tr>
									<td align="left" colspan="4">
									 
					    			<div class="text_area" style="height:100px; overflow:auto;">
					       			<%=(lom != null && lom.size() > 0)?lom.get("cont"):"" %>
					       			</div>
					       			</td>	
								       							
								</tr> 			
								</tbody>			
							</table>
							</div>
							<!-- 테이블안에 테이블01 -->
						</td>
						
					</tr>
	<%
			idx1++;
			}
			if(resultList2.size() > 0){
	%>	
				  <tr>
					  <td colspan="2" style='padding:4px 10px;' class='tL'>
					  		<span class="gray_t_arw2"><spring:message code="las.page.field.statistics.consult" /></span>
					  </td>
				  </tr>		
	<%		}
			// 자문 실적
			if(resultList!=null)
			for(int idx=0;idx < resultList2.size();idx++){
			    
			   ListOrderedMap lom = (ListOrderedMap)resultList2.get(idx);			    	
	%>									
						  
					<tr>
						<td class="cor_th01 ">
						<%=((String)lom.get("reg_dt")).substring(0,4)+"-"+((String)lom.get("reg_dt")).substring(4,6)+"-"+((String)lom.get("reg_dt")).substring(6,8) %>	
						<input type="checkbox" style="display:none;" id='seqnos1' name='seqnos1' value='<%=lom.get("seqno") %>' onClick="javascript:chkSeqnos(<%=idx1 %>)">
						<input type="hidden" id='seqnos' name='seqnos' value='<%=lom.get("seqno") %>'>
						<input type="hidden" id='tabs' name='tabs' value='<%=lom.get("tab") %>@R'>
						<input type="hidden" id='monthdutyyns' name='monthdutyyns' value='<%=lom.get("conf_monthdutyyn") %>'>						
						</td>
						<td class="tL ">
							<!-- 테이블안에 테이블01 -->
							<div class="">
							<table border="0" cellspacing="0" cellpadding="0" class="tal_las01_sub">
								<colgroup>
									<col width="15%" />
									<col width="*" />
									<col width="15%" />
									<col width="35%" />
								</colgroup>
								<tbody>
								<tr>
									<th><spring:message code="las.page.field.common.title" /></th>
									<td colspan="3"><%=(lom != null && lom.size() > 0)?lom.get("title"):"" %></td>							
								</tr>
								<tr>
									<th><spring:message code="las.page.field.statistics.req_dept" /></th>
									<td><%=(lom != null && lom.size() > 0)?lom.get("dept_nm"):"" %></td>
									<th><spring:message code="las.page.field.speakconsult.respman_nm" /></th>
									<td><%=(lom != null && lom.size() > 0)?lom.get("respman_nm"):"" %></td>				
								</tr>
								<tr>
									<td align="left" colspan="4">
									<div class="text_area" style="height:100px; overflow:auto;">
					    			<%=(lom != null && lom.size() > 0)?lom.get("cont"):"" %>
					       			</div>
					       			</td>						
								</tr> 			
								</tbody>			
							</table>	
	
							</div>
							<!-- 테이블안에 테이블01 -->
						</td>
						
					</tr>
	<%		idx1++;
			}

			if(resultList4.size() >0){ 
	%>				  
			  <tr>
				  <td colspan="2" style='padding:4px 10px;' class='tL'>
						<span class="gray_t_arw2"><spring:message code="las.page.field.statistics.etc" /></span>
				  </td>
			  </tr>					  
<%		}	
			// 기타 실적
			if(resultList!=null)
			for(int idx=0;idx < resultList4.size();idx++){			    
			   ListOrderedMap lom = (ListOrderedMap)resultList4.get(idx);			    	
	%>									
						  
					<tr>
						<td class="cor_th01 ">
						<%=((String)lom.get("reg_dt")).substring(0,4)+"-"+((String)lom.get("reg_dt")).substring(4,6)+"-"+((String)lom.get("reg_dt")).substring(6,8) %>	
						<input type="checkbox" style="display:none;" id='seqnos1' name='seqnos1' value='<%=lom.get("seqno") %>' onClick="javascript:chkSeqnos(<%=idx1 %>)"/>
						<input type="hidden" id='seqnos' name='seqnos' value='<%=lom.get("seqno") %>'/>
						<input type="hidden" id='tabs' name='tabs' value='<%=lom.get("tab") %>@R'/>
						<input type="hidden" id='monthdutyyns' name='monthdutyyns' value='<%=lom.get("conf_monthdutyyn") %>'/>						
						</td>
						<td class="tL ">
							<!-- 테이블안에 테이블01 -->
							<div class="">
							<table border="0" cellspacing="0" cellpadding="0" class="tal_las01_sub">
								<colgroup>
									<col width="15%" />
									<col width="*" />
									<col width="15%" />
									<col width="35%" />
								</colgroup>
								<tbody>
								<tr>
									<th><spring:message code="las.page.field.speakconsult.respman_nm" /></th>
									<td colspan="2"><%=(lom != null && lom.size() > 0)?lom.get("dept_nm"):"" %></td>											
								</tr>
								<tr>
									<td align="left" colspan="4">
					    			<div class="text_area" style="height:100px; overflow:auto;">
					       			<%=(lom != null && lom.size() > 0)?lom.get("cont"):"" %>
					       			</div>
					       			</td>	
								</tr> 			
								</tbody>			
							</table>	
	
							</div>
							<!-- 테이블안에 테이블01 -->
						</td>
						
					</tr>
	<%		idx1++;
			} %>
				
		  </tbody>
		 </table>			
	 
		<!-- List -->
		<table border="0" cellspacing="0" cellpadding="0" class="tal_las01 mt20">
				<colgroup>
				<c:choose>
					<c:when test="${langCd=='fr'}">
						<col width="14%"/>	
						<col width="86%"/>
					</c:when>
					<c:otherwise>
						<col width="11%"/>	
						<col width="89%"/>
					</c:otherwise>
				</c:choose>
				</colgroup>
				
				  <thead>
					<tr>
					<th><spring:message code="las.page.field.statistics.schedule" /></th>
					  <th><spring:message code="las.page.field.statistics.monthplan1" /></th>
					  
					</tr>
				  </thead>
				  <tbody>
	<%		if(resultList1.size() > 0){ %>				  
				  <tr>
				  <td colspan="2" style='padding:4px 10px;' class='tL'>
						<span class="gray_t_arw2"><spring:message code="las.page.field.statistics.contract" /></span>
				  </td>
				  </tr>					  
	<%		}
			// 계약 계획
			if(resultList!=null)
			for(int idx=0;idx < resultList1.size();idx++){
			   ListOrderedMap lom = (ListOrderedMap)resultList1.get(idx);
			   
	%>									
						  
					<tr>
						<td class="cor_th01 ">
						<%=((String)lom.get("reg_dt")).substring(0,4)+"-"+((String)lom.get("reg_dt")).substring(4,6)+"-"+((String)lom.get("reg_dt")).substring(6,8) %>	
						<input type="checkbox" style="display:none;" id='seqnos1' name='seqnos1' value='<%=lom.get("seqno") %>' onClick="javascript:chkSeqnos(<%=idx1 %>)">
						<input type="hidden" id='seqnos' name='seqnos' value='<%=lom.get("seqno") %>'>
						<input type="hidden" id='tabs' name='tabs' value='<%=lom.get("tab") %>@P'>
						<input type="hidden" id='monthdutyyns' name='monthdutyyns' value='<%=lom.get("conf_monthdutyyn") %>'>							
						</td>
						<td class="tL ">
							<!-- 테이블안에 테이블01 -->
							<div class="">
							<table border="0" cellspacing="0" cellpadding="0" class="tal_las01_sub">
								<colgroup>
									<col width="15%" />
									<col width="*" />
									<col width="15%" />
									<col width="35%" />
								</colgroup>
								<tbody>
								<tr>
									<th><spring:message code="las.page.field.common.title" /></th>
									<td colspan="3"><%=(lom != null && lom.size() > 0)?lom.get("title"):"" %></td>					
								</tr>
								<tr>
									<th><spring:message code="las.page.field.mainproject.oppnt_comp" /></th>
									<td><%=(lom != null && lom.size() > 0)?lom.get("company"):"" %></td>
									<th><spring:message code="las.page.field.statistics.req_dept" /></th>
									<td><%=(lom != null && lom.size() > 0)?lom.get("dept_nm"):"" %></td>				
								</tr>
								<tr>
									<th><spring:message code="las.page.field.speakconsult.respman_nm" /></th>
									<td colspan="3"><%=(lom != null && lom.size() > 0)?lom.get("respman_nm"):"" %></td>
								</tr>									
								<tr>
									<td align="left" colspan="4">
									 
					    			<div class="text_area" style="height:100px; overflow:auto;">
					       			<%=(lom != null && lom.size() > 0)?lom.get("cont"):"" %>
					       			</div>
					       			</td>	
					
								</tr> 			
								</tbody>			
							</table>	
	
							</div>
							<!-- 테이블안에 테이블01 -->
						</td>
						
					</tr>
	<%		idx1++;
			}
			if(resultList3.size() > 0){
	%>	
				  <tr>
				  <td colspan="2" style='padding:4px 10px;' class='tL'>
						<span class="gray_t_arw2"><spring:message code="las.page.field.statistics.consult" /></span>
				  </td>
				  </tr>		
	<%		}
			// 자문 계획
			if(resultList!=null)
			for(int idx=0;idx < resultList3.size();idx++){
			    
			   ListOrderedMap lom = (ListOrderedMap)resultList3.get(idx);			    	
	%>									
						  
					<tr>
						<td class="cor_th01 ">
						<%=((String)lom.get("reg_dt")).substring(0,4)+"-"+((String)lom.get("reg_dt")).substring(4,6)+"-"+((String)lom.get("reg_dt")).substring(6,8) %>	
						<input type="checkbox" style="display:none;" id='seqnos1' name='seqnos1' value='<%=lom.get("seqno") %>' onClick="javascript:chkSeqnos(<%=idx1 %>)">									
						<input type="hidden" id='seqnos' name='seqnos' value='<%=lom.get("seqno") %>'>
						<input type="hidden" id='tabs' name='tabs' value='<%=lom.get("tab") %>@P'>
						<input type="hidden" id='monthdutyyns' name='monthdutyyns' value='<%=lom.get("conf_monthdutyyn") %>'>						
						</td>
						<td class="tL ">
							<!-- 테이블안에 테이블01 -->
							<div class="">
							<table border="0" cellspacing="0" cellpadding="0" class="tal_las01_sub">
								<colgroup>
									<col width="15%" />
									<col width="*" />
									<col width="15%" />
									<col width="35%" />
								</colgroup>
								<tbody>
								<tr>
									<th><spring:message code="las.page.field.common.title" /></th>
									<td colspan="3"><%=(lom != null && lom.size() > 0)?lom.get("title"):"" %></td>								
								</tr>
								<tr>
									<th><spring:message code="las.page.field.statistics.req_dept" /></th>
									<td><%=(lom != null && lom.size() > 0)?lom.get("dept_nm"):"" %></td>
									<th><spring:message code="las.page.field.speakconsult.respman_nm" /></th>
									<td><%=(lom != null && lom.size() > 0)?lom.get("respman_nm"):"" %></td>													
								</tr>
								<tr>
									<td align="left" colspan="4">
					    			<div class="text_area" style="height:100px; overflow:auto;">
					    			<!-- <textarea name="conts" rows="14" cols="170" readonly class="text_area_full" ><%=(lom != null && lom.size() > 0)?lom.get("cont"):"" %></textarea> -->
					       			<%=(lom != null && lom.size() > 0)?lom.get("cont"):"" %>
					       			</div>
					       			</td>	
								</tr> 			
								</tbody>			
							</table>	
	
							</div>
							<!-- 테이블안에 테이블01 -->
						</td>
						
					</tr>
	<%		idx1++;
			}
			
			if(resultList5.size() >0){ %>				  
			  <tr>
			  <td colspan="2" style='padding:4px 10px;' class='tL'>
					<span class="gray_t_arw2"><spring:message code="las.page.field.statistics.etc" /></span>
			  </td>
			  </tr>					  
<%		}			
			// 기타 계획
			if(resultList!=null)
			for(int idx=0;idx < resultList5.size();idx++){
			    
			   ListOrderedMap lom = (ListOrderedMap)resultList5.get(idx);			    	
	%>									
						  
					<tr>
						<td class="cor_th01 ">
						<%=((String)lom.get("reg_dt")).substring(0,4)+"-"+((String)lom.get("reg_dt")).substring(4,6)+"-"+((String)lom.get("reg_dt")).substring(6,8) %>	
						<input type="checkbox" style="display:none;" id='seqnos1' name='seqnos1' value='<%=lom.get("seqno") %>' onClick="javascript:chkSeqnos(<%=idx1 %>)"/>
						<input type="hidden" id='seqnos' name='seqnos' value='<%=lom.get("seqno") %>'/>
						<input type="hidden" id='tabs' name='tabs' value='<%=lom.get("tab") %>@P'/>
						<input type="hidden" id='monthdutyyns' name='monthdutyyns' value='<%=lom.get("conf_monthdutyyn") %>'/>							
						</td>
						<td class="tL ">
							<!-- 테이블안에 테이블01 -->
							<div class="">
							<table border="0" cellspacing="0" cellpadding="0" class="tal_las01_sub">
								<colgroup>
									<col width="15%" />
									<col width="*" />
								</colgroup>
								<tbody>

								<tr>
									<th><spring:message code="las.page.field.speakconsult.respman_nm" /></th>
									<td><%=(lom != null && lom.size() > 0)?lom.get("dept_nm"):"" %></td>								
								</tr>
								<tr>
									<td align="left" colspan="2">
					    			<div class="text_area" style="height:100px; overflow:auto;">
					       			<%=(lom != null && lom.size() > 0)?lom.get("cont"):"" %>
					       			</div>
					       			</td>	
								</tr> 			
								</tbody>			
							</table>	
	
							</div>
							<!-- 테이블안에 테이블01 -->
						</td>
						
					</tr>
	<%		idx1++;
			} %>					 
		</tbody>
		</table>				
		<!-- //view  -->
		 
		<!--  Function Button  -->
		<div class="btn_wrap" id="btn1" style="display:none;">
			<span class='ico_alert02'><spring:message code="las.page.field.statistics.checksave"/></span> &nbsp;
			<span class="btn_all_w"><span class="save"></span><a href="javascript:modifyView()"><spring:message code="las.page.button.save"/></a></span>
		</div>
		<div class="btn_wrap" id="btn2" style="display:none;"></div>	
		<!-- //  Function Button  -->				
	
		</form:form>
		<!-- form -->
		
	</div>
	<!-- content_in -->
	</div>
	<!-- content -->
	
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- container -->
	
	</div>
	
	<!-- footer -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->
</body>
</html>