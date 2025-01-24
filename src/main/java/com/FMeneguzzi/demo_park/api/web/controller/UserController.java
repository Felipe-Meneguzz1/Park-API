package com.FMeneguzzi.demo_park.api.web.controller;

import com.FMeneguzzi.demo_park.api.web.dto.UserCreateDto;
import com.FMeneguzzi.demo_park.api.web.dto.UserResponseDto;
import com.FMeneguzzi.demo_park.api.web.dto.UserSenhaDto;
import com.FMeneguzzi.demo_park.api.web.dto.mapper.UserMapper;
import com.FMeneguzzi.demo_park.api.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.FMeneguzzi.demo_park.api.entities.User;
import com.FMeneguzzi.demo_park.api.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Tag(name = "Usuarios", description = "Contem todas as operações relativas os recursos de um usurario")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;


    @Operation(summary = "Criar um novo Usuario", description = "Recurso para criar um usuario",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                             content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "409", description = "Usuario e-mail já cadastrado no sistema",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "422", description = "Dados de entrada Invalidos",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            })
    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserCreateDto createDto) {
        User user = userService.salvar(UserMapper.toUser(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(user));
    }

    @Operation(summary = "Recuperar um Usuario pelo ID", description = "Recurso para buscar um usuario",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Recursos não encontrados",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {
        User user1 = userService.buscarPorId(id);
        return ResponseEntity.ok(UserMapper.toDto(user1));
    }

    @Operation(summary = "Atualizar senha", description = "Recurso para atualizar senha",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Senha Atualizada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = void.class))),
                    @ApiResponse(responseCode = "400", description = "Senha não Confere",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Recursos não encontrados",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            })
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UserSenhaDto dto) {
        User user = userService.editarSenha(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Recuperar todos os Usuario pelo ID", description = "Recurso para buscar todos usuario",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuarios recuperados com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
             })
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<User> users = userService.buscarTodos();
        return ResponseEntity.ok(UserMapper.toListDto(users));
    }

}

