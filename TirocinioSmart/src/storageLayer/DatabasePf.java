
package storageLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gestioneProgettoFormativo.RichiestaTirocinio;

/**
 * Classe che fornisce i metodi per le interrogazioni al database per le classi del package gestioneProgettoFormativo.
 * 
 * @author Caggiano Gianluca
 *
 * @version 1.0
 */
public class DatabasePf {

	/**
	 * 
	 * @param La richiesta di tirocinio da memorizzare
	 * @return {@code true} se la aggiunta della richiesta e' ok, {@code false} altrimenti.
	 * @throws SQLException
	 */
	public synchronized static boolean AddRichiesta(RichiestaTirocinio rt) throws SQLException
	{
		Connection connection = null;
		
		PreparedStatement psAddRichiesta = null;
		try {
			connection = Database.getConnection();
			psAddRichiesta = connection.prepareStatement(queryAddRichiesta);
			
			psAddRichiesta.setString(1, rt.getAzienda().getUser());
			psAddRichiesta.setString(2, rt.getProfessore().getUser());
			psAddRichiesta.executeUpdate();
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
				
				if (psAddRichiesta != null)
				{
					psAddRichiesta.close();
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
	
	private static String queryAddRichiesta;
	private static String queryGetRichiestaById;
	
	static {
		
		queryAddRichiesta = "INSERT INTO richiestatirocinio (AziendaEmail, ProfessoreEmail) VALUES (?,?);";
		
		queryGetRichiestaById = "SELECT * FROM richiestatirocinio WHERE richiestatirocinio.ID=?";
	}
}
