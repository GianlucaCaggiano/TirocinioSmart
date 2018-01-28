<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="sessionImport.txt" %>

<!DOCTYPE html>
<html lang="it">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Pagina di registrazione">
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
		<% 	
			if(request.getParameter("errore") != null)
			{
				String err="Errore : "+request.getParameter("errore");
		%>
		
		<h4 class="text-danger"><%= err %></h4>
		
		<% 
			}
		%>
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
        			<form id="ajax-registerStudente-form" action="RegistrazioneStudente" method="post" role="form" autocomplete="off" style="margin: 0px 10px 0px 10px">
        				<div class="form-group">
                			<input type="text" name="matricola" id="matricola" tabindex="1" class="form-control" placeholder="Matricola*" value="" required>
              			</div>
        				<div class="form-group">
                			<input type="email" name="email" id="email" tabindex="2" class="form-control" placeholder="Email di ateneo*" value="" required>
              			</div>
              			<div class="form-group">
                			<input type="password" name="password" id="password" tabindex="3" class="form-control" placeholder="Password*" required>
              			</div>
              			<div class="form-group">
                			<input type="password" name="confirmpassword" id="confirmpassword" tabindex="4" class="form-control" placeholder="Conferma Password*" required>
              			</div>
              			<div class="form-group">
                			<input type="text" name="nome" id="nome" tabindex="5" class="form-control" placeholder="Nome*" value="" required>
              			</div>
              			<div class="form-group">
                			<input type="text" name="cognome" id="cognome" tabindex="6" class="form-control" placeholder="Cognome*" value="" required>
              			</div>
              			<div class="form-group">
                			<input type="text" name="dataNascita" id="dataNascita" tabindex="7" class="form-control" placeholder="Data di nascita: YYYY/MM/GG*" value="" required>
              			</div>
              			<div class="form-group">
                			<input type="text" name="luogoNascita" id="luogoNascita" tabindex="8" class="form-control" placeholder="Comune di nascita*" value="" required>
              			</div>
        				<div class="modal-footer">
                			<input type="submit" name="register-submit" id="register-submit" tabindex="9" class="btn btn-primary" value="Registra">
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
    		<div class="modal-dialog">
     		 	<div class="modal-content">
        			<div class="modal-header">
          				<button type="button" class="close" data-dismiss="modal">&times;</button>
          				<h4 class="modal-title">Registrazione Azienda</h4>
        			</div>
        			<form id="ajax-registerAzienda-form" action="RegistrazioneAzienda" method="post" role="form" autocomplete="off" style="margin: 0px 10px 0px 10px">
        				<div class="form-group">
                			<input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="Email*" value="" required>
              			</div>
              			<div class="form-group">
                			<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password*" required>
              			</div>
              			<div class="form-group">
                			<input type="password" name="confirmpassword" id="confirmpassword" tabindex="3" class="form-control" placeholder="Conferma Password*" required>
              			</div>
              			<fieldset class="form-group">
        				<legend>Dati azienda</legend>
        				<div class="form-group">
                			<input type="text" name="denominazione" id="denominazione" tabindex="4" class="form-control" placeholder="Ragione sociale*" value="" required>
              			</div>
              			<div class="form-group">
                			<input type="text" name="citta" id="citta" tabindex="5" class="form-control" placeholder="Citt&agrave in cui è presente la sede*" value="" required>
              			</div>
              			<div class="form-group">
                			<input type="text" name="cap" id="cap" tabindex="6" class="form-control" placeholder="CAP*" value="" required>
              			</div>
              			<div class="form-group">
                			<input type="text" name="via" id="via" tabindex="7" class="form-control" placeholder="Via*" value="" required>
              			</div>
              			<div class="form-group">
                			<input type="text" name="telefono" id="telefono" tabindex="8" class="form-control" placeholder="Telefono" value="">
              			</div>
              			<div class="form-group">
                			<input type="text" name="sitoWeb" id="sitoWeb" tabindex="9" class="form-control" placeholder="Sito Web" value="">
              			</div>
              			<div class="form-group">
                			<input type="text" name="chiSiamo" id="chiSiamo" tabindex="10" class="form-control" placeholder="Info Azienda" maxlength="255" value="">
              			</div>
              			</fieldset>
              			<fieldset class="form-group">
        				<legend>Dati tutor aziendale</legend>
              			<div class="form-group">
                			<input type="text" name="nome" id="nome" tabindex="11" class="form-control" placeholder="Nome*" value="" required>
              			</div>
              			<div class="form-group">
                			<input type="text" name="cognome" id="nome" tabindex="12" class="form-control" placeholder="Cognome*" value="" required>
              			</div>
              			<div class="form-group">
                			<input type="text" name="dataNascita" id="dataNascita" tabindex="13" class="form-control" placeholder="Data di nascita: YYYY/MM/GG*" value="" required>
              			</div>
              			<div class="form-group">
                			<input type="text" name="luogoNascita" id="luogoNascita" tabindex="14" class="form-control" placeholder="Comune di nascita*" value="" required>
              			</div>
              		<legend>Convenzione</legend>
              			<div class="form-group">
              				<p>La convenzione tra ente e universita' viene effettuata secondo le specifiche previste dallo stato </p>
              				<p>Se si vogliono inserire delle specifiche di convenzione extra, queste possono essere indicate di seguito</p>
                			<input type="text" name="specifiche" id="specifiche" tabindex="15" class="form-control" placeholder="Specifica Convenzione" value="">
              			</div>
              			</fieldset>
        				<div class="modal-footer">
                			<input type="submit" name="register-submit" id="register-submit" tabindex="16" class="btn btn-primary" value="Registra">
          					<input type="reset" class="btn btn-default" value="Reset">
        				</div>
        			</form>
      			</div>
    		</div>
  		</div>
  		
  		<br/>
  		<!-- Trigger the modal with a button -->
  		<button type="button" class="btn btn-success btn-lg" data-toggle="modal" data-target="#professore" style=" width: 50%;  margin: 10px;">Professore</button>

  		<!-- Modal -->
  		<div class="modal fade" id="professore" role="dialog">
    		<div class="modal-dialog">
     		 	<div class="modal-content">
        			<div class="modal-header">
          				<button type="button" class="close" data-dismiss="modal">&times;</button>
          				<h4 class="modal-title">Registrazione Professore</h4>
        			</div>
        			<form id="ajax-registerProfessore-form" action="RegistrazioneProfessore" method="post" role="form" autocomplete="off" style="margin: 0px 10px 0px 10px">
        				<div class="form-group">
                			<input type="email" name="email" id="email" tabindex="2" class="form-control" placeholder="Email di ateneo" value="" required>
              			</div>
              			<div class="form-group">
                			<input type="password" name="password" id="password" tabindex="3" class="form-control" placeholder="Password" required>
              			</div>
              			<div class="form-group">
                			<input type="password" name="confirmpassword" id="confirmpassword" tabindex="4" class="form-control" placeholder="Conferma Password" required>
              			</div>
              			<div class="form-group">
                			<input type="text" name="nome" id="nome" tabindex="5" class="form-control" placeholder="Nome" value="" required>
              			</div>
              			<div class="form-group">
                			<input type="text" name="cognome" id="cognome" tabindex="6" class="form-control" placeholder="Cognome" value="" required>
              			</div>
              			<div class="form-group">
                			<input type="text" name="materia" id="materia" tabindex="7" class="form-control" placeholder="Corso in cui insegna (laurea triennale)" value="" required>
                		</div>
        				<div class="modal-footer">
                			<input type="submit" name="register-submit" id="register-submit" tabindex="8" class="btn btn-primary" value="Registra">
          					<input type="reset" class="btn btn-default" value="Reset">
        				</div>
        			</form>
      			</div>
    		</div>
  		</div>
	</div>
	

   <%@ include file="footer.html" %>

   <%@ include file="footerImport.html" %>
</body>

</html>
