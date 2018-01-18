package gestioneUtente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import storageLayer.DatabaseGu;

/**
 * Servlet implementation class RegistrazioneStudente
 * Gestisce la registrazione dello Studente
 * 
 * @author Caggiano Gianluca, Iannuzzi Nicola'
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
		String matricola = request.getParameter("matricola");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String dataNascita = request.getParameter("dataNascita");
		String luogoNascita = request.getParameter("luogoNascita");
		Studente studente = new Studente(matricola, email, password, nome, cognome, dataNascita, luogoNascita, false);

		try {
			DatabaseGu.addUser(studente);
		}catch(SQLException e1)
		{
			e1.printStackTrace();
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

}
