package storagelayer;

import gestioneutente.Azienda;
import gestioneutente.Convenzione;
import gestioneutente.Professore;
import gestioneutente.Segreteria;
import gestioneutente.Studente;
import gestioneutente.Utente;

import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Classe che fornisce i metodi per le interrogazioni al database per le classi del package
 * gestioneUtente.
 * 
 * @author Caggiano Gianluca, Iannuzzi Nicola'
 *
 * @version 1.0
 */
public class DatabaseGu {
  /**
   * Registra un utente nel database.
   * 
   * @param utente
   *          Utente della piattaforma che deve essere aggiunto
   * @return {@code true} se la registrazione e' ok, {@code false} altrimenti.
   * @throws SQLException
   * 
   * @author Caggiano Gianluca, Iannuzzi Nicola'
   */
  public static synchronized boolean addUser(Utente utente) throws SQLException {
    Connection connection = null;

    if (utente instanceof Studente) {
      PreparedStatement psAddUtente = null;
      PreparedStatement psAddStudente = null;
      try {
        connection = Database.getConnection();
        psAddUtente = connection.prepareStatement(queryAddUtente);

        psAddUtente.setString(1, utente.getUser());
        psAddUtente.setString(2, utente.getPassword());
        psAddUtente.setString(3, utente.getNome());
        psAddUtente.setString(4, utente.getCognome());
        psAddUtente.setString(5, "ST");// ST sta ad indicare il tipo "Studente" nel database
        // AZ=Azienda, SR=Segreteria, PR=Professore
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
        connection.commit();
      } finally {
        try {
          if (psAddUtente != null) {
            psAddUtente.close();
          }

          if (psAddStudente != null) {
            psAddStudente.close();
          }
        } finally {
          Database.releaseConnection(connection);
        }
      }
    }

    if (utente instanceof Azienda) {
      PreparedStatement psAddUtente = null;
      PreparedStatement psAddAzienda = null;
      try {
        connection = Database.getConnection();
        psAddUtente = connection.prepareStatement(queryAddUtente);

        psAddUtente.setString(1, utente.getUser());
        psAddUtente.setString(2, utente.getPassword());
        psAddUtente.setString(3, utente.getNome());
        psAddUtente.setString(4, utente.getCognome());
        psAddUtente.setString(5, "AZ");// ST sta ad indicare il tipo "Studente" nel database
        // AZ=Azienda, SR=Segreteria, PR=Professore
        psAddUtente.executeUpdate();

        connection.commit();

        Azienda azienda = (Azienda) utente;

        psAddAzienda = connection.prepareStatement(queryAddAzienda);

        psAddAzienda.setString(1, azienda.getUser());
        psAddAzienda.setString(2, azienda.getLuogoNascita());
        psAddAzienda.setString(3, azienda.getDataNascita());
        psAddAzienda.setString(4, azienda.getDenominazione());
        psAddAzienda.setString(5, azienda.getCitta());
        psAddAzienda.setString(6, azienda.getCap());
        psAddAzienda.setString(7, azienda.getVia());
        psAddAzienda.setInt(8, 0);

        if (azienda.getTelefono() != null) {
          psAddAzienda.setString(9, azienda.getTelefono());
        } else {
          psAddAzienda.setString(9, null);
        }

        if (azienda.getSitoWeb() != null) {
          psAddAzienda.setString(10, azienda.getSitoWeb());
        } else {
          psAddAzienda.setString(10, null);
        }

        if (azienda.getChiSiamo() != null) {
          psAddAzienda.setString(11, azienda.getChiSiamo());
        } else {
          psAddAzienda.setString(11, null);
        }

        int id = getIdMaxConvenzione();
        if (id >= 0) {
          psAddAzienda.setInt(12, id);
        }
        psAddAzienda.executeUpdate();
        System.out.println(azienda);
        connection.commit();
      } finally {
        try {
          if (psAddUtente != null) {
            psAddUtente.close();
          }

          if (psAddAzienda != null) {
            psAddAzienda.close();
          }
        } finally {
          Database.releaseConnection(connection);
        }
      }
    }

    if (utente instanceof Professore) {
      PreparedStatement psAddUtente = null;
      PreparedStatement psAddProfessore = null;
      try {
        connection = Database.getConnection();
        psAddUtente = connection.prepareStatement(queryAddUtente);

        psAddUtente.setString(1, utente.getUser());
        psAddUtente.setString(2, utente.getPassword());
        psAddUtente.setString(3, utente.getNome());
        psAddUtente.setString(4, utente.getCognome());
        psAddUtente.setString(5, "PR");// ST sta ad indicare il tipo "Studente" nel database
        // AZ=Azienda, SR=Segreteria, PR=Professore
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
      } finally {
        try {
          if (psAddUtente != null) {
            psAddUtente.close();
          }

          if (psAddProfessore != null) {
            psAddProfessore.close();
          }
        } finally {
          Database.releaseConnection(connection);
        }
      }

    }
    return true;
  }

  /**
   * Registra una convenzione nel database.
   * 
   * @param convenzione
   *          Convenzione da aggiungere
   * @throws SQLException
   * 
   * @author Iannuzzi Nicola'
   */
  public static synchronized void addConvenzione(Convenzione convenzione) throws SQLException {
    PreparedStatement psAddConvenzione = null;
    Connection connection = null;
    try {
      connection = Database.getConnection();
      psAddConvenzione = connection.prepareStatement(queryAddConvenzione);

      psAddConvenzione.setDate(1, new Date(Calendar.getInstance().getTime().getTime()));
      psAddConvenzione.setString(2, convenzione.getSpecifica());
      psAddConvenzione.executeUpdate();

      connection.commit();
    } finally {
      try {
        if (psAddConvenzione != null) {
          psAddConvenzione.close();
        }

        if (psAddConvenzione != null) {
          psAddConvenzione.close();
        }
      } finally {
        Database.releaseConnection(connection);
      }
    }
  }

  /**
   * Elimina un utente dal database.
   * 
   * @param email
   *          Email dell'utente da eliminare.
   * @return {@code true} se l'eliminazione e' ok, {@code false} altrimenti.
   * @throws SQLException
   *           Eccezione lanciata nel caso in cui non si riesce a stabilite una connessione con il
   *           database.
   * 
   * @author Caggiano Gianluca
   */
  public static synchronized boolean deleteUser(String email) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    int result = 0;

    try {
      Utente u = DatabaseGu.getUtenteById(email);
      Azienda a = null;
      if (u instanceof Azienda) {
        a = DatabaseGu.getAziendaByEmail(email);
      }
      connection = Database.getConnection();
      preparedStatement = connection.prepareStatement(queryEliminaAccount);
      preparedStatement.setString(1, email);

      result = preparedStatement.executeUpdate();
      connection.commit();
      
      if (a != null) {
        DatabaseGu.deleteConvenzione(a.getConvenzione().getId());
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
    return (result != 0);
  }

  /**
   * Elimina la convenzione di un'azienda eliminata dal database.
   * 
   * @param id Identificativo della convenzione da eliminare
   * @return {@code true} se l'eliminazione e' ok, {@code false} altrimenti.
   * @throws SQLException 
   *            Eccezione lanciata nel caso in cui non si riesce a stabilite una connessione con il
   *            database.
   */
  public static synchronized boolean deleteConvenzione(int id) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    int result = 0;

    try {
      connection = Database.getConnection();
      preparedStatement = connection.prepareStatement(queryEliminaConvenzione);
      preparedStatement.setInt(1, id);

      result = preparedStatement.executeUpdate();
      connection.commit();
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } finally {
        Database.releaseConnection(connection);
      }
    }
    return (result != 0);
  }

  /**
   * Restituisce ,se esiste, un oggetto Utente data la user di accesso.
   * 
   * @param user
   *          Username dell'utente da prelevare
   * @return {@code null} se l'utente non esiste, {@code Oggetto Utente } altrimenti.
   * @throws SQLException
   * 
   * @author Caggiano Gianluca
   */
  public static synchronized Utente getUtenteById(String user) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Utente u = null;

    try {
      connection = Database.getConnection();
      preparedStatement = connection.prepareStatement(queryGetUtenteById);
      preparedStatement.setString(1, user);

      ResultSet rs = preparedStatement.executeQuery();
      connection.commit();

      if (rs.next()) {
        String tipo = rs.getString("Tipo");
        if (tipo.equals("ST")) {
          u = new Studente();
          u.setUser(rs.getString("User"));
          u.setPassword(rs.getString("Password"));
        }
        if (tipo.equals("AZ")) {
          u = new Azienda();
          u.setUser(rs.getString("User"));
          u.setPassword(rs.getString("Password"));
        }
        if (tipo.equals("PR")) {
          u = new Professore();
          u.setUser(rs.getString("User"));
          u.setPassword(rs.getString("Password"));
        }
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
    return u;
  }

  /**
   * Restituisce ,se esiste, un oggetto Studente data una email.
   * 
   * @param email
   *          email dello studente da prelevare
   * @return {@code null} se l'utente non esiste, {@code Oggetto Studente } altrimenti.
   * @throws SQLException
   * 
   * @author Caggiano Gianluca
   */
  public static synchronized Studente getStudenteByEmail(String email) throws SQLException {
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
        String idRichiesta = rs.getString("RichiestaTirocinioID");
        if (idRichiesta != null) {
          studente
              .setRichiestaTirocinio(DatabasePf.getRichiestaById(Integer.parseInt(idRichiesta)));
        }
        studente.setAbilitato(rs.getBoolean("abilitato"));
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
    if (studente.getUser() == null) {
      return null;
    } else {
      return studente;
    }
  }

  /**
   * Restituisce, se esiste, un oggetto Studente data una matricola.
   * 
   * @param matricola
   *          Matricola dello studente da prelevare
   * @return {@code null} se l'utente non esiste, {@code Oggetto Studente } altrimenti.
   * @throws SQLException
   * 
   * @author Caggiano Gianluca
   */
  public static synchronized Studente getStudenteByMatricola(String matricola) throws SQLException {
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
        studente.setMatricola(matricola);
        studente.setDataNascita(rs.getString("DataNascita"));
        studente.setLuogoNascita(rs.getString("LuogoNascita"));
        String idRichiesta = rs.getString("RichiestaTirocinioID");
        if (idRichiesta != null) {
          studente
              .setRichiestaTirocinio(DatabasePf.getRichiestaById(Integer.parseInt(idRichiesta)));
        }
        studente.setAbilitato(rs.getBoolean("abilitato"));
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
    if (studente.getUser() == null) {
      return null;
    } else {
      return studente;
    }
  }

  /**
   * Restituisce ,se esiste, un oggetto Azienda data una email.
   * 
   * @param email
   *          Email dell'azienda da prelevare
   * @return {@code null} se l'utente non esiste, {@code Oggetto Azienda } altrimenti.
   * @throws SQLException
   * 
   * @author Iannuzzi Nicolà
   */
  public static synchronized Azienda getAziendaByEmail(String email) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Azienda azienda = new Azienda();

    try {
      connection = Database.getConnection();
      preparedStatement = connection.prepareStatement(queryGetAziendaEmail);
      preparedStatement.setString(1, email);

      ResultSet rs = preparedStatement.executeQuery();
      connection.commit();

      while (rs.next()) {
        azienda.setUser(rs.getString("User"));
        azienda.setPassword(rs.getString("Password"));
        azienda.setNome(rs.getString("Nome"));
        azienda.setCognome(rs.getString("Cognome"));
        azienda.setTipo(rs.getString("Tipo"));
        azienda.setLuogoNascita(rs.getString("LuogoNascita"));
        azienda.setDataNascita(rs.getString("DataNascita"));
        azienda.setDenominazione(rs.getString("Denominazione"));
        azienda.setCitta(rs.getString("Citta"));
        azienda.setCap(rs.getString("CAP"));
        azienda.setVia(rs.getString("Via"));
        if (rs.getString("Telefono") != null) {
          azienda.setTelefono(rs.getString("Telefono"));
        }
        if (rs.getString("SitoWeb") != null) {
          azienda.setSitoWeb(rs.getString("SitoWeb"));
        }
        if (rs.getString("ChiSiamo") != null) {
          azienda.setChiSiamo(rs.getString("ChiSiamo"));
        }
        String idConvenzione = rs.getString("ConvenzioneID");
        if (idConvenzione != null) {
          azienda.setConvenzione(DatabaseGu.getConvenzioneById(Integer.parseInt(idConvenzione)));
        }
        azienda.setAbilitato(rs.getBoolean("abilitato"));
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
    if (azienda.getUser() == null) {
      return null;
    } else {
      return azienda;
    }
  }

  /**
   * Restituisce, se esiste, un oggetto Professore data una email.
   * 
   * @param email
   *          Email del professore da prelevare
   * @return {@code null} se l'utente non esiste, {@code Oggetto Professore } altrimenti.
   * @throws SQLException
   * 
   * @author Iannuzzi Nicolà
   */
  public static synchronized Professore getProfessoreByEmail(String email) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Professore professore = new Professore();

    try {
      connection = Database.getConnection();
      preparedStatement = connection.prepareStatement(queryGetProfessoreEmail);
      preparedStatement.setString(1, email);

      ResultSet rs = preparedStatement.executeQuery();
      connection.commit();

      while (rs.next()) {
        professore.setUser(rs.getString("User"));
        professore.setPassword(rs.getString("Password"));
        professore.setNome(rs.getString("Nome"));
        professore.setCognome(rs.getString("Cognome"));
        professore.setMateria(rs.getString("Materia"));
        professore.setAutorizzo(rs.getBoolean("Autorizzato"));
        professore.setTipo(rs.getString("Tipo"));
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
    if (professore.getUser() == null) {
      return null;
    } else {
      return professore;
    }
  }

  /**
   * Restituisce, se esiste, un oggetto Segreteria dato uno username.
   * 
   * @param username
   *          Username della segreteria
   * @return {@code null} se l'utente non esiste, {@code Oggetto Segreteria} altrimenti.
   * @throws SQLException
   * 
   * @author Iannuzzi Nicolà
   */
  public static synchronized Segreteria getSegreteriaByUser(String username) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Segreteria segreteria = new Segreteria();

    try {
      connection = Database.getConnection();
      preparedStatement = connection.prepareStatement(queryGetSegreteriaUsername);
      preparedStatement.setString(1, username);

      ResultSet rs = preparedStatement.executeQuery();
      connection.commit();

      while (rs.next()) {
        segreteria.setUser(rs.getString("User"));
        segreteria.setPassword(rs.getString("Password"));
        segreteria.setNome(rs.getString("Nome"));
        segreteria.setCognome(rs.getString("Cognome"));
        segreteria.setEmail(rs.getString("Email"));
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
    if (segreteria.getUser() == null) {
      return null;
    } else {
      return segreteria;
    }
  }

  /**
   * Restituisce un ArrayList di Aziende che hanno una convenzione con l'Università.
   * 
   * @return ArrayList Azienda
   * @throws SQLException
   *           Eccezione lanciata
   * @author Gianluca Caggiano
   */
  public static synchronized ArrayList<Azienda> doRetriveAllAziende() throws SQLException {
    Connection connection = null;
    java.sql.Statement statement = null;

    String sql = "SELECT * FROM utente, azienda ";
    sql = sql + "WHERE utente.User=azienda.Email ";
    sql = sql + "AND azienda.ConvenzioneID IS NOT NULL AND abilitato=1";
    ArrayList<Azienda> arrayList = new ArrayList<Azienda>();
    Azienda azienda;

    try {
      connection = Database.getConnection();
      statement = connection.createStatement();

      ResultSet res = statement.executeQuery(sql);

      while (res.next()) {
        azienda = new Azienda();
        azienda.setUser(res.getString("User"));
        azienda.setNome(res.getString("Nome"));
        azienda.setCognome(res.getString("Cognome"));
        azienda.setPassword(res.getString("Password"));
        azienda.setTipo(res.getString("Tipo"));
        azienda.setDataNascita(res.getString("DataNascita"));
        azienda.setLuogoNascita(res.getString("LuogoNascita"));
        azienda.setDenominazione(res.getString("Denominazione"));
        azienda.setCap(res.getString("CAP"));
        azienda.setCitta(res.getString("Citta"));
        azienda.setVia(res.getString("Via"));
        if (res.getString("Telefono") != null) {
          azienda.setTelefono(res.getString("Telefono"));
        }
        if (res.getString("SitoWeb") != null) {
          azienda.setSitoWeb(res.getString("SitoWeb"));
        }
        if (res.getString("ChiSiamo") != null) {
          azienda.setChiSiamo(res.getString("ChiSiamo"));
        }
        String idConvenzione = res.getString("ConvenzioneID");
        if (idConvenzione != null) {
          azienda.setConvenzione(DatabaseGu.getConvenzioneById(Integer.parseInt(idConvenzione)));
        }
        azienda.setAbilitato(res.getBoolean("abilitato"));
        System.out.println(azienda);
        arrayList.add(azienda);
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

    return arrayList;
  }

  /**
   * Restituisce un ArrayList di Professori.
   * 
   * @return ArrayList Professore
   * @throws SQLException
   *           Eccezione lanciata
   * @author Iannuzzi Nicola'
   */
  public static synchronized ArrayList<Professore> doRetriveAllProfessore() throws SQLException {
    Connection connection = null;
    java.sql.Statement statement = null;

    String sql = "SELECT * FROM utente, professore ";
    sql = sql + "WHERE utente.User=professore.Email AND professore.Autorizzato=1";
    ArrayList<Professore> arrayList = new ArrayList<Professore>();
    Professore professore;

    try {
      connection = Database.getConnection();
      statement = connection.createStatement();

      ResultSet res = statement.executeQuery(sql);

      while (res.next()) {
        professore = new Professore();
        professore.setUser(res.getString("User"));
        professore.setPassword(res.getString("Password"));
        professore.setNome(res.getString("Nome"));
        professore.setCognome(res.getString("Cognome"));
        professore.setMateria(res.getString("Materia"));
        professore.setAutorizzo(res.getBoolean("Autorizzato"));

        arrayList.add(professore);
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

    return arrayList;
  }

  /**
   * Restituisce, se esiste, un oggetto di tipo convenzione con un certo id.
   * 
   * @param id
   *          Identificativo della convezione da prelevare
   * @return {@code null} se la convenzione non esiste, {@code Oggetto Convenzione } altrimenti.
   * @throws SQLException
   * 
   * @author Caggiano Gianluca
   */
  private static synchronized Convenzione getConvenzioneById(int id) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    Convenzione convenzione = new Convenzione();

    try {
      connection = Database.getConnection();
      preparedStatement = connection.prepareStatement(queryGetConvenzioneById);
      preparedStatement.setInt(1, id);

      ResultSet rs = preparedStatement.executeQuery();
      connection.commit();

      while (rs.next()) {
        convenzione.setId(rs.getInt("ID"));
        convenzione.setData(rs.getString("Data"));
        convenzione.setSpecifica(rs.getString("Specifiche"));
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
    if (convenzione.getId() == -1) {
      return null;
    } else {
      return convenzione;
    }
  }

  /**
   * Restituisce l'ultima convenzione creata in ordine temporale.
   * 
   * @return {@code -1} non e' presente nessuna convenzione, {@code Oggetto Convenzione} altrimenti.
   * @throws SQLException
   *           Eccezione lanciata
   */
  private static synchronized int getIdMaxConvenzione() throws SQLException {
    Connection connection = null;
    java.sql.Statement statement = null;

    int id = -1;
    try {
      connection = Database.getConnection();
      statement = connection.createStatement();

      ResultSet rs = statement.executeQuery(queryGetMaxConvenzione);

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
   * Restituisce un ArrayList di Utenti (Azienda) che non sono ancora abilitati.
   * 
   * @return ArrayList Utenti
   * @throws SQLException
   *           Eccezione lanciata
   * @author Iannuzzi Nicola'
   */
  public static synchronized ArrayList<Utente> doRetriveAllNonAbilitatiAziende()
      throws SQLException {
    Connection connection = null;
    java.sql.Statement statement = null;

    String sql = "SELECT * FROM utente, azienda ";
    sql = sql + "WHERE  utente.User = azienda.Email  AND  azienda.abilitato=0";
    ArrayList<Utente> arrayList = new ArrayList<Utente>();

    try {
      connection = Database.getConnection();
      statement = connection.createStatement();

      ResultSet res = statement.executeQuery(sql);

      while (res.next()) {
        arrayList.add(DatabaseGu.getAziendaByEmail(res.getString("User")));
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

    return arrayList;
  }

  /**
   * Restituisce un ArrayList di Utenti (Professori) che non sono ancora abilitati.
   * 
   * @return ArrayList Utenti
   * @throws SQLException
   *           Eccezione lanciata
   * @author Iannuzzi Nicola'
   */
  public static synchronized ArrayList<Utente> doRetriveAllNonAbilitatiProfessori()
      throws SQLException {
    Connection connection = null;
    java.sql.Statement statement = null;

    String sql = "SELECT * FROM utente, professore ";
    sql = sql + "WHERE  utente.User = professore.Email  AND  professore.Autorizzato=0";
    ArrayList<Utente> arrayList = new ArrayList<Utente>();

    try {
      connection = Database.getConnection();
      statement = connection.createStatement();

      ResultSet res = statement.executeQuery(sql);

      while (res.next()) {
        arrayList.add(DatabaseGu.getProfessoreByEmail(res.getString("User")));
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

    return arrayList;
  }

  /**
   * Setta abilitato 1 in Azienda.
   * 
   * @param email
   *          Email dell'azienda
   * @return boolean
   * @throws SQLException
   * 
   * @author Iannuzzi Nicola'
   */
  public static synchronized boolean setAbilitatoAzienda(String email) throws SQLException {
    Connection connection = null;
    Statement statement = null;

    String query = "UPDATE azienda SET abilitato=1 WHERE azienda.Email='" + email + "'";

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
   * Setta autorizzato 1 in Professore.
   * 
   * @param email
   *          Email del professore
   * @return boolean
   * @throws SQLException
   * 
   * @author Iannuzzi Nicola'
   */
  public static synchronized boolean setAbilitatoProfessore(String email) throws SQLException {
    Connection connection = null;
    Statement statement = null;

    String query = "UPDATE professore SET Autorizzato=1 WHERE professore.Email='" + email + "'";

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
  private static String queryGetConvenzioneById;
  private static String queryGetMaxConvenzione;
  private static String queryEliminaAccount;
  private static String queryEliminaConvenzione;

  static {
    // Query universale per tutti gli utenti
    queryAddUtente = "Insert into utente (User, Password, Nome, Cognome, Tipo) VALUES (?,?,?,?,?);";
    queryAddStudente = "Insert into studente "
        + "(Matricola, Email, DataNascita, LuogoNascita, abilitato) VALUES (?,?,?,?,?);";
    queryAddAzienda = "Insert into azienda "
        + "(Email, LuogoNascita, DataNascita, Denominazione, Citta, "
        + "CAP, Via, abilitato, Telefono, SitoWeb, ChiSiamo, ConvenzioneID) "
        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
    queryAddProfessore = "Insert into professore (Email, Autorizzato, Materia) VALUES (?,?,?);";
    queryAddConvenzione = "Insert into convenzione (Data, Specifiche) VALUES (?,?);";

    queryGetUtenteById = "SELECT * From utente WHERE utente.User=?;";
    queryGetStudenteEmail = "SELECT * From utente,studente "
        + "WHERE studente.Email=utente.User AND utente.User=?;";
    queryGetStudenteMatricola = "SELECT * From utente,studente "
        + "WHERE studente.Email=utente.User AND studente.Matricola=?;";
    queryGetAziendaEmail = "SELECT * From utente,azienda "
        + "WHERE azienda.Email=utente.User AND azienda.Email=?;";
    queryGetProfessoreEmail = "SELECT * From utente, professore "
        + "WHERE utente.User = professore.Email AND professore.Email=?";
    queryGetSegreteriaUsername = "SELECT * From utente, segreteria "
        + "WHERE utente.User = segreteria.Username AND segreteria.Username=?";
    queryGetConvenzioneById = "SELECT * FROM convenzione WHERE ID=?";
    queryGetMaxConvenzione = "SELECT MAX(ID) FROM Convenzione";

    queryEliminaAccount = "DELETE FROM utente WHERE utente.user=?;";
    queryEliminaConvenzione = "DELETE FROM convenzione WHERE convenzione.ID=?;";
  }
}