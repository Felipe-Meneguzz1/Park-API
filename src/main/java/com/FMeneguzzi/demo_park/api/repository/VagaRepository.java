package com.FMeneguzzi.demo_park.api.repository;

import com.FMeneguzzi.demo_park.api.entities.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VagaRepository extends JpaRepository<Vaga, Long> {
    Optional<Vaga> findByCodigo(String codigo);

    Optional<Vaga> findFirstByStatus(com.FMeneguzzi.demo_park.api.entities.Vaga.StatusVaga statusVaga);
}
