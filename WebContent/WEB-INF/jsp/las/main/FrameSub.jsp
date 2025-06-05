<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>                                              
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core"%>                                                                
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>                                                              
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>    
                                                                                
<%                                                                                                                                  
String targetMenuId 		= StringUtil.bvl((String)request.getAttribute("targetMenuId"),"");
String selectedMenuId 		= StringUtil.bvl(request.getAttribute("selectedMenuId"), "");
String selectedMenuDetailId = StringUtil.bvl(request.getAttribute("selectedMenuDetailId"), "");
String set_init_url 		= StringUtil.bvl(request.getAttribute("setInitUrl"), "");

%>                                                                                                                                  
                                                                                                                                    
<%@page import="com.sds.secframework.common.util.StringUtil"%>                                                                      

<html>
<head>
<meta charset="utf-8" />
                                                                                                                   
<title><spring:message code="las.main.title" /></title>                                                                                
<!--[if IE]> <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script> <![endif]-->
                                                              
<frameset id='frameSub' name="frameSub" cols="180,*" frameborder="no" border="0" framespacing="0">  
  <frame src="<%=request.getContextPath() %>/secfw/main.ftl?method=forwardLeftPage&target_menu_id=<%=targetMenuId%>&selected_menu_id=<%=selectedMenuId%>&selected_menu_detail_id=<%=selectedMenuDetailId%>&set_init_url=<%=set_init_url %>" id="leftFrame" name="leftFrame" scrolling="no" noresize="noresize">
  <frame src="<%=request.getContextPath() %>/secfw/main2.do?method=forwardBodyPage" name="bodyFrame">
</frameset>                                                                                                                         
<noframes>                                                                                                                          
<body>                                                                                                                              
</body>                                                                                                                             
</noframes>                                                                                                                         
</html>                                                                                                                             