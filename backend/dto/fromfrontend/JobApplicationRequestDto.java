package com.relink.backend.dto.fromfrontend;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record JobApplicationRequestDto(

        @NotNull(message = ERROR_MESSAGE_JOB_ID_EMPTY)
        UUID jobID,

       //this will be somehow replaced
        @NotBlank(message = ERROR_MESSAGE_APPLICANT_NAME_LENGHT)
        String applicantName,

        @Email(message = "NOT VALID EMAIL")
        String applicantEmail

) {
     private final static String ERROR_MESSAGE_JOB_ID_EMPTY =
            "Job id is not provided";

     private final static String ERROR_MESSAGE_APPLICANT_NAME_LENGHT=
             "Name Must have  Between 2 and 30 characters";


}
