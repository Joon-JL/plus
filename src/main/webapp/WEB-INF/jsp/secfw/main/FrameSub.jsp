<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>                                              
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core"%>                                                                
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>                                                              
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>    
                                                                                
<%                                                                                                                                  
String targetMenuId 		= StringUtil.bvl((String)request.getAttribute("targetMenuId"),"");
String selectedMenuId 		= StringUtil.bvl(request.getAttribute("selectedMenuId"), "");
String selectedMenuNm 		= StringUtil.bvl(request.getAttribute("selectedMenuNm"), "");
String selectedMenuDetailId = StringUtil.bvl(request.getAttribute("selectedMenuDetailId"), "");
String set_init_url 		= StringUtil.bvl(request.getAttribute("setInitUrl"), "");

%>                                                                                                                                  
                                                                                                                                    
<%@page import="com.sds.secframework.common.util.StringUtil"%>                                                                      

<html xmlns='http://www.w3.org/1999/xhtml'>                                                       
<head>                                                                                                                              
<title>Sub Frame Sample</title>                                                                                
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">                                                                 
<frameset id='frameSub' name="frameSub" cols="190,*" frameborder="no" border="0" framespacing="0">  
  <frame src="<%=request.getContextPath() %>/secfw/main.ftl?method=forwardLeftPage&target_menu_id=<%=targetMenuId%>&selected_menu_id=<%=selectedMenuId%>&selected_menu_nm=<%=selectedMenuNm%>&selected_menu_detail_id=<%=selectedMenuDetailId%>&set_init_url=<%=set_init_url %>" name="leftFrame" scrolling="no" noresize="noresize">
  <frame src="<%=request.getContextPath() %>/secfw/main.do?method=forwardBodyPage" name="bodyFrame">
</frameset>                                                                                                                         
<noframes>                                                                                                                          
<body>                                                                                                                              
</body>                                                                                                                             
</noframes>                                                                                                                         
</html>                                                                                                                             