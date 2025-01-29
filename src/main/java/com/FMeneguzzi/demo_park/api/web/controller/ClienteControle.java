package com.FMeneguzzi.demo_park.api.web.controller;

import com.FMeneguzzi.demo_park.api.entities.Cliente;
import com.FMeneguzzi.demo_park.api.jwt.JwtUserDetails;
import com.FMeneguzzi.demo_park.api.service.ClienteService;
import com.FMeneguzzi.demo_park.api.service.UserService;
import com.FMeneguzzi.demo_park.api.web.dto.ClienteCreateDto;
import com.FMeneguzzi.demo_park.api.web.dto.ClienteResponseDto;
import com.FMeneguzzi.demo_park.api.web.dto.mapper.ClienteMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/clientes")
@RequiredArgsConstructor
public class ClienteControle {

    private final ClienteService clienteService;
    private final UserService userService;

    @PostMapping
   // @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<ClienteResponseDto> create(@RequestBody @Valid ClienteCreateDto dto,
                                                     @AuthenticationPrincipal JwtUserDetails userDetails){
        Cliente cliente = ClienteMapper.toCliente(dto);
        cliente.setUsuario(userService.buscarPorId(userDetails.getId()));
        clienteService.salvar(cliente);
        return ResponseEntity.status(201).body(ClienteMapper.toDto(cliente));

    }
}
