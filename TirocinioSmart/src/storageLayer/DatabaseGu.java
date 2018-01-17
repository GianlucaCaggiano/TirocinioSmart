package storageLayer;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import gestioneUtente.*;

public class DatabaseGu 
{

	public DatabaseGu()
	{
		
	}
	
	public void doSafe(Utente utente)
	{
		Database database = new Database();
		try 
		{
			database.createDBConnection();
			Connection connection = database.getConnection();
			Statement statement = connection.createStatement();
			
			if(utente instanceof Studente) 
			{
				Studente studente = (Studente) utente;
				
				String query = "Insert into utente VALUES('"+studente.getUser()+"', '"+studente.getNome()+"', '"+studente.getCognome()+"', 'Studente');";
				statement.executeQuery(query);
				
				query = "Insert into studente (Matricola, Email, DataNascita, LuogoNascita, abilitato) VALUES ('"+studente.getMatricola()+"', ''"+studente.getUser()+", '"+studente.getDataNascita()+"', '"+studente.getLuogoNascita()+"', 0); ";
				statement.execute(query);
				
				database.releaseConnection(connection);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
