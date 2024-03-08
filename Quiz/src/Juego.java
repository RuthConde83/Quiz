import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Juego
 * 
 * Crear la clase Juego con los atributos jugadores (que será un array
 * de objetos Jugador) y preguntas (será un array de objetos Pregunta).
 */

/**
 * La clase Juego es la encargada de gestionar el juego, los jugadores y las
 * preguntas.
 */
class Juego {

    private ArrayList<Jugador> jugadores;
    private ArrayList<Pregunta> preguntas;
    private Jugador jugador;
    public final int NUM_MAXIMO_PREGUNTAS = 3;
    public final int RONDAS_PARTIDA = 3;
    public final int PUNTOS_POR_PREGUNTA = 10;

    /**
     * Constructor de la clase Juego, inicializa los atributos jugadores y
     * preguntas.
     */
    public Juego() {
        jugadores = new ArrayList<>();
        preguntas = new ArrayList<>();
        cargarJugadoresIniciales();
        cargarPreguntasIniciales();
    }

    /**
     * Método jugar no estático, inicia el juego y muestra el resultado final.
     */

    public void jugar() {

        jugador = seleccionarJugador();
        System.out.println("\n-------- Inicio de Partida --------\n");
        System.out.println(jugador.toString() + "\n");

        empezarPartida();

        System.out.println("\n-------- Fin de Partida --------\n");

    }

    /**
     * Método empezarPartida no estático, inicia la partida y muestra el resultado
     * final.
     */

    public void empezarPartida() {
        int puntuacionPartida = 0;
        ArrayList<Pregunta> preguntasPartida = getPreguntasPartida();
        Pregunta pregunta = null;

        for (int i = 0, numPregunta = 1; i < RONDAS_PARTIDA; i++, numPregunta++) {

            pregunta = preguntasPartida.get(i);

            System.out.println("\n-------- Pregunta #" + numPregunta + " --------\n");

            pregunta.mostrarPregunta();
            int opcion = App.obtenerOpcion(1, pregunta.getRespuestas().size(), "Seleccione la respuesta:");

            if (pregunta.validarSolucion(opcion - 1)) {
                puntuacionPartida += PUNTOS_POR_PREGUNTA;
                jugador.sumarPuntos(PUNTOS_POR_PREGUNTA);
                System.out.println("Respuesta Correcta!!!");
            } else {
                puntuacionPartida -= PUNTOS_POR_PREGUNTA;
                jugador.restarPuntos(PUNTOS_POR_PREGUNTA);
                System.out
                        .println("Respuesta Incorrecta, la respuesta correcta era la numero " + (pregunta.getSolucion()
                                + 1));
            }
            ;

            System.out.println("\n-------- Fin Pregunta --------\n");

        }
        mostrarPuntuacionFinal(puntuacionPartida);
    }

    /**
     * Método getPreguntasPartida no estático, devuelve un array de preguntas para
     * la partida.
     * 
     * @return ArrayList<Pregunta> preguntasPartida
     */
    private ArrayList<Pregunta> getPreguntasPartida() {
        Random random = new Random();

        Pregunta pregunta;
        ArrayList<Pregunta> preguntasPartida = new ArrayList<>();

        for (int i = 0, numRandom = 0; i < NUM_MAXIMO_PREGUNTAS; i++) {
            numRandom = random.nextInt(preguntas.size());
            pregunta = preguntas.get(numRandom);

            if (!preguntasPartida.contains(pregunta)) {
                preguntasPartida.add(pregunta);
            } else {
                i--;
            }
            ;
        }

        return preguntasPartida;
    }

    /**
     * Método mostrarPuntuacionFinal no estático, muestra la puntuación final de la
     * partida.
     * 
     * @param puntuacion Puntuación obtenida por el jugador en la partida.
     */
    public void mostrarPuntuacionFinal(int puntuacion) {
        if (puntuacion == NUM_MAXIMO_PREGUNTAS * PUNTOS_POR_PREGUNTA) {
            System.out.println("Puntuacion Perfecta, Felicidades!!!");
        } else if (puntuacion > 0) {
            System.out.println("Tu puntuacion fue " + puntuacion + " Puntos, bien hecho.");
        } else {
            System.out.println("Tu lamentable puntuacion fue " + puntuacion + " Puntos, Haber estudiado.");
        }
    }

    /**
     * Método seleccionarJugador no estático, muestra los jugadores disponibles y
     * permite al usuario seleccionar uno.
     * 
     * @return jugadores.get(posicion - 1) Jugador seleccionado.
     */
    private Jugador seleccionarJugador() {
        mostrarJugadores();
        int posicion = App.obtenerOpcion(1, jugadores.size(), "Seleccione un jugador: ");
        return jugadores.get(posicion - 1);
    }

    /**
     * Método agregarJugador no estático, permite al usuario agregar un nuevo
     * jugador a la lista de jugadores.
     */
    public void agregarJugador() {

        String nombre = "";
        int edad = 0;

        nombre = Jugador.pedirNombreJugador();
        edad = Jugador.pedirEdadJugador();

        Jugador jugador = new Jugador(nombre, edad);
        jugadores.add(jugador);

        System.out.println("\nSe ha agregado el jugador correctamente.\n");

    }

    /**
     * Método mostrarPuntuaciones no estático, muestra las puntuaciones de los
     * jugadores.
     */

    public void mostrarPuntuaciones() {

        System.out.println("---------- Puntuaciones ----------\n");
        for (int i = 0; i < jugadores.size(); i++) {
            System.out.println((i + 1) + ") " + jugadores.get(i).mostrarPuntuacion());
        }
        System.out.println("\n----------------------------------\n");

    }

    /**
     * Método eliminarJugador no estático, permite al usuario eliminar un jugador de
     * la lista de jugadores.
     */

    public void eliminarJugador() {
        System.out.println("---------- Jugadores ----------\n");

        mostrarJugadores();
        int numeroJugador = App.obtenerOpcion(1, jugadores.size(), "Selecciona el jugador: ");
        jugadores.remove(numeroJugador - 1);

        System.out.println("\nEl jugador " + numeroJugador + " ha sido eliminado.\n");
        System.out.println("-------------------------------\n");

    }

    /**
     * Método mostrarJugadores no estático, muestra los jugadores disponibles.
     */
    public void mostrarJugadores() {
        for (int i = 0; i < jugadores.size(); i++) {
            System.out.println((i + 1) + ") " + jugadores.get(i).toString());
        }
        System.out.println("");

    }

    /**
     * Método crearPreguntas no estático, permite al usuario crear nuevas preguntas
     * y añadirlas al juego.
     */

    public void crearPreguntas() {

        Scanner sc = new Scanner(System.in);

        Pregunta nuevaPregunta = new Pregunta();

        System.out.print("Introduzca la nueva pregunta: ");
        String preguntaTxt = sc.nextLine();
        nuevaPregunta.setPregunta(preguntaTxt);

        for (int i = 1; i <= NUM_MAXIMO_PREGUNTAS; i++) {
            System.out.print("Introduce la respuesta nº " + i + ": ");
            String respuesta = sc.nextLine();
            nuevaPregunta.getRespuestas().add(respuesta);
        }
        System.out.println("");
        mostrarRespuestas(nuevaPregunta.getRespuestas());
        int solucion = App.obtenerOpcion(1, NUM_MAXIMO_PREGUNTAS, "Seleciona la respuesta: ");
        nuevaPregunta.setSolucion(solucion - 1);
        System.out.println("");
    }

    /**
     * Método mostrarRespuestas no estático, muestra las respuestas de una pregunta.
     * 
     * @param respuestas ArrayList<String> Lista de respuestas.
     */
    public void mostrarRespuestas(ArrayList<String> respuestas) {
        for (int i = 0; i < respuestas.size(); i++) {
            System.out.println((i + 1) + ") " + respuestas.get(i).toString());
        }
    }

    /**
     * Método cargarJugadoresIniciales no estático, carga jugadores iniciales al
     * juego.
     */
    public void cargarJugadoresIniciales() {

        String[] nombres = { "Javier", "Ruth", "Maria Angeles" };
        int[] edades = { 25, 41, 40 };

        for (int index = 0; index < nombres.length; index++) {
            Jugador j = new Jugador(nombres[index], edades[index]);
            jugadores.add(j);
        }

    }

    /**
     * Método cargarPreguntasIniciales no estático, carga preguntas iniciales al
     * juego.
     */
    public void cargarPreguntasIniciales() {

        String[] preguntas = {
                "¿Cuántas zonas horarias tiene Rusia?",
                "¿Cuántas franjas tiene la bandera de estados unidos?",
                "¿Dónde nació Juana de Arco?",
                "¿Cual es el rio mas largo del mundo?",
                "¿Cuándo acabó la 2da guerra mundial?",
                "¿Cual es el animal nacional de australia?",
                "¿Cual es el pais mas pequeño del mundo?",
                "¿Cuantas teclas tiene un piano?",
                "¿Que conductor de la Formula 1 ha ganado mas campeonatos?",
                "¿Como se llama la iconica cafeteria de Friends?",
        };
        String[][] respuestas = {
                { "11", "13", "12" },
                { "11", "13", "12" },
                { "Francia", "Portugal", "España" },
                { "Amazonas", "Nilo", "Mississipi" },
                { "1943", "1947", "1945" },
                { "Canguro", "Caballo", "Serpiente" },
                { "San Marino", "Andorra", "El Vaticano" },
                { "75", "92", "88" },
                { "Fernando Alonso", "Michael Schumacher", "Lewis Hamilton" },
                { "Central Perk", "Joe", "Blue Bottle Coffe" }
        };
        int[] soluciones = { 0, 1, 0, 1, 2, 0, 2, 2, 1, 0 };

        for (int i = 0; i < preguntas.length; i++) {
            ArrayList<String> arrayListRespuesta = new ArrayList<String>(Arrays.asList(respuestas[i]));
            Pregunta p = new Pregunta(preguntas[i], arrayListRespuesta, soluciones[i]);
            this.preguntas.add(p);
        }

    }

}