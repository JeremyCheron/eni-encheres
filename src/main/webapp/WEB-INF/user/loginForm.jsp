<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Objects" %>
<%@ page import="java.util.Optional" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/style.css">
    <title>ENI Auctions - Log In</title>
</head>

<%@ include file="../../assets/commons/header.jsp" %>

<body>

    <div class="flex items-center justify-center h-screen my-10">
    
		<% String loginError = Objects.toString(request.getAttribute("loginError"), ""); %>
		<% if (!loginError.isEmpty()) { %>
		    <div class="bg-red-500 text-white font-bold rounded-md p-4 mb-4">
		        <%= loginError %>
		    </div>
		<% } %>
    
        <div class="bg-green-700 text-white p-8 rounded shadow-md w-96">
        
        <img alt="Logo JFA" src="${pageContext.request.contextPath}/assets/images/logoJFA.png">
        
            <h1 class="text-2xl font-bold mb-6">Login</h1>

            <form ACTION="${pageContext.request.contextPath}/user/login" METHOD="post" class="">
                <label class="block text-gray-200 mb-2">Username:</label>
                <input type="text" class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4" name="username" required>

                <label class="block text-gray-200 mb-2">Password:</label>
                <input type="password" class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4" name="password" required>

                <button type="submit" class="w-full bg-green-500 text-white p-2 rounded">Log In</button>
            </form>

            <div class="flex justify-between mt-4">
                <div>
                    <input type="checkbox" id="remember" class="mr-2">
                    <label for="remember" class="text-gray-200">Remember Me</label>
                </div>
                <a href="#" class="text-blue-200">Forgot password ?</a>
            </div>

            <div class="mt-4">
                <button type="button" class="w-full bg-blue-500 text-white p-2 rounded" onclick="window.location.href='user/create-account'">Sign In</button>
            </div>
        </div>
    </div>
    
<%@ include file="../../assets/commons/footer.jsp" %>

</body>
</html>