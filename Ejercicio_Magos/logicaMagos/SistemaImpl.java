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
    
    /**
     * Genera un listado con los 10 hechizos de mayor puntuación en el sistema.
     * Utiliza el algoritmo de ordenamiento de burbuja de forma descendente.
     * @return ArrayList con los elementos ordenados y limitados.
     */
    @Override
    public ArrayList<Hechizo> obtenerTop10Hechizos() {
        // Se realiza una copia de la lista original para preservar su orden estructural
        ArrayList<Hechizo> copia = new ArrayList<>(this.listaHechizosGenerales);

        // Algoritmo de ordenamiento por burbuja en orden descendente
        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = 0; j < copia.size() - 1 - i; j++) {
                if (copia.get(j).calcularPuntaje() < copia.get(j + 1).calcularPuntaje()) {
                    // Intercambio de posiciones utilizando una variable temporal
                    Hechizo temp = copia.get(j);
                    copia.set(j, copia.get(j + 1));
                    copia.set(j + 1, temp);
                }
            }
        }

        // Extracción y acotación de los primeros 10 elementos ordenados
        ArrayList<Hechizo> top10 = new ArrayList<>();
        int limite = Math.min(10, copia.size());
        for (int i = 0; i < limite; i++) {
            top10.add(copia.get(i));
        }
        
        return top10;
    }

    /**
     * Genera un listado con los 3 magos de mayor puntuación acumulada en el sistema.
     * Utiliza el algoritmo de ordenamiento de burbuja de forma descendente.
     * @return ArrayList con los elementos ordenados y limitados.
     */
    @Override
    public ArrayList<Mago> obtenerTop3Magos() {
        // Se realiza una copia de la lista original para preservar su orden estructural
        ArrayList<Mago> copia = new ArrayList<>(this.listaMagosGenerales);

        // Algoritmo de ordenamiento por burbuja en orden descendente
        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = 0; j < copia.size() - 1 - i; j++) {
                if (copia.get(j).calcularPuntaje() < copia.get(j + 1).calcularPuntaje()) {
                    // Intercambio de posiciones utilizando una variable temporal
                    Mago temp = copia.get(j);
                    copia.set(j, copia.get(j + 1));
                    copia.set(j + 1, temp);
                }
            }
        }

        // Extracción y acotación de los primeros 3 elementos ordenados
        ArrayList<Mago> top3 = new ArrayList<>();
        int limite = Math.min(3, copia.size());
        for (int i = 0; i < limite; i++) {
            top3.add(copia.get(i));
        }
        
        return top3;
    }
}