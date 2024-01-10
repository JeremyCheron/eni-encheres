<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../assets/commons/header.jsp" %>
<body>

	<c:if test="${!empty requestScope.newSale}">
			<div class="flex items-center justify-center">
				<div
					class="bg-blue-500 text-white font-bold rounded-md p-4 my-5 w-1/4 text-center">
					${newSale}</div>
			</div>
	</c:if>
	
<div class="flex items-center justify-center mt-12">

	
    <c:choose>
		<c:when test="${empty searchedArticle}">
	    		<div class="bg-gray-800 text-white p-8 rounded shadow-md w-96">
	    			<h1 class="text-2xl font-bold text-center">Article not found</h1>
	    		</div>
	   	</c:when>
	   	<c:otherwise>
	
			<div class="bg-gray-800 text-white p-8 rounded shadow-md w-96">
		
					<h1 class="text-2xl font-bold text-center mb-3">Sale Detail</h1>
					
					<div class="flex jutify-center mb-3">
					
						<h2 class="text-center text-xl">${searchedArticle.name}</h2>
						
					</div>
					
					<div class="flex jutify-center">
					
						<label class="block text-gray-200 mb-2 w-48">Description :</label> 
						<span class="text-center w-48">${searchedArticle.description}</span>
						
					</div>
					
					<div class="flex jutify-center">
					
						<label class="block text-gray-200 mb-2 w-48">Auction End Date :</label> 
						<span class="text-center w-48">${searchedArticle.endDate}</span>
						
					</div>
					
					<div class="flex jutify-center">
					
						<label class="block text-gray-200 mb-2 w-48">Starting Price :</label> 
						<span class="text-center w-48">${searchedArticle.startPrice}</span>
						
					</div>
					
					<div class="flex jutify-center">
					
						<label class="block text-gray-200 mb-2 w-48">Actual Best Bid :</label> 
						<span class="text-center w-48">${searchedArticle.finalPrice}</span>
						
					</div>
					
					<div class="flex jutify-center">
					
						<label class="block text-gray-200 mb-2 w-48">Seller :</label> 
						<span class="text-center w-48">${searchedArticle.sellerName}</span>
						
					</div>
					<c:choose>
						<c:when test="${searchedArticle.userId.toString() eq  sessionScope.userId.toString() }">
							<div class="flex items-center justify-center flex-wrap mt-3 w-full pb-3 px-3">
								<a class="w-full" href="${pageContext.request.contextPath}/newSale?articleId=${searchedArticle.articleId}"><button class="w-full bg-blue-500 text-white mb-3 rounded">Update Sale</button></a>
							</div>
						</c:when>
						<c:otherwise>
						<div class="flex items-center justify-center flex-wrap mt-3 w-full pb-3 px-3">
							<form action="${pageContext.request.contextPath}/sales/bid" method="post">
								<fieldset class="border border-solid border-gray-300 flex flex-wrap items-center justify-center">
									<legend>Want this Article ?</legend>
									
									<label class="block text-gray-200 w-30">Bid : </label> 
									<input type="number" name="bidAmount" min="${searchedArticle.finalPrice}" placeholder="${searchedArticle.finalPrice}" class="text-right w-30 m-5 text-black"/>
									<input type="hidden" name="articleId" value="${searchedArticle.articleId}" />
									<input type="submit" class="w-80 bg-blue-500 text-white mb-3 mx-3 rounded"value="Bid Now"/>
								</fieldset>
							</form>
							
							
						</div>
						</c:otherwise>
					</c:choose>
		
			</div>
		</c:otherwise>
	</c:choose>
</div>
		

<%@ include file="../../assets/commons/footer.jsp" %>

</body>

</html>