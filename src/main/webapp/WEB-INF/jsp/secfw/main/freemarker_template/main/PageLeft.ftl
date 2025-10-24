<#import "/spring.ftl" as spring />

<jsp:include page="/WEB-INF/jsp/common/common.jsp" />

<#assign CSS_TEMP        =  '/style/shri/ko' + '/HP_left.css' />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>myCoach</title>
<LINK href="<@spring.url '${CSS_TEMP}'/>" type="text/css" rel="stylesheet">
<script language="javascript" src="<@spring.url '/script/shri/common.js'/>"></script>
<script language="javascript" src="<@spring.url '/script/secfw/jquery/jquery.js'/>"></script>
<script language="javascript" src="<@spring.url '/script/shri/menu.js'/>"></script>

<script language="javascript">
/**
 * 
 */
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


<table id='comOut'>
	<tr>
		<td class='leftBase'>		
			<div class='leftMenuOut'>
	
	
		<#assign beforLevle        	=  0? int />	
		<#assign nowLevle          	=  0? int />	
		<#assign beforMenuTitle     =  "" />	
		<#assign nowMenuId        	=  "" />			
		<#assign beforMenuId        =  "" />			
		<#assign menuLevle_second  	=  0 ? int />	

		<#assign selectedMenu  	    =  "" />		
		<#assign level3Count        =  0?int>
		
		<#list menuList as tt>	
		<#assign nowLevle =tt.menu_level />	
		
				<#if nowLevle =  2 && beforLevle = 0>
				<#assign menuLevle_second = menuLevle_second + 1 />	
				<#assign beforMenuTitle	  = tt.menu_nm />		
				<#assign nowMenuId		  = "submenu0"+menuLevle_second />		
				<#assign beforMenuId	  = nowMenuId />			
												
					<div id='leftMenu' onClick="menuClick(${nowMenuId});" >
					<a 
					<#if tt.menu_url !="">
					href="javascript:Menu.go('frm', '${tt.menu_url}', '', '${tt.menu_id}', '${tt.menu_nm}', 'bodyFrame');" 
					</#if>
					>${tt.menu_nm}
					</a>
					</div>
					
					<table id='${nowMenuId}' class='subMenuTb' cellpadding='0' cellspacing='0'>  									         
					<tr>
						<td class='top'></td>
					</tr>
					<tr>
						<td class='cen'>
						<div class='subMenu' ><a href="javascript:menuClick(${nowMenuId});Menu.go('frm', '${tt.menu_url}', '', '${tt.menu_id}', '${tt.menu_nm}', 'bodyFrame')" >${tt.menu_nm}</a></div>
				<#elseif  nowLevle =  2 && beforLevle = 2>
				<#assign menuLevle_second = menuLevle_second + 1 />	
				<#assign beforMenuTitle	  = tt.menu_nm />		
				<#assign nowMenuId		  = "submenu0"+menuLevle_second />		
				<#assign beforMenuId	  = nowMenuId />	
						</td>
					</tr>
					<tr>
						<td class='btm'></td>
					</tr>
				</table>								
								
					<div id='leftMenu' onClick="menuClick(${nowMenuId});" >
					<a 
					<#if tt.menu_url !="">
					href="javascript:Menu.go('frm', '${tt.menu_url}', '', '${tt.menu_id}', '${tt.menu_nm}', 'bodyFrame');" 
					</#if>
					class="1depth_selected">${tt.menu_nm}
					</a>
					</div>
								
					<table id='${nowMenuId}'  class='subMenuTb' cellpadding='0' cellspacing='0'>  			
					<tr>
						<td class='top'></td>
					</tr>
					<tr>
						<td class='cen'>
						<div class='subMenu' ><a href="javascript:menuClick(${nowMenuId});Menu.go('frm', '${tt.menu_url}', '', '${tt.menu_id}', '${tt.menu_nm}', 'bodyFrame')">${tt.menu_nm}</a></div>
				<#elseif  nowLevle =  2 && beforLevle = 3>
				<#assign menuLevle_second = menuLevle_second + 1 />	
				<#assign beforMenuTitle	  = tt.menu_nm />		
				<#assign nowMenuId		  = "submenu0"+menuLevle_second />		
				<#assign beforMenuId	  = nowMenuId />		
													
						</div>	
						</td>
					</tr>
					<tr>
						<td class='btm'></td>
					</tr>
				</table>

					<div id='leftMenu' onClick="menuClick(${nowMenuId});" >
					<a 
					<#if tt.menu_url !="">
					href="javascript:Menu.go('frm', '${tt.menu_url}', '', '${tt.menu_id}', '${tt.menu_nm}', 'bodyFrame');" 
					</#if>
					class="1depth_selected">${tt.menu_nm}
					</a>
					</div>

					<table id='${nowMenuId}'  class='subMenuTb' cellpadding='0' cellspacing='0' > 			
					<tr>
						<td class='top'></td>
					</tr>
					<tr>
						<td class='cen'>
						<div class='subMenu' ><a href="javascript:menuClick(${nowMenuId});Menu.go('frm', '${tt.menu_url}', '', '${tt.menu_id}', '${tt.menu_nm}', 'bodyFrame')">${tt.menu_nm}</a></div>
				<#elseif  nowLevle =  3 && beforLevle = 2>
							<div class='subFont'>						
								<a href="javascript:Menu.go('frm', '${tt.menu_url}', '', '${tt.menu_id}', '${tt.menu_nm}', 'bodyFrame');" class="2depth_selected">${tt.menu_nm}</a></br>

				<#elseif  nowLevle =  3 && beforLevle = 3>

								<a href="javascript:Menu.go('frm', '${tt.menu_url}', '', '${tt.menu_id}', '${tt.menu_nm}', 'bodyFrame');" class="2depth_selected">${tt.menu_nm}</a></br>
				
				</#if>	
				
				<#assign beforLevle        =  nowLevle />		
	
				<#if tt.menu_id 		  = selectedMenuId>
				<#assign selectedMenu  	  =  nowMenuId />			
				</#if>
						
		</#list>
			<#if beforLevle != 0>
			<#if beforLevle = 3>	
			    </div>
			</#if>			    
			    </td>
			    </tr>
					<tr>
						<td class='btm'></td>
					</tr>
				</table>
				
			</#if>

			</div>
		</td>
	</tr>
</table>


<!-- 로딩상 넘어온 메뉴를 클릭해 준다. -->
<script language="javascript">
<#if selectedMenu!="" >
	menuClick('${selectedMenu}');
</#if>	
</script>

</body>
</html>