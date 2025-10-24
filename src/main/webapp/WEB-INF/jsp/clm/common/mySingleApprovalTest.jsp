<%@ page language="java" contentType="text/html;" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%

//String cnsdreq_id = request.getParameter("cnsdreq_id");
//String is_contractreview = request.getParameter("is_contactreview");
String str = (String)request.getAttribute("status_mode");

//String menuNavi = (String)request.getAttribute("secfw.menuNavi");
%>
Hello World from [<%=str%>]
