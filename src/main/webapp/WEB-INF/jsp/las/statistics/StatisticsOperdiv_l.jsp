<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.Token" %>
<%@ page import="com.sec.las.statistics.dto.StatisticsForm" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>  
<%@ page import="enc.*" %> 
<%@ page import="java.util.HashMap" %>
<%--
/**
 * 파     일     명 : StatisticsOperdiv_l.jsp
 * 프로그램명 : 사업부별 통계 현황 조회
 * 설               명 : 
 * 작     성     자 : 서백진
 * 작     성     일 : 2011.09
 */
--%>
<%
	//메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil") ;
	ArrayList resultList = (ArrayList)request.getAttribute("resultList");
	String curpage = (String)request.getAttribute("curPage");
	String returnMessage = (String)request.getAttribute("returnMessage");
	HttpSession hs = request.getSession(false);
	StatisticsForm statisticsForm = (StatisticsForm)request.getAttribute("command") ;
	String locale = statisticsForm.getDmstfrgn_gbn();
	String pattern = "0.##";
	DecimalFormat df = new DecimalFormat(pattern);
	pattern = "0,000";
	DecimalFormat df1 = new DecimalFormat(pattern);
	String report_url   = "";
	String img_url   	= "";
	
	int t_req1 =0 ;
	int t_re_req1=0;
	int t_reply1=0;
	float t_reply_time1=0;	

	if(statisticsForm!=null){			
		if(langCd.startsWith("ko")){
			report_url = statisticsForm.getReport_url()+"/report/" ;
		}
		else{
			report_url = statisticsForm.getReport_url()+"/report/" + langCd + "_" ;
		}
		img_url = statisticsForm.getReport_url()+IMAGE+"/common/" ;
	}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.page.title.statistics.StatisticsDept" /></title>

<LINK href="<%=CSS%>/popup.css"   type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet">  
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/CommonProgress.js' />"></script>
<script language="javascript">
<!--

	$(function() {
	
		$(document).keydown(function(event){
	
		});
	});
	
	function showMessage(msg){
		if(msg != null && msg.length > 1) {
			alert(msg);
		}
	}	
	/**
	* 목록 조회
	*/

	function listStatistics(displayYn, page){
		var frm = document.frm;

	
		frm.target = "_self";
		frm.action = "<c:url value='/las/statistics/statistics.do' />";
		frm.method.value = "forwardStatistics";

		if("N" != displayYn)
			viewHiddenProgress(true) ;
	
		frm.submit();
		
		if("N" != displayYn)
			progressWinClose();		
	
	}
	function rdOpen() {
		var frm = document.frm;
		var srch_year="";
		if(frm.srch_year)
			srch_year = frm.srch_year.value;
<%
		int type=2;
		Date cal= new Date();
		int iYear = cal.getYear()+2000-100;
		String mrd = "StatisticsDept_l1.mrd"; 
		String mrd_param = "";
		String param = "";

		mrd_param = 			
			" /rchartactive "+
			"/rv image_url["+img_url+"logo.jpg]"+
			" image_confi_url["+img_url+"confidential.jpg]"+
			"/riprnmargin";	
		//param = new String(C.process(mrd_param,type));
%>		
		reportPop("/rp ["+srch_year+"]"+'<%=mrd_param%>', '<%=report_url+mrd%>', '<%=mrd%>');	
	    //return true;
	}	

    function reportPop(Params , URL, mrd){ 
    	
    	var frm = document.frm;
        frm.target = "PopUpWindow";
        frm.action = "<c:url value='/las/statistics/statistics.do' />";
        frm.method.value = "reportPopup";
        frm.report_param.value = Params;
        frm.report_url.value = URL; 
       	PopUpWindowOpen('', 1000, 450, true);
		frm.submit();
    }	
//-->
</script>

</head>
<body onLoad="showMessage('<%=returnMessage %>');">  

<!-- container -->
<div id="wrap">	
<div id="container">	
<form:form id="frm" name="frm" method="post" autocomplete="off">
	<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
	<!-- 페이지 공통 -->	
	<input type="hidden" name="method"   value="">
	<input type="hidden" name="doSearch" value="<c:out value='${command.doSearch}'/>">
	<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>">	
	<input type="hidden" name="transfer" value="dept1">
	
	<input type="hidden" name="report_param"   value="">
	<input type="hidden" name="report_url"   value="">	
	<!-- 이중등록 방지 -->
	<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>">
	<div class="location">
		<img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/>		
		<c:out value='${menuNavi}' escapeXml="false"/>
	</div>	
	<!-- content -->
		<!-- Title -->
		<div class="title">
		<h3><spring:message code="las.page.title.statistics.StatisticsDept1" /></h3>
		</div> 		
	<div id="content">
	<!--  년도 검색 필요시 검색조건 주석 제거후 쿼리는 그대로 사용 -->
		<!-- search-->
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
								<col width="7%"/>
								<col width="10%"/>		
								<col width="7%"/>
								<col width="76%"/>														
							</colgroup>
							<tr>
							<th><spring:message code="las.page.field.statistics.theYear"/></th>
							<td>
							
		    			    <select name="srch_year" id="srch_year" class="select" >
		    			    <%

		    			    String sYear 		= "";
		    			    for(int i=0;i < 6 ; i++){	
		    			    	int iYear1 = iYear-i;
		    			    %>
						    <c:choose>
						    	<c:when test="${command.srch_year==null}">
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
						    		if(statisticsForm.getSrch_year() != null && statisticsForm.getSrch_year().equals(Integer.toString(iYear1))){
						    			sYear = iYear1+"";
						    			out.println("<option value='"+iYear1+"' selected>"+iYear1+"</option>");
						    		}else if(statisticsForm.getSrch_year() == null){
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
		    			    </select><spring:message code="las.page.title.report.Year" />
							</td>
							<td></td>
							<td></td>
						</table>
					</td>
					
					<td class="vb tC">
					<a href="javascript:listStatistics('Y','1');"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code='las.page.button.search' />"/></a>
					</td>
					</tr>
				</table>
			</div>
  		</div>
  		
		<!-- //Search -->
		 
			<div class="btn_wrap fR">
				<span class="btn"><span class="excel_down"></span><a href="javascript:rdOpen();"><spring:message code="las.page.field.statistics.excelDw"/></a></span>
			</div>
		<!-- list -->
		<table class="list_basic">
		  <colgroup>
			<!-- 
			<col width="150px"/>		    
		    <col width="150px"/>
		    <col width="150px"/>
		    <col width="150px"/>
		    <col width="150px"/>
		     -->
			<col width="20%"/>		    
		    <col width="20%"/>
		    <col width="20%"/>
		    <col width="20%"/>
		    <col width="20%"/>	
			<col />			
	      </colgroup>
		  <thead>
		    <tr>
		      <th rowspan="2"><spring:message code='las.page.field.statistics.gubun' /></th>
		      <th colspan="4"><spring:message code='las.page.field.statistics.perform5' /></th>
		      <!-- 
		      <th rowspan="2"><spring:message code='las.page.field.statistics.bigo' /></th>
		       -->
	        </tr>
			<tr>
				<th class="tal_lineL tal_bg_cor01"><spring:message code='las.page.field.statistics.req' /></th>
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.re_req' /></th>
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.reply' /></th>
				<th class="tal_bg_cor01"><spring:message code='las.page.field.statistics.replytime1' /></th>
															
			</tr>
	      </thead>
		  <tbody>

	<%
		int t_reply_cnt1 = 0;
		if(pageUtil != null && pageUtil.getTotalRow() > 0){
			int idx = (pageUtil.getThisPage() -1)*pageUtil.getRowPerPage()+1;
			for(idx=0;idx < resultList.size();idx++){
			    
				ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
				t_req1          = t_req1        + Integer.parseInt(lom.get("req1").toString());                  
				t_re_req1       = t_re_req1     + Integer.parseInt(lom.get("re_req1").toString());                    
				t_reply1        = t_reply1      + Integer.parseInt(lom.get("reply1").toString());    
				t_reply_time1   = t_reply_time1 + Float.parseFloat(lom.get("reply_time1").toString())-1; 	
				
				if(Integer.parseInt(lom.get("reply1").toString()) > 0)
					t_reply_cnt1 = t_reply_cnt1 + 1;				
			}				
		%>	
			<tr>
			    <td><spring:message code='las.page.field.statistics.totcnt' /></td>		
			    <td class="tR"><%=t_req1>=1000?df1.format(t_req1):t_req1%></td>	
			    <td class="tR"><%=t_re_req1>=1000?df1.format(t_re_req1):t_re_req1%></td>	
			    <td class="tR"><%=t_reply1>=1000?df1.format(t_reply1):t_reply1%></td>			    
			    <td class="tR"><%=(t_reply_cnt1 > 0)?df.format(t_reply_time1/t_reply_cnt1):"0"%></td>
			    <!-- 
			    <td></td>
			     -->
			</tr>		
		
		<%				
			for(idx=0;idx < resultList.size();idx++){
			    
			   ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);			    	
	%>
	          <tr>

			    <td class="tL"><%=lom.get("req_dept") %></td>
			    <td class="tR"><%=(Integer)lom.get("req1")>=1000?df1.format((Integer)lom.get("req1")):(Integer)lom.get("req1")%></td>	
			    <td class="tR"><%=(Integer)lom.get("re_req1")>=1000?df1.format((Integer)lom.get("re_req1")):(Integer)lom.get("re_req1")%></td>	
			    <td class="tR"><%=(Integer)lom.get("reply1")>=1000?df1.format((Integer)lom.get("reply1")):(Integer)lom.get("reply1")%></td>				    
			    <td class="tR"><%= df.format(Float.valueOf((String)lom.get("reply_time1")).floatValue()-1)%></td>
			    <!--  
			    <td></td>
			    -->			    			    			    	         
	          </tr>
	<%
			}
		} else { // 조회된 데이타가 없을경우
	%>
			  <tr>
			     <td colspan="6"><spring:message code="las.msg.succ.noResult" /></td>
			   </tr>
	<%
		}
	%>	
				
		  </tbody>
		  </table>
		<!-- 페이징 처리 
		<div class="pagination"><%=pageUtil!=null?pageUtil.getPageNavi(pageUtil, ""):"" %></div>
		-->		  
	</div>		
		<!-- //List -->
	</form:form>
	<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</div>
<!-- //container -->
	</div>
</body>
</html>