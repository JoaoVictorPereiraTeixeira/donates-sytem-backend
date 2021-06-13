package com.hackaton.hackatondonatessystem.domain;

import javassist.NotFoundException;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    @Getter
    private List<String> errors;

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }

    public ApiErrors(String mensagemErro){
        this.errors = Arrays.asList(mensagemErro);
    }

    public static void verifyListIsEmpty(List t,String message) throws NotFoundException {
        if(t.isEmpty()){
            throw new NotFoundException(message);
        }
    }

    public static void verifyIsEmpty(Object t, String message) throws NotFoundException {
        if(t == null || t.equals("")){
            throw new NotFoundException(message);
        }
    }
}