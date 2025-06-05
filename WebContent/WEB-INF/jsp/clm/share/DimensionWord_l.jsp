<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.admin.dto.DimensionForm" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%-- 
/**
 * 파  일  명 : DimensionWord_l.jsp
 * 프로그램명 : 계약용어집
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
    DimensionForm command = (DimensionForm)request.getAttribute("command");
	List resultList = (List)request.getAttribute("resultList");
	int srch_rule_no = command.getSrch_rule_no();//검색시넘어오는규정번호
	int rule_no      = 0;//규정번호
	int up_rule_no   = 0;//상위규정번호
	int rule_depth   = 0;//규정단계
	int rule_srt     = 0;//규정순서
	int rule_cnt     = 0;//규정번호의전체카운트
	int rn           = 0;//규정번호의 rownumber
	
	//검색 시 넘어오는 규정번호에 해당하는 depth onLoad 시 펼치기 위해.
	if(resultList != null && resultList.size() > 0){
		for(int i = 0 ; i < resultList.size() ; i++){
			ListOrderedMap result = (ListOrderedMap)resultList.get(i);
			rule_no = Integer.parseInt(String.valueOf(result.get("rule_no")));
			
			if(srch_rule_no == rule_no){
				up_rule_no = Integer.parseInt(String.valueOf(result.get("up_rule_no")));
				rule_depth = Integer.parseInt(String.valueOf(result.get("rule_depth")));
				rule_srt   = Integer.parseInt(String.valueOf(result.get("rule_srt")));
				rn         = Integer.parseInt(String.valueOf(result.get("up_rule_no_rn")));
				rule_cnt   = Integer.parseInt(String.valueOf(result.get("cnt")));
			}
		}
	}
	
	String   menu_id   = command.getMenu_id();    // 메뉴 아이디로 3개의 화면을 컨트롤 한다.
	    String   menuGubun = "";

	if(menu_id.equals("20111101205129964_0000000282") || menu_id.equals("20130319155933166_0000000380")){   // 계약 용어집
	    	menuGubun = "C05302";
    }  else if(menu_id.equals("20111106105940035_0000000283") || menu_id.equals("20111021143617885_0000000236")) {  // 계약 유형별 이해
    	menuGubun = "C05303";
    } else {
    	menuGubun = ""; // 값을 가지고 오지 못할 경우 
    }
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<LINK href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet">

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript">

	/*
	* 각 조의 상세내역 보여주기 
	*/
 	function detailShow(top, depth, srt, rn, cnt){
 		for(var i = 2 ; i <= cnt ; i++){
			if(rn == i){//클릭 시 class 변경.
			}else{//클릭 한 나머지 class 변경.
				$('#rl_'+top+'_'+depth+'_'+i+'_'+cnt).removeClass("selectOn").addClass("select");
				$('#rl_'+top+'_'+depth+'_'+i+'_'+cnt).removeClass("select end02").addClass("select");
			}
		}
 		
	    var val = $('#rl_'+top+'_'+depth+'_'+srt).css("display");
		if(val == "none"){
			$('#rl_'+top+'_'+depth+'_'+srt).attr("style", "display:");
			$('#rl_'+top+'_'+depth+'_'+rn+'_'+cnt).removeClass("select").addClass("selectOn");
			$('#rl_'+top+'_'+depth+'_'+eval(rn-1)+'_'+cnt).removeClass("select").addClass("select end02");
		}else{
			$('#rl_'+top+'_'+depth+'_'+srt).attr("style", "display:none");
			$('#rl_'+top+'_'+depth+'_'+rn+'_'+cnt).removeClass("selectOn").addClass("select");
			$('#rl_'+top+'_'+depth+'_'+eval(rn-1)+'_'+cnt).removeClass("select end02").addClass("select");
  		}
		
		if(<%=srch_rule_no%> != 0){
			$('#td_'+top+'_'+depth+'_'+srt).focus();
		}
 
	}
 
	//전체 열기
 	function showCont(){
 		$('.cont').attr('style', 'display:');
 		var trCls =  $('.trCls').length ;
 		for(var i = 0 ; i < trCls ; i++){
 			$('#tr_'+i).addClass('selectOn');
 		}
 	}
 	
	//전체 닫기
 	function hideCont(){
 		$('.cont').attr('style', 'display:none');
 		var trCls =  $('.trCls').length ;
 		for(var i = 0 ; i < trCls ; i++){
 			$('#tr_'+i).removeClass('selectOn');
 		}
 	}
	
</script>
</head>

<body onLoad="detailShow(<%=up_rule_no%>, <%=rule_depth%>, <%=rule_srt%>, <%=rn%>, <%=rule_cnt%>);">

<div id="wrap">
	<!-- container -->
	<div id="container">
	    <!-- Location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
		<!-- Title -->
<%		
        if(menu_id.equals("20111028162847369_0000000278") || menu_id.equals("20130319155933166_0000000380")){   // 계약 용어집
%>			
    	<div class="title">
		    <h3><spring:message code="clm.page.title.dimension.word" /></h3>
		    <a name="p1" id="p1"></a>
		<!-- //title -->
		</div>
<%		
        }  else if(menu_id.equals("20111028162812818_0000000277") || menu_id.equals("20111021143617885_0000000236")) {  // 계약 유형별 이해
%>
    	<div class="title">
		    <h3><spring:message code="clm.page.title.dimension.infoword" /></h3>
		    <a name="p1" id="p1"></a>
		<!-- //title -->
		</div>
<%		
        } else {
%>
    	<div class="title">
		    <h3><spring:message code="clm.page.msg.common.announce002" /></h3>
		<!-- //title -->
		</div>
<%		
    }
%>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
		<div id="content_in">
		<!-- **************************** Form Setting **************************** -->
		<form:form name="frm" id='frm' method="post" autocomplete="off"> 
		<!-- search form -->
		<input type="hidden" name="method"       value="" />
		<input type="hidden" name="menu_id"      value="<c:out value='${command.menu_id}'/>" />
		<input type="hidden" name="srch_rule_no" value="<c:out value='${command.srch_rule_no}'/>" />
		
		<input type="hidden" name="menuGubun"  value="<%=menuGubun %>" />
		<!-- //
		**************************** Form Setting **************************** 
		-->
		<div class="btnwrap">
			<td><span class="btn_all_w"><span class="add"></span><a href="javascript:showCont();"><spring:message code='clm.page.button.selectAll' /></a></span></td>
		    <td><span class="btn_all_w"><span class="delete"></span><a href="javascript:hideCont();"><spring:message code='clm.page.button.selectCancel' /></a></span></td>
		</div>
		<%if(resultList != null && resultList.size() > 0){ 
			int beforeDepth = 0;
			int beforeupRuleNo = 0;
				for(int i=0; i < resultList.size(); i++){
				ListOrderedMap result = (ListOrderedMap)resultList.get(i);
				int upRuleNo = Integer.parseInt(String.valueOf(result.get("up_rule_no")));
				int	depth = Integer.parseInt(String.valueOf(result.get("rule_depth")));
				int	srt = Integer.parseInt(String.valueOf(result.get("rule_srt")));
				String dnYn = (String)result.get("dn_rule_exist_yn");
				int cnt = Integer.parseInt(String.valueOf(result.get("cnt")));
				int upRuleNoRn =  Integer.parseInt(String.valueOf(result.get("up_rule_no_rn")));
				if(depth ==1){
					if(beforeDepth > 0){
		%>
					</table>
		<%				
					}
					if(upRuleNo == 1){
		%>
					<!-- title -->
					<div class="title_basic02 mt0">
						<h4><%=(String)result.get("rule_title") %></h4>
					</div>
					<!-- //title -->
					
		<%	
					}else{
		%>
					<!-- title -->
					<div class="title_basic02 mt15">
						<h4><%=(String)result.get("rule_title") %></h4>
					</div>
					<!-- //title -->
		<%
					}
				}else{
					if(beforeDepth == 1){
		%>
						<table class="table-style01">
		<%
					}
					if(depth == 2 && srt == 1){
		%>
						<tr id="tr_<%=i %>" class="trCls" onClick="javascript:detailShow(<%=beforeupRuleNo%>,<%=depth%>,<%=srt%>, <%=upRuleNoRn%>, <%=cnt%>);" style="cursor:pointer">
							<td class="selectOn" id="rl_<%=beforeupRuleNo%>_<%=depth%>_<%=upRuleNoRn%>_<%=cnt%>" ><%=(String)result.get("rule_title") %></td>
						</tr>
		<%          }else if(cnt == upRuleNoRn){              %>
						<tr id="tr_<%=i %>" class="trCls" onClick="javascript:detailShow(<%=beforeupRuleNo%>,<%=depth%>,<%=srt%>, <%=upRuleNoRn%>, <%=cnt%>);" style="cursor:pointer">
							<td class="select end02" id="rl_<%=beforeupRuleNo%>_<%=depth%>_<%=upRuleNoRn%>_<%=cnt%>"><%=(String)result.get("rule_title") %></td>
						</tr>
		<%			}else{					 %>
						<tr id="tr_<%=i %>" class="trCls" onClick="javascript:detailShow(<%=beforeupRuleNo%>,<%=depth%>,<%=srt%>, <%=upRuleNoRn%>, <%=cnt%>);" style="cursor:pointer">
							<td class="select" id="rl_<%=beforeupRuleNo%>_<%=depth%>_<%=upRuleNoRn%>_<%=cnt%>"><%=(String)result.get("rule_title") %></td>
						</tr>
		<%			}						 %>						
						<tr id="rl_<%=beforeupRuleNo%>_<%=depth%>_<%=srt%>" class='cont' style="display:none;cursor:pointer">
							<td class="select_q2" id="td_<%=beforeupRuleNo%>_<%=depth%>_<%=srt%>">
<%-- 								<%if("ko".equals(command.getSession_user_locale())){ %> --%>
<%-- 									<span><%=StringUtil.convertCharsToHtml((String)result.get("rule_cont"))%></span> --%>
<%-- 								<%}else{ %> --%>
<%-- 									<span><%=StringUtil.convertCharsToHtml((String)result.get("rule_cont_en"))%></span> --%>
<%-- 								<%} %> --%>
								<span><%=StringUtil.convertEnterToBR((String)result.get("rule_cont_en"))%></span>
							</td>
						</tr>
		<%
				}
				beforeupRuleNo = upRuleNo;
				beforeDepth = depth;
				if(i == resultList.size() -1 && beforeDepth != 1){
		%>
					</table>
		<%				
					
				}
			}
				
		}
		%>
		</form:form>
		
		<div style='float:right;clear:both;margin:30px 0 10px 0;'><a href="#p1"><img src="<%=IMAGE%>/common/btn_top.gif"></a></div>
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
