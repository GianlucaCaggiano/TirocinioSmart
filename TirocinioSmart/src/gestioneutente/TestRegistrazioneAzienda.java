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
    /*
    // rimuovi l'azienda hsoftware@miao.it
    try {
      DatabaseGu.deleteUser(unProfessore.getUser());
    } catch (SQLException e) {
      e.printStackTrace();
    }*/
  }
}
