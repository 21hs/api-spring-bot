package br.com.facol.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlerNotFoundException(NotFoundException ex) {
        var exception = new ExceptionResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.name()
        );

        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGenericException(Exception ex) {
        var exception = new ExceptionResponse(
                "Erro interno no servidor. Tente novamente mais tarde.",
                HttpStatus.INTERNAL_SERVER_ERROR.name()
        );
        // opcional: logar o erro
        ex.printStackTrace();

        return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
