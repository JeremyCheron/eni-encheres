<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<body>
	<%@ include file="../../assets/commons/header.jsp"%>

	<div class="pt-20">
		<h1 class="text-center align-middle">Your Sale has been created
			successfully !</h1>

		<div class="text-center align-middle p-20">ANNONCE !!!</div>
	</div>
	<div class="flex items-center justify-center h-screen mt-10 mb-10">


		<div class="mt-4">
			<button type="button"
				class="w-full bg-green-500 hover:bg-green-700 text-white p-2 rounded"
				onclick="window.location.href='${pageContext.request.contextPath}/newSale'">Create
				a new sale</button>
			<button type="button"
				class="w-full bg-blue-500 hover:bg-blue-700 text-white p-2 rounded"
				onclick="window.location.href='${pageContext.request.contextPath}'">Go
				back to the home page</button>
		</div>
	</div>
	<%@ include file="../../assets/commons/footer.jsp"%>
</body>
</html>