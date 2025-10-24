<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.manage.dto.ConsiderationForm" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파  일  명 : ChooseClient_p.jsp
 * 프로그램명 : 관련자 선택 팝업
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2013.05
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
	List resultList = (List)request.getAttribute("resultList");
	
%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title" /></title>
<link href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet" />
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript">
<!--
//팝업오픈-모달리스
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
	
	//var Feture = "fullscreen=no,toolbar=0,location=0,directories=no,status=yes,menubar=no,scrollbars=" + (bScroll ? "yes" : "no") + ",resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;
	var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars=no, resizable=no, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
	PopUpWindow = window.open(surl, popName , Feture);
	//PopUpWindow.resizeTo(parseInt(popupwidth)+10, parseInt(popupheight)+29);
	PopUpWindow.focus();
	
}
//의뢰인추가
function searchEmployee() {
    var frm = document.frm;
    var srchValue = comTrim(frm.srch_user_cntnt.value); //입력받은 값
	
    if (srchValue == "" || getByteLength(srchValue) < 4) { //최소 2자리 이상 입력받게 한다.
        alert('<spring:message code="secfw.msg.error.nameMinByte" />');
        frm.srch_user_cntnt.focus();
        return;
    }

   /* PopUpWindowOpen2('', 800, 450);*/
    
    PopUpWindowOpen('', "800", "450", true, "popUpChooseClient");
    //frm.srch_user_cntnt_type.value = "userName"; //이름으로 검색  - 보통 이름으로 검색함
    frm.action = "<c:url value='/secfw/esbOrg.do' />";
    frm.method.value = "listEsbEmployee";
    frm.target = "popUpChooseClient";
    frm.submit();
    
    nmCle();
    
}

/**
 * 명칭 초기화 하기
 */
function nmCle(){
	
	var frm = document.frm;
	
	frm.srch_user_cntnt.value = "";
	
}

/**
* 임직원 조회정보 결과 setting
*/
function setUserInfos(obj) {
    var userInfo = obj;
 	if(userInfo.epid=="") {
    	return;
    }
    
    addClient(obj);
}


//주요이행사항-행추가버튼클릭
function addClient(obj) {
	var tr0cnt = $('#client-tr0').length;
	if(tr0cnt==1) {
		$('#client-tr0').remove();
	}
	
	
	var trcnt = $('#client_tbody tr').length;
	id = trcnt+1;
	 
	var isExist = false;
	$('input[name*=trgtman_id]').each(function(){
		if($(this).val() == obj.epid) {
			alert("<spring:message code="clm.page.msg.manage.announce132" />");
			isExist = true;
			return;
		}
	});
	
	if(isExist) return;
	var html = "<tr id=\"client-tr" + id + "\">"
  			 //+ "<th class=\"tC\">" + id + "</th>"
  	 		 + "<td class=\"tC\">"
  	 		 + "    <input type=\"hidden\" name=\"demnd_seqno\" value=\"" + id + "\"/>"
	 		 + "    <input type=\"hidden\" name=\"trgtman_id\" value=\"" + obj.epid +"\"/>"
	 		 + "    <input type=\"hidden\" name=\"trgtman_nm\" value=\"" + obj.cn + "\"/>"
	 		 + "    <input type=\"hidden\" name=\"trgtman_title\" value=\"" + obj.title + "\"/>"
	 		 + "    <input type=\"hidden\" name=\"trgtman_department\" value=\"" + obj.department + "\"/>"
      		 + obj.cn  
      		 + "</td>"
      		 + "<td class=\"tC\">"
      		 + obj.title
      		 + "</td>"
      		 + "<td class=\"tC\">"
      		 + obj.department 
      		 + "</td>"
      		 + "<td class=\"tC\">"
      		 + "<span class=\"btn_all_b\"><span class=\"delete\"></span><a href=\"javascript:deleteItem(" + id + ");\"><spring:message code='las.page.button.delete' /></a></span>"
      		 +"</td>"
      	     + "</tr>";
     	     
	$('#client_tbody').append(html);
}

//삭제
function deleteItem(id) {
	$('#client-tr'+id).remove();
	var trcnt = $('#client_tbody tr').length;
	if(trcnt==0) {
		$('#client_tbody').append("<tr id=\"client-tr0\"><td class=\"tC\" colspan='4'></td></tr>");
	}
}


function save() {
	var frm	= document.frm;
	var tr0cnt = $('#client-tr0').length;
	/*
	if(tr0cnt==1) {
		alert("<spring:message code="clm.page.msg.manage.announce196" />");
		return;
	} */
	
	var returnValue = "";
	$('#client_tbody tr').each(function(idx){
		//$(this)
		var seqno = $(this).contents().find('input[name=demnd_seqno]').val();
		var trgtman_id = $(this).contents().find('input[name=trgtman_id]').val();
		var	trgtman_nm = $(this).contents().find('input[name=trgtman_nm]').val();
		var	trgtman_title = $(this).contents().find('input[name=trgtman_title]').val(); 
		var	trgtman_department = $(this).contents().find('input[name=trgtman_department]').val();
		    if(seqno!=null){
				if(idx > 0) {
					returnValue = returnValue + "!@#$" + seqno + "|" + trgtman_id + "|" + trgtman_nm +"|"+ trgtman_title + "|" + trgtman_department;		
				} else {
					returnValue = seqno + "|" + trgtman_id + "|" + trgtman_nm + "|"+ trgtman_title +"|" + trgtman_department;
				}
		    } else {
		    	returnValue += "" ;
		    }
	
	});
	
	opener.setListClientInfo(returnValue);
	self.close();
}

function initUserInfo() {
	var mainClient = "<c:out value='${command.chose_client}'/>";
	var trcnt = $('#client_tbody tr').length;
	
	var arrInfo = mainClient.split("!@#$");
		if(arrInfo[0]!="") {
			if($('#client-tr0').length > 0) {$('#client-tr0').remove();}
			
			var arrSeqno 	= arrInfo[0].split(",");
			var arrEpid	 	= arrInfo[1].split(",");
			var arrNm	 	= arrInfo[2].split(",");
			var arrJikgup	= arrInfo[3].split(",");
			var arrDept	 	= arrInfo[4].split(",");
			var html		= "";
			var id 			= 1;
			
			for(var i=0; i < arrEpid.length;i++) {
				id=i+1;
				html =  html + "<tr id=\"client-tr" + id + "\">"
					 //+ "<th class=\"tC\">" + id + "</th>"
			 		 + "<td class=\"tC\">"
			 		 + "  <input type=\"hidden\" name=\"demnd_seqno\" value=\"" + id + "\"/>"
			 		 + "  <input type=\"hidden\" name=\"trgtman_id\" value=\"" + arrEpid[i] +"\"/>"
			 		 + "  <input type=\"hidden\" name=\"trgtman_nm\" value=\"" + arrNm[i] + "\"/>"
			 		 + "  <input type=\"hidden\" name=\"trgtman_title\" value=\"" + arrJikgup[i] + "\"/>"
			 		 + "  <input type=\"hidden\" name=\"trgtman_department\" value=\"" + arrDept[i] + "\"/>"
		 		 	 //+ "  <span class=\"\">" + arrNm[i] + "/" + arrJikgup[i] + "/" + arrDept[i] + "</span><span class=\"btn_all_w\"><span class=\"delete\"></span><a href=\"javascript:deleteItem(" + id + ");\">삭제</a></span></td>"
		 		 	 + arrNm[i]  
		      		 + "</td>"
		      		 + "<td class=\"tC\">"
		      		 + arrJikgup[i]
		      		 + "</td>"
		      		 + "<td class=\"tC\">"
		      		 + arrDept[i]
		      		 + "</td>"
		      		 + "<td class=\"tC\">"
		      		 + "<span class=\"btn_all_b\"><span class=\"delete\"></span><a href=\"javascript:deleteItem(" + id + ");\"><spring:message code='las.page.button.delete' /></a></span>"
		 		 	 + "</tr>";
		 	     
			}
			$('#client_tbody').append(html);
		}
}

/**
 * Description : 파이어폭스 keydown 이벤트 처리
 */
function Keycode(e){
	var result;
	if(window.event)
		result = window.event.keyCode;
	else if(e)
		result = e.which;	
	return result;
}	

//-->
</script>
</head>
<body class="pop" onload="javascript:initUserInfo();">

<h1><spring:message code="clm.page.msg.manage.relPersonManage" /><span class="close" onclick="javascript:self.close();" title="close"></span></h1>
<div class="pop_area">

		<div class="pop_content">
		
		<form:form name="frm" id='frm' method="post" autocomplete="off"> 
	<!-- search form -->
	<input type="hidden" name="method"   value="" />
	<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
		
			<div class="title_basic mt0">
				<h4><spring:message code="clm.page.msg.manage.relPersonReg" /></h4> 			   
			</div>
			
			<!-- search-->	
			<div class="search_box">
					<div class="search_box_inner">
						<table class="search_tb">
							<tr>
								<td>
								
									<table class="search_form">
										<colgroup>
											<col width="15%" />
											<col width="85%" />
									  	</colgroup>
										<tr>
<%-- 											<th><spring:message code="clm.page.msg.common.name" /></th> --%>
											<th>
							              		<select name="srch_user_cntnt_type" class="select" >
													<option value="userName"><spring:message code="secfw.page.field.user.user_nm" /></option>
													<option value="userId">SingleID</option>
												</select>
 											</th>
											
											<td>
												<span class='fL' style='margin:1px 4px 0 0'><input type="text" name="srch_user_cntnt" class="text" onKeyPress="if(Keycode(event)==13){ searchEmployee(); return false; }" style="width:100px;"/></span>
											    <span class='fL'><span class="btn_all_b"><span class="add"></span><a href="javascript:searchEmployee();"><spring:message code="clm.page.msg.manage.add" /></a></span></span>
											</td>
										</tr>
									
									</table>
									
								</td>
							</tr>
						</table>
					</div>
	            </div>     
				<!-- search-->	
			
		
			
			<table class="table-style01 mt20">
				<colgroup>
					<col width="20%" />
					<col width="25%" />
					<col width="35%" /> <!-- width size 변경 신성우 2014-04-24 -->
					<col width="20%" />
			  	</colgroup>
			  	<thead>
			  		<th><spring:message code='clm.page.msg.manage.name' /></th><!-- 성명 -->
			  		<th><spring:message code='las.page.field.mainproject.operdiv_respman_jikgup' /></th><!-- 직급 -->
			  		<th><spring:message code='las.page.field.mainproject.operdiv_respman_dept_nm' /></th><!-- 부서 -->
			  		<th><spring:message code='las.page.button.delete' /></th><!-- 삭제 -->
			  	</thead>
			 	<tbody id="client_tbody">
			 	<tr id="client-tr0">
			 		<td class="tC" colspan='4'>&nbsp;
			 		</td>
			 	</tr>
				</tbody>
			</table>
			</form:form>
		</div>
	</div>
<!-- //body -->
<!--footer -->
	<div class="pop_footer">
		<!-- button -->
	 	<div class="btn_wrap tR">
	    	<span class="btn_all_w"><span class="check"></span><a href="javascript:save();"><spring:message code="clm.page.msg.common.confirm" /></a></span>
	     	<span class="btn_all_w"><span class="cancel"></span><a href="#" onclick="javascript:self.close();"><spring:message code="clm.page.msg.common.close" /></a></span>
	 	</div>
	<!-- //button -->
	<!-- //footer -->
	</div>	
	
</body>
</html>
