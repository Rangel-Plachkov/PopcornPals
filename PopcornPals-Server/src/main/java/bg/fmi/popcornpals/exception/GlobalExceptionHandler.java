package bg.fmi.popcornpals.exception;

import bg.fmi.popcornpals.exception.nocontent.NoContentException;
import bg.fmi.popcornpals.exception.notfound.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(NotFoundException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", 404);
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("timestamp", new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<Map<String, Object>> handleNoContentException(NoContentException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", 204);
        response.put("message", ex.getMessage());
        response.put("timestamp", new Date());
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationError(MethodArgumentNotValidException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        Map<String, Object> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        errorResponse.put("errors", errors);
        errorResponse.put("status", 400);
        errorResponse.put("message", "Validation Errors");
        errorResponse.put("timestamp", new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralError(Exception ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage() != null ? ex.getMessage() : "Unexpected error occurred");
        errorResponse.put("status", 500);
        errorResponse.put("timestamp", new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
