<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%--
/**
 * 파  일  명 : DlgAlert.jsp
 * 프로그램명 : 공통 Alert창
 * 설      명 : 
 * 작  성  자 : 한지훈
 * 작  성  일 : 2012.04.26
 */
--%>
<script language="javascript">
	function viewDlgAlert(msg) {
		
		if(msg){
			if(msg == "processEnd"){
				msg = "<spring:message code='secfw.page.field.common.isCompleted' />";
			}
			var html = "" ;
			html += '    <table border="0" cellspacing="0" cellpadding="0">\r\n' ;
			html += '    	<tr>\r\n' ;
			html += '    		<td align="left" valign="top">\r\n' ;
			html += '    			<table border="0" cellspacing="0" cellpadding="0">\r\n' ;
			html += '    				<tr>\r\n' ;
			html += '    					<td align="left" valign="top">\r\n' ;
			html += '    			  			<div style="display:block;float:left;width:331px;height:109px; font-size:15px; font-family:Malgun Gothic; background:url(/images/secfw/common/progress_bg.jpg) no-repeat;padding:25px 0 0 52px;">\r\n' ;
			html += '    			  				<p style="font-family:Malgun Gothic; float:left;sans-serif!important;color:#333;font-weight:bold; font-size:16px;letter-spacing:-1px; line-height:230%"><img src="/images/secfw/common/confirm.png">' + msg + '</p>\r\n' ;
			html += '                  			</div>\r\n' ;
			html += '    					</td>\r\n' ;
			html += '    				</tr>\r\n' ;
			html += '    			</table>\r\n' ;
			html += '    		</td>\r\n' ;
			html += '    	</tr>\r\n' ;
			html += '    </table>\r\n' ;
			html += '<LINK href="/style/secfw/common.css" type="text/css" rel="stylesheet">\r\n' ;
			dlgAlertFrame.document.body.innerHTML = html ;
			
			document.getElementById("dlgAlert").style.display = "block" ;
			document.getElementById("dlgAlert").focus();

			var ltop_xp	= document.body.clientWidth/2;
			var ltop_yp	= document.body.clientHeight/3+document.body.scrollTop;
			
			document.getElementById('dlgAlert').style.left = (ltop_xp-165) + "px";
			document.getElementById('dlgAlert').style.top = (ltop_yp-55) + "px";
			
			window.setTimeout(function() {document.getElementById("dlgAlert").style.display = "none";},2500); 
		}
	}
</script>

<div id='dlgAlert' style='position: absolute; display: none; ridge; z-index:1; top:0; left:0'>
	<iframe name="dlgAlertFrame" id="dlgAlertFrame" width="331" height="109" src="" frameborder="0" scrolling="no" width="0" height="0">
	</iframe>
</div>