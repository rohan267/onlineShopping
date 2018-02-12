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
	<meta name="_csrf" content="${_csrf.token}">
	<meta name="_csrf_header" content="${_csrf.headerName}">
	
    <title>Online Shopping - ${title}</title>

	<script>
	window.menu='${title}';
	window.contextRoot='${contextRoot}';
	</script>

	<!-- Bootstrap debug CSS -->
<%-- 	<link href="${css}/debug.css" rel="stylesheet"> --%>
	<!-- Bootstrap debug CSS -->

<%-- 		<link href="${css}/bootstrap.min.css" rel="stylesheet"> --%>
<!-- 			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"> -->
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
		
		<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">
<%-- 			<link href="${css}/bootstrap-materia-theme.css" rel="stylesheet"> --%>
<!-- 			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootswatch/4.0.0-beta.3/materia/bootstrap.min.css"> -->
<!-- 			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootswatch/4.0.0-beta.3/superhero/bootstrap.min.css"> -->
		
		
<%-- 		<link href="${css}/dataTables.bootstrap.css" rel="stylesheet"> --%>
<%-- 		<link href="${css}/bootstrap-theme.css" rel="stylesheet"> --%>
<%-- 		<link href="${css}/bootstrap-materia-theme.css" rel="stylesheet"> --%>
	
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap4.min.css"/>

	<!-- Custom CSS -->
	<link href="${css}/myapp.css" rel="stylesheet">

  </head>

  <body>
<div class="wrapper">
    <!-- Navigation -->
	<%@ include file="./shared/navbar.jsp" %>
	
    <!-- Page Content -->
    <div class="content">
	    <c:if test="${userClickHome == true }">
	    		<%@ include file="home.jsp" %>
	    </c:if>
	
		<!-- About Us -->
		<c:if test="${userClickAbout == true }">
	    		<%@ include file="about.jsp" %>
	    </c:if>
	    
		<!-- Contact Us -->
	    <c:if test="${userClickContact == true }">
	    		<%@ include file="contact.jsp" %>
	    </c:if>
	    
	    <c:if test="${userClickAllProducts == true or userClickCategoryProducts == true }">
	    		<%@ include file="listProducts.jsp" %>
	    </c:if>
	    
	    <!-- product details -->
	    <c:if test="${userClickProductDetails==true }">
	    		<%@ include file="productDetails.jsp" %>
	    	</c:if>
	    	
	    	<!-- Manage products -->
	    	<c:if test="${userClickManageProducts==true }">
	    		<%@ include file="manageProducts.jsp" %>
	    	</c:if>
    </div>
    
    <!-- Footer -->
	<%@ include file="./shared/footer.jsp" %>
	
    <!-- Bootstrap core JavaScript -->
    <script src="${js}/jquery.js"></script>
    <script src="${js}/bootstrap.bundle.min.js"></script>
    <script src="${js}/jquery.dataTables.js"></script>
    <script src="${js}/dataTables.bootstrap.js"></script>
	<script src="${js}/bootbox.min.js"></script>
<!--     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script> -->
    <!-- Bootstrap bundle js -->
<!-- 	    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>	 -->
	<!-- Bootstrap bundle js -->    
<!--     		<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script> -->
<!--     		<script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap4.min.js"></script> -->
    <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/additional-methods.min.js"></script>
    <script src="${js}/myapp.js"></script>
</div>
  </body>

</html>