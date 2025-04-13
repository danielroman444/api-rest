package EF.__FloresRoman.api_rest.Exception;
public class InvalidPathException extends RuntimeException {
    public InvalidPathException(Long idcliente) {
        super("No se encontró el cliente con ID: " + idcliente);
    }
}