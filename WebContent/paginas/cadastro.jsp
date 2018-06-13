<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="StyleSheet" type="text/css" href="/css/Style.css"
	media="screen">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rede Social</title>
</head>
<body>
	<div class="center">Seja Bem vindo</div>
	<div class="center">Informe seus dados</div>
	<div class="center">
		<form action="${pageContext.request.contextPath}/Cadastrosvl" method="post">
			Login:<input type="text" name="login"> <br /> 
			Senha:<input type="text" name="senha"> <br /> 
			Nome:<input type="text" name="nome"> <br /> 
			Trabalho:<input type="text" name="trabalho"> <br /> 
			Endereço:<input type="text" name="ender"> <br /> 
			<input class="btn-green" type="submit" value="Realizar cadastro"> <br />
		</form>
		<div>${msg}</div>
		<a href="${pageContext.request.contextPath}/index.jsp">Retornar</a>
	</div>
</body>
</html>