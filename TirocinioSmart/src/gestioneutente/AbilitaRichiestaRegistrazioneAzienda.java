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
 * Servlet implementation class AbilitaRichiestaRegistrazioneAzienda. 
 * Gestisce l'update di un abilitato in Azienda
 * 
 * @author Iannuzzi Nicola'
 * 
 * @version 1.0
 */
@WebServlet("/AbilitaRichiestaRegistrazioneAzienda")
public class AbilitaRichiestaRegistrazioneAzienda extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * Costruttore della classe.
   * 
   * @see HttpServlet#HttpServlet()
   */
  public AbilitaRichiestaRegistrazioneAzienda() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * abilitazione di un'azienda da parte della segreteria tramite il metodo doGet().
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String success = "Azienda abilitata con successo";
    String id = request.getParameter("id");
    try {
      DatabaseGu.setAbilitatoAzienda(id);
      RequestDispatcher dispatcher = request
          .getRequestDispatcher("/ListaRichiestaRegistrazione.jsp?success=" + success);
      dispatcher.forward(request, response);
    } catch (NumberFormatException | SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * abilitazione di un'azienda da parte della segreteria tramite il metodo doPost().
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
