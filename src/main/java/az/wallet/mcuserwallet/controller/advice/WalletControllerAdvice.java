package az.wallet.mcuserwallet.controller.advice;

import az.wallet.mcuserwallet.exception.WalletNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class WalletControllerAdvice {

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleWalletNotFoundException(WalletNotFoundException exception) {
        Map<String, String>  body = Map.of("timestamp", LocalDateTime.now().toString(),
                "status", "404",
                "error", "UUID Not Found In Data Base",
                "message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(body);
    }

    // TODO add path in response body
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        Map<String, String>  body = Map.of("timestamp", LocalDateTime.now().toString(),
                "status", "400",
                "error", "Bad Request",
                "message", exception.getMessage(),
                "path", "/"
                );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(body);
    }
}
