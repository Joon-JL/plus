<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.sds.secframework.common.util.StringUtil"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<%-- 
/**
 * 파  일  명 : Arbitrary_l.jsp
 * 프로그램명 : 체결규정화면
 * 설      명 : 
 * 작  성  자 : 박정훈
 * 작  성  일 : 2013.04
 */
--%>

<%
String viewFlag = "";
String blngt_orgnz = StringUtil.bvl((String)session.getAttribute("secfw.session.blngt_orgnz"), "");

if("B00000001".equals(blngt_orgnz)){ //영상디스플레이사업부
	viewFlag = "VD";
}else if("B00000002".equals(blngt_orgnz)){ //무선사업부
	viewFlag = "MC";
}else if("B00000003".equals(blngt_orgnz)){ //메모리사업부
	viewFlag = "ME";
}else if("B00000017".equals(blngt_orgnz)){ //Media Solution 센터
	viewFlag = "MSC";
}else if("B00000019".equals(blngt_orgnz)){ //한국총괄
	viewFlag = "KR";
}else if("B00000046".equals(blngt_orgnz)){ //전자 스포츠구단
	viewFlag = "ST";
}else if("B00000041".equals(blngt_orgnz)){ //종합기술원
	viewFlag = "AIT";
}else if("B00000025".equals(blngt_orgnz)){ //제조기술센터
	viewFlag = "PT";
}

%>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title>::: <spring:message code="clm.page.msg.common.clms" /> :::</title>
<link href="<%=CSS%>/las.css" type="text/css" rel="stylesheet"/>
<!-- [if IE]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
<script language="javascript" src="/script/clms/common.js" type="text/javascript"></script>
</head>
<body>
<div id="wrap">
	<!-- container -->
	<div id="container">
		<!-- location -->
		<div class="location"><img src="<%=IMAGE%>/icon/ico_home.gif" width="13" height="11" border="0" alt="Home"/> <spring:message code="clm.page.msg.rule.contManRule" /> > <strong> <spring:message code="clm.page.msg.rulearbitraryRule" /></strong></div>
		<!-- //location -->
		
		<!-- content -->

<%
	if("VD".equals(viewFlag)){
%>	
	<!-- title -->
	<div class="title">
		<h3><spring:message code="clm.page.msg.rulearbitraryPathStd" /></h3>
	</div>
	<!-- //title -->
	<!-- content -->
	<div id="content">
	<div id="content_in">
	<div style="clear: both; width:100%;">
	  <div class="page_list">
      	  <div class="in">
	    <ul>
	      <spring:message code="clm.page.msg.rule.announce021" />
	      <li><spring:message code="clm.page.msg.rule.announce022" /></li>
	      <li><spring:message code="clm.page.msg.rule.announce017" /></li>
        </ul>
	    <ul>
	      <spring:message code="clm.page.msg.ruleapprovalPathConsul" />
	      <li><em><spring:message code="clm.page.msg.rule.announce007" /></em><br />
	        <em><spring:message code="clm.page.msg.rule.announce031" /></em><br />
	        <em><spring:message code="clm.page.msg.rule.announce005" /></em><br />
	        <em><spring:message code="clm.page.msg.rule.announce001" /></em></li>
	      <li><spring:message code="clm.page.msg.rule.announce009" /> <spring:message code="clm.page.msg.rule.announce018" /></li>
	      <li><spring:message code="clm.page.msg.rule.announce004" /></li>
	      <li><spring:message code="clm.page.msg.rule.announce003" /></li>
	      <li><spring:message code="clm.page.msg.rule.announce016" /></li>
	      <li><spring:message code="clm.page.msg.rule.announce030" /> </li>
        </ul>
	    <span>※ <spring:message code="clm.page.msg.rule.announce031" /> </span>
       </div>
      </div>
    </div>
	<!-- title -->
	
	<br/>
	<div class="title mt15" style="margin-bottom:5px ;padding-bottom: 5px;">
		<h3><spring:message code="clm.page.msg.rulespecificRule" /></h3>
    </div>        
		<table class="list_basic tr_nobg" cellspacing="0" cellpadding="0" style="white-space:nowrap; min-width: 1200px;">
		 <colgroup>
         <col width="12%" />
		  <col width="15%" />
		  <col width="10%" />
		  <col width="6%" />
		  <col width="5%" />
		  <col width="5%" />
		  <col width="5%" />
		  <col width="5%" />                                        
		  <col width="8%" />
		  <col width="7%" /> 
   		  <col width="6%" /> 
		  <col width="6%" />           
		  <col width="10%" />
          </colgroup>
		    <tr>
		      <th rowspan="2" align="center"><spring:message code="clm.page.msg.rule.contType1" /></th>
		      <th rowspan="2" align="center"><spring:message code="clm.page.msg.rule.contType2" /></th>
		      <th rowspan="2" align="center"><spring:message code="clm.page.msg.rulearbitraryStd" /></th>
		      <th colspan="5" align="center" class="line"><spring:message code="clm.page.msg.rulearbitraryMngr" /></th>
		      <th class="line"><spring:message code="clm.page.msg.rule.funcStaf" /></th>
		      <th colspan="3" align="center" class="line"><spring:message code="clm.page.msg.rulesupportTeamAgree" /></th>
		      <th align="center" class="line"><spring:message code="clm.page.msg.rule.contact004" /></th>
	        </tr>
		    <tr>
		      <th class="sub" style="white-space:nowrap"><spring:message code="clm.page.msg.rule.grpMaster" /><br />
		        <spring:message code="clm.page.msg.rule.executive" /></th>
		      <th class="sub" style="white-space:nowrap"><spring:message code="clm.page.msg.rule.teamMngr" /></th>
		      <th class="sub" style="white-space:nowrap"><spring:message code="clm.page.msg.rule.business" /><br />
		        <spring:message code="clm.page.msg.rule.head" /></th>
		      <th class="sub" style="white-space:nowrap">CFO</th>
		      <th class="sub" style="white-space:nowrap">CEO</th>
		      <th class="sub" style="white-space:nowrap"><spring:message code="clm.page.msg.common.agreement" /></th>
		      <th class="sub" style="white-space:nowrap"><spring:message code="clm.page.msg.rulepartMngr" /> </th>
		      <th class="sub" style="white-space:nowrap"><spring:message code="clm.page.msg.rule.grpMaster" /> </th>
		      <th class="sub" style="white-space:nowrap"><spring:message code="clm.page.msg.rule.teamMngr" /> </th>
		      <th class="sub" style="white-space:nowrap"><spring:message code="clm.page.msg.common.agreement" /></th>
	        </tr>
	  </table>
          <div style="overflow-x:auto; overflow-y: auto; height:240px;">              
		<table class="list_basic tr_nobg" cellspacing="0" cellpadding="0" style="border-top: 0;white-space:nowrap; min-width: 1200px;">
		  <tbody>
	      </tbody>
		 <colgroup>
         <col width="12%" />
		  <col width="15%" />
		  <col width="10%" />
		  <col width="6%" />
		  <col width="5%" />
		  <col width="5%" />
		  <col width="5%" />
		  <col width="5%" />                                        
		  <col width="8%" />
		  <col width="7%" /> 
   		  <col width="6%" /> 
		  <col width="6%" />           
		  <col width="10%" />
          </colgroup>
		  <tbody>
		    <tr>
		      <td rowspan="3" class="Ncolor01"><spring:message code="clm.page.msg.common.dev" /></td>
		      <td rowspan="3" class="td_col_03 "><spring:message code="clm.page.msg.outsrcDev" /><br />
		        <spring:message code="clm.page.msg.rule.consortium" /><br />
		        <spring:message code="clm.page.msg.rule.devSupport" /><br />
		        <spring:message code="clm.page.msg.ruleevalWorker" /><br />
		        <spring:message code="clm.page.msg.rule.devMaint" /><br />
		        <spring:message code="clm.page.msg.common.etc" /></td>
		      <td height="27" align="center"><spring:message code="clm.page.msg.rule.above300mill" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td rowspan="3"><spring:message code="clm.page.msg.rule.techMark" /><br />
	          / <spring:message code="clm.page.msg.hr" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td></td>
	        </tr>
		    <tr>
		      <td align="center" class="tal_lineL"><spring:message code="clm.page.msg.rule.und300mill" /></td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td></td>
	        </tr>
		    <tr>
		      <td align="center" class="tal_lineL"><spring:message code="clm.page.msg.rule.und100millPlan" /></td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td></td>
	        </tr>
		    <tr>
		      <td rowspan="5" class="Ncolor01"><spring:message code="clm.page.msg.common.patent" /></td>
		      <td rowspan="5" class="td_col_03"><spring:message code="clm.page.msg.rule.buySell" /><br />
		        <spring:message code="clm.page.msg.rule.licenseCross" /><br />
		        <spring:message code="clm.page.msg.rule.licensePerm" /></td>
		      <td><spring:message code="clm.page.msg.rulereturnEquity2" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td rowspan="5"><spring:message code="clm.page.msg.rule.ipCenter" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>○</td>
	        </tr>
		    <tr>
		      <td class="tal_lineL"><spring:message code="clm.page.msg.rule.above10bill" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td><spring:message code="clm.page.msg.rule.report" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>○</td>
	        </tr>
		    <tr>
		      <td class="tal_lineL"><spring:message code="clm.page.msg.rule.above300mill" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td class="tal_lineL"><spring:message code="clm.page.msg.rule.und300mill" /></td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td class="tal_lineL"><spring:message code="clm.page.msg.rule.und100millPlan" /></td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td rowspan="6" class="Ncolor01"><spring:message code="clm.page.msg.rule.license" /></td>
		      <td rowspan="5" class="td_col_03"><spring:message code="clm.page.msg.rule.bring" /><br />
		        <spring:message code="clm.page.msg.ci" /><br />
		        <spring:message code="clm.page.msg.common.etc" /></td>
		      <td><spring:message code="clm.page.msg.rulereturnEquity2" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td rowspan="5"><spring:message code="clm.page.msg.rule.ipCenter" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>○</td>
	        </tr>
		    <tr>
		      <td class="tal_lineL"><spring:message code="clm.page.msg.rule.above10bill" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td><spring:message code="clm.page.msg.rule.report" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>○</td>
	        </tr>
		    <tr>
		      <td class="tal_lineL"><spring:message code="clm.page.msg.rule.above300mill" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td class="tal_lineL"><spring:message code="clm.page.msg.rule.und300mill" />↓</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td class="tal_lineL"><spring:message code="clm.page.msg.rule.und100millPlan" /></td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td class=" td_col_03 tal_lineL"><spring:message code="clm.page.msg.rule.ipPreTest" /></td>
		      <td>-</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td rowspan="3" class="Ncolor01">Purchase</td>
		      <td rowspan="2" class="td_col_03"><spring:message code="clm.page.msg.rule.bscTradeCont" /></td>
		      <td><spring:message code="clm.page.msg.ruleelecContract" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td class="tal_lineL"><spring:message code="clm.page.msg.rule.memoirs" /></td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td class="td_col_03 tal_lineL"><spring:message code="clm.page.msg.rule.buyService" /><br />
		        <spring:message code="clm.page.msg.rule.buyHelp" /><br />
		        <spring:message code="clm.page.msg.rule.buyMaint" /><br />
		        <spring:message code="clm.page.msg.common.etc" /></td>
		      <td><spring:message code="clm.page.msg.rule.noAmt" /></td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td rowspan="3" class="Ncolor01"><spring:message code="clm.page.msg.marketing" /></td>
		      <td rowspan="3" class="td_col_03"><spring:message code="clm.page.msg.rule.adv" /><br />
		        <spring:message code="clm.page.msg.rule.sponPrEvent" /><br />
		        Consumer Promotion,<br />
		        Channel Enhancement</td>
		      <td align="center"><spring:message code="clm.page.msg.rule.above100mill" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td></td>
	        </tr>
		    <tr>
		      <td align="center" class="tal_lineL"><spring:message code="clm.page.msg.rule.und100mill" /></td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td></td>
	        </tr>
		    <tr>
		      <td align="center" class="tal_lineL"><spring:message code="clm.page.msg.rule.under10millPlan" /></td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td class="Ncolor01"><spring:message code="clm.page.msg.rulemanufacture" /></td>
		      <td class="td_col_03">ODM<br />
		        <spring:message code="clm.page.msg.rule.oem" /><br />
		        <spring:message code="clm.page.msg.rulemanufacSuppWorker" /><br />
		        <spring:message code="clm.page.msg.common.etc" /></td>
		      <td>-</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td><spring:message code="clm.page.msg.rule.briefing" /></td>
		      <td>&nbsp;</td>
		      <td><spring:message code="clm.page.msg.rule.global" /><br />
		        <spring:message code="clm.page.msg.operation" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td><spring:message code="clm.page.msg.common.review" /></td>
	        </tr>
		    <tr>
		      <td rowspan="4" class="Ncolor01"><spring:message code="clm.page.msg.rule.distribution" /></td>
		      <td rowspan="2" class="td_col_03"><spring:message code="clm.page.msg.transportation" /></td>
		      <td align="center"><spring:message code="clm.page.msg.rule.above100mill" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td align="center" class="tal_lineL"><spring:message code="clm.page.msg.rule.und100mill" /></td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td rowspan="2" width="145" class="td_col_03 tal_lineL"><spring:message code="clm.page.msg.rulestorage" /><br />
		        Agency<br />
		        <spring:message code="clm.page.msg.common.etc" /></td>
		      <td align="center"><spring:message code="clm.page.msg.rule.above100mill" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td align="center" class="tal_lineL"><spring:message code="clm.page.msg.rule.und100mill" /></td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td rowspan="6" class="Ncolor01"><spring:message code="clm.page.msg.rule.serviceQual" /><br />
		        /
		        <spring:message code="clm.page.msg.ruleenvironment" />
		        (ESH)</td>
		      <td rowspan="3" class="td_col_03"><spring:message code="clm.page.msg.rule.serviceComiss" /><br />
		        <spring:message code="clm.page.msg.common.trustTest" /><br />
		        <spring:message code="clm.page.msg.rule.return" /><br />
		        <spring:message code="clm.page.msg.common.qa" /><br />
		        <spring:message code="clm.page.msg.common.etc" /></td>
		      <td align="center"><spring:message code="clm.page.msg.rule.above300mill" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td align="center" class="tal_lineL"><spring:message code="clm.page.msg.rule.und300mill" /></td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td align="center" class="tal_lineL"><spring:message code="clm.page.msg.rule.und100millPlan" /></td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td rowspan="3" class="td_col_03 tal_lineL"><spring:message code="clm.page.msg.ruleenvPresSec" /></td>
		      <td align="center"><spring:message code="clm.page.msg.rule.above300mill" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td align="center" class="tal_lineL"><spring:message code="clm.page.msg.rule.und300mill" /></td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td align="center" class="tal_lineL"><spring:message code="clm.page.msg.rule.und100millPlan" /></td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td rowspan="3" class="Ncolor01"><spring:message code="clm.page.msg.hrMan" /></td>
		      <td rowspan="3" class="td_col_03 tal_lineL"><spring:message code="clm.page.msg.hrManager" /></td>
		      <td align="center"><spring:message code="clm.page.msg.rule.above300mill" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td align="center" class="tal_lineL"><spring:message code="clm.page.msg.rule.und300mill" /></td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td align="center" class="tal_lineL"><spring:message code="clm.page.msg.rule.und100millPlan" /></td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td rowspan="5" class="Ncolor01"><spring:message code="clm.page.msg.rule.construction" /></td>
		      <td rowspan="2" class="td_col_03"><spring:message code="clm.page.msg.rule.construction" /></td>
		      <td align="center"><spring:message code="clm.page.msg.rule.above50bill" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td><spring:message code="clm.page.msg.rule.agreement" /></td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>○</td>
	        </tr>
		    <tr>
		      <td align="center" class="tal_lineL"><spring:message code="clm.page.msg.rule.und50bill" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td rowspan="3" class="td_col_03 tal_lineL"><spring:message code="clm.page.msg.rule.construction" /><br />
		        <spring:message code="clm.page.msg.ci" /></td>
		      <td align="center"><spring:message code="clm.page.msg.rule.und500mill" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td align="center" class="tal_lineL"><spring:message code="clm.page.msg.rule.100millto0p5bill" /></td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td align="center" class="tal_lineL"><spring:message code="clm.page.msg.rule.und100mill" /></td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td rowspan="9" class="Ncolor01"><spring:message code="clm.page.msg.rulealliance" />/
		        <spring:message code="clm.page.msg.ruleinvestment" />/ <br />
		        <spring:message code="clm.page.msg.manage.finance" /></td>
		      <td rowspan="2" class="td_col_03"><spring:message code="clm.page.msg.ruleallianceCollab" /></td>
		      <td align="center"><spring:message code="clm.page.msg.rule.above10mill" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td rowspan="2"><spring:message code="clm.page.msg.rule.lab" /><br />
		        <spring:message code="clm.page.msg.rule.planTeam" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>○</td>
	        </tr>
		    <tr>
		      <td align="center" class="tal_lineL"><spring:message code="clm.page.msg.rule.under10mill" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>○</td>
	        </tr>
		    <tr>
		      <td rowspan="4" class="td_col_03 tal_lineL"><spring:message code="clm.page.msg.rulebuyoutInvest" /></td>
		      <td><spring:message code="clm.page.msg.rule.mnaOne" /><br />
		        <spring:message code="clm.page.msg.rule.more2p5pcnt" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>△</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>○</td>
	        </tr>
		    <tr>
		      <td class="tal_lineL"><spring:message code="clm.page.msg.rule.above50billmna" /> <br />
		        <spring:message code="clm.page.msg.rulereturnEquity1" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>△</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>○</td>
	        </tr>
		    <tr>
		      <td class="tal_lineL"><spring:message code="clm.page.msg.rule.und50billmna" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>○</td>
	        </tr>
		    <tr>
		      <td class="tal_lineL"><spring:message code="clm.page.msg.rule.und5billmna" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td><spring:message code="clm.page.msg.rule.briefing" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>○</td>
	        </tr>
		    <tr>
		      <td rowspan="3" class="td_col_03 tal_lineL"><spring:message code="clm.page.msg.rule.insFin" /></td>
		      <td align="center"><spring:message code="clm.page.msg.rule.above2bill" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>○</td>
	        </tr>
		    <tr>
		      <td align="center" class="tal_lineL"><spring:message code="clm.page.msg.rule.10millto2bill" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>(○)</td>
	        </tr>
		    <tr>
		      <td align="center" class="tal_lineL"><spring:message code="clm.page.msg.rule.under10mill" /></td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td rowspan="2" class="Ncolor01"><spring:message code="clm.page.msg.ruleconsutInvesti" /></td>
		      <td rowspan="2" class="td_col_03"><spring:message code="clm.page.msg.ruleconsulting" /><br />
		        <spring:message code="clm.page.msg.rule.research" /><br />
		        <spring:message code="clm.page.msg.ruleuseInfo" /><br />
		        <spring:message code="clm.page.msg.common.etc" /></td>
		      <td align="center"><spring:message code="clm.page.msg.rule.above100mill" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td rowspan="2"><spring:message code="clm.page.msg.rule.marketing" /><br />
		        <spring:message code="clm.page.msg.rule.plan" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>○</td>
	        </tr>
		    <tr>
		      <td align="center" class="tal_lineL"><spring:message code="clm.page.msg.rule.und100mill" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>(○)</td>
	        </tr>
		    <tr>
		      <td rowspan="6" class="Ncolor01">IT Service</td>
		      <td rowspan="3" class="td_col_03"><spring:message code="clm.page.msg.rule.itInvestment" /></td>
		      <td align="center"><spring:message code="clm.page.msg.rule.und500mill" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td rowspan="3"><spring:message code="clm.page.msg.common.info" /><br/>
		        <spring:message code="clm.page.msg.rulestrategyTeam" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td></td>
	        </tr>
		    <tr>
		      <td align="center" class="tal_lineL"><spring:message code="clm.page.msg.rule.100millto0p5bill" /></td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td></td>
	        </tr>
		    <tr>
		      <td align="center" class="tal_lineL"><spring:message code="clm.page.msg.rule.und100mill" /></td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td></td>
	        </tr>
		    <tr>
		      <td rowspan="3" class="td_col_03 tal_lineL"><spring:message code="clm.page.msg.rule.itMaintenance" /><br />
		        <spring:message code="clm.page.msg.rule.itService" /><br />
		        <spring:message code="clm.page.msg.common.etc" /></td>
		      <td align="center"><spring:message code="clm.page.msg.rule.und500mill" /></td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td align="center" class="tal_lineL"><spring:message code="clm.page.msg.rule.100millto0p5bill" /></td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
	        </tr>
		    <tr>
		      <td align="center" class="tal_lineL"><spring:message code="clm.page.msg.rule.und100mill" /></td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
		      <td>○</td>
		      <td>&nbsp;</td>
		      <td>&nbsp;</td>
	        </tr>
	      </tbody>
		  <tbody>
	      </tbody>
		  </table>
		</div>
		</div>
	</div>
	<!-- //content -->

<%
	}else if("MC".equals(viewFlag)){ //무선사업부
%>
		<!-- content -->
		<div id="content">
			<div id="content_in">
			<!-- title -->
			<div class="title">
				<h3><spring:message code="clm.page.msg.rulectrtDecisionStd" /></h3>
			</div>
			<!-- //title -->
			
			<div class='standard_bg' style=''>
				
				<div class='standard_in'>
					<h1><img src="<%=IMAGE%>/new2011/iconSu1.gif" border="0" alt="01"> <spring:message code="clm.page.msg.rule.announce021" /> </h1>
				 	<span><img src="<%=IMAGE%>/icon/bu_circle_gray.gif" border="0" alt="ball"> <spring:message code="clm.page.msg.rule.announce023" /></span><br>
				    <span><img src="<%=IMAGE%>/icon/bu_circle_gray.gif" border="0" alt="ball"> <spring:message code="clm.page.msg.rule.announce017" /></span><br><br>
				
				 
					<h1><img src="<%=IMAGE%>/new2011/iconSu2.gif" border="0" alt="02"> <spring:message code="clm.page.msg.ruleapprovalPathConsul" /> </h1>
				
				    <span><img src="<%=IMAGE%>/icon/bu_circle_gray.gif" border="0" alt="ball"> <spring:message code="clm.page.msg.rule.announce006" /></span><br>
				
				 
				
				    <span><img src="<%=IMAGE%>/icon/bu_circle_gray.gif" border="0" alt="ball"> <spring:message code="clm.page.msg.rule.announce031" /> </span><br>
				    <span style='margin-left:24px;'><spring:message code="clm.page.msg.rule.announce005" /></span></span><br>
				    <span style='margin-left:24px;'><spring:message code="clm.page.msg.rule.announce002" /></span><br>
				
				   
				    <span><img src="<%=IMAGE%>/icon/bu_circle_gray.gif" border="0" alt="ball"> <spring:message code="clm.page.msg.rule.announce009" /></span><br>
				    <span style='margin-left:24px;'><spring:message code="clm.page.msg.rule.announce018" /></span><br>
				    <span><img src="<%=IMAGE%>/icon/bu_circle_gray.gif" border="0" alt="ball"> <spring:message code="clm.page.msg.rule.announce004" /> </span><br>
				    <span><img src="<%=IMAGE%>/icon/bu_circle_gray.gif" border="0" alt="ball"> <spring:message code="clm.page.msg.rule.announce003" /> </span><br>
				    <span><img src="<%=IMAGE%>/icon/bu_circle_gray.gif" border="0" alt="ball"> <spring:message code="clm.page.msg.rule.announce016" /> </span><br>
				    <span><img src="<%=IMAGE%>/icon/bu_circle_gray.gif" border="0" alt="ball"> <spring:message code="clm.page.msg.rule.announce029" /> </span><br>
				 
				
					<span class='alert'><img src="<%=IMAGE%>/new2011/ico_alert.gif" border="0" alt="01"> <spring:message code="clm.page.msg.rule.announce024" /></span><br>
				</div>
				
			</div>
			
		</div>
		</div>
		<!-- //content -->
<%
	}else if("ME".equals(viewFlag)){ //메모리사업부
%>			
	<div id="content">
		<div id="content_in">
			<br/>
			<br/>
			<spring:message code="clm.page.msg.rule.announce028" />
		</div>
	</div>
<%	
	}else if("MSC".equals(viewFlag)){ //Media Solution 센터
%>
	<div id="content">
		<div id="content_in">
			<br/>
			<br/>
			<spring:message code="clm.page.msg.rule.announce028" />
		</div>
	</div>
<%		
	}else if("KR".equals(viewFlag)){ //한국총괄
%>
		<!-- content -->
		<div id="content">
			<div id="content_in">
			<!-- title -->
			<div class="title">
				<h3><spring:message code="clm.page.msg.rulectrtDecisionStd" /></h3>	
			</div>
			<!-- //title -->
			
			<div class='standard_bg' style=''>
				
				<div class='standard_in'>
					<h1><img src="<%=IMAGE%>/new2011/iconSu1.gif" border="0" alt="01"> <spring:message code="clm.page.msg.rule.announce027" /></h1>
				 	<span><img src="<%=IMAGE%>/icon/bu_circle_gray.gif" border="0" alt="ball"> <spring:message code="clm.page.msg.rule.announce023" /></span><br>
				    <span><img src="<%=IMAGE%>/icon/bu_circle_gray.gif" border="0" alt="ball"> <spring:message code="clm.page.msg.rule.announce017" /></span><br><br>
				
					<h1><img src="<%=IMAGE%>/new2011/iconSu2.gif" border="0" alt="02"> <spring:message code="clm.page.msg.ruleapprovalPathConsul" /></h1>
				
				    <span><img src="<%=IMAGE%>/icon/bu_circle_gray.gif" border="0" alt="ball"> <spring:message code="clm.page.msg.rule.announce008" /></span><br>
			    	<span style='margin-left:22px;'><spring:message code="clm.page.msg.rule.announce015" /></span><br>
			    	<span style='margin-left:22px;'><spring:message code="clm.page.msg.rule.announce025" /></span> <br>
				
				    <span><img src="<%=IMAGE%>/icon/bu_circle_gray.gif" border="0" alt="ball"> <strong style='color:#D8175B'>NDA :</strong> <spring:message code="clm.page.msg.rule.announce012" /></span><br>
				    <span><img src="<%=IMAGE%>/icon/bu_circle_gray.gif" border="0" alt="ball"> <strong style='color:#D8175B'>MOU/LOI :</strong> <spring:message code="clm.page.msg.rule.announce013" /></span><br>
				    <span><img src="<%=IMAGE%>/icon/bu_circle_gray.gif" border="0" alt="ball"> <strong style='color:#D8175B'><spring:message code="clm.page.msg.rule.chgCont" /> :</strong> <spring:message code="clm.page.msg.rule.announce019" /></span><br>
				    <span><img src="<%=IMAGE%>/icon/bu_circle_gray.gif" border="0" alt="ball"> <strong style='color:#D8175B'><spring:message code="clm.page.msg.rulecancelContract" /> :</strong> <spring:message code="clm.page.msg.rule.announce020" /></span><br>
				</div>
			</div>
			</div>
		</div>
		<!-- //content -->		
<%
	}else if("ST".equals(viewFlag)){ //전자스포츠구단
%>
		<!-- content -->
		<div id="content">
			<div id="content_in">
			<!-- title -->
			<div class="title">
				<h3><spring:message code="clm.page.msg.rulectrtDecisionStd" /></h3>
			</div>
			<!-- //title -->
			
			<div class='standard_bg' style=''>
			
				<div class='standard_in'>
					<h1><img src="<%=IMAGE%>/new2011/iconSu1.gif" border="0" alt="01"> <spring:message code="clm.page.msg.rule.entClms" /> <span style='text-decoration:underline;margin-left:0;color:#DB2F70'><spring:message code="clm.page.msg.rule.basketBallTeam" /></span> <spring:message code="clm.page.msg.rule.apprPath" /></h1>
				 	<span><img src="<%=IMAGE%>/icon/bu_circle_gray.gif" border="0" alt="ball"> <span style='margin-right:18px; margin-left:0'><spring:message code="clm.page.msg.rule.makePlan" /></span> : <spring:message code="clm.page.msg.contact001" /></span><br>
				    <span><img src="<%=IMAGE%>/icon/bu_circle_gray.gif" border="0" alt="ball"> <span style='margin-right:18px; margin-left:0'><spring:message code="clm.page.msg.rule.approve" /></span> : <spring:message code="clm.page.msg.rulesecretGeneral" /></span><br>
					<span><img src="<%=IMAGE%>/icon/bu_circle_gray.gif" border="0" alt="ball"> <spring:message code="clm.page.msg.rule.secAppr" /> : <spring:message code="clm.page.msg.contact002" /></span><br>
					<span class='alert'><img src="<%=IMAGE%>/new2011/ico_alert.gif" border="0" alt="01"> <spring:message code="clm.page.msg.rule.announce010" /></span><br>
				</div>
				
			</div>
			</div>			
		</div>
		<!-- //content -->
<%		
	}else if("AIT".equals(viewFlag)){ //종합기술원
%>
		<!-- content -->
		<div id="content">
		<div id="content_in">
			<!-- title -->
			<div class="title">
				<h3><spring:message code="clm.page.msg.rulectrtDecisionStd" /></h3>
			</div>
			<!-- //title -->
			
			<style>
				.standard_bg_top {clear:both;width:1150px; height:100px; background:url(<%=IMAGE%>/new2011/standard_top.gif) no-repeat;}
				.standard_bg_mid {clear:both;width:1150px; height:; background:url(<%=IMAGE%>/new2011/standard_mid.gif) repeat-y;}
				.standard_bg_btm {clear:both;width:1150px; height:35px; background:url(<%=IMAGE%>/new2011/standard_btm.gif) no-repeat;}
				.standard_in2 {position:relative; top:10px; left:35px;font:normal 12px nanumgothic; line-height:180%; }
			</style>
			
			<div class='standard_bg_top'></div>
			<div class='standard_bg_mid'>
				
				<div class='standard_in2'>
					<img src="<%=IMAGE%>/new2011/ttc_standard01.gif" border="0" /><br><br><br>
					<img src="<%=IMAGE%>/new2011/ttc_standard02.gif" border="0" /><br><br>
					<img src="<%=IMAGE%>/new2011/ttc_standard03.gif" border="0"/><br><br>
					<img src="<%=IMAGE%>/new2011/ttc_standard04.gif" border="0"/><br><br><br>
					<img src="<%=IMAGE%>/new2011/ttc_standard05.gif" border="0"/><br><br><br>
					<img src="<%=IMAGE%>/new2011/ttc_standard06.gif" border="0"/><br><br><br>
					<img src="<%=IMAGE%>/new2011/ttc_standard07.gif" border="0"/><br><br><br>
					<img src="<%=IMAGE%>/new2011/ttc_standard08.gif" border="0"/><br><br><br>
					<img src="<%=IMAGE%>/new2011/ttc_standard09.gif" border="0"/><br><br>
					<img src="<%=IMAGE%>/new2011/ttc_standard10.gif" border="0"/><br><br><br>
					<img src="<%=IMAGE%>/new2011/ttc_standard11.gif" border="0"/><br><br><br>
					<img src="<%=IMAGE%>/new2011/ttc_standard12.gif" border="0"/><br><br><br>
					<img src="<%=IMAGE%>/new2011/ttc_standard13.gif" border="0"/><br><br><br>
					<img src="<%=IMAGE%>/new2011/ttc_standard14.gif" border="0"/><br><br><br>
				</div>
				
			</div>
			<div class='standard_bg_btm'></div>
			</div>	
		</div>
		<!-- //content -->
<%		
	}else if("PT".equals(viewFlag)){
%>
		<!-- content -->
		<div id="content">
			<div id="content_in">
			<!-- title -->
			<div class="title">
				<h3><spring:message code="clm.page.msg.rulectrtDecisionStd" /></h3>
			</div>
			<!-- //title -->
			
			<div class='standard_bg' style=''>
				
				<div class='standard_in'>
					<h1><img src="<%=IMAGE%>/new2011/iconSu1.gif" border="0" alt="01"> <spring:message code="clm.page.msg.rule.eduIndCont" /></h1>
				 	<span><img src="<%=IMAGE%>/icon/bu_circle_gray.gif" border="0" alt="ball"> <spring:message code="clm.page.msg.rule.pl" /> → <spring:message code="clm.page.msg.rule.teamMngrApproval" /> → <spring:message code="clm.page.msg.rule.techMarkPartAgree" /> → <spring:message code="clm.page.msg.rule.contact003" /></span><br><br>
				 	
				 	<h1><img src="<%=IMAGE%>/new2011/iconSu2.gif" border="0" alt="02"> NDA</h1>
				 	<span><img src="<%=IMAGE%>/icon/bu_circle_gray.gif" border="0" alt="ball"> <spring:message code="clm.page.msg.rule.pl" /> → <spring:message code="clm.page.msg.rule.teamMngrApproval" /> → <spring:message code="clm.page.msg.rule.techMarkPartAgree" /></span><br><br>
				 	
				 	<h1><img src="<%=IMAGE%>/new2011/iconSu3.gif" border="0" alt="03"> <spring:message code="clm.page.msg.rule.counselCont" /></h1>
				 	<span><img src="<%=IMAGE%>/icon/bu_circle_gray.gif" border="0" alt="ball"> <b><spring:message code="clm.page.msg.rule.suwonGumi" /></b> <span style='margin-left:4px;'></span>: <spring:message code="clm.page.msg.rule.contPerPro" /> → <spring:message code="clm.page.msg.rule.teamMngrApproval" /> → <spring:message code="clm.page.msg.contact004" /> → <spring:message code="clm.page.msg.rule.contact001" /></span><br>
				 	<span><img src="<%=IMAGE%>/icon/bu_circle_gray.gif" border="0" alt="ball"> <b><spring:message code="clm.page.msg.rule.gwangju" /></b> <span style='margin-left:33px;'></span>: <spring:message code="clm.page.msg.rule.contPerPro" /> → <spring:message code="clm.page.msg.rule.teamMngrApproval" /> → <spring:message code="clm.page.msg.contact003" /> → <spring:message code="clm.page.msg.rule.contact002" /></span><br><br>
				 	
				 	<h1><img src="<%=IMAGE%>/new2011/iconSu4.gif" border="0" alt="04"> <spring:message code="clm.page.msg.rule.buyCont" /></h1>
				 	<span><img src="<%=IMAGE%>/icon/bu_circle_gray.gif" border="0" alt="ball"> <spring:message code="clm.page.msg.rule.buyCont" /> → <spring:message code="clm.page.msg.rule.buyPartAppr" /> → <spring:message code="clm.page.msg.rule.grpMasterAppr" /></span><br><br>
				 	
				 	<h1><img src="<%=IMAGE%>/new2011/iconSu5.gif" border="0" alt="05"> <spring:message code="clm.page.msg.rule.techTrans" /></h1>
				 	<span><img src="<%=IMAGE%>/icon/bu_circle_gray.gif" border="0" alt="ball"> <spring:message code="clm.page.msg.rule.contPerPro" /> → <spring:message code="clm.page.msg.rulemanagerApproval" /> → <spring:message code="clm.page.msg.rule.announce011" /></span><br><br>
				 </div>
				
			</div>
			</div>
		</div>
		<!-- //content -->		
<%		
	}else{
%>
	<div id="content">
		<div id="content_in">
			<br/>
			<br/>
			<spring:message code="clm.page.msg.rule.announce028" />
		</div>
	</div>
<%		
	}
%>	
	</div>
	<!-- //container -->
</div>
	<!-- footer  -->
	<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
	<!-- // footer -->
</body>
</html>