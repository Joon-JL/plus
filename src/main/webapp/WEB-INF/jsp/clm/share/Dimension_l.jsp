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
 * 파  일  명 : Dimension_l.jsp
 * 프로그램명 : 계약 유형 정의
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2011.10.24
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
    DimensionForm command = (DimensionForm)request.getAttribute("command");
	List resultList = (List)request.getAttribute("resultList");
	
	String srch_type_cd = command.getSrch_type_cd();//검색시넘어오는 type_cd
	String type_cd      = null;//유형코드
	String up_type_cd   = null;//상위 유형 코드
	int rule_depth   = 0;//레벨(lvl)
	int rule_srt     = 0;//규정순서(rule_no)
	int rule_cnt     = 0;//규정번호의전체카운트 cnt
	int rn           = 0;//규정번호의 rownumber up_ytpe_cd_rn
	
	//검색 시 넘어오는 규정번호에 해당하는 depth onLoad 시 펼치기 위해.
	if(resultList != null && resultList.size() > 0){
		for(int i = 0 ; i < resultList.size() ; i++){
			ListOrderedMap result = (ListOrderedMap)resultList.get(i);
			type_cd = (String)result.get("type_cd");
			up_type_cd = (String)result.get("up_type_cd");
			if(srch_type_cd == type_cd){
				up_type_cd = (String)result.get("up_type_cd");
				rule_depth = ((Integer)result.get("lvl")).intValue();
				rule_srt   = ((Integer)result.get("rule_no")).intValue();
				rn         = ((Long)result.get("up_type_cd_rn")).intValue();
				rule_cnt   = ((Integer)result.get("cnt")).intValue();
			}
		}
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
		
		//alert("1 = "+top+" "+ depth+" "+ srt+" "+ rn+" "+ cnt);
		
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
		
		
		if(<%=srch_type_cd%> != ""){
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
 
<body onLoad="detailShow('<%=up_type_cd%>', <%=rule_depth%>, <%=rule_srt%>, <%=rn%>, <%=rule_cnt%>);">

<div id="wrap">
	<!-- container -->
	<div id="container">
	    <!-- Location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
		<!-- Title -->
		<div class="title">
		<h3><spring:message code="clm.page.title.dimension.infoword" /></h3>
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
		<div id="content_in">
		<!-- **************************** Form Setting **************************** -->
			<form:form name="frm" id='frm' method="post" autocomplete="off"> 
			<!-- search form -->
			<input type="hidden" name="method"       value="" />
			<input type="hidden" name="menu_id"      value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="srch_type_cd" value="<c:out value='${command.srch_type_cd}'/>" />
			<!-- //
			**************************** Form Setting **************************** 
			-->
		
		<div class="btn_wrap fR">
		    <td><span class="btn_all_w"><a href="javascript:showCont();"><spring:message code='clm.page.button.selectAll' /></a></span></td>
		    <td><span class="btn_all_w"><a href="javascript:hideCont();"><spring:message code='clm.page.button.selectCancel' /></a></span></td>
		</div>
		<%if(resultList != null && resultList.size() > 0){ 
			int beforeDepth = 0;
			String beforeupRuleNo = null;
			
				for(int i=0; i < resultList.size(); i++){
				ListOrderedMap result = (ListOrderedMap)resultList.get(i);
				String upRuleNo = (String)result.get("up_type_cd");
				int	depth = ((Integer)result.get("lvl")).intValue();
				int	srt = ((Integer)result.get("prnt_srt")).intValue();
				String dnYn = (String)result.get("tree_isleaf");
				int cnt = ((Integer)result.get("cnt")).intValue();
				int upRuleNoRn =  ((Long)result.get("up_type_cd_rn")).intValue();
				//int treeLvl =  ((Long)result.get("tree_level")).intValue();
				
				if(depth ==1){
					if(beforeDepth > 0){
		%>
					</table>
		<%				
					}
					if(upRuleNo == "TOP"){    // 최초 상위 제목 레벨 'TOP'
		%>
					<!-- title -->
					<div class="title_basic02">
						<h4><%=(String)result.get("cd_nm") %></h4>
					</div>
					<!-- //title -->
					
		<%	
					}else{
		%>
					<!-- title -->
					<div class="title_basic02 mt15">
						<h4><%=(String)result.get("cd_nm") %></h4>
					</div>
					<!-- //title -->
		<%
					}
				}else{
					if(beforeDepth == 1){
		%>
						<table cellspacing="0" cellpadding="0" border="0" class="table-style01">
		<%
					}
					if(depth == 2 ){
		%>
						<tr id="tr_<%=i %>" class="trCls" onClick="javascript:detailShow('<%=beforeupRuleNo%>',<%=depth%>,<%=srt%>, <%=upRuleNoRn%>, <%=cnt%>);" style="cursor:hand">
							<td class="selectOn" id="rl_<%=beforeupRuleNo%>_<%=depth%>_<%=upRuleNoRn%>_<%=cnt%>" ><%=(String)result.get("cd_nm") %>1</td>
						</tr>
	    <%    	   
	                 }else if(depth == 3){	    	
	    %>
						<tr id="tr_<%=i %>" class="trCls" onClick="javascript:detailShow('<%=beforeupRuleNo%>',<%=depth%>,<%=srt%>, <%=upRuleNoRn%>, <%=cnt%>);" style="cursor:hand">
							<td class="select end02" id="rl_<%=beforeupRuleNo%>_<%=depth%>_<%=upRuleNoRn%>_<%=cnt%>"><%=(String)result.get("cd_nm") %>2</td>
						</tr>
		<%          
					}else if(cnt == upRuleNoRn){             
        %>
						<tr id="tr_<%=i %>" class="trCls" onClick="javascript:detailShow('<%=beforeupRuleNo%>',<%=depth%>,<%=srt%>, <%=upRuleNoRn%>, <%=cnt%>);" style="cursor:hand">
							<td class="select end02" id="rl_<%=beforeupRuleNo%>_<%=depth%>_<%=upRuleNoRn%>_<%=cnt%>"><%=(String)result.get("cd_nm") %>3</td>
						</tr>
		<%			}else{	
		
	    %>
						<tr id="tr_<%=i %>" class="trCls" onClick="javascript:detailShow('<%=beforeupRuleNo%>',<%=depth%>,<%=srt%>, <%=upRuleNoRn%>, <%=cnt%>);" style="cursor:hand">
							<td class="select" id="rl_<%=beforeupRuleNo%>_<%=depth%>_<%=upRuleNoRn%>_<%=cnt%>"><%=(String)result.get("cd_nm") %>4</td>
						</tr>
		<%			}						 %>						
						<tr id="rl_<%=beforeupRuleNo%>_<%=depth%>_<%=srt%>" class='cont' style="display:none;cursor:hand">
							<td class="select_q" id="td_<%=beforeupRuleNo%>_<%=depth%>_<%=srt%>">
								<span><%=StringUtil.convertCharsToHtml((String)result.get("cd_expl"))%></span>
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