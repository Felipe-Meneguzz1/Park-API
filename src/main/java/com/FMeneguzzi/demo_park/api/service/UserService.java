package com.FMeneguzzi.demo_park.api.service;

import org.springframework.stereotype.Service;

import com.FMeneguzzi.demo_park.api.entities.User;
import com.FMeneguzzi.demo_park.api.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;

	@Transactional
	public User salvar(User user) {
		return userRepository.save(user);
		
	}

	@Transactional//(readOnly = true)
	public User buscarPorId(Long id) {
		return userRepository.findById(id).orElseThrow(
				() -> new RuntimeException("Usuario não encontrado")
		);
	}

	@Transactional
	public User editarSenha(Long id, String password) {
		User user = buscarPorId(id);
		user.setPassword(password);
		return user;
	}

	@Transactional
	public List<User> buscarTodos() {
		return userRepository.findAll();
	}
}
