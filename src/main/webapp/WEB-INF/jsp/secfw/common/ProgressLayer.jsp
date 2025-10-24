<%@ taglib prefix="c"      uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<LINK href="<c:url value='/style/secfw/common.css' />" type="text/css" rel="stylesheet">
<p class="fL"><img src="<c:url value='/images/secfw/common/progress_bar.gif' />"></p>
<p class="text"><strong><spring:message code="secfw.page.field.common.isWorking"/></strong><br>
<spring:message code="secfw.page.field.common.plzWait"/>.</p>
<script language = "JavaScript">
	var nava = (document.layers);
	var dom = (document.getElementById);
	var iex = (document.all);
	if (nava) { cach = document.progressBar }
	else if (dom) { cach = document.getElementById("progressBar").style }
	else if (iex) { cach = progressBar.style }
	largeur = screen.width;
	cach.left = Math.round((largeur/2)-480);
	cach.visibility = "visible";

	function loadOff() {
		cach.visibility = "hidden";
	}

	function loadOn() {
		cach.visibility = "visible";
	}
	loadOn();
</script>