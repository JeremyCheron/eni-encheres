<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Objects" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	connecté
	<% if (session != null) { %>
		Bonjour, <%= session.getAttribute("username") %> !
	<% } %>
	
	<% String username = Objects.toString(session.getAttribute("username"), ""); %>
<% if (!username.isEmpty()) { %>
    <p>Bienvenue, <%= username %>! <a href="<%= request.getContextPath() %>/user/logout">Déconnexion</a></p>
<% } %>
</body>
</html>