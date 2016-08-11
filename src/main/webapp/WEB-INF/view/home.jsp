<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spittr</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />"/>
</head>
<body>
	<h1><s:message code="spittr.welcome" /></h1>
	<a href="<c:url value="/spittles" />"><s:message code="spittr.spittles" /></a>
	<a href="<c:url value="/spitter/register" />"><s:message code="spittr.register" /></a>
</body>
</html>