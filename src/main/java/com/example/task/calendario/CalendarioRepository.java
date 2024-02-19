package com.example.task.calendario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalendarioRepository extends JpaRepository<Calendario,Long> {
Optional<Calendario> findByAnno(int anno);

    Calendario findByAnnoAndUserList_Id(int anno,long user_id);
    List<Calendario> findByUserList_Id(long user_id);

}
