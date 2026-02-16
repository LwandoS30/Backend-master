package com.relink.backend.exception;

import java.util.UUID;

public class JobNotFoundException extends RuntimeException{

    private final UUID id;

    public JobNotFoundException(UUID id){
        super(String.format("Job with Id '%s' does not exist",id));
        this.id=id;
    }

    public UUID getId(){
        return id;
    }

}
