package gestioneUtente;

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
	private String citta;
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
	 * @param Denominazione: String che rapprresenta la ragione sociale dell'Azienda
	 * @param Citta: String che rappresenta la città dell'Azienda.
	 * @param CAP: String che rappresenta il CAP dell'Azienda.
	 * @param via: String che rappresenta la via dell'Azienda.
	 * @param abilitato: boolean che rappresenta l'abilitazione dell'Azienda per il Tirocinio.
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public Azienda(String email, String password, String Nome, String Cognome, String LuogoNascita, String dataNascita, String Denominazione, String Citta, String CAP, String Via, boolean abilitato)
	{
		super(email, password, Nome, Cognome);
		this.luogoNascita = LuogoNascita;
		this.dataNascita = dataNascita;
		this.denominazione = Denominazione;
		this.citta = Citta;
		this.CAP = CAP;
		this.via = Via;
		this.abilitato = abilitato;
	}

	/**
	 * Ritorno il luogo di Nascita dell'Azienda
	 * 
	 * @return luogoNascita
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public String getLuogoNascita()
	{
		return luogoNascita;
	}
	
	/**
	 * Setta il luogo di Nascita dell'Azienda
	 * 
	 * @param luogoNascita String che rappresenta il luogo di nascita dell'azienda
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setLuogoNascita(String luogoNascita) 
	{
		this.luogoNascita = luogoNascita;
	}

	/**
	 * Ritorna la data di Nascira dell'Azienda
	 * 
	 * @return DataNascita
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public String getDataNascita() 
	{
		return dataNascita;
	}

	/**
	 * Setta la data di nascita dell'Azienda
	 * 
	 * @param dataNascita String che rappresenta la data di nascita dell'Azienda
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setDataNascita(String dataNascita) 
	{
		this.dataNascita = dataNascita;
	}
	
	/**
	 * Ritorna la Denominazione dell'Azienda 
	 * 
	 * @return Denominazione
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public String getDenominazione() 
	{
		return denominazione;
	}

	/**
	 * Setta la denominazione dell'Azienda
	 * 
	 * @param denominazione String che rappresenta la ragione sociale dell'Azienda
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setDenominazione(String denominazione) 
	{
		this.denominazione = denominazione;
	}

	/**
	 * Ritorna la Città dell'Azienda
	 * 
	 * @return Città
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public String getCitta() 
	{
		return citta;
	}
	
	/**
	 * Setta la Città dell'Azienda
	 * 
	 * @param citta String che rappresenta la città dell'Azienda
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setCitta(String citta) 
	{
		this.citta = citta;
	}

	/**
	 * Ritorna il Cap della Città dell'Azienda
	 * 
	 * @return CAP
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public String getCAP() 
	{
		return CAP;
	}
	
	/**
	 * Setta il cap della città dell'Azienda
	 * 
	 * @param CAP String che rappresenta il cap della Cità dell'Azienda
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setCAP(String cAP)
	{
		CAP = cAP;
	}
	
	/**
	 * Ritorna la via dell'Azienda
	 * 
	 * @return via
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public String getVia() 
	{
		return via;
	}

	/**
	 * Setta la via dell'Azienda
	 * 
	 * @param via String che rappresenta la via dell'Azienda
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setVia(String via) 
	{
		this.via = via;
	}

	/**
	 * Ritorna il tipo di Azienda
	 * 
	 * @return tipo
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public String getTipo() 
	{
		return tipo;
	}

	/**
	 * Setta il tipo di Azienda 
	 * 
	 * @param tipo String che rappresenta il tipo di Azienda 
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}

	/**
	 * Ritorna il telefono dell'Azienda
	 * 
	 * @return telefono
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public String getTelefono() 
	{
		return telefono;
	}

	/**
	 * Setta il numero di telefono dell'Azienda
	 * 
	 * @param telefono String che rappresenta il numero di telefono dell'Azienda
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setTelefono(String telefono)
	{
		this.telefono = telefono;
	}

	/**
	 * Ritorna una descrizione sull'Azienda
	 * 
	 * @return chiSiamo
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public String getChiSiamo()
	{
		return chiSiamo;
	}

	/**
	 * Setta una descrizione sull'Azienda
	 * 
	 * @param chiSiamo String che rappresenta una descrizione dell'Azienda
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setChiSiamo(String chiSiamo)
	{
		this.chiSiamo = chiSiamo;
	}

	/**
	 * Ritorna il sito web dell'Azienda 
	 * 
	 * @return sitoweb
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public String getSitoWeb()
	{
		return sitoWeb;
	}

	/**
	 * Setta il sito web dell'Azienda
	 * 
	 * @param sitoWeb String che rappresenta il sito web dell'Azienda
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setSitoWeb(String sitoWeb)
	{
		this.sitoWeb = sitoWeb;
	}

	/**
	 * Ritorna la convenzione dell'Azienda
	 * 
	 * @return convenzione
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public Convenzione getConvenzione() 
	{
		return convenzione;
	}

	/**
	 * Setta la convenzione dell'Azienda
	 * 
	 * @param convenzione Convenzione che rappresenta la convenzione dell'Azienda con l'università
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setConvenzione(Convenzione convenzione)
	{
		this.convenzione = convenzione;
	}

	/**
	 * Ritorna un boolean che rappresenta se l'azienda è abilitata per il Tirocinio
	 * 
	 * @return abilitato
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public boolean isAbilitato()
	{
		return abilitato;
	}
	
	/**
	 * Setta un boolean che rappresenta l'azienda è abilitata per il Tirocinio
	 * 
	 * @param abilitato boolean
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setAbilitato(boolean abilitato)
	{
		this.abilitato = abilitato;
	}
	
	/**
	* Permette di definire una stringa che pu� essere considerata come la 
	* "rappresentazione testuale" dell'oggetto Azienda.
	* 
	* @return Stringa che rappresenta una descrizione pi� accurata e consona dell'oggetto
	* 
	* @author Iannuzzi Nicolà
	*/
	public String toString() 
	{
		return super.toString()+ "Azienda [luogoNascita=" + luogoNascita + ", dataNascita=" + dataNascita + ", denominazione="
				+ denominazione + ", citta=" + citta + ", CAP=" + CAP + ", via=" + via + ", tipo=" + tipo
				+ ", telefono=" + telefono + ", chiSiamo=" + chiSiamo + ", sitoWeb=" + sitoWeb + ", convenzione="
				+ convenzione + ", abilitato=" + abilitato + "]";
	}

	/**
	* Determina se due oggetti rappresentano lo stesso utente confrontando gli user dei suddetti.
	* 
	* @return true se gli user dei due oggetti sono gli stessi, false altrimenti
	* 
	* @author IannuzziNicolà
	*/
	public boolean equals(Object arg0) 
	{
		return super.equals(arg0);
	}
	
	 /** Espressione regolare che definisce il formato del campo email. */
	  public static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/"
	                                           + "=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f"
	                                           + "\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x"
	                                           + "0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-"
	                                           + "9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25["
	                                           + "0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2"
	                                           + "[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\"
	                                           + "x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]"
	                                           + "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	  
	/** Espressione regolare che definisce il formato del campo CAP. */
	  public static final String CAP_PATTERN = "^[0-9]{5,6}";
	  
	  /** Espressione regolare che definisce il formato del campo telefono. */
	  public static final String TELEFONO_PATTERN = "^[0-9]{10,11}";
}
