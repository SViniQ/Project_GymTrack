package com.dereckportela.gymtracker.repository;

import com.dereckportela.gymtracker.model.Instrutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {
    boolean existsByCpf(String cpf);
}
