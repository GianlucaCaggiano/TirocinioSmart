package storagelayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import gestioneutente.Azienda;
import gestioneutente.Convenzione;
import gestioneutente.Professore;
import gestioneutente.Studente;
import gestioneutente.Utente;

import java.sql.SQLException;

import org.junit.Test;

/**
 * Classe per i test di unità DatabaseGU.
 * 
 * @author Caggiano Gianluca
 *
 */
public class TestDatabaseGu {

  /**
   * Test method for
   * {@link storagelayer.DatabaseGu#addUser(gestioneutente.Utente)}.
   */
  @Test
  public void testAddUser() {
    // Registrazione studente
    Studente s = new Studente("0512103879", "testStudente@studenti.unisa.it", "testStudente", "testNome", "testCognome",
        "1989/1/1", "Poggiomarino", true);
    try {
      Boolean done = DatabaseGu.addUser(s);
      assertEquals(true, done);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    // Registrazione azienda
    Convenzione convenzione = new Convenzione();
    convenzione.setSpecifica("Test Specifica Convenzione");
    Azienda a = new Azienda("azienda@test.it", "testAzienda", "testNome", "testCognome", "Nocera", "1956/2/8",
        "Test Azienda s.r.l.", "Napoli", "80100", "Via Pioppi, 10", false);
    try {
      DatabaseGu.addConvenzione(convenzione);
      Boolean done = DatabaseGu.addUser(a);
      assertEquals(true, done);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    // Registra Azienda con tutti i campi opzionali
    convenzione = new Convenzione();
    convenzione.setSpecifica("Test Specifica Convenzione per Azienda con tutti i campi");
    a = new Azienda("aziendaCompleta@test.it", "testAzienda", "testNome", "testCognome", "Nocera", "1956/2/8",
        "Test Azienda s.r.l.", "Napoli", "80100", "Via Pioppi, 10", false);
    a.setTelefono("0825477958");
    a.setSitoWeb("www.aziendatest.it");
    a.setChiSiamo("Questo è un test");
    try {
      DatabaseGu.addConvenzione(convenzione);
      Boolean done = DatabaseGu.addUser(a);
      assertEquals(true, done);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    // Registrazione Professore
    Professore p = new Professore("testProfessore@unisa.it", "profPassword", "Test", "Test", false, "Programmazione 1");
    try {
      Boolean done = DatabaseGu.addUser(p);
      assertEquals(true, done);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Test method for
   * {@link storagelayer.DatabaseGu#deleteUser(java.lang.String)}.
   */
  @Test
  public void testDeleteUser() {
    // Elimina account Studente
    String email = "testStudente@studenti.unisa.it";
    try {
      Boolean done = DatabaseGu.deleteUser(email);
      assertEquals(true, done);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    // Elimina account Azienda
    email = "azienda@test.it";

    try {

      Boolean done = DatabaseGu.deleteUser(email);
      assertEquals(true, done);

    } catch (SQLException e) {
      e.printStackTrace();
    }

    // Elimina account Azienda completa
    email = "aziendaCompleta@test.it";
    try {
      Boolean done = DatabaseGu.deleteUser(email);
      assertEquals(true, done);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    // Elimina account Professore
    email = "testProfessore@unisa.it";
    try {
      Boolean done = DatabaseGu.deleteUser(email);
      assertEquals(true, done);
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  /**
   * Test method for
   * {@link storagelayer.DatabaseGu#getUtenteById(java.lang.String)}.
   */
  @Test
  public void testGetUtenteById() {
    String user = "s.ciao@unisa.it";
    try {
      Utente u = DatabaseGu.getUtenteById(user);
      assertEquals(null, u);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Test method for
   * {@link storagelayer.DatabaseGu#getStudenteByEmail(java.lang.String)}.
   */
  @Test
  public void testGetStudenteByEmail() {
    String email = "ciccio@studenti.unisa.it";
    try {
      String atteso = "gestioneutente.Studente "
          + "[user= ciccio@studenti.unisa.it, password= 987654321, nome=Ciccio, cognome=Pasticcio] "
          + "[matricola= 0512105879, data di nascita= 1995-07-08, luogo di nascita=Bari, "
          + "richiesta di tirocinio=null abilitato=false]";
      String test = DatabaseGu.getStudenteByEmail(email).toString();
      assertEquals(atteso, test);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    // getStudente con richiesta
    email = "francesco@studenti.unisa.it";
    try {
      String atteso = "gestioneutente.Studente [user= francesco@studenti.unisa.it, "
          + "password= 1234567891, nome=francesco, cognome=grasso] [matricola= 0512103699, "
          + "data di nascita= 1996-08-12, luogo di nascita=Nola, "
          + "richiesta di tirocinio=RichiestaTirocinio [azienda=gestioneutente.Azienda "
          + "[user= accasoftware@test.it, password= 3216549870, nome=Pasquale, cognome=Michele]"
          + "Azienda [luogoNascita=Firenze, dataNascita=1956-08-04, denominazione=HS s.r.l., "
          + "citta=Bisaccia, CAP=83044, via=Via pioppi, 13, tipo=AZ, telefono=0827488965, "
          + "chiSiamo=test, sitoWeb=www.accasoftware.it, convenzione=Convenzione "
          + "[id=7, data=2018-01-30, specifica=Serve per il test], abilitato=false], "
          + "professore=gestioneutente.Professore [user= pino@unisa.it, password= 9876543210, "
          + "nome=Pino, cognome=Sbirillino]Professore [autorizzo=true, materia=PW], "
          + "convalidaAzienda=false, convalidaProf=false] abilitato=false]";
      String test = DatabaseGu.getStudenteByEmail(email).toString();
      assertEquals(atteso, test);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    //user non esistente
    String user = "ciao";
    try {
      Utente u = DatabaseGu.getStudenteByEmail(user);
      assertEquals(null, u);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }

  /**
   * Test method for
   * {@link storagelayer.DatabaseGu#getStudenteByMatricola(java.lang.String)}.
   */
  @Test
  public void testGetStudenteByMatricola() {
    String matricola = "0512105879";
    try {
      String atteso = "gestioneutente.Studente "
          + "[user= ciccio@studenti.unisa.it, password= 987654321, nome=Ciccio, cognome=Pasticcio] "
          + "[matricola= 0512105879, data di nascita= 1995-07-08, luogo di nascita=Bari, "
          + "richiesta di tirocinio=null abilitato=false]";
      String test = DatabaseGu.getStudenteByMatricola(matricola).toString();
      assertEquals(atteso, test);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    // getStudente con richiesta
    matricola = "0512103699";
    try {
      String atteso = "gestioneutente.Studente [user= francesco@studenti.unisa.it, "
          + "password= 1234567891, nome=francesco, cognome=grasso] [matricola= 0512103699, "
          + "data di nascita= 1996-08-12, luogo di nascita=Nola, "
          + "richiesta di tirocinio=RichiestaTirocinio [azienda=gestioneutente.Azienda "
          + "[user= accasoftware@test.it, password= 3216549870, nome=Pasquale, cognome=Michele]"
          + "Azienda [luogoNascita=Firenze, dataNascita=1956-08-04, denominazione=HS s.r.l., "
          + "citta=Bisaccia, CAP=83044, via=Via pioppi, 13, tipo=AZ, telefono=0827488965, "
          + "chiSiamo=test, sitoWeb=www.accasoftware.it, convenzione=Convenzione "
          + "[id=7, data=2018-01-30, specifica=Serve per il test], abilitato=false], "
          + "professore=gestioneutente.Professore [user= pino@unisa.it, password= 9876543210, "
          + "nome=Pino, cognome=Sbirillino]Professore [autorizzo=true, materia=PW], "
          + "convalidaAzienda=false, convalidaProf=false] abilitato=false]";
      String test = DatabaseGu.getStudenteByMatricola(matricola).toString();
      assertEquals(atteso, test);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    //user non esistente
    String user = "ciao";
    try {
      Studente u = DatabaseGu.getStudenteByMatricola(user);
      assertEquals(null, u);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Test method for
   * {@link storagelayer.DatabaseGu#getAziendaByEmail(java.lang.String)}.
   */
  @Test

  public void testGetAziendaByEmail() {
    // idConvezione = null
    
    String mail = "testsoftware@unina.it";
    try {
      Azienda u = DatabaseGu.getAziendaByEmail(mail);
      assertEquals(null, u.getConvenzione());
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
  
  /**
   * Test method for
   * {@link storagelayer.DatabaseGu#getProfessoreByEmail(java.lang.String)}.
   */
  @Test
  public void testGetProfessoreByEmail() {
    // user non esistente
    String user = "ciao";
    try {
      Utente u = DatabaseGu.getProfessoreByEmail(user);
      assertEquals(null, u);
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Test method for
   * {@link storagelayer.DatabaseGu#getSegreteriaByUser(java.lang.String)}.
   */
  @Test
  public void testGetSegreteriaByUser() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link storagelayer.DatabaseGu#doRetriveAllAziende()}.
   */
  @Test
  public void testDoRetriveAllAziende() {
    fail("Not yet implemented");
  }

  /**
   * Test method for {@link storagelayer.DatabaseGu#doRetriveAllProfessore()}.
   */
  @Test
  public void testDoRetriveAllProfessore() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link storagelayer.DatabaseGu#doRetriveAllNonAbilitatiAziende()}.
   */
  @Test
  public void testDoRetriveAllNonAbilitatiAziende() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link storagelayer.DatabaseGu#doRetriveAllNonAbilitatiProfessori()}.
   */
  @Test
  public void testDoRetriveAllNonAbilitatiProfessori() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link storagelayer.DatabaseGu#setAbilitatoAzienda(java.lang.String)}.
   */
  @Test
  public void testSetAbilitatoAzienda() {
    fail("Not yet implemented");
  }

  /**
   * Test method for
   * {@link storagelayer.DatabaseGu#setAbilitatoProfessore(java.lang.String)}.
   */
  @Test
  public void testSetAbilitatoProfessore() {
    fail("Not yet implemented");
  }

}
