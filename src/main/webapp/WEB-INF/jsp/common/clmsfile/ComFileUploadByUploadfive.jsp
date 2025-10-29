<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.common.clmsfile.dto.ComFileForm" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="java.util.List" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * Subject   : ComFileUpload.jsp 
 * Author    : 신남원 
 * Create On : 2011.09
 * fileInfos형식 : moduleId * misId * sequence * fileName * filePath * fileSize|
 *               모듈식별자      MIS_ID   파일순번        원본파일            파일경로       파일사이즈 
 */
--%>
<%
	try {
		//허용된 파일 확장자
		List allowFileList = (List)request.getAttribute("allowFileList");
		//파일 업로드 전체 최대 사이즈 (Default Size : 10MB)
		//String maxUploadSize	= StringUtil.bvl(request.getAttribute("maxUploadSize"),"10240000");
		//싱글결재 및 메일 첨부용은 10MB 이상은 첨부할 수 없다
		String maxUploadSize	= "10485760";
		//파일 설정정보
		ComFileForm command 	= (ComFileForm)request.getAttribute("command");
		//화면_구분
		String view_gbn    = StringUtil.bvl(command.getView_gbn(),"modify");
		//파일 정보
		String fileInfos  = command.getFileInfos();
		//시스템 구분(mail,approval)
		String sysGbn = command.getSys_gbn();
		
		//파일정보 반환 객체
		String fileInfoName = StringUtil.bvl(command.getFileInfoName(), "fileInfos");
		//파일Frame명
		String fileFrameName = StringUtil.bvl(command.getFileFrameName(), "fileList");
		
/* 		String userAgent = request.getHeader("User-Agent");
		String os = userAgent.substring(userAgent.toLowerCase().indexOf("windows"), userAgent.length() -1);
		String isNT = "_M";	//(os.toLowerCase().indexOf("nt") > 0)?"_U":"_M";		 */
%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>File Page</title>

<link href="<c:url value="/script/secfw/jquery/uploadify/uploadify_${langCd}.css"/>" type="text/css" rel="stylesheet" />
<script src="<c:url value="/script/secfw/jquery/uploadfive/jquery.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/script/secfw/jquery/uploadfive/jquery.uploadifive.js"/>" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/uploadify/jquery.form.js' />"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/script/secfw/jquery/uploadfive/uploadifive.css"/>" />

</head>
<BODY bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  OnLoad="javaScript:SetFiles();">


<form:form name="POGC" id="POGC" method="post" action="/clms/common/clmsfile.do?method=doComUpload" autocomplete="off">
	<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
	<input type="hidden" name="sys_gbn" value="<c:out value='${command.sys_gbn}'/>" />
	<input type="hidden" name="module_id" value="" />
	<input type="hidden" name="mis_id" value="" />
	<input type="hidden" name="sequence" value="" />
	<input type="hidden" name="file_info_str" value="" />
	

	<%
		if( view_gbn.equals("upload")){
			view_gbn=new String("upload");
	%>
				<input type="file" id="file_upload" name="file_upload" />
	<%
		} else if (view_gbn.equals("download")) {
			view_gbn=new String("download");
	%>
		  <div class="uploadifyQueue" id="file_uploadQueue"></div>
	<%
		} else if (view_gbn.equals("modify")) {
			view_gbn=new String("modify");
	%>
			    <input type="file" id="file_upload" name="file_upload" />
	<%
		}
	%>
	
	
</form:form>
<form:form id="frm1" name ="frm1" method="post" autocomplete="off">
	<input type="hidden" id="file_info_str" name="file_info_str" />
</form:form>
</BODY>
</HTML>
<SCRIPT language="javascript">
	var UploadFilesInfo = "";        //upload된 파일의 정보
	var TotalFileInfos = "";
	var view_gbn = "<%=view_gbn%>";
	
	var Uploadify_ID = "#file_upload";
	var UploadCount = 0;		//File Upload Cnt
	var UploadSize  = 0;		//File Upload Total Size
	
	var Func;
	
	$(document).ready(function() {
		$(Uploadify_ID).uploadifive({
	        'uploadScript'      	: '<c:url value="/clms/common/clmsfile.do?method=doComUpload"/>', //비동기 파일 업로드 처리 페이지 url.
	        'multi'       	: true,			// Multi file 여부
//          'queueSizelimit': 10,			// Multi 일 경우 : 최대 첨부파일 수 					
	        'auto'          : false,        //자동 파일 업로드 여부
	        'dnd'             : false, 
	        <c:set var="butimg_width" value="63"/><c:if test="${langCd=='en'}"><c:set var="butimg_width" value="87"/></c:if>
	        'width'			: '<c:out value="${butimg_width}"/>',		//buttonImg width
			'height'		: '17',			//buttonImg height
			'simUploadLimit': 10,			// 한번에 전송되는 파일 수
//	        'sizeLimit'		: '<!--%=maxUploadSize%>',		// 각 파일별 업로드 최대 사이즈
	        'method'      	: 'post',		// Form send method Post
	        'removeCompleted'	:	true,	// Disable automatic removal of the queue item for completed uploads.
	        'onUploadComplete'	: 				//각 파일이 업로드 완료 된 경우 호출되는 event
	        	function(fileObj,response) {
	    			eval("var respJson="+response);
	    			UploadFilesInfo = respJson.fileInfos;
	    			if(UploadFilesInfo != undefined){
	    				parent.document.forms[0].<%=fileInfoName%>.value += UploadFilesInfo;
		    			TotalFileInfos += UploadFilesInfo;
	    			}
			  },
 	      	'onQueueComplete':function(){	//모든 파일이 업로드 된 후 호출되는 event
 	      		//첨부파일과 업로드 파일 수 비교하여 틀릴 경우 -1 반환
/*  	      		if(!checkUploadFile()){
 	      			UploadCount = -1;
 	      		} */
 	      	
 	      		if ( typeof(Func) == "function" ){
	      			Func(UploadCount);
	      			UploadCount = 0;
	      		}
	        },
			 'onAddQueueItem':function (fileObj) { //각 파일이 선택되어 추가될 경우 호출되는 event 
				//파일확장자 체크 로직
				var realfilename = fileObj.name.toUpperCase();
				var realfileExt = realfilename.substring(realfilename.indexOf(".", realfilename.length-5)+1, realfilename.length);

				//존재하는 확장자일 경우
				if(checkExtName(realfileExt)){
					//현재 파일 사이즈가 0이거나 총사이즈가 지정된 사이즈보다 클경우 취소
		            if (UploadSize+fileObj.size > <%=maxUploadSize%>){               
		                 isFileBig = true;
		                 alert(fileObj.name +"<spring:message code="secfw.msg.error.fileSizeOver" />");
		                 return false;
		            }else if(fileObj.size < 1){
		                 isFileBig = true;
		                 alert(fileObj.name +"<spring:message code="secfw.msg.error.filesizeZero" />");
		                 return false;
		            }else if(chkLen(realfilename, '100') == false){
		            	 isFileBig = true;
		                 alert("<spring:message code='common.page.field.clmsfile.notOver50letters'/>");
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
	        'onCancel'    : function(fileObj) {	//신규추가된 파일 취소 시 호출되는 event
	        	
	        	if(UploadSize != 0){
	            	UploadSize -= fileObj.size;
	            	if(UploadSize < 0){ //혹시 사이즈가 -로 내려가면 0으로 셋팅한다.
	            		UploadSize = 0;
	            	}
	            }
	        
				if(UploadCount != 0){
					UploadCount--;	
	            }
	          }
	      });
		
		//첨부파일 리스트 창 높이 계산
		<%if( !view_gbn.equals("download")){%>
			var fileHeight = 0;
			<%--fileHeight = parent.document.getElementById('<%=fileFrameName%>').height - 30;
			$('#file_uploadQueue').attr('style','height:'+fileHeight+'px;margin-top:2px;');
			--%>
			$('#file_uploadQueue').attr('style','height:57px;margin-top:2px;');
		<%}else{%>
			$('#file_uploadQueue').addClass('adddn');
		<%}%>	
		
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
		<%}%>
		
		return false;
	}

	//문자열 길이 체크(UTF-8)
	//초과하면 false, 초과안하면 true
	function chkLen(str, vmaxLen) 
	{
		var nchar = str.length;  
	   
	   	var nmax = vmaxLen;
	   	var nbyte = 0;
	   	var npos = 0;
	   
	   	for(var i=0; i< nchar; i++){
	      	nbyte+=(escape(str.charAt(i)).length > 4 ? 3:1);
	   	  	if(nbyte <= nmax) npos = i + 1;
	   	}
	   	
	   	if(nbyte > nmax){
			return false ;
		}else{
			return true ;
		}
	}	

	//업로드시 불법적인 파일이 존재하는지 체크
	function checkUploadFile(){
		var tempfile = TotalFileInfos.split("|");
		if(tempfile.length-1 == UploadCount){
			return true;
		}
		return false;
	}
	
	//
	
	//첨부파일 upLoad 처리
	function UploadFile(func) {
	
		// 첨부파일 변경없을 경우 func call
		if ( UploadCount == 0){
			<%if( view_gbn.equals("modify")){%>
//			alert($('.uploadifyQueueItem').length);
			UploadCount = UploadCount + $('.uploadifyQueueItem').length;
			<%}%>
			func(UploadCount);	

		}else{ // 첨부파일 업로드후 func call;
			Func = func;
			$(Uploadify_ID).uploadifive('upload');
		}
	}

	/* File 정보 설정 */
	function SetFiles(){

		if ( view_gbn == "upload" ) return;
		
		var curAttach= "<%=fileInfos%>"; 
		
		if(curAttach == null || curAttach == 'undefined' || curAttach.length<=0){
			if( view_gbn == "download" ){
				parent.document.getElementById('<%=fileFrameName%>').height = 0;
			}
			return;
		}
		
		var temp = curAttach.split("|");
		var tempCol;
		var tempHtml;
		var tempCount;
		var fileSize = 0;
		var totSize = 0;
		
		
		for(var i=0; i < temp.length-1; i++ ) {
			tempCol = temp[i].split("*");
			tempCount = i+1;
		
			UploadSize += eval(tempCol[5]); 
			var byteSize = myRound(eval(Math.round(tempCol[5] / 1024 * 100) * .01), 2);
			var suffix = 'KB';
			if (byteSize > 1000) {
				byteSize = myRound(Math.round(byteSize *.001 * 100) * .01, 2);
				suffix = 'MB';
			}
			
			
			var queue = Uploadify_ID + 'Queue';
			
			
			if ( view_gbn == "download"){
				jQuery(queue).append('<div id="file_uploadREAD' + i + '" class="uploadifyQueueItem">\
						<span class="fileName"><a href="javascript:ExecuteServerFile(\'' + tempCol[0] + '\',\'' + tempCol[1] + '\',\'' + tempCol[2] + '\')">' + tempCol[3] + ' (' + byteSize + suffix + ')</a></span>\
					</div>');
				
			}else if( view_gbn == "modify"){
				jQuery(queue).append('<div id="file_uploadREAD' + i + '" class="uploadifyQueueItem">\
						<span class="fileName">' + tempCol[3] + ' (' + byteSize + suffix + ')</span>\
						<span class="cancel">\
						<a href="javascript:deleteFile(\'READ' + i + '\',\'' + tempCol[0] + '\',\'' + tempCol[1] + '\',\'' + tempCol[2]  +'\',\'' + tempCol[4]  +'\')"><img src="<c:url value="/script/secfw/jquery/uploadify/cancel_old.gif"/>" border="0" /></a>\
						</span>\
					</div>');
			}
		}
		
		//다운로드 모드일경우
		if( view_gbn == "download" ){
			var wHeight = 0;
			fileCount = temp.length - 1;
			var fileHeight = 19;
			
			wHeight = fileHeight * fileCount;
			parent.document.getElementById('<%=fileFrameName%>').height = wHeight; 
//			parent.document.getElementById('<%=fileFrameName%>').scrolling = "no";
		}
	}

	/* Modify Mode : File Delete Infos */
	function deleteFile(id, moduleId, misId, sequence, size){
		var queue = Uploadify_ID + id;
  		var options = {
			url: "/clms/common/clmsfile.do?method=doComDelete&module_id="+moduleId+"&mis_id="+misId+"&sequence="+sequence ,
			type: "post",
			async: false,
			success: function (data) {	
				parent.document.forms[0].<%=fileInfoName%>.value += data;
				jQuery(queue).remove();
				UploadSize -= eval(size);
			},
			error: function(data){
			}
		}
 		$("#frm1").ajaxSubmit(options);
 
	}

	/* File Download*/
	function ExecuteServerFile(modueId, misId, sequence) {
		//var downLoadAlertW = 520;
		//var downLoadAlertH = 300;
		//var downLoadAlertL = (window.screen.availWidth - downLoadAlertW) / 2;
		//var downLoadAlertT = (window.screen.availHeight - downLoadAlertH) / 2;
		//var returnValue = window.showModalDialog("<c:url value='/secfw/main2.do?method=forwardPage&forward_url=/WEB-INF/jsp/common/clmsfile/FileDownLoadAlert.jsp' />", null, "dialogWidth:" + downLoadAlertW + "px; dialogHeight:" + downLoadAlertH + "px; dialogLeft:" + downLoadAlertL + "; dialogTop:" + downLoadAlertT + "; help:no; status:no; scroll:no; resizable:no");
		//if(returnValue == "T") {
			document.POGC.module_id.value = modueId;
			document.POGC.mis_id.value = misId;
			document.POGC.sequence.value = sequence;
	    	document.POGC.action="/clms/common/clmsfile.do?method=doComDownload";
	    	document.POGC.submit();
		//} else {
		//	return;
		//}
	}

	/* File Size 재계산 */
	function myRound(num, pos) {
		var posV = Math.pow(10, (pos ? pos : 2));
		return Math.round(num*posV)/posV;
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
