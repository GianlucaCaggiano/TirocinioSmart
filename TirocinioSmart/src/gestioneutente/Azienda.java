package gestioneutente;

/**
 * Classe che modella un'Azienda che si è iscritto alla piattaforma.
 * 
 * @author Iannuzzi Nicolà
 *
 * @version 1.0
 */
public class Azienda extends Utente
{
	private String luogoNascita;
	private String dataNascita;
	private String denominazione;
	private String città;
	private String CAP;
	private String via;
	private String tipo;
	private String telefono;
	private String chiSiamo;
	private String sitoWeb;
	private Convenzione convenzione;
	private boolean abilitato;
	
	/**
	 * Costruttore Vuoto Azienda
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public Azienda()
	{
		
	}
	
	/**
	 * Costruttore con parametri:
	 * 
	 * @param emal: String che rappresenta l'email dell'azienda nonchè la user di accesso.
	 * @param password: String che rappresenta la password dell'azienda per l'accesso.
	 * @param Nome: String che rappresenta il nome del Tutor Aziendale.
	 * @param Cognome: String che rappresenta il cognome del Tutor Aziendale.
	 * @param LuogoNascita: String che rappresenta il luogo di nascita dell'Azienda.
	 * @param DataNascita: String che rappresenta la data di nascita dell'Azienda.
	 * @param Denominazione
	 * @param Città: String che rappresenta la città dell'Azienda.
	 * @param CAP: String che rappresenta il CAP dell'Azienda.
	 * @param via: String che rappresenta la via dell'Azienda.
	 * @param tipo: String che rappresenta il tipo di Azienda.
	 * @param telefono: String che rappresenta il numero di telefono dell'Azienda.
	 * @param chiSiamo: String che rappresenta la descrizione dell'Azienda.
	 * @param sitoWeb: String che rappresenta il sito web dell'Azienda.
	 * @raram convenzione: Convenzione che rappresenta la convenzione con l'università
	 * @param abilitato: boolean che rappresenta l'abilitazione dell'Azienda per il Tirocinio.
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public Azienda(String email, String password, String Nome, String Cognome, String LuogoNascita, String dataNascita, String Denominazione, String Città, String CAP, String Via, String Tipo, String Telefono, String SitoWeb, String ChiSiamo, Convenzione Convenzione, boolean abilitato)
	{
		super(email, password, Nome, Cognome);
		this.luogoNascita = LuogoNascita;
		this.dataNascita = dataNascita;
		this.denominazione = Denominazione;
		this.città = Città;
		this.CAP = CAP;
		this.via = Via;
		this.tipo = Tipo;
		this.telefono = Telefono;
		this.chiSiamo = ChiSiamo;
		this.sitoWeb = SitoWeb;
		this.convenzione = Convenzione;
		this.abilitato = abilitato;
	}
}
