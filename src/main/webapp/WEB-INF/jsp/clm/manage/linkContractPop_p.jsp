<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.manage.dto.ManageForm" %>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%-- 
/**
 * 파  일  명 : linkContractPop_p.jsp
 * 프로그램명 : 연계 시스템에서 호출 되는 의뢰 중심 화면
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2012.01.09
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
	PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil");
	List resultList = (List)request.getAttribute("resultList");
	ManageForm command = (ManageForm)request.getAttribute("command");
%>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>

<!-- style -->
<LINK href="<%=CSS%>/popup.css"  type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/clm.css"    type="text/css" rel="stylesheet">

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
		getCodeSelectByUpCd("frm", "srch_step", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=F&combo_grp_cd=C025&combo_type=T&combo_del_yn=N&combo_selected='+'<c:out value='${command.srch_step}'/>');
		getCodeSelectByUpCd("frm", "srch_state", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=F&combo_grp_cd=' + '<c:out value='${command.srch_step}'/>' +'&combo_type=T&combo_del_yn=N&combo_selected='+'<c:out value='${command.srch_state}'/>');
	 	
	 	initCal("srch_start_reqday");   //첫번재 체결일 설정
	    initCal("srch_end_reqday");     //두번재 체결일 설정

	});
		
	$(function() {
		$("#srch_step").change(function() {
			var grpCd = $("#srch_step").val();
			getCodeSelectByUpCd("frm", "srch_state", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=CLM&combo_abbr=F&combo_grp_cd=' + grpCd +'&combo_type=T&combo_del_yn=N&combo_selected=');
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
	function showMessage(msg,status) {
		if("req" != status){
			if( msg != "" && msg != null && msg.length > 1 ) {
				alert(msg);
			}
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
	
		var startDt = frm.srch_start_reqday.value;
		var endDt = frm.srch_end_reqday.value;
	
		if((startDt != "" && endDt != "") && (startDt > endDt)){
	  		alert("<spring:message code='clm.msg.alert.board.srchAnnceDt' />");
	  		return;
	  	}
		
		viewHiddenProgress(true) ;
		frm.target = "_self";
		frm.action = "<c:url value='/clm/manage/contract.do' />";
	    frm.method.value = "linkContractPop";
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
		frm.action = "<c:url value='/clm/manage/contract.do' />";
		frm.method.value = "linkContractPop";
		frm.curPage.value = pgNum;
		
		frm.submit();
	}
	
	/**
	*	계약상대방 팝업
	*/
	function popOppnt(){
		var frm = document.frm;
		 
		PopUpWindowOpen('', 900, 600, true);
		frm.target = "PopUpWindow";
		frm.action = "/clm/draft/customerTest.do";
		frm.method.value = "listCustomerTest";
		frm.srch_customer.value = frm.srch_oppnt_nm.value;
		frm.customer_gb.value   = "O";
		frm.submit();
	}
	
	/**
	* 거래상대방 객체 리턴
	*/
	function returnCustomer(obj){
		$('#srch_oppnt_cd').val(obj.customer_cd);
		$('#srch_oppnt_nm').val(obj.customer_nm1);
	}	
	/**
	*	담당부서 팝업
	*/
	function popDept(){
		 var frm = document.frm;
		 
		 PopUpWindowOpen('', 850, 440, true);
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
	
	function setInfo(cntId, cntNm, tyNm1, tyId1, tyNm2, tyId2, tyNm3, tyId3, tyNm4, tyId4, autoYn){
		
	    var setInfo = new Object();
	    var frm = document.frm;
	    
	    var rURL = frm.sReturnUrl.value;
	    var strParam =  cntId +"^"+ cntNm +"^"+ tyNm1+"^"+ tyId1+"^"+ tyNm2+"^"+ tyId2+"^"+ tyNm3+"^"+ tyId3+"^"+ tyNm4+"^"+ tyId4+"^"+ autoYn;
	    
	    var sumURL = rURL+"?strParam="+strParam+"&id="+cntId+"&name="+cntNm;
	    
	    //alert(cntNm +" , "+ cntId +" , "+ tyNm1+" , "+ tyId1+" , "+ tyNm2+" , "+ tyId2+" , "+ tyNm3+" , "+ tyId3+" , "+ tyNm4+" , "+ tyId4+" , "+ autoYn);
	    //alert("rURL : strParam ==="+ rURL+"?"+strParam);
	    //alert("최종 리턴 값 : sumURL ==="+ sumURL);
	   
	   //window.open(sumURL,"self");
	   
	   location.href = sumURL;
	   
	   //window.close();
	    
	    
	}   

</script>
</head>

<body  class="pop" onLoad='showMessage("<c:out value='${returnMessage}'/>","<c:out value='${command.submit_status}'/>");'> 

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
	<input type="hidden" name="srch_customer" value=""/>
				
	<!-- Detail Page Param -->
	<input type="hidden" name="cnsdreq_id" value="" />
	<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>">
	
	<input type="hidden" name="forward_url" value="" />
	
	<input type="hidden" name="sReturnUrl" id="sReturnUrl" value="<c:out value='${command.sReturnUrl}'/>">
	
<!-- // **************************** Form Setting **************************** -->
<div id="cntrtLayer" style="display:none;">
</div>
<!-- header -->
<h1><spring:message code="clm.page.title.manageContractPop.listTitle" /></h1>
<!-- //header -->
<div class="pop_area">
	<!-- Popup Size 840*600 -->
	<div class="h_600">
		<div class="pop_content mt10">
		<!-- content -->
		<div id="content">
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
							<!-- 의뢰 기준 조회일 경우 -->
								<table class="search_form">
									<colgroup>
										<col width="10%"/>
										<col width="25%"/>
										<col width="10%"/>
										<col width="10%"/>
										<col width="10%"/>
										<col width="35%"/>
									</colgroup>
									<tr>
										<th><spring:message code="clm.page.field.manageCommon.srchReqTitle" /></th>
										<td>
											<input class="srch" type="text" name="srch_review_title"  id="srch_review_title" value="<c:out value='${command.srch_review_title}'/>" style="width:100%"/>
										</td>
										<th><spring:message code="clm.page.field.manageCommon.srchReqmanNm" /></th>
										<td>
											<input class="srch" type="text" name="srch_reqman_nm" id="srch_reqman_nm" value="<c:out value='${command.srch_reqman_nm}'/>" style="width:80px" />
										</td>
										<th><spring:message code="clm.page.field.manageCommon.srchReqDay" /></th>
										<td>
											<input type="text" name="srch_start_reqday" id="srch_start_reqday" value="<c:out value='${command.srch_start_reqday}'/>"  class="text_calendar02 srch"/>
											~
	  										<input type="text" name="srch_end_reqday" id="srch_end_reqday" value="<c:out value='${command.srch_end_reqday}'/>" class="text_calendar02 srch"/>
										</td>
									</tr>
									<tr>
										<th><spring:message code="clm.page.field.manageCommon.srchRespDept" /></th>
										<td>
											<input type="text" name="srch_resp_dept_nm" id="srch_resp_dept_nm" value="<c:out value='${command.srch_resp_dept_nm}'/>" style="width:130px;" class="text_search srch" onKeypress="if(event.keyCode == 13){popDept();return false;}"/><a href="javascript:popDept();"><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" />
										</td>
										<th><spring:message code="clm.page.field.manageCommon.srchRespmanNm" /></th>
										<td>
											<input class="srch" type="text" name="srch_respman_nm" id="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}'/>" style="width:80px" />
										</td>
										<th><spring:message code="clm.page.field.manageCommon.srchCnsdmanNm" /></th>
										<td>
											<input type="text" name="srch_cnsdman_nm" id="srch_cnsdman_nm" value="<c:out value='${command.srch_cnsdman_nm}'/>" style="width:80px" />
										</td>
									</tr>
									<tr>
										<th><spring:message code="clm.page.field.manageCommon.srchOppntCd" /></th>
										<td>
											<input type="text" name="srch_oppnt_nm" id="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}'/>" style="width:100px" class="text_search srch" onKeypress="if(event.keyCode == 13){popOppnt();return false;}"/><a href="javascript:popOppnt();"><img src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" /></a>
										</td>										
									</tr>
								</table>
							
							<!-- 계약 기준 조회일 경우 -->
							</td>
							<td class="vb tC">
								<a href="javascript:searchAction('');"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="clm.page.msg.common.inquire" htmlEscape="true" />"/></a>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<!--//search-->
			<!-- list -->
			<!-- 의뢰기준일 경우 -->
			<table border="0" cellspacing="0" cellpadding="0" class="list_basic">
				<colgroup>
					<col width="30%" />
					<col width="30%" />
					<col width="10%" />
					<col width="10%" />
					<col width="10%" />
					<col width="10%" />
			  	</colgroup>
			  	<thead>
					<tr>
						<th><nobr><spring:message code="clm.page.field.manageCommon.reqTitle" /></nobr></th>
						<th><nobr><spring:message code="clm.page.msg.manage.contName" /></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.reqmanNm" /></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.reqDay" /></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.respmanNm" /></nobr></th>
						<th><nobr><spring:message code="clm.page.field.manageCommon.cnsdmanNm" /></nobr></th>
					</tr>
				</thead>
			 	<tbody>
			  <%
				if(pageUtil.getTotalRow() > 0) {
					for(int idx=0; idx<resultList.size(); idx++) {
						ListOrderedMap lom = (ListOrderedMap)resultList.get(idx);
				%>			
				    <tr  <%if(idx == resultList.size()-1){%>class="end"<%}%> onMouseOut="this.className='<%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onMouseOver="this.className='on <%if(idx == resultList.size()-1){%>end<%}%>';this.style.cursor='pointer'" onClick="javascript:setInfo('<%=lom.get("cntrt_id") %>', '<%=lom.get("cntrt_nm")%>', '<%=lom.get("biz_clsfcn")%>', '<%=lom.get("t1_nm")%>', '<%=lom.get("depth_clsfcn")%>', '<%=lom.get("t2_nm")%>', '<%=lom.get("cnclsnpurps_midclsfcn")%>', '<%=lom.get("t3_nm")%>', '<%=lom.get("cntrt_trgt")%>', '<%=lom.get("t4_nm")%>', '<%=lom.get("auto_rnew_yn")%>');">
				      <td class="tL txtover" title="<%=StringUtil.convertHtmlTochars((String)lom.get("req_title")) %>"><nobr><%=StringUtil.convertHtmlTochars((String)lom.get("req_title")) %></nobr></td>
				      <td class="tL txtover" title="<%=lom.get("cntrt_nm") %>"><nobr><%=lom.get("cntrt_nm") %></nobr></td>
				      <td title="<%=lom.get("reqman_nm") %>"><nobr><%=lom.get("reqman_nm") %></nobr></td>
				      <td><nobr><%=DateUtil.formatDate((String)(lom.get("req_dt")), "-") %></nobr></td>
				      <td title="<%=lom.get("cntrt_respman_nm") %>"><nobr><%=lom.get("cntrt_respman_nm") %></nobr></td>
				      <td title="<%=lom.get("cnsdmans") %>"><nobr><%=lom.get("cnsdman") %></nobr></td>
			        </tr>
		      <%	}
				}else{		  
 			  %>
					<tr class="end">
					  <td colspan="6"><spring:message code="secfw.msg.succ.noResult" /></td>
					</tr>
			  <%}%>			
			  </tbody>
			</table>
			
			<div class="t_titBtn">	
				<!-- total data -->
				<div class="total_num">
				<%//if("cnsdreq".equals(command.getList_mode())) {%>
				<!--	총의뢰 -->
				<%//} else { %>
				<!--	총계약-->
				<%//} %>
				<!-- : -->
					<%=StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow()))%><spring:message code="clm.page.msg.common.case" />
				</div>
				<!-- //total data -->
				
				<!-- pagination  -->
				<div class="pagination">
				<%=pageUtil.getPageNavi(pageUtil, "") %>
				</div>
				<!-- //pagination -->
			</div>

		<!-- //content -->
		
		</div>
	</div>
</div>
<input type="text" name="srch_review_title2" style="width:0px; height: 0px;" />
<!-- //body -->
<!--footer -->
<div class="pop_footer">

</div>
</form:form>
</body>
</html>

