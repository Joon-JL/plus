<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>:: Notice</title>
	
<style>
td {
	FONT-FAMILY: "돋움";
	FONT-SIZE: 9pt;
	color:#484848;
	line-height: 1.6;
	white-space: 10;

}
:link {color:#3b8aff;text-decoration: none;}
:visited {color:#003482;text-decoration: none;}
:hover  {color:#FF6634;text-decoration: none; text-decoration: underline;}
	.m		{font-family: "Arial,Helvetica, geneva,돋움,sans-serif"; font-size: 9pt; color:#454545; text-decoration:none ; }
	A:link.m	{font-family: "Arial,Helvetica, geneva,돋움,sans-serif"; font-size: 9pt; color:#454545; text-decoration:none ; }
  	A:visited.m	{font-family: "Arial,Helvetica, geneva,돋움,sans-serif"; color:#454545; text-decoration:none ; }
 	A:active.m	{font-family: "Arial,Helvetica, geneva,돋움,sans-serif"; color:#349034; text-decoration:none ; }
  	A:hover.m	{font-family: "Arial,Helvetica, geneva,돋움,sans-serif"; color:#349034; text-decoration:none ; }
body 
{scrollbar-face-color: #FFFFFF; scrollbar-shadow-color: #D9D9D9; 
scrollbar-highlight-color: #FFFFFF; scrollbar-3dlight-color: #D9D9D9; 
scrollbar-darkshadow-color: #FFFFFF; scrollbar-track-color: #F3F5FC; 
scrollbar-arrow-color: #D0D2B0}
.note {
	font-family: "돋움체";
	font-size: 24px;
	color: #1F3F5F;
	font-weight: bold;
}
.ln16{line-height:160%}
.border { border: 1 #D8D8D8; border-style:solid;}
.Pad_1{padding-left:5px}
.Pad_2{
	padding-left:20px;
	text-align: justify;}
.Pad_2_2{
	padding-left:30px;
	text-align: justify;}

.Pad_3{
	text-align: justify;	
}

.Pad_3_1{
	font-size: 13px;
	text-align: justify;
	color: #4C4C4C;
}

.Pad_left{
	text-align: left;	
}

</style>
<script language="javascript">
/**
* 하루동안 창을 열지 않기
*/
function closeWin(){
	var frm = document.frm ;	
	if (frm.closeEvent.checked ) {
		setCookie("noticePopupDS", "no" , 1); // 1일 간 쿠키적용 	
	}
	self.close();	
}

function setCookie( name, value, expiredays )
{
	var todayDate = new Date();
	todayDate.setDate( todayDate.getDate() + expiredays );
	document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";";
}

/**
 * 전체공지사항 상세
 */
function getBoardDetail(seqno) {
	
	var sUrl = "/clm/admin/board.do?seqno="+seqno+"&method=detailBoardByMain&menu_id=20110803092210347_0000000062";
	
	PopUpWindowOpen2(sUrl, '800', '575', true, 'PopUpDSNoticeDetail');
}

</script>
</head>
<BODY BGCOLOR="#FFFFFF" LEFTMARGIN="0" TOPMARGIN="0" MARGINWIDTH="0" MARGINHEIGHT="0">
<form name="frm" id="frm" method="post" autocomplete="off">
<TABLE WIDTH=340 BORDER=0 CELLPADDING=0 CELLSPACING=0>
  <TR>
		
    <TD COLSPAN=3> <IMG SRC="<%=IMAGE%>/popup_01.gif" ALT="" WIDTH=340 HEIGHT=369 border="0" usemap="#Map" ></TD>
	</TR>
	<TR>
		
    <TD width="247" height="31" background="<%=IMAGE%>/popup_02.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="71%">&nbsp;</td>
          <td width="29%"><input name="closeEvent" type="checkbox" onClick="javascript:closeWin();"></td>
        </tr>
      </table> </TD>
    <TD> <a href="javascript:window.close();"><IMG SRC="<%=IMAGE%>/popup_03.gif" ALT="" WIDTH=85 HEIGHT=31 border="0"></a></TD>
		<TD>
			<IMG SRC="<%=IMAGE%>/popup_04.gif" WIDTH=8 HEIGHT=31 ALT=""></TD>
	</TR>
</TABLE>
<map name="Map">
  <area shape="rect" href="javascript:" coords="90,341,243,372" onClick="javascript:getBoardDetail('95');"  > 
</map>

</form>
</body>
</html>
 