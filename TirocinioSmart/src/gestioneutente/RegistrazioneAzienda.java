package gestioneutente;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import storagelayer.DatabaseGu;

/**
 * Servlet implementation class RegistrazioneAzienda Gestisce la registrazione dell'Azienda
 * 
 * @author Iannuzzi Nicola'
 * 
 * @version 1.0
 */
@WebServlet("/RegistrazioneAzienda")
public class RegistrazioneAzienda extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private String errore;

  /**
   * costruttore della classe.
   * 
   * @see HttpServlet#HttpServlet()
   */
  public RegistrazioneAzienda() {
    super();
  }

  /**
   * Richiama il metodo doPost per la registrazione di un Utente nel Database, Azienda.
   * 
   * @param request,
   *          response
   * @throws ServletException,
   *           IOException
   * 
   * @author Iannuzzi Nicola'
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  /**
   * Registra un utente nel database, Azienda.
   * 
   * @param request,
   *          response
   * @throws ServletException,
   *           IOException
   * 
   * @author Iannuzzi Nicola'
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    errore = "";
    if (controllo(request, response)) {
      String email = request.getParameter("email");
      String password = request.getParameter("password");
      String denominazione = request.getParameter("denominazione");
      String citta = request.getParameter("citta");
      String cap = request.getParameter("cap");
      String via = request.getParameter("via");
      String nome = request.getParameter("nome");
      String cognome = request.getParameter("cognome");
      String dataNascita = request.getParameter("dataNascita");
      String luogoNascita = request.getParameter("luogoNascita");

      try {
        Azienda azienda = new Azienda(email, password, nome, cognome, luogoNascita, dataNascita,
            denominazione, citta, cap, via, false);
        String telefono = request.getParameter("telefono");
        String sitoweb = request.getParameter("sitoWeb");
        String chiSiamo = request.getParameter("chiSiamo");
        
        if (telefono != null) {
          azienda.setTelefono(telefono);
        }
        if (sitoweb != null) {
          azienda.setSitoWeb(sitoweb);
        }
        if (chiSiamo != null) {
          azienda.setChiSiamo(chiSiamo);
        }
        
        String specifica = request.getParameter("specifiche");
        
        Convenzione convenzione = new Convenzione();
        convenzione.setSpecifica(specifica);
        DatabaseGu.addConvenzione(convenzione);
        DatabaseGu.addUser(azienda);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/success.jsp");
        dispatcher.forward(request, response);
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
    } else {
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/registrazione.jsp?errore=" + errore);
      dispatcher.forward(request, response);
    }
  }

  /**
   * Controllo parametri formato lato Server.
   * 
   * @param request,
   *          response
   * 
   * @author Caggiano Gianluca, Iannuzzi Nicola'
   */
  private boolean controllo(HttpServletRequest request, HttpServletResponse response) {
    
    String email = request.getParameter("email");
    //Controlla se l'azienda è già presente ne database
    try {
      Azienda a = DatabaseGu.getAziendaByEmail(email);
      if (a != null) {
        errore = "Azienda gia' presente nel sistema";
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    }

    String luogoNascita = request.getParameter("luogoNascita");
    luogoNascita = luogoNascita.trim();
    luogoNascita = luogoNascita.replace("'", " ");
    if ((!luogoNascita.matches(Utente.ALL_LETTERS)) 
        || (luogoNascita.length() < Utente.MIN_LUNGHEZZA_DUE)
        || (luogoNascita.length() > Utente.MAX_LUNGHEZZA_TRENTA)) {
      errore = "La citta' di nascita inserita non e' valida";
    }

    String dataNascita = request.getParameter("dataNascita");
    dataNascita = dataNascita.trim();
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    try {
      java.util.Date dataN = df.parse(dataNascita);
      Calendar cal = new GregorianCalendar();
      cal.setTime(dataN);
      Calendar oggi = Calendar.getInstance();
      
      //Controlla se l'utente e' minorenne
      int giorno = cal.get(Calendar.DAY_OF_MONTH);
      int mese = cal.get(Calendar.MONTH);
      int anno = cal.get(Calendar.YEAR) + Utente.MIN_ETA;
      GregorianCalendar mageta = new GregorianCalendar(anno,mese,giorno,0,0,0);
      mageta.set(Calendar.MILLISECOND, 0);
          
      if (oggi.getTimeInMillis() - mageta.getTimeInMillis() < 0) {
        errore = "Mi sembri un pochino troppo piccolo :-P";
      }

      //Controlla se l'utente è ultra centenario
      anno = cal.get(Calendar.YEAR) + Utente.MAX_ETA;
      GregorianCalendar centenario = new GregorianCalendar(anno,mese,giorno,0,0,0);
      centenario.set(Calendar.MILLISECOND, 0);
      
      if (oggi.getTimeInMillis() - centenario.getTimeInMillis() >= 0) {
        errore = "Mi sembri un pochino troppo grande :-P";
      }
  
    } catch (ParseException e) {
      errore = "Data di nascita errata";
    }

    String cognome = request.getParameter("cognome");
    cognome = cognome.trim();
    cognome = cognome.replace("'", " ");
    if ((!cognome.matches(Utente.ALL_LETTERS)) || (cognome.length() < Utente.MIN_LUNGHEZZA_DUE)
        || (cognome.length() > Utente.MAX_LUNGHEZZA_TRENTA)) {
      errore = "cognome non valido";
    }
    
    String nome = request.getParameter("nome");
    nome = nome.trim();
    if ((!nome.matches(Utente.ALL_LETTERS)) || ((nome.length() < Utente.MIN_LUNGHEZZA_DUE) 
        || (nome.length() > Utente.MAX_LUNGHEZZA_TRENTA))) {
      errore = "nome non valido";
    }
    
    String sitoWeb = request.getParameter("sitoWeb");
    sitoWeb = sitoWeb.trim();
    if (sitoWeb != null) {
      if (!sitoWeb.matches(Azienda.SITO_PATTERN)) {
        errore = "Indirizzo web non valido";
      }
    }
    
    String telefono = request.getParameter("telefono");
    telefono = telefono.trim();
    if (telefono != null) {
      if (!telefono.matches(Azienda.TELEFONO_PATTERN)) {
        errore = "Numero di telefono non valido";
      }
    }
    
    String via = request.getParameter("via");
    via = via.trim();
    via = via.replace("'", " ");
    if ((!via.matches(Utente.ALFANUMERIC)) 
        || (via.length() < Utente.MIN_LUNGHEZZA_DUE)
        || (via.length() > Utente.MAX_LUNGHEZZA_TRENTA)) {
      errore = "la strada inserita non e' valida";
    }
    
    String cap = request.getParameter("cap");
    cap = cap.trim();
    if (!cap.matches(Azienda.CAP_PATTERN)) {
      errore = "CAP non valido";
    }

    String citta = request.getParameter("citta");
    citta = citta.trim();
    citta = citta.replace("'", " ");
    if ((!citta.matches(Utente.ALL_LETTERS)) 
        || (citta.length() < Utente.MIN_LUNGHEZZA_DUE)
        || (citta.length() > Utente.MAX_LUNGHEZZA_TRENTA)) {
      errore = "La citta' inserita non e' valida";
    }

    String denominazione = request.getParameter("denominazione");
    denominazione = denominazione.trim();
    denominazione = denominazione.replace("'", " ");
    if ((!denominazione.matches(Utente.ALFANUMERIC)) 
        || (denominazione.length() < Utente.MIN_LUNGHEZZA_DUE)
        || (denominazione.length() > Utente.MAX_LUNGHEZZA_TRENTA)) {
      errore = "Ragione sociale non valida. ";
      errore = errore + "Se la denominazione dell'azienda contiene piu' di trenta caratteri";
      errore = errore + "inserire solo l'acronimo.";
    }

    String password = request.getParameter("password");
    password = password.trim();
    if (!password.matches(Utente.PASSWORD_PATTERN)) {
      errore = "Password non valida";
    }

    email = email.trim();
    if ((!email.matches(Azienda.EMAIL_PATTERN)) || email.length() > Utente.MAX_LUNGHEZZA_USER) {
      errore = "Email non valida";
    }

    if (errore.length() != 0) {
      return false;
    } else {
      return true;
    }

  }

}
