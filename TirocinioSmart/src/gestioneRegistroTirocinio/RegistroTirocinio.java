package gestioneRegistroTirocinio;
import gestioneProgettoFormativo.*;

/**
 * Classe che modella il Registro Tirocinio per la piattaforma.
 * 
 * @author Iannuzzi Nicolà
 *
 * @version 1.0
 */
public class RegistroTirocinio 
{
	private int id;
	private ProgettoFormativo progettoFormativo;
	private boolean convalidaAzienda;
	private boolean convalidaProf;
	private boolean convalidaSegr;
	
	/**
	 * Costruttore Vuoto
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public RegistroTirocinio()
	{
		
	}
	
	/**
	 * Costruttore con Parametri:
	 * 
	 * @param id int che rappresenta l'identificativo del Registro Tirocinio
	 * @param progettoFormativo ProgettoFormativo che rappresenta a quale Progetto di Formativo è collegato il Registro
	 * @param convalidaAzienda boolean che rappresenta se l'Azienda ha convalidato il Registro di Tirocinio
	 * @param convalidaProf boolean che rappresenta se il Professore ha convalidato il Registro di Tirocinio
	 * @param convalidaSegr boolean che rappresenta se la Segreteria ha convalidato il Registro di Tirocinio
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public RegistroTirocinio(int id, ProgettoFormativo progettoFormativo, boolean convalidaAzienda, boolean convalidaProf, boolean convalidaSegr)
	{
		this.id = id;
		this.progettoFormativo = progettoFormativo;
		this.convalidaAzienda = convalidaAzienda;
		this.convalidaProf = convalidaProf;
		this.convalidaSegr = convalidaSegr;
	}

	/**
	 * Ritorna l'identificativo del Registro di Tirocinio
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
	 * Setta l'identificativo del Registro di Tirocinio
	 * 
	 * @param id int che rappresenta l'identificativo del Registro Tirocinio
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setId(int id) 
	{
		this.id = id;
	}
	
	/**
	 * Ritorna il Progetto di Formativo alla quale è collegato il Registro
	 * 
	 * @return progettoFormativo
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public ProgettoFormativo getProgettoFormativo() 
	{
		return progettoFormativo;
	}

	/**
	 * Setta il Progetto di Formativo alla quale è collegato il Registro
	 * 
	 * @param progettoFormativo ProgettoFormativo che rappresenta a quale Progetto di Formativo è collegato il Registro
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setProgettoFormativo(ProgettoFormativo progettoFormativo) 
	{
		this.progettoFormativo = progettoFormativo;
	}

	/**
	 * Ritorna un boolean che rappresenta se l'Azienda ha convalidato il Registro di Tirocinio
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
	 * Setta un boolean che rappresenta se l'Azienda ha convalidato il Registro di Tirocinio
	 * 
	 * @param convalidaAzienda che rappresenta se l'Azienda ha convalidato il Registro di Tirocinio
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setConvalidaAzienda(boolean convalidaAzienda) 
	{
		this.convalidaAzienda = convalidaAzienda;
	}

	/**
	 * Ritorna un boolean che rappresenta se il Professore ha convalidato il Registro di Tirocinio
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
	 * Setta un boolean che rappresenta se il Professore ha convalidato il Registro di Tirocinio
	 * 
	 * @param convalidaAzienda che rappresenta se il Professore ha convalidato il Registro di Tirocinio
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setConvalidaProf(boolean convalidaProf) 
	{
		this.convalidaProf = convalidaProf;
	}

	/**
	 * Ritorna un boolean che rappresenta se la Segreteria ha convalidato il Registro di Tirocinio
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
	 * Setta un boolean che rappresenta se la Segreteria ha convalidato il Registro di Tirocinio
	 * 
	 * @param convalidaAzienda che rappresenta se la Segreteria ha convalidato il Registro di Tirocinio
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setConvalidaSegr(boolean convalidaSegr) 
	{
		this.convalidaSegr = convalidaSegr;
	}

	/**
	* Permette di definire una stringa che può essere considerata come la 
	* "rappresentazione testuale" dell'oggetto RegistroTirocinio.
	* 
	* @return Stringa che rappresenta una descrizione pi� accurata e consona dell'oggetto
	* 
	* @author Iannuzzi Nicolà
	*/
	public String toString() 
	{
		return "RegistroTirocinio [id=" + id + ", progettoFormativo=" + progettoFormativo + ", convalidaAzienda="
				+ convalidaAzienda + ", convalidaProf=" + convalidaProf + ", convalidaSegr=" + convalidaSegr + "]";
	}
	
	/**
	* Determina se due oggetti rappresentano lo stesso Rregistro di Tirocinio confrontandoli.
	* 
	* @return true se i Registri di Tirocinio dei due oggetti sono gli stessi, false altrimenti
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
		    
		RegistroTirocinio registroTirocinio = (RegistroTirocinio) object;
		    
		return registroTirocinio.getId() == this.id;
	}
}
