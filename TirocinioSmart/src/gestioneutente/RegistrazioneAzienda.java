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
    email = email.trim();
    if (!email.matches(Azienda.EMAIL_PATTERN)) {
      errore = "Email non valida";
    }

    String password = request.getParameter("password");
    password = password.trim();
    if (!password.matches(Utente.PASSWORD_PATTERN)) {
      errore = "Password non valida";
    }

    String cap = request.getParameter("cap");
    cap = cap.trim();
    if (!cap.matches(Azienda.CAP_PATTERN)) {
      errore = "CAP non valido";
    }

    String telefono = request.getParameter("telefono");
    telefono = telefono.trim();
    if (telefono != null) {
      if (!telefono.matches(Azienda.TELEFONO_PATTERN)) {
        errore = "Numero di telefono non valido";
      }
    }

    String nome = request.getParameter("nome");
    nome = nome.trim();
    if (nome.length() < Utente.MIN_LUNGHEZZA_DUE || nome.length() > Utente.MAX_LUNGHEZZA_TRENTA) {
      errore = "nome non valido";
    }

    String cognome = request.getParameter("cognome");
    cognome = cognome.trim();
    if (cognome.length() < Utente.MIN_LUNGHEZZA_DUE
        || cognome.length() > Utente.MAX_LUNGHEZZA_TRENTA) {
      errore = "cognome non valido";
    }

    String denominazione = request.getParameter("denominazione");
    denominazione = denominazione.trim();
    if (denominazione.length() < Utente.MIN_LUNGHEZZA_DUE
        || denominazione.length() > Utente.MAX_LUNGHEZZA_TRENTA) {
      errore = "Ragione sociale non valida. ";
      errore = errore + "Se la denominazione dell'azienda contiene piu' di trenta caratteri";
      errore = errore + "inserire solo l'acronimo.";
    }

    String citta = request.getParameter("citta");
    citta = citta.trim();
    if (citta.length() < Utente.MIN_LUNGHEZZA_DUE || citta.length() > Utente.MAX_LUNGHEZZA_TRENTA) {
      errore = "La citta' inserita non e' valida";
    }

    String luogoNascita = request.getParameter("luogoNascita");
    luogoNascita = luogoNascita.trim();
    if (luogoNascita.length() < Utente.MIN_LUNGHEZZA_DUE
        || luogoNascita.length() > Utente.MAX_LUNGHEZZA_TRENTA) {
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

      if (oggi.get(Calendar.YEAR) - cal.get(Calendar.YEAR) <= Utente.MIN_ETA) {
        errore = "Mi sembri un pochino troppo piccolo :-P";
      }

      if (cal.after(oggi)) {
        errore = "Data di nascita successiva alla data odierna";
      }

    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    try {
      Azienda a = DatabaseGu.getAziendaByEmail(email);
      if (a != null) {
        errore = "Azienda gia' presente nel sistema";
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    }

    if (errore.length() != 0) {
      return false;
    } else {
      return true;
    }

  }

}
