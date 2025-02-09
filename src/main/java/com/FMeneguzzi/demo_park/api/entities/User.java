package com.FMeneguzzi.demo_park.api.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Id; 

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "username", nullable = false, unique = true, length = 100)
	private String username;
	@Column(name = "password", nullable = false, length = 200)
	private String password;
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false, length = 25)
	private Role role = Role.ROLE_CLIENT;

	@CreatedDate
	@Column(name = "data_criacao")
	private LocalDateTime dataCriacao;
	@LastModifiedDate
	@Column(name = "data_modificacao")
	private LocalDateTime dataModificacao;
	@CreatedBy
	@Column(name = "criado_por")
	private String criadoPor;
	@LastModifiedBy
	@Column(name = "modificado_por")
	private String modificadoPor;
	
	public enum Role {
		ROLE_ADMIN,
		ROLE_CLIENT
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "User [id=" + id + "]";
	}
	
	
 
}
