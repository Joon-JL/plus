<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%--
/**
 * 파  일  명 : CommonProgress.jsp
 * 프로그램명 : 공통_진행창
 * 설      명 : 
 * 작  성  자 : 한지훈
 * 작  성  일 : 2012.06.18
 */
--%>
<script language="javascript">
function viewHiddenProgress(view) {
	var obj = document.getElementById("progress") ;
	if(view){
		obj.style.display = "" ;
		document.getElementById("progressFrame").height = 109 ;
		document.getElementById("progressFrame").width = 329 ;
		
		var ltop_xp	= document.body.clientWidth/2;
		var ltop_yp	= document.body.clientHeight/3+document.body.scrollTop;
		
		document.getElementById('progressbar').style.left = (ltop_xp-165) + "px";
		document.getElementById('progressbar').style.top = (ltop_yp-55) + "px";
	} else {
		obj.style.display = "none" ;
	}
}
</script>

<div id='progress' style='position:absolute; display: none; z-index:10; top:0; left:0; width:100%; height:100%; background:url(/images/secfw/common/opa.png) repeat;'>
	<div id='progressbar' style='width:331px;height:113px;  text-align:center; position:absolute; top:0; left:0;text-align:center;font-size:12px;line-height:180%;background:#fff; border-top:1px solid #7bb9e2;border-right:1px solid #1f6096;border-bottom:1px solid #001d69;border-left:1px solid #4385b5'>
		<div name="progressFrame" id="progressFrame" style='height:109px;border-top:2px solid #a7d5ed;border-right:2px solid #75a0c3;border-bottom:2px solid #4a6c9c;border-left:2px solid #81a8c7'>
			<img src="/images/secfw/common/progress_bar.gif" style="margin:18px 0 5px 0;"><br/>
			<span style="border:none;margin:10px 0 0 0px; font-family:Malgun Gothic;"><strong style="color:#36C; font-size:14px"><spring:message code="secfw.page.field.common.isWorking"/>.</strong><br /><spring:message code="secfw.page.field.common.plzWait"/></span>
		</div>
	</div>
</div>