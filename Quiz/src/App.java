import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Ruth y Javi
 * 
 *         Crear un proyecto “Quiz” que sea una aplicación que implemente un
 *         juego con preguntas cortas.
 * 
 *         Crear la clase Jugador con los atributos nombre, edad, puntuación.
 * 
 *         Crear la clase Pregunta con los atributos necesarios para almacenar
 *         la pregunta, 3 opciones de respuesta y la solución a la pregunta
 *         (respuesta correcta).
 * 
 *         Crear la clase Juego con los atributos jugadores (que será un array
 *         de objetos Jugador) y preguntas (será un array de objetos Pregunta).
 * 
 *         Tendréis que crear un objeto de tipo Juego con datos ficticios que
 *         tenga mínimo 3 jugadores y 10 preguntas.
 * 
 *         Crear un método “jugar()” que será invocado por un jugador y que de
 *         manera aleatoria hará 3 de las preguntas que tiene el juego. Por cada
 *         respuesta correcta se sumará 10 a la puntuación y si hay un fallo se
 *         le restará 5 a la puntuación.
 * 
 *         Crear un menú que te permita añadir nuevos jugadores, eliminar
 *         jugadores, jugar, mostrar puntuaciones, añadir nuevas preguntas al
 *         juego.
 * 
 *         ** A las clases se le pueden añadir más atributos si se ve necesario.
 *         Crear los métodos que consideréis necesarios para estructurar vuestro
 *         código. Debe haber control de errores.
 * 
 *         ** Obligatorio entregar un documento pdf con el manual de usuario y
 *         especificación técnica del código.
 * 
 *         ** Obligatorio entrevista de prácticas.
 * 
 *         comentario extras
 */
public class App {
    static final String TITULO_MENU = "MENU INICIAL";
    static final String[] OPCIONES_INICIALES = { "Añadir un nuevo jugador", "Eliminar un jugador", "Jugar",
            "Mostrar puntuaciones", "Añadir nuevas preguntas al juego", "Salir" };

    public static void main(String[] args) throws Exception {

        Juego juego = new Juego();
        int opcion;

        do {

            mostrarMenu(TITULO_MENU, OPCIONES_INICIALES);
            opcion = obtenerOpcion(1, OPCIONES_INICIALES.length, "Selecciona una opcion:");
            System.out.println("----------------------------\n");

            switch (opcion) {
                case 1:
                    juego.agregarJugador();
                    break;
                case 2:
                    juego.eliminarJugador();
                    break;
                case 3:
                    juego.jugar();
                    break;
                case 4:
                    juego.mostrarPuntuaciones();
                    break;
                case 5:
                    juego.crearPreguntas();
                    break;
                case 6:
                    System.out.println("Hasta pronto...");
                    break;

            }

        } while (opcion != 6);

    }

    public static int obtenerOpcion(int min, int max, String titulo) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        final String MENSAJE_ERROR = "La opcion introducida no es valida.";
        boolean valido;

        do {

            valido = true;

            try {
                System.out.print(titulo.trim() + " ");
                opcion = sc.nextInt();

                valido = validarOpcion(opcion, min, max);

                if (!valido)
                    System.out.println(MENSAJE_ERROR);

            } catch (InputMismatchException e) {
                sc.next();
                System.out.println(MENSAJE_ERROR);
                valido = false;
            }

        } while (!valido);

        return opcion;

    }

    public static boolean validarOpcion(int opcion, int min, int max) {

        return (opcion >= min && opcion <= max);

    }

    public static void mostrarMenu(String titulo, String[] opciones) {

        System.out.println("---------- " + titulo + " ----------");
        for (int i = 0, indice = 1; i < opciones.length; i++, indice++) {
            System.out.println(indice + ") " + opciones[i]);
        }
        System.out.println("");

    }
}
