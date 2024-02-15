package com.example.task.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CalendarioDTO (
        @NotNull(message = "L'anno non può essere null")
        int anno,
        @NotEmpty(message = "Tipo anno necessario")
        String tipoAnno,
        @NotNull(message = "Almeno un mese in questo anno")
        List<Long> mese_id,
        long user_id
        ){
}
