package com.kthomas.springsecurity.demo.dao;

import com.kthomas.springsecurity.demo.entity.User;

public interface UserDao {
    User findByUserName(String userName);
    
    void save(User user);
}
