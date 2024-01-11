<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/disabledElements.js">
</script>


<body>

	<%@ include file="../../assets/commons/header.jsp"%>

	<div class="flex w-full items-center">
		<form action="${pageContext.request.contextPath}/sales/list"
			method="GET">
			<div class="m-10 w-full flex justify-center align-middle items-center">
				<div>
					<div>
						<label for="filter" class="block text-lg font-bold text-gray-900">Filters
							:</label>
						<div class="relative mt-2 rounded-md">
							<div
								class="pointer-events-none absolute inset-y-0 left-0 flex items-center pl-2">
								<span class="">&#128269;</span>
							</div>
							<input type="text" name="filter" id="filter"
								class="placeholder:italic placeholder:text-slate-400 block bg-white w-70 border border-gray-500 rounded-md py-1.5 pl-10 pr-20 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1 sm:text-sm"
								placeholder="Article name contains ..." type="text"
								name="search">
						</div>
					</div>

					<div class="">
						<label class="flex-initial" for="category">Category :</label> <select
							class="w-64 p-2 border border-gray-500 text-gray-800 rounded ml-5 my-5 shadow-sm focus:outline-none focus:border-sky-500 focus:ring-sky-500 focus:ring-1"
							name="category" id="category">
							<option value="" selected>all</option>
							<c:forEach var="current" items="${categories}">
								<option value="${current.getCategoryId()}">${current.getName()}</option>
							</c:forEach>
						</select>
					</div>
					
				</div>
				<div class="flex mx-40 w-60">
					<button type="submit"
						class="w-full h-28 bg-blue-500 hover:bg-blue-700 text-white p-2 rounded my-5">Search</button>
				</div>
			</div>
		</form>
	</div>

<div class="mx-10 mb-40">
    <ul>
        <c:choose>
            <c:when test="${empty searchResults}">
                <div class="flex flex-wrap justify-center">
				    <c:forEach var="current" items="${allSales}" varStatus="loopStatus">
				        <div class="flex items-center justify-center mt-12 mx-4">
				            <div class="bg-gray-800 text-white p-8 rounded shadow-md w-96">
                				<h1 class="text-2xl font-bold">${current.name}</h1>
                				<p>Starting Price : ${current.startPrice}</p>
								<p>Start Date : ${current.startDate}</p>
                				<p>End Date : ${current.endDate}</p>
                				<p>
                				Seller :
                				<c:choose>
                				<c:when test="${logged}">
                					<a class="hover:text-cyan-400" href="${pageContext.request.contextPath}/user/detail?userId=${current.userId}">${current.sellerName}</a>
								</c:when> 
								<c:otherwise>
                					${current.sellerName}
               					</c:otherwise>
                				</c:choose>
                				</p>
                				
				            </div>
				        </div>

				        <!-- Ajouter une nouvelle ligne après chaque troisième article -->
				        <c:if test="${loopStatus.index % 3 == 2}">
				            <div class="w-full"></div>
				        </c:if>
    				</c:forEach>
				</div>
            </c:when>
            <c:otherwise>
                <div class="flex flex-wrap justify-center">
				    <c:forEach var="current" items="${searchResults}" varStatus="loopStatus">
				        <div class="flex items-center justify-center mt-12 mx-4">
				            <div class="bg-gray-800 text-white p-8 rounded shadow-md w-96">
                				<h1 class="text-2xl font-bold">${current.name}</h1>
                				<p>Starting Price : ${current.startPrice}</p>
                				<p>Start Date : ${current.startDate}</p>
                				<p>End Date : ${current.endDate}</p>
                				<p>
                				Seller :
                				<c:choose>
                				<c:when test="${logged}">
                					<a class="hover:text-cyan-400" href="${pageContext.request.contextPath}/user/detail?userId=${current.userId}">${current.sellerName}</a>
								</c:when> 
								<c:otherwise>
                					${current.sellerName}
               					</c:otherwise>
                				</c:choose>
                				</p>
				            </div>
				        </div>

				        <!-- Ajouter une nouvelle ligne après chaque troisième article -->
				        <c:if test="${loopStatus.index % 3 == 2}">
				            <div class="w-full"></div>
				        </c:if>
    				</c:forEach>
				</div>
            </c:otherwise>
        </c:choose>
    </ul>
</div>


	<%@ include file="../../assets/commons/footer.jsp"%>

</body>


</html>