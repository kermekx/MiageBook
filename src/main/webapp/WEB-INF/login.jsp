<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Login - MiageBook</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>

<body>
	<div class="container">
		<h1>Login</h1>
		<form method="post" action="login">
			<div class="form-group">
				<label for="username">Username</label> <input type="text"
					class="form-control ${empty form.erreurs['username'] ? 'is-valid' : 'is-invalid'}"
					id="username" aria-describedby="userameHelp"
					placeholder="Enter username" name="username"> <small id="userameHelp"
					class="form-text text-muted">We'll never share your
					username with anyone else.</small>
				<div class="invalid-feedback">${form.erreurs['username']}</div>
			</div>
			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					class="form-control ${empty form.erreurs['password'] ? 'is-valid' : 'is-invalid'}"
					id="password" placeholder="Password" name="password">

				<div class="invalid-feedback">${form.erreurs['password']}</div>
			</div>
			<button type="submit" class="btn btn-primary">Login</button>

			<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>

			<%-- Vérification de la présence d'un objet utilisateur en session --%>
			<c:if test="${!empty sessionScope.userSession}">
				<%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
				<p class="succes">Vous êtes connecté(e) avec le compte :
					${sessionScope.userSession.username}</p>
			</c:if>
		</form>
			<a href="signin">Pas encore de compte?</a>
	</div>

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
