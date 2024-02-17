package com.example.task.calendario;

import com.example.task.enums.TipoAnno;
import com.example.task.mese.Mese;
import com.example.task.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "calendari")
@Data
public class Calendario {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
private int anno;
@Enumerated(EnumType.STRING)
private TipoAnno tipoAnno;
@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
@JoinTable(name = "calendario_mese",
        joinColumns = @JoinColumn(name = "calendario_id"),
        foreignKey = @ForeignKey(name = "mese_id"),
        inverseJoinColumns = @JoinColumn(name = "mese_id"),
        inverseForeignKey = @ForeignKey(name = "calendario_id"))
private List<Mese> meseList;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "calendario_user",
            joinColumns = @JoinColumn(name = "calendario_id"),
            foreignKey = @ForeignKey(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            inverseForeignKey = @ForeignKey(name = "calendario_id"))
    private List<User> userList;
}
