<%@page contentType="text/html; charset=UTF-8" %>
<%@page import="java.io.*"%>
<%@page import="java.net.*"%>
<%
	try {
		String userDomain = URLDecoder.decode(request.getParameter("userdomain"), "UTF-8");
		String funcname = URLDecoder.decode(request.getParameter("funcname"), "UTF-8");
		// 2014-02-25 신성우 swat test replacement Decode 처리
		//String param = request.getParameter("param");
		String param = URLDecoder.decode(request.getParameter("param"), "UTF-8");		// 2014-02-25 신성우 swat test replacement Decode 처리
		param = param.replace("%", "<namo-percent>");
		param = URLDecoder.decode(param, "UTF-8");
		param = param.replace("<namo-percent>", "%");

		String returnValue = "<scr" + "ipt language='javascript' type='text/javascript'>" + userDomain + "parent.window." + funcname + "(" + param + ");</scr" + "ipt>";
		out.println(returnValue);
	} catch (Exception e) {
		out.println("error");
	}
	
	return;
%>