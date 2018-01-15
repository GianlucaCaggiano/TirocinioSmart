package gestioneutente;

/**
 * Classe che modella uno studente che si è iscritto alla piattaforma.
 * 
 * @author Caggiano Gianluca
 *
 * @version 1.0
 */
public class Studente extends Utente{

	private String matricola;
	private String dataNascita;
	private String luogoNascita;
	private boolean abilitato;
	
	/**
	 * Costruttore vuoto.
	 * 
	 * @author Caggiano Gianluca
	 */
	public Studente() {
		
	}
	
	/**
	 * Costruttore con parametri:
	 * 
	 * @param matricola Stringa che rappresenta la matricola dello studente
	 * @param email Stringa che rappresenta la mail dello studente, nonchè la user di accesso al sistema per tale utente
	 * @param password Stringa che rappresenta la password di accesso al sistema
	 * @param nome Stringa che rapppresenta il nome dello studente
	 * @param cognome Stringa che rappresenta il cognome dello studente
	 * @param dataNascita Stringa che rappresenta la data di nascita dello studente
	 * @param luogoNascita Stringa che rapppresenta il luogo di nascita dello studente
	 * @param abilitato Valore booleano che rappresenta l'abilitazione o meno dell'account
	 * 
	 * @author Caggiano Gianluca
	 */
	public Studente(String matricola, String email, String password, String nome, String cognome, String dataNascita, String luogoNascita, boolean abilitato) {
		super(email,password,nome,cognome);
		this.matricola=matricola;
		this.dataNascita=dataNascita;
		this.luogoNascita=luogoNascita;
		this.abilitato=abilitato;
	}
	
	/**
	 * Restituisce la matricola dello studente
	 * 
	 * @return la matricola 
	 * 
	 * @author Caggiano Gianluca
	 */
	public String getMatricola() {
		return matricola;
	}
	/**
	 * Setta la matricola dello studente
	 * 
	 * @param matricola Stringa che rappresenta la matricola dello studente
	 * 
	 * @author Caggiano Gianluca
	 */
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	
	/**
	 * Restituisce la data di nascita dello studente
	 * 
	 * @return la data di nascita
	 * 
	 *  @author Caggiano Gianluca
	 */
	public String getDataNascita() {
		return dataNascita;
	}
	/**
	 * Setta la data di nascita dello studente
	 * 
	 * @param dataNascita Stringa che rappresenta la data di nascita
	 * 
	 * @author Caggiano Gianluca
	 */
	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	/**
	 * Restituisce il luogo di nascita dello studente
	 * 
	 * @return il luogo di nascita
	 * 
	 * @author Caggiano Gianluca
	 */
	public String getLuogoNascita() {
		return luogoNascita;
	}
	/**
	 * Setta il luogo di nascita dello studente
	 * 
	 * @param luogoNascita Stringa che rappresenta il luogo di nascita dello studente
	 * 
	 * @author Caggiano Gianluca
	 */
	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}
	
	/**
	 * Serve per verificare se lo studente è abilitato o meno
	 *  
	 * @return true se è abilitato, false altrimenti
	 * 
	 * @author Caggiano Gianluca
	 */
	public boolean isAbilitato() {
		return abilitato;
	}
	/**
	 * Serve per abilitare o disabilitare un account studente
	 * 
	 * @param abilitato valore booleano che rappresenta se lo studente è abilitato o meno
	 * 
	 * @author Caggiano Gianluca
	 */
	public void setAbilitato(boolean abilitato) {
		this.abilitato = abilitato;
	}

	/**
	* Permette di definire una stringa che può essere considerata come la 
	* "rappresentazione testuale" dell'oggetto Studente.
	* 
	* @return Stringa che rappresenta una descrizione più accurata e consona dell'oggetto
	* 
	* @author Caggiano Gianluca
	*/
	@Override
	public String toString() {
		return super.toString()+" [matricola= " + matricola + ", data di nascita= " + dataNascita + ", luogo di nascita=" + luogoNascita + ", abilitato=" + abilitato + "]";
	}

	/**
	* Determina se due oggetti rappresentano lo stesso utente confrontando gli user dei suddetti.
	* 
	* @return true se gli user dei due oggetti sono gli stessi, false altrimenti
	* 
	* @author Caggiano Gianluca
	*/
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}
	
}
