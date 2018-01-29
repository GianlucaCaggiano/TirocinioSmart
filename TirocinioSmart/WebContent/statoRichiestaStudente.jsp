<%@page import="storageLayer.DatabasePf"%>
<%@page import="storageLayer.DatabaseGu"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, gestioneProgettoFormativo.*"%>
    <%@ include file="sessionImport.txt" %>

<!DOCTYPE html>
<html lang="it">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Visualizza Stato Richiesta">
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

    <title>Stato Richiesta</title>

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
		RichiestaTirocinio richiesta = DatabasePf.getRichiestaByID(studente.getRichiestaTirocinio().getId());
		String aziendaConvalida;
		String professoreConvalida;
		if(richiesta.isConvalidaAzienda())
		{
			aziendaConvalida = "Confermato";
		}
		else
		{
			aziendaConvalida = "In Attesa";
		}
		if(richiesta.isConvalidaProf())
		{
			professoreConvalida = "Confermato";
		}
		else
		{
			professoreConvalida = "In Attesa";
		}
	%>
	<div class="container text-center">
		<h1>Stato Richiesta</h1>
		<br/>
		<table>
			<tr>
			   <th>ID Richiesta Tirocinio</th>
			   <th>Azienda</th>
			   <th>Professore</th>
			</tr>					
			<tr>
			    <th><%=richiesta.getId()%></th>
			    <th><%=aziendaConvalida%></th>
			    <th><%=professoreConvalida%></th>
			 </tr>
		 </table>
		<br/>
  	</div>
	

   <%@ include file="footer.html" %>

   <%@ include file="footerImport.html" %>
</body>

</html>
