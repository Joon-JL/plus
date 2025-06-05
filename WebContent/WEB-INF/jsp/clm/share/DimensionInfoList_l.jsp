<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<%@ page import="com.sec.clm.admin.dto.DimensionForm" %>

<%@ page import="java.util.List" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%-- 
/**
 * 파  일  명 : DimensionInfoList_l.jsp
 * 프로그램명 : 계약 유형정의
 * 설      명 : 
 * 작  성  자 : 김재원
 * 작  성  일 : 2011.10.24
 */
--%>
<%
	String menuNavi = (String)request.getAttribute("secfw.menuNavi");
    DimensionForm command = (DimensionForm)request.getAttribute("command");
	List resultList = (List)request.getAttribute("resultList");
	
	String srch_type_cd = command.getSrch_type_cd();//검색시넘어오는 type_cd
	String type_cd      = null;//유형코드
	String up_type_cd   = null;//상위 유형 코드
	int rule_depth   = 0;//레벨(lvl)
	int rule_srt     = 0;//규정순서(rule_no)
	int rule_cnt     = 0;//규정번호의전체카운트 cnt
	int rn           = 0;//규정번호의 rownumber up_ytpe_cd_rn
	
	//검색 시 넘어오는 규정번호에 해당하는 depth onLoad 시 펼치기 위해.
//	if(resultList != null && resultList.size() > 0){
//		for(int i = 0 ; i < resultList.size() ; i++){
//			ListOrderedMap result = (ListOrderedMap)resultList.get(i);
//			type_cd = (String)result.get("type_cd");
//			up_type_cd = (String)result.get("up_type_cd");
//			if(srch_type_cd == type_cd){
//				up_type_cd = (String)result.get("up_type_cd");
//				rule_depth = ((Integer)result.get("lvl")).intValue();
//				rule_srt   = ((Integer)result.get("rule_no")).intValue();
//				rn         = ((Long)result.get("up_type_cd_rn")).intValue();
//				rule_cnt   = ((Integer)result.get("cnt")).intValue();
//			}
//		}
//	}
%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8; IE=9" />
<title><spring:message code="clm.main.title"/></title>
<link href="<%=CSS%>/las.css"   type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
<script language="javascript" src="/script/clms/common.js" type="text/javascript"></script>
<script language="javascript" src="/script/clms/jquery-1.6.1.min.js" type="text/javascript"></script>
<script language="javascript" src="/script/clms/new_style.js" type="text/javascript"></script>
<script language="javascript">

  

	//전체 열기
 	function showCont(){
 		$('.cont').attr('style', 'display:');
 		var trCls =  $('.trCls').length ;
 		for(var i = 0 ; i < trCls ; i++){
 			$('#tr_'+i).addClass('selectOn');
 		}
 	}
 	
	//전체 닫기
 	function hideCont(){
 		$('.cont').attr('style', 'display:none');
 		var trCls =  $('.trCls').length ;
 		for(var i = 0 ; i < trCls ; i++){
 			$('#tr_'+i).removeClass('selectOn');
 		}
 	}
	
</script>
</head>
<body>
<div id="wrap">
  <!-- container -->
  <div id="container">
	
	<!-- location -->
	<div class="location"><img SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0"/><c:out value='${menuNavi}' escapeXml="false"/></div>
	<!-- //location -->
	
	<!-- title -->
	<div class="title">
		<h3><spring:message code="clm.page.title.dimension.listTitle" /></h3>
	</div>
	<!-- //title -->
    
    <div id="content">
    <div id="content_in">
    
    	<div class="title_basic mt0">
			<h4><spring:message code="clm.page.msg.common.purposeOfConcl2"/> <img src="<%=IMAGE%>/btn/btn_up.gif" alt="down" onclick="tit_Toggle(this,'tr_down02');"/></h4>
		</div>

<table class="list_basic" cellspacing="0" cellpadding="0" id="tr_down02" style="display:;">
    <col width="10%" />
    <col width="10%" />
    <col width="60%" />
    <col width="20%" />
    <thead>
      <tr>
        <th>Level1</th>
        <th>Level2</th>
        <th><spring:message code="clm.page.msg.share.definition"/></th>
        <th>Level3</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td rowspan="31" class="sub vm tC"><spring:message code="clm.page.msg.common.dev"/></td>
        <td rowspan="7" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract057"/></td>
        <td rowspan="7" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract018"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.tech"/></td>
      </tr>
      <tr class="tal_lineL">
        <td  class='tC'><spring:message code="clm.page.msg.share.prodPartMtr"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.facilityEquip"/></td>
      </tr>
      <tr>
        <td class='tC'>S/W</td>
      </tr>
      <tr>
        <td class='tC'>Contents</td>
      </tr>
      <tr>
        <td class='tC'>Design</td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="6" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract021"/></td>
        <td rowspan="6" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract017"/></td> 
        <td class='tC'><spring:message code="clm.page.msg.share.tech"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.prodPartMtr"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.facilityEquip"/></td>
      </tr>
      <tr>
        <td class='tC'>S/W</td>
      </tr>
      <tr>
        <td class='tC'>Contents</td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="3" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract011"/></td>
        <td rowspan="3" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract035"/></td> 
        <td class='tC'><spring:message code="clm.page.msg.share.useDevEquip"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.supportTech"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="8" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract088"/></td>
        <td rowspan="8" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract012"/></td> 
        <td class='tC'>BTL</td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.contract002"/></td>
      </tr>
      <tr>
        <td class='tC'>Contents</td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td class='tC'>3rd Party</td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.asStaffOutSrc"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.asOutSrcEnBlk"/></td>
      </tr>
      <tr>
        <td class='tC'>ATL</td>
      </tr>
      <tr>
        <td rowspan="6" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract010"/></td>
        <td rowspan="6" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract013"/></td> 
        <td class='tC'>S/W</td>
      </tr>
      <tr>
        <td class='tC'>Contents</td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.facilityEquip"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.tech"/></td>
      </tr>
      <tr>
        <td class='tC'> <spring:message code="clm.page.msg.share.prodPartMtr"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td class="sub2 vm tC"><spring:message code="clm.page.msg.common.etc"/></td>
        <td class="tal_lineR tL">&nbsp;</td>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="7" class="sub vm tC"><spring:message code="clm.page.msg.common.patent"/></td>
        <td rowspan="2" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract042"/></td>
        <td rowspan="2" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract084"/></td>
        <td class='tC'><spring:message code="clm.page.msg.common.patent"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="2" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract037"/></td>
        <td rowspan="2" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract016"/></td>
        <td class='tC'><spring:message code="clm.page.msg.common.patent"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="2" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract038"/></td>
        <td rowspan="2" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract083"/></td>
        <td class='tC'><spring:message code="clm.page.msg.common.patent"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td class="sub2 vm tC"><spring:message code="clm.page.msg.common.etc"/></td>
        <td class="tal_lineR tL">&nbsp;</td>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="15" class="sub vm tC"><spring:message code="clm.page.msg.share.license"/></td>
        <td rowspan="6" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract036"/></td>
        <td rowspan="6"class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract075"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.tech"/></td>
      </tr>
      <tr>
        <td class='tC'>S/W</td>
      </tr>
      <tr>
		<td class='tC'>Contents</td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.trademark"/></td>
	  </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.copyright"/></td>
	  </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="4" class="sub2 vm tC"><spring:message code="clm.page.msg.share.preEstimateCnrt"/></td>
        <td rowspan="4"class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract074"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.tech"/></td>
      </tr>
      <tr>
        <td class='tC'>S/W</td>
      </tr>
      <tr>
        <td class='tC'>Contents</td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="4" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract039"/></td>
        <td rowspan="4" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract073"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.tech"/></td>
      </tr>
      <tr>
        <td class='tC'>S/W</td>
      </tr>
      <tr>
        <td class='tC'>Contents</td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td class="sub2 vm tC"><spring:message code="clm.page.msg.common.etc"/></td> <td class="tal_lineR tL">&nbsp;</td>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="31" class="sub vm tC"> Purchase</td>
        <td rowspan="10" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract047"/></td>
        <td rowspan="10" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract009"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.rowMtrlChemi"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.part"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.mold"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.facilityEquip"/></td>
      </tr>
      <tr>
        <td class='tC'>S/W</td>
      </tr>
      <tr>
        <td class='tC'>Contents</td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.completeProduct"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.service"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.varGoods"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="10" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract026"/></td>
        <td rowspan="10"class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract008"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.rowMtrlChemi"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.part"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.mold"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.facilityEquip"/></td>
      </tr>
      <tr>
        <td class='tC'>S/W</td>
      </tr>
      <tr>
        <td class='tC'>Contents</td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.completeProduct"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.service"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.varGoods"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="10" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract027"/></td>
        <td rowspan="10"class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract028"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.rowMtrlChemi"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.part"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.mold"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.facilityEquip"/></td>
      </tr>
      <tr>
        <td class='tC'>S/W</td>
      </tr>
      <tr>
        <td class='tC'>Contents</td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.completeProduct"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.service"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.varGoods"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td class="sub2 vm tC"><spring:message code="clm.page.msg.common.etc"/></td> 
        <td class="tal_lineR tL">&nbsp;</td>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="13" class="sub vm tC">Manufacturing</td>
        <td rowspan="2" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract006"/></td>
        <td rowspan="2" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract045"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.transBsc"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="7" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract007"/></td>
        <td rowspan="7" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract046"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.transBsc"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.propertyRentEqpRent"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.rateUnitPrice"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.lyq"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.stockMgmt"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.supplyMtrl"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="3" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract066"/></td>
        <td rowspan="3" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract065"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.employment"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.dispatchWorker"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td class="sub2 vm tC"><spring:message code="clm.page.msg.common.etc"/></td> <td class="tal_lineR tL">&nbsp;</td>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="18" class="sub vm tC">Logistics</td>
        <td rowspan="5" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract058"/></td>
        <td rowspan="5" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract071"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.landTransort"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.shipping"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.airTransport"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.multiTransport"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="4" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract076"/></td>
        <td rowspan="4" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract070"/></td>
        <td class='tC'>Lease</td>
      </tr>
      <tr>
        <td class='tC'>Operation Service</td>
      </tr>
      <tr>
        <td class='tC'>Lease + Operation Service</td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="4" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract043_2"/></td>
        <td rowspan="4" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract001"/></td>
        <td class='tC'>3rd Party</td>
      </tr>
      <tr>
        <td class='tC'>Custom</td>
      </tr>
      <tr>
        <td class='tC'>Forwarder</td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="4" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract044"/></td>
        <td rowspan="4" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract067"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.cargoInsur"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.warehousingInsr"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.transportInsur"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td class="sub2 vm tC"><spring:message code="clm.page.msg.common.etc"/></td> <td class="tal_lineR tL">&nbsp;</td>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="18" class="sub vm tC">Marketing/Sales</td>
        <td rowspan="5" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract023"/></td>
        <td rowspan="5" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract022"/></td>
        <td class='tC'>ATL</td>
      </tr>
      <tr>
        <td class='tC'>BTL</td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.contract025"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.contract043"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract055"/></td> 
        <td class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract048"/></td>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract085"/></td> 
        <td class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract024"/></td>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="2" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract093"/></td>
        <td rowspan="2" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract041"/><br />
          <spring:message code="clm.page.msg.share.IncludeChannelEnhance"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.chnlEnhncInclude"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="4" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract020"/></td>
        <td rowspan="4" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract033"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.salesProdServMpaMsa"/><</td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.salesProdServLta"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.salesProdServOneTime"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></tr>
      <tr>
        <td rowspan="4" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract087"/></td>
        <td rowspan="4" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract069"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.contract002"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.branchContract"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.contract054"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td class="sub2 vm tC"><spring:message code="clm.page.msg.common.etc"/></td> <td class="tal_lineR tL">&nbsp;</td>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="10" class="sub vm tC">Customer Satisfaction<br />
          /Service</td>
        <td rowspan="6" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract053"/></td>
        <td rowspan="6" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract068"/><br />
          <spring:message code="clm.page.msg.share.announce001"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.asOutSrcEnBlk"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.asStaffOutSrc"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.rentEqipMtrl"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.returnProcess"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.svc"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="3" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract091"/></td>
        <td rowspan="3" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract086"/></td>
        <td class='tC'><spring:message code="clm.page.msg.common.trustTest"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.qa"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td class="sub2 vm tC"><spring:message code="clm.page.msg.common.etc"/></td> <td class="tal_lineR tL">&nbsp;</td>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="8" class="sub vm tC">HR<br />
          /General Administration</td>
        <td rowspan="4" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract059"/></td>
        <td rowspan="4" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract078"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.epmtContr"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.welfareBenefit"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.eduContract"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="3" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract080"/></td>
        <td rowspan="3" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract081"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.transportContract"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.leaseProduct"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td class="sub2 vm tC"><spring:message code="clm.page.msg.common.etc"/></td> <td class="tal_lineR tL">&nbsp;</td>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="10" class="sub vm tC">Construction</td>
        <td rowspan="3" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract014"/></td>
        <td rowspan="3" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract050"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.building"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.facility"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="3" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract015"/></td>
        <td rowspan="3" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract051"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.building"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.facility"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="3" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract090"/></td>
        <td rowspan="3" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract089"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.disposal"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.contractProcess"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td class="sub2 vm tC"><spring:message code="clm.page.msg.common.etc"/></td> <td class="tal_lineR tL">&nbsp;</td>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="16" class="sub vm tC">Alliance/Investment<br />
          /Finance</td>
        <td rowspan="6" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract072"/></td>
        <td rowspan="6" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract030"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.preAliiance"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.techAlliance"/></td>
      </tr>
      <tr>
        <td class='tC'>JV</td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.conso"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.assocation"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="5" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract060"/></td>
        <td rowspan="5" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract092"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.businessTransfer"/></td>
      </tr>
      <tr>
        <td class='tC'>M&amp;A</td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.fundInvestRent"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.stakeInvest"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="4" class="sub2 vm tC"><spring:message code="clm.page.msg.share.monetaryInssur"/></td>
        <td rowspan="4" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract077"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.bond"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.securityAssur"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.insurance"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td class="sub2 vm tC"><spring:message code="clm.page.msg.common.etc"/></td> <td class="tal_lineR tL">&nbsp;</td>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="12" class="sub vm tC">Consulting/Research</td>
        <td rowspan="5" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract082"/></td>
        <td rowspan="5" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract061"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.techAdvice"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.manageAdvice"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.legalAdvice"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.accountConsul"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="3" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract040"/></td>
        <td rowspan="3" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract005"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.pordService"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.marketCutomer"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="3" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract063"/></td>
        <td rowspan="3" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract062"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.reportJournal"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.share.onlineMembership"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td class="sub2 vm tC"><spring:message code="clm.page.msg.common.etc"/></td> <td class="tal_lineR tL">&nbsp;</td>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="7" class="sub vm tC">IT/System</td>
        <td rowspan="2" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract004"/></td> <td rowspan="2" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract049"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.itSysBuild"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="2" class="sub2 vm tC"><spring:message code="clm.page.msg.share.contract003"/></td> <td rowspan="2" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract029"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.itSysMaint"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr>
        <td rowspan="2" class="sub2 vm tC">IT<spring:message code="clm.page.msg.share.contract052"/></td> <td rowspan="2" class="tal_lineR tL"><spring:message code="clm.page.msg.share.contract032"/></td>
        <td class='tC'><spring:message code="clm.page.msg.share.itRelService"/></td>
      </tr>
      <tr>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
      <tr class="end">
        <td class="sub2 vm tC"><spring:message code="clm.page.msg.common.etc"/></td> <td class="tal_lineR tL">&nbsp;</td>
        <td class='tC'><spring:message code="clm.page.msg.common.etc"/></td>
      </tr>
    </tbody>
    <tbody>
    </tbody>
  </table>

        			<!-- title -->                  
					<div class="title_basic02">
						<h4><spring:message code="clm.page.msg.share.businessStage"/></h4>
					</div>
        			<table class="list_basic tr_nobg" cellspacing="0" cellpadding="0">
        			  <tbody>
      			    </tbody>
        			  <col width="150px" />
        			  <col />
        			  <thead>
        			    <tr>
        			      <th><spring:message code="clm.page.msg.common.div"/></th>
        			      <th><spring:message code="clm.page.msg.share.definition"/></th>
      			      </tr>
      			    </thead>
        			  <tbody>
        			    <tr>
        			      <td class="sub vm tC">NDA</td>
        			      <td><div align="left">-<spring:message code="clm.page.msg.share.contract064"/><br />
        			        -<spring:message code="clm.page.msg.share.contract079"/><br />
       			          <spring:message code="clm.page.msg.share.preEtcPart"/></div></td>
      			      </tr>
        			    <tr>
        			      <td class="sub vm tC">MOU/LOI/MOA/LOA</td>
        			      <td><div align="left">MOU/LOI/MOA/LOA</div></td>
      			      </tr>
        			    <tr class="end">
        			      <td class="sub vm tC"><spring:message code="clm.page.msg.share.formalContract"/></td>
        			      <td><div align="left">-<spring:message code="clm.page.msg.share.contract034"/><br />
       			          -<spring:message code="clm.page.msg.share.contract056"/></div></td>
      			      </tr>
      			    </tbody>
        			  <tbody>
      			    </tbody>
      			  </table>
<div class="mt15"></div>                  
        			<!-- title -->
    <div class="title_basic02">
						<h4><spring:message code="clm.page.msg.share.contractStage"/></h4>
</div>
					<!-- //title -->
					<!-- title -->
			
					<!-- //title -->
					<table class="list_basic tr_nobg" cellspacing="0" cellpadding="0">
					  <tbody>
				      </tbody>
					  <col width="150px" />
					  <col />
					  <thead>
					    <tr>
					      <th><spring:message code="clm.page.msg.common.div"/></th>
					      <th><spring:message code="clm.page.msg.share.definition"/></th>
				        </tr>
				      </thead>
					  <tbody>
					    <tr>
					      <td class="sub vm tC"><spring:message code="clm.page.msg.share.newContract"/></td>
					      <td><div align="left">-<spring:message code="clm.page.msg.share.contract031"/><br />
				          -<spring:message code="clm.page.msg.share.ableToNewContarct"/></div></td>
				        </tr>
					    <tr>
					      <td class="sub vm tC"><spring:message code="clm.page.msg.share.alterContract"/></td>
					      <td ><div align="left"></div></td>
				        </tr>
					    <tr class="end">
					      <td class="sub vm tC"><spring:message code="clm.page.msg.share.cancelCrt"/></td>
					      <td><div align="left"><spring:message code="clm.page.msg.share.contract019"/></div></td>
				        </tr>
				      </tbody>
			    </table>
			    
			    <br><br><br>
			    
			    
			</div>
		</div>
	</div>
</div>
<!-- footer -->
<script language="javascript" src="/script/clms/footer.js" type="text/javascript"></script>
<!-- // footer -->
</body>
</html>