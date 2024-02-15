package com.example.task.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record TaskDTO(
        @NotEmpty(message = "Testo vuoto")
        String testo,
        @NotNull(message="mese non presente")
        int mese,
        @NotNull(message = "giorno non presente")
        int giorno,

        @NotEmpty(message="ora non presente")
        String ora,
        @NotNull(message = "mese_id non presente")
        long mese_id,
        @NotNull(message = "user_id non presente")
        long user_id
) {
}
