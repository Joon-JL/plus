<!DOCTYPE html>
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

<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.sec.las.statistics.dto.StatisticsForm"%>
<%
	List listRow = (List)request.getAttribute("result_list") ;
	List listCol = (List)request.getAttribute("title_list") ;
	StatisticsForm command = (StatisticsForm)request.getAttribute("command") ;
	
	String searchAuth = (String)request.getAttribute("searchAuth") ;	
%>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.form.js"></script>
<script language="javascript" src="/script/secfw/jquery/calendar/jquery.mousewheel.min.js"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script language=javascript>
	$(document).ready(function(){
	 
	}); 

    function f_scroll() {
        var x = document.all["list"].scrollLeft;
        document.all["intitle"].style.left = 0 - x; 
    }
    
    function chkType(value) {
    	if(value=="C") {
    		$("#tr_srch_div").show() ;
    	} else {
    		$("#tr_srch_div").hide() ;
    	}
    }
    
    function search() {
    	viewHiddenProgress(true);
    	
    	var f = document.frm ;
    	f.method.value = "listStatisticsNew" ;
    	f.action = "/las/statistics/statistics.do" ; 
    	f.submit() ;
    }
    
    function excelDownload() {
    	var f = document.frm ;
    	f.target = "_self";
        f.method.value = "listStatisticsExcelDown" ;
        f.action = "/las/statistics/statistics.do" ; 
        f.submit() ;
    }
    
    //*************** 라디오 버튼용 시작 ***************//
    function setRadioCl(e){ 
        var srcEl = getSrc(e);
        var ra = srcEl.form[srcEl.name];
        for(var i=0;i<ra.length;i++){
            if(ra[i].checked) ra[i].onpropertychange = function(e){getSrc(e).click();};
            else ra[i].onclick = function(){return false;};
        }
    }
    function getSrc(e)
    {
        return e? e.target || e.srcElement : event.srcElement;
    }
    //*************** 라디오 버튼용 끝   ***************//
    
    function divChange(flag){
    	viewHiddenProgress(true) ;
        var f = document.frm ;
		
        f.srch_type.value = flag;
        f.method.value = "listStatisticsNew" ;
        f.action = "<c:url value='/las/statistics/statistics.do' />" ;
        f.target = "_self" ;
        f.submit() ;
    }
    
    function divPop(target,e,sp_val){
    	var id = 'div_pop';

   		calDivPosition(target,id,e);
   		var arr_sp_val = sp_val.split('|');
   		document.getElementById("sp_val1").innerHTML = arr_sp_val[0];
   		document.getElementById("sp_val2").innerHTML = arr_sp_val[1];
   		document.getElementById("sp_val3").innerHTML = arr_sp_val[2];
   		document.getElementById("sp_val4").innerHTML = arr_sp_val[3];
   		document.getElementById("sp_val5").innerHTML = arr_sp_val[4];
   		document.getElementById("sp_val6").innerHTML = arr_sp_val[5];
   		document.getElementById("sp_val7").innerHTML = arr_sp_val[6];
   		
   		$("#" + id).show();	

    }
    

    function calDivPosition(target,id,e){
		var nv = window.navigator.appName;
		var left ;
		var top ;
		if(nv.indexOf('Microsoft')  != -1) {
			left = (event.clientX + document.body.scrollLeft + document.documentElement.scrollLeft) - 265;
			top = (event.clientY + document.body.scrollTop + document.documentElement.scrollTop) - 30;
		}else{
			left = (e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft) - 265;
			top = (e.clientY + document.body.scrollTop + document.documentElement.scrollTop)  - 30;
		}
		$("#" + id).css({"top":top , "left":left});
    }
    
    function divPop_hide(){
    	var id = 'div_pop';
    	
    	$("#" + id).hide();
    }
    
    // SELMS 통계 상세 페이지로 이동
    function goDetail(sElComp, srch_step, srch_state, srch_prgrs_status){
    	
        var f = document.frm ;
        //PopUpWindowOpen2("/clm/manage/myContract.do?method=listMyContract&sElCompe="+sElComp+"&srch_prgrs_status="+srch_prgrs_status+"&srch_step="+srch_step+"&srch_state="+srch_state+"&menu_id=20131010142659508_0000000457&status_mode=StatisticsListManage" , 1250, 650, false, "statistics");
      
        /**
			통계상세 페이지 링크
			srch_type = C (기존계약) 
			srch_type = L (기존자문) 
			srch_type = PC (현재계약) 
			srch_type = PL (현재자문)
		*/
		if("<c:out value='${command.srch_type}'/>" == "C" || "<c:out value='${command.srch_type}'/>" == "PC"){
			  
			PopUpWindowOpen2("/clm/manage/myContractPop.do?method=listMyContract&status_mode=StatisticsListManage&sElCompe="+sElComp+"&srch_state="+srch_state+"&srch_step="+srch_step+ "&closed_yn=N", 1050, 470, false, "statistics");  // 계약별 - 계약
		
		}else{
			
			/* SELMS 자문 통계 */
			if("<c:out value='${command.srch_type}'/>" == "L"){
				f.srch_prgrs_status_cd.value=srch_prgrs_status;
			
			/* SELMS PLUS 자문 통계 */
			}else if("<c:out value='${command.srch_type}'/>" == "PL"){
				f.srch_prgrs_status.value=srch_prgrs_status;
			 }else{
		    	return;
		    }
			
			f.srch_comp_cd.value = sElComp;
		    f.method.value = "listLawConsultStatistics" ;
		    f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
		    PopUpWindowOpen('', 1024, 610, true);
		    frm.target = "PopUpWindow";
		    f.submit();
		}
    }
   
</script>
</head>
<body>
<div id="wrap">
    <div id="container">        
    <!-- Location -->
	<div class="location" id="location"><IMG SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
	<!-- //Location -->	 
        
    <!-- title -->
    <div class="title" id="title">
        <h3><spring:message code="las.page.field.statistics.listStatisticsExcelDown07"/></h3><!-- Contract & Legal Advice Statistics -->
    </div>
    <!-- //title -->
        
        <!-- content -->
        <div id="content">
        	<div id="content_in">
            <form name="frm" id="frm" method="post">
            
            <input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />	        
	        <input type="hidden" name="method" id="method" value=""/>
	        <input type="hidden" name="gubun" id="gubun" value="<c:out value="${command.gubun}"/>"/>	        
	        <input type="hidden" name="srch_dmstfrgn_gbn" id="srch_dmstfrgn_gbn"  value="H" >
	        <input type="hidden" name="srch_type" id="srch_type" value="<c:out value="${command.srch_type}"/>"/>
 
	        <!-- 자문통계 전달용 -->
	        <input type="hidden" name="srch_comp_cd"		id="srch_comp_cd"  			value="" />
	        <input type="hidden" name="srch_prgrs_status"	id="srch_prgrs_status"		value="" />
	        <input type="hidden" name="srch_prgrs_status_cd"id="srch_prgrs_status_cd"	value="" />
	        <input type="hidden" name="hstry_no" 			id="hstry_no" 				value="0"/>
 
				<!-- btn -->
                <div class="btnwrap tR" id="btnwrap">
                    <span class="btn_all_w"><span class="excel_down"></span><a href="javascript:excelDownload();"><spring:message code="las.page.field.statistics.excelDw"/></a></span>
                </div>
                <!-- // btn -->
                
				<!----통계테이블 ---->
		
				<div id='div_pop' style="display:none;position:absolute;top:100px; right:20px; width:200px; height:130px; background:#fff; padding:5px; border:1px solid #6b86d4; line-height:170%;font-family: Verdana, Tahoma, Arial, Helvetica, sans-serif; font-size:11px;"> 
						<span class="iconBox icon019"></span> <spring:message code="las.page.field.statistics.contlegaladvice.sp1"/>:<span id='sp_val1'></span><br><!-- Pending(Approval) -->
						<span class="iconBox icon019"></span> <spring:message code="las.page.field.statistics.contlegaladvice.sp2"/>:<span id='sp_val2'></span><br><!-- Holding(Reply) -->
						<span class="iconBox icon019"></span> <spring:message code="las.page.field.statistics.contlegaladvice.sp3"/>:<span id='sp_val3'></span><br><!-- Approval -->
						<span class="iconBox icon019"></span> <spring:message code="las.page.field.statistics.contlegaladvice.sp4"/>:<span id='sp_val4'></span><br><!-- Processing -->
						<span class="iconBox icon019"></span> <spring:message code="las.page.field.statistics.contlegaladvice.sp5"/>:<span id='sp_val5'></span><br><!-- Approval Completed -->
						<span class="iconBox icon019"></span> <spring:message code="las.page.field.statistics.contlegaladvice.sp6"/>:<span id='sp_val6'></span><br><!-- Approval Rejected -->
						<span class="iconBox icon019"></span> <spring:message code="las.page.field.statistics.contlegaladvice.sp7"/>:<span id='sp_val7'></span><br><!-- Withdrawn -->
						<span class="close_s" onclick="javascript:divPop_hide();" title="close"></span></div>
					
				<div id='tab01' style='display:'>
					<div class="tab_box mt20">
						<ul class="tab_basic">
							<c:if test="${command.srch_type=='PC'}">
							<li class="on"><a><spring:message code="clm.page.field.regist.makeApprovalReqInfo01"/></a></li><!-- Contracts Request -->
							</c:if>
							<c:if test="${command.srch_type!='PC'}">
							<li onclick="divChange('PC')"><a><spring:message code="clm.page.field.regist.makeApprovalReqInfo01"/></a></li><!-- Contracts Request -->
							</c:if>
							
							<c:if test="${command.srch_type=='PL'}">
							<li class="on"><a><spring:message code="las.page.field.statistics.contlegaladvice.legaladvicereq"/></a></li><!--Legal Advice Request-->
							</c:if>
							<c:if test="${command.srch_type!='PL'}">
							<li onclick="divChange('PL')"><a><spring:message code="las.page.field.statistics.contlegaladvice.legaladvicereq"/></a></li><!--Legal Advice Request-->
							</c:if>
							
							<c:if test="${command.srch_type=='C'}">
							<li class="on"><a><spring:message code="las.page.field.statistics.contlegaladvice.contreq"/></a></li><!--SELMS Contracts Request -->
							</c:if>
							<c:if test="${command.srch_type!='C'}">
							<li onclick="divChange('C')"><a><spring:message code="las.page.field.statistics.contlegaladvice.contreq"/></a></li><!--SELMS Contracts Request -->
							</c:if>
							
							<c:if test="${command.srch_type=='L'}">
							<li class="on"><a><spring:message code="las.page.field.statistics.contlegaladvice.otherlegreq"/></a></li><!--SELMS Legal Advice Request -->
							</c:if>
							<c:if test="${command.srch_type!='L'}">
							<li onclick="divChange('L')"><a><spring:message code="las.page.field.statistics.contlegaladvice.otherlegreq"/></a></li><!--SELMS Legal Advice Request -->
							</c:if>
						</ul>
					</div>						
			
					<c:if test="${command.srch_type=='C' || command.srch_type=='L'}">
					<table  class="list_basic_new">
						<!-- SELMS C, L -->
						<colgroup>
						<col width="10%" />
						<col width="10%" />
						<col width="10%" />
						<col width="10%" />
						<col width="10%" />
						<col width="10%" />
						<col width="10%" />
						<col width="10%" />
						<col width="10%" />
						<col width="10%" />
						<col width="5%" />
						</colgroup>	
						<thead>
							<tr>
								<th><spring:message code="las.page.field.statistics.contlegaladvice.contries"/></th>     <!-- Contries        -->
								<th><spring:message code="las.page.field.statistics.contlegaladvice.tcount"/></th> <!-- Total Count     -->
								<th><spring:message code="las.page.field.statistics.contlegaladvice.pending"/></th>      <!-- Pending         -->
								<th><spring:message code="las.page.field.statistics.contlegaladvice.replied"/></th>      <!-- Replied         -->
								<th><spring:message code="las.page.field.statistics.contlegaladvice.rerequest"/></th>    <!-- Rerequest       -->
								<th><spring:message code="las.page.field.statistics.contlegaladvice.holding"/></th>      <!-- Holding         -->
								<th><spring:message code="las.page.field.statistics.contlegaladvice.executed"/></th>     <!-- Executed        -->
								<th><spring:message code="las.page.field.statistics.contlegaladvice.returned"/> </th>    <!-- Returned        -->
								<th><spring:message code="las.page.field.statistics.contlegaladvice.dnp"/></th>          <!-- DNP             -->
								<th><spring:message code="las.page.field.statistics.contlegaladvice.leftbiz"/></th><!-- Left Business   -->
								<th><spring:message code="las.page.field.statistics.contlegaladvice.etc"/></th>          <!-- Etc             -->
							</tr>
						</thead>
						<!-- SELMS C, L END-->
						</c:if>
					
					<!-- SELMSPLUS PC, PL -->	
					<c:if test="${command.srch_type=='PC'}">	
					<DIV style="OVERFLOW-X: scroll; OVERFLOW-Y: hidden; WIDTH: 100%">
					<table  class="list_table2">	
						<colgroup>
						<col width="70px" /><!-- Subsidiary -->
						<col width="40px" /><!-- Total Count -->
						<!--<col width="70px" />--><!-- Draft Saved -->
						<col width="40px" /><!-- Draft Saved -->
						<col width="60px" /><!-- Review in Progress -->
						<col width="50px" /><!-- Holding -->
						<col width="50px" /><!-- Admin Replied -->
						<col width="50px" /><!-- Replied -->
						<col width="70px" /><!-- Approval in Progress -->
						<col width="40px" /><!-- Draft Saved -->
						<col width="50px" /><!-- Holding -->
						<col width="52px" /><!-- Cancel Approval -->
						<col width="50px" /><!-- Signing -->
						<col width="80px" /><!-- Hard copy not Delivered -->
						<col width="70px" /><!-- In Progress -->
						<col width="60px" /><!-- Cancelled -->
						<col width="50px" /><!-- Expired -->
						<col width="60px" /><!-- Will be terminated -->
						<col width="70px" /><!-- Approval in Progress -->
						<col width="60px" /><!-- Extension Review -->
						<col width="60px" /><!-- Executed Contract -->
						</colgroup>	
						<thead>
							<tr>
	                            <th rowspan="2"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle03"/></th><!-- Subsidiary -->
	                            <th rowspan="2"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle04"/></th><!-- Total Count -->
	                            <!--<th><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle25"/></th> --><!-- Request -->
	                            <th colspan="5"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle18"/></th><!-- Review -->
                                <th colspan="4"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle19"/></th><!-- Conclusion -->
                                <th colspan="2"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle20"/></th><!-- Registration -->
                                <th><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle21"/></th><!-- Execution -->
                                <th colspan="5"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle22"/></th><!-- Termination -->
                                <th rowspan="2"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle24"/></th><!-- Executed Contract -->
                            </tr>
	                        <tr>
	                        	<!-- <th class="sub01"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle01"/></th> --><!-- Draft Saved -->
	                            <th class="sub01"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle01"/></th><!-- Draft Saved -->
	                            <th class="sub01"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle02"/></th><!-- Review in Progress -->
	                            <th class="sub01"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle05"/></th><!-- DNP -->
	                            <th class="sub01"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle23"/></th><!-- Admin Replied -->
	                            <th class="sub01"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle06"/></th><!-- Replied -->
	                            
	                            <th class="sub01"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle07"/></th><!-- Approval in Progress -->
                                <th class="sub01"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle01"/></th><!-- Draft Saved -->
                                <th class="sub01"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle05"/></th><!-- DNP -->
                                <th class="sub01"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle08"/></th><!-- Cancel Approval -->
                                
                                <th class="sub01"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle09"/></th><!-- Signing -->
                                <th class="sub01"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle10"/></th><!-- Hard copy not Delivered -->
                                
                                <th class="sub01"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle11"/></th><!-- In Progress -->
                                
                                <th class="sub01"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle12"/></th><!-- Cancelled -->
                                <th class="sub01"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle13"/></th><!-- Expired -->
                                <th class="sub01"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle14"/></th><!-- Will be terminated -->
                                <th class="sub01"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle07"/></th><!-- Approval in Progress -->
                                <th class="sub01"><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle16"/></th><!-- Extension Review -->
	                        </tr>
	                    </thead>
						</c:if>
						
					<c:if test="${command.srch_type=='PL'}">
					<table  class="list_basic_new">
						<colgroup>
						<col width="10%" />
						<col width="10%" />
						<col width="10%" />
						<col width="10%" />
						<col width="10%" />
						<col width="10%" />
						<col width="10%" />
						<col width="10%" />
						</colgroup>	
						<thead>
							<tr>
								<th><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle03"/></th><!-- Subsidiary -->
								<th><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle04"/></th><!-- Total Count -->
								<th><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle17"/></th><!-- Not Assigned -->
								<th><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle02"/></th><!-- Review in Progress -->
								<th><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle06"/></th><!-- Replied -->
								<th><spring:message code="las.page.field.statistics.contlegaladvice.tabtitle05"/></th><!-- Holding -->
								<th><spring:message code="las.page.field.statistics.contlegaladvice.returned"/></th>  <!-- Returned -->
								<th><spring:message code="las.msg.succ.tempSave"/></th>								  <!-- Saved -->
							</tr>
						</thead>
						</c:if>
	                    <!-- SELMS PC, PL END-->    
	                   	    
						
						<tbody>
							
					<%	
						StringBuffer sb = new StringBuffer();
						sb.append("");
						int ietc_total = 0;
						
						String rowComp_cd = "";
						String view_user_comp = (String)session.getAttribute("secfw.session.comp_cd");
						
						for(int i=0; listRow!=null && i<listRow.size(); i++){ 
					        Map rowMap = (Map)listRow.get(i) ;
					        
					        if(command != null && command.getSrch_type() != null && ( command.getSrch_type().equals("C") || command.getSrch_type().equals("L") ) 
					        ){
						      	sb.append("var v_sp_val_"+i+"=\"")
						      	  .append(rowMap.get("pending(approval)")).append("|")
						      	  .append(rowMap.get("holding(reply)")).append("|")
						      	  .append(rowMap.get("approval")).append("|")
						      	  .append(rowMap.get("processing")).append("|")
						      	  
						      	  .append(rowMap.get("approval completed")).append("|")
						      	  .append(rowMap.get("approval rejected")).append("|")
						      	  .append(rowMap.get("withdrawn"))
						      	  .append("\";\n");
						      	
						      	ietc_total = (Integer.parseInt(rowMap.get("etc_total").toString()));
					        }
					        
					        rowComp_cd = (String)rowMap.get("comp_cd");
					        
					        if("EHQ".equals(rowComp_cd)){
					      		rowComp_cd = "EHQSEUK";
					      	}
					        
					        if("SEUK".equals(rowComp_cd)){
				      			rowComp_cd = "EHQSEUK";
				      		}
					      	
					      	if("EHQ".equals(view_user_comp)){
					      		view_user_comp  = "EHQSEUK";
					      	}
					      	
					      	if("SEUK".equals(view_user_comp)){
				      			view_user_comp = "EHQSEUK";
				      		}
					      	
					%>
							<c:if test="${command.srch_type=='C' || command.srch_type=='L'}">
							<% if(rowComp_cd.equals(view_user_comp)) { %>
							        <tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
							            <!-- 로그인 사람의 comp_cd와 DB에서 가지고온 comp_cd가 같은면 링크 생성하는 곳으로 이동함.
							                 goDetail(sElComp, srch_prgrs_status, srch_step, srch_state) -->
							                <td class="tC"><%=rowMap.get("comp_cd") %></td>
							                <td class="tC overflow"><%=rowMap.get("total") %></td>
									        <td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02506','C0250601','S02')"><%=rowMap.get("pending") %></a></td>
									        <td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02506','C0250602','S03')"><%=rowMap.get("replied") %></a></td>
									        <td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02506','C0250603','S04')"><%=rowMap.get("rerequest") %></a></td>
									        <td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02506','C0250604','S05')"><%=rowMap.get("holding") %></a></td>
									        <td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02506','C0250605','')"><%=rowMap.get("executed") %></a></td>
									        <td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02506','C0250606','S07')"><%=rowMap.get("returned") %></a></td>
									        <td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02506','C0250607','')"><%=rowMap.get("dnp") %></a></td>
									        <td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02506','C0250608','')"><%=rowMap.get("left business") %></a></td>
									        <td class="tC" id='td_etc_<%=i%>'>
									        <%if(ietc_total==0){%>
									            <%=rowMap.get("etc_total") %>
									        <%}else{%>
									            <a href='#' onclick='divPop("td_etc_<%=i%>",event,v_sp_val_<%=i%>);'>
									                <%=rowMap.get("etc_total") %>
									            </a>
									        <%} %>
									        </td>
									</tr>
							    <% } else { %>
                         		    <tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
							                <td class="tC"><%=rowMap.get("comp_cd") %></td>
							                <td class="tC overflow"><%=rowMap.get("total") %></td>
									        <td class="tC"><%=rowMap.get("pending") %></td>
									        <td class="tC"><%=rowMap.get("replied") %></td>
									        <td class="tC"><%=rowMap.get("rerequest") %></td>
									        <td class="tC"><%=rowMap.get("holding") %></td>
									        <td class="tC"><%=rowMap.get("executed") %></td>
									        <td class="tC"><%=rowMap.get("returned") %></td>
									        <td class="tC"><%=rowMap.get("dnp") %></td>
									        <td class="tC"><%=rowMap.get("left business") %></td>
									        <td class="tC" id='td_etc_<%=i%>'>
									        <%if(ietc_total==0){%>
									            <%=rowMap.get("etc_total") %>
									        <%}else{%>
									            <a href='#' onclick='divPop("td_etc_<%=i%>",event,v_sp_val_<%=i%>);'>
									                <%=rowMap.get("etc_total") %>
									            </a>
									        <%} %>
									        </td>
									</tr>					
							    <% } %>
							</c:if>
							
							<c:if test="${command.srch_type=='PC'}">
														
								<% if(rowComp_cd.equals(view_user_comp)) { %>
								<!-- ** 상태코드 **  
									-- Requuest
									C04201	CNSD_LEVEL = 1 Request Draft saved
									
									-- Review
									
									C02501	C02601	Draft Saved
									C02501	C02606	Review in Progress
									C02501	C02607	Holding
									C02501	C02609	Admin Replied
									C02501	C02608	Replied
									
									-- Conclusion
									C02502	C02622	Approval in Progress
									C02502	C02621	Draft Saved
									C02502	C02627	Holding
									C02502	C02624	Cancellation of Approval
									
									-- Registration
									C02503	C02641	Unchecked
									C02503	C02642	Hard copy not delivered
									
									-- Execution
									C02504	C02662	In Progress
									
									-- Termination
									C02505	C02688	Termination (Cancellation)
									C02505	C02686	Termination (Expiration)
									C02505	C02681	Subject to Termination
									C02505	C02682	Approval in Progress
									C02505	C02687	Termination (Revision)
									
									-- Executed Contract
									C02507			Executed Contract
								-->								
								<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
									<td class="tC"><%=rowMap.get("comp_cd") %></td>
									<td class="tC overflow"><%=rowMap.get("total") %></td>
									<!-- 2014-02-03 Kevin added. -->
									<!--<td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C04201','','')"><%=rowMap.get("DraftSaved") %></td> -->
									<td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02501','C02601','')"><%=rowMap.get("review1") %></td>          	
									<td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02501','C02606','')"><%=rowMap.get("review2") %></td>        
									<td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02501','C02607','')"><%=rowMap.get("review3") %></td>      
									<!-- 2014-02-03 Kevin added. -->
									<td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02501','C02609','')"><%=rowMap.get("AdminReplied") %></td> 
									<td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02501','C02608','')"><%=rowMap.get("review4") %></td>        
									<td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02502','C02622','')"><%=rowMap.get("conclusion1") %></td>    
									<td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02502','C02621','')"><%=rowMap.get("conclusion2") %></td>    
									<td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02502','C02627','')"><%=rowMap.get("conclusion3") %></td>    
									<td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02502','C02624','')"><%=rowMap.get("conclusion4") %></td>    
									<td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02503','C02641','')"><%=rowMap.get("registration1") %></td>  
									<td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02503','C02642','')"><%=rowMap.get("registration2") %></td>  
									<td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02504','C02662','')"><%=rowMap.get("execution1") %></td>     
									<td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02505','C02688','')"><%=rowMap.get("termination1") %></td>   
									<td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02505','C02686','')"><%=rowMap.get("termination2") %></td>   
									<td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02505','C02681','')"><%=rowMap.get("termination3") %></td>   
									<td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02505','C02682','')"><%=rowMap.get("termination4") %></td>   
									<td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02505','C02685','')"><%=rowMap.get("termination5") %></td>  
									<!-- 2014-02-03 Kevin added. -->
									<td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','C02507','','')"><%=rowMap.get("ExecutedContract") %></td>   
							<% } else { %>
								<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
									<td class="tC"><%=rowMap.get("comp_cd") %></td>
									<td class="tC overflow"><%=rowMap.get("total") %></td>
									<!-- 2014-02-03 Kevin added. -->
									<!--<td class="tC"><%=rowMap.get("DraftSaved") %></td> -->
									<td class="tC"><%=rowMap.get("review1") %></td>
									<td class="tC"><%=rowMap.get("review2") %></td>
									<td class="tC"><%=rowMap.get("review3") %></td>
									<!-- 2014-02-03 Kevin added. -->
									<td class="tC"><%=rowMap.get("AdminReplied") %></td>
									<td class="tC"><%=rowMap.get("review4") %></td>
									<td class="tC"><%=rowMap.get("conclusion1") %></td>
									<td class="tC"><%=rowMap.get("conclusion2") %></td>
									<td class="tC"><%=rowMap.get("conclusion3") %></td>
									<td class="tC"><%=rowMap.get("conclusion4") %></td>
									<td class="tC"><%=rowMap.get("registration1") %></td>
									<td class="tC"><%=rowMap.get("registration2") %></td>
									<td class="tC"><%=rowMap.get("execution1") %></td>
									<td class="tC"><%=rowMap.get("termination1") %></td>
									<td class="tC"><%=rowMap.get("termination2") %></td>
									<td class="tC"><%=rowMap.get("termination3") %></td>
									<td class="tC"><%=rowMap.get("termination4") %></td>
									<td class="tC"><%=rowMap.get("termination5") %></td>
									<!-- 2014-02-03 Kevin added. -->
									<td class="tC"><%=rowMap.get("ExecutedContract") %></td>
							</tr>
							<%} %>
							</c:if>
							
							<c:if test="${command.srch_type=='PL'}">
							<% if(rowComp_cd.equals(view_user_comp)) { %>
							        <tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
							            <!-- 로그인 사람의 comp_cd와 DB에서 가지고온 comp_cd가 같은면 링크 생성하는 곳으로 이동함.
							                 goDetail(sElComp, srch_prgrs_status, srch_step, srch_state) -->
							                 
							            <!--  상태코드 
								            V01 - Not Assigned			
								            V02 - Review in Progress 	
								            S03 - Replied 				
								            S05 - Holding 				
								            S07 - Returned 			
								            S09 - Saved 				
							             -->
							                <td class="tC"><%=rowMap.get("comp_cd") %></td>                                                                           <!-- Subsidiary 			-->            
							                <td class="tC overflow"><%=rowMap.get("total") %></td>					                                                  <!-- Total Count 			-->           
									        <td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','','','V01')"><%=rowMap.get("col1") %></a></td> <!-- Not Assigned			-->          
											<td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','','','V02')"><%=rowMap.get("col2") %></a></td> <!-- Review in Progress 	-->    
											<td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','','','S03')"><%=rowMap.get("col3") %></a></td> <!-- Replied 				-->               
											<td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','','','S05')"><%=rowMap.get("col4") %></a></td> <!-- Holding 				-->               
											<td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','','','S07')"><%=rowMap.get("col5") %></a></td> <!-- Returned 			-->              
											<td class="tC"><a href="javascript:goDetail('<%=rowMap.get("comp_cd") %>','','','S09')"><%=rowMap.get("col6") %></a></td> <!-- Saved 				-->                 
									</tr>
							<% } else { %>
								<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
									<td class="tC"><%=rowMap.get("comp_cd") %></td>
									<td class="tC overflow"><%=rowMap.get("total") %></td>
									<td class="tC"><%=rowMap.get("col1") %></td>
									<td class="tC"><%=rowMap.get("col2") %></td>
									<td class="tC"><%=rowMap.get("col3") %></td>
									<td class="tC"><%=rowMap.get("col4") %></td>
									<td class="tC"><%=rowMap.get("col5") %></td>
									<td class="tC"><%=rowMap.get("col6") %></td>
								</tr>
							<%} %>
							</c:if>
					<%  } %>
					 	
						</tbody>
					</table>
					
					<c:if test="${command.srch_type=='PC' || command.srch_type=='PL'}">	
					</div>
					</c:if>
					
				</div>
			</div>	
			<!----// 통계테이블 ---->
             </form>
             
             
            </div>
			<!-- //content_in -->
        </div>
        <!-- //content -->
        <jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>    
       	
    </div>
    <!-- //container -->
</div>
<!-- //wrap -->


 
<!-- footer -->
<script language="JavaScript" src="/script/clms/footer.js"></script>
<!-- //footer -->
<script type="text/javascript">
	<%=sb.toString() %>
</script>
</body>
</html>
