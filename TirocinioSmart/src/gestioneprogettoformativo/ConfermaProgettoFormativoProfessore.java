package gestioneprogettoformativo;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import storagelayer.DatabasePf;

/**
 * Servlet implementation class ConfermaProgettoFormativoProfessore. 
 * Gestisce l'update di un ConvalidaProf in ProgettoFormativo.
 * 
 * @author Iannuzzi Nicola'
 * 
 * @version 1.0
 */
@WebServlet("/ConfermaProgettoFormativoProfessore")
public class ConfermaProgettoFormativoProfessore extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * Costruttore della classe.
   * 
   * @see HttpServlet#HttpServlet()
   */
  public ConfermaProgettoFormativoProfessore() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Conferma da parte del professore di un progetto formativo chiamando il metodo doGet().
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String success = "Progetto formativo confermato con successo";
    String id = request.getParameter("id");
    try {
      DatabasePf.setConvalidaProfProgetto(Integer.parseInt(id));
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/ConfermaProgettoFormativoProfessore.jsp"
              + "?success=" + success);
      dispatcher.forward(request, response);
    } catch (NumberFormatException | SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Conferma da parte del professore di un progetto formativo chiamando il metodo doPost().
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
