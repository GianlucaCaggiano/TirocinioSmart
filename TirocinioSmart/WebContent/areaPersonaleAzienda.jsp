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

    <title>Area Personale Azienda</title>

	<%@ include file="headImport.html" %>
	
</head>

<body>

	<%if(studente.isAutenticato() || azienda.isAutenticato() || professore.isAutenticato() || segreteria.isAutenticato())
	  {%>
			<%@ include file="navigationAutenticate.html" %>
	<%}else{ %>
			<%@ include file="navigation.html" %>
	<%} %>
	<br/><br/>
	<div class="container text-center">
		<h1>Area Personale Azienda</h1>
		
		<br/>
		<button type="button" class="btn btn-warning btn-lg" data-toggle="modal" style=" min-width: 280px; width: 50%; margin: 10px;">Domanda di Tirocinio</button>
		<br/>
		<button type="button" class="btn btn-warning btn-lg" data-toggle="modal" style=" min-width: 280px; width: 50%; margin: 10px;">Compila Progetto Formativo</button>
		
	</div>

   <%@ include file="footer.html" %>

   <%@ include file="footerImport.html" %>
</body>

</html>