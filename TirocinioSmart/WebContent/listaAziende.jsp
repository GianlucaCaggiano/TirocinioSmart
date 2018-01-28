<%@page import="storageLayer.DatabaseGu"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, gestioneUtente.Azienda"%>
    <%@ include file="sessionImport.txt" %>

<!DOCTYPE html>
<html lang="it">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Pagina lista aziende">
    <meta name="author" content="Caggiano Gianluca">

    <title>Lista azienda</title>

	<%@ include file="headImport.html" %>
	
</head>

<body>

	<%@ include file="navigation.html" %>
	<br/><br/>
	<%
		ArrayList<Azienda> array = new ArrayList<Azienda>();
		array = DatabaseGu.doRetriveAllAziende();
	%>
	<div class="container text-center">
		<h1>Aziende Convenzionate</h1>
		<br/>
			<%
				for(Azienda a:array)
				{
					%>
					<p><%=a.getDenominazione() %></p><br/>
					<%
				}
			%>
		<br/>
  	</div>
	

   <%@ include file="footer.html" %>

   <%@ include file="footerImport.html" %>
</body>

</html>
