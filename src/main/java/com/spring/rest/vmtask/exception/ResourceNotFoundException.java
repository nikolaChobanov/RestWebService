package com.spring.rest.vmtask.exception;


import com.spring.rest.vmtask.model.DatabaseStoredTypes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public ResourceNotFoundException(final Long id, DatabaseStoredTypes type){
        super("Could not find a" + type.name() + "with id: " + id);
    }

    public ResourceNotFoundException(final String name, DatabaseStoredTypes type){
        super("Could not find a" + type.name() + "with name: " + name);
    }
}