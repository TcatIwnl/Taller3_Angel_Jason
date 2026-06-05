package logicaMagos;

/**
 * Representa un hechizo de tipo Tierra.
 */
public class Tierra extends Hechizo {
    private int mejoraDefensa;

    /**
     * Constructor para el hechizo de Tierra.
     * @param nombreHechizo Nombre del hechizo.
     * @param danio Daño base.
     * @param mejoraDefensa Puntos de mejora en la defensa.
     */
    public Tierra(String nombreHechizo, int danio, int mejoraDefensa) {
        super(nombreHechizo, danio);
        this.mejoraDefensa = mejoraDefensa;
    }

    /**
     * Calcula el puntaje específico para el elemento Tierra.
     * Fórmula: (Daño * Mejora Defensa) / 2.
     * @return El puntaje total del hechizo.
     */
    @Override
    public double calcularPuntaje() {
        return (this.getDanio() * this.mejoraDefensa) / 2.0;
    }

    public int getMejoraDefensa() { return mejoraDefensa; }
    public void setMejoraDefensa(int mejoraDefensa) { this.mejoraDefensa = mejoraDefensa; }
}