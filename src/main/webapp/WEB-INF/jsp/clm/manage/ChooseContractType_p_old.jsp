<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.manage.dto.ConsiderationForm"%>
<%@ page import="com.sds.secframework.common.util.DateUtil"%>
<%@ page import="com.sds.secframework.common.util.StringUtil"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<%--
/**
 * 파  일  명 : ConsiderationContractType_p.jsp
 * 프로그램명 : 계약유형선택팝업
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2013.03
 */
--%>

<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title>::: <spring:message code="clm.page.msg.common.clms" /> :::</title>
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<link href="<%=CSS%>/popup.css"   type="text/css" rel="stylesheet">
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet">

<script language="javascript" src="/script/clms/common.js" type="text/javascript"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.form.js"></script>
<script language="javascript" src="/script/secfw/jquery/jquery.flot.js"></script>
<script language="javascript" src="/script/clms/new_style.js" type="text/javascript"></script>
<script language="javascript">


$(document).ready(function(){
	showMessage("<c:out value='${returnMessage}'/>");
	setPurposeStatus();
	setInitValue();
	
	$("#type_sel21").click(function(){
		
		popUPTnC();
		
	});	
	
	
	/*window.onbeforeunload = function() {
		returnValue();
		}*/
});
	
	/**
	* 메시지 처리
	*/
	function showMessage(msg) {
		if( msg != "" && msg != null && msg.length > 1 ) {
			alert(msg);
		}
	}
	
	function setPurposeStatus() {
		var frm = document.frm;
		if(frm.purpose[0].checked){
			for(var i=0; i<frm.type3.length;i++) {
				frm.type3[i].disabled = false;
			}
		} else{
			for(var i=0; i<frm.type3.length;i++) {
				frm.type3[i].disabled = true;
				frm.type3[i].checked = false;
			}
		}
	}
	
	function setInitValue() {
		var obj = window.dialogArguments;
		var frm = document.frm;
		
		var parent_cnsd_level = opener.document.getElementById("tmp_cnsd_level").value ;
		var parent_prgrs_status = opener.document.getElementById("tmp_prgrs_status").value ; 
		
		var parent_biz_clsfcn = opener.document.getElementById("biz_clsfcn").value;
		var parent_depth_clsfcn = opener.document.getElementById("depth_clsfcn").value;
		var parent_cnclsnpurps_bigclsfcn = opener.document.getElementById("cnclsnpurps_bigclsfcn").value;
		var parent_cnclsnpurps_midclsfcn = opener.document.getElementById("cnclsnpurps_midclsfcn").value;
		var isMod = (parent_cnsd_level=="1" &&  parent_prgrs_status=="C04201") || (parent_cnsd_level=="" && parent_prgrs_status=="") ? true : false ;
		
		if(parent_biz_clsfcn != "") {
			for(var i=0; i<frm.type1.length;i++) {
				if(frm.type1[i].value==parent_biz_clsfcn) {
					frm.type1[i].checked = true;
					setDispValue(frm.type1[i], frm.type1[i].value + "_nm");
					break;
				}	
			}
			
			for(var i=0; i<frm.type2.length;i++) {
				if(frm.type2[i].value==parent_depth_clsfcn) {
					frm.type2[i].checked = true;
					setDispValue(frm.type2[i], frm.type2[i].value + "_nm");
					break;
				}	
			}
			
			if(parent_cnclsnpurps_bigclsfcn == "T0399") {
				frm.purpose[1].checked = true;
				setPurposeStatus();
				setNoneValue(frm.purpose[1]);
			} else {
				frm.purpose[0].checked = true;
				setPurposeStatus();
				setNoneValue(frm.purpose[0]);
				
				var mTypeCd = null ;
				
				for(var i=0; i<frm.type3.length;i++) {
					if(frm.type3[i].value == parent_cnclsnpurps_midclsfcn) {
						frm.type3[i].checked = true;
						setDispValue(frm.type3[i], frm.type3[i].value + "_nm");
						//break;
					}
					
					// 수정 불가능인 경우(1차 임시저장이 아닌경우)
					// 즉 개발/라이선스, 특허, 일반 내에서만 수정 가능
					if(!isMod){
						mTypeCd = frm.type3[i].value.substring(0,5) ;
						
						// 개발/라이선스인 경우
						if(parent_cnclsnpurps_bigclsfcn == "T0301" || parent_cnclsnpurps_bigclsfcn == "T0303") {
						    if(mTypeCd!= "T0301" && mTypeCd!="T0303") {
						    	frm.type3[i].disabled = true ;
						    } else {
                                frm.type3[i].disabled = false ;
                            }
						}
						// 특허 인 경우
						else if(parent_cnclsnpurps_bigclsfcn == "T0302") {
							
							if(mTypeCd!= "T0302") {
                                frm.type3[i].disabled = true ;
                            } else {
                                frm.type3[i].disabled = false ;
                            }
						} 
						// 일반인 경우 
						else {
							if(mTypeCd== "T0301" || mTypeCd=="T0302" || mTypeCd=="T0303") {
                                frm.type3[i].disabled = true ;
                            } else {
                            	frm.type3[i].disabled = false ;
                            }
						}
					}
				}	
			}
		}	
	}
	
	function setNoneValue(object) {
		var frm = document.frm;
		var objValue = object.value;
		
		if(objValue=="none") {
			frm.cls_bigcontpurpose_nm.value = "<spring:message code="clm.page.msg.manage.noComp" />";
			frm.cls_bigcontpurpose.value = "T0399";
			frm.cls_midcontpurpose_nm.value = "<spring:message code="clm.page.msg.manage.noComp" />";
			frm.cls_midcontpurpose.value = "T039999";
			spanpurpose.innerHTML = "<spring:message code="clm.page.msg.manage.noComp" />";
		} else {
			frm.cls_bigcontpurpose_nm.value = "";
			frm.cls_bigcontpurpose.value = "";
			frm.cls_midcontpurpose_nm.value = "";
			frm.cls_midcontpurpose.value = "";
			spanpurpose.innerHTML = "";
		}
	}
	
	// 유형을 선택하게 되면 각 유형의 정보와 함께 상단에 내용을 자동으로 입력하게 처리함.
	function setDispValue(object, textFieldNm) {
		
		
		var frm = document.frm;
		var rdoValue = object.value;
		var rdoText	 = document.getElementById(textFieldNm).value;
		var bigclsnm = "";
		
		if(rdoValue.substr(0,3)=="T01") {
			frm.cls_biz_nm.value = rdoText;
			frm.cls_biz.value = rdoValue;
			spanbiz.innerHTML = rdoText;
			
			if(rdoValue=="T0101"){ // 1. 비즈니스 단계 - NDA
				frm.purpose[1].disabled=false;
			} else if(rdoValue=="T0103"){ // 1. 비즈니스 단계 - 본계약
				frm.purpose[1].disabled=true;
				frm.purpose[1].checked=false;
				frm.purpose[0].checked=true;
			
		    } else {
				frm.purpose[1].disabled=true;
				frm.purpose[1].checked=false;
				
				// 유형을 선택해도 초기 값으로 되기 않게 처리 함. 김재원.!@#.20130502 
				//frm.cls_bigcontpurpose_nm.value = "";
				//frm.cls_bigcontpurpose.value = "";
				//frm.cls_midcontpurpose_nm.value = "";
				//frm.cls_midcontpurpose.value = "";
				//spanpurpose.innerHTML = "";

				//for(var i=0; i<frm.type3.length;i++) {
				//	frm.type3[i].checked = false;
				//}
			}
		} else if (rdoValue.substr(0,3)=="T02") {  // 2. 계약의 단계
			
		    frm.cls_contdepth_nm.value = rdoText;
			frm.cls_contdepth.value = rdoValue;
			spandepth.innerHTML = rdoText;
			
		} else {
			bigclsnm = rdoValue.substr(0,5);
			frm.cls_bigcontpurpose.value = eval("frm." + bigclsnm + "_bigtype.value");
			frm.cls_bigcontpurpose_nm.value = eval("frm." + bigclsnm + "_bigtype_nm.value");
			frm.cls_midcontpurpose_nm.value = rdoText;
			frm.cls_midcontpurpose.value = rdoValue;
			spanpurpose.innerHTML = rdoText;
	    }
	    		
		//t&c 추가 2014.05.20 // tnc 팝업의 경우 체결후 등록 창에서 호출 되었을때는 처리 하지 않는다.		
		<c:if test="${'regConsideration' ne command.forword_from }">
			if(rdoValue == "T030705" || rdoValue == "T030706"){		
				$('#type_sel21').show();				
				PopUpWindowOpen2("/clm/manage/chooseContractType.do?method=forwardTncOrEcms", 500, 170, false, "TncOrEcms_p");
			}
		</c:if>
		
	}	
	
	function checkValidation(){
		var frm = document.frm;
		
		if(frm.cls_biz.value=="") {
			alert("<spring:message code="clm.page.msg.manage.announce095" />");
			return false;
		}
		
		if(frm.cls_contdepth.value=="") {
			alert("<spring:message code="clm.page.msg.manage.announce053" />");
			return false;
		}
		
		if(frm.cls_midcontpurpose.value=="") {
			alert("<spring:message code="clm.page.msg.manage.announce172" />");
			return false;
		}
		
		return true;
	}
	
	
	function returnValue() {
		
		$('#type_sel21').hide();
		
		if(checkValidation()){
			
			var frm = document.frm;
			
			//var mod_status = opener.document.getElementById("mod_status").value ;
			
			var parent_biz_clsfcn = opener.document.getElementById("biz_clsfcn").value;
			var parent_depth_clsfcn = opener.document.getElementById("depth_clsfcn").value;
			var parent_cnclsnpurps_bigclsfcn = opener.document.getElementById("cnclsnpurps_bigclsfcn").value;
			var parent_cnclsnpurps_midclsfcn = opener.document.getElementById("cnclsnpurps_midclsfcn").value;
			
			
				
				opener.document.getElementById("biz_clsfcn").value 	                = frm.cls_biz.value;
				opener.document.getElementById("biz_clsfcn_nm").value	            = frm.cls_biz_nm.value;
				opener.document.getElementById("depth_clsfcn").value 	            = frm.cls_contdepth.value;
				opener.document.getElementById("depth_clsfcn_nm").value	            = frm.cls_contdepth_nm.value;
				opener.document.getElementById("cnclsnpurps_bigclsfcn").value 	    = frm.cls_bigcontpurpose.value;
				opener.document.getElementById("cnclsnpurps_bigclsfcn_nm").value	= frm.cls_bigcontpurpose_nm.value;
				opener.document.getElementById("cnclsnpurps_midclsfcn").value 	    = frm.cls_midcontpurpose.value;
				opener.document.getElementById("cnclsnpurps_midclsfcn_nm").value	= frm.cls_midcontpurpose_nm.value;

				
				//alert("Formal contract = " + $(":input:radio[name=type1]:checked").val());
				//alert("New contract = " + $(":input:radio[name=type2]:checked").val());
				//alert("3dr = " + $(":input:radio[name=type3]:checked").val());
			
				if( ($(":input:radio[name=type1]:checked").val() == 'T0103') && ($(":input:radio[name=type2]:checked").val() == 'T0201') && ($(":input:radio[name=type3]:checked").val() == "T030705" || $(":input:radio[name=type3]:checked").val() == "T030706" )){
					//alert("open");
					
					opener.tncfunc_false();	
				}else{
					//alert("clo");
					opener.tncfunc();
					
									
					//$("#cntrt_top30_cus", opener.document).attr('disabled', false);
					//$("#cntrt_src_cont_drft", opener.document).attr('disabled', false);
				}				
							
				window.opener.returnCntrtNm();
				window.opener.changeSubCd("cntrt_trgt", "CONTRACTTYPE",frm.cls_midcontpurpose.value);
				self.close();	
			}			
			
		
	}
	
	//각 팝업 오픈시 target을 달리 주고 싶을 때
	 function PopUpWindowOpen2(surl, popupwidth, popupheight, bScroll, popName) {
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
	 	
	 	
	 	var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars=" + (bScroll ? "yes" : "no") + ", resizable=no, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
	 	PopUpWindow = window.open(surl, popName , Feture);

	 	PopUpWindow.focus();
	 	
	 }
	
	
	function opnerGoTnc(){
		
		opener.GoTnc();
		
		//opener.location.href="http://10.40.65.96:6060/TNC/";
		
		target="main";
		
		self.close();
		
	}
	
	function noScreen(){
		$('#type_sel21').hide();
	}
	
	
	function popUPTnC(){		
					
		// tnc 팝업의 경우 체결후 등록 창에서 호출 되었을때는 처리 하지 않는다.
		<c:choose>
 			<c:when test="${'regConsideration' eq command.forword_from }">
	 			return;
			</c:when> 
			<c:otherwise>
				PopUpWindowOpen2("/clm/manage/chooseContractType.do?method=forwardTncOrEcms", 500, 170, false, "TncOrEcms_p");
			</c:otherwise>
		</c:choose>		
		
	}
		
</script>
</head>
<body class="pop">
	
	<div class='type_sel2' id="type_sel21" name="type_sel21" style="display: none;"></div>
	
	<div id="arrow" style='position:absolute; top:488px; left:196px;'><img src="<%=IMAGE%>/new2011/arrow2.png" /></div>
	<!-- header -->
	<h1>
		<spring:message code="clm.page.msg.manage.selContType" />
		<span class="close" onclick="javascript:self.close();" title="close"></span>
	</h1>
	<!-- //header -->
	
	<div class="pop_area">
		<div class="pop_content">
				<form:form name="frm" id='frm' method="post" autocomplete="off">
				
				
					<div class="choo_Tbox">
						<div class="pop_Nbox">
							<spring:message code="clm.page.msg.manage.announce054" />
						</div>
						<div class="choo_Talbox">
							<table border="0" cellspacing="0" cellpadding="0" class="pop_tal_choo">
							<colgroup>
							<col width="30%">
							<col width="30%">
							<col width="*">
							</colgroup>
								<tr>
									<th><spring:message code="clm.page.msg.manage.bizStage" /></th>
									<th><spring:message code="clm.page.msg.manage.contStage" /></th>
									<th><spring:message code="clm.page.msg.common.purposeOfConcl" /></th>
								</tr>
								<tr>
									<td  class='Linetop'><span id="spanbiz">&nbsp;</span> 
									    <input type="hidden" name="cls_biz_nm" />
									    <input type="hidden" name="cls_biz" />
									</td>
									<td  class='Linetop'>
									    <span id="spandepth">&nbsp;</span> 
									        <input type="hidden" name="cls_contdepth_nm" />
									        <input type="hidden" name="cls_contdepth" />
									</td>
									<td  class='Linetop'>
									    <span id="spanpurpose">&nbsp;</span> 
									        <input type="hidden" name="cls_midcontpurpose_nm" />
									        <input type="hidden" name="cls_midcontpurpose" /> 
									        <input type="hidden" name="cls_bigcontpurpose_nm" />
									        <input type="hidden" name="cls_bigcontpurpose" />
									</td>
								</tr>
							</table>
						</div>
					</div>

					<!-- choo_wrap -->
					<div class="choo_wrap">
						<c:set var="loop" value="1" />
						<!-- //left_list -->
						<div class="choo_Lbox fL" >
							<ul>
								<c:forEach var="list" items="${biz}">
									<c:choose>
										<c:when test="${list.flag == 0}">
											<c:if test="${list.type_cd != 'T01'}">
							</ul>
							</dd>
							</dl>
							<span class='arr'><img src="<%=IMAGE%>/new2011/arrow1.png" /></span>
							</li>
							</c:if>
							<li>
								<dl class="chooBox02">
									<dt><c:out value="${loop}" />.<c:out value="${list.cd_nm_abbr}" /></dt>
									<dd>
										<ul>
											<c:set var="loop" value="${loop+loop}" />
											</c:when>
											<c:otherwise>
												
												<li>
												   <div class='fL' style='width:14%'><input type="radio" class="radio" name="<c:out value='${list.opt_nm}'/>" id="<c:out value='${list.type_cd}'/>" value="<c:out value='${list.type_cd}'/>" text="<c:out value='${list.cd_nm}'/>" onclick="javascript:setDispValue(this, '<c:out value='${list.type_cd}'/>_nm');"></input></div>
												   <div class='fL' style='width:86%'><c:out value="${list.cd_nm}" /><input type="hidden" id="<c:out value='${list.type_cd}'/>_nm" value="<c:out value='${list.cd_nm}'/>" /></div>
												</li>
											</c:otherwise>
											</c:choose>
											</c:forEach>
										</ul>
									</dd>
								</dl>
							</li>
							<li><span class='arr'><img src="<%=IMAGE%>/new2011/arrow1.png" /></span></li>
							<li>
								<dl class="chooBox02">
									<dt>
										<span><spring:message code="clm.page.msg.manage.purpOfConcl" /></span>
									</dt>
									<dd>
										<ul>
											<li>
											    <div class='fL' style='width:14%'><input name="purpose" type="radio" class="radio" value="top3" text="<spring:message code="clm.page.msg.manage.selRight" htmlEscape="true" />" onclick="javascript:setPurposeStatus();setNoneValue(this);" checked=true /></div>
											    <div class='fL' style='width:86%'><spring:message code="clm.page.msg.manage.selRight" /></div>
											</li>
											<li>
											    <div class='fL' style='width:14%'><input name="purpose" type="radio" class="radio" value="none" text="<spring:message code="clm.page.msg.manage.noDecide" htmlEscape="true" />" onclick="javascript:setPurposeStatus();setNoneValue(this);" /></div>
											    <div class='fL' style='width:86%'><spring:message code="clm.page.msg.manage.noDecide" /></div>
											</li>
										</ul>
									</dd>
								</dl>
							</li>
						</ul>

						</div>
						<!-- //left_list -->
						<!-- right_list -->
						<div class="choo_Rbox fL">
							<ul>
								<c:forEach var="list" items="${depth}">
									<c:if test="${list.rm != '1'}">
							</ul>
							</dd>
							</dl>
							</li>
							</c:if>
							<li>
								<dl class="chooBox">
									<dt>
										<c:out value="${list.cd_nm}" />
										<input type="hidden" name="<c:out value='${list.type_cd}'/>_bigtype" value="<c:out value='${list.type_cd}'/>" text="<c:out value='${list.cd_nm}'/>" /> 
										<input type="hidden" name="<c:out value='${list.type_cd}'/>_bigtype_nm" value="<c:out value='${list.cd_nm}'/>" />
									</dt>
									<dd>
										<ul>
											<c:forEach var="list3" items="${purpose}">
												<c:if test="${list.type_cd==list3.up_type_cd}">
													<li>
													    <div class='fL' style='width:10%'><input type="radio" class="radio" name="<c:out value='${list3.opt_nm}'/>" value="<c:out value='${list3.type_cd}'/>" text="<c:out value='${list3.cd_nm}'/>" onclick="javascript:setDispValue(this, '<c:out value='${list3.type_cd}'/>_nm');" /></div>
													    <div class='fL' style='width:90%'><c:out value="${list3.cd_nm}" /><input type="hidden" id="<c:out value='${list3.type_cd}'/>_nm" value="<c:out value='${list3.cd_nm}'/>" /></div>
													</li>
												</c:if>
											</c:forEach>
											</c:forEach>
										</ul>
									</dd>
								</dl>
							</li>
							</ul>
						</div>
						<!-- //right_list -->
					</div>
					<!-- choowrap -->
				
				<!--// pop_content -->
				</form:form>
				
		</div>
	</div>
	<!--footer button --><!-- 확인 버튼 -->
	<div class="pop_footer">
		<div class="btn_wrap tR">
			<span class="btn_all_w" onclick="javascript:returnValue();" ><span class="confirm"></span><a><spring:message code="clm.page.msg.common.confirmType" /></a></span>
		</div>
	</div>
	<!--//footer button -->
</body>

</html>