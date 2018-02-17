<%@page import="storagelayer.DatabaseGu"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="gestioneutente.*"%>
    <%@ include file="sessionImport.txt" %>

<!DOCTYPE html>
<html lang="it">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Pagina Contatti">
    <meta name="author" content="Iannuzzi Nicolà">

    <title>Contatti</title>

	<%@ include file="headImport.html" %>
	
</head>

<body>

	<%if(studente.isAutenticato() || azienda.isAutenticato() || professore.isAutenticato() || segreteria.isAutenticato())
	  {%>
			<%@ include file="navigationAutenticate.html" %>
	<%}else{ %>
			<%@ include file="navigation.html" %>
	<%} %>
	<!-- Full Width Image Header with Logo -->
    <!-- Image backgrounds are set within the full-width-pics.css file. -->
    <header class="image-bg-fluid-height">
        <img style="width: 75%;" src="images/logo-di.png" alt="Logo"> <!-- Logo del dipartimento di informatica dell'Unisa -->
    </header>
	<br/><br/>
	<div class="container text-center">
		<h1>Contatti</h1>
		<h3>Segreteria Unisa Informatica</h3>
		<br/><br/>
		
		<%Segreteria segre = DatabaseGu.getSegreteriaByUser(Segreteria.USERNAME);%>
		
		<p style="font-size: 20px;">Email: <%=segre.getEmail()%> </p>
		<p style="font-size: 20px;">Telefono: <%=segre.getTelefono()%> </p>
		
  	</div>
	<br/><br/><br/><br/><br/>

   <%@ include file="footer.html" %>

   <%@ include file="footerImport.html" %>
</body>

</html>