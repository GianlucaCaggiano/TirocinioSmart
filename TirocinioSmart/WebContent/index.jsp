<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="sessionImport.txt" %>
    
<!DOCTYPE html>
<html lang="it">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Home page del sito">
    <meta name="author" content="Caggiano Gianluca">

    <title>Tirocinio Smart</title>

	<%@ include file="headImport.html" %>
	
</head>

<body>

	<%if(studente.isAutenticato() || azienda.isAutenticato() || professore.isAutenticato() || segreteria.isAutenticato())
	  {%>
			<%@ include file="navigationAutenticate.html" %>
	<%}else{ %>
			<%@ include file="navigation.html" %>
	<%} %>
	
    <!-- Full Width Image Header with Logo -->
    <!-- Image backgrounds are set within the full-width-pics.css file. -->
    <header class="image-bg-fluid-height">
        <img style="width: 75%;" src="images/logo-di.png" alt="Logo"> <!-- Logo del dipartimento di informatica dell'Unisa -->
    </header>

    <!-- Content Section -->
    <section>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="section-heading">Tirocinio Smart</h1>
                    <p class="lead section-lead">Benvenuto sul Sito per la Gestione dei Tirocini Esterni per il dipartimento di Informatica dell'Università degli Studi di Salerno.</p>
                    <p class="section-paragraph" style="font-size: 17px;">Se non sei ancora registrato, <a href="registrazione.jsp">Registrati Ora.</a><br/>
                    Se invece hai un account Studente allora effettua il <a href="login.jsp">Login </a> e comincia da subito la tua esperienza.<br/>
                    Hai qualche perplessità o dubbio? Clicca su <a href="infoTirocinio.jsp">Info Tirocinio</a> per saperne di più.<br/>
                    Ancora dubbi? <a href="contatti.jsp">Contatti</a>, risponderemo a tutte le tue domande.</p>
                </div>
            </div>
        </div>
    </section>

   <%@ include file="footer.html" %>

   <%@ include file="footerImport.html" %>
</body>

</html>
