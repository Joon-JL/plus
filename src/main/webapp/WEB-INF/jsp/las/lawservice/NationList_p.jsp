<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%--
/**
 * 파  일  명 : NationList_p.jsp
 * 프로그램명 : 법무시스템 : 국가 선택 팝업
 * 설      명 : 
 * 작  성  자 : 박병주
 * 작  성  일 : 2011.09
 */
 --%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><spring:message code="las.main.title" /></title>
<LINK href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/las.css" type="text/css" rel="stylesheet">
<script language="javascript" src="<c:url value='/script/clms/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/validation_${langCd}.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/clms_common.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="JavaScript" type="text/JavaScript" >
<!--
	
	function setCountry(){
		
		var allCountry = document.all.tags("INPUT");
		
		var check_count = 0;
		var checkedNum = 0;
		
		for(var i=0; i < allCountry.length ; i++) {
			if (allCountry.item(i).checked == true ){
				
				check_count = check_count + 1;
				checkedNum = i;
			}
		}
		
		if(opener.frm.screen_type.value == 'list'){
			opener.frm.srch_nation.value = allCountry.item(checkedNum).value;
			opener.frm.srch_nation_nm.value = allCountry.item(checkedNum).title;		
			window.close();
		} else {
			opener.frm.nation.value = allCountry.item(checkedNum).value;
			opener.frm.nation_nm.value = allCountry.item(checkedNum).title;		
			window.close();
		}
	}

//-->
</script>
</head>
<body class="pop">
<!-- header -->
<h1><spring:message code="las.page.field.lawservice.selectnation"/><span class="close" onclick="javascript:self.close();" title="close"></span></h1>
<!-- //header -->
<div class="pop_area">
<!-- Popup Detail -->
<!-- Popup Size 1000*860 -->
	<div class="h_860_1">
		<!-- pop_content -->
		<div class="pop_content">
			
			<div class="nation_box">
				<ul>
					<li><input type="checkbox" name="country" value="CN" title="China" onfocus=this.blur() onclick="javascript:setCountry()"; >China (CN)</li>
					<li><input type="checkbox" name="country" value="JP" title="Japan" onfocus=this.blur() onclick="javascript:setCountry()"; >Japan (JP)</li>
					<li><input type="checkbox" name="country" value="KR" title="Korea" onfocus=this.blur() onclick="javascript:setCountry()"; >Korea (KR)</li>
					<li><input type="checkbox" name="country" value="US" title="U.S.A. " onfocus=this.blur() onclick="javascript:setCountry()"; >U.S.A.  (US)</li>
					<li><input type="checkbox" name="country" value="GB" title="United Kingdom" onfocus=this.blur() onclick="javascript:setCountry()"; >United Kingdom (GB)</li>
				</ul>
			</div>
			<!-- 
				 <div class="btn_wrap fR">
     				<span class="btn_all_b"><span class="save"></span><a href="#" onclick="javascript:setCountry();">확인</a></span>
	 				<span class="btn_all_w"><span class="cancel"></span><a href="#" onclick="javascript:self.close();">닫기</a></span>
	 			</div>
		-->
				
			<!-- choo_wrap -->
			<div class="nation_wrap">
				<table class="national">
					<thead>
						<tr>
							<th class="col_01"><spring:message code="las.page.field.lawservice.asia"/></th>
							<th class="col_02"><spring:message code="las.page.field.lawservice.euro"/></th>
							<th class="col_03"><spring:message code="las.page.field.lawservice.afn"/></th>
							<th class="col_04"><spring:message code="las.page.field.lawservice.oceania"/></th>
							<th class="col_05"><spring:message code="las.page.field.lawservice.america"/></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<ul>
								<li><input type="checkbox" name="country" value="AF" title="Afghanistan" onfocus=this.blur() onclick="javascript:setCountry()"; >Afghanistan (AF)</li>
								<li><input type="checkbox" name="country" value="BH" title="Bahrain" onfocus=this.blur() onclick="javascript:setCountry()"; >Bahrain (BH)</li>
								<li><input type="checkbox" name="country" value="BD" title="Bangladesh" onfocus=this.blur() onclick="javascript:setCountry()"; >Bangladesh (BD)</li>
								<li><input type="checkbox" name="country" value="BT" title="Bhutan" onfocus=this.blur() onclick="javascript:setCountry()"; >Bhutan (BT)</li>
								<li><input type="checkbox" name="country" value="BN" title="Brunei" onfocus=this.blur() onclick="javascript:setCountry()"; >Brunei (BN)</li>
								<li><input type="checkbox" name="country" value="CB" title="Combodia" onfocus=this.blur() onclick="javascript:setCountry()"; >Combodia (CB)</li>
								<li><input type="checkbox" name="country" value="HK" title="Hong Kong" onfocus=this.blur() onclick="javascript:setCountry()"; >Hong Kong (HK)</li>
								<li><input type="checkbox" name="country" value="IN" title="India" onfocus=this.blur() onclick="javascript:setCountry()"; >India (IN)</li>
								<li><input type="checkbox" name="country" value="ID" title="Indonesia" onfocus=this.blur() onclick="javascript:setCountry()"; >Indonesia (ID)</li>
								<li><input type="checkbox" name="country" value="IR" title="Iran" onfocus=this.blur() onclick="javascript:setCountry()"; >Iran (IR)</li>
								<li><input type="checkbox" name="country" value="IQ" title="Iraq" onfocus=this.blur() onclick="javascript:setCountry()"; >Iraq (IQ)</li>
								<li><input type="checkbox" name="country" value="IL" title="Israel" onfocus=this.blur() onclick="javascript:setCountry()"; >Israel (IL)</li>
								<li><input type="checkbox" name="country" value="JO" title="Jordan" onfocus=this.blur() onclick="javascript:setCountry()"; >Jordan (JO)</li>
								<li><input type="checkbox" name="country" value="KZ" title="Kazakstan" onfocus=this.blur() onclick="javascript:setCountry()"; >Kazakstan (KZ)</li>
								<li><input type="checkbox" name="country" value="KW" title="Kuwait" onfocus=this.blur() onclick="javascript:setCountry()"; >Kuwait (KW)</li>
								<li><input type="checkbox" name="country" value="LA" title="Laos" onfocus=this.blur() onclick="javascript:setCountry()"; >Laos (LA)</li>
								<li><input type="checkbox" name="country" value="LB" title="Lebanon" onfocus=this.blur() onclick="javascript:setCountry()"; >Lebanon (LB)</li>
								<li><input type="checkbox" name="country" value="MO" title="Macau" onfocus=this.blur() onclick="javascript:setCountry()"; >Macau (MO)</li>
								<li><input type="checkbox" name="country" value="MY" title="Malaysia" onfocus=this.blur() onclick="javascript:setCountry()"; >Malaysia (MY)</li>
								<li><input type="checkbox" name="country" value="MV" title="Maldiv Islands" onfocus=this.blur() onclick="javascript:setCountry()"; >Maldiv Islands (MV)</li>
								<li><input type="checkbox" name="country" value="MN" title="Mongolia" onfocus=this.blur() onclick="javascript:setCountry()"; >Mongolia (MN)</li>
								<li><input type="checkbox" name="country" value="MM" title="Myanmar" onfocus=this.blur() onclick="javascript:setCountry()"; >Myanmar (MM)</li>
								<li><input type="checkbox" name="country" value="NP" title="Nepal" onfocus=this.blur() onclick="javascript:setCountry()"; >Nepal (NP)</li>
								<li><input type="checkbox" name="country" value="KP" title="Nouth Korea" onfocus=this.blur() onclick="javascript:setCountry()"; >Nouth Korea (KP)</li>
								<li><input type="checkbox" name="country" value="OM" title="Oman" onfocus=this.blur() onclick="javascript:setCountry()"; >Oman (OM)</li>
								<li><input type="checkbox" name="country" value="PK" title="Pakistan" onfocus=this.blur() onclick="javascript:setCountry()"; >Pakistan (PK)</li>
								<li><input type="checkbox" name="country" value="PS" title="Palestine" onfocus=this.blur() onclick="javascript:setCountry()"; >Palestine (PS)</li>
								<li><input type="checkbox" name="country" value="PH" title="Philippines" onfocus=this.blur() onclick="javascript:setCountry()"; >Philippines (PH)</li>
								<li><input type="checkbox" name="country" value="QA" title="Qatar" onfocus=this.blur() onclick="javascript:setCountry()"; >Qatar (QA)</li>
								<li><input type="checkbox" name="country" value="RA" title="Ras Al Khaimah" onfocus=this.blur() onclick="javascript:setCountry()"; >Ras Al Khaimah (RA)</li>
								<li><input type="checkbox" name="country" value="SA" title="Saudi Arabia" onfocus=this.blur() onclick="javascript:setCountry()"; >Saudi Arabia (SA)</li>
								<li><input type="checkbox" name="country" value="SG" title="Singapore" onfocus=this.blur() onclick="javascript:setCountry()"; >Singapore (SG)</li>
								<li><input type="checkbox" name="country" value="LK" title="Sri Lanka" onfocus=this.blur() onclick="javascript:setCountry()"; >Sri Lanka (LK)</li>
								<li><input type="checkbox" name="country" value="SY" title="Syria" onfocus=this.blur() onclick="javascript:setCountry()"; >Syria (SY)</li>
								<li><input type="checkbox" name="country" value="TW" title="Taiwan" onfocus=this.blur() onclick="javascript:setCountry()"; >Taiwan (TW)</li>
								<li><input type="checkbox" name="country" value="TH" title="Thailand" onfocus=this.blur() onclick="javascript:setCountry()"; >Thailand (TH)</li>
								<li><input type="checkbox" name="country" value="AE" title="United Arb Emirates" onfocus=this.blur() onclick="javascript:setCountry()"; >United Arb Emirates (AE)</li>
								<li><input type="checkbox" name="country" value="VN" title="Vietnam" onfocus=this.blur() onclick="javascript:setCountry()"; >Vietnam (VN)</li>
								<li><input type="checkbox" name="country" value="YE" title="Yemen" onfocus=this.blur() onclick="javascript:setCountry()"; >Yemen (YE)</li>
								</ul>
							</td>
							<td>
								<ul>
								<li><input type="checkbox" name="country" value="AL" title="Albania" onfocus=this.blur() onclick="javascript:setCountry()"; >Albania (AL)</li>
								<li><input type="checkbox" name="country" value="AM" title="Armenia" onfocus=this.blur() onclick="javascript:setCountry()"; >Armenia (AM)</li>
								<li><input type="checkbox" name="country" value="AT" title="Austria" onfocus=this.blur() onclick="javascript:setCountry()"; >Austria (AT)</li>
								<li><input type="checkbox" name="country" value="AZ" title="Azerbaijan" onfocus=this.blur() onclick="javascript:setCountry()"; >Azerbaijan (AZ)</li>
								<li><input type="checkbox" name="country" value="BY" title="Belarus" onfocus=this.blur() onclick="javascript:setCountry()"; >Belarus (BY)</li>
								<li><input type="checkbox" name="country" value="BE" title="Belgium" onfocus=this.blur() onclick="javascript:setCountry()"; >Belgium (BE)</li>
								<li><input type="checkbox" name="country" value="BX" title="Belgium,Netherlands,Luxembourg" onfocus=this.blur() onclick="javascript:setCountry()"; >Belgium,Netherlands,
								<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Luxembourg (BX)</li>
								<li><input type="checkbox" name="country" value="BG" title="Bulgaria" onfocus=this.blur() onclick="javascript:setCountry()"; >Bulgaria (BG)</li>
								<li><input type="checkbox" name="country" value="KH" title="Cambodia" onfocus=this.blur() onclick="javascript:setCountry()"; >Cambodia (KH)</li>
								<li><input type="checkbox" name="country" value="CV" title="Cape Verde" onfocus=this.blur() onclick="javascript:setCountry()"; >Cape Verde (CV)</li>
								<li><input type="checkbox" name="country" value="CT" title="Croatia" onfocus=this.blur() onclick="javascript:setCountry()"; >Croatia (CT)</li>
								<li><input type="checkbox" name="country" value="CY" title="Cyprus" onfocus=this.blur() onclick="javascript:setCountry()"; >Cyprus (CY)</li>
								<li><input type="checkbox" name="country" value="CS" title="Czechoslovakia" onfocus=this.blur() onclick="javascript:setCountry()"; >Czechoslovakia (CS)</li>
								<li><input type="checkbox" name="country" value="DK" title="Denmark" onfocus=this.blur() onclick="javascript:setCountry()"; >Denmark (DK)</li>
								<li><input type="checkbox" name="country" value="EO" title="Estonia" onfocus=this.blur() onclick="javascript:setCountry()"; >Estonia (EO)</li>
								<li><input type="checkbox" name="country" value="FI" title="Finland" onfocus=this.blur() onclick="javascript:setCountry()"; >Finland (FI)</li>
								<li><input type="checkbox" name="country" value="FR" title="France" onfocus=this.blur() onclick="javascript:setCountry()"; >France (FR)</li>
								<li><input type="checkbox" name="country" value="GE" title="Georgia" onfocus=this.blur() onclick="javascript:setCountry()"; >Georgia (GE)</li>
								<li><input type="checkbox" name="country" value="DE" title="Germany" onfocus=this.blur() onclick="javascript:setCountry()"; >Germany (DE)</li>
								<li><input type="checkbox" name="country" value="GI" title="Gibraltar" onfocus=this.blur() onclick="javascript:setCountry()"; >Gibraltar (GI)</li>
								<li><input type="checkbox" name="country" value="GR" title="Greece" onfocus=this.blur() onclick="javascript:setCountry()"; >Greece (GR)</li>
								<li><input type="checkbox" name="country" value="GG" title="Guernsey" onfocus=this.blur() onclick="javascript:setCountry()"; >Guernsey (GG)</li>
								<li><input type="checkbox" name="country" value="HU" title="Hungary" onfocus=this.blur() onclick="javascript:setCountry()"; >Hungary (HU)</li>
								<li><input type="checkbox" name="country" value="IS" title="Iceland" onfocus=this.blur() onclick="javascript:setCountry()"; >Iceland (IS)</li>
								<li><input type="checkbox" name="country" value="IE" title="Ireland" onfocus=this.blur() onclick="javascript:setCountry()"; >Ireland (IE)</li>
								<li><input type="checkbox" name="country" value="IT" title="Italy" onfocus=this.blur() onclick="javascript:setCountry()"; >Italy (IT)</li>
								<li><input type="checkbox" name="country" value="JE" title="Jersey" onfocus=this.blur() onclick="javascript:setCountry()"; >Jersey (JE)</li>
								<li><input type="checkbox" name="country" value="KG" title="Kyrgyzstan" onfocus=this.blur() onclick="javascript:setCountry()"; >Kyrgyzstan (KG)</li>
								<li><input type="checkbox" name="country" value="LT" title="Latvia" onfocus=this.blur() onclick="javascript:setCountry()"; >Latvia (LT)</li>
								<li><input type="checkbox" name="country" value="LI" title="Liechtenstein" onfocus=this.blur() onclick="javascript:setCountry()"; >Liechtenstein (LI)</li>
								<li><input type="checkbox" name="country" value="LN" title="Lithuania" onfocus=this.blur() onclick="javascript:setCountry()"; >Lithuania (LN)</li>
								<li><input type="checkbox" name="country" value="LU" title="Luxembourg" onfocus=this.blur() onclick="javascript:setCountry()"; >Luxembourg (LU)</li>
								<li><input type="checkbox" name="country" value="MT" title="Malta" onfocus=this.blur() onclick="javascript:setCountry()"; >Malta (MT)</li>
								<li><input type="checkbox" name="country" value="MD" title="Moldova" onfocus=this.blur() onclick="javascript:setCountry()"; >Moldova (MD)</li>
								<li><input type="checkbox" name="country" value="MC" title="Monaco" onfocus=this.blur() onclick="javascript:setCountry()"; >Monaco (MC)</li>
								<li><input type="checkbox" name="country" value="MS" title="Montserrat" onfocus=this.blur() onclick="javascript:setCountry()"; >Montserrat (MS)</li>
								<li><input type="checkbox" name="country" value="NT" title="Neutral Zone" onfocus=this.blur() onclick="javascript:setCountry()"; >Neutral Zone (NT)</li>
								<li><input type="checkbox" name="country" value="NO" title="Norway" onfocus=this.blur() onclick="javascript:setCountry()"; >Norway (NO)</li>
								<li><input type="checkbox" name="country" value="EP" title="OHIM" onfocus=this.blur() onclick="javascript:setCountry()"; >OHIM (EP)</li>
								<li><input type="checkbox" name="country" value="PL" title="Poland" onfocus=this.blur() onclick="javascript:setCountry()"; >Poland (PL)</li>
								<li><input type="checkbox" name="country" value="PT" title="Protugal" onfocus=this.blur() onclick="javascript:setCountry()"; >Protugal (PT)</li>
								<li><input type="checkbox" name="country" value="RO" title="Romania" onfocus=this.blur() onclick="javascript:setCountry()"; >Romania (RO)</li>
								<li><input type="checkbox" name="country" value="RU" title="Russia" onfocus=this.blur() onclick="javascript:setCountry()"; >Russia (RU)</li>
								<li><input type="checkbox" name="country" value="SM" title="Serbia and Montenegro" onfocus=this.blur() onclick="javascript:setCountry()"; >Serbia and Montenegro</li>
								<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(SM)</li>
								<li><input type="checkbox" name="country" value="SK" title="Slovakia" onfocus=this.blur() onclick="javascript:setCountry()"; >Slovakia (SK)</li>
								<li><input type="checkbox" name="country" value="SI" title="Slovenia" onfocus=this.blur() onclick="javascript:setCountry()"; >Slovenia (SI)</li>
								<li><input type="checkbox" name="country" value="ES" title="Spain" onfocus=this.blur() onclick="javascript:setCountry()"; >Spain (ES)</li>
								<li><input type="checkbox" name="country" value="SE" title="Sweden" onfocus=this.blur() onclick="javascript:setCountry()"; >Sweden (SE)</li>
								<li><input type="checkbox" name="country" value="CH" title="Switzerland" onfocus=this.blur() onclick="javascript:setCountry()"; >Switzerland (CH)</li>
								<li><input type="checkbox" name="country" value="TA" title="Tadzhikistan" onfocus=this.blur() onclick="javascript:setCountry()"; >Tadzhikistan (TA)</li>
								<li><input type="checkbox" name="country" value="TR" title="Turkey" onfocus=this.blur() onclick="javascript:setCountry()"; >Turkey (TR)</li>
								<li><input type="checkbox" name="country" value="UA" title="Ukraine" onfocus=this.blur() onclick="javascript:setCountry()"; >Ukraine (UA)</li>
								<li><input type="checkbox" name="country" value="UZ" title="Uzbekistan" onfocus=this.blur() onclick="javascript:setCountry()"; >Uzbekistan (UZ)</li>
								<li><input type="checkbox" name="country" value="VA" title="Vatican City State (Holy See)" onfocus=this.blur() onclick="javascript:setCountry()"; >Vatican City State </li>
								<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(Holy See)(VA)</li>
								<li><input type="checkbox" name="country" value="VG" title="Virgin Islands (British)" onfocus=this.blur() onclick="javascript:setCountry()"; >Virgin Islands </li>
								<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(British) (VG)</li>
								<li><input type="checkbox" name="country" value="MW" title="WIPO" onfocus=this.blur() onclick="javascript:setCountry()"; >WIPO (MW)</li>
								<li><input type="checkbox" name="country" value="WS" title="Western Samoa" onfocus=this.blur() onclick="javascript:setCountry()"; >Western Samoa (WS)</li>
								<li><input type="checkbox" name="country" value="YU" title="Yugoslavia" onfocus=this.blur() onclick="javascript:setCountry()"; >Yugoslavia (YU)</li>
								<li><input type="checkbox" name="country" value="TM" title="Turkmenistan" onfocus=this.blur() onclick="javascript:setCountry()"; >Turkmenistan (TM)</li>
								</ul>
							</td>
							<td>
								<ul>
								<li><input type="checkbox" name="country" value="DZ" title="Algeria" onfocus=this.blur() onclick="javascript:setCountry()"; >Algeria (DZ)</li>
								<li><input type="checkbox" name="country" value="AO" title="Angola" onfocus=this.blur() onclick="javascript:setCountry()"; >Angola (AO)</li>
								<li><input type="checkbox" name="country" value="BJ" title="Benin" onfocus=this.blur() onclick="javascript:setCountry()"; >Benin (BJ)</li>
								<li><input type="checkbox" name="country" value="BP" title="Bophuthatswana" onfocus=this.blur() onclick="javascript:setCountry()"; >Bophuthatswana (BP)</li>
								<li><input type="checkbox" name="country" value="BW" title="Bostwana" onfocus=this.blur() onclick="javascript:setCountry()"; >Bostwana (BW)</li>
								<li><input type="checkbox" name="country" value="BI" title="Burundi" onfocus=this.blur() onclick="javascript:setCountry()"; >Burundi (BI)</li>
								<li><input type="checkbox" name="country" value="CM" title="Cameroon" onfocus=this.blur() onclick="javascript:setCountry()"; >Cameroon (CM)</li>
								<li><input type="checkbox" name="country" value="CF" title="Central African Republic" onfocus=this.blur() onclick="javascript:setCountry()"; >Central African </li>
								<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Republic (CF)</li>
								<li><input type="checkbox" name="country" value="TD" title="Chad" onfocus=this.blur() onclick="javascript:setCountry()"; >Chad (TD)</li>
								<li><input type="checkbox" name="country" value="KM" title="Comoro Islands" onfocus=this.blur() onclick="javascript:setCountry()"; >Comoro Islands (KM)</li>
								<li><input type="checkbox" name="country" value="CG" title="Congo (Brazzaville)" onfocus=this.blur() onclick="javascript:setCountry()"; >Congo (Brazzaville)</li> (CG)</li>
								<li><input type="checkbox" name="country" value="DJ" title="Djibouti" onfocus=this.blur() onclick="javascript:setCountry()"; >Djibouti (DJ)</li>
								<li><input type="checkbox" name="country" value="EG" title="Egypt" onfocus=this.blur() onclick="javascript:setCountry()"; >Egypt (EG)</li>
								<li><input type="checkbox" name="country" value="GQ" title="Equatorial Guinea" onfocus=this.blur() onclick="javascript:setCountry()"; >Equatorial Guinea (GQ)</li>
								<li><input type="checkbox" name="country" value="ET" title="Ethiopia" onfocus=this.blur() onclick="javascript:setCountry()"; >Ethiopia (ET)</li>
								<li><input type="checkbox" name="country" value="GA" title="Gabon" onfocus=this.blur() onclick="javascript:setCountry()"; >Gabon (GA)</li>
								<li><input type="checkbox" name="country" value="GM" title="Gambia" onfocus=this.blur() onclick="javascript:setCountry()"; >Gambia (GM)</li>
								<li><input type="checkbox" name="country" value="GH" title="Ghana" onfocus=this.blur() onclick="javascript:setCountry()"; >Ghana (GH)</li>
								<li><input type="checkbox" name="country" value="GD" title="Grenada" onfocus=this.blur() onclick="javascript:setCountry()"; >Grenada (GD)</li>
								<li><input type="checkbox" name="country" value="GN" title="Guinea" onfocus=this.blur() onclick="javascript:setCountry()"; >Guinea (GN)</li>
								<li><input type="checkbox" name="country" value="GW" title="Guinea Bissau" onfocus=this.blur() onclick="javascript:setCountry()"; >Guinea Bissau (GW)</li>
								<li><input type="checkbox" name="country" value="CI" title="Ivory Coast" onfocus=this.blur() onclick="javascript:setCountry()"; >Ivory Coast (CI)</li>
								<li><input type="checkbox" name="country" value="KE" title="Kenya" onfocus=this.blur() onclick="javascript:setCountry()"; >Kenya (KE)</li>
								<li><input type="checkbox" name="country" value="LS" title="Lesotho" onfocus=this.blur() onclick="javascript:setCountry()"; >Lesotho (LS)</li>
								<li><input type="checkbox" name="country" value="LR" title="Liberia" onfocus=this.blur() onclick="javascript:setCountry()"; >Liberia (LR)</li>
								<li><input type="checkbox" name="country" value="LY" title="Libya" onfocus=this.blur() onclick="javascript:setCountry()"; >Libya (LY)</li>
								<li><input type="checkbox" name="country" value="MG" title="Malagasy Republic (Madagascar)" onfocus=this.blur() onclick="javascript:setCountry()"; >Malagasy Republic</li>
								<li> (Madagascar) (MG)</li>
								<li><input type="checkbox" name="country" value="2232" title="Malawi" onfocus=this.blur() onclick="javascript:setCountry()"; >Malawi (2232)</li>
								<li><input type="checkbox" name="country" value="ML" title="Mali" onfocus=this.blur() onclick="javascript:setCountry()"; >Mali (ML)</li>
								<li><input type="checkbox" name="country" value="MR" title="Mauritania" onfocus=this.blur() onclick="javascript:setCountry()"; >Mauritania (MR)</li>
								<li><input type="checkbox" name="country" value="MU" title="Mauritius" onfocus=this.blur() onclick="javascript:setCountry()"; >Mauritius (MU)</li>
								<li><input type="checkbox" name="country" value="MA" title="Morocco" onfocus=this.blur() onclick="javascript:setCountry()"; >Morocco (MA)</li>
								<li><input type="checkbox" name="country" value="MZ" title="Mozambique" onfocus=this.blur() onclick="javascript:setCountry()"; >Mozambique (MZ)</li>
								<li><input type="checkbox" name="country" value="NA" title="Namibia" onfocus=this.blur() onclick="javascript:setCountry()"; >Namibia (NA)</li>
								<li><input type="checkbox" name="country" value="NE" title="Niger " onfocus=this.blur() onclick="javascript:setCountry()"; >Niger  (NE)</li>
								<li><input type="checkbox" name="country" value="NG" title="Nigeria" onfocus=this.blur() onclick="javascript:setCountry()"; >Nigeria (NG)</li>
								<li><input type="checkbox" name="country" value="OA" title="O.A.P.I." onfocus=this.blur() onclick="javascript:setCountry()"; >O.A.P.I. (OA)</li>
								<li><input type="checkbox" name="country" value="RW" title="Rwanda" onfocus=this.blur() onclick="javascript:setCountry()"; >Rwanda (RW)</li>
								<li><input type="checkbox" name="country" value="ST" title="Sao Tome Principe" onfocus=this.blur() onclick="javascript:setCountry()"; >Sao Tome Principe (ST)</li>
								<li><input type="checkbox" name="country" value="SN" title="Senegal" onfocus=this.blur() onclick="javascript:setCountry()"; >Senegal (SN)</li>
								<li><input type="checkbox" name="country" value="SC" title="Seychelles" onfocus=this.blur() onclick="javascript:setCountry()"; >Seychelles (SC)</li>
								<li><input type="checkbox" name="country" value="SL" title="Sierra Leone" onfocus=this.blur() onclick="javascript:setCountry()"; >Sierra Leone (SL)</li>
								<li><input type="checkbox" name="country" value="SO" title="Somalia" onfocus=this.blur() onclick="javascript:setCountry()"; >Somalia (SO)</li>
								<li><input type="checkbox" name="country" value="ZA" title="South Africa" onfocus=this.blur() onclick="javascript:setCountry()"; >South Africa (ZA)</li>
								<li><input type="checkbox" name="country" value="SD" title="Sudan" onfocus=this.blur() onclick="javascript:setCountry()"; >Sudan (SD)</li>
								<li><input type="checkbox" name="country" value="SZ" title="Swaziland" onfocus=this.blur() onclick="javascript:setCountry()"; >Swaziland (SZ)</li>
								<li><input type="checkbox" name="country" value="TK" title="Tanzania (Tanganyika)" onfocus=this.blur() onclick="javascript:setCountry()"; >Tanzania (Tanganyika) (TK)</li>
								<li><input type="checkbox" name="country" value="ZZZ" title="Tanzania (Zanzibar)" onfocus=this.blur() onclick="javascript:setCountry()"; >Tanzania (Zanzibar) (ZZZ)</li>
								<li><input type="checkbox" name="country" value="TG" title="Togo" onfocus=this.blur() onclick="javascript:setCountry()"; >Togo (TG)</li>
								<li><input type="checkbox" name="country" value="TS" title="Transkei" onfocus=this.blur() onclick="javascript:setCountry()"; >Transkei (TS)</li>
								<li><input type="checkbox" name="country" value="TN" title="Tunisia" onfocus=this.blur() onclick="javascript:setCountry()"; >Tunisia (TN)</li>
								<li><input type="checkbox" name="country" value="UG" title="Uganda" onfocus=this.blur() onclick="javascript:setCountry()"; >Uganda (UG)</li>
								<li><input type="checkbox" name="country" value="BF" title="Upper Volta(Burkina Faso)" onfocus=this.blur() onclick="javascript:setCountry()"; >Upper Volta(Burkina Faso)</li>
								<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(BF)</li>
								<li><input type="checkbox" name="country" value="VD" title="Venda" onfocus=this.blur() onclick="javascript:setCountry()"; >Venda (VD)</li>
								<li><input type="checkbox" name="country" value="ZR" title="Zaire" onfocus=this.blur() onclick="javascript:setCountry()"; >Zaire (ZR)</li>
								<li><input type="checkbox" name="country" value="ZM" title="Zambia" onfocus=this.blur() onclick="javascript:setCountry()"; >Zambia (ZM)</li>
								<li><input type="checkbox" name="country" value="TZ" title="Zanzibar" onfocus=this.blur() onclick="javascript:setCountry()"; >Zanzibar (TZ)</li>
								<li><input type="checkbox" name="country" value="ZW" title="Zimbabwe" onfocus=this.blur() onclick="javascript:setCountry()"; >Zimbabwe (ZW)</li>
								<li><input type="checkbox" name="country" value="TE" title="tangier" onfocus=this.blur() onclick="javascript:setCountry()"; >tangier (TE)</li>
								</ul>
							</td>
							<td>
								<ul>
								<li><input type="checkbox" name="country" value="AU" title="Australia" onfocus=this.blur() onclick="javascript:setCountry()"; >Australia (AU)</li>
								<li><input type="checkbox" name="country" value="FJ" title="Fiji" onfocus=this.blur() onclick="javascript:setCountry()"; >Fiji (FJ)</li>
								<li><input type="checkbox" name="country" value="KI" title="Kiribati" onfocus=this.blur() onclick="javascript:setCountry()"; >Kiribati (KI)</li>
								<li><input type="checkbox" name="country" value="NR" title="Nauru" onfocus=this.blur() onclick="javascript:setCountry()"; >Nauru (NR)</li>
								<li><input type="checkbox" name="country" value="NZ" title="New Zealand" onfocus=this.blur() onclick="javascript:setCountry()"; >New Zealand (NZ)</li>
								<li><input type="checkbox" name="country" value="PG" title="Papua New Guinea" onfocus=this.blur() onclick="javascript:setCountry()"; >Papua New Guinea (PG)</li>
								<li><input type="checkbox" name="country" value="SB" title="Solomon Islands" onfocus=this.blur() onclick="javascript:setCountry()"; >Solomon Islands (SB)</li>
								<li><input type="checkbox" name="country" value="TO" title="Tonga" onfocus=this.blur() onclick="javascript:setCountry()"; >Tonga (TO)</li>
								<li><input type="checkbox" name="country" value="TV" title="Tuvalu" onfocus=this.blur() onclick="javascript:setCountry()"; >Tuvalu (TV)</li>
								<li><input type="checkbox" name="country" value="VU" title="Vanuatu" onfocus=this.blur() onclick="javascript:setCountry()"; >Vanuatu (VU)</li>
								</ul>
							</td>
							<td>
								<ul>
								<li><input type="checkbox" name="country" value="AR" title="Argentina" onfocus=this.blur() onclick="javascript:setCountry()"; >Argentina (AR)</li>
								<li><input type="checkbox" name="country" value="BS" title="Bahamars" onfocus=this.blur() onclick="javascript:setCountry()"; >Bahamars (BS)</li>
								<li><input type="checkbox" name="country" value="BB" title="Barbados" onfocus=this.blur() onclick="javascript:setCountry()"; >Barbados (BB)</li>
								<li><input type="checkbox" name="country" value="BZ" title="Belize" onfocus=this.blur() onclick="javascript:setCountry()"; >Belize (BZ)</li>
								<li><input type="checkbox" name="country" value="BO" title="Bolivia" onfocus=this.blur() onclick="javascript:setCountry()"; >Bolivia (BO)</li>
								<li><input type="checkbox" name="country" value="BR" title="Brazil" onfocus=this.blur() onclick="javascript:setCountry()"; >Brazil (BR)</li>
								<li><input type="checkbox" name="country" value="BM" title="Burmuda" onfocus=this.blur() onclick="javascript:setCountry()"; >Burmuda (BM)</li>
								<li><input type="checkbox" name="country" value="CA" title="Canada" onfocus=this.blur() onclick="javascript:setCountry()"; >Canada (CA)</li>
								<li><input type="checkbox" name="country" value="KY" title="Cayman Islands" onfocus=this.blur() onclick="javascript:setCountry()"; >Cayman Islands (KY)</li>
								<li><input type="checkbox" name="country" value="CL" title="Chile" onfocus=this.blur() onclick="javascript:setCountry()"; >Chile (CL)</li>
								<li><input type="checkbox" name="country" value="CO" title="Colombia" onfocus=this.blur() onclick="javascript:setCountry()"; >Colombia (CO)</li>
								<li><input type="checkbox" name="country" value="CR" title="Costa Rica" onfocus=this.blur() onclick="javascript:setCountry()"; >Costa Rica (CR)</li>
								<li><input type="checkbox" name="country" value="CU" title="Cuba" onfocus=this.blur() onclick="javascript:setCountry()"; >Cuba (CU)</li>
								<li><input type="checkbox" name="country" value="DM" title="Dominican" onfocus=this.blur() onclick="javascript:setCountry()"; >Dominican (DM)</li>
								<li><input type="checkbox" name="country" value="DO" title="Dominican Republic" onfocus=this.blur() onclick="javascript:setCountry()"; >Dominican Republic (DO)</li>
								<li><input type="checkbox" name="country" value="EC" title="Ecuador" onfocus=this.blur() onclick="javascript:setCountry()"; >Ecuador (EC)</li>
								<li><input type="checkbox" name="country" value="SV" title="El Salvador" onfocus=this.blur() onclick="javascript:setCountry()"; >El Salvador (SV)</li>
								<li><input type="checkbox" name="country" value="MK" title="F.Y.R.O.M. (Macedonia)" onfocus=this.blur() onclick="javascript:setCountry()"; >F.Y.R.O.M.</li>
								<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(Macedonia) (MK)</li>
								<li><input type="checkbox" name="country" value="FK" title="Falkland Islands (Malvinas)" onfocus=this.blur() onclick="javascript:setCountry()"; >Falkland Islands</li>
								<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(Malvinas) (FK)</li>
								<li><input type="checkbox" name="country" value="GT" title="Guatemala" onfocus=this.blur() onclick="javascript:setCountry()"; >Guatemala (GT)</li>
								<li><input type="checkbox" name="country" value="GY" title="Guayana" onfocus=this.blur() onclick="javascript:setCountry()"; >Guayana (GY)</li>
								<li><input type="checkbox" name="country" value="HT" title="Haiti" onfocus=this.blur() onclick="javascript:setCountry()"; >Haiti (HT)</li>
								<li><input type="checkbox" name="country" value="HN" title="Honduras" onfocus=this.blur() onclick="javascript:setCountry()"; >Honduras (HN)</li>
								<li><input type="checkbox" name="country" value="JM" title="Jamaica" onfocus=this.blur() onclick="javascript:setCountry()"; >Jamaica (JM)</li>
								<li><input type="checkbox" name="country" value="MX" title="Mexico" onfocus=this.blur() onclick="javascript:setCountry()"; >Mexico (MX)</li>
								<li><input type="checkbox" name="country" value="AN" title="Netherland Antilles" onfocus=this.blur() onclick="javascript:setCountry()"; >Netherland Antilles (AN)</li>
								<li><input type="checkbox" name="country" value="NL" title="Netherlands" onfocus=this.blur() onclick="javascript:setCountry()"; >Netherlands (NL)</li>
								<li><input type="checkbox" name="country" value="NI" title="Nicaragua" onfocus=this.blur() onclick="javascript:setCountry()"; >Nicaragua (NI)</li>
								<li><input type="checkbox" name="country" value="PA" title="Panama" onfocus=this.blur() onclick="javascript:setCountry()"; >Panama (PA)</li>
								<li><input type="checkbox" name="country" value="PY" title="Paraguay" onfocus=this.blur() onclick="javascript:setCountry()"; >Paraguay (PY)</li>
								<li><input type="checkbox" name="country" value="PE" title="Peru" onfocus=this.blur() onclick="javascript:setCountry()"; >Peru (PE)</li>
								<li><input type="checkbox" name="country" value="PO" title="Puerto Rico" onfocus=this.blur() onclick="javascript:setCountry()"; >Puerto Rico (PO)</li>
								<li><input type="checkbox" name="country" value="KN" title="SAINT KITTS AND NEVIS" onfocus=this.blur() onclick="javascript:setCountry()"; >SAINT KITTS AND NEVIS</li>
								<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(KN)</li>
								<li><input type="checkbox" name="country" value="CP" title="Saint Christopher Nevis" onfocus=this.blur() onclick="javascript:setCountry()"; >Saint Christopher Nevis</li>
								<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(CP)</li>
								<li><input type="checkbox" name="country" value="SH" title="Saint Helena" onfocus=this.blur() onclick="javascript:setCountry()"; >Saint Helena (SH)</li>
								<li><input type="checkbox" name="country" value="LC" title="Saint Lucia" onfocus=this.blur() onclick="javascript:setCountry()"; >Saint Lucia (LC)</li>
								<li><input type="checkbox" name="country" value="VC" title="Saint Vincent" onfocus=this.blur() onclick="javascript:setCountry()"; >Saint Vincent (VC)</li>
								<li><input type="checkbox" name="country" value="SR" title="Suriname" onfocus=this.blur() onclick="javascript:setCountry()"; >Suriname (SR)</li>
								<li><input type="checkbox" name="country" value="TT" title="Trinidad and Tobago" onfocus=this.blur() onclick="javascript:setCountry()"; >Trinidad and Tobago (TT)</li>
								<li><input type="checkbox" name="country" value="UY" title="Uruguay" onfocus=this.blur() onclick="javascript:setCountry()"; >Uruguay (UY)</li>
								<li><input type="checkbox" name="country" value="VE" title="Venezuela" onfocus=this.blur() onclick="javascript:setCountry()"; >Venezuela (VE)</li>
</ul>
							</td>
						</tr>
					</tbody>
				</table>
				<ul>
					<li>
						
					</li>

				</ul>
			</div>
		<!-- choowrap -->
		</div>
		<!--// pop_content -->
	</div>
</div>
<!--footer -->
<div class="pop_footer">
	<!-- button -->
	 <div class="btn_wrap fR mt20">
     	 <span class="btn_all_w"><span class="cancel"></span><a href="#" onclick="javascript:self.close();"><spring:message code="las.page.button.lawservice.close"/></a></span>
	 </div>
<!-- //button -->
</div>
<!-- //footer -->
</body>

</html></html>