
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Jugador
 * 
 * Crear la clase Jugador con los atributos nombre, edad, puntuación.
 */

public class Jugador {

    private String nombre;
    private int edad;
    private int puntuacion;

    public Jugador(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.puntuacion = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getPuntuacion() {
        return this.puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " - " + "Edad: " + edad + " - " + "Puntuación: " + puntuacion;
    }

    /**
     * Método mostrarPuntuacion no estático, muestra el nombre y la puntuación del
     * jugador.
     * 
     * @return nombre y puntuación del jugador.
     */
    public String mostrarPuntuacion() {
        return "Nombre: " + nombre + " - " + "Puntuación: " + puntuacion;
    }

    /**
     * Método pedirNombreJugador estático, pide el nombre del jugador y lo devuelve.
     * 
     * @return nombre del jugador en minúsculas y sin espacios al principio y al
     *         final.
     */
    public static String pedirNombreJugador() {
        Scanner sc = new Scanner(System.in);
        String nombre = "";
        Boolean seguir;

        do {
            seguir = false;

            System.out.print("Introduce el nombre del jugador: ");
            nombre = sc.nextLine().trim().toLowerCase();

            if (nombre.isEmpty()) {
                System.out.println("\nERROR: El nombre no puede estar vacío.\n");
                seguir = true;
            }

        } while (seguir);

        return nombre;
    }

    // Tarea Ruth, hacer un metodo que se llame pedirEdadJugador que le pida al
    // usuario su edad, valide si es mayor a 0 y que te devuelva la edad

    /**
     * Método pedirEdadJugador estático, pide la edad del jugador y la devuelve.
     * 
     * @return edad del jugador en números.
     */
    public static int pedirEdadJugador() {
        Scanner sc = new Scanner(System.in);
        int edad = 0;
        boolean seguir;

        do {
            seguir = false;

            try {

                System.out.print("Introduce la edad del jugador: ");
                edad = sc.nextInt();

                if (edad <= 0) {
                    System.out.println("ERROR! Eres demasiado joven... Dudo que sepas leer y escribir...");
                    seguir = true;
                }

            } catch (InputMismatchException ex) {
                sc.next();
                System.out.println("ERROR! Introduce números");
                seguir = true;
            }

        } while (seguir);

        return edad;
    }

    /**
     * Método sumarPuntos no estático, suma los puntos al jugador.
     * 
     * @param puntos
     */
    public void sumarPuntos(int puntos) {
        this.puntuacion += puntos;
    }

    /**
     * Método restarPuntos no estático, resta los puntos al jugador.
     * 
     * @param puntos
     */
    public void restarPuntos(int puntos) {
        this.puntuacion -= puntos;
    }

}