package com.example.task.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DocenteDTO(
        @NotEmpty(message="Il nome deve essere presente")
        String nome,
        @NotNull(message="Deve esserci almeno una materia")
        List<Long> materia_id
) {
}
