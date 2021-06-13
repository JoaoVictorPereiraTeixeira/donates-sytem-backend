package com.hackaton.hackatondonatessystem.resources;

import com.hackaton.hackatondonatessystem.domain.ApiErrors;
import com.hackaton.hackatondonatessystem.exceptions.CredentialsException;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleNotFoundExceptions(NotFoundException ex){
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }


    @ExceptionHandler(CredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleCredentialsException(CredentialsException ex){
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrors handleGenericExceptions(Exception ex){
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());
        return new ApiErrors(errors);
    }
}