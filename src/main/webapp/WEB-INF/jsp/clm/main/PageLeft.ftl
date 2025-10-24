<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<#import "/spring.ftl" as spring />

<#assign CSS        	=  '/style/clm/${localeCd}' />
<#assign IMAGE        	=  '/images/clm/${localeCd}' />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>


<LINK href="${CSS}/menu.css" type="text/css" rel="stylesheet">

<script language="javascript" src="<@spring.url '/script/clms/common.js'/>"></script>
<script language="javascript" src="<@spring.url '/script/secfw/jquery/jquery.js'/>"></script>
<script language="javascript" src="<@spring.url '/script/clms/menu.js'/>"></script>

<script language="javascript">

 
$(document).ready(function() {
	var frm = document.frm;

	if ('${initUrl}' == '') {
		return;
	}

	frm.target = "bodyFrame";
	frm.action = '<@spring.url "${initUrl}" />';
	
	frm.submit();
});

</script>
</head>
<body>
<form name="frm" id="frm" method="post" autocomplete="off">
	<!-- method -->
	<input type="hidden" name="method">
	<!-- targetMenuId -->
	<input type="hidden" name="targetMenuId">
	<!-- selected_menu_id -->
	<input type="hidden" name="selected_menu_id">
	<input type="hidden" name="selected_menu_nm">
	<input type="hidden" name="menuLogEnable" value="Y">		
</form>
<div id="leftWrap">
	<div id="leftMenu">
		<div id="left_colum">
			<div class="nav nav3">
				<#if localeCd = "ko">
				<h2>${menuNm}</h2>
				<#elseif localeCd = "fr">
				<h2>${menuNmFra}</h2>
				<#elseif localeCd = "de">
				<h2>${menuNmDeu}</h2>
				<#elseif localeCd = "it">
				<h2>${menuNmIta}</h2>
				<#elseif localeCd = "es">
				<h2>${menuNmEsp}</h2>
				<#else>
				<h2>${menuNmEng}</h2>
				</#if>
				<ul>
					<#assign beforLevle        	=  0 ? int />	
					<#assign nowLevle          	=  0 ? int />	
					<#assign selectedMenu  	    =  "" />
					<#assign level2Count		=  0 ? int />		
					<#assign level3Count        =  0 ? int />
					<#assign multi_menu_nm		=  "" />
					
					<#list menuList as tt>	
						
						<!--계약서담당자변경 RA00, RD01은 보이게하고 RD02이면 조직장일 때 부이게 한다-->
						<#if tt.menu_id = "20110803091537677_0000000066" && chgPersonflag = false>
						
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
						
						<#if localeCd='en'><#assign multi_menu_nm = tt.menu_nm_eng /><#else><#assign multi_menu_nm = tt.menu_nm /></#if>
						
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
			</div>
		</div>
	</div>
	<div id="hide" class="hide"  onClick="left_menu_Toggle('left_colum', 'las', 'hide');" title="hide"></div>
	<div id="show" class="show"  onClick="left_menu_Toggle('left_colum', 'las', 'show');" title="show" style="display:none;" ></div>
</div>

<!-- 로딩상 넘어온 메뉴를 클릭해 준다. -->
<script language="javascript">
<#if beforeMenuId != "" >
	//alert('${selectedMenuId}');
	//alert('${beforeMenuId}'+':'+ '${beforeLevel2}'+':'+'${beforeLevel3}'+':'+'${beforeMenuCnt}');
	leftMenuClick(${beforeLevel2}, ${beforeLevel3}, ${beforeMenuCnt}, 'Y');
</#if>	
</script>

</body>
</html>