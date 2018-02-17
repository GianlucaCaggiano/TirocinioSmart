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
    <meta name="description" content="Pagina Info Tirocinio">
    <meta name="author" content="Iannuzzi Nicolà">

    <title>Info Tirocinio</title>

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
		<h1>Info Tirocinio</h1>
		<h3>Presentazione</h3>
		<br/><br/>
		
		<p style="font-size: 17px; text-align: left;"> Il Dipartimento di Informatica promuove e gestisce l'offerta e l'attivazione di tirocini curriculari per i propri studenti.<br/>
		Il tirocinio viene svolto presso Aziende o Enti accreditati previa stipula di convenzione tra il Dipartimento di Informatica (soggetto promotore) e l'Azienda/Ente 
		(soggetto ospitante). <a href="listaAziende.jsp">Consulta l'Elenco delle Aziende convenzionate.</a> <br/>
		Per ogni tirocinio è previsto un progetto formativo che riporta gli obiettivi formativi, i riferimenti del tirocinante, del tutor didattico e del tutor aziendale, e 
		definisce la tipologia, la durata e le modalità del tirocinio. <br/>
		La procedura per l'attivazione e lo svolgimento dei tirocini formativi prevede:</p>
		
		<br/>
		<ol style="font-size: 17px; text-align: left;">
			<li><a href="registrazione.jsp">Registrazione</a> presso la Piattaforma Tirocinio Smart</li>
			<li>Scelta da parte dello studente <a href="listaAziende.jsp">dall'elenco delle aziende già convenzionate</a> con il Dipartimento di Informatica.</li>
			<li>Scelta da parte dello studente <a href="listaProfessori.jsp">dall'elenco dei professori</a> disponibili.</li>
			<li>Attendere Esito della Richiesta di Tirocinio da parte del Tutor Accademico e Tutor Aziendale.</li>
			<li>Una volta che viene confermata la Richiesta, l'Azienda stila il Progetto Formativo riportanti gli obiettivi e il periodo di Tirocinio.</li>
			<li>Validazione da parte di Professore e Segreteria del Progetto Formativo proposto..</li>
			<li>Sottoscrizione da parte dello Studente del Progetto Formativo e inizio del Tirocinio nel periodo indicato.</li>
		</ol>
		
  	</div>

   <%@ include file="footer.html" %>

   <%@ include file="footerImport.html" %>
</body>

</html>