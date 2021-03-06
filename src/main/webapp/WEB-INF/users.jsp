<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Users - MiageBook</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <a class="navbar-brand" href="#">MiageBook</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    <ul class="navbar-nav">
	      <li class="nav-item">
	        <a class="nav-link" href="./profile">Profile</a>
	      </li>
	      <li class="nav-item active">
	        <a class="nav-link" href="#">Users <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="./logout">Déconnexion</a>
	      </li>
	    </ul>
	  </div>
	</nav>
	
	<div class="container">
		
		<h1>Liste des utilisateurs</h1>
		<c:forEach items="${users}" var="user">
		    <c:choose>
			    <c:when test="${sessionScope.userSession.isFriend(user)}">
			        <form action="./users" method="post" class="form-inline pt-1">
			          <label class="mr-sm-2" for="username">${user.username}</label>
					  <input type="hidden" name="username" value="${user.username}">
					  <input type="hidden" name="add" value="false">
					  <button type="submit" class="btn btn-primary btn-sm">Remove friend</button>
					</form>
			    </c:when>    
			    <c:otherwise>
				    <form action="./users" method="post" class="form-inline pt-1">
				    <label class="mr-sm-2" for="username">${user.username}</label>
					  <input type="hidden" name="username" value="${user.username}">
					  <input type="hidden" name="add" value="true">
					  <button type="submit" class="btn btn-primary btn-sm">Add friend</button>
					</form>
			    </c:otherwise>
			</c:choose>
		</c:forEach>
	</div>

	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
