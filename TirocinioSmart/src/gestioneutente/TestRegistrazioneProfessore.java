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
 * Classe per il test della servlet {@link RegistrazioneProfessore}.
 * 
 * @author Gianluca
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRegistrazioneProfessore {

  @Mock
  HttpServletRequest request;
  @Mock
  HttpServletResponse response;
  @Mock
  HttpSession sessione;
  @Mock
  RequestDispatcher dispatcher;

  private static Professore unProfessore;
  private static Professore professore;
  
  /**
   * Inizializzazione degli oggetti utili nel test.
   */
  @BeforeClass
  public static void init() {
    professore = new Professore();
    professore.setUser("fferrucci@unisa.it");
    professore.setPassword("123456");
    professore.setNome("Filomena");
    professore.setCognome("Ferrucci");
    professore.setMateria("Ingegneria del software");
    
    try {
      DatabaseGu.addUser(professore);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    unProfessore = new Professore();
    unProfessore.setUser("gravino@unisa.it");
    unProfessore.setPassword("123456");
    unProfessore.setNome("Carmine");
    unProfessore.setCognome("Gravino");
    unProfessore.setMateria("Programmazione 2");
    
  }
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  /**
   * Test case con id TC_GU_1.2_1
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test01_Email() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn("");
    when(request.getParameter("password")).thenReturn("");
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("materia")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=Email non valida"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneProfessore().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }

  /**
   * Test case con id TC_GU_1.2_2
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test02_Email() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn("a");
    when(request.getParameter("password")).thenReturn("");
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("materia")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=Email non valida"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneProfessore().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }

  /**
   * Test case con id TC_GU_1.2_3
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test03_Password() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(professore.getUser());
    when(request.getParameter("password")).thenReturn("123");
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("materia")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=Password non valida"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneProfessore().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }

  /**
   * Test case con id TC_GU_1.2_4
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test04_Password() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(professore.getUser());
    when(request.getParameter("password")).thenReturn("123456@");
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("materia")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=Password non valida"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneProfessore().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }


  /**
   * Test case con id TC_GU_1.2_6
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test06_Nome() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(professore.getUser());
    when(request.getParameter("password")).thenReturn(unProfessore.getPassword());
    when(request.getParameter("nome")).thenReturn("");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("materia")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=nome non valido"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneProfessore().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.2_7
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test07_Nome() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(professore.getUser());
    when(request.getParameter("password")).thenReturn(unProfessore.getPassword());
    when(request.getParameter("nome")).thenReturn("Car@");
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("materia")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=nome non valido"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneProfessore().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.2_8
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test08_Cognome() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(professore.getUser());
    when(request.getParameter("password")).thenReturn(unProfessore.getPassword());
    when(request.getParameter("nome")).thenReturn(unProfessore.getNome());
    when(request.getParameter("cognome")).thenReturn("");
    when(request.getParameter("materia")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=cognome non valido"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneProfessore().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.2_9
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test09_Cognome() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(professore.getUser());
    when(request.getParameter("password")).thenReturn(unProfessore.getPassword());
    when(request.getParameter("nome")).thenReturn(unProfessore.getNome());
    when(request.getParameter("cognome")).thenReturn("Gravin&");
    when(request.getParameter("materia")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=cognome non valido"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneProfessore().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.2_10
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test10_Materia() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(professore.getUser());
    when(request.getParameter("password")).thenReturn(unProfessore.getPassword());
    when(request.getParameter("nome")).thenReturn(unProfessore.getNome());
    when(request.getParameter("cognome")).thenReturn(unProfessore.getCognome());
    when(request.getParameter("materia")).thenReturn("");
    when(request.getRequestDispatcher("/registrazione.jsp?errore=materia non valida"))
      .thenReturn(dispatcher);
    
    try {
      new RegistrazioneProfessore().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.2_11
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test11_EmailPresente() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(professore.getUser());
    when(request.getParameter("password")).thenReturn(unProfessore.getPassword());
    when(request.getParameter("nome")).thenReturn(unProfessore.getNome());
    when(request.getParameter("cognome")).thenReturn(unProfessore.getCognome());
    when(request.getParameter("materia")).thenReturn(unProfessore.getMateria());
    when(request.getRequestDispatcher("/registrazione.jsp?errore="
        + "Utente gia' presente nel sistema")).thenReturn(dispatcher);
    
    try {
      new RegistrazioneProfessore().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Test case con id TC_GU_1.2_12
   * 
   * @throws IOException Eccezione lanciata per errore di IO
   * @throws ServletException Eccezzione lanciata dalla servlet
   * 
   */
  @Test
  public void test12_Successo() throws ServletException, IOException {
    when(request.getParameter("email")).thenReturn(unProfessore.getUser());
    when(request.getParameter("password")).thenReturn(unProfessore.getPassword());
    when(request.getParameter("nome")).thenReturn(unProfessore.getNome());
    when(request.getParameter("cognome")).thenReturn(unProfessore.getCognome());
    when(request.getParameter("materia")).thenReturn(unProfessore.getMateria());
    when(request.getRequestDispatcher("/success.jsp")).thenReturn(dispatcher);
    
    try {
      new RegistrazioneProfessore().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }    
  }
  
  /**
   * Elimina gli oggetti creati nel database durante il test.
   */
  @AfterClass
  public static void rimuovi() {
    // rimuovi il professore fferrucci
    try {
      DatabaseGu.deleteUser(professore.getUser());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    /*
    // rimuovi il professore 
    try {
      DatabaseGu.deleteUser(unProfessore.getUser());
    } catch (SQLException e) {
      e.printStackTrace();
    }*/
  }
}
