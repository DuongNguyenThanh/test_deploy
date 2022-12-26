package vn.edu.clevai.quiztest.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.edu.clevai.quiztest.exception.exeptions.BaseWebException;
import vn.edu.clevai.quiztest.payload.response.BadRequestResponse;
import vn.edu.clevai.quiztest.payload.response.ErrorResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(BaseWebException.class)
    public ResponseEntity<?> handleErrorRequest(BaseWebException ex, HttpServletRequest req) {
        return ResponseEntity.status(ex.getHttpStatus()).body(getErrorResponse(ex,req));
    }

    /*
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleRest(Throwable ex, HttpServletRequest request) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(getErrorResponse(ex, request).getStatus())
            .body(getErrorResponse(ex, request));
    }
    */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleBadRequest(MethodArgumentNotValidException ex,HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BadRequestResponse.builder().status(HttpStatus.BAD_REQUEST.value())
            .path(request.getServletPath()).errors(errors).message("Bad request").timestamp(new Date().getTime()).build());
    }

    private ErrorResponse getErrorResponse(Throwable ex, HttpServletRequest request) {
        HttpStatus status = (ex instanceof BaseWebException)
            ? ((BaseWebException) ex).getHttpStatus()
            : (ex instanceof MethodArgumentNotValidException) ? HttpStatus.BAD_REQUEST : HttpStatus.INTERNAL_SERVER_ERROR;
        return ErrorResponse.builder()
            .timestamp(new Date().getTime())
            .status(status.value())
            .path(request.getServletPath())
            .message(ex.getMessage())
            .build();
    }
}
