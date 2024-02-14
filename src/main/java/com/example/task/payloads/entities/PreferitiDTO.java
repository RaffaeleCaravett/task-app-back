package com.example.task.payloads.entities;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PreferitiDTO(

        @NotNull(message = "User_id necessario")
        long user_id,
        @NotNull(message = "Necessario almeno un corso")
        List<Long> corso_id
) {
}
