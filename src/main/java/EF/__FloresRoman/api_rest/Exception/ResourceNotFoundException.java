package EF.__FloresRoman.api_rest.Exception;
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}
//un constructor de la clase padre RuntimeException con un mensaje personalizado