
package storageLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import gestioneProgettoFormativo.RichiestaTirocinio;
import gestioneUtente.Azienda;
import gestioneUtente.Professore;
import gestioneUtente.Studente;
import gestioneUtente.Utente;

/**
 * Classe che fornisce i metodi per le interrogazioni al database per le classi del package gestioneProgettoFormativo.
 * 
 * @author Caggiano Gianluca
 *
 * @version 1.0
 */
public class DatabasePf 
{

	/**
	 * 
	 * @param La richiesta di tirocinio da memorizzare
	 * @return {@code true} se la aggiunta della richiesta e' ok, {@code false} altrimenti.
	 * @throws SQLException
	 * 
	 * @author Gianluca Caggiano, Iannuzzi Nicola'
	 */
	public synchronized static int AddRichiesta(RichiestaTirocinio rt, Studente s) throws SQLException
	{
		Connection connection = null;
		
		int id = -1;
		PreparedStatement psAddRichiesta = null;
		PreparedStatement psUpdateStudente = null;
		try {
			connection = Database.getConnection();
			psAddRichiesta = connection.prepareStatement(queryAddRichiesta);
			
			psAddRichiesta.setString(1, rt.getAzienda().getUser());
			psAddRichiesta.setString(2, rt.getProfessore().getUser());
			psAddRichiesta.executeUpdate();
			connection.commit();
			
			psUpdateStudente = connection.prepareStatement(queryUpdateStudente);
			id=getIDMaxRichiestaTirocinio();
			psUpdateStudente.setInt(1, id);
			psUpdateStudente.setString(2, s.getUser());
			psUpdateStudente.executeUpdate();
			connection.commit();
		}
		finally 
		{
			try 
			{
				if (psAddRichiesta != null)
				{
					psAddRichiesta.close();
				}
				
				if (psUpdateStudente != null)
				{
					psUpdateStudente.close();
				}
			} 
			finally 
			{
				Database.releaseConnection(connection);
			}
		}
		return id;
	}
	
	/**
	 * Restituisce, se esiste, un oggetto di tipo RichiestaTirocinio dato l'identificativo.
	 * 
	 * @param id
	 * @return {@code null} se la richiesta di tirocinio con tale id non esiste, {@code Oggetto RichiestaTirocinio } altrimenti.
	 * @throws SQLException
	 * 
	 * @author Caggiano Gianluca
	 */
	public synchronized static RichiestaTirocinio getRichiestaByID(int id) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		RichiestaTirocinio richiesta = new RichiestaTirocinio();

		try 
		{
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(queryGetRichiestaById);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();
			connection.commit();

			while (rs.next())
			{
				richiesta.setId(rs.getInt("ID"));
				richiesta.setAzienda(DatabaseGu.getAziendaByEmail(rs.getString("AziendaEmail")));
				richiesta.setProfessore(DatabaseGu.getProfessoreByEmail(rs.getString("ProfessoreEmail")));
				richiesta.setConvalidaAzienda(rs.getBoolean("ConvalidaAzienda"));
				richiesta.setConvalidaProf(rs.getBoolean("ConvalidaProf"));
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
		if (richiesta.getId() == -1)
			return null;
		else
			return richiesta;
	}
	
	/**
	 * Restituisce l'ultima Richiesta Tirocinio creata in ordine temporale.
	 * 
	 * @return {@code -1} non e' presente nessuna convenzione, {@code Oggetto Convenzione} altrimenti.
	 * @throws SQLException
	 */
	private synchronized static int getIDMaxRichiestaTirocinio() throws SQLException
	{
		Connection connection = null;
		java.sql.Statement statement = null;
		
		int id = -1;
		try 
		{
			connection = Database.getConnection();
			statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery(queryGetMaxRichiestaTirocinio);
			
			if(rs.next())
			{
				id = rs.getInt(1);
			}
		}
		finally
		{
			try 
			{
				if (statement != null)
					statement.close();
			} 
			finally 
			{
				Database.releaseConnection(connection);
			}
		}
		return id;
	}
	
	/**
	 * Restituisce un ArrayList di oggetti di tipo RichiestaTirocinio dato l'email dell'Azienda.
	 * 
	 * @param email
	 * @return {@code ArrayList<RichiestaTirocinio>}.
	 * @throws SQLException
	 * 
	 * @author Caggiano Gianluca, Iannuzzi Nicola'
	 */
	public synchronized static ArrayList<RichiestaTirocinio> doRetrieveRichiesteAziende(String email) throws SQLException
	{
		Connection connection = null;
		Statement statement = null;

		RichiestaTirocinio richiesta = null;
		ArrayList<RichiestaTirocinio> arrayRichiesta = new ArrayList<RichiestaTirocinio>();
		try 
		{
			connection = Database.getConnection();
			statement = connection.createStatement();

			ResultSet rs = statement.executeQuery("SELECT * FROM richiestatirocinio WHERE richiestatirocinio.AziendaEmail='"+email+"'");
			connection.commit();

			while (rs.next())
			{
				richiesta = new RichiestaTirocinio();
				richiesta.setId(rs.getInt("ID"));
				richiesta.setAzienda(DatabaseGu.getAziendaByEmail(rs.getString("AziendaEmail")));
				richiesta.setProfessore(DatabaseGu.getProfessoreByEmail(rs.getString("ProfessoreEmail")));
				richiesta.setConvalidaAzienda(rs.getBoolean("ConvalidaAzienda"));
				richiesta.setConvalidaProf(rs.getBoolean("ConvalidaProf"));
				
				arrayRichiesta.add(richiesta);
			}
		} finally 
		{
			try 
			{
				if (statement != null)
					statement.close();
			} 
			finally 
			{
				Database.releaseConnection(connection);
			}
		}
		return arrayRichiesta;
	}
	
	/**
	 * Restituisce un ArrayList di oggetti di tipo RichiestaTirocinio dato l'email del Professore.
	 * 
	 * @param email
	 * @return {@code ArrayList<RichiestaTirocinio>}.
	 * @throws SQLException
	 * 
	 * @author Caggiano Gianluca, Iannuzzi Nicola'
	 */
	public synchronized static ArrayList<RichiestaTirocinio> doRetrieveRichiesteProfessore(String email) throws SQLException
	{
		Connection connection = null;
		Statement statement = null;

		RichiestaTirocinio richiesta = null;
		ArrayList<RichiestaTirocinio> arrayRichiesta = new ArrayList<RichiestaTirocinio>();
		try 
		{
			connection = Database.getConnection();
			statement = connection.createStatement();

			ResultSet rs = statement.executeQuery("SELECT * FROM richiestatirocinio WHERE richiestatirocinio.ProfessoreEmail='"+email+"'");
			connection.commit();

			while (rs.next())
			{
				richiesta = new RichiestaTirocinio();
				richiesta.setId(rs.getInt("ID"));
				richiesta.setAzienda(DatabaseGu.getAziendaByEmail(rs.getString("AziendaEmail")));
				richiesta.setProfessore(DatabaseGu.getProfessoreByEmail(rs.getString("ProfessoreEmail")));
				richiesta.setConvalidaAzienda(rs.getBoolean("ConvalidaAzienda"));
				richiesta.setConvalidaProf(rs.getBoolean("ConvalidaProf"));
				
				arrayRichiesta.add(richiesta);
			}
		} finally 
		{
			try 
			{
				if (statement != null)
					statement.close();
			} 
			finally 
			{
				Database.releaseConnection(connection);
			}
		}
		return arrayRichiesta;
	}
	
	/**
	 * Restituisce, se esiste, un oggetto di tipo Studente dato l'identificativo della Richiesta.
	 * 
	 * @param id
	 * @return {@code Studente}.
	 * @throws SQLException
	 * 
	 * @author Caggiano Gianluca, Iannuzzi Nicola'
	 */
	public synchronized static Studente getStudenteByIDRichiesta(int id) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Studente s = null; 
		
		try {
			connection = Database.getConnection();
			preparedStatement = connection.prepareStatement(queryGetStudenteByIDRichiesta);
			preparedStatement.setInt(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();
			connection.commit();

			if (rs.next())
			{
				s = new Studente();
				s.setUser(rs.getString("User"));
				s.setPassword(rs.getString("Password"));
				s.setNome(rs.getString("Nome"));
				s.setCognome(rs.getString("Cognome"));
				s.setMatricola(rs.getString("Matricola"));
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
		return s;
	}
	
	private static String queryAddRichiesta;
	private static String queryGetRichiestaById;
	private static String queryUpdateStudente;
	private static String queryGetMaxRichiestaTirocinio;
	private static String queryGetStudenteByIDRichiesta;
	
	static {
		
		queryAddRichiesta = "INSERT INTO richiestatirocinio (AziendaEmail, ProfessoreEmail) VALUES (?,?);";
		
		queryUpdateStudente= "UPDATE studente SET RichiestaTirocinioID=? WHERE studente.Email=?";
		
		queryGetRichiestaById = "SELECT * FROM richiestatirocinio WHERE richiestatirocinio.ID=?";
		queryGetMaxRichiestaTirocinio = "SELECT MAX(ID) FROM richiestatirocinio";
		queryGetStudenteByIDRichiesta = "SELECT * FROM studente, utente WHERE studente.Email=utente.User AND studente.RichiestaTirocinioID=?";
	}
}
