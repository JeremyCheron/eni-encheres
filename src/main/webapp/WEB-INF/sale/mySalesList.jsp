<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ENI Auctions - My Sales</title>
<link
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/style.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/disabledElements.js">
</script>

</head>


<body class="bg-green-300">

	<%@ include file="../../assets/commons/header.jsp"%>

	<div>
		<h1 class="text-center align-middle my-10">My sales</h1>
	</div>


	<form action="${pageContext.request.contextPath}/user/mySales"
		method="GET">
		<div class="ml-10">
			<label for="filter" class="block text-lg font-bold text-gray-900">Filters
				:</label>
			<div class="relative mt-2 rounded-md">
				<div
					class="pointer-events-none absolute inset-y-0 left-0 flex items-center pl-2">
					<span class="">&#128269;</span>
				</div>
				<input type="text" name="filter" id="filter"
					class="placeholder:italic placeholder:text-slate-400 block bg-white w-70 border border-gray-500 rounded-md py-1.5 pl-10 pr-20 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1 sm:text-sm"
					placeholder="Article name contains ..." type="text" name="search">
			</div>
		</div>

		<div class="ml-10">
			<label class="flex-initial" for="category">Category :</label> <select
				class="w-64 p-2 border border-gray-500 text-gray-800 rounded ml-5 my-5 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1"
				name="category" id="category">
				<option value="default" selected>all</option>
				<c:forEach var="current" items="${categories}">
					<option value="${current.getId()}">${current.getName()}</option>
				</c:forEach>
			</select>
		</div>

		<div class="ml-10 flex">
			<div>
				<label class="flex-initial" for="buyingsButton">Buyings</label> <input
					class="p-2 border border-gray-500 text-gray-800 rounded ml-5 mr-20"
					name="radio" id="buyingsButton" type="radio" value="buyingsButton" onchange="toggleBuyingsSales()">
				<div class="ml-10 disabledGroup" id="buyingCheckboxes">
					<div class="block">
						<input type="checkbox" name="openedBids" id="openedBids" disabled>
						<label class="flex-initial" for="openedBids">opened bids</label>
					</div>

					<div class="block">
						<input type="checkbox" name="myCurrentBids" id="myCurrentBids" disabled>
						<label class="flex-initial" for="myCurrentBids">my current
							bids</label>
					</div>

					<div class="block">
						<input type="checkbox" name="myWonBids" id="myWonBids" disabled> <label
							class="flex-initial" for="myWonBids">my won bids</label>
					</div>
				</div>

			</div>

			<div id="salesButton" class="ml-10">
				<label class="flex-initial" for="mySales">My sales</label> <input
					class="p-2 border border-gray-500 text-gray-800 rounded ml-5"
					name="radio" id="salesButton" type="radio" value="salesButton" checked onchange="toggleBuyingsSales()">
				<div class="ml-10 enableGroup" id="salesCheckboxes">
					<div class="block">
						<input type="checkbox" name="myCurrentSales" id="myCurrentSales">
						<label class="flex-initial" for="myCurrentSales">my
							current sales</label>
					</div>

					<div class="block">
						<input type="checkbox" name="nonBeginnedSales"
							id="nonBeginnedSales"> <label class="flex-initial"
							for="nonBeginnedSales">non beginned sales</label>
					</div>

					<div class="block">
						<input type="checkbox" name="endedSales" id="endedSales">
						<label class="flex-initial" for="endedSales">ended sales</label>
					</div>
				</div>
			</div>
		</div>

	</form>


	<%@ include file="../../assets/commons/footer.jsp"%>

</body>


</html>