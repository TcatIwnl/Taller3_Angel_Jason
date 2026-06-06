//Angel Eduardo Olivares Flores
//22.338.590-7 / ICCI
//Jason Alexander Tapia Castro
//22.382.028-K / ICCI
package logicaMagos;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Clase principal que actúa como punto de entrada de la aplicación y gestiona las interacciones con el usuario.
 */
public class App {

    public static void main(String[] args) {
        // Inicializamos el sistema y cargamos los datos
        SistemaImpl sistema = new SistemaImpl();
        cargarDatos(sistema);
        
        System.out.println("=== Bienvenido al Sistema Gestor de Magia ===");
        
        Scanner teclado = new Scanner(System.in);
        boolean menuPrincipal = true;

        while (menuPrincipal) {
            try {
                System.out.println("\n=================================");
                System.out.println("   SISTEMA DE GESTIÓN MÁGICA     ");
                System.out.println("=================================");
                System.out.println("1. Panel de Administrador");
                System.out.println("2. Panel de Analista");
                System.out.println("3. Salir del Sistema");
                System.out.print("Seleccione una opción: ");
                
                int opcion = Integer.parseInt(teclado.nextLine());

                switch (opcion) {
                    case 1:
                        desplegarMenuAdministrador(teclado, sistema);
                        break;
                    case 2:
                        desplegarMenuAnalista(teclado, sistema);
                        break;
                    case 3:
                        System.out.println("Guardando cambios y cerrando el sistema...");
                        menuPrincipal = false;
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (Exception e) {
                System.out.println("Error de ingreso: Por favor, digite un número válido. " + e.getMessage());
            }
        }
        teclado.close();
    }

    /**
     * Método que lee los archivos .txt y alimenta al sistema usando Scanner.
     */
    private static void cargarDatos(SistemaImpl sistema) {
        try {
            // 1. LEER HECHIZOS
            Scanner scanHechizos = new Scanner(new File("Hechizos.txt"));
            while (scanHechizos.hasNextLine()) {
                String[] partes = scanHechizos.nextLine().split(";");
                String nombreHechizo = partes[0];
                String tipo = partes[1];
                int danio = Integer.parseInt(partes[2]);
                
                switch (tipo) {
                    case "Fuego":
                        sistema.agregarHechizoGeneral(new Fuego(nombreHechizo, danio, Integer.parseInt(partes[3])));
                        break;
                    case "Tierra":
                        sistema.agregarHechizoGeneral(new Tierra(nombreHechizo, danio, Integer.parseInt(partes[3])));
                        break;
                    case "Planta":
                        String[] atP = partes[3].split(",");
                        sistema.agregarHechizoGeneral(new Planta(nombreHechizo, danio, Integer.parseInt(atP[0]), Double.parseDouble(atP[1])));
                        break;
                    case "Agua":
                        String[] atA = partes[3].split(",");
                        sistema.agregarHechizoGeneral(new Agua(nombreHechizo, danio, Integer.parseInt(atA[0]), Double.parseDouble(atA[1])));
                        break;
                }
            }
            scanHechizos.close();

            // 2. LEER MAGOS
            Scanner scanMagos = new Scanner(new File("Magos.txt"));
            while (scanMagos.hasNextLine()) {
                String[] partes = scanMagos.nextLine().split(";");
                Mago mago = new Mago(partes[0]);
                
                String[] nombresHechizos = partes[1].split("\\|");
                
                for (String nombreH : nombresHechizos) {
                    for (Hechizo h : sistema.getListaHechizosGenerales()) {
                        if (h.getNombreHechizo().equals(nombreH)) {
                            mago.agregarHechizo(h);
                            break;
                        }
                    }
                }
                sistema.agregarMagoGeneral(mago);
            }
            scanMagos.close();

        } catch (Exception e) {
            System.out.println("Error al cargar los archivos: " + e.getMessage());
        }
    }

    /**
     * Despliega las opciones del Panel de Administrador (CRUD).
     */
    private static void desplegarMenuAdministrador(Scanner teclado, SistemaImpl sistema) {
        boolean subMenu = true;
        while (subMenu) {
            try {
                System.out.println("\n--- PANEL DE ADMINISTRADOR ---");
                System.out.println("1. Agregar Mago");
                System.out.println("2. Modificar Mago");
                System.out.println("3. Eliminar Mago");
                System.out.println("4. Agregar Hechizo");
                System.out.println("5. Modificar Hechizo");
                System.out.println("6. Eliminar Hechizo");
                System.out.println("7. Volver al Menú Principal");
                System.out.print("Seleccione una opción: ");

                int opcion = Integer.parseInt(teclado.nextLine());

                switch (opcion) {
                    case 1: 
                        agregarMagoInteractivo(teclado, sistema);
                        break;
                    case 2: System.out.println("[Acción: Modificar Mago] - Próximamente..."); break;
                    case 3: System.out.println("[Acción: Eliminar Mago] - Próximamente..."); break;
                    case 4: 
                        agregarHechizoInteractivo(teclado, sistema); //
                        break;
                    case 5: System.out.println("[Acción: Modificar Hechizo] - Próximamente..."); break;
                    case 6: System.out.println("[Acción: Eliminar Hechizo] - Próximamente..."); break;
                    case 7: subMenu = false; break;
                    default: System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error en el panel de administrador: Entrada no válida.");
            }
        }
    }

    /**
     * Despliega las opciones del Panel de Analista (Reportes y Rankings).
     */
    private static void desplegarMenuAnalista(Scanner teclado, SistemaImpl sistema) {
        boolean subMenu = true;
        while (subMenu) {
            try {
                System.out.println("\n--- PANEL DE ANALISTA ---");
                System.out.println("1. Top 10 Mejores Hechizos");
                System.out.println("2. Top 3 Mejores Magos");
                System.out.println("3. Mostrar todos los Hechizos");
                System.out.println("4. Mostrar todos los Magos");
                System.out.println("5. Mostrar Hechizos con su puntuación");
                System.out.println("6. Mostrar Magos con su puntuación");
                System.out.println("7. Volver al Menú Principal");
                System.out.print("Seleccione una opción: ");

                int opcion = Integer.parseInt(teclado.nextLine());

                switch (opcion) {
	            	case 1: 
	                    System.out.println("\n--- TOP 10 MEJORES HECHIZOS ---");
	                    ArrayList<Hechizo> topHechizos = sistema.obtenerTop10Hechizos();
	                    for (int i = 0; i < topHechizos.size(); i++) {
	                        Hechizo h = topHechizos.get(i);
	                        System.out.println((i + 1) + ". " + h.getNombreHechizo() + " | Puntaje: " + h.calcularPuntaje());
	                    }
	                    break;
	            	case 2: 
	                    System.out.println("\n--- TOP 3 MEJORES MAGOS ---");
	                    ArrayList<Mago> topMagos = sistema.obtenerTop3Magos();
	                    for (int i = 0; i < topMagos.size(); i++) {
	                        Mago m = topMagos.get(i);
	                        System.out.println((i + 1) + ". " + m.getNombreMago() + " | Puntaje Total: " + m.calcularPuntaje());
	                    }
	                    break;
                    case 3: 
                    	System.out.println("\n--- LISTA DE TODOS LOS HECHIZOS ---");
                        for (Hechizo h : sistema.getListaHechizosGenerales()) {
                            String tipo = "";
                            if (h instanceof Fuego) {
                                tipo = "Fuego";
                            } else if (h instanceof Agua) {
                                tipo = "Agua";
                            } else if (h instanceof Tierra) {
                                tipo = "Tierra";
                            } else if (h instanceof Planta) {
                                tipo = "Planta";
                            }
                            System.out.println("- " + h.getNombreHechizo() + " (Tipo: " + tipo + ")");
                        }
                        break;
                    case 4: 
                        System.out.println("\n--- LISTA DE TODOS LOS MAGOS ---");
                        for (Mago m : sistema.getListaMagosGenerales()) {
                            System.out.println("- " + m.getNombreMago());
                        }
                        break;
                    case 5: 
                        System.out.println("\n--- HECHIZOS Y SU PUNTUACIÓN ---");
                        for (Hechizo h : sistema.getListaHechizosGenerales()) {
                            System.out.println("- " + h.getNombreHechizo() + " | Puntaje: " + h.calcularPuntaje());
                        }
                        break;
                    case 6: 
                        System.out.println("\n--- MAGOS Y SU PUNTUACIÓN ---");
                        for (Mago m : sistema.getListaMagosGenerales()) {
                            System.out.println("- " + m.getNombreMago() + " | Puntaje Total: " + m.calcularPuntaje());
                        }
                        break;
                    case 7: 
                        subMenu = false; 
                        break;
                    default: 
                        System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error en el panel de analista: " + e.getMessage());
            }
        }
    }
    
    /**
     * Solicita los datos al usuario para crear un nuevo mago y lo añade al sistema.
     */
    private static void agregarMagoInteractivo(Scanner teclado, SistemaImpl sistema) {
        System.out.println("\n--- AGREGAR NUEVO MAGO ---");
        System.out.print("Ingrese el nombre del mago: ");
        String nombre = teclado.nextLine().trim();

        // Pequeña validación para evitar magos sin nombre
        if (nombre.isEmpty()) {
            System.out.println("Error: El nombre no puede estar vacío. Operación cancelada.");
            return;
        }

        Mago nuevoMago = new Mago(nombre);
        sistema.agregarMagoGeneral(nuevoMago);
        System.out.println("-> ¡Mago '" + nombre + "' agregado exitosamente al sistema!");
    }
    
    /**
     * Solicita los datos al usuario para crear un nuevo hechizo según su elemento y lo añade al sistema.
     * Implementa validación de tipos de datos para evitar caídas del sistema.
     */
    private static void agregarHechizoInteractivo(Scanner teclado, SistemaImpl sistema) {
        System.out.println("\n--- AGREGAR NUEVO HECHIZO ---");
        System.out.print("Ingrese el nombre del hechizo: ");
        String nombre = teclado.nextLine().trim();

        if (nombre.isEmpty()) {
            System.out.println("Error: El nombre del hechizo no puede estar vacío. Operación cancelada.");
            return;
        }

        try {
            System.out.print("Ingrese el daño base del hechizo (número entero): ");
            int danio = Integer.parseInt(teclado.nextLine());

            System.out.println("\nSeleccione el elemento del hechizo:");
            System.out.println("1. Fuego");
            System.out.println("2. Tierra");
            System.out.println("3. Planta");
            System.out.println("4. Agua");
            System.out.print("Opción: ");
            int tipo = Integer.parseInt(teclado.nextLine());

            switch (tipo) {
                case 1:
                    System.out.print("Ingrese la duración de la quemadura (turnos): ");
                    int duracionQuemadura = Integer.parseInt(teclado.nextLine());
                    sistema.agregarHechizoGeneral(new Fuego(nombre, danio, duracionQuemadura));
                    System.out.println("-> ¡Hechizo de Fuego '" + nombre + "' agregado exitosamente!");
                    break;
                case 2:
                    System.out.print("Ingrese la mejora de defensa: ");
                    int mejoraDefensa = Integer.parseInt(teclado.nextLine());
                    sistema.agregarHechizoGeneral(new Tierra(nombre, danio, mejoraDefensa));
                    System.out.println("-> ¡Hechizo de Tierra '" + nombre + "' agregado exitosamente!");
                    break;
                case 3:
                    System.out.print("Ingrese la duración del aturdimiento (stun en turnos): ");
                    int duracionStun = Integer.parseInt(teclado.nextLine());
                    System.out.print("Ingrese la cantidad de plantas generadas (decimal, ej. 1.5): ");
                    double cantPlantas = Double.parseDouble(teclado.nextLine());
                    sistema.agregarHechizoGeneral(new Planta(nombre, danio, duracionStun, cantPlantas));
                    System.out.println("-> ¡Hechizo de Planta '" + nombre + "' agregado exitosamente!");
                    break;
                case 4:
                    System.out.print("Ingrese la cantidad de curación (heal): ");
                    int cantidadHeal = Integer.parseInt(teclado.nextLine());
                    System.out.print("Ingrese la presión del agua (decimal, ej. 2.0): ");
                    double presionAgua = Double.parseDouble(teclado.nextLine());
                    sistema.agregarHechizoGeneral(new Agua(nombre, danio, cantidadHeal, presionAgua));
                    System.out.println("-> ¡Hechizo de Agua '" + nombre + "' agregado exitosamente!");
                    break;
                default:
                    System.out.println("Error: Opción de elemento no válida. Operación cancelada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error de formato: Debe ingresar valores numéricos válidos. Operación cancelada.");
        }
    }
}