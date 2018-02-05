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
 * Servlet implementation class ConfermaRichiestaTirocinio.
 * Gestisce l'update di una ConvalidaAzienda in RichiestaTirocinio
 * 
 * @author Iannuzzi Nicola', Gianluca Caggiano
 * 
 * @version 1.0
 */
@WebServlet("/ConfermaRichiestaAzienda")
public class ConfermaRichiestaAzienda extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * Costruttore della classe.
   * 
   * @see HttpServlet#HttpServlet()
   */
  public ConfermaRichiestaAzienda() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * Conferma dell'azienda di una richiesta di tirocinio chiamando il metodo doGet().
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String success = "Richiesta accettata con successo";
    String id = request.getParameter("id");
    try {
      DatabasePf.setConvalidaAzienda(Integer.parseInt(id));
      RequestDispatcher dispatcher = request.getRequestDispatcher("/listaRichiesteAzienda.jsp"
          + "?success=" + success);
      dispatcher.forward(request, response);
    } catch (NumberFormatException | SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Conferma da partedell'azienda di una richiesta di tirocinio chiamando il metodo doPost().
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
