<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<script>
	var text = text || {}; // play well with other jsps on the page
	text.bbs_alert = {
		textarea_overbyte: "<spring:message code='las.msg.bbs.textareaMaxByte'/>",
	    system_error: "<spring:message code='las.page.field.alert.exceptionMessage'/>"
	};
</script>