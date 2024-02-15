package com.example.task.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findAllByMese_Id(long mese_id);
    List<Task> findAllByUser_Id(long user_id);
    List<Task> findAllByUser_IdAndMese_id(long user_id,long mese_id);
}
