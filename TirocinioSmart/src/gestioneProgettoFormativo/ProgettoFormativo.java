package gestioneProgettoFormativo;
import gestioneUtente.*;

/**
 * Classe che modella un Progetto Formativo per la piattaforma.
 * 
 * @author Iannuzzi Nicolà
 *
 * @version 1.0
 */
public class ProgettoFormativo 
{
	private int id;
	private Azienda azienda;
	private Segreteria segreteria;
	private Studente studente;
	private String dataInizio;
	private String obiettivi;
	private String dataFine;
	private boolean convalidaProf;
	private boolean convalidaSegr;
	private boolean sottoscrizioneStu;
	
	/**
	 * Costruttore Vuoto
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public ProgettoFormativo()
	{
		
	}
	
	/**
	 * Costruttore con parametri:
	 * 
	 * @param id int che rappresenta l'identificativo del Progetto Formativo
	 * @param azienda 
	 * @param segreteria
	 * @param studente
	 * @param dataInizio
	 * @param obiettivi
	 * @param dataFine
	 * @param convalidaProf
	 * @param convalidaSegr
	 * @param sottoscrizioneStu
	 */
	public ProgettoFormativo(int id, Azienda azienda, Segreteria segreteria, Studente studente, String dataInizio, String obiettivi, String dataFine, boolean convalidaProf, boolean convalidaSegr, boolean sottoscrizioneStu)
	{
		this.id = id;
		this.azienda = azienda;
		this.segreteria = segreteria;
		this.studente = studente;
		this.dataInizio = dataInizio;
		this.obiettivi = obiettivi;
		this.dataFine = dataFine;
		this.convalidaProf = convalidaProf;
		this.convalidaSegr = convalidaSegr;
		this.sottoscrizioneStu = sottoscrizioneStu;
	}
}
