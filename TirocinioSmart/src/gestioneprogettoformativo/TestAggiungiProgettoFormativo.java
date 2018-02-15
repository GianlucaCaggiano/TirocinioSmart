package gestioneprogettoformativo;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import gestioneutente.Azienda;
import gestioneutente.Convenzione;
import gestioneutente.Professore;
import gestioneutente.Studente;

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
 * Classe per il test della servlet {@link AggiungiProgettoFormativo}.
 * 
 * @author Gianluca
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAggiungiProgettoFormativo {

  @Mock
  HttpServletRequest request;
  @Mock
  HttpServletResponse response;
  @Mock
  HttpSession sessione;
  @Mock
  RequestDispatcher dispatcher;

  private static Studente studente;
  private static Professore professore;
  private static Azienda azienda;
  private static ProgettoFormativo progetto;

  /**
   * Inizializzazione degli oggetti utili nel test.
   */
  @BeforeClass
  public static void init() {
    //Aggiungi uno studente
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
    
    //Aggiungi un professore
    professore = new Professore();
    professore.setUser("fferrucci@unisa.it");
    professore.setPassword("123456");
    professore.setNome("Filomena");
    professore.setCognome("Ferrucci");
    professore.setMateria("Ingegneria del software");
    
    try {
      DatabaseGu.addUser(professore);
      //Settalo come abilitato
      DatabaseGu.setAbilitatoProfessore(professore.getUser());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    //Aggiungi un'azienda
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
      //Settala come abilitata
      DatabaseGu.setAbilitatoAzienda(azienda.getUser());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    progetto = new ProgettoFormativo();
    progetto.setAzienda(azienda);
    progetto.setProfessore(professore);
    progetto.setStudente(studente);
    progetto.setDataInizio("2018/01/01");
    progetto.setDataFine("2018/03/02");
    progetto.setObiettivi("Questo è un test");
  }
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }
  
  /**
   * Test case con id TC_PF_5_1 .
   * 
   * @throws ServletException Eccezzione lanciata dalla servlet
   * @throws IOException Eccezione lanciata per errore di IO
   */
  @Test
  public void test01_DataInizio() throws ServletException, IOException {
    when(request.getParameter("dataInizio")).thenReturn("");
    when(request.getParameter("dataFine")).thenReturn("");
    when(request.getParameter("obiettivi")).thenReturn("");
    when(request.getRequestDispatcher("/compilaProgettoFormativoAzienda.jsp?errore="
        + "Data non valida")).thenReturn(dispatcher);
    try {
      new AggiungiProgettoFormativo().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }
  }
  
  /**
   * Test case con id TC_PF_5_2 .
   * 
   * @throws ServletException Eccezzione lanciata dalla servlet
   * @throws IOException Eccezione lanciata per errore di IO
   */
  @Test
  public void test02_DataInizio() throws ServletException, IOException {
    when(request.getParameter("dataInizio")).thenReturn("02032018");
    when(request.getParameter("dataFine")).thenReturn("");
    when(request.getParameter("obiettivi")).thenReturn("");
    when(request.getRequestDispatcher("/compilaProgettoFormativoAzienda.jsp?errore="
        + "Data non valida")).thenReturn(dispatcher);
    try {
      new AggiungiProgettoFormativo().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }
  }
  
  /**
   * Test case con id TC_PF_5_3 .
   * 
   * @throws ServletException Eccezzione lanciata dalla servlet
   * @throws IOException Eccezione lanciata per errore di IO
   */
  @Test
  public void test03_DataFine() throws ServletException, IOException {
    when(request.getParameter("dataInizio")).thenReturn(progetto.getDataFine());
    when(request.getParameter("dataFine")).thenReturn("");
    when(request.getParameter("obiettivi")).thenReturn("");
    when(request.getRequestDispatcher("/compilaProgettoFormativoAzienda.jsp?errore="
        + "Data non valida")).thenReturn(dispatcher);
    try {
      new AggiungiProgettoFormativo().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }
  }

  /**
   * Test case con id TC_PF_5_4 .
   * 
   * @throws ServletException Eccezzione lanciata dalla servlet
   * @throws IOException Eccezione lanciata per errore di IO
   */
  @Test
  public void test04_DataFine() throws ServletException, IOException {
    when(request.getParameter("dataInizio")).thenReturn(progetto.getDataFine());
    when(request.getParameter("dataFine")).thenReturn("01012018");
    when(request.getParameter("obiettivi")).thenReturn("");
    when(request.getRequestDispatcher("/compilaProgettoFormativoAzienda.jsp?errore="
        + "Data non valida")).thenReturn(dispatcher);
    try {
      new AggiungiProgettoFormativo().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }
  }
  
  /**
   * Test case con id TC_PF_5_5 .
   * 
   * @throws ServletException Eccezzione lanciata dalla servlet
   * @throws IOException Eccezione lanciata per errore di IO
   */
  @Test
  public void test05_DateErrate() throws ServletException, IOException {
    when(request.getParameter("dataInizio")).thenReturn(progetto.getDataFine());
    when(request.getParameter("dataFine")).thenReturn(progetto.getDataInizio());
    when(request.getParameter("obiettivi")).thenReturn("");
    when(request.getRequestDispatcher("/compilaProgettoFormativoAzienda.jsp?errore="
        + "Data inizio successiva alla data di fine")).thenReturn(dispatcher);
    try {
      new AggiungiProgettoFormativo().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }
  }
  
  /**
   * Test case con id TC_PF_5_6 .
   * 
   * @throws ServletException Eccezzione lanciata dalla servlet
   * @throws IOException Eccezione lanciata per errore di IO
   */
  @Test
  public void test06_Obiettivi() throws ServletException, IOException {
    when(request.getParameter("dataInizio")).thenReturn(progetto.getDataInizio());
    when(request.getParameter("dataFine")).thenReturn(progetto.getDataFine());
    when(request.getParameter("obiettivi")).thenReturn("a");
    when(request.getRequestDispatcher("/compilaProgettoFormativoAzienda.jsp?errore="
        + "Campo obiettivi troppo corto o troppo lungo (max 255 caratteri)"))
      .thenReturn(dispatcher);
    try {
      new AggiungiProgettoFormativo().doPost(request, response);
    } catch (NullPointerException e) {
      fail("Non doveva capitare");
    }
  }
  
  /**
   * Elimina gli oggetti creati nel database durante il test.
   */
  @AfterClass
  public static void rimuovi() {
    /*
    //Rimuovi il progetto formativo creato durante il test
    Connection con = null;

    String rimuoviRichiesta = "DELETE FROM progettoformativo ";

    // Elimina la richiesta dalla tabella nel database
    try {
      con = Database.getConnection();

      PreparedStatement ps = con.prepareStatement(rimuoviRichiesta);

      ps.executeUpdate();
      ps.close();
      con.commit();

    } finally {

      Database.releaseConnection(con);

    }
    */
    // rimuovi lo studente
    try {
      DatabaseGu.deleteUser(studente.getUser());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    // rimuovi il prof
    try {
      DatabaseGu.deleteUser(professore.getUser());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    //rimuovi l'azienda
    try {
      DatabaseGu.deleteUser(azienda.getUser());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
