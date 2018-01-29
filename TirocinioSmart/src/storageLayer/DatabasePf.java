
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
				richiesta.setId(rs.getString("ID"));
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
		if (richiesta.getId() == null)
			return null;
		else
			return richiesta;
	}
	
	private static String queryGetRichiestaById;
	
	static {
		
		queryGetRichiestaById = "SELECT * FROM richiestatirocinio WHERE richiestatirocinio.ID=?";
	}
}
