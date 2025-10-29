
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.Token" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="org.springframework.web.servlet.support.RequestContext"%>
<%@ page import="com.sec.common.util.ClmsDataUtil" %>
<%@ page import="com.sds.secframework.common.dto.CommonForm" %>

<%--
/**
 * 파     일     명    : ApprovalInsertAS_p.jsp
 * 프 로 그  램  명    : 결재내역  등록 페이지
 * 설            명    : 
 * 작     성     자    : 
 * 작     성     일    : 2013.05
 */
--%>

<%
	String approvalModuleID         = StringUtil.bvl((String)request.getAttribute("approvalModuleID"),"");
	String approvalMisID            = StringUtil.bvl((String)request.getAttribute("approvalMisID"),"");
	String approvalDrafterID        = StringUtil.bvl((String)request.getAttribute("approvalDrafterID"),"");
	String approvalTitle            = StringUtil.bvl((String)request.getAttribute("approvalTitle"),"");
	String approvalContent          = StringUtil.bvl((String)request.getAttribute("approvalContent"),"");
	String approvalHtmlGbn          = StringUtil.bvl((String)request.getAttribute("approvalHtmlGbn"),"1");
	String approvalFileInfos        = StringUtil.bvl((String)request.getAttribute("approvalFileInfos"),"");
	String approvalDrafterUserPath  = StringUtil.bvl((String)request.getAttribute("approvalDrafterUserPath"),"");
	String approvalDrafterUserRight = StringUtil.bvl((String)request.getAttribute("approvalDrafterUserRight"),"");
	String approvalPostProcess      = StringUtil.bvl((String)request.getAttribute("approvalPostProcess"),"");
	String approvalEncoding         = StringUtil.bvl((String)request.getAttribute("approvalEncoding"),"UTF-8");
	
	CommonForm command = (CommonForm)request.getAttribute("form");
	String menuId         = StringUtil.bvl((String)command.getMenu_id(),"");
	
	//사용자 로케일
	Locale lc = new RequestContext(request).getLocale();
	String locale = StringUtil.bvl(lc.getLanguage(), "en");
	String localeDisplayType = "";
	
	if("en".equals(locale)) {
		localeDisplayType = "Eng";
	}
	
	String approval_option 				= StringUtil.bvl((String)request.getAttribute("approval_option"),"");
	String ref_key 						= StringUtil.bvl((String)request.getAttribute("ref_key"),""); 
	String apprvl_clsfcn				= StringUtil.bvl((String)request.getAttribute("apprvl_clsfcn"),"");
	String approvalAgreeUserPath 		= StringUtil.bvl((String)request.getAttribute("approvalAgreeUserPath"),"");
	String approvalAgreeUserRight 		= StringUtil.bvl((String)request.getAttribute("approvalAgreeUserRight"),"");
	String approvalAuthorizerUserPath 	= StringUtil.bvl((String)request.getAttribute("approvalAuthorizerUserPath"),"");
	String approvalAuthorizerUserRight 	= StringUtil.bvl((String)request.getAttribute("approvalAuthorizerUserRight"),"");
%>

<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title><spring:message code="secfw.page.field.approval.approval" /></title>
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet">
<link href="<%=CSS%>/popup.css"   type="text/css" rel="stylesheet">
<link href="<c:url value='/style/secfw/common.css' />" rel='stylesheet' type='text/css'>
<link href="<c:url value='/style/secfw/approval_${langCd}.css' />" rel='stylesheet' type='text/css'>
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script type="text/javascript" src="/crosseditor/js/namo_scripteditor.js"></script>
<script language="javascript">
<!--

var V_APPROVAL_NM     = "<spring:message code='secfw.page.field.approval.approval' />";
var V_CONSENT_NM      = "<spring:message code='secfw.page.field.approval.consent' />";
var V_POSTAPPROVAL_NM = "<spring:message code='secfw.page.field.approval.postApproval' />";
var V_NOTIFY_NM       = "<spring:message code='secfw.page.field.approval.notify' />";

var DEFAULT_CHANGE_LINE = '0'; //경로변경
var DEFAULT_MODIFY_TEXT = '0'; //수정가능
var DEFAULT_ARBITRATY   = '0'; //전결가능

//전자결재 결재경로 코드
var SUBMIT_CODE 		= 0; 
var APPROVAL_CODE 		= 1; // 결재
var AGREEMENT_CODE 		= 2; // 합의
var POST_APPROVAL_CODE 	= 3; // 후결
var NOTIFICATION_CODE 	= 9; // 통보
var PARALLELAPP_CODE 	= 7; // 병렬결재
var PARALLELAGREE_CODE 	= 4; // 병렬합의

var encodeItem = new Array(27);
encodeItem[0]  = new Array ("Arabic(Windows)",             "ISO-8859-6"    );
encodeItem[1]  = new Array ("Baltic(Windows)",             "WINDOWS-1257"  );
encodeItem[2]  = new Array ("Baltic(ISO)",                 "ISO-8859-4"    );
encodeItem[3]  = new Array ("Central European(ISO)",       "WINDOWS-1250"  );
encodeItem[4]  = new Array ("Central European(Windows)",   "ISO-8859-2"    );
encodeItem[5]  = new Array ("Chinese Simplified(GB18030)", "GB18030"       );
encodeItem[6]  = new Array ("Chinese Simplified(GB2312)",  "GB2312"        );
encodeItem[7]  = new Array ("Chinese Simplified(HZ)",      "GB2312"        );
encodeItem[8]  = new Array ("Chinese Traditinal(Big5)",    "BIG5"          );
encodeItem[9]  = new Array ("Cyrillic(ISO)",               "ISO-8859-5"    );
encodeItem[10] = new Array ("Cyrillic(KOI8-R)",            "KOI8-R"        );
encodeItem[11] = new Array ("Cyrillic(KOI8-U)",            "KOI8-R"        );
encodeItem[12] = new Array ("Cyrillic(Windows)",           "WINDOWS-1251"  );
encodeItem[13] = new Array ("Greek(ISO)",                  "ISO-8859-7"    );
encodeItem[14] = new Array ("Greek(Windows)",              "WINDOWS-1253"  );
encodeItem[15] = new Array ("Hebrew(Windows)",             "ISO-8859-8"    );
encodeItem[16] = new Array ("Korean(EUC)",                 "EUC-KR"        );
encodeItem[17] = new Array ("Japanese(SJIS)",              "SJIS"          );
encodeItem[18] = new Array ("Japanese(MS932)",             "MS932"         );
encodeItem[19] = new Array ("Japanese(JIS)",               "ISO-2022-JP"   );
encodeItem[20] = new Array ("Latin 9(ISO)",                "ISO-8859-15"   );
encodeItem[21] = new Array ("Thai(Windows)",               "WINDOWS-874"   );
encodeItem[22] = new Array ("Trukish(ISO)",                "ISO-8859-9"    );
encodeItem[23] = new Array ("Trukish(Windows)",            "WINDOWS-1254"  );
encodeItem[24] = new Array ("Vietnames(Windows)",          "WINDOWS-1258"  );
encodeItem[25] = new Array ("Western European(ISO)",       "WINDOWS-1252"  );
encodeItem[26] = new Array ("Unicode(UTF-8)",              "UTF-8"         );

/**
* - 페이지 초기화 
*/
$(document).ready(function(){

    var frm = document.frm;
    
    initPage();
    
    //2011.10.16심주완수정
    //팝업사이즈변경
    //window.resizeTo(1024,768);
    //센터위치
    MoveCenterWindow();
    
    $('#optionBlock').toggle();
	
	//결재구분-최초 결재 Radio버튼 선택
	$('#activityType1').attr('checked', true);

	//상신자 정보 세팅
	var drafterPathHtml = '<%=approvalDrafterUserPath%>';
	$('#receivers option').remove();
	$('#receivers').append(drafterPathHtml);
	
	//상신자 결재권한 세팅
	var drafterRightHtml = '<%=approvalDrafterUserRight%>';
	$('#receiversRight option').remove();
	$('#receiversRight').append(drafterRightHtml);

	//2011.10.20 심주완추가 결재자정보
	if("C03001"=="<%=apprvl_clsfcn%>") { 
		//결재자정보세팅
		var authorizerPathHtml = '<%=approvalAuthorizerUserPath%>';
		$('#receivers').append(authorizerPathHtml);	
	
		//결재자결재권한세팅
		var authorizerRightHtml = '<%=approvalAuthorizerUserRight%>';
		$('#receiversRight').append(authorizerRightHtml);
	}
	
	//2011.10.13 심주완추가 합의자정보


	
	//결재자 검색-ESB임직원 검색
	$("input[name=srchReceiverNm]").keypress(function(event){
		if(event.keyCode == "13") {
			searchUser();
			$("input[name=srchReceiverNm]").val('');
			return false;				
		}
	});

	//결재자 검색-ESB임직원 검색
	$('#srchReceiverBtn').click(function(){
		searchUser();
		return false;
	});

	//인코딩정보 세팅
	for(var encodeLoop=0; encodeLoop < encodeItem.length; encodeLoop++) {
		var eachItem = encodeItem[encodeLoop];
		var encodeValue = "<option value='" + eachItem[1] + "'>"
     		+ eachItem[0]+ "</option>";
		$('select[name=encoding]').append(encodeValue);
	}

	$('select[name=encoding] option')
		.each(function(){
			var optionValue = $(this).val();
			if(optionValue == '<%=approvalEncoding%>') {
				$(this).attr('selected', true);
			}
		});

	// 결재구분 변경
	$('input[name=activityType]').click(function(event){
		var target = event.target;
		var setValue = $(this).val();
		var setTextValue = $(this).attr('textValue');
		
		$('#receivers option:selected')
			.each(function(){

				var originValue = $(this).val().split("|");
				var originText  = $(this).text().split(" / ");

				var newValue = '';
				var newText  = '';

				var type    = originValue[0];
				var userId  = originValue[1];
				
				var pathFlag = true;	//결재 라인 변경 플래그 체크

				//1. 설정된 값과 설정할 값이 다를경우에만
				//2. 상신자가 아닐경우 변경
				if(setValue != type && userId != $('input[name=draft_user_id]').val()) {

					//구주요청에 의한 추가 tb_com_user의 approval_Y 값이 Y인 유저는 결재타입 변경불가
					if(originValue[12] == "Y" || originValue[13] == "APP_Y_ID" || originValue[12] == "M") {
						pathFlag = false;
						option = "LEGAL";
					}

					if(!pathFlag) {
						if(option=="LEGAL") {
							alert("<spring:message code='secfw.msg.approval.notModifyApprovor'/>");
							return;
						} 
					} else {
						if(originValue.length > 0) {
							originValue[0] = setValue;
							originText[0]  = setTextValue;
						}
		
						for(var loop=0; loop < originValue.length; loop++) {
							newValue += originValue[loop];
							if(loop+1 < originValue.length) {
								newValue += "|";
							}
						}
		
						for(var loop=0; loop < originText.length; loop++) {
							newText += originText[loop];
							if(loop+1 < originText.length) {
								newText += " / ";
							}
						}
						
						$(this).val(newValue);
						$(this).text(newText);
	
						modifyReceiversRightType(userId, setTextValue);
					}
				} else {
					return;
				}
				
			});

		$('#receiversRight').focus();
		$('#receivers').focus();
	});

	//옵션 Display 
	$('#optionPanel').click(function(){
		$('#optionBlock').toggle();
	});
	
	/**
	* 임직원 조회 function
	*/	
	var searchUser = function() {

		var frm = document.frm;

		var srchValue = comTrim(frm.srchReceiverNm.value);

		if(srchValue == '' || getByteLength(srchValue) < 4) {
			alert('<spring:message code='secfw.msg.error.nameMinByte' />');
			frm.srchReceiverNm.focus();
			return false;
		}
		
		PopUpWindowOpen2('', 850, 450, false);
		frm.target = "PopUpWindow2";
		
		frm.srch_user_cntnt_type.value = 'userName';
		frm.srch_user_cntnt.value = srchValue;
		
		frm.action = "<c:url value='/secfw/esbOrg.do' />";
		frm.method.value = "listEsbEmployee";
		
		frm.submit();
				
	};

	/**
	* 버튼 동작 부분
	*/
	// 취소
	$('.btnw').click(function(){
		document.frm.isStandardExit.value = "Y";
		opener.setApprovalYN('N');
		self.close();
	});

	// 결재상신
	$('.btnb').click(function(){		
		//FORM Validation
		var jikgupCd ="";
		if(validateForm(document.frm) != false) { 

			//수신인여부 체크
			var receiverCnt = 0;	//결재자수
			var agreeCnt	= 0;	//합의자수
			$('#receivers option')
			.each(function(){				
				var approvalUserInfos = $(this).val().split("|");				
				for(var i=0; i < approvalUserInfos.length; i++) {
					if(approvalUserInfos[0]== "1") {
						receiverCnt = receiverCnt + 1;
						jikgupCd = approvalUserInfos[3];
						break;
					} else if(approvalUserInfos[0] == "2") {
						agreeCnt	= agreeCnt + 1;
						break;
					}
				}
			});
			
			if(receiverCnt < 1) {
				//결재자를 지정해 주십시오.
				alert('<spring:message code="secfw.msg.approval.checkApprovalPath" />');
				$('#srchReceiverNm').focus();
				return;
			}
			
			if(document.frm.approval_option.value=="B") {	//체결품의일경우
				if(agreeCnt < 1) {
					// 2011.12.05 심우규 합의자없이 상신 가능하게 
					//alert('');//secfw.msg.approval.checkAgreePath
					//return;
				}	
			}
			//본문체크
			if (!checkContents()) return;

			//예약상신 일자 및 시간 체크
			if (!checkTime()) return;
			
			//예약상신 일시 설정
			if($('#checkReserved').attr('checked')) {
				$('input[name=reserved]').val(comStrReplace($('#reservedDay').val(),"-", "") + $('#reservedTime').val() + "0000");
			}			
			
			if (document.all.body_type1.checked) {
				//frm.body_mime.value = frm.wec.MIMEValue;
				//frm.body.value = frm.wec.BodyValue;				
				//frm.body_mime.value = "<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"\\>" + CrossEditor.GetBodyValue();
				frm.body_mime.value = CrossEditor.GetBodyValue();
				frm.body.value = CrossEditor.GetBodyValue();
			}
			
			$('#receivers option')
			.each(function(){
				$(this).attr("selected", true);
			});
			
			$('#receiversRight option')
			.each(function(){
				$(this).attr("selected", true);
			});
			
			listTbComCd(jikgupCd);
			
			//PopUpWindowOpen("<c:url value='/WEB-INF/jsp/secfw/singleIF/ApprovalOpinion.jsp' />", 700, 300, false);
			//PopUpWindowOpen("<c:url value='/secfw/main2.do?method=forwardPage&forward_url=/WEB-INF/jsp/secfw/singleIF/ApprovalOpinion.jsp' />", 700, 300, false);
			
			/*var widthLen = 700;
			var heightLen = 300;
			var leftPos = (window.screen.availWidth - widthLen) / 2;
			var topPos = (window.screen.availHeight - heightLen) / 2;
			var optWin = window.open("<c:url value='/secfw/main2.do?method=forwardPage&forward_url=/WEB-INF/jsp/secfw/singleIF/ApprovalOpinion.jsp' />", "searchDeptWin", "fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,left=" + leftPos + ",top=" + topPos + ",width=" + widthLen + ",height=" + heightLen)
			optWin.focus();*/			
		}
	});
	
	/**
	* 결재순서조정
	*/	
	$('div[name=moveOrderBtn]').click(function() {
		var rightDocLen = frm.receivers.options.length;
		var isUp = $(this).hasClass('up<%=localeDisplayType %>');
		var tempName = "" ;
		var tempValue = "" ;

		var tempRightName  = "";
		var tempRightValue = "";
		var option = "";
		var flag = true;
		//구주요청에 의한 추가 tb_com_user의 approval_Y 값이 Y인 유저는 이동 불가
		$('#receivers option:selected')
		.each(function(){	
			var originValue = $(this).val().split("|");

				if(originValue[12] == "Y") {
					flag = false;
					option = "Move";
				}
		});
	
		if(isUp) {
			for (var i = 0; i < rightDocLen; i++)
			{
				if( frm.receivers.options[i].selected && i != 0 && !frm.receivers.options[i - 1].selected && (i - 1) != 0)
				{				
					//순서변경 조건 추가 조건값이 Y일 경우 신성우 2014-03-13
					if(frm.receivers.options[i-1].value.split("|")[12] != "Y" && frm.receivers.options[i].value.split("|")[12] != "Y"){
						//결재순서 조정
						tempName = frm.receivers.options[i - 1].value ;
						tempValue = frm.receivers.options[i - 1].text ;
	
						frm.receivers.options[i - 1].value = frm.receivers.options[i].value ;
						frm.receivers.options[i - 1].text  = frm.receivers.options[i].text;
	
						frm.receivers.options[i].value = tempName ;
						frm.receivers.options[i].text  = tempValue;
	
						frm.receivers.options[i - 1].selected = true ;
						frm.receivers.options[i].selected = false ;
	
						//결재권한 순서조정
						tempRightName = frm.receiversRight.options[i - 1].value ;
						tempRightValue = frm.receiversRight.options[i - 1].text ;
	
						frm.receiversRight.options[i - 1].value = frm.receiversRight.options[i].value ;
						frm.receiversRight.options[i - 1].text  = frm.receiversRight.options[i].text;
	
						frm.receiversRight.options[i].value = tempRightName ;
						frm.receiversRight.options[i].text  = tempRightValue;
	
						frm.receiversRight.options[i - 1].selected = true ;
						frm.receiversRight.options[i].selected = false ;
					}else{
						flag = false;
						option = "MoveUp";
					}
				}
				else if( i == 0 && frm.receivers.options[i].selected)
				{
					frm.receivers.options[i].selected = true ;
				}
			}
		} else {
			for (var i = (rightDocLen - 1); i >= 0; i--)
			{
				if( frm.receivers.options[i].selected && i != (rightDocLen - 1) && !frm.receivers.options[i + 1].selected && i != 0)
				{
					//순서변경 조건 추가 조건값이 Y일 경우 신성우 2014-03-13
					if(frm.receivers.options[i].value.split("|")[12] != "Y" && frm.receivers.options[i+1].value.split("|")[12] != "Y"){
						//결재 순서 조정
						tempName = frm.receivers.options[i + 1].value ;
						tempValue = frm.receivers.options[i + 1].text ;
	
						frm.receivers.options[i + 1].value = frm.receivers.options[i].value ;
						frm.receivers.options[i + 1].text  = frm.receivers.options[i].text;
	
						frm.receivers.options[i].value = tempName ;
						frm.receivers.options[i].text  = tempValue;
	
						frm.receivers.options[i + 1].selected = true ;
						frm.receivers.options[i].selected = false ;
	
						//결재권한 순서조정
						tempRightName = frm.receiversRight.options[i + 1].value ;
						tempRightValue = frm.receiversRight.options[i + 1].text ;
	
						frm.receiversRight.options[i + 1].value = frm.receiversRight.options[i].value ;
						frm.receiversRight.options[i + 1].text  = frm.receiversRight.options[i].text;
	
						frm.receiversRight.options[i].value = tempRightName ;
						frm.receiversRight.options[i].text  = tempRightValue;
	
						frm.receiversRight.options[i + 1].selected = true ;
						frm.receiversRight.options[i].selected = false ;
					}else{
						flag = false;
						option = "MoveDown";
					}
				}
				else if( i == (rightDocLen - 1) && frm.receivers.options[i].selected)
				{
					frm.receivers.options[i].selected = true ;
				}
			}
		}
		
		//flag 메시지 추가
		if(!flag) {
			if(option=="Move") {
				alert("<spring:message code='secfw.msg.approval.notMoveApprovor'/>");
				return;
			} else if(option=="MoveUp") {
				alert("<spring:message code='secfw.msg.approval.notMoveUpApprovor'/>");
				return;
			} else if(option=="MoveDown") {
				alert("<spring:message code='secfw.msg.approval.notMoveDownApprovor'/>");
				return;
			} 
		}
	});

	/**
	* 수신자 삭제
	*/
	$('#deleteBtn').click(function() {

		var flag = true;
		var option = "";
		//기안자는 삭제불가
		$('#receivers option:selected')
		.each(function(){
			var originValue = $(this).val();
			if(originValue.indexOf(frm.draft_user_id.value) >= 0) {
				flag = false;
				option = "DRAFT";
			}
			if(document.frm.approval_option.value=="B") {//체결품의일 경우
				var agreeUserInfos = originValue.split("|");
				for(var i=0; i < agreeUserInfos.length; i++) {
					if(agreeUserInfos[0] == "2") {
						flag = false;
						option = "AGREE";
						break;
					}
				}	
			}
			
		//구주요청에 의한 추가 tb_com_user의 approval_Y 값이 Y인 유저는 삭제 불가
			var agreeUserInfos = originValue.split("|");

						
			for(var i=0; i < agreeUserInfos.length; i++) {
				//Sungwoo replaced 2014-11-04
				if(agreeUserInfos[12] == "Y" || agreeUserInfos[12] == "M" || agreeUserInfos[13] == "APP_Y_ID") {
					flag = false;
					option = "LEGAL";
					break;
										
				}
			}	
			
		});

		if(!flag) {
			if(option=="DRAFT") {
				alert("<spring:message code='secfw.msg.approval.notDeleteCreator'/>");
				return;
			} 
			
			if(option=="AGREE") {
				alert("<spring:message code='secfw.msg.approval.notDeleteAgree'/>");
				return;
			}
			if(option=="LEGAL") {
				alert("<spring:message code='secfw.msg.approval.notDeleteLegalAdmin'/>");
				return;
			} 
		} else {
			
			var delUserInfos = $('#receivers option:selected').val().split("|");
			
			var selectedIndex = $('#receivers').attr("selectedIndex");
			
			$('#receivers option:selected').remove();
			//2014-05-09. Sungwoo Replacement remove
			$('#receiversRight option')[selectedIndex].removeNode();
			/*$('#receiversRight option')
				.each(function() {
				var targetValue = $(this).val();	
				if(targetValue.indexOf(delUserInfos[1]) >= 0 ) {
					$(this).remove();
				}					
			});*/
		}		
	});

	/**
	* Text - HTML 변환
	*/
	if("0" == '<%=approvalHtmlGbn%>') {
		$('#body_type0').attr('checked',true);
        $('input[name=checkedBodyType]').val('0');
		$('#textEdit').show();
		$('#htmlEdit').hide();
	} else if("1" == '<%=approvalHtmlGbn%>') {
		$('#body_type1').attr('checked',true);
        $('input[name=checkedBodyType]').val('1');
		$('#textEdit').hide();
		$('#htmlEdit').show();
	}
	/**
	$('input[name=body_type]').click(function() {
		var typeValue = $(this).val();
		bodyChange(typeValue);
	});
	**/
	var bodyChange = function(iType) {

		var tempValue = $('input[name=checkedBodyType]')
		
	    //var wecObj  = document.getElementById("wec");
		var textObj = document.getElementById("body");
	   
		var strBody  = ""; //CrossEditor.GetTextValue(); //wecObj.Value;
	    var strBody1 = textObj.value;
	    var strBody2 = ""; //CrossEditor.GetTextValue(); // wecObj.BodyValue; //0903 추가
	    
	    if ( iType =="0" ) {

	        if ($('input[name=checkedBodyType]').val() == "0")
	            return;

	        if (!confirm("<spring:message code='secfw.msg.approval.toTextMode' />")) {
	            if (document.all.body_type0.checked) // Text Checked
	            {
	                document.all.body_type0.checked = false;
	                document.all.body_type1.checked = true;
	            }
	            return;
	        } else {
				$('#htmlEdit').hide();
				$('#textEdit').show();
	        }
	        
	        textObj.value = strBody2; // stripHTML(strBody2); //0903 추가
	        $('input[name=checkedBodyType]').val("0");
	        textObj.focus();
	    } else {			
	        if ($('input[name=checkedBodyType]').val() == "1")
	            return;
	        
			$('#textEdit').hide();
			$('#htmlEdit').show();
	        
			/*
			wecObj.EditMode = 1;			
			wecObj.Value = "<HTML><HEAD>" + document.frm.wec.HeadValue +"</HEAD><BODY>"+ textToHtml(strBody1) + "</BODY></HTML>";

	        $('input[name=checkedBodyType]').val('1');
	        wecObj.focus();
	        */
	        $('input[name=checkedBodyType]').val('1');
	        //CrossEditor.SetValue(textToHtml(strBody1));	        
	    }
	};

	/**
	* bodySize - 변경
	*/
	$('#bodySize').change(function() {
		var changeSize = $(this).val();
		onLoadBodyHeight(changeSize);		
	});
	
	var initChangeBodyHeight = function (screenSize) {
		var bodyHeight = 100;

		if(screenSize == 100)
			bodyHeight = 100;
		else if(screenSize == 200)
			bodyHeight = 200;
		else if(screenSize == 300)
			bodyHeight = 300;
		else if(screeSize == 700)
		//본문입력창 저장된 값으로 resize
		onLoadBodyHeight(bodyHeight);
	};

	var onLoadBodyHeight = function(iFormat) {
		if (iFormat==100) {
			document.getElementById("wec").style.height ="300px";//Activesquare 7 
			document.getElementById("body").style.height ="300px";
		}
		else if (iFormat==200) {
			document.getElementById("wec").style.height ="525px";
			document.getElementById("body").style.height ="525px";
		}
	    else if (iFormat==300) {
			document.getElementById("wec").style.height ="825px";
			document.getElementById("body").style.height ="825px";
		} else if(iFormat==700){
			document.getElementById("wec").style.height ="1425px";
		    document.getElementById("body").style.height ="1425px";
		}    
		
		iFormat = parseInt(iFormat);
	    if (isNaN(iFormat)) iFormat=100;

	    return;
	};

	/**
	* 결재선 권한변경
	*/
	$('input[name=receiversRightType]').click(function() {
		var receiversRightType = $(this).val();
		var checkedValue       = $(this).attr('checked');

		//기안자외 권한변경
		$('#receiversRight option:selected')
		.each(function(){

			var rightValue = $(this).val();
			var originValue    = $(this).val().split("|");
			var originText0    = $(this).text().split(" : ")[0];
			var originText1    = $(this).text().split(" : ")[1];
			var originText1Sub = originText1.split(" / ");
			
			var newValue = '';
			var newText  = '';

			if(!(rightValue.indexOf(frm.draft_user_id.value) >= 0)) {
						
				//checked
				if(checkedValue) {
					//경로변경
					if(receiversRightType == '0') {
						originValue[1] = '0';
						originText1Sub[0] = "<spring:message code='secfw.page.field.approval.grantApprovalRight.changeLine' />";
					}
		
					//수정가능
					if(receiversRightType == '1') { 
						originValue[2] = '0';
						originText1Sub[1] = "<spring:message code='secfw.page.field.approval.grantApprovalRight.modifyText' />";
					}
		
					//전결가능
					if(receiversRightType == '2') {
						originValue[3] = '0';
						originText1Sub[2] = "<spring:message code='secfw.page.field.approval.grantApprovalRight.arbitrary' />";
					}
				} else {
					//경로변경 불가
					if(receiversRightType == '0') {
						originValue[1] = '-1';
						originText1Sub[0] = "";
					}
		
					//수정불가
					if(receiversRightType == '1') { 
						originValue[2] = '-1';
						originText1Sub[1] = "";
					}
		
					//전결 불가
					if(receiversRightType == '2') { 
						originValue[3] = '-1';
						originText1Sub[2] = "";
					}
				}	
				
				for(var loop=0; loop < originValue.length; loop++) {
					newValue += originValue[loop];
					if(loop+1 < originValue.length) {
						newValue += "|";
					}
				}
	
				newText = originText0 + " : "; 
				for(var loop=0; loop < originText1Sub.length; loop++) {
					newText += originText1Sub[loop];
					if(loop+1 < originText1Sub.length) {
						newText += " / ";
					}
				}
				
				$('#receiversRight option:selected').val(newValue);
				$('#receiversRight option:selected').text(newText);
			} else {
				return;
			}
			
		});

		$('#receiversRight').focus();
		
	});

	/**
	* 결재구분 변경시 결재권한 타입명 변경(기안, 결재...)
	*/
	var modifyReceiversRightType = function(userId, typeNm) {

		$('#receiversRight option')
		.each(function(){
			var originValue = $(this).val();

			if(originValue.indexOf(userId) >= 0) {

				var originText0    = $(this).text().split(" : ")[0];
				var originText1    = $(this).text().split(" : ")[1];
				var originText0Sub = originText0.split(" / ");

				originText0Sub[0] = typeNm;
				
				var newText1  = typeNm + " / " + originText0Sub[1] + " / " + originText0Sub[2];

				//for(var loop=0; loop < originText0Sub.length; loop++) {
				//	newText += originText0Sub[loop];
				//	if(loop+1 < originText0Sub.length) {
				//		newText += " / ";
				//	}
				//}

				newText = newText1 + " : " + originText1;
				
				$(this).text(newText);

			}
		});

		$('#receiversRight').focus();
		
	};
	
	/**
	* 결재자 경로변경/본문수정/전결  체크박스 컨트롤
	*/
	$('#receiversRight').click(function() {

		if($('#receiversRight option:selected').val()) {

			var originValue    = $('#receiversRight option:selected').val().split("|");

			var userId         = originValue[0];
			var checkPath      = originValue[1];
			var checkModify    = originValue[2];
			var checkArbitrary = originValue[3];

			//if(userId != $('input[name=draft_user_id]').val()) { //상신자가 아닌경우
				//경로변경
				if(checkPath == '0') {
					$('#receiversRightType0').attr('checked', true);
				} else {
					$('#receiversRightType0').attr('checked', false);
				}
	
				//수정가능
				if(checkModify == '0') { 
					$('#receiversRightType1').attr('checked', true);
				} else {
					$('#receiversRightType1').attr('checked', false);
				}				
	
				//전결가능
				if(checkArbitrary == '0') { 
					$('#receiversRightType2').attr('checked', true);
				} else {
					$('#receiversRightType2').attr('checked', false);
				}
				
			//} else {
			//	alert('상신자  정보는 변경하실 수 없습니다.');
			//	$('#receiversRightType0').attr('enabled', false);
			//	$('#receiversRightType1').attr('enabled', false);
			//	$('#receiversRightType2').attr('enabled', false);
			//}
		} 
		
	});
	
	//텍스트-HTML 초기설정
	bodyChange('<%=approvalHtmlGbn%>');

	var checkContents = function () {

	    //var strBody  = comTrim(stripHTML(document.wec.Value));
	    var strBody  = comTrim(stripHTML(CrossEditor.GetValue()));
	    
	    var strBody1 = comTrim($('textarea[name=body]').html());

	    if ($('input[name=checkedBodyType]').val() == "1") {  // Rich Text Editor 입력시	   
	        if ( strBody.length < 1) {
		        //본문을 입력해 주십시오.
	            alert('<spring:message code="secfw.msg.approval.insertContent" />');
	            //document.wec.focus();
	            CrossEditor.SetFocusEditor();
	            return false;
	        }
	    } else { // 텍스트 입력시
	        if (strBody1.length < 1) {
		        //본문을 입력해 주십시오.
	            alert('<spring:message code="secfw.msg.approval.insertContent" />');
	            document.frm.body.focus();
	            return false;
	        }
	    }

		//본문의 크기 제한(500KB 이하로)
		if($('input[name=checkedBodyType]').val() == "1") {
			//var object1 = document.wec.GetDocumentSize();
			var object1 = CrossEditor.GetValueLength();
			
			if(object1 > 500*1024) {
               //alert('본문의 크기는 1M를 초과할수 없습니다.');
               alert( '<spring:message code="secfw.msg.approval.exceedContentLength" />');
               return false;
           }
		} else {
			 var textVal  = $('textarea[name=body]').html();
			 if(CheckByte(textVal) > 500*1024 ) {
				 alert( '<spring:message code="secfw.msg.approval.exceedContentLength" />');
				 return false;
			 }
		}
	    
	    return true;
	}

	//예약상신 시간 설정
	$('#reservedTime').val('<%=DateUtil.getTime("HH")%>');
	
	/**
	* 예약상신 일자 설정.
	*/
	$('#reserveCal').click(function(){
	    //var nowDate = "2010-08-31";
	    var nowDate = '<%=DateUtil.getTime("yyyy-MM-dd")%>';
	    var resrvTime = document.frm.reservedTime.value;
	    var nowHour = '<%=DateUtil.getTime("HH")%>';
	    var returnStr = $('input[name=reservedDay]').val();

	    if(!compareDate(nowDate, returnStr, "yyyy-MM-dd")) {

		    //예약상신기간은 현재 이후로 지정하셔야 합니다.
	        alert('<spring:message code="secfw.msg.approval.setScheduledTime" />');
	        $('input[name=reservedDay]').val(nowDate);
	        return;
	    }
	    
	    $('input[name=reservedDay]').val(returnStr);
	});

	/**
	* 예약상신 일자 점검.
	*/
	var checkTime = function () {
		var isReturn = $('#checkReserved').attr('checked');
		
	    //var nowDate = "2010-08-31 10:19";
	    var nowDate = '<%=DateUtil.getTime("yyyy-MM-dd HH:mm")%>';
	    var resrvTime = document.frm.reservedTime.value;
	    var resrvDate = document.frm.reservedDay.value + ' ' + resrvTime + ':00';

	    if(!isReturn) return true;
	    
	    if(!compareDate(nowDate, resrvDate, "yyyy-MM-dd HH:mm"))
	    {	    
		    //예약상신기간은 현재 이후로 지정하셔야 합니다.
	        alert('<spring:message code="secfw.msg.approval.setScheduledTime" />');
	        document.frm.reservedTime.value = '<%=DateUtil.getTime("HH")%>';
            return false;
	    }

	    return true;
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
<%--
//결재경로
var pathHtml = "<option value='1|M030402040320C605656|김형준|과장|전자파트너'>기안 / 김형준 / 과장 / 전자파트너 / 전자협력사 /</option>";
pathHtml += "<option value='1|M030402040320C605656'>결재 / 김회기 / 차장 / 전자파트너 / 전자협력사 /</option>";
$('#receivers').append(pathHtml);

//결재권한
var rightHtml = "<option value='M100523234206C608705|-1|-1|-1'>기안 / 김형준 / 과장 : 경로변경 / 본문수정 / </option>";
rightHtml  += "<option value='M030402040320C605656|0|0|0'>결재 / 김회기 / 차장 : 경로변경 / 본문수정 / 전결</option>";
$('#receiversRight').append(rightHtml);

//2011.10.13 심주완수정
var pathHtml = "<option value='1|M030402040320C605656|김회기|차장|전자파트너'>결재 / 김회기 / 차장 / 전자파트너 / 전자협력사 /</option>";
$('#receivers').append(pathHtml);
var rightHtml = "<option value='M030402040320C605656|" + + DEFAULT_CHANGE_LINE + "|" + DEFAULT_MODIFY_TEXT + "|" + DEFAULT_ARBITRATY + "'>결재 / 김회기 / 차장 : 경로변경 / 본문수정 / 전결</option>";
$('#receiversRight').append(rightHtml);
--%>
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	initCal("reservedDay");
	
	
	
	
});

	//첨부파일 호출
	function initPage(){
	    var frm = document.frm;
	    
	    frm.target = "fileList";
	    frm.action = "<c:url value='/clms/common/clmsfile.do' />";
		frm.method.value = "forwardComFilePage";
		frm.submit();
	}
	
	/**
	* 결재상신
	*/
	function approvalSubmit() {
		viewHiddenProgress(true);

		if(document.frm.approval_option.value == 'B'){ //DB에만 인서트! 나중에 수동으로 ESB upload
			//document.frm.method.value = "preSubmit";	
			document.frm.method.value = "submit";  //바로상신하게 수정
		}else{ //DB에만 입력
			document.frm.method.value = "submit"; //바로상신
		}
		
		//첨부파일 업로드
		fileList.UploadFile(function(uploadCount){
			if(uploadCount == -1){
				initPage();
				alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
				return;
			}

			// 2012.05.02 결재상신전 main창의 setApprovalYN 함수 실행여부를 판단 added by hanjihoon
			if(!opener.closed && $("#approval_key", opener.document).val() == $("#ref_key").val()){
				var options = {
						url: "<c:url value='/secfw/esbApproval.do' />",
						type: "post",
						async: false,
						dataType: "json",
						beforeSubmit: function(formData, form){
							//comLayerPopCenter('ProgressLayer1');
						},
						success: function(responseText,returnMessage) {
							
							viewHiddenProgress(false);
							
							//alert(responseText.returnMessage);
							document.frm.isStandardExit.value = "Y";
							opener.setApprovalYN(responseText.returnValue);
							
							window.close();
						}
					};
					$("#frm").ajaxSubmit(options);
			}else{
				viewHiddenProgress(false);
				alert("<spring:message code='secfw.page.field.singleIF.stoppedCazClosePage'/>");
			}
		});
	}

	/**
	* 임직원정보 Setting
	*/	
	function setUserInfos(obj) {
		
		var name     	= obj.cn;					//이름
		var type;
		var jikgupCd 	= obj.eptitlenumber;		//직급코드
		var jikgupNm 	= obj.title;				//직급명
		var deptCd	 	= obj.departmentnumber;		//부서코드 	
		var deptNm   	= obj.department;			//부서명
		var compCd		= obj.eporganizationnumber; //회사코드
		var compNm  	= obj.o;					//회사명
		var grpCd       = obj.epsuborgcode;			//총괄코드
		var grpNm		= obj.epsuborgname;			//총괄명
		var email    	= obj.mail;					//메일주소
		var userId   	= obj.epid;					//epid-userid
		var userNm   	= obj.cn; 					//사용자명

		var typeNm;
		
		if(obj.epsuborgcode=="") {
			grpCd = "A";
		}
		if(obj.epsuborgname=="") {
			grpNm = "<spring:message code='clm.page.field.regist.makeApprovalHtmlDirectNew02' />";//없음	
		}
		
	
		type = $('input[name=activityType]:radio:checked').val();
	
		if(type == '1') {
			//결재
			typeNm = V_APPROVAL_NM;
		} else if(type == '2') {
			//합의
			typeNm = V_CONSENT_NM;
		} else if(type == '3') {
			//후결
			typeNm = V_POSTAPPROVAL_NM;
		} else if(type == '9') {
			//통보
			typeNm = V_NOTIFY_NM;
		}
	
		/*
		if((email == null || comTrim(email) == '') && (userId == null || comTrim(userId) == '') ) {
			alert('결재자  정보가 부족합니다.');
			return;
		}
		*/
		
		var flag = true;
		
		//기존 추가된 사용자 인지 체크
		$('#receivers option')
		.each(function(){
			var originValue = $(this).val();
			if(originValue.indexOf(userId) >= 0) {
				flag = false;
			}
		});
	
		if(!flag) {
			// 이미 등록되어 있습니다.
			alert('<spring:message code="secfw.msg.approval.alreadyRegistered" />');
			return;
		} else {
			
			//2012-05-16
			//간혹 ESB에서 정보가 안넘어올경우 controller에서 배열을 split하면서 ArrayIndexOutOfBoundsException 에러가 난다.
			//값이 없을경우 공백을 넣어서 셋팅한다.
			if(type == '') type = ' ';
			if(userId == '') userId = ' ';
			if(userNm == '') userNm = ' ';
			if(jikgupCd == '') jikgupCd = ' ';
			if(jikgupNm == '') jikgupNm = ' ';
			if(deptCd == '') deptCd = ' ';
			if(deptNm == '') deptNm = ' ';
			if(compCd == '') compCd = ' ';
			if(compNm == '') compNm = ' ';
			if(grpCd == '') grpCd = ' ';
			if(grpNm == '') grpNm = ' ';
			if(email == '') email = ' ';
			
			//결재경로
			var pathHtml =  '<option value="' + type + "|" + userId + "|" + userNm + "|" + jikgupCd + "|" + jikgupNm + "|" + deptCd + "|" + deptNm + "|" + compCd + "|" + compNm + "|" + grpCd + "|" + grpNm + "|" + email + '">'
		                 + typeNm + " / " + name + " / " + jikgupNm + " / " + deptNm + " / " + compNm + " / " + "</option>";	
			$('#receivers').append(pathHtml);

			//결재권한
			var rightHtml = '<option value="' + userId + "|" + DEFAULT_CHANGE_LINE + "|" + DEFAULT_MODIFY_TEXT + "|" + DEFAULT_ARBITRATY + '">'
		                  + typeNm + " / " + name + " / " + jikgupNm + " : " + "<spring:message code='secfw.page.field.approval.grantApprovalRight.changeLine' />" + " / " + "<spring:message code='secfw.page.field.approval.grantApprovalRight.modifyText' />" + " / " + "<spring:message code='secfw.page.field.approval.grantApprovalRight.arbitrary' />"
		                  + "</option>";			
			$('#receiversRight').append(rightHtml);
		}

		$('#srchReceiverNm').val('');
	}
	
	
	
	/**
	* 임직원정보 Setting
	*/	
	function setUserInfosMulit(obj) {
		
		
		
		
		for(var i=0; i< obj.length; i++){
			
			var name     	= obj[i].cn;					//이름
			var type;
			var jikgupCd 	= obj[i].eptitlenumber;		//직급코드
			var jikgupNm 	= obj[i].title;				//직급명
			var deptCd	 	= obj[i].departmentnumber;		//부서코드 	
			var deptNm   	= obj[i].department;			//부서명
			var compCd		= obj[i].eporganizationnumber; //회사코드
			var compNm  	= obj[i].o;					//회사명
			var grpCd       = obj[i].epsuborgcode;			//총괄코드
			var grpNm		= obj[i].epsuborgname;			//총괄명
			var email    	= obj[i].mail;					//메일주소
			var userId   	= obj[i].epid;					//epid-userid
			var userNm   	= obj[i].cn; 					//사용자명
			
		

		var typeNm;
		
		if(obj.epsuborgcode=="") {
			grpCd = "A";
		}
		if(obj.epsuborgname=="") {
			grpNm = "<spring:message code='clm.page.field.regist.makeApprovalHtmlDirectNew02' />";//없음	
		}
		
	
		type = $('input[name=activityType]:radio:checked').val();
	
		if(type == '1') {
			//결재
			typeNm = V_APPROVAL_NM;
		} else if(type == '2') {
			//합의
			typeNm = V_CONSENT_NM;
		} else if(type == '3') {
			//후결
			typeNm = V_POSTAPPROVAL_NM;
		} else if(type == '9') {
			//통보
			typeNm = V_NOTIFY_NM;
		}
	
		/*
		if((email == null || comTrim(email) == '') && (userId == null || comTrim(userId) == '') ) {
			alert('결재자  정보가 부족합니다.');
			return;
		}
		*/
		
		var flag = true;
		
		//기존 추가된 사용자 인지 체크
		$('#receivers option')
		.each(function(){
			var originValue = $(this).val();
			if(originValue.indexOf(userId) >= 0) {
				flag = false;
			}
		});
	
		if(!flag) {
			// 이미 등록되어 있습니다.
			alert('<spring:message code="secfw.msg.approval.alreadyRegistered" />');
			return;
		} else {
			
			//2012-05-16
			//간혹 ESB에서 정보가 안넘어올경우 controller에서 배열을 split하면서 ArrayIndexOutOfBoundsException 에러가 난다.
			//값이 없을경우 공백을 넣어서 셋팅한다.
			if(type == '') type = ' ';
			if(userId == '') userId = ' ';
			if(userNm == '') userNm = ' ';
			if(jikgupCd == '') jikgupCd = ' ';
			if(jikgupNm == '') jikgupNm = ' ';
			if(deptCd == '') deptCd = ' ';
			if(deptNm == '') deptNm = ' ';
			if(compCd == '') compCd = ' ';
			if(compNm == '') compNm = ' ';
			if(grpCd == '') grpCd = ' ';
			if(grpNm == '') grpNm = ' ';
			if(email == '') email = ' ';
			
			//결재경로
			var pathHtml =  "<option value='" + type + "|" + userId + "|" + userNm + "|" + jikgupCd + "|" + jikgupNm + "|" + deptCd + "|" + deptNm + "|" + compCd + "|" + compNm + "|" + grpCd + "|" + grpNm + "|" + email + "'>"
		                 + typeNm + " / " + name + " / " + jikgupNm + " / " + deptNm + " / " + compNm + " / " + "</option>";	
			$('#receivers').append(pathHtml);

			//결재권한
			var rightHtml = "<option value='" + userId + "|" + DEFAULT_CHANGE_LINE + "|" + DEFAULT_MODIFY_TEXT + "|" + DEFAULT_ARBITRATY + "'>"
		                  + typeNm + " / " + name + " / " + jikgupNm + " : " + "<spring:message code='secfw.page.field.approval.grantApprovalRight.changeLine' />" + " / " + "<spring:message code='secfw.page.field.approval.grantApprovalRight.modifyText' />" + " / " + "<spring:message code='secfw.page.field.approval.grantApprovalRight.arbitrary' />"
		                  + "</option>";			
			$('#receiversRight').append(rightHtml);
		}
		
		}

		$('#srchReceiverNm').val('');
	}
	

	function retOpener() {
		if(document.frm.isStandardExit.value=="N") {
			opener.setApprovalYN('N');
		} 
	}
	
	function setResize() {
		//document.getElementById("wec").style.height ="300px";
		//document.getElementById("body").style.height ="300px";
	}
	
	function listTbComCd(arg){
	    var frm = document.frm;
	    var result = "N";
	    var options = {
	        url: "/common/clmsCom.do?method=listTbComCd&grp_cd=C061&cd="+arg,
	        type: "post",
	        async: false,
	        dataType: "json",
	        success: function(responseText,returnMessage) {
	            if(responseText != null && responseText.length != 0) {
	                $.each(responseText, function(entryIndex, entry) {
	                    //entry['DB컬럼명'] : TB_COM_CD 에서 조회된 "DB컬럼" 값
	                    result = entry['useman_mng_itm1'];
	                    return result;
	                });
	            } else {
	            	return result;
	            }
	            if(result=="N") {
		        	if(!confirm("<spring:message code='secfw.page.field.singleIF.checkPosition'/>")) return;
		        }
		    	var widthLen = 700;
				var heightLen = 300;
				var leftPos = (window.screen.availWidth - widthLen) / 2;
				var topPos = (window.screen.availHeight - heightLen) / 2;
				var optWin = window.open("<c:url value='/secfw/main2.do?method=forwardPage&forward_url=/WEB-INF/jsp/secfw/singleIF/ApprovalOpinion.jsp' />", "searchDeptWin", "toolbar=no,location=no,status=no,menubar=no,scrollbars=no,resizable=no,left=" + leftPos + ",top=" + topPos + ",width=" + widthLen + ",height=" + heightLen)
				optWin.focus();
	        }
	       
	    };
	   
	    $("#frm").ajaxSubmit(options);
	}


	function openArbitraryPop(){
		PopUpWindowOpen('/las/board/notice.do?method=detailDecideArbitrarilyRe&menu_id=20130319155746339_0000000377', 840, 600, true);
	}
	
//-->
</script>
<!-- **************************** Calendar 관련 추가 영역 **************************** -->
<div id='popCal' style='position: absolute; visibility: hidden; ridge; width: 10; z-index:1;'>
<iframe name="popFrame" src="<c:url value='/secfw/popCalendar.do?method=goPopCalendar' />" frameborder="0" scrolling="no" width=196 height=188></iframe></div>
<script event=onclick() for=document> 
if(popCal.style.visibility == "visible"){
  popCal.style.visibility="hidden";
}
</script>
</head>
<body onload="javascript:setResize();" onUnload="javascript:retOpener();" class='pop'>


	
	
	<h1><spring:message code="secfw.page.field.approval.startProcess" /></h1>

	<div class="pop_area">
		<div class="pop_content"  style="height:585px;" id="bodyDivIn">	
		
		
			<form:form id="frm" name="frm" method="post" autocomplete="off">
	<!-- 결재선검색 -->
	<input type='hidden' name='srch_user_cntnt_type' />
	<input type='hidden' name='srch_user_cntnt' />
	<input type='hidden' name='doSearch' value='Y' />

	<!-- 상신자정보 -->
	<input type='hidden' name='draft_user_id' value='<%= approvalDrafterID %>' />
	
	
	<!-- Controller Method -->
	<input type='hidden' name='method' value='Y' />

	<!-- 텍스트-편집기입력 구분 -->
	<input type='hidden' name='checkedBodyType' value='<%=approvalHtmlGbn %>' />
	<input type='hidden' name='body_mime'       value='' />
	<!-- 2014-03-17 신성우 body_type 구분자가 checked 여부 확인구분자 있어 해당 내역 주석처리 radio_box 내역 추가-->
	<%-- <input type='hidden' name='body_type'       value='1' /> BODY_TYPE='1'  --%>
	
	<!-- 첨부파일정보 -->
	<input type="hidden" name="fileInfos" 	value="" />
	<input type="hidden" name="sys_gbn" 	value="approval" />
	<input type="hidden" name="view_gbn" 	value="upload" />

	<!-- 예약시간설정 -->
	<input type="hidden" name="reserved" 	value="" />
	<input type="hidden" name="opinion" 	value="" />
	
	<!-- 후속프로세스 메소드 -->
	<input type="hidden" name="approvalPostProcess" 	value="<%=approvalPostProcess %>" />
	
	<!-- 결재 요청 업무에서 추출한 MIS_ID -->
	<input type="hidden" name="module_id" 	value="<%=approvalModuleID %>" />
	<input type="hidden" name="mis_id" 	value="<%=approvalMisID %>" />
	
	<!-- menuId -->
	<input type="hidden" name="menu_id" value="<%=menuId%>"/>
	
	<!-- flag -->
	<input type="hidden" name="approval_option" value="<%=approval_option%>"/>
	
	<!-- ref_key -->
	<input type="hidden" name="ref_key" id="ref_key" value="<%=ref_key%>"/>
	<input type="hidden" name="apprvl_clsfcn" value="<%=apprvl_clsfcn%>"/>
	
	<!-- 비정상종료체크 -->
	<input type="hidden" name="isStandardExit" value="N"/>
						
			
			
			<div class='btnwrap'>
				<div class='btnb'>
					<span class="btn_all_w"><span class="sangsin"></span><a href="#"><spring:message code="secfw.page.field.approval.submit" /></a></span>
				</div>
			</div>
	
			
			
			<!-- 결재 start -->
			<table cellspacing="0" id='tbl' cellpadding="0">
			   <colgroup>
                 <col width="15%"/>
                 <col width="*%"/>
                 <col width="20%"/>
               </colgroup>
				<tr>
					<!-- 제목 -->
					<th><spring:message code="secfw.page.field.approval.subject" /></th>
					<td colspan='2'><input type='text' name='title' alt="<spring:message code="secfw.page.field.approval.subject" />" value='<%=approvalTitle %>' class='IpTextLe' style='width:98%' fieldTitle="<spring:message code="secfw.page.field.approval.subject" />" required="*" maxLength="100"></td>
				</tr>
				<tr>
					<!-- 결재자 -->
					<th><spring:message code="secfw.page.field.approval.to" /><!-- div class='tip'></div--></th>
					<td colspan='2'>
						<input type='text' id="srchReceiverNm" name='srchReceiverNm' class='IpTextLe' style='width:90%;'>
						<span class="btn_all_b" id='srchReceiverBtn'><span class="check"></span><a><spring:message code="clm.page.button.confirm" /></a></span>
						
					</td>
				</tr>
				<tr>
					<!-- 결재경로 -->
					<th><spring:message code="secfw.page.field.approval.path" /></th>
					<td>
						<select id="receivers" name="receivers" multiple="multiple" class='text' style='width:100%; height:100px;'>
						</select>					
					</td>
					<td>
						<table cellspacing="0" cellpadding="0" id='opa' style='margin:-5px -7px'>
						   <colgroup>
			                 <col width="*%"/>
			                 <col width="35%"/>
			               </colgroup>
							<tr>
								<td><input id="activityType1" type="radio" name="activityType" value="1" textValue="<spring:message code="secfw.page.field.approval.approval" />"> <span><spring:message code="secfw.page.field.approval.approval" /></span>[<span id="activityType1Cnt"></span>]</td>
								<td><span class="btn_all_b" id="deleteBtn"><span class="delete1"></span><a><spring:message code="clm.page.button.delete" /></a></span></td>
							</tr>
							<tr>
								<td><input id="activityType2" type="radio" name="activityType" value="2" textValue="<spring:message code="secfw.page.field.approval.consent" />"> <span><spring:message code="secfw.page.field.approval.consent" /></span>[<span id="activityType2Cnt"></span>]</td>								 
								<td><div name="moveOrderBtn" class="up<%=localeDisplayType %>"><span class="btn_all_b" name="moveOrderBtn"><span class="up1"></span><a><spring:message code="las.page.field.approval.moveUp" /></a></span></div></td>							
							</tr>
							<tr>
								<td><input id="activityType3" type="radio" name="activityType" value="3" textValue="<spring:message code="secfw.page.field.approval.postApproval" />"> <span><spring:message code="secfw.page.field.approval.postApproval" /></span>[<span id="activityType3Cnt"></span>]</td>
								<td><div name="moveOrderBtn" class="down<%=localeDisplayType %>"><span class="btn_all_b" name="moveOrderBtn"><span class="down1"></span><a><spring:message code="las.page.field.approval.moveDown" /></a></span></div></td>
							</tr>
							<tr>
								<td><input id="activityType9" type="radio" name="activityType" value="9" textValue="<spring:message code="secfw.page.field.approval.notify" />"> <span><spring:message code="secfw.page.field.approval.notify" /></span>[<span id="activityType9Cnt"></span>]</td>
								<td><!-- <div id="parallelBtn" class='row'></div> --></td>
							</tr>
						</table>
					</td>
				</tr>
				<textarea id='body' name='body' style="display: none;"><%=approvalContent %></textarea>
				<!-- 신성우 추가 checkbox script 처리위해서 실제 사용하지는 않음 -->
				<tr style="display: none;">
					<th class='con1'><spring:message code="secfw.page.field.mail.mailCntnt" /></th>
					<td colspan="2" class='con1 end'>
						<div>
						<input type='radio' id="body_type0" name='body_type' value='0'> <spring:message code="secfw.page.field.approval.planText" /> 
						<input type='radio' id="body_type1" name='body_type' value='1'> <spring:message code="secfw.page.field.approval.richText" /> 
						<select id='bodySize'>
							<option value=100><spring:message code="secfw.page.field.approval.content.size1" /></option>
							<option value=200><spring:message code="secfw.page.field.approval.content.size2" /></option>
							<option value=300><spring:message code="secfw.page.field.approval.content.size3" /></option>
						</select>
						<select name='encoding'>
						</select>
						</div>
						<!-- div class='tip'></div -->
					</td>
				</tr>				
				<tr id="htmlEdit">
					<td  colspan="3">
						<%-- Namo Active Square 7 --%>
						<%-- @ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude2.jspf" --%>
						<%-- //Namo Active Square 7 --%>
						<script type="text/javascript" type="text/javascript">							
							  var CrossEditor = new NamoSE('wec');
							  CrossEditor.params.Height = "700";	
							  CrossEditor.params.Width = "965";
						      CrossEditor.params.UserLang = "enu";
						      CrossEditor.params.FullScreen = false;
						        					
						      CrossEditor.UserToolbar = true;
						      CrossEditor.params.CreateToolbar = "print";
						        					
						      CrossEditor.EditorStart();
						        					
						      function OnInitCompleted(e){
						            CrossEditor.ShowTab(false);						        						
						        	CrossEditor.SetBodyValue(document.frm.body.value);
						        	CrossEditor.SetActiveTab(2);						        		
						      }							        					
						</script>
					</td>
				</tr>
				<tr>
					<td colspan='3'>
						<!-- Sungwoo Replaced scroll option 2014/08/04 -->
						<iframe  src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" leftmargin="0" topmargin="0" scrolling="auto"></iframe>
					</td>
				</tr>
			</table>
			
			
					
			<table cellspacing="0"  cellpadding="0" id='file' >
				<!-- 첨부파일 start -->
				<tr>
            		<td>
            			
            		</td>
          		</tr>
				<!-- 첨부파일 End -->
				<tr>
					<td class='con2'>
						<!-- 상세옵션 start -->
                        <table id='optionPanel' border="0" cellspacing="0" cellpadding="0">
                            
                            <tr>
                                <th style='width:125px'>
                                    <div class='fL'><spring:message code="secfw.page.field.approval.options" /></div>
                                    <div class='open'></div>
                                </th>
                                <td style='width:70%'><spring:message code="secfw.page.field.approval.optionHelp" /></td>
                                <td>&nbsp;</td>
                            </tr>
                        </table>
                        <!-- 상세옵션 End -->
						<!-- 상세옵션설정 start -->
						
						<div style='display:block;margin-top:-1px; width:100%' id='optionBlock'>
							<table id='option' border="0" cellspacing="0" cellpadding="0">
								<tr>
									<th style='width:125px'><spring:message code="secfw.page.field.approval.approvalType" /></th>
									<td style='width:70%'>
										<!-- 
										<input type="radio" name='security' value='0' checked> <spring:message code="secfw.page.field.approval.approvalType.general" /> 
										<input type="radio" name='security' value='1'> <spring:message code="secfw.page.field.approval.approvalType.confidential" /> 
										<input type="radio" name='security' value='2'> <spring:message code="secfw.page.field.approval.approvalType.topSecret" /><br>
										-->
										<input type='checkbox' name='priority' value='1'><spring:message code="secfw.page.field.approval.approvalType.urgent" />
									</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<th><spring:message code="secfw.page.field.approval.reserved" /></th>
									<td>
	                                    <div class='fL' style='margin:2px 4px 0 0'><input type='checkbox' id='checkReserved' name='checkReserved'><spring:message code="secfw.page.field.approval.checkReserved" /></div>
	                                    <div class='fL'>
	                                    <input type="text" id='reservedDay' name='reservedDay' value="<%=DateUtil.getTime("yyyy-MM-dd")%>" class="text_calendar02" readonly="readonly" />
	                                    </div>
	                                    <div class='fL'>
	                                    	&nbsp;
	                                        <spring:message code="las.page.field.log.hour"/>
	                                        <select id='reservedTime' name='reservedTime'>
	                                        <%
	                                            for(int i=0; i<24; i++) {
	                                        %>
	                                        <option value='<%=StringUtil.lpad(String.valueOf(i),2,'0') %>'><%=StringUtil.lpad(String.valueOf(i),2,'0') %></option><spring:message code="secfw.page.field.approval.reservedTime" />
	                                        <%
	                                            }
	                                        %>                                      
	                                        </select>
	                                        
	                                    </div>                  
                                    </td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<th><spring:message code="secfw.page.field.approval.notification" /></th>
									<td>
										<input type="radio" name='noti_mail' value='0' checked> <spring:message code="secfw.page.field.approval.notification.selected" /> 
										<input type="radio" name='noti_mail' value='1'> <spring:message code="secfw.page.field.approval.notification.all" /> 
									</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<th><spring:message code="secfw.page.field.approval.grantApprovalRight" /></th>
									<td>
										<select id='receiversRight' name='receiversRight' multiple="multiple" size='7' class='InputList100' style='width:98%;height:70px; padding:5px;'>
										</select>								
									</td>
									<td>
										<table cellspacing="0" cellpadding="0" id='opa'>
											<tr>
												<td><input type='checkbox' id='receiversRightType0' name='receiversRightType' value='0'> <spring:message code="secfw.page.field.approval.grantApprovalRight.changeLine" /></td>
											</tr>
											<tr>
												<td><input type='checkbox' id='receiversRightType1' name='receiversRightType' value='1'> <spring:message code="secfw.page.field.approval.grantApprovalRight.modifyText" /></td>
											</tr>
											<tr>
												<td><input type='checkbox' id='receiversRightType2' name='receiversRightType' value='2'> <spring:message code="secfw.page.field.approval.grantApprovalRight.arbitrary" /></td>
											</tr>
										</table>								
									</td>
								</tr>
							</table>
						</div>
						<!-- 상세옵션설정 End -->         
					</td>
				</tr>
			</table>
			
			<div class='btnwrap'>
				<div class='btnb'>
					<span class="btn_all_w"><span class="sangsin"></span><a href="#"><spring:message code="secfw.page.field.approval.submit" /></a></span>
				</div>
			</div>
			<br>
			</form:form>
		</div>
	</div>


	<!--footer -->
	<div class="pop_footer">
		<div class="btn_wrap fR" >
			<span class="btn_all_w"><span class="preview"></span><a href="javascript:openArbitraryPop();"><spring:message code="clm.page.msg.manage.conclRule" /></a></span>
			<span class="btn_all_w mR5"><span class="cancel"></span><a href="#" onclick="javascript:self.close();"><spring:message code="clm.page.button.cancel" /></a></span>
		</div>
	</div>
	<!-- //footer -->
	<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</body>
<!-- 
<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
	var wecObj = document.wec;
	wecObj.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
	wecObj.SetDefaultFontSize("9");
	wecObj.EditMode = 0;
	wecObj.Value = document.frm.body.value;
</SCRIPT>
 -->
</html>