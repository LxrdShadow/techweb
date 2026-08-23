package com.project.techweb.prof;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfRepository extends JpaRepository<Prof, Integer> {
    List<Prof> findByNomContainingIgnoreCase(String nom);
}
