<header>
<%@ page import="java.util.Objects" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
	<div class="bg-gray-800 text-white p-4">
	    <div class="container mx-auto flex justify-between items-center">
	        <h1 class="text-2xl font-bold">ENI Auctions</h1>
	        <div class="text-sm">
	            	<% String username = Objects.toString(session.getAttribute("username"), ""); %>
					<% if (!username.isEmpty()) { %>
					    <span>Bienvenue, <%= username %>! <a href="<%= request.getContextPath() %>/user/logout">Déconnexion</a></span>
					<% } %>
	        </div>
	    </div>
	</div>
</header>