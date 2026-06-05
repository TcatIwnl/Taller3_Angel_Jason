package logicaMagos;

/**
 * Representa un hechizo de tipo Planta.
 */
public class Planta extends Hechizo {
    private int duracionStun;
    private double cantPlantas;
    
    /**
     * Constructor para el hechizo de Planta.
     * @param nombreHechizo Nombre del hechizo.
     * @param danio Daño base.
     * @param duracionStun Duración del aturdimiento.
     * @param cantPlantas Cantidad de plantas invocadas.
     */
    public Planta(String nombreHechizo, int danio, int duracionStun, double cantPlantas) {
        super(nombreHechizo, danio);
        this.duracionStun = duracionStun;
        this.cantPlantas = cantPlantas;
    }

    /**
     * Calcula el puntaje específico para el elemento Planta.
     * Fórmula: Daño + (Duración Stun * Cantidad Plantas).
     * @return El puntaje total del hechizo.
     */
    @Override
    public double calcularPuntaje() {
        return this.getDanio() + (this.duracionStun * this.cantPlantas);
    }

    public int getDuracionStun() { return duracionStun; }
    public void setDuracionStun(int duracionStun) { this.duracionStun = duracionStun; }
    public double getCantPlantas() { return cantPlantas; }
    public void setCantPlantas(double cantPlantas) { this.cantPlantas = cantPlantas; }
}