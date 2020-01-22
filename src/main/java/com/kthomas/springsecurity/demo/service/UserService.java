package com.kthomas.springsecurity.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.kthomas.springsecurity.demo.client.ClientUser;
import com.kthomas.springsecurity.demo.entity.User;

public interface UserService extends UserDetailsService {

	User findByUserName(String userName);
	
	void save(ClientUser user);
	
}
