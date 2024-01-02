<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <title>Document</title>
</head>
<body class="bg-green-300">

    <div class="flex items-center justify-center h-screen">
        <div class="bg-green-700 text-white p-8 rounded shadow-md w-96">
            <h1 class="text-2xl font-bold mb-6">ENI Enchères</h1>

            <form>
                <label class="block text-gray-200 mb-2">Identifiant:</label>
                <input type="text" class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4" required>

                <label class="block text-gray-200 mb-2">Mot de Passe:</label>
                <input type="password" class="w-full p-2 border border-gray-500 text-gray-800 rounded mb-4" required>

                <button type="submit" class="w-full bg-green-500 text-white p-2 rounded">Connexion</button>
            </form>

            <div class="flex justify-between mt-4">
                <div>
                    <input type="checkbox" id="remember" class="mr-2">
                    <label for="remember" class="text-gray-200">Se souvenir de moi</label>
                </div>
                <a href="#" class="text-blue-200">Mot de passe oublié?</a>
            </div>

            <div class="mt-4">
                <button type="button" class="w-full bg-blue-500 text-white p-2 rounded">Créer un compte</button>
            </div>
        </div>
    </div>

</body>
</html>