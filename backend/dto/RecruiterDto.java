package com.relink.backend.dto;

import java.util.UUID;

public record RecruiterDto(
        UUID id,
        String name,
        String surname,
        String username,
        String email

) {
}
