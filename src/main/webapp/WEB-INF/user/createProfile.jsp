
    <script src="${pageContext.request.contextPath}/assets/js/confirmPassword.js"></script>


<body class="items-center justify-center">

<%@ include file="../../assets/commons/header.jsp" %>


	<div class="container mx-auto my-auto h-auto flex items-center justify-center p-24">
        <div class="bg-gray-800 text-white p-8 rounded shadow-md w-full md:w-96">
               <%--  <img alt="Logo JFA" src="${pageContext.request.contextPath}/assets/images/logoJFA.png" class="mx-auto mb-6"> --%>
        
            <h1 class="text-2xl font-bold">Sign In</h1>

			<form action="${pageContext.request.contextPath}/user/create-account" id="registrationForm" method="post" onsubmit="return validatePassword()">
                <div class="grid grid-cols-1 md:grid-cols-2 gap-4 h-3/4 " >
                    <div>
                        <label class="block text-gray-200 mb-2">Username:</label>
                        <input type="text" class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4" name="username" required>

                        <label class="block text-gray-200 mb-2">FirstName:</label>
                        <input type="text" class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4" name="firstname" required>

                        <label class="block text-gray-200 mb-2">Phone:</label>
                        <input type="tel" class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4" name="phone" required>

                        <label class="block text-gray-200 mb-2">PostCode:</label>
                        <input type="text" class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4" name="postcode" required>

                        <label class="block text-gray-200 mb-2">Password:</label>
                        <input type="password" class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4" name="password" id="password" required>
                    </div>

                    <div>
                        <label class="block text-gray-200 mb-2">Lastname:</label>
                        <input type="text" class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4" name="lastname" required>

                        <label class="block text-gray-200 mb-2">Email:</label>
                        <input type="email" class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4" name="email" required>

                        <label class="block text-gray-200 mb-2">Street:</label>
                        <input type="text" class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4" name="street" required>

                        <label class="block text-gray-200 mb-2">City:</label>
                        <input type="text" class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4" name="city" required>

                        <label class="block text-gray-200 mb-2">Confirm Password:</label>
                        <input type="password" class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4" name="confirmPassword" id="confirmPassword" required>
                    </div>
                </div>

                <div class="flex justify-between mt-6">
                    <button type="submit" class="bg-green-500 text-white p-2 rounded">Sign In</button>
                    <button type="button" class="bg-red-500 text-white p-2 rounded" onclick="window.location.href='${pageContext.request.contextPath}'">Cancel</button>
                </div>
            </form>
        </div>
    </div>
    
<%@ include file="../../assets/commons/footer.jsp" %>

</body>
</html>
