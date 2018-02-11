package storagelayer;

import gestioneprogettoformativo.ProgettoFormativo;
import gestioneprogettoformativo.RichiestaTirocinio;
import gestioneutente.Studente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Classe che fornisce i metodi per le interrogazioni al database per le classi del package
 * gestioneProgettoFormativo.
 * 
 * @author Caggiano Gianluca
 *
 * @version 1.0
 */
public class DatabasePf {

  /**
   * 
   * @param rt
   *          richiesta di tirocinio da memorizzare
   * @return {@code true} se la aggiunta della richiesta e' ok, {@code false} altrimenti.
   * @throws SQLException
   * 
   * @author Gianluca Caggiano, Iannuzzi Nicola'
   */
  public static synchronized int addRichiesta(RichiestaTirocinio rt, Studente s)
      throws SQLException {
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

    } finally {
      try {
        if (psAddRichiesta != null) {
          psAddRichiesta.close();
        }
      } finally {
        Database.releaseConnection(connection);
      }
    }
    
    try {
      connection = Database.getConnection();
      psUpdateStudente = connection.prepareStatement(queryUpdateStudente);
      
      id = getIdMaxRichiestaTirocinio();
      psUpdateStudente.setInt(1, id);
      psUpdateStudente.setString(2, s.getUser());
      psUpdateStudente.executeUpdate();
      connection.commit();
    
    } finally {
      try {
        if (psUpdateStudente != null) {
          psUpdateStudente.close();
        }
      } finally {
        Database.releaseConnection(connection);
      }
    }
    
    return id;
  }

  /**
   * 
   * @param progettoFormativo
   *          Progetto Formativo da memorizzare
   * @return {@code true} se la aggiunta del Progetto e' ok, {@code false} altrimenti.
   * @throws SQLException
   * 
   * @author Gianluca Caggiano, Iannuzzi Nicola'
   */
  public static synchronized boolean addProgettoFormativo(ProgettoFormativo progettoFormativo)
      throws SQLException {
    Connection connection = null;

    PreparedStatement psAddProgettoFormativo = null;
    try {
      connection = Database.getConnection();
      psAddProgettoFormativo = connection.prepareStatement(queryAddProgettoFormativo);

      psAddProgettoFormativo.setString(1, progettoFormativo.getAzienda().getUser());
      psAddProgettoFormativo.setString(2, progettoFormativo.getSegreteria().getUser());
      psAddProgettoFormativo.setString(3, progettoFormativo.getStudente().getMatricola());
      psAddProgettoFormativo.setString(4, progettoFormativo.getProfessore().getUser());
      psAddProgettoFormativo.setString(5, progettoFormativo.getObiettivi());
      psAddProgettoFormativo.setString(6, progettoFormativo.getDataInizio());
      psAddProgettoFormativo.setString(7, progettoFormativo.getDataFine());
      psAddProgettoFormativo.executeUpdate();
      connection.commit();
    } finally {
      try {
        if (psAddProgettoFormativo != null) {
          psAddProgettoFormativo.close();
        }
      } finally {
        Database.releaseConnection(connection);
      }
    }
    return true;
  }

  /**
   * Restituisce, se esiste, un oggetto di tipo RichiestaTirocinio dato l'identificativo.
   * 
   * @param id Identificativo della richiesta da prelevare
   * @return {@code null} se la richiesta di tirocinio con tale id non esiste,
   *         {@code Oggetto RichiestaTirocinio } altrimenti.
   * @throws SQLException
   * 
   * @author Caggiano Gianluca
   */
  public static synchronized RichiestaTirocinio getRichiestaById(int id) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    RichiestaTirocinio richiesta = new RichiestaTirocinio();

    try {
      connection = Database.getConnection();
      preparedStatement = connection.prepareStatement(queryGetRichiestaById);
      preparedStatement.setInt(1, id);

      ResultSet rs = preparedStatement.executeQuery();
      connection.commit();

      while (rs.next()) {
        richiesta.setId(rs.getInt("ID"));
        richiesta.setAzienda(DatabaseGu.getAziendaByEmail(rs.getString("AziendaEmail")));
        richiesta.setProfessore(DatabaseGu.getProfessoreByEmail(rs.getString("ProfessoreEmail")));
        richiesta.setConvalidaAzienda(rs.getBoolean("ConvalidaAzienda"));
        richiesta.setConvalidaProf(rs.getBoolean("ConvalidaProf"));
      }
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        Database.releaseConnection(connection);
      }
    }
    if (richiesta.getId() == -1) {
      return null;
    } else {
      return richiesta;
    }
  }

  /**
   * Setta in Richiesta Tirocinio 1 a ConvalidaAzienda.
   * 
   * @param id Identificativo della richiesta di tirocinio 
   * @return boolean
   * @throws SQLException
   * 
   * @author Gianluca Caggiano, Iannuzzi Nicola'
   */
  public static synchronized boolean setConvalidaAzienda(int id) throws SQLException {
    Connection connection = null;
    Statement statement = null;

    String query = "UPDATE richiestatirocinio SET ConvalidaAzienda=1 WHERE richiestatirocinio.ID="
        + id + "";

    try {
      connection = Database.getConnection();
      statement = connection.createStatement();

      statement.executeUpdate(query);
      connection.commit();
    } finally {
      try {
        if (statement != null) {
          statement.close();
        }
      } finally {
        Database.releaseConnection(connection);
      }
    }
    return true;
  }

  /**
   * Setta in Richiesta Tirocinio 1 a ConvalidaProf.
   * 
   * @param id Identificativo della richiesta di tirocinio
   * @return boolean
   * @throws SQLException
   * 
   * @author Iannuzzi Nicola', Gianluca Caggiano
   */
  public static synchronized boolean setConvalidaProf(int id) throws SQLException {
    Connection connection = null;
    Statement statement = null;

    String query = "UPDATE richiestatirocinio SET ConvalidaProf=1 WHERE richiestatirocinio.ID=" + id
        + "";

    try {
      connection = Database.getConnection();
      statement = connection.createStatement();

      statement.executeUpdate(query);
      connection.commit();
    } finally {
      try {
        if (statement != null) {
          statement.close();
        }
      } finally {
        Database.releaseConnection(connection);
      }
    }
    return true;
  }

  /**
   * Setta in Progetto Formativo 1 ConvalidaProf.
   * 
   * @param id Identificativo della richiesta di tirocinio
   * @return boolean
   * @throws SQLException
   * 
   * @author Iannuzzi Nicola'
   */
  public static synchronized boolean setConvalidaProfProgetto(int id) throws SQLException {
    Connection connection = null;
    Statement statement = null;

    String query = "UPDATE progettoformativo SET convalidaProf=1 WHERE progettoformativo.ID=" + id
        + "";

    try {
      connection = Database.getConnection();
      statement = connection.createStatement();

      statement.executeUpdate(query);
      connection.commit();
    } finally {
      try {
        if (statement != null) {
          statement.close();
        }
      } finally {
        Database.releaseConnection(connection);
      }
    }
    return true;
  }

  /**
   * Setta in Progetto Formativo 1 ConvalidaSegr.
   * 
   * @param id Identificativo del progetto formativo
   * @return boolean
   * @throws SQLException
   * 
   * @author Iannuzzi Nicola'
   */
  public static synchronized boolean setConvalidaSegrProgetto(int id) throws SQLException {
    Connection connection = null;
    Statement statement = null;

    String query = "UPDATE progettoformativo SET convalidaSegr=1 WHERE progettoformativo.ID=" + id
        + "";

    try {
      connection = Database.getConnection();
      statement = connection.createStatement();

      statement.executeUpdate(query);
      connection.commit();
    } finally {
      try {
        if (statement != null) {
          statement.close();
        }
      } finally {
        Database.releaseConnection(connection);
      }
    }
    return true;
  }

  /**
   * Setta in Progetto Formativo 1 sottoscrizioneStu.
   * 
   * @param id Identificativo del progetto formativo
   * @return boolean
   * @throws SQLException
   * 
   * @author Iannuzzi Nicola'
   */
  public static synchronized boolean setSottoscrizioneStuProgetto(int id) throws SQLException {
    Connection connection = null;
    Statement statement = null;

    String query = "UPDATE progettoformativo SET sottoscrizioneStu=1 WHERE progettoformativo.ID="
        + id + "";

    try {
      connection = Database.getConnection();
      statement = connection.createStatement();

      statement.executeUpdate(query);
      connection.commit();
    } finally {
      try {
        if (statement != null) {
          statement.close();
        }
      } finally {
        Database.releaseConnection(connection);
      }
    }
    return true;
  }

  /**
   * Restituisce l'ultima Richiesta Tirocinio creata in ordine temporale.
   * 
   * @return {@code -1} non e' presente nessuna convenzione, {@code Oggetto Convenzione} altrimenti.
   * @throws SQLException Eccezione lanciata
   */
  private static synchronized int getIdMaxRichiestaTirocinio() throws SQLException {
    Connection connection = null;
    java.sql.Statement statement = null;

    int id = -1;
    try {
      connection = Database.getConnection();
      statement = connection.createStatement();

      ResultSet rs = statement.executeQuery(queryGetMaxRichiestaTirocinio);

      if (rs.next()) {
        id = rs.getInt(1);
      }
    } finally {
      try {
        if (statement != null) {
          statement.close();
        }
      } finally {
        Database.releaseConnection(connection);
      }
    }
    return id;
  }

  /**
   * Restituisce un ArrayList di oggetti di tipo RichiestaTirocinio dato l'email dell'Azienda.
   * 
   * @param email Email dell'azienda
   * @return {@code ArrayList<RichiestaTirocinio>}.
   * @throws SQLException
   * 
   * @author Caggiano Gianluca, Iannuzzi Nicola'
   */
  public static synchronized ArrayList<RichiestaTirocinio> doRetrieveRichiesteAziende(String email)
      throws SQLException {
    Connection connection = null;
    Statement statement = null;

    RichiestaTirocinio richiesta = null;
    ArrayList<RichiestaTirocinio> arrayRichiesta = new ArrayList<RichiestaTirocinio>();
    try {
      connection = Database.getConnection();
      statement = connection.createStatement();

      ResultSet rs = statement
          .executeQuery("SELECT * FROM richiestatirocinio WHERE richiestatirocinio.AziendaEmail='"
              + email + "' AND richiestatirocinio.ConvalidaAzienda=0");
      connection.commit();

      while (rs.next()) {
        richiesta = new RichiestaTirocinio();
        richiesta.setId(rs.getInt("ID"));
        richiesta.setAzienda(DatabaseGu.getAziendaByEmail(rs.getString("AziendaEmail")));
        richiesta.setProfessore(DatabaseGu.getProfessoreByEmail(rs.getString("ProfessoreEmail")));
        richiesta.setConvalidaAzienda(rs.getBoolean("ConvalidaAzienda"));
        richiesta.setConvalidaProf(rs.getBoolean("ConvalidaProf"));

        arrayRichiesta.add(richiesta);
      }
    } finally {
      try {
        if (statement != null) {
          statement.close();
        }
      } finally {
        Database.releaseConnection(connection);
      }
    }
    return arrayRichiesta;
  }

  /**
   * Restituisce un ArrayList di oggetti di tipo RichiestaTirocinio dato l'email dell'Azienda che
   * sono state Convalidate sia da esse che dal Prof.
   * 
   * @param email Email dell'azienda
   * @return {@code ArrayList<RichiestaTirocinio>}.
   * @throws SQLException
   * 
   * @author Iannuzzi Nicola'
   */
  public static synchronized ArrayList<RichiestaTirocinio> doRetrieveRichiesteConvalidate(
      String email) throws SQLException {
    Connection connection = null;
    Statement statement = null;

    RichiestaTirocinio richiesta = null;
    ArrayList<RichiestaTirocinio> arrayRichiesta = new ArrayList<RichiestaTirocinio>();
    try {
      connection = Database.getConnection();
      statement = connection.createStatement();

      ResultSet rs = statement.executeQuery(
          "SELECT * FROM richiestatirocinio WHERE richiestatirocinio.AziendaEmail='" + email
              + "' AND richiestatirocinio.ConvalidaAzienda=1 "
              + "AND richiestatirocinio.ConvalidaProf=1");
      connection.commit();

      while (rs.next()) {
        richiesta = new RichiestaTirocinio();
        richiesta.setId(rs.getInt("ID"));
        richiesta.setAzienda(DatabaseGu.getAziendaByEmail(rs.getString("AziendaEmail")));
        richiesta.setProfessore(DatabaseGu.getProfessoreByEmail(rs.getString("ProfessoreEmail")));
        richiesta.setConvalidaAzienda(rs.getBoolean("ConvalidaAzienda"));
        richiesta.setConvalidaProf(rs.getBoolean("ConvalidaProf"));

        arrayRichiesta.add(richiesta);
      }
    } finally {
      try {
        if (statement != null) {
          statement.close();
        }
      } finally {
        Database.releaseConnection(connection);
      }
    }
    return arrayRichiesta;
  }

  /**
   * Restituisce un ArrayList di oggetti di tipo RichiestaTirocinio dato l'email del Professore.
   * 
   * @param email Email del professore
   * @return {@code ArrayList<RichiestaTirocinio>}.
   * @throws SQLException
   * 
   * @author Caggiano Gianluca, Iannuzzi Nicola'
   */
  public static synchronized ArrayList<RichiestaTirocinio> doRetrieveRichiesteProfessore(
      String email) throws SQLException {
    Connection connection = null;
    Statement statement = null;

    RichiestaTirocinio richiesta = null;
    ArrayList<RichiestaTirocinio> arrayRichiesta = new ArrayList<RichiestaTirocinio>();
    try {
      connection = Database.getConnection();
      statement = connection.createStatement();

      ResultSet rs = statement.executeQuery(
          "SELECT * FROM richiestatirocinio WHERE richiestatirocinio.ProfessoreEmail='" + email
              + "' AND richiestatirocinio.ConvalidaProf=0");
      connection.commit();

      while (rs.next()) {
        richiesta = new RichiestaTirocinio();
        richiesta.setId(rs.getInt("ID"));
        richiesta.setAzienda(DatabaseGu.getAziendaByEmail(rs.getString("AziendaEmail")));
        richiesta.setProfessore(DatabaseGu.getProfessoreByEmail(rs.getString("ProfessoreEmail")));
        richiesta.setConvalidaAzienda(rs.getBoolean("ConvalidaAzienda"));
        richiesta.setConvalidaProf(rs.getBoolean("ConvalidaProf"));

        arrayRichiesta.add(richiesta);
      }
    } finally {
      try {
        if (statement != null) {
          statement.close();
        }
      } finally {
        Database.releaseConnection(connection);
      }
    }
    return arrayRichiesta;
  }

  /**
   * Restituisce, se esiste, un oggetto di tipo Studente dato l'identificativo della Richiesta.
   * 
   * @param id Identificativo della richiesta di tirocinio
   * @return {@code Studente}.
   * @throws SQLException
   * 
   * @author Caggiano Gianluca, Iannuzzi Nicola'
   */
  public static synchronized Studente getStudenteByIdRichiesta(int id) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Studente s = null;

    try {
      connection = Database.getConnection();
      preparedStatement = connection.prepareStatement(queryGetStudenteByIDRichiesta);
      preparedStatement.setInt(1, id);

      ResultSet rs = preparedStatement.executeQuery();
      connection.commit();

      if (rs.next()) {
        s = new Studente();
        s.setUser(rs.getString("User"));
        s.setPassword(rs.getString("Password"));
        s.setNome(rs.getString("Nome"));
        s.setCognome(rs.getString("Cognome"));
        s.setMatricola(rs.getString("Matricola"));
        s.setDataNascita(rs.getString("DataNascita"));
        s.setLuogoNascita(rs.getString("LuogoNascita"));
      }
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        Database.releaseConnection(connection);
      }
    }
    return s;
  }

  /**
   * Restituisce un oggetto di tipo ProgettoFormativo data la matricola dello Studente.
   * 
   * @param matricola Matricola dello studente
   * @return {@code RichiestaTirocinio}.
   * @throws SQLException
   * 
   * @author Iannuzzi Nicola'
   */
  public static synchronized ProgettoFormativo doRetrievProgettoFormativoStudente(String matricola)
      throws SQLException {
    Connection connection = null;
    Statement statement = null;

    ProgettoFormativo progettoFormativo = null;
    try {
      connection = Database.getConnection();
      statement = connection.createStatement();

      ResultSet rs = statement
          .executeQuery("SELECT * FROM progettoformativo WHERE StudenteMatricola='" + matricola
              + "' AND sottoscrizioneStu=0");
      connection.commit();

      while (rs.next()) {
        progettoFormativo = new ProgettoFormativo();
        progettoFormativo.setId(rs.getInt("ID"));
        progettoFormativo.setAzienda(DatabaseGu.getAziendaByEmail(rs.getString("AziendaEmail")));
        progettoFormativo
            .setSegreteria(DatabaseGu.getSegreteriaByUser(rs.getString("SegreteriaUsername")));
        progettoFormativo.setStudente(DatabaseGu.getStudenteByMatricola(matricola));
        progettoFormativo
            .setProfessore(DatabaseGu.getProfessoreByEmail(rs.getString("ProfessoreEmail")));
        progettoFormativo.setObiettivi(rs.getString("Obiettivi"));
        progettoFormativo.setDataInizio(rs.getString("DataInizio"));
        progettoFormativo.setDataFine(rs.getString("DataFine"));
        progettoFormativo.setConvalidaProf(rs.getBoolean("convalidaProf"));
        progettoFormativo.setConvalidaSegr(rs.getBoolean("convalidaSegr"));
        progettoFormativo.setSottoscrizioneStu(rs.getBoolean("sottoscrizioneStu"));
      }
    } finally {
      try {
        if (statement != null) {
          statement.close();
        }
      } finally {
        Database.releaseConnection(connection);
      }
    }
    return progettoFormativo;
  }

  /**
   * Restituisce un oggetto di tipo ProgettoFormativo data l'email Professore.
   * 
   * @param email Email del professore
   * @return {@code ArrayList<RichiestaTirocinio>}.
   * @throws SQLException
   * 
   * @author Iannuzzi Nicola'
   */
  public static synchronized ArrayList<ProgettoFormativo> doRetrievProgettoFormativoProfessore(
      String email) throws SQLException {
    Connection connection = null;
    Statement statement = null;

    ProgettoFormativo progettoFormativo = null;
    ArrayList<ProgettoFormativo> array = new ArrayList<ProgettoFormativo>();
    try {
      connection = Database.getConnection();
      statement = connection.createStatement();

      ResultSet rs = statement
          .executeQuery("SELECT * FROM progettoformativo WHERE ProfessoreEmail='" + email
              + "' AND convalidaProf=0");
      connection.commit();

      while (rs.next()) {
        progettoFormativo = new ProgettoFormativo();
        progettoFormativo.setId(rs.getInt("ID"));
        progettoFormativo.setAzienda(DatabaseGu.getAziendaByEmail(rs.getString("AziendaEmail")));
        progettoFormativo
            .setSegreteria(DatabaseGu.getSegreteriaByUser(rs.getString("SegreteriaUsername")));
        progettoFormativo
            .setStudente(DatabaseGu.getStudenteByMatricola(rs.getString("StudenteMatricola")));
        progettoFormativo.setProfessore(DatabaseGu.getProfessoreByEmail(email));
        progettoFormativo.setObiettivi(rs.getString("Obiettivi"));
        progettoFormativo.setDataInizio(rs.getString("DataInizio"));
        progettoFormativo.setDataFine(rs.getString("DataFine"));
        progettoFormativo.setConvalidaProf(rs.getBoolean("convalidaProf"));
        progettoFormativo.setConvalidaSegr(rs.getBoolean("convalidaSegr"));
        progettoFormativo.setSottoscrizioneStu(rs.getBoolean("sottoscrizioneStu"));

        array.add(progettoFormativo);
      }
    } finally {
      try {
        if (statement != null) {
          statement.close();
        }
      } finally {
        Database.releaseConnection(connection);
      }
    }
    return array;
  }

  /**
   * Restituisce un oggetto di tipo ProgettoFormativo data l'username Segreteria.
   * 
   * @param username Username della segreteria
   * @return {@code ArrayList<RichiestaTirocinio>}.
   * @throws SQLException
   * 
   * @author Iannuzzi Nicola'
   */
  public static synchronized ArrayList<ProgettoFormativo> doRetrievProgettoFormativoSegreteria(
      String username) throws SQLException {
    Connection connection = null;
    Statement statement = null;

    ProgettoFormativo progettoFormativo = null;
    ArrayList<ProgettoFormativo> array = new ArrayList<ProgettoFormativo>();
    try {
      connection = Database.getConnection();
      statement = connection.createStatement();

      ResultSet rs = statement
          .executeQuery("SELECT * FROM progettoformativo WHERE SegreteriaUsername='" + username
              + "' AND convalidaProf=1 AND sottoscrizioneStu=1 AND convalidaSegr=0");
      connection.commit();

      while (rs.next()) {
        progettoFormativo = new ProgettoFormativo();
        progettoFormativo.setId(rs.getInt("ID"));
        progettoFormativo.setAzienda(DatabaseGu.getAziendaByEmail(rs.getString("AziendaEmail")));
        progettoFormativo.setSegreteria(DatabaseGu.getSegreteriaByUser(username));
        progettoFormativo
            .setStudente(DatabaseGu.getStudenteByMatricola(rs.getString("StudenteMatricola")));
        progettoFormativo
            .setProfessore(DatabaseGu.getProfessoreByEmail(rs.getString("ProfessoreEmail")));
        progettoFormativo.setObiettivi(rs.getString("Obiettivi"));
        progettoFormativo.setDataInizio(rs.getString("DataInizio"));
        progettoFormativo.setDataFine(rs.getString("DataFine"));
        progettoFormativo.setConvalidaProf(rs.getBoolean("convalidaProf"));
        progettoFormativo.setConvalidaSegr(rs.getBoolean("convalidaSegr"));
        progettoFormativo.setSottoscrizioneStu(rs.getBoolean("sottoscrizioneStu"));

        array.add(progettoFormativo);
      }
    } finally {
      try {
        if (statement != null) {
          statement.close();
        }
      } finally {
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

  static {

    queryAddRichiesta = "INSERT INTO richiestatirocinio (AziendaEmail, ProfessoreEmail) "
        + "VALUES (?,?);";
    queryAddProgettoFormativo = "INSERT INTO progettoformativo "
        + "(AziendaEmail, SegreteriaUsername, StudenteMatricola, "
        + "ProfessoreEmail, Obiettivi, DataInizio, DataFine) "
        + "VALUES (?,?,?,?,?,?,?);";

    queryUpdateStudente = "UPDATE studente SET RichiestaTirocinioID=? WHERE studente.Email=?";

    queryGetRichiestaById = "SELECT * FROM richiestatirocinio WHERE richiestatirocinio.ID=?";
    queryGetMaxRichiestaTirocinio = "SELECT MAX(ID) FROM richiestatirocinio";
    queryGetStudenteByIDRichiesta = "SELECT * FROM studente, utente "
        + "WHERE studente.Email=utente.User AND studente.RichiestaTirocinioID=?";
  }
}
