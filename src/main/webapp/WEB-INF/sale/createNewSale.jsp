<%@ page import="java.time.LocalDate"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%!DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");%>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/disabledElements.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/modifyDates.js"></script>



<body onload="changeEndDateAccordingToStartDate()">
<%@ include file="../../assets/commons/header.jsp"%>

		<h1 class="text-center align-middle text-xl my-10">New Sale</h1>

<!--enctype="multipart/form-data"  -->
	<form action="${pageContext.request.contextPath}/newSale" method="POST"   class="max-h-4/5 flex items-center justify-center mb-10 ">
			<div class="bg-gray-800 text-white p-8 rounded shadow-md w-2/5 mb-10">

				<div>
					<label for="article">Article* :</label> 
					<input
						class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1"
						type="text" name="article" id="article" required>
				</div>
				<div>
					<label class="flex-initial" for="description">Description* :</label>
					<textarea
						class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1"
						id="champ_description" name="champ_description"
						placeholder="Description of the article" required></textarea>
				</div>

				<div>
					<label class="flex-initial" for="category">Category</label>
					 <select
						class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1"
						name="category" id="category">
						<option value="default" selected></option>
							<c:forEach var="current" items="${categories}">
								<option value="${current.getCategoryId()}"> ${current.getName()} </option>
							</c:forEach>
					</select>
				</div>

				<div>
					<label for="photo"> Picture of the item </label> <input
						class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1"
						type="file" id="photo" accept="image/*">
				</div>

				<div>
					<label for="price"> Pricing </label> <input
						class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1"
						type="number" id="price" name="price" min="1" step="1">
				</div>

				<div>
					<label for="startDate"> Beginning of the auction </label> <input
						class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1"
						type="date" name="startDate" id="startDate"
						min="<%=LocalDate.now().format(formatter)%>"
						value="<%=LocalDate.now().format(formatter)%>" required
						onchange="changeEndDateAccordingToStartDate()">
				</div>

				<div>
					<label for="endDate"> End of the auction</label> 
					<input
						class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1"
						type="date" name="endDate" id="endDate"
						min="<%=LocalDate.now().format(formatter)%>" max="" value="<%=LocalDate.now().format(formatter)%>" required>
				</div>

				<fieldset class="border border-slate-600 rounded p-2">
					<legend>Withdrawal</legend>
					<div>
						<label for="defaultAddress" class="flex-initial">Use an other adress than mine</label>
						<input type="checkbox" value="newAdress" name="defaultAddress" id="defaultAddress"
							onchange="toggleInputs()">
					</div>
					<div id="withdrawal">
						<div>
							<label for="street">Street*:</label> <input
								class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1"
								type="text" id="street" name="street" disabled>
						</div>
						<div>
							<label for="postalCode">Postal Code*:</label> <input
								class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1"
								type="text" id="postalCode" name="postalCode" disabled>
						</div>
						<div>
							<label for="city">City*:</label> <input
								class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1"
								type="text" id="city" name="city" disabled>
						</div>
					</div>
				</fieldset>
				<div>
					<button class="w-full bg-green-500 text-white p-2 my-5 rounded"
						type="submit" name="save">Create a new Sale</button>
					<button type="button"
						class="w-full bg-blue-500 text-white p-2 rounded"
						onclick="window.location.href='${pageContext.request.contextPath}'">Cancel</button>
				</div>
			</div>
	</form>
<%@ include file="../../assets/commons/footer.jsp"%>

</body>
</html>