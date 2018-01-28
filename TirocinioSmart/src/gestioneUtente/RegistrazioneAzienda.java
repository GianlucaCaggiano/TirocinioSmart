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
 * Servlet implementation class RegistrazioneAzienda
 * Gestisce la registrazione dell'Azienda
 * 
 * @author Iannuzzi Nicola'
 * 
 * @version 1.0
 */
@WebServlet("/RegistrazioneAzienda")
public class RegistrazioneAzienda extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private String errore;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneAzienda()
    {
        super();
    }

    /**
   	 * Richiama il metodo doPost per la registrazione di un Utente nel Database, Azienda
   	 * 
   	 * @param request, response
   	 * @throws ServletException, IOException
   	 * 
   	 * @author Iannuzzi Nicola'
   	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	/**
	 * Registra un utente nel database, Azienda
	 * 
	 * @param request, response
	 * @throws ServletException, IOException
	 * 
	 * @author Iannuzzi Nicola'
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		errore = "";
		if(controllo(request,response))
		{
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String denominazione = request.getParameter("denominazione");
			String citta = request.getParameter("citta");
			String cap = request.getParameter("cap");
			String via = request.getParameter("via");
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String dataNascita = request.getParameter("dataNascita");
			String luogoNascita = request.getParameter("luogoNascita");
		
		
			try 
			{
				Azienda azienda = new Azienda(email, password, nome, cognome, luogoNascita, dataNascita, denominazione, citta, cap, via, false);
				String telefono = request.getParameter("telefono");
				String sitoweb = request.getParameter("sitoWeb");
				String chiSiamo = request.getParameter("chiSiamo");
				String specifica = request.getParameter("specifiche");
				if(telefono!=null)
				{
					azienda.setTelefono(telefono);
				}
				if(sitoweb!=null)
				{
					azienda.setSitoWeb(sitoweb);
				}
				if(chiSiamo!=null)
				{
					azienda.setChiSiamo(chiSiamo);
				}
				Convenzione convenzione = new Convenzione();
				convenzione.setSpecifica(specifica);
				DatabaseGu.addConvenzione(convenzione);
				DatabaseGu.addUser(azienda);
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
	 * @author Caggiano Gianluca, Iannuzzi Nicola'
	 */
	private boolean controllo(HttpServletRequest request, HttpServletResponse response) 
	{
		String email = request.getParameter("email");
		email = email.trim();
		
		String password = request.getParameter("password");
		password = password.trim();
		if(!password.matches(Utente.PASSWORD_PATTERN))
		{
			errore = "Password non valida";
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
			Azienda a = DatabaseGu.getAziendaByEmail(email);
			if(a!=null)
			{	
				errore = "Azienda gia' presente nel sistema";
			}
		} catch (SQLException e1) {
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
