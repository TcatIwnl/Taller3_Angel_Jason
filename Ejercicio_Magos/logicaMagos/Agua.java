package logicaMagos;

/**
 * Representa un hechizo de tipo Agua.
 */
public class Agua extends Hechizo {
    private int cantidadHeal;
    private double presionAgua;
    
    /**
     * Constructor para el hechizo de Agua.
     * @param nombreHechizo Nombre del hechizo.
     * @param danio Daño base.
     * @param cantidadHeal Cantidad de curación que otorga.
     * @param presionAgua Presión del agua en el ataque.
     */
    public Agua(String nombreHechizo, int danio, int cantidadHeal, double presionAgua) {
        super(nombreHechizo, danio);
        this.cantidadHeal = cantidadHeal;
        this.presionAgua = presionAgua;
    }

    /**
     * Calcula el puntaje específico para el elemento Agua.
     * Fórmula: (Daño + Cantidad de Heal + Presión de Agua) * 2.
     * @return El puntaje total del hechizo.
     */
    @Override
    public double calcularPuntaje() {
        return (this.getDanio() + this.cantidadHeal + this.presionAgua) * 2.0;
    }

    public int getCantidadHeal() { return cantidadHeal; }
    public void setCantidadHeal(int cantidadHeal) { this.cantidadHeal = cantidadHeal; }
    public double getPresionAgua() { return presionAgua; }
    public void setPresionAgua(double presionAgua) { this.presionAgua = presionAgua; }
}