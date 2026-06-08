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

    /**
     * Obtiene una lista con los diez mejores hechizos ordenados de mayor a menor puntaje.
     * @return ArrayList con el Top 10 de Hechizos.
     */
    ArrayList<Hechizo> obtenerTop10Hechizos();
    
    /**
     * Obtiene una lista con los tres mejores magos ordenados de mayor a menor puntaje.
     * @return ArrayList con el Top 3 de Magos.
     */
    ArrayList<Mago> obtenerTop3Magos();
    
    /**
     * Sobrescribe los archivos de texto (Magos.txt y Hechizos.txt) con los datos actuales en memoria.
     */
    void guardarDatos();
    
}