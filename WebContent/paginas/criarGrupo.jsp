<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="center">Crie Seu Grupo</div>
	<div class="center">
		<form action="CriarGruposvl" method="post">			
			Nome:<input type="text" name="nome"> <br /> 			
			<input class="btn-green" type="submit" value="CRIAR"> <br />
		</form>
		<div>${msg}</div>
		<a href="${pageContext.request.contextPath}/paginas/principal.jsp">Retornar</a>
	</div>
</body>
</html>