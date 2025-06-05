<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
<%@ page import="com.sds.secframework.common.util.DateUtil" %>
<%
	List 	menuList 	 	=  (List)request.getAttribute("menuList");
	String	topMenuLevel 	=  (String)request.getAttribute("topMenuLevel");
	String	targetMenuId	=  (String)request.getAttribute("targetMenuId");
	int     menuSize        =  Integer.parseInt((String)request.getAttribute("menuSize"));  
	int 	menuIdCnt 		= 0;
	int 	menuMax 		= 1;
	
	String  itvocUrl 		= (String)request.getAttribute("itvocUrl");
	
	String localeCode = (String)session.getAttribute("secfw.session.language_flag");
	
	
	String titleImage = "";
	String blngt_orgnz = StringUtil.bvl((String)session.getAttribute("secfw.session.blngt_orgnz"), "");
	if("B00000003".equals(blngt_orgnz)){ //메모리사업부
		titleImage = "system_logo1";
	}else if("B00000002".equals(blngt_orgnz)){ //무선사업부
		titleImage = "system_logo3";
	}else if("B00000001".equals(blngt_orgnz)){ //영상디스플레이사업부
		titleImage = "system_logo4";
	}else if("B00000014".equals(blngt_orgnz)){ //DS부문(Device Solution Division)
		titleImage = "system_logo5";
	}else if("B00000015".equals(blngt_orgnz)){ //System LSI사업부
		titleImage = "system_logo6";
	}else if("B00000016".equals(blngt_orgnz)){ //LCD사업부
		titleImage = "system_logo7";
	}else if("B00000007".equals(blngt_orgnz)){ //DMC부문(Degital Media & Communications Division)
		titleImage = "system_logo8";
	}else if("B00000008".equals(blngt_orgnz)){ //CE담당(Comsumer Electronics Division)
		titleImage = "system_logo9";
	}else if("B00000009".equals(blngt_orgnz)){ //생활가전사업부
		titleImage = "system_logo10";
	}else if("B00000010".equals(blngt_orgnz)){ //IM담당(IT & Mobile Comunications Division)
		titleImage = "system_logo11";
	}else if("B00000011".equals(blngt_orgnz)){ //IT솔루션사업부
		titleImage = "system_logo12";
	}else if("B00000012".equals(blngt_orgnz)){ //네트워크사업부
		titleImage = "system_logo13";
	}else if("B00000013".equals(blngt_orgnz)){ //디지털이미징사업부
		titleImage = "system_logo14";
	}else if("B00000017".equals(blngt_orgnz)){ //Dedia Solution 센터
		titleImage = "system_logo15";
	}else if("B00000018".equals(blngt_orgnz)){ //의료기기사업팀
		titleImage = "system_logo18";
	}else if("B00000019".equals(blngt_orgnz)){ //한국총괄
		titleImage = "system_logo19";
	}else if("B00000020".equals(blngt_orgnz)){ //소프트웨어센터
		titleImage = "system_logo20";
	}else if("B00000021".equals(blngt_orgnz)){ //DMC연구소
		titleImage = "system_logo21";
	}else if("B00000022".equals(blngt_orgnz)){ //글로벌마케팅실
		titleImage = "system_logo22";
	}else if("B00000023".equals(blngt_orgnz)){ //디자인경영센터
		titleImage = "system_logo23";
	}else if("B00000024".equals(blngt_orgnz)){ //CS환경센터
		titleImage = "system_logo24";
	}else if("B00000025".equals(blngt_orgnz)){ //제조기술센터
		titleImage = "system_logo25";
	}else if("B00000026".equals(blngt_orgnz)){ //B2B지원센터
		titleImage = "system_logo26";
	}else if("B00000027".equals(blngt_orgnz)){ //환경안전센터
		titleImage = "system_logo27";
	}else if("B00000028".equals(blngt_orgnz)){ //수원지원센터
		titleImage = "system_logo28";
	}else if("B00000029".equals(blngt_orgnz)){ //구미지원센터
		titleImage = "system_logo29";
	}else if("B00000030".equals(blngt_orgnz)){ //인재개발센터
		titleImage = "system_logo30";
	}else if("B00000033".equals(blngt_orgnz)){ //반도체연구소
		titleImage = "system_logo33";
	}else if("B00000034".equals(blngt_orgnz)){ //Test&Package센터
		titleImage = "system_logo34";
	}else if("B00000035".equals(blngt_orgnz)){ //Infra기술센터
		titleImage = "system_logo35";
	}else if("B00000036".equals(blngt_orgnz)){ //생산기술연구소
		titleImage = "system_logo36";
	}else if("B00000040".equals(blngt_orgnz)){ //LED사업부
		titleImage = "system_logo40";
	}else if("B00000041".equals(blngt_orgnz)){ //종합기술원
		titleImage = "system_logo41";
	}else if("B00000042".equals(blngt_orgnz)){ //상생협력센터
		titleImage = "system_logo42";
	}else if("B00000043".equals(blngt_orgnz)){ //서초지원센터
		titleImage = "system_logo43";
	}else if("B00000044".equals(blngt_orgnz)){ //정보보호센터
		titleImage = "system_logo44";
	}else if("B00000045".equals(blngt_orgnz)){ //경영지원실
		titleImage = "system_logo45";
	}else if("B00000046".equals(blngt_orgnz)){ //전자스포츠구단
		titleImage = "system_logo46";
	}else if("B00000047".equals(blngt_orgnz)){ //삼성사회공헌위원회
		titleImage = "system_logo47";
	}else if("A00000003".equals(blngt_orgnz)){ //IP센터
		titleImage = "system_logo_ip";
	}else{ //기본
		titleImage = "system_logo";
	}
	
	List menuSiteMapList = (List)request.getAttribute("menuSiteMapList");
	
	//계약서담당자변경
	boolean chgPersonflag = chgPersonflag(session);
%>	
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><spring:message code="clm.main.title" /></title>

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
	PopUpWindowOpen2('/las/openPopME.do', '840', '583', false, 'PopUpContactUsClm');		
}
//업무분야별 전문 변호사 팝업
function openExpertPop(){
	PopUpWindowOpen2('/las/openPopMSC.do', '840', '600', false, 'PopUpExpertClm');
}

</script>
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
	
	<!-- <input type="hidden" name="seqno"> -->
	<input type="hidden" name="menu_id" value="" />
	
	<input type="hidden" name="flag" value=""/>
</form>

<div id="topWrap">

	<!-- header -->
	<div id="header">
		<div id="logo"> 
			<a href="javascript:Menu.main('frm','C');" class="h1_logo"><IMG SRC="<%=IMAGE%>/common/<%=titleImage%>.gif"  BORDER="0" alt="<spring:message code="clm.page.msg.common.mcClms" htmlEscape="true" />"></a>
			<span style='margin-left:7px'><img src="<%=IMAGE%>/common/pncacc.gif"  border="0" alt="PRIVILEGED AND CONFIDENTIAL ATTORNEY-CLIENT COMMUNICATION" /></span>
			<!-- utility -->
			<ul class="utility">
				<li><a href="#" class="user_request"><img src="<%=IMAGE%>/icon/ico_user.gif" alt="user" title="user"/></a> 
				<%if(localeCode.equals("ko")){ %>
					<a href="#" class="user"><%=StringUtil.bvl((String)session.getAttribute("secfw.session.user_nm"), "")%> / <%=StringUtil.bvl((String)session.getAttribute("secfw.session.grade_nm"), "")%> / <%=StringUtil.bvl((String)session.getAttribute("secfw.session.dept_nm"), "")%></a> </li>
				<%}else{ %>
					<a href="#" class="user"><%=StringUtil.bvl((String)session.getAttribute("secfw.session.user_nm_en"), "")%> / <%=StringUtil.bvl((String)session.getAttribute("secfw.session.grade_nm_en"), "")%> / <%=StringUtil.bvl((String)session.getAttribute("secfw.session.dept_nm_en"), "")%></a> </li>	
				<%} %>	
				<li><a href="javascript:Menu.main('frm','C');"><img src="<%=IMAGE%>/icon/icon_home.gif" alt="Home" title="Home"/></a> </li>
				<li><a href="javascript:Menu.goSiteMap('frm','C');"><img src="<%=IMAGE%>/icon/icon_sitemap.gif" alt="Sitemap" title="Sitemap"/></a></li>   
				<%--<li><a href="<%=itvocUrl%>" target="_blank">IT VOC</a></li>--%>
				<!-- <li class="bg_none"><a href="javascript:Menu.logout('frm','C');">Logout</a></li> -->
				<%-- <li class="confidential"><img src="<%=IMAGE%>/icon/ico_confidential2.gif" alt="confidential"/></li>  --%>
				<li style='margin-top:-2px;'>
				<%if(localeCode.equals("ko")){ %>
					<a href=# onclick="javascript:frm.chgLangflag.value='ko';Menu.main('frm','C');" style='background:url(<%=IMAGE%>/btn/arr_dot_bg.gif) no-repeat right 1px;'>
					<img id="lang_ko" src="<%=IMAGE%>/btn/btn_on_l_kor.gif"  title='<spring:message code="clm.page.field.menu.koLang" />' /></a><a href=# onclick="javascript:frm.chgLangflag.value='en';Menu.main('frm','C');" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('lang_en','','<%=IMAGE%>/btn/btn_on_l_eng.gif',1)"><img id="lang_en" src="<%=IMAGE%>/btn/btn_off_l_eng.gif"  title='<spring:message code="clm.page.field.menu.enLang" />' /></a>
			
				<%}else{ %>
					<a href=# onclick="javascript:frm.chgLangflag.value='ko';Menu.main('frm','C');" style='background:url(<%=IMAGE%>/btn/arr_dot_bg.gif) no-repeat right 1px;' onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('lang_ko','','<%=IMAGE%>/btn/btn_on_l_kor.gif',1)">
					<img id="lang_ko" src="<%=IMAGE%>/btn/btn_off_l_kor.gif"  title='<spring:message code="clm.page.field.menu.koLang" />' /></a><a href=# onclick="javascript:frm.chgLangflag.value='en';Menu.main('frm','C');"><img id="lang_en" src="<%=IMAGE%>/btn/btn_on_l_eng.gif"  title='<spring:message code="clm.page.field.menu.enLang" />' /></a>
				<%} %>	
				</li>
				
				
				<li style='margin-top:-4px; margin-left:5px;'  onMouseOver='divShow(contactus)' onMouseOut='divHidden(contactus)'>
                	<a href="javascript:openContactUsPop();" style='background:none;'><img src="<%=IMAGE%>/new2011/contactus_btn.gif" alt="<spring:message code="clm.page.field.menu.execLawyer" />" title="<spring:message code="clm.page.field.menu.execLawyer" />" /></a><!--공무집행  대응 변호사-->
                </li>
			</ul>
			<!-- //utility -->
		</div>
		<div id='contactus' style='display:none;position:absolute; top:10px; right:137px; z-index:1'>
			<img src="<%=IMAGE%>/new2011/contactus_tip.png" alt="<spring:message code="clm.page.msg.common.announce001" htmlEscape="true" />">
		</div>
		<hr/>

		<!-- gnb -->
        <div class="gnb">
			<ul>
<%
				for(int idx=0;idx < menuList.size();idx++ ){	
				    ListOrderedMap menu = null;
				    menu = (ListOrderedMap)menuList.get(idx);
				    if(((Integer)menu.get("menu_level")).intValue()==1){
%>
				<li id="menu_<%=menuMax%>" onMouseOver="javascript:subMenuSet(<%=menuMax%>)">
					<a href="javascript:Menu.go('frm', '<c:url value='/secfw/main.do' />', 'forwardMainFrame', '<%=menu.get("menu_id")%>', '_self');">
					<%=menu.get("menu_nm")%>
					</a>
					<ul id="gnb_2depth_0<%=menuMax%>" class="gnb_2depth_0<%=menuMax%> gnb_2depth">		
					
<%
						int subMenuIdx = 1;

						for(int idx2=0;idx2 < menuList.size(); idx2++){
							ListOrderedMap subMenu = null;
							subMenu = (ListOrderedMap)menuList.get(idx2);
							
							if(((Integer)subMenu.get("menu_level")).intValue()==2 && subMenu.get("up_menu_id").equals(menu.get("menu_id"))){
								
								//계약서담당자 변경 view여부
								if("20110803091537677_0000000066".equals(subMenu.get("menu_id")) ){
									if(chgPersonflag){
									
									if(idx2 == menuList.size()){
%>
						<li id="menu_<%=menuMax%>_<%=subMenuIdx%>" class="last-menu"><a href="javascript:Menu.go('frm', '<c:url value='/secfw/main.do' />', 'forwardMainFrame', '<%=subMenu.get("menu_id")%>', '_self');"><%=subMenu.get("menu_nm")%></a></li>
<%									
									}else{
%>
						<li id="menu_<%=menuMax%>_<%=subMenuIdx%>"><a href="javascript:Menu.go('frm', '<c:url value='/secfw/main.do' />', 'forwardMainFrame', '<%=subMenu.get("menu_id")%>', '_self');"><%=subMenu.get("menu_nm")%></a></li>
<%
									}
									subMenuIdx++;
									
									}
								}else{
								
								if(idx2 == menuList.size()){
%>
						<li id="menu_<%=menuMax%>_<%=subMenuIdx%>" class="last-menu"><a href="javascript:Menu.go('frm', '<c:url value='/secfw/main.do' />', 'forwardMainFrame', '<%=subMenu.get("menu_id")%>', '_self');"><%=subMenu.get("menu_nm")%></a></li>
<%									
								}else{
%>
						<li id="menu_<%=menuMax%>_<%=subMenuIdx%>"><a href="javascript:Menu.go('frm', '<c:url value='/secfw/main.do' />', 'forwardMainFrame', '<%=subMenu.get("menu_id")%>', '_self');"><%=subMenu.get("menu_nm")%></a></li>
<%
								}
								subMenuIdx++;
								
								}
							}			
						}
%>														
					</ul>								
				</li>
<%				    	 
						menuMax++;	 
				    }
				}
%>			
			</ul>
			 <span class="collapse"><img src="<%=IMAGE%>/icon/ico_collapse.png" alt="collapse"  class="png24 cp" onclick="menu_Toggle(this, 'clm', 'logo', '');" /></span>
		</div>
        <!-- //gnb -->

		<!-- lnb -->
		<!-- //lnb -->

	</div>
	<!-- //header -->

	<!-- container -->
	<div id="container">
		
		<div style="height:18px"></div>
		<!-- location -->
		<div class="location"><IMG SRC="<%=IMAGE%>/icon/ico_home.gif" WIDTH="13" HEIGHT="11" BORDER="0" ALT="Home">Home  > <strong> <spring:message code="clm.page.msg.main.sitemap" /></strong></div>
		<!-- //location -->

		<!-- content -->
		<div id="content">
			<!-- title -->
			<div class="title">
				<h3><spring:message code="clm.page.msg.main.sitemap" /></h3>
			</div>
			<!-- //title -->				
			<div class="sitemap_Bbox_tl">
				<div class="sitemap_Bbox_tr">
					<div class="sitemap_Bbox_bl">
						<div class="sitemap_Bbox_br">
							 
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
						
						if(menueMax2 == 5 && ((Integer)menu.get("menu_level")).intValue() == 1){
%>
						</div>
							<div class="sitemap_list_box sitemap_four">
<%							
						}
						
						if(((Integer)menu.get("menu_level")).intValue() == 1){
%>
						    <div class="sitemap_list site_<%=menueMax2%>list"> <strong class="title"><img src="<%=IMAGE%>/new2011/sitemap_title0<%=menu.get("menu_img")%>.jpg" alt="" /></strong>
						      <ul class="map_list">						
<% 
							int subMenuIdx2 = 1;
							for(int idx2=0;idx2<menuSiteMapList.size();idx2++){
								ListOrderedMap subMenu = null;
								subMenu = (ListOrderedMap)menuSiteMapList.get(idx2);
								if(((Integer)subMenu.get("menu_level")).intValue()==2 && subMenu.get("up_menu_id").equals(menu.get("menu_id"))){
									//계약서담당자 변경 view여부
									if("20110803091537677_0000000066".equals(subMenu.get("menu_id")) ){
										if(chgPersonflag){

%>
									<li> <strong class="onedeps"><a href="javascript:Menu.go('frm', '<c:url value='/secfw/main.do' />', 'forwardMainFrame', '<%=subMenu.get("menu_id")%>', '_self');"><%=subMenu.get("menu_nm")%></a></strong>
										<ul class="twodeps_list">
<%
								int subMenuIdx3 = 1;
								for(int idx3=0;idx3<menuSiteMapList.size();idx3++){
									ListOrderedMap subMenu2 = null;
									subMenu2 = (ListOrderedMap)menuSiteMapList.get(idx3);
									if(((Integer)subMenu2.get("menu_level")).intValue()==3 && subMenu2.get("up_menu_id").equals(subMenu.get("menu_id"))){
%>
											<li><a href="javascript:Menu.detail('frm','_self','<%=subMenu.get("menu_id")%>','<%=subMenu2.get("menu_id")%>','<%=subMenu2.get("menu_url")%>');"><%=subMenu2.get("menu_nm")%></a></li>
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
									}else{
										
									
%>
									<li> <strong class="onedeps"><a href="javascript:Menu.go('frm', '<c:url value='/secfw/main.do' />', 'forwardMainFrame', '<%=subMenu.get("menu_id")%>', '_self');"><%=subMenu.get("menu_nm")%></a></strong>
										<ul class="twodeps_list">
<%
								int subMenuIdx3 = 1;
								for(int idx3=0;idx3<menuSiteMapList.size();idx3++){
									ListOrderedMap subMenu2 = null;
									subMenu2 = (ListOrderedMap)menuSiteMapList.get(idx3);
									if(((Integer)subMenu2.get("menu_level")).intValue()==3 && subMenu2.get("up_menu_id").equals(subMenu.get("menu_id"))){
%>
											<li><a href="javascript:Menu.detail('frm','_self','<%=subMenu.get("menu_id")%>','<%=subMenu2.get("menu_id")%>','<%=subMenu2.get("menu_url")%>');"><%=subMenu2.get("menu_nm")%></a></li>
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
%>							
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>							
	
		<!-- //content -->
		<!-- footer -->
		<div id="footer">
			<address>ⓒ SAMSUNG</address>
		</div>
		<!-- //footer -->
	</div>
	<!-- //container -->
</div>

</body>
</html>
<%!
//계약서담당자변경 view 여부 판단
public boolean chgPersonflag(HttpSession session) throws Exception{
	boolean retV = false;
	ArrayList roleList = (ArrayList)session.getAttribute("secfw.session.role");
	String managerYN = (String)session.getAttribute("secfw.session.manager_yn");
	
	ArrayList userRoleList = new ArrayList();
    if(roleList != null && roleList.size()>0){
    	for(int i=0; i < roleList.size() ;i++){
        	HashMap hm = (HashMap)roleList.get(i);
        	String roleCd = (String)hm.get("role_cd");
        	userRoleList.add(roleCd);
        }
	}
    String result = "";
    if(userRoleList != null && userRoleList.size()>0) {
    	
		if(userRoleList.contains("RA00") || userRoleList.contains("RD01")){
			result = "A";
		}else if(userRoleList.contains("RD02")){
			result = "B";
		}
	}
    
    //RA00,RD01 보여야함
    //RD02이면서 조직장인 경우 보여야함
    if("A".equals(result) || ("B".equals(result) && "Y".equals(managerYN))){
    	retV = true;
    }
    
    return retV;
}
%>
