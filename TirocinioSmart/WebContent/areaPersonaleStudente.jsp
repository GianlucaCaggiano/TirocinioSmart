<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, gestioneProgettoFormativo.*, storageLayer.*"%>
<%@ include file="sessionImport.txt" %>
    
<!DOCTYPE html>
<html lang="it">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Area Personale Studente">
    <meta name="author" content="Iannuzzi Nicola'">

    <title>Area Personale Studente</title>

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
		<h1>Area Personale Studente</h1>
		
		<br/>
		<%if(studente.getRichiestaTirocinio() != null) {%>
		<button type="button" onclick="location.href='statoRichiestaStudente.jsp'" class="btn btn-danger btn-lg" data-toggle="modal" style=" min-width: 280px; width: 50%; margin: 10px;">Visualizza Stato Richiesta</button>
		<%}else{ %>
		<button type="button" onclick="location.href='listaAziende.jsp'" class="btn btn-danger btn-lg" data-toggle="modal" style=" min-width: 280px; width: 50%; margin: 10px;">Scelta Azienda e Professore</button>
		<%} %>
		<br/>
		<%RichiestaTirocinio richiesta = DatabasePf.getRichiestaByID(studente.getRichiestaTirocinio().getId());
		if(richiesta.isConvalidaAzienda() && richiesta.isConvalidaProf())
		{%>
		<button type="button" class="btn btn-danger btn-lg" data-toggle="modal" style=" min-width: 280px; width: 50%; margin: 10px;">Sottoscrivi Progetto Formativo</button>
		<%}%>
	</div>

   <%@ include file="footer.html" %>

   <%@ include file="footerImport.html" %>
</body>

</html>