<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="sessionImport.txt" %>
    
<!DOCTYPE html>
<html lang="it">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Pagina di errore">
    <meta name="author" content="Caggiano Gianluca">
    <meta http-equiv="refresh" content="5;URL=index.jsp">
    
    <title>Errore</title>

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
		<h1 class="text-danger">Ops...Pagina non trovata</h1>
		<br/>
		<p>Nel vano tentativo di scoprire i nostri segreti più oscuri sei incappato in una pagina che non esiste.</p>
		 <br/> <br/>
		<p>Non dannarti, ci pensiamo noi a riportarti alla Home... Buona Navigazione :).</p>
		
	</div>

   <%@ include file="footer.html" %>

   <%@ include file="footerImport.html" %>
</body>

</html>
