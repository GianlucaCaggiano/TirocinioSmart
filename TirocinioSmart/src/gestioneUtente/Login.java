package gestioneUtente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import storageLayer.DatabaseGu;

/**
 * Servlet implementation class Login.
 * Gestisce il login di tutti gli utenti tranne Segreteria.
 * 
 * @author Caggiano Gianluca
 * 
 * @version 1.0
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
		String errore="Email o password errati";
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			Utente u = DatabaseGu.getUtenteById(email);
			
			if(u!=null)
			{
				if(u.getPassword().equals(password))
				{
					//E' solo una prova.
					if(u instanceof Studente)
					{
						Studente s = DatabaseGu.getStudenteByEmail(email);
						HttpSession session = request.getSession();
						session.setAttribute("studente", s);
						request.getRequestDispatcher("registrazione.jsp").forward(request, response);
					}
					if(u instanceof Azienda)
					{
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
					if(u instanceof Professore)
					{
						request.getRequestDispatcher("loginAdmin.jsp").forward(request, response);
					}
					
				}
				else
				{
					request.getRequestDispatcher("login.jsp?errore="+errore).forward(request, response);
				}
			}
			else
			{
				request.getRequestDispatcher("login.jsp?errore="+errore).forward(request, response);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
