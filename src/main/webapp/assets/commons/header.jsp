<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://cdn.tailwindcss.com"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/style.css">
<title>ENI Auctions</title>
</head>

<header>
	<%@ page import="java.util.Objects"%>
	<%@ page import="jakarta.servlet.http.HttpSession"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<c:set var="username" value="${sessionScope.username}" />
	<div
		class="bg-gray-800 text-white flex items-center w-full justify-between">
		<div class="flex items-center h-full">
			<a class="flex items-center"
				href="${pageContext.request.contextPath}"> <img
				class="size-16 ml-10"
				src="${pageContext.request.contextPath}/assets/images/JFAsansFond.png" />
				<span class="text-2xl font-bold ml-6">ENI Auctions</span>
			</a>
		</div>

		<div>
			<c:if test="${!empty username}">
			<p> - Hi, ${username} ! -</p>
			</c:if>
		</div>
	
		<div class="text-sm justify-center mr-10">

			<c:choose>
				<c:when test="${!empty username}">

					<a class="m-5 hover:text-cyan-400"
						href="${pageContext.request.contextPath}">Auctions</a>
					<a class="m-5 hover:text-cyan-400"
						href="${pageContext.request.contextPath}/newSale">New Sale </a>
					<a class="m-5 hover:text-cyan-400"
						href="${pageContext.request.contextPath}/user/mySales">My
						Sales</a>
					<a class="m-5 hover:text-cyan-400"
						href="${pageContext.request.contextPath}/user/detail?userId=${sessionScope.userId}">My
						Profile</a>
					<a class="m-5 hover:text-cyan-400"
						href="${pageContext.request.contextPath}/user/logout">Disconnect</a>

				</c:when>
				<c:otherwise>
					<a class="m-5 hover:text-cyan-400"
						href="${pageContext.request.contextPath}/user/login">Login</a>
					<a class="m-5 hover:text-cyan-400"
						href="${pageContext.request.contextPath}/user/create-account">Sign
						in</a>
				</c:otherwise>
			</c:choose>
		</div>

	</div>
</header>