package gestioneutente;

/**
 * Oggetto astratto Utente che contiene le informazioni comuni a tutti gli utenti.
 * 
 * @author Caggiano Gianluca
 * 
 * @version 1.0
 */
public abstract class Utente {

  private String user;
  private String password;
  private String nome;
  private String cognome;
  private String tipo;
  private boolean autenticato;

  /**
   * Costruttore vuoto.
   * 
   * @author Caggiano Gianluca
   */
  public Utente() {
    autenticato = false;
  }

  /**
   * Costruttore con parametri.
   * 
   * @param user
   *          Stringa che rappresenta la user di accesso al sistema
   * @param password
   *          Stringa che rappresenta la password di accesso al sistema
   * @param nome
   *          Stringa che rappresenta il nome dell'utente
   * @param cognome
   *          Stringa che rappresenta il cognome dell'utente
   * 
   * @author Caggiano Gianluca
   */
  public Utente(String user, String password, String nome, String cognome) {
    super();
    this.user = user;
    this.password = password;
    this.nome = nome;
    this.cognome = cognome;
    autenticato = false;
  }

  /**
   * Restituisce la user utilizzata dall'utente per accedere al sistema.
   * 
   * @return La user di accesso dell'utente
   * 
   * @author Caggiano Gianluca
   */
  public String getUser() {
    return user;
  }

  /**
   * Setta l'user dell'utente.
   * 
   * @param user
   *          Stringa che rappresenta la user di accesso dell'utente
   * 
   * @pre user != null
   * @pre user.length() &#62;= 2 and nome.length() &#60;= 64
   * 
   * @author Caggiano Gianluca
   */
  public void setUser(String user) {
    this.user = user;
  }

  /**
   * Restituisce la password utilizzata dall'utente per accedere al sistema.
   * 
   * @return La password dell'utente
   * 
   * @author Caggiano Gianluca
   */
  public String getPassword() {
    return password;
  }

  /**
   * Setta la password dell'utente.
   * 
   * @param password
   *          Stringa che rappresenta la password dell'utente
   * 
   * @pre password != null
   * @pre password.length() &#62;= 2 and password.length() &#60;= 32
   * 
   * @author Caggiano Gianluca
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Restituisce il nome dell'utente.
   * 
   * @return Il nome dell'utente
   * 
   * @author Caggiano Gianluca
   */
  public String getNome() {
    return nome;
  }

  /**
   * Setta il nome dell'utente
   * 
   * @param nome
   *          Stringa che rappresenta il nome dell'utente
   * 
   * @pre nome != null
   * @pre nome.length() &#62;= 2 and nome.length() &#60;= 30
   * 
   * @author Caggiano Gianluca
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Restituisce il cognome dell'utente.
   * 
   * @return Il cognome dell'utente
   * 
   * @author Caggiano Gianluca
   */
  public String getCognome() {
    return cognome;
  }

  /**
   * Setta il cognome dell'utente.
   * 
   * @param cognome
   *          Stringa che rappresenta il cognome dell'utente
   * 
   * @pre cognome != null
   * @pre cognome.length() &#62;= 2 and nome.length() &#60;= 30
   * 
   * @author Caggiano Gianluca
   */
  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  /**
   * Restituisce il tipo dell'utente.
   * 
   * @return Il tipo dell'utente
   * 
   * @author Iannuzzi Nicola'
   */
  public String getTipo() {
    return tipo;
  }

  /**
   * Setta il tipo dell'utente.
   * 
   * @param tipo
   *          Stringa che rappresenta il tipo dell'utente
   * 
   * @author Iannuzzi Nicola'
   */
  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  /**
   * Verifica se un utente e' autenticato alla piattaforma.
   * 
   * @return {@code true} se e' autenticato, {@code false} altrimenti.
   * 
   * @author Caggiano Gianluca
   */
  public boolean isAutenticato() {
    return autenticato;
  }

  /**
   * Setta il parametro per autenticazione o logout di un utente.
   * 
   * @param autenticato
   *          the autenticato to set
   * 
   * @author Caggiano Gianluca
   */
  public void setAutenticato(boolean autenticato) {
    this.autenticato = autenticato;
  }

  /**
   * Permette di definire una stringa che puo' essere considerata come la "rappresentazione
   * testuale" dell'oggetto Utente.
   * 
   * @return Stringa che rappresenta una descrizione pi� accurata e consona dell'oggetto
   */
  public String toString() {
    return getClass().getName() + " [user= " + user + ", password= " + password + ", nome=" + nome
        + ", cognome=" + cognome + "]";
  }

  /**
   * Determina se due oggetti rappresentano lo stesso utente confrontando gli user dei suddetti.
   * 
   * @return true se gli user dei due oggetti sono gli stessi, false altrimenti
   */
  public boolean equals(Object object) {
    if (object == null) {
      return false;
    }

    if (object.getClass() != getClass()) {
      return false;
    }

    Utente utenteRegistrato = (Utente) object;

    return user.equals(utenteRegistrato.getUser());
  }

  /** Espressione regolare che definisce il formato del campo password. */
  public static final String PASSWORD_PATTERN = "^[0-9a-zA-Z._-]{6,32}$";

  /** Costante che definisce la minima lunghezza dei campi nome e cognome. */
  public static final int MIN_LUNGHEZZA_DUE = 2;

  /** Costante che definisce la massima lunghezza dei campi nome e cognome. */
  public static final int MAX_LUNGHEZZA_TRENTA = 30;

}
