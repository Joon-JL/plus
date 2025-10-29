<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.apache.commons.collections.map.ListOrderedMap" %>
<%
	List 	menuList 	 	=  (List)request.getAttribute("menuList");
	String	topMenuLevel 	=  (String)request.getAttribute("topMenuLevel");
	String	targetMenuId	=  (String)request.getAttribute("targetMenuId");
	int     menuSize        =  Integer.parseInt((String)request.getAttribute("menuSize"));  
	int 	menuIdCnt 		= 0;
	int 	menuMax 		= 1;
	
	String localeCode = (String)session.getAttribute("secfw.session.language_flag");
	
%>	
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<html xmlns='http://www.w3.org/1999/xhtml'>
<title>▒▒ Health Priority ▒▒</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<LINK href="<%=CSS%>/main.css" type="text/css" rel="stylesheet">
<LINK href="<%=CSS%>/HP_top.css" type="text/css" rel="stylesheet">
<script language='javascript' src="<c:url value='/script/shri/common.js' />"></script> 
<script language="javascript" src="<c:url value='/script/shri/main.js' />" type="text/javascript"></script>
<script language="javascript" src="<@spring.url '/script/shri/menu.js'/>"></script>

<script src="<c:url value='/script/shri/flashObj.js' />"></script>

<script language="javascript">
/*
* 써브메뉴의 목록을 지워준다. 
*/
 function HideAllSubMenu(){

<%
	for(int idx=0;idx<menuList.size();idx++){	
	    ListOrderedMap menu = null;
	    menu = (ListOrderedMap)menuList.get(idx);
	    if(((Integer)menu.get("menu_level")).intValue() == 1)
	    {	
	    	 menuIdCnt =menuIdCnt+1;
%>	 
			divHidden(sub_menu0<%=menu.get("menu_img")%>);
<%
	    }
	}
%>
}

function goFileTest(gbn){
	var frm = document.frm;
	
	frm.target					= "_self";
	frm.action 					= "<c:url value='/clms/common/clmsfile.do' />";
	frm.method.value 			= "forwardFileTest";	
	frm.view_gbn.value = gbn;
	frm.submit();
	
	
}

 /*
 * 페이지 로그아웃
 */
function logout(){
	frm.action 					= "<c:url value='/secfw/user.do' />";
	frm.method.value 			= "shriLogOut";	
	frm.submit();
	window.close();
 }


/*
* 삼성 건강연구소 그림 Clcik 
*/
 function main(){
	frm.action 					= "<c:url value='/secfw/main.do' />";
	frm.method.value 			= "forwardMainPage";	
	frm.submit();
}
/*
* 써브메뉴의 의 클레스를 변경해준다. 
*/
 function SubMenuClassChg(class_nbr){
	  document.getElementById("menu_sub").className   = "sub_menu_0"+class_nbr;
 }
/*
* 선택된 서브  메뉴 의 항목을 띄워준다.  Menu.js go참조바람
*/
function Menu_go(formId, actionUrl, methodNm, menuId, menuNm, targetNm,systemNm) {
	var frm = document.getElementById(formId);
	
	if (actionUrl == "") {
	//	return;
	}
	
	frm.action 					= "<c:url value='/secfw/main.do' />";
	frm.method.value 			= "forwardMainFrame";
	frm.targetMenuId.value 		= menuId;
	frm.selected_menu_id.value  = menuId; 			//중메뉴 설정
	frm.selected_menu_detail_id.value  = menuId;	//디테일값 설정	
	frm.target = "_self";
	frm.submit();
}
function goNotice(DetailId,Div){	/*What's New 공지사항*/
	menuId                      = "20110329174748717_0000000018";
	frm.action 					= "<c:url value='/secfw/main.do' />";
	frm.method.value 			= "forwardMainFrame";
	frm.targetMenuId.value 		= menuId;
	frm.selected_menu_id.value  = menuId; 			//중메뉴 설정
	frm.selected_menu_detail_id.value  = menuId;	//디테일값 설정
	if(Div=="detail"){	
		frm.set_init_url.value      ="/shri/notice.do?method=forwardDetailForm&notice_id="+DetailId;
	}else if(Div=="list"){
		frm.set_init_url.value      ="";
	}
	frm.target = "_self";
	frm.submit();
}
function goTrueNotice(DetailId,Div){ /*진실의창 공지사항*/
	menuId                      = "20110329174749372_0000000019";
	frm.action 					= "<c:url value='/secfw/main.do' />";
	frm.method.value 			= "forwardMainFrame";
	frm.targetMenuId.value 		= menuId;
	frm.selected_menu_id.value  = menuId; 			//중메뉴 설정
	frm.selected_menu_detail_id.value  = menuId;	//디테일값 설정	
	if(Div=="detail"){	
		frm.set_init_url.value      ="/shri/truth.do?method=forwardDetailForm&notice_id="+DetailId;
	}else if(Div=="list"){
		frm.set_init_url.value      ="";
	}

	frm.target = "_self";
	frm.submit();
}
function goMagazine(DetailId,Div){ /*사내외주요발간지.*/
	menuId                      = "20110329175825402_0000000026";
	frm.action 					= "<c:url value='/secfw/main.do' />";
	frm.method.value 			= "forwardMainFrame";
	frm.targetMenuId.value 		= menuId;
	frm.selected_menu_id.value  = menuId; 			//중메뉴 설정
	frm.selected_menu_detail_id.value  = menuId;	//디테일값 설정	
	if(Div=="detail"){	
		frm.set_init_url.value      ="/shri/publication.do?method=forwardDetailForm&notice_id="+DetailId;
	}else if(Div=="list"){
		frm.set_init_url.value      ="";
	}

	frm.target = "_self";
	frm.submit();
	
}

function goTestNotice(){ /*공지사항 TEST*/
	var frm				= document.frm;
	frm.action 			= "<c:url value='/shri/testfaq.do' />";
	frm.method.value 	= "listPage";
	frm.target 			= "_self";
	frm.submit();
}

function goTestQuestion() {	/*알려주세요? TEST*/
	var frm				= document.frm;
	frm.curPage.value	= "1";
	frm.action			= "<c:url value='/shri/testQuestion.do' />";
	frm.method.value	= "listPage";
	frm.target			= "_self";
	frm.submit();
}

/*
function goTestKnow() {	//궁금증 해결 TEST//
	var frm				= document.frm;
	frm.action			= "<c:url value='/shri/textQuestion.do' />";
	frm.method.value	= "listPage";
	frm.target			= "_self";
	frm.submit();
}
*/

function setChangeLang(vLangflag)
{  
	frm.action			  = "<c:url value='/secfw/main.do' />";
	frm.method.value	  = "forwardMainPage";
	frm.chgLangflag.value = vLangflag;
	frm.target			  = "_self";
	frm.submit();	
} 

</script>

<body leftmargin='0' topmargin='0' class='mainBg' style="overflow-y:auto">
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
	
	<!-- 파일첨부 테스트 -->
	<input type="hidden" name="view_gbn" 	value="" />
	
	<!-- 파일첨부 테스트 -->
</form>





<div class='main_top'>
	<div class='menuWrap'>
		<div class='logo_hp fL' onClick="main()"></div>
		<div class='menu_hm fL' >
			
			<div class='langWrap fL'>
			<%if(localeCode.equals("ko")){ %>	
				<div class="bt_langKorOk"></div>
			<%}else{ %>
				<div class="bt_langKorNO" onMouseover="className='bt_langKorOk'" onMouseOut="className='bt_langKorNO'" onclick="javascript:setChangeLang('ko')"></div>
			<%} %>
			<%if(localeCode.equals("en")){ %>	
				<div class="bt_langEngOk"></div>
			<%}else{ %>
				<div class="bt_langEngNO" onMouseover="className='bt_langEngOk'" onMouseOut="className='bt_langEngNO'" onclick="javascript:setChangeLang('en')"></div>
			<%} %>
			<%if(localeCode.equals("fr")){ %>	
				<div class="bt_langEngOk"></div>
			<%}else{ %>
				<div class="bt_langEngNO" onMouseover="className='bt_langEngOk'" onMouseOut="className='bt_langEngNO'" onclick="javascript:setChangeLang('fr')"></div>
			<%} %>
			<%if(localeCode.equals("de")){ %>	
				<div class="bt_langEngOk"></div>
			<%}else{ %>
				<div class="bt_langEngNO" onMouseover="className='bt_langEngOk'" onMouseOut="className='bt_langEngNO'" onclick="javascript:setChangeLang('de')"></div>
			<%} %>
			<%if(localeCode.equals("it")){ %>	
				<div class="bt_langEngOk"></div>
			<%}else{ %>
				<div class="bt_langEngNO" onMouseover="className='bt_langEngOk'" onMouseOut="className='bt_langEngNO'" onclick="javascript:setChangeLang('it')"></div>
			<%} %>
			<%if(localeCode.equals("es")){ %>	
				<div class="bt_langEngOk"></div>
			<%}else{ %>
				<div class="bt_langEngNO" onMouseover="className='bt_langEngOk'" onMouseOut="className='bt_langEngNO'" onclick="javascript:setChangeLang('es')"></div>
			<%} %>
			<%if(localeCode.equals("zh")){ %>	
				<div class="bt_langChnOk"></div>
			<%}else{ %>
				<div class="bt_langChnNO" onMouseover="className='bt_langChnOk'" onMouseOut="className='bt_langChnNO'" onclick="javascript:setChangeLang('zh')"></div>
			<%} %>	
			</div>
			
			<div class='menu_header fR'>
				<div class="tbl_l fL"></div>
				<div class="tbl_blank fL"></div>
				<div class="tbl_menu01 fL" onclick="javascript:main()"></div>
				<div class="tbl_blank fL"></div>
				<div class="tbl_line fL"></div>
				<div class="tbl_blank fL"></div>
				<div class="tbl_menu03 fL" onClick="javascript:logout()"></div>
				<div class="tbl_blank fL"></div>
				<div class="tbl_r fL"></div>
			</div>

			
				
		<div class="menu_main fR">
<%
				menuIdCnt 		= 0;
				menuMax 		= 1;

				for(int idx=0;idx < menuList.size();idx++ )
				{	
				    ListOrderedMap menu = null;
				    menu = (ListOrderedMap)menuList.get(idx);
				    if(((Integer)menu.get("menu_level")).intValue()==1)
				    {
				   	 menuMax++;
			    	 menuIdCnt =menuIdCnt+1;
%>																																																																								
							<div class='menu0<%=menu.get("menu_img")%>' onMouseover="className='menu0<%=menu.get("menu_img")%>_over';HideAllSubMenu();SubMenuClassChg('<%=menu.get("menu_img")%>');divShow(sub_menu0<%=menu.get("menu_img")%>)" onMouseOut="className='menu0<%=menu.get("menu_img")%>'"  id="a_<%=menu.get("menu_img")%>" 
								onclick="javascript:Menu_go('frm','<%=menu.get("menu_url")%>','','<%=menu.get("menu_id")%>','<%=menu.get("menu_nm")%>','bodyFrame','<%=menu.get("menu_id")%>')"  
<%                              							
								if(targetMenuId.equals(menu.get("menu_id")))
								{
%>
									class="selected_thub"
<%
								}else{
%>									
									class="thub"
<%
								}
%>
									>
							</div>
<%
							if(menuMax <=  menuSize )
							{
%>							
						    <div class='menu_l'></div>						    
<%
							}
				    }
				}	
%>
		</div>
		</div>
	</div>
</div>


<!-- Menu Layer -->
<div class='sub_menu_01'  id="menu_sub" name="menu_sub" >
<!--선택된 메뉴 아이디-->
<%
	String selectedMenu 		= "";
    if(topMenuLevel.equals("2"))
    {
    	int		LargeMenuIdCnt 	=0;
    	int     checkChildCnt   =0;
    	int 	childCnt		=0;
    	String 	menuId			="";
    	int 	beforLvel       =0;
    	int 	nowLvel         =1;

		for(int idx=0;idx < menuList.size();idx++ )
		{	
		    ListOrderedMap subMenu = null;
		    subMenu = (ListOrderedMap)menuList.get(idx);
		   // if(((BigDecimal)menu.get("menu_level")).intValue()==1)
			nowLvel =((Integer)subMenu.get("menu_level")).intValue();
		   if(subMenu.get("up_menu_id").equals("root"))
			{
				LargeMenuIdCnt = LargeMenuIdCnt + 1;
				menuId   =(String)subMenu.get("menu_id");
			
				checkChildCnt = 0;
			}
		   
		   if(nowLvel==1 && idx == 0)
		   {
%>
		<div style="display:;" id="sub_menu0<%=subMenu.get("menu_img")%>" class='sub_menu0<%=subMenu.get("menu_img")%>'>
<% 
		   }else if(nowLvel==1 && beforLvel==1)
		   {
%>		   
		</div>
		<div style="display:non;" id="sub_menu0<%=subMenu.get("menu_img")%>" class='sub_menu0<%=subMenu.get("menu_img")%>'>
<%		   
		   }else if(nowLvel==1 && beforLvel==2)
		   {
%>		   
		</div>
		<div style="display:non;" id="sub_menu0<%=subMenu.get("menu_img")%>" class='sub_menu0<%=subMenu.get("menu_img")%>'>
<%		   
			}else if(nowLvel == 2 )
		   {
%>
		    	<li>										
		    		<a href="javascript:Menu_go('frm','<%=subMenu.get("menu_url")%>','','<%=subMenu.get("menu_id")%>','<%=subMenu.get("menu_nm")%>','bodyFrame','<%=subMenu.get("menu_id")%>');">
		    		<%
		    			if("ko".equals(localeCode)){
		    		%>
		    		<%=(String)subMenu.get("menu_nm") %>
		    		<% 	
		    			}else if("zh".equals(localeCode)){
		    		%>
		    		<%=(String)subMenu.get("menu_nm_cha") %>
		    		<% 	
		    			}else if("fr".equals(localeCode)){
		    		%>
		    		<%=(String)subMenu.get("menu_nm_fra") %>
		    		<% 	
		    			}else if("de".equals(localeCode)){
		    		%>
		    		<%=(String)subMenu.get("menu_nm_deu") %>
		    		<% 	
		    			}else if("it".equals(localeCode)){
		    		%>
		    		<%=(String)subMenu.get("menu_nm_ita") %>
		    		<% 	
		    			}else if("es".equals(localeCode)){
		    		%>
		    		<%=(String)subMenu.get("menu_nm_esp") %>		
		    		<%
		    			}else{
		    		%>
		    		<%=(String)subMenu.get("menu_nm_eng") %>
		    		<%
		    			}
		    		%>
		    		</a>
		    	</li>
<% 
		   }
		 if((idx+1) == menuList.size()) 
		 {	 
%>		    	
		</div>
<%
		 }
%>

<% 
beforLvel = nowLvel;
		}

    }
%>
</div>
 
<div class="contentWrap" >
	<div class="content_area">
		<!-- img -->
		<div>
		<table width="100%" class="main_bg">
			<tr>
				<td align="center" valign="top">
					
					<table border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td align="left"><script>FlashObject("<%=IMAGE%>/main/main.swf","980","365","#ffffff")</script></td>
						</tr>
					</table>
				
				</td>
			</tr>
		</table>
		</div>
		<!-- //img -->

		<!-- What's New -->	
		<div class="bbs_area">
			<!-- bbs -->
			<div class="fL bbs">
				<table  class="bbs">
					<colgroup>
					<col width="*">
					<col width="27%">
					</colgroup>
					
									
					<tr>
						<td class="bbs_news" colspan="2"><a href="#"><img src="<%=IMAGE%>/main/btn_more.gif" onclick="javascript:goNotice('','list')" ></a></td>
					</tr>
					<c:forEach var="list" items="${noticeList}">
					<tr>
						<td class="bbs_line overflow"><a href="javascript:goNotice('<c:out value="${list.notice_id}"/>','detail')"><c:out value="${list.title}"/></a></td> 
						<td class="tC bbs_date"><c:out value="${list.reg_dt_date}"/></td>  
					</tr>
					</c:forEach>
				</table>
			</div>
			
		<!-- 진실의창 -->	
			<div class="fL bbs" style="margin-left:36px;">
				<table  class="bbs">
					<colgroup>
					<col width="*">
					<col width="27%">
					</colgroup>
									
					<tr>
						<td class="bbs_info" colspan="2"><a href="#"><img src="<%=IMAGE%>/main/btn_more.gif" onclick="javascript:goTrueNotice('','list')"></a></td>
					</tr>
					<c:forEach var="list" items="${truthList}">
					<tr>
						<td class="bbs_line overflow"><a href="javascript:goTrueNotice('<c:out value="${list.notice_id}"/>','detail')"><c:out value="${list.title}"/></a></td>
						<td class="tC bbs_date"><c:out value="${list.reg_dt_date}"/></td> 
					</tr>
					</c:forEach>
				</table>
			</div>
			<!-- //bbs -->
			
			
			<!-- 사내외 주요 발간지 -->
			<div class="fR">
				<table>
					<tr>
						<td class="title_file" colspan="2"><a href="#"><img src="<%=IMAGE%>/main/btn_more.gif" onclick="javascript:goMagazine('','list')"></a></td>
					</tr>
					<tr>
						<td class="file_photo" rowspan="3"> </td>
						<td valign="top">
							<table>
								<c:forEach var="list" items="${publicationList}">
								<tr>
									<td class="file_line"><div  style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;width:100px" ><a href="javascript:goMagazine('<c:out value="${list.notice_id}"/>','detail')"><c:out value="${list.title}"/></a></div></td>
								</tr>
								</c:forEach>
							</table>
						</td>
					</tr>
 
				</table>
			</div>

			<!-- 사내외 주요 발간지 끝 -->		
			
			
			<!-- //사내외 주요 발간지 -->
			
			
			<!-- banner -->
			<div class="tC banner">
				<table >
					<tr>
						<td><a href="" target="_blank"><img src="<%=IMAGE%>/main/btn_ba01.gif"></a></td>
						<td><a href="" target="_blank"><img src="<%=IMAGE%>/main/btn_ba02.gif"></a></td>
						<td><a href="http://www.kbsmc.co.kr/" target="_blank"><img src="<%=IMAGE%>/main/btn_ba03.gif"></a></td>
						<td><a href="http://www.greensamsung.com/" target="_blank"><img src="<%=IMAGE%>/main/btn_ba04.gif"></a></td>
						<td><a href="http://www.cdc.gov/niosh/" target="_blank"><img src="<%=IMAGE%>/main/btn_ba05.gif"></a></td>
					</tr>
				</table>
			</div>
			<!-- banner -->


			<!-- 첨부파일 테스트 -->
			<div class="tC banner">
				<table >
					<tr>
						<td colspan="3"><b>&lt;<spring:message code="secfw.page.field.main.testAttached"/>&gt;</b></td>
					</tr>
					<tr style="height:20">
						<td><a href="javascript:goFileTest('upload');" ><spring:message code="secfw.page.field.main.newUpload"/></a></td>

						<td><a href="javascript:goFileTest('modify');" ><spring:message code="secfw.page.field.main.newUpdate"/></a></td>

						<td><a href="javascript:goFileTest('download');" ><spring:message code="secfw.page.field.main.newDown"/></a></td>
					</tr>
				</table>
			</div>
			<!-- 첨부파일 테스트 -->

		<!-- 
			<table>
				<tr>
					<td colspan='5'><a href="javascript:goTestNotice()" >TEST FAQ</a></td>
				</tr>
			</table>
		 -->
			<!-- //banner -->

	</div>
</div>

<!-- footer -->
<div class="footer">		
	<script language="JavaScript" src="/script/shri/footer.js"></script>
</div>
<!-- //footer-->

</body>
</html>
