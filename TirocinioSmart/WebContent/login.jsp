<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="it">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Pagina di login">
    <meta name="author" content="Caggiano Gianluca">

    <title>Login</title>

	<%@ include file="headImport.html" %>
	
</head>

<body>

	<%@ include file="navigation.html" %>
	<br/><br/>
	<div class="container text-center">
		<h1>Login</h1>
		<br/>
		<h4>Benvenuto nella sezione dedicata al login</h4>
		<br/>
		<form id="ajax-login-form" action="Login" method="post" role="form" autocomplete="off" style="width: 50%; margin:0 auto;">
        	<fieldset class="form-group">
        		<legend>Dati di accesso</legend>
        		<div class="form-group">
                	<input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="Email" value="" required>
              	</div>
              	<div class="form-group">
                	<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password" required>
              	</div>
              	<input type="submit" name="login-submit" id="login-submit" tabindex="3" class="btn btn-primary" value="Login">
			</fieldset>
		</form>
		<br/><br/>
		<h4>Sei un admin? Clicca <a href="loginAdmin.jsp"> qui </a> per accedere</h4>
  	</div>
	

   <%@ include file="footer.html" %>

   <%@ include file="footerImport.html" %>
</body>

</html>
