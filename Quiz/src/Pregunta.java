import java.util.ArrayList;

/**
 * Pregunta
 * 
 * Crear la clase Pregunta con los atributos necesarios para almacenar
 * la pregunta, 3 opciones de respuesta y la solución a la pregunta
 * (respuesta correcta).
 */
public class Pregunta {

    private String pregunta;
    private ArrayList<String> respuestas;
    private int solucion;

    public Pregunta() {
        this.respuestas = new ArrayList<>();
        this.pregunta = "";
        this.solucion = 0;
    }

    public Pregunta(String pregunta, ArrayList<String> respuestas, int solucion) {
        this.pregunta = pregunta;
        this.respuestas = respuestas;
        this.solucion = solucion;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public ArrayList<String> getRespuestas() {
        return respuestas;
    }

    public void agregarRespuesta(String respuesta) {
        this.respuestas.add(respuesta);
    }

    public int getSolucion() {
        return solucion;
    }

    public void setSolucion(int solucion) {
        this.solucion = solucion;
    }

    public void mostrarPregunta() {
        String respuestaTexto = "";
        System.out.println(pregunta);
        for (int i = 0; i < respuestas.size(); i++) {
            respuestaTexto += (i + 1) + ") " + respuestas.get(i) + "   ";
        }
        System.out.println(respuestaTexto);
    }

    public boolean validarSolucion(int solucion) {

        return this.solucion == solucion;

    }

    @Override
    public String toString() {
        return "Respuestas: " + respuestas;

    }

}
