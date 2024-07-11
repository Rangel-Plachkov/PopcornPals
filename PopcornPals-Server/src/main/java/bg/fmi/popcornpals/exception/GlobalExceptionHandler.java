package bg.fmi.popcornpals.exception;

import bg.fmi.popcornpals.exception.nocontent.NoContentException;
import bg.fmi.popcornpals.exception.notfound.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String STATUS_STRING = "status";
    private static final String MESSAGE_STRING = "message";
    private static final String TIMESTAMP_STRING = "timestamp";

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(NotFoundException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put(STATUS_STRING, 404);
        errorResponse.put(MESSAGE_STRING, ex.getMessage());
        errorResponse.put(TIMESTAMP_STRING, new Date());
        log.warn("Resource not found: " + ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<Map<String, Object>> handleNoContentException(NoContentException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put(STATUS_STRING, 204);
        response.put(MESSAGE_STRING, ex.getMessage());
        response.put(TIMESTAMP_STRING, new Date());
        log.info("No content found: " + ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationError(MethodArgumentNotValidException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        Map<String, Object> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        errorResponse.put("errors", errors);
        errorResponse.put(STATUS_STRING, 400);
        errorResponse.put(MESSAGE_STRING, "Validation Errors");
        errorResponse.put(TIMESTAMP_STRING, new Date());
        log.warn("Validation errors occurred: " + errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralError(Exception ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put(MESSAGE_STRING, ex.getMessage() != null ? ex.getMessage() : "Unexpected error occurred");
        errorResponse.put(STATUS_STRING, 500);
        errorResponse.put(TIMESTAMP_STRING, new Date());
        log.error("Unexpected error occurred: " + ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
