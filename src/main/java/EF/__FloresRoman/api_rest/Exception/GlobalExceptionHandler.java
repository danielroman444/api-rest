package EF.__FloresRoman.api_rest.Exception;
import EF.__FloresRoman.api_rest.Util.GenericResponseDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalExceptionHandler {
    // Maneja excepciones de tipo EntityNotFoundException
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<GenericResponseDto> manejarNoEncontrado(EntityNotFoundException ex) {
        GenericResponseDto response = new GenericResponseDto("Recurso no encontrado", false, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    // Maneja excepciones de tipo MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponseDto> manejarValidacion(MethodArgumentNotValidException ex) {
        String mensaje = ex.getBindingResult().getFieldError().getDefaultMessage();
        GenericResponseDto response = new GenericResponseDto(mensaje, false, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    // Maneja excepciones de tipo ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<GenericResponseDto> manejarViolaciones(ConstraintViolationException ex) {
        GenericResponseDto response = new GenericResponseDto(ex.getMessage(), false, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    // Maneja excepciones generales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponseDto> manejarErroresGenerales(Exception ex) {
        GenericResponseDto response = new GenericResponseDto("Error inesperado: " + ex.getMessage(), false, null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}