package gestioneUtente;

/**
 * Classe che modella un professore inscritto alla piattaforma
 * 
 * @author Iannuzzi Nicolà
 *
 * @version 1.0
 */
public class Professore extends Utente
{
	private boolean autorizzo;
	private String materia;
	
	/**
	 * Costruttore vuoto
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public Professore()
	{
		
	}
	
	/**
	 * Costruttore con parametri:
	 * 
	 * @param email String che rappresenta l'email del Professore nonchè la user di accesso
	 * @param password String che rappresenta la password del Professore per l'accesso 
	 * @param Nome String che rappresenta il nome del Professore
	 * @param Cognome String che rappresenta il cognome del Professore
	 * @param autorizzo boolean che rappresental abilitazione del Professore per il Tirocinio
	 * @param materia String che rappresenta la materia di insegnimento del Professore
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public Professore(String email, String password, String Nome, String Cognome, boolean autorizzo, String materia)
	{
		super(email, password, Nome, Cognome);
		this.autorizzo = autorizzo;
		this.materia = materia;
	}

	/**
	 * Ritorna un boolean che rappresenta se il Professore è abilitato per il Tirocinio
	 * 
	 * @return autorizzo
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public boolean isAutorizzo() 
	{
		return autorizzo;
	}

	/**
	 * Setta un boolean che rappresenta se il Professore è abilitato per il Tirocinio
	 * 
	 * @param autorizzo boolean che rappresenta se il Professore è abilitato per il Tirocinio
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setAutorizzo(boolean autorizzo) 
	{
		this.autorizzo = autorizzo;
	}

	/**
	 * Ritorna la materia di insegnimento del Professore
	 * 
	 * @return materia
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public String getMateria() 
	{
		return materia;
	}

	/**
	 * Setta la materia di insegnimento del Professore
	 * 
	 * @param materia String che rappresenta la materia di insegnimento del Professore
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setMateria(String materia)
	{
		this.materia = materia;
	}

	/**
	* Permette di definire una stringa che pu� essere considerata come la 
	* "rappresentazione testuale" dell'oggetto Professore.
	* 
	* @return Stringa che rappresenta una descrizione pi� accurata e consona dell'oggetto
	* 
	* @author Iannuzzi Nicolà
	*/
	public String toString() 
	{
		return super.toString()+ "Professore [autorizzo=" + autorizzo + ", materia=" + materia + "]";
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
	
}
