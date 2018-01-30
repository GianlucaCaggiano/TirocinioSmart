package gestioneProgettoFormativo;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import storageLayer.DatabasePf;

/**
 * Servlet implementation class SottoscriviProgettoFormativo.
 * Gestisce l'update di una sottoscrizioneStu in ProgettoFormativo
 * 
 * @author Iannuzzi Nicola'
 * 
 * @version 1.0
 */
@WebServlet("/SottoscriviProgettoFormativo")
public class SottoscriviProgettoFormativo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SottoscriviProgettoFormativo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			DatabasePf.setSottoscrizioneStuProgetto(Integer.parseInt(id));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/areaPersonale.jsp");
			dispatcher.forward(request, response);
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
