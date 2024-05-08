package com.enviro.assessment.grad001.cynthiadladla.wastesorting.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class DuplicationOfValues extends RuntimeException {

    public DuplicationOfValues(String message){
        super(message);
    }
}
