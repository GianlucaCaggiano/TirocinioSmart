<%@page import="gestioneprogettoformativo.RichiestaTirocinio"%>
<%@page import="storagelayer.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, gestioneutente.Azienda"%>
    <%@ include file="sessionImport.txt" %>

<!DOCTYPE html>
<html lang="it">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Pagina lista Richieste azienda">
    <meta name="author" content="Caggiano Gianluca">

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

    <title>Lista Richieste Azienda</title>

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
		array = DatabasePf.doRetrieveRichiesteAziende(az.getUser());
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
    
		<h1>Lista Richieste Azienda</h1>
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
					    <th><%=DatabasePf.getStudenteByIdRichiesta(rt.getId()).getMatricola()%></th>
					    <th><%=DatabasePf.getStudenteByIdRichiesta(rt.getId()).getCognome()%> <%=DatabasePf.getStudenteByIdRichiesta(rt.getId()).getNome()%></th>
					    <th><%=rt.getProfessore().getCognome()%> <%=rt.getProfessore().getNome()%></th>
					    <th class="btn-right"><a href="ConfermaRichiestaAzienda?id=<%=rt.getId()%>">Conferma</a></th>
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
