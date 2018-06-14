<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="StyleSheet" type="text/css" href="css/Style.css"
	media="screen">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
	integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
	crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RedeSocial</title>
</head>
<body>
	<div class="verde-1 menu">
		<p>Yorkut</p>
		<form align="right" action="Pesquisasvl" method="get">
			<input type="text" name="nomepesquisa">
			<button type="submit">
				<i class="fas fa-search"></i>
			</button>
		</form>
	</div>
	<div>
		<form align="center">
			<a href="ConsultaPublicsvl"><i class="fas fa-plus"></i>Postar</a>
		</form>
	</div>
	<div class="content">
		<div class="col-small verde-3">
			<h2>Opções</h2>
			<ul>
				<c:forEach items="${listaAmigos.entrySet()}" var="amigo">
					<li><a href="Mural?id=${item.getKey()}">"${item.getValue()}"</a></li>
				</c:forEach>
				<c:forEach items="${listaGrupos.entrySet()}" var="grupo">
					<li><a href="Mural?id=${item.getKey()}">"${item.getValue()}"</a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="timeline">
			<c:forEach var="p" items="${publicacoes}">
				<div class="post">
					<div class="verde-2 left post-head">
						<p>
							<c:out value="${p.getNomeIDAutor()}" />
						</p>
						<a href="Mural?id=${p.getIDUsuarioAutor()}">Ver mural</a>
					</div>
					<div class="post-content center">
						<p>
							<c:out value="${p.getTexto()}" />
						</p>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>