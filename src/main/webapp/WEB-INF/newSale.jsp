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
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
   	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/style.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/disabledWithdrawal.js"></script>
</head>



<%@ include file="../assets/commons/header.jsp"%>

<body class="bg-green-300">
	<h1>New Sale</h1>

	<div class="flex items-center justify-center h-screen">


		<form action="${pageContext.request.contextPath}/auctions"
			method="POST">
			<!-- ATTENTION : mettre la bonne servlet-->

			<div>
				<label for="article">Article :</label> <input type="text">
			</div>
			<div class="flex">
				<label class="flex-initial" for="description">Description :</label>
				<textarea id="champ_description" name="champ_description"
					placeholder="Description de l'article" required></textarea>
			</div>

			<div>
				<label class="flex-initial" for="categorie">Category</label> <select
					name="categorie" id="categorie">
					<option value="default" selected></option>
					<option value="informatique">Informatique</option>
					<option value="ameublement">Furniture</option>
					<option value="petit-electromenager">Small appliance</option>
					<option value="gros-electromenager">large household
						appliances</option>
					<option value="jeux">Games</option>
					<option value="Livres">Books</option>
				</select>
			</div>

			<div>
				<label for="photo"> Picture of the item </label> <input type="file"
					id="photo" accept="image/*">
			</div>

			<div>
				<label for="prix"> Pricing </label> <input type="number" id="prix"
					name="prix" min="1" step="1">
			</div>

			<div>
				<label for="debut-enchere"> Beginning of the auction </label> <input
					type="date" name="debut-enchere" id="debut-enchere"
					min="<%=LocalDate.now().format(formatter)%>"
					value="<%=LocalDate.now().format(formatter)%>" required>
			</div>

			<div>
				<label for="fin-enchere"> End of the auction</label> <input
					type="date" name="fin-enchere" id="fin-enchere"
					min="<%=LocalDate.now().format(formatter)%>" max="" required>
				<!-- ATTENTION : faire en sorte que la date max = date début enchere + 30j-->
			</div>

			<fieldset>
				<legend>Withdrawal</legend>
				<div>
					<label for="defaultAddress">Use an other adress than mine</label> <input type="checkbox" name="defaultAddress" id="defaultAddress" onchange="toggleInputs()">
				</div>
				<div id="withdrawal">
					<div>
						<label for="street">Street*:</label> <input type="text" id="street"
							name="street" disabled>
					</div>
					<div>
						<label for="postalCode">Postal Code*:</label> <input type="text"
							id="postalCode" name="postalCode" disabled>
					</div>
					<div>
						<label for="city">City*:</label> <input type="text" id="city"
							name="city" disabled>
					</div>
				</div>
			</fieldset>

			<div>
				<button class="w-full bg-green-500 text-white p-2 rounded"
					type="submit" name="save">Save</button>
				<button type="button"
					class="w-full bg-blue-500 text-white p-2 rounded"
					onclick="window.location.href='auctions'">Cancel</button>
			</div>
		</form>
	</div>
</body>
<%@ include file="../assets/commons/footer.jsp"%>
</html>