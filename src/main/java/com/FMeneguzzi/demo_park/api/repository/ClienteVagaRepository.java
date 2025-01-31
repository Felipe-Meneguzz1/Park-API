package com.FMeneguzzi.demo_park.api.repository;

import com.FMeneguzzi.demo_park.api.entities.ClienteVaga;
import com.FMeneguzzi.demo_park.api.repository.projection.ClienteVagaProjection;
import jdk.dynalink.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteVagaRepository extends JpaRepository<ClienteVaga, Long> {
    Optional<ClienteVaga> findByReciboAndDataSaidaIsNull(String recibo);

    long countByClienteCpfAndDataSaidaIsNotNull(String cpf);

    Page<ClienteVagaProjection> findAllByClienteCpf(String cpf, Pageable pageable);
}
