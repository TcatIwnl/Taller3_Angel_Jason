package logicaMagos;

import java.util.ArrayList;

/**
 * Clase que implementa la lógica de negocio y administra las colecciones principales del programa.
 */
public class SistemaImpl implements Sistema {
    private ArrayList<Mago> listaMagosGenerales;
    private ArrayList<Hechizo> listaHechizosGenerales;

    public SistemaImpl() {
        this.listaMagosGenerales = new ArrayList<>();
        this.listaHechizosGenerales = new ArrayList<>();
    }

    /**
     * Añade un hechizo a la lista general del sistema.
     */
    public void agregarHechizoGeneral(Hechizo h) {
        this.listaHechizosGenerales.add(h);
    }

    /**
     * Añade un mago a la lista general del sistema.
     */
    public void agregarMagoGeneral(Mago m) {
        this.listaMagosGenerales.add(m);
    }
    
    @Override
    public ArrayList<Mago> getListaMagosGenerales() {
        return listaMagosGenerales;
    }

    @Override
    public ArrayList<Hechizo> getListaHechizosGenerales() {
        return listaHechizosGenerales;
    }
}