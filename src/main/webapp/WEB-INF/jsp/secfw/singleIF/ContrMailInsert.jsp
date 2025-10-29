<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sds.secframework.common.util.Token" %>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ page import="com.sds.secframework.common.util.DateUtil" %>

<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Hashtable" %>
<%@ page import="java.util.ArrayList" %>
<%@page import="java.util.Locale"%>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@page import="org.springframework.web.servlet.support.RequestContext"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ page import="com.sec.clm.review.dto.ConsultationForm" %>

<%--
/**
 * 파	일	명	   : ContrMailInsertAS.jsp
 * 프로그램명	   	   : 메일내역 발송록 페이지
 * 설			명 :  
 * 작     성		자 : 박정훈
 * 작     성		일 : 2013.044
 */
--%>

<%
	
	boolean authYN_RA02 = false;
	
	ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList");
		
	if(tmpSessionRoleList.contains("RA02")){
		authYN_RA02 = true;
	}
	//detail 데이터 처리를 위해 호출 신성우
	ConsultationForm command = (ConsultationForm) request.getAttribute("command");
	ListOrderedMap lomRq = (ListOrderedMap) request.getAttribute("lomRq");
	String modYn = (String)request.getAttribute("modYn");

	String mailTitle    = StringUtil.bvl((String)request.getAttribute("mailTitle"),"");
	String mailContent  = StringUtil.bvl((String)request.getAttribute("mailContent"),"");
	
	String mailHtmlGbn = "";
	if(lomRq.get("cnsdreq_id") == null){
		mailHtmlGbn  = StringUtil.bvl((String)request.getAttribute("mailHtmlGbn"),"1");
	}else{
		mailHtmlGbn  = StringUtil.bvl((String)request.getAttribute("mailHtmlGbn"),"0");
	}
	String mailEncoding = StringUtil.bvl((String)request.getAttribute("mailEncoding"),"UTF-8");
	
	
	Vector UserPathVector    = (Vector)request.getAttribute("UserPathVector");
	Vector UserRightVector    = (Vector)request.getAttribute("UserRightVector");
	
	int UserPathlistSize = 0;
	
	if(UserPathVector != null && UserPathVector.size()>0) {
		UserPathlistSize = UserPathVector.size();
	}
	
	//사용자 로케일
	Locale lc = new RequestContext(request).getLocale();
	String locale = StringUtil.bvl(lc.getLanguage(), "en");
	String localeDisplayType = "";
	
	if("en".equals(locale)) {
		localeDisplayType = "Eng";
	} 
	
%>
<html>
<head>
<meta charset="utf-8" />

<title>mail</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet"/>
<!-- [if IE]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>

<script type="text/javascript" src="/crosseditor/js/namo_scripteditor.js"></script>
<script language="javascript">
<!--
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
    
	//메일수신 타입 초기화 설정
	$('#rcvTypeT').attr('checked', true);
	
	//수신자 정보 세팅
	   
	var userInfoList = new Array();
<%
 	if(UserPathVector != null && UserPathVector.size()>0) {
	    for(int idx=0;idx < UserPathVector.size();idx++){
	    	
	    	Hashtable ht = (Hashtable)UserPathVector.get(idx);
			    	
%> 	
    var userInfo = new Object();
	var UserPathHtml = "<%=ht.get("userid") %>";
	
	userInfo.cn                       = "<%=ht.get("cn") %>";//전체 이름
	userInfo.title                    = "<%=ht.get("title") %>";//직급명
	userInfo.department               = "<%=ht.get("department") %>";//부서명
	userInfo.o                        = "<%=ht.get("o") %>";//회사명
	userInfo.mail                     = "<%=ht.get("mail") %>";//메일주소
	
<%--
    userInfo.departmentnumber         = "<%=ht.get("departmentnumber") %>";//부서코드
    userInfo.employeenumber           = "<%=ht.get("employeenumber") %>";//사번
    userInfo.employeetype             = "<%=ht.get("employeetype") %>";//사용자구분
    userInfo.epbusicode               = "<%=ht.get("epbusicode") %>";//사업장 코드
    userInfo.o                        = "<%=ht.get("o") %>";//회사명
    userInfo.eppostaladdress          = "<%=ht.get("eppostaladdress") %>";//회사주소
    userInfo.epsuborgname             = "<%=ht.get("epsuborgname") %>";//총괄명
    userInfo.epbusiname               = "<%=ht.get("epbusiname") %>";//사업장명
    userInfo.department               = "<%=ht.get("department") %>";//부서명
    userInfo.epregionname             = "<%=ht.get("epregionname") %>";//지역명
    userInfo.cn                       = "<%=ht.get("cn") %>";//전체 이름
    userInfo.sn                       = "<%=ht.get("sn") %>";//성
    userInfo.givenname                = "<%=ht.get("givenname") %>";//이름
    userInfo.description              = "<%=ht.get("description") %>";//담당업무
    userInfo.title                    = "<%=ht.get("title") %>";//직급명
    userInfo.epgradename              = "<%=ht.get("epgradename") %>";//직위명
    userInfo.epsendcompanyname        = "<%=ht.get("epsendcompanyname") %>";//파견 회사명
    userInfo.epsendsuborgname         = "<%=ht.get("epsendsuborgname") %>";//파견 총괄명
    userInfo.epsenddeptname           = "<%=ht.get("epsenddeptname") %>";//파견 부서명
    userInfo.epsendtitlename          = "<%=ht.get("epsendtitlename") %>";//파견 직급명
    userInfo.epsendgradename          = "<%=ht.get("epsendgradename") %>";//파견 직위명
    userInfo.epid                     = "<%=ht.get("epid") %>";//EPID
    userInfo.eporganizationnumber     = "<%=ht.get("eporganizationnumber") %>";//회사코드
    userInfo.epregioncode             = "<%=ht.get("epregioncode") %>";//지역코드
    userInfo.epsuborgcode             = "<%=ht.get("epsuborgcode") %>";//총괄코드
    userInfo.eptitlenumber            = "<%=ht.get("eptitlenumber") %>";//직급코드
    userInfo.epuserstatus             = "<%=ht.get("epuserstatus") %>";//임직원 상태
    userInfo.l                        = "<%=ht.get("l") %>";//회사주소1
    userInfo.mail                     = "<%=ht.get("mail") %>";//메일주소
    userInfo.mobile                   = "<%=ht.get("mobile") %>";//핸드폰
    userInfo.postaladdress            = "<%=ht.get("postaladdress") %>";//회사 주소 2
    userInfo.postalcode               = "<%=ht.get("postalcode") %>";//회사 우편번호
    userInfo.telephonenumber          = "<%=ht.get("telephonenumber") %>";//회사 전화번호
    userInfo.userid                   = "<%=ht.get("userid") %>";//마이싱글ID
    userInfo.eptitlesortorder         = "<%=ht.get("eptitlesortorder") %>";//직급 정렬 순서
    userInfo.epsendtitlesortorder     = "<%=ht.get("epsendtitlesortorder") %>";//파견 직급 정렬 순서
    userInfo.c                        = "<%=ht.get("c") %>";//국가
    userInfo.dn                       = "<%=ht.get("dn") %>";//dn
    userInfo.epdefaultcompcode        = "<%=ht.get("epdefaultcompcode") %>";//기본소속구분코드
    userInfo.epgradeortitle           = "<%=ht.get("epgradeortitle") %>";//직급/직위 표기방식
    userInfo.episblue                 = "<%=ht.get("episblue") %>";//임원여부
    userInfo.eppreferredlanguage      = "<%=ht.get("eppreferredlanguage") %>";//표현언어
    userInfo.epsecuritylevel          = "<%=ht.get("epsecuritylevel") %>";//보안등급
    userInfo.epsendbusicode           = "<%=ht.get("epsendbusicode") %>";//파견사업장코드
    userInfo.epsendcompanycode        = "<%=ht.get("epsendcompanycode") %>";//파견회사코드
    userInfo.epsenddeptcode           = "<%=ht.get("epsenddeptcode") %>";//파견부서코드
    userInfo.epsendgradeortitle       = "<%=ht.get("epsendgradeortitle") %>";//파견사 직급/직위 표기방식
    userInfo.epsendregioncode         = "<%=ht.get("epsendregioncode") %>";//파견지역코드
    userInfo.epsendsecuritylevel      = "<%=ht.get("epsendsecuritylevel") %>";//파견보안등급
    userInfo.epsendsuborgcode         = "<%=ht.get("epsendsuborgcode") %>";//파견총괄코드
    userInfo.epsendtitlenumber        = "<%=ht.get("epsendtitlenumber") %>";//파견직급코드
    userInfo.epuserlevel              = "<%=ht.get("epuserlevel") %>";//사용자등급
    userInfo.facsimiletelephonenumber = "<%=ht.get("facsimiletelephonenumber") %>";//회사팩스번호
    userInfo.nickname                 = "<%=ht.get("nickname") %>";//Nickname
    userInfo.preferredlanguage        = "<%=ht.get("preferredlanguage") %>";//메일자동응답언어
    userInfo.epvoipnumber             = "<%=ht.get("epvoipnumber") %>";//인터넷전화
    userInfo.mailHost                 = "<%=ht.get("mailHost") %>";//메일호스트
    userInfo.epindeptcode             = "<%=ht.get("epindeptcode") %>";//내부부서코드
    userInfo.epjob                    = "<%=ht.get("epjob") %>";//직무코드
    userInfo.epjobname                = "<%=ht.get("epjobname") %>";//직무명
    userInfo.epindeptcodename         = "<%=ht.get("epindeptcodename") %>";//내부부서명
    userInfo.epwithdrawdate           = "<%=ht.get("epwithdrawdate") %>";//퇴직/휴직일
    userInfo.epnative                 = "<%=ht.get("epnative") %>";//현채인여부
    userInfo.epuserclassify           = "<%=ht.get("epuserclassify") %>";//실가명구분
    userInfo.serverlocation           = "<%=ht.get("serverlocation") %>";//서버위치
--%>
    userInfoList[<%=idx%>] = userInfo; 
    
<%
    }
}
%>
    //setCPUserInfos(userInfoList);
    
	$("#receiver_nm").keypress(function(event){
		if(event.keyCode == "13") {
			searchUser();
			$("#receiver_nm").val('');
			return false;			
		}
	});
 
	$('#tCnt').html(0);
	$('#cCnt').html(0);
	$('#bCnt').html(0);
	
	setCPUserInfos(userInfoList); // 수신,참조,비밀참조 카운트 표시 2013.10.18

	//수신자 검색-ESB임직원 검색
	$('#srchReceiverBtn1').click(function(){
		searchUser();
		return;
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
			if(optionValue == '<%=mailEncoding%>') {
				$(this).attr('selected', true);
			};
		});
    

	//메일수신 타입 변경	$('input[name=rdSendItem]:radio:checked')
	$('input[name=rdSendItem]:radio').click(function(event){
		var target = event.target;
		//alert(target.id);
		if(target.id == "rcvTypeT") {
			$('#receivers option:selected')
				.each(function(){
					var originValue = $(this).val();
					var originText  = $(this).text();

					var newValue;
					var newText;

					if(originValue.indexOf("c|") >= 0) {
						newValue = replaceString(originValue, "c|", "t|");
						newText  = replaceString(originText,  "/ " + "<spring:message code='secfw.page.field.mail.rcvTypeC' />" + " /", "/ " + "<spring:message code='secfw.page.field.mail.rcvTypeT' />" + " /");
					} else if(originValue.indexOf("b|") >= 0) {
						newValue = replaceString(originValue, "b|", "t|");
						newText  = replaceString(originText,  "/ " + "<spring:message code='secfw.page.field.mail.rcvTypeB' />" + " /", "/ " + "<spring:message code='secfw.page.field.mail.rcvTypeT' />" + " /");
					} else {
						newValue = originValue;
						newText  = originText;
					}
					
					$(this).val(newValue);
					$(this).text(newText);
				});
		} else if(target.id == "rcvTypeC") {
			$('#receivers option:selected')
			.each(function(){
				var originValue = $(this).val();
				var originText  = $(this).text();
				
				var newValue;
				var newText;

				if(originValue.indexOf("t|") >= 0) {
					newValue = replaceString(originValue, "t|", "c|"); 
					newText  = replaceString(originText,  "/ " + "<spring:message code='secfw.page.field.mail.rcvTypeT' />" + " /", "/ " + "<spring:message code='secfw.page.field.mail.rcvTypeC' />" + " /");					
				} else if(originValue.indexOf("b|") >= 0) {
					newValue = replaceString(originValue, "b|", "c|");
					newText  = replaceString(originText,  "/ " + "<spring:message code='secfw.page.field.mail.rcvTypeB' />" + " /", "/ " + "<spring:message code='secfw.page.field.mail.rcvTypeC' />" + " /");
				} else {
					newValue = originValue;
					newText  = originText;
				}
				$(this).val(newValue);
				$(this).text(newText);
			});
		} else if(target.id == "rcvTypeB") {
			$('#receivers option:selected')
			.each(function(){
				var originValue = $(this).val();
				var originText  = $(this).text();

				var newValue;
				var newText;

				if(originValue.indexOf("t|") >= 0) {
					newValue = replaceString(originValue, "t|", "b|");
					newText  = replaceString(originText,  "/ " + "<spring:message code='secfw.page.field.mail.rcvTypeT' />" + " /", "/ " + "<spring:message code='secfw.page.field.mail.rcvTypeB' />" + " /");
				} else if(originValue.indexOf("c|") >= 0) {
					newValue = replaceString(originValue, "c|", "b|");
					newText  = replaceString(originText,  "/ " + "<spring:message code='secfw.page.field.mail.rcvTypeC' />" + " /", "/ " + "<spring:message code='secfw.page.field.mail.rcvTypeB' />" + " /");
				} else {
					newValue = originValue;
					newText  = originText;
				}
				
				$(this).val(newValue);
				$(this).text(newText);
			});
		}

		$('#receivers').focus();
		setTypeCount();
	});

	/**
	* 임직원 조회 function
	*/	
	var searchUser = function() {

		var frm = document.frm;
		var srchValue = comTrim(frm.receiver_nm.value);
		
		if(srchValue == '' || getByteLength(srchValue) < 4) {
			alert('<spring:message code='secfw.msg.error.nameMinByte' />');
			frm.receiver_nm.focus();
			return false;
		}

		frm.srch_user_cntnt_type.value = 'userName';
		frm.srch_user_cntnt.value = frm.receiver_nm.value;
		
		PopUpWindowOpen('', 800, 450, false);
		frm.target = "PopUpWindow";

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
	$("a[id='cancel']").click(function(){
		pageAction('refresh');
	});

	// 메일발신
	$("a[id='mail']").click(function(){
	
		pageAction('send');
	});

	// 수신자 순서조정 - UP
	$('#moveUp').click(function(){
		moveOrder('U');
	});

	// 수신자 순서조정 - DOWN
	$('#moveDown').click(function(){
		moveOrder('D');
	});

	// 수신자삭제
	$('#deleteBtn').click(function(){				
		$('#receivers option:selected').remove();
		setTypeCount();
	});
		
	/**
	* 메일순서조정
	*/	
	var moveOrder = function(upDown)
	{
		var rightDocLen = frm.receivers.options.length;

		var tempName = "" ;
		var tempValue = "" ;

		if(upDown == "U") {
			for ( i = 0; i < rightDocLen; i++)
			{
				if( frm.receivers.options[i].selected && i != 0 && !frm.receivers.options[i - 1].selected)
				{
					tempName = frm.receivers.options[i - 1].value ;
					tempValue = frm.receivers.options[i - 1].text ;
	
					frm.receivers.options[i - 1].value = frm.receivers.options[i].value ;
					frm.receivers.options[i - 1].text  = frm.receivers.options[i].text;
	
					frm.receivers.options[i].value = tempName ;
					frm.receivers.options[i].text  = tempValue;
	
					frm.receivers.options[i - 1].selected = true ;
					frm.receivers.options[i].selected = false ;
				}
				else if( i == 0 && frm.receivers.options[i].selected)
				{
					frm.receivers.options[i].selected = true ;
				}
			}
		} else {
			for ( i = (rightDocLen - 1); i >= 0; i--)
			{
				if( frm.receivers.options[i].selected && i != (rightDocLen - 1) && !frm.receivers.options[i + 1].selected)
				{
					tempName = frm.receivers.options[i + 1].value ;
					tempValue = frm.receivers.options[i + 1].text ;
	
					frm.receivers.options[i + 1].value = frm.receivers.options[i].value ;
					frm.receivers.options[i + 1].text  = frm.receivers.options[i].text;
	
					frm.receivers.options[i].value = tempName ;
					frm.receivers.options[i].text  = tempValue;
	
					frm.receivers.options[i + 1].selected = true ;
					frm.receivers.options[i].selected = false ;
				}
				else if( i == (rightDocLen - 1) && frm.receivers.options[i].selected)
				{
					frm.receivers.options[i].selected = true ;
				};
			};
		};
	};

	/**
	* Text - HTML 변환
	*/
	if("0" == '<%=mailHtmlGbn%>') {
		$('#body_type0').attr('checked',true);
        $('input[name=checkedBodyType]').val('0');
		$('#textEdit').show();
		$('#htmlEdit').hide();
	} else if("1" == '<%=mailHtmlGbn%>') {
		$('#body_type1').attr('checked',true);
        $('input[name=checkedBodyType]').val('1');
		$('#textEdit').hide();
		$('#htmlEdit').show();
	}
	
	$('input[name=body_type]').click(function() {
		var typeValue = $(this).val();
		bodyChange(typeValue);
	});
	
	var bodyChange = function(iType) {		
	    var wecObj  = document.getElementById("wec");
		var textObj = document.getElementById("body");
	   
		//var strBody  = wecObj.Value;
	    var strBody1 = textObj.value;
	    //var strBody2 = wecObj.BodyValue; //0903 추가
	    var strBody2 =''; // CrossEditor.GetBodyValue();
	    
	    if ( iType =="0" ) {
	        if ($('input[name=checkedBodyType').val() == "0")
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
	        
	        textObj.value = stripHTML(strBody2); //0903 추가
	        document.frm.checkedBodyType.value = '0';
	        textObj.focus();
	    } else {

	        if ($('input[name=checkedBodyType]').val() == "1")
	            return;

			$('#textEdit').hide();
			$('#htmlEdit').show();

			//alert(document.frm.checkedBodyType.value);
			//wecObj.SetEditMode = 1;
			//wecObj.EditMode = 1;			
			//wecObj.Value = "<HTML><HEAD>" + document.frm.wec.HeadValue +"</HEAD><BODY>"+ textToHtml(strBody1) + "</BODY></HTML>";
						
			//CrossEditor.setValue("<HTML><HEAD></HEAD><BODY>"+ textToHtml(strBody1) + "</BODY></HTML>");
	        document.frm.checkedBodyType.value = '1';	        
	        //wecObj.focus();
	    };
	}

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

		//본문입력창 저장된 값으로 resize
		onLoadBodyHeight(bodyHeight);
	}

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
			}

		iFormat = parseInt(iFormat);
	    if (isNaN(iFormat)) iFormat=100;

	    return;
	}

	//텍스트-HTML 초기설정
	bodyChange('<%=mailHtmlGbn%>');

	//본문 사이즈 체크
	var isOverDocSize = function (limit) {
		return true;
		/*
	    var object1 = document.wec.GetDocumentSize();
	    if(object1 > limit*1024) return true;
	    else return false;
	    */
	}

	/**
	* 버튼 동작 부분
	*/
	var pageAction = function(flag){
		var frm = document.frm;
	
		if(flag == "send"){

			//테스트를 위하여 개발인력 추가
			//var html = "<option value='t|namwon.shin@stage.samsung.com'>"
         	//	+ '신남원' + " / " + '수신' + " / " + '선임' + " / " + '협력사' + " / " + '유비포럼' + " / " + 'namwon.shin@samsung.com'
         	//	+ "</option>";
			//$('#receivers').append(html);	

			//상태값설정
			frm.status.value = '0';			
			
			//수신인여부 체크
			var receiverCnt = 0;
			$('#receivers option')
			.each(function(){
				receiverCnt = receiverCnt + 1;
			});
									
			if(!(receiverCnt > 0)) {
				//수신인을 입력해 주십시오.
				alert('<spring:message code="secfw.msg.mail.alert2" />');
				return;
			}
			
			if($('input[name=subject]').val() == "") {
				//제목을 입력해 주십시오.
				alert('<spring:message code="secfw.msg.mail.subject" />');
				return;
			}
			
			//본문의 크기 제한(1024KB 이하로)
			 if($('input[name=checkedBodyType]').val() == "1") {
				 /*
				if(isOverDocSize(1024)) {
	                //alert('본문의 크기는 1M를 초과할수 없습니다.');
	                alert( '<spring:message code="secfw.msg.mail.alert3" />');
	                return;
	            };
	            */			
				frm.body.value = CrossEditor.GetValue();	 
	        } else {
				 var textVal  = $('textarea[name=body]')[0].value;
				 if(CheckByte(textVal) > 1024*1024 ) {
								 alert( '<spring:message code="secfw.msg.mail.alert3" />');
					 return;
				 };
	        }
			 			 
			//수신인 제한 (100명 이하)
		    if(document.frm.receivers.options.length > 100) {
		        alert('<spring:message code="secfw.msg.mail.alert5" />');
            	return;
        	}
			
			//첨부파일 갯수 제한(50개이하)
			frm.body_mime.value = CrossEditor.GetValue(); //GetBodyValue(); 
			
			//FORM Validation
			if(validateForm(document.frm) != false) { 
				
				$('#receivers option')
				.each(function(){
					$(this).attr("selected", true);
				});

								
				//첨부파일 업로드
				fileList.UploadFile(function(uploadCount){
					
					if(uploadCount == -1){
						initPage();
						alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
						return;
					}
					
					var options = {
							url: "<c:url value='/secfw/esbMail.do?method=sendMail2' />",
							type: "post",
							async: false,
							dataType: "json",
							success: function(responseText, statusText) {
								alert(responseText.returnMessage);
								window.close();
							}
						};
					$("#frm").ajaxSubmit(options);			
				});
				
								
				frm.target = '_self';
				frm.action = "<c:url value='/clm/manage/myContract.do' />";
				frm.method.value = "listMyContract";
				viewHiddenProgress(true) ;
				frm.submit();
				
			};				
			
			
		} else if(flag == "refresh"){
			frm.target = '_self';
			frm.action = "<c:url value='/clm/manage/myContract.do' />";
			frm.method.value = "listMyContract";
			viewHiddenProgress(true) ;
			frm.submit();

		} else if(flag == "temp"){

			//수신인여부 체크
			var receiverCnt = 0;

			$('#receivers option')
			.each(function(){
				receiverCnt = receiverCnt + 1;
			});

			if(!(receiverCnt > 0)) {
				//수신인을 입력해 주십시오.
				alert('<spring:message code="secfw.msg.mail.alert2" />');
				return;
			}

			//Form Validation
			if(validateForm(document.frm) != false) { 
				
				//첨부파일 업로드
				fileList.UploadFile(function(uploadCount){
					if(uploadCount == -1){
						initPage();
						alert("<spring:message code='secfw.msg.error.fileUploadFail' />");
						return;
					}
				});
//				var uploadCount = fileList.UploadFile();
	
				$('#receivers option')
				.each(function(){
					$(this).attr("selected", true);
				});
						
				frm.status.value = 'T';
	
				$('#receivers option')
				.each(function(){
					$(this).attr("selected", true);
				});

				frm.target = '_self';
				frm.action = "<c:url value='/secfw/esbMail.do' />";
			    frm.method.value = "sendMail2";
			    viewHiddenProgress(true) ;
				frm.submit();
				
			}
		} else if(flag == "close"){
			self.close();
		} 
	};
	
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
	* 수신, 참조, 비밀참조 수 설정
	*/	
	function setTypeCount() {

		var totalTCnt = 0;
		var totalCCnt = 0;
		var totalBCnt = 0;

		$('#receivers option')
			.each(function(){
	
				var val = $(this).val();
				
				if(val.substr(0,1)=='t') {
					totalTCnt++;
				} else if(val.substr(0,1)=='c') {
					totalCCnt++;
				} else if(val.substr(0,1)=='b') {
					totalBCnt++;
				}; 
		});
	
		$('#tCnt').html(totalTCnt);
		$('#cCnt').html(totalCCnt);
		$('#bCnt').html(totalBCnt);
		
	}

	/**
	* 임직원정보 Setting
	*/	
	function setUserInfos(obj) {
	
		var name     = obj.cn;
		var type;
		var jikgupCd = obj.eptitlenumber;
		var jikgupNm = obj.title;
		var deptNm   = obj.department;
		var compNm   = obj.o;
		var email    = obj.mail;
		var typeNm;
	
		type = $('input[name=rdSendItem]:radio:checked').val();
	
		if(type == 't') {
			typeNm = '<spring:message code="secfw.page.field.mail.rcvTypeT" />';
		} else if(type == 'c') {
			typeNm = '<spring:message code="secfw.page.field.mail.rcvTypeC" />';
		} else if(type == 'b') {
			typeNm = '<spring:message code="secfw.page.field.mail.rcvTypeB" />';
		}
		
		if(email == null || comTrim(email) == '') {
			alert("<spring:message code='secfw.page.field.singleIF.noEmailAccount'/>");
			return;
		}
	
		var flag = true;
		
		//기존 추가된 사용자 인지 체크
		$('#receivers option')
		.each(function(){
			var originValue = $(this).val();
			if(originValue.indexOf(email) >= 0) {
				flag = false;
			};
		});
	
		if(!flag) {
			//이미 등록되어 있습니다.
			alert('<spring:message code="secfw.msg.mail.alert4" />');
			return;
		} else {
			var html = "<option value='" + type + "|" + email + "'>"
	         + name + " / " + typeNm + " / " + jikgupNm + " / " + deptNm + " / " + compNm + " / " + email
	         + "</option>";
	         
			$('#receivers').append(html);
		}

		setTypeCount();
	}

	/**
	* 임직원정보 Setting
	*/	
	function setCPUserInfos(listObj) {
   
		for(var idx=0; idx < listObj.length; idx++) {

			var obj = listObj[idx];
			
			var name     = obj.cn;
			var type;
			var jikgupNm = obj.title;
			var deptNm   = obj.department;
			var compNm   = obj.o;
			var email    = obj.mail;
			var typeNm;
			
			type = $('input[name=rdSendItem]:radio:checked').val();
		
			if(type == 't') {
				typeNm = '<spring:message code="secfw.page.field.mail.rcvTypeT" />';
			} else if(type == 'c') {
				typeNm = '<spring:message code="secfw.page.field.mail.rcvTypeC" />';
			} else if(type == 'b') {
				typeNm = '<spring:message code="secfw.page.field.mail.rcvTypeB" />';
			}
			
			if(email == null || comTrim(email) == '') {
				alert(name + "<spring:message code='secfw.page.field.singleIF.noEmailAccount'/>");
				return;
			}
		
			var flag = true;
			
			//기존 추가된 사용자 인지 체크
			$('#receivers option')
			.each(function(){
				var originValue = $(this).val();
				if(originValue.indexOf(email) >= 0) {
					flag = false;
				};
			});
		 
			if(!flag) {
				//alert('이미 등록된 사용자 입니다.');
				continue;
			} else {
				
				var html = "<option value='" + type + "|" + email + "'>"
		         + name + " / " + typeNm + " / " + jikgupNm + " / " + deptNm + " / " + compNm + " / " + email
		         + "</option>";
				
				$('#receivers').append(html);
			};
		}

		setTypeCount();
	}

<%	if(lomRq.get("cnsdreq_id") != null){ %>

	$(window).load(function(){	
		namoView();
	});
	
	function namoView(){
		
		//나모 내용 display

		//1. 검토요청내용
	    document.getElementById('if_cnsd_demnd_cont').contentWindow.document.body.innerHTML = document.getElementById('if_cnsd_demnd_cont_textarea').value;

	 	//2. 추진목적 및 배경
	 	document.getElementById('if_cnsd_bg_cont').contentWindow.document.body.innerHTML = document.getElementById('if_cnsd_bg_cont_textarea').value;

	 	//3. 검토의견
	 	if("<c:out value='${modYn}'/>" == "N"){	 		
	 		if("C04303" == "<c:out value='${lomMn.cnsd_status}'/>"){	 			
	 		 	//검토의견
	 		 	document.getElementById('if_cnsd_opnn').contentWindow.document.body.innerHTML = document.getElementById('if_cnsd_opnn_textarea').value;
	 		 }
	 		 		
	 		if("C04305" == "<c:out value='${lomMn.cnsd_status}'/>"){
	 		 	//검토의견
	 		 	document.getElementById('if_cnsd_opnn').contentWindow.document.body.innerHTML = document.getElementById('if_cnsd_opnn_textarea').value;
	 		}
	 	}
	}
<% } %>
//-->
</script>
</head>
<body>
<div id="wrap">
	<div id="container">
    	
    	<!-- location -->
		<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" BORDER="0" alt="Home" title="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
		<!-- //location -->
		
		<!-- title -->
		<div class="title">
			<h3><spring:message code="clm.page.title.ManageMailContract.listTitle" /></h3>
		</div>
		<!-- //title -->
		
		<!-- content -->
		<div id="content">
		
			<div id="content_in">
				<form:form id="frm" name="frm" method="post" autocomplete="off">
					<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>">
					<!-- ESB사용자검색 -->
					<input type='hidden' name='srch_user_cntnt_type' />
					<input type='hidden' name='srch_user_cntnt' />
					<input type='hidden' name='doSearch' value='Y' />
				
					<!-- Controller Method -->
					<input type='hidden' name='method' value='' />
				
					<input type='hidden' name='msg_key' value='11' />
				
					<!-- 텍스트-편집기입력 구분 -->
					<input type='hidden' name='checkedBodyType' value='' />
					<input type='hidden' name='body_mime'       value='' />
				
					<!-- 첨부파일정보 -->
					<input type="hidden" name="fileInfos" 	value="" />
					<input type="hidden" name="sys_gbn" 	value="mail" />
					<input type="hidden" name="view_gbn" 	value="upload" />
				
					<!-- 이중등록 방지 -->
					<input type="hidden" name="status" 	value="" />
					<input type="hidden" name="TOKEN"  value="<%=Token.set(request) %>">
					
					<!-- 리스트 검색 조건  -->
					<input type="hidden" name="status_mode" id="status_mode" value="mail">
					<input type="hidden" name="curPage" id="curPage" value="<c:out value='${command.curPage}'/>">
					<input type="hidden" name="srch_review_title" value="<c:out value='${command.srch_review_title}' escapeXml='false'/>" />		<!-- 의뢰명 -->
					<input type="hidden" name="srch_reqman_nm" value="<c:out value='${command.srch_reqman_nm}' escapeXml='false'/>" />				<!-- 의뢰자 -->
					<input type="hidden" name="srch_start_reqday" value="<c:out value='${command.srch_start_reqday}' escapeXml='false'/>" />		<!-- 의뢰 시작일 -->
					<input type="hidden" name="srch_end_reqday" value="<c:out value='${command.srch_end_reqday}' escapeXml='false'/>" />			<!-- 의뢰 종료일 -->
					<input type="hidden" name="srch_start_cnlsnday" value="<c:out value='${command.srch_start_cnlsnday}' escapeXml='false'/>" />	<!-- 계약 시작일 -->
					<input type="hidden" name="srch_end_cnlsnday" value="<c:out value='${command.srch_end_cnlsnday}' escapeXml='false'/>" />		<!-- 계약 종료일 -->
					<input type="hidden" name="srch_resp_dept_nm" value="<c:out value='${command.srch_resp_dept_nm}' escapeXml='false'/>" />		<!-- 담당부서명 -->
					<input type="hidden" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}' escapeXml='false'/>" />			<!-- 담당자명 -->
					<input type="hidden" name="srch_biz_clsfcn" value="<c:out value='${command.srch_biz_clsfcn}' escapeXml='false'/>" />			<!-- 계약단계 -->
					<input type="hidden" name="srch_cnsdman_nm" value="<c:out value='${command.srch_cnsdman_nm}' escapeXml='false'/>" />			<!-- 검토자 -->
					<input type="hidden" name="srch_oppnt_nm" value="<c:out value='${command.srch_oppnt_nm}' escapeXml='false'/>" />				<!-- 계약상대방 -->
					<input type="hidden" name="srch_state" value="<c:out value='${command.srch_state}' escapeXml='false'/>" />						<!-- 상태 -->
					<input type="hidden" name="srch_cnclsnpurps_bigclsfcn" value="<c:out value='${command.srch_cnclsnpurps_bigclsfcn}' escapeXml='false'/>" />	<!-- 체결목적-->
							
					<table width='100%' height='100%' cellpadding='0' cellspacing='0'>
						<tr>
							<td id='bodyDivIn'>						
										
							 <!-- button -->
				            <div class="t_titBtn">
				            	<div class="btn_wrap_t">
									<span class="btn_all_w"><span class="cancel"></span><a href="#" id="cancel"><spring:message code="secfw.page.field.approval.cancel" /></a></span>
								</div>
				                <div class="btn_wrap_t">
				                    <span class="btn_all_w"><span class="mail"></span><a href="#" id="mail"><spring:message code="secfw.page.button.send" /></a></span>
				                </div>
				            </div>
				            <!-- //button -->
		           			</td>
		           		</tr>
		           	</table>
					<!-- 계약정보 start 신성우-->
					<c:if test="${lomRq.cnsdreq_id != null}">
						<!-- title -->
					    <div class="title_basic">
							<h4><spring:message code="las.page.field.contractmanager.consideration_d.cnsd_default_info" /><!-- 계약 정보 --></h4>
						</div>
						<!-- //title -->
						<div id="tr_down02">
							<table cellspacing="0" cellpadding="0" class="table-style01">
								<colgroup>
									<col width="15%" />
									<col width="9%" />
									<col width="18%" />
									<col width="13%" />
									<col width="16%" />
									<col width="13%" />
									<col width="16%" />
								</colgroup>
								<tr>
									<th><spring:message code="las.page.field.contractmanager.consideration_d.cnsd_title" /><!-- 의뢰명 --></th>
									<td colspan="6"><span class="fL"> 
									<c:out value="${lomRq.req_title}" escapeXml='false'/></span>
									<input type="hidden" name="cnsdreq_id" id="cnsdreq_id" value="<c:out value='${lomRq.cnsdreq_id}' />" />
									</td>
								</tr>
								<tr>
									<th style="border-top:0px;"><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrt_nm" /><!-- 계약명 --></th>
									<td colspan="4"><span class="fL">
									<input type="hidden" name="cntrt_nm" id="cntrt_nm" value="<c:out value="${lomRq.cntrt_nm}" escapeXml='false'/>" /> 
									<c:out value="${lomRq.cntrt_nm}" escapeXml='false'/></span></td>
									<th><spring:message code="las.page.field.contractManager.contractId"/><!-- 계약ID --></th>
									<td><span class="fL"><c:out value="${lomRq.cntrt_no}" /></span></td>
								</tr>
										
								<tr>
									<th><spring:message code="las.page.field.contractmanager.consideration_d.reqman_nm" /></th><!-- 의뢰자 -->
									<td colspan="2"><c:out value="${lomRq.reqman_nm}" escapeXml='false'/> / <c:out value="${lomRq.reqman_jikgup_nm}" escapeXml='false'/> / <c:out value="${lomRq.req_dept_nm}" escapeXml='false'/></td>
									<th><spring:message code="las.page.field.contractmanager.consideration_d.req_dt" /></th><!-- 의뢰일시 -->
									<td><c:out value="${lomRq.req_dt}" /></td>
									<th><spring:message code="las.page.field.contractmanager.consideration_d.re_demndday" /></th><!-- 회신요청일 -->
									<td><c:out value="${lomRq.re_demndday}" /></td>
								</tr>
								<tr>
									<th><spring:message code="las.page.field.contractManager.personInCharge"/><!-- 담당자 --></th>
									<td colspan="4"><c:out value="${lomRq.cntrt_respman_nm}" escapeXml='false'/> / <c:out value="${lomRq.cntrt_respman_jikgup_nm}" escapeXml='false'/> / <c:out value="${lomRq.cntrt_resp_dept_nm}" escapeXml='false'/></td>
									<th><spring:message code="las.page.field.contractManager.reviewer"/><!-- 검토자 --></th>
									<td><c:out value="${lomRq.cnsdman_info}" /></td>
								</tr>
								<tr>
									<th style="border-top:0px;"><spring:message code="las.page.field.contractmanager.consideration_d.relation_man" /><!-- 관련자 --></th>
								  	<td colspan="6">
									  	<span id="id_trgtman_nm">
										  	<c:forEach var="list" items="${lomRm}" varStatus="status">
												<c:out value='${list.relation_man}' escapeXml='false'/> 
													<input type="hidden" name="arr_demnd_seqno" id="arr_demnd_seqno" value="<c:out value='${status.count}' />" />
													<input type="hidden" name="arr_trgtman_id" id="arr_trgtman_id" value="<c:out value='${list.TRGTMAN_ID}' />" />
													<input type="hidden" name="arr_trgtman_nm" id="arr_trgtman_nm" value="<c:out value='${list.TRGTMAN_NM}' />" />
													<input type="hidden" name="arr_trgtman_jikgup_nm" id="arr_trgtman_jikgup_nm" value="<c:out value='${list.TRGTMAN_JIKGUP_NM}' />" />
													<input type="hidden" name="arr_trgtman_dept_nm" id="arr_trgtman_dept_nm" value="<c:out value='${list.TRGTMAN_DEPT_NM}' />" />
													<c:if test="${status.count > 0 && !status.last}">
					
														,&nbsp;&nbsp;
													</c:if>
													<c:if test="${status.count mod 2==0}">
														<br/>
													</c:if>
											</c:forEach>
										</span>
								  	</td>
								</tr>
								<c:if test="${!empty lomRq.cntrt_oppnt_nm || !empty lomRq.cntrt_oppnt_rprsntman || !empty lomRq.cntrt_oppnt_type_nm}">
								<tr>
									<th><spring:message code="clm.page.msg.common.otherParty" /><!-- 계약상대방 --></th>
									<td colspan="2"><a href="javascript:customerPop('<c:out value="${lomRq.cntrt_oppnt_cd}" />','<c:out value="${lomRq.cntrt_oppnt_cd}" />');"><c:out value="${lomRq.cntrt_oppnt_nm}" /></a></td>
									<th><spring:message code="clm.page.field.customer.registerNo" /><!-- Registration No  --></th>
									<td><c:out value="${lomRq.cntrt_oppnt_rprsntman}" escapeXml='false'/></td>
									<th><spring:message code="clm.page.field.customer.contractRequired" /><!-- Contract Required  --></th>
									<td><c:out value="${lomRq.cntrt_oppnt_type_nm}" escapeXml='false'/></td>
									
								</tr>
								</c:if>
								<c:if test="${!empty lomRq.cntrt_oppnt_respman || !empty lomRq.cntrt_oppnt_telno || !empty lomRq.cntrt_oppnt_email}">
								<tr>
									<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrt_oppnt_respman" /><!-- 상대방 담당자 --></th>
									<td colspan="2"><c:out value="${lomRq.cntrt_oppnt_respman}" escapeXml='false'/></td>
									<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.tel" /><!-- 담당자 전화번호 --></th>
									<td><c:out value="${lomRq.cntrt_oppnt_telno}" escapeXml='false'/></td>
									<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.email" /><!-- 담당자 E-mail --></th>
									<td><c:out value="${lomRq.cntrt_oppnt_email}" escapeXml='false'/></td>
								</tr>
								</c:if>
								<tr class="slide-target02 slide-area">			
									<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrt_type" /><!-- 계약유형 --></th>				
									<td colspan="6">
										<c:out value="${lomRq.biz_clsfcn_nm}" /> / 
										<c:out value="${lomRq.depth_clsfcn_nm}" /> / 
										<c:out value="${lomRq.cnclsnpurps_bigclsfcn_nm}" /> / 
										<c:out value="${lomRq.cnclsnpurps_midclsfcn_nm}" />
									</td>				
								</tr>
								<tr class="slide-target02 slide-area">
									<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrt_trgt_det" /><!-- 계약대상 상세 --></th>
									<td colspan='6'><c:out value="${lomRq.cntrt_trgt_det}" escapeXml='false'/></td>
								</tr>
								<tr>
									<th style="border-top:0px;"><spring:message code="las.page.field.contractmanager.consideration_inner_d.cntrtperiod" /></th><!-- 계약기간  -->
									<td colspan="4">
										<%=DateUtil.formatDate((String)lomRq.get("cntrtperiod_startday"),"-")%> <c:if test="${!empty lomRq.cntrtperiod_startday}">~</c:if> <%=DateUtil.formatDate((String)lomRq.get("cntrtperiod_endday"),"-")%>
									</td>
									<th style="border-top:0px;"><spring:message code="las.page.field.contractManager.chkPayCol"/><!-- 지불/수금 구분 --></th>
									<td><c:out value="${lomRq.payment_gbn_nm}" escapeXml='false'/><input type="hidden" id="cntrt_amt" value="<c:out value='${lomRq.cntrt_amt_chg}' />"/></td>
								</tr>	
					            <tr>
									<c:choose>
										<c:when test="${lomRq.cntrt_untprc_expl != ''}">
											<th><spring:message code="las.page.field.contractManager.contAmt"/><!-- 계약금액 --></th>
											<td colspan="2">
												<c:out value='${lomRq.cntrt_amt_chg}' />
												<input type="text" id="cntrt_amt" style="border:0px" width="100%" value="<c:out value='${lomRq.cntrt_amt_chg}' />" readonly="readonly"/>
											</td>
											<th><spring:message code="las.page.field.contractManager.contCost"/><!-- 계약단가 --></th>
											<td><spring:message code="las.page.field.contractManager.signUcost"/><!-- 단가로 체결 --></td>
											<th><spring:message code="las.page.field.contractManager.currcUnt"/><!-- 통화단위 --></th>
											<td><c:out value="${lomRq.crrncy_unit}" /></td>
										</c:when>
										<c:otherwise>
											<th><spring:message code="las.page.field.contractManager.contAmt"/><!-- 계약금액 --></th>
											<td colspan="4">
												<c:out value='${lomRq.cntrt_amt_chg}' />
												<input type="hidden" id="cntrt_amt" value="<c:out value='${lomRq.cntrt_amt_chg}'/>" readonly="readonly"/>
											</td>
											<th><spring:message code="las.page.field.contractManager.currcUnt"/><!-- 통화단위 --></th>
											<td><c:out value="${lomRq.crrncy_unit}" escapeXml='false'/></td>
										</c:otherwise>
									</c:choose>							
								</tr>
								<c:if test='${lomRq.cntrt_untprc_expl != "" }'>
								<tr>
									<th rowspan="2"><spring:message code='clm.page.field.contract.detail.unitcont' /><!-- 단가내역 요약 --></th>
									<td colspan="6">
										<c:out value="${lomRq.cntrt_untprc_expl}" escapeXml="false"/>                                                    
									</td>
								</tr>
								<tr>
									<td colspan="6" class="tal_lineL">
										<!-- Sungwoo Replaced scroll option 2014/08/04 -->
										<iframe src="<c:url value='/clm/blank.do' />" id="fileListUnit" name="fileListUnit"  frameborder="0" class='addfile_iframe_d' scrolling="auto" allowTransparency="true"></iframe>					
									</td>
								</tr>
								</c:if>
								<tr class="lineAdd">
									<th style="border-top:0px;"><spring:message code="las.page.field.contractmanager.consideration_d.cnsd_demnd_cont" /><!-- 검토요청내용 --></th>
									<td colspan="6">
										<textarea id="if_cnsd_demnd_cont_textarea" name="cnsd_demnd_cont_textarea" style="display: none;"><c:out value="${lomRq.cnsd_demnd_cont}" escapeXml="" /></textarea>
										<iframe id="if_cnsd_demnd_cont" name="if_cnsd_demnd_cont" src="<c:url value='/clm/blank.do' />" frameborder="0"  scrolling="auto" style="width:100%;" leftmargin="0" topmargin="0"></iframe>
									</td>
								</tr>
								<c:if test="${lomRq.plndbn_req_yn eq 'Y' && !empty lomRq.lastbn_chge_yn_nm}">
								<tr class="lineAdd">
									<th><spring:message code="las.page.field.contractmanager.consideration_d.lastbn_chge_yn_nm" /><!-- 검토본변경여부 --></th>
									<td colspan="6">
										<c:out value='${lomRq.lastbn_chge_yn_nm}' escapeXml='false'/>
									</td>
								</tr>
								</c:if>
								<c:if test="${lomRq.plndbn_req_yn eq 'Y' && !empty lomRq.plndbn_chge_cont_dp}">
								<tr class="lineAdd">
									<th><spring:message code="las.page.field.contractmanager.consideration_d.plndbn_chge_cont" /><!-- 변경내역 및 사유 --></th>
									<td colspan="6">
										<c:out value="${lomRq.plndbn_chge_cont_dp}" escapeXml="false" />
									</td>
								</tr>
								</c:if>
								<c:if test="${!empty lomRq.vc_cnsd_demnd_cont_dp}">
								<tr class="lineAdd">
									<th><spring:message code="las.page.field.contractManager.lnkDept"/><br><spring:message code="las.page.field.contractManager.reqReason"/><!-- 검토요청 사유 --></th>
									<td colspan="4">
										<c:out value='${lomRq.vc_cnsd_demnd_cont_dp}' escapeXml="false" />
									</td>
									<th><spring:message code="las.page.field.contractManager.lnkDept"/><br><spring:message code="las.page.field.contractManager.reqDate"/><!-- 검토요청 일시 --></th>
									<td>
										<c:out value='${lomRq.req_cnsd_demnd_dt}' />
									</td>
								</tr>
								</c:if>
								<tr>
									<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.pshdbkgrnd_purps" /></th><!-- 추진목적 및 배경 -->
									<td colspan="6">				
								    <textarea id="if_cnsd_bg_cont_textarea" name="cnsd_bg_cont_textarea" style="display: none;"><c:out value="${lomRq.pshdbkgrnd_purps}" escapeXml="" /></textarea>
						    		<iframe id="if_cnsd_bg_cont" name="cnsd_bg_cont" src="<c:url value='/clm/blank.do' />" frameborder="0" scrolling="auto" style="width:100%"></iframe>  
									</td>
								</tr>
								<c:if test="${!empty lomRq.antcptnefct_dp}">
								<tr>
									<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.antcptnefct" /></th><!-- 기대효과 -->
									<td colspan="6">
										<c:out value="${lomRq.antcptnefct_dp}" escapeXml="false"/>
										</td>
								</tr>
								</c:if>
								<c:if test="${!empty lomRq.payment_cond_dp}">
								<tr>
									<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.payment_cond" /></th><!-- 지불/수금 조건 -->
									<td colspan="6">
										<c:out value="${lomRq.payment_cond_dp}" escapeXml="false"/>
									</td>
								</tr>
								</c:if>
								<c:if test="${!empty lomRq.etc_main_cont_dp || !empty lomRq.if_sys_cd}">
								<tr>
									<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.etc_main_cont" /></th><!-- 기타주요사항 -->
									<td colspan="6">
										<c:out value="${lomRq.etc_main_cont_dp}" escapeXml='false'/>
										<c:if test="${!empty lomRq.if_sys_cd}"> [<c:out value="${lomRq.if_sys_cd}" />]</c:if>
									</td>
								</tr>
								</c:if>
								<c:if test="${lomRq.plndbn_req_yn eq 'Y'}">
								<tr>
									<th><spring:message code="clm.page.field.contract.consultation.detail.autorenew" /></th><!-- 자동연장여부 -->
									<td colspan="6"><input type="radio" name="auto_rnew_yn" disabled='disabled' value="Y" <%if("Y".equals(lomRq.get("auto_rnew_yn"))){out.println(" checked");} %> />Yes
										<input type="radio" name="auto_rnew_yn" disabled='disabled' value="N" <%if(!"Y".equals(lomRq.get("auto_rnew_yn"))){out.println(" checked");} %>/>No
									</td>
								</tr>
								<tr>
								<c:choose>
								<c:when test="${command.top_role eq 'RA02' && lomRq.resp_user_div eq 'Y' && modYn eq 'Y'}">
									<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.secret_keepperiod_rq" /></th><!-- * 비밀유지기간 -->
									<td id="td_secret_keepperiod"  colspan="6">
										<c:out value='${lomRq.secret_keepperiod}' escapeXml='false'/>
									</td>
								</c:when>
								<c:otherwise>
									<th><spring:message code="clm.page.msg.manage.secretPeriod" /></th><!-- 비밀유지기간 -->
									<td id="td_secret_keepperiod" colspan="6">
										<c:out value="${lomRq.secret_keepperiod_dp}" escapeXml="false"/>
									</td>
								</c:otherwise>
								</c:choose>
								</tr>
								</c:if>
							</table>
						</div>
						<div class="border-top-no">
							<table id="tab_contents_sub_conts6" cellspacing="0" cellpadding="0" border="0" class="table-style01" >
								<colgroup>
									<col width="15%" />
									<col width="27%"/>
									<col width="13%" />
									<col width="45%" />
								</colgroup>
							<% if("Y".equals(lomRq.get("plndbn_req_yn"))){ 	//최종본회신 %>
								<% if( ("RA01".equals(command.getTop_role()) ||"RB01".equals(command.getTop_role())) || "N".equals(modYn)){ // 그룹장인경우 VieWmode %>
								<tr id="tr_view_oblgt_lmt" style="border-top:0px;">
									<th style="border-top:0px;"><spring:message code="las.page.field.contractmanager.consideration_inner_d.oblgt_lmt" /></th><!-- 배상책임한도 -->
									<td colspan="3">
										<c:out value='${lomRq.oblgt_lmt_dp}' escapeXml='false'/>
									</td>
								</tr>
								<c:if test="${!empty lomRq.spcl_cond_dp}">
								<tr>
									<th style="border-top:0px;"><spring:message code="las.page.field.contractmanager.consideration_inner_d.spcl_cond" /></th><!-- 기타 특약사항 -->
									<td colspan="3" id="tr_spcl_cond">
										<c:out value='${lomRq.spcl_cond_dp}' escapeXml='false'/>
									</td>
								</tr>
								</c:if>
								<c:if test="${!empty lomRq.loac_nm}">
								<tr>
									<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.loac_rq" /></th><!-- * 준거법 -->
									<td id="td_loac">
										<c:out value='${lomRq.loac_nm}' escapeXml='false'/>
									</td>
									<c:choose>
										<c:when test="${lomRq.loac=='C02211'}"><!-- 기타(자유기술) -->
											<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.loac_etc_rq" /></th><!-- * 준거법 상세 -->
										</c:when>
										<c:otherwise>
											<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.loac_etc" /></th><!-- 준거법 -->
										</c:otherwise>
									</c:choose>
									<td id="td_loac_etc">
										<c:out value='${lomRq.loac_etc_dp}' escapeXml="false"/>
									</td>
								</tr>
								</c:if>
								<tr>
									<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.dspt_resolt_rq" /></th><!-- * 분쟁해결방법-->
									<td id="td_dspt_resolt_mthd_det" colspan="3">
										<c:out value='${lomRq.dspt_resolt_mthd_det_dp}' escapeXml="false"/>
									</td>
								</tr>
								<% }else{%>
								<tr style="border-top:0px;">
									<th style="border-top:0px;"><spring:message code="las.page.field.contractmanager.consideration_inner_d.oblgt_lmt" /></th><!-- 배상책임한도 -->
									<td colspan="3" id="tr_oblgt_lmt">
										<textarea name="oblgt_lmt" id="oblgt_lmt" cols="30" rows="3" class="text_area_full" onkeyup="f_chk_byte(this,1000)" maxLength="1000" alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.oblgt_lmt' />"><%=(String)StringUtil.nvl((String)lomRq.get("oblgt_lmt"), "") %></textarea>
									</td>
								</tr>
								<tr>
									<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.spcl_cond" /></th><!-- 기타 특약사항 -->
									<td colspan="3" id="tr_spcl_cond">
										<textarea name="spcl_cond" id="spcl_cond" cols="30" rows="3" class="text_area_full" onkeyup="f_chk_byte(this,1000)" maxLength="1000" alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.spcl_cond' />"><%=(String)StringUtil.nvl((String)lomRq.get("spcl_cond"), "") %></textarea>
									</td>
								</tr>
								<tr>
									<th><spring:message code="las.page.field.contractmanager.consideration_inner_d.loac_rq" /> <span class='astro'>*</span></th><!-- * 준거법 -->
									<td id="td_loac">
										<select name="loac" id="loac" style="width:110px;" required alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.loac_rq' />" onChange="javascript:chgLoac(this.value);">						  
										</select>
									</td>
									<c:choose>
										<c:when test="${lomRq.loac=='C02211'}">
											<th id="th_loac_etc"><spring:message code="las.page.field.contractmanager.consideration_inner_d.loac_etc_rq" /> <span class='astro'>*</span></th><!-- * 준거법상세 -->
							          		<td id="td_loac_etc"><%=(String)StringUtil.nvl((String)lomRq.get("loac_etc"), "") %></td>	
										</c:when>
										<c:otherwise>
											<th id="th_loac_etc"><spring:message code="las.page.field.contractmanager.consideration_inner_d.loac_etc" /></th><!-- 준거법상세 -->
											<td id="td_loac_etc"><%=(String)StringUtil.nvl((String)lomRq.get("loac_etc"), "") %></td>
										</c:otherwise>
									</c:choose>	
								</tr>							
								<tr>
									<th rowspan="2"  class='rightline'><spring:message code="las.page.field.contractmanager.consideration_inner_d.dspt_resolt_rq" /></th><!-- * 분쟁해결방법 -->
									<td id="td_dspt_resolt_mthd"  colspan="3">
										<select name="dspt_resolt_mthd" id="dspt_resolt_mthd" style="width:110px;" alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.dspt_resolt_rq' />" >
										</select> <span class="btn_all_b" onclick="javascript:addDspt();"><span class="add"></span> <a><spring:message code="las.page.field.contractManager.add"/></a></span> 
									</td>
									<!-- <th><spring:message code="las.page.field.contractmanager.consideration_inner_d.dspt_resolt_det" /></th>분쟁_해결_방법_상세 -->					
								 	
								</tr>
								<tr>
									<td id="td_dspt_resolt_mthd_det" colspan="3">
										<textarea name="dspt_resolt_mthd_det" id="dspt_resolt_mthd_det" cols="30" rows="3" class="text_area_full" onkeyup="f_chk_byte(this,1000)" maxLength='1000' alt="<spring:message code='las.page.field.contractmanager.consideration_inner_d.dspt_resolt_det' />"><%=(String)StringUtil.nvl((String)lomRq.get("dspt_resolt_mthd_det"), "") %></textarea>
									</td>
								</tr>
								<% }%>	
							<% }%>
							</table>
				    	</div>				
				    </c:if>
					<!-- //계약정보 end 신성우-->
					<table cellspacing="0" cellpadding="0" class="table-style01">
						<tbody>
							<tr>
								<td id='bodyDivIn' colspan="3">		
									<!-- 메일 start -->
									<colgroup>
										<col width="15%" />
										<col width="64%" />
										<col width="21%" />
									</colgroup>
								</td>
							</tr>
							<tr>
								<!-- 제목 -->
								<th><spring:message code="secfw.page.field.mail.subject" /></th>
								<td colspan="2"><input type='text' name='subject' alt="<spring:message code="secfw.page.field.mail.subject" />" value='<%= mailTitle%>' class='IpTextLe' style='width:74%' fieldTitle="<spring:message code="secfw.page.field.mail.subject" />" required="*" maxLength="500"></td>
								<td class='end'></td>
							</tr>
							<tr>
								<!-- 수신자 -->
								<th><spring:message code="secfw.page.field.mail.receiver" /></th>
								<td colspan="2">
									<input type='text' id="receiver_nm" name='receiver_nm' class='IpTextLe' style='width:74%;'>
									<span class="btn_all_b" id='srchReceiverBtn1'><span class="check"></span><a><spring:message code="clm.page.button.confirm" /></a></span>
								</td>
							</tr>
							<tr>
								<!-- MAIL 수신경로 -->
								<th class='btm'><spring:message code="secfw.page.field.mail.receivers" /></th>
								<td class='btm'>
									<select id="receivers" name="receivers" size=7 multiple="MULTIPLE" class='IpTextLe' style='width:100%; height:100px'>
									<!-- TODO : Test용 reciever
										<option value="t|sungwoo.shin@partner.samsung.com">Sungwoo Shin / Staff / Staff / WEB CRM / SDSE / sungwoo.shin@partner.samsung.com</option>
										<option value="t|homin.chun@samsung.com">homin chun/ Staff / Staff / WEB CRM / SDSE / sungwoo.shin@partner.samsung.com</option>
										<option value="t|kelly.yoo@samsung.com">kelly yoo/ Staff / Staff / WEB CRM / SDSE / sungwoo.shin@partner.samsung.com</option>
										 -->
									</select>					
								</td>
								<td class='btm end'>
									<table cellspacing="0" cellpadding="0" id='opa'>
										<colgroup>
											<col width="49%" />
											<col width="51%" />												
										</colgroup>
										<tr>
											<td><input id="rcvTypeT" type="radio" name="rdSendItem" value="t" textValue="<spring:message code="secfw.page.field.mail.rcvTypeT" />"> <spring:message code="secfw.page.field.mail.rcvTypeT" />[<span id="tCnt"></span>]</td>
	<%-- 												<td><div id="deleteBtn" class='delete<%=localeDisplayType %>'></div></td> --%>
											<td><span class="btn_all_b" id="deleteBtn"><span class="delete1"></span><a><spring:message code="clm.page.button.delete" /></a></span></td>
										</tr>
										<tr>
											<td><input id="rcvTypeC" type="radio" name="rdSendItem" value="c" textValue="<spring:message code="secfw.page.field.mail.rcvTypeC" />"> <spring:message code="secfw.page.field.mail.rcvTypeC" />[<span id="cCnt"></span>]</td>
	<%-- 												<td><div id="moveUp" name="moveUpBtn" class='up<%=localeDisplayType %>'></div></td> --%>
											<td><div id="moveUp" name="moveUpBtn" class="up1"><span class="btn_all_b" name="moveOrderBtn"><span class="up1"></span><a><spring:message code="las.page.field.approval.moveUp" /></a></span></div></td>
										</tr>
										<tr>
											<td><input id="rcvTypeB" type="radio" name="rdSendItem" value="b" textValue="<spring:message code="secfw.page.field.mail.rcvTypeB" />"> <spring:message code="secfw.page.field.mail.rcvTypeB" />[<span id="bCnt"></span>]</td>
	<%-- 												<td><div id="moveDown" name="moveDownBtn" class='down<%=localeDisplayType %>'></div></td> --%>
											<td><div id="moveDown" name="moveDownBtn" class="down1"><span class="btn_all_b" name="moveOrderBtn"><span class="down1"></span><a><spring:message code="las.page.field.approval.moveDown" /></a></span></div></td>
										</tr>
										<tr>
											<td></td>
											<td><!-- <div id="clearParalBtn" class='row_no'></div> --></td>
										</tr>
									</table>
								</td>
							</tr>
							<div style="visibility:hidden;">
								<input type='radio' id="body_type0" name='body_type' value='0'> <spring:message code="secfw.page.field.approval.planText" /> 
								<input type='radio' id="body_type1" name='body_type' value='1'> <spring:message code="secfw.page.field.approval.richText" /> 
								<select id='bodySize'>
									<option value=100><spring:message code="secfw.page.field.approval.content.size1" /></option>
									<option value=200><spring:message code="secfw.page.field.approval.content.size2" /></option>
									<option value=300><spring:message code="secfw.page.field.approval.content.size3" /></option>
								</select>
								<select name='encoding'/>
							</div>			
							<tr id="htmlEdit" >
								<td colspan="3" class='con2 tC'>
									<!-- Namo Active Square 7
									@ include file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"
									//Namo Active Square 7
									 -->
									<script type="text/javascript" language="javascript">							
									    var CrossEditor = new NamoSE('wec');
										CrossEditor.params.Width = "100%";
										CrossEditor.params.UserLang = "enu";
									    CrossEditor.params.FullScreen = false;
									    CrossEditor.ShowTab(false);
									    CrossEditor.UserToolbar = true;
									    CrossEditor.params.CreateToolbar = "newdoc|saveas|print|spacebar|undo|redo|cut|copy|paste|pastetext|search|replace|selectall|spacebar|word_style|space|word_color|space|cancelattribute|spacebar|word_justify|space|word_indentset|spacebar|word_listset|spacebar|tableinsert|spacebar|tablerowinsert|tablerowdelete|spacebar|tablecolumninsert|tablecolumndelete|spacebar|tablecellmerge|tablecellsplit|spacebar|tablecellattribute|formatblock|space|fontname|space|fontsize|space|lineheight|spacebar|fullscreen";
									    CrossEditor.EditorStart();										        					
									</script> 
								</td>
							</tr>
							<tr id="textEdit" >
								<!-- MAIL 본문 -->
								<th class='btm'><spring:message code="secfw.page.field.mail.mailCntnt" /></th>
								<td class='btm'>
									<!-- 본문입력 start -->
									<textarea id='body' name='body' class='IpTextLe' style='width:100%;height:300px;'><%=mailContent %></textarea>
									<!-- 본문입력 end -->
								</td>
							</tr>
							<!-- 첨부파일 start -->
							<tr>
			            		<td colspan="3">
			            			<iframe src="<c:url value='/clm/blank.do' />" name="fileList" id="fileList" frameborder="0" width="100%" height="98px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
			            		</td>
			          		</tr> 
						</tbody>
					</table>
					<table width='100%' height='100%' cellpadding='0' cellspacing='0'>
						<tr>
							<td id='bodyDivIn'>						
										
							 <!-- button -->
				            <div class="t_titBtn">
				            	<div class="btn_wrap_t">
									<span class="btn_all_w"><span class="cancel"></span><a href="#" id="cancel"><spring:message code="secfw.page.field.approval.cancel" /></a></span>
								</div>
				                <div class="btn_wrap_t">
				                    <span class="btn_all_w"><span class="mail"></span><a href="#" id="mail"><spring:message code="secfw.page.button.send" /></a></span>
				                </div>
				            </div>
				            <!-- //button -->
		           			</td>
		           		</tr>
		           	</table>
				</form:form>
			</div>
			<!-- //content_in -->
		</div>
		<!-- //content -->
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	</div>
		<!-- footer  -->
		<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
		<!-- // footer -->
	</body>
</html>
