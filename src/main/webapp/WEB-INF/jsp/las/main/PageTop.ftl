<!DOCTYPE html>
<#import "/spring.ftl" as spring />
<#assign CSS   = '/style/las/${localeCd}' />
<#assign IMAGE = '/images/las/${localeCd}' />
<html>
<head>
<meta charset="utf-8" />

<title><spring:message code="las.main.title" /></title>
<link href="${CSS}/menu.css" type="text/css" rel="stylesheet" />
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
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
	PopUpWindowOpen2('/las/openPopME.do', '840', '583', false, 'PopUpContactUsLas');		
}

//업무분야별 전문 변호사 팝업
function openExpertPop(){
	PopUpWindowOpen2('/las/openPopMSC.do', '840', '620', false, 'PopUpExpertClm');
}

</script>
<!-- select 디자인 -->
	<script type="text/javascript">
		$(document).ready(function(){	
	
			if (!$.browser.opera) {
	    
				
				$('select.lang_select').each(function(){
					var title = $(this).attr('title');
					if( $('option:selected', this).val() != ''  ) title = $('option:selected',this).text();
					$(this)
						.css({'z-index':10,'opacity':0,'-khtml-appearance':'none'})
						.after('<span class="lang_select">' + title + '</span>')
						
						.change(function(){
							val = $('option:selected',this).text();
							$(this).next().text(val);
							})
				});
				
			};
			
		});
	</script>
	<!-- // select 디자인 -->
</head>
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
	<input type="hidden" name="chgLangflag">
	<input type="hidden" name="flag" value=""/>	
</form>
<div id="topWrap">
	<div id="header">
		<div id='util'>
			<div id="logo">
				<a href="javascript:Menu.main('frm','L')" class="h1_logo"><#if compCd = 'C60' || compCd = 'C10'><img src="${IMAGE}/common/sys_logo2.gif"  border="0"  /><#else><img src="${IMAGE}/common/sys_logo_${compCd}.gif"  border="0"  /></#if></a>
				<span class='pncacc'><img src="${IMAGE}/common/pncacc.gif"  border="0" alt="PRIVILEGED AND CONFIDENTIAL ATTORNEY-CLIENT COMMUNICATION" title="PRIVILEGED AND CONFIDENTIAL ATTORNEY-CLIENT COMMUNICATION" /></span>
				<!-- utility -->
				<ul class="utility">
					<li><a class="user" title="${roleNm}">${userInfo}</a></li>
					<li><a href="javascript:Menu.main('frm','L')"><img src="${IMAGE}/icon/icon_home.gif" alt="Home" title="Home"/></a><a href="javascript:Menu.goSiteMap('frm','L');"><img src="${IMAGE}/icon/icon_sitemap.gif" alt="Sitemap" title="Sitemap"/></a></li>   
					<li class="lang_sel">
						<select name="chgLangflagSel" class="lang_select" onChange="javascript:frm.chgLangflag.value=this.value;Menu.main('frm','L');">
							    <option value="en" <#if localeCd='en'>selected</#if>>English</option>
							    <option value="fr" <#if localeCd='fr'>selected</#if>>Français</option>
						<!--	    <option value="de" <#if localeCd='de'>selected</#if>>Deutsch</option> -->
						 </select>
					</li>
				</ul>
				<!-- // utility -->
		  	</div>
		  </div>
	  	
		
		
		
		<div id='gnbWrap'>
	        <div class="gnb">
				<ul>
					<!--li-->
					<#assign menuIdCnt = 0 , menuMax = 1/>
					<#assign beforeMenuIdCnt = 0 />
					<#assign beforeSubMenuIdCnt = 0 />
					<#list menuList as tt>
					
	
					<#if tt.menu_id = "20110803155109700_0000000106">
						<#if lawfirmAccessFlag = true>
							<#if tt.menu_level = 1>
							<#assign menuMax = menuMax + 1 /> 				
								<#assign menuIdCnt = menuIdCnt + 1 />
								<li id="menu_${menuIdCnt}" onMouseOver="javascript:subMenuSet(${menuIdCnt})">
								 	<a href="javascript:Menu.go('frm', '<@spring.url "/secfw/main.do"/>', 'forwardSubFrame', '${tt.menu_id}', 'subFrame');">
										<#if localeCd='ko'>${tt.menu_nm}<#elseif localeCd='fr'>${tt.menu_nm_fra}<#elseif localeCd='de'>${tt.menu_nm_deu}<#elseif localeCd='it'>${tt.menu_nm_ita}<#elseif localeCd='es'>${tt.menu_nm_esp}<#else>${tt.menu_nm_eng}</#if>
									</a>
									<ul id="gnb_2depth_0${menuIdCnt}" class="gnb_2depth_0${menuIdCnt} gnb_2depth">
									<#assign subMenuIdCnt = 0 />
									<#list menuList as subMenu>
									<#if tt.menu_id = subMenu.up_menu_id>
										<#assign subMenuIdCnt = subMenuIdCnt + 1 />
										<li id="menu_${menuIdCnt}_${subMenuIdCnt}">
											<a href="javascript:Menu.go('frm', '<@spring.url "/secfw/main.do"/>', 'forwardSubFrame', '${subMenu.menu_id}', 'subFrame')">
												<#if localeCd='ko'>${subMenu.menu_nm}<#elseif localeCd='fr'>${subMenu.menu_nm_fra}<#elseif localeCd='de'>${subMenu.menu_nm_deu}<#elseif localeCd='it'>${subMenu.menu_nm_ita}<#elseif localeCd='es'>${subMenu.menu_nm_esp}<#else>${subMenu.menu_nm_eng}</#if>
											</a>
										</li>
										<#assign beforeSubMenuIdCnt = subMenuIdCnt />								
									</#if>
							    	</#list>
							    	</ul>
								</li>						    
							</#if>
						</#if>
					<#elseif tt.menu_id = "20110725131147621_0000000007">
					   <!-- 2013-01-16 IP센터도 신_통계내용이 보여야한다-->
						<#if statisticsViewFlag = true || statisticsViewFlag = false>
							<#if tt.menu_level = 1>
							<#assign menuMax = menuMax + 1 /> 				
								<#assign menuIdCnt = menuIdCnt + 1 />
								<li id="menu_${menuIdCnt}" onMouseOver="javascript:subMenuSet(${menuIdCnt})">
								 	<a href="javascript:Menu.go('frm', '<@spring.url "/secfw/main.do"/>', 'forwardSubFrame', '${tt.menu_id}', 'subFrame');">
										<#if localeCd='ko'>${tt.menu_nm}<#elseif localeCd='fr'>${tt.menu_nm_fra}<#elseif localeCd='de'>${tt.menu_nm_deu}<#elseif localeCd='it'>${tt.menu_nm_ita}<#elseif localeCd='es'>${tt.menu_nm_esp}<#else>${tt.menu_nm_eng}</#if>
									</a>
									<ul id="gnb_2depth_0${menuIdCnt}" class="gnb_2depth_0${menuIdCnt} gnb_2depth">
									<#assign subMenuIdCnt = 0 />
									<#list menuList as subMenu>
									<#if tt.menu_id = subMenu.up_menu_id>
										<#assign subMenuIdCnt = subMenuIdCnt + 1 />
										<li id="menu_${menuIdCnt}_${subMenuIdCnt}">
											<a href="javascript:Menu.go('frm', '<@spring.url "/secfw/main.do"/>', 'forwardSubFrame', '${subMenu.menu_id}', 'subFrame')">
												<#if localeCd='ko'>${subMenu.menu_nm}<#elseif localeCd='fr'>${subMenu.menu_nm_fra}<#elseif localeCd='de'>${subMenu.menu_nm_deu}<#elseif localeCd='it'>${subMenu.menu_nm_ita}<#elseif localeCd='es'>${subMenu.menu_nm_esp}<#else>${subMenu.menu_nm_eng}</#if>
											</a>
										</li>
										<#assign beforeSubMenuIdCnt = subMenuIdCnt />								
									</#if>
							    	</#list>
							    	</ul>
								</li>						    
							</#if>
						</#if>				
					<#elseif tt.menu_id = "20130320132153700_0000000335">
					<!-- 소송관리 메뉴 view여부 -->
						<#if slmsViewFlag = true>
							<#assign menuMax = menuMax + 1 /> 
							<#assign menuIdCnt = menuIdCnt + 1 />
								<li id="menu_${menuIdCnt}" >
									<a href="${slmsUrl}" target="_blank"><#if localeCd='ko'>${tt.menu_nm}<#elseif localeCd='fr'>${tt.menu_nm_fra}<#elseif localeCd='de'>${tt.menu_nm_deu}<#elseif localeCd='it'>${tt.menu_nm_ita}<#elseif localeCd='es'>${tt.menu_nm_esp}<#else>${tt.menu_nm_eng}</#if></a>
								</li>
						</#if> 		
					<#else>					
						<#if tt.menu_level = 1>
	
	<#if tt.menu_id = "20130319152827427_0000000336">
	  <#assign menuMax = menuMax + 1 /> 				
		<#assign subMenuCnt = 0 />
		<#list menuList as subMenu>
			<#if tt.menu_id = subMenu.up_menu_id>
				<#assign subMenuCnt = subMenuCnt + 1 />
			</#if>
		</#list>
		<#if subMenuCnt = 0>
			<#assign menuIdCnt = menuIdCnt + 1 />	
		  				<li id="menu_${menuIdCnt}">
								<a href="${clmLoginUrl}" target="_blank">
									<#if localeCd='ko'>${tt.menu_nm}<#elseif localeCd='fr'>${tt.menu_nm_fra}<#elseif localeCd='de'>${tt.menu_nm_deu}<#elseif localeCd='it'>${tt.menu_nm_ita}<#elseif localeCd='es'>${tt.menu_nm_esp}<#else>${tt.menu_nm_eng}</#if>
								</a>
							</li>								
		<#else>
							<#assign menuIdCnt = menuIdCnt + 1 />
							<li id="menu_${menuIdCnt}" onMouseOver="javascript:subMenuSet(${menuIdCnt })">
							 	<a href="javascript:Menu.go('frm', '<@spring.url "/secfw/main.do"/>', 'forwardSubFrame', '${tt.menu_id}', 'subFrame');">
							 		<#if localeCd='ko'>${tt.menu_nm}<#elseif localeCd='fr'>${tt.menu_nm_fra}<#elseif localeCd='de'>${tt.menu_nm_deu}<#elseif localeCd='it'>${tt.menu_nm_ita}<#elseif localeCd='es'>${tt.menu_nm_esp}<#else>${tt.menu_nm_eng}</#if>
								</a>
								<ul id="gnb_2depth_0${menuIdCnt}" class="gnb_2depth_0${menuIdCnt} gnb_2depth">
								<#assign subMenuIdCnt = 0 />
								<#list menuList as subMenu>
								<#if tt.menu_id = subMenu.up_menu_id>
									<!-- 국내 구두내역은 소속조직이 국내 법무팀일경우만 메뉴가 보인다. -->
									<#if subMenu.menu_id = "20110804082024506_0000000133">
										<#if blngt_orgnz = "A00000001">
												<#assign subMenuIdCnt = subMenuIdCnt + 1 />
												<li id="menu_${menuIdCnt}_${subMenuIdCnt}">
													<a href="javascript:Menu.go('frm', '<@spring.url "/secfw/main.do"/>', 'forwardSubFrame', '${subMenu.menu_id}', 'subFrame')">
														<#if localeCd='ko'>${subMenu.menu_nm}<#elseif localeCd='fr'>${subMenu.menu_nm_fra}<#elseif localeCd='de'>${subMenu.menu_nm_deu}<#elseif localeCd='it'>${subMenu.menu_nm_ita}<#elseif localeCd='es'>${subMenu.menu_nm_esp}<#else>${subMenu.menu_nm_eng}</#if>
													</a>
												</li>
												<#assign beforeSubMenuIdCnt = subMenuIdCnt />									
										</#if>
									<!-- 해외 구두내역은 소속조직이 해외 법무팀일 경우만 메뉴가 보인다. -->
									<#elseif subMenu.menu_id = "20110804082252377_0000000140">
										<#if blngt_orgnz = "A00000002">
												<#assign subMenuIdCnt = subMenuIdCnt + 1 />
												<li id="menu_${menuIdCnt}_${subMenuIdCnt}">
													<a href="javascript:Menu.go('frm', '<@spring.url "/secfw/main.do"/>', 'forwardSubFrame', '${subMenu.menu_id}', 'subFrame')">
														<#if localeCd='ko'>${subMenu.menu_nm}<#elseif localeCd='fr'>${subMenu.menu_nm_fra}<#elseif localeCd='de'>${subMenu.menu_nm_deu}<#elseif localeCd='it'>${subMenu.menu_nm_ita}<#elseif localeCd='es'>${subMenu.menu_nm_esp}<#else>${subMenu.menu_nm_eng}</#if>
													</a>
												</li>
												<#assign beforeSubMenuIdCnt = subMenuIdCnt />									
										</#if>
									<#else>
										
												<#assign subMenuIdCnt = subMenuIdCnt + 1 />
												<li id="menu_${menuIdCnt}_${subMenuIdCnt}">
													<a href="javascript:Menu.go('frm', '<@spring.url "/secfw/main.do"/>', 'forwardSubFrame', '${subMenu.menu_id}', 'subFrame')">
														<#if localeCd='ko'>${subMenu.menu_nm}<#elseif localeCd='fr'>${subMenu.menu_nm_fra}<#elseif localeCd='de'>${subMenu.menu_nm_deu}<#elseif localeCd='it'>${subMenu.menu_nm_ita}<#elseif localeCd='es'>${subMenu.menu_nm_esp}<#else>${subMenu.menu_nm_eng}</#if>
													</a>
												</li>
												<#assign beforeSubMenuIdCnt = subMenuIdCnt />
																		
										
									</#if>
								</#if>
								</#list>
								</ul>
							</li>		
							<li class="line"></li>							    
		
		</#if>
	<#else>
	
						<#assign menuMax = menuMax + 1 /> 				
							<#assign menuIdCnt = menuIdCnt + 1 />
							<li id="menu_${menuIdCnt}" onMouseOver="javascript:subMenuSet(${menuIdCnt})">
							 	<#if tt.menu_nm_eng =="TCMS">
								<a href="javascript:Menu.gotoTnc();">
							 		<#if localeCd='ko'>${tt.menu_nm}<#elseif localeCd='fr'>${tt.menu_nm_fra}<#elseif localeCd='de'>${tt.menu_nm_deu}<#elseif localeCd='it'>${tt.menu_nm_ita}<#elseif localeCd='es'>${tt.menu_nm_esp}<#else>${tt.menu_nm_eng}</#if>
								</a>
								<#else>
							 	<a href="javascript:Menu.go('frm', '<@spring.url "/secfw/main.do"/>', 'forwardSubFrame', '${tt.menu_id}', 'subFrame');">
							 		<#if localeCd='ko'>${tt.menu_nm}<#elseif localeCd='fr'>${tt.menu_nm_fra}<#elseif localeCd='de'>${tt.menu_nm_deu}<#elseif localeCd='it'>${tt.menu_nm_ita}<#elseif localeCd='es'>${tt.menu_nm_esp}<#else>${tt.menu_nm_eng}</#if>
								</a>
								</#if>
								<ul id="gnb_2depth_0${menuIdCnt}" class="gnb_2depth_0${menuIdCnt} gnb_2depth">
								<#assign subMenuIdCnt = 0 />
								<#list menuList as subMenu>
								<#if tt.menu_id = subMenu.up_menu_id>
	
									<!-- 소속조직이 해외법무팀이면서 사용자role 이 RA00,01,02,03일 경우만 일정관리 메뉴가 보인다. -->
									<#if subMenu.menu_id = "20110804083442451_0000000165">
										<#if planAccessFlag = true>										
												<#assign subMenuIdCnt = subMenuIdCnt + 1 />
												<li id="menu_${menuIdCnt}_${subMenuIdCnt}">
													<a href="javascript:Menu.go('frm', '<@spring.url "/secfw/main.do"/>', 'forwardSubFrame', '${subMenu.menu_id}', 'subFrame')">
														<#if localeCd='ko'>${subMenu.menu_nm}<#elseif localeCd='fr'>${subMenu.menu_nm_fra}<#elseif localeCd='de'>${subMenu.menu_nm_deu}<#elseif localeCd='it'>${subMenu.menu_nm_ita}<#elseif localeCd='es'>${subMenu.menu_nm_esp}<#else>${subMenu.menu_nm_eng}</#if>
													</a>
												</li>
												<#assign beforeSubMenuIdCnt = subMenuIdCnt />
										</#if>
									<!-- 법률자문 국내 - 국내법무팀 및 IP센터만 보이게한다. -->	
									<#--
									<#elseif subMenu.menu_id = "20110803155533470_0000000109">
										<#if (blngt_orgnz = "A00000001" || blngt_orgnz = "A00000003") || consultFlag = true>							
												<#assign subMenuIdCnt = subMenuIdCnt + 1 />
												<li id="menu_${menuIdCnt}_${subMenuIdCnt}">
													<a href="javascript:Menu.go('frm', '<@spring.url "/secfw/main.do"/>', 'forwardSubFrame', '${subMenu.menu_id}', 'subFrame')">
														<#if localeCd='ko'>${subMenu.menu_nm}<#elseif localeCd='fr'>${subMenu.menu_nm_fra}<#elseif localeCd='de'>${subMenu.menu_nm_deu}<#elseif localeCd='it'>${subMenu.menu_nm_ita}<#elseif localeCd='es'>${subMenu.menu_nm_esp}<#else>${subMenu.menu_nm_eng}</#if>
													</a>
												</li>
												<#assign beforeSubMenuIdCnt = subMenuIdCnt />
										</#if>
									-->
									<!-- 법률자문 해외 - 해외법무팀 및 IP센터만 보이게한다. -->
									<#--
									<#elseif subMenu.menu_id = "20110803155533587_0000000110">
										<#if (blngt_orgnz = "A00000002" || blngt_orgnz = "A00000003") || consultFlag = true>									
												<#assign subMenuIdCnt = subMenuIdCnt + 1 />
												<li id="menu_${menuIdCnt}_${subMenuIdCnt}">
													<a href="javascript:Menu.go('frm', '<@spring.url "/secfw/main.do"/>', 'forwardSubFrame', '${subMenu.menu_id}', 'subFrame')">
														<#if localeCd='ko'>${subMenu.menu_nm}<#elseif localeCd='fr'>${subMenu.menu_nm_fra}<#elseif localeCd='de'>${subMenu.menu_nm_deu}<#elseif localeCd='it'>${subMenu.menu_nm_ita}<#elseif localeCd='es'>${subMenu.menu_nm_esp}<#else>${subMenu.menu_nm_eng}</#if>
													</a>
												</li>
												<#assign beforeSubMenuIdCnt = subMenuIdCnt />
										</#if>
									-->									
									<#else>
										
												<#assign subMenuIdCnt = subMenuIdCnt + 1 />
												<li id="menu_${menuIdCnt}_${subMenuIdCnt}">
													<a href="javascript:Menu.go('frm', '<@spring.url "/secfw/main.do"/>', 'forwardSubFrame', '${subMenu.menu_id}', 'subFrame')">
														<#if localeCd='ko'>${subMenu.menu_nm}<#elseif localeCd='fr'>${subMenu.menu_nm_fra}<#elseif localeCd='de'>${subMenu.menu_nm_deu}<#elseif localeCd='it'>${subMenu.menu_nm_ita}<#elseif localeCd='es'>${subMenu.menu_nm_esp}<#else>${subMenu.menu_nm_eng}</#if>
													</a>
												</li>
												<#assign beforeSubMenuIdCnt = subMenuIdCnt />
																		
										
									</#if>								
								</#if>
								</#list>
								</ul>
							</li>	
							<li class="line fL"></li>											    
						</#if>
	</#if>					
					</#if>					
						
						
					<#assign beforeMenuIdCnt = menuIdCnt />
					</#list>
					<!--/li-->
					
				</ul>
				 <span class="util_hidden"><img src="${IMAGE}/icon/ico_collapse.png" alt="collapse" title="collapse"  onclick="menu_Toggle(this, 'las', 'util', 'top');" /></span>
			</div>
		</div>
		
		
	</div>
</div>



	
<!-- 로딩상 넘어온 메뉴를 클릭해 준다. -->
<script language="javascript">


</script>	

<!-- 세션을 유지하기 위한 iframe하나를 숨겨돈다. iframe안에서 페이지를 5분마다 refresh 한다-->
<iframe name='iframeTemp' src='<@spring.url "/las/pageReload.do"/>' frameborder='0' style='width:0px;height:0px'></iframe>	
</body>
</html>
