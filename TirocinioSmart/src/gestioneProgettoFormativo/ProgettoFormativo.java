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
	 * @param azienda Azienda che rappresenta l'Azienda coinvolta nel Progetto Formativo
	 * @param segreteria Segreteria che rappresenta la Segreteria
	 * @param studente Studente che rappresenta lo Studente coinvolto nel Progetto Formativo
	 * @param dataInizio String che rappresenta la data di inizio del Progetto Formativo
	 * @param obiettivi String che raprresenta gli obiettivi del Progetto Formativo
	 * @param dataFine String che raprresenta la data di fine del Progetto Formativo
	 * @param convalidaProf boolean che rappresenta l'approvazione del Progetto Formativo da parte del Professore
	 * @param convalidaSegr boolean che rappresenta l'approvazione del Progetto Formativo da parte della Segreteria
	 * @param sottoscrizioneStu boolean che rappresenta la sottoscrizione al Progetto Formaitvo da parte dello Studente
	 * 
	 * @author Iannuzzi Nicolà
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

	/**
	 * Ritorna l'identificativo del Progetto Formativo
	 * 
	 * @return id
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public int getId() 
	{
		return id;
	}

	/**
	 * Setta l'identificativo del Progetto Formativo
	 * 
	 * @param id int che rappresenta l'identificativo del Progetto Formativo
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setId(int id) 
	{
		this.id = id;
	}

	/**
	 * Ritorna l'Azienda coinvolta nel Progetto Formativo
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
	 * Setta l'Azienda coinvolta nel Progetto Formativo
	 * 
	 * @param azienda String che rappresenta l'Azienda coinvolta nel Progetto Formativo
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setAzienda(Azienda azienda)
	{
		this.azienda = azienda;
	}

	/**
	 * Ritorna la Segreteria coinvolta nel Progetto Formativo
	 * 
	 * @return segreteria
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public Segreteria getSegreteria() 
	{
		return segreteria;
	}

	/**
	 * Setta la Segreteria coinvolta nel Progetto Formativo
	 * 
	 * @param azienda String che rappresenta la Segreteria coinvolta nel Progetto Formativo
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setSegreteria(Segreteria segreteria) 
	{
		this.segreteria = segreteria;
	}

	/**
	 * Ritorna lo Studente che effettua il Progetto Formativo
	 * 
	 * @return studente
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public Studente getStudente() 
	{
		return studente;
	}

	/**
	 * Setta lo Studente che effettua il Progetto Formativo
	 * 
	 * @param azienda String che rappresenta lo Studente che effettua il Progetto Formativo
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setStudente(Studente studente) 
	{
		this.studente = studente;
	}

	/**
	 * Ritorna la data di inizio del Progetto Formativo
	 * 
	 * @return dataInizio
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public String getDataInizio()
	{
		return dataInizio;
	}

	/**
	 * Setta la data di inizio del Progetto Formativo
	 * 
	 * @param dataInizio
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setDataInizio(String dataInizio)
	{
		this.dataInizio = dataInizio;
	}

	/**
	 * Ritorna gli obiettivi del Progetto Formativo
	 * 
	 * @return obiettivi
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public String getObiettivi() 
	{
		return obiettivi;
	}

	/**
	 * Setta gli obiettivi del Progetto Formativo
	 * 
	 * @param obiettivi String che rappresenta gli obiettivi del Progetto Formativo
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setObiettivi(String obiettivi) 
	{
		this.obiettivi = obiettivi;
	}

	/**
	 * Ritorna data di Fine del Progetto Formativo
	 * 
	 * @return dataFine
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public String getDataFine() 
	{
		return dataFine;
	}

	/**
	 * Setta la data di fine del Progetto Formativo
	 * 
	 * @param dataFine String che rappresenta la data di fine del Progetto Formativo
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setDataFine(String dataFine) 
	{
		this.dataFine = dataFine;
	}

	/**
	 * Ritorna un boolean che rappresenta se il professore ha convalidato il Progetto Formativo
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
	 * Setta un boolean che rappresenta se il professore ha convalidato il Progetto Formativo
	 * 
	 * @param convalidaProf boolean che rappresenta se il professore ha convalidato il Progetto Formativo
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setConvalidaProf(boolean convalidaProf) 
	{
		this.convalidaProf = convalidaProf;
	}

	/**
	 * Ritorna un boolean che rappresenta se la Segreteria ha convalidato il Progetto Formativo
	 * 
	 * @return convalidaSegr
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public boolean isConvalidaSegr() 
	{
		return convalidaSegr;
	}
	
	/**
	 * Setta un boolean che rappresenta se la Segreteria ha convalidato il Progetto Formativo
	 * 
	 * @param convalidaSegr boolean che rappresenta se la Segretera ha convalidato il Progetto Formativo
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setConvalidaSegr(boolean convalidaSegr) 
	{
		this.convalidaSegr = convalidaSegr;
	}

	/**
	 * Ritorna un boolean che rappresenta se lo Studente ha sottoscritto il Progetto Formativo
	 * 
	 * @return sottoscrizioneStu
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public boolean isSottoscrizioneStu() 
	{
		return sottoscrizioneStu;
	}

	/**
	 * Setta un boolean che rappresenta se lo Studente ha sottoscritto il Progetto Formativo
	 * 
	 * @param sottoscrizioneStu boolean che rappresenta se lo Studente ha sottoscritto il Progetto Formativo
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setSottoscrizioneStu(boolean sottoscrizioneStu) 
	{
		this.sottoscrizioneStu = sottoscrizioneStu;
	}

	/**
	* Permette di definire una stringa che può essere considerata come la 
	* "rappresentazione testuale" dell'oggetto ProgettoFormativo.
	* 
	* @return Stringa che rappresenta una descrizione pi� accurata e consona dell'oggetto
	* 
	* @author Iannuzzi Nicolà
	*/
	public String toString() 
	{
		return "ProgettoFormativo [id=" + id + ", azienda=" + azienda + ", segreteria=" + segreteria + ", studente="
				+ studente + ", dataInizio=" + dataInizio + ", obiettivi=" + obiettivi + ", dataFine=" + dataFine
				+ ", convalidaProf=" + convalidaProf + ", convalidaSegr=" + convalidaSegr + ", sottoscrizioneStu="
				+ sottoscrizioneStu + "]";
	}
	

	/**
	* Determina se due oggetti rappresentano lo stesso ProgettoFormativo confrontandoli.
	* 
	* @return true se i Progetti Formativi dei due oggetti sono gli stessi, false altrimenti
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
		    
		ProgettoFormativo progettoFormativo = (ProgettoFormativo) object;
		    
		return progettoFormativo.getId() == this.id;
	}
	
}
