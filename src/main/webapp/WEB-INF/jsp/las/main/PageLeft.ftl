<!DOCTYPE html>
<#import "/spring.ftl" as spring />

<#assign CSS        	=  '/style/las/${localeCd}' />
<#assign IMAGE        	=  '/images/las/${localeCd}' />
<html>
<head>
<meta charset="utf-8" />

<title><@spring.message code='las.main.title' /></title>
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
</head>


<LINK href="${CSS}/menu.css" type="text/css" rel="stylesheet">
<script language="javascript" src="<@spring.url '/script/clms/common.js'/>"></script>
<script language="javascript" src="<@spring.url '/script/secfw/jquery/jquery.js'/>"></script>
<script language="javascript" src="<@spring.url '/script/secfw/jquery/jquery.form.js'/>"></script>
<script language="javascript" src="<@spring.url '/script/clms/menu.js'/>"></script>

<script language="javascript">
/**
 * 
 */

$(document).ready(function() {

	var frm = document.frm;
	
	if ('${initUrl}' == '') {
		return;
	}
	
	if('${reloadYn}' != 'y') {
		frm.target = "bodyFrame";
		frm.action = '<@spring.url "${initUrl}" />';
	
		frm.submit();
	}
	
	$('.section').click(function(){
		if($(this).next().css("display") == 'block'){
			$(this).next().css("display","none");
		}
		else{
		$(this).next().css("display","block");
		}
	});
});

function refreshCount(){
	var options = {
		url			: '<@spring.url "/secfw/main.do?method=refreshLasCount&menu_id=20130319152827427_0000000336"/>',
		type		: "post",
	 	async		: false,
	 	dataType	: "html",
		success: function (data) { 
			$("#left_count_show").html("");
			$("#left_count_show").html(data);		
		}
	};
	
	$("#frm").ajaxSubmit(options);	
}

</script>
<script language='javascript' type='text/javascript'>
	function divShow(obj) {
	obj.style.display = 'block';
}
function divHidden(obj) {
	obj.style.display = 'none';
}
</script>
</head>
<body>
<#assign arguments = ["${cntResign}"]>
<form name="frm" id="frm" method="post" autocomplete="off">
	<!-- method -->
	<input type="hidden" name="method">
	<!-- targetMenuId -->
	<input type="hidden" name="targetMenuId">
	<!-- selected_menu_id -->
	<input type="hidden" name="selected_menu_id">
	<input type="hidden" name="selected_menu_nm">
	<input type="hidden" name="menuLogEnable" value="Y">
	
	<input type="hidden" name="selected_menu_detail_id">	
	<input type="hidden" name="set_init_url">
	<input type="hidden" name="reload_yn" value="n">
</form>
<div id="leftWrap">
	<div id="leftMenu">
		<div id="left_colum">
			<div class="nav nav3">
				<#if localeCd = 'ko'>
					<h2><div></div>${menuNm}</h2>
					<div></div>
				<#elseif localeCd = 'fr'>
					<h2><div></div>${menuNmFra}</h2>
					<div></div>
				<#elseif localeCd = 'de'>
					<h2><div></div>${menuNmDeu}</h2>
					<div></div>
				<#elseif localeCd = 'it'>
					<h2><div></div>${menuNmIta}</h2>
					<div></div>
				<#elseif localeCd = 'es'>
					<h2><div></div>${menuNmEsp}</h2>
					<div></div>	
				<#else>
					<h2><div></div>${menuNmEng}</h2>
					
				</#if>
				<ul>
					<#assign beforLevle        	=  0 ? int />	
					<#assign nowLevle          	=  0 ? int />	
					<#assign selectedMenu  	    =  "" />
					<#assign level2Count		=  0 ? int />		
					<#assign level3Count        =  0 ? int />
					<#assign beforeMenuId		=  "" />
					<#assign multi_menu_nm		=  "" />
					
					<#list menuList as tt>
						<!-- 소속조직이 해외법무팀이면서 사용자role 이 RA00,01,02,03일 경우만 일정관리 메뉴가 보인다. -->
						<#if tt.menu_id = "20110804083442451_0000000165" && planAccessFlag = false>
						<!-- 국내 구두내역은 소속조직이 국내 법무팀일경우만 메뉴가 보인다. -->
						<#elseif tt.menu_id = "20110804082024506_0000000133" && blngt_orgnz != "A00000001">							
						<!-- 해외 구두내역은 소속조직이 해외 법무팀일 경우만 메뉴가 보인다. -->
						<#elseif tt.menu_id = "20110804082252377_0000000140" && blngt_orgnz != "A00000002">
						
						<!-- 법률자문 국내 및 그이하 - 국내법무팀 및 IP센터만 보이게한다. -->
						<#--
						<#elseif tt.menu_id = "20110803155533470_0000000109" && blngt_orgnz = "A00000002">
						<#elseif tt.menu_id = "20110803163201974_0000000117" && blngt_orgnz = "A00000002">
						<#elseif tt.menu_id = "20110803163202117_0000000118" && blngt_orgnz = "A00000002">
						-->
						<!-- 법률자문 해외 및 그이하 - 해외법무팀 및 IP센터만 보이게한다. -->
						<#--
						<#elseif tt.menu_id = "20110803155533587_0000000110" && blngt_orgnz = "A00000001">
						<#elseif tt.menu_id = "20110803163243426_0000000119" && blngt_orgnz = "A00000001">
						<#elseif tt.menu_id = "20110803163243568_0000000120" && blngt_orgnz = "A00000001">
						-->
						<!-- 국내/해외 구두내역은 IP센타일 경우 안보이게 한다. -->
						<#elseif tt.menu_id = "20110803163202117_0000000118" && blngt_orgnz = "A00000003">
						<#elseif tt.menu_id = "20110803163243568_0000000120" && blngt_orgnz = "A00000003">
				
						
						<#else>
						
						<#assign nowLevle = tt.menu_level />
						<#if nowLevle ==  2>
							<#assign level2Count      = level2Count + 1 />
							<#if beforLevle == 3>
								<#assign level3Count      = 0 ? int/>
							</#if>
						<#elseif nowLevle == 3>

							<#assign level3Count      = level3Count + 1 />
						</#if>
							
						<#if tt.menu_id == selectedMenuId>
							<#assign beforeMenuId = tt.menu_id />
							<#assign beforeLevel2 = level2Count />
							<#assign beforeLevel3 = level3Count />
							<#assign beforeMenuCnt = tt.menu_cnt />
						</#if>
						
						<#if localeCd='ko'><#assign multi_menu_nm = tt.menu_nm /><#elseif localeCd='fr'><#assign multi_menu_nm = tt.menu_nm_fra /><#elseif localeCd='de'><#assign multi_menu_nm = tt.menu_nm_deu /><#elseif localeCd='it'><#assign multi_menu_nm = tt.menu_nm_ita /><#elseif localeCd='es'><#assign multi_menu_nm = tt.menu_nm_esp /><#else><#assign multi_menu_nm = tt.menu_nm_eng /></#if>
						
						<#if nowLevle ==  2 && beforLevle == 0>
							<li id="LM_${level2Count}">
									<a id="LM_A_${level2Count}" href="javascript:leftMenuClick(${level2Count}, <#if tt.menu_cnt &gt; 0 && tt.menu_url == ''>1<#else>0</#if>, ${tt.menu_cnt});Menu.go('frm', '<#if tt.menu_cnt &gt; 0 && tt.menu_url == ''>${tt.new_menu_url}<#else>${tt.menu_url}</#if>', '', '<#if tt.menu_cnt &gt; 0 && tt.menu_url == ''>${tt.new_menu_id}<#else>${tt.menu_id}</#if>', 'bodyFrame');" class="off">${multi_menu_nm}</a>
						<#elseif  nowLevle ==  2 && beforLevle == 2>
							</li>
							<li id="LM_${level2Count}">
								<a id="LM_A_${level2Count}" href="javascript:leftMenuClick(${level2Count}, <#if tt.menu_cnt &gt; 0 && tt.menu_url == ''>1<#else>0</#if>, ${tt.menu_cnt});Menu.go('frm', '<#if tt.menu_cnt &gt; 0 && tt.menu_url == ''>${tt.new_menu_url}<#else>${tt.menu_url}</#if>', '', '<#if tt.menu_cnt &gt; 0 && tt.menu_url == ''>${tt.new_menu_id}<#else>${tt.menu_id}</#if>', 'bodyFrame');" class="off">${multi_menu_nm}</a>
						<#elseif  nowLevle ==  2 && beforLevle == 3>
									</li>
								</ul>
							</li>
							<li id="LM_${level2Count}">
								<a id="LM_A_${level2Count}" href="javascript:leftMenuClick(${level2Count}, <#if tt.menu_cnt &gt; 0 && tt.menu_url == ''>1<#else>0</#if>, ${tt.menu_cnt});Menu.go('frm', '<#if tt.menu_cnt &gt; 0 && tt.menu_url == ''>${tt.new_menu_url}<#else>${tt.menu_url}</#if>', '', '<#if tt.menu_cnt &gt; 0 && tt.menu_url == ''>${tt.new_menu_id}<#else>${tt.menu_id}</#if>', 'bodyFrame');" class="off">${multi_menu_nm}</a>
						<#elseif  nowLevle ==  3 && beforLevle == 2>
								<ul id="LM_AREA_${level2Count}" style="display:none">
									<li id="LM_${level2Count}_${level3Count}">
										<a id="LM_A_${level2Count}_${level3Count}" href="javascript:leftMenuClick(${level2Count}, ${level3Count}, ${tt.menu_cnt});Menu.go('frm', '${tt.menu_url}', '', '${tt.menu_id}', 'bodyFrame');" class="off">${multi_menu_nm}</a>
						<#elseif  nowLevle ==  3 && beforLevle == 3>					
									</li>
									<li id="LM_${level2Count}_${level3Count}">
										<a id="LM_A_${level2Count}_${level3Count}" href="javascript:leftMenuClick(${level2Count}, ${level3Count}, ${tt.menu_cnt});Menu.go('frm', '${tt.menu_url}', '', '${tt.menu_id}', 'bodyFrame');" class="off">${multi_menu_nm}</a>
						</#if>
		
						<#assign beforLevle        =  nowLevle />		
						
						
						
						</#if>
					</#list>
					<#if beforLevle = 2>
						</li>
					<#elseif beforLevle = 3>
							</li>
						</ul>
					</li>
					</#if>			    
				</ul>
				
				<!--계약검토 및 법률자문에서만 보인다 -->
				<div id="left_count_show">
				<#if menuId == '20130319152827427_0000000336' ||  menuId == '20130319152827442_0000000337'>
						<div class="box_table mt20">
							<div class='box_table_top'><span><@spring.message code="las.page.field.main.todoList"/></span></div>
							<div class='box_table_mid'>
							
								<!-- ① 계약 -->
								<div class='section'>
									<@spring.message code="las.page.field.main.contract"/>
										<!--
										<#if topRole == "RA01" || topRole == "RA02" || topRole == "RB01"> 
											<a href="javascript:Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=1');">
										<#else>
											<a href="javascript:Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642301_0000000355', '/clm/manage/myContract.do?method=listMyContract');">
										</#if>
										-->
										<span>[<#if topRole == "RA01" || topRole == "RB01">${cntTotal}<#elseif topRole == "RA02">${cntTotal-cntUnassign}
												<#elseif topRole == "HQ01">${cntUnassign+cntReviewinProgress+cntSaved+cntNotapproved}
											    <#elseif topRole == "HQ02">${cntReviewinProgress+cntSaved+cntNotapproved}
												<#else>${cntCont01+cntCont02+cntCont03+cntCont04+cntCont05}</#if>]</span>
								</div>
								
								<div class='section_sub' id='menu01' style='display:'>
									<div class='sub_top'></div>
									<div class='sub_mid'>
										<table>
											<#if topRole == "RA01" || topRole == "RB01">
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=3&srch_law_status=C04224&srch_closed_yn=N&srch_solo_yn=${solo_yn}');">
													<th><a><@spring.message code="las.page.field.main.unchosenLong"/></a></th>
													<td>: <a>${cntUnassign}</a></td>
												</tr>
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=3&srch_law_status=C04204&srch_closed_yn=N&srch_solo_yn=${solo_yn}');">
													<th><a><@spring.message code="las.page.field.main.inCheck"/></a></th>
													<td>: <a>${cntConsider}</a></td>
												</tr>
												<!-- 2014-02-05 Kevin Added-->
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=3&srch_closed_yn=Y&srch_solo_yn=${solo_yn}');">
													<th><a><@spring.message code="las.page.title.main.closed"/></a></th>
													<td>: <a>${cntClosed}</a></td>
												</tr>
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=3&srch_closed_yn=N&srch_law_status=C04225&srch_solo_yn=${solo_yn}');">
													<th><a><@spring.message code="las.page.field.main.tmpSave"/></a></th>
													<td>: <a>${cntTempsave}</a></td>
												</tr>
												<!--
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=3&srch_closed_yn=N&srch_law_status=C04205&srch_solo_yn=${solo_yn}');">
													<th><a><@spring.message code="las.page.field.main.undecidedLong"/></a></th>
													<td>: <a>${cntPendding}</a></td>
												</tr>
												-->
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=3&srch_closed_yn=N&srch_law_status=C04206&srch_solo_yn=${solo_yn}');">
													<th><a><@spring.message code="las.page.field.main.reassign"/></a></th>
													<td>: <a>${cntResign}</a></td>
												</tr>
												<!-- 신성우 추가 2014-04-22 -->
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/manage/myContract.do?method=listMyContract&closed_yn=N&status_mode=myContractCCed');">
													<th><a><@spring.message code="las.page.field.main.cced"/></a></th>
													<td>: <a>${cntContCced}</a></td>
												</tr>
											<#elseif topRole == "RA02">
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=2&srch_law_status=C04204&srch_closed_yn=N');">
													<th><a><@spring.message code="las.page.field.main.inCheck"/></a></th>
													<td>: <a>${cntConsider}</a></td>
												</tr>
												<!-- 2014-02-05 Kevin Added-->
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&page_flag=2&srch_closed_yn=Y');">
													<th><a><@spring.message code="las.page.title.main.closed"/></a></th>
													<td>: <a>${cntClosed}</a></td>
												</tr>
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&srch_closed_yn=N&page_flag=2&srch_law_status=C04225');">
													<th><a><@spring.message code="las.page.field.main.tmpSave"/></a></th>
													<td>: <a>${cntTempsave}</a></td>
												</tr>
												<!--
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&srch_closed_yn=N&page_flag=2&srch_law_status=C04205');">
													<th><a><@spring.message code="las.page.field.main.undecidedLong"/></a></th>
													<td>: <a>${cntPendding}</a></td>
												</tr>
												-->
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/review/consideration.do?method=listConsideration&srch_closed_yn=N&page_flag=2&srch_law_status=C04206');">
													<th><a><@spring.message code="las.page.field.main.reassign"/></a></th>
													<td>: <a>${cntResign}</a></td>
												</tr>
												<!-- 신성우 추가 2014-04-22 -->
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/manage/myContract.do?method=listMyContract&closed_yn=N&status_mode=myContractCCed');">
													<th><a><@spring.message code="las.page.field.main.cced"/></a></th>
													<td>: <a>${cntContCced}</a></td>
												</tr>
											
											<#elseif (topRole == "HQ01" || topRole == "HQ02")>
												<#assign p_srch_hq_reqman_nm = '' />
												<#if topRole == "HQ01">
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20140425132144927_0000000467', '/clm/review/considerationHQ.do?method=listConsiderationHQ&page_flag=3&srch_prgrs_status_hq=C16202');">
													<th><a><@spring.message code="las.page.field.main.unchosenLong"/></a></th><!-- Not Assigned -->
													<td>: <a>${cntUnassign}</a></td>
												</tr>
												<#else>
													<#assign p_srch_hq_reqman_nm = '&srch_hq_reqman_nm=${command.session_user_nm}' />
												</#if>
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20140425132144927_0000000467', '/clm/review/considerationHQ.do?method=listConsiderationHQ&page_flag=3&srch_prgrs_status_hq=C16203${p_srch_hq_reqman_nm}');">
													<th><a><@spring.message code="las.page.field.main.inCheck"/></a></th><!-- Review In Progress -->
													<td>: <a>${cntReviewinProgress}</a></td>
												</tr>
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20140425132144927_0000000467', '/clm/review/considerationHQ.do?method=listConsiderationHQ&page_flag=3&srch_prgrs_status_hq=C16204${p_srch_hq_reqman_nm}');">
													<th><a><@spring.message code="las.page.field.main.undecidedLong"/></a></th><!-- Not Approved -->
													<td>: <a>${cntNotapproved}</a></td>
												</tr>										
											
											<#else>
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642301_0000000355', '/clm/manage/myContract.do?method=listMyContract&srch_closed_yn=N&srch_step=C02501&closed_yn=N');">
													<th><a><@spring.message code="las.page.title.main.review"/></a></th>
													<td>: <a>${cntCont01}</a></td>
												</tr>
												<!-- 2014-01-31 Kevin Added-->
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642301_0000000355', '/clm/manage/myContract.do?method=listMyContract&closed_yn=Y');">
													<th><a><@spring.message code="las.page.title.main.closed"/></a></th>
													<td>: <a>${cntClosed}</a></td>
												</tr>
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642301_0000000355', '/clm/manage/myContract.do?method=listMyContract&closed_yn=N&srch_step=C02502');">
													<th><a><@spring.message code="las.page.title.main.consultation"/></a></th>
													<td>: <a>${cntCont02}</a></td>
												</tr>
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642301_0000000355', '/clm/manage/myContract.do?method=listMyContract&closed_yn=N&srch_step=C02503');">
													<th><a><@spring.message code="las.page.title.main.conclusion"/></a></th>
													<td>: <a>${cntCont03}</a></td>
												</tr>
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642301_0000000355', '/clm/manage/myContract.do?method=listMyContract&closed_yn=N&srch_step=C02504');">
													<th><a><@spring.message code="las.page.title.main.execution"/></a></th>
													<td>: <a>${cntCont04}</a></td>
												</tr>
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642301_0000000355', '/clm/manage/myContract.do?method=listMyContract&closed_yn=N&srch_step=C02505');">
													<th><a><@spring.message code="las.page.title.main.completion"/></a></th>
													<td>: <a>${cntCont05}</a></td>
												</tr>
												<!-- 신성우 추가 2014-04-22 -->
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827427_0000000336', '20130319154642402_0000000359', '/clm/manage/myContract.do?method=listMyContract&closed_yn=N&status_mode=myContractCCed');">
													<th><a><@spring.message code="las.page.field.main.cced"/></a></th>
													<td>: <a>${cntContCced}</a></td>
												</tr>
											</#if>
										</table>
									</div>
									<div class='sub_btm'></div>
								</div>
								
								<#if !(topRole == "HQ01" || topRole == "HQ02")>
								<!-- ② 자문 -->
								<div class='section'>
									<@spring.message code="las.page.field.main.advice"/>
									<span>[<#if topRole == "RA01" || topRole == "RB01">${lawCntTotal}<#elseif topRole == "RA02">${lawCntTotal-lawCntNoassign}<#else>${lawCntReg}</#if>]</span>							
								</div>
								
								<div class='section_sub' id='menu02' style='display:'>
									<div class='sub_top'></div>
									<div class='sub_mid'>
										<table>
											<#if topRole == "RA01" || topRole == "RB01">
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsult&isForeign=H&srch_reception=Y&srch_prgrs_status=V01');">
													<th><a><@spring.message code="las.page.field.main.unchosenLong"/></a></th>
													<td>: <a>${lawCntNoassign}</a></td>
												</tr>
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsult&isForeign=H&srch_reception=Y&srch_prgrs_status=V02');">
													<th><a><@spring.message code="las.page.field.main.inCheck"/></a></th>
													<td>: <a>${lawCntReview}</a></td>
												</tr>
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsult&isForeign=H&srch_reception=Y&srch_prgrs_status=S09');">
													<th><a><@spring.message code="las.page.field.main.tmpSave"/></a></th>
													<td>: <a>${lawCntTemp}</a></td>
												</tr>
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsult&isForeign=H&srch_reception=Y&srch_prgrs_status=V03');">
													<th><a><@spring.message code="las.page.field.main.reassign"/></a></th>
													<td>: <a>${lawCntResign}</a></td>
												</tr>
											<#elseif topRole == "RA02">
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsult&isForeign=H&srch_reception=Y&srch_prgrs_status=V02');">
													<th><a><@spring.message code="las.page.field.main.inCheck"/></a></th>
													<td>: <a>${lawCntReview}</a></td>
												</tr>
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsult&isForeign=H&srch_reception=Y&srch_prgrs_status=S09');">
													<th><a><@spring.message code="las.page.field.main.tmpSave"/></a></th>
													<td>: <a>${lawCntTemp}</a></td>
												</tr>
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsult&isForeign=H&srch_reception=Y&srch_prgrs_status=V03');">
													<th><a><@spring.message code="las.page.field.main.reassign"/></a></th>
													<td>: <a>${lawCntResign}</a></td>
												</tr>
											<#else>
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsultRequest&isForeign=H&srch_reception=Y&srch_prgrs_status=S02');">
													<th><a><@spring.message code="las.page.field.main.inCheck"/></a></th>
													<td>: <a>${lawCntReg}</a></td>
												</tr>
											</#if>
												<!-- 2014-03-03 Kevin. CC'ed  Added-->
												<tr onclick="Menu.detail3('frm', '_top', '20130319152827442_0000000337', '20130319155500478_0000000371', '/las/lawconsulting/lawConsult.do?method=listLawConsultCCed&isForeign=H&srch_reception=Y');">
													<th><a><@spring.message code="las.page.field.main.cced"/></a></th>
													<td>: <a>${lawCntCced}</a></td>
												</tr>
										</table>
									</div>
									<div class='sub_btm'></div>
								</div>

								<!-- ④ 계약관리 -->
								<#if isCntMng == "Y">
								<div class='section'>
	          						<@spring.message code="las.page.title.main.tab04"/> <span>[${cntContMngTotal}]</span>
								</div>
								<div class='section_sub' id='menu04' style='display:none'>
									<div class='sub_top'></div>
									<div class='sub_mid'>
										<table>
											<tr onclick="Menu.detail3('frm', '_top', '20130319155330501_0000000362', '20130319155330501_0000000362', '/clm/manage/myApproval.do?method=listMyApproval&list_mode=cnsdreq&isOrgMgr=N');">
												<th><a><@spring.message code="las.page.title.main.orgRcv"/></a></th>
												<td>: <a>${cntContMngOrgRcv}</a></td>
											</tr>
											<tr onclick="Menu.detail3('frm', '_top', '20130319155330749_0000000363', '20130319155330749_0000000363', '/clm/manage/completion.do?method=listAutoRenewApproval');">
												<th><a><@spring.message code="las.page.title.main.autoExp"/></a></th>
												<td>: <a>${cntContMngAutoExp}</a></td>
											</tr>
											<tr onclick="Menu.detail3('frm', '_top', '20130319155330773_0000000364', '20130319155330773_0000000364', '/clm/manage/registration.do?method=listRegistrationApproval');">
												<th><a><@spring.message code="las.page.title.main.aftconclReg"/></a></th>
												<td>: <a>${cntContMngAfterConReg}</a></td>
											</tr>
										
										</table>
									</div>
									<div class='sub_btm'></div>
								</div>
								</#if>
								
								<!-- ⑤ 날인 · 증명서류접수 
								<#if isCntSealorSign == "Y">
								<div class='section'>
	          						<@spring.message code="las.page.field.main.sealSign"/> <a href="javascript:Menu.detail3('frm', '_top', '20130418141001246_0000000444', '20130418141339964_0000000446', '/clm/sign/signManage.do?method=listSignMng&forwardFrom=MAIN');"><span>[${cntSealSign}]</span></a>
								</div>
								</#if>
								-->
								
								</#if><!--HQ end-->
								
							</div>
							<div class='box_table_btm'></div>
						</div>
							
				</#if>
				</div>				
			</div>
		</div>
	</div>
	<div id="hide" class="hide"  onClick="left_menu_Toggle('left_colum', 'las', 'hide');" title="hide"></div>
	<div id="show" class="show"  onClick="left_menu_Toggle('left_colum', 'las', 'show');" title="show" style="display:none;" ></div>
</div>

<!-- 로딩상 넘어온 메뉴를 클릭해 준다. -->
<script language="javascript">
<#if beforeMenuId != "" >
	leftMenuClick(${beforeLevel2}, ${beforeLevel3}, ${beforeMenuCnt}, 'Y');
</#if>	
</script>

</body>
</html>