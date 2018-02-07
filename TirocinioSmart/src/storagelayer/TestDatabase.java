package storagelayer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

/**
 * Classe che definisce i casi di test per la classe di connessione al database.
 * 
 * @author Gianluca
 *
 */
public class TestDatabase {

  /**
   * Test method for {@link storagelayer.Database#createDbConnection()}.
   */
  @Test
  public void testCreateDbConnection() {
    Connection con = null;

    try {
      con = Database.createDbConnection();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    assertNotNull(con);
  }

  /**
   * Test method for {@link storagelayer.Database#getConnection()} and
   * {@link storagelayer.Database#releaseConnection(java.sql.Connection)}.
   */
  @Test
  public void test_Connection() {
    Connection connection = null;
    try {
      connection = Database.getConnection();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    assertNotNull(connection);

    try {
      Database.releaseConnection(connection);
    } catch (SQLException e) {
      fail();
    }

  }

}
