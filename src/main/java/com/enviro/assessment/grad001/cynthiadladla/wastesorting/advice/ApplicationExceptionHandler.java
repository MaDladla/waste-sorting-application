package com.enviro.assessment.grad001.cynthiadladla.wastesorting.advice;

import com.enviro.assessment.grad001.cynthiadladla.wastesorting.exceptions.DuplicationOfValues;
import com.enviro.assessment.grad001.cynthiadladla.wastesorting.exceptions.HandleItemNotFound;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex){

        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->{
            errorMap.put(error.getField(), error.getDefaultMessage());
        });

        return errorMap;
    }

    @ExceptionHandler(HandleItemNotFound.class)
    public Map<String, String> handleInvalidArgument(HandleItemNotFound ex, HttpServletRequest request){

        Map<String, String> errorMap = new HashMap<>();
       errorMap.put(request.getRequestURI(),ex.getMessage());

        return errorMap;
    }

    @ExceptionHandler(DuplicationOfValues.class)
    public Map<String, String> handleInvalidArgument(DuplicationOfValues ex, HttpServletRequest request){

        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(request.getRequestURI(),ex.getMessage());

        return errorMap;
    }

}
