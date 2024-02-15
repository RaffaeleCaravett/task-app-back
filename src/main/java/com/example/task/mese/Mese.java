package com.example.task.mese;

import com.example.task.calendario.Calendario;
import com.example.task.enums.NomeMese;
import com.example.task.task.Task;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class Mese {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private NomeMese nomeMese;
    private int giorni;
    @ManyToMany(mappedBy = "meseList",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Calendario> calendarioList;
    @OneToMany(mappedBy = "mese")
    @JsonIgnore
    private List<Task>tasks;
}
