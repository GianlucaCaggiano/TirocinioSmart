package gestioneUtente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import storageLayer.DatabaseGu;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			Utente u = DatabaseGu.getUtenteById(email);
			//E' solo una prova.
			if(u!=null)
			{
				if(u.getPassword().equals(password))
				{
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				else
				{
					request.getRequestDispatcher("registrazione.jsp").forward(request, response);
				}
			}
			else
			{
				request.getRequestDispatcher("loginAdmin.jsp").forward(request, response);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
