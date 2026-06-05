package logicaMagos;

/**
 * Interfaz que define el comportamiento para calcular el puntaje de entidades evaluables dentro del sistema.
 */
public interface Evaluable {
    
    /**
     * Calcula y retorna el puntaje de la entidad.
     * @return El puntaje calculado en formato double.
     */
    double calcularPuntaje();
}