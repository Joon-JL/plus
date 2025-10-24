<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.PageUtil" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="java.util.List" %>
<%--
/**
 * 파  일  명 : excelUpload.jsp
 * 프로그램명 : 엑셀 업로드 팝업
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");

	List calList = (List)request.getAttribute("calList");
	session.setAttribute("calList",calList);
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html>
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title" /></title>
<link href="<%=CSS%>/las.css"    type="text/css" rel="stylesheet" />
<link href="<%=CSS%>/popup.css"  type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript">

$(function(){
		
	//file_path 입력칸 클릭 못하게 disabled
	$('#file_path').attr("disabled",true);
	
	
	//button event
	$('a').click(function(){
		
		//샘플파일 다운로드
		if(this.id == 'btn_down'){
			
		}
			
		//선택한 엑셀파일에서 내용 읽어서 화면에 출력
		else if(this.id == 'btn_add'){
			
			//파일선택 안했을 시 예외처리
			if($("#vendorTypes").val()==''){
				alert("<spring:message code='las.msg.alert.vendorType'/>");
				return;
			}
			
			$("#vendorType").val($("#vendorTypes").val());
			
			var filePath = $('#file_path').val();
			if( !(filePath.substr(filePath.length-3) == 'xls' || filePath.substr(filePath.length-4) == 'xlsx')){
				alert("<common.msg.error.nofileExt'/>");
				return;
			}
			//ajax로 선택한 엑셀의 컨텐츠를 가져온다.
			addExcelContent();
			
		}

		//파일찾기
		//else if(this.id == 'btn_search'){
			//$('#filename').click();
			//$('#filename').select();
			//로컬 경로가 ../fakePath/..로 나오는 것을 절대경로를 얻기 위함
			//$('#file_path').val(document.selection.createRangeCollection()[0].text.toString());
			
			//XLS, XLSX 확장자 체크
			//checkFile($('#file_path').val());
			//uploadExcelfile();
			
			//requestToServer('_self','<c:url value='/las/lawfirm/lawfirmCounselinkUpload.do' />','printContentFormExcel');
		//}
			
		//체크박스 선택된 행 삭제
		else if(this.id == 'btn_delRow'){
			if($("input[name=chkbox]").length == 0){
				alert('삭제할 대상이 없습니다');
				return;
			}
			else{
				if($("input[name=chkbox]:checked").length > 0){
					var confirmMessage = "<spring:message code='secfw.msg.ask.delete' />";
					if (confirm(confirmMessage)){
						$("input[name=chkbox]").each(function(){
							if($(this).is(':checked')){
								//delYn 갱신
								$(this).parents('tr').next().find('input[name=delYn]').val('Y');
								$(this).parents('tr').remove();
							}
						});
					}	
				}
				else{
					alert('삭제할 대상을 선택해 주세요');
					return;
				}
			}
		}
		//행 추가
		//else if(this.id == 'btn_addRow'){
			//$('#content_table').append($('#add_row_content').val());
		//}
			
		//저장
		else if(this.id == 'btn_save'){
			if($("input[name=chkbox]").length == 0){
				alert('저장할 대상이 없습니다');
				return;
			}
			
			var confirmMessage = "<spring:message code='las.jsp.msg.ask.insert' />";
			
			
			
			if (confirm(confirmMessage)){
				if($("#vendorTypes").val()=="V"){
					requestToServer('_self','/clm/draft/excelUpload.do','saveExcelContent');
				}else if($("#vendorTypes").val()=="C"){
					requestToServer('_self','/clm/draft/excelUpload.do','saveExcelContentC');
				}
				
			}
		}
	});
	
	$('[name=all_chkbox]').click(function(){
		if($(this).is(':checked')){
			$('[name=chkbox]').attr("checked",true);
		}
		else{
			$('[name=chkbox]').attr("checked",false);
		}
	});
	
	
});

//AJAX로 선택한 EXCEL의 컨텐츠를 가져와서 화면에 뿌려준다
function addExcelContent(){
	requestToServer('_self','/clm/draft/excelUpload.do', 'saveExcelFile');
	
// 	if($("#vendorType").val()=="V"){
// 		requestToServer('_self','/clm/draft/excelUpload.do', 'saveExcelFile');
// 	}else if($("#vendorType").val()=="C"){
// 		requestToServer('_self','/clm/draft/excelUpload.do', 'saveExcelFile');
// 	}
	
	/*
	var options = {
				url: "<c:url value='/las/lawfirm/lawfirmCounselinkUpload.do?method=addExcelContent'/>",
				type: "post",
				async: false,
				dataType: "html",
				data : 
				({
					file_path : $('#file_path').val()
				}),
				//contentType : 'application/x-www-form-urlencoded;charset=UTF-8',
				success: function (data) {
					$('#ajax_content').html("");
					$('#ajax_content').append(data);
				}
			};
  	$("#frm").ajaxSubmit(options);
  	*/
}	

//요청 전송
function requestToServer(_target, _action, _method){
	viewHiddenProgress(true) ;
	document.frm.target = _target;
	document.frm.action = _action;
	document.frm.method.value = _method;
	document.frm.submit();		
}	
	
//메세지 처리
function showMessage(msg) {
	if( msg != "" && msg != null && msg.length > 1 ) {
		alert(msg);
	}
}

function cfSetFile(param)
{
    var frm = document.frm;
    var fileName = frm.filename.value;

    $('#filename').select();
    //로컬 경로가 ../fakePath/..로 나오는 것을 절대경로를 얻기 위함
    $('#file_path').val(document.selection.createRangeCollection()[0].text.toString());

}

function checkFileType( filePath ) {
    var fileFormat = filePath.toLowerCase();

    if( fileFormat.indexOf(".xls") > -1 ) return true;
    else if( fileFormat.indexOf(".xlsx") > -1 ) return true;
    else return false;
}    
</script>
</head>
<body class="pop" onload='showMessage("<c:out value='${returnMessage}'/>");'>
<!-- header -->
<h1><spring:message code="las.page.button.excelUpload"/><span class="close" onclick="javascript:self.close();" title="close"></span></h1>
<!-- //header -->
<div class="pop_area">
	<!-- content -->
	<div class="pop_content"  style='height:'>
	
	<!-- **************************** Form Setting **************************** -->
	<form:form name="frm" id='frm' method="post" autocomplete="off" enctype="multipart/form-data"> 

	<!-- search form -->
	<input type="hidden" name="method"   value="" />
	<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
	<!-- key form-->
	<input type="hidden" name="vendorType" id="vendorType" value="" />
	<!-- 첨부파일정보 -->
	<!-- // **************************** Form Setting **************************** -->
	
	<style>
		.btn_filesearch {position:absolute; top:4px;left:223px; z-index:1}
		.btn_fileadd {position:absolute; top:4px;left:305px; z-index:1}
		.input_opa {height:19px;display:inline-block;width:300px; z-index:200;position:absolute; top:4px;left:0px; }
		.input_view {height:19px;display:inline-block;width:300px; z-index:100;position:absolute; top:4px;left:0px; }
	</style>
	
	<table class='list_basic'>	
		<colgroup>
			<col width="25%" />
			<col width="*" />
		</colgroup>
		<tr>
			<th>
				<select id="vendorTypes">
					<option value=""><spring:message code="las.msg.alert.consideration.respmanSelect" /></option>
					<option value="V"><spring:message code="las.page.field.vendor" /></option>
					<option value="C"><spring:message code="las.page.field.customer" /></option>
				</select>
			</th>
			<td>
				<div style='position:relative;top:-3px; left:0;overflow:hidden; height:24px'>
					<span class='input_opa'>
						<input type="file" name="filename" id="filename" style='filter:alpha(opacity:0);width:300px; height:19px; padding:0; text-align:left; cursor:pointer;' onchange="cfSetFile(this);" />
					</span>
					<span class=' input_view'>
						<input type="text" style='width:215px; border:1px solid #e1e1e1' name="file_path" id = "file_path" readonly="readonly"/>
					</span>
					
					
					<span class='btn_filesearch'>
						<span class="btn_all_b"><span class="search"></span><a><spring:message code="las.page.field.contractManager.searchFile" /></a></span>
					</span>		
					<span class='btn_fileadd'>
						<span class="btn_all_b"><span class="add"></span><a id="btn_add" href="#"><spring:message code="las.page.button.upload" /></a></span>
					</span>
				</div>	
			</td>
		</tr>
	</table>
		</form:form>
	</div>
</div>

	<!--footer -->
	<div class="pop_footer">
		<div class="btn_wrap fR" >
			<span class="btn_all_w mR5"><span class="cancel"></span><a href="#" onclick="javascript:self.close();"><spring:message code="clm.page.msg.common.close" /></a></span>
		</div>
	</div>
	<!-- //footer -->
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>

</body>
</html>