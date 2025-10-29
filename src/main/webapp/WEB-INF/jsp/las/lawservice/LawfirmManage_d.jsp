<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>
<%@ page import="com.sds.secframework.common.util.Token"%>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파  일  명 : LawFirmManage_d.jsp
 * 프로그램명 : 로펌관리 - 상세
 * 설      명 : 
 * 작  성  자 : 박병주
 * 작  성  일 : 2011.09
 */
 --%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript" src="<c:url value='/script/clms/menu.js'/>"></script>

<script language="javascript">
	/**
	* 초기화면 로딩시
	*/
	$(document).ready(function(){
	
		var frm = document.frm;
		
		//첨부파일창 load
		initPage();
	});
	
	/**
	* 첨부파일창 load
	*/
	function initPage(){

	    frm.target          = "fileList";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.submit();   
	}
	
	/**
	* 목록으로 가기
	*/
	function goList() {
		var frm = document.frm;
		viewHiddenProgress(true);
		frm.method.value = "listLawfirmManage";
		frm.action = "<c:url value='/las/lawservice/lawfirmManage.do' />";
		frm.target = "_self";
		frm.submit();
	}
	
	/**
	* 수정처리
	*/
	function modify() {
		var frm = document.frm;
		viewHiddenProgress(true);
		frm.method.value = "forwardLawfirmManageModify";		
		frm.action = "<c:url value='/las/lawservice/lawfirmManage.do' />";
		frm.target = "_self";
		frm.submit();
	}
	
	/**
	* 삭제처리
	*/
	function remove(){
		var frm = document.frm;
		
		if(confirm("<spring:message code='secfw.msg.ask.delete' />")) {
			
			viewHiddenProgress(true);
			frm.method.value = "deleteLawfirmManage";
			frm.action = "<c:url value='/las/lawservice/lawfirmManage.do' />";
			frm.target = "_self";
			frm.submit();
		}
	}
	
	function showMessage(msg){
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
		}
	}
	
	/**
	* 평가추가  POPUP
	*/
	function add_estimate()
	{
    	var frm = document.frm;

		PopUpWindowOpen('', "760", "280", false);
		frm.action = "<c:url value='/las/lawservice/lawfirmManage.do' />";
		frm.method.value="forwardLawfirmEstimateInsert";
		frm.target = "PopUpWindow";
		frm.submit();
	}
	
	/**
	* 평가삭제 
	*/
	function del_estimate(estmt_no)
	{
		var frm = document.frm;
		if(confirm("<spring:message code='secfw.msg.ask.delete' />")){
			viewHiddenProgress(true);
			frm.estmt_no.value = estmt_no;
			frm.target = "_self";
			frm.action = "<c:url value='/las/lawservice/lawfirmManage.do' />";
			frm.method.value="deleteLawfirmEstimate";

			frm.submit();
		}
	}

//-->
</script>
</head>
<body onLoad='showMessage("<c:out value='${returnMessage}'/>");'>
<form:form name="frm" id="frm" autocomplete="off">
<input type="hidden" id="method" name="method" value="" />
<input type="hidden" id="menu_id" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" id="curPage" name="curPage" value="<c:out value='${command.curPage}'/>" />
<input type="hidden" id="forward_from" name="forward_from"  value="<c:out value='${command.forward_from}'/>" />

<!-- URL 이동시 사용 -->
<input type="hidden" name="targetMenuId">
<input type="hidden" name="selected_menu_id">
<input type="hidden" name="selected_menu_detail_id">
<input type="hidden" name="set_init_url">

<!-- search form -->
<input type="hidden" name="srch_event_no"   value="<c:out value='${command.srch_event_no}'/>" />
<input type="hidden" name="srch_event_nm"   value="<c:out value='${command.srch_event_nm}'/>" />
<input type="hidden" name="srch_lawfirm_nm"   value="<c:out value='${command.srch_lawfirm_nm}'/>" />
<input type="hidden" name="srch_lawfirm_id"   value="<c:out value='${command.srch_lawfirm_id}'/>" />
<input type="hidden" name="srch_mainftre_estmt"   value="<c:out value='${command.srch_mainftre_estmt}'/>" />
<input type="hidden" name="srch_kbn1"   value="<c:out value='${command.srch_kbn1}'/>" />
<input type="hidden" name="srch_kbn2"   value="<c:out value='${command.srch_kbn2}'/>" />
<input type="hidden" name="srch_nation"   value="<c:out value='${command.srch_nation}'/>" />
<input type="hidden" name="srch_nation_nm"   value="<c:out value='${command.srch_nation_nm}'/>" />

<!-- key form-->
<input type="hidden" name="lawfirm_id"   value="<c:out value='${command.lawfirm_id}'/>" />
<input type="hidden" name="estmt_no"   value="<c:out value='${command.estmt_no}'/>" />
<input type="hidden" name="lwr_no"   value="" />

<!-- 첨부파일정보 -->
<input type="hidden" name="fileInfos" 		value="" />
<input type="hidden" name="file_bigclsfcn" 	value="F005" />
<input type="hidden" name="file_midclsfcn" 	value="F00505" />
<input type="hidden" name="file_smlclsfcn" 	value="" />
<input type="hidden" name="ref_key" 		value="<c:out value='${command.lawfirm_id}'/>" />
<input type="hidden" name="view_gbn" 		value="download" />

<!-- 이중등록 방지 -->
<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>" />

<div id="wrap">   
	<div id="container">

	<div id="content">
	            <!-- Location -->
            <div class="location">
                <img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/>
            </div>
            <!-- //Location -->
		<div class="t_titBtn">
			<!-- title -->
			<div class="title">
				<h3><spring:message code="las.page.field.lawservice.lawfirmmng"/>&nbsp;<spring:message code="las.page.field.lawservice.detail"/>
				</h3>
			</div>
			<!-- //title -->	
			<div class="btn_wrap">
					<c:if test="${command.lws_auth_modify}">
						<span class="btn_all_w"><span class="edit"></span><a href="javascript:modify()"><spring:message code="las.page.button.modify" /></a></span>
					</c:if>
					<c:if test="${command.lws_auth_delete}">
						<span class="btn_all_w"><span class="delete"></span><a href="javascript:remove()"><spring:message code="las.page.button.delete" /></a></span>
					</c:if>
			</div>
		</div>

		<!-- view -->
		<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
			<colgroup>
				<col class="tal_w04" />
				<col />
				<col class="tal_w04" />
				<col />
			</colgroup>
			<tbody>
				<tr>
					<th>Law Firm</th>
					<td colspan="3">
						<c:out value='${lom.lawfirm_nm}'/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.fstcont"/></th>
					<td><c:out value='${lom.fst_cntrtday}'/></td>
					<th><spring:message code="las.page.field.lawservice.fstcontevent"/></th>
					<td><c:out value='${lom.fst_event_nm}'/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.contact"/></th>
					<td><c:out value='${lom.rprsnt_tel}'/></td>
					<th><spring:message code="las.page.field.lawservice.fax"/></th>
					<td><c:out value='${lom.rprsnt_fax}'/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.email"/></th>
					<td colspan="3"><c:out value='${lom.rprsnt_email}'/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.addr"/></th>
					<td colspan="3"><c:out value='${lom.addr}'/></td>
				</tr>
				<tr>
					<th>State</th>
					<td><c:out value='${lom.state_gbn}'/></td>
					<th><spring:message code="las.page.field.lawservice.nation"/></th>
					<td><c:out value='${lom.nation_nm}'/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.lwr"/></th>
					<td colspan="3">
						<c:forEach var="list" items="${lwr_list}"  varStatus="status">
							<a href='javascript:Menu.detail("frm", "_top", "20110804083733310_0000000168", "20110804083733310_0000000168", "/las/lawservice/lawyerManage.do?method=detailLawyerManage&lwr_no=<c:out value='${list.lwr_no}'/>")' >
							<c:out value='${list.lwr_nm}' /></a>
							<c:if test="${status.count > 0}">
								&nbsp;&nbsp;&nbsp;
							</c:if>
							<c:if test="${status.count mod 5==0}">
								</br>
							</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.home"/></th>
					<td colspan="3"><c:out value='${lom.homepage}'/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.maniftree"/>
					<a href="javascript:add_estimate()"><IMG SRC="<%=IMAGE %>/btn/b_estimation.gif" BORDER="0" ALT="add_estimate" ></a></th>
					<td colspan="3"><textarea name="textarea" id="textarea" cols="as" rows="7" class="text_area_full"><c:out value='${lom.mainftre_estmt}'/></textarea>
				</br> 
						<ul class="list_assess">							
							<c:forEach var="list" items="${el}">							
							<li> 
							&nbsp;
							</li>
							<li> 
							<input style="width:200px;" type="text" name="estmt_name" id="estmt_name" value="<c:out value='${list.estmt_name}'/>" readonly class="text"/> 
							<span style='margin-right 1px;align:right;vertical-align:3px' > <spring:message code="las.page.field.lawservice.estDate"/>: <c:out value='${list.reg_dt}'/></span>
							&nbsp;&nbsp;&nbsp;<span class="btn_option" style='margin-right 1px;align:right;vertical-align:3px' ><span class="delete"></span><a href="#" onclick='javascript:del_estimate("<c:out value='${list.estmt_no}'/>");'><spring:message code="las.page.button.lawservice.del"/></a></span>
							</li>
							<li>
							<textarea name="estmt_cont" id="estmt_cont" cols="10" rows="7" class="text_area_full" readonly ><c:out value='${list.estmt_cont}'/></textarea>
							</li>
							<li> 
							<spring:message code="las.page.field.lawservice.attach"/>
							: <a href="<c:out value='${list.file_path_temp}'/>" ><c:out value='${list.org_file_nm}'/></a> (<c:out value='${list.file_sz}'/> kb)
							</li>
							</c:forEach>
						</ul>
				</td>
				</tr>				
				<tr>
					<th><spring:message code="las.page.field.lawservice.banknm"/></th>
					<td><c:out value='${lom.bank_nm}'/></td>
					<th><spring:message code="las.page.field.lawservice.account"/></th>
					<td><c:out value='${lom.accnt_no}'/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.bankaddr"/></th>
					<td colspan="3"><c:out value='${lom.bank_addr}'/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.banketc"/></th>
					<td colspan="3"><textarea name="textarea" id="textarea" cols="as" rows="3" class="text_area_full"><c:out value='${lom.bank_note}'/></textarea></td>
				</tr>
				<tr class="end">
					<th><spring:message code="las.page.field.lawservice.attach"/></th>
					<td colspan="3">
						<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="80px" leftmargin="0" topmargin="0" scrolling="auto"  ></iframe>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- //view -->
		<!-- list -->
		<table border="0" cellspacing="0" cellpadding="0" class="list_basic  mt20">
		  <colgroup>
				  <col width="7%" />
				  <col width="33%" />
				  <col width="30%" />
				  <col width="15%" />
				  <col width="15%" />
		  </colgroup>
		  <thead>
			<tr>
			  <th><spring:message code="las.page.field.lawservice.order"/></th>
			  <th><spring:message code="las.page.field.lawservice.eventnm"/></th>
			  <th><spring:message code="las.page.field.lawservice.lawsuittrgt"/></th>
			  <th><spring:message code="las.page.field.lawservice.acptnm"/></th>
			  <th><spring:message code="las.page.field.lawservice.acptday"/></th>
			</tr>
		  </thead>
		  <tbody>
			<c:choose>
			<c:when test="${command.event_list_cnt > 0}">
				<c:forEach var="list" items="${al}">
					<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
					    <td  class="tC"><c:out value='${list.rn}'/></td>
						<td  class="tL">						
							<a href='javascript:Menu.detail("frm", "_top", "20110804083733433_0000000169", "20110804083733433_0000000169", "/las/lawservice/eventManage.do?forward_from=&method=detailEventManage&event_no=<c:out value='${list.event_no}'/>")' >
								<strong><c:out value='${list.event_nm}'/></strong>
							</a>
		            	</div>
		            </td>
		            <td><c:out value='${list.lawsuit_trgt_nm}'/></td>
		            <td><c:out value='${list.reg_nm}'/></td>
		            <td><c:out value='${list.acptday}'/></td>
				</tr>
				</c:forEach>
		    </c:when>
		    <c:otherwise>
				<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
					<td colspan="5" align="center"><spring:message code="las.msg.succ.noResult" /></td>
				</tr>
		    </c:otherwise>
		</c:choose>
		  </tbody>
		  </table>
		<!-- //list -->
		<div class="t_titBtn">
			<div class="btn_wrap">
				<span class="btn_all_w"><span class="list"></span><a href="javascript:goList()"><spring:message code="las.page.button.lawservice.golist"/></a></span>
			</div>
		</div>
	</div>
	<!-- //content -->
	<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</div>
<!-- //container -->
</div>
<!-- //wrap -->
</form:form>
</body>
</html>