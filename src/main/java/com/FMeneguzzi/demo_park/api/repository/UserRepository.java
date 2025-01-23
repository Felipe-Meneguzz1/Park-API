package com.FMeneguzzi.demo_park.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FMeneguzzi.demo_park.api.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
