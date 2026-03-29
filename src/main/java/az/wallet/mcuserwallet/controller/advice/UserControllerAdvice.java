package az.wallet.mcuserwallet.controller.advice;

import az.wallet.mcuserwallet.dto.error.ErrorResponse;
import az.wallet.mcuserwallet.exception.RegisterNotCompleteException;
import az.wallet.mcuserwallet.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResponseErrorHandler;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class UserControllerAdvice {
    @ExceptionHandler(RegisterNotCompleteException.class)
    public ResponseEntity<?> handleRegisterNotCompleteException(RegisterNotCompleteException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                // TODO сделать правильный вывод об ошибке. добавить путь откуда пришла ошибка
                //  и что именно человек вписать неправильно
                .body(Map.of("timestamp", LocalDateTime.now().toString(),
                        "status", "400",
                        "error", "Not Valid Info About User",
                        "message", e.getMessage()));
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(
            UserNotFoundException e,
            HttpServletRequest request){

        ErrorResponse error = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(404)
                .error("User Not Found")
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
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
                .status(400)
                .error("Bad Request")
                .message("Invalid request format (check UUID or JSON structure)")
                .path(request.getRequestURI())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }
}
