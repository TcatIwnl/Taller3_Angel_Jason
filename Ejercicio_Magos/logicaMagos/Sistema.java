package logicaMagos;

import java.util.ArrayList;

/**
 * Interfaz que define los contratos de la lógica de negocio y las operaciones principales del sistema.
 */
public interface Sistema {
    
    /**
     * Añade un hechizo a la lista general del sistema.
     * @param h Hechizo a agregar.
     */
    void agregarHechizoGeneral(Hechizo h);
    
    /**
     * Añade un mago a la lista general del sistema.
     * @param m Mago a agregar.
     */
    void agregarMagoGeneral(Mago m);
    
    /**
     * Obtiene la lista general de magos.
     * @return ArrayList de magos.
     */
    ArrayList<Mago> getListaMagosGenerales();
    
    /**
     * Obtiene la lista general de hechizos.
     * @return ArrayList de hechizos.
     */
    ArrayList<Hechizo> getListaHechizosGenerales();
}