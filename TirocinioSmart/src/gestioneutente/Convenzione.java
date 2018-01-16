package gestioneutente;

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
		
	}
	
	/**
	 * Costruttore Convenzione con parametri:
	 * 
	 * @param id: int identificativo Convenzione
	 * @param data: String specifica la data della convenzione
	 * @param specifica
	 * 
	 * @author Iannuzzi Nicolà
	 */
	public Convenzione(int id, String data, String specifica)
	{
		this.id = id;
		this.data = data;
		this.specifica = specifica;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getData()
	{
		return data;
	}

	public void setData(String data)
	{
		this.data = data;
	}

	public String getSpecifica()
	{
		return specifica;
	}

	public void setSpecifica(String specifica) 
	{
		this.specifica = specifica;
	}
	
	
}
