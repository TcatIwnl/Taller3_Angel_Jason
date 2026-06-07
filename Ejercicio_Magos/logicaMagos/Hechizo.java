package logicaMagos;

/**
 * Clase abstracta que representa la base de cualquier hechizo en el sistema.
 */
public abstract class Hechizo implements Evaluable {
    private String nombreHechizo;
    private int danio;
    private String tipo;
    /**
     * Constructor de la clase abstracta Hechizo.
     * @param nombreHechizo El nombre identificador del hechizo.
     * @param danio La cantidad de daño base que inflige.
     */
    public Hechizo(String nombreHechizo, String tipo , int danio) {
        this.nombreHechizo = nombreHechizo;
        this.danio = danio;
        this.tipo = tipo;
    }

    /**
     * Obtiene el nombre del hechizo.
     * @return nombreHechizo
     */
    public String getNombreHechizo() {
        return nombreHechizo;
    }

    /**
     * Establece el nombre del hechizo.
     * @param nombreHechizo El nuevo nombre del hechizo.
     */
    public void setNombreHechizo(String nombreHechizo) {
        this.nombreHechizo = nombreHechizo;
    }

    /**
     * Obtiene el daño del hechizo.
     * @return danio
     */
    public int getDanio() {
        return danio;
    }

    /**
     * Establece el daño del hechizo.
     * @param danio El nuevo valor de daño.
     */
    public void setDanio(int danio) {
        this.danio = danio;
    }
    
    public String getTipo() {
    	return tipo;
    }

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
    
}