<%@page import="storageLayer.DatabaseGu"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, gestioneUtente.Professore"%>
    <%@ include file="sessionImport.txt" %>

<!DOCTYPE html>
<html lang="it">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Pagina Lista Professori">
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

    <title>Lista Professori</title>

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
		ArrayList<Professore> arrayProfessore = new ArrayList<Professore>();
		arrayProfessore = DatabaseGu.doRetriveAllProfessore();
	%>
	<div class="container text-center">
		<h1>Lista Professori</h1>
		<br/>
			<table>
					<tr>
					   <th>Cognome Nome</th>
					   <th>Materia</th>
					   <th class="btn-right"></th>
					</tr>
			<%
				for(Professore p:arrayProfessore)
				{
					%>
					  <tr>
					    <th><%=p.getCognome()%> <%=p.getNome()%></th>
					    <th><%=p.getMateria()%></th>
					    <%String aziendaScelta = request.getParameter("azienda"); %>
					    <th class="btn-right"><a href="AggiungiRichiestaTirocinio?azienda=<%=aziendaScelta%>&professore=<%=p.getUser()%>">Scegli Professore</a></th>
					  </tr>
					</table>
					<%
				}
			%>
		<br/>
  	</div>
	

   <%@ include file="footer.html" %>

   <%@ include file="footerImport.html" %>
</body>

</html>