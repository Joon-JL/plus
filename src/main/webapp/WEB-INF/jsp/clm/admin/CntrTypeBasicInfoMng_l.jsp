<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sec.clm.admin.dto.CntrTypeBasicInfoMngForm" %>
<%--
/**
 * 파  일  명 : CntrTypeBasicInfoMng_l.jsp
 * 프로그램명 : 계약 유형별 속성관리 - 리스트
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	
	CntrTypeBasicInfoMngForm command = (CntrTypeBasicInfoMngForm)request.getAttribute("command");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="clm.main.title" /></title>
<link href="<%=CSS %>/clm.css" type="text/css" rel="stylesheet" />
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript">

	$(document).ready(function(){
		//비즈니스 분류
		getCodeSelectByUpCd("frm", "srch_biz_clsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T01&combo_abbr=F&combo_type=T&combo_del_yn=N&combo_selected=<%=command.getSrch_biz_clsfcn()%>");
		//단계
		getCodeSelectByUpCd("frm", "srch_depth_clsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T02&combo_abbr=F&combo_type=T&combo_del_yn=N&combo_selected=<%=command.getSrch_depth_clsfcn()%>");
		//체결목적 대분류
		getCodeSelectByUpCd("frm", "srch_cnclsnpurps_bigclsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T03&combo_abbr=F&combo_type=T&combo_del_yn=N&combo_selected=<%=command.getSrch_cnclsnpurps_bigclsfcn()%>");



<%
	//검색 후 체결의 목적 대분류가 값이 있을 경우, 중분류도 셋팅한다.
	if (!"".equals(StringUtil.bvl(command.getSrch_cnclsnpurps_bigclsfcn(), ""))) { 
%>
		getCodeSelectByUpCd("frm", "srch_cnclsnpurps_midclsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=<%=command.getSrch_cnclsnpurps_bigclsfcn()%>&combo_abbr=F&combo_type=T&combo_del_yn=N&combo_selected=<%=command.getSrch_cnclsnpurps_midclsfcn()%>");
<%			
	}
%>

		initPage();
	});

	function changeSubCd(selObjId, gbn, upCd) {
    	if(upCd == "") {
        	upCd = "XXX";
    	}
    	getCodeSelectByUpCd('frm', selObjId, '/common/clmsCom.do?method=getComboHTML&combo_gbn=' + gbn + '&combo_up_cd=' + upCd + '&combo_abbr=F&combo_type=T&combo_del_yn=N');
	}
	
	/**
	* 메시지 처리
	*/
	function showMessage(msg) {
		if(msg != "" && msg != null && msg.length > 1) {
			alert(msg);
		}
	}
	
	/**
	* 검색
	*/
	function goSearch(){
		var frm = document.frm;
		viewHiddenProgress(true) ;
		
		frm.curPage.value = 1;
		frm.method.value = "listCntrTypeBasicInfoMng";
		frm.action = "<c:url value='/clm/admin/cntrTypeBasicInfoMng.do' />";
		frm.target = "_self";
		frm.iframe_yn.value = "N";
		frm.submit();
	}
	
	/**
	* 상세 이동
	*/
	function goDetail(biz_clsfcn, depth_clsfcn, cnclsnpurps_bigclsfcn, cnclsnpurps_midclsfcn){
		var frm = document.frm;
		viewHiddenProgress(true) ;
		
		frm.biz_clsfcn.value = biz_clsfcn;
		frm.depth_clsfcn.value = depth_clsfcn;
		frm.cnclsnpurps_bigclsfcn.value = cnclsnpurps_bigclsfcn;
		frm.cnclsnpurps_midclsfcn.value = cnclsnpurps_midclsfcn;
		
		frm.method.value = "detailCntrTypeBasicInfoMng";
		frm.action = "<c:url value='/clm/admin/cntrTypeBasicInfoMng.do' />";
		frm.target = "_self";
		frm.submit();
	}
	
	/**
	* 페이지 이동
	*/
	function goPage(page) {
		var frm = document.frm;
		viewHiddenProgress(true) ;

		frm.curPage.value = page;
		frm.method.value = "listCntrTypeBasicInfoMng";
		frm.action = "<c:url value='/clm/admin/cntrTypeBasicInfoMng.do' />";
		frm.target = "_self";
		frm.iframe_yn.value = "N";
		
		frm.submit();
	}
	
	/**
	 * iframe 초기화
	 */
	function initPage() {
		var frm = document.frm;

		frm.action = "<c:url value='/clm/admin/cntrTypeBasicInfoMng.do' />";
		frm.method.value = "listCntrTypeBasicInfoMng";
		frm.target = "cntrTypeBasicInfoMngIF";
		frm.iframe_yn.value = "Y";

		frm.submit();
	}


	
</script>
</head>
<body onload='showMessage("<c:out value='${returnMessage}'/>");'>

<form:form name="frm" id="frm" method="post" autocomplete="off">
<input type="hidden" name="method" 		value=""/>
<input type="hidden" name="menu_id" 	value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage" 	value="<c:out value='${command.curPage}'/>" />

<!-- iframe 여부 -->
<input type="hidden" name="iframe_yn"	value=""/>


<input type="hidden" name="biz_clsfcn" value=""/>
<input type="hidden" name="depth_clsfcn" value=""/>
<input type="hidden" name="cnclsnpurps_bigclsfcn" value=""/>
<input type="hidden" name="cnclsnpurps_midclsfcn" value=""/>


<div id="wrap">
	<!-- container -->
	<div id="container">

		<!-- location -->
		<div class="location"><IMG SRC="<%=IMAGE %>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->

		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.cntrTypeBasicInfoMng.listTitle" /></h3><!-- 계약유형별 속성관리 목록 -->
		</div>
		<!-- //title -->	
		<!-- content -->
		<div id="content">
			
				<!-- search-->
				<%--
				<div class="search_box">
					<div class="search_box_inner05">
						<ul>
							<li>
								<dl>
									<dt><spring:message code="clm.field.cntrTypeBasicInfoMng.bizStage" /></dt><!-- BIZ단계 -->
									<dd>
										<select style="width:140px;" name="srch_biz_clsfcn" id="srch_biz_clsfcn" >
										</select> 
									</dd>
									<dt><spring:message code="clm.field.cntrTypeBasicInfoMng.contractStage" /></dt><!-- 계약단계 -->
									<dd>
										<select style="width:140px" name="srch_depth_clsfcn" id="srch_depth_clsfcn" >
										</select>
									</dd>
									<dt><spring:message code="clm.field.cntrTypeBasicInfoMng.conclusionPurpose" /></dt><!-- 체결의목적 -->
									<dd>
										<select style="width:140px" name="srch_cnclsnpurps_bigclsfcn" id="srch_cnclsnpurps_bigclsfcn" onChange='javascript:changeSubCd("srch_cnclsnpurps_midclsfcn", "CONTRACTTYPE", this.value);'>
										</select>
										<select style="width:140px;" name="srch_cnclsnpurps_midclsfcn" id="srch_cnclsnpurps_midclsfcn">
											<option value=''>-- select --</option>
										</select>
									</dd>
								</dl>
							</li>
							
						</ul>
						<div class="btn_search_01 vb fR"><a href="javascript:goSearch();"><img src="<%=IMAGE %>/btn/btn_search.gif"/></a></div>
					</div>
				</div>
				 --%>
				 
				 <%--
				<div class="search_box">
					<div class="search_box_inner">
						<div class="searchin">
							<ul>
								<li>
									<dl>
										<dt style="width:10%;"><spring:message code="clm.field.cntrTypeBasicInfoMng.bizStage" /></dt><!-- BIZ단계 -->
										<dd style="width:35%;">
											<select name="srch_biz_clsfcn" id="srch_biz_clsfcn" >
											</select> 
										</dd>
										<dt style="width:10%;"><spring:message code="clm.field.cntrTypeBasicInfoMng.contractStage" /></dt><!-- 계약단계 -->
										<dd style="width:35%;">
											<select name="srch_depth_clsfcn" id="srch_depth_clsfcn" >
											</select>
										</dd>
									</dl>
								</li>
								<li>
									<dl>
										<dt style="width:10%;"><spring:message code="clm.field.cntrTypeBasicInfoMng.conclusionPurpose" /></dt><!-- 체결의목적 -->
										<dd style="width:80%;">
											<select style="width:180px;" name="srch_cnclsnpurps_bigclsfcn" id="srch_cnclsnpurps_bigclsfcn" onChange='javascript:changeSubCd("srch_cnclsnpurps_midclsfcn", "CONTRACTTYPE", this.value);'>
											</select>
											<select style="width:160px;" name="srch_cnclsnpurps_midclsfcn" id="srch_cnclsnpurps_midclsfcn">
												<option value=''>-- select --</option>
											</select>
										</dd>
									</dl>
								</li>
							</ul>
							<div class="btn_search_01"><a href="javascript:goSearch();"><img src="<%=IMAGE %>/btn/btn_search.gif"/></a></div>
						</div>
					</div>
				</div>
				 --%>
				

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
										<col width="15%"/>
										<col width="10%"/>
										<col width="15%"/>
										<col width="10%"/>
										<col width="39%"/>
									</colgroup>
									<tr>
										<th><spring:message code="clm.field.cntrTypeBasicInfoMng.bizStage" /></th><!-- BIZ단계 -->
										<td>
											<select name="srch_biz_clsfcn" id="srch_biz_clsfcn" class="all" style="width:125px">
								    		</select>
								    	</td>
										<th><spring:message code="clm.field.cntrTypeBasicInfoMng.contractStage" /></th><!-- 계약단계 -->
										<td>
											<select name="srch_depth_clsfcn" id="srch_depth_clsfcn" class="all" style="width:125px">
											</select>
										</td>
										<th><spring:message code="clm.field.cntrTypeBasicInfoMng.conclusionPurpose" /></th><!-- 체결의목적 -->
										<td>
											<select name="srch_cnclsnpurps_bigclsfcn" id="srch_cnclsnpurps_bigclsfcn" class="all" style="width:182px" onchange='javascript:changeSubCd("srch_cnclsnpurps_midclsfcn", "CONTRACTTYPE", this.value);'>
									    	</select>
									    	<select name="srch_cnclsnpurps_midclsfcn" id="srch_cnclsnpurps_midclsfcn" class="all" style="width:160px;" >
												<option value=''>-- select --</option>
											</select>
									    </td>
									</tr>
								</table>
							</td>
							<td class="tC"><a href="javascript:goSearch();"><img src="<%=IMAGE %>/btn/btn_search.gif" alt="<spring:message code="clm.page.msg.common.inquire" htmlEscape="true" />"/></a></td>
							</tr>
						</table>
					</div>
				</div>
				
				
				
				
				
				
				<!-- //search-->
				
				<!-- button -->
				<div class="btn_wrap fR">
				
				</div>
				<!-- //button -->	
				
				<!-- list -->
				<!-- 테이블 데이터 -->
				<div class="tal_scroL">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="20" valign="top">
								<!-- 테이블 데이터 번호/성명/사번 시작 -->
								<table class="list_basic tal_lineR" style="width:460px; min-width:460px;">
									<colgroup>
										<col width="40" />
										<col width="80" />
										<col width="80" />
										<col width="130" />
										<col width="130" />
									</colgroup>
									<thead>
										<tr>
											<th rowspan="2">No</th>
											<th rowspan="2"><spring:message code="clm.field.cntrTypeBasicInfoMng.bizStage" /></th><!-- BIZ단계 -->
											<th rowspan="2"><spring:message code="clm.field.cntrTypeBasicInfoMng.contractStage" /></th><!-- 계약단계 -->
											<th colspan="2"><spring:message code="clm.field.cntrTypeBasicInfoMng.bizStage" /></th><!-- 체결의목적 -->
										</tr>
										<tr>
											<th class="tal_lineL tal_bg_cor01"><spring:message code="clm.field.cntrTypeBasicInfoMng.bigCate" /></th><!-- 대분류 -->
											<th class="tal_bg_cor01"><spring:message code="clm.field.cntrTypeBasicInfoMng.midCate" /></th><!-- 중분류 -->
										</tr>
									</thead>
									<tbody>
								<c:choose>
									<c:when test="${pageUtil.totalRow > 0}">
									<c:forEach var="list" items="${lom}">
										<tr>
											<td>
												<div style="width:40px;display:block;white-space:nowrap;overflow:hidden; text-overflow:ellipsis; -o-text-overflow:ellipsis; -ms-text-overflow:ellipsis; ">
													<nobr><c:out value='${list.rn}'/></nobr>
												</div>
											</td>
											<td title="<c:out value='${list.biz_clsfcn_nm}'/>">
												<a href="javascript:goDetail('<c:out value='${list.biz_clsfcn}'/>','<c:out value='${list.depth_clsfcn}'/>','<c:out value='${list.cnclsnpurps_bigclsfcn}'/>','<c:out value='${list.cnclsnpurps_midclsfcn}'/>');">
													<div style="width:80px;display:block;white-space:nowrap;overflow:hidden; text-overflow:ellipsis; -o-text-overflow:ellipsis; -ms-text-overflow:ellipsis; ">
														<nobr><c:out value='${list.biz_clsfcn_nm}'/></nobr>
													</div>
												</a>
											</td>
											<td title="<c:out value='${list.depth_clsfcn_nm}'/>">
												<a href="javascript:goDetail('<c:out value='${list.biz_clsfcn}'/>','<c:out value='${list.depth_clsfcn}'/>','<c:out value='${list.cnclsnpurps_bigclsfcn}'/>','<c:out value='${list.cnclsnpurps_midclsfcn}'/>');">
													<div style="width:80px;display:block;white-space:nowrap;overflow:hidden; text-overflow:ellipsis; -o-text-overflow:ellipsis; -ms-text-overflow:ellipsis; ">
														<nobr><c:out value='${list.depth_clsfcn_nm}'/></nobr>
													</div>
												</a>
											</td>
											<td class="tL" title="<c:out value='${list.cnclsnpurps_bigclsfcn_nm}'/>">
												<a href="javascript:goDetail('<c:out value='${list.biz_clsfcn}'/>','<c:out value='${list.depth_clsfcn}'/>','<c:out value='${list.cnclsnpurps_bigclsfcn}'/>','<c:out value='${list.cnclsnpurps_midclsfcn}'/>');">
													<div style="width:130px;display:block;white-space:nowrap;overflow:hidden; text-overflow:ellipsis; -o-text-overflow:ellipsis; -ms-text-overflow:ellipsis; ">
														<nobr><c:out value='${list.cnclsnpurps_bigclsfcn_nm}'/></nobr>
													</div>
												</a>
											</td>
											<td class="tL" title="<c:out value='${list.cnclsnpurps_midclsfcn_nm}'/>">
												<a href="javascript:goDetail('<c:out value='${list.biz_clsfcn}'/>','<c:out value='${list.depth_clsfcn}'/>','<c:out value='${list.cnclsnpurps_bigclsfcn}'/>','<c:out value='${list.cnclsnpurps_midclsfcn}'/>');">
													<div style="width:130px;display:block;white-space:nowrap;overflow:hidden; text-overflow:ellipsis; -o-text-overflow:ellipsis; -ms-text-overflow:ellipsis; ">
														<nobr><c:out value='${list.cnclsnpurps_midclsfcn_nm}'/></nobr>
													</div>
												</a>
											</td>
										</tr>	
										<input type="hidden" name="biz_clsfcn_arr" value="<c:out value='${list.biz_clsfcn}'/>"/>
										<input type="hidden" name="depth_clsfcn_arr" value="<c:out value='${list.depth_clsfcn}'/>"/>
										<input type="hidden" name="cnclsnpurps_bigclsfcn_arr" value="<c:out value='${list.cnclsnpurps_bigclsfcn}'/>"/>
										<input type="hidden" name="cnclsnpurps_midclsfcn_arr" value="<c:out value='${list.cnclsnpurps_midclsfcn}'/>"/>								
									</c:forEach>
				    				</c:when>
				    			</c:choose>	
									</tbody>
								</table>
								<!-- 테이블 데이터 번호/성명/사번 끝 -->
							</td>
							<td align="left" valign="top" >
							<!-- 테이블 데이터 시작 -->
							<DIV id="bottomLine" class="list_basic04_if" style="width:100%;height:283px;overflow-x:hidden; overflow-y:hidden;" onscroll="scrollX()">
								<iframe src="<c:url value='/clm/blank.do' />" name="cntrTypeBasicInfoMngIF" width="100%" height="100%"   frameborder="no"></iframe>
							</DIV>
							<!-- 테이블 데이터 끝 -->
							</td>
						</tr>
					</table>
				</div>
				<!-- //테이블 데이터 끝 -->				
				
				
				
				<!-- //list -->
		 		<div class="total_num">Total: <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%></div>
		 		<!-- pagination -->
				<div class="pagination">
					<%=pageUtil.getPageNavi(pageUtil, "") %>
				</div>
				<!-- //pagination -->
			
		</div>
		<!-- //content -->
					
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js"></script>
		<!-- // footer -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>		
</form:form>
</body>
</html>
