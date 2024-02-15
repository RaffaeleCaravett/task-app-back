package com.example.task.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record MeseDTO(
        @NotEmpty(message = "nome mese mancante")
        String nome_mese,
        @NotNull(message = "giorni mancanti")
        int giorni,
        @NotNull(message="Almeno un calendario")
        List<Long> calendario_id,
        List<Long> task_id
) {
}
