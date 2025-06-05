<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.review.dto.ConsiderationForm" %>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%-- 
/**
 * 파  일  명 : RelConsideration_total_l.jsp
 * 프로그램명 : 연관 계약 전사계약 List
 * 설      명 : 계약관리시스템의 계약검토목록을 참조하여 작성 (ManageConsideration_l.jsp)
 * 작  성  자 : 최준영
 * 작  성  일 : 2011.11.05
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	List resultList = (List)request.getAttribute("resultList");
	ConsiderationForm command = (ConsiderationForm)request.getAttribute("command");
%>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta sci="RelConsideration_total_l.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<LINK href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet">

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script language="javascript">
	
	$(document).ready(function(){
		//Step 세팅            search_state
		getCodeSelectByUpCd("frm", "srch_step", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=F&combo_grp_cd=C025&useman_mng_itm1=totalList&combo_type=T&combo_del_yn=N&combo_selected='+'<c:out value='${command.srch_step}'/>');
		getCodeSelectByUpCd("frm", "srch_state", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=F&combo_grp_cd=' + '<c:out value='${command.srch_step}'/>' +'&combo_type=T&combo_del_yn=N&combo_selected='+'<c:out value='${command.srch_state}'/>');
		//비즈니스 단계 
		getCodeSelectByUpCd("frm", "srch_biz_clsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T01&combo_abbr=F&combo_type=S&combo_del_yn=N&combo_selected="+"<c:out value='${command.srch_biz_clsfcn}'/>");   
		//계약유형1
		getCodeSelectByUpCd("frm", "srch_cnclsnpurps_bigclsfcn", "/common/clmsCom.do?method=getComboHTML&combo_gbn=CONTRACTTYPE&combo_up_cd=T03&combo_abbr=F&combo_type=S&combo_del_yn=N&combo_selected="+"<c:out value='${command.srch_cnclsnpurps_bigclsfcn}'/>");   
		//연계시스템
		getCodeSelectByUpCd("frm", "srch_if_sys_cd", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=F&combo_grp_cd=C066&combo_type=T&combo_del_yn=N&combo_selected='+'<c:out value='${command.srch_if_sys_cd}'/>');
		
	 	initCal("srch_start_reqday");   //첫번째 날짜창 설정 : 변수는 해당 태그의 id값
	    initCal("srch_end_reqday");     //두번째 날짜창 설정 : 변수는 해당 태그의 id값
	 	initCal("srch_start_cnlsnday");   //세번째 날짜창 설정 : 변수는 해당 태그의 id값
	    initCal("srch_end_cnlsnday");     //네번째 날짜창 설정 : 변수는 해당 태그의 id값
	});
		
	$(function() {
		$("#srch_step").change(function() {
			var grpCd = $("#srch_step").val();
			if(grpCd == "C02502") {
				getCodeSelectByUpCd("frm", "srch_state", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=F&useman_mng_itm1=' + grpCd +'&combo_type=T&combo_del_yn=N&combo_selected=');
			} else if(grpCd == "C02503") {
				getCodeSelectByUpCd("frm", "srch_state", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=F&useman_mng_itm2=' + grpCd +'&combo_type=T&combo_del_yn=N&combo_selected=');
			} else {
				getCodeSelectByUpCd("frm", "srch_state", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=F&combo_grp_cd=' + grpCd +'&combo_type=T&combo_del_yn=N&combo_selected=');
			}
		});
	
		$("#srch_review_title").keydown(function(event) {
			if(event.keyCode == "13") {
				searchAction('');
			}
		});

		$("#srch_reqman_nm").keydown(function(event) {
			if(event.keyCode == "13") {
				searchAction('');
			}
		});

		$("#srch_respman_nm").keydown(function(event) {
			if(event.keyCode == "13") {
				searchAction('');
			}
		});

		$("#srch_cnsdman_nm").keydown(function(event) {
			if(event.keyCode == "13") {
				searchAction('');
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

	/** 계약정보 레이어 호출 **/
	function listCntrt(id, reqId, display){
		var frm = document.frm;
		$('#cntrtLayer').html("");
		$("#Layer1").attr('style', 'display:none;');
		
//		alert(display);
		if("show" == display){
			frm.cnsdreq_id.value = reqId;
			var Obj = getBounds(document.getElementById(id));
			
			var left = Obj.right+2;
			var top = Obj.top;
			//alert(width+' '+left+' '+top);
			var options = {
					url: "<c:url value='/clm/manage/contract.do?method=getCntrtHTML' />",
					type: "post",
					success: function(responseText,returnMessage) {
						$('#cntrtLayer').html("");
						$('#cntrtLayer').html(responseText);
						//alert(responseText);
					}
			}
			$("#frm").ajaxSubmit(options);		

			$("#cntrtLayer").attr('style', 'position:absolute; left:'+left+'px; top:'+top+'px; z-index:1; display:block;');
		}else{
			$("#cntrtLayer").attr('style', 'display:none;');
		}
	}
	
	/** 현재 위치 구하기 **/
	function getBounds(obj) {
		var ret = new Object();
		if (document.all) {
			var rect = obj.getBoundingClientRect();
			ret.left = rect.left + (document.documentElement.scrollLeft || document.body.scrollLeft);
			ret.right = rect.right;
			ret.top = rect.top + (document.documentElement.scrollTop || document.body.scrollTop);
		} else {
			var box = document.getBoxObjectFor(obj);
			ret.left = box.x;
			ret.top = box.y;
			ret.width = box.width;
			ret.height = box.height;
		}
		return ret;
	}	
	
	/**
	* 조회버튼 function
	*/ 
	function searchAction(gbn){
		var frm = document.frm;
		
		if(gbn != ""){
			frm.list_mode.value = gbn;
			$(".srch").val("");
		}
		
		//상대방명 없을 경우 코드값 초기화
		if($("#srch_oppnt_nm").val() == ""){
			$("#srch_oppnt_cd").val("");
		}
	
		//부서명 없을 경우 코드값 초기화
		if($("#srch_resp_dept_nm").val() == ""){
			$("#srch_resp_dept").val("");
		}
		
		viewHiddenProgress(true) ;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/review/consideration.do' />";
	    frm.method.value = "listConsideration";
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
		frm.action = "<c:url value='/clm/review/consideration.do' />";
		frm.method.value = "listConsideration";
		frm.curPage.value = pgNum;
		
		frm.submit();
	}

	/**
	*	담당부서 팝업
	*/
	function popDept(){
		 var frm = document.frm;
		 
		 PopUpWindowOpen('', 640, 640, true);
		 frm.target = "PopUpWindow";
		 frm.action = "<c:url value='/common/clmsCom.do' />";
		 frm.method.value = "listDeptTree";
		 frm.dept_nm.value = frm.srch_resp_dept_nm.value;
		 frm.submit();
	}
	
	function setDeptInfo(retDeptCd, retDeptNm){
		$('#srch_resp_dept').val(retDeptCd);
		$('#srch_resp_dept_nm').val(retDeptNm);	
	}
	
	function goOldConsiderationInsert(){
		var frm = document.frm;
		viewHiddenProgress(true) ;
		frm.target = "_self";
		frm.action = "<c:url value='/las/contractmanager/consideration.do' />";
		frm.method.value = "forwardInsertyOldConsideration";
		frm.submit();
	}
	
	// 부모 창으로 내용을 전달 함.
	function parentContract(id, name, biz, sArg) {
		
		//alert("전달 = "+ id+" "+name+" "+biz+" "+sArg );
		
		opener.setContract(id,name,biz,sArg);

	}
	
	// 상세 창 열기.
	function detailAction(cnsdreq_id) {
		
		//alert("전달 = "+ cnsdreq_id );
		
		PopUpWindowOpen('/clm/manage/completion.do?method=listContract&menu_id=20110803091537445_0000000053&conListGu=Z1000&cnsdreq_id='+cnsdreq_id, 900, 600, true,"detailPopUp2");

	}
	
	/**
	* 취소
	*/
	function cancle(){
		if(confirm("<spring:message code='secfw.msg.ask.cancel' />")){
			
			opener.setContract('','','','');
			
			window.close();
		}
	}
	
	/**
	* 확인
	*/
	function con(){
        
		if(confirm("<spring:message code='las.page.field.contractManager.willBeInput'/>")){
			window.close();
		}
		
	}
	
	// 상세 페이지 팝업오픈
	function PopUpWindowOpen(surl, popupwidth, popupheight, bScroll, popName) {
		if( popupwidth  > window.screen.width )
			popupwidth = window.screen.width;
		if( popupheight > window.screen.height )
			popupheight = window.screen.height; 
			
		if( isNaN(parseInt(popupwidth)) ){
			Top  = (window.screen.availHeight - 600) / 2;
			Left = (window.screen.availWidth  - 800) / 2;
		} else {
			Top  = (window.screen.availHeight - popupheight)  / 2;
			Left = (window.screen.availWidth  - popupwidth) / 2;
		}

		if (Top < 0) Top = 0;
		if (Left < 0) Left = 0;
		
		popupwidth = parseInt(popupwidth) + 10 ;
		popupheight = parseInt(popupheight) + 29 ;
		
		var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars=yes, resizable=yes, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
		PopUpWindow = window.open(surl, popName , Feture);

		PopUpWindow.focus();
		
	}
	
	
</script>
</head>

<body onLoad='showMessage("<c:out value='${returnMessage}'/>");'>
<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id='frm' method="post" autocomplete="off"> 
	<!-- search form -->
	<input type="hidden" name="method"   value="" />
	<input type="hidden" name="menu_id"   value="<c:out value='${command.menu_id}'/>" />

	<input type="hidden" name="list_mode" value="<c:out value='${command.list_mode}'/>"/>
	<input type="hidden" name="page_flag" value="5"/>
	<input type="hidden" name="cntrt_oppnt_nm" value=""/>
	<input type="hidden" id="srch_resp_dept" name="srch_resp_dept" value="<c:out value='${command.srch_resp_dept}'/>" class="srch"/>
	<input type="hidden" id="srch_oppnt_cd" name="srch_oppnt_cd" value="<c:out value='${command.srch_oppnt_cd}'/>" class="srch"/>

	<!-- PopUp Param -->
	<input type="hidden" name="customer_gb" value=""/>
	<input type="hidden" name="dept_nm" value=""/>
	<input type="hidden" name="page_gbn" value="one"/>
	<input type="hidden" name="srch_customer" value=""/>
				
	<!-- Detail Page Param -->
	<input type="hidden" name="cnsdreq_id" value="" />
	<input type="hidden" name="list_mode" value="notCnsdreq" />
	<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>">
	
	<input type="hidden" name="page_mode" value="rel" />
	
<!-- // **************************** Form Setting **************************** -->
<div id="cntrtLayer" style="display:none;">
</div>
<div id="wrap">
	<!-- container -->
	<div id="container">
		<!-- Location -->
		<!-- //Location -->	

		<!-- Title -->
		<div class="title">
			<h3><spring:message code="las.page.field.contractManager.lnkCont"/></h3>
		</div>
		<!-- //title -->			

		<!-- content -->
		<div id="content">
			<!-- tab -->
		  	<div class="tab_box">
				<ul class="tab_basic ptz">
			      <li class="on"><a><spring:message code="clm.page.field.manageCommon.srchListMode2" /></a></li>
		        </ul>
			</div>
			<!-- //tab -->
						
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
							<!-- 계약 기준 조회일 경우 -->
								<table class="search_form">
									<colgroup>
										<col width="12%"/>
										<col width="28%"/>
										<col width="10%"/>
										<col width="27%"/>
										<col width="10%"/>
										<col width="13%"/>
									</colgroup>
									<tr>
										<th><spring:message code="clm.page.field.manageCommon.srchCntrtNm" /></th>
										<td>
											<input class="srch" type="text" name="srch_review_title"  id="srch_review_title" value="<c:out value='${command.srch_review_title}'/>" style="width:100%"/>
										</td>
										<th><spring:message code="clm.page.field.manageCommon.srchRespDept" /></th>
										<td>
											<input type="text" name="srch_resp_dept_nm" id="srch_resp_dept_nm" value="<c:out value='${command.srch_resp_dept_nm}'/>" style="width:130px" class="text_search srch" onKeypress="if(event.keyCode == 13){popDept();return false;}"/><a href="javascript:popDept();"><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" />
										</td>
										<th><spring:message code="clm.page.field.manageCommon.srchRespmanNm" /></th>
										<td>
											<input class="srch" type="text" name="srch_respman_nm" id="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}'/>" style="width:80px" />
										</td>
									</tr>
									<tr>
										<th><spring:message code="clm.page.field.manageCommon.srchBizclsfcn" /></th>
										<td>
											<select class="srch" name="srch_biz_clsfcn" id="srch_biz_clsfcn">
											</select>	
										</td>										
										<th><spring:message code="clm.page.field.manageCommon.srchCnclsnpurpsBigclsfcn" /></th>
										<td>
											<select class="srch" name="srch_cnclsnpurps_bigclsfcn" id="srch_cnclsnpurps_bigclsfcn">
											</select>	
										</td>										
										<th><spring:message code="clm.page.field.manageCommon.srchCnsdmanNm" /></th>
										<td>
											<input type="text" name="srch_cnsdman_nm" id="srch_cnsdman_nm" value="<c:out value='${command.srch_cnsdman_nm}'/>" style="width:80px" />
										</td>
									</tr>
									<tr>
										<th><spring:message code="clm.page.field.manageCommon.srchOppntCd" /></th>
										<td>
											<input type="text" name="srch_oppnt_nm" id="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}'/>" style="width:130px" class="text_search srch" onKeypress="if(event.keyCode == 13){popOppnt();return false;}"/><a href="javascript:popOppnt();"><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" /></a>
										</td>										
										<th><spring:message code="clm.page.field.manageCommon.srchStep" /> / <spring:message code="clm.page.field.manageCommon.srchStatus" /></th>
										<td>
											<select class="srch" name="srch_step" id="srch_step">
											</select>
											/			
											<select class="srch" name="srch_state"	id="srch_state">
											</select>
										</td>							
										<th><spring:message code="las.page.field.contractManager.lnkSys"/></th>
										<td>
											<select class="srch" name="srch_if_sys_cd"	id="srch_if_sys_cd">
											</select>
										</td>										
									</tr>
									<tr>
										<th><spring:message code="clm.page.field.manageCommon.srchReqDay" /></th>
										<td>
											<input type="text" name="srch_start_reqday" id="srch_start_reqday" value="<c:out value='${command.srch_start_reqday}'/>"  class="text_calendar02 srch" readonly="readonly" />
											~
	  										<input type="text" name="srch_end_reqday" id="srch_end_reqday" value="<c:out value='${command.srch_end_reqday}'/>" class="text_calendar02 srch" readonly="readonly" />
										</td>
										<th><spring:message code="clm.page.field.manageCommon.srchCnlsnday" /></th>
										<td colspan="3">
											<input type="text" name="srch_start_cnlsnday" id="srch_start_cnlsnday" value="<c:out value='${command.srch_start_cnlsnday}'/>"  class="text_calendar02 srch" readonly="readonly" />
											~
	  										<input type="text" name="srch_end_cnlsnday" id="srch_end_cnlsnday" value="<c:out value='${command.srch_end_cnlsnday}'/>" class="text_calendar02 srch" readonly="readonly" />
										</td>
									</tr>
								</table>
							</td>
							<td class="vb tC">
								<a href="javascript:searchAction('');"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="las.page.field.contractManager.search"/>"/></a>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!--//search-->
			
			<!-- button -->
		  	<!-- //button -->
		  	
			<!-- list -->
			<!-- 의뢰기준일 경우 -->
			<!-- 계약기준일 경우 -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
				<colgroup>
					<col width="7%"/>
					<col width="25%" />
					<col width="9%" />
					<col width="15%" />
					<col width="9%" />
					<col width="9%" />
					<col width="9%" />
					<col width="8%" />
					<col width="9%" />
			  	</colgroup>
			  	<thead>
					<tr>
						<th><nobr><spring:message code="las.page.field.contractManager.select"/></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.cntrtNm" /></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.respmanNm" /></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.respDept" /></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.reqDay" /></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.cnsdmanNm" /></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.cntrtCnclsnDay" /></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.step" /></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.status" /></nobr></th>
					</tr>
				</thead>
			 	<tbody>
			  <%
				if(pageUtil.getTotalRow() > 0) {
					for(int idx=0; idx<resultList.size(); idx++) {
						ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
				%>			
				    <tr>
				      <td><input type="radio" name="check_item" value="<%=lom.get("cntrt_id") %>" onclick="javascript:parentContract('<%=lom.get("cntrt_id") %>','<%=lom.get("cntrt_nm") %>','<%=lom.get("biz_clsfcn") %>')"></td>
				      <td class="tL txtover" title="<%=lom.get("cntrt_nm") %>"><nobr><a href="javascript:detailAction('<%=lom.get("cnsdreq_id") %>')"><%=lom.get("cntrt_nm") %></a></nobr></td>
				      <td title="<%=lom.get("cntrt_respman_nm") %>"><nobr><%=lom.get("cntrt_respman_nm") %></nobr></td>
				      <td title="<%=lom.get("cntrt_resp_dept_nm") %>"><nobr><%=lom.get("cntrt_resp_dept_nm") %></nobr></td>
				      <td><nobr><%=DateUtil.formatDate((String)(lom.get("req_dt")), "-") %></nobr></td>
				      <td class="txtover" title="<%=lom.get("respman_nm_str") %>"><nobr>
<%
				      out.println(lom.get("respman_fnm"));
				      if( ((Long)lom.get("respman_cnt")).longValue() > 1 ) {
				    	  out.println(" 외 " + lom.get("respman_cnt") + "명");
				      }
%>
					  </nobr></td>
				      <td><nobr><%=DateUtil.formatDate((String)(lom.get("cntrt_cnclsnday")), "-") %></nobr></td>
				      <td title="<%=lom.get("prcs_depth_nm") %>"><nobr><%=lom.get("prcs_depth_nm") %></nobr></td>
				     <%if(!"C02506".equals((String)lom.get("prcs_depth")) && !"C02507".equals((String)lom.get("prcs_depth"))) { %>
				      <td title="<%=lom.get("depth_status_nm") %>"><nobr><%=lom.get("depth_status_nm") %></nobr></td>
				     <%} else { %>
				      <td title="<spring:message code="las.page.field.contractManager.trans"/>"><spring:message code="las.page.field.contractManager.trans"/></td>
				     <%} %>
			        </tr>
		      <%	}
				}else{		  
 			  %>
					<tr class="end">
					  <td colspan="9"><spring:message code="secfw.msg.succ.noResult" /></td>
					</tr>
			  <%}%>			
			  </tbody>
			</table>
			<!-- //list -->

			<div class="t_titBtn">	
				<!-- total data -->
				<div class="total_num">
					<spring:message code="las.page.field.contractManager.totalCont"/>  : <%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%><spring:message code="las.page.field.contractManager.case"/>
				</div>
				<!-- //total data -->
				
				<!-- pagination  -->
				<div class="pagination">
				<%=pageUtil.getPageNavi(pageUtil, "") %>
				</div>
				<!-- //pagination -->
			</div>
		</div>
		<!-- //content -->
		
		<!-- footer -->
		    <!-- button -->
	<div class="btn_wrap fR">
		<span class="btn_all_w">
		    <span class="save">
		    </span>
		        <a href="javascript:con()"><spring:message code='clm.page.button.confirm' /></a>
		    </span>
	        <span class="btn_all_w">
	            <span class="save">
	            </span>
	                <a href="javascript:cancle()"><spring:message code='clm.page.button.cancel' /></a>
		</span>
	</div>
	<!-- //button -->
		<!-- //footer -->
	</div>
	<!-- //container -->
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</div>
</form:form>
</body>
</html>

