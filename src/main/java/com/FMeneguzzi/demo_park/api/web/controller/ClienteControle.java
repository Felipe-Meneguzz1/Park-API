package com.FMeneguzzi.demo_park.api.web.controller;

import com.FMeneguzzi.demo_park.api.entities.Cliente;
import com.FMeneguzzi.demo_park.api.jwt.JwtUserDetails;
import com.FMeneguzzi.demo_park.api.service.ClienteService;
import com.FMeneguzzi.demo_park.api.service.UserService;
import com.FMeneguzzi.demo_park.api.web.dto.ClienteCreateDto;
import com.FMeneguzzi.demo_park.api.web.dto.ClienteResponseDto;
import com.FMeneguzzi.demo_park.api.web.dto.UserResponseDto;
import com.FMeneguzzi.demo_park.api.web.dto.mapper.ClienteMapper;
import com.FMeneguzzi.demo_park.api.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cliente", description = "Contem todas as operações relativas os recursos de um cliente")
@RestController
@RequestMapping("api/v1/clientes")
@RequiredArgsConstructor
public class ClienteControle {

    private final ClienteService clienteService;
    private final UserService userService;

    @Operation(summary = "Criar um novo cliente", description = "Recurso para criar um cliente",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "409", description = "Cliente CPF já cadastrado no sistema",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "422", description = "Dados de entrada Invalidos",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Usuario sem permissão para acessar o recurso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),

            })
    @PostMapping
   // @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<ClienteResponseDto> create(@RequestBody @Valid ClienteCreateDto dto,
                                                     @AuthenticationPrincipal JwtUserDetails userDetails){
        Cliente cliente = ClienteMapper.toCliente(dto);
        cliente.setUsuario(userService.buscarPorId(userDetails.getId()));
        clienteService.salvar(cliente);
        return ResponseEntity.status(201).body(ClienteMapper.toDto(cliente));
    }

    @Operation(summary = "localizar um  cliente", description = "Recurso para localizar um cliente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso localizado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                     @ApiResponse(responseCode = "403", description = "Usuario sem permissão para acessar o recurso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),

            })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClienteResponseDto> getById(@PathVariable Long id){
        Cliente cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(ClienteMapper.toDto(cliente));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<Cliente>> getAll(Pageable pageable){
        Page<Cliente> clientes = clienteService.buscarTodos(pageable);
        return ResponseEntity.ok(clientes);
    }
}
