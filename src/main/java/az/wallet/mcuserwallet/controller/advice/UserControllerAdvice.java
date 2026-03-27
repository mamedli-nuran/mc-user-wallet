package az.wallet.mcuserwallet.controller.advice;

import az.wallet.mcuserwallet.exception.RegisterNotCompleteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
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
}

/*
{
    "timestamp": "2026-03-27T18:56:50.342Z",
    "status": 500,
    "error": "Internal Server Error",
    "path": "/api/users/register"
}
 */