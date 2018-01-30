<%@page import="gestioneProgettoFormativo.RichiestaTirocinio"%>
<%@page import="storageLayer.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, gestioneProgettoFormativo.*"%>
    <%@ include file="sessionImport.txt" %>

<!DOCTYPE html>
<html lang="it">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Sottoscrivi Progetto Formativo">
    <meta name="author" content="Iannuzzi Nicola'">

	<style>
		table 
		{
			position: relative;
		    font-family: arial, sans-serif;
		    border-collapse: collapse;
		    width: 90%;
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

    <title>Sottoscrivi Progetto Formativo</title>

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
		ProgettoFormativo progettoFormativo = DatabasePf.doRetrievProgettoFormativoStudente(studente.getMatricola());
	%>
	<div class="container text-center">
		<h1>Sottoscrivi Progetto Formativo</h1>
		<br/>
		<%if(progettoFormativo == null)
		{%>
			<span>Nessuna Richiesta</span>
	<% 	} else {%>
				<table>
					<tr>
					   <th>ID Progetto Formativo</th>
					   <th>Azienda</th>
					   <th>Professore</th>
					   <th>Data Inizio</th>
					   <th>Data Fine</th>
					   <th>Obiettivo</th>
					   <th class="btn-right"></th>  
					</tr>
				
					<tr>
				    <th><%=progettoFormativo.getId()%></th>
				    <th><%=progettoFormativo.getAzienda().getDenominazione()%></th>
				    <th><%=progettoFormativo.getProfessore().getCognome()%> <%=progettoFormativo.getProfessore().getNome()%></th>
				    <th><%=progettoFormativo.getDataInizio()%></th>
				    <th><%=progettoFormativo.getDataFine()%></th>
				    <th><%=progettoFormativo.getObiettivi()%></th>
				    <th class="btn-right"><a href="SottoscriviProgettoFormativo?id=<%=progettoFormativo.getId()%>">Sottoscrivi</a></th>
				  </tr>
			</table>
		<%} %>
		<br/>
  	</div>
	

   <%@ include file="footer.html" %>

   <%@ include file="footerImport.html" %>
</body>

</html>