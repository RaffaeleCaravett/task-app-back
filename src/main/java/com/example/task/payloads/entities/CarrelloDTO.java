package com.example.task.payloads.entities;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CarrelloDTO(
        @NotNull(message = "Lo user_id non deve essere null")
        long user_id,
@NotNull(message="Almeno un corso nel carrello")
        List<Long> corso_id
        ) {
}
