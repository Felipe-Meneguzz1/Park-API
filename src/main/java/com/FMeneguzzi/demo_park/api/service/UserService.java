	package com.FMeneguzzi.demo_park.api.service;

	import com.FMeneguzzi.demo_park.api.exception.EntityNotFoundException;
	import com.FMeneguzzi.demo_park.api.exception.UsernameUniqueViolationException;
	import org.springframework.dao.DataIntegrityViolationException;
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
			try {
				return userRepository.save(user);
			}catch (DataIntegrityViolationException ex) {
				throw new UsernameUniqueViolationException(String.format("Username {%s}já cadastrado", user.getUsername()));
			}
		}

		@Transactional//(readOnly = true)
		public User buscarPorId(Long id) {
			return userRepository.findById(id).orElseThrow(
					() -> new EntityNotFoundException(String.format("Usuario id=%s não encontrado", id))
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
