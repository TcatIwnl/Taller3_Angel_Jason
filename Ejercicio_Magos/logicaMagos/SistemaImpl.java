package logicaMagos;

import java.util.*;
import java.io.*;
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
    
    public void guardarDatos() {
        try {
            // 1. SOBRESCRIBIR HECHIZOS.TXT
            BufferedWriter writerHechizos = new BufferedWriter(new FileWriter("Hechizos.txt"));
            
            for (Hechizo h : getListaHechizosGenerales()) {
                // Reconstruimos la primera parte que todos tienen
                String linea = h.getNombreHechizo() + ";";
                
                if (h.getTipo().equalsIgnoreCase("Fuego")) {
                    Fuego f = (Fuego) h; // Casteo
                    linea += "Fuego;" + f.getDanio() + ";" + f.getDuracionQuemadura();
                } else if (h.getTipo().equalsIgnoreCase("Tierra")) {
                    Tierra t = (Tierra) h;
                    linea += "Tierra;" + t.getDanio() + ";" + t.getMejoraDefensa();
                } else if (h.getTipo().equalsIgnoreCase("Planta")) {
                    Planta p = (Planta) h;
                    linea += "Planta;" + p.getDanio() + ";" + p.getDuracionStun() + "," + (int)p.getCantPlantas(); 
                } else if (h.getTipo().equalsIgnoreCase("Agua")) {
                    Agua a = (Agua) h;
                    linea += "Agua;" + a.getDanio() + ";" + a.getCantidadHeal() + "," + (int)a.getPresionAgua();
                }
                
                writerHechizos.write(linea);
                writerHechizos.newLine();
            }
            writerHechizos.close();

            // 2. SOBRESCRIBIR MAGOS.TXT
            BufferedWriter writerMagos = new BufferedWriter(new FileWriter("Magos.txt"));
            
            for (Mago m : getListaMagosGenerales()) {
                String lineaMago = m.getNombreMago() + ";";
                
                ArrayList<Hechizo> repertorio = m.getListaHechizos();
                for (int i = 0; i < repertorio.size(); i++) {
                    lineaMago += repertorio.get(i).getNombreHechizo();
                    // Si no es el último hechizo, agregamos el separador "|"
                    if (i < repertorio.size() - 1) {
                        lineaMago += "|";
                    }
                }
                
                writerMagos.write(lineaMago);
                writerMagos.newLine();
            }
            writerMagos.close();
            
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar los archivos: " + e.getMessage());
        }
    }
}