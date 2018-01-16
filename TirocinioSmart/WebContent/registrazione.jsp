<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="it">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Home page del sito">
    <meta name="author" content="Caggiano Gianluca">

    <title>Registrazione</title>

	<%@ include file="headImport.html" %>
	
</head>

<body>

	<%@ include file="navigation.html" %>
	<br/><br/>
	<div class="container text-center">
	<h1>Registrazione</h1>
	<br/>
	<h4>Benvenuto nella sezione dedicata alla registrazione</h4>
		<br/>
		
  		<h2>Cosa sei?</h2>
  		<br/>
  		<!-- Trigger the modal with a button -->
  		<button type="button" class="btn btn-danger btn-lg" data-toggle="modal" data-target="#studente" style=" width: 50%; margin: 10px;">Studente</button>

  		<!-- Modal -->
  		<div class="modal fade" id="studente" role="dialog">
    		<div class="modal-dialog">
     		 	<div class="modal-content">
        			<div class="modal-header">
          				<button type="button" class="close" data-dismiss="modal">&times;</button>
          				<h4 class="modal-title">Registrazione Studente</h4>
        			</div>
        			<form id="ajax-register-form" action="#" method="post" role="form" autocomplete="off" style="margin: 0px 10px 0px 10px">
        				<div class="form-group">
                			<input type="text" name="matricola" id="matricola" tabindex="1" class="form-control" placeholder="Matricola" value="" required>
              			</div>
        				<div class="form-group">
                			<input type="email" name="email" id="email" tabindex="2" class="form-control" placeholder="Email" value="" required>
              			</div>
              			<div class="form-group">
                			<input type="password" name="password" id="password" tabindex="3" class="form-control" placeholder="Password" required>
              			</div>
              			<div class="form-group">
                			<input type="password" name="confirm-password" id="confirm-password" tabindex="4" class="form-control" placeholder="Conferma Password" required>
              			</div>
              			<div class="form-group">
                			<input type="text" name="nome" id="nome" tabindex="5" class="form-control" placeholder="Nome" value="" required>
              			</div>
              			<div class="form-group">
                			<input type="text" name="cognome" id="nome" tabindex="6" class="form-control" placeholder="Cognome" value="" required>
              			</div>
        				<div class="modal-footer">
                			<input type="submit" name="register-submit" id="register-submit" tabindex="7" class="btn btn-primary" value="Registra">
          					<input type="reset" class="btn btn-default" value="Reset">
        				</div>
        			</form>
      			</div>
    		</div>
  		</div>
  		
  		<br/>
  		<!-- Trigger the modal with a button -->
  		<button type="button" class="btn btn-warning btn-lg" data-toggle="modal" data-target="#azienda" style=" width: 50%;  margin: 10px;">Azienda</button>

  		<!-- Modal -->
  		<div class="modal fade" id="azienda" role="dialog">
    		<div class="modal-dialog modal-lg">
     		 	<div class="modal-content">
        			<div class="modal-header">
          				<button type="button" class="close" data-dismiss="modal">&times;</button>
          				<h4 class="modal-title">Registrazione Azienda</h4>
        			</div>
        			<div class="modal-body">
          				<p>This is a large modal.</p>
        			</div>
        			<div class="modal-footer">
          				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        			</div>
      			</div>
    		</div>
  		</div>
  		
  		<br/>
  		<!-- Trigger the modal with a button -->
  		<button type="button" class="btn btn-success btn-lg" data-toggle="modal" data-target="#professore" style=" width: 50%;  margin: 10px;">Professore</button>

  		<!-- Modal -->
  		<div class="modal fade" id="professore" role="dialog">
    		<div class="modal-dialog modal-lg">
     		 	<div class="modal-content">
        			<div class="modal-header">
          				<button type="button" class="close" data-dismiss="modal">&times;</button>
          				<h4 class="modal-title">Registrazione Professore</h4>
        			</div>
        			<div class="modal-body">
          				<p>This is a large modal.</p>
        			</div>
        			<div class="modal-footer">
          				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        			</div>
      			</div>
    		</div>
  		</div>
	</div>
	

   <%@ include file="footer.html" %>

   <%@ include file="footerImport.html" %>
</body>

</html>
