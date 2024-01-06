<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
</head>


<body class="bg-green-300">
	
	<%@ include file="../../assets/commons/header.jsp"%>
		
		<div>
		<h1 class="text-center align-middle">My sales</h1>
		</div>
		
		
		<form action="${pageContext.request.contextPath}/user/mySales" method="GET">
			<div class="flex items-center justify-left pl-10 h-4/5 w-3/10" >
			<div >
				<label class="relative block" for="filter">Filters :
				<input class="placeholder:italic placeholder:text-slate-400 block bg-white w-full border border-slate-300 rounded-md py-2 pl-2 pr-3 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1 sm:text-sm" placeholder="&#128269; Search for anything..." type="text" name="search"/>
				</label>
			</div>
			</div>
		</form>
		

<%@ include file="../../assets/commons/footer.jsp"%>

</body>


</html>