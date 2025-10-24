<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>

<%--
/**
 * Subject   : EPUploadType1.jsp
 * Author    : 금현서
 * Create On : 2009.11
 * 
 */
--%>
<%
	try {
		String userId     = StringUtil.bvl((String)session.getAttribute("secfw.session.user_id"),"");
	
		String fileRefNo  = StringUtil.bvl(request.getAttribute("fileRefNo"),"");
		String fileInfos  = StringUtil.bvl(request.getAttribute("fileInfos"),"");
		String sDir       = StringUtil.bvl(request.getAttribute("sDir"),"");
		String serverName = StringUtil.bvl(request.getAttribute("serverName"),"");
		String serverPort = StringUtil.bvl(request.getAttribute("serverPort"),"");
		String epftpdIP   = StringUtil.bvl(request.getAttribute("epftpdIP"),"");
		
		String userAgent = request.getHeader("User-Agent");
		String os = userAgent.substring(userAgent.toLowerCase().indexOf("windows"), userAgent.length() -1);
		String isNT = (os.toLowerCase().indexOf("nt") > 0)?"_U":"_M";
	
		String subDir = DateUtil.getTime("yyyyMM");
		sDir = sDir + "/" + subDir;
		String view_gbn    = StringUtil.bvl(request.getParameter("view_gbn"),"");
		
%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title>File Page</title>
<link href="<c:url value='/style/secfw/common.css' />" type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
</head>
<body  bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="overflow-x:hidden;overflow-y:hidden" OnLoad="javaScript:SetFiles();">
<form:form name = "fm" >
	<input type="hidden" name="menu_id" value="">
    <OBJECT ID="EpFTPC1" codeBase='/util/epftp/client/EpFTP3<%=isNT%>.cab#version=1,1,6,4' HEIGHT="0" WIDTH="0" CLASSID="CLSID:34B5A473-9696-4F9A-9BA1-41B8185A9798"></OBJECT>
	<!-- 기존싱글 -->
	<%-- 
    <OBJECT ID="EpAdm2 Control" name="EpAdmC" CLASSID="CLSID:C63E3330-049F-4C31-B47E-425C84A5A725" HEIGHT="0" WIDTH="0" ></OBJECT>
    --%>
</form:form>
<form:form name = "POGC" >
		<ul class="file">
			<li class="head" style="padding:0 0 3px 10px">
	<%
		if( view_gbn.equals("upload")||(fileRefNo.equals("")&&view_gbn.equals("modify"))){
			view_gbn=new String("upload");
	%>
		  	<a href="javascript:PogcOpenEpFTP()" class="bt_option_s"><span><spring:message code="secfw.page.button.file" /></span></a>
		   	<a href="javascript:del_row()" class="bt_option_s"><span><spring:message code="secfw.page.button.delete" /></span></a>
	<%
		} else if (view_gbn.equals("download")) {
			view_gbn=new String("download");
	%>
		  <a href="javascript:SaveServerFile()" class="bt_option_s"><span><spring:message code="secfw.page.button.download" /></span></a>
		  <a href="javascript:runFiles()" class="bt_option_s"><span><spring:message code="secfw.page.button.execution" /></span></a>
		  <a href="javascript:saveandrunFiles()" class="bt_option_s"><span><spring:message code="secfw.page.button.saveStart" /></span></a>
	<%
		} else if (view_gbn.equals("modify")&&!fileRefNo.equals("")) {
			view_gbn=new String("modify");
	%>
		  	<a href="javascript:PogcOpenEpFTP()" class="bt_option_s"><span><spring:message code="secfw.page.button.file" /></span></a>
		   	<a href="javascript:del_row()" class="bt_option_s"><span><spring:message code="secfw.page.button.delete" /></span></a>
			<a href="javascript:SaveServerFile()" class="bt_option_s"><span><spring:message code="secfw.page.button.download" /></span></a>
			<a href="javascript:runFiles()" class="bt_option_s"><span><spring:message code="secfw.page.button.execution" /></span></a>
			<a href="javascript:saveandrunFiles()" class="bt_option_s"><span><spring:message code="secfw.page.button.saveStart" /></span></a>
	<%
		} 
	%>
			<span class="total_size"><strong><span id="attachSize">0</span> KB</strong>/10MB</span>
			</li>
		</ul>
	
		<!-- Scroll -->
		<div class="dscrolly" style="height:68px;">
			<table class="file_add_list all" id='filerow'>
	            <tr>
	              <th valign="top"></th>
	            </tr>
			</table>		
		</div>
		<!-- //Scroll -->
</form:form>
<form:form id="flog" name ="flog" >
    <input type="hidden" name="file_info_str" value="" />
</form:form>

</body>
</html>
<script] language="javascript">
var MaxRow=0;    //현재까지 추가된 row의 수, 단 삭제시에도 증가하는 값
var DelRow=0;    //현재까지 삭제된 row의 수
var checkedval = 0;
var UploadFilesInfo = "";        //upload된 파일의 정보

var SDir;                   //첨부파일이 올라갈 디렉토리
SDir = '<%=sDir%>/';        //수정 (첨부파일이 올라갈 서버쪽 디렉토리)

document.fm.EpFTPC1.ServerName = "<%=serverName%>";       // 수정 (서버 IP)
document.fm.EpFTPC1.ServerPort = "<%=serverPort%>";     // 수정 (서버 포트)
document.fm.EpFTPC1.EpftpdIP =   "<%=epftpdIP%>";  // epftpd IP

document.fm.EpFTPC1.SecureBox = "";             //document.fm.EpAdmC.GetSecureBox();		// 암호화된 사용자 정보값
document.fm.EpFTPC1.IsV4 = 0;				    // proxy 구조 사용 여부
document.fm.EpFTPC1.ExternalBody = 0;		    // 권한체크,파일압축 사용여부
document.fm.EpFTPC1.ServerDir  = SDir;          // 첨부파일이 저장될 서버 path
document.fm.EpFTPC1.ModuleName  = "secModule";  // 접근 권한 체크하는 url을 찾을 때 사용하는 모듈명
document.fm.EpFTPC1.UniqueKey  = "secKey";      // 권한 체크할 때 사용할 수 있는 키값

document.fm.EpFTPC1.UserId = "testID";
document.fm.EpFTPC1.UserName = "user";

document.fm.EpFTPC1.ViewType = 0;
document.fm.EpFTPC1.DeleteAuthority = 2;
document.fm.EpFTPC1.LimitSize = 10;             // 수정 (첨부파일 최대 사이즈)
document.fm.EpFTPC1.IsMail = 0;
document.fm.EpFTPC1.ZipCompress = 0;
document.fm.EpFTPC1.Base64 = 0;
//document.fm.EpFTPC1.TempDir = "C:/temp/";
document.fm.EpFTPC1.TempDir = SDir;
document.fm.EpFTPC1.IsLocalMail = 0;

document.fm.EpFTPC1.ServerFilesAutoDeleted = 1;

// 다운로드 리스트에 보여줄 화일 정보
//DocID*로컬파일명*파일정보*서버파일명*크기|
var viewFiles="<%=fileInfos%>"; 
var fileRefNo="<%=fileRefNo%>";

if(viewFiles.length > 0) {
	document.fm.EpFTPC1.Files = viewFiles;
}

//	parent.document.all.fileList.style.height= 55;

function UploadFile(formName) {
	var UploadCount;            //File Upload Cnt
	var oldFiles;

	// 첨부파일 타입 필터링 시작
		var retval = document.fm.EpFTPC1.FilesInfo();       // Upload Method  수행
	
		if (retval.indexOf('|') > 1) {
			var valuelist = splitData(retval,'|');
			for (var i = 0; i < valuelist.length-1 ; i++) {
				var textlist= splitData(valuelist[i],'*');
				var filenm = splitData(textlist[1],"\\");
				var fsize = textlist[4];
		
				var realfilename = filenm[filenm.length-1].toUpperCase();
				
				if(realfilename.indexOf('.JS') != -1
					       || realfilename.indexOf('.PHP') != -1 
					       || realfilename.indexOf('.ASP') != -1 
					       || realfilename.indexOf('.EXE') != -1 
					       || realfilename.indexOf('.HTM') != -1 
					       || realfilename.indexOf('.HTML') != -1  
				) {
					/*
					alert('아래와 같은 파일타입은 보안상 업로드 하실 수 없습니다.\n' +
						  '*.jsp, *.php, *.asp, *.exe, *.htm, *.html, *.js'
						  );
						  */
						  alert('<spring:message code="secfw.msg.ask.noExt" />.\n' +
								  '*.jsp, *.php, *.asp, *.exe, *.htm, *.html, *.js'
								  );						  
					return -1;
				}	
			}
		}
	// 첨부파일 타입 필터링 끝
	document.fm.EpFTPC1.UploadType = 1;              // Upload  유형
	UploadCount      = document.fm.EpFTPC1.UpLoad();       // Upload Method  수행
	UploadFilesInfo  = document.fm.EpFTPC1.AttachedFiles;  // Upload된 파일정보 return
	oldFiles = getFileInfo1();    // 수정을 하는 경우 이미 서버에 업로드된 파일리스트
	// oldFiles + UploadFilesInfo 를 return 하거나 form input태그에 저장하면 됨

	UploadFilesInfo = oldFiles+UploadFilesInfo;
	parent.document.forms[0].fileInfos.value = UploadFilesInfo;

	/*
	* 김정곤 수정 추가내역 2011년3월18일 현재 fiel_ref_no를 servlet 에서 생성해줄때 
	* 업로드일경우만 생성해주기에 만약  view_gbn 값을 modify 로 설정시 문제가 저장시 fiel_ref_no 널로
	* 인한 에러가 생길수 있어.  modify 이지만 fiel_ref_no가 널일시 화면을  upload 로 로딩하고 해당값을
	* 아이프레임으로 호출한 부모 페이지에 넣어주는 로직 추가. 
	*/
	parent.document.forms[0].view_gbn.value = '<%=view_gbn%>';
	
	return UploadCount;
}

function SetFiles(){
	var rowCount = filerow.rows.length;

	for(var i=1;i<rowCount;++i){
		filerow.deleteRow(1);
	}

	var curAttach=document.fm.EpFTPC1.FilesInfo;
	if(curAttach == null || curAttach == 'undefined' || curAttach.length<=0)
		return;
	var temp = curAttach.split("|");
	var tempCol;
	var tempHtml;
	var tempCount;
	var fileSize = 0;
	var totSize = 0;
	for(var i=0; i < temp.length-1; i++ ) {
		r = filerow.insertRow();
		r.style.color="#000000";
		a = r.insertCell();
		MaxRow = MaxRow+1;
		tempCol = temp[i].split("*");
		tempCount = i+1;
		fileSize = myRound(eval(tempCol[4]/1024),2);

		/*
		tempHtml = "<p><input type=checkbox name='c" + tempCount + "' value='c" + tempCount + "'>&nbsp;";
		tempHtml = tempHtml + "<a href='javascript:ExecuteServerFile(\"" + filenameEnc(temp[i]) + "\");' >" + tempCol[1] + "&nbsp;(" + fileSize + "&nbsp;KB)</a>" ;
		tempHtml = tempHtml + "<input name='d" + tempCount + "' type=hidden value='" + filenameEnc(temp[i]) + "|' id='old'>";
		tempHtml = tempHtml + "<input name='s" + tempCount + "' type=hidden value='" + fileSize + "'></p>";
		*/
		tempHtml = "<input type=checkbox name='c" + tempCount + "' value='c" + tempCount + "'>&nbsp;";
		tempHtml = tempHtml + "<a href='javascript:ExecuteServerFile(\"" + filenameEnc(temp[i]) + "\");' ><span class=pass>" + tempCol[1] + "</span></a>&nbsp;(" + fileSize + "&nbsp;KB)" ;
		tempHtml = tempHtml + "<input name='d" + tempCount + "' type=hidden value='" + filenameEnc(temp[i]) + "|' id='old'>";
		tempHtml = tempHtml + "<input name='s" + tempCount + "' type=hidden value='" + fileSize + "'><br>";

		totSize = totSize + fileSize;

		a.innerHTML = tempHtml;
	}
	totSize = myRound(totSize,2);
	document.all.attachSize.innerHTML = totSize;

//	parent.document.all.fileList.style.height= (filerow.rows.length-1) * 23 +55;

//	if( parent.parent.document.all.DataiFrame != undefined){
//		parent.parent.document.all.DataiFrame.style.height = parent.document.body.scrollHeight;
//	}
//	if( parent.opener != undefined){
//		var winBody = parent.document.body;
//		var marginWidth = parseInt(winBody.leftMargin)+parseInt(winBody.rightMargin);
//		var wid = winBody.scrollWidth + (winBody.offsetWidth - winBody.clientWidth) + marginWidth-5;
//		parent.window.resizeTo( wid, parent.document.body.scrollHeight+30);
//	}

	//메뉴아이디 세팅
	var menuIdObj = parent.document.frm.menu_id ;
	if(menuIdObj!=null) {
		document.fm.menu_id.value = menuIdObj.value ;
	}

}

function filenameEnc (filename) {

	var Ori="%|#|'|\\$|&|@| |\\+|=|\\?";
	var Rep="<↑>|<→>|<↘>|<↓>|<↔>|<〓>|<↕>|<≪>|<†>|_";
	var OriArr = Ori.split("|");
	var RepArr = Rep.split("|");
	var text = filename;
	for(var r=0; r < OriArr.length; r++ ) {
		tmp ="";
		tmp = text.replace(new RegExp(OriArr[r],"g"), RepArr[r]);
		//tmp = SontextReplaceAll(text, OriArr[r], RepArr[r]);
		text = tmp;
	}
	return text;
}
function filenameDec (filename) {
	var Ori="%|#|'|$|&|@| |+|=";
	var Rep="<↑>|<→>|<↘>|<↓>|<↔>|<〓>|<↕>|<≪>|<†>";
	var OriArr = Ori.split("|");
	var RepArr = Rep.split("|");
	var text = filename;
	for(var r=0; r < OriArr.length; r++ ) {
		tmp ="";
		tmp = text.replace(new RegExp(RepArr[r],"g"), OriArr[r]);
		//tmp = SontextReplaceAll(text, RepArr[r], OriArr[r]);
		text = tmp;
	}
	return text;
}
function splitData(source, delimeter) {
	var ret = source.split(delimeter);
	return ret;
}
function PogcOpenEpFTP()
{
	var retval =  document.fm.EpFTPC1.AddAttachFiles();
	fnPutVal(retval);
}
function fnPutVal(retval)
{
	//파일제한
	//if(!UploadFile()) return;
	
	if (retval.indexOf('|') < 1) return; //선택된 값이 없는 경우
	var valuelist = splitData(retval,'|');
	for (var i = 0; i < valuelist.length-1 ; i++) {
		var textlist= splitData(valuelist[i],'*');
		var filenm = splitData(textlist[1],"\\");
		var fsize = textlist[4];

		var realfilename = filenm[filenm.length-1];

		ins_row(realfilename, filenameEnc(valuelist[i]),filenameEnc(textlist[1]), fsize);
	
	}
}
function ins_row(txt,val,realfilename, fsize)
{
	if (txt=="") return;
	var curSize = myRound(eval(fsize/1024),2);
	var TotalRow = MaxRow - DelRow;      //현재 존재하는 테이블의 row
	r = filerow.insertRow();
	r.style.color="#000000";
	a = r.insertCell();
	MaxRow = MaxRow+1;

	var strip = new RegExp();
	strip = /\\/gi;
	var realfilenameRe = realfilename.replace(strip, "/");

	//var useDHTML = "<p><input type=checkbox name='c" +MaxRow+"' value='c" +MaxRow+"'>&nbsp;<a href=\"javascript:displayLocalFile('"+ realfilenameRe +"');\" window.status=''>" + txt + "&nbsp;(" + curSize + "&nbsp;KB)</a><input name='d" +MaxRow+"' type=hidden value='"+ val +"|' id='new'><input name='s" +MaxRow+"' type=hidden value='"+ curSize +"'></p>";
	var useDHTML = "<input type=checkbox name='c" +MaxRow+"' value='c" +MaxRow+"'>&nbsp;<a href=\"javascript:displayLocalFile('"+ realfilenameRe +"');\"><span class=pass>" + txt + "</span></a>&nbsp;(" + curSize + "&nbsp;KB)<input name='d" +MaxRow+"' type=hidden value='"+ val +"|' id='new'><input name='s" +MaxRow+"' type=hidden value='"+ curSize +"'><br>";

//		parent.document.all.fileList.style.height= TotalRow * 23 +80;

	a.innerHTML = useDHTML;
	var tempval = eval(parseFloat(document.all.attachSize.innerHTML) + curSize);
	var putval = myRound(tempval,2);
	document.all.attachSize.innerHTML = putval;

//	if( parent.parent.document.all.DataiFrame != undefined){
//		parent.parent.document.all.DataiFrame.style.height = parent.document.body.scrollHeight;
//	}
//	if( parent.opener != undefined){
//		var winBody = parent.document.body;
//		var marginWidth = parseInt(winBody.leftMargin)+parseInt(winBody.rightMargin);
//		var wid = winBody.scrollWidth + (winBody.offsetWidth - winBody.clientWidth) + marginWidth-5;
//
//		progressWinOpen( self, 0, 0);	// 처리 프로세스 상태보여주는 모달다이얼로그
//		progressWinClose();
//		parent.window.resizeTo( wid, parent.document.body.scrollHeight+30);
//	}


}

function selectAll() {
	var TotalRow = MaxRow-DelRow;
	if(checkedval == 0) {
	for (i = 0; i < TotalRow ; i++) //현재 선택한 row가 몇번째 것인지 확인한다.
	{
		if(document.POGC.elements[i*3].disabled == false)
		document.POGC.elements[i*3].checked = true;
	}
		checkedval = 1;
	}else {
	for (i = 0; i < TotalRow ; i++) //현재 선택한 row가 몇번째 것인지 확인한다.
	{
		if(document.POGC.elements[i*3].disabled == false)
		document.POGC.elements[i*3].checked = false;
	}
		checkedval = 0;
	}
}
function del_row()
{
	var TotalRow = MaxRow - DelRow;
	var ti = 1;                //현재 삭제된 개수
	for (i=1; i < TotalRow+1 ; i++) {
		if(document.POGC.elements[(i-ti)*3].checked == true) {
			delId= document.POGC.elements[(i-ti)*3+1];
			document.fm.EpFTPC1.SelectedFiles = filenameDec(delId.value);
			document.fm.EpFTPC1.DeleteSelectedFiles();

			sdel= document.POGC.elements[(i-ti)*3+2];
			var tempval = eval(parseFloat(document.all.attachSize.innerHTML) - parseFloat(sdel.value));
			var putval = myRound(tempval,2);
			document.all.attachSize.innerHTML = putval ;
			r = filerow.deleteRow(i-ti+1);
			ti = ti+1;
		}
	}
	if(ti == 1) {
		//alert('현재 선택된 파일이 없습니다.');
		alert('<spring:message code="secfw.msg.ask.noSelect" />');
		return;
	}
	DelRow = DelRow + ti - 1;

//	parent.document.all.fileList.style.height= TotalRow * 23+33;

}
function runFiles() {
	var selval = "";
	var selcnt = 0; //현재 삭제된 개수
	for (i=0; i < MaxRow ; i++) {
		if(document.POGC.elements[i*3].checked == true) {
			delId= document.POGC.elements[i*3+1];
			selval += filenameDec(delId.value) + "|";
			selcnt++;
			document.POGC.elements[i*3].checked = false;

		}
	}
	if(selcnt == 0) {
		//alert('현재 선택된 파일이 없습니다.');
		alert('<spring:message code="secfw.msg.ask.noSelect" />');
		return;
	}
	ExecuteServerFile(selval);
}
function saveandrunFiles() {
        var TotalRow = MaxRow;
        var selval = "";
        var ti = 1;
        var chk = 0;
        for (i=1; i < TotalRow+1 ; i++) {
            if(document.POGC.elements[(i-ti)*3].checked == true ) {
                selId = document.POGC.elements[(i-ti)*3+1];
                selval += filenameDec(selId.value);
                chk ++;
                document.POGC.elements[(i-ti)*3].checked = false;
            }
        }
        if(chk ==0) {
            //alert("현재 선택된 파일이 없습니다.");
            alert('<spring:message code="secfw.msg.ask.noSelect" />');
            return;
        }

    	var downLoadAlertW = 520;
    	var downLoadAlertH = 300;
    	var downLoadAlertL = (window.screen.availWidth - downLoadAlertW) / 2;
    	var downLoadAlertT = (window.screen.availHeight - downLoadAlertH) / 2;

		var returnValue ="T";// window.showModalDialog("<c:url value='/secfw/comControlNoAuth.do?method=forwardPage&forward_url=/jsp/secfw/common/FileDownLoadAlert.jsp' />", null, "dialogWidth:" + downLoadAlertW + "px; dialogHeight:" + downLoadAlertH + "px; dialogLeft:" + downLoadAlertL + "; dialogTop:" + downLoadAlertT + "; help:no; status:no; scroll:no; resizable:no");

		if(returnValue == "T") {
	    	insertFileLog(selval);
	
	        document.fm.EpFTPC1.SelectedFiles = selval;
	        document.fm.EpFTPC1.Download();
	        runFile3();
		} else {
			return;
		}
}
function runFile3(){
        var executeInfo = document.fm.EpFTPC1.AttachedFiles;
        var strarr = executeInfo.split('|');
        var i;
        var executeFiles = "";
        var tempStr;
        for(i=0;i<strarr.length-1;++i){
        	tempStr = strarr[i].split('*');
        	executeFiles += tempStr[1] + "|";
        }
        document.fm.EpFTPC1.Execute(filenameDec(executeFiles));
}

function SaveServerFile() {
	var selval = "";
	var chk = 0;
	for (i=0; i < MaxRow ; i++) {
		if(document.POGC.elements[i*3].checked == true ) {
			selId = document.POGC.elements[i*3+1];
			selval += filenameDec(selId.value);
			chk ++;
			document.POGC.elements[i*3].checked = false;
		}
	}
	if(chk ==0) {
		//alert("현재 선택된 파일이 없습니다.");
		alert('<spring:message code="secfw.msg.ask.noSelect" />');
		return;
	}

	var downLoadAlertW = 520;
	var downLoadAlertH = 300;
	var downLoadAlertL = (window.screen.availWidth - downLoadAlertW) / 2;
	var downLoadAlertT = (window.screen.availHeight - downLoadAlertH) / 2;

	var returnValue ="T";// window.showModalDialog("<c:url value='/secfw/comControlNoAuth.do?method=forwardPage&forward_url=/jsp/secfw/common/FileDownLoadAlert.jsp' />", null, "dialogWidth:" + downLoadAlertW + "px; dialogHeight:" + downLoadAlertH + "px; dialogLeft:" + downLoadAlertL + "; dialogTop:" + downLoadAlertT + "; help:no; status:no; scroll:no; resizable:no");

	if(returnValue == "T") {
		insertFileLog(selval);
		document.fm.EpFTPC1.SelectedFiles = selval;
		document.fm.EpFTPC1.Download();
	} else {
		return;
	}

	
	
}

function ExecuteServerFile(val) {

	var selval = "";
	selval = filenameDec(val);

	var downLoadAlertW = 520;
	var downLoadAlertH = 300;
	var downLoadAlertL = (window.screen.availWidth - downLoadAlertW) / 2;
	var downLoadAlertT = (window.screen.availHeight - downLoadAlertH) / 2;

	var returnValue ="T";// window.showModalDialog("<c:url value='/secfw/comControlNoAuth.do?method=forwardPage&forward_url=/jsp/secfw/common/FileDownLoadAlert.jsp' />", null, "dialogWidth:" + downLoadAlertW + "px; dialogHeight:" + downLoadAlertH + "px; dialogLeft:" + downLoadAlertL + "; dialogTop:" + downLoadAlertT + "; help:no; status:no; scroll:no; resizable:no");

	if(returnValue == "T") {
		insertFileLog(selval);
		document.fm.EpFTPC1.SelectedFiles = selval;
		document.fm.EpFTPC1.FileOpen();
	} else {
		return;
	}
}

function displayLocalFile(strFileName) {
	var strip = new RegExp("/", "gi");
	var strFileName = strFileName.replace(strip, "\\");
	//문제발생시 아래줄 삭제..그러나 그러면 저장 이전의첨부파일을 열지 못할수 있음.
	var strFileName = strFileName.substring(0,strFileName.length-3);
    //////////////////////////////////////////////////////////////
	var retfiles = document.fm.EpFTPC1.Execute(filenameDec(strFileName));
	return;
}

function myRound(num, pos) {
	var posV = Math.pow(10, (pos ? pos : 2));
	return Math.round(num*posV)/posV;
}
function getFileInfo1() {
	var TotalRow = MaxRow - DelRow;
	var chkcnt = 0;
	var selval = "";
	for (i=0; i < TotalRow ; i++) {
		sdel= document.POGC.elements[i*3+1];
		if(document.POGC.elements[i*3+1].id == 'old') {
			selval += filenameDec(sdel.value);
			chkcnt ++;
		}
	}
	return selval;
}

	function insertFileLog(fileStr) {
		var frm = document.flog;
		frm.file_info_str.value = fileStr;
		
		var options = {
				url: "<c:url value='/secfw/log.do?method=insertFileDownLog'/>&menu_id=" + document.fm.menu_id.value,
				type: "post",
				dataType: "json",
				success:function(responseText, statusText){
				},
				error: function(xhr, statusText){
				}
			}
			$("#flog").ajaxSubmit(options);
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
