package gestioneutente;

/**
 * Classe che modella una Segreteria per la piattaforma.
 * 
 * @author Iannuzzi Nicolà
 *
 * @version 1.0
 */
public class Segreteria extends Utente {
  private String telefono;
  private String email;

  /**
   * Costruttore senza parametri.
   * 
   * @author Iannuzzi Nicolà
   */
  public Segreteria() {

  }

  /**
   * Costruttore con parametri.
   * 
   * @param user
   *          String che rappresenta la Segreteria nonchè user di accesso alla piattaforma
   * @param password
   *          String che rappresenta la chiave di accesso alla piattaforma per la Segreteria
   * @param nome
   *          String che rappresenta il nome dell'adetto della Segreteria
   * @param cognome
   *          String che rappresenta il cognome dell'adetto della Segreteria
   * @param telefono
   *          String che rappresenta il numero di telefono della Segreteria
   * @param email
   *          String che rappresenta l'email della Segreteria
   * 
   * @author Iannuzzi Nicolà
   */
  public Segreteria(String user, String password, String nome, String cognome, String telefono,
      String email) {
    super(email, password, nome, cognome);
    this.telefono = telefono;
    this.email = email;
  }

  /**
   * Ritorna il numero di telefono della Segreteria.
   * 
   * @return telefono
   * 
   * @author Iannuzzi Nicolà
   */
  public String getTelefono() {
    return telefono;
  }

  /**
   * Setta il numero di telefono della Segreteria.
   * 
   * @param telefono
   *          String che rappresenta il numero di telefono della Segreteria
   * 
   * @author Iannuzzi Nicolà
   */
  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  /**
   * Ritorna l'email della Segreteria.
   * 
   * @return email
   * 
   * @author Iannuzzi Nicolà
   */
  public String getEmail() {
    return email;
  }

  /**
   * Setta l'email della Segreteria.
   * 
   * @param email
   *          String che rappresenta l'email della Segreteria
   * 
   * @author Iannuzzi Nicolà
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Permette di definire una stringa che pu� essere considerata come la "rappresentazione
   * testuale" dell'oggetto Segreteria.
   * 
   * @return Stringa che rappresenta una descrizione pi� accurata e consona dell'oggetto
   * 
   * @author Iannuzzi Nicolà
   */
  public String toString() {
    return super.toString() + "Segreteria [telefono=" + telefono + ", email=" + email + "]";
  }

  /**
   * Determina se due oggetti rappresentano lo stesso utente confrontando gli user dei suddetti.
   * 
   * @return true se gli user dei due oggetti sono gli stessi, false altrimenti
   * 
   * @author IannuzziNicolà
   */
  public boolean equals(Object arg0) {
    return super.equals(arg0);
  }

}
