package EF.__FloresRoman.api_rest.Exception;
public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(Long id) {
        super("Cliente con ID " + id + " no encontrado.");
    }
}