<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<#import "/spring.ftl" as spring />

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<#assign CSS        	=  '/style/clm/${localeCd}' />
<#assign IMAGE        	=  '/images/clm/${localeCd}' />

<html xmlns="http://www.w3.org/1999/xhtml"  lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><@spring.message code="clm.main.title" /></title>


<LINK href="${CSS}/menu.css" type="text/css" rel="stylesheet">

<script language="javascript" src="<@spring.url '/script/secfw/jquery/jquery.js'/>" type="text/javascript"></script>
<script language="javascript" src="<@spring.url '/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<@spring.url '/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<@spring.url '/script/clms/common.js'/>" type="text/javascript"></script>
<script language="javascript" src="<@spring.url '/script/clms/menu.js'/>"></script>

<script language="javascript">

function divShow(obj) {
	obj.style.display = 'block';
}

function divHidden(obj) {
	obj.style.display = 'none';
}

//각 팝업 오픈시 target을 달리 주고 싶을 때
function PopUpWindowOpen2(surl, popupwidth, popupheight, bScroll, popName) {
	if( popupwidth  > window.screen.width )
		popupwidth = window.screen.width;
	if( popupheight > window.screen.height )
		popupheight = window.screen.height; 
		
	if( isNaN(parseInt(popupwidth)) ){
		Top  = (window.screen.availHeight - 600) / 2;
		Left = (window.screen.availWidth  - 800) / 2;
	} else {
		Top  = (window.screen.availHeight - popupheight)  / 2;
		Left = (window.screen.availWidth  - popupwidth) / 2;
	}

	if (Top < 0) Top = 0;
	if (Left < 0) Left = 0;
	
	popupwidth = parseInt(popupwidth) + 10 ;
	popupheight = parseInt(popupheight) + 29 ;
	
	
	var Feture = "toolbar=0, location=0, status=1, menubar=0, scrollbars=" + (bScroll ? "yes" : "no") + ", resizable=no, top="+Top+", left="+Left+", width="+popupwidth+", height="+popupheight;
	PopUpWindow = window.open(surl, popName , Feture);

	PopUpWindow.focus();
	
}

//공무집행 대응 변호사 팝업
function openContactUsPop(){
	PopUpWindowOpen2('/las/openPopME.do', '840', '583', false, 'PopUpContactUsClm');		
}
//업무분야별 전문 변호사 팝업
function openExpertPop(){
	PopUpWindowOpen2('/las/openPopMSC.do', '840', '600', false, 'PopUpExpertClm');
}
</script>
</head>
<body onLoad="">
<form name="frm" id="frm" method="post" autocomplete="off">
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
	<input type="hidden" name="chgLangflag">
	<input type="hidden" name="flag" value=""/>	
</form>

<div id="topWrap">
	<!-- header start-->
	<div id="header">
		<div id="logo">
			<a href="javascript:Menu.main('frm','C')" class="h1_logo"><img src="${IMAGE}/common/${titleImage}.gif"  border="0" alt="<@spring.message code="clm.page.field.logo.title" />" title="<@spring.message code="clm.page.field.logo.title" />" /></a><!-- 계약관리 시스템 -->
			<span style='margin-left:7px'><img src="${IMAGE}/common/pncacc.gif"  border="0" alt="PRIVILEGED AND CONFIDENTIAL ATTORNEY-CLIENT COMMUNICATION" title="PRIVILEGED AND CONFIDENTIAL ATTORNEY-CLIENT COMMUNICATION"/></span>
			<!-- utility -->
			<ul class="utility">
				<li><a href="#" class="user_request"><img src="${IMAGE}/icon/ico_user.gif" alt="user" title="user"/></a> <a href="#" class="user">${userInfo}</a> </li>
				<li> <a href="javascript:Menu.main('frm','C')"><img src="${IMAGE}/icon/icon_home.gif" alt="Home" title="Home"/></a> </li>
				<li><a href="javascript:Menu.goSiteMap('frm','C');" ><img src="${IMAGE}/icon/icon_sitemap.gif" alt="Sitemap" title="Sitemap"/></a></li>   
				<!--<li><a href="#">Impersonate</a></li>-->
				<!--<li class="bg_none"><a href="javascript:Menu.logout('frm','C')">Logout</a></li>-->
				<!--<li class="confidential"><img src="${IMAGE}/icon/ico_confidential2.gif" alt="confidential"/></li>-->
				<li style='margin-top:-2px;'>
				<#if localeCd='ko'>
					<a href="#" onclick="javascript:frm.chgLangflag.value='ko';Menu.main('frm','C');" style='background:url(${IMAGE}/btn/arr_dot_bg.gif) no-repeat right 1px;'>
					<img src="${IMAGE}/btn/btn_on_l_kor.gif" title='<@spring.message code="clm.page.field.menu.koLang" />' /></a><a href="#" onclick="javascript:frm.chgLangflag.value='en';Menu.main('frm','C');" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('lang_en','','${IMAGE}/btn/btn_on_l_eng.gif',1)"><img id="lang_en" src="${IMAGE}/btn/btn_off_l_eng.gif"  title='<spring.message code="clm.page.field.menu.enLang" />' /></a>
				<#else>
					<a href="#" onclick="javascript:frm.chgLangflag.value='ko';Menu.main('frm','C');" style='background:url(${IMAGE}/btn/arr_dot_bg.gif) no-repeat right 1px;' onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('lang_ko','','${IMAGE}/btn/btn_on_l_kor.gif',1)">
					<img id="lang_ko" src="${IMAGE}/btn/btn_off_l_kor.gif" title='<@spring.message code="clm.page.field.menu.koLang" />' /></a><a href="#" onclick="javascript:frm.chgLangflag.value='en';Menu.main('frm','C');"><img src="${IMAGE}/btn/btn_on_l_eng.gif"  title='<@spring.message code="clm.page.field.menu.enLang" />' /></a>
				</#if>
				</li>
				
				<li style='margin-top:-4px; margin-left:5px;'  onMouseOver='divShow(contactus)' onMouseOut='divHidden(contactus)'>
                	<a href="javascript:openContactUsPop();" style='background:none;'><img src="${IMAGE}/new2011/contactus_btn.gif" title="<@spring.message code="clm.page.field.menu.execLawyer" />"></a><!--공무집행  대응 변호사-->
                </li>				
            </ul>
			<!-- //utility -->
	  	</div>
	  	<div id='contactus' style='display:none;position:absolute; top:10px; right:137px; z-index:1'>
			<img src="${IMAGE}/new2011/contactus_tip.png" alt="<@spring.message code="clm.page.field.menu.execOfficial" />" title="<@spring.message code="clm.page.field.menu.execOfficial" />"><!--공정거래위원회 조사 등 공무집행에 대응할 사업부별 담당 변호사입니다.-->
		</div>
		<hr/>
		
		<!-- gnb -->
        <div class="gnb">
			<ul>
				<!--li-->
				<#assign menuIdCnt = 0 , menuMax = 1/>
				<#assign beforeMenuIdCnt = 0 />
				<#assign beforeSubMenuIdCnt = 0 />
				<#list menuList as tt>
					<#if tt.menu_level = 1>
					<#assign menuMax = menuMax + 1 /> 				
						<#assign menuIdCnt = menuIdCnt + 1 />
						<li id="menu_${menuIdCnt}" onMouseOver="javascript:subMenuSet(${menuIdCnt})">
						 	<a href="javascript:Menu.go('frm', '<@spring.url "/secfw/main.do"/>', 'forwardSubFrame', '${tt.menu_id}', 'subFrame');">
						 		<#if localeCd='ko'>${tt.menu_nm}<#else>${tt.menu_nm_eng}</#if>
						 	</a>
							<ul id="gnb_2depth_0${menuIdCnt}" class="gnb_2depth_0${menuIdCnt} gnb_2depth">
							<#assign subMenuIdCnt = 0 />
							<#list menuList as subMenu>
							<#if tt.menu_id = subMenu.up_menu_id>
								<!-- 계약서담당자변경 제어 -->
								<#if subMenu.menu_id = "20110803091537677_0000000066">
									<#if chgPersonflag = true>
										<#assign subMenuIdCnt = subMenuIdCnt + 1 />
										<li id="menu_${menuIdCnt}_${subMenuIdCnt}">
											<a href="javascript:Menu.go('frm', '<@spring.url "/secfw/main.do"/>', 'forwardSubFrame', '${subMenu.menu_id}', 'subFrame')">
												<#if localeCd='ko'>${subMenu.menu_nm}<#else>${subMenu.menu_nm_eng}</#if>
											</a>
										</li>
										<#assign beforeSubMenuIdCnt = subMenuIdCnt />																		
									</#if>
								<#else>
									<#assign subMenuIdCnt = subMenuIdCnt + 1 />
									<li id="menu_${menuIdCnt}_${subMenuIdCnt}">
										<a href="javascript:Menu.go('frm', '<@spring.url "/secfw/main.do"/>', 'forwardSubFrame', '${subMenu.menu_id}', 'subFrame')">
											<#if localeCd='ko'>${subMenu.menu_nm}<#else>${subMenu.menu_nm_eng}</#if>
										</a>
									</li>
									<#assign beforeSubMenuIdCnt = subMenuIdCnt />	
								</#if>						
							</#if>
					    	</#list>
					    	</ul>
						</li>				    
					</#if>
				<#assign beforeMenuIdCnt = menuIdCnt />
				</#list>
				<!--/li-->
			</ul>
			 <span class="collapse"><img src="${IMAGE}/icon/ico_collapse.png" alt="collapse"  title="collapse" class="png24 cp" onclick="menu_Toggle(this, 'clm', 'logo', 'top');" /></span>
		</div>
		<!-- //gnb -->
	</div>
	<!-- header end-->
</div>



	
<!-- 로딩상 넘어온 메뉴를 클릭해 준다. -->
<script language="javascript">


</script>	

<!-- 세션을 유지하기 위한 iframe하나를 숨겨돈다. iframe안에서 페이지를 5분마다 refresh 한다-->
<iframe name='iframeTemp' src='<@spring.url "/clm/pageReload.do"/>' frameborder='0' style='width:0px;height:0px'></iframe>	
</body>
</html>
