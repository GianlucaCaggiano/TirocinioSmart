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
 * Classe per il test della servlet {@link RegistrazioneAzienda}.
 * 
 * @author Caggiano Gianluca
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRegistrazioneAzienda {
  
  @Mock
  HttpServletRequest request;
  @Mock
  HttpServletResponse response;
  @Mock
  HttpSession sessione;
  @Mock
  RequestDispatcher dispatcher;

  private static Azienda unAzienda;
  private static Azienda azienda;
  
  /**
   * Inizializzazione degli oggetti utili nel test.
   */
  @BeforeClass
  public static void init() {
    azienda = new Azienda();
    azienda.setUser("accasoftware@miao.it");
    azienda.setPassword("a1b2c3d4");
    azienda.setDenominazione("Acca software s.r.l.");
    azienda.setCitta("Montella");
    azienda.setCap("83025");
    azienda.setVia("Via castagna, 23");
    azienda.setTelefono("3456789123");
    azienda.setSitoWeb("www.accasoftware.it");
    azienda.setNome("Michael");
    azienda.setCognome("Michelone");
    azienda.setDataNascita("1985-9-8");
    azienda.setLuogoNascita("Roma");
    
    Convenzione convenzione = new Convenzione();
    convenzione.setSpecifica("Serve per il test");
    try {
      DatabaseGu.addConvenzione(convenzione);
      DatabaseGu.addUser(azienda);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    unAzienda = new Azienda();
    unAzienda.setUser("hsoftware@miao.it");
    unAzienda.setPassword("a1b2c3d4");
    unAzienda.setDenominazione("H software s.r.l.");
    unAzienda.setCitta("Settefichi");
    unAzienda.setCap("84084");
    unAzienda.setVia("Via Pioppi, 20");
    unAzienda.setTelefono("3333333333");
    unAzienda.setSitoWeb("www.acca.it");
    unAzienda.setNome("Gino");
    unAzienda.setCognome("Fastidio");
    unAzienda.setDataNascita("1986/2/2");
    unAzienda.setLuogoNascita("Pisa");
    
  }
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  /**
   * Test case con id TC_GU_1.3_1
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test01_Email() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn("");
    when(request.getParameter("password")).thenReturn("");
    when(request.getParameter("denominazione")).thenReturn("");
    when(request.getParameter("citta")).thenReturn("");
    when(request.getParameter("cap")).thenReturn("");
    when(request.getParameter("via")).thenReturn("");
    when(request.getParameter("telefono")).thenReturn("");
    when(request.getParameter("sitoWeb")).thenReturn("");
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=Email non valida"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.3_2
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test02_Email() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn("aa@.it");
    when(request.getParameter("password")).thenReturn("");
    when(request.getParameter("denominazione")).thenReturn("");
    when(request.getParameter("citta")).thenReturn("");
    when(request.getParameter("cap")).thenReturn("");
    when(request.getParameter("via")).thenReturn("");
    when(request.getParameter("telefono")).thenReturn("");
    when(request.getParameter("sitoWeb")).thenReturn("");
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=Email non valida"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.3_3
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test03_Password() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(azienda.getUser());
    when(request.getParameter("password")).thenReturn("a1b2");
    when(request.getParameter("denominazione")).thenReturn("");
    when(request.getParameter("citta")).thenReturn("");
    when(request.getParameter("cap")).thenReturn("");
    when(request.getParameter("via")).thenReturn("");
    when(request.getParameter("telefono")).thenReturn("");
    when(request.getParameter("sitoWeb")).thenReturn("");
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=Password non valida"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.3_4
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test04_Password() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(azienda.getUser());
    when(request.getParameter("password")).thenReturn("a1b2c3@");
    when(request.getParameter("denominazione")).thenReturn("");
    when(request.getParameter("citta")).thenReturn("");
    when(request.getParameter("cap")).thenReturn("");
    when(request.getParameter("via")).thenReturn("");
    when(request.getParameter("telefono")).thenReturn("");
    when(request.getParameter("sitoWeb")).thenReturn("");
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=Password non valida"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.3_8
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test08_Denominazione() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(azienda.getUser());
    when(request.getParameter("password")).thenReturn(unAzienda.getPassword());
    when(request.getParameter("denominazione")).thenReturn("");
    when(request.getParameter("citta")).thenReturn("");
    when(request.getParameter("cap")).thenReturn("");
    when(request.getParameter("via")).thenReturn("");
    when(request.getParameter("telefono")).thenReturn("");
    when(request.getParameter("sitoWeb")).thenReturn("");
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=Ragione sociale non valida. "
        + "Se la denominazione dell'azienda contiene piu' di trenta caratteri"
        + "inserire solo l'acronimo."))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.3_9
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test09_Denominazione() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(azienda.getUser());
    when(request.getParameter("password")).thenReturn(unAzienda.getPassword());
    when(request.getParameter("denominazione")).thenReturn("H$Software s.r.l.");
    when(request.getParameter("citta")).thenReturn("");
    when(request.getParameter("cap")).thenReturn("");
    when(request.getParameter("via")).thenReturn("");
    when(request.getParameter("telefono")).thenReturn("");
    when(request.getParameter("sitoWeb")).thenReturn("");
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=Ragione sociale non valida. "
        + "Se la denominazione dell'azienda contiene piu' di trenta caratteri"
        + "inserire solo l'acronimo."))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.3_10
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test10_Citta() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(azienda.getUser());
    when(request.getParameter("password")).thenReturn(unAzienda.getPassword());
    when(request.getParameter("denominazione")).thenReturn(unAzienda.getDenominazione());
    when(request.getParameter("citta")).thenReturn("");
    when(request.getParameter("cap")).thenReturn("");
    when(request.getParameter("via")).thenReturn("");
    when(request.getParameter("telefono")).thenReturn("");
    when(request.getParameter("sitoWeb")).thenReturn("");
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=La citta' inserita non e' valida"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.3_11
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test11_Citta() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(azienda.getUser());
    when(request.getParameter("password")).thenReturn(unAzienda.getPassword());
    when(request.getParameter("denominazione")).thenReturn(unAzienda.getDenominazione());
    when(request.getParameter("citta")).thenReturn("7fichi");
    when(request.getParameter("cap")).thenReturn("");
    when(request.getParameter("via")).thenReturn("");
    when(request.getParameter("telefono")).thenReturn("");
    when(request.getParameter("sitoWeb")).thenReturn("");
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=La citta' inserita non e' valida"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.3_12
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test12_Cap() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(azienda.getUser());
    when(request.getParameter("password")).thenReturn(unAzienda.getPassword());
    when(request.getParameter("denominazione")).thenReturn(unAzienda.getDenominazione());
    when(request.getParameter("citta")).thenReturn(unAzienda.getCitta());
    when(request.getParameter("cap")).thenReturn("8408");
    when(request.getParameter("via")).thenReturn("");
    when(request.getParameter("telefono")).thenReturn("");
    when(request.getParameter("sitoWeb")).thenReturn("");
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=CAP non valido"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.3_13
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test13_Cap() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(azienda.getUser());
    when(request.getParameter("password")).thenReturn(unAzienda.getPassword());
    when(request.getParameter("denominazione")).thenReturn(unAzienda.getDenominazione());
    when(request.getParameter("citta")).thenReturn(unAzienda.getCitta());
    when(request.getParameter("cap")).thenReturn("8408a");
    when(request.getParameter("via")).thenReturn("");
    when(request.getParameter("telefono")).thenReturn("");
    when(request.getParameter("sitoWeb")).thenReturn("");
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=CAP non valido"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.3_14
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test14_Via() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(azienda.getUser());
    when(request.getParameter("password")).thenReturn(unAzienda.getPassword());
    when(request.getParameter("denominazione")).thenReturn(unAzienda.getDenominazione());
    when(request.getParameter("citta")).thenReturn(unAzienda.getCitta());
    when(request.getParameter("cap")).thenReturn(unAzienda.getCap());
    when(request.getParameter("via")).thenReturn("Via Pioppi%20");
    when(request.getParameter("telefono")).thenReturn("");
    when(request.getParameter("sitoWeb")).thenReturn("");
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=la strada inserita non e' valida"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.3_15
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test15_Via() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(azienda.getUser());
    when(request.getParameter("password")).thenReturn(unAzienda.getPassword());
    when(request.getParameter("denominazione")).thenReturn(unAzienda.getDenominazione());
    when(request.getParameter("citta")).thenReturn(unAzienda.getCitta());
    when(request.getParameter("cap")).thenReturn(unAzienda.getCap());
    when(request.getParameter("via")).thenReturn("Via Pioppi%20");
    when(request.getParameter("telefono")).thenReturn("");
    when(request.getParameter("sitoWeb")).thenReturn("");
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=la strada inserita non e' valida"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      e.printStackTrace();
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.3_16
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test16_Telefono() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(azienda.getUser());
    when(request.getParameter("password")).thenReturn(unAzienda.getPassword());
    when(request.getParameter("denominazione")).thenReturn(unAzienda.getDenominazione());
    when(request.getParameter("citta")).thenReturn(unAzienda.getCitta());
    when(request.getParameter("cap")).thenReturn(unAzienda.getCap());
    when(request.getParameter("via")).thenReturn(unAzienda.getVia());
    when(request.getParameter("telefono")).thenReturn("3333333t33");
    when(request.getParameter("sitoWeb")).thenReturn("");
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=Numero di telefono non valido"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.3_17
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test17_SitoWeb() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(azienda.getUser());
    when(request.getParameter("password")).thenReturn(unAzienda.getPassword());
    when(request.getParameter("denominazione")).thenReturn(unAzienda.getDenominazione());
    when(request.getParameter("citta")).thenReturn(unAzienda.getCitta());
    when(request.getParameter("cap")).thenReturn(unAzienda.getCap());
    when(request.getParameter("via")).thenReturn(unAzienda.getVia());
    when(request.getParameter("telefono")).thenReturn(unAzienda.getTelefono());
    when(request.getParameter("sitoWeb")).thenReturn("wwww.acca.it");
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=Indirizzo web non valido"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.3_18
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test18_Nome() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(azienda.getUser());
    when(request.getParameter("password")).thenReturn(unAzienda.getPassword());
    when(request.getParameter("denominazione")).thenReturn(unAzienda.getDenominazione());
    when(request.getParameter("citta")).thenReturn(unAzienda.getCitta());
    when(request.getParameter("cap")).thenReturn(unAzienda.getCap());
    when(request.getParameter("via")).thenReturn(unAzienda.getVia());
    when(request.getParameter("telefono")).thenReturn(unAzienda.getTelefono());
    when(request.getParameter("sitoWeb")).thenReturn(unAzienda.getSitoWeb());
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=nome non valido"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.3_19
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test19_Nome() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(azienda.getUser());
    when(request.getParameter("password")).thenReturn(unAzienda.getPassword());
    when(request.getParameter("denominazione")).thenReturn(unAzienda.getDenominazione());
    when(request.getParameter("citta")).thenReturn(unAzienda.getCitta());
    when(request.getParameter("cap")).thenReturn(unAzienda.getCap());
    when(request.getParameter("via")).thenReturn(unAzienda.getVia());
    when(request.getParameter("telefono")).thenReturn(unAzienda.getTelefono());
    when(request.getParameter("sitoWeb")).thenReturn(unAzienda.getSitoWeb());
    when(request.getParameter("nome")).thenReturn("Gin0");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=nome non valido"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.3_20
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test20_Cognome() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(azienda.getUser());
    when(request.getParameter("password")).thenReturn(unAzienda.getPassword());
    when(request.getParameter("denominazione")).thenReturn(unAzienda.getDenominazione());
    when(request.getParameter("citta")).thenReturn(unAzienda.getCitta());
    when(request.getParameter("cap")).thenReturn(unAzienda.getCap());
    when(request.getParameter("via")).thenReturn(unAzienda.getVia());
    when(request.getParameter("telefono")).thenReturn(unAzienda.getTelefono());
    when(request.getParameter("sitoWeb")).thenReturn(unAzienda.getSitoWeb());
    when(request.getParameter("nome")).thenReturn(unAzienda.getNome());
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=cognome non valido"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.3_21
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test21_Cognome() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(azienda.getUser());
    when(request.getParameter("password")).thenReturn(unAzienda.getPassword());
    when(request.getParameter("denominazione")).thenReturn(unAzienda.getDenominazione());
    when(request.getParameter("citta")).thenReturn(unAzienda.getCitta());
    when(request.getParameter("cap")).thenReturn(unAzienda.getCap());
    when(request.getParameter("via")).thenReturn(unAzienda.getVia());
    when(request.getParameter("telefono")).thenReturn(unAzienda.getTelefono());
    when(request.getParameter("sitoWeb")).thenReturn(unAzienda.getSitoWeb());
    when(request.getParameter("nome")).thenReturn(unAzienda.getNome());
    when(request.getParameter("cognome")).thenReturn("Fastidi0");
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=cognome non valido"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.3_22
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test22_DataNascita() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(azienda.getUser());
    when(request.getParameter("password")).thenReturn(unAzienda.getPassword());
    when(request.getParameter("denominazione")).thenReturn(unAzienda.getDenominazione());
    when(request.getParameter("citta")).thenReturn(unAzienda.getCitta());
    when(request.getParameter("cap")).thenReturn(unAzienda.getCap());
    when(request.getParameter("via")).thenReturn(unAzienda.getVia());
    when(request.getParameter("telefono")).thenReturn(unAzienda.getTelefono());
    when(request.getParameter("sitoWeb")).thenReturn(unAzienda.getSitoWeb());
    when(request.getParameter("nome")).thenReturn(unAzienda.getNome());
    when(request.getParameter("cognome")).thenReturn(unAzienda.getCognome());
    when(request.getParameter("dataNascita")).thenReturn("");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=Data di nascita errata"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.3_23
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test23_DataNascita() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(azienda.getUser());
    when(request.getParameter("password")).thenReturn(unAzienda.getPassword());
    when(request.getParameter("denominazione")).thenReturn(unAzienda.getDenominazione());
    when(request.getParameter("citta")).thenReturn(unAzienda.getCitta());
    when(request.getParameter("cap")).thenReturn(unAzienda.getCap());
    when(request.getParameter("via")).thenReturn(unAzienda.getVia());
    when(request.getParameter("telefono")).thenReturn(unAzienda.getTelefono());
    when(request.getParameter("sitoWeb")).thenReturn(unAzienda.getSitoWeb());
    when(request.getParameter("nome")).thenReturn(unAzienda.getNome());
    when(request.getParameter("cognome")).thenReturn(unAzienda.getCognome());
    when(request.getParameter("dataNascita")).thenReturn("20040511");
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=Data di nascita errata"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.3_24
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test24_LuogoNascita() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(azienda.getUser());
    when(request.getParameter("password")).thenReturn(unAzienda.getPassword());
    when(request.getParameter("denominazione")).thenReturn(unAzienda.getDenominazione());
    when(request.getParameter("citta")).thenReturn(unAzienda.getCitta());
    when(request.getParameter("cap")).thenReturn(unAzienda.getCap());
    when(request.getParameter("via")).thenReturn(unAzienda.getVia());
    when(request.getParameter("telefono")).thenReturn(unAzienda.getTelefono());
    when(request.getParameter("sitoWeb")).thenReturn(unAzienda.getSitoWeb());
    when(request.getParameter("nome")).thenReturn(unAzienda.getNome());
    when(request.getParameter("cognome")).thenReturn(unAzienda.getCognome());
    when(request.getParameter("dataNascita")).thenReturn(unAzienda.getDataNascita());
    when(request.getParameter("luogoNascita")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore="
        + "La citta' di nascita inserita non e' valida"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }

  /**
   * Test case con id TC_GU_1.3_25
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test25_LuogoNascita() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(azienda.getUser());
    when(request.getParameter("password")).thenReturn(unAzienda.getPassword());
    when(request.getParameter("denominazione")).thenReturn(unAzienda.getDenominazione());
    when(request.getParameter("citta")).thenReturn(unAzienda.getCitta());
    when(request.getParameter("cap")).thenReturn(unAzienda.getCap());
    when(request.getParameter("via")).thenReturn(unAzienda.getVia());
    when(request.getParameter("telefono")).thenReturn(unAzienda.getTelefono());
    when(request.getParameter("sitoWeb")).thenReturn(unAzienda.getSitoWeb());
    when(request.getParameter("nome")).thenReturn(unAzienda.getNome());
    when(request.getParameter("cognome")).thenReturn(unAzienda.getCognome());
    when(request.getParameter("dataNascita")).thenReturn(unAzienda.getDataNascita());
    when(request.getParameter("luogoNascita")).thenReturn("%");
    when(request.getRequestDispatcher("/registrazione.jsp?errore="
        + "La citta' di nascita inserita non e' valida"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }

  /**
   * Test case con id TC_GU_1.3_26
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test26_EmailPresente() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(azienda.getUser());
    when(request.getParameter("password")).thenReturn(unAzienda.getPassword());
    when(request.getParameter("denominazione")).thenReturn(unAzienda.getDenominazione());
    when(request.getParameter("citta")).thenReturn(unAzienda.getCitta());
    when(request.getParameter("cap")).thenReturn(unAzienda.getCap());
    when(request.getParameter("via")).thenReturn(unAzienda.getVia());
    when(request.getParameter("telefono")).thenReturn(unAzienda.getTelefono());
    when(request.getParameter("sitoWeb")).thenReturn(unAzienda.getSitoWeb());
    when(request.getParameter("nome")).thenReturn(unAzienda.getNome());
    when(request.getParameter("cognome")).thenReturn(unAzienda.getCognome());
    when(request.getParameter("dataNascita")).thenReturn(unAzienda.getDataNascita());
    when(request.getParameter("luogoNascita")).thenReturn(unAzienda.getLuogoNascita());
    when(request.getRequestDispatcher("/registrazione.jsp?errore="
        + "Azienda gia' presente nel sistema"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }

  /**
   * Test case con id TC_GU_1.3_27
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test27_TroppoPiccolo() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(unAzienda.getUser());
    when(request.getParameter("password")).thenReturn(unAzienda.getPassword());
    when(request.getParameter("denominazione")).thenReturn(unAzienda.getDenominazione());
    when(request.getParameter("citta")).thenReturn(unAzienda.getCitta());
    when(request.getParameter("cap")).thenReturn(unAzienda.getCap());
    when(request.getParameter("via")).thenReturn(unAzienda.getVia());
    when(request.getParameter("telefono")).thenReturn(unAzienda.getTelefono());
    when(request.getParameter("sitoWeb")).thenReturn(unAzienda.getSitoWeb());
    when(request.getParameter("nome")).thenReturn(unAzienda.getNome());
    when(request.getParameter("cognome")).thenReturn(unAzienda.getCognome());
    when(request.getParameter("dataNascita")).thenReturn("2006/2/2");
    when(request.getParameter("luogoNascita")).thenReturn(unAzienda.getLuogoNascita());
    when(request.getRequestDispatcher("/registrazione.jsp?errore="
        + "Mi sembri un pochino troppo piccolo :-P"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }

  /**
   * Test case con id TC_GU_1.3_28
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test28_TroppoGrande() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(unAzienda.getUser());
    when(request.getParameter("password")).thenReturn(unAzienda.getPassword());
    when(request.getParameter("denominazione")).thenReturn(unAzienda.getDenominazione());
    when(request.getParameter("citta")).thenReturn(unAzienda.getCitta());
    when(request.getParameter("cap")).thenReturn(unAzienda.getCap());
    when(request.getParameter("via")).thenReturn(unAzienda.getVia());
    when(request.getParameter("telefono")).thenReturn(unAzienda.getTelefono());
    when(request.getParameter("sitoWeb")).thenReturn(unAzienda.getSitoWeb());
    when(request.getParameter("nome")).thenReturn(unAzienda.getNome());
    when(request.getParameter("cognome")).thenReturn(unAzienda.getCognome());
    when(request.getParameter("dataNascita")).thenReturn("1880/2/2");
    when(request.getParameter("luogoNascita")).thenReturn(unAzienda.getLuogoNascita());
    when(request.getRequestDispatcher("/registrazione.jsp?errore="
        + "Mi sembri un pochino troppo grande :-P"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }

  /**
   * Test case con id TC_GU_1.3_29
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test29_Successo() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(unAzienda.getUser());
    when(request.getParameter("password")).thenReturn(unAzienda.getPassword());
    when(request.getParameter("denominazione")).thenReturn(unAzienda.getDenominazione());
    when(request.getParameter("citta")).thenReturn(unAzienda.getCitta());
    when(request.getParameter("cap")).thenReturn(unAzienda.getCap());
    when(request.getParameter("via")).thenReturn(unAzienda.getVia());
    when(request.getParameter("telefono")).thenReturn(unAzienda.getTelefono());
    when(request.getParameter("sitoWeb")).thenReturn(unAzienda.getSitoWeb());
    when(request.getParameter("nome")).thenReturn(unAzienda.getNome());
    when(request.getParameter("cognome")).thenReturn(unAzienda.getCognome());
    when(request.getParameter("dataNascita")).thenReturn(unAzienda.getDataNascita());
    when(request.getParameter("luogoNascita")).thenReturn(unAzienda.getLuogoNascita());
    when(request.getRequestDispatcher("/success.jsp")).thenReturn(dispatcher);
    
    try {
      new RegistrazioneAzienda().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Elimina gli oggetti creati nel database durante il test.
   */
  @AfterClass
  public static void rimuovi() {
    // rimuovi l'azienda accasoftware@miao.it
    try {
      DatabaseGu.deleteUser(azienda.getUser());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    // rimuovi l'azienda hsoftware@miao.it
    try {
      DatabaseGu.deleteUser(unAzienda.getUser());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
