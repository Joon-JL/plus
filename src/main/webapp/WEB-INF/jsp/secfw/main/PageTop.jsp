<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>

<html xmlns='http://www.w3.org/1999/xhtml'>
<head>
<title>Page Top Sample</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<c:url value='/style/secfw/common.css' />" rel='stylesheet' type='text/css'>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/ui.core.js' />"></script>
<script language="javascript" src="<c:url value='/script/secfw/jquery/jquery.form.js' />"></script>
<script language='javascript' src="<c:url value='/script/secfw/common/common.js' />"></script>
<script language="javascript">
</script>
</head>
<body leftmargin='0' topmargin='0'>
<LINK href="/style/project/top.css" type="text/css" rel="stylesheet">
<div class="header">
  <!-- Logo -->
  <h1><a href="../../index.html"  title="my Coach" target="_top"><img src="../../images/common/sub_logo.gif" alt="myCoach"></a></h1>
  <!-- //Logo -->
  <!-- Search -->
  <fieldset class="search">
  <legend>
  <input name="" type="text" value="Search" class="search_text"><input name="" type="image" src="../../images/btn/bt_all_search.gif" class="search_bt">
  </legend>
  </fieldset>
  <!-- //Search -->
  <!-- Gnb -->
  <div class="gnb">
    <ul>
      <li><a href="../ulearning/ulearning.html" target="_top" class="thub">u-Learning</a></li>
      <li><a href="../education/education.html" target="_top" class="thub">Education</a></li>
      <li><a href="../intern/intern.html" target="_top" class="thub">Intern</a></li>
	  <li><a href="../freshmen/freshmen.html" target="_top" class="thub">Freshmen</a></li>
	  <li><a href="../competency/competency.html" target="_top" class="thub">Competency</a></li>     
	  <li onMouseOver="menu_1.style.display='block';" class="selected_thub"><a href="../thub/thub.html" target="_top">T-Hub</a></li>
      <li><a href="../hrq/hrq.html" target="_top" class="thub">HR-Q</a></li>
      <li class="non"><a href="../gple/gple.html" target="_top" class="thub">GPLE</a></li>
    </ul>
  </div>
  <!--//Gnb -->
</div>
<!-- Menu Layer -->
<div style="position:absolute;top:90px;left:694px; display:none;" id="menu_1" class="m_layer_thub">
  <ul class="m_layer thub_line">
    <li><a href="#">Main title 1</a></li>
    <li><a href="#">Main title 2</a></li>
    <li class="non"><a href="#">Main title 3</a></li>
  </ul>
</div>
<!-- //Menu Layer -->
</body>
</html>
