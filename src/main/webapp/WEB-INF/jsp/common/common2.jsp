<%
	response.setDateHeader("Expires",0);
	response.setHeader("Pragma","no-cache");
	if(request.getProtocol().equals("HTTP/1.1")){
		response.setHeader("Cache-Control","no-cache");
	}

	String langCd = (String)session.getAttribute("secfw.session.language_flag");
	String sysCd = (String)session.getAttribute("secfw.session.sys_cd");

	//이미지 경로설정
	//String IMAGE = "/images/"+ sysCd.toLowerCase() + "/" + langCd; // for multi lang
	String IMAGE = "/images/"+ sysCd.toLowerCase() + "/ko";
	
	//CSS 경로 설정
	//String CSS 	 = "/style/"+ sysCd.toLowerCase() + "/" + langCd; // for multi lang
	String CSS 	 = "/style/"+ sysCd.toLowerCase() + "/ko";
%>
