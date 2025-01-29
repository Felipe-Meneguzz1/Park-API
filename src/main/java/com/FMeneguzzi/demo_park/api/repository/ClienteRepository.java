package com.FMeneguzzi.demo_park.api.repository;

import com.FMeneguzzi.demo_park.api.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
