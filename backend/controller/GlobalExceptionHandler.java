package com.relink.backend.controller;

import com.relink.backend.dto.ErrorDto;
import com.relink.backend.exception.JobNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

@ControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<ErrorDto> handleValidationExceptions(MethodArgumentNotValidException ex){

        String errorMessage=ex.getBindingResult().getFieldErrors().stream().findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).orElse("Validation Failed");
        ErrorDto errorDto=new ErrorDto(errorMessage);
        return new ResponseEntity<>(errorDto,HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<ErrorDto> handleJobNotFoundException(JobNotFoundException ex){

        UUID jobNotFoundId=ex.getId();
        String errorMessage=String.format("Job with Id '%s' is not found",jobNotFoundId);
        ErrorDto errorDto=new ErrorDto(errorMessage);

        return new ResponseEntity<>(errorDto,HttpStatus.BAD_REQUEST);

    }


}
