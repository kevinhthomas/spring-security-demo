<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to the home page</title>
</head>
<body>

	<h2>HOME</h2>
	
	<security:authorize access="hasRole('MANAGER')">
		<a href="${pageContext.request.contextPath}/manager">Manager Page</a>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<a href="${pageContext.request.contextPath}/admin">Admin Page</a>
	</security:authorize>
	
	<security:authorize access="isAuthenticated()">
	  	<p>
			User: <security:authentication property="principal.username" />
			<br/>
			Role(s): <security:authentication property="principal.authorities" />
		</p>
	
		<hr>
		<form:form action="${pageContext.request.contextPath}/logout" method="POST">
			<input type="submit" value="Logout" />
		</form:form>
	</security:authorize>
	
	<security:authorize access="!isAuthenticated()">
		<a href="${pageContext.request.contextPath}/login">Login</a>
	</security:authorize>
	
</body>
</html>