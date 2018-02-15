package gestioneutente;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import storagelayer.DatabaseGu;

/**
 * Classe per il test della servlet {@link RegistrazioneStudente}.
 * 
 * @author Caggiano Gianluca
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRegistrazioneStudente {

  @Mock
  HttpServletRequest request;
  @Mock
  HttpServletResponse response;
  @Mock
  HttpSession sessione;
  @Mock
  RequestDispatcher dispatcher;

  private static Studente unStudente;
  private static Studente studente;

  /**
   * Inizializzazione degli oggetti utili nel test.
   */
  @BeforeClass
  public static void init() {
    studente = new Studente();
    studente.setMatricola("0512103617");
    studente.setUser("n.iannuzzzi@studenti.unisa.it");
    studente.setPassword("123456");
    studente.setNome("Nicola");
    studente.setCognome("Iannuzzi");
    studente.setDataNascita("1996-5-5");
    studente.setLuogoNascita("Bisaccia");

    try {
      DatabaseGu.addUser(studente);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    unStudente = new Studente();
    unStudente.setMatricola("0512103619");
    unStudente.setUser("m.verdi1@studenti.unisa.it");
    unStudente.setPassword("123456");
    unStudente.setNome("Mario");
    unStudente.setCognome("Verdi");
    unStudente.setDataNascita("1996/6/23");
    unStudente.setLuogoNascita("Avellino");

  }

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  /**
   * Test case con id TC_GU_1.1_1
   * 
   * @throws ServletException Eccezzione lanciata dalla servlet
   * @throws IOException Eccezione lanciata per errore di IO
   */
  @Test
  public void test01_Matricola() throws ServletException, IOException {
    when(request.getParameter("matricola")).thenReturn("05121");
    when(request.getParameter("email")).thenReturn("");
    when(request.getParameter("password")).thenReturn("");
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=matricola non valida"))
        .thenReturn(dispatcher);

    try {
      new RegistrazioneStudente().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }
  }

  /**
   * Test case con id TC_GU_1.1_2
   * 
   * @throws ServletException Eccezzione lanciata dalla servlet
   * @throws IOException Eccezione lanciata per errore di IO
   */
  @Test
  public void test02_Matricola() throws ServletException, IOException {
    when(request.getParameter("matricola")).thenReturn("051210361k");
    when(request.getParameter("email")).thenReturn("");
    when(request.getParameter("password")).thenReturn("");
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=matricola non valida"))
        .thenReturn(dispatcher);

    try {
      new RegistrazioneStudente().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }
  }

  /**
   * Test case con id TC_GU_1.1_3
   * 
   * @throws ServletException Eccezzione lanciata dalla servlet
   * @throws IOException Eccezione lanciata per errore di IO
   */
  @Test
  public void test03_Email() throws ServletException, IOException {
    when(request.getParameter("matricola")).thenReturn(studente.getMatricola());
    when(request.getParameter("email")).thenReturn("");
    when(request.getParameter("password")).thenReturn("");
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=Email non valida"))
        .thenReturn(dispatcher);

    try {
      new RegistrazioneStudente().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }
  }

  /**
   * Test case con id TC_GU_1.1_4
   * 
   * @throws ServletException Eccezzione lanciata dalla servlet
   * @throws IOException Eccezione lanciata per errore di IO
   */
  @Test
  public void test04_Email() throws ServletException, IOException {
    when(request.getParameter("matricola")).thenReturn(studente.getMatricola());
    when(request.getParameter("email")).thenReturn("1");
    when(request.getParameter("password")).thenReturn("");
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=Email non valida"))
        .thenReturn(dispatcher);

    try {
      new RegistrazioneStudente().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }
  }
  
  /**
   * Test case con id TC_GU_1.1_5
   * 
   * @throws ServletException Eccezzione lanciata dalla servlet
   * @throws IOException Eccezione lanciata per errore di IO
   */
  @Test
  public void test05_Password() throws ServletException, IOException {
    when(request.getParameter("matricola")).thenReturn(studente.getMatricola());
    when(request.getParameter("email")).thenReturn(studente.getUser());
    when(request.getParameter("password")).thenReturn("123");
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=Password non valida"))
        .thenReturn(dispatcher);

    try {
      new RegistrazioneStudente().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }
  }

  /**
   * Test case con id TC_GU_1.1_6
   * 
   * @throws ServletException Eccezzione lanciata dalla servlet
   * @throws IOException Eccezione lanciata per errore di IO
   */
  @Test
  public void test06_Password() throws ServletException, IOException {
    when(request.getParameter("matricola")).thenReturn(studente.getMatricola());
    when(request.getParameter("email")).thenReturn(studente.getUser());
    when(request.getParameter("password")).thenReturn("123456@");
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=Password non valida"))
        .thenReturn(dispatcher);

    try {
      new RegistrazioneStudente().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }
  }

  /**
   * Test case con id TC_GU_1.1_8
   * 
   * @throws ServletException Eccezzione lanciata dalla servlet
   * @throws IOException Eccezione lanciata per errore di IO
   */
  @Test
  public void test08_Nome() throws ServletException, IOException {
    when(request.getParameter("matricola")).thenReturn(studente.getMatricola());
    when(request.getParameter("email")).thenReturn(studente.getUser());
    when(request.getParameter("password")).thenReturn(unStudente.getPassword());
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=nome non valido"))
        .thenReturn(dispatcher);

    try {
      new RegistrazioneStudente().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }
  }
  
  /**
   * Test case con id TC_GU_1.1_9
   * 
   * @throws ServletException Eccezzione lanciata dalla servlet
   * @throws IOException Eccezione lanciata per errore di IO
   */
  @Test
  public void test09_Nome() throws ServletException, IOException {
    when(request.getParameter("matricola")).thenReturn(studente.getMatricola());
    when(request.getParameter("email")).thenReturn(studente.getUser());
    when(request.getParameter("password")).thenReturn(unStudente.getPassword());
    when(request.getParameter("nome")).thenReturn("Mari@");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=nome non valido"))
        .thenReturn(dispatcher);

    try {
      new RegistrazioneStudente().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }
  }

  /**
   * Test case con id TC_GU_1.1_10
   * 
   * @throws ServletException Eccezzione lanciata dalla servlet
   * @throws IOException Eccezione lanciata per errore di IO
   */
  @Test
  public void test10_Cognome() throws ServletException, IOException {
    when(request.getParameter("matricola")).thenReturn(studente.getMatricola());
    when(request.getParameter("email")).thenReturn(studente.getUser());
    when(request.getParameter("password")).thenReturn(unStudente.getPassword());
    when(request.getParameter("nome")).thenReturn(unStudente.getNome());
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=cognome non valido"))
        .thenReturn(dispatcher);

    try {
      new RegistrazioneStudente().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }
  }
  

  /**
   * Test case con id TC_GU_1.1_11
   * 
   * @throws ServletException Eccezzione lanciata dalla servlet
   * @throws IOException Eccezione lanciata per errore di IO
   */
  @Test
  public void test11_Cognome() throws ServletException, IOException {
    when(request.getParameter("matricola")).thenReturn(studente.getMatricola());
    when(request.getParameter("email")).thenReturn(studente.getUser());
    when(request.getParameter("password")).thenReturn(unStudente.getPassword());
    when(request.getParameter("nome")).thenReturn(unStudente.getNome());
    when(request.getParameter("cognome")).thenReturn("Verd$");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=cognome non valido"))
        .thenReturn(dispatcher);

    try {
      new RegistrazioneStudente().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }
  }

  /**
   * Test case con id TC_GU_1.1_12
   * 
   * @throws ServletException Eccezzione lanciata dalla servlet
   * @throws IOException Eccezione lanciata per errore di IO
   */
  @Test
  public void test12_DataNascita() throws ServletException, IOException {
    when(request.getParameter("matricola")).thenReturn(studente.getMatricola());
    when(request.getParameter("email")).thenReturn(studente.getUser());
    when(request.getParameter("password")).thenReturn(unStudente.getPassword());
    when(request.getParameter("nome")).thenReturn(unStudente.getNome());
    when(request.getParameter("cognome")).thenReturn(unStudente.getCognome());
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=Data di nascita errata"))
        .thenReturn(dispatcher);

    try {
      new RegistrazioneStudente().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }
  }

  /**
   * Test case con id TC_GU_1.1_13
   * 
   * @throws ServletException Eccezzione lanciata dalla servlet
   * @throws IOException Eccezione lanciata per errore di IO
   */
  @Test
  public void test13_DataNascita() throws ServletException, IOException {
    when(request.getParameter("matricola")).thenReturn(studente.getMatricola());
    when(request.getParameter("email")).thenReturn(studente.getUser());
    when(request.getParameter("password")).thenReturn(unStudente.getPassword());
    when(request.getParameter("nome")).thenReturn(unStudente.getNome());
    when(request.getParameter("cognome")).thenReturn(unStudente.getCognome());
    when(request.getParameter("dataNascita")).thenReturn("12554734");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=Data di nascita errata"))
        .thenReturn(dispatcher);

    try {
      new RegistrazioneStudente().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }
  }
  
  /**
   * Test case con id TC_GU_1.1_14
   * 
   * @throws ServletException Eccezzione lanciata dalla servlet
   * @throws IOException Eccezione lanciata per errore di IO
   */
  @Test
  public void test14_LuogoNascita() throws ServletException, IOException {
    when(request.getParameter("matricola")).thenReturn(studente.getMatricola());
    when(request.getParameter("email")).thenReturn(studente.getUser());
    when(request.getParameter("password")).thenReturn(unStudente.getPassword());
    when(request.getParameter("nome")).thenReturn(unStudente.getNome());
    when(request.getParameter("cognome")).thenReturn(unStudente.getCognome());
    when(request.getParameter("dataNascita")).thenReturn(unStudente.getDataNascita());
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=Citta' non valida"))
        .thenReturn(dispatcher);

    try {
      new RegistrazioneStudente().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }
  }
  
  /**
   * Test case con id TC_GU_1.1_15
   * 
   * @throws ServletException Eccezzione lanciata dalla servlet
   * @throws IOException Eccezione lanciata per errore di IO
   */
  @Test
  public void test15_LuogoNascita() throws ServletException, IOException {
    when(request.getParameter("matricola")).thenReturn(studente.getMatricola());
    when(request.getParameter("email")).thenReturn(studente.getUser());
    when(request.getParameter("password")).thenReturn(unStudente.getPassword());
    when(request.getParameter("nome")).thenReturn(unStudente.getNome());
    when(request.getParameter("cognome")).thenReturn(unStudente.getCognome());
    when(request.getParameter("dataNascita")).thenReturn(unStudente.getDataNascita());
    when(request.getParameter("luogoNascita")).thenReturn("@");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=Citta' non valida"))
        .thenReturn(dispatcher);

    try {
      new RegistrazioneStudente().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }
  }

  /**
   * Test case con id TC_GU_1.1_16
   * 
   * @throws ServletException Eccezzione lanciata dalla servlet
   * @throws IOException Eccezione lanciata per errore di IO
   */
  @Test
  public void test16_MatricolaPresente() throws ServletException, IOException {
    when(request.getParameter("matricola")).thenReturn(studente.getMatricola());
    when(request.getParameter("email")).thenReturn(studente.getUser());
    when(request.getParameter("password")).thenReturn(unStudente.getPassword());
    when(request.getParameter("nome")).thenReturn(unStudente.getNome());
    when(request.getParameter("cognome")).thenReturn(unStudente.getCognome());
    when(request.getParameter("dataNascita")).thenReturn(unStudente.getDataNascita());
    when(request.getParameter("luogoNascita")).thenReturn(unStudente.getLuogoNascita());
    when(request.getRequestDispatcher("/registrazione.jsp?errore=Utente gia' presente nel sistema"))
        .thenReturn(dispatcher);

    try {
      new RegistrazioneStudente().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }
  }

  /**
   * Test case con id TC_GU_1.1_17
   * 
   * @throws ServletException Eccezzione lanciata dalla servlet
   * @throws IOException Eccezione lanciata per errore di IO
   */
  @Test
  public void test17_EmailPresente() throws ServletException, IOException {
    when(request.getParameter("matricola")).thenReturn(unStudente.getMatricola());
    when(request.getParameter("email")).thenReturn(studente.getUser());
    when(request.getParameter("password")).thenReturn(unStudente.getPassword());
    when(request.getParameter("nome")).thenReturn(unStudente.getNome());
    when(request.getParameter("cognome")).thenReturn(unStudente.getCognome());
    when(request.getParameter("dataNascita")).thenReturn(unStudente.getDataNascita());
    when(request.getParameter("luogoNascita")).thenReturn(unStudente.getLuogoNascita());
    when(request.getRequestDispatcher("/registrazione.jsp?errore=Utente gia' presente nel sistema"))
        .thenReturn(dispatcher);

    try {
      new RegistrazioneStudente().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }
  }
  
  /**
   * Test case con id TC_GU_1.1_18
   * 
   * @throws ServletException Eccezzione lanciata dalla servlet
   * @throws IOException Eccezione lanciata per errore di IO
   */
  @Test
  public void test18_TroppoPiccolo() throws ServletException, IOException {
    when(request.getParameter("matricola")).thenReturn(unStudente.getMatricola());
    when(request.getParameter("email")).thenReturn(unStudente.getUser());
    when(request.getParameter("password")).thenReturn(unStudente.getPassword());
    when(request.getParameter("nome")).thenReturn(unStudente.getNome());
    when(request.getParameter("cognome")).thenReturn(unStudente.getCognome());
    when(request.getParameter("dataNascita")).thenReturn("2005/06/23");
    when(request.getParameter("luogoNascita")).thenReturn(unStudente.getLuogoNascita());
    when(request.getRequestDispatcher("/registrazione.jsp?errore="
            + "Mi sembri un pochino troppo piccolo per essere uno studente universitario :-P"))
        .thenReturn(dispatcher);

    try {
      new RegistrazioneStudente().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }
  }

  /**
   * Test case con id TC_GU_1.1_19
   * 
   * @throws ServletException Eccezzione lanciata dalla servlet
   * @throws IOException Eccezione lanciata per errore di IO
   */
  @Test
  public void test19_TroppoGrande() throws ServletException, IOException {
    when(request.getParameter("matricola")).thenReturn(unStudente.getMatricola());
    when(request.getParameter("email")).thenReturn(unStudente.getUser());
    when(request.getParameter("password")).thenReturn(unStudente.getPassword());
    when(request.getParameter("nome")).thenReturn(unStudente.getNome());
    when(request.getParameter("cognome")).thenReturn(unStudente.getCognome());
    when(request.getParameter("dataNascita")).thenReturn("1895/06/23");
    when(request.getParameter("luogoNascita")).thenReturn(unStudente.getLuogoNascita());
    when(request.getRequestDispatcher("/registrazione.jsp?errore="
        + "Mi sembri un pochino troppo grande :-P"))
        .thenReturn(dispatcher);

    try {
      new RegistrazioneStudente().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }
  }

  /**
   * Test case con id TC_GU_1.1_20
   * 
   * @throws ServletException Eccezzione lanciata dalla servlet
   * @throws IOException Eccezione lanciata per errore di IO
   */
  @Test
  public void test21_Successo() throws ServletException, IOException {
    when(request.getParameter("matricola")).thenReturn(unStudente.getMatricola());
    when(request.getParameter("email")).thenReturn(unStudente.getUser());
    when(request.getParameter("password")).thenReturn(unStudente.getPassword());
    when(request.getParameter("nome")).thenReturn(unStudente.getNome());
    when(request.getParameter("cognome")).thenReturn(unStudente.getCognome());
    when(request.getParameter("dataNascita")).thenReturn(unStudente.getDataNascita());
    when(request.getParameter("luogoNascita")).thenReturn(unStudente.getLuogoNascita());
    when(request.getRequestDispatcher("/success.jsp")).thenReturn(dispatcher);

    try {
      new RegistrazioneStudente().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }
  }
  
  /**
   * Elimina gli oggetti creati nel database durante il test.
   */
  @AfterClass
  public static void rimuovi() {
    // rimuovi lo studente n.iannuzzi@studenti.unisa.it
    try {
      DatabaseGu.deleteUser(studente.getUser());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    // rimuovi lo studente m.verdi1@studenti.unisa.it
    try {
      DatabaseGu.deleteUser(unStudente.getUser());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
