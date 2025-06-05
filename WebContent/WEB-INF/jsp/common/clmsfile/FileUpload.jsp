<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.common.clmsfile.dto.FileForm" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="java.util.List" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * Subject   : lawyerFileUpload.jsp 
 * Author    : 신남원 
 * Create On : 2011.09
 * fileInfos형식 : fileId * orgFileNm * fileInfo * filePath * fileSize * fileGbn |
 *                아이디         원본파일            파일형식          파일경로         파일사이즈     파일처리구분(delete, add)
 */
--%>
<%
	try {
		//허용된 파일 확장자
		List allowFileList = (List)request.getAttribute("allowFileList");
		//파일 업로드 전체 최대 사이즈 (Default Size : 10MB)
		String maxUploadSize	= "1024000";  
		//파일 설정정보
		FileForm command 	= (FileForm)request.getAttribute("command");
		
/* 		String userAgent = request.getHeader("User-Agent");
		String os = userAgent.substring(userAgent.toLowerCase().indexOf("windows"), userAgent.length() -1);
		String isNT = "_M";	//(os.toLowerCase().indexOf("nt") > 0)?"_U":"_M";		 */
%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>File Page</title>
<LINK href="<c:url value="/script/secfw/jquery/uploadify/uploadify_${langCd}.css"/>" type="text/css" rel="stylesheet">

<script type="text/javascript" src="<c:url value="/script/secfw/jquery/uploadify/jquery-1.6.2.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/secfw/jquery/uploadify/jquery.uploadify.v2.1.4.js"/>"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/uploadify/jquery.form.js' />"></script>
<script type="text/javascript" src="<c:url value="/script/secfw/jquery/uploadify/jquery.async.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/secfw/jquery/uploadify/swfobject.js"/>"></script>

</head>
<BODY bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  OnLoad="">


<form:form name="POGC" id="POGC" method="post" action="/clms/common/clmsfile.do?method=doFileUpload" autocomplete="off">
	<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>">
	<input type="hidden" name="file_info_str" value="" />

	
	
	
		<input type="file" id="file_upload" name="file_upload" />
	
	
</form:form>
<form:form id="frm1" name ="frm1" method="post" autocomplete="off">
	<input type="hidden" name="fileInfos" value="" />
	<input type="hidden" name="fileNm" value="<c:out value='${command.fileNm}'/>" />
	<input type="hidden" name="folderNm" value="<c:out value='${command.folderNm}'/>" />
	<input type="hidden" name="fileType" value="<c:out value='${command.fileType}'/>" />
</form:form>
</BODY>
</HTML>
<SCRIPT language="javascript">
	/** 파일리스트 높이 설정 시작 **/
	var fileHeight = 0;
	fileHeight = parent.document.getElementById('fileList').height - 25;
	/** 파일리스트 높이 설정  끝 **/
	
	var UploadFilesInfo = "";        //upload된 파일의 정보
	var TotalFileInfos = "";
	
	var Uploadify_ID = "#file_upload";
	var UploadCount = 0;		//File Upload Cnt
	var UploadSize  = 0;		//File Upload Total Size
	
	var Func;

	$(document).ready(function() {
		$(Uploadify_ID).uploadify({
	        'uploader'      : '<c:url value="/script/secfw/jquery/uploadify/uploadify.swf"/>',  //파일업로드 이벤트를 가로챌 플래쉬.
	        'script'      	: '<c:url value="/clms/common/clmsfile.do?method=doFileUpload"/>', //비동기 파일 업로드 처리 페이지 url.
	       	'buttonImg'		: '<c:url value="/script/secfw/jquery/uploadify/search_${langCd}.gif"/>', // 파일 선택 버튼 이미지.
	        'cancelImg'   	: '<c:url value="/script/secfw/jquery/uploadify/cancel_new_${langCd}.gif"/>',   // 파일업로드시 취소 버튼 이미지.
	        'multi'       	: false,			// Multi file 여부
//          'queueSizelimit': 10,			// Multi 일 경우 : 최대 첨부파일 수 					
	        'auto'          : false,        //자동 파일 업로드 여부
	        <c:set var="butimg_width" value="63"/><c:if test="${langCd=='en'}"><c:set var="butimg_width" value="87"/></c:if>
	        'width'			: '<c:out value="${butimg_width}"/>',		//buttonImg width
			'height'		: '17',			//buttonImg height
			'simUploadLimit': 10,			// 한번에 전송되는 파일 수
//	        'sizeLimit'		: '<!--%=maxUploadSize%>',		// 각 파일별 업로드 최대 사이즈
	        'wmode'       	: 'transparent', //'transparent',				// 전송 상태
	        'method'      	: 'post',		// Form send method Post
	        'scriptData'      	: {'fileNm':'12'},		// Form send method Post
	        'removeCompleted'	:	true,	// Disable automatic removal of the queue item for completed uploads.
	        'onComplete'	: 				//각 파일이 업로드 완료 된 경우 호출되는 event
        		function(event,queueID,fileObj,response,data) {
	    			eval("var respJson="+response);
	    			TotalFileInfos = respJson.fileInfos;
			  },
 	      	'onAllComplete':function(event, data){	//모든 파일이 업로드 된 후 호출되는 event
 	      		//업로드된 파일 정보가 없을 경우
				if(TotalFileInfos == ""){
					UploadCount = -1;
				}
 	      		
 	      		if ( typeof(Func) == "function" ){
 	      			var frm = document.frm1;
 	      			
 	      			//지정된 폴더와 파일명으로 파일을 이동할 경우
 	      			if(TotalFileInfos != "" && frm.fileNm.value != "" && frm.folderNm.value != ""){
 	 	      			moveFile(TotalFileInfos);
					}
 	      			
 	      			Func(UploadCount);
	      			UploadCount = 0;
	      		}
	        },
			 'onSelect':function (event, queueID, fileObj) { //각 파일이 선택되어 추가될 경우 호출되는 event 
				//파일확장자 체크 로직
				var realfilename = fileObj.name.toUpperCase();
				var realfileExt = realfilename.substring(realfilename.indexOf(".", realfilename.length-5)+1, realfilename.length);

				//존재하는 확장자일 경우
				if(checkExtName(realfileExt)){
					//현재 파일 사이즈가 0이거나 총사이즈가 지정된 사이즈보다 클경우 취소
		            if (fileObj.size > <%=maxUploadSize%>){               
		                 isFileBig = true;
		                 alert(fileObj.name +"<spring:message code="secfw.msg.error.fileSizeOver" />");
		                 return false;
		            }else if(fileObj.size < 1){
		                 isFileBig = true;
		                 alert(fileObj.name +"<spring:message code="secfw.msg.error.filesizeZero" />");
		                 return false;
		            }else{
		            	UploadSize += fileObj.size;
		            	UploadCount++;
		            }
		 		}else{
		 			isFileBig = true;
		 			alert(fileObj.name +"\n"+"<spring:message code="secfw.msg.error.fileNonAllow" />");
		 			return false;
		 		}
	      	 },
	        'onCancel'    : function(event,ID,fileObj,data) {	//신규추가된 파일 취소 시 호출되는 event
	            UploadSize -= fileObj.size;
	          }
	      });
		
		$('#file_uploadQueue').attr('style','height:'+fileHeight+'px;');
		
	});

	//파일 확장자 체크
	function checkExtName(ext){
 		var fileExt = ext;
		<%if(allowFileList != null && allowFileList.size()>0){%>
			<%for(int i=0; i<allowFileList.size();i++){%>
				if('<%=allowFileList.get(i)%>' == fileExt){
					return true;
				}
			<%}%>
		<%}else{%>
			return true;
		<%}%>
		
		return false;
	}

	//첨부파일 upLoad 처리
	function UploadFile(func) {
	
		// 첨부파일 변경없을 경우 func call
		if ( UploadCount == 0){
			if(view_gbn == "modify"){
				if(document.getElementById("file_uploadREAD0") != null){
					UploadCount = 1;
				}
			}
			func(UploadCount);	

		}else{ // 첨부파일 업로드후 func call;
			Func = func;
			$(Uploadify_ID).uploadifyUpload();
		}
	}
	
	/* 파일이동  : 지정된 경로로 파일 업로드 후 해당업무폴더로 파일 이동*/
	function moveFile(fileInfo){
		var frm = document.frm1;
		frm.fileInfos.value = fileInfo;
		
 		var options = {
			url: "/clms/common/clmsfile.do?method=doMoveFile" ,
			type: "post",
			async: false,
			success: function (data) {	
			},
			error: function(data){
			}
		}
 		$("#frm1").ajaxSubmit(options);
 
	}
</SCRIPT>

<%
	}
	catch (Exception e)
	{
		request.setAttribute("target"	, "_self"								);
		pageContext.forward("/WEB-INF/jsp/secfw/error/ErrorPage.jsp");
	}
%>
