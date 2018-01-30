<%@page import="gestioneProgettoFormativo.RichiestaTirocinio"%>
<%@page import="storageLayer.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, gestioneUtente.Azienda"%>
    <%@ include file="sessionImport.txt" %>

<!DOCTYPE html>
<html lang="it">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Compila Progetto Formativo">
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

    <title>Compila Progetto Formativo</title>

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
		Azienda az = (Azienda) request.getSession().getAttribute("azienda");
	
		ArrayList<RichiestaTirocinio> array = new ArrayList<RichiestaTirocinio>();
		array = DatabasePf.doRetrieveRichiesteAziendeConvalidate(az.getUser());
	%>
	<div class="container text-center">
		<h1>Compila Progetto Formativo</h1>
		<br/>
		<%if(array.size()==0)
		{%>
			<span>Nessuna Richiesta</span>
	<% 	} else {%>
				<table>
						<tr>
						   <th>ID Richiesta</th>
						   <th>Matricola Studente</th>
						   <th>Studente</th>
						   <th>Professore</th>
						   <th class="btn-right"></th>
						    
						</tr>
			<%
				for(RichiestaTirocinio rt:array)
				{
					%>
					
						<tr>
					    <th><%=rt.getId()%></th>
					    <th><%=DatabasePf.getStudenteByIDRichiesta(rt.getId()).getMatricola()%></th>
					    <th><%=DatabasePf.getStudenteByIDRichiesta(rt.getId()).getCognome()%> <%=DatabasePf.getStudenteByIDRichiesta(rt.getId()).getNome()%></th>
					    <th><%=rt.getProfessore().getCognome()%> <%=rt.getProfessore().getNome()%></th>
					    <th class="btn-right"><a href="compilaProgettoFormativoAzienda.jsp?cognome=<%=DatabasePf.getStudenteByIDRichiesta(rt.getId()).getCognome()%>&nome=<%=DatabasePf.getStudenteByIDRichiesta(rt.getId()).getNome()%>&matricola=<%=DatabasePf.getStudenteByIDRichiesta(rt.getId()).getMatricola()%>&pCognome=<%=rt.getProfessore().getCognome()%>&pNome=<%=rt.getProfessore().getNome()%>&pEmail=<%=rt.getProfessore().getUser()%>">Compila</a></th>
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