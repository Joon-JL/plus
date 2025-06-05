<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%@ page import="com.sds.secframework.common.util.WebUtil" %>
<%@ page import="com.sds.secframework.common.util.Token"%>

<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%--
/**
 * 파  일  명 : Sign_.jsp
 * 프로그램명 : 날인 등록
 * 설      명 : 
 * 작  성  자 : 박병주
 * 작  성  일 : 2013.05
 */
--%>
<%
    //메뉴네비게이션 스트링 
	//String menuNavi = (String)request.getAttribute("secfw.menuNavi");
    String menuNavi = (String)request.getAttribute("menuNavi");
%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>

<script language="javascript">
<!--

	/**
	* 초기화면 로딩
	*/
	$(document).ready(function(){
		
		// 첨부파일창 load
		initPage();
		
		// 캘린더 초기화
		$(".text_calendar").each(function(index){   
			initCal($(this).attr("id"));
		});
		
		// 등록 화면 일 경우 날인/ 증명서류/ 인장반출 필드 초기화
		<c:if test="${command.apl_sqn == ''}">
			toggleSealYN(false);
			toggleAplOutYN(false);
			toggleDocYN(false);
		</c:if>
		
		// 수정 화면 일 경우 날인/ 증명서류/ 인장반출 필드 초기화
		<c:if test="${command.apl_sqn != ''}">
			<c:if test="${lom.seal_yn eq 'Y'}">
				toggleSealYN(true);
			</c:if>
			<c:if test="${lom.seal_yn eq 'N'}">
				toggleSealYN(false);
			</c:if>
			<c:if test="${lom.apl_out_yn eq 'Y'}">
				toggleAplOutYN(true);
			</c:if>
			<c:if test="${lom.apl_out_yn eq 'N'}">
				toggleAplOutYN(false);
			</c:if>
			<c:if test="${lom.doc_yn eq 'Y'}">
				toggleDocYN(true);
			</c:if>
			<c:if test="${lom.doc_yn eq 'N'}">
				toggleDocYN(false);
			</c:if>			
		</c:if>
		
		var frm = document.frm;
		
		// seal_knd_cd
		getCodeSelectByUpCd2("frm", "seal_knd_cd", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"C021"+"&combo_abbr=F&combo_type=S2&combo_use_yn=Y&combo_selected="+"<c:out value='${lom.seal_knd_cd}'/>");
	
		// 신청유형 (금융거래용,계약용,,,) srch_apl_cls
		getCodeSelectByUpCd2("frm", "apl_cls", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&page_gbn=MOD&combo_grp_cd="+"AT01"+"&combo_abbr=F&combo_type=S2&combo_use_yn=Y&combo_selected="+"<c:out value='${lom.apl_cls}'/>");

	}); 	

/**
* 텍스트제한
*/


function chkMsgLength(intMax,objMsg,st) 
{

	var length = lengthMsg(objMsg.value);


st.innerHTML = length;//현재 byte수를 넣는다

if (length > intMax) 
{
alert('<spring:message code="clm.field.signmng.alertmsgword" />');  // 글자를 1000자 이상 입력 할수 없습니다.초과된 글자는 삭제됩니다.
<!-- alert('<spring:message code="clm.field.signmng.alertmsg1" />');  // 날인신청에 체크를 먼저 하셔야 합니다.-->
objMsg.value = objMsg.value.replace(/\r\n$/, "");
objMsg.value = assertMsg(intMax,objMsg.value, st); 
}
} 


function TempNull()
{
return false;
} 


		// 현재 메시지 바이트 수 계산
		function lengthMsg(objMsg) 
		{
		
		var nbytes = 0;
		for (i=0; i<objMsg.length; i++) 
		{
		var ch = objMsg.charAt(i);
		if(escape(ch).length > 4) 
		{
		nbytes += 3;
		} 
		else if (ch == '\n') 
		{
		if (objMsg.charAt(i-1) != '\r') 
		{
		nbytes += 1;
		}
		} 
		else if (ch == '<' || ch == '>') 
		{
		nbytes += 4;
		} 
		else 
		{
		nbytes += 1;
		}
		}
		return nbytes;
		}
		
		// 80 바이트 넘는 문자열 자르기
		function assertMsg(intMax, objMsg, st) 
		{
		var inc = 0;
		var nbytes = 0;
		var msg = "";
		
		var msglen = objMsg.length;
		
		for (i=0; i < msglen; i++) 
		{
		var ch = objMsg.charAt(i);
		
		if (escape(ch).length > 4) 
		{
		inc = 3;
		} 
		else if (ch == '\n') 
		{
		if (objMsg.charAt(i-1) != '\r') 
		{
		inc = 1;
		}
		} 
		else if (ch == '<' || ch == '>') 
		{
		inc = 4;
		} 
		else 
		{
		inc = 1;
		}
		
		if ((nbytes + inc) > intMax) 
		{
		break;
		}
		
		nbytes += inc;
		msg += ch;
		}
		
		st.innerHTML = nbytes; //현재 byte수를 넣는다
		
		return msg;
		}






	/**
	* 날인신청 필드 활성/비활성화
	*/
	function toggleSealYN(offSet){		
				
		if(offSet){
			$("#seal_knd_cd").attr("disabled",false);
			$("#apl_out_yn").attr("disabled",false);			
			toggle_ffmtman(true);
		} else {
			$("#seal_knd_cd").attr("disabled",true);
			$("#apl_out_yn").attr("disabled",true);
			toggle_ffmtman(false);
			
			$("#seal_knd_cd").val("");
			$("#apl_out_yn").attr("checked",false);
			toggleAplOutYN(false);
		}
	}
	
	/**
	* 인장반출 필드 활성/비활성화(초기화)
	*/			
	function toggleAplOutYN(offSet){		
		
		var imageUrl  = "/script/secfw/jquery/calendar/ico_calendar.gif";
		$("#apl_ymd_from").next().remove();		
		$("#apl_ymd_to").next().remove(); 
		
		if(offSet){
			$("#apl_seal_no").attr("disabled",false);
			$("#apl_ymd_from").attr("disabled",false);
			$("#apl_ymd_to").attr("disabled",false);
			
  			initCal("apl_ymd_from");
			initCal("apl_ymd_to");  
						
		} else {
			$("#apl_seal_no").attr("disabled",true);
			$("#apl_ymd_from").attr("disabled",true);
			$("#apl_ymd_to").attr("disabled",true);
			
 			$("#apl_ymd_from").after($("<img src='"+ imageUrl +"' class=''>"));
			$("#apl_ymd_to").after($("<img src='"+ imageUrl +"' class=''>"));
			
			$("#apl_seal_no").val("");
			$("#apl_ymd_from").val("");
			$("#apl_ymd_to").val("");
		}
	}
	
	/**
	* 증명서류발급신청 필드 활성/비활성화
	*/
	function toggleDocYN(offSet){				
		if(offSet){
			$("#seal_scr input:text" ).each(function(index){     
		         $(this).attr("disabled" ,false);
		    });
			toggle_docman(true);
		} else {
			$("#seal_scr input:text" ).each(function(index){     
		         $(this).attr("disabled" ,true);
		    });
			toggle_docman(false);
 			$("#seal_scr input:text" ).each(function(index){     
		         $(this).val("");
		    }); 
		}
	}			
	
	/**
	* 날인담당자 필드 활성/비활성화
	*/
	function toggle_ffmtman(offSet){
		
		var handler = function() {
			searchSealPerson('SC0101','');
		};
		
		var alert_handler = function() {
			alert('<spring:message code="clm.field.signmng.alertmsg1" />');  // 날인신청에 체크를 먼저 하셔야 합니다.
		};
				
		if(offSet){
			$("#seal_ffmtman_search_nm").attr("disabled" ,false);		
			$("#seal_ffmtman_search_img").unbind('click');
			$('#seal_ffmtman_search_img').bind('click',handler);
		} else {
			$("#seal_ffmtman_search_nm").val("");
			$("#seal_ffmtman_search_nm").attr("disabled" ,true);			
			$("#seal_ffmtman_search_img").unbind('click');
			$('#seal_ffmtman_search_img').bind('click',alert_handler);
			$("#ffmtman").html("");
			$("#ffmtman_td input:hidden" ).each(function(index){     
		         $(this).val("");
		    });
		}
	}
	
	/**
	* 증명서류 발급 담당자 필드 활성/비활성화
	*/
	function toggle_docman(offSet){
		
		var handler = function() {
			searchSealPerson('SC0102','');
		};
		
		var alert_handler = function() {
			alert('<spring:message code="clm.field.signmng.alertmsg2" />');  // 증명서류에 체크를 먼저 하셔야 합니다.
		};
				
		if(offSet){
			$("#doc_issuer_search_nm").attr("disabled" ,false);	
			$("#doc_issuer_search_img").unbind('click');
			$('#doc_issuer_search_img').bind('click',handler);
		} else {
			$("#doc_issuer_search_nm").val("");
			$("#doc_issuer_search_nm").attr("disabled" ,true);			
			$("#doc_issuer_search_img").unbind('click');
			$('#doc_issuer_search_img').bind('click',alert_handler);
			$("#docman").html("");
			$("#doc_issuer_td input:hidden" ).each(function(index){     
		         $(this).val("");
		    });
		}
	}
	
	/* 숫자체크 */
	var prevInput = true;
	function checkItemNum(input)
	{
		var temp_input = input.value;
		
		if (temp_input=='') return; 	
		if (isNaN(input.value) == true)
		{
			prevInput	= false;
			alert('<spring:message code="clm.field.signmng.alertmsg3" />');  // 숫자만 입력가능합니다.
			input.focus();
			return;
		} 		

		prevInput	= true;
	}
	
	/**
	* 첨부파일창 load
	*/
	function initPage(){
		
		var frm = document.frm;	
	    frm.target          = "fileList";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.submit();   
	}
	
	/*
	 * 날인담당 /증명서류 발급담당 팝업 
	 */
	function searchSealPerson(srch_type_gbn,srch_cntnt) {
		var frm 		= document.frm;
		// PopUpWindowOpen('', 800, 450, true, "PopUpSealPerson");
		var top_xp  = window.screenLeft;
        var top_yp  = window.screenTop;
		window.open("", "PopUpSealPerson", "left="+top_xp+ ", top="+top_yp+", width=800, height=450, menubar=no, directories=no, resizeble=no, status=no, scrollbars=no");
		
		frm.srch_type_gbn.value = srch_type_gbn;
		frm.srch_cntnt.value = srch_cntnt;	
		frm.target = "PopUpSealPerson";
	    frm.action = "<c:url value='/clm/manage/chooseSealPerson.do' />";
	    frm.method.value = "forwardChooseSealPersonPopup";
	    frm.submit();   
	}
	
	//날인자 셋팅
	function setSealPerson(obj) {
		$('#ffmtman').html('');
		$('#ffmtman').append('&nbsp;&nbsp;' + obj);
	}
	
	//증명서류 발급자 셋팅
	function setDocPerson(obj) {
		$('#docman').html('');
		$('#docman').append('&nbsp;&nbsp;' + obj);
	}
	
	/**
	* form 체크
	*/	
	function checkForm(){
		
		var frm = document.frm;
		var check_flag = true;
		
		var seal_yn = true;
		var apl_out_yn = true;
		var doc_yn = true;
		
		if($("#seal_yn").attr("checked"))
			seal_yn = false;
		
		if($("#apl_out_yn").attr("checked"))
			apl_out_yn = false;
		
		if($("#doc_yn").attr("checked"))
			doc_yn = false;	
		
		if($("#apl_cls").val()=='AT0104'){
			alert('<spring:message code="clm.field.signmng.alertmsg4" />');  // 신청유형:계약용(법무시스템) 에 대한 입력 권한이 없습니다.
			$("#apl_cls").focus();
			return false;
		}		
		
		if(seal_yn && doc_yn){
			alert('<spring:message code="clm.field.signmng.alertmsg5" />');  // 날인 신청 혹은 증명서류발급 신청 입력을 해 주십시오.
			check_flag = false;
		}
		
		// 날인신청 체크 박스를 체크한 경우
		if(!seal_yn){		
			
			if($("#seal_knd_cd").val()==''){
				alert('<spring:message code="clm.field.signmng.alertmsg6" />');  // 날인종류를 선택해 주십시오.
				return false;
			}
			
			// 인장 반출 신청 체크 박스를 체크한 경우
			if(!apl_out_yn){			
				if($("#apl_seal_no").val()==''){
					alert('<spring:message code="clm.field.signmng.alertmsg7" />');  // 반출인장번호를 입력해 주십시오.
					return false;
				}
				if($("#apl_ymd_from").val()=='' || $("#apl_ymd_to").val()==''){
					alert('<spring:message code="clm.field.signmng.alertmsg8" />');  // 반출기간을 입력해 주십시오.
					return false;
				}				
			}
			
			// 날인담당자 입력체크
			if($("#seal_ffmtman_id").val()==''){
				alert('<spring:message code="clm.field.signmng.alertmsg9" />');  // 날인담당자를 입력해 주십시오.
				return false;
			}
		}
		
		var doc_cnt = 0;
		
		// 증명서류 발급 신청 체크 박스를 체크한 경우
		if(!doc_yn){			
			// 각 문서의 신청매수가 공백이 아닐 경우 값을 더한다.
			$("#seal_scr input:text" ).each(function(index){     
		        if($(this).attr("alt")=='doc_num'){
		        	
		        	if($(this).val()!='')
		        		doc_cnt = doc_cnt + Number($(this).val());       	
		        	
		        }				
		    });
			
			if(doc_cnt==0){
				alert('<spring:message code="clm.field.signmng.alertmsg10" />');  // 증명서류의 발급 매수를 입력해 주십시오.
				return false;
			}
			
			// 증명서류 발급 담당자 입력체크
			if($("#doc_issuer_id").val()==''){
				alert('<spring:message code="clm.field.signmng.alertmsg11" />');  // 증명서류 발급 담당자를 입력해 주십시오.
				return false;
			}
		}
		
		var doc4_cnt = 0; // 사용인감계 체크		
		doc4_cnt =  Number($("#doc4").val());
		
		if(doc4_cnt > 0 && seal_yn){ // 
			alert('<spring:message code="clm.field.signmng.alertmsg12" />');  // 사용인감계 증명서류 발급 신청인 경우 날인 신청을 해야 합니다.
			return false;		
		}		

		if(doc4_cnt > 0 && $("#use_summ").val()==''){			
			alert('<spring:message code="clm.field.signmng.alertmsg13" />');  // 사용인감계 증명서류 발급 신청인 경우 사용용도를 입력해 주십시오.
			return false;
		}		
				
		return check_flag;
	}

	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		
		var frm = document.frm;
		var confirmMessage = "";
		var pumui_flag = frm.go_pumui.value;		

		//저장
		if(flag == "insert"){				
 			
			if(pumui_flag == "Y"){
				confirmMessage = "<spring:message code='clm.field.signmng.alertmsg14' />"; // 품의 상신 하시겠습니까?
			} else {
				confirmMessage = "<spring:message code='clm.field.signmng.alertmsg15' />"; // 저장하시겠습니까?
			} 
		    
		    //유효성 체크
		    if(validateForm(frm)){
		    	
				if(!checkForm()){
					document.frm.go_pumui.value='N';
		    		return;	
				}                 

                if(checkForm() && confirm(confirmMessage)){ 
		    	
		    		//첨부파일 업로드 실행
					fileList.UploadFile(function(uploadCount){							
						if(uploadCount == -1){
							// initPage();
							alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
							return;
						}
		                
		                /*  첨부 파일은 날인 신청을 했을 경우에만 필수 체크를 하게 됨.
						if (uploadCount == 0) { //첨부파일 필수일 경우
		                    alert("<spring:message code='secfw.msg.error.fileNon' />");
		                    return;
		                }
		                */
		                						
						if (uploadCount < 0) { //지정된 형식 이외에 파일을 추가한 경우
							return;
						} 
                    	
                    	// 체크박스 벨류처리
                    	if($("#seal_yn").attr("checked")){
                    		$("#seal_yn").val("Y");
                    		
                    		//날인 신청 체크시에 첨부파일 필수
                    		if (uploadCount == 0) { //첨부파일 필수일 경우
    		                    alert("<spring:message code='secfw.msg.error.fileNon' />");
    		                    return;
    		                }  
                    		
                    	} else {
                    		$("#seal_yn").val("N");
                    	}                    	
                    	if($("#doc_yn").attr("checked")){
                    		$("#doc_yn").val("Y");
                    	} else {
                    		$("#doc_yn").val("N");
                    	}                    	
                    	if($("#apl_out_yn").attr("checked")){
                    		$("#apl_out_yn").val("Y");
                    	} else {
                    		$("#apl_out_yn").val("N");
                    	}
                    	
                		// 날짜 체크
                		check_date();        		
                		
                    	viewHiddenProgress(true) ;
    		    		frm.method.value = "saveSign";
    					frm.target = "_self";
    					frm.action = "<c:url value='/clm/sign/signManage.do' />";
    			    	frm.submit();	                 
					});
	    	
		  		} else {                    	             	
                	frm.go_pumui.value='N';             	
                	return;	
		    	}
		   } 		    
			
		// 목록
		} else if(flag == "list"){			
			viewHiddenProgress(true);
			frm.method.value = "listSign";
			//frm.method.value = frm.apl_sqn.value==("") ? "listSign" : "listSign";
			frm.action = "<c:url value='/clm/sign/signManage.do' />";
			frm.target = "_self";
			frm.submit();					
		} 
	}	
	
	/*
	 * 팝업오픈-모달리스
	 */
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
	
	/**
	* 저장시 날짜 form 에서 "-" 를 삭제 
	*/
	function check_date(){
		$('*').attr('class',function(index){
			if($(this).attr("class") == 'text_calendar'){
				$(this).val($(this).val().replace(/-/gi,''));					
			}
		}); 
	}
	
//-->
</script>

</head>
<body>
<!-- Calendar  -->
<div id='popCal' style='position: absolute; visibility: hidden; ridge; width: 10; z-index:1'>
<iframe name="popFrame" src="<c:url value='/secfw/popCalendar.do?method=goPopCalendar' />" frameborder="0" scrolling="no" width="196" height="188"></iframe></div>
<script event=onclick() for=document> 
    if(popCal.style.visibility == "visible"){
      popCal.style.visibility="hidden";
    }
</script>

<div id="wrap">	
	<!-- container -->
<div id="container">
	<!-- Location -->
	<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" border="0" alt="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
	<!-- //Location -->	
	<!-- title -->
	<div class="title">
		<c:choose>
			<c:when test="${command.apl_sqn == ''}">
				<h3><spring:message code="clm.field.signmng.apltab" /><!-- 날인/증명서류 신청 --></h3>
			</c:when>
		<c:otherwise>
			<h3><spring:message code="clm.field.signmng.apltab" /><!-- 날인/증명서류 신청 --></h3>
				</c:otherwise>
		</c:choose>		
	</div>
	<!-- //title -->
	<!-- content -->
	<div id="content">
		<div id="content_in">
		
		<form:form name="frm" id="frm" method="post" autocomplete="off">
		<input type="hidden" name="method"   value="" />
		<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
		<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
		<input type="hidden" id="forwardFrom" name="forwardFrom"  value="<c:out value='${command.forwardFrom}'/>" />
		
		<!-- search form -->
		<input type="hidden" name="srch_apl_y"   value="<c:out value='${command.srch_apl_y}'/>" />  
		<input type="hidden" name="srch_apl_m"   value="<c:out value='${command.srch_apl_m}'/>" />
		<input type="hidden" name="srch_apl_prev30d"   value="<c:out value='${command.srch_apl_prev30d}'/>" />
		<input type="hidden" name="srch_seal_rqst_status"   value="<c:out value='${command.srch_seal_rqst_status}'/>" />
		<input type="hidden" name="srch_seal_knd_cd"   value="<c:out value='${command.srch_seal_knd_cd}'/>" />  
		<input type="hidden" name="srch_seal_ffmt_status"   value="<c:out value='${command.srch_seal_ffmt_status}'/>" />
		<input type="hidden" name="srch_title"   value="<c:out value='${command.srch_title}'/>" />
		<input type="hidden" name="srch_apl_cls"   value="<c:out value='${command.srch_apl_cls}'/>" />
		<input type="hidden" name="srch_doc_no"   value="<c:out value='${command.srch_doc_no}'/>" />  
		<input type="hidden" name="srch_doc_issue_status"   value="<c:out value='${command.srch_doc_issue_status}'/>" />
		<input type="hidden" name="srch_sbm"   value="<c:out value='${command.srch_sbm}'/>" />
		<input type="hidden" name="srch_seal_rqstman_nm"   value="<c:out value='${command.srch_seal_rqstman_nm}'/>" />
		<input type="hidden" name="srch_seal_ffmtman_nm"   value="<c:out value='${command.srch_seal_ffmtman_nm}'/>" />  
		<input type="hidden" name="srch_seal_ffmtday_start"   value="<c:out value='${command.srch_seal_ffmtday_start}'/>" />
		<input type="hidden" name="srch_seal_ffmtday_end"   value="<c:out value='${command.srch_seal_ffmtday_end}'/>" />
		<input type="hidden" name="srch_apl_seal_no"   value="<c:out value='${command.srch_apl_seal_no}'/>" />
		
		<!--메인 필드 값들-->
		<input type="hidden" name="apl_sqn" id="apl_sqn" value="<c:out value='${command.apl_sqn}'/>"  />
		
		<!-- 날인담당자 팝업-->
		<input type="hidden" name="srch_type_gbn" id="srch_type_gbn" value="" />
		<input type="hidden" name="srch_cntnt_type" id="srch_cntnt_type" value="user_nm" />
		<input type="hidden" name="srch_cntnt" id="srch_cntnt" value="" />
		
		<!-- 첨부파일정보 시작 -->
		<input type="hidden" name="fileInfos"   value="" />
		<input type="hidden" name="file_bigclsfcn"  value="N005" />
		<input type="hidden" name="file_midclsfcn" 	value="N00501" />
		<input type="hidden" name="file_smlclsfcn"  value="" />
		<input type="hidden" name="ref_key"     value="<c:out value='${command.apl_sqn}'/>" />
		
		<c:choose>
		<c:when test="${command.apl_sqn == ''}">
			<input type="hidden" name="view_gbn"    value="upload" />
			</c:when>
			<c:otherwise>
			<input type="hidden" name="view_gbn" 	value="modify" />
			</c:otherwise>
		</c:choose>
		
		<!--상신버튼 -->
		<input type="hidden" name="go_pumui" id="go_pumui" value=""  />
	
		    <!-- button -->
			<div class="tR mt5">
				<span class="btn_all_w"><span class="sangsin"></span><a href="javascript:document.frm.go_pumui.value='Y';pageAction('insert');"><spring:message code="clm.page.button.contract.cosultationapproval" /><!-- 품의상신 --></a></span>
				<span class="btn_all_w"><span class="tsave"></span><a href="javascript:pageAction('insert');"><spring:message code="clm.page.msg.manage.tmpSave" /><!--임시저장--></a></span>
				<span class="btn_all_w"><span class="list"></span><a href="javascript:pageAction('list');"><spring:message code="clm.page.button.list" /><!--목록--></a></span>
			</div>
			<!-- //button -->				
				<table>
					<colgroup>
						<col width="48%" />
						<col width="2%" />
						<col width="50%" />
					</colgroup>
					<tr>
					    <td id="l_tbl" class='vt'>
					    
					   
						<div class="title_basic mt0">
							<h4><spring:message code="clm.field.signmng.basicinfo" /><!-- 기본내역 --></h4>
						</div>
						
						<table class="list_basic">
							<colgroup>
								<col width="27%" />
								<col width="83%" /> <!-- width size 변경 신성우 2014-04-24 -->
							</colgroup>							
							<tr>
								<th ><spring:message code="clm.field.signmng.title" /><!-- 건명 --></th>
								<td class='overflow'><input type='text' class='text w_99' id="title" name="title" mexlength="99" required  alt="<spring:message code="clm.field.signmng.title" />"  value="<c:out value='${lom.title}' escapeXml="false" />" /></td>
							</tr>
							<tr>
								<th ><spring:message code="clm.field.signmng.towhere" /><!-- 제출처 --></th>
								<td class='overflow'><input type='text' class='text w_99' id="sbm" name="sbm" mexlength="66" required  alt="<spring:message code="clm.field.signmng.towhere" />" value="<c:out value='${lom.sbm}' escapeXml="false" />" /></td>
							</tr>
							<tr>
								<th ><spring:message code="clm.field.signmng.reqman" /><!-- 신청자 --></th>
							    <td><c:out value='${command.reg_nm}'/><c:if test="${empty command.reg_nm}"><c:out value='${lom.seal_rqstman_txt}' /></c:if></td>
							</tr>
							<tr>
							    <th ><spring:message code="clm.field.signmng.reqday" /><!-- 신청일자 --></th>
								<td><c:out value='${command.reg_dt}'/><c:if test="${empty command.reg_dt}"><c:out value='${lom.reg_dt}' /></c:if></td>
							</tr>
							<tr>
								<th ><spring:message code="clm.field.signmng.apltype" /><!-- 신청유형 --></th>
								<td>
									<select class="w_70" id="apl_cls" name="apl_cls" required  alt="<spring:message code="clm.field.signmng.apltype" />" ></select>
								</td>
							</tr>
							<tr>
								<th ><spring:message code="clm.field.signmng.detaildsc" /><!-- 세부내용 --></th>
								<td>
								<c:choose>
									<c:when test="${command.apl_sqn == ''}">
										<textarea id="txt"  name="txt"  class="text_area_full01"  onkeyup="chkMsgLength(3500,this,txt_Byte);" style="height:79px;" required  alt="<spring:message code="clm.field.signmng.detaildsc" />" ></textarea>
										<input type ="hidden" id="txt_Byte" style="padding-left: 60;">
							
									</c:when>
									<c:otherwise>
										<textarea id="txt"  name="txt"  class="text_area_full01" onkeyup="chkMsgLength(3500,this,txt_Byte);" style="height:79px;" required  alt="<spring:message code="clm.field.signmng.detaildsc" />" ><c:out value='${lom.txt}' escapeXml="false" /></textarea>
								<input type ="hidden" id="txt_Byte" style="padding-left: 60;">												
									</c:otherwise>
								</c:choose>
									
								</td>
							</tr>
							<tr>
								<th ><spring:message code="las.page.field.lawservice.attach" /><!-- 첨부파일--></th>
								<td class='vt'>
									<iframe src="<c:url value='/clm/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="85px" leftmargin="0" topmargin="0" scrolling="auto" style='margin-top:2px;' ></iframe>
									<div class='ico_alert01 mb3'><spring:message code="clm.field.signmng.apprmsg1" /><!-- 날인 대상 문서 첨부 필수 --><br><spring:message code="clm.field.signmng.apprmsg2" /><!-- 파일명에 포함된 '&'기호는 삭제 후 첨부 하십시오 -->
</div>
								</td>
							</tr>
						</table>
						<!-- // 기본내역 -->
					    
					    </td>
					    <td> &nbsp;</td>
						<td id="r_tbl" class='vt'>
						 
						<!-- 신청내역 -->
						<div class="title_basic mt0">
							<h4><spring:message code="clm.field.signmng.aplinfo" /><!-- 신청내역 --></h4>
						</div>
						
						<table class="list_basic">
							<colgroup>
								<col width="26%" />
								<col width="74%" /> <!-- width size 변경 신성우 2014-04-24 -->
							</colgroup>
							<tr>
								<th ><input type='checkbox' id="seal_yn" name="seal_yn" onclick="javascript:toggleSealYN(this.checked);" <c:if test="${lom.seal_yn eq 'Y'}">checked</c:if> ><spring:message code="clm.field.signmng.apl" /><!-- 날인신청 --></th>
								<td><spring:message code="clm.field.signmng.knd" /><!-- 날인종류 --> <select class="w_50 mL10" id="seal_knd_cd" name="seal_knd_cd" ></select></td>
							</tr>
							<tr>
								<th ><input type='checkbox' id="apl_out_yn" name="apl_out_yn" onclick="javascript:toggleAplOutYN(this.checked);" <c:if test="${lom.apl_out_yn eq 'Y'}">checked</c:if> /><spring:message code="clm.field.signmng.aploutreq" /><!-- 인장반출신청 --></th>
								<td style='height:73px' >
									 <spring:message code="clm.field.signmng.aplno" /><!-- 반출인장번호 -->
 <input type='text' class='text w_50 mL10' id="apl_seal_no" name="apl_seal_no" mexlength="30" value="<c:out value='${lom.apl_seal_no}' escapeXml="false" />" /><br>
									<div class='ico_alert01 mt5'><spring:message code="clm.field.signmng.apprmsg3" /><!-- 회사 외부로의 인장반출을 신청합니다.[임원결재필요] --><br>
									<spring:message code="clm.field.signmng.apprmsg4" /><!-- 날인담당자에게 인장번호 확인 후 신청하시기 바랍니다. --></div>
								</td>
							</tr>
							<tr>
								<th ><spring:message code="clm.field.signmng.outfromto" /><!-- 반출기간 --></th>
								<td><div id="apl_out_div" >
									<input type="text" id="apl_ymd_from" name="apl_ymd_from" class="text_calendar" value="<c:out value='${lom.apl_ymd_from_txt}'/>" /> ~
									<input type="text" id="apl_ymd_to" name="apl_ymd_to" class="text_calendar" value="<c:out value='${lom.apl_ymd_to_txt}'/>" /></div>
								</td>
							</tr>
							<tr>
								<th class='rightline'><input type='checkbox'  id="doc_yn" name="doc_yn" onclick="javascript:toggleDocYN(this.checked);" <c:if test="${lom.doc_yn eq 'Y'}">checked</c:if> /> <spring:message code="clm.field.signmng.doc" /><!-- 증명서류 --></th>
								<td class='zero' >
									<div class='seal_scr' id="seal_scr" style='height:118px'>
										<dl class='seal_doc'>
											<dt><spring:message code="clm.field.signmng.cert1" /><!-- 법인인감증명서 --></dt>
											<dd><input type='text' class='text_02 tC' id="doc1" name="doc1"  onBlur="javascript:checkItemNum(this);" alt="doc_num" value="<c:out value='${lom.doc1}'/>" /> <spring:message code="clm.field.signmng.adddoccnt" /></dd><!-- 부 -->
										</dl>
										<dl class='seal_doc'>
											<dt><spring:message code="clm.field.signmng.cert2" /><!-- 등기부등본 --></dt>
											<dd><input type='text' class='text_02 tC'  id="doc2" name="doc2"  onBlur="javascript:checkItemNum(this);" alt="doc_num" value="<c:out value='${lom.doc2}'/>" /> <spring:message code="clm.field.signmng.adddoccnt" /></dd><!-- 부 -->
										</dl>
										<dl class='seal_doc'>
											<dt><spring:message code="clm.field.signmng.cert3" /><!-- 등기부초본 --></dt>
											<dd><input type='text' class='text_02 tC'  id="doc3" name="doc3" onBlur="javascript:checkItemNum(this);" alt="doc_num" value="<c:out value='${lom.doc3}'/>" /> <spring:message code="clm.field.signmng.adddoccnt" /></dd><!-- 부 -->
										</dl>
										<dl class='seal_doc'>
											<dt>
												<spring:message code="clm.field.signmng.cert4" /><!-- 사용인감계 --> <span><spring:message code="clm.field.signmng.apprmsg5" /><!-- 날인신청이 필요합니다. --></span><br>
												<span class='use '><spring:message code="clm.field.signmng.usem" /><!-- 사용용도 --> <input type='text' class='text_02 mL5' style='width:60%;' id="use_summ" name="use_summ"  value="<c:out value='${lom.use_summ}' escapeXml="false" />"></span>
											</dt>
											<dd><input type='text' class='text_02 tC'  id="doc4" name="doc4" onBlur="javascript:checkItemNum(this);" alt="doc_num" value="<c:out value='${lom.doc4}'/>" /> <spring:message code="clm.field.signmng.adddoccnt" /></dd><!-- 부 -->
										</dl>
										<dl class='seal_doc'>
											<dt><spring:message code="clm.field.signmng.cert5" /><!-- 일반위임장 --></dt>
											<dd><input type='text' class='text_02 tC'  id="doc5" name="doc5" onBlur="javascript:checkItemNum(this);" alt="doc_num" value="<c:out value='${lom.doc5}'/>" /> <spring:message code="clm.field.signmng.adddoccnt" /></dd><!-- 부 -->
										</dl>
										<dl class='seal_doc'>
											<dt><spring:message code="clm.field.signmng.cert6" /><!-- 공증위임장 --></dt>
											<dd><input type='text' class='text_02 tC'  id="doc6" name="doc6" onBlur="javascript:checkItemNum(this);" alt="doc_num" value="<c:out value='${lom.doc6}'/>" /> <spring:message code="clm.field.signmng.adddoccnt" /></dd><!-- 부 -->
										</dl>
										<dl class='seal_doc bottom_noline'>
											<dt><spring:message code="clm.field.signmng.cert7" /><!-- 대표이사신분증 --></dt>
											<dd><input type='text' class='text_02 tC'  id="doc7" name="doc7" onBlur="javascript:checkItemNum(this);" alt="doc_num" value="<c:out value='${lom.doc7}'/>" /> <spring:message code="clm.field.signmng.adddoccnt" /></dd><!-- 부 -->
										</dl>
									</div>
								</td>	
							</tr>
							<tr>
								<th><spring:message code="clm.field.signmng.doc" /><!-- 증명서류 --> <spring:message code="clm.page.field.decidearbitrarilyre.note" /><!-- 비고 --></th>
								<td>
									<input type='text' class='text w_50 mL10' id="doc_scrtxt" name="doc_scrtxt" mexlength="30" value="<c:out value='${lom.doc_scrtxt}' escapeXml="false" />" />
								</td>
							</tr>
							<tr>
								<th ><spring:message code="clm.field.signmng.desc" /><!-- 용어설명 --></th>
								<td class='zero'>
									<div class='seal_scr font_11' style='padding:4px 10px; height:70px;'>
										<span class='title01'>1. <spring:message code="clm.field.signmng.adddoc1" /><!-- 법인인감 --></span> - <spring:message code="clm.field.signmng.apprmsg6" /><!-- 법원에 등록한 대표이사 인장. 법인인감증명서로 진위를 증명함. --><br> 
										<span class='title01'>2. <spring:message code="clm.field.signmng.adddoc2" /><!-- 사용인감 --></span> -<spring:message code="clm.field.signmng.apprmsg7" /><!-- 법원에 등록하지 않은 대표이사 인장.사용인감계 및 법인인감증명서로 진위 증명 시 법인인감과 동일한 효력.  --><br>
										<span class='title01'>3. <spring:message code="clm.field.signmng.adddoc3" /><!-- 사인 --></span> - <spring:message code="clm.field.signmng.apprmsg8" /><!-- 회사 명의의 사각형 인장. 주로 회사 명의의 임명장, 상장 등에 사용. --><br>
									</div>
								</td>
							</tr>
						</table>
						<!-- // 신청내역 -->
						 
						 </td>
					</tr>
				</table>	
				
				<!-- 담당자내역 -->
				<div class="title_basic mt20">
					<h4><spring:message code="clm.field.signmng.manhis" /><!-- 담당자내역 --></h4>
				</div>
				
				<table class="list_basic">
					<colgroup>
						<col width="13%" />
						<col width="87%" />
					</colgroup>
					<tr>
						<th><spring:message code="clm.field.signmng.aplprcman" /><!-- 날인담당자 --></th>
						<td id="ffmtman_td" >
						<input type="text"   name="seal_ffmtman_search_nm" id="seal_ffmtman_search_nm" value="" style="width:110px" class="text_search" onKeyDown="if(event.keyCode==13){event.returnValue = false;javascript:searchSealPerson('SC0101',this.value);}"/><img id="seal_ffmtman_search_img" src="<%=IMAGE%>/icon/ico_search.gif" class="cp" alt="search" />
						<span id="ffmtman">&nbsp;&nbsp;<c:out value='${lom.seal_ffmtman_txt}' /></span>
						<input type="hidden" name="seal_ffmtman_id" id="seal_ffmtman_id" value="<c:out value='${lom.seal_ffmtman_id}' />" /> 
						<input type="hidden" name="seal_ffmtman_nm" id="seal_ffmtman_nm" value="<c:out value='${lom.seal_ffmtman_nm}' />" />
						<input type="hidden" name="seal_ffmt_dept_cd" id="seal_ffmt_dept_cd" value="<c:out value='${lom.seal_ffmt_dept_cd}' />"/>
						<input type="hidden" name="seal_ffmt_dept_nm" id="seal_ffmt_dept_nm" value="<c:out value='${lom.seal_ffmt_dept_nm}' />"/>
						<input type="hidden" name="seal_ffmtman_jikgup_nm" id="seal_ffmtman_jikgup_nm" value="<c:out value='${lom.seal_ffmtman_jikgup_nm}' />"/>

						</td>
					</tr>
					<tr>
						<th><spring:message code="clm.field.signmng.docprcman" /><!-- 증명서류 발급자 --></th>
						<td id="doc_issuer_td">
						<input type="text"   name="doc_issuer_search_nm" id="doc_issuer_search_nm" value="" style="width:110px" class="text_search" onKeyDown="if(event.keyCode==13){event.returnValue = false;javascript:searchSealPerson('SC0102',this.value);}"/><img id="doc_issuer_search_img" src="<%=IMAGE%>/icon/ico_search.gif"  class="cp" alt="search" />
						<span id="docman">&nbsp;&nbsp;<c:out value='${lom.doc_issuer_txt}' /></span>
						<input type="hidden" name="doc_issuer_id" id="doc_issuer_id" value="<c:out value='${lom.doc_issuer_id}' />"  />
						<input type="hidden" name="doc_issuer_nm" id="doc_issuer_nm" value="<c:out value='${lom.doc_issuer_nm}' />"  />
						<input type="hidden" name="doc_issuer_dept_cd" id="doc_issuer_dept_cd" value="<c:out value='${lom.doc_issuer_dept_cd}' />"/>
						<input type="hidden" name="doc_issuer_dept_nm" id="doc_issuer_dept_nm" value="<c:out value='${lom.doc_issuer_dept_nm}' />"/>
						<input type="hidden" name="doc_issuer_jikgup_nm" id="doc_issuer_jikgup_nm" value="<c:out value='${lom.doc_issuer_jikgup_nm}' />"/>
						  
						</td>
					</tr>
				</table>
				<!-- // 담당자내역 -->
		
		</form:form>
		</div>
		<!-- //content_in -->	
	</div>
	<!-- //content -->

	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</div>
<!-- //container -->
</div>
<!-- //wrapr -->

<!-- footer  -->
<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- // footer -->

</body>
</html>