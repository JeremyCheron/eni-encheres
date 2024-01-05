<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ENI Auctions - Sale created successfully</title>
<link
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<%@ include file="../../assets/commons/header.jsp"%>
<body class="bg-green-300">

	<div class="pt-20">
		<h1 class="text-center align-middle">Your Sale has been created
			successfully !</h1>

		<div class="text-center align-middle p-20">ANNONCE !!!</div>
	</div>
	<div class="flex items-center justify-center h-screen mt-10 mb-10">


		<div class="mt-4">
			<button type="button"
				class="w-full bg-green-500 hover:bg-green-700 text-white p-2 rounded"
				onclick="window.location.href='../newSale'">Create a new sale</button>
			<button type="button"
				class="w-full bg-blue-500 hover:bg-blue-700 text-white p-2 rounded"
				onclick="window.location.href='${pageContext.request.contextPath}'">Go back to the home
				page</button>
		</div>
	</div>
</body>
</html>