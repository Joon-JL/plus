<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="common.page.field.OpenPopME.samsungClms"/></title>
<link href="<%=CSS%>/clm.css"   type="text/css" rel="stylesheet"/>
<link href="<%=CSS%>/popup.css" type="text/css" rel="stylesheet"/>
<script language="javascript" src="/script/clms/common.js" type="text/javascript"></script>
<script language="javascript" src="/script/clms/jquery-1.6.1.min.js" type="text/javascript"></script>
<script language="javascript" src="/script/clms/new_style.js" type="text/javascript"></script>
</head>
<body class="pop">
<!-- header -->
<h1><spring:message code="common.page.field.OpenPopME.offiLawyer"/></h1>
<!-- //header -->
<div class="pop_area">
	<!-- Popup Size 840*600 -->
	<div class="h_600" style='height:525px'>
        <!-- content -->
        <div class="pop_content">
        
        	<br/>
			
			<!-- title -->
			<!-- 
			<div class="title">
				<h4>공무집행 대응 변호사</h4>
			</div>
			 -->
			<!-- //title -->
			
			<!-- search-->
			<div class="search_box">
				<div class="search_box_inner">
					<table class="search_tb">
						<tr>
							<td>
								<table class="search_form">
									<tr>
										<th><spring:message code="common.page.field.OpenPopME.lwFairTrade"/></th>
									</tr>
									<tr>
										<td style='padding:0 16px'>
											<spring:message code="common.page.field.OpenPopME.contactFstLw1"/><br>
											<spring:message code="common.page.field.OpenPopME.lgAsstc"/>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
            </div>
			<!-- //search-->
					
			<br>
			
			<style>
				.tableWrap {width:100%; clear:both;}
				.tableone {margin-right:17px; _width:100%; }
				*:first-child+html .tableone {margin-right:0px; }
				.tabletwo {overflow-y:scroll; overflow-x:hidden; table-layout:fixed;}
				.sub4 {background:#FFFFCC; color:#48372B !important; border-right:1px solid #CADBE2; border-bottom:1px solid #97B7E3 !important;}
				.sub5 {background:#F6FDF5; color:#45645F !important;  border-bottom:1px solid #97B7E3 !important;}
				.sub6 {background:#FFF7FA; color:#583A45 !important;  border-bottom:1px solid #97B7E3 !important;}
				.list_basic .sec_line td {border-bottom:1px solid #97B7E3 !important}
				.list_basic .rightline {border-right:1px solid #CADBE2 !important}
			</style>			
			
			
				<!-- list_table -->
				<div class='tableone'>
				<table class="list_basic">
					<colgroup>
					<col width="13%"/>
					<col width="13%"/>
					<col width="28%"/>
					<col width="18%"/>
					<col width="*%"/>
					</colgroup>
					<thead>	
						<tr>
							<th><spring:message code="common.page.field.OpenPopME.workSite"/></th>
							<th><spring:message code="common.page.field.OpenPopME.workDept"/></th>
							<th><spring:message code="common.page.field.OpenPopME.personInCharge"/></th>
							<th><spring:message code="common.page.field.OpenPopME.cellphone"/></th>
							<th>E - mail</th>
						</tr>
					</thead>
				</table>
				</div>
				<div class='tabletwo' style='height:352px;'>
				<table class="list_basic" style='border-top:0px solid #fff; border-bottom:1px solid #97B7E3'>
					<colgroup>
					<col width="13%"/>
					<col width="13%"/>
					<col width="10%"/>
					<col width="18%"/>
					<col width="18%"/>
					<col width="*%"/>
					</colgroup>
					<tbody>
						<tr class='sec_line'>
							<td class='sub6 rightline' rowspan='4'><spring:message code="common.page.field.OpenPopME.workSite001"/><!--서울--></td>
							<td class='sub4' rowspan='2'><spring:message code="common.page.field.OpenPopME.workDept001"/><!--한국총괄--></td>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.personInChargeOrd01"/><!--1차--></td>
							<td><spring:message code="common.page.field.OpenPopME.personInCharge001"/><!--김광훈 변호사--></td>
							<td>010-9904-6336</td>
							<td>gh21.kim@samsung.com</td>
						</tr>
						<tr class='sec_line'>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.personInChargeOrd02"/><!--2차--></td>
							<td><spring:message code="common.page.field.OpenPopME.personInCharge002"/><!--강태욱 변호사--></td>
							<td>010-7223-2399</td>
							<td>tw911.kang@samsung.com</td>
						</tr>
						<tr>
							<td class='sub4' rowspan='2'><spring:message code="common.page.field.OpenPopME.workSite001"/><!--기타--></td>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.personInChargeOrd01"/><!--1차--></td>
							<td><spring:message code="common.page.field.OpenPopME.personInCharge003"/></td>
							<td>010-3653-6527</td>
							<td>sb1004.kwon@samsung.com</td>
						</tr>
						<tr class='sec_line'>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.personInChargeOrd02"/><!--2차--></td>
							<td><spring:message code="common.page.field.OpenPopME.personInCharge004"/><!--이경선 변호사--></td>
							<td>010-5225-8963</td>
							<td>ks121.lee@samsung.com</td>
						</tr>
						
						<tr>
							<td class='sub6 rightline' rowspan='12'><spring:message code="common.page.field.OpenPopME.workSite005"/><!--수원--></td>
							<td class='sub4' rowspan='2'><spring:message code="common.page.field.OpenPopME.workDept005"/><!--무선--></td>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.personInChargeOrd01"/><!--1차--></td>
							<td><spring:message code="common.page.field.OpenPopME.personInCharge005"/><!--김성욱 변호사--></td>
							<td>010-3523-5037</td>
							<td>swk.kim@samsung.com</td>
						</tr>
						<tr class='sec_line'>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.personInChargeOrd02"/><!--2차--></td>
							<td><spring:message code="common.page.field.OpenPopME.personInCharge006"/><!--강은하 변호사--></td>
							<td>010-7153-9961</td>
							<td>enha.kang@samsung.com</td>
						</tr>
						<tr>
							<td class='sub4' rowspan='2'>VD</td>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.personInChargeOrd01"/><!--1차--></td>
							<td><spring:message code="common.page.field.OpenPopME.personInCharge007"/><!--전명선 변호사--></td>
							<td>010-2058-9107</td>
							<td>ms2023.jeon@samsung.com</td>
						</tr>
						<tr class='sec_line'>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.personInChargeOrd02"/><!--2차--></td>
							<td><spring:message code="common.page.field.OpenPopME.personInCharge008"/><!--최경원 변호사--></td>
							<td>010-5767-5491</td>
							<td>losus.choi@samsung.com</td>
						</tr>
						
						<tr>
							<td class='sub4' rowspan='2'><spring:message code="common.page.field.OpenPopME.workSite009"/><!--프린팅솔루션<br>디지털이미징--></td>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.personInChargeOrd01"/><!--1차--></td>
							<td><spring:message code="common.page.field.OpenPopME.personInCharge009"/><!--박지윤 변호사--></td>
							<td>010-3114-6768</td>
							<td>jeeyoon.park@samsung.com</td>
						</tr>
						<tr class='sec_line'>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.personInChargeOrd02"/><!--2차--></td>
							<td><spring:message code="common.page.field.OpenPopME.personInCharge010"/><!--유병택 변호사--></td>
							<td>010-2268-5335</td>
							<td>bt.yoo@samsung.com</td>
						</tr>
						
						<tr>
							<td class='sub4' rowspan='2'><spring:message code="common.page.field.OpenPopME.workDept011"/><!--네트워크--></td>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.personInChargeOrd01"/><!--1차--></td>
							<td><spring:message code="common.page.field.OpenPopME.personInCharge011"/><!--권대훈 변호사--></td>
							<td>010-3296-1555</td>
							<td>daehoon.kwon@samsung.com</td>
						</tr>
						<tr class='sec_line'>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.personInChargeOrd02"/><!--2차--></td>
							<td><spring:message code="common.page.field.OpenPopME.personInCharge012"/><!--박지윤 변호사--></td>
							<td>010-3114-6768</td>
							<td>jeeyoon.park@samsung.com</td>
						</tr>
						
						<tr>
							<td class='sub4' rowspan='2'><spring:message code="common.page.field.OpenPopME.workDept013"/><!--생활가전--></td>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.personInChargeOrd01"/><!--1차--></td>
							<td><spring:message code="common.page.field.OpenPopME.personInCharge013"/><!--정병조 변호사--></td>
							<td>010-7131-1663</td>
							<td>bj98.jeong@samsung.com</td>
						</tr>
						<tr class='sec_line'>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.personInChargeOrd02"/><!--2차--></td>
							<td><spring:message code="common.page.field.OpenPopME.personInCharge014"/><!--박지윤 변호사--></td>
							<td>010-3114-6768</td>
							<td>jeeyoon.park@samsung.com</td>
						</tr>
						
						<tr>
							<td class='sub4' rowspan='2'><spring:message code="common.page.field.OpenPopME.workDept015"/><!--기타--></td>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.personInChargeOrd01"/><!--1차--></td>
							<td><spring:message code="common.page.field.OpenPopME.personInCharge015"/><!--박지윤 변호사--></td>
							<td>010-3114-6768</td>
							<td>jeeyoon.park@samsung.com</td>
						</tr>
						<tr class='sec_line'>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.personInChargeOrd02"/><!--2차--></td>
							<td><spring:message code="common.page.field.OpenPopME.personInCharge016"/><!--전명선 변호사--></td>
							<td>010-2058-9107</td>
							<td>ms2023.jeon@samsung.com</td>
						</tr>
						
						
						<tr>
							<td class='sub6' rowspan='2'><spring:message code="common.page.field.OpenPopME.workSite017"/><!--기흥/화성/온양--></td>
							<td class='sub4' rowspan='2'><spring:message code="common.page.field.OpenPopME.workDept017"/><!--DS부문<br>종합기술원--></td>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.personInChargeOrd01"/><!--1차--></td>
							<td><spring:message code="common.page.field.OpenPopME.personInCharge017"/><!--김평진 변호사--></td>
							<td>010-4550-1231</td>
							<td>olivian.kim@samsung.com</td>
						</tr>
						<tr class='sec_line'>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.personInChargeOrd02"/><!--2차--></td>
							<td><spring:message code="common.page.field.OpenPopME.personInCharge018"/><!--허유하 변호사--></td>
							<td>010-7496-0730</td>
							<td>yh.huh@samsung.com</td>
						</tr>
						
						<tr>
							<td class='sub6' rowspan='2'><spring:message code="common.page.field.OpenPopME.workSite019"/><!--구미--></td>
							<td class='sub4' rowspan='2'><spring:message code="common.page.field.OpenPopME.workDept019"/><!--무선<br>기타--></td>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.personInChargeOrd01"/><!--1차--></td>
							<td><spring:message code="common.page.field.OpenPopME.personInCharge019"/><!--김민석 변호사--></td>
							<td>010-7173-7971</td>
							<td>mslaw.kim@samsung.com</td>
						</tr>
						<tr class='sec_line'>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.personInChargeOrd02"/><!--2차--></td>
							<td><spring:message code="common.page.field.OpenPopME.personInCharge020"/><!--김성욱 변호사--></td>
							<td>010-3523-5037</td>
							<td>swk.kim@samsung.com</td>
						</tr>
						
						<tr>
							<td class='sub6' rowspan='2'><spring:message code="common.page.field.OpenPopME.workSite021"/><!--광주--></td>
							<td class='sub4' rowspan='2'><spring:message code="common.page.field.OpenPopME.workDept021"/><!--생활가전--></td>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.personInChargeOrd01"/><!--1차--></td>
							<td><spring:message code="common.page.field.OpenPopME.personInCharge021"/><!--정용중 변호사--></td>
							<td>017-617-2076</td>
							<td>yj11.jeong@samsung.com</td>
						</tr>
						<tr class='sec_line'>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.personInChargeOrd02"/><!--2차--></td>
							<td><spring:message code="common.page.field.OpenPopME.personInCharge022"/><!--정병조 변호사--></td>
							<td>010-7131-1663</td>
							<td>bj98.jeong@samsung.com</td>
						</tr>
						
						
						<tr>
							<td class='sub6' rowspan='7'><spring:message code="common.page.field.OpenPopME.nightDuty"/><!--당직자--></td>
							<td class='sub4' rowspan='7'>-</td>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.mon"/></td>
							<td><spring:message code="common.page.field.OpenPopME.lwKwonKiduk"/></td>
							<td>010-4035-5514</td>
							<td>kd.kwon@samsung.com</td>
						</tr>
						<tr>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.tue"/></td>
							<td><spring:message code="common.page.field.OpenPopME.lwKownSoonbum"/></td>
							<td>010-3653-6527</td>
							<td>sb1004.kwon@samsung.com</td>
						</tr>
						<tr>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.wed"/></td>
							<td><spring:message code="common.page.field.OpenPopME.lwLeeDonghyun"/></td>
							<td>010-6267-1151</td>
							<td>dh7680.lee@samsung.com</td>
						</tr>
						<tr>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.thur"/></td>
							<td><spring:message code="common.page.field.OpenPopME.lwYangMoonsik"/></td>
							<td>010-9208-3023</td>
							<td>yms.yang@samsung.com</td>
						</tr>
						<tr>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.fri"/></td>
							<td><spring:message code="common.page.field.OpenPopME.drJangJunghwan"/></td>
							<td>010-8670-7266</td>
							<td>jh66.jang@samsung.com</td>
						</tr>
						<tr>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.sat"/></td>
							<td><spring:message code="common.page.field.OpenPopME.drSonHuntae"/></td>
							<td>010-2826-6207</td>
							<td>sonht@samsung.com</td>
						</tr>
						<tr class='sec_line'>
							<td class='sub5'><spring:message code="common.page.field.OpenPopME.sun"/></td>
							<td><spring:message code="common.page.field.OpenPopME.drKimByungju"/></td>
							<td>010-3490-7592</td>
							<td>bj7592.kim@samsung.com</td>
						</tr>
					</tbody>
				</table>
				</div>
				<!-- // list_table -->
			
			
			
				
			


			
		</div>
		<!-- //content -->
	</div>
	<!-- Popup Size 840*600 -->
</div>
<!-- //body -->


<!--footer -->
<div class="pop_footer">
	<div class="btn_wrap fR">
		<span class="btn_all_w"><span class="cancel"></span><a href="#" onclick="javascript:self.close();"><spring:message code="common.page.field.OpenPopME.close"/></a></span>
	</div>
</div>
<!-- //footer -->
</body>

</html>
