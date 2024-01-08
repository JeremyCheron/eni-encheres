<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Objects" %>
<%@ page import="java.util.Optional" %>

<%@ include file="/assets/commons/header.jsp" %>

<body>

<% String loginError = Objects.toString(request.getAttribute("loginError"), ""); %>
		<% if (!loginError.isEmpty()) { %>
		<div class="flex items-center justify-center"> 
		    <div class="bg-red-500 text-white font-bold rounded-md p-4 my-5 w-1/4 text-center">
		        <%= loginError %>
		        
		    </div>
		 </div>
		<% } %>
    <div class="flex items-center justify-center mt-12">
    
        <div class="bg-gray-800 text-white p-8 rounded shadow-md w-96">
        
        <img alt="Logo JFA" src="${pageContext.request.contextPath}/assets/images/JFAsansFond.png">
        
            <h1 class="text-2xl font-bold">Login</h1>

            <form ACTION="${pageContext.request.contextPath}/user/login" METHOD="post" class="max-h-96">
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
    
<%@ include file="../assets/commons/footer.jsp" %>

</body>
</html>