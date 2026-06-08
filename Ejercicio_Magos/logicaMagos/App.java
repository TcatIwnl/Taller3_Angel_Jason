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
                        sistema.guardarDatos();
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
                        sistema.agregarHechizoGeneral(new Fuego(nombreHechizo, tipo, danio, Integer.parseInt(partes[3])));
                        break;
                    case "Tierra":
                        sistema.agregarHechizoGeneral(new Tierra(nombreHechizo, tipo, danio, Integer.parseInt(partes[3])));
                        break;
                    case "Planta":
                        String[] atP = partes[3].split(",");
                        sistema.agregarHechizoGeneral(new Planta(nombreHechizo, tipo, danio, Integer.parseInt(atP[0]), Integer.parseInt(atP[1])));
                        break;
                    case "Agua":
                        String[] atA = partes[3].split(",");
                        sistema.agregarHechizoGeneral(new Agua(nombreHechizo, tipo, danio, Integer.parseInt(atA[0]), Integer.parseInt(atA[1])));
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
                    case 2: 
                    	modificarMagoInteractivo(teclado, sistema);
                    	break;
                    case 3: 
                    	eliminarMagoInteractivo(teclado, sistema);
                    	break;
                    case 4: 
                        agregarHechizoInteractivo(teclado, sistema); //
                        break;
                    case 5: 
                    	modificarHechizoInteractivo(teclado, sistema); 
                    	break;
                    case 6: 
                    	eliminarHechizoInteractivo(teclado, sistema);
                    	break;
                    case 7:
                    	subMenu = false;
                    	break;
                    default:
                    	System.out.println("Opción no válida.");
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

                            System.out.println("- " + h.getNombreHechizo() + " (Tipo: " + h.getTipo() + ")");
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
            String tipo = teclado.nextLine();

            switch (tipo) {
                case "1":
                	
                	tipo = "Fuego";
                    System.out.print("Ingrese la duración de la quemadura (turnos): ");
                    int duracionQuemadura = Integer.parseInt(teclado.nextLine());
                    sistema.agregarHechizoGeneral(new Fuego(nombre, tipo, danio, duracionQuemadura));
                    System.out.println("-> ¡Hechizo de Fuego '" + nombre + "' agregado exitosamente!");
                    break;
                case "2":
                	tipo = "Tierra";
                    System.out.print("Ingrese la mejora de defensa: ");
                    int mejoraDefensa = Integer.parseInt(teclado.nextLine());
                    sistema.agregarHechizoGeneral(new Tierra(nombre, tipo, danio, mejoraDefensa));
                    System.out.println("-> ¡Hechizo de Tierra '" + nombre + "' agregado exitosamente!");
                    break;
                case "3":
                	tipo = "Planta";
                    System.out.print("Ingrese la duración del aturdimiento (stun en turnos): ");
                    int duracionStun = Integer.parseInt(teclado.nextLine());
                    System.out.print("Ingrese la cantidad de plantas generadas (decimal, ej. 1.5): ");
                    int cantPlantas = Integer.parseInt(teclado.nextLine());
                    sistema.agregarHechizoGeneral(new Planta(nombre, tipo, danio, duracionStun, cantPlantas));
                    System.out.println("-> ¡Hechizo de Planta '" + nombre + "' agregado exitosamente!");
                    break;
                case "4":
                	tipo = "Agua";
                    System.out.print("Ingrese la cantidad de curación (heal): ");
                    int cantidadHeal = Integer.parseInt(teclado.nextLine());
                    System.out.print("Ingrese la presión del agua (decimal, ej. 2.0): ");
                    int presionAgua = Integer.parseInt(teclado.nextLine());
                    sistema.agregarHechizoGeneral(new Agua(nombre, tipo, danio, cantidadHeal, presionAgua));
                    System.out.println("-> ¡Hechizo de Agua '" + nombre + "' agregado exitosamente!");
                    break;
                default:
                    System.out.println("Error: Opción de elemento no válida. Operación cancelada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error de formato: Debe ingresar valores numéricos válidos. Operación cancelada.");
        }
    }
    
    /**
     * Despliega un menú interactivo para modificar los atributos de un hechizo existente.
     * Permite cambiar el nombre, daño base o atributos específicos según su elemento.
     * Guarda los cambios automáticamente en los archivos de persistencia.
     * * @param teclado Objeto Scanner para capturar la entrada del usuario.
     * @param sistema Instancia del sistema que contiene la lógica y los datos.
     */
    private static void modificarHechizoInteractivo(Scanner teclado, SistemaImpl sistema) {
        System.out.println("\n--- MODIFICAR HECHIZO ---");
        ArrayList<Hechizo> lista = sistema.getListaHechizosGenerales();
        
        // Mostrar hechizos disponibles
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i).getNombreHechizo() + " (Tipo: " + lista.get(i).getTipo() + ")");
        }
        
        System.out.print("\nSeleccione el número del hechizo a modificar: ");
        try {
            int indice = Integer.parseInt(teclado.nextLine()) - 1;
            
            if (indice >= 0 && indice < lista.size()) {
                Hechizo hechizoElegido = lista.get(indice);
                boolean editando = true;
                
                // Sub-menú para el Hechizo elegido
                while (editando) {
                    try {
                        System.out.println("\n>>> Editando el Hechizo: " + hechizoElegido.getNombreHechizo() + " <<<");
                        System.out.println("1. Cambiar nombre del hechizo");
                        System.out.println("2. Cambiar daño base (Actual: " + hechizoElegido.getDanio() + ")");
                        System.out.println("3. Cambiar atributos elementales de " + hechizoElegido.getTipo());
                        System.out.println("4. Volver al menú anterior");
                        System.out.print("Seleccione una opción: ");
                        
                        int opcion = Integer.parseInt(teclado.nextLine());
                        
                        switch (opcion) {
                            case 1:
                                // MODIFICAR NOMBRE
                                System.out.print("Ingrese el nuevo nombre para " + hechizoElegido.getNombreHechizo() + ": ");
                                String nuevoNombre = teclado.nextLine().trim();
                                if (!nuevoNombre.isEmpty()) {
                                    hechizoElegido.setNombreHechizo(nuevoNombre);
                                    System.out.println("¡Nombre actualizado con éxito!");
                                    sistema.guardarDatos(); // Guardar cambios
                                } else {
                                    System.out.println("Error: El nombre no puede estar vacío.");
                                }
                                break;
                                
                            case 2:
                                // MODIFICAR DAÑO BASE
                                System.out.print("Ingrese el nuevo daño base (actual: " + hechizoElegido.getDanio() + "): ");
                                int nuevoDanio = Integer.parseInt(teclado.nextLine());
                                hechizoElegido.setDanio(nuevoDanio);
                                System.out.println("¡Daño base actualizado con éxito!");
                                sistema.guardarDatos(); // Guardar cambios
                                break;
                                
                            case 3:
                                // MODIFICAR ATRIBUTOS ESPECÍFICOS SEGÚN TIPO
                                if (hechizoElegido instanceof Fuego) {
                                    Fuego f = (Fuego) hechizoElegido;
                                    System.out.print("Ingrese nueva duración de quemadura (actual: " + f.getDuracionQuemadura() + "): ");
                                    int nuevaDuracion = Integer.parseInt(teclado.nextLine());
                                    f.setDuracionQuemadura(nuevaDuracion);
                                    
                                } else if (hechizoElegido instanceof Tierra) {
                                    Tierra t = (Tierra) hechizoElegido;
                                    System.out.print("Ingrese nueva mejora de defensa (actual: " + t.getMejoraDefensa() + "): ");
                                    int nuevaDefensa = Integer.parseInt(teclado.nextLine());
                                    t.setMejoraDefensa(nuevaDefensa);
                                    
                                } else if (hechizoElegido instanceof Planta) {
                                    Planta p = (Planta) hechizoElegido;
                                    System.out.print("Ingrese nueva duración de aturdimiento (actual: " + p.getDuracionStun() + "): ");
                                    int nuevoStun = Integer.parseInt(teclado.nextLine());
                                    p.setDuracionStun(nuevoStun);
                                    
                                    System.out.print("Ingrese nueva cantidad de plantas (actual: " + (int)p.getCantPlantas() + "): ");
                                    int nuevaCant = Integer.parseInt(teclado.nextLine());
                                    p.setCantPlantas(nuevaCant);
                                    
                                } else if (hechizoElegido instanceof Agua) {
                                    Agua a = (Agua) hechizoElegido;
                                    System.out.print("Ingrese nueva cantidad de curación/heal (actual: " + a.getCantidadHeal() + "): ");
                                    int nuevoHeal = Integer.parseInt(teclado.nextLine());
                                    a.setCantidadHeal(nuevoHeal);
                                    
                                    System.out.print("Ingrese nueva presión del agua (actual: " + (int)a.getPresionAgua() + "): ");
                                    int nuevaPresion = Integer.parseInt(teclado.nextLine());
                                    a.setPresionAgua(nuevaPresion);
                                }
                                System.out.println("¡Atributos elementales actualizados con éxito!");
                                sistema.guardarDatos(); // Guardar cambios
                                break;
                                
                            case 4:
                                // SALIR DEL BUCLE
                                editando = false;
                                break;
                                
                            default:
                                System.out.println("Opción no válida. Intente nuevamente.");
                        }
                        
                    } catch (NumberFormatException e) {
                        System.out.println("Error de entrada: Debe ingresar un valor numérico válido. Intente de nuevo.");
                    }
                }
            } else {
                System.out.println("Error: Mago no encontrado o número fuera de rango.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error de formato al seleccionar hechizo: Debe ingresar un número.");
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }
    
    /**
     * Despliega un menú interactivo para modificar un mago existente.
     * Permite cambiar su nombre, enseñarle nuevos hechizos del catálogo mundial o hacer que olvide hechizos.
     * Guarda los cambios automáticamente en los archivos de persistencia.
     * * @param teclado Objeto Scanner para capturar la entrada del usuario.
     * @param sistema Instancia del sistema que contiene la lógica y los datos.
     */
    private static void modificarMagoInteractivo(Scanner teclado, SistemaImpl sistema) {
        System.out.println("\n--- MODIFICAR MAGO ---");
        ArrayList<Mago> magos = sistema.getListaMagosGenerales();
        
        for (int i = 0; i < magos.size(); i++) {
            System.out.println((i + 1) + ". " + magos.get(i).getNombreMago());
        }
        
        System.out.print("Seleccione el mago que desea modificar: ");
        try {
            int indexMago = Integer.parseInt(teclado.nextLine()) - 1;
            
            if (indexMago >= 0 && indexMago < magos.size()) {
                Mago magoElegido = magos.get(indexMago);
                boolean editando = true;
                
                // Sub-menú para el Mago elegido
                while (editando) {
                    System.out.println("\n>>> Editando al Mago: " + magoElegido.getNombreMago() + " <<<");
                    System.out.println("1. Cambiar nombre del mago");
                    System.out.println("2. Enseñar nuevo hechizo (Agregar)");
                    System.out.println("3. Olvidar hechizo (Eliminar)");
                    System.out.println("4. Volver al menú anterior");
                    System.out.print("Seleccione una opción: ");
                    
                    int opcion = Integer.parseInt(teclado.nextLine());
                    
                    switch (opcion) {
                        case 1:
                            // MODIFICAR NOMBRE
                            System.out.print("Ingrese el nuevo nombre para " + magoElegido.getNombreMago() + ": ");
                            String nuevoNombre = teclado.nextLine().trim();
                            if (!nuevoNombre.isEmpty()) {
                                magoElegido.setNombreMago(nuevoNombre);
                                System.out.println("¡Nombre actualizado con éxito!");
                                sistema.guardarDatos(); // Guardar cambios
                            } else {
                                System.out.println("Error: El nombre no puede estar vacío.");
                            }
                            break;
                            
                        case 2:
                            // AGREGAR HECHIZO
                            System.out.println("\nHechizos disponibles en el mundo:");
                            ArrayList<Hechizo> hechizosMundo = sistema.getListaHechizosGenerales();
                            for (int j = 0; j < hechizosMundo.size(); j++) {
                                System.out.println((j + 1) + ". " + hechizosMundo.get(j).getNombreHechizo());
                            }
                            
                            System.out.print("Seleccione el número del hechizo que desea enseñarle: ");
                            int indexHechizo = Integer.parseInt(teclado.nextLine()) - 1;
                            
                            if (indexHechizo >= 0 && indexHechizo < hechizosMundo.size()) {
                                Hechizo nuevoHechizo = hechizosMundo.get(indexHechizo);
                                
                                // Validamos que el hechizo que eligio no lo tenga
                                if (!magoElegido.getListaHechizos().contains(nuevoHechizo)) {
                                    magoElegido.agregarHechizo(nuevoHechizo);
                                    System.out.println("¡" + magoElegido.getNombreMago() + " ha aprendido " + nuevoHechizo.getNombreHechizo() + "!");
                                    sistema.guardarDatos(); // Guardar cambios
                                } else {
                                    System.out.println("El mago " + magoElegido.getNombreMago() + " ya posee " + nuevoHechizo.getNombreHechizo());
                                }
                            } else {
                                System.out.println("Número de hechizo fuera de rango.");
                            }
                            break;
                            
                        case 3:
                            // ELIMINAR HECHIZO
                            ArrayList<Hechizo> repertorio = magoElegido.getListaHechizos();
                            if (repertorio.isEmpty()) {
                                System.out.println("Este mago no conoce ningún hechizo actualmente.");
                            } else {
                                System.out.println("\nRepertorio actual de " + magoElegido.getNombreMago() + ":");
                                for (int j = 0; j < repertorio.size(); j++) {
                                    System.out.println((j + 1) + ". " + repertorio.get(j).getNombreHechizo());
                                }
                                System.out.print("Seleccione el número del hechizo a olvidar: ");
                                int indexOlvidar = Integer.parseInt(teclado.nextLine()) - 1;
                                
                                if (indexOlvidar >= 0 && indexOlvidar < repertorio.size()) {
                                    //l saca el elemento de la lista y te lo devuelve para poder mostrar su nombre
                                    Hechizo hechizoRemovido = repertorio.remove(indexOlvidar);
                                    System.out.println("¡" + magoElegido.getNombreMago() + " ha olvidado " + hechizoRemovido.getNombreHechizo() + "!");
                                    sistema.guardarDatos(); // Guardar cambios
                                } else {
                                    System.out.println("Número de hechizo fuera de rango.");
                                }
                            }
                            break;
                            
                        case 4:
                            // SALIR DEL BUCLE
                            editando = false;
                            break;
                            
                        default:
                            System.out.println("Opción no válida.");
                    }
                }
            } else {
                System.out.println("Mago no encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error de formato: Debe ingresar un valor numérico.");
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado.");
        }
    }
    
    /**
     * Lista los magos actuales y permite al usuario eliminar uno del sistema mediante su índice.
     * Si la operación es exitosa, se actualizan los archivos de texto.
     * * @param teclado Objeto Scanner para capturar la entrada del usuario.
     * @param sistema Instancia del sistema que contiene la lógica y los datos.
     */
    private static void eliminarMagoInteractivo(Scanner teclado, SistemaImpl sistema) {
        System.out.println("\n--- ELIMINAR MAGO ---");
        ArrayList<Mago> magos = sistema.getListaMagosGenerales();

        if (magos.isEmpty()) {
            System.out.println("No hay magos registrados en el sistema actualmente.");
            return;
        }

        for (int i = 0; i < magos.size(); i++) {
            System.out.println((i + 1) + ". " + magos.get(i).getNombreMago());
        }

        System.out.print("\nSeleccione el número del mago que desea eliminar (o 0 para cancelar): ");
        try {
            int index = Integer.parseInt(teclado.nextLine()) - 1;

            if (index == -1) {
                System.out.println("Operación cancelada.");
                return;
            }

            if (index >= 0 && index < magos.size()) {
                // remove() elimina el objeto y lo retorna temporalmente para poder usar su nombre en el mensaje
                Mago magoEliminado = magos.remove(index);
                System.out.println("¡El mago '" + magoEliminado.getNombreMago() + "' ha sido eliminado del sistema con éxito!");
                
                // Guardar cambios en los txt
                sistema.guardarDatos();
            } else {
                System.out.println("Error: El número seleccionado está fuera de rango.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error de formato: Debe ingresar solo números válidos. Operación cancelada.");
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }
    
    /**
     * Elimina un hechizo del catálogo general del mundo mágico.
     * Adicionalmente, recorre el repertorio de todos los magos y elimina este hechizo
     * de sus listas personales para mantener la coherencia de datos.
     * * @param teclado Objeto Scanner para capturar la entrada del usuario.
     * @param sistema Instancia del sistema que contiene la lógica y los datos.
     */
    private static void eliminarHechizoInteractivo(Scanner teclado, SistemaImpl sistema) {
        System.out.println("\n--- ELIMINAR HECHIZO ---");
        ArrayList<Hechizo> hechizos = sistema.getListaHechizosGenerales();

        if (hechizos.isEmpty()) {
            System.out.println("No hay hechizos registrados en el catálogo mundial.");
            return;
        }

        for (int i = 0; i < hechizos.size(); i++) {
            System.out.println((i + 1) + ". " + hechizos.get(i).getNombreHechizo() + " (Tipo: " + hechizos.get(i).getTipo() + ")");
        }

        System.out.print("\nSeleccione el número del hechizo que desea eliminar (o 0 para cancelar): ");
        try {
            int index = Integer.parseInt(teclado.nextLine()) - 1;

            if (index == -1) {
                System.out.println("Operación cancelada.");
                return;
            }

            if (index >= 0 && index < hechizos.size()) {
                // 1. Lo eliminamos del catálogo general
                Hechizo hechizoEliminado = hechizos.remove(index);
                
                // 2. Recorremos todos los magos para que "olviden" el hechizo
                ArrayList<Mago> magos = sistema.getListaMagosGenerales();
                for (Mago m : magos) {
                    // .remove(Objeto) busca si el mago tiene esa referencia y la borra. 
                    // Si el mago no lo tiene, no hace nada y sigue adelante.
                    m.getListaHechizos().remove(hechizoEliminado);
                }

                System.out.println("¡El hechizo '" + hechizoEliminado.getNombreHechizo() + "' ha sido erradicado del mundo!");
                System.out.println("-> Nota: Todos los magos que conocían este hechizo lo han olvidado.");
                
                // 3. Guardamos los cambios. Al sobrescribir Magos.txt, los repertorios ya saldrán limpios.
                sistema.guardarDatos();
                
            } else {
                System.out.println("Error: El número seleccionado está fuera de rango.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error de formato: Debe ingresar solo números válidos.");
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }
}