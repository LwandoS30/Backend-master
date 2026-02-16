package com.relink.backend.mapper;

import com.relink.backend.dto.RecruiterDto;
import com.relink.backend.dto.fromfrontend.RecruiterRequestDto;
import com.relink.backend.model.Recruiter;
import com.relink.backend.record.CreateRecruiterRequest;

public interface RecruiterMapper {

    CreateRecruiterRequest fromDto(RecruiterRequestDto dto);

    RecruiterDto toDto(Recruiter recruiter);

}
