# Sistema Gestor de Magia - Taller 3 POO

## Integrantes

* **Integrante 1:** Angel Eduardo Olivares Flores - 22.338.590-7 - TcatIwnl
* **Integrante 2:** Jason Alexander Tapia Castro - 22.382.028-K - jtapia-code

**Carrera:** Ingeniería Civil en Computación e Informática  
**Sede:** Coquimbo, Universidad Católica del Norte (UCN)

## Descripción del Proyecto

Este proyecto es un sistema de gestión interactivo por consola basado en un mundo mágico. Permite administrar un catálogo mundial de hechizos elementales (Fuego, Agua, Planta, Tierra) y un registro de magos. El sistema permite realizar operaciones CRUD completas y generar reportes analíticos basados en el poder evaluado de cada entidad, calculados dinámicamente mediante el uso de herencia y polimorfismo.

## Estructura del Proyecto

El código fuente se encuentra organizado bajo el paquete logicaMagos, contenido en el directorio del proyecto:

* **Directorio de fuentes:** src/
* **App.java:** Punto de entrada con los datos de identificación obligatorios y gestión de menús por consola.
* **Sistema.java:** Interfaz que define los contratos de la lógica de negocio y operaciones principales.
* **SistemaImpl.java:** Controlador principal del sistema (Lógica, Ordenamientos y Persistencia).
* **Evaluable.java:** Interfaz que define el comportamiento para calcular el puntaje de las entidades.
* **Hechizo.java:** Superclase abstracta que representa la base de cualquier hechizo en el sistema.
* **Fuego.java:** Clase que representa un hechizo de tipo Fuego con su respectiva duración de quemadura.
* **Tierra.java:** Clase que representa un hechizo de tipo Tierra con su respectiva mejora de defensa.
* **Planta.java:** Clase que representa un hechizo de tipo Planta con su respectiva duración de aturdimiento y plantas generadas.
* **Agua.java:** Clase que representa un hechizo de tipo Agua con su respectiva cantidad de curación y presión de agua.
* **Mago.java:** Entidad que representa a un mago dentro del sistema y encapsula su repertorio de hechizos.
* **Archivos .txt:** Localizados en la raíz para la persistencia y carga de datos (Magos.txt y Hechizos.txt).

## Instrucciones de Ejecución

* **Requisitos:** JDK 17 o superior.
* **Importación:** Importar la carpeta raíz en Eclipse como un proyecto existente. El IDE reconocerá automáticamente la carpeta del proyecto como el contenedor de los paquetes de código.
* **Ejecución:** Ejecutar la clase App.java para iniciar la consola interactiva.

## Diagramas

Los entregables en PDF se encuentran en la raíz del repositorio:
* **ModeloDominio_Taller3.pdf**
* **UML_Taller3.pdf**

---
Desarrollado para la asignatura Programación Orientada a Objetos 2026-I.
