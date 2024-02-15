package com.example.task.mese;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeseRepository extends JpaRepository<Mese, Long> {
}
