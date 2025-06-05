<#import "/spring.ftl" as spring />

<jsp:include page="/WEB-INF/jsp/common/common.jsp" />

<#assign CSS_HP        	=  '/style/shri/ko' + '/HP_top.css' />
<#assign CSS_CO        	=  '/style/shri/ko' + '/common.css' />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Hermes</title>
</head>
<LINK href="<@spring.url '${CSS_HP}'/>" type="text/css" rel="stylesheet">
<LINK href="<@spring.url '${CSS_CO}'/>" type="text/css" rel="stylesheet">

<script language="javascript" src="<@spring.url '/script/shri/common.js'/>"></script>
<script language="javascript" src="<@spring.url '/script/secfw/jquery/jquery.js'/>"></script>
<script language="javascript" src="<@spring.url '/script/shri/menu.js'/>"></script>

<script language="javascript">

/*
* 써브메뉴의 목록을 지워준다. 
*/
 function HideAllSubMenu(){
	<#assign menuIdCnt = 0 />
	<#list menuList as tt>
		<#if tt.menu_level = 1>
			<#assign menuIdCnt = menuIdCnt + 1 /> 
				divHidden(sub_menu0${tt.menu_img});
		</#if>
	</#list>
 }

/*
* 써브메뉴의 의 클레스를 변경해준다.  
*/
 function SubMenuClassChg(class_nbr){
 
	  document.getElementById("menu_sub").className   	= "sub_menu_0"+class_nbr;
	  //document.getElementById("sub_img").className    = "sub_img_0"+class_nbr;
 }
/*
*  시스템 타이틀 그림 변경. 
*/
 
 function SubTitleChg(class_nbr){ 
	  document.getElementById("title_img").className  = "title_img0"+class_nbr;	 
	  document.getElementById("sub_img").className  = "sub_img_0"+class_nbr;	 

 }
/*
* 삼성 건강연구소 그림 Clcik 
*/
 function main(){
 	var frm = document.getElementById("frm");
	frm.action 					= "<@spring.url '/secfw/main.do' />";
	frm.method.value 			= "forwardMainPage";	
	frm.target 					= "_top";	
	frm.submit(); 		
} 
/*
* 선택된 서브  메뉴 의 항목을 띄워준다.  Menu.js go참조바람
*/
 function SetMenuDetail(formId, actionUrl, methodNm, menuId, menuNm, targetNm){
 	var frm = document.getElementById("frm");
		frm.action 							= actionUrl;
		frm.target 							= targetNm;
		frm.method.value 					= methodNm;
		frm.targetMenuId.value 				= menuId;
		frm.selected_menu_id.value 			= menuId;
 		frm.selected_menu_detail_id.value  	= menuId;
 		frm.submit();
 } 
/*
 * 페이지 로그아웃
 */
function logout(){
 	var frm = document.getElementById("frm");
	frm.action 					= "<@spring.url '/secfw/user.do' />";
	frm.method.value 			= "shriLogOut";	
	frm.target 					= "_top";	
	frm.submit();
    top.window.close();  
 }


</script>
<body>
<form id="frm" method="post" autocomplete="off">
	<!-- method -->
	<input type="hidden" name="method">
	<!-- targetMenuId -->
	<input type="hidden" name="targetMenuId">
	<input type="hidden" name="target_menu_id">
	<!-- selected_menu_id -->
	<input type="hidden" name="selected_menu_id">
	<input type="hidden" name="selected_menu_nm">
	<input type="hidden" name="selected_menu_detail_id" id="selected_menu_detail_id">	
	<input type="hidden" name="menuLogEnable" value="Y">		
</form>

<div class='top_menu'>
	<div class='menuWrap'>
		<div class='logo_hp fL' onClick="main()"></div>
		<div class='menu_hm fL'>
			
			
			<div class='menu_header fR'>
				<div class="tbl_l fL"></div>
				<div class="tbl_blank fL"></div>
				<div class="tbl_menu01 fL"  onclick="javascript:main()"></div>
				<div class="tbl_blank fL"></div>
				<div class="tbl_line fL"></div>
				<div class="tbl_blank fL"></div>
				<div class="tbl_menu03 fL"  onclick="javascript:logout()" ></div>
				<div class="tbl_blank fL"></div>
				<div class="tbl_r fL"></div>
			</div>

			
					
		<div class="menu_main fR">
			<!--ul-->
				<#assign menuIdCnt = 0 , menuMax = 1/>
				<#list menuList as tt>
					<#if tt.menu_level = 1>
					<#assign menuMax = menuMax + 1 /> 				
						<#assign menuIdCnt = menuIdCnt + 1 /> 
							<div class='menu0${tt.menu_img}' onMouseover="className='menu0${tt.menu_img}_over';HideAllSubMenu();SubMenuClassChg('${tt.menu_img}');divShow(sub_menu0${tt.menu_img})" onMouseOut="className='menu0${tt.menu_img}'"  id="a_${tt.menu_img}" onclick="javascript:Menu.go('frm', '<@spring.url "/secfw/main.do"/>', 'forwardSubFrame', '${tt.menu_id}', '${tt.menu_nm}', 'subFrame');Menu.menuTitleEnable('frm', 'a_${tt.menu_img}', 'selected_thub', 'thub');SubTitleChg('${tt.menu_img}')" 
								<#if freeMarkerMap.topMenuLevel = "2">
									//onMouseOver="javascript:Menu.subMenuDisplay('menu_${tt.menu_img}', 'm_layer_thub', 'Y');" 
									//onMouseOut="javascript:Menu.subMenuDisplay('menu_${tt.menu_img}', 'm_layer_thub', 'Y');"
								</#if> 
								<#if freeMarkerMap.targetMenuId = tt.menu_id> 
									class="selected_thub"
								<#else>
									class="thub"
								</#if>
									>
							</div>
							
							<#if menuMax <= menuSize?number >	
						    	<div class='menu_l'></div>
							</#if> 
						    
					</#if>
				</#list>
			<!--/ul-->
		</div>
		
	
		</div>
	</div>
</div>



<!-- Menu Layer -->
<div class='sub_menu_01'  id="menu_sub" name="menu_sub" >
<#assign selectedMenu 		= ""/> <!--선택된 메뉴 아이디-->


<#if freeMarkerMap.topMenuLevel = "2">
	<#assign LargeMenuIdCnt = 0 ? int />	
	<#assign menuId 		= "" />
	<#assign RoadRoot 		= "" />	
	<#assign checkChildCnt 	= 0 ? int />
	<#list menuList as subMenu>
		<#if subMenu.up_menu_id 	= "root">
			<#assign LargeMenuIdCnt = LargeMenuIdCnt + 1 />
			<#assign menuId 		= subMenu.menu_id />
			<#assign childCnt 		= subMenu.child_cnt />
			<#assign checkChildCnt 	= 0 />
		</#if>
		<#if subMenu.up_menu_id 	= "root">
		<#if LargeMenuIdCnt = 1>
		<div style="display:;" 		id="sub_menu0${subMenu.menu_img}" class='sub_menu0${subMenu.menu_img}'>
		<#assign RoadRoot = subMenu.menu_img />
		<#else>
		<div style="display:non;" 	id="sub_menu0${subMenu.menu_img}" class='sub_menu0${subMenu.menu_img}'>
		<#assign RoadRoot = subMenu.menu_img />		
		</#if>
		</#if>
				<#if subMenu.menu_level = 2>
				<#assign checkChildCnt = checkChildCnt + 1 />
		    	<li>
		    		<a href="javascript:SetMenuDetail('frm', '<@spring.url "/secfw/main.do"/>', 'forwardSubFrame', '${subMenu.menu_id}', '${subMenu.menu_nm}', 'subFrame');SubTitleChg('${RoadRoot}')">${subMenu.menu_nm}
		    		</a>
		    	</li>
		    	</#if>
		<#if checkChildCnt = childCnt>
		</div>
		</#if>		
		
		<#if 	 selected_menu_detail_id 	= subMenu.menu_id && subMenu.up_menu_id ="root" >
		<#assign selectedMenu 				= subMenu.menu_img /> <!--선택된 메뉴 아이디-->	
		<#elseif selected_menu_detail_id 	= subMenu.menu_id && subMenu.up_menu_id !="root" >	
		<#assign selectedMenu 				= RoadRoot /> <!--선택된 메뉴 아이디-->	
		</#if>
		
	</#list>		
</#if>
</div>	


<div class='sub_img_01' 	 id="sub_img"   name="sub_img">
	<div class='title_img01' id="title_img" name="id="title_img"></div>
</div>
		
		
	<div class="header">
		<h1><a href="<@spring.url '/secfw/main.do?method=forwardMainPage'/>" target="_top"></a></h1>
		<fieldset class="search">
			<legend>
		  	</legend>
		</fieldset>		
		
	
	
	</div>
	
<!-- 로딩상 넘어온 메뉴를 클릭해 준다. -->
<script language="javascript">
	//alert('${selected_menu_detail_id}'+"/"+'${selectedMenu}'); 

<#if selected_menu_detail_id!="" >
	HideAllSubMenu();
	SubMenuClassChg('${selectedMenu}');
	SubTitleChg('${selectedMenu}');
	divShow(sub_menu0${selectedMenu})
</#if>	
</script>	
	
</body>
</html>
