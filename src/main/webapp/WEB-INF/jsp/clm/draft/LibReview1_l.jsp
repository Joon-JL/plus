<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.draft.dto.LibReviewForm" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file= "/WEB-INF/jsp/common/common.jsp" %>

<%-- 
/**
 * 파  일  명 : LibReview1_l.jsp
 * 프로그램명 : 계약서 Library 유형별조회 목록
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%
	ArrayList resultList = (ArrayList)request.getAttribute("resultList");

	String menuNavi     = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil   = (PageUtil)request.getAttribute("pageUtil");
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<!-- style -->
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet" />
<link href="<%=CSS%>/tooltip.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/excanvas.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/tooltip.js' />"></script>


<script language="javascript">
	$(document).ready(function(){
		
		$('li > label').hover(function(){
			//툴팁
			init_tooltip();
			
			$(this).attr("style", "color: #fff;");  
		}, function(){
			$(this).removeAttr("style", "");
		});
		
		$('li > dl > dt').hover(function(){
			$(this).attr("style", "color: #fff;");  
		}, function(){
			$(this).removeAttr("style", "");
		});	
	});
  	
	/**
	* 상세내역 조회
	*/
	function detaiListView(contract_type_id, type_cd){
		
		var frm = document.frm;
	    //초기화 : 연계 시 이 부분을 주석처리...
		$('input[name^=srch_]').val("");
	    //설정
	    $('#srch_'+ contract_type_id.toLowerCase()).val(type_cd)

		frm.target = "_self";
		frm.action = "<c:url value='/clm/draft/libReview.do' />";
		frm.method.value = "listLibReview";
		//comLayerPopCenter('ProgressLayer1');
		
		if( contract_type_id=="null"){
			//모달
			init_modal();
			//alert("해당 자료가 없습니다.");
			return;			
		}
		
		frm.submit();		
	}
	
</script>
</head>
<body>
<!-- header -->

<!-- //header -->	
<!-- 

**************************** Form Setting **************************** 
-->
<form:form name="frm" id='frm' method="post" autocomplete="off"> 
	<!-- search form -->
	<input type="hidden" name="method"   value="" />
	<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
	<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>" />
	<input type="hidden" name="srch_biz_clsfcn"            id="srch_biz_clsfcn"             value="<c:out value='${command.srch_biz_clsfcn}'/>" />
	<input type="hidden" name="srch_depth_clsfcn"          id="srch_depth_clsfcn"           value="<c:out value='${command.srch_depth_clsfcn}'/>" />
	<input type="hidden" name="srch_cnclsnpurps_bigclsfcn" id="srch_cnclsnpurps_bigclsfcn"  value="<c:out value='${command.srch_cnclsnpurps_bigclsfcn}'/>" />
	<input type="hidden" name="srch_cnclsnpurps_midclsfcn" id="srch_cnclsnpurps_midclsfcn"  value="<c:out value='${command.srch_cnclsnpurps_midclsfcn}'/>" />
	<!-- key Form -->
	<input type="hidden" name="lib_no"     id="lib_no"     value="<c:out value='${command.lib_no}'/>" />
	<input type="hidden" name="lib_gbn"    id="lib_gbn"    value="<c:out value='${command.lib_gbn}'/>" />
	<input type="hidden" name="region_gbn" id="region_gbn" value="<c:out value='${command.region_gbn}'/>" />

<!-- //
**************************** Form Setting **************************** 
-->
<div id="wrap">
	<!-- container -->
	<div id="container">
		<!-- location -->
		<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //Location -->
	 
		<!-- content -->
		<div id="content">
			<!-- title -->
			<div class="title">		
				<h3><spring:message code="clm.page.msg.draft.contLib" /></h3>
			</div>
			<!-- //title -->
				
		    <div id="content">
				<!-- choo_wrap -->
				<div class="choo_wrap2">
					<!-- //left_list -->
					<div class="choo_Lbox2">
						<ul>
		<%
			for(int idx=0;idx < resultList.size();idx++){
		
				ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
				// 타이틀
				if( "TOP".equals((String)lom.get("up_type_cd")) && ( idx < resultList.size() )){			
		%>
							<li>
								<dl class="chooBox02-2">
									<dt style="font-size:12px;"><label for="" onClick='javascript:detaiListView("<%=lom.get("contract_type_id") %>","<%=lom.get("type_cd") %>");'>
									<span><%=lom.get("cd_nm_abbr") %>  ( <%=lom.get("contract_type_cnt") %> )</span></label></dt>
									<dd>
										<ul>
		<%
				continue;	// 유형의 타이틀만을 조회 하기 위해 추가
				}
				// 세부내용
				if( ((Integer)lom.get("level")).intValue() < 3 ){	//체결의 목적 3 레벨은 제외하기 위해 조건 추가
		%>
											<li class="tooltip" title="<%=lom.get("cd_nm") %>( <%=lom.get("contract_type_cnt") %> )">
												<label class="_modal" for="" onClick='javascript:detaiListView("<%=lom.get("contract_type_id") %>","<%=lom.get("type_cd") %>");'><%=lom.get("cd_nm") %>
												<span>( <%=lom.get("contract_type_cnt") %> )</span></label>
											</li>
		<%
				}
				// 앞 노드의 값이 TOP 일 경우 유형이 달라지므로 실행...
				if(("TOP".equals((String)lom.get("lead_up_type_cd"))) || ((String)lom.get("lead_up_type_cd"))=="" || ((String)lom.get("lead_up_type_cd"))==null) {
		%>
		
										</ul>
									</dd>
								</dl>
							</li>			
		<%			
				}
			}
		%>
						</ul>
					</div>
					<!-- //left_list -->
					
					<!-- right_list -->
					<div class="choo_Rbox2 fL" >
						<ul>
		<%
			for(int idx=0;idx < resultList.size();idx++){
			
				ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
				// 타이틀
				if( "T03".equals((String)lom.get("up_type_cd")) && ( idx < resultList.size() )){			
		%>
							<li>
								<dl class="chooBox2">
									<dt><label for="" onClick='javascript:detaiListView("<%=lom.get("contract_type_id") %>","<%=lom.get("type_cd") %>");'>
									<span ><nobr style="overflow:hidden;"><%=lom.get("cd_nm") %> (<%=lom.get("contract_type_cnt") %>)</nobr></span></label></dt>
									<dd>
										<ul>
		<%
				continue;	// 유형의 타이틀만을 조회 하기 위해 추가
				}
				// 세부내용
				if( ((Integer)lom.get("level")).intValue() >= 2 && "T03".equals(((String)lom.get("up_type_cd")).substring(0, 3)) ){	//체결의 목적 3 레벨은 제외하기 위해 조건 추가
		%>
											<li class="tooltip" title="<%=lom.get("cd_nm") %>( <%=lom.get("contract_type_cnt") %> )">
												<label class="_modal" for="" onClick='javascript:detaiListView("<%=lom.get("contract_type_id") %>","<%=lom.get("type_cd") %>");'><%=lom.get("cd_nm") %>
												<span>(<%=lom.get("contract_type_cnt") %>)</span></label>
											</li>
		<%
				}
				// 앞 노드의 값이 TOP 일 경우 유형이 달라지므로 실행...
				if( (("T03".equals((String)lom.get("lead_up_type_cd"))) && !("TOP".equals((String)lom.get("up_type_cd"))))  || ((String)lom.get("lead_up_type_cd"))=="" || ((String)lom.get("lead_up_type_cd"))==null ) {
		%>
		
										</ul>
									</dd>
								</dl>
							</li>
		<%			
				}
			}
		%>
						</ul>
					</div>
					<!-- //right_list -->
				</div>		
				<!-- //choowrap -->
			</div>
			<!-- footer  -->
			<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
			<!-- // footer -->
		</div>
		<!--// content -->
	</div>
</div>
</form:form>
</body>
</html>

