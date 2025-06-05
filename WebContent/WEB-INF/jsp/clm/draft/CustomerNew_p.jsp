<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%--
/**
 * 파  일  명 : CustomerNew_p.jsp
 * 프로그램명 : 계약상대방 신규등록 팝업
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2013.04.16
 */
--%>

<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="clm.main.title" /></title>
<link href="<%=CSS%>/clm.css"    type="text/css" rel="stylesheet" />
<link href="<%=CSS%>/popup.css"  type="text/css" rel="stylesheet" />

<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript">

	$(document).ready(function(){
		/* getCodeSelectByUpCd("frm", "busopen", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=T&combo_grp_cd=C051&combo_type=T&combo_del_yn=N&combo_selected='); */
		/* getCodeSelectByUpCd("frm", "legst", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=T&combo_grp_cd=C050&combo_type=T&combo_del_yn=N&combo_selected='); */
		/* getCodeSelectByUpCd("frm", "business_gubn", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=T&combo_grp_cd=C052&combo_type=T&combo_del_yn=N&combo_selected='); */
		
		/* getCodeSelectByUpCd("frm", "busopen1", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=T&combo_grp_cd=C051&combo_type=T&combo_del_yn=N&combo_selected='); */
		/* getCodeSelectByUpCd("frm", "legst1", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=T&combo_grp_cd=C050&combo_type=T&combo_del_yn=N&combo_selected='); */
		/* getCodeSelectByUpCd("frm", "business_gubn1", '/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=T&combo_grp_cd=C052&combo_type=T&combo_del_yn=N&combo_selected='); */
		//Vendor Type code read
		/* getCodeSelectByUpCd("frm", "vendor_type",'/common/clmsCom.do?method=listComCdByGrpCd&combo_sys_cd=LAS&combo_abbr=T&combo_grp_cd=C072&combo_type=T&combo_del_yn=N&combo_selected='); */
	});

	/**
	* 버튼 동작 부분
	*/
	function pageAction(flag){
		var frm = document.frm;
		var nation = "";
		frm.target = "PopUpWindow";
		frm.action = "<c:url value='/clm/draft/customerNew.do' />";
		
		//국내인지 해외인지 구분하는 radio box
		for(var i = 0 ; i < frm.nationRd.length ; i++) {
			if(frm.nationRd[i].checked == true){
				nation = frm.nationRd[i].value; 	
			}
		}
		
		if(flag == "insert"){//등록 버튼 클릭 시
			
			if(confirm("<spring:message code='secfw.msg.ask.insert' />")){
				
				if(nation == "I" ){   //개인일 경우 체크 하기
					
					if(frm.customer_nm1_H.value == ""){   //업체명 null 체크
						alert("<spring:message code="clm.page.msg.draft.announce016" />");
					    frm.customer_nm1.focus();
					    return;
					}
				
					frm.rep_nm.value = frm.customer_nm1.value;	
					
					if(frm.nationNm_H.value == "" ){   //국가 null 체크
						alert("<spring:message code="clm.page.msg.draft.announce004" />");
					    frm.nationNm.focus();
					    return;
					}
				}
				
				if(nation == "G"){
					if(frm.customer_nm1.value == ""){   //업체명 null 체크
						alert("<spring:message code='clm.msg.alert.customer.customerNm1NullChk'/>");
					    frm.customer_nm1.focus();
					    return;
					}

					if(frm.nationNm.value == "" ){   //국가 null 체크
						alert("<spring:message code="clm.page.msg.draft.announce004" />");
					    frm.nationNm.focus();
					    return;
					}
					
					if(frm.street.value == "" ){   // 주소 null 체크
						alert("<spring:message code="clm.page.msg.draft.announce020" />");
					    frm.street.focus();
					    return;
					}
			    }
				
				frm.method.value = "insertCustomer";
				frm.target = "_self";
				
				frm.submit();
				
				if(nation == 'I'){
					var returnV = frm.customer_nm1_H.value;	
				} else {
					var returnV = frm.customer_nm1.value;
				}
				
				opener.setInfo(returnV);
				
			}
			
		}else if(flag == "cancel"){//취소 버튼 클릭 시
			if(confirm("<spring:message code='secfw.msg.ask.cancel' />")){
				window.close();
			}
		}
	}
	
	//국가 검색 팝업 호출
	function nationPop(){
		var frm = document.frm;
		var nation = "";
		
		//국내인지 해외인지 구분하는 radio box
		for(var i = 0 ; i < frm.nationRd.length ; i++) {
			if(frm.nationRd[i].checked == true){
				nation = frm.nationRd[i].value; 	
			}
		}
		if(nation == "G" || nation == "I"){
			PopUpWindowOpen('', 890, 500, true, 'PopUpWindow3');
		    frm.target = "PopUpWindow3";
			
			frm.action = "<c:url value='/clm/draft/nation.do' />";
			frm.method.value = "listNation";
			frm.submit();
		}
	}
	
	//팝업오픈
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
	
	//국가 검색 리턴
	function setNation(obj){
		var frm = document.frm;
		frm.nation.value   = obj.nation;//국가코드
		frm.nationNm.value = obj.nationNm;//국가명
		frm.nationNm_H.value = obj.nationNm;//국가명
		
		document.getElementById("nationNm").value = obj.nationNm;
		
		//국내인지 해외인지 구분하는 radio box
		for(var i = 0 ; i < frm.nationRd.length ; i++) {
			if(frm.nationRd[i].checked == true){
				nation = frm.nationRd[i].value; 	
			}
		}
	}
	
	// 사업자 번호 검색
	function checkTaxNo(){
		
		var frm = document.frm;
		
		//국내인지 해외인지 구분하는 radio box
		for(var i = 0 ; i < frm.nationRd.length ; i++) {
			if(frm.nationRd[i].checked == true){
				nation = frm.nationRd[i].value; 	
			}
		}
		
		if(nation =="K"){
			/*******
			if(frm.tax_no.value == ""){    //사업자등록번호
				
				alert("<spring:message code="clm.page.msg.draft.announce005" />");
				frm.tax_no.focus();
			    return;
			}
			*/
			
			var options = {
					
					url : "<c:url value='/clm/draft/customerNew.do?method=jocheckTexNo' />",
					type : "post",
					async :  false,
					dataType : "json",
					success : function(responseText, statusText){
						
						if(responseText.check_YN == "P" && responseText.check_texNo == ""){
							
							alert("<spring:message code="clm.page.msg.draft.announce008" />");
							
							frm.check_YN.value = "P";

							return;
							
						} else if(responseText.check_texNo != "" && responseText.check_YN == "P") {
							
							
							if(responseText.check_texNm != ""){
								
								alert(responseText.check_texNm + " <spring:message code='clm.page.msg.draft.exist' />");	
								frm.check_YN.value = "";
								return;
								
							} else {
								
								alert("<spring:message code="clm.page.msg.draft.announce009" />");
								frm.check_YN.value = "";
								return;
								
							}
							
						}
						
					}
					
			};
			
			$("#frm").ajaxSubmit(options);
			
		}
		
		if(nation == "H"){
			
			alert("<spring:message code="clm.page.msg.draft.unness" />");
			
		} else if(nation == "G") {
			
			alert("<spring:message code="clm.page.msg.draft.announce025" />");
			
		}
		
		//checkMa();
		
	}
	
	function checkMa(){
		
		var frm = document.frm;
		
		if(frm.check_YN.value == "P" && frm.check_texNo.value == ""){
			
			alert("<spring:message code="clm.page.msg.draft.announce008" />");

			return;
			
		} else if(frm.check_texNo.value != "" && frm.check_YN.value == "P") {
			
			if(frm.check_texNm.value != ""){
				alert(frm.check_texNm.value+" <spring:message code='clm.page.msg.draft.exist' />");	
				frm.check_YN.value = "";
				return;
			} else {
				alert("<spring:message code="clm.page.msg.draft.announce009" />");
				frm.check_YN.value = "";
				return;
			}
			
		}
	}
	
	// 사업자에 숫자만 입력하게 합니다. 숫자값이 아니면 그냥 빈칸으로 만들어 버리죠.
	function fOnlyNum(obj){
		
		var str = obj.value;
		str = new String(str);
		var Re = /[^0-9]/g;
		str = str.replace(Re,'');
		
		obj.value = str;
		
	}
	
	function setOpen(){
		var frm = document.frm;
		
		
		var returnR = frm.returnWord.value;
		
		if(returnR == '1'){
			self.close();
		} else if(returnR == '2'){
			alert("<spring:message code="clm.page.msg.draft.announce012" />");
		}
		
	}
	
	function nationRdChng(){
		
        var frm = document.frm;
		
		//국내인지 해외인지 구분하는 radio box
		for(var i = 0 ; i < frm.nationRd.length ; i++) {
			if(frm.nationRd[i].checked == true){
				nation = frm.nationRd[i].value; 	
			}
		}

		if(nation =="G"){
			
			frm.nation.value   = "";//국가코드
			frm.nationNm.value = "";//국가명
			frm.stapr.value    = "";//주
			/* frm.cityn.value    = ""; *///도시
			frm.tax_no.value   = "";//사업자등록번호
			frm.email.value    = ""; //email
			
			frm.nationNm.disabled = false;//국가명
			frm.stapr.disabled    = false;//주
			/* frm.cityn.disabled    = false; *///도시
			
			document.getElementById("nationNm").value = "";
			
			// 해외만 보여주기
			document.getElementById("full_KR").style.display = "block";
			document.getElementById("full_KR_H").style.display = "none";
			
		}else if(nation =="I") {  // 개인인 경우(해외)
			
			frm.nation.value   = "";//국가코드
			frm.nationNm.value = "";//국가명
			
			document.getElementById("nationNm_H").value = "";
			
			// 해외(개인)만 보여주기
			document.getElementById("full_KR").style.display = "none";
			document.getElementById("full_KR_H").style.display = "block";
			
		}
	}

</script>
</head>
<body class="pop" onload="nationRdChng(); setOpen();">

<!-- **************************** Form Setting **************************** -->
<form:form name="frm" id="frm" method="post" autocomplete="off">

<!-- search form -->
<input type="hidden" name="method"   value="" />
<input type="hidden" name="menu_id"  value="<c:out value='${command.menu_id}'/>" />

<input type="hidden" name="nation"  value="<c:out value='${command.nation}'/>" />
<!-- key form-->
<!-- 첨부파일정보 -->
<input type="hidden" name="check_texNo"  value="<c:out value='${check.check_texNo}'/>" />
<input type="hidden" name="check_texNm"  value="<c:out value='${check.check_texNm}'/>" />
<input type="hidden" name="check_YN"     value="<c:out value='${check.check_YN}'/>" />

<input type="hidden" name="returnWord" value="<c:out value='${command.return_message}'/>" />
<!-- // **************************** Form Setting **************************** -->
<!-- header -->
<h1><spring:message code="clm.page.title.customer.titleNew" /><span class="close" onclick="javascript:self.close();" title="close"></span>
  <!-- //header -->
<!-- body -->
</h1>
<div class="pop_area">
	<!-- Popup Detail -->
	<div>
	  <div class="pop_content">
		<!-- title -->
		<div class="title_sub">
			<h5><spring:message code="clm.page.title.customer.titleNew" /></h5>
		</div>
		<!-- //title -->
		
		<!-- search-->
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
									<col width="100%"/>
								</colgroup>
								<tr>
									<td style="padding-left:5px"> 
									    <!-- <input type="radio" name="nationRd" id="nationRd" size="150" value="K" onclick="javascript:nationRdChng();" checked /><spring:message code='clm.page.field.customer.nationK' /> -->
				           				<input type="radio" name="nationRd" id="nationRd" size="150" value="G" onclick="javascript:nationRdChng();" checked /> <spring:message code='clm.page.field.contract.basic.customer' />
				           		<span style="padding:10px;"></span>
				           				<!-- <input type="radio" name="nationRd" id="nationRd" size="150" value="H" onclick="javascript:nationRdChng();" /><spring:message code="clm.page.msg.draft.indDom" /> -->
				           				<input type="radio" name="nationRd" id="nationRd" size="150" value="I" onclick="javascript:nationRdChng();" /> <spring:message code="clm.page.msg.draft.indOvrs2" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<!--//search-->
		
		<!-- view 국내  -->
		<div id="full_KR" style="display: none;">
		<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
			<colgroup>
				<col class="tal_w03" style="width:160px;" /><col />
				<col class="tal_w04" style="width:140px;" /><col />
				<col class="tal_w03" style="width:100px;" /><col />
			</colgroup>
			<tbody>
				<tr><!-- 업체명, 사업분야 -->
					<th id="customer_nm"><spring:message code="clm.page.field.customer.customerNm1"/><span class='astro'>*</span></th>
					<td><input class='text' type="text" id="customer_nm1" name="customer_nm1" style="width:100%" value="<c:out value='${check.customer_nm1}'/>" maxlength="200" /></td>
					<th><spring:message code="clm.page.field.customer.repNm"/></th>
					<td><input type="text" id="rep_nm" name="rep_nm" style="width:100%" class='text' value="<c:out value='${check.rep_nm}'/>" maxlength="105" /></td>
					<th><spring:message code="clm.page.field.customer.telephone1"/></th>
				    <td><input class='text' type="text" id="telephone1" name="telephone1" style="width:100%;" value="<c:out value='${check.telephone1}'/>" maxlength="100" onKeypress = "if(event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;"/></td>
				</tr>
				<tr><!-- 대표자, 사업자등록번호, Email -->
					<th><spring:message code="clm.page.field.customer.gerpCode"/></th>
					<td><input class='text' type="text" id="gerp_cd" name="gerp_cd" style="width: 100%" value="" maxlength="50" /></td>
					<th><spring:message code="clm.page.field.customer.vendorType" /></th>
					<td id="vendorTypeInp">
					    <select name="vendor_type" id="vendor_type" style="width:80%">
					    	<option value="">Not Decided</option>
							<option value="V">Vendor</option>
							<option value="C">Customer</option>
					    </select>
					</td>		
					<!-- 2014-03-18 Kevin. 필수 계약 counterparty 설정 추가 -->
					<th><spring:message code="clm.page.field.customer.IsContractRequired" /></th>
					<td>
							<select name="contract_required" id="contract_required" style="width:140px">
								<option value="">Not Decided</option>
								<option value="Y">Yes</option>
								<option value="N">No</option>		
							</select>
					</td>
				</tr>
				<tr>
					<th><spring:message code="clm.page.field.customer.registerNo" /></th>
					<td><input class='text' type="text" id="tax_no" style="width:100%" name="tax_no"  value="" maxLength="50" /></td>
                    <th><spring:message code="clm.page.field.customer.vatno"/></th>
					<td><input class='text' type="text" id="vatno" name="vatno" style="width:100%;" value="<c:out value='${check.vatno}'/>" maxlength="100" /></td>
					<th><spring:message code="clm.page.field.customer.Email"/></th>
					<td><input class='text' type="text" id="email" name="email" style="width:100%;" value="<c:out value='${check.email}'/>" maxlength="100" /></td>	
				</tr>
				<tr><!-- 국가, 주, 도시 -->
					<th><spring:message code="clm.page.field.customer.nation"/><span class='astro'>*</span></th>
					<td>
						<input type="text" readonly="true" name="nationNm" id="nationNm" value="" style="width:80%" class="text_search" /><img src="<%=IMAGE%>/icon/ico_search.gif" onclick="javascript:nationPop();" class="cp" alt="search"/>
					</td>
					<th><spring:message code="clm.page.field.customer.stapr"/></th>
					<td><input class='text' type="text" id="stapr" name="stapr" style="width:100%" value="<c:out value='${check.stapr}'/>" maxlength="50"/></td>
					<th><spring:message code="clm.page.field.customer.postalcode"/></th>
				    <td><input class='text' type="text" id="postalcode" name="postalcode" style="width:100%" value="<c:out value='${check.postalcode}'/>" maxlength="100" /></td>
				</tr>
				<tr><!-- 주소 -->
					<th><spring:message code="clm.page.field.customer.street"/><span class='astro'>*</span></th>
					<td colspan="5"><input class='text' type="text" id="street" name="street" style="width:100%" value="<c:out value='${check.street}'/>" maxlength="100"/></td>
				</tr>
			</tbody>
		</table>
		</div>
		<!-- //view -->
		
		<!-- view -->
		<div id="full_KR_H"  style="display: none;">
		<table border="0" cellspacing="0" cellpadding="0" class="table-style01">
			<colgroup>
				<col class="tal_w04" /><col />
				<col class="tal_w04" /><col />
				<col class="tal_w04" /><col />
			</colgroup>
			<tbody>
				<tr><!-- 업체명, 사업분야 -->
					<th id="customer_nm_H"><spring:message code="clm.page.msg.common.name" /><span class='astro'>*</span></th>
					<td colspan="6"><input class='text' type="text" id="customer_nm1_H" name="customer_nm1_H" style="width:50%" value="<c:out value='${check.customer_nm1}'/>" maxlength="500" /></td>
				</tr>
				<tr><!-- 국가, 주, 도시 -->
					<th><spring:message code="clm.page.field.customer.nation"/><span class='astro'>*</span></th>
					<td colspan="6">
						<input type="text" readonly="true" name="nationNm_H" id="nationNm_H" value="" style="width:30%" class="text_search" /><img src="<%=IMAGE%>/icon/ico_search.gif"  onclick="javascript:nationPop();" class="cp" alt="search"/>
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		<!-- //view -->
		
		<!--footer -->
	    <div class="pop_footer">
	        <!-- button -->
	        <div class="btn_wrap fR">
	            <span class="btn_all_w"><span class="save"></span><a href="javascript:pageAction('insert')"><spring:message code='clm.page.button.insert' /></a></span>
	            <span class="btn_all_w"><span class="cancel"></span><a href="javascript:pageAction('cancel')"><spring:message code='clm.page.button.cancel' /></a></span>
	        </div>
             <!-- //button -->
         </div>
         <!-- //footer -->
         <jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	  </div>
	</div>
	<!-- //Popup Detail -->
</div>
<!-- //body -->
</form:form>
</body>
</html>