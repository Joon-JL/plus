<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>

<%@ page import="java.util.List" %>

<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%--
/**
 * 파  일  명 : ChooseContract_p.jsp
 * 프로그램명 : 계약선택팝업
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%
	List resultList = (List)request.getAttribute("resultList");
	int iCount = resultList.size();
%>	
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title"/></title>
<link href="<%=CSS%>/popup.css"   type="text/css" rel="stylesheet" />
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/CommonProgress.js' />"></script>

<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript">
$(document).ready(function(){
	if(opener!=null) {
		if(opener.initAttach()) {
			  opener.initAttach() ;
		}
		
		if(opener.document.frm.fileInfos) {
			opener.document.frm.fileInfos.value = "" ;
		}
		
		if(opener.document.frm.fileInfos2) {
            opener.document.frm.fileInfos2.value = "" ;
        }
		
		if(opener.document.frm.fileInfos3) {
            opener.document.frm.fileInfos3.value = "" ;
        }
		
		if(opener.document.frm.fileInfos4) {
            opener.document.frm.fileInfos4.value = "" ;
        }
		
		if(opener.document.frm.fileInfos5) {
            opener.document.frm.fileInfos5.value = "" ;
        }
	}
	
	
	$('input[name*=drop_yn]').each(function(idx){
		if($(this).val() == "Y") {
			$(this).next().attr("disabled", "disabled");		
		} else {
			$(this).next().removeAttr("disabled");
		}
	});	
	
	if(<%=iCount%>==1) {
		selectAll();
		save();
		
	} else {
		$('#master_choosecontract').attr("style", "display:block");
		
	}
});

function save(){
	var frm = document.frm;
	var sel_contract_cnt = $('#contract_tbody tr :checked').length;
	//var all_contract_cnt = $('#contract_tbody tr').length;
	//var obj	= null;
	
	if(sel_contract_cnt == 0) {
		alert("<spring:message code='clm.msg.alert.choosecontract.noSelect' />");
		return;
	}
	
	/*if(sel_contract_cnt == all_contract_cnt) {
		alert("<spring:message code='clm.msg.alert.choosecontract.noSelect' />");
		return;
	}*/
	
	
	if(frm.worktype.value=="CONSULTATION") {	//체결품의
		if(<%=iCount%>>1) {
			if(!confirm("<spring:message code='clm.msg.confirm.choosecontract.drop'/>")) return;
		}	
		
		$('input[name*=approval_yn_arr]').val("N");	
		$('#contract_tbody tr :checked').each(function(idx){
			$(this).next().next().val("Y");
		});
		validationApproval(); 
			
		/*frm.action = "<c:url value='/clm/manage/chooseContract.do' />";
		frm.target = "_self";	
		frm.method.value="modifyChooseContract";*/
		
	} else {									//계약의뢰,체결본의뢰
		var arrContract = new Array(sel_contract_cnt);
		$('#contract_tbody tr :checked').each(function(idx){
			arrContract[idx] = $(this).val();
		});
		opener.setSelectedContractInfo(arrContract);
		self.close();
	}
}

function cancel(){
	var frm = document.frm;
	if(frm.worktype.value=="CONSULTATION") {
		if(typeof(opener.checkNew)!="undefined" && opener.checkNew()) {
			if(frm.method.value != "makeApprovalHtmlNew") {
                opener.setProgressApprovalInfo("N");
                self.close();
            }
			
		} else {
			if(frm.method.value != "makeApprovalHtml") {
				opener.setProgressApprovalInfo("N");
				self.close();
			}
		}
	} else {
		self.close();
	}
	
}

//체결품의 클릭시 계약유효성체크
function validationApproval() {
	var frm = document.frm;
	
	frm.method.value = "listApprovalValidation";
	//var returnString = "";
	var dropSize = 0;
	var result = true;
	var options = {
		url: "<c:url value='/clm/manage/chooseContract.do' />",
		type: "post",
		dataType: "json",
		success: function(returnJson,returnMessage) {
			if(typeof returnJson.errorMessage == "undefined") {
				if(!returnJson.cntrtNm == "") {
					if(dropSize == $('#contract_tbody tr').length) {
						alert("<spring:message code='clm.msg.alert.contract.consultation.dropall'/>");
						result = false;
					}
					
					$('#contract_tbody tr :checked').each(function(idx){
						var arrCntrtId = (returnJson.cntrtId).split(", ");
						for(var i=0; i < arrCntrtId.length;i++) {
							if($(this).next().val() == arrCntrtId[i]) {
								alert(returnJson.cntrtNm + "<spring:message code='clm.msg.alert.choosecontract.require'/>");
								result = false;
								break;
							}
						}	
					});
					
				}
				
				if(returnJson.wsvoCnt != "0"){
					alert("<spring:message code="clm.page.msg.manage.announce131" />");
					result = false;
					window.close();
				}
			} else {
				alert("<spring:message code="clm.page.msg.manage.announce055" />");
				result = false;
			}
			var approvalContract="";
			if(result) {
				$('#contract_tbody tr :checked').each(function(idx){
					if(approvalContract==""){
						approvalContract = $(this).next().val();
					} else {
						approvalContract = approvalContract + "#" + $(this).next().val(); 
					}
				});
				opener.setApprovalContractInfo(approvalContract);
				frm.action    		= "<c:url value='/clm/manage/consultation.do' />";
				frm.target 			= "_self";
				
				//alert(typeof(opener.checkNew)!="undefined" + " : " + opener.checkNew()) ;
				
				if(typeof(opener.checkNew)!="undefined" && opener.checkNew()) {
					frm.method.value   = "makeApprovalHtmlNew";
				} else {
				    frm.method.value	= "makeApprovalHtml";
				}
				
				frm.submit();  
			}
			
		}
	};
	
	$("#frm").ajaxSubmit(options);
	
}

function selectAll() {
	//var frm = document.frm;
	//var contract_cnt = $('#contract_tbody tr').length;
	
	$('#contract_tbody tr').each(function(idx){
		$(this).contents().find('input[name=check_yn_arr]').attr("checked", "checked");
	});
	
}

function selectNone() {
	//var frm = document.frm;
	//var contract_cnt = $('#contract_tbody tr').length;
	
	$('#contract_tbody tr').each(function(idx){
		$(this).contents().find('input[name=check_yn_arr]').attr("checked", "");
	});
	
}

</script>
</head>
<body class="pop" onunload="javascript:cancel();" id="master_choosecontract" style="display:none">
<!-- header -->
<h1><spring:message code="clm.page.title.choosecontract.mainTitle"/><span class="close" onclick="javascript:self.close();" title="close"></span></h1>
<!-- //header -->
<!-- body -->
<div class="pop_area">
	<!-- Popup Detail -->
	<!-- Popup Size 600*600 -->
	<div class="h_300">
	  <div class="pop_content">
		<!-- title -->
		<div class="title_sub">
			<h5><spring:message code="clm.page.title.choosecontract.mainTitle"/></h5> 		
			<div class="btn_wrap_t">
				<span class="btn"><span class="add"></span><a href="javascript:selectAll();"> <spring:message code="clm.page.button.contract.selectall"/></a></span>
				<span class="btn"><span class="delete"></span><a href="javascript:selectNone();"> <spring:message code="clm.page.button.contract.selectnone"/></a></span>
			</div>	   
		</div>
		<form:form name="frm" id='frm' method="post" autocomplete="off">
			<input type="hidden" name="method" id="method" value=""/>
			<input type="hidden" name="menu_id" id="menu_id" value="<c:out value='${command.menu_id}'/>" />
			<input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>"/>
			<input type="hidden" name="worktype" value="<c:out value='${command.worktype}'/>"/>
			<input type="hidden" name="cnsdreq_id" value="<c:out value='${command.cnsdreq_id}'/>"/>
			
		<!-- //title -->
		<table cellspacing="0" cellpadding="0" class="table-style01 table-top">
			<colgroup>
		  	<col width="80" />
		  	<col width="30" />
		  	<col />
		  	<col width="80" />
		  	<col width="240" />
		  	</colgroup>
		  	<tbody id="contract_tbody">
		  	<c:forEach var="list" items="${resultList}"> 
		  	<tr id="contract_tr">
		  	<c:choose>
				<c:when test="${list.rm == 1}">
					<c:choose>
						<c:when test="${list.rowspan == 1}">
				<th><span><spring:message code="clm.page.field.choosecontract.cntrtNm"/></span></th>
						</c:when>
						<c:otherwise>
				<th rowspan="<c:out value='${list.rowspan}'/>"><spring:message code="clm.page.field.choosecontract.cntrtNm" /></th>
						</c:otherwise>	
					</c:choose>
				</c:when>
			</c:choose>	
			<td class="tal_lineL">
				<input type="hidden" name="drop_yn" id="drop_yn" value="<c:out value='${list.drop_yn}'/>"/>
				<input name="check_yn_arr" id="check_yn_arr" type="checkbox" value="Y" />
				<input name="cntrt_id_arr" id="cntrt_id_arr" type="hidden" value="<c:out value='${list.cntrt_id}'/>" />
				<input name="approval_yn_arr" id="approval_yn_arr" type="hidden" value="N"/>
				</td>
			<td><c:out value='${list.cntrt_nm}' /></td>
			<c:choose>
				<c:when test="${list.rm == 1}">
					<c:choose>
						<c:when test="${list.rowspan == 1}">
				<th><span><spring:message code="clm.page.field.choosecontract.cntrtType"/></span></th>
						</c:when>
						<c:otherwise>
				<th rowspan="<c:out value='${list.rowspan}'/>"><spring:message code="clm.page.field.choosecontract.cntrtType" /></th>
						</c:otherwise>	
					</c:choose>
				</c:when>
			</c:choose>
			<td width="200"><c:out value='${list.biz_clsfcn_nm }'/>/<c:out value='${list.depth_clsfcn_nm }'/>/<c:out value='${list.cnclsnpurps_midclsfcn_nm }'/></td>
		  </tr>
		  </c:forEach>
		  </tbody>
		</table>
		</form:form>
	  </div>
	</div>
	<!-- //Popup Detail -->
</div>

<!-- //body -->
<!--footer -->
<div class="pop_footer">
	<!-- button -->
	 <div class="btn_wrap fR">
     <span class="btn_all_b"><span class="save"></span><a href="javascript:save();"><spring:message code="clm.page.msg.common.save" /></a></span>
	 <span class="btn_all_w"><span class="cancel"></span><a href="#" onclick="javascript:cancel();"><spring:message code="clm.page.msg.common.cancel" /></a></span>
	 </div>
<!-- //button -->
</div>
<!-- //footer -->
</body>
</html>
