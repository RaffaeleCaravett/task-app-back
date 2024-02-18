package com.example.task.task;

import com.example.task.mese.Mese;
import com.example.task.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Data
public class Task {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
private String testo;
private LocalDate data;
private String ora;
private int giornoDelMese;
private int giornoDellaSettimana;
private int giornoDellaSettimanaNome;
@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mese_id")
    private Mese mese;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

}
