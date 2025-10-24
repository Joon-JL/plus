<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%@ page import="com.sec.common.util.ClmsDataUtil" %>
<%@ page import="com.sds.secframework.common.util.StringUtil"%>
<%@ page import="com.sec.common.util.ClmsBoardUtil" %>
<%
	List 	menuList 	 	=  (List)request.getAttribute("menuList");
	String	topMenuLevel 	=  (String)request.getAttribute("topMenuLevel");
	String	targetMenuId	=  (String)request.getAttribute("targetMenuId");
	int     menuSize        =  Integer.parseInt((String)request.getAttribute("menuSize"));  
	int 	menuIdCnt 		= 0;
	int 	menuMax 		= 1;
	
	String localeCode = (String)session.getAttribute("secfw.session.language_flag");
	
	String  clmLoginUrl		= (String)request.getAttribute("clmLoginUrl");
	//소송관리 URL
	String  slmsUrl 		= (String)request.getAttribute("slmsUrl");
	
	ClmsDataUtil.debug("############## :" + localeCode);
	boolean lawfirmAccessFlag = lawfirmAccessFlag(session, "A");
	boolean planAccessFlag = lawfirmAccessFlag(session, "B");
	boolean personalFlag = personalFlag(session);
	boolean statisticsViewFlag = statisticsViewFlag(session);
	boolean consultFlag = consultFlag(session);
	boolean slmsViewFlag = slmsViewFlag(session);
	
	String blngt_orgnz = StringUtil.bvl((String)session.getAttribute("secfw.session.blngt_orgnz"), "");
	String division_gbn = StringUtil.bvl((String)session.getAttribute("secfw.server.division_gbn"), ""); // 개발서버와 운영서버 구분값. 다국어 오픈 전 개발서버에만 독어/불어 옵션 선택 가능.
	List menuSiteMapList = (List)request.getAttribute("menuSiteMapList");
%>	
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html>
<head>
<meta charset="utf-8" />

<title>Site Map</title>

<!-- style -->
<LINK href="<%=CSS%>/main.css"   type="text/css" rel="stylesheet">


<!-- js  -->
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js'/>" type="text/javascript"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.flot.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript" src="<c:url value='/script/clms/new_style.js'/>" type="text/javascript"></script>
<script language="javascript" src="<c:url value='/script/clms/common.js'/>" type="text/javascript"></script>
<script language="javascript" src="<c:url value='/script/clms/menu.js'/>"></script>

<script language="javascript">

function divShow(obj) {
	obj.style.display = 'block';
}

function divHidden(obj) {
	obj.style.display = 'none';
}

//공무집행 대응 변호사 팝업
function openContactUsPop(){
	PopUpWindowOpen2('/las/openPopME.do', '840', '583', false, 'PopUpContactUsLas');		
}
//업무분야별 전문 변호사 팝업
function openExpertPop(){
	PopUpWindowOpen2('/las/openPopMSC.do', '840', '600', false, 'PopUpExpertClm');
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

<form name="frm" id="frm" method="post" autocomplete="off">
	<!-- method -->
	<input type="hidden" name="method">
	<!-- targetMenuId -->
	<input type="hidden" name="targetMenuId">
	<input type="hidden" name="target_menu_id">
	
	<!-- selected_menu_id -->
	<input type="hidden" name="selected_menu_id">
	<input type="hidden" name="selected_menu_nm">
	<input type="hidden" name="selected_menu_detail_id">
	<!--상세내역에 띄워줄 URL 정보.-->
	<input type="hidden" name="set_init_url">
	
	<input type="hidden" name="menuLogEnable" value="Y">
	<input type="hidden" name="chgLangflag">
	
	<input type="hidden" name="curPage">		
	
	<input type="hidden" name="menu_id" value="" />
	
	<input type="hidden" name="flag" value=""/>
</form>

<!-- topMenu -->
<div id="topWrap" >
<div id="header">
 
	<div id='util'>
		<div class='mainC1'>
		    <div id="logo">
		      <a href="javascript:Menu.main('frm','L');" class="h1_logo"><IMG SRC="<%=IMAGE%>/common/sys_logo<%= "C60".equals(compCd) || "C10".equals(compCd) ? "2" : "_" + compCd %>.gif"  BORDER="0" alt="<spring:message code="las.page.field.logo.title" />" ></a>
		      <span class='pncacc'><img src="<%=IMAGE%>/common/pncacc.gif"  border="0" alt="Privileged and Confidential Attorney-Client Communication" title="Privileged and Confidential Attorney-Client Communication"/></span>
		      <!-- utility -->
		      <ul class="utility">
		        <li>
		<% if (localeCode.equals("ko")) { %>
		          <a href="#" class="user"><%=StringUtil.bvl((String)session.getAttribute("secfw.session.user_nm"), "")%> / <%=StringUtil.bvl((String)session.getAttribute("secfw.session.grade_nm"), "")%> / <%=StringUtil.bvl((String)session.getAttribute("secfw.session.dept_nm"), "")%></a></li>
		<% } else { %>
		          <a href="#" class="user"><%=StringUtil.bvl((String)session.getAttribute("secfw.session.user_nm_en"), "")%> / <%=StringUtil.bvl((String)session.getAttribute("secfw.session.grade_nm_en"), "")%> / <%=StringUtil.bvl((String)session.getAttribute("secfw.session.dept_nm_en"), "")%></a></li>
		<% } %>
		       </li>
		        <li><a href="javascript:Menu.main('frm','L')"> <img src="<%=IMAGE%>/icon/icon_home.gif" alt="Home" title="Home"/></a></li>
		        <li><a href="javascript:Menu.goSiteMap('frm','L');"><img src="<%=IMAGE%>/icon/icon_sitemap.gif" alt="Sitemap" title="Sitemap"/></a></li>
		       <!-- <li>
		<% if (localeCode.equals("ko")) { %>
		          <a href="#" onclick="javascript:frm.chgLangflag.value='ko';Menu.main('frm','L');" style='background:none;'>
		          <img id="lang_ko" src="<%=IMAGE%>/btn/btn_on_l_kor.gif"  title='<spring:message code="clm.page.field.menu.koLang" />' /></a><a href="#" onclick="javascript:frm.chgLangflag.value='en';Menu.main('frm','L');" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('lang_en','','<%=IMAGE%>/btn/btn_on_l_eng.gif',1)"><img id="lang_en" src="<%=IMAGE%>/btn/btn_off_l_eng.gif"  title='<spring:message code="clm.page.field.menu.enLang" />' /></a>
		<% } else { %>
		          <a href="#" onclick="javascript:frm.chgLangflag.value='ko';Menu.main('frm','L');" style='background:none;' onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('lang_ko','','<%=IMAGE%>/btn/btn_on_l_kor.gif',1)">
		          <img id="lang_ko" src="<%=IMAGE%>/btn/btn_off_l_kor.gif"  title='<spring:message code="clm.page.field.menu.koLang" />' /></a><a href="#" onclick="javascript:frm.chgLangflag.value='en';Menu.main('frm','L');"><img id="lang_en" src="<%=IMAGE%>/btn/btn_on_l_eng.gif"  title='<spring:message code="clm.page.field.menu.enLang" />' /></a>
		<% } %>
		        </li>-->
		        <li class="lang_sel">
					<% if(division_gbn.equals("DEV")){ %>	        
						<select class="lang_select" name="chgLangflagSel" onChange="javascript:frm.chgLangflag.value=this.value;Menu.main('frm','L');">
							<option value="en" <% if (localeCode.equals("en")) { %>selected<%}%> >English</option>
							<option value="fr" <% if (localeCode.equals("fr")) { %>selected<%}%> >Français</option>
 			<!-- 		    <option value="de" <% if (localeCode.equals("de")) { %>selected<%}%> >Deutsch</option>  -->
						</select>
					<%}else{%>
						<select class="lang_select" name="chgLangflagSel" onChange="javascript:frm.chgLangflag.value=this.value;Menu.main('frm','L');">
							<option value="en" <% if (localeCode.equals("en")) { %>selected<%}%> >English</option>
							<option value="fr" <% if (localeCode.equals("fr")) { %>selected<%}%> >Français</option>
<!-- <%-- 						<option value="de" <% if (localeCode.equals("de")) { %>selected<%}%> >Deutsch</option> --%> -->
						</select>
					
					<%} %>
				</li>
		       
		      </ul>
		      
		    </div>
		</div>
   </div>
   
   
	
	<div id='gnbWrap'>
		<div class="gnb mainC2">
	    	<ul>
			<%
			    String multi_menu_nm    = "";
			    String multi_submenu_nm = "";
			
			    for (int idx=0;idx < menuList.size();idx++ ) {
			        ListOrderedMap menu = null;
			        menu = (ListOrderedMap)menuList.get(idx);
					
			        if (localeCode.equals("de")) {
			            multi_menu_nm = (String) menu.get("menu_nm_deu");
			        } else if (localeCode.equals("fr")) {
           				multi_menu_nm = (String) menu.get("menu_nm_fra");
        			} else {
			            multi_menu_nm = (String) menu.get("menu_nm_eng");
			        }
			
			        if (Integer.parseInt(String.valueOf((BigDecimal)menu.get("menu_level"))) == 1) {
			%>
			        <li  id="menu_<%=menuMax%>" onMouseOver="javascript:subMenuSet(<%=menuMax%>)">
			          <a href="javascript:Menu.go('frm', '<c:url value='/secfw/main.do' />', 'forwardMainFrame', '<%=menu.get("menu_id")%>', '_self');"><%=multi_menu_nm%></a>
			          <ul id="gnb_2depth_0<%=menuMax%>" class="gnb_2depth_0<%=menuMax%> gnb_2depth">
			<%
			            int subMenuIdx = 1;
			
			            for (int idx2=0; idx2 < menuList.size(); idx2++) {
			                ListOrderedMap subMenu = null;
			                subMenu = (ListOrderedMap)menuList.get(idx2);
			
			                if (localeCode.equals("de")) {
			                    multi_submenu_nm = (String) subMenu.get("menu_nm_deu");
			                } else if (localeCode.equals("fr")) {
			                    multi_submenu_nm = (String) subMenu.get("menu_nm_fra");
			                } else {
			                    multi_submenu_nm = (String) subMenu.get("menu_nm_eng");
			                }
			
			                if (Integer.parseInt(String.valueOf((BigDecimal)subMenu.get("menu_level"))) == 2 && subMenu.get("up_menu_id").equals(menu.get("menu_id"))) {
			                    if (idx2 == menuList.size()) {
			%>
			            <li id="menu_<%=menuMax%>_<%=subMenuIdx%>" class="last-menu"><a href="javascript:Menu.go('frm', '<c:url value='/secfw/main.do' />', 'forwardMainFrame', '<%=subMenu.get("menu_id")%>', '_self');"><%=multi_submenu_nm%></a></li>
			<%
			                    }else{
			%>
			            <li id="menu_<%=menuMax%>_<%=subMenuIdx%>"><a href="javascript:Menu.go('frm', '<c:url value='/secfw/main.do' />', 'forwardMainFrame', '<%=subMenu.get("menu_id")%>', '_self');"><%=multi_submenu_nm%></a></li>
			<%
			                    }
			                    subMenuIdx++;
			                }
			            }
			%>
			          </ul>
			         
			        </li>
			         <li class="line fL"></li>	
			<%
			            menuMax++;
			        }
			    }
			%>
	      	</ul>
	      	<span class="util_hidden_m"><img src="<%=IMAGE%>/icon/ico_collapse.png" alt="collapse"  title="collapse" onclick="menu_Toggle(this, 'las', 'util', '');" /></span>
    	</div>
    </div>
    
</div>
</div>
<!-- // topMenu -->


	<!-- container -->
	<div id="sitemapWrap">
	    <div id="main_content">
			
			<div class="sitemap_Bbox_tl">
				<div class="sitemap_Bbox_tr">
					<div class="sitemap_Bbox_bl">
						<div class="sitemap_Bbox_br">
							<img src="<%=IMAGE%>/main/sitemap_img.gif" alt="" style='margin:20px 0 30px 35px' /> 
<%
					int menueMax2 = 1;
					for(int idx=0;idx<menuSiteMapList.size();idx++){
						ListOrderedMap menu = null;
						menu = (ListOrderedMap)menuSiteMapList.get(idx);
						
						if(menueMax2 == 1){
%>
						<div class="sitemap_list_box sitemap_four sitemap_first"><!-- 메뉴가 4개 나올때 -->	
<%							
						}
						
						if((menueMax2 == 5 || menueMax2 == 9) && Integer.parseInt(String.valueOf((BigDecimal)menu.get("menu_level"))) == 1){
%>
						</div>
							<div class="sitemap_list_box sitemap_four">
<%							
						}
						
						
						 //소속조직이 해외법무팀이면서 사용자role 이 RA00,01,02,03일 경우만 로펌 메뉴가 보인다.
						if("20110803155109700_0000000106".equals(menu.get("menu_id")) ){
							if(lawfirmAccessFlag){

								if(Integer.parseInt(String.valueOf((BigDecimal)menu.get("menu_level"))) == 1){
%>
						    <div class="sitemap_list site_<%=menueMax2%>list"> <strong class="title">계약검토</strong>
						      <ul class="map_list">						
<% 
									int subMenuIdx2 = 1;
									for(int idx2=0;idx2<menuSiteMapList.size();idx2++){
										ListOrderedMap subMenu = null;
										subMenu = (ListOrderedMap)menuSiteMapList.get(idx2);
										
										if(Integer.parseInt(String.valueOf((BigDecimal)subMenu.get("menu_level")))==2 && subMenu.get("up_menu_id").equals(menu.get("menu_id"))){
%>
									<li> <strong class="onedeps"><a href="javascript:Menu.go('frm', '<c:url value='/secfw/main.do' />', 'forwardMainFrame', '<%=subMenu.get("menu_id")%>', '_self');"><%= localeCode.equals("de") ? subMenu.get("menu_nm_deu") : ( localeCode.equals("fr") ? subMenu.get("menu_nm_fra") : subMenu.get("menu_nm_eng")) %></a></strong>
										<ul class="twodeps_list">
<%
											int subMenuIdx3 = 1;
											for(int idx3=0;idx3<menuSiteMapList.size();idx3++){
												ListOrderedMap subMenu2 = null;
												subMenu2 = (ListOrderedMap)menuSiteMapList.get(idx3);
												if(Integer.parseInt(String.valueOf((BigDecimal)subMenu2.get("menu_level")))==3 && subMenu2.get("up_menu_id").equals(subMenu.get("menu_id"))){
%>
											<li><a href="javascript:Menu.detail('frm','_self','<%=subMenu.get("menu_id")%>','<%=subMenu2.get("menu_id")%>','<%=subMenu2.get("menu_url")%>');"><%= localeCode.equals("de") ? subMenu2.get("menu_nm_deu") : ( localeCode.equals("fr") ? subMenu2.get("menu_nm_fra") : subMenu2.get("menu_nm_eng")) %></a></li>
<%																			
													subMenuIdx3++;
												}
											}
%>																		
										</ul>
									</li>
<%																	
											subMenuIdx2++;
										}								
									}
%>						      
					          </ul>
					        </div>
<%								
									menueMax2++;
								}								
							}
						}else if("20110725131147621_0000000007".equals(menu.get("menu_id")) ){ //통계
							if(statisticsViewFlag){

								if(Integer.parseInt(String.valueOf((BigDecimal)menu.get("menu_level"))) == 1){
%>
						    <div class="sitemap_list site_<%=menueMax2%>list"> <strong class="title">통계</strong>
						      <ul class="map_list">						
<% 
									int subMenuIdx2 = 1;
									for(int idx2=0;idx2<menuSiteMapList.size();idx2++){
										ListOrderedMap subMenu = null;
										subMenu = (ListOrderedMap)menuSiteMapList.get(idx2);
										if(Integer.parseInt(String.valueOf((BigDecimal)subMenu.get("menu_level")))==2 && subMenu.get("up_menu_id").equals(menu.get("menu_id"))){
%>
									<li> <strong class="onedeps"><a href="javascript:Menu.go('frm', '<c:url value='/secfw/main.do' />', 'forwardMainFrame', '<%=subMenu.get("menu_id")%>', '_self');"><%= localeCode.equals("de") ? subMenu.get("menu_nm_deu") : ( localeCode.equals("fr") ? subMenu.get("menu_nm_fra") : subMenu.get("menu_nm_eng")) %></a></strong>
										<ul class="twodeps_list">
<%
											int subMenuIdx3 = 1;
											for(int idx3=0;idx3<menuSiteMapList.size();idx3++){
												ListOrderedMap subMenu2 = null;
												subMenu2 = (ListOrderedMap)menuSiteMapList.get(idx3);
												if(Integer.parseInt(String.valueOf((BigDecimal)subMenu2.get("menu_level")))==3 && subMenu2.get("up_menu_id").equals(subMenu.get("menu_id"))){
%>
											<li><a href="javascript:Menu.detail('frm','_self','<%=subMenu.get("menu_id")%>','<%=subMenu2.get("menu_id")%>','<%=subMenu2.get("menu_url")%>');"><%= localeCode.equals("de") ? subMenu2.get("menu_nm_deu") : ( localeCode.equals("fr") ? subMenu2.get("menu_nm_fra") : subMenu2.get("menu_nm_eng")) %></a></li>
<%																			
													subMenuIdx3++;
												}
											}
%>																		
										</ul>
									</li>
<%																	
											subMenuIdx2++;
										}								
									}
%>						      
					          </ul>
					        </div>
<%								
									menueMax2++;
								}								
							}							
						}else if("20130320132153700_0000000335".equals(menu.get("menu_id"))){ //소송관리
							if(slmsViewFlag){ 
								if(Integer.parseInt(String.valueOf((BigDecimal)menu.get("menu_level"))) == 1){
%>
								<div class="sitemap_list site_<%=menueMax2%>list"> 
									<strong class="title"><a href="<%=slmsUrl%>" target="_blank"><%= localeCode.equals("de") ? menu.get("menu_nm_deu") : (  localeCode.equals("fr") ? menu.get("menu_nm_fra"): menu.get("menu_nm_eng")) %></a></strong>
								</div>
<%									
									menueMax2++;
								}
							}
						}else{
							if(Integer.parseInt(String.valueOf((BigDecimal)menu.get("menu_level"))) == 1){
								
								//1레벨이 계약관리이면서 하위서브메뉴 갯수가 0개일 경우 계약관리시스템으로 이동					    	
								if("20130319152827427_0000000336".equals(menu.get("menu_id")) ){
									int subMenuCount = 0;
									for(int a=0;a<menuSiteMapList.size();a++){
										ListOrderedMap tMenu = null;
										tMenu = (ListOrderedMap)menuSiteMapList.get(a);
										if(Integer.parseInt(String.valueOf((BigDecimal)tMenu.get("menu_level")))==2 && tMenu.get("up_menu_id").equals(menu.get("menu_id"))){
											subMenuCount++;
										}
									}
									
									if(subMenuCount == 0){
%>
									<div class="sitemap_list site_<%=menueMax2%>list"> 
										<strong class="title"><a href="<%=clmLoginUrl%>" target="_blank"><%= localeCode.equals("de") ? menu.get("menu_nm_deu") : (  localeCode.equals("fr") ? menu.get("menu_nm_fra"): menu.get("menu_nm_eng")) %></a></strong>
									</div>
<%										
										menueMax2++;	
									}else{
%>
								    <div class="sitemap_list site_<%=menueMax2%>list"> <strong class="title"><%= localeCode.equals("de") ? menu.get("menu_nm_deu") : (  localeCode.equals("fr") ? menu.get("menu_nm_fra"): menu.get("menu_nm_eng")) %></strong>
								      <ul class="map_list">						
<% 
										int subMenuIdx2 = 1;
										for(int idx2=0;idx2<menuSiteMapList.size();idx2++){
											ListOrderedMap subMenu = null;
											subMenu = (ListOrderedMap)menuSiteMapList.get(idx2);
											if(Integer.parseInt(String.valueOf((BigDecimal)subMenu.get("menu_level")))==2 && subMenu.get("up_menu_id").equals(menu.get("menu_id"))){
												
												//국내 구두내역은 소속조직이 국내 법무팀일경우만 메뉴가 보인다.
												if("20110804082024506_0000000133".equals(subMenu.get("menu_id"))){
													if("A00000001".equals(blngt_orgnz)){
%>
														<li> <strong class="onedeps"><a href="javascript:Menu.go('frm', '<c:url value='/secfw/main.do' />', 'forwardMainFrame', '<%=subMenu.get("menu_id")%>', '_self');"><%= localeCode.equals("de") ? subMenu.get("menu_nm_deu") : ( localeCode.equals("fr") ? subMenu.get("menu_nm_fra") : subMenu.get("menu_nm_eng")) %></a></strong>
															<ul class="twodeps_list">
															</ul>
														</li>
<%																	
														subMenuIdx2++;																										
													}
																								
												//해외 구두내역은 소속조직이 해외 법무팀일 경우만 메뉴가 보인다.
												}else if("20110804082252377_0000000140".equals(subMenu.get("menu_id"))){
													if("A00000002".equals(blngt_orgnz)){														
%>
														<li> <strong class="onedeps"><a href="javascript:Menu.go('frm', '<c:url value='/secfw/main.do' />', 'forwardMainFrame', '<%=subMenu.get("menu_id")%>', '_self');"><%= localeCode.equals("de") ? subMenu.get("menu_nm_deu") : ( localeCode.equals("fr") ? subMenu.get("menu_nm_fra") : subMenu.get("menu_nm_eng")) %></a></strong>
															<ul class="twodeps_list">
															</ul>
														</li>
<%																	
														subMenuIdx2++;												
													}
																										
												}else{
%>
											<li> <strong class="onedeps"><a href="javascript:Menu.go('frm', '<c:url value='/secfw/main.do' />', 'forwardMainFrame', '<%=subMenu.get("menu_id")%>', '_self');"><%= localeCode.equals("de") ? subMenu.get("menu_nm_deu") : ( localeCode.equals("fr") ? subMenu.get("menu_nm_fra") : subMenu.get("menu_nm_eng")) %></a></strong>
												<ul class="twodeps_list">
<%
												int subMenuIdx3 = 1;
												for(int idx3=0;idx3<menuSiteMapList.size();idx3++){
													ListOrderedMap subMenu2 = null;
													subMenu2 = (ListOrderedMap)menuSiteMapList.get(idx3);
													if(Integer.parseInt(String.valueOf((BigDecimal)subMenu2.get("menu_level")))==3 && subMenu2.get("up_menu_id").equals(subMenu.get("menu_id"))){
%>
													<li><a href="javascript:Menu.detail('frm','_self','<%=subMenu.get("menu_id")%>','<%=subMenu2.get("menu_id")%>','<%=subMenu2.get("menu_url")%>');"><%= localeCode.equals("de") ? subMenu2.get("menu_nm_deu") : ( localeCode.equals("fr") ? subMenu2.get("menu_nm_fra") : subMenu2.get("menu_nm_eng")) %></a></li>
<%																			
														subMenuIdx3++;
													}
												}
%>																		
												</ul>
											</li>
<%																	
												subMenuIdx2++;
											}
												
											}
										}
%>						      
							          </ul>
							        </div>
<%								
										menueMax2++;		
									}
								}else{
%>
						    <div class="sitemap_list site_<%=menueMax2%>list"> <strong class="title"><%= localeCode.equals("de") ? menu.get("menu_nm_deu") : (  localeCode.equals("fr") ? menu.get("menu_nm_fra"): menu.get("menu_nm_eng")) %></strong>
						      <ul class="map_list">						
<% 
								int subMenuIdx2 = 1;
								for(int idx2=0;idx2<menuSiteMapList.size();idx2++){
									ListOrderedMap subMenu = null;
									subMenu = (ListOrderedMap)menuSiteMapList.get(idx2);
									if(Integer.parseInt(String.valueOf((BigDecimal)subMenu.get("menu_level")))==2 && subMenu.get("up_menu_id").equals(menu.get("menu_id"))){

										//소속조직이 해외법무팀이면서 사용자role 이 RA00,01,02,03일 경우만 일정관리 메뉴가 보인다.
										if("20110804083442451_0000000165".equals(subMenu.get("menu_id")) ){
											if(planAccessFlag){
%>
												<li> <strong class="onedeps"><a href="javascript:Menu.go('frm', '<c:url value='/secfw/main.do' />', 'forwardMainFrame', '<%=subMenu.get("menu_id")%>', '_self');"><%= localeCode.equals("de") ? subMenu.get("menu_nm_deu") : ( localeCode.equals("fr") ? subMenu.get("menu_nm_fra") : subMenu.get("menu_nm_eng")) %></a></strong>
													<ul class="twodeps_list">
													</ul>
												</li>
<%																	
												subMenuIdx2++;												
											}
%>
<%--
										}
										//법률자문 국내 - 국내법무팀 & IP센터만 보인다.
										else if("20110803155533470_0000000109".equals(subMenu.get("menu_id")) ){
											if(("A00000001".equals(blngt_orgnz) || "A00000003".equals(blngt_orgnz)) || consultFlag){
%>
												<li> <strong class="onedeps"><a href="javascript:Menu.go('frm', '<c:url value='/secfw/main.do' />', 'forwardMainFrame', '<%=subMenu.get("menu_id")%>', '_self');"><%= localeCode.equals("de") ? subMenu.get("menu_nm_deu") : ( localeCode.equals("fr") ? subMenu.get("menu_nm_fra") : subMenu.get("menu_nm_eng")) %></a></strong>
													<ul class="twodeps_list">
<%
										int subMenuIdx3 = 1;
										for(int idx3=0;idx3<menuSiteMapList.size();idx3++){
											ListOrderedMap subMenu2 = null;
											subMenu2 = (ListOrderedMap)menuSiteMapList.get(idx3);
											if(Integer.parseInt(String.valueOf((BigDecimal)subMenu2.get("menu_level")))==3 && subMenu2.get("up_menu_id").equals(subMenu.get("menu_id"))){												
%>
											<li><a href="javascript:Menu.detail('frm','_self','<%=subMenu.get("menu_id")%>','<%=subMenu2.get("menu_id")%>','<%=subMenu2.get("menu_url")%>');"><%= localeCode.equals("de") ? subMenu2.get("menu_nm_deu") : ( localeCode.equals("fr") ? subMenu2.get("menu_nm_fra") : subMenu2.get("menu_nm_eng")) %></a></li>
<%																			
												subMenuIdx3++;
											}										
										}
%>														
													</ul>
												</li>
<%																	
												subMenuIdx2++;													
											}
										}
										//법률자문 해외 - 해외법무팀 & IP센터만 보인다.
										else if("20110803155533587_0000000110".equals(subMenu.get("menu_id")) ){
											if(("A00000002".equals(blngt_orgnz) || "A00000003".equals(blngt_orgnz)) || consultFlag){
%>
												<li> <strong class="onedeps"><a href="javascript:Menu.go('frm', '<c:url value='/secfw/main.do' />', 'forwardMainFrame', '<%=subMenu.get("menu_id")%>', '_self');"><%= localeCode.equals("de") ? subMenu.get("menu_nm_deu") : ( localeCode.equals("fr") ? subMenu.get("menu_nm_fra") : subMenu.get("menu_nm_eng")) %></a></strong>
													<ul class="twodeps_list">
<%
										int subMenuIdx3 = 1;
										for(int idx3=0;idx3<menuSiteMapList.size();idx3++){
											ListOrderedMap subMenu2 = null;
											subMenu2 = (ListOrderedMap)menuSiteMapList.get(idx3);
											if(Integer.parseInt(String.valueOf((BigDecimal)subMenu2.get("menu_level")))==3 && subMenu2.get("up_menu_id").equals(subMenu.get("menu_id"))){												
%>
											<li><a href="javascript:Menu.detail('frm','_self','<%=subMenu.get("menu_id")%>','<%=subMenu2.get("menu_id")%>','<%=subMenu2.get("menu_url")%>');"><%= localeCode.equals("de") ? subMenu2.get("menu_nm_deu") : ( localeCode.equals("fr") ? subMenu2.get("menu_nm_fra") : subMenu2.get("menu_nm_eng")) %></a></li>
<%																			
												subMenuIdx3++;
											}										
										}
%>														
													</ul>
												</li>
<%																	
												subMenuIdx2++;													
											}
--%>
<%
										}																			
										else{
										
%>
									<li> <strong class="onedeps"><a href="javascript:Menu.go('frm', '<c:url value='/secfw/main.do' />', 'forwardMainFrame', '<%=subMenu.get("menu_id")%>', '_self');"><%= localeCode.equals("de") ? subMenu.get("menu_nm_deu") : ( localeCode.equals("fr") ? subMenu.get("menu_nm_fra") : subMenu.get("menu_nm_eng")) %></a></strong>
										<ul class="twodeps_list">
<%
										int subMenuIdx3 = 1;
										for(int idx3=0;idx3<menuSiteMapList.size();idx3++){
											ListOrderedMap subMenu2 = null;
											subMenu2 = (ListOrderedMap)menuSiteMapList.get(idx3);
											if(Integer.parseInt(String.valueOf((BigDecimal)subMenu2.get("menu_level")))==3 && subMenu2.get("up_menu_id").equals(subMenu.get("menu_id"))){												
%>
											<li><a href="javascript:Menu.detail('frm','_self','<%=subMenu.get("menu_id")%>','<%=subMenu2.get("menu_id")%>','<%=subMenu2.get("menu_url")%>');"><%= localeCode.equals("de") ? subMenu2.get("menu_nm_deu") : ( localeCode.equals("fr") ? subMenu2.get("menu_nm_fra") : subMenu2.get("menu_nm_eng")) %></a></li>
<%																			
												subMenuIdx3++;
											}										
										}
%>																		
										</ul>
									</li>
<%																	
										subMenuIdx2++;
										}							
									}								
								}
%>						      
					          </ul>
					        </div>
<%								
								menueMax2++;

								}
							}
						}
					}
%>							
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>							
		<!-- //content -->
	<!----footer---->

		<!----푸터시작---->
<div id="footer">
		<address>
		<div class='fL copy'>ⓒ SAMSUNG<span class='contactus'><span>Contact Us</span> : selmsplus@samsung.com</span></div>
		<div class='fR privacy'> <spring:message code="las.main.field.individual.policy"/><!-- Privacy Policy --></div>
		</address>
</div>
<!----// 푸터마침---->	
<!----// footer---->		
 
		<jsp:include page="/WEB-INF/jsp/secfw/common/CommonProgress.jsp"/>
	</div>
	<!-- //container -->
</div>

</body>
</html>
<%!
//로펌, 일정관리 메뉴 view flag 설정
public boolean lawfirmAccessFlag(HttpSession session, String gubun) throws Exception{
	/*
		RA01 : 법무관리자
		RA02 : 법무담당자
		RA03 : 법무일반사용자
		RA00 : 시스템 관리자
	*/
	String result = "";
	ArrayList roleList = (ArrayList)session.getAttribute("secfw.session.role");
	String blngt_orgnz = StringUtil.bvl((String)session.getAttribute("secfw.session.blngt_orgnz"), "");
        
	ArrayList userRoleList = new ArrayList();
    if(roleList != null && roleList.size()>0){
    	for(int i=0; i < roleList.size() ;i++){
        	HashMap hm = (HashMap)roleList.get(i);
        	String roleCd = (String)hm.get("role_cd");
        	userRoleList.add(roleCd);
        }
	}
        
    if(userRoleList != null && userRoleList.size()>0) {
    	if(userRoleList.contains("RA00")) {	
        	result = "A";
    	}else if(userRoleList.contains("RA01") || userRoleList.contains("RA02")){
			result = "B";
		}else if(userRoleList.contains("RA03")){
			result = "C";
		}
	}
    
    boolean retV = false;
    if("A".equals(gubun)){ //로펌
    	//시스템관리자는 로펌을 보이게 한다.
    	//소속조직이 해외법무팀이면서  RA01,RA02,RA03 인 사용자만 로펌이 보이게한다.
    	//소속조직이 IP센터이면서 RA03인 사용자만 로펌이 보이게 한다.
    	if("A".equals(result) || (("B".equals(result) || "C".equals(result)) && ("A00000002".equals(blngt_orgnz))) || ("C".equals(result) && ("A00000003".equals(blngt_orgnz)))){
    		retV = true;
        }	
    }else{ //일정관리
    	//시스템관리자 및 소속조직이 해외법무팀이면서 RA01,RA02,RA03 인 사용자만 일정관리가 보이게한다.
    	if("A".equals(result) || (("B".equals(result) || "C".equals(result)) && ("A00000002".equals(blngt_orgnz)))){
    		retV = true;
	    }	
    }
    return retV;
}

//공지사항, 주요입법동향 설정(일반사용자)
public boolean personalFlag(HttpSession session) throws Exception{
	/*
	RZ00 : 일반사용자
	RD01 : 사업부계약관리자
	RD02 : 사업부계약담당자
	*/	
	boolean retV = false;
	ArrayList roleList = (ArrayList)session.getAttribute("secfw.session.role");
	
	ArrayList userRoleList = new ArrayList();
    if(roleList != null && roleList.size()>0){
    	for(int i=0; i < roleList.size() ;i++){
        	HashMap hm = (HashMap)roleList.get(i);
        	String roleCd = (String)hm.get("role_cd");
        	userRoleList.add(roleCd);
        }
	}
    
    if(userRoleList != null && userRoleList.size()>0) {
    	if(userRoleList.contains("RZ00") || userRoleList.contains("RD01") || userRoleList.contains("RD02")) {	
    		retV = true;
		}
	}
    return retV;
}

//통계 메뉴 View 여부
public boolean statisticsViewFlag(HttpSession session) throws Exception{
	String blngt_orgnz = StringUtil.bvl((String)session.getAttribute("secfw.session.blngt_orgnz"), "");
	
	boolean retV = true;
	
	//소속 조직이 IP센터인 경우 통계를 안보이게 한다.
	if("A00000003".equals(blngt_orgnz)){
		retV = false;
	}
	//return retV;
	//2013-01-16 IP센터도 신_통계내용이 보여야한다.
	return true;
}

//소송관리 메뉴 View 여부
public boolean slmsViewFlag(HttpSession session) throws Exception{

	/*
	RA01 : 법무관리자
	RA02 : 법무담당자
	RA03 : 법무일반사용자
	RA00 : 시스템 관리자
	*/
	String result = "";
	ArrayList roleList = (ArrayList)session.getAttribute("secfw.session.role");
	String blngt_orgnz = StringUtil.bvl((String)session.getAttribute("secfw.session.blngt_orgnz"), "");
	    
	ArrayList userRoleList = new ArrayList();
	if(roleList != null && roleList.size()>0){
		for(int i=0; i < roleList.size() ;i++){
	    	HashMap hm = (HashMap)roleList.get(i);
	    	String roleCd = (String)hm.get("role_cd");
	    	userRoleList.add(roleCd);
	    }
	}
	    
	if(userRoleList != null && userRoleList.size()>0) {
		
		if(userRoleList.contains("RA00")) {	
        	result = "A";
		}else if(userRoleList.contains("RA01") || userRoleList.contains("RA02") || userRoleList.contains("RA03")){
			result = "B";
		}
	}
	
	boolean retV = false;
	
	//국내/해외 법무팀 및 RA01,02,03 권한 사용자만 보이게 한다.
	if("A".equals(result) || ("B".equals(result) && ("A00000001".equals(blngt_orgnz) || "A00000002".equals(blngt_orgnz)))){ 
		retV = true;
	}
	
	return retV; 
		
}

//법률자문
public boolean consultFlag(HttpSession session) throws Exception{
	/*
	RZ00 : 일반사용자
	RD01 : 사업부계약관리자
	RD02 : 사업부계약담당자
	*/	
	boolean retV = true;
	ArrayList roleList = (ArrayList)session.getAttribute("secfw.session.role");
	
	ArrayList userRoleList = new ArrayList();
    if(roleList != null && roleList.size()>0){
    	for(int i=0; i < roleList.size() ;i++){
        	HashMap hm = (HashMap)roleList.get(i);
        	String roleCd = (String)hm.get("role_cd");
        	userRoleList.add(roleCd);
        }
	}
    
    if(userRoleList != null && userRoleList.size()>0) {
     	if(userRoleList.contains("RA01") || userRoleList.contains("RA02") || userRoleList.contains("RA03") || userRoleList.contains("RA00")) {	
    		retV = false;
		}
	}
    return retV;
}
%>