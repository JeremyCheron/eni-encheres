<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">

<head>
<meta charset="UTF-8">
<meta name="description" content="Formulaire">
<link rel="stylesheet" href="">
<title>Mon profil</title>
</head>
<body>
	<h1>Mon profil</h1>

	<fieldset>
		<legend>Informations</legend>
		<form action="" method="POST">

			<div>
				<label for="nom">Nom*:</label> 
				<input type text="text" id="nom"
					name="nom" autofocus required pattern="[A-Za-z]{3,}"
					placeholder="Saisissez votre Nom">
			</div>
			<div>
				<label for="prenom">Prénom :</label> 
				<input type="text" id="prenom"
					name="Prénom" pattern="[A-Za-z]{3,}"
					placeholder="Saisissez votre Prénom">
			</div>
			<div>
				<label for="pseudo">Pseudo :</label> 
				<input type="text" name="pseudo" id="pseudo" pattern="[A-Za-z]{3,}"
				placeholder="Saisissez votre Pseudo">
			</div>
			<div>
				<label for="email">Email :</label> 
				<input type="email" name="email" id="email"
				placeholder="Saisissez votre Email">
			</div>
			<div>
				<label for="telephone">Téléphone :</label>
				<input type="tel" name="tel" id="tel"
				placeholder="Saisissez votre numéro de téléphone">
			</div>
			<div>
				<label for="rue">Rue :</label>
				<input type="text" name="rue" id="rue"
				placeholder="Saisissez votre rue">
			</div>
			<div>
				<label for="ville">Ville :</label>
				<input type="text" name="ville" id="ville"
				pattern="[A-Za-z]{3,}"
				placeholder="Saisissez votre ville">
			</div>
			<div>
				<label for="Code postal">Code postal :</label>
				<input type="text" name="code postal" id="code postal"
				placeholder="Saisissez votre code postal">
			</div>
			<div>
				<label for="nouveau mot de passe">Nouveau mot de passe :</label>
				<input type="password" name="nouveau mot de passe" id="nouveau mot de passe"
				pattern="[A-Za-z]{3,}"
				placeholder="Saisissez votre mot de passe">
			</div>
			<div>
				<label for="confirmation">Confirmation :</label>
				<input type="password" name="confirmation" id="confirmation"
				pattern="[A-Za-z]{3,}"
				placeholder="Confirmez votre mot de passe">
			</div>
			<div>
				<button type="creer">"Créer "</button>
			</div>
			<div>
				<button type="annuler creation">"Annuler création "</button>
			</div>
			</div>
		</form>
	</fieldset>
</body>
</html>