<%@ page import="java.time.LocalDate"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%!DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");%>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ENI Auctions - Create Account</title>
<link
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/style.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/disabledWithdrawal.js"></script>
</head>



<%@ include file="../assets/commons/header.jsp"%>

<body class="bg-green-300">
	<div class="pt-20">
		<h1 class="text-center align-middle">New Sale</h1>
	</div>

	<form action="${pageContext.request.contextPath}/newSale" method="POST">
		<div class="flex items-center justify-center h-4/5">
			<div class="bg-green-700 text-white p-8 rounded shadow-md w-3/10">

				<div>
					<label for="article">Article :</label> <input
						class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4"
						type="text" name="article" id="article">
				</div>
				<div>
					<label class="flex-initial" for="description">Description :</label>
					<textarea
						class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4"
						id="champ_description" name="champ_description"
						placeholder="Description of the article" required></textarea>
				</div>

				<div>
					<label class="flex-initial" for="categorie">Category</label> <select
						class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4"
						name="category" id="category">
						<option value="default" selected></option>
						<option value="multimedia">Multimedia</option>
						<option value="home">Home</option>
						<option value="fashion">Fashion</option>
						<option value="leisure">Leisure</option>
					</select>
				</div>

				<div>
					<label for="photo"> Picture of the item </label> <input
						class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4"
						type="file" id="photo" accept="image/*">
				</div>

				<div>
					<label for="price"> Pricing </label> <input
						class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4"
						type="number" id="price" name="price" min="1" step="1">
				</div>

				<div>
					<label for="startDate"> Beginning of the auction </label> <input
						class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4"
						type="date" name="startDate" id="startDate"
						min="<%=LocalDate.now().format(formatter)%>"
						value="<%=LocalDate.now().format(formatter)%>" required>
				</div>

				<div>
					<label for="endDate"> End of the auction</label> <input
						class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4"
						type="date" name="endDate" id="endDate"
						min="<%=LocalDate.now().format(formatter)%>" max="" required>
					<!-- ATTENTION : faire en sorte que la date max = date début enchere + 30j-->
				</div>

				<fieldset class="border border-slate-600 rounded p-2">
					<legend>Withdrawal</legend>
					<div>
						<label for="defaultAddress" class="flex-initial">Use an other adress than mine</label>
						<input type="checkbox" name="defaultAddress" id="defaultAddress"
							onchange="toggleInputs()">
					</div>
					<div id="withdrawal">
						<div>
							<label for="street">Street*:</label> <input
								class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4"
								type="text" id="street" name="street" disabled>
						</div>
						<div>
							<label for="postalCode">Postal Code*:</label> <input
								class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4"
								type="text" id="postalCode" name="postalCode" disabled>
						</div>
						<div>
							<label for="city">City*:</label> <input
								class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4"
								type="text" id="city" name="city" disabled>
						</div>
					</div>
				</fieldset>
				<div>
					<button class="w-full bg-green-500 text-white p-2 my-5 rounded"
						type="submit" name="save">Create a new Sale</button>
					<button type="button"
						class="w-full bg-blue-500 text-white p-2 rounded"
						onclick="window.location.href='index'">Cancel</button>
				</div>
			</div>
		</div>
	</form>

</body>
<%@ include file="../assets/commons/footer.jsp"%>
</html>