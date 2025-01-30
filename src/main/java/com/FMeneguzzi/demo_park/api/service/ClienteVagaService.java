package com.FMeneguzzi.demo_park.api.service;

import com.FMeneguzzi.demo_park.api.entities.ClienteVaga;
import com.FMeneguzzi.demo_park.api.repository.ClienteVagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ClienteVagaService {

    private final ClienteVagaRepository repository;

    @Transactional
    public ClienteVaga salvar(ClienteVaga clienteVaga){
        return repository.save(clienteVaga);
    }
}
