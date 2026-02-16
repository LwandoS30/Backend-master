package com.relink.backend.mapper.implimentation;

import com.relink.backend.dto.RecruiterDto;
import com.relink.backend.dto.fromfrontend.RecruiterRequestDto;
import com.relink.backend.mapper.RecruiterMapper;
import com.relink.backend.model.Recruiter;
import com.relink.backend.record.CreateRecruiterRequest;
import org.springframework.stereotype.Component;

@Component
public class RecruiterMapperImpl implements RecruiterMapper {
    @Override
    public CreateRecruiterRequest fromDto(RecruiterRequestDto dto) {
       return new CreateRecruiterRequest(
               dto.name(),
               dto.surname(),
               dto.username(),
               dto.email()
       );
    }

    @Override
    public RecruiterDto toDto(Recruiter recruiter) {
        return new RecruiterDto(
                recruiter.getId(),
                recruiter.getName(),
                recruiter.getSurname(),
                recruiter.getUsername(),
                recruiter.getEmail()
        );
    }


}
