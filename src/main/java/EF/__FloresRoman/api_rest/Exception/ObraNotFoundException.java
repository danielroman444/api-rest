package EF.__FloresRoman.api_rest.Exception;
public class ObraNotFoundException extends RuntimeException {
    public ObraNotFoundException(String mensaje) {
        super(mensaje);
    }
}
//un constructor de la clase padre RuntimeException con un mensaje
