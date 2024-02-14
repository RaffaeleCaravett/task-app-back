package com.example.task.payloads.entities;

import jakarta.validation.constraints.NotNull;

public record AcquistoDTO (

        @NotNull(message = "il carrello_id non può essere null")
        long carrello_id
){
}
