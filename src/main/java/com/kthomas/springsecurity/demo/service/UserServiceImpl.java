package com.kthomas.springsecurity.demo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kthomas.springsecurity.demo.client.ClientUser;
import com.kthomas.springsecurity.demo.dao.RoleDao;
import com.kthomas.springsecurity.demo.dao.UserDao;
import com.kthomas.springsecurity.demo.entity.Role;
import com.kthomas.springsecurity.demo.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	RoleDao roleDao;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	
    private Logger logger = Logger.getLogger(getClass().getName());

	@Override
	@Transactional
	public User findByUserName(String userName) {
		logger.info("INSIDE FIND BY USER NAME");
		return userDao.findByUserName(userName);
	}

	@Override
	@Transactional
	public void save(ClientUser clientUser) {
		
		logger.info("INSIDE SAVE");
		
		logger.info(clientUser.toString());
		
		User user = new User();
		 // assign user details to the user object
		user.setUserName(clientUser.getUserName());
		user.setPassword(passwordEncoder.encode(clientUser.getPassword()));
		user.setFirstName(clientUser.getFirstName());
		user.setLastName(clientUser.getLastName());
		user.setEmail(clientUser.getEmail());

		// give user default role of "employee"
		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_EMPLOYEE")));

		 // save user in the database
		userDao.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				this.mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
