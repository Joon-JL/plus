<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="com.sec.common.clmsfile.dto.ClmsFileForm"%>
<%@ page import="com.sds.secframework.common.util.StringUtil"%>
<%@ page import="com.sds.secframework.common.util.DateUtil"%>
<%@ page import="java.util.List"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<%--
/**
 * Subject   : ClmsFileUpload.jsp 
 * Author    : 신남원 
 * Create On : 2011.09
 * fileInfos형식 : fileId * orgFileNm * fileInfo * filePath * fileSize * dnCnt * fileStatus |
 *                아이디         원본파일            파일형식          파일경로         파일사이즈    다운로드수     파일처리구분(old:기존업로드된 파일, delete:삭제할 파일, add:신규추가파일)
 */
--%>
<%
	try {
		//허용된 파일 확장자
		List allowFileList = (List)request.getAttribute("allowFileList");
		//파일 업로드 전체 최대 사이즈 (Default Size : 10MB)
		String maxUploadSize	= StringUtil.bvl(request.getAttribute("maxUploadSize"),"10240000");  
		//파일 설정정보
		ClmsFileForm command 	= (ClmsFileForm)request.getAttribute("command");
		//화면_구분
		String view_gbn    = StringUtil.bvl(command.getView_gbn(),"modify");
		//파일 정보
		String fileInfos  = command.getFileInfos();
		//파일정보 반환 객체
		String fileInfoName = StringUtil.bvl(command.getFileInfoName(), "fileInfos");
		//파일Frame명
		String fileFrameName = StringUtil.bvl(command.getFileFrameName(), "fileList");
		//멀티 여부
		String multiYn = StringUtil.bvl(command.getMultiYn(), "Y");
		
		//파일종류구분
		String bigclsfcn = StringUtil.bvl(command.getFile_bigclsfcn(), "");
		String midclsfcn = StringUtil.bvl(command.getFile_midclsfcn(), "");
		String smlclsfcn = StringUtil.bvl(command.getFile_smlclsfcn(), "");
	
		String multi = "";
		if("Y".equals(multiYn)){
			multi = "true";
		}else{
			multi = "false";			
		}
		
		
/* 		String userAgent = request.getHeader("User-Agent");
		String os = userAgent.substring(userAgent.toLowerCase().indexOf("windows"), userAgent.length() -1);
		String isNT = "_M";	//(os.toLowerCase().indexOf("nt") > 0)?"_U":"_M";		 */
%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>File Page</title>
<link href="<c:url value="/script/secfw/jquery/uploadify/uploadify_${langCd}.css"/>" type="text/css" rel="stylesheet" />
<script src="<c:url value="/script/secfw/jquery/uploadfive/jquery.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/script/secfw/jquery/uploadfive/jquery.uploadifive.js"/>" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value='/script/secfw/jquery/uploadify/jquery.form.js' />"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/script/secfw/jquery/uploadfive/uploadifive.css"/>" />

</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="javaScript:SetFiles();" style='margin: 0; padding: 0'>
	<form:form name="POGC" id="POGC" method="post" action="/clms/common/clmsfile.do?method=doClmsUpload" autocomplete="off">
		<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
		<input type="hidden" name="file_info_str" value="" />
		<input type="hidden" name="file_id" value="" />
		<input type="hidden" id="totalFileInfos" name="totalFileInfos" value="" />
		<!-- title -->



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
		} else if (view_gbn.equals("reAttach")) {
			view_gbn=new String("reAttach");
	%>
		<input type="file" id="file_upload" name="file_upload" />
		<%
		}
	%>


	</form:form>
	<form:form id="frm1" name="frm1" method="post" autocomplete="off">
		<input type="hidden" id="file_info_str" name="file_info_str" />
	</form:form>
</body>
</html>
<script language="javascript">
	var UploadFilesInfo = "";        //upload된 파일의 정보
	var TotalFileInfos = "";
	var view_gbn = "<%=view_gbn%>";
	
	var Uploadify_ID = "#file_upload";
	var UploadCount = 0;		//File Upload Cnt
	var UploadSize  = 0;		//File Upload Total Size
	
	var Func;
	$(document).ready(function() {
		$(Uploadify_ID).uploadifive({
			'auto'             : false,
			'dnd'             : false,   
			'multi'       	: <%=multi%>,			// Multi file 여부
			'method'      	: 'post',
			'uploadScript'     : '<c:url value="/clms/common/clmsfile.do?method=doClmsUpload"/>', //비동기 파일 업로드 처리 페이지 url.
		    'width'			: '120',		//buttonImg width
			'height'		: '17',			//buttonImg height
			'simUploadLimit': 10,			// 한번에 전송되는 파일 수
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
	 	      		}else{
		 				if(view_gbn == "modify"){
		 					UploadCount = UploadCount + $('.uploadifyQueueItem').length;
		 				}
	 	      		}
	 */
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
			fileHeight = parent.document.getElementById('<%=fileFrameName%>').height - 30;
			$('#file_uploadQueue').attr('style','height:'+fileHeight+'px;margin-top:2px;');
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
//				alert($('.uploadifyQueueItem').length);
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
		
		$('#totalFileInfos').val("<%=fileInfos%>");
		
		if(curAttach == null || curAttach == 'undefined' || curAttach.length<=0){
			if( view_gbn == "download" ){
				parent.document.getElementById('<%=fileFrameName%>').height = 0;
			}
			return;
		}
		
		var temp = curAttach.split("|");
		var tempCol;
		var tempCount;
		var DownCnt = 0;
		
		var fileCount = temp.length;
		
		for(var i=0; i < temp.length-1; i++ ) {
			tempCol = temp[i].split("*");
			tempCount = i+1;
		
			UploadSize += eval(tempCol[4]); 
			var byteSize = myRound(eval(Math.round(tempCol[4] / 1024 * 100) * .01), 2);
			var suffix = 'KB';
			if (byteSize > 1000) {
				byteSize = myRound(Math.round(byteSize *.001 * 100) * .01, 2);
				suffix = 'MB';
			}
			DownCnt = eval(tempCol[5]);
			
			var queue = Uploadify_ID + 'Queue';
			
			
			if ( view_gbn == "download"){
				jQuery(queue).append('<div id="file_uploadREAD' + i + '" class="uploadifyQueueItem">\
						<span class="fileNameD"><img src="<%=IMAGE%>/icon/ico_save_w.gif"/>&nbsp;<a href="javascript:ExecuteServerFile(\'' + tempCol[0] + '\',\'' + tempCol[2] + '\')">' + tempCol[1] + ' (' + byteSize + suffix + ')</a></span>\
						<span class="downCntD"><spring:message code="secfw.msg.title.downLoad" /> '+ DownCnt + '</span>\
					</div>');
				
			}else if( view_gbn == "modify"){
				jQuery(queue).append('<div id="file_uploadREAD' + i + '" class="uploadifyQueueItem">\
						<span class="fileName"><a href="javascript:ExecuteServerFile(\'' + tempCol[0] + '\',\'' + tempCol[2] + '\')">' + tempCol[1] + ' (' + byteSize + suffix + ')</a></span>\
						<span class="cancel">\
						<a href="javascript:deleteFile(\'READ' + i + '\',\'' + tempCol[0]  +'\',\'' + tempCol[4]  +'\')"><img src="<%=IMAGE%>/icon/cancel_new.gif" border="0" /></a>\
						</span>\
					</div>');
			}else if( view_gbn == "reAttach"){	//Sungwoo added 2014-09-29
				jQuery(queue).append('<div id="file_uploadREAD' + i + '" class="uploadifyQueueItem">\
						<span class="fileName"><a href="javascript:ExecuteServerFile(\'' + tempCol[0] + '\',\'' + tempCol[2] + '\')">' + tempCol[1] + ' (' + byteSize + suffix + ')</a></span>\
						<span class="cancel">\
						<a href="javascript:deleteFile(\'READ' + i + '\',\'' + tempCol[0]  +'\',\'' + tempCol[4]  +'\')"><img src="<%=IMAGE%>/icon/cancel_new.gif" border="0" /></a>\
						</span>\
					</div>');
				parent.document.forms[0].<%=fileInfoName%>.value = "<%=fileInfos%>";
			}
		}
		
		//다운로드 모드일경우
		if( view_gbn == "download" ){
			var wHeight = 0;
			fileCount = temp.length - 1;
			var fileHeight = 19;
			
			wHeight = fileHeight * fileCount;
//			parent.document.getElementById('<%=fileFrameName%>').height = wHeight; 
			parent.document.getElementById('<%=fileFrameName%>').height = '100%';		//Sungwoo replacement 2014-07-03
//			parent.document.getElementById('<%=fileFrameName%>').scrolling = "no";

			$('.downCntD').attr('style', 'display:none');
		}
		
		<% if(!"F013".equals(bigclsfcn)){ %>
		$('.downCntD').attr('style', 'display:none');
		<%}%>
	}

	/* Modify Mode : File Delete Infos */
	function deleteFile(id, seq_no, size){
		var queue = Uploadify_ID + id;
  		var options = {
			url: "/clms/common/clmsfile.do?method=doClmsDelete&file_id="+seq_no ,
			type: "post",
			async: false,
			success: function (data) {	
				parent.document.forms[0].<%=fileInfoName%>.value += data;
				jQuery(queue).remove();
				UploadSize -= eval(size);
				//Sungwoo added 2014-09-29
				<%--// commented by seil park 2015-04-16
				 if( view_gbn == "reAttach")parent.document.forms[0].<%=fileInfoName%>.value = ''; --%>
			},
			error: function(data){
			}
		}
 		$("#frm1").ajaxSubmit(options);
 
	}

	/* File Download*/
	function ExecuteServerFile(val, gubun) {
		//var downLoadAlertW = 520;
		//var downLoadAlertH = 200;
		//var downLoadAlertL = (window.screen.availWidth - downLoadAlertW) / 2;
		//var downLoadAlertT = (window.screen.availHeight - downLoadAlertH) / 2;
		//var returnValue = window.showModalDialog("<c:url value='/secfw/main2.do?method=forwardPage&forward_url=/WEB-INF/jsp/common/clmsfile/FileDownLoadAlert.jsp' />", null, "dialogWidth:" + downLoadAlertW + "px; dialogHeight:" + downLoadAlertH + "px; dialogLeft:" + downLoadAlertL + "; dialogTop:" + downLoadAlertT + "; help:no; status:no; scroll:no; resizable:no");
		//if(returnValue == "T") { 
			
			document.POGC.file_id.value = val;
			
			//html 관련된 확장자는 새창으로 띄운다.
			
			/* 2014-05-08 신성우 주석처리. 새탭만 뜨고 파일은 원래 페이지에서 다운로드처리되고있음..
			if(gubun == 'mht' || gubun == 'html' || gubun == 'htm' || gubun == 'eml' || gubun == 'mhtml' || gubun == ''){
				document.POGC.target = "new";	
			} */
			
			document.POGC.action="/clms/common/clmsfile.do?method=doClmsDownload";
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
</script>

<%
	}
	catch (Exception e)
	{
		request.setAttribute("target"	, "_self"								);
		pageContext.forward("/WEB-INF/jsp/secfw/error/ErrorPage.jsp");
	}
%>
