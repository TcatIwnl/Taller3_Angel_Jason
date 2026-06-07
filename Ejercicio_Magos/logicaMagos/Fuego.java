package logicaMagos;

/**
 * Representa un hechizo de tipo Fuego.
 */
public class Fuego extends Hechizo {
    private int duracionQuemadura;
    
    /**
     * Constructor para el hechizo de Fuego.
     * @param nombreHechizo Nombre del hechizo.
     * @param danio Daño base.
     * @param duracionQuemadura Turnos que dura la quemadura.
     */
    public Fuego(String nombreHechizo, String tipo, int danio, int duracionQuemadura) {
        super(nombreHechizo, tipo, danio);
        this.duracionQuemadura = duracionQuemadura;
    }

    /**
     * Calcula el puntaje específico para el elemento Fuego.
     * Fórmula: Daño * Duración de Quemadura.
     * @return El puntaje total del hechizo.
     */
    @Override
    public double calcularPuntaje() {
        return this.getDanio() * this.duracionQuemadura;
    }

    public int getDuracionQuemadura() { return duracionQuemadura; }
    public void setDuracionQuemadura(int duracionQuemadura) { this.duracionQuemadura = duracionQuemadura; }
}