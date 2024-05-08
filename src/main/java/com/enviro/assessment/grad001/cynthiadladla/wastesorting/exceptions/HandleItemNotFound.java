package com.enviro.assessment.grad001.cynthiadladla.wastesorting.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class HandleItemNotFound extends RuntimeException{
    public HandleItemNotFound(String message) {
        super(message);
    }
}
