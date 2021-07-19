package br.com.zupacademy.valteir.proposta.config.errors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidationErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ErroResponse handle(BindException exception) {
        BindingResult result =  exception.getBindingResult();

        List<String> erros =  result.getFieldErrors().stream()
                .map(e -> e.getField() + " " + e.getDefaultMessage()).collect(Collectors.toList());

        if(erros.isEmpty())
            erros = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

        return new ErroResponse(erros);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalStateException.class, IllegalArgumentException.class})
    public ErroResponse handle(RuntimeException exception) {
        return new ErroResponse(Collections.singleton(exception.getMessage()));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErroResponse> handle(ResponseStatusException exception) {
        return ResponseEntity.status(exception.getStatus()).body(new ErroResponse(Collections.singleton(exception.getReason())));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErroResponse handle(Exception exception) {
        exception.printStackTrace();
        return new ErroResponse(Collections.singleton("Ocorreu um erro interno\n" + exception.getMessage()));
    }
}
