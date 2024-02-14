package com.example.task.calendario;

import com.example.task.enums.TipoAnno;
import com.example.task.mese.Mese;
import jakarta.persistence.*;
import lombok.Data;

import java.lang.foreign.MemorySegment;
import java.util.List;

@Entity
@Table(name = "calendari")
@Data
public class Calendario {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
private int anno;
private TipoAnno tipoAnno;
@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
@JoinTable(name = "calendario_mese",
        joinColumns = @JoinColumn(name = "calendario_id"),
        foreignKey = @ForeignKey(name = "mese_id"),
        inverseJoinColumns = @JoinColumn(name = "mese_id"),
        inverseForeignKey = @ForeignKey(name = "calendario_id"))
private List<Mese> meseList;
}
