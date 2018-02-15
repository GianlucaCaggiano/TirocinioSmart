package storagelayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * Connector per il database.
 *
 * @author Caggiano Gianluca
 *
 * @version 1.0
 */
public class Database {

  private static String protocol;
  private static String hostname;
  private static String port;
  private static String username;
  private static String password;
  private static String dbName;
  private static Properties userInfo;
  private static String mySqlUrl;
  private static List<Connection> freeDbConnections;
  static final String driver = "com.mysql.jdbc.Driver";

  static {
    protocol = "jdbc:mysql://";

    hostname = "localhost:";
    port = "3306/";
    dbName = "tirociniosmart";
    mySqlUrl = protocol + hostname + port + dbName + "?autoReconnect=true&useSSL=false";
    username = "root";
    password = "tomorrow";
    userInfo = new Properties();
    userInfo.put("user", username);
    userInfo.put("password", password);

    freeDbConnections = new LinkedList<Connection>();
    try {
      Class.forName(driver);
    } catch (ClassNotFoundException e) {
      System.out.println("DB driver not found:" + e.getMessage());
    }
  }

  /**
   * Inizializza le connessioni.
   * 
   * @return la nuova connessione al database
   * @throws SQLException Eccezione lanciata
   * @author Caggiano Gianluca
   */

  public static synchronized Connection createDbConnection() throws SQLException {

    Connection newConnection = null;
    newConnection = DriverManager.getConnection(mySqlUrl, userInfo);
    newConnection.setAutoCommit(false);
    return newConnection;
  }

  /**
   * Fornisce una connessione al database.
   * 
   * @return connessione al database
   * @throws SQLException Eccezione lanciata
   * @author Caggiano Gianluca
   */
  public static synchronized Connection getConnection() throws SQLException {
    Connection connection;

    if (!freeDbConnections.isEmpty()) {
      connection = (Connection) freeDbConnections.get(0);
      freeDbConnections.remove(0);

      try {
        if (connection.isClosed()) {
          connection = getConnection();
        }
      } catch (SQLException e) {
        connection.close();
        connection = getConnection();
      }
    } else {
      connection = createDbConnection();
    }

    return connection;
  }

  /**
   * Rilascia la connessione
   * 
   * @param connection
   *          rappresenta la connessione rilasciata e ri-aggiunta al pool di connessioni.
   *          
   * @throws SQLException Eccezione lanciata da un errore SQL. 
   * 
   * @author Caggiano Gianluca
   */

  public static synchronized void releaseConnection(Connection connection) throws SQLException {
    if (connection != null) {
      freeDbConnections.add(connection);
    }
  }
}
