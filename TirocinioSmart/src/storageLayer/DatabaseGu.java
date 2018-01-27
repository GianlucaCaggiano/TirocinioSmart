package storageLayer;

import java.sql.Connection;
import java.sql.Date;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gestioneUtente.*;

/**
 * Classe che fornisce i metodi per le interrogazioni al database.
 * 
 * @author Caggiano Gianluca, Iannuzzi Nicola'
 *
 * @version 1.0
 */
public class DatabaseGu 
{
	/**
	 * Registra un utente nel database.
	 * 
	 * @param utente
	 * @throws SQLException
	 * 
	 * @author Caggiano Gianluca, Iannuzzi Nicola'
	 */
	public synchronized static void addUser(Utente utente) throws SQLException
	{
		Connection connection = null;
		
		if(utente instanceof Studente) 
		{
			PreparedStatement psAddUtente = null;
			PreparedStatement psAddStudente = null;
			try {
				connection = Database.getConnection();
				psAddUtente = connection.prepareStatement(queryAddUtente);

				psAddUtente.setString(1, utente.getUser());
				psAddUtente.setString(2, utente.getPassword());
				psAddUtente.setString(3, utente.getNome());
				psAddUtente.setString(4, utente.getCognome());
				psAddUtente.setString(5, "ST");//ST sta ad indicare il tipo "Studente" nel database
				//AZ=Azienda, SR=Segreteria, PR=Professore
				psAddUtente.executeUpdate();

				connection.commit();

				Studente studente = (Studente) utente;
				
				psAddStudente = connection.prepareStatement(queryAddStudente);

				psAddStudente.setString(1, studente.getMatricola());
				psAddStudente.setString(2, studente.getUser());
				psAddStudente.setString(3, studente.getDataNascita());
				psAddStudente.setString(4, studente.getLuogoNascita());
				psAddStudente.setInt(5, 1);
				psAddStudente.executeUpdate();
				System.out.println(studente);
				connection.commit();
			}
			finally 
			{
				try 
				{
					if (psAddUtente != null)
					{
						psAddUtente.close();
					}
					
					if (psAddStudente != null)
					{
						psAddStudente.close();
					}
				} 
				finally 
				{
					Database.releaseConnection(connection);
				}
			}
		}
		
		if(utente instanceof Azienda)
		{
			PreparedStatement psAddUtente = null;
			PreparedStatement psAddAzienda = null;
			try {
				connection = Database.getConnection();
				psAddUtente = connection.prepareStatement(queryAddUtente);

				psAddUtente.setString(1, utente.getUser());
				psAddUtente.setString(2, utente.getPassword());
				psAddUtente.setString(3, utente.getNome());
				psAddUtente.setString(4, utente.getCognome());
				psAddUtente.setString(5, "AZ");//ST sta ad indicare il tipo "Studente" nel database
				//AZ=Azienda, SR=Segreteria, PR=Professore
				psAddUtente.executeUpdate();

				connection.commit();

				Azienda azienda = (Azienda) utente;
				
				psAddAzienda = connection.prepareStatement(queryAddAzienda);

				psAddAzienda.setString(1, azienda.getUser());
				psAddAzienda.setString(2, azienda.getLuogoNascita());
				psAddAzienda.setString(3, azienda.getDataNascita());
				psAddAzienda.setString(4, azienda.getDenominazione());
				psAddAzienda.setString(5, azienda.getCitta());
				psAddAzienda.setString(6, azienda.getCAP());
				psAddAzienda.setString(7, azienda.getVia());
				psAddAzienda.setInt(8, 0);
				if(azienda.getTelefono()!=null)
				{
					psAddAzienda.setString(9, azienda.getTelefono());
				}
				if(azienda.getSitoWeb()!=null)
				{
					psAddAzienda.setString(10, azienda.getSitoWeb());
				}
				if(azienda.getChiSiamo()!=null)
				{
					psAddAzienda.setString(11, azienda.getChiSiamo());
				}
				psAddAzienda.executeUpdate();
				System.out.println(azienda);
				connection.commit();
			}
			finally 
			{
				try 
				{
					if (psAddUtente != null)
					{
						psAddUtente.close();
					}
					
					if (psAddAzienda != null)
					{
						psAddAzienda.close();
					}
				} 
				finally 
				{
					Database.releaseConnection(connection);
				}
			}
		}
		
		if(utente instanceof Professore)
		{
			PreparedStatement psAddUtente = null;
			PreparedStatement psAddProfessore = null;
			try {
				connection = Database.getConnection();
				psAddUtente = connection.prepareStatement(queryAddUtente);

				psAddUtente.setString(1, utente.getUser());
				psAddUtente.setString(2, utente.getPassword());
				psAddUtente.setString(3, utente.getNome());
				psAddUtente.setString(4, utente.getCognome());
				psAddUtente.setString(5, "PR");//ST sta ad indicare il tipo "Studente" nel database
				//AZ=Azienda, SR=Segreteria, PR=Professore
				psAddUtente.executeUpdate();

				connection.commit();

				Professore professore = (Professore) utente;
				
				psAddProfessore = connection.prepareStatement(queryAddProfessore);

				psAddProfessore.setString(1, professore.getUser());
				psAddProfessore.setInt(2, 0);
				psAddProfessore.setString(3, professore.getMateria());
				psAddProfessore.executeUpdate();
				System.out.println(professore);
				connection.commit();
			}
			finally 
			{
				try 
				{
					if (psAddUtente != null)
					{
						psAddUtente.close();
					}
					
					if (psAddProfessore != null)
					{
						psAddProfessore.close();
					}
				} 
				finally 
				{
					Database.releaseConnection(connection);
				}
			}
			
		}
	}
	
	/**
	 * Registra una convenzione nel database.
	 * 
	 * @param convenzione
	 * @throws SQLException
	 * 
	 * @author Iannuzzi Nicola'
	 */
	public synchronized static void addConvenzione(Convenzione convenzione) throws SQLException
	{
		PreparedStatement psAddConvenzione = null;
		Connection connection = null;
		try {
			connection = Database.getConnection();
			psAddConvenzione = connection.prepareStatement(queryAddConvenzione);
			
			psAddConvenzione.setDate(1, new Date(Calendar.getInstance().getTime().getTime()));
			psAddConvenzione.setString(2, convenzione.getSpecifica());
			psAddConvenzione.executeUpdate();

			connection.commit();
		}
		finally 
		{
			try 
			{
				if (psAddConvenzione != null)
				{
					psAddConvenzione.close();
				}
				
				if (psAddConvenzione != null)
				{
					psAddConvenzione.close();
				}
			} 
			finally 
			{
				Database.releaseConnection(connection);
			}
		}
	}

	/**
	 * Restituisce ,se esiste, un oggetto Utente data la user di accesso.
	 * @param user
	 * @return {@code null} se l'utente non esiste, {@code Oggetto Utente } altrimenti.
	 * @throws SQLException
	 * 
	 * @author Caggiano Gianluca
	 */
	public synchronized static Utente getUtenteById(String user) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Utente u = null; 
		
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(queryGetUtenteById);
			preparedStatement.setString(1, user);
			
			ResultSet rs = preparedStatement.executeQuery();
			connection.commit();

			if (rs.next())
			{
				String tipo = rs.getString("Tipo");
				if(tipo.equals("ST"))
				{
					u = new Studente();
					u.setUser(rs.getString("User"));
					u.setPassword(rs.getString("Password"));
				}
				if(tipo.equals("AZ"))
				{
					u = new Azienda();
					u.setUser(rs.getString("User"));
					u.setPassword(rs.getString("Password"));
				}
				if(tipo.equals("PR"))
				{
					u = new Professore();
					u.setUser(rs.getString("User"));
					u.setPassword(rs.getString("Password"));
				}
			}
		}
		finally 
		{
			try 
			{
				if (preparedStatement != null)
					preparedStatement.close();
			} finally 
			{
				Database.releaseConnection(connection);
			}
		}
		return u;
	}
	
	/**
	 * Restituisce ,se esiste, un oggetto Studente data una email.
	 * 
	 * @param email
	 * @return {@code null} se l'utente non esiste, {@code Oggetto Studente } altrimenti.
	 * @throws SQLException
	 * 
	 * @author Caggiano Gianluca
	 */
	public synchronized static Studente getStudenteByEmail(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Studente studente = new Studente();

		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(queryGetStudenteEmail);
			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();
			connection.commit();

			while (rs.next()) {
				studente.setUser(rs.getString("User"));
				studente.setPassword(rs.getString("Password"));
				studente.setNome(rs.getString("Nome"));
				studente.setCognome(rs.getString("Cognome"));
				studente.setMatricola(rs.getString("Matricola"));
				studente.setDataNascita(rs.getString("DataNascita"));
				studente.setLuogoNascita(rs.getString("LuogoNascita"));
				studente.setAbilitato(rs.getBoolean("abilitato"));
			}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				Database.releaseConnection(connection);
			}
		}
		if (studente.getUser() == null)
			return null;
		else
			return studente;
	}
	
	/**
	 * Restituisce, se esiste, un oggetto Studente data una matricola.
	 * 
	 * @param matricola
	 * @return {@code null} se l'utente non esiste, {@code Oggetto Studente } altrimenti.
	 * @throws SQLException
	 * 
	 * @author Caggiano Gianluca
	 */
	public synchronized static Studente getStudenteByMatricola(String matricola) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Studente studente = new Studente();

		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(queryGetStudenteMatricola);
			preparedStatement.setString(1, matricola);

			ResultSet rs = preparedStatement.executeQuery();
			connection.commit();

			while (rs.next()) {
				studente.setUser(rs.getString("User"));
				studente.setPassword(rs.getString("Password"));
				studente.setNome(rs.getString("Nome"));
				studente.setCognome(rs.getString("Cognome"));
				studente.setMatricola(rs.getString("Matricola"));
				studente.setDataNascita(rs.getString("DataNascita"));
				studente.setLuogoNascita(rs.getString("LuogoNascita"));
				studente.setAbilitato(rs.getBoolean("abilitato"));
			}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				Database.releaseConnection(connection);
			}
		}
		if (studente.getUser() == null)
			return null;
		else
			return studente;
	}
	
	/**
	 * Restituisce ,se esiste, un oggetto Azienda data una email.
	 * 
	 * @param email
	 * @return {@code null} se l'utente non esiste, {@code Oggetto Azienda } altrimenti.
	 * @throws SQLException
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public synchronized static Azienda getAziendaByEmail(String email) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Azienda azienda = new Azienda();

		try 
		{
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(queryGetAziendaEmail);
			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();
			connection.commit();

			while (rs.next())
			{
				azienda.setUser(rs.getString("User"));
				azienda.setPassword(rs.getString("Password"));
				azienda.setNome(rs.getString("Nome"));
				azienda.setCognome(rs.getString("Cognome"));
				azienda.setLuogoNascita(rs.getString("LuogoNascita"));
				azienda.setDataNascita(rs.getString("DataNascita"));
				azienda.setDenominazione(rs.getString("Denominazione"));
				azienda.setCitta(rs.getString("Citta"));
				azienda.setCAP(rs.getString("CAP"));
				azienda.setVia(rs.getString("Via"));
				azienda.setAbilitato(rs.getBoolean("abilitato"));
			}
		} finally 
		{
			try 
			{
				if (preparedStatement != null)
					preparedStatement.close();
			} 
			finally 
			{
				Database.releaseConnection(connection);
			}
		}
		if (azienda.getUser() == null)
			return null;
		else
			return azienda;
	}
	
	/**
	 * Restituisce, se esiste, un oggetto Professore data una email.
	 * 
	 * @param email
	 * @return {@code null} se l'utente non esiste, {@code Oggetto Professore } altrimenti.
	 * @throws SQLException
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public synchronized static Professore getProfessoreByEmail(String email) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Professore professore = new Professore();

		try 
		{
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(queryGetProfessoreEmail);
			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();
			connection.commit();

			while (rs.next())
			{
				professore.setUser(rs.getString("User"));
				professore.setPassword(rs.getString("Password"));
				professore.setNome(rs.getString("Nome"));
				professore.setCognome(rs.getString("Cognome"));
				professore.setMateria(rs.getString("Materia"));
				professore.setAutorizzo(rs.getBoolean("Autorizzato"));
			}
		} finally 
		{
			try 
			{
				if (preparedStatement != null)
					preparedStatement.close();
			} 
			finally 
			{
				Database.releaseConnection(connection);
			}
		}
		if (professore.getUser() == null)
			return null;
		else
			return professore;
	}
	
	/**
	 * Restituisce, se esiste, un oggetto Segreteria dato uno username.
	 * 
	 * @param email
	 * @return {@code null} se l'utente non esiste, {@code Oggetto Segreteria} altrimenti.
	 * @throws SQLException
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public synchronized static Segreteria getSegreteriaByUser(String username) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Segreteria segreteria = new Segreteria();

		try 
		{
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(queryGetSegreteriaUsername);
			preparedStatement.setString(1, username);

			ResultSet rs = preparedStatement.executeQuery();
			connection.commit();

			while (rs.next())
			{
				segreteria.setUser(rs.getString("User"));
				segreteria.setPassword(rs.getString("Password"));
				segreteria.setNome(rs.getString("Nome"));
				segreteria.setCognome(rs.getString("Cognome"));
				segreteria.setEmail(rs.getString("Email"));
			}
		} finally 
		{
			try 
			{
				if (preparedStatement != null)
					preparedStatement.close();
			} 
			finally 
			{
				Database.releaseConnection(connection);
			}
		}
		if (segreteria.getUser() == null)
			return null;
		else
			return segreteria;
	}
	
	private static String queryAddUtente;
	private static String queryAddStudente;
	private static String queryAddAzienda;
	private static String queryAddProfessore;
	private static String queryAddConvenzione;
	private static String queryGetUtenteById;
	private static String queryGetStudenteEmail;
	private static String queryGetStudenteMatricola;
	private static String queryGetAziendaEmail;
	private static String queryGetProfessoreEmail;
	private static String queryGetSegreteriaUsername;
	
	static 
	{
		//Query universale per tutti gli utenti
		queryAddUtente = "Insert into utente (User, Password, Nome, Cognome, Tipo) VALUES (?,?,?,?,?);";
		queryAddStudente = "Insert into studente (Matricola, Email, DataNascita, LuogoNascita, abilitato) VALUES (?,?,?,?,?);";
		queryAddAzienda = "Insert into azienda (Email, LuogoNascita, DataNascita, Denominazione, Citta, CAP, Via, abilitato, Telefono, SitoWeb, ChiSiamo) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
		queryAddProfessore = "Insert into professore (Email, Autorizzato, Materia) VALUES (?,?,?);";
		queryAddConvenzione = "Insert into convenzione (Data, Specifiche) VALUES (?,?);";
		
		queryGetUtenteById = "SELECT * From utente WHERE utente.User=?;";
		queryGetStudenteEmail = "SELECT * From utente,studente WHERE studente.Email=utente.User AND utente.User=?;";
		queryGetStudenteMatricola = "SELECT * From utente,studente WHERE studente.Email=utente.User AND studente.Matricola=?;";
		queryGetAziendaEmail = "SELECT * From utente,azienda WHERE azienda.Email=utente.User AND azienda.Email=?;";
		queryGetProfessoreEmail = "SELECT * From utente, professore WHERE utente.User = professore.Email AND professore.Email=?";
		queryGetSegreteriaUsername = "SELECT * From utente, segreteria WHERE utente.User = segreteria.Username AND segreteria.Username=?";
	}
}