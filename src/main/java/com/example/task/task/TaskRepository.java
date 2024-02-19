package com.example.task.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findAllByMese_IdAndDataBetween(long meseId, LocalDate startDate, LocalDate endDate);
    List<Task> findAllByUser_Id(long user_id);
    List<Task> findAllByUser_IdAndMese_idAndDataBetween(long user_id,long mese_id, LocalDate startDate, LocalDate endDate);
    List<Task> findAllByUser_IdAndMese_idAndGiornoDellaSettimanaNomeAndDataBetween(long user_id,long mese_id,String nome, LocalDate startDate, LocalDate endDate);
    List<Task> findAllByUser_IdAndMese_idAndDataBetweenAndGiornoDelMese(long user_id,long mese_id, LocalDate startDate, LocalDate endDate,int giornoDelMese);
    Optional<Task> findByMese_idAndUser_IdAndOraAndDataBetween(long mese_id, long user_id, String ora, LocalDate startDate, LocalDate endDate);

}
