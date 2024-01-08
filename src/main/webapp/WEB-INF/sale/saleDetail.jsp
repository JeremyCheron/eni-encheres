<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ENI Auctions - Sale detail</title>
<link
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/assets/css/style.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/disabledElements.js">
	
</script>

</head>


<body>


	<%@ include file="../../assets/commons/header.jsp"%>

	<form action="${pageContext.request.contextPath}/newSale" method="POST"
		class="h-3/4 flex items-center justify-center mb-10 p-24">
		<div
			class="bg-gray-800 text-white p-8 rounded shadow-md w-3/10 mb-10 ">
			<div>
				<form class="bg-gray-800">
					<div class="flex">
						<form action="${pageContext.request.contextPath}/user/mySales"
							method="GET">
							<div class="m-10 flex items-center">
								<div>
									<div>
										<label for="filter" class="block text-lg font-bold text-white">CONTAIN
										</label>
									</div>

									<div class="m-4">
										<label class="flex-initial" for="description">Description
											:</label>
									</div>

									<div class="m-4">
										<label class="flex-initial" for="category">Category :</label>
									</div>

									<div class="m-4">
										<label class="flex-initial" for="Best offer">Best
											offer :</label>
									</div>

									<div class="m-4">
										<label class="flex-initial" for="Bounty">Bounty :</label>
									</div>


									<div class="m-4">
										<label class="flex-initial" for="End of auction">End
											of auction :</label>
									</div>

									<div class="m-4">
										<label class="flex-initial" for="Withdrawal">Withdrawal
											:</label>
									</div>

									<div class="m-4">
										<label class="flex-initial" for="Seller">Seller :</label>
									</div>

									<div class="m-4 flex items-center justify-center">
										<label for="My proposal">My proposal :</label> <input
											class="text-black text-right w-16 mx-2 border-gray-500"
											id="ticketNum" type="number" name="ticketNum" value="0" />

										<div class="flex mx-2 w-40">
											<button type="submit"
												class="w-full bg-green-500 hover:bg-green-700 text-grey-800 p-2 rounded">Bid</button>
										</div>
									</div>
								</div>
							</div>
					</div>
				</form>
			</div>

			<%@ include file="../../assets/commons/footer.jsp"%>
</body>


</html>