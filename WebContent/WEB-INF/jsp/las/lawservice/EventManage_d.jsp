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
 * 파  일  명 : EventManage_d.jsp
 * 프로그램명 : 사건관리 - 상세
 * 설      명 : 
 * 작  성  자 : 박병주
 * 작  성  일 : 2011.09
 */
--%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
<!--
	/**
	* 초기화면 로딩
	*/
	$(document).ready(function(){
		
		var frm = document.frm;
		
		// 사건구분1
		getCodeSelectByUpCd2("frm", "event_gbn1", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"106"+"&combo_abbr=F&combo_type=T&combo_use_yn=Y&combo_selected="+"<c:out value='${lom.event_gbn1}'/>");
		
		// 사건구분2
		getCodeSelectByUpCd2("frm", "event_gbn2", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"<c:out value='${lom.event_gbn1}'/>"+"&combo_abbr=F&combo_type=T&combo_use_yn=Y&combo_selected="+"<c:out value='${lom.event_gbn2}'/>");
		
		// 소송상대 리스트
		getCodeSelectByUpCd2("frm", "lawsuit_trgt", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"105"+"&combo_abbr=F&combo_type=T&combo_use_yn=Y&combo_selected="+"<c:out value='${lom.lawsuit_trgt}'/>");	
		
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
		
		<c:choose>
			<c:when test="${command.forward_from ==  '1'}">
				frm.action = "<c:url value='/las/lawservice/eventAcceptSrvCost.do' />";
				frm.method.value = "listEventSub";
			</c:when>
			<c:otherwise>
				frm.action = "<c:url value='/las/lawservice/eventManage.do' />";
				frm.method.value = "listEventManage";
			</c:otherwise>
		</c:choose>
		
		viewHiddenProgress(true);
		frm.target = "_self";
		frm.submit();
	}
	
	/**
	* SRV 입력
	*/
	function goSrvInsert(){
		
		var frm = document.frm;
		
		viewHiddenProgress(true);
		frm.method.value = "forwardEventAcceptSrvCostInsert";
		frm.action = "<c:url value='/las/lawservice/eventAcceptSrvCost.do' />";
		frm.target = "_self";
		frm.submit();

	}
	
	/**
	* 수정처리
	*/
	function modify() {
		var frm = document.frm;
		viewHiddenProgress(true);
		frm.method.value = "forwardEventManageModify";		
		frm.action = "<c:url value='/las/lawservice/eventManage.do' />";
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
			frm.method.value = "deleteEventManage";
			frm.action = "<c:url value='/las/lawservice/eventManage.do' />";
			frm.target = "_self";
			frm.submit();
		}
	}
	
	/**
	* INVOICE 상세내역 조회
	*/
	function detailInvoiceView(acpt_no){
		var frm = document.frm;
		
		viewHiddenProgress(true) ;
		frm.acpt_no.value		= acpt_no;
		frm.target = "_self";
		frm.action = "<c:url value='/las/lawservice/eventAcceptSrvCost.do' />";
		frm.method.value = "detailEventAcceptSrvCost";
		
		frm.submit();		
	}
	
	function showMessage(msg){
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
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
<input type="hidden" name="forward_from"  value="<c:out value='${command.forward_from}'/>" />

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
<input type="hidden" name="srch_kbn1"   value="<c:out value='${command.srch_kbn1}'/>" />
<input type="hidden" name="srch_kbn2"   value="<c:out value='${command.srch_kbn2}'/>" />
<input type="hidden" name="srch_lwr_nm"   value="<c:out value='${command.srch_lwr_nm}'/>" />
<input type="hidden" name="srch_reg_nm"   value="<c:out value='${command.srch_reg_nm}'/>" />
<input type="hidden" name="srch_start_dt"   value="<c:out value='${command.srch_start_dt}'/>" />
<input type="hidden" name="srch_end_dt"   value="<c:out value='${command.srch_end_dt}'/>" />
<input type="hidden" name="srch_order_type"   value="<c:out value='${command.srch_order_type}'/>" />

<!-- key form-->
<input type="hidden" name="event_no"   value="<c:out value='${command.event_no}'/>" />
<input type="hidden" name="event_nm"   value="<c:out value='${lom.event_nm}'/>" />
<input type="hidden" name="event_gbn1"   value="<c:out value='${lom.event_gbn1}'/>" />
<input type="hidden" name="event_gbn2"   value="<c:out value='${lom.event_gbn2}'/>" />
<input type="hidden" name="lawsuit_trgt_nm"   value="<c:out value='${lom.lawsuit_trgt_nm}'/>" />

<input type="hidden" id="lawfirm_id" name="lawfirm_id" value="" />
<input type="hidden" id="acpt_no" name="acpt_no" value="" />

<!-- 첨부파일정보 -->
<input type="hidden" name="fileInfos" 		value="" />
<input type="hidden" name="file_bigclsfcn" 	value="F005" />
<input type="hidden" name="file_midclsfcn" 	value="F00503" />
<input type="hidden" name="file_smlclsfcn" 	value="" />
<input type="hidden" name="ref_key" 		value="<c:out value='${command.event_no}'/>" />
<input type="hidden" name="view_gbn" 		value="download" />

<!-- //**************************** Form Setting **************************** -->

<div id="wrap">
<!-- container -->
<div id="container">
	<!-- content -->
	<div id="content">
	<!-- location -->
	<div class="location">
		<IMG SRC="<%=IMAGE %>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/>
	</div>
	<!-- //location -->
		<div class="t_titBtn">
				<!-- title -->
				<div class="title" >
					<h3><spring:message code="las.page.field.lawservice.event" />&nbsp;<spring:message code="las.page.field.lawservice.detail" /></h3>
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
					<th><spring:message code="las.page.field.lawservice.eventnm" /></th>
					<td colspan="3">
						<c:out value='${lom.event_nm}'/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.kbn" /></th>
					<td>
						<select id="event_gbn1" name="event_gbn1" style="width:150px;" disabled >
						</select>
						<select id="event_gbn2" name="event_gbn2" style="width:100px;" disabled >
						</select>
						</td>
					<th><spring:message code="las.page.field.lawservice.lawsuittrgt" /></th>
					<td>
						<c:out value='${lom.lawsuit_trgt_nm}'/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.moneyrech" /></th>
					<td colspan="3">
						<c:forEach var="list" items="${dept_list}" varStatus="status">
							<c:out value='${list.dept_nm}' />
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
					<th><spring:message code="las.page.field.lawservice.regnm" /></th>
					<td><c:out value='${lom.reg_nm}'/></td>
					<th><spring:message code="las.page.field.lawservice.acptday" /></th>
					<td><c:out value='${lom.reg_dt}'/></td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.lwr" /></th>
					<td colspan="3">
						<c:forEach var="list" items="${lwr_list}"  varStatus="status">
							<a href='javascript:Menu.detail("frm", "_top", "20110804083733310_0000000168", "20110804083733310_0000000168", "/las/lawservice/lawyerManage.do?method=detailLawyerManage&lwr_no=<c:out value='${list.lwr_no}'/>")' >
								<c:out value='${list.lwr_nm}' />
							</a>
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
					<th><spring:message code="las.page.field.lawservice.eventsum" /></th>
					<td colspan="3"><c:out value='${lom.event_cont}' escapeXml="false" /></td>
				</tr>
				<tr class="end">
					<th><spring:message code="las.page.field.lawservice.attachfile" /></th>
					<td colspan="3">
						<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="80px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- //view -->
		
		<c:choose>

			<c:when test="${command.forward_from != '1'}">

				<table border="0" cellspacing="0" cellpadding="0" class="list_basic  mt20">
				  <colgroup>
				  <col class="tal_w02" />
				  <col />
				  <col />
				  <col class="tal_w04" />
				  <col class="tal_w04" />		
				  </colgroup>
				  <thead>
					<tr>
					  <th><spring:message code="las.page.field.lawservice.order" /></th>
					  <th><spring:message code="las.page.field.lawservice.lawfirm" /></th>
					  <th><spring:message code="las.page.field.lawservice.lawsuittrgt" /></th>
					  <th><spring:message code="las.page.field.lawservice.acptnm" /></th>
					  <th><spring:message code="las.page.field.lawservice.acptday" /></th>
					</tr>
				  </thead>
				  <tbody>
					<c:choose>
					<c:when test="${event_accept_list_cnt > 0}">
						<c:forEach var="list" items="${event_accept_list}">
							<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
							    <td  class="tC"><c:out value='${list.rn}'/></td>
								<td  class="tL">
									<a href='javascript:Menu.detail("frm", "_top", "20110804083547346_0000000167", "20110804083547346", "/las/lawservice/lawfirmManage.do?method=detailLawfirmManage&lawfirm_id=<c:out value='${list.lawfirm_id}'/>")' >
									<strong><c:out value='${list.lawfirm_nm}'/></a></strong></nobr></td>
				            	<td><c:out value='${list.lawsuit_trgt_nm}'/></td>
				            	<td><c:out value='${list.reg_nm}'/></td>
				            	<td><c:out value='${list.reg_dt}'/></td>
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

			</c:when>

		    <c:otherwise>	
		       	<script language="javascript">
		       	/**
		    	* INVOICE 기등록 여부 확인 정보를  Ajax로 호출
		    	*/
		    	function srch_invoice(invoice_no) {
		       				     		
		     		if($.trim($("#invoice_no").val()) == ''){
		     			alert("<spring:message code='las.page.msg.lawservice.invoiceno' />");
		     			$("#invoice_no").val("");
		     			return;
		     		}
		     		
		     		if(!checkMaxLength($("#invoice_no").val(),"24")){
		     			alert("<spring:message code='las.page.msg.lawservice.maxinvoicenum' />");
		     			$("#invoice_no").val("");
		     			return;
		     		}
		     		
		    		var options = {
		    				url: "<c:url value='/las/lawservice/eventAcceptSrvCost.do?method=srchInvoiceNo' />",
		    				type: "post",
		    				dataType: "json",
		    				success: function(responseText, statusText) {
		    					if(responseText.returnCnt != 0) {
		    						$.each(responseText, function(entryIndex, entry) {
		    							if(entry['acpt_no'] != '') {
		    								detailInvoiceView(entry['acpt_no']);
		    							} else {
		    								alert("<spring:message code='las.page.field.lawservice.noResult'/>");
		    							}
		    						});
		    				} else {
								alert("<spring:message code='las.page.field.lawservice.noResult'/>");
							}
		    			}
		    		}
		    		
		    		$("#frm").ajaxSubmit(options);	
		    	}
				</script>
				</br>
			
				<div class="t_titBtn">
					<div class="input_list">
						Invoice No: 
						  <input alt="INVOICE NO" class="text_full" type="text"  style='width:247px;' name="invoice_no" id="invoice_no" value="<c:out value='${lom.invoice_no}'/>" required onKeyDown="javascript:if(event.keyCode==13){event.returnValue = false;srch_invoice(this.value);}"  />
			    	</div>                
	       				 <!-- button -->
					<div class="btn_wrap_t fR">
						<span class="btn_all_w"><span class="save"></span><a href="javascript:goSrvInsert()"><spring:message code="las.page.button.lawservice.input"/></a></span>
					</div>
					<!-- //button --> 
				</div>
						    
	
				<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
				  <colgroup>
				  <col width="7%" />
				  <col width="15%" />
				  <col width="18%" />
				  <col width="15%" />
				  <col width="15%" />
				  <col width="15%" />
				  <col width="15%" />		
				  </colgroup>
				  <thead>
					<tr>
					  <th><spring:message code="las.page.field.lawservice.order" /></th>
					  <th><spring:message code="las.page.field.lawservice.eventnm" /></th>
					  <th><spring:message code="las.page.field.lawservice.lawfirm" /></th>
					  <th><spring:message code="las.page.field.lawservice.regnm" /></th>
					  <th><spring:message code="las.page.field.lawservice.acptday" /></th>
					  <th><spring:message code="las.page.field.lawservice.unpay" /></th>
					  <th><spring:message code="las.page.field.lawservice.remityn" /></th>
					</tr>
				  </thead>
				  <tbody>
					<c:choose>
					<c:when test="${invoice_list_cnt > 0}">
						<c:forEach var="list" items="${invoice_list}">
							<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
							    <td  class="tC"><c:out value='${list.rn}'/></td>
								<td  class="tL">
									<img src="<%=IMAGE%>/icon/icon_reply.gif" />
										<c:if test="${list.upfile_yn != 'N'}">
											<img src="<%=IMAGE%>/icon/ico_save_w.gif" />
										</c:if>
									<a href='javascript:detailInvoiceView(<c:out value='${list.acpt_no}'/>)' >	
										<c:choose>
											<c:when test="${!empty lom.acpt_no}">
												<c:choose>
													<c:when test="${list.acpt_no == lom.acpt_no}">
														<strong><c:out value='${list.invoice_no}'/></strong>
													</c:when>
													<c:otherwise>
														<c:out value='${list.invoice_no}'/>
													</c:otherwise>	
												</c:choose>
											</c:when>
											<c:otherwise>
												<c:out value='${list.invoice_no}'/>
											</c:otherwise>
										</c:choose>							
									</a>				            	
				            	<td>
				            		<a href='javascript:Menu.detail("frm", "_top", "20110804083547346_0000000167", "20110804083547346", "/las/lawservice/lawfirmManage.do?method=detailLawfirmManage&lawfirm_id=<c:out value='${list.lawfirm_id}'/>")' >
				            			<c:out value='${list.lawfirm_nm}'/>
				            		</a>
				            	</td>
				            	<td><c:out value='${list.reg_nm}'/></td>
				            	<td><c:out value='${list.acptday}'/></td>
				            	<td><c:out value='${list.unpayday}'/></td>
				            	<td><c:out value='${list.remitday}'/></td>
							</tr>
						</c:forEach>
				    </c:when>
				    <c:otherwise>
						<tr onMouseOut="this.className='';" onMouseOver="this.className='selected'">
							<td colspan="7" align="center"><spring:message code="las.msg.succ.noResult" /></td>
						</tr>
				    </c:otherwise>
				</c:choose>
				  </tbody>
				  </table>
		    </c:otherwise>
		</c:choose>
		
				<div class="t_titBtn">
					<div class="btn_wrap">
						<span class="btn_all_w"><span class="list"></span><a href="javascript:goList()"><spring:message code="las.page.button.lawservice.golist" /></a></span>
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
</form:form>
</body>
</html>