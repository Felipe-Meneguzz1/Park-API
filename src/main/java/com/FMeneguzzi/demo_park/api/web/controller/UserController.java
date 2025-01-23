package com.FMeneguzzi.demo_park.api.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FMeneguzzi.demo_park.api.entities.User;
import com.FMeneguzzi.demo_park.api.service.UserService;

//import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

	@Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
    	User user1 = userService.salvar(user);
    	return ResponseEntity.status(HttpStatus.CREATED).body(user1);

    }

}

