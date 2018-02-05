<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="sessionImport.txt" %>

<!DOCTYPE html>
<html lang="it">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Pagina di successo">
    <meta name="author" content="Caggiano Gianluca">
    <%if(studente.isAutenticato() || azienda.isAutenticato() || professore.isAutenticato() || segreteria.isAutenticato())
    {%>
      <meta http-equiv="refresh" content="3;URL=areaPersonale.jsp">
    <%}else{ %>
      <meta http-equiv="refresh" content="3;URL=index.jsp">
    <%} %>
    
    <title>Successo operazione</title>

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
  <img src="images/success.png" alt="successo">
  <br/>
  <h1 class="text-danger">Complimenti!!!</h1>
  <br/>
  <h2>Operazione completata con <span class="text-success">successo!</span></h2>
  <br/>
  <br/>
  <h4>Tra qualche secondo verrai automaticamente reindirizzato alla home page</h4>
  <br/>
  <br/>
  </div>
  

   <%@ include file="footer.html" %>

   <%@ include file="footerImport.html" %>
</body>

</html>
