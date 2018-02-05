<%@page import="storagelayer.DatabaseGu"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, gestioneutente.*, gestioneprogettoformativo.*, storagelayer.*"%>
    <%@ include file="sessionImport.txt" %>

<!DOCTYPE html>
<html lang="it">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Richieste Registrazioni">
    <meta name="author" content="Iannuzzi Nicola'">

	<style>
		table 
		{
			position: relative;
		    font-family: arial, sans-serif;
		    border-collapse: collapse;
		    width: 70%;
		    margin:0 auto;
		}
		
		td, th 
		{
		    border: 1px solid lightgrey;
		    text-align: left;
		    padding: 8px !important;
		}
		
		td, th.btn-right
		{
		    width: 20%;
			text-align: center;
		}
		
		tr:nth-child(even) 
		{
		    background-color: #dddddf;
		}
	</style>

    <title>Richieste Registrazioni</title>

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
	
	<%
		ArrayList<Utente> arrayRichiestaAziende = new ArrayList<Utente>();
		arrayRichiestaAziende = DatabaseGu.doRetriveAllNonAbilitatiAziende();
		
		ArrayList<Utente> arrayRichiestaProfessore = new ArrayList<Utente>();
		arrayRichiestaProfessore = DatabaseGu.doRetriveAllNonAbilitatiProfessori();
	%>
	<div class="container text-center">
	 <%   
      if(request.getParameter("success") != null)
      {
        String succ=request.getParameter("success");
    %>
    <img src="images/success.png" alt="successo">
    <br/>
    <h4 class="text-success"><%= succ %></h4>
    
    <% 
      }
    %>
    
		<h1>Richieste Registrazioni</h1>
		<br/>
		<%if(arrayRichiestaAziende.size()==0 && arrayRichiestaProfessore.size()==0)
		{%>
			<h4>Nessuna Richiesta</h4>
	<% 	} else {%>
			<table>
					<tr>
					   <th>Email</th>
					   <th>Nome</th>
					   <th>Cognome</th>
					   <th>Tipo</th>
					   <th class="btn-right"></th>
					</tr>
			<%
				for(Utente utente:arrayRichiestaAziende)
				{
					%>
					  <tr>
					    <th><%=utente.getUser()%></th>
					    <th><%=utente.getNome()%></th>
					    <th><%=utente.getCognome()%></th>
					    <th><%=utente.getTipo()%></th>
					    <th class="btn-right"><a href="AbilitaRichiestaRegistrazioneAzienda?id=<%=utente.getUser()%>">Abilita</a></th>
					  </tr>
					
					<%
				}
			%>
			<%
				for(Utente utente:arrayRichiestaProfessore)
				{
					%>
					  <tr>
					    <th><%=utente.getUser()%></th>
					    <th><%=utente.getNome()%></th>
					    <th><%=utente.getCognome()%></th>
					    <th><%=utente.getTipo()%></th>
					    <th class="btn-right"><a href="AbilitaRichiestaRegistrazioneProfessore?id=<%=utente.getUser()%>">Abilita</a></th>
					  </tr>
					
					<%
				}
			%>
			</table>
			<%} %>
		<br/>
  	</div>
	

   <%@ include file="footer.html" %>

   <%@ include file="footerImport.html" %>
</body>

</html>