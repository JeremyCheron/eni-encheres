<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">

<head>
<meta charset="UTF-8">
<meta name="description" content="Formulaire">
<link rel="stylesheet" href="">
<title>Nouvelle vente </title>
</head>
<body>
	<h1>Nouvelle vente</h1>

	<div>
		<label for="article">Article :</label>
		<input type="text">
	</div>
	<div>
		<label for="description">Description :</label>
		<input type="text">
	</div>
	<fieldset>
		<legend>Retrait</legend>
		<form action="" method="POST">
			<div>
				<label for="rue">Rue*:</label> 
				<input type="text" id="rue"
					name="nom" autofocus required pattern="[A-Za-z]{3,}"
					placeholder="Rue des mouettes">
			</div>
			<div>
				<label for="code postal">Code postal*:</label> 
				<input type="text" id="code postal"
					name="nom" autofocus required pattern="[A-Za-z]{3,}"
					placeholder="44800">
			</div>
			<div>
				<label for="ville">Ville*:</label> 
				<input type="text" id="ville"
					name="nom" autofocus required pattern="[A-Za-z]{3,}"
					placeholder="Saint Herblain" required>
			</div>
		</form>
	</fieldset>
			
		
			<div>
				<input type="submit" name="save" value="Save">
			</div>
			<div>
				<input type="button" value="Cancel">
			</div>
</body>
</html>