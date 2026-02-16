package com.relink.backend.controller;


import com.relink.backend.dto.RecruiterDto;
import com.relink.backend.dto.fromfrontend.RecruiterRequestDto;
import com.relink.backend.mapper.RecruiterMapper;
import com.relink.backend.model.Recruiter;
import com.relink.backend.record.CreateRecruiterRequest;
import com.relink.backend.service.RecruiterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path="/recruiters")
public class RecruiterController {

    private final RecruiterService recruiterService;
    private final RecruiterMapper recruiterMapper;

    public RecruiterController(RecruiterService recruiterService, RecruiterMapper recruiterMapper) {
        this.recruiterService = recruiterService;
        this.recruiterMapper = recruiterMapper;
    }


    @PostMapping(path="/create")
    public ResponseEntity<RecruiterDto> createRecruiter(
            @Valid @RequestBody RecruiterRequestDto requestDto
    ){
        CreateRecruiterRequest createRecruiterRequest=recruiterMapper.fromDto(requestDto);
        Recruiter recruiter=recruiterService.createRecruiter(createRecruiterRequest);
        RecruiterDto createdRecruiterDto=recruiterMapper.toDto(recruiter);
        return new ResponseEntity<>(createdRecruiterDto, HttpStatus.CREATED);

    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deleteRecruiter(@PathVariable UUID id){
        recruiterService.deleteRecruiter(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }






}
