<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="sessionImport.txt" %>

<!DOCTYPE html>
<html lang="it">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Progetto Formativo">
    <meta name="author" content="Iannuzzi Nicola'">

    <title>Progetto Formativo</title>

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
		<h1>Progetto Formativo</h1>
		<br/>
		
		<br/>
		<form id="ajax-progettoFormativo-form" action="AggiungiProgettoFormativo" method="post" role="form" autocomplete="off" style="width: 50%; margin:0 auto;">
        	<fieldset class="form-group">
        		<legend>Dati Progetto Formativo</legend>
        			<div class="form-group">
                	<span><b>Tirocinante:</b></span> <input type="text" class="form-control" disabled="disabled" value="<%=request.getParameter("cognome")%> <%=request.getParameter("nome")%>"><br/>
                	<span><b>Matricola:</b></span> <input type="text" class="form-control"  disabled="disabled" value="<%=request.getParameter("matricola")%>"><br/>
                	<span><b>Professore:</b></span> <input type="text" class="form-control" disabled="disabled" value="<%=request.getParameter("pCognome")%> <%=request.getParameter("pNome")%>"><br/>
                	<input type="hidden" name="professoreEmail" class="form-control" value="<%=request.getParameter("pEmail")%>">
                	<input type="hidden" name="matricolaStudente" class="form-control" value="<%=request.getParameter("matricola")%>">
            <legend>Periodo di Tirocinio</legend>
                <span><b>Data Inizio:</b></span> <input type="text" class="form-control" name="dataInizio" id="dataInizio" placeholder="Data Inizio: YYYY/MM/GG*" value="" required ><br/>
                	<span><b>Data Fine:</b></span> <input type="text" class="form-control" name="dataFine" id="dataFine" placeholder="Data Fine: YYYY/MM/GG*" value="" required ><br/>   	
            <legend>Obiettivi del tirocinio:</legend>
                	<input type="text" class="form-control" name="obiettivi" id="obiettivi" placeholder="Obiettivi*" value="" required ><br/>
                	</div>
              	<input type="submit" name="login-submit" id="login-submit" class="btn btn-primary" value="Invia">
              	<input type="reset" class="btn btn-default" value="Reset">
			</fieldset>
		</form>
		<br/><br/>
  	</div>
	

   <%@ include file="footer.html" %>

   <%@ include file="footerImport.html" %>
</body>

</html>