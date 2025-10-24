<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
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
 * 파  일  명 : EventManage_i.jsp
 * 프로그램명 : 사건관리 등록화면
 * 설      명 :  사건을 등록
 * 작  성  자 :  박병주
 * 작  성  일 : 2011.09
 */
--%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript">
<!--
	/**
	* 초기화면 로딩
	*/
	$(document).ready(function(){
		
		var frm = document.frm;
		var insert_mode = "<c:out value='${lom.event_no}'/>";
		
		//첨부파일창 load
		initPage();
		
		// event_gbn1
		getCodeSelectByUpCd2("frm", "event_gbn1", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"106"+"&combo_abbr=F&combo_type=S2&combo_use_yn=Y&combo_selected="+"<c:out value='${lom.event_gbn1}'/>");
		
		// 수정 화면 일 경우
		if(insert_mode!=''){
			// 사건구분 로드
			$(partin2).html("<select name='event_gbn2' id='event_gbn2' required alt="+"<spring:message code='las.page.field.lawservice.gbn2' />"+" ></select>");
			getCodeSelectByUpCd2("frm", "event_gbn2", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"<c:out value='${lom.event_gbn1}'/>"+"&combo_abbr=F&combo_type=S2&combo_use_yn=Y&combo_selected="+"<c:out value='${lom.event_gbn2}'/>");
			// 부서정보 로드
			setDeptInfo();
		}			
		
		// 소송상대 리스트
		getCodeSelectByUpCd2("frm", "lawsuit_trgt", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"105"+"&combo_abbr=F&combo_type=S2&combo_use_yn=Y&combo_selected="+"<c:out value='${lom.lawsuit_trgt}'/>");
	
		var $inputTxt=$('#title');
		if($inputTxt.val()==''){
			$inputTxt.focus();
		}
	});
	
	/**
	* 첨부파일창 load
	*/
	function initPage(){
	    frm.target          = "fileList";
	    frm.action          = "<c:url value='/clms/common/clmsfile.do' />";
	    frm.method.value    = "forwardClmsFilePage";
	    frm.submit();   
	}
		
	/**
	*  사건구분2  reload
	*/
	function StReload_partin(index){
    	$(partin2).html("<select name='event_gbn2' id='event_gbn2' required alt="+"<spring:message code='las.page.field.lawservice.gbn2' />"+" ></select>");
		getCodeSelectByUpCd2("frm", "event_gbn2", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+ index +"&combo_abbr=F&combo_type=S2&combo_use_yn=Y&combo_selected="+"");
	} 
	
	/**
	*  부서선택정보 reload
	*/
	function setDeptInfo(){
		var frm = document.frm;
		var selected_cd = '';
		var selected_nm = '';
		var selected_id = '';
		var selected_nm_alone = '';
		var t = 0;
		var str_msg = '';
		
		<c:forEach var='list' items='${dept_list}' varStatus='status'>
			t = t + 1;			
			<c:choose>
				<c:when test="${status.count ==  1}">
					selected_nm_alone = "<c:out value='${list.dept_nm}'/>";
					selected_nm = "<c:out value='${list.dept_nm}'/>";
					selected_cd = "<c:out value='${list.intnl_dept_cd}'/>";
					selected_id = "<c:out value='${list.grp_dept_cd}'/>";
				</c:when>
				<c:otherwise>
					selected_nm = selected_nm + '/' + "<c:out value='${list.dept_nm}'/>";
					selected_cd = selected_cd + '/' + "<c:out value='${list.intnl_dept_cd}'/>";
					selected_id = selected_id + '/' + "<c:out value='${list.grp_dept_cd}'/>";
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
		if(t > 0)
			str_msg = selected_nm_alone + "<spring:message code='las.page.msg.lawservice.deptnum1' />" + (t-1) + "<spring:message code='las.page.msg.lawservice.deptnum2' />";
		
		frm.dept_select.value = "0";
		frm.intnl_dept.value = str_msg;
		frm.dept_nm.value = selected_nm;;
		frm.intnl_dept_cd.value = selected_cd;
		frm.grp_dept_cd.value = selected_id;
	
	}
	
	/**
	* 비용 귀속 부서 팝업 
	*/
	function listDeptTree()
	{		
		var frm = document.frm;
			
		PopUpWindowOpen('', "640", "530", false);
		frm.action = "<c:url value='/las/lawservice/eventManage.do' />";
		frm.method.value="listDeptTree";
		frm.target = "PopUpWindow";
		frm.forward_url.value="/WEB-INF/jsp/las/lawservice/EventManage_p.jsp";
		frm.submit();
	}

	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;

		//저장
		if(flag == "insert"){
		    
		    //나모웹에디터 관련
		    frm.body_mime.value = frm.wec.MIMEValue;
		    
		    var confirmMessage = frm.event_no.value==("") ? "<spring:message code='secfw.msg.ask.insert' />" : "<spring:message code='secfw.msg.ask.update' />" ;
		    
		    //유효성 체크
		    if(validateForm(frm)){
		    	
		    	var confirmMessage = frm.event_no.value==("") ? "<spring:message code='secfw.msg.ask.insert' />" : "<spring:message code='secfw.msg.ask.update' />" ;
		    	
		    	fileList.UploadFile(function(uploadCount){

					//지정된 형식 이외에 파일을 추가한 경우
					if (uploadCount < 0) {
						return;
					}                  

                    if(checkForm() && confirm(confirmMessage)){
    		    		
    		    		viewHiddenProgress(true) ;
    		    		frm.method.value = frm.event_no.value==("") ? "insertEventManage" : "modifyEventManage";
    					frm.target = "_self";
    					frm.action = "<c:url value='/las/lawservice/eventManage.do' />";

    			    	frm.submit();	
    		    	} else {
    		    		return;	
    		    	}
                });
		    } 		    
			
		//취소
		} else if(flag == "cancel"){
			
			<c:choose>
				<c:when test="${command.forward_from ==  '1'}">
					frm.action = "<c:url value='/las/lawservice/eventAcceptSrvCost.do' />";
					frm.method.value = "listEventSub";
				</c:when>
				<c:otherwise>
					frm.action = "<c:url value='/las/lawservice/eventManage.do' />";
					frm.method.value = "listEventManage";
				</c:otherwise>
			</c:choose>
			
			viewHiddenProgress(true) ;			
			frm.target = "_self";
			frm.submit();			
		} 
	}
	
	/**
	* Form check
	*/
	function checkForm(){
		var frm = document.frm;
		var check_tag = true;
		
		return check_tag;
	}
	
	/**
	* select box 에서 선택된 항목의 텍스트 리턴
	*/
	function GetSelectedTxt(objSelect){
        var i;
        var selectedtext;
        for(i=0;i<objSelect.options.length;i++){
                if(objSelect.options[i].selected==true){
                        selectedtext= objSelect.options[i].text;
                        break;
                }
        }
        return selectedtext;
	}
	
	function selectOptions(select){
		
		var frm = document.frm;		
		var textValue;
		
		textValue = select.options[select.selectedIndex].text;
		
		frm.title.value=textValue;
	}
	
	function show_hidden_dept_nm(){
		
		var frm = document.frm;
		
		if(frm.dept_nm.value != ''){
			alert(frm.dept_nm.value);
/* 			alert(frm.intnl_dept_cd.value);
			alert(frm.grp_dept_cd.value); */
		}else{
			alert('<spring:message code="las.page.msg.lawservice.deptselect" />');
		}
	}
	
	function show_hidden_dept_nm_div(){
		if(intnl_dept_nm_div.style.visibility == "visible"){
			intnl_dept_nm_div.style.visibility="hidden";
		}
	}

	
//-->
</script>

</head>
<body>
<form:form name="frm" id="frm" method="post" autocomplete="off">
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
<input type="hidden" name="screen_type"  value="insert" />
<input type="hidden" name="forward_from"  value="<c:out value='${command.forward_from}'/>" />

<!-- search form -->
<input type="hidden" name="srch_event_no"   value="<c:out value='${command.srch_event_no}'/>" />
<input type="hidden" name="srch_event_nm"   value="<c:out value='${command.srch_event_nm}'/>" />
<input type="hidden" name="srch_lawfirm_nm"   value="<c:out value='${command.srch_lawfirm_nm}'/>" />  
<input type="hidden" name="srch_lawfirm_id"   value="<c:out value='${command.srch_lawfirm_id}'/>" />
<input type="hidden" name="srch_kbn1"   value="<c:out value='${command.srch_kbn1}'/>" />
<input type="hidden" name="srch_kbn2"   value="<c:out value='${command.srch_kbn2}'/>" />
<input type="hidden" name="srch_lwr_nm"   value="<c:out value='${command.srch_lwr_nm}'/>" />
<input type="hidden" name="srch_reg_nm"   value="<c:out value='${command.srch_reg_nm}'/>" />
<input type="hidden" name="srch_start_dt"   value="<c:out value='${command.srch_start_dt}'/>" />
<input type="hidden" name="srch_end_dt"   value="<c:out value='${command.srch_end_dt}'/>" />
<input type="hidden" name="srch_order_type"   value="<c:out value='${command.srch_order_type}'/>" />

<!-- key form-->
<input type="hidden" name="forward_url"   value="" />
<input type="hidden" name="event_no"   value="<c:out value='${command.event_no}'/>" />
<input id="dept_nm" name="dept_nm" value="" maxlength="500" type="hidden"  />
<input id="intnl_dept_cd" name="intnl_dept_cd" value="" maxlength="500" type="hidden"  />
<input id="grp_dept_cd" name="grp_dept_cd" value="" maxlength="500" type="hidden"/>

<!-- 첨부파일정보 시작 -->
<input type="hidden" name="fileInfos"   value="" />
<input type="hidden" name="file_bigclsfcn"  value="F005" />
<input type="hidden" name="file_midclsfcn" 	value="F00503" />
<input type="hidden" name="file_smlclsfcn"  value="" />
<input type="hidden" name="ref_key"     value="<c:out value='${command.event_no}'/>" />
<c:choose>
<c:when test="${command.event_no == ''}">
	<input type="hidden" name="view_gbn"    value="upload" />
	</c:when>
	<c:otherwise>
	<input type="hidden" name="view_gbn" 	value="modify" />
	</c:otherwise>
</c:choose>

<!-- 나모 웹 에디터 관련 -->
<c:choose>
	<c:when test="${command.event_no == ''}">
		<input type="hidden" name="body_mime" id="body_mime" value="" />
	</c:when>
	<c:otherwise>
		<input type="hidden" name="body_mime" id="body_mime" value="<c:out value='${lom.event_cont}' />" />
	</c:otherwise>
</c:choose>

<!-- // **************************** Form Setting **************************** -->

<div id="wrap">	
	<!-- container -->
<div id="container">
	<!-- Location -->
	<div class="location"><IMG SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
	<!-- //Location -->	
	<!-- title -->
	<div class="title">
		<c:choose>
			<c:when test="${command.event_no == ''}">
				<h3><spring:message code="las.page.field.lawservice.event" /> <spring:message code="las.page.field.lawservice.register" /></h3>
			</c:when>
		<c:otherwise>
			<h3><spring:message code="las.page.field.lawservice.event" /> <spring:message code="las.page.field.lawservice.modify" /></h3>
				</c:otherwise>
		</c:choose>		
	</div>
	<!-- //title -->
	<!-- content -->
	<div id="content">
		<!-- view -->
		<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
			<colgroup>
				<col width="100" />
				<col width="310" />
				<col width="100" />
				<col width="310" />
			</colgroup>
			<tbody>
				<tr>
					<th><spring:message code="las.page.field.lawservice.eventnm" /></th>
					<td colspan="3">
					<input alt="<spring:message code="las.page.field.lawservice.eventnm" />" id="event_nm" name="event_nm" value="<c:out value='${lom.event_nm}'/>" maxlength="100" type="text" style="width:300px" required />
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.kbn" /></th>
					<td>
						<select id="event_gbn1" name="event_gbn1" style="width:150px;" onChange="StReload_partin(this.value);" required alt='<spring:message code="las.page.field.lawservice.gbn" />' >
						</select>
						<span id="partin2"></span>
					</td>
					<th><spring:message code="las.page.field.lawservice.lawsuittrgt" /></th>
					<td>
						<select id="lawsuit_trgt" name="lawsuit_trgt" >
						</select>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.moneyrech" /></th>
					<td colspan="3">
						<select id="dept_select" name="dept_select" style="width:150px;" onChange="listDeptTree();">
							<option value="0" ><spring:message code="las.page.field.lawservice.select" /></option>
							<option value="1" ><spring:message code="las.page.field.lawservice.selectdept" /></option>
						</select>
						<input required alt="<spring:message code="las.page.field.lawservice.srvcostdept"/>" id="intnl_dept" name="intnl_dept" value="" maxlength="200" type="text" style="width:350px" alt="" readonly /> 
						<a href="javascript:show_hidden_dept_nm();"><spring:message code="las.page.field.lawservice.viewseldept"/></a>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.incharge" /></th>
					<td>
					<c:choose>
						<c:when test="${command.event_no == ''}">
							<c:out value='${command.reg_nm}'/>
						</c:when>
						<c:otherwise>
							<c:out value='${lom.reg_nm}'/>
						</c:otherwise>
					</c:choose>						
					</td>
					<th><spring:message code="las.page.field.lawservice.regdt"/></th>
					<td>
					<c:choose>
						<c:when test="${command.event_no == ''}">
							<c:out value='${command.reg_dt}'/>
						</c:when>
						<c:otherwise>
							<c:out value='${lom.reg_dt}'/>
						</c:otherwise>
					</c:choose>	
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.eventsum" /></th>
					<td colspan="3">
					<%@ include	file="/WEB-INF/jsp/secfw/common/NamoEditInclude.jspf"%>
					</td>
				</tr>
				<tr class="end">
					<th><spring:message code="las.page.field.lawservice.attachfile" /></th>
					<td colspan="3">
						<iframe src="<c:url value='/las/blank.do' />" id="fileList" name="fileList" frameborder="0" width="100%" height="80px" leftmargin="0" topmargin="0" scrolling="auto" ></iframe>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- //view -->
		
		<div class="t_titBtn">
			<div class="btn_wrap">
				<span class="btn_all_b"><span class="save"></span><a href="javascript:pageAction('insert')"><spring:message code="las.page.button.lawservice.save" /></a></span>
				<span class="btn_all_w"><span class="cancel"></span><a href="javascript:pageAction('cancel')"><spring:message code="las.page.button.lawservice.cancel" /></a></span>
			</div>
		</div>

	</div>
	<!-- //content -->
			<!-- footer  -->
			<script language="javascript" src="/script/clms/footer.js"
				type="text/javascript"></script>
			<!-- // footer -->
			<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</div>
<!-- //container -->
</div>
</form:form>
</body>
<!-- 나모 웹에디터 관련 추가 -->
<c:choose>
	<c:when test="${lom.event_no == ''}">
		<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
			var wecObj = document.wec;
			wecObj.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
			wecObj.SetDefaultFontSize("9");
			wecObj.EditMode = 1;
		</SCRIPT>
	</c:when>
	<c:otherwise>
		<SCRIPT language="javascript" FOR="wec" EVENT="OnInitCompleted()">
			var wecObj = document.wec;
			wecObj.SetDefaultFont('<spring:message code="secfw.page.namo.default.font" />');
			wecObj.SetDefaultFontSize("9");
			wecObj.EditMode = 1;
			wecObj.Value = document.frm.body_mime.value;
		</SCRIPT>
	</c:otherwise>
</c:choose>
</html>
