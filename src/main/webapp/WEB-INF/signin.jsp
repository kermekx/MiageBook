<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Sign in - MiageBook</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>

<body>
	<div class="container">
		<h1>Sign in</h1>
		<form method="post" action="signin">
			<div class="form-group">
				<label for="username">Username</label> <input type="text"
					class="form-control ${empty form.erreurs['username'] ? 'is-valid' : 'is-invalid'}"
					id="username"
					placeholder="Enter username" name="username" value="${empty form.username ? '' : form.username}">
				<div class="invalid-feedback" id="usernameInvalidFeedback">${form.erreurs['username']}</div>
			</div>
			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					class="form-control ${empty form.erreurs['password'] ? 'is-valid' : 'is-invalid'}"
					id="password" placeholder="Password" name="password" value="${empty form.password ? '' : form.password}">

				<div class="invalid-feedback">${form.erreurs['password']}</div>
			</div>
			<div class="form-group">
				<label for="confirmPassword">Confirm Password</label> <input
					type="password"
					class="form-control ${empty form.erreurs['confirmPassword'] ? 'is-valid' : 'is-invalid'}"
					id="confirmPassword" placeholder="Confirm Password"
					name="confirmPassword" value="${empty form.confirmPassword ? '' : form.confirmPassword}">

				<div class="invalid-feedback">${form.erreurs['confirmPassword']}</div>
			</div>
			<div class="form-group">
				<label for="mail">Mail</label> <input type="email"
					class="form-control ${empty form.erreurs['mail'] ? 'is-valid' : 'is-invalid'}"
					id="mail" aria-describedby="mailHelp" placeholder="Enter email"
					name="mail" value="${empty form.mail ? '' : form.mail}"> <small id="mailHelp"
					class="form-text text-muted">Nous ne partagerons jamais votre adresse mail.</small>
				<div class="invalid-feedback" id="mailInvalidFeedback">${form.erreurs['mail']}</div>
			</div>
			<div class="form-group">
				<label for="firstname">Firstname</label> <input type="text"
					class="form-control ${empty form.erreurs['firstname'] ? 'is-valid' : 'is-invalid'}"
					id="firstname" placeholder="Enter firstname" name="firstname" value="${empty form.firstname ? '' : form.firstname}">
				<div class="invalid-feedback">${form.erreurs['firstname']}</div>
			</div>
			<div class="form-group">
				<label for="lastname">Lastname</label> <input type="text"
					class="form-control ${empty form.erreurs['lastname'] ? 'is-valid' : 'is-invalid'}"
					id="lastname" placeholder="Enter lastname" name="lastname" value="${empty form.lastname ? '' : form.lastname}">
				<div class="invalid-feedback">${form.erreurs['lastname']}</div>
			</div>
			<button type="submit" class="btn btn-primary">Sign in</button>

			<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
		</form>
		<a href="./login">Vous avez déjà un compte?</a>
	</div>

	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
		
		
		
	<script type="text/javascript">
		console.log("ready");
		$(document).ready(function(){
		    $("#username").change(function() {
		    	console.log("change");
		        var usernameValue = $(this).val(); // this.value
		        $.ajax({
                    url : "./checkUsername",
                    type: 'GET',
                    data : {
                        username : usernameValue
                    },
                    success : function(results){
                        if(results != null && results=!= "AVAILABLE"){
                            $('#username').removeClass("is-invalid").addClass("is-valid");
                        }else{
                            $('#username').removeClass("is-valid").addClass("is-invalid");
                            $('#usernameInvalidFeedback').text("This username is already used");
                        }
                    }
                });
		    });
		    
		    $("#mail").change(function() {
		    	console.log("change");
		        var mailValue = $(this).val(); // this.value
		        $.ajax({
                    url : "./checkMail",
                    type: 'GET',
                    data : {
                        mail : mailValue
                    },
                    success : function(results){
                        if(results != null && results=!= "AVAILABLE"){
                            $('#mail').removeClass("is-invalid").addClass("is-valid");
                        }else{
                            $('#mail').removeClass("is-valid").addClass("is-invalid");
                            $('#mailInvalidFeedback').text("This mail is already used");
                        }
                    }
                });
		    });
		}); 
	</script>
</body>
</html>
