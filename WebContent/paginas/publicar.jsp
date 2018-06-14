<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="center">
	<form action="Publicarsvl" method="post">			
		Pensamento:<input type="text" name="pensamento"> <br /> 		
		Privado?<input type="checkbox" name="visibilidade" value="1"> <br />
		<select name="destino">	
			<c:forEach items="${listaAmigos}" var="amigo">
				<option value="${item.getKey()}">${item.getValue()}</option>
			</c:forEach>	
			<c:forEach items="${listaGrupos}" var="grupo">
				<option value="${item.getKey()}">${item.getValue()}</option>
			</c:forEach>		
		</select>
		<input class="btn-green" type="submit" value="POSTAR"> <br />		
	</form>
	<div>${msg}</div>
	<a href="${pageContext.request.contextPath}/principal.jsp">Retornar</a>
</div>
</body>
</html>