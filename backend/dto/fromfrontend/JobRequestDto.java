package com.relink.backend.dto.fromfrontend;

import com.relink.backend.enums.JobStatus;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public record JobRequestDto(
        @NotBlank(message = ERROR_MESSAGE_TITLE_LENGTH)
        String title,

        @NotBlank(message = ERROR_MESSAGE_LOCATION_LENGTH)
        String location,

        @NotBlank(message = ERROR_MESSAGE_DESCRIPTION_LENGTH)
        String description,

        @Nullable
        String salary,

        @NotNull(message = ERROR_MESSAGE_JOB_STATUS)
        JobStatus status,

        @Nullable
        @FutureOrPresent(message=ERROR_MESSAGE_CLOSING_DATE_IN_PAST)
        LocalDateTime closingDate,

        UUID recruiterId
) {
    private static final String ERROR_MESSAGE_TITLE_LENGTH=
            "Job Must have title";

    private static final String ERROR_MESSAGE_DESCRIPTION_LENGTH=
            "Job Must Have Description";

    private static final String ERROR_MESSAGE_JOB_STATUS=
            "Job Must Have Status";

    private static final String ERROR_MESSAGE_CLOSING_DATE_IN_PAST=
            "Job CLosing date must be in the future";

    private static final String ERROR_MESSAGE_LOCATION_LENGTH=
            "Job Must have Location";


}