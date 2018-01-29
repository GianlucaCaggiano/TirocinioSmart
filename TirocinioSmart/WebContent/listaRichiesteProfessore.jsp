<%@page import="storageLayer.DatabaseGu"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, gestioneUtente.Professore, gestioneProgettoFormativo.*, storageLayer.*"%>
    <%@ include file="sessionImport.txt" %>

<!DOCTYPE html>
<html lang="it">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Pagina Lista Richieste per Professore">
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

    <title>Lista Richieste Professore</title>

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
		ArrayList<RichiestaTirocinio> arrayRichiesta = new ArrayList<RichiestaTirocinio>();
		arrayRichiesta = DatabasePf.doRetrieveRichiesteProfessore(professore.getUser());
	%>
	<div class="container text-center">
		<h1>Lista Richieste di Tirocinio</h1>
		<br/>
			<table>
					<tr>
					   <th>ID Richiesta</th>
					   <th>Matricola Studente</th>
					   <th>Studente</th>
					   <th>Azienda</th>
					   <th class="btn-right"></th>
					</tr>
			<%
				for(RichiestaTirocinio richiesta:arrayRichiesta)
				{
					%>
					  <tr>
					    <th><%=richiesta.getId()%></th>
					    <th><%=DatabasePf.getStudenteByIDRichiesta(richiesta.getId()).getMatricola()%></th>
					    <th><%=DatabasePf.getStudenteByIDRichiesta(richiesta.getId()).getCognome()%> <%=DatabasePf.getStudenteByIDRichiesta(richiesta.getId()).getNome()%></th>
					    <th><%=richiesta.getAzienda().getDenominazione()%></th>
					    <th class="btn-right"><a href="ConfermaRichiestaProf?id=<%=richiesta.getId()%>">Conferma</a></th>
					  </tr>
					
					<%
				}
			%>
			</table>
		<br/>
  	</div>
	

   <%@ include file="footer.html" %>

   <%@ include file="footerImport.html" %>
</body>

</html>