package com.FMeneguzzi.demo_park.api.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FMeneguzzi.demo_park.api.service.UserService;

//import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

	@Autowired
    private UserService userService;

}

