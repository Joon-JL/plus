<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%
	response.setDateHeader("Expires",0);
	response.setHeader("Pragma","no-cache");

	if(request.getProtocol().equals("HTTP/1.1")){
		response.setHeader("Cache-Control","no-cache");
	}

	String langCd = (String)session.getAttribute("secfw.session.language_flag"); // 언어코드(ko|en)
	String sysCd  = (String)session.getAttribute("secfw.session.sys_cd");        // 시스템코드(LAS)
	String compCd = (String)session.getAttribute("secfw.session.comp_cd");       // 회사코드

	// 세션이 생성안된 상태에서 바로 이 페이지를 호출 시 에러가 발생하므로 그럴 경우 처리
	if (langCd == null) {
		langCd = "en";
	}

	if (sysCd == null) {
		sysCd = "las";
		//String f_c = (String)request.getAttribute("f");
		//if(f_c != null && f_c.equals("c")) sysCd = "clm";
		//else if(f_c != null && f_c.equals("l")) sysCd = "las";
	}

	String IMAGE = "/images/"+ sysCd.toLowerCase() + "/" + langCd; // 이미지 경로설정
	String CSS 	 = "/style/"+ sysCd.toLowerCase() + "/" + langCd;  // CSS 경로 설정

	request.setAttribute("langCd",langCd);

	if (langCd.equals("ko")) {
		request.setAttribute("langCd2","H");
	} else {
		request.setAttribute("langCd2","F");
	}
%>
<script language="javascript">
	var naviTitle = "<c:out value='${menuNavi}' escapeXml='false'/>";

	if (naviTitle != "" && naviTitle.indexOf("span") >= 0) {
		naviTitle = naviTitle.substring(naviTitle.lastIndexOf("<span>")+6, naviTitle.lastIndexOf("</span>"));

		if (opener == null) {
		   window.parent.parent.parent.document.title = naviTitle;
		}
	}
</script>