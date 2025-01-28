package com.FMeneguzzi.demo_park.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.FMeneguzzi.demo_park.api.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findByUsername(String username);

   @Query("select u.role from User u where u.username like :username")
    User.Role findRoleByUsername(String username);
}
