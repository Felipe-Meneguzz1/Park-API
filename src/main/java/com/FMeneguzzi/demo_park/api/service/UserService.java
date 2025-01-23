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

	@org.springframework.transaction.annotation.Transactional
	public User editarSenha(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {
		if (!novaSenha.equals(confirmaSenha)) {
			throw new RuntimeException("Nova senha não confere com confirmação de senha.");
		}

		User user = buscarPorId(id);
		if (!user.getPassword().equals(senhaAtual)) {
			throw new RuntimeException("Sua senha não confere.");
		}

		user.setPassword(novaSenha);
		return user;
	}

	@Transactional
	public List<User> buscarTodos() {
		return userRepository.findAll();
	}
}
