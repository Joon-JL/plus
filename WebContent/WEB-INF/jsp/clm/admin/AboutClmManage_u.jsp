<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%--
/**
 * 파  일  명 : AboutClmManage_u.jsp
 * 프로그램명 : About CLM 관리 수정
 * 설      명 : 
 * 작  성  자 : 
 * 작  성  일 : 
 */
 --%>
<%
    //메뉴네비게이션 스트링
			String menuNavi = (String) request.getAttribute("secfw.menuNavi");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<%@ include file="/WEB-INF/jsp/common/messageUtil.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title" />
</title>

<!-- style -->
<link href="<%=CSS%>/clm.css" type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script type="text/javascript" src="/crosseditor/js/namo_scripteditor.js"></script>

<script language="javascript">
	$(document).ready(function() {
		frmChkLenMe(frm.cont, 2000, 'cont');
		frmChkLenMe(frm.srch_cont, 2000, 'curByteExpl');
		//Lang 셋팅
// 		getCodeSelectByUpCd("frm","sel_lang",'/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=MULTI_LANG&combo_type=NULL&combo_del_yn=N&combo_selected=');
	});

	/**
	 * 버튼 동작 부분
	 */
	function pageAction(flag) {
		var frm = document.frm;

		frm.target = "_self";
		frm.action = "<c:url value='/clm/admin/aboutClmManage.do'/>";
		//저장
		if (flag == "insert") {
// 			alert(frm.cont.value);
// 			if(frm.cont.value==""){
// 				alert("<spring:message code='clm.page.field.aboutClmManage.cont'/>"+"<spring:message code='las.msg.alert.isRequired'/>");
// 				return;
// 			}
			
			if (confirm("<spring:message code='secfw.msg.ask.update' />")) {
				frm.method.value = "modifyAboutClmManage";
				//나모웹에디터 관련
				
// 				frm.cont.value = CrossEditor.GetBodyValue();
				
				
				viewHiddenProgress(true);
				frm.submit();
			}

			//초기화
		} else if (flag == "cancel") {
			if (confirm("<spring:message code='secfw.msg.ask.cancel' />")) {
				frm.seqno.value = "1";
				frm.method.value = "listAboutClmManage";
				viewHiddenProgress(true);
				frm.submit();
			}
		}
	}

	function chglangContent(v_sel_flag) {
		var wecObj = document.wec;
		wecObj.BodyValue = ' ';
		if (v_sel_flag == 'ko') {
			wecObj.BodyValue = document.frm.cont.value;
			document.frm.title.value = document.frm.h_title.value;
		} else if (v_sel_flag == 'fr') {
			wecObj.BodyValue = document.frm.cont_fr.value;
			document.frm.title.value = document.frm.h_title_fr.value;
		} else if (v_sel_flag == 'de') {
			wecObj.BodyValue = document.frm.cont_de.value;
			document.frm.title.value = document.frm.h_title_de.value;
		} else {
			wecObj.BodyValue = document.frm.cont_en.value;
			document.frm.title.value = document.frm.h_title_en.value;
		}
	}

</script>

</head>
<body>

	<div id="wrap">
		<!-- container -->
		<div id="container">
			<!-- Location -->
			<div class="location">
				<img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" />
				<c:out value='${menuNavi}' escapeXml="false" />
			</div>
			<!-- //Location -->
			<!-- title -->
			<div class="title">
				<h3>
					<spring:message code="clm.page.title.aboutClmManage.modifyTitle" />
				</h3>
			</div>
			<!-- //title -->
			<!-- content -->
			<div id="content">
				<!-- content in -->
				<div id="content_in">
					
					<form:form name="frm" id="frm" method="post" autocomplete="off">
					
					  <!-- **************************** Form Setting **************************** -->
						<!-- search form -->
						<input type="hidden" name="method" value="" />
						<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>" />
						<!-- key form-->
						<input type="hidden" name="seqno" value="<c:out value='${command.seqno}'/>" />
<%-- 						<input type="hidden" name="cont" value="<c:out value='${lom.cont}'/>" /> --%>
						<input type="hidden" name="cont_en" value="<c:out value='${lom.cont_en}'/>" />
						<input type="hidden" name="cont_fr" value="<c:out value='${lom.cont_fr}'/>" />
						<input type="hidden" name="cont_de" value="<c:out value='${lom.cont_de}'/>" />
						<input type="hidden" name="h_title" value="<c:out value='${lom.title}'/>" />
						<input type="hidden" name="h_title_en" value="<c:out value='${lom.title_en}'/>" />
						<input type="hidden" name="h_title_fr" value="<c:out value='${lom.title_fr}'/>" />
						<input type="hidden" name="h_title_de" value="<c:out value='${lom.title_de}'/>" />
						<!-- // **************************** Form Setting **************************** -->
						
						<div class="btnwrap ">
							<span class="btn_all_w"><span class="save"></span><a href="javascript:pageAction('insert');"><spring:message code="secfw.page.button.insert" />
							</a>
							</span> <span class="btn_all_w"><span class="cancel"></span><a href="javascript:pageAction('cancel');"><spring:message
										code="secfw.page.button.cancel" />
							</a>
							</span>
						</div>
						
						<!-- view -->
						<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
							<colgroup>
								<col width="10%" />
								<col width="*"/>
							</colgroup>
							<tbody>
								<tr>
									<th><spring:message code="clm.page.field.aboutClmManage.title" />
									</th>
									<td>
									<%
									    if ("fr".equals(langCd)) {
									%>
									<input type="text" id="title_fr" name="title_fr" value="<c:out value='${lom.title_fr}'/>" maxlength="50" class="text_full"/>
									<%
									    } else if ("de".equals(langCd)) {
									%>
									<input type="text" id="title_de" name="title_de" value="<c:out value='${lom.title_de}'/>" maxlength="50" class="text_full"/>
									<%
									    } else {
									%>
									<input type="text" id="title_en" name="title_en" value="<c:out value='${lom.title_en}'/>" maxlength="50" class="text_full"/>
									<%
									    }
									%>
									
									</td>
								</tr>
								<tr>
									<th><spring:message code="clm.page.field.aboutClmManage.menuPos" />
									</th>
									<td>
									<%
									    if ("fr".equals(langCd)) {
									%>
									<input type="text" id="menu_pos_fr" name="menu_pos_fr" value="<c:out value='${lom.menu_pos_fr}'/>" maxlength="50" class="text_full"/>
									<%
									    } else if ("de".equals(langCd)) {
									%>
									<input type="text" id="menu_pos_de" name="menu_pos_de" value="<c:out value='${lom.menu_pos_de}'/>" maxlength="50" class="text_full"/>
									<%
									    } else {
									%>
									<input type="text" id="menu_pos_en" name="menu_pos_en" value="<c:out value='${lom.menu_pos_en}'/>" maxlength="50" class="text_full"/>
									<%
									    }
									%>
									</td>
								</tr>
<!-- 								<tr> -->
<%-- 									<th><spring:message code="clm.page.field.aboutClmManage.language" /> --%>
<!-- 									</th> -->
<!-- 									언어 -->
<!-- 									<td><select class="srch" name="sel_lang" id="sel_lang" onchange="chglangContent(this.value);"></select></td> -->
<!-- 								</tr> -->
								<tr>
									<th><spring:message code="clm.page.field.aboutClmManage.cont" /></th>
									<td>
										<span id="cont">0</span>/<spring:message code="clm.page.msg.manage.stringLen2000" /><br>
						  				<textarea id="cont" name="cont" cols="100%" rows="10" maxLength="2000" onkeyup="frmChkLenMe(this,2000,'cont')" class="text_area_full"><c:out value='${lom.cont}' escapeXml="" /></textarea>
									</td>
								</tr>
								<tr>
									<th><spring:message code="clm.page.field.aboutClmManage.srchCont" />
									</th>
									<td><span id="curByteExpl">0</span>/<spring:message code="clm.page.msg.common.max1000byte" /> <br> 
									<textarea name="srch_cont" id="srch_cont" cols="100%" rows="10" maxLength="2000" class="text_area_full" onkeyup="frmChkLenMe(this,2000,'curByteExpl')"><c:out value="${lom.srch_cont}" /></textarea></td>
								</tr>
							</tbody>
						</table>
						<!-- //view -->

						<div class="btnwrap mt20">
							<span class="btn_all_w"><span class="save"></span><a href="javascript:pageAction('insert');"><spring:message code="secfw.page.button.insert" />
							</a>
							</span> <span class="btn_all_w"><span class="cancel"></span><a href="javascript:pageAction('cancel');"><spring:message
										code="secfw.page.button.cancel" />
							</a>
							</span>
						</div>
					</form:form>
				</div>
				<!-- //content in -->
			</div>
			<!-- //content -->

			<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp" />
		</div>
		<!-- //container -->
	</div>
	<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->
</body>
</html>
