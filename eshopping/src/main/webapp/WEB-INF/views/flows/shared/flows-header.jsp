<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url var="css" value="/resources/css"/>
<spring:url var="js" value="/resources/js"/>
<spring:url var="images" value="/resources/images"/>

<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Online Shopping - ${title}</title>

	<script>
	window.menu='${title}';
	window.contextRoot='${contextRoot}';
	</script>

	<!-- Bootstrap debug CSS -->
<%-- 	<link href="${css}/debug.css" rel="stylesheet"> --%>
	<!-- Bootstrap debug CSS -->

		<link href="${css}/bootstrap.min.css" rel="stylesheet">
		
		<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">
<%-- 			<link href="${css}/bootstrap-materia-theme.css" rel="stylesheet"> --%>
<!-- 			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootswatch/4.0.0-beta.3/materia/bootstrap.min.css"> -->
<!-- 			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootswatch/4.0.0-beta.3/superhero/bootstrap.min.css"> -->
		
		
<%-- 		<link href="${css}/dataTables.bootstrap.css" rel="stylesheet"> --%>
<%-- 		<link href="${css}/bootstrap-theme.css" rel="stylesheet"> --%>
<%-- 		<link href="${css}/bootstrap-materia-theme.css" rel="stylesheet"> --%>
	
	<!-- Latest compiled and minified CSS -->
<!-- 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"> -->
<!-- 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css"> -->
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap4.min.css"/>

	<!-- Custom CSS -->
	<link href="${css}/myapp.css" rel="stylesheet">

  </head>

  <body>
<div class="wrapper">
    <!-- Navigation -->
	<%@include file="flows-navbar.jsp" %>
    <!-- Page Content -->
    hiii
    <div class="content">