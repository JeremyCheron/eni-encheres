<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../assets/commons/header.jsp" %>

<body>


<div class="flex items-center justify-center mt-12">
    <c:choose>
    
    	<c:when test="${empty searchedUser}">
    		<div class="bg-gray-800 text-white p-8 rounded shadow-md w-96">
    			<h1 class="text-2xl font-bold text-center">User not found</h1>
    		</div>
    	</c:when>
    	<c:otherwise>
        <div class="bg-gray-800 text-white p-8 rounded shadow-md w-96">

			<h1 class="text-2xl font-bold text-center">${searchedUser.username}</h1>
			
			<div class="flex jutify-center">
			
				<label class="block text-gray-200 mb-2 w-48">Lastname:</label> 
				<span class="text-center w-48">${searchedUser.lastname}</span>
				
			</div>
			
			<div class="flex jutify-center">
			
				<label class="block text-gray-200 mb-2 w-48">Firstname:</label> 
				<span class="text-center w-48">${searchedUser.firstname}</span>
				
			</div>
			
			<div class="flex jutify-center">
			
				<label class="block text-gray-200 mb-2 w-48">Email:</label> 
				<span class="text-center w-48">${searchedUser.mail}</span>
				
			</div>
			
			<div class="flex jutify-center">
			
				<label class="block text-gray-200 mb-2 w-48">Phone:</label>
				<span class="text-center w-48">${searchedUser.phone}</span>
				
			</div>
			
			<div class="flex jutify-center">
			
				<label class="block text-gray-200 mb-2 w-48">Street:</label> 
				<span class="text-center w-48">${searchedUser.street}</span>
				
			</div>
			
			<div class="flex jutify-center">
			
				<label class="block text-gray-200 mb-2 w-48">PostCode:</label> 
				<span class="text-center w-48">${searchedUser.postCode}</span>
				
			</div>
			
			<div class="flex jutify-center">
			
				<label class="block text-gray-200 mb-2 w-48">City:</label> 
				<span class="text-center w-48">${searchedUser.city}</span>
				
			</div>
			
			<c:if test="${sessionScope.username eq searchedUser.username}">
    		
    		<div>
               <a href="${pageContext.request.contextPath}/user/create-account"> <button type="button" class="w-full bg-blue-500 text-white p-2 rounded">Update Profile</button></a>
    		</div>
    		<div>
				<h3 class="text-center text-2xl animate-pulse mt-5"> /!\ WARNING /!\ </h3>
				<a href="${pageContext.request.contextPath}/user/delete-account"> <button type="button" class="w-full bg-red-500 text-white mt-5 p-2 rounded">DELETE ACCOUNT</button></a>
    		</div>
    	
    		</c:if>

		</div>
		</c:otherwise>
	</c:choose>
</div>

<%@ include file="../../assets/commons/footer.jsp" %>

</body>
</html>