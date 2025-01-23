package com.FMeneguzzi.demo_park.api.web.controller;

import com.FMeneguzzi.demo_park.api.web.dto.UserCreateDto;
import com.FMeneguzzi.demo_park.api.web.dto.UserResponseDto;
import com.FMeneguzzi.demo_park.api.web.dto.UserSenhaDto;
import com.FMeneguzzi.demo_park.api.web.dto.mapper.UserMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.FMeneguzzi.demo_park.api.entities.User;
import com.FMeneguzzi.demo_park.api.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserCreateDto createDto) {
        User user = userService.salvar(UserMapper.toUser(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id) {
        User user1 = userService.buscarPorId(id);
        return ResponseEntity.ok(UserMapper.toDto(user1));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody UserSenhaDto dto) {
        User user = userService.editarSenha(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<User> users = userService.buscarTodos();
        return ResponseEntity.ok(UserMapper.toListDto(users));
    }

}

