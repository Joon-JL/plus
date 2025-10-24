<%@page contentType="text/html;charset=utf-8" %>
<%@include file = "./include/session_check.jsp"%>
<%@page import="java.io.*"%>
<%@page import="java.net.*"%>
<%
	//2014-02-25 신성우 swat test replacement Decode 처리
	String memId = URLDecoder.decode(session.getAttribute("memId").toString(),"UTF-8");
	String webPageKind = URLDecoder.decode(session.getAttribute("webPageKind").toString(),"UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>Namo CrossEditor : Admin</title>
	<script type="text/javascript"> var ce_js = "ce_pp"; </script>
	<script type="text/javascript" src="../manage_common.js"></script>
	<script type="text/javascript" language="javascript" src="../../js/namo_cengine.js"></script>
	<script type="text/javascript" language="javascript" src="../manager.js"></script>
	<link href="../css/common.css" rel="stylesheet" type="text/css">
</head>

<body>

<%@include file = "../include/top.html"%>

<div id="ce_OT" class="ce_do">	
	<table class="ce_ip">
	  <tr>
		<td class="ce_do">
		
			<table id="Info">
				<tr>
					<td style="padding:0 0 0 10px;height:30px;text-align:left">
					<font style="font-size:14pt;color:#3e77c1;font-weight:bold;text-decoration:none;"><span id="ce_pM"></span></font></td>
					<td id="InfoText"><span id="ce_lP"></span></td>
				</tr>
				<tr>
					<td colspan="2"><img id="ce_nd" src="../images/title_line.jpg" alt="" /></td>
				</tr>
			</table>
		
		</td>
	  </tr>
	  <tr>
		<td class="ce_do">
			
				<form method="post" id="ce_Qx" action="account_proc.jsp" onsubmit="return ce_y(this);">
				<table class="ce_ft" >
				  <tr>
					<td>

						<table class="ce_bN">
						  <tr><td class="ce_cY" colspan="3"></td></tr>
						</table>
						 
						<table class="ce_bN" >
						  <tr>
							<td class="ce_bF">&nbsp;&nbsp;&nbsp;&nbsp;<b><span id="ce_oI"></span></b></td>
							<td class="ce_bu"></td>
							<td class="ce_bw">
								<input type="hidden" name="u_id" id="u_id" value="<%=memId%>" />
								<input type="password" name="passwd" id="passwd" value="" class="ce_gC" />
							</td>
						  </tr>
						  <tr>
							<td class="ce_bz" colspan="3"></td>
						  </tr>
						  <tr>
							<td class="ce_bF">&nbsp;&nbsp;&nbsp;&nbsp;<b><span id="ce_tB"></span></b></td>
							<td class="ce_bu"></td>
							<td class="ce_bw">
								<input type="password" name="newPasswd" id="newPasswd" value="" class="ce_gC" />
							</td>
						  </tr>
						  <tr>
							<td class="ce_bz" colspan="3"></td>
						  </tr>
						  <tr>
							<td class="ce_bF">&nbsp;&nbsp;&nbsp;&nbsp;<b><span id="ce_tx"></span></b></td>
							<td class="ce_bu"></td>
							<td class="ce_bw">
								<input type="password" name="newPasswdCheck" id="newPasswdCheck" value="" class="ce_gC" />
							</td>
						  </tr>
						</table>
					
						<table class="ce_bN">
						  <tr><td class="ce_cY" colspan="3"></td></tr>
						</table>
								
					</td>
				  </tr>
				  <tr id="ce_sH">
					<td id="ce_sr">
						<ul style="margin:0 auto;width:170px;">
							<li class="ce_ej">
								<input type="submit" id="ce_qR" value="" class="ce_ed ce_dd" style="width:66px;height:26px;" />
							</li>
							<li class="ce_ej"><input type="button" id="ce_lu" value="" class="ce_ed ce_dd" style="width:66px;height:26px;"></li>
						</ul>
					</td>
				  </tr>
				</table>
				</form>
		
		</td>
	  </tr>
	</table>

</div>

<%@include file = "../include/bottom.html"%>

</body>
<script>
var webPageKind = '<%=webPageKind%>'
topInit();
ce_x();
</script>

</html>

	
	

