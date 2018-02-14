package gestioneutente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import storagelayer.DatabaseGu;

/**
 * Servlet implementation class RegistrazioneProfessore. Gestisce la registrazione del Professore
 * 
 * @author Iannuzzi Nicola'
 * 
 * @version 1.0
 */
@WebServlet("/RegistrazioneProfessore")
public class RegistrazioneProfessore extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private String errore;

  /**
   * Default Costructor.
   * 
   * @see HttpServlet#HttpServlet()
   */
  public RegistrazioneProfessore() {
    super();
  }

  /**
   * Richiama il metodo doPost per la registrazione di un Utente nel Database, Professore.
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
   * Registra un utente nel database, Professore.
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
      String nome = request.getParameter("nome");
      String cognome = request.getParameter("cognome");
      String materia = request.getParameter("materia");

      try {
        Professore professore = new Professore(email, password, nome, cognome, false, materia);
        DatabaseGu.addUser(professore);
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

    String email = request.getParameter("email");
    
    //Controlla se il professore è già presente del database
    try {
      Professore p = DatabaseGu.getProfessoreByEmail(email);
      if (p != null) {
        errore = "Professore gia' presente nel sistema";
      }
    } catch (SQLException e1) {
      e1.printStackTrace();
    }

    String materia = request.getParameter("materia");
    materia = materia.trim();
    materia = materia.replace("'", " ");
    if ((!materia.matches(Utente.ALFANUMERIC)) || (materia.length() < Utente.MIN_LUNGHEZZA_DUE)
        || (materia.length() > Utente.MAX_LUNGHEZZA_TRENTA)) {
      errore = "materia non valida";
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

    String password = request.getParameter("password");
    password = password.trim();
    if (!password.matches(Utente.PASSWORD_PATTERN)) {
      errore = "Password non valida";
    }
    
    email = email.trim();
    if ((!email.matches(Professore.EMAIL_PATTERN)) || email.length() > Utente.MAX_LUNGHEZZA_USER) {
      errore = "Email non valida";
    }

    if (errore.length() != 0) {
      return false;
    } else {
      return true;
    }

  }

}
