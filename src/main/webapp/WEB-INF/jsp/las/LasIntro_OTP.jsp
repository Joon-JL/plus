﻿
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="org.springframework.web.servlet.support.RequestContext"%>
<%@page import="com.sds.secframework.common.util.StringUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%--
/**
 * 파    일    명 : LasIntro.jsp
 * 프 로 그 램 명 : 법무시스템 인트로
 * 작    성    자 : 전종효
 * 작    성    일 : 2013.05
 * 설          명 : 
 */
--%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<%
    boolean spotViewFlag  = spotViewFlag(session);
	
	spotViewFlag = true; //임시로 모두 볼수 있도록 권한을 해제함. 2013. 11. 4
%>
<html>
<head>
<meta charset="utf-8" />

<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/layout.css" type="text/css" rel="stylesheet">
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript"
	src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="/script/clms/common.js"
	type="text/javascript"></script>
<script language="javascript">
$(document).ready(function(){
    $('#spot').bind('click', function(){
        $('#sessionConfig').attr("style","display:block");
    });
});

/**
 * 메인 페이지로 이동
 */
 function doAction() {

	/* FERNANDO (MFA project) Jan/2024 - start */
	var otp = document.getElementById("otp").value;
	if (!otp || $.trim(""+otp) === "") {
		alert('TEST 002');
		return;
	}
	if (otp !== "<%=session.getAttribute("otp_access")%>") {
		alert("Wrong OTP. Please, check your inbox.");
		return;
	}
	/* FERNANDO (MFA project) Jan/2024 - end */
	
    var frm = document.frm;

    var goFlag = "main";

    //세션 재 설정
    var $roleChk = "";
    $roleChk = $('#sessionConfig :checked').val();

    if (typeof $roleChk != "undefined") {
        goFlag = "backdoor";
<%
    if (spotViewFlag) { //보안관련하여  spotViewFlag 가 true인 사람만 표시한다(소스보기에서 안보이기 용도)
%>
        if($roleChk=="1"){
            $('input[name=user_id]').val('M130227094726C104215');
        }else if($roleChk=="2"){
            $('input[name=user_id]').val('D130611143537C105052');
        }else if($roleChk=="3"){
            $('input[name=user_id]').val('D100120091526C101019');

        }else if($roleChk=="20"){
            $('input[name=user_id]').val('D130821144033C103880');
        }else if($roleChk=="21"){
            $('input[name=user_id]').val('M060126174649C108791');
        }else if($roleChk=="22"){
            $('input[name=user_id]').val('D130613144720C105659');
        
        }else if($roleChk=="30"){
        	$('input[name=user_id]').val('M030826123611C109225');
        }else if($roleChk=="31"){
            $('input[name=user_id]').val('S020305135846XSD7217');
        }else if($roleChk=="32"){
            $('input[name=user_id]').val('M050607102534C103142');

        }else if($roleChk=="40"){
            $('input[name=user_id]').val('M110211091704C104416');
        }else if($roleChk=="41"){
            $('input[name=user_id]').val('M080103132220C105553');
        }else if($roleChk=="42"){
            $('input[name=user_id]').val('D080307161356C104159');
        
        }else if($roleChk=="70"){
            $('input[name=user_id]').val('M040518004048PC91625');
        }else if($roleChk=="71") {
            $('input[name=user_id]').val('S030118102846PC90037');    
        }else if($roleChk=="72"){
            $('input[name=user_id]').val('TEST_PC9_AE02');
        }else if($roleChk=="73"){
            $('input[name=user_id]').val('TEST_PC9_AE01');
        }else if($roleChk=="74"){
            $('input[name=user_id]').val('M121008055322PC98808');
        }else if($roleChk=="75"){
            $('input[name=user_id]').val('S020318192730C120195');
        }else if($roleChk=="76"){
            $('input[name=user_id]').val('TEST_PC9_AM00');
        }else if($roleChk=="77"){
            $('input[name=user_id]').val('R020305092704M207148');
        }else if($roleChk=="78") {
            $('input[name=user_id]').val('M060601041117M208402');
        }else if($roleChk=="79"){
            $('input[name=user_id]').val('TEST_M20_AE02');
        }else if($roleChk=="80"){
            $('input[name=user_id]').val('TEST_M20_AE01');
        }else if($roleChk=="81"){
            $('input[name=user_id]').val('M040311020028M206100');
        }else if($roleChk=="82"){
            $('input[name=user_id]').val('S020305135841E803223');
        }else if($roleChk=="83"){
            $('input[name=user_id]').val('S020305135841E803223');
        }else if($roleChk=="84"){
            $('input[name=user_id]').val('TEST_M20_AM00');
        }else if($roleChk=="85"){
            $('input[name=user_id]').val('D091230221722C100767');
        }else if($roleChk=="86"){
            $('input[name=user_id]').val('R020218102340C104121');
        }else if($roleChk=="87") {
            $('input[name=user_id]').val('D100205194926C101822');
         // hq 100번
        }else if($roleChk=="100"){
	    	$('input[name=user_id]').val('D060614093220C310830');  // 최봉석
	    }else if($roleChk=="101"){ 
	    	$('input[name=user_id]').val('S030107223846C100639');  // 한장수
	    }else if($roleChk=="102"){
	    	$('input[name=user_id]').val('D060614093220C310830');  // 최봉석
	    }else if($roleChk=="103"){
	    	$('input[name=user_id]').val('D110209084130C100685');  // 양승훈
	    }else if($roleChk=="104"){
	    	$('input[name=user_id]').val('D100701113607C101639');  // 홍상범
	    }else if($roleChk=="105"){
	    	$('input[name=user_id]').val('M111010063719C106938');  // 강은하
	    }else if($roleChk=="106"){
	    	$('input[name=user_id]').val('D130103115601C101386');  // 이제준
	    }else if($roleChk=="88") {
	            $('input[name=user_id]').val('S030107223846C100639');
            
        }else{
            $('input[name=user_id]').val();
        }
<%
    }
%>
    }

    if(goFlag == "main"){
        frm.action = "<c:url value='/secfw/main.do' />";
        frm.method.value = "forwardMainPage";
        frm.flag.value = "L"; //menuInterceptor 에서 sys_cd를 셋팅하기 위해
    }else{
        frm.action = "<c:url value='/secfw/ssoCheck.do' />";
        frm.method.value = "clmsSelLoginPrcs";
        frm.f.value = "l";
    }
    frm.submit();
}

   // 즐겨 찾기 추가 
   function setFav(){
	   
	   var title = "SELMS+"; //고정타이틀을 원하시면 이곳에 입력해주세요
	   
	   var url = ""; //고정URL을 입력하고 싶으시면 이곳에 입력해주세요
	   
	   if(!title) title = document.title; //현재 보고 있는 페이지의 Title
	   
	   if(!url) url = location.href; //현재 보고 있는 페이지의 Url
	   
	   if(window.sidebar && window.sidebar.addPanel){ // Firefox
		   
	       window.sidebar.addPanel(title, url,"");
	   
	   } else if(document.all){ // Internet Explorer
	       
		   window.external.AddFavorite( url, title);
	   
	   } else {
	       
		   alert("Use your Web browser does not support the feature. \n \n Ctrl + D keys to add to favorites you can press.");
		   
	       return true;
	   }
	   
   }

//페이지 자동 forward
function goMain() {
<%
    if (!spotViewFlag) { //spot이 보이는 사람들은 자동으로 메인으로 넘어가는 기능을 안쓴다.
%>
     setTimeout(function(){doAction();}, 1000);
<%
    }
%>
}
</script>
</head>
<body onLoad="javascript:goMain();" class='bady_slas'>
	<!-- **************************** Form Setting **************************** -->
	<form:form id="frm" name="frm" autocomplete="off">
		<input type="hidden" id="method" name="method" />
		<input type="hidden" id="flag" name="flag" />
		<!-- login box -->
		<div id="login_box_slas">
			<%
    if (langCd.equals("ko")) {
        request.setAttribute("main_user_nm",(String)session.getAttribute("secfw.session.user_nm"));
    } else {
        request.setAttribute("main_user_nm",(String)session.getAttribute("secfw.session.user_nm_en"));
    }
%>
			<!-- login -->
			<div class="login_slas">
				<span class="logo"><img
					src="<%=IMAGE%>/common/login_logo.png" alt="logo" /></span>
				<div class='user'>
					<span><%=StringUtil.bvl((String)session.getAttribute("secfw.session.dept_nm_en"), "")%>
						&nbsp;</span> <span><c:out value="${main_user_nm}" /></span>
				</div>
				<div class='info_data'>
					<dl>
						<dt>Current Connection IP</dt>
						<dd><%=StringUtil.bvl((String)session.getAttribute("secfw.session.loginIP"), "")%></dd>
					</dl>
					<dl>
						<dt>Last Connection IP</dt>
						<dd><%=StringUtil.bvl((String)request.getAttribute("lastConnectIP"), "")%></dd>
					</dl>
					<dl>
						<dt>Last Login Time</dt>
						<dd><%=StringUtil.bvl((String)request.getAttribute("lastConnectTime"), "")%></dd>
					</dl>
					<!-- FERNANDO (MFA project) Jan/2024 - start -->
					<dl>
						<dt>
							<span style='font-weight: 700; color: red; font-size: 110%;'>OTP</span>
						</dt>
						<dd>
							<input id="otp" type="text" />
						</dd>
					</dl>
					<!-- FERNANDO (MFA project) Jan/2024 - end -->
				</div>
				<p class="detail">
					This System is strictly restricted to authorized users only.<br />Any
					illegal access shall be punished with a related-law.
				</p>

				<!-- <a href="<%=StringUtil.mainBvl((String)request.getAttribute("strAttachUrl"), "")%>direct_las" class="btn_main_go"><spring:message code="las.page.field.LasIntro.jsp.shortcut"/> <img src="<%=IMAGE%>/icon/ico_arrow_yellow.png" /></a> -->
				<a href="javascript:doAction();" class="btn_main">Confirm <img
					src="<%=IMAGE%>/icon/ico_arrow_yellow.png" /></a>
			</div>
			<!-- //login -->
			<%
			if (spotViewFlag) {
			%>

			<%
			/*기존 사용자 등록시 임시적으로 사용할 것임. */
			String myid = (String) session.getAttribute("secfw.session.user_id");
			if (myid != null && (myid.equals("M030402040320C605656") || myid.equals("D110725091850C600095"))) {
				String sTargetCount = (String) request.getAttribute("sTargetCount");
				sTargetCount = sTargetCount == null ? "" : sTargetCount;
				String sMigCount = (String) request.getAttribute("sMigCount");
				sMigCount = sMigCount == null ? "" : sMigCount;
			%>
			<a href='#' onclick="migUser();">.</a> <input type=hidden
				name='ecms_tran_yn' value='Y'>
			<script>
			var v_TargetCount = "<%=sTargetCount%>";
			var v_MigCount = "<%=sMigCount%>
				";
				if (v_TargetCount != 0) {
					alert('Mig/Target:' + v_MigCount + '/' + v_TargetCount);
				}

				function migUser() {
					//alert('이관시만 사용하므로 주석처리함');
					if (confirm("ECMS 사용자정보를 수정하시겠습니까?")) {
						if (confirm("ECMS 사용자정보를 정말 수정하시겠습니까?")) {
							frm.action = "/secfw/ssoCheck.do";
							frm.method.value = "migUserInfoByESB";
							frm.f.value = "l";

							frm.submit();
						}
					}
				}
			</script>
			<%
			}
			%>

			<!-- spotView login -->
			<input type="hidden" name="user_id" value="" /> <input type="hidden"
				name="f" value="" />

			<c:set var='server_division'>
				<spring:message code="secfw.server.division" />
			</c:set>
			<c:choose>
				<c:when test="${server_division=='DEV'}">
					<span id="spot">☞☜</span>
					<div id="sessionConfig" style="display: none">
						<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[SEUK]
							============================================================</div>
						<div>
							<input type="radio" name="userid" id="userid" value="1" />&nbsp;Katie
							hughes(Requestor)
						</div>
						<div>
							<input type="radio" name="userid" id="userid" value="2" />&nbsp;Natalie
							Focosi(Admin)
						</div>
						<div>
							<input type="radio" name="userid" id="userid" value="3" />&nbsp;Phillip
							Carnell(Lawyer)
						</div>
						<div>
							<br />
						</div>

						<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[EHQ]
							============================================================</div>
						<div>
							<input type="radio" name="userid" id="userid" value="20" />&nbsp;Siobhan
							Langmead(Requestor)
						</div>
						<div>
							<input type="radio" name="userid" id="userid" value="21" />&nbsp;Sharon
							Elizabeth Robinson(Admin)
						</div>
						<div>
							<input type="radio" name="userid" id="userid" value="22" />&nbsp;Edward
							Phillips(Lawyer)
						</div>
						<div>
							<br />
						</div>

						<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[SEF]
							=============================================================</div>
						<div>
							<input type="radio" name="userid" id="userid" value="30" />&nbsp;Claire
							Thiriez(Requestor)
						</div>
						<div>
							<input type="radio" name="userid" id="userid" value="31" />&nbsp;Bernard
							Vaudin(Admin)
						</div>
						<div>
							<input type="radio" name="userid" id="userid" value="32" />&nbsp;William
							Baffard(Lawyer)
						</div>
						<div>
							<br />
						</div>

						<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[SEG]
							=============================================================</div>
						<div>
							<input type="radio" name="userid" id="userid" value="40" />&nbsp;Chantal
							Privat(Requestor)
						</div>
						<div>
							<input type="radio" name="userid" id="userid" value="41" />&nbsp;Diane
							Kunz(Admin)
						</div>
						<div>
							<input type="radio" name="userid" id="userid" value="42" />&nbsp;STEFAN
							LAUN(Lawyer)
						</div>
						<div>
							<br />
						</div>

						<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[HQ]
							==============================================================</div>
						<div>
							<input type="radio" name="userid" id="userid" value="100" />&nbsp;CE:최봉석
							(HQ CE Leagal Head)
						</div>
						<div>
							<input type="radio" name="userid" id="userid" value="104" />&nbsp;IM:홍상범
							(HQ IM Leagal Head)
						</div>
						<div>
							<input type="radio" name="userid" id="userid" value="102" />&nbsp;CE:최봉석
							(HQ CE Reviewer)
						</div>
						<div>
							<input type="radio" name="userid" id="userid" value="103" />&nbsp;CE:양승훈
							(HQ CE Reviewer)
						</div>
						<div>
							<input type="radio" name="userid" id="userid" value="101" />&nbsp;IM:한장수
							(HQ IM Reviewer)
						</div>
						<div>
							<input type="radio" name="userid" id="userid" value="105" />&nbsp;IM:강은하
							(HQ IM Reviewer)
						</div>
						<div>
							<input type="radio" name="userid" id="userid" value="106" />&nbsp;IM:이재준
							(HQ IM Reviewer)
						</div>
						<div>
							<br />
						</div>
				</c:when>

				<c:otherwise>
					<div id="sessionConfig" style="display: none"></div>
				</c:otherwise>
			</c:choose>



			<!-- //spotView login -->
			<%
			}
			%>
		</div>
		<!-- //login box -->
	</form:form>
	<!-- //**************************** Form Setting **************************** -->
</body>
</html>
<%!// 백도어 view 여부
	public boolean spotViewFlag(HttpSession session) throws Exception {
		boolean retV = false;
		String result = "";
		String userId = (String) session.getAttribute("secfw.session.user_id"); //UserId
		ArrayList roleList = (ArrayList) session.getAttribute("secfw.session.role");
		ArrayList userRoleList = new ArrayList();

		if (roleList != null && roleList.size() > 0) {
			for (int i = 0; i < roleList.size(); i++) {
				HashMap hm = (HashMap) roleList.get(i);
				String roleCd = (String) hm.get("role_cd");
				userRoleList.add(roleCd);
			}
		}

		if (userRoleList != null && userRoleList.size() > 0) {
			if (userRoleList.contains("RA00")) {
				result = "A";
			}
		}

		// 시스템 관리자이거나 아래 사람들만 Spot이 보임
		if ("A".equals(result) || (
		// 추가 TESTER
		"S020305135813C100100".equals(userId) || //권영석
				"S020305135812C107929".equals(userId) || //이광원
				"M120822081848PC37170".equals(userId) || //이종욱
				"S020305135812C109442".equals(userId) || //김유태
				"M121203002919PC28106".equals(userId) || //민경희
				"M110715015244PC28750".equals(userId) || //임승호
				"M050929004152PCA9578".equals(userId) || //김준형
				"S020305135850C106829".equals(userId) || //김도형
				"M071220072821PCA6616".equals(userId) || //손희정
				"M030326014900PCB5232".equals(userId) || //정상구
				"S020305135815C104841".equals(userId) || //이근무
				"S020305135815C105008".equals(userId) || //이춘석
				"P040105084241PC60003".equals(userId) || //김병현
				"R020218102335C107832".equals(userId) || //김상길
				"R020218102335C107925".equals(userId) || //이석한
				"D110405100257C100550".equals(userId) || //추정이
				"M110302024254PC95224".equals(userId) || //김현규
				//"M121008055322PC98808".equals(userId) || //김동현
				"S020228000435C121963".equals(userId) || //오수진
				//"S020318192730C120195".equals(userId) || //김영학
				"S020318202830C120712".equals(userId) || //이경호
				"M090305070412PCD9912".equals(userId) || //박인춘
				"D110330154025PCO0469".equals(userId) || //이종호
				"D110330162721PCO0611".equals(userId) || //이재무
				"M060714002113C600764".equals(userId) || //이동석
				"M060306044642D119825".equals(userId) || //송인섭
				"S020305135824C609820".equals(userId) || //이승호
				"D060302074041C601059".equals(userId) || //서준영
				"M101102020118C109001".equals(userId) || //조현순
				"D091005090702C100026".equals(userId) || //김슬기
				"M040706233525PCC7842".equals(userId) || //손준호
				"M071102021634PCC6092".equals(userId) || //이인주
				"M030325105834PCC6273".equals(userId) || //안태현
				//"S020305135841E803223".equals(userId) || //백성욱
				//"M040311020028M206100".equals(userId) || //현수정
				"M120620162035M201731".equals(userId) || //이후락
				"M121231063345M205949".equals(userId) || //이재원
				"S020228021406G004639".equals(userId) || //김건훈
				"S020305135845M301097".equals(userId) || //남승태
				"D070122171630C400539".equals(userId) || //김민구
				"S020910221945C100338".equals(userId) || //김병주
				"S030116013726C100068".equals(userId) || //구준모(구주총괄 TESTER)
				"R020218102320C601101".equals(userId) || //서정우(구주총괄 TESTER)
				"M130702075043C6E7034".equals(userId) || //유재령(구주총괄 TESTER)
				"S020305135854C604960".equals(userId) || //이지숙( TESTER)
				// 표시 USER
				"D091230221722C100767".equals(userId) || //박태준
				"R020218102340C104121".equals(userId) || //최윤식
				"R020305092704M207148".equals(userId) || //정영식
				"M060601041117M208402".equals(userId) || //이성호
				"S020305135841E803223".equals(userId) || //백성욱
				"M040311020028M206100".equals(userId) || //현수정
				"S020318192730C120195".equals(userId) || //김영학
				"M121008055322PC98808".equals(userId) || //김동현
				"M040518004048PC91625".equals(userId) || //강세훈
				"S030118102846PC90037".equals(userId) || //조성훈
				"D100205194926C101822".equals(userId) || //윤혜영

				"M140912090254C6E2948".equals(userId) || // 박K M140912090254C6E2948

				"S030107223846C100639".equals(userId) //한장수
		)) {
			retV = true;
		}

		return retV;
	}%>