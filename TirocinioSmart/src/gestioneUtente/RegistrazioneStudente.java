package gestioneUtente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrazioneStudente
 * Gestisce la registrazione dello Studente
 * 
 * @author Caggiano Gianluca
 * 
 * @version 1.0
 */
@WebServlet("/RegistrazioneStudente")
public class RegistrazioneStudente extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * Default constructor.
     *  
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneStudente()
    {
        super();
        // TODO Auto-generated constructor stub
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
		
	}

}
