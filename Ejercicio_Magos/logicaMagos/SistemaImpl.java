package logicaMagos;

import java.util.ArrayList;

/**
 * Clase que implementa la lógica de negocio y administra las colecciones principales del programa.
 */
public class SistemaImpl implements Sistema {
    private ArrayList<Mago> listaMagosGenerales;
    private ArrayList<Hechizo> listaHechizosGenerales;

    /**
     * Constructor que inicializa las colecciones del sistema.
     */
    public SistemaImpl() {
        this.listaMagosGenerales = new ArrayList<>();
        this.listaHechizosGenerales = new ArrayList<>();
    }
    
}