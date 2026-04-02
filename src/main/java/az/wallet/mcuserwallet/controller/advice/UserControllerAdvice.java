package az.wallet.mcuserwallet.controller.advice;

import az.wallet.mcuserwallet.dto.error.ErrorResponse;
import az.wallet.mcuserwallet.exception.EmailAlreadyExistsException;
import az.wallet.mcuserwallet.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestControllerAdvice
public class UserControllerAdvice {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> handleEmailAlreadyExistsException(
            EmailAlreadyExistsException e, HttpServletRequest request) {
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT)
                .message(e.getMessage())
                .error("Conflict")
                .path(request.getRequestURI())
                .build();

        log.error(error.toString());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(
            UserNotFoundException e,
            HttpServletRequest request){

        ErrorResponse error = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND)
                .error("User Not Found")
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();

        log.error(error.toString());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    // todo что делать если неправильно парсится айди кошелька.
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFormat(
            HttpMessageNotReadableException e,
            HttpServletRequest request) {

        ErrorResponse error = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .error("Bad Request")
                .message("Invalid request format (check UUID or JSON structure)")
                .path(request.getRequestURI())
                .build();

        log.error(error.toString());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e, HttpServletRequest request) {
        Map<String, String> validationErrors = new HashMap<>();

         e.getBindingResult()
                 .getFieldErrors()
                 .forEach(error -> validationErrors.put(error.getField(), error.getDefaultMessage()));


         ErrorResponse errorResponse = ErrorResponse.builder()
                 .timestamp(LocalDateTime.now())
                 .status(HttpStatus.BAD_REQUEST)
                 .error("Validation Failed")
                 .path(request.getRequestURI())
                 .errors(validationErrors)
                 .message("Invalid Request Info")
                 .build();
         log.error(errorResponse.toString());
         return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}
