package com.relink.backend.record;

public record CreateRecruiterRequest(
        String name,
        String surname,
        String username,
        String email
) {
}
