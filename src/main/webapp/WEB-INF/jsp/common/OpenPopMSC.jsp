<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="common.page.field.OpenPopMSC.samsungClms"/></title>
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet"/>
<link href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet"/>
<script language="javascript" src="/script/clms/common.js" type="text/javascript"></script>
<script language="javascript" src="/script/clms/jquery-1.6.1.min.js" type="text/javascript"></script>
<script language="javascript" src="/script/clms/new_style.js" type="text/javascript"></script>

<script>
function divShow(obj) {
	obj.style.display = 'block';
}
function divHidden(obj) {
	obj.style.display = 'none';
}
</script>

</head>
<body class="pop">
<!-- header -->
<h1><spring:message code="common.page.field.OpenPopMSC.lwListEachField"/></h1>
<!-- //header -->
<div class="pop_area">
	<!-- Popup Size 840*600 -->
	<div class="h_600" style='height:545px'>
        <!-- content -->
        <div class="pop_content">
			
				<style>
				.tableWrap {width:100%; clear:both;}
				.tableone {margin-right:17px; _width:100%; }
				*:first-child+html .tableone {margin-right:0px; }
				.tabletwo {overflow-y:scroll; overflow-x:hidden; table-layout:fixed;}
				.sub4 {background:#FFFFCC; color:#48372B !important; border-right:1px solid #CADBE2; border-bottom:1px solid #97B7E3 !important;}
				.sub5 {background:#F6FDF5; color:#45645F !important;  border-bottom:1px solid #97B7E3 !important;}
				.sub6 {background:#FFF7FA; color:#583A45 !important;  border-bottom:1px solid #97B7E3 !important; }
				.list_basic  td {padding:2px 4px}
				.list_basic .sec_line td {border-bottom:1px solid #97B7E3 !important; line-height:150% }
				.list_basic .rightline {border-right:1px solid #CADBE2 !important}
				</style>
			
			<!-- title -->
			<div class="title" style='margin-bottom:5px; margin-top:-5px'>
				<h4><spring:message code="common.page.field.OpenPopMSC.lwListEachField"/></h4>
			</div>
			<!-- //title -->
			
				<!-- 국내법무 -->
				<div id='a_01' style='display:; height:530px;''>
				
					<!-- tab -->
					<div class="tab_box">
						 <ul class="tab_basic">
							<li class="on"><a href="#" style='text-align:center'><spring:message code="common.page.field.OpenPopMSC.lgDomst"/></a></li>
							<li ><a href="#" style='text-align:center' onclick="divShow(a_02),divHidden(a_01)"><spring:message code="common.page.field.OpenPopMSC.abroadLegal"/></a></li>
						 </ul>
					</div>
					<!-- //tab -->
					<div style='height:1px; border-top:1px solid #3E74BE; margin-top:-1px'>&nbsp;</div>
					
					<br>			
			
			
				<!-- 국내법무테이블 -->				
				<table class="list_basic">
					<colgroup>
					<col width="8%"/>
					<col width="10%"/>
					<col width="12%"/>
					<col width="24%"/>
					<col width="46%"/>
					</colgroup>
					<thead>	
						<tr>
							<th><spring:message code="common.page.field.OpenPopMSC.field"/></th>
							<th><spring:message code="common.page.field.OpenPopMSC.proLawyer"/></th>
							<th><spring:message code="common.page.field.OpenPopMSC.phoneNo"/></th>
							<th>E-mail</th>
							<th><spring:message code="common.page.field.OpenPopMSC.linkedIssues"/></th>
						</tr>
					</thead>
				
				
					<tbody>
						<tr class='sec_line'>
							<td class='sub6 rightline' rowspan='2'><spring:message code="common.page.field.OpenPopMSC.fairTrade"/></td><!-- 공정거래 -->
							<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.proLawyername001"/></td><!-- 권순범 -->
							<td class='sub5'>02-2255-8323</td>
							<td>sb1004.kwon@samsung.com</td>
							<td  class='tL' rowspan='2'>- <spring:message code="common.page.field.OpenPopMSC.subcontractToSmallB"/><br/><span style='margin-left:4px;'></span><spring:message code="common.page.field.OpenPopMSC.needAttend"/></td>
						</tr>
						<tr class='sec_line'>
							<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.leeKyungsun"/></td>
							<td class='sub5'>02-2255-8437</td>
							<td>ks121.lee@samsung.com</td>
						</tr>
						
						<tr class='sec_line'>
							<td class='sub6 rightline' rowspan='2'>M & A</td>
							<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.kwonKiduk"/></td>
							<td class='sub5'>02-2255-8438</td>
							<td>kd.kwon@samsung.com</td>
							<td  class='tL' rowspan='2'>- <spring:message code="common.page.field.OpenPopMSC.copMerge"/><br/><span style='margin-left:5px;'></span><spring:message code="common.page.field.OpenPopMSC.copEstb"/></td>
						</tr>
						<tr class='sec_line'>
							<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.kwonDaehoon"/></td>
							<td class='sub5'>02-2255-8325</td>
							<td>daehoon.kwon@samsung.com</td>
						</tr>
						
						
						<tr class='sec_line'>
							<td class='sub6 rightline' rowspan='2'><spring:message code="common.page.field.OpenPopMSC.offLab"/></td>
							<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.parkJungho"/></td>
							<td class='sub5'>02-2255-7575</td>
							<td>perry.park@samsung.com</td>
							<td  class='tL' rowspan='2'>
								- <spring:message code="common.page.field.OpenPopMSC.common"/>: <spring:message code="common.page.field.OpenPopMSC.laborSuit"/><br/>
								- <spring:message code="common.page.field.OpenPopMSC.perParkJungho"/><br/>
								- <spring:message code="common.page.field.OpenPopMSC.noParkSamkeun"/><br/>
							</td>
						</tr>
						<tr class='sec_line'>
							<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.parkSamkeun"/></td>
							<td class='sub5'>02-2255-7574</td>
							<td>sk43.park@samsung.com</td>
						</tr>
						
						<tr class='sec_line'>
							<td class='sub6 rightline' rowspan='2'><spring:message code="common.page.field.OpenPopMSC.crmSuit"/></td>
							<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.JiEunSeok"/></td>
							<td class='sub5'>02-2255-3747</td>
							<td>zi.enseok@samsung.com</td>
							<td  class='tL' rowspan='2'>
								- <spring:message code="common.page.field.OpenPopMSC.strDecideOpn"/><br/>
								<span style='margin-left:4px;'></span><spring:message code="common.page.field.OpenPopMSC.chargeWithCrime"/><br/>
								<span style='margin-left:4px;'></span><spring:message code="common.page.field.OpenPopMSC.dealCloseCase"/>
							</td>
						</tr>
						<tr class='sec_line'>
							<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.HanGiuk"/></td>
							<td class='sub5'>02-2255-3733</td>
							<td>ku.han@samsung.com</td>
						</tr>
						
						<tr class='sec_line'>
							<td class='sub6 rightline' rowspan='2'><spring:message code="common.page.field.OpenPopMSC.tax"/></td>
							<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.kimPyungjin"/></td>
							<td class='sub5'>031-8000-0228</td>
							<td>olivian.kim@samsung.com</td>
							<td  class='tL' rowspan='2'>
								- <spring:message code="common.page.field.OpenPopMSC.complexIssue"/><br/>
  								<span style='margin-left:4px;'></span><spring:message code="common.page.field.OpenPopMSC.taxBenf"/>
							</td>
						</tr>
						<tr class='sec_line'>
							<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.yoonYongduk"/></td>
							<td class='sub5'>031-2255-8362</td>
							<td>yongdoc.yoon@samsung.com</td>
						</tr>
						
						
						<tr class='sec_line'>
							<td class='sub6 rightline' rowspan='2'><spring:message code="common.page.field.OpenPopMSC.decideOgz"/></td>
							<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.kwonSoonbum"/></td>
							<td class='sub5'>02-2255-8323</td>
							<td>sb1004.kwon@samsung.com</td>
							<td  class='tL' rowspan='2'>
								- <spring:message code="common.page.field.OpenPopMSC.shareholdersTarget"/><br/>
								<span style='margin-left:4px;'></span><spring:message code="common.page.field.OpenPopMSC.strSteptarget"/>
							</td>
						</tr>
						<tr class='sec_line'>
							<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.yoonHaeyoung"/></td>
							<td class='sub5'>02-2255-8369</td>
							<td>hy1102.yoon@samsung.com</td>
						</tr>
						
						
						<tr class='sec_line'>
							<td class='sub6 rightline' rowspan='2'><spring:message code="common.page.field.OpenPopMSC.industrialAccident"/></td>
							<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.leeDonghyun"/></td>
							<td class='sub5'>02-2255-8436</td>
							<td>dh7680.lee@samsung.com</td>
							<td  class='tL' rowspan='2'>
								- <spring:message code="common.page.field.OpenPopMSC.judgeIndustAcc"/><br/>
								<span style='margin-left:4px;'></span><spring:message code="common.page.field.OpenPopMSC.dealIndustrial"/>
							</td>
						</tr>
						<tr class='sec_line'>
							<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.kimJunghwa"/></td>
							<td class='sub5'>02-2255-8349</td>
							<td>jhag.kim@samsung.com</td>
						</tr>
						
						
						<tr class='sec_line'>
							<td class='sub6 rightline' rowspan='2'><spring:message code="common.page.field.OpenPopMSC.tradingFirm"/></td>
							<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.junMyungsun"/></td>
							<td class='sub5'>031-277-8972</td>
							<td>ms2023.jeon@samsung.com</td>
							<td  class='tL' rowspan='2'>
								- <spring:message code="common.page.field.OpenPopMSC.reasonFor"/><br/>
								<span style='margin-left:4px;'></span><spring:message code="common.page.field.OpenPopMSC.strStockOption"/>
							</td>
						</tr>
						<tr class='sec_line'>
							<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.proLawyername002"/></td><!-- 이선기 -->
							<td class='sub5'>02-2255-8367</td>
							<td>sunki37.lee@samsung.com</td>
						</tr>
						
						
						<tr class='sec_line'>
							<td class='sub6 rightline' rowspan='2'><spring:message code="common.page.field.OpenPopMSC.personalInfo"/></td>
							<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.kimKwanghoon"/></td>
							<td class='sub5'>02-2255-6519</td>
							<td>gh21.kim@samsung.com</td>
							<td  class='tL' rowspan='2'>
								- <spring:message code="common.page.field.OpenPopMSC.pInfoWay"/><br/>
								<span style='margin-left:4px;'></span><spring:message code="common.page.field.OpenPopMSC.senInfoReview"/>
							</td>
						</tr>
						<tr class='sec_line'>
							<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.jungHeebum"/></td>
							<td class='sub5'>02-2255-3734</td>
							<td>heebeom.jung@samsung.com</td>
						</tr>
					</tbody>
				</table>
					<!-- // 국내법무테이블 -->
					
				</div>
				<!-- // 국내법무 -->	
			
				
				<!-- 해외법무 -->
				<div id='a_02' style='display:none; height:530px;'>
					<!-- tab -->
					<div class="tab_box">
						 <ul class="tab_basic">
							<li><a href="#" style='text-align:center' onclick="divShow(a_01),divHidden(a_02)"><spring:message code="common.page.field.OpenPopMSC.lgDomst"/></a></li>
							<li class="on"><a href="#" style='text-align:center'><spring:message code="common.page.field.OpenPopMSC.abroadLegal"/></a></li>
						 </ul>
					</div>
					<!-- //tab -->
					<div style='height:1px; border-top:1px solid #3E74BE; margin-top:-1px'>&nbsp;</div>
					
					<br>
					
					<div class="search_box">
				        <div class="search_box_inner">
					        <span class="search_box_tb">
					            <table class="search_form">
						            <tr>
						                <td style="padding:5px;"><img src='<%=IMAGE%>/icon/ico_std_unit.gif'  align="absmiddle">
							            <spring:message code="common.page.field.OpenPopMSC.needProLw"/></td>
						            </tr>
						        </table>
						    </span>
					    </div>
					</div>
				
					<!-- list_table -->
					<table class="list_basic mt10">
						<colgroup>
						<col width="12%"/>
						<col width="10%"/>
						<col width="12%"/>
						<col width="22%"/>
						<col width="44%"/>
						</colgroup>
						<thead>	
							<tr>
								<th><spring:message code="common.page.field.OpenPopMSC.field"/></th>
								<th><spring:message code="common.page.field.OpenPopMSC.proLawyer"/></th>
								<th><spring:message code="common.page.field.OpenPopMSC.phoneNo"/></th>
								<th>E-mail</th>
								<th><spring:message code="common.page.field.OpenPopMSC.linkedIssues"/></th>
							</tr>
						</thead>
					
					
						<tbody>
							<tr class='sec_line'>
								<td class='sub6 rightline' rowspan='2'><spring:message code="common.page.field.OpenPopMSC.antiMonp"/></td>
								<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.parkSanghoon"/></td>
								<td class='sub5'>02-2255-8365</td>
								<td>sanghoon.park@samsung.com</td>
								<td  class='tL' rowspan='2'><spring:message code="common.page.field.OpenPopMSC.etcCollusion"/></td>
							</tr>
							<tr class='sec_line'>
								<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.dandelion"/></td>
								<td class='sub5'>02-2255-8336</td>
								<td>rae.min@samsung.com</td>
							</tr>
							
							<tr class='sec_line'>
								<td class='sub6 rightline'>M & A</td>
								<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.HanJangsu"/></td>
								<td class='sub5'>031-279-1810</td>
								<td>changsu.han@samsung.com</td>
								<td class='tL'><spring:message code="common.page.field.OpenPopMSC.copMerge2"/></td>
							</tr>
							<!-- 
							<tr class='sec_line'>
								<td class='sub4'>홍석현</td>
								<td class='sub5'>02-2255-8409</td>
								<td>sam.s.hong@samsung.com</td>
							</tr>
							 -->
							
							
							<tr class='sec_line'>
								<td class='sub6 rightline' rowspan='2'><spring:message code="common.page.field.OpenPopMSC.suit"/></td>
								<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.umKyungho"/></td>
								<td class='sub5'>02-2255-8353</td>
								<td>umgh@samsung.com</td>
								<td  class='tL' rowspan='2'><spring:message code="common.page.field.OpenPopMSC.suitStep"/></td>
							</tr>
							<tr class='sec_line'>
								<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.hongjaeseok"/></td>
								<td class='sub5'>02-2255-8337</td>
								<td>jaishuk.hong@samsung.com</td>
							</tr>
							
							
							<tr class='sec_line'>
								<td class='sub6 rightline'><spring:message code="common.page.field.OpenPopMSC.finance"/></td>
								<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.maxwellKim"/></td>
								<td class='sub5'>031-8000-0218</td>
								<td>msk149.kim@samsung.com</td>
								<td class='tL'><spring:message code="common.page.field.OpenPopMSC.issueWithFinance"/></td>
							</tr>
							<tr class='sec_line'>
								<td class='sub6 rightline'><spring:message code="common.page.field.OpenPopMSC.protectInfo"/></td>
								<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.kimSujin"/></td>
								<td class='sub5'>031-277-0611</td>
								<td>sj91.kim@samsung.com</td>
								<td class='tL'><spring:message code="common.page.field.OpenPopMSC.pInfoUse"/></td>
							</tr>
							<tr class='sec_line'>
								<td class='sub6 rightline'><spring:message code="common.page.field.OpenPopMSC.insurance"/></td>
								<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.shinDonghyun"/></td>
								<td class='sub5'>02-2255-8404</td>
								<td>tds.shin@samsung.com</td>
								<td class='tL'><spring:message code="common.page.field.OpenPopMSC.insTerms"/></td>
							</tr>
							<tr class='sec_line'>
								<td class='sub6 rightline'><spring:message code="common.page.field.OpenPopMSC.csImmgr"/></td>
								<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.woonJimi"/></td>
								<td class='sub5'>031-8000-0224</td>
								<td>jimmy.yun@samsung.com</td>
								<td class='tL'><spring:message code="common.page.field.OpenPopMSC.visaIssue"/></td>
							</tr>
							<tr class='sec_line'>
								<td class='sub6 rightline'><spring:message code="common.page.field.OpenPopMSC.antiDump"/></td>
								<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.ohYoungmin"/></td>
								<td class='sub5'>02-2255-8333</td>
								<td>yongmin.oh@samsung.com</td>
								<td class='tL'><spring:message code="common.page.field.OpenPopMSC.taxInsubor"/></td>
							</tr>
							<tr class='sec_line'>
								<td class='sub6 rightline'><spring:message code="common.page.field.OpenPopMSC.tax"/></td>
								<td class='sub4'><spring:message code="common.page.field.OpenPopMSC.hongSangbeom"/></td>
								<td class='sub5'>031-277-1561</td>
								<td>lawsang@samsung.com</td>
								<td class='tL'><spring:message code="common.page.field.OpenPopMSC.aroundTax"/></td>
							</tr>
						</tbody>
					</table>
					<!-- // list_table -->
				</div>
				<!-- // 해외법무 -->				


			
		</div>
		<!-- //content -->
	</div>
	<!-- Popup Size 840*610 -->
</div><br>
<!-- //body -->


<!--footer -->
<div class="pop_footer">
	<div class="btn_wrap fR">
		<span class="btn_all_w"><span class="cancel"></span><a href="#" onclick="javascript:self.close();"><spring:message code="common.page.field.OpenPopMSC.close"/></a></span>
	</div>
</div>
<!-- //footer -->
</body>

</html>
