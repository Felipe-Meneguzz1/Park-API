package com.FMeneguzzi.demo_park.api.service;

import org.springframework.stereotype.Service;

import com.FMeneguzzi.demo_park.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private UserRepository userRepository;
}
