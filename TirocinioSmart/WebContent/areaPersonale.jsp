<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="sessionImport.txt" %>
    
<!DOCTYPE html>
<html lang="it">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Area Personale Azienda">
    <meta name="author" content="Iannuzzi Nicola'">

    <title>Area Personale</title>

	<%@ include file="headImport.html" %>
	
</head>

<body>

	<%if(studente.isAutenticato())
	  {%>
			<%response.sendRedirect("areaPersonaleStudente.jsp");%>
	<%}else if(azienda.isAutenticato()){ %>
			<%response.sendRedirect("areaPersonaleAzienda.jsp"); %>
	<%}else if(professore.isAutenticato()){%>
			<%response.sendRedirect("areaPersonaleProfessore.jsp"); %>
	<%}else if(segreteria.isAutenticato()){%>
	<		<%response.sendRedirect("areaPersonaleSegreteria.jsp"); %>
	<%}%>
</body>

</html>