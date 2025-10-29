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
<script language="javascript" src="/script/clms/jquery-1.6.2.min.js" type="text/javascript"></script>
<script language="javascript" src="/script/clms/new_style.js" type="text/javascript"></script>
<script language="javascript">
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
		
		var parent_cnsd_level = obj.cnsd_level ;
		var parent_prgrs_status = obj.prgrs_status ; 
		
		var parent_biz_clsfcn = obj.biz_clsfcn;
		var parent_depth_clsfcn = obj.depth_clsfcn;
		var parent_cnclsnpurps_bigclsfcn = obj.cnclsnpurps_bigclsfcn;
		var parent_cnclsnpurps_midclsfcn = obj.cnclsnpurps_midclsfcn;
		var isMod = (parent_cnsd_level=="1" &&  parent_prgrs_status=="C04201") || (parent_cnsd_level=="" && parent_prgrs_status=="") ? true : false ;
		
		if(obj.biz_clsfcn != "") {
			for(var i=0; i<frm.type1.length;i++) {
				if(frm.type1[i].value==parent_biz_clsfcn) {
					frm.type1[i].checked = true;
					setDispValue(frm.type1[i], frm.type1[i].value + "_nm", "en_"+frm.type1[i].value + "_nm");
					break;
				}	
			}
			
			for(var i=0; i<frm.type2.length;i++) {
				if(frm.type2[i].value==parent_depth_clsfcn) {
					frm.type2[i].checked = true;
					
					setDispValue(frm.type2[i], frm.type2[i].value + "_nm", "en_"+frm.type2[i].value + "_nm");
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
						setDispValue(frm.type3[i], frm.type3[i].value + "_nm", "en_"+frm.type3[i].value + "_nm");
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
	function setDispValue(object, textFieldNm, e_textFieldNm) {

		var frm = document.frm;
		var rdoValue = object.value;
		var rdoText	 = document.getElementById(textFieldNm).value;
		
		var e_rdoText	 = document.getElementById(e_textFieldNm).value;
		var bigclsnm = "";
		
		if(rdoValue.substr(0,3)=="T01") {
			frm.cls_biz_nm.value = rdoText;
			frm.en_cls_biz_nm.value = e_rdoText;
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
		    frm.en_cls_contdepth_nm.value = e_rdoText;
			frm.cls_contdepth.value = rdoValue;
			spandepth.innerHTML = rdoText;
			
		} else {
			bigclsnm = rdoValue.substr(0,5);
			
			frm.cls_bigcontpurpose.value = eval("frm." + bigclsnm + "_bigtype.value");
			frm.cls_bigcontpurpose_nm.value = eval("frm." + bigclsnm + "_bigtype_nm.value");
			frm.en_cls_bigcontpurpose_nm.value = eval("frm.en_" + bigclsnm + "_bigtype_nm.value");
			frm.cls_midcontpurpose_nm.value = rdoText;
			frm.en_cls_midcontpurpose_nm.value = e_rdoText;
			frm.cls_midcontpurpose.value = rdoValue;
			spanpurpose.innerHTML = rdoText;
	    }
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
		if(checkValidation()){
			
			var frm = document.frm;
			var obj = window.dialogArguments;
			
			//alert(obj.cnclsnpurps_bigclsfcn + frm.cls_bigcontpurpose.value);
			//alert(obj.cnclsnpurps_midclsfcn + frm.cls_midcontpurpose.value);
			//alert(frm.cls_bigcontpurpose.value.substring(4,5));
			//alert(obj.cnclsnpurps_bigclsfcn.substring(4,5));
			
			if(obj.mod_status == 'popup'){			
				
				if(obj.cnclsnpurps_bigclsfcn == 'T0301' && frm.cls_bigcontpurpose.value != 'T0301'){  // 개발
					alert("<spring:message code="clm.page.msg.manage.announce011" /> ");return;
				}else if(obj.cnclsnpurps_bigclsfcn == 'T0302' && frm.cls_bigcontpurpose.value != 'T0302'){ //특허
					alert("<spring:message code="clm.page.msg.manage.announce190" /> ");return;
				}else if(obj.cnclsnpurps_bigclsfcn == 'T0303' && frm.cls_bigcontpurpose.value != 'T0303'){ //라아손수
					alert("<spring:message code="clm.page.msg.manage.announce080" /> ");return;
				}else if(eval(obj.cnclsnpurps_bigclsfcn.substring(4,5)) >  3 && eval(frm.cls_bigcontpurpose.value.substring(4,5)) < 4 ){
					alert("<spring:message code="clm.page.msg.manage.announce010" />");return;
				}else{
					self.close();
				}
			}else{
				self.close();	
			}			
		} else {
			return;
		}	
		
	}	
	
	function setReturnVal() {
		var frm 	= document.frm;
		var retObj = window.dialogArguments;
		 
		retObj.biz_clsfcn 				    = frm.cls_biz.value;
		retObj.biz_clsfcn_nm			    = frm.cls_biz_nm.value;
		retObj.en_biz_clsfcn_nm			    = frm.en_cls_biz_nm.value;
		retObj.depth_clsfcn 		 	    = frm.cls_contdepth.value;
		retObj.depth_clsfcn_nm		 	    = frm.cls_contdepth_nm.value;
		retObj.en_depth_clsfcn_nm		    = frm.en_cls_contdepth_nm.value;
		retObj.cnclsnpurps_bigclsfcn 	    = frm.cls_bigcontpurpose.value;
		retObj.cnclsnpurps_bigclsfcn_nm	    = frm.cls_bigcontpurpose_nm.value;
		retObj.en_cnclsnpurps_bigclsfcn_nm	= frm.en_cls_bigcontpurpose_nm.value;
		retObj.cnclsnpurps_midclsfcn 	    = frm.cls_midcontpurpose.value;
		retObj.cnclsnpurps_midclsfcn_nm	    = frm.cls_midcontpurpose_nm.value;
		retObj.en_cnclsnpurps_midclsfcn_nm	= frm.en_cls_midcontpurpose_nm.value;
		
		window.returnValue = retObj;
	}
</script>
</head>
<body class="pop" onload='showMessage("<c:out value='${returnMessage}'/>");setPurposeStatus();setInitValue();' onunload="javascript:setReturnVal();">
	
	<div id="arrow" style='position:absolute; top:480px; left:196px;'><img src="<%=IMAGE%>/new2011/arrow2.png" /></div>
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
								<tr>
									<th><spring:message code="clm.page.msg.manage.bizStage" /></th>
									<th><spring:message code="clm.page.msg.manage.contStage" /></th>
									<th><spring:message code="clm.page.msg.common.purposeOfConcl" /></th>
								</tr>
								<tr>
									<td  class='Linetop'><span id="spanbiz">&nbsp;</span> 
									    <input type="hidden" name="cls_biz_nm" />
									    <input type="hidden" name="en_cls_biz_nm" />
									    <input type="hidden" name="cls_biz" />
									</td>
									<td  class='Linetop'>
									    <span id="spandepth">&nbsp;</span> 
									        <input type="hidden" name="cls_contdepth_nm" />
									        <input type="hidden" name="en_cls_contdepth_nm" />
									        <input type="hidden" name="cls_contdepth" />
									</td>
									<td  class='Linetop'>
									    <span id="spanpurpose">&nbsp;</span> 
									        <input type="hidden" name="cls_midcontpurpose_nm" />
									        <input type="hidden" name="en_cls_midcontpurpose_nm" />
									        <input type="hidden" name="cls_midcontpurpose" /> 
									        <input type="hidden" name="cls_bigcontpurpose_nm" />
									        <input type="hidden" name="en_cls_bigcontpurpose_nm" />
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
												   <input type="radio" class="radio" name="<c:out value='${list.opt_nm}'/>" id="<c:out value='${list.type_cd}'/>" value="<c:out value='${list.type_cd}'/>" text="<c:out value='${list.cd_nm}'/>" onclick="javascript:setDispValue(this, '<c:out value='${list.type_cd}'/>_nm', 'en_<c:out value='${list.type_cd}'/>_nm');"> <c:out value="${list.cd_nm}" /></input> 
												   <input type="hidden" id="<c:out value='${list.type_cd}'/>_nm" value="<c:out value='${list.cd_nm}'/>" />
												   <input type="hidden" id="en_<c:out value='${list.type_cd}'/>_nm" value="<c:out value='${list.e_cd_nm}'/>" />
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
											    <input name="purpose" type="radio" class="radio" value="top3" text="<spring:message code="clm.page.msg.manage.selRight" htmlEscape="true" />" onclick="javascript:setPurposeStatus();setNoneValue(this);" checked=true />
											    <spring:message code="clm.page.msg.manage.selRight" />
											</li>
											<li>
											    <input name="purpose" type="radio" class="radio" value="none" text="<spring:message code="clm.page.msg.manage.noDecide" htmlEscape="true" />" onclick="javascript:setPurposeStatus();setNoneValue(this);" />
											    <spring:message code="clm.page.msg.manage.noDecide" />
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
										<input type="hidden" name="en_<c:out value='${list.type_cd}'/>_bigtype_nm" value="<c:out value='${list.e_cd_nm}'/>" />
									</dt>
									<dd>
										<ul>
											<c:forEach var="list3" items="${purpose}">
												<c:if test="${list.type_cd==list3.up_type_cd}">
													<li>
													    <input type="radio" class="radio" name="<c:out value='${list3.opt_nm}'/>" value="<c:out value='${list3.type_cd}'/>" text="<c:out value='${list3.cd_nm}'/>" onclick="javascript:setDispValue(this, '<c:out value='${list3.type_cd}'/>_nm', 'en_<c:out value='${list3.type_cd}'/>_nm');" />
													    <c:out value="${list3.cd_nm}" /> 
													    <input type="hidden" id="<c:out value='${list3.type_cd}'/>_nm" value="<c:out value='${list3.cd_nm}'/>" />
													    <input type="hidden" id="en_<c:out value='${list3.type_cd}'/>_nm" value="<c:out value='${list3.e_cd_nm}'/>" />
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
	<!--footer button -->
	<div class="pop_footer">
		<div class="btn_wrap tR">
			<span class="btn_all_w"><span class="confirm"></span><a href="javascript:returnValue();"><spring:message code="clm.page.msg.common.confirmType" /></a></span>
		</div>
	</div>
	<!--//footer button -->
</body>

</html>