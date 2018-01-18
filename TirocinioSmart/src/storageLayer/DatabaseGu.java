package storageLayer;

import java.sql.Connection;
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
				psAddStudente.setInt(5, 0);
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
		//if(utente instanceof Azienda) ...
	}

	/**
	 * Restituisce ,se esiste, un oggetto Studente data una email.
	 * 
	 * @param email
	 * @return {@code null} se l'utene non esiste, {@code Oggetto Studente } altrimenti.
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
	 * Restituisce ,se esiste, un oggetto Studente data una matricola.
	 * 
	 * @param matricola
	 * @return {@code null} se l'utene non esiste, {@code Oggetto Studente } altrimenti.
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
	
	private static String queryAddUtente;
	private static String queryAddStudente;
	private static String queryGetStudenteEmail;
	private static String queryGetStudenteMatricola;
	
	static {
		//Query universale per tutti gli utenti
		queryAddUtente = "Insert into utente (User, Password, Nome, Cognome, Tipo) VALUES (?,?,?,?,?);";
		queryAddStudente = "Insert into studente (Matricola, Email, DataNascita, LuogoNascita, abilitato) VALUES (?,?,?,?,?);";
		
		queryGetStudenteEmail = "SELECT * From utente,studente WHERE studente.Email=utente.User AND utente.User=?;";
		queryGetStudenteMatricola = "SELECT * From utente,studente WHERE studente.Email=utente.User AND studente.Matricola=?;";
	}
}