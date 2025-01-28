	package com.FMeneguzzi.demo_park.api.service;

	import com.FMeneguzzi.demo_park.api.exception.EntityNotFoundException;
	import com.FMeneguzzi.demo_park.api.exception.UsernameUniqueViolationException;
	import com.FMeneguzzi.demo_park.api.jwt.JwtUtils;
	import org.springframework.dao.DataIntegrityViolationException;
	import org.springframework.security.crypto.password.PasswordEncoder;
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
		private final PasswordEncoder passwordEncoder;


		@Transactional
		public User salvar(User user) {
			try {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
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
			if (!passwordEncoder.matches(senhaAtual, user.getPassword())) {
				throw new RuntimeException("Sua senha não confere.");
			}

			user.setPassword(passwordEncoder.encode(novaSenha));
			return user;
		}

		@Transactional
		public List<User> buscarTodos() {
			return userRepository.findAll();
		}

		@Transactional
        public User buscarPorUsername(String username) {
			return userRepository.findByUsername(username).orElseThrow(
					() -> new EntityNotFoundException(String.format("Usuario com '%s' não encontrado", username))
			);
        }

		@Transactional
		public User.Role buscarRolePorUsername(String username) {
			return userRepository.findRoleByUsername(username);
		}

	}
