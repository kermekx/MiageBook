<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Login - MiageBook</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>

<body>
	<div class="container">
		<form method="post" action="signin">
			<div class="form-group">
				<label for="username">Username</label> <input type="text"
					class="form-control ${empty form.erreurs['username'] ? 'is-valid' : 'is-invalid'}"
					id="username" aria-describedby="userameHelp"
					placeholder="Enter username" name="username"> <small
					id="userameHelp" class="form-text text-muted">We'll never
					share your username with anyone else.</small>
				<div class="invalid-feedback">${form.erreurs['username']}</div>
			</div>
			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					class="form-control ${empty form.erreurs['password'] ? 'is-valid' : 'is-invalid'}"
					id="password" placeholder="Password" name="password">

				<div class="invalid-feedback">${form.erreurs['password']}</div>
			</div>
			<div class="form-group">
				<label for="confirmPassword">Confirm Password</label> <input
					type="password"
					class="form-control ${empty form.erreurs['confirmPassword'] ? 'is-valid' : 'is-invalid'}"
					id="confirmPassword" placeholder=Confirm Password
					name="confirmPassword">

				<div class="invalid-feedback">${form.erreurs['confirmPassword']}</div>
			</div>
			<div class="form-group">
				<label for="mail">Mail</label> <input type="email"
					class="form-control ${empty form.erreurs['mail'] ? 'is-valid' : 'is-invalid'}"
					id="mail" aria-describedby="mailHelp" placeholder="Enter email"
					name="mail"> <small id="mailHelp"
					class="form-text text-muted">We'll never share your mail
					with anyone else.</small>
				<div class="invalid-feedback">${form.erreurs['mail']}</div>
			</div>
			<div class="form-group">
				<label for="firstname">Firstname</label> <input type="text"
					class="form-control ${empty form.erreurs['firstname'] ? 'is-valid' : 'is-invalid'}"
					id="firstname" placeholder="Enter firstname" name="firstname">
				<div class="invalid-feedback">${form.erreurs['firstname']}</div>
			</div>
			<div class="form-group">
				<label for="lastname">Lastname</label> <input type="text"
					class="form-control ${empty form.erreurs['lastname'] ? 'is-valid' : 'is-invalid'}"
					id="username" placeholder="Enter lastname" name="lastname">
				<div class="invalid-feedback">${form.erreurs['lastname']}</div>
			</div>
			<button type="submit" class="btn btn-primary">Sign in</button>

			<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
		</form>
	</div>

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
