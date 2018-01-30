
package storageLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import gestioneProgettoFormativo.ProgettoFormativo;
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
	 * 
	 * @param Il Progetto Formativo da memorizzare
	 * @return {@code true} se la aggiunta del Progetto e' ok, {@code false} altrimenti.
	 * @throws SQLException
	 * 
	 * @author Gianluca Caggiano, Iannuzzi Nicola'
	 */
	public synchronized static boolean AddProgettoFormativo(ProgettoFormativo progettoFormativo) throws SQLException
	{
		Connection connection = null;
		
		PreparedStatement psAddProgettoFormativo = null;
		try {
			connection = Database.getConnection();
			psAddProgettoFormativo= connection.prepareStatement(queryAddProgettoFormativo);
			
			psAddProgettoFormativo.setString(1, progettoFormativo.getAzienda().getUser());
			psAddProgettoFormativo.setString(2, progettoFormativo.getSegreteria().getUser());
			psAddProgettoFormativo.setString(3, progettoFormativo.getStudente().getMatricola());
			psAddProgettoFormativo.setString(4, progettoFormativo.getProfessore().getUser());
			psAddProgettoFormativo.setString(5, progettoFormativo.getObiettivi());
			psAddProgettoFormativo.setString(6, progettoFormativo.getDataInizio());
			psAddProgettoFormativo.setString(7, progettoFormativo.getDataFine());
			psAddProgettoFormativo.executeUpdate();
			connection.commit();
		}
		finally 
		{
			try 
			{
				if (psAddProgettoFormativo != null)
				{
					psAddProgettoFormativo.close();
				}
			} 
			finally 
			{
				Database.releaseConnection(connection);
			}
		}
		return true;
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
	 * Setta in Richiesta Tirocinio 1 ConvalidaAzienda
	 * 
	 * @param id
	 * @return boolean
	 * @throws SQLException
	 * 
	 * @author Gianluca Caggiano, Iannuzzi Nicola'
	 */
	public synchronized static boolean setConvalidaAzienda(int id) throws SQLException
	{
		Connection connection = null;
		Statement statement = null;
		
		String query="UPDATE richiestatirocinio SET ConvalidaAzienda=1 WHERE richiestatirocinio.ID="+id+"";
		
		try {
			connection=Database.getConnection();
			statement=connection.createStatement();
			
			statement.executeUpdate(query);
			connection.commit();
		}
		finally {
			try {
				if(statement != null)
				{
					statement.close();
				}
			}
			finally {
				Database.releaseConnection(connection);
			}
		}
		return true;
	}
	
	/**
	 * Setta in Richiesta Tirocinio 1 ConvalidaProf
	 * 
	 * @param id
	 * @return boolean
	 * @throws SQLException
	 * 
	 * @author Iannuzzi Nicola', Gianluca Caggiano
	 */
	public synchronized static boolean setConvalidaProf(int id) throws SQLException
	{
		Connection connection = null;
		Statement statement = null;
		
		String query="UPDATE richiestatirocinio SET ConvalidaProf=1 WHERE richiestatirocinio.ID="+id+"";
		
		try {
			connection=Database.getConnection();
			statement=connection.createStatement();
			
			statement.executeUpdate(query);
			connection.commit();
		}
		finally {
			try {
				if(statement != null)
				{
					statement.close();
				}
			}
			finally {
				Database.releaseConnection(connection);
			}
		}
		return true;
	}
	
	/**
	 * Setta in Progetto Formativo 1 ConvalidaProf
	 * 
	 * @param id
	 * @return boolean
	 * @throws SQLException
	 * 
	 * @author Iannuzzi Nicola'
	 */
	public synchronized static boolean setConvalidaProfProgetto(int id) throws SQLException
	{
		Connection connection = null;
		Statement statement = null;
		
		String query="UPDATE progettoformativo SET convalidaProf=1 WHERE progettoformativo.ID="+id+"";
		
		try {
			connection=Database.getConnection();
			statement=connection.createStatement();
			
			statement.executeUpdate(query);
			connection.commit();
		}
		finally {
			try {
				if(statement != null)
				{
					statement.close();
				}
			}
			finally {
				Database.releaseConnection(connection);
			}
		}
		return true;
	}
	
	/**
	 * Setta in Progetto Formativo 1 ConvalidaSegr
	 * 
	 * @param id
	 * @return boolean
	 * @throws SQLException
	 * 
	 * @author Iannuzzi Nicola'
	 */
	public synchronized static boolean setConvalidaSegrProgetto(int id) throws SQLException
	{
		Connection connection = null;
		Statement statement = null;
		
		String query="UPDATE progettoformativo SET convalidaSegr=1 WHERE progettoformativo.ID="+id+"";
		
		try {
			connection=Database.getConnection();
			statement=connection.createStatement();
			
			statement.executeUpdate(query);
			connection.commit();
		}
		finally {
			try {
				if(statement != null)
				{
					statement.close();
				}
			}
			finally {
				Database.releaseConnection(connection);
			}
		}
		return true;
	}
	
	/**
	 * Setta in Progetto Formativo 1 sottoscrizioneStu
	 * 
	 * @param id
	 * @return boolean
	 * @throws SQLException
	 * 
	 * @author Iannuzzi Nicola'
	 */
	public synchronized static boolean setSottoscrizioneStuProgetto(int id) throws SQLException
	{
		Connection connection = null;
		Statement statement = null;
		
		String query="UPDATE progettoformativo SET sottoscrizioneStu=1 WHERE progettoformativo.ID="+id+"";
		
		try {
			connection=Database.getConnection();
			statement=connection.createStatement();
			
			statement.executeUpdate(query);
			connection.commit();
		}
		finally {
			try {
				if(statement != null)
				{
					statement.close();
				}
			}
			finally {
				Database.releaseConnection(connection);
			}
		}
		return true;
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

			ResultSet rs = statement.executeQuery("SELECT * FROM richiestatirocinio WHERE richiestatirocinio.AziendaEmail='"+email+"' AND richiestatirocinio.ConvalidaAzienda=0");
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
	 * Restituisce un ArrayList di oggetti di tipo RichiestaTirocinio dato l'email dell'Azienda che sono state Convalidate sia da esse che dal Prof.
	 * 
	 * @param email
	 * @return {@code ArrayList<RichiestaTirocinio>}.
	 * @throws SQLException
	 * 
	 * @author Iannuzzi Nicola'
	 */
	public synchronized static ArrayList<RichiestaTirocinio> doRetrieveRichiesteAziendeConvalidate(String email) throws SQLException
	{
		Connection connection = null;
		Statement statement = null;

		RichiestaTirocinio richiesta = null;
		ArrayList<RichiestaTirocinio> arrayRichiesta = new ArrayList<RichiestaTirocinio>();
		try 
		{
			connection = Database.getConnection();
			statement = connection.createStatement();

			ResultSet rs = statement.executeQuery("SELECT * FROM richiestatirocinio WHERE richiestatirocinio.AziendaEmail='"+email+"' AND richiestatirocinio.ConvalidaAzienda=1 AND richiestatirocinio.ConvalidaProf=1");
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

			ResultSet rs = statement.executeQuery("SELECT * FROM richiestatirocinio WHERE richiestatirocinio.ProfessoreEmail='"+email+"' AND richiestatirocinio.ConvalidaProf=0");
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
	
	/**
	 * Restituisce un oggetto di tipo ProgettoFormativo data la matricola dello Studente.
	 * 
	 * @param matricola
	 * @return {@code RichiestaTirocinio}.
	 * @throws SQLException
	 * 
	 * @author Iannuzzi Nicola'
	 */
	public synchronized static ProgettoFormativo doRetrievProgettoFormativoStudente(String matricola) throws SQLException
	{
		Connection connection = null;
		Statement statement = null;

		ProgettoFormativo progettoFormativo = null;
		try 
		{
			connection = Database.getConnection();
			statement = connection.createStatement();

			ResultSet rs = statement.executeQuery("SELECT * FROM progettoformativo WHERE StudenteMatricola='"+matricola+"' AND sottoscrizioneStu=0");
			connection.commit();

			while (rs.next())
			{
				progettoFormativo = new ProgettoFormativo();
				progettoFormativo.setId(rs.getInt("ID"));
				progettoFormativo.setAzienda(DatabaseGu.getAziendaByEmail(rs.getString("AziendaEmail")));
				progettoFormativo.setSegreteria(DatabaseGu.getSegreteriaByUser(rs.getString("SegreteriaUsername")));
				progettoFormativo.setStudente(DatabaseGu.getStudenteByMatricola(matricola));
				progettoFormativo.setProfessore(DatabaseGu.getProfessoreByEmail(rs.getString("ProfessoreEmail")));
				progettoFormativo.setObiettivi(rs.getString("Obiettivi"));
				progettoFormativo.setDataInizio(rs.getString("DataInizio"));
				progettoFormativo.setDataFine(rs.getString("DataFine"));
				progettoFormativo.setConvalidaProf(rs.getBoolean("convalidaProf"));
				progettoFormativo.setConvalidaSegr(rs.getBoolean("convalidaSegr"));
				progettoFormativo.setSottoscrizioneStu(rs.getBoolean("sottoscrizioneStu"));
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
		return progettoFormativo;
	}
	
	/**
	 * Restituisce un oggetto di tipo ProgettoFormativo data l'email Professore.
	 * 
	 * @param email
	 * @return {@code ArrayList<RichiestaTirocinio>}.
	 * @throws SQLException
	 * 
	 * @author Iannuzzi Nicola'
	 */
	public synchronized static ArrayList<ProgettoFormativo> doRetrievProgettoFormativoProfessore(String email) throws SQLException
	{
		Connection connection = null;
		Statement statement = null;

		ProgettoFormativo progettoFormativo = null;
		ArrayList<ProgettoFormativo> array = new ArrayList<ProgettoFormativo>();
		try 
		{
			connection = Database.getConnection();
			statement = connection.createStatement();

			ResultSet rs = statement.executeQuery("SELECT * FROM progettoformativo WHERE ProfessoreEmail='"+email+"' AND convalidaProf=0");
			connection.commit();

			while (rs.next())
			{
				progettoFormativo = new ProgettoFormativo();
				progettoFormativo.setId(rs.getInt("ID"));
				progettoFormativo.setAzienda(DatabaseGu.getAziendaByEmail(rs.getString("AziendaEmail")));
				progettoFormativo.setSegreteria(DatabaseGu.getSegreteriaByUser(rs.getString("SegreteriaUsername")));
				progettoFormativo.setStudente(DatabaseGu.getStudenteByMatricola(rs.getString("StudenteMatricola")));
				progettoFormativo.setProfessore(DatabaseGu.getProfessoreByEmail(email));
				progettoFormativo.setObiettivi(rs.getString("Obiettivi"));
				progettoFormativo.setDataInizio(rs.getString("DataInizio"));
				progettoFormativo.setDataFine(rs.getString("DataFine"));
				progettoFormativo.setConvalidaProf(rs.getBoolean("convalidaProf"));
				progettoFormativo.setConvalidaSegr(rs.getBoolean("convalidaSegr"));
				progettoFormativo.setSottoscrizioneStu(rs.getBoolean("sottoscrizioneStu"));
				
				array.add(progettoFormativo);
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
		return array;
	}
	
	/**
	 * Restituisce un oggetto di tipo ProgettoFormativo data l'username Segreteria.
	 * 
	 * @param username
	 * @return {@code ArrayList<RichiestaTirocinio>}.
	 * @throws SQLException
	 * 
	 * @author Iannuzzi Nicola'
	 */
	public synchronized static ArrayList<ProgettoFormativo> doRetrievProgettoFormativoSegreteria(String username) throws SQLException
	{
		Connection connection = null;
		Statement statement = null;

		ProgettoFormativo progettoFormativo = null;
		ArrayList<ProgettoFormativo> array = new ArrayList<ProgettoFormativo>();
		try 
		{
			connection = Database.getConnection();
			statement = connection.createStatement();

			ResultSet rs = statement.executeQuery("SELECT * FROM progettoformativo WHERE SegreteriaUsername='"+username+"' AND convalidaProf=1 AND sottoscrizioneStu=1 AND convalidaSegr=0");
			connection.commit();

			while (rs.next())
			{
				progettoFormativo = new ProgettoFormativo();
				progettoFormativo.setId(rs.getInt("ID"));
				progettoFormativo.setAzienda(DatabaseGu.getAziendaByEmail(rs.getString("AziendaEmail")));
				progettoFormativo.setSegreteria(DatabaseGu.getSegreteriaByUser(username));
				progettoFormativo.setStudente(DatabaseGu.getStudenteByMatricola(rs.getString("StudenteMatricola")));
				progettoFormativo.setProfessore(DatabaseGu.getProfessoreByEmail(rs.getString("ProfessoreEmail")));
				progettoFormativo.setObiettivi(rs.getString("Obiettivi"));
				progettoFormativo.setDataInizio(rs.getString("DataInizio"));
				progettoFormativo.setDataFine(rs.getString("DataFine"));
				progettoFormativo.setConvalidaProf(rs.getBoolean("convalidaProf"));
				progettoFormativo.setConvalidaSegr(rs.getBoolean("convalidaSegr"));
				progettoFormativo.setSottoscrizioneStu(rs.getBoolean("sottoscrizioneStu"));
				
				array.add(progettoFormativo);
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
		return array;
	}
	
	private static String queryAddRichiesta;
	private static String queryAddProgettoFormativo;
	private static String queryGetRichiestaById;
	private static String queryUpdateStudente;
	private static String queryGetMaxRichiestaTirocinio;
	private static String queryGetStudenteByIDRichiesta;
	private static String queryGetProgettoFormativoByProfessore;
	private static String queryGetProgettoFormativoBySegreteria;
	
	static {
		
		queryAddRichiesta = "INSERT INTO richiestatirocinio (AziendaEmail, ProfessoreEmail) VALUES (?,?);";
		queryAddProgettoFormativo= "INSERT INTO progettoformativo (AziendaEmail, SegreteriaUsername, StudenteMatricola, ProfessoreEmail, Obiettivi, DataInizio, DataFine) VALUES (?,?,?,?,?,?,?);";
		
		queryUpdateStudente= "UPDATE studente SET RichiestaTirocinioID=? WHERE studente.Email=?";
		
		queryGetRichiestaById = "SELECT * FROM richiestatirocinio WHERE richiestatirocinio.ID=?";
		queryGetMaxRichiestaTirocinio = "SELECT MAX(ID) FROM richiestatirocinio";
		queryGetStudenteByIDRichiesta = "SELECT * FROM studente, utente WHERE studente.Email=utente.User AND studente.RichiestaTirocinioID=?";
		queryGetProgettoFormativoByProfessore = "SELECT * FROM progettoformativo WHERE ProfessoreEmail=? AND convalidaProf=0";
		queryGetProgettoFormativoBySegreteria = "SELECT * FROM progettoformativo WHERE SegreteriaUsername=? AND convalidaSegr=0";
	}
}
