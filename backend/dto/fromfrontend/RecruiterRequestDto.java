package com.relink.backend.dto.fromfrontend;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RecruiterRequestDto(
       @NotBlank(message=ERROR_MESSAGE_NAME_LENGTH)
        String name,

        @NotBlank(message = ERROR_MESSAGE_SURNNAME_LENGTH)
        String surname,

        @Nullable
        String username,

        @Email
        @NotBlank(message = ERROR_MESSAGE_EMAIL_FORM)
        String email
) {
    private static final String ERROR_MESSAGE_NAME_LENGTH=
            "Name Must have  Between 2 and 30 characters";
    private static final String ERROR_MESSAGE_SURNNAME_LENGTH=
            "Surname must have between 2 and 30 characters";
    private static final String ERROR_MESSAGE_EMAIL_FORM=
            "Email must be a valid format,e.g. name@example.com";

}
