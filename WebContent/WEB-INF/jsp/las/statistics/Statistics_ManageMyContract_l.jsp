<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.manage.dto.ManageForm" %>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sec.common.util.ClmsBoardUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%-- 
/**
 * 파  일  명 : Statistics_ManageMyContract_l.jsp
 * 프로그램명 : 통계에서 넘어오는 계약 목록
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2013.12
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	List resultList = (List)request.getAttribute("resultList");
	ManageForm command = (ManageForm)request.getAttribute("command");
%>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />

<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/menu.js'/>"></script>

<script language="javascript">

	$(document).ready(function(){
		
		//Step 세팅            search_state
		getCodeSelectByUpCd("frm", "srch_step", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=C025&useman_mng_itm2=clmsList&combo_type=T&combo_del_yn=N&combo_selected='+'<c:out value='${command.srch_step}'/>');
		getCodeSelectByUpCd("frm", "srch_state", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=' + '<c:out value='${command.srch_step}'/>' +'&combo_type=T&combo_del_yn=N&combo_selected='+'<c:out value='${command.srch_state}'/>');
		
		//회사선택
		getCodeSelectByUpCd("frm", "sElComp", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=USERCOMP&combo_type=T&combo_del_yn=N&sel_grd=<c:out value='${command.sSel_grd}'/>&combo_selected='+'<c:out value='${command.sElComp}'/>');
		
	});
	
	$(function() {
		$("#srch_step").change(function() {
			var grpCd = $("#srch_step").val();
			if(grpCd == "C02502") {
				getCodeSelectByUpCd("frm", "srch_state", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&useman_mng_itm1=' + grpCd +'&combo_type=T&combo_del_yn=N&combo_selected=');
			} else if(grpCd == "C02503") {
				getCodeSelectByUpCd("frm", "srch_state", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&useman_mng_itm2=' + grpCd +'&combo_type=T&combo_del_yn=N&combo_selected=');
			} else {
				getCodeSelectByUpCd("frm", "srch_state", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=' + grpCd +'&combo_type=T&combo_del_yn=N&combo_selected=');
			}
		});
		
	});
	
	/**
	* 메시지 처리
	*/
	function showMessage(msg) {
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
		}
	}
	
	/**
	* 조회(검색)버튼 function
	*/ 
	function searchAction(gbn){
		var frm = document.frm;

		viewHiddenProgress(true) ;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/manage/myContract.do' />";
	    frm.method.value = "listMyContract";
	    frm.curPage.value = "1";
	    frm.submit();
	}
	
	/**
	* 조회 페이지 이동
	*/	
	function goPage(pgNum){
		var frm = document.frm;
		viewHiddenProgress(true) ;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/manage/myContract.do' />";
		frm.method.value = "listMyContract";
		frm.curPage.value = pgNum;
		
		frm.submit();
	}
	
	/**
	* 관리(상세)페이지 이동
	*/	
	function detailAction(id, prcs, depth_status){
		
		//alert("id = "+id+"  prcs = "+prcs+"  depth_status = "+depth_status);
		
		var frm = document.frm;
		var formId = "frm";
		var target = "_top";
		var menuId2 = "";
		var initUrl = "";
		var myAction = "";
		var myMethod = "";
		
		frm.ismycontract.value = "Y";

		//2012-05-17 추가. 사업부계약관리자 && 원본미등록 일시 myapproval 로 넘어간다.
		if(<%=ClmsBoardUtil.searchRole(request, "RD01")%> == true && (depth_status == 'C02642' || depth_status == 'C02685')){
			viewHiddenProgress(true);
			frm.cnsdreq_id.value = id;
			frm.target = "_self";
			frm.action = "<c:url value='/clm/manage/myApproval.do' />";
			frm.method.value ="detailMyApproval";
			frm.submit();
		} else if(prcs == "C02506" || prcs == "C02507") {  // 사별이관
			
			frm.cnsdreq_id.value = id;
			frm.target = "_self";
			frm.action = "<c:url value='/clm/review/consideration.do' />";
			frm.method.value = "detailConsideration";
			viewHiddenProgress(true);
			frm.submit();
		} else {
			if(prcs == "C02501"){   // 계약 검토/최종회신
				initUrl = "/clm/manage/consideration.do?method=detailConsiderationNew&cnsdreq_id="+id;
				myAction = "/clm/manage/consideration.do";
				myMethod = "detailConsiderationNew";
				
				// 회신일 경우 위 메소드에서 다시 분기 함.
				
			}else if(prcs == "C02502"){  // 계약 체결
				initUrl = "/clm/manage/consultation.do?method=detailConsultationNew&cnsdreq_id="+id;
				myAction = "/clm/manage/consultation.do";
				myMethod = "detailConsultationNew";
			}else if(prcs == "C02503"){ // 계약 등록
				initUrl = "/clm/manage/conclusion.do?method=detailConclusionNew&cnsdreq_id="+id;
				myAction = "/clm/manage/conclusion.do";
				myMethod = "detailConclusionNew";
			}else if(prcs == "C02504"){ // 계약 이행
				initUrl = "/clm/manage/execution.do?method=listContract&cnsdreq_id="+id;
				myAction = "/clm/manage/execution.do";
				myMethod = "listContract";
			}else if(prcs == "C02505"){ // 계약 종료
				initUrl = "/clm/manage/completion.do?method=listContract&cnsdreq_id="+id;
				myAction = "/clm/manage/completion.do";
				myMethod = "listContract";
			}
			viewHiddenProgress(true);
			frm.target = "_self";
			frm.action = myAction;
			frm.method.value = myMethod;
			frm.cnsdreq_id.value = id;
			frm.submit();

		}
	}

</script>
</head>
<html>
<body onload='showMessage("<c:out value='${returnMessage}'/>");'>

<div id="cntrtLayer" style="display:none;">
</div>
<div id="wrap">
	<!-- container -->
	<div id="container">

		<!-- Title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.manageMyContract.listTitle" /></h3>
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
			<div id="content_in">
				<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id='frm' method="post" autocomplete="off"> 
	<!-- search form -->
	<input type="hidden" name="method"   value="" />
	<input type="hidden" name="menu_id"   value="<c:out value='${command.menu_id}'/>" />

	<input type="hidden" name="list_mode" value="<c:out value='${command.list_mode}'/>"/>
	<input type="hidden" id="srch_resp_dept" name="srch_resp_dept" value="<c:out value='${command.srch_resp_dept}'/>" class="srch"/>
	<input type="hidden" id="srch_oppnt_cd" name="srch_oppnt_cd" value="<c:out value='${command.srch_oppnt_cd}'/>" class="srch"/>

	<!-- PopUp Param -->
	<input type="hidden" name="customer_gb" value=""/>
	<input type="hidden" name="dept_nm" value=""/>
	<input type="hidden" name="page_gbn" value="one"/>
	<input type="hidden" name="srch_customer" value=""/>
				
	<!-- Detail Page Param -->
	<input type="hidden" name="cnsdreq_id" value="" />
	<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>" />
	<input type="hidden" name="status_mode" value="<c:out value='${command.status_mode}'/>" />

	<!-- Detail View -->
	<input type="hidden" name="targetMenuId" />
	<input type="hidden" name="target_menu_id" />
	<input type="hidden" name="selected_menu_id" />
	<input type="hidden" name="selected_menu_nm" />
	<input type="hidden" name="selected_menu_detail_id" />
	<input type="hidden" name="set_init_url" />
	
	<!-- Detail hidden value -->
	<input type="hidden" name="srch_lsrch_Ltype_cd"      id="srch_lsrch_Ltype_cd"      value="<c:out value='${command.srch_lsrch_Ltype_cd}'/>" />        
	<input type="hidden" name="srch_lsrch_Mtype_cd"      id="srch_lsrch_Mtype_cd"      value="<c:out value='${command.srch_lsrch_Mtype_cd}'/>" />
	<input type="hidden" name="srch_lsrch_Stype_cd"      id="srch_lsrch_Stype_cd"      value="<c:out value='${command.srch_lsrch_Stype_cd}'/>" />
	<input type="hidden" name="srch_sSrch_Ltype_cd"      id="srch_sSrch_Ltype_cd"      value="<c:out value='${command.srch_sSrch_Ltype_cd}'/>" />        
	<input type="hidden" name="srch_sSrch_Mtype_cd"      id="srch_sSrch_Mtype_cd"      value="<c:out value='${command.srch_sSrch_Mtype_cd}'/>" />
	<input type="hidden" name="srch_sSrch_Stype_cd"      id="srch_sSrch_Stype_cd"      value="<c:out value='${command.srch_sSrch_Stype_cd}'/>" />
	<input type="hidden" name="srch_cntrt_trgt_det2"     id="srch_cntrt_trgt_det2"     value="<c:out value='${command.srch_cntrt_trgt_det2}'/>" />
	<input type="hidden" name="srch_sPayment_gbn"        id="srch_sPayment_gbn"        value="<c:out value='${command.srch_sPayment_gbn}'/>" />
	<input type="hidden" name="srch_bfhdcstn_apbtman_nm" id="srch_bfhdcstn_apbtman_nm" value="<c:out value='${command.srch_bfhdcstn_apbtman_nm}'/>" />
	<input type="hidden" name="srch_sMn_cnsd_dept"       id="srch_sMn_cnsd_dept"       value="<c:out value='${command.srch_sMn_cnsd_dept}'/>" />
	
	<input type="hidden" name="srch_auto_rnew_yn"        id="srch_auto_rnew_yn"        value="<c:out value='${command.srch_auto_rnew_yn}'/>" />
	<input type="hidden" name="srch_sSeal_mthd"          id="srch_sSeal_mthd"          value="<c:out value='${command.srch_sSeal_mthd}'/>" />
	<input type="hidden" name="srch_signman_nm"          id="srch_signman_nm"          value="<c:out value='${command.srch_signman_nm}'/>" />
	<input type="hidden" name="srch_sLoac"               id="srch_sLoac"               value="<c:out value='${command.srch_sLoac}'/>" />
	<input type="hidden" name="srch_sDesp_resolt_mthd"   id="srch_sDesp_resolt_mthd"   value="<c:out value='${command.srch_sDesp_resolt_mthd}'/>" />
	
	<input type="hidden" name="ismycontract"	id="ismycontract" value="" />
	<!-- Copy Page Param -->
	<input type="hidden" name="c_cnsdreq_id" value="" />
	<input type="hidden" name="c_prcs_depth" value="" />
		
<!-- // **************************** Form Setting **************************** -->
			
			
			<!-- search-->		    
			<div class="search_box">
				<div class="search_box_inner">
					<table class="search_tb">
					
						<tr>
							<td>
							<!-- 의뢰 기준 조회일 경우 -->
							
								<table class="search_form" style='min-width:700px;'>
									<colgroup>
                                        <col width="*%"/>
                                        <col width="*%"/>
                                        <col width="*%"/>
                                    </colgroup>
									
									
									
									<tr>  
									   <th><spring:message code="clm.page.field.manageCommon.srchStep" /> / <spring:message code="clm.page.field.manageCommon.srchStatus" /></th>
                                        <td>
                                            <select class="srch" name="srch_step" id="srch_step" value="<c:out value='${command.srch_step}'/>" style='width:85px'/>
                                            </select>
                                            /           
                                            <select class="srch" name="srch_state"  id="srch_state" style='width:84px'/>
                                            </select>
                                        </td>       
										<!-- 전자변호가 들어 올 경우 회사를 조회 할 수 있는 콤보 리스트가 나타나게 된다. -->
										
										<c:choose>
											<c:when test="${elUserlYn}">
												<th><spring:message code="clm.page.msg.common.selCompNm" /></th>
											    <td>
												    <select class="srch" name="sElComp"	id="sElComp"> </select>
											    </td>
											</c:when>
											<c:otherwise>
												<td></td>
												<td></td>
											</c:otherwise>
										</c:choose>
								
										<td class="tR">
											<a href="javascript:searchAction('');"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="clm.page.msg.common.search" htmlEscape="true" />" class='mt5' style='margin-right:9%' /></a>
										</td>
										
										
										
									</tr>
								</table>
							
							<!-- 계약 기준 조회일 경우 -->
							</td>
							
						</tr>
					</table>
				</div>
			</div>
			<!--//search-->
			
			<!-- list -->
			<!-- 의뢰기준일 경우 -->
		
		   
			<table class="list_basic">

				<colgroup>
					<col width="20%" />
					<col width="7%" />
					<col width="11%" />
					<col width="9%" />
					<col width="9%" />
					<col width="10%" />
					<col width="9%" />
					<col width="70px" />
					<col width="95px" />
			  	</colgroup>

			  	<thead>
					<tr>
					    
						<th title='<spring:message code="clm.page.field.manageCommon.reqTitle" />&#13;(<spring:message code="clm.page.field.manageCommon.srchCntrtNm" />)'><spring:message code="clm.page.field.manageCommon.reqTitle" /><br>(<spring:message code="clm.page.field.manageCommon.srchCntrtNm" />)</th>
						<th class="overflow" title="<spring:message code="clm.page.msg.manage.announce032" htmlEscape="true" />"><spring:message code="clm.page.field.manageCommon.reqmanNm" /><br/><spring:message code="clm.page.field.manageCommon.respmanNm" /></th>
						<th class="overflow" title='<spring:message code="las.page.field.contractmanager.consideration.first"/> <spring:message code="clm.page.field.manageCommon.reqDay" />&#13;(<spring:message code="clm.page.field.contract.conclusion.detail.conclusionorgacceptday" />)'>
						 
						    <spring:message code="las.page.field.contractmanager.consideration.first"/> <spring:message code="clm.page.field.manageCommon.reqDay" /><br/>
						    (<spring:message code="clm.page.field.contract.conclusion.detail.conclusionorgacceptday" />)
						  
						</th>
						<th><spring:message code="clm.page.field.manageCommon.respDept" /></th>
						<th><spring:message code="clm.page.field.manageCommon.oppntCd" /></th>
						<th class='overflow'><spring:message code="clm.page.field.manageCommon.srchLasCnsdmanNm_br" /></th>
						<th><spring:message code="clm.page.field.manageCommon.srchCntrtId" /></th>
						<th><spring:message code="clm.page.field.manageCommon.step" /></th>
						<th><spring:message code="clm.page.field.manageCommon.status" /></th>
					</tr>
				</thead>
			 	<tbody>
			  <%
				if(pageUtil.getTotalRow() > 0) {
					String sover_title = "";
					for(int idx=0; idx<resultList.size(); idx++) {
						ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
				%>			
				    <tr  <%if(idx == resultList.size()-1){%>class="end"<%}%> onmouseout="this.className='<%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onmouseover="this.className='on <%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'">
					      <td class="tL txtover overflow" style="line-height:150%; border-left:1px solid #CADBE2;" title="<%=StringUtil.convertHtmlTochars((String)lom.get("req_title")) %>&#13(<%=StringUtil.convertHtmlTochars((String)lom.get("cntrt_nm")) %>)">
					        <a onclick="javascript:detailAction('<%=lom.get("cnsdreq_id") %>', '<%=lom.get("prcs_depth")%>','<%=lom.get("depth_status")%>');">
						        <span style='color:#485B91'><%=StringUtil.convertHtmlTochars((String)lom.get("req_title")) %></span></a><br/>
						        <span style='color:#A9B4BC'>(<%=StringUtil.convertHtmlTochars((String)lom.get("cntrt_nm")) %>)</span>
						   
					      </td>
					      <% sover_title = lom.get("cntrt_respman_nm")!=null && !lom.get("cntrt_respman_nm").equals("")? "&#13;" + lom.get("cntrt_respman_nm") : ""; %>
					      <td class="txtover tC" title="<%=lom.get("reqman_nm")+sover_title %>">
					        <%=lom.get("reqman_nm") %>
					        <%=lom.get("cntrt_respman_nm")!=null && !lom.get("cntrt_respman_nm").equals("")? "<br>" + lom.get("cntrt_respman_nm") : "" %>
					      </td>
					      <td class="txtover tC" style='text-align:center'>
					        
					            <%=lom.get("req_dt") %><br/>
					            <%if(lom.get("org_acptday")!=null && !lom.get("org_acptday").equals("")) { %>
					            <span style='color:#A9B4BC;'>(<%=lom.get("org_acptday") %>)</span>
					            <%  } %>
					        </td>
					      <td class="txtover tC" title="<%=lom.get("cntrt_resp_dept_nm") %>"><%=lom.get("cntrt_resp_dept_nm") %></td>
					      <td class="txtover tC" title="<%=lom.get("cntrt_oppnt_nm") %>"><%=lom.get("cntrt_oppnt_nm") %></td>
					      <td class="txtover tC" title="<%=StringUtil.replace((String)lom.get("cnsdmans"),"<br>","&#13;") %>"><%=lom.get("cnsdmans") %></td>
					      <td class="txtover tC" title="<%=lom.get("cntrt_no") %>"><%=lom.get("cntrt_no") %></td>
					      <td class="txtover tC" title="<%=lom.get("prcs_depth_nm") %>"><%=lom.get("prcs_depth_nm") %></td>
					      <td class="txtover tC" title="<%=lom.get("depth_status_nm") %>"><%=lom.get("depth_status_nm") %></td>
				    
			        </tr>
		      <%	}
				}else{		  
 			  %> 
					<tr>
					  <td colspan="10" class='tC'><spring:message code="secfw.msg.succ.noResult" /></td>
					</tr>
			  <%}%>			
			  </tbody>
			</table>
			
			
			<!-- //list -->

		
				<!-- total data -->
				<div class="total_num">
				<%if("cnsdreq".equals(command.getList_mode())) {%>
					Total
				<%} else { %>
					Total
				<%} %>
					: <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%>
				</div>
				<!-- //total data -->
				
				<!-- pagination  -->
				<div class="pagination mt_22">
				<%=pageUtil.getPageNavi(pageUtil, "") %>
				</div>
				<!-- //pagination -->
			
			
			</form:form>
			
			
		</div>
		</div>
		<!-- //content -->
		
		
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>
<!-- footer -->
<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- //footer -->

</body>
</html>

