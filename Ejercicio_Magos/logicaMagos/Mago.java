package logicaMagos;

import java.util.ArrayList;

/**
 * Representa a un mago dentro del sistema, el cual posee un repertorio de hechizos.
 */
public class Mago implements Evaluable {
    private String nombreMago;
    private ArrayList<Hechizo> listaHechizos;

    /**
     * Constructor de la clase Mago.
     * @param nombreMago El nombre del mago.
     */
    public Mago(String nombreMago) {
        this.nombreMago = nombreMago;
        this.listaHechizos = new ArrayList<>();
    }

    /**
     * Añade un nuevo hechizo al repertorio del mago.
     * @param hechizo El objeto Hechizo a añadir.
     */
    public void agregarHechizo(Hechizo hechizo) {
        this.listaHechizos.add(hechizo);
    }

    /**
     * Calcula el puntaje total del mago sumando el puntaje de todos los hechizos que domina.
     * @return El puntaje total del mago.
     */
    @Override
    public double calcularPuntaje() {
        double puntajeTotal = 0;
        for (Hechizo h : listaHechizos) {
            puntajeTotal += h.calcularPuntaje();
        }
        return puntajeTotal;
    }

    public String getNombreMago() { return nombreMago; }
    public void setNombreMago(String nombreMago) { this.nombreMago = nombreMago; }
    public ArrayList<Hechizo> getListaHechizos() { return listaHechizos; }
    public void setListaHechizos(ArrayList<Hechizo> listaHechizos) { this.listaHechizos = listaHechizos; }
}