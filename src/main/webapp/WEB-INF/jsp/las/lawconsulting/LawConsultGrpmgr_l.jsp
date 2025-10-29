<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@page import="com.sds.secframework.common.util.PageUtil"%>
<%@ page import="com.sds.secframework.common.util.StringUtil" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ page import="com.sec.common.util.ClmsBoardUtil" %>
<%@ page import="java.util.ArrayList" %>
<%
    PageUtil pageUtil = (PageUtil)request.getAttribute("pageUtil") ;
    session.removeAttribute("main_approach");
    
	boolean authYN_RA02 = false;
	
	ArrayList tmpSessionRoleList = (ArrayList)session.getAttribute("secfw.session.roleList");
		
	if(tmpSessionRoleList.contains("RA02")){
		authYN_RA02 = true;
	}
%>
<html>
<head>
	<meta charset="utf-8" />

	<title><spring:message code="las.main.title" /></title>
	<LINK href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet">
	<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->


<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/CommonProgress.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/jquery.mousewheel.min.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/calendar/calendar_clms_${langCd}.js' />"></script>
<script language="javascript">
	$(document).ready(function(){
		// 2014-02-10 Kevin. TO-DO 리스트의 값 로드가 중복으로 발생한다. 페이지 로딩 시간이 오래 걸리는 상황에서는 응답시간 지연이 발생하여 주석처리 함. 데이터 Refresh는 메뉴 클릭으로 대신한다.
		/* window.parent.frames['leftFrame'].refreshCount();  */
		
	    getCodeSelectByUpCd("frm", "srch_prgrs_status", '/common/clmsCom.do?method=listComCdByGrpCd&combo_abbr=F&combo_sys_cd=LAS&combo_grp_cd=N1_I&combo_type=S&useman_mng_itm1=S&combo_del_yn=N&combo_selected=<c:out value='${command.srch_prgrs_status}'/>');
	    getCodeSelectByUpCd("frm", "srch_reg_dept", '/common/clmsCom.do?method=getUnitOrgnzComboHTML&up_orgnz_cd=B00000004&combo_abbr=A&combo_type=S&locale='+'<c:out value='${langCd2}'/>'+'&combo_selected='+'<c:out value='${command.srch_reg_dept}'/>');
	    getCodeSelectByUpCd("frm", "srch_elcomp", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=F&combo_grp_cd=USERCOMP&combo_type=T&combo_del_yn=N&sel_grd=<c:out value='${command.sSel_grd}'/>&combo_selected='+'<c:out value='${command.srch_elcomp}'/>');
	    initCal("srch_start_ymd");   //첫번재 날짜창 설정 : 변수는 해당 태그의 id값
        initCal("srch_end_ymd","20110803163201974_0000000117");     //두번재 날짜창 설정 : 변수는 해당 태그의 id값

        
        //if(frm.srch_reception.value == "Y")
        //  frm.srch_reception.checked = true;
        //else
        //  frm.srch_reception.checked = false;

        <%//if(ClmsBoardUtil.searchRole(request, "RA00")){ %>
        //$('[name=srch_reception]').hide();
        //$('#receipt').hide();
        <%//}%>
    });

    $(function(){

        $("#srch_respman_nm").keydown(function(event) {
            if(event.keyCode == "13") {
                search();
            }
        });
        $("#srch_title").keydown(function(event) {
            if(event.keyCode == "13") {
                search();
            }
        });
        $("#srch_cont").keydown(function(event) {
            if(event.keyCode == "13") {
                search();
            }
        });
    });

    function search(){
        goPage(1) ;
    }
    
    function initPage(){
    	alertMessage('<c:out value='${command.return_message}'/>');
    }

    function goPage(page) {
        viewHiddenProgress(true) ;
        var f = document.frm ;

        var startDt = f.srch_start_ymd.value ;
        var endDt = f.srch_end_ymd.value ;

        if(startDt == "" && endDt != ""){
            viewHiddenProgress(false) ;
            alert("<spring:message code='las.page.field.statistics.inputFromDate'/>") ;
            f.srch_start_ymd.focus() ;

            return ;
        }
        
        if(startDt != "" && endDt == ""){
            viewHiddenProgress(false) ;
            alert("<spring:message code='las.page.field.statistics.inputToDate'/>") ;
            f.srch_end_ymd.focus() ;

            return ;
        }
        
        
        if(startDt>endDt){
            viewHiddenProgress(false) ;
            alert("<spring:message code='las.page.field.lawconsulting.setFromDateEarly'/>") ;
            f.srch_start_ymd.focus() ;
            return ;
        }

        //if(f.srch_reception.checked){
        //  f.srch_reception.value = 'Y';
        //}
        //else{
        //  f.srch_reception.value = 'N';
        //}

        f.cnslt_no.value = "";
        f.hstry_no.value = 0;
        f.curPage.value = page ;
        f.method.value = "listLawConsult" ;
        f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
        f.target = "_self" ;
        f.submit() ;
    }

    function goDetail(cnslt_no, hstry_no) {
        viewHiddenProgress(true) ;
        var f = document.frm ;
        f.cnslt_no.value = cnslt_no;
        f.hstry_no.value = hstry_no;
        f.method.value = "forwardDetailLawConsult" ;
        f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
        f.target = "_self" ;
        f.submit() ;
    }

    /*자문유형(상담종류) 팝업*/
    function popup(){
        var f = document.frm ;
        f.cnslt_no.value = "";
        f.hstry_no.value = 0;
        f.method.value = "forwardPopupLawConsult" ;
        f.action = "<c:url value='/las/lawconsulting/lawConsult.do' />" ;
        PopUpWindowOpen('', 700, 380, true);
        frm.target = "PopUpWindow";
        f.submit() ;
    }

    /* 팝업 창에서 체크한 상담종류에 대한 값을 받음 */
    function returnType(type, type_name){

        frm.srch_consult_type.value = type;
        frm.srch_consult_type_name.value = type_name;
        frm.srch_consult_type_name.setAttribute('width',frm.srch_consult_type_name.value.length);
    }

    /**
     * ESB 임직원 조회
     */
    function searchEmployee() {
        var frm = document.frm;
        frm.srch_user_cntnt.value = frm.srch_reg_nm.value;
        var srchValue = comTrim(frm.srch_user_cntnt.value); //입력받은 값

        if (srchValue == "" || getByteLength(srchValue) < 4) { //최소 2자리 이상 입력받게 한다.
            alert('<spring:message code='secfw.msg.error.nameMinByte' />');
            frm.srch_reg_nm.focus();
            return;
        }

        PopUpWindowOpen('', 800, 450, true);
        frm.target = "PopUpWindow";

        frm.srch_user_cntnt_type.value = "userName"; //이름으로 검색  - 보통 이름으로 검색함
        //frm.srch_user_cntnt_type.value = "userId"; //마이싱글아이디로 검색(tb_com_user의 user_id 아님, tb_com_user의 single_id 임)
        //frm.srch_user_cntnt_type.value = "epid"; //EPID로 검색(tb_com_user의 user_id임)

        frm.action = "<c:url value='/secfw/esbOrg.do' />";
        frm.method.value = "listEsbEmployee";
        frm.submit();

        frm.target = "";
    }

    function setUserInfos(obj) {
        var f = document.frm;
        var userInfo = obj;

        f.srch_reg_nm.value = userInfo.cn;
        f.srch_reg_id.value = userInfo.epid;
    }
    
    
    function excelDownLoad(){
    	// listLawconsultReviewExcel
    
    	var frm = document.frm;
		
		// 1000row가 넘어가는 시점에서 날짜를 체크 하고 있음.
		if(<%=pageUtil.getTotalRow()%>>1000){
			alert("<spring:message code='clm.page.msg.manage.announce180max1000'/>");
			return;
		}
			//!@#$ 2013.11.29 기존 300건 초과시 조회날짜 조정 검사에서 1000건으로 변경
// 		    if(frm.srch_start_ymd.value == ''){
// 			    alert("<spring:message code='clm.page.msg.manage.announce128'/>");//의뢰일 시작을 선택해주세요.
// 			    return;
// 		    }
// 		    if(frm.srch_end_ymd.value == ''){
// 			    alert("<spring:message code='clm.page.msg.manage.announce129'/>");//의뢰일 종료를 선택해주세요.
// 			    return;
// 		    }
// 		    //날짜계산
// 		    var sd = frm.srch_start_ymd.value; //start Day
// 	        var ed = frm.srch_end_ymd.value; //end Day
// 		    sd = sd.split("-");
// 		    ed = ed.split("-");
// 		    var st = new Date(sd[0],sd[1]-1,sd[2]).getTime();
// 		    var et = new Date(ed[0],ed[1]-1,ed[2]).getTime();
// 		    var fromto = (et-st)/(1000*60*60*24)+1;

// 		    if(fromto > 90){
// 			    alert("<spring:message code='clm.page.msg.manage.announce180max90'/>");//최대 90일 기간까지 다운 할 수 있습니다.
// 			    return;			
// 		    }
//		}

		//viewHiddenProgress(true) ;
		frm.cnslt_no.value = "";
        frm.hstry_no.value = 0;
		frm.target = "_self";
		frm.action = "<c:url value='/las/lawconsulting/lawConsult.do' />";
	    frm.method.value = "listLawconsultExcel";
	    frm.curPage.value = "1";
	    frm.submit();
    }
    
    
	function setMarkUpAJAX(cnslt_no, hstry_no) {
		
		var frm = document.frm;
		var confirmMessage = "";
		var mark_src = "";
		var flag= "";		
		
		var msg_chk = "<spring:message code='las.msg.considertaton.mark001' />";   // 중요도를 체크 처리 하시겠습니까?
		var msg_unchk = "<spring:message code='las.msg.considertaton.mark002' />";   // 해제 처리 하시겠습니까?
		var msg_comp = "<spring:message code='las.msg.succ.process' />";   // 처리 되었습니다.
				
		frm.cnslt_no.value = cnslt_no;
		frm.hstry_no.value = hstry_no;
		
		
		if($('#icon_' + cnslt_no).attr('alt')=='3'){
			confirmMessage = msg_unchk;		
			mark_src = "/images/las/en/icon/icon_b_white.gif";
			frm.mark_num.value = "1";
			flag = "unchk";			
		} else {
			confirmMessage = msg_chk;
			mark_src = "/images/las/en/icon/icon_b_red.gif";
			frm.mark_num.value = "3";			
		}
			
		//if(confirm(confirmMessage)){		
			
			var options = {   
					url: "<c:url value='/las/lawconsulting/lawConsult.do?method=setMarkUpAJAX' />",
					type: "post",
					dataType: "json",
					success: function(responseText, statusText) {
						if(responseText.returnCnt != 0) {
							var html = "";
							$.each(responseText, function(entryIndex, entry) {
								
								var return_val = entry['return_val'];
								
								if(return_val != '0'){
									//alert($('#icon_' + cnslt_no).attr('src') + '//////' + mark_src);
									
									if(flag=='unchk'){
										$('#icon_' + cnslt_no).attr('alt','1');
									} else {
										$('#icon_' + cnslt_no).attr('alt','3');
									}
									
									$('#icon_' + cnslt_no).attr('src',mark_src);
									//alert(msg_comp); // 처리하였습니다.
								}									

							});						
						}	 
						
						// viewHiddenProgress(false) ;	
					}
			};		
			$("#frm").ajaxSubmit(options);	
	//}		
	}	
	
    
    
    
    
</script>
</head>
<!--  2012.04.26 Alert 수정 modified by hanjihoon  -->
<body onload="initPage()" autocomplete="off">

<div id="wrap">
  <!-- container -->
  <div id="container">
    <!-- Location -->
    <div class="location">
      <img src="<%=IMAGE%>/icon/ico_home.gif" border="0" alt="Home" /><c:out value='${menuNavi}' escapeXml="false"/>
    </div>
    <!-- //Location -->
    <!-- title -->
    <div class="title">
      <%-- /* <h3><spring:message code="las.page.field.lawconsult.main_title" /><!--접수현황조회--></h3> */ --%>
      <h3><spring:message code="las.page.field.lawconsulting.lawAdvReview"/></h3>
    </div>
    <!-- //title -->
    <!-- content -->
    <div id="content">
    <div id="content_in">
    <form:form name="frm" id="frm" autocomplete="off">
	<input type="hidden" name="method" value=""/>
	<input type="hidden" name="menu_id" value="<c:out value='${command.menu_id}'/>"/>
	<input type="hidden" name="curPage" value="<c:out value='${command.curPage}'/>"/>
	<input type="hidden" name="isForeign" value="<c:out value='${command.isForeign}'/>"/>
	<input type="hidden" name="cnslt_no" />
	<input type="hidden" name="hstry_no" />
	<input type="hidden" name="srch_consult_type" value="<c:out value='${command.srch_consult_type}'/>"/>
	<input type="hidden" name="srch_user_cntnt_type" value=""/>
	<input type="hidden" name="srch_user_cntnt" value=""/>
	<input type="hidden" name="srch_reg_id" value="<c:out value='${command.srch_reg_id}'/>"/>
	<!-- input type="hidden" name="srch_reception" value="<c:out value='${command.srch_reception}'/>"/ -->
	
	<input type="hidden" id="isStdCont" name="isStdCont" value="N"/>
	<input type="hidden" name="fwd_gbn" value="grpmgr"/>
	<input type="hidden" name="mark_num" id="mark_num" value="" />	<!-- 중요도 체크용-->
   
	<div class="search_box">
		<div class="search_box_inner">
			<table class="search_tb">
            <colgroup>
            	<col/>
            	<col width="75px"/>
            </colgroup>
	            <tr>
					<td>
						<table class="search_form">
	                    	<colgroup>
								<col width="*"/>
								<col width="*"/>
								<col width="*"/>
								<col width="*"/>
								<col width="*"/>
								<col width="*"/>
							</colgroup>
	                    	
	                    	<tr>
		                      	<th><spring:message code="las.page.field.lawconsult.reg_dt"/><!--의뢰일--></th>
		                      	<td>
		                         	<input type="text" name="srch_start_ymd" id="srch_start_ymd" value="<c:out value='${command.srch_start_ymd}'/>"  class="text_calendar02" /> ~
		                         	<input type="text" name="srch_end_ymd" id="srch_end_ymd" value="<c:out value='${command.srch_end_ymd}'/>" class="text_calendar02" /></td>
		                      	<th><spring:message code="las.page.field.manageCommon.srchLasCnsdmanNm" /><span class="astro"></span></th>
		                      	<td>
		                          	<input id="srch_respman_nm" name="srch_respman_nm" value="<c:out value='${command.srch_respman_nm}' escapeXml='false'/>" type="text" class="text" style='width:179px'/>
		                      	</td>
		                      	<th><spring:message code="las.page.field.lawconsult.prgrs_status" /><!--진행상황--><span class="astro"></span></th>
		                      	<td>
		                          	<select name="srch_prgrs_status" id="srch_prgrs_status" style='width:182px'></select>
		                      	</td>
	                    	</tr>
	                    	<tr>
								<!-- 
								<th><spring:message code="las.page.field.lawconsult.regdept" /></th>
								<td>
								    <select name="srch_reg_dept" id="srch_reg_dept"  style='width:268px'>
								    </select>
								</td>
								 -->
								 
								<!-- 의뢰명 : Subject (Title ) -->
								<th><spring:message code="las.page.field.lawconsult.title" /></th>
								<td><input id="srch_title" name="srch_title" value="<c:out value='${command.srch_title}' escapeXml='false'/>" type="text" class="text" style='width:179px'/></td>
								
								<!-- 의뢰자 : Requester  -->
								<th><spring:message code="las.page.field.contractManager.requBy" /></th>
								<td>
									<input id="srch_reg_nm" name="srch_reg_nm" value="<c:out value='${command.srch_reg_nm}' escapeXml='false'/>" type="text"  class="text" style='width:179px'/>
									<%--<img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:searchEmployee();" class="cp" alt="search" /> --%>
								</td>
								
								<!-- 자문유형 : type of Legal advice  -->
								<th><spring:message code="las.page.field.lawconsult.consulttype" /></th>
								<td>
									<input id="srch_consult_type_name" name="srch_consult_type_name" value="<c:out value='${command.srch_consult_type_name}' escapeXml='false' />" type="text" class="text" style='width:178px'/>
									
									<!-- 2013.12.10 검색방법을 팝업을 통한 조회조건 선택에서, Key-in 으로 변경  -->
									<%-- <img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:popup()" class="cp" alt="search" /> --%>
								</td>
							</tr>
	                    	<tr>
	                      		<th><spring:message code="clm.page.msg.common.content" /></th>
	                      		<td>
	                      			<input id="srch_cont" name="srch_cont" value="<c:out value='${command.srch_cont}' escapeXml='false'/>" type="text" class="text" style='width:179px'/>  
	                      		</td>
		                      <!--
		                      <th id="receipt"><spring:message code="las.page.field.lawconsult.receipt" /></th>
		                      <td id="receipt">
		                        <input type="checkbox" name="srch_reception" value="<c:out value='${command.srch_reception}'/>"/>
		                      </td>
	                       		-->
<%-- 	                     	<th><spring:message code="las.page.field.lawconsult.solo_yn" /></th> --%>
<!-- 		            		<td> -->
<!-- 								<select name="srch_solo_yn" id="srch_solo_yn" style='width:182px'/> -->
<%-- 									<option value=""><spring:message code="las.page.field.contractManager.selectL"/></option><!-- -- 선택 -- --> --%>
<%-- 									<option value="1" <c:if test="${command.srch_solo_yn eq '1'}">selected</c:if>><spring:message code="las.page.field.lawconsult.cavity.dmst"/></option><!-- 공동 --> --%>
<%-- 									<option value="2" <c:if test="${command.srch_solo_yn eq '2'}">selected</c:if>><spring:message code="las.page.field.lawconsult.cavity.frgn"/></option><!-- 공동 --> --%>
<%-- 									<option value="3" <c:if test="${command.srch_solo_yn eq '3'}">selected</c:if>><spring:message code="las.page.field.lawconsult.alone"/></option><!-- 단독 --> --%>
<!-- 								</select> -->
<!-- 							</td> -->
							<th><spring:message code="las.page.field.lawconsult.completYn"/></th>
							<td>
								<select name="srch_complete_yn" id="srch_complete_yn" style="width:182px">
									<option value="">-- Select --</option>
									<option value="Y" <c:if test="${command.srch_complete_yn eq 'Y'}">selected</c:if>><spring:message code="las.page.field.option.complete"/></option>
									<option value="N" <c:if test="${command.srch_complete_yn eq 'N'}">selected</c:if>><spring:message code="las.page.field.option.uncompleted"/></option>
								</select>
								
							</td>
							
	          				<td></td>
	          				<c:if test="${elUserYn != 'Y'}">
	          				<td class="tR">
				            	<a href="javascript:search()"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="las.page.field.lawconsulting.search"/>" style='margin-right:9%' class='mt5'/></a>
				            </td>
				            </c:if>
	                    </tr>
	                    
	                    <c:if test="${elUserYn == 'Y'}">
	              			<tr>
		              			<th><spring:message code="clm.page.msg.common.selCompNm" /></th>
		              			<td>
		               				<select class="srch" name="srch_elcomp" id="srch_elcomp"> </select>
		              			</td>
		              			<td colspan='3'></td>
		          				<td class="tR">
					            	<a href="javascript:search()"><img src="<%=IMAGE%>/btn/btn_search.gif" alt="<spring:message code="las.page.field.lawconsulting.search"/>" style='margin-right:9%' class='mt5'/></a>
					            </td>
	              			</tr>
	              			
	          			</c:if>
	          			
					</table>
				</td>
	            
			</tr>
		</table>
    </div>
    </div>
        <!-- //90% 안에서 작업하기 search -->
        <!-- list -->
        <table class="list_basic mt20" >
        
        <!-- button -->
		<div class="t_titBtn">
			<div class="btn_wrap_t">
				<span class="btn_all_w" onclick="excelDownLoad();"><span class="excel_down"></span><a> <spring:message code='las.page.button.excelDownload' /></a></span><!-- 엑셀 다운로드 -->
			</div>
	  	</div>
		<!-- //button -->
        
        <c:choose>
              	<c:when test="${elUserYn == 'Y'}">
        			 <colgroup>
			            <col width="10%" />
<%-- 			            <col width="10%" /> --%>
			            <col width="*%"/>
			            <col width="9%" />
			            <col width="9%" />
			            <col width="20%" />
			            <col width="9%" />
			            <col width="8%" />
			            <col width="9%" />
			         </colgroup>      		
              	</c:when>
              	<c:otherwise>
              	    <colgroup>
			            <col width="5%" />
<%-- 			            <col width="7%" /> --%>
			            <col width="*%"/>
			            <col width="13%" />
			            <col width="13%" />
			            <col width="13%" />
			            <col width="8%" />
			            <col width="8%" />
			            <col width="9%" />
			          </colgroup>
              	</c:otherwise>
              </c:choose>
        
          <thead>
            <tr>
              <c:choose>
              	<c:when test="${elUserYn == 'Y'}">
              		<th><spring:message code="secfw.page.field.user.comp_nm" /></th>
              	</c:when>
              	<c:otherwise>
              		<th><spring:message code="las.page.field.lawconsult.no" /></th>
              	</c:otherwise>
              </c:choose>
<%--               <th><spring:message code="las.page.field.lawconsult.solo_yn_list" /></th> --%>
              <th><spring:message code="las.page.field.lawconsult.title" /></th>
              <th><spring:message code="las.page.field.lawconsideration.dept"/></th>
              <th><spring:message code="las.page.field.contractManager.requBy" /></th>
              <th><spring:message code="las.page.field.manageCommon.srchLasCnsdmanNm" /></th>
              <th><spring:message code="las.page.field.lawconsult.reg_dt" /></th>
              <th><spring:message code="las.page.field.lawconsult.status" /></th>
              <th><spring:message code="las.page.field.lawconsult.completYnList"/></th>
          	</tr>
          </thead>
          <tbody>
            <c:choose>
              <c:when test="${pageUtil.totalRow > 0}">
                <c:forEach var="list" items="${command.lawconsult_list}">
                  <tr onmouseout="this.className='';" onmouseover="this.className='selected'">
                  	<c:choose>
		              	<c:when test="${elUserYn == 'Y'}">
		              		<td class='tC'><c:out value='${list.comp_nm}'/></td>
		              	</c:when>
		              	<c:otherwise>
		              		<td class='tC'><c:out value='${list.rm}'/></td>
		              	</c:otherwise>
		            </c:choose>
                    <td class="tL">
                    	
						<!--  검토권한자에 한해서 중요 마크 보여줌  -->
                    	<% if(authYN_RA02){ %>
							<c:choose>
								<c:when test="${list.mark_num eq '3'}">
									<img  id="icon_<c:out value='${list.cnslt_no}'/>" src="/images/las/en/icon/icon_b_red.gif" class='img_align'  onclick="javascript:setMarkUpAJAX('<c:out value='${list.cnslt_no}'/>', '<c:out value='${list.hstry_no}'/>');" alt="<c:out value='${list.mark_num}'/>" style="cursor:pointer"/>&nbsp;					
								</c:when>
								<c:otherwise>
									<img  id="icon_<c:out value='${list.cnslt_no}'/>" src="/images/las/en/icon/icon_b_white.gif" class='img_align' onclick="javascript:setMarkUpAJAX('<c:out value='${list.cnslt_no}'/>', '<c:out value='${list.hstry_no}'/>');" alt="<c:out value='${list.mark_num}'/>"  style="cursor:pointer" />&nbsp;															
								</c:otherwise>
							</c:choose>
						<%} %>
                    
                    	<a href="javascript:goDetail('<c:out value="${list.cnslt_no}"/>','<c:out value="${list.hstry_no}"/>')">
                    		<c:out value='${list.title}' escapeXml="false"/>
                    	</a>
                    </td>
                    <td class='tL'><c:out value='${list.reg_dept_nm}'/></td>
                    <td class='tL'><c:out value='${list.reg_nm}'/></td>
                    <td class='tL'><c:out value='${list.respman_nm}'/></td>
                    <td class='tC'><c:out value='${list.reg_dt_date}'/></td>
                    <td class='tC'><c:out value='${list.prgrs_status_name100}'/></td>
                    <td class='tC'>
	                    <c:choose>
							<c:when test="${list.complete_yn=='Y'}">
								<spring:message code="las.page.field.option.complete"/>
							</c:when>
							<c:otherwise>
								<spring:message code="las.page.field.option.uncompleted"/>
							</c:otherwise>
						</c:choose>
                    </td>
                  </tr>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <tr onmouseout="this.className='';" onmouseover="this.className='selected'">
                  <td colspan="8" class="tC"><spring:message code="las.page.msg.noResult" /></td>
                </tr>
              </c:otherwise>
            </c:choose>
          </tbody>
        </table>
        <!-- //list -->
        <!-- pagination -->
        <div class="t_titBtn">
          <div class="total_num">
            Total : <%= StringUtil.commaIn(String.valueOf(pageUtil.getTotalRow())) %>
          </div>
          <div class="pagination">
            <%= pageUtil.getPageNavi(pageUtil, "") %>
          </div>
        </div>
        <!-- //pagination -->
      </div>
      
      </form:form>
    
    </div>
    <!-- //content -->
    
    <jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
  </div>
  <!-- //container -->
</div>

<!-- footer -->
    <script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
    <!-- // footer -->

<jsp:include page="/WEB-INF/jsp/secfw/common/DlgAlert.jsp"/>
</body>
</html>