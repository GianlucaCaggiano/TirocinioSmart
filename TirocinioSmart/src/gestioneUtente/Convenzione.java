package gestioneUtente;

/**
 * Classe che modella la convenzione tra Azienda e Università
 * 
 * @author Iannuzzi Nicolà
 *
 * @version 1.0
 */
public class Convenzione 
{
	
	private int id;
	private String data;
	private String specifica;
	
	/**
	 * Costruttore Vuoto Convenzione
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public Convenzione()
	{
		this.id = -1;
	}
	
	/**
	 * Costruttore Convenzione con parametri:
	 * 
	 * @param id: int identificativo Convenzione
	 * @param data: String specifica la data della convenzione
	 * @param specifica String che specifica una breve descrizione.
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public Convenzione(int id, String data, String specifica)
	{
		this.id = id;
		this.data = data;
		this.specifica = specifica;
	}

	/**
	 * Restituisce l'id della convenzione
	 * 
	 * @return ID
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public int getId() 
	{
		return id;
	}

	/**
	 * Setta l'id della convenzione
	 * 
	 * @param id Int che rappresenta l'id della convenzione
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * Ritorna la data in un cui è stata stipulata la convenzione
	 * 
	 * @return data
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public String getData()
	{
		return data;
	}

	/**
	 * Setta la data in cui è stipulata la convenzione
	 * 
	 * @param data String che rappresenta la data in cui è stata stipulata la convenzione
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setData(String data)
	{
		this.data = data;
	}

	/**
	 * Ritorna la specifica della convenzione
	 * 
	 * @return specifica 
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public String getSpecifica()
	{
		return specifica;
	}

	/**
	 * Setta la specifica della convenzione
	 * 
	 * @param specifica String 
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public void setSpecifica(String specifica) 
	{
		this.specifica = specifica;
	}
	
	/**
	* Determina se due oggetti rappresentano la stessa convenzione confrontandole alle convenzioni suddette.
	* 
	* @return true se le convenzioni dei due oggetti è la stessa, false altrimenti
	* 
	* @author Iannuzzi Nicolà
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
		    
		Convenzione convenzione = (Convenzione) object;
		    
		return convenzione.getId() == this.id;
	}

	/**
	 * 
	 * 
	 */
	public String toString() 
	{
		return "Convenzione [id=" + id + ", data=" + data + ", specifica=" + specifica + "]";
	}

}
