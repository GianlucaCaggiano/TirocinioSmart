<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="it">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Pagina di login della segreteria">
    <meta name="author" content="Caggiano Gianluca">

    <title>Login Segreteria</title>

	<%@ include file="headImport.html" %>
	
</head>

<body>

	<%@ include file="navigation.html" %>
	<br/><br/>
	<div class="container text-center">
		<h1>Login <span class="text-danger">Segreteria</span></h1>
		<br/>
		<h4>Benvenuto nella sezione dedicata al login della <span class="text-danger">Segreteria</span></h4>
		<br/>
		<form action="#" method="post" role="form" autocomplete="off" style="width: 50%; margin:0 auto;">
        	<fieldset class="form-group">
        		<legend>Dati di accesso</legend>
        		<div class="form-group">
                	<input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Username" value="" required>
              	</div>
              	<div class="form-group">
                	<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password" required>
              	</div>
              	<input type="submit" name="login-submit" id="login-submit" tabindex="3" class="btn btn-primary" value="Login">
			</fieldset>
		</form>
  	</div>
	

   <%@ include file="footer.html" %>

   <%@ include file="footerImport.html" %>
</body>

</html>
