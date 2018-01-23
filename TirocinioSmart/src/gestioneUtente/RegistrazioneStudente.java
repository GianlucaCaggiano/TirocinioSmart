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
    
	private String errore;
	
    /**
     * Default constructor.
     *  
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneStudente()
    {
        super();
    }

    /**
	 * Richiama il metodo doPost per la registrazione di un Utente nel Database, Studente
	 * 
	 * @param request, response
	 * @throws ServletException, IOException
	 * 
	 * @author Caggiano Gianluca, Iannuzzi Nicola'
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	/**
	 * Registra un utente nel database, Studente
	 * 
	 * @param request, response
	 * @throws ServletException, IOException
	 * 
	 * @author Caggiano Gianluca, Iannuzzi Nicola'
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		errore = "";
		if(controllo(request,response))
		{
			String matricola = request.getParameter("matricola");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String dataNascita = request.getParameter("dataNascita");
			String luogoNascita = request.getParameter("luogoNascita");
		
		
			try 
			{
				Studente studente = new Studente(matricola, email, password, nome, cognome, dataNascita, luogoNascita, false);
				DatabaseGu.addUser(studente);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
			}
			catch(SQLException e1)
			{
				e1.printStackTrace();
			}
		}
		else
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("/registrazione.jsp?errore="+errore);
			dispatcher.forward(request, response);
		}
		
	}
	
	/**
	 * Controllo parametri formato lato Server
	 * 
	 * @param request, response
	 * 
	 * @author Caggiano Gianluca
	 */
	private boolean controllo(HttpServletRequest request, HttpServletResponse response) 
	{
		String email = request.getParameter("email");
		email = email.trim();
		if(!email.matches(Studente.EMAIL_PATTERN))
		{
			errore = "Email non valida";
		}
		
		String password = request.getParameter("password");
		password = password.trim();
		if(!password.matches(Utente.PASSWORD_PATTERN))
		{
			errore = "Password non valida";
		}
		
		String matricola = request.getParameter("matricola");
		matricola = matricola.trim();
		if(!matricola.matches(Studente.MATRICOLA_PATTERN))
		{
			errore = "matricola non valida";
		}
		
		String nome = request.getParameter("nome");
		nome = nome.trim();
		if(nome.length() < Utente.MIN_LUNGHEZZA_NOME || nome.length() > Utente.MAX_LUNGHEZZA_NOME)
		{
			errore = "nome non valido";
		}
		
		String cognome = request.getParameter("cognome");
		cognome = cognome.trim();
		if(cognome.length() < Utente.MIN_LUNGHEZZA_NOME || cognome.length() > Utente.MAX_LUNGHEZZA_NOME)
		{
			errore = "cognome non valido";
		}
		
		try 
		{
			Studente s = DatabaseGu.getStudenteByEmail(email);
			Studente s2= DatabaseGu.getStudenteByMatricola(matricola);
			if(s!=null && s2!=null)
			{	
				errore = "Utente gia' presente nel sistema";
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(errore.length()!=0)
		{
			return false;
		}
		else
		{
			return true;
		}
		
	}

}
