<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
	integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
	crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RedeSocial</title>
<link rel="StyleSheet" type="text/css" href="css/Style.css"
	media="screen">
</head>

<body>
	<div class="verde-1 menu">
		<h1 align="right">Yorkut</h1>
		<h6>${idUsuario}</h6>
		<form action="Pesquisasvl" method="get">
			<input type="text" name="nomepesquisa">
			<button type="submit">
				<i class="fas fa-search"></i>
			</button>
		</form>
	</div>
	<div class="content">
		<div class="col-small verde-3">
			<h2>Opções</h2>
			<ul>
				<li>Item 1</li>
				<li>Item 2</li>
				<li>Item 3</li>
			</ul>
		</div>
			<div class="verde-1">
			<ul>
				<c:forEach items="${listaUsuarios}" var="resultado">
					<li><c:out value="${resultado.getNomeCompleto()}" /></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>