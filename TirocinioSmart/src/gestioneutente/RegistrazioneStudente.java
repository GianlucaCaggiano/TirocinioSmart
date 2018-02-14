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
 * Servlet implementation class RegistrazioneStudente Gestisce la registrazione dello Studente
 * 
 * @author Caggiano Gianluca, Iannuzzi Nicola'
 * 
 * @version 1.0
 */
@WebServlet("/RegistrazioneStudente")
public class RegistrazioneStudente extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private String errore;

  /**
   * Default constructor.
   * 
   * @see HttpServlet#HttpServlet()
   */
  public RegistrazioneStudente() {
    super();
  }

  /**
   * Richiama il metodo doPost per la registrazione di un Utente nel Database, Studente.
   * 
   * @param request,
   *          response
   * @throws ServletException,
   *           IOException
   * 
   * @author Caggiano Gianluca, Iannuzzi Nicola'
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  /**
   * Registra un utente nel database, Studente.
   * 
   * @param request,
   *          response
   * @throws ServletException,
   *           IOException
   * 
   * @author Caggiano Gianluca, Iannuzzi Nicola'
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    errore = "";
    if (controllo(request, response)) {
      String matricola = request.getParameter("matricola");
      String email = request.getParameter("email");
      String password = request.getParameter("password");
      String nome = request.getParameter("nome");
      String cognome = request.getParameter("cognome");
      String dataNascita = request.getParameter("dataNascita");
      String luogoNascita = request.getParameter("luogoNascita");

      try {
        Studente studente = new Studente(matricola, email, password, nome, cognome, dataNascita,
            luogoNascita, false);
        DatabaseGu.addUser(studente);
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
   * @author Caggiano Gianluca
   */
  private boolean controllo(HttpServletRequest request, HttpServletResponse response) {
    String matricola = request.getParameter("matricola");
    String email = request.getParameter("email");
    
    //Controlla se l'utente è già presente nel database
    try {
      Studente s = DatabaseGu.getStudenteByEmail(email);
      Studente s2 = DatabaseGu.getStudenteByMatricola(matricola);
      if (s != null && s2 != null) {
        errore = "Utente gia' presente nel sistema";
      }
    } catch (SQLException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    //Controllo se il luogo di nascita rispetta il formato
    String luogoNascita = request.getParameter("luogoNascita");
    luogoNascita = luogoNascita.trim();
    luogoNascita = luogoNascita.replace("'", " ");
    if ((!luogoNascita.matches(Utente.ALL_LETTERS)) 
        || (luogoNascita.length() < Utente.MIN_LUNGHEZZA_DUE)
        || (luogoNascita.length() > Utente.MAX_LUNGHEZZA_TRENTA)) {
      errore = "Citta' non valida";
    }

    //Controllo sulla data di nascita
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
        errore = "Mi sembri un pochino troppo piccolo per essere uno studente universitario :-P";
      }

      //Controlla se l'utente è ultra centenario
      anno = cal.get(Calendar.YEAR) + Utente.MAX_ETA;
      GregorianCalendar centenario = new GregorianCalendar(anno,mese,giorno,0,0,0);
      centenario.set(Calendar.MILLISECOND, 0);
      
      if (oggi.getTimeInMillis() - centenario.getTimeInMillis() >= 0) {
        errore = "Mi sembri un pochino troppo grande :-P";
      }
      
      if (cal.after(oggi)) {
        errore = "Data di nascita successiva alla data odierna";
      }

    } catch (ParseException e) {
      errore = "Data di nascita errata";
    }

    //Controllo sul formato del cognome
    String cognome = request.getParameter("cognome");
    cognome = cognome.trim();
    cognome = cognome.replace("'", " ");
    if ((!cognome.matches(Utente.ALL_LETTERS)) || (cognome.length() < Utente.MIN_LUNGHEZZA_DUE)
        || (cognome.length() > Utente.MAX_LUNGHEZZA_TRENTA)) {
      errore = "cognome non valido";
    }
    
    //Controllo sul formato del nome
    String nome = request.getParameter("nome");
    nome = nome.trim();
    if ((!nome.matches(Utente.ALL_LETTERS)) || ((nome.length() < Utente.MIN_LUNGHEZZA_DUE) 
        || (nome.length() > Utente.MAX_LUNGHEZZA_TRENTA))) {
      errore = "nome non valido";
    }
    
    //Controllo sul formato della password
    String password = request.getParameter("password");
    password = password.trim();
    if (!password.matches(Utente.PASSWORD_PATTERN)) {
      errore = "Password non valida";
    }

    //Controllo sul formato dell'email
    email = email.trim();
    if ((!email.matches(Studente.EMAIL_PATTERN)) || email.length() > Utente.MAX_LUNGHEZZA_USER) {
      errore = "Email non valida";
    }

    //Controllo sul formato della matricola
    matricola = matricola.trim();
    if (!matricola.matches(Studente.MATRICOLA_PATTERN)) {
      errore = "matricola non valida";
    }
    
    //Verifica se c'è stato un errore
    if (errore.length() != 0) {
      return false;
    } else {
      return true;
    }

  }

}
