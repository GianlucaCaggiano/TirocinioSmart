package gestioneProgettoFormativo;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestioneUtente.Azienda;
import gestioneUtente.Professore;
import gestioneUtente.Studente;
import storageLayer.DatabaseGu;
import storageLayer.DatabasePf;

/**
 * Servlet implementation class AggiungiProgettoFormativo.
 * Gestisce l'aggiunta di un Progetto Formativo.
 * 
 * @author Iannuzzi Nicola'
 * 
 * @version 1.0
 */
@WebServlet("/AggiungiProgettoFormativo")
public class AggiungiProgettoFormativo extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiungiProgettoFormativo() 
    {
        super();
    }

    /**
	 * Registra un Progetto Formativo nel database, chiamando il metodo doPost();
	 * 
	 * @param request, response
	 * @throws ServletException, IOException
	 * 
	 * @author Iannuzzi Nicola'
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	 /**
		 * Registra un Progetto Formativo nel database.
		 * 
		 * @param request, response
		 * @throws ServletException, IOException
		 * 
		 * @author Iannuzzi Nicola'
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String studente = request.getParameter("matricolaStudente");
		Studente s = new Studente();
		s.setMatricola(studente);
		String professore = request.getParameter("professoreEmail");
		Professore p = new Professore();
		p.setUser(professore);
		Azienda azienda = (Azienda) request.getSession().getAttribute("azienda");
		String obiettivi = request.getParameter("obiettivi");
		String dataInizio = request.getParameter("dataInizio");
		String dataFine = request.getParameter("dataFine");
		ProgettoFormativo progettoFormativo = new ProgettoFormativo();
		try 
		{
			progettoFormativo.setAzienda(azienda);
			progettoFormativo.setDataInizio(dataInizio);
			progettoFormativo.setDataFine(dataFine);
			progettoFormativo.setObiettivi(obiettivi);
			progettoFormativo.setStudente(s);
			progettoFormativo.setSegreteria(DatabaseGu.getSegreteriaByUser("segreteriaUnisa"));
			progettoFormativo.setProfessore(p);
			
			DatabasePf.AddProgettoFormativo(progettoFormativo);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/compilaProgettoFormativoAziendaLista.jsp");
			dispatcher.forward(request, response);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

}
