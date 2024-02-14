package com.example.task.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CorsoDTO(

        @NotEmpty(message="il nome è necessario")
        String nome,
        @NotNull(message="Il prezzo è necessario")
        double prezzo,
        @NotEmpty(message = "La descrizione è necessaria")
        String descrizione,
        @NotNull(message = "L'id del docente è necessario")
        List<Long> docente_id,
         @NotNull(message = "L'id della materia è necessario")
                List<Long> materia_id
) {
}
