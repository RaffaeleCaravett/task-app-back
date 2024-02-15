package com.example.task.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findAllByMese_IdAndDataBetween(long meseId, LocalDate startDate, LocalDate endDate);
    List<Task> findAllByUser_Id(long user_id);
    List<Task> findAllByUser_IdAndMese_idAndDataBetween(long user_id,long mese_id, LocalDate startDate, LocalDate endDate);
}
