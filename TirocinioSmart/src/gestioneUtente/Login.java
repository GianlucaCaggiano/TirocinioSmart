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
 * @author Caggiano Gianluca, Iannuzzi Nicola'
 * 
 * @version 1.0
 */
@WebServlet("/Login")
public class Login extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login()
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String errore="Email o password errati";
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try 
		{
			Utente u = DatabaseGu.getUtenteById(email);
			
			if(u!=null)
			{
				if(u.getPassword().equals(password))
				{
					if(u instanceof Studente)
					{
						Studente s = DatabaseGu.getStudenteByEmail(email);
						if(s.isAbilitato())
						{
							s.setAutenticato(true);
							System.out.println(s);
							HttpSession session = request.getSession();
							session.setAttribute("studente", s);
							request.getRequestDispatcher("areaPersonaleStudente.jsp").forward(request, response);
						}
						else
						{
							request.getRequestDispatcher("login.jsp?errore=L'account a cui si sta tentanto di accedere e' disabilitato. Contatta la segreteria per maggiori informazioni").forward(request, response);
						}
					}
					if(u instanceof Azienda)
					{
						Azienda a = DatabaseGu.getAziendaByEmail(email);
						if(a.isAbilitato())
						{
							a.setAutenticato(true);
							HttpSession session = request.getSession();
							session.setAttribute("azienda", a);
							request.getRequestDispatcher("areaPersonaleAzienda.jsp").forward(request, response);
						}
						else
						{
							request.getRequestDispatcher("login.jsp?errore=L'account a cui si sta tentanto di accedere e' disabilitato. Contatta la segreteria per maggiori informazioni").forward(request, response);
						}
					}
					if(u instanceof Professore)
					{
						Professore p = DatabaseGu.getProfessoreByEmail(email);
						if(p.isAutorizzo())
						{
							p.setAutenticato(true);
							HttpSession session = request.getSession();
							session.setAttribute("professore", p);
							request.getRequestDispatcher("areaPersonaleProfessore.jsp").forward(request, response);
						}
						else
						{
							request.getRequestDispatcher("login.jsp?errore=L'account a cui si sta tentanto di accedere e' disabilitato. Contatta la segreteria per maggiori informazioni").forward(request, response);
						}
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
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

}
