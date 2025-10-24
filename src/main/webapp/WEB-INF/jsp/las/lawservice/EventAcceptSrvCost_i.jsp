<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
 * 파  일  명 : EventAcceptSrvCost_i.jsp
 * 프로그램명 : 용역비 입력 화면
 * 설      명 :  용역비를 입력한다.
 * 작  성  자 :  박병주
 * 작  성  일 : 2011.09
 */
--%>
<%
    //메뉴네비게이션 스트링
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="las.main.title" /></title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet" />

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
				
		var frm = document.frm;
		// 첨부파일창 load
		initPage();
		
		// 날짜 textbox
 		$('*').attr('class',function(index){
			if($(this).attr("class") == 'text_calendar02'){
				initCal($(this).attr("id"));
			}
		}); 
  		
	
		// 사건구분1
		getCodeSelectByUpCd2("frm", "event_gbn1", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"106"+"&combo_abbr=F&combo_type=T&combo_use_yn=Y&combo_selected="+"<c:out value='${event_lom.event_gbn1}'/>");
		
		// 사건구분2
		getCodeSelectByUpCd2("frm", "event_gbn2", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"<c:out value='${event_lom.event_gbn1}'/>"+"&combo_abbr=F&combo_type=T&combo_use_yn=Y&combo_selected="+"<c:out value='${event_lom.event_gbn2}'/>");

		// 화폐단위 20111026  운영요청  입력이 빈번한 화폐로 설정
		// getCodeSelectByUpCd2("frm", "crrncy_unit", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+"C5"+"&combo_abbr=F&combo_type=S2&combo_use_yn=Y&combo_selected="+"<c:out value='${lom.crrncy_unit}'/>");
		
		// 청구금액 TEXTBOX 숫자체크 합산 (KEYUP EVENT)		
		$('#src_tb tr input').bind('keyup',function(){		
			
 				if($(this).val() == ''){ // ''체크
					$(this).val('');
				} 
				
				if(isNaN($(this).val())){ // 문자체크
					$(this).focus();
					$(this).val('');
					alert("<spring:message code='las.page.msg.lawservice.onlynum' />");	
				}
				
 				if($(this).attr('id') == 'srvc_amt'){ // 합산 
 					$(this).parent().next().next().children().val(new Number($(this).val()) + new Number($(this).parent().next().children().val()));
				}  				
 				if($(this).attr('id') == 'addtnl_amt'){
					$(this).parent().next().children().val(new Number($(this).val()) + new Number($(this).parent().prev().children().val()));
				}  
	 	});	
		
		// 숫자체크  합산 (FOCUSOUT EVENT)		
		$('#src_ul li input').bind('focusout',function(){		
				if($(this).attr('id') == 'srvc_amt'){ // 합산 
					 $(this).next().next().val(new Number($(this).val()) + new Number($(this).next().val())); 
			}  				
				if($(this).attr('id') == 'addtnl_amt'){
				 $(this).next().val(new Number($(this).prev().val()) + new Number($(this).val())); 
			} 
 		});	
		
		// 청구금액 소수점 두자리로 반올림
		$('#src_tb tr input').bind('focusout',function(){
			if($(this).attr('id') != 'dc_rate')
				$(this).val(new Number($(this).val()).toFixed(2));
		});
				
		// 송금금액  소수점 두자리로 반올림
		$('#remit_amt').bind('focusout',function(){
				$(this).val(new Number($(this).val()).toFixed(2));
		});	
		
		// 화폐단위 선택시 환율적용
		// $("#crrncy_unit").attr("selectedIndex", -1);

        $("#crrncy_unit").change(function()
        {
           check_rate();
        });
        
		// 날짜 수입력시 날짜 포맷체크
		/*$('*').attr('class',function(index){
			if($(this).attr("class") == 'text_calendar02'){
				$(this).bind('focusout',function(){		
					if(!checkdateformat($(this).val())){
						alert("입력 날짜가 올바르지 않습니다.");
						$(this).focus();
					}
	 			});
				
			}
		});		*/
		
	}); 	//초기화면 로딩 끝

	
	/**
	* form 전송시
	*/	
	function checkForm(){
		
		var frm = document.frm;
		var check_flag = true;
		
		if($("#excute_rate_yn").val() == ''){
		 	alert("<spring:message code='las.page.msg.lawservice.firstexrate' />");	
		 	return false;
		}
		
		if($("#unpayday").val() != ''){
			if($("#unpayday").val() != $("#c_dt_in").val()){
				alert("<spring:message code='las.page.msg.lawservice.nomatchunpayrate' />");	
			 	return false;
			}
		}
		
		return check_flag;
	}
	
	/**
	* 저장시 날짜 form 에서 "-" 를 삭제 
	*/
	function check_date(){
		$('*').attr('class',function(index){
			if($(this).attr("class") == 'text_calendar02'){
				$(this).val($(this).val().replace(/-/gi,''));					
			}
		}); 
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
	
	/**
	*  사건구분2 reload
	*/
	function StReload_partin(index){
    	$(partin2).html("<select name='event_gbn2' id='event_gbn2' required alt='<spring:message code='las.page.field.lawservice.event2'/>' ></select>");
		getCodeSelectByUpCd2("frm", "event_gbn2", "/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_grp_cd="+ index +"&combo_abbr=F&combo_type=S2&combo_use_yn=Y&combo_selected="+"");
	}
	
	/**
	*  변호사 comboListBox 추가
	*/
 	function addLwrCombo(){		
		
		var mNum = $("#lwr_append li").length;
		
		mNum = mNum + 1;
		mNumID = 'LI_' + mNum;
		
		var html = "";
		html += "<li id=\"" + mNumID + "\">";
		html += "<select id='lwr_no' name='lwr_no' style='width:250px;' onChange='lwr_onchange(this,this.value)'>";
		html += "<option value=''>"+"<spring:message code='las.page.button.lawservice.select' />"+"</option>	";
		<c:forEach var="list" items="${lwr_list_all}">
			html += "<option value=" + "<c:out value='${list.lwr_no}'/>" + ">"+"<c:out value='${list.lwr_nm}'/>"+"</option>";			
		</c:forEach>
		html += "</select>";
		html += " <span class='btn_option'><span class='delete'></span><a href='#' onclick='delLwrCombo(\"" + mNumID + "\")' >"+"<spring:message code='las.page.button.lawservice.del' />"+"</a></span>";
		html += "</li>";

		$('#lwr_append').append(html);	
	} 

	/**
	*  변호사 comboListBox 삭제
	*/
 	function delLwrCombo(mNumID){
		$('#'+mNumID).remove();
	}
	
	/**
	* 변호사 comboListBox 중복선택 체크
	*/
	function lwr_onchange(obj,this_val){
		var val_cnt = 0;
		$('select option:selected').each(function(index) {
			if(($(this).parent().attr("id") == 'lwr_no') && ($(this).val() == this_val)){
				val_cnt = val_cnt + 1;
			}				
	    });
		if(val_cnt >= 2){
			alert("<spring:message code='las.page.msg.lawservice.nodupllwr' />");
			obj.selectedIndex = 0;
		}		
	}
	
 	/**
	* INVOICE 기등록 여부 확인 정보를  Ajax로 호출
	*/
	function check_invoice(invoice_no) {
 		
 		if($.trim($("#invoice_no").val()) == ''){
 			alert("<spring:message code='las.page.msg.lawservice.invoiceno' />");
 			$("#invoice_no").val("");
 			return;
 		}
 		
 		if(!checkMaxLength($("#invoice_no").val(),"24")){
 			alert("<spring:message code='las.page.msg.lawservice.maxinvoicenum' />");
 			$("#invoice_no").val("");
 			return;
 		}
 		
		var options = {
				url: "<c:url value='/las/lawservice/eventAcceptSrvCost.do?method=checkInvoiceNoAjax' />",
				type: "post",
				dataType: "json",
				success: function(responseText, statusText) {
					if(responseText.returnCnt != 0) {
						$.each(responseText, function(entryIndex, entry) {
							if(entry['invoice_no'] != '0') {
								alert("<spring:message code='las.page.msg.lawservice.wronginvoice' />");
							} else {
								alert("<spring:message code='las.page.msg.lawservice.acptinvoice' />");
							}
						});
				}
			}
		}
		
		$("#frm").ajaxSubmit(options);	
	}
 	
 	/**
	* 환율적용날짜의 환율정보를  Ajax로 호출
	*/
	function check_rate() {
 		
 		var check_cnt = 0;
 		var check_rate_cnt = 0;
 		var usrate = 0.00;
 		var exrate = 0.00;
 		
 		var exp_pre_amount = 0.00; // (totamt * exrate); // 원화예상송금금액
 		var exp_usd_amount = 0.00; // exp_pre_amount / usrate; //USD환산
 		var totamt = 0.00; 
		
 		if($.trim($("#crrncy_unit").val()) == ''){
 			// 화폐단위를 입력해 주십시오.
 			alert("<spring:message code='las.page.msg.lawservice.inputcunit' />");
 			$("#crrncy_unit").attr("selectedIndex", 0);
 			return;
 		}
 		/*
 		if($.trim($("#c_dt_in").val()) == ''){
 			//환율적용 날자를 입력해 주십시오.
 			alert("<spring:message code='las.page.msg.lawservice.inputcdt' />");
 			$("#crrncy_unit").attr("selectedIndex", 0);
  			return;
 		} 
 		*/
		$('#src_tb tr input').each(function(index) {			
			if($(this).attr('id')=='totamt'){
				if($(this).val() != '')
					check_cnt =  check_cnt + 1;	
			}			
		}); 
		
		if(check_cnt < 1){
			// 멀티부서 일경우 최소 1개 부서는 입력을 해야 한다.
			alert("<spring:message code='las.page.field.lawservice.inputReqAmt'/>");
			$("#crrncy_unit").attr("selectedIndex", 0);
	  		return;	
		} else {
		    // 최소 1개 부서가 입력 되어 있을 경우 비어 있는 금액란을 0.00 으로 입력처리한다.
		   	$('#src_tb tr input').each(function(index) {			
				if($(this).attr('id')=='totamt'){
					if($(this).val() == '')
						$(this).val('0.00'); 
				}			
			});
		}
		
		var check_size_cnt = 0;
		
		$('#src_tb tr input').each(function(index) {			
			if($(this).attr('id')=='srvc_amt' || $(this).attr('id')=='addtnl_amt' || $(this).attr('id')=='totamt' || $(this).attr('id')=='dc_rate'){
		 		if(!checkMaxLength($(this).val(),"19")){
		 			check_size_cnt = check_size_cnt + 1;
		 		}
			}			
		}); 
		
		if(check_size_cnt > 0){
			// 청구금액은 최대 17자리 이상 입력 할수 없습니다.
			alert("<spring:message code='las.page.msg.lawservice.maxclaimamt' />");
	  		return;	
		}
		
		$("#c_dt").val($("#c_dt_in").val().replace(/-/gi,''));
 		 		
			var options = {
					url: "<c:url value='/las/lawservice/eventAcceptSrvCost.do?method=checkRateAjax' />",
					type: "post",
					dataType: "json",
					success: function(responseText, statusText) {
						if(responseText.returnCnt != 0) {
							$.each(responseText, function(entryIndex, entry) {
								usrate = entry['usrate'];
								exrate = entry['exrate'];
								check_rate_cnt = entry['check_rate_cnt'];
							});
							
							if(check_rate_cnt == 0){
								// 해당 날짜의 환율 정보가 없습니다. 시스템 담당자에게 문의 바랍니다.
								alert("<spring:message code='las.page.msg.lawservice.norateinfoalert' />");
								$("#crrncy_unit").attr("selectedIndex", 0);
								return;
							} else {
 								$('#src_tb tr input').each(function(index) {
	 						 		var exp_pre_amount = 0.00; // (totamt * exrate); // 원화예상송금금액
							 		var exp_usd_amount = 0.00; // exp_pre_amount / usrate; //USD환산
							 		var totamt = 0.00; 
									
							 		if($(this).attr('id')=='totamt'){
							 			
							 			var crrncyUnit = $.trim($("#crrncy_unit").val());
							 			
							 			totamt = $(this).val();
							 			
							 			//2012-09-04 엔화일 경우 100을 나눠줘야 원화가 된다.
							 			if(crrncyUnit == 'JPY'){
							 				exp_pre_amount = totamt * exrate / 100;
							 			}else if(crrncyUnit == 'KRW'){
							 				exp_pre_amount = totamt;
							 			}else{
							 				exp_pre_amount = totamt * exrate;
							 			}
								 		
								 		exp_usd_amount = exp_pre_amount / usrate;
							 		
								 		if(crrncyUnit == 'KRW'){
								 			$(this).parent().parent().next().children().children().html(exp_pre_amount);
								 		}else{
								 			$(this).parent().parent().next().children().children().html(exp_pre_amount.toFixed(2));	
								 		}
								 		$(this).parent().parent().next().children().next().children().html(exp_usd_amount.toFixed(2));
							 		}
								});  
 								
 								$('#excute_rate_yn').val('Y');
							}
							
					} else {
						// 해당 날짜의 환율 정보가 없습니다. 시스템 담당자에게 문의 바랍니다.
						alert("<spring:message code='las.page.msg.lawservice.norateinfoalert' />");
						$("#crrncy_unit").attr("selectedIndex", 0);
					}
				}
			}
			
			$("#frm").ajaxSubmit(options);	
		}

	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		
		var frm = document.frm;

		//저장
		if(flag == "insert"){
		    
		    var confirmMessage = frm.acpt_no.value==("") ? "<spring:message code='secfw.msg.ask.insert' />" : "<spring:message code='secfw.msg.ask.update' />" ;
		    
		    //유효성 체크
		    if(validateForm(frm)){
		    	
		    	var confirmMessage = frm.acpt_no.value==("") ? "<spring:message code='secfw.msg.ask.insert' />" : "<spring:message code='secfw.msg.ask.update' />" ;
		    	
		    	fileList.UploadFile(function(uploadCount){

					//지정된 형식 이외에 파일을 추가한 경우
					if (uploadCount < 0) {
						return;
					}                  

                    if(checkForm() && confirm(confirmMessage)){
    		    		
                    	viewHiddenProgress(true) ;
                    	check_date();
    		    		frm.method.value = frm.acpt_no.value==("") ? "insertEventAcceptSrvCost" : "modifyEventAcceptSrvCost";
    					frm.target = "_self";
    					frm.action = "<c:url value='/las/lawservice/eventAcceptSrvCost.do' />";
    			    	frm.submit();	

                    } else {
    		    		return;	
    		    	}
                });
		    } 		    
			
		//취소
		} else if(flag == "cancel"){
			
			viewHiddenProgress(true);
			frm.method.value = frm.acpt_no.value==("") ? "detailEventManage" : "detailEventAcceptSrvCost";
			frm.action = frm.acpt_no.value==("") ? "<c:url value='/las/lawservice/eventManage.do' />" : "<c:url value='/las/lawservice/eventAcceptSrvCost.do' />";
			frm.target = "_self";
			frm.submit();			
		} 
	}
	
	/**
	* 변호사 상세내역 조회
	*/
	function detailLawyerView(lwr_no){
		var frm = document.frm;
		viewHiddenProgress(true) ;
		frm.lwr_no.value = lwr_no;
	    frm.target = "_self";
		frm.action = "<c:url value='/las/lawservice/lawyerManage.do' />";
		frm.method.value = "detailLawyerManage";
		frm.submit();		
	}
	
	/**
	* 사건수정 바로가기
	*/
	function go_event_modify(){
		var frm = document.frm;
		viewHiddenProgress(true) ;
	    frm.target = "_self";
		frm.action = "<c:url value='/las/lawservice/eventManage.do' />";
		frm.method.value = "forwardEventManageModify";
		frm.submit();		
	}
	
	/**
	* 청구금액 미 입력시 '0' 으로  처리하기
	*/
	function set_money_field_zero(){
		var frm = document.frm;
		viewHiddenProgress(true) ;
	    frm.target = "_self";
		frm.action = "<c:url value='/las/lawservice/eventManage.do' />";
		frm.method.value = "forwardEventManageModify";
		frm.submit();		
	}
	
	
	//미지급일 초기화 기능
	function clearUnpayday(){
		document.getElementById('unpayday').value = '';
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

<form:form name="frm" id="frm" method="post" autocomplete="off">
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />
<input type="hidden" name="curPage"  value="<c:out value='${command.curPage}'/>" />
<input type="hidden" name="forward_from"  value="<c:out value='${command.forward_from}'/>" />
<input type="hidden" id="forward_url" name="forward_url"   value="" />

<c:choose>
<c:when test="${command.acpt_no == ''}">
	<input type="hidden" id="excute_rate_yn" name="excute_rate_yn"   value="" />
	</c:when>
	<c:otherwise>
	<input type="hidden" id="excute_rate_yn" name="excute_rate_yn"   value="Y" />
	</c:otherwise>
</c:choose>

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


<input type="hidden" name="srch_unpay_yn"   value="<c:out value='${command.srch_unpay_yn}'/>" />
<input type="hidden" name="srch_remit_yn"   value="<c:out value='${command.srch_remit_yn}'/>" />
<input type="hidden" name="srch_lawsuit_trgt_cd"   value="<c:out value='${command.srch_lawsuit_trgt_cd}'/>" />
<input type="hidden" name="srch_review_yn"   value="<c:out value='${command.srch_review_yn}'/>" />
<input type="hidden" name="srch_group_cd"   value="<c:out value='${command.srch_group_cd}'/>" />
<input type="hidden" name="srch_acpt_start_dt"   value="<c:out value='${command.srch_acpt_start_dt}'/>" />
<input type="hidden" name="srch_acpt_end_dt"   value="<c:out value='${command.srch_acpt_end_dt}'/>" />
<input type="hidden" name="srch_unpay_start_dt"   value="<c:out value='${command.srch_unpay_start_dt}'/>" />
<input type="hidden" name="srch_unpay_end_dt"   value="<c:out value='${command.srch_unpay_end_dt}'/>" />
<input type="hidden" name="srch_remit_start_dt"   value="<c:out value='${command.srch_remit_start_dt}'/>" />
<input type="hidden" name="srch_remit_end_dt"   value="<c:out value='${command.srch_remit_end_dt}'/>" />

<!-- key form-->

<input type="hidden" name="event_no" id="event_no" value="<c:out value='${command.event_no}'/>" alt="사건" required />
<input type="hidden" id="acpt_no" name="acpt_no"   value="<c:out value='${command.acpt_no}'/>" />

<!-- URL 이동시 사용 -->
<input type="hidden" name="targetMenuId">
<input type="hidden" name="selected_menu_id">
<input type="hidden" name="selected_menu_detail_id">
<input type="hidden" name="set_init_url">

<!-- 첨부파일정보 시작 -->
<input type="hidden" name="fileInfos"   value="" />
<input type="hidden" name="file_bigclsfcn"  value="F005" />
<input type="hidden" name="file_midclsfcn" 	value="F00504" />
<input type="hidden" name="file_smlclsfcn"  value="" />
<input type="hidden" name="ref_key"     value="<c:out value='${command.acpt_no}'/>" />
<c:choose>
<c:when test="${command.acpt_no == ''}">
	<input type="hidden" name="view_gbn"    value="upload" />
	</c:when>
	<c:otherwise>
	<input type="hidden" name="view_gbn" 	value="modify" />
	</c:otherwise>
</c:choose>

<div id="wrap">	
	<!-- container -->
<div id="container">
	<!-- Location -->
	<div class="location"><IMG SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home"><c:out value='${menuNavi}' escapeXml="false"/></div>
	<!-- //Location -->	
	<!-- title -->
	<div class="title">
		<c:choose>
			<c:when test="${command.acpt_no == ''}">
				<h3><spring:message code="las.page.field.lawservice.srvcost"/>&nbsp;<spring:message code="las.page.field.lawservice.register" /></h3>
			</c:when>
		<c:otherwise>
			<h3><spring:message code="las.page.field.lawservice.srvcost"/>&nbsp;<spring:message code="las.page.field.lawservice.modify" /></h3>
				</c:otherwise>
		</c:choose>		
	</div>
	<!-- //title -->
	<!-- content -->
	<div id="content">
		<!-- view -->
		<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
			<colgroup>
				  <col width="12%" />
				  <col width="50%" />
				  <col width="12%" />
				  <col width="26%" />
			</colgroup>
			<tbody>
				<tr>
					<th><spring:message code="las.page.field.lawservice.eventnm" /></th>
					<td colspan="3">
					<c:out value='${event_lom.event_nm}'/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.kbn" /></th>
					<td>
						<select id="event_gbn1" name="event_gbn1" style="width:154px;" disabled >
						</select>
						<select id="event_gbn2" name="event_gbn2" style="width:92px;" disabled >
						</select>
					</td>
					<th><spring:message code="las.page.field.lawservice.acptday" /></th>
					<td>
						<c:choose>
							<c:when test="${command.acpt_no == ''}">
								<input alt="<spring:message code="las.page.field.lawservice.acptday" />" required type="text" name="acptday" id="acptday" value="<c:out value='${command.acptday}'/>"  class="text_calendar02" readonly />
							</c:when>
							<c:otherwise>
								<input alt="<spring:message code="las.page.field.lawservice.acptday" />" required type="text" name="acptday" id="acptday" value="<c:out value='${lom.acptday}'/>"  class="text_calendar02" readonly />
							</c:otherwise>
						</c:choose>						
					</td>
				</tr>
				<tr>	
					<th><spring:message code="las.page.field.lawservice.lawsuittrgt" /></th>
					<td>
						<c:out value='${event_lom.lawsuit_trgt_nm}'/>
					</td>
					<th><spring:message code="las.page.field.lawservice.incharge" /></th>
					<td>
						<c:out value='${command.reg_nm}'/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.lawfirm" /></th>
					<td colspan="3">
						<select alt="<spring:message code="las.page.field.lawservice.lawfirm" />" required id='lawfirm_id' name='lawfirm_id' style='width:250px;'>
							<option value=''><spring:message code="las.page.field.lawservice.select" /></option>	
								<c:forEach var="list" items="${lawfirm_list}">
									<option value="<c:out value='${list.lawfirm_id}'/>" <c:if test='${list.lawfirm_id == lom.lawfirm_id}'> selected </c:if>><c:out value='${list.lawfirm_nm}'/></option>			
								</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.lwr" /></th>
					<td colspan="3">
						
						<c:forEach var="list" items="${lwr_list_event}"  varStatus="status">
							<a href="javascript:detailLawyerView('<c:out value='${list.lwr_no}'/>')"><c:out value='${list.lwr_nm}' /></a>
							<c:if test="${status.count > 0}">
								&nbsp;&nbsp;&nbsp;
							</c:if>
							<c:if test="${status.count mod 5==0}">
								</br>
							</c:if>
						</c:forEach>

						
							<ul id="lwr_append">
								<li>
									<select id='lwr_no' name='lwr_no' style='width:250px;' onChange='lwr_onchange(this,this.value)'>
										<option value=''><spring:message code="las.page.field.lawservice.select" /></option>	
										<c:forEach var="list" items="${lwr_list_all}">
										<option value="<c:out value='${list.lwr_no}'/>" ><c:out value='${list.lwr_nm}'/></option>			
										</c:forEach>
									</select> <span class="btn_option"><span class="add"></span><a href="#" onclick='addLwrCombo();'><spring:message code="las.page.button.lawservice.add" /></a> </span>
								</li>
							</ul>			


					</td>
				</tr>
				<tr>
					<th>INVOICE NO</th>
					<td>
						<input alt="INVOICE NO" type="text"  style='width:247px;' name="invoice_no" id="invoice_no" value="<c:out value='${lom.invoice_no}'/>" required onKeyDown="javascript:if(event.keyCode==13){event.returnValue = false;check_invoice(this.value);}"  />
					</td>
					<th><spring:message code="las.page.field.lawservice.srvfromto"/></th>
					<td>
						<input type="text" name="srvcstartday" id="srvcstartday" value="<c:out value='${lom.srvcstartday}'/>" class="text_calendar02" />
						- 
						<input type="text" name="srvcendday" id="srvcendday" value="<c:out value='${lom.srvcendday}'/>" class="text_calendar02" />
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.claimday"/></th>
					<td colspan="3">
						<input alt="<spring:message code="las.page.field.lawservice.claimday"/>" required type="text" name="claimday" id="claimday" value="<c:out value='${lom.claimday}'/>" class="text_calendar02" />
					</td>
				</tr>
				<tr>
					<th ><spring:message code="las.page.field.lawservice.claimamt"/></th>
					<td colspan="3">
					
					<c:choose>
						<c:when test="${empty dept_list}">
							<spring:message code="las.page.msg.lawservice.nodept" /> <a href="javascript:go_event_modify()"><spring:message code="las.page.field.lawservice.deptin"/></a>
						</c:when>
						<c:otherwise>
							<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table-stylesub" id="src_tb">
			  					<colgroup>
							  <col width="18%" />
							  <col width="18%" />
							  <col width="18%" />
							  <col width="18%" />
							  <col width="20%" />
							</colgroup>
			  				<c:forEach var='list' items='${dept_list}' varStatus='status'>
			  					<input type="hidden" id="dept_nm" name="dept_nm" value="<c:out value='${list.dept_nm}'/>" ></input>	
								<input type="hidden" id="intnl_dept_cd" name="intnl_dept_cd" value="<c:out value='${list.intnl_dept_cd}'/>" ></input>	
								<input type="hidden" id="grp_dept_cd" name="grp_dept_cd" value="<c:out value='${list.grp_dept_cd}' />" ></input>		
							    <tr id="src_tbr">
							      <td id="fee" >fee.
							        <input type="text" id="srvc_amt" name="srvc_amt" value="<c:out value='${list.srvc_amt}'/>" width="100px" alt="fee." maxLength="19" <c:if test="${command.acpt_no != '' && lom.unpayday != ''}"> readonly </c:if>	/> </td>
							      <td id="dis" >Dis.
							        <input type="text" name="addtnl_amt" id="addtnl_amt" value="<c:out value='${list.addtnl_amt}'/>" width="100px" alt="dis" maxLength="19" <c:if test="${command.acpt_no != '' && lom.unpayday != ''}"> readonly </c:if> /></td>
							      <td id="sum" >SUM
							        <input type="text" name="totamt" id="totamt" value="<c:out value='${list.totamt}'/>" width="100px" alt="sum" maxLength="19" <c:if test="${command.acpt_no != '' && lom.unpayday != ''}"> readonly </c:if>/></td>
							      <td><spring:message code="las.page.field.lawservice.dcrate"/>				        
						          <input type="text" name="dc_rate" id="dc_rate"  value="<c:out value='${list.dc_rate}'/>" width="100px" alt="<spring:message code="las.page.field.lawservice.dcrate"/>" maxLength="19" <c:if test="${command.acpt_no != '' && lom.unpayday != ''}"> readonly </c:if>/></td>
							      <th rowspan="2"><c:out value='${list.dept_nm}'/></th>
						        </tr>
							    <tr id="src_tbr2" >
							      <td colspan="2"><spring:message code="las.page.field.lawservice.plndremitamt"/>: <span id="plnd_remit_amt" ><c:out value='${list.plnd_remit_amt}'/></span></td>
							      <td colspan="2"><spring:message code="las.page.field.lawservice.usdamt"/>: <span id="usd_amt" ><c:out value='${list.usd_amt}'/></span></td>
						        </tr>
						       	</c:forEach>						        
					        </table>						
						</c:otherwise>
					</c:choose>	
					
  					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.cunit"/></th>
					<td>
						<select alt="<spring:message code="las.page.field.lawservice.cunit"/>" required id="crrncy_unit" name="crrncy_unit" style='width:200px;' >
							<option value=''><spring:message code="las.page.field.lawservice.select" /></option>
							<option value='AUD' <c:if test="${lom.crrncy_unit == 'AUD'}"> selected </c:if>><spring:message code="las.page.field.lawservice.aud"/></option>
							<option value='CAD' <c:if test="${lom.crrncy_unit == 'CAD'}"> selected </c:if>><spring:message code="las.page.field.lawservice.cad"/></option>
							<option value='CHF' <c:if test="${lom.crrncy_unit == 'CHF'}"> selected </c:if>><spring:message code="las.page.field.lawservice.cchf"/></option>
							<option value='DEM' <c:if test="${lom.crrncy_unit == 'DEM'}"> selected </c:if>><spring:message code="las.page.field.lawservice.dem"/></option>
							<option value='EUR' <c:if test="${lom.crrncy_unit == 'EUR'}"> selected </c:if>><spring:message code="las.page.field.lawservice.eur"/></option>
							<option value='FRF' <c:if test="${lom.crrncy_unit == 'FRF'}"> selected </c:if>><spring:message code="las.page.field.lawservice.frf"/></option>
							<option value='GBP' <c:if test="${lom.crrncy_unit == 'GBP'}"> selected </c:if>><spring:message code="las.page.field.lawservice.gbp"/></option>
							<option value='ITL' <c:if test="${lom.crrncy_unit == 'ITL'}"> selected </c:if>><spring:message code="las.page.field.lawservice.itl"/></option>
							<option value='JPY' <c:if test="${lom.crrncy_unit == 'JPY'}"> selected </c:if>><spring:message code="las.page.field.lawservice.jpy"/></option>
							<option value='KRW' <c:if test="${lom.crrncy_unit == 'KRW'}"> selected </c:if>><spring:message code="las.page.field.lawservice.krw"/></option>
							<option value='USD' <c:if test="${lom.crrncy_unit == 'USD'}"> selected </c:if>><spring:message code="las.page.field.lawservice.cusd"/></option>
						</select> 
					</td>
					<th><spring:message code="las.page.field.lawservice.crateday"/></th>
					<td>
						
						<c:choose>
						<c:when test="${command.acpt_no == ''}">
							<input type="text" name="c_dt_in" id="c_dt_in" value="<c:out value='${str_cday}'/>" class="text_calendar02" readonly />
							<input type="hidden" name="c_dt" id="c_dt" value="" />
							</c:when>
							<c:otherwise>
									<fmt:parseDate value="${lom.shiftday}" var="dateFmt" pattern="yyyymmdd"/>
					            	
							<input type="text" name="c_dt_in" id="c_dt_in" value="<fmt:formatDate value="${dateFmt}" pattern="yyyy-mm-dd"/>" class="text_calendar02" readonly />
							<input type="hidden" name="c_dt" id="c_dt" value="<c:out value='${lom.shiftday}'/>" />
							</c:otherwise>
						</c:choose>
						<span class="btn_option"><span class="arrow"></span><a href="javascript:check_rate()"><spring:message code="las.page.field.lawservice.crate"/></a></span>
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.plndremitday"/></th>
					<td>
						<input type="text" name="remitplndday" id="remitplndday" value="<c:out value='${lom.remitplndday}'/>" class="text_calendar02" readonly />
					</td>
					<th><spring:message code="las.page.field.lawservice.remitday"/></th>
					<td>
						<input type="text" name="remitday" id="remitday" value="<c:out value='${lom.remitday}'/>" class="text_calendar02" readonly /> 
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.reviewcont"/></th>
					<td colspan="3">
						<select id="review_yn" name="review_yn" >
							<option value = "N" <c:if test="${lom.review_yn == 'N'}"> selected </c:if>>N</option>
							<option value = "Y" <c:if test="${lom.review_yn == 'Y'}"> selected </c:if>>Y</option>
						</select>
						&nbsp;<input type="text" name="review_cont" id="review_cont" size="70" value="<c:out value='${lom.review_cont}'/>" style="width:300px" alt="review" maxLength="200" />
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.unpayday"/></th>
					<td>
						<input alt="<spring:message code="las.page.field.lawservice.unpayday"/>" type="text" name="unpayday" id="unpayday" value="<c:out value='${lom.unpayday}'/>" class="text_calendar02" readonly /> 
						<img src='<%=IMAGE%>/icon/iconClear.gif' onClick="javascript:clearUnpayday();" alt="Clear"/>
						*<spring:message code="las.page.msg.lawservice.unpaylogo" />
						 
					</td>
					<th><spring:message code="las.page.field.lawservice.remitamt"/></th>
					<td>
						<input type="text" name="remit_amt" id="remit_amt" value="<c:out value='${lom.remit_amt}'/>" num="" class="tR" alt="<spring:message code="las.page.field.lawservice.remitamt"/>" maxLength="19" />
					</td>
				</tr>
				<tr>
					<th><spring:message code="las.page.field.lawservice.claimcont"/></th>
					<td colspan="3">
						<textarea alt="<spring:message code="las.page.field.lawservice.claimcont"/>" id="claim_cont" name="claim_cont"  maxlength="500" cols="as" rows="3" class="text_area_full" maxLength="1000" ><c:out value='${lom.claim_cont}'/></textarea>
					</td>
				</tr>
				<tr class="end">
					<th><spring:message code="las.page.field.lawservice.attach" /></th>
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
			<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
			<!-- // footer -->
			<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
</div>
<!-- //container -->
</div>
</form:form>
</body>
</html>