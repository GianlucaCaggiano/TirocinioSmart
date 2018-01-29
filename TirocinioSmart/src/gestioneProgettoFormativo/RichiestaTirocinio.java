package gestioneProgettoFormativo;
import gestioneUtente.*;

/**
 * Classe che modella la Richiesta Tirocinio per la piattaforma.
 * 
 * @author Iannuzzi Nicola', Caggiano Gianluca
 *
 * @version 1.0
 */
public class RichiestaTirocinio 
{
	private int id;
	private Azienda azienda;
	private Professore professore;
	private boolean convalidaAzienda;
	private boolean convalidaProf;
	
	/**
	 * Costruttore vuoto
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public RichiestaTirocinio()
	{
		this.id = -1;
	}
	
	/**
	 * Costruttore con parametri:
	 * 
	 * @param id Identificativo che contrassegna la richiesta di tirocinio
	 * @param azienda Azienda che rappresenta l'azienda coinvolta nella Richiesta Tirocinio
	 * @param professore Professore che rappresenta il professore coinvolto nella Richiesta Tirocinio
	 * @param convalidaAzienda boolean che rappresenta la convalida dell'Azienda nella Richiesta Tirocinio
	 * @param convalidaProf boolean che rappresenta la convalida del Professore nella Richiesta Tirocinio
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public RichiestaTirocinio(int id, Azienda azienda, Professore professore, boolean convalidaAzienda, boolean convalidaProf)
	{
		this.id = id;
		this.azienda = azienda;
		this.professore = professore;
		this.convalidaAzienda = convalidaAzienda;
		this.convalidaProf = convalidaProf;
	}

	/**
	 * Restituisce l'idendificativo della richiesta di tirocinio.
	 * 
	 * @return the id
	 * 
	 * @author Caggiano Gianluca
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setta l'idendificativo della richiesta di tirocinio.
	 * 
	 * @param id the id to set
	 * 
	 * @author Caggiano Gianluca
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Ritorna l'Azienda coinvolta nella Richiesta Tirocinio
	 * 
	 * @return azienda
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public Azienda getAzienda() 
	{
		return azienda;
	}

	/**
	 * Setta l'Azienda coinvolta nella Richiesta Tirocinio
	 * 
	 * @param azienda Azienda che rappresenta l'azienda coinvolta nella Richiesta Tirocinio
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setAzienda(Azienda azienda) 
	{
		this.azienda = azienda;
	}

	/**
	 * Ritorna il Professore coinvolto nella Richiesta Tirocinio
	 * 
	 * @return professore
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public Professore getProfessore() 
	{
		return professore;
	}

	/**
	 * Setta l'Azienda coinvolta nella Richiesta Tirocinio
	 * 
	 * @param professore Professore che rappresenta il professore coinvolto nella Richiesta Tirocinio
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setProfessore(Professore professore) 
	{
		this.professore = professore;
	}

	/**
	 * Ritorna un boolean che rappresenta se l'Azienda ha convalidato la Richiesta di Tirocinio
	 * 
	 * @return convalidaAzienda
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public boolean isConvalidaAzienda() 
	{
		return convalidaAzienda;
	}

	/**
	 * Setta un boolean che rappresenta se l'Azienda ha convalidato la Richiesta di Tirocinio
	 * 
	 * @param convalidaAzienda boolean che rappresenta la convalida dell'Azienda nella Richiesta Tirocinio
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setConvalidaAzienda(boolean convalidaAzienda) 
	{
		this.convalidaAzienda = convalidaAzienda;
	}

	/**
	 * Ritorna un boolean che rappresenta se il Profeossore ha convalidato la Richiesta di Tirocinio
	 * 
	 * @return convalidaProf
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public boolean isConvalidaProf() 
	{
		return convalidaProf;
	}

	/**
	 * Setta un boolean che rappresenta se il Professore ha convalidato la Richiesta di Tirocinio
	 * 
	 * @param convalidaAzienda boolean che rappresenta la convalida del Professore nella Richiesta Tirocinio
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setConvalidaProf(boolean convalidaProf) 
	{
		this.convalidaProf = convalidaProf;
	}

	/**
	* Permette di definire una stringa che può essere considerata come la 
	* "rappresentazione testuale" dell'oggetto RichiestaTirocinio.
	* 
	* @return Stringa che rappresenta una descrizione pi� accurata e consona dell'oggetto
	* 
	* @author Iannuzzi Nicolà
	*/
	public String toString() 
	{
		return "RichiestaTirocinio [azienda=" + azienda + ", professore=" + professore + ", convalidaAzienda="
				+ convalidaAzienda + ", convalidaProf=" + convalidaProf + "]";
	}
	
	/**
	* Determina se due oggetti rappresentano la stessa Richiesta di Tirocinio confrontandole.
	* 
	* @return true se le Richieste di Tirocinio dei due oggetti sono gli stessi, false altrimenti
	*/
	public boolean equals(Object object) 
	{
		if (object == null) 
		{
		      return false;
		}
		    
		if (object.getClass() != getClass()) 
		{
		      return false;
		}
		    
		RichiestaTirocinio richiestaTirocinio = (RichiestaTirocinio) object;
		    
		return (richiestaTirocinio.getAzienda().equals(this.azienda) && richiestaTirocinio.getProfessore().equals(this.professore) && richiestaTirocinio.isConvalidaAzienda() == this.isConvalidaAzienda() && richiestaTirocinio.isConvalidaProf() == this.convalidaProf);
	}
	
}
