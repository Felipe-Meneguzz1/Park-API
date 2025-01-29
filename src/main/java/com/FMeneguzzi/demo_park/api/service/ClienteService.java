package com.FMeneguzzi.demo_park.api.service;

import com.FMeneguzzi.demo_park.api.entities.Cliente;
import com.FMeneguzzi.demo_park.api.exception.CpfUniqueViolationException;
import com.FMeneguzzi.demo_park.api.exception.EntityNotFoundException;
import com.FMeneguzzi.demo_park.api.repository.ClienteRepository;
import com.FMeneguzzi.demo_park.api.repository.projection.ClienteProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente){
        try {
            return clienteRepository.save(cliente);
        } catch (DataIntegrityViolationException ex) {
            throw new CpfUniqueViolationException(String.format("CPF '%s' não pode ser cadastrado, já existe no sistema", cliente.getCpf()));
        }
    }

    @Transactional
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Cliente com '%s' não encontrado", id))
        );
    }

    @Transactional
    public Page<ClienteProjection> buscarTodos(Pageable pageable) {
        return clienteRepository.findAllPageable(pageable);
    }

    @Transactional
    public Cliente buscarPorUsuarioId(Long id) {
        return clienteRepository.findByUsuarioId(id);
    }
}
