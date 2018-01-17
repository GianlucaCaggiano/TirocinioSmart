package gestioneRegistroTirocinio;
import gestioneUtente.*;

/**
 * Classe che modella la Riga Registro riferito al Registro di Tirocinio.
 * 
 * @author Iannuzzi Nicolà
 *
 * @version 1.0
 */
public class RigaRegistro 
{
	private int numRiga;
	private RegistroTirocinio registroTirocinio;
	private String relazione;
	private String oraInizio;
	private String oraFine;
	private String giorno;
	private Studente studente;
	
	/**
	 * Costruttore Vuoto
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public RigaRegistro()
	{
		
	}
	
	/**
	 * Costruttore con parametri:
	 * 
	 * @param numRiga
	 * @param registroTirocinio
	 * @param relazione
	 * @param oraInizio
	 * @param oraFine
	 * @param giorno
	 * @param studente
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public RigaRegistro(int numRiga, RegistroTirocinio registroTirocinio, String relazione, String oraInizio, String oraFine, String giorno, Studente studente)
	{
		this.numRiga = numRiga;
		this.registroTirocinio = registroTirocinio;
		this.relazione = relazione;
		this.oraInizio = oraInizio;
		this.oraFine = oraFine;
		this.giorno = giorno;
		this.studente = studente;
	}

}
