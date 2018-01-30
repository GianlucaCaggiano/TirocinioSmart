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
		<% 	
			if(request.getParameter("errore") != null)
			{
				String err="Errore : "+request.getParameter("errore");
		%>
		
		<br/>
		<h4 class="text-danger"><%= err %></h4>
		
		<% 
			}
		%>
		
		<br/>
		<br/>
		<form id="ajax-progettoFormativo-form" action="AggiungiProgettoFormativo" method="post" role="form" autocomplete="off" style="width: 50%; margin:0 auto;">
        	<fieldset class="form-group">
        		<legend>Dati Progetto Formativo</legend>
        			<span><b>Tirocinante:</b></span>
        			<div class="form-group">
                		 <input type="text" class="form-control" disabled="disabled" value="<%=request.getParameter("cognome")%> <%=request.getParameter("nome")%>">
                	</div>
                	<span><b>Matricola:</b></span>
                	<div class="form-group">
                		<input type="text" class="form-control"  disabled="disabled" value="<%=request.getParameter("matricola")%>">
                	</div>
                	<span><b>Professore:</b></span> 
                	<div class="form-group">
                		<input type="text" class="form-control" disabled="disabled" value="<%=request.getParameter("pCognome")%> <%=request.getParameter("pNome")%>">
                	</div>
                	<div class="form-group">
                		<input type="hidden" name="professoreEmail" class="form-control" value="<%=request.getParameter("pEmail")%>">
                	</div>
                	<div class="form-group">
                		<input type="hidden" name="matricolaStudente" class="form-control" value="<%=request.getParameter("matricola")%>">
                	</div>
            </fieldset>
            <fieldset class="form-group">
            	<legend>Periodo di Tirocinio</legend>
            		<span><b>Data Inizio:</b></span>
            		<div class="form-group">
                		 <input type="text" class="form-control" name="dataInizio" id="dataInizio" placeholder="Data Inizio: YYYY/MM/GG*" value="" required >
                	</div>
                	<span><b>Data Fine:</b></span>
                	<div class="form-group">
                		 <input type="text" class="form-control" name="dataFine" id="dataFine" placeholder="Data Fine: YYYY/MM/GG*" value="" required >
                	</div>	
            	<legend>Obiettivi del tirocinio:</legend>
            		<div class="form-group">
                		<input type="text" class="form-control" name="obiettivi" id="obiettivi" placeholder="Obiettivi*" value="" maxlength="255" >
                	</div>
             </fieldset>
              <input type="submit" name="login-submit" id="login-submit" class="btn btn-primary" value="Invia">
              <input type="reset" class="btn btn-default" value="Reset">
		</form>
		<br/><br/>
  	</div>
	

   <%@ include file="footer.html" %>

   <%@ include file="footerImport.html" %>
</body>

</html>