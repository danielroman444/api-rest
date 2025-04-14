package EF.__FloresRoman.api_rest.Exception;
public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(Long id) {
        super("Cliente con ID " + id + " no encontrado.");
    }
}
//un constructor de la clase padre RuntimeException con un mensaje personalizado